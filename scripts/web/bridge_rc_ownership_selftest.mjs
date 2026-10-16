#!/usr/bin/env node
// Self-test of the Web bridge's RefCounted holds (protocol 37) where a browser run cannot reach them:
//
// - Fallback mode. A browser whose FinalizationRegistry refuses a Kotlin/Wasm wrapper counts no wrapper,
//   so a RefCounted only Kotlin keeps goes with its script. A hold that is not a wrapper (a Sprite2D
//   texture snapshot, `holdRefCounted`) or the end of a temporary's hold (`dropWrapper`) must then change
//   nothing: their -1 would bring the count to 0 and release a handle a Kotlin wrapper still names
//   (`sprite.texture = a; sprite.texture = b` dropped `a`). Chrome and Firefox both accept the wrappers, so
//   no browser run is in this mode.
// - The lent cap evicts the least recently wrapped lend first, not the first minted.
// - A Variant result's RefCounted (a utility, builtin or generic call's: a per-frame `GD.weakref`) is
//   under the same cap; a RefCounted the script made through a typed call (a load, a create()) is not.
//
// The bridge is loaded unmodified into a vm context with the browser pieces it touches stubbed (as
// bridge_simtime_selftest.mjs does); the FinalizationRegistry stub refuses a target marked `refuse`.
import fs from "node:fs";
import path from "node:path";
import vm from "node:vm";
import { fileURLToPath } from "node:url";

const here = path.dirname(fileURLToPath(import.meta.url));
const bridgePath = path.resolve(here, "../../web-runtime/src/webSpikeGodot/assets/kanama-web-bridge.js");
const bridgeSource = fs.readFileSync(bridgePath, "utf8");
const RC_LENT_CAP = Number(/const RC_LENT_CAP = (\d+);/.exec(bridgeSource)?.[1]);
const LENT_GRACE_FRAMES = Number(/const LENT_GRACE_FRAMES = (\d+);/.exec(bridgeSource)?.[1]);

class StubFinalizationRegistry {
  constructor() {
    this.tokens = new Set();
  }
  register(target, _held, token) {
    if (target.refuse) throw new TypeError("invalid target");
    this.tokens.add(token);
  }
  unregister(token) {
    return this.tokens.delete(token);
  }
}

async function loadBridge() {
  const rafCallbacks = [];
  const noop = () => {};
  const element = { textContent: "", style: {}, classList: { add: noop, remove: noop }, addEventListener: noop, setAttribute: noop };
  const context = {
    console: { ...console, warn: noop },
    URLSearchParams,
    location: { search: "" },
    navigator: { userAgent: "node-selftest" },
    performance: { now: () => 0 },
    document: { querySelector: () => element, getElementById: () => element, createElement: () => element, addEventListener: noop },
    requestAnimationFrame: (callback) => rafCallbacks.push(callback),
    FinalizationRegistry: StubFinalizationRegistry,
    KanamaWebPageStartedAt: 0,
    setTimeout,
    clearTimeout,
  };
  context.globalThis = context;
  context.window = context;
  vm.createContext(context);
  const protocolVersion = Number(/const KANAMA_WEB_PROTOCOL_VERSION = (\d+);/.exec(bridgeSource)?.[1]);
  vm.runInContext(bridgeSource, context, { filename: bridgePath });
  const api = {
    kanamaWebProtocolVersion: () => protocolVersion,
    kanamaWebMarkRefCountedHandle: () => 1,
    kanamaWebAdoptObjectHandle: () => 1,
    kanamaWebAdoptResourceHandle: () => 1,
    kanamaWebIsNodeHandle: () => 0,
    kanamaWebDiscardBrowserHandle: () => 1,
    kanamaWebDiscardFreedHandle: () => 1,
  };
  const bridge = await context.bootstrapKanamaWeb(Promise.resolve(api));
  let timestamp = 0;
  // One engine frame: a requestAnimationFrame callback with a new timestamp, then the drain.
  const frame = () => {
    context.requestAnimationFrame(noop);
    timestamp += 16.6;
    rafCallbacks.shift()(timestamp);
    bridge.drainUnheldHandles();
  };
  const frames = (count) => {
    for (let i = 0; i < count; i += 1) frame();
  };
  // A RefCounted handle a script made (a load, a create()): marked by the proxy's track.
  const madeRefCounted = () => {
    const handle = bridge.allocateBrowserHandle("Object", 1);
    bridge.markRefCounted(handle);
    return handle;
  };
  const live = (handle) => bridge.isBrowserHandleLive(handle) === 1;
  return { bridge, frames, madeRefCounted, live };
}

const failures = [];
const expect = (ok, message) => {
  if (!ok) failures.push(message);
};

{
  // Fallback from the start: no wrapper counts, so neither a snapshot hold nor a dropped temporary
  // may release the handle the script's wrappers still name.
  const { bridge, frames, madeRefCounted, live } = await loadBridge();
  const handle = madeRefCounted();
  const wrapper = { refuse: true };
  expect(bridge.registerWrapper(wrapper, handle) === 0, "fallback: registerWrapper must answer 0 when the registry refuses");
  expect(bridge.wrapperRegistryWorks === false, "fallback: a refused registration must switch the registry off");
  bridge.holdRefCounted(handle, 1);
  bridge.holdRefCounted(handle, -1);
  frames(LENT_GRACE_FRAMES + 3);
  expect(live(handle), "fallback: a texture snapshot's hold (+1 then -1) released a handle a Kotlin wrapper still names");
  const retyped = madeRefCounted();
  bridge.registerWrapper(wrapper, retyped);
  bridge.dropWrapper(wrapper, retyped);
  frames(LENT_GRACE_FRAMES + 3);
  expect(live(retyped), "fallback: dropWrapper released a handle a Kotlin wrapper still names");
}
{
  // The registry worked, then refused a later wrapper: the counted wrappers no longer say which
  // ones Kotlin uses, so ending a counted one must not release the handle the uncounted one names.
  const { bridge, frames, madeRefCounted, live } = await loadBridge();
  const handle = madeRefCounted();
  const temporary = {};
  expect(bridge.registerWrapper(temporary, handle) === 1, "late fallback: the first registration must count");
  expect(bridge.registerWrapper({ refuse: true }, handle) === 0, "late fallback: the refused registration must answer 0");
  bridge.dropWrapper(temporary, handle);
  frames(LENT_GRACE_FRAMES + 3);
  expect(live(handle), "late fallback: dropWrapper released a handle the uncounted Kotlin wrapper still names");
}
{
  // Working registry: the same calls do count (the cases above are not vacuous).
  const { bridge, frames, madeRefCounted, live } = await loadBridge();
  const handle = madeRefCounted();
  const wrapper = {};
  expect(bridge.registerWrapper(wrapper, handle) === 1, "registry: registerWrapper must count a wrapper");
  bridge.holdRefCounted(handle, 1);
  bridge.holdRefCounted(handle, -1);
  frames(LENT_GRACE_FRAMES + 3);
  expect(live(handle), "registry: a snapshot hold's end released a handle its wrapper still holds");
  bridge.dropWrapper(wrapper, handle);
  frames(1);
  expect(!live(handle), "registry: the last hold's end (dropWrapper) must release the handle at the next drain");
}
{
  // The lent cap: RC_LENT_CAP + 1 wrapped lends, the first one wrapped again last. The cap evicts
  // the least recently wrapped (the second lend), not the first minted.
  const { bridge, frames } = await loadBridge();
  const lent = [];
  for (let i = 0; i <= RC_LENT_CAP; i += 1) {
    const handle = bridge.lendHandle(1, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    bridge.registerWrapper({}, handle);
    lent.push(handle);
  }
  bridge.registerWrapper({}, lent[0]);
  frames(LENT_GRACE_FRAMES + 2);
  expect(bridge.rcReleasedByCap === 1, `cap: expected 1 lend released by the cap, got ${bridge.rcReleasedByCap}`);
  expect(bridge.isBrowserHandleLive(lent[0]) === 1, "cap: evicted the first minted lend although it was wrapped most recently");
  expect(bridge.isBrowserHandleLive(lent[1]) === 0, "cap: the least recently wrapped lend must go first");
}
{
  // A Variant result is capped like a lend: RC_LENT_CAP + 1 wrapped results after one typed
  // create() the script owns (made first, so the oldest). The cap evicts the first result, never
  // the owned one.
  const { bridge, frames, madeRefCounted, live } = await loadBridge();
  const owned = madeRefCounted();
  bridge.registerWrapper({}, owned);
  const results = [];
  for (let i = 0; i <= RC_LENT_CAP; i += 1) {
    const handle = bridge.mintGenericHandle(1, i % 2 === 0 ? "object" : "resource");
    bridge.markRefCounted(handle);
    bridge.registerWrapper({}, handle);
    results.push(handle);
  }
  frames(LENT_GRACE_FRAMES + 2);
  expect(bridge.rcReleasedByCap === 1, `variant cap: expected 1 Variant result released by the cap, got ${bridge.rcReleasedByCap}`);
  expect(!live(results[0]), "variant cap: a Variant result's RefCounted is not under the lent cap");
  expect(live(results[1]), "variant cap: the cap released more than the oldest Variant result");
  expect(live(owned), "variant cap: the cap released a typed create() the script owns");
}

if (failures.length > 0) {
  console.error("bridge_rc_ownership_selftest FAIL:");
  for (const failure of failures) console.error(`  ${failure}`);
  process.exit(1);
}
console.log(
  "bridge_rc_ownership_selftest PASS: without a working FinalizationRegistry a snapshot hold or a dropped temporary releases nothing; with one they count; the lent cap evicts the least recently wrapped lend, and bounds Variant results but never a typed create()",
);

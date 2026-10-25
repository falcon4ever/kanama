#!/usr/bin/env node
// Self-test of the Web bridge's live-script census (task 142 review): `liveScriptsByClass` counts a
// script from its first _ready to its teardown. A second _ready of the same script (Godot runs _ready
// again after request_ready) is not a new live script, and a handle VALUE that comes back after
// teardown is a new script: script slots are reused and their 14-bit generation wraps after about 16k
// spawns, so "first _ready" cannot be read from scriptNameByHandle, which is never cleared.
//
// The bridge is loaded unmodified into a vm context with the browser pieces it touches stubbed (the
// loader is bridge_simtime_selftest.mjs's), and driven through recordReady / teardownScriptHandles.
import fs from "node:fs";
import path from "node:path";
import vm from "node:vm";
import { fileURLToPath } from "node:url";

const here = path.dirname(fileURLToPath(import.meta.url));
const bridgePath = path.resolve(here, "../../web-runtime/src/webSpikeGodot/assets/kanama-web-bridge.js");

async function loadBridge() {
  const rafCallbacks = [];
  const noop = () => {};
  const element = { textContent: "", style: {}, classList: { add: noop, remove: noop }, addEventListener: noop, setAttribute: noop };
  const context = {
    console,
    URLSearchParams,
    location: { search: "" },
    navigator: { userAgent: "node-selftest" },
    performance: { now: () => 0 },
    document: { querySelector: () => element, getElementById: () => element, createElement: () => element, addEventListener: noop },
    requestAnimationFrame: (callback) => rafCallbacks.push(callback),
    KanamaWebPageStartedAt: 0,
    setTimeout,
    clearTimeout,
  };
  context.globalThis = context;
  context.window = context;
  vm.createContext(context);
  const bridgeSource = fs.readFileSync(bridgePath, "utf8");
  const protocolVersion = Number(/const KANAMA_WEB_PROTOCOL_VERSION = (\d+);/.exec(bridgeSource)?.[1]);
  vm.runInContext(bridgeSource, context, { filename: bridgePath });
  const api = {
    // the version the bridge itself pins: this test is not about the protocol, so it must not go stale when the protocol moves
    kanamaWebProtocolVersion: () => protocolVersion,
    kanamaWebProcess: () => 0,
    kanamaWebPhysicsProcess: () => 0,
  };
  const bridge = await context.bootstrapKanamaWeb(Promise.resolve(api));
  // the dispatch bookkeeping (handle ownership, exercised-member census) is not under test: run the callback
  bridge.invoke = (_handle, _callback, _member, action) => action();
  return { bridge };
}

const failures = [];
const { bridge } = await loadBridge();
const live = () => bridge.liveScriptsByClass["game.Mob"] ?? 0;
const handle = 4097;
bridge.recordReady(handle, 1, "game.Mob");
if (live() !== 1) failures.push(`first _ready: live ${live()}, expected 1`);
bridge.recordReady(handle, 1, "game.Mob");
if (live() !== 1) failures.push(`second _ready of the same script (request_ready): live ${live()}, expected 1`);
bridge.teardownScriptHandles(handle, "game.Mob");
if (live() !== 0) failures.push(`after teardown: live ${live()}, expected 0`);
// The same handle value again, as a slot whose generation wrapped: a new live script.
bridge.recordReady(handle, 1, "game.Mob");
if (live() !== 1) failures.push(`a reused handle value's first _ready: live ${live()}, expected 1 (the census undercounts the class)`);
bridge.teardownScriptHandles(handle, "game.Mob");
bridge.teardownScriptHandles(handle, "game.Mob");
if (live() !== 0) failures.push(`a second teardown of one handle: live ${live()}, expected 0`);
const ready = bridge.match3ReadyByClass["game.Mob"] ?? 0;
if (ready !== 3) failures.push(`ready dispatches counted ${ready}, expected 3`);

if (failures.length > 0) {
  console.error("bridge_live_scripts_selftest FAIL:");
  for (const failure of failures) console.error(`  ${failure}`);
  process.exit(1);
}
console.log("bridge_live_scripts_selftest PASS: a second _ready is no new live script, a reused handle value counts again after teardown, a double teardown counts once");

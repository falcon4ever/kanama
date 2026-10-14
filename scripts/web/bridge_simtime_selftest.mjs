#!/usr/bin/env node
// Self-test of the Web bridge's simulated clock (task 138 item 33 review): `simSeconds` is the game's time,
// once per engine frame, and must NOT scale with the number of scripted nodes. `process(handle, delta)` and
// `physicsFrame(handle, delta)` run for every scripted handle; adding `delta` in each made dodge's
// simSeconds 4-7x game time and made any "per simulated second" budget a function of the node count.
//
// The bridge is loaded unmodified into a vm context with the browser pieces it touches stubbed; the test
// drives it the way Godot's main loop does (one requestAnimationFrame callback per frame, then `process`
// for each scripted handle, `physicsFrame` for each handle once per physics step) and reads `simSeconds`.
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
  vm.runInContext(fs.readFileSync(bridgePath, "utf8"), context, { filename: bridgePath });
  const api = {
    kanamaWebProtocolVersion: () => 35,
    kanamaWebProcess: () => 0,
    kanamaWebPhysicsProcess: () => 0,
  };
  const bridge = await context.bootstrapKanamaWeb(Promise.resolve(api));
  // the dispatch bookkeeping (handle ownership, exercised-member census) is not under test: run the callback
  bridge.invoke = (_handle, _callback, _member, action) => action();
  // one engine frame = one requestAnimationFrame callback with a new timestamp
  let timestamp = 0;
  const frame = () => {
    context.requestAnimationFrame(noop);
    timestamp += 16.6;
    rafCallbacks.shift()(timestamp);
  };
  return { bridge, frame };
}

function approx(actual, expected) {
  return Math.abs(actual - expected) < 1e-9;
}

const failures = [];

async function processClock(handles) {
  const { bridge, frame } = await loadBridge();
  const frames = 100;
  const delta = 0.02;
  for (let i = 0; i < frames; i += 1) {
    frame();
    for (let h = 1; h <= handles; h += 1) bridge.process(h, delta);
  }
  return { sim: bridge.simSeconds, expected: frames * delta, calls: bridge.processCalls };
}

async function physicsClock(handles, stepsPerFrame) {
  const { bridge, frame } = await loadBridge();
  const frames = 100;
  const delta = 1 / 60;
  for (let i = 0; i < frames; i += 1) {
    frame();
    for (let step = 0; step < stepsPerFrame; step += 1) {
      for (let h = 1; h <= handles; h += 1) bridge.physicsFrame(h, delta);
    }
  }
  return { sim: bridge.simSeconds, expected: frames * stepsPerFrame * delta, calls: bridge.physicsProcessCalls };
}

for (const handles of [1, 5, 60]) {
  const r = await processClock(handles);
  if (!approx(r.sim, r.expected)) failures.push(`_process with ${handles} scripted node(s): simSeconds ${r.sim}, game time ${r.expected} (${r.calls} dispatches)`);
}
for (const [handles, steps] of [[1, 1], [8, 1], [8, 2], [30, 3]]) {
  const r = await physicsClock(handles, steps);
  if (!approx(r.sim, r.expected)) failures.push(`_physics_process, ${handles} node(s) x ${steps} step(s) per frame: simSeconds ${r.sim}, game time ${r.expected} (${r.calls} dispatches)`);
}
{
  // a game with both: the process clock is kept, physics time is not added on top of it
  const { bridge, frame } = await loadBridge();
  for (let i = 0; i < 50; i += 1) {
    frame();
    bridge.physicsFrame(1, 1 / 60);
    bridge.physicsFrame(2, 1 / 60);
    bridge.process(1, 0.02);
    bridge.process(2, 0.02);
  }
  if (!approx(bridge.simSeconds, 50 * 0.02)) failures.push(`process + physics: simSeconds ${bridge.simSeconds}, expected the _process clock ${50 * 0.02}`);
}

if (failures.length > 0) {
  console.error("bridge_simtime_selftest FAIL:");
  for (const failure of failures) console.error(`  ${failure}`);
  process.exit(1);
}
console.log("bridge_simtime_selftest PASS: simSeconds is game time (one delta per engine frame) for 1, 5 and 60 scripted nodes; physics clock counts steps, not nodes; process + physics keeps one clock");

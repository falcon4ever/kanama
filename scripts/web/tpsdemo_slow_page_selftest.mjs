#!/usr/bin/env node
// Self-test of the tpsdemo driver on a page that answers only between frames.
//
// A page runs a script (a CDP Runtime.evaluate, a BiDi script.evaluate) only between two frames. On
// the hosted Linux runner Chrome draws on SwiftShader, and one frame of tpsdemo's level takes 11-31 s
// there (Godot 4.8; 9-19 s under 4.7.2), most of it with the main thread blocked in a WebGL call that
// waits for the GPU process. Each frame runs at most 8 physics steps (max_physics_steps_per_frame).
// The cell failed there in two ways: an evaluate that waited past the old 20 s CDP call limit, and
// wall-clock windows that closed on their first sample (a 4 s input hold that covered two frames and
// moved the player 0.3 m; a teardown window that saw only the frame still holding the level).
//
// This drives the real runTpsdemo() against a fake page (no browser): `evaluate` runs the driver's
// own snapshot/call source inside a vm context whose KanamaWebBridge is a small frame-stepped game,
// answers in the idle moment after a frame or else when the frame in progress ends, and rejects as chrome_cdp does when that is
// further away than PAGE_EVALUATE_TIMEOUT_MS. Time is virtual, so the cases take milliseconds.
//
// The page that never answers (a frozen main thread) must still fail, before the cell's budget: the
// longer evaluate limit is for slow frames, not for hangs.
import fs from "node:fs";
import os from "node:os";
import path from "node:path";
import vm from "node:vm";
import { fileURLToPath } from "node:url";
import { PAGE_EVALUATE_TIMEOUT_MS, PageTimeoutError } from "./drivers/envelope.mjs";
import { runTpsdemo } from "./drivers/demos/tpsdemo.mjs";

const HERE = path.dirname(fileURLToPath(import.meta.url));
const SMOKE_HANDLE = 7;
const PLAYER_HANDLE = 65565;
const START_GAME_ID = 3;
const TEARDOWN_ID = 4;
// The unscaled per-demo budget (scripts/web/demos.sh: tpsdemo 600 s). CI doubles it; the slow
// scenarios must fit the unscaled one.
const BUDGET_MS = 600_000;
const PROCESS_SCRIPTS = 19; // _process calls per frame, as the real level counts them
const PHYSICS_SCRIPTS = 5; // _physics_process calls per physics step
const MAX_STEPS_PER_FRAME = 8; // Godot's max_physics_steps_per_frame
const IDLE_MS = 5; // the page's idle moment between two frames
const ACCEL = 10; // m/s^2 while move_forward is held
const MAX_SPEED = 6; // m/s

/**
 * page:
 *   menuMs, compileMs, levelMs, tornMs  frame lengths in the menu, the first level frame (shader
 *                                       compiles), the level, and after the teardown
 *   freeze(state)    true from then on: the frame in progress never ends (a frozen main thread)
 *   moveIgnored      the move_forward action reaches nothing (the player never moves)
 *   physicsStopsOnPress the level's physics stops when move_forward is pressed (frames still advance)
 *   teardownLeak     handles the teardown leaves behind
 */
function makePage({
  menuMs = 16,
  compileMs = 16,
  levelMs = 16,
  tornMs = 16,
  freeze = () => false,
  moveIgnored = false,
  physicsStopsOnPress = false,
  teardownLeak = 0,
}) {
  const state = {
    phase: "menu", // menu -> loading -> level -> tearing -> torn
    loadingFrames: 0,
    levelFrames: 0,
    steps: 0,
    pressed: false,
    speed: 0,
    z: 78.76,
    frozen: false,
    physicsStopped: false,
    pressedSteps: 0, // physics steps run with move_forward held
  };
  const bridge = {
    mode: "tpsdemo",
    results: { protocolVersion: 39 },
    tpsMainHandle: 65539,
    tpsMenuHandle: 65540,
    tpsLevelHandle: 0,
    tpsPlayerHandle: 0,
    smokeHandle: SMOKE_HANDLE,
    readyCount: 4,
    match3ReadyByClass: { "tps.Main": 1, "tps.Menu": 1 },
    processCalls: 0,
    physicsProcessCalls: 0,
    appliedCommands: 0,
    liveBrowserHandleCount: 30,
    maxLiveBrowserHandles: 30,
    kotlinToGodotCalls: 10,
    callbackErrors: 0,
    callbackErrorLog: null,
    physicsAfterProcessSameTick: 0,
    api: {
      kanamaWebPendingSignalCallbackCount: () => 0,
      kanamaWebPendingCoroutineCount: () => 0,
      kanamaWebRegisteredCoroutineJobCount: () => 0,
    },
    callNoArgs(handle, id) {
      if (handle === SMOKE_HANDLE && id === START_GAME_ID && state.phase === "menu") state.phase = "loading";
      else if (handle === SMOKE_HANDLE && id === TEARDOWN_ID) state.phase = "tearing";
      else throw new Error(`fake tpsdemo page: unexpected call handle=${handle} id=${id}`);
    },
    immediateObjectQuery(opcode, handle, name) {
      if (handle !== PLAYER_HANDLE || name !== "move_forward") throw new Error(`fake tpsdemo page: unexpected action ${opcode} ${handle} ${name}`);
      if (opcode === 86) {
        state.pressed = !moveIgnored;
        if (physicsStopsOnPress) state.physicsStopped = true;
      }
      else if (opcode === 87) state.pressed = false;
      else throw new Error(`fake tpsdemo page: unexpected action opcode ${opcode}`);
    },
    immediateNoArgsVector3X(opcode, handle) {
      if (opcode !== 138 || handle !== PLAYER_HANDLE) throw new Error(`fake tpsdemo page: unexpected position read ${opcode} ${handle}`);
      return 64.82;
    },
    immediateNoArgsVector3Y: () => -1.2,
    immediateNoArgsVector3Z: () => state.z,
  };

  const frameLength = () => {
    if (state.frozen) return Infinity;
    if (state.phase === "menu" || state.phase === "loading") return menuMs;
    if (state.phase === "level" && state.levelFrames === 0) return compileMs;
    if (state.phase === "torn") return tornMs;
    return levelMs;
  };
  // One frame of `ms`: the level's scripts, at most MAX_STEPS_PER_FRAME physics steps.
  const runFrame = (ms) => {
    if (state.phase === "loading") {
      state.loadingFrames += 1;
      if (state.loadingFrames >= 2) {
        state.phase = "level";
        bridge.tpsLevelHandle = 65550;
        bridge.tpsPlayerHandle = PLAYER_HANDLE;
        Object.assign(bridge.match3ReadyByClass, {
          "tps.Level": 1,
          "tps.Player": 1,
          "tps.PlayerInputSynchronizer": 1,
          "tps.RedRobot": 4,
          "tps.Part": 12,
          "tps.CameraNoiseShakeEffect": 1,
        });
        bridge.readyCount = 25;
        bridge.liveBrowserHandleCount = 290;
        bridge.maxLiveBrowserHandles = 290;
      }
      return;
    }
    if (state.phase === "tearing") {
      // The frame that frees the scene root still holds part of the level; the next one releases it.
      state.phase = "torn";
      bridge.liveBrowserHandleCount = 100;
      return;
    }
    if (state.phase === "torn") {
      bridge.liveBrowserHandleCount = teardownLeak;
      bridge.processCalls += 1;
      return;
    }
    if (state.phase !== "level") return;
    state.levelFrames += 1;
    bridge.processCalls += PROCESS_SCRIPTS;
    const steps = Math.min(MAX_STEPS_PER_FRAME, Math.max(1, Math.round((ms / 1000) * 60)));
    for (let step = 0; step < steps && !state.physicsStopped; step += 1) {
      state.steps += 1;
      bridge.physicsProcessCalls += PHYSICS_SCRIPTS;
      bridge.appliedCommands += 18;
      bridge.kotlinToGodotCalls += 40;
      if (state.pressed) state.pressedSteps += 1;
      state.speed = state.pressed ? Math.min(MAX_SPEED, state.speed + ACCEL / 60) : 0;
      state.z += state.speed / 60;
    }
    bridge.appliedCommands += 3;
  };

  let frameEnd = 0; // when the frame in progress ends (virtual ms)
  let lastBoundary = -Infinity; // when the last frame ended
  const queued = []; // evaluates whose caller timed out; the page still runs them when it gets there
  const context = { KanamaWebBridge: bridge, gc: () => {} };
  context.globalThis = context;
  vm.createContext(context);
  const runScript = (source) => vm.runInContext(source, context);
  // Run every frame that ends by `time`; each boundary runs the scripts queued for it.
  const advanceTo = (time) => {
    while (frameEnd <= time) {
      const ms = frameLength();
      if (!Number.isFinite(ms)) return;
      runFrame(ms);
      for (const script of queued.splice(0)) runScript(script);
      if (freeze(state, bridge)) state.frozen = true;
      lastBoundary = frameEnd;
      frameEnd += frameLength();
    }
  };
  // When the page answers a script asked at `time`: at once in the idle moment right after a frame
  // (scripts queued there run before the next frame starts), else when the frame in progress ends.
  const answerTime = (time) => {
    advanceTo(time);
    return time - lastBoundary < IDLE_MS ? time : frameEnd;
  };
  return { bridge, state, advanceTo, runScript, queued, answerTime, start: (now) => (frameEnd = now + menuMs) };
}

async function runScenario(pageOptions) {
  const exportDir = fs.mkdtempSync(path.join(os.tmpdir(), "tpsdemo-slow-page-selftest-"));
  fs.mkdirSync(path.join(exportDir, "kanama-web"));
  fs.writeFileSync(
    path.join(exportDir, "kanama-web", "KanamaWebProtocol.generated.json"),
    JSON.stringify({
      scripts: [{ className: "tps.Smoke", methods: [{ name: "smoke_start_game", id: START_GAME_ID }, { name: "smoke_teardown", id: TEARDOWN_ID }] }],
    }),
  );
  const page = makePage(pageOptions);

  const realSetTimeout = globalThis.setTimeout;
  const realNow = Date.now;
  let virtualNow = 1_000_000;
  page.start(virtualNow);
  let longestWait = 0;
  let timeouts = 0;
  // chrome_cdp's evaluate: the page answers between two frames, or the call rejects after PAGE_EVALUATE_TIMEOUT_MS (the page still runs the script when it gets there).
  const evaluate = async (source) => {
    const asked = virtualNow;
    const answerAt = page.answerTime(asked);
    if (!Number.isFinite(answerAt) || answerAt - asked > PAGE_EVALUATE_TIMEOUT_MS) {
      if (Number.isFinite(answerAt)) page.queued.push(source);
      virtualNow = asked + PAGE_EVALUATE_TIMEOUT_MS;
      timeouts += 1;
      throw new PageTimeoutError(PAGE_EVALUATE_TIMEOUT_MS);
    }
    page.advanceTo(answerAt);
    longestWait = Math.max(longestWait, answerAt - asked);
    virtualNow = answerAt;
    return page.runScript(source);
  };
  globalThis.setTimeout = (callback, ms = 0) => realSetTimeout(() => { virtualNow += ms; callback(); }, 0);
  Date.now = () => virtualNow;
  const started = virtualNow;
  try {
    const result = await runTpsdemo({
      url: "http://fake.invalid/",
      evaluate,
      navigate: async () => {},
      deadline: started + BUDGET_MS,
      exportDir,
    });
    return { result, error: null, elapsedMs: virtualNow - started, longestWait, timeouts, page: page.state };
  } catch (error) {
    return { result: null, error, elapsedMs: virtualNow - started, longestWait, timeouts };
  } finally {
    globalThis.setTimeout = realSetTimeout;
    Date.now = realNow;
    fs.rmSync(exportDir, { recursive: true, force: true });
  }
}

const failures = [];
const expect = (condition, message) => {
  if (!condition) failures.push(message);
};
const redChecks = (result) => Object.entries(result.checks).filter(([, ok]) => ok !== true).map(([name]) => name);

// The hosted runner's Chrome: quick menu frames, a long first level frame (shader compiles), long
// level frames, quick frames once the level is gone.
const runner = (levelMs) => ({ menuMs: 700, compileMs: 30_000, levelMs, tornMs: 400 });
const afterLevelReady = (state) => state.phase === "level" && state.levelFrames >= 1;
const midHold = (state) => state.pressed && state.steps > 30;

// [label, page, expected: { red: [checks] (empty: all green), throws: text the error must carry,
// maxTimeouts: evaluates allowed to time out before the driver gives up (a read retry must not wait
// out the limit again and again on a page that does not answer) }]
const scenarios = [
  ["a fast page (a workstation, Firefox on the runner)", {}, { red: [] }],
  ["the runner's Chrome, 11 s frames (a fast runner)", runner(11_000), { red: [] }],
  ["the runner's Chrome, 23 s frames (a slow runner)", runner(23_000), { red: [] }],
  ["the runner's Chrome, 31 s frames (the slowest frame measured)", runner(31_000), { red: [] }],
  ["a slow page whose input moves nothing", { ...runner(23_000), moveIgnored: true }, { red: ["playerMovedOnInput"] }],
  ["a slow page whose physics stops when the hold starts", { ...runner(23_000), physicsStopsOnPress: true }, { red: ["playerMovedOnInput"] }],
  ["a slow page whose teardown leaks", { ...runner(23_000), teardownLeak: 12 }, { red: ["fullTeardownToZero"] }],
  ["a slow page that freezes once the level is up", { ...runner(23_000), freeze: afterLevelReady }, { throws: "the page did not answer", maxTimeouts: 2 }],
  ["a slow page that freezes during the input hold", { ...runner(23_000), freeze: midHold }, { throws: "timed out" }],
  ["a fast page that freezes once the level is up", { freeze: afterLevelReady }, { throws: "" }],
  ["a fast page that freezes during the teardown", { freeze: (state) => state.phase === "torn" }, { throws: "timed out", orRed: ["fullTeardownToZero"] }],
];

for (const [label, page, expected] of scenarios) {
  const { result, error, elapsedMs, longestWait, timeouts, page: pageState } = await runScenario(page);
  const seconds = (elapsedMs / 1000).toFixed(0);
  expect(elapsedMs < BUDGET_MS, `${label}: the driver took ${seconds}s, past the ${BUDGET_MS / 1000}s budget`);
  if (expected.throws !== undefined) {
    // A frozen page must fail: an error, or (where the expected list allows) a red check.
    const red = result ? redChecks(result) : [];
    const failed = error ? String(error.message ?? error).includes(expected.throws) : (expected.orRed ?? []).some((name) => red.includes(name));
    expect(failed, `${label}: a frozen page must fail, got ${error ? `error "${error.message}"` : `checks red: [${red.join(", ")}]`}`);
    if (expected.maxTimeouts !== undefined) {
      expect(timeouts <= expected.maxTimeouts, `${label}: ${timeouts} evaluates timed out before the driver gave up, expected at most ${expected.maxTimeouts}`);
    }
    console.log(`  ${label}: failed in ${seconds}s (${error ? error.message.split("\n")[0] : `red=[${red.join(",")}]`}), ${timeouts} timed-out evaluate(s)`);
    continue;
  }
  if (error) {
    expect(false, `${label}: the driver threw: ${error.message}`);
    console.log(`  ${label}: threw after ${seconds}s: ${error.message.split("\n")[0]}`);
    continue;
  }
  const red = redChecks(result);
  if (expected.red.length === 0) {
    expect(red.length === 0, `${label}: checks red: ${red.join(", ")}`);
    expect(result.boundaryErrors.length === 0, `${label}: boundaryErrors ${JSON.stringify(result.boundaryErrors)}`);
    // The hold is 40 physics steps from the press (HOLD_PHYSICS_CALLS / PHYSICS_SCRIPTS), however
    // many frames ran between the settled sample and the press.
    expect(pageState.pressedSteps >= 40, `${label}: move_forward was held for ${pageState.pressedSteps} physics steps, expected 40+`);
  } else {
    for (const name of expected.red) expect(red.includes(name), `${label}: ${name} must be false (red: [${red.join(", ")}])`);
  }
  console.log(
    `  ${label}: ${seconds}s, longest wait ${(longestWait / 1000).toFixed(1)}s, displacement=${result.crossings.playerDisplacement} ` +
      `physics=${result.crossings.physicsProcessCalls} held=${pageState.pressedSteps} steps red=[${red.join(",")}]`,
  );
}

// The fake models chrome_cdp's evaluate with PAGE_EVALUATE_TIMEOUT_MS; the real driver must use it.
const chromeDriver = fs.readFileSync(path.join(HERE, "drivers", "chrome_cdp.mjs"), "utf-8");
expect(
  /call\(\s*"Runtime\.evaluate",[^;]*PAGE_EVALUATE_TIMEOUT_MS/.test(chromeDriver),
  "chrome_cdp.mjs no longer gives Runtime.evaluate PAGE_EVALUATE_TIMEOUT_MS (the limit this self-test models)",
);

if (failures.length > 0) {
  console.error(`tpsdemo slow-page self-test: FAIL (${failures.length})`);
  for (const failure of failures) console.error(`  - ${failure}`);
  process.exit(1);
}
console.log(`tpsdemo slow-page self-test: PASS (${scenarios.length} scenarios)`);

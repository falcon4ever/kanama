#!/usr/bin/env node
// Self-test of the dodge driver's round-restart budget (task 138 tooling bundle, fix round 2).
//
// Dodge restarts a round (new_game) when the player is hit before enough mobs have spawned, and the
// mob counter is cumulative across rounds. Without a budget, a build where the player dies on the FIRST
// mob of every round passes: four rounds x one mob = the four mobs the gate asks for. The driver now puts
// `roundRestarts` in the envelope and fails `restartBudgetNotExhausted` when all three restarts were used.
//
// This drives the real runDodge() against a fake bridge (no browser): `evaluate` runs the driver's own
// snapshot/call source inside a vm context whose KanamaWebBridge is a small game simulation. Time is
// virtual (setTimeout/Date.now are replaced for the duration of the run), so the cases take milliseconds.
import fs from "node:fs";
import os from "node:os";
import path from "node:path";
import vm from "node:vm";
import { runDodge } from "./drivers/demos/dodge.mjs";

const NEW_GAME_ID = 8;
const GAME_OVER_ID = 7;
const TEARDOWN_ID = 9;
const MOB_LIFETIME_TICKS = 5; // a mob queue_frees itself this many 150 ms polls after it spawned
const SPAWN_EVERY_TICKS = 3; // MobTimer

/**
 * `deathAfterMobs(round)` is how many mobs the player survives spawning in that round (1-based);
 * Infinity means the player never dies.
 */
function makeFakeBridge(deathAfterMobs) {
  const state = { round: 0, roundMobs: 0, ticksSinceSpawn: 0, active: false, mobAges: [], gameOvers: 0 };
  const bridge = {
    mode: "dodge",
    results: { protocolVersion: 35 },
    dodgeMainHandle: 1,
    smokeHandle: 2,
    readyCount: 4,
    match3ReadyByClass: { "dodge.Main": 1, "dodge.Player": 1, "dodge.HUD": 1, "dodge.Mob": 0 },
    match3PackedSceneInstantiations: 0,
    match3AddChildCommands: 0,
    exercisedMembers: { "dodge.Main": {} },
    dodgeMethodId: (name) => (name === "game_over" ? GAME_OVER_ID : NEW_GAME_ID),
    liveBrowserHandleCount: 4,
    maxLiveBrowserHandles: 4,
    scriptNameByHandle: {},
    browserHandleSlots: [],
    kotlinToGodotCalls: 10,
    callbackErrors: 0,
    processCalls: 0,
    simSeconds: 0,
    api: {
      kanamaWebIsLive: () => 1,
      kanamaWebPendingSignalCallbackCount: () => 0,
      kanamaWebPendingCoroutineCount: () => 0,
      kanamaWebRegisteredCoroutineJobCount: () => 0,
    },
    callNoArgs(handle, id) {
      if (id === NEW_GAME_ID) {
        state.round += 1;
        state.roundMobs = 0;
        state.ticksSinceSpawn = 0;
        state.active = true;
        bridge.liveBrowserHandleCount -= state.mobAges.length; // new_game queue_frees the mobs group
        state.mobAges = [];
      } else if (id === TEARDOWN_ID) {
        bridge.liveBrowserHandleCount = 0;
      }
    },
  };
  // One simulated 150 ms step, taken whenever the driver polls a snapshot.
  const step = () => {
    bridge.processCalls += 1;
    bridge.simSeconds += 0.15;
    bridge.kotlinToGodotCalls += 3;
    state.mobAges = state.mobAges.map((age) => age + 1);
    const expired = state.mobAges.filter((age) => age >= MOB_LIFETIME_TICKS).length;
    state.mobAges = state.mobAges.filter((age) => age < MOB_LIFETIME_TICKS);
    bridge.liveBrowserHandleCount -= expired;
    if (!state.active) return;
    state.ticksSinceSpawn += 1;
    if (state.ticksSinceSpawn < SPAWN_EVERY_TICKS) return;
    state.ticksSinceSpawn = 0;
    state.roundMobs += 1;
    state.mobAges.push(0);
    bridge.match3PackedSceneInstantiations += 1;
    bridge.match3AddChildCommands += 1;
    bridge.liveBrowserHandleCount += 1;
    bridge.maxLiveBrowserHandles = Math.max(bridge.maxLiveBrowserHandles, bridge.liveBrowserHandleCount);
    if (state.roundMobs >= deathAfterMobs(state.round)) {
      state.active = false; // Main.game_over: MobTimer stops, the mobs already out keep moving
      state.gameOvers += 1;
      bridge.exercisedMembers["dodge.Main"]["method#" + GAME_OVER_ID] = state.gameOvers;
    }
  };
  return { bridge, step };
}

async function runScenario(deathAfterMobs) {
  const exportDir = fs.mkdtempSync(path.join(os.tmpdir(), "dodge-restart-selftest-"));
  fs.mkdirSync(path.join(exportDir, "kanama-web"));
  fs.writeFileSync(
    path.join(exportDir, "kanama-web", "KanamaWebProtocol.generated.json"),
    JSON.stringify({
      scripts: [
        { className: "dodge.SmokeQuit", methods: [{ name: "smoke_teardown", id: TEARDOWN_ID }] },
      ],
    }),
  );
  const { bridge, step } = makeFakeBridge(deathAfterMobs);
  const context = { KanamaWebBridge: bridge, document: { querySelector: () => ({ clientWidth: 480, clientHeight: 720 }) } };
  context.globalThis = context;
  vm.createContext(context);
  const evaluate = async (source) => {
    // A snapshot is the driver's poll: advance the fake game one step per poll.
    if (source.includes("liveByKind")) step();
    return vm.runInContext(source, context);
  };

  // Virtual time: every wait resolves at once and advances the clock by the requested amount.
  const realSetTimeout = globalThis.setTimeout;
  const realNow = Date.now;
  let virtualNow = 1_000_000;
  globalThis.setTimeout = (callback, ms = 0) => realSetTimeout(() => { virtualNow += ms; callback(); }, 0);
  Date.now = () => virtualNow;
  try {
    return await runDodge({
      url: "http://fake.invalid/",
      evaluate,
      navigate: async () => {},
      deadline: virtualNow + 300_000,
      exportDir,
    });
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

// Healthy: the player survives (never hit) -> no restarts, every check green.
{
  const result = await runScenario(() => Infinity);
  expect(result.crossings.roundRestarts === 0, `healthy: roundRestarts is ${result.crossings.roundRestarts}, expected 0`);
  expect(result.checks.restartBudgetNotExhausted === true, "healthy: restartBudgetNotExhausted must be true");
  const red = Object.entries(result.checks).filter(([, ok]) => ok !== true).map(([n]) => n);
  expect(red.length === 0, `healthy: checks red: ${red.join(", ")}`);
  expect(result.boundaryErrors.length === 0, `healthy: boundaryErrors ${JSON.stringify(result.boundaryErrors)}`);
}

// One unlucky round (hit on the first mob of round 1 only) -> one restart is within budget.
{
  const result = await runScenario((round) => (round === 1 ? 1 : Infinity));
  expect(result.crossings.roundRestarts === 1, `one early death: roundRestarts is ${result.crossings.roundRestarts}, expected 1`);
  expect(result.checks.restartBudgetNotExhausted === true, "one early death: restartBudgetNotExhausted must be true");
  const red = Object.entries(result.checks).filter(([, ok]) => ok !== true).map(([n]) => n);
  expect(red.length === 0, `one early death: checks red: ${red.join(", ")}`);
}

// Instant kill: every round ends after one mob. The cumulative mob counter still reaches the four mobs the
// other checks ask for, so only the restart budget can catch it.
{
  const result = await runScenario(() => 1);
  expect(result.crossings.roundRestarts === 3, `instant kill: roundRestarts is ${result.crossings.roundRestarts}, expected 3`);
  expect(result.checks.mobsInstantiated === true, "instant kill: the scenario no longer hides behind the cumulative counter (mobsInstantiated is false); the self-test proves nothing");
  expect(result.checks.restartBudgetNotExhausted === false, "instant kill: restartBudgetNotExhausted must be false");
  expect(
    result.boundaryErrors.some((message) => message.includes("restart budget exhausted") && message.includes("1, 2, 3 mob(s)")),
    `instant kill: boundaryErrors must name the early game overs: ${JSON.stringify(result.boundaryErrors)}`,
  );
}

if (failures.length > 0) {
  console.error(`dodge_restart_selftest: ${failures.length} failure(s)`);
  for (const failure of failures) console.error(`  FAIL ${failure}`);
  process.exit(1);
}
console.log("dodge_restart_selftest: OK (healthy 0 restarts, one early death 1 restart, instant kill red)");

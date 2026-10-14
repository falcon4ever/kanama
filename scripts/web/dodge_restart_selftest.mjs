#!/usr/bin/env node
// Self-test of the dodge driver's round-restart budget (task 138 tooling bundle, fix round 2).
//
// Dodge restarts a round (new_game) when the player is hit before enough mobs have spawned, and the
// bridge's mob counter is cumulative across rounds. Judged cumulatively, a build where the player dies on
// the 1st (or 2nd, or 3rd) mob of every round passes: 1+1+1+1, or 2+2 = 4 mobs. The driver therefore
// judges mobs PER ROUND (`mobsInstantiated` = the best round reached 4) and also fails
// `restartBudgetNotExhausted` when all three restarts were used. The scenarios below pin both.
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
const redChecks = (result) => Object.entries(result.checks).filter(([, ok]) => ok !== true).map(([name]) => name);

// [label, deathAfterMobs(round), expected restarts, expected best-round mobs (min), red?]
// A round ends when the player is hit; "healthy" never is.
const scenarios = [
  ["healthy", () => Infinity, 0, 4, false],
  ["one early death then healthy", (round) => (round === 1 ? 1 : Infinity), 1, 4, false],
  ["2 mobs per round", () => 2, 3, 2, true],
  ["3 mobs per round", () => 3, 3, 3, true],
  ["1 mob per round", () => 1, 3, 1, true],
  ["1,1,1 then healthy", (round) => (round <= 3 ? 1 : Infinity), 3, 4, true],
];

for (const [label, deathAfterMobs, restarts, bestRound, expectRed] of scenarios) {
  const result = await runScenario(deathAfterMobs);
  const red = redChecks(result);
  expect(result.crossings.roundRestarts === restarts, `${label}: roundRestarts is ${result.crossings.roundRestarts}, expected ${restarts}`);
  expect(
    result.crossings.bestRoundMobInstantiations >= bestRound && (bestRound >= 4 || result.crossings.bestRoundMobInstantiations === bestRound),
    `${label}: bestRoundMobInstantiations is ${result.crossings.bestRoundMobInstantiations}, expected ${bestRound}${bestRound >= 4 ? "+" : ""}`,
  );
  if (expectRed) {
    expect(red.length > 0, `${label}: expected a red check, but every check is green (the run would pass)`);
    expect(result.boundaryErrors.length > 0 || result.checks.mobsInstantiated === false, `${label}: red without a boundary error or a failed mobsInstantiated`);
  } else {
    expect(red.length === 0, `${label}: checks red: ${red.join(", ")}`);
    expect(result.boundaryErrors.length === 0, `${label}: boundaryErrors ${JSON.stringify(result.boundaryErrors)}`);
  }
  console.log(`  ${label}: restarts=${result.crossings.roundRestarts} bestRound=${result.crossings.bestRoundMobInstantiations} cumulative=${result.crossings.mobInstantiations} red=[${red.join(",")}]`);
}

// The two ways to go red are separate checks: a run that never has a 4-mob round fails mobsInstantiated; a run
// that needed every restart fails restartBudgetNotExhausted even though its last round was long enough.
{
  const twoMobs = await runScenario(() => 2);
  expect(twoMobs.checks.mobsInstantiated === false, "2 mobs per round: mobsInstantiated must be false (no round reached 4 mobs)");
  expect(twoMobs.checks.restartBudgetNotExhausted === false, "2 mobs per round: restartBudgetNotExhausted must be false");
  const lateRecovery = await runScenario((round) => (round <= 3 ? 1 : Infinity));
  expect(lateRecovery.checks.mobsInstantiated === true, "1,1,1 then healthy: the fourth round reached 4 mobs, mobsInstantiated must be true");
  expect(lateRecovery.checks.restartBudgetNotExhausted === false, "1,1,1 then healthy: restartBudgetNotExhausted must be false");
  expect(
    lateRecovery.boundaryErrors.some((message) => message.includes("restart budget exhausted") && message.includes("1, 1, 1 mob(s)")),
    `1,1,1 then healthy: boundaryErrors must name the per-round counts: ${JSON.stringify(lateRecovery.boundaryErrors)}`,
  );
}

if (failures.length > 0) {
  console.error(`dodge_restart_selftest: ${failures.length} failure(s)`);
  for (const failure of failures) console.error(`  FAIL ${failure}`);
  process.exit(1);
}
console.log("dodge_restart_selftest: OK (healthy and one early death green; 1, 2, 3 mobs per round and 1,1,1-then-healthy red)");

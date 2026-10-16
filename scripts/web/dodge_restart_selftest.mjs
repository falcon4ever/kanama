#!/usr/bin/env node
// Self-test of the dodge driver's round logic: early hits, the forced restart, and the per-round
// handle retention measurement.
//
// Dodge's player stands still and a mob can hit it before the round's 4th mob; Main.game_over then
// stops MobTimer. The driver RESUMES such a round (Main._on_StartTimer_timeout starts MobTimer again;
// the hit player stays hidden and unhittable until the next new_game), so every round reaches 4 mobs
// whatever the RNG does. Retention is judged per round, at the trough between rounds (game_over, then
// Start: new_game queue_frees the mobs), against that round's own baseline, with a collection forced
// before every poll: a mob's RefCounted results (its SpriteFrames) go only when the page collects the
// dropped wrapper. The scenarios below pin all of it.
//
// This drives the real runDodge() against a fake bridge (no browser): `evaluate` runs the driver's own
// snapshot/call source inside a vm context whose KanamaWebBridge is a small game simulation. Time is
// virtual (setTimeout/Date.now are replaced for the duration of the run), so the cases take milliseconds.
import fs from "node:fs";
import os from "node:os";
import path from "node:path";
import vm from "node:vm";
import { runDodge } from "./drivers/demos/dodge.mjs";

const MAIN_HANDLE = 1;
const SMOKE_HANDLE = 2;
const HUD_HANDLE = 3;
const MAIN_IDS = { game_over: 1, new_game: 2, _on_MobTimer_timeout: 3, _on_ScoreTimer_timeout: 4, _on_StartTimer_timeout: 5 };
const START_PRESSED_ID = 4;
const TEARDOWN_ID = 9;
const SCENE_NODES = 14; // handles the scene holds before any mob (the driver's baselines absorb them)
const START_TIMER_TICKS = 4; // StartTimer, in 150 ms polls
const SPAWN_EVERY_TICKS = 3; // MobTimer
const MOB_LIFETIME_TICKS = 5; // a mob queue_frees itself this many polls after it spawned
const HUD_COROUTINE_TICKS = 6; // game over -> MessageTimer -> 1s delay -> Start button shown again

/**
 * options:
 *   hitAt(round)      mobs spawned in that round when the player is hit (0: before the first mob;
 *                     Infinity: never). The hit stops MobTimer (Main.game_over) and the player stays
 *                     hidden and unhittable until the next new_game.
 *   resumeIgnored     Main._on_StartTimer_timeout does not start MobTimer (spawning never resumes).
 *   leakPerMob(round) Node handles each mob of that round leaves behind when it is freed.
 */
function makeFakeBridge({ hitAt = () => Infinity, resumeIgnored = false, leakPerMob = () => 0 }) {
  const slots = [];
  const mint = (kind, extra = {}) => {
    const slot = { kind, live: true, refCounted: false, reachable: true, ...extra };
    slots.push(slot);
    return slot;
  };
  for (let index = 0; index < SCENE_NODES; index += 1) mint("Node");
  mint("Resource", { refCounted: true });
  const state = {
    round: 0,
    roundMobs: 0,
    ticksSinceSpawn: 0,
    mobTimer: false,
    startTimer: null,
    playerHittable: false,
    mobs: [], // { age, node, round }
    freeQueue: [], // mobs new_game queue_freed: released at the end of the frame (the next step)
    hudCoroutine: null, // ticks until the HUD's game-over coroutine shows the Start button again
    startButton: null,
    collectRequested: false,
    gameOvers: 0,
    torn: false,
  };
  const exercised = { "dodge.Main": {} };
  const gameOver = () => {
    state.mobTimer = false;
    state.gameOvers += 1;
    exercised["dodge.Main"]["method#" + MAIN_IDS.game_over] = state.gameOvers;
    state.hudCoroutine = HUD_COROUTINE_TICKS;
  };
  const lookUpStartButton = () => {
    if (!state.startButton) state.startButton = mint("Node");
  };
  const newGame = () => {
    state.round += 1;
    state.roundMobs = 0;
    state.ticksSinceSpawn = 0;
    state.startTimer = START_TIMER_TICKS; // MobTimer keeps whatever state it had, as in Main.newGame
    state.playerHittable = true;
    state.freeQueue.push(...state.mobs);
    state.mobs = [];
  };
  const freeMob = (mob) => {
    mob.node.live = false;
    for (let index = 0; index < leakPerMob(mob.round); index += 1) mint("Node");
  };
  const bridge = {
    mode: "dodge",
    results: { protocolVersion: 38 },
    dodgeMainHandle: MAIN_HANDLE,
    smokeHandle: SMOKE_HANDLE,
    readyCount: 4,
    match3ReadyByClass: { "dodge.Main": 1, "dodge.Player": 1, "dodge.HUD": 1, "dodge.Mob": 0 },
    match3PackedSceneInstantiations: 0,
    match3AddChildCommands: 0,
    exercisedMembers: exercised,
    dodgeMethodId: (name) => {
      if (!(name in MAIN_IDS)) throw new Error(`Unknown dodge method=${name}`);
      return MAIN_IDS[name];
    },
    get liveBrowserHandleCount() {
      return slots.filter((slot) => slot.live).length;
    },
    maxLiveBrowserHandles: 0,
    scriptNameByHandle: { [MAIN_HANDLE]: "dodge.Main", [SMOKE_HANDLE]: "dodge.SmokeQuit", [HUD_HANDLE]: "dodge.HUD" },
    browserHandleSlots: slots,
    kotlinToGodotCalls: 10,
    callbackErrors: 0,
    processCalls: 0,
    simSeconds: 0,
    api: {
      kanamaWebIsLive: (handle) => (handle <= HUD_HANDLE && !state.torn ? 1 : 0),
      kanamaWebPendingSignalCallbackCount: () => 0,
      kanamaWebPendingCoroutineCount: () => 0,
      kanamaWebRegisteredCoroutineJobCount: () => 0,
    },
    callNoArgs(handle, id) {
      if (handle === MAIN_HANDLE && id === MAIN_IDS.game_over) gameOver();
      else if (handle === MAIN_HANDLE && id === MAIN_IDS.new_game) newGame();
      else if (handle === MAIN_HANDLE && id === MAIN_IDS._on_StartTimer_timeout) {
        if (!resumeIgnored) state.mobTimer = true;
      } else if (handle === HUD_HANDLE && id === START_PRESSED_ID) {
        lookUpStartButton(); // startButton.hide(), then start_game -> Main.new_game
        newGame();
      } else if (handle === SMOKE_HANDLE && id === TEARDOWN_ID) {
        state.torn = true;
        for (const slot of slots) slot.live = false;
      } else {
        throw new Error(`fake dodge bridge: unexpected call handle=${handle} id=${id}`);
      }
    },
  };
  // One simulated 150 ms step, taken whenever the driver polls a snapshot.
  const step = () => {
    bridge.processCalls += 1;
    bridge.simSeconds += 0.15;
    bridge.kotlinToGodotCalls += 3;
    for (const mob of state.freeQueue) freeMob(mob);
    state.freeQueue = [];
    // A requested collection releases every RefCounted handle no wrapper can reach any more. The
    // page never collects on its own here (Firefox on a quiet page, CI's Linux runner).
    if (state.collectRequested) {
      for (const slot of slots) if (slot.live && slot.refCounted && !slot.reachable) slot.live = false;
      state.collectRequested = false;
    }
    if (state.torn) return;
    for (const mob of state.mobs) mob.age += 1;
    for (const mob of state.mobs.filter((candidate) => candidate.age >= MOB_LIFETIME_TICKS)) freeMob(mob);
    state.mobs = state.mobs.filter((mob) => mob.age < MOB_LIFETIME_TICKS);
    if (state.hudCoroutine !== null) {
      state.hudCoroutine -= 1;
      if (state.hudCoroutine === 3) mint("SceneTreeTimer", { refCounted: true, reachable: false }); // delaySeconds(1.0)
      if (state.hudCoroutine <= 0) {
        lookUpStartButton(); // startButton.show()
        state.hudCoroutine = null;
      }
    }
    if (state.startTimer !== null) {
      state.startTimer -= 1;
      if (state.startTimer <= 0) {
        state.startTimer = null;
        state.mobTimer = true; // Main._on_StartTimer_timeout
      }
    }
    if (state.playerHittable && state.round > 0 && hitAt(state.round) === 0 && state.roundMobs === 0) {
      state.playerHittable = false;
      gameOver();
    }
    if (!state.mobTimer) return;
    state.ticksSinceSpawn += 1;
    if (state.ticksSinceSpawn < SPAWN_EVERY_TICKS) return;
    state.ticksSinceSpawn = 0;
    state.roundMobs += 1;
    // Mob.ready reads its AnimatedSprite2D's SpriteFrames and drops the wrapper: a RefCounted
    // handle only a collection releases.
    mint("SpriteFrames", { refCounted: true, reachable: false });
    state.mobs.push({ age: 0, node: mint("Node"), round: state.round });
    bridge.match3PackedSceneInstantiations += 1;
    bridge.match3AddChildCommands += 1;
    bridge.maxLiveBrowserHandles = Math.max(bridge.maxLiveBrowserHandles, bridge.liveBrowserHandleCount);
    if (state.playerHittable && state.roundMobs >= hitAt(state.round)) {
      state.playerHittable = false; // Player._on_body_entered: hide, hit, disable the collision shape
      gameOver();
    }
  };
  bridge.maxLiveBrowserHandles = bridge.liveBrowserHandleCount;
  return { bridge, step, requestCollection: () => (state.collectRequested = true) };
}

async function runScenario(options, { forceRestart = false } = {}) {
  const exportDir = fs.mkdtempSync(path.join(os.tmpdir(), "dodge-restart-selftest-"));
  fs.mkdirSync(path.join(exportDir, "kanama-web"));
  fs.writeFileSync(
    path.join(exportDir, "kanama-web", "KanamaWebProtocol.generated.json"),
    JSON.stringify({
      scripts: [
        { className: "dodge.SmokeQuit", methods: [{ name: "smoke_teardown", id: TEARDOWN_ID }] },
        { className: "dodge.HUD", methods: [{ name: "_on_StartButton_pressed", id: START_PRESSED_ID }] },
      ],
    }),
  );
  const { bridge, step, requestCollection } = makeFakeBridge(options);
  const context = {
    KanamaWebBridge: bridge,
    gc: requestCollection, // the driver's `collecting` calls gc() where the page exposes it (Chrome)
    document: { querySelector: () => ({ clientWidth: 480, clientHeight: 720 }) },
  };
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
  const realForce = process.env.KANAMA_WEB_DODGE_FORCE_RESTART;
  let virtualNow = 1_000_000;
  globalThis.setTimeout = (callback, ms = 0) => realSetTimeout(() => { virtualNow += ms; callback(); }, 0);
  Date.now = () => virtualNow;
  if (forceRestart) process.env.KANAMA_WEB_DODGE_FORCE_RESTART = "1";
  else delete process.env.KANAMA_WEB_DODGE_FORCE_RESTART;
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
    if (realForce === undefined) delete process.env.KANAMA_WEB_DODGE_FORCE_RESTART;
    else process.env.KANAMA_WEB_DODGE_FORCE_RESTART = realForce;
    fs.rmSync(exportDir, { recursive: true, force: true });
  }
}

const failures = [];
const expect = (condition, message) => {
  if (!condition) failures.push(message);
};
const redChecks = (result) => Object.entries(result.checks).filter(([, ok]) => ok !== true).map(([name]) => name);

// [label, fake options, run options, expected: { red: [checks] (empty: all green), restarts, resumes }]
const never = () => Infinity;
const scenarios = [
  ["healthy", {}, {}, { red: [], restarts: 0, resumes: 0 }],
  ["one early hit (1 mob)", { hitAt: (round) => (round === 1 ? 1 : Infinity) }, {}, { red: [], restarts: 0, resumes: 1 }],
  ["one early hit (3 mobs)", { hitAt: (round) => (round === 1 ? 3 : Infinity) }, {}, { red: [], restarts: 0, resumes: 1 }],
  ["hit after 4 mobs", { hitAt: () => 4 }, {}, { red: [], restarts: 0, resumes: 0 }],
  ["early hit, spawning never resumes", { hitAt: () => 2, resumeIgnored: true }, {}, { red: ["mobsInstantiated"], restarts: 0, resumes: 1 }],
  ["hit before the first mob", { hitAt: () => 0 }, {}, { red: ["noHitBeforeFirstMob"], restarts: 0, resumes: 0 }],
  ["forced restart", { hitAt: never }, { forceRestart: true }, { red: [], restarts: 1, resumes: 0 }],
  ["forced restart, hit before the forced point", { hitAt: (round) => (round === 1 ? 1 : Infinity) }, { forceRestart: true }, { red: [], restarts: 1, resumes: 0 }],
  [
    "forced restart, then an early hit and spawning never resumes",
    { hitAt: (round) => (round === 2 ? 2 : Infinity), resumeIgnored: true },
    { forceRestart: true },
    { red: ["mobsInstantiated"], restarts: 1, resumes: 1 },
  ],
  ["one handle leaked per mob", { leakPerMob: () => 1 }, {}, { red: ["noPerMobHandleRetention"], restarts: 0, resumes: 0 }],
  [
    "one handle leaked per mob of the restarted round",
    { leakPerMob: (round) => (round === 1 ? 1 : 0) },
    { forceRestart: true },
    { red: ["noPerMobHandleRetention"], restarts: 1, resumes: 0 },
  ],
];

for (const [label, options, runOptions, expected] of scenarios) {
  const result = await runScenario(options, runOptions);
  const red = redChecks(result);
  const c = result.crossings;
  expect(c.roundRestarts === expected.restarts, `${label}: roundRestarts is ${c.roundRestarts}, expected ${expected.restarts}`);
  expect(c.roundResumes === expected.resumes, `${label}: roundResumes is ${c.roundResumes}, expected ${expected.resumes}`);
  if (expected.red.length === 0) {
    expect(red.length === 0, `${label}: checks red: ${red.join(", ")}`);
    expect(result.boundaryErrors.length === 0, `${label}: boundaryErrors ${JSON.stringify(result.boundaryErrors)}`);
    expect(c.bestRoundMobInstantiations >= 4, `${label}: bestRoundMobInstantiations is ${c.bestRoundMobInstantiations}, expected 4+`);
    expect(c.worstHandleRetentionPerMobX100 === 0, `${label}: worstHandleRetentionPerMobX100 is ${c.worstHandleRetentionPerMobX100}, expected 0`);
  } else {
    expect(red.length > 0, `${label}: expected a red check, but every check is green (the run would pass)`);
    for (const name of expected.red) expect(red.includes(name), `${label}: ${name} must be false (red: [${red.join(", ")}])`);
  }
  console.log(
    `  ${label}: restarts=${c.roundRestarts} resumes=${c.roundResumes} hits=${c.playerHits} bestRound=${c.bestRoundMobInstantiations} ` +
      `cumulative=${c.mobInstantiations} retentionX100=${c.worstHandleRetentionPerMobX100} red=[${red.join(",")}]`,
  );
}

// The instant-kill check names itself in the boundary errors.
{
  const instant = await runScenario({ hitAt: () => 0 });
  expect(
    instant.boundaryErrors.some((message) => message.includes("hit before round 1 spawned a mob")),
    `hit before the first mob: boundaryErrors must name the instant kill: ${JSON.stringify(instant.boundaryErrors)}`,
  );
}

if (failures.length > 0) {
  console.error(`dodge_restart_selftest: ${failures.length} failure(s)`);
  for (const failure of failures) console.error(`  FAIL ${failure}`);
  process.exit(1);
}
console.log(
  "dodge_restart_selftest: OK (early hits resume to 4 mobs; a forced restart is measured per round; " +
    "no resume, an instant kill and a per-mob leak in any round are red)",
);

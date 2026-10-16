#!/usr/bin/env node
// Self-test of the dodge driver's round logic: early hits, the restart every run makes, the hit
// checks, and the per-round handle retention measurement.
//
// Dodge's player stands still and a mob can hit it before the round's 4th mob; Main.game_over then
// stops MobTimer. The driver RESUMES such a round (Main._on_StartTimer_timeout starts MobTimer again;
// the hit player stays hidden and unhittable until the next new_game), so every round reaches 4 mobs
// whatever the RNG does. A second hit in a round, a hit before a round's first mob and a hit sooner
// than a mob can fly to the player are red. Every run ends round 1 after 4 mobs with a restart and
// judges both rounds' retention, at the trough between rounds (game_over, then Start: new_game
// queue_frees the mobs), against that round's own baseline, with a collection forced before every
// poll: a mob's RefCounted results (its SpriteFrames) go only when the page collects the dropped
// wrapper. Main's method ids come from the export manifest, by name. The scenarios below pin all of it.
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
// Not the order of Main.kt's functions: the driver must resolve them from the manifest by name.
const MAIN_IDS = { game_over: 21, new_game: 22, _on_MobTimer_timeout: 23, _on_ScoreTimer_timeout: 24, _on_StartTimer_timeout: 25 };
const START_PRESSED_ID = 4;
const TEARDOWN_ID = 9;
const SCENE_NODES = 14; // handles the scene holds before any mob (the driver's baselines absorb them)
const START_TIMER_TICKS = 4; // StartTimer, in 150 ms polls
const SPAWN_EVERY_TICKS = 3; // MobTimer
const MOB_LIFETIME_TICKS = 8; // a mob queue_frees itself this many polls after it spawned
const HIT_DELAY_TICKS = 5; // a hitting mob reaches the player this many polls (0.75 s) after it spawned
const HUD_COROUTINE_TICKS = 6; // game over -> MessageTimer -> 1s delay -> Start button shown again

/**
 * options:
 *   hitAt(round)      the mob of that round (1-based) that hits the player, hitDelayTicks after it
 *                     spawned (0: the player is hit before the first mob; Infinity: never). The hit
 *                     stops MobTimer (Main.game_over) and the player stays hidden and unhittable
 *                     until the next new_game.
 *   hitDelayTicks     polls from that mob's spawn to the hit (default HIT_DELAY_TICKS; 0: on spawn).
 *   hitDisables       false: Player._on_body_entered no longer disables the player, and every later
 *                     mob of the round hits it too.
 *   resumeIgnored     Main._on_StartTimer_timeout does not start MobTimer (spawning never resumes).
 *   leakPerMob(round) Node handles each mob of that round leaves behind when it is freed.
 *   leakPerRound      Node handles every new_game mints and keeps (once per round, not per mob).
 *   hitAtRestart      the player is still hittable when Start is pressed for round 2, and a mob's
 *                     overlap from the old round's last physics step is reported in the first frame
 *                     after the press, before that frame's _process (Godot's flush_queries order).
 */
function makeFakeBridge({
  hitAt = () => Infinity,
  hitDelayTicks = HIT_DELAY_TICKS,
  hitDisables = true,
  resumeIgnored = false,
  leakPerMob = () => 0,
  leakPerRound = 0,
  hitAtRestart = false,
}) {
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
    mobs: [], // { age, node, round, hitIn }
    freeQueue: [], // mobs new_game queue_freed: released at the end of the frame (the next step)
    hudCoroutine: null, // ticks until the HUD's game-over coroutine shows the Start button again
    startButton: null,
    collectRequested: false,
    gameOvers: 0,
    pendingHit: false,
    torn: false,
  };
  const exercised = { "dodge.Main": {} };
  // Main.game_over, dispatched through the bridge as the real proxy does (the driver's probe wraps
  // recordExercisedDispatch, like immediatePackedSceneInstantiate for a spawn).
  const gameOver = () => {
    bridge.recordExercisedDispatch(MAIN_HANDLE, "registered_function", "method#" + MAIN_IDS.game_over);
    state.mobTimer = false;
    state.gameOvers += 1;
    state.hudCoroutine = HUD_COROUTINE_TICKS;
  };
  const hitPlayer = () => {
    if (hitDisables) state.playerHittable = false; // Player._on_body_entered: hide, hit, disable the collision shape
    gameOver();
  };
  const lookUpStartButton = () => {
    if (!state.startButton) state.startButton = mint("Node");
  };
  const newGame = () => {
    if (hitAtRestart && state.round === 1 && state.playerHittable) state.pendingHit = true;
    state.round += 1;
    state.roundMobs = 0;
    state.ticksSinceSpawn = 0;
    state.startTimer = START_TIMER_TICKS; // MobTimer keeps whatever state it had, as in Main.newGame
    state.playerHittable = true;
    state.freeQueue.push(...state.mobs);
    state.mobs = [];
    for (let index = 0; index < leakPerRound; index += 1) mint("Node");
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
    recordExercisedDispatch(owner, callback, member) {
      const bucket = (exercised[this.scriptNameByHandle[owner]] ??= {});
      bucket[member] = (bucket[member] ?? 0) + 1;
    },
    immediatePackedSceneInstantiate() {
      this.match3PackedSceneInstantiations += 1;
      return 100 + this.match3PackedSceneInstantiations;
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
    if (state.pendingHit) {
      state.pendingHit = false;
      hitPlayer(); // flush_queries: before this frame's _process advances the game time
    }
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
    for (const mob of state.mobs) {
      if (mob.hitIn !== null && mob.age >= mob.hitIn && state.playerHittable) hitPlayer();
      if (mob.hitIn !== null && mob.age >= mob.hitIn) mob.hitIn = null;
    }
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
    if (state.playerHittable && state.round > 0 && hitAt(state.round) === 0 && state.roundMobs === 0) hitPlayer();
    if (!state.mobTimer) return;
    state.ticksSinceSpawn += 1;
    if (state.ticksSinceSpawn < SPAWN_EVERY_TICKS) return;
    state.ticksSinceSpawn = 0;
    state.roundMobs += 1;
    // Mob.ready reads its AnimatedSprite2D's SpriteFrames and drops the wrapper: a RefCounted
    // handle only a collection releases.
    mint("SpriteFrames", { refCounted: true, reachable: false });
    const hits = state.roundMobs === hitAt(state.round) || (!hitDisables && state.roundMobs > hitAt(state.round));
    const mob = { age: 0, node: mint("Node"), round: state.round, hitIn: hits ? hitDelayTicks : null };
    state.mobs.push(mob);
    bridge.immediatePackedSceneInstantiate(); // Main._on_MobTimer_timeout: mob_scene.instantiate()
    bridge.match3AddChildCommands += 1;
    bridge.maxLiveBrowserHandles = Math.max(bridge.maxLiveBrowserHandles, bridge.liveBrowserHandleCount);
    if (mob.hitIn === 0 && state.playerHittable) hitPlayer();
    if (mob.hitIn === 0) mob.hitIn = null;
  };
  bridge.maxLiveBrowserHandles = bridge.liveBrowserHandleCount;
  return { bridge, step, requestCollection: () => (state.collectRequested = true) };
}

async function runScenario(options, { manifestMain = MAIN_IDS } = {}) {
  const exportDir = fs.mkdtempSync(path.join(os.tmpdir(), "dodge-restart-selftest-"));
  fs.mkdirSync(path.join(exportDir, "kanama-web"));
  fs.writeFileSync(
    path.join(exportDir, "kanama-web", "KanamaWebProtocol.generated.json"),
    JSON.stringify({
      scripts: [
        { className: "dodge.SmokeQuit", methods: [{ name: "smoke_teardown", id: TEARDOWN_ID }] },
        { className: "dodge.HUD", methods: [{ name: "_on_StartButton_pressed", id: START_PRESSED_ID }] },
        { className: "dodge.Main", methods: Object.entries(manifestMain).map(([name, id]) => ({ name, id })) },
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

// [label, fake options, expected: { red: [checks] (empty: all green), restarts (default 1: round 1 ends
// after 4 mobs), resumes, hits, retentionX100 (green; default 0) }]
const round1 = (value) => (round) => (round === 1 ? value : Infinity);
const round2 = (value) => (round) => (round === 2 ? value : Infinity);
const scenarios = [
  ["healthy", {}, { red: [], resumes: 0, hits: 0 }],
  ["early hit in round 1 (1st mob)", { hitAt: round1(1) }, { red: [], resumes: 1, hits: 1 }],
  ["early hit in round 2 (2nd mob)", { hitAt: round2(2) }, { red: [], resumes: 1, hits: 1 }],
  ["an early hit in both rounds", { hitAt: () => 1 }, { red: [], resumes: 2, hits: 2 }],
  ["early hit, spawning never resumes", { hitAt: round1(1), resumeIgnored: true }, { red: ["mobsInstantiated"], restarts: 0, resumes: 1, hits: 1 }],
  ["round 2 hit early, spawning never resumes", { hitAt: round2(1), resumeIgnored: true }, { red: ["mobsInstantiated"], resumes: 1, hits: 1 }],
  ["hit before round 1's first mob", { hitAt: round1(0) }, { red: ["noHitBeforeFirstMob"], resumes: 0, hits: 1 }],
  ["hit before round 2's first mob", { hitAt: round2(0) }, { red: ["noHitBeforeFirstMob"], resumes: 0, hits: 1 }],
  ["the hit does not disable the player", { hitAt: round1(1), hitDisables: false }, { red: ["atMostOneHitPerRound"] }],
  ["a mob hits the player as it spawns", { hitAt: round1(1), hitDelayTicks: 0 }, { red: ["noEarlyHit"] }],
  ["a mob hits the player 0.15 s after it spawned", { hitAt: round2(1), hitDelayTicks: 1 }, { red: ["noEarlyHit"] }],
  ["one handle leaked per mob", { leakPerMob: () => 1 }, { red: ["noPerMobHandleRetention"], resumes: 0, hits: 0 }],
  ["one handle leaked per mob of round 1 (the restarted round)", { leakPerMob: (round) => (round === 1 ? 1 : 0) }, { red: ["noPerMobHandleRetention"], resumes: 0, hits: 0 }],
  ["one handle leaked per mob of round 2 only", { leakPerMob: (round) => (round === 2 ? 1 : 0) }, { red: ["noPerMobHandleRetention"], resumes: 0, hits: 0 }],
  ["one handle kept per round, not per mob", { leakPerRound: 1 }, { red: [], resumes: 0, hits: 0, retentionX100: 25 }],
  ["round 1's hit reported in the first frame after the restart", { hitAtRestart: true }, { red: [], resumes: 0, hits: 1 }],
];

for (const [label, options, expected] of scenarios) {
  const result = await runScenario(options);
  const red = redChecks(result);
  const c = result.crossings;
  const restarts = expected.restarts ?? 1;
  expect(c.roundRestarts === restarts, `${label}: roundRestarts is ${c.roundRestarts}, expected ${restarts}`);
  if (expected.resumes !== undefined) expect(c.roundResumes === expected.resumes, `${label}: roundResumes is ${c.roundResumes}, expected ${expected.resumes}`);
  if (expected.hits !== undefined) expect(c.playerHits === expected.hits, `${label}: playerHits is ${c.playerHits}, expected ${expected.hits}`);
  if (expected.red.length === 0) {
    const retentionX100 = expected.retentionX100 ?? 0;
    expect(red.length === 0, `${label}: checks red: ${red.join(", ")}`);
    expect(result.boundaryErrors.length === 0, `${label}: boundaryErrors ${JSON.stringify(result.boundaryErrors)}`);
    expect(c.bestRoundMobInstantiations >= 4, `${label}: bestRoundMobInstantiations is ${c.bestRoundMobInstantiations}, expected 4+`);
    expect(c.worstHandleRetentionPerMobX100 === retentionX100, `${label}: worstHandleRetentionPerMobX100 is ${c.worstHandleRetentionPerMobX100}, expected ${retentionX100}`);
  } else {
    expect(red.length > 0, `${label}: expected a red check, but every check is green (the run would pass)`);
    for (const name of expected.red) expect(red.includes(name), `${label}: ${name} must be false (red: [${red.join(", ")}])`);
  }
  console.log(
    `  ${label}: restarts=${c.roundRestarts} resumes=${c.roundResumes} hits=${c.playerHits} bestRound=${c.bestRoundMobInstantiations} ` +
      `cumulative=${c.mobInstantiations} retentionX100=${c.worstHandleRetentionPerMobX100} red=[${red.join(",")}]`,
  );
}

// Each hit check names itself in the boundary errors.
for (const [options, text] of [
  [{ hitAt: round2(0) }, "hit before a round spawned its first mob"],
  [{ hitAt: round1(1), hitDisables: false }, "hit more than once in a round"],
  [{ hitAt: round1(1), hitDelayTicks: 0 }, "after the round's first spawn"],
]) {
  const result = await runScenario(options);
  expect(
    result.boundaryErrors.some((message) => message.includes(text)),
    `boundaryErrors must say "${text}": ${JSON.stringify(result.boundaryErrors)}`,
  );
}

// Main's method ids are resolved by name from the export manifest: a manifest without one fails loudly.
{
  const withoutResume = { ...MAIN_IDS };
  delete withoutResume._on_StartTimer_timeout;
  let error = null;
  try {
    await runScenario({ hitAt: round1(1) }, { manifestMain: withoutResume });
  } catch (caught) {
    error = caught;
  }
  expect(
    String(error?.message ?? "").includes("dodge.Main._on_StartTimer_timeout is not in the export manifest"),
    `a manifest without dodge.Main._on_StartTimer_timeout must fail the run by name, got: ${error?.message ?? "no error"}`,
  );
}

if (failures.length > 0) {
  console.error(`dodge_restart_selftest: ${failures.length} failure(s)`);
  for (const failure of failures) console.error(`  FAIL ${failure}`);
  process.exit(1);
}
console.log(
  "dodge_restart_selftest: OK (early hits resume to 4 mobs; every run restarts once and judges both rounds; " +
    "no resume, a second hit, a hit before the first mob or too soon after it, and a per-mob leak in either round are red)",
);

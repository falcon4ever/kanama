// demos/thirdperson.mjs -- third-person controller play + teardown assertions for the
// Web smoke.
//
// The demo boots PAUSED behind its instructions page: the driver first dispatches
// SmokeQuit.smoke_resume to press through. COMBAT runs next (task 81 fix #3):
// SmokeQuit.smoke_combat ports the desktop smoke's two damage(Vector3, Vector3)
// calls -- the FPS damage bug's sibling shape -- onto the scene's own near bee and beetle,
// and the driver asserts both deaths (liveScriptsByClass decrement on the death
// coroutine's free, the fps.mjs pattern) while the exercised-member census gates the
// BeeBot.damage/BeetleBot.damage dispatches. Combat runs BEFORE movement on purpose: see
// the ORDER IS LOAD-BEARING note at the combat phase. Then the driver holds move_up via
// the trusted per-engine transport and PROVES movement by reading the player's global
// position through the bridge's immediate Vector3 channel (opcode 138); remaining enemy
// AI is self-evidencing while the world runs (far bots navigate and bob). The COIN
// ECONOMY phase runs last (task 64: see the note at the phase itself). smoke_teardown
// releases the scene caches and frees the root, draining every live handle to zero.
//
// Method ids are resolved from the export's own manifest by NAME, never hardcoded: ids are
// positional, so appending one @RegisterFunction above another renumbers it and a pinned id
// then dispatches a DIFFERENT method while still "working" (task 80 slice 4/6).
//
// Falsification (task 81 requires every gate provably able to fail): set
// KANAMA_WEB_T81_FALSIFY=no-combat to skip the smoke_combat dispatch and watch
// botsKilledByDamageCall go false (and the census gate fail naming both damage members),
// or KANAMA_WEB_T81_FALSIFY=no-coins to skip the coin spill and watch
// coinsSpilledByPlayerDamage/coinsCollectedByTweenCallbacks go false (and the census gate
// fail naming Player.damage, Player.collect_coin, Coin._follow and Coin._collect).

import { resolveMethodId } from "../envelope.mjs";

const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
const DEBUG = process.env.KANAMA_WEB_SMOKE_DEBUG === "1";
const FALSIFY = process.env.KANAMA_WEB_T81_FALSIFY ?? "";
const START = Date.now();
const trace = (msg) => {
  if (DEBUG) process.stderr.write(`[thirdperson ${((Date.now() - START) / 1000).toFixed(1)}s] ${msg}\n`);
};

async function snapshot(evaluate) {
  try {
    return await evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      if (!bridge) return null;
      const classCount = (suffix) => {
        const entry = Object.entries(bridge.match3ReadyByClass ?? {}).find(([n]) => n.endsWith(suffix));
        return entry?.[1] ?? 0;
      };
      const liveCount = (suffix) => {
        const entry = Object.entries(bridge.liveScriptsByClass ?? {}).find(([n]) => n.endsWith(suffix));
        return entry?.[1] ?? 0;
      };
      return {
        mode: bridge.mode,
        protocol: bridge.results?.protocolVersion ?? bridge.protocolVersion ?? 0,
        playerHandle: bridge.tpPlayerHandle,
        smokeQuitHandle: bridge.tpSmokeQuitHandle,
        demoPageHandle: bridge.tpDemoPageHandle,
        readyCount: bridge.readyCount,
        playerReady: classCount(".Player"),
        demoPageReady: classCount(".DemoPage"),
        cameraReady: classCount(".CameraController"),
        skinReady: classCount(".CharacterSkin"),
        beeReady: classCount(".BeeBot"),
        beetleReady: classCount(".BeetleBot"),
        boxReady: classCount(".Box"),
        // LIVE, not cumulative: decrements on the _exit_tree free, which is what lets
        // the combat gate see a damaged bot actually die (the fps.mjs enemyLive pattern).
        beeLive: liveCount(".BeeBot"),
        beetleLive: liveCount(".BeetleBot"),
        smokeQuitReady: classCount(".SmokeQuit"),
        physicsCalls: bridge.physicsProcessCalls ?? 0,
        // Task 84: real _process dispatches. This demo has no mode branch in the
        // bridge's frame dispatch, and until task 84 the fallthrough there was the
        // SYNTHETIC SPIKE BENCHMARK -- so this demo reported processTicks: 0 while
        // quietly appending a benchmark scalar mutation on every dispatch.
        // (No backticks in here: this whole function body is a template literal.)
        processCalls: bridge.processCalls ?? 0,
        appliedCommands: bridge.appliedCommands,
        liveHandles: bridge.liveBrowserHandleCount,
        maxLiveHandles: bridge.maxLiveBrowserHandles,
        crossings: bridge.kotlinToGodotCalls,
        callbackErrors: bridge.callbackErrors,
        physicsAfterProcess: bridge.physicsAfterProcessSameTick ?? 0,
        callbacks: bridge.api.kanamaWebPendingSignalCallbackCount(),
        pending: bridge.api.kanamaWebPendingCoroutineCount(),
        jobs: bridge.api.kanamaWebRegisteredCoroutineJobCount(),
        failure: globalThis.KanamaWebFailure?.stack ?? globalThis.KanamaWebFailure?.message ?? null,
      };
    })()`);
  } catch {
    return null; // page mid-navigation or torn down
  }
}

// The player's world position through the immediate no-args Vector3 channel
// (opcode 138 = Node3D.get_global_position on the player's script handle).
async function playerPosition(evaluate) {
  try {
    return await evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      const handle = bridge.tpPlayerHandle;
      const x = bridge.immediateNoArgsVector3X(138, handle);
      return { x, y: bridge.immediateNoArgsVector3Y(), z: bridge.immediateNoArgsVector3Z() };
    })()`);
  } catch {
    return null;
  }
}

// The coin economy's own counters (task 64).
//
// `spawned`/`live` come from the ready/live class registries; `follows`/`collects` come
// from the bridge's exercised-member census, which is keyed "method#<id>" IN THE PAGE --
// the id->name resolution happens later, in envelope.mjs, against the export manifest. So
// an in-run assertion has to ask by id, and the ids are resolved from that same manifest
// (match3.mjs reads the census the same way for its _input proof).
async function coinCensus(evaluate, followId, collectId) {
  try {
    return await evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      if (!bridge) return null;
      const forClass = (registry, suffix) =>
        Object.entries(registry ?? {}).find(([n]) => n.endsWith(suffix))?.[1];
      const bucket = forClass(bridge.exercisedMembers, ".Coin") ?? {};
      return {
        spawned: forClass(bridge.match3ReadyByClass, ".Coin") ?? 0,
        live: forClass(bridge.liveScriptsByClass, ".Coin") ?? 0,
        follows: bucket["method#${followId}"] ?? 0,
        collects: bucket["method#${collectId}"] ?? 0,
      };
    })()`);
  } catch {
    return null; // page mid-navigation or torn down
  }
}

// A single op-138 read can come back null under host load (the page briefly busy or
// mid-frame) -- observed as a one-shot flake in the kanama#170 validation. Retries are
// BOUNDED and the caller's null handling is unchanged, so a position that STAYS
// unreadable (dead bridge, freed player) still fails exactly as loudly as before.
async function playerPositionRetry(evaluate, attempts = 5) {
  for (let attempt = 1; ; attempt += 1) {
    const position = await playerPosition(evaluate);
    if (position || attempt >= attempts) return position;
    trace(`position read null (attempt ${attempt}/${attempts}); retrying`);
    await delay(250);
  }
}

async function observe(evaluate, seed, windowMs, deadline, predicate) {
  const peak = { ...seed };
  let last = null;
  const until = Math.min(deadline, Date.now() + windowMs);
  while (Date.now() < until) {
    const snap = await snapshot(evaluate);
    if (snap) {
      peak.physicsCalls = Math.max(peak.physicsCalls, snap.physicsCalls);
      peak.appliedCommands = Math.max(peak.appliedCommands, snap.appliedCommands);
      peak.maxLiveHandles = Math.max(peak.maxLiveHandles, snap.maxLiveHandles);
      peak.crossings = Math.max(peak.crossings, snap.crossings);
      peak.callbackErrors = Math.max(peak.callbackErrors, snap.callbackErrors);
      last = snap;
      trace(`physics=${snap.physicsCalls} applied=${snap.appliedCommands} live=${snap.liveHandles} errs=${snap.callbackErrors}`);
      if (predicate && predicate(snap, peak)) break;
    }
    await delay(150);
  }
  return { last, peak };
}

export async function runThirdperson({ url, evaluate, navigate, deadline, exportDir }) {
  // Every dispatched method id comes from the export's own manifest, by name. The old
  // "method#1/method#2 are pinned, new methods must APPEND" contract in the demo's
  // SmokeQuit doc comment was a landmine: appending is exactly what a contributor would
  // NOT do if they grouped a new method with a related one.
  const methodId = (className, name) => {
    const id = resolveMethodId(exportDir, className, name);
    if (!id) throw new Error(`thirdperson: ${className}.${name} not found in the export manifest`);
    return id;
  };
  const smokeResumeId = methodId("thirdperson.SmokeQuit", "smoke_resume");
  const smokeTeardownId = methodId("thirdperson.SmokeQuit", "smoke_teardown");
  const smokeCombatId = methodId("thirdperson.SmokeQuit", "smoke_combat");
  const collectCoinId = methodId("thirdperson.Player", "collect_coin");
  const playerDamageId = methodId("thirdperson.Player", "damage");
  const coinFollowId = methodId("thirdperson.Coin", "_follow");
  const coinCollectId = methodId("thirdperson.Coin", "_collect");

  // Engine-level action injection (ops 86/87) on the player's own handle, the
  // same path racing and tpsdemo already use. Player.kt reads movement through
  // Input.get_vector("move_left", "move_right", "move_up", "move_down"), and
  // action_press bakes strength 1.0, so pressing move_up is what holding W
  // expresses. This was the last driver still requiring a browser `keys`
  // transport, which SafariDriver does not provide -- and browser key synthesis
  // stalls headless Firefox anyway, so one injected path serves all engines.
  const action = (opcode) =>
    evaluate(
      `globalThis.KanamaWebBridge.immediateObjectQuery(${opcode}, globalThis.KanamaWebBridge.tpPlayerHandle, "move_up"); true`,
    );
  const keyDown = () => action(86);
  const keyUp = () => action(87);

  const startupStart = Date.now();
  trace("navigate");
  await navigate(`${url}?thirdperson=${Date.now()}`);

  // The 100 MB level takes longer to fetch/import than the small demos.
  const readyDeadline = Math.min(deadline, Date.now() + 120_000);
  let ready = null;
  while (Date.now() < readyDeadline) {
    const snap = await snapshot(evaluate);
    if (
      snap &&
      snap.mode === "thirdperson" &&
      snap.protocol > 0 &&
      snap.playerReady >= 1 &&
      snap.demoPageReady >= 1 &&
      snap.cameraReady >= 1 &&
      snap.skinReady >= 1 &&
      snap.beeReady >= 1 &&
      snap.beetleReady >= 1 &&
      snap.boxReady >= 1 &&
      snap.smokeQuitReady >= 1 &&
      snap.playerHandle > 0 &&
      snap.smokeQuitHandle > 0
    ) {
      ready = snap;
      break;
    }
    await delay(250);
  }
  if (!ready) throw new Error("Kotlin/Wasm third-person controller did not become ready");
  const startupDurationMs = Date.now() - startupStart;
  trace(`ready: readyCount=${ready.readyCount} playerHandle=${ready.playerHandle} protocol=${ready.protocol}`);

  // The demo boots paused behind its instructions page; physics must be still.
  const pausedBefore = (await snapshot(evaluate))?.physicsCalls ?? 0;

  // Press through the page (SmokeQuit.smoke_resume) and wait for physics.
  trace("smoke_resume");
  await evaluate(
    `globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.tpSmokeQuitHandle, ${smokeResumeId}); true`,
  );
  const resumed = await observe(
    evaluate,
    { ...seedFrom(ready), callbackErrors: 0 },
    8_000,
    deadline,
    (snap) => snap.physicsCalls >= pausedBefore + 30,
  );
  const baseline = resumed.last ?? ready;
  trace(`resumed: physics=${baseline.physicsCalls}`);

  // --- Combat FIRST: the desktop smoke's damage routines, ported (task 81 fix #3) ----
  //
  // SmokeQuit.smoke_combat calls damage(Vector3, Vector3) on the scene's own
  // near bee and beetle through Object.call -- the same boundary crossing Bullet/Grenade/
  // MeleeAttackArea combat uses, and the registered-method shape that killed the whole
  // combat system silently (the FPS damage bug's sibling; the desktop SmokeQuit tested
  // it, the Web path never did). The bots' death coroutines queue_free them ~2 SIMULATED
  // seconds later (smoke puff awaited, coin burst zeroed by the smoke method), so the
  // assertion is the EFFECT: each live script count drops below its pre-combat value.
  // The exercised-member census independently gates the BeeBot.damage/BeetleBot.damage
  // dispatches (scripts/web/required_members.json).
  //
  // ORDER IS LOAD-BEARING (MEASURED 2026-08-12): run the combat BEFORE the movement
  // hold. Moving first walks the player into GroundEnemy's detection range, the beetle
  // chases and lands an attack ~1 s after the hold ends, and its collider.call("damage")
  // into the PLAYER reaches the coin-spill -> CoinsContainer tween, whose NUMBER final
  // value is the known Web Tween.tween_property gap (task 57e backlog / task 81
  // confirmed break #3) -- a FATAL boundary failure that then stops every later
  // dispatch, including the combat calls this phase exists to make. Killing the near
  // bots first removes the only AI that can reach the driver's path, and the movement
  // baseline is re-snapshotted AFTER the deaths so the movement-window thresholds stay
  // measured against movement alone (a bound met partly by combat traffic proves
  // nothing).
  const beesBefore = baseline.beeLive ?? 0;
  const beetlesBefore = baseline.beetleLive ?? 0;
  let beesAfter = beesBefore;
  let beetlesAfter = beetlesBefore;
  let combatErrors = 0;
  if (FALSIFY !== "no-combat") {
    trace(`smoke_combat: bees=${beesBefore} beetles=${beetlesBefore}`);
    await evaluate(
      `globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.tpSmokeQuitHandle, ${smokeCombatId}); true`,
    );
    // Window MEASURED both ways (2026-08-12): healthy hosts break out in 1-3s, but the
    // first gated runner outing (main 31623511177) showed damage DISPATCHED (census
    // BeeBot.damage/BeetleBot.damage = 1) with neither live count dropping in 30s wall --
    // the death sequence (coroutine + smoke-puff await) appears paced by RENDERED frames,
    // and that runner's software-GL Chrome renders ~1-2 fps. 120s bounds the wait without
    // weakening the check (it still requires both real deaths); if this window ever proves
    // insufficient there, plan B is demos-side: the smoke_combat path shortens the
    // cosmetic death delay, and the pacing question gets the task-74 histogram recipe.
    const combatDeadline = Math.min(deadline, Date.now() + 120_000);
    while (Date.now() < combatDeadline) {
      const snap = await snapshot(evaluate);
      if (snap) {
        beesAfter = snap.beeLive;
        beetlesAfter = snap.beetleLive;
        combatErrors = Math.max(combatErrors, snap.callbackErrors);
        trace(`combat: bees=${snap.beeLive} beetles=${snap.beetleLive} errs=${snap.callbackErrors}`);
        if (snap.beeLive < beesBefore && snap.beetleLive < beetlesBefore) break;
      }
      await delay(200);
    }
    trace(`combat done: bees=${beesAfter}/${beesBefore} beetles=${beetlesAfter}/${beetlesBefore}`);
  } else {
    trace("FALSIFY=no-combat: smoke_combat not dispatched");
  }

  // Movement baseline AFTER the combat settles, so the movement-window thresholds
  // measure the movement window alone.
  const moveBaseline = (await snapshot(evaluate)) ?? baseline;
  const startPosition = await playerPositionRetry(evaluate);
  if (!startPosition) throw new Error("could not read the player's global position");
  trace(`move baseline: physics=${moveBaseline.physicsCalls} start=${JSON.stringify(startPosition)}`);

  // Hold move_up for a FIXED wall-time (no early exit: the tick rate races past any
  // physics-count predicate before the first observe sample, cutting the hold short —
  // played sessions with a fixed 3s hold consistently cover ~8 units).
  await keyDown();
  const gameplay = await observe(
    evaluate,
    { ...seedFrom(moveBaseline), callbackErrors: combatErrors },
    3_000,
    deadline,
    null,
  );
  await keyUp();
  const runPosition = await playerPositionRetry(evaluate);
  const peak = gameplay.peak;
  const atPeak = gameplay.last ?? moveBaseline;
  const displacement = runPosition
    ? Math.hypot(runPosition.x - startPosition.x, runPosition.z - startPosition.z)
    : 0;
  trace(`ran: displacement=${displacement.toFixed(2)} physics=${peak.physicsCalls} live=${atPeak.liveHandles}`);

  // --- COIN ECONOMY: spill and collect (task 64) --------------------------------------
  //
  // The demo's coin loop was DARK on Web, and not by accident: smoke_combat deliberately
  // zeroes each dying bot's coinsCount, because `CoinsContainer` slides its HUD with
  // `tween_property(self, "position:y", <number>, 0.5)` and the Web backend had no SCALAR
  // tween_property arm -- every other shape (Vector2/Vector3/Color) was there, so the hole
  // never showed up in a shape audit. The first coin to reach the player faulted the whole
  // boundary. Task 64 landed the scalar arm (bridge immediateTweenPropertyDouble); this
  // phase is what turns that arm from a fixture claim (web3d's scalar_tween_probe) into a
  // REAL gameplay path, and it is the driver step that unblocks Player.damage in
  // scripts/web/required_members.json.
  //
  // Choreography, entirely through registered methods that already exist in the demo:
  //   1. COIN_SEED x Player.collect_coin -- the purse; each one also runs the HUD's
  //      scalar tween, so a regressed arm faults here, before the spill.
  //   2. Player.damage(Vector3.ZERO, +Z) -- the boundary-crossing (VECTOR3, VECTOR3)
  //      shape a beetle attack lands in play. It calls loseCoins(), which instantiates
  //      min(coins, 5) real coins AT THE PLAYER and spawns them.
  //   3. The coins' own vacuum does the rest: each coin's PlayerDetectionArea (r=5) is
  //      centred on the player it spawned at, so body_entered fires on the next physics
  //      frame and the coin tweens home -- `_follow` per interpolation step and `_collect`
  //      at the end, both dispatched BY NAME through the engine's Tween, i.e. across the
  //      JS<->Kotlin boundary where the census can see them. `_collect` calls back into
  //      Player.collectCoin -> CoinsContainer.updateCoinsAmount -> the scalar tween again.
  //
  // A tiny +Z force keeps the knockback from moving the player anywhere; the damage, not
  // the ragdoll, is the point. The phase runs AFTER the movement window so it cannot lend
  // that window commands or displacement, and its own traffic is folded into a SEPARATE
  // peak (runPeak) for the same reason.
  const COIN_SEED = 5;
  const coinsBefore = await coinCensus(evaluate, coinFollowId, coinCollectId);
  let coinsAfter = coinsBefore;
  if (FALSIFY !== "no-coins") {
    trace(`coins: seeding the purse with ${COIN_SEED} x collect_coin`);
    for (let index = 0; index < COIN_SEED; index += 1) {
      await evaluate(
        `globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.tpPlayerHandle, ${collectCoinId}); true`,
      );
    }
    trace("coins: Player.damage");
    await evaluate(
      "globalThis.KanamaWebBridge.callDoubles(globalThis.KanamaWebBridge.tpPlayerHandle, " +
        `${playerDamageId}, 0, 0, 0, 0, 0, 1); true`,
    );
    // The collect tween is 0.5 SIMULATED seconds and the coins spawn already overlapping
    // the player, so a healthy host finishes in a second or two; the bound is generous for
    // the same reason the combat window is (a software-GL runner paces these coroutines by
    // rendered frames). It still requires every seeded coin to be collected -- waiting
    // longer cannot make a dead dispatch path pass.
    const coinDeadline = Math.min(deadline, Date.now() + 60_000);
    while (Date.now() < coinDeadline) {
      const snap = await coinCensus(evaluate, coinFollowId, coinCollectId);
      if (snap) {
        coinsAfter = snap;
        trace(
          `coins: spawned=${snap.spawned} live=${snap.live} follows=${snap.follows} collects=${snap.collects}`,
        );
        if (snap.collects >= COIN_SEED) break;
      }
      await delay(250);
    }
  } else {
    trace("FALSIFY=no-coins: the coin spill is not driven");
  }
  const coinsSpilled = (coinsAfter?.spawned ?? 0) - (coinsBefore?.spawned ?? 0);
  trace(`coins done: spilled=${coinsSpilled} collects=${coinsAfter?.collects ?? 0}`);

  // Fold the coin phase into a peak of its own: `peak` stays the MOVEMENT window's, so
  // gameplayCommandsApplied/physicsFramesAdvanced keep measuring movement alone, while the
  // handle and crossing REPORTS describe the whole run.
  const runPeak = (await observe(evaluate, peak, 1_000, deadline, null)).peak;

  // Full teardown: smoke_teardown releases the scene caches and frees the root; every
  // node exits the tree and releases its handles.
  trace("smoke_teardown");
  await evaluate(
    `globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.tpSmokeQuitHandle, ${smokeTeardownId}); true`,
  );
  const teardown = await observe(evaluate, runPeak, 12_000, deadline, (snap) => snap.liveHandles === 0);
  const settled = teardown.last ?? atPeak;
  trace(`teardown: live=${settled.liveHandles} callbacks=${settled.callbacks} errs=${settled.callbackErrors}`);

  const protocolVersion = ready.protocol;
  const checks = {
    modeThirdperson: ready.mode === "thirdperson",
    sceneScriptsReady:
      ready.playerReady >= 1 &&
      ready.demoPageReady >= 1 &&
      ready.cameraReady >= 1 &&
      ready.skinReady >= 1 &&
      ready.beeReady >= 1 &&
      ready.beetleReady >= 1 &&
      ready.boxReady >= 1,
    // The pause page held the world still until smoke_resume unpaused it.
    bootPausedThenResumed: ready.physicsCalls === 0 && baseline.physicsCalls > 0,
    // Synthetic move_up displaced the player through the camera-relative controller.
    playerMovedOnInput: displacement > 1.0,
    // Task 81 fix #3: smoke_combat's damage(Vector3, Vector3) calls killed a bee AND a
    // beetle -- both death coroutines freed their scripts. Before this port the shape
    // was driver-invoked zero times on Web while the desktop smoke tested it twice.
    botsKilledByDamageCall:
      beesBefore >= 1 &&
      beetlesBefore >= 1 &&
      beesAfter < beesBefore &&
      beetlesAfter < beetlesBefore,
    physicsFramesAdvanced: peak.physicsCalls >= moveBaseline.physicsCalls + 40,
    // MEASURED 2026-08-10 (macOS arm64, Chrome 151 headless, protocol 18, task 84): the
    // gameplay window applies ~680 commands over its baseline, so +80 keeps an 8x margin
    // on the REAL _process path. The threshold is unchanged and was NOT re-derived from
    // the pre-84 runs, which were inflated by the benchmark's synthetic per-dispatch
    // scalar mutation -- a lower bound met partly by scaffolding proves nothing, so it
    // had to be re-measured even though the number stayed put.
    gameplayCommandsApplied: peak.appliedCommands > moveBaseline.appliedCommands + 80,
    crossingsAdvanced: peak.crossings > moveBaseline.crossings,
    // Task 84: this demo runs the engine's REAL `_process`, not the transport benchmark.
    // MEASURED 793 (Chrome) / 7405 (Firefox) process dispatches; it was exactly 0 on both
    // before the fix. Asserted as > 0 rather than a magnitude because the count tracks the
    // host's rAF rate, and the thing worth failing on is the demo silently leaving the
    // real dispatch path again.
    realProcessPathDispatched: settled.processCalls > 0,
    // Task 64 coin economy. Player.damage -> loseCoins() instantiated one real coin per
    // seeded coin AT THE PLAYER: the cumulative Coin ready count rose by exactly the seed.
    // A spill that silently produced nothing (a broken DemoScenes cache, a Coin scene that
    // fails to instantiate) reads as 0 here, not as a quiet pass.
    coinsSpilledByPlayerDamage: coinsSpilled >= COIN_SEED,
    // ...and every spilled coin came HOME through the engine's Tween: `_follow` per
    // interpolation step, `_collect` once per coin, both dispatched by name across the
    // JS<->Kotlin boundary. `_collect` is what re-enters Player.collectCoin and therefore
    // the CoinsContainer scalar tween, so this is the check that keeps the task-64 arm
    // honest in real gameplay rather than in a fixture.
    coinsCollectedByTweenCallbacks:
      (coinsAfter?.follows ?? 0) >= COIN_SEED && (coinsAfter?.collects ?? 0) >= COIN_SEED,
    fullTeardownToZero: settled.liveHandles === 0,
    physicsOrderingDeterministic: (settled.physicsAfterProcess ?? 0) === 0,
    // runPeak covers the coin phase too: a scalar-tween regression faults the boundary
    // there, and this is the check that must go red for it.
    noCallbackFaults:
      peak.callbackErrors === 0 &&
      runPeak.callbackErrors === 0 &&
      settled.callbackErrors === 0 &&
      settled.failure === null,
  };

  const boundaryErrors = [];
  if (runPeak.callbackErrors !== 0) boundaryErrors.push(`callbackErrors=${runPeak.callbackErrors}`);
  if (settled.failure !== null) boundaryErrors.push(`failure: ${settled.failure}`);

  return {
    protocolVersion,
    startup: {
      loaded: ready.mode === "thirdperson",
      outcome: ready.mode === "thirdperson" ? "ready" : "failed",
      durationMs: startupDurationMs,
    },
    checks,
    handles: {
      liveAfterGameplay: runPeak.maxLiveHandles,
      liveAfterTeardown: settled.liveHandles,
      staleRejected: 0,
    },
    crossings: {
      kotlinToGodotCalls: runPeak.crossings,
      physicsProcessCalls: runPeak.physicsCalls,
      appliedCommands: runPeak.appliedCommands,
      processCalls: settled.processCalls,
      playerDisplacement: Number(displacement.toFixed(3)),
      beesBeforeCombat: beesBefore,
      beesAfterCombat: beesAfter,
      beetlesBeforeCombat: beetlesBefore,
      beetlesAfterCombat: beetlesAfter,
      coinsSpilled,
      coinFollowDispatches: coinsAfter?.follows ?? 0,
      coinCollectDispatches: coinsAfter?.collects ?? 0,
    },
    callbacks: {
      pendingSignalCallbacks: settled.callbacks,
    },
    connections: {
      afterGameplayLiveHandles: settled.liveHandles,
    },
    scheduler: {
      pendingCoroutines: settled.pending,
      registeredJobs: settled.jobs,
    },
    teardown: {
      outcome:
        checks.fullTeardownToZero && settled.callbackErrors === 0 && settled.failure === null
          ? "clean"
          : "incomplete",
      // Task 88: this was `settled.liveHandles <= peak.maxLiveHandles`, which is TRUE BY
      // CONSTRUCTION -- maxLiveHandles is a monotone high-water mark of liveHandles and
      // observe() merges the settled sample into peak before returning it. The field is
      // the contract's post-teardown invariant, so it must assert what match3/tpsdemo
      // assert: every owner registry actually drained.
      ownerRegistriesToBaseline:
        settled.liveHandles === 0 &&
        settled.callbacks === 0 &&
        settled.pending === 0 &&
        settled.jobs === 0,
    },
    boundaryErrors,
  };
}

function seedFrom(snap) {
  return {
    physicsCalls: snap.physicsCalls,
    appliedCommands: snap.appliedCommands,
    maxLiveHandles: snap.liveHandles,
    crossings: snap.crossings,
  };
}

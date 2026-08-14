// demos/thirdperson.mjs -- third-person controller play + teardown assertions for the
// Web smoke.
//
// The demo boots PAUSED behind its instructions page: the driver first dispatches
// SmokeQuit.smoke_resume (method#1) to press through. COMBAT runs next (task 81 fix #3):
// SmokeQuit.smoke_combat (method#3) ports the desktop smoke's two damage(Vector3, Vector3)
// calls -- the FPS damage bug's sibling shape -- onto the scene's own near bee and beetle,
// and the driver asserts both deaths (liveScriptsByClass decrement on the death
// coroutine's free, the fps.mjs pattern) while the exercised-member census gates the
// BeeBot.damage/BeetleBot.damage dispatches. Combat runs BEFORE movement on purpose: see
// the ORDER IS LOAD-BEARING note at the combat phase. Then the driver holds move_up via
// the trusted per-engine transport and PROVES movement by reading the player's global
// position through the bridge's immediate Vector3 channel (opcode 138); remaining enemy
// AI is self-evidencing while the world runs (far bots navigate and bob). smoke_teardown
// (method#2) releases the scene caches and frees the root, draining every live handle to
// zero.
//
// Falsification (task 81 requires every gate provably able to fail): set
// KANAMA_WEB_T81_FALSIFY=no-combat to skip the smoke_combat dispatch and watch
// botsKilledByDamageCall go false (and the census gate fail naming both damage members).

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

export async function runThirdperson({ url, evaluate, navigate, deadline }) {
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

  // Press through the page (SmokeQuit.smoke_resume, method#1) and wait for physics.
  trace("smoke_resume");
  await evaluate(
    "globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.tpSmokeQuitHandle, 1); true",
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
  // SmokeQuit.smoke_combat (method#3) calls damage(Vector3, Vector3) on the scene's own
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
      "globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.tpSmokeQuitHandle, 3); true",
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

  // Full teardown: smoke_teardown (method#2) releases the scene caches and frees the
  // root; every node exits the tree and releases its handles.
  trace("smoke_teardown");
  await evaluate(
    "globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.tpSmokeQuitHandle, 2); true",
  );
  const teardown = await observe(evaluate, peak, 12_000, deadline, (snap) => snap.liveHandles === 0);
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
    fullTeardownToZero: settled.liveHandles === 0,
    physicsOrderingDeterministic: (settled.physicsAfterProcess ?? 0) === 0,
    noCallbackFaults:
      peak.callbackErrors === 0 && settled.callbackErrors === 0 && settled.failure === null,
  };

  const boundaryErrors = [];
  if (peak.callbackErrors !== 0) boundaryErrors.push(`callbackErrors=${peak.callbackErrors}`);
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
      liveAfterGameplay: peak.maxLiveHandles,
      liveAfterTeardown: settled.liveHandles,
      staleRejected: 0,
    },
    crossings: {
      kotlinToGodotCalls: peak.crossings,
      physicsProcessCalls: peak.physicsCalls,
      appliedCommands: peak.appliedCommands,
      processCalls: settled.processCalls,
      playerDisplacement: Number(displacement.toFixed(3)),
      beesBeforeCombat: beesBefore,
      beesAfterCombat: beesAfter,
      beetlesBeforeCombat: beetlesBefore,
      beetlesAfterCombat: beetlesAfter,
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

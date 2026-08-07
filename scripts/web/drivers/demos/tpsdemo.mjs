// demos/tpsdemo.mjs -- tps-demo-kanama play + teardown assertions for the Web smoke.
//
// The demo boots into its menu; the driver presses Play through Main.smoke_start_game
// (method#1) and waits for the level to finish streaming in (four red robots, the player,
// its input synchronizer and camera-shake camera). It then holds move_forward at ENGINE
// level (ops 86/87 — browser key synthesis stalls headless Firefox) and PROVES movement by
// reading the player's global position through the immediate Vector3 channel (opcode 138).
// The robots' AI, the AnimationTree-driven locomotion, and the level's coroutine timers all
// run under that window. Main.smoke_teardown (method#2) releases the cached scenes and the
// settings ConfigFile, then frees the scene root, draining every live handle to zero.

const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
const DEBUG = process.env.KANAMA_WEB_SMOKE_DEBUG === "1";
const START = Date.now();
const trace = (msg) => {
  if (DEBUG) process.stderr.write(`[tpsdemo ${((Date.now() - START) / 1000).toFixed(1)}s] ${msg}\n`);
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
      return {
        mode: bridge.mode,
        protocol: bridge.results?.protocolVersion ?? bridge.protocolVersion ?? 0,
        mainHandle: bridge.tpsMainHandle,
        menuHandle: bridge.tpsMenuHandle,
        levelHandle: bridge.tpsLevelHandle,
        playerHandle: bridge.tpsPlayerHandle,
        readyCount: bridge.readyCount,
        mainReady: classCount(".Main"),
        menuReady: classCount(".Menu"),
        levelReady: classCount(".Level"),
        playerReady: classCount(".Player"),
        inputReady: classCount(".PlayerInputSynchronizer"),
        robotReady: classCount(".RedRobot"),
        partReady: classCount(".Part"),
        shakeReady: classCount(".CameraNoiseShakeEffect"),
        processCalls: bridge.processCalls ?? 0,
        physicsCalls: bridge.physicsProcessCalls ?? 0,
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
      const handle = bridge.tpsPlayerHandle;
      if (!handle) return null;
      const x = bridge.immediateNoArgsVector3X(138, handle);
      return { x, y: bridge.immediateNoArgsVector3Y(), z: bridge.immediateNoArgsVector3Z() };
    })()`);
  } catch {
    return null;
  }
}

function seedFrom(snap) {
  return {
    processCalls: snap.processCalls,
    physicsCalls: snap.physicsCalls,
    appliedCommands: snap.appliedCommands,
    maxLiveHandles: snap.maxLiveHandles,
    crossings: snap.crossings,
    callbackErrors: snap.callbackErrors,
  };
}

async function observe(evaluate, seed, windowMs, deadline, predicate) {
  const peak = { ...seed };
  let last = null;
  const until = Math.min(deadline, Date.now() + windowMs);
  while (Date.now() < until) {
    const snap = await snapshot(evaluate);
    if (snap) {
      peak.processCalls = Math.max(peak.processCalls, snap.processCalls);
      peak.physicsCalls = Math.max(peak.physicsCalls, snap.physicsCalls);
      peak.appliedCommands = Math.max(peak.appliedCommands, snap.appliedCommands);
      peak.maxLiveHandles = Math.max(peak.maxLiveHandles, snap.maxLiveHandles);
      peak.crossings = Math.max(peak.crossings, snap.crossings);
      peak.callbackErrors = Math.max(peak.callbackErrors, snap.callbackErrors);
      last = snap;
      trace(
        `process=${snap.processCalls} physics=${snap.physicsCalls} applied=${snap.appliedCommands} live=${snap.liveHandles} errs=${snap.callbackErrors}`,
      );
      if (predicate && predicate(snap, peak)) break;
    }
    await delay(150);
  }
  return { last, peak };
}

export async function runTpsdemo({ url, evaluate, navigate, deadline }) {
  // Engine-level action injection (ops 86/87) on the player's own handle: browser key
  // synthesis stalls the headless-Firefox main thread for the whole hold.
  const action = (opcode, name) =>
    evaluate(
      `globalThis.KanamaWebBridge.immediateObjectQuery(${opcode}, globalThis.KanamaWebBridge.tpsPlayerHandle, "${name}"); true`,
    );

  const startupStart = Date.now();
  trace("navigate");
  await navigate(`${url}?tpsdemo=${Date.now()}`);

  // Generous first-load window: this is the largest export in the corpus.
  const menuDeadline = Math.min(deadline, Date.now() + 180_000);
  let menu = null;
  while (Date.now() < menuDeadline) {
    const snap = await snapshot(evaluate);
    if (
      snap &&
      snap.mode === "tpsdemo" &&
      snap.protocol > 0 &&
      snap.mainReady >= 1 &&
      snap.menuReady >= 1 &&
      snap.mainHandle > 0
    ) {
      menu = snap;
      break;
    }
    await delay(250);
  }
  if (!menu) throw new Error("Kotlin/Wasm tps-demo menu did not become ready");
  const startupDurationMs = Date.now() - startupStart;
  trace(`menu ready: readyCount=${menu.readyCount} mainHandle=${menu.mainHandle}`);

  // Press Play: Main.smoke_start_game (method#1) drives the menu's own play path, which
  // loads the level and hands it to Main through the demo's replace_main_scene signal.
  trace("smoke_start_game");
  await evaluate(
    "globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.tpsMainHandle, 1); true",
  );

  const levelDeadline = Math.min(deadline, Date.now() + 180_000);
  let level = null;
  while (Date.now() < levelDeadline) {
    const snap = await snapshot(evaluate);
    if (
      snap &&
      snap.levelReady >= 1 &&
      snap.playerReady >= 1 &&
      snap.inputReady >= 1 &&
      snap.robotReady >= 1 &&
      snap.playerHandle > 0
    ) {
      level = snap;
      break;
    }
    if (snap?.failure) throw new Error(`tps-demo failed while loading the level: ${snap.failure}`);
    await delay(250);
  }
  if (!level) throw new Error("tps-demo level did not come up after pressing Play");
  trace(
    `level ready: robots=${level.robotReady} parts=${level.partReady} player=${level.playerHandle}`,
  );

  // Let the level settle before measuring: the first frames compile the robot laser and
  // particle shaders, which freezes the software-GL main thread for seconds.
  const settled = await observe(
    evaluate,
    { ...seedFrom(level), callbackErrors: 0 },
    20_000,
    deadline,
    (snap) => snap.physicsCalls >= level.physicsCalls + 120,
  );
  const baseline = settled.last ?? level;
  const startPosition = await playerPosition(evaluate);
  if (!startPosition) throw new Error("could not read the player's global position");
  trace(`settled: physics=${baseline.physicsCalls} start=${JSON.stringify(startPosition)}`);

  // Hold move_forward for a FIXED wall-time: the tick rate races past any physics-count
  // predicate before the first observe sample, which would cut the hold short.
  await action(86, "move_forward");
  const gameplay = await observe(
    evaluate,
    { ...seedFrom(baseline), callbackErrors: 0 },
    4_000,
    deadline,
    null,
  );
  await action(87, "move_forward");
  const runPosition = await playerPosition(evaluate);
  const peak = gameplay.peak;
  const atPeak = gameplay.last ?? baseline;
  const displacement = runPosition
    ? Math.hypot(runPosition.x - startPosition.x, runPosition.z - startPosition.z)
    : 0;
  trace(`ran: displacement=${displacement.toFixed(2)} physics=${peak.physicsCalls}`);

  // Full teardown: smoke_teardown (method#2) releases the cached scenes and the settings
  // ConfigFile, then frees the scene root; every node exits the tree and drops its handles.
  trace("smoke_teardown");
  await evaluate(
    "globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.tpsMainHandle, 2); true",
  );
  const teardown = await observe(evaluate, peak, 20_000, deadline, (snap) => snap.liveHandles === 0);
  const torndown = teardown.last ?? atPeak;
  trace(`teardown: live=${torndown.liveHandles} callbacks=${torndown.callbacks} errs=${torndown.callbackErrors}`);

  const protocolVersion = menu.protocol;
  const checks = {
    modeTpsdemo: menu.mode === "tpsdemo",
    protocol17: protocolVersion === 17,
    // The menu boots, and pressing Play streams the whole level in: the AnimationTree-driven
    // player, its input synchronizer, the shake camera, and the four red robots with their
    // detachable death parts.
    menuScriptsReady: menu.mainReady >= 1 && menu.menuReady >= 1,
    levelScriptsReady:
      level.levelReady >= 1 &&
      level.playerReady >= 1 &&
      level.inputReady >= 1 &&
      level.shakeReady >= 1,
    enemiesSpawned: level.robotReady >= 4 && level.partReady >= 12,
    playerMovedOnInput: displacement > 0.5,
    physicsFramesAdvanced: peak.physicsCalls >= baseline.physicsCalls + 40,
    processFramesAdvanced: peak.processCalls > baseline.processCalls,
    gameplayCommandsApplied: peak.appliedCommands > baseline.appliedCommands + 80,
    crossingsAdvanced: peak.crossings > baseline.crossings,
    fullTeardownToZero: torndown.liveHandles === 0,
    physicsOrderingDeterministic: (torndown.physicsAfterProcess ?? 0) === 0,
    noCallbackFaults:
      peak.callbackErrors === 0 && torndown.callbackErrors === 0 && torndown.failure === null,
  };

  const boundaryErrors = [];
  if (peak.callbackErrors !== 0) boundaryErrors.push(`callbackErrors=${peak.callbackErrors}`);
  if (torndown.failure !== null) boundaryErrors.push(`failure: ${torndown.failure}`);

  return {
    protocolVersion,
    // The envelope schema (scripts/web/result_schema.py) requires startup
    // {loaded, outcome, durationMs} plus the handles/crossings/callbacks/
    // connections/scheduler/teardown sections. tpsdemo shipped a flat `metrics`
    // bag instead, so web_export_smoke.sh rejected its result on EVERY engine
    // and the demo never passed the wrapper gate -- only its own 13 driver
    // checks, which is exactly the distinction the kickoff warns about. Same
    // facts, reported in the shape the other eleven modules use.
    startup: {
      loaded: menu.mode === "tpsdemo",
      outcome: menu.mode === "tpsdemo" ? "ready" : "failed",
      durationMs: startupDurationMs,
      readyCount: level.readyCount,
    },
    checks,
    handles: {
      liveAfterGameplay: peak.maxLiveHandles,
      liveAfterTeardown: torndown.liveHandles,
      // tpsdemo has no stale-handle probe; teardown-to-zero is its invariant.
      staleRejected: 0,
    },
    crossings: {
      kotlinToGodotCalls: peak.crossings,
      physicsProcessCalls: peak.physicsCalls,
      processCalls: peak.processCalls,
      appliedCommands: peak.appliedCommands,
      playerDisplacement: Number(displacement.toFixed(3)),
      robotsSpawned: level.robotReady,
      deathPartsSpawned: level.partReady,
    },
    callbacks: {
      pendingSignalCallbacks: torndown.callbacks,
    },
    connections: {
      afterTeardownLiveHandles: torndown.liveHandles,
    },
    scheduler: {
      pendingCoroutines: torndown.pending,
      registeredJobs: torndown.jobs,
    },
    teardown: {
      outcome:
        checks.fullTeardownToZero && torndown.callbackErrors === 0 && torndown.failure === null
          ? "clean"
          : "incomplete",
      ownerRegistriesToBaseline:
        torndown.liveHandles === 0 && torndown.callbacks === 0 &&
        torndown.pending === 0 && torndown.jobs === 0,
    },
    boundaryErrors,
  };
}

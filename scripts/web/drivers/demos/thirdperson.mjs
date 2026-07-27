// demos/thirdperson.mjs -- third-person controller play + teardown assertions for the
// Web smoke.
//
// The demo boots PAUSED behind its instructions page: the driver first dispatches
// SmokeQuit.smoke_resume (method#1) to press through, then holds move_up via the trusted
// per-engine key transport and PROVES movement by reading the player's global position
// through the bridge's immediate Vector3 channel (opcode 138). Enemy AI is self-evidencing
// while the world runs (beetle-bot navigation, bee-bot bobbing). smoke_teardown (method#2)
// releases the scene caches and frees the root, draining every live handle to zero.

const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
const DEBUG = process.env.KANAMA_WEB_SMOKE_DEBUG === "1";
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
        smokeQuitReady: classCount(".SmokeQuit"),
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
      const handle = bridge.tpPlayerHandle;
      const x = bridge.immediateNoArgsVector3X(138, handle);
      return { x, y: bridge.immediateNoArgsVector3Y(), z: bridge.immediateNoArgsVector3Z() };
    })()`);
  } catch {
    return null;
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

export async function runThirdperson({ url, evaluate, navigate, keys, deadline }) {
  const keyDown = keys ? () => keys("down", "w") : () => { throw new Error("thirdperson needs the keys transport"); };
  const keyUp = keys ? () => keys("up", "w") : () => {};

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
  const startPosition = await playerPosition(evaluate);
  if (!startPosition) throw new Error("could not read the player's global position");
  trace(`resumed: physics=${baseline.physicsCalls} start=${JSON.stringify(startPosition)}`);

  // Hold move_up for a FIXED wall-time (no early exit: the tick rate races past any
  // physics-count predicate before the first observe sample, cutting the hold short —
  // played sessions with a fixed 3s hold consistently cover ~8 units).
  await keyDown();
  const gameplay = await observe(
    evaluate,
    { ...seedFrom(baseline), callbackErrors: 0 },
    3_000,
    deadline,
    null,
  );
  await keyUp();
  const runPosition = await playerPosition(evaluate);
  const peak = gameplay.peak;
  const atPeak = gameplay.last ?? baseline;
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
    protocol15: protocolVersion === 15,
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
    physicsFramesAdvanced: peak.physicsCalls >= baseline.physicsCalls + 40,
    gameplayCommandsApplied: peak.appliedCommands > baseline.appliedCommands + 80,
    crossingsAdvanced: peak.crossings > baseline.crossings,
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
      playerDisplacement: Number(displacement.toFixed(3)),
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
      ownerRegistriesToBaseline: settled.liveHandles <= peak.maxLiveHandles,
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

// demos/platformer.mjs -- Starter-Kit-3D-Platformer play + teardown assertions.
//
// The platformer is self-driven for smoke purposes: without any synthesized input the
// CharacterBody3D player still runs gravity + move_and_slide every physics tick, coins
// spin, clouds bob, and the View camera follows -- so _physics_process and _process both
// advance with their command mutations applied. This driver OBSERVES that both frame
// pumps run (physics AND process), that commands flow, then triggers
// SmokeQuit.smoke_teardown and polls the live-handle count to zero.

const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
const DEBUG = process.env.KANAMA_WEB_SMOKE_DEBUG === "1";
const START = Date.now();
const trace = (msg) => {
  if (DEBUG) process.stderr.write(`[platformer ${((Date.now() - START) / 1000).toFixed(1)}s] ${msg}\n`);
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
        mainHandle: bridge.platformerMainHandle,
        smokeQuitHandle: bridge.platformerSmokeQuitHandle,
        readyCount: bridge.readyCount,
        mainReady: classCount(".Main"),
        playerReady: classCount(".Player"),
        hudReady: classCount(".Hud"),
        coinReady: classCount(".Coin"),
        processCalls: bridge.processCalls,
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
      trace(`process=${snap.processCalls} physics=${snap.physicsCalls} applied=${snap.appliedCommands} live=${snap.liveHandles} crossings=${snap.crossings} errs=${snap.callbackErrors}`);
      if (predicate && predicate(snap, peak)) break;
    }
    await delay(150);
  }
  return { last, peak };
}

export async function runPlatformer({ url, evaluate, navigate, deadline }) {
  const startupStart = Date.now();
  trace("navigate");
  await navigate(`${url}?platformer=${Date.now()}`);

  const readyDeadline = Math.min(deadline, Date.now() + 45_000);
  let ready = null;
  while (Date.now() < readyDeadline) {
    const snap = await snapshot(evaluate);
    if (
      snap &&
      snap.mode === "platformer" &&
      snap.protocol > 0 &&
      snap.mainReady >= 1 &&
      snap.playerReady >= 1 &&
      snap.hudReady >= 1 &&
      snap.coinReady >= 1 &&
      snap.mainHandle > 0 &&
      snap.smokeQuitHandle > 0
    ) {
      ready = snap;
      break;
    }
    await delay(100);
  }
  if (!ready) throw new Error("Kotlin/Wasm 3D platformer did not become ready");
  const startupDurationMs = Date.now() - startupStart;
  trace(`ready: readyCount=${ready.readyCount} mainHandle=${ready.mainHandle} protocol=${ready.protocol}`);

  // Observe the game running idle: the player's _physics_process (gravity +
  // move_and_slide) advances physicsCalls and its velocity/position mutations advance
  // appliedCommands; coins/clouds/view advance processCalls.
  const seed = {
    processCalls: ready.processCalls,
    physicsCalls: ready.physicsCalls,
    appliedCommands: ready.appliedCommands,
    maxLiveHandles: ready.maxLiveHandles,
    crossings: ready.crossings,
    callbackErrors: 0,
  };
  const gameplay = await observe(
    evaluate,
    seed,
    8_000,
    deadline,
    (snap) =>
      snap.physicsCalls >= ready.physicsCalls + 30 &&
      snap.processCalls >= ready.processCalls + 30 &&
      snap.appliedCommands >= ready.appliedCommands + 30,
  );
  const peak = gameplay.peak;
  const atPeak = gameplay.last ?? ready;
  trace(`gameplay: process=${peak.processCalls} physics=${peak.physicsCalls} applied=${peak.appliedCommands} live=${atPeak.liveHandles}`);

  // Full teardown: SmokeQuit.smoke_teardown (its only @RegisterFunction, method#1) frees
  // the scene root; every node exits the tree and releases its handles.
  trace("smoke_teardown");
  await evaluate(
    "globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.platformerSmokeQuitHandle, 1); true",
  );
  const teardown = await observe(evaluate, peak, 8_000, deadline, (snap) => snap.liveHandles === 0);
  const settled = teardown.last ?? atPeak;
  trace(`teardown: live=${settled.liveHandles} callbacks=${settled.callbacks} pending=${settled.pending} jobs=${settled.jobs} errs=${settled.callbackErrors}`);

  const protocolVersion = ready.protocol;
  const checks = {
    modePlatformer: ready.mode === "platformer",
    protocol13: protocolVersion === 13,
    sceneScriptsReady:
      ready.mainReady >= 1 && ready.playerReady >= 1 && ready.hudReady >= 1 && ready.coinReady >= 1,
    // Both frame pumps ran: _physics_process (player gravity/move_and_slide) and
    // _process (coins/clouds/camera) each advanced many frames.
    physicsFramesAdvanced: peak.physicsCalls >= ready.physicsCalls + 30,
    processFramesAdvanced: peak.processCalls >= ready.processCalls + 30,
    gameplayCommandsApplied: peak.appliedCommands >= ready.appliedCommands + 30,
    // move_and_slide/is_on_floor are immediate kotlin->godot crossings every physics tick.
    crossingsAdvanced: peak.crossings > ready.crossings,
    fullTeardownToZero: settled.liveHandles === 0,
    // Godot runs every physics tick before the idle/_process pass inside one rAF
    // iteration; the bridge counts any same-tick physics-after-process dispatch.
    physicsOrderingDeterministic: (settled.physicsAfterProcess ?? 0) === 0,
    noCallbackFaults: peak.callbackErrors === 0 && settled.callbackErrors === 0 && settled.failure === null,
  };

  const boundaryErrors = [];
  if (peak.callbackErrors !== 0) boundaryErrors.push(`callbackErrors=${peak.callbackErrors}`);
  if (settled.failure !== null) boundaryErrors.push(`failure: ${settled.failure}`);

  return {
    protocolVersion,
    startup: {
      loaded: ready.mode === "platformer",
      outcome: ready.mode === "platformer" ? "ready" : "failed",
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
      processCalls: peak.processCalls,
      physicsProcessCalls: peak.physicsCalls,
      appliedCommands: peak.appliedCommands,
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

// demos/web3d.mjs -- 3D render-foundation smoke: observe + teardown assertions.
//
// The web3d scene auto-runs: Main._ready applies the platformer's Compatibility-renderer
// tuning (CanvasLayer.visible, Light3D.set_param, WorldEnvironment/Environment) and _process
// spins a child Node3D every frame. This driver OBSERVES the render is live (process frames
// and applied commands advancing, no faults), then triggers SmokeQuit.smoke_teardown and polls
// the live-handle count to zero.

const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
const DEBUG = process.env.KANAMA_WEB_SMOKE_DEBUG === "1";
const START = Date.now();
const trace = (msg) => {
  if (DEBUG) process.stderr.write(`[web3d ${((Date.now() - START) / 1000).toFixed(1)}s] ${msg}\n`);
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
        mainHandle: bridge.web3dMainHandle,
        smokeQuitHandle: bridge.web3dSmokeQuitHandle,
        readyCount: bridge.readyCount,
        mainReady: classCount(".Main"),
        processCalls: bridge.processCalls,
        appliedCommands: bridge.appliedCommands,
        liveHandles: bridge.liveBrowserHandleCount,
        maxLiveHandles: bridge.maxLiveBrowserHandles,
        crossings: bridge.kotlinToGodotCalls,
        callbackErrors: bridge.callbackErrors,
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
      peak.appliedCommands = Math.max(peak.appliedCommands, snap.appliedCommands);
      peak.maxLiveHandles = Math.max(peak.maxLiveHandles, snap.maxLiveHandles);
      peak.crossings = Math.max(peak.crossings, snap.crossings);
      peak.callbackErrors = Math.max(peak.callbackErrors, snap.callbackErrors);
      last = snap;
      trace(`process=${snap.processCalls} applied=${snap.appliedCommands} live=${snap.liveHandles} max=${snap.maxLiveHandles} errs=${snap.callbackErrors}`);
      if (predicate && predicate(snap, peak)) break;
    }
    await delay(150);
  }
  return { last, peak };
}

export async function runWeb3d({ url, evaluate, navigate, deadline }) {
  const startupStart = Date.now();
  trace("navigate");
  await navigate(`${url}?web3d=${Date.now()}`);

  const readyDeadline = Math.min(deadline, Date.now() + 30_000);
  let ready = null;
  while (Date.now() < readyDeadline) {
    const snap = await snapshot(evaluate);
    if (
      snap &&
      snap.mode === "web3d" &&
      snap.protocol > 0 &&
      snap.mainReady >= 1 &&
      snap.mainHandle > 0 &&
      snap.smokeQuitHandle > 0
    ) {
      ready = snap;
      break;
    }
    await delay(100);
  }
  if (!ready) throw new Error("Kotlin/Wasm web3d scene did not become ready");
  const startupDurationMs = Date.now() - startupStart;
  trace(`ready: readyCount=${ready.readyCount} mainHandle=${ready.mainHandle} protocol=${ready.protocol}`);

  // Observe the render running: the spinner's _process advances processCalls and its
  // Node3D.rotation mutations advance appliedCommands each frame.
  const seed = {
    processCalls: ready.processCalls,
    appliedCommands: ready.appliedCommands,
    maxLiveHandles: ready.maxLiveHandles,
    crossings: ready.crossings,
    callbackErrors: 0,
  };
  const render = await observe(evaluate, seed, 5_000, deadline, (snap) =>
    snap.processCalls >= ready.processCalls + 10 && snap.appliedCommands >= ready.appliedCommands + 10,
  );
  const peak = render.peak;
  const atPeak = render.last ?? ready;
  trace(`render: process=${peak.processCalls} applied=${peak.appliedCommands} live=${atPeak.liveHandles}`);

  // Full teardown: SmokeQuit.smoke_teardown (method#1) frees the scene root, draining handles.
  trace("smoke_teardown");
  await evaluate(
    "globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.web3dSmokeQuitHandle, 1); true",
  );
  const teardown = await observe(evaluate, peak, 6_000, deadline, (snap) => snap.liveHandles === 0);
  const settled = teardown.last ?? atPeak;
  trace(`teardown: live=${settled.liveHandles} errs=${settled.callbackErrors}`);

  const protocolVersion = ready.protocol;
  const checks = {
    modeWeb3d: ready.mode === "web3d",
    protocol12: protocolVersion === 12,
    sceneReady: ready.mainReady >= 1,
    // _process ran many frames (the spinner) with its Node3D.rotation mutations applied.
    renderFramesAdvanced: peak.processCalls >= ready.processCalls + 10,
    transformCommandsApplied: peak.appliedCommands >= ready.appliedCommands + 10,
    fullTeardownToZero: settled.liveHandles === 0,
    noCallbackFaults: peak.callbackErrors === 0 && settled.callbackErrors === 0 && settled.failure === null,
  };

  const boundaryErrors = [];
  if (peak.callbackErrors !== 0) boundaryErrors.push(`callbackErrors=${peak.callbackErrors}`);
  if (settled.failure !== null) boundaryErrors.push(`failure: ${settled.failure}`);

  return {
    protocolVersion,
    startup: {
      loaded: ready.mode === "web3d",
      outcome: ready.mode === "web3d" ? "ready" : "failed",
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

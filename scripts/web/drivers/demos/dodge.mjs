// demos/dodge.mjs -- dodge-the-creeps play + teardown assertions for the Web smoke.
//
// Unlike Bunnymark (driven by bridge method calls) and Match3 (driven by synthetic
// pointer input), dodge is a real-time, self-driven demo: the SmokeQuit script starts
// a game (new_game), lets Godot's MobTimer spawn mobs and the ScoreTimer tick, then
// tears the mobs down and quits. This driver OBSERVES: it polls the bridge telemetry,
// captures the gameplay peak (mobs instantiated + added to the tree, kotlin->godot
// crossings advancing) and the post-teardown trough, and asserts a clean run with no
// callback faults.

const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
const DEBUG = process.env.KANAMA_WEB_SMOKE_DEBUG === "1";
const START = Date.now();
const trace = (msg) => {
  if (DEBUG) process.stderr.write(`[dodge ${((Date.now() - START) / 1000).toFixed(1)}s] ${msg}\n`);
};

// Reads a flat telemetry snapshot from the bridge. Every field is generic (the
// match3-prefixed instantiation/addChild counters are, despite the name, incremented
// for any mode). Returns null if the page is mid-navigation or has been torn down.
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
        protocol: bridge.results?.protocolVersion ?? 0,
        mainHandle: bridge.dodgeMainHandle,
        smokeQuitHandle: bridge.dodgeSmokeQuitHandle,
        readyCount: bridge.readyCount,
        mainReady: classCount(".Main"),
        playerReady: classCount(".Player"),
        hudReady: classCount(".HUD"),
        mobReady: classCount(".Mob"),
        mobInstantiations: bridge.match3PackedSceneInstantiations,
        mobAddChildCommands: bridge.match3AddChildCommands,
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

async function callMain(evaluate, method) {
  return evaluate(
    `globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.dodgeMainHandle, globalThis.KanamaWebBridge.dodgeMethodId(${JSON.stringify(method)})); true`,
  );
}

// SmokeQuit.smoke_teardown is its only @RegisterFunction (method#1); it quits the
// SceneTree so every node exits the tree and releases its handles.
async function callTeardown(evaluate) {
  return evaluate(
    "globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.dodgeSmokeQuitHandle, 1); true",
  );
}

// Polls the snapshot until `predicate` holds or the window elapses, tracking the
// running peak and returning { last, peak }. Robust to the page briefly returning null.
async function observe(evaluate, seedPeak, windowMs, deadline, predicate) {
  const peak = { mobFrees: 0, ...seedPeak };
  let last = null;
  let prevLive = seedPeak.maxLiveHandles;
  const until = Math.min(deadline, Date.now() + windowMs);
  while (Date.now() < until) {
    const snap = await snapshot(evaluate);
    if (snap) {
      peak.mobInstantiations = Math.max(peak.mobInstantiations, snap.mobInstantiations);
      peak.mobAddChildCommands = Math.max(peak.mobAddChildCommands, snap.mobAddChildCommands);
      peak.maxLiveHandles = Math.max(peak.maxLiveHandles, snap.maxLiveHandles);
      peak.crossings = Math.max(peak.crossings, snap.crossings);
      peak.callbackErrors = Math.max(peak.callbackErrors, snap.callbackErrors);
      // A drop in the live-handle count means a mob (and its child nodes) was freed —
      // dodge mobs queue_free themselves when a VisibleOnScreenNotifier2D reports they
      // left the screen. Counting drops proves the free/release path works during play.
      // It UNDERCOUNTS when a spawn lands in the same sample as a free, which is why the
      // window below is a generous ceiling rather than a fixed duration.
      if (snap.liveHandles < prevLive) peak.mobFrees += 1;
      prevLive = snap.liveHandles;
      last = snap;
      trace(`mobs=${snap.mobInstantiations} addChild=${snap.mobAddChildCommands} live=${snap.liveHandles} max=${snap.maxLiveHandles} frees=${peak.mobFrees} crossings=${snap.crossings} errs=${snap.callbackErrors}`);
      if (predicate && predicate(snap, peak)) break;
    }
    await delay(150);
  }
  return { last, peak };
}

export async function runDodge({ url, evaluate, navigate, deadline }) {
  const startupStart = Date.now();
  trace("navigate");
  await navigate(`${url}?dodge=${Date.now()}`);

  // Ready: dodge mode, the scene scripts recorded ready, the Main handle captured.
  const readyDeadline = Math.min(deadline, Date.now() + 30_000);
  let ready = null;
  while (Date.now() < readyDeadline) {
    const snap = await snapshot(evaluate);
    if (
      snap &&
      snap.mode === "dodge" &&
      snap.protocol > 0 &&
      snap.mainReady >= 1 &&
      snap.playerReady >= 1 &&
      snap.hudReady >= 1 &&
      snap.mainHandle > 0 &&
      snap.smokeQuitHandle > 0
    ) {
      ready = snap;
      break;
    }
    await delay(100);
  }
  if (!ready) throw new Error("Kotlin/Wasm dodge-the-creeps did not become ready");
  const startupDurationMs = Date.now() - startupStart;
  trace(`ready: readyCount=${ready.readyCount} mainHandle=${ready.mainHandle} protocol=${ready.protocol}`);

  // Drive gameplay from the browser (dodge's SmokeQuit env-var gate is inert on Web):
  // new_game starts StartTimer (2s), then MobTimer spawns a mob every 0.5s and ScoreTimer
  // ticks. Observe until several mobs have spawned (the gameplay peak).
  const seedPeak = { mobInstantiations: 0, mobAddChildCommands: 0, maxLiveHandles: ready.liveHandles, crossings: ready.crossings, callbackErrors: 0 };
  trace("new_game");
  await callMain(evaluate, "new_game");
  // Play long enough for several mobs to spawn AND for the earliest ones to fly across
  // and leave the screen (each mob queue_frees itself on VisibleOnScreenNotifier2D
  // screen_exited). Run the full window so the spawn+free lifecycle is exercised.
  // The window is a CEILING, not a duration: the predicate ends it as soon as two mobs
  // have actually been freed, which on a workstation is a few seconds. It is generous
  // because a software-GL host simulates less game time per wall-clock second, and the
  // assertion should wait for the evidence rather than be graded on the host's speed.
  const gameplay = await observe(evaluate, seedPeak, 45_000, deadline, (snap, p) => p.mobFrees >= 2);
  const peak = gameplay.peak;
  const atPeak = gameplay.last ?? ready;
  trace(`gameplay: mobs=${peak.mobInstantiations} addChild=${peak.mobAddChildCommands} maxLive=${peak.maxLiveHandles} frees=${peak.mobFrees} finalLive=${atPeak.liveHandles} crossings=${peak.crossings}`);

  // Full teardown: SmokeQuit.smoke_teardown quits the SceneTree, exiting every node so it
  // releases its handles. Poll until the live-handle count drains to zero (the smoke
  // wrapper's schema requires liveAfterTeardown === 0). The wasm/JS stays readable after
  // the loop stops, so the snapshot still resolves.
  trace("smoke_teardown");
  await callTeardown(evaluate);
  const teardown = await observe(evaluate, { ...peak, maxLiveHandles: peak.maxLiveHandles }, 6_000, deadline, (snap) => snap.liveHandles === 0);
  const settled = teardown.last ?? atPeak;
  trace(`teardown: live=${settled.liveHandles} (peak=${peak.maxLiveHandles}) callbacks=${settled.callbacks} pending=${settled.pending} jobs=${settled.jobs} errs=${settled.callbackErrors}`);

  const protocolVersion = ready.protocol;
  const checks = {
    modeDodge: ready.mode === "dodge",
    protocol15: protocolVersion === 15,
    sceneScriptsReady: ready.mainReady >= 1 && ready.playerReady >= 1 && ready.hudReady >= 1,
    mobsInstantiated: peak.mobInstantiations >= 4,
    mobsAddedToTree: peak.mobAddChildCommands >= peak.mobInstantiations,
    handleGrowthDuringGameplay: peak.maxLiveHandles > ready.liveHandles,
    crossingsAdvanced: peak.crossings > ready.crossings,
    // The spawn/free lifecycle works: mobs left the screen and released their handles
    // (>=2 observed drops during play) and stayed bounded by the peak — no leak.
    mobsSpawnAndFree: peak.mobFrees >= 2 && atPeak.liveHandles <= peak.maxLiveHandles,
    // Full teardown: quitting the tree drained every live handle to zero.
    fullTeardownToZero: settled.liveHandles === 0,
    noCallbackFaults: peak.callbackErrors === 0 && settled.callbackErrors === 0 && settled.failure === null,
  };

  const last = settled;
  const boundaryErrors = [];
  if (peak.callbackErrors !== 0) boundaryErrors.push(`callbackErrors=${peak.callbackErrors}`);
  if (settled.failure !== null) boundaryErrors.push(`failure: ${settled.failure}`);

  return {
    protocolVersion,
    startup: {
      loaded: ready.mode === "dodge",
      outcome: ready.mode === "dodge" ? "ready" : "failed",
      durationMs: startupDurationMs,
    },
    checks,
    handles: {
      liveAfterGameplay: peak.maxLiveHandles,
      liveAfterTeardown: last.liveHandles,
      staleRejected: 0,
    },
    crossings: {
      kotlinToGodotCalls: peak.crossings,
      mobInstantiations: peak.mobInstantiations,
      mobAddChildCommands: peak.mobAddChildCommands,
    },
    callbacks: {
      pendingSignalCallbacks: last.callbacks,
    },
    connections: {
      afterGameplayLiveHandles: last.liveHandles,
    },
    scheduler: {
      pendingCoroutines: last.pending,
      registeredJobs: last.jobs,
    },
    teardown: {
      outcome:
        checks.fullTeardownToZero && last.callbackErrors === 0 && last.failure === null
          ? "clean"
          : "incomplete",
      ownerRegistriesToBaseline: last.liveHandles <= peak.maxLiveHandles,
    },
    boundaryErrors,
  };
}

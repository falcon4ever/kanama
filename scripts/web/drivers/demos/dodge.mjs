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

// Polls the snapshot until `predicate` holds or the window elapses, tracking the
// running peak and returning { last, peak }. Robust to the page briefly returning null.
async function observe(evaluate, seedPeak, windowMs, deadline, predicate) {
  const peak = { ...seedPeak };
  let last = null;
  const until = Math.min(deadline, Date.now() + windowMs);
  while (Date.now() < until) {
    const snap = await snapshot(evaluate);
    if (snap) {
      peak.mobInstantiations = Math.max(peak.mobInstantiations, snap.mobInstantiations);
      peak.mobAddChildCommands = Math.max(peak.mobAddChildCommands, snap.mobAddChildCommands);
      peak.maxLiveHandles = Math.max(peak.maxLiveHandles, snap.maxLiveHandles);
      peak.crossings = Math.max(peak.crossings, snap.crossings);
      peak.callbackErrors = Math.max(peak.callbackErrors, snap.callbackErrors);
      last = snap;
      trace(`mobs=${snap.mobInstantiations} addChild=${snap.mobAddChildCommands} live=${snap.liveHandles} max=${snap.maxLiveHandles} crossings=${snap.crossings} errs=${snap.callbackErrors}`);
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
      snap.mainHandle > 0
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
  const gameplay = await observe(evaluate, seedPeak, 8_000, deadline, (snap) => snap.mobInstantiations >= 4);
  const peak = gameplay.peak;
  const atPeak = gameplay.last ?? ready;
  trace(`peak: mobs=${peak.mobInstantiations} addChild=${peak.mobAddChildCommands} maxLive=${peak.maxLiveHandles} crossings=${peak.crossings} live@peak=${atPeak.liveHandles}`);

  // Tear the run down: a second new_game runs callGroup("mobs", "queue_free"), freeing
  // every spawned mob. StartTimer holds new mobs off, so live handles settle back.
  trace("new_game (teardown)");
  await callMain(evaluate, "new_game");
  const teardown = await observe(evaluate, peak, 4_000, deadline, (snap) => snap.liveHandles <= ready.liveHandles);
  const settled = teardown.last ?? atPeak;
  trace(`teardown: live=${settled.liveHandles} (baseline=${ready.liveHandles}) mobs=${settled.mobInstantiations} errs=${settled.callbackErrors}`);

  const protocolVersion = ready.protocol;
  const checks = {
    modeDodge: ready.mode === "dodge",
    protocol7: protocolVersion === 7,
    sceneScriptsReady: ready.mainReady >= 1 && ready.playerReady >= 1 && ready.hudReady >= 1,
    mobsInstantiated: peak.mobInstantiations >= 4,
    mobsAddedToTree: peak.mobAddChildCommands >= peak.mobInstantiations,
    handleGrowthDuringGameplay: atPeak.liveHandles > ready.liveHandles,
    crossingsAdvanced: peak.crossings > ready.crossings,
    mobsTornDownToBaseline: settled.liveHandles <= ready.liveHandles,
    noCallbackFaults: peak.callbackErrors === 0 && settled.failure === null,
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
        last.liveHandles <= ready.liveHandles && last.callbackErrors === 0 && last.failure === null
          ? "clean"
          : "incomplete",
      ownerRegistriesToBaseline: last.liveHandles <= ready.liveHandles,
    },
    boundaryErrors,
  };
}

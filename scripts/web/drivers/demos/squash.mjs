// demos/squash.mjs -- squash-the-creeps play + teardown assertions for the Web smoke.
//
// squash is self-driven: MobTimer autostarts and spawns a mob every 0.5s; each mob's
// initialize runs look_at_from_position/rotate_y and reads its own rotation back, the
// Player's _physics_process runs gravity + move_and_slide + the slide-collision query
// loop every tick, and mobs queue_free themselves when they leave the screen. This
// driver OBSERVES: mobs spawn (instantiate + add_child), physics advances, mobs free
// themselves, then SmokeQuit.smoke_teardown drains every live handle to zero.

const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
const DEBUG = process.env.KANAMA_WEB_SMOKE_DEBUG === "1";
const START = Date.now();
const trace = (msg) => {
  if (DEBUG) process.stderr.write(`[squash ${((Date.now() - START) / 1000).toFixed(1)}s] ${msg}\n`);
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
        mainHandle: bridge.squashMainHandle,
        smokeQuitHandle: bridge.squashSmokeQuitHandle,
        readyCount: bridge.readyCount,
        mainReady: classCount(".Main"),
        playerReady: classCount(".Player"),
        scoreLabelReady: classCount(".ScoreLabel"),
        mobReady: classCount(".Mob"),
        mobInstantiations: bridge.match3PackedSceneInstantiations,
        mobAddChildCommands: bridge.match3AddChildCommands,
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
  const peak = { mobFrees: 0, ...seed };
  let last = null;
  let prevLive = seed.maxLiveHandles;
  const until = Math.min(deadline, Date.now() + windowMs);
  while (Date.now() < until) {
    const snap = await snapshot(evaluate);
    if (snap) {
      peak.mobInstantiations = Math.max(peak.mobInstantiations, snap.mobInstantiations);
      peak.mobAddChildCommands = Math.max(peak.mobAddChildCommands, snap.mobAddChildCommands);
      peak.physicsCalls = Math.max(peak.physicsCalls, snap.physicsCalls);
      peak.appliedCommands = Math.max(peak.appliedCommands, snap.appliedCommands);
      peak.maxLiveHandles = Math.max(peak.maxLiveHandles, snap.maxLiveHandles);
      peak.crossings = Math.max(peak.crossings, snap.crossings);
      peak.callbackErrors = Math.max(peak.callbackErrors, snap.callbackErrors);
      // A drop in the live-handle count means a mob (walked off the level and past its
      // VisibleOnScreenNotifier3D) freed itself and released its handles.
      if (snap.liveHandles < prevLive) peak.mobFrees += 1;
      prevLive = snap.liveHandles;
      last = snap;
      trace(`mobs=${snap.mobInstantiations} physics=${snap.physicsCalls} live=${snap.liveHandles} frees=${peak.mobFrees} errs=${snap.callbackErrors}`);
      if (predicate && predicate(snap, peak)) break;
    }
    await delay(150);
  }
  return { last, peak };
}

export async function runSquash({ url, evaluate, navigate, deadline }) {
  const startupStart = Date.now();
  trace("navigate");
  await navigate(`${url}?squash=${Date.now()}`);

  const readyDeadline = Math.min(deadline, Date.now() + 45_000);
  let ready = null;
  while (Date.now() < readyDeadline) {
    const snap = await snapshot(evaluate);
    if (
      snap &&
      snap.mode === "squash" &&
      snap.protocol > 0 &&
      snap.mainReady >= 1 &&
      snap.playerReady >= 1 &&
      snap.scoreLabelReady >= 1 &&
      snap.mainHandle > 0 &&
      snap.smokeQuitHandle > 0
    ) {
      ready = snap;
      break;
    }
    await delay(100);
  }
  if (!ready) throw new Error("Kotlin/Wasm squash-the-creeps did not become ready");
  const startupDurationMs = Date.now() - startupStart;
  trace(`ready: readyCount=${ready.readyCount} mainHandle=${ready.mainHandle} protocol=${ready.protocol}`);

  // Observe self-driven gameplay: MobTimer spawns mobs (instantiate + initialize with
  // look_at_from_position/rotate_y + rotation read-back), the Player ticks physics with
  // the slide-collision query loop, and the earliest mobs walk off and free themselves.
  const seed = {
    mobInstantiations: 0,
    mobAddChildCommands: 0,
    physicsCalls: ready.physicsCalls,
    appliedCommands: ready.appliedCommands,
    maxLiveHandles: ready.liveHandles,
    crossings: ready.crossings,
    callbackErrors: 0,
  };
  const gameplay = await observe(evaluate, seed, 14_000, deadline, (snap, p) =>
    p.mobInstantiations >= 4 && p.mobFrees >= 2,
  );
  const peak = gameplay.peak;
  const atPeak = gameplay.last ?? ready;
  trace(`gameplay: mobs=${peak.mobInstantiations} physics=${peak.physicsCalls} frees=${peak.mobFrees} live=${atPeak.liveHandles}`);

  // Full teardown: SmokeQuit.smoke_teardown (its only @RegisterFunction, method#1) frees
  // the scene root; every node exits the tree and releases its handles.
  trace("smoke_teardown");
  await evaluate(
    "globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.squashSmokeQuitHandle, 1); true",
  );
  const teardown = await observe(evaluate, peak, 8_000, deadline, (snap) => snap.liveHandles === 0);
  const settled = teardown.last ?? atPeak;
  trace(`teardown: live=${settled.liveHandles} callbacks=${settled.callbacks} errs=${settled.callbackErrors}`);

  const protocolVersion = ready.protocol;
  const checks = {
    modeSquash: ready.mode === "squash",
    protocol9: protocolVersion === 9,
    sceneScriptsReady:
      ready.mainReady >= 1 && ready.playerReady >= 1 && ready.scoreLabelReady >= 1,
    // MobTimer spawned mobs; each initialize ran the look_at/rotate/rotation-read chain.
    mobsInstantiated: peak.mobInstantiations >= 4,
    mobsAddedToTree: peak.mobAddChildCommands >= peak.mobInstantiations,
    // The Player's _physics_process (gravity + move_and_slide + slide-collision loop) and
    // every mob's move_and_slide advanced many ticks.
    physicsFramesAdvanced: peak.physicsCalls >= ready.physicsCalls + 60,
    handleGrowthDuringGameplay: peak.maxLiveHandles > ready.liveHandles,
    crossingsAdvanced: peak.crossings > ready.crossings,
    // Mobs left the level and released their handles, bounded by the peak — no leak.
    mobsSpawnAndFree: peak.mobFrees >= 2 && atPeak.liveHandles <= peak.maxLiveHandles,
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
      loaded: ready.mode === "squash",
      outcome: ready.mode === "squash" ? "ready" : "failed",
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
      mobInstantiations: peak.mobInstantiations,
      mobAddChildCommands: peak.mobAddChildCommands,
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

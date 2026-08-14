// demos/soak.mjs -- long-run leak gate for the Web backend (Task 60h, W4).
//
// The per-demo drivers each play for a fixed window of seconds. That proves a
// demo runs; it cannot prove it still runs in ten minutes. A slow leak -- a
// handle slot never released, a signal callback never unregistered, a coroutine
// job that accumulates -- is invisible at that timescale and fatal at a real
// one.
//
// This driver runs against the DODGE export, deliberately. Leak detection wants
// churn, not polygons: dodge instantiates a mob every half second and each mob
// frees itself when it leaves the screen, so a ten-minute run is hundreds of
// full node create/free cycles through the handle registry, the signal
// connection table and the deferred-free path. A heavier 3D scene renders more
// and allocates less.
//
// What it asserts, beyond "it survived":
//   * live handles do not TREND upward -- the second half's high-water mark may
//     not exceed the first half's (plus a small slack), which catches a leak of
//     even one handle per cycle;
//   * the lifecycle registries (pending signal callbacks, pending coroutines,
//     registered jobs) stay bounded rather than growing with time;
//   * the game restarts cleanly, repeatedly -- `new_game` is called every cycle,
//     which is where a leak of per-round state would show; and
//   * teardown still drains to zero at the end of a long run, not just a short
//     one.
//
// Duration comes from KANAMA_WEB_SOAK_SECONDS (default 600). The smoke shell's
// --timeout must exceed it; scripts/web/demos.sh derives that budget.

const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
const DEBUG = process.env.KANAMA_WEB_SMOKE_DEBUG === "1";
const START = Date.now();
const trace = (msg) => {
  if (DEBUG) process.stderr.write(`[soak ${((Date.now() - START) / 1000).toFixed(1)}s] ${msg}\n`);
};

const SOAK_SECONDS = Number(process.env.KANAMA_WEB_SOAK_SECONDS ?? 600);
// One "cycle" is a full round of dodge: new_game, mobs spawn and free, repeat.
// Restarting is the point -- a leak of per-round state only shows across rounds.
// Configurable so a run can isolate WHICH path leaks: set it longer than the soak
// duration for a single-cycle run, and any growth then belongs to ordinary
// gameplay rather than to the restart path.
const CYCLE_SECONDS = Number(process.env.KANAMA_WEB_SOAK_CYCLE_SECONDS ?? 60);
const SAMPLE_MS = 2000;
// Slack on the half-over-half comparison. The high-water mark is sampled, not
// exact, and mob count varies with the RNG, so a couple of handles of noise must
// not fail the gate; a real leak grows without bound and blows past this.
const HIGH_WATER_SLACK = 8;

async function snapshot(evaluate) {
  try {
    return await evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      if (!bridge) return null;
      return {
        mode: bridge.mode,
        protocol: bridge.results?.protocolVersion ?? 0,
        mainHandle: bridge.dodgeMainHandle,
        smokeQuitHandle: bridge.dodgeSmokeQuitHandle,
        readyCount: bridge.readyCount,
        mobInstantiations: bridge.match3PackedSceneInstantiations,
        liveHandles: bridge.liveBrowserHandleCount,
        maxLiveHandles: bridge.maxLiveBrowserHandles,
        crossings: bridge.kotlinToGodotCalls,
        callbackErrors: bridge.callbackErrors,
        callbacks: bridge.api.kanamaWebPendingSignalCallbackCount(),
        pending: bridge.api.kanamaWebPendingCoroutineCount(),
        jobs: bridge.api.kanamaWebRegisteredCoroutineJobCount(),
        failure: globalThis.KanamaWebFailure?.stack ?? globalThis.KanamaWebFailure?.message ?? null,
        // What is live, by handle kind. A leak count alone says "something grew";
        // this says WHICH kind grew, which turns a hunt into a lookup.
        readyDebug: (bridge.readyDebug ?? []).join(" "),
        sfWho: (bridge.sfWho ?? []).join(" "),
        liveByKind: (() => {
          const counts = {};
          for (const slot of bridge.browserHandleSlots ?? []) {
            if (!slot || !slot.live) continue;
            const kind = slot.kind ?? "(null-kind)";
            counts[kind] = (counts[kind] ?? 0) + 1;
          }
          return Object.entries(counts)
            .sort((a, b) => b[1] - a[1])
            .map((entry) => entry[0] + ":" + entry[1])
            .join(" ");
        })(),
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

async function callTeardown(evaluate) {
  return evaluate(
    "globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.dodgeSmokeQuitHandle, 1); true",
  );
}

export async function runSoak({ url, evaluate, navigate, deadline }) {
  const startupStart = Date.now();
  trace(`navigate (soak ${SOAK_SECONDS}s)`);
  await navigate(`${url}?soak=${Date.now()}`);

  const readyDeadline = Math.min(deadline, Date.now() + 30_000);
  let ready = null;
  while (Date.now() < readyDeadline) {
    const snap = await snapshot(evaluate);
    if (snap && snap.mode === "dodge" && snap.protocol > 0 && snap.mainHandle > 0 && snap.smokeQuitHandle > 0) {
      ready = snap;
      break;
    }
    await delay(100);
  }
  if (!ready) throw new Error("Kotlin/Wasm soak target (dodge) did not become ready");
  const startupDurationMs = Date.now() - startupStart;

  const samples = [];
  const soakUntil = Math.min(deadline - 30_000, Date.now() + SOAK_SECONDS * 1000);
  let cycles = 0;
  let nextCycleAt = 0;
  let last = ready;
  let observedError = null;

  while (Date.now() < soakUntil) {
    if (Date.now() >= nextCycleAt) {
      // Restart the round. The first call starts the game; later ones exercise
      // new_game's own teardown path (it queue_frees the whole mobs group).
      await callMain(evaluate, "new_game");
      cycles += 1;
      nextCycleAt = Date.now() + CYCLE_SECONDS * 1000;
      trace(`cycle ${cycles} (live=${last.liveHandles} before restart)`);
    }
    const snap = await snapshot(evaluate);
    if (snap) {
      last = snap;
      samples.push({ t: Date.now() - START, ...snap });
      // Per-sample trace: when this gate fails, the SHAPE of the growth is the
      // whole diagnosis (a step at each restart means the restart path leaks; a
      // steady climb means ordinary gameplay does).
      trace(`live=${snap.liveHandles} max=${snap.maxLiveHandles} mobs=${snap.mobInstantiations} callbacks=${snap.callbacks} jobs=${snap.jobs} | ${snap.liveByKind}\n   READY ${snap.readyDebug}\n   SFWHO ${snap.sfWho}`);
      if (snap.callbackErrors > 0 && observedError === null) {
        observedError = `callbackErrors=${snap.callbackErrors}`;
      }
      if (snap.failure && observedError === null) observedError = snap.failure;
    }
    await delay(SAMPLE_MS);
  }
  trace(`soak done: cycles=${cycles} samples=${samples.length} live=${last.liveHandles}`);

  if (samples.length < 8) {
    throw new Error(`soak collected only ${samples.length} samples; the window was too short to judge a trend`);
  }

  // Halve the sample series and compare high-water marks. A run that leaks a
  // handle per mob cycle climbs steadily; a healthy one oscillates around a
  // fixed working set no matter how long it runs.
  const midpoint = Math.floor(samples.length / 2);
  const firstHalf = samples.slice(0, midpoint);
  const secondHalf = samples.slice(midpoint);
  const highWater = (series) => Math.max(...series.map((s) => s.liveHandles));
  const maxOf = (series, key) => Math.max(...series.map((s) => s[key]));
  const firstHigh = highWater(firstHalf);
  const secondHigh = highWater(secondHalf);
  trace(`high-water: first=${firstHigh} second=${secondHigh} (slack ${HIGH_WATER_SLACK})`);

  // Teardown after a long run must still drain everything.
  await callTeardown(evaluate);
  const teardownUntil = Math.min(deadline, Date.now() + 15_000);
  let settled = last;
  while (Date.now() < teardownUntil) {
    const snap = await snapshot(evaluate);
    if (snap) {
      settled = snap;
      if (snap.liveHandles === 0) break;
    }
    await delay(200);
  }
  trace(`teardown: live=${settled.liveHandles} callbacks=${settled.callbacks} pending=${settled.pending} jobs=${settled.jobs}`);

  const checks = {
    modeDodge: ready.mode === "dodge",
    // The run really lasted: several full rounds, and enough samples to judge.
    soakCyclesCompleted: cycles >= 2,
    soakSamplesCollected: samples.length >= 8,
    // Gameplay kept happening for the whole window rather than stalling early.
    mobsKeptSpawning: last.mobInstantiations > ready.mobInstantiations + 10,
    crossingsAdvanced: last.crossings > ready.crossings,
    // The leak assertions.
    liveHandlesDidNotTrendUp: secondHigh <= firstHigh + HIGH_WATER_SLACK,
    pendingCallbacksBounded: maxOf(secondHalf, "callbacks") <= maxOf(firstHalf, "callbacks") + HIGH_WATER_SLACK,
    coroutineJobsBounded: maxOf(secondHalf, "jobs") <= maxOf(firstHalf, "jobs") + HIGH_WATER_SLACK,
    noCallbackFaults: observedError === null && settled.callbackErrors === 0 && settled.failure === null,
    // Teardown still drains to zero after a long run.
    fullTeardownToZero: settled.liveHandles === 0,
  };

  const boundaryErrors = [];
  if (observedError !== null) boundaryErrors.push(observedError);
  if (settled.failure !== null) boundaryErrors.push(`failure: ${settled.failure}`);

  return {
    protocolVersion: ready.protocol,
    startup: {
      loaded: ready.mode === "dodge",
      outcome: ready.mode === "dodge" ? "ready" : "failed",
      durationMs: startupDurationMs,
    },
    checks,
    handles: {
      liveAfterGameplay: Math.max(firstHigh, secondHigh),
      liveAfterTeardown: settled.liveHandles,
      staleRejected: 0,
    },
    crossings: {
      kotlinToGodotCalls: last.crossings,
      mobInstantiations: last.mobInstantiations,
      // The evidence a reader wants from a soak: the two half-window high-water
      // marks it was judged on, and how long it actually ran.
      soakSeconds: Math.round((Date.now() - START) / 1000),
      soakCycles: cycles,
      soakSamples: samples.length,
      liveHighWaterFirstHalf: firstHigh,
      liveHighWaterSecondHalf: secondHigh,
    },
    callbacks: {
      pendingSignalCallbacks: settled.callbacks,
      maxPendingFirstHalf: maxOf(firstHalf, "callbacks"),
      maxPendingSecondHalf: maxOf(secondHalf, "callbacks"),
    },
    connections: {
      afterGameplayLiveHandles: settled.liveHandles,
    },
    scheduler: {
      pendingCoroutines: settled.pending,
      registeredJobs: settled.jobs,
      maxJobsFirstHalf: maxOf(firstHalf, "jobs"),
      maxJobsSecondHalf: maxOf(secondHalf, "jobs"),
    },
    teardown: {
      outcome: checks.fullTeardownToZero && checks.noCallbackFaults ? "clean" : "incomplete",
      ownerRegistriesToBaseline: settled.liveHandles === 0,
    },
    boundaryErrors,
  };
}

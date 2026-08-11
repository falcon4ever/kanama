// demos/spike.mjs -- the synthetic transport benchmark as a GATED smoke cell.
//
// Task 84 made the benchmark opt-in: `bridge.frame()`'s fallthrough is the real
// `_process` and only a page that declares `globalThis.KanamaWebMode = "spike"`
// (stamped by `stageWebSpikeGodotProject`) reaches the benchmark branch. Opt-in
// with no gate is how a path rots unnoticed (task 81), so this driver proves the
// branch is alive in BOTH directions on every run that includes it:
//
//   1. mode === "spike" -- the staging's mode line is present. If it ever goes
//      missing the bridge falls back to "game" GAMEPLAY (by design) and this is
//      the check that fails loudly instead of the run quietly measuring nothing.
//   2. The benchmark branch actually ran: its distinctive counters advanced
//      (frameIndex through both sample windows, 120 empty + 120 batched frame
//      samples) AND the real path's distinctive counter did NOT (processCalls
//      stays 0 in spike mode -- the exact counter task 84's demo drivers assert
//      is NON-zero on the real path).
//   3. The page's own Phase-0 harness verdict (18 structural checks: exact
//      sample counts, the OPERATIONS-mutation batch crossing exactly once,
//      read-your-write, hot-reload generation advance, stale-handle rejection)
//      is folded into the envelope as page_* checks.
//   4. Envelope-complete teardown: the driver ends the run the way dodge's
//      SmokeQuit does -- it raises a quit flag the staged benchmark_controller
//      polls, the SceneTree quits, every node exits, and live handles drain to
//      zero.
//
// The page drives itself (samples, benchmarks, hot-reload, verdict); the driver
// observes, then quits. There is no synthetic input.
//
// Transport-cost gating is STRUCTURAL, not wall-denominated: nothing paces rAF
// in headless browsers (free-runs up to ~8.7x), so any ms/throughput number
// grades the host. The page's exact-equality checks (one crossing per
// OPERATIONS-command batch, zero buffer growths) are the transport regression
// gate; the crossings-per-tick budget ratio is inapplicable here because the
// spike never takes the _process/_physics_process tick paths its denominator
// counts (see budgets.json).

const SAMPLE_FRAMES = 120; // mirrors the bridge's SAMPLE_FRAMES
const WARMUP_FRAMES = 30; // mirrors the bridge's WARMUP_FRAMES
const SPIKE_FRAME_TOTAL = (WARMUP_FRAMES + SAMPLE_FRAMES) * 2;

const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
const DEBUG = process.env.KANAMA_WEB_SMOKE_DEBUG === "1";
const START = Date.now();
const trace = (msg) => {
  if (DEBUG) process.stderr.write(`[spike ${((Date.now() - START) / 1000).toFixed(1)}s] ${msg}\n`);
};

const counters = (evaluate) =>
  evaluate(`JSON.stringify((() => {
    const bridge = globalThis.KanamaWebBridge;
    return {
      mode: bridge.mode,
      frameIndex: bridge.frameIndex,
      emptyFrameSamples: bridge.emptyFrameMs.length,
      batchedFrameSamples: bridge.batchedFrameMs.length,
      processCalls: bridge.processCalls,
      physicsProcessCalls: bridge.physicsProcessCalls ?? 0,
      appliedCommands: bridge.appliedCommands,
      kotlinToGodotCalls: bridge.kotlinToGodotCalls,
      readyCount: bridge.readyCount,
      resourceLoads: bridge.resourceLoads,
      resourceReleases: bridge.resourceReleases,
      liveBrowserHandles: bridge.liveBrowserHandleCount,
      commandBufferGrowths: bridge.commandBufferGrowths,
      callbackErrors: bridge.callbackErrors,
      lastCallbackError: bridge.lastCallbackError,
      status: document.body.dataset.status ?? null,
    };
  })())`).then(JSON.parse);

// The mode was wrong: the export under test is NOT the spike page (either a demo
// export was pointed at this driver, or the staging lost its mode line and the
// bridge fell back to "game" gameplay -- task 84's designed failure shape). The
// benchmark lifecycle will never run, so report the one fact that matters and
// fail fast rather than timing out.
function wrongModeEnvelope(mode, startupDurationMs) {
  return {
    protocolVersion: 18,
    startup: {
      loaded: true,
      outcome: `wrong-mode:${mode}`,
      durationMs: startupDurationMs,
    },
    checks: {
      mode: false,
    },
    handles: { liveAfterGameplay: 0, liveAfterTeardown: 0, staleRejected: 0 },
    crossings: { kotlinToGodotCalls: 0 },
    callbacks: { readyCount: 0 },
    connections: { afterTeardownLiveHandles: 0 },
    scheduler: { commandBufferGrowths: 0 },
    teardown: { outcome: "not-run", ownerRegistriesToBaseline: false },
    boundaryErrors: [
      `expected bridge mode "spike", found "${mode}" -- the export under test does not opt in to the benchmark`,
    ],
  };
}

export async function runSpike({ url, evaluate, navigate, deadline }) {
  const startupStart = Date.now();
  trace("navigate");
  await navigate(`${url}?spike=${Date.now()}`);

  // The bridge object exists as soon as its script tag evaluates (before the
  // wasm loads), and its mode is fixed at that moment from the shell's
  // KanamaWebMode line -- so the opt-in check needs no gameplay to run.
  let mode = null;
  const bridgeDeadline = Math.min(deadline, Date.now() + 30_000);
  while (mode === null && Date.now() < bridgeDeadline) {
    try {
      mode = await evaluate("globalThis.KanamaWebBridge ? globalThis.KanamaWebBridge.mode : null");
    } catch {
      // page mid-navigation; retry
    }
    if (mode === null) await delay(100);
  }
  if (mode === null) throw new Error("Kanama Web bridge never appeared on the spike page");
  if (mode !== "spike") {
    trace(`mode check FAILED: found "${mode}"`);
    return wrongModeEnvelope(mode, Date.now() - startupStart);
  }

  let ready = false;
  const readyDeadline = Math.min(deadline, Date.now() + 60_000);
  while (!ready && Date.now() < readyDeadline) {
    try {
      ready = Boolean(await evaluate("globalThis.KanamaWebBridge?.firstHandle > 0"));
    } catch {
      // page mid-navigation; retry
    }
    if (!ready) await delay(100);
  }
  if (!ready) throw new Error("Kotlin/Wasm spike benchmark did not become ready");
  const startupDurationMs = Date.now() - startupStart;
  trace("ready; page is self-driving the benchmark");

  // The page runs itself: 30+120 empty frames, 30+120 batched frames, then the
  // call benchmarks, a hot-reload teardown/replacement cycle, and finish().
  let status = null;
  let lastTraced = 0;
  while (Date.now() < deadline) {
    const snap = await counters(evaluate);
    status = snap.status;
    if (status === "pass" || status === "fail") break;
    if (DEBUG && Date.now() - lastTraced > 2_000) {
      trace(`waiting: frameIndex=${snap.frameIndex} ready=${snap.readyCount} status=${status}`);
      lastTraced = Date.now();
    }
    await delay(250);
  }
  if (status !== "pass" && status !== "fail") {
    throw new Error("spike benchmark lifecycle did not reach a verdict before the deadline");
  }
  trace(`page verdict: ${status}`);

  const results = await evaluate(
    "globalThis.KanamaWebSpikeResults ? JSON.stringify(globalThis.KanamaWebSpikeResults) : null",
  ).then((raw) => (raw ? JSON.parse(raw) : null));
  if (!results) {
    const fatal = await evaluate(
      "document.querySelector('#kanama-results')?.textContent ?? 'no results element'",
    );
    throw new Error(`spike page reached status=${status} without publishing results: ${fatal}`);
  }
  const afterBenchmark = await counters(evaluate);
  trace(
    `benchmark done: frames=${afterBenchmark.frameIndex} applied=${afterBenchmark.appliedCommands} ` +
      `live=${afterBenchmark.liveBrowserHandles} process=${afterBenchmark.processCalls}`,
  );

  // Full teardown: raise the quit flag the staged benchmark_controller polls;
  // the SceneTree quits, every node exits, and the handle registry drains to
  // zero (the dodge SmokeQuit pattern).
  await evaluate("globalThis.KanamaWebSpikeQuitRequested = true; true");
  const teardownUntil = Math.min(deadline, Date.now() + 15_000);
  let drained = afterBenchmark;
  while (Date.now() < teardownUntil) {
    drained = await counters(evaluate);
    if (drained.liveBrowserHandles === 0) break;
    await delay(250);
  }
  trace(`teardown: live=${drained.liveBrowserHandles} callbackErrors=${drained.callbackErrors}`);

  const checks = {
    // The gate that makes the opt-in gated: the staging must still name the mode.
    mode: afterBenchmark.mode === "spike",
    // The benchmark branch ran: both sample windows completed on its own counters.
    spikeBranchDispatched:
      afterBenchmark.frameIndex >= SPIKE_FRAME_TOTAL &&
      afterBenchmark.emptyFrameSamples === SAMPLE_FRAMES &&
      afterBenchmark.batchedFrameSamples === SAMPLE_FRAMES,
    // The branch, not just activity: in spike mode the real _process/_physics
    // paths must never run (the demo drivers assert the exact inverse).
    realProcessPathNotDispatched:
      drained.processCalls === 0 && drained.physicsProcessCalls === 0,
    // finish() published a complete report.
    benchmarkReportPublished:
      typeof results.completedAt === "string" && results.protocolVersion > 0,
    fullTeardownToZero: drained.liveBrowserHandles === 0 && drained.callbackErrors === 0,
  };
  // The page's own Phase-0 verdict, check by check: exact sample counts, the
  // OPERATIONS-mutation batch crossing exactly ONCE (the structural
  // transport-cost gate), read-your-write, hot-reload generation advance,
  // stale-handle invalidation, teardown of the first instance.
  for (const [name, value] of Object.entries(results.checks ?? {})) {
    checks[`page_${name}`] = value === true;
  }

  const boundaryErrors = [];
  if (drained.callbackErrors !== 0) {
    boundaryErrors.push(
      `callbackErrors=${drained.callbackErrors}: ${drained.lastCallbackError ?? "unknown"}`,
    );
  }

  return {
    protocolVersion: results.protocolVersion,
    startup: {
      loaded: ready,
      outcome: ready ? "ready" : "failed",
      durationMs: startupDurationMs,
    },
    checks,
    handles: {
      liveAfterGameplay: afterBenchmark.liveBrowserHandles,
      liveAfterTeardown: drained.liveBrowserHandles,
      staleRejected: results.lifecycle?.staleHandleInvalidated === true ? 1 : 0,
    },
    crossings: {
      kotlinToGodotCalls: drained.kotlinToGodotCalls,
      appliedCommands: drained.appliedCommands,
      spikeFrameDispatches: drained.frameIndex,
      backendContractQueuedCrossings: results.backendContract?.queuedCrossings ?? 0,
    },
    callbacks: {
      readyCount: drained.readyCount,
    },
    connections: {
      afterTeardownLiveHandles: drained.liveBrowserHandles,
    },
    scheduler: {
      commandBufferGrowths: drained.commandBufferGrowths,
    },
    teardown: {
      outcome: checks.fullTeardownToZero ? "clean" : "incomplete",
      ownerRegistriesToBaseline: drained.liveBrowserHandles === 0,
    },
    boundaryErrors,
  };
}

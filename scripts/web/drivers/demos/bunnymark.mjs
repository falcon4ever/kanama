// demos/bunnymark.mjs -- Bunnymark play + teardown assertions for the Web smoke.
//
// Ported from the validated Task-57 Firefox BiDi Bunnymark driver, decoupled
// from any one browser: the caller injects `evaluate` (page expression -> value).
// Bunnymark drives entirely through bridge method calls, so unlike Match3 it
// needs no synthetic pointer input.
//
// It adds 256 sprites through one bounded position batch, measures a steady
// window, removes/re-adds one sprite to advance a handle generation and prove a
// stale handle is rejected, then finishes and asserts deterministic teardown to
// zero live handles.

const BUNNY_COUNT = 256;
const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
const DEBUG = process.env.KANAMA_WEB_SMOKE_DEBUG === "1";
const START = Date.now();
const trace = (msg) => {
  if (DEBUG) process.stderr.write(`[bunnymark ${((Date.now() - START) / 1000).toFixed(1)}s] ${msg}\n`);
};

const snapshot = (evaluate) =>
  evaluate("JSON.stringify(globalThis.KanamaWebBridge.bunnymarkSnapshot())").then(JSON.parse);

export async function runBunnymark({ url, evaluate, navigate, deadline }) {
  const startupStart = Date.now();
  trace("navigate");
  await navigate(`${url}?bunnymark=${Date.now()}`);

  let ready = false;
  const readyDeadline = Math.min(deadline, Date.now() + 30_000);
  while (!ready && Date.now() < readyDeadline) {
    try {
      ready = Boolean(
        await evaluate(
          "globalThis.KanamaWebBridge?.firstHandle > 0 && globalThis.KanamaWebBridge?.resourceLoads === 1",
        ),
      );
    } catch {
      // page mid-navigation; retry
    }
    if (!ready) await delay(100);
  }
  if (!ready) throw new Error("Kotlin/Wasm Bunnymark did not become ready");
  const startupDurationMs = Date.now() - startupStart;
  trace("ready; adding bunnies");

  await evaluate(`(() => {
    const bridge = globalThis.KanamaWebBridge;
    for (let i = 0; i < ${BUNNY_COUNT}; i += 1) bridge.callNoArgs(bridge.firstHandle, bridge.bunnymarkMethodId("add"));
    return true;
  })()`);
  await delay(750);
  await evaluate("globalThis.KanamaWebBridge.resetBunnymarkTimings(); true");

  const windowStart = await snapshot(evaluate);
  const target = windowStart.processCalls + 120;
  while (Number(await evaluate("globalThis.KanamaWebBridge.processCalls")) < target) {
    if (Date.now() > deadline) throw new Error("Bunnymark measurement window did not complete");
    await delay(25);
  }
  const running = await snapshot(evaluate);
  trace(`steady: adds=${running.addBunnyCalls} live=${running.liveBrowserHandles} batch=${running.maxPositionMutationBatch}`);

  await evaluate(`globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.firstHandle, globalThis.KanamaWebBridge.bunnymarkMethodId("remove")); true`);
  await delay(100);
  const removed = await snapshot(evaluate);

  await evaluate(`globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.firstHandle, globalThis.KanamaWebBridge.bunnymarkMethodId("add")); true`);
  await delay(250);
  const replaced = await snapshot(evaluate);

  const staleHandleFailed = Boolean(
    await evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      try {
        bridge.requireBrowserHandle(bridge.lastFreedObjectHandle, "Sprite2D");
        return false;
      } catch (error) {
        return String(error).includes("Stale or wrong-kind");
      }
    })()`),
  );

  await evaluate(`globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.firstHandle, globalThis.KanamaWebBridge.bunnymarkMethodId("finish")); true`);
  await delay(750);
  const finished = await snapshot(evaluate);
  trace(`finished: live=${finished.liveBrowserHandles} frees=${finished.objectFrees} signal=${finished.signalEmits}`);

  const protocolVersion = Number(await evaluate("globalThis.KanamaWebBridge.results.protocolVersion"));
  const processDelta = running.processCalls - windowStart.processCalls;
  const crossingDelta = running.kotlinToGodotCalls - windowStart.kotlinToGodotCalls;

  const checks = {
    mode: running.mode === "bunnymark" && running.bunnymarkVariant === "BunnymarkV1Sprites",
    ready: running.readyCount === 1 && running.handle > 0,
    exactAdds: running.addBunnyCalls === BUNNY_COUNT,
    exactConstruction:
      running.objectConstructions === BUNNY_COUNT &&
      running.liveBrowserHandles === BUNNY_COUNT + 1,
    textureLoaded: running.resourceLoads === 1,
    exactPositionBatch:
      running.maxPositionMutationBatch === BUNNY_COUNT &&
      running.lastPositionMutationBatch === BUNNY_COUNT,
    boundedPhaseCrossings:
      processDelta >= 20 && crossingDelta >= processDelta - 2 && crossingDelta <= processDelta + 2,
    timingSamples: running.processTiming.count >= 120 && running.applyTiming.count >= 120,
    fixedBuffer: running.commandBufferGrowths === 0,
    removedAndFreed: removed.objectFrees === 1 && removed.liveBrowserHandles === BUNNY_COUNT,
    generationAdvanced:
      replaced.objectConstructions === BUNNY_COUNT + 1 &&
      replaced.liveBrowserHandles === BUNNY_COUNT + 1 &&
      replaced.objectHandleGenerationAdvanced === true &&
      staleHandleFailed,
    finishSignal:
      finished.signalEmits === 1 &&
      finished.lastSignalName === "benchmark_finished" &&
      finished.lastSignalValue === BUNNY_COUNT,
    deterministicTeardown:
      finished.resourceReleases === 1 &&
      finished.objectFrees === BUNNY_COUNT + 1 &&
      finished.liveBrowserHandles === 0,
  };

  const boundaryErrors = [];
  for (const [label, snap] of [["running", running], ["removed", removed], ["replaced", replaced], ["finished", finished]]) {
    if (snap.callbackErrors !== 0) boundaryErrors.push(`${label}.callbackErrors=${snap.callbackErrors}`);
  }

  return {
    protocolVersion,
    startup: {
      loaded: ready,
      outcome: ready ? "ready" : "failed",
      durationMs: startupDurationMs,
    },
    checks,
    handles: {
      liveAfterGameplay: running.liveBrowserHandles,
      liveAfterTeardown: finished.liveBrowserHandles,
      staleRejected: staleHandleFailed ? 1 : 0,
    },
    crossings: {
      kotlinToGodotCalls: running.kotlinToGodotCalls,
      maxPositionMutationBatch: running.maxPositionMutationBatch,
      processDelta,
    },
    callbacks: {
      finishSignalEmits: finished.signalEmits,
    },
    connections: {
      afterTeardownLiveHandles: finished.liveBrowserHandles,
    },
    scheduler: {
      commandBufferGrowths: running.commandBufferGrowths,
    },
    teardown: {
      outcome: checks.deterministicTeardown ? "clean" : "incomplete",
      ownerRegistriesToBaseline: finished.liveBrowserHandles === 0,
    },
    boundaryErrors,
  };
}

// demos/citybuilder.mjs -- Starter-Kit-City-Builder play + teardown assertions for
// the Web smoke.
//
// The builder places structures on a GridMap at the cell under the mouse ray; headless
// browsers never move the pointer, so every placement deterministically targets the cell
// under the top-left screen ray. The driver injects actions at ENGINE level (ops 86/87),
// observes the grid through the GridMap query family on the Builder's gridmap property
// handle, and proves the full save round-trip: build -> save (user://map.res, scripted
// DataMap/DataStructure resources) -> demolish -> load -> the placed cell comes back.
// smoke_teardown (method#1) releases hydrated structure assets, frees the Audio autoload
// and the scene root, draining every live handle to zero.

const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
const DEBUG = process.env.KANAMA_WEB_SMOKE_DEBUG === "1";
const START = Date.now();
const trace = (msg) => {
  if (DEBUG) process.stderr.write(`[citybuilder ${((Date.now() - START) / 1000).toFixed(1)}s] ${msg}\n`);
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
        builderHandle: bridge.cbBuilderHandle,
        smokeHandle: bridge.cbSmokeHandle,
        builderReady: classCount(".Builder"),
        viewReady: classCount(".View"),
        audioReady: classCount(".Audio"),
        smokeReady: classCount(".Smoke"),
        processCalls: bridge.processCalls ?? 0,
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

// Occupied grid cells through the GridMap string-query family (opcode 208) on the
// Builder's gridmap property handle (property#5).
async function usedCells(evaluate) {
  try {
    return await evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      const grid = Number(bridge.api.kanamaWebGetPackedProperty(bridge.cbBuilderHandle, 5));
      if (!grid) return null;
      const packed = bridge.immediateStringQuery(208, grid, "");
      const cells = packed === "" ? [] : packed.split("\\u001f");
      const items = cells.map((cell) => {
        const item = bridge.immediateObjectQuery(204, grid, cell.replaceAll(",", "\\u001f"));
        return { cell, item };
      });
      return { count: cells.length, items };
    })()`);
  } catch {
    return null;
  }
}

// The selector's world position (opcode 138 on the Builder's selector property handle):
// it lerps toward the mouse-ray cell every process tick.
async function selectorPosition(evaluate) {
  try {
    return await evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      const selector = Number(bridge.api.kanamaWebGetPackedProperty(bridge.cbBuilderHandle, 2));
      if (!selector) return null;
      const x = bridge.immediateNoArgsVector3X(138, selector);
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
      peak.processCalls = Math.max(peak.processCalls, snap.processCalls);
      peak.appliedCommands = Math.max(peak.appliedCommands, snap.appliedCommands);
      peak.maxLiveHandles = Math.max(peak.maxLiveHandles, snap.maxLiveHandles);
      peak.crossings = Math.max(peak.crossings, snap.crossings);
      peak.callbackErrors = Math.max(peak.callbackErrors, snap.callbackErrors);
      last = snap;
      trace(`process=${snap.processCalls} applied=${snap.appliedCommands} live=${snap.liveHandles} errs=${snap.callbackErrors}`);
      if (predicate && predicate(snap, peak)) break;
    }
    await delay(150);
  }
  return { last, peak };
}

export async function runCitybuilder({ url, evaluate, navigate, deadline }) {
  // Engine-level action injection (ops 86/87) instead of browser key events: the first
  // browser input gesture stalls the headless-Firefox main thread for the whole hold
  // (audio-context resume), freezing gameplay under it.
  const press = (action) =>
    evaluate(
      `globalThis.KanamaWebBridge.immediateObjectQuery(86, globalThis.KanamaWebBridge.cbSmokeHandle, "${action}"); true`,
    );
  const release = (action) =>
    evaluate(
      `globalThis.KanamaWebBridge.immediateObjectQuery(87, globalThis.KanamaWebBridge.cbSmokeHandle, "${action}"); true`,
    );
  const tap = async (action) => {
    await press(action);
    await delay(250);
    await release(action);
    await delay(250);
  };

  const startupStart = Date.now();
  trace("navigate");
  await navigate(`${url}?citybuilder=${Date.now()}`);

  // Generous first-load window: startup itself exercises the new families (SceneState
  // mesh extraction + duplication into the runtime MeshLibrary for all 13 structures).
  const readyDeadline = Math.min(deadline, Date.now() + 120_000);
  let ready = null;
  while (Date.now() < readyDeadline) {
    const snap = await snapshot(evaluate);
    if (
      snap &&
      snap.mode === "citybuilder" &&
      snap.protocol > 0 &&
      snap.builderReady >= 1 &&
      snap.viewReady >= 1 &&
      snap.audioReady >= 1 &&
      snap.smokeReady >= 1 &&
      snap.builderHandle > 0 &&
      snap.smokeHandle > 0
    ) {
      ready = snap;
      break;
    }
    await delay(250);
  }
  if (!ready) throw new Error("Kotlin/Wasm City-Builder did not become ready");
  const startupDurationMs = Date.now() - startupStart;
  trace(`ready: builderHandle=${ready.builderHandle} protocol=${ready.protocol}`);

  // Let process ticks flow before touching the grid (the selector needs a mouse-ray
  // sample; the first ticks also settle the preview model instantiation).
  const settledStart = await observe(
    evaluate,
    { ...seedFrom(ready), callbackErrors: 0 },
    6_000,
    deadline,
    (snap) => snap.processCalls >= ready.processCalls + 30,
  );
  const baseline = settledStart.last ?? ready;
  const baselineCells = await usedCells(evaluate);
  if (!baselineCells) throw new Error("could not read the GridMap used cells");
  const selectorStart = await selectorPosition(evaluate);
  trace(`baseline: cells=${baselineCells.count} selector=${JSON.stringify(selectorStart)}`);

  // Warm-up placement: the FIRST structure render compiles its materials on software GL,
  // which can freeze the main thread for seconds. Place, wait for process to flow again,
  // then demolish so the measured sequence starts from a clean grid.
  await tap("build");
  await observe(
    evaluate,
    { ...seedFrom(baseline), callbackErrors: 0 },
    12_000,
    deadline,
    (snap, p) => snap.processCalls >= p.processCalls && snap.processCalls > baseline.processCalls + 60,
  );
  const warmCells = await usedCells(evaluate);
  trace(`warm-up build: cells=${warmCells?.count}`);
  if (!warmCells || warmCells.count !== baselineCells.count + 1) {
    throw new Error(`warm-up build did not place a cell (before=${baselineCells.count} after=${warmCells?.count})`);
  }

  // Toggle to the next structure and rotate the selector, then rebuild on the same cell:
  // same cell count, different item index proves toggle + placement replacement.
  await tap("structure_next");
  await tap("rotate");
  await tap("build");
  const toggledCells = await usedCells(evaluate);
  const warmItem = warmCells.items[0]?.item ?? -1;
  const toggledItem = toggledCells?.items[0]?.item ?? -1;
  trace(`toggled build: cells=${toggledCells?.count} item ${warmItem} -> ${toggledItem}`);

  // Save the one-cell map: engine-side ResourceSaver.save on the scripted DataMap after
  // pulling the current Kotlin property values (newScriptInstance -> DataStructure).
  await tap("save");
  const savedCells = await usedCells(evaluate);

  // Demolish the cell, then load user://map.res back: the scripted resources hydrate
  // and the placed cell returns.
  await tap("demolish");
  const demolishedCells = await usedCells(evaluate);
  trace(`demolished: cells=${demolishedCells?.count}`);
  await tap("load");
  const gameplay = await observe(
    evaluate,
    { ...seedFrom(baseline), callbackErrors: 0 },
    3_000,
    deadline,
    null,
  );
  const loadedCells = await usedCells(evaluate);
  const selectorRun = await selectorPosition(evaluate);
  const peak = gameplay.peak;
  const atPeak = gameplay.last ?? baseline;
  const selectorDisplacement =
    selectorStart && selectorRun
      ? Math.hypot(selectorRun.x - selectorStart.x, selectorRun.z - selectorStart.z)
      : 0;
  trace(`loaded: cells=${loadedCells?.count} selectorMoved=${selectorDisplacement.toFixed(2)}`);

  // Full teardown: smoke_teardown (method#1) releases hydrated structure assets, frees
  // the Audio autoload and the scene root; every node exits and releases its handles.
  trace("smoke_teardown");
  await evaluate(
    "globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.cbSmokeHandle, 1); true",
  );
  const teardown = await observe(evaluate, peak, 12_000, deadline, (snap) => snap.liveHandles === 0);
  const settled = teardown.last ?? atPeak;
  trace(`teardown: live=${settled.liveHandles} callbacks=${settled.callbacks} errs=${settled.callbackErrors}`);
  if (DEBUG && settled.liveHandles !== 0) {
    const liveKinds = await evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      const counts = {};
      for (let index = 1; index < bridge.browserHandleSlots.length; index += 1) {
        const slot = bridge.browserHandleSlots[index];
        if (slot.live) counts[slot.kind] = (counts[slot.kind] ?? 0) + 1;
      }
      return counts;
    })()`);
    trace(`surviving handle kinds: ${JSON.stringify(liveKinds)}`);
  }

  const protocolVersion = ready.protocol;
  const checks = {
    modeCitybuilder: ready.mode === "citybuilder",
    protocol16: protocolVersion === 16,
    sceneScriptsReady:
      ready.builderReady >= 1 && ready.viewReady >= 1 && ready.audioReady >= 1 && ready.smokeReady >= 1,
    // Injected build placed a structure on the grid at the mouse-ray cell.
    buildPlacedCell: warmCells.count === baselineCells.count + 1,
    // structure_next + rebuild replaced the cell's item (same cell, new index).
    toggleReplacedItem:
      (toggledCells?.count ?? 0) === warmCells.count && toggledItem !== warmItem && toggledItem >= 0,
    // demolish cleared the cell; loading user://map.res brought it back with its item —
    // the scripted-resource save round-trip (newScriptInstance -> ResourceSaver.save ->
    // ResourceLoader.load -> hydration) survived.
    demolishClearedCell: (demolishedCells?.count ?? -1) === warmCells.count - 1,
    saveLoadRoundTrip:
      (savedCells?.count ?? 0) === (toggledCells?.count ?? -1) &&
      (loadedCells?.count ?? 0) === (savedCells?.count ?? -1) &&
      (loadedCells?.items[0]?.item ?? -2) === toggledItem,
    processFramesAdvanced: peak.processCalls >= baseline.processCalls + 40,
    gameplayCommandsApplied: peak.appliedCommands > baseline.appliedCommands,
    crossingsAdvanced: peak.crossings > baseline.crossings,
    fullTeardownToZero: settled.liveHandles === 0,
    noCallbackFaults:
      peak.callbackErrors === 0 && settled.callbackErrors === 0 && settled.failure === null,
  };

  const boundaryErrors = [];
  if (peak.callbackErrors !== 0) boundaryErrors.push(`callbackErrors=${peak.callbackErrors}`);
  if (settled.failure !== null) boundaryErrors.push(`failure: ${settled.failure}`);

  return {
    protocolVersion,
    startup: {
      loaded: ready.mode === "citybuilder",
      outcome: ready.mode === "citybuilder" ? "ready" : "failed",
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
      selectorDisplacement: Number(selectorDisplacement.toFixed(3)),
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
    processCalls: snap.processCalls,
    appliedCommands: snap.appliedCommands,
    maxLiveHandles: snap.liveHandles,
    crossings: snap.crossings,
  };
}

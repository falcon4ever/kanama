// demos/match3.mjs -- Match3 play + teardown assertions for the Web export smoke.
//
// Ported from the validated Task-57 group-8 integration driver, but decoupled
// from any one browser: the caller injects `call` (CDP-style command) and
// `evaluate` (page expression -> value). It returns the demoResult contract in
// envelope.mjs: protocol, startup, checks, handles, crossings, callbacks,
// connections, scheduler, teardown, boundaryErrors.
//
// A run is never green from page load alone: it plays a runtime-selected legal
// swap, asserts match/collapse/refill + audiovisual feedback + bounded
// crossings, then tears the scene down twice to baseline and proves stale
// handles are rejected.

import { resolveMethodId } from "../envelope.mjs";

const OFFSET = 68;
const BOARD = 8;
const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
const DEBUG = process.env.KANAMA_WEB_SMOKE_DEBUG === "1";
const trace = (msg) => {
  if (DEBUG) process.stderr.write(`[match3 ${((Date.now() - START) / 1000).toFixed(1)}s] ${msg}\n`);
};
const START = Date.now();

async function snapshot(evaluate) {
  return evaluate(`(() => {
    const bridge = globalThis.KanamaWebBridge;
    const classCount = (suffix) => {
      const entry = Object.entries(bridge.match3ReadyByClass).find(([n]) => n.endsWith(suffix));
      return entry?.[1] ?? 0;
    };
    const tileGrid = [...bridge.match3TileTypeByHandle.entries()].map(([handle, type]) => {
      const position = bridge.match3NodePositions.get(handle);
      return position
        ? { handle, type, x: Math.round(position.x / ${OFFSET}), y: Math.round(position.y / ${OFFSET}) }
        : { handle, type, x: null, y: null };
    }).sort((l, r) => (l.y - r.y) || (l.x - r.x));
    return {
      protocol: bridge.results.protocolVersion,
      mainHandle: bridge.match3MainHandle,
      audioHandle: bridge.match3AudioHandle,
      tileGrid,
      tileReady: classCount(".Tile"),
      particleReady: classCount(".Particles"),
      tileFrees: bridge.match3TileScriptFrees,
      particleFrees: bridge.match3ParticleFrees,
      packedSceneInstantiations: bridge.match3PackedSceneInstantiations,
      addChildCommands: bridge.match3AddChildCommands,
      positionTweenTargets: bridge.match3PositionTweenTargets,
      playCommands: bridge.match3AudioPlayCommands,
      playersConstructed: bridge.match3AudioPlayersConstructed,
      audioResourceLoads: bridge.match3AudioResourceLoads,
      audioResourceLoadFailures: bridge.match3AudioResourceLoadFailures,
      sceneTreeHandles: [...bridge.sceneTreeHandlesByOwner.entries()],
      sceneTreeHandlesCreated: bridge.match3SceneTreeHandlesCreated,
      sceneTreeHandleReuses: bridge.match3SceneTreeHandleReuses,
      liveHandles: bridge.liveBrowserHandleCount,
      callbacks: bridge.api.kanamaWebPendingSignalCallbackCount(),
      pending: bridge.api.kanamaWebPendingCoroutineCount(),
      jobs: bridge.api.kanamaWebRegisteredCoroutineJobCount(),
      tweensCreated: bridge.match3TweensCreated,
      tweensReleased: bridge.match3TweensReleased,
      callbackErrors: bridge.callbackErrors,
      failure: globalThis.KanamaWebFailure?.stack ?? globalThis.KanamaWebFailure?.message ?? null,
    };
  })()`);
}

async function waitUntil(evaluate, predicate, timeoutMs) {
  const deadline = Date.now() + timeoutMs;
  let current = null;
  while (Date.now() < deadline) {
    current = await snapshot(evaluate);
    if (predicate(current)) return current;
    await delay(25);
  }
  return current;
}

function matrixFrom(tileGrid) {
  const grid = Array.from({ length: BOARD }, () => Array(BOARD).fill(null));
  for (const tile of tileGrid) {
    if (Number.isInteger(tile.x) && Number.isInteger(tile.y) &&
        tile.x >= 0 && tile.x < BOARD && tile.y >= 0 && tile.y < BOARD) {
      if (grid[tile.x][tile.y] !== null) throw new Error(`Duplicate tile ${tile.x},${tile.y}`);
      grid[tile.x][tile.y] = tile.type;
    }
  }
  if (grid.some((col) => col.some((v) => v === null))) {
    throw new Error(`Incomplete Match3 grid telemetry: ${JSON.stringify(tileGrid)}`);
  }
  return grid;
}

function hasMatch(grid) {
  for (let y = 0; y < BOARD; y += 1)
    for (let x = 0; x < BOARD - 2; x += 1)
      if (grid[x][y] === grid[x + 1][y] && grid[x][y] === grid[x + 2][y]) return true;
  for (let x = 0; x < BOARD; x += 1)
    for (let y = 0; y < BOARD - 2; y += 1)
      if (grid[x][y] === grid[x][y + 1] && grid[x][y] === grid[x][y + 2]) return true;
  return false;
}

function findLegalSwap(tileGrid) {
  const grid = matrixFrom(tileGrid);
  if (hasMatch(grid)) throw new Error("Board was not settled before legal-swap search");
  for (let y = 0; y < BOARD; y += 1)
    for (let x = 0; x < BOARD; x += 1)
      for (const [dx, dy] of [[1, 0], [0, 1]]) {
        const ox = x + dx;
        const oy = y + dy;
        if (ox >= BOARD || oy >= BOARD) continue;
        [grid[x][y], grid[ox][oy]] = [grid[ox][oy], grid[x][y]];
        const legal = hasMatch(grid);
        [grid[x][y], grid[ox][oy]] = [grid[ox][oy], grid[x][y]];
        if (legal) return { x, y, dx, dy };
      }
  return null;
}

async function drag(pointer, evaluate, { x, y, dx, dy }) {
  // Board -> CSS client-pixel geometry is browser-agnostic; each driver's
  // pointer() handles the protocol.
  //
  // Godot's coordinate space here is CSS pixels, NOT the canvas backing store.
  // Main.center_grid_on_screen() centres the board on get_viewport().
  // get_visible_rect(), whose size is the canvas's CSS size, and the engine
  // reports mouse positions in those same units (verified against
  // CanvasItem.get_local_mouse_position: a click at CSS 538,186 reads back as
  // 538,186 at devicePixelRatio 2). Deriving the board origin from
  // canvas.width instead only agrees at devicePixelRatio 1 -- which is why this
  // worked in headless Chrome/Firefox and put Safari's press two tiles off the
  // intended cell with half the intended drag distance (task 69).
  const geometry = await evaluate(`(() => {
    const canvas = document.querySelector("canvas");
    const rect = canvas.getBoundingClientRect();
    const boardX = rect.width / 2 - ((${BOARD} - 1) * ${OFFSET}) / 2;
    const boardY = rect.height / 2 - ((${BOARD} - 1) * ${OFFSET}) / 2;
    const toClient = (gx, gy) => ({ x: rect.left + gx, y: rect.top + gy });
    return {
      press: toClient(boardX + ${x} * ${OFFSET}, boardY + ${y} * ${OFFSET}),
      release: toClient(boardX + (${x} + ${dx}) * ${OFFSET}, boardY + (${y} + ${dy}) * ${OFFSET}),
    };
  })()`);
  trace(`drag swap=${JSON.stringify({ x, y, dx, dy })} press=${JSON.stringify(geometry.press)} release=${JSON.stringify(geometry.release)}`);
  await pointer(geometry.press, geometry.release);
  return geometry;
}

async function navigateAndSettle(navigate, evaluate, url, label) {
  trace(`navigate ${label}`);
  await navigate(`${url}?run=${label}&cache=${Date.now()}`);
  const deadline = Date.now() + 30_000;
  let board = null;
  while (Date.now() < deadline) {
    board = await evaluate("globalThis.KanamaWebMatch3Results ?? null");
    if (board?.pass === true) break;
    await delay(50);
  }
  if (board?.pass !== true) throw new Error(`Match3 ${label} board did not become ready`);
  trace(`${label} board ready; settling`);
  const settled = await waitUntil(
    evaluate,
    (v) => v.tileGrid.length === 64 && v.pending === 0 && v.jobs === 0 &&
           v.callbacks === 12 && v.tweensCreated === v.tweensReleased,
    30_000,
  );
  trace(`${label} settled: tiles=${settled.tileGrid.length} pending=${settled.pending} jobs=${settled.jobs} callbacks=${settled.callbacks} tweens=${settled.tweensCreated}/${settled.tweensReleased}`);
  matrixFrom(settled.tileGrid);
  return { board, settled };
}

async function teardownCurrentRun(evaluate) {
  const beforeMain = await snapshot(evaluate);
  const sceneTreeProbe = await evaluate(`globalThis.KanamaWebBridge.invoke(${beforeMain.mainHandle}, "g8_scene_tree", "g8_scene_tree", () => globalThis.KanamaWebBridge.api.kanamaWebMatch3Group8SceneTreeProbe(${beforeMain.mainHandle}), 0)`);
  const sceneTreeHandle = await evaluate("globalThis.KanamaWebBridge.api.kanamaWebMatch3Group8SceneTreeHandle()");
  const afterSceneTree = await snapshot(evaluate);
  const mainTeardownResult = await evaluate(`globalThis.KanamaWebBridge.invoke(${beforeMain.mainHandle}, "g8_main_teardown", "g8_main_teardown", () => globalThis.KanamaWebBridge.api.kanamaWebMatch3Group8MainTeardownProbe(${beforeMain.mainHandle}), 0)`);
  const afterMain = await waitUntil(
    evaluate,
    (v) => v.mainHandle === 0 && v.tileGrid.length === 0 && v.pending === 0 && v.jobs === 0 &&
           v.tweensCreated === v.tweensReleased,
    15_000,
  );
  const sceneTreeStaleProbe = await evaluate(`globalThis.KanamaWebBridge.api.kanamaWebMatch3Group8SceneTreeStaleProbe(${sceneTreeHandle})`);
  const audioTeardownResult = await evaluate(`globalThis.KanamaWebBridge.invoke(${afterMain.audioHandle}, "g7_audio_teardown", "g7_audio_teardown", () => globalThis.KanamaWebBridge.api.kanamaWebMatch3Group7AudioOwnerTeardownProbe(${afterMain.audioHandle}), 0)`);
  const afterAudio = await waitUntil(
    evaluate,
    (v) => v.audioHandle === 0 && v.liveHandles === 0 && v.callbacks === 0 && v.pending === 0 && v.jobs === 0,
    15_000,
  );
  return { beforeMain, sceneTreeProbe, sceneTreeHandle, afterSceneTree, mainTeardownResult, afterMain, sceneTreeStaleProbe, audioTeardownResult, afterAudio };
}

function teardownClean(t) {
  return (
    t.sceneTreeProbe === 3 && t.sceneTreeHandle > 0 &&
    t.afterSceneTree.sceneTreeHandles.length === 1 &&
    t.mainTeardownResult === 1 && t.afterMain.mainHandle === 0 &&
    t.afterMain.tileGrid.length === 0 && t.afterMain.sceneTreeHandles.length === 0 &&
    t.sceneTreeStaleProbe === 1 && t.audioTeardownResult === 1 &&
    t.afterAudio.audioHandle === 0 && t.afterAudio.liveHandles === 0 &&
    t.afterAudio.callbacks === 0 && t.afterAudio.pending === 0 && t.afterAudio.jobs === 0
  );
}

export async function runMatch3({ url, evaluate, navigate, pointer, exportDir }) {
  const startupStart = Date.now();
  const firstRun = await navigateAndSettle(navigate, evaluate, url, "first");
  const startupDurationMs = Date.now() - startupStart;

  // Task 80 slice 6: read the demo's hydrated-state dump WHILE THE BOARD IS ALIVE. Run
  // after teardown it returns null -- the bridge zeroes match3MainHandle when Main frees,
  // and a null payload would look like "the probe is broken" rather than "asked too late".
  // Ids are resolved from the export manifest, never hardcoded: adding a
  // @RegisterFunction renumbers the rest (this probe took id 1 and pushed the existing
  // two down), so a hardcoded call would dispatch a different method and still "work".
  const probeId = resolveMethodId(exportDir, "match3.Main", "differential_probe");
  const differentialPayload =
    probeId === null
      ? null
      : await evaluate(
          `(() => { const b = globalThis.KanamaWebBridge;` +
            ` if (!b || !b.match3MainHandle) return null;` +
            ` return String(b.callPacked(b.match3MainHandle, ${probeId})); })()`,
        );

  const legalSwap = findLegalSwap(firstRun.settled.tileGrid);
  if (!legalSwap) throw new Error("Settled Match3 board has no legal swap");
  const beforeSwap = await snapshot(evaluate);
  await drag(pointer, evaluate, legalSwap);
  const afterSwap = await waitUntil(
    evaluate,
    (v) => v.tileFrees > beforeSwap.tileFrees && v.tileGrid.length === 64 &&
           v.pending === 0 && v.jobs === 0 && v.callbacks === 12 &&
           v.tweensCreated === v.tweensReleased,
    30_000,
  );
  matrixFrom(afterSwap.tileGrid);

  const firstTeardown = await teardownCurrentRun(evaluate);
  const secondRun = await navigateAndSettle(navigate, evaluate, url, "restart");
  const secondTeardown = await teardownCurrentRun(evaluate);

  const tileFreeDelta = afterSwap.tileFrees - beforeSwap.tileFrees;
  const tileReadyDelta = afterSwap.tileReady - beforeSwap.tileReady;
  const particleReadyDelta = afterSwap.particleReady - beforeSwap.particleReady;
  const particleFreeDelta = afterSwap.particleFrees - beforeSwap.particleFrees;
  const packedDelta = afterSwap.packedSceneInstantiations - beforeSwap.packedSceneInstantiations;
  const addDelta = afterSwap.addChildCommands - beforeSwap.addChildCommands;
  const positionTweenDelta = afterSwap.positionTweenTargets - beforeSwap.positionTweenTargets;

  const checks = {
    exactOriginalBoard:
      firstRun.board.pass === true && firstRun.settled.tileGrid.length === 64 &&
      firstRun.settled.tileReady >= 64 && firstRun.settled.playersConstructed === 12,
    settledBoardHasNoExistingMatch: !hasMatch(matrixFrom(firstRun.settled.tileGrid)),
    runtimeSelectedLegalSwap: legalSwap !== null,
    matchCollapseRefillCompleted:
      tileFreeDelta >= 3 && tileReadyDelta === tileFreeDelta &&
      particleReadyDelta === tileFreeDelta && particleFreeDelta === particleReadyDelta &&
      packedDelta === tileReadyDelta + particleReadyDelta && addDelta === packedDelta &&
      positionTweenDelta >= tileReadyDelta + 2 && afterSwap.tileGrid.length === 64,
    audiovisualFeedback: afterSwap.playCommands > beforeSwap.playCommands && particleReadyDelta > 0,
    // Bridge-level RefCounted ownership (Task 60a): Match3 audio loads a stream via
    // ResourceLoader (setStreamFromPath), hands it to a player, then releases the temp
    // wrapper. Ownership holds iff every load-and-handoff succeeded (the handed-off
    // resource survived its temp release), the streams actually played, and no resource
    // handle leaked at teardown.
    resourceOwnershipHandoffSurvives:
      afterSwap.audioResourceLoads > 0 &&
      afterSwap.audioResourceLoadFailures === 0 &&
      afterSwap.playCommands > beforeSwap.playCommands &&
      secondTeardown.afterAudio.liveHandles === 0,
    boundedAfterGameplay:
      afterSwap.liveHandles === beforeSwap.liveHandles && afterSwap.callbacks === 12 &&
      afterSwap.pending === 0 && afterSwap.jobs === 0,
    firstFullTeardown: teardownClean(firstTeardown),
    restartRecreatesOriginalShape:
      secondRun.board.pass === true && secondRun.settled.tileGrid.length === 64 &&
      secondRun.settled.playersConstructed === 12 && secondRun.settled.callbacks === 12,
    repeatedFullTeardown: teardownClean(secondTeardown),
  };

  const boundaryErrors = [];
  if (secondTeardown.afterAudio.callbackErrors !== 0) boundaryErrors.push("callbackErrors > 0");
  if (secondTeardown.afterAudio.failure !== null) boundaryErrors.push(`failure: ${secondTeardown.afterAudio.failure}`);

  const staleRejected =
    (firstTeardown.sceneTreeStaleProbe === 1 ? 1 : 0) +
    (secondTeardown.sceneTreeStaleProbe === 1 ? 1 : 0);

  // Task 80 slice 6: read the demo's own hydrated-state dump. The SAME Kotlin runs on
  // desktop, which prints it to stdout under the smoke env var, so the two payloads must
  // be byte-identical; differential_diff.py compares them. The method id is resolved from
  // the export manifest rather than hardcoded -- ids are positional, so adding a
  // @RegisterFunction above this one would silently renumber it and a hardcoded call
  // would dispatch a different method while still appearing to work.
  return {
    differential:
      probeId === null
        ? { available: false, reason: "differential_probe not found in the export manifest" }
        : { available: true, script: "match3.Main", payload: differentialPayload },
    protocolVersion: firstRun.settled.protocol,
    startup: {
      loaded: firstRun.board.pass === true,
      outcome: firstRun.board.pass === true ? "ready" : "failed",
      durationMs: startupDurationMs,
    },
    checks,
    handles: {
      liveAfterGameplay: afterSwap.liveHandles,
      liveAfterTeardown: secondTeardown.afterAudio.liveHandles,
      staleRejected,
    },
    crossings: {
      packedSceneInstantiations: afterSwap.packedSceneInstantiations,
      addChildCommands: afterSwap.addChildCommands,
      positionTweenTargets: afterSwap.positionTweenTargets,
      audioPlayCommands: afterSwap.playCommands,
    },
    callbacks: {
      settledDuringGameplay: afterSwap.callbacks,
      afterTeardown: secondTeardown.afterAudio.callbacks,
    },
    connections: {
      signalCallbacksSettled: afterSwap.callbacks,
      afterTeardown: secondTeardown.afterAudio.callbacks,
    },
    scheduler: {
      pendingCoroutines: secondTeardown.afterAudio.pending,
      registeredJobs: secondTeardown.afterAudio.jobs,
    },
    teardown: {
      outcome: teardownClean(firstTeardown) && teardownClean(secondTeardown) ? "clean" : "incomplete",
      ownerRegistriesToBaseline:
        secondTeardown.afterAudio.liveHandles === 0 &&
        secondTeardown.afterAudio.callbacks === 0 &&
        secondTeardown.afterAudio.pending === 0 &&
        secondTeardown.afterAudio.jobs === 0,
    },
    boundaryErrors,
  };
}

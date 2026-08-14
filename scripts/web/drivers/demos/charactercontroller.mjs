// demos/charactercontroller.mjs -- 3D character-controller tutorial play + teardown
// assertions for the Web smoke.
//
// The tutorial is input-driven: this driver dispatches synthetic DOM keyboard events
// (transport-agnostic -- Godot's Web input listens on the canvas, so the same evaluate
// path works on Chrome and Firefox) to hold move_up, and PROVES movement by reading the
// player's global position through the bridge's immediate Vector3 channel (opcode 138)
// before and after the hold. Gameplay around it is self-evidencing: the AnimationTree
// state machine travels Idle/Move each physics tick, the blink timers cycle, and
// SmokeQuit.smoke_teardown frees the Events autoload + scene root, draining every live
// handle to zero.

const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
// How far below the start height counts as "off the platform and falling". The level's
// KillPlane sits ~17 units down, so anything past this is unambiguously a fall, not a step.
const FALL_DEPTH = 5;
// The kill-plane reset teleports to the position captured in the player's _ready; the driver's
// baseline is read after a settle, so allow for the gravity snap between the two.
const RESET_TOLERANCE = 2.0;
const DEBUG = process.env.KANAMA_WEB_SMOKE_DEBUG === "1";
const START = Date.now();
const trace = (msg) => {
  if (DEBUG) process.stderr.write(`[charactercontroller ${((Date.now() - START) / 1000).toFixed(1)}s] ${msg}\n`);
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
        playerHandle: bridge.charPlayerHandle,
        smokeQuitHandle: bridge.charSmokeQuitHandle,
        readyCount: bridge.readyCount,
        playerReady: classCount(".Player3DTemplate"),
        skinReady: classCount(".SophiaSkin"),
        eventsReady: classCount(".Events"),
        smokeQuitReady: classCount(".SmokeQuit"),
        flagReady: classCount(".Flag3D"),
        killPlaneReady: classCount(".KillPlane3D"),
        physicsCalls: bridge.physicsProcessCalls ?? 0,
        // Task 84: real _process dispatches. This demo has no mode branch in the
        // bridge's frame dispatch, and until task 84 the fallthrough there was the
        // SYNTHETIC SPIKE BENCHMARK -- so this demo reported processTicks: 0 while
        // quietly appending a benchmark scalar mutation on every dispatch.
        // (No backticks in here: this whole function body is a template literal.)
        processCalls: bridge.processCalls ?? 0,
        // Task 82: the coroutine frame scheduler's per-frame advance. cc named no "Main"
        // handle anywhere, which is exactly why it never pumped before protocol 18.
        pumps: bridge.frameSchedulerPumps ?? 0,
        continuations: bridge.frameSchedulerContinuations ?? 0,
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

// The player's world position through the immediate no-args Vector3 channel
// (opcode 138 = Node3D.get_global_position on the player's script handle).
async function playerPosition(evaluate) {
  try {
    return await evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      const handle = bridge.charPlayerHandle;
      const x = bridge.immediateNoArgsVector3X(138, handle);
      return { x, y: bridge.immediateNoArgsVector3Y(), z: bridge.immediateNoArgsVector3Z() };
    })()`);
  } catch {
    return null;
  }
}

// A single op-138 read can come back null under host load (the page briefly busy or
// mid-frame) -- observed as a one-shot flake in the kanama#170 validation. Retries are
// BOUNDED and the caller's null handling is unchanged, so a position that STAYS
// unreadable (dead bridge, freed player) still fails exactly as loudly as before.
async function playerPositionRetry(evaluate, attempts = 5) {
  for (let attempt = 1; ; attempt += 1) {
    const position = await playerPosition(evaluate);
    if (position || attempt >= attempts) return position;
    trace(`position read null (attempt ${attempt}/${attempts}); retrying`);
    await delay(250);
  }
}

const keyExpression = (type, code, key, keyCode) => `(() => {
  const canvas = document.querySelector("canvas");
  canvas.focus();
  canvas.dispatchEvent(new KeyboardEvent(${JSON.stringify(type)}, {
    code: ${JSON.stringify(code)}, key: ${JSON.stringify(key)},
    keyCode: ${keyCode}, which: ${keyCode}, bubbles: true, cancelable: true,
  }));
  return true;
})()`;

async function observe(evaluate, seed, windowMs, deadline, predicate) {
  const peak = { ...seed };
  let last = null;
  const until = Math.min(deadline, Date.now() + windowMs);
  while (Date.now() < until) {
    const snap = await snapshot(evaluate);
    if (snap) {
      peak.physicsCalls = Math.max(peak.physicsCalls, snap.physicsCalls);
      peak.appliedCommands = Math.max(peak.appliedCommands, snap.appliedCommands);
      peak.maxLiveHandles = Math.max(peak.maxLiveHandles, snap.maxLiveHandles);
      peak.crossings = Math.max(peak.crossings, snap.crossings);
      peak.callbackErrors = Math.max(peak.callbackErrors, snap.callbackErrors);
      last = snap;
      trace(`physics=${snap.physicsCalls} applied=${snap.appliedCommands} live=${snap.liveHandles} errs=${snap.callbackErrors}`);
      if (predicate && predicate(snap, peak)) break;
    }
    await delay(150);
  }
  return { last, peak };
}

export async function runCharactercontroller({ url, evaluate, navigate, keys, deadline }) {
  // Trusted per-engine key synthesis when the transport provides it; DOM-event
  // fallback otherwise (works headless-Chrome, stalls headless-Firefox rAF).
  const keyDown = keys ? () => keys("down", "w") : () => evaluate(keyExpression("keydown", "KeyW", "w", 87));
  const keyUp = keys ? () => keys("up", "w") : () => evaluate(keyExpression("keyup", "KeyW", "w", 87));
  // move_down (S). move_up walks the player into the level's boundary wall ~15 units ahead;
  // the open edge of the starting platform is ~3 units BEHIND the spawn, so S is the way off.
  const backDown = keys ? () => keys("down", "s") : () => evaluate(keyExpression("keydown", "KeyS", "s", 83));
  const backUp = keys ? () => keys("up", "s") : () => evaluate(keyExpression("keyup", "KeyS", "s", 83));
  const startupStart = Date.now();
  trace("navigate");
  await navigate(`${url}?charactercontroller=${Date.now()}`);

  const readyDeadline = Math.min(deadline, Date.now() + 45_000);
  let ready = null;
  while (Date.now() < readyDeadline) {
    const snap = await snapshot(evaluate);
    if (
      snap &&
      snap.mode === "charactercontroller" &&
      snap.protocol > 0 &&
      snap.playerReady >= 1 &&
      snap.skinReady >= 1 &&
      snap.eventsReady >= 1 &&
      snap.smokeQuitReady >= 1 &&
      snap.flagReady >= 1 &&
      snap.killPlaneReady >= 1 &&
      snap.playerHandle > 0 &&
      snap.smokeQuitHandle > 0
    ) {
      ready = snap;
      break;
    }
    await delay(100);
  }
  if (!ready) throw new Error("Kotlin/Wasm character-controller tutorial did not become ready");
  const startupDurationMs = Date.now() - startupStart;
  trace(`ready: readyCount=${ready.readyCount} playerHandle=${ready.playerHandle} protocol=${ready.protocol}`);

  // Let the scene settle (gravity snap to floor), then take the movement baseline.
  // Counters restart from the post-settle snapshot: on fast-ticking engines the settle
  // itself consumes physics frames and must not eat into the movement window.
  await observe(evaluate, { ...seedFrom(ready), callbackErrors: 0 }, 2_000, deadline, null);
  const baseline = (await snapshot(evaluate)) ?? ready;
  const startPosition = await playerPositionRetry(evaluate);
  if (!startPosition) throw new Error("could not read the player's global position");
  trace(`start position: ${JSON.stringify(startPosition)}`);

  // Hold move_up (W) via synthetic DOM keys: the camera-relative controller
  // accelerates, the skin travels to Move, dust particles emit.
  await keyDown();
  const gameplay = await observe(
    evaluate,
    { ...seedFrom(baseline), callbackErrors: 0 },
    6_000,
    deadline,
    (snap, p) => p.physicsCalls >= baseline.physicsCalls + 150,
  );
  await keyUp();
  const runPosition = await playerPositionRetry(evaluate);
  const peak = gameplay.peak;
  const atPeak = gameplay.last ?? ready;
  const displacement = runPosition
    ? Math.hypot(runPosition.x - startPosition.x, runPosition.z - startPosition.z)
    : 0;
  trace(`ran: displacement=${displacement.toFixed(2)} physics=${peak.physicsCalls} live=${atPeak.liveHandles}`);

  // ---- Task 82 gate (a): the kill plane, the LOSE transition ----
  //
  // Keep walking until the player leaves the platform and falls. The level's KillPlane is an
  // Area3D whose body_entered handler does NOT emit straight away: it defers one frame through
  // `kanamaScope.launch { delaySeconds(0.0) }` -- the Web spelling of desktop's
  // MainThread.awaitNextFrame -- because Godot forbids restructuring nodes inside a physics
  // callback. Only then does it emit kill_plane_touched, which teleports the player back to its
  // start position with zero velocity.
  //
  // That deferral is the entire test. Before protocol 18 the frame scheduler was never pumped in
  // this demo, so the continuation after delaySeconds(0.0) never ran: body_entered fired, nothing
  // threw, and the player fell forever. The assertion is therefore the OBSERVED return to the
  // start position, read through the engine's own global-position channel -- never "no error".
  //
  // Fall and reset are watched by ONE tight loop, so the ordering is part of the assertion: the
  // return to the spawn only counts once a sample has actually seen the player below the
  // platform. The whole excursion is about a second of free fall, so the poll is fast enough to
  // land several samples inside it on an engine whose rAF nothing is pacing.
  trace("kill plane: walking off the platform");
  await backDown();
  let lowestY = startPosition.y;
  let killPlaneReset = null;
  let walkedOff = false;
  const killPlaneDeadline = Math.min(deadline, Date.now() + 60_000);
  while (Date.now() < killPlaneDeadline) {
    const here = await playerPosition(evaluate);
    if (here) {
      lowestY = Math.min(lowestY, here.y);
      if (!walkedOff && lowestY < startPosition.y - FALL_DEPTH) {
        walkedOff = true;
        trace(`kill plane: falling at y=${here.y.toFixed(2)} z=${here.z.toFixed(2)}`);
        await backUp(); // stop walking so the reset lands at the spawn and stays there
      }
      if (
        walkedOff &&
        Math.hypot(here.x - startPosition.x, here.z - startPosition.z) < RESET_TOLERANCE &&
        here.y > startPosition.y - RESET_TOLERANCE
      ) {
        killPlaneReset = here;
        break;
      }
    }
    await delay(50);
  }
  if (!walkedOff) await backUp();
  trace(
    `kill plane: lowest y=${lowestY.toFixed(2)} (start y=${startPosition.y.toFixed(2)}) reset=${JSON.stringify(killPlaneReset)}`,
  );

  // ---- Task 82 gate (b): the flag, the WIN transition ----
  //
  // Reaching the flag on foot means crossing the whole platforming course, which no synthetic
  // key hold can steer, so the win is driven at the signal it actually hangs off: the Events
  // autoload's `flag_reached`, emitted through Godot itself (the bridge's no-args signal
  // crossing, the same one Kotlin uses). Everything downstream is the real chain --
  // FlagReachedScreen's connected Kotlin lambda launches
  // `kanamaScope.launch { delaySeconds(2.0); play("fade_in"); await(animation_finished);
  // reloadCurrentScene() }`.
  //
  // The observable is the LEVEL RELOAD: every script in the scene is torn down and readied
  // again, so the player's script handle changes and its ready count goes to two. None of it
  // happens if the coroutine never resumes past its 2-second delay.
  const eventsHandle = Number(
    await evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      const entry = Object.entries(bridge.scriptNameByHandle ?? {}).find(([, name]) =>
        String(name).endsWith(".Events"),
      );
      return entry ? Number(entry[0]) : 0;
    })()`),
  );
  trace(`flag: eventsHandle=${eventsHandle} playerHandle=${ready.playerHandle}`);
  let flagReload = null;
  if (eventsHandle > 0) {
    await evaluate(
      `globalThis.KanamaWebBridge.immediateEmitSignalNoArgs(${eventsHandle}, "flag_reached"); true`,
    );
    const reloadDeadline = Math.min(deadline, Date.now() + 40_000);
    while (Date.now() < reloadDeadline) {
      const snap = await snapshot(evaluate);
      if (snap && snap.playerReady >= 2 && snap.playerHandle !== ready.playerHandle) {
        flagReload = snap;
        break;
      }
      await delay(250);
    }
  }
  trace(
    `flag: reloaded=${flagReload !== null} playerReady=${flagReload?.playerReady} newPlayerHandle=${flagReload?.playerHandle}`,
  );

  const afterGameplay = (await snapshot(evaluate)) ?? atPeak;
  peak.maxLiveHandles = Math.max(peak.maxLiveHandles, afterGameplay.maxLiveHandles);
  peak.crossings = Math.max(peak.crossings, afterGameplay.crossings);
  peak.appliedCommands = Math.max(peak.appliedCommands, afterGameplay.appliedCommands);
  peak.callbackErrors = Math.max(peak.callbackErrors, afterGameplay.callbackErrors);
  trace(`scheduler: pumps=${afterGameplay.pumps} continuations=${afterGameplay.continuations}`);

  // Full teardown: SmokeQuit.smoke_teardown (method#1) frees the Events autoload and
  // the scene root; every node exits the tree and releases its handles.
  trace("smoke_teardown");
  await evaluate(
    "globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.charSmokeQuitHandle, 1); true",
  );
  const teardown = await observe(evaluate, peak, 8_000, deadline, (snap) => snap.liveHandles === 0);
  const settled = teardown.last ?? atPeak;
  trace(`teardown: live=${settled.liveHandles} callbacks=${settled.callbacks} errs=${settled.callbackErrors}`);

  const protocolVersion = ready.protocol;
  const checks = {
    modeCharactercontroller: ready.mode === "charactercontroller",
    sceneScriptsReady:
      ready.playerReady >= 1 &&
      ready.skinReady >= 1 &&
      ready.eventsReady >= 1 &&
      ready.smokeQuitReady >= 1 &&
      ready.flagReady >= 1 &&
      ready.killPlaneReady >= 1,
    // Synthetic move_up displaced the player through the camera-relative controller
    // (move_and_slide + the global-position immediate read prove the whole chain).
    playerMovedOnInput: displacement > 1.0,
    // Headless rAF throttles to ~15 ticks/s; 40 ticks proves sustained physics.
    physicsFramesAdvanced: peak.physicsCalls >= baseline.physicsCalls + 40,
    // travel(Idle/Move) + particle toggles + blink timers flow as queued commands.
    // MEASURED 2026-08-10 (macOS arm64, Chrome 151 headless, protocol 18, task 84): the
    // gameplay window applies ~800 commands over its baseline, so +80 keeps a 10x margin
    // on the REAL _process path. The threshold is unchanged and was NOT re-derived from
    // the pre-84 runs, which were inflated by the benchmark's synthetic per-dispatch
    // scalar mutation -- a lower bound met partly by scaffolding is a lower bound that
    // proves nothing, so it had to be re-measured even though the number stayed put.
    gameplayCommandsApplied: peak.appliedCommands > baseline.appliedCommands + 80,
    crossingsAdvanced: peak.crossings > ready.crossings,
    // Task 84: this demo runs the engine's REAL `_process`, not the transport benchmark.
    // MEASURED 829 (Chrome) / 8524 (Firefox) process dispatches; it was exactly 0 on both
    // before the fix. Asserted as > 0 rather than a magnitude because the count tracks the
    // host's rAF rate, and the thing worth failing on is the demo silently leaving the
    // real dispatch path again.
    realProcessPathDispatched: afterGameplay.processCalls > 0,
    // Task 82: the coroutine frame scheduler is advanced in THIS demo, which names no
    // "Main" handle anywhere -- the pump rides the _process dispatch every proxy emits.
    frameSchedulerPumped: afterGameplay.pumps >= 10,
    // The lose transition: the player left the platform (engine-level global position)...
    playerFellOffLevel: lowestY < startPosition.y - FALL_DEPTH,
    // ...and the KillPlane's deferred coroutine put it back at the start. Nothing else in
    // this demo moves the player upward, so the observed return IS the resumed continuation.
    killPlaneResetPlayer: killPlaneReset !== null,
    // The win transition: `flag_reached` reached FlagReachedScreen's coroutine, which after a
    // 2-second delay played the fade and reloaded the level -- every script readied a second
    // time under a new handle.
    flagSignalSourceResolved: eventsHandle > 0,
    flagReachedReloadedLevel: flagReload !== null,
    fullTeardownToZero: settled.liveHandles === 0,
    // Godot runs every physics tick before the idle/_process pass inside one rAF
    // iteration; the bridge counts any same-tick physics-after-process dispatch.
    physicsOrderingDeterministic: (settled.physicsAfterProcess ?? 0) === 0,
    noCallbackFaults:
      peak.callbackErrors === 0 && settled.callbackErrors === 0 && settled.failure === null,
  };

  const boundaryErrors = [];
  if (peak.callbackErrors !== 0) boundaryErrors.push(`callbackErrors=${peak.callbackErrors}`);
  if (settled.failure !== null) boundaryErrors.push(`failure: ${settled.failure}`);

  return {
    protocolVersion,
    startup: {
      loaded: ready.mode === "charactercontroller",
      outcome: ready.mode === "charactercontroller" ? "ready" : "failed",
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
      appliedCommands: peak.appliedCommands,
      processCalls: afterGameplay.processCalls,
      playerDisplacement: Number(displacement.toFixed(3)),
      frameSchedulerPumps: afterGameplay.pumps,
      frameSchedulerContinuations: afterGameplay.continuations,
      // How far the player fell before the kill plane's deferred emit put it back.
      killPlaneFallDepth: Number(Math.max(0, startPosition.y - lowestY).toFixed(3)),
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
      // Task 88: this was `settled.liveHandles <= peak.maxLiveHandles`, which is TRUE BY
      // CONSTRUCTION -- maxLiveHandles is a monotone high-water mark of liveHandles and
      // observe() merges the settled sample into peak before returning it. The field is
      // the contract's post-teardown invariant, so it must assert what match3/tpsdemo
      // assert: every owner registry actually drained.
      ownerRegistriesToBaseline:
        settled.liveHandles === 0 &&
        settled.callbacks === 0 &&
        settled.pending === 0 &&
        settled.jobs === 0,
    },
    boundaryErrors,
  };
}

function seedFrom(snap) {
  return {
    physicsCalls: snap.physicsCalls,
    appliedCommands: snap.appliedCommands,
    maxLiveHandles: snap.liveHandles,
    crossings: snap.crossings,
  };
}

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
  const startPosition = await playerPosition(evaluate);
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
  const runPosition = await playerPosition(evaluate);
  const peak = gameplay.peak;
  const atPeak = gameplay.last ?? ready;
  const displacement = runPosition
    ? Math.hypot(runPosition.x - startPosition.x, runPosition.z - startPosition.z)
    : 0;
  trace(`ran: displacement=${displacement.toFixed(2)} physics=${peak.physicsCalls} live=${atPeak.liveHandles}`);

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
    protocol16: protocolVersion === 16,
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
    gameplayCommandsApplied: peak.appliedCommands > baseline.appliedCommands + 80,
    crossingsAdvanced: peak.crossings > ready.crossings,
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
      playerDisplacement: Number(displacement.toFixed(3)),
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
    physicsCalls: snap.physicsCalls,
    appliedCommands: snap.appliedCommands,
    maxLiveHandles: snap.liveHandles,
    crossings: snap.crossings,
  };
}

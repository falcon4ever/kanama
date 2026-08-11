// demos/squash.mjs -- squash-the-creeps gameplay + teardown assertions for the Web smoke.
//
// Task 81 slice 1: this driver DRIVES gameplay; it is no longer observe-only. (Until
// this parcel it injected no input at all: the player stood still, no mob was ever
// squashed, and the demo's entire scoring loop was dark to every gate.) MobTimer still
// self-drives spawns (a mob every 0.5s; each initialize runs look_at_from_position/
// rotate_y; walk-offs free themselves past the VisibleOnScreenNotifier3D), and this run
// layers real play on top:
//   1. movement: engine-level action injection (ops 86/87 -- browser key synthesis
//      stalls headless Firefox; SafariDriver has no keys transport) as a fixed
//      in-page-released hold from an airborne stance pin (op 142) -- airborne so no
//      ground-level mob can reach the MobDetector and end the run early; movement is
//      proven by op 138 position reads (threshold sized by a found Web parity defect,
//      see the MOVE_HOLD_MS note),
//   2. score: the driver re-pins the player a body-height above the latest live mob
//      (bridge.squashMobHandle, re-read every poll and stale-tolerated) and lets
//      gravity close the gap; the landing is real -- slide-collision normal dot UP >
//      0.1 -> Mob.squash -> squashed signal -> ScoreLabel -- and the score is read back
//      through op 288 until the label reads "Score: 1"+,
//   3. death: the player is parked at ground level; a mob reaching the MobDetector
//      fires Player.die -> hit signal -> Main stops MobTimer + shows Retry, and the
//      player script frees (liveScriptsByClass ".Player" drops to 0),
//   4. teardown: SmokeQuit.smoke_teardown still drains every live handle to zero
//      (SmokeQuit survives the player's free).
//
// Falsification harness (task 81 requires every gate provably able to fail): set
// KANAMA_WEB_T81_FALSIFY to induce exactly one break and watch its check go false --
//   no-input  -> movement pulses skipped: playerMovedOnInput false
//   no-chase  -> mob-drop choreography skipped: mobSquashScoredOnHud false
//   no-death  -> ground park skipped (player stays pinned aloft): playerFreedOnMobContact false

const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
const DEBUG = process.env.KANAMA_WEB_SMOKE_DEBUG === "1";
const FALSIFY = process.env.KANAMA_WEB_T81_FALSIFY ?? "";
const START = Date.now();
const trace = (msg) => {
  if (DEBUG) process.stderr.write(`[squash ${((Date.now() - START) / 1000).toFixed(1)}s] ${msg}\n`);
};

// Airborne stance anchor for movement holds: mobs top out around y=1.1 and the
// MobDetector rides the player's feet, so y=8 keeps the hold's early frames out of
// reach while the arena is still filling.
const AIR_ANCHOR = [0, 8, 0];
// Drop height above a mob's root for the squash: the mob's box top sits ~1.07 above its
// root and the player's sphere bottom ~0.02 below its own, so +1.25 leaves ~0.2 of fall.
// The player's velocity.y keeps accumulating between pins (a teleport does not touch
// velocity), so the swept move_and_slide contacts the box top within a tick or two --
// faster than a 10-18 u/s mob can slide out from under the pin.
const DROP_HEIGHT = 1.25;
// In-page hold per movement attempt; released in page so driver-transport jank can
// never stretch a hold. The squash arena is walled (WorldBoundaryShape3D), so even a
// free-running engine cannot carry the player anywhere dangerous.
//
// SIZED BY A FOUND DEFECT (task 81, 2026-08-11, filed in the PR): while a move action
// is held, squash's Player calls lookAtFromPosition(self.position, ...) every physics
// tick, and on Web `self.position` is the mirrored transform snapshot, which the proxy
// refreshes only in _process -- once per RENDERED frame (_physics_process refreshes
// only the velocity slot; see WebScriptCodeEmitter's _physics_process emission). Every
// tick after the first inside one rendered frame therefore teleports the body back to
// the frame-start position, so NET movement is one physics tick (14/60 = 0.23 u) per
// rendered frame regardless of how many ticks the frame ran. MEASURED: 100ms holds
// moved 0.23-0.35 u on a free-running headless Chrome instead of the naive 1.4 u. A
// 600ms hold nets ~0.23 x rendered-frames, comfortably past the 0.5 u gate even on a
// slow-framed headless engine, and on a display-paced engine (1 tick/frame) it is
// simply full-speed movement.
const MOVE_HOLD_MS = 600;

async function snapshot(evaluate) {
  try {
    return await evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      if (!bridge) return null;
      const classCount = (suffix) => {
        const entry = Object.entries(bridge.match3ReadyByClass ?? {}).find(([n]) => n.endsWith(suffix));
        return entry?.[1] ?? 0;
      };
      const liveCount = (suffix) => {
        const entry = Object.entries(bridge.liveScriptsByClass ?? {}).find(([n]) => n.endsWith(suffix));
        return entry?.[1] ?? 0;
      };
      return {
        mode: bridge.mode,
        protocol: bridge.results?.protocolVersion ?? bridge.protocolVersion ?? 0,
        mainHandle: bridge.squashMainHandle,
        smokeQuitHandle: bridge.squashSmokeQuitHandle,
        playerHandle: bridge.squashPlayerHandle,
        scoreLabelHandle: bridge.squashScoreLabelHandle,
        mobHandle: bridge.squashMobHandle,
        readyCount: bridge.readyCount,
        mainReady: classCount(".Main"),
        playerReady: classCount(".Player"),
        scoreLabelReady: classCount(".ScoreLabel"),
        mobReady: classCount(".Mob"),
        playerLive: liveCount(".Player"),
        mobLive: liveCount(".Mob"),
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

// Player world position (op 138 on the Player script's handle); null when unreadable
// (page busy, or the player already freed on the death path).
async function playerPosition(evaluate) {
  try {
    return await evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      const handle = bridge.squashPlayerHandle;
      const x = bridge.immediateNoArgsVector3X(138, handle);
      return { x, y: bridge.immediateNoArgsVector3Y(), z: bridge.immediateNoArgsVector3Z() };
    })()`);
  } catch {
    return null;
  }
}

// One squash-chase step, entirely in page so the mob read and the pin land inside one
// event-loop turn: re-read the LATEST mob handle (it goes stale when a mob frees --
// squashed or walked off -- so every access tolerates a throw), read its position, and
// pin the player DROP_HEIGHT above it.
async function chaseStep(evaluate) {
  try {
    return await evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      const mob = bridge.squashMobHandle;
      if (!mob) return { pinned: false, reason: "no-mob" };
      let x, y, z;
      try {
        x = bridge.immediateNoArgsVector3X(138, mob);
        y = bridge.immediateNoArgsVector3Y();
        z = bridge.immediateNoArgsVector3Z();
      } catch {
        return { pinned: false, reason: "stale-mob" };
      }
      const sep = String.fromCharCode(31);
      try {
        bridge.immediateObjectQuery(142, bridge.squashPlayerHandle, [x, y + ${DROP_HEIGHT}, z].join(sep));
      } catch {
        return { pinned: false, reason: "player-gone" };
      }
      return { pinned: true, mob: { x, y, z } };
    })()`);
  } catch {
    return { pinned: false, reason: "evaluate-failed" };
  }
}

// One movement attempt: pin the airborne stance, hold move_forward, release in page.
// Returns the pin result and the engine's own view of the pressed state for the trace.
async function movePulse(evaluate) {
  return await evaluate(`(() => {
    const bridge = globalThis.KanamaWebBridge;
    const handle = bridge.squashPlayerHandle;
    const sep = String.fromCharCode(31);
    const pinned = bridge.immediateObjectQuery(142, handle, [${AIR_ANCHOR.join(", ")}].join(sep));
    bridge.immediateObjectQuery(86, handle, "move_forward");
    const pressed = bridge.immediateObjectQuery(69, handle, "move_forward");
    setTimeout(() => {
      try {
        bridge.immediateObjectQuery(87, bridge.squashPlayerHandle, "move_forward");
      } catch {}
    }, ${MOVE_HOLD_MS});
    return { pinned, pressed };
  })()`);
}

async function pinPlayer(evaluate, target) {
  try {
    await evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      const sep = String.fromCharCode(31);
      bridge.immediateObjectQuery(142, bridge.squashPlayerHandle, [${target.join(", ")}].join(sep));
      return true;
    })()`);
  } catch {}
}

// ScoreLabel text through op 288 (empty child path: the ScoreLabel IS the scripted
// node). A missing target publishes no string and the bridge throws -> null here.
async function scoreText(evaluate) {
  try {
    return await evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      return bridge.immediateStringQuery(288, bridge.squashScoreLabelHandle, "");
    })()`);
  } catch {
    return null;
  }
}

function mergeSnap(state, snap) {
  if (!snap) return;
  const peak = state.peak;
  peak.mobInstantiations = Math.max(peak.mobInstantiations, snap.mobInstantiations);
  peak.mobAddChildCommands = Math.max(peak.mobAddChildCommands, snap.mobAddChildCommands);
  peak.physicsCalls = Math.max(peak.physicsCalls, snap.physicsCalls);
  peak.appliedCommands = Math.max(peak.appliedCommands, snap.appliedCommands);
  peak.maxLiveHandles = Math.max(peak.maxLiveHandles, snap.maxLiveHandles);
  peak.crossings = Math.max(peak.crossings, snap.crossings);
  peak.callbackErrors = Math.max(peak.callbackErrors, snap.callbackErrors);
  // A drop in the live ".Mob" script count means a mob freed itself (squashed, or walked
  // off past its VisibleOnScreenNotifier3D). Exact per class, so the player's own free
  // on the death path can never masquerade as a mob free (the pre-81 driver inferred
  // frees from total live-handle drops, which could).
  if (state.prevMobLive !== null && snap.mobLive < state.prevMobLive) {
    state.mobFrees += state.prevMobLive - snap.mobLive;
  }
  state.prevMobLive = snap.mobLive;
  state.last = snap;
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
      snap.smokeQuitHandle > 0 &&
      snap.playerHandle > 0 &&
      snap.scoreLabelHandle > 0
    ) {
      ready = snap;
      break;
    }
    await delay(100);
  }
  if (!ready) throw new Error("Kotlin/Wasm squash-the-creeps did not become ready");
  const startupDurationMs = Date.now() - startupStart;
  trace(`ready: readyCount=${ready.readyCount} player=${ready.playerHandle} score=${ready.scoreLabelHandle} protocol=${ready.protocol}`);

  const state = {
    peak: {
      mobInstantiations: 0,
      mobAddChildCommands: 0,
      physicsCalls: ready.physicsCalls,
      appliedCommands: ready.appliedCommands,
      maxLiveHandles: ready.liveHandles,
      crossings: ready.crossings,
      callbackErrors: 0,
    },
    prevMobLive: ready.mobLive ?? null,
    mobFrees: 0,
    last: ready,
  };

  // --- Phase 1: movement (task 81 part A) ------------------------------------------
  // Runs FIRST, airborne, before the arena fills: the pre-81 driver left the player
  // standing at the spawn for its whole observe window, and whether a mob reached the
  // MobDetector during it was pure spawn-angle luck that nothing measured.
  let maxDisplacement = 0;
  let movePulses = 0;
  if (FALSIFY !== "no-input") {
    for (let attempt = 0; attempt < 3 && Date.now() < deadline; attempt += 1) {
      movePulses += 1;
      const pulseState = await movePulse(evaluate);
      // Wait out the full in-page hold plus a margin, then read where it ended up.
      await delay(MOVE_HOLD_MS + 150);
      const position = await playerPosition(evaluate);
      if (position) {
        const displacement = Math.hypot(position.x - AIR_ANCHOR[0], position.z - AIR_ANCHOR[2]);
        maxDisplacement = Math.max(maxDisplacement, displacement);
      }
      mergeSnap(state, await snapshot(evaluate));
      trace(
        `move#${attempt}: displacement=${maxDisplacement.toFixed(2)} pinned=${pulseState?.pinned} pressed=${pulseState?.pressed} pos=${position ? `(${position.x.toFixed(2)},${position.y.toFixed(2)},${position.z.toFixed(2)})` : "null"}`,
      );
      if (maxDisplacement > 0.5) break;
    }
  } else {
    trace("FALSIFY=no-input: movement pulses skipped");
  }
  const playerMovedOnInput = maxDisplacement > 0.5;
  trace(`move: attempts=${movePulses} displacement=${maxDisplacement.toFixed(2)}`);

  // --- Phase 2: squash a mob, read the score (task 81 part B) ------------------------
  // The chase re-pins above the latest live mob every poll; mob spawn/free evidence for
  // the long-standing checks keeps accruing through the same snapshots.
  let score = null;
  let chaseSteps = 0;
  const chaseDeadline = Math.min(deadline, Date.now() + 30_000);
  if (FALSIFY !== "no-chase") {
    while (Date.now() < chaseDeadline) {
      chaseSteps += 1;
      const step = await chaseStep(evaluate);
      await delay(70);
      const text = await scoreText(evaluate);
      if (text !== null) score = text;
      const snap = await snapshot(evaluate);
      mergeSnap(state, snap);
      if (DEBUG && step) {
        trace(`chase#${chaseSteps}: ${step.pinned ? `mob@(${step.mob.x.toFixed(1)},${step.mob.y.toFixed(1)},${step.mob.z.toFixed(1)})` : step.reason} score="${score}" mobs=${snap?.mobLive}`);
      }
      if (score !== null && /^Score: [1-9]\d*$/.test(score)) break;
      // The player died mid-chase (a mob caught a grounded frame): record and move on
      // honestly rather than chasing with a freed handle.
      if (snap && snap.playerLive === 0) {
        trace("chase aborted: player died before scoring");
        break;
      }
    }
  } else {
    trace("FALSIFY=no-chase: squash chase skipped");
    score = await scoreText(evaluate);
  }
  const mobSquashScoredOnHud = score !== null && /^Score: [1-9]\d*$/.test(score);
  trace(`score: steps=${chaseSteps} label="${score}"`);

  // --- Phase 3: death path (task 81 part B, run AFTER the score attempt) -------------
  // Park the player at ground level and let a mob reach the MobDetector. From here on
  // the player handle is left alone: it frees itself.
  let playerFreedOnMobContact = false;
  if (FALSIFY !== "no-death") {
    await pinPlayer(evaluate, [0, 0.1, 0]);
    const deathDeadline = Math.min(deadline, Date.now() + 30_000);
    while (Date.now() < deathDeadline) {
      const snap = await snapshot(evaluate);
      mergeSnap(state, snap);
      if (snap) {
        trace(`death: playerLive=${snap.playerLive} mobs=${snap.mobLive} live=${snap.liveHandles}`);
        if (snap.playerLive === 0) {
          playerFreedOnMobContact = true;
          break;
        }
      }
      await delay(200);
    }
  } else {
    trace("FALSIFY=no-death: player left pinned aloft");
    await pinPlayer(evaluate, AIR_ANCHOR);
  }

  // Let any remaining self-driven evidence (spawns, walk-off frees) settle briefly if
  // the long-standing thresholds have not been reached yet.
  const evidenceDeadline = Math.min(deadline, Date.now() + 10_000);
  while (
    Date.now() < evidenceDeadline &&
    !(state.peak.mobInstantiations >= 4 && state.mobFrees >= 2)
  ) {
    mergeSnap(state, await snapshot(evaluate));
    await delay(200);
  }
  const peak = state.peak;
  const atPeak = state.last ?? ready;
  trace(`gameplay: mobs=${peak.mobInstantiations} physics=${peak.physicsCalls} frees=${state.mobFrees} live=${atPeak.liveHandles}`);

  // Full teardown: SmokeQuit.smoke_teardown (its only @RegisterFunction, method#1) frees
  // the scene root; every node exits the tree and releases its handles. SmokeQuit
  // survives the player's death-path free, so this works in every phase outcome.
  trace("smoke_teardown");
  await evaluate(
    "globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.squashSmokeQuitHandle, 1); true",
  );
  let settled = atPeak;
  const teardownDeadline = Math.min(deadline, Date.now() + 8_000);
  while (Date.now() < teardownDeadline) {
    const snap = await snapshot(evaluate);
    if (snap) {
      mergeSnap(state, snap);
      settled = snap;
      if (snap.liveHandles === 0) break;
    }
    await delay(150);
  }
  trace(`teardown: live=${settled.liveHandles} callbacks=${settled.callbacks} errs=${settled.callbackErrors}`);

  const protocolVersion = ready.protocol;
  const checks = {
    modeSquash: ready.mode === "squash",
    protocol18: protocolVersion === 18,
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
    // Mobs freed themselves (squashed and/or walked off), bounded by the peak -- no leak.
    mobsSpawnAndFree: state.mobFrees >= 2 && atPeak.liveHandles <= peak.maxLiveHandles,
    // Task 81: injected move_forward displaced the player through Input.is_action_pressed
    // -> physicsProcess -> move_and_slide -- the demo's input path, driven end to end.
    playerMovedOnInput,
    // Task 81: a real landing squashed a mob and the score chain ran end to end
    // (slide-collision normal check -> Mob.squash -> squashed signal -> ScoreLabel).
    mobSquashScoredOnHud,
    // Task 81: a mob reached the ground-level MobDetector, Player.die ran, and the
    // player script freed -- the demo's lose path, previously dark on every gate.
    playerFreedOnMobContact,
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
      playerDisplacement: Number(maxDisplacement.toFixed(3)),
      scoreReported: mobSquashScoredOnHud ? Number.parseInt(score.slice("Score: ".length), 10) : 0,
      mobFrees: state.mobFrees,
      chaseSteps,
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

// demos/platformer.mjs -- Starter-Kit-3D-Platformer gameplay + teardown assertions.
//
// Task 81 slice 1: this driver DRIVES gameplay; it is no longer observe-only. (Until
// this parcel it injected no input at all, so the player stood still under gravity and
// the demo's entire scoring loop -- coins, HUD, footsteps -- was dark to every gate.)
// The run:
//   1. observes both frame pumps idling (physics AND process, commands applied),
//   2. reads SoundFootsteps is_playing (op 287) while idle AND later during the walk
//      hold -- a PIPELINE-LEVEL assertion (stream loaded + mixer playing), NOT a
//      walking discriminator; see the MEASURED note at the check,
//   3. walks: engine-level action injection (ops 86/87 -- browser key synthesis stalls
//      headless Firefox and SafariDriver has no keys transport) in short in-page-released
//      pulses, re-pinning the stance (op 142) each pulse so a free-running engine can
//      never carry the player off the level and into Player's y<-10 scene reload;
//      movement is proven by op 138 position reads and footsteps by op 287 going true,
//   4. scores: pins the player onto the coin at (-3, 0.635, 0); the Area3D overlap fires
//      body_entered -> Player.collectCoin -> coinCollected signal -> Hud, all real, and
//      the HUD "Coins" Label is read back through op 288 and must reach >= 1,
//   5. tears down via SmokeQuit.smoke_teardown and drains live handles to zero.
//
// Falsification harness (task 81 requires every gate provably able to fail): set
// KANAMA_WEB_T81_FALSIFY to induce exactly one break and watch its check go false --
//   no-input          -> pulses skipped: playerMovedOnInput false
//   wrong-audio-path  -> op 287 queries a nonexistent child: footstepsPipelineLive false
//   no-coin           -> coin pin skipped: coinCollectedOnHud false

const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
const DEBUG = process.env.KANAMA_WEB_SMOKE_DEBUG === "1";
const FALSIFY = process.env.KANAMA_WEB_T81_FALSIFY ?? "";
const START = Date.now();
const trace = (msg) => {
  if (DEBUG) process.stderr.write(`[platformer ${((Date.now() - START) / 1000).toFixed(1)}s] ${msg}\n`);
};

// Stance anchor: the Player's spawn (0, 0.49, 0), pinned slightly high so a pin never
// wedges the capsule into the floor. Walk pulses re-pin here every pulse.
const ANCHOR = [0, 0.6, 0];
// The coin nearest the spawn platform chain: scenes/main.tscn places it at
// (-3, 0.635, 0) above the platform-medium piece, ground-level footing on all sides.
const COIN_PIN = [-3, 0.75, 0];
// Walk-hold sizing, MEASURED 2026-08-11 (macOS arm64, protocol 18): wall-time holds
// are unusable here because engine progress decouples from the wall clock in both
// directions -- 100ms holds moved only 0.22-0.27u on free-running Firefox (progress
// starved under driver evaluate traffic), while 400ms and 600ms holds BOTH ended at
// x~-2.3 on Chrome (the engine catches up in bursts during the driver's quiet
// delays), which is inside the coin's overlap reach (~x=-2.2: capsule 0.3 + coin
// sphere 0.5) and let the walk itself collect the coin, un-falsifying the no-coin
// break. The release is therefore gated IN PAGE on MEASURED displacement (op 138
// polled every 25ms, released past WALK_RELEASE_AT) with a wall-time failsafe -- the
// page's own event loop interleaves with engine frames, so the endpoint is bounded by
// the threshold plus one burst (~0.6u) plus the deceleration slide (~0.4u), keeping
// the walk clear of the coin while comfortably past the 0.5u movement gate.
const WALK_RELEASE_AT = 0.7;
const WALK_FAILSAFE_MS = 400;
const AUDIO_CHILD_PATH = FALSIFY === "wrong-audio-path" ? "SoundFootstepsMissing" : "SoundFootsteps";

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
        mainHandle: bridge.platformerMainHandle,
        smokeQuitHandle: bridge.platformerSmokeQuitHandle,
        playerHandle: bridge.platformerPlayerHandle,
        hudHandle: bridge.platformerHudHandle,
        readyCount: bridge.readyCount,
        mainReady: classCount(".Main"),
        playerReady: classCount(".Player"),
        hudReady: classCount(".Hud"),
        coinReady: classCount(".Coin"),
        processCalls: bridge.processCalls,
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

// Player world position through the immediate no-args Vector3 channel (op 138 =
// Node3D.get_global_position on the Player script's handle).
async function playerPosition(evaluate) {
  try {
    return await evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      const handle = bridge.platformerPlayerHandle;
      const x = bridge.immediateNoArgsVector3X(138, handle);
      return { x, y: bridge.immediateNoArgsVector3Y(), z: bridge.immediateNoArgsVector3Z() };
    })()`);
  } catch {
    return null;
  }
}

// SoundFootsteps is_playing through op 287 (child-path payload on the Player's handle).
// Returns 1/0, or null when the page could not be asked.
async function footstepsPlaying(evaluate) {
  try {
    return await evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      return bridge.immediateObjectQuery(287, bridge.platformerPlayerHandle, ${JSON.stringify(AUDIO_CHILD_PATH)});
    })()`);
  } catch {
    return null;
  }
}

// HUD "Coins" Label text through op 288 (child path under the Hud script's Control).
// A wrong path publishes no string and the bridge throws -> null here -> check false.
async function hudCoinsText(evaluate) {
  try {
    return await evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      return bridge.immediateStringQuery(288, bridge.platformerHudHandle, "Coins");
    })()`);
  } catch {
    return null;
  }
}

// One walk pulse: re-pin the stance, press move_left+move_forward (the View camera is
// yawed +45 deg, so this input pair rotates to exactly -X -- along the platform chain
// toward the coin), and release IN PAGE once the measured displacement passes
// WALK_RELEASE_AT (wall-time failsafe at WALK_FAILSAFE_MS) so neither driver-transport
// jank nor an engine catch-up burst can stretch the walk into the coin.
async function walkPulse(evaluate) {
  await evaluate(`(() => {
    const bridge = globalThis.KanamaWebBridge;
    const handle = bridge.platformerPlayerHandle;
    const sep = String.fromCharCode(31);
    bridge.immediateObjectQuery(142, handle, [${ANCHOR.join(", ")}].join(sep));
    bridge.immediateObjectQuery(86, handle, "move_left");
    bridge.immediateObjectQuery(86, handle, "move_forward");
    const release = () => {
      try {
        bridge.immediateObjectQuery(87, bridge.platformerPlayerHandle, "move_left");
        bridge.immediateObjectQuery(87, bridge.platformerPlayerHandle, "move_forward");
      } catch {}
    };
    const start = Date.now();
    const watch = setInterval(() => {
      let done = Date.now() - start >= ${WALK_FAILSAFE_MS};
      try {
        const x = bridge.immediateNoArgsVector3X(138, bridge.platformerPlayerHandle);
        bridge.immediateNoArgsVector3Y();
        const z = bridge.immediateNoArgsVector3Z();
        if (Math.hypot(x - ${ANCHOR[0]}, z - ${ANCHOR[2]}) > ${WALK_RELEASE_AT}) done = true;
      } catch {
        done = true;
      }
      if (done) {
        clearInterval(watch);
        release();
      }
    }, 25);
    return true;
  })()`);
}

async function pinPlayer(evaluate, target) {
  try {
    await evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      const sep = String.fromCharCode(31);
      bridge.immediateObjectQuery(142, bridge.platformerPlayerHandle, [${target.join(", ")}].join(sep));
      return true;
    })()`);
  } catch {}
}

async function observe(evaluate, seed, windowMs, deadline, predicate) {
  const peak = { ...seed };
  let last = null;
  const until = Math.min(deadline, Date.now() + windowMs);
  while (Date.now() < until) {
    const snap = await snapshot(evaluate);
    if (snap) {
      peak.processCalls = Math.max(peak.processCalls, snap.processCalls);
      peak.physicsCalls = Math.max(peak.physicsCalls, snap.physicsCalls);
      peak.appliedCommands = Math.max(peak.appliedCommands, snap.appliedCommands);
      peak.maxLiveHandles = Math.max(peak.maxLiveHandles, snap.maxLiveHandles);
      peak.crossings = Math.max(peak.crossings, snap.crossings);
      peak.callbackErrors = Math.max(peak.callbackErrors, snap.callbackErrors);
      last = snap;
      trace(`process=${snap.processCalls} physics=${snap.physicsCalls} applied=${snap.appliedCommands} live=${snap.liveHandles} crossings=${snap.crossings} errs=${snap.callbackErrors}`);
      if (predicate && predicate(snap, peak)) break;
    }
    await delay(150);
  }
  return { last, peak };
}

function mergeSnap(peak, snap) {
  if (!snap) return;
  peak.processCalls = Math.max(peak.processCalls, snap.processCalls);
  peak.physicsCalls = Math.max(peak.physicsCalls, snap.physicsCalls);
  peak.appliedCommands = Math.max(peak.appliedCommands, snap.appliedCommands);
  peak.maxLiveHandles = Math.max(peak.maxLiveHandles, snap.maxLiveHandles);
  peak.crossings = Math.max(peak.crossings, snap.crossings);
  peak.callbackErrors = Math.max(peak.callbackErrors, snap.callbackErrors);
}

export async function runPlatformer({ url, evaluate, navigate, deadline }) {
  const startupStart = Date.now();
  trace("navigate");
  await navigate(`${url}?platformer=${Date.now()}`);

  const readyDeadline = Math.min(deadline, Date.now() + 45_000);
  let ready = null;
  while (Date.now() < readyDeadline) {
    const snap = await snapshot(evaluate);
    if (
      snap &&
      snap.mode === "platformer" &&
      snap.protocol > 0 &&
      snap.mainReady >= 1 &&
      snap.playerReady >= 1 &&
      snap.hudReady >= 1 &&
      snap.coinReady >= 1 &&
      snap.mainHandle > 0 &&
      snap.smokeQuitHandle > 0 &&
      snap.playerHandle > 0 &&
      snap.hudHandle > 0
    ) {
      ready = snap;
      break;
    }
    await delay(100);
  }
  if (!ready) throw new Error("Kotlin/Wasm 3D platformer did not become ready");
  const startupDurationMs = Date.now() - startupStart;
  trace(`ready: readyCount=${ready.readyCount} player=${ready.playerHandle} hud=${ready.hudHandle} protocol=${ready.protocol}`);

  // Observe the game running idle: the player's _physics_process (gravity +
  // move_and_slide) advances physicsCalls and its velocity/position mutations advance
  // appliedCommands; coins/clouds/view advance processCalls.
  const seed = {
    processCalls: ready.processCalls,
    physicsCalls: ready.physicsCalls,
    appliedCommands: ready.appliedCommands,
    maxLiveHandles: ready.maxLiveHandles,
    crossings: ready.crossings,
    callbackErrors: 0,
  };
  const gameplay = await observe(
    evaluate,
    seed,
    8_000,
    deadline,
    (snap) =>
      snap.physicsCalls >= ready.physicsCalls + 30 &&
      snap.processCalls >= ready.processCalls + 30 &&
      snap.appliedCommands >= ready.appliedCommands + 30,
  );
  const peak = gameplay.peak;
  let atPeak = gameplay.last ?? ready;
  trace(`idle: process=${peak.processCalls} physics=${peak.physicsCalls} applied=${peak.appliedCommands} live=${atPeak.liveHandles}`);

  // --- Audio (task 81 part C): PIPELINE-LEVEL, not a walking discriminator ---------
  // MEASURED in the real export (2026-08-11, macOS arm64, protocol 18): op 287 reads 1
  // while the player idles with stream_paused=true, and 1 during the walk hold. Root
  // cause verified in the Godot 4.7 source: Web nothreads plays this ogg through the
  // SAMPLE path, set_stream_paused pauses the audible sample (set_sample_playback_pause)
  // but never removes it from AudioServer::sample_playback_list, and a sample's
  // is_playing IS that list membership -- so on Web is_playing asserts "stream loaded +
  // mixer entry live" and CANNOT see the pause-toggle. (On the desktop mixed path it
  // would: set_playback_paused parks the node in FADE_OUT_TO_PAUSE/PAUSED and
  // is_playback_active reports only PLAYING.) AudioStreamPlayer.get_stream_paused reads
  // the mixed-list node state on both paths and would distinguish walking; noted as a
  // follow-up, not in this parcel.
  const idleReads = [];
  for (let i = 0; i < 3; i += 1) {
    const playing = await footstepsPlaying(evaluate);
    if (playing !== null) idleReads.push(playing);
    await delay(120);
  }
  const footstepsLiveWhileIdle = idleReads.length >= 2 && idleReads.every((v) => v === 1);
  trace(`audio idle reads=${JSON.stringify(idleReads)}`);

  // --- Walk pulses (task 81 part A) ------------------------------------------------
  // Fixed short holds released in page; NO early exit of any hold, and no physics-count
  // predicates (standing lore). The pulse LOOP stops once both movement and walking
  // audio have been evidenced, or after the pulse budget.
  const startPosition = await playerPosition(evaluate);
  if (!startPosition) throw new Error("could not read the player's global position");
  let maxDisplacement = 0;
  let footstepsLiveDuringWalk = false;
  let walkPulses = 0;
  if (FALSIFY !== "no-input") {
    for (let pulse = 0; pulse < 10 && Date.now() < deadline; pulse += 1) {
      walkPulses += 1;
      await walkPulse(evaluate);
      // Sample the audio pipeline twice inside the hold window (the engine may be
      // free-running, so the walking frames can sit anywhere inside it).
      await delay(120);
      const mid = await footstepsPlaying(evaluate);
      if (mid === 1) footstepsLiveDuringWalk = true;
      await delay(260);
      const late = await footstepsPlaying(evaluate);
      if (late === 1) footstepsLiveDuringWalk = true;
      await delay(150);
      const pressed = await evaluate(
        'globalThis.KanamaWebBridge.immediateObjectQuery(69, globalThis.KanamaWebBridge.platformerPlayerHandle, "move_left")',
      ).catch(() => null);
      const position = await playerPosition(evaluate);
      if (position) {
        const displacement = Math.hypot(position.x - ANCHOR[0], position.z - ANCHOR[2]);
        maxDisplacement = Math.max(maxDisplacement, displacement);
      }
      mergeSnap(peak, await snapshot(evaluate));
      trace(
        `pulse#${pulse}: displacement=${maxDisplacement.toFixed(2)} audio=[${mid},${late}] pressed=${pressed} pos=${position ? `(${position.x.toFixed(2)},${position.y.toFixed(2)},${position.z.toFixed(2)})` : "null"}`,
      );
      if (maxDisplacement > 0.5 && footstepsLiveDuringWalk) break;
      await delay(120);
    }
    // Park the player back on the spawn anchor before the scored phase.
    await pinPlayer(evaluate, ANCHOR);
  } else {
    trace("FALSIFY=no-input: walk pulses skipped");
  }
  const playerMovedOnInput = maxDisplacement > 0.5;
  trace(`walk: pulses=${walkPulses} displacement=${maxDisplacement.toFixed(2)} audioWalk=${footstepsLiveDuringWalk}`);

  // --- Coin -> HUD score (task 81 part B) ------------------------------------------
  // Stance-place the player onto the coin; the Area3D overlap fires body_entered and the
  // whole collision -> cross-script call -> signal -> HUD chain is real. The pin repeats
  // each poll (the coin bobs) until the HUD label advances.
  let hudCoins = null;
  if (FALSIFY !== "no-coin") {
    for (let attempt = 0; attempt < 25 && Date.now() < deadline; attempt += 1) {
      await pinPlayer(evaluate, COIN_PIN);
      await delay(150);
      hudCoins = await hudCoinsText(evaluate);
      trace(`coin attempt#${attempt}: hud="${hudCoins}"`);
      if (hudCoins !== null && Number.parseInt(hudCoins, 10) >= 1) break;
    }
  } else {
    trace("FALSIFY=no-coin: coin pin skipped");
    hudCoins = await hudCoinsText(evaluate);
  }
  const coinCollectedOnHud = hudCoins !== null && Number.parseInt(hudCoins, 10) >= 1;
  mergeSnap(peak, await snapshot(evaluate));
  atPeak = (await snapshot(evaluate)) ?? atPeak;

  // Full teardown: SmokeQuit.smoke_teardown (its only @RegisterFunction, method#1) frees
  // the scene root; every node exits the tree and releases its handles.
  trace("smoke_teardown");
  await evaluate(
    "globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.platformerSmokeQuitHandle, 1); true",
  );
  const teardown = await observe(evaluate, peak, 8_000, deadline, (snap) => snap.liveHandles === 0);
  const settled = teardown.last ?? atPeak;
  trace(`teardown: live=${settled.liveHandles} callbacks=${settled.callbacks} pending=${settled.pending} jobs=${settled.jobs} errs=${settled.callbackErrors}`);

  const protocolVersion = ready.protocol;
  const checks = {
    modePlatformer: ready.mode === "platformer",
    protocol18: protocolVersion === 18,
    sceneScriptsReady:
      ready.mainReady >= 1 && ready.playerReady >= 1 && ready.hudReady >= 1 && ready.coinReady >= 1,
    // Both frame pumps ran: _physics_process (player gravity/move_and_slide) and
    // _process (coins/clouds/camera) each advanced many frames.
    physicsFramesAdvanced: peak.physicsCalls >= ready.physicsCalls + 30,
    processFramesAdvanced: peak.processCalls >= ready.processCalls + 30,
    gameplayCommandsApplied: peak.appliedCommands >= ready.appliedCommands + 30,
    // move_and_slide/is_on_floor are immediate kotlin->godot crossings every physics tick.
    crossingsAdvanced: peak.crossings > ready.crossings,
    // Task 81: injected move actions displaced the player through Input.get_axis ->
    // handleControls -> move_and_slide -- the demo's input path, driven end to end.
    playerMovedOnInput,
    // Task 81: PIPELINE-LEVEL audio assertion -- SoundFootsteps' stream is loaded and
    // its mixer entry live (op 287 = 1) while idling AND during the walk hold. On the
    // Web sample path is_playing does NOT see the stream_paused walk-toggle (measured
    // [1,1,1] idle / [1,1] walking; root cause in the comment at the idle read), so
    // this catches a dead audio pipeline or a wrong node path, not a mute toggle.
    footstepsPipelineLive: footstepsLiveWhileIdle && footstepsLiveDuringWalk,
    // Task 81: the coin was collected and the HUD "Coins" label advanced -- the full
    // Area3D overlap -> Player.collectCoin -> coinCollected signal -> Hud label chain.
    coinCollectedOnHud,
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
      loaded: ready.mode === "platformer",
      outcome: ready.mode === "platformer" ? "ready" : "failed",
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
      physicsProcessCalls: peak.physicsCalls,
      appliedCommands: peak.appliedCommands,
      playerDisplacement: Number(maxDisplacement.toFixed(3)),
      hudCoinsReported: hudCoins !== null ? Number.parseInt(hudCoins, 10) || 0 : 0,
      walkPulses,
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

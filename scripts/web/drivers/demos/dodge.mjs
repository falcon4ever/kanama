// demos/dodge.mjs -- dodge-the-creeps play + teardown assertions for the Web smoke.
//
// Unlike Bunnymark (driven by bridge method calls) and Match3 (driven by synthetic
// pointer input), dodge is a real-time, self-driven demo: the SmokeQuit script starts
// a game (new_game), lets Godot's MobTimer spawn mobs and the ScoreTimer tick, then
// tears the mobs down and quits. This driver OBSERVES: it polls the bridge telemetry,
// captures the gameplay peak (mobs instantiated + added to the tree, kotlin->godot
// crossings advancing) and the post-teardown trough, and asserts a clean run with no
// callback faults.

const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
const DEBUG = process.env.KANAMA_WEB_SMOKE_DEBUG === "1";
// Task 88: prove the post-teardown invariant can FAIL. Set
// KANAMA_WEB_T81_FALSIFY=pre-teardown-registries to judge ownerRegistriesToBaseline
// against the mid-play sample (mobs alive, callbacks and coroutine jobs registered)
// instead of the settled one. The run must go RED -- a gate only ever observed
// passing is not evidence that it discriminates.
const FALSIFY = process.env.KANAMA_WEB_T81_FALSIFY ?? "";
const START = Date.now();
const trace = (msg) => {
  if (DEBUG) process.stderr.write(`[dodge ${((Date.now() - START) / 1000).toFixed(1)}s] ${msg}\n`);
};

// Reads a flat telemetry snapshot from the bridge. Every field is generic (the
// match3-prefixed instantiation/addChild counters are, despite the name, incremented
// for any mode). Returns null if the page is mid-navigation or has been torn down.
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
        protocol: bridge.results?.protocolVersion ?? 0,
        mainHandle: bridge.dodgeMainHandle,
        smokeQuitHandle: bridge.dodgeSmokeQuitHandle,
        readyCount: bridge.readyCount,
        mainReady: classCount(".Main"),
        playerReady: classCount(".Player"),
        hudReady: classCount(".HUD"),
        mobReady: classCount(".Mob"),
        mobInstantiations: bridge.match3PackedSceneInstantiations,
        mobAddChildCommands: bridge.match3AddChildCommands,
        liveHandles: bridge.liveBrowserHandleCount,
        // Live handles broken down by kind. A per-entity leak is a RATIO -- handles
        // retained per entity created -- and a ratio needs no long window: one
        // handle per mob is as visible across seven mobs as across seven hundred.
        // Task 72 leaked exactly that and still took a ten-minute nightly soak to
        // surface, because the absolute growth is what needs time, not the ratio.
        // Are the mobs MOVING? (task 71). The mob proxy has no get_position arm,
        // but it does have opcode 27, CanvasItem.get_local_mouse_position, which is
        // the pointer expressed in the node's local space. The pointer never moves
        // in a driven run, so this value changes exactly as the NODE moves -- a
        // motion probe that needs no protocol change.
        //
        // It splits task 71 in half: if these sweep while frees stays 0, the mobs
        // move and VisibleOnScreenNotifier2D is not reporting them off-screen; if
        // they are frozen, it is physics or the velocity write.
        mobPositions: (() => {
          const out = [];
          for (const [key, name] of Object.entries(bridge.scriptNameByHandle ?? {})) {
            if (!String(name).endsWith(".Mob") || out.length >= 3) continue;
            const handle = Number(key);
            try {
              if (bridge.api.kanamaWebIsLive(handle) !== 1) continue;
              const x = bridge.immediateNoArgsVector2X(27, handle);
              const y = bridge.immediateNoArgsVector2Y();
              out.push(handle + ":" + Math.round(x) + "," + Math.round(y));
            } catch (e) {
              out.push(handle + ":err");
            }
          }
          return out.join(" ");
        })(),
        liveByKind: (() => {
          const counts = {};
          for (const slot of bridge.browserHandleSlots ?? []) {
            if (!slot || !slot.live) continue;
            counts[slot.kind ?? "(null-kind)"] = (counts[slot.kind ?? "(null-kind)"] ?? 0) + 1;
          }
          return counts;
        })(),
        maxLiveHandles: bridge.maxLiveBrowserHandles,
        crossings: bridge.kotlinToGodotCalls,
        callbackErrors: bridge.callbackErrors,
        callbacks: bridge.api.kanamaWebPendingSignalCallbackCount(),
        pending: bridge.api.kanamaWebPendingCoroutineCount(),
        jobs: bridge.api.kanamaWebRegisteredCoroutineJobCount(),
        failure: globalThis.KanamaWebFailure?.stack ?? globalThis.KanamaWebFailure?.message ?? null,
        // Diagnostics for a cell that runs but never frees a mob: the canvas the
        // engine sizes its viewport from, and the latest mirrored transform the
        // bridge saw (proof that something is actually moving in the scene).
        canvasW: document.querySelector("canvas")?.clientWidth ?? 0,
        canvasH: document.querySelector("canvas")?.clientHeight ?? 0,
        snapX: Math.round(bridge.latestSnapshotX ?? 0),
        snapY: Math.round(bridge.latestSnapshotY ?? 0),
        // Engine truth, not bridge bookkeeping: Node.get_child_count (opcode 1) on
        // Main. Mobs are added as its children and queue_free themselves off screen,
        // so this rises with every spawn and falls with every real free -- which
        // separates "the mobs never left the screen" from "they did, and the handle
        // release did not follow".
        frames: bridge.processCalls ?? 0,
        simSeconds: Math.round((bridge.simSeconds ?? 0) * 10) / 10,
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

// SmokeQuit.smoke_teardown is its only @RegisterFunction (method#1); it quits the
// SceneTree so every node exits the tree and releases its handles.
async function callTeardown(evaluate) {
  return evaluate(
    "globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.dodgeSmokeQuitHandle, 1); true",
  );
}

// Polls the snapshot until `predicate` holds or the window elapses, tracking the
// running peak and returning { last, peak }. Robust to the page briefly returning null.
async function observe(evaluate, seedPeak, windowMs, deadline, predicate) {
  const peak = { mobFrees: 0, ...seedPeak };
  let last = null;
  let prevLive = seedPeak.maxLiveHandles;
  const until = Math.min(deadline, Date.now() + windowMs);
  while (Date.now() < until) {
    const snap = await snapshot(evaluate);
    if (snap) {
      peak.mobInstantiations = Math.max(peak.mobInstantiations, snap.mobInstantiations);
      peak.mobAddChildCommands = Math.max(peak.mobAddChildCommands, snap.mobAddChildCommands);
      peak.maxLiveHandles = Math.max(peak.maxLiveHandles, snap.maxLiveHandles);
      peak.crossings = Math.max(peak.crossings, snap.crossings);
      peak.callbackErrors = Math.max(peak.callbackErrors, snap.callbackErrors);
      // A drop in the live-handle count means a mob (and its child nodes) was freed —
      // dodge mobs queue_free themselves when a VisibleOnScreenNotifier2D reports they
      // left the screen. Counting drops proves the free/release path works during play.
      // It UNDERCOUNTS when a spawn lands in the same sample as a free, which is why the
      // window below is a generous ceiling rather than a fixed duration.
      if (snap.liveHandles < prevLive) peak.mobFrees += 1;
      prevLive = snap.liveHandles;
      last = snap;
      trace(`mobs=${snap.mobInstantiations} addChild=${snap.mobAddChildCommands} live=${snap.liveHandles} max=${snap.maxLiveHandles} frees=${peak.mobFrees} crossings=${snap.crossings} errs=${snap.callbackErrors} canvas=${snap.canvasW}x${snap.canvasH} frames=${snap.frames} sim=${snap.simSeconds}s mobs@ ${snap.mobPositions}`);
      if (predicate && predicate(snap, peak)) break;
    }
    await delay(150);
  }
  return { last, peak };
}

export async function runDodge({ url, evaluate, navigate, deadline }) {
  const startupStart = Date.now();
  trace("navigate");
  await navigate(`${url}?dodge=${Date.now()}`);

  // Ready: dodge mode, the scene scripts recorded ready, the Main handle captured.
  const readyDeadline = Math.min(deadline, Date.now() + 30_000);
  let ready = null;
  while (Date.now() < readyDeadline) {
    const snap = await snapshot(evaluate);
    if (
      snap &&
      snap.mode === "dodge" &&
      snap.protocol > 0 &&
      snap.mainReady >= 1 &&
      snap.playerReady >= 1 &&
      snap.hudReady >= 1 &&
      snap.mainHandle > 0 &&
      snap.smokeQuitHandle > 0
    ) {
      ready = snap;
      break;
    }
    await delay(100);
  }
  if (!ready) throw new Error("Kotlin/Wasm dodge-the-creeps did not become ready");
  const startupDurationMs = Date.now() - startupStart;
  trace(`ready: readyCount=${ready.readyCount} mainHandle=${ready.mainHandle} protocol=${ready.protocol}`);

  // Drive gameplay from the browser (dodge's SmokeQuit env-var gate is inert on Web):
  // new_game starts StartTimer (2s), then MobTimer spawns a mob every 0.5s and ScoreTimer
  // ticks. Observe until several mobs have spawned (the gameplay peak).
  const seedPeak = { mobInstantiations: 0, mobAddChildCommands: 0, maxLiveHandles: ready.liveHandles, crossings: ready.crossings, callbackErrors: 0 };
  // Baseline before any mob exists: everything alive here belongs to the scene.
  const baselineByKind = ready.liveByKind ?? {};
  trace("new_game");
  await callMain(evaluate, "new_game");
  // Round baseline: sampled after new_game has done its own work (HUD score/message
  // lookups, music) but before StartTimer's 2s has elapsed, so no mob exists yet.
  // Comparing the post-restart trough against THIS rather than against the
  // pre-game scene keeps a fixed per-round cost out of a per-mob ratio -- otherwise
  // three HUD handles over seven mobs reads as 0.43 retention and the threshold is
  // measuring the mob count.
  await delay(1_200);
  const roundBaselineByKind = (await snapshot(evaluate))?.liveByKind ?? baselineByKind;
  trace(`round baseline: ${JSON.stringify(roundBaselineByKind)}`);
  // Play long enough for several mobs to spawn AND for the earliest ones to fly across
  // and leave the screen (each mob queue_frees itself on VisibleOnScreenNotifier2D
  // screen_exited). Run the full window so the spawn+free lifecycle is exercised.
  // The window is a CEILING, not a duration: the predicate ends it as soon as two mobs
  // have actually been freed, which on a workstation is a few seconds. It is generous
  // because a software-GL host simulates less game time per wall-clock second, and the
  // assertion should wait for the evidence rather than be graded on the host's speed.
  const gameplay = await observe(evaluate, seedPeak, 45_000, deadline, (snap, p) => p.mobFrees >= 2);
  const peak = gameplay.peak;
  const atPeak = gameplay.last ?? ready;
  trace(`gameplay: mobs=${peak.mobInstantiations} addChild=${peak.mobAddChildCommands} maxLive=${peak.maxLiveHandles} frees=${peak.mobFrees} finalLive=${atPeak.liveHandles} crossings=${peak.crossings}`);

  // Full teardown: SmokeQuit.smoke_teardown quits the SceneTree, exiting every node so it
  // releases its handles. Poll until the live-handle count drains to zero (the smoke
  // wrapper's schema requires liveAfterTeardown === 0). The wasm/JS stays readable after
  // the loop stops, so the snapshot still resolves.
  // Per-entity retention, measured at the one moment dodge makes unambiguous:
  // just after a restart. `new_game` queue_frees the whole mobs group, so every
  // mob instantiated so far is gone and the next one has not spawned yet (a 2s
  // StartTimer). Anything still held above the scene baseline at that trough was
  // retained by mobs that no longer exist.
  //
  // Measuring during play cannot work: mobs alive on screen hold their handles
  // legitimately, and in a short run they have no time to die -- which is exactly
  // why task 72's one-handle-per-mob leak needed a ten-minute soak to show as
  // absolute growth. As a RATIO at the trough it needs seconds.
  trace("restart to measure retention");
  const mobsBeforeRestart = peak.mobInstantiations;
  await callMain(evaluate, "new_game");
  const troughByKind = {};
  const troughUntil = Math.min(deadline, Date.now() + 4_000);
  while (Date.now() < troughUntil) {
    const snap = await snapshot(evaluate);
    if (snap?.liveByKind) {
      for (const [kind, count] of Object.entries(snap.liveByKind)) {
        const above = count - (roundBaselineByKind[kind] ?? 0);
        troughByKind[kind] = Math.min(troughByKind[kind] ?? Number.POSITIVE_INFINITY, above);
      }
    }
    await delay(150);
  }
  const retention = {};
  let worstRetention = 0;
  let worstRetentionKind = "none";
  if (mobsBeforeRestart > 0) {
    for (const [kind, above] of Object.entries(troughByKind)) {
      const perMob = Math.round((Math.max(above, 0) / mobsBeforeRestart) * 100) / 100;
      retention[kind] = perMob;
      if (perMob > worstRetention) {
        worstRetention = perMob;
        worstRetentionKind = kind;
      }
    }
  }
  trace(`retention per mob after restart (${mobsBeforeRestart} mobs): ${JSON.stringify(retention)} worst=${worstRetentionKind}:${worstRetention}`);

  trace("smoke_teardown");
  await callTeardown(evaluate);
  const teardown = await observe(evaluate, { ...peak, maxLiveHandles: peak.maxLiveHandles }, 6_000, deadline, (snap) => snap.liveHandles === 0);
  const settled = teardown.last ?? atPeak;
  trace(`teardown: live=${settled.liveHandles} (peak=${peak.maxLiveHandles}) callbacks=${settled.callbacks} pending=${settled.pending} jobs=${settled.jobs} errs=${settled.callbackErrors}`);

  const protocolVersion = ready.protocol;
  const checks = {
    modeDodge: ready.mode === "dodge",
    protocol18: protocolVersion === 18,
    sceneScriptsReady: ready.mainReady >= 1 && ready.playerReady >= 1 && ready.hudReady >= 1,
    mobsInstantiated: peak.mobInstantiations >= 4,
    mobsAddedToTree: peak.mobAddChildCommands >= peak.mobInstantiations,
    handleGrowthDuringGameplay: peak.maxLiveHandles > ready.liveHandles,
    crossingsAdvanced: peak.crossings > ready.crossings,
    // The spawn/free lifecycle works: mobs left the screen and released their handles
    // (>=2 observed drops during play) and stayed bounded by the peak — no leak.
    // Task 88: the second clause used to be `atPeak.liveHandles <= peak.maxLiveHandles`,
    // which cannot be false (maxLiveHandles is a monotone high-water mark of
    // liveHandles). Dropped rather than rewritten: the free path is already proven by
    // the mobFrees count here, by the per-entity retention ratio (task 73) during play,
    // and by liveAfterTeardown === 0 at the end. A clause that cannot fail only makes
    // the check read stronger than it is.
    mobsSpawnAndFree: peak.mobFrees >= 2,
    // Full teardown: quitting the tree drained every live handle to zero.
    fullTeardownToZero: settled.liveHandles === 0,
    // No handle kind is retained per spawned mob, measured at the post-restart
    // trough where every earlier mob is provably gone. A real per-spawn leak sits
    // at 1.0 (task 72 measured exactly that); a healthy run sits near 0. This is
    // the SHORT-run form of what the soak gate needs ten minutes to see.
    noPerMobHandleRetention: worstRetention <= 0.5,
    noCallbackFaults: peak.callbackErrors === 0 && settled.callbackErrors === 0 && settled.failure === null,
  };

  const last = settled;
  // Task 88 falsification hook: normally the settled post-teardown sample; the
  // falsify mode judges the invariant against the mid-play peak instead, where mobs
  // are alive and callbacks/jobs are registered, so the check must go false.
  const registries = FALSIFY === "pre-teardown-registries" ? atPeak : last;
  const boundaryErrors = [];
  if (peak.callbackErrors !== 0) boundaryErrors.push(`callbackErrors=${peak.callbackErrors}`);
  if (settled.failure !== null) boundaryErrors.push(`failure: ${settled.failure}`);

  return {
    protocolVersion,
    startup: {
      loaded: ready.mode === "dodge",
      outcome: ready.mode === "dodge" ? "ready" : "failed",
      durationMs: startupDurationMs,
    },
    checks,
    handles: {
      liveAfterGameplay: peak.maxLiveHandles,
      liveAfterTeardown: last.liveHandles,
      staleRejected: 0,
    },
    crossings: {
      kotlinToGodotCalls: peak.crossings,
      mobInstantiations: peak.mobInstantiations,
      mobAddChildCommands: peak.mobAddChildCommands,
      // Worst per-mob handle retention, x100 so the envelope stays integer-friendly
      // (the schema requires numeric counters). 0 is healthy; 100 is one leaked
      // handle per spawn.
      worstHandleRetentionPerMobX100: Math.round(worstRetention * 100),
    },
    callbacks: {
      pendingSignalCallbacks: last.callbacks,
    },
    connections: {
      afterGameplayLiveHandles: last.liveHandles,
    },
    scheduler: {
      pendingCoroutines: last.pending,
      registeredJobs: last.jobs,
    },
    teardown: {
      outcome:
        checks.fullTeardownToZero && last.callbackErrors === 0 && last.failure === null
          ? "clean"
          : "incomplete",
      // Task 88: this was `last.liveHandles <= peak.maxLiveHandles`, which is TRUE BY
      // CONSTRUCTION -- maxLiveHandles is a monotone high-water mark of liveHandles and
      // observe() merges the settled sample into peak before returning it. The field is
      // the contract's post-teardown invariant, so it must assert what match3/tpsdemo
      // assert: every owner registry actually drained.
      ownerRegistriesToBaseline:
        registries.liveHandles === 0 &&
        registries.callbacks === 0 &&
        registries.pending === 0 &&
        registries.jobs === 0,
    },
    boundaryErrors,
  };
}

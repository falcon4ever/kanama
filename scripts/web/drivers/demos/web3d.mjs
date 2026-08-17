// demos/web3d.mjs -- 3D render-foundation smoke: observe + teardown assertions.
//
// The web3d scene auto-runs: Main._ready applies the platformer's Compatibility-renderer
// tuning (CanvasLayer.visible, Light3D.set_param, WorldEnvironment/Environment) and _process
// spins a child Node3D every frame. This driver OBSERVES the render is live (process frames
// and applied commands advancing, no faults), then triggers SmokeQuit.smoke_teardown and polls
// the live-handle count to zero.

const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
const DEBUG = process.env.KANAMA_WEB_SMOKE_DEBUG === "1";
const START = Date.now();
const trace = (msg) => {
  if (DEBUG) process.stderr.write(`[web3d ${((Date.now() - START) / 1000).toFixed(1)}s] ${msg}\n`);
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
        mainHandle: bridge.web3dMainHandle,
        smokeQuitHandle: bridge.web3dSmokeQuitHandle,
        readyCount: bridge.readyCount,
        enterTreeCalls: bridge.enterTreeCalls,
        mainReady: classCount(".Main"),
        processCalls: bridge.processCalls,
        // Task 82: the coroutine frame scheduler's per-frame advance. Demo-independent.
        pumps: bridge.frameSchedulerPumps ?? 0,
        continuations: bridge.frameSchedulerContinuations ?? 0,
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
      trace(`process=${snap.processCalls} applied=${snap.appliedCommands} live=${snap.liveHandles} max=${snap.maxLiveHandles} errs=${snap.callbackErrors}`);
      if (predicate && predicate(snap, peak)) break;
    }
    await delay(150);
  }
  return { last, peak };
}

export async function runWeb3d({ url, evaluate, navigate, deadline }) {
  const startupStart = Date.now();
  trace("navigate");
  await navigate(`${url}?web3d=${Date.now()}`);

  const readyDeadline = Math.min(deadline, Date.now() + 30_000);
  let ready = null;
  while (Date.now() < readyDeadline) {
    const snap = await snapshot(evaluate);
    if (
      snap &&
      snap.mode === "web3d" &&
      snap.protocol > 0 &&
      snap.mainReady >= 1 &&
      snap.mainHandle > 0 &&
      snap.smokeQuitHandle > 0
    ) {
      ready = snap;
      break;
    }
    await delay(100);
  }
  if (!ready) throw new Error("Kotlin/Wasm web3d scene did not become ready");
  const startupDurationMs = Date.now() - startupStart;
  trace(`ready: readyCount=${ready.readyCount} mainHandle=${ready.mainHandle} protocol=${ready.protocol}`);

  // Task 66b enter-tree proof: Main.enter_tree_probe (method#5, Int->Int) returns a mask —
  // bit 1 = @OnEnterTree dispatched, bit 2 = the scene-exported @ScriptProperty value
  // ("web3d-enter-tree", never the default) was visible inside it, bit 4 = it ran before
  // @OnReady. A healthy protocol-16 run returns exactly 7.
  const enterTreeProbe = Number(
    await evaluate(
      "globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, 5, 0)",
    ),
  );
  trace(`enterTreeProbe: mask=${enterTreeProbe} enterTreeCalls=${ready.enterTreeCalls}`);

  // Task 64 property-push proof: Main.property_probe (method#7, Int->Int) returns a mask —
  // bit 1 = the scene-exported NodePath (NodePath("Spinner"), never the default) was pushed
  // into Kotlin, bit 2 = the scene value 47 of the RANGE-hinted one-line-annotated export
  // arrived, bit 4 = the pushed NodePath resolves a live node. Task 80 slice 4 added one bit
  // per remaining TYPED property arm (8..2048), so a healthy run now returns 4095.
  const propertyProbe = Number(
    await evaluate(
      "globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, 7, 0)",
    ),
  );
  trace(`propertyProbe: mask=${propertyProbe}`);

  // Task 80 dispatch-shape conformance: Main.dispatch_probe (method#16, Int->Int)
  // returns a mask. Every bit is a shape task 80 admitted, exercised through the REAL crossing
  // (Kotlin asks Godot to call the method by name, Godot dispatches to the generated GDScript
  // proxy, the proxy takes the new arm) rather than through the emitter tests alone:
  //   1 = a (FLOAT) registered function received its argument -- task 79's exact hole
  //   2 = a (BOOL) registered function received its argument
  //   4 = a (VECTOR3, VECTOR3) registered function received BOTH vectors intact
  //   8 = a () -> VECTOR3 return survived the packed transport AND the proxy's parse
  //  16 = the () -> FLOAT / STRING / BOOL / INT returns did too
  //  32 = a one-int signal payload reached a Kotlin lambda instead of being discarded
  //  64 = the slice-3 MIXED shapes: (STRING, OBJECT) carried a hostile label plus a live object
  //       handle, and (INT, OBJECT?) carried a number plus a null object
  // A healthy run returns exactly 127. Values, not just dispatch: each bit compares the value
  // that came back against the value that went out.
  const dispatchProbe = Number(
    await evaluate(
      "globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, 16, 0)",
    ),
  );
  trace(`dispatchProbe: mask=${dispatchProbe}`);

  // Observe the render running: the spinner's _process advances processCalls and its
  // Node3D.rotation mutations advance appliedCommands each frame.
  const seed = {
    processCalls: ready.processCalls,
    appliedCommands: ready.appliedCommands,
    maxLiveHandles: ready.maxLiveHandles,
    crossings: ready.crossings,
    callbackErrors: 0,
  };
  const render = await observe(evaluate, seed, 5_000, deadline, (snap) =>
    snap.processCalls >= ready.processCalls + 10 && snap.appliedCommands >= ready.appliedCommands + 10,
  );
  const peak = render.peak;
  const atPeak = render.last ?? ready;
  trace(`render: process=${peak.processCalls} applied=${peak.appliedCommands} live=${atPeak.liveHandles}`);

  // Task-64 API-parity probes (Main methods #3 and #4). Method #3 exercises
  // Resource.fromHandle identity and AudioStreamPlayer.setStream (assign + play + null-clear),
  // then aims the root at +X with the default -Z-forward convention; method #4 repeats the aim
  // with useModelFront=true. Orientation is read back over the immediate global-rotation
  // channel (opcode 141): the default aim must read yaw -PI/2, the model-front aim +PI/2 — a
  // PI flip (the fps Enemy's 180-degree gap). A Kotlin-side check failure throws before the
  // aim, so a stale yaw fails these checks and the throw itself lands in callbackErrors.
  const readGlobalYaw = () =>
    evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      bridge.immediateNoArgsVector3X(141, bridge.web3dMainHandle);
      return bridge.immediateNoArgsVector3Y();
    })()`);
  trace("parity_probe");
  await evaluate(
    "globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.web3dMainHandle, 3); true",
  );
  const defaultYaw = await readGlobalYaw();
  trace(`parity default yaw=${defaultYaw}`);
  await evaluate(
    "globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.web3dMainHandle, 4); true",
  );
  const modelFrontYaw = await readGlobalYaw();
  trace(`parity model-front yaw=${modelFrontYaw}`);
  const HALF_PI = Math.PI / 2;
  const angleNear = (a, b) => Math.abs(Math.atan2(Math.sin(a - b), Math.cos(a - b))) < 1e-3;

  // Task 76: generic callv fallback. Main.generic_probe (method#6) runs queued generic
  // mutations, immediate generic read-backs, the object-return shapes under the minting
  // policy, and the generic-vs-typed crossing-cost timing, then publishes a JSON report
  // for these assertions.
  trace("generic_probe");
  await evaluate(
    "globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.web3dMainHandle, 6); true",
  );
  const generic = JSON.parse(
    await evaluate("globalThis.KanamaWebBridge.api.kanamaWebGenericCallProbeReport()"),
  );
  trace(`generic: ${JSON.stringify(generic)}`);

  // Task 82 coroutine conformance probe. Main.coroutine_probe (method#19) launches ONE coroutine
  // on the script's own scope that awaits both delay shapes gameplay uses -- the wait-one-frame
  // safe point delaySeconds(0.0) and a timed delaySeconds -- then posts to the main thread.
  // Main.coroutine_probe_mask (method#20, Int->Int) reports how far it got.
  //
  // This is the generic version of the bug task 82 fixed: before the fix the frame scheduler was
  // pumped only for four hardcoded "Main" handles, so in eight of twelve demos a launched
  // coroutine stopped at its first delay and NOTHING threw. A pumped scheduler reaches 31; an
  // unpumped one never gets past bit 1, because `launch` dispatches even its first continuation
  // through the same scheduler. Asserting "no error" would have passed either way.
  const readCoroutineMask = () =>
    evaluate(
      "globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, 20, 0)",
    ).then(Number);
  trace("coroutine_probe");
  const maskBeforeArm = await readCoroutineMask();
  await evaluate(
    "globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.web3dMainHandle, 19); true",
  );
  let coroutineMask = await readCoroutineMask();
  const coroutineDeadline = Math.min(deadline, Date.now() + 15_000);
  while (coroutineMask !== 31 && Date.now() < coroutineDeadline) {
    await delay(150);
    coroutineMask = await readCoroutineMask();
  }
  const afterCoroutine = (await snapshot(evaluate)) ?? atPeak;
  trace(
    `coroutine: mask=${coroutineMask} (was ${maskBeforeArm}) pumps=${afterCoroutine.pumps} continuations=${afterCoroutine.continuations}`,
  );

  // Full teardown: SmokeQuit.smoke_teardown (method#1) frees the scene root, draining handles.
  trace("smoke_teardown");
  await evaluate(
    "globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.web3dSmokeQuitHandle, 1); true",
  );
  const teardown = await observe(evaluate, peak, 6_000, deadline, (snap) => snap.liveHandles === 0);
  const settled = teardown.last ?? atPeak;
  trace(`teardown: live=${settled.liveHandles} errs=${settled.callbackErrors}`);

  const protocolVersion = ready.protocol;
  const checks = {
    modeWeb3d: ready.mode === "web3d",
    sceneReady: ready.mainReady >= 1,
    // 66b: the bridge crossing fired and the Kotlin @OnEnterTree body observed it.
    enterTreeDispatched: ready.enterTreeCalls >= 1 && (enterTreeProbe & 1) === 1,
    // 66b: exported property visible at _enter_tree (bit 2) AND it ran before _ready (bit 4).
    enterTreePropertyOrdering: enterTreeProbe === 7,
    // Task 64: the scene-exported NodePath value was pushed into the Kotlin instance.
    nodePathPropertyPushed: (propertyProbe & 1) === 1,
    // Task 64: the scene value of the RANGE-hinted one-line-annotated export arrived in Kotlin
    // (initializer parser + hint emission end to end).
    rangeHintPropertyPushed: (propertyProbe & 2) === 2,
    // Task 64: the pushed NodePath resolves a live node through the NodePath accessor overload.
    nodePathResolvesNode: (propertyProbe & 4) === 4,
    // Task 80 slice 4: EVERY typed property arm delivered the scene's value, not merely a
    // dispatch. Each Kotlin default is wrong on purpose, so a shape that fails to push leaves
    // its default behind and clears its bit -- "arrived" and "looks plausible" cannot be
    // confused. Bits 8..2048 = String, Int, Float, Bool, Vector2, Vector3, Vector2i, Object,
    // String[].
    //
    // Expected 3071, NOT 4095: bit 1024 (the OBJECT arm, an exported NODE reference) does not
    // arrive. MEASURED on this fixture's first run, and the fixture is spelled exactly like the
    // corpus -- `probe_object = NodePath("Spinner")` in the scene, the same form City-Builder
    // uses for `selector` / `view_camera`, pointing at a live Node3D child that bit 4 proves
    // resolves. The generated proxy emits the push correctly too, so this is neither a scene
    // typo nor a missing emitter arm. Leading hypothesis: Godot resolves an exported NodePath
    // to its object as the node enters the tree, while `_kanama_ensure_created` can push
    // properties BEFORE that (the 60i "a script instance exists before _ready" rule), so the
    // push reads null. NOT confirmed -- recorded in kanama-tasks/80.
    //
    // Gated at the measured-working set deliberately: a red gate nobody can act on teaches
    // nothing, and silently dropping the bit from the mask would hide exactly what slice 4
    // exists to find. When the object arm is fixed this returns 4095 and fails until updated.
    propertyShapesDeliverValues: propertyProbe === 3071,
    // Task 80 slice 2: every admitted dispatch shape round-tripped its VALUE, not just its call.
    dispatchShapesRoundTrip: dispatchProbe === 127,
    // _process ran many frames (the spinner) with its Node3D.rotation mutations applied.
    renderFramesAdvanced: peak.processCalls >= ready.processCalls + 10,
    transformCommandsApplied: peak.appliedCommands >= ready.appliedCommands + 10,
    // Task-64 parity: fromHandle + setStream ran clean (a throw would leave the aim stale)
    // and lookAt's useModelFront flips the forward axis by exactly PI.
    parityDefaultLook: angleNear(defaultYaw, -HALF_PI),
    parityModelFrontLook: angleNear(modelFrontYaw, HALF_PI),
    parityModelFrontFlip: angleNear(modelFrontYaw - defaultYaw, Math.PI),
    // Task 76 (generic callv fallback). (a) A queued generic mutation on a method
    // outside the admitted typed opcodes (set_meta) applied...
    genericQueuedMutationApplied: generic.metaTag === "i" && generic.metaValue === 42,
    // ...(b) proven by an immediate generic call reading a primitive back (is_in_group
    // after a queued generic add_to_group).
    genericImmediatePrimitiveReadback: generic.groupTag === "b" && generic.groupValue === true,
    // Escaping: a string full of separators / colons / percent look-alikes round-trips
    // through queued args and the string-return payload unchanged.
    genericHostileStringRoundTrip: generic.hostileRoundTrip === true,
    // (c) Object returns resolve to ALREADY-TRACKED handles first: a script-backed node
    // (spinner.get_parent() -> Main, kind "script") and a tracked engine node
    // (main.get_node("Spinner"), kind "tracked" via the is_same scan).
    genericObjectReturnsTrackedHandles:
      generic.parentTag === "o" &&
      generic.parentKind === "script" &&
      generic.parentHandle === ready.mainHandle &&
      generic.parentHandle === generic.mainHandle &&
      generic.childTag === "o" &&
      generic.childKind === "tracked" &&
      generic.childHandle > 0 &&
      generic.childHandle === generic.spinnerHandle,
    // Minting policy: an untracked engine Node (get_window) mints a NODE-kind handle —
    // deliberately left unclosed, so fullTeardownToZero below proves owner teardown
    // drains minted handles.
    genericMintsNodeHandle: generic.windowKind === "node" && generic.windowHandle > 0,
    // An untracked RefCounted non-Resource (get_multiplayer) mints a plain OBJECT-kind
    // handle, closed through the OBJECT release lane.
    genericMintsObjectHandleAndCloses:
      generic.multiplayerKind === "object" &&
      generic.multiplayerHandle > 0 &&
      generic.closedObjectOk === true,
    // An untracked Resource (Environment.duplicate) mints a RESOURCE-kind handle.
    genericMintsResourceHandle: generic.dupKind === "resource" && generic.dupHandle > 0,
    // Task-61 handoff-then-close: the minted resource is handed to the engine (queued
    // generic set_environment), is then discoverable as "tracked" under OUR handle,
    // survives our close() via the engine's own reference, and its meta tag reads back
    // through a re-minted handle.
    genericHandoffThenCloseSurvives:
      generic.handoffTrackedOk === true && generic.handoffSurvivedClose === true,
    // The generic-vs-typed crossing cost was measured over N iterations of the same call.
    genericCostMeasured:
      generic.iterations === 500 &&
      generic.genericHits === 500 &&
      generic.typedHits === 500 &&
      generic.genericMs > 0 &&
      generic.typedMs > 0,
    // Task 82 (a) the frame scheduler is advanced at all, without this demo naming a "Main"
    // handle anywhere -- the pump rides the _process dispatch every proxy emits.
    frameSchedulerPumped: afterCoroutine.pumps >= 10,
    // ...(b) and a launched coroutine RESUMED past both delay shapes and ran its main-thread
    // post. Bit 1 armed, 2 body entered, 4 past delaySeconds(0.0), 8 past a timed delay,
    // 16 MainThread.post ran. An unpumped scheduler stalls at 3 and throws nothing.
    coroutineDelayResumed: coroutineMask === 31,
    // The probe had NOT already run before the driver armed it: the mask is a fresh observation,
    // not a leftover from startup.
    coroutineProbeArmedByDriver: maskBeforeArm === 0,
    fullTeardownToZero: settled.liveHandles === 0,
    noCallbackFaults: peak.callbackErrors === 0 && settled.callbackErrors === 0 && settled.failure === null,
  };

  const boundaryErrors = [];
  if (peak.callbackErrors !== 0) boundaryErrors.push(`callbackErrors=${peak.callbackErrors}`);
  if (settled.failure !== null) boundaryErrors.push(`failure: ${settled.failure}`);

  return {
    protocolVersion,
    startup: {
      loaded: ready.mode === "web3d",
      outcome: ready.mode === "web3d" ? "ready" : "failed",
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
      frameSchedulerPumps: afterCoroutine.pumps,
      frameSchedulerContinuations: afterCoroutine.continuations,
      appliedCommands: peak.appliedCommands,
      // Task 76 spike cost measurement (schema requires non-negative numbers, so the
      // millisecond totals ride x1000 and the generic/typed ratio rides x100).
      genericProbeIterations: generic.iterations,
      genericImmediateMsX1000: Math.max(0, Math.round(generic.genericMs * 1000)),
      typedImmediateMsX1000: Math.max(0, Math.round(generic.typedMs * 1000)),
      genericVsTypedRatioX100:
        generic.typedMs > 0 ? Math.max(0, Math.round((generic.genericMs / generic.typedMs) * 100)) : 0,
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

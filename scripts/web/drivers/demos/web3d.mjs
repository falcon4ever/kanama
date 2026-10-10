import { resolveMethodId } from "../envelope.mjs";
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
        // Task 131 item 14: the contained script exceptions (a subset of callbackErrors) and the
        // texts reported to Godot's error log.
        scriptErrors: bridge.scriptErrors ?? 0,
        scriptErrorReports: bridge.scriptErrorReports ?? [],
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
      peak.scriptErrors = Math.max(peak.scriptErrors, snap.scriptErrors);
      last = snap;
      trace(`process=${snap.processCalls} applied=${snap.appliedCommands} live=${snap.liveHandles} max=${snap.maxLiveHandles} errs=${snap.callbackErrors}`);
      if (predicate && predicate(snap, peak)) break;
    }
    await delay(150);
  }
  return { last, peak };
}

export async function runWeb3d({ url, evaluate, navigate, deadline, exportDir }) {
  // Task 80 slice 4: resolve probe ids from the export manifest, never hardcode them.
  // Adding ONE registered function renumbers the rest -- `signal_probe` took id 7 and pushed
  // `property_probe` to 8 and `dispatch_probe` to 17. A hardcoded id then dispatches a
  // DIFFERENT method and still returns a number, so the check keeps "passing" while testing
  // something else entirely. Same trap slice 6 hit on match3.
  const probeId = (name) => {
    const id = resolveMethodId(exportDir, "web3d.Main", name);
    if (!id) throw new Error(`web3d: ${name} not found in the export manifest`);
    return id;
  };
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
  // bit 1 = @OnEnterTree dispatched, bit 2 = the scene-exported @Export value
  // ("web3d-enter-tree", never the default) was visible inside it, bit 4 = it ran before
  // @OnReady. A healthy protocol-16 run returns exactly 7.
  const enterTreeProbe = Number(
    await evaluate(
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("enter_tree_probe")}, 0)`,
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
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("property_probe")}, 0)`,
    ),
  );
  trace(`propertyProbe: mask=${propertyProbe}`);

  // Task 64 Curve + Resource-typed hydration proof: Main.curve_sample_probe (Int->Int) returns
  // a mask -- bit 1 = the scene-exported `Curve?` property hydrated non-null over the generic
  // OBJECT property arm, bit 2 = Curve.sample (opcode 306) read back the VALUE a linear
  // two-point curve predicts at its midpoint. A healthy run returns 3.
  const curveProbe = Number(
    await evaluate(
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("curve_sample_probe")}, 0)`,
    ),
  );
  trace(`curveProbe: mask=${curveProbe}`);

  // Task 64 DemoPage set (protocol 24): Main.demo_page_probe (Int->Int) returns a mask -- bit 1 =
  // SceneTree.is_paused read the pause back, bit 2 = Input.get_connected_joypads answered (the new
  // Long-list singleton shape; empty on a headless runner), bit 4 = Environment SSIL/SDFGI toggles
  // were queued, bit 8 = Control.release_focus was queued, bit 16 = a postAfterFrames(3) chain was
  // scheduled; demo_page_probe_after reads 1 once that chain ran (checked after the coroutine
  // section pumped frames). A healthy run returns 31 then 1.
  const demoPageProbe = Number(
    await evaluate(
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("demo_page_probe")}, 0)`,
    ),
  );
  trace(`demoPageProbe: mask=${demoPageProbe}`);

  // Task 64 CameraMode family (protocol 25): Main.camera_mode_probe (Int->Int) returns a mask --
  // bit 1 = a Kotlin-constructed Camera3D made current took and read back its fov, bit 2 =
  // SceneTree.get_nodes_in_group returned the fixture's Spinner as the same instance, bit 4 =
  // Input.is_key_pressed is false for W on a headless runner, bit 8 = Input.get_last_mouse_velocity
  // answered a finite Vector2, bit 16 = the previous camera is current again after the probe camera
  // Task 80 slice 4: signal-shape conformance (see Main.signal_probe).
  const signalProbe = Number(
    await evaluate(
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("signal_probe")}, 0)`,
    ),
  );
  trace(`signalProbe: mask=${signalProbe}`);

  // Task 64: the scalar tween arm (see Main.scalar_tween_probe). 1 = a NUMBER final value
  // produced a live PropertyTweener instead of faulting the boundary.
  const scalarTween = Number(
    await evaluate(
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("scalar_tween_probe")}, 0)`,
    ),
  );
  trace(`scalarTweenProbe: ${scalarTween}`);

  // Task 64 tier 2: PropertyTweener.from (see Main.tweener_from_probe).
  const tweenerFrom = Number(
    await evaluate(
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("tweener_from_probe")}, 0)`,
    ),
  );
  trace(`tweenerFromProbe: ${tweenerFrom}`);

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
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("dispatch_probe")}, 0)`,
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
    scriptErrors: 0,
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
  // Task 118: both aims turn the whole level (the probes aim the ROOT). Turn it back before anything else
  // runs: left turned, the Player walks off the floor and its DownRay check faults (the old "teardown race").
  await evaluate(
    `globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("parity_restore")}); true`,
  );
  const restoredYaw = await readGlobalYaw();
  trace(`parity_restore: yaw=${restoredYaw}`);
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

  // Task 64 tier 3: InputMap / InputEventKey / process mode / Window mode (see Main.input_map_probe).
  // Runs AFTER generic_probe on purpose: the probe wraps the root Window through the typed
  // SceneTree.get_root path, which TRACKS that window in Main's handle table, and the generic
  // probe's genericMintsNodeHandle check needs the same window still UNTRACKED when it asks
  // get_window (an already-tracked object is reported "tracked", not minted). Same object, two
  // proofs -- order is the coupling, stated here and in Main.kt.
  const inputMapProbe = Number(
    await evaluate(
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("input_map_probe")}, 0)`,
    ),
  );
  trace(`inputMapProbe: ${inputMapProbe}`);

  // Task 64 CameraMode family. Runs AFTER generic_probe / input_map_probe on purpose: the probe wraps
  // the viewport (root Window) through the typed path, which TRACKS it; genericMintsNodeHandle needs
  // the window still untracked (same coupling as input_map_probe, stated in Main.kt).
  // is freed. A healthy run returns 31.
  const cameraModeProbe = Number(
    await evaluate(
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("camera_mode_probe")}, 0)`,
    ),
  );
  trace(`cameraModeProbe: mask=${cameraModeProbe}`);

  // Task 64 tps-demo parcel 6 (protocol 26): Main.node_lifecycle_probe -- is_inside_tree /
  // is_queued_for_deletion on a node created and added by Kotlin. Healthy = 15.
  const nodeLifecycleProbe = Number(
    await evaluate(
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("node_lifecycle_probe")}, 0)`,
    ),
  );
  trace(`nodeLifecycleProbe: mask=${nodeLifecycleProbe}`);

  // Task 64 tps-demo parcel 8 (protocol 28): the render-quality family (322-327) plus the two
  // immediate string reads (328/329) and the queued Viewport.set_input_as_handled (330).
  // Runs AFTER generic_probe like the other viewport-wrapping probes. Healthy = 63.
  const renderSettingsProbe = Number(
    await evaluate(
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("render_settings_probe")}, 0)`,
    ),
  );
  trace(`renderSettingsProbe: mask=${renderSettingsProbe}`);

  // Task 64 tps-demo parcel 8 (protocol 28): Node.get_window as a member, Control.set_position /
  // set_size (331/332) round-tripped through get_position / get_size, the narrowed
  // Node.propagate_call, and the now-nullable LONG_OBJECT_ARG slot
  // (Mesh.surface_set_material(0, null)). Healthy = 31.
  const windowFamilyProbe = Number(
    await evaluate(
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("window_family_probe")}, 0)`,
    ),
  );
  trace(`windowFamilyProbe: mask=${windowFamilyProbe}`);

  // Task 134 D1 (protocol 32): Web parity for signals and value types (see Main.d1_probe). Bits:
  // 1 = a three-argument @Signal reached its typed lambda intact, 2 = five value-type arguments,
  // 4 = the engine's five-argument CollisionObject3D.input_event through its generated Signal5,
  // 8 = builtin methods run by the engine (instance, static, Variant hit/miss, Basis.slerp),
  // 16 = Vector4/Vector4i/AABB/Transform2D/Projection exports hydrated, 32 = arguments AND a
  // return through the proxy, 64 = doubles bit-exact both ways (signal, argument, return), 128 =
  // a builtin call the engine rejects throws in Kotlin. Healthy = 255. It also arms awaits that
  // d1_probe_after reads after the coroutine section's pumps: 1 = an await on a non-script engine
  // emitter resumed, 2 = an await whose emitter was freed first was cancelled instead of hanging,
  // 4 = another script's await is a connection on D1Emitter, 16 = Node.duplicate() did not copy
  // it, 8 = freeing that script first disconnected it (healthy = 31).
  const d1Probe = Number(
    await evaluate(
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("d1_probe")}, 0)`,
    ),
  );
  trace(`d1Probe: mask=${d1Probe}`);

  // Shared-node-handle fix: two scripts look up the same plain node, the first is freed, the second
  // keeps calling it and a third looks it up again; then the node itself is freed under its owners
  // (see Main.share_probe). Armed here, read back after the pumps (healthy = 31).
  await evaluate(
    `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("share_probe")}, 0)`,
  );

  // Task 131 item 12: lambda signal connections give their Kotlin callback back with whatever held
  // them -- a fired one-shot, an emitter freed by the engine, the receiving script freed (see
  // Main.leak_probe). Armed here, read back after the pumps (healthy = 31).
  await evaluate(
    `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("leak_probe")}, 0)`,
  );

  // Task 131 item 14: callbacks that throw on purpose -- a function, a signal handler, _process and a
  // coroutine -- are reported to Godot's error log and contained (see Main.error_probe).
  const errorProbeFrames = (await snapshot(evaluate))?.processCalls ?? 0;
  const errorProbe = Number(
    await evaluate(
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("error_probe")}, 0)`,
    ),
  );
  trace(`errorProbe armed: ${errorProbe}`);

  // Task 138 item 3: a node the engine frees under a script that holds it (see Main.freed_probe).
  await evaluate(
    `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("freed_probe")}, 0)`,
  );

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
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("coroutine_probe_mask")}, 0)`,
    ).then(Number);
  // Task 64 tps-demo parcel 7: arm the SceneTree.create_timer probe before the coroutine section so
  // the frames it pumps also carry the 50 ms timer to its timeout; read the mask after it.
  await evaluate(
    `globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("timer_probe")}); true`,
  );
  // A typed await() on a plain engine emitter (see Main.typed_await_probe), read after the section.
  await evaluate(
    `globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("typed_await_probe")}); true`,
  );
  trace("coroutine_probe");
  const maskBeforeArm = await readCoroutineMask();
  await evaluate(
    `globalThis.KanamaWebBridge.callNoArgs(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("coroutine_probe")}); true`,
  );
  let coroutineMask = await readCoroutineMask();
  const coroutineDeadline = Math.min(deadline, Date.now() + 15_000);
  while (coroutineMask !== 31 && Date.now() < coroutineDeadline) {
    await delay(150);
    coroutineMask = await readCoroutineMask();
  }
  const afterCoroutine = (await snapshot(evaluate)) ?? atPeak;
  const timerMask = Number(
    await evaluate(
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("timer_probe_mask")}, 0)`,
    ),
  );
  trace(`timerProbe: mask=${timerMask}`);
  const readTypedAwait = () =>
    evaluate(
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("typed_await_probe_mask")}, 0)`,
    ).then(Number);
  let typedAwaitMask = await readTypedAwait();
  const typedAwaitDeadline = Math.min(deadline, Date.now() + 5_000);
  while (typedAwaitMask !== 1 && Date.now() < typedAwaitDeadline) {
    await delay(150);
    typedAwaitMask = await readTypedAwait();
  }
  trace(`typedAwaitProbe: mask=${typedAwaitMask}`);
  trace(
    `coroutine: mask=${coroutineMask} (was ${maskBeforeArm}) pumps=${afterCoroutine.pumps} continuations=${afterCoroutine.continuations}`,
  );

  // Full teardown: SmokeQuit.smoke_teardown (method#1) frees the scene root, draining handles.
  const demoPageProbeAfter = Number(
    await evaluate(
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("demo_page_probe_after")}, 0)`,
    ),
  );
  trace(`demoPageProbeAfter: ${demoPageProbeAfter}`);
  const readD1After = () =>
    evaluate(
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("d1_probe_after")}, 0)`,
    ).then(Number);
  let d1ProbeAfter = await readD1After();
  const d1Deadline = Math.min(deadline, Date.now() + 10_000);
  while (d1ProbeAfter !== 31 && Date.now() < d1Deadline) {
    await delay(150);
    d1ProbeAfter = await readD1After();
  }
  trace(`d1ProbeAfter: ${d1ProbeAfter}`);
  const readShareAfter = () =>
    evaluate(
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("share_probe_after")}, 0)`,
    ).then(Number);
  let shareProbeAfter = await readShareAfter();
  const shareDeadline = Math.min(deadline, Date.now() + 10_000);
  while (shareProbeAfter !== 31 && Date.now() < shareDeadline) {
    await delay(150);
    shareProbeAfter = await readShareAfter();
  }
  trace(`shareProbeAfter: ${shareProbeAfter}`);
  const readFreedAfter = () =>
    evaluate(
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("freed_probe_after")}, 0)`,
    ).then(Number);
  let freedProbeAfter = await readFreedAfter();
  const freedDeadline = Math.min(deadline, Date.now() + 10_000);
  while (freedProbeAfter !== 1023 && Date.now() < freedDeadline) {
    await delay(150);
    freedProbeAfter = await readFreedAfter();
  }
  trace(`freedProbeAfter: ${freedProbeAfter}`);
  // Batch of two (Main.freed_batch_probe): a setter on the freed node, then a command on a live one,
  // in one batch. The freed one is skipped and reported; the live command must still apply, and a
  // live `queueFree` after the skip must release its bridge slot (live handles back to where they
  // were).
  const liveBeforeBatch = (await snapshot(evaluate))?.liveHandles;
  await evaluate(
    `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("freed_batch_probe")}, 0)`,
  );
  const readFreedBatchAfter = () =>
    evaluate(
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("freed_batch_probe_after")}, 0)`,
    ).then(Number);
  let freedBatchAfter = await readFreedBatchAfter();
  const freedBatchDeadline = Math.min(deadline, Date.now() + 10_000);
  while (freedBatchAfter !== 7 && Date.now() < freedBatchDeadline) {
    await delay(150);
    freedBatchAfter = await readFreedBatchAfter();
  }
  const liveAfterBatch = (await snapshot(evaluate))?.liveHandles;
  trace(`freedBatchAfter: ${freedBatchAfter} liveHandles ${liveBeforeBatch} -> ${liveAfterBatch}`);
  const readLeakAfter = () =>
    evaluate(
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("leak_probe_after")}, 0)`,
    ).then(Number);
  let leakProbeAfter = await readLeakAfter();
  const leakDeadline = Math.min(deadline, Date.now() + 10_000);
  while (leakProbeAfter !== 31 && Date.now() < leakDeadline) {
    await delay(150);
    leakProbeAfter = await readLeakAfter();
  }
  trace(`leakProbeAfter: ${leakProbeAfter}`);

  // The seven deliberate script errors have landed: three at scene load (constructor, property
  // setter, _ready), the rest when ErrorProbe's calls run (the process and coroutine ones a frame or
  // two after the arm); and the frame loop went on past them.
  // Kind -> the containment site the report's `at:` line names (a Wasm trace has no Kotlin frame).
  const expectedSites = {
    function: "ErrorProbe.error_throw",
    signal: "ErrorProbe.<signal handler>",
    process: "ErrorProbe._process",
    coroutine: "ErrorProbe.<coroutine>",
    constructor: "ThrowingConstructor.<init>",
    property: "ThrowingProperty.gate",
    ready: "ThrowingReady._ready",
  };
  const expectedScriptErrors = Object.keys(expectedSites);
  // ...plus the queued setters on a node the engine freed: FreedHolder.freed_setter (one) and the
  // two batches of FreedHolder.freed_batch (one each). Each is reported once as a script error
  // naming the freed instance, and none is counted among the deliberate throws.
  const freedSetterReports = 3;
  const expectedReportTotal = expectedScriptErrors.length + freedSetterReports;
  let errorSnap = null;
  const errorDeadline = Math.min(deadline, Date.now() + 10_000);
  while (Date.now() < errorDeadline) {
    errorSnap = await snapshot(evaluate);
    if (errorSnap && errorSnap.scriptErrors >= expectedReportTotal) break;
    await delay(150);
  }
  const errorProbeAfter = Number(
    await evaluate(
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("error_probe_after")}, 0)`,
    ),
  );
  const errorReports = errorSnap?.scriptErrorReports ?? [];
  trace(`errorProbeAfter: ${errorProbeAfter} scriptErrors=${errorSnap?.scriptErrors} frames=${errorProbeFrames}->${errorSnap?.processCalls}`);
  trace(`errorReports: ${JSON.stringify(errorReports)}`);

  // Script-initializer engine calls: InitProbe's property initializers call an engine singleton and
  // construct a RefCounted, read `self`, and run its @OnReady (Main.init_probe reads it back;
  // healthy = 255: one bit per row, see InitProbe).
  const initProbe = Number(
    await evaluate(
      `globalThis.KanamaWebBridge.callInt(globalThis.KanamaWebBridge.web3dMainHandle, ${probeId("init_probe")}, 0)`,
    ),
  );
  trace(`initProbe: ${initProbe}`);

  // KANAMA_WEB3D_EXTRA_PLAY_MS=<ms>: keep the level running this long before teardown. A timing-dependent
  // defect in the fixture (the Player drifting off its floor, task 118) fires with a small probability per
  // second of play; a long window makes it near-certain, which is this gate's own red run. Unset = no wait.
  const extraPlayMs = Number(process.env.KANAMA_WEB3D_EXTRA_PLAY_MS ?? 0);
  if (!Number.isFinite(extraPlayMs) || extraPlayMs < 0) {
    throw new Error(`web3d: KANAMA_WEB3D_EXTRA_PLAY_MS must be a number >= 0 (got ${process.env.KANAMA_WEB3D_EXTRA_PLAY_MS})`);
  }
  if (extraPlayMs > 0) {
    trace(`extra play: ${extraPlayMs}ms`);
    await observe(evaluate, peak, extraPlayMs, deadline);
  }

  // KANAMA_WEB3D_INJECT_RUNTIME_FAULT=1: the red run for the containment boundary (task 131 item 14).
  // A signal dispatch with a callback id the registry never issued is a RUNTIME invariant failing
  // inside a boundary, not a script error: it must end the page (a fatal failure naming the id), where
  // a contained script error carries on. The run is then supposed to fail every other row; the
  // evidence is `runtimeFaultIsFatal`. Unset = no injection.
  const injectRuntimeFault = process.env.KANAMA_WEB3D_INJECT_RUNTIME_FAULT === "1";
  let injectedFailure = null;
  if (injectRuntimeFault) {
    await evaluate(
      "globalThis.KanamaWebBridge.dispatchSignal0(globalThis.KanamaWebBridge.web3dMainHandle, 2147483000); true",
    );
    // The page ended (the fatal path sets data-status="fail") and the bridge's last callback error is
    // the registry's own invariant, not a contained script error.
    injectedFailure = await evaluate(
      "document.body.dataset.status === 'fail' ? String(globalThis.KanamaWebBridge.lastCallbackError ?? '') : null",
    );
    trace(`injected runtime fault: failure=${injectedFailure?.split("\n")[0]}`);
  }

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
    // A healthy run returns 4095 -- every typed arm, INCLUDING the OBJECT arm.
    //
    // The object arm briefly looked like a gap: bit 1024 was clear, and the scene carried
    // `probe_object = NodePath("Spinner")`, the same value line City-Builder uses. The value
    // line is not the whole story -- Godot only resolves an exported NodePath into a node
    // REFERENCE when the node header declares it: `node_paths=PackedStringArray("probe_object")`.
    // City-Builder's Builder node has that attribute; this fixture did not. Nothing was wrong
    // with the backend, and the corpus was never affected (fps and City-Builder both `error()`
    // on a null reference and both pass).
    propertyShapesDeliverValues: propertyProbe === 4095,
    // Task 64: Curve + Resource-typed hydration -- the exported Curve resource hydrated
    // non-null and Curve.sample reads back the VALUE the linear probe curve predicts, not
    // merely a successful call.
    curveResourceHydrationDeliversValue: curveProbe === 3,
    // Task 64 DemoPage set: pause read-back, joypad enumeration, Environment toggles, focus release
    // and the postAfterFrames hop chain (the readback runs after the coroutine section's pumps).
    demoPageSetDelivers: demoPageProbe === 31 && demoPageProbeAfter === 1,
    // Task 64 CameraMode family: Kotlin-constructed camera + fov round-trip, group query identity,
    // key polling and mouse velocity, previous camera restored.
    cameraModeFamilyDelivers: cameraModeProbe === 31,
    // Task 64 tps-demo parcel 7: SceneTree.create_timer handle + its timeout awaited (7).
    sceneTreeTimerDelivers: timerMask === 7,
    // A typed await() on an engine emitter with no Kanama script (charactercontroller's flag
    // reload, third-person's box/coin/grenade sounds) resumes instead of failing to connect.
    typedAwaitOnEngineEmitterResumes: typedAwaitMask === 1,
    // Task 64 tps-demo parcel 6: node lifecycle queries (is_inside_tree, is_queued_for_deletion).
    nodeLifecycleDelivers: nodeLifecycleProbe === 15,
    // Task 64 tps-demo parcel 8: the render-quality family reached the engine (driver name, OS
    // name, the two Long writes, the two six-value quality writes, the queued Environment
    // toggles, set_input_as_handled).
    renderSettingsFamilyDelivers: renderSettingsProbe === 63,
    // Task 64 tps-demo parcel 8: get_window, Control.set_position / set_size round-trips, the
    // narrowed propagate_call, and the nullable object slot clearing a mesh surface material.
    windowFamilyDelivers: windowFamilyProbe === 31,
    // Task 134 D1: multi-argument and value-type signals, the engine builtin-call path, the five new
    // script types, arguments + return, an await that resumes on an engine emitter, and an await
    // cancelled by its emitter's free.
    webParitySignalsAndValueTypes: d1Probe === 255 && d1ProbeAfter === 31,
    // A plain node's handle shared by two scripts survives the first script's free (owner counting),
    // a later lookup of it works, and a node freed under its owners fails cleanly.
    sharedNodeHandleSurvivesFirstFree: shareProbeAfter === 31,
    // Task 138 item 3: using a node the engine freed under its holder throws the freed-instance
    // error (catchable, like desktop's), and the holder keeps working.
    engineFreedNodeThrowsFreedInstanceError: freedProbeAfter === 1023,
    // A skipped command (a setter on a freed node) does not hide the commands around it: in a
    // batch of two the live command still applies, and a live queueFree after the skip releases
    // its slot (the applied commands are not a prefix of the batch).
    skippedFreedCommandKeepsTheRestOfTheBatch:
      freedBatchAfter === 7 && liveBeforeBatch !== undefined && liveAfterBatch === liveBeforeBatch,
    // Task 131 item 12: a lambda connection's Kotlin callback is dropped with its one-shot firing,
    // its emitter's free and its receiver's free (desktop's SignalCallbackRegistry rules).
    lambdaConnectionsReleaseTheirCallbacks: leakProbeAfter === 31,
    // Task 131 item 14: every deliberate throw (function, signal handler, _process, coroutine) was
    // contained -- the call came back, the script still answers, the frame loop kept running and no
    // other bridge fault appeared.
    scriptErrorsAreContained:
      errorProbe === 0 &&
      errorProbeAfter === 7 &&
      (errorSnap?.scriptErrors ?? 0) === expectedReportTotal &&
      (errorSnap?.processCalls ?? 0) >= errorProbeFrames + 5,
    // ...and each was reported to Godot's error log exactly once with the exception type, the
    // message and the "at:" frame line (the browser console must show the same ones: see
    // expectedConsoleErrors below).
    scriptErrorsAreReported:
      errorReports.length === expectedReportTotal &&
      // A setter queued on a node the engine freed is reported once as the freed-instance
      // IllegalStateException (desktop reports and carries on) and the page kept running (the
      // freed probe's bit 512 is the frames after it).
      errorReports.filter(
        (text) =>
          text.startsWith("SCRIPT ERROR: ") &&
          text.includes("IllegalStateException") &&
          text.includes("Invalid access to previously freed instance") &&
          text.includes("<queued command>"),
      ).length === freedSetterReports &&
      expectedScriptErrors.every(
        (kind) =>
          errorReports.filter(
            (text) =>
              text.startsWith("SCRIPT ERROR: ") &&
              text.includes("IllegalStateException") &&
              text.includes(`web3d deliberate script error (${kind})`) &&
              text.includes(`\n   at: ${expectedSites[kind]} (`),
          ).length === 1,
      ),
    // A script whose property initializers call an engine singleton and construct a RefCounted
    // constructs on Web as it does on desktop (the tps-demo Settings autoload's boot failure).
    scriptInitializersCallEngine: initProbe === 255,
    // Task 80 slice 4, signal shapes: bit 1 = a ZERO-argument signal reached a Kotlin lambda,
    // bit 2 = a ONE-OBJECT signal delivered a live handle. The scalar shape is dispatch_probe
    // bit 32. The two-argument shape is absent because it CANNOT BE DECLARED: slice 3 makes an
    // argument-dropped member a build error, so the fixture failed to compile when this probe
    // first tried it -- see the note on signal_probe in Main.kt.
    signalShapesDeliverPayloads: signalProbe === 3,
    // Task 64: tween_property with a NUMBER. Vector2/Color/Vector3 all had arms and this one
    // did not, so third-person's coin spill faulted the Web boundary on a component path.
    scalarTweenArmDelivers: scalarTween === 1,
    // Task 64 tier 2: from() returned the SAME tweener, which is what the fluent contract
    // promises -- a dropped call returns null and clears this.
    propertyTweenerFromDelivers: tweenerFrom === 1,
    // Task 64 tier 3: every InputMap / InputEventKey / process-mode / Window-mode family delivered
    // its VALUE -- has_action flips on add and erase, two queued keycodes read back, is_action
    // flips on attach, the new action presses, process_mode reads 3, the root window reports a
    // legal mode, and (task 128 C, protocol 29) a constructed InputEventMouseButton reads its
    // button back and binds to the action. Any dropped call clears a bit.
    inputMapFamiliesDeliverValues: inputMapProbe === 511,
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
    // Task 118: the parity probes turn the ROOT; the level must be upright again afterwards, or the Player
    // (world-axis velocity, local-axis pacing) walks off its floor and its DownRay check faults at random.
    levelUprightAfterParity: angleNear(restoredYaw, 0),
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
    ...(injectRuntimeFault
      ? {
          runtimeFaultIsFatal:
            injectedFailure !== null &&
            injectedFailure.includes("Stale Kanama Web signal callback id") &&
            !injectedFailure.includes("script error (contained)"),
        }
      : {}),
    // The deliberate script errors above are counted in callbackErrors too (so every other demo's
    // zero-errors check sees a throwing script). Exactly those are subtracted -- never every
    // scriptError -- and the count must be exactly the expected one, so an unexpected script error
    // (or one fewer) fails here instead of being absorbed.
    noCallbackFaults:
      settled.callbackErrors === expectedReportTotal &&
      settled.scriptErrors === expectedReportTotal &&
      settled.failure === null,
  };

  const boundaryErrors = [];
  // callbackErrors only counts up, so the settled sample covers the whole run.
  if (settled.callbackErrors !== expectedReportTotal) {
    boundaryErrors.push(
      `callbackErrors=${settled.callbackErrors} (expected only the ${expectedScriptErrors.length} deliberate script errors and the ${freedSetterReports} freed-node setter report)`,
    );
  }
  if (settled.scriptErrors !== expectedReportTotal) {
    boundaryErrors.push(`scriptErrors=${settled.scriptErrors} (expected ${expectedReportTotal})`);
  }
  if (settled.failure !== null) boundaryErrors.push(`failure: ${settled.failure}`);

  return {
    protocolVersion,
    startup: {
      loaded: ready.mode === "web3d",
      outcome: ready.mode === "web3d" ? "ready" : "failed",
      durationMs: startupDurationMs,
    },
    checks,
    // The deliberate script errors reach the browser console through Godot's push_error.
    expectedConsoleErrors: [
      { pattern: "web3d deliberate script error", count: expectedScriptErrors.length },
      { pattern: "previously freed instance", count: freedSetterReports },
    ],
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
        checks.fullTeardownToZero &&
        settled.callbackErrors === expectedScriptErrors.length &&
        settled.scriptErrors === expectedScriptErrors.length &&
        settled.failure === null
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

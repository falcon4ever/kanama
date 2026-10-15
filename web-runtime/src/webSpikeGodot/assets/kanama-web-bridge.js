(() => {
  "use strict";

  const WARMUP_FRAMES = 30;
  const SAMPLE_FRAMES = 120;
  const OPERATIONS = 10_000;
  const BENCHMARK_WARMUP_TRIALS = 5;
  const INDIVIDUAL_WARMUP_TRIALS = 1;
  const BROWSER_HANDLE_NAMESPACE = 0x40000000;
  const BROWSER_HANDLE_SLOT_MASK = 0xffff;
  const BROWSER_HANDLE_GENERATION_MASK = 0x3fff;
  const KANAMA_WEB_PROTOCOL_VERSION = 38;
  // Prefix of a contained script error's message (Main.kt WEB_SCRIPT_ERROR_MARKER).
  const SCRIPT_ERROR_MARKER = "Kanama Web script error (contained):";
  // Added after the marker when Godot's error log could not be reached (Main.kt
  // WEB_SCRIPT_ERROR_UNREPORTED): the error is still contained, but no report counted it.
  const SCRIPT_ERROR_UNREPORTED = "(not reported to Godot)";
  // Task 138 item 3: the object-query arm that answers whether a browser handle's object is gone.
  const KANAMA_WEB_OPCODE_FREED_CHECK = 1004;
  const FREED_INSTANCE_MESSAGE = (handle) =>
    `Invalid access to previously freed instance (Kanama Web handle=${handle}): the engine freed ` +
    "the object while a script still held it";
  // Task 138 item 32: how many engine-freed lent handles the bridge (and Kotlin) remember, so a later
  // use of one reads as a freed instance. Bounded: one int each.
  const FREED_HANDLES_REMEMBERED = 4096;
  // A RefCounted handle Kotlin holds lives while a Kotlin wrapper of it is reachable, or until
  // close() (desktop's rule). A lent one (a dispatch's argument) a handler never wrapped is held
  // for its dispatch plus these engine frames, the floor for an argument nobody materialized.
  const LENT_GRACE_FRAMES = 2;
  // At most this many lent RefCounted handles stay pinned past their grace: above it the drain
  // releases the oldest lends first. Kanama's own bound (C# has none): Firefox collects only under
  // allocation pressure, so a quiet scene with mouse motion would otherwise pin every event.
  const RC_LENT_CAP = 4096;
  // The bridge kinds of the handles that go with the script that made them (a tween runs its
  // callbacks through that script's proxy); every other non-RefCounted handle lives as long as its
  // object.
  const OWNER_BOUND_KINDS = new Set(["Tween", "PropertyTweener"]);
  // Task 134 D1: the proxy's object-query arm that runs one builtin (value-type) method.
  const KANAMA_WEB_OPCODE_BUILTIN_CALL = 1003;

  function commandWordCount(opcode) {
    if (
      opcode === 5 ||
      opcode === 15 ||
      opcode === 58 ||
      opcode === 59 ||
      opcode === 63 ||
      opcode === 64 ||
      // Task 64 DemoPage set: Control.release_focus, SceneTree.unload_current_scene (no payload).
      opcode === 308 ||
      opcode === 311 ||
      // Task 64 tps-demo parcel 8: Viewport.set_input_as_handled (no payload).
      opcode === 330 ||
      opcode === 147 ||
      opcode === 251 ||
      opcode === 283 ||
      opcode === 181
    ) return 2;
    if (
      opcode === 14 ||
      opcode === 16 ||
      opcode === 46 ||
      opcode === 47 ||
      opcode === 52 ||
      opcode === 57 ||
      opcode === 115 ||
      opcode === 128 ||
      opcode === 129 ||
      opcode === 130 ||
      opcode === 140 ||
      opcode === 150 ||
      opcode === 165 ||
      opcode === 170 ||
      opcode === 171 ||
      opcode === 185 ||
      opcode === 189 ||
      opcode === 202 ||
      opcode === 209 ||
      opcode === 247 ||
      opcode === 257 ||
      opcode === 258 ||
      opcode === 260 ||
      opcode === 267 ||
      opcode === 273 ||
      opcode === 274 ||
      opcode === 277 ||
      opcode === 280 ||
      opcode === 284 ||
      opcode === 285 ||
      opcode === 286 ||
      // Task 64 tier 3: InputEventKey.set_keycode / set_physical_keycode, Node.set_process_mode,
      // Window.set_mode -- one int32 argument each (opcode, handle, value).
      opcode === 295 ||
      opcode === 297 ||
      opcode === 301 ||
      opcode === 302 ||
      // Task 128 C: InputEventMouseButton.set_button_index (the portable mouse-button action) and
      // InputEvent.set_device (-1 = all devices, as project.godot spells input maps).
      opcode === 333 ||
      opcode === 334 ||
      opcode === 66
    ) return 3;
    if (
      opcode === 3 ||
      // Task 64 tps-demo parcel 8: Control.set_position / set_size (one Vector2 each).
      opcode === 331 ||
      opcode === 332 ||
      opcode === 30 ||
      opcode === 43 ||
      opcode === 48 ||
      opcode === 49 ||
      opcode === 50 ||
      opcode === 53 ||
      opcode === 54 ||
      // Task 64 DemoPage set: Environment.set_ssil_enabled / set_sdfgi_enabled (one bool word).
      opcode === 309 ||
      opcode === 310 ||
      // Task 64 tps-demo parcel 8: Environment.set_ssao_enabled / set_volumetric_fog_enabled.
      opcode === 322 ||
      opcode === 323 ||
      // Task 64 CameraMode family: Camera3D.set_current (one bool word), set_fov (one double).
      opcode === 315 ||
      opcode === 316 ||
      opcode === 55 ||
      opcode === 56 ||
      opcode === 60 ||
      opcode === 61 ||
      opcode === 62 ||
      opcode === 65 ||
      opcode === 67 ||
      opcode === 68 ||
      opcode === 80 ||
      opcode === 82 ||
      opcode === 93 ||
      opcode === 94 ||
      opcode === 95 ||
      opcode === 96 ||
      opcode === 97 ||
      opcode === 99 ||
      opcode === 105 ||
      opcode === 144 ||
      opcode === 146 ||
      opcode === 152 ||
      opcode === 153 ||
      opcode === 158 ||
      opcode === 161 ||
      opcode === 162 ||
      opcode === 168 ||
      opcode === 169 ||
      opcode === 180 ||
      opcode === 182 ||
      opcode === 183 ||
      opcode === 200 ||
      opcode === 230 ||
      opcode === 231 ||
      opcode === 236 ||
      opcode === 237 ||
      opcode === 241 ||
      opcode === 242 ||
      opcode === 254 ||
      opcode === 256 ||
      opcode === 261 ||
      opcode === 262 ||
      opcode === 275 ||
      opcode === 279 ||
      opcode === 281 ||
      opcode === 1000 ||
      // EXPERIMENTAL (task 76 spike): queued generic void call
      // (opcode, handle, method string id, packed-args string id).
      opcode === 1001
    ) return 4;
    if (
      opcode === 13 ||
      opcode === 74 ||
      opcode === 118 ||
      opcode === 76 ||
      opcode === 78 ||
      opcode === 84 ||
      opcode === 88 ||
      opcode === 101 ||
      opcode === 103 ||
      opcode === 132 ||
      opcode === 226 ||
      opcode === 228 ||
      opcode === 232 ||
      opcode === 238 ||
      opcode === 248 ||
      opcode === 151
    ) return 5;
    if (opcode === 32) return 6;
    if (opcode === 6 || opcode === 191) return 9;
    throw new Error(`Unknown Kanama Web command opcode=${opcode}`);
  }

  function percentile(values, fraction) {
    const sorted = [...values].sort((left, right) => left - right);
    if (sorted.length === 0) return 0;
    const nearestRank = Math.max(0, Math.ceil(sorted.length * fraction) - 1);
    return sorted[Math.min(sorted.length - 1, nearestRank)];
  }

  function summary(values) {
    return {
      count: values.length,
      p50Ms: percentile(values, 0.5),
      p95Ms: percentile(values, 0.95),
      p99Ms: percentile(values, 0.99),
    };
  }

  function updateStatus(message, kind) {
    const status = document.querySelector("#kanama-status");
    status.textContent = message;
    if (kind) status.dataset.kind = kind;
  }

  function requestedPreviewBunnies() {
    const requested = Number.parseInt(new URLSearchParams(location.search).get("bunnies") ?? "0", 10);
    return Number.isInteger(requested) ? Math.max(0, Math.min(requested, 5_000)) : 0;
  }

  // Task 81 (census-as-gate): the virtual dispatch categories the exercised-member census
  // records. These are exactly the names `invoke` receives for the virtuals the generated
  // proxies dispatch (WebScriptCodeEmitter.DISPATCHED_VIRTUALS), and they map 1:1 onto the
  // "virtuals" entries of KanamaWebProtocol.generated.json.
  const EXERCISED_VIRTUAL_DISPATCHES = new Set([
    "_ready",
    "_enter_tree",
    "_process",
    "_physics_process",
    "_draw",
    "_exit_tree",
    "_input",
    "_unhandled_input",
  ]);

  const bridge = {
    api: null,
    protocolVersion: KANAMA_WEB_PROTOCOL_VERSION,
    // Task 84: the fallback mode is a plain GAME, never the benchmark. Defaulting to "spike" put
    // the same trap one level up -- a page that forgot its `KanamaWebMode` line took the synthetic
    // transport AND the single-script benchmark lifecycle below, silently. Every page that wants
    // the benchmark now says so; every page that forgets gets a game (and its driver's
    // `mode === "<demo>"` check fails loudly instead of the run quietly measuring the wrong thing).
    mode: globalThis.KanamaWebMode ?? "game",
    bunnymarkVariant: globalThis.KanamaWebBunnymarkVariant ?? null,
    bunnymarkLanguage: globalThis.KanamaWebBunnymarkLanguage ?? "kanama",
    previewBunnies: requestedPreviewBunnies(),
    previewScheduled: false,
    applyCallbacks: new Map(),
    immediateCallbacks: new Map(),
    resourceCallbacks: new Map(),
    signalCallbacks: new Map(),
    resourceReleaseCallbacks: new Map(),
    constructCallbacks: new Map(),
    nodeLookupCallbacks: new Map(),
    packedSceneCallbacks: new Map(),
    noArgsObjectCallbacks: new Map(),
    inputCursorCallbacks: new Map(),
    connectCallbacks: new Map(),
    objectQueryCallbacks: new Map(),
    noArgsVector2Callbacks: new Map(),
    noArgsVector3Callbacks: new Map(),
    // Task 131 item 14: per script, the proxy function that pushes a script error to Godot.
    reportCallbacks: new Map(),
    signalVector2iCallbacks: new Map(),
    tweenCallbacks: new Map(),
    // handle -> the proxy whose callbacks run the handle's calls (its ROUTING owner). Not who keeps
    // it alive: a Node or plain Object handle lives as long as its object, a RefCounted one while
    // Kotlin wraps it; a handle whose routing owner goes is re-routed to another proxy.
    handleOwners: new Map(),
    tweenChildren: new Map(),
    sceneTreeHandlesByOwner: new Map(),
    viewportHandlesByOwner: new Map(),
    rootHandlesByOwner: new Map(),
    commandStringNamesByValue: new Map(),
    commandStringNamesById: new Map(),
    nextCommandStringNameId: 1,
    stagedGenericArgs: new Map(),
    nextStagedGenericArgsId: 1,
    activeCommandFlushFrame: null,
    activeOwnerHandle: 0,
    benchmarkCallback: null,
    firstHandle: 0,
    freedHandle: 0,
    readyCount: 0,
    enterTreeCalls: 0,
    immediateResult: null,
    immediateChildCountResult: null,
    immediateResourceHandleResult: null,
    immediateSignalResult: null,
    immediateResourceReleaseResult: null,
    immediateConstructHandleResult: null,
    immediateLongResult: null,
    immediateVector2Result: null,
    immediateVector3Result: null,
    browserHandleSlots: [{ generation: 0, kind: null, live: false }],
    freeBrowserHandleSlots: [],
    resourceLoads: 0,
    resourceReleases: 0,
    objectConstructions: 0,
    objectFrees: 0,
    liveBrowserHandleCount: 0,
    // Handles of the SceneTree and the root Window (markPageHandle), apart from the live count.
    pageHandles: 0,
    // The most page handles at once: never more than the two (the envelope's pageHandlesAtMostTwo).
    maxPageHandles: 0,
    maxLiveBrowserHandles: 0,
    // The handles minted for a dispatch's object arguments (a signal argument, `_input`'s event, a
    // method's object argument; one an earlier lookup or result made is handed over as it is):
    // minted, released (all), live now and at most; every Node or plain Object handle released
    // because the engine freed its object (swept, a lent one or not); and the GDScript->JS calls the
    // lending made (the dispatch-cost measure; the dispatch itself is not counted). Every driver's
    // performance section reports them.
    lentMints: 0,
    lentReleases: 0,
    lentSwept: 0,
    liveLent: 0,
    maxLiveLent: 0,
    lendBridgeCalls: 0,
    // RefCounted handles held by Kotlin wrappers (registerWrapper): live now and at most, released
    // because the browser collected their last wrapper, by close(), because a lent one nobody
    // wrapped passed its grace, or by the lent cap; wrapper registrations; and whether the
    // browser's FinalizationRegistry accepted a Kotlin/Wasm object (null until the first try).
    rcPinned: 0,
    maxRcPinned: 0,
    rcReleasedByGc: 0,
    rcReleasedByClose: 0,
    rcReleasedByGrace: 0,
    rcReleasedByCap: 0,
    wrapperRegistrations: 0,
    wrapperRegistryWorks: null,
    wrapperRegistry: null,
    // Handles whose last wrapper went (collected, or never made for a lent one): the drain at the
    // next engine frame releases them. Lent RefCounted still pinned, oldest lend first (the cap).
    pendingUnheld: new Set(),
    lentRefCountedByAge: new Set(),
    // Engine frames the drain has seen (the frame scheduler's pump; a lend's grace counts them).
    ownershipFrame: 0,
    lastDrainRafTick: -1,
    // The handles whose object the engine freed (the sweep released them), most recent last: a
    // later use of one is the freed-instance error, not "no proxy owns handle". A handle carries
    // its slot's generation, so a reused slot's new handle never matches. Capped (the oldest goes):
    // a use of a handle freed FREED_HANDLES_REMEMBERED sweeps ago reads as a stale handle.
    freedHandles: new Set(),
    lastConstructedObjectHandle: 0,
    lastFreedObjectHandle: 0,
    objectHandleGenerationAdvanced: false,
    signalEmits: 0,
    processCalls: 0,
    // Accumulated engine delta. Wall-clock time is not the game's time: Godot caps
    // how much simulation one frame may advance, so a slow host runs the SCENE in
    // slow motion while frames keep ticking. A gate that waits a fixed number of
    // wall seconds is really waiting for an unknown amount of gameplay, and this
    // is the number that says which.
    //
    // It is the GAME's time, once per engine frame, NOT once per scripted handle: `process` and
    // `physicsFrame` run for every scripted node, so adding `delta` in each of them made the sum scale
    // with the number of scripted nodes (dodge's mobs: 4-7x game time; task 138 item 33 review). One clock
    // is kept: the _process (render frame) clock when any script ran `process()`, else the fixed-step
    // _physics_process clock. They are never added together, which would count the same game time twice.
    // WebScriptCodeEmitter emits `_process` for EVERY proxy (whatever the script declares), and `frame()` hands
    // it to `process()`, so every demo that dispatches at all, the physics-driven controllers included, runs
    // the _process clock. The physics clock is only the fallback for a run where no `process()` happened:
    // the spike benchmark (`spikeBenchmarkFrame` bypasses `process()`) and a scene whose only proxies are
    // match3 board Tiles (their `frame()` returns before `process()`).
    simProcessSeconds: 0,
    simPhysicsSeconds: 0,
    simProcessRafTick: -1, // the engine frame whose _process delta is already in simProcessSeconds
    simPhysicsRafTick: -1, // the engine frame whose first physics step's handle is the physics clock's
    simPhysicsClockHandle: 0, // the handle whose physicsFrame calls advance the physics clock this frame
    get simSeconds() {
      return this.processCalls > 0 ? this.simProcessSeconds : this.simPhysicsSeconds;
    },
    noArgCalls: 0,
    // Task 80 slice 2: how often the two new registered-method crossings actually ran. A
    // driver can otherwise only see the SIDE EFFECT of a dispatched method, never the dispatch.
    doubleArgCalls: 0,
    packedReturnCalls: 0,
    builtinCalls: 0,
    releasedSignalCallbacks: 0,
    addBunnyCalls: 0,
    removeBunnyCalls: 0,
    finishCalls: 0,
    callbackErrors: 0,
    lastCallbackError: null,
    lastCallbackErrorFatal: false,
    // What a packed-return callback answers when its script threw (contained) or the page failed:
    // not a packed value, so the proxy returns the declared type's default instead of parsing it.
    scriptErrorResult: "\u001fkanama-script-error\u001f",
    // Commands skipped because their target was freed by the engine (flushCommands): the messages
    // not yet reported.
    freedCommandSkips: [],
    // Every boundary failure's message, oldest first, capped: lastCallbackError alone hides the
    // FIRST failure behind whatever it caused (an autoload that failed to construct surfaced only
    // as the later script that read it).
    callbackErrorLog: [],
    // Task 131 item 14: Kotlin script exceptions that were reported to Godot and contained (they
    // are also counted in callbackErrors, so a demo run that throws still fails its gate).
    scriptErrors: 0,
    scriptErrorLog: [],
    // The texts handed to Godot's error log, oldest first, capped.
    scriptErrorReports: [],
    drawCalls: 0,
    drawCommands: 0,
    drawBatches: 0,
    drawCrossings: 0,
    maxDrawCommands: 0,
    lastDrawCommands: 0,
    movingDrawSamples: 0,
    firstDrawPosition: null,
    lastDrawPosition: null,
    activeDraw: false,
    positionMutationCommands: 0,
    positionMutationBatches: 0,
    maxPositionMutationBatch: 0,
    lastPositionMutationBatch: 0,
    reloadRequested: false,
    reloadStarted: false,
    benchmarksStarted: false,
    appliedCommands: 0,
    lastAppliedValue: 0,
    kotlinToGodotCalls: 0,
    snapshotBatchLoads: 0,
    immediateCalls: 0,
    commandBufferGrowths: 0,
    latestSnapshotX: 0,
    latestSnapshotY: 0,
    match3Properties: new Map(),
    match3ReadyByClass: {},
    // Script class name -> how many instances are LIVE right now. match3ReadyByClass only ever
    // counts up (it is a _ready tally), so a driver cannot use it to see something die. This
    // decrements on the _exit_tree free, which is what lets the FPS driver assert that a shot
    // enemy actually queue_free()d instead of merely that enemies once existed.
    liveScriptsByClass: {},
    // Task 81 (census-as-gate): which registered script members actually DISPATCHED across the
    // JS<->Kotlin boundary, per script class: script class name -> { memberKey: dispatchCount }.
    // memberKey is the virtual's name for virtual dispatches, "method#<id>" for registered
    // registered-function dispatches (the engine drivers resolve the id to the manifest name from
    // the export's kanama-web/KanamaWebProtocol.generated.json), and "~kotlinSignalCallback" as
    // one aggregate for anonymous Kotlin-lambda signal subscriptions (they have no member name;
    // per-id keys would be runtime-assigned noise). Recorded at the `invoke` chokepoint, which
    // every one of those dispatch funnels already passes through, so a new funnel cannot arrive
    // unrecorded. This is what result_schema.py's per-demo required-member gate reads: a check
    // that only counts frames is no longer as green as one that proves an enemy died.
    exercisedMembers: {},
    // Dispatches that arrive before the script's recordReady names its handle (60i lesson: a
    // script instance exists before _ready, and _enter_tree/_process can dispatch first). Keyed
    // by owner handle and folded into exercisedMembers at recordReady.
    pendingExercisedByHandle: new Map(),
    match3DeferredReadyByClass: {},
    // handle -> script class name, recorded at ready. Purely diagnostic: a
    // boundary failure otherwise reports a bare handle number, which tells a CI
    // log reader nothing about which script actually threw.
    scriptNameByHandle: {},
    match3PackedSceneInstantiations: 0,
    match3AddChildCommands: 0,
    match3TextureAssignments: 0,
    match3PositionMutations: 0,
    match3PositionTweenTargets: 0,
    match3CursorSets: 0,
    match3Connections: 0,
    match3LambdaConnections: 0,
    match3LambdaCallbacks: 0,
    match3NoArgsSignalEmits: 0,
    match3InputEvents: 0,
    match3TileInputEvents: 0,
    match3TileScriptFrees: 0,
    match3Vector2iCalls: 0,
    match3Vector2iSignalEmits: 0,
    match3ScriptNodeLookups: 0,
    match3ReusedNodeLookups: 0,
    match3FirstTileHandle: 0,
    match3ScriptNamesByHandle: new Map(),
    match3TextureIndexByHandle: new Map(),
    match3TileSpriteByRoot: new Map(),
    match3TileRootBySprite: new Map(),
    match3TileTypeByHandle: new Map(),
    match3NodePositions: new Map(),
    match3MainHandle: 0,
    // Task 131 item 17: every demo keeps its smoke and probe hooks (probes, harness entry points,
    // teardown) in a script named `Smoke` (`SmokeQuit` in the older scene-child demos) that is no
    // gameplay class. The bridge captures its handle
    // by that name alone, in every mode; a driver resolves the method ids it calls from the export
    // manifest (resolveMethodId), never by position.
    smokeHandle: 0,
    dodgeMainHandle: 0,
    web3dMainHandle: 0,
    platformerMainHandle: 0,
    // Task 81: the platformer driver drives gameplay -- the Player handle carries the
    // action press/release (ops 86/87), stance pins (142), position reads (138) and the
    // SoundFootsteps is_playing query (287); the Hud handle carries the "Coins" Label
    // text read (288).
    platformerPlayerHandle: 0,
    platformerHudHandle: 0,
    squashMainHandle: 0,
    // Task 81: the squash driver drives gameplay -- the Player handle carries movement
    // injection and stance pins; the ScoreLabel handle carries the score text read (288);
    // the Mob handle is the LATEST ready mob, the driver's squash target. Mobs free
    // themselves (squash or screen-exit), so this goes stale -- the driver re-reads it
    // every poll and tolerates a stale-handle throw.
    squashPlayerHandle: 0,
    squashScoreLabelHandle: 0,
    squashMobHandle: 0,
    fpsPlayerHandle: 0,
    charPlayerHandle: 0,
    tpPlayerHandle: 0,
    tpDemoPageHandle: 0,
    tpsMainHandle: 0,
    tpsMenuHandle: 0,
    tpsLevelHandle: 0,
    tpsPlayerHandle: 0,
    racingVehicleHandle: 0,
    racingViewHandle: 0,
    cbBuilderHandle: 0,
    // _physics_process/_process ordering evidence (Task 60d): Godot's iteration runs all
    // physics ticks BEFORE the idle/_process pass inside one requestAnimationFrame callback,
    // so a physics dispatch AFTER a process dispatch within the same rAF tick would be an
    // ordering violation. The rAF wrapper below stamps engine ticks.
    rafTick: 0,
    lastRafTimestamp: -1,
    lastProcessRafTick: -1,
    physicsAfterProcessSameTick: 0,
    // Task 82: the coroutine frame scheduler's per-frame advance. These are demo-independent
    // on purpose -- a demo that never pumps is a demo whose `delaySeconds` never returns, so
    // every driver can assert on them and none has to be told which script is "Main".
    frameSchedulerPumps: 0,
    frameSchedulerContinuations: 0,
    lastPumpRafTick: -1,
    // Handles that have taken their _process dispatch since the last pump. Godot dispatches a
    // node's _process at most once per engine iteration, so a REPEAT proves a new iteration
    // began -- the fallback that keeps the pump alive if the rAF tick token ever stops moving.
    pumpDispatchedHandles: new Set(),
    match3ScaleMutations: 0,
    match3ModulateMutations: 0,
    match3TweensCreated: 0,
    match3TweenProperties: 0,
    match3TweensReleased: 0,
    match3ParticleInitialSnapshots: 0,
    match3ParticleInitialNonEmitting: 0,
    match3ParticleInitialLifetimeOne: 0,
    match3ParticleEmittingCommands: 0,
    match3ParticleFrees: 0,
    match3FirstParticleHandle: 0,
    particleSnapshots: new Map(),
    match3AudioHandle: 0,
    match3AudioProcessCalls: 0,
    match3AudioPlayersConstructed: 0,
    match3AudioPlayerAdds: 0,
    match3AudioConnections: 0,
    match3AudioStreamAssignments: 0,
    match3AudioBusCommands: 0,
    match3AudioVolumeCommands: 0,
    match3AudioPitchCommands: 0,
    match3AudioPlayCommands: 0,
    match3AudioCommandBatches: 0,
    match3AudioCommandCrossings: 0,
    match3AudioBatchHistory: [],
    match3AudioResourceLoads: 0,
    match3AudioResourceLoadFailures: 0,
    match3AudioResourceReleases: 0,
    match3AudioPlayerFrees: 0,
    match3FirstAudioPlayerHandle: 0,
    match3SceneTreeHandlesCreated: 0,
    match3SceneTreeHandleReuses: 0,
    match3SceneTreeQuitCommands: 0,
    audioPlayerStates: new Map(),
    resourcePathByHandle: new Map(),
    match3DeferredMethods: {},
    kotlinToGodotMs: [],
    bunnymarkProcessMs: [],
    gdscriptBaselineCallback: null,
    gdscriptBaselineReadyCount: 0,
    gdscriptBaselineAddCalls: 0,
    gdscriptBaselineFrameMs: [],
    emptyFrameMs: [],
    batchedFrameMs: [],
    frameIndex: 0,
    checksums: {},
    latestGdscriptBenchmark: {},
    results: {
      protocolVersion: 0,
      startup: {},
      lifecycle: {},
      benchmarks: {},
      environment: {
        userAgent: navigator.userAgent,
        hardwareConcurrency: navigator.hardwareConcurrency ?? null,
        deviceMemoryGiB: navigator.deviceMemory ?? null,
      },
    },

    /**
     * Counts one failed callback. `lastCallbackError` names the failure that ended the page, so a
     * contained script error (`fatal` false) never replaces an earlier fatal one; the log keeps
     * every message either way.
     */
    recordCallbackError(message, fatal) {
      this.callbackErrors += 1;
      if (fatal || !this.lastCallbackErrorFatal) this.lastCallbackError = message;
      if (fatal) this.lastCallbackErrorFatal = true;
      if (this.callbackErrorLog.length < 8) this.callbackErrorLog.push(message);
    },

    invoke(handle, callback, member, action, fallback) {
      const previousOwner = this.activeOwnerHandle;
      // A script with its own proxy owns its OWN lifetime, whatever the routing map
      // says. Those are two different questions and `handleOwners` answers both:
      // the PackedScene instantiate path deliberately re-points a spawned root at
      // the proxy that instantiated it, so calls route through the spawner. Reading
      // that as the LIFETIME owner meant everything a spawned script acquired was
      // billed to its spawner -- dodge's mobs charged their SpriteFrames and node
      // lookups to the scene root, which outlives them, so nothing was released
      // until full teardown (task 72: one handle leaked per mob, forever).
      if (handle > 0) {
        this.activeOwnerHandle = this.applyCallbacks.has(handle)
          ? handle
          : this.ownerForHandle(handle);
        this.recordExercisedDispatch(this.activeOwnerHandle, callback, member);
      }
      try {
        return action();
      } catch (error) {
        // Chrome's `stack` starts with the message; Firefox's does not. Taking
        // the stack alone therefore reports a Firefox-only boundary failure as
        // a wall of wasm frames with no reason attached -- which is exactly the
        // shape a CI-only failure arrives in.
        const message = error?.message ?? String(error);
        const stack = error?.stack ?? "";
        const detail = stack.startsWith(message) ? stack : `${message}\n${stack}`.trimEnd();
        const script = this.scriptNameByHandle[handle];
        const contextual = new Error(
          `Kanama Web boundary failure: handle=${handle}${script ? ` (${script})` : ""} ` +
            `callback=${callback} member=${member}\n${detail}`,
        );
        if (message.includes(SCRIPT_ERROR_MARKER)) {
          // A script exception the Kotlin side already reported to Godot's error log
          // (reportScriptError counted it) and contained (task 131 item 14): the callback returns
          // its default and the game runs on. Bridge or protocol faults still end the page below.
          if (this.scriptErrorLog.length < 8) this.scriptErrorLog.push(contextual.message);
          if (message.includes(SCRIPT_ERROR_UNREPORTED)) {
            // reportScriptError never ran, so nothing counted this error: count it here, or a
            // swallowed failure would leave the run green.
            this.recordCallbackError(contextual.message, false);
          }
          return fallback;
        }
        this.recordCallbackError(contextual.message, true);
        globalThis.failKanamaWeb(contextual);
        return fallback;
      } finally {
        this.activeOwnerHandle = previousOwner;
      }
    },

    /**
     * Task 81 (census-as-gate): record one boundary dispatch into the exercised-member census.
     *
     * Categories: registered-function dispatches (all nine call* funnels pass
     * callback === "registered_function" and member === "method#<id>"), the dispatched
     * virtuals (callback is the virtual's own name), and the Kotlin-lambda signal dispatch
     * path (callback === "_kanama_web_signal_dispatch0/1/_object"), which is recorded as ONE
     * aggregate key because an anonymous lambda has no stable member name to require.
     * Everything else through `invoke` (property pushes, create, the scheduler pump) is not a
     * script member and is deliberately not recorded.
     *
     * Attribution is the resolved OWNER: for registered methods and virtuals the proxies
     * always pass the script's own `_kanama_handle`, so owner === handle; for signal lambdas
     * the owner is the subscribing script. A dispatch that lands before recordReady names the
     * handle is buffered per handle and folded in at recordReady.
     */
    recordExercisedDispatch(owner, callback, member) {
      let memberKey;
      if (callback === "registered_function") memberKey = member;
      else if (EXERCISED_VIRTUAL_DISPATCHES.has(callback)) memberKey = callback;
      else if (callback.startsWith("_kanama_web_signal_dispatch")) memberKey = "~kotlinSignalCallback";
      else return;
      const scriptName = this.scriptNameByHandle[owner];
      let bucket;
      if (scriptName === undefined) {
        bucket = this.pendingExercisedByHandle.get(owner);
        if (!bucket) {
          bucket = {};
          this.pendingExercisedByHandle.set(owner, bucket);
        }
      } else {
        bucket = this.exercisedMembers[scriptName] ?? (this.exercisedMembers[scriptName] = {});
      }
      bucket[memberKey] = (bucket[memberKey] ?? 0) + 1;
    },

    unsupportedGameplayMethod(scriptId, methodId, methodName) {
      throw new Error(
        `Kanama Web gameplay method is not implemented: script=${scriptId} method=${methodId}/${methodName} (Task 57e backlog)`,
      );
    },
    unsupportedGameplayVirtual(scriptId, virtualName) {
      throw new Error(
        `Kanama Web gameplay virtual is not implemented: script=${scriptId} virtual=${virtualName} (Task 57e backlog)`,
      );
    },
    shouldDeferGameplayMethod(scriptName, methodName) {
      return false;
    },
    recordDeferredGameplayMethod(scriptName, methodName) {
      const key = `${scriptName}.${methodName}`;
      this.match3DeferredMethods[key] = (this.match3DeferredMethods[key] ?? 0) + 1;
    },

    /**
     * Script creation is two steps so a script's property initializers can call the engine, as
     * GDScript's `var x = ...` initializers can. `reserve` mints the handle; the proxy then installs
     * its callbacks under it (installProxyCallbacks); `construct` runs the Kotlin constructor with
     * that handle as the active owner, so an engine call from an initializer is applied through the
     * proxy. Returns 1, or 0 after a failed constructor (the callbacks it installed are cleared).
     */
    reserve(scriptId) {
      return this.invoke(0, "create", `script#${scriptId}`, () => this.api.kanamaWebReserve(scriptId), 0);
    },
    construct(handle) {
      // Checked before anything can be torn down: a handle that is already constructed is a live
      // script, and the failure teardown below must never run on one.
      if (this.api.kanamaWebIsConstructed(handle) === 1) {
        globalThis.failKanamaWeb(new Error(`Kanama Web handle=${handle} is already constructed`));
        return 1;
      }
      const constructed = this.invoke(handle, "create", "construct", () => this.api.kanamaWebConstruct(handle), 0);
      if (constructed !== handle) {
        this.teardownScriptHandles(handle, this.match3ScriptNamesByHandle.get(handle));
        this.clearProxyCallbacks(handle);
        return 0;
      }
      return 1;
    },
    ready(handle) {
      return this.invoke(handle, "_ready", "_ready", () => this.api.kanamaWebReady(handle), 0);
    },
    enterTree(handle) {
      // Dispatched by proxies whose script declares @OnEnterTree; fires on every tree
      // entry (before _ready on the first one), mirroring Godot's _enter_tree.
      this.enterTreeCalls += 1;
      return this.invoke(
        handle,
        "_enter_tree",
        "_enter_tree",
        () => this.api.kanamaWebEnterTree(handle),
        0,
      );
    },
    input(handle, eventHandle) {
      if (this.mode === "match3") this.match3InputEvents += 1;
      return this.invoke(
        handle,
        "_input",
        "_input",
        () => this.api.kanamaWebInput(handle, eventHandle),
        0,
      );
    },
    unhandledInput(handle, eventHandle) {
      return this.invoke(
        handle,
        "_unhandled_input",
        "_unhandled_input",
        () => this.api.kanamaWebUnhandledInput(handle, eventHandle),
        0,
      );
    },
    // Task 82: scope the bridge's active owner to a resumed coroutine's OWN script. Called from
    // Kotlin around each continuation the pump runs (see kanamaWebPumpFrameScheduler); mirrors
    // the ownership rule `invoke` applies for a real script callback.
    enterFrameSchedulerOwner(handle) {
      const previous = this.activeOwnerHandle;
      if (handle > 0) {
        this.activeOwnerHandle = this.applyCallbacks.has(handle)
          ? handle
          : this.ownerForHandle(handle);
      }
      return previous;
    },
    exitFrameSchedulerOwner(previous) {
      this.activeOwnerHandle = previous;
      return 1;
    },

    /**
     * Task 82: advance the shared coroutine frame scheduler EXACTLY once per engine frame.
     *
     * This is deliberately unconditional and mode-blind. It used to be a per-demo branch below
     * that named a "Main" handle, and the eight demos nobody remembered to name never resumed a
     * `delaySeconds` at all -- silently, because nothing failed; the queued work simply never
     * ran. The pump itself was always global (it selects by due frame/time, and each task carries
     * its own owner), so the gating was pure accident. Riding the `_process` dispatch that EVERY
     * generated proxy emits makes it impossible to bring up a demo without it.
     *
     * Once per frame, not once per script: the pump advances the scheduler's frame counter and
     * elapsed clock, so a second call in the same frame would run time at N x speed. Frame
     * identity is the rAF tick -- one engine iteration runs synchronously inside one animation
     * frame, so every dispatch of an iteration observes the same tick -- with a repeat-handle
     * fallback: Godot dispatches a node's `_process` at most once per iteration, so seeing the
     * same handle twice proves a new iteration even if the tick token ever stops moving.
     */
    pumpFrameScheduler(handle, delta) {
      const tick = this.rafTick;
      if (tick === this.lastPumpRafTick && !this.pumpDispatchedHandles.has(handle)) {
        this.pumpDispatchedHandles.add(handle);
        return 0;
      }
      this.lastPumpRafTick = tick;
      this.pumpDispatchedHandles.clear();
      this.pumpDispatchedHandles.add(handle);
      this.frameSchedulerPumps += 1;
      // Once per engine frame, inside a Godot callback (this runs from a proxy's `_process`): the
      // RefCounted handles whose last wrapper went are released here, as desktop's OwnedReleases
      // drains the GC's cleanups on the next frame.
      this.drainUnheldHandles();
      const executed = this.invoke(
        0,
        "frame_scheduler",
        "frame scheduler",
        () => this.api.kanamaWebPumpFrameScheduler(delta),
        0,
      );
      this.frameSchedulerContinuations += executed;
      return executed;
    },

    /**
     * One engine frame's dispatch into one script.
     *
     * Task 84: the REAL `_process` is the FALLTHROUGH and the synthetic transport benchmark is an
     * explicitly named branch. It used to be the other way round -- eight demos were listed here
     * and the three nobody remembered (charactercontroller, thirdperson, racing) silently ran the
     * benchmark instead of their game: ~150 dispatches dropped into `kanamaWebEmptyFrame`, then
     * `kanamaWebSpikeProcess` appending a synthetic scalar mutation on EVERY dispatch forever.
     * Nothing failed, so nothing noticed; the numbers simply stopped describing the game
     * (inflated appliedCommands, an extra crossing per script per frame, and `processTicks: 0`
     * for a demo that never reached `process()` at all).
     *
     * The default has to be what a GAME needs; benchmark scaffolding is what you opt INTO. This is
     * the same accident shape as task 82's mode-gated scheduler pump one layer down: there the
     * per-frame work was MISSING by default, here it was WRONG by default. A demo that adds no
     * branch here now gets correct behaviour by construction, so demo thirteen cannot repeat it.
     */
    frame(handle, delta) {
      this.lastProcessRafTick = this.rafTick;
      // Before any mode branch: the scheduler advance belongs to the engine frame, not to a demo.
      this.pumpFrameScheduler(handle, delta);
      // Opt-in only, named by the page: `stageWebSpikeGodotProject` writes
      // `globalThis.KanamaWebMode = "spike"` into the harness shell, the same way every demo's
      // staging task names its own mode. The benchmark is legitimate tooling -- it measures
      // crossing throughput with no gameplay in the way -- so it stays reachable via
      // `./gradlew :web-runtime:exportWebSpike`, but only when asked for.
      if (this.mode === "spike") return this.spikeBenchmarkFrame(handle, delta);
      if (this.mode === "match3") {
        if (handle === this.match3AudioHandle) {
          this.match3AudioProcessCalls += 1;
          return this.invoke(
            handle,
            "_process",
            "Audio._process",
            () => this.api.kanamaWebProcess(handle, delta),
            0,
          );
        }
        // match3's board Tiles deliberately take no per-frame dispatch (their animation is
        // Tween-driven); only Main runs its own _process. The scheduler advance above is
        // unaffected -- it already happened, whichever handle arrived first this frame.
        if (handle !== this.match3MainHandle) return 0;
      }
      return this.process(handle, delta);
    },

    /**
     * The synthetic transport benchmark (`KanamaWebMode === "spike"`, the webSpikeGodot harness).
     *
     * Samples empty crossings first, then batched frames whose `_process` appends one synthetic
     * scalar mutation (opcode 1000) per dispatch, so throughput can be measured without a game
     * emitting its own commands. That appended marker is why this must never be a fallthrough: a
     * real demo taking it reports work no game asked for.
     */
    spikeBenchmarkFrame(handle, delta) {
      const started = performance.now();
      let result;
      const emptyLimit = WARMUP_FRAMES + SAMPLE_FRAMES;
      const batchLimit = emptyLimit + WARMUP_FRAMES + SAMPLE_FRAMES;
      if (this.frameIndex < emptyLimit) {
        result = this.invoke(
          handle,
          "_process",
          "empty transport frame",
          () => this.api.kanamaWebEmptyFrame(handle, delta),
          0,
        );
        if (this.frameIndex >= WARMUP_FRAMES) {
          this.emptyFrameMs.push(performance.now() - started);
        }
      } else {
        result = this.invoke(
          handle,
          "_process",
          "synthetic transport frame",
          () => this.api.kanamaWebSpikeProcess(handle, delta),
          0,
        );
        if (this.frameIndex >= emptyLimit + WARMUP_FRAMES && this.frameIndex < batchLimit) {
          this.batchedFrameMs.push(performance.now() - started);
        }
      }
      this.frameIndex += 1;
      if (this.frameIndex >= batchLimit) this.maybeRunBenchmarks();
      return result;
    },
    process(handle, delta) {
      this.lastProcessRafTick = this.rafTick;
      this.processCalls += 1;
      if (this.simProcessRafTick !== this.rafTick) {
        // the first _process of this engine frame carries the frame's delta; the other scripted nodes' do not add
        this.simProcessRafTick = this.rafTick;
        this.simProcessSeconds += delta;
      }
      const started = performance.now();
      const result = this.invoke(
        handle,
        "_process",
        "_process",
        () => this.api.kanamaWebProcess(handle, delta),
        0,
      );
      this.bunnymarkProcessMs.push(performance.now() - started);
      return result;
    },
    physicsFrame(handle, delta) {
      if (this.rafTick === this.lastProcessRafTick) this.physicsAfterProcessSameTick += 1;
      // Godot's fixed physics tick: run the script's _physics_process (character controllers
      // set velocity + move_and_slide here). Applies to every mode with a physics-body script.
      this.physicsProcessCalls = (this.physicsProcessCalls ?? 0) + 1;
      // The physics clock is the fallback for a run with no `process()` calls (the spike benchmark, a
      // match3 scene of Tiles only): every other proxy also emits `_process` (WebScriptCodeEmitter), so the
      // physics-driven demos (character controllers, racing, third-person) are timed by the _process clock
      // and this one is not read for them. One step is one delta however many scripted
      // nodes run it: the first handle to run in an engine frame is the clock, and its later calls in
      // the same frame (a catch-up step) are the further steps.
      if (this.simPhysicsRafTick !== this.rafTick) {
        this.simPhysicsRafTick = this.rafTick;
        this.simPhysicsClockHandle = handle;
      }
      if (handle === this.simPhysicsClockHandle) this.simPhysicsSeconds += delta;
      return this.invoke(
        handle,
        "_physics_process",
        "_physics_process",
        () => this.api.kanamaWebPhysicsProcess(handle, delta),
        0,
      );
    },
    draw(handle) {
      const crossingsBefore = this.kotlinToGodotCalls;
      this.activeDraw = true;
      try {
        const applied = this.invoke(
          handle,
          "_draw",
          "_draw",
          () => this.api.kanamaWebDraw(handle),
          0,
        );
        this.drawCalls += 1;
        this.drawCommands += applied;
        this.drawCrossings += this.kotlinToGodotCalls - crossingsBefore;
        return applied;
      } finally {
        this.activeDraw = false;
      }
    },
    getStringProperty(handle, propertyId) {
      return this.invoke(
        handle,
        "property_get",
        `property#${propertyId}`,
        () => this.api.kanamaWebGetStringProperty(handle, propertyId),
        "",
      );
    },
    setStringProperty(handle, propertyId, value) {
      return this.invoke(
        handle,
        "property_set",
        `property#${propertyId}`,
        () => this.api.kanamaWebSetStringProperty(handle, propertyId, value),
        0,
      );
    },
    setDoubleProperty(handle, propertyId, value) {
      return this.invoke(
        handle,
        "property_set",
        `property#${propertyId}`,
        () => this.api.kanamaWebSetDoubleProperty(handle, propertyId, value),
        0,
      );
    },
    setVector2Property(handle, propertyId, x, y) {
      return this.invoke(
        handle,
        "property_set",
        `property#${propertyId}`,
        () => this.api.kanamaWebSetVector2Property(handle, propertyId, x, y),
        0,
      );
    },
    setVector3Property(handle, propertyId, x, y, z) {
      return this.invoke(
        handle,
        "property_set",
        `property#${propertyId}`,
        () => this.api.kanamaWebSetVector3Property(handle, propertyId, x, y, z),
        0,
      );
    },
    setPackedValueProperty(handle, propertyId, packed) {
      return this.invoke(
        handle,
        "property_set",
        `property#${propertyId}`,
        () => this.api.kanamaWebSetPackedValueProperty(handle, propertyId, packed),
        0,
      );
    },
    setColorProperty(handle, propertyId, r, g, b, a) {
      return this.invoke(
        handle,
        "property_set",
        `property#${propertyId}`,
        () => this.api.kanamaWebSetColorProperty(handle, propertyId, r, g, b, a),
        0,
      );
    },
    setVector2iProperty(handle, propertyId, x, y) {
      return this.invoke(
        handle,
        "property_set",
        `property#${propertyId}`,
        () => this.api.kanamaWebSetVector2iProperty(handle, propertyId, x, y),
        0,
      );
    },
    getPackedProperty(handle, propertyId) {
      return this.invoke(
        handle,
        "property_get",
        `property#${propertyId}`,
        () => this.api.kanamaWebGetPackedProperty(handle, propertyId),
        this.scriptErrorResult,
      );
    },
    setLongProperty(handle, propertyId, value) {
      this.recordMatch3Property(handle, propertyId, value);
      return this.invoke(
        handle,
        "property_set",
        `property#${propertyId}`,
        () => this.api.kanamaWebSetLongProperty(handle, propertyId, value),
        0,
      );
    },
    setObjectProperty(handle, propertyId, value) {
      this.recordMatch3Property(handle, propertyId, value);
      return this.invoke(
        handle,
        "property_set",
        `property#${propertyId}`,
        () => this.api.kanamaWebSetObjectProperty(handle, propertyId, value),
        0,
      );
    },
    setStringArrayProperty(handle, propertyId, values) {
      return this.invoke(
        handle,
        "property_set",
        `property#${propertyId}`,
        () => this.api.kanamaWebSetStringArrayProperty(handle, propertyId, String(values)),
        0,
      );
    },
    setObjectArrayProperty(handle, propertyId, values) {
      const parsedValues =
        values === "" ? [] : String(values).split(",").map((value) => Number.parseInt(value, 10));
      if (this.mode === "match3" && propertyId === 6) {
        this.match3TextureIndexByHandle.clear();
        parsedValues.forEach((value, index) => this.match3TextureIndexByHandle.set(value, index));
      }
      this.recordMatch3Property(handle, propertyId, parsedValues);
      return this.invoke(
        handle,
        "property_set",
        `property#${propertyId}`,
        () =>
          this.api.kanamaWebSetObjectArrayProperty(
            handle,
            propertyId,
            String(values),
          ),
        0,
      );
    },
    recordMatch3Property(handle, propertyId, value) {
      if (this.mode !== "match3") return;
      const properties = this.match3Properties.get(handle) ?? {};
      properties[propertyId] = value;
      this.match3Properties.set(handle, properties);
    },
    shouldDeferReady(scriptName) {
      return false;
    },
    // Task 88: the _ready echo probe is SPIKE SCAFFOLDING and must not ride the default
    // path. Every generated proxy used to call its first Int->Int registered function with
    // the literal 47 at scene entry -- harmless for WebSpikeScript.echo (whose round trip
    // `finish()` asserts below), but a user script declaring `fun addScore(points: Long):
    // Long` got a phantom +47 per instance, on Web only, silently. Same accident shape as
    // tasks 82 and 84: benchmark scaffolding reachable by default instead of opted into.
    shouldRunImmediateProbe() {
      return this.mode === "spike";
    },
    recordDeferredReady(scriptName) {
      this.match3DeferredReadyByClass[scriptName] =
        (this.match3DeferredReadyByClass[scriptName] ?? 0) + 1;
    },
    callString(handle, methodId, value) {
      return this.invoke(
        handle,
        "registered_function",
        `method#${methodId}`,
        () => this.api.kanamaWebCallString(handle, methodId, String(value)),
        0,
      );
    },
    callInt(handle, methodId, value) {
      return this.invoke(
        handle,
        "registered_function",
        `method#${methodId}`,
        () => this.api.kanamaWebCallInt(handle, methodId, value),
        0,
      );
    },
    callLongVoid(handle, methodId, value) {
      return this.invoke(
        handle,
        "registered_function",
        `method#${methodId}`,
        () => this.api.kanamaWebCallLongVoid(handle, methodId, value),
        0,
      );
    },
    callNoArgs(handle, methodId) {
      this.noArgCalls += 1;
      if (this.mode === "bunnymark") {
        if (methodId === this.bunnymarkMethodId("add")) this.addBunnyCalls += 1;
        if (methodId === this.bunnymarkMethodId("remove")) this.removeBunnyCalls += 1;
        if (methodId === this.bunnymarkMethodId("finish")) this.finishCalls += 1;
      }
      return this.invoke(
        handle,
        "registered_function",
        `method#${methodId}`,
        () => this.api.kanamaWebCallNoArgs(handle, methodId),
        0,
      );
    },
    callVector2i(handle, methodId, x, y) {
      if (this.mode === "match3") this.match3Vector2iCalls += 1;
      return this.invoke(
        handle,
        "registered_function",
        `method#${methodId}`,
        () => this.api.kanamaWebCallVector2i(handle, methodId, x, y),
        0,
      );
    },
    callObjectObjectLong(handle, methodId, firstHandle, secondHandle, value) {
      if (this.mode === "match3") this.match3TileInputEvents += 1;
      return this.invoke(
        handle,
        "registered_function",
        `method#${methodId}`,
        () =>
          this.api.kanamaWebCallObjectObjectLong(
            handle,
            methodId,
            firstHandle,
            secondHandle,
            value,
          ),
        0,
      );
    },
    callObject(handle, methodId, objectHandle) {
      return this.invoke(
        handle,
        "registered_function",
        `method#${methodId}`,
        () => this.api.kanamaWebCallObject(handle, methodId, objectHandle),
        0,
      );
    },
    // Task 80 slice 2: the one crossing every all-numeric registered-method shape rides. The
    // proxy flattens each declared argument into these six slots (six is one VECTOR3 pair, the
    // widest shape in the corpus) and pads the rest with 0.0; the generated Kotlin dispatcher
    // rebuilds the declared arguments from the same walk. This is what makes the FPS's
    // damage(amount: float) reach Kotlin instead of throwing (task 79).
    callDoubles(handle, methodId, a0, a1, a2, a3, a4, a5) {
      this.doubleArgCalls += 1;
      return this.invoke(
        handle,
        "registered_function",
        `method#${methodId}`,
        () => this.api.kanamaWebCallDoubles(handle, methodId, a0, a1, a2, a3, a4, a5),
        0,
      );
    },
    // Task 80 slice 2: a zero-argument value-returning registered method. The value crosses
    // packed into one string with the same encoding getPackedProperty uses, so the whole
    // value-returning category needs one entry point rather than one per return type.
    callPacked(handle, methodId) {
      this.packedReturnCalls += 1;
      return this.invoke(
        handle,
        "registered_function",
        `method#${methodId}`,
        () => this.api.kanamaWebCallPacked(handle, methodId),
        this.scriptErrorResult,
      );
    },
    // Task 134 D1: a registered method with arguments AND a return value: the packed argument
    // list in (as callString's packed form), the packed return out (as callPacked's).
    callPackedArgs(handle, methodId, value) {
      this.packedReturnCalls += 1;
      return this.invoke(
        handle,
        "registered_function",
        `method#${methodId}`,
        () => this.api.kanamaWebCallPackedArgs(handle, methodId, String(value)),
        this.scriptErrorResult,
      );
    },
    bunnymarkMethodId(method) {
      const spriteVariant = this.bunnymarkVariant === "BunnymarkV1Sprites";
      if (method === "add") return spriteVariant ? 1 : 2;
      if (method === "remove") return spriteVariant ? 2 : 3;
      if (method === "finish") return spriteVariant ? 3 : 4;
      throw new Error(`Unknown Bunnymark method=${method}`);
    },
    dodgeMethodId(method) {
      // Ordinals follow the registered-function order in dodge's Main.kt (mirrored by
      // the generated Main.gd proxy: game_over=1, new_game=2, timer callbacks 3-5).
      if (method === "game_over") return 1;
      if (method === "new_game") return 2;
      if (method === "_on_MobTimer_timeout") return 3;
      if (method === "_on_ScoreTimer_timeout") return 4;
      if (method === "_on_StartTimer_timeout") return 5;
      throw new Error(`Unknown dodge method=${method}`);
    },
    roundTrip(value) {
      return this.api.kanamaWebRoundTrip(value);
    },
    // Task 88 (finding 3): the script's @OnExitTree, dispatched on EVERY tree exit and
    // without destroying the instance -- a node that leaves the tree may come back.
    exitTree(handle) {
      return this.invoke(
        handle,
        "_exit_tree",
        "_exit_tree",
        () => this.api.kanamaWebExitTree(handle),
        0,
      );
    },
    /**
     * Everything the bridge holds on behalf of one script handle, released in the order a finished
     * script's `free` needs: the match3 and census bookkeeping, then the browser handles it routes
     * (releaseBrowserHandlesOwnedBy: an object-lifetime one is re-routed). A script whose
     * constructor failed runs the same teardown. The proxy clears its callbacks AFTER this (the
     * release path needs them).
     */
    teardownScriptHandles(handle, scriptName) {
      if (scriptName?.endsWith(".Tile")) {
        const spriteHandle = this.match3TileSpriteByRoot.get(handle);
        if (spriteHandle !== undefined) {
          this.match3TileRootBySprite.delete(spriteHandle);
        }
        this.match3TileSpriteByRoot.delete(handle);
        this.match3TileTypeByHandle.delete(handle);
        this.match3NodePositions.delete(handle);
        this.match3TileScriptFrees += 1;
      }
      this.match3ScriptNamesByHandle.delete(handle);
      const liveScriptName = this.scriptNameByHandle[handle];
      if (liveScriptName !== undefined) {
        const remaining = (this.liveScriptsByClass[liveScriptName] ?? 0) - 1;
        this.liveScriptsByClass[liveScriptName] = remaining > 0 ? remaining : 0;
      }
      this.releaseBrowserHandlesOwnedBy(handle);
    },
    free(handle) {
      const scriptName = this.match3ScriptNamesByHandle.get(handle);
      const result = this.invoke(
        handle,
        "_exit_tree",
        "_exit_tree",
        () => this.api.kanamaWebFree(handle),
        0,
      );
      if (result === 1 && handle === this.match3MainHandle) this.match3MainHandle = 0;
      if (result === 1 && handle === this.smokeHandle) this.smokeHandle = 0;
      if (result === 1 && handle === this.match3AudioHandle) this.match3AudioHandle = 0;
      if (result === 1) this.teardownScriptHandles(handle, scriptName);
      return result;
    },
    installProxyCallbacks(handle, apply, immediate, resource, signal, release, construct, nodeLookup, packedScene, noArgsObject, inputCursor, connect, objectQuery, noArgsVector2, signalVector2i, tween, noArgsVector3) {
      this.handleOwners.set(handle, handle);
      this.applyCallbacks.set(handle, apply);
      this.immediateCallbacks.set(handle, immediate);
      this.resourceCallbacks.set(handle, resource);
      this.signalCallbacks.set(handle, signal);
      this.resourceReleaseCallbacks.set(handle, release);
      this.constructCallbacks.set(handle, construct);
      this.nodeLookupCallbacks.set(handle, nodeLookup);
      this.packedSceneCallbacks.set(handle, packedScene);
      this.noArgsObjectCallbacks.set(handle, noArgsObject);
      this.inputCursorCallbacks.set(handle, inputCursor);
      this.connectCallbacks.set(handle, connect);
      this.objectQueryCallbacks.set(handle, objectQuery);
      this.noArgsVector2Callbacks.set(handle, noArgsVector2);
      this.signalVector2iCallbacks.set(handle, signalVector2i);
      this.tweenCallbacks.set(handle, tween);
      this.noArgsVector3Callbacks.set(handle, noArgsVector3);
    },
    installReportCallback(handle, report) {
      this.reportCallbacks.set(handle, report);
    },
    /**
     * Task 131 item 14: hands a Kotlin script error, rendered by the Kotlin side, to Godot's error
     * log through a proxy's `push_error` (the calling script's own when it has one). Without any
     * proxy (a constructor that failed before its callbacks were reachable) it is the browser
     * console, which is where Godot's own error output lands as well.
     */
    reportScriptError(text) {
      this.scriptErrors += 1;
      this.recordCallbackError(text, false);
      if (this.scriptErrorReports.length < 16) this.scriptErrorReports.push(text);
      const callback =
        this.reportCallbacks.get(this.activeOwnerHandle) ??
        this.reportCallbacks.values().next().value;
      if (callback) callback(text);
      else console.error(text);
    },
    clearProxyCallbacks(handle) {
      this.applyCallbacks.delete(handle);
      this.immediateCallbacks.delete(handle);
      this.resourceCallbacks.delete(handle);
      this.signalCallbacks.delete(handle);
      this.resourceReleaseCallbacks.delete(handle);
      this.constructCallbacks.delete(handle);
      this.nodeLookupCallbacks.delete(handle);
      this.packedSceneCallbacks.delete(handle);
      this.noArgsObjectCallbacks.delete(handle);
      this.inputCursorCallbacks.delete(handle);
      this.connectCallbacks.delete(handle);
      this.objectQueryCallbacks.delete(handle);
      this.noArgsVector2Callbacks.delete(handle);
      this.signalVector2iCallbacks.delete(handle);
      this.tweenCallbacks.delete(handle);
      this.noArgsVector3Callbacks.delete(handle);
      this.reportCallbacks.delete(handle);
      this.handleOwners.delete(handle);
      // A spawned SCRIPTED child is deliberately routed through its instantiator (the
      // immediatePackedSceneInstantiate re-point; task-60h/#125 separated routing from
      // lifetime) -- but that routing had no story for the instantiator dying FIRST.
      // MEASURED (task 81 fix #3, 2026-08-12, thirdperson): a BeeBot's death coroutine
      // spawns a SmokePuff and queue_frees the bee; the puff's own later self
      // queue_free (opcode 15, its animation-finished coroutine) then grouped under the
      // dead bee's owner entry and flushGroup threw "callback is not installed", a
      // fatal that stopped every later dispatch. When the dying proxy clears, re-point
      // every child that carries its OWN installed callbacks at itself -- such a child
      // is a live scripted proxy that can apply its own commands. Children WITHOUT
      // callbacks are plain acquired handles and keep the existing release-sweep path.
      for (const [child, owner] of this.handleOwners) {
        if (owner === handle && child !== handle && this.applyCallbacks.has(child)) {
          this.handleOwners.set(child, child);
        }
      }
    },
    ownerForHandle(handle) {
      const owner = this.handleOwners.get(handle);
      if (!owner && this.freedHandles.has(handle)) {
        // A lent handle the sweep released because the engine freed its object.
        throw new Error(FREED_INSTANCE_MESSAGE(handle));
      }
      if (!owner) {
        // handle=0 is "no script is running". A script's own constructor and property initializers
        // are NOT that case (they run with their handle active, see construct above). What remains
        // is an engine call with no script running at all; a companion-object or top-level
        // initializer is not it, since it runs on first access inside some script's callback (and
        // bills its engine objects to that script). Name the cause.
        const hint =
          handle === 0
            ? " (a Godot call ran with no script running -- on Web an engine call needs a running" +
              " script or constructor; do it from a script callback or a property initializer)"
            : "";
        throw new Error(`No Kanama Web proxy owns handle=${handle}${hint}`);
      }
      return owner;
    },
    internCommandStringName(value) {
      const normalized = String(value);
      const existing = this.commandStringNamesByValue.get(normalized);
      if (existing !== undefined) return existing;
      const id = this.nextCommandStringNameId;
      if (!Number.isSafeInteger(id) || id <= 0) {
        throw new Error("Kanama Web StringName command table exhausted");
      }
      this.nextCommandStringNameId += 1;
      this.commandStringNamesByValue.set(normalized, id);
      this.commandStringNamesById.set(id, normalized);
      return id;
    },
    resolveCommandStringName(id) {
      const value = this.commandStringNamesById.get(id);
      if (value === undefined) {
        throw new Error(`Unknown Kanama Web StringName command id=${id}`);
      }
      return value;
    },
    callbackFor(callbacks, handle, label) {
      const owner = this.ownerForHandle(handle);
      const callback = callbacks.get(owner);
      if (!callback) throw new Error(`${label} callback is not installed for owner=${owner}`);
      return callback;
    },
    allocateBrowserHandle(kind, owner = this.activeOwnerHandle) {
      if (!owner) throw new Error(`Cannot allocate ${kind} handle without an active proxy owner`);
      let slotIndex;
      if (this.freeBrowserHandleSlots.length === 0) {
        slotIndex = this.browserHandleSlots.length;
        if (slotIndex > BROWSER_HANDLE_SLOT_MASK) {
          throw new Error("Kanama Web browser handle registry exhausted");
        }
        this.browserHandleSlots.push({ generation: 0, kind: null, live: false });
      } else {
        slotIndex = this.freeBrowserHandleSlots.pop();
      }
      const slot = this.browserHandleSlots[slotIndex];
      slot.generation =
        slot.generation >= BROWSER_HANDLE_GENERATION_MASK ? 1 : slot.generation + 1;
      slot.kind = kind;
      slot.live = true;
      slot.lent = false;
      // Whether Kotlin knows it as a NODE (a lend refreshes its transform snapshot): asked once.
      slot.snapshots = null;
      slot.freeQueued = false;
      slot.refCounted = false;
      slot.wrappers = 0;
      slot.lendFrame = 0;
      slot.collected = false;
      slot.page = false;
      const handle = BROWSER_HANDLE_NAMESPACE | (slot.generation << 16) | slotIndex;
      this.freedHandles.delete(handle);
      this.handleOwners.set(handle, owner);
      this.liveBrowserHandleCount += 1;
      this.maxLiveBrowserHandles = Math.max(this.maxLiveBrowserHandles, this.liveBrowserHandleCount);
      return handle;
    },
    /**
     * Task 138 item 32: a dispatch hands Kotlin an object argument (`_kanama_lend` in the proxy).
     * One call per lend at most: `handle` 0 mints a slot (flags 1: a Node, adopted as NODE so its
     * snapshot and `queueFree` work, otherwise OBJECT; flags 16: a RefCounted, held by Kotlin
     * wrappers from now on and released LENT_GRACE_FRAMES after the lend if none was made); flags
     * 4 / 8 refresh a Node2D-or-Control / Node3D snapshot from the nine values, of the new handle
     * or of the one the object already has. Like every Node or plain Object handle, a lent one lives
     * as long as its object (releaseFreedHandles), never ending when the dispatch returns.
     */
    lendHandle(owner, handle, flags, a0, a1, a2, a3, a4, a5, a6, a7, a8) {
      this.lendBridgeCalls += 1;
      if (handle === 0) {
        const node = (flags & 1) !== 0;
        handle = this.allocateBrowserHandle(node ? "Node" : "Object", owner);
        if (node) this.api.kanamaWebAdoptNodeHandle(handle);
        else this.api.kanamaWebAdoptObjectHandle(handle);
        const slot = this.browserHandleSlot(handle);
        this.countLent(handle, slot);
        if ((flags & 16) !== 0) {
          // No wrapper holds it yet: unless the handler makes one, the drain releases it once its
          // grace is over. Oldest lends first, for the cap.
          this.markRefCounted(handle);
          slot.lendFrame = this.ownershipFrame;
          this.pendingUnheld.add(handle);
          this.lentRefCountedByAge.add(handle);
        }
        this.lentMints += 1;
      }
      // A snapshot needs a handle Kotlin knows as a NODE (whatever the bridge's own kind label: a
      // lookup's "Node", a constructed "Sprite2D" or "MeshInstance3D"); any other keeps none.
      if ((flags & 12) !== 0 && this.takesSnapshots(handle)) {
        if ((flags & 8) !== 0) this.refreshNode3DSnapshot(handle, a0, a1, a2, a3, a4, a5, a6, a7, a8);
        else this.refreshNode2DSnapshot(handle, a0, a1, a2, a3, a4, a5, a6, a7, a8);
      }
      return handle;
    },
    /** A slot now holds a lent handle: counted until it is retired, however it is retired. */
    countLent(handle, slot) {
      slot.lent = true;
      this.liveLent += 1;
      this.maxLiveLent = Math.max(this.maxLiveLent, this.liveLent);
    },
    uncountLent(handle, slot) {
      if (!slot.lent) return;
      slot.lent = false;
      this.lentReleases += 1;
      this.liveLent -= 1;
    },
    /** Whether Kotlin knows `handle` as a NODE, whose transform snapshot a dispatch refreshes. */
    takesSnapshots(handle) {
      const slot = this.browserHandleSlot(handle);
      if (!slot) return false;
      if (slot.snapshots === null) slot.snapshots = this.api.kanamaWebIsNodeHandle(handle) === 1;
      return slot.snapshots;
    },
    /**
     * A browser handle names a RefCounted (the proxy's `_kanama_track` says so, or a lend's flag
     * 16): from now on it lives while a Kotlin wrapper of it is reachable, or until that wrapper's
     * close(), not with the script that made it (desktop's rule). Kotlin keeps the same mark, so
     * the wrappers it builds for the handle register (registerWrapper).
     */
    markRefCounted(handle) {
      const slot = this.browserHandleSlot(handle);
      if (!slot || slot.refCounted) return 0;
      slot.refCounted = true;
      slot.wrappers = 0;
      this.rcPinned += 1;
      this.maxRcPinned = Math.max(this.maxRcPinned, this.rcPinned);
      this.api.kanamaWebMarkRefCountedHandle(handle);
      return 1;
    },
    /**
     * The one FinalizationRegistry every RefCounted wrapper registers with, or null when this
     * browser refuses a Kotlin/Wasm object as a target (then a RefCounted only Kotlin keeps is
     * released when its grace ends or its script goes, the protocol-36 limit, and one warning
     * says so).
     */
    wrapperRegistryOrNull() {
      if (this.wrapperRegistryWorks === false) return null;
      if (this.wrapperRegistry === null) {
        if (typeof FinalizationRegistry !== "function") {
          this.disableWrapperRegistry("FinalizationRegistry is missing");
          return null;
        }
        this.wrapperRegistry = new FinalizationRegistry((handle) => this.onWrapperFinalized(handle));
      }
      return this.wrapperRegistry;
    },
    disableWrapperRegistry(reason) {
      if (this.wrapperRegistryWorks === false) return;
      this.wrapperRegistryWorks = false;
      console.warn(
        `Kanama Web: this browser cannot watch Kotlin wrappers (${reason}); a RefCounted only ` +
          "Kotlin keeps is released when its script goes, a dispatch argument 2 frames after it",
      );
    },
    /**
     * Kotlin built a wrapper of `handle` (the `GodotObject` root's init): while `ref` is reachable
     * the handle stays. 1 when it now counts as a hold; 0 for a handle that is not a RefCounted
     * (nodes and plain objects live as long as their object) or without a working registry.
     */
    registerWrapper(ref, handle) {
      const slot = this.browserHandleSlot(handle);
      if (!slot?.refCounted) return 0;
      const registry = this.wrapperRegistryOrNull();
      if (!registry) return 0;
      try {
        registry.register(ref, handle, ref);
      } catch (error) {
        this.disableWrapperRegistry(String(error?.message ?? error));
        return 0;
      }
      this.wrapperRegistryWorks = true;
      slot.wrappers += 1;
      this.wrapperRegistrations += 1;
      // A lent one Kotlin wraps again is in use: the cap evicts the least recently wrapped first.
      if (slot.lent && this.lentRefCountedByAge.delete(handle)) this.lentRefCountedByAge.add(handle);
      return 1;
    },
    /**
     * The browser collected a wrapper of `handle` (a FinalizationRegistry callback: between tasks,
     * outside any Godot callback, so bookkeeping only). The handle's generation makes a slot reused
     * since a miss. The last one queues the handle for the next frame's drain.
     */
    onWrapperFinalized(handle) {
      const slot = this.browserHandleSlot(handle);
      if (!slot?.refCounted || slot.wrappers <= 0) return;
      slot.wrappers -= 1;
      if (slot.wrappers === 0) {
        slot.collected = true;
        this.pendingUnheld.add(handle);
      }
    },
    /**
     * Kotlin's `close()` on one wrapper: that wrapper's hold ends now; the last hold's end releases
     * the handle at once (deterministic, as desktop's close() unreferences). Another wrapper of the
     * same object keeps it. A handle that is not a RefCounted one (a tween, which lives until it
     * finishes) is left alone. 1 when the handle was live.
     */
    closeWrapper(ref, handle) {
      const slot = this.browserHandleSlot(handle);
      if (!slot) return 0;
      if (!slot.refCounted) return 1;
      if (this.wrapperRegistry?.unregister(ref) && slot.wrappers > 0) slot.wrappers -= 1;
      if (slot.wrappers > 0) return 1;
      this.rcReleasedByClose += 1;
      this.releaseHeldHandle(handle, slot);
      return 1;
    },
    /**
     * A temporary wrapper another one replaced (Kotlin's `webRetyped`: a load's `Resource` becoming
     * a `Texture2D`): its hold ends, never releasing at once; the last hold's end goes to the drain.
     * Without a working registry no wrapper is counted, so `wrappers` says nothing about the ones
     * Kotlin still uses: nothing to end (the handle goes with its script, the fallback's rule).
     */
    dropWrapper(ref, handle) {
      if (this.wrapperRegistryWorks === false) return 0;
      const slot = this.browserHandleSlot(handle);
      if (!slot?.refCounted) return 0;
      if (this.wrapperRegistry?.unregister(ref) && slot.wrappers > 0) {
        slot.wrappers -= 1;
        if (slot.wrappers === 0) {
          slot.collected = true;
          this.pendingUnheld.add(handle);
        }
      }
      return 1;
    },
    /**
     * A hold that is not a wrapper (`delta` +1 / -1): Kotlin's Sprite2D texture snapshot names the
     * texture's handle, so the handle stays while a snapshot names it. Not without a working
     * registry: the wrappers Kotlin still uses are not counted there, so the snapshot's -1 would
     * release a handle a wrapper still names (`sprite.texture = a; sprite.texture = b` would drop
     * `a`); the handle goes with its script instead (the fallback's rule).
     */
    holdRefCounted(handle, delta) {
      if (this.wrapperRegistryWorks === false) return 0;
      const slot = this.browserHandleSlot(handle);
      if (!slot?.refCounted) return 0;
      slot.wrappers = Math.max(0, slot.wrappers + delta);
      if (slot.wrappers === 0) {
        slot.collected = true;
        this.pendingUnheld.add(handle);
      }
      return 1;
    },
    /**
     * Once per engine frame (inside a Godot callback): release every
     * RefCounted handle whose last hold went (a lent one only once its grace is over), then, above
     * RC_LENT_CAP pinned lends, the oldest lends. A capped one Kotlin still holds is released as
     * freed: its later use is the freed-instance error.
     */
    drainUnheldHandles() {
      // Two callers, once per engine frame between them: the pump, and the SceneTree.process_frame
      // hook (KanamaWebHandles.drain_frame) that runs when no proxy processes (only scripted
      // resources left after a teardown).
      if (this.rafTick === this.lastDrainRafTick) return;
      this.lastDrainRafTick = this.rafTick;
      this.ownershipFrame += 1;
      if (this.pendingUnheld.size > 0) {
        for (const handle of [...this.pendingUnheld]) {
          const slot = this.browserHandleSlot(handle);
          if (!slot?.refCounted || slot.wrappers > 0) {
            this.pendingUnheld.delete(handle);
            continue;
          }
          if (slot.lent && this.ownershipFrame - slot.lendFrame <= LENT_GRACE_FRAMES) continue;
          this.pendingUnheld.delete(handle);
          if (slot.collected) this.rcReleasedByGc += 1;
          else this.rcReleasedByGrace += 1;
          this.releaseHeldHandle(handle, slot);
        }
      }
      if (this.lentRefCountedByAge.size > RC_LENT_CAP) {
        for (const handle of [...this.lentRefCountedByAge]) {
          if (this.lentRefCountedByAge.size <= RC_LENT_CAP) break;
          const slot = this.browserHandleSlot(handle);
          if (!slot) {
            this.lentRefCountedByAge.delete(handle);
            continue;
          }
          if (this.ownershipFrame - slot.lendFrame <= LENT_GRACE_FRAMES) break;
          this.rcReleasedByCap += 1;
          this.releaseHeldHandle(handle, slot, slot.wrappers > 0);
        }
      }
    },
    /**
     * Releases one handle nobody holds any more (the drain, close()): erase the proxies' dictionary
     * entry through any installed proxy, drop the per-kind state, tell Kotlin, retire the slot.
     * `freed`: Kotlin may still hold it (the lent cap), so its use reads as a freed instance.
     */
    releaseHeldHandle(handle, slot, freed = false) {
      const owner = this.handleOwners.get(handle);
      if (slot.kind === "Resource") {
        // A release by its holders (not a teardown sweep): counted as a resource release.
        this.resourceReleases += 1;
        if (this.resourcePathByHandle.get(handle)?.typeHint === "AudioStream") {
          this.match3AudioResourceReleases += 1;
        }
      }
      this.forgetHandleKindState(handle, slot, owner, slot.freeQueued === true);
      this.tweenChildren.delete(handle);
      const via = this.resourceReleaseCallbacks.has(owner) ? owner : this.lentRouteOtherThan(0);
      this.eraseObjectHandleEntry(handle, via);
      if (freed) {
        this.api.kanamaWebDiscardFreedHandle(handle);
        this.rememberFreed(handle);
      } else {
        this.api.kanamaWebDiscardBrowserHandle(handle);
      }
      this.retireBrowserSlot(handle, slot);
    },
    /**
     * The generic tier's minted results (`WebGenericCallResult.close`): a RefCounted's or a tween's
     * handle is released now. A Node's or plain Object's is the one handle of its object, which
     * other scripts may hold too: it lives as long as the object, so closing it does nothing. 1 when
     * it was live.
     */
    releaseHandleNow(handle) {
      const slot = this.browserHandleSlot(handle);
      if (!slot) return 0;
      if (!slot.refCounted && !OWNER_BOUND_KINDS.has(slot.kind)) return 1;
      if (slot.refCounted) this.rcReleasedByClose += 1;
      this.releaseHeldHandle(handle, slot);
      return 1;
    },
    rememberFreed(handle) {
      this.freedHandles.add(handle);
      if (this.freedHandles.size > FREED_HANDLES_REMEMBERED) {
        this.freedHandles.delete(this.freedHandles.values().next().value);
      }
    },
    /**
     * The proxy's sweep found these lent handles' objects freed by the engine (comma-separated; it
     * already erased their entries): release each slot, tell Kotlin the object was FREED (its later
     * use reads "previously freed instance", as desktop's does), and remember it here for the
     * calls that reach the bridge without Kotlin's check.
     */
    releaseFreedHandles(csv) {
      this.lendBridgeCalls += 1;
      for (const text of String(csv).split(",")) {
        const handle = Number(text);
        const slot = this.browserHandleSlot(handle);
        if (!slot) continue;
        // A node freed by a queued `queueFree()` had its per-kind state dropped when that applied.
        if (!slot.freeQueued) {
          if (slot.kind === "Sprite2D") this.objectFrees += 1;
          this.forgetHandleKindState(handle, slot, this.handleOwners.get(handle));
        }
        this.api.kanamaWebDiscardFreedHandle(handle);
        this.retireBrowserSlot(handle, slot);
        this.rememberFreed(handle);
        this.lentSwept += 1;
      }
    },
    /** The proxy (other than `leaving`) a lent handle's calls can route through instead. */
    lentRouteOtherThan(leaving) {
      for (const owner of this.applyCallbacks.keys()) {
        if (owner !== leaving) return owner;
      }
      return 0;
    },
    browserHandleSlot(handle) {
      if ((handle & BROWSER_HANDLE_NAMESPACE) === 0 || handle < 0) return null;
      const slotIndex = handle & BROWSER_HANDLE_SLOT_MASK;
      if (slotIndex === 0 || slotIndex >= this.browserHandleSlots.length) return null;
      const generation = (handle >>> 16) & BROWSER_HANDLE_GENERATION_MASK;
      const slot = this.browserHandleSlots[slotIndex];
      return slot.live && slot.generation === generation ? slot : null;
    },
    requireBrowserHandle(handle, kind) {
      const slot = this.browserHandleSlot(handle);
      if (!slot || slot.kind !== kind) {
        throw new Error(`Stale or wrong-kind Kanama Web browser handle=${handle} expected=${kind}`);
      }
      return slot;
    },
    releaseBrowserHandle(handle, kind) {
      const slot = this.requireBrowserHandle(handle, kind);
      const owner = this.handleOwners.get(handle);
      if (kind === "SceneTree" && this.sceneTreeHandlesByOwner.get(owner) === handle) {
        this.sceneTreeHandlesByOwner.delete(owner);
      }
      this.retireBrowserSlot(handle, slot);
    },
    /** Frees a live slot: the one place a browser handle stops being live. */
    retireBrowserSlot(handle, slot) {
      this.uncountLent(handle, slot);
      if (slot.refCounted) {
        slot.refCounted = false;
        slot.wrappers = 0;
        this.rcPinned -= 1;
        this.pendingUnheld.delete(handle);
        this.lentRefCountedByAge.delete(handle);
      }
      slot.collected = false;
      slot.live = false;
      slot.kind = null;
      this.handleOwners.delete(handle);
      if (slot.page) {
        slot.page = false;
        this.pageHandles -= 1;
      } else {
        this.liveBrowserHandleCount -= 1;
      }
      this.freeBrowserHandleSlots.push(handle & BROWSER_HANDLE_SLOT_MASK);
    },
    /**
     * The proxy tracked the SceneTree or the root Window, which live as long as the page: their one
     * handle each (the instance-id index answers it to every later ask) is a page handle, counted
     * in `pageHandles`, not among the live handles a scene's teardown returns to zero. Like every
     * Node or plain Object handle it stays while any script is left; the last proxy releases it.
     */
    markPageHandle(handle) {
      const slot = this.browserHandleSlot(handle);
      if (!slot || slot.page) return 0;
      slot.page = true;
      this.liveBrowserHandleCount -= 1;
      this.pageHandles += 1;
      this.maxPageHandles = Math.max(this.maxPageHandles, this.pageHandles);
      return 1;
    },
    /**
     * Whether a handle stays when the script routing it goes: every Node or plain Object handle
     * (it lives as long as its object; the sweep releases it once the engine freed the object), a
     * RefCounted one while a Kotlin wrapper holds it; never a tween or tweener, which goes with the
     * script that made it (its proxy runs the tween's callbacks).
     */
    outlivesItsRouter(slot) {
      if (slot.refCounted) return slot.wrappers > 0;
      return !OWNER_BOUND_KINDS.has(slot.kind);
    },
    eraseObjectHandleEntry(handle, viaOwner) {
      // The proxies share one handle -> object dictionary. Retiring a handle without erasing its
      // entry leaves a stale one: the next lookup of the same (live) object finds it and answers
      // with a handle the bridge no longer knows. Any installed proxy can erase it.
      const callback = this.resourceReleaseCallbacks.get(viaOwner);
      if (!callback) return;
      // The callback's result recorder counts a RESOURCE release: this erase is not one.
      const resourceReleases = this.resourceReleases;
      this.immediateResourceReleaseResult = null;
      callback(handle);
      this.resourceReleases = resourceReleases;
    },
    releaseBrowserHandlesOwnedBy(owner) {
      for (const tweenHandle of [...this.tweenChildren.keys()]) {
        if (this.handleOwners.get(tweenHandle) === owner) this.tweenChildren.delete(tweenHandle);
      }
      for (const [handle, handleOwner] of [...this.handleOwners]) {
        if (handleOwner !== owner || (handle & BROWSER_HANDLE_NAMESPACE) === 0) continue;
        const slot = this.browserHandleSlot(handle);
        if (!slot) continue;
        if (this.outlivesItsRouter(slot)) {
          // A Node or plain Object handle lives as long as its object, and a RefCounted one while
          // Kotlin wraps it, whichever script obtained it, so a copy another script stored (or a
          // companion holds) stays valid: its calls route through another proxy. The last proxy
          // releases it. A RefCounted nobody wraps, and a tween, go now.
          const route = this.lentRouteOtherThan(owner);
          if (route !== 0) {
            this.handleOwners.set(handle, route);
            continue;
          }
        }
        // A lent node whose queue_free applied was counted then (opcode 15): not again here.
        if (slot.kind === "Sprite2D" && !slot.freeQueued) this.objectFrees += 1;
        this.forgetHandleKindState(handle, slot, handleOwner, slot.freeQueued === true);
        this.eraseObjectHandleEntry(handle, owner);
        this.api.kanamaWebDiscardBrowserHandle(handle);
        this.retireBrowserSlot(handle, slot);
      }
      this.sceneTreeHandlesByOwner.delete(owner);
    },
    /**
     * The per-kind state a released browser handle leaves behind: the owner teardown's release and
     * the sweep's release of an engine-freed (possibly adopted lookup) handle both drop it.
     */
    forgetHandleKindState(handle, slot, owner, freeCounted = false) {
      if (slot.kind === "AudioStreamPlayer") {
        this.audioPlayerStates.delete(handle);
        if (!freeCounted) this.match3AudioPlayerFrees += 1;
      }
      if (slot.kind === "Resource") this.resourcePathByHandle.delete(handle);
      if (slot.kind === "SceneTree" && this.sceneTreeHandlesByOwner.get(owner) === handle) {
        this.sceneTreeHandlesByOwner.delete(owner);
      }
    },
    isBrowserHandleLive(handle) {
      return this.browserHandleSlot(handle) ? 1 : 0;
    },
    refreshPositionSnapshot(handle, x, y) {
      this.snapshotBatchLoads += 1;
      this.latestSnapshotX = x;
      this.latestSnapshotY = y;
      return this.api.kanamaWebLoadPositionSnapshot(handle, x, y);
    },
    refreshNode2DSnapshot(handle, positionX, positionY, scaleX, scaleY, r, g, b, a, rotation) {
      this.snapshotBatchLoads += 1;
      this.latestSnapshotX = positionX;
      this.latestSnapshotY = positionY;
      return this.api.kanamaWebLoadNode2DSnapshot(
        handle,
        positionX,
        positionY,
        scaleX,
        scaleY,
        r,
        g,
        b,
        a,
        rotation,
      );
    },
    refreshRayTargetSnapshot(handle, x, y, z) {
      return this.api.kanamaWebLoadRayTargetSnapshot(handle, x, y, z);
    },
    refreshNode3DSnapshot(handle, positionX, positionY, positionZ, rotationX, rotationY, rotationZ, scaleX, scaleY, scaleZ) {
      this.snapshotBatchLoads += 1;
      return this.api.kanamaWebLoadNode3DSnapshot(
        handle,
        positionX,
        positionY,
        positionZ,
        rotationX,
        rotationY,
        rotationZ,
        scaleX,
        scaleY,
        scaleZ,
      );
    },
    refreshRenderingMethodSnapshot(handle, method) {
      return this.api.kanamaWebLoadRenderingMethodSnapshot(handle, String(method));
    },
    refreshVelocitySnapshot(handle, x, y, z) {
      return this.api.kanamaWebLoadVelocitySnapshot(handle, x, y, z);
    },
    refreshViewportRectSnapshot(handle, x, y, width, height) {
      return this.api.kanamaWebLoadViewportRectSnapshot(handle, x, y, width, height);
    },
    loadAnimationNames(handle, joined) {
      return this.api.kanamaWebLoadAnimationNames(handle, String(joined));
    },
    refreshParticlesSnapshot(handle, emitting, lifetime) {
      if (!this.particleSnapshots.has(handle)) {
        this.match3ParticleInitialSnapshots += 1;
        if (emitting === false) this.match3ParticleInitialNonEmitting += 1;
        if (Math.abs(lifetime - 1.0) <= 0.000001) {
          this.match3ParticleInitialLifetimeOne += 1;
        }
        if (this.match3FirstParticleHandle === 0) this.match3FirstParticleHandle = handle;
      }
      this.particleSnapshots.set(handle, { emitting, lifetime });
      return this.api.kanamaWebLoadParticlesSnapshot(handle, emitting, lifetime);
    },
    immediateChildCount(handle, includeInternal) {
      const callback = this.callbackFor(this.immediateCallbacks, handle, "Godot immediate");
      this.immediateCalls += 1;
      this.immediateChildCountResult = null;
      callback(handle, includeInternal);
      if (!Number.isInteger(this.immediateChildCountResult)) {
        throw this.unpublishedResult(handle, "Godot immediate callback did not publish a child count");
      }
      return this.immediateChildCountResult;
    },
    immediateResourceLoad(path, typeHint, cacheMode) {
      const owner = this.activeOwnerHandle;
      const callback = this.callbackFor(this.resourceCallbacks, owner, "Godot resource");
      const resourceHandle = this.allocateBrowserHandle("Resource", owner);
      this.immediateResourceHandleResult = null;
      callback(resourceHandle, path, typeHint, cacheMode);
      if (
        this.immediateResourceHandleResult !== 0 &&
        this.immediateResourceHandleResult !== resourceHandle
      ) {
        // A Kanama-scripted resource resolves to its live script handle instead of the
        // proposed browser handle (node-lookup rule for loads).
        if (this.api.kanamaWebIsLive(this.immediateResourceHandleResult) !== 1) {
          throw new Error("Godot resource callback published an invalid handle");
        }
        this.releaseBrowserHandle(resourceHandle, "Resource");
        this.resourceLoads += 1;
        return this.immediateResourceHandleResult;
      }
      if (this.immediateResourceHandleResult === 0) {
        if (owner === this.match3AudioHandle) this.match3AudioResourceLoadFailures += 1;
        this.releaseBrowserHandle(resourceHandle, "Resource");
      } else {
        this.resourcePathByHandle.set(resourceHandle, {
          path: String(path),
          typeHint: String(typeHint),
          cacheMode,
        });
        if (owner === this.match3AudioHandle) this.match3AudioResourceLoads += 1;
      }
      this.resourceLoads += 1;
      return this.immediateResourceHandleResult;
    },
    instantiateScript(className) {
      // Runtime factory crossing (reserved opcode 0 on the object-query channel): the
      // active proxy instantiates the named Kanama scripted resource and returns the
      // hydrated script handle (0 on failure).
      const owner = this.activeOwnerHandle;
      const callback = this.callbackFor(this.objectQueryCallbacks, owner, "Godot script instantiate");
      this.immediateLongResult = null;
      callback(0, owner, className);
      const result = this.immediateLongResult;
      if (!Number.isInteger(result)) {
        throw new Error("Godot script instantiate callback did not publish a result");
      }
      if (result !== 0 && this.api.kanamaWebIsLive(result) !== 1) {
        throw new Error("Godot script instantiate did not return a live script handle");
      }
      return result;
    },
    releaseScriptResource(handle) {
      // Drop the proxies' dictionary reference to an owned scripted resource ("close what
      // you create"): the target is a script handle, so no browser slot is retired.
      if (this.api.kanamaWebIsLive(handle) !== 1) {
        throw new Error(`Cannot release non-live Kanama script resource handle=${handle}`);
      }
      const callback = this.callbackFor(
        this.resourceReleaseCallbacks,
        handle,
        "Godot script resource release",
      );
      this.immediateResourceReleaseResult = null;
      callback(handle);
      if (!Number.isInteger(this.immediateResourceReleaseResult)) {
        throw new Error("Godot script resource release callback did not publish a result");
      }
      return this.immediateResourceReleaseResult;
    },
    allocateFoundNodeHandle(ownerHandle) {
      // Node.find_children match without a script or existing handle: mint and adopt a
      // tracked browser Node handle for the applier to register.
      const handle = this.allocateBrowserHandle("Node", ownerHandle);
      this.api.kanamaWebAdoptNodeHandle(handle);
      return handle;
    },
    mintGenericHandle(ownerHandle, kindTag) {
      // Task 76: an immediate generic call returned an engine object that is neither
      // script-backed nor already tracked. Mint a tracked browser handle of the kind the
      // GDScript arm classified (node / resource / tween / object), routed through the calling
      // script: a RefCounted is released by `WebGenericCallResult.close` (releaseHandleNow) or its
      // Kotlin wrappers' end (the proxy marks it), a tween by its close or with the calling script,
      // and a Node or plain Object when the engine frees it (its close does nothing).
      if (kindTag === "node") {
        const handle = this.allocateBrowserHandle("Node", ownerHandle);
        this.api.kanamaWebAdoptNodeHandle(handle);
        return handle;
      }
      if (kindTag === "resource") {
        const handle = this.allocateBrowserHandle("Resource", ownerHandle);
        this.api.kanamaWebAdoptResourceHandle(handle);
        return handle;
      }
      // A tween goes with the calling script (OWNER_BOUND_KINDS); Kotlin knows it as an OBJECT.
      const handle = this.allocateBrowserHandle(kindTag === "tween" ? "Tween" : "Object", ownerHandle);
      this.api.kanamaWebAdoptObjectHandle(handle);
      return handle;
    },
    stageGenericArgs(value) {
      // Task 76: one-shot transport for a queued generic call's packed-args string. NOT
      // the interned StringName table (which never evicts, by design, and is bounded by
      // the set of distinct method/animation/bus names): packed argument strings are
      // unbounded in a hot loop, so each staged string is consumed exactly once by its
      // applier arm and deleted.
      const id = this.nextStagedGenericArgsId;
      this.nextStagedGenericArgsId += 1;
      this.stagedGenericArgs.set(id, String(value));
      // Task 88 (B2): every staged string is consumed by its applier arm in the same
      // batch, so this map should never hold more than one batch's worth. It has no
      // teardown drain and no leak gate can see it -- liveAfterTeardown counts browser
      // handles, and the soak gate trends handles/callbacks/jobs -- so an entry that is
      // never consumed (a stale target drops its command before takeStagedGenericArgs
      // runs) would accumulate silently and forever. A ceiling well above any legitimate
      // batch converts that invisible leak into a named failure.
      if (this.stagedGenericArgs.size > 65536) {
        throw new Error(
          `Kanama Web staged generic-args map has ${this.stagedGenericArgs.size} unconsumed ` +
            "entries: applier arms are not consuming what the encoder stages",
        );
      }
      return id;
    },
    takeStagedGenericArgs(id) {
      const value = this.stagedGenericArgs.get(id);
      if (value === undefined) {
        throw new Error(`Unknown or already consumed Kanama Web generic args id=${id}`);
      }
      this.stagedGenericArgs.delete(id);
      return value;
    },
    immediateConstructObject(className) {
      const owner = this.activeOwnerHandle;
      const callback = this.callbackFor(this.constructCallbacks, owner, "Godot construction");
      const objectHandle = this.allocateBrowserHandle(className, owner);
      this.immediateConstructHandleResult = null;
      callback(objectHandle, className);
      if (
        this.immediateConstructHandleResult !== 0 &&
        this.immediateConstructHandleResult !== objectHandle
      ) {
        throw new Error("Godot construction callback published an invalid handle");
      }
      if (this.immediateConstructHandleResult === 0) {
        this.releaseBrowserHandle(objectHandle, className);
      } else {
        this.objectConstructions += 1;
        if (className === "AudioStreamPlayer") {
          this.match3AudioPlayersConstructed += 1;
          if (this.match3FirstAudioPlayerHandle === 0) {
            this.match3FirstAudioPlayerHandle = objectHandle;
          }
          this.audioPlayerStates.set(objectHandle, {
            bus: null,
            volumeDb: null,
            pitchScale: null,
            streamPath: null,
            streamTypeHint: null,
            streamCacheMode: null,
            plays: [],
          });
        }
        if (
          this.lastFreedObjectHandle !== 0 &&
          (this.lastFreedObjectHandle & BROWSER_HANDLE_SLOT_MASK) ===
            (objectHandle & BROWSER_HANDLE_SLOT_MASK)
        ) {
          this.objectHandleGenerationAdvanced =
            objectHandle !== this.lastFreedObjectHandle &&
            this.browserHandleSlot(this.lastFreedObjectHandle) === null;
        }
        this.lastConstructedObjectHandle = objectHandle;
      }
      return this.immediateConstructHandleResult;
    },
    immediateEmitSignal(handle, name, value) {
      const callback = this.callbackFor(this.signalCallbacks, handle, "Godot signal");
      this.lastSignalName = name;
      this.lastSignalValue = value;
      this.immediateSignalResult = null;
      callback(handle, name, value);
      if (!Number.isInteger(this.immediateSignalResult)) {
        throw this.unpublishedResult(handle, "Godot signal callback did not publish a result");
      }
      return this.immediateSignalResult;
    },
    immediateEmitSignalNoArgs(handle, name) {
      const callback = this.callbackFor(this.signalCallbacks, handle, "Godot no-args signal");
      this.lastSignalName = name;
      this.lastSignalValue = null;
      this.immediateSignalResult = null;
      callback(handle, name);
      if (!Number.isInteger(this.immediateSignalResult)) {
        throw this.unpublishedResult(handle, "Godot no-args signal callback did not publish a result");
      }
      if (this.mode === "match3") this.match3NoArgsSignalEmits += 1;
      return this.immediateSignalResult;
    },
    immediateEmitSignalVector2i(handle, name, x, y) {
      const callback = this.callbackFor(
        this.signalVector2iCallbacks,
        handle,
        "Godot Vector2i signal",
      );
      this.lastSignalName = name;
      this.lastSignalValue = { x, y };
      this.immediateSignalResult = null;
      callback(handle, name, x, y);
      if (!Number.isInteger(this.immediateSignalResult)) {
        throw this.unpublishedResult(handle, "Godot Vector2i signal callback did not publish a result");
      }
      if (this.mode === "match3") this.match3Vector2iSignalEmits += 1;
      return this.immediateSignalResult;
    },
    immediatePropertyObjectQuery(opcode, handle, name) {
      // Object-valued property/animation read: the proxy resolves the named object,
      // registers the result under the proposed slot (or an existing/script handle), and
      // publishes the winning handle through the object-query integer channel.
      // The result belongs to the SCRIPT that asked (always inside an invoke boundary),
      // not to the receiver's owner: a scene-state property read off a cache-persistent
      // Structure resource must release with the Builder that read it.
      const owner = this.activeOwnerHandle || this.ownerForHandle(handle);
      const callback = this.callbackFor(this.objectQueryCallbacks, handle, "Godot object query");
      const resultHandle = this.allocateBrowserHandle("Object", owner);
      this.immediateLongResult = null;
      callback(opcode, handle, `${name}\u001f${resultHandle}`);
      const result = this.immediateLongResult;
      if (!Number.isInteger(result)) {
        throw this.unpublishedResult(handle, "Godot property object query callback did not publish a result");
      }
      if (result !== resultHandle) this.releaseBrowserHandle(resultHandle, "Object");
      return result;
    },
    immediateNodeLookup(handle, path) {
      const owner = this.ownerForHandle(handle);
      const callback = this.callbackFor(this.nodeLookupCallbacks, handle, "Godot node lookup");
      const resultHandle = this.allocateBrowserHandle("Node", owner);
      this.api.kanamaWebAdoptNodeHandle(resultHandle);
      this.immediateObjectHandleResult = null;
      callback(handle, resultHandle, path);
      const result = this.immediateObjectHandleResult;
      this.rejectUnpublishedObject(
        result,
        handle,
        () => {
          this.api.kanamaWebDiscardNodeHandle(resultHandle);
          this.releaseBrowserHandle(resultHandle, "Node");
        },
        "Godot node lookup callback did not publish a result",
      );
      if (result !== 0 && result !== resultHandle) {
        const scriptHandle = this.api.kanamaWebIsLive(result) === 1;
        if (!scriptHandle && this.isBrowserHandleLive(result) !== 1) {
          throw new Error("Godot node lookup callback returned neither its proposed nor a live object handle");
        }
        // The node is already tracked: its one handle answers (it lives as long as the node).
        this.api.kanamaWebDiscardNodeHandle(resultHandle);
        this.releaseBrowserHandle(resultHandle, "Node");
        if (this.mode === "match3") {
          if (scriptHandle) this.match3ScriptNodeLookups += 1;
          else this.match3ReusedNodeLookups += 1;
        }
      }
      if (result === 0) {
        this.api.kanamaWebDiscardNodeHandle(resultHandle);
        this.releaseBrowserHandle(resultHandle, "Node");
      }
      if (this.mode === "match3" && path === "Sprite2D" && result !== 0) {
        const previousSprite = this.match3TileSpriteByRoot.get(handle);
        if (previousSprite !== undefined && previousSprite !== result) {
          this.match3TileRootBySprite.delete(previousSprite);
        }
        this.match3TileSpriteByRoot.set(handle, result);
        this.match3TileRootBySprite.set(result, handle);
      }
      return result;
    },
    immediatePackedSceneInstantiate(resourceHandle, editState) {
      const owner = this.ownerForHandle(resourceHandle);
      const callback = this.callbackFor(
        this.packedSceneCallbacks,
        resourceHandle,
        "Godot PackedScene",
      );
      // Instantiated nodes belong to the SCRIPT that instantiated them (always inside an
      // invoke boundary), not to the packed-scene resource's owner — a weapon model
      // instantiated from a persistent Weapon resource must release with the Player.
      const instantiatingOwner = this.activeOwnerHandle || owner;
      const proposedHandle = this.allocateBrowserHandle("Node", instantiatingOwner);
      this.api.kanamaWebAdoptNodeHandle(proposedHandle);
      this.immediateObjectHandleResult = null;
      callback(resourceHandle, proposedHandle, editState);
      const result = this.immediateObjectHandleResult;
      this.rejectUnpublishedObject(
        result,
        resourceHandle,
        () => {
          this.api.kanamaWebDiscardNodeHandle(proposedHandle);
          this.releaseBrowserHandle(proposedHandle, "Node");
        },
        "Godot PackedScene callback did not publish a result",
      );
      if (result === 0) {
        this.api.kanamaWebDiscardNodeHandle(proposedHandle);
        this.releaseBrowserHandle(proposedHandle, "Node");
      } else if (result !== proposedHandle) {
        if (this.api.kanamaWebIsLive(result) !== 1) {
          throw new Error("PackedScene callback returned neither its proposed nor a live script handle");
        }
        this.api.kanamaWebDiscardNodeHandle(proposedHandle);
        this.releaseBrowserHandle(proposedHandle, "Node");
        // The proxy that instantiated the scene owns the concrete Godot object table entry. Route
        // later calls on the child through that proxy as well, even when the PackedScene root has
        // its own Kanama script handle and callback set.
        this.handleOwners.set(result, owner);
      }
      if (result !== 0) this.match3PackedSceneInstantiations += 1;
      return result;
    },
    immediateNoArgsObject(opcode, handle) {
      // Returned objects belong to the SCRIPT that asked (always inside an invoke
      // boundary), not to the receiver's owner — a SceneState/Mesh read off a
      // cache-persistent resource must release with the reading script (the
      // packed-scene-instantiate ownership rule).
      const owner = this.activeOwnerHandle || this.ownerForHandle(handle);
      const isSceneTree = opcode === 51;
      if (isSceneTree) {
        const existing = this.sceneTreeHandlesByOwner.get(owner);
        if (existing !== undefined && this.browserHandleSlot(existing)?.kind === "SceneTree") {
          this.match3SceneTreeHandleReuses += 1;
          return existing;
        }
        this.sceneTreeHandlesByOwner.delete(owner);
      }
      // Per-frame get_viewport (City-Builder's mouse-ray cursor) must not mint a handle
      // per call: reuse the owner's viewport handle like the SceneTree above.
      const isViewport = opcode === 19;
      if (isViewport) {
        const existing = this.viewportHandlesByOwner.get(owner);
        if (existing !== undefined && this.browserHandleSlot(existing)?.kind === "Node") {
          return existing;
        }
        this.viewportHandlesByOwner.delete(owner);
      }
      // Per-shot SceneTree.get_root (the robot laser parents its blast under the root)
      // follows the same per-owner reuse rule as get_viewport.
      const isRoot = opcode === 250;
      if (isRoot) {
        const existing = this.rootHandlesByOwner.get(owner);
        if (existing !== undefined && this.browserHandleSlot(existing)?.kind === "Node") {
          return existing;
        }
        this.rootHandlesByOwner.delete(owner);
      }
      const callback = this.callbackFor(
        this.noArgsObjectCallbacks,
        handle,
        "Godot no-args object",
      );
      const isTween = opcode === 36;
      // opcode 71 = AnimatedSprite2D.get_sprite_frames returns a Resource (SpriteFrames),
      // not a Node, so it must be adopted as an Object like Tween/SceneTree — otherwise the
      // handle is registered NODE and Kotlin's use as an OBJECT trips a handle-kind conflict.
      const isSpriteFrames = opcode === 71;
      // opcode 81 = WorldEnvironment.get_environment returns an Environment Resource, not a
      // Node — same handle-kind rule as SpriteFrames (adopt as Object, or Kotlin's OBJECT use
      // trips a NODE-vs-OBJECT conflict).
      const isEnvironment = opcode === 81;
      // A result that names a live object (a collider, the parent, the viewport, the camera, the
      // tree, the root) answers the handle it already has (a scripted Mob resolves to its script
      // handle; a looked-up Ground reuses its browser handle), like a node lookup, instead of a
      // second one: a Node or plain Object handle lives as long as its object, so one per call
      // would pile up. opcode 114 = Node.duplicate and opcode 36 = create_tween adopt a genuinely
      // new object through the proposed handle; the Resource results get one each (Kotlin's
      // wrappers hold them).
      const answersTracked =
        opcode === 112 ||
        opcode === 122 ||
        isViewport ||
        isSceneTree ||
        isRoot ||
        opcode === 73 ||
        opcode === 194;
      // opcode 212 = PackedScene.get_state returns a SceneState, opcode 225 =
      // MeshInstance3D.get_mesh returns a Mesh Resource — neither is a Node, so both
      // follow the SpriteFrames/Environment handle-kind rule.
      const isSceneState = opcode === 212;
      const isMesh = opcode === 225;
      // opcode 246 = Material.get_next_pass returns a Material Resource — same handle-kind
      // rule as SpriteFrames/Environment/Mesh (adopt as Object, never as a Node).
      const isMaterial = opcode === 246;
      const isObjectResult =
        isTween ||
        isSceneTree ||
        isSpriteFrames ||
        isEnvironment ||
        isSceneState ||
        isMesh ||
        isMaterial;
      const kind = isTween
        ? "Tween"
        : isSceneTree
          ? "SceneTree"
          : isSpriteFrames
            ? "SpriteFrames"
            : isEnvironment
              ? "Environment"
              : isSceneState
                ? "SceneState"
                : isMesh
                  ? "Mesh"
                  : isMaterial
                    ? "Material"
                    : "Node";
      const resultHandle = this.allocateBrowserHandle(kind, owner);
      if (isObjectResult) this.api.kanamaWebAdoptObjectHandle(resultHandle);
      else this.api.kanamaWebAdoptNodeHandle(resultHandle);
      this.immediateObjectHandleResult = null;
      callback(opcode, handle, resultHandle);
      const result = this.immediateObjectHandleResult;
      this.rejectUnpublishedObject(
        result,
        handle,
        () => {
          if (isObjectResult) this.api.kanamaWebDiscardBrowserHandle(resultHandle);
          else this.api.kanamaWebDiscardNodeHandle(resultHandle);
          this.releaseBrowserHandle(resultHandle, kind);
        },
        "Godot no-args object callback did not publish a result",
      );
      if (result !== 0 && result !== resultHandle) {
        const liveScript = this.api.kanamaWebIsLive(result) === 1;
        if (!answersTracked || (!liveScript && this.isBrowserHandleLive(result) !== 1)) {
          throw new Error("Godot no-args object callback published an invalid handle");
        }
        if (isObjectResult) this.api.kanamaWebDiscardBrowserHandle(resultHandle);
        else this.api.kanamaWebDiscardNodeHandle(resultHandle);
        this.releaseBrowserHandle(resultHandle, kind);
      }
      if (result === 0) {
        if (isObjectResult) this.api.kanamaWebDiscardBrowserHandle(resultHandle);
        else this.api.kanamaWebDiscardNodeHandle(resultHandle);
        this.releaseBrowserHandle(resultHandle, kind);
      } else if (isTween) {
        this.tweenChildren.set(resultHandle, new Set());
        if (this.mode === "match3") this.match3TweensCreated += 1;
      } else if (isSceneTree) {
        this.sceneTreeHandlesByOwner.set(owner, result);
        if (result === resultHandle) this.match3SceneTreeHandlesCreated += 1;
      } else if (isViewport) {
        this.viewportHandlesByOwner.set(owner, result);
      } else if (isRoot) {
        this.rootHandlesByOwner.set(owner, result);
      }
      return result;
    },
    immediateTweenNoArgs(opcode, handle) {
      const callback = this.callbackFor(this.tweenCallbacks, handle, "Godot Tween");
      this.immediateLongResult = null;
      callback(opcode, handle);
      if (!Number.isInteger(this.immediateLongResult)) {
        throw this.unpublishedResult(handle, "Godot Tween no-args callback did not publish a result");
      }
      return this.immediateLongResult;
    },
    immediateTweenBoolRetObject(opcode, handle, value) {
      const callback = this.callbackFor(this.tweenCallbacks, handle, "Godot Tween bool");
      this.immediateObjectHandleResult = null;
      callback(opcode, handle, value);
      this.rejectUnpublishedObject(this.immediateObjectHandleResult, handle, () => {}, "Godot Tween bool callback did not return its receiver");
      if (this.immediateObjectHandleResult !== handle) {
        throw new Error("Godot Tween bool callback did not return its receiver");
      }
      return handle;
    },
    immediateTweenLongRetObject(opcode, handle, value) {
      const callback = this.callbackFor(this.tweenCallbacks, handle, "Godot Tweener long");
      this.immediateObjectHandleResult = null;
      callback(opcode, handle, value);
      this.rejectUnpublishedObject(this.immediateObjectHandleResult, handle, () => {}, "Godot Tweener long callback did not return its receiver");
      if (this.immediateObjectHandleResult !== handle) {
        throw new Error("Godot Tweener long callback did not return its receiver");
      }
      return handle;
    },
    immediateMoveAndCollide(opcode, handle, packed) {
      // Mirrors immediateSlideCollision: the applier registers the KinematicCollision3D
      // under the proposed slot (0 when the sweep hit nothing).
      const owner = this.ownerForHandle(handle);
      const callback = this.callbackFor(this.tweenCallbacks, handle, "Godot move-and-collide");
      const resultHandle = this.allocateBrowserHandle("KinematicCollision3D", owner);
      this.api.kanamaWebAdoptObjectHandle(resultHandle);
      this.immediateObjectHandleResult = null;
      callback(opcode, handle, resultHandle, packed);
      const result = this.immediateObjectHandleResult;
      this.rejectUnpublishedObject(
        result,
        handle,
        () => {
          this.api.kanamaWebDiscardBrowserHandle(resultHandle);
          this.releaseBrowserHandle(resultHandle, "KinematicCollision3D");
        },
        "Godot move-and-collide callback did not publish a result",
      );
      if (result !== 0 && result !== resultHandle) {
        throw new Error("Godot move-and-collide callback published an invalid handle");
      }
      if (result === 0) {
        this.api.kanamaWebDiscardBrowserHandle(resultHandle);
        this.releaseBrowserHandle(resultHandle, "KinematicCollision3D");
      }
      return result;
    },
    /**
     * The error for a callback that did not publish its result. When the cause is an object the
     * engine freed while a script still held its handle (a child freed with its parent, a timer, a
     * body), say so, as desktop does: "did not publish a result" names a symptom, and Kotlin turns
     * this message into the freed-instance IllegalStateException (task 138 item 3).
     */
    unpublishedResult(handle, message) {
      return new Error(this.isHandleFreed(handle) ? FREED_INSTANCE_MESSAGE(handle) : message);
    },
    /**
     * Task 131 item 3 for the object-returning shapes. A callback on a receiver the engine freed
     * publishes nothing (a debug build aborts the typed read) or 0 (a null receiver); both are
     * "the object is gone", not a missing node or a bridge fault. `abandon` gives the proposed
     * result handle back first, so the throw leaks nothing. An unpublished result that is not a
     * freed receiver keeps its own message. A published 0 for a live receiver is a real null.
     */
    rejectUnpublishedObject(result, handle, abandon, message) {
      if (Number.isInteger(result) && result !== 0) return;
      const freed = this.isHandleFreed(handle);
      if (!freed && result === 0) return;
      abandon();
      throw new Error(freed ? FREED_INSTANCE_MESSAGE(handle) : message);
    },
    isHandleFreed(handle, ownerHandle = this.activeOwnerHandle) {
      const callback =
        this.objectQueryCallbacks.get(ownerHandle) ??
        this.objectQueryCallbacks.values().next().value;
      if (!callback || (handle & BROWSER_HANDLE_NAMESPACE) === 0) return false;
      this.immediateLongResult = null;
      callback(KANAMA_WEB_OPCODE_FREED_CHECK, handle, "");
      return this.immediateLongResult === 1;
    },
    immediateStringQuery(opcode, handle, value) {
      const callback = this.callbackFor(this.objectQueryCallbacks, handle, "Godot object query");
      this.immediateStringResult = null;
      callback(opcode, handle, value);
      if (typeof this.immediateStringResult !== "string") {
        throw this.unpublishedResult(handle, "Godot string query callback did not publish a string result");
      }
      return this.immediateStringResult;
    },
    // Task 134 D1: one value-type (builtin) method run by the engine. The call is stateless, so
    // any live proxy answers it: the running script's, else the active owner, else the first
    // installed one (a builtin call made before any script callback still works).
    immediateBuiltinCall(handle, packed) {
      // A stale or unowned handle (a script freed while a coroutine still runs) falls back like
      // the other calls do, instead of throwing in ownerForHandle (review N4).
      const handleOwner = handle ? this.handleOwners.get(handle) : undefined;
      let owner = handleOwner && this.objectQueryCallbacks.has(handleOwner) ? handle : 0;
      if (!owner && this.activeOwnerHandle && this.objectQueryCallbacks.has(this.activeOwnerHandle)) {
        owner = this.activeOwnerHandle;
      }
      if (!owner) owner = this.objectQueryCallbacks.keys().next().value ?? 0;
      // No proxy yet (a GD.print before any script is ready): Kotlin prints a text utility itself
      // and fails loud for anything else (review N9).
      if (!owner) return "E:no proxy is installed";
      this.builtinCalls += 1;
      return this.immediateStringQuery(KANAMA_WEB_OPCODE_BUILTIN_CALL, owner, String(packed));
    },
    recordImmediateStringResult(value) {
      this.immediateStringResult = String(value);
    },
    immediateVector2ArgVector3X(opcode, handle, x, y) {
      // Vector2-argument Vector3 query (camera ray projection): rides the no-args
      // Vector3 channel with the screen point in the extra argument slots.
      const callback = this.callbackFor(
        this.noArgsVector3Callbacks,
        handle,
        "Godot Vector2-arg Vector3",
      );
      this.immediateVector3Result = null;
      callback(opcode, handle, x, y);
      if (
        this.immediateVector3Result === null ||
        !Number.isFinite(this.immediateVector3Result.x)
      ) {
        throw this.unpublishedResult(handle, "Godot Vector2-arg Vector3 callback did not publish a finite result");
      }
      return this.immediateVector3Result.x;
    },
    immediateIndexedVector3X(opcode, handle, index) {
      const callback = this.callbackFor(
        this.noArgsVector3Callbacks,
        handle,
        "Godot indexed Vector3",
      );
      this.immediateVector3Result = null;
      callback(opcode, handle, index);
      if (
        this.immediateVector3Result === null ||
        !Number.isFinite(this.immediateVector3Result.x)
      ) {
        throw this.unpublishedResult(handle, "Godot indexed Vector3 callback did not publish a finite result");
      }
      return this.immediateVector3Result.x;
    },
    immediateDisconnectBound(handle, signal, targetHandle, method, boundValue) {
      const router = this.activeOwnerHandle || targetHandle;
      const callback = this.callbackFor(this.connectCallbacks, router, "Godot bound disconnect");
      this.immediateConnectResult = null;
      // flags slot carries -1 to select the disconnect arm in the shared connect callback.
      callback(handle, signal, targetHandle, method, -1, boundValue);
      if (!Number.isInteger(this.immediateConnectResult)) {
        throw this.unpublishedResult(handle, "Godot bound disconnect callback did not publish a result");
      }
      return this.immediateConnectResult;
    },
    immediateTweenMethod(opcode, tweenHandle, targetHandle, method, fromValue, toValue, duration) {
      return this.immediateTweenProperty(opcode, tweenHandle, targetHandle, method, [
        fromValue,
        toValue,
        duration,
      ]);
    },
    immediateIndexedObjectLookup(opcode, handle, index) {
      // Indexed hit lookup (shape-cast colliders): node-lookup validation — the applier may
      // return the proposed slot, an existing tracked handle, or a script handle.
      const owner = this.ownerForHandle(handle);
      const callback = this.callbackFor(this.tweenCallbacks, handle, "Godot indexed lookup");
      const resultHandle = this.allocateBrowserHandle("Node", owner);
      this.api.kanamaWebAdoptNodeHandle(resultHandle);
      this.immediateObjectHandleResult = null;
      callback(opcode, handle, resultHandle, index);
      const result = this.immediateObjectHandleResult;
      this.rejectUnpublishedObject(
        result,
        handle,
        () => {
          this.api.kanamaWebDiscardNodeHandle(resultHandle);
          this.releaseBrowserHandle(resultHandle, "Node");
        },
        "Godot indexed lookup callback did not publish a result",
      );
      if (result !== 0 && result !== resultHandle) {
        const scriptHandle = this.api.kanamaWebIsLive(result) === 1;
        if (!scriptHandle && this.isBrowserHandleLive(result) !== 1) {
          throw new Error("Godot indexed lookup returned neither its proposed nor a live handle");
        }
        this.api.kanamaWebDiscardNodeHandle(resultHandle);
        this.releaseBrowserHandle(resultHandle, "Node");
      }
      if (result === 0) {
        this.api.kanamaWebDiscardNodeHandle(resultHandle);
        this.releaseBrowserHandle(resultHandle, "Node");
      }
      return result;
    },
    immediateSlideCollision(handle, index) {
      const owner = this.ownerForHandle(handle);
      const callback = this.callbackFor(this.tweenCallbacks, handle, "Godot slide collision");
      const resultHandle = this.allocateBrowserHandle("KinematicCollision3D", owner);
      this.api.kanamaWebAdoptObjectHandle(resultHandle);
      this.immediateObjectHandleResult = null;
      callback(111, handle, resultHandle, index);
      const result = this.immediateObjectHandleResult;
      this.rejectUnpublishedObject(
        result,
        handle,
        () => {
          this.api.kanamaWebDiscardBrowserHandle(resultHandle);
          this.releaseBrowserHandle(resultHandle, "KinematicCollision3D");
        },
        "Godot slide-collision callback did not publish a result",
      );
      if (result !== 0 && result !== resultHandle) {
        throw new Error("Godot slide-collision callback published an invalid handle");
      }
      if (result === 0) {
        this.api.kanamaWebDiscardBrowserHandle(resultHandle);
        this.releaseBrowserHandle(resultHandle, "KinematicCollision3D");
      }
      return result;
    },
    immediateNodeChild(handle, index) {
      // Node.get_child through the tween-callback channel with a proposed handle; the
      // applier resolves an existing script/browser handle first (node-lookup rule).
      const owner = this.ownerForHandle(handle);
      const callback = this.callbackFor(this.tweenCallbacks, handle, "Godot child lookup");
      const resultHandle = this.allocateBrowserHandle("Node", owner);
      this.api.kanamaWebAdoptNodeHandle(resultHandle);
      this.immediateObjectHandleResult = null;
      callback(133, handle, resultHandle, index);
      const result = this.immediateObjectHandleResult;
      this.rejectUnpublishedObject(
        result,
        handle,
        () => {
          this.api.kanamaWebDiscardNodeHandle(resultHandle);
          this.releaseBrowserHandle(resultHandle, "Node");
        },
        "Godot child lookup callback did not publish a result",
      );
      if (result !== 0 && result !== resultHandle) {
        const liveScript = this.api.kanamaWebIsLive(result) === 1;
        if (!liveScript && this.isBrowserHandleLive(result) !== 1) {
          throw new Error("Godot child lookup returned neither its proposed nor a live handle");
        }
        this.api.kanamaWebDiscardNodeHandle(resultHandle);
        this.releaseBrowserHandle(resultHandle, "Node");
      }
      if (result === 0) {
        this.api.kanamaWebDiscardNodeHandle(resultHandle);
        this.releaseBrowserHandle(resultHandle, "Node");
      }
      return result;
    },
    immediateTweenObjectRetObject(opcode, handle, valueId) {
      const callback = this.callbackFor(this.tweenCallbacks, handle, "Godot Tween object");
      this.immediateObjectHandleResult = null;
      callback(opcode, handle, valueId);
      this.rejectUnpublishedObject(this.immediateObjectHandleResult, handle, () => {}, "Godot Tween object callback did not return its receiver");
      if (this.immediateObjectHandleResult !== handle) {
        throw new Error("Godot Tween object callback did not return its receiver");
      }
      return handle;
    },
    immediateTweenPropertyVector3(opcode, tweenHandle, targetHandle, property, x, y, z, duration) {
      return this.immediateTweenProperty(opcode, tweenHandle, targetHandle, property, [x, y, z, duration]);
    },
    immediateTweenCallback(opcode, tweenHandle, targetHandle, method) {
      // Rides the generic tween-property flow: the "property" slot carries the method name
      // and the returned CallbackTweener registers as a tween child for release.
      return this.immediateTweenProperty(opcode, tweenHandle, targetHandle, method, []);
    },
    immediateColorRetHandle(opcode, objectId, r, g, b, a) {
      // Task 64 tier 2: a fluent PropertyTweener call that returns the receiver, so it
      // reuses the tween-callback channel and reports the SAME handle back rather than
      // minting one -- the proxy sets result_handle = receiver_handle.
      const callback = this.callbackFor(this.tweenCallbacks, objectId, "Godot PropertyTweener color");
      this.immediateObjectHandleResult = null;
      callback(opcode, objectId, 0, 0, "", r, g, b, a);
      this.rejectUnpublishedObject(
        this.immediateObjectHandleResult,
        objectId,
        () => {},
        "Godot PropertyTweener color callback did not return its receiver",
      );
      return this.immediateObjectHandleResult === objectId ? objectId : 0;
    },
    immediateTweenPropertyDouble(opcode, tweenHandle, targetHandle, property, value, duration) {
      // Task 64: the SCALAR arm. A component path like "position:y" takes a number, and its
      // absence is why third-person's coin spill faulted the boundary -- every other tween
      // shape was here, so the hole never showed up in a shape audit.
      return this.immediateTweenProperty(opcode, tweenHandle, targetHandle, property, [value, duration]);
    },
    immediateTweenPropertyVector2(opcode, tweenHandle, targetHandle, property, x, y, duration) {
      if (this.mode === "match3" && property === "position") {
        this.match3NodePositions.set(targetHandle, { x, y });
        this.match3PositionTweenTargets += 1;
      }
      return this.immediateTweenProperty(
        opcode,
        tweenHandle,
        targetHandle,
        property,
        [x, y, duration],
      );
    },
    immediateTweenPropertyColor(opcode, tweenHandle, targetHandle, property, r, g, b, a, duration) {
      return this.immediateTweenProperty(
        opcode,
        tweenHandle,
        targetHandle,
        property,
        [r, g, b, a, duration],
      );
    },
    immediateTweenProperty(opcode, tweenHandle, targetHandle, property, values) {
      const owner = this.ownerForHandle(tweenHandle);
      const callback = this.callbackFor(this.tweenCallbacks, tweenHandle, "Godot Tween property");
      const resultHandle = this.allocateBrowserHandle("PropertyTweener", owner);
      this.api.kanamaWebAdoptObjectHandle(resultHandle);
      this.immediateObjectHandleResult = null;
      callback(opcode, tweenHandle, resultHandle, targetHandle, property, ...values);
      const result = this.immediateObjectHandleResult;
      this.rejectUnpublishedObject(
        result,
        tweenHandle,
        () => {
          this.api.kanamaWebDiscardBrowserHandle(resultHandle);
          this.releaseBrowserHandle(resultHandle, "PropertyTweener");
        },
        "Godot Tween property callback did not publish a result",
      );
      if (result !== 0 && result !== resultHandle) {
        throw new Error("Godot Tween property callback published an invalid handle");
      }
      if (result === 0) {
        this.api.kanamaWebDiscardBrowserHandle(resultHandle);
        this.releaseBrowserHandle(resultHandle, "PropertyTweener");
      } else {
        const children = this.tweenChildren.get(tweenHandle);
        if (!children) throw new Error(`Unknown Kanama Web Tween handle=${tweenHandle}`);
        children.add(resultHandle);
        if (this.mode === "match3") this.match3TweenProperties += 1;
      }
      return result;
    },
    releaseTweenGraph(tweenHandle) {
      const children = this.tweenChildren.get(tweenHandle);
      if (!children) return 0;
      this.tweenChildren.delete(tweenHandle);
      for (const handle of [...children, tweenHandle]) {
        const slot = this.browserHandleSlot(handle);
        if (!slot) continue;
        this.api.kanamaWebDiscardBrowserHandle(handle);
        this.releaseBrowserHandle(handle, slot.kind);
      }
      if (this.mode === "match3") this.match3TweensReleased += 1;
      return 1;
    },
    immediateSetCustomMouseCursor(owner, resourceHandle, shape, hotspotX, hotspotY) {
      const callback = this.callbackFor(this.inputCursorCallbacks, owner, "Godot Input cursor");
      callback(resourceHandle, shape, hotspotX, hotspotY);
      this.match3CursorSets += 1;
      return 1;
    },
    immediateConnect(handle, signal, targetHandle, method, flags) {
      // Route to the ACTIVE calling script's proxy: it minted both the source and target
      // handles (instantiate/node-lookup), so its dictionary can resolve them. Routing by
      // target broke cross-script connects whose source is foreign to the target's proxy
      // (squash: Main connects mob.squashed -> ScoreLabel).
      const router = this.activeOwnerHandle || targetHandle;
      const callback = this.callbackFor(this.connectCallbacks, router, "Godot connect");
      this.immediateConnectResult = null;
      callback(handle, signal, targetHandle, method, flags);
      if (!Number.isInteger(this.immediateConnectResult)) {
        throw this.unpublishedResult(this.isHandleFreed(handle) ? handle : targetHandle, "Godot connect callback did not publish a result");
      }
      if (this.immediateConnectResult === 0) {
        const sourceKind = this.browserHandleSlot(handle)?.kind;
        if (sourceKind === "AudioStreamPlayer") this.match3AudioConnections += 1;
        else this.match3Connections += 1;
      }
      return this.immediateConnectResult;
    },
    immediateConnectBound(handle, signal, targetHandle, method, boundValue, flags) {
      const router = this.activeOwnerHandle || targetHandle;
      const callback = this.callbackFor(this.connectCallbacks, router, "Godot bound connect");
      this.immediateConnectResult = null;
      callback(handle, signal, targetHandle, method, flags, boundValue);
      if (!Number.isInteger(this.immediateConnectResult)) {
        throw this.unpublishedResult(this.isHandleFreed(handle) ? handle : targetHandle, "Godot bound connect callback did not publish a result");
      }
      if (this.immediateConnectResult === 0) {
        const sourceKind = this.browserHandleSlot(handle)?.kind;
        if (sourceKind === "AudioStreamPlayer") this.match3AudioConnections += 1;
        else {
          this.match3Connections += 1;
          this.match3LambdaConnections += 1;
        }
      }
      return this.immediateConnectResult;
    },
    dispatchSignalObject(handle, callbackId, argHandle) {
      return this.invoke(
        handle,
        "_kanama_web_signal_dispatch_object",
        `callback#${callbackId}`,
        () => this.api.kanamaWebDispatchSignalObject(handle, callbackId, argHandle),
        0,
      );
    },
    // Task 134 D1: every emitted argument, packed by the proxy as `<Variant.Type>:<payload>`
    // parts -- the one delivery path of the typed signals, whatever their arity.
    dispatchSignalArgs(handle, callbackId, packed) {
      const result = this.invoke(
        handle,
        "_kanama_web_signal_dispatch_args",
        `callback#${callbackId}`,
        () => this.api.kanamaWebDispatchSignalArgs(handle, callbackId, String(packed)),
        0,
      );
      if (result === 1 && this.mode === "match3") this.match3LambdaCallbacks += 1;
      return result;
    },
    // Task 134 D1: an await watcher was freed with its emitter before the signal fired. Called
    // from the watcher's NOTIFICATION_PREDELETE, so it bypasses `invoke`: the owner may be
    // gone, which Kotlin treats as nothing left to cancel.
    releaseSignalCallback(handle, callbackId) {
      this.releasedSignalCallbacks += 1;
      return this.api.kanamaWebReleaseSignalCallback(handle, callbackId);
    },
    dispatchSignal0(handle, callbackId) {
      const result = this.invoke(
        handle,
        "_kanama_web_signal_dispatch0",
        `callback#${callbackId}`,
        () => this.api.kanamaWebDispatchSignal0(handle, callbackId),
        0,
      );
      if (result === 1 && this.mode === "match3") this.match3LambdaCallbacks += 1;
      return result;
    },
    // Task 80 slice 2: one emitted scalar, packed by the proxy the same way a packed property
    // is. A zero-argument Kotlin lambda still runs -- the registry ignores the payload for it --
    // so this replaces dispatchSignal0 on the one-argument helper without changing that path.
    dispatchSignal1(handle, callbackId, packed) {
      const result = this.invoke(
        handle,
        "_kanama_web_signal_dispatch1",
        `callback#${callbackId}`,
        () => this.api.kanamaWebDispatchSignal1(handle, callbackId, String(packed)),
        0,
      );
      if (result === 1 && this.mode === "match3") this.match3LambdaCallbacks += 1;
      return result;
    },
    immediateObjectQuery(opcode, handle, value) {
      const callback = this.callbackFor(this.objectQueryCallbacks, handle, "Godot object query");
      this.immediateLongResult = null;
      callback(opcode, handle, value);
      if (!Number.isInteger(this.immediateLongResult)) {
        throw this.unpublishedResult(handle, "Godot object query callback did not publish an integer result");
      }
      return this.immediateLongResult;
    },
    immediateSetProgressRatio(handle, ratio) {
      // Reuses the object-query callback: opcode 65 sets progress_ratio and re-pushes the
      // PathFollow2D Node2D snapshot so read-your-write position/rotation reads are fresh.
      const callback = this.callbackFor(this.objectQueryCallbacks, handle, "Godot progress ratio");
      this.immediateLongResult = null;
      callback(65, handle, ratio);
      if (this.immediateLongResult !== 1) {
        throw this.unpublishedResult(handle, "Godot set_progress_ratio callback did not confirm application");
      }
      return this.immediateLongResult;
    },
    immediateDoubleQuery(opcode, handle, value) {
      // Object-query channel carrying one double (PathFollow3D.set_progress_ratio,
      // Node3D.rotate_y): the applier confirms application and re-pushes the transform
      // snapshot so read-your-write reads reflect the move.
      const callback = this.callbackFor(this.objectQueryCallbacks, handle, "Godot double query");
      this.immediateLongResult = null;
      callback(opcode, handle, value);
      if (this.immediateLongResult !== 1) {
        throw this.unpublishedResult(handle, "Godot double-query callback did not confirm application");
      }
      return this.immediateLongResult;
    },
    immediateDoubleRetDouble(opcode, handle, value) {
      // Object-query channel carrying one double and RETURNING one (Noise.get_noise_1d,
      // Curve.sample): the applier publishes the sample scaled x1000 as the integer result, so
      // any published integer -- 0 included, a curve really can sample to 0 -- is the answer.
      // Only a callback that never published is a failure; `immediateDoubleQuery` above is the
      // confirm-only shape (result must be 1) and must not be reused for value returns.
      const callback = this.callbackFor(this.objectQueryCallbacks, handle, "Godot double query");
      this.immediateLongResult = null;
      callback(opcode, handle, value);
      if (!Number.isInteger(this.immediateLongResult)) {
        throw this.unpublishedResult(handle, "Godot double-returning query callback did not publish a result");
      }
      return this.immediateLongResult;
    },
    immediateNoArgsVector2X(opcode, handle) {
      const callback = this.callbackFor(
        this.noArgsVector2Callbacks,
        handle,
        "Godot no-args Vector2",
      );
      this.immediateVector2Result = null;
      callback(opcode, handle);
      if (
        this.immediateVector2Result === null ||
        !Number.isFinite(this.immediateVector2Result.x) ||
        !Number.isFinite(this.immediateVector2Result.y)
      ) {
        throw this.unpublishedResult(handle, "Godot Vector2 callback did not publish a finite result");
      }
      return this.immediateVector2Result.x;
    },
    immediateNoArgsVector2Y() {
      if (this.immediateVector2Result === null) {
        throw new Error("Godot Vector2 y requested before the corresponding x query");
      }
      return this.immediateVector2Result.y;
    },
    recordImmediateObjectHandle(value) {
      this.immediateObjectHandleResult = value;
    },
    recordImmediateConnectResult(value) {
      this.immediateConnectResult = value;
    },
    recordImmediateLongResult(value) {
      this.immediateLongResult = value;
    },
    recordImmediateVector2(x, y) {
      this.immediateVector2Result = { x, y };
    },
    immediateNoArgsVector3X(opcode, handle) {
      const callback = this.callbackFor(
        this.noArgsVector3Callbacks,
        handle,
        "Godot no-args Vector3",
      );
      this.immediateVector3Result = null;
      callback(opcode, handle);
      if (
        this.immediateVector3Result === null ||
        !Number.isFinite(this.immediateVector3Result.x) ||
        !Number.isFinite(this.immediateVector3Result.y) ||
        !Number.isFinite(this.immediateVector3Result.z)
      ) {
        throw this.unpublishedResult(handle, "Godot Vector3 callback did not publish a finite result");
      }
      return this.immediateVector3Result.x;
    },
    immediateNoArgsVector3Y() {
      if (this.immediateVector3Result === null) {
        throw new Error("Godot Vector3 Y read without a preceding X read");
      }
      return this.immediateVector3Result.y;
    },
    immediateNoArgsVector3Z() {
      if (this.immediateVector3Result === null) {
        throw new Error("Godot Vector3 Z read without a preceding X read");
      }
      return this.immediateVector3Result.z;
    },
    recordImmediateVector3(x, y, z) {
      this.immediateVector3Result = { x, y, z };
    },
    installBenchmarkCallback(callback) {
      this.benchmarkCallback = callback;
      this.maybeRunBenchmarks();
    },
    clearBenchmarkCallback() {
      this.benchmarkCallback = null;
    },
    installGdscriptBaselineCallback(callback) {
      this.gdscriptBaselineCallback = callback;
    },
    clearGdscriptBaselineCallback() {
      this.gdscriptBaselineCallback = null;
    },
    recordGdscriptBaselineReady() {
      this.gdscriptBaselineReadyCount += 1;
    },
    recordGdscriptBaselineFrame(elapsedMs) {
      this.gdscriptBaselineFrameMs.push(elapsedMs);
    },
    callGdscriptBaseline(method) {
      if (!this.gdscriptBaselineCallback) {
        throw new Error("GDScript baseline callback is not installed");
      }
      if (method === "add") this.gdscriptBaselineAddCalls += 1;
      this.gdscriptBaselineCallback(method);
    },
    resetGdscriptBaselineTimings() {
      this.gdscriptBaselineFrameMs.length = 0;
    },
    gdscriptBaselineSnapshot() {
      return {
        bunnymarkVariant: this.bunnymarkVariant,
        bunnymarkLanguage: this.bunnymarkLanguage,
        readyCount: this.gdscriptBaselineReadyCount,
        addCalls: this.gdscriptBaselineAddCalls,
        frameTiming: summary(this.gdscriptBaselineFrameMs),
      };
    },
    flushCommands(words, wordCount, commandCount) {
      // Snapshot the batch: `words` is a live view into the Kotlin command buffer, which
      // is cleared and refilled by any nested flush. Applying a command can synchronously
      // re-enter (e.g. add_child fires a spawned node's _ready, which flushes its own
      // commands into the same buffer), so without this copy the post-dispatch telemetry
      // pass — and any later command group — would read commands from the nested batch and
      // misparse (a handle read as an opcode). Only surfaces for demos that mutate during a
      // flush, i.e. dodge's mob spawning; match3/bunnymark never re-enter mid-batch.
      words = words.slice(0, wordCount);
      // A top-level flush starts clean: messages a throw left unreported must not surface against
      // another script. A nested flush (a callback flushing mid-batch) keeps the outer's.
      if (this.activeCommandFlushFrame === null) this.freedCommandSkips = [];
      if (this.activeDraw) {
        this.drawBatches += 1;
        this.maxDrawCommands = Math.max(this.maxDrawCommands, commandCount);
        this.lastDrawCommands = commandCount;
        if (commandCount > 0 && wordCount >= 9) {
          const data = new DataView(words.buffer, words.byteOffset, wordCount * 4);
          const position = {
            x: data.getFloat32(12, true),
            y: data.getFloat32(16, true),
          };
          if (this.firstDrawPosition === null) this.firstDrawPosition = position;
          if (
            this.lastDrawPosition !== null &&
            (position.x !== this.lastDrawPosition.x || position.y !== this.lastDrawPosition.y)
          ) {
            this.movingDrawSamples += 1;
          }
          this.lastDrawPosition = position;
        }
      }
      const started = performance.now();
      let groupStart = 0;
      let groupWords = 0;
      let groupCommands = 0;
      let groupOwner = 0;
      let groupCrossings = 0;
      let scanOffset = 0;
      let applied = 0;
      let skippedFreed = 0;
      // Batch indices of the commands skipped because their target was freed: they were consumed
      // but NOT applied, so the bookkeeping below must not treat them as applied (the applied ones
      // are no longer a prefix).
      const skippedIndices = new Set();
      let groupFirstIndex = 0;
      const flushGroup = () => {
        if (groupCommands === 0) return;
        const callback = this.applyCallbacks.get(groupOwner);
        if (!callback && this.isFreedScriptHandle(groupOwner)) {
          // Task 138 item 22: an earlier group of this batch freed the owning script, so no proxy
          // can apply its commands: each is skipped and reported, as skipFreedCommand does for an
          // engine-freed browser handle. Only a command on the script's own node targets a freed
          // instance; one on another (live) object is reported as dropped with its script.
          let at = groupStart;
          for (let index = 0; index < groupCommands; index += 1) {
            const message =
              words[at + 1] === groupOwner
                ? FREED_INSTANCE_MESSAGE(words[at + 1])
                : `Kanama Web command on handle=${words[at + 1]} skipped: the script that queued it ` +
                  `(handle=${groupOwner}) was freed earlier in the same batch`;
            this.recordFreedSkip(words[at], words[at + 1], words[at + 3], message);
            skippedFreed += 1;
            skippedIndices.add(groupFirstIndex + index);
            at += commandWordCount(words[at]);
          }
          return;
        }
        if (!callback) {
          throw new Error(`Godot command callback is not installed for owner=${groupOwner}`);
        }
        let from = groupStart;
        let remaining = groupCommands;
        while (remaining > 0) {
          const parentFrame = this.activeCommandFlushFrame;
          const frame = { applied: 0 };
          this.activeCommandFlushFrame = frame;
          try {
            // Must be slice(), not subarray(): Godot's js_buffer_to_packed_byte_array reads
            // from the underlying ArrayBuffer's start and ignores a view's byteOffset, so a
            // subarray view for any group after the first (non-zero offset) would deliver the
            // wrong commands to that owner's proxy. slice() copies into a fresh zero-offset
            // buffer. Single-group batches (match3/bunnymark) start at 0 and were unaffected;
            // multi-owner batches (e.g. dodge's new_game) require this.
            callback(words.slice(from, groupStart + groupWords), remaining);
          } finally {
            this.activeCommandFlushFrame = parentFrame;
          }
          applied += frame.applied;
          groupCrossings += 1;
          if (frame.applied >= remaining) break;
          // The proxy stopped at command `frame.applied`. When its target is an object the engine
          // freed under the script (a setter queued on a freed node), that command alone is
          // skipped and reported (see takeFreedCommandSkips) and the rest of the group runs;
          // anything else stays a protocol fault, which Kotlin's applied/expected check ends the
          // page on.
          let stop = from;
          for (let skipped = 0; skipped < frame.applied; skipped += 1) {
            stop += commandWordCount(words[stop]);
          }
          if (!this.skipFreedCommand(words[stop], words[stop + 1], words[stop + 3], groupOwner)) break;
          skippedFreed += 1;
          skippedIndices.add(groupFirstIndex + (groupCommands - remaining) + frame.applied);
          from = stop + commandWordCount(words[stop]);
          remaining -= frame.applied + 1;
        }
      };
      for (let commandIndex = 0; commandIndex < commandCount; commandIndex += 1) {
        const opcode = words[scanOffset];
        const size = commandWordCount(opcode);
        const target = words[scanOffset + 1];
        if (!this.handleOwners.has(target) && this.isFreedScriptHandle(target)) {
          // Task 138 item 22: a command queued on a script-backed object freed before this batch
          // applied is skipped and reported, like one on an engine-freed browser handle. The
          // group so far is applied first: a group's commands are one contiguous run.
          flushGroup();
          groupCommands = 0;
          groupWords = 0;
          this.recordFreedSkip(opcode, target, words[scanOffset + 3]);
          skippedFreed += 1;
          skippedIndices.add(commandIndex);
          scanOffset += size;
          continue;
        }
        const owner = this.ownerForHandle(target);
        if (groupCommands > 0 && owner !== groupOwner) {
          flushGroup();
          groupStart = scanOffset;
          groupWords = 0;
          groupCommands = 0;
        }
        if (groupCommands === 0) {
          groupStart = scanOffset;
          groupOwner = owner;
          groupFirstIndex = commandIndex;
        }
        groupWords += size;
        groupCommands += 1;
        scanOffset += size;
      }
      flushGroup();
      this.kotlinToGodotCalls += groupCrossings;
      this.kotlinToGodotMs.push(performance.now() - started);
      let wordOffset = 0;
      let positionMutationCount = 0;
      const appliedAt = (index) => index < applied + skippedFreed && !skippedIndices.has(index);
      const audioOpcodes = [];
      const commandData = new DataView(words.buffer, words.byteOffset, wordCount * 4);
      for (let commandIndex = 0; commandIndex < commandCount; commandIndex += 1) {
        const opcode = words[wordOffset];
        if (appliedAt(commandIndex) && opcode === 13) {
          const childKind = this.browserHandleSlot(words[wordOffset + 2])?.kind;
          if (childKind === "AudioStreamPlayer") this.match3AudioPlayerAdds += 1;
          else this.match3AddChildCommands += 1;
        }
        if (appliedAt(commandIndex) && opcode === 16) {
          this.match3TextureAssignments += 1;
          const tileHandle = this.match3TileRootBySprite.get(words[wordOffset + 1]);
          const textureIndex = this.match3TextureIndexByHandle.get(words[wordOffset + 2]);
          if (tileHandle !== undefined && textureIndex !== undefined) {
            this.match3TileTypeByHandle.set(tileHandle, textureIndex);
          }
        }
        if (appliedAt(commandIndex) && opcode === 3) {
          this.match3PositionMutations += 1;
          this.match3NodePositions.set(words[wordOffset + 1], {
            x: commandData.getFloat32(wordOffset * 4 + 8, true),
            y: commandData.getFloat32(wordOffset * 4 + 12, true),
          });
        }
        if (appliedAt(commandIndex) && opcode === 30) this.match3ScaleMutations += 1;
        if (appliedAt(commandIndex) && opcode === 32) this.match3ModulateMutations += 1;
        if (appliedAt(commandIndex) && opcode === 43) {
          this.match3ParticleEmittingCommands += 1;
          const particleHandle = words[wordOffset + 1];
          const snapshot = this.particleSnapshots.get(particleHandle);
          if (snapshot) snapshot.emitting = words[wordOffset + 2] !== 0;
        }
        if (appliedAt(commandIndex) && opcode === 46) {
          audioOpcodes.push(opcode);
          const playerHandle = words[wordOffset + 1];
          const streamHandle = words[wordOffset + 2];
          const state = this.audioPlayerStates.get(playerHandle);
          const stream = this.resourcePathByHandle.get(streamHandle);
          if (state) {
            state.streamPath = stream?.path ?? null;
            state.streamTypeHint = stream?.typeHint ?? null;
            state.streamCacheMode = stream?.cacheMode ?? null;
          }
          this.match3AudioStreamAssignments += 1;
        }
        if (appliedAt(commandIndex) && opcode === 47) {
          audioOpcodes.push(opcode);
          const state = this.audioPlayerStates.get(words[wordOffset + 1]);
          if (state) state.bus = this.resolveCommandStringName(words[wordOffset + 2]);
          this.match3AudioBusCommands += 1;
        }
        if (appliedAt(commandIndex) && opcode === 48) {
          audioOpcodes.push(opcode);
          const state = this.audioPlayerStates.get(words[wordOffset + 1]);
          if (state) state.volumeDb = commandData.getFloat64(wordOffset * 4 + 8, true);
          this.match3AudioVolumeCommands += 1;
        }
        if (appliedAt(commandIndex) && opcode === 49) {
          audioOpcodes.push(opcode);
          const state = this.audioPlayerStates.get(words[wordOffset + 1]);
          if (state) state.pitchScale = commandData.getFloat64(wordOffset * 4 + 8, true);
          this.match3AudioPitchCommands += 1;
        }
        if (appliedAt(commandIndex) && opcode === 50) {
          audioOpcodes.push(opcode);
          const state = this.audioPlayerStates.get(words[wordOffset + 1]);
          const fromPosition = commandData.getFloat64(wordOffset * 4 + 8, true);
          if (state) state.plays.push(fromPosition);
          this.match3AudioPlayCommands += 1;
        }
        if (appliedAt(commandIndex) && opcode === 52) {
          this.match3SceneTreeQuitCommands += 1;
        }
        if (appliedAt(commandIndex) && opcode === 3) positionMutationCount += 1;
        if (appliedAt(commandIndex) && opcode === 15) {
          this.lastFreedObjectHandle = words[wordOffset + 1];
          const slot = this.browserHandleSlot(this.lastFreedObjectHandle);
          if (slot?.kind === "AudioStreamPlayer") {
            this.audioPlayerStates.delete(this.lastFreedObjectHandle);
            this.match3AudioPlayerFrees += 1;
          }
          // A node's handle lives with the node, which stays callable to the end of the frame (a
          // second dispatch of it this frame reads it, as on desktop), so its slot -- and Kotlin's
          // registration -- stay until the engine frees it and the sweep releases the handle as
          // freed (a later use: "previously freed instance").
          if (slot) slot.freeQueued = true;
          this.objectFrees += 1;
        }
        wordOffset += commandWordCount(opcode);
      }
      if (audioOpcodes.length > 0) {
        this.match3AudioCommandBatches += 1;
        this.match3AudioCommandCrossings += groupCrossings;
        this.match3AudioBatchHistory.push(audioOpcodes);
        if (this.match3AudioBatchHistory.length > 100) this.match3AudioBatchHistory.shift();
      }
      if (positionMutationCount > 0) {
        this.positionMutationCommands += positionMutationCount;
        this.positionMutationBatches += 1;
        this.maxPositionMutationBatch = Math.max(
          this.maxPositionMutationBatch,
          positionMutationCount,
        );
        this.lastPositionMutationBatch = positionMutationCount;
      }
      // A skipped command was consumed (reported, not lost): Kotlin's applied/expected check
      // counts it, so only a command that was neither applied nor skipped fails the batch.
      return applied + skippedFreed;
    },
    /**
     * Whether a command's target handle is an object the engine freed (see flushCommands): if so
     * records the freed-instance message for [takeFreedCommandSkips] and answers true.
     */
    skipFreedCommand(opcode, handle, genericArgsId, ownerHandle) {
      // Only an engine-freed node: it keeps its bridge slot. A handle with no slot (never minted,
      // corrupt, or already released) is a protocol fault and stays fatal.
      if (this.browserHandleSlot(handle) === null) return false;
      if (!this.isHandleFreed(handle, ownerHandle)) return false;
      this.recordFreedSkip(opcode, handle, genericArgsId);
      return true;
    },
    recordFreedSkip(opcode, handle, genericArgsId, message = FREED_INSTANCE_MESSAGE(handle)) {
      // The skipped command's staged generic-call arguments would never be consumed by its
      // applier arm; drop them or a per-frame call on a freed node fills the staging map.
      if (opcode === 1001) this.stagedGenericArgs.delete(genericArgsId);
      this.freedCommandSkips.push(message);
    },
    /** Task 138 item 22: a script handle (bit 30 clear) whose script Kotlin has freed. */
    isFreedScriptHandle(handle) {
      return (
        handle > 0 &&
        (handle & BROWSER_HANDLE_NAMESPACE) === 0 &&
        this.api.kanamaWebIsFreedScriptHandle(handle) === 1
      );
    },
    /**
     * The freed-instance messages of the commands [flushCommands] skipped since the last call, one
     * per line. Kotlin reports each as a script error, so a setter on a freed node is reported and
     * the page carries on, as it does on desktop.
     */
    takeFreedCommandSkips() {
      const messages = this.freedCommandSkips.join("\n");
      this.freedCommandSkips = [];
      return messages;
    },
    recordReady(handle, scriptId, scriptName) {
      this.readyCount += 1;
      this.scriptNameByHandle[handle] = scriptName;
      this.match3ReadyByClass[scriptName] = (this.match3ReadyByClass[scriptName] ?? 0) + 1;
      this.liveScriptsByClass[scriptName] = (this.liveScriptsByClass[scriptName] ?? 0) + 1;
      // Task 81: fold in any dispatches that crossed before this handle had a name (the
      // _ready dispatch itself always does -- the proxy calls bridge.ready before recordReady).
      const pendingExercised = this.pendingExercisedByHandle.get(handle);
      if (pendingExercised) {
        this.pendingExercisedByHandle.delete(handle);
        const bucket =
          this.exercisedMembers[scriptName] ?? (this.exercisedMembers[scriptName] = {});
        for (const [memberKey, count] of Object.entries(pendingExercised)) {
          bucket[memberKey] = (bucket[memberKey] ?? 0) + count;
        }
      }
      if (scriptName.endsWith(".Smoke") || scriptName.endsWith(".SmokeQuit")) this.smokeHandle = handle;
      if (this.mode === "dodge" && scriptName.endsWith(".Main")) {
        // The Web smoke drives gameplay from the browser driver (dodge's SmokeQuit
        // gate reads an env var, which Kotlin/Wasm cannot; the driver calls new_game
        // through this handle instead, mirroring bunnymark's method-call driving).
        this.dodgeMainHandle = handle;
      }
      if (this.mode === "web3d" && scriptName.endsWith(".Main")) this.web3dMainHandle = handle;
      if (this.mode === "platformer" && scriptName.endsWith(".Main")) {
        this.platformerMainHandle = handle;
      }
      if (this.mode === "platformer" && scriptName.endsWith(".Player")) {
        // Task 81: the driver injects movement (ops 86/87), pins the stance (142), reads
        // positions (138) and queries SoundFootsteps is_playing (287) through this handle.
        this.platformerPlayerHandle = handle;
      }
      if (this.mode === "platformer" && scriptName.endsWith(".Hud")) {
        // Task 81: the driver reads the "Coins" Label under the Hud Control (288) to
        // assert the collision -> cross-script call -> signal -> HUD chain end to end.
        this.platformerHudHandle = handle;
      }
      if (this.mode === "squash" && scriptName.endsWith(".Main")) {
        this.squashMainHandle = handle;
      }
      if (this.mode === "squash" && scriptName.endsWith(".Player")) {
        // Task 81: movement injection, stance pins, and position reads ride this handle.
        this.squashPlayerHandle = handle;
      }
      if (this.mode === "squash" && scriptName.endsWith(".ScoreLabel")) {
        // Task 81: the driver reads this Label's own text (288, empty child path) to
        // assert the squash -> squashed signal -> ScoreLabel chain scored.
        this.squashScoreLabelHandle = handle;
      }
      if (this.mode === "squash" && scriptName.endsWith(".Mob")) {
        // Task 81: the LATEST ready mob is the driver's squash target. Mobs free
        // themselves, so this handle goes stale; the driver re-reads it each poll and
        // tolerates a stale-handle throw rather than the bridge tracking frees here.
        this.squashMobHandle = handle;
      }
      if (this.mode === "fps" && scriptName.endsWith(".Player")) {
        this.fpsPlayerHandle = handle;
      }
      if (this.mode === "charactercontroller" && scriptName.endsWith(".Player3DTemplate")) {
        this.charPlayerHandle = handle;
      }
      if (this.mode === "thirdperson" && scriptName.endsWith(".Player")) {
        this.tpPlayerHandle = handle;
      }
      if (this.mode === "thirdperson" && scriptName.endsWith(".DemoPage")) {
        this.tpDemoPageHandle = handle;
      }
      if (this.mode === "racing" && scriptName.endsWith(".Vehicle")) {
        // The player vehicle uses the base class (trucks/motorcycle variants subclass it).
        this.racingVehicleHandle = handle;
      }
      if (this.mode === "racing" && scriptName.endsWith(".View")) {
        // The camera rig lerps toward the vehicle every tick: its root position is the
        // driver's movement evidence (the Vehicle ROOT node intentionally never moves).
        this.racingViewHandle = handle;
      }
      if (this.mode === "tpsdemo" && scriptName.endsWith(".Main")) {
        // The scene root pumps the frame scheduler.
        this.tpsMainHandle = handle;
      }
      if (this.mode === "tpsdemo" && scriptName.endsWith(".Menu")) {
        this.tpsMenuHandle = handle;
      }
      if (this.mode === "tpsdemo" && scriptName.endsWith(".Level")) {
        this.tpsLevelHandle = handle;
      }
      if (this.mode === "tpsdemo" && scriptName.endsWith(".Player")) {
        // The player body is the driver's movement evidence (opcode 138 on this handle).
        this.tpsPlayerHandle = handle;
      }
      if (this.mode === "citybuilder" && scriptName.endsWith(".Builder")) {
        // The driver reads the gridmap/selector property handles off the Builder to
        // observe placements and selector movement.
        this.cbBuilderHandle = handle;
      }
      if (this.mode === "match3") {
        this.match3ScriptNamesByHandle.set(handle, scriptName);
        if (scriptName.endsWith(".Audio")) {
          this.match3AudioHandle = handle;
        }
        if (scriptName.endsWith(".Tile") && this.match3FirstTileHandle === 0) {
          this.match3FirstTileHandle = handle;
        }
        if (scriptName.endsWith(".Main")) {
          this.match3MainHandle = handle;
          this.finishMatch3Group1(handle, scriptId, scriptName);
        }
        return;
      }
      if (this.mode === "bunnymark" && this.previewBunnies > 0 && !this.previewScheduled) {
        this.previewScheduled = true;
        setTimeout(() => {
          if (this.api.kanamaWebIsLive(handle) !== 1) return;
          for (let index = 0; index < this.previewBunnies; index += 1) {
            this.callNoArgs(handle, this.bunnymarkMethodId("add"));
          }
          updateStatus(`Running Kotlin/Wasm Bunnymark with ${this.previewBunnies} bunnies…`);
        }, 150);
      }
      // Single-script benchmark lifecycle (spike/bunnymark): the first ready is the
      // benchmark script; a second ready is a hot-reload replacement that finishes the
      // run. Scene-based demos (match3 returns early above; dodge and any future scene
      // demo) have many scripts and must not treat the 2nd ready as a benchmark finish.
      if (this.mode === "bunnymark" || this.mode === "spike") {
        if (this.readyCount === 1) {
          this.firstHandle = handle;
          this.results.startup.timeToFirstReadyMs =
            performance.now() - globalThis.KanamaWebPageStartedAt;
          updateStatus("Running frame and bridge benchmarks…");
        } else {
          this.results.lifecycle.replacementHandle = handle;
          this.results.lifecycle.generationAdvanced = handle !== this.firstHandle;
          this.results.lifecycle.staleHandleInvalidated =
            this.api.kanamaWebIsLive(this.freedHandle) === 0;
          this.finish();
        }
      }
    },
    finishMatch3Group1(handle, scriptId, scriptName) {
      const properties = this.match3Properties.get(handle) ?? {};
      const tileClass = Object.keys(this.match3ReadyByClass).find((name) => name.endsWith(".Tile"));
      const snapshot = {
        mode: this.mode,
        main: { handle, scriptId, scriptName },
        exported: {
          width: properties[1],
          height: properties[2],
          offset: properties[3],
          tileSceneAssigned: Number.isInteger(properties[4]) && properties[4] > 0,
          sparklesSceneAssigned: Number.isInteger(properties[5]) && properties[5] > 0,
          textureCount: Array.isArray(properties[6]) ? properties[6].length : -1,
          openCursorAssigned: Number.isInteger(properties[7]) && properties[7] > 0,
          closedCursorAssigned: Number.isInteger(properties[8]) && properties[8] > 0,
        },
        board: {
          tileScriptReadyCount: tileClass ? this.match3ReadyByClass[tileClass] : 0,
          packedSceneInstantiations: this.match3PackedSceneInstantiations,
          addChildCommands: this.match3AddChildCommands,
          textureAssignments: this.match3TextureAssignments,
          positionMutations: this.match3PositionMutations,
          cursorSets: this.match3CursorSets,
          connections: this.match3Connections,
        },
        audio: {
          handle: this.match3AudioHandle,
          readyCount: Object.entries(this.match3ReadyByClass)
            .filter(([name]) => name.endsWith(".Audio"))
            .reduce((total, [, count]) => total + count, 0),
          playersConstructed: this.match3AudioPlayersConstructed,
          playerAdds: this.match3AudioPlayerAdds,
          connections: this.match3AudioConnections,
          busCommands: this.match3AudioBusCommands,
          volumeCommands: this.match3AudioVolumeCommands,
          initializedPlayers: [...this.audioPlayerStates.values()].filter(
            (state) => state.bus === "master" && state.volumeDb === -10.0,
          ).length,
        },
        pendingFrameCoroutines: this.api.kanamaWebPendingCoroutineCount(),
        deferredSubsystemReady: this.match3DeferredReadyByClass,
        callbackErrors: this.callbackErrors,
      };
      const checks = {
        originalDimensions:
          snapshot.exported.width === 8 &&
          snapshot.exported.height === 8 &&
          snapshot.exported.offset === 68,
        originalResources:
          snapshot.exported.tileSceneAssigned &&
          snapshot.exported.sparklesSceneAssigned &&
          snapshot.exported.textureCount === 5 &&
          snapshot.exported.openCursorAssigned &&
          snapshot.exported.closedCursorAssigned,
        exactTileInstances: snapshot.board.packedSceneInstantiations === 64,
        exactTileScripts: snapshot.board.tileScriptReadyCount === 64,
        exactBoardAdds: snapshot.board.addChildCommands === 64,
        texturesAssigned: snapshot.board.textureAssignments === 64,
        boardPositioned: snapshot.board.positionMutations >= 65,
        cursorConfigured: snapshot.board.cursorSets === 1,
        boardSignalsWired: snapshot.board.connections === 65,
        laterCoroutineExplicitlyPending: snapshot.pendingFrameCoroutines === 1,
        audioPoolReady:
          snapshot.audio.handle > 0 &&
          snapshot.audio.readyCount === 1 &&
          snapshot.audio.playersConstructed === 12 &&
          snapshot.audio.playerAdds === 12 &&
          snapshot.audio.connections === 12 &&
          snapshot.audio.busCommands === 12 &&
          snapshot.audio.volumeCommands === 12 &&
          snapshot.audio.initializedPlayers === 12,
        noBoundaryErrors: snapshot.callbackErrors === 0,
      };
      snapshot.checks = checks;
      snapshot.pass = Object.values(checks).every(Boolean);
      globalThis.KanamaWebMatch3Results = snapshot;
      document.body.dataset.status = snapshot.pass ? "pass" : "fail";
      updateStatus(
        snapshot.pass ? "MATCH3 BOARD PASS" : "MATCH3 BOARD FAIL",
        snapshot.pass ? "pass" : "fail",
      );
      document.querySelector("#kanama-results").textContent = JSON.stringify(snapshot, null, 2);
      console.info("[kanama:web-match3] RESULT", JSON.stringify(snapshot));
    },
    recordImmediateResult(value) {
      this.immediateResult = value;
    },
    recordImmediateChildCount(value) {
      this.immediateChildCountResult = value;
    },
    recordImmediateResourceHandle(value) {
      this.immediateResourceHandleResult = value;
    },
    recordImmediateSignalResult(value) {
      this.immediateSignalResult = value;
      this.signalEmits += 1;
    },
    recordImmediateResourceRelease(value) {
      this.immediateResourceReleaseResult = value;
      if (value === 1) this.resourceReleases += 1;
    },
    recordImmediateConstructHandle(value) {
      this.immediateConstructHandleResult = value;
    },
    recordApplied(count, lastValue) {
      this.appliedCommands += count;
      if (this.activeCommandFlushFrame !== null) {
        this.activeCommandFlushFrame.applied += count;
      }
      this.lastAppliedValue = lastValue;
    },
    recordFreed(handle) {
      if (this.particleSnapshots.delete(handle)) this.match3ParticleFrees += 1;
      this.freedHandle = handle;
      this.results.lifecycle.freedHandle = handle;
      this.results.lifecycle.liveAfterFree = this.api.kanamaWebIsLive(handle);
    },
    recordReloadStarted() {
      this.reloadStarted = true;
    },
    shouldReload() {
      return this.reloadRequested && !this.reloadStarted;
    },
    recordGdscriptChecksum(mode, checksum) {
      this.checksums[mode] = checksum;
    },
    recordGdscriptBenchmark(mode, elapsedMs) {
      this.latestGdscriptBenchmark[mode] = elapsedMs;
    },

    bunnymarkSnapshot() {
      return {
        mode: this.mode,
        bunnymarkVariant: this.bunnymarkVariant,
        handle: this.firstHandle,
        readyCount: this.readyCount,
        processCalls: this.processCalls,
        noArgCalls: this.noArgCalls,
        doubleArgCalls: this.doubleArgCalls,
        packedReturnCalls: this.packedReturnCalls,
        builtinCalls: this.builtinCalls,
        addBunnyCalls: this.addBunnyCalls,
        removeBunnyCalls: this.removeBunnyCalls,
        finishCalls: this.finishCalls,
        callbackErrors: this.callbackErrors,
        lastCallbackError: this.lastCallbackError,
        resourceLoads: this.resourceLoads,
        resourceReleases: this.resourceReleases,
        objectConstructions: this.objectConstructions,
        objectFrees: this.objectFrees,
        maxLiveBrowserHandles: this.maxLiveBrowserHandles,
        liveBrowserHandles: this.liveBrowserHandleCount,
        pageHandles: this.pageHandles,
        maxPageHandles: this.maxPageHandles,
        lentMints: this.lentMints,
        lentReleases: this.lentReleases,
        lentSwept: this.lentSwept,
        liveLent: this.liveLent,
        maxLiveLent: this.maxLiveLent,
        lendBridgeCalls: this.lendBridgeCalls,
        rcPinned: this.rcPinned,
        maxRcPinned: this.maxRcPinned,
        rcReleasedByGc: this.rcReleasedByGc,
        rcReleasedByClose: this.rcReleasedByClose,
        rcReleasedByGrace: this.rcReleasedByGrace,
        rcReleasedByCap: this.rcReleasedByCap,
        wrapperRegistrations: this.wrapperRegistrations,
        wrapperRegistry: this.wrapperRegistryWorks !== false,
        lastConstructedObjectHandle: this.lastConstructedObjectHandle,
        lastFreedObjectHandle: this.lastFreedObjectHandle,
        objectHandleGenerationAdvanced: this.objectHandleGenerationAdvanced,
        signalEmits: this.signalEmits,
        lastSignalName: this.lastSignalName ?? null,
        lastSignalValue: this.lastSignalValue ?? null,
        drawCalls: this.drawCalls,
        drawCommands: this.drawCommands,
        drawBatches: this.drawBatches,
        drawCrossings: this.drawCrossings,
        maxDrawCommands: this.maxDrawCommands,
        lastDrawCommands: this.lastDrawCommands,
        movingDrawSamples: this.movingDrawSamples,
        kotlinToGodotCalls: this.kotlinToGodotCalls,
        appliedCommands: this.appliedCommands,
        positionMutationCommands: this.positionMutationCommands,
        positionMutationBatches: this.positionMutationBatches,
        maxPositionMutationBatch: this.maxPositionMutationBatch,
        lastPositionMutationBatch: this.lastPositionMutationBatch,
        firstDrawPosition: this.firstDrawPosition,
        lastDrawPosition: this.lastDrawPosition,
        commandBufferGrowths: this.commandBufferGrowths,
        processTiming: summary(this.bunnymarkProcessMs),
        applyTiming: summary(this.kotlinToGodotMs),
      };
    },

    resetBunnymarkTimings() {
      this.bunnymarkProcessMs.length = 0;
      this.kotlinToGodotMs.length = 0;
    },

    maybeRunBenchmarks() {
      if (
        this.benchmarksStarted ||
        !this.benchmarkCallback ||
        !this.firstHandle ||
        this.frameIndex < (WARMUP_FRAMES + SAMPLE_FRAMES) * 2
      ) {
        return;
      }
      this.benchmarksStarted = true;
      setTimeout(() => this.runBenchmarks(), 0);
    },

    runBenchmarks() {
      const pureKotlin = [];
      const pureGdscript = [];
      const individual = [];
      const batch = [];

      for (let trial = 0; trial < BENCHMARK_WARMUP_TRIALS; trial += 1) {
        this.api.kanamaWebBenchmarkPure(OPERATIONS);
        this.benchmarkCallback(0, OPERATIONS);
        this.api.kanamaWebBenchmarkBatch(this.firstHandle, OPERATIONS);
      }
      for (let trial = 0; trial < INDIVIDUAL_WARMUP_TRIALS; trial += 1) {
        this.benchmarkCallback(1, OPERATIONS);
      }

      for (let trial = 0; trial < 20; trial += 1) {
        pureKotlin.push(this.api.kanamaWebBenchmarkPure(OPERATIONS));
        this.benchmarkCallback(0, OPERATIONS);
        pureGdscript.push(this.latestGdscriptBenchmark[0]);
        batch.push(this.api.kanamaWebBenchmarkBatch(this.firstHandle, OPERATIONS));
      }
      for (let trial = 0; trial < 5; trial += 1) {
        this.benchmarkCallback(1, OPERATIONS);
        individual.push(this.latestGdscriptBenchmark[1]);
      }

      const contractBefore = {
        appliedCommands: this.appliedCommands,
        kotlinToGodotCalls: this.kotlinToGodotCalls,
        snapshotBatchLoads: this.snapshotBatchLoads,
        immediateCalls: this.immediateCalls,
      };
      this.refreshPositionSnapshot(
        this.firstHandle,
        this.latestSnapshotX,
        this.latestSnapshotY,
      );
      const contractChildCount = this.api.kanamaWebBenchmarkBackendContract(
        this.firstHandle,
        OPERATIONS,
      );
      this.results.backendContract = {
        queuedCommands: this.appliedCommands - contractBefore.appliedCommands,
        queuedCrossings: this.kotlinToGodotCalls - contractBefore.kotlinToGodotCalls,
        snapshotBatchLoads: this.snapshotBatchLoads - contractBefore.snapshotBatchLoads,
        immediateCalls: this.immediateCalls - contractBefore.immediateCalls,
        childCount: contractChildCount,
        finalPositionX: this.lastAppliedValue,
        commandBufferGrowths: this.commandBufferGrowths,
      };

      this.results.benchmarks = {
        operationsPerTrial: OPERATIONS,
        warmupTrials: BENCHMARK_WARMUP_TRIALS,
        individualWarmupTrials: INDIVIDUAL_WARMUP_TRIALS,
        pureKotlinWasm: summary(pureKotlin),
        equivalentGdscript: summary(pureGdscript),
        emptyFrameCallback: summary(this.emptyFrameMs),
        individualTransformRoundTrips: summary(individual),
        generatedBatchWithTransformApply: summary(batch),
        representativeBatchedFrame: summary(this.batchedFrameMs),
        kotlinToGodotApply: summary(this.kotlinToGodotMs),
        kotlinToGodotCalls: this.kotlinToGodotCalls,
      };
      this.results.lifecycle.immediateResult = this.immediateResult;
      this.results.lifecycle.appliedCommands = this.appliedCommands;
      this.results.lifecycle.lastAppliedValue = this.lastAppliedValue;
      this.results.rendering = {
        resourceLoads: this.resourceLoads,
        drawCalls: this.drawCalls,
        drawCommands: this.drawCommands,
      };
      this.results.environment.peakJsHeapBytes = performance.memory?.usedJSHeapSize ?? null;
      this.reloadRequested = true;
      updateStatus("Benchmarks complete; validating teardown and generation reuse…");
    },

    finish() {
      const checks = {
        protocol: this.results.protocolVersion === KANAMA_WEB_PROTOCOL_VERSION,
        immediateResult: this.immediateResult === 47,
        queuedMutation: this.appliedCommands >= OPERATIONS,
        freed: this.results.lifecycle.liveAfterFree === 0,
        generationAdvanced: this.results.lifecycle.generationAdvanced === true,
        staleHandleInvalidated: this.results.lifecycle.staleHandleInvalidated === true,
        emptyFrameSamples: this.emptyFrameMs.length === SAMPLE_FRAMES,
        batchedFrameSamples: this.batchedFrameMs.length === SAMPLE_FRAMES,
        gdscriptMeasurements: Number.isFinite(
          this.results.benchmarks.equivalentGdscript?.p50Ms,
        ),
        individualMeasurements: Number.isFinite(
          this.results.benchmarks.individualTransformRoundTrips?.p50Ms,
        ),
        backendQueuedCommands: this.results.backendContract.queuedCommands === OPERATIONS + 1,
        backendQueuedCrossings: this.results.backendContract.queuedCrossings === 1,
        backendSnapshotBatch: this.results.backendContract.snapshotBatchLoads === 1,
        backendImmediateExplicit: this.results.backendContract.immediateCalls === 1,
        backendImmediateValue: this.results.backendContract.childCount === 3,
        backendReadYourWrite: this.results.backendContract.finalPositionX === OPERATIONS - 1,
        backendNoBufferGrowth: this.results.backendContract.commandBufferGrowths === 0,
        textureLoaded: this.resourceLoads >= 1,
        kotlinDrawApplied: this.drawCommands >= 1,
      };
      this.results.checks = checks;
      this.results.pass = Object.values(checks).every(Boolean);
      this.results.completedAt = new Date().toISOString();
      globalThis.KanamaWebSpikeResults = this.results;
      document.body.dataset.status = this.results.pass ? "pass" : "fail";
      updateStatus(this.results.pass ? "PASS" : "FAIL", this.results.pass ? "pass" : "fail");
      document.querySelector("#kanama-results").textContent = JSON.stringify(this.results, null, 2);
      console.info("[kanama:web-spike] RESULT", JSON.stringify(this.results));
    },
  };

  globalThis.failKanamaWeb = (error) => {
    document.body.dataset.status = "fail";
    updateStatus("FAIL", "fail");
    document.querySelector("#kanama-results").textContent = error?.stack ?? String(error);
    console.error("[kanama:web-spike] FATAL", error);
  };

  // Stamp engine ticks for the physics/process ordering counter: one increment per
  // animation-frame timestamp (Godot registers a single main-loop rAF callback).
  const originalRequestAnimationFrame = globalThis.requestAnimationFrame.bind(globalThis);
  globalThis.requestAnimationFrame = (callback) =>
    originalRequestAnimationFrame((timestamp) => {
      if (timestamp !== bridge.lastRafTimestamp) {
        bridge.lastRafTimestamp = timestamp;
        bridge.rafTick += 1;
      }
      return callback(timestamp);
    });

  globalThis.bootstrapKanamaWeb = async (apiPromise) => {
    const api = await apiPromise;
    const protocolVersion = api.kanamaWebProtocolVersion();
    if (protocolVersion !== KANAMA_WEB_PROTOCOL_VERSION) {
      throw new Error(
        `Kanama Web protocol mismatch: expected ${KANAMA_WEB_PROTOCOL_VERSION}, received ${protocolVersion}`,
      );
    }
    bridge.api = api;
    bridge.results.protocolVersion = protocolVersion;
    bridge.results.startup.kotlinModuleReadyMs =
      performance.now() - globalThis.KanamaWebPageStartedAt;
    globalThis.KanamaWebBridge = bridge;
    updateStatus("Kotlin/Wasm ready; starting Godot…");
    return bridge;
  };
})();

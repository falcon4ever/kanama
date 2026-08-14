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
  const KANAMA_WEB_PROTOCOL_VERSION = 18;

  function commandWordCount(opcode) {
    if (
      opcode === 5 ||
      opcode === 15 ||
      opcode === 58 ||
      opcode === 59 ||
      opcode === 63 ||
      opcode === 64 ||
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
      opcode === 264 ||
      opcode === 265 ||
      opcode === 267 ||
      opcode === 273 ||
      opcode === 274 ||
      opcode === 277 ||
      opcode === 280 ||
      opcode === 284 ||
      opcode === 285 ||
      opcode === 286 ||
      opcode === 66
    ) return 3;
    if (
      opcode === 3 ||
      opcode === 30 ||
      opcode === 43 ||
      opcode === 48 ||
      opcode === 49 ||
      opcode === 50 ||
      opcode === 53 ||
      opcode === 54 ||
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
    signalVector2iCallbacks: new Map(),
    tweenCallbacks: new Map(),
    handleOwners: new Map(),
    browserNodeHandlesByScript: new Map(),
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
    maxLiveBrowserHandles: 0,
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
    simSeconds: 0,
    noArgCalls: 0,
    // Task 80 slice 2: how often the two new registered-method crossings actually ran. A
    // driver can otherwise only see the SIDE EFFECT of a dispatched method, never the dispatch.
    doubleArgCalls: 0,
    packedReturnCalls: 0,
    addBunnyCalls: 0,
    removeBunnyCalls: 0,
    finishCalls: 0,
    callbackErrors: 0,
    lastCallbackError: null,
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
    // @RegisterFunction dispatches (the engine drivers resolve the id to the manifest name from
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
    dodgeMainHandle: 0,
    dodgeSmokeQuitHandle: 0,
    web3dMainHandle: 0,
    web3dSmokeQuitHandle: 0,
    platformerMainHandle: 0,
    platformerSmokeQuitHandle: 0,
    // Task 81: the platformer driver drives gameplay -- the Player handle carries the
    // action press/release (ops 86/87), stance pins (142), position reads (138) and the
    // SoundFootsteps is_playing query (287); the Hud handle carries the "Coins" Label
    // text read (288).
    platformerPlayerHandle: 0,
    platformerHudHandle: 0,
    squashMainHandle: 0,
    squashSmokeQuitHandle: 0,
    // Task 81: the squash driver drives gameplay -- the Player handle carries movement
    // injection and stance pins; the ScoreLabel handle carries the score text read (288);
    // the Mob handle is the LATEST ready mob, the driver's squash target. Mobs free
    // themselves (squash or screen-exit), so this goes stale -- the driver re-reads it
    // every poll and tolerates a stale-handle throw.
    squashPlayerHandle: 0,
    squashScoreLabelHandle: 0,
    squashMobHandle: 0,
    fpsSmokeHandle: 0,
    fpsPlayerHandle: 0,
    charSmokeQuitHandle: 0,
    charPlayerHandle: 0,
    tpSmokeQuitHandle: 0,
    tpPlayerHandle: 0,
    tpDemoPageHandle: 0,
    tpsMainHandle: 0,
    tpsMenuHandle: 0,
    tpsLevelHandle: 0,
    tpsPlayerHandle: 0,
    racingSmokeHandle: 0,
    racingVehicleHandle: 0,
    racingViewHandle: 0,
    cbSmokeHandle: 0,
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
        this.callbackErrors += 1;
        this.lastCallbackError = contextual.message;
        globalThis.failKanamaWeb(contextual);
        return fallback;
      } finally {
        this.activeOwnerHandle = previousOwner;
      }
    },

    /**
     * Task 81 (census-as-gate): record one boundary dispatch into the exercised-member census.
     *
     * Categories: registered @RegisterFunction dispatches (all nine call* funnels pass
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

    create(scriptId) {
      const handle = this.invoke(0, "create", `script#${scriptId}`, () => this.api.kanamaWebCreate(scriptId), 0);
      return handle;
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
        this.simSeconds += delta;
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
      // Physics-driven demos (character controllers, racing, third-person) run their
      // script work here and never touch _process, so simulated time must accumulate
      // on this path too or those demos report zero.
      this.simSeconds += delta;
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
        "",
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
    // path. Every generated proxy used to call its first Int->Int @RegisterFunction with
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
        "",
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
      // Ordinals follow the @RegisterFunction order in dodge's Main.kt (mirrored by
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
      if (result === 1 && handle === this.match3AudioHandle) this.match3AudioHandle = 0;
      if (result === 1) {
        const childNodeHandles = this.browserNodeHandlesByScript.get(handle);
        if (childNodeHandles) {
          for (const childHandle of childNodeHandles) {
            if (this.browserHandleSlot(childHandle)?.kind !== "Node") continue;
            this.api.kanamaWebDiscardNodeHandle(childHandle);
            this.releaseBrowserHandle(childHandle, "Node");
          }
          this.browserNodeHandlesByScript.delete(handle);
        }
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
      }
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
      if (!owner) throw new Error(`No Kanama Web proxy owns handle=${handle}`);
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
      const handle = BROWSER_HANDLE_NAMESPACE | (slot.generation << 16) | slotIndex;
      this.handleOwners.set(handle, owner);
      this.liveBrowserHandleCount += 1;
      this.maxLiveBrowserHandles = Math.max(this.maxLiveBrowserHandles, this.liveBrowserHandleCount);
      return handle;
    },
    allocateTransientObjectHandle(owner) {
      const handle = this.allocateBrowserHandle("Object", owner);
      this.api.kanamaWebAdoptObjectHandle(handle);
      return handle;
    },
    releaseTransientObjectHandle(handle) {
      // The dispatched callback may have torn down the handle's owner (ui_accept retry
      // reloads the scene, freeing every node and releasing the owner's handles — this
      // transient among them). Already-released is fine; only release a live handle.
      const slot = this.browserHandleSlot(handle);
      if (!slot || slot.kind !== "Object") return;
      this.api.kanamaWebDiscardBrowserHandle(handle);
      this.releaseBrowserHandle(handle, "Object");
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
      slot.live = false;
      slot.kind = null;
      this.handleOwners.delete(handle);
      this.liveBrowserHandleCount -= 1;
      this.freeBrowserHandleSlots.push(handle & BROWSER_HANDLE_SLOT_MASK);
    },
    releaseBrowserHandlesOwnedBy(owner) {
      for (const tweenHandle of [...this.tweenChildren.keys()]) {
        if (this.handleOwners.get(tweenHandle) === owner) this.tweenChildren.delete(tweenHandle);
      }
      for (const [handle, handleOwner] of [...this.handleOwners]) {
        if (handleOwner !== owner || (handle & BROWSER_HANDLE_NAMESPACE) === 0) continue;
        const slot = this.browserHandleSlot(handle);
        if (!slot) continue;
        if (slot.kind === "Sprite2D") this.objectFrees += 1;
        if (slot.kind === "AudioStreamPlayer") {
          this.audioPlayerStates.delete(handle);
          this.match3AudioPlayerFrees += 1;
        }
        if (slot.kind === "Resource") this.resourcePathByHandle.delete(handle);
        if (
          slot.kind === "SceneTree" &&
          this.sceneTreeHandlesByOwner.get(handleOwner) === handle
        ) {
          this.sceneTreeHandlesByOwner.delete(handleOwner);
        }
        this.api.kanamaWebDiscardBrowserHandle(handle);
        slot.live = false;
        slot.kind = null;
        this.handleOwners.delete(handle);
        this.freeBrowserHandleSlots.push(handle & BROWSER_HANDLE_SLOT_MASK);
        this.liveBrowserHandleCount -= 1;
      }
      this.sceneTreeHandlesByOwner.delete(owner);
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
        throw new Error("Godot immediate callback did not publish a child count");
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
    releaseConstructedObject(handle) {
      // Release a ClassDB-constructed handle whose bridge kind is its class name (e.g. a
      // constructed MeshLibrary Resource): the applier erases the dictionary reference and
      // the slot retires under its recorded kind.
      const slot = this.browserHandleSlot(handle);
      if (!slot) {
        throw new Error(`Stale Kanama Web constructed handle=${handle}`);
      }
      const callback = this.callbackFor(
        this.resourceReleaseCallbacks,
        handle,
        "Godot constructed release",
      );
      this.immediateResourceReleaseResult = null;
      callback(handle);
      if (!Number.isInteger(this.immediateResourceReleaseResult)) {
        throw new Error("Godot constructed release callback did not publish a result");
      }
      if (this.immediateResourceReleaseResult === 1) {
        this.releaseBrowserHandle(handle, slot.kind);
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
      // GDScript arm classified (node / resource / object), OWNED by the calling script
      // per the task-61 "close what you create" contract: released explicitly through
      // the kind-specific release paths (releaseResource / releaseConstructedObject) or
      // implicitly by releaseBrowserHandlesOwnedBy at the owner script's teardown.
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
      const handle = this.allocateBrowserHandle("Object", ownerHandle);
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
        throw new Error("Godot signal callback did not publish a result");
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
        throw new Error("Godot no-args signal callback did not publish a result");
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
        throw new Error("Godot Vector2i signal callback did not publish a result");
      }
      if (this.mode === "match3") this.match3Vector2iSignalEmits += 1;
      return this.immediateSignalResult;
    },
    releaseResource(handle) {
      const callback = this.callbackFor(
        this.resourceReleaseCallbacks,
        handle,
        "Godot resource release",
      );
      this.immediateResourceReleaseResult = null;
      callback(handle);
      if (!Number.isInteger(this.immediateResourceReleaseResult)) {
        throw new Error("Godot resource release callback did not publish a result");
      }
      if (this.immediateResourceReleaseResult === 1) {
        if (this.resourcePathByHandle.get(handle)?.typeHint === "AudioStream") {
          this.match3AudioResourceReleases += 1;
        }
        this.resourcePathByHandle.delete(handle);
        this.releaseBrowserHandle(handle, "Resource");
      }
      return this.immediateResourceReleaseResult;
    },
    releaseCollision(handle) {
      // KinematicCollision3D records ride the same proxy release channel as resources
      // (the applier erases the object from its handle dictionary), but the browser
      // handle was allocated with the KinematicCollision3D kind.
      const callback = this.callbackFor(
        this.resourceReleaseCallbacks,
        handle,
        "Godot collision release",
      );
      this.immediateResourceReleaseResult = null;
      callback(handle);
      if (!Number.isInteger(this.immediateResourceReleaseResult)) {
        throw new Error("Godot collision release callback did not publish a result");
      }
      if (this.immediateResourceReleaseResult === 1) {
        this.releaseBrowserHandle(handle, "KinematicCollision3D");
      }
      return this.immediateResourceReleaseResult;
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
        throw new Error("Godot property object query callback did not publish a result");
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
      if (result !== 0 && result !== resultHandle) {
        const scriptHandle = this.api.kanamaWebIsLive(result) === 1;
        if (!scriptHandle && this.isBrowserHandleLive(result) !== 1) {
          throw new Error("Godot node lookup callback returned neither its proposed nor a live object handle");
        }
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
      if (
        this.api.kanamaWebIsLive(handle) === 1 &&
        this.browserHandleSlot(result)?.kind === "Node"
      ) {
        const childHandles = this.browserNodeHandlesByScript.get(handle) ?? new Set();
        childHandles.add(result);
        this.browserNodeHandlesByScript.set(handle, childHandles);
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
      // opcode 112 = KinematicCollision3D.get_collider returns a live scene node that is
      // usually already tracked (a scripted Mob resolves to its script handle; a looked-up
      // Ground reuses its browser handle) — tolerate an existing live handle like a node
      // lookup instead of requiring the proposed one. opcode 114 = Node.duplicate adopts a
      // genuinely new node through the proposed handle.
      const isCollider = opcode === 112 || opcode === 122;
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
      if (result !== 0 && result !== resultHandle) {
        const liveScript = this.api.kanamaWebIsLive(result) === 1;
        if (!isCollider || (!liveScript && this.isBrowserHandleLive(result) !== 1)) {
          throw new Error("Godot no-args object callback published an invalid handle");
        }
        this.api.kanamaWebDiscardNodeHandle(resultHandle);
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
        this.sceneTreeHandlesByOwner.set(owner, resultHandle);
        this.match3SceneTreeHandlesCreated += 1;
      } else if (isViewport && result === resultHandle) {
        this.viewportHandlesByOwner.set(owner, resultHandle);
      } else if (isRoot && result === resultHandle) {
        this.rootHandlesByOwner.set(owner, resultHandle);
      }
      return result;
    },
    immediateTweenNoArgs(opcode, handle) {
      const callback = this.callbackFor(this.tweenCallbacks, handle, "Godot Tween");
      this.immediateLongResult = null;
      callback(opcode, handle);
      if (!Number.isInteger(this.immediateLongResult)) {
        throw new Error("Godot Tween no-args callback did not publish a result");
      }
      return this.immediateLongResult;
    },
    immediateTweenBoolRetObject(opcode, handle, value) {
      const callback = this.callbackFor(this.tweenCallbacks, handle, "Godot Tween bool");
      this.immediateObjectHandleResult = null;
      callback(opcode, handle, value);
      if (this.immediateObjectHandleResult !== handle) {
        throw new Error("Godot Tween bool callback did not return its receiver");
      }
      return handle;
    },
    immediateTweenLongRetObject(opcode, handle, value) {
      const callback = this.callbackFor(this.tweenCallbacks, handle, "Godot Tweener long");
      this.immediateObjectHandleResult = null;
      callback(opcode, handle, value);
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
      if (result !== 0 && result !== resultHandle) {
        throw new Error("Godot move-and-collide callback published an invalid handle");
      }
      if (result === 0) {
        this.api.kanamaWebDiscardBrowserHandle(resultHandle);
        this.releaseBrowserHandle(resultHandle, "KinematicCollision3D");
      }
      return result;
    },
    immediateStringQuery(opcode, handle, value) {
      const callback = this.callbackFor(this.objectQueryCallbacks, handle, "Godot object query");
      this.immediateStringResult = null;
      callback(opcode, handle, value);
      if (typeof this.immediateStringResult !== "string") {
        throw new Error("Godot string query callback did not publish a string result");
      }
      return this.immediateStringResult;
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
        throw new Error("Godot Vector2-arg Vector3 callback did not publish a finite result");
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
        throw new Error("Godot indexed Vector3 callback did not publish a finite result");
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
        throw new Error("Godot bound disconnect callback did not publish a result");
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
        throw new Error("Godot connect callback did not publish a result");
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
        throw new Error("Godot bound connect callback did not publish a result");
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
        throw new Error("Godot object query callback did not publish an integer result");
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
        throw new Error("Godot set_progress_ratio callback did not confirm application");
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
        throw new Error("Godot double-query callback did not confirm application");
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
        throw new Error("Godot Vector2 callback did not publish a finite result");
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
        throw new Error("Godot Vector3 callback did not publish a finite result");
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
      const flushGroup = () => {
        if (groupCommands === 0) return;
        const callback = this.applyCallbacks.get(groupOwner);
        if (!callback) {
          throw new Error(`Godot command callback is not installed for owner=${groupOwner}`);
        }
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
          callback(words.slice(groupStart, groupStart + groupWords), groupCommands);
        } finally {
          this.activeCommandFlushFrame = parentFrame;
        }
        applied += frame.applied;
        groupCrossings += 1;
      };
      for (let commandIndex = 0; commandIndex < commandCount; commandIndex += 1) {
        const opcode = words[scanOffset];
        const owner = this.ownerForHandle(words[scanOffset + 1]);
        const size = commandWordCount(opcode);
        if (groupCommands > 0 && owner !== groupOwner) {
          flushGroup();
          groupStart = scanOffset;
          groupWords = 0;
          groupCommands = 0;
        }
        if (groupCommands === 0) {
          groupStart = scanOffset;
          groupOwner = owner;
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
      const audioOpcodes = [];
      const commandData = new DataView(words.buffer, words.byteOffset, wordCount * 4);
      for (let commandIndex = 0; commandIndex < commandCount; commandIndex += 1) {
        const opcode = words[wordOffset];
        if (commandIndex < applied && opcode === 13) {
          const childKind = this.browserHandleSlot(words[wordOffset + 2])?.kind;
          if (childKind === "AudioStreamPlayer") this.match3AudioPlayerAdds += 1;
          else this.match3AddChildCommands += 1;
        }
        if (commandIndex < applied && opcode === 16) {
          this.match3TextureAssignments += 1;
          const tileHandle = this.match3TileRootBySprite.get(words[wordOffset + 1]);
          const textureIndex = this.match3TextureIndexByHandle.get(words[wordOffset + 2]);
          if (tileHandle !== undefined && textureIndex !== undefined) {
            this.match3TileTypeByHandle.set(tileHandle, textureIndex);
          }
        }
        if (commandIndex < applied && opcode === 3) {
          this.match3PositionMutations += 1;
          this.match3NodePositions.set(words[wordOffset + 1], {
            x: commandData.getFloat32(wordOffset * 4 + 8, true),
            y: commandData.getFloat32(wordOffset * 4 + 12, true),
          });
        }
        if (commandIndex < applied && opcode === 30) this.match3ScaleMutations += 1;
        if (commandIndex < applied && opcode === 32) this.match3ModulateMutations += 1;
        if (commandIndex < applied && opcode === 43) {
          this.match3ParticleEmittingCommands += 1;
          const particleHandle = words[wordOffset + 1];
          const snapshot = this.particleSnapshots.get(particleHandle);
          if (snapshot) snapshot.emitting = words[wordOffset + 2] !== 0;
        }
        if (commandIndex < applied && opcode === 46) {
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
        if (commandIndex < applied && opcode === 47) {
          audioOpcodes.push(opcode);
          const state = this.audioPlayerStates.get(words[wordOffset + 1]);
          if (state) state.bus = this.resolveCommandStringName(words[wordOffset + 2]);
          this.match3AudioBusCommands += 1;
        }
        if (commandIndex < applied && opcode === 48) {
          audioOpcodes.push(opcode);
          const state = this.audioPlayerStates.get(words[wordOffset + 1]);
          if (state) state.volumeDb = commandData.getFloat64(wordOffset * 4 + 8, true);
          this.match3AudioVolumeCommands += 1;
        }
        if (commandIndex < applied && opcode === 49) {
          audioOpcodes.push(opcode);
          const state = this.audioPlayerStates.get(words[wordOffset + 1]);
          if (state) state.pitchScale = commandData.getFloat64(wordOffset * 4 + 8, true);
          this.match3AudioPitchCommands += 1;
        }
        if (commandIndex < applied && opcode === 50) {
          audioOpcodes.push(opcode);
          const state = this.audioPlayerStates.get(words[wordOffset + 1]);
          const fromPosition = commandData.getFloat64(wordOffset * 4 + 8, true);
          if (state) state.plays.push(fromPosition);
          this.match3AudioPlayCommands += 1;
        }
        if (commandIndex < applied && opcode === 52) {
          this.match3SceneTreeQuitCommands += 1;
        }
        if (commandIndex < applied && opcode === 3) positionMutationCount += 1;
        if (commandIndex < applied && opcode === 15) {
          this.lastFreedObjectHandle = words[wordOffset + 1];
          const slot = this.browserHandleSlot(this.lastFreedObjectHandle);
          if (slot?.kind === "AudioStreamPlayer") {
            this.audioPlayerStates.delete(this.lastFreedObjectHandle);
            this.match3AudioPlayerFrees += 1;
          }
          if (slot) this.releaseBrowserHandle(this.lastFreedObjectHandle, slot.kind);
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
      return applied;
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
      if (this.mode === "dodge" && scriptName.endsWith(".Main")) {
        // The Web smoke drives gameplay from the browser driver (dodge's SmokeQuit
        // gate reads an env var, which Kotlin/Wasm cannot; the driver calls new_game
        // through this handle instead, mirroring bunnymark's method-call driving).
        this.dodgeMainHandle = handle;
      }
      if (this.mode === "dodge" && scriptName.endsWith(".SmokeQuit")) {
        // The driver calls SmokeQuit.smoke_teardown (method#1) through this handle to
        // quit the SceneTree and drain live handles to zero for the teardown assertion.
        this.dodgeSmokeQuitHandle = handle;
      }
      if (this.mode === "web3d" && scriptName.endsWith(".Main")) this.web3dMainHandle = handle;
      if (this.mode === "web3d" && scriptName.endsWith(".SmokeQuit")) {
        // The driver calls SmokeQuit.smoke_teardown (method#1) to free the scene root and
        // drain live handles to zero for the render smoke's teardown assertion.
        this.web3dSmokeQuitHandle = handle;
      }
      if (this.mode === "platformer" && scriptName.endsWith(".Main")) {
        this.platformerMainHandle = handle;
      }
      if (this.mode === "platformer" && scriptName.endsWith(".SmokeQuit")) {
        this.platformerSmokeQuitHandle = handle;
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
      if (this.mode === "squash" && scriptName.endsWith(".SmokeQuit")) {
        // The driver calls SmokeQuit.smoke_teardown (method#1) to free the scene root and
        // drain live handles to zero for the teardown assertion.
        this.squashSmokeQuitHandle = handle;
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
      if (this.mode === "fps" && scriptName.endsWith(".Smoke")) {
        // The scene-root Smoke script's smoke_teardown (method#1) frees the Audio autoload
        // and the scene root, draining live handles to zero.
        this.fpsSmokeHandle = handle;
      }
      if (this.mode === "fps" && scriptName.endsWith(".Player")) {
        this.fpsPlayerHandle = handle;
      }
      if (this.mode === "charactercontroller" && scriptName.endsWith(".SmokeQuit")) {
        // The driver calls SmokeQuit.smoke_teardown (method#1) to free the Events autoload
        // and the scene root, draining live handles to zero.
        this.charSmokeQuitHandle = handle;
      }
      if (this.mode === "charactercontroller" && scriptName.endsWith(".Player3DTemplate")) {
        this.charPlayerHandle = handle;
      }
      if (this.mode === "thirdperson" && scriptName.endsWith(".SmokeQuit")) {
        // smoke_resume (method#1) presses through the boot pause; smoke_teardown (method#2)
        // frees the scene root and drains live handles to zero.
        this.tpSmokeQuitHandle = handle;
      }
      if (this.mode === "thirdperson" && scriptName.endsWith(".Player")) {
        this.tpPlayerHandle = handle;
      }
      if (this.mode === "thirdperson" && scriptName.endsWith(".DemoPage")) {
        this.tpDemoPageHandle = handle;
      }
      if (this.mode === "racing" && scriptName.endsWith(".Smoke")) {
        // smoke_teardown (method#1) frees the scene root for the teardown assertion.
        this.racingSmokeHandle = handle;
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
        // The scene root pumps the frame scheduler and hosts the harness entry points:
        // smoke_start_game (method#1) presses Play, smoke_teardown (method#2) releases the
        // cached scenes plus the settings ConfigFile and frees the root.
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
      if (this.mode === "citybuilder" && scriptName.endsWith(".Smoke")) {
        // smoke_teardown (method#1) frees the Audio autoload, releases hydrated structure
        // assets, and frees the scene root for the teardown assertion.
        this.cbSmokeHandle = handle;
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

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
  const KANAMA_WEB_PROTOCOL_VERSION = 6;

  function commandWordCount(opcode) {
    if (opcode === 5 || opcode === 15) return 2;
    if (
      opcode === 14 ||
      opcode === 16 ||
      opcode === 46 ||
      opcode === 47 ||
      opcode === 52
    ) return 3;
    if (
      opcode === 3 ||
      opcode === 30 ||
      opcode === 43 ||
      opcode === 48 ||
      opcode === 49 ||
      opcode === 50 ||
      opcode === 100
    ) return 4;
    if (opcode === 13) return 5;
    if (opcode === 32) return 6;
    if (opcode === 6) return 9;
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

  const bridge = {
    api: null,
    protocolVersion: KANAMA_WEB_PROTOCOL_VERSION,
    mode: globalThis.KanamaWebMode ?? "spike",
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
    signalVector2iCallbacks: new Map(),
    tweenCallbacks: new Map(),
    handleOwners: new Map(),
    browserNodeHandlesByScript: new Map(),
    tweenChildren: new Map(),
    sceneTreeHandlesByOwner: new Map(),
    commandStringNamesByValue: new Map(),
    commandStringNamesById: new Map(),
    nextCommandStringNameId: 1,
    activeCommandFlushFrame: null,
    activeOwnerHandle: 0,
    benchmarkCallback: null,
    firstHandle: 0,
    freedHandle: 0,
    readyCount: 0,
    immediateResult: null,
    immediateChildCountResult: null,
    immediateResourceHandleResult: null,
    immediateSignalResult: null,
    immediateResourceReleaseResult: null,
    immediateConstructHandleResult: null,
    immediateLongResult: null,
    immediateVector2Result: null,
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
    noArgCalls: 0,
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
    match3DeferredReadyByClass: {},
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
    match3FramePumps: 0,
    match3FrameContinuations: 0,
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
      if (handle > 0) this.activeOwnerHandle = this.ownerForHandle(handle);
      try {
        return action();
      } catch (error) {
        const detail = error?.stack ?? error?.message ?? String(error);
        const contextual = new Error(
          `Kanama Web boundary failure: handle=${handle} callback=${callback} member=${member}\n${detail}`,
        );
        this.callbackErrors += 1;
        this.lastCallbackError = contextual.message;
        globalThis.failKanamaWeb(contextual);
        return fallback;
      } finally {
        this.activeOwnerHandle = previousOwner;
      }
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
    frame(handle, delta) {
      if (this.mode === "bunnymark") {
        return this.process(handle, delta);
      }
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
        if (handle !== this.match3MainHandle) return 0;
        this.match3FramePumps += 1;
        const executed = this.invoke(
          handle,
          "frame_scheduler",
          "frame scheduler",
          () => this.api.kanamaWebFrame(handle, delta),
          0,
        );
        this.match3FrameContinuations += executed;
        this.processCalls += 1;
        return executed;
      }
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
      this.processCalls += 1;
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
    recordDeferredReady(scriptName) {
      this.match3DeferredReadyByClass[scriptName] =
        (this.match3DeferredReadyByClass[scriptName] ?? 0) + 1;
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
    bunnymarkMethodId(method) {
      const spriteVariant = this.bunnymarkVariant === "BunnymarkV1Sprites";
      if (method === "add") return spriteVariant ? 1 : 2;
      if (method === "remove") return spriteVariant ? 2 : 3;
      if (method === "finish") return spriteVariant ? 3 : 4;
      throw new Error(`Unknown Bunnymark method=${method}`);
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
        this.releaseBrowserHandlesOwnedBy(handle);
      }
      return result;
    },
    installProxyCallbacks(handle, apply, immediate, resource, signal, release, construct, nodeLookup, packedScene, noArgsObject, inputCursor, connect, objectQuery, noArgsVector2, signalVector2i, tween) {
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
      this.handleOwners.delete(handle);
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
    releaseRemainingBrowserHandles() {
      for (let slotIndex = 1; slotIndex < this.browserHandleSlots.length; slotIndex += 1) {
        const slot = this.browserHandleSlots[slotIndex];
        if (!slot.live) continue;
        if (slot.kind === "Sprite2D") this.objectFrees += 1;
        for (const [handle, owner] of this.handleOwners) {
          if ((handle & BROWSER_HANDLE_SLOT_MASK) !== slotIndex) continue;
          if (slot.kind === "AudioStreamPlayer") {
            this.audioPlayerStates.delete(handle);
            this.match3AudioPlayerFrees += 1;
          }
          if (slot.kind === "Resource") this.resourcePathByHandle.delete(handle);
          if (slot.kind === "SceneTree" && this.sceneTreeHandlesByOwner.get(owner) === handle) {
            this.sceneTreeHandlesByOwner.delete(owner);
          }
          this.handleOwners.delete(handle);
        }
        slot.live = false;
        slot.kind = null;
        this.freeBrowserHandleSlots.push(slotIndex);
      }
      this.liveBrowserHandleCount = 0;
      this.browserNodeHandlesByScript.clear();
      this.tweenChildren.clear();
      this.sceneTreeHandlesByOwner.clear();
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
    refreshNode2DSnapshot(handle, positionX, positionY, scaleX, scaleY, r, g, b, a) {
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
      );
    },
    refreshViewportRectSnapshot(handle, x, y, width, height) {
      return this.api.kanamaWebLoadViewportRectSnapshot(handle, x, y, width, height);
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
        throw new Error("Godot resource callback published an invalid handle");
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
      const proposedHandle = this.allocateBrowserHandle("Node", owner);
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
      const owner = this.ownerForHandle(handle);
      const isSceneTree = opcode === 51;
      if (isSceneTree) {
        const existing = this.sceneTreeHandlesByOwner.get(owner);
        if (existing !== undefined && this.browserHandleSlot(existing)?.kind === "SceneTree") {
          this.match3SceneTreeHandleReuses += 1;
          return existing;
        }
        this.sceneTreeHandlesByOwner.delete(owner);
      }
      const callback = this.callbackFor(
        this.noArgsObjectCallbacks,
        handle,
        "Godot no-args object",
      );
      const isTween = opcode === 36;
      const kind = isTween ? "Tween" : isSceneTree ? "SceneTree" : "Node";
      const resultHandle = this.allocateBrowserHandle(kind, owner);
      if (isTween || isSceneTree) this.api.kanamaWebAdoptObjectHandle(resultHandle);
      else this.api.kanamaWebAdoptNodeHandle(resultHandle);
      this.immediateObjectHandleResult = null;
      callback(opcode, handle, resultHandle);
      const result = this.immediateObjectHandleResult;
      if (result !== 0 && result !== resultHandle) {
        throw new Error("Godot no-args object callback published an invalid handle");
      }
      if (result === 0) {
        if (isTween || isSceneTree) this.api.kanamaWebDiscardBrowserHandle(resultHandle);
        else this.api.kanamaWebDiscardNodeHandle(resultHandle);
        this.releaseBrowserHandle(resultHandle, kind);
      } else if (isTween) {
        this.tweenChildren.set(resultHandle, new Set());
        if (this.mode === "match3") this.match3TweensCreated += 1;
      } else if (isSceneTree) {
        this.sceneTreeHandlesByOwner.set(owner, resultHandle);
        this.match3SceneTreeHandlesCreated += 1;
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
      const callback = this.callbackFor(this.connectCallbacks, targetHandle, "Godot connect");
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
      const callback = this.callbackFor(this.connectCallbacks, targetHandle, "Godot bound connect");
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
    immediateObjectQuery(opcode, handle, value) {
      const callback = this.callbackFor(this.objectQueryCallbacks, handle, "Godot object query");
      this.immediateLongResult = null;
      callback(opcode, handle, value);
      if (!Number.isInteger(this.immediateLongResult)) {
        throw new Error("Godot object query callback did not publish an integer result");
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
          callback(words.subarray(groupStart, groupStart + groupWords), groupCommands);
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
      this.match3ReadyByClass[scriptName] = (this.match3ReadyByClass[scriptName] ?? 0) + 1;
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

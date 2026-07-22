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
  const KANAMA_WEB_PROTOCOL_VERSION = 2;

  function commandWordCount(opcode) {
    if (opcode === 5 || opcode === 15) return 2;
    if (opcode === 14 || opcode === 16) return 3;
    if (opcode === 3 || opcode === 100) return 4;
    if (opcode === 13) return 5;
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
    handleOwners: new Map(),
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
    match3CursorSets: 0,
    match3Connections: 0,
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

    create(scriptId) {
      const handle = this.invoke(0, "create", `script#${scriptId}`, () => this.api.kanamaWebCreate(scriptId), 0);
      return handle;
    },
    ready(handle) {
      return this.invoke(handle, "_ready", "_ready", () => this.api.kanamaWebReady(handle), 0);
    },
    frame(handle, delta) {
      if (this.mode === "bunnymark") {
        return this.process(handle, delta);
      }
      if (this.mode === "match3") {
        // Task 57e group 1 deliberately leaves frame coroutines queued for group 6. Do not run
        // the transport spike's synthetic per-frame mutation against real Match3 script proxies.
        return 0;
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
      return this.mode === "match3" && scriptName.endsWith(".Audio");
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
      const result = this.invoke(
        handle,
        "_exit_tree",
        "_exit_tree",
        () => this.api.kanamaWebFree(handle),
        0,
      );
      if (result === 1) this.releaseBrowserHandlesOwnedBy(handle);
      return result;
    },
    installProxyCallbacks(handle, apply, immediate, resource, signal, release, construct, nodeLookup, packedScene, noArgsObject, inputCursor, connect) {
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
      this.handleOwners.delete(handle);
    },
    ownerForHandle(handle) {
      const owner = this.handleOwners.get(handle);
      if (!owner) throw new Error(`No Kanama Web proxy owns handle=${handle}`);
      return owner;
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
        slot.live = false;
        slot.kind = null;
        for (const [handle, owner] of this.handleOwners) {
          if ((handle & BROWSER_HANDLE_SLOT_MASK) === slotIndex) this.handleOwners.delete(handle);
        }
        this.freeBrowserHandleSlots.push(slotIndex);
      }
      this.liveBrowserHandleCount = 0;
    },
    releaseBrowserHandlesOwnedBy(owner) {
      for (const [handle, handleOwner] of [...this.handleOwners]) {
        if (handleOwner !== owner || (handle & BROWSER_HANDLE_NAMESPACE) === 0) continue;
        const slot = this.browserHandleSlot(handle);
        if (!slot) continue;
        if (slot.kind === "Sprite2D") this.objectFrees += 1;
        this.api.kanamaWebDiscardBrowserHandle(handle);
        slot.live = false;
        slot.kind = null;
        this.handleOwners.delete(handle);
        this.freeBrowserHandleSlots.push(handle & BROWSER_HANDLE_SLOT_MASK);
        this.liveBrowserHandleCount -= 1;
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
    refreshViewportRectSnapshot(handle, x, y, width, height) {
      return this.api.kanamaWebLoadViewportRectSnapshot(handle, x, y, width, height);
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
        this.releaseBrowserHandle(resourceHandle, "Resource");
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
        throw new Error("Godot node lookup callback published an invalid handle");
      }
      if (result === 0) {
        this.api.kanamaWebDiscardNodeHandle(resultHandle);
        this.releaseBrowserHandle(resultHandle, "Node");
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
      const callback = this.callbackFor(
        this.noArgsObjectCallbacks,
        handle,
        "Godot no-args object",
      );
      const resultHandle = this.allocateBrowserHandle("Node", this.ownerForHandle(handle));
      this.api.kanamaWebAdoptNodeHandle(resultHandle);
      this.immediateObjectHandleResult = null;
      callback(opcode, handle, resultHandle);
      const result = this.immediateObjectHandleResult;
      if (result !== 0 && result !== resultHandle) {
        throw new Error("Godot no-args object callback published an invalid handle");
      }
      if (result === 0) {
        this.api.kanamaWebDiscardNodeHandle(resultHandle);
        this.releaseBrowserHandle(resultHandle, "Node");
      }
      return result;
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
      if (this.immediateConnectResult === 0) this.match3Connections += 1;
      return this.immediateConnectResult;
    },
    recordImmediateObjectHandle(value) {
      this.immediateObjectHandleResult = value;
    },
    recordImmediateConnectResult(value) {
      this.immediateConnectResult = value;
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
      const appliedBefore = this.appliedCommands;
      let groupStart = 0;
      let groupWords = 0;
      let groupCommands = 0;
      let groupOwner = 0;
      let groupCrossings = 0;
      let scanOffset = 0;
      const flushGroup = () => {
        if (groupCommands === 0) return;
        const callback = this.applyCallbacks.get(groupOwner);
        if (!callback) {
          throw new Error(`Godot command callback is not installed for owner=${groupOwner}`);
        }
        callback(words.subarray(groupStart, groupStart + groupWords), groupCommands);
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
      const applied = this.appliedCommands - appliedBefore;
      this.kotlinToGodotCalls += groupCrossings;
      this.kotlinToGodotMs.push(performance.now() - started);
      let wordOffset = 0;
      let positionMutationCount = 0;
      for (let commandIndex = 0; commandIndex < commandCount; commandIndex += 1) {
        const opcode = words[wordOffset];
        if (commandIndex < applied && opcode === 13) this.match3AddChildCommands += 1;
        if (commandIndex < applied && opcode === 16) this.match3TextureAssignments += 1;
        if (commandIndex < applied && opcode === 3) this.match3PositionMutations += 1;
        if (commandIndex < applied && opcode === 3) positionMutationCount += 1;
        if (commandIndex < applied && opcode === 15) {
          this.lastFreedObjectHandle = words[wordOffset + 1];
          const slot = this.browserHandleSlot(this.lastFreedObjectHandle);
          if (slot) this.releaseBrowserHandle(this.lastFreedObjectHandle, slot.kind);
          this.objectFrees += 1;
        }
        wordOffset += commandWordCount(opcode);
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
        if (scriptName.endsWith(".Main")) this.finishMatch3Group1(handle, scriptId, scriptName);
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
        audioGroupExplicitlyPending:
          Object.entries(snapshot.deferredSubsystemReady).some(
            ([name, count]) => name.endsWith(".Audio") && count === 1,
          ),
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
      this.lastAppliedValue = lastValue;
    },
    recordFreed(handle) {
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

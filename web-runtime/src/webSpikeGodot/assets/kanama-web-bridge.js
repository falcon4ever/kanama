(() => {
  "use strict";

  const WARMUP_FRAMES = 30;
  const SAMPLE_FRAMES = 120;
  const OPERATIONS = 10_000;
  const BENCHMARK_WARMUP_TRIALS = 5;
  const INDIVIDUAL_WARMUP_TRIALS = 1;

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
    mode: globalThis.KanamaWebMode ?? "spike",
    previewBunnies: requestedPreviewBunnies(),
    previewScheduled: false,
    applyCallback: null,
    immediateCallback: null,
    resourceCallback: null,
    signalCallback: null,
    resourceReleaseCallback: null,
    benchmarkCallback: null,
    firstHandle: 0,
    freedHandle: 0,
    readyCount: 0,
    immediateResult: null,
    immediateChildCountResult: null,
    immediateResourceHandleResult: null,
    immediateSignalResult: null,
    immediateResourceReleaseResult: null,
    nextResourceHandle: 0x40000001,
    resourceLoads: 0,
    resourceReleases: 0,
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
    kotlinToGodotMs: [],
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
      }
    },

    create(scriptId) {
      return this.invoke(0, "create", `script#${scriptId}`, () => this.api.kanamaWebCreate(scriptId), 0);
    },
    ready(handle) {
      return this.invoke(handle, "_ready", "_ready", () => this.api.kanamaWebReady(handle), 0);
    },
    frame(handle, delta) {
      if (this.mode === "bunnymark") {
        return this.process(handle, delta);
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
      return this.invoke(
        handle,
        "_process",
        "_process",
        () => this.api.kanamaWebProcess(handle, delta),
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
        if (methodId === 2) this.addBunnyCalls += 1;
        if (methodId === 3) this.removeBunnyCalls += 1;
        if (methodId === 4) this.finishCalls += 1;
      }
      return this.invoke(
        handle,
        "registered_function",
        `method#${methodId}`,
        () => this.api.kanamaWebCallNoArgs(handle, methodId),
        0,
      );
    },
    roundTrip(value) {
      return this.api.kanamaWebRoundTrip(value);
    },
    free(handle) {
      return this.invoke(handle, "_exit_tree", "_exit_tree", () => this.api.kanamaWebFree(handle), 0);
    },
    installApplyCallback(callback) {
      this.applyCallback = callback;
    },
    clearApplyCallback() {
      this.applyCallback = null;
    },
    installImmediateCallback(callback) {
      this.immediateCallback = callback;
    },
    clearImmediateCallback() {
      this.immediateCallback = null;
    },
    installResourceCallback(callback) {
      this.resourceCallback = callback;
    },
    clearResourceCallback() {
      this.resourceCallback = null;
    },
    installSignalCallback(callback) {
      this.signalCallback = callback;
    },
    clearSignalCallback() {
      this.signalCallback = null;
    },
    installResourceReleaseCallback(callback) {
      this.resourceReleaseCallback = callback;
    },
    clearResourceReleaseCallback() {
      this.resourceReleaseCallback = null;
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
      if (!this.immediateCallback) throw new Error("Godot immediate callback is not installed");
      this.immediateCalls += 1;
      this.immediateChildCountResult = null;
      this.immediateCallback(handle, includeInternal);
      if (!Number.isInteger(this.immediateChildCountResult)) {
        throw new Error("Godot immediate callback did not publish a child count");
      }
      return this.immediateChildCountResult;
    },
    immediateResourceLoad(path, typeHint, cacheMode) {
      if (!this.resourceCallback) throw new Error("Godot resource callback is not installed");
      if (this.nextResourceHandle > 0x7fffffff) {
        throw new Error("Kanama Web resource handle namespace exhausted");
      }
      const resourceHandle = this.nextResourceHandle;
      this.nextResourceHandle += 1;
      this.immediateResourceHandleResult = null;
      this.resourceCallback(resourceHandle, path, typeHint, cacheMode);
      if (
        this.immediateResourceHandleResult !== 0 &&
        this.immediateResourceHandleResult !== resourceHandle
      ) {
        throw new Error("Godot resource callback published an invalid handle");
      }
      this.resourceLoads += 1;
      return this.immediateResourceHandleResult;
    },
    immediateEmitSignal(handle, name, value) {
      if (!this.signalCallback) throw new Error("Godot signal callback is not installed");
      this.lastSignalName = name;
      this.lastSignalValue = value;
      this.immediateSignalResult = null;
      this.signalCallback(handle, name, value);
      if (!Number.isInteger(this.immediateSignalResult)) {
        throw new Error("Godot signal callback did not publish a result");
      }
      return this.immediateSignalResult;
    },
    releaseResource(handle) {
      if (!this.resourceReleaseCallback) {
        throw new Error("Godot resource release callback is not installed");
      }
      this.immediateResourceReleaseResult = null;
      this.resourceReleaseCallback(handle);
      if (!Number.isInteger(this.immediateResourceReleaseResult)) {
        throw new Error("Godot resource release callback did not publish a result");
      }
      return this.immediateResourceReleaseResult;
    },
    installBenchmarkCallback(callback) {
      this.benchmarkCallback = callback;
      this.maybeRunBenchmarks();
    },
    clearBenchmarkCallback() {
      this.benchmarkCallback = null;
    },
    flushCommands(words, wordCount, commandCount) {
      if (!this.applyCallback) throw new Error("Godot command callback is not installed");
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
      const applied = this.applyCallback(words.subarray(0, wordCount), commandCount);
      this.kotlinToGodotCalls += 1;
      this.kotlinToGodotMs.push(performance.now() - started);
      return applied;
    },
    recordReady(handle) {
      this.readyCount += 1;
      if (this.mode === "bunnymark" && this.previewBunnies > 0 && !this.previewScheduled) {
        this.previewScheduled = true;
        setTimeout(() => {
          if (this.api.kanamaWebIsLive(handle) !== 1) return;
          for (let index = 0; index < this.previewBunnies; index += 1) {
            this.callNoArgs(handle, 2);
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
        firstDrawPosition: this.firstDrawPosition,
        lastDrawPosition: this.lastDrawPosition,
        commandBufferGrowths: this.commandBufferGrowths,
      };
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
        protocol: this.results.protocolVersion === 1,
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
    if (protocolVersion !== 1) {
      throw new Error(`Kanama Web protocol mismatch: expected 1, received ${protocolVersion}`);
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

import { collecting, resolveMethodId } from "../envelope.mjs";
// demos/starter.mjs -- the user-project cell: the starter project a new game begins from, exported
// with `-PkanamaWebProjectDir` (the path a game developer runs), not a demo.
//
// The starter's one script, HelloScript, spins its Node2D every frame and writes a Status label.
// This driver waits for its _ready, watches _process dispatch for a while, calls the registered
// `greet(name)` through the bridge and checks the String it returns, then quits the engine the way
// closing the tab would (the starter has no teardown script of its own) and polls the live-handle
// count to zero, collecting garbage before every poll.

const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
const DEBUG = process.env.KANAMA_WEB_SMOKE_DEBUG === "1";
const START = Date.now();
const trace = (msg) => {
  if (DEBUG) process.stderr.write(`[starter ${((Date.now() - START) / 1000).toFixed(1)}s] ${msg}\n`);
};

const SCRIPT = "HelloScript";
// Frames of _process the run must see dispatch after _ready (about one second at 60 Hz).
const MIN_PROCESS_DISPATCHES = 60;

async function snapshot(evaluate) {
  try {
    return await evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      if (!bridge) return null;
      const byClass = (table) => {
        const entry = Object.entries(table ?? {}).find(([name]) => name.endsWith(".${SCRIPT}"));
        return entry?.[1] ?? 0;
      };
      const handleEntry = Object.entries(bridge.scriptNameByHandle ?? {}).find(([, name]) =>
        String(name).endsWith(".${SCRIPT}"),
      );
      const exercised = Object.entries(bridge.exercisedMembers ?? {}).find(([name]) => name.endsWith(".${SCRIPT}"));
      return {
        mode: bridge.mode,
        protocol: bridge.results?.protocolVersion ?? bridge.protocolVersion ?? 0,
        helloHandle: handleEntry ? Number(handleEntry[0]) : 0,
        helloReady: byClass(bridge.match3ReadyByClass),
        helloLive: byClass(bridge.liveScriptsByClass),
        processDispatches: exercised?.[1]?._process ?? 0,
        appliedCommands: bridge.appliedCommands,
        liveHandles: bridge.liveBrowserHandleCount,
        maxLiveHandles: bridge.maxLiveBrowserHandles,
        crossings: bridge.kotlinToGodotCalls,
        callbackErrors: bridge.callbackErrors,
        scriptErrors: bridge.scriptErrors ?? 0,
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

async function pollUntil(read, deadline, windowMs, predicate) {
  let last = null;
  const until = Math.min(deadline, Date.now() + windowMs);
  while (Date.now() < until) {
    const snap = await read();
    if (snap) {
      last = snap;
      trace(
        `ready=${snap.helloReady} process=${snap.processDispatches} applied=${snap.appliedCommands} ` +
          `live=${snap.liveHandles} errs=${snap.callbackErrors}`,
      );
      if (predicate(snap)) return { snap, met: true };
    }
    await delay(150);
  }
  return { snap: last, met: false };
}

export async function runStarter({ url, evaluate, navigate, deadline, exportDir }) {
  const greetId = resolveMethodId(exportDir, SCRIPT, "greet");
  if (!greetId) throw new Error(`starter: ${SCRIPT}.greet is not in the export manifest`);

  const startupStart = Date.now();
  trace("navigate");
  await navigate(`${url}?starter=${Date.now()}`);

  const started = await pollUntil(
    () => snapshot(evaluate),
    deadline,
    60_000,
    (snap) => snap.protocol > 0 && snap.helloReady >= 1 && snap.helloHandle > 0,
  );
  if (!started.met) {
    // The page's own failure line (failKanamaWeb writes it under the canvas): a protocol mismatch, a
    // module whose main() did not start the runtime, a bundle that did not load.
    let pageFailure = null;
    try {
      pageFailure = await evaluate(
        `document.body.dataset.status === "fail" ? (document.querySelector("#kanama-results")?.textContent ?? "") : null`,
      );
    } catch {
      // the page is mid-navigation or gone; the error below still says what did not happen
    }
    const reason = pageFailure ? `: the page failed with ${String(pageFailure).split("\n")[0]}` : "";
    throw new Error(`Kotlin/Wasm ${SCRIPT} did not become ready${reason}`);
  }
  const ready = started.snap;
  const startupDurationMs = Date.now() - startupStart;
  trace(`ready: handle=${ready.helloHandle} protocol=${ready.protocol}`);

  const running = await pollUntil(
    () => snapshot(evaluate),
    deadline,
    30_000,
    (snap) => snap.processDispatches >= ready.processDispatches + MIN_PROCESS_DISPATCHES,
  );
  const atPeak = running.snap ?? ready;

  const greeting = await evaluate(
    `String(globalThis.KanamaWebBridge.callPackedArgs(${ready.helloHandle}, ${greetId}, "web"))`,
  );
  trace(`greet("web") -> ${JSON.stringify(greeting)}`);
  const afterGreet = (await snapshot(evaluate)) ?? atPeak;

  // Quit the engine as closing the tab would: the scene tree frees its nodes and every live handle
  // goes. The polls collect garbage first, so a dropped wrapper's hold cannot linger on a host whose
  // collector stays quiet.
  trace("requestQuit");
  await evaluate("globalThis.KanamaWebEngine.requestQuit(); true");
  const takeCollected = () => snapshot(collecting(evaluate));
  const quit = await pollUntil(takeCollected, deadline, 10_000, (snap) => snap.liveHandles === 0 && snap.helloLive === 0);
  const settled = quit.snap ?? afterGreet;
  trace(`teardown: live=${settled.liveHandles} helloLive=${settled.helloLive} errs=${settled.callbackErrors}`);

  const peakErrors = Math.max(atPeak.callbackErrors, afterGreet.callbackErrors, settled.callbackErrors);
  const checks = {
    modeGame: ready.mode === "game",
    helloScriptReady: ready.helloReady === 1,
    processDispatching: running.met,
    // The label write every frame reaches Godot as applied commands.
    commandsApplied: atPeak.appliedCommands > ready.appliedCommands,
    greetReturnsString: greeting === "Hello from Kanama, web",
    fullTeardownToZero: settled.liveHandles === 0 && settled.helloLive === 0,
    noCallbackFaults: peakErrors === 0 && settled.scriptErrors === 0 && settled.failure === null,
  };
  const boundaryErrors = [];
  if (peakErrors !== 0) boundaryErrors.push(`callbackErrors=${peakErrors}`);
  if (settled.scriptErrors !== 0) boundaryErrors.push(`scriptErrors=${settled.scriptErrors}`);
  if (settled.failure !== null) boundaryErrors.push(`failure: ${settled.failure}`);

  return {
    protocolVersion: ready.protocol,
    startup: {
      loaded: true,
      outcome: "ready",
      durationMs: startupDurationMs,
    },
    checks,
    handles: {
      liveAfterGameplay: atPeak.maxLiveHandles,
      liveAfterTeardown: settled.liveHandles,
      staleRejected: 0,
    },
    crossings: {
      kotlinToGodotCalls: afterGreet.crossings,
      appliedCommands: afterGreet.appliedCommands,
      processDispatches: afterGreet.processDispatches,
    },
    callbacks: {
      pendingSignalCallbacks: settled.callbacks,
    },
    connections: {
      afterGameplayLiveHandles: atPeak.liveHandles,
    },
    scheduler: {
      pendingCoroutines: settled.pending,
      registeredJobs: settled.jobs,
    },
    teardown: {
      outcome: checks.fullTeardownToZero && settled.failure === null ? "clean" : "incomplete",
      ownerRegistriesToBaseline:
        settled.liveHandles === 0 && settled.callbacks === 0 && settled.pending === 0 && settled.jobs === 0,
    },
    boundaryErrors,
  };
}

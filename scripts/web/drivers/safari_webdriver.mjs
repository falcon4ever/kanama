// safari_webdriver.mjs -- Safari production Web export-smoke driver (Task 57f).
//
// Drives Safari over classic W3C WebDriver via `safaridriver`. Safari is an
// explicit LOCAL release gate (Chrome is the CI gate). It requires a one-time
// `safaridriver --enable` and "Allow Remote Automation" in Safari's Develop menu.
//
// SafariDriver exposes no browser-log endpoint, so console errors cannot be
// collected here; the gate instead asserts bridge callback/telemetry via the
// demo module's boundaryErrors plus every gameplay and teardown invariant.
//
// Args match the other drivers: --url --result --demo --timeout --source-checksum
// --export-dir [--browser-binary(unused)].

import { execFileSync, spawn } from "node:child_process";
import fs from "node:fs";

import { runMatch3 } from "./demos/match3.mjs";
import { runBunnymark } from "./demos/bunnymark.mjs";
import { runDodge } from "./demos/dodge.mjs";
import { runWeb3d } from "./demos/web3d.mjs";
import { runPlatformer } from "./demos/platformer.mjs";
import { runSquash } from "./demos/squash.mjs";
import { runFps } from "./demos/fps.mjs";
import { runCharactercontroller } from "./demos/charactercontroller.mjs";
import { runThirdperson } from "./demos/thirdperson.mjs";
import { runRacing } from "./demos/racing.mjs";
import { runCitybuilder } from "./demos/citybuilder.mjs";
import { runTpsdemo } from "./demos/tpsdemo.mjs";
import { runSoak } from "./demos/soak.mjs";
import { runVisibilityprobe } from "./demos/visibilityprobe.mjs";
import { runSpike } from "./demos/spike.mjs";
import {
  buildEnvelope,
  collectExercisedMembers,
  collectPayload,
  collectPerformance,
  mergeExercisedMembers,
} from "./envelope.mjs";

const DEMOS = { match3: runMatch3, bunnymark: runBunnymark, dodge: runDodge, web3d: runWeb3d, platformer: runPlatformer, squash: runSquash, fps: runFps, charactercontroller: runCharactercontroller, thirdperson: runThirdperson, racing: runRacing, citybuilder: runCitybuilder, tpsdemo: runTpsdemo, soak: runSoak, visibilityprobe: runVisibilityprobe, spike: runSpike };
const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));

function parseArgs(argv) {
  const args = {};
  for (let i = 0; i < argv.length; i += 2) {
    if (!argv[i].startsWith("--")) throw new Error(`unexpected argument: ${argv[i]}`);
    args[argv[i].slice(2)] = argv[i + 1];
  }
  for (const req of ["url", "result", "demo", "timeout", "source-checksum", "export-dir"]) {
    if (!args[req]) throw new Error(`safari_webdriver: missing --${req}`);
  }
  return args;
}

async function main() {
  const args = parseArgs(process.argv.slice(2));
  const deadline = Date.now() + Number(args.timeout) * 1000;
  const startedAt = Date.now();

  const runDemo = DEMOS[args.demo];
  if (!runDemo) throw new Error(`safari_webdriver: unknown demo '${args.demo}'`);

  // Killing safaridriver does NOT reap the Safari it drives: the automation
  // Safari is not its child, so it survives and keeps its window, its port and
  // its share of the GPU. Leftovers measurably degrade later runs (a Match3
  // board that settles in 1s against a clean machine took over 400s with
  // orphans present), which reads exactly like a flaky gate. Safari also has no
  // headless mode, so there is no run without a window to leak.
  //
  // Attribute by difference: whatever automation Safari appears after we spawn
  // safaridriver is ours. A concurrent Safari gate starting inside this window
  // could be misattributed, so the drivers are not safe to run two-at-a-time on
  // one machine -- which is already true of a gate that needs an unoccluded
  // window.
  // Task 89: Safari has no headless mode, so the gate needs a REAL window that is
  // visible and focused -- and when it is not, nothing says so. Safari suspends
  // requestAnimationFrame for a non-visible page, so Godot's main loop advances a
  // handful of frames and stops; the run then fails deep inside a demo ("board was
  // not settled") with no console error, no boundary error and no failing check to
  // point at the cause. A locked screen is the common way to get there: an unattended
  // spot-check that idles into the lock screen produces a full corpus of confusing,
  // demo-specific failures that look exactly like a code regression.
  //
  // Worse than the noise is the other direction: a demo whose checks complete inside
  // the few frames that DO run could pass while the engine is effectively dead. So
  // this is refused up front, not reported afterwards.
  //
  // macOS omits CGSSessionScreenIsLocked entirely when unlocked, and sets it to Yes
  // when locked -- so the key's PRESENCE with Yes is the signal, not its absence.
  const screenIsLocked = () => {
    try {
      const consoleUsers = execFileSync("ioreg", ["-n", "Root", "-d1"], { encoding: "utf8" });
      return /"CGSSessionScreenIsLocked"\s*=\s*Yes/.test(consoleUsers);
    } catch {
      return false; // Cannot tell -- do not block the gate on a diagnostic failing.
    }
  };
  if (screenIsLocked()) {
    throw new Error(
      "safari_webdriver: the macOS screen is LOCKED, so this run would be meaningless.\n" +
        "  Safari suspends requestAnimationFrame for a non-visible page: Godot advances a few\n" +
        "  frames and stops, and every check downstream then fails as a demo-level assertion\n" +
        "  (or passes vacuously). Unlock the screen and re-run. The Safari gate is a hand-run\n" +
        "  spot-check by design -- it needs a logged-in GUI session with a visible window.",
    );
  }

  const automationSafariPids = () => {
    try {
      return execFileSync("pgrep", ["-f", "Safari.*--automation"], { encoding: "utf8" })
        .trim().split("\n").filter(Boolean);
    } catch {
      return []; // pgrep exits non-zero when nothing matches
    }
  };
  const preexistingSafari = new Set(automationSafariPids());

  const port = 9500 + Math.floor(Math.random() * 400);
  const driver = spawn("safaridriver", ["-p", String(port)], { stdio: ["ignore", "pipe", "pipe"] });
  const base = `http://127.0.0.1:${port}`;
  let sessionId = null;

  const cleanup = () => {
    if (!driver.killed) driver.kill("SIGKILL");
    for (const pid of automationSafariPids()) {
      if (preexistingSafari.has(pid)) continue;
      try {
        process.kill(Number(pid), "SIGKILL");
      } catch {
        // already gone
      }
    }
  };
  process.on("exit", cleanup);
  // Three ways this process can end without the browser being reaped, all of
  // which have happened: a headless Firefox was found still playing demo audio
  // TWO DAYS after its run. The browser must not outlive the driver.
  //
  // 1. A signal. Node's 'exit' does NOT fire on one, and macOS has no setsid,
  //    so the smoke shell can only signal this process, never a group.
  for (const signal of ["SIGINT", "SIGTERM", "SIGHUP"]) {
    process.on(signal, () => {
      cleanup();
      process.exit(1);
    });
  }
  // 2. The parent dies without signalling us at all (the shell is SIGKILLed, or
  //    its terminal goes away). Nothing reaches this process, so watch for
  //    being reparented away from the shell that launched us and self-reap.
  const launchParentPid = process.ppid;
  const orphanWatch = setInterval(() => {
    if (process.ppid !== launchParentPid) {
      console.error("driver: parent process is gone; reaping the browser");
      cleanup();
      process.exit(1);
    }
  }, 1000);
  orphanWatch.unref();
  // 3. The driver itself hangs past its own budget (a CDP/WebDriver call that
  //    never returns leaves the browser held open). Hard stop, cleanup, exit.
  const budgetMs = Number(args.timeout) * 1000;
  const hardStop = setTimeout(() => {
    console.error(`driver: exceeded its ${args.timeout}s budget; reaping the browser`);
    cleanup();
    process.exit(1);
  }, budgetMs + 30_000);
  hardStop.unref();

  const wd = async (method, endpoint, body) => {
    const response = await fetch(`${base}${endpoint}`, {
      method,
      headers: { "Content-Type": "application/json" },
      body: body === undefined ? undefined : JSON.stringify(body),
    });
    const json = await response.json();
    if (!response.ok || json.value?.error) {
      throw new Error(`WebDriver ${method} ${endpoint} failed: ${JSON.stringify(json.value ?? json)}`);
    }
    return json.value;
  };

  try {
    // Wait for the safaridriver HTTP server, then open a session.
    while (Date.now() < deadline) {
      try {
        const value = await wd("POST", "/session", {
          capabilities: { alwaysMatch: { browserName: "safari" } },
        });
        sessionId = value.sessionId;
        break;
      } catch (error) {
        if (driver.exitCode !== null) {
          throw new Error(`safaridriver exited (${driver.exitCode})`, { cause: error });
        }
        await delay(300);
      }
    }
    if (!sessionId) throw new Error("Safari session could not be created (is Remote Automation enabled?)");

    await wd("POST", `/session/${sessionId}/window/rect`, { x: 0, y: 0, width: 1280, height: 900 });

    // W3C execute/sync returns a JSON-cloned value. eval(arguments[0]) evaluates
    // the demo's expression (or "stmt; value") uniformly and returns its value.
    const evaluate = async (expression) =>
      (await wd("POST", `/session/${sessionId}/execute/sync`, {
        script: "return eval(arguments[0]);",
        args: [expression],
      }));
    // Task 89: the lock check at startup cannot prove the window stayed usable -- an
    // occluded, minimized or backgrounded window fails the same way, and the screen can
    // lock mid-run. Two distinct conditions matter, and each has its own silent failure:
    //
    //   visibility -- a hidden page gets no rAF, so the engine stops advancing.
    //   focus      -- Safari delivers synthesized pointer input to the KEY window only,
    //                 so an unfocused window swallows the gesture entirely.
    //
    // The second is what task 89 chased for weeks as a "match3 regression". The census
    // told the real story: on a failing run the demo's input members are simply MISSING
    // (no _input, no _input_event, no _on_tile_pressed, not even _on_mouse_entered)
    // while the board is perfectly healthy -- tweens balanced 33/33, callbacks at 12,
    // zero console errors. The gesture never arrived. The run then failed as "the match
    // did not complete", naming the symptom and never the cause.
    const focusOwnWindow = async () => {
      const handle = await wd("GET", `/session/${sessionId}/window`);
      await wd("POST", `/session/${sessionId}/window`, { handle });
    };
    const interactiveState = () =>
      evaluate("({ visibility: document.visibilityState, focused: document.hasFocus() })");
    const assertInteractive = async (label) => {
      let state = await interactiveState();
      if (process.env.KANAMA_WEB_SMOKE_DEBUG === "1") {
        process.stderr.write(`[safari interactive] ${label}: ${JSON.stringify(state)}\n`);
      }
      if (!state.focused) {
        // Recoverable on its own terms: switching to our own window handle is the W3C
        // way to make it the active top-level browsing context.
        await focusOwnWindow();
        state = await interactiveState();
      }
      if (state.visibility !== "visible" || !state.focused) {
        throw new Error(
          `safari_webdriver: the automation window is not usable at ${label} ` +
            `(visibility=${state.visibility}, focused=${state.focused}).\n` +
            "  A hidden page gets no requestAnimationFrame, so the Godot main loop stalls; an\n" +
            "  unfocused window silently discards synthesized pointer input. Either way the\n" +
            "  run's evidence would be meaningless, so it stops here rather than failing later\n" +
            "  inside a demo assertion. Keep the Safari window visible and frontmost -- do not\n" +
            "  let the screen lock, and do not run two Safari gates at once.",
        );
      }
    };

    // Task 81: a navigation resets the page's bridge, so harvest the exercised-member
    // census BEFORE leaving each page and merge across loads (see chrome_cdp.mjs).
    let exercisedAccumulator = null;
    const harvestExercisedMembers = async () => {
      exercisedAccumulator = mergeExercisedMembers(
        exercisedAccumulator,
        await collectExercisedMembers(evaluate, args["export-dir"]),
      );
      return exercisedAccumulator;
    };
    // Task 88 finding 11: Safari used to ship `consoleEvents: []` unconditionally,
    // because SafariDriver exposes no console endpoint like CDP's consoleAPICalled or
    // BiDi's log entries. An empty list is BYTE-IDENTICAL to a genuinely clean run, so
    // the envelope's zero-console-errors pass leg -- one of the four -- asserted nothing
    // on the one engine this gate exists for. A page-level JS error on Safari simply did
    // not appear in the evidence.
    //
    // Observe it in-page instead: wrap console.error and listen for `error` /
    // `unhandledrejection`, accumulating into a global the driver reads back. This is a
    // WEAKER guarantee than Chrome's and Firefox's protocol-level capture, and the code
    // says so: the hook can only be installed once navigation RETURNS, so anything
    // thrown before that is still missed. In practice the Kotlin/Wasm boot runs after
    // the load event and takes seconds, so boot and the whole gameplay run are covered
    // -- but "covered" and "guaranteed" are different words and this is the first.
    const CONSOLE_HOOK = `(() => {
      if (globalThis.__kanamaSafariConsole) return 1;
      const sink = [];
      globalThis.__kanamaSafariConsole = sink;
      const original = console.error ? console.error.bind(console) : null;
      console.error = (...parts) => {
        try { sink.push({ type: "error", text: "console.error: " + parts.map(String).join(" ") }); } catch (_) {}
        if (original) original(...parts);
      };
      addEventListener("error", (event) => {
        sink.push({ type: "error", text: "uncaught: " + (event.message || String(event.error)) });
      });
      addEventListener("unhandledrejection", (event) => {
        sink.push({ type: "error", text: "unhandled rejection: " + String(event.reason) });
      });
      return 1;
    })()`;
    let consoleAccumulator = [];
    const harvestConsole = async () => {
      try {
        const captured = await evaluate("globalThis.__kanamaSafariConsole ?? []");
        if (Array.isArray(captured)) consoleAccumulator = consoleAccumulator.concat(captured);
      } catch {
        // The page is mid-navigation or gone. Anything it captured is already merged.
      }
      return consoleAccumulator;
    };
    const navigate = async (url) => {
      await harvestExercisedMembers();
      // A navigation destroys the hook with its realm, so drain before leaving and
      // re-install after arriving -- the same shape as the exercised-member census.
      await harvestConsole();
      const result = await wd("POST", `/session/${sessionId}/url`, { url });
      await evaluate(CONSOLE_HOOK);
      await assertInteractive(`navigate(${new URL(url).pathname})`);
      return result;
    };
    const pointer = async (press, release) => {
      // Checked HERE and not only at navigate: focus can be stolen at any moment by
      // another app, and the gesture below is exactly what a stolen focus destroys.
      await assertInteractive("pointer dispatch");
      // Viewport-origin coordinates are CSS client pixels (W3C), which the demo
      // already produces. Safari requires the trailing DELETE /actions release
      // to actually finalize and dispatch the pointer sequence.
      // Discrete waypoints (not one interpolated move) so Godot reliably tracks
      // the pointer to the release position before the button release -- Main.input
      // reads getLocalMousePosition() at mouse-up, and a single move can race.
      const px = Math.round(press.x);
      const py = Math.round(press.y);
      const rx = Math.round(release.x);
      const ry = Math.round(release.y);
      const mx = Math.round((px + rx) / 2);
      const my = Math.round((py + ry) / 2);
      await wd("POST", `/session/${sessionId}/actions`, {
        actions: [
          {
            type: "pointer",
            id: "mouse",
            parameters: { pointerType: "mouse" },
            actions: [
              { type: "pointerMove", duration: 0, x: px, y: py, origin: "viewport" },
              { type: "pointerDown", button: 0 },
              { type: "pause", duration: 120 },
              { type: "pointerMove", duration: 80, x: mx, y: my, origin: "viewport" },
              { type: "pause", duration: 40 },
              { type: "pointerMove", duration: 80, x: rx, y: ry, origin: "viewport" },
              { type: "pause", duration: 200 },
              { type: "pointerUp", button: 0 },
            ],
          },
        ],
      });
      await wd("DELETE", `/session/${sessionId}/actions`);
    };

    const demoResult = await runDemo({ url: args.url, evaluate, navigate, pointer, deadline , exportDir: args["export-dir"]});

    const browserVersion = await evaluate("navigator.userAgent");
    const payload = collectPayload(args["export-dir"], args.url, args["source-checksum"]);
    const performance = await collectPerformance(evaluate);
    const exercisedMembers = await harvestExercisedMembers();

    // No SafariDriver console endpoint: rely on the demo's bridge telemetry.
    const envelope = buildEnvelope({
      demo: args.demo,
      browser: { engine: "safari", name: "Safari", version: String(browserVersion) },
      payload,
      // Task 86: this line was MISSING from 2026-07-29 (kanama#124) until today --
      // collected two lines up, never placed in the envelope, so the budget stage
      // failed every Safari run with "no performance section". Its numbers are
      // record-only until measured Safari ceilings exist (budgets.json
      // enginePolicies.safari).
      performance,
      durationMs: Date.now() - startedAt,
      // Real captured events now, not a fabricated empty list (task 88 finding 11).
      consoleEvents: await harvestConsole(),
      demoResult,
      exercisedMembers,
    });

    fs.writeFileSync(args.result, `${JSON.stringify(envelope, null, 2)}\n`);
    try {
      await wd("DELETE", `/session/${sessionId}`);
    } catch {
      // ignore
    }
    cleanup();
    process.exit(envelope.pass ? 0 : 1);
  } catch (error) {
    if (sessionId) {
      try {
        await wd("DELETE", `/session/${sessionId}`);
      } catch {
        // ignore
      }
    }
    cleanup();
    process.stderr.write(`safari_webdriver: ${error.stack ?? error}\n`);
    process.exit(2);
  }
}

main();

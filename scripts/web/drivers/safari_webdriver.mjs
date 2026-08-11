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
import { buildEnvelope, collectPayload, collectPerformance } from "./envelope.mjs";

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
        if (driver.exitCode !== null) throw new Error(`safaridriver exited (${driver.exitCode})`);
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
    const navigate = (url) => wd("POST", `/session/${sessionId}/url`, { url });
    const pointer = async (press, release) => {
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

    const demoResult = await runDemo({ url: args.url, evaluate, navigate, pointer, deadline });

    const browserVersion = await evaluate("navigator.userAgent");
    const payload = collectPayload(args["export-dir"], args.url, args["source-checksum"]);
    const performance = await collectPerformance(evaluate);

    // No SafariDriver console endpoint: rely on the demo's bridge telemetry.
    const envelope = buildEnvelope({
      demo: args.demo,
      browser: { engine: "safari", name: "Safari", version: String(browserVersion) },
      payload,
      durationMs: Date.now() - startedAt,
      consoleEvents: [],
      demoResult,
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

// firefox_bidi.mjs -- Firefox production Web export-smoke driver (Task 57f).
//
// Self-launches headless Firefox with WebDriver BiDi, drives the demo over BiDi,
// and emits the versioned v1 result envelope. Firefox is an explicit LOCAL
// release gate (Chrome is the CI gate).
//
// BiDi serializes return values, so evaluate() deserializes them back into plain
// JS -- keeping the shared demo modules (which expect plain objects) unchanged.
//
// Args match the other drivers: --url --result --demo --timeout --source-checksum
// --export-dir [--browser-binary].

import { spawn } from "node:child_process";
import fs from "node:fs";
import os from "node:os";
import path from "node:path";

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
// macOS workstation path first, then the usual Linux install paths (the CI
// matrix cell, Task 60h). Missing everywhere is a loud failure, never a silent
// substitution of some other browser.
const FIREFOX_CANDIDATES = [
  "/Applications/Firefox.app/Contents/MacOS/firefox",
  "/usr/bin/firefox",
  "/usr/local/bin/firefox",
  "/snap/bin/firefox",
];

function resolveBrowser(explicit, candidates, engine) {
  if (explicit) return explicit;
  const found = candidates.find((candidate) => fs.existsSync(candidate));
  if (found) return found;
  throw new Error(
    `${engine}: no browser found; pass --browser-binary (looked in: ${candidates.join(", ")})`,
  );
}

const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));

function parseArgs(argv) {
  const args = {};
  for (let i = 0; i < argv.length; i += 2) {
    if (!argv[i].startsWith("--")) throw new Error(`unexpected argument: ${argv[i]}`);
    args[argv[i].slice(2)] = argv[i + 1];
  }
  for (const req of ["url", "result", "demo", "timeout", "source-checksum", "export-dir"]) {
    if (!args[req]) throw new Error(`firefox_bidi: missing --${req}`);
  }
  return args;
}

// Reconstruct plain JS from a BiDi RemoteValue (with a generous object depth).
function deserialize(node) {
  if (node == null || typeof node !== "object") return node;
  switch (node.type) {
    case "undefined":
      return undefined;
    case "null":
      return null;
    case "string":
    case "boolean":
      return node.value;
    case "number":
      return typeof node.value === "string" ? Number(node.value) : node.value;
    case "bigint":
      return Number(node.value);
    case "array":
    case "set":
      return (node.value ?? []).map(deserialize);
    case "object":
    case "map":
      return Object.fromEntries(
        (node.value ?? []).map(([key, value]) => [
          typeof key === "string" ? key : deserialize(key),
          deserialize(value),
        ]),
      );
    default:
      return node.value;
  }
}

async function main() {
  const args = parseArgs(process.argv.slice(2));
  const timeoutMs = Number(args.timeout) * 1000;
  const deadline = Date.now() + timeoutMs;
  const startedAt = Date.now();

  const runDemo = DEMOS[args.demo];
  if (!runDemo) throw new Error(`firefox_bidi: unknown demo '${args.demo}'`);

  const firefoxBinary = resolveBrowser(args["browser-binary"], FIREFOX_CANDIDATES, "firefox_bidi");
  const profileDir = fs.mkdtempSync(path.join(os.tmpdir(), "kanama-firefox-"));
  // Headless Firefox needs software WebGL for Godot's Compatibility renderer.
  fs.writeFileSync(
    path.join(profileDir, "user.js"),
    [
      'user_pref("remote.active-protocols", 2);',
      'user_pref("webgl.force-enabled", true);',
      'user_pref("webgl.disabled", false);',
      'user_pref("gfx.webrender.software", true);',
      'user_pref("dom.webgl.software.render", true);',
    ].join("\n"),
  );

  const port = 9300 + Math.floor(Math.random() * 500);
  const firefox = spawn(
    firefoxBinary,
    [
      "--headless",
      "--no-remote",
      // Headless Firefox IGNORES this; the viewport is really pinned through
      // browsingContext.setViewport below. Kept because it costs nothing and is
      // what a reader expects to find next to --headless.
      "--window-size=1280,900",
      `--profile`,
      profileDir,
      `--remote-debugging-port=${port}`,
      "about:blank",
    ],
    { stdio: ["ignore", "pipe", "pipe"] },
  );

  let socket = null;
  const cleanup = () => {
    try {
      socket?.close();
    } catch {
      // ignore
    }
    if (!firefox.killed) firefox.kill("SIGKILL");
    try {
      fs.rmSync(profileDir, { recursive: true, force: true });
    } catch {
      // ignore
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

  try {
    // Poll-connect to the BiDi session endpoint until Firefox is listening.
    const endpoint = `ws://127.0.0.1:${port}/session`;
    while (Date.now() < deadline) {
      try {
        const candidate = new WebSocket(endpoint);
        await new Promise((resolve, reject) => {
          candidate.addEventListener("open", resolve, { once: true });
          candidate.addEventListener("error", reject, { once: true });
        });
        socket = candidate;
        break;
      } catch {
        if (firefox.exitCode !== null) throw new Error(`Firefox exited early (${firefox.exitCode})`);
        await delay(250);
      }
    }
    if (!socket) throw new Error("Firefox BiDi endpoint never became reachable");

    let nextId = 1;
    const pending = new Map();
    const logEntries = [];
    socket.addEventListener("message", (event) => {
      const message = JSON.parse(event.data);
      if (message.id != null) {
        const waiter = pending.get(message.id);
        if (waiter) {
          pending.delete(message.id);
          if (message.type === "error") waiter.reject(new Error(`${message.error}: ${message.message}`));
          else waiter.resolve(message.result);
        }
        return;
      }
      if (message.method === "log.entryAdded") logEntries.push(message.params);
    });
    const command = (method, params = {}) => {
      const id = nextId++;
      socket.send(JSON.stringify({ id, method, params }));
      return new Promise((resolve, reject) => pending.set(id, { resolve, reject }));
    };

    await command("session.new", { capabilities: { alwaysMatch: { acceptInsecureCerts: true } } });
    await command("session.subscribe", { events: ["log.entryAdded"] });
    const tree = await command("browsingContext.getTree", { maxDepth: 1 });
    const context = tree.contexts[0]?.context;
    if (!context) throw new Error("Firefox exposed no browsing context");

    // Pin the viewport to the same 1280x900 the Chrome and Safari drivers use.
    // Firefox IGNORES --window-size in headless mode -- it kept reporting its own
    // 1366x682 default -- and Godot sizes its viewport from the canvas, so an
    // unpinned Firefox judges "left the screen" against different geometry than
    // the other two engines. This is a gate comparing engines; the geometry has to
    // be the constant.
    await command("browsingContext.setViewport", {
      context,
      viewport: { width: 1280, height: 900 },
      devicePixelRatio: 1,
    });

    const evaluate = async (expression) => {
      const result = await command("script.evaluate", {
        expression,
        target: { context },
        awaitPromise: true,
        resultOwnership: "none",
        serializationOptions: { maxObjectDepth: 99, maxDomDepth: 0 },
        userActivation: true,
      });
      if (result.type === "exception") {
        throw new Error(result.exceptionDetails?.text ?? `evaluate failed: ${expression.slice(0, 80)}`);
      }
      return deserialize(result.result);
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
    const navigate = async (url) => {
      await harvestExercisedMembers();
      return command("browsingContext.navigate", { context, url, wait: "complete" });
    };
    const pointer = (press, release) =>
      command("input.performActions", {
        context,
        actions: [
          {
            type: "pointer",
            id: "mouse",
            parameters: { pointerType: "mouse" },
            actions: [
              { type: "pointerMove", x: Math.round(press.x), y: Math.round(press.y), origin: "viewport" },
              { type: "pointerDown", button: 0 },
              { type: "pause", duration: 100 },
              { type: "pointerMove", x: Math.round(release.x), y: Math.round(release.y), origin: "viewport", duration: 50 },
              { type: "pause", duration: 25 },
              { type: "pointerUp", button: 0 },
            ],
          },
        ],
      });

    // Trusted keyboard input: WebDriver key-source state persists across
    // performActions calls, so a down-only action holds the key until keyUp.
    const keys = (type, value) =>
      command("input.performActions", {
        context,
        actions: [
          {
            type: "key",
            id: "keyboard",
            actions: [{ type: type === "down" ? "keyDown" : "keyUp", value }],
          },
        ],
      });

    const demoResult = await runDemo({ url: args.url, evaluate, navigate, pointer, keys, deadline });

    const browserVersion = await evaluate("navigator.userAgent");
    const payload = collectPayload(args["export-dir"], args.url, args["source-checksum"]);
    const performance = await collectPerformance(evaluate);
    const exercisedMembers = await harvestExercisedMembers();
    const consoleEvents = logEntries
      .filter((entry) => entry.level === "error")
      .map((entry) => ({ type: "console.error", text: entry.text ?? "error" }));

    const envelope = buildEnvelope({
      demo: args.demo,
      browser: { engine: "firefox", name: "Firefox", version: String(browserVersion) },
      payload,
      durationMs: Date.now() - startedAt,
      consoleEvents,
      demoResult,
      performance,
      exercisedMembers,
    });

    fs.writeFileSync(args.result, `${JSON.stringify(envelope, null, 2)}\n`);
    try {
      await command("session.end");
    } catch {
      // ignore
    }
    cleanup();
    process.exit(envelope.pass ? 0 : 1);
  } catch (error) {
    cleanup();
    process.stderr.write(`firefox_bidi: ${error.stack ?? error}\n`);
    process.exit(2);
  }
}

main();

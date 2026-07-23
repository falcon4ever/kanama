// chrome_cdp.mjs -- Chrome production Web export-smoke driver (Task 57f).
//
// Formalized from the Task-57 group-8 integration CDP driver. Unlike that
// ad-hoc script (which assumed a Chrome already listening on a fixed port), this
// driver OWNS its browser: it launches headless Chrome on an ephemeral remote
// debugging port, drives the demo over CDP, emits the versioned v1 result
// envelope (scripts/web/result_schema.py), and always tears the browser down.
//
// It is the intended CI gate. Firefox and Safari are separate local drivers.
//
// Args (all explicit; no workstation-absolute assumptions):
//   --url <url>              served export URL (from the smoke shell)
//   --result <path>          where to write the result envelope
//   --demo <match3>          demo to drive (bunnymark: Phase 3 follow-on)
//   --timeout <seconds>      overall driver budget
//   --source-checksum <hex>  pre-run served-tree checksum to echo into artifact
//   --export-dir <dir>       served export dir, for payload sizing
//   --browser-binary <path>  Chrome binary (optional; falls back to macOS default)

import { spawn } from "node:child_process";
import fs from "node:fs";
import os from "node:os";
import path from "node:path";

import { runMatch3 } from "./demos/match3.mjs";
import { runBunnymark } from "./demos/bunnymark.mjs";
import { buildEnvelope, collectPayload } from "./envelope.mjs";

const DEMOS = { match3: runMatch3, bunnymark: runBunnymark };

function parseArgs(argv) {
  const args = {};
  for (let i = 0; i < argv.length; i += 2) {
    const key = argv[i];
    if (!key.startsWith("--")) throw new Error(`unexpected driver argument: ${key}`);
    args[key.slice(2)] = argv[i + 1];
  }
  for (const required of ["url", "result", "demo", "timeout", "source-checksum", "export-dir"]) {
    if (!args[required]) throw new Error(`chrome_cdp: missing --${required}`);
  }
  return args;
}

const DEFAULT_CHROME =
  "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome";

const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));

async function waitForDebugTarget(port, deadline) {
  while (Date.now() < deadline) {
    try {
      const pages = await (await fetch(`http://127.0.0.1:${port}/json/list`)).json();
      const page = pages.find((entry) => entry.type === "page" && entry.webSocketDebuggerUrl);
      if (page) return page;
    } catch {
      // Chrome not listening yet.
    }
    await delay(150);
  }
  throw new Error("Chrome did not expose a CDP page target before the deadline");
}

async function main() {
  const args = parseArgs(process.argv.slice(2));
  const timeoutMs = Number(args.timeout) * 1000;
  const deadline = Date.now() + timeoutMs;
  const startedAt = Date.now();

  const runDemo = DEMOS[args.demo];
  if (!runDemo) {
    throw new Error(`chrome_cdp: unknown demo '${args.demo}' (expected ${Object.keys(DEMOS).join("|")})`);
  }

  const chromeBinary = args["browser-binary"] ?? DEFAULT_CHROME;
  const profileDir = fs.mkdtempSync(path.join(os.tmpdir(), "kanama-chrome-"));
  const debugPort = 0; // let Chrome choose; we read the chosen port from DevToolsActivePort

  const chrome = spawn(
    chromeBinary,
    [
      "--headless=new",
      // Godot's Compatibility renderer needs a WebGL context. In headless Chrome
      // that comes from ANGLE's SwiftShader software path, which recent Chrome
      // gates behind an explicit opt-in; do NOT pass --disable-gpu (it kills it).
      "--enable-unsafe-swiftshader",
      "--use-angle=swiftshader",
      "--no-first-run",
      "--no-default-browser-check",
      "--disable-extensions",
      "--hide-scrollbars",
      "--force-device-scale-factor=1",
      `--user-data-dir=${profileDir}`,
      `--remote-debugging-port=${debugPort}`,
      "--window-size=1280,900",
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
    if (!chrome.killed) chrome.kill("SIGKILL");
    try {
      fs.rmSync(profileDir, { recursive: true, force: true });
    } catch {
      // ignore
    }
  };
  process.on("exit", cleanup);

  try {
    // Chrome writes the chosen debugging port to DevToolsActivePort in the
    // profile dir; poll for it, then connect.
    const portFile = path.join(profileDir, "DevToolsActivePort");
    let port = null;
    while (Date.now() < deadline) {
      if (fs.existsSync(portFile)) {
        port = fs.readFileSync(portFile, "utf-8").split("\n")[0].trim();
        if (port) break;
      }
      if (chrome.exitCode !== null) {
        throw new Error(`Chrome exited early with code ${chrome.exitCode}`);
      }
      await delay(100);
    }
    if (!port) throw new Error("Chrome did not report a DevTools port");

    const page = await waitForDebugTarget(port, deadline);

    socket = new WebSocket(page.webSocketDebuggerUrl);
    await new Promise((resolve, reject) => {
      socket.addEventListener("open", resolve, { once: true });
      socket.addEventListener("error", reject, { once: true });
    });

    let nextId = 1;
    const pendingCalls = new Map();
    const consoleEvents = [];
    socket.addEventListener("message", ({ data }) => {
      const message = JSON.parse(data);
      if (message.id) {
        const callback = pendingCalls.get(message.id);
        pendingCalls.delete(message.id);
        if (message.error) callback.reject(new Error(JSON.stringify(message.error)));
        else callback.resolve(message.result);
        return;
      }
      if (message.method === "Runtime.exceptionThrown") {
        consoleEvents.push({
          type: "exception",
          text:
            message.params.exceptionDetails?.exception?.description ??
            message.params.exceptionDetails?.text ??
            "exception",
        });
      }
      if (
        message.method === "Runtime.consoleAPICalled" &&
        message.params.type === "error"
      ) {
        consoleEvents.push({
          type: "console.error",
          text: message.params.args
            .map((arg) => arg.value ?? arg.description ?? arg.type)
            .join(" "),
        });
      }
    });

    // Per-call timeout: a CDP command whose response is dropped (e.g. a race
    // with navigation destroying the execution context) must surface as an
    // error, never hang the whole driver past the smoke budget.
    const CALL_TIMEOUT_MS = 20_000;
    const call = (method, params = {}) => {
      const id = nextId++;
      return new Promise((resolve, reject) => {
        const timer = setTimeout(() => {
          pendingCalls.delete(id);
          reject(new Error(`CDP ${method} timed out after ${CALL_TIMEOUT_MS}ms`));
        }, CALL_TIMEOUT_MS);
        pendingCalls.set(id, {
          resolve: (value) => {
            clearTimeout(timer);
            resolve(value);
          },
          reject: (error) => {
            clearTimeout(timer);
            reject(error);
          },
        });
        socket.send(JSON.stringify({ id, method, params }));
      });
    };
    const evaluate = async (expression) => {
      const result = await call("Runtime.evaluate", {
        expression,
        returnByValue: true,
        awaitPromise: true,
      });
      if (result.exceptionDetails) {
        throw new Error(
          result.exceptionDetails.exception?.description ?? result.exceptionDetails.text,
        );
      }
      return result.result.value;
    };

    await call("Runtime.enable");
    await call("Page.enable");
    await call("Network.enable");
    await call("Network.setCacheDisabled", { cacheDisabled: true });
    await call("Emulation.setDeviceMetricsOverride", {
      width: 1280,
      height: 900,
      deviceScaleFactor: 1,
      mobile: false,
    });

    // Transport the demo modules use, independent of CDP specifics.
    const navigate = (url) => call("Page.navigate", { url });
    const pointer = async (press, release) => {
      await call("Input.dispatchMouseEvent", { type: "mousePressed", x: press.x, y: press.y, button: "left", buttons: 1, clickCount: 1 });
      await delay(100);
      await call("Input.dispatchMouseEvent", { type: "mouseMoved", x: release.x, y: release.y, button: "none", buttons: 1 });
      await delay(25);
      await call("Input.dispatchMouseEvent", { type: "mouseReleased", x: release.x, y: release.y, button: "left", buttons: 0, clickCount: 1 });
    };

    // Drive the demo. The demo module returns startup + assertion + lifecycle
    // facts; console/exception capture stays here where CDP delivers it.
    const demoResult = await runDemo({ url: args.url, evaluate, navigate, pointer, deadline });

    const browserVersion = await evaluate("navigator.userAgent");
    const payload = collectPayload(args["export-dir"], args.url, args["source-checksum"]);

    const envelope = buildEnvelope({
      demo: args.demo,
      browser: { engine: "chrome", name: "Google Chrome", version: browserVersion },
      payload,
      durationMs: Date.now() - startedAt,
      consoleEvents,
      demoResult,
    });

    fs.writeFileSync(args.result, `${JSON.stringify(envelope, null, 2)}\n`);
    cleanup();
    process.exit(envelope.pass ? 0 : 1);
  } catch (error) {
    cleanup();
    process.stderr.write(`chrome_cdp: ${error.stack ?? error}\n`);
    process.exit(2);
  }
}

main();

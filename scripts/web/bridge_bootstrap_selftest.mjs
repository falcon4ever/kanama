#!/usr/bin/env node
// Self-test of the Web bridge's start (task 135 item 5): `bootstrapKanamaWeb` refuses a Kotlin/Wasm module it cannot
// run with one line saying what to do, the line the page shows under the canvas and the smokes read. Three refusals:
// no module at all (the bundle defined no web-runtime), a module whose main() did not start the runtime (its own
// start-problem line, passed through), and a module of another protocol than the bridge (a bridge and a module from
// two builds, or a stale cache). A module of the bridge's own protocol starts.
//
// The bridge is loaded unmodified into a vm context with the browser pieces it touches stubbed (the same loader as
// bridge_simtime_selftest.mjs); the protocol under test is read from the bridge itself, so the test outlives a bump.
import fs from "node:fs";
import path from "node:path";
import vm from "node:vm";
import { fileURLToPath } from "node:url";

const here = path.dirname(fileURLToPath(import.meta.url));
const bridgePath = path.resolve(here, "../../web-runtime/src/webSpikeGodot/assets/kanama-web-bridge.js");
const bridgeSource = fs.readFileSync(bridgePath, "utf8");
const protocolVersion = Number(/const KANAMA_WEB_PROTOCOL_VERSION = (\d+);/.exec(bridgeSource)?.[1]);

function loadBridge() {
  const noop = () => {};
  const element = { textContent: "", style: {}, classList: { add: noop, remove: noop }, addEventListener: noop, setAttribute: noop };
  const context = {
    console,
    URLSearchParams,
    location: { search: "" },
    navigator: { userAgent: "node-selftest" },
    performance: { now: () => 0 },
    document: { querySelector: () => element, getElementById: () => element, createElement: () => element, addEventListener: noop },
    requestAnimationFrame: noop,
    KanamaWebPageStartedAt: 0,
    setTimeout,
    clearTimeout,
  };
  context.globalThis = context;
  context.window = context;
  vm.createContext(context);
  vm.runInContext(bridgeSource, context, { filename: bridgePath });
  return context;
}

/** The error bootstrapKanamaWeb rejects [api] with, or null when it starts. */
async function startError(api) {
  try {
    await loadBridge().bootstrapKanamaWeb(Promise.resolve(api));
    return null;
  } catch (error) {
    return String(error?.message ?? error);
  }
}

const failures = [];
const expectRefusal = async (label, api, ...parts) => {
  const message = await startError(api);
  if (message === null) {
    failures.push(`${label}: the bridge started`);
  } else if (message.includes("\n") || !parts.every((part) => message.includes(part))) {
    failures.push(`${label}: the refusal is not one line naming ${parts.map((part) => JSON.stringify(part)).join(", ")}: ${JSON.stringify(message)}`);
  }
};

if (!Number.isInteger(protocolVersion) || protocolVersion <= 0) {
  failures.push(`the bridge states no KANAMA_WEB_PROTOCOL_VERSION (${bridgePath})`);
} else {
  const module = (overrides) => ({ kanamaWebProtocolVersion: () => protocolVersion, kanamaWebStartProblem: () => null, ...overrides });
  await expectRefusal("no module", null, "the Kotlin/Wasm module did not load", "Export again");
  const notStarted = "Kanama Web runtime not started: the generated main() did not run";
  await expectRefusal("runtime not started", module({ kanamaWebStartProblem: () => notStarted }), notStarted);
  await expectRefusal(
    "protocol mismatch",
    module({ kanamaWebProtocolVersion: () => protocolVersion + 1 }),
    "Kanama Web protocol mismatch",
    `expected ${protocolVersion}, received ${protocolVersion + 1}`,
    "export again",
  );
  const started = await startError(module({}));
  if (started !== null) failures.push(`a module of the bridge's protocol ${protocolVersion}: refused with ${JSON.stringify(started)}`);
}

if (failures.length > 0) {
  console.error("bridge_bootstrap_selftest FAIL:");
  for (const failure of failures) console.error(`  ${failure}`);
  process.exit(1);
}
console.log(`bridge_bootstrap_selftest PASS: the bridge refuses no module, a runtime not started and a module of another protocol than ${protocolVersion} with one line each, and starts a module of protocol ${protocolVersion}`);

// envelope.mjs -- shared helpers for the Web export-smoke browser drivers.
//
// collectPayload() reports served-file sizes (drivers run in Node, so they can
// read the export dir directly). buildEnvelope() maps a demo module's result
// into the versioned v1 result envelope that result_schema.py validates.

import fs from "node:fs";
import path from "node:path";

const SCHEMA_VERSION = 1;

export function collectPayload(exportDir, url, sourceChecksum) {
  const files = [];
  let totalBytes = 0;
  const walk = (dir) => {
    for (const entry of fs.readdirSync(dir, { withFileTypes: true })) {
      const full = path.join(dir, entry.name);
      if (entry.isDirectory()) {
        walk(full);
      } else if (entry.isFile()) {
        const bytes = fs.statSync(full).size;
        files.push({ name: path.relative(exportDir, full), bytes });
        totalBytes += bytes;
      }
    }
  };
  walk(exportDir);
  files.sort((a, b) => a.name.localeCompare(b.name));
  return { url, files, totalBytes, sourceTreeChecksum: sourceChecksum };
}

/**
 * Reads the bridge's own performance counters at the end of a run.
 *
 * Deliberately generic: every demo gets these numbers without its module knowing
 * anything about budgets, so a new demo cannot silently arrive unmeasured.
 *
 * The metric that matters for budgets is **crossings per engine tick**, not
 * anything per wall-clock second. The engine's loop is paced by requestAnimationFrame and
 * Godot advances a fixed step per iteration, so the same build runs at ~2x real
 * time on one host and ~8.7x on another (measured, CI Chrome vs CI Firefox). A
 * wall-clock budget would grade the host; crossings per frame is a property of
 * the backend.
 */
export async function collectPerformance(evaluate) {
  try {
    return await evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      if (!bridge) return null;
      // A tick is one engine dispatch into the script layer. Both kinds count:
      // physics-driven demos (character controllers, racing, third-person) do all
      // their work in _physics_process and never touch _process, so counting render
      // frames alone reported ZERO ticks for a third of the corpus.
      const processTicks = bridge.processCalls ?? 0;
      const physicsTicks = bridge.physicsProcessCalls ?? 0;
      const ticks = processTicks + physicsTicks;
      const crossings = bridge.kotlinToGodotCalls ?? 0;
      return {
        ticksObserved: ticks,
        processTicks,
        physicsTicks,
        simSeconds: Math.round((bridge.simSeconds ?? 0) * 100) / 100,
        kotlinToGodotCalls: crossings,
        appliedCommands: bridge.appliedCommands ?? 0,
        // Rounded to two decimals: this is a budget input, not a benchmark.
        crossingsPerTick: ticks > 0 ? Math.round((crossings / ticks) * 100) / 100 : 0,
      };
    })()`);
  } catch {
    // A demo that tore the page down before we could ask is not a budget failure;
    // the budget checker treats a missing section as "not measured" and says so.
    return null;
  }
}

// demoResult contract (produced by each demo module):
//   protocolVersion : number
//   startup         : { loaded, outcome, durationMs }
//   checks          : { [name]: boolean }   (every gameplay/teardown assertion)
//   handles         : { liveAfterGameplay, liveAfterTeardown, staleRejected }
//   crossings       : { [name]: number }    (non-empty)
//   callbacks       : { [name]: number }
//   connections     : { [name]: number }
//   scheduler       : { [name]: number }
//   teardown        : { outcome, ownerRegistriesToBaseline }
//   boundaryErrors  : string[]               (bridge/telemetry-reported faults)
export function buildEnvelope({
  demo,
  browser,
  payload,
  durationMs,
  consoleEvents,
  demoResult,
  performance = null,
}) {
  const checks = demoResult.checks;
  const checkNames = Object.keys(checks);
  const passed = checkNames.filter((name) => checks[name] === true).length;
  const total = checkNames.length;
  const failed = total - passed;

  // Godot's web shell routes engine stderr to console.error, so engine WARNINGs the
  // engine itself recovered from (e.g. per-GPU texture-format fallbacks) arrive as
  // console errors. Those must not make the gate GPU/driver-dependent: record them as
  // warnings, fail only on everything else. Each WARNING is followed by an "at:" source
  // line, classified with the warning it belongs to.
  const consoleErrors = [];
  const consoleWarnings = [];
  let lastWasWarning = false;
  for (const event of consoleEvents) {
    const isWarning =
      (event.type === "console.error" &&
        (event.text.startsWith("WARNING:") || (lastWasWarning && /^\s*at: /.test(event.text)))) ||
      // Headless browsers reject pointer lock without a user gesture/focus; Godot's
      // mouse-capture request then surfaces as a WrongDocumentError (an async exception in
      // Chrome, a console error in Firefox). Environmental, not a game fault.
      /WrongDocumentError/.test(event.text);
    (isWarning ? consoleWarnings : consoleErrors).push(`${event.type}: ${event.text}`);
    lastWasWarning = isWarning;
  }
  const boundaryErrors = [...(demoResult.boundaryErrors ?? [])];

  const loaded = demoResult.startup.loaded === true;
  const pass = failed === 0 && loaded && boundaryErrors.length === 0 && consoleErrors.length === 0;

  return {
    schemaVersion: SCHEMA_VERSION,
    demo,
    protocolVersion: demoResult.protocolVersion,
    pass,
    durationMs,
    artifact: payload,
    browser,
    startup: demoResult.startup,
    assertions: {
      summary: { total, passed, failed, skipped: 0 },
      checks,
    },
    crossings: demoResult.crossings,
    handles: demoResult.handles,
    callbacks: demoResult.callbacks,
    connections: demoResult.connections,
    scheduler: demoResult.scheduler,
    console: { errors: consoleErrors, warnings: consoleWarnings, boundaryErrors },
    teardown: demoResult.teardown,
    // Optional: absent when the page could not be asked. The schema validates it
    // when present, and the budget gate reports "not measured" rather than
    // quietly passing.
    ...(performance ? { performance } : {}),
  };
}

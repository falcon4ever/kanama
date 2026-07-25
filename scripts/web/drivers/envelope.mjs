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
      event.type === "console.error" &&
      (event.text.startsWith("WARNING:") || (lastWasWarning && /^\s*at: /.test(event.text)));
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
  };
}

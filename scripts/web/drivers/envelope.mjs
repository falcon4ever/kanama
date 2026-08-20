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

/**
 * Reads the bridge's exercised-member census at the end of a run (task 81).
 *
 * The bridge records every boundary dispatch of a registered member as
 * scriptClass -> { memberKey: count }, with registered methods keyed "method#<id>"
 * (the proxies dispatch by id). This resolves those ids to member names using the
 * export's own kanama-web/KanamaWebProtocol.generated.json -- the manifest that
 * describes exactly the proxies this export shipped. An unresolvable key is kept
 * raw rather than dropped: if a REQUIRED member then appears missing,
 * result_schema.py fails loudly (a stale export without the manifest must never
 * pass the gate by omission).
 *
 * Collected centrally here, like collectPerformance, so the twelve demo modules
 * need no changes and a new demo cannot arrive uncounted.
 */
export async function collectExercisedMembers(evaluate, exportDir) {
  let raw;
  try {
    raw = await evaluate(`(() => {
      const bridge = globalThis.KanamaWebBridge;
      if (!bridge || !bridge.exercisedMembers) return null;
      return bridge.exercisedMembers;
    })()`);
  } catch {
    // The page was already torn down. The schema decides whether that matters:
    // a demo with a non-empty required-member list fails without this section.
    return null;
  }
  if (!raw || typeof raw !== "object") return null;

  let manifestScripts = null;
  try {
    const manifestPath = path.join(exportDir, "kanama-web", "KanamaWebProtocol.generated.json");
    manifestScripts = JSON.parse(fs.readFileSync(manifestPath, "utf-8")).scripts ?? null;
  } catch {
    // No readable manifest (an export predating the task-81 copy): keys stay raw
    // "method#<id>", and a demo with a required-member list then fails loudly.
  }

  const members = {};
  for (const [scriptName, bucket] of Object.entries(raw)) {
    const script = manifestScripts?.find((entry) => entry.className === scriptName);
    const resolved = {};
    for (const [key, count] of Object.entries(bucket)) {
      const idMatch = /^method#(\d+)$/.exec(key);
      const name = idMatch
        ? (script?.methods?.find((method) => method.id === Number(idMatch[1]))?.name ?? key)
        : key;
      resolved[name] = (resolved[name] ?? 0) + count;
    }
    members[scriptName] = resolved;
  }
  return members;
}

/**
 * Task 80 slice 6: resolve a registered method's NAME to the id the bridge dispatches on.
 *
 * The reverse of the census resolution above. Method ids are positional, so a driver must
 * never hardcode one -- adding a @RegisterFunction above another silently renumbers it,
 * and the call would then dispatch a DIFFERENT method while still "working".
 *
 * Returns null when the manifest or the method is missing, so the caller can report the
 * probe as unavailable rather than calling id 0 and reporting a confident wrong answer.
 */
export function resolveMethodId(exportDir, className, methodName) {
  try {
    const manifestPath = path.join(exportDir, "kanama-web", "KanamaWebProtocol.generated.json");
    const scripts = JSON.parse(fs.readFileSync(manifestPath, "utf-8")).scripts ?? [];
    // Package spelling differs per demo -- match3's manifest says
    // `net.multigesture.kanama.demos.match3.Main` while fps says `fps.Enemy` -- so match
    // on the tail rather than the full string. An exact-equality lookup silently found
    // nothing for match3 and would have reported "probe unavailable" for a probe that
    // was present.
    const tail = (name) => String(name).split(".").pop();
    const script = scripts.find(
      (entry) => entry.className === className || tail(entry.className) === tail(className),
    );
    return script?.methods?.find((method) => method.name === methodName)?.id ?? null;
  } catch {
    return null;
  }
}

/**
 * Merges two exercised-member censuses, summing dispatch counts (task 81).
 *
 * A navigation resets the page's bridge, so a driver that navigates more than
 * once (match3 reloads for its restart proof) would otherwise report only the
 * FINAL page load's census -- and match3's final load never receives the drag.
 * The engine drivers harvest before every navigation and merge, so the envelope
 * describes the whole run. Either side may be null (page not yet booted, or
 * already torn down); null contributes nothing.
 */
export function mergeExercisedMembers(base, addition) {
  if (!addition) return base;
  if (!base) return structuredClone(addition);
  const merged = structuredClone(base);
  for (const [scriptName, members] of Object.entries(addition)) {
    const bucket = (merged[scriptName] ??= {});
    for (const [member, count] of Object.entries(members)) {
      bucket[member] = (bucket[member] ?? 0) + count;
    }
  }
  return merged;
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
  exercisedMembers = null,
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
      /WrongDocumentError/.test(event.text) ||
      // Safari states the same refusal differently: it requires a genuine user gesture and a
      // WebDriver-synthesized click does not qualify. Same environmental cause, same verdict.
      /NotAllowedError.*Pointer lock requires a user gesture/.test(event.text);
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
    ...(demoResult.differential ? { differential: demoResult.differential } : {}),
    ...(performance ? { performance } : {}),
    // Task 81: absent only when the page could not be asked -- and then a demo
    // with a non-empty required-member list FAILS the schema, so this section
    // cannot quietly disappear the way the Safari performance section did (task 86).
    ...(exercisedMembers ? { exercisedMembers } : {}),
  };
}

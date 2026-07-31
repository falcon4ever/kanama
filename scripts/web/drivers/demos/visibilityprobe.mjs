// demos/visibilityprobe.mjs -- task 71 control (see scripts/web/visibility_probe.py).
//
// Drives a PLAIN GDSCRIPT export that contains no Kanama at all. Its only job is
// to open the page, keep it open, and let the scene report for itself; the
// verdict comes from the beacons the scene fetches back to the serving process,
// not from anything here.
//
// It exists because the standalone launcher could not get Firefox to run the page
// on a CI runner (zero beacons, which is INCONCLUSIVE rather than a result).
// Borrowing the engine driver's launch is legitimate: what is in question is the
// notifier, not our ability to start a browser -- and the driver also captures
// console output, which is the thing that explains a page that never boots.

const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
const DEBUG = process.env.KANAMA_WEB_SMOKE_DEBUG === "1";
const START = Date.now();
const trace = (msg) => {
  if (DEBUG) process.stderr.write(`[probe ${((Date.now() - START) / 1000).toFixed(1)}s] ${msg}\n`);
};

const WATCH_SECONDS = Number(process.env.KANAMA_PROBE_SECONDS ?? 40);

export async function runVisibilityprobe({ url, evaluate, navigate, deadline }) {
  const startedAt = Date.now();
  trace(`navigate ${url}`);
  await navigate(url);

  // The scene mirrors every beacon into a global as well as fetching it, so the
  // driver log carries the timeline even if the network path is what is broken.
  const readBeacons = async () => {
    try {
      return await evaluate("(globalThis.__kanamaProbe ?? []).join(' ')");
    } catch {
      return "";
    }
  };
  // Godot's own boot state, for the case where nothing is reported at all.
  const readBoot = async () => {
    try {
      return await evaluate(`(() => {
        const c = document.querySelector("canvas");
        return JSON.stringify({
          canvas: c ? c.clientWidth + "x" + c.clientHeight : "none",
          engineDefined: typeof globalThis.Engine !== "undefined",
          probe: (globalThis.__kanamaProbe ?? []).length,
          status: document.querySelector(".godot-status, #status-notice")?.textContent?.slice(0, 120) ?? "",
        });
      })()`);
    } catch (error) {
      return `evaluate failed: ${String(error).slice(0, 120)}`;
    }
  };

  const until = Math.min(deadline - 5_000, Date.now() + WATCH_SECONDS * 1000);
  let beacons = "";
  let boot = "";
  while (Date.now() < until) {
    beacons = await readBeacons();
    boot = await readBoot();
    trace(`boot=${boot} beacons=[${beacons}]`);
    if (beacons.includes("exited") || beacons.includes("timeout")) break;
    await delay(2_000);
  }

  const fired = beacons.includes("exited");
  return {
    // This page has no Kanama protocol; 1 marks "control, not a Kanama export"
    // rather than claiming a protocol version it does not implement.
    protocolVersion: 1,
    startup: {
      loaded: beacons.length > 0,
      outcome: beacons.length > 0 ? "ready" : "never-reported",
      durationMs: Date.now() - startedAt,
    },
    checks: {
      pageReported: beacons.length > 0,
      screenExitedFired: fired,
    },
    handles: { liveAfterGameplay: 0, liveAfterTeardown: 0, staleRejected: 0 },
    crossings: { beacons: beacons.split(" ").filter(Boolean).length },
    callbacks: {},
    connections: {},
    scheduler: {},
    teardown: { outcome: "n/a", ownerRegistriesToBaseline: true },
    boundaryErrors: [],
    probeBeacons: beacons,
    probeBoot: boot,
  };
}

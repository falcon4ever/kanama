#!/usr/bin/env bash
#
# web_ci_matrix.sh -- Web browser matrix gate (Task 60h, promotion criterion W4).
#
# Exports a set of demos and drives each one through `web_export_smoke.sh` in
# each requested browser, then aggregates every run into one machine-readable
# evidence file. This is what CI runs; it is also the one command a maintainer
# runs locally before a release.
#
# Why a matrix runner instead of a for-loop in the workflow: a demo can pass its
# own driver's checks and still fail the envelope schema (tpsdemo did exactly
# that on every engine until kanama#118). Routing every cell through
# `web_export_smoke.sh` -- which validates the envelope before it prints PASS --
# is what makes "the corpus is green" mean something.
#
# The matrix is Chrome + Firefox. Safari is deliberately NOT a CI cell: it has
# no headless mode, needs a logged-in GUI session with an unoccluded window, and
# two concurrent Safari gates cannot be told apart by the driver's PID-diff
# reaping. `--engine safari` works here for the local pre-promotion gate, but run
# it alone.
#
# Every cell runs even after an earlier one fails, so a red run reports the whole
# picture instead of the first casualty.
#
# Usage:
#   scripts/web_ci_matrix.sh \
#       --godot <godot-binary> \
#       --template <web_nothreads_release.zip> \
#       [--demos-dir <kanama-demos checkout>] \
#       [--demo-set pr|full] [--demo <key> ...] \
#       [--engine chrome] [--engine firefox] \
#       [--chrome-binary <path>] [--firefox-binary <path>] \
#       [--result-dir <dir>] [--evidence <path>] [--summary-md <path>] \
#       [--timeout-scale <n>] [--skip-export] [--keep-exports]
#
# `--demo-set pr` is the per-PR subset, `full` the whole corpus (see
# scripts/web/demos.sh for both lists and the per-demo budgets).

set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
WEB_DIR="$ROOT_DIR/scripts/web"
# shellcheck source=scripts/web/demos.sh
source "$WEB_DIR/demos.sh"

GODOT_BIN=""
TEMPLATE=""
DEMOS_DIR=""
DEMO_SET=""
DEMOS=()
ENGINES=()
CHROME_BINARY=""
FIREFOX_BINARY=""
RESULT_DIR=""
EVIDENCE=""
SUMMARY_MD=""
TIMEOUT_SCALE="${KANAMA_WEB_TIMEOUT_SCALE:-1}"
SKIP_EXPORT=0
KEEP_EXPORTS=0
CREATED_RESULT_DIR=0

die() {
  echo "[web_ci_matrix] $*" >&2
  exit 2
}

usage() {
  sed -n '2,42p' "${BASH_SOURCE[0]}" | sed 's/^# \{0,1\}//'
  exit "${1:-2}"
}

while [[ $# -gt 0 ]]; do
  case "$1" in
    --godot) GODOT_BIN="${2:?}"; shift 2 ;;
    --template) TEMPLATE="${2:?}"; shift 2 ;;
    --demos-dir) DEMOS_DIR="${2:?}"; shift 2 ;;
    --demo-set) DEMO_SET="${2:?}"; shift 2 ;;
    --demo) DEMOS+=("${2:?}"); shift 2 ;;
    --engine) ENGINES+=("${2:?}"); shift 2 ;;
    --chrome-binary) CHROME_BINARY="${2:?}"; shift 2 ;;
    --firefox-binary) FIREFOX_BINARY="${2:?}"; shift 2 ;;
    --result-dir) RESULT_DIR="${2:?}"; shift 2 ;;
    --evidence) EVIDENCE="${2:?}"; shift 2 ;;
    --summary-md) SUMMARY_MD="${2:?}"; shift 2 ;;
    --timeout-scale) TIMEOUT_SCALE="${2:?}"; shift 2 ;;
    --skip-export) SKIP_EXPORT=1; shift ;;
    --keep-exports) KEEP_EXPORTS=1; shift ;;
    -h|--help) usage 0 ;;
    *) die "unknown argument: $1" ;;
  esac
done

# Argument shape first, environment second: a typo in --demo-set should not be
# reported as a missing export template.
[[ "$TIMEOUT_SCALE" =~ ^[0-9]+([.][0-9]+)?$ ]] || die "--timeout-scale must be a positive number"

case "${DEMO_SET:-}" in
  "") ;;
  pr) DEMOS+=("${KANAMA_WEB_PR_DEMOS[@]}") ;;
  full) DEMOS+=("${KANAMA_WEB_ALL_DEMOS[@]}") ;;
  *) die "unknown --demo-set: $DEMO_SET (expected pr|full)" ;;
esac
[[ "${#DEMOS[@]}" -gt 0 ]] || DEMOS=("${KANAMA_WEB_PR_DEMOS[@]}")
for demo in "${DEMOS[@]}"; do
  kanama_web_demo_is_known "$demo" ||
    die "unknown --demo: $demo (expected one of: ${KANAMA_WEB_ALL_DEMOS[*]})"
done

[[ "${#ENGINES[@]}" -gt 0 ]] || ENGINES=(chrome firefox)
for engine in "${ENGINES[@]}"; do
  case "$engine" in
    chrome|firefox) ;;
    safari)
      echo "[web_ci_matrix] NOTE: Safari is a local gate -- never run two Safari" >&2
      echo "[web_ci_matrix]       gates at once on one machine (PID-diff reaping)." >&2
      ;;
    *) die "unknown --engine: $engine (expected chrome|firefox|safari)" ;;
  esac
done

[[ -n "$GODOT_BIN" ]] || die "--godot is required"
[[ -x "$GODOT_BIN" ]] || die "Godot binary is not executable: $GODOT_BIN"
GODOT_BIN="$(cd "$(dirname "$GODOT_BIN")" && pwd)/$(basename "$GODOT_BIN")"
if [[ "$SKIP_EXPORT" -eq 0 ]]; then
  [[ -n "$TEMPLATE" ]] || die "--template /path/to/web_nothreads_release.zip is required"
  [[ -f "$TEMPLATE" ]] || die "export template not found: $TEMPLATE"
  TEMPLATE="$(cd "$(dirname "$TEMPLATE")" && pwd)/$(basename "$TEMPLATE")"
fi

# A demos checkout is only needed to EXPORT a demo that lives there; driving an
# already-built export needs nothing but the export directory.
NEEDS_DEMOS_DIR=0
if [[ "$SKIP_EXPORT" -eq 0 ]]; then
  for demo in "${DEMOS[@]}"; do
    [[ -n "$(kanama_web_demo_project_dir "$demo")" ]] && NEEDS_DEMOS_DIR=1
  done
fi
if [[ "$NEEDS_DEMOS_DIR" -eq 1 ]]; then
  [[ -n "$DEMOS_DIR" ]] || die "--demos-dir is required for the selected demos"
  [[ -d "$DEMOS_DIR" ]] || die "demos checkout not found: $DEMOS_DIR"
  DEMOS_DIR="$(cd "$DEMOS_DIR" && pwd)"
fi

if [[ -z "$RESULT_DIR" ]]; then
  RESULT_DIR="$(mktemp -d "${TMPDIR:-/tmp}/kanama_web_matrix.XXXXXX")"
  CREATED_RESULT_DIR=1
else
  mkdir -p "$RESULT_DIR"
fi
RESULT_DIR="$(cd "$RESULT_DIR" && pwd)"
RUNS_DIR="$RESULT_DIR/runs"
mkdir -p "$RUNS_DIR"

absolutize_out() {
  mkdir -p "$(dirname "$1")"
  echo "$(cd "$(dirname "$1")" && pwd)/$(basename "$1")"
}
[[ -n "$EVIDENCE" ]] && EVIDENCE="$(absolutize_out "$EVIDENCE")"
[[ -n "$SUMMARY_MD" ]] && SUMMARY_MD="$(absolutize_out "$SUMMARY_MD")"
[[ -n "$EVIDENCE" ]] || EVIDENCE="$RESULT_DIR/web-ci-matrix-evidence.json"

echo "[web_ci_matrix] demos:   ${DEMOS[*]}"
echo "[web_ci_matrix] engines: ${ENGINES[*]}"
echo "[web_ci_matrix] results: $RESULT_DIR"

# Record a failed cell per engine for a demo that never got as far as a driver
# run. Without this a missing or failed export makes the demo VANISH from the
# summary table -- the run reports FAIL with no row explaining which demo, which
# is the same "absence of evidence reads as evidence" trap this gate exists to
# close.
record_unrun_demo() {
  local demo="$1" reason="$2"
  local engine
  for engine in "${ENGINES[@]}"; do
    RUN_INDEX=$((RUN_INDEX + 1))
    python3 -c '
import json, sys
out, demo, engine, reason = sys.argv[1:]
with open(out, "w") as handle:
    json.dump(
        {
            "demo": demo,
            "engine": engine,
            "pass": False,
            "wallSeconds": 0,
            "failureReason": reason,
            "exportPayloadBytes": None,
            "protocolVersion": None,
            "resultPath": None,
            "browser": None,
            "durationMs": None,
            "assertions": None,
            "liveAfterTeardown": None,
        },
        handle,
        indent=2,
    )
' "$RUNS_DIR/$(printf '%03d' "$RUN_INDEX")-$demo-$engine.json" "$demo" "$engine" "$reason"
  done
}

FAILED=0
RUN_INDEX=0

for demo in "${DEMOS[@]}"; do
  echo "[web_ci_matrix] === $demo ==="
  # The build key can differ from the driver key: the soak gate drives the dodge
  # export. Everything else exports under its own name.
  export_key="$(kanama_web_demo_export_key "$demo")"
  export_dir="$ROOT_DIR/web-runtime/build/web-export/$export_key"

  if [[ "$SKIP_EXPORT" -eq 0 ]]; then
    # Always export from a clean directory: a stale export silently re-runs an
    # older protocol and the mismatch surfaces as an unrelated driver failure.
    rm -rf "$export_dir"
    gradle_args=(
      --no-daemon
      -Pkotlin.compiler.execution.strategy=in-process
      :web-runtime:exportWeb
      "-PkanamaWebDemo=$export_key"
      "-PkanamaGodotExecutable=$GODOT_BIN"
      "-PkanamaWebTemplateRelease=$TEMPLATE"
    )
    project_subdir="$(kanama_web_demo_project_dir "$export_key")"
    if [[ -n "$project_subdir" ]]; then
      project_dir="$DEMOS_DIR/$project_subdir"
      [[ -d "$project_dir" ]] || die "$demo: demo project not found: $project_dir"
      gradle_args+=("-P$(kanama_web_demo_project_property "$export_key")=$project_dir")
    fi
    while IFS= read -r extra; do
      [[ -n "$extra" ]] && gradle_args+=("$extra")
    done < <(kanama_web_demo_export_args "$export_key")

    if ! "$ROOT_DIR/gradlew" -p "$ROOT_DIR" "${gradle_args[@]}"; then
      echo "[web_ci_matrix] $demo: EXPORT FAILED" >&2
      FAILED=1
      record_unrun_demo "$demo" "export failed"
      continue
    fi
  fi

  if [[ ! -f "$export_dir/index.html" ]]; then
    echo "[web_ci_matrix] $demo: no export at $export_dir" >&2
    FAILED=1
    record_unrun_demo "$demo" "no export at $export_dir"
    continue
  fi

  report="$export_dir/kanama-web/export-report.json"
  payload_bytes=""
  protocol=""
  if [[ -f "$report" ]]; then
    payload_bytes="$(python3 -c '
import json, sys
report = json.load(open(sys.argv[1]))
print(report.get("totalBytes") or sum(f["bytes"] for f in report["files"]))
' "$report")"
    protocol="$(python3 -c '
import json, sys
print(json.load(open(sys.argv[1])).get("protocolVersion", ""))
' "$report")"
  fi

  for engine in "${ENGINES[@]}"; do
    RUN_INDEX=$((RUN_INDEX + 1))
    budget="$(python3 -c 'import math,sys; print(int(math.ceil(float(sys.argv[1]) * float(sys.argv[2]))))' \
      "$(kanama_web_demo_timeout "$demo")" "$TIMEOUT_SCALE")"
    result_path="$RESULT_DIR/$demo-$engine.json"
    smoke_args=(
      --engine "$engine"
      --export-dir "$export_dir"
      --demo "$demo"
      --result "$result_path"
      --timeout "$budget"
      --log-dir "$RESULT_DIR"
    )
    case "$engine" in
      chrome) [[ -n "$CHROME_BINARY" ]] && smoke_args+=(--browser-binary "$CHROME_BINARY") ;;
      firefox) [[ -n "$FIREFOX_BINARY" ]] && smoke_args+=(--browser-binary "$FIREFOX_BINARY") ;;
    esac

    quarantine="$(kanama_web_quarantine_reason "$demo:$engine")"
    echo "[web_ci_matrix] --- $demo on $engine (budget ${budget}s)${quarantine:+ [QUARANTINED]} ---"
    started="$(date +%s)"
    cell_pass="false"
    if "$ROOT_DIR/scripts/web_export_smoke.sh" "${smoke_args[@]}"; then
      cell_pass="true"
      if [[ -n "$quarantine" ]]; then
        # A stale quarantine is worse than no quarantine: say so every single run.
        echo "[web_ci_matrix] $demo on $engine PASSED while quarantined ($quarantine)."
        echo "[web_ci_matrix] If this holds, LIFT the quarantine in scripts/web/demos.sh."
      fi
    elif [[ -n "$quarantine" ]]; then
      echo "[web_ci_matrix] $demo on $engine: FAILED but QUARANTINED -- $quarantine" >&2
    else
      echo "[web_ci_matrix] $demo on $engine: FAILED" >&2
      FAILED=1
    fi
    elapsed=$(( $(date +%s) - started ))

    python3 -c '
import json, os, sys
(out, demo, engine, passed, elapsed, payload, protocol, result_path, web_dir, quarantine) = sys.argv[1:]
sys.path.insert(0, web_dir)
from browser_version import parse_version
record = {
    "demo": demo,
    "engine": engine,
    "pass": passed == "true",
    "wallSeconds": int(elapsed),
    "exportPayloadBytes": int(payload) if payload else None,
    "protocolVersion": int(protocol) if protocol else None,
    "resultPath": result_path,
    "quarantine": quarantine or None,
    "browser": None,
    "durationMs": None,
    "assertions": None,
    "liveAfterTeardown": None,
}
# Fold the driver envelope in, when the driver got far enough to write one: the
# browser version is what the floor check reads, and a failed cell is far easier
# to triage with its assertion summary attached.
if os.path.exists(result_path):
    try:
        envelope = json.load(open(result_path))
    except (OSError, ValueError):
        envelope = None
    if isinstance(envelope, dict):
        browser = envelope.get("browser")
        if isinstance(browser, dict):
            # Keep the raw user-agent as the evidence and add the read version
            # beside it, so the floor gate never re-derives it differently.
            browser = dict(browser)
            browser["parsedVersion"] = parse_version(engine, browser.get("version"))
        record["browser"] = browser
        record["durationMs"] = envelope.get("durationMs")
        summary = (envelope.get("assertions") or {}).get("summary")
        record["assertions"] = summary
        record["liveAfterTeardown"] = (envelope.get("handles") or {}).get("liveAfterTeardown")
with open(out, "w") as handle:
    json.dump(record, handle, indent=2)
' "$RUNS_DIR/$(printf '%03d' "$RUN_INDEX")-$demo-$engine.json" \
      "$demo" "$engine" "$cell_pass" "$elapsed" "$payload_bytes" "$protocol" "$result_path" \
      "$WEB_DIR" "$quarantine"
  done

  if [[ "$KEEP_EXPORTS" -eq 0 && "$SKIP_EXPORT" -eq 0 ]]; then
    # The full corpus is ~500 MB of exports; a CI host does not need them once
    # every cell has run. Evidence lives in the result JSON, not the payload.
    rm -rf "$export_dir"
  fi
done

python3 - "$EVIDENCE" "$ROOT_DIR" "$GODOT_BIN" "$TEMPLATE" "$FAILED" "$RUNS_DIR" \
  "${DEMOS_DIR:-}" "${SUMMARY_MD:-}" "${DEMO_SET:-custom}" <<'PY'
import datetime, glob, json, os, subprocess, sys

(path, root, godot, template, failed, runs_dir, demos_dir, summary_md, demo_set) = sys.argv[1:]
runs = [json.load(open(name)) for name in sorted(glob.glob(os.path.join(runs_dir, "*.json")))]


def git(repo, *args):
    if not repo or not os.path.isdir(repo):
        return None
    try:
        out = subprocess.run(
            ["git", "-C", repo, *args], capture_output=True, text=True, check=True
        )
    except (OSError, subprocess.CalledProcessError):
        return None
    return out.stdout.strip() or None


godot_version = subprocess.run(
    [godot, "--headless", "--version"], capture_output=True, text=True
).stdout.strip().splitlines()
evidence = {
    "schemaVersion": 1,
    "gate": "web-ci-matrix",
    "generatedAt": datetime.datetime.now(datetime.timezone.utc).isoformat(timespec="seconds"),
    "demoSet": demo_set,
    "kanama": {"commit": git(root, "rev-parse", "HEAD")},
    "demos": {"commit": git(demos_dir, "rev-parse", "HEAD")} if demos_dir else None,
    "godot": {"binary": godot, "version": godot_version[-1] if godot_version else None},
    "template": template or None,
    "runs": runs,
    "pass": failed == "0",
}
with open(path, "w") as handle:
    json.dump(evidence, handle, indent=2)
    handle.write("\n")
print(f"[web_ci_matrix] evidence: {path}")

lines = [
    "| demo | engine | result | wall | browser | protocol |",
    "| --- | --- | --- | --- | --- | --- |",
]
for run in runs:
    browser = run.get("browser") or {}
    version = browser.get("parsedVersion") or run.get("failureReason") or "?"
    lines.append(
        "| {demo} | {engine} | {verdict} | {wall}s | {name} {version} | {protocol} |".format(
            demo=run["demo"],
            engine=run["engine"],
            verdict=(
                ("PASS" if run["pass"] else "**FAIL**")
                + (" _(quarantined)_" if run.get("quarantine") else "")
            ),
            wall=run["wallSeconds"],
            name=browser.get("name") or run["engine"],
            version=version,
            protocol=run.get("protocolVersion") or "-",
        )
    )
table = "\n".join(lines)
print(table)
if summary_md:
    with open(summary_md, "w") as handle:
        handle.write(f"### Web matrix ({demo_set})\n\n{table}\n")
PY

if [[ "$FAILED" -ne 0 ]]; then
  echo "[web_ci_matrix] FAIL -- see $RESULT_DIR" >&2
  exit 1
fi

if [[ "$CREATED_RESULT_DIR" -eq 1 ]]; then
  echo "[web_ci_matrix] results kept at $RESULT_DIR"
fi
echo "[web_ci_matrix] PASS -- ${#DEMOS[@]} demo(s) x ${#ENGINES[@]} engine(s)"

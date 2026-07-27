#!/usr/bin/env bash
#
# web_fresh_checkout_smoke.sh -- fresh-source-checkout Web export gate (Task 60g, W3).
#
# Proves a Web export is reproducible from a CLEAN CLONE with nothing but a Godot
# binary and the matching web_nothreads_release template: it clones Kanama (and
# kanama-demos when an external demo is selected) into a throwaway workspace with
# its own HOME, Gradle home and Maven-local, exports each requested demo from
# that clone, and then asserts the three things W3 asks for:
#
#   1. the export succeeds with no workstation-absolute path in ANY served file
#      (not just index.html, which exportWeb already checks),
#   2. exporting never mutates the demo source tree (checksum + git status), and
#   3. the exported artifact really runs -- the shipped export smoke drives it in
#      a real browser out of the fresh clone, so the harness is proven to ship.
#
# It writes a machine-readable evidence file recording commits, checksums,
# payload sizes and per-demo driver results, which is what a promotion review
# reads. It never touches the caller's checkout, Gradle home or demo trees.
#
# Usage:
#   scripts/web_fresh_checkout_smoke.sh \
#       --template /path/to/web_nothreads_release.zip \
#       [--demo web3d] [--demo match3] ... | [--demo all] \
#       [--kanama-source URL_OR_PATH] [--demos-source URL_OR_PATH] \
#       [--engine chrome|firefox|safari] [--skip-browser] \
#       [--work-dir DIR] [--keep-work-dir] [--evidence FILE] \
#       /path/to/godot
#
# Default demo set: web3d (in-repo fixture: proves the Kanama clone alone can
# export) and match3 (external demo: proves the demos-checkout path).

set -euo pipefail

KANAMA_SOURCE="https://github.com/falcon4ever/kanama.git"
DEMOS_SOURCE="https://github.com/falcon4ever/kanama-demos.git"
TEMPLATE=""
ENGINE="chrome"
SKIP_BROWSER=0
WORK_DIR="${KANAMA_WEB_FRESH_WORK_DIR:-}"
KEEP_WORK_DIR="${KANAMA_WEB_FRESH_KEEP:-0}"
EVIDENCE=""
GODOT_BIN=""
DEMOS=()
CREATED_WORK_DIR=0

ALL_DEMOS=(match3 bunnymark dodge web3d platformer squash fps charactercontroller thirdperson racing citybuilder tpsdemo)

# demo key -> kanama-demos project directory. `web3d` is an in-repo fixture and
# needs no demos checkout; its project dir is resolved by the build itself.
demo_project_dir() {
  case "$1" in
    match3) echo "Starter-Kit-Match3" ;;
    bunnymark) echo "Bunnymark" ;;
    dodge) echo "godot-demo-2d-dodge-the-creeps" ;;
    web3d) echo "" ;;
    platformer) echo "Starter-Kit-3D-Platformer" ;;
    squash) echo "godot-demo-3d-squash-the-creeps" ;;
    fps) echo "Starter-Kit-FPS" ;;
    charactercontroller) echo "godot-4-3d-character-controller-tutorial" ;;
    thirdperson) echo "godot-4-3d-third-person-controller" ;;
    racing) echo "Starter-Kit-Racing" ;;
    citybuilder) echo "Starter-Kit-City-Builder" ;;
    tpsdemo) echo "tps-demo-kanama" ;;
    *) return 1 ;;
  esac
}

# demo key -> the -PkanamaWeb<Key>ProjectDir gradle property the build reads.
demo_project_property() {
  case "$1" in
    match3) echo "kanamaWebMatch3ProjectDir" ;;
    bunnymark) echo "kanamaWebBunnymarkProjectDir" ;;
    dodge) echo "kanamaWebDodgeProjectDir" ;;
    web3d) echo "" ;;
    platformer) echo "kanamaWebPlatformerProjectDir" ;;
    squash) echo "kanamaWebSquashProjectDir" ;;
    fps) echo "kanamaWebFpsProjectDir" ;;
    charactercontroller) echo "kanamaWebCharactercontrollerProjectDir" ;;
    thirdperson) echo "kanamaWebThirdpersonProjectDir" ;;
    racing) echo "kanamaWebRacingProjectDir" ;;
    citybuilder) echo "kanamaWebCitybuilderProjectDir" ;;
    tpsdemo) echo "kanamaWebTpsdemoProjectDir" ;;
    *) return 1 ;;
  esac
}

die() {
  echo "[web_fresh_checkout] $*" >&2
  exit 2
}

usage() {
  sed -n '2,34p' "${BASH_SOURCE[0]}" | sed 's/^# \{0,1\}//'
  exit "${1:-2}"
}

while [[ $# -gt 0 ]]; do
  case "$1" in
    --kanama-source) KANAMA_SOURCE="${2:?}"; shift 2 ;;
    --demos-source) DEMOS_SOURCE="${2:?}"; shift 2 ;;
    --template) TEMPLATE="${2:?}"; shift 2 ;;
    --demo) DEMOS+=("${2:?}"); shift 2 ;;
    --engine) ENGINE="${2:?}"; shift 2 ;;
    --skip-browser) SKIP_BROWSER=1; shift ;;
    --work-dir) WORK_DIR="${2:?}"; shift 2 ;;
    --keep-work-dir) KEEP_WORK_DIR=1; shift ;;
    --evidence) EVIDENCE="${2:?}"; shift 2 ;;
    -h|--help) usage 0 ;;
    --*) die "unknown option: $1" ;;
    *)
      [[ -z "$GODOT_BIN" ]] || die "unexpected extra argument: $1"
      GODOT_BIN="$1"; shift ;;
  esac
done

[[ -n "$GODOT_BIN" ]] || die "missing Godot binary (positional argument)"
[[ -x "$GODOT_BIN" ]] || die "Godot binary is not executable: $GODOT_BIN"
[[ -n "$TEMPLATE" ]] || die "--template /path/to/web_nothreads_release.zip is required"
[[ -f "$TEMPLATE" ]] || die "export template not found: $TEMPLATE"
GODOT_BIN="$(cd "$(dirname "$GODOT_BIN")" && pwd)/$(basename "$GODOT_BIN")"
TEMPLATE="$(cd "$(dirname "$TEMPLATE")" && pwd)/$(basename "$TEMPLATE")"

if [[ "${#DEMOS[@]}" -eq 0 ]]; then
  DEMOS=(web3d match3)
elif [[ "${#DEMOS[@]}" -eq 1 && "${DEMOS[0]}" == "all" ]]; then
  DEMOS=("${ALL_DEMOS[@]}")
fi
for demo in "${DEMOS[@]}"; do
  demo_project_dir "$demo" >/dev/null || die "unknown --demo: $demo (expected one of: ${ALL_DEMOS[*]} | all)"
done

# Only clone kanama-demos when a selected demo actually lives there.
NEED_DEMOS=0
for demo in "${DEMOS[@]}"; do
  if [[ -n "$(demo_project_dir "$demo")" ]]; then
    NEED_DEMOS=1
  fi
done

if [[ -z "$WORK_DIR" ]]; then
  WORK_DIR="$(mktemp -d "${TMPDIR:-/tmp}/kanama_web_fresh.XXXXXX")"
  CREATED_WORK_DIR=1
else
  mkdir -p "$WORK_DIR"
fi
WORK_DIR="$(cd "$WORK_DIR" && pwd)"

cleanup() {
  if [[ "$CREATED_WORK_DIR" -eq 1 && "$KEEP_WORK_DIR" != "1" ]]; then
    rm -rf "$WORK_DIR"
  fi
}
trap cleanup EXIT

FRESH_HOME="$WORK_DIR/home"
GRADLE_HOME="$WORK_DIR/gradle-home"
MAVEN_REPO="$WORK_DIR/m2-repository"
KANAMA_DIR="$WORK_DIR/kanama"
DEMOS_DIR="$WORK_DIR/kanama-demos"
RESULT_DIR="$WORK_DIR/results"
RUNS_DIR="$WORK_DIR/runs"
mkdir -p "$FRESH_HOME" "$GRADLE_HOME" "$MAVEN_REPO" "$RESULT_DIR" "$RUNS_DIR"

if [[ -z "$EVIDENCE" ]]; then
  EVIDENCE="$WORK_DIR/web-fresh-checkout-evidence.json"
else
  mkdir -p "$(dirname "$EVIDENCE")"
  EVIDENCE="$(cd "$(dirname "$EVIDENCE")" && pwd)/$(basename "$EVIDENCE")"
fi

run() {
  echo "[web_fresh_checkout] $*"
  "$@"
}

checksum() {
  python3 "$KANAMA_DIR/scripts/web/tree_checksum.py" "$@"
}

echo "[web_fresh_checkout] workspace: $WORK_DIR"
echo "[web_fresh_checkout] demos: ${DEMOS[*]}"
run git clone --quiet "$KANAMA_SOURCE" "$KANAMA_DIR"
KANAMA_COMMIT="$(git -C "$KANAMA_DIR" rev-parse HEAD)"
DEMOS_COMMIT=""
if [[ "$NEED_DEMOS" -eq 1 ]]; then
  run git clone --quiet "$DEMOS_SOURCE" "$DEMOS_DIR"
  DEMOS_COMMIT="$(git -C "$DEMOS_DIR" rev-parse HEAD)"
fi

# Isolated build environment: nothing may reach the caller's HOME, Gradle caches
# or Maven-local, or the run is not evidence of a fresh checkout.
CALLER_HOME="$HOME"
export HOME="$FRESH_HOME"
export GRADLE_USER_HOME="$GRADLE_HOME"
export KANAMA_MAVEN_LOCAL_REPO="$MAVEN_REPO"
export GRADLE_OPTS="-Dmaven.repo.local=$MAVEN_REPO ${GRADLE_OPTS:-}"

FAILED=0
RUN_INDEX=0

for demo in "${DEMOS[@]}"; do
  RUN_INDEX=$((RUN_INDEX + 1))
  echo "[web_fresh_checkout] === $demo ==="
  project_subdir="$(demo_project_dir "$demo")"
  project_property="$(demo_project_property "$demo")"

  gradle_args=(
    --no-daemon
    -Pkotlin.compiler.execution.strategy=in-process
    :web-runtime:exportWeb
    "-PkanamaWebDemo=$demo"
    "-PkanamaGodotExecutable=$GODOT_BIN"
    "-PkanamaWebTemplateRelease=$TEMPLATE"
  )

  project_dir=""
  source_before=""
  if [[ -n "$project_subdir" ]]; then
    project_dir="$DEMOS_DIR/$project_subdir"
    [[ -d "$project_dir" ]] || die "$demo: demo project not found in the fresh clone: $project_dir"
    gradle_args+=("-P$project_property=$project_dir")
    source_before="$(checksum "$project_dir" --exclude .git --exclude .godot)"
  fi
  # Bunnymark's validated Web configuration is the 256-sprite V1Sprites variant.
  if [[ "$demo" == "bunnymark" ]]; then
    gradle_args+=("-PkanamaWebBunnymarkVariant=BunnymarkV1Sprites")
  fi

  run "$KANAMA_DIR/gradlew" -p "$KANAMA_DIR" "${gradle_args[@]}"

  export_dir="$KANAMA_DIR/web-runtime/build/web-export/$demo"
  [[ -f "$export_dir/index.html" ]] || die "$demo: export produced no index.html at $export_dir"

  # (1) No workstation-absolute path in ANY served file. The workspace covers the
  # clone and the fresh home; the caller's real home catches anything the build
  # picked up from the machine it ran on.
  if ! run python3 "$KANAMA_DIR/scripts/web/check_no_local_paths.py" "$export_dir" \
    --forbid "$WORK_DIR" --forbid "$CALLER_HOME"; then
    echo "[web_fresh_checkout] $demo: served export embeds local paths" >&2
    FAILED=1
  fi

  # (2) Exporting must not have written into the demo source tree.
  source_after=""
  if [[ -n "$project_dir" ]]; then
    source_after="$(checksum "$project_dir" --exclude .git --exclude .godot)"
    if [[ "$source_before" != "$source_after" ]]; then
      echo "[web_fresh_checkout] $demo: export MUTATED the demo source tree" >&2
      git -C "$DEMOS_DIR" status --short >&2 || true
      FAILED=1
    fi
    if [[ -n "$(git -C "$DEMOS_DIR" status --porcelain -- "$project_subdir")" ]]; then
      echo "[web_fresh_checkout] $demo: demo checkout is dirty after export" >&2
      git -C "$DEMOS_DIR" status --short -- "$project_subdir" >&2
      FAILED=1
    fi
  fi

  payload_bytes="$(python3 -c '
import json, sys
report = json.load(open(sys.argv[1]))
print(report.get("totalBytes") or sum(f["bytes"] for f in report["files"]))
' "$export_dir/kanama-web/export-report.json")"
  protocol="$(python3 -c '
import json, sys
print(json.load(open(sys.argv[1])).get("protocolVersion", ""))
' "$export_dir/kanama-web/export-report.json")"

  # (3) The artifact runs: drive it with the harness from the fresh clone.
  result_path=""
  driver_pass="null"
  if [[ "$SKIP_BROWSER" -eq 0 ]]; then
    result_path="$RESULT_DIR/$demo-$ENGINE.json"
    if run "$KANAMA_DIR/scripts/web_export_smoke.sh" \
      --engine "$ENGINE" \
      --export-dir "$export_dir" \
      --demo "$demo" \
      --result "$result_path"; then
      driver_pass="true"
    else
      echo "[web_fresh_checkout] $demo: export smoke FAILED on $ENGINE" >&2
      driver_pass="false"
      FAILED=1
    fi
  fi

  python3 -c '
import json, sys
out, demo, project, before, after, export_dir, payload, protocol, engine, result, passed = sys.argv[1:]
run = {
    "demo": demo,
    "projectDir": project or "(in-repo fixture)",
    "exportDir": export_dir,
    "sourceChecksumBefore": before or None,
    "sourceChecksumAfter": after or None,
    "sourceImmutable": (before == after) if before else None,
    "exportPayloadBytes": int(payload),
    "protocolVersion": int(protocol) if protocol else None,
    "engine": engine if result else None,
    "resultPath": result or None,
    "driverPass": {"true": True, "false": False, "null": None}[passed],
}
with open(out, "w") as handle:
    json.dump(run, handle, indent=2)
' "$RUNS_DIR/$(printf '%02d' "$RUN_INDEX")-$demo.json" \
  "$demo" "${project_subdir:-}" "$source_before" "$source_after" "$export_dir" \
  "$payload_bytes" "$protocol" "$ENGINE" "$result_path" "$driver_pass"
done

python3 - "$EVIDENCE" "$KANAMA_SOURCE" "$KANAMA_COMMIT" "$DEMOS_SOURCE" "$DEMOS_COMMIT" \
  "$GODOT_BIN" "$TEMPLATE" "$FAILED" "$RUNS_DIR" <<'PY'
import glob, json, os, subprocess, sys, datetime
(path, kanama_source, kanama_commit, demos_source, demos_commit, godot, template, failed,
 runs_dir) = sys.argv[1:]
runs = [json.load(open(name)) for name in sorted(glob.glob(os.path.join(runs_dir, "*.json")))]
godot_version = subprocess.run(
    [godot, "--headless", "--version"], capture_output=True, text=True
).stdout.strip().splitlines()[-1:]
evidence = {
    "schemaVersion": 1,
    "gate": "web-fresh-checkout",
    "generatedAt": datetime.datetime.now(datetime.timezone.utc).isoformat(timespec="seconds"),
    "kanama": {"source": kanama_source, "commit": kanama_commit},
    "demos": {"source": demos_source, "commit": demos_commit or None},
    "godot": {"binary": godot, "version": godot_version[0] if godot_version else None},
    "template": template,
    "runs": runs,
    "pass": failed == "0",
}
with open(path, "w") as handle:
    json.dump(evidence, handle, indent=2)
    handle.write("\n")
print(f"[web_fresh_checkout] evidence: {path}")
PY

if [[ "$FAILED" -ne 0 ]]; then
  echo "[web_fresh_checkout] FAIL" >&2
  if [[ "$CREATED_WORK_DIR" -eq 1 && "$KEEP_WORK_DIR" != "1" ]]; then
    echo "[web_fresh_checkout] re-run with --keep-work-dir to preserve $WORK_DIR" >&2
  fi
  exit 1
fi

echo "[web_fresh_checkout] PASS -- ${#DEMOS[@]} demo(s) exported from a fresh clone"

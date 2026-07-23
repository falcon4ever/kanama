#!/usr/bin/env bash
#
# web_export_smoke.sh -- production Web export smoke harness (Task 57f).
#
# Serves an ALREADY-BUILT Web export over an ephemeral localhost port, runs a
# per-browser driver that plays and tears down the demo, validates the driver's
# machine-readable result against the versioned schema, and proves the served
# tree was not mutated. It never builds the export (that is `exportWeb`), never
# downloads a browser, and never declares a browser green from page load alone.
#
# The shell owns the HTTP server and the driver process group; the driver owns
# its browser. On timeout or failure the shell kills only the processes it
# started and preserves logs and the result for diagnosis.
#
# Usage:
#   scripts/web_export_smoke.sh \
#       --engine <chrome|firefox|safari> \
#       --export-dir <dir> \
#       --demo <bunnymark|match3> \
#       --result <result.json> \
#       [--browser-binary <path>] \
#       [--timeout <seconds>] \
#       [--log-dir <dir>] \
#       [--driver-cmd "<command>"]   # override driver (scaffold self-test/advanced)
#
# Chrome is the intended CI gate; Firefox and Safari are explicit local release
# gates.

set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
WEB_DIR="$ROOT_DIR/scripts/web"

ENGINE=""
EXPORT_DIR=""
DEMO=""
RESULT=""
BROWSER_BINARY=""
TIMEOUT=300
LOG_DIR=""
DRIVER_CMD=""

die() {
  echo "web_export_smoke: $*" >&2
  exit 2
}

usage() {
  sed -n '2,32p' "${BASH_SOURCE[0]}" | sed 's/^# \{0,1\}//'
  exit "${1:-2}"
}

while [[ $# -gt 0 ]]; do
  case "$1" in
    --engine) ENGINE="${2:?}"; shift 2 ;;
    --export-dir) EXPORT_DIR="${2:?}"; shift 2 ;;
    --demo) DEMO="${2:?}"; shift 2 ;;
    --result) RESULT="${2:?}"; shift 2 ;;
    --browser-binary) BROWSER_BINARY="${2:?}"; shift 2 ;;
    --timeout) TIMEOUT="${2:?}"; shift 2 ;;
    --log-dir) LOG_DIR="${2:?}"; shift 2 ;;
    --driver-cmd) DRIVER_CMD="${2:?}"; shift 2 ;;
    -h|--help) usage 0 ;;
    *) die "unknown argument: $1" ;;
  esac
done

[[ -n "$EXPORT_DIR" ]] || die "--export-dir is required"
[[ -n "$DEMO" ]] || die "--demo is required"
[[ -n "$RESULT" ]] || die "--result is required"
[[ -d "$EXPORT_DIR" ]] || die "export dir not found: $EXPORT_DIR"
[[ -f "$EXPORT_DIR/index.html" ]] || die "export dir has no index.html: $EXPORT_DIR"
[[ "$TIMEOUT" =~ ^[0-9]+$ ]] || die "--timeout must be an integer number of seconds"

# Resolve absolute paths so nothing depends on the caller's working directory.
EXPORT_DIR="$(cd "$EXPORT_DIR" && pwd)"
mkdir -p "$(dirname "$RESULT")"
RESULT="$(cd "$(dirname "$RESULT")" && pwd)/$(basename "$RESULT")"

if [[ -z "$LOG_DIR" ]]; then
  LOG_DIR="$(dirname "$RESULT")"
fi
mkdir -p "$LOG_DIR"
LOG_DIR="$(cd "$LOG_DIR" && pwd)"
DRIVER_LOG="$LOG_DIR/$(basename "$RESULT" .json).driver.log"
SERVER_LOG="$LOG_DIR/$(basename "$RESULT" .json).server.log"

# Determine the driver command. Either an explicit override or the built-in
# per-engine driver. Real engines require --engine; the override path is for the
# scaffold self-test's fake fixture and does not require a known engine.
if [[ -z "$DRIVER_CMD" ]]; then
  [[ -n "$ENGINE" ]] || die "--engine is required unless --driver-cmd is given"
  driver_file=""
  case "$ENGINE" in
    chrome) driver_file="$WEB_DIR/drivers/chrome_cdp.mjs" ;;
    firefox) driver_file="$WEB_DIR/drivers/firefox_bidi.mjs" ;;
    safari) driver_file="$WEB_DIR/drivers/safari_webdriver.mjs" ;;
    *) die "unknown --engine: $ENGINE (expected chrome|firefox|safari)" ;;
  esac
  [[ -f "$driver_file" ]] || die "driver not yet formalized: $driver_file (Task 57f Phase 3)"
  DRIVER_CMD="node $driver_file"
fi

# --- source-tree immutability: checksum the served tree before the run. -------
tree_checksum() {
  # Deterministic checksum over sorted (relative-path, sha256) pairs. Portable
  # across macOS (shasum) and Linux CI (sha256sum) via python3.
  python3 - "$1" <<'PY'
import hashlib, os, sys
root = sys.argv[1]
digest = hashlib.sha256()
for dirpath, dirnames, filenames in os.walk(root):
    dirnames.sort()
    for name in sorted(filenames):
        path = os.path.join(dirpath, name)
        rel = os.path.relpath(path, root)
        digest.update(rel.encode("utf-8"))
        digest.update(b"\0")
        with open(path, "rb") as handle:
            for chunk in iter(lambda: handle.read(65536), b""):
                digest.update(chunk)
        digest.update(b"\0")
print(digest.hexdigest())
PY
}

CHECKSUM_BEFORE="$(tree_checksum "$EXPORT_DIR")"

# --- process bookkeeping: kill only what we start. ----------------------------
SERVER_PID=""
DRIVER_PID=""
FAILED=0

cleanup() {
  local status=$?
  # Terminate the driver's whole process group (it owns the browser it spawned).
  if [[ -n "$DRIVER_PID" ]] && kill -0 "$DRIVER_PID" 2>/dev/null; then
    kill -TERM -- "-$DRIVER_PID" 2>/dev/null || kill -TERM "$DRIVER_PID" 2>/dev/null || true
    for _ in 1 2 3 4 5 6 7 8 9 10; do
      kill -0 "$DRIVER_PID" 2>/dev/null || break
      sleep 0.2
    done
    kill -KILL -- "-$DRIVER_PID" 2>/dev/null || kill -KILL "$DRIVER_PID" 2>/dev/null || true
  fi
  if [[ -n "$SERVER_PID" ]] && kill -0 "$SERVER_PID" 2>/dev/null; then
    kill -TERM "$SERVER_PID" 2>/dev/null || true
    wait "$SERVER_PID" 2>/dev/null || true
  fi
  if [[ "$FAILED" -ne 0 || "$status" -ne 0 ]]; then
    echo "web_export_smoke: FAILED -- logs preserved:" >&2
    echo "  driver log: $DRIVER_LOG" >&2
    echo "  server log: $SERVER_LOG" >&2
    [[ -f "$RESULT" ]] && echo "  result:     $RESULT (may be partial)" >&2
  fi
}
trap cleanup EXIT

fail() {
  echo "web_export_smoke: $*" >&2
  FAILED=1
  exit 1
}

# --- serve the export on an ephemeral port. -----------------------------------
: >"$SERVER_LOG"
python3 "$WEB_DIR/serve_export.py" "$EXPORT_DIR" >"$SERVER_LOG" 2>&1 &
SERVER_PID=$!

PORT=""
for _ in $(seq 1 100); do
  if ! kill -0 "$SERVER_PID" 2>/dev/null; then
    fail "static server exited before announcing a port (see $SERVER_LOG)"
  fi
  PORT="$(sed -n 's/^PORT=//p' "$SERVER_LOG" | head -n1)"
  [[ -n "$PORT" ]] && break
  sleep 0.1
done
[[ -n "$PORT" ]] || fail "static server did not announce a port within 10s"
URL="http://127.0.0.1:$PORT/"
echo "web_export_smoke: serving $EXPORT_DIR at $URL (server pid $SERVER_PID)"

# --- run the driver under a finite timeout, in its own process group. ---------
: >"$DRIVER_LOG"
rm -f "$RESULT"

# The driver receives everything explicitly: no workstation-absolute assumptions.
# `setsid` puts it in its own process group so a timeout kill reaps the browser.
run_driver() {
  exec setsid bash -c '
    exec "$@"
  ' _ $DRIVER_CMD \
    --url "$URL" \
    --result "$RESULT" \
    --demo "$DEMO" \
    --timeout "$TIMEOUT" \
    --source-checksum "$CHECKSUM_BEFORE" \
    --export-dir "$EXPORT_DIR" \
    ${BROWSER_BINARY:+--browser-binary "$BROWSER_BINARY"}
}

# Fallback when setsid is unavailable (macOS lacks it by default): run directly.
if ! command -v setsid >/dev/null 2>&1; then
  run_driver() {
    # shellcheck disable=SC2086
    exec $DRIVER_CMD \
      --url "$URL" \
      --result "$RESULT" \
      --demo "$DEMO" \
      --timeout "$TIMEOUT" \
      --source-checksum "$CHECKSUM_BEFORE" \
      --export-dir "$EXPORT_DIR" \
      ${BROWSER_BINARY:+--browser-binary "$BROWSER_BINARY"}
  }
fi

( run_driver ) >"$DRIVER_LOG" 2>&1 &
DRIVER_PID=$!

# Enforce the finite timeout without a foreground `sleep` builtin dependency.
DEADLINE=$(( $(date +%s) + TIMEOUT + 15 ))  # driver has its own timeout; +15s grace
DRIVER_STATUS=""
while :; do
  if ! kill -0 "$DRIVER_PID" 2>/dev/null; then
    wait "$DRIVER_PID"; DRIVER_STATUS=$?
    break
  fi
  if [[ "$(date +%s)" -ge "$DEADLINE" ]]; then
    echo "web_export_smoke: driver exceeded ${TIMEOUT}s (+grace); terminating" >&2
    kill -TERM -- "-$DRIVER_PID" 2>/dev/null || kill -TERM "$DRIVER_PID" 2>/dev/null || true
    sleep 1
    kill -KILL -- "-$DRIVER_PID" 2>/dev/null || kill -KILL "$DRIVER_PID" 2>/dev/null || true
    DRIVER_PID=""
    fail "driver timed out after ${TIMEOUT}s (see $DRIVER_LOG)"
  fi
  sleep 0.5
done
DRIVER_PID=""

if [[ "$DRIVER_STATUS" -ne 0 ]]; then
  fail "driver exited with status $DRIVER_STATUS (see $DRIVER_LOG)"
fi

# --- validate the result envelope against the versioned schema. ---------------
[[ -f "$RESULT" ]] || fail "driver produced no result at $RESULT"
if ! python3 "$WEB_DIR/result_schema.py" "$RESULT"; then
  fail "result schema validation failed for $RESULT"
fi

# --- prove the served tree was not mutated by serving/driving. ----------------
CHECKSUM_AFTER="$(tree_checksum "$EXPORT_DIR")"
if [[ "$CHECKSUM_BEFORE" != "$CHECKSUM_AFTER" ]]; then
  fail "served export tree was mutated during the run (checksum drift)"
fi

# The driver must have echoed the pre-run checksum into the envelope, tying its
# own evidence to the tree the shell actually served.
ENVELOPE_CHECKSUM="$(python3 -c 'import json,sys; print(json.load(open(sys.argv[1]))["artifact"]["sourceTreeChecksum"])' "$RESULT")"
if [[ "$ENVELOPE_CHECKSUM" != "$CHECKSUM_BEFORE" ]]; then
  fail "result envelope checksum does not match the served tree"
fi

# --- overall pass gate. -------------------------------------------------------
OVERALL_PASS="$(python3 -c 'import json,sys; print("1" if json.load(open(sys.argv[1]))["pass"] else "0")' "$RESULT")"
if [[ "$OVERALL_PASS" != "1" ]]; then
  fail "driver reported pass=false (see $RESULT)"
fi

echo "web_export_smoke: PASS -- $DEMO on ${ENGINE:-custom} ($RESULT)"

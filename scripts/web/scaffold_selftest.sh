#!/usr/bin/env bash
#
# scaffold_selftest.sh -- exercises web_export_smoke.sh against a static fake
# fixture (Task 57f1 scaffold validation). It proves the shell's mechanics only:
# argument parsing, ephemeral port + process cleanup, timeout and error capture,
# result-schema validation, source-tree immutability, and the pass gate. It does
# NOT claim Bunnymark or Match3 pass -- those need real browsers (Task 57f
# Phase 3).

set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/../.." && pwd)"
SMOKE="$ROOT_DIR/scripts/web_export_smoke.sh"
FIXTURE="$ROOT_DIR/scripts/fixtures/web-fake-export"
FAKE_DRIVER="$FIXTURE/fake_driver.py"

WORK="$(mktemp -d "${TMPDIR:-/tmp}/kanama-web-scaffold.XXXXXX")"
cleanup() { rm -rf "$WORK"; }
trap cleanup EXIT

PASSES=0
FAILURES=0

note() { echo "  $*"; }
ok() { PASSES=$((PASSES + 1)); echo "PASS: $1"; }
bad() { FAILURES=$((FAILURES + 1)); echo "FAIL: $1" >&2; }

# Run one scenario: fresh fixture copy, run the shell with a fake-driver mode,
# assert the exit code, then assert no owned process leaked.
run_case() {
  local name="$1" expected_status="$2" mode="$3"; shift 3
  local case_dir="$WORK/$name"
  local export_dir="$case_dir/export"
  local result="$case_dir/result.json"
  mkdir -p "$export_dir"
  cp -R "$FIXTURE/index.html" "$FIXTURE/kanama-web-fake.js" "$export_dir/"

  local driver_cmd="python3 $FAKE_DRIVER --mode $mode"
  if [[ "$mode" == "mutate" ]]; then
    driver_cmd="$driver_cmd --mutate-path $export_dir/index.html"
  fi

  local status=0
  "$SMOKE" \
    --export-dir "$export_dir" \
    --demo "fake" \
    --result "$result" \
    --timeout 2 \
    --log-dir "$case_dir/logs" \
    --driver-cmd "$driver_cmd" \
    >"$case_dir/stdout.log" 2>"$case_dir/stderr.log" || status=$?

  if [[ "$status" -eq "$expected_status" ]]; then
    ok "$name (exit $status)"
  else
    bad "$name expected exit $expected_status, got $status"
    note "stderr: $(tail -n3 "$case_dir/stderr.log" 2>/dev/null || true)"
  fi

  # No owned process may survive: neither the static server for this export nor
  # the fake driver for this result.
  if pgrep -f "serve_export.py $export_dir" >/dev/null 2>&1; then
    bad "$name leaked a static server process"
  fi
  if pgrep -f "fake_driver.py.*$result" >/dev/null 2>&1; then
    bad "$name leaked a driver process"
  fi

  # On failure the shell must preserve logs and the result path must be reported.
  if [[ "$expected_status" -ne 0 ]]; then
    if [[ ! -f "$case_dir/logs/result.driver.log" ]]; then
      bad "$name did not preserve the driver log on failure"
    fi
  fi
}

echo "== scaffold self-test: web_export_smoke.sh =="

run_case success           0 success
run_case failed-assertion  1 fail
run_case malformed-result  1 malformed
run_case schema-violation  1 schema-violation
run_case wrong-checksum    1 wrong-checksum
run_case driver-crash      1 crash
run_case driver-timeout    1 timeout
run_case tree-mutation     1 mutate

# Success case must yield a schema-valid result on disk.
if [[ -f "$WORK/success/result.json" ]] \
   && python3 "$ROOT_DIR/scripts/web/result_schema.py" "$WORK/success/result.json" >/dev/null; then
  ok "success result validates against the schema"
else
  bad "success result missing or invalid"
fi

# Argument validation: missing --export-dir must be a usage error (exit 2).
usage_status=0
"$SMOKE" --demo fake --result "$WORK/x.json" --driver-cmd "python3 $FAKE_DRIVER --mode success" \
  >/dev/null 2>&1 || usage_status=$?
if [[ "$usage_status" -eq 2 ]]; then
  ok "missing --export-dir is a usage error (exit 2)"
else
  bad "missing --export-dir expected exit 2, got $usage_status"
fi

echo "== scaffold self-test: $PASSES passed, $FAILURES failed =="
[[ "$FAILURES" -eq 0 ]]

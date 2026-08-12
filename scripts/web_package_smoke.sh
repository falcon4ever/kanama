#!/usr/bin/env bash
#
# web_package_smoke.sh -- prove a packaged Web export zip IS the game.
#
# `packageWebExport` gates and zips an already-validated export, but a zip that
# was gated is still only a zip: the proof that it is complete and servable is
# serving THE UNZIPPED ARTIFACT and driving it like any export. This wrapper
# unzips the artifact into a scratch directory, asserts the unzip produced a
# non-empty tree rooted at index.html (an empty unzip passing vacuously is the
# "two empty dirs diff identical" trap), then hands that copy to
# scripts/web_export_smoke.sh -- which serves it with scripts/web/serve_export.py
# and runs the full driver + envelope-schema gate. Read the inner
# `web_export_smoke: PASS` line; this wrapper only adds the unzip step.
#
# Usage:
#   scripts/web_package_smoke.sh \
#       --zip <web-runtime/build/distributions/kanama-web-<demo>-v<version>.zip> \
#       --demo <demo key, as passed to packageWebExport> \
#       --engine <chrome|firefox|safari> \
#       --result <result.json> \
#       [--timeout <seconds>] \
#       [--keep-dir]              # preserve the scratch dir for diagnosis
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

ZIP=""
DEMO=""
ENGINE=""
RESULT=""
TIMEOUT=""
KEEP_DIR=0

die() {
  echo "web_package_smoke: $*" >&2
  exit 2
}

usage() {
  sed -n '2,25p' "${BASH_SOURCE[0]}" | sed 's/^# \{0,1\}//'
  exit "${1:-2}"
}

while [[ $# -gt 0 ]]; do
  case "$1" in
    --zip) ZIP="${2:?}"; shift 2 ;;
    --demo) DEMO="${2:?}"; shift 2 ;;
    --engine) ENGINE="${2:?}"; shift 2 ;;
    --result) RESULT="${2:?}"; shift 2 ;;
    --timeout) TIMEOUT="${2:?}"; shift 2 ;;
    --keep-dir) KEEP_DIR=1; shift ;;
    -h|--help) usage 0 ;;
    *) die "unknown argument: $1" ;;
  esac
done

[[ -n "$ZIP" ]] || die "--zip is required"
[[ -n "$DEMO" ]] || die "--demo is required"
[[ -n "$ENGINE" ]] || die "--engine is required"
[[ -n "$RESULT" ]] || die "--result is required"
[[ -f "$ZIP" ]] || die "zip not found: $ZIP (run :web-runtime:packageWebExport first)"
ZIP="$(cd "$(dirname "$ZIP")" && pwd)/$(basename "$ZIP")"

SCRATCH="$(mktemp -d "${TMPDIR:-/tmp}/kanama-web-package.XXXXXX")"
cleanup() {
  if [[ "$KEEP_DIR" -eq 1 ]]; then
    echo "web_package_smoke: scratch dir preserved at $SCRATCH"
  else
    rm -rf "$SCRATCH"
  fi
}
trap cleanup EXIT

EXPORT_COPY="$SCRATCH/export"
mkdir -p "$EXPORT_COPY"
unzip -q "$ZIP" -d "$EXPORT_COPY"

# Anti-vacuous assertions: a non-zero file count AND the path used, printed,
# before believing anything downstream.
FILE_COUNT="$(find "$EXPORT_COPY" -type f | wc -l | tr -d ' ')"
[[ "$FILE_COUNT" -gt 0 ]] || die "unzip of $ZIP produced no files at $EXPORT_COPY"
[[ -f "$EXPORT_COPY/index.html" ]] || \
  die "unzip of $ZIP has no index.html at the zip root ($EXPORT_COPY) -- itch.io requires it there"
echo "web_package_smoke: unzipped $ZIP -> $EXPORT_COPY ($FILE_COUNT files)"

# The full export smoke against the UNZIPPED COPY: it serves the copy with
# scripts/web/serve_export.py, drives the demo in a real browser, and gates the
# result envelope. A PASS here is the completeness proof for the zip.
"$ROOT_DIR/scripts/web_export_smoke.sh" \
  --engine "$ENGINE" \
  --export-dir "$EXPORT_COPY" \
  --demo "$DEMO" \
  --result "$RESULT" \
  ${TIMEOUT:+--timeout "$TIMEOUT"}

echo "web_package_smoke: PASS -- $ZIP served and driven as $DEMO on $ENGINE ($RESULT)"

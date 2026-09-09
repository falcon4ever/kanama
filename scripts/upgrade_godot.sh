#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
DRY_RUN=0
GODOT_DOCS_DIR="${GODOT_DOCS:-}"
GODOT_BIN=""

usage() {
  cat <<'EOF'
usage: scripts/upgrade_godot.sh [--dry-run] [--godot-docs /path/to/godot/doc/classes] /absolute/path/to/godot_binary

Runs the MECHANICAL steps of a Godot baseline bump in order, then stops at the
human-judgment boundary (device gates, support claims, release wording). The
full ordered process is docs/contributing/godot-upgrade.md.

Steps:
  1. preflight   pin/binary/policy/tool/tree checks (fail fast)
  2. refresh     scripts/refresh_godot_api.sh (API dump, jextract, validation,
                 virtual-signature table)
  3. classify    metadata-only vs real API change vs no-op
  4. constants   regenerate engine-wide name constants
  5. re-adopt    regenerate every committed generated wrapper on every platform
                 (desktop --emit-class, iOS --ios-emit-class + ObjectCallsGenerated)
                 and re-sync KDoc  [skipped with --dry-run]
  6. reports     refresh coverage + generator reports
  7. gates       full drift-gate, sync_kdoc --check, version-pin check

--dry-run validates the process against the CURRENT pin: all steps run in
check-only mode and the git tree must be byte-identical afterwards.

Requires: JEXTRACT_HOME (jextract 25+), a Godot doc/classes checkout at the
pinned version (--godot-docs, $GODOT_DOCS, or an auto-discovered checkout).

This script never edits support claims, CHANGELOG, badges, or templates, and
never runs device gates — those are human-judgment steps by design.
EOF
}

while [[ $# -gt 0 ]]; do
  case "$1" in
    --help|-h)
      usage
      exit 0
      ;;
    --dry-run)
      DRY_RUN=1
      shift
      ;;
    --godot-docs)
      GODOT_DOCS_DIR="$2"
      shift 2
      ;;
    --*)
      echo "[upgrade_godot] unknown option: $1" >&2
      usage
      exit 2
      ;;
    *)
      GODOT_BIN="$1"
      shift
      ;;
  esac
done

if [[ -z "$GODOT_BIN" ]]; then
  echo "[upgrade_godot] missing Godot binary" >&2
  usage
  exit 2
fi
if [[ ! -x "$GODOT_BIN" ]]; then
  echo "[upgrade_godot] Godot binary is not executable: $GODOT_BIN" >&2
  exit 2
fi

# ---------------------------------------------------------------------------
# Step 1: preflight — fail fast before any regeneration.
# ---------------------------------------------------------------------------
echo "[upgrade_godot] step 1: preflight"

PIN="$(sed -nE 's/^kanamaGodotVersion=(.+)$/\1/p' "$ROOT_DIR/gradle.properties")"
if [[ -z "$PIN" ]]; then
  echo "[upgrade_godot] kanamaGodotVersion missing from gradle.properties" >&2
  exit 1
fi

# Upgrade policy (maintainer decision, 2026-07-09): Kanama re-pins only on
# stable Godot releases. dev/beta/rc baselines are not accepted.
if [[ "$PIN" != *.stable ]]; then
  echo "[upgrade_godot] POLICY FAIL: kanamaGodotVersion=$PIN is not a stable release." >&2
  echo "[upgrade_godot] Kanama re-pins only on stable Godot releases (no dev/beta/rc baselines)." >&2
  exit 1
fi

BIN_VERSION="$("$GODOT_BIN" --version 2>/dev/null | grep -E '^[0-9]' | tail -n 1)"
if [[ "$BIN_VERSION" != "$PIN" && "$BIN_VERSION" != "$PIN".* ]]; then
  echo "[upgrade_godot] FAIL: binary reports '$BIN_VERSION' but kanamaGodotVersion=$PIN." >&2
  echo "[upgrade_godot] A bump starts by editing ONLY kanamaGodotVersion in gradle.properties" >&2
  echo "[upgrade_godot] (plus GODOT_VERSION in .github/workflows/package.yml, dash form)," >&2
  echo "[upgrade_godot] then running this script with the matching new stable binary." >&2
  exit 1
fi
echo "[upgrade_godot] pin=$PIN binary=$BIN_VERSION"

if [[ -z "${JEXTRACT_HOME:-}" || ! -x "${JEXTRACT_HOME:-}/bin/jextract" ]]; then
  echo "[upgrade_godot] FAIL: JEXTRACT_HOME must point at a jextract 25+ install (bin/jextract)" >&2
  exit 1
fi

# KDoc source tree: same discovery order as local_ci.sh.
if [[ -z "$GODOT_DOCS_DIR" ]]; then
  for candidate in "$HOME/godot-projects/godot/doc/classes" "$HOME/dev/godot/doc/classes"; do
    if [[ -d "$candidate" ]]; then
      GODOT_DOCS_DIR="$candidate"
      break
    fi
  done
fi
if [[ -z "$GODOT_DOCS_DIR" || ! -d "$GODOT_DOCS_DIR" ]]; then
  echo "[upgrade_godot] FAIL: no Godot doc/classes tree (use --godot-docs or set GODOT_DOCS)." >&2
  echo "[upgrade_godot] KDoc must be synced from the doc/classes matching the new pin" >&2
  echo "[upgrade_godot] (never a -rc/latest tree; see docs/contributing/wrapper-maintenance.md)." >&2
  exit 1
fi

# Guard against syncing KDoc from a mismatched Godot source tree.
DOCS_VERSION_PY="$(cd "$GODOT_DOCS_DIR/../.." 2>/dev/null && pwd)/version.py"
if [[ -f "$DOCS_VERSION_PY" ]]; then
  DOCS_VERSION="$(python3 - "$DOCS_VERSION_PY" <<'EOF'
import re, sys
text = open(sys.argv[1]).read()
fields = {k: v.strip('"') for k, v in re.findall(r'^(major|minor|patch|status)\s*=\s*(.+)$', text, re.MULTILINE)}
# Godot spells a .0 release without the patch component (4.7.stable) and a maintenance
# release with it (4.7.2.stable); the pin follows the same convention.
patch = fields.get('patch', '0')
version = f"{fields.get('major','?')}.{fields.get('minor','?')}"
if patch not in ('0', '?'):
    version += f".{patch}"
print(f"{version}.{fields.get('status','?')}")
EOF
)"
  if [[ "$DOCS_VERSION" != "$PIN" ]]; then
    echo "[upgrade_godot] FAIL: doc/classes tree is Godot $DOCS_VERSION but the pin is $PIN." >&2
    echo "[upgrade_godot] Check out the matching stable tag in the Godot source tree first." >&2
    exit 1
  fi
  echo "[upgrade_godot] kdoc source: $GODOT_DOCS_DIR (version $DOCS_VERSION)"
else
  echo "[upgrade_godot] FAIL: cannot verify the doc/classes version — no version.py two levels up from $GODOT_DOCS_DIR." >&2
  echo "[upgrade_godot] Point --godot-docs at doc/classes inside a real Godot source checkout at the pinned tag;" >&2
  echo "[upgrade_godot] an unverifiable docs tree risks exactly the KDoc version skew this gate exists to prevent." >&2
  exit 1
fi

# The regen must start from a reviewable state: no tracked modifications except
# the two human pin edits (gradle.properties + CI workflow; none in a dry run).
# Untracked files don't affect the pipeline and are allowed; the dry run instead
# asserts the tree state is unchanged at the end.
STATUS_BEFORE="$(git -C "$ROOT_DIR" status --porcelain)"
if [[ $DRY_RUN -eq 1 ]]; then
  allowed_dirty="^$"
else
  allowed_dirty="^..[[:space:]]+(gradle\.properties|\.github/workflows/package\.yml)$"
fi
unexpected="$(printf '%s\n' "$STATUS_BEFORE" | grep -v '^??' | grep -vE "$allowed_dirty" | grep -v '^$' || true)"
if [[ -n "$unexpected" ]]; then
  echo "[upgrade_godot] FAIL: working tree has unexpected tracked changes:" >&2
  printf '%s\n' "$unexpected" >&2
  echo "[upgrade_godot] commit or stash them first (a bump diff must stay reviewable)." >&2
  exit 1
fi

echo "[upgrade_godot] version pin consistency (fail fast before regenerating)"
python3 "$ROOT_DIR/scripts/check_godot_version_pin.py" || {
  echo "[upgrade_godot] fix the pins above (gradle.properties is the single source; CI uses the dash form)" >&2
  exit 1
}

# ---------------------------------------------------------------------------
# Step 2: refresh API/header inputs (dump, jextract, validate, virtual table).
# ---------------------------------------------------------------------------
echo "[upgrade_godot] step 2: refresh API/header inputs"
"$ROOT_DIR/scripts/refresh_godot_api.sh" "$GODOT_BIN"

# ---------------------------------------------------------------------------
# Step 3: classify the API diff (metadata-only vs real API change).
# ---------------------------------------------------------------------------
echo "[upgrade_godot] step 3: classify the API diff vs HEAD"
CLASSIFICATION="$(python3 - "$ROOT_DIR" <<'EOF'
import json, subprocess, sys
from pathlib import Path

root = Path(sys.argv[1])
old = json.loads(subprocess.run(
    ["git", "-C", str(root), "show", "HEAD:extension_api.json"],
    check=True, capture_output=True).stdout)
new = json.loads((root / "extension_api.json").read_text(encoding="utf-8"))

if old == new:
    print("classification=none")
    print("  extension_api.json is identical to HEAD (no-op bump)")
    sys.exit(0)

old_body = {k: v for k, v in old.items() if k != "header"}
new_body = {k: v for k, v in new.items() if k != "header"}
if old_body == new_body:
    print("classification=metadata-only")
    print(f"  header only: {old.get('header')} -> {new.get('header')}")
    print("  expect: regen/KDoc/report churn limited to version strings")
    sys.exit(0)

print("classification=api-change")
old_classes = {c["name"]: c for c in old.get("classes", [])}
new_classes = {c["name"]: c for c in new.get("classes", [])}
added = sorted(set(new_classes) - set(old_classes))
removed = sorted(set(old_classes) - set(new_classes))
changed = sorted(n for n in set(old_classes) & set(new_classes)
                 if old_classes[n] != new_classes[n])
print(f"  classes: +{len(added)} -{len(removed)} ~{len(changed)}")
for label, names in (("added", added), ("removed", removed)):
    for n in names[:20]:
        print(f"    {label}: {n}")
    if len(names) > 20:
        print(f"    {label}: ... {len(names) - 20} more")
other = sorted(k for k in set(old_body) | set(new_body)
               if old_body.get(k) != new_body.get(k) and k != "classes")
if other:
    print(f"  other changed sections: {', '.join(other)}")
print("  expect: wrapper regen churn; NEW classes are coverage/policy work and are NOT auto-adopted")
EOF
)"
printf '%s\n' "$CLASSIFICATION"

if [[ $DRY_RUN -eq 1 ]] && ! printf '%s' "$CLASSIFICATION" | grep -q '^classification=none'; then
  echo "[upgrade_godot] FAIL: --dry-run expects a no-op API diff on the current pin" >&2
  exit 1
fi

# ---------------------------------------------------------------------------
# Step 4: engine-wide name constants.
# ---------------------------------------------------------------------------
if [[ $DRY_RUN -eq 1 ]]; then
  echo "[upgrade_godot] step 4: name constants (--check)"
  python3 "$ROOT_DIR/scripts/generate_name_constants.py" --check
else
  echo "[upgrade_godot] step 4: regenerate name constants"
  python3 "$ROOT_DIR/scripts/generate_name_constants.py"
fi

# ---------------------------------------------------------------------------
# Step 5: re-adopt every committed generated wrapper (all platforms) + KDoc.
# The drift-gate in step 7 is the authority: committed == fresh regen.
# ---------------------------------------------------------------------------
if [[ $DRY_RUN -eq 1 ]]; then
  echo "[upgrade_godot] step 5: skip re-adopt (--dry-run; the drift-gate performs the same full regen check-only)"
else
  echo "[upgrade_godot] step 5: re-adopt generated wrappers (desktop + iOS)"
  (cd "$ROOT_DIR" && PYTHONPATH="$ROOT_DIR/scripts" python3 - <<'EOF'
import json, shutil, subprocess, sys
from check_wrapper_generator import (
    ROOT, API_DIR, IOS_API_DIR, DESKTOP_HANDSHAPED, IOS_HANDSHAPED, _api_class_names,
)
from generate_api_wrapper import IOS_HANDWRITTEN_COLLISION_CLASSES

IOS_OBJECTCALLS = ROOT / "ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/binding/runtime/ObjectCallsGenerated.kt"


def batch(flag: str, classes: list[str], *extra: str) -> None:
    args = [sys.executable, str(ROOT / "scripts/generate_api_wrapper.py")]
    for name in classes:
        args += [flag, name]
    args += list(extra)
    subprocess.run(args, cwd=ROOT, check=True, stdout=subprocess.DEVNULL)


api = _api_class_names()
# "Removed from the API" means it was a class in the PREVIOUS dump and is not one now —
# not "a .kt file in api/ that is not a Godot class" (GD, GodotObject, the handle aliases
# are hand-written non-API files and are not removals).
old_dump = subprocess.run(
    ["git", "-C", str(ROOT), "show", "HEAD:extension_api.json"],
    check=True, capture_output=True, text=True,
).stdout
old_api = {c["name"] for c in json.loads(old_dump).get("classes", [])}

committed = {p.stem for p in API_DIR.glob("*.kt")}
removed = sorted((committed & old_api) - api)
targets = sorted((committed & api) - DESKTOP_HANDSHAPED)
print(f"[upgrade_godot] desktop re-adopt: {len(targets)} classes "
      f"(hand-shaped exempt: {len(DESKTOP_HANDSHAPED & committed)})")
batch("--emit-class", targets, "--allow-overwrite")

# iOS: the emit UNION must include IOS_HANDSHAPED. The generator emits a method returning a
# wrapper type only when that class is in the active set (AGENTS.md "Looks Wrong But Isn't"),
# so emitting just the non-hand-shaped targets silently drops every method returning Image
# & co. and shrinks ObjectCallsGenerated.kt. Do what the drift-gate does: emit the full union
# into a scratch dir, then copy back only the files that are ours to overwrite.
ios_committed = {p.stem for p in IOS_API_DIR.glob("*.kt")}
ios_removed = sorted((ios_committed & old_api) - api)
ios_emit = sorted((ios_committed & api) - set(IOS_HANDWRITTEN_COLLISION_CLASSES))
ios_targets = sorted(set(ios_emit) - IOS_HANDSHAPED)
scratch = ROOT / "build/upgrade-godot/ios-regen"
shutil.rmtree(scratch, ignore_errors=True)
scratch.mkdir(parents=True)
class_list = scratch / "_class_list.txt"
class_list.write_text("\n".join(ios_emit) + "\n", encoding="utf-8")
print(f"[upgrade_godot] iOS re-adopt: {len(ios_targets)} classes + ObjectCallsGenerated.kt "
      f"(emit union {len(ios_emit)}, incl. {len(IOS_HANDSHAPED)} hand-shaped kept as committed)")
subprocess.run(
    [sys.executable, str(ROOT / "scripts/generate_api_wrapper.py"),
     "--ios-class-list-file", str(class_list),
     "--ios-output-dir", str(scratch),
     "--ios-objectcalls", str(scratch / "ObjectCallsGenerated.kt")],
    cwd=ROOT, check=True, stdout=subprocess.DEVNULL,
)
missing = [c for c in ios_targets if not (scratch / f"{c}.kt").exists()]
if missing:
    sys.exit(f"[upgrade_godot] FAIL: the iOS regen did not emit {missing[:20]} (unexpected collision/skip)")
for c in ios_targets:
    shutil.copyfile(scratch / f"{c}.kt", IOS_API_DIR / f"{c}.kt")
shutil.copyfile(scratch / "ObjectCallsGenerated.kt", IOS_OBJECTCALLS)

new_classes = sorted(api - committed)
print(f"[upgrade_godot] classes in the new API without a committed wrapper: {len(new_classes)} "
      f"(coverage/policy work — deliberately not auto-adopted)")
for scope, names in (("desktop", removed), ("ios", ios_removed)):
    if names:
        print(f"[upgrade_godot] HUMAN: {scope} wrappers for classes REMOVED from the API "
              f"(delete or hand-shape deliberately): {', '.join(names)}")
EOF
  )

  echo "[upgrade_godot] step 5: re-sync KDoc from Godot doc/classes"
  python3 "$ROOT_DIR/scripts/sync_kdoc_from_godot_docs.py" --godot-docs "$GODOT_DOCS_DIR" --write
fi

# ---------------------------------------------------------------------------
# Step 6: coverage + generator reports.
# ---------------------------------------------------------------------------
if [[ $DRY_RUN -eq 1 ]]; then
  echo "[upgrade_godot] step 6: reports (--check)"
  python3 "$ROOT_DIR/scripts/api_wrapper_coverage.py" --markdown "$ROOT_DIR/docs/contributing/api-coverage.md" --check
  python3 "$ROOT_DIR/scripts/api_wrapper_generator_report.py" --markdown "$ROOT_DIR/docs/contributing/wrapper-generator-report.md" --check
else
  echo "[upgrade_godot] step 6: refresh coverage + generator reports"
  python3 "$ROOT_DIR/scripts/api_wrapper_coverage.py" --markdown "$ROOT_DIR/docs/contributing/api-coverage.md"
  python3 "$ROOT_DIR/scripts/api_wrapper_generator_report.py" --markdown "$ROOT_DIR/docs/contributing/wrapper-generator-report.md"
fi

# ---------------------------------------------------------------------------
# Step 7: gates. Never finish a bump with any of these red.
# ---------------------------------------------------------------------------
echo "[upgrade_godot] step 7: gate — full drift-gate (committed == fresh regen, per platform)"
python3 "$ROOT_DIR/scripts/check_wrapper_generator.py"

echo "[upgrade_godot] step 7: gate — KDoc staleness"
python3 "$ROOT_DIR/scripts/sync_kdoc_from_godot_docs.py" --godot-docs "$GODOT_DOCS_DIR" --check

echo "[upgrade_godot] step 7: gate — Godot version pin consistency"
python3 "$ROOT_DIR/scripts/check_godot_version_pin.py"

if [[ $DRY_RUN -eq 1 ]]; then
  STATUS_AFTER="$(git -C "$ROOT_DIR" status --porcelain)"
  if [[ "$STATUS_AFTER" != "$STATUS_BEFORE" ]]; then
    echo "[upgrade_godot] FAIL: --dry-run changed the tree:" >&2
    diff <(printf '%s\n' "$STATUS_BEFORE") <(printf '%s\n' "$STATUS_AFTER") >&2 || true
    exit 1
  fi
  echo "[upgrade_godot] dry run PASS — no-op bump left the tree byte-identical and all gates green"
fi

# ---------------------------------------------------------------------------
# Human-judgment boundary. The script stops here by design.
# ---------------------------------------------------------------------------
cat <<EOF
[upgrade_godot] mechanical steps complete — STOPPING at the human-judgment boundary.
[upgrade_godot] Remaining steps (full runbook: docs/contributing/godot-upgrade.md):
  1. Review the API diff classification above. New classes are coverage/policy
     work (generator policy, audits, adoption) — never bulk-adopt them blindly.
  2. Run local CI against the new binary: scripts/local_ci.sh $GODOT_BIN
  3. Re-run the platform smoke/device gates per the release-gate matrix (§6) in
     the internal task repo. This script does NOT change support claims; support
     wording moves only after the matching gate passes.
  4. Refresh release-facing wording by hand: CHANGELOG.md, README.md, docs
     version badges/wording, templates, companion demo defaults. Review the
     .gdextension compatibility_minimum invariant before touching it
     (see AGENTS.md "Looks Wrong But Isn't").
  5. Bump the KDoc docs-pin commit hash in docs/contributing/wrapper-maintenance.md.
  6. Before tagging: scripts/fresh_clone_smoke.sh $GODOT_BIN
EOF

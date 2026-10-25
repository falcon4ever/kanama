#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
# shellcheck source=scripts/portable.sh
source "$ROOT_DIR/scripts/portable.sh"

MODE=bump # bump | dry-run | plan
PRERELEASE=0
GODOT_DOCS_DIR="${GODOT_DOCS:-}"
GODOT_BIN=""
TEMPLATES=""
# Gradle runs twice (ktfmtFormat, ktfmtCheck), through the build daemon as local_ci.sh does: a
# --no-daemon build waits on the Gradle home's journal lock that an idle daemon holds and times out.
# A hung build fails the step instead of waiting forever.
GRADLE_TIMEOUT="${KANAMA_UPGRADE_GRADLE_TIMEOUT:-1200}"

usage() {
  cat <<'EOF'
usage: scripts/upgrade_godot.sh [--plan | --dry-run] [--prerelease-trial]
                                [--godot-docs /path/to/godot/doc/classes]
                                [--templates /path/to/export_templates.tpz]
                                /absolute/path/to/godot_binary

Runs every MECHANICAL step of a Godot baseline bump in order, then stops at the
human-judgment boundary (device gates, support claims, release wording). The
full ordered process is docs/contributing/godot-upgrade.md.

Steps:
  1. preflight   pin/binary/policy/tool/tree/template checks (fail fast)
  2. refresh     scripts/refresh_godot_api.sh --skip-validate (API dump, header, jextract,
                 virtual-signature table)
  3. classify    no-op vs metadata-only vs API change; added/removed classes and members
  4. constants   engine-wide name constants
  5. tree        the generated wrapper tree (generate_api_wrapper.py --write-tree: re-adopts every
                 class, adopts the classes the new API adds, sets the desktop ObjectCalls `actual`
                 set)
  6. outputs     every other generated output: builtin ops, engine-method table, Web backend,
                 Web wrappers, Web exercise, API exercise, generator/coverage reports,
                 enum migration table, iOS handwritten report; then the KDoc sync
  7. format      ./gradlew ktfmtFormat (the regen leaves the iOS region, the expect and toggled
                 desktop members unformatted)
  8. gates index docs/reference/generated/gates.md (last: it reads the gate list and the ledger)
  9. gates       every drift gate and API check, run to the end and summarised: API validation
                 (after the re-adopt, so a removed method is an inventory line, not a stop), the
                 wrapper drift gate, KDoc, pins, the --check form of every generator above. Two
                 more are listed as human decisions: public signature changes (CHANGELOG source
                 breaks) and the gate-evidence ledger (every entry goes stale on a pin move).

Modes:
  (default)   the bump: run every step. Start from a clean tree plus the pin edit (the files
              `scripts/check_godot_version_pin.py --list-files` names).
  --plan      run the read-only preflight checks and print the steps and commands; change nothing.
  --dry-run   prove the pipeline on the CURRENT pin: a clean tree, the same steps in write mode,
              a no-op API diff, every gate green, and a byte-identical tree afterwards.

--prerelease-trial   allow a beta/rc pin (4.8.beta1), for a trial on a throwaway branch. Kanama
                     re-pins only on stable releases; never merge a prerelease pin.
--templates PATH     the export templates .tpz (or the directory holding it): installed into the
                     templates folder for the pin when it is missing, checked against the pin when not.

Requires: JEXTRACT_HOME (jextract 25+), a Godot doc/classes checkout at the
pinned version (--godot-docs, $GODOT_DOCS, or an auto-discovered checkout).
Every Godot call runs under a timeout (KANAMA_GODOT_DUMP_TIMEOUT, default 300 s), the Gradle
call under KANAMA_UPGRADE_GRADLE_TIMEOUT (default 1200 s).

This script never edits support claims, CHANGELOG, badges, or templates, and
never runs device gates — those are human-judgment steps by design.
EOF
}

while [[ $# -gt 0 ]]; do
  case "$1" in
    --help | -h)
      usage
      exit 0
      ;;
    --dry-run)
      MODE=dry-run
      shift
      ;;
    --plan)
      MODE=plan
      shift
      ;;
    --prerelease-trial)
      PRERELEASE=1
      shift
      ;;
    --godot-docs)
      GODOT_DOCS_DIR="${2:?--godot-docs needs a directory}"
      shift 2
      ;;
    --templates)
      TEMPLATES="${2:?--templates needs a .tpz or a directory}"
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

log() { echo "[upgrade_godot] $*"; }

# ---------------------------------------------------------------------------
# Step 1: preflight — read-only checks. Every failure is listed, then the run stops before
# anything is regenerated (in --plan mode the plan is printed anyway).
# ---------------------------------------------------------------------------
PREFLIGHT_FAILURES=()
preflight_fail() {
  PREFLIGHT_FAILURES+=("$1")
  echo "[upgrade_godot] PREFLIGHT FAIL: $1" >&2
}

log "step 1: preflight (mode=$MODE)"

PIN="$(sed -nE 's/^kanamaGodotVersion=(.+)$/\1/p' "$ROOT_DIR/gradle.properties" | tr -d '[:space:]')"
if [[ -z "$PIN" ]]; then
  echo "[upgrade_godot] kanamaGodotVersion missing from gradle.properties" >&2
  exit 1
fi

# Upgrade policy (maintainer decision, 2026-07-09): Kanama re-pins only on stable Godot releases.
# --prerelease-trial lets a beta/rc pin through for a trial branch (task 139: the RC re-run).
if [[ "$PIN" == *.stable ]]; then
  :
elif [[ $PRERELEASE -eq 1 && "$PIN" =~ \.(beta|rc)[0-9]+$ ]]; then
  log "prerelease trial: kanamaGodotVersion=$PIN (a trial branch only; never merge a prerelease pin)"
else
  preflight_fail "kanamaGodotVersion=$PIN is not a stable release. Kanama re-pins only on stable Godot releases; --prerelease-trial allows a beta/rc pin on a trial branch"
fi

# justified: a binary that prints no version leaves BIN_VERSION empty, which fails the pin comparison just below.
BIN_VERSION="$(run_with_timeout "${KANAMA_GODOT_DUMP_TIMEOUT:-300}" "$GODOT_BIN" --version 2>/dev/null | grep -E '^[0-9]' | tail -n 1 || true)"
if [[ "$BIN_VERSION" != "$PIN" && "$BIN_VERSION" != "$PIN".* ]]; then
  preflight_fail "binary reports '$BIN_VERSION' but kanamaGodotVersion=$PIN. A bump starts by editing the pin files ($(python3 "$ROOT_DIR/scripts/check_godot_version_pin.py" --list-files | tr '\n' ' ')) and runs this script with the matching binary"
else
  log "pin=$PIN binary=$BIN_VERSION"
fi

if [[ -z "${JEXTRACT_HOME:-}" || ! -x "${JEXTRACT_HOME:-}/bin/jextract" ]]; then
  preflight_fail "JEXTRACT_HOME must point at a jextract 25+ install (bin/jextract)"
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
  preflight_fail "no Godot doc/classes tree (use --godot-docs or set GODOT_DOCS); KDoc syncs from the doc/classes matching the new pin"
else
  # Guard against syncing KDoc from a mismatched Godot source tree.
  # justified: an unreachable docs dir yields a version.py path that does not exist, which fails loudly just below.
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
    # A prerelease source tree spells its status without the number (version.py says `beta`; the
    # number comes from the build environment), so 4.8.beta matches the pin 4.8.beta1.
    if [[ "$DOCS_VERSION" == "$PIN" ]] || [[ $PRERELEASE -eq 1 && "$PIN" =~ ^${DOCS_VERSION//./\\.}[0-9]+$ ]]; then
      log "kdoc source: $GODOT_DOCS_DIR (version $DOCS_VERSION)"
    else
      preflight_fail "doc/classes tree is Godot $DOCS_VERSION but the pin is $PIN; check out the matching tag in the Godot source tree"
    fi
  else
    preflight_fail "cannot verify the doc/classes version: no version.py two levels up from $GODOT_DOCS_DIR (point --godot-docs at doc/classes inside a Godot source checkout at the pinned tag)"
  fi
fi

# The regen must start from a reviewable state: no tracked modification except the pin edit,
# whose files the pin check names (none in a dry run). Untracked files are allowed; the dry run
# asserts the whole status is unchanged at the end.
STATUS_BEFORE="$(git -C "$ROOT_DIR" status --porcelain)"
PIN_FILES_RE="$(python3 "$ROOT_DIR/scripts/check_godot_version_pin.py" --list-files | sed 's/[.]/\\./g' | tr '\n' '|' | sed 's/|$//')"
if [[ "$MODE" == dry-run ]]; then
  allowed_dirty="^$"
else
  allowed_dirty="^..[[:space:]]+($PIN_FILES_RE)$"
fi
# justified: grep -v exits 1 when it filters every line out, which is the clean case (no unexpected change).
unexpected="$(printf '%s\n' "$STATUS_BEFORE" | grep -v '^??' | grep -vE "$allowed_dirty" | grep -v '^$' || true)"
if [[ -n "$unexpected" ]]; then
  preflight_fail "working tree has tracked changes besides the pin edit (commit them first; a bump diff must stay reviewable):
$unexpected"
fi

# The header check needs the new dump, so it runs in step 9; here only the pins themselves.
if ! python3 "$ROOT_DIR/scripts/check_godot_version_pin.py" --skip-api-header; then
  preflight_fail "the Godot pins disagree (gradle.properties is the single source; CI uses the dash form)"
fi

# Export templates: the mechanical steps do not need them, the gates after the bump do.
templates_root="${KANAMA_GODOT_TEMPLATES_DIR:-}"
if [[ -z "$templates_root" ]]; then
  if [[ -d "$HOME/Library/Application Support/Godot/export_templates" ]]; then
    templates_root="$HOME/Library/Application Support/Godot/export_templates"
  else
    templates_root="${XDG_DATA_HOME:-$HOME/.local/share}/godot/export_templates"
  fi
fi
templates_dir="$templates_root/$PIN"
TEMPLATES_INSTALL=""
if [[ -n "$TEMPLATES" && -d "$TEMPLATES" ]]; then
  # justified: no .tpz in the directory leaves the path empty, which the -f test below reports.
  TEMPLATES="$(find "$TEMPLATES" -maxdepth 1 -name '*.tpz' | head -n 1 || true)"
fi
if [[ -f "$templates_dir/version.txt" ]]; then
  installed="$(tr -d '[:space:]' <"$templates_dir/version.txt")"
  if [[ "$installed" == "$PIN" ]]; then
    log "export templates: $templates_dir (version.txt $installed)"
  else
    preflight_fail "export templates at $templates_dir carry version.txt '$installed', not $PIN"
  fi
elif [[ -d "$templates_dir" ]]; then
  log "export templates: $templates_dir (no version.txt to compare; installed by hand?)"
elif [[ -n "$TEMPLATES" ]]; then
  if [[ ! -f "$TEMPLATES" ]]; then
    preflight_fail "--templates: no .tpz found at the given path"
  else
    # justified: an archive without templates/version.txt leaves the version empty, which the comparison below rejects.
    tpz_version="$(unzip -p "$TEMPLATES" templates/version.txt 2>/dev/null | tr -d '[:space:]' || true)"
    if [[ "$tpz_version" != "$PIN" ]]; then
      preflight_fail "--templates $TEMPLATES holds version '$tpz_version', not $PIN"
    else
      TEMPLATES_INSTALL="$TEMPLATES"
      log "export templates: $templates_dir is missing; will install $TEMPLATES"
    fi
  fi
else
  echo "[upgrade_godot] WARN export templates for $PIN are not installed ($templates_dir); the mechanical steps do not need them, the export gates after the bump do (pass --templates <tpz> to install them)" >&2
fi

# ---------------------------------------------------------------------------
# The plan. Each step is a function; `run_hard` stops the bump on a failure (later steps would
# render from a broken input), `run_soft` records it and carries on so one run lists everything
# that is left. In --plan mode both only print the command.
# ---------------------------------------------------------------------------
SOFT_FAILURES=()
HUMAN_ITEMS=()
STEP=""

quote_cmd() {
  local out="" arg
  for arg in "$@"; do
    case "$arg" in
      "$ROOT_DIR") arg="." ;;
      "$ROOT_DIR"/*) arg="${arg#"$ROOT_DIR"/}" ;;
      "${BASELINE_API:-}") arg="<HEAD:extension_api.json>" ;;
    esac
    if [[ "$arg" == *[[:space:]]* ]]; then
      out+=" '$arg'"
    else
      out+=" $arg"
    fi
  done
  printf '%s' "${out# }"
}

run_hard() {
  if [[ "$MODE" == plan ]]; then
    echo "      $(quote_cmd "$@")"
    return 0
  fi
  local rc=0
  (cd "$ROOT_DIR" && "$@") || rc=$?
  if [[ $rc -ne 0 ]]; then
    echo "[upgrade_godot] FAIL $STEP: '$(quote_cmd "$@")' exited $rc" >&2
    echo "[upgrade_godot] the steps after this one render from its output, so the bump stops here." >&2
    if [[ "$MODE" == dry-run ]]; then
      echo "[upgrade_godot] the dry run may have left changes: git -C \"$ROOT_DIR\" status" >&2
    fi
    exit 1
  fi
}

# run_soft <label> <cmd...>: a failure is recorded under <label> and the run continues.
run_soft() {
  local label="$1"
  shift
  if [[ "$MODE" == plan ]]; then
    echo "      $(quote_cmd "$@")"
    return 0
  fi
  local rc=0
  (cd "$ROOT_DIR" && "$@") || rc=$?
  if [[ $rc -ne 0 ]]; then
    echo "[upgrade_godot] FAIL ($STEP) $label: exit $rc" >&2
    SOFT_FAILURES+=("$label  [$(quote_cmd "$@")]")
  fi
}

# run_human <label> <cmd...>: a check whose failure on a real bump is a decision for a person
# (announce a source break, re-run device evidence), listed separately. In a dry run it is a gate.
run_human() {
  local label="$1"
  shift
  if [[ "$MODE" == plan ]]; then
    echo "      $(quote_cmd "$@")   (a failure is a human decision)"
    return 0
  fi
  local rc=0
  (cd "$ROOT_DIR" && "$@") || rc=$?
  if [[ $rc -ne 0 ]]; then
    if [[ "$MODE" == dry-run ]]; then
      SOFT_FAILURES+=("$label  [$(quote_cmd "$@")]")
    else
      HUMAN_ITEMS+=("$label  [$(quote_cmd "$@")]")
    fi
  fi
}

step() {
  STEP="$1"
  if [[ "$MODE" == plan ]]; then
    echo "  $1"
  else
    log "$1"
  fi
}

if [[ ${#PREFLIGHT_FAILURES[@]} -gt 0 ]]; then
  echo "[upgrade_godot] preflight: ${#PREFLIGHT_FAILURES[@]} problem(s) above; nothing was changed." >&2
  if [[ "$MODE" != plan ]]; then
    exit 1
  fi
fi

if [[ "$MODE" == plan ]]; then
  echo "[upgrade_godot] plan (nothing below runs in --plan mode):"
fi

if [[ -n "$TEMPLATES_INSTALL" ]]; then
  step "step 1: install the export templates for $PIN"
  install_templates() {
    local tmp
    tmp="$(mktemp -d "${TMPDIR:-/tmp}/kanama-templates.XXXXXX")"
    unzip -q "$TEMPLATES_INSTALL" -d "$tmp" && mkdir -p "$templates_root" && mv "$tmp/templates" "$templates_dir"
    local rc=$?
    rm -rf "$tmp"
    return $rc
  }
  if [[ "$MODE" == plan ]]; then
    echo "      unzip $TEMPLATES_INSTALL templates/ -> $templates_dir"
  else
    run_hard install_templates
  fi
fi

# Step 2: refresh the API/header inputs. Validation waits for step 9 (task 139 U3): the committed
# wrappers still name what the new API removed until step 5 re-adopts them.
step "step 2: refresh API/header inputs (dump, header, jextract, virtual table)"
run_hard "$ROOT_DIR/scripts/refresh_godot_api.sh" --skip-validate "$GODOT_BIN"

# Step 3: classify the API diff against HEAD and list what the new Godot removed.
BASELINE_API="$(mktemp "${TMPDIR:-/tmp}/kanama-baseline-api.XXXXXX")"
trap 'rm -f "$BASELINE_API"' EXIT
git -C "$ROOT_DIR" show HEAD:extension_api.json >"$BASELINE_API"
step "step 3: classify the API diff vs HEAD"
classify() {
  python3 - "$ROOT_DIR/extension_api.json" "$BASELINE_API" <<'EOF'
import json, sys

new = json.load(open(sys.argv[1], encoding="utf-8"))
old = json.load(open(sys.argv[2], encoding="utf-8"))
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
changed = sorted(n for n in set(old_classes) & set(new_classes) if old_classes[n] != new_classes[n])
print(f"  classes: +{len(added)} -{len(removed)} ~{len(changed)} (added classes are adopted in step 5)")
for label, names in (("added", added), ("removed", removed)):
    for n in names:
        print(f"    {label}: {n}")
# Removed members are an inventory, not a stop: step 5 drops them from the wrappers, step 9's
# signature gate lists the source break, and the CHANGELOG line is a human step.
for kind in ("methods", "properties", "signals", "constants", "enums"):
    lost = []
    for n in sorted(set(old_classes) & set(new_classes)):
        before = {m["name"] for m in old_classes[n].get(kind) or []}
        after = {m["name"] for m in new_classes[n].get(kind) or []}
        lost += [f"{n}.{m}" for m in sorted(before - after)]
    if lost:
        print(f"  removed {kind}: {', '.join(lost)}")
other = sorted(k for k in set(old_body) | set(new_body) if old_body.get(k) != new_body.get(k) and k != "classes")
if other:
    print(f"  other changed sections: {', '.join(other)}")
EOF
}
if [[ "$MODE" == plan ]]; then
  echo "      (diff extension_api.json against HEAD; lists added/removed classes and removed members)"
else
  CLASSIFICATION="$(classify)"
  printf '%s\n' "$CLASSIFICATION"
  if [[ "$MODE" == dry-run ]] && ! printf '%s' "$CLASSIFICATION" | grep -q '^classification=none'; then
    echo "[upgrade_godot] FAIL: --dry-run expects a no-op API diff on the current pin" >&2
    exit 1
  fi
fi

step "step 4: engine-wide name constants"
run_hard python3 "$ROOT_DIR/scripts/generate_name_constants.py"

# Step 5: the wrapper tree. --write-tree re-renders until a pass writes nothing, adopts every
# class the new API adds (listed), and moves the desktop ObjectCalls `actual` set with the expect.
step "step 5: the generated wrapper tree (re-adopt, adopt new classes, desktop actual set)"
run_hard python3 "$ROOT_DIR/scripts/generate_api_wrapper.py" --write-tree \
  --skip-report "$ROOT_DIR/build/wrapper-generator/skips.txt"
report_removed_classes() {
  PYTHONPATH="$ROOT_DIR/scripts" python3 - "$BASELINE_API" <<'EOF'
import json, sys
from generate_api_wrapper import PER_PLATFORM_WRAPPERS, committed_wrapper_stems
from wrapper_model import ROOT

api = {c["name"] for c in json.loads((ROOT / "extension_api.json").read_text(encoding="utf-8"))["classes"]}
# "Removed from the API" means it was a class in the PREVIOUS dump and is not one now --
# not "a .kt file in api/ that is not a Godot class" (GD, GodotObject, the handle aliases).
old_api = {c["name"] for c in json.load(open(sys.argv[1], encoding="utf-8")).get("classes", [])}
removed = sorted((committed_wrapper_stems() & old_api) - api)
if removed:
    print("[upgrade_godot] HUMAN: wrappers for classes REMOVED from the API (delete the file, announce the "
          f"source break; PER_PLATFORM_WRAPPERS must not name them): {', '.join(removed)}")
stale_table = sorted(set(PER_PLATFORM_WRAPPERS) - api)
if stale_table:
    raise SystemExit(f"[upgrade_godot] FAIL: PER_PLATFORM_WRAPPERS names classes no longer in the API: {stale_table}")
EOF
}
run_hard report_removed_classes

# Step 6: every other output generated from extension_api.json or the wrapper tree (task 139 U4:
# the bump used to leave these stale for the gates to find one per local_ci run). Order matters
# where one reads another: the coverage page reads the generator report, and the KDoc sync runs
# after the builtin ops, which rewrite the value types without their KDoc.
step "step 6: the other generated outputs, then the KDoc sync"
run_soft "builtin operators and methods" python3 "$ROOT_DIR/scripts/generate_builtin_ops.py" --write
run_soft "engine method-name table" python3 "$ROOT_DIR/scripts/generate_engine_method_table.py"
run_soft "Web backend dispatch" python3 "$ROOT_DIR/scripts/generate_web_backend.py" \
  --output "$ROOT_DIR/web-runtime/src/wasmJsMain/kotlin/net/multigesture/kanama/web/WebCommonGodotBackend.generated.kt"
run_soft "Web wrapper tree" python3 "$ROOT_DIR/scripts/generate_web_wrappers.py"
run_soft "Web API exercise" python3 "$ROOT_DIR/scripts/generate_web_exercise.py"
run_soft "API exercise inventory" python3 "$ROOT_DIR/scripts/generate_api_exercise.py" --report
run_soft "generator report" python3 "$ROOT_DIR/scripts/api_wrapper_generator_report.py" \
  --markdown "$ROOT_DIR/docs/reference/generated/wrapper-generator-report.md"
run_soft "coverage report" python3 "$ROOT_DIR/scripts/api_wrapper_coverage.py" \
  --markdown "$ROOT_DIR/docs/reference/generated/api-coverage.md"
run_soft "enum migration table" python3 "$ROOT_DIR/scripts/migrate_enum_constants.py" \
  --table "$ROOT_DIR/docs/reference/generated/enum-migration.md"
run_soft "iOS handwritten report" python3 "$ROOT_DIR/scripts/ios_handwritten_report.py"
run_hard python3 "$ROOT_DIR/scripts/sync_kdoc_from_godot_docs.py" --godot-docs "$GODOT_DOCS_DIR" --write

step "step 7: ktfmtFormat (the regen leaves ktfmt-formatted files unformatted)"
run_soft "ktfmtFormat" run_with_timeout "$GRADLE_TIMEOUT" "$ROOT_DIR/gradlew" -p "$ROOT_DIR" --quiet ktfmtFormat

step "step 8: gates index"
run_soft "gates index" python3 "$ROOT_DIR/scripts/generate_gates_index.py" \
  --markdown "$ROOT_DIR/docs/reference/generated/gates.md"

# Step 9: the gates, all of them, then one summary. --baseline-api lets the drift gate report a
# method the new Godot added to a strict-list class without a helper as pending work (task 139 U7).
step "step 9: gates"
run_soft "API constants vs the new extension_api.json" python3 "$ROOT_DIR/scripts/validate_godot_api.py"
run_soft "wrapper drift gate" python3 "$ROOT_DIR/scripts/check_wrapper_generator.py" --baseline-api "$BASELINE_API"
run_soft "ktfmt formatting" run_with_timeout "$GRADLE_TIMEOUT" "$ROOT_DIR/gradlew" -p "$ROOT_DIR" --quiet ktfmtCheck
run_soft "KDoc staleness" python3 "$ROOT_DIR/scripts/sync_kdoc_from_godot_docs.py" --godot-docs "$GODOT_DOCS_DIR" --check
run_soft "Godot version pins (with the new API header)" python3 "$ROOT_DIR/scripts/check_godot_version_pin.py"
run_soft "virtual-method signature table" python3 "$ROOT_DIR/scripts/generate_virtual_signature_table.py" --check
run_soft "engine method-name table" python3 "$ROOT_DIR/scripts/generate_engine_method_table.py" --check
run_soft "builtin operators and methods" python3 "$ROOT_DIR/scripts/generate_builtin_ops.py" --check
run_soft "builtin coverage" python3 "$ROOT_DIR/scripts/check_builtin_coverage.py"
run_soft "ObjectCalls parity" python3 "$ROOT_DIR/scripts/check_objectcalls_parity.py"
run_soft "typed enums" python3 "$ROOT_DIR/scripts/check_typed_enums.py"
run_soft "hand-code budget" python3 "$ROOT_DIR/scripts/check_hand_code_budget.py"
run_soft "GDExtension modernization" python3 "$ROOT_DIR/scripts/check_gdextension_modernization.py"
run_soft "API exercise inventory" python3 "$ROOT_DIR/scripts/generate_api_exercise.py" --check
run_soft "Web call descriptors" python3 "$ROOT_DIR/scripts/generate_platform_backend_contract.py" \
  --api "$ROOT_DIR/extension_api.json" \
  --output "$ROOT_DIR/kanama-common-api/src/commonMain/kotlin/net/multigesture/kanama/backend/InitialGodotCallDescriptors.generated.kt" \
  --check
run_soft "Web backend dispatch" python3 "$ROOT_DIR/scripts/generate_web_backend.py" \
  --output "$ROOT_DIR/web-runtime/src/wasmJsMain/kotlin/net/multigesture/kanama/web/WebCommonGodotBackend.generated.kt" --check
run_soft "Web wrapper tree" python3 "$ROOT_DIR/scripts/generate_web_wrappers.py" --check
run_soft "Web API exercise" python3 "$ROOT_DIR/scripts/generate_web_exercise.py" --check
run_soft "Web typed enums" python3 "$ROOT_DIR/scripts/check_web_typed_enums.py"
run_soft "coverage report" python3 "$ROOT_DIR/scripts/api_wrapper_coverage.py" \
  --markdown "$ROOT_DIR/docs/reference/generated/api-coverage.md" --check
run_soft "generator report" python3 "$ROOT_DIR/scripts/api_wrapper_generator_report.py" \
  --markdown "$ROOT_DIR/docs/reference/generated/wrapper-generator-report.md" --check
run_soft "enum migration table" python3 "$ROOT_DIR/scripts/migrate_enum_constants.py" \
  --table "$ROOT_DIR/docs/reference/generated/enum-migration.md" --check
run_soft "gates index" python3 "$ROOT_DIR/scripts/generate_gates_index.py" \
  --markdown "$ROOT_DIR/docs/reference/generated/gates.md" --check
run_human "adopted-list classes: a method the new Godot added has no helper; add it, or the PENDING_HELPER_SKIPS row the drift gate printed" \
  python3 "$ROOT_DIR/scripts/check_wrapper_generator.py"
run_human "public API signatures: announce each source break in CHANGELOG.md, then --write the snapshots" \
  python3 "$ROOT_DIR/scripts/check_public_signature_changes.py"
run_human "gate-evidence ledger: re-run each stale gate on the new pin, or accept it stale with a reason and a date" \
  python3 "$ROOT_DIR/scripts/check_gate_evidence.py"

if [[ "$MODE" == plan ]]; then
  if [[ ${#PREFLIGHT_FAILURES[@]} -gt 0 ]]; then
    echo "[upgrade_godot] plan printed; the preflight problems above must be fixed before the bump can run." >&2
    exit 1
  fi
  log "plan printed; preflight passed. Run again without --plan to perform the bump."
  exit 0
fi

if [[ "$MODE" == dry-run ]]; then
  STATUS_AFTER="$(git -C "$ROOT_DIR" status --porcelain)"
  if [[ "$STATUS_AFTER" != "$STATUS_BEFORE" ]]; then
    # justified: diff exits 1 on a difference, which is the very thing being recorded as a failure here.
    status_diff="$(diff <(printf '%s\n' "$STATUS_BEFORE") <(printf '%s\n' "$STATUS_AFTER") || true)"
    SOFT_FAILURES+=("the dry run changed the tree:
$status_diff")
  fi
fi

# ---------------------------------------------------------------------------
# Summary.
# ---------------------------------------------------------------------------
echo
if [[ ${#HUMAN_ITEMS[@]} -gt 0 ]]; then
  log "human decisions this bump needs (expected on an API change; the gate stays red until done):"
  for item in "${HUMAN_ITEMS[@]}"; do
    echo "  - $item"
  done
fi
if [[ ${#SOFT_FAILURES[@]} -gt 0 ]]; then
  log "FAIL: ${#SOFT_FAILURES[@]} step(s) or gate(s) failed (output above):"
  for item in "${SOFT_FAILURES[@]}"; do
    echo "  - $item"
  done
  if [[ "$MODE" == dry-run ]]; then
    echo "[upgrade_godot] the dry run may have left changes: git -C \"$ROOT_DIR\" status" >&2
  fi
  exit 1
fi
if [[ "$MODE" == dry-run ]]; then
  log "dry run PASS: the whole pipeline ran on the current pin, every gate is green and the tree is byte-identical"
  exit 0
fi

cat <<EOF
[upgrade_godot] mechanical steps complete: every generated output is refreshed and the gates above are green.
[upgrade_godot] STOPPING at the human-judgment boundary. Remaining (full runbook: docs/contributing/godot-upgrade.md):
  1. Review the classification above and the classes step 5 adopted (named in its output).
  2. Run local CI against the new binary: scripts/local_ci.sh $GODOT_BIN
     The runtime smoke re-records nothing: when Godot adds a builtin member, re-record the Web parity
     hashes from its log (generate_builtin_ops.py --record-parity <log>).
  3. Run the API exercise rows headless and review the new classes' rows (runbook).
  4. Re-run the platform smoke/device gates per the release-gate matrix (§6) in
     the internal task repo, and record them in evidence/gates.json. This script does NOT change
     support claims; support wording moves only after the matching gate passes.
  5. Refresh release-facing wording by hand: CHANGELOG.md, README.md, docs
     version badges/wording, templates, companion demo defaults. Review the
     .gdextension compatibility_minimum invariant before touching it
     (see AGENTS.md "Looks Wrong But Isn't").
  6. Bump the KDoc docs-pin commit hash in docs/contributing/wrapper-maintenance.md.
  7. Before tagging: scripts/fresh_clone_smoke.sh $GODOT_BIN
EOF

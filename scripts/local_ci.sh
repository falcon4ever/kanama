#!/usr/bin/env bash
# `-E` so the ERR trap below is inherited by functions, subshells and command
# substitutions. Without it a failure inside `( ... )` exits non-zero with no banner,
# which is the exact "non-zero exit, no visible error" report this trap exists to fix.
set -Eeuo pipefail

bootstrap_build_dir=""
current_stage=""

cleanup() {
  if [[ -n "$bootstrap_build_dir" && -d "$bootstrap_build_dir" ]]; then
    rm -rf "$bootstrap_build_dir"
  fi
}

# Failure banner. Stages print their own diagnostics, but a failing smoke dumps ~120 lines
# of Godot verbose log afterwards, which buries the one line that says what broke. This
# always fires last, so the reason for the failure is the final thing on screen.
ci_failed() {
  local exit_code="$1" line="$2" command="$3"
  # `-E` propagates ERR into subshells and command substitutions, so a failure inside one
  # would report twice. Only the top-level shell reports; it still fires, because set -e
  # surfaces the subshell's non-zero status here anyway, and its banner is the informative
  # one (the subshell reports the inner fragment, e.g. just `head -n 1`).
  if (( BASH_SUBSHELL != 0 )); then
    return
  fi
  echo >&2
  echo "========================================================================" >&2
  echo "[local_ci] FAILED (exit $exit_code)" >&2
  echo "  stage:   ${current_stage:-<startup>}" >&2
  echo "  command: $command" >&2
  echo "  at:      scripts/local_ci.sh:$line" >&2
  echo >&2
  echo "  This run did NOT pass. Scroll up for the stage output above -- if the" >&2
  echo "  failing stage was a smoke, its diagnostic is repeated at the end of its" >&2
  echo "  own log dump." >&2
  echo "========================================================================" >&2
}

# Installed before any real work, including the version probe below: a failure there used to
# exit 1 with nothing on screen, which is the same defect this banner exists to fix.
trap 'ci_failed "$?" "$LINENO" "$BASH_COMMAND"' ERR
trap cleanup EXIT

# Announce a stage and record it, so the failure banner can name what was running.
stage() {
  current_stage="$1"
  echo "[local_ci] $1"
}

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
HOST_UNAME="$(uname -s)"
kanama_version="$(sed -nE 's/^version = "([^"]+)"/\1/p' "$ROOT_DIR/build.gradle.kts" | head -n 1)"

ensure_gdextension_header() {
  local godot_bin="$1"
  local header_file="$ROOT_DIR/gdextension/gdextension_interface.h"
  local tmp_dir

  if [[ -f "$header_file" ]]; then
    return
  fi

  echo "[local_ci] missing gdextension_interface.h; dumping from: $godot_bin"
  tmp_dir="$(mktemp -d "${TMPDIR:-/tmp}/kanama_gdextension_header.XXXXXX")"
  (
    cd "$tmp_dir"
    "$godot_bin" --headless --log-file "$tmp_dir/godot-gdextension-interface.log" --dump-gdextension-interface
  )
  mkdir -p "$ROOT_DIR/gdextension"
  cp "$tmp_dir/gdextension_interface.h" "$header_file"
  rm -rf "$tmp_dir"
}

usage() {
  cat <<'EOF'
usage: scripts/local_ci.sh [--skip-docs] [--skip-bootstrap] /path/to/godot [more_godot_binaries...]

Runs local CI-style checks:
  - Gradle build/sync
  - optional CMake bootstrap build
  - optional mkdocs strict build
  - runtime smoke for each Godot binary
  - @Tool editor execution smoke for each Godot binary
  - hot-reload smoke for each Godot binary

You can also provide one Godot binary with KANAMA_GODOT_BIN.
EOF
}

skip_docs=0
skip_bootstrap=0
godot_bins=()

while [[ $# -gt 0 ]]; do
  case "$1" in
    --help|-h)
      usage
      exit 0
      ;;
    --skip-docs)
      skip_docs=1
      shift
      ;;
    --skip-bootstrap)
      skip_bootstrap=1
      shift
      ;;
    --*)
      echo "[local_ci] unknown option: $1" >&2
      usage
      exit 2
      ;;
    *)
      godot_bins+=("$1")
      shift
      ;;
  esac
done

if [[ ${#godot_bins[@]} -eq 0 && -n "${KANAMA_GODOT_BIN:-}" ]]; then
  godot_bins+=("$KANAMA_GODOT_BIN")
fi

if [[ ${#godot_bins[@]} -eq 0 ]]; then
  echo "[local_ci] missing Godot binary" >&2
  usage
  exit 2
fi

case "$HOST_UNAME" in
  MINGW*|MSYS*|CYGWIN*)
    if command -v cygpath >/dev/null 2>&1; then
      for i in "${!godot_bins[@]}"; do
        godot_bins[$i]="$(cygpath -u "${godot_bins[$i]}")"
      done
    fi
    ;;
esac

for godot_bin in "${godot_bins[@]}"; do
  if [[ ! -x "$godot_bin" ]]; then
    echo "[local_ci] Godot binary is not executable: $godot_bin" >&2
    exit 2
  fi
done

echo "[local_ci] repo: $ROOT_DIR"
if [[ -z "$kanama_version" ]]; then
  echo "[local_ci] could not determine Kanama version from build.gradle.kts" >&2
  exit 1
fi
ensure_gdextension_header "${godot_bins[0]}"

# JVM unit tests across all modules (runtime :test + KSP processor :processor:test). These were not
# previously gated — the iOS @ScriptProperty get/set parity contract (task 46) lives here, along with
# the existing type tests.
stage "JVM unit tests"
"$ROOT_DIR/gradlew" -p "$ROOT_DIR" test

stage "public docs local-path guard"
if git -C "$ROOT_DIR" grep -nE '(/Users/[[:alnum:]_.-]+|/home/[[:alnum:]_.-]+|lmuller)' -- \
  README.md docs CONTRIBUTING.md templates example_project; then
  echo "[local_ci] tracked public docs/templates must not contain local machine paths or personal checkout names" >&2
  exit 1
fi

stage "validate Godot API constants"
python3 "$ROOT_DIR/scripts/validate_godot_api.py"

stage "API wrapper coverage report"
python3 "$ROOT_DIR/scripts/api_wrapper_coverage.py"

stage "API wrapper coverage docs check"
python3 "$ROOT_DIR/scripts/api_wrapper_coverage.py" --markdown "$ROOT_DIR/docs/contributing/api-coverage.md" --check

stage "API wrapper generator report docs check"
python3 "$ROOT_DIR/scripts/api_wrapper_generator_report.py" --markdown "$ROOT_DIR/docs/contributing/wrapper-generator-report.md" --check

stage "wrapper KDoc staleness check (4.7-stable)"
# Guarded: KDoc is synced from Godot's doc/classes (see docs/contributing/wrapper-maintenance.md
# "Docs version pin"). Runners without a Godot source checkout skip this instead of failing.
kdoc_docs="${GODOT_DOCS:-}"
for candidate in "$HOME/godot-projects/godot/doc/classes" "$HOME/dev/godot/doc/classes"; do
  if [[ -z "$kdoc_docs" && -d "$candidate" ]]; then
    kdoc_docs="$candidate"
  fi
done
if [[ -n "$kdoc_docs" && -d "$kdoc_docs" ]]; then
  python3 "$ROOT_DIR/scripts/sync_kdoc_from_godot_docs.py" --godot-docs "$kdoc_docs" --check
else
  echo "[local_ci] skip: no Godot doc/classes checkout found (set GODOT_DOCS to enable)"
fi

stage "API shell wrapper generator coverage check"
PYTHONPATH="$ROOT_DIR/scripts" python3 "$ROOT_DIR/scripts/generate_api_shell_wrappers.py" \
  --from-skip-report "$ROOT_DIR/build/wrapper-generator/skips.txt" --dry-run --fail-if-candidates

stage "wrapper MethodBind signature audit"
python3 "$ROOT_DIR/scripts/audit_wrapper_signatures.py"

stage "exact wrapper ABI policy audit"
PYTHONPATH="$ROOT_DIR/scripts" python3 "$ROOT_DIR/scripts/audit_wrapper_abi_policy.py" --strict

stage "API wrapper inheritance audit"
PYTHONPATH="$ROOT_DIR/scripts" python3 "$ROOT_DIR/scripts/audit_api_wrapper_inheritance.py"

stage "singleton RefCounted lifetime audit"
python3 "$ROOT_DIR/scripts/audit_singleton_refcounted_policy.py"

stage "conservative wrapper generator fixture"
python3 "$ROOT_DIR/scripts/check_wrapper_generator.py"

stage "iOS no-silent-stubs check"
python3 "$ROOT_DIR/scripts/check_ios_no_silent_stubs.py"

stage "Godot version pin consistency"
python3 "$ROOT_DIR/scripts/check_godot_version_pin.py"

stage "vararg ptrcall audit"
python3 "$ROOT_DIR/scripts/audit_vararg_ptrcalls.py"

stage "type marshal coverage audit"
python3 "$ROOT_DIR/scripts/type_coverage_audit.py"

stage "generator call-shape policy audit"
PYTHONPATH="$ROOT_DIR/scripts" python3 "$ROOT_DIR/scripts/audit_generator_shape_policy.py"

stage "generator object policy audit"
PYTHONPATH="$ROOT_DIR/scripts" python3 "$ROOT_DIR/scripts/audit_generator_object_policy.py"

stage "scalar float ABI audit"
python3 "$ROOT_DIR/scripts/audit_scalar_float_abi.py"

stage "ptrcall helper layout ABI audit"
python3 "$ROOT_DIR/scripts/audit_ptrcall_helper_layouts.py"

stage "builtin storage size ABI audit"
python3 "$ROOT_DIR/scripts/audit_builtin_storage_sizes.py"

stage "Variant marshalling policy audit"
python3 "$ROOT_DIR/scripts/audit_variant_marshalling_policy.py"

stage "GodotObject script-path audit"
python3 "$ROOT_DIR/scripts/audit_godot_object_script_paths.py"

stage "runtime node/RPC guardrail audit"
python3 "$ROOT_DIR/scripts/audit_runtime_node_lookups.py" "$ROOT_DIR/example_project"

stage "replicated script-property guardrail audit"
python3 "$ROOT_DIR/scripts/audit_replicated_script_properties.py" "$ROOT_DIR/example_project"

stage "value-type builtin parity audit"
python3 "$ROOT_DIR/scripts/audit_value_type_wrappers.py" --strict

stage "JDWP bootstrap/project-setting guard"
if ! rg -q 'debug/jdwp_port' "$ROOT_DIR/bootstrap/bootstrap.c" "$ROOT_DIR/example_project/addons/kanama_tools/plugin.gd" "$ROOT_DIR/templates/starter/addons/kanama_tools/plugin.gd"; then
  echo "[local_ci] JDWP project setting is not wired through bootstrap and editor tools" >&2
  exit 1
fi
if ! rg -q 'debug/jdwp_enabled' "$ROOT_DIR/bootstrap/bootstrap.c" "$ROOT_DIR/example_project/addons/kanama_tools/plugin.gd" "$ROOT_DIR/templates/starter/addons/kanama_tools/plugin.gd"; then
  echo "[local_ci] JDWP enable setting is not wired through bootstrap and editor tools" >&2
  exit 1
fi
if rg -q 'debug/jdwp_port_editor' "$ROOT_DIR/bootstrap/bootstrap.c" "$ROOT_DIR/example_project/addons/kanama_tools/plugin.gd" "$ROOT_DIR/templates/starter/addons/kanama_tools/plugin.gd"; then
  echo "[local_ci] obsolete JDWP editor project setting should not be registered" >&2
  exit 1
fi
if rg -q 'set_initial_value\([^,]+,[^,]+,' "$ROOT_DIR/example_project/addons/kanama_tools/plugin.gd" "$ROOT_DIR/templates/starter/addons/kanama_tools/plugin.gd"; then
  echo "[local_ci] Godot plugin set_initial_value must use the GDScript two-argument form" >&2
  exit 1
fi
if ! rg -q 'is_editor_process' "$ROOT_DIR/bootstrap/bootstrap.c"; then
  echo "[local_ci] JDWP editor/game-runner split guard is missing" >&2
  exit 1
fi
if ! rg -q 'KANAMA_JDWP_PORT' "$ROOT_DIR/bootstrap/bootstrap.c"; then
  echo "[local_ci] JDWP environment override is missing" >&2
  exit 1
fi
if ! rg -q 'tools/java_preflight_enabled' "$ROOT_DIR/example_project/addons/kanama_tools/plugin.gd" "$ROOT_DIR/templates/starter/addons/kanama_tools/plugin.gd"; then
  echo "[local_ci] Java runtime preflight setting is not registered in editor tools" >&2
  exit 1
fi
if ! rg -Fq 'libjvm not found. Kanama desktop runtime requires a JDK 25+' "$ROOT_DIR/bootstrap/bootstrap.c"; then
  echo "[local_ci] native bootstrap missing clear libjvm diagnostic" >&2
  exit 1
fi

stage "gradle sync"
"$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar

stage "KSP script-property default literals"
find_project_script_registrar() {
  find "$ROOT_DIR/project-scripts/build" \
    -path "*/generated/ksp/main/kotlin/net/multigesture/kanama/generated/$1" \
    -type f | sort | tail -n 1
}

default_probe_registrar="$(find_project_script_registrar "DefaultProbeScriptScriptRegistrar.kt")"
if [[ ! -f "$default_probe_registrar" ]]; then
  echo "[local_ci] missing generated default-probe registrar" >&2
  exit 1
fi
if ! rg -q 'private var defaultAmount: Long = 250' "$default_probe_registrar"; then
  echo "[local_ci] generated default-probe amount did not use the source literal" >&2
  exit 1
fi
if ! rg -q 'private var defaultTarget: net\.multigesture\.kanama\.types\.NodePath = net\.multigesture\.kanama\.types\.NodePath\("\.\./SceneTarget3D"\)' "$default_probe_registrar"; then
  echo "[local_ci] generated default-probe NodePath did not use the source literal" >&2
  exit 1
fi
if rg -q 'val defaults = DefaultProbeScript\(MemorySegment\.NULL\)' "$default_probe_registrar"; then
  echo "[local_ci] default-probe registrar still constructs a NULL-handle default instance" >&2
  exit 1
fi
hello_script_registrar="$(find_project_script_registrar "HelloScriptScriptRegistrar.kt")"
if [[ ! -f "$hello_script_registrar" ]]; then
  echo "[local_ci] missing generated hello-script registrar" >&2
  exit 1
fi
if ! rg -q 'ClassDB\.PropertySpec\("Smoke Properties", VariantType\.NIL, 0, "", 64\)' "$hello_script_registrar"; then
  echo "[local_ci] generated script-property export group is missing" >&2
  exit 1
fi
if ! rg -q 'ClassDB\.PropertySpec\("Runtime", VariantType\.NIL, 0, "", 256\)' "$hello_script_registrar"; then
  echo "[local_ci] generated script-property export subgroup is missing" >&2
  exit 1
fi
if ! rg -q 'ClassDB\.PropertySpec\("smoke_scene", VariantType\.OBJECT, 17, "PackedScene", 6\)' "$hello_script_registrar"; then
  echo "[local_ci] generated script-property PackedScene export metadata is missing" >&2
  exit 1
fi
if ! rg -q 'ClassDB\.PropertySpec\("smoke_textures", VariantType\.ARRAY, 23, "24/17:Texture2D", 6\)' "$hello_script_registrar"; then
  echo "[local_ci] generated script-property typed Texture2D array metadata is missing" >&2
  exit 1
fi
if ! rg -q 'ClassDB\.PropertySpec\("smoke_resource", VariantType\.OBJECT, 17, "SmokeResource", 6\)' "$hello_script_registrar"; then
  echo "[local_ci] generated script-property custom Resource metadata is missing" >&2
  exit 1
fi
if ! rg -q 'ClassDB\.PropertySpec\("smoke_resources", VariantType\.ARRAY, 23, "24/17:SmokeResource", 6\)' "$hello_script_registrar"; then
  echo "[local_ci] generated script-property custom Resource array metadata is missing" >&2
  exit 1
fi
resource_owner_registrar="$(find_project_script_registrar "ResourceOwnerSmokeScriptRegistrar.kt")"
if ! rg -q 'ClassDB\.PropertySpec\("smoke_resource", VariantType\.OBJECT, 17, "SmokeResource", 6\)' "$resource_owner_registrar"; then
  echo "[local_ci] generated script-property custom Resource metadata is missing" >&2
  exit 1
fi
self_smoke_registrar="$(find_project_script_registrar "SelfSmokeScriptRegistrar.kt")"
if [[ ! -f "$self_smoke_registrar" ]]; then
  echo "[local_ci] missing generated self-smoke registrar" >&2
  exit 1
fi
if ! rg -q 'val kt = SelfSmoke\(godotObject\)' "$self_smoke_registrar"; then
  echo "[local_ci] generated self-smoke registrar does not construct the KanamaScript base-class example" >&2
  exit 1
fi
# 25 = the example HelloScript's exported properties incl. metadata/tool-button
# entries, the task-32 enum export (smoke_difficulty), the task-38 enum-list
# export (smoke_joints), the task-50 throwing-accessor containment fixture
# (smoke_throwing_setter, smoke_throwing_getter), the MutableList export
# (smoke_mutable_textures), and issue #40 typed Map exports (smoke_scalar_map,
# smoke_region_map, smoke_enum_map, smoke_vector_key_map, smoke_vector_resource_map,
# smoke_mutable_map, smoke_nullable_scalar_map). Bump when the example gains/loses one.
if ! rg -q 'propertyCount = 25' "$hello_script_registrar"; then
  echo "[local_ci] generated script-property list count does not include metadata/tool-button entries" >&2
  exit 1
fi
if ! rg -q 'cleanup = \{ cleanupKanamaOwnedProperties\(kt\) \}' "$hello_script_registrar"; then
  echo "[local_ci] generated script-property cleanup hook is missing" >&2
  exit 1
fi
if ! rg -q 'closeKanamaOwned\("smoke_scene", kt\.smokeScene\)' "$hello_script_registrar"; then
  echo "[local_ci] generated script-property reassignment cleanup is missing" >&2
  exit 1
fi
if ! rg -Fq 'kt.smokeResource?.let { BuiltinTypes.releaseRefCounted(it.godotObject) }' "$hello_script_registrar"; then
  echo "[local_ci] generated custom Resource property cleanup does not release the retained handle" >&2
  exit 1
fi
if ! rg -Fq 'kt.smokeResources.forEach { BuiltinTypes.releaseRefCounted(it.godotObject) }' "$hello_script_registrar"; then
  echo "[local_ci] generated custom Resource array cleanup does not release retained handles" >&2
  exit 1
fi
if rg -Fq 'SmokeResourceScriptRegistrar.cleanupKanamaOwnedProperties' "$hello_script_registrar"; then
  echo "[local_ci] generated custom Resource cleanup must not recursively clean child script properties" >&2
  exit 1
fi
if ! rg -q 'BuiltinTypes\.initVariantDictionary\(ret, mapOf\(' "$hello_script_registrar"; then
  echo "[local_ci] generated RPC config must return a Variant-wrapped Dictionary" >&2
  exit 1
fi
if ! rg -q 'object HelloScriptMethods' "$hello_script_registrar"; then
  echo "[local_ci] generated method helper object is missing" >&2
  exit 1
fi
if ! rg -q 'fun greet\(instance: HelloScript, name: String\): String =' "$hello_script_registrar"; then
  echo "[local_ci] generated direct method helper is missing" >&2
  exit 1
fi
if ! rg -q 'fun greet\(target: net\.multigesture\.kanama\.api\.GodotObject, name: String\): String\? =' "$hello_script_registrar"; then
  echo "[local_ci] generated GodotObject method helper is missing" >&2
  exit 1
fi
if ! rg -q 'fun resetHealth\(target: net\.multigesture\.kanama\.api\.GodotObject\): Boolean' "$hello_script_registrar"; then
  echo "[local_ci] generated Unit-returning GodotObject method helper is missing" >&2
  exit 1
fi
if ! rg -q 'object HelloScriptRpcs' "$hello_script_registrar"; then
  echo "[local_ci] generated RPC sender helper object is missing" >&2
  exit 1
fi
if ! rg -q 'fun rpcReplaceSmokeScene\(instance: HelloScript\): Long =' "$hello_script_registrar"; then
  echo "[local_ci] generated RPC sender helper is missing" >&2
  exit 1
fi
if ! rg -q 'fun rpcIdReplaceSmokeScene\(instance: HelloScript, peerId: Long\): Long =' "$hello_script_registrar"; then
  echo "[local_ci] generated RPC peer-targeted sender helper is missing" >&2
  exit 1
fi
if ! rg -q 'fun callLocalReplaceSmokeScene\(instance: HelloScript\)' "$hello_script_registrar"; then
  echo "[local_ci] generated call-local RPC sender helper is missing" >&2
  exit 1
fi

stage "external addon install"
install_check_dir="${TMPDIR:-/tmp}/kanama_local_ci_install"
"$ROOT_DIR/gradlew" -p "$ROOT_DIR" installAddonJar \
  "-PkanamaProjectDir=$install_check_dir" \
  "-PkanamaProjectScriptsDir=$ROOT_DIR/example_project"
case "$HOST_UNAME" in
  Darwin) native_artifact="libkanama_bootstrap.dylib" ;;
  Linux) native_artifact="libkanama_bootstrap.so" ;;
  MINGW*|MSYS*|CYGWIN*) native_artifact="kanama_bootstrap.dll" ;;
  *)
    echo "[local_ci] unsupported host platform for native artifact check: $HOST_UNAME" >&2
    exit 1
    ;;
esac
for artifact in kanama.jar kanama-scripts.jar kanama.gdextension "$native_artifact"; do
  if [[ ! -f "$install_check_dir/addons/kanama/$artifact" ]]; then
    echo "[local_ci] missing installed artifact: $artifact" >&2
    exit 1
  fi
done
if [[ "$HOST_UNAME" == "Linux" ]]; then
  linux_native="$install_check_dir/addons/kanama/$native_artifact"
  stage "Linux native bootstrap preflight: file"
  if ! file "$linux_native" | grep -Eq 'ELF .*shared object'; then
    echo "[local_ci] Linux native bootstrap is not an ELF shared object: $linux_native" >&2
    exit 1
  fi
  stage "Linux native bootstrap preflight: ldd"
  if ldd "$linux_native" | grep -Eq 'not found'; then
    echo "[local_ci] Linux native bootstrap has missing dynamic dependencies" >&2
    ldd "$linux_native" >&2 || true
    exit 1
  fi
  if command -v readelf >/dev/null 2>&1; then
    stage "Linux native bootstrap preflight: readelf"
    readelf -d "$linux_native" >/dev/null
    if readelf -d "$linux_native" | grep -Eq '/Users/|/home/'; then
      echo "[local_ci] Linux native bootstrap contains local absolute paths" >&2
      readelf -d "$linux_native" >&2
      exit 1
    fi
  else
    echo "[local_ci] readelf not found; skipping Linux dynamic-section preflight"
  fi
fi
if ! rg -q '^res://addons/kanama/kanama\.gdextension$' "$install_check_dir/.godot/extension_list.cfg"; then
  echo "[local_ci] installAddonJar did not enable the Kanama gdextension" >&2
  exit 1
fi

stage "publish to mavenLocal"
"$ROOT_DIR/gradlew" -p "$ROOT_DIR" publishKanamaToMavenLocal

stage "mavenLocal publication"
maven_local="${KANAMA_MAVEN_LOCAL_REPO:-${HOME}/.m2/repository}/net/multigesture/kanama"
for artifact in \
  "kanama/$kanama_version/kanama-$kanama_version.jar" \
  "kanama/$kanama_version/kanama-$kanama_version-sources.jar" \
  "annotations/$kanama_version/annotations-$kanama_version.jar" \
  "annotations/$kanama_version/annotations-$kanama_version-sources.jar" \
  "processor/$kanama_version/processor-$kanama_version.jar" \
  "processor/$kanama_version/processor-$kanama_version-sources.jar"; do
  if [[ ! -f "$maven_local/$artifact" ]]; then
    echo "[local_ci] missing mavenLocal artifact: $artifact" >&2
    exit 1
  fi
done

if [[ $skip_bootstrap -eq 0 ]]; then
  if command -v cmake >/dev/null 2>&1; then
    stage "bootstrap cmake build"
    bootstrap_build_dir="$(mktemp -d "${TMPDIR:-/tmp}/kanama_bootstrap_build.XXXXXX")"
    cmake -S "$ROOT_DIR/bootstrap" -B "$bootstrap_build_dir" -DCMAKE_BUILD_TYPE=Release
    cmake --build "$bootstrap_build_dir" --config Release
  else
    echo "[local_ci] cmake not found; skipping bootstrap build"
  fi
else
  echo "[local_ci] skipping bootstrap build"
fi

if [[ $skip_docs -eq 0 ]]; then
  if command -v mkdocs >/dev/null 2>&1; then
    stage "mkdocs strict build"
    (cd "$ROOT_DIR" && mkdocs build --strict)
  else
    echo "[local_ci] mkdocs not found; skipping docs build"
    echo "[local_ci] install with: pip install -r docs/requirements.txt"
  fi
else
  echo "[local_ci] skipping docs build"
fi

for godot_bin in "${godot_bins[@]}"; do
  stage "runtime smoke: $godot_bin"
  "$ROOT_DIR/scripts/runtime_smoke.sh" "$godot_bin"

  stage "@Tool smoke: $godot_bin"
  "$ROOT_DIR/scripts/tool_smoke.sh" "$godot_bin"

  stage "hot reload smoke: $godot_bin"
  "$ROOT_DIR/scripts/hot_reload_smoke.sh" "$godot_bin"

  stage "in-process hot reload smoke: $godot_bin"
  "$ROOT_DIR/scripts/hot_reload_in_process_smoke.sh" "$godot_bin"
done

echo "[local_ci] PASS"

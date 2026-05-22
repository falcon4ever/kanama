#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
HOST_UNAME="$(uname -s)"
bootstrap_build_dir=""

cleanup() {
  if [[ -n "$bootstrap_build_dir" && -d "$bootstrap_build_dir" ]]; then
    rm -rf "$bootstrap_build_dir"
  fi
}
trap cleanup EXIT

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

echo "[local_ci] public docs local-path guard"
if git -C "$ROOT_DIR" grep -nE '(/Users/[[:alnum:]_.-]+|/home/[[:alnum:]_.-]+|lmuller)' -- \
  README.md docs CONTRIBUTING.md templates example_project; then
  echo "[local_ci] tracked public docs/templates must not contain local machine paths or personal checkout names" >&2
  exit 1
fi

echo "[local_ci] validate Godot API constants"
python3 "$ROOT_DIR/scripts/validate_godot_api.py"

echo "[local_ci] API wrapper coverage report"
python3 "$ROOT_DIR/scripts/api_wrapper_coverage.py"

echo "[local_ci] API wrapper coverage docs check"
python3 "$ROOT_DIR/scripts/api_wrapper_coverage.py" --markdown "$ROOT_DIR/docs/reference/api-coverage.md" --check

echo "[local_ci] API wrapper generator report docs check"
python3 "$ROOT_DIR/scripts/api_wrapper_generator_report.py" --markdown "$ROOT_DIR/docs/reference/wrapper-generator-report.md" --check

echo "[local_ci] API shell wrapper generator coverage check"
PYTHONPATH="$ROOT_DIR/scripts" python3 "$ROOT_DIR/scripts/generate_api_shell_wrappers.py" \
  --from-skip-report "$ROOT_DIR/build/wrapper-generator/skips.txt" --dry-run --fail-if-candidates

echo "[local_ci] wrapper MethodBind signature audit"
python3 "$ROOT_DIR/scripts/audit_wrapper_signatures.py"

echo "[local_ci] exact wrapper ABI policy audit"
PYTHONPATH="$ROOT_DIR/scripts" python3 "$ROOT_DIR/scripts/audit_wrapper_abi_policy.py" --strict

echo "[local_ci] API wrapper inheritance audit"
PYTHONPATH="$ROOT_DIR/scripts" python3 "$ROOT_DIR/scripts/audit_api_wrapper_inheritance.py"

echo "[local_ci] singleton RefCounted lifetime audit"
python3 "$ROOT_DIR/scripts/audit_singleton_refcounted_policy.py"

echo "[local_ci] conservative wrapper generator fixture"
python3 "$ROOT_DIR/scripts/check_wrapper_generator.py"

echo "[local_ci] vararg ptrcall audit"
python3 "$ROOT_DIR/scripts/audit_vararg_ptrcalls.py"

echo "[local_ci] type marshal coverage audit"
python3 "$ROOT_DIR/scripts/type_coverage_audit.py"

echo "[local_ci] generator call-shape policy audit"
PYTHONPATH="$ROOT_DIR/scripts" python3 "$ROOT_DIR/scripts/audit_generator_shape_policy.py"

echo "[local_ci] generator object policy audit"
PYTHONPATH="$ROOT_DIR/scripts" python3 "$ROOT_DIR/scripts/audit_generator_object_policy.py"

echo "[local_ci] scalar float ABI audit"
python3 "$ROOT_DIR/scripts/audit_scalar_float_abi.py"

echo "[local_ci] ptrcall helper layout ABI audit"
python3 "$ROOT_DIR/scripts/audit_ptrcall_helper_layouts.py"

echo "[local_ci] builtin storage size ABI audit"
python3 "$ROOT_DIR/scripts/audit_builtin_storage_sizes.py"

echo "[local_ci] Variant marshalling policy audit"
python3 "$ROOT_DIR/scripts/audit_variant_marshalling_policy.py"

echo "[local_ci] GodotObject script-path audit"
python3 "$ROOT_DIR/scripts/audit_godot_object_script_paths.py"

echo "[local_ci] value-type builtin parity audit"
python3 "$ROOT_DIR/scripts/audit_value_type_wrappers.py" --strict

echo "[local_ci] JDWP bootstrap/project-setting guard"
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

echo "[local_ci] gradle sync"
"$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar

echo "[local_ci] KSP script-property default literals"
default_probe_registrar="$ROOT_DIR/project-scripts/build/generated/ksp/main/kotlin/net/multigesture/kanama/generated/DefaultProbeScriptScriptRegistrar.kt"
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
hello_script_registrar="$ROOT_DIR/project-scripts/build/generated/ksp/main/kotlin/net/multigesture/kanama/generated/HelloScriptScriptRegistrar.kt"
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
resource_owner_registrar="$ROOT_DIR/project-scripts/build/generated/ksp/main/kotlin/net/multigesture/kanama/generated/ResourceOwnerSmokeScriptRegistrar.kt"
if ! rg -q 'ClassDB\.PropertySpec\("smoke_resource", VariantType\.OBJECT, 17, "SmokeResource", 6\)' "$resource_owner_registrar"; then
  echo "[local_ci] generated script-property custom Resource metadata is missing" >&2
  exit 1
fi
self_smoke_registrar="$ROOT_DIR/project-scripts/build/generated/ksp/main/kotlin/net/multigesture/kanama/generated/SelfSmokeScriptRegistrar.kt"
if [[ ! -f "$self_smoke_registrar" ]]; then
  echo "[local_ci] missing generated self-smoke registrar" >&2
  exit 1
fi
if ! rg -q 'val kt = SelfSmoke\(godotObject\)' "$self_smoke_registrar"; then
  echo "[local_ci] generated self-smoke registrar does not construct the KanamaScript base-class example" >&2
  exit 1
fi
if ! rg -q 'propertyCount = 11' "$hello_script_registrar"; then
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
if ! rg -q 'BuiltinTypes\.initVariantDictionary\(ret, mapOf\(' "$hello_script_registrar"; then
  echo "[local_ci] generated RPC config must return a Variant-wrapped Dictionary" >&2
  exit 1
fi

echo "[local_ci] external addon install"
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
  echo "[local_ci] Linux native bootstrap preflight: file"
  if ! file "$linux_native" | grep -Eq 'ELF .*shared object'; then
    echo "[local_ci] Linux native bootstrap is not an ELF shared object: $linux_native" >&2
    exit 1
  fi
  echo "[local_ci] Linux native bootstrap preflight: ldd"
  if ldd "$linux_native" | grep -Eq 'not found'; then
    echo "[local_ci] Linux native bootstrap has missing dynamic dependencies" >&2
    ldd "$linux_native" >&2 || true
    exit 1
  fi
  if command -v readelf >/dev/null 2>&1; then
    echo "[local_ci] Linux native bootstrap preflight: readelf"
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

echo "[local_ci] mavenLocal publication"
maven_local="${HOME}/.m2/repository/net/multigesture/kanama"
for artifact in \
  "kanama/0.1.0/kanama-0.1.0.jar" \
  "kanama/0.1.0/kanama-0.1.0-sources.jar" \
  "annotations/0.1.0/annotations-0.1.0.jar" \
  "annotations/0.1.0/annotations-0.1.0-sources.jar" \
  "processor/0.1.0/processor-0.1.0.jar" \
  "processor/0.1.0/processor-0.1.0-sources.jar"; do
  if [[ ! -f "$maven_local/$artifact" ]]; then
    echo "[local_ci] missing mavenLocal artifact: $artifact" >&2
    exit 1
  fi
done

if [[ $skip_bootstrap -eq 0 ]]; then
  if command -v cmake >/dev/null 2>&1; then
    echo "[local_ci] bootstrap cmake build"
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
    echo "[local_ci] mkdocs strict build"
    (cd "$ROOT_DIR" && mkdocs build --strict)
  else
    echo "[local_ci] mkdocs not found; skipping docs build"
    echo "[local_ci] install with: pip install -r docs/requirements.txt"
  fi
else
  echo "[local_ci] skipping docs build"
fi

for godot_bin in "${godot_bins[@]}"; do
  echo "[local_ci] runtime smoke: $godot_bin"
  "$ROOT_DIR/scripts/runtime_smoke.sh" "$godot_bin"

  echo "[local_ci] @Tool smoke: $godot_bin"
  "$ROOT_DIR/scripts/tool_smoke.sh" "$godot_bin"

  echo "[local_ci] hot reload smoke: $godot_bin"
  "$ROOT_DIR/scripts/hot_reload_smoke.sh" "$godot_bin"

  echo "[local_ci] in-process hot reload smoke: $godot_bin"
  "$ROOT_DIR/scripts/hot_reload_in_process_smoke.sh" "$godot_bin"
done

echo "[local_ci] PASS"

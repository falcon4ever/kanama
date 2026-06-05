#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

usage() {
  cat <<'EOF'
usage: scripts/ios_visual_smoke.sh [options]

Creates a temporary pure-Godot visual scene, installs Kanama's experimental iOS
addon artifacts, exports an iOS Xcode project, builds it for a booted iOS
simulator, installs it, launches it, and captures a screenshot.

Options:
  --godot BIN                  Godot editor binary. Defaults to KANAMA_GODOT_BIN
                               or /Applications/Godot.app/Contents/MacOS/Godot.
  --device UDID                iOS simulator UDID. Defaults to the first booted
                               simulator.
  --xcode-developer-dir DIR    Xcode Developer dir. Defaults to DEVELOPER_DIR or
                               /Applications/Xcode.app/Contents/Developer.
  --godot-simulator-lib PATH   Optional arm64 iphonesimulator libgodot.a used
                               to patch the exported Xcode project.
  --kanama-probe               Add a normal Label to the kanama_ios_probe
                               group. The iOS runtime's main-loop callback
                               updates it from Kotlin/Native via ptrcall.
  --kanama-script-probe        Attach a .kt script resource to a normal Label.
                               The iOS runtime creates a ScriptInstance and
                               updates the Label from Kotlin/Native _ready.
  --kanama-user-script-probe   Compile kotlin-src/IosSmokeScript.kt into the
                               iOS Kotlin/Native runtime, attach it to a Label,
                               and update the Label from the project script.
  --kanama-bunnymark-probe     Compile the Bunnymark V1 sprite Kanama script
                               into the iOS Kotlin/Native runtime and run a
                               small add/process/finish smoke scene.
  --bunnymark-demo-dir DIR     Bunnymark demo checkout. Defaults to
                               ../kanama-demos/Bunnymark relative to this repo.
  --work-dir DIR               Smoke workspace. Defaults to a new /tmp dir.
  --keep-running               Leave the launched simulator app running.
  --help, -h                   Show this help.

If the installed Godot iOS template is missing arm64 simulator support, build a
matching Godot simulator template library and pass it with --godot-simulator-lib.
EOF
}

godot_bin="${KANAMA_GODOT_BIN:-/Applications/Godot.app/Contents/MacOS/Godot}"
xcode_developer_dir="${DEVELOPER_DIR:-/Applications/Xcode.app/Contents/Developer}"
device_udid="${KANAMA_IOS_SIMULATOR_UDID:-}"
godot_simulator_lib="${KANAMA_IOS_GODOT_SIMULATOR_LIB:-}"
work_dir=""
keep_running=0
kanama_probe=0
kanama_script_probe=0
kanama_user_script_probe=0
kanama_bunnymark_probe=0
bunnymark_demo_dir="$ROOT_DIR/../kanama-demos/Bunnymark"

while [[ $# -gt 0 ]]; do
  case "$1" in
    --godot)
      godot_bin="${2:-}"
      shift 2
      ;;
    --device)
      device_udid="${2:-}"
      shift 2
      ;;
    --xcode-developer-dir)
      xcode_developer_dir="${2:-}"
      shift 2
      ;;
    --godot-simulator-lib)
      godot_simulator_lib="${2:-}"
      shift 2
      ;;
    --kanama-probe)
      kanama_probe=1
      shift
      ;;
    --kanama-script-probe)
      kanama_script_probe=1
      shift
      ;;
    --kanama-user-script-probe)
      kanama_user_script_probe=1
      shift
      ;;
    --kanama-bunnymark-probe)
      kanama_bunnymark_probe=1
      shift
      ;;
    --bunnymark-demo-dir)
      bunnymark_demo_dir="${2:-}"
      shift 2
      ;;
    --work-dir)
      work_dir="${2:-}"
      shift 2
      ;;
    --keep-running)
      keep_running=1
      shift
      ;;
    --help|-h)
      usage
      exit 0
      ;;
    --*)
      echo "[ios_visual_smoke] unknown option: $1" >&2
      usage
      exit 2
      ;;
    *)
      echo "[ios_visual_smoke] unexpected argument: $1" >&2
      usage
      exit 2
      ;;
  esac
done

probe_count=$((kanama_probe + kanama_script_probe + kanama_user_script_probe + kanama_bunnymark_probe))
if [[ "$probe_count" -gt 1 ]]; then
  echo "[ios_visual_smoke] --kanama-probe, --kanama-script-probe, --kanama-user-script-probe, and --kanama-bunnymark-probe are mutually exclusive" >&2
  exit 2
fi

if [[ ! -x "$godot_bin" ]]; then
  echo "[ios_visual_smoke] Godot binary is not executable: $godot_bin" >&2
  exit 2
fi
if [[ ! -d "$xcode_developer_dir" ]]; then
  echo "[ios_visual_smoke] Xcode Developer dir does not exist: $xcode_developer_dir" >&2
  exit 2
fi
if [[ -n "$godot_simulator_lib" && ! -f "$godot_simulator_lib" ]]; then
  echo "[ios_visual_smoke] Godot simulator lib does not exist: $godot_simulator_lib" >&2
  exit 2
fi
if [[ "$kanama_bunnymark_probe" -eq 1 && ! -d "$bunnymark_demo_dir" ]]; then
  echo "[ios_visual_smoke] Bunnymark demo dir does not exist: $bunnymark_demo_dir" >&2
  exit 2
fi

if [[ -z "$device_udid" ]]; then
  device_udid="$(
    DEVELOPER_DIR="$xcode_developer_dir" xcrun simctl list devices booted |
      sed -nE 's/.*\(([A-F0-9-]{36})\) \(Booted\).*/\1/p' |
      head -n 1
  )"
fi
if [[ -z "$device_udid" ]]; then
  echo "[ios_visual_smoke] no booted iOS simulator found; boot one or pass --device" >&2
  exit 2
fi

if [[ -z "$work_dir" ]]; then
  work_dir="$(mktemp -d "${TMPDIR:-/tmp}/kanama_ios_visual_smoke.XXXXXX")"
else
  mkdir -p "$work_dir"
fi

project_dir="$work_dir/project"
export_dir="$work_dir/export"
derived_dir="$work_dir/derived"
app_name="KanamaIosVisualSmoke"
bundle_id="net.multigesture.kanama.iosvisualsmoke"
screenshot_path="$work_dir/kanama-ios-visual-smoke.png"
stdout_log="$work_dir/launch.stdout.log"
stderr_log="$work_dir/launch.stderr.log"
launch_pid_file="$work_dir/launch.pid"

rm -rf "$project_dir" "$export_dir" "$derived_dir"
mkdir -p "$project_dir" "$export_dir" "$derived_dir"

echo "[ios_visual_smoke] repo: $ROOT_DIR"
echo "[ios_visual_smoke] work dir: $work_dir"
echo "[ios_visual_smoke] simulator: $device_udid"
if [[ "$kanama_probe" -eq 1 ]]; then
  echo "[ios_visual_smoke] mode: grouped Label Kotlin/Native frame smoke"
elif [[ "$kanama_script_probe" -eq 1 ]]; then
  echo "[ios_visual_smoke] mode: attached .kt Kotlin/Native script smoke"
elif [[ "$kanama_user_script_probe" -eq 1 ]]; then
  echo "[ios_visual_smoke] mode: compiled kotlin-src Kotlin/Native script smoke"
elif [[ "$kanama_bunnymark_probe" -eq 1 ]]; then
  echo "[ios_visual_smoke] mode: Bunnymark V1 sprite Kotlin/Native script smoke"
else
  echo "[ios_visual_smoke] mode: pure Godot render smoke"
fi

DEVELOPER_DIR="$xcode_developer_dir" "$ROOT_DIR/gradlew" createStarterProject \
  -PkanamaStarterProjectDir="$project_dir" \
  -PkanamaStarterOverwrite=true

cp "$ROOT_DIR/docs/assets/kanama-logo.png" "$project_dir/icon.png"

status_node_type="Label"
status_node_groups=""
status_text="Pure Godot iOS render smoke"
scene_header="[gd_scene format=3]"
script_resource_line=""
main_script_line=""
status_script_line=""
launch_sleep=3
if [[ "$kanama_probe" -eq 1 ]]; then
  status_node_groups=' groups=["kanama_ios_probe"]'
  status_text="Waiting for Kanama iOS frame probe"
elif [[ "$kanama_script_probe" -eq 1 ]]; then
  status_text="Waiting for Kanama iOS script probe"
  scene_header='[gd_scene load_steps=2 format=3]'
  script_resource_line='[ext_resource type="Script" path="res://kanama_ios_probe.kt" id="1_probe"]'
  status_script_line='script = ExtResource("1_probe")'
  cat >"$project_dir/kanama_ios_probe.kt" <<'EOF'
// Kanama iOS script smoke resource.
// The experimental iOS backend binds this file to a built-in Kotlin/Native probe.
EOF
elif [[ "$kanama_user_script_probe" -eq 1 ]]; then
  status_text="Waiting for Kanama iOS project script"
  scene_header='[gd_scene load_steps=2 format=3]'
  script_resource_line='[ext_resource type="Script" path="res://kotlin-src/IosSmokeScript.kt" id="1_probe"]'
  status_script_line='script = ExtResource("1_probe")'
  mkdir -p "$project_dir/kotlin-src"
  cat >"$project_dir/kotlin-src/IosSmokeScript.kt" <<'EOF'
package net.multigesture.kanama.iossmoke

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Label

@ScriptClass(attachTo = "Label")
class IosSmokeScript(godotObject: MemorySegment) : KanamaScript<Label>(godotObject, ::Label) {
    @OnReady
    fun ready() {
        self.text = "Kanama iOS project script ready"
    }
}
EOF
elif [[ "$kanama_bunnymark_probe" -eq 1 ]]; then
  status_text="Running Kanama iOS Bunnymark"
  scene_header='[gd_scene load_steps=2 format=3]'
  script_resource_line='[ext_resource type="Script" path="res://main.gd" id="1_main"]'
  main_script_line='script = ExtResource("1_main")'
  launch_sleep=5
  mkdir -p "$project_dir/kotlin-src" "$project_dir/images"
  cp "$bunnymark_demo_dir/images/godot_bunny.png" "$project_dir/images/godot_bunny.png"
  cp "$bunnymark_demo_dir/kotlin-src/BunnymarkV1SpritesKanama.kt" "$project_dir/kotlin-src/BunnymarkV1SpritesKanama.kt"
  perl -0pi -e 's/\A/package net.multigesture.kanama.iosbunnymark\n\n/' \
    "$project_dir/kotlin-src/BunnymarkV1SpritesKanama.kt"
  perl -0pi -e 's/\n}\s*\z/\n\n    \@RegisterFunction("signal_ack")\n    fun signalAck() {\n        println("[kanama][ios][kn] bunnymark signal ack")\n    }\n}\n/' \
    "$project_dir/kotlin-src/BunnymarkV1SpritesKanama.kt"
  cat >"$project_dir/main.gd" <<'EOF'
extends Control

var benchmark_node: Node2D
var elapsed := 0.0
var finished := false

func _ready():
    call_deferred("_start_bunnymark")

func _start_bunnymark():
    var script = load("res://kotlin-src/BunnymarkV1SpritesKanama.kt")
    benchmark_node = Node2D.new()
    benchmark_node.set_script(script)
    benchmark_node.add_user_signal("benchmark_finished", [{"name": "output", "type": TYPE_INT}])
    benchmark_node.connect("benchmark_finished", Callable(self, "_on_benchmark_finished"))
    add_child(benchmark_node)
    await get_tree().process_frame
    benchmark_node.set_process(true)
    for i in range(25):
        benchmark_node.call("add_bunny")
    set_process(true)

func _process(delta):
    if finished:
        return
    elapsed += delta
    if elapsed >= 2.0:
        benchmark_node.call("finish")
        finished = true

func _on_benchmark_finished(output):
    $Status.text = "Bunnymark output: %s" % output
    benchmark_node.call("signal_ack")
    print("bunnymark output: ", output)
EOF
fi

cat >"$project_dir/main.tscn" <<EOF
$scene_header

$script_resource_line

[node name="Main" type="Control"]
layout_mode = 3
anchors_preset = 15
anchor_right = 1.0
anchor_bottom = 1.0
$main_script_line

[node name="Background" type="ColorRect" parent="."]
layout_mode = 1
anchors_preset = 15
anchor_right = 1.0
anchor_bottom = 1.0
color = Color(0.0823529, 0.137255, 0.203922, 1)

[node name="Accent" type="ColorRect" parent="."]
layout_mode = 1
anchors_preset = 10
anchor_right = 1.0
offset_bottom = 160.0
color = Color(0.0196078, 0.658824, 0.619608, 1)

[node name="Status" type="$status_node_type" parent="."$status_node_groups]
layout_mode = 1
anchors_preset = 8
anchor_left = 0.5
anchor_top = 0.5
anchor_right = 0.5
anchor_bottom = 0.5
offset_left = -260.0
offset_top = -80.0
offset_right = 260.0
offset_bottom = 80.0
theme_override_font_sizes/font_size = 34
text = "$status_text"
horizontal_alignment = 1
vertical_alignment = 1
$status_script_line
EOF

if ! rg -q '^\[rendering\]' "$project_dir/project.godot"; then
  cat >>"$project_dir/project.godot" <<'EOF'

[rendering]
EOF
fi
if ! rg -q '^textures/vram_compression/import_etc2_astc=' "$project_dir/project.godot"; then
  cat >>"$project_dir/project.godot" <<'EOF'
textures/vram_compression/import_etc2_astc=true
EOF
fi

install_ios_addon_args=(
  installIosAddon
  "-PkanamaIosProjectDir=$project_dir"
  "-PkanamaXcodeDeveloperDir=$xcode_developer_dir"
)
if [[ "$kanama_user_script_probe" -eq 1 ]]; then
  install_ios_addon_args+=("-PkanamaIosProjectScriptsDir=$project_dir/kotlin-src")
fi
if [[ "$kanama_bunnymark_probe" -eq 1 ]]; then
  install_ios_addon_args+=("-PkanamaIosProjectScriptsDir=$project_dir/kotlin-src")
fi

DEVELOPER_DIR="$xcode_developer_dir" "$ROOT_DIR/gradlew" "${install_ios_addon_args[@]}"

cat >"$project_dir/export_presets.cfg" <<EOF
[preset.0]

name="iOS"
platform="iOS"
runnable=false
advanced_options=false
dedicated_server=false
custom_features=""
export_filter="all_resources"
include_filter=""
exclude_filter=""
export_path="$export_dir/$app_name.ipa"
patches=PackedStringArray()
encryption_include_filters=""
encryption_exclude_filters=""
seed=0
encrypt_pck=false
encrypt_directory=false
script_export_mode=2

[preset.0.options]

custom_template/debug=""
custom_template/release=""
architectures/arm64=true
application/app_store_team_id="ABCDE12XYZ"
application/export_method_debug=1
application/code_sign_identity_debug=""
application/code_sign_identity_release=""
application/provisioning_profile_specifier_debug=""
application/provisioning_profile_specifier_release=""
application/export_method_release=0
application/targeted_device_family=2
application/bundle_identifier="$bundle_id"
application/signature=""
application/short_version="0.1"
application/version="1"
application/min_ios_version="14.0"
application/additional_plist_content=""
application/icon_interpolation=4
application/export_project_only=true
application/delete_old_export_files_unconditionally=true
modules/camera=false
entitlements/increased_memory_limit=false
entitlements/game_center=false
entitlements/push_notifications="Disabled"
entitlements/additional=""
capabilities/access_wifi=false
capabilities/performance_gaming_tier=false
capabilities/performance_a12=false
capabilities/additional=PackedStringArray()
shader_baker/enabled=false
user_data/accessible_from_files_app=false
user_data/accessible_from_itunes_sharing=false
privacy/camera_usage_description="Kanama iOS visual smoke does not use the camera."
privacy/microphone_usage_description="Kanama iOS visual smoke does not use the microphone."
privacy/photolibrary_usage_description="Kanama iOS visual smoke does not use the photo library."
privacy/file_timestamp_access_reasons=3
privacy/system_boot_time_access_reasons=1
privacy/disk_space_access_reasons=3
privacy/active_keyboard_access_reasons=0
privacy/user_defaults_access_reasons=0
privacy/tracking_enabled=false
privacy/tracking_domains=PackedStringArray()
EOF

icon_keys=(
  icons/icon_1024x1024
  icons/icon_1024x1024_dark
  icons/icon_1024x1024_tinted
  icons/settings_58x58
  icons/settings_58x58_dark
  icons/settings_58x58_tinted
  icons/settings_87x87
  icons/settings_87x87_dark
  icons/settings_87x87_tinted
  icons/notification_40x40
  icons/notification_40x40_dark
  icons/notification_40x40_tinted
  icons/notification_60x60
  icons/notification_60x60_dark
  icons/notification_60x60_tinted
  icons/notification_76x76
  icons/notification_76x76_dark
  icons/notification_76x76_tinted
  icons/notification_114x114
  icons/notification_114x114_dark
  icons/notification_114x114_tinted
  icons/spotlight_80x80
  icons/spotlight_80x80_dark
  icons/spotlight_80x80_tinted
  icons/spotlight_120x120
  icons/spotlight_120x120_dark
  icons/spotlight_120x120_tinted
  icons/iphone_120x120
  icons/iphone_120x120_dark
  icons/iphone_120x120_tinted
  icons/iphone_180x180
  icons/iphone_180x180_dark
  icons/iphone_180x180_tinted
  icons/ipad_167x167
  icons/ipad_167x167_dark
  icons/ipad_167x167_tinted
  icons/ipad_152x152
  icons/ipad_152x152_dark
  icons/ipad_152x152_tinted
  icons/ios_128x128
  icons/ios_128x128_dark
  icons/ios_128x128_tinted
  icons/ios_192x192
  icons/ios_192x192_dark
  icons/ios_192x192_tinted
  icons/ios_136x136
  icons/ios_136x136_dark
  icons/ios_136x136_tinted
  icons/app_store_1024x1024
  icons/app_store_1024x1024_dark
  icons/app_store_1024x1024_tinted
  icons/ipad_76x76
  icons/spotlight_40x40
)
for key in "${icon_keys[@]}"; do
  printf '%s="res://icon.png"\n' "$key" >>"$project_dir/export_presets.cfg"
done
cat >>"$project_dir/export_presets.cfg" <<'EOF'
storyboard/image_scale_mode=0
storyboard/custom_image@2x=""
storyboard/custom_image@3x=""
storyboard/use_custom_bg_color=false
storyboard/custom_bg_color=Color(0, 0, 0, 1)
EOF

"$godot_bin" --headless --path "$project_dir" --export-debug iOS "$export_dir/$app_name.ipa"

engine_simulator_lib="$export_dir/$app_name.xcframework/ios-arm64_x86_64-simulator/libgodot.a"
if [[ -n "$godot_simulator_lib" ]]; then
  echo "[ios_visual_smoke] patching simulator libgodot.a: $godot_simulator_lib"
  cp "$godot_simulator_lib" "$engine_simulator_lib"
fi

engine_archs="$(DEVELOPER_DIR="$xcode_developer_dir" xcrun lipo -archs "$engine_simulator_lib")"
echo "[ios_visual_smoke] exported simulator libgodot.a archs: $engine_archs"
if ! grep -Eq '(^|[[:space:]])arm64($|[[:space:]])' <<<"$engine_archs"; then
  echo "[ios_visual_smoke] exported simulator libgodot.a is missing arm64" >&2
  echo "[ios_visual_smoke] pass --godot-simulator-lib with an arm64 simulator Godot library" >&2
  exit 1
fi

DEVELOPER_DIR="$xcode_developer_dir" xcodebuild \
  -project "$export_dir/$app_name.xcodeproj" \
  -scheme "$app_name" \
  -configuration Debug \
  -sdk iphonesimulator \
  -destination "id=$device_udid" \
  -derivedDataPath "$derived_dir" \
  CODE_SIGNING_ALLOWED=NO \
  build

app_path="$derived_dir/Build/Products/Debug-iphonesimulator/$app_name.app"
DEVELOPER_DIR="$xcode_developer_dir" xcrun simctl install "$device_udid" "$app_path"

DEVELOPER_DIR="$xcode_developer_dir" xcrun simctl launch \
  --terminate-running-process \
  --console \
  "$device_udid" \
  "$bundle_id" \
  >"$stdout_log" \
  2>"$stderr_log" &
launch_pid="$!"
printf '%s\n' "$launch_pid" >"$launch_pid_file"

sleep "$launch_sleep"
DEVELOPER_DIR="$xcode_developer_dir" xcrun simctl io "$device_udid" screenshot "$screenshot_path"

kill "$launch_pid" >/dev/null 2>&1 || true
wait "$launch_pid" >/dev/null 2>&1 || true

if [[ -f "$stderr_log" ]] && rg -q '\[kanama\]\[ios\]' "$stderr_log"; then
  echo "[ios_visual_smoke] Kanama iOS loader log detected in stderr"
elif [[ -f "$stdout_log" ]] && rg -q '\[kanama\]\[ios\]' "$stdout_log"; then
  echo "[ios_visual_smoke] Kanama iOS loader log detected in stdout"
else
  echo "[ios_visual_smoke] warning: no Kanama iOS loader log captured from simctl stdout/stderr" >&2
fi

if [[ "$kanama_probe" -eq 1 ]]; then
  if rg -q 'updated grouped probe label' "$stderr_log" "$stdout_log"; then
    echo "[ios_visual_smoke] grouped probe label update log detected"
  else
    echo "[ios_visual_smoke] grouped probe label update log missing" >&2
    exit 1
  fi
fi

if [[ "$kanama_script_probe" -eq 1 ]]; then
  if rg -q 'script instance ready' "$stderr_log" "$stdout_log"; then
    echo "[ios_visual_smoke] script instance ready log detected"
  else
    echo "[ios_visual_smoke] script instance ready log missing" >&2
    exit 1
  fi
fi

if [[ "$kanama_user_script_probe" -eq 1 ]]; then
  if rg -q 'project script method call.*_ready' "$stderr_log" "$stdout_log"; then
    echo "[ios_visual_smoke] project script ready log detected"
  else
    echo "[ios_visual_smoke] project script ready log missing" >&2
    exit 1
  fi
fi

if [[ "$kanama_bunnymark_probe" -eq 1 ]]; then
  bunnymark_add_calls="$(
    {
      rg -h 'project script method call.*method=add_bunny' "$stderr_log" "$stdout_log" || true
    } | wc -l | tr -d '[:space:]'
  )"
  if [[ "$bunnymark_add_calls" -lt 25 ]]; then
    echo "[ios_visual_smoke] Bunnymark add_bunny call count too low: $bunnymark_add_calls" >&2
    exit 1
  fi
  if ! rg -q 'project script method call.*method=_process' "$stderr_log" "$stdout_log"; then
    echo "[ios_visual_smoke] Bunnymark _process log missing" >&2
    exit 1
  fi
  if ! rg -q 'project script method call.*method=finish' "$stderr_log" "$stdout_log"; then
    echo "[ios_visual_smoke] Bunnymark finish log missing" >&2
    exit 1
  fi
  if rg -q 'bunnymark signal ack' "$stderr_log" "$stdout_log"; then
    echo "[ios_visual_smoke] Bunnymark add/process/finish/signal logs detected"
  else
    echo "[ios_visual_smoke] Bunnymark signal ack log missing" >&2
    exit 1
  fi
fi

echo "[ios_visual_smoke] app: $app_path"
echo "[ios_visual_smoke] screenshot: $screenshot_path"
echo "[ios_visual_smoke] stdout: $stdout_log"
echo "[ios_visual_smoke] stderr: $stderr_log"

if [[ "$keep_running" -ne 1 ]]; then
  DEVELOPER_DIR="$xcode_developer_dir" xcrun simctl terminate "$device_udid" "$bundle_id" >/dev/null 2>&1 || true
else
  DEVELOPER_DIR="$xcode_developer_dir" xcrun simctl launch "$device_udid" "$bundle_id" >/dev/null
fi

echo "[ios_visual_smoke] OK"

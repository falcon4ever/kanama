#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

usage() {
  cat <<'EOF'
usage: scripts/ios_visual_smoke.sh [options]

Creates a temporary pure-Godot visual scene, installs Kanama's experimental iOS
addon artifacts, exports an iOS Xcode project, builds it for a physical iOS
device or an explicitly selected simulator, installs it, and launches it.

Options:
  --godot BIN                  Godot editor binary. Defaults to KANAMA_GODOT_BIN
                               or /Applications/Godot.app/Contents/MacOS/Godot.
  --device UDID                Physical iOS device identifier/UDID when
                               --physical-device is active; simulator UDID when
                               --simulator is active.
  --physical-device            Treat --device as a physical iOS device
                               identifier/UDID and install with devicectl.
                               This is also selected by KANAMA_IOS_DEVICE.
  --simulator                  Treat --device as a simulator UDID. Defaults to
                               the first booted simulator when no device is set.
  --development-team TEAM      Xcode development team for physical-device
                               signing. Defaults to KANAMA_IOS_DEVELOPMENT_TEAM
                               or KANAMA_IOS_TEAM.
  --allow-provisioning-updates Allow xcodebuild to create/update signing
                               assets for physical-device builds.
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
  --godot-fps-probe            Add a pure-Godot GDScript FPS label.
  --kanama-bunnymark-probe     Compile the Bunnymark V1 sprite Kanama script
                               into the iOS Kotlin/Native runtime and run a
                               small add/process/finish smoke scene.
  --bunnymark-demo-dir DIR     Bunnymark demo checkout. Defaults to
                               ../kanama-demos/Bunnymark relative to this repo.
  --kanama-match3-probe        Compile a small Match3 asset/script probe into
                               the iOS Kotlin/Native runtime.
  --match3-demo-dir DIR        Match3 demo checkout. Defaults to
                               ../kanama-demos/Starter-Kit-Match3.
  --kanama-platformer3d-probe  Compile a small Kenney 3D platformer asset/script
                               probe into the iOS Kotlin/Native runtime.
  --platformer-demo-dir DIR    3D platformer demo checkout. Defaults to
                               ../kanama-demos/Starter-Kit-3D-Platformer.
  --godot-project-baseline DIR Copy and export an existing Godot project without
                               installing Kanama. Injects only an FPS overlay.
  --work-dir DIR               Smoke workspace. Defaults to a new /tmp dir.
  --keep-running               Leave the launched simulator app running.
                               Physical-device runs are always left running.
  --help, -h                   Show this help.

Physical-device runs are the default validation path for the iOS spike. The
simulator path remains available for link/debug checks, but it is not a
performance gate.
EOF
}

godot_bin="${KANAMA_GODOT_BIN:-/Applications/Godot.app/Contents/MacOS/Godot}"
xcode_developer_dir="${DEVELOPER_DIR:-/Applications/Xcode.app/Contents/Developer}"
device_udid="${KANAMA_IOS_DEVICE:-${KANAMA_IOS_SIMULATOR_UDID:-}}"
physical_device=0
if [[ -n "${KANAMA_IOS_DEVICE:-}" ]]; then
  physical_device=1
fi
development_team="${KANAMA_IOS_DEVELOPMENT_TEAM:-${KANAMA_IOS_TEAM:-}}"
allow_provisioning_updates=0
godot_simulator_lib="${KANAMA_IOS_GODOT_SIMULATOR_LIB:-}"
work_dir=""
keep_running=0
kanama_probe=0
kanama_script_probe=0
kanama_user_script_probe=0
godot_fps_probe=0
kanama_bunnymark_probe=0
kanama_match3_probe=0
kanama_platformer3d_probe=0
bunnymark_demo_dir="$ROOT_DIR/../kanama-demos/Bunnymark"
match3_demo_dir="$ROOT_DIR/../kanama-demos/Starter-Kit-Match3"
platformer_demo_dir="$ROOT_DIR/../kanama-demos/Starter-Kit-3D-Platformer"
godot_project_baseline_dir=""

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
    --physical-device)
      physical_device=1
      shift
      ;;
    --simulator)
      physical_device=0
      shift
      ;;
    --development-team)
      development_team="${2:-}"
      shift 2
      ;;
    --allow-provisioning-updates)
      allow_provisioning_updates=1
      shift
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
    --godot-fps-probe)
      godot_fps_probe=1
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
    --kanama-match3-probe)
      kanama_match3_probe=1
      shift
      ;;
    --match3-demo-dir)
      match3_demo_dir="${2:-}"
      shift 2
      ;;
    --kanama-platformer3d-probe)
      kanama_platformer3d_probe=1
      shift
      ;;
    --platformer-demo-dir)
      platformer_demo_dir="${2:-}"
      shift 2
      ;;
    --godot-project-baseline)
      godot_project_baseline_dir="${2:-}"
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

probe_count=$((kanama_probe + kanama_script_probe + kanama_user_script_probe + godot_fps_probe + kanama_bunnymark_probe + kanama_match3_probe + kanama_platformer3d_probe))
if [[ "$probe_count" -gt 1 ]]; then
  echo "[ios_visual_smoke] Kanama probe modes are mutually exclusive" >&2
  exit 2
fi
if [[ -n "$godot_project_baseline_dir" && "$probe_count" -gt 0 ]]; then
  echo "[ios_visual_smoke] --godot-project-baseline cannot be combined with probe modes" >&2
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
if [[ "$kanama_match3_probe" -eq 1 && ! -d "$match3_demo_dir" ]]; then
  echo "[ios_visual_smoke] Match3 demo dir does not exist: $match3_demo_dir" >&2
  exit 2
fi
if [[ "$kanama_platformer3d_probe" -eq 1 && ! -d "$platformer_demo_dir" ]]; then
  echo "[ios_visual_smoke] 3D platformer demo dir does not exist: $platformer_demo_dir" >&2
  exit 2
fi
if [[ -n "$godot_project_baseline_dir" && ! -f "$godot_project_baseline_dir/project.godot" ]]; then
  echo "[ios_visual_smoke] Godot baseline project.godot does not exist: $godot_project_baseline_dir/project.godot" >&2
  exit 2
fi

if [[ "$physical_device" -eq 1 && -z "$device_udid" ]]; then
  echo "[ios_visual_smoke] pass --device with a physical iOS device identifier or UDID" >&2
  echo "[ios_visual_smoke] available devices:" >&2
  DEVELOPER_DIR="$xcode_developer_dir" xcrun devicectl list devices >&2 || true
  exit 2
fi
if [[ "$physical_device" -eq 1 && -z "$development_team" ]]; then
  echo "[ios_visual_smoke] physical-device export requires --development-team TEAM, KANAMA_IOS_DEVELOPMENT_TEAM, or KANAMA_IOS_TEAM" >&2
  echo "[ios_visual_smoke] find the Team ID in Xcode Settings > Accounts after signing in" >&2
  exit 2
fi

if [[ "$physical_device" -eq 0 && -z "$device_udid" ]]; then
  device_udid="$(
    DEVELOPER_DIR="$xcode_developer_dir" xcrun simctl list devices booted |
      sed -nE 's/.*\(([A-F0-9-]{36})\) \(Booted\).*/\1/p' |
      head -n 1
  )"
fi
if [[ -z "$device_udid" ]]; then
  if [[ "$physical_device" -eq 1 ]]; then
    echo "[ios_visual_smoke] no physical iOS device set; pass --device or set KANAMA_IOS_DEVICE" >&2
  else
    echo "[ios_visual_smoke] no booted iOS simulator found; boot one, pass --device, or use --physical-device" >&2
  fi
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

ensure_project_setting() {
  local section="$1"
  local key="$2"
  local value="$3"
  local project_file="$project_dir/project.godot"

  if rg -q -F "$key=" "$project_file"; then
    return
  fi

  local tmp_file="$project_file.tmp"
  awk -v section="[$section]" -v line="$key=$value" '
    $0 == section {
      print
      print line
      inserted = 1
      next
    }
    { print }
    END {
      if (!inserted) {
        print ""
        print section
        print line
      }
    }
  ' "$project_file" >"$tmp_file"
  mv "$tmp_file" "$project_file"
}

echo "[ios_visual_smoke] repo: $ROOT_DIR"
echo "[ios_visual_smoke] work dir: $work_dir"
if [[ "$physical_device" -eq 1 ]]; then
  echo "[ios_visual_smoke] physical device: $device_udid"
  if [[ -z "$development_team" ]]; then
    echo "[ios_visual_smoke] warning: no development team set; physical-device signing may fail" >&2
  fi
else
  echo "[ios_visual_smoke] simulator: $device_udid"
fi
if [[ "$kanama_probe" -eq 1 ]]; then
  echo "[ios_visual_smoke] mode: grouped Label Kotlin/Native frame smoke"
elif [[ "$kanama_script_probe" -eq 1 ]]; then
  echo "[ios_visual_smoke] mode: attached .kt Kotlin/Native script smoke"
elif [[ "$kanama_user_script_probe" -eq 1 ]]; then
  echo "[ios_visual_smoke] mode: compiled kotlin-src Kotlin/Native script smoke"
elif [[ "$godot_fps_probe" -eq 1 ]]; then
  echo "[ios_visual_smoke] mode: pure Godot GDScript FPS smoke"
elif [[ "$kanama_bunnymark_probe" -eq 1 ]]; then
  echo "[ios_visual_smoke] mode: Bunnymark V1 sprite Kotlin/Native script smoke"
elif [[ "$kanama_match3_probe" -eq 1 ]]; then
  echo "[ios_visual_smoke] mode: Match3 asset Kotlin/Native script smoke"
elif [[ "$kanama_platformer3d_probe" -eq 1 ]]; then
  echo "[ios_visual_smoke] mode: Kenney 3D platformer asset Kotlin/Native script smoke"
elif [[ -n "$godot_project_baseline_dir" ]]; then
  echo "[ios_visual_smoke] mode: Godot-only project baseline"
else
  echo "[ios_visual_smoke] mode: pure Godot render smoke"
fi

launch_sleep=3
custom_scene_written=0
if [[ -n "$godot_project_baseline_dir" ]]; then
  cp -R "$godot_project_baseline_dir"/. "$project_dir"
  launch_sleep=12
  mkdir -p "$project_dir/scripts"
  cat >"$project_dir/scripts/ios_fps_overlay.gd" <<'EOF'
extends CanvasLayer

var delta_frames := 0
var delta_elapsed := 0.0
var wall_frames := 0
var wall_start_msec := 0
var label: Label

func _ready() -> void:
    layer = 100
    label = Label.new()
    label.name = "IosFpsOverlayLabel"
    label.anchor_left = 0.0
    label.anchor_top = 0.0
    label.anchor_right = 1.0
    label.anchor_bottom = 0.0
    label.offset_left = 24.0
    label.offset_top = 132.0
    label.offset_right = -24.0
    label.offset_bottom = 184.0
    label.horizontal_alignment = HORIZONTAL_ALIGNMENT_CENTER
    label.vertical_alignment = VERTICAL_ALIGNMENT_CENTER
    label.add_theme_font_size_override("font_size", 24)
    add_child(label)
    wall_start_msec = Time.get_ticks_msec()
    set_process(true)
    _update_status(0, 0, 0)

func _process(delta: float) -> void:
    delta_frames += 1
    delta_elapsed += delta
    wall_frames += 1
    var now := Time.get_ticks_msec()
    var wall_elapsed := now - wall_start_msec
    if wall_elapsed >= 2000:
        var delta_fps := int(round(float(delta_frames) / delta_elapsed))
        var wall_fps := int(round(float(wall_frames) * 1000.0 / float(wall_elapsed)))
        var engine_fps := int(round(Engine.get_frames_per_second()))
        print("[godot][ios][fps] wall=%d delta=%d engine=%d" % [wall_fps, delta_fps, engine_fps])
        _update_status(wall_fps, delta_fps, engine_fps)
        delta_frames = 0
        delta_elapsed = 0.0
        wall_frames = 0
        wall_start_msec = now

func _update_status(wall_fps: int, delta_fps: int, engine_fps: int) -> void:
    label.text = "Wall %d  Delta %d  Engine %d" % [wall_fps, delta_fps, engine_fps]
EOF
  ensure_project_setting autoload IosFpsOverlay '"*res://scripts/ios_fps_overlay.gd"'
else
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
elif [[ "$godot_fps_probe" -eq 1 ]]; then
  status_text="Running pure Godot iOS FPS smoke"
  launch_sleep=12
  custom_scene_written=1
  mkdir -p "$project_dir/scripts"
  cat >"$project_dir/scripts/godot_fps_probe.gd" <<'EOF'
extends Control

var delta_frames := 0
var delta_elapsed := 0.0
var wall_frames := 0
var wall_start_msec := 0
var last_delta_fps := 0
var last_wall_fps := 0
var last_engine_fps := 0

@onready var status: Label = $Status

func _ready() -> void:
    wall_start_msec = Time.get_ticks_msec()
    set_process(true)
    _update_status()

func _process(delta: float) -> void:
    delta_frames += 1
    delta_elapsed += delta
    wall_frames += 1
    var now := Time.get_ticks_msec()
    var wall_elapsed := now - wall_start_msec
    if wall_elapsed >= 2000:
        last_delta_fps = int(round(float(delta_frames) / delta_elapsed))
        last_wall_fps = int(round(float(wall_frames) * 1000.0 / float(wall_elapsed)))
        last_engine_fps = int(round(Engine.get_frames_per_second()))
        delta_frames = 0
        delta_elapsed = 0.0
        wall_frames = 0
        wall_start_msec = now
        _update_status()

func _update_status() -> void:
    status.text = "Wall %d  Delta %d  Engine %d" % [last_wall_fps, last_delta_fps, last_engine_fps]
EOF
  cat >"$project_dir/main.tscn" <<'EOF'
[gd_scene load_steps=2 format=3]

[ext_resource type="Script" path="res://scripts/godot_fps_probe.gd" id="1_fps"]

[node name="Main" type="Control"]
layout_mode = 3
anchors_preset = 15
anchor_right = 1.0
anchor_bottom = 1.0
script = ExtResource("1_fps")

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

[node name="Status" type="Label" parent="."]
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
text = "Wall 0  Delta 0  Engine 0"
horizontal_alignment = 1
vertical_alignment = 1
EOF
elif [[ "$kanama_bunnymark_probe" -eq 1 ]]; then
  status_text="Running Kanama iOS Bunnymark"
  scene_header='[gd_scene load_steps=2 format=3]'
  script_resource_line='[ext_resource type="Script" path="res://main.gd" id="1_main"]'
  main_script_line='script = ExtResource("1_main")'
  launch_sleep=10
  mkdir -p "$project_dir/kotlin-src" "$project_dir/images"
  cp "$bunnymark_demo_dir/images/godot_bunny.png" "$project_dir/images/godot_bunny.png"
  cp "$bunnymark_demo_dir/kotlin-src/BunnymarkV1SpritesKanama.kt" "$project_dir/kotlin-src/BunnymarkV1SpritesKanama.kt"
  perl -0pi -e 's/\A/package net.multigesture.kanama.iosbunnymark\n\n/' \
    "$project_dir/kotlin-src/BunnymarkV1SpritesKanama.kt"
  perl -0pi -e 's/(private var bunnyTexture: Texture2D\? = null\n)/$1    private var frame = 0\n/' \
    "$project_dir/kotlin-src/BunnymarkV1SpritesKanama.kt"
  perl -0pi -e 's/fun process\(delta: Double\) \{\n/fun process(delta: Double) {\n        frame += 1\n        if (frame == 3) {\n            println("[kanama][ios][kn] bunnymark process frame=3")\n        }\n/' \
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
elif [[ "$kanama_match3_probe" -eq 1 ]]; then
  status_text="Running Kanama iOS Match3 smoke"
  launch_sleep=12
  custom_scene_written=1
  mkdir -p "$project_dir/kotlin-src" "$project_dir/scripts" "$project_dir/sprites/backgrounds" "$project_dir/sprites/tiles"
  cp "$match3_demo_dir/sprites/backgrounds/background.png" "$project_dir/sprites/backgrounds/background.png"
  cp "$match3_demo_dir/sprites/tiles/tile-gem.png" "$project_dir/sprites/tiles/tile-gem.png"
  cp "$match3_demo_dir/sprites/tiles/tile-godot.png" "$project_dir/sprites/tiles/tile-godot.png"
  cp "$match3_demo_dir/sprites/tiles/tile-grass.png" "$project_dir/sprites/tiles/tile-grass.png"
  cp "$match3_demo_dir/sprites/tiles/tile-kenney.png" "$project_dir/sprites/tiles/tile-kenney.png"
  cp "$match3_demo_dir/sprites/tiles/tile-spikes.png" "$project_dir/sprites/tiles/tile-spikes.png"
  cat >"$project_dir/kotlin-src/Match3IosSmoke.kt" <<'EOF'
package net.multigesture.kanama.iosmatch3

import java.lang.foreign.MemorySegment
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min
import net.multigesture.kanama.annotations.OnInput
import net.multigesture.kanama.annotations.OnProcess
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.RegisterFunction
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.Control
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.InputEventMouseButton
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.ResourceLoader
import net.multigesture.kanama.api.Sprite2D
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

@ScriptClass(attachTo = "Control")
class Match3IosSmoke(godotObject: MemorySegment) : KanamaScript<Control>(godotObject, ::Control) {
    private val width = 8
    private val height = 8
    private val textures = mutableListOf<net.multigesture.kanama.api.Texture2D>()
    private val grid = Array(width) { arrayOfNulls<Tile>(height) }
    private val allTiles = linkedSetOf<Tile>()
    private var frame = 0
    private var fpsFrames = 0
    private var fpsTime = 0.0
    private var startX = 0.0
    private var startY = 0.0
    private var spacing = 72.0
    private var tileScale = 0.32
    private var selectedColumn = -1
    private var selectedRow = -1
    private var touchStartX = 0.0
    private var touchStartY = 0.0
    private var busy = false
    private var moveCount = 0
    private var removedThisMove = 0
    private var cascadesThisMove = 0
    private var phase = Phase.IDLE
    private var phaseTimer = 0.0
    private var pendingSwap: PendingSwap? = null

    private val moveDuration = 0.24
    private val clearDuration = 0.18
    private val settleDuration = 0.26

    @OnReady
    fun ready() {
        val viewport = self.getViewportRect()
        val center = Vector2(viewport.size.x / 2.0, viewport.size.y / 2.0)
        textures += listOf(
            "res://sprites/tiles/tile-gem.png",
            "res://sprites/tiles/tile-godot.png",
            "res://sprites/tiles/tile-grass.png",
            "res://sprites/tiles/tile-kenney.png",
            "res://sprites/tiles/tile-spikes.png",
        ).mapNotNull(ResourceLoader::loadTexture2D)
        if (textures.isEmpty()) {
            println("[kanama][ios][kn] match3 smoke missing textures")
            return
        }

        val shortSide = min(viewport.size.x, viewport.size.y)
        spacing = shortSide / 9.5
        tileScale = spacing / 128.0 * 0.82
        startX = center.x - spacing * 3.5
        startY = center.y - spacing * 3.5
        for (row in 0 until 8) {
            for (column in 0 until 8) {
                spawnAt(column, row)
            }
        }

        println("[kanama][ios][kn] match3 smoke ready tiles=${width * height}")
    }

    @OnProcess
    fun process(delta: Double) {
        frame += 1
        fpsFrames += 1
        fpsTime += delta
        updateAnimations(delta)
        updatePhase(delta)
        if (frame == 10 || frame == 60 || frame == 120) {
            println("[kanama][ios][kn] match3 smoke process frame=$frame tiles=${width * height}")
        }
        if (fpsTime >= 2.0) {
            val fps = fpsFrames / fpsTime
            println("[kanama][ios][kn] match3 smoke fps=${fps.toInt()} moves=$moveCount")
            fpsTime = 0.0
            fpsFrames = 0
        }
    }

    @OnInput
    fun input(event: GodotObject) {
        val mouseButton = InputEventMouseButton.from(event) ?: return
        if (mouseButton.getButtonIndex() != InputEventMouseButton.MOUSE_BUTTON_LEFT) {
            return
        }
        val position = self.getLocalMousePosition()
        if (mouseButton.isPressed()) {
            touchStart(encodePosition(position))
        } else if (mouseButton.isReleased()) {
            touchEnd(encodePosition(position))
            println("[kanama][ios][kn] match3 smoke input release pos=$position")
        }
    }

    @RegisterFunction("kanama_touch_start")
    fun touchStart(encodedPosition: Double) {
        if (busy || textures.isEmpty()) {
            return
        }
        val position = decodePosition(encodedPosition)
        val gridPosition = screenToGrid(position) ?: return
        clearSelection()
        selectedColumn = gridPosition.first
        selectedRow = gridPosition.second
        touchStartX = position.x
        touchStartY = position.y
        grid[selectedColumn][selectedRow]?.let { animateScale(it, tileScale * 1.12, tileScale * 1.12, 0.08) }
    }

    @RegisterFunction("kanama_touch_end")
    fun touchEnd(encodedPosition: Double) {
        if (busy || selectedColumn < 0 || selectedRow < 0) {
            clearSelection()
            return
        }
        val position = decodePosition(encodedPosition)
        val selectedCenter = gridToScreen(selectedColumn, selectedRow)
        val dx = position.x - selectedCenter.x
        val dy = position.y - selectedCenter.y
        if (abs(dx) < spacing * 0.45 && abs(dy) < spacing * 0.45) {
            clearSelection()
            return
        }

        var targetColumn = selectedColumn
        var targetRow = selectedRow
        if (abs(dx) > abs(dy)) {
            targetColumn += if (dx > 0.0) 1 else -1
        } else {
            targetRow += if (dy > 0.0) 1 else -1
        }

        val sourceColumn = selectedColumn
        val sourceRow = selectedRow
        clearSelection()
        if (!isWithinGrid(targetColumn, targetRow)) {
            return
        }
        handleSwap(sourceColumn, sourceRow, targetColumn, targetRow)
    }

    private fun handleSwap(columnA: Int, rowA: Int, columnB: Int, rowB: Int) {
        if (busy) {
            return
        }
        busy = true
        removedThisMove = 0
        cascadesThisMove = 0
        pendingSwap = PendingSwap(columnA, rowA, columnB, rowB)
        swapTiles(columnA, rowA, columnB, rowB)
        phase = Phase.CHECK_AFTER_SWAP
        phaseTimer = moveDuration
    }

    private fun spawnAt(column: Int, row: Int, fromAbove: Boolean = false) {
        val sprite = Sprite2D(ObjectCalls.constructObject("Sprite2D"))
        val type = chooseType(column, row)
        sprite.setTexture(textures[type])
        sprite.scale = Vector2(tileScale, tileScale)
        self.addChild(sprite)
        val tile = Tile(sprite, type, column, row)
        tile.scaleX = tileScale
        tile.scaleY = tileScale
        grid[column][row] = tile
        allTiles += tile
        val target = gridToScreen(column, row)
        if (fromAbove) {
            sprite.position = Vector2(target.x, target.y - spacing * 2.0)
            tile.displayX = target.x
            tile.displayY = target.y - spacing * 2.0
            beginMove(tile, column, row, settleDuration)
        } else {
            moveTile(tile, column, row, animate = false)
        }
    }

    private fun chooseType(column: Int, row: Int): Int {
        val candidates = (0 until textures.size).toMutableList()
        val left = if (column >= 2) grid[column - 1][row]?.type else null
        if (left != null && grid[column - 2][row]?.type == left) {
            candidates.remove(left)
        }
        val up = if (row >= 2) grid[column][row - 1]?.type else null
        if (up != null && grid[column][row - 2]?.type == up) {
            candidates.remove(up)
        }
        return candidates.randomOrNull() ?: 0
    }

    private fun swapTiles(columnA: Int, rowA: Int, columnB: Int, rowB: Int) {
        val tileA = grid[columnA][rowA] ?: return
        val tileB = grid[columnB][rowB] ?: return
        grid[columnA][rowA] = tileB
        grid[columnB][rowB] = tileA
        moveTile(tileA, columnB, rowB, animate = true)
        moveTile(tileB, columnA, rowA, animate = true)
    }

    private fun moveTile(tile: Tile, column: Int, row: Int, animate: Boolean) {
        tile.column = column
        tile.row = row
        if (animate) {
            beginMove(tile, column, row, moveDuration)
        } else {
            val position = gridToScreen(column, row)
            tile.displayX = position.x
            tile.displayY = position.y
            tile.scaleX = tileScale
            tile.scaleY = tileScale
            tile.sprite.position = position
            tile.sprite.scale = Vector2(tileScale, tileScale)
        }
    }

    private fun beginMove(tile: Tile, column: Int, row: Int, duration: Double) {
        val target = gridToScreen(column, row)
        tile.moveStartX = tile.displayX
        tile.moveStartY = tile.displayY
        tile.moveTargetX = target.x
        tile.moveTargetY = target.y
        tile.moveElapsed = 0.0
        tile.moveDuration = duration
        tile.moving = true
        tile.scaleStartX = tileScale * 1.14
        tile.scaleStartY = tileScale * 0.86
        tile.scaleTargetX = tileScale
        tile.scaleTargetY = tileScale
        tile.scaleElapsed = 0.0
        tile.scaleDuration = duration
        tile.scaling = true
        tile.scaleX = tile.scaleStartX
        tile.scaleY = tile.scaleStartY
        tile.sprite.scale = Vector2(tileScale * 1.14, tileScale * 0.86)
    }

    private fun findMatches(): Set<Tile> {
        val matches = linkedSetOf<Tile>()
        for (row in 0 until height) {
            for (column in 0 until width - 2) {
                val a = grid[column][row]
                val b = grid[column + 1][row]
                val c = grid[column + 2][row]
                if (a != null && b != null && c != null && a.type == b.type && a.type == c.type) {
                    matches += a
                    matches += b
                    matches += c
                }
            }
        }
        for (column in 0 until width) {
            for (row in 0 until height - 2) {
                val a = grid[column][row]
                val b = grid[column][row + 1]
                val c = grid[column][row + 2]
                if (a != null && b != null && c != null && a.type == b.type && a.type == c.type) {
                    matches += a
                    matches += b
                    matches += c
                }
            }
        }
        return matches
    }

    private fun clearMatches(matches: Set<Tile>) {
        for (tile in matches) {
            if (grid[tile.column][tile.row] === tile) {
                grid[tile.column][tile.row] = null
            }
            tile.removeWhenScaleDone = true
            animateScale(tile, 0.0, 0.0, clearDuration)
        }
    }

    private fun collapseColumns() {
        for (column in 0 until width) {
            var writeRow = height - 1
            for (readRow in height - 1 downTo 0) {
                val tile = grid[column][readRow] ?: continue
                if (writeRow != readRow) {
                    grid[column][writeRow] = tile
                    grid[column][readRow] = null
                    moveTile(tile, column, writeRow, animate = true)
                }
                writeRow -= 1
            }
            for (row in writeRow downTo 0) {
                grid[column][row] = null
            }
        }
    }

    private fun refillBoard() {
        for (column in 0 until width) {
            for (row in 0 until height) {
                if (grid[column][row] == null) {
                    spawnAt(column, row, fromAbove = true)
                }
            }
        }
    }

    private fun clearSelection() {
        if (isWithinGrid(selectedColumn, selectedRow)) {
            grid[selectedColumn][selectedRow]?.let { animateScale(it, tileScale, tileScale, 0.08) }
        }
        selectedColumn = -1
        selectedRow = -1
    }

    private fun updatePhase(delta: Double) {
        if (phase == Phase.IDLE) {
            return
        }
        phaseTimer = max(0.0, phaseTimer - delta)
        if (phaseTimer > 0.0 || hasActiveAnimations()) {
            return
        }
        when (phase) {
            Phase.CHECK_AFTER_SWAP -> {
                val matches = findMatches()
                if (matches.isEmpty()) {
                    val swap = pendingSwap
                    if (swap != null) {
                        swapTiles(swap.columnA, swap.rowA, swap.columnB, swap.rowB)
                    }
                    phase = Phase.REJECT_SWAP_BACK
                    phaseTimer = moveDuration
                    println("[kanama][ios][kn] match3 smoke rejected swap")
                } else {
                    startClear(matches)
                }
            }
            Phase.REJECT_SWAP_BACK -> {
                pendingSwap = null
                busy = false
                phase = Phase.IDLE
            }
            Phase.AFTER_CLEAR -> {
                collapseColumns()
                refillBoard()
                phase = Phase.AFTER_COLLAPSE_REFILL
                phaseTimer = settleDuration
            }
            Phase.AFTER_COLLAPSE_REFILL -> {
                val matches = findMatches()
                if (matches.isEmpty()) {
                    moveCount += 1
                    busy = false
                    pendingSwap = null
                    phase = Phase.IDLE
                    println("[kanama][ios][kn] match3 smoke swap ok moves=$moveCount removed=$removedThisMove cascades=$cascadesThisMove")
                } else {
                    startClear(matches)
                }
            }
            Phase.IDLE -> Unit
        }
    }

    private fun startClear(matches: Set<Tile>) {
        cascadesThisMove += 1
        removedThisMove += matches.size
        clearMatches(matches)
        phase = Phase.AFTER_CLEAR
        phaseTimer = clearDuration
    }

    private fun updateAnimations(delta: Double) {
        val iterator = allTiles.iterator()
        while (iterator.hasNext()) {
            val tile = iterator.next()
            if (tile.moving) {
                tile.moveElapsed += delta
                val t = (tile.moveElapsed / tile.moveDuration).coerceIn(0.0, 1.0)
                val eased = easeOutBack(t)
                tile.displayX = lerp(tile.moveStartX, tile.moveTargetX, eased)
                tile.displayY = lerp(tile.moveStartY, tile.moveTargetY, eased)
                tile.sprite.position = Vector2(tile.displayX, tile.displayY)
                if (t >= 1.0) {
                    tile.moving = false
                    tile.displayX = tile.moveTargetX
                    tile.displayY = tile.moveTargetY
                    tile.sprite.position = Vector2(tile.displayX, tile.displayY)
                }
            }
            if (tile.scaling) {
                tile.scaleElapsed += delta
                val t = (tile.scaleElapsed / tile.scaleDuration).coerceIn(0.0, 1.0)
                val eased = easeOutBack(t)
                tile.scaleX = lerp(tile.scaleStartX, tile.scaleTargetX, eased)
                tile.scaleY = lerp(tile.scaleStartY, tile.scaleTargetY, eased)
                tile.sprite.scale = Vector2(tile.scaleX, tile.scaleY)
                if (t >= 1.0) {
                    tile.scaling = false
                    tile.scaleX = tile.scaleTargetX
                    tile.scaleY = tile.scaleTargetY
                    tile.sprite.scale = Vector2(tile.scaleX, tile.scaleY)
                }
            }
            if (tile.removeWhenScaleDone && !tile.scaling) {
                tile.sprite.queueFree()
                iterator.remove()
            }
        }
    }

    private fun animateScale(tile: Tile, targetX: Double, targetY: Double, duration: Double) {
        tile.scaleStartX = tile.scaleX
        tile.scaleStartY = tile.scaleY
        tile.scaleTargetX = targetX
        tile.scaleTargetY = targetY
        tile.scaleElapsed = 0.0
        tile.scaleDuration = duration
        tile.scaling = true
    }

    private fun hasActiveAnimations(): Boolean {
        for (tile in allTiles) {
            if (tile.moving || tile.scaling) {
                return true
            }
        }
        return false
    }

    private fun lerp(from: Double, to: Double, t: Double): Double =
        from + (to - from) * t

    private fun easeOutBack(t: Double): Double {
        val c1 = 1.70158
        val c3 = c1 + 1.0
        val p = t - 1.0
        return 1.0 + c3 * p * p * p + c1 * p * p
    }

    private fun decodePosition(encoded: Double): Vector2 {
        val raw = encoded.toLong()
        return Vector2((raw / 10000L).toDouble(), (raw % 10000L).toDouble())
    }

    private fun encodePosition(position: Vector2): Double =
        (position.x.toLong() * 10000L + position.y.toLong()).toDouble()

    private fun screenToGrid(position: Vector2): Pair<Int, Int>? {
        val left = startX - spacing / 2.0
        val top = startY - spacing / 2.0
        val column = floor((position.x - left) / spacing).toInt()
        val row = floor((position.y - top) / spacing).toInt()
        return if (isWithinGrid(column, row)) column to row else null
    }

    private fun gridToScreen(column: Int, row: Int): Vector2 =
        Vector2(startX + column * spacing, startY + row * spacing)

    private fun isWithinGrid(column: Int, row: Int): Boolean =
        column >= 0 && column < width && row >= 0 && row < height

    private class Tile(
        val sprite: Sprite2D,
        val type: Int,
        var column: Int,
        var row: Int,
    ) {
        var displayX = 0.0
        var displayY = 0.0
        var scaleX = 0.0
        var scaleY = 0.0
        var moving = false
        var moveStartX = 0.0
        var moveStartY = 0.0
        var moveTargetX = 0.0
        var moveTargetY = 0.0
        var moveElapsed = 0.0
        var moveDuration = 0.0
        var scaling = false
        var scaleStartX = 0.0
        var scaleStartY = 0.0
        var scaleTargetX = 0.0
        var scaleTargetY = 0.0
        var scaleElapsed = 0.0
        var scaleDuration = 0.0
        var removeWhenScaleDone = false
    }

    private data class PendingSwap(
        val columnA: Int,
        val rowA: Int,
        val columnB: Int,
        val rowB: Int,
    )

    private enum class Phase {
        IDLE,
        CHECK_AFTER_SWAP,
        REJECT_SWAP_BACK,
        AFTER_CLEAR,
        AFTER_COLLAPSE_REFILL,
    }
}
EOF
  cat >"$project_dir/scripts/touch_probe.gd" <<'EOF'
extends Control

var fps_frames := 0
var fps_start_msec := 0
var last_fps := 0
@onready var status: Label = $TouchStatus

func _ready() -> void:
    mouse_filter = Control.MOUSE_FILTER_PASS
    fps_start_msec = Time.get_ticks_msec()
    set_process(true)
    _update_status()

func _process(_delta: float) -> void:
    fps_frames += 1
    var now := Time.get_ticks_msec()
    var elapsed := now - fps_start_msec
    if elapsed >= 2000:
        last_fps = int(round(float(fps_frames) * 1000.0 / float(elapsed)))
        fps_frames = 0
        fps_start_msec = now
        _update_status()

func _update_status() -> void:
    status.text = "Kanama input  FPS %d" % last_fps
EOF
  cat >"$project_dir/main.tscn" <<'EOF'
[gd_scene load_steps=4 format=3]

[ext_resource type="Script" path="res://kotlin-src/Match3IosSmoke.kt" id="1_match3"]
[ext_resource type="Texture2D" path="res://sprites/backgrounds/background.png" id="2_bg"]
[ext_resource type="Script" path="res://scripts/touch_probe.gd" id="3_touch"]

[node name="Main" type="Control"]
layout_mode = 3
anchors_preset = 15
anchor_right = 1.0
anchor_bottom = 1.0
script = ExtResource("1_match3")

[node name="Background" type="TextureRect" parent="."]
layout_mode = 1
anchors_preset = 15
anchor_right = 1.0
anchor_bottom = 1.0
mouse_filter = 2
texture = ExtResource("2_bg")
expand_mode = 1
stretch_mode = 6

[node name="TouchProbe" type="Control" parent="."]
layout_mode = 1
anchors_preset = 15
anchor_right = 1.0
anchor_bottom = 1.0
mouse_filter = 1
script = ExtResource("3_touch")

[node name="TouchStatus" type="Label" parent="TouchProbe"]
layout_mode = 1
anchors_preset = 3
anchor_left = 1.0
anchor_top = 1.0
anchor_right = 1.0
anchor_bottom = 1.0
offset_left = -260.0
offset_top = -72.0
offset_right = -32.0
offset_bottom = -32.0
theme_override_font_sizes/font_size = 22
text = "Swipe 0  FPS 0"
horizontal_alignment = 1
vertical_alignment = 1
EOF
elif [[ "$kanama_platformer3d_probe" -eq 1 ]]; then
  status_text="Running Kanama iOS 3D platformer smoke"
  launch_sleep=10
  custom_scene_written=1
  mkdir -p "$project_dir/kotlin-src" "$project_dir/models/Textures" "$project_dir/scripts"
  cp "$platformer_demo_dir/models/character.glb" "$project_dir/models/character.glb"
  cp "$platformer_demo_dir/models/platform.glb" "$project_dir/models/platform.glb"
  cp "$platformer_demo_dir/models/coin.glb" "$project_dir/models/coin.glb"
  cp "$platformer_demo_dir/models/Textures/colormap.png" "$project_dir/models/Textures/colormap.png"
  cat >"$project_dir/kotlin-src/Platformer3dIosSmoke.kt" <<'EOF'
package net.multigesture.kanama.iosplatformer

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.annotations.OnProcess
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node3D

@ScriptClass(attachTo = "Node3D")
class Platformer3dIosSmoke(godotObject: MemorySegment) : KanamaScript<Node3D>(godotObject, ::Node3D) {
    private var frame = 0

    @OnReady
    fun ready() {
        println("[kanama][ios][kn] platformer3d smoke ready")
    }

    @OnProcess
    fun process(delta: Double) {
        frame += 1
        if (frame == 3) {
            println("[kanama][ios][kn] platformer3d smoke process frame=3 delta=$delta")
        }
    }
}
EOF
  cat >"$project_dir/scripts/mobile_controls.gd" <<'EOF'
extends CanvasLayer

var touch_count := 0

func _ready() -> void:
    visible = true
    for button in $TouchRoot.find_children("*", "Button", true, false):
        button.button_down.connect(_on_button_down.bind(button.name))
        button.button_up.connect(_on_button_up.bind(button.name))

func _on_button_down(button_name: String) -> void:
    touch_count += 1
    $TouchRoot/TouchStatus.text = "%s %d" % [button_name, touch_count]
    print("[kanama][ios][godot] platformer3d touch button_down name=%s count=%d" % [button_name, touch_count])

func _on_button_up(button_name: String) -> void:
    print("[kanama][ios][godot] platformer3d touch button_up name=%s count=%d" % [button_name, touch_count])
EOF
  cat >"$project_dir/main.tscn" <<'EOF'
[gd_scene load_steps=5 format=3]

[ext_resource type="Script" path="res://kotlin-src/Platformer3dIosSmoke.kt" id="1_actor"]
[ext_resource type="Script" path="res://scripts/mobile_controls.gd" id="2_mobile"]

[sub_resource type="BoxMesh" id="BoxMesh_platform"]
size = Vector3(4, 0.25, 4)

[sub_resource type="SphereMesh" id="SphereMesh_actor"]
radius = 0.35
height = 0.7

[node name="Main" type="Node3D"]

[node name="Platform" type="MeshInstance3D" parent="."]
transform = Transform3D(1, 0, 0, 0, 1.0, 0, 0, 0, 1, 0, -0.15, 0)
mesh = SubResource("BoxMesh_platform")

[node name="Actor" type="Node3D" parent="."]
transform = Transform3D(1, 0, 0, 0, 1, 0, 0, 0, 1, -0.75, 0.45, 0)
script = ExtResource("1_actor")

[node name="ActorMesh" type="MeshInstance3D" parent="Actor"]
mesh = SubResource("SphereMesh_actor")

[node name="Camera3D" type="Camera3D" parent="."]
transform = Transform3D(1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 2.4, 7.0)
current = true

[node name="Sun" type="DirectionalLight3D" parent="."]
transform = Transform3D(0.707107, -0.353553, 0.612372, 0, 0.866025, 0.5, -0.707107, -0.353553, 0.612372, 0, 4, 4)
light_energy = 1.8

[node name="MobileControls" type="CanvasLayer" parent="."]
layer = 10
script = ExtResource("2_mobile")

[node name="TouchRoot" type="Control" parent="MobileControls"]
layout_mode = 3
anchors_preset = 15
anchor_right = 1.0
anchor_bottom = 1.0
mouse_filter = 2

[node name="MoveButton" type="Button" parent="MobileControls/TouchRoot"]
layout_mode = 1
anchors_preset = 2
anchor_top = 1.0
anchor_bottom = 1.0
offset_left = 40.0
offset_top = -132.0
offset_right = 184.0
offset_bottom = -36.0
text = "Move"

[node name="JumpButton" type="Button" parent="MobileControls/TouchRoot"]
layout_mode = 1
anchors_preset = 3
anchor_left = 1.0
anchor_top = 1.0
anchor_right = 1.0
anchor_bottom = 1.0
offset_left = -184.0
offset_top = -132.0
offset_right = -40.0
offset_bottom = -36.0
text = "Jump"

[node name="TouchStatus" type="Label" parent="MobileControls/TouchRoot"]
layout_mode = 1
anchors_preset = 8
anchor_left = 0.5
anchor_top = 0.5
anchor_right = 0.5
anchor_bottom = 0.5
offset_left = -110.0
offset_top = 352.0
offset_right = 110.0
offset_bottom = 392.0
theme_override_font_sizes/font_size = 22
text = "Touch 0"
horizontal_alignment = 1
vertical_alignment = 1
EOF
fi

if [[ "$custom_scene_written" -eq 0 ]]; then
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
fi
fi

ensure_project_setting application run/max_fps 60
ensure_project_setting application run/low_processor_mode false
ensure_project_setting application run/low_processor_mode_sleep_usec 0

if [[ -n "$godot_project_baseline_dir" || "$godot_fps_probe" -eq 1 || "$kanama_match3_probe" -eq 1 || "$kanama_platformer3d_probe" -eq 1 ]]; then
  if ! rg -q '^\[display\]' "$project_dir/project.godot"; then
    cat >>"$project_dir/project.godot" <<'EOF'

[display]
EOF
  fi
  if ! rg -q '^window/size/viewport_width=' "$project_dir/project.godot"; then
    cat >>"$project_dir/project.godot" <<'EOF'
window/size/viewport_width=720
EOF
  fi
  if ! rg -q '^window/size/viewport_height=' "$project_dir/project.godot"; then
    cat >>"$project_dir/project.godot" <<'EOF'
window/size/viewport_height=1280
EOF
  fi
  if ! rg -q '^window/stretch/mode=' "$project_dir/project.godot"; then
    cat >>"$project_dir/project.godot" <<'EOF'
window/stretch/mode="canvas_items"
EOF
  fi
  if ! rg -q '^window/stretch/aspect=' "$project_dir/project.godot"; then
    cat >>"$project_dir/project.godot" <<'EOF'
window/stretch/aspect="expand"
EOF
  fi
  if ! rg -q '^window/handheld/orientation=' "$project_dir/project.godot"; then
    cat >>"$project_dir/project.godot" <<'EOF'
window/handheld/orientation=1
EOF
  fi
  if ! rg -q '^window/vsync/vsync_mode=' "$project_dir/project.godot"; then
    cat >>"$project_dir/project.godot" <<'EOF'
window/vsync/vsync_mode=1
EOF
  fi
fi

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

if [[ -z "$godot_project_baseline_dir" ]]; then
  install_ios_addon_args=(
    installIosAddon
    "-PkanamaIosProjectDir=$project_dir"
    "-PkanamaXcodeDeveloperDir=$xcode_developer_dir"
  )
  if [[ "$physical_device" -eq 1 ]]; then
    install_ios_addon_args+=("-PkanamaIosXcframeworkMode=device")
  else
    install_ios_addon_args+=("-PkanamaIosXcframeworkMode=full")
  fi
  if [[ "$kanama_user_script_probe" -eq 1 ]]; then
    install_ios_addon_args+=("-PkanamaIosProjectScriptsDir=$project_dir/kotlin-src")
  fi
  if [[ "$kanama_bunnymark_probe" -eq 1 ]]; then
    install_ios_addon_args+=("-PkanamaIosProjectScriptsDir=$project_dir/kotlin-src")
  fi
  if [[ "$kanama_match3_probe" -eq 1 ]]; then
    install_ios_addon_args+=("-PkanamaIosProjectScriptsDir=$project_dir/kotlin-src")
  fi
  if [[ "$kanama_platformer3d_probe" -eq 1 ]]; then
    install_ios_addon_args+=("-PkanamaIosProjectScriptsDir=$project_dir/kotlin-src")
  fi

  DEVELOPER_DIR="$xcode_developer_dir" "$ROOT_DIR/gradlew" "${install_ios_addon_args[@]}"
fi

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
application/app_store_team_id="$development_team"
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
if [[ "$physical_device" -eq 0 && -n "$godot_simulator_lib" ]]; then
  echo "[ios_visual_smoke] patching simulator libgodot.a: $godot_simulator_lib"
  cp "$godot_simulator_lib" "$engine_simulator_lib"
fi

if [[ "$physical_device" -eq 0 ]]; then
  engine_archs="$(DEVELOPER_DIR="$xcode_developer_dir" xcrun lipo -archs "$engine_simulator_lib")"
  echo "[ios_visual_smoke] exported simulator libgodot.a archs: $engine_archs"
  if ! grep -Eq '(^|[[:space:]])arm64($|[[:space:]])' <<<"$engine_archs"; then
    echo "[ios_visual_smoke] exported simulator libgodot.a is missing arm64" >&2
    echo "[ios_visual_smoke] pass --godot-simulator-lib with an arm64 simulator Godot library" >&2
    exit 1
  fi
fi

if [[ "$physical_device" -eq 1 ]]; then
  xcodebuild_args=(
    -project "$export_dir/$app_name.xcodeproj"
    -scheme "$app_name"
    -configuration Debug
    -sdk iphoneos
    -destination "id=$device_udid"
    -derivedDataPath "$derived_dir"
    CODE_SIGNING_ALLOWED=YES
    CODE_SIGN_STYLE=Automatic
  )
  if [[ -n "$development_team" ]]; then
    xcodebuild_args+=(DEVELOPMENT_TEAM="$development_team")
  fi
  if [[ "$allow_provisioning_updates" -eq 1 ]]; then
    xcodebuild_args=(-allowProvisioningUpdates "${xcodebuild_args[@]}")
  fi
  DEVELOPER_DIR="$xcode_developer_dir" xcodebuild "${xcodebuild_args[@]}" build

  app_path="$derived_dir/Build/Products/Debug-iphoneos/$app_name.app"
  DEVELOPER_DIR="$xcode_developer_dir" xcrun devicectl device install app \
    --device "$device_udid" \
    "$app_path"
  DEVELOPER_DIR="$xcode_developer_dir" xcrun devicectl device process launch \
    --device "$device_udid" \
    --terminate-existing \
    "$bundle_id" \
    >"$stdout_log" \
    2>"$stderr_log"
  launch_pid=""
  : >"$launch_pid_file"
else
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

  if [[ -n "$godot_project_baseline_dir" ]]; then
    DEVELOPER_DIR="$xcode_developer_dir" xcrun simctl launch \
      --terminate-running-process \
      "$device_udid" \
      "$bundle_id" \
      >"$stdout_log" \
      2>"$stderr_log"
    launch_pid=""
    : >"$launch_pid_file"
  else
    DEVELOPER_DIR="$xcode_developer_dir" xcrun simctl launch \
      --terminate-running-process \
      --console \
      "$device_udid" \
      "$bundle_id" \
      >"$stdout_log" \
      2>"$stderr_log" &
    launch_pid="$!"
    printf '%s\n' "$launch_pid" >"$launch_pid_file"
  fi

  sleep "$launch_sleep"
  DEVELOPER_DIR="$xcode_developer_dir" xcrun simctl io "$device_udid" screenshot "$screenshot_path"

  if [[ -n "$launch_pid" ]]; then
    kill "$launch_pid" >/dev/null 2>&1 || true
    wait "$launch_pid" >/dev/null 2>&1 || true
  fi
fi

if [[ "$physical_device" -eq 1 ]]; then
  echo "[ios_visual_smoke] physical app launched; screenshot capture is not automated"
fi

if [[ "$physical_device" -eq 1 ]]; then
  echo "[ios_visual_smoke] physical-device run; no Kanama loader log assertion"
elif [[ -n "$godot_project_baseline_dir" ]]; then
  echo "[ios_visual_smoke] Godot-only baseline; no Kanama loader log expected"
elif [[ -f "$stderr_log" ]] && rg -q '\[kanama\]\[ios\]' "$stderr_log"; then
  echo "[ios_visual_smoke] Kanama iOS loader log detected in stderr"
elif [[ -f "$stdout_log" ]] && rg -q '\[kanama\]\[ios\]' "$stdout_log"; then
  echo "[ios_visual_smoke] Kanama iOS loader log detected in stdout"
else
  echo "[ios_visual_smoke] warning: no Kanama iOS loader log captured from simctl stdout/stderr" >&2
fi

if [[ "$physical_device" -eq 0 ]] && rg -q 'SCRIPT ERROR|Parse Error|Failed to load script' "$stderr_log" "$stdout_log"; then
  echo "[ios_visual_smoke] script error detected in launch logs" >&2
  exit 1
fi

if [[ "$physical_device" -eq 0 && -n "$godot_project_baseline_dir" ]]; then
  echo "[ios_visual_smoke] Godot-only baseline screenshot captured"
fi

if [[ "$physical_device" -eq 0 && "$kanama_probe" -eq 1 ]]; then
  if rg -q 'updated grouped probe label' "$stderr_log" "$stdout_log"; then
    echo "[ios_visual_smoke] grouped probe label update log detected"
  else
    echo "[ios_visual_smoke] grouped probe label update log missing" >&2
    exit 1
  fi
fi

if [[ "$physical_device" -eq 0 && "$kanama_script_probe" -eq 1 ]]; then
  if rg -q 'script instance ready' "$stderr_log" "$stdout_log"; then
    echo "[ios_visual_smoke] script instance ready log detected"
  else
    echo "[ios_visual_smoke] script instance ready log missing" >&2
    exit 1
  fi
fi

if [[ "$physical_device" -eq 0 && "$kanama_user_script_probe" -eq 1 ]]; then
  if rg -q 'project script method call.*_ready' "$stderr_log" "$stdout_log"; then
    echo "[ios_visual_smoke] project script ready log detected"
  else
    echo "[ios_visual_smoke] project script ready log missing" >&2
    exit 1
  fi
fi

if [[ "$physical_device" -eq 0 && "$kanama_bunnymark_probe" -eq 1 ]]; then
  bunnymark_add_calls="$(
    {
      rg -h 'project script method call.*method=add_bunny' "$stderr_log" "$stdout_log" || true
    } | wc -l | tr -d '[:space:]'
  )"
  if [[ "$bunnymark_add_calls" -lt 25 ]]; then
    echo "[ios_visual_smoke] Bunnymark add_bunny call count too low: $bunnymark_add_calls" >&2
    exit 1
  fi
  if ! rg -q 'bunnymark process frame=3' "$stderr_log" "$stdout_log"; then
    echo "[ios_visual_smoke] Bunnymark process log missing" >&2
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

if [[ "$physical_device" -eq 0 && "$kanama_match3_probe" -eq 1 ]]; then
  if ! rg -q 'match3 smoke ready tiles=64' "$stderr_log" "$stdout_log"; then
    echo "[ios_visual_smoke] Match3 ready log missing" >&2
    exit 1
  fi
  if rg -q 'match3 smoke process frame=10' "$stderr_log" "$stdout_log"; then
    echo "[ios_visual_smoke] Match3 ready/process logs detected"
  else
    echo "[ios_visual_smoke] Match3 process log missing" >&2
    exit 1
  fi
fi

if [[ "$physical_device" -eq 0 && "$kanama_platformer3d_probe" -eq 1 ]]; then
  if ! rg -q 'platformer3d smoke ready' "$stderr_log" "$stdout_log"; then
    echo "[ios_visual_smoke] 3D platformer ready log missing" >&2
    exit 1
  fi
  if rg -q 'platformer3d smoke process frame=3' "$stderr_log" "$stdout_log"; then
    echo "[ios_visual_smoke] 3D platformer ready/process logs detected"
  else
    echo "[ios_visual_smoke] 3D platformer process log missing" >&2
    exit 1
  fi
fi

echo "[ios_visual_smoke] app: $app_path"
if [[ "$physical_device" -eq 0 ]]; then
  echo "[ios_visual_smoke] screenshot: $screenshot_path"
fi
echo "[ios_visual_smoke] stdout: $stdout_log"
echo "[ios_visual_smoke] stderr: $stderr_log"

if [[ "$physical_device" -eq 1 ]]; then
  echo "[ios_visual_smoke] physical-device app left running"
elif [[ "$keep_running" -ne 1 ]]; then
  DEVELOPER_DIR="$xcode_developer_dir" xcrun simctl terminate "$device_udid" "$bundle_id" >/dev/null 2>&1 || true
else
  DEVELOPER_DIR="$xcode_developer_dir" xcrun simctl launch "$device_udid" "$bundle_id" >/dev/null
fi

echo "[ios_visual_smoke] OK"

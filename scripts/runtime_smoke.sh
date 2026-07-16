#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
PROJECT_DIR="${KANAMA_PROJECT_DIR:-$ROOT_DIR/example_project}"
LOG_FILE="${KANAMA_SMOKE_LOG:-/tmp/kanama_runtime_smoke.log}"
IMPORT_LOG_FILE="${KANAMA_SMOKE_IMPORT_LOG:-${LOG_FILE}.import}"

if [[ $# -lt 1 ]]; then
  echo "usage: $0 /absolute/path/to/godot_binary"
  exit 2
fi

GODOT_BIN="$1"
UNAME_S="$(uname -s)"
PROJECT_DIR_FOR_GODOT="$PROJECT_DIR"

case "$UNAME_S" in
  MINGW*|MSYS*|CYGWIN*)
    if command -v cygpath >/dev/null 2>&1; then
      GODOT_BIN="$(cygpath -u "$GODOT_BIN")"
      PROJECT_DIR_FOR_GODOT="$(cygpath -m "$PROJECT_DIR")"
    fi
    ;;
esac

"$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >/dev/null
# task 37 (issue #39) — the editor scan must register @GlobalClass Kotlin scripts
# in the global class list (feeds the Create New Resource dialog and typed-slot
# matching). Clear the cache first so the check exercises a fresh scan, not a
# previous run's result. SmokeResource is @ScriptClass(attachTo = "Resource")
# @GlobalClass.
GLOBAL_CLASS_CACHE="$PROJECT_DIR/.godot/global_script_class_cache.cfg"
rm -f "$GLOBAL_CLASS_CACHE"

KANAMA_TRACE_SCRIPT_PROPERTY_CLEANUP=1 "$GODOT_BIN" --headless --editor --quit-after 120 --path "$PROJECT_DIR_FOR_GODOT" --verbose >"$IMPORT_LOG_FILE" 2>&1

if ! grep -q '"class": &"SmokeResource"' "$GLOBAL_CLASS_CACHE" ||
   ! grep -q '"base": &"Resource"' "$GLOBAL_CLASS_CACHE"; then
  echo "[runtime_smoke] SmokeResource missing from global_script_class_cache.cfg (issue #39 regression)"
  cat "$GLOBAL_CLASS_CACHE" 2>/dev/null || true
  exit 1
fi
KANAMA_TRACE_SCRIPT_PROPERTY_CLEANUP=1 "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" --quit --verbose >"$LOG_FILE" 2>&1
KANAMA_TRACE_SCRIPT_PROPERTY_CLEANUP=1 "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" res://resource_owner_smoke.tscn --quit --verbose >>"$LOG_FILE" 2>&1
KANAMA_TRACE_SCRIPT_PROPERTY_CLEANUP=1 "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" res://self_smoke.tscn --quit --verbose >>"$LOG_FILE" 2>&1

check() {
  local pattern="$1"
  if ! grep -Eq -- "$pattern" "$LOG_FILE"; then
    echo "[runtime_smoke] missing pattern: $pattern"
    echo "[runtime_smoke] log tail:"
    tail -n 120 "$LOG_FILE"
    exit 1
  fi
}

check_absent() {
  local pattern="$1"
  if grep -Eq -- "$pattern" "$LOG_FILE"; then
    echo "[runtime_smoke] unexpected pattern: $pattern"
    echo "[runtime_smoke] log tail:"
    tail -n 120 "$LOG_FILE"
    exit 1
  fi
}

check "ResourceFormatLoader\\._load path=res://HelloScript\\.kt"
check "HelloScript\\(file\\)\\._ready health=99 speed=5\\.1 label=from_tscn difficulty=HARD"
check "ResourceOwnerSmoke payload=from_tscn present=true"
# task 33 — resource-typed exports on a @ScriptClass(attachTo = "Resource") class:
# base-typed slots (AudioStream/Mesh/Shape3D) deserialize .tscn-stored subtypes
# (AudioStreamWAV/BoxMesh/BoxShape3D), stay callable while held, and release on
# cleanup (the check_absent Leaked instance rows below).
check "ResourceOwnerSmoke resource_slots int=7 stream_present=true stream_length=0\\.0 mesh_present=true shape_present=true"
check "script property cleanup stream type=AudioStream"
check "script property cleanup mesh type=Mesh"
check "script property cleanup shape type=Shape3D"
check_absent "Leaked instance: AudioStreamWAV"
check_absent "Leaked instance: BoxMesh"
check_absent "Leaked instance: BoxShape3D"
check "SelfSmoke self_class=Node3D same_object=true"
check "kt script export groups group=true subgroup=true"
# task 32 — custom enum exports: INT + PROPERTY_HINT_ENUM metadata, ordinal
# round-trip via set/get, tscn-stored int deserialized into the enum slot, and
# out-of-range stored ints clamped to a valid entry (no crash).
check "kt script enum export type=true hint=true hint_string=true tscn=true roundtrip=true clamp=true"
# task 38 — enum-list exports: ARRAY + PROPERTY_HINT_TYPE_STRING "2/2:<entries>",
# ordinal-array round-trip via set/get, tscn deserialize, per-element clamp, and
# inspector Add-Element null-fill defaulting to the first entry.
check "kt script enum list export type=true hint=true hint_string=true tscn=true roundtrip=true clamp=true nullfill=true"
check "Mathf lerp=2\\.5 clamp=10 wrap=1 approx=true round=3 lerpf=2\\.5 clampf=10\\.0 sinf=0\\.0 sqrtf=3\\.0"
check "Generated name constants ok=true"
check "ProjectSettings string_list=alpha\\|beta"
check "ProjectSettings dictionary name=kanama enabled=true count=2 scale=1\\.5"
check "ResourceLoader exists=true has_hello=true loaded_path_len=[0-9]+ loaded_is_script=true loaded_ref_count=[0-9]+ loaded_name_len=[0-9]+ loaded_scene_id_len=[0-9]+ loaded_path_id_len=[0-9]+ loaded_built_in=(true|false) loaded_local_to_scene=(true|false) threaded_request=0 threaded_status_before=[0-3] threaded_status_after=[0-3] threaded_packed=true threaded_path_len=[0-9]+ generated_scene_id_len=[0-9]+ packed_scene_pack_error=0 packed_scene_can=true packed_scene_instance_body=true packed_scene_instance_children=[0-9]+ duplicate_is_script=true duplicate_path_len=[0-9]+ deep_duplicate_is_script=true save_ext_has_kt=true save_error=0 save_exists=true save_has_class=true save_uid_set_error=0 save_cleanup_error=0 cached_path_len=[0-9]+ cached_is_script=(true|false) cached_ref_count=[0-9]+"
check "ResourceSaver script_uid=[0-9-]+"
check "Script property replay object_set_amount=777"
check "FileAccess exists=true size_positive=true has_class=true"
check "FileAccess metadata modified_positive=true accessed_nonnegative=true md5_len=32 sha256_len=64 permissions=[0-9]+ hidden=(true|false) read_only=(true|false) xattrs=[0-9]+"
check "FileAccess instance path_len=[0-9]+ abs_path_len=[0-9]+ is_open=true position=0 length_matches=true eof=false first_line_len=[0-9]+ text_has_class=true error=0"
check "FileAccess handle open_present=true open_is_open=true path_len=[0-9]+ line_len=[0-9]+ text_has_class=true temp_present=true temp_open=true temp_path_len=[0-9]+ temp_store=true"
check "FileAccess primitive byte_positive=true word_nonnegative=true dword_nonnegative=true qword_nonnegative=true float_text_len=[0-9]+ double_text_len=[0-9]+ half_text_len=[0-9]+ real_text_len=[0-9]+ big_endian=(true|false) csv_cols=[0-9]+"
check "FileAccess write_fixture string_ok=true string_text=alpha line_ok=true line_text=beta resize_error=0 resize_text=be cleanup_error=0"
check "FileAccess byte_fixture source_bytes_positive=true buffer_len=8 write_bytes_ok=true written_len=2 first=75 second=84 cleanup_error=0"
check "FileAccess numeric_fixture write8=true read8=127 write16=true be16=18-52 write32=true read32=16909060 write64=true read64=72623859790382856 write_double=true read_double=12\\.5 write_float=true read_float=3\\.5 write_half=true read_half_text_len=[0-9]+ write_real=true read_real_text_len=[0-9]+ cleanup_error=0"
check "FileAccess string_fixture pascal_write=true pascal_read=kanama pascal_cleanup=0 csv_write=true csv_cols=2 csv_first=alpha csv_end_matches=true csv_cleanup=0 var_write=true var_read=variant-smoke var_cleanup=0"
if [[ "$UNAME_S" == "Linux" ]]; then
  check "FileAccess attr_fixture xattr_string_set=0 xattr_string_read=value xattr_list_has=true xattr_bytes_set=0 xattr_bytes_len=3 xattr_string_remove=0 xattr_bytes_remove=0 hidden_set=2 hidden=false hidden_reset=2 readonly_set=2 readonly=false readonly_reset=2 permissions_set=0 permissions=420 cleanup_error=0"
elif [[ "$UNAME_S" == MINGW* || "$UNAME_S" == MSYS* || "$UNAME_S" == CYGWIN* ]]; then
  check "FileAccess attr_fixture xattr_string_set=0 xattr_string_read=value xattr_list_has=true xattr_bytes_set=0 xattr_bytes_len=3 xattr_string_remove=0 xattr_bytes_remove=0 hidden_set=0 hidden=true hidden_reset=0 readonly_set=0 readonly=true readonly_reset=0 permissions_set=2 permissions=[0-9]+ cleanup_error=0"
else
  check "FileAccess attr_fixture xattr_string_set=0 xattr_string_read=value xattr_list_has=true xattr_bytes_set=0 xattr_bytes_len=3 xattr_string_remove=0 xattr_bytes_remove=0 hidden_set=0 hidden=true hidden_reset=0 readonly_set=0 readonly=true readonly_reset=0 permissions_set=0 permissions=420 cleanup_error=0"
fi
check "Node self class=Node is_node=true instance_positive=true queued=false inside_tree=true child_count=[0-9]+ index=-?[0-9]+ part_edited=(true|false) scene_path_len=[0-9]+ tree_string_len=[0-9]+ can_process=(true|false) processing=(true|false) physics_processing=(true|false) process_delta_nonnegative=true physics_delta_nonnegative=true tostring_len=[0-9]+ selfas_match=true"
check "Node lookup has_dot=true dot_matches=true missing_null=true parent_is_node=true owner_class_len=[0-9]+"
check "Node3D body found=true target_path=\\.\\./SceneTarget3D target_path_tscn=true pos=1\\.0,2\\.0,3\\.0 translated=1\\.5,2\\.0,2\\.5 global=2\\.0,3\\.0,4\\.0 rot_y=45\\.0 scale=1\\.0,1\\.0,1\\.0 hidden=true visible=true"
check "CharacterBody3D velocity=4\\.0,5\\.0,6\\.0 moved=(true|false) real_len=[0-9]+ delta_len=[0-9]+ up_y=1\\.0 floor=(true|false) wall=(true|false) ceiling=(true|false)"
check "CollisionObject3D layer=3 mask=5 ray_pickable=true priority=2\\.5"
check "CollisionShape3D found=true disabled=true enabled_after_reset=true fill=false color=0\\.25,0\\.5,0\\.75,1\\.0 shape_box=true shape_size=2\\.0,3\\.0,4\\.0 shape_margin=0\\.07999999821186066 shape_bias=0\\.20000000298023224"
check "Camera3D found=true current=true fov=70\\.0 near=0\\.10000000149011612 far=250\\.0 projection=0 cull_mask=1"
check "RayCast3D found=true enabled=true target=0\\.0,-2\\.0,0\\.0 mask=1 bodies=true areas=false colliding=(true|false) point_len=[0-9]+ normal_len=[0-9]+"
check "Area3D found=true monitoring=true monitorable=true gravity=12\\.0 gravity_y=-1\\.0 priority=7 bodies=(true|false) areas=(true|false) body_count=-?[0-9]+ area_count=-?[0-9]+ deferred_set=true"
check "StaticBody3D found=true linear=1\\.0,2\\.0,3\\.0 angular=4\\.0,5\\.0,6\\.0"
check "AudioStreamPlayer3D found=true paused=(true|false) volume=-6\\.0 pitch=1\\.25 max_distance=42\\.0 stream_null=true playing_before=false playing_after_stop=false"
check "AudioStreamPlayer found=true paused=false volume=-6\\.020599842071533 linear=0\\.5 pitch=1\\.100000023841858 bus=Master autoplay=false polyphony=2 position=0\\.0 stream_null=true playing_before=false playing_after_stop=false"
check "AnimationPlayer found=true active=true deterministic=true mixer_process=1 mixer_method=1 mixer_discrete=2 polyphony=3 root_local=true root_pos_len=[0-9]+ root_scale_len=[0-9]+ root_pos_acc_len=[0-9]+ root_scale_acc_len=[0-9]+"
check "AnimationPlayer playback blend=0\\.25 auto_capture=true auto_duration=0\\.5 playing=false animation_active=(true|false) speed_scale=1\\.5 playing_speed=0\\.0 movie_quit=false current_len=0 assigned_len=0 position=0\\.0 length=0\\.0 has_section=false section_start=-1\\.0 section_end=-1\\.0 process=1 method=1"
check "MeshInstance3D found=true layer_mask=7 sorting=2\\.0 sorting_aabb=true shadows=0 lod=1\\.25 transparency=0\\.25 visibility=1\\.0,100\\.0 fade=1 extra_cull=0\\.5 lightmap_texel=1\\.5 ignore_occlusion=true surfaces=1 blend_shapes=0 mesh_box=true mesh_size=1\\.5,2\\.5,3\\.5 mesh_subdivide=1,2,3 mesh_flip=true mesh_uv2=true mesh_uv2_padding=2\\.0"
check "Material3D override=true overlay=true surface=true albedo=0\\.1,0\\.2,0\\.3,0\\.75 metallic=0\\.4000000059604645 roughness=0\\.6000000238418579 shading=0 transparency=1 cull=2 priority=2"
check "InstancedMesh raw_class=MeshInstance3D is_class_match=true typed_lookup=true"
# Non-tool script must run normally in game mode (placeholder gating only kicks in
# under Engine.is_editor_hint()).
check "NonToolScript\\._ready fired"
check "NonToolScript\\._process fired"
# Disabling processing in @OnReady must survive later ScriptInstance lifecycle
# notifications. Re-enabling here makes authority-gated multiplayer inputs run on
# every peer and lets one device control multiple players.
check "ProcessDisableSmoke ready processing=false"
check_absent "ProcessDisableSmoke unexpected process"
# Non-tool @RegisterClass must run in game mode too — runtime gating only
# applies to editor instances.
check "NonToolHelloKanama\\._ready fired"
check "Particles3D gpu_present=true gpu_amount=32 gpu_lifetime=1\\.75 gpu_one_shot=true gpu_pre=0\\.25 gpu_explosive=0\\.5 gpu_random=0\\.125 gpu_fps=30 gpu_fractional=false gpu_speed=1\\.5 gpu_draw=2 gpu_emitting=true cpu_present=true cpu_amount=24 cpu_lifetime=2\\.25 cpu_one_shot=true cpu_pre=0\\.5 cpu_explosive=0\\.25 cpu_random=0\\.375 cpu_fps=20 cpu_fractional=false cpu_speed=0\\.75 cpu_draw=2 cpu_emitting=true"
check "Timer found=true wait=1\\.25 one_shot=true autostart=false paused=true ignore_time_scale=true process=1 time_left_positive=true stopped_before=false stopped_after=true"
check "SceneTreeTimer class=SceneTreeTimer ref_count_positive=true time_left=3\\.0"
check "Tween class=Tween ref_count_positive=true valid_before=true prop_class=PropertyTweener callback_class=CallbackTweener interval_class=IntervalTweener await_class=AwaitTweener await_finished=true step=(true|false) elapsed_nonnegative=true running_after_step=(true|false) loops_left=-?[0-9]+ priority_after_step=5 processed_before_kill=[1-9][0-9]* valid_after_kill=false"
# Virtual-return families (task 29): @OverrideVirtual returns marshalled through the
# script-instance dispatch must arrive in GDScript as the family's Variant type with
# width-exact contents (Int.MAX_VALUE, 1.0e308, full-range bytes).
check "vret mesh aabb_type=true aabb_pos=true aabb_size=true array_type=true array_vals=true dict_type=true dict_vals=true"
check "vret textserver bytes_type=true bytes_vals=true int32_type=true int32_vals=true"
check "vret xr transform3d_type=true transform3d_origin=true vector3_array_type=true vector3_array_vals=true float64_array_type=true float64_array_vals=true"
check "vret graphedit vector2_array_type=true vector2_array_vals=true"
check "vret body2d transform2d_type=true transform2d_vals=true"
check "vret rendersscenedata projection_type=true projection_vals=true"
check "vret control rid_type=true"
# RefCounted return-slot ownership (task 31): every RefCounted-typed ptrcall return
# transfers +1 (required-meta included); self-returning fluent calls must collapse to
# the receiver and release the duplicate, so all wrapper-visible deltas stay 0.
check "Tweener ownership method_delay_delta=0 method_trans_delta=0 method_chained_same=true property_from_delta=0 await_timeout_delta=0 await_chained_same=true"
# ClassDB.instantiate ownership (task 43, GitHub PR #42): the return Variant holds the
# fresh instance's ONLY reference — the owned decode must retain RefCounted results into
# an owning RefCounted wrapper (created_rc=1 exactly; setMeta refs independently to 2;
# close() drops the wrapper's ref back to 1) and leave Node results borrowed/plain.
check "ClassDB instantiate ownership class=Gradient owned_wrapper=true created_rc=1 held_rc=2 closed_rc=1 usable=true node_class=Node node_plain=true"
check_absent "Leaked instance: Gradient"
check_absent "Leaked instance: AwaitTweener"
check_absent "Leaked instance: MethodTweener"
check_absent "Leaked instance: PropertyTweener"
check_absent "Leaked instance: CallbackTweener"
check_absent "Leaked instance: IntervalTweener"
check_absent "Leaked instance: SubtweenTweener"
check_absent "Leaked instance: Tween:"
check "Node controls ready=(true|false) in_group=true group_removed=true group_set=true group_flags=true processing_after_set=true physics_processing_after_set=true processing_input=true shortcut_input=true unhandled_input=true unhandled_key_input=true multiplayer_authority=[0-9-]+ is_multiplayer_authority=(true|false)"
check "Node scalar_controls process_priority=3 physics_process_priority=4 displayed_folded=true unique_name=true editor_description_len=17 tree_node_count_positive=true"
check "Vector helpers v3_len=5\\.0 v3_norm=0\\.0,0\\.6,0\\.8 v3_dot=32\\.0 v3_cross=0\\.0,0\\.0,1\\.0 v3_lerp=1\\.0,2\\.0,3\\.0 v3_limited=2\\.0,0\\.0,0\\.0 v3_distance=2\\.0 v2_len=5\\.0 v2_angle=0\\.0 v2_lerp=1\\.0,1\\.5 v3_withx=9\\.0,2\\.0,3\\.0 v3_withy=1\\.0,9\\.0,3\\.0 v3_withz=1\\.0,2\\.0,9\\.0 v2_withx=9\\.0,2\\.0 v2_withy=1\\.0,9\\.0"
check "Node process_modes mode=3 thread_group=1 thread_messages=3 thread_order=2 internal=true physics_internal=true physics_interp_mode=2 physics_interp=false physics_interp_enabled=(true|false) auto_translate=2 can_auto_translate=false scene_load_flag=true scene_load_flag_reset=false"
check "Object introspection can_revert_name=(true|false) missing_meta=false missing_user_signal=false has_queue_free=true queue_free_args=0 has_script_changed=true script_changed_connections=(true|false) signal_connect=0 signal_callback=Node signal_lambda=Node script_signal_connect=0 script_signal_callback=helper coroutine_started=true blocking=true blocking_after_reset=false translate_disabled=false translate_enabled=true"
check "Object call autoload_present=true describe=audio:3:true:1\\.5:1 add=9 negate=true object=Node returned=Node resource=StandardMaterial3D v2=3\\.0,5\\.0 v3=3\\.0,5\\.0,7\\.0 color=0\\.2,0\\.4,0\\.6,0\\.4 quat=-0\\.1,-0\\.2,-0\\.3,-0\\.4 v4=-1\\.0,-2\\.0,-3\\.0,-4\\.0 rect2=1\\.0,2\\.0,13\\.0,24\\.0 aabb=1\\.0,2\\.0,3\\.0,14\\.0,25\\.0,36\\.0 plane=-1\\.0,-0\\.0,-0\\.0,-5\\.0 basis=2\\.0,2\\.0,2\\.0 t3d=11\\.0,22\\.0,33\\.0 t2d=15\\.0,26\\.0 proj=1\\.0,-13\\.0,-14\\.0,-15\\.0,-16\\.0 v2i=-2,-3 v3i=-2,-3,-4 v4i=-1,-2,-3,-4 rect2i=1,2,13,24 np_described=np:3:foo/bar/baz"
check "UI wrappers ui_present=true pos=8\\.0,12\\.0 size=260\\.0,120\\.0 min=180\\.0,80\\.0 mouse_filter=1 visible_before=true hidden=false shown=true label=kanama label button=kanama button toggle=true pressed=true disabled=false focus_mode=2 focused=true"
check "UI metadata option_item=option-meta option_selected=option-meta option_id=10 tab_count=1 tab_title=Alpha tab_metadata=tab-meta line_bidi_options=0"
check "Dynamic UI label=dynamic label button=dynamic button label_pos=12\\.0,32\\.0 button_pos=12\\.0,56\\.0 child_count=[0-9]+"
check "OS granted_permissions=[0-9]+ memory_info_keys=[0-9]+"
check "Engine singletons count=[0-9]+ has_os=true version_major=[0-9]+ version_minor=[0-9]+ author_keys=[0-9]+ donor_keys=[0-9]+ license_keys=[0-9]+ copyright_entries=[0-9]+ backtraces=[0-9]+"
check "Input joypads count=[0-9]+ joy_info_keys=[0-9]+"
check "Time dictionaries system_dt_year=[0-9]+ system_date_month=[0-9]+ system_time_hour=[0-9]+ unix_dt_year=[0-9]+ unix_date_month=[0-9]+ unix_time_hour=[0-9]+ parsed_weekday=[0-9]+ time_zone_keys=[0-9]+"
check "DirAccess has_hello=true has_addons=true drive_count=[0-9]+"
check "DirAccess instance file_exists=true dir_exists=true current_drive=-?[0-9]+ current_dir_len=[0-9]+ space_left=[0-9]+ fs_type_len=[0-9]+ is_link=(true|false) read_link_len=[0-9]+ is_bundle=(true|false) case_sensitive=(true|false) equivalent=true"
check "DirAccess handle open_present=true file_exists=true current_dir_len=[0-9]+ files_has_hello=true temp_present=true temp_current_dir_len=[0-9]+"
check "DirAccess list_controls files_has_hello=true dirs_has_addons=true include_hidden=false include_nav=false entries_has_hello=true"
check "DirAccess write_fixture make_error=0 make_exists=true make_cleanup_error=0 recursive_error=0 recursive_exists=true recursive_nested_cleanup_error=0 recursive_cleanup_error=0 copy_error=0 copy_exists=true copy_has_class=true rename_error=0 rename_exists=true rename_old_missing=true rename_has_class=true rename_cleanup_error=0"
check "DirAccess instance_write change_error=0 make_error=0 make_exists=true recursive_error=0 recursive_exists=true nested_cleanup_error=0 cleanup_error=0 copy_error=0 copy_exists=true rename_error=0 rename_exists=true rename_old_missing=true rename_cleanup_error=0"
check "DisplayServer name=.* screen_count=[0-9]+"
check "DisplayServer env dark_supported=(true|false) dark=(true|false) touch=(true|false) kept_on=(true|false) keyboard_layouts=[0-9]+ keyboard_current=-?[0-9]+"
check "DisplayServer input mouse=-?[0-9]+,-?[0-9]+ buttons=[0-9]+ keyboard_name_len=[0-9]+ keyboard_lang_len=[0-9]+"
check "DisplayServer passive clipboard=(true|false) image=(true|false) clipboard_len=[0-9]+ primary_len=[0-9]+ cursor=[0-9]+ mouse_mode=[0-9]+ keyboard_focus=-?[0-9]+ swap_cancel=(true|false) additional_outputs=(true|false) hardware_keyboard=(true|false) window_transparency=(true|false) dpi=[0-9]+ max_scale=[0-9.]+ ime_selection=-?[0-9]+,-?[0-9]+ ime_text_len=[0-9]+ tablet_drivers=[0-9]+ tablet_current_len=[0-9]+ tablet_first_len=[0-9]+"
check "DisplayServer tts speaking=(true|false) paused=(true|false) voices=[0-9]+ vk_height=[0-9]+ active_popup=-?[0-9]+ window_instance=-?[0-9]+ window_screen=-?[0-9]+ can_draw=(true|false) focused=(true|false) maximize_allowed=(true|false) max_dbl=(true|false) min_dbl=(true|false)"
check "DisplayServer accessibility screen_reader=-?[0-9]+ contrast=-?[0-9]+ reduce_animation=-?[0-9]+ reduce_transparency=-?[0-9]+ window_max=-?[0-9]+,-?[0-9]+ window_min=-?[0-9]+,-?[0-9]+ window_pos=-?[0-9]+,-?[0-9]+ window_pos_decorated=-?[0-9]+,-?[0-9]+ window_size=-?[0-9]+,-?[0-9]+ window_size_decorated=-?[0-9]+,-?[0-9]+"
check "kt script methods size = 9"
check "kt script properties size = 15"
check "kt script signals size = 1"
check "kt script replace_smoke_scene = true"
check "kt script rpc config ok = true"
check "kt script rpc replace_smoke_scene error = 0"
check "script property cleanup smoke_scene type=PackedScene"
# releaseRefCounted's last unreference() destroys the owner directly since the
# refcount_decremented fix; the old "destroy=true ref_count=0" variant was the
# shutdown zombie-sweeper's trace, whose premise (undying scripted RefCounteds)
# is gone.
check "script property cleanup RefCounted handle=0x[0-9a-f]+ destroy=true"
check "destroyed [0-9]+/[0-9]+ tracked KanamaScript object\\(s\\)"
check "unregistered [0-9]+ extension class\\(es\\)"
check_absent "Orphan StringName"
check_absent "unclaimed string names"
check_absent "Cannot ptrcall nil constructor"
check_absent "Unable to get the RPC configuration"
check_absent "RPC config metadata missing or malformed"
check_absent "local RPC smoke failed"
check_absent "Leaked instance: FileAccess"
check_absent "Leaked instance: DirAccess"
check_absent "Resource still in use: .*Resource_smoke"

echo "[runtime_smoke] PASS"

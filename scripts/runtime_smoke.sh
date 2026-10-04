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

KANAMA_TRACE_SCRIPT_PROPERTY_CLEANUP=1 KANAMA_TRACE_NATIVE_ADAPTERS=1 "$GODOT_BIN" --headless --editor --quit-after 120 --path "$PROJECT_DIR_FOR_GODOT" --verbose >"$IMPORT_LOG_FILE" 2>&1

if ! grep -q '"class": &"SmokeResource"' "$GLOBAL_CLASS_CACHE" ||
   ! grep -q '"base": &"Resource"' "$GLOBAL_CLASS_CACHE"; then
  echo "[runtime_smoke] SmokeResource missing from global_script_class_cache.cfg (issue #39 regression)"
  # Diagnostics only; the verdict is the exit 1 below. Say so when the cache was never written.
  if [[ -f "$GLOBAL_CLASS_CACHE" ]]; then
    cat "$GLOBAL_CLASS_CACHE"
  else
    echo "[runtime_smoke] $GLOBAL_CLASS_CACHE was not written at all"
  fi
  exit 1
fi
KANAMA_TRACE_SCRIPT_PROPERTY_CLEANUP=1 KANAMA_TRACE_NATIVE_ADAPTERS=1 "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" --quit --verbose >"$LOG_FILE" 2>&1
KANAMA_TRACE_SCRIPT_PROPERTY_CLEANUP=1 KANAMA_TRACE_NATIVE_ADAPTERS=1 "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" res://resource_owner_smoke.tscn --quit --verbose >>"$LOG_FILE" 2>&1
KANAMA_TRACE_SCRIPT_PROPERTY_CLEANUP=1 KANAMA_TRACE_NATIVE_ADAPTERS=1 "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" res://self_smoke.tscn --quit --verbose >>"$LOG_FILE" 2>&1
# task 131 -- a throwing _ready (script_error_smoke.tscn) and lambda-connection release
# (signal_leak_smoke.tscn), each in its own process so nothing else touches their state.
KANAMA_TRACE_NATIVE_ADAPTERS=1 "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" res://script_error_smoke.tscn --quit --verbose >>"$LOG_FILE" 2>&1
KANAMA_TRACE_NATIVE_ADAPTERS=1 "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" res://signal_leak_smoke.tscn --quit --verbose >>"$LOG_FILE" 2>&1
# task 131 items 2 + 6 -- wrapper equality and a call through a wrapper of a freed object.
KANAMA_TRACE_NATIVE_ADAPTERS=1 "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" res://freed_object_smoke.tscn --quit --verbose >>"$LOG_FILE" 2>&1
# task 134 A2 -- value types store Godot's width: Kotlin and GDScript print the same three lines.
KANAMA_TRACE_NATIVE_ADAPTERS=1 "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" res://value_type_storage_smoke.tscn --quit --verbose >>"$LOG_FILE" 2>&1
# task 133 C2 -- Color as a script type, beside its GDScript twin (color_script_smoke.tscn).
KANAMA_TRACE_NATIVE_ADAPTERS=1 "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" res://color_script_smoke.tscn --quit-after 600 --verbose >>"$LOG_FILE" 2>&1
# task 134 B -- every value-type operator and method against GDScript (builtin_parity_ref.gd).
KANAMA_TRACE_NATIVE_ADAPTERS=1 "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" res://builtin_parity_smoke.tscn --quit --verbose >>"$LOG_FILE" 2>&1
# task 134 B -- builtin calls re-entered from an engine error print (a GDScript logger calling Kotlin).
KANAMA_TRACE_NATIVE_ADAPTERS=1 "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" res://builtin_reentry_smoke.tscn --quit --verbose >>"$LOG_FILE" 2>&1
# task 133 -- node/script delegates, checked casts, preload, tree accessors and the script coroutine
# scope; the scene quits itself once its async rows (wait, nextFrame, cancel on free) have printed.
KANAMA_TRACE_NATIVE_ADAPTERS=1 "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" res://script_access_smoke.tscn --quit-after 5000 --verbose >>"$LOG_FILE" 2>&1
# task 132 -- owned RefCounted wrappers dropped without close() are released by the GC fallback
# (green), and stay leaked with the fallback off (red: KANAMA_GC_RELEASES=0, its own log, since its
# shutdown reports the 10,000 leaked Resources on purpose). The scene quits itself once the count is
# back; --quit-after is only the cap.
OWNED_RELEASE_RED_LOG="${LOG_FILE}.owned_release_red"
# task 132 blocker 1 -- a script object keeps its RefCounted owner alive (the City-Builder pattern:
# load, keep only the script object, GC, use it), and the owner still dies once it is dropped. The
# red run (KANAMA_SCRIPT_OWNER_LINKS=0: no owner link, the first task 132 commit) loses the owner.
SCRIPT_OWNER_RED_LOG="${LOG_FILE}.script_owner_red"
KANAMA_TRACE_NATIVE_ADAPTERS=1 "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" res://script_owner_smoke.tscn --quit-after 600 --verbose >>"$LOG_FILE" 2>&1
KANAMA_SCRIPT_OWNER_LINKS=0 "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" res://script_owner_smoke.tscn --quit-after 600 >"$SCRIPT_OWNER_RED_LOG" 2>&1
# task 132 round 2 -- the ResourceCache re-reference window (the reviewer's probe): a cached resource
# whose KanamaScript object was collected is loaded again before the drain; its rebuilt instance is
# refilled from the file (GDScript re-parses it), never left at the Kotlin defaults.
KANAMA_TRACE_NATIVE_ADAPTERS=1 "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" res://cache_recreate_probe.tscn --quit-after 300 --verbose >>"$LOG_FILE" 2>&1
# task 132 round 4 -- the same window, with the rebuilt instance never used before its owner dies:
# nothing is constructed inside refcount_incremented (it runs under the loader locks) nor while
# the owner is freed (803d04d6: after_reload=3, constructions_at_end=4).
KANAMA_TRACE_NATIVE_ADAPTERS=1 "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" res://refill_on_free_probe.tscn --quit-after 300 --verbose >>"$LOG_FILE" 2>&1
# task 132 (after the rebase) -- a property setter's references are the owner's, not the Kotlin
# object's: a KanamaScript resource's List<KanamaScript resource> property, set, re-set, then the
# owner dropped (its Kotlin object collected first) -> every element dies (883c936c: the elements
# leaked, the City-Builder "resources still in use at exit"). Its --verbose leak report lands in the
# main log, where `check_absent "Leaked instance: Resource:"` covers it too.
KANAMA_TRACE_NATIVE_ADAPTERS=1 "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" res://property_retain_smoke.tscn --quit-after 300 --verbose >>"$LOG_FILE" 2>&1
# task 132 review -- what a property holds, over the script's lifetime: set_script(null) on a live
# resource lets its script object (and its items) go (a9b495ac: swap_items_dead=false, pinned by
# the cleaner); a setter on a worker thread; a Kotlin alias of an engine-set resource survives the
# next set (a9b495ac: alias_valid=false); Node-typed exports take no reference.
KANAMA_TRACE_NATIVE_ADAPTERS=1 "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" res://property_lifetime_smoke.tscn --quit-after 300 --verbose >>"$LOG_FILE" 2>&1
# task 132 D7 -- the same freed-object scene with the instance-binding check (opt-in), own log.
FREED_BINDING_LOG="${LOG_FILE}.freed_binding"
KANAMA_FREED_OBJECT_CHECKS=binding "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" res://freed_object_smoke.tscn --quit >"$FREED_BINDING_LOG" 2>&1
# The green run also turns kanama/debug/log_gc_releases on through a transient override.cfg (D5:
# one "released by GC" line per creation site).
OWNED_RELEASE_OVERRIDE="$PROJECT_DIR/override.cfg"
printf '[kanama]\n\ndebug/log_gc_releases=true\n' >"$OWNED_RELEASE_OVERRIDE"
trap 'rm -f "$OWNED_RELEASE_OVERRIDE"' EXIT
OWNED_RELEASE_GREEN_LOG="${LOG_FILE}.owned_release_green"
KANAMA_TRACE_NATIVE_ADAPTERS=1 "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" res://owned_release_smoke.tscn --quit-after 600 --verbose >"$OWNED_RELEASE_GREEN_LOG" 2>&1
cat "$OWNED_RELEASE_GREEN_LOG" >>"$LOG_FILE"
rm -f "$OWNED_RELEASE_OVERRIDE"
KANAMA_GC_RELEASES=0 "$GODOT_BIN" --headless --path "$PROJECT_DIR_FOR_GODOT" res://owned_release_smoke.tscn --quit-after 600 >"$OWNED_RELEASE_RED_LOG" 2>&1

# Report a failed assertion. The log tail is verbose Godot output, so the reason is
# restated *after* it -- otherwise the one line that matters ends up ~120 lines above the
# end of the output and reads as a silent non-zero exit.
smoke_fail() {
  local kind="$1" pattern="$2"
  echo "[runtime_smoke] $kind: $pattern" >&2
  echo "[runtime_smoke] log tail:" >&2
  tail -n 120 "$LOG_FILE" >&2
  echo >&2
  echo "[runtime_smoke] FAIL -- $kind: $pattern" >&2
  echo "[runtime_smoke] full log: $LOG_FILE" >&2
  exit 1
}

check() {
  local pattern="$1"
  if ! grep -Eq -- "$pattern" "$LOG_FILE"; then
    smoke_fail "missing pattern" "$pattern"
  fi
}

check_absent() {
  local pattern="$1"
  if grep -Eq -- "$pattern" "$LOG_FILE"; then
    smoke_fail "unexpected pattern" "$pattern"
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
# issue #38 — newScriptInstance() creates a script-backed custom resource from Kotlin
# at runtime: the instance is live, saves (owned reference survives the transient Ref<>
# inside ResourceSaver.save), and the saved .tres references the SmokeResource.kt script
# and stores the "forged" payload. The probe releases its owned reference, so no leak.
check "ResourceForgeSmoke live=true save_error=0 script_ref=true payload_saved=true reload_ok=true"
check_absent "ResourceForgeSmoke create_failed"
# PR #44 review — the owning reference must be released via the supported path (OwnedScriptResource
# .close()/use), not leaked. Reverting to the bare-T return + Resource.fromHandle().close() (or
# dropping the use{}) reintroduces a per-call Resource leak that Godot reports on shutdown.
check_absent "Leaked instance: Resource:"
# task 61 / issue #91 — a StandardMaterial3D.create()'d resource handed to the engine (surface
# override *and* material override, two distinct Ref<Material> sinks) and released via use{}/close()
# must survive ResourceSaver.save. With owning-create(), close() drops only the wrapper's reference;
# the engine keeps its own. Pre-fix, close() unreferenced the engine's only reference to zero and
# freed the material, so the saved scene lost it (…_has_material=false) and Godot logged
# `Parameter "material" is null`. Every created resource is use{}-closed, so no material/mesh leak.
check "MaterialHandoffSmoke create_refcount=1 surface_has_material=true override_has_material=true"
check_absent "Parameter \"material\" is null"
check_absent "Leaked instance: StandardMaterial3D"
check "kt script export groups group=true subgroup=true"
# task 32 — custom enum exports: INT + PROPERTY_HINT_ENUM metadata, ordinal
# round-trip via set/get, tscn-stored int deserialized into the enum slot, and
# out-of-range stored ints clamped to a valid entry (no crash).
check "kt script enum export type=true hint=true hint_string=true tscn=true roundtrip=true clamp=true"
# task 38 — enum-list exports: ARRAY + PROPERTY_HINT_TYPE_STRING "2/2:<entries>",
# ordinal-array round-trip via set/get, tscn deserialize, per-element clamp, and
# inspector Add-Element null-fill defaulting to the first entry.
check "kt script enum list export type=true hint=true hint_string=true tscn=true roundtrip=true clamp=true nullfill=true"
# task 128 B — Godot enum value classes (`var mode: Node.ProcessMode`) as script members: INT +
# PROPERTY_HINT_ENUM / _FLAGS with Godot's names-with-values hint, GDScript's class marker
# (PROPERTY_USAGE_CLASS_IS_ENUM / _CLASS_IS_BITFIELD + class_name), the Godot values (not ordinals)
# through the scene, Object.set/get, a registered function and a signal, read back typed in Kotlin;
# engine virtuals with an enum parameter / return and the required `_get_space_state` object return;
# a @RegisterClass object return (HelloKanama.self_object) through varcall and ptrcall.
check "GodotEnumExportSmoke typed mode=true flags=true list=true default=true signal=true"
check "godot enum export mode_meta=true flags_meta=true class_meta=true list_meta=true default=true tscn=true roundtrip=true function=true"
check "godot enum virtuals enum_arg=true enum_return=true required_object_return=true register_class_object_return=true"
# task 133 C -- typed hint annotations match a GDScript twin's get_property_list() row by row (and
# the folded `Mathf.PI / 3.0` default), the generated Autoloads object, and script inheritance.
check "export hint twin rows=38 mismatches=0 folded_default=true"
check "autoload kotlin=autoload:1 gd=KanamaSmokeAutoload:5 missing=true wrong_class=true wrong_script=true thread=same=true unresolved=true"
check "inheritance exports=true values=true methods=true override_wins=true ready_once=true signal=true"
# task 133 C2 -- a generic base's members typed as members of the script class (asMemberOf).
check "generic inheritance export=true value=true override=true echo=true"
# task 50 — a throwing user @Export accessor must be contained by ScriptBridge's
# siSet/siGet rather than escaping the FFM upcall and aborting the process. A failed set is
# rejected (previous value survives), a failed get yields null, and the property recovers.
# Removing the containment makes Godot exit 134 before printing this line at all.
check "kt script accessor containment tscn_rejected=true baseline=true set_rejected=true set_recovered=true get_contained=true get_recovered=true"
# task 51 — Resource extends RefCounted, so setMeta/getMeta (GodotObject surface) work on a
# Resource directly (no asObject() hop). set_get=51 proves the inherited meta round-trips.
check "task51 resource meta set_get=51 has=true"
# task 52a / issue #60 — Node.setOwner(null) clears the owner through the typed wrapper
# (and the writable owner: Node? property). Reverting to a non-null setOwner fails to compile.
check "task52a setOwner set=true cleared=true prop_set=true prop_cleared=true"
# MutableList export (preserves mutability in the setter): ARRAY + PROPERTY_HINT_TYPE_STRING,
# set/get round-trip for a mutable typed list.
check "kt script mutable list export type=true hint=true hint_string=true roundtrip=true"
# issue #40 — typed Map exports: DICTIONARY + PROPERTY_HINT_DICTIONARY_TYPE with a
# "<key>;<value>" hint string, set/get round-trip for a scalar map, an enum-valued map,
# a long-keyed custom-resource map (Map<Long, SmokeResource>), a value-type-keyed
# scalar map (Map<Vector2i, Long>), a value-type-keyed resource map
# (Map<Vector2i, SmokeResource>), and a MutableMap (MutableMap<String, Long>).
check "kt script dictionary export scalar_type=true tscn_scalar_map=true scalar_hint=true scalar_hint_string=true scalar_roundtrip=true enum_roundtrip=true region_type=true region_hint_string=true region_roundtrip=true vector_key_hint_string=true vector_key_roundtrip=true vector_resource_type=true vector_resource_hint_string=true vector_resource_roundtrip=true mutable_type=true mutable_hint=true mutable_hint_string=true mutable_roundtrip=true"
# issue #40 review — malformed Dictionary input is contained by the fail-soft decode: wrong-typed
# keys/values drop instead of throwing a ClassCastException out of the FFM upcall (which aborted
# the process, exit 134). Reaching this line at all proves survival; reverting the fail-soft decode
# makes Godot exit before printing it.
check "kt script dictionary malformed survived=true wrong_key_empty=true wrong_value_dropped=true wrong_vector_key_dropped=true"
# task 50 — the standing malformed-input matrix: a wrong-typed value is fed to a representative
# @Export of every exported shape (scalar/Double/String/NodePath/enum/enum-list/wrapper-
# list/resource/PackedScene/@ScriptClass-list). Reaching this line proves the process survived
# (an uncontained cast in a generated setter/getter aborts the JVM before it prints); all_readable
# proves each getter still returns a defined value. Removing siSet/siGet containment fails here.
check "kt script malformed_matrix survived=true all_readable=true"
# issue #40 review — nullable scalar Map value (Map<String, Long?>) preserves a null value's key
# (C# parity), and a wrong-typed value nulls rather than dropping. Round-trips through the write path.
check "kt script dictionary nullable_value null_preserved=true wrong_nulled=true object_null_dropped=true"
# task 41 — a Kanama @GlobalClass script resource matches a plain GDScript global class for the
# ClassDB pair (can_instantiate=true, instantiate=null: script classes aren't engine classes),
# and the supported base-Resource + set_script construction recipe works.
check "kt script classdb_parity kanama_can=true kanama_instantiate_null=true gdscript_can=true gdscript_instantiate_null=true recipe_works=true"
# issue #106 — GDScript static typing against a Kanama @GlobalClass: same_load proves the
# loaded .kt Script is ResourceCache-cached (one object per path), and the typed member/local
# assignments plus `is` prove the analyzer and VM accept the class as its own type. The loader
# must not pre-set the script path in _load; doing so regresses all five to a parse error.
# task 64 — PropertyInfo.class_name on object-typed Kotlin exports, both metadata paths
# (instance PropertySpec structs and script-level dictionaries), plus a statically typed
# member read (`var m: SmokeResource = hs.smoke_resource` on a typed HelloScript var).
check "kt script typed_global_class same_load=true member_null=true typed=true is_check=true roundtrip=true member_matches=true instance_class_name=true script_class_name=true"
check "Mathf lerp=2\\.5 clamp=10 wrap=1 approx=true round=3 lerpf=2\\.5 clampf=10\\.0 sinf=0\\.0 sqrtf=3\\.0"
# task 78 — GD's Variant-taking utilities must encode arguments as real Variants. The old
# encoder knew six scalar types and stringified everything else, so is_instance_valid was
# handed a STRING and returned false for every LIVE object (and typeof reported TYPE_STRING
# for objects and value types alike). type_of_node=24 (TYPE_OBJECT), type_of_vector3=9
# (TYPE_VECTOR3) and valid_live=true all fail if the toString() fallback returns; the
# _freed=false pair proves the check still reports a dead instance.
check "GD variant utilities type_of_node=24 hash_nonzero=true valid_live=true valid_freed=false id_valid_live=true id_valid_freed=false valid_null=false type_of_string=4 type_of_int=2 type_of_vector3=9"
check "Generated name constants ok=true"
check "ProjectSettings string_list=alpha\\|beta"
check "ProjectSettings dictionary name=kanama enabled=true count=2 scale=1\\.5"
check "ResourceLoader exists=true has_hello=true loaded_path_len=[0-9]+ loaded_is_script=true loaded_ref_count=[0-9]+ loaded_name_len=[0-9]+ loaded_scene_id_len=[0-9]+ loaded_path_id_len=[0-9]+ loaded_built_in=(true|false) loaded_local_to_scene=(true|false) threaded_request=0 threaded_status_before=[0-3] threaded_status_after=[0-3] threaded_packed=true threaded_path_len=[0-9]+ generated_scene_id_len=[0-9]+ packed_scene_pack_error=0 packed_scene_can=true packed_scene_instance_body=true packed_scene_instance_children=[0-9]+ duplicate_is_script=true duplicate_path_len=[0-9]+ deep_duplicate_is_script=true save_ext_has_kt=true save_error=0 save_exists=true save_has_class=true save_uid_set_error=0 save_cleanup_error=0 cached_path_len=[0-9]+ cached_is_script=(true|false) cached_ref_count=[0-9]+"
check "ResourceSaver script_uid=[0-9-]+"
# issue #81 — the reporter's repro: PackedScene.create() -> pack -> ResourceSaver.save. save decodes
# its Ref<Resource> argument into a transient Ref and releases it; pre-fix that freed the not-yet-
# owned scene (its only reference was the construction placeholder), so ref_after_save / alive_after
# _save below dereferenced freed memory and aborted the process. Confirmed by removing the guard:
# getReferenceCount() here aborts (exit 134). ref_after_save>=1 + alive_after_save=true prove the
# save guard kept the scene live across the call.
check "issue81 packed_scene_save pack_error=0 save_error=0 ref_after_save=[1-9][0-9]* alive_after_save=true save_exists=true"
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
check "Material3D override=true overlay=true surface=true albedo=0\\.10000000149011612,0\\.20000000298023224,0\\.30000001192092896,0\\.75 metallic=0\\.4000000059604645 roughness=0\\.6000000238418579 shading=0 transparency=1 cull=2 priority=2"
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
# task 98 — lifetime safety. GD.isInstanceValid answers through the instance id the wrapper
# captured at construction, so asking it about a freed object is safe (it used to build an
# OBJECT Variant from the raw pointer and read the freed header). Every RefCounted-derived
# wrapper — generated (Image, Material) and hand-shaped (BaseMaterial3D) — refuses a call
# through a closed handle, receiver- and argument-side, with the same IllegalStateException the
# hand-shaped Tween family raised before (Mesh is generated since task 117 P1'(a)). Removing the guard turns closed_* into "none" (or
# a native fault); removing the id capture turns instance_valid_after_free into UB.
check "LifetimeSmoke instance_valid_alive=true instance_valid_after_free=false id_after_free_valid=false id_matches_ptrcall=true closed_receiver=IllegalStateException:RefCounted handle is closed closed_inherited=IllegalStateException:RefCounted handle is closed closed_generated=IllegalStateException:RefCounted handle is closed closed_argument=IllegalStateException:RefCounted handle is closed"
check_absent "Leaked instance: Image"
# task 98 — structural upcall containment (Upcalls.stub wraps every stub in
# MethodHandles.catchException). A registered function that throws is logged with the site label
# and the engine receives the zero default; GDScript sees null and keeps running. Removing the
# containment makes the exception unwind through native frames and abort Godot (exit 134)
# before the survived= line is printed.
check "\[kanama\] upcall [A-Za-z0-9_]+\.call_smoke_throw threw: java\.lang\.IllegalStateException: kanama smoke: deliberate upcall failure"
check "upcall containment survived=true result_null=true"
# task 131 (F4) -- a Kotlin exception is a Godot script error, not only a stderr trace (the editor's
# Play does not capture stderr). ScriptErrorSmoke's _ready throws; Godot itself must print the
# SCRIPT ERROR with the exception and the Kotlin file:line of the throw. Before task 131 neither
# line appeared: the trace went to stderr and the failed _ready was silent in Godot's output.
script_error_line="$(grep -n 'deliberate _ready failure' "$PROJECT_DIR/ScriptErrorSmoke.kt" | cut -d: -f1)"
check "^SCRIPT ERROR: java\.lang\.IllegalStateException: kanama smoke: deliberate _ready failure$"
# The file is reported as its res:// path (the editor's Errors tab can open it).
check "^ +at: ScriptErrorSmoke\.ready \(res://ScriptErrorSmoke\.kt:${script_error_line}\)$"
# The stderr trace is kept beside it.
check "\[kanama:kt\] script method failed script=net\.multigesture\.kanama\.example\.ScriptErrorSmoke "
# task 131 (F9) -- a lambda connection's closure is released whenever Godot drops the connection's
# custom Callable: the receiver freed, the emitter freed, a ONE_SHOT connection fired. The registry
# returns to its size before the connect. Before task 131 every *_released was false (only
# SignalConnection.close() released an entry).
check "SignalLeakSmoke connected=1 fired=1 free_released=true after_free_fired=0 emitter_connected=1 emitter_released=true one_shot_connected=1 one_shot_fired=1 one_shot_released=true"
# A contained Kotlin error returns CALL_OK with a nil return, as a GDScript runtime error does, so
# Godot adds no "method not found" style follow-up for a method that exists.
check_absent "Invalid call\. Nonexistent function"
# task 131 item 6 (F23) -- wrapper equality is object identity (the instance id), not JVM identity:
# a Node and a Node3D wrapper of one object are ==, hash alike and collapse in a Set; it still holds
# after the object is freed. Before task 131 equal=false and set_size=3.
# task 131 item 2 (F2) -- the editor binary is a debug build, so the freed-object check is on.
check "\[kanama:kt\] freed-object checks: on"
# task 132 -- the GC fallback release: 10,000 dropped owned Resources are gone again after GC +
# drain (object count back to the baseline), and a getter's +1 that was closed and then collected
# is released once (the mesh keeps exactly its two references).
check "\[kanama:kt\] owned-reference GC releases: on \(java\.lang\.ref\.Cleaner\)"
check "\[kanama:kt\] owned-reference GC releases: on \(java\.lang\.ref\.Cleaner, logging GC releases\)"
check "OwnedReleaseSmoke dropped=10000"
# D5: the 10,000 dropped Resources share one creation site, so one line names it.
owned_drop_line="$(grep -n 'repeat(DROPPED_RESOURCES)' "$PROJECT_DIR/OwnedReleaseSmoke.kt" | cut -d: -f1)"
check "released by GC: Resource \(created at OwnedReleaseSmoke\.kt:${owned_drop_line}\)"
if [[ "$(grep -cF "released by GC: Resource (created at OwnedReleaseSmoke.kt:${owned_drop_line})" "$LOG_FILE")" != 1 ]]; then
  smoke_fail "one GC-release line per creation site" "released by GC: Resource (created at OwnedReleaseSmoke.kt:${owned_drop_line})"
fi
check "OwnedReleaseSmoke baseline=[0-9]+ after_drop=[0-9]+ after_gc=[0-9]+ back_to_baseline=true mesh_refcount=2 "
# A wrapper dropped just before quit() is released by the shutdown GC (D4, at the editor or scene
# deinitialization level), so Godot's leak report names nothing; the drain is timed (no stall).
if grep -Eq "Leaked instance|ObjectDB instances leaked|Resources still in use at exit" "$OWNED_RELEASE_GREEN_LOG"; then
  echo "[runtime_smoke] FAIL -- a wrapper dropped before quit leaked:" >&2
  grep -E "Leaked instance|leaked|still in use" "$OWNED_RELEASE_GREEN_LOG" >&2
  exit 1
fi
check "shutdown GC releases \((editor|scene)\): [1-9][0-9]* in [0-9]+ ms"
# The red run: with the fallback off the same drop stays leaked (and the mesh keeps the 100 +1s).
if ! grep -Eq "OwnedReleaseSmoke baseline=[0-9]+ after_drop=[0-9]+ after_gc=[0-9]+ back_to_baseline=false mesh_refcount=102 " "$OWNED_RELEASE_RED_LOG"; then
  echo "[runtime_smoke] FAIL -- the KANAMA_GC_RELEASES=0 red run did not leak as expected:" >&2
  grep -E "OwnedReleaseSmoke|owned-reference GC releases" "$OWNED_RELEASE_RED_LOG" >&2 || tail -n 40 "$OWNED_RELEASE_RED_LOG" >&2
  exit 1
fi
# Holding a freed wrapper is silent, as in GDScript: two exported-property reads and a script
# method return of it give Godot null (no error; counted below).
# task 134 A2 -- a position written and read back is `==` (its x is not `== 0.1`, as in GDScript),
# toString() is GDScript's str(), and Kotlin-side arithmetic has the engine's float32 bits: each
# Kotlin line must equal the GDScript line printed in the same run (value_type_storage_ref.gd).
# The parity row hashes the float32 bits of every operation the docs call bit-identical over 256
# fixed-seed random inputs, per operation, so one differing bit in one result fails the row.
check "ValueTypeStorage kotlin roundtrip_eq=true str=\\(0\\.1, 0\\.2\\) x_eq_literal=false$"
check "ValueTypeStorage kotlin str=\\(0\\.1, 0\\.2\\)\\|\\(1\\.0, 2\\.0, 3\\.0\\)\\|\\(12345\\.68, -0\\.000001\\)\\|"
check "ValueTypeStorage kotlin parity=n=256 v2_add=[0-9a-f]+ "
for vts_row in roundtrip_eq str bits parity; do
  vts_kotlin="$(grep -o "ValueTypeStorage kotlin ${vts_row}=.*" "$LOG_FILE" | head -n 1 | sed 's/^ValueTypeStorage kotlin //')"
  vts_gdscript="$(grep -o "ValueTypeStorage gdscript ${vts_row}=.*" "$LOG_FILE" | head -n 1 | sed 's/^ValueTypeStorage gdscript //')"
  if [[ -z "$vts_kotlin" || "$vts_kotlin" != "$vts_gdscript" ]]; then
    smoke_fail "Kotlin/GDScript value-type mismatch (${vts_row})" "kotlin: ${vts_kotlin:-<missing>} gdscript: ${vts_gdscript:-<missing>}"
  fi
done
# task 133 C2 -- a Color export stored in a .tscn, read back, set/get through Object, passed to and
# returned from a function and carried by a signal (to a GDScript lambda and Kotlin's typed
# connection), plus its property row and @ExportColorNoAlpha's hint 21: the Kotlin line must equal
# the GDScript twin's line from the same run.
check "ColorScript kotlin scene=0\\.25,0\\.5,0\\.123456[0-9]*,0\\.75 type=20 rows=20/0//20/21/ default=0\\.1[0-9]*,0\\.2[0-9]*,0\\.3[0-9]*,0\\.4[0-9]* "
color_kotlin="$(grep -o "ColorScript kotlin scene=.*" "$LOG_FILE" | head -n 1 | sed 's/^ColorScript kotlin //')"
color_gdscript="$(grep -o "ColorScript gdscript scene=.*" "$LOG_FILE" | head -n 1 | sed 's/^ColorScript gdscript //')"
if [[ -z "$color_kotlin" || "$color_kotlin" != "$color_gdscript" ]]; then
  smoke_fail "Kotlin/GDScript Color script type mismatch" "kotlin: ${color_kotlin:-<missing>} gdscript: ${color_gdscript:-<missing>}"
fi
# task 133 C3 -- HDR and NaN channels (set/get, function, return, signal) match GDScript too.
check "ColorScript kotlin hdr=2\\.5,nan,-0\\.5,1\\.0\\|"
color_hdr_kotlin="$(grep -o "ColorScript kotlin hdr=.*" "$LOG_FILE" | head -n 1 | sed 's/^ColorScript kotlin //')"
color_hdr_gdscript="$(grep -o "ColorScript gdscript hdr=.*" "$LOG_FILE" | head -n 1 | sed 's/^ColorScript gdscript //')"
if [[ -z "$color_hdr_kotlin" || "$color_hdr_kotlin" != "$color_hdr_gdscript" ]]; then
  smoke_fail "Kotlin/GDScript HDR/NaN Color mismatch" "kotlin: ${color_hdr_kotlin:-<missing>} gdscript: ${color_hdr_gdscript:-<missing>}"
fi
# task 134 B -- the generated probe pair (scripts/generate_builtin_ops.py): `pure=` hashes every
# value-type operator and every Kotlin-implemented method over 256 fixed-seed random inputs,
# `edge=` the same members over ±0, NaN, ±INF, .5 ties and 1e-30 (where Godot's result is
# defined), `facade=` every engine-backed method over 8, `const=` every builtin constant and enum
# value; each Kotlin line must equal the GDScript line, and a mismatch names the differing members.
check "BuiltinParity kotlin pure=n=256 [^ ]+=[0-9a-f]+ "
check "BuiltinParity kotlin edge=n=64 [^ ]+=[0-9a-f]+ "
check "BuiltinParity kotlin facade=n=8 [^ ]+=[0-9a-f]+ "
check "BuiltinParity kotlin const=n=1 [^ ]+=[0-9a-f]+ "
for bp_row in pure edge facade const; do
  bp_kotlin="$(grep -o "BuiltinParity kotlin ${bp_row}=.*" "$LOG_FILE" | head -n 1 | sed 's/^BuiltinParity kotlin //')"
  bp_gdscript="$(grep -o "BuiltinParity gdscript ${bp_row}=.*" "$LOG_FILE" | head -n 1 | sed 's/^BuiltinParity gdscript //')"
  if [[ -z "$bp_kotlin" || "$bp_kotlin" != "$bp_gdscript" ]]; then
    bp_diff="$(comm -3 <(tr ' ' '\n' <<<"$bp_kotlin" | sort) <(tr ' ' '\n' <<<"$bp_gdscript" | sort) | head -n 20 | tr '\n' ' ')"
    smoke_fail "Kotlin/GDScript builtin parity mismatch (${bp_row})" "differing entries (kotlin | gdscript): ${bp_diff:-<missing line>}"
  fi
done
# task 134 B -- a builtin that warns or errors can re-enter Kotlin (builtin_reentry_logger.gd calls
# the probe from inside the print), and the nested builtin calls must not overwrite the frame the
# engine is still reading: Basis.lookingAt with a colinear up, Color.html with a bad code, a nested
# slerp. Each Kotlin result must equal GDScript's, and the logger must really have re-entered.
check "BuiltinReentry kotlin hits=reentered$"
for br_row in basis merge html nested; do
  br_kotlin="$(grep -o "BuiltinReentry kotlin ${br_row}=.*" "$LOG_FILE" | head -n 1 | sed 's/^BuiltinReentry kotlin //')"
  br_gdscript="$(grep -o "BuiltinReentry gdscript ${br_row}=.*" "$LOG_FILE" | head -n 1 | sed 's/^BuiltinReentry gdscript //')"
  if [[ -z "$br_kotlin" || "$br_kotlin" != "$br_gdscript" ]]; then
    smoke_fail "re-entered builtin call differs from GDScript (${br_row})" "kotlin: ${br_kotlin:-<missing>} gdscript: ${br_gdscript:-<missing>}"
  fi
done
# The Web value types' parity test (WebBuiltinParityTest) asserts the GDScript hashes recorded in
# scripts/fixtures/builtin_parity_expected.json; they must still be what GDScript prints here.
if ! python3 "$ROOT_DIR/scripts/generate_builtin_ops.py" --verify-recorded "$LOG_FILE" >&2; then
  smoke_fail "recorded Web builtin parity hashes" "stale: python3 scripts/generate_builtin_ops.py --record-parity $LOG_FILE"
fi
check "FreedObjectSmoke equal=true same_hash=true set_size=2 not_equal=true valid_after_free=false equal_after_free=true to_string=<Freed Object> property_reads=null,null method_return=null survived=true result_null=true"
# A call through the freed wrapper throws IllegalStateException instead of dereferencing the dead
# pointer (before task 131: a use-after-free, typically a native crash and no line below at all).
check "FreedObjectSmoke caught=Invalid access to previously freed instance \(Node3D, instance id [0-9]+\)$"
# Uncaught in a script method Godot calls, it is a Godot script error at the game line, and the
# caller (ready) carries on with a nil result (survived=true above).
freed_call_line="$(grep -n 'fun callFreed' "$PROJECT_DIR/FreedObjectSmoke.kt" | cut -d: -f1)"
check "^SCRIPT ERROR: java\.lang\.IllegalStateException: Invalid access to previously freed instance \(Node3D, instance id [0-9]+\)$"
check "^ +at: FreedObjectSmoke\.callFreed \(res://FreedObjectSmoke\.kt:${freed_call_line}\)$"
# Exactly one: the call. The property reads and the method return of the freed wrapper reported
# nothing.
# justified: grep -c exits 1 when the count is 0 (and still prints 0); the count itself is checked on the next line.
freed_errors="$(grep -c '^SCRIPT ERROR: .*previously freed instance' "$LOG_FILE" || true)"
if [[ "$freed_errors" != "1" ]]; then
  smoke_fail "freed-object script errors (want exactly 1)" "$freed_errors"
fi
# task 131 S5 -- the RefCounted elements of a returned typed Array (Engine.captureScriptBacktraces)
# are retained before the Array is destroyed: alive, usable, then closed. Before, each was already
# freed (valid=false), and closing it was a use-after-free.
check "FreedObjectSmoke backtraces valid=\[true(, true)*\] languages=\[[A-Za-z]"
# Task 131 review: custom-script-typed exports (a KanamaScript type, a plain script type, a List
# and a Map of them) whose nodes were freed read back as nil, without reading the freed owners.
check "FreedObjectSmoke script_values live_read=true script_target=null script_targets=\[null\] plain_target=null plain_target_map=\{a=null\}"
# task 132 round 2 -- the cache re-reference keeps the file's values (caeee78b: reload_read=10).
check "CacheRecreateProbe saved=true first_read=4242 alive_before_reload=true same_object=true reload_read=4242 kotlin_cash=4242"
check "RefillOnFreeProbe reload_same_object=true"
check "RefillOnFreeProbe constructions_before_reload=2 after_reload=2 alive=true"
check "RefillOnFreeProbe dead=true frames=[0-9]+ constructions_at_end=2 after_reload=2"
check_absent "property values reset|recreated with its default property values"
check "PropertyRetainSmoke held=2 old_released=true owner_dead=true items_dead=true frames=[0-9]+"
check "PropertyLifetimeSmoke alias_valid=true node_paths_kept=true"
check "PropertyLifetimeSmoke items reset_drop=1 held=20"
check "PropertyLifetimeSmoke swap_items_dead=true swap_owner_alive=true swap_script_collected=true thread_owner_dead=true thread_items_dead=true old_dead=true freed_dead=true frames=[0-9]+"
# task 132 blocker 1 -- script objects keep their owners (see the run above).
check "ScriptOwnerSmoke saved=true loaded=true created=true"
check "ScriptOwnerSmoke alive_after_gc=true engine_read=4242 resaved=true created_alive_after_gc=true created_read=77 plain_alive_after_gc=true"
check "ScriptOwnerSmoke dies_after_drop=true created_dies_after_drop=true plain_dies_after_detach=true"
if ! grep -Eq "ScriptOwnerSmoke alive_after_gc=false engine_read=null resaved=false created_alive_after_gc=false created_read=null" "$SCRIPT_OWNER_RED_LOG"; then
  echo "[runtime_smoke] FAIL -- the KANAMA_SCRIPT_OWNER_LINKS=0 red run did not lose the owners:" >&2
  grep -E "ScriptOwnerSmoke" "$SCRIPT_OWNER_RED_LOG" >&2 || tail -n 40 "$SCRIPT_OWNER_RED_LOG" >&2
  exit 1
fi
# task 132 D7 -- KANAMA_FREED_OBJECT_CHECKS=binding: the instance-binding liveness flag gives the same
# GDScript semantics as the instance-id lookup (silent holds, an error on a call).
for pattern in \
  "freed-object checks: on \(KANAMA_FREED_OBJECT_CHECKS=binding: instance binding\)" \
  "FreedObjectSmoke equal=true same_hash=true set_size=2 not_equal=true valid_after_free=false equal_after_free=true to_string=<Freed Object> property_reads=null,null method_return=null survived=true result_null=true" \
  "FreedObjectSmoke caught=Invalid access to previously freed instance \(Node3D, instance id [0-9]+\)$" \
  "FreedObjectSmoke script_values live_read=true script_target=null script_targets=\[null\] plain_target=null plain_target_map=\{a=null\}"; do
  if ! grep -Eq -- "$pattern" "$FREED_BINDING_LOG"; then
    echo "[runtime_smoke] FAIL -- binding-mode freed-object run is missing: $pattern" >&2
    tail -n 60 "$FREED_BINDING_LOG" >&2
    exit 1
  fi
done
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
# ClassDB.class_call_static ownership: the varargs Object.call path shares instantiate's
# return-ownership hazard — a fresh RefCounted static factory return arrives with its
# ONLY reference in the return Variant, so the owned decode must retain it into an owning
# RefCounted wrapper (created_rc=1 exactly; setMeta refs independently to 2; close() drops
# back to 1) while a non-object static return (Thread.is_main_thread) passes through untouched.
check "ClassDB class_call_static ownership class=RegEx owned_wrapper=true created_rc=1 held_rc=2 closed_rc=1 usable=true scalar_main_thread=true"
check_absent "Leaked instance: RegEx"
check_absent "Leaked instance: AwaitTweener"
check_absent "Leaked instance: MethodTweener"
check_absent "Leaked instance: PropertyTweener"
check_absent "Leaked instance: CallbackTweener"
check_absent "Leaked instance: IntervalTweener"
check_absent "Leaked instance: SubtweenTweener"
check_absent "Leaked instance: Tween:"
check "Node controls ready=(true|false) in_group=true group_removed=true group_set=true group_flags=true processing_after_set=true physics_processing_after_set=true processing_input=true shortcut_input=true unhandled_input=true unhandled_key_input=true multiplayer_authority=[0-9-]+ is_multiplayer_authority=(true|false)"
check "Node scalar_controls process_priority=3 physics_process_priority=4 displayed_folded=true unique_name=true editor_description_len=17 tree_node_count_positive=true"
check "Vector helpers v3_len=5\\.0 v3_norm=0\\.0,0\\.6000000238418579,0\\.800000011920929 v3_dot=32\\.0 v3_cross=0\\.0,0\\.0,1\\.0 v3_lerp=1\\.0,2\\.0,3\\.0 v3_limited=2\\.0,0\\.0,0\\.0 v3_distance=2\\.0 v2_len=5\\.0 v2_angle=0\\.0 v2_lerp=1\\.0,1\\.5 v3_withx=9\\.0,2\\.0,3\\.0 v3_withy=1\\.0,9\\.0,3\\.0 v3_withz=1\\.0,2\\.0,9\\.0 v2_withx=9\\.0,2\\.0 v2_withy=1\\.0,9\\.0"
# task 128 A follow-up — a typed enum through Object.set / ConfigFile.setValue is encoded as INT.
check "typed enum dynamic set=1 config_roundtrip=3$"
# task 128 C — InputEventMouseButton.create(): typed button read back, attached to an action, the
# event outlives the wrapper's close() (a fresh RIGHT event still matches), the action erases.
check "input mouse_button read_back=true has_event=true survives_close=true erased=true$"
check "Node process_modes mode=3 thread_group=1 thread_messages=3 thread_order=2 internal=true physics_internal=true physics_interp_mode=2 physics_interp=false physics_interp_enabled=(true|false) auto_translate=2 can_auto_translate=false scene_load_flag=true scene_load_flag_reset=false typed_mode=true typed_flags_physics=true"
check "Object introspection can_revert_name=(true|false) missing_meta=false missing_user_signal=false has_queue_free=true queue_free_args=0 has_script_changed=true script_changed_connections=(true|false) signal_connect=0 signal_callback=Node signal_lambda=Node script_signal_connect=0 script_signal_callback=helper coroutine_started=true blocking=true blocking_after_reset=false translate_disabled=false translate_enabled=true"
check "Object call autoload_present=true describe=audio:3:true:1\\.5:1 add=9 negate=true object=Node returned=Node resource=StandardMaterial3D v2=3\\.0,5\\.0 v3=3\\.0,5\\.0,7\\.0 color=0\\.20000000298023224,0\\.4000000059604645,0\\.6000000238418579,0\\.4000000059604645 quat=-0\\.10000000149011612,-0\\.20000000298023224,-0\\.30000001192092896,-0\\.4000000059604645 v4=-1\\.0,-2\\.0,-3\\.0,-4\\.0 rect2=1\\.0,2\\.0,13\\.0,24\\.0 aabb=1\\.0,2\\.0,3\\.0,14\\.0,25\\.0,36\\.0 plane=-1\\.0,-0\\.0,-0\\.0,-5\\.0 basis=2\\.0,2\\.0,2\\.0 t3d=11\\.0,22\\.0,33\\.0 t2d=15\\.0,26\\.0 proj=1\\.0,-13\\.0,-14\\.0,-15\\.0,-16\\.0 v2i=-2,-3 v3i=-2,-3,-4 v4i=-1,-2,-3,-4 rect2i=1,2,13,24 np_described=np:3:foo/bar/baz"
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
check "kt script methods size = 10"
check "kt script properties size = 25"
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
# task 133 -- script authoring like GDScript (script_access_smoke.tscn)
check "ScriptAccessSmoke sync before_ready=true node=true wrong_type=true missing=true script=true no_script=true is_script=true as_script=true cast=true require_as=true preload=true preload_wrong=true instantiate=true tree=true orphan_tree=true"
# task 132 -- a cast to a RefCounted class owns a reference, like the from* downcasts (883c936c:
# the cast was a borrowed view and the object died when the original was closed).
check "ScriptAccessSmoke cast_owns=true"
check "ScriptAccessSmoke async wait=true next_frame=true freed_cancelled=true"
check "ScriptAccessSmoke reready cached_until_ready=true re_resolved=true ready_count=2"
# the tree accessors check tree membership first: no engine error of their own
check_absent 'Parameter "data\.tree" is null'
check_absent "Resource still in use: res://script_access_child\\.tscn"
check_absent "Orphan StringName"
check_absent "unclaimed string names"
check_absent "Cannot ptrcall nil constructor"
check_absent "Unable to get the RPC configuration"
check_absent "RPC config metadata missing or malformed"
check_absent "local RPC smoke failed"
check_absent "Leaked instance: FileAccess"
check_absent "Leaked instance: DirAccess"
check_absent "Resource still in use: .*Resource_smoke"

# task 83 -- no native call adapter may be generated inside a Godot->JVM upcall.
# The trace (KANAMA_TRACE_NATIVE_ADAPTERS=1, set above) timestamps every adapter and
# the moment the first lifecycle upcall is installed. Assert the boundary line is
# present FIRST, so a dropped env var fails loudly instead of making the absence
# check pass vacuously.
for adapter_log in "$LOG_FILE" "$IMPORT_LOG_FILE"; do
  if ! grep -Eq -- "\[kanama:adapter\] boundary first-lifecycle-upcall-install" "$adapter_log"; then
    echo "[runtime_smoke] FAIL -- adapter trace missing from $adapter_log" >&2
    exit 1
  fi
  if grep -Eq -- "\[kanama:adapter\] downcall .* phase=post-boundary" "$adapter_log"; then
    echo "[runtime_smoke] FAIL -- native adapter generated after the first upcall:" >&2
    grep -E -- "\[kanama:adapter\] downcall .* phase=post-boundary" "$adapter_log" >&2
    exit 1
  fi
done

echo "[runtime_smoke] PASS"

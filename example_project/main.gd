extends Node

var _kanama_smoke_reload_requested := false

func _on_pinged(value: int) -> void:
	print("[kanama:gd] received pinged(", value, ")")

func _ready() -> void:
	if OS.get_environment("KANAMA_IN_PROCESS_HOT_RELOAD_SMOKE") == "1":
		_kanama_hot_reload_smoke_ready()

	$HelloKanama.pinged.connect(_on_pinged)

	var a = $HelloKanama.ping()
	var b = $HelloKanama.ping()
	var c = $HelloKanama/HelloKanama.ping()
	print("[kanama:gd] ping results: ", a, " ", b, " ", c)

	print("[kanama:gd] initial counter = ", $HelloKanama.counter)
	$HelloKanama.counter = 42
	print("[kanama:gd] after set, counter = ", $HelloKanama.counter)
	$HelloKanama.counter += 1
	print("[kanama:gd] after inc, counter = ", $HelloKanama.counter)

	# Bool
	print("[kanama:gd] isActive (counter>0) = ", $HelloKanama.is_active())

	# String property
	print("[kanama:gd] initial label = ", $HelloKanama.label)
	$HelloKanama.label = "kanama"
	print("[kanama:gd] after set, label = ", $HelloKanama.label)

	# String arg + return
	var greeting = $HelloKanama.greet("World")
	print("[kanama:gd] greet result = ", greeting)

	# Double / float
	print("[kanama:gd] initial scale = ", $HelloKanama.scale)
	$HelloKanama.scale = 3.14
	print("[kanama:gd] after set, scale = ", $HelloKanama.scale)

	var kt_script = $ScriptNode.get_script()
	if kt_script != null:
		var methods = kt_script.get_script_method_list()
		var properties = kt_script.get_script_property_list()
		var signals = kt_script.get_script_signal_list()
		print("[kanama:gd] kt script methods size = ", methods.size())
		print("[kanama:gd] kt script properties size = ", properties.size())
		print("[kanama:gd] kt script signals size = ", signals.size())
		if methods.size() > 0:
			print("[kanama:gd] kt script method[0] = ", methods[0])
		if properties.size() > 0:
			print("[kanama:gd] kt script property[0] = ", properties[0])
		var has_export_group := false
		var has_export_subgroup := false
		for property in properties:
			if property.get("name", "") == "Smoke Properties" and int(property.get("usage", 0)) & PROPERTY_USAGE_GROUP:
				has_export_group = true
			if property.get("name", "") == "Runtime" and int(property.get("usage", 0)) & PROPERTY_USAGE_SUBGROUP:
				has_export_subgroup = true
		print("[kanama:gd] kt script export groups group=", has_export_group, " subgroup=", has_export_subgroup)
		if not has_export_group or not has_export_subgroup:
			push_error("Kanama script export group/subgroup metadata missing")
		_kanama_enum_export_smoke(properties)
		_kanama_enum_list_export_smoke(properties)
		_kanama_accessor_containment_smoke()
		_kanama_mutable_list_export_smoke(properties)
		_kanama_dictionary_export_smoke(properties)
		_kanama_dictionary_malformed_smoke()
		_kanama_dictionary_nullable_value_smoke()
		var replace_smoke_scene = $ScriptNode.replace_smoke_scene()
		print("[kanama:gd] kt script replace_smoke_scene = ", replace_smoke_scene)
		if not replace_smoke_scene:
			push_error("Kanama script smoke_scene replacement failed")
		var rpc_config = kt_script.get_rpc_config()
		var rpc_replace_config = rpc_config.get("replace_smoke_scene", {}) if rpc_config is Dictionary else {}
		var rpc_config_ok = (
			rpc_config is Dictionary
			and rpc_replace_config is Dictionary
			and int(rpc_replace_config.get("rpc_mode", -1)) == MultiplayerAPI.RPC_MODE_AUTHORITY
			and bool(rpc_replace_config.get("call_local", false))
			and int(rpc_replace_config.get("transfer_mode", -1)) == MultiplayerPeer.TRANSFER_MODE_RELIABLE
			and int(rpc_replace_config.get("channel", -1)) == 0
		)
		print("[kanama:gd] kt script rpc config ok = ", rpc_config_ok)
		if not rpc_config_ok:
			push_error("Kanama script RPC config metadata missing or malformed")
		multiplayer.multiplayer_peer = OfflineMultiplayerPeer.new()
		var rpc_error = $ScriptNode.rpc("replace_smoke_scene")
		print("[kanama:gd] kt script rpc replace_smoke_scene error = ", rpc_error)
		if rpc_error != OK:
			push_error("Kanama script local RPC smoke failed")

	_kanama_virtual_return_families_smoke()

	#get_tree().quit()

# task 32 — custom enum exports. A Kotlin `enum class` @ScriptProperty must register
# as an INT property with PROPERTY_HINT_ENUM and the entry names as hint_string
# (C# export parity), round-trip ordinals through set/get, and clamp out-of-range
# stored ints to a valid entry instead of crashing.
func _kanama_enum_export_smoke(properties: Array) -> void:
	var enum_type := false
	var enum_hint := false
	var enum_hint_string := false
	for property in properties:
		if property.get("name", "") == "smoke_difficulty":
			enum_type = int(property.get("type", -1)) == TYPE_INT
			enum_hint = int(property.get("hint", -1)) == PROPERTY_HINT_ENUM
			enum_hint_string = str(property.get("hint_string", "")) == "EASY,NORMAL,HARD"
	var tscn_value = $ScriptNode.smoke_difficulty
	$ScriptNode.smoke_difficulty = 0
	var roundtrip = $ScriptNode.smoke_difficulty
	$ScriptNode.smoke_difficulty = 99
	var clamped = $ScriptNode.smoke_difficulty
	$ScriptNode.smoke_difficulty = tscn_value
	print("[kanama:gd] kt script enum export type=", enum_type,
		" hint=", enum_hint,
		" hint_string=", enum_hint_string,
		" tscn=", tscn_value == 2,
		" roundtrip=", roundtrip == 0,
		" clamp=", clamped == 2)
	if not (enum_type and enum_hint and enum_hint_string and tscn_value == 2 and roundtrip == 0 and clamped == 2):
		push_error("Kanama enum export metadata or round-trip failed")

# task 38 — enum-list exports (List<enum>). Must register as an ARRAY property with
# PROPERTY_HINT_TYPE_STRING and a per-element enum hint ("2/2:<entries>"), round-trip
# ordinal arrays through set/get, and clamp out-of-range stored ints per element.
func _kanama_enum_list_export_smoke(properties: Array) -> void:
	var list_type := false
	var list_hint := false
	var list_hint_string := false
	for property in properties:
		if property.get("name", "") == "smoke_joints":
			list_type = int(property.get("type", -1)) == TYPE_ARRAY
			list_hint = int(property.get("hint", -1)) == PROPERTY_HINT_TYPE_STRING
			list_hint_string = str(property.get("hint_string", "")) == "%d/%d:EASY,NORMAL,HARD" % [TYPE_INT, PROPERTY_HINT_ENUM]
	var tscn_value = $ScriptNode.smoke_joints
	$ScriptNode.smoke_joints = [0, 1]
	var roundtrip = $ScriptNode.smoke_joints
	$ScriptNode.smoke_joints = [99, -3]
	var clamped = $ScriptNode.smoke_joints
	# The inspector's Add Element/resize null-fills the (untyped) array it got from
	# the getter; null elements must default to the first entry, not silently drop.
	$ScriptNode.smoke_joints = [1, null]
	var nullfill = $ScriptNode.smoke_joints
	$ScriptNode.smoke_joints = tscn_value
	print("[kanama:gd] kt script enum list export type=", list_type,
		" hint=", list_hint,
		" hint_string=", list_hint_string,
		" tscn=", tscn_value == [2, 0],
		" roundtrip=", roundtrip == [0, 1],
		" clamp=", clamped == [2, 0],
		" nullfill=", nullfill == [1, 0])
	if not (list_type and list_hint and list_hint_string and tscn_value == [2, 0] and roundtrip == [0, 1] and clamped == [2, 0] and nullfill == [1, 0]):
		push_error("Kanama enum-list export metadata or round-trip failed")

# task 50 — a throwing user accessor must not abort the process. Without containment in
# ScriptBridge.siSet/siGet the exception escapes an FFM upcall stub and kills Godot
# (exit 134). With it: a failed set is rejected and the previous value survives, a failed
# get yields null, and the property recovers afterwards. Reaching the print at all is
# itself part of the assertion — an uncontained throw never gets here.
func _kanama_accessor_containment_smoke() -> void:
	# main.tscn stores 666 for this property, which the setter rejects. Scene-load property
	# application goes through siSet, so containment must leave the Kotlin default (0) intact
	# rather than aborting the process — the stale-.tscn case, where a scene holds a value the
	# current script no longer accepts.
	var tscn_rejected = $ScriptNode.smoke_throwing_setter == 0
	$ScriptNode.smoke_throwing_setter = 42
	var baseline = $ScriptNode.smoke_throwing_setter == 42
	# Setter throws on 666: the write must be rejected, the previous value must survive,
	# and the process must stay alive.
	$ScriptNode.smoke_throwing_setter = 666
	var set_rejected = $ScriptNode.smoke_throwing_setter == 42
	# ...and the property still takes valid writes afterwards.
	$ScriptNode.smoke_throwing_setter = 7
	var set_recovered = $ScriptNode.smoke_throwing_setter == 7
	# Getter throws while armed: the read must yield null rather than aborting the caller
	# (returning "property missing" here would raise a GDScript error and halt this func).
	$ScriptNode.smoke_arm_getter_throw(true)
	var get_contained = $ScriptNode.smoke_throwing_getter == null
	$ScriptNode.smoke_arm_getter_throw(false)
	var get_recovered = $ScriptNode.smoke_throwing_getter == 5
	print("[kanama:gd] kt script accessor containment tscn_rejected=", tscn_rejected,
		" baseline=", baseline,
		" set_rejected=", set_rejected,
		" set_recovered=", set_recovered,
		" get_contained=", get_contained,
		" get_recovered=", get_recovered)
	if not (tscn_rejected and baseline and set_rejected and set_recovered and get_contained and get_recovered):
		push_error("Kanama script-property accessor containment failed")

# MutableList export (preserves mutability in the setter). Must register as an ARRAY
# property with PROPERTY_HINT_TYPE_STRING and the element type hint, and round-trip
# through set/get without losing the mutable type.
func _kanama_mutable_list_export_smoke(properties: Array) -> void:
	var mutable_type := false
	var mutable_hint := false
	var mutable_hint_string := false
	for property in properties:
		if property.get("name", "") == "smoke_mutable_textures":
			mutable_type = int(property.get("type", -1)) == TYPE_ARRAY
			mutable_hint = int(property.get("hint", -1)) == PROPERTY_HINT_TYPE_STRING
			mutable_hint_string = str(property.get("hint_string", "")) == "%d/%d:Texture2D" % [TYPE_OBJECT, PROPERTY_HINT_RESOURCE_TYPE]
	var texture = PlaceholderTexture2D.new()
	$ScriptNode.smoke_mutable_textures = [texture]
	var roundtrip = $ScriptNode.smoke_mutable_textures == [texture]
	$ScriptNode.smoke_mutable_textures = []
	print("[kanama:gd] kt script mutable list export type=", mutable_type,
		" hint=", mutable_hint,
		" hint_string=", mutable_hint_string,
		" roundtrip=", roundtrip)
	if not (mutable_type and mutable_hint and mutable_hint_string and roundtrip):
		push_error("Kanama mutable-list export metadata or round-trip failed")

# issue #40 — typed Map exports (Dictionary). Each must register as a TYPE_DICTIONARY
# property with PROPERTY_HINT_DICTIONARY_TYPE and a "<key>;<value>" hint_string, and
# round-trip through set/get: a String->int scalar map, the reporter's int->custom-resource
# map (non-String key + resource value), a Vector2i->int map (value-type key), a
# Vector2i->custom-resource map (value-type key + resource value), and a MutableMap
# (preserving mutability in the setter).
func _kanama_dictionary_export_smoke(properties: Array) -> void:
	# Scene-deserialization of a typed Map: main.tscn stores smoke_scalar_map = {"seed": 42}, applied
	# through siSet on scene load. Captured before any mutation below. This is the stale/scene-stored
	# Dictionary path the malformed-input review flagged as untested.
	var tscn_scalar_map: bool = $ScriptNode.smoke_scalar_map == {"seed": 42}
	var scalar_type := false
	var scalar_hint := false
	var scalar_hint_string := false
	var region_type := false
	var region_hint_string := false
	var vector_key_hint_string := false
	var vector_resource_type := false
	var vector_resource_hint_string := false
	var mutable_type := false
	var mutable_hint := false
	var mutable_hint_string := false
	for property in properties:
		if property.get("name", "") == "smoke_scalar_map":
			scalar_type = int(property.get("type", -1)) == TYPE_DICTIONARY
			scalar_hint = int(property.get("hint", -1)) == PROPERTY_HINT_DICTIONARY_TYPE
			scalar_hint_string = str(property.get("hint_string", "")) == "%d:;%d:" % [TYPE_STRING, TYPE_INT]
		if property.get("name", "") == "smoke_region_map":
			region_type = int(property.get("type", -1)) == TYPE_DICTIONARY
			region_hint_string = str(property.get("hint_string", "")) == "%d:;%d/%d:SmokeResource" % [TYPE_INT, TYPE_OBJECT, PROPERTY_HINT_RESOURCE_TYPE]
		if property.get("name", "") == "smoke_vector_key_map":
			vector_key_hint_string = str(property.get("hint_string", "")) == "%d:;%d:" % [TYPE_VECTOR2I, TYPE_INT]
		if property.get("name", "") == "smoke_vector_resource_map":
			vector_resource_type = int(property.get("type", -1)) == TYPE_DICTIONARY
			vector_resource_hint_string = str(property.get("hint_string", "")) == "%d:;%d/%d:SmokeResource" % [TYPE_VECTOR2I, TYPE_OBJECT, PROPERTY_HINT_RESOURCE_TYPE]
		if property.get("name", "") == "smoke_mutable_map":
			mutable_type = int(property.get("type", -1)) == TYPE_DICTIONARY
			mutable_hint = int(property.get("hint", -1)) == PROPERTY_HINT_DICTIONARY_TYPE
			mutable_hint_string = str(property.get("hint_string", "")) == "%d:;%d:" % [TYPE_STRING, TYPE_INT]
	$ScriptNode.smoke_scalar_map = {"a": 1, "b": 2}
	var scalar_roundtrip = $ScriptNode.smoke_scalar_map == {"a": 1, "b": 2}
	$ScriptNode.smoke_enum_map = {"boss": 2, "mob": 0}
	var enum_roundtrip = $ScriptNode.smoke_enum_map == {"boss": 2, "mob": 0}
	var region_resource = ClassDB.instantiate("Resource")
	region_resource.set_script(load("res://SmokeResource.kt"))
	region_resource.payload = "region"
	$ScriptNode.smoke_region_map = {7: region_resource}
	var region_map = $ScriptNode.smoke_region_map
	var region_roundtrip = region_map.has(7) and region_map[7] != null and region_map[7].payload == "region"
	$ScriptNode.smoke_vector_key_map = {Vector2i(1, 2): 5, Vector2i(3, 4): 6}
	var vector_key_map = $ScriptNode.smoke_vector_key_map
	var vector_key_roundtrip = vector_key_map == {Vector2i(1, 2): 5, Vector2i(3, 4): 6}
	var vector_resource = ClassDB.instantiate("Resource")
	vector_resource.set_script(load("res://SmokeResource.kt"))
	vector_resource.payload = "vector_resource"
	$ScriptNode.smoke_vector_resource_map = {Vector2i(5, 6): vector_resource}
	var vector_resource_map = $ScriptNode.smoke_vector_resource_map
	var vector_resource_roundtrip = vector_resource_map.has(Vector2i(5, 6)) and vector_resource_map[Vector2i(5, 6)] != null and vector_resource_map[Vector2i(5, 6)].payload == "vector_resource"
	$ScriptNode.smoke_mutable_map = {"x": 10, "y": 20}
	var mutable_roundtrip = $ScriptNode.smoke_mutable_map == {"x": 10, "y": 20}
	$ScriptNode.smoke_scalar_map = {}
	$ScriptNode.smoke_enum_map = {}
	$ScriptNode.smoke_region_map = {}
	$ScriptNode.smoke_vector_key_map = {}
	$ScriptNode.smoke_vector_resource_map = {}
	$ScriptNode.smoke_mutable_map = {}
	print("[kanama:gd] kt script dictionary export scalar_type=", scalar_type,
		" tscn_scalar_map=", tscn_scalar_map,
		" scalar_hint=", scalar_hint,
		" scalar_hint_string=", scalar_hint_string,
		" scalar_roundtrip=", scalar_roundtrip,
		" enum_roundtrip=", enum_roundtrip,
		" region_type=", region_type,
		" region_hint_string=", region_hint_string,
		" region_roundtrip=", region_roundtrip,
		" vector_key_hint_string=", vector_key_hint_string,
		" vector_key_roundtrip=", vector_key_roundtrip,
		" vector_resource_type=", vector_resource_type,
		" vector_resource_hint_string=", vector_resource_hint_string,
		" vector_resource_roundtrip=", vector_resource_roundtrip,
		" mutable_type=", mutable_type,
		" mutable_hint=", mutable_hint,
		" mutable_hint_string=", mutable_hint_string,
		" mutable_roundtrip=", mutable_roundtrip)
	if not (scalar_type and tscn_scalar_map and scalar_hint and scalar_hint_string and scalar_roundtrip and enum_roundtrip and region_type and region_hint_string and region_roundtrip and vector_key_hint_string and vector_key_roundtrip and vector_resource_type and vector_resource_hint_string and vector_resource_roundtrip and mutable_type and mutable_hint and mutable_hint_string and mutable_roundtrip):
		push_error("Kanama dictionary export metadata or round-trip failed")

# issue #40 review / task 50 — malformed Dictionary input must NOT abort the process. Before the
# fail-soft decode, a wrong-typed key ({1: 2} into a Map<String, Long>) or wrong-typed value
# ({"bad": "not-a-number"}) threw ClassCastException out of an FFM upcall and killed Godot (exit
# 134); a stale .tscn saved before the declared types changed hit the same path on scene load.
# The decode now drops undecodable entries. Reaching this print at all is the core assertion —
# an uncontained throw never gets here — and the property must hold a defined value afterwards.
# Reverting the fail-soft decode (scalarReadCast* -> throwing `as`) reproduces the abort.
func _kanama_dictionary_malformed_smoke() -> void:
	# Wrong-typed keys: Long keys into a String-keyed map. Both keys fail to decode and drop.
	$ScriptNode.smoke_scalar_map = {1: 2, 3: 4}
	var wrong_key_empty = $ScriptNode.smoke_scalar_map == {}
	# Wrong-typed value: a non-numeric value into a Long-valued map drops; the valid entry survives.
	$ScriptNode.smoke_scalar_map = {"bad": "not-a-number", "ok": 5}
	var wrong_value_dropped = $ScriptNode.smoke_scalar_map == {"ok": 5}
	# Wrong-typed key on the value-type-key map (String key into a Vector2i-keyed map) drops too.
	$ScriptNode.smoke_vector_key_map = {"nope": 1, Vector2i(9, 9): 8}
	var wrong_vector_key_dropped = $ScriptNode.smoke_vector_key_map == {Vector2i(9, 9): 8}
	$ScriptNode.smoke_scalar_map = {}
	$ScriptNode.smoke_vector_key_map = {}
	print("[kanama:gd] kt script dictionary malformed survived=true",
		" wrong_key_empty=", wrong_key_empty,
		" wrong_value_dropped=", wrong_value_dropped,
		" wrong_vector_key_dropped=", wrong_vector_key_dropped)
	if not (wrong_key_empty and wrong_value_dropped and wrong_vector_key_dropped):
		push_error("Kanama dictionary malformed-input handling failed")

# issue #40 review — nullable scalar Map value (Map<String, Long?>) mirrors C#'s nil-preserving
# Dictionary: a null value keeps its key (unlike a non-null value map, which cannot hold null and
# drops). Round-trips through the write path too (null -> nil Variant). A wrong-typed value also
# becomes null rather than dropping, since the key can now hold it.
func _kanama_dictionary_nullable_value_smoke() -> void:
	$ScriptNode.smoke_nullable_scalar_map = {"a": null, "b": 2}
	var got = $ScriptNode.smoke_nullable_scalar_map
	# Key "a" survives with a null value (C# parity), "b" keeps 2.
	var null_preserved = got.has("a") and got["a"] == null and got.get("b") == 2
	# A wrong-typed value nulls (key preserved) rather than dropping.
	$ScriptNode.smoke_nullable_scalar_map = {"c": "not-a-number"}
	var wrong_value = $ScriptNode.smoke_nullable_scalar_map
	var wrong_nulled = wrong_value.has("c") and wrong_value["c"] == null
	$ScriptNode.smoke_nullable_scalar_map = {}
	print("[kanama:gd] kt script dictionary nullable_value null_preserved=", null_preserved,
		" wrong_nulled=", wrong_nulled)
	if not (null_preserved and wrong_nulled):
		push_error("Kanama dictionary nullable-value handling failed")

# task 29 — virtual-return families. Calling an @OverrideVirtual method by name on a
# script-attached host routes through the script instance dispatch (the same path the
# engine's virtual call uses) and hands the marshalled Variant back to GDScript, so
# typeof() + content checks validate each family's return marshalling end to end.
func _kanama_virtual_return_families_smoke() -> void:
	var mesh_host = ClassDB.instantiate("Mesh")
	mesh_host.set_script(load("res://MeshVirtualReturnProbe.kt"))
	var v_aabb = mesh_host._get_aabb()
	var v_arr = mesh_host._surface_get_arrays(3)
	var v_lods = mesh_host._surface_get_lods(2)
	print("[kanama:gd] vret mesh aabb_type=", typeof(v_aabb) == TYPE_AABB,
		" aabb_pos=", v_aabb.position == Vector3(1, 2, 3),
		" aabb_size=", v_aabb.size == Vector3(4, 5, 6),
		" array_type=", typeof(v_arr) == TYPE_ARRAY,
		" array_vals=", v_arr.size() == 3 and v_arr[0] == 3 and v_arr[1] == "kanama" and v_arr[2] == true,
		" dict_type=", typeof(v_lods) == TYPE_DICTIONARY,
		" dict_vals=", v_lods.get("lod") == 2 and v_lods.get("name") == "kanama")

	var ts_host = ClassDB.instantiate("TextServerExtension")
	ts_host.set_script(load("res://TextServerVirtualReturnProbe.kt"))
	var v_bytes = ts_host._get_support_data()
	var v_breaks = ts_host._string_get_word_breaks("kanama-example", "en", 80)
	print("[kanama:gd] vret textserver bytes_type=", typeof(v_bytes) == TYPE_PACKED_BYTE_ARRAY,
		" bytes_vals=", v_bytes.size() == 3 and v_bytes[0] == 127 and v_bytes[1] == 0 and v_bytes[2] == 128,
		" int32_type=", typeof(v_breaks) == TYPE_PACKED_INT32_ARRAY,
		" int32_vals=", v_breaks.size() == 3 and v_breaks[0] == 0 and v_breaks[1] == 14 and v_breaks[2] == 2147483647)

	var xr_host = ClassDB.instantiate("XRInterfaceExtension")
	xr_host.set_script(load("res://XrVirtualReturnProbe.kt"))
	var v_xform = xr_host._get_camera_transform()
	var v_area = xr_host._get_play_area()
	var v_proj_view = xr_host._get_projection_for_view(0, 1.5, 0.25, 4096.0)
	print("[kanama:gd] vret xr transform3d_type=", typeof(v_xform) == TYPE_TRANSFORM3D,
		" transform3d_origin=", v_xform.origin == Vector3(7, 8, 9),
		" vector3_array_type=", typeof(v_area) == TYPE_PACKED_VECTOR3_ARRAY,
		" vector3_array_vals=", v_area.size() == 2 and v_area[0] == Vector3(1, 0, 0) and v_area[1] == Vector3(0, 0, 1),
		" float64_array_type=", typeof(v_proj_view) == TYPE_PACKED_FLOAT64_ARRAY,
		" float64_array_vals=", v_proj_view.size() == 4 and v_proj_view[0] == 1.5 and v_proj_view[3] == 1.0e308)

	var graph_host = ClassDB.instantiate("GraphEdit")
	graph_host.set_script(load("res://GraphEditVirtualReturnProbe.kt"))
	var v_line = graph_host._get_connection_line(Vector2(1, 2), Vector2(3, 4))
	print("[kanama:gd] vret graphedit vector2_array_type=", typeof(v_line) == TYPE_PACKED_VECTOR2_ARRAY,
		" vector2_array_vals=", v_line.size() == 2 and v_line[0] == Vector2(1, 2) and v_line[1] == Vector2(3, 4))
	graph_host.free()

	var body_host = ClassDB.instantiate("PhysicsDirectBodyState2DExtension")
	body_host.set_script(load("res://Body2dVirtualReturnProbe.kt"))
	var v_t2d = body_host._get_transform()
	print("[kanama:gd] vret body2d transform2d_type=", typeof(v_t2d) == TYPE_TRANSFORM2D,
		" transform2d_vals=", v_t2d.x == Vector2(1, 2) and v_t2d.y == Vector2(3, 4) and v_t2d.origin == Vector2(5, 6))
	body_host.free()

	var rsd_host = ClassDB.instantiate("RenderSceneDataExtension")
	rsd_host.set_script(load("res://RenderSceneDataVirtualReturnProbe.kt"))
	var v_proj = rsd_host._get_cam_projection()
	print("[kanama:gd] vret rendersscenedata projection_type=", typeof(v_proj) == TYPE_PROJECTION,
		" projection_vals=", v_proj.x.x == 1.0 and v_proj.y.y == 2.0 and v_proj.z.z == 3.0 and v_proj.w.w == 4.0)
	rsd_host.free()

	var rid_host = ClassDB.instantiate("Control")
	rid_host.set_script(load("res://VirtualOverrideProbe.kt"))
	var v_rid = rid_host._get_focused_accessibility_element()
	print("[kanama:gd] vret control rid_type=", typeof(v_rid) == TYPE_RID)
	rid_host.free()

func _process(_delta: float) -> void:
	if OS.get_environment("KANAMA_IN_PROCESS_HOT_RELOAD_SMOKE") != "1":
		return
	if _kanama_smoke_reload_requested:
		return

	var signal_file := OS.get_environment("KANAMA_IN_PROCESS_HOT_RELOAD_SIGNAL")
	var stage_file := OS.get_environment("KANAMA_IN_PROCESS_HOT_RELOAD_STAGE")
	if signal_file == "" or stage_file == "":
		return
	if not FileAccess.file_exists(signal_file):
		return

	if FileAccess.file_exists(stage_file):
		_kanama_smoke_reload_requested = true
		call_deferred("_kanama_hot_reload_smoke_quit")
		return

	_kanama_smoke_reload_requested = true
	var stage := FileAccess.open(stage_file, FileAccess.WRITE)
	if stage != null:
		stage.store_string("reloaded\n")
	call_deferred("_kanama_hot_reload_smoke_reload_scene")

func _kanama_hot_reload_smoke_ready() -> void:
	print("[kanama:gd] in-process hot reload smoke ready")

func _kanama_hot_reload_smoke_reload_scene() -> void:
	await get_tree().create_timer(1.5).timeout
	print("[kanama:gd] in-process hot reload smoke reload_scene")
	get_tree().reload_current_scene()

func _kanama_hot_reload_smoke_quit() -> void:
	await get_tree().create_timer(1.0).timeout
	print("[kanama:gd] in-process hot reload smoke quit")
	get_tree().quit()

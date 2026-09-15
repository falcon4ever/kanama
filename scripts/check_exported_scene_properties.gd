extends SceneTree
## check_exported_scene_properties.gd — export integrity check (task 112).
##
## Godot's export instantiates and re-packs every scene when it converts text resources to
## binary (editor/export: convert_text_resources_to_binary) and keeps only the properties the
## node's script instance reports. A project whose desktop kanama-scripts.jar does not match its
## .kt scripts therefore loses every scene-stored @ScriptProperty value silently (task 106). This
## script runs headless in the exported project AFTER the export, reads the conversion cache
## (.godot/exported/<hash>/file_cache: "source::md5::mtime::saved"), loads every source .tscn and
## its converted .scn, and fails when a script-declared property present in the source is
## missing from the export. Native (ClassDB) properties are ignored: the re-pack may legitimately
## drop values equal to the class default.
##
## Usage (from the project's export environment, so the Kanama addon loads):
##   godot --headless --path <project> --script <kanama>/scripts/check_exported_scene_properties.gd
## Exit 0 = every converted scene keeps its script properties (or nothing was converted);
## exit 1 = at least one property missing (each named on stderr); exit 2 = a scene failed to load.

const TAG := "[check_exported_scenes]"

# ClassDB has no class_has_property(); cache each class's inherited property-name set instead.
var _native_props := {}

func _is_native_property(type: String, prop: String) -> bool:
	if type == "" or not ClassDB.class_exists(type):
		return false
	if not _native_props.has(type):
		var names := {}
		for info in ClassDB.class_get_property_list(type, false):
			names[String(info["name"])] = true
		_native_props[type] = names
	return _native_props[type].has(prop)

func _initialize() -> void:
	quit(_run())

func _run() -> int:
	var exported_root := "res://.godot/exported"
	var dir := DirAccess.open(exported_root)
	if dir == null:
		print("%s no %s directory: nothing was converted (convert_text_resources_to_binary off?)" % [TAG, exported_root])
		return 0
	var checked := 0
	var missing := 0
	var load_failures := 0
	dir.list_dir_begin()
	var entry := dir.get_next()
	while entry != "":
		if dir.current_is_dir() and not entry.begins_with("."):
			var cache_path := exported_root.path_join(entry).path_join("file_cache")
			if FileAccess.file_exists(cache_path):
				var f := FileAccess.open(cache_path, FileAccess.READ)
				while f != null and not f.eof_reached():
					var fields := f.get_line().split("::")
					if fields.size() != 4:
						continue
					var source: String = fields[0].strip_edges()
					var saved: String = fields[3].strip_edges()
					if not source.ends_with(".tscn"):
						continue
					checked += 1
					var result := _compare(source, saved)
					if result < 0:
						load_failures += 1
					else:
						missing += result
		entry = dir.get_next()
	dir.list_dir_end()
	if load_failures > 0:
		printerr("%s FAIL: %d converted scene(s) could not be loaded for comparison" % [TAG, load_failures])
		return 2
	if missing > 0:
		printerr("%s FAIL: %d script propert%s dropped by the export across %d converted scene(s)" % [TAG, missing, "y" if missing == 1 else "ies", checked])
		return 1
	print("%s PASS: %d converted scene(s), every script property kept" % [TAG, checked])
	return 0

## Godot's SceneState::pack skips a property whose value equals its default. Mirror that: the default is
## the sub-scene's own stored value when the node is an instance (recursively), else the script's
## declared default (Script.get_property_default_value; Kanama scripts implement the hook). A value that
## equals its default was not lost by the export.
func _is_default_value(state: SceneState, node_idx: int, prop: String, value: Variant) -> bool:
	var instance := state.get_node_instance(node_idx)
	if instance != null:
		var sub := instance.get_state()
		if sub.get_node_count() > 0:
			for k in sub.get_node_property_count(0):
				if String(sub.get_node_property_name(0, k)) == prop:
					return _values_equal(sub.get_node_property_value(0, k), value)
			# Not stored by the sub-scene either: fall through to its root script's default.
			return _is_default_value(sub, 0, prop, value)
	var inner := _resolve_in_ancestor_instance(state, node_idx)
	if inner.size() == 2:
		# An override node: the default is what the sub-scene's node stores, else its script default.
		return _is_default_value(inner[0], inner[1], prop, value)
	var script := _node_script(state, node_idx)
	if script == null:
		return false
	var default_value: Variant = script.get_property_default_value(prop)
	return _values_equal(default_value, value)

## The node's class, resolved through the instance chain: the state records "" for an instanced
## node, so ask the sub-scene's root, recursively; an OVERRIDE node (a child inside an ancestor's
## instanced sub-scene, with no type/instance/script of its own) is resolved inside that sub-scene
## (task 119 finding 2). "" when nothing in the chain records a type.
func _node_type(state: SceneState, node_idx: int) -> String:
	var type := String(state.get_node_type(node_idx))
	if type != "":
		return type
	var instance := state.get_node_instance(node_idx)
	if instance != null and instance.get_state().get_node_count() > 0:
		return _node_type(instance.get_state(), 0)
	var inner := _resolve_in_ancestor_instance(state, node_idx)
	if inner.size() == 2:
		return _node_type(inner[0], inner[1])
	return ""

## The script attached to a node in a SceneState: its own `script` property, else (for an instanced
## sub-scene) the sub-scene root's script, else (for an override node) the script of the node it
## overrides inside the ancestor's sub-scene, recursively. Null when the node has no script at all.
func _node_script(state: SceneState, node_idx: int) -> Script:
	for k in state.get_node_property_count(node_idx):
		if String(state.get_node_property_name(node_idx, k)) == "script":
			return state.get_node_property_value(node_idx, k) as Script
	var instance := state.get_node_instance(node_idx)
	if instance != null and instance.get_state().get_node_count() > 0:
		return _node_script(instance.get_state(), 0)
	var inner := _resolve_in_ancestor_instance(state, node_idx)
	if inner.size() == 2:
		return _node_script(inner[0], inner[1])
	return null

## For an override node: walk up its path to the nearest ancestor that is an instanced sub-scene and
## find the node at the same relative path inside that sub-scene. Returns [SceneState, idx] or [].
func _resolve_in_ancestor_instance(state: SceneState, node_idx: int) -> Array:
	var segments := _segments(String(state.get_node_path(node_idx)))
	for depth in range(segments.size() - 1, 0, -1):
		var ancestor_idx := _find_node(state, segments.slice(0, depth))
		if ancestor_idx < 0:
			continue
		var instance := state.get_node_instance(ancestor_idx)
		if instance == null:
			continue
		var sub := instance.get_state()
		var inner_idx := _find_node(sub, segments.slice(depth))
		if inner_idx >= 0:
			return [sub, inner_idx]
	return []

func _segments(path: String) -> PackedStringArray:
	var out := PackedStringArray()
	for seg in path.split("/"):
		if seg != "" and seg != ".":
			out.append(seg)
	return out

func _find_node(state: SceneState, segments: PackedStringArray) -> int:
	for i in state.get_node_count():
		if _segments(String(state.get_node_path(i))) == segments:
			return i
	return -1

func _values_equal(a: Variant, b: Variant) -> bool:
	if typeof(a) == typeof(b):
		return a == b
	if (typeof(a) == TYPE_INT or typeof(a) == TYPE_FLOAT) and (typeof(b) == TYPE_INT or typeof(b) == TYPE_FLOAT):
		return is_equal_approx(float(a), float(b))
	return false

## Returns the number of script properties missing from the export, or -1 when a scene failed to load.
func _compare(source: String, saved: String) -> int:
	var src := ResourceLoader.load(source, "PackedScene", ResourceLoader.CACHE_MODE_IGNORE) as PackedScene
	if src == null:
		printerr("%s cannot load source scene %s" % [TAG, source])
		return -1
	var exp := ResourceLoader.load(saved, "PackedScene", ResourceLoader.CACHE_MODE_IGNORE) as PackedScene
	if exp == null:
		printerr("%s cannot load converted scene %s (from %s)" % [TAG, saved, source])
		return -1
	var ss := src.get_state()
	var es := exp.get_state()
	var exported := {}
	for i in es.get_node_count():
		var names := {}
		for j in es.get_node_property_count(i):
			names[String(es.get_node_property_name(i, j))] = true
		exported[String(es.get_node_path(i))] = names
	var missing := 0
	var checked := 0
	for i in ss.get_node_count():
		var path := String(ss.get_node_path(i))
		var type := String(ss.get_node_type(i))
		var got: Dictionary = exported.get(path, {})
		# Which properties can be lost the task-106 way? Those the node's TYPE does not own: a
		# native property (transform, position, …) is the engine's business, everything else stored
		# on the node came from its script. The type is resolved through the instance chain (an
		# instanced sub-scene's root, recursively) because the state records "" for instances. Do NOT
		# gate on the script's own declared-property list: in the failure this check exists for, the
		# script bound no class at export time and declares nothing, which would hide exactly the
		# loss we are looking for. The declared list is used only when the type cannot be resolved.
		var resolved_type := _node_type(ss, i)
		var script := _node_script(ss, i)
		var declared := {}
		if script != null:
			for info in script.get_script_property_list():
				declared[String(info["name"])] = true
		for j in ss.get_node_property_count(i):
			var prop := String(ss.get_node_property_name(i, j))
			if prop == "script" or prop.begins_with("metadata/"):
				continue
			if resolved_type != "":
				if _is_native_property(resolved_type, prop):
					continue
			elif not declared.has(prop):
				continue  # type unknown and the script does not claim it: cannot judge, skip
			checked += 1
			if not got.has(prop):
				# The re-pack stores only values that differ from the default the node would have
				# anyway: the script's declared default, or, for an instanced sub-scene, the value the
				# sub-scene itself stores (task 116: a .tscn that spells out `movement_speed = 250` when
				# the Kotlin default is 250 is dropped legitimately).
				if _is_default_value(ss, i, prop, ss.get_node_property_value(i, j)):
					continue
				printerr("%s FAIL %s: node '%s' (%s) lost script property '%s' in %s" % [TAG, source, path, resolved_type if resolved_type != "" else "instance", prop, saved])
				missing += 1
	if missing == 0:
		print("%s ok %s (%d nodes, %d script properties checked) -> %s" % [TAG, source, ss.get_node_count(), checked, saved.get_file()])
	return missing

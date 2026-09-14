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
	for i in ss.get_node_count():
		var path := String(ss.get_node_path(i))
		var type := String(ss.get_node_type(i))
		var got: Dictionary = exported.get(path, {})
		for j in ss.get_node_property_count(i):
			var prop := String(ss.get_node_property_name(i, j))
			if prop == "script":
				continue
			if _is_native_property(type, prop):
				continue
			if not got.has(prop):
				printerr("%s FAIL %s: node '%s' (%s) lost script property '%s' in %s" % [TAG, source, path, type if type != "" else "instance", prop, saved])
				missing += 1
	if missing == 0:
		print("%s ok %s (%d nodes) -> %s" % [TAG, source, ss.get_node_count(), saved.get_file()])
	return missing

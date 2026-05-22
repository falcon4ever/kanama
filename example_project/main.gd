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

	#get_tree().quit()

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

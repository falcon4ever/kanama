extends Node
# Task 134 B: installs builtin_reentry_logger.gd before the Kotlin probe's _ready runs.


func _enter_tree() -> void:
	var logger = load("res://builtin_reentry_logger.gd").new()
	logger.target = get_node("../BuiltinReentrySmoke")
	OS.add_logger(logger)

extends Logger
# Task 134 B: during an engine WARN/ERR print, call back into the Kotlin probe, which makes builtin
# calls of its own (see BuiltinReentrySmoke.kt).

var target: Object


func _log_error(function: String, file: String, line: int, code: String, rationale: String, editor_notify: bool, error_type: int, script_backtraces: Array[ScriptBacktrace]) -> void:
	var text := code + " " + rationale
	if target != null and (text.contains("colinear") or text.contains("Invalid color code")):
		target.scribble()

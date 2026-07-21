extends Node

var _bridge
var _benchmark_callback
var _reload_started := false


func _ready() -> void:
	if not OS.has_feature("web"):
		return
	_bridge = JavaScriptBridge.get_interface("KanamaWebBridge")
	_benchmark_callback = JavaScriptBridge.create_callback(_run_benchmark)
	_bridge.installBenchmarkCallback(_benchmark_callback)


func _process(_delta: float) -> void:
	if _bridge == null or _reload_started or not _bridge.shouldReload():
		return
	_reload_started = true
	_bridge.recordReloadStarted()
	get_tree().call_deferred("reload_current_scene")


func _exit_tree() -> void:
	if _bridge != null:
		_bridge.clearBenchmarkCallback()
	_benchmark_callback = null


func _run_benchmark(args: Array) -> float:
	var mode := int(args[0])
	var iterations := int(args[1])
	var checksum := 0
	var started := Time.get_ticks_usec()
	if mode == 0:
		for index in range(iterations):
			checksum = (checksum * 31) ^ index
	else:
		var target := get_parent() as Node2D
		for index in range(iterations):
			checksum = int(_bridge.roundTrip(index))
			target.position = Vector2(float(checksum % 640), target.position.y)
	var elapsed_ms := float(Time.get_ticks_usec() - started) / 1000.0
	_bridge.recordGdscriptChecksum(mode, checksum)
	_bridge.recordGdscriptBenchmark(mode, elapsed_ms)
	return elapsed_ms

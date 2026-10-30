extends SceneTree

# runtime_smoke.sh runs this with KANAMA_OWNERSHIP_SELFTEST=1. Like an editor `--script` check
# (check_exported_scene_properties.gd), it quits from _initialize, so the run ends one frame after
# the ownership self-test's worker started making Resources. Shutdown must stop that worker before
# Godot tears the ObjectDB down: no leaked instances and no abort at exit.
func _initialize() -> void:
	print("[kanama-smoke] selftest_quick_quit quitting")
	quit()

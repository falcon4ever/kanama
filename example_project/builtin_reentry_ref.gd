extends Node2D
# Task 134 B: the GDScript half of builtin_reentry_smoke.tscn (same calls as BuiltinReentrySmoke.kt).


func _ready() -> void:
	report("basis=%s" % Basis.looking_at(Vector3(0, 2, 0), Vector3(0, 1, 0), false))
	report("merge=%s" % AABB(Vector3(0, 0, 0), Vector3(-1, 1, 1)).merge(AABB(Vector3(5, 5, 5), Vector3(1, 1, 1))))
	report("html=%s" % Color.html("#zz"))
	report("nested=%s" % Vector2(1, 2).slerp(Vector2(3, 4).slerp(Vector2(5, 1), 0.25), 0.5))


func report(line: String) -> void:
	printerr("[kanama:kt] BuiltinReentry gdscript ", line)

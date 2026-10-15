extends Node
# Task 138 item 6: drives RegisterClassObjectReturnProbe (a @RegisterClass) through typed calls
# (the ptrcall upcall) on the main thread and on a GDScript Thread, and through Object.call (the
# Variant upcall), then waits FRAMES frames -- under KANAMA_GC_STRESS=1 each starts with a full
# collection, so the wrappers' own references are released -- and prints one line: every returned
# Resource must still be alive with exactly the references GDScript holds (the array's and the loop
# variable's: 2), and the Resource the probe keeps must be back at the probe's own reference.

const COUNT := 100
const FRAMES := 30

var probe: RegisterClassObjectReturnProbe
var held: Array = []
var worker_count := 0
var kept_after := -1
var frames := 0


func _ready() -> void:
	probe = ClassDB.instantiate("RegisterClassObjectReturnProbe")
	for i in COUNT:
		var typed = probe.make_resource()
		held.append(typed)
		held.append(probe.call("make_resource"))
	var thread := Thread.new()
	thread.start(_worker)
	var from_worker: Array = thread.wait_to_finish()
	worker_count = from_worker.size()
	held.append_array(from_worker)
	# Returned while the probe still holds it; GDScript drops each result at once.
	for i in 3:
		var kept = probe.kept_resource()
		kept = null
	kept_after = probe.kept_reference_count()


func _worker() -> Array:
	var out: Array = []
	for i in COUNT:
		var typed = probe.make_resource()
		out.append(typed)
	return out


func _process(_delta: float) -> void:
	frames += 1
	if frames < FRAMES:
		return
	var valid := 0
	var gdscript_only := 0
	for r in held:
		if is_instance_valid(r):
			valid += 1
			if r.get_reference_count() == 2:
				gdscript_only += 1
	print("RegisterClassObjectReturn returned=%d worker=%d valid=%d gdscript_only=%d kept_refcount=%d" % [
		held.size(), worker_count, valid, gdscript_only, kept_after])
	held.clear()
	probe.free()
	get_tree().quit()

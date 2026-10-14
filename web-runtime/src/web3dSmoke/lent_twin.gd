extends Node
## Harness-only: the engine side of LentProbe's rows, the lifetime of the objects a dispatch lends
## Kotlin (a signal argument, `_input`'s event, a method's object argument). It owns plain engine
## objects no Kanama script tracks, emits them, frees them, and reads the bridge's counters, so each
## row is the GDScript a game would run against the Kotlin a game would write.

signal one(obj: Object)
signal two(obj: Object, round: int)
signal outer(obj: Object)
signal inner(obj: Object)
signal escape(obj: Object)
signal rc(obj: Object)
signal moved(obj: Object)
signal doomed(obj: Object)
signal churn(obj: Object)
signal cost(obj: Object)

var body: Node3D
var plain: Node
var escapee: Node
var event: InputEventKey
var held_rc: Resource
var unkept_rc: WeakRef
var held_rc_weak: WeakRef
var resolved_rc: WeakRef
var doomed_free: Node
var doomed_queue: Node
var doomed_kotlin: WeakRef
var churn_max_lent := 0
var churn_max_live := 0


func _ready() -> void:
	body = Node3D.new()
	body.name = "LentBody"
	body.position = Vector3(1.0, 2.0, 3.0)
	add_child(body)
	plain = Node.new()
	plain.name = "LentPlain"
	add_child(plain)
	escapee = Node.new()
	escapee.name = "LentEscapee"
	add_child(escapee)


## Why a LentProbe row failed, for the driver's trace.
func publish_notes(text: String) -> int:
	JavaScriptBridge.eval("globalThis.KanamaWebLentNotes = %s" % JSON.stringify(text))
	return 1


func frames(_unused: int) -> int:
	return Engine.get_process_frames()


func counter(name: String) -> int:
	return int(JavaScriptBridge.eval("globalThis.KanamaWebBridge.%s ?? 0" % name))


## L1/L2: one object over every dispatch path. `_input` and `_unhandled_input` get an event this
## script keeps (a RefCounted Godot still holds).
func lend_all(probe: Node) -> int:
	one.emit(body)
	two.emit(body, 0)
	probe.call("take_node", body)
	probe.call("take_tagged", "tag", body)
	event = InputEventKey.new()
	event.keycode = KEY_K
	event.pressed = true
	get_viewport().push_input(event)
	return 1


func emit_one(_unused: int) -> int:
	one.emit(body)
	return 1


## L3: the outer handler asks for this while its own dispatch of `body` is still running.
func emit_outer(_unused: int) -> int:
	outer.emit(body)
	return 1


func emit_inner(_unused: int) -> int:
	inner.emit(body)
	return 1


func emit_escape(_unused: int) -> int:
	escape.emit(escapee)
	return 1


## L7: one RefCounted nobody keeps (only the argument list holds it), one this script keeps, and
## one nobody keeps that the Kotlin handler resolves to an owned reference (the documented way).
func emit_rc(_unused: int) -> int:
	var fresh := Resource.new()
	fresh.resource_name = "LentUnkept"
	unkept_rc = weakref(fresh)
	rc.emit(fresh)
	fresh = null
	held_rc = Resource.new()
	held_rc.resource_name = "LentHeld"
	held_rc_weak = weakref(held_rc)
	rc.emit(held_rc)
	var resolved := Resource.new()
	resolved.resource_name = "LentResolved"
	resolved_rc = weakref(resolved)
	rc.emit(resolved)
	resolved = null
	return 1


func resolved_alive(_unused: int) -> int:
	return 1 if resolved_rc != null and resolved_rc.get_ref() != null else 0


func unkept_alive(_unused: int) -> int:
	return 1 if unkept_rc != null and unkept_rc.get_ref() != null else 0


func held_alive(_unused: int) -> int:
	return 1 if held_rc_weak != null and held_rc_weak.get_ref() != null else 0


func drop_held(_unused: int) -> int:
	held_rc = null
	return held_alive(0)


## L8: move the node, then hand it over again.
func emit_moved(x: int) -> int:
	body.position = Vector3(float(x), 0.5, -1.0)
	moved.emit(body)
	return 1


## L5: two plain nodes handed over, then freed by the engine: one at once, one at the end of frame.
func emit_doomed(_unused: int) -> int:
	doomed_free = Node.new()
	doomed_free.name = "LentDoomedFree"
	add_child(doomed_free)
	doomed_queue = Node.new()
	doomed_queue.name = "LentDoomedQueue"
	add_child(doomed_queue)
	doomed.emit(doomed_free)
	doomed.emit(doomed_queue)
	return 1


func free_doomed(_unused: int) -> int:
	doomed_free.free()
	doomed_queue.queue_free()
	return 1


## L9: a node a Kotlin method argument frees with queueFree.
func lend_to_free(probe: Node) -> int:
	var node := Node.new()
	node.name = "LentKotlinFreed"
	add_child(node)
	doomed_kotlin = weakref(node)
	probe.call("take_and_free", node)
	return 1


func kotlin_freed_gone(_unused: int) -> int:
	return 1 if doomed_kotlin != null and doomed_kotlin.get_ref() == null else 0


## L6: `rounds` fresh nodes, each created, handed over and freed; the most lent handles alive at
## once (and live browser handles) over the rounds.
func churn_rounds(rounds: int) -> int:
	churn_max_lent = 0
	churn_max_live = 0
	for round in rounds:
		var node := Node.new()
		add_child(node)
		churn.emit(node)
		node.free()
		churn_max_lent = maxi(churn_max_lent, counter("liveLent"))
		churn_max_live = maxi(churn_max_live, counter("liveBrowserHandleCount"))
	return rounds


func churn_max(which: int) -> int:
	return churn_max_lent if which == 0 else churn_max_live


## L6: the same node handed over `rounds` times (to a handler that only counts).
func churn_same(rounds: int) -> int:
	for round in rounds:
		cost.emit(plain)
	return rounds


## L10: `rounds` dispatches of one object already seen (0 = a plain Node, 1 = a Node3D): the lending's
## GDScript->JS calls per 1000 dispatches (index 0) and the microseconds per 1000 (index 1).
func measure_cost(kind: int, rounds: int) -> PackedInt64Array:
	var target: Node = plain if kind == 0 else body
	cost.emit(target)
	var calls_before := counter("lendBridgeCalls")
	var started := Time.get_ticks_usec()
	for round in rounds:
		cost.emit(target)
	var elapsed := Time.get_ticks_usec() - started
	var calls := counter("lendBridgeCalls") - calls_before
	return PackedInt64Array([calls * 1000 / rounds, elapsed * 1000 / rounds])

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
signal twice(obj: Object)
signal capped(obj: Object)
signal renew(obj: Object)
signal paused_lend(obj: Object)
signal adopted(obj: Object)

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
var twice_node: WeakRef
var renew_rc: WeakRef
var renew_frame := -1
var renew_alive := -1
var renew_gone := -1
var burst_nodes: Array[Node] = []
var burst_base := 0
var burst_max := 0
var paused_node: Node
var paused_rc: WeakRef
var paused_frame := -1
var paused_base := 0
var paused_result := -1
var paused_lent_after := 0
var adopted_nodes: Array[Node] = []


func _ready() -> void:
	# The paused-tree row (L15) counts frames here while every Kanama script is paused.
	process_mode = Node.PROCESS_MODE_ALWAYS
	body = Node3D.new()
	body.name = "LentBody"
	body.position = Vector3(1.0, 2.0, 3.0)
	add_child(body)
	plain = Node.new()
	plain.name = "LentPlain"
	add_child(plain)


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


## L4: a node made here, so the only handle it ever gets is the one LentKeeper's dispatch lends
## (a lookup or a get_children result would route it through another script).
func emit_escape(_unused: int) -> int:
	escapee = Node.new()
	escapee.name = "LentEscapee"
	add_child(escapee)
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


## L11: one node handed to the same handler twice in one frame; the first call frees it from
## Kotlin (`queueFree()`), the second reads it (desktop: the node lives to the end of the frame).
func emit_twice(_unused: int) -> int:
	var node := Node.new()
	node.name = "LentTwice"
	add_child(node)
	twice_node = weakref(node)
	twice.emit(node)
	twice.emit(node)
	return 1


func twice_gone(_unused: int) -> int:
	return 1 if twice_node != null and twice_node.get_ref() == null else 0


## L12: one node handed over and freed, then `rounds` more created, handed over and freed: more
## freed handles than the bridge and Kotlin remember (4096).
func emit_capped(rounds: int) -> int:
	var first := Node.new()
	first.name = "LentCapped"
	add_child(first)
	capped.emit(first)
	first.free()
	for round in rounds:
		var node := Node.new()
		add_child(node)
		cost.emit(node)
		node.free()
	return rounds


## L13: a RefCounted nobody keeps, handed over now and again 2 frames later (the twin finds it
## through a weak reference): the second dispatch renews its 2 frames. `_process` records whether it
## is alive 4 frames after the first dispatch and gone by 8.
func emit_renew(_unused: int) -> int:
	var fresh := Resource.new()
	fresh.resource_name = "LentRenewed"
	renew_rc = weakref(fresh)
	renew_frame = Engine.get_process_frames()
	renew.emit(fresh)
	return renew_frame


func renew_state(which: int) -> int:
	return renew_alive if which == 0 else renew_gone


## L14: `count` fresh nodes handed over and kept, then all freed at once (`burst_free`).
## Returns the handles the burst minted.
func emit_burst(count: int) -> int:
	burst_base = counter("lentMints")
	for index in count:
		var node := Node.new()
		add_child(node)
		burst_nodes.append(node)
		cost.emit(node)
	burst_max = counter("lentMints")
	return burst_max - burst_base


func burst_free(_unused: int) -> int:
	for node in burst_nodes:
		node.free()
	burst_nodes.clear()
	return 1


## L15: a node and a RefCounted nobody keeps handed over, then the tree is paused (no Kanama script
## processes) and the node freed; `_process` (this node runs while paused) reads the live lent
## handles 6 frames later and resumes the tree. 1 = both handles were released while paused.
func emit_paused(_unused: int) -> int:
	paused_node = Node.new()
	paused_node.name = "LentPaused"
	add_child(paused_node)
	paused_lend.emit(paused_node)
	var fresh := Resource.new()
	paused_rc = weakref(fresh)
	paused_lend.emit(fresh)
	fresh = null
	paused_base = counter("liveLent")
	paused_frame = Engine.get_process_frames()
	paused_result = -1
	get_tree().paused = true
	paused_node.free()
	return paused_base


func paused_state(which: int) -> int:
	return paused_result if which == 0 else (paused_base if which == 1 else paused_lent_after)


## L16 / L17: a node a Kotlin script created (its handle is that script's, tracked before any
## dispatch) handed over: the dispatch takes the handle over for the node's lifetime. A Node2D is
## moved first, so the handler's read shows whether the dispatch refreshed its snapshot.
func emit_adopted(node: Node, x: int) -> int:
	if node is Node2D:
		(node as Node2D).position = Vector2(float(x), 5.0)
	adopted_nodes.append(node)
	adopted.emit(node)
	return 1


func free_adopted(_unused: int) -> int:
	for node in adopted_nodes:
		node.free()
	adopted_nodes.clear()
	return 1

func _process(_delta: float) -> void:
	var frame := Engine.get_process_frames()
	if renew_frame >= 0:
		if frame == renew_frame + 2 and renew_rc.get_ref() != null:
			renew.emit(renew_rc.get_ref())
		elif frame >= renew_frame + 4 and renew_alive < 0:
			renew_alive = 1 if renew_rc.get_ref() != null else 0
		elif frame >= renew_frame + 8:
			renew_gone = 1 if renew_rc.get_ref() == null else 0
			renew_frame = -1
	if paused_frame >= 0 and frame >= paused_frame + 6:
		paused_lent_after = counter("liveLent")
		var released := paused_lent_after == paused_base - 2
		paused_result = 1 if released and paused_rc.get_ref() == null else 0
		paused_frame = -1
		get_tree().paused = false

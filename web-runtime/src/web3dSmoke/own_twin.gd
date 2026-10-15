extends Node
## Harness-only: the engine side of OwnProbe's rows (the O rows), the lifetime of a RefCounted a
## script holds only in Kotlin (a dispatch's argument, a load, an event). It makes RefCounted
## objects nobody else keeps, emits them, keeps a weak reference to each so a row can ask whether
## it still lives, makes and frees the short-lived keeper scripts, and reads the bridge's counters.

signal own_one(obj: Object)
signal own_two(obj: Object, round: int)
signal own_close(obj: Object)
signal own_keep(obj: Object)
signal own_cost(obj: Object)

## Weak references to the objects a row emitted, by row name.
var weak: Dictionary = {}
var keeper: Node
var cost_event: InputEventKey
var cost_node: Node


func _ready() -> void:
	cost_node = Node.new()
	cost_node.name = "OwnCostNode"
	add_child(cost_node)


## Why an OwnProbe row failed, for the driver's trace.
func publish_notes(text: String) -> int:
	JavaScriptBridge.eval("globalThis.KanamaWebOwnNotes = %s" % JSON.stringify(text))
	return 1


func counter(name: String) -> int:
	return int(JavaScriptBridge.eval("globalThis.KanamaWebBridge.%s ?? 0" % name))


## 1 while the bridge still holds a live slot for `handle`.
func handle_live(handle: int) -> int:
	return int(JavaScriptBridge.eval("globalThis.KanamaWebBridge.isBrowserHandleLive(%d)" % handle))


## A full collection where the page offers one (Chrome with --expose-gc): 1 when it ran. Firefox has
## none; the probe allocates instead.
func collect(_unused: int) -> int:
	return int(JavaScriptBridge.eval("typeof globalThis.gc === 'function' ? (globalThis.gc(), 1) : 0"))


## 1 while the object the row `name` emitted still lives.
func alive(name: String) -> int:
	var ref: WeakRef = weak.get(name)
	return 1 if ref != null and ref.get_ref() != null else 0


func _fresh(name: String) -> Resource:
	var fresh := Resource.new()
	fresh.resource_name = name
	weak[name] = weakref(fresh)
	return fresh


## O1: one RefCounted per dispatch path, none kept here: the one-object arm, the packed arm, and an
## input event (`_input`). Only the arguments and the event queue hold them.
func emit_held(_unused: int) -> int:
	own_one.emit(_fresh("o1_one"))
	own_two.emit(_fresh("o1_two"), 0)
	var event := InputEventKey.new()
	event.keycode = KEY_O
	event.pressed = true
	weak["o1_input"] = weakref(event)
	get_viewport().push_input(event)
	return 1


## O3: a RefCounted nobody here keeps, for the handler to close.
func emit_close(round: int) -> int:
	own_close.emit(_fresh("o3_%d" % round))
	return 1


## O5 / O6: a LentKeeper made at run time (a sibling, so its "../OwnTwin" finds this node).
func make_keeper(_unused: int) -> int:
	keeper = load(KanamaWebHandles.proxy_paths["LentKeeper"]).new()
	keeper.name = "OwnKeeper"
	get_parent().add_child(keeper)
	return 1


## O5: the keeper connects to `own_keep` and stores what it receives; nobody here keeps it.
func keeper_receives(_unused: int) -> int:
	keeper.call("arm_own", 0)
	own_keep.emit(_fresh("o5"))
	return 1


## O6: the keeper loads a resource into its companion.
func keeper_loads(_unused: int) -> int:
	return int(keeper.call("keep_loaded", 0))


func free_keeper(_unused: int) -> int:
	keeper.free()
	keeper = null
	return 1


## O10: `rounds` dispatches of one object already seen (0 = an InputEvent, 1 = a plain Node) to a
## handler that keeps nothing: the lending's GDScript->JS calls plus the wrapper registrations per
## 1000 dispatches (index 0) and the microseconds per 1000 (index 1).
func measure_cost(kind: int, rounds: int) -> PackedInt64Array:
	if cost_event == null:
		cost_event = InputEventKey.new()
		cost_event.keycode = KEY_C
	var target: Object = cost_event if kind == 0 else cost_node
	own_cost.emit(target)
	var calls_before := counter("lendBridgeCalls") + counter("wrapperRegistrations")
	var started := Time.get_ticks_usec()
	for round in rounds:
		own_cost.emit(target)
	var elapsed := Time.get_ticks_usec() - started
	var calls := counter("lendBridgeCalls") + counter("wrapperRegistrations") - calls_before
	return PackedInt64Array([calls * 1000 / rounds, elapsed * 1000 / rounds])

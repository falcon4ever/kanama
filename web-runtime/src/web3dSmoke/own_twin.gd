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
signal own_ignored(obj: Object, round: int)
signal own_doomed(obj: Object)
signal own_motion(event: Object)

## Weak references to the objects a row emitted, by row name.
var weak: Dictionary = {}
var keeper: Node
var cost_event: InputEventKey
var cost_node: Node
var button: Button
var doomed: Node
var bumps := 0
var orphan: Window
var fade: ColorRect


func _ready() -> void:
	add_to_group("own_bump")
	cost_node = Node.new()
	cost_node.name = "OwnCostNode"
	add_child(cost_node)
	button = Button.new()
	button.name = "OwnButton"
	add_child(button)
	# O11: a node no script looks up before the keeper does.
	var kept := Node.new()
	kept.name = "OwnKeptNode"
	add_child(kept)
	# O9: a body no script tracks, far from the level, and a Node3D to query the world from.
	var body := StaticBody3D.new()
	body.name = "OwnRayBody"
	var shape := CollisionShape3D.new()
	var box := BoxShape3D.new()
	box.size = Vector3(2.0, 2.0, 2.0)
	shape.shape = box
	body.add_child(shape)
	add_child(body)
	body.global_position = Vector3(500.0, 500.0, 500.0)
	var from := Node3D.new()
	from.name = "OwnRayFrom"
	add_child(from)
	# The R rows: a second body below OwnRayBody (a ray that excludes the first hits it), an area
	# above it (hit only with collide_with_areas), a body only the RID cache row asks, a RayCast3D
	# and a SpringArm3D over a body each, and an area whose body_shape_entered carries a RID.
	_box_body("OwnRayBody2", Vector3(500.0, 496.0, 500.0))
	var area := Area3D.new()
	area.name = "OwnRayArea"
	area.add_child(_box_shape())
	add_child(area)
	area.global_position = Vector3(500.0, 504.0, 500.0)
	var rid_body := StaticBody3D.new()
	rid_body.name = "OwnRidBody"
	add_child(rid_body)
	_box_body("OwnCastBody", Vector3(520.0, 500.0, 500.0))
	var cast := RayCast3D.new()
	cast.name = "OwnRayCast"
	cast.target_position = Vector3(0.0, -20.0, 0.0)
	add_child(cast)
	cast.global_position = Vector3(520.0, 510.0, 500.0)
	_box_body("OwnSpringBody", Vector3(540.0, 500.0, 500.0))
	var arm := SpringArm3D.new()
	arm.name = "OwnSpringArm"
	arm.spring_length = 20.0
	add_child(arm)
	arm.global_position = Vector3(540.0, 510.0, 500.0)
	# The arm casts along its +Z: turned to point down.
	arm.rotation_degrees = Vector3(90.0, 0.0, 0.0)
	# R8b: one concave quad (two triangles) at x = 620, so a ray from either side meets one face.
	var back := StaticBody3D.new()
	back.name = "OwnBackFaceBody"
	var quad := ConcavePolygonShape3D.new()
	# A ray meets a back face only when the shape has back-face collision (Godot 4.7, either engine).
	quad.backface_collision = true
	quad.set_faces(PackedVector3Array([
		Vector3(-1, 0, -1), Vector3(1, 0, -1), Vector3(1, 0, 1),
		Vector3(-1, 0, -1), Vector3(1, 0, 1), Vector3(-1, 0, 1)]))
	var quad_shape := CollisionShape3D.new()
	quad_shape.shape = quad
	back.add_child(quad_shape)
	add_child(back)
	back.global_position = Vector3(620.0, 500.0, 500.0)
	var rid_area := Area3D.new()
	rid_area.name = "OwnRidArea"
	add_child(rid_area)
	rid_area.global_position = Vector3(580.0, 500.0, 500.0)
	# uint32Masks: a body and a visual instance OwnProbe writes uint32 masks to (no shape: it
	# collides with nothing).
	var mask_body := StaticBody3D.new()
	mask_body.name = "OwnMaskBody"
	add_child(mask_body)
	var layered := MeshInstance3D.new()
	layered.name = "OwnLayered"
	add_child(layered)
	# genericCloseKeepsSharedNode / handleRingBounded: nodes no script looks up before OwnProbe's
	# generic calls do.
	var shared := Node.new()
	shared.name = "OwnSharedNode"
	add_child(shared)
	var ringed := Node.new()
	ringed.name = "OwnRingNode"
	add_child(ringed)


func _box_shape() -> CollisionShape3D:
	var shape := CollisionShape3D.new()
	var box := BoxShape3D.new()
	box.size = Vector3(2.0, 2.0, 2.0)
	shape.shape = box
	return shape


func _box_body(body_name: String, at: Vector3) -> StaticBody3D:
	var body := StaticBody3D.new()
	body.name = body_name
	body.add_child(_box_shape())
	add_child(body)
	body.global_position = at
	return body


## R6: the RID of a body made and freed here (a stale RID: its validator no longer matches).
func stale_rid(_unused: int) -> int:
	var body := _box_body("OwnStaleBody", Vector3(600.0, 500.0, 500.0))
	var id := body.get_rid().get_id()
	body.free()
	return id


## R11: a RID whose id is past 2^53 (it never names a live object).
func big_rid() -> RID:
	return rid_from_int64(0x7fffffff12345678)


## R11: 1 when the RID Kotlin handed back has the same id.
func rid_is_big(rid: RID) -> int:
	return 1 if rid.get_id() == 0x7fffffff12345678 else 0


## R10: OwnRidArea's body_shape_entered with OwnRayBody's RID, as the physics server would emit it.
func emit_rid_signal(_unused: int) -> int:
	var body := get_node("OwnRayBody") as StaticBody3D
	(get_node("OwnRidArea") as Area3D).body_shape_entered.emit(body.get_rid(), body, 0, 0)
	return 1


## R9: how far OwnSpringArm reaches, in millimetres (its full length is 20 m).
func spring_hit_length_mm(_unused: int) -> int:
	return int((get_node("OwnSpringArm") as SpringArm3D).get_hit_length() * 1000.0)


## N2: a ColorRect handed back by a generic call (no canvas mirror), authored at alpha 0.25.
func fade_rect() -> ColorRect:
	if fade == null:
		fade = ColorRect.new()
		fade.name = "OwnFadeRect"
		fade.modulate = Color(1.0, 1.0, 1.0, 0.25)
		add_child(fade)
	return fade


## N2: the rect's alpha as the engine has it, x100.
func fade_rect_alpha_x100(_unused: int) -> int:
	return roundi(fade.modulate.a * 100.0)


## N4: a mouse motion whose screen_relative differs from its relative.
func emit_motion(_unused: int) -> int:
	var event := InputEventMouseMotion.new()
	event.relative = Vector2(1.0, 2.0)
	event.screen_relative = Vector2(3.0, 4.0)
	own_motion.emit(event)
	return 1


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
## none (the probe allocates instead, and a row accepts the capped bound when nothing was collected);
## nor does a run whose bridge ignores collections (the driver's KANAMA_WEB_IGNORE_COLLECTIONS).
func collect(_unused: int) -> int:
	return int(JavaScriptBridge.eval(
		"globalThis.KanamaWebBridge.ignoreWrapperCollections ? 0"
		+ " : typeof globalThis.gc === 'function' ? (globalThis.gc(), 1) : 0"))


## The bridge's view of `handle`: 1 live, | 2 a RefCounted held by Kotlin wrappers, | 4 under the
## lent cap (released, least recently wrapped first, above 4096 pinned).
func handle_state(handle: int) -> int:
	return int(JavaScriptBridge.eval(
		"(() => { const b = globalThis.KanamaWebBridge; const s = b.browserHandleSlot(%d);" % handle
		+ " if (!s) return 0; return 1 | (s.refCounted ? 2 : 0)"
		+ " | (b.lentRefCountedByAge.has(%d) ? 4 : 0); })()" % handle))


## How many RefCounted handles are under the lent cap now.
func capped_count(_unused: int) -> int:
	return int(JavaScriptBridge.eval("globalThis.KanamaWebBridge.lentRefCountedByAge.size"))


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


## O3b: 1 when OwnButton holds a ButtonGroup (the handler's queued set reached it before close()).
func button_has_group(_unused: int) -> int:
	return 1 if button.button_group != null else 0


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


## O11: the keeper looks a node up and makes one in a companion initializer, both kept in its
## companion.
func keeper_keeps_nodes(_unused: int) -> int:
	return int(keeper.call("keep_nodes", 0))


## pageHandlesAtMostTwo: a Window outside the tree (OwnProbe holds its handle).
func orphan_window() -> Window:
	if orphan == null:
		orphan = Window.new()
	return orphan


func _exit_tree() -> void:
	if orphan != null:
		orphan.free()
		orphan = null


## OwnProbe's shared row: the keeper looks up the node OwnProbe's generic call minted a handle for.
func keeper_keeps_shared(_unused: int) -> int:
	return int(keeper.call("keep_shared", 0))


## uint32Masks: 1 when OwnProbe's uint32 writes reached the engine whole.
func uint32_masks(_unused: int) -> int:
	var body := get_node("OwnMaskBody") as StaticBody3D
	var layered := get_node("OwnLayered") as MeshInstance3D
	var whole := body.collision_layer == 0xffffffff and body.collision_mask == 0xffffffff
	return 1 if whole and layered.layers == 0x80000001 else 0


## handleRingBounded: the sweep ring's length.
func ring_size(_unused: int) -> int:
	return KanamaWebHandles.lent_ring.size()


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


## O4: a RefCounted on the packed arm to a handler that never reads it.
func emit_ignored(_unused: int) -> int:
	own_ignored.emit(_fresh("o4"), 0)
	return 1


## O8: a fresh node no script tracks, for the probe to look up and queue_free.
func make_doomed(_unused: int) -> int:
	doomed = Node.new()
	doomed.name = "OwnDoomed"
	add_child(doomed)
	return 1


## O8: the queue_free'd node handed to a handler in the same frame (it lives to the frame's end).
func emit_doomed(_unused: int) -> int:
	if not is_instance_valid(doomed):
		return 0
	own_doomed.emit(doomed)
	return 1


## The constructor-rollback row: the group call the probe queued; how often it ran.
func own_bump() -> void:
	bumps += 1


func own_bumps(_unused: int) -> int:
	return bumps

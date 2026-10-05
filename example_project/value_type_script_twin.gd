extends Node
# Task 133: the GDScript twin of ValueTypeScriptSmoke.kt (same members, same tscn values).

signal flats(rect: Rect2, recti: Rect2i, vec4: Vector4, vec4i: Vector4i)
signal solids(plane: Plane, box: AABB, cell: Vector3i, rot: Quaternion)
signal frames(xform2: Transform2D, xform3: Transform3D, proj: Projection, basis: Basis)

@export var rect: Rect2 = Rect2(Vector2(1.0, 2.0), Vector2(3.0, 4.0))
@export var recti: Rect2i = Rect2i()
@export var vec4: Vector4 = Vector4.ONE
@export var vec4i: Vector4i = Vector4i(1, -2, 3, -4)
@export var plane: Plane = Plane.PLANE_XY
@export var box: AABB = AABB(Vector3(1.0, 2.0, 3.0), Vector3(4.0, 5.0, 6.0))
@export var xform2: Transform2D = Transform2D.FLIP_X
@export var xform3: Transform3D = Transform3D(Basis.IDENTITY, Vector3(1.0, 2.0, 3.0))
@export var proj: Projection = Projection.IDENTITY
@export var cell: Vector3i = Vector3i(1, 2, 3)
@export var rot: Quaternion = Quaternion.IDENTITY
@export var basis: Basis = Basis.FLIP_Y

func echo_rect(v: Rect2) -> Rect2: return v
func echo_recti(v: Rect2i) -> Rect2i: return v
func echo_vec4(v: Vector4) -> Vector4: return v
func echo_vec4i(v: Vector4i) -> Vector4i: return v
func echo_plane(v: Plane) -> Plane: return v
func echo_box(v: AABB) -> AABB: return v
func echo_xform2(v: Transform2D) -> Transform2D: return v
func echo_xform3(v: Transform3D) -> Transform3D: return v
func echo_proj(v: Projection) -> Projection: return v
func echo_cell(v: Vector3i) -> Vector3i: return v
func echo_rot(v: Quaternion) -> Quaternion: return v
func echo_basis(v: Basis) -> Basis: return v

func _on_flats(a: Rect2, b: Rect2i, c: Vector4, d: Vector4i) -> void:
	rect = a
	recti = b
	vec4 = c
	vec4i = d

func _on_solids(a: Plane, b: AABB, c: Vector3i, d: Quaternion) -> void:
	plane = a
	box = b
	cell = c
	rot = d

func _on_frames(a: Transform2D, b: Transform3D, c: Projection, d: Basis) -> void:
	xform2 = a
	xform3 = b
	proj = c
	basis = d

func listen() -> void:
	flats.connect(_on_flats)
	solids.connect(_on_solids)
	frames.connect(_on_frames)

func unlisten() -> void:
	flats.disconnect(_on_flats)
	solids.disconnect(_on_solids)
	frames.disconnect(_on_frames)

func emit_all() -> void:
	flats.emit(rect, recti, vec4, vec4i)
	solids.emit(plane, box, cell, rot)
	frames.emit(xform2, xform3, proj, basis)

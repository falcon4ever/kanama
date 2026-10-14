extends Node
# The GDScript twin of WrongTypeSetSmoke.kt: the same members with the same types, so the driver
# sees what GDScript does with a wrong-typed value.

@export var flag: bool = false
@export var count: int = 0
@export var ratio: float = 0.0
@export var label: String = ""
@export var path: NodePath = NodePath("")
@export var vec2: Vector2 = Vector2(0.0, 0.0)
@export var vec2i: Vector2i = Vector2i(0, 0)
@export var vec3: Vector3 = Vector3(0.0, 0.0, 0.0)
@export var vec3i: Vector3i = Vector3i(0, 0, 0)
@export var tint: Color = Color(0.0, 0.0, 0.0, 1.0)
@export var vec4: Vector4 = Vector4.ZERO
@export var area: Rect2 = Rect2()
@export var rot: Quaternion = Quaternion.IDENTITY
@export var xform: Transform3D = Transform3D.IDENTITY
@export var plane: Plane = Plane.PLANE_XY
@export var names: Array[String] = []
@export var modes: Array[int] = []
@export var extras: Dictionary = {}
@export var scores: Dictionary[String, int] = {}

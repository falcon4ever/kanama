package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.types.Vector3i
import net.multigesture.kanama.types.Vector4

enum class WrongTypeMode {
  FIRST,
  SECOND,
}

/**
 * A script property that receives a Variant of the wrong type. `wrong_type_set_smoke.tscn` holds
 * this script and its GDScript twin (`wrong_type_set_twin.gd`, the same typed members);
 * `wrong_type_set_driver.gd` offers the same values to each through Object.set and prints what
 * every property holds afterwards. GDScript refuses a wrong-typed value and applies the conversions
 * `Variant::construct` has (int and float, a String to a number, a Vector2i to a Vector2, ...), so
 * the Kotlin lines are the GDScript lines: a property keeps its value when the type is refused.
 * `scripts/runtime_smoke.sh` requires the two to be identical and the refusals to name the
 * property.
 */
@ScriptClass(attachTo = "Node")
class WrongTypeSetSmoke(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  @Export var flag: Boolean = false
  @Export var count: Long = 0L
  @Export var ratio: Double = 0.0
  @Export var label: String = ""
  @Export var path: NodePath = NodePath("")
  @Export var vec2: Vector2 = Vector2(0.0, 0.0)
  @Export var vec2i: Vector2i = Vector2i(0, 0)
  @Export var vec3: Vector3 = Vector3(0.0, 0.0, 0.0)
  @Export var vec3i: Vector3i = Vector3i(0, 0, 0)
  @Export var tint: Color = Color(0.0, 0.0, 0.0, 1.0)
  @Export var vec4: Vector4 = Vector4.ZERO
  @Export var area: Rect2 = Rect2.ZERO
  @Export var rot: Quaternion = Quaternion.IDENTITY
  @Export var xform: Transform3D = Transform3D.IDENTITY
  @Export var plane: Plane = Plane.PLANE_XY
  @Export var names: List<String> = emptyList()
  @Export var modes: List<WrongTypeMode> = emptyList()
  @Export var extras: Map<String, Any?> = emptyMap()
  @Export var scores: Map<String, Long> = emptyMap()
}

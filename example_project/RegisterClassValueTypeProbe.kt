package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.RegisterClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Projection
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.types.Vector3i
import net.multigesture.kanama.types.Vector4
import net.multigesture.kanama.types.Vector4i

/**
 * Task 133 review: every value type as a `@RegisterClass` (ClassDB extension class) argument and
 * return, over both the Variant `call` path and the typed `ptrcall` path
 * (`register_class_value_type_driver.gd`). Before the fix a Vector2i / Vector3i argument read past
 * a 4-byte scratch and value-type returns did not compile.
 */
@RegisterClass(parentClassName = "Node")
class RegisterClassValueTypeProbe(val godotObject: GodotHandle) {
  fun echoV2(v: Vector2): Vector2 = v

  fun echoV2i(v: Vector2i): Vector2i = v

  fun echoV3(v: Vector3): Vector3 = v

  fun echoV3i(v: Vector3i): Vector3i = v

  fun echoV4(v: Vector4): Vector4 = v

  fun echoV4i(v: Vector4i): Vector4i = v

  fun echoRect(v: Rect2): Rect2 = v

  fun echoRecti(v: Rect2i): Rect2i = v

  fun echoPlane(v: Plane): Plane = v

  fun echoBox(v: AABB): AABB = v

  fun echoRot(v: Quaternion): Quaternion = v

  fun echoBasis(v: Basis): Basis = v

  fun echoXform2(v: Transform2D): Transform2D = v

  fun echoXform3(v: Transform3D): Transform3D = v

  fun echoProj(v: Projection): Projection = v

  fun echoColor(v: Color): Color = v

  /** Kotlin's view of an argument, to prove the decode (not just a byte echo). */
  fun describeXform3(v: Transform3D): String = "${v.basis.x}|${v.basis.y}|${v.basis.z}|${v.origin}"
}

package web3d

import kotlin.math.PI
import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.Signal
import net.multigesture.kanama.api.GD.degToRad
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Basis
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

private const val TAU = 2 * PI

/**
 * Task 133 compile fixture for the Web value types (every value type since task 134 D1): one `@Export` per default the processor
 * normalizes for a Web script type (every named constant, and each constructor with nested
 * constants and folded `PI` / `TAU` / `degToRad`), plus value-type arguments, returns and a signal.
 * `ValueTypeScriptTypesTest` holds this file to the normalizer's constant table, and the web3d
 * build compiles it (and its proxy), so a default the processor accepts always exists on Web.
 * Attached to no node in the web3d scene.
 */
@ScriptClass(attachTo = "Node")
class ValueTypeDefaultsFixture(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  @Export var c0: Vector2 = Vector2.ZERO
  @Export var c1: Vector2 = Vector2.ONE
  @Export var c2: Vector2 = Vector2.INF
  @Export var c3: Vector2 = Vector2.UP
  @Export var c4: Vector2 = Vector2.DOWN
  @Export var c5: Vector2 = Vector2.LEFT
  @Export var c6: Vector2 = Vector2.RIGHT
  @Export var c7: Vector2i = Vector2i.ZERO
  @Export var c8: Vector2i = Vector2i.ONE
  @Export var c9: Vector2i = Vector2i.MIN
  @Export var c10: Vector2i = Vector2i.MAX
  @Export var c11: Vector2i = Vector2i.UP
  @Export var c12: Vector2i = Vector2i.DOWN
  @Export var c13: Vector2i = Vector2i.LEFT
  @Export var c14: Vector2i = Vector2i.RIGHT
  @Export var c15: Vector3 = Vector3.ZERO
  @Export var c16: Vector3 = Vector3.ONE
  @Export var c17: Vector3 = Vector3.INF
  @Export var c18: Vector3 = Vector3.UP
  @Export var c19: Vector3 = Vector3.DOWN
  @Export var c20: Vector3 = Vector3.LEFT
  @Export var c21: Vector3 = Vector3.RIGHT
  @Export var c22: Vector3 = Vector3.FORWARD
  @Export var c23: Vector3 = Vector3.BACK
  @Export var c24: Vector3 = Vector3.MODEL_LEFT
  @Export var c25: Vector3 = Vector3.MODEL_RIGHT
  @Export var c26: Vector3 = Vector3.MODEL_TOP
  @Export var c27: Vector3 = Vector3.MODEL_BOTTOM
  @Export var c28: Vector3 = Vector3.MODEL_FRONT
  @Export var c29: Vector3 = Vector3.MODEL_REAR
  @Export var c30: Vector3i = Vector3i.ZERO
  @Export var c31: Vector3i = Vector3i.ONE
  @Export var c32: Vector3i = Vector3i.MIN
  @Export var c33: Vector3i = Vector3i.MAX
  @Export var c34: Vector3i = Vector3i.UP
  @Export var c35: Vector3i = Vector3i.DOWN
  @Export var c36: Vector3i = Vector3i.LEFT
  @Export var c37: Vector3i = Vector3i.RIGHT
  @Export var c38: Vector3i = Vector3i.FORWARD
  @Export var c39: Vector3i = Vector3i.BACK
  @Export var c40: Rect2 = Rect2.ZERO
  @Export var c41: Rect2i = Rect2i.ZERO
  @Export var c42: Plane = Plane.ZERO
  @Export var c43: Plane = Plane.PLANE_YZ
  @Export var c44: Plane = Plane.PLANE_XZ
  @Export var c45: Plane = Plane.PLANE_XY
  @Export var c46: Quaternion = Quaternion.IDENTITY
  @Export var c47: Basis = Basis.IDENTITY
  @Export var c48: Basis = Basis.FLIP_X
  @Export var c49: Basis = Basis.FLIP_Y
  @Export var c50: Basis = Basis.FLIP_Z
  @Export var c51: Transform3D = Transform3D.IDENTITY
  @Export var c52: Transform3D = Transform3D.FLIP_X
  @Export var c53: Transform3D = Transform3D.FLIP_Y
  @Export var c54: Transform3D = Transform3D.FLIP_Z
  @Export var c55: Vector2 = Vector2(1.5, PI / 2)
  @Export var c56: Vector2i = Vector2i(3, 60 * 5)
  @Export var c57: Vector3 = Vector3(1, degToRad(90.0), -0.25)
  @Export var c58: Vector3i = Vector3i(-7, 8, 9)
  @Export var c59: Rect2 = Rect2(Vector2.UP, Vector2(0.5, TAU))
  @Export var c60: Rect2i = Rect2i(Vector2i(1, 2), Vector2i.ONE)
  @Export var c61: Plane = Plane(Vector3.UP, 2)
  @Export var c62: Plane = Plane(0.0, 1.0, 0.0, 2.5)
  @Export var c63: Quaternion = Quaternion(0.0, 0.6, 0.0, 0.8)
  @Export var c64: Basis = Basis(Vector3.RIGHT, Vector3(0.0, 1.0, 0.0), Vector3.BACK)
  @Export var c65: Transform3D = Transform3D(Basis.FLIP_Y, Vector3(1.0, 2.0, PI))

  @Export var c66: Vector4 = Vector4.ZERO
  @Export var c67: Vector4 = Vector4.ONE
  @Export var c68: Vector4 = Vector4.INF
  @Export var c69: Vector4i = Vector4i.ZERO
  @Export var c70: Vector4i = Vector4i.ONE
  @Export var c71: Vector4i = Vector4i.MIN
  @Export var c72: Vector4i = Vector4i.MAX
  @Export var c73: AABB = AABB.ZERO
  @Export var c74: Transform2D = Transform2D.IDENTITY
  @Export var c75: Transform2D = Transform2D.FLIP_X
  @Export var c76: Transform2D = Transform2D.FLIP_Y
  @Export var c77: Projection = Projection.IDENTITY
  @Export var c78: Projection = Projection.ZERO
  @Export var c79: Vector4 = Vector4(1.0, 2.0, PI, -0.5)
  @Export var c80: Vector4i = Vector4i(1, -2, 3, 60 * 5)
  @Export var c81: AABB = AABB(Vector3.UP, Vector3(0.5, 1.0, TAU))
  @Export var c82: Transform2D = Transform2D(Vector2.RIGHT, Vector2.DOWN, Vector2(3.0, 4.0))

  @Signal fun moved(transform: Transform3D) = Unit

  @Signal fun reshaped(box: AABB, view: Projection, count: Long) = Unit

  fun takeRect(rect: Rect2) {
    c65 = Transform3D(Basis.IDENTITY, Vector3(rect.position.x, rect.size.y, 0.0))
  }

  fun takeTransform(transform: Transform3D) {
    c65 = transform
  }

  fun currentTransform(): Transform3D = c65

  fun scaledBox(box: AABB, factor: Double): AABB = AABB(box.position * factor, box.size * factor)
}

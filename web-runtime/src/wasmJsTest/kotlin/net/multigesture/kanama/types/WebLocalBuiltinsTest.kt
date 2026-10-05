package net.multigesture.kanama.types

import kotlin.math.PI
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Task 134 D1: the Web build runs the shared value types, and the builtin methods the Web value
 * types always computed in Kotlin stay local (no bridge under Node, so these would throw if they
 * reached the engine path). Each result is checked against the identity it must satisfy.
 */
class WebLocalBuiltinsTest {
  private fun near(expected: Double, actual: Double, what: String) =
    assertTrue(kotlin.math.abs(expected - actual) < 1e-5, "$what: expected $expected, got $actual")

  private fun near(expected: Vector3, actual: Vector3, what: String) {
    near(expected.x, actual.x, "$what.x")
    near(expected.y, actual.y, "$what.y")
    near(expected.z, actual.z, "$what.z")
  }

  @Test
  fun vectorRotationsAndAngles() {
    val r = Vector2(1.0, 0.0).rotated(PI / 2)
    near(0.0, r.x, "rotated.x")
    near(1.0, r.y, "rotated.y")
    near(PI / 2, Vector2(0.0, 2.0).angle(), "angle")
    near(
      Vector3(0.0, 0.0, -1.0),
      Vector3(1.0, 0.0, 0.0).rotated(Vector3.UP, PI / 2),
      "Vector3.rotated",
    )
    near(
      -PI / 2,
      Vector3(1.0, 0.0, 0.0).signedAngleTo(Vector3(0.0, 0.0, 1.0), Vector3.UP),
      "signedAngleTo",
    )
  }

  @Test
  fun basisRoundTripsAndScale() {
    val euler = Vector3(0.3, -1.1, 0.7)
    val basis = Basis.fromEuler(euler)
    near(euler, basis.getEuler(), "getEuler(fromEuler)")
    near(1.0, basis.determinant(), "determinant")
    val scaled = basis.scaled(Vector3(2.0, 3.0, 4.0))
    near(24.0, scaled.determinant(), "scaled determinant")
    near(Vector3(1.0, 1.0, 1.0), basis.orthonormalized().getScale(), "orthonormalized scale")
    val q = basis.getRotationQuaternion()
    near(1.0, q.length(), "quaternion length")
    near(euler, Basis(q).getEuler(), "Basis(getRotationQuaternion)")
    near(Vector3(1.0, 0.0, 0.0), (basis * basis.inverse()).x, "basis * inverse")
    near(Vector3(0.0, 0.0, -1.0), Basis.IDENTITY.rotated(Vector3.UP, PI / 2).x, "Basis.rotated")
  }

  @Test
  fun lookingAtAndInterpolation() {
    val looking = Basis.lookingAt(Vector3(0.0, 0.0, -5.0))
    near(Vector3(0.0, 0.0, 1.0), looking.z, "lookingAt -Z")
    val from = Transform3D(Basis.IDENTITY, Vector3.ZERO)
    val to = Transform3D(Basis.fromEuler(Vector3(0.0, PI / 2, 0.0)), Vector3(10.0, 0.0, 0.0))
    val half = from.interpolateWith(to, 0.5)
    near(Vector3(5.0, 0.0, 0.0), half.origin, "interpolateWith origin")
    near(PI / 4, half.basis.getEuler().y, "interpolateWith rotation")
    val t = Transform3D(Basis.IDENTITY, Vector3(1.0, 2.0, 3.0)).lookingAt(Vector3(1.0, 2.0, -7.0))
    near(Vector3(0.0, 0.0, 1.0), t.basis.z, "Transform3D.lookingAt")
    near(Vector3(1.0, 2.0, 3.0), t.origin, "Transform3D.lookingAt origin")
    val q = Basis.IDENTITY.rotated(Vector3.UP, 1.0).getRotationQuaternion()
    near(0.0, Quaternion.IDENTITY.slerp(q, 0.0).y, "slerp at 0")
    assertEquals(q.isEqualApprox(Quaternion.IDENTITY.slerp(q, 1.0)), true, "slerp at 1")
  }
}

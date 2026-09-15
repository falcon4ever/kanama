package net.multigesture.kanama.types

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CompositeApproxTest {
  @Test
  fun compositeIsEqualApproxDelegatesToLeaves() {
    assertCompositeApprox(
      AABB(Vector3(1, 2, 3), Vector3(4, 5, 6)),
      AABB(Vector3(1.000004, 2, 3), Vector3(4, 5, 6)),
      AABB(Vector3(1.00002, 2, 3), Vector3(4, 5, 6)),
      { a, b -> a.position.isEqualApprox(b.position) && a.size.isEqualApprox(b.size) },
      AABB::isEqualApprox,
    )

    assertCompositeApprox(
      Rect2(Vector2(1, 2), Vector2(3, 4)),
      Rect2(Vector2(1.000004, 2), Vector2(3, 4)),
      Rect2(Vector2(1.00002, 2), Vector2(3, 4)),
      { a, b -> a.position.isEqualApprox(b.position) && a.size.isEqualApprox(b.size) },
      Rect2::isEqualApprox,
    )

    assertCompositeApprox(
      Basis.IDENTITY,
      Basis.IDENTITY.withX(Vector3(1.000004, 0, 0)),
      Basis.IDENTITY.withX(Vector3(1.00002, 0, 0)),
      { a, b -> a.x.isEqualApprox(b.x) && a.y.isEqualApprox(b.y) && a.z.isEqualApprox(b.z) },
      Basis::isEqualApprox,
    )

    assertCompositeApprox(
      Transform2D.IDENTITY,
      Transform2D.IDENTITY.copy(origin = Vector2(0.000004, 0)),
      Transform2D.IDENTITY.copy(origin = Vector2(0.00002, 0)),
      { a, b ->
        a.x.isEqualApprox(b.x) && a.y.isEqualApprox(b.y) && a.origin.isEqualApprox(b.origin)
      },
      Transform2D::isEqualApprox,
    )

    assertCompositeApprox(
      Transform3D.IDENTITY,
      Transform3D.IDENTITY.copy(origin = Vector3(0.000004, 0, 0)),
      Transform3D.IDENTITY.copy(origin = Vector3(0.00002, 0, 0)),
      { a, b -> a.basis.isEqualApprox(b.basis) && a.origin.isEqualApprox(b.origin) },
      Transform3D::isEqualApprox,
    )

    assertCompositeApprox(
      Projection.IDENTITY,
      Projection.IDENTITY.copy(x = Vector4(1.000004, 0, 0, 0)),
      Projection.IDENTITY.copy(x = Vector4(1.00002, 0, 0, 0)),
      { a, b ->
        a.x.isEqualApprox(b.x) &&
          a.y.isEqualApprox(b.y) &&
          a.z.isEqualApprox(b.z) &&
          a.w.isEqualApprox(b.w)
      },
      Projection::isEqualApprox,
    )
  }

  @Test
  fun compositeIsZeroApproxDelegatesToLeaves() {
    assertZeroApprox(
      AABB(Vector3(0.000004, 0, 0), Vector3.ZERO),
      AABB(Vector3(0.00002, 0, 0), Vector3.ZERO),
      { value -> value.position.isZeroApprox() && value.size.isZeroApprox() },
      AABB::isZeroApprox,
    )

    assertZeroApprox(
      Rect2(Vector2(0.000004, 0), Vector2.ZERO),
      Rect2(Vector2(0.00002, 0), Vector2.ZERO),
      { value -> value.position.isZeroApprox() && value.size.isZeroApprox() },
      Rect2::isZeroApprox,
    )

    assertZeroApprox(
      Basis(Vector3(0.000004, 0, 0), Vector3.ZERO, Vector3.ZERO),
      Basis(Vector3(0.00002, 0, 0), Vector3.ZERO, Vector3.ZERO),
      { value -> value.x.isZeroApprox() && value.y.isZeroApprox() && value.z.isZeroApprox() },
      Basis::isZeroApprox,
    )

    assertZeroApprox(
      Transform2D(Vector2(0.000004, 0), Vector2.ZERO, Vector2.ZERO),
      Transform2D(Vector2(0.00002, 0), Vector2.ZERO, Vector2.ZERO),
      { value -> value.x.isZeroApprox() && value.y.isZeroApprox() && value.origin.isZeroApprox() },
      Transform2D::isZeroApprox,
    )

    assertZeroApprox(
      Transform3D(Basis(Vector3(0.000004, 0, 0), Vector3.ZERO, Vector3.ZERO), Vector3.ZERO),
      Transform3D(Basis(Vector3(0.00002, 0, 0), Vector3.ZERO, Vector3.ZERO), Vector3.ZERO),
      { value -> value.basis.isZeroApprox() && value.origin.isZeroApprox() },
      Transform3D::isZeroApprox,
    )

    assertZeroApprox(
      Projection(Vector4(0.000004, 0, 0, 0), Vector4.ZERO, Vector4.ZERO, Vector4.ZERO),
      Projection(Vector4(0.00002, 0, 0, 0), Vector4.ZERO, Vector4.ZERO, Vector4.ZERO),
      { value ->
        value.x.isZeroApprox() &&
          value.y.isZeroApprox() &&
          value.z.isZeroApprox() &&
          value.w.isZeroApprox()
      },
      Projection::isZeroApprox,
    )
  }

  private fun <T> assertCompositeApprox(
    base: T,
    near: T,
    far: T,
    leafExpected: (T, T) -> Boolean,
    actual: T.(T) -> Boolean,
  ) {
    assertEquals(leafExpected(base, near), base.actual(near))
    assertTrue(base.actual(near))
    assertEquals(leafExpected(base, far), base.actual(far))
    assertFalse(base.actual(far))
  }

  private fun <T> assertZeroApprox(
    nearZero: T,
    farFromZero: T,
    leafExpected: (T) -> Boolean,
    actual: T.() -> Boolean,
  ) {
    assertEquals(leafExpected(nearZero), nearZero.actual())
    assertTrue(nearZero.actual())
    assertEquals(leafExpected(farFromZero), farFromZero.actual())
    assertFalse(farFromZero.actual())
  }
}

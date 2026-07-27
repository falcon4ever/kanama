package net.multigesture.kanama.types

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

data class Vector2(val x: Double, val y: Double) {
  constructor(x: Number, y: Number) : this(x.toDouble(), y.toDouble())

  operator fun plus(other: Vector2): Vector2 = Vector2(x + other.x, y + other.y)

  operator fun minus(other: Vector2): Vector2 = Vector2(x - other.x, y - other.y)

  operator fun times(scalar: Number): Vector2 =
    Vector2(x * scalar.toDouble(), y * scalar.toDouble())

  operator fun times(scalar: Double): Vector2 = Vector2(x * scalar, y * scalar)

  operator fun div(scalar: Number): Vector2 = Vector2(x / scalar.toDouble(), y / scalar.toDouble())

  operator fun div(scalar: Double): Vector2 = Vector2(x / scalar, y / scalar)

  fun length(): Double = sqrt(x * x + y * y)

  fun angle(): Double = atan2(y, x)

  fun lerp(to: Vector2, weight: Double): Vector2 =
    Vector2(x + (to.x - x) * weight, y + (to.y - y) * weight)

  fun withX(value: Number): Vector2 = Vector2(value.toDouble(), y)

  fun withY(value: Number): Vector2 = Vector2(x, value.toDouble())

  fun normalized(): Vector2 {
    val len = length()
    return if (len > 0.0) Vector2(x / len, y / len) else ZERO
  }

  fun clamp(min: Vector2, max: Vector2): Vector2 =
    Vector2(x.coerceIn(min.x, max.x), y.coerceIn(min.y, max.y))

  fun rotated(angle: Double): Vector2 {
    val c = cos(angle)
    val s = sin(angle)
    return Vector2(x * c - y * s, x * s + y * c)
  }

  companion object {
    val ZERO = Vector2(0.0, 0.0)
    val ONE = Vector2(1.0, 1.0)
    val RIGHT = Vector2(1.0, 0.0)
    val DOWN = Vector2(0.0, 1.0)
  }
}

data class Vector3(val x: Double, val y: Double, val z: Double) {
  constructor(x: Number, y: Number, z: Number) : this(x.toDouble(), y.toDouble(), z.toDouble())

  operator fun plus(other: Vector3): Vector3 = Vector3(x + other.x, y + other.y, z + other.z)

  operator fun minus(other: Vector3): Vector3 = Vector3(x - other.x, y - other.y, z - other.z)

  operator fun times(scalar: Number): Vector3 =
    Vector3(x * scalar.toDouble(), y * scalar.toDouble(), z * scalar.toDouble())

  operator fun div(scalar: Number): Vector3 =
    Vector3(x / scalar.toDouble(), y / scalar.toDouble(), z / scalar.toDouble())

  fun length(): Double = sqrt(x * x + y * y + z * z)

  fun lengthSquared(): Double = x * x + y * y + z * z

  operator fun unaryMinus(): Vector3 = Vector3(-x, -y, -z)

  fun distanceTo(other: Vector3): Double = (other - this).length()

  fun distanceSquaredTo(other: Vector3): Double = (other - this).lengthSquared()

  /** Godot's bounce: reflect off the plane with (unit) normal [normal]. */
  fun bounce(normal: Vector3): Vector3 = this - normal * (2.0 * dot(normal))

  fun dot(other: Vector3): Double = x * other.x + y * other.y + z * other.z

  fun cross(other: Vector3): Vector3 =
    Vector3(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x)

  /** Signed angle to [to] around [axis] (Godot's signed_angle_to). */
  fun signedAngleTo(to: Vector3, axis: Vector3): Double {
    val crossTo = cross(to)
    val unsigned = atan2(crossTo.length(), dot(to))
    return if (crossTo.dot(axis) < 0.0) -unsigned else unsigned
  }

  /** Move toward [to] by at most [delta] (Godot's move_toward). */
  fun moveToward(to: Vector3, delta: Double): Vector3 {
    val difference = to - this
    val len = difference.length()
    return if (len <= delta || len < 1e-8) to else this + difference / len * delta
  }

  fun withX(value: Number): Vector3 = Vector3(value.toDouble(), y, z)

  fun withY(value: Number): Vector3 = Vector3(x, value.toDouble(), z)

  fun withZ(value: Number): Vector3 = Vector3(x, y, value.toDouble())

  fun normalized(): Vector3 {
    val len = length()
    return if (len > 0.0) Vector3(x / len, y / len, z / len) else ZERO
  }

  fun lerp(to: Vector3, weight: Double): Vector3 =
    Vector3(x + (to.x - x) * weight, y + (to.y - y) * weight, z + (to.z - z) * weight)

  fun limitLength(max: Double): Vector3 {
    val len = length()
    return if (len > 0.0 && len > max) this * (max / len) else this
  }

  /** Rodrigues rotation of this vector around a (unit) [axis] by [angle] radians. */
  fun rotated(axis: Vector3, angle: Double): Vector3 {
    val a = axis.normalized()
    val c = cos(angle)
    val s = sin(angle)
    val dot = x * a.x + y * a.y + z * a.z
    val crossX = a.y * z - a.z * y
    val crossY = a.z * x - a.x * z
    val crossZ = a.x * y - a.y * x
    return Vector3(
      x * c + crossX * s + a.x * dot * (1 - c),
      y * c + crossY * s + a.y * dot * (1 - c),
      z * c + crossZ * s + a.z * dot * (1 - c),
    )
  }

  companion object {
    val ZERO = Vector3(0.0, 0.0, 0.0)
    val ONE = Vector3(1.0, 1.0, 1.0)
    val UP = Vector3(0.0, 1.0, 0.0)
    val DOWN = Vector3(0.0, -1.0, 0.0)
    val FORWARD = Vector3(0.0, 0.0, -1.0)
    val BACK = Vector3(0.0, 0.0, 1.0)
    val LEFT = Vector3(-1.0, 0.0, 0.0)
    val RIGHT = Vector3(1.0, 0.0, 0.0)
  }
}

data class Rect2(val position: Vector2, val size: Vector2)

data class Color(val r: Float, val g: Float, val b: Float, val a: Float = 1.0f)

data class Vector2i(val x: Int, val y: Int) {
  companion object {
    val ZERO = Vector2i(0, 0)
  }
}

/** Rotation quaternion backing Basis decomposition and slerp (Godot layout: x, y, z, w). */
data class Quaternion(val x: Double, val y: Double, val z: Double, val w: Double) {
  fun normalized(): Quaternion {
    val len = sqrt(x * x + y * y + z * z + w * w)
    return if (len > 0.0) Quaternion(x / len, y / len, z / len, w / len) else IDENTITY
  }

  /** Spherical interpolation along the shortest arc (Godot's slerp). */
  fun slerp(to: Quaternion, weight: Double): Quaternion {
    var cosom = x * to.x + y * to.y + z * to.z + w * to.w
    var target = to
    if (cosom < 0.0) {
      cosom = -cosom
      target = Quaternion(-to.x, -to.y, -to.z, -to.w)
    }
    val scale0: Double
    val scale1: Double
    if (1.0 - cosom > 1e-6) {
      val omega = kotlin.math.acos(cosom)
      val sinom = sin(omega)
      scale0 = sin((1.0 - weight) * omega) / sinom
      scale1 = sin(weight * omega) / sinom
    } else {
      scale0 = 1.0 - weight
      scale1 = weight
    }
    return Quaternion(
      scale0 * x + scale1 * target.x,
      scale0 * y + scale1 * target.y,
      scale0 * z + scale1 * target.z,
      scale0 * w + scale1 * target.w,
    )
  }

  companion object {
    val IDENTITY = Quaternion(0.0, 0.0, 0.0, 1.0)
  }
}

/**
 * 3x3 basis over row-major storage (rows match Godot's internal layout: xform(v) dots rows with v;
 * the x/y/z axes are COLUMNS). Pure Kotlin — composes from the mirrored rotation/scale snapshots
 * without an engine crossing.
 */
class Basis internal constructor(internal val m: DoubleArray) {
  init {
    require(m.size == 9)
  }

  /** Godot's axis constructor: [xAxis]/[yAxis]/[zAxis] are the matrix COLUMNS. */
  constructor(
    xAxis: Vector3,
    yAxis: Vector3,
    zAxis: Vector3,
  ) : this(
    doubleArrayOf(xAxis.x, yAxis.x, zAxis.x, xAxis.y, yAxis.y, zAxis.y, xAxis.z, yAxis.z, zAxis.z)
  )

  constructor(
    quaternion: Quaternion
  ) : this(
    quaternion.normalized().let { q ->
      val xx = q.x * q.x
      val yy = q.y * q.y
      val zz = q.z * q.z
      val xy = q.x * q.y
      val xz = q.x * q.z
      val yz = q.y * q.z
      val wx = q.w * q.x
      val wy = q.w * q.y
      val wz = q.w * q.z
      doubleArrayOf(
        1.0 - 2.0 * (yy + zz),
        2.0 * (xy - wz),
        2.0 * (xz + wy),
        2.0 * (xy + wz),
        1.0 - 2.0 * (xx + zz),
        2.0 * (yz - wx),
        2.0 * (xz - wy),
        2.0 * (yz + wx),
        1.0 - 2.0 * (xx + yy),
      )
    }
  )

  operator fun times(v: Vector3): Vector3 =
    Vector3(
      m[0] * v.x + m[1] * v.y + m[2] * v.z,
      m[3] * v.x + m[4] * v.y + m[5] * v.z,
      m[6] * v.x + m[7] * v.y + m[8] * v.z,
    )

  /** Rotation-only inverse: the transpose. */
  fun inverse(): Basis = transposed()

  fun transposed(): Basis =
    Basis(doubleArrayOf(m[0], m[3], m[6], m[1], m[4], m[7], m[2], m[5], m[8]))

  fun getColumn(index: Int): Vector3 = Vector3(m[index], m[3 + index], m[6 + index])

  /** Column lengths signed by the determinant (Godot's get_scale). */
  fun getScale(): Vector3 {
    val detSign = if (determinant() < 0.0) -1.0 else 1.0
    return Vector3(
      detSign * getColumn(0).length(),
      detSign * getColumn(1).length(),
      detSign * getColumn(2).length(),
    )
  }

  fun determinant(): Double =
    m[0] * (m[4] * m[8] - m[5] * m[7]) - m[1] * (m[3] * m[8] - m[5] * m[6]) +
      m[2] * (m[3] * m[7] - m[4] * m[6])

  /** Godot's scaled(): rows scaled componentwise (scale applied on the left). */
  fun scaled(scale: Vector3): Basis =
    Basis(
      doubleArrayOf(
        m[0] * scale.x,
        m[1] * scale.x,
        m[2] * scale.x,
        m[3] * scale.y,
        m[4] * scale.y,
        m[5] * scale.y,
        m[6] * scale.z,
        m[7] * scale.z,
        m[8] * scale.z,
      )
    )

  /** Columns normalized (drops scale; assumes no shear — node transforms in these demos). */
  fun orthonormalized(): Basis {
    val x = getColumn(0).normalized()
    val y = getColumn(1).normalized()
    val z = getColumn(2).normalized()
    return Basis(x, y, z)
  }

  /** Rotation quaternion of the orthonormalized basis (Shepperd's method). */
  fun getRotationQuaternion(): Quaternion {
    val ortho = orthonormalized()
    val r = (if (ortho.determinant() < 0.0) ortho.scaled(Vector3(-1, -1, -1)) else ortho).m
    val trace = r[0] + r[4] + r[8]
    val raw =
      if (trace > 0.0) {
        val s = sqrt(trace + 1.0) * 2.0
        Quaternion((r[7] - r[5]) / s, (r[2] - r[6]) / s, (r[3] - r[1]) / s, 0.25 * s)
      } else if (r[0] > r[4] && r[0] > r[8]) {
        val s = sqrt(1.0 + r[0] - r[4] - r[8]) * 2.0
        Quaternion(0.25 * s, (r[1] + r[3]) / s, (r[2] + r[6]) / s, (r[7] - r[5]) / s)
      } else if (r[4] > r[8]) {
        val s = sqrt(1.0 + r[4] - r[0] - r[8]) * 2.0
        Quaternion((r[1] + r[3]) / s, 0.25 * s, (r[5] + r[7]) / s, (r[2] - r[6]) / s)
      } else {
        val s = sqrt(1.0 + r[8] - r[0] - r[4]) * 2.0
        Quaternion((r[2] + r[6]) / s, (r[5] + r[7]) / s, 0.25 * s, (r[3] - r[1]) / s)
      }
    return raw.normalized()
  }

  /** Euler angles in Godot's default YXZ order (inverse of [fromEuler]). */
  fun getEuler(): Vector3 {
    val r = orthonormalized().m
    val sx = -r[5]
    return if (sx > 0.999999) {
      Vector3(kotlin.math.PI / 2.0, atan2(r[3], r[0]), 0.0)
    } else if (sx < -0.999999) {
      Vector3(-kotlin.math.PI / 2.0, atan2(r[3], r[0]), 0.0)
    } else {
      Vector3(kotlin.math.asin(sx), atan2(r[2], r[8]), atan2(r[3], r[4]))
    }
  }

  companion object {
    val IDENTITY = Basis(doubleArrayOf(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0))

    /** Godot's default Euler order YXZ: R = Ry * Rx * Rz. */
    fun fromEuler(euler: Vector3): Basis {
      val cx = cos(euler.x)
      val sx = sin(euler.x)
      val cy = cos(euler.y)
      val sy = sin(euler.y)
      val cz = cos(euler.z)
      val sz = sin(euler.z)
      return Basis(
        doubleArrayOf(
          cy * cz + sy * sx * sz,
          -cy * sz + sy * sx * cz,
          sy * cx,
          cx * sz,
          cx * cz,
          -sx,
          -sy * cz + cy * sx * sz,
          sy * sz + cy * sx * cz,
          cy * cx,
        )
      )
    }

    /** Godot's looking_at basis: -Z oriented at [direction], [up] as the vertical hint. */
    fun lookingAt(direction: Vector3, up: Vector3 = Vector3.UP): Basis {
      val z = -direction.normalized()
      val x = up.cross(z).normalized()
      val y = z.cross(x)
      return Basis(x, y, z)
    }
  }
}

/** Basis + origin. Pure Kotlin — the engine side sees only the decomposed writes. */
data class Transform3D(val basis: Basis, val origin: Vector3) {
  /** Godot's xform: rotate/scale then translate. */
  operator fun times(v: Vector3): Vector3 = basis * v + origin

  fun withBasis(newBasis: Basis): Transform3D = Transform3D(newBasis, origin)

  fun withOrigin(newOrigin: Vector3): Transform3D = Transform3D(basis, newOrigin)

  /** Keeps the origin; orients -Z at [target] (Godot's looking_at). */
  fun lookingAt(target: Vector3, up: Vector3 = Vector3.UP): Transform3D =
    Transform3D(Basis.lookingAt(target - origin, up), origin)

  /** Godot's interpolate_with: slerp rotation, lerp scale and origin. */
  fun interpolateWith(other: Transform3D, weight: Double): Transform3D {
    val rotation = basis.getRotationQuaternion().slerp(other.basis.getRotationQuaternion(), weight)
    val scale = basis.getScale().lerp(other.basis.getScale(), weight)
    return Transform3D(Basis(rotation).scaled(scale), origin.lerp(other.origin, weight))
  }

  companion object {
    val IDENTITY = Transform3D(Basis.IDENTITY, Vector3.ZERO)
  }
}

package net.multigesture.kanama.types

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * Godot's Vector2. Components are `Double` in every signature and stored as float32, Godot's
 * `real_t` in Web builds, so equality, printing and basic arithmetic match GDScript's (task 134).
 */
class Vector2
private constructor(
  internal val rawX: Float,
  internal val rawY: Float,
  @Suppress("UNUSED_PARAMETER") raw: RawStorage,
) {
  constructor(x: Double, y: Double) : this(x.toFloat(), y.toFloat(), RawStorage)

  constructor(x: Int, y: Int) : this(x.toDouble(), y.toDouble())

  val x: Double
    get() = rawX.toDouble()

  val y: Double
    get() = rawY.toDouble()

  operator fun component1(): Double = x

  operator fun component2(): Double = y

  fun copy(x: Double = this.x, y: Double = this.y): Vector2 = Vector2(x, y)

  override fun equals(other: Any?): Boolean =
    this === other ||
      (other is Vector2 && storedEquals(rawX, other.rawX) && storedEquals(rawY, other.rawY))

  override fun hashCode(): Int = 31 * storedHash(rawX) + storedHash(rawY)

  /** Godot's `str(v)`: `(0.1, 0.2)`. */
  override fun toString(): String =
    "(${godotRealString(rawX, true)}, ${godotRealString(rawY, true)})"

  operator fun plus(other: Vector2): Vector2 = Vector2(x + other.x, y + other.y)

  operator fun minus(other: Vector2): Vector2 = Vector2(x - other.x, y - other.y)

  // A scalar operand is a `real_t` (float32) in Godot: narrowed first, like `Vector2 * float`.
  operator fun times(scalar: Double): Vector2 = real(scalar).let { s -> Vector2(x * s, y * s) }

  operator fun times(scalar: Int): Vector2 = times(scalar.toDouble())

  operator fun times(scalar: Long): Vector2 = times(scalar.toDouble())

  operator fun div(scalar: Double): Vector2 = real(scalar).let { s -> Vector2(x / s, y / s) }

  operator fun div(scalar: Int): Vector2 = div(scalar.toDouble())

  operator fun div(scalar: Long): Vector2 = div(scalar.toDouble())

  fun length(): Double = sqrt(x * x + y * y)

  fun angle(): Double = atan2(y, x)

  fun lerp(to: Vector2, weight: Double): Vector2 =
    Vector2(x + (to.x - x) * weight, y + (to.y - y) * weight)

  fun withX(value: Double): Vector2 = Vector2(value, y)

  fun withY(value: Double): Vector2 = Vector2(x, value)

  fun withX(value: Int): Vector2 = withX(value.toDouble())

  fun withY(value: Int): Vector2 = withY(value.toDouble())

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

/** Godot's Vector3: `Double` in every signature, stored as float32 (Godot's Web `real_t`). */
class Vector3
private constructor(
  internal val rawX: Float,
  internal val rawY: Float,
  internal val rawZ: Float,
  @Suppress("UNUSED_PARAMETER") raw: RawStorage,
) {
  constructor(
    x: Double,
    y: Double,
    z: Double,
  ) : this(x.toFloat(), y.toFloat(), z.toFloat(), RawStorage)

  constructor(x: Int, y: Int, z: Int) : this(x.toDouble(), y.toDouble(), z.toDouble())

  val x: Double
    get() = rawX.toDouble()

  val y: Double
    get() = rawY.toDouble()

  val z: Double
    get() = rawZ.toDouble()

  operator fun component1(): Double = x

  operator fun component2(): Double = y

  operator fun component3(): Double = z

  fun copy(x: Double = this.x, y: Double = this.y, z: Double = this.z): Vector3 = Vector3(x, y, z)

  override fun equals(other: Any?): Boolean =
    this === other ||
      (other is Vector3 &&
        storedEquals(rawX, other.rawX) &&
        storedEquals(rawY, other.rawY) &&
        storedEquals(rawZ, other.rawZ))

  override fun hashCode(): Int = 31 * (31 * storedHash(rawX) + storedHash(rawY)) + storedHash(rawZ)

  /** Godot's `str(v)`: `(0.1, 0.2, 0.3)`. */
  override fun toString(): String =
    "(${godotRealString(rawX, true)}, ${godotRealString(rawY, true)}, ${godotRealString(rawZ, true)})"

  operator fun plus(other: Vector3): Vector3 = Vector3(x + other.x, y + other.y, z + other.z)

  operator fun minus(other: Vector3): Vector3 = Vector3(x - other.x, y - other.y, z - other.z)

  // A scalar operand is a `real_t` (float32) in Godot: narrowed first, like `Vector3 * float`.
  operator fun times(scalar: Double): Vector3 =
    real(scalar).let { s -> Vector3(x * s, y * s, z * s) }

  operator fun times(scalar: Int): Vector3 = times(scalar.toDouble())

  operator fun times(scalar: Long): Vector3 = times(scalar.toDouble())

  operator fun div(scalar: Double): Vector3 = real(scalar).let { s -> Vector3(x / s, y / s, z / s) }

  operator fun div(scalar: Int): Vector3 = div(scalar.toDouble())

  operator fun div(scalar: Long): Vector3 = div(scalar.toDouble())

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

  fun withX(value: Double): Vector3 = Vector3(value, y, z)

  fun withY(value: Double): Vector3 = Vector3(x, value, z)

  fun withZ(value: Double): Vector3 = Vector3(x, y, value)

  fun withX(value: Int): Vector3 = withX(value.toDouble())

  fun withY(value: Int): Vector3 = withY(value.toDouble())

  fun withZ(value: Int): Vector3 = withZ(value.toDouble())

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

data class Rect2(val position: Vector2, val size: Vector2) {
  /** Godot's `str(r)`: `[P: (0.0, 0.0), S: (1.0, 1.0)]`. */
  override fun toString(): String = "[P: $position, S: $size]"
}

/**
 * Godot's Color: channels are `Double` in every signature and stored as float32, as Godot stores
 * them in every build, so equality and printing match GDScript's (task 134).
 */
class Color
private constructor(
  internal val rawR: Float,
  internal val rawG: Float,
  internal val rawB: Float,
  internal val rawA: Float,
  @Suppress("UNUSED_PARAMETER") raw: RawStorage,
) {
  constructor(
    r: Double,
    g: Double,
    b: Double,
    a: Double = 1.0,
  ) : this(r.toFloat(), g.toFloat(), b.toFloat(), a.toFloat(), RawStorage)

  constructor(
    r: Int,
    g: Int,
    b: Int,
    a: Int = 1,
  ) : this(r.toDouble(), g.toDouble(), b.toDouble(), a.toDouble())

  val r: Double
    get() = rawR.toDouble()

  val g: Double
    get() = rawG.toDouble()

  val b: Double
    get() = rawB.toDouble()

  val a: Double
    get() = rawA.toDouble()

  operator fun component1(): Double = r

  operator fun component2(): Double = g

  operator fun component3(): Double = b

  operator fun component4(): Double = a

  fun copy(r: Double = this.r, g: Double = this.g, b: Double = this.b, a: Double = this.a): Color =
    Color(r, g, b, a)

  // Godot's `==` on the stored channels: -0.0 == 0.0 (the old data-class equals said otherwise).
  override fun equals(other: Any?): Boolean =
    this === other ||
      (other is Color &&
        storedEquals(rawR, other.rawR) &&
        storedEquals(rawG, other.rawG) &&
        storedEquals(rawB, other.rawB) &&
        storedEquals(rawA, other.rawA))

  override fun hashCode(): Int {
    var result = storedHash(rawR)
    result = 31 * result + storedHash(rawG)
    result = 31 * result + storedHash(rawB)
    result = 31 * result + storedHash(rawA)
    return result
  }

  /** Godot's `str(c)`: four decimals at most, `(1.0, 0.5, 0.0, 1.0)`. */
  override fun toString(): String =
    "(${godotNum(r, 4)}, ${godotNum(g, 4)}, ${godotNum(b, 4)}, ${godotNum(a, 4)})"
}

data class Vector3i(val x: Int, val y: Int, val z: Int) {
  companion object {
    val ZERO = Vector3i(0, 0, 0)
  }
}

/** Godot's Plane in Hessian normal form: [normal] and signed distance [d] from the origin. */
class Plane
private constructor(
  val normal: Vector3,
  internal val rawD: Float,
  @Suppress("UNUSED_PARAMETER") raw: RawStorage,
) {
  constructor(normal: Vector3, d: Double) : this(normal, d.toFloat(), RawStorage)

  constructor(normal: Vector3, d: Int) : this(normal, d.toDouble())

  val d: Double
    get() = rawD.toDouble()

  override fun equals(other: Any?): Boolean =
    this === other || (other is Plane && normal == other.normal && storedEquals(rawD, other.rawD))

  override fun hashCode(): Int = 31 * normal.hashCode() + storedHash(rawD)

  /** Godot's `str(p)`: `[N: (0.0, 1.0, 0.0), D: 0]`. */
  override fun toString(): String = "[N: $normal, D: ${godotRealString(rawD, false)}]"

  /**
   * Godot's intersects_ray: the intersection of the ray [from] + t * [dir] with this plane, or null
   * when the ray is parallel to or points away from it.
   */
  fun intersectsRay(from: Vector3, dir: Vector3): Vector3? {
    val den = normal.dot(dir)
    if (kotlin.math.abs(den) < 1e-8) return null
    val dist = (normal.dot(from) - d) / den
    if (dist > 1e-5) return null
    return from + dir * -dist
  }
}

data class Vector2i(val x: Int, val y: Int) {
  companion object {
    val ZERO = Vector2i(0, 0)
  }
}

/** Integer axis-aligned rectangle; mirrors desktop's `Rect2i` (position + size, derived `end`). */
data class Rect2i(val position: Vector2i, val size: Vector2i) {
  val end: Vector2i
    get() = Vector2i(position.x + size.x, position.y + size.y)

  companion object {
    val ZERO = Rect2i(Vector2i(0, 0), Vector2i(0, 0))
  }
}

/** Rotation quaternion backing Basis decomposition and slerp (Godot layout: x, y, z, w). */
class Quaternion
private constructor(
  internal val rawX: Float,
  internal val rawY: Float,
  internal val rawZ: Float,
  internal val rawW: Float,
  @Suppress("UNUSED_PARAMETER") raw: RawStorage,
) {
  constructor(
    x: Double,
    y: Double,
    z: Double,
    w: Double,
  ) : this(x.toFloat(), y.toFloat(), z.toFloat(), w.toFloat(), RawStorage)

  val x: Double
    get() = rawX.toDouble()

  val y: Double
    get() = rawY.toDouble()

  val z: Double
    get() = rawZ.toDouble()

  val w: Double
    get() = rawW.toDouble()

  operator fun component1(): Double = x

  operator fun component2(): Double = y

  operator fun component3(): Double = z

  operator fun component4(): Double = w

  fun copy(
    x: Double = this.x,
    y: Double = this.y,
    z: Double = this.z,
    w: Double = this.w,
  ): Quaternion = Quaternion(x, y, z, w)

  override fun equals(other: Any?): Boolean =
    this === other ||
      (other is Quaternion &&
        storedEquals(rawX, other.rawX) &&
        storedEquals(rawY, other.rawY) &&
        storedEquals(rawZ, other.rawZ) &&
        storedEquals(rawW, other.rawW))

  override fun hashCode(): Int {
    var result = storedHash(rawX)
    result = 31 * result + storedHash(rawY)
    result = 31 * result + storedHash(rawZ)
    result = 31 * result + storedHash(rawW)
    return result
  }

  /** Godot's `str(q)`: `(0, 0, 0, 1)`. */
  override fun toString(): String =
    "(${godotRealString(rawX, false)}, ${godotRealString(rawY, false)}, " +
      "${godotRealString(rawZ, false)}, ${godotRealString(rawW, false)})"

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
class Basis internal constructor(private val stored: FloatArray) {
  /** Row-major 3x3, widened from the float32 storage for the Double math below. */
  internal val m: DoubleArray
    get() = DoubleArray(9) { stored[it].toDouble() }

  internal constructor(m: DoubleArray) : this(FloatArray(9) { m[it].toFloat() })

  init {
    require(stored.size == 9)
  }

  /** Godot's axis constructor: [xAxis]/[yAxis]/[zAxis] are the matrix COLUMNS. */
  constructor(
    xAxis: Vector3,
    yAxis: Vector3,
    zAxis: Vector3,
  ) : this(
    floatArrayOf(
      xAxis.rawX,
      yAxis.rawX,
      zAxis.rawX,
      xAxis.rawY,
      yAxis.rawY,
      zAxis.rawY,
      xAxis.rawZ,
      yAxis.rawZ,
      zAxis.rawZ,
    )
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
      stored[0] * v.x + stored[1] * v.y + stored[2] * v.z,
      stored[3] * v.x + stored[4] * v.y + stored[5] * v.z,
      stored[6] * v.x + stored[7] * v.y + stored[8] * v.z,
    )

  /** Row-major matrix product (this applied after [other] in xform order). */
  operator fun times(other: Basis): Basis {
    val a = m
    val b = other.m
    val out = DoubleArray(9)
    for (row in 0..2) {
      for (col in 0..2) {
        out[row * 3 + col] =
          a[row * 3] * b[col] + a[row * 3 + 1] * b[3 + col] + a[row * 3 + 2] * b[6 + col]
      }
    }
    return Basis(out)
  }

  /** Rotation-only inverse: the transpose. */
  fun inverse(): Basis = transposed()

  fun transposed(): Basis =
    Basis(
      floatArrayOf(
        stored[0],
        stored[3],
        stored[6],
        stored[1],
        stored[4],
        stored[7],
        stored[2],
        stored[5],
        stored[8],
      )
    )

  fun getColumn(index: Int): Vector3 =
    Vector3(stored[index].toDouble(), stored[3 + index].toDouble(), stored[6 + index].toDouble())

  override fun equals(other: Any?): Boolean =
    this === other ||
      (other is Basis && (0 until 9).all { storedEquals(stored[it], other.stored[it]) })

  override fun hashCode(): Int = (0 until 9).fold(0) { acc, i -> 31 * acc + storedHash(stored[i]) }

  /** Godot's `str(b)`: the columns, `[X: (1.0, 0.0, 0.0), Y: …, Z: …]`. */
  override fun toString(): String = "[X: $x, Y: $y, Z: $z]"

  /** Godot's basis.x/y/z axis properties (matrix COLUMNS). */
  val x: Vector3
    get() = getColumn(0)

  val y: Vector3
    get() = getColumn(1)

  val z: Vector3
    get() = getColumn(2)

  fun withX(axis: Vector3): Basis = withColumn(0, axis)

  fun withY(axis: Vector3): Basis = withColumn(1, axis)

  fun withZ(axis: Vector3): Basis = withColumn(2, axis)

  private fun withColumn(index: Int, axis: Vector3): Basis {
    val out = m.copyOf()
    out[index] = axis.x
    out[3 + index] = axis.y
    out[6 + index] = axis.z
    return Basis(out)
  }

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
    // Godot's real_t (float32) arithmetic on the stored values.
    (stored[0] * (stored[4] * stored[8] - stored[5] * stored[7]) -
        stored[1] * (stored[3] * stored[8] - stored[5] * stored[6]) +
        stored[2] * (stored[3] * stored[7] - stored[4] * stored[6]))
      .toDouble()

  /** Godot's scaled(): rows scaled componentwise (scale applied on the left). */
  fun scaled(scale: Vector3): Basis =
    Basis(
      doubleArrayOf(
        stored[0] * scale.x,
        stored[1] * scale.x,
        stored[2] * scale.x,
        stored[3] * scale.y,
        stored[4] * scale.y,
        stored[5] * scale.y,
        stored[6] * scale.z,
        stored[7] * scale.z,
        stored[8] * scale.z,
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

    /** Rodrigues rotation matrix about (unit) [axis] by [angle] radians. */
    fun fromAxisAngle(axis: Vector3, angle: Double): Basis {
      val a = axis.normalized()
      val c = cos(angle)
      val s = sin(angle)
      val t = 1.0 - c
      return Basis(
        doubleArrayOf(
          t * a.x * a.x + c,
          t * a.x * a.y - s * a.z,
          t * a.x * a.z + s * a.y,
          t * a.x * a.y + s * a.z,
          t * a.y * a.y + c,
          t * a.y * a.z - s * a.x,
          t * a.x * a.z - s * a.y,
          t * a.y * a.z + s * a.x,
          t * a.z * a.z + c,
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
  /** Godot's `str(t)`: `[X: (1.0, 0.0, 0.0), Y: …, Z: …, O: (0.0, 0.0, 0.0)]`. */
  override fun toString(): String = "[X: ${basis.x}, Y: ${basis.y}, Z: ${basis.z}, O: $origin]"

  /** Godot's xform: rotate/scale then translate. */
  operator fun times(v: Vector3): Vector3 = basis * v + origin

  fun withBasis(newBasis: Basis): Transform3D = Transform3D(newBasis, origin)

  fun withOrigin(newOrigin: Vector3): Transform3D = Transform3D(basis, newOrigin)

  fun orthonormalized(): Transform3D = Transform3D(basis.orthonormalized(), origin)

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

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

  // Every Int/Double mix (GDScript's `Vector2(x, 0)`): exactly one overload matches, none boxes.
  constructor(x: Int, y: Double) : this(x.toDouble(), y)

  constructor(x: Double, y: Int) : this(x, y.toDouble())

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
  override fun toString(): String = "(${godotRealString(x, true)}, ${godotRealString(y, true)})"

  operator fun plus(other: Vector2): Vector2 = Vector2(x + other.x, y + other.y)

  operator fun minus(other: Vector2): Vector2 = Vector2(x - other.x, y - other.y)

  // A scalar operand is a `real_t` (float32) in Godot: narrowed first, like `Vector2 * float`.
  operator fun times(scalar: Double): Vector2 =
    narrowReal(scalar).let { s -> raw(rawX * s, rawY * s) }

  operator fun times(scalar: Int): Vector2 = times(scalar.toDouble())

  operator fun times(scalar: Long): Vector2 = times(scalar.toDouble())

  operator fun div(scalar: Double): Vector2 =
    narrowReal(scalar).let { s -> raw(rawX / s, rawY / s) }

  operator fun div(scalar: Int): Vector2 = div(scalar.toDouble())

  operator fun div(scalar: Long): Vector2 = div(scalar.toDouble())

  fun length(): Double = widenReal(sqrt(realDot(rawX, rawY, rawX, rawY)))

  fun angle(): Double = widenReal(atan2(rawY, rawX))

  fun withX(value: Double): Vector2 = Vector2(value, y)

  fun withY(value: Double): Vector2 = Vector2(x, value)

  fun withX(value: Int): Vector2 = withX(value.toDouble())

  fun withY(value: Int): Vector2 = withY(value.toDouble())

  fun normalized(): Vector2 =
    realNormalize(
      rawX.isFinite() && rawY.isFinite(),
      realDot(rawX, rawY, rawX, rawY),
      { ZERO },
      { len -> raw(rawX / len, rawY / len) },
    )

  fun rotated(angle: Double): Vector2 {
    val c = cos(angle)
    val s = sin(angle)
    return Vector2(x * c - y * s, x * s + y * c)
  }

  // ===== BEGIN GENERATED BUILTIN MEMBERS: Vector2 (generate_builtin_ops.py) =====
  /**
   * Godot's `Vector2.Axis` enum as a typed value: `.value` is the raw number Godot uses, and the
   * companion holds the named values (`Vector2.Axis.<NAME>`).
   *
   * Generated from Godot docs: Vector2.Axis
   */
  @kotlin.jvm.JvmInline
  value class Axis(override val value: Long) : net.multigesture.kanama.api.GodotEnumValue {
    companion object {
      /**
       * Enumerated value for the X axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector2.AXIS_X
       */
      val X: Axis
        get() = Axis(0L)

      /**
       * Enumerated value for the Y axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector2.AXIS_Y
       */
      val Y: Axis
        get() = Axis(1L)
    }
  }

  operator fun unaryPlus(): Vector2 = this

  operator fun compareTo(other: Vector2): Int {
    val c0 = godotCompareStep(rawX, other.rawX)
    if (c0 != 0) return c0
    return godotCompareStep(rawY, other.rawY)
  }

  operator fun times(other: Vector2): Vector2 = raw(rawX * other.rawX, rawY * other.rawY)

  operator fun div(other: Vector2): Vector2 = raw(rawX / other.rawX, rawY / other.rawY)

  fun directionTo(to: Vector2): Vector2 = raw(to.rawX - rawX, to.rawY - rawY).normalized()

  fun limitLength(length: Double = 1.0): Vector2 {
    val p = narrowReal(length)
    val l = godotSqrt(rawX * rawX + rawY * rawY)
    if (!(l > narrowReal(0.0) && p < l)) return this
    return raw(rawX / l * p, rawY / l * p)
  }

  fun isFinite(): Boolean = rawX.isFinite() && rawY.isFinite()

  fun posmod(mod: Double): Vector2 {
    val m = narrowReal(mod)
    return raw(godotFposmod(rawX, m), godotFposmod(rawY, m))
  }

  fun posmodv(modv: Vector2): Vector2 =
    raw(godotFposmod(rawX, modv.rawX), godotFposmod(rawY, modv.rawY))

  fun project(b: Vector2): Vector2 {
    val s = (rawX * b.rawX + rawY * b.rawY) / (b.rawX * b.rawX + b.rawY * b.rawY)
    return raw(b.rawX * s, b.rawY * s)
  }

  fun lerp(to: Vector2, weight: Double): Vector2 {
    val w = narrowReal(weight)
    return raw(realLerp(rawX, to.rawX, w), realLerp(rawY, to.rawY, w))
  }

  fun maxAxisIndex(): Long = if (rawX < rawY) 1L else 0L

  fun minAxisIndex(): Long = if (rawX < rawY) 0L else 1L

  fun moveToward(to: Vector2, delta: Double): Vector2 {
    val d = narrowReal(delta)
    val v0 = to.rawX - rawX
    val v1 = to.rawY - rawY
    val len = godotSqrt(v0 * v0 + v1 * v1)
    if (len <= d || len < narrowReal(0.00001)) return to
    return raw(rawX + v0 / len * d, rawY + v1 / len * d)
  }

  fun orthogonal(): Vector2 = raw(rawY, -rawX)

  fun floor(): Vector2 = raw(godotFloor(rawX), godotFloor(rawY))

  fun ceil(): Vector2 = raw(godotCeil(rawX), godotCeil(rawY))

  fun round(): Vector2 = raw(godotRound(rawX), godotRound(rawY))

  fun aspect(): Double = widenReal(rawX / rawY)

  fun slide(n: Vector2): Vector2 {
    val d = rawX * n.rawX + rawY * n.rawY
    return raw(rawX - n.rawX * d, rawY - n.rawY * d)
  }

  fun bounce(n: Vector2): Vector2 {
    val d = rawX * n.rawX + rawY * n.rawY
    val two = narrowReal(2.0)
    return raw(-(n.rawX * two * d - rawX), -(n.rawY * two * d - rawY))
  }

  fun reflect(line: Vector2): Vector2 {
    val d = rawX * line.rawX + rawY * line.rawY
    val two = narrowReal(2.0)
    return raw(line.rawX * two * d - rawX, line.rawY * two * d - rawY)
  }

  fun cross(with: Vector2): Double = widenReal(rawX * with.rawY - rawY * with.rawX)

  fun abs(): Vector2 = raw(godotFabs(rawX), godotFabs(rawY))

  fun sign(): Vector2 = raw(godotSign(rawX), godotSign(rawY))

  fun clamp(min: Vector2, max: Vector2): Vector2 =
    raw(godotClamp(rawX, min.rawX, max.rawX), godotClamp(rawY, min.rawY, max.rawY))

  fun clampf(min: Double, max: Double): Vector2 {
    val lo = narrowReal(min)
    val hi = narrowReal(max)
    return raw(godotClamp(rawX, lo, hi), godotClamp(rawY, lo, hi))
  }

  fun min(with: Vector2): Vector2 = raw(godotMin(rawX, with.rawX), godotMin(rawY, with.rawY))

  fun minf(with: Double): Vector2 {
    val s = narrowReal(with)
    return raw(godotMin(rawX, s), godotMin(rawY, s))
  }

  fun max(with: Vector2): Vector2 = raw(godotMax(rawX, with.rawX), godotMax(rawY, with.rawY))

  fun maxf(with: Double): Vector2 {
    val s = narrowReal(with)
    return raw(godotMax(rawX, s), godotMax(rawY, s))
  }

  // ===== END GENERATED BUILTIN MEMBERS: Vector2 =====

  companion object {
    // ===== BEGIN GENERATED BUILTIN STATICS: Vector2 (generate_builtin_ops.py) =====
    val INF: Vector2 = Vector2(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)

    val LEFT: Vector2 = Vector2(-1.0, 0.0)

    val UP: Vector2 = Vector2(0.0, -1.0)

    // ===== END GENERATED BUILTIN STATICS: Vector2 =====

    internal fun raw(x: Float, y: Float): Vector2 = Vector2(x, y, RawStorage)

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

  // Every Int/Double mix (GDScript's `Vector3(x, 0, y)`): exactly one overload matches, none boxes.
  constructor(x: Int, y: Int, z: Double) : this(x.toDouble(), y.toDouble(), z)

  constructor(x: Int, y: Double, z: Int) : this(x.toDouble(), y, z.toDouble())

  constructor(x: Int, y: Double, z: Double) : this(x.toDouble(), y, z)

  constructor(x: Double, y: Int, z: Int) : this(x, y.toDouble(), z.toDouble())

  constructor(x: Double, y: Int, z: Double) : this(x, y.toDouble(), z)

  constructor(x: Double, y: Double, z: Int) : this(x, y, z.toDouble())

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
    "(${godotRealString(x, true)}, ${godotRealString(y, true)}, ${godotRealString(z, true)})"

  operator fun plus(other: Vector3): Vector3 = Vector3(x + other.x, y + other.y, z + other.z)

  operator fun minus(other: Vector3): Vector3 = Vector3(x - other.x, y - other.y, z - other.z)

  // A scalar operand is a `real_t` (float32) in Godot: narrowed first, like `Vector3 * float`.
  operator fun times(scalar: Double): Vector3 =
    narrowReal(scalar).let { s -> raw(rawX * s, rawY * s, rawZ * s) }

  operator fun times(scalar: Int): Vector3 = times(scalar.toDouble())

  operator fun times(scalar: Long): Vector3 = times(scalar.toDouble())

  operator fun div(scalar: Double): Vector3 =
    narrowReal(scalar).let { s -> raw(rawX / s, rawY / s, rawZ / s) }

  operator fun div(scalar: Int): Vector3 = div(scalar.toDouble())

  operator fun div(scalar: Long): Vector3 = div(scalar.toDouble())

  fun length(): Double = widenReal(sqrt(rawLengthSquared()))

  fun lengthSquared(): Double = widenReal(rawLengthSquared())

  private fun rawLengthSquared(): Float = realDot(rawX, rawY, rawZ, rawX, rawY, rawZ)

  operator fun unaryMinus(): Vector3 = Vector3(-x, -y, -z)

  fun distanceTo(other: Vector3): Double = (other - this).length()

  fun distanceSquaredTo(other: Vector3): Double = (other - this).lengthSquared()

  /** Godot's bounce: reflect off the plane with (unit) normal [normal]. */
  fun bounce(normal: Vector3): Vector3 = this - normal * (2.0 * dot(normal))

  fun dot(other: Vector3): Double =
    widenReal(realDot(rawX, rawY, rawZ, other.rawX, other.rawY, other.rawZ))

  fun cross(other: Vector3): Vector3 =
    realCross(rawX, rawY, rawZ, other.rawX, other.rawY, other.rawZ) { x, y, z -> raw(x, y, z) }

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

  fun normalized(): Vector3 =
    realNormalize(
      rawX.isFinite() && rawY.isFinite() && rawZ.isFinite(),
      rawLengthSquared(),
      { ZERO },
      { len -> raw(rawX / len, rawY / len, rawZ / len) },
    )

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

  // ===== BEGIN GENERATED BUILTIN MEMBERS: Vector3 (generate_builtin_ops.py) =====
  /**
   * Godot's `Vector3.Axis` enum as a typed value: `.value` is the raw number Godot uses, and the
   * companion holds the named values (`Vector3.Axis.<NAME>`).
   *
   * Generated from Godot docs: Vector3.Axis
   */
  @kotlin.jvm.JvmInline
  value class Axis(override val value: Long) : net.multigesture.kanama.api.GodotEnumValue {
    companion object {
      /**
       * Enumerated value for the X axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector3.AXIS_X
       */
      val X: Axis
        get() = Axis(0L)

      /**
       * Enumerated value for the Y axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector3.AXIS_Y
       */
      val Y: Axis
        get() = Axis(1L)

      /**
       * Enumerated value for the Z axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector3.AXIS_Z
       */
      val Z: Axis
        get() = Axis(2L)
    }
  }

  operator fun unaryPlus(): Vector3 = this

  operator fun compareTo(other: Vector3): Int {
    val c0 = godotCompareStep(rawX, other.rawX)
    if (c0 != 0) return c0
    val c1 = godotCompareStep(rawY, other.rawY)
    if (c1 != 0) return c1
    return godotCompareStep(rawZ, other.rawZ)
  }

  operator fun times(other: Vector3): Vector3 =
    raw(rawX * other.rawX, rawY * other.rawY, rawZ * other.rawZ)

  operator fun div(other: Vector3): Vector3 =
    raw(rawX / other.rawX, rawY / other.rawY, rawZ / other.rawZ)

  fun minAxisIndex(): Long =
    if (rawX < rawY) (if (rawX < rawZ) 0L else 2L) else (if (rawY < rawZ) 1L else 2L)

  fun maxAxisIndex(): Long =
    if (rawX < rawY) (if (rawY < rawZ) 2L else 1L) else (if (rawX < rawZ) 2L else 0L)

  fun directionTo(to: Vector3): Vector3 =
    raw(to.rawX - rawX, to.rawY - rawY, to.rawZ - rawZ).normalized()

  fun limitLength(length: Double = 1.0): Vector3 {
    val p = narrowReal(length)
    val l = godotSqrt(rawX * rawX + rawY * rawY + rawZ * rawZ)
    if (!(l > narrowReal(0.0) && p < l)) return this
    return raw(rawX / l * p, rawY / l * p, rawZ / l * p)
  }

  fun isFinite(): Boolean = rawX.isFinite() && rawY.isFinite() && rawZ.isFinite()

  fun inverse(): Vector3 {
    val one = narrowReal(1.0)
    return raw(one / rawX, one / rawY, one / rawZ)
  }

  fun clamp(min: Vector3, max: Vector3): Vector3 =
    raw(
      godotClamp(rawX, min.rawX, max.rawX),
      godotClamp(rawY, min.rawY, max.rawY),
      godotClamp(rawZ, min.rawZ, max.rawZ),
    )

  fun clampf(min: Double, max: Double): Vector3 {
    val lo = narrowReal(min)
    val hi = narrowReal(max)
    return raw(godotClamp(rawX, lo, hi), godotClamp(rawY, lo, hi), godotClamp(rawZ, lo, hi))
  }

  fun lerp(to: Vector3, weight: Double): Vector3 {
    val w = narrowReal(weight)
    return raw(realLerp(rawX, to.rawX, w), realLerp(rawY, to.rawY, w), realLerp(rawZ, to.rawZ, w))
  }

  fun abs(): Vector3 = raw(godotFabs(rawX), godotFabs(rawY), godotFabs(rawZ))

  fun floor(): Vector3 = raw(godotFloor(rawX), godotFloor(rawY), godotFloor(rawZ))

  fun ceil(): Vector3 = raw(godotCeil(rawX), godotCeil(rawY), godotCeil(rawZ))

  fun round(): Vector3 = raw(godotRound(rawX), godotRound(rawY), godotRound(rawZ))

  fun posmod(mod: Double): Vector3 {
    val m = narrowReal(mod)
    return raw(godotFposmod(rawX, m), godotFposmod(rawY, m), godotFposmod(rawZ, m))
  }

  fun posmodv(modv: Vector3): Vector3 =
    raw(godotFposmod(rawX, modv.rawX), godotFposmod(rawY, modv.rawY), godotFposmod(rawZ, modv.rawZ))

  fun project(b: Vector3): Vector3 {
    val s =
      (rawX * b.rawX + rawY * b.rawY + rawZ * b.rawZ) /
        (b.rawX * b.rawX + b.rawY * b.rawY + b.rawZ * b.rawZ)
    return raw(b.rawX * s, b.rawY * s, b.rawZ * s)
  }

  fun slide(n: Vector3): Vector3 {
    val d = rawX * n.rawX + rawY * n.rawY + rawZ * n.rawZ
    return raw(rawX - n.rawX * d, rawY - n.rawY * d, rawZ - n.rawZ * d)
  }

  fun reflect(line: Vector3): Vector3 {
    val d = rawX * line.rawX + rawY * line.rawY + rawZ * line.rawZ
    val two = narrowReal(2.0)
    return raw(line.rawX * two * d - rawX, line.rawY * two * d - rawY, line.rawZ * two * d - rawZ)
  }

  fun sign(): Vector3 = raw(godotSign(rawX), godotSign(rawY), godotSign(rawZ))

  fun min(with: Vector3): Vector3 =
    raw(godotMin(rawX, with.rawX), godotMin(rawY, with.rawY), godotMin(rawZ, with.rawZ))

  fun minf(with: Double): Vector3 {
    val s = narrowReal(with)
    return raw(godotMin(rawX, s), godotMin(rawY, s), godotMin(rawZ, s))
  }

  fun max(with: Vector3): Vector3 =
    raw(godotMax(rawX, with.rawX), godotMax(rawY, with.rawY), godotMax(rawZ, with.rawZ))

  fun maxf(with: Double): Vector3 {
    val s = narrowReal(with)
    return raw(godotMax(rawX, s), godotMax(rawY, s), godotMax(rawZ, s))
  }

  // ===== END GENERATED BUILTIN MEMBERS: Vector3 =====

  companion object {
    // ===== BEGIN GENERATED BUILTIN STATICS: Vector3 (generate_builtin_ops.py) =====
    val INF: Vector3 =
      Vector3(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)

    val MODEL_LEFT: Vector3 = Vector3(1.0, 0.0, 0.0)

    val MODEL_RIGHT: Vector3 = Vector3(-1.0, 0.0, 0.0)

    val MODEL_TOP: Vector3 = Vector3(0.0, 1.0, 0.0)

    val MODEL_BOTTOM: Vector3 = Vector3(0.0, -1.0, 0.0)

    val MODEL_FRONT: Vector3 = Vector3(0.0, 0.0, 1.0)

    val MODEL_REAR: Vector3 = Vector3(0.0, 0.0, -1.0)

    // ===== END GENERATED BUILTIN STATICS: Vector3 =====

    internal fun raw(x: Float, y: Float, z: Float): Vector3 = Vector3(x, y, z, RawStorage)

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

  /** GDScript's `Color(1, 1, 1, 0.72)`: integer channels with a decimal alpha. */
  constructor(r: Int, g: Int, b: Int, a: Double) : this(r.toDouble(), g.toDouble(), b.toDouble(), a)

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

  // ===== BEGIN GENERATED BUILTIN MEMBERS: Color (generate_builtin_ops.py) =====
  operator fun unaryMinus(): Color = raw(1.0f - rawR, 1.0f - rawG, 1.0f - rawB, 1.0f - rawA)

  operator fun unaryPlus(): Color = this

  operator fun times(scalar: Int): Color = times(scalar.toDouble())

  operator fun times(scalar: Long): Color = times(scalar.toDouble())

  operator fun div(scalar: Int): Color = div(scalar.toDouble())

  operator fun div(scalar: Long): Color = div(scalar.toDouble())

  operator fun times(scalar: Double): Color {
    val s = scalar.toFloat()
    return raw(rawR * s, rawG * s, rawB * s, rawA * s)
  }

  operator fun div(scalar: Double): Color {
    val s = scalar.toFloat()
    return raw(rawR / s, rawG / s, rawB / s, rawA / s)
  }

  operator fun plus(other: Color): Color =
    raw(rawR + other.rawR, rawG + other.rawG, rawB + other.rawB, rawA + other.rawA)

  operator fun minus(other: Color): Color =
    raw(rawR - other.rawR, rawG - other.rawG, rawB - other.rawB, rawA - other.rawA)

  operator fun times(other: Color): Color =
    raw(rawR * other.rawR, rawG * other.rawG, rawB * other.rawB, rawA * other.rawA)

  operator fun div(other: Color): Color =
    raw(rawR / other.rawR, rawG / other.rawG, rawB / other.rawB, rawA / other.rawA)

  fun toHtml(withAlpha: Boolean = true): String {
    val out = StringBuilder(8)
    godotHexByte(rawR, out)
    godotHexByte(rawG, out)
    godotHexByte(rawB, out)
    if (withAlpha) godotHexByte(rawA, out)
    return out.toString()
  }

  fun clamp(min: Color = Color(0.0, 0.0, 0.0, 0.0), max: Color = Color(1.0, 1.0, 1.0, 1.0)): Color =
    raw(
      godotClamp(rawR, min.rawR, max.rawR),
      godotClamp(rawG, min.rawG, max.rawG),
      godotClamp(rawB, min.rawB, max.rawB),
      godotClamp(rawA, min.rawA, max.rawA),
    )

  fun inverted(): Color = raw(1.0f - rawR, 1.0f - rawG, 1.0f - rawB, rawA)

  fun lerp(to: Color, weight: Double): Color {
    val w = weight.toFloat()
    return raw(
      realLerpF(rawR, to.rawR, w),
      realLerpF(rawG, to.rawG, w),
      realLerpF(rawB, to.rawB, w),
      realLerpF(rawA, to.rawA, w),
    )
  }

  fun getLuminance(): Double = (0.2126f * rawR + 0.7152f * rawG + 0.0722f * rawB).toDouble()

  // ===== END GENERATED BUILTIN MEMBERS: Color =====

  companion object {
    // ===== BEGIN GENERATED BUILTIN STATICS: Color (generate_builtin_ops.py) =====
    val ALICE_BLUE: Color = raw(0.9411765f, 0.972549f, 1f, 1f)

    val ANTIQUE_WHITE: Color = raw(0.98039216f, 0.92156863f, 0.84313726f, 1f)

    val AQUA: Color = raw(0f, 1f, 1f, 1f)

    val AQUAMARINE: Color = raw(0.49803922f, 1f, 0.83137256f, 1f)

    val AZURE: Color = raw(0.9411765f, 1f, 1f, 1f)

    val BEIGE: Color = raw(0.9607843f, 0.9607843f, 0.8627451f, 1f)

    val BISQUE: Color = raw(1f, 0.89411765f, 0.76862746f, 1f)

    val BLACK: Color = raw(0f, 0f, 0f, 1f)

    val BLANCHED_ALMOND: Color = raw(1f, 0.92156863f, 0.8039216f, 1f)

    val BLUE: Color = raw(0f, 0f, 1f, 1f)

    val BLUE_VIOLET: Color = raw(0.5411765f, 0.16862746f, 0.8862745f, 1f)

    val BROWN: Color = raw(0.64705884f, 0.16470589f, 0.16470589f, 1f)

    val BURLYWOOD: Color = raw(0.87058824f, 0.72156864f, 0.5294118f, 1f)

    val CADET_BLUE: Color = raw(0.37254903f, 0.61960787f, 0.627451f, 1f)

    val CHARTREUSE: Color = raw(0.49803922f, 1f, 0f, 1f)

    val CHOCOLATE: Color = raw(0.8235294f, 0.4117647f, 0.11764706f, 1f)

    val CORAL: Color = raw(1f, 0.49803922f, 0.3137255f, 1f)

    val CORNFLOWER_BLUE: Color = raw(0.39215687f, 0.58431375f, 0.92941177f, 1f)

    val CORNSILK: Color = raw(1f, 0.972549f, 0.8627451f, 1f)

    val CRIMSON: Color = raw(0.8627451f, 0.078431375f, 0.23529412f, 1f)

    val CYAN: Color = raw(0f, 1f, 1f, 1f)

    val DARK_BLUE: Color = raw(0f, 0f, 0.54509807f, 1f)

    val DARK_CYAN: Color = raw(0f, 0.54509807f, 0.54509807f, 1f)

    val DARK_GOLDENROD: Color = raw(0.72156864f, 0.5254902f, 0.043137256f, 1f)

    val DARK_GRAY: Color = raw(0.6627451f, 0.6627451f, 0.6627451f, 1f)

    val DARK_GREEN: Color = raw(0f, 0.39215687f, 0f, 1f)

    val DARK_KHAKI: Color = raw(0.7411765f, 0.7176471f, 0.41960785f, 1f)

    val DARK_MAGENTA: Color = raw(0.54509807f, 0f, 0.54509807f, 1f)

    val DARK_OLIVE_GREEN: Color = raw(0.33333334f, 0.41960785f, 0.18431373f, 1f)

    val DARK_ORANGE: Color = raw(1f, 0.54901963f, 0f, 1f)

    val DARK_ORCHID: Color = raw(0.6f, 0.19607843f, 0.8f, 1f)

    val DARK_RED: Color = raw(0.54509807f, 0f, 0f, 1f)

    val DARK_SALMON: Color = raw(0.9137255f, 0.5882353f, 0.47843137f, 1f)

    val DARK_SEA_GREEN: Color = raw(0.56078434f, 0.7372549f, 0.56078434f, 1f)

    val DARK_SLATE_BLUE: Color = raw(0.28235295f, 0.23921569f, 0.54509807f, 1f)

    val DARK_SLATE_GRAY: Color = raw(0.18431373f, 0.30980393f, 0.30980393f, 1f)

    val DARK_TURQUOISE: Color = raw(0f, 0.80784315f, 0.81960785f, 1f)

    val DARK_VIOLET: Color = raw(0.5803922f, 0f, 0.827451f, 1f)

    val DEEP_PINK: Color = raw(1f, 0.078431375f, 0.5764706f, 1f)

    val DEEP_SKY_BLUE: Color = raw(0f, 0.7490196f, 1f, 1f)

    val DIM_GRAY: Color = raw(0.4117647f, 0.4117647f, 0.4117647f, 1f)

    val DODGER_BLUE: Color = raw(0.11764706f, 0.5647059f, 1f, 1f)

    val FIREBRICK: Color = raw(0.69803923f, 0.13333334f, 0.13333334f, 1f)

    val FLORAL_WHITE: Color = raw(1f, 0.98039216f, 0.9411765f, 1f)

    val FOREST_GREEN: Color = raw(0.13333334f, 0.54509807f, 0.13333334f, 1f)

    val FUCHSIA: Color = raw(1f, 0f, 1f, 1f)

    val GAINSBORO: Color = raw(0.8627451f, 0.8627451f, 0.8627451f, 1f)

    val GHOST_WHITE: Color = raw(0.972549f, 0.972549f, 1f, 1f)

    val GOLD: Color = raw(1f, 0.84313726f, 0f, 1f)

    val GOLDENROD: Color = raw(0.85490197f, 0.64705884f, 0.1254902f, 1f)

    val GRAY: Color = raw(0.74509805f, 0.74509805f, 0.74509805f, 1f)

    val GREEN: Color = raw(0f, 1f, 0f, 1f)

    val GREEN_YELLOW: Color = raw(0.6784314f, 1f, 0.18431373f, 1f)

    val HONEYDEW: Color = raw(0.9411765f, 1f, 0.9411765f, 1f)

    val HOT_PINK: Color = raw(1f, 0.4117647f, 0.7058824f, 1f)

    val INDIAN_RED: Color = raw(0.8039216f, 0.36078432f, 0.36078432f, 1f)

    val INDIGO: Color = raw(0.29411766f, 0f, 0.50980395f, 1f)

    val IVORY: Color = raw(1f, 1f, 0.9411765f, 1f)

    val KHAKI: Color = raw(0.9411765f, 0.9019608f, 0.54901963f, 1f)

    val LAVENDER: Color = raw(0.9019608f, 0.9019608f, 0.98039216f, 1f)

    val LAVENDER_BLUSH: Color = raw(1f, 0.9411765f, 0.9607843f, 1f)

    val LAWN_GREEN: Color = raw(0.4862745f, 0.9882353f, 0f, 1f)

    val LEMON_CHIFFON: Color = raw(1f, 0.98039216f, 0.8039216f, 1f)

    val LIGHT_BLUE: Color = raw(0.6784314f, 0.84705883f, 0.9019608f, 1f)

    val LIGHT_CORAL: Color = raw(0.9411765f, 0.5019608f, 0.5019608f, 1f)

    val LIGHT_CYAN: Color = raw(0.8784314f, 1f, 1f, 1f)

    val LIGHT_GOLDENROD: Color = raw(0.98039216f, 0.98039216f, 0.8235294f, 1f)

    val LIGHT_GRAY: Color = raw(0.827451f, 0.827451f, 0.827451f, 1f)

    val LIGHT_GREEN: Color = raw(0.5647059f, 0.93333334f, 0.5647059f, 1f)

    val LIGHT_PINK: Color = raw(1f, 0.7137255f, 0.75686276f, 1f)

    val LIGHT_SALMON: Color = raw(1f, 0.627451f, 0.47843137f, 1f)

    val LIGHT_SEA_GREEN: Color = raw(0.1254902f, 0.69803923f, 0.6666667f, 1f)

    val LIGHT_SKY_BLUE: Color = raw(0.5294118f, 0.80784315f, 0.98039216f, 1f)

    val LIGHT_SLATE_GRAY: Color = raw(0.46666667f, 0.53333336f, 0.6f, 1f)

    val LIGHT_STEEL_BLUE: Color = raw(0.6901961f, 0.76862746f, 0.87058824f, 1f)

    val LIGHT_YELLOW: Color = raw(1f, 1f, 0.8784314f, 1f)

    val LIME: Color = raw(0f, 1f, 0f, 1f)

    val LIME_GREEN: Color = raw(0.19607843f, 0.8039216f, 0.19607843f, 1f)

    val LINEN: Color = raw(0.98039216f, 0.9411765f, 0.9019608f, 1f)

    val MAGENTA: Color = raw(1f, 0f, 1f, 1f)

    val MAROON: Color = raw(0.6901961f, 0.1882353f, 0.3764706f, 1f)

    val MEDIUM_AQUAMARINE: Color = raw(0.4f, 0.8039216f, 0.6666667f, 1f)

    val MEDIUM_BLUE: Color = raw(0f, 0f, 0.8039216f, 1f)

    val MEDIUM_ORCHID: Color = raw(0.7294118f, 0.33333334f, 0.827451f, 1f)

    val MEDIUM_PURPLE: Color = raw(0.5764706f, 0.4392157f, 0.85882354f, 1f)

    val MEDIUM_SEA_GREEN: Color = raw(0.23529412f, 0.7019608f, 0.44313726f, 1f)

    val MEDIUM_SLATE_BLUE: Color = raw(0.48235294f, 0.40784314f, 0.93333334f, 1f)

    val MEDIUM_SPRING_GREEN: Color = raw(0f, 0.98039216f, 0.6039216f, 1f)

    val MEDIUM_TURQUOISE: Color = raw(0.28235295f, 0.81960785f, 0.8f, 1f)

    val MEDIUM_VIOLET_RED: Color = raw(0.78039217f, 0.08235294f, 0.52156866f, 1f)

    val MIDNIGHT_BLUE: Color = raw(0.09803922f, 0.09803922f, 0.4392157f, 1f)

    val MINT_CREAM: Color = raw(0.9607843f, 1f, 0.98039216f, 1f)

    val MISTY_ROSE: Color = raw(1f, 0.89411765f, 0.88235295f, 1f)

    val MOCCASIN: Color = raw(1f, 0.89411765f, 0.70980394f, 1f)

    val NAVAJO_WHITE: Color = raw(1f, 0.87058824f, 0.6784314f, 1f)

    val NAVY_BLUE: Color = raw(0f, 0f, 0.5019608f, 1f)

    val OLD_LACE: Color = raw(0.99215686f, 0.9607843f, 0.9019608f, 1f)

    val OLIVE: Color = raw(0.5019608f, 0.5019608f, 0f, 1f)

    val OLIVE_DRAB: Color = raw(0.41960785f, 0.5568628f, 0.13725491f, 1f)

    val ORANGE: Color = raw(1f, 0.64705884f, 0f, 1f)

    val ORANGE_RED: Color = raw(1f, 0.27058825f, 0f, 1f)

    val ORCHID: Color = raw(0.85490197f, 0.4392157f, 0.8392157f, 1f)

    val PALE_GOLDENROD: Color = raw(0.93333334f, 0.9098039f, 0.6666667f, 1f)

    val PALE_GREEN: Color = raw(0.59607846f, 0.9843137f, 0.59607846f, 1f)

    val PALE_TURQUOISE: Color = raw(0.6862745f, 0.93333334f, 0.93333334f, 1f)

    val PALE_VIOLET_RED: Color = raw(0.85882354f, 0.4392157f, 0.5764706f, 1f)

    val PAPAYA_WHIP: Color = raw(1f, 0.9372549f, 0.8352941f, 1f)

    val PEACH_PUFF: Color = raw(1f, 0.85490197f, 0.7254902f, 1f)

    val PERU: Color = raw(0.8039216f, 0.52156866f, 0.24705882f, 1f)

    val PINK: Color = raw(1f, 0.7529412f, 0.79607844f, 1f)

    val PLUM: Color = raw(0.8666667f, 0.627451f, 0.8666667f, 1f)

    val POWDER_BLUE: Color = raw(0.6901961f, 0.8784314f, 0.9019608f, 1f)

    val PURPLE: Color = raw(0.627451f, 0.1254902f, 0.9411765f, 1f)

    val REBECCA_PURPLE: Color = raw(0.4f, 0.2f, 0.6f, 1f)

    val RED: Color = raw(1f, 0f, 0f, 1f)

    val ROSY_BROWN: Color = raw(0.7372549f, 0.56078434f, 0.56078434f, 1f)

    val ROYAL_BLUE: Color = raw(0.25490198f, 0.4117647f, 0.88235295f, 1f)

    val SADDLE_BROWN: Color = raw(0.54509807f, 0.27058825f, 0.07450981f, 1f)

    val SALMON: Color = raw(0.98039216f, 0.5019608f, 0.44705883f, 1f)

    val SANDY_BROWN: Color = raw(0.95686275f, 0.6431373f, 0.3764706f, 1f)

    val SEA_GREEN: Color = raw(0.18039216f, 0.54509807f, 0.34117648f, 1f)

    val SEASHELL: Color = raw(1f, 0.9607843f, 0.93333334f, 1f)

    val SIENNA: Color = raw(0.627451f, 0.32156864f, 0.1764706f, 1f)

    val SILVER: Color = raw(0.7529412f, 0.7529412f, 0.7529412f, 1f)

    val SKY_BLUE: Color = raw(0.5294118f, 0.80784315f, 0.92156863f, 1f)

    val SLATE_BLUE: Color = raw(0.41568628f, 0.3529412f, 0.8039216f, 1f)

    val SLATE_GRAY: Color = raw(0.4392157f, 0.5019608f, 0.5647059f, 1f)

    val SNOW: Color = raw(1f, 0.98039216f, 0.98039216f, 1f)

    val SPRING_GREEN: Color = raw(0f, 1f, 0.49803922f, 1f)

    val STEEL_BLUE: Color = raw(0.27450982f, 0.50980395f, 0.7058824f, 1f)

    val TAN: Color = raw(0.8235294f, 0.7058824f, 0.54901963f, 1f)

    val TEAL: Color = raw(0f, 0.5019608f, 0.5019608f, 1f)

    val THISTLE: Color = raw(0.84705883f, 0.7490196f, 0.84705883f, 1f)

    val TOMATO: Color = raw(1f, 0.3882353f, 0.2784314f, 1f)

    val TRANSPARENT: Color = raw(1f, 1f, 1f, 0f)

    val TURQUOISE: Color = raw(0.2509804f, 0.8784314f, 0.8156863f, 1f)

    val VIOLET: Color = raw(0.93333334f, 0.50980395f, 0.93333334f, 1f)

    val WEB_GRAY: Color = raw(0.5019608f, 0.5019608f, 0.5019608f, 1f)

    val WEB_GREEN: Color = raw(0f, 0.5019608f, 0f, 1f)

    val WEB_MAROON: Color = raw(0.5019608f, 0f, 0f, 1f)

    val WEB_PURPLE: Color = raw(0.5019608f, 0f, 0.5019608f, 1f)

    val WHEAT: Color = raw(0.9607843f, 0.87058824f, 0.7019608f, 1f)

    val WHITE: Color = raw(1f, 1f, 1f, 1f)

    val WHITE_SMOKE: Color = raw(0.9607843f, 0.9607843f, 0.9607843f, 1f)

    val YELLOW: Color = raw(1f, 1f, 0f, 1f)

    val YELLOW_GREEN: Color = raw(0.6039216f, 0.8039216f, 0.19607843f, 1f)

    // ===== END GENERATED BUILTIN STATICS: Color =====

    /** A color from float32 channels (no conversion). */
    internal fun raw(r: Float, g: Float, b: Float, a: Float): Color = Color(r, g, b, a, RawStorage)
  }
}

data class Vector3i(val x: Int, val y: Int, val z: Int) {
  // ===== BEGIN GENERATED BUILTIN MEMBERS: Vector3i (generate_builtin_ops.py) =====
  /**
   * Godot's `Vector3i.Axis` enum as a typed value: `.value` is the raw number Godot uses, and the
   * companion holds the named values (`Vector3i.Axis.<NAME>`).
   *
   * Generated from Godot docs: Vector3i.Axis
   */
  @kotlin.jvm.JvmInline
  value class Axis(override val value: Long) : net.multigesture.kanama.api.GodotEnumValue {
    companion object {
      /**
       * Enumerated value for the X axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector3i.AXIS_X
       */
      val X: Axis
        get() = Axis(0L)

      /**
       * Enumerated value for the Y axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector3i.AXIS_Y
       */
      val Y: Axis
        get() = Axis(1L)

      /**
       * Enumerated value for the Z axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector3i.AXIS_Z
       */
      val Z: Axis
        get() = Axis(2L)
    }
  }

  operator fun unaryMinus(): Vector3i = Vector3i(-x, -y, -z)

  operator fun unaryPlus(): Vector3i = this

  operator fun times(scalar: Int): Vector3i = Vector3i(x * scalar, y * scalar, z * scalar)

  operator fun times(scalar: Long): Vector3i = times(scalar.toInt())

  operator fun div(scalar: Int): Vector3i = Vector3i(x / scalar, y / scalar, z / scalar)

  operator fun div(scalar: Long): Vector3i = div(scalar.toInt())

  operator fun rem(scalar: Int): Vector3i = Vector3i(x % scalar, y % scalar, z % scalar)

  operator fun rem(scalar: Long): Vector3i = rem(scalar.toInt())

  operator fun times(scalar: Double): Vector3 {
    val s = narrowReal(scalar)
    return Vector3.raw(
      narrowReal(x.toDouble()) * s,
      narrowReal(y.toDouble()) * s,
      narrowReal(z.toDouble()) * s,
    )
  }

  operator fun div(scalar: Double): Vector3 {
    val s = narrowReal(scalar)
    return Vector3.raw(
      narrowReal(x.toDouble()) / s,
      narrowReal(y.toDouble()) / s,
      narrowReal(z.toDouble()) / s,
    )
  }

  operator fun compareTo(other: Vector3i): Int {
    val c0 = godotCompareStep(x, other.x)
    if (c0 != 0) return c0
    val c1 = godotCompareStep(y, other.y)
    if (c1 != 0) return c1
    return godotCompareStep(z, other.z)
  }

  operator fun plus(other: Vector3i): Vector3i = Vector3i(x + other.x, y + other.y, z + other.z)

  operator fun minus(other: Vector3i): Vector3i = Vector3i(x - other.x, y - other.y, z - other.z)

  operator fun times(other: Vector3i): Vector3i = Vector3i(x * other.x, y * other.y, z * other.z)

  operator fun div(other: Vector3i): Vector3i = Vector3i(x / other.x, y / other.y, z / other.z)

  operator fun rem(other: Vector3i): Vector3i = Vector3i(x % other.x, y % other.y, z % other.z)

  fun minAxisIndex(): Long = if (x < y) (if (x < z) 0L else 2L) else (if (y < z) 1L else 2L)

  fun maxAxisIndex(): Long = if (x < y) (if (y < z) 2L else 1L) else (if (x < z) 2L else 0L)

  fun distanceTo(to: Vector3i): Double = (to - this).length()

  fun distanceSquaredTo(to: Vector3i): Long = (to - this).lengthSquared()

  fun length(): Double = godotSqrt(lengthSquared().toDouble())

  fun lengthSquared(): Long = x.toLong() * x + y.toLong() * y + z.toLong() * z

  fun sign(): Vector3i = Vector3i(godotSign(x), godotSign(y), godotSign(z))

  fun abs(): Vector3i = Vector3i(godotAbs(x), godotAbs(y), godotAbs(z))

  fun clamp(min: Vector3i, max: Vector3i): Vector3i =
    Vector3i(godotClamp(x, min.x, max.x), godotClamp(y, min.y, max.y), godotClamp(z, min.z, max.z))

  fun clampi(min: Long, max: Long): Vector3i {
    val lo = min.toInt()
    val hi = max.toInt()
    return Vector3i(godotClamp(x, lo, hi), godotClamp(y, lo, hi), godotClamp(z, lo, hi))
  }

  fun min(with: Vector3i): Vector3i =
    Vector3i(godotMin(x, with.x), godotMin(y, with.y), godotMin(z, with.z))

  fun mini(with: Long): Vector3i {
    val s = with.toInt()
    return Vector3i(godotMin(x, s), godotMin(y, s), godotMin(z, s))
  }

  fun max(with: Vector3i): Vector3i =
    Vector3i(godotMax(x, with.x), godotMax(y, with.y), godotMax(z, with.z))

  fun maxi(with: Long): Vector3i {
    val s = with.toInt()
    return Vector3i(godotMax(x, s), godotMax(y, s), godotMax(z, s))
  }

  // ===== END GENERATED BUILTIN MEMBERS: Vector3i =====

  companion object {
    // ===== BEGIN GENERATED BUILTIN STATICS: Vector3i (generate_builtin_ops.py) =====
    val ONE: Vector3i = Vector3i(1, 1, 1)

    val MIN: Vector3i = Vector3i(Int.MIN_VALUE, Int.MIN_VALUE, Int.MIN_VALUE)

    val MAX: Vector3i = Vector3i(Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE)

    val LEFT: Vector3i = Vector3i(-1, 0, 0)

    val RIGHT: Vector3i = Vector3i(1, 0, 0)

    val UP: Vector3i = Vector3i(0, 1, 0)

    val DOWN: Vector3i = Vector3i(0, -1, 0)

    val FORWARD: Vector3i = Vector3i(0, 0, -1)

    val BACK: Vector3i = Vector3i(0, 0, 1)

    // ===== END GENERATED BUILTIN STATICS: Vector3i =====

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
  override fun toString(): String = "[N: $normal, D: ${godotRealString(d, false)}]"

  /**
   * Godot's intersects_ray: the intersection of the ray [from] + t * [dir] with this plane, or null
   * when the ray is parallel to or points away from it.
   */
  // Godot's `Plane::intersects_ray` exactly as the native Plane computes it (CMP_EPSILON
  // tolerances, the distance in real_t); task 134 B's Web parity test caught the old 1e-8 / Double
  // version answering differently from Godot.
  fun intersectsRay(from: Vector3, dir: Vector3): Vector3? {
    val denominator = normal.dot(dir)
    if (kotlin.math.abs(denominator) <= 0.00001) return null
    val signedDistance =
      (realDot(normal.rawX, normal.rawY, normal.rawZ, from.rawX, from.rawY, from.rawZ) - rawD) /
        narrowReal(denominator)
    if (widenReal(signedDistance) > 0.00001) return null
    return from + dir * widenReal(-signedDistance)
  }

  // ===== BEGIN GENERATED BUILTIN MEMBERS: Plane (generate_builtin_ops.py) =====
  operator fun unaryMinus(): Plane = raw(-normal, -rawD)

  operator fun unaryPlus(): Plane = this

  fun getCenter(): Vector3 = Vector3.raw(normal.rawX * rawD, normal.rawY * rawD, normal.rawZ * rawD)

  fun isPointOver(point: Vector3): Boolean =
    normal.rawX * point.rawX + normal.rawY * point.rawY + normal.rawZ * point.rawZ > rawD

  fun hasPoint(point: Vector3, tolerance: Double = 1e-05): Boolean =
    godotFabs(
      normal.rawX * point.rawX + normal.rawY * point.rawY + normal.rawZ * point.rawZ - rawD
    ) <= narrowReal(tolerance)

  fun project(point: Vector3): Vector3 {
    val d = normal.rawX * point.rawX + normal.rawY * point.rawY + normal.rawZ * point.rawZ - rawD
    return Vector3.raw(
      point.rawX - normal.rawX * d,
      point.rawY - normal.rawY * d,
      point.rawZ - normal.rawZ * d,
    )
  }

  // ===== END GENERATED BUILTIN MEMBERS: Plane =====

  companion object {
    // ===== BEGIN GENERATED BUILTIN STATICS: Plane (generate_builtin_ops.py) =====
    val PLANE_YZ: Plane = Plane(Vector3(1.0, 0.0, 0.0), 0.0)

    val PLANE_XZ: Plane = Plane(Vector3(0.0, 1.0, 0.0), 0.0)

    val PLANE_XY: Plane = Plane(Vector3(0.0, 0.0, 1.0), 0.0)

    // ===== END GENERATED BUILTIN STATICS: Plane =====

    /** A plane from a distance already at the storage width (no conversion). */
    internal fun raw(normal: Vector3, d: Float): Plane = Plane(normal, d, RawStorage)
  }
}

data class Vector2i(val x: Int, val y: Int) {
  // ===== BEGIN GENERATED BUILTIN MEMBERS: Vector2i (generate_builtin_ops.py) =====
  /**
   * Godot's `Vector2i.Axis` enum as a typed value: `.value` is the raw number Godot uses, and the
   * companion holds the named values (`Vector2i.Axis.<NAME>`).
   *
   * Generated from Godot docs: Vector2i.Axis
   */
  @kotlin.jvm.JvmInline
  value class Axis(override val value: Long) : net.multigesture.kanama.api.GodotEnumValue {
    companion object {
      /**
       * Enumerated value for the X axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector2i.AXIS_X
       */
      val X: Axis
        get() = Axis(0L)

      /**
       * Enumerated value for the Y axis. Returned by `max_axis_index` and `min_axis_index`.
       *
       * Generated from Godot docs: Vector2i.AXIS_Y
       */
      val Y: Axis
        get() = Axis(1L)
    }
  }

  operator fun unaryMinus(): Vector2i = Vector2i(-x, -y)

  operator fun unaryPlus(): Vector2i = this

  operator fun times(scalar: Int): Vector2i = Vector2i(x * scalar, y * scalar)

  operator fun times(scalar: Long): Vector2i = times(scalar.toInt())

  operator fun div(scalar: Int): Vector2i = Vector2i(x / scalar, y / scalar)

  operator fun div(scalar: Long): Vector2i = div(scalar.toInt())

  operator fun rem(scalar: Int): Vector2i = Vector2i(x % scalar, y % scalar)

  operator fun rem(scalar: Long): Vector2i = rem(scalar.toInt())

  operator fun times(scalar: Double): Vector2 {
    val s = narrowReal(scalar)
    return Vector2.raw(narrowReal(x.toDouble()) * s, narrowReal(y.toDouble()) * s)
  }

  operator fun div(scalar: Double): Vector2 {
    val s = narrowReal(scalar)
    return Vector2.raw(narrowReal(x.toDouble()) / s, narrowReal(y.toDouble()) / s)
  }

  operator fun compareTo(other: Vector2i): Int {
    val c0 = godotCompareStep(x, other.x)
    if (c0 != 0) return c0
    return godotCompareStep(y, other.y)
  }

  operator fun plus(other: Vector2i): Vector2i = Vector2i(x + other.x, y + other.y)

  operator fun minus(other: Vector2i): Vector2i = Vector2i(x - other.x, y - other.y)

  operator fun times(other: Vector2i): Vector2i = Vector2i(x * other.x, y * other.y)

  operator fun div(other: Vector2i): Vector2i = Vector2i(x / other.x, y / other.y)

  operator fun rem(other: Vector2i): Vector2i = Vector2i(x % other.x, y % other.y)

  fun aspect(): Double = widenReal(narrowReal(x.toDouble()) / narrowReal(y.toDouble()))

  fun maxAxisIndex(): Long = if (x < y) 1L else 0L

  fun minAxisIndex(): Long = if (x < y) 0L else 1L

  fun distanceTo(to: Vector2i): Double = (to - this).length()

  fun distanceSquaredTo(to: Vector2i): Long = (to - this).lengthSquared()

  fun length(): Double = godotSqrt(lengthSquared().toDouble())

  fun lengthSquared(): Long = x.toLong() * x + y.toLong() * y

  fun sign(): Vector2i = Vector2i(godotSign(x), godotSign(y))

  fun abs(): Vector2i = Vector2i(godotAbs(x), godotAbs(y))

  fun clamp(min: Vector2i, max: Vector2i): Vector2i =
    Vector2i(godotClamp(x, min.x, max.x), godotClamp(y, min.y, max.y))

  fun clampi(min: Long, max: Long): Vector2i {
    val lo = min.toInt()
    val hi = max.toInt()
    return Vector2i(godotClamp(x, lo, hi), godotClamp(y, lo, hi))
  }

  fun min(with: Vector2i): Vector2i = Vector2i(godotMin(x, with.x), godotMin(y, with.y))

  fun mini(with: Long): Vector2i {
    val s = with.toInt()
    return Vector2i(godotMin(x, s), godotMin(y, s))
  }

  fun max(with: Vector2i): Vector2i = Vector2i(godotMax(x, with.x), godotMax(y, with.y))

  fun maxi(with: Long): Vector2i {
    val s = with.toInt()
    return Vector2i(godotMax(x, s), godotMax(y, s))
  }

  // ===== END GENERATED BUILTIN MEMBERS: Vector2i =====

  companion object {
    // ===== BEGIN GENERATED BUILTIN STATICS: Vector2i (generate_builtin_ops.py) =====
    val ONE: Vector2i = Vector2i(1, 1)

    val MIN: Vector2i = Vector2i(Int.MIN_VALUE, Int.MIN_VALUE)

    val MAX: Vector2i = Vector2i(Int.MAX_VALUE, Int.MAX_VALUE)

    val LEFT: Vector2i = Vector2i(-1, 0)

    val RIGHT: Vector2i = Vector2i(1, 0)

    val UP: Vector2i = Vector2i(0, -1)

    val DOWN: Vector2i = Vector2i(0, 1)

    // ===== END GENERATED BUILTIN STATICS: Vector2i =====

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
    "(${godotRealString(x, false)}, ${godotRealString(y, false)}, " +
      "${godotRealString(z, false)}, ${godotRealString(w, false)})"

  // Godot: `*this / length()`, which multiplies by `1 / length` in `real_t`.
  fun normalized(): Quaternion {
    // Godot's `*this / length()`: a zero quaternion gives NaN components, as natively.
    val len = sqrt(realDot(rawX, rawY, rawZ, rawW, rawX, rawY, rawZ, rawW))
    val inverse = 1.0f / len
    return raw(rawX * inverse, rawY * inverse, rawZ * inverse, rawW * inverse)
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

  // ===== BEGIN GENERATED BUILTIN MEMBERS: Quaternion (generate_builtin_ops.py) =====
  operator fun unaryPlus(): Quaternion = this

  operator fun times(scalar: Int): Quaternion = times(scalar.toDouble())

  operator fun times(scalar: Long): Quaternion = times(scalar.toDouble())

  operator fun div(scalar: Int): Quaternion = div(scalar.toDouble())

  operator fun div(scalar: Long): Quaternion = div(scalar.toDouble())

  operator fun times(scalar: Double): Quaternion {
    val s = narrowReal(scalar)
    return raw(rawX * s, rawY * s, rawZ * s, rawW * s)
  }

  operator fun div(scalar: Double): Quaternion {
    val s = narrowReal(1.0) / narrowReal(scalar)
    return raw(rawX * s, rawY * s, rawZ * s, rawW * s)
  }

  operator fun plus(other: Quaternion): Quaternion =
    raw(rawX + other.rawX, rawY + other.rawY, rawZ + other.rawZ, rawW + other.rawW)

  operator fun minus(other: Quaternion): Quaternion =
    raw(rawX - other.rawX, rawY - other.rawY, rawZ - other.rawZ, rawW - other.rawW)

  fun isFinite(): Boolean = rawX.isFinite() && rawY.isFinite() && rawZ.isFinite() && rawW.isFinite()

  // ===== END GENERATED BUILTIN MEMBERS: Quaternion =====

  companion object {
    internal fun raw(x: Float, y: Float, z: Float, w: Float): Quaternion =
      Quaternion(x, y, z, w, RawStorage)

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

  /** Godot's `Basis(Quaternion)` (`set_quaternion`), in `real_t`. */
  constructor(
    quaternion: Quaternion
  ) : this(
    realBasisFromQuaternion(quaternion.rawX, quaternion.rawY, quaternion.rawZ, quaternion.rawW) {
      r00,
      r01,
      r02,
      r10,
      r11,
      r12,
      r20,
      r21,
      r22 ->
      floatArrayOf(r00, r01, r02, r10, r11, r12, r20, r21, r22)
    }
  )

  /** Godot's `Basis.xform`: each row dotted with [v], in `real_t`. */
  operator fun times(v: Vector3): Vector3 =
    Vector3.raw(
      realDot(stored[0], stored[1], stored[2], v.rawX, v.rawY, v.rawZ),
      realDot(stored[3], stored[4], stored[5], v.rawX, v.rawY, v.rawZ),
      realDot(stored[6], stored[7], stored[8], v.rawX, v.rawY, v.rawZ),
    )

  /** Row-major matrix product (this applied after [other] in xform order). */
  /**
   * Row-major matrix product (this applied after [other] in xform order), Godot's real_t sum order.
   */
  operator fun times(other: Basis): Basis {
    val a = stored
    val b = other.stored
    return Basis(
      FloatArray(9) {
        val row = it / 3
        val col = it % 3
        // Godot: other.tdot{x,y,z}(rows[row]) = b[0][col] * a[row][0] + b[1][col] * a[row][1] + ...
        b[col] * a[row * 3] + b[3 + col] * a[row * 3 + 1] + b[6 + col] * a[row * 3 + 2]
      }
    )
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

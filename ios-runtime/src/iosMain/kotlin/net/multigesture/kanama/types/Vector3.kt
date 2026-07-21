package net.multigesture.kanama.types

import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import net.multigesture.kanama.binding.runtime.BuiltinCalls

data class Vector3(val x: real_t, val y: real_t, val z: real_t) {
  constructor(
    x: Number,
    y: Number,
    z: Number,
  ) : this(GodotReal.fromNumber(x), GodotReal.fromNumber(y), GodotReal.fromNumber(z))

  // Match GDScript/C# `==`: signed zero equal (-0.0 == 0.0), NaN reflexive. See
  // wrapper-coverage-roadmap.md. hashCode canonicalizes signed zero so equal vectors hash equal.
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is Vector3) return false
    return (x == other.x || (x.isNaN() && other.x.isNaN())) &&
      (y == other.y || (y.isNaN() && other.y.isNaN())) &&
      (z == other.z || (z.isNaN() && other.z.isNaN()))
  }

  override fun hashCode(): Int {
    var result = (x + 0.0).hashCode()
    result = 31 * result + (y + 0.0).hashCode()
    result = 31 * result + (z + 0.0).hashCode()
    return result
  }

  /** Godot `Vector3.is_equal_approx`: per-component fuzzy compare (CMP_EPSILON tolerance). */
  fun isEqualApprox(other: Vector3): Boolean =
    isEqualApprox(x, other.x) && isEqualApprox(y, other.y) && isEqualApprox(z, other.z)

  /** Godot `Vector3.is_zero_approx`: true if every component is approximately zero. */
  fun isZeroApprox(): Boolean = isZeroApprox(x) && isZeroApprox(y) && isZeroApprox(z)

  operator fun plus(other: Vector3): Vector3 = Vector3(x + other.x, y + other.y, z + other.z)

  operator fun minus(other: Vector3): Vector3 = Vector3(x - other.x, y - other.y, z - other.z)

  operator fun times(scale: Number): Vector3 =
    Vector3(x * scale.toDouble(), y * scale.toDouble(), z * scale.toDouble())

  operator fun div(scale: Number): Vector3 =
    Vector3(x / scale.toDouble(), y / scale.toDouble(), z / scale.toDouble())

  operator fun unaryMinus(): Vector3 = Vector3(-x, -y, -z)

  fun withX(value: Number): Vector3 = copy(x = GodotReal.fromNumber(value))

  fun withY(value: Number): Vector3 = copy(y = GodotReal.fromNumber(value))

  fun withZ(value: Number): Vector3 = copy(z = GodotReal.fromNumber(value))

  fun lengthSquared(): Double = (x * x + y * y + z * z).toDouble()

  fun length(): Double = sqrt(lengthSquared())

  fun normalized(): Vector3 {
    val len = length()
    return if (len == 0.0) ZERO else this / len
  }

  fun lerp(to: Vector3, weight: Double): Vector3 = this + (to - this) * weight

  fun limitLength(maxLength: Double): Vector3 {
    val len = length()
    return if (len > maxLength && len > 0.0) normalized() * maxLength else this
  }

  /**
   * Rotates this vector about [axis] by [angle] radians, matching Godot's `Vector3.rotated`
   * (right-handed, same handedness as the engine). General axis-angle (Rodrigues) form so any axis
   * is correct — the previous iOS impl only handled UP and rotated by the WRONG sign (off-diagonal
   * terms negated), which mirrored camera-relative movement on device.
   */
  fun rotated(axis: Vector3, angle: Double): Vector3 {
    val a = axis.normalized()
    val c = cos(angle)
    val s = sin(angle)
    val dot = x.toDouble() * a.x + y.toDouble() * a.y + z.toDouble() * a.z
    val cx = a.y.toDouble() * z - a.z.toDouble() * y
    val cy = a.z.toDouble() * x - a.x.toDouble() * z
    val cz = a.x.toDouble() * y - a.y.toDouble() * x
    return Vector3(
      x.toDouble() * c + cx * s + a.x.toDouble() * dot * (1.0 - c),
      y.toDouble() * c + cy * s + a.y.toDouble() * dot * (1.0 - c),
      z.toDouble() * c + cz * s + a.z.toDouble() * dot * (1.0 - c),
    )
  }

  /**
   * Returns the cross product of this vector and [with], computed by Godot via the value-type
   * builtin call path (so the handedness/ordering matches the engine exactly).
   */
  fun cross(with: Vector3): Vector3 =
    fromGodotRealArray(
      BuiltinCalls.call(
        crossBind,
        toGodotRealArray(),
        3,
        listOf(BuiltinCalls.BArg.Floats(BuiltinCalls.PT_VECTOR3, with.toGodotRealArray())),
      )
    )

  /**
   * Returns the dot product of this vector and [with], computed by Godot via the value-type builtin
   * call path (scalar `real_t` return decoded to Double).
   */
  fun dot(with: Vector3): Double =
    BuiltinCalls.callScalar(
      dotBind,
      toGodotRealArray(),
      listOf(BuiltinCalls.BArg.Floats(BuiltinCalls.PT_VECTOR3, with.toGodotRealArray())),
    )

  /** Euclidean distance from this point to [to] (matches Godot Vector3.distance_to). */
  fun distanceTo(to: Vector3): Double = (to - this).length()

  /** Squared distance from this point to [to] (matches Godot Vector3.distance_squared_to). */
  fun distanceSquaredTo(to: Vector3): Double = (to - this).lengthSquared()

  /**
   * Returns this vector bounced off a plane with the given [normal] (matches Godot Vector3.bounce
   * == -reflect(normal); [normal] must be normalized).
   */
  fun bounce(normal: Vector3): Vector3 = this - normal * (2.0 * dot(normal))

  /**
   * Returns this vector moved toward [to] by at most [delta] (matches Godot Vector3.move_toward).
   */
  fun moveToward(to: Vector3, delta: Double): Vector3 {
    val diff = to - this
    val len = diff.length()
    return if (len <= delta || len < CMP_EPSILON) to else this + diff / len * delta
  }

  /**
   * Returns the signed angle (radians) to [to] about [axis] (matches Godot
   * Vector3.signed_angle_to): positive counter-clockwise when viewed from the [axis] side. Built
   * from cross/dot.
   */
  fun signedAngleTo(to: Vector3, axis: Vector3): Double {
    val crossTo = cross(to)
    val unsignedAngle = kotlin.math.atan2(crossTo.length(), dot(to))
    return if (crossTo.dot(axis) < 0.0) -unsignedAngle else unsignedAngle
  }

  /**
   * Returns whether this vector is normalized (length ≈ 1), per Godot's own check (bool return
   * decoded from the ptr-ABI's uint8).
   */
  fun isNormalized(): Boolean = BuiltinCalls.callBool(isNormalizedBind, toGodotRealArray())

  /**
   * Returns the index (0 = x, 1 = y, 2 = z) of this vector's largest component, computed by Godot
   * (int return decoded from the ptr-ABI's int64).
   */
  fun maxAxisIndex(): Int = BuiltinCalls.callInt(maxAxisIndexBind, toGodotRealArray()).toInt()

  private fun toGodotRealArray(): GodotRealArray =
    GodotRealArray(3).also {
      it[0] = GodotReal.toC(x)
      it[1] = GodotReal.toC(y)
      it[2] = GodotReal.toC(z)
    }

  companion object {
    val ZERO = Vector3(0.0, 0.0, 0.0)
    val ONE = Vector3(1.0, 1.0, 1.0)
    val UP = Vector3(0.0, 1.0, 0.0)
    val DOWN = Vector3(0.0, -1.0, 0.0)
    val LEFT = Vector3(-1.0, 0.0, 0.0)
    val RIGHT = Vector3(1.0, 0.0, 0.0)
    val FORWARD = Vector3(0.0, 0.0, -1.0)
    val BACK = Vector3(0.0, 0.0, 1.0)

    private val crossBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_VECTOR3, "cross", 2923479887L)
    }
    private val dotBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_VECTOR3, "dot", 1047977935L)
    }
    private val isNormalizedBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_VECTOR3, "is_normalized", 3918633141L)
    }
    private val maxAxisIndexBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_VECTOR3, "max_axis_index", 3173160232L)
    }

    private fun fromGodotRealArray(c: GodotRealArray): Vector3 =
      Vector3(GodotReal.fromC(c[0]), GodotReal.fromC(c[1]), GodotReal.fromC(c[2]))
  }
}

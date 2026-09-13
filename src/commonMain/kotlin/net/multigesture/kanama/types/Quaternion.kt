package net.multigesture.kanama.types

import kotlin.math.sqrt
import net.multigesture.kanama.binding.runtime.BuiltinCalls

private const val SLERP_HASH = 1773590316L
private const val INVERSE_HASH = 4274879941L
private const val FROM_EULER_HASH = 4053467903L

/**
 * A unit quaternion used for representing 3D rotations. Kanama value types are immutable snapshots;
 * assign a new value back to the Godot property after changing components.
 *
 * One body for every backend (task 104 step 2): `inverse`, `slerp` and `from_euler` are computed by
 * the engine through [net.multigesture.kanama.binding.runtime.BuiltinCalls] (their shortest-arc and
 * non-unit handling is the engine's), the rest is exact arithmetic in Kotlin. At ptrcall a
 * Quaternion is 4 `real_t` in x, y, z, w order.
 *
 * Generated from Godot docs: Quaternion
 */
data class Quaternion(
  /**
   * X component of the quaternion. This is the value along the "imaginary" `i` axis. Note:
   * Quaternion components should usually not be manipulated directly.
   *
   * Generated from Godot docs: Quaternion.x
   */
  val x: real_t,
  /**
   * Y component of the quaternion. This is the value along the "imaginary" `j` axis. Note:
   * Quaternion components should usually not be manipulated directly.
   *
   * Generated from Godot docs: Quaternion.y
   */
  val y: real_t,
  /**
   * Z component of the quaternion. This is the value along the "imaginary" `k` axis. Note:
   * Quaternion components should usually not be manipulated directly.
   *
   * Generated from Godot docs: Quaternion.z
   */
  val z: real_t,
  /**
   * W component of the quaternion. This is the "real" part. Note: Quaternion components should
   * usually not be manipulated directly.
   *
   * Generated from Godot docs: Quaternion.w
   */
  val w: real_t,
) {
  constructor(
    x: Number,
    y: Number,
    z: Number,
    w: Number,
  ) : this(
    GodotReal.fromNumber(x),
    GodotReal.fromNumber(y),
    GodotReal.fromNumber(z),
    GodotReal.fromNumber(w),
  )

  // Match GDScript/C# `==`: signed zero equal (-0.0 == 0.0), NaN reflexive. See
  // wrapper-coverage-roadmap.md. hashCode canonicalizes signed zero so equal quaternions hash
  // equal.
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is Quaternion) return false
    return (x == other.x || (x.isNaN() && other.x.isNaN())) &&
      (y == other.y || (y.isNaN() && other.y.isNaN())) &&
      (z == other.z || (z.isNaN() && other.z.isNaN())) &&
      (w == other.w || (w.isNaN() && other.w.isNaN()))
  }

  override fun hashCode(): Int {
    var result = (x + 0.0f).hashCode()
    result = 31 * result + (y + 0.0f).hashCode()
    result = 31 * result + (z + 0.0f).hashCode()
    result = 31 * result + (w + 0.0f).hashCode()
    return result
  }

  /** Godot `Quaternion.is_equal_approx`: per-component fuzzy compare (CMP_EPSILON tolerance). */
  /**
   * Returns `true` if this quaternion and `to` are approximately equal, by calling
   * `@GlobalScope.is_equal_approx` on each component.
   *
   * Generated from Godot docs: Quaternion.is_equal_approx
   */
  fun isEqualApprox(other: Quaternion): Boolean =
    isEqualApprox(x, other.x) &&
      isEqualApprox(y, other.y) &&
      isEqualApprox(z, other.z) &&
      isEqualApprox(w, other.w)

  /**
   * Returns this quaternion's length, squared. Note: This method is faster than `length`, so prefer
   * it if you only need to compare quaternion lengths.
   *
   * Generated from Godot docs: Quaternion.length_squared
   */
  fun lengthSquared(): Double = (x * x + y * y + z * z + w * w).toDouble()

  /**
   * Returns this quaternion's length, also called magnitude.
   *
   * Generated from Godot docs: Quaternion.length
   */
  fun length(): Double = sqrt(lengthSquared())

  /**
   * Returns a copy of this quaternion, normalized so that its length is `1.0`. See also
   * `is_normalized`.
   *
   * Generated from Godot docs: Quaternion.normalized
   */
  fun normalized(): Quaternion {
    val len = length()
    return if (len == 0.0) IDENTITY
    else Quaternion(x.toDouble() / len, y.toDouble() / len, z.toDouble() / len, w.toDouble() / len)
  }

  operator fun times(other: Quaternion): Quaternion =
    Quaternion(
      w * other.x + x * other.w + y * other.z - z * other.y,
      w * other.y - x * other.z + y * other.w + z * other.x,
      w * other.z + x * other.y - y * other.x + z * other.w,
      w * other.w - x * other.x - y * other.y - z * other.z,
    )

  operator fun unaryMinus(): Quaternion = Quaternion(-x, -y, -z, -w)

  /**
   * Returns the inverse version of this quaternion, inverting the sign of every component except
   * `w`.
   *
   * Generated from Godot docs: Quaternion.inverse
   */
  fun inverse(): Quaternion =
    fromGodotRealArray(BuiltinCalls.callNoArgsFloat32(inverseBind, toGodotRealArray()))

  /**
   * Returns the dot product between this quaternion and `with`. This is equivalent to `(quat.x *
   * with.x) + (quat.y * with.y) + (quat.z * with.z) + (quat.w * with.w)`.
   *
   * Generated from Godot docs: Quaternion.dot
   */
  fun dot(other: Quaternion): Double =
    (x * other.x + y * other.y + z * other.z + w * other.w).toDouble()

  /**
   * Performs a spherical-linear interpolation with the `to` quaternion, given a `weight` and
   * returns the result. Both this quaternion and `to` must be normalized.
   *
   * Generated from Godot docs: Quaternion.slerp
   */
  fun slerp(to: Quaternion, weight: Double): Quaternion =
    fromGodotRealArray(
      BuiltinCalls.call(
        slerpBind,
        toGodotRealArray(),
        4,
        listOf(
          BuiltinCalls.BArg.Floats(BuiltinCalls.PT_QUATERNION, to.toGodotRealArray()),
          BuiltinCalls.BArg.Real(weight),
        ),
      )
    )

  private fun toGodotRealArray(): GodotRealArray =
    GodotRealArray(4).also {
      it[0] = GodotReal.toC(x)
      it[1] = GodotReal.toC(y)
      it[2] = GodotReal.toC(z)
      it[3] = GodotReal.toC(w)
    }

  companion object {
    /**
     * The identity quaternion, representing no rotation. This has the same rotation as
     * `Basis.IDENTITY`. If a `Vector3` is rotated (multiplied) by this quaternion, it does not
     * change. Note: In GDScript, this constant is equivalent to creating a [constructor Quaternion]
     * without any arguments. It can be used to make your code clearer, and for consistency with C#.
     *
     * Generated from Godot docs: Quaternion.IDENTITY
     */
    val IDENTITY = Quaternion(0f, 0f, 0f, 1f)

    private val inverseBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_QUATERNION, "inverse", INVERSE_HASH)
    }
    private val slerpBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_QUATERNION, "slerp", SLERP_HASH)
    }
    private val fromEulerBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_QUATERNION, "from_euler", FROM_EULER_HASH)
    }

    /**
     * Constructs a new `Quaternion` from the given `Vector3` of Euler angles, in radians. This
     * method always uses the YXZ convention (`EULER_ORDER_YXZ`).
     *
     * Generated from Godot docs: Quaternion.from_euler
     */
    fun fromEuler(euler: Vector3): Quaternion =
      // `Quaternion.from_euler` is a *static* builtin, so the call passes an empty base.
      fromGodotRealArray(
        BuiltinCalls.call(
          fromEulerBind,
          GodotRealArray(0),
          4,
          listOf(
            BuiltinCalls.BArg.Floats(
              BuiltinCalls.PT_VECTOR3,
              GodotRealArray(3).also {
                it[0] = GodotReal.toC(euler.x)
                it[1] = GodotReal.toC(euler.y)
                it[2] = GodotReal.toC(euler.z)
              },
            )
          ),
        )
      )

    private fun fromGodotRealArray(c: GodotRealArray): Quaternion =
      Quaternion(
        GodotReal.fromC(c[0]),
        GodotReal.fromC(c[1]),
        GodotReal.fromC(c[2]),
        GodotReal.fromC(c[3]),
      )
  }
}

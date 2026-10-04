package net.multigesture.kanama.types

import kotlin.math.sqrt
import net.multigesture.kanama.binding.runtime.BArg
import net.multigesture.kanama.binding.runtime.BuiltinCalls
import net.multigesture.kanama.binding.runtime.PT_QUATERNION
import net.multigesture.kanama.binding.runtime.PT_VECTOR3
import net.multigesture.kanama.binding.runtime.VT_QUATERNION

private const val SLERP_HASH = 1773590316L
private const val INVERSE_HASH = 4274879941L
private const val FROM_EULER_HASH = 4053467903L

// One body for every backend (task 104 step 2): `inverse`, `slerp` and `from_euler` are computed by
// the engine through BuiltinCalls (their shortest-arc and non-unit handling is the engine's), the
// rest is exact arithmetic in Kotlin. At ptrcall a Quaternion is 4 `real_t` in x, y, z, w order.
/**
 * A unit quaternion used for representing 3D rotations. Kanama value types are immutable snapshots;
 * assign a new value back to the Godot property after changing components.
 *
 * Generated from Godot docs: Quaternion
 */
class Quaternion
private constructor(
  internal val rawX: GodotRealStorage,
  internal val rawY: GodotRealStorage,
  internal val rawZ: GodotRealStorage,
  internal val rawW: GodotRealStorage,
  @Suppress("UNUSED_PARAMETER") raw: RawStorage,
) {
  /** A quaternion stored at Godot's `real_t` width: each component is rounded to it. */
  constructor(
    x: Double,
    y: Double,
    z: Double,
    w: Double,
  ) : this(GodotReal.toC(x), GodotReal.toC(y), GodotReal.toC(z), GodotReal.toC(w), RawStorage)

  /** GDScript's `Quaternion(0, 0, 0, 1)`: integer components. */
  constructor(
    x: Int,
    y: Int,
    z: Int,
    w: Int,
  ) : this(x.toDouble(), y.toDouble(), z.toDouble(), w.toDouble())

  /**
   * X component of the quaternion. This is the value along the "imaginary" `i` axis. Note:
   * Quaternion components should usually not be manipulated directly.
   *
   * Generated from Godot docs: Quaternion.x
   */
  val x: Double
    get() = GodotReal.fromC(rawX)

  /**
   * Y component of the quaternion. This is the value along the "imaginary" `j` axis. Note:
   * Quaternion components should usually not be manipulated directly.
   *
   * Generated from Godot docs: Quaternion.y
   */
  val y: Double
    get() = GodotReal.fromC(rawY)

  /**
   * Z component of the quaternion. This is the value along the "imaginary" `k` axis. Note:
   * Quaternion components should usually not be manipulated directly.
   *
   * Generated from Godot docs: Quaternion.z
   */
  val z: Double
    get() = GodotReal.fromC(rawZ)

  /**
   * W component of the quaternion. This is the "real" part. Note: Quaternion components should
   * usually not be manipulated directly.
   *
   * Generated from Godot docs: Quaternion.w
   */
  val w: Double
    get() = GodotReal.fromC(rawW)

  operator fun component1(): Double = x

  operator fun component2(): Double = y

  operator fun component3(): Double = z

  operator fun component4(): Double = w

  /** This quaternion with some components replaced. */
  fun copy(
    x: Double = this.x,
    y: Double = this.y,
    z: Double = this.z,
    w: Double = this.w,
  ): Quaternion = Quaternion(x, y, z, w)

  // Godot's `==` on the stored components (signed zero equal, -0.0 == 0.0); NaN equals NaN to keep
  // the JVM equals contract reflexive. hashCode canonicalizes signed zero so equal values hash
  // equal.
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

  /** Godot's `str(q)`: `(0, 0, 0, 1)` (no `.0` on whole numbers, unlike the vectors). */
  override fun toString(): String =
    "(${godotRealString(x, false)}, ${godotRealString(y, false)}, " +
      "${godotRealString(z, false)}, ${godotRealString(w, false)})"

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
  fun lengthSquared(): Double = GodotReal.fromC(rawLengthSquared())

  private fun rawLengthSquared(): GodotRealStorage =
    rawX * rawX + rawY * rawY + rawZ * rawZ + rawW * rawW

  /**
   * Returns this quaternion's length, also called magnitude.
   *
   * Generated from Godot docs: Quaternion.length
   */
  fun length(): Double = GodotReal.fromC(sqrt(rawLengthSquared()))

  /**
   * Returns a copy of this quaternion, normalized so that its length is `1.0`. See also
   * `is_normalized`.
   *
   * Generated from Godot docs: Quaternion.normalized
   */
  fun normalized(): Quaternion {
    // Godot: `*this / length()`, which multiplies by `1 / length` in `real_t`.
    val len = sqrt(rawLengthSquared())
    if (len == GodotReal.toC(0.0)) return IDENTITY
    val inverseLength = GodotReal.toC(1.0) / len
    return raw(
      rawX * inverseLength,
      rawY * inverseLength,
      rawZ * inverseLength,
      rawW * inverseLength,
    )
  }

  operator fun times(other: Quaternion): Quaternion =
    raw(
      rawW * other.rawX + rawX * other.rawW + rawY * other.rawZ - rawZ * other.rawY,
      rawW * other.rawY - rawX * other.rawZ + rawY * other.rawW + rawZ * other.rawX,
      rawW * other.rawZ + rawX * other.rawY - rawY * other.rawX + rawZ * other.rawW,
      rawW * other.rawW - rawX * other.rawX - rawY * other.rawY - rawZ * other.rawZ,
    )

  operator fun unaryMinus(): Quaternion = raw(-rawX, -rawY, -rawZ, -rawW)

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
    GodotReal.fromC(rawX * other.rawX + rawY * other.rawY + rawZ * other.rawZ + rawW * other.rawW)

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
        listOf(BArg.Floats(PT_QUATERNION, to.toGodotRealArray()), BArg.Real(weight)),
      )
    )

  private fun toGodotRealArray(): GodotRealArray =
    GodotRealArray(4).also {
      it[0] = rawX
      it[1] = rawY
      it[2] = rawZ
      it[3] = rawW
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
    val IDENTITY = Quaternion(0.0, 0.0, 0.0, 1.0)

    private val inverseBind by lazy {
      BuiltinCalls.getBuiltinMethod(VT_QUATERNION, "inverse", INVERSE_HASH)
    }
    private val slerpBind by lazy {
      BuiltinCalls.getBuiltinMethod(VT_QUATERNION, "slerp", SLERP_HASH)
    }
    private val fromEulerBind by lazy {
      BuiltinCalls.getBuiltinMethod(VT_QUATERNION, "from_euler", FROM_EULER_HASH)
    }

    /**
     * Constructs a new `Quaternion` from the given `Vector3` of Euler angles
     * (https://en.wikipedia.org/wiki/Euler_angles), in radians. In Godot, Euler angles always use
     * intrinsic order. This method always uses the intrinsic YXZ convention (`EulerOrder.YXZ`).
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
            BArg.Floats(
              PT_VECTOR3,
              GodotRealArray(3).also {
                it[0] = euler.rawX
                it[1] = euler.rawY
                it[2] = euler.rawZ
              },
            )
          ),
        )
      )

    private fun fromGodotRealArray(c: GodotRealArray): Quaternion = raw(c[0], c[1], c[2], c[3])

    /** A quaternion from components already at the storage width (marshalling; no conversion). */
    internal fun raw(
      x: GodotRealStorage,
      y: GodotRealStorage,
      z: GodotRealStorage,
      w: GodotRealStorage,
    ): Quaternion = Quaternion(x, y, z, w, RawStorage)
  }
}

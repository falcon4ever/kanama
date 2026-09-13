package net.multigesture.kanama.types

import net.multigesture.kanama.binding.runtime.BuiltinCalls

private const val GET_EULER_HASH = 1394941017L
private const val FROM_EULER_HASH = 2802321791L
private const val LOOKING_AT_HASH = 3728732505L
private const val SCALED_HASH = 3934786792L
private const val GET_SCALE_HASH = 1776574132L
private const val GET_ROTATION_QUATERNION_HASH = 4274879941L
private const val NO_ARG_SELF_HASH = 594669093L
private const val DETERMINANT_HASH = 466405837L

/**
 * A 3×3 matrix for representing 3D rotation and scale. Kanama value types are immutable snapshots;
 * assign a new value back to the Godot property after changing components.
 *
 * One body for every backend (task 104 step 2). Everything whose result depends on Godot's own
 * orthonormalization, Euler convention or negative-scale handling is computed by the engine through
 * [net.multigesture.kanama.binding.runtime.BuiltinCalls]; the exact arithmetic is plain Kotlin. At
 * ptrcall a Basis is 9 `real_t` in column-major order — `[x.x, y.x, z.x, x.y, y.y, z.y, x.z, y.z,
 * z.z]` — which is also the marshal form of the builtin calls below.
 *
 * Generated from Godot docs: Basis
 */
data class Basis(
  /**
   * The basis's X axis, and the column `0` of the matrix. On the identity basis, this vector points
   * right (`Vector3.RIGHT`).
   *
   * Generated from Godot docs: Basis.x
   */
  val x: Vector3,
  /**
   * The basis's Y axis, and the column `1` of the matrix. On the identity basis, this vector points
   * up (`Vector3.UP`).
   *
   * Generated from Godot docs: Basis.y
   */
  val y: Vector3,
  /**
   * The basis's Z axis, and the column `2` of the matrix. On the identity basis, this vector points
   * back (`Vector3.BACK`).
   *
   * Generated from Godot docs: Basis.z
   */
  val z: Vector3,
) {
  /**
   * Builds the rotation basis for [quaternion] (matches Godot's `Basis(Quaternion)` constructor).
   */
  constructor(quaternion: Quaternion) : this(columnsFromQuaternion(quaternion))

  private constructor(
    columns: Triple<Vector3, Vector3, Vector3>
  ) : this(columns.first, columns.second, columns.third)

  /** Godot-style fuzzy compare: true if every axis is approximately equal. */
  /**
   * Returns `true` if this basis and `b` are approximately equal, by calling
   * `@GlobalScope.is_equal_approx` on all vector components.
   *
   * Generated from Godot docs: Basis.is_equal_approx
   */
  fun isEqualApprox(other: Basis): Boolean =
    x.isEqualApprox(other.x) && y.isEqualApprox(other.y) && z.isEqualApprox(other.z)

  /**
   * kanama convenience (Godot has no composite `is_zero_approx`): true if every axis is
   * approximately zero.
   */
  fun isZeroApprox(): Boolean = x.isZeroApprox() && y.isZeroApprox() && z.isZeroApprox()

  operator fun times(vector: Vector3): Vector3 = x * vector.x + y * vector.y + z * vector.z

  /**
   * Returns the determinant (https://en.wikipedia.org/wiki/Determinant) of this basis's matrix. For
   * advanced math, this number can be used to determine a few attributes: - If the determinant is
   * exactly `0.0`, the basis is not invertible (see `inverse`). - If the determinant is a negative
   * number, the basis represents a negative scale. Note: If the basis's scale is the same for every
   * axis, its determinant is always that scale by the power of 3.
   *
   * Generated from Godot docs: Basis.determinant
   */
  fun determinant(): Double = BuiltinCalls.callScalar(determinantBind, toGodotRealArray())

  /**
   * Returns the inverse of this basis's matrix (https://en.wikipedia.org/wiki/Invertible_matrix).
   *
   * Generated from Godot docs: Basis.inverse
   */
  fun inverse(): Basis =
    fromGodotRealArray(BuiltinCalls.callNoArgsFloat32(inverseBind, toGodotRealArray()))

  /**
   * Returns the transposed version of this basis. This turns the basis matrix's columns into rows,
   * and its rows into columns.
   *
   * Generated from Godot docs: Basis.transposed
   */
  fun transposed(): Basis =
    fromGodotRealArray(BuiltinCalls.callNoArgsFloat32(transposedBind, toGodotRealArray()))

  /**
   * Returns the orthonormalized version of this basis. An orthonormal basis is both orthogonal (the
   * axes are perpendicular to each other) and normalized (the axes have a length of `1.0`), which
   * also means it can only represent a rotation. It is often useful to call this method to avoid
   * rounding errors on a rotating basis:
   *
   * Generated from Godot docs: Basis.orthonormalized
   */
  fun orthonormalized(): Basis =
    fromGodotRealArray(BuiltinCalls.callNoArgsFloat32(orthonormalizedBind, toGodotRealArray()))

  fun lerp(to: Basis, weight: Double): Basis =
    Basis(x.lerp(to.x, weight), y.lerp(to.y, weight), z.lerp(to.z, weight))

  /**
   * Returns this basis's rotation as a `Quaternion`. Note: Quaternions are much more suitable for
   * 3D math but are less intuitive. For user interfaces, consider using the `get_euler` method,
   * which returns Euler angles.
   *
   * Generated from Godot docs: Basis.get_rotation_quaternion
   */
  fun getRotationQuaternion(): Quaternion {
    val c = BuiltinCalls.call(getRotationQuaternionBind, toGodotRealArray(), 4)
    return Quaternion(
      GodotReal.fromC(c[0]),
      GodotReal.fromC(c[1]),
      GodotReal.fromC(c[2]),
      GodotReal.fromC(c[3]),
    )
  }

  /**
   * Returns the length of each axis of this basis, as a `Vector3`. If the basis is not sheared,
   * this value is the scaling factor. It is not affected by rotation.
   *
   * Generated from Godot docs: Basis.get_scale
   */
  fun getScale(): Vector3 = vector3From(BuiltinCalls.call(getScaleBind, toGodotRealArray(), 3))

  /**
   * Returns this basis with each axis's components scaled by the given `scale`'s components. The
   * basis matrix's rows are multiplied by `scale`'s components. This operation is a global scale
   * (relative to the parent).
   *
   * Generated from Godot docs: Basis.scaled
   */
  fun scaled(scale: Vector3): Basis =
    fromGodotRealArray(
      BuiltinCalls.call(
        scaledBind,
        toGodotRealArray(),
        9,
        listOf(BuiltinCalls.BArg.Floats(BuiltinCalls.PT_VECTOR3, vector3Array(scale))),
      )
    )

  fun withX(value: Vector3): Basis = Basis(value, y, z)

  fun withY(value: Vector3): Basis = Basis(x, value, z)

  fun withZ(value: Vector3): Basis = Basis(x, y, value)

  /**
   * Returns this basis's rotation as a `Vector3` of Euler angles
   * (https://en.wikipedia.org/wiki/Euler_angles), in radians. For the returned value: - The
   * `Vector3.x` contains the angle around the `x` axis (pitch); - The `Vector3.y` contains the
   * angle around the `y` axis (yaw); - The `Vector3.z` contains the angle around the `z` axis
   * (roll). The order of each consecutive rotation can be changed with `order` (see `EulerOrder`
   * constants). In Godot, Euler angles always use intrinsic order. By default, the intrinsic YXZ
   * convention is used (`EULER_ORDER_YXZ`): since we are decomposing, local Z (roll) is calculated
   * first, then local X (pitch), and lastly local Y (yaw). When using the opposite method
   * `from_euler` to compose a rotation, this order is reversed. Note: For this method to return
   * correctly, the basis needs to be orthonormal (see `orthonormalized`). Note: Euler angles are
   * much more intuitive but are not suitable for 3D math. Because of this, consider using the
   * `get_rotation_quaternion` method instead, which returns a `Quaternion`. Note: In the Inspector
   * dock, a basis's rotation is often displayed in Euler angles (in degrees), as is the case with
   * the `Node3D.rotation` property.
   *
   * Generated from Godot docs: Basis.get_euler
   */
  fun getEuler(order: Long = EULER_ORDER_YXZ): Vector3 =
    vector3From(
      BuiltinCalls.call(getEulerBind, toGodotRealArray(), 3, listOf(BuiltinCalls.BArg.Int64(order)))
    )

  // Column-major real_t values, matching the ObjectCalls Basis ptrcall layout.
  private fun toGodotRealArray(): GodotRealArray =
    GodotRealArray(9).also {
      it[0] = GodotReal.toC(x.x)
      it[1] = GodotReal.toC(y.x)
      it[2] = GodotReal.toC(z.x)
      it[3] = GodotReal.toC(x.y)
      it[4] = GodotReal.toC(y.y)
      it[5] = GodotReal.toC(z.y)
      it[6] = GodotReal.toC(x.z)
      it[7] = GodotReal.toC(y.z)
      it[8] = GodotReal.toC(z.z)
    }

  companion object {
    const val EULER_ORDER_XYZ = 0L
    const val EULER_ORDER_XZY = 1L
    const val EULER_ORDER_YXZ = 2L
    const val EULER_ORDER_YZX = 3L
    const val EULER_ORDER_ZXY = 4L
    const val EULER_ORDER_ZYX = 5L

    /**
     * The identity `Basis`. This is an orthonormal basis with no rotation, no shear, and a scale of
     * `Vector3.ONE`. This also means that: - The `x` points right (`Vector3.RIGHT`); - The `y`
     * points up (`Vector3.UP`); - The `z` points back (`Vector3.BACK`).
     *
     * Generated from Godot docs: Basis.IDENTITY
     */
    val IDENTITY = Basis(Vector3(1f, 0f, 0f), Vector3(0f, 1f, 0f), Vector3(0f, 0f, 1f))

    // The hash keys the signature SHAPE, the name selects the method: inverse, transposed and
    // orthonormalized are all no-arg -> Self and share one hash.
    private val inverseBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_BASIS, "inverse", NO_ARG_SELF_HASH)
    }
    private val transposedBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_BASIS, "transposed", NO_ARG_SELF_HASH)
    }
    private val orthonormalizedBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_BASIS, "orthonormalized", NO_ARG_SELF_HASH)
    }
    private val determinantBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_BASIS, "determinant", DETERMINANT_HASH)
    }
    private val scaledBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_BASIS, "scaled", SCALED_HASH)
    }
    private val getScaleBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_BASIS, "get_scale", GET_SCALE_HASH)
    }
    private val getRotationQuaternionBind by lazy {
      BuiltinCalls.getBuiltinMethod(
        BuiltinCalls.VT_BASIS,
        "get_rotation_quaternion",
        GET_ROTATION_QUATERNION_HASH,
      )
    }
    private val getEulerBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_BASIS, "get_euler", GET_EULER_HASH)
    }
    private val fromEulerBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_BASIS, "from_euler", FROM_EULER_HASH)
    }
    private val lookingAtBind by lazy {
      BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_BASIS, "looking_at", LOOKING_AT_HASH)
    }

    /**
     * Constructs a new `Basis` that only represents rotation from the given `Vector3` of Euler
     * angles (https://en.wikipedia.org/wiki/Euler_angles), in radians. - The `Vector3.x` should
     * contain the angle around the `x` axis (pitch); - The `Vector3.y` should contain the angle
     * around the `y` axis (yaw); - The `Vector3.z` should contain the angle around the `z` axis
     * (roll).
     *
     * Generated from Godot docs: Basis.from_euler
     */
    fun fromEuler(euler: Vector3, order: Long = EULER_ORDER_YXZ): Basis =
      // `Basis.from_euler` is a *static* builtin, so the call passes an empty base (NULL instance).
      fromGodotRealArray(
        BuiltinCalls.call(
          fromEulerBind,
          GodotRealArray(0),
          9,
          listOf(
            BuiltinCalls.BArg.Floats(BuiltinCalls.PT_VECTOR3, vector3Array(euler)),
            BuiltinCalls.BArg.Int64(order),
          ),
        )
      )

    /**
     * Creates a new `Basis` with a rotation such that the forward axis (-Z) points towards the
     * `target` position. By default, the -Z axis (camera forward) is treated as forward (implies +X
     * is right). If `use_model_front` is `true`, the +Z axis (asset front) is treated as forward
     * (implies +X is left) and points toward the `target` position. The up axis (+Y) points as
     * close to the `up` vector as possible while staying perpendicular to the forward axis. The
     * returned basis is orthonormalized (see `orthonormalized`). The `target` and the `up` cannot
     * be `Vector3.ZERO`, and shouldn't be colinear to avoid unintended rotation around local Z
     * axis.
     *
     * Generated from Godot docs: Basis.looking_at
     */
    fun lookingAt(
      target: Vector3,
      up: Vector3 = Vector3.UP,
      useModelFront: Boolean = false,
    ): Basis =
      fromGodotRealArray(
        BuiltinCalls.call(
          lookingAtBind,
          GodotRealArray(0),
          9,
          listOf(
            BuiltinCalls.BArg.Floats(BuiltinCalls.PT_VECTOR3, vector3Array(target)),
            BuiltinCalls.BArg.Floats(BuiltinCalls.PT_VECTOR3, vector3Array(up)),
            BuiltinCalls.BArg.Bool(useModelFront),
          ),
        )
      )

    // Godot `Basis::set_quaternion`: builds the 3 column axes from a quaternion. rows[i][j] in
    // the engine is component i of column j here, so the engine rows are transposed into columns.
    // Exact arithmetic (no epsilon, no normalization), so it stays in Kotlin.
    private fun columnsFromQuaternion(q: Quaternion): Triple<Vector3, Vector3, Vector3> {
      val d = q.x * q.x + q.y * q.y + q.z * q.z + q.w * q.w
      val s = 2.0 / d
      val xs = q.x * s
      val ys = q.y * s
      val zs = q.z * s
      val wx = q.w * xs
      val wy = q.w * ys
      val wz = q.w * zs
      val xx = q.x * xs
      val xy = q.x * ys
      val xz = q.x * zs
      val yy = q.y * ys
      val yz = q.y * zs
      val zz = q.z * zs
      return Triple(
        Vector3(1.0 - (yy + zz), xy + wz, xz - wy),
        Vector3(xy - wz, 1.0 - (xx + zz), yz + wx),
        Vector3(xz + wy, yz - wx, 1.0 - (xx + yy)),
      )
    }

    private fun vector3Array(v: Vector3): GodotRealArray =
      GodotRealArray(3).also {
        it[0] = GodotReal.toC(v.x)
        it[1] = GodotReal.toC(v.y)
        it[2] = GodotReal.toC(v.z)
      }

    private fun vector3From(c: GodotRealArray): Vector3 =
      Vector3(GodotReal.fromC(c[0]), GodotReal.fromC(c[1]), GodotReal.fromC(c[2]))

    private fun fromGodotRealArray(c: GodotRealArray): Basis =
      Basis(
        Vector3(GodotReal.fromC(c[0]), GodotReal.fromC(c[3]), GodotReal.fromC(c[6])),
        Vector3(GodotReal.fromC(c[1]), GodotReal.fromC(c[4]), GodotReal.fromC(c[7])),
        Vector3(GodotReal.fromC(c[2]), GodotReal.fromC(c[5]), GodotReal.fromC(c[8])),
      )
  }
}

package net.multigesture.kanama.types

// One body for every backend (task 104 step 2). Everything whose result depends on Godot's own
// orthonormalization, Euler convention or negative-scale handling is computed by the engine through
// BuiltinCalls; the exact arithmetic is plain Kotlin. At ptrcall a Basis is 9 `real_t` in
// column-major order — [x.x, y.x, z.x, x.y, y.y, z.y, x.z, y.z, z.z] — which is also the marshal
// form of the builtin calls below.
/**
 * A 3×3 matrix for representing 3D rotation and scale. Kanama value types are immutable snapshots;
 * assign a new value back to the Godot property after changing components.
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
   * Godot's `str(b)`: the columns, `[X: (1.0, 0.0, 0.0), Y: (0.0, 1.0, 0.0), Z: (0.0, 0.0, 1.0)]`.
   */
  override fun toString(): String = "[X: $x, Y: $y, Z: $z]"

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

  fun lerp(to: Basis, weight: Double): Basis =
    Basis(x.lerp(to.x, weight), y.lerp(to.y, weight), z.lerp(to.z, weight))

  fun withX(value: Vector3): Basis = Basis(value, y, z)

  fun withY(value: Vector3): Basis = Basis(x, value, z)

  fun withZ(value: Vector3): Basis = Basis(x, y, value)

  // ===== BEGIN GENERATED BUILTIN MEMBERS: Basis (generate_builtin_ops.py) =====
  operator fun times(scalar: Int): Basis = times(scalar.toDouble())

  operator fun times(scalar: Long): Basis = times(scalar.toDouble())

  operator fun div(scalar: Int): Basis = div(scalar.toDouble())

  operator fun div(scalar: Long): Basis = div(scalar.toDouble())

  operator fun times(scalar: Double): Basis {
    val s = narrowReal(scalar)
    return basisMap(this) { it * s }
  }

  operator fun div(scalar: Double): Basis {
    val s = narrowReal(scalar)
    return basisMap(this) { it / s }
  }

  operator fun times(other: Basis): Basis = basisMultiply(this, other)

  /**
   * Returns the inverse of this basis's matrix (https://en.wikipedia.org/wiki/Invertible_matrix).
   *
   * Generated from Godot docs: Basis.inverse
   */
  fun inverse(): Basis {
    val f = builtinFrame()
    f.put(0, this)
    f.call(BasisMethods.inverse, 0)
    return f.retBasis()
  }

  /**
   * Returns the transposed version of this basis. This turns the basis matrix's columns into rows,
   * and its rows into columns.
   *
   * Generated from Godot docs: Basis.transposed
   */
  fun transposed(): Basis {
    val f = builtinFrame()
    f.put(0, this)
    f.call(BasisMethods.transposed, 0)
    return f.retBasis()
  }

  /**
   * Returns the orthonormalized version of this basis. An orthonormal basis is both orthogonal (the
   * axes are perpendicular to each other) and normalized (the axes have a length of `1.0`), which
   * also means it can only represent a rotation.
   *
   * Generated from Godot docs: Basis.orthonormalized
   */
  fun orthonormalized(): Basis {
    val f = builtinFrame()
    f.put(0, this)
    f.call(BasisMethods.orthonormalized, 0)
    return f.retBasis()
  }

  /**
   * Returns the determinant (https://en.wikipedia.org/wiki/Determinant) of this basis's matrix. For
   * advanced math, this number can be used to determine a few attributes: - If the determinant is
   * exactly `0.0`, the basis is not invertible (see `inverse`). - If the determinant is a negative
   * number, the basis represents a negative scale. Note: If the basis's scale is the same for every
   * axis, its determinant is always that scale by the power of 3.
   *
   * Generated from Godot docs: Basis.determinant
   */
  fun determinant(): Double {
    val f = builtinFrame()
    f.put(0, this)
    f.call(BasisMethods.determinant, 0)
    return f.retDouble()
  }

  /**
   * Returns a copy of this basis rotated around the given `axis` by the given `angle` (in radians).
   * The `axis` must be a normalized vector (see `Vector3.normalized`). If `angle` is positive, the
   * basis is rotated counter-clockwise around the axis.
   *
   * Generated from Godot docs: Basis.rotated
   */
  fun rotated(axis: Vector3, angle: Double): Basis {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, axis)
    f.putDouble(2, angle)
    f.call(BasisMethods.rotated, 2)
    return f.retBasis()
  }

  /**
   * Returns this basis with each axis's components scaled by the given `scale`'s components. The
   * basis matrix's rows are multiplied by `scale`'s components. This operation is a global scale
   * (relative to the parent).
   *
   * Generated from Godot docs: Basis.scaled
   */
  fun scaled(scale: Vector3): Basis {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, scale)
    f.call(BasisMethods.scaled, 1)
    return f.retBasis()
  }

  /**
   * Returns this basis with each axis scaled by the corresponding component in the given `scale`.
   * The basis matrix's columns are multiplied by `scale`'s components. This operation is a local
   * scale (relative to self).
   *
   * Generated from Godot docs: Basis.scaled_local
   */
  fun scaledLocal(scale: Vector3): Basis {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, scale)
    f.call(BasisMethods.scaledLocal, 1)
    return f.retBasis()
  }

  /**
   * Returns the length of each axis of this basis, as a `Vector3`. If the basis is not sheared,
   * this value is the scaling factor. It is not affected by rotation.
   *
   * Generated from Godot docs: Basis.get_scale
   */
  fun getScale(): Vector3 {
    val f = builtinFrame()
    f.put(0, this)
    f.call(BasisMethods.getScale, 0)
    return f.retVector3()
  }

  /**
   * Returns this basis's rotation as a `Vector3` of Euler angles
   * (https://en.wikipedia.org/wiki/Euler_angles), in radians. For the returned value: - The
   * `Vector3.x` contains the angle around the `x` axis (pitch); - The `Vector3.y` contains the
   * angle around the `y` axis (yaw); - The `Vector3.z` contains the angle around the `z` axis
   * (roll). The order of each consecutive rotation can be changed with `order` (see `EulerOrder`
   * constants). In Godot, Euler angles always use intrinsic order. By default, the intrinsic YXZ
   * convention is used (`EulerOrder.YXZ`): since we are decomposing, local Z (roll) is calculated
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
  fun getEuler(order: Long = 2L): Vector3 {
    val f = builtinFrame()
    f.put(0, this)
    f.putLong(1, order)
    f.call(BasisMethods.getEuler, 1)
    return f.retVector3()
  }

  /**
   * Returns the transposed dot product between `with` and the `x` axis (see `transposed`). This is
   * equivalent to `basis.x.dot(vector)`.
   *
   * Generated from Godot docs: Basis.tdotx
   */
  fun tdotx(with: Vector3): Double {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, with)
    f.call(BasisMethods.tdotx, 1)
    return f.retDouble()
  }

  /**
   * Returns the transposed dot product between `with` and the `y` axis (see `transposed`). This is
   * equivalent to `basis.y.dot(vector)`.
   *
   * Generated from Godot docs: Basis.tdoty
   */
  fun tdoty(with: Vector3): Double {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, with)
    f.call(BasisMethods.tdoty, 1)
    return f.retDouble()
  }

  /**
   * Returns the transposed dot product between `with` and the `z` axis (see `transposed`). This is
   * equivalent to `basis.z.dot(vector)`.
   *
   * Generated from Godot docs: Basis.tdotz
   */
  fun tdotz(with: Vector3): Double {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, with)
    f.call(BasisMethods.tdotz, 1)
    return f.retDouble()
  }

  /**
   * Performs a spherical-linear interpolation with the `to` basis, given a `weight`. Both this
   * basis and `to` should represent a rotation.
   *
   * Generated from Godot docs: Basis.slerp
   */
  fun slerp(to: Basis, weight: Double): Basis {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, to)
    f.putDouble(2, weight)
    f.call(BasisMethods.slerp, 2)
    return f.retBasis()
  }

  /**
   * Returns `true` if this basis is conformal. A conformal basis is both orthogonal (the axes are
   * perpendicular to each other) and uniform (the axes share the same length). This method can be
   * especially useful during physics calculations.
   *
   * Generated from Godot docs: Basis.is_conformal
   */
  fun isConformal(): Boolean {
    val f = builtinFrame()
    f.put(0, this)
    f.call(BasisMethods.isConformal, 0)
    return f.retBool()
  }

  /**
   * Returns `true` if this basis is finite, by calling `@GlobalScope.is_finite` on all vector
   * components.
   *
   * Generated from Godot docs: Basis.is_finite
   */
  fun isFinite(): Boolean {
    val f = builtinFrame()
    f.put(0, this)
    f.call(BasisMethods.isFinite, 0)
    return f.retBool()
  }

  /**
   * Returns `true` if this basis is orthonormal. An orthonormal basis is both orthogonal (the axes
   * are perpendicular to each other) and normalized (the length of every axis is `1.0`). This
   * method can be especially useful during physics calculations.
   *
   * Generated from Godot docs: Basis.is_orthonormal
   */
  fun isOrthonormal(): Boolean {
    val f = builtinFrame()
    f.put(0, this)
    f.call(BasisMethods.isOrthonormal, 0)
    return f.retBool()
  }

  /**
   * Returns this basis's rotation as a `Quaternion`. Note: Quaternions are much more suitable for
   * 3D math but are less intuitive. For user interfaces, consider using the `get_euler` method,
   * which returns Euler angles.
   *
   * Generated from Godot docs: Basis.get_rotation_quaternion
   */
  fun getRotationQuaternion(): Quaternion {
    val f = builtinFrame()
    f.put(0, this)
    f.call(BasisMethods.getRotationQuaternion, 0)
    return f.retQuaternion()
  }

  // ===== END GENERATED BUILTIN MEMBERS: Basis =====

  companion object {
    // ===== BEGIN GENERATED BUILTIN STATICS: Basis (generate_builtin_ops.py) =====
    /**
     * When any basis is multiplied by `FLIP_X`, it negates all components of the `x` axis (the X
     * column). When `FLIP_X` is multiplied by any basis, it negates the `Vector3.x` component of
     * all axes (the X row).
     *
     * Generated from Godot docs: Basis.FLIP_X
     */
    val FLIP_X: Basis =
      Basis(Vector3(-1.0, 0.0, 0.0), Vector3(0.0, 1.0, 0.0), Vector3(0.0, 0.0, 1.0))

    /**
     * When any basis is multiplied by `FLIP_Y`, it negates all components of the `y` axis (the Y
     * column). When `FLIP_Y` is multiplied by any basis, it negates the `Vector3.y` component of
     * all axes (the Y row).
     *
     * Generated from Godot docs: Basis.FLIP_Y
     */
    val FLIP_Y: Basis =
      Basis(Vector3(1.0, 0.0, 0.0), Vector3(0.0, -1.0, 0.0), Vector3(0.0, 0.0, 1.0))

    /**
     * When any basis is multiplied by `FLIP_Z`, it negates all components of the `z` axis (the Z
     * column). When `FLIP_Z` is multiplied by any basis, it negates the `Vector3.z` component of
     * all axes (the Z row).
     *
     * Generated from Godot docs: Basis.FLIP_Z
     */
    val FLIP_Z: Basis =
      Basis(Vector3(1.0, 0.0, 0.0), Vector3(0.0, 1.0, 0.0), Vector3(0.0, 0.0, -1.0))

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
      up: Vector3 = Vector3(0.0, 1.0, 0.0),
      useModelFront: Boolean = false,
    ): Basis {
      val f = builtinFrame()
      f.put(1, target)
      f.put(2, up)
      f.putBool(3, useModelFront)
      f.callStatic(BasisMethods.lookingAt, 3)
      return f.retBasis()
    }

    /**
     * Constructs a new `Basis` that only represents scale, with no rotation or shear, from the
     * given `scale` vector.
     *
     * Generated from Godot docs: Basis.from_scale
     */
    fun fromScale(scale: Vector3): Basis {
      val f = builtinFrame()
      f.put(1, scale)
      f.callStatic(BasisMethods.fromScale, 1)
      return f.retBasis()
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
    fun fromEuler(euler: Vector3, order: Long = 2L): Basis {
      val f = builtinFrame()
      f.put(1, euler)
      f.putLong(2, order)
      f.callStatic(BasisMethods.fromEuler, 2)
      return f.retBasis()
    }

    // ===== END GENERATED BUILTIN STATICS: Basis =====

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
    val IDENTITY = Basis(Vector3(1.0, 0.0, 0.0), Vector3(0.0, 1.0, 0.0), Vector3(0.0, 0.0, 1.0))

    // Godot `Basis::set_quaternion`: builds the 3 column axes from a quaternion. rows[i][j] in
    // the engine is component i of column j here, so the engine rows are transposed into columns.
    // Exact arithmetic (no epsilon, no normalization), so it stays in Kotlin, in `real_t` like
    // the engine, so the result is the engine's to the bit.
    private fun columnsFromQuaternion(q: Quaternion): Triple<Vector3, Vector3, Vector3> =
      // Godot's rows[i][j] is component i of column j here: the rows are transposed into columns.
      realBasisFromQuaternion(q.rawX, q.rawY, q.rawZ, q.rawW) {
        r00,
        r01,
        r02,
        r10,
        r11,
        r12,
        r20,
        r21,
        r22 ->
        Triple(Vector3.raw(r00, r10, r20), Vector3.raw(r01, r11, r21), Vector3.raw(r02, r12, r22))
      }
  }
}

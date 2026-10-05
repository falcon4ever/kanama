package net.multigesture.kanama.types

private const val VECTOR3_ARG_SELF_HASH = 1405596198L

// One body for every backend (task 104 step 2): every method here is computed by the engine through
// BuiltinCalls, because each one encodes a Godot convention (the orthonormal-only fast `inverse`,
// the quaternion slerp inside `interpolate_with`, the discarded rotation in `looking_at`). At
// ptrcall a Transform3D is 12 `real_t` — the 9 column-major basis components followed by the 3
// origin components.
/**
 * A 3×4 matrix representing a 3D transformation. Kanama value types are immutable snapshots; assign
 * a new value back to the Godot property after changing components.
 *
 * Generated from Godot docs: Transform3D
 */
data class Transform3D(
  /**
   * The `Basis` of this transform. It is composed by 3 axes (`Basis.x`, `Basis.y`, and `Basis.z`).
   * Together, these represent the transform's rotation, scale, and shear.
   *
   * Generated from Godot docs: Transform3D.basis
   */
  val basis: Basis,
  /**
   * The translation offset of this transform. In 3D space, this can be seen as the position.
   *
   * Generated from Godot docs: Transform3D.origin
   */
  val origin: Vector3,
) {
  /**
   * Godot's `str(t)`: the basis columns and the origin, `[X: (1.0, 0.0, 0.0), …, O: (0.0, 0.0,
   * 0.0)]`.
   */
  override fun toString(): String = "[X: ${basis.x}, Y: ${basis.y}, Z: ${basis.z}, O: $origin]"

  /** Godot-style fuzzy compare: true if basis and origin are approximately equal. */
  /**
   * Returns `true` if this transform and `xform` are approximately equal, by running
   * `@GlobalScope.is_equal_approx` on each component.
   *
   * Generated from Godot docs: Transform3D.is_equal_approx
   */
  fun isEqualApprox(other: Transform3D): Boolean =
    basis.isEqualApprox(other.basis) && origin.isEqualApprox(other.origin)

  /**
   * kanama convenience (Godot has no composite `is_zero_approx`): true if basis and origin are
   * approximately zero.
   */
  fun isZeroApprox(): Boolean = basis.isZeroApprox() && origin.isZeroApprox()

  operator fun times(vector: Vector3): Vector3 = basis * vector + origin

  fun withBasis(value: Basis): Transform3D = copy(basis = value)

  fun withOrigin(value: Vector3): Transform3D = copy(origin = value)

  // ===== BEGIN GENERATED BUILTIN MEMBERS: Transform3D (generate_builtin_ops.py) =====
  operator fun times(scalar: Int): Transform3D = times(scalar.toDouble())

  operator fun times(scalar: Long): Transform3D = times(scalar.toDouble())

  operator fun div(scalar: Int): Transform3D = div(scalar.toDouble())

  operator fun div(scalar: Long): Transform3D = div(scalar.toDouble())

  operator fun times(scalar: Double): Transform3D {
    val s = narrowReal(scalar)
    return Transform3D(basisMap(basis) { it * s }, origin * scalar)
  }

  operator fun div(scalar: Double): Transform3D {
    val s = narrowReal(scalar)
    return Transform3D(basisMap(basis) { it / s }, origin / scalar)
  }

  operator fun times(other: Plane): Plane = transform3DXformPlane(this, other)

  operator fun times(other: AABB): AABB = transform3DXformAabb(this, other)

  operator fun times(other: Transform3D): Transform3D = transform3DMultiply(this, other)

  operator fun times(other: List<Vector3>): List<Vector3> = other.map { transform3DXform(this, it) }

  /**
   * Returns the inverted version of this transform
   * (https://en.wikipedia.org/wiki/Invertible_matrix). See also `Basis.inverse`. Note: For this
   * method to return correctly, the transform's `basis` needs to be orthonormal (see
   * `orthonormalized`). That means the basis should only represent a rotation. If it does not, use
   * `affine_inverse` instead.
   *
   * Generated from Godot docs: Transform3D.inverse
   */
  fun inverse(): Transform3D = transform3DInverse(this)

  /**
   * Returns the inverted version of this transform. Unlike `inverse`, this method works with almost
   * any `basis`, including non-uniform ones, but is slower. See also `Basis.inverse`. Note: For
   * this method to return correctly, the transform's `basis` needs to have a determinant that is
   * not exactly `0.0` (see `Basis.determinant`).
   *
   * Generated from Godot docs: Transform3D.affine_inverse
   */
  fun affineInverse(): Transform3D = transform3DAffineInverse(this) ?: affineInverseInEngine()

  private fun affineInverseInEngine(): Transform3D {
    val f = builtinFrame()
    f.put(0, this)
    f.call(Transform3DMethods.affineInverse, 0)
    return f.retTransform3D()
  }

  /**
   * Returns a copy of this transform with its `basis` orthonormalized. An orthonormal basis is both
   * orthogonal (the axes are perpendicular to each other) and normalized (the axes have a length of
   * `1.0`), which also means it can only represent a rotation. See also `Basis.orthonormalized`.
   *
   * Generated from Godot docs: Transform3D.orthonormalized
   */
  fun orthonormalized(): Transform3D = Transform3D(basisOrthonormalized(basis), origin)

  /**
   * Returns a copy of this transform rotated around the given `axis` by the given `angle` (in
   * radians). The `axis` must be a normalized vector (see `Vector3.normalized`). If `angle` is
   * positive, the basis is rotated counter-clockwise around the axis. This method is an optimized
   * version of multiplying the given transform `X` with a corresponding rotation transform `R` from
   * the left, i.e., `R * X`. This can be seen as transforming with respect to the global/parent
   * frame.
   *
   * Generated from Godot docs: Transform3D.rotated
   */
  fun rotated(axis: Vector3, angle: Double): Transform3D {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, axis)
    f.putDouble(2, angle)
    f.call(Transform3DMethods.rotated, 2)
    return f.retTransform3D()
  }

  /**
   * Returns a copy of this transform rotated around the given `axis` by the given `angle` (in
   * radians). The `axis` must be a normalized vector in the transform's local coordinate system.
   * For example, to rotate around the local X-axis, use `Vector3.RIGHT`. This method is an
   * optimized version of multiplying the given transform `X` with a corresponding rotation
   * transform `R` from the right, i.e., `X * R`. This can be seen as transforming with respect to
   * the local frame.
   *
   * Generated from Godot docs: Transform3D.rotated_local
   */
  fun rotatedLocal(axis: Vector3, angle: Double): Transform3D {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, axis)
    f.putDouble(2, angle)
    f.call(Transform3DMethods.rotatedLocal, 2)
    return f.retTransform3D()
  }

  /**
   * Returns a copy of this transform scaled by the given `scale` factor. This method is an
   * optimized version of multiplying the given transform `X` with a corresponding scaling transform
   * `S` from the left, i.e., `S * X`. This can be seen as transforming with respect to the
   * global/parent frame.
   *
   * Generated from Godot docs: Transform3D.scaled
   */
  fun scaled(scale: Vector3): Transform3D {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, scale)
    f.call(Transform3DMethods.scaled, 1)
    return f.retTransform3D()
  }

  /**
   * Returns a copy of this transform scaled by the given `scale` factor. This method is an
   * optimized version of multiplying the given transform `X` with a corresponding scaling transform
   * `S` from the right, i.e., `X * S`. This can be seen as transforming with respect to the local
   * frame.
   *
   * Generated from Godot docs: Transform3D.scaled_local
   */
  fun scaledLocal(scale: Vector3): Transform3D {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, scale)
    f.call(Transform3DMethods.scaledLocal, 1)
    return f.retTransform3D()
  }

  /**
   * Returns a copy of this transform translated by the given `offset`. This method is an optimized
   * version of multiplying the given transform `X` with a corresponding translation transform `T`
   * from the left, i.e., `T * X`. This can be seen as transforming with respect to the
   * global/parent frame.
   *
   * Generated from Godot docs: Transform3D.translated
   */
  fun translated(offset: Vector3): Transform3D = Transform3D(basis, origin + offset)

  /**
   * Returns a copy of this transform translated by the given `offset`. This method is an optimized
   * version of multiplying the given transform `X` with a corresponding translation transform `T`
   * from the right, i.e., `X * T`. This can be seen as transforming with respect to the local
   * frame.
   *
   * Generated from Godot docs: Transform3D.translated_local
   */
  fun translatedLocal(offset: Vector3): Transform3D = Transform3D(basis, origin + basis * offset)

  /**
   * Returns a copy of this transform rotated so that the forward axis (-Z) points towards the
   * `target` position. The up axis (+Y) points as close to the `up` vector as possible while
   * staying perpendicular to the forward axis. The resulting transform is orthonormalized. The
   * existing rotation, scale, and skew information from the original transform is discarded. The
   * `target` and `up` vectors cannot be zero, cannot be parallel to each other, and are defined in
   * global/parent space. If `use_model_front` is `true`, the +Z axis (asset front) is treated as
   * forward (implies +X is left) and points toward the `target` position. By default, the -Z axis
   * (camera forward) is treated as forward (implies +X is right).
   *
   * Generated from Godot docs: Transform3D.looking_at
   */
  fun lookingAt(
    target: Vector3,
    up: Vector3 = Vector3(0.0, 1.0, 0.0),
    useModelFront: Boolean = false,
  ): Transform3D {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, target)
    f.put(2, up)
    f.putBool(3, useModelFront)
    f.call(Transform3DMethods.lookingAt, 3)
    return f.retTransform3D()
  }

  /**
   * Returns the result of the linear interpolation between this transform and `xform` by the given
   * `weight`. The `weight` should be between `0.0` and `1.0` (inclusive). Values outside this range
   * are allowed and can be used to perform extrapolation instead.
   *
   * Generated from Godot docs: Transform3D.interpolate_with
   */
  fun interpolateWith(xform: Transform3D, weight: Double): Transform3D {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, xform)
    f.putDouble(2, weight)
    f.call(Transform3DMethods.interpolateWith, 2)
    return f.retTransform3D()
  }

  /**
   * Returns `true` if this transform is finite, by calling `@GlobalScope.is_finite` on each
   * component.
   *
   * Generated from Godot docs: Transform3D.is_finite
   */
  fun isFinite(): Boolean {
    val f = builtinFrame()
    f.put(0, this)
    f.call(Transform3DMethods.isFinite, 0)
    return f.retBool()
  }

  // ===== END GENERATED BUILTIN MEMBERS: Transform3D =====

  companion object {
    // ===== BEGIN GENERATED BUILTIN STATICS: Transform3D (generate_builtin_ops.py) =====
    /**
     * `Transform3D` with mirroring applied perpendicular to the YZ plane. Its `basis` is equal to
     * `Basis.FLIP_X`.
     *
     * Generated from Godot docs: Transform3D.FLIP_X
     */
    val FLIP_X: Transform3D =
      Transform3D(
        Basis(Vector3(-1.0, 0.0, 0.0), Vector3(0.0, 1.0, 0.0), Vector3(0.0, 0.0, 1.0)),
        Vector3(0.0, 0.0, 0.0),
      )

    /**
     * `Transform3D` with mirroring applied perpendicular to the XZ plane. Its `basis` is equal to
     * `Basis.FLIP_Y`.
     *
     * Generated from Godot docs: Transform3D.FLIP_Y
     */
    val FLIP_Y: Transform3D =
      Transform3D(
        Basis(Vector3(1.0, 0.0, 0.0), Vector3(0.0, -1.0, 0.0), Vector3(0.0, 0.0, 1.0)),
        Vector3(0.0, 0.0, 0.0),
      )

    /**
     * `Transform3D` with mirroring applied perpendicular to the XY plane. Its `basis` is equal to
     * `Basis.FLIP_Z`.
     *
     * Generated from Godot docs: Transform3D.FLIP_Z
     */
    val FLIP_Z: Transform3D =
      Transform3D(
        Basis(Vector3(1.0, 0.0, 0.0), Vector3(0.0, 1.0, 0.0), Vector3(0.0, 0.0, -1.0)),
        Vector3(0.0, 0.0, 0.0),
      )

    // ===== END GENERATED BUILTIN STATICS: Transform3D =====

    /**
     * The identity `Transform3D`. This is a transform with no translation, no rotation, and a scale
     * of `Vector3.ONE`. Its `basis` is equal to `Basis.IDENTITY`. This also means that: - Its
     * `Basis.x` points right (`Vector3.RIGHT`); - Its `Basis.y` points up (`Vector3.UP`); - Its
     * `Basis.z` points back (`Vector3.BACK`).
     *
     * Generated from Godot docs: Transform3D.IDENTITY
     */
    val IDENTITY = Transform3D(Basis.IDENTITY, Vector3.ZERO)
  }
}

package net.multigesture.kanama.types

/**
 * A 2×3 matrix representing a 2D transformation. Kanama value types are immutable snapshots; assign
 * a new value back to the Godot property after changing components.
 *
 * Generated from Godot docs: Transform2D
 */
data class Transform2D(
  /**
   * The transform basis's X axis, and the column `0` of the matrix. Combined with `y`, this
   * represents the transform's rotation, scale, and skew. On the identity transform, this vector
   * points right (`Vector2.RIGHT`).
   *
   * Generated from Godot docs: Transform2D.x
   */
  val x: Vector2,
  /**
   * The transform basis's Y axis, and the column `1` of the matrix. Combined with `x`, this
   * represents the transform's rotation, scale, and skew. On the identity transform, this vector
   * points down (`Vector2.DOWN`).
   *
   * Generated from Godot docs: Transform2D.y
   */
  val y: Vector2,
  /**
   * The translation offset of this transform, and the column `2` of the matrix. In 2D space, this
   * can be seen as the position.
   *
   * Generated from Godot docs: Transform2D.origin
   */
  val origin: Vector2,
) {
  /** Godot's `str(t)`: `[X: (1.0, 0.0), Y: (0.0, 1.0), O: (0.0, 0.0)]`. */
  override fun toString(): String = "[X: $x, Y: $y, O: $origin]"

  /** Godot-style fuzzy compare: true if every column is approximately equal. */
  /**
   * Returns `true` if this transform and `xform` are approximately equal, by running
   * `@GlobalScope.is_equal_approx` on each component.
   *
   * Generated from Godot docs: Transform2D.is_equal_approx
   */
  fun isEqualApprox(other: Transform2D): Boolean =
    x.isEqualApprox(other.x) && y.isEqualApprox(other.y) && origin.isEqualApprox(other.origin)

  /**
   * kanama convenience (Godot has no composite `is_zero_approx`): true if every column is
   * approximately zero.
   */
  fun isZeroApprox(): Boolean = x.isZeroApprox() && y.isZeroApprox() && origin.isZeroApprox()

  // ===== BEGIN GENERATED BUILTIN MEMBERS: Transform2D (generate_builtin_ops.py) =====
  operator fun times(scalar: Int): Transform2D = times(scalar.toDouble())

  operator fun times(scalar: Long): Transform2D = times(scalar.toDouble())

  operator fun div(scalar: Int): Transform2D = div(scalar.toDouble())

  operator fun div(scalar: Long): Transform2D = div(scalar.toDouble())

  operator fun times(scalar: Double): Transform2D =
    Transform2D(x * scalar, y * scalar, origin * scalar)

  operator fun div(scalar: Double): Transform2D =
    Transform2D(x / scalar, y / scalar, origin / scalar)

  operator fun times(other: Vector2): Vector2 = transform2DXform(this, other)

  operator fun times(other: Rect2): Rect2 = transform2DXformRect(this, other)

  operator fun times(other: Transform2D): Transform2D = transform2DMultiply(this, other)

  operator fun times(other: List<Vector2>): List<Vector2> = other.map { transform2DXform(this, it) }

  /**
   * Returns the inverted version of this transform
   * (https://en.wikipedia.org/wiki/Invertible_matrix). Note: For this method to return correctly,
   * the transform's basis needs to be orthonormal (see `orthonormalized`). That means the basis
   * should only represent a rotation. If it does not, use `affine_inverse` instead.
   *
   * Generated from Godot docs: Transform2D.inverse
   */
  fun inverse(): Transform2D =
    builtinTransform2D(builtinReals(Transform2DMethods.inverse, builtinArg(), 6, emptyList()))

  /**
   * Returns the inverted version of this transform. Unlike `inverse`, this method works with almost
   * any basis, including non-uniform ones, but is slower. Note: For this method to return
   * correctly, the transform's basis needs to have a determinant that is not exactly `0.0` (see
   * `determinant`).
   *
   * Generated from Godot docs: Transform2D.affine_inverse
   */
  fun affineInverse(): Transform2D =
    builtinTransform2D(builtinReals(Transform2DMethods.affineInverse, builtinArg(), 6, emptyList()))

  /**
   * Returns this transform's rotation (in radians). This is equivalent to `x`'s angle (see
   * `Vector2.angle`).
   *
   * Generated from Godot docs: Transform2D.get_rotation
   */
  fun getRotation(): Double =
    builtinDouble(Transform2DMethods.getRotation, builtinArg(), emptyList())

  /**
   * Returns the length of both `x` and `y`, as a `Vector2`. If this transform's basis is not
   * skewed, this value is the scaling factor. It is not affected by rotation.
   *
   * Generated from Godot docs: Transform2D.get_scale
   */
  fun getScale(): Vector2 =
    builtinVector2(builtinReals(Transform2DMethods.getScale, builtinArg(), 2, emptyList()))

  /**
   * Returns this transform's skew (in radians).
   *
   * Generated from Godot docs: Transform2D.get_skew
   */
  fun getSkew(): Double = builtinDouble(Transform2DMethods.getSkew, builtinArg(), emptyList())

  /**
   * Returns a copy of this transform with its basis orthonormalized. An orthonormal basis is both
   * orthogonal (the axes are perpendicular to each other) and normalized (the axes have a length of
   * `1.0`), which also means it can only represent a rotation.
   *
   * Generated from Godot docs: Transform2D.orthonormalized
   */
  fun orthonormalized(): Transform2D =
    builtinTransform2D(
      builtinReals(Transform2DMethods.orthonormalized, builtinArg(), 6, emptyList())
    )

  /**
   * Returns a copy of this transform rotated by the given `angle` (in radians). If `angle` is
   * positive, the transform is rotated clockwise. This method is an optimized version of
   * multiplying the given transform `X` with a corresponding rotation transform `R` from the left,
   * i.e., `R * X`. This can be seen as transforming with respect to the global/parent frame.
   *
   * Generated from Godot docs: Transform2D.rotated
   */
  fun rotated(angle: Double): Transform2D =
    builtinTransform2D(
      builtinReals(Transform2DMethods.rotated, builtinArg(), 6, listOf(argReal(angle)))
    )

  /**
   * Returns a copy of the transform rotated by the given `angle` (in radians). This method is an
   * optimized version of multiplying the given transform `X` with a corresponding rotation
   * transform `R` from the right, i.e., `X * R`. This can be seen as transforming with respect to
   * the local frame.
   *
   * Generated from Godot docs: Transform2D.rotated_local
   */
  fun rotatedLocal(angle: Double): Transform2D =
    builtinTransform2D(
      builtinReals(Transform2DMethods.rotatedLocal, builtinArg(), 6, listOf(argReal(angle)))
    )

  /**
   * Returns a copy of the transform scaled by the given `scale` factor. This method is an optimized
   * version of multiplying the given transform `X` with a corresponding scaling transform `S` from
   * the left, i.e., `S * X`. This can be seen as transforming with respect to the global/parent
   * frame.
   *
   * Generated from Godot docs: Transform2D.scaled
   */
  fun scaled(scale: Vector2): Transform2D =
    builtinTransform2D(
      builtinReals(Transform2DMethods.scaled, builtinArg(), 6, listOf(scale.builtinArg()))
    )

  /**
   * Returns a copy of the transform scaled by the given `scale` factor. This method is an optimized
   * version of multiplying the given transform `X` with a corresponding scaling transform `S` from
   * the right, i.e., `X * S`. This can be seen as transforming with respect to the local frame.
   *
   * Generated from Godot docs: Transform2D.scaled_local
   */
  fun scaledLocal(scale: Vector2): Transform2D =
    builtinTransform2D(
      builtinReals(Transform2DMethods.scaledLocal, builtinArg(), 6, listOf(scale.builtinArg()))
    )

  /**
   * Returns a copy of the transform translated by the given `offset`. This method is an optimized
   * version of multiplying the given transform `X` with a corresponding translation transform `T`
   * from the left, i.e., `T * X`. This can be seen as transforming with respect to the
   * global/parent frame.
   *
   * Generated from Godot docs: Transform2D.translated
   */
  fun translated(offset: Vector2): Transform2D =
    builtinTransform2D(
      builtinReals(Transform2DMethods.translated, builtinArg(), 6, listOf(offset.builtinArg()))
    )

  /**
   * Returns a copy of the transform translated by the given `offset`. This method is an optimized
   * version of multiplying the given transform `X` with a corresponding translation transform `T`
   * from the right, i.e., `X * T`. This can be seen as transforming with respect to the local
   * frame.
   *
   * Generated from Godot docs: Transform2D.translated_local
   */
  fun translatedLocal(offset: Vector2): Transform2D =
    builtinTransform2D(
      builtinReals(Transform2DMethods.translatedLocal, builtinArg(), 6, listOf(offset.builtinArg()))
    )

  /**
   * Returns the determinant (https://en.wikipedia.org/wiki/Determinant) of this transform basis's
   * matrix. For advanced math, this number can be used to determine a few attributes: - If the
   * determinant is exactly `0.0`, the basis is not invertible (see `inverse`). - If the determinant
   * is a negative number, the basis represents a negative scale. Note: If the basis's scale is the
   * same for every axis, its determinant is always that scale by the power of 2.
   *
   * Generated from Godot docs: Transform2D.determinant
   */
  fun determinant(): Double =
    builtinDouble(Transform2DMethods.determinant, builtinArg(), emptyList())

  /**
   * Returns a copy of the `v` vector, transformed (multiplied) by the transform basis's matrix.
   * Unlike the multiplication operator (`*`), this method ignores the `origin`.
   *
   * Generated from Godot docs: Transform2D.basis_xform
   */
  fun basisXform(v: Vector2): Vector2 =
    builtinVector2(
      builtinReals(Transform2DMethods.basisXform, builtinArg(), 2, listOf(v.builtinArg()))
    )

  /**
   * Returns a copy of the `v` vector, transformed (multiplied) by the inverse transform basis's
   * matrix (see `inverse`). This method ignores the `origin`. Note: This method assumes that this
   * transform's basis is orthonormal (see `orthonormalized`). If the basis is not orthonormal,
   * `transform.affine_inverse().basis_xform(vector)` should be used instead (see `affine_inverse`).
   *
   * Generated from Godot docs: Transform2D.basis_xform_inv
   */
  fun basisXformInv(v: Vector2): Vector2 =
    builtinVector2(
      builtinReals(Transform2DMethods.basisXformInv, builtinArg(), 2, listOf(v.builtinArg()))
    )

  /**
   * Returns the result of the linear interpolation between this transform and `xform` by the given
   * `weight`. The `weight` should be between `0.0` and `1.0` (inclusive). Values outside this range
   * are allowed and can be used to perform extrapolation instead.
   *
   * Generated from Godot docs: Transform2D.interpolate_with
   */
  fun interpolateWith(xform: Transform2D, weight: Double): Transform2D =
    builtinTransform2D(
      builtinReals(
        Transform2DMethods.interpolateWith,
        builtinArg(),
        6,
        listOf(xform.builtinArg(), argReal(weight)),
      )
    )

  /**
   * Returns `true` if this transform's basis is conformal. A conformal basis is both orthogonal
   * (the axes are perpendicular to each other) and uniform (the axes share the same length). This
   * method can be especially useful during physics calculations.
   *
   * Generated from Godot docs: Transform2D.is_conformal
   */
  fun isConformal(): Boolean =
    builtinBool(Transform2DMethods.isConformal, builtinArg(), emptyList())

  /**
   * Returns `true` if this transform is finite, by calling `@GlobalScope.is_finite` on each
   * component.
   *
   * Generated from Godot docs: Transform2D.is_finite
   */
  fun isFinite(): Boolean = builtinBool(Transform2DMethods.isFinite, builtinArg(), emptyList())

  /**
   * Returns a copy of the transform rotated such that the rotated X-axis points towards the
   * `target` position, in global space.
   *
   * Generated from Godot docs: Transform2D.looking_at
   */
  fun lookingAt(target: Vector2 = Vector2(0.0, 0.0)): Transform2D =
    builtinTransform2D(
      builtinReals(Transform2DMethods.lookingAt, builtinArg(), 6, listOf(target.builtinArg()))
    )

  // ===== END GENERATED BUILTIN MEMBERS: Transform2D =====

  companion object {
    /**
     * The identity `Transform2D`. This is a transform with no translation, no rotation, and a scale
     * of `Vector2.ONE`. This also means that: - The `x` points right (`Vector2.RIGHT`); - The `y`
     * points down (`Vector2.DOWN`).
     *
     * Generated from Godot docs: Transform2D.IDENTITY
     */
    val IDENTITY = Transform2D(Vector2(1.0, 0.0), Vector2(0.0, 1.0), Vector2.ZERO)
  }
}

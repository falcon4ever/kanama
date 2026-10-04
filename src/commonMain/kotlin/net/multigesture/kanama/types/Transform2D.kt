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
  fun inverse(): Transform2D {
    val ix = Vector2.raw(x.rawX, y.rawX)
    val iy = Vector2.raw(x.rawY, y.rawY)
    val ox = -origin.rawX
    val oy = -origin.rawY
    return Transform2D(
      ix,
      iy,
      Vector2.raw(ix.rawX * ox + iy.rawX * oy, ix.rawY * ox + iy.rawY * oy),
    )
  }

  /**
   * Returns the inverted version of this transform. Unlike `inverse`, this method works with almost
   * any basis, including non-uniform ones, but is slower. Note: For this method to return
   * correctly, the transform's basis needs to have a determinant that is not exactly `0.0` (see
   * `determinant`).
   *
   * Generated from Godot docs: Transform2D.affine_inverse
   */
  fun affineInverse(): Transform2D {
    val f = builtinFrame()
    f.put(0, this)
    f.call(Transform2DMethods.affineInverse, 0)
    return f.retTransform2D()
  }

  /**
   * Returns this transform's rotation (in radians). This is equivalent to `x`'s angle (see
   * `Vector2.angle`).
   *
   * Generated from Godot docs: Transform2D.get_rotation
   */
  fun getRotation(): Double {
    val f = builtinFrame()
    f.put(0, this)
    f.call(Transform2DMethods.getRotation, 0)
    return f.retDouble()
  }

  /**
   * Returns the length of both `x` and `y`, as a `Vector2`. If this transform's basis is not
   * skewed, this value is the scaling factor. It is not affected by rotation.
   *
   * Generated from Godot docs: Transform2D.get_scale
   */
  fun getScale(): Vector2 {
    val f = builtinFrame()
    f.put(0, this)
    f.call(Transform2DMethods.getScale, 0)
    return f.retVector2()
  }

  /**
   * Returns this transform's skew (in radians).
   *
   * Generated from Godot docs: Transform2D.get_skew
   */
  fun getSkew(): Double {
    val f = builtinFrame()
    f.put(0, this)
    f.call(Transform2DMethods.getSkew, 0)
    return f.retDouble()
  }

  /**
   * Returns a copy of this transform with its basis orthonormalized. An orthonormal basis is both
   * orthogonal (the axes are perpendicular to each other) and normalized (the axes have a length of
   * `1.0`), which also means it can only represent a rotation.
   *
   * Generated from Godot docs: Transform2D.orthonormalized
   */
  fun orthonormalized(): Transform2D {
    val f = builtinFrame()
    f.put(0, this)
    f.call(Transform2DMethods.orthonormalized, 0)
    return f.retTransform2D()
  }

  /**
   * Returns a copy of this transform rotated by the given `angle` (in radians). If `angle` is
   * positive, the transform is rotated clockwise. This method is an optimized version of
   * multiplying the given transform `X` with a corresponding rotation transform `R` from the left,
   * i.e., `R * X`. This can be seen as transforming with respect to the global/parent frame.
   *
   * Generated from Godot docs: Transform2D.rotated
   */
  fun rotated(angle: Double): Transform2D {
    val f = builtinFrame()
    f.put(0, this)
    f.putDouble(1, angle)
    f.call(Transform2DMethods.rotated, 1)
    return f.retTransform2D()
  }

  /**
   * Returns a copy of the transform rotated by the given `angle` (in radians). This method is an
   * optimized version of multiplying the given transform `X` with a corresponding rotation
   * transform `R` from the right, i.e., `X * R`. This can be seen as transforming with respect to
   * the local frame.
   *
   * Generated from Godot docs: Transform2D.rotated_local
   */
  fun rotatedLocal(angle: Double): Transform2D {
    val f = builtinFrame()
    f.put(0, this)
    f.putDouble(1, angle)
    f.call(Transform2DMethods.rotatedLocal, 1)
    return f.retTransform2D()
  }

  /**
   * Returns a copy of the transform scaled by the given `scale` factor. This method is an optimized
   * version of multiplying the given transform `X` with a corresponding scaling transform `S` from
   * the left, i.e., `S * X`. This can be seen as transforming with respect to the global/parent
   * frame.
   *
   * Generated from Godot docs: Transform2D.scaled
   */
  fun scaled(scale: Vector2): Transform2D {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, scale)
    f.call(Transform2DMethods.scaled, 1)
    return f.retTransform2D()
  }

  /**
   * Returns a copy of the transform scaled by the given `scale` factor. This method is an optimized
   * version of multiplying the given transform `X` with a corresponding scaling transform `S` from
   * the right, i.e., `X * S`. This can be seen as transforming with respect to the local frame.
   *
   * Generated from Godot docs: Transform2D.scaled_local
   */
  fun scaledLocal(scale: Vector2): Transform2D {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, scale)
    f.call(Transform2DMethods.scaledLocal, 1)
    return f.retTransform2D()
  }

  /**
   * Returns a copy of the transform translated by the given `offset`. This method is an optimized
   * version of multiplying the given transform `X` with a corresponding translation transform `T`
   * from the left, i.e., `T * X`. This can be seen as transforming with respect to the
   * global/parent frame.
   *
   * Generated from Godot docs: Transform2D.translated
   */
  fun translated(offset: Vector2): Transform2D = Transform2D(x, y, origin + offset)

  /**
   * Returns a copy of the transform translated by the given `offset`. This method is an optimized
   * version of multiplying the given transform `X` with a corresponding translation transform `T`
   * from the right, i.e., `X * T`. This can be seen as transforming with respect to the local
   * frame.
   *
   * Generated from Godot docs: Transform2D.translated_local
   */
  fun translatedLocal(offset: Vector2): Transform2D = Transform2D(x, y, origin + basisXform(offset))

  /**
   * Returns the determinant (https://en.wikipedia.org/wiki/Determinant) of this transform basis's
   * matrix. For advanced math, this number can be used to determine a few attributes: - If the
   * determinant is exactly `0.0`, the basis is not invertible (see `inverse`). - If the determinant
   * is a negative number, the basis represents a negative scale. Note: If the basis's scale is the
   * same for every axis, its determinant is always that scale by the power of 2.
   *
   * Generated from Godot docs: Transform2D.determinant
   */
  fun determinant(): Double {
    val f = builtinFrame()
    f.put(0, this)
    f.call(Transform2DMethods.determinant, 0)
    return f.retDouble()
  }

  /**
   * Returns a copy of the `v` vector, transformed (multiplied) by the transform basis's matrix.
   * Unlike the multiplication operator (`*`), this method ignores the `origin`.
   *
   * Generated from Godot docs: Transform2D.basis_xform
   */
  fun basisXform(v: Vector2): Vector2 =
    Vector2.raw(x.rawX * v.rawX + y.rawX * v.rawY, x.rawY * v.rawX + y.rawY * v.rawY)

  /**
   * Returns a copy of the `v` vector, transformed (multiplied) by the inverse transform basis's
   * matrix (see `inverse`). This method ignores the `origin`. Note: This method assumes that this
   * transform's basis is orthonormal (see `orthonormalized`). If the basis is not orthonormal,
   * `transform.affine_inverse().basis_xform(vector)` should be used instead (see `affine_inverse`).
   *
   * Generated from Godot docs: Transform2D.basis_xform_inv
   */
  fun basisXformInv(v: Vector2): Vector2 =
    Vector2.raw(x.rawX * v.rawX + x.rawY * v.rawY, y.rawX * v.rawX + y.rawY * v.rawY)

  /**
   * Returns the result of the linear interpolation between this transform and `xform` by the given
   * `weight`. The `weight` should be between `0.0` and `1.0` (inclusive). Values outside this range
   * are allowed and can be used to perform extrapolation instead.
   *
   * Generated from Godot docs: Transform2D.interpolate_with
   */
  fun interpolateWith(xform: Transform2D, weight: Double): Transform2D {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, xform)
    f.putDouble(2, weight)
    f.call(Transform2DMethods.interpolateWith, 2)
    return f.retTransform2D()
  }

  /**
   * Returns `true` if this transform's basis is conformal. A conformal basis is both orthogonal
   * (the axes are perpendicular to each other) and uniform (the axes share the same length). This
   * method can be especially useful during physics calculations.
   *
   * Generated from Godot docs: Transform2D.is_conformal
   */
  fun isConformal(): Boolean {
    val f = builtinFrame()
    f.put(0, this)
    f.call(Transform2DMethods.isConformal, 0)
    return f.retBool()
  }

  /**
   * Returns `true` if this transform is finite, by calling `@GlobalScope.is_finite` on each
   * component.
   *
   * Generated from Godot docs: Transform2D.is_finite
   */
  fun isFinite(): Boolean {
    val f = builtinFrame()
    f.put(0, this)
    f.call(Transform2DMethods.isFinite, 0)
    return f.retBool()
  }

  /**
   * Returns a copy of the transform rotated such that the rotated X-axis points towards the
   * `target` position, in global space.
   *
   * Generated from Godot docs: Transform2D.looking_at
   */
  fun lookingAt(target: Vector2 = Vector2(0.0, 0.0)): Transform2D {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, target)
    f.call(Transform2DMethods.lookingAt, 1)
    return f.retTransform2D()
  }

  // ===== END GENERATED BUILTIN MEMBERS: Transform2D =====

  companion object {
    // ===== BEGIN GENERATED BUILTIN STATICS: Transform2D (generate_builtin_ops.py) =====
    /**
     * When any transform is multiplied by `FLIP_X`, it negates all components of the `x` axis (the
     * X column). When `FLIP_X` is multiplied by any transform, it negates the `Vector2.x` component
     * of all axes (the X row).
     *
     * Generated from Godot docs: Transform2D.FLIP_X
     */
    val FLIP_X: Transform2D = Transform2D(Vector2(-1.0, 0.0), Vector2(0.0, 1.0), Vector2(0.0, 0.0))

    /**
     * When any transform is multiplied by `FLIP_Y`, it negates all components of the `y` axis (the
     * Y column). When `FLIP_Y` is multiplied by any transform, it negates the `Vector2.y` component
     * of all axes (the Y row).
     *
     * Generated from Godot docs: Transform2D.FLIP_Y
     */
    val FLIP_Y: Transform2D = Transform2D(Vector2(1.0, 0.0), Vector2(0.0, -1.0), Vector2(0.0, 0.0))

    // ===== END GENERATED BUILTIN STATICS: Transform2D =====

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

package net.multigesture.kanama.types

import kotlin.jvm.JvmInline

/**
 * A 4×4 matrix for 3D projective transformations. Kanama value types are immutable snapshots;
 * assign a new value back to the Godot property after changing components.
 *
 * Generated from Godot docs: Projection
 */
data class Projection(
  /**
   * The projection matrix's X vector (column 0). Equivalent to array index `0`.
   *
   * Generated from Godot docs: Projection.x
   */
  val x: Vector4,
  /**
   * The projection matrix's Y vector (column 1). Equivalent to array index `1`.
   *
   * Generated from Godot docs: Projection.y
   */
  val y: Vector4,
  /**
   * The projection matrix's Z vector (column 2). Equivalent to array index `2`.
   *
   * Generated from Godot docs: Projection.z
   */
  val z: Vector4,
  /**
   * The projection matrix's W vector (column 3). Equivalent to array index `3`.
   *
   * Generated from Godot docs: Projection.w
   */
  val w: Vector4,
) {
  /** Godot's `str(p)`: the four columns, `[X: (1.0, 0.0, 0.0, 0.0), Y: …, W: …]`. */
  override fun toString(): String = "[X: $x, Y: $y, Z: $z, W: $w]"

  // ===== BEGIN GENERATED ENUMS: Projection (scripts/generate_api_wrapper.py — do not edit) =====
  /**
   * Godot's `Projection.Planes` enum as a typed value: `.value` is the raw number Godot uses, and
   * the companion holds the named values (`Projection.Planes.<NAME>`).
   *
   * Generated from Godot docs: Projection.Planes
   */
  @JvmInline
  value class Planes(override val value: Long) : net.multigesture.kanama.api.GodotEnumValue {
    companion object {
      /**
       * The index value of the projection's near clipping plane.
       *
       * Generated from Godot docs: Projection.PLANE_NEAR
       */
      val NEAR: Planes
        get() = Planes(0L)

      /**
       * The index value of the projection's far clipping plane.
       *
       * Generated from Godot docs: Projection.PLANE_FAR
       */
      val FAR: Planes
        get() = Planes(1L)

      /**
       * The index value of the projection's left clipping plane.
       *
       * Generated from Godot docs: Projection.PLANE_LEFT
       */
      val LEFT: Planes
        get() = Planes(2L)

      /**
       * The index value of the projection's top clipping plane.
       *
       * Generated from Godot docs: Projection.PLANE_TOP
       */
      val TOP: Planes
        get() = Planes(3L)

      /**
       * The index value of the projection's right clipping plane.
       *
       * Generated from Godot docs: Projection.PLANE_RIGHT
       */
      val RIGHT: Planes
        get() = Planes(4L)

      /**
       * The index value of the projection bottom clipping plane.
       *
       * Generated from Godot docs: Projection.PLANE_BOTTOM
       */
      val BOTTOM: Planes
        get() = Planes(5L)
    }
  }

  // ===== END GENERATED ENUMS: Projection =====

  /** Godot-style fuzzy compare: true if every column is approximately equal. */
  fun isEqualApprox(other: Projection): Boolean =
    x.isEqualApprox(other.x) &&
      y.isEqualApprox(other.y) &&
      z.isEqualApprox(other.z) &&
      w.isEqualApprox(other.w)

  /**
   * kanama convenience (Godot has no composite `is_zero_approx`): true if every column is
   * approximately zero.
   */
  fun isZeroApprox(): Boolean =
    x.isZeroApprox() && y.isZeroApprox() && z.isZeroApprox() && w.isZeroApprox()

  // ===== BEGIN GENERATED BUILTIN MEMBERS: Projection (generate_builtin_ops.py) =====
  operator fun times(other: Vector4): Vector4 = projectionXform(this, other)

  operator fun times(other: Projection): Projection = projectionMultiply(this, other)

  /**
   * Returns a scalar value that is the signed factor by which areas are scaled by this matrix. If
   * the sign is negative, the matrix flips the orientation of the area. The determinant can be used
   * to calculate the invertibility of a matrix or solve linear systems of equations involving the
   * matrix, among other applications.
   *
   * Generated from Godot docs: Projection.determinant
   */
  fun determinant(): Double {
    val f = builtinFrame()
    f.put(0, this)
    f.call(ProjectionMethods.determinant, 0)
    return f.retDouble()
  }

  /**
   * Returns a `Projection` with the near clipping distance adjusted to be `new_znear`. Note: The
   * original `Projection` must be a perspective projection.
   *
   * Generated from Godot docs: Projection.perspective_znear_adjusted
   */
  fun perspectiveZnearAdjusted(newZnear: Double): Projection {
    val f = builtinFrame()
    f.put(0, this)
    f.putDouble(1, newZnear)
    f.call(ProjectionMethods.perspectiveZnearAdjusted, 1)
    return f.retProjection()
  }

  /**
   * Returns the clipping plane of this `Projection` whose index is given by `plane`. `plane` should
   * be equal to one of `Planes.NEAR`, `Planes.FAR`, `Planes.LEFT`, `Planes.TOP`, `Planes.RIGHT`, or
   * `Planes.BOTTOM`.
   *
   * Generated from Godot docs: Projection.get_projection_plane
   */
  fun getProjectionPlane(plane: Long): Plane {
    val f = builtinFrame()
    f.put(0, this)
    f.putLong(1, plane)
    f.call(ProjectionMethods.getProjectionPlane, 1)
    return f.retPlane()
  }

  /**
   * Returns a copy of this `Projection` with the signs of the values of the Y column flipped.
   *
   * Generated from Godot docs: Projection.flipped_y
   */
  fun flippedY(): Projection {
    val f = builtinFrame()
    f.put(0, this)
    f.call(ProjectionMethods.flippedY, 0)
    return f.retProjection()
  }

  /**
   * Returns a `Projection` with the X and Y values from the given `Vector2` added to the first and
   * second values of the final column respectively.
   *
   * Generated from Godot docs: Projection.jitter_offseted
   */
  fun jitterOffseted(offset: Vector2): Projection {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, offset)
    f.call(ProjectionMethods.jitterOffseted, 1)
    return f.retProjection()
  }

  /**
   * Returns the distance for this `Projection` beyond which positions are clipped.
   *
   * Generated from Godot docs: Projection.get_z_far
   */
  fun getZFar(): Double {
    val f = builtinFrame()
    f.put(0, this)
    f.call(ProjectionMethods.getZFar, 0)
    return f.retDouble()
  }

  /**
   * Returns the distance for this `Projection` before which positions are clipped.
   *
   * Generated from Godot docs: Projection.get_z_near
   */
  fun getZNear(): Double {
    val f = builtinFrame()
    f.put(0, this)
    f.call(ProjectionMethods.getZNear, 0)
    return f.retDouble()
  }

  /**
   * Returns the X:Y aspect ratio of this `Projection`'s viewport.
   *
   * Generated from Godot docs: Projection.get_aspect
   */
  fun getAspect(): Double {
    val f = builtinFrame()
    f.put(0, this)
    f.call(ProjectionMethods.getAspect, 0)
    return f.retDouble()
  }

  /**
   * Returns the horizontal field of view of the projection (in degrees).
   *
   * Generated from Godot docs: Projection.get_fov
   */
  fun getFov(): Double {
    val f = builtinFrame()
    f.put(0, this)
    f.call(ProjectionMethods.getFov, 0)
    return f.retDouble()
  }

  /**
   * Returns `true` if this `Projection` performs an orthogonal projection.
   *
   * Generated from Godot docs: Projection.is_orthogonal
   */
  fun isOrthogonal(): Boolean {
    val f = builtinFrame()
    f.put(0, this)
    f.call(ProjectionMethods.isOrthogonal, 0)
    return f.retBool()
  }

  /**
   * Returns the dimensions of the viewport plane that this `Projection` projects positions onto,
   * divided by two.
   *
   * Generated from Godot docs: Projection.get_viewport_half_extents
   */
  fun getViewportHalfExtents(): Vector2 {
    val f = builtinFrame()
    f.put(0, this)
    f.call(ProjectionMethods.getViewportHalfExtents, 0)
    return f.retVector2()
  }

  /**
   * Returns the dimensions of the far clipping plane of the projection, divided by two.
   *
   * Generated from Godot docs: Projection.get_far_plane_half_extents
   */
  fun getFarPlaneHalfExtents(): Vector2 {
    val f = builtinFrame()
    f.put(0, this)
    f.call(ProjectionMethods.getFarPlaneHalfExtents, 0)
    return f.retVector2()
  }

  /**
   * Returns a `Projection` that performs the inverse of this `Projection`'s projective
   * transformation.
   *
   * Generated from Godot docs: Projection.inverse
   */
  fun inverse(): Projection {
    val f = builtinFrame()
    f.put(0, this)
    f.call(ProjectionMethods.inverse, 0)
    return f.retProjection()
  }

  /**
   * Returns `for_pixel_width` divided by the viewport's width measured in meters on the near plane,
   * after this `Projection` is applied.
   *
   * Generated from Godot docs: Projection.get_pixels_per_meter
   */
  fun getPixelsPerMeter(forPixelWidth: Long): Long {
    val f = builtinFrame()
    f.put(0, this)
    f.putLong(1, forPixelWidth)
    f.call(ProjectionMethods.getPixelsPerMeter, 1)
    return f.retLong()
  }

  /**
   * Returns the factor by which the visible level of detail is scaled by this `Projection`.
   *
   * Generated from Godot docs: Projection.get_lod_multiplier
   */
  fun getLodMultiplier(): Double {
    val f = builtinFrame()
    f.put(0, this)
    f.call(ProjectionMethods.getLodMultiplier, 0)
    return f.retDouble()
  }

  // ===== END GENERATED BUILTIN MEMBERS: Projection =====

  companion object {
    // ===== BEGIN GENERATED BUILTIN STATICS: Projection (generate_builtin_ops.py) =====
    /**
     * Creates a new `Projection` that projects positions from a depth range of `-1` to `1` to one
     * that ranges from `0` to `1`, and flips the projected positions vertically, according to
     * `flip_y`.
     *
     * Generated from Godot docs: Projection.create_depth_correction
     */
    fun createDepthCorrection(flipY: Boolean): Projection {
      val f = builtinFrame()
      f.putBool(1, flipY)
      f.callStatic(ProjectionMethods.createDepthCorrection, 1)
      return f.retProjection()
    }

    /**
     * Creates a new `Projection` that projects positions into the given `Rect2`.
     *
     * Generated from Godot docs: Projection.create_light_atlas_rect
     */
    fun createLightAtlasRect(rect: Rect2): Projection {
      val f = builtinFrame()
      f.put(1, rect)
      f.callStatic(ProjectionMethods.createLightAtlasRect, 1)
      return f.retProjection()
    }

    /**
     * Creates a new `Projection` that projects positions using a perspective projection with the
     * given Y-axis field of view (in degrees), X:Y aspect ratio, and clipping planes. `flip_fov`
     * determines whether the projection's field of view is flipped over its diagonal.
     *
     * Generated from Godot docs: Projection.create_perspective
     */
    fun createPerspective(
      fovy: Double,
      aspect: Double,
      zNear: Double,
      zFar: Double,
      flipFov: Boolean = false,
    ): Projection {
      val f = builtinFrame()
      f.putDouble(1, fovy)
      f.putDouble(2, aspect)
      f.putDouble(3, zNear)
      f.putDouble(4, zFar)
      f.putBool(5, flipFov)
      f.callStatic(ProjectionMethods.createPerspective, 5)
      return f.retProjection()
    }

    /**
     * Creates a new `Projection` that projects positions using a perspective projection with the
     * given Y-axis field of view (in degrees), X:Y aspect ratio, and clipping distances. The
     * projection is adjusted for a head-mounted display with the given distance between eyes and
     * distance to a point that can be focused on. `eye` creates the projection for the left eye
     * when set to 1, or the right eye when set to 2. `flip_fov` determines whether the projection's
     * field of view is flipped over its diagonal.
     *
     * Generated from Godot docs: Projection.create_perspective_hmd
     */
    fun createPerspectiveHmd(
      fovy: Double,
      aspect: Double,
      zNear: Double,
      zFar: Double,
      flipFov: Boolean,
      eye: Long,
      intraocularDist: Double,
      convergenceDist: Double,
    ): Projection {
      val f = builtinFrame()
      f.putDouble(1, fovy)
      f.putDouble(2, aspect)
      f.putDouble(3, zNear)
      f.putDouble(4, zFar)
      f.putBool(5, flipFov)
      f.putLong(6, eye)
      f.putDouble(7, intraocularDist)
      f.putDouble(8, convergenceDist)
      f.callStatic(ProjectionMethods.createPerspectiveHmd, 8)
      return f.retProjection()
    }

    /**
     * Creates a new `Projection` for projecting positions onto a head-mounted display with the
     * given X:Y aspect ratio, distance between eyes, display width, distance to lens, oversampling
     * factor, and depth clipping planes. `eye` creates the projection for the left eye when set to
     * 1, or the right eye when set to 2.
     *
     * Generated from Godot docs: Projection.create_for_hmd
     */
    fun createForHmd(
      eye: Long,
      aspect: Double,
      intraocularDist: Double,
      displayWidth: Double,
      displayToLens: Double,
      oversample: Double,
      zNear: Double,
      zFar: Double,
    ): Projection {
      val f = builtinFrame()
      f.putLong(1, eye)
      f.putDouble(2, aspect)
      f.putDouble(3, intraocularDist)
      f.putDouble(4, displayWidth)
      f.putDouble(5, displayToLens)
      f.putDouble(6, oversample)
      f.putDouble(7, zNear)
      f.putDouble(8, zFar)
      f.callStatic(ProjectionMethods.createForHmd, 8)
      return f.retProjection()
    }

    /**
     * Creates a new `Projection` that projects positions using an orthogonal projection with the
     * given clipping planes.
     *
     * Generated from Godot docs: Projection.create_orthogonal
     */
    fun createOrthogonal(
      left: Double,
      right: Double,
      bottom: Double,
      top: Double,
      zNear: Double,
      zFar: Double,
    ): Projection {
      val f = builtinFrame()
      f.putDouble(1, left)
      f.putDouble(2, right)
      f.putDouble(3, bottom)
      f.putDouble(4, top)
      f.putDouble(5, zNear)
      f.putDouble(6, zFar)
      f.callStatic(ProjectionMethods.createOrthogonal, 6)
      return f.retProjection()
    }

    /**
     * Creates a new `Projection` that projects positions using an orthogonal projection with the
     * given size, X:Y aspect ratio, and clipping planes. `flip_fov` determines whether the
     * projection's field of view is flipped over its diagonal.
     *
     * Generated from Godot docs: Projection.create_orthogonal_aspect
     */
    fun createOrthogonalAspect(
      size: Double,
      aspect: Double,
      zNear: Double,
      zFar: Double,
      flipFov: Boolean = false,
    ): Projection {
      val f = builtinFrame()
      f.putDouble(1, size)
      f.putDouble(2, aspect)
      f.putDouble(3, zNear)
      f.putDouble(4, zFar)
      f.putBool(5, flipFov)
      f.callStatic(ProjectionMethods.createOrthogonalAspect, 5)
      return f.retProjection()
    }

    /**
     * Creates a new `Projection` that projects positions in a frustum with the given clipping
     * planes.
     *
     * Generated from Godot docs: Projection.create_frustum
     */
    fun createFrustum(
      left: Double,
      right: Double,
      bottom: Double,
      top: Double,
      zNear: Double,
      zFar: Double,
    ): Projection {
      val f = builtinFrame()
      f.putDouble(1, left)
      f.putDouble(2, right)
      f.putDouble(3, bottom)
      f.putDouble(4, top)
      f.putDouble(5, zNear)
      f.putDouble(6, zFar)
      f.callStatic(ProjectionMethods.createFrustum, 6)
      return f.retProjection()
    }

    /**
     * Creates a new `Projection` that projects positions in a frustum with the given size, X:Y
     * aspect ratio, offset, and clipping planes. `flip_fov` determines whether the projection's
     * field of view is flipped over its diagonal.
     *
     * Generated from Godot docs: Projection.create_frustum_aspect
     */
    fun createFrustumAspect(
      size: Double,
      aspect: Double,
      offset: Vector2,
      zNear: Double,
      zFar: Double,
      flipFov: Boolean = false,
    ): Projection {
      val f = builtinFrame()
      f.putDouble(1, size)
      f.putDouble(2, aspect)
      f.put(3, offset)
      f.putDouble(4, zNear)
      f.putDouble(5, zFar)
      f.putBool(6, flipFov)
      f.callStatic(ProjectionMethods.createFrustumAspect, 6)
      return f.retProjection()
    }

    /**
     * Creates a new `Projection` that scales a given projection to fit around a given `AABB` in
     * projection space.
     *
     * Generated from Godot docs: Projection.create_fit_aabb
     */
    fun createFitAabb(aabb: AABB): Projection {
      val f = builtinFrame()
      f.put(1, aabb)
      f.callStatic(ProjectionMethods.createFitAabb, 1)
      return f.retProjection()
    }

    /**
     * Returns the vertical field of view of the projection (in degrees) associated with the given
     * horizontal field of view (in degrees) and aspect ratio. Note: Unlike most methods of
     * `Projection`, `aspect` is expected to be 1 divided by the X:Y aspect ratio.
     *
     * Generated from Godot docs: Projection.get_fovy
     */
    fun getFovy(fovx: Double, aspect: Double): Double {
      val f = builtinFrame()
      f.putDouble(1, fovx)
      f.putDouble(2, aspect)
      f.callStatic(ProjectionMethods.getFovy, 2)
      return f.retDouble()
    }

    // ===== END GENERATED BUILTIN STATICS: Projection =====

    /**
     * A `Projection` with no transformation defined. When applied to other data structures, no
     * transformation is performed.
     *
     * Generated from Godot docs: Projection.IDENTITY
     */
    val IDENTITY =
      Projection(
        Vector4(1.0, 0.0, 0.0, 0.0),
        Vector4(0.0, 1.0, 0.0, 0.0),
        Vector4(0.0, 0.0, 1.0, 0.0),
        Vector4(0.0, 0.0, 0.0, 1.0),
      )

    /**
     * A `Projection` with all values initialized to 0. When applied to other data structures, they
     * will be zeroed.
     *
     * Generated from Godot docs: Projection.ZERO
     */
    val ZERO = Projection(Vector4.ZERO, Vector4.ZERO, Vector4.ZERO, Vector4.ZERO)
  }
}

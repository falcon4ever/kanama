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

  companion object {
    /**
     * A `Projection` with no transformation defined. When applied to other data structures, no
     * transformation is performed.
     *
     * Generated from Godot docs: Projection.IDENTITY
     */
    val IDENTITY =
      Projection(
        Vector4(1f, 0f, 0f, 0f),
        Vector4(0f, 1f, 0f, 0f),
        Vector4(0f, 0f, 1f, 0f),
        Vector4(0f, 0f, 0f, 1f),
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

package net.multigesture.kanama.types

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
  }
}

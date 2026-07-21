package net.multigesture.kanama.types

/**
 * A 2×3 matrix representing a 2D transformation: two basis columns ([x], [y]) plus an [origin]
 * (translation). Kanama value types are immutable snapshots; assign a new value back to the Godot
 * property after changing components.
 *
 * iOS marshalling note: at ptrcall a Transform2D is 6 `real_t` (float32 on single-precision iOS) —
 * the three columns x, y, origin, each a [Vector2] — laid out by the generated ObjectCalls helpers.
 * Unlike [Basis]/[Transform3D] the columns ARE the stored axes, so there is no column-major
 * reshuffle.
 *
 * Generated from Godot docs: Transform2D
 */
data class Transform2D(val x: Vector2, val y: Vector2, val origin: Vector2) {
  /** Godot-style fuzzy compare: true if every column is approximately equal. */
  fun isEqualApprox(other: Transform2D): Boolean =
    x.isEqualApprox(other.x) && y.isEqualApprox(other.y) && origin.isEqualApprox(other.origin)

  /**
   * kanama convenience (Godot has no composite `is_zero_approx`): true if every column is
   * approximately zero.
   */
  fun isZeroApprox(): Boolean = x.isZeroApprox() && y.isZeroApprox() && origin.isZeroApprox()

  companion object {
    /**
     * The identity `Transform2D`: no translation, no rotation, scale of `Vector2.ONE` (`x` points
     * right, `y` points down).
     */
    val IDENTITY = Transform2D(Vector2(1.0, 0.0), Vector2(0.0, 1.0), Vector2.ZERO)
  }
}

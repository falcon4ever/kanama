package net.multigesture.kanama.types

/**
 * A 2×3 matrix representing a 2D transformation. Kanama value types are immutable snapshots;
 * assign a new value back to the Godot property after changing components.
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
    /** Godot-style fuzzy compare: true if every column is approximately equal. */
    /**
     * Returns `true` if this transform and `xform` are approximately equal, by running
     * `@GlobalScope.is_equal_approx` on each component.
     *
     * Generated from Godot docs: Transform2D.is_equal_approx
     */
    fun isEqualApprox(other: Transform2D): Boolean =
        x.isEqualApprox(other.x) && y.isEqualApprox(other.y) && origin.isEqualApprox(other.origin)

    /** kanama convenience (Godot has no composite `is_zero_approx`): true if every column is approximately zero. */
    fun isZeroApprox(): Boolean = x.isZeroApprox() && y.isZeroApprox() && origin.isZeroApprox()

    companion object {
        /**
         * The identity `Transform2D`. This is a transform with no translation, no rotation, and a scale of
         * `Vector2.ONE`. This also means that: - The `x` points right (`Vector2.RIGHT`); - The `y` points
         * down (`Vector2.DOWN`).
         *
         * Generated from Godot docs: Transform2D.IDENTITY
         */
        val IDENTITY = Transform2D(
            Vector2(1f, 0f),
            Vector2(0f, 1f),
            Vector2.ZERO,
        )
    }
}

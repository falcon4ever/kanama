package net.multigesture.kanama.types

data class Rect2(
    val position: Vector2,
    val size: Vector2,
) {
    /** Godot-style fuzzy compare: true if position and size are approximately equal. */
    fun isEqualApprox(other: Rect2): Boolean =
        position.isEqualApprox(other.position) && size.isEqualApprox(other.size)

    /** kanama convenience (Godot has no composite `is_zero_approx`): true if position and size are approximately zero. */
    fun isZeroApprox(): Boolean = position.isZeroApprox() && size.isZeroApprox()

    val end: Vector2 get() = position + size

    companion object {
        val ZERO = Rect2(Vector2.ZERO, Vector2.ZERO)
    }
}

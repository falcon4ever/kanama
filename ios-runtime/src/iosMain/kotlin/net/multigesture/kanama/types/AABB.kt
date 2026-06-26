package net.multigesture.kanama.types

/**
 * An axis-aligned bounding box in 3D space: an origin [position] and a [size]. Kanama
 * value types are immutable snapshots.
 *
 * iOS marshalling note: at ptrcall an AABB is 6 `real_t` (float32 on single-precision
 * iOS) — the 3 position components followed by the 3 size components — POD passthrough.
 */
data class AABB(
    val position: Vector3,
    val size: Vector3,
) {
    /** Godot-style fuzzy compare: true if position and size are approximately equal. */
    fun isEqualApprox(other: AABB): Boolean =
        position.isEqualApprox(other.position) && size.isEqualApprox(other.size)

    /** kanama convenience (Godot has no composite `is_zero_approx`): true if position and size are approximately zero. */
    fun isZeroApprox(): Boolean = position.isZeroApprox() && size.isZeroApprox()

    companion object {
        val ZERO = AABB(Vector3.ZERO, Vector3.ZERO)
    }
}

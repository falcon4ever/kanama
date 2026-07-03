@file:Suppress("REDUNDANT_CALL_OF_CONVERSION_METHOD")

package net.multigesture.kanama.types

import net.multigesture.kanama.binding.runtime.BuiltinTypes
import net.multigesture.kanama.binding.runtime.VariantType
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.JAVA_DOUBLE
import kotlin.math.atan2
import kotlin.math.sqrt

private const val LERP_HASH = 4250033116L
private const val LIMIT_LENGTH_HASH = 2544004089L
private const val ROTATED_HASH = 2544004089L
private const val CLAMP_HASH = 318031021L

/**
 * A 2D vector using floating-point coordinates. Kanama value types are immutable snapshots; assign
 * a new value back to the Godot property after changing components.
 *
 * Generated from Godot docs: Vector2
 */
data class Vector2(
    /**
     * The vector's X component. Also accessible by using the index position `[0]`.
     *
     * Generated from Godot docs: Vector2.x
     */
    val x: real_t,
    /**
     * The vector's Y component. Also accessible by using the index position `[1]`.
     *
     * Generated from Godot docs: Vector2.y
     */
    val y: real_t,
) {
    constructor(x: Number, y: Number) : this(GodotReal.fromNumber(x), GodotReal.fromNumber(y))

    // Match GDScript/C# `==`: signed zero equal (-0.0 == 0.0), NaN reflexive. See
    // wrapper-coverage-roadmap.md. hashCode canonicalizes signed zero so equal vectors hash equal.
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Vector2) return false
        return (x == other.x || (x.isNaN() && other.x.isNaN())) &&
            (y == other.y || (y.isNaN() && other.y.isNaN()))
    }

    override fun hashCode(): Int {
        var result = (x + 0.0f).hashCode()
        result = 31 * result + (y + 0.0f).hashCode()
        return result
    }

    /** Godot `Vector2.is_equal_approx`: per-component fuzzy compare (CMP_EPSILON tolerance). */
    /**
     * Returns `true` if this vector and `to` are approximately equal, by running
     * `@GlobalScope.is_equal_approx` on each component.
     *
     * Generated from Godot docs: Vector2.is_equal_approx
     */
    fun isEqualApprox(other: Vector2): Boolean =
        isEqualApprox(x, other.x) && isEqualApprox(y, other.y)

    /** Godot `Vector2.is_zero_approx`: true if both components are approximately zero. */
    /**
     * Returns `true` if this vector's values are approximately zero, by running
     * `@GlobalScope.is_zero_approx` on each component. This method is faster than using
     * `is_equal_approx` with one value as a zero vector.
     *
     * Generated from Godot docs: Vector2.is_zero_approx
     */
    fun isZeroApprox(): Boolean = isZeroApprox(x) && isZeroApprox(y)

    operator fun plus(other: Vector2): Vector2 =
        Vector2(x + other.x, y + other.y)

    operator fun minus(other: Vector2): Vector2 =
        Vector2(x - other.x, y - other.y)

    operator fun times(scale: Number): Vector2 =
        Vector2(x.toDouble() * scale.toDouble(), y.toDouble() * scale.toDouble())

    operator fun times(scale: Double): Vector2 =
        Vector2(GodotReal.fromDouble(x.toDouble() * scale), GodotReal.fromDouble(y.toDouble() * scale))

    operator fun times(scale: Float): Vector2 =
        Vector2(
            GodotReal.fromDouble(x.toDouble() * scale.toDouble()),
            GodotReal.fromDouble(y.toDouble() * scale.toDouble()),
        )

    operator fun div(scale: Number): Vector2 =
        Vector2(x.toDouble() / scale.toDouble(), y.toDouble() / scale.toDouble())

    operator fun div(scale: Double): Vector2 =
        Vector2(GodotReal.fromDouble(x.toDouble() / scale), GodotReal.fromDouble(y.toDouble() / scale))

    operator fun div(scale: Float): Vector2 =
        Vector2(
            GodotReal.fromDouble(x.toDouble() / scale.toDouble()),
            GodotReal.fromDouble(y.toDouble() / scale.toDouble()),
        )

    operator fun unaryMinus(): Vector2 =
        Vector2(-x, -y)

    /**
     * Returns the squared length (squared magnitude) of this vector. This method runs faster than
     * `length`, so prefer it if you need to compare vectors or need the squared distance for some
     * formula.
     *
     * Generated from Godot docs: Vector2.length_squared
     */
    fun lengthSquared(): Double =
        (x * x + y * y).toDouble()

    /**
     * Returns the length (magnitude) of this vector.
     *
     * Generated from Godot docs: Vector2.length
     */
    fun length(): Double =
        sqrt(lengthSquared())

    /**
     * Returns the result of scaling the vector to unit length. Equivalent to `v / v.length()`. Returns
     * `(0, 0)` if `v.length() == 0`. See also `is_normalized`. Note: This function may return
     * incorrect values if the input vector length is near zero.
     *
     * Generated from Godot docs: Vector2.normalized
     */
    fun normalized(): Vector2 {
        val len = length()
        return if (len == 0.0) ZERO else this / len
    }

    /**
     * Returns the dot product of this vector and `with`. This can be used to compare the angle between
     * two vectors. For example, this can be used to determine whether an enemy is facing the player.
     * The dot product will be `0` for a right angle (90 degrees), greater than 0 for angles narrower
     * than 90 degrees and lower than 0 for angles wider than 90 degrees. When using unit (normalized)
     * vectors, the result will always be between `-1.0` (180 degree angle) when the vectors are facing
     * opposite directions, and `1.0` (0 degree angle) when the vectors are aligned. Note: `a.dot(b)`
     * is equivalent to `b.dot(a)`.
     *
     * Generated from Godot docs: Vector2.dot
     */
    fun dot(other: Vector2): Double =
        (x * other.x + y * other.y).toDouble()

    /**
     * Returns the Euclidean distance (https://en.wikipedia.org/wiki/Euclidean_distance) between this
     * vector and `to`.
     *
     * Generated from Godot docs: Vector2.distance_to
     */
    fun distanceTo(other: Vector2): Double =
        (this - other).length()

    /**
     * Returns the squared Euclidean distance (https://en.wikipedia.org/wiki/Euclidean_distance)
     * between this vector and `to`. This method runs faster than `distance_to`, so prefer it if you
     * need to compare vectors or need the squared distance for some formula.
     *
     * Generated from Godot docs: Vector2.distance_squared_to
     */
    fun distanceSquaredTo(other: Vector2): Double =
        (this - other).lengthSquared()

    /**
     * Returns this vector's angle with respect to the positive X axis, or `(1, 0)` vector, in radians.
     * For example, `Vector2.RIGHT.angle()` will return zero, `Vector2.DOWN.angle()` will return `PI /
     * 2` (a quarter turn, or 90 degrees), and `Vector2(1, -1).angle()` will return `-PI / 4` (a
     * negative eighth turn, or -45 degrees). This is equivalent to calling `@GlobalScope.atan2` with
     * `y` and `x`. Illustration of the returned angle.
     * (https://raw.githubusercontent.com/godotengine/godot-docs/master/img/vector2_angle.png)
     *
     * Generated from Godot docs: Vector2.angle
     */
    fun angle(): Double =
        atan2(y.toDouble(), x.toDouble())

    /**
     * Returns the result of the linear interpolation between this vector and `to` by amount `weight`.
     * `weight` is on the range of `0.0` to `1.0`, representing the amount of interpolation.
     *
     * Generated from Godot docs: Vector2.lerp
     */
    fun lerp(to: Vector2, weight: Double): Vector2 {
        Arena.ofConfined().use { arena ->
            val base = arena.allocate(GodotReal.SIZE_BYTES * 2, GodotReal.ALIGN_BYTES)
            val toArg = arena.allocate(GodotReal.SIZE_BYTES * 2, GodotReal.ALIGN_BYTES)
            val weightArg = arena.allocate(JAVA_DOUBLE)
            val ret = arena.allocate(GodotReal.SIZE_BYTES * 2, GodotReal.ALIGN_BYTES)
            writeTo(base)
            to.writeTo(toArg)
            weightArg.set(JAVA_DOUBLE, 0, weight)
            BuiltinTypes.call(
                type = VariantType.VECTOR2,
                method = "lerp",
                hash = LERP_HASH,
                base = base,
                args = listOf(toArg, weightArg),
                rReturn = ret,
            )
            return readFrom(ret)
        }
    }

    /**
     * Returns the vector with a maximum length by limiting its length to `length`. If the vector is
     * non-finite, the result is undefined.
     *
     * Generated from Godot docs: Vector2.limit_length
     */
    fun limitLength(maxLength: Double): Vector2 {
        Arena.ofConfined().use { arena ->
            val base = arena.allocate(GodotReal.SIZE_BYTES * 2, GodotReal.ALIGN_BYTES)
            val lengthArg = arena.allocate(JAVA_DOUBLE)
            val ret = arena.allocate(GodotReal.SIZE_BYTES * 2, GodotReal.ALIGN_BYTES)
            writeTo(base)
            lengthArg.set(JAVA_DOUBLE, 0, maxLength)
            BuiltinTypes.call(
                type = VariantType.VECTOR2,
                method = "limit_length",
                hash = LIMIT_LENGTH_HASH,
                base = base,
                args = listOf(lengthArg),
                rReturn = ret,
            )
            return readFrom(ret)
        }
    }

    /**
     * Returns the result of rotating this vector by `angle` (in radians). See also
     * `@GlobalScope.deg_to_rad`.
     *
     * Generated from Godot docs: Vector2.rotated
     */
    fun rotated(angle: Double): Vector2 {
        Arena.ofConfined().use { arena ->
            val base = arena.allocate(GodotReal.SIZE_BYTES * 2, GodotReal.ALIGN_BYTES)
            val angleArg = arena.allocate(JAVA_DOUBLE)
            val ret = arena.allocate(GodotReal.SIZE_BYTES * 2, GodotReal.ALIGN_BYTES)
            writeTo(base)
            angleArg.set(JAVA_DOUBLE, 0, angle)
            BuiltinTypes.call(
                type = VariantType.VECTOR2,
                method = "rotated",
                hash = ROTATED_HASH,
                base = base,
                args = listOf(angleArg),
                rReturn = ret,
            )
            return readFrom(ret)
        }
    }

    /**
     * Returns a new vector with all components clamped between the components of `min` and `max`, by
     * running `@GlobalScope.clamp` on each component.
     *
     * Generated from Godot docs: Vector2.clamp
     */
    fun clamp(min: Vector2, max: Vector2): Vector2 {
        Arena.ofConfined().use { arena ->
            val base = arena.allocate(GodotReal.SIZE_BYTES * 2, GodotReal.ALIGN_BYTES)
            val minArg = arena.allocate(GodotReal.SIZE_BYTES * 2, GodotReal.ALIGN_BYTES)
            val maxArg = arena.allocate(GodotReal.SIZE_BYTES * 2, GodotReal.ALIGN_BYTES)
            val ret = arena.allocate(GodotReal.SIZE_BYTES * 2, GodotReal.ALIGN_BYTES)
            writeTo(base)
            min.writeTo(minArg)
            max.writeTo(maxArg)
            BuiltinTypes.call(
                type = VariantType.VECTOR2,
                method = "clamp",
                hash = CLAMP_HASH,
                base = base,
                args = listOf(minArg, maxArg),
                rReturn = ret,
            )
            return readFrom(ret)
        }
    }

    fun withX(value: Number): Vector2 = Vector2(value, y)
    fun withY(value: Number): Vector2 = Vector2(x, value)

    private fun writeTo(dest: MemorySegment) {
        GodotReal.writeIndex(dest, 0, x)
        GodotReal.writeIndex(dest, 1, y)
    }

    companion object {
        private fun readFrom(src: MemorySegment): Vector2 =
            Vector2(
                GodotReal.readIndex(src, 0),
                GodotReal.readIndex(src, 1),
            )

        /**
         * Zero vector, a vector with all components set to `0`.
         *
         * Generated from Godot docs: Vector2.ZERO
         */
        val ZERO = Vector2(0f, 0f)
        /**
         * One vector, a vector with all components set to `1`.
         *
         * Generated from Godot docs: Vector2.ONE
         */
        val ONE = Vector2(1f, 1f)
        /**
         * Up unit vector. Y is down in 2D, so this vector points -Y.
         *
         * Generated from Godot docs: Vector2.UP
         */
        val UP = Vector2(0f, -1f)
        /**
         * Down unit vector. Y is down in 2D, so this vector points +Y.
         *
         * Generated from Godot docs: Vector2.DOWN
         */
        val DOWN = Vector2(0f, 1f)
        /**
         * Left unit vector. Represents the direction of left.
         *
         * Generated from Godot docs: Vector2.LEFT
         */
        val LEFT = Vector2(-1f, 0f)
        /**
         * Right unit vector. Represents the direction of right.
         *
         * Generated from Godot docs: Vector2.RIGHT
         */
        val RIGHT = Vector2(1f, 0f)
    }
}

package net.multigesture.kanama.types

import net.multigesture.kanama.binding.runtime.BuiltinTypes
import net.multigesture.kanama.binding.runtime.VariantType
import java.lang.foreign.Arena
import java.lang.foreign.ValueLayout.JAVA_DOUBLE
import kotlin.math.sqrt

private const val SLERP_HASH = 1773590316L

/**
 * A unit quaternion used for representing 3D rotations. Kanama value types are immutable
 * snapshots; assign a new value back to the Godot property after changing components.
 *
 * Generated from Godot docs: Quaternion
 */
data class Quaternion(
    /**
     * X component of the quaternion. This is the value along the "imaginary" `i` axis. Note:
     * Quaternion components should usually not be manipulated directly.
     *
     * Generated from Godot docs: Quaternion.x
     */
    val x: real_t,
    /**
     * Y component of the quaternion. This is the value along the "imaginary" `j` axis. Note:
     * Quaternion components should usually not be manipulated directly.
     *
     * Generated from Godot docs: Quaternion.y
     */
    val y: real_t,
    /**
     * Z component of the quaternion. This is the value along the "imaginary" `k` axis. Note:
     * Quaternion components should usually not be manipulated directly.
     *
     * Generated from Godot docs: Quaternion.z
     */
    val z: real_t,
    /**
     * W component of the quaternion. This is the "real" part. Note: Quaternion components should
     * usually not be manipulated directly.
     *
     * Generated from Godot docs: Quaternion.w
     */
    val w: real_t,
) {
    constructor(x: Number, y: Number, z: Number, w: Number) :
        this(
            GodotReal.fromNumber(x),
            GodotReal.fromNumber(y),
            GodotReal.fromNumber(z),
            GodotReal.fromNumber(w),
        )

    // Match GDScript/C# `==`: signed zero equal (-0.0 == 0.0), NaN reflexive. See
    // wrapper-coverage-roadmap.md. hashCode canonicalizes signed zero so equal quaternions hash equal.
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Quaternion) return false
        return (x == other.x || (x.isNaN() && other.x.isNaN())) &&
            (y == other.y || (y.isNaN() && other.y.isNaN())) &&
            (z == other.z || (z.isNaN() && other.z.isNaN())) &&
            (w == other.w || (w.isNaN() && other.w.isNaN()))
    }

    override fun hashCode(): Int {
        var result = (x + 0.0f).hashCode()
        result = 31 * result + (y + 0.0f).hashCode()
        result = 31 * result + (z + 0.0f).hashCode()
        result = 31 * result + (w + 0.0f).hashCode()
        return result
    }

    /** Godot `Quaternion.is_equal_approx`: per-component fuzzy compare (CMP_EPSILON tolerance). */
    /**
     * Returns `true` if this quaternion and `to` are approximately equal, by calling
     * `@GlobalScope.is_equal_approx` on each component.
     *
     * Generated from Godot docs: Quaternion.is_equal_approx
     */
    fun isEqualApprox(other: Quaternion): Boolean =
        isEqualApprox(x, other.x) && isEqualApprox(y, other.y) &&
            isEqualApprox(z, other.z) && isEqualApprox(w, other.w)

    /**
     * Returns this quaternion's length, squared. Note: This method is faster than `length`, so prefer
     * it if you only need to compare quaternion lengths.
     *
     * Generated from Godot docs: Quaternion.length_squared
     */
    fun lengthSquared(): Double = (x * x + y * y + z * z + w * w).toDouble()
    /**
     * Returns this quaternion's length, also called magnitude.
     *
     * Generated from Godot docs: Quaternion.length
     */
    fun length(): Double = sqrt(lengthSquared())

    /**
     * Returns a copy of this quaternion, normalized so that its length is `1.0`. See also
     * `is_normalized`.
     *
     * Generated from Godot docs: Quaternion.normalized
     */
    fun normalized(): Quaternion {
        val len = length()
        return if (len == 0.0) IDENTITY else Quaternion(
            x.toDouble() / len,
            y.toDouble() / len,
            z.toDouble() / len,
            w.toDouble() / len,
        )
    }

    operator fun times(other: Quaternion): Quaternion = Quaternion(
        w * other.x + x * other.w + y * other.z - z * other.y,
        w * other.y - x * other.z + y * other.w + z * other.x,
        w * other.z + x * other.y - y * other.x + z * other.w,
        w * other.w - x * other.x - y * other.y - z * other.z,
    )

    operator fun unaryMinus(): Quaternion = Quaternion(-x, -y, -z, -w)

    /**
     * Returns the inverse version of this quaternion, inverting the sign of every component except
     * `w`.
     *
     * Generated from Godot docs: Quaternion.inverse
     */
    fun inverse(): Quaternion {
        val ls = lengthSquared()
        if (ls == 0.0) return IDENTITY
        val inv = 1.0 / ls
        return Quaternion(-x * inv, -y * inv, -z * inv, w * inv)
    }

    /**
     * Returns the dot product between this quaternion and `with`. This is equivalent to `(quat.x *
     * with.x) + (quat.y * with.y) + (quat.z * with.z) + (quat.w * with.w)`.
     *
     * Generated from Godot docs: Quaternion.dot
     */
    fun dot(other: Quaternion): Double =
        (x * other.x + y * other.y + z * other.z + w * other.w).toDouble()

    /**
     * Performs a spherical-linear interpolation with the `to` quaternion, given a `weight` and returns
     * the result. Both this quaternion and `to` must be normalized.
     *
     * Generated from Godot docs: Quaternion.slerp
     */
    fun slerp(to: Quaternion, weight: Double): Quaternion {
        Arena.ofConfined().use { arena ->
            val base = arena.allocate(GodotReal.SIZE_BYTES * 4, GodotReal.ALIGN_BYTES)
            val toArg = arena.allocate(GodotReal.SIZE_BYTES * 4, GodotReal.ALIGN_BYTES)
            val weightArg = arena.allocate(JAVA_DOUBLE)
            val ret = arena.allocate(GodotReal.SIZE_BYTES * 4, GodotReal.ALIGN_BYTES)
            writeTo(base)
            to.writeTo(toArg)
            weightArg.set(JAVA_DOUBLE, 0, weight)
            BuiltinTypes.call(
                type = VariantType.QUATERNION,
                method = "slerp",
                hash = SLERP_HASH,
                base = base,
                args = listOf(toArg, weightArg),
                rReturn = ret,
            )
            return Quaternion(
                GodotReal.readIndex(ret, 0),
                GodotReal.readIndex(ret, 1),
                GodotReal.readIndex(ret, 2),
                GodotReal.readIndex(ret, 3),
            )
        }
    }

    private fun writeTo(dest: java.lang.foreign.MemorySegment) {
        GodotReal.writeIndex(dest, 0, x)
        GodotReal.writeIndex(dest, 1, y)
        GodotReal.writeIndex(dest, 2, z)
        GodotReal.writeIndex(dest, 3, w)
    }

    companion object {
        /**
         * The identity quaternion, representing no rotation. This has the same rotation as
         * `Basis.IDENTITY`. If a `Vector3` is rotated (multiplied) by this quaternion, it does not change.
         * Note: In GDScript, this constant is equivalent to creating a [constructor Quaternion] without
         * any arguments. It can be used to make your code clearer, and for consistency with C#.
         *
         * Generated from Godot docs: Quaternion.IDENTITY
         */
        val IDENTITY = Quaternion(0f, 0f, 0f, 1f)
    }
}

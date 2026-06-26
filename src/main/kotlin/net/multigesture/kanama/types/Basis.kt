package net.multigesture.kanama.types

import net.multigesture.kanama.binding.runtime.BuiltinTypes
import net.multigesture.kanama.binding.runtime.VariantType
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.JAVA_BYTE
import java.lang.foreign.ValueLayout.JAVA_DOUBLE
import java.lang.foreign.ValueLayout.JAVA_LONG

private const val GET_EULER_HASH = 1394941017L
private const val FROM_EULER_HASH = 2802321791L
private const val LOOKING_AT_HASH = 3728732505L
private const val SCALED_HASH = 3934786792L
private const val GET_SCALE_HASH = 1776574132L
private const val GET_ROTATION_QUATERNION_HASH = 4274879941L
private const val INVERSE_HASH = 594669093L
private const val ORTHONORMALIZED_HASH = 594669093L
private const val DETERMINANT_HASH = 466405837L

/**
 * A 3×3 matrix for representing 3D rotation and scale. Kanama value types are immutable snapshots;
 * assign a new value back to the Godot property after changing components.
 *
 * Generated from Godot docs: Basis
 */
data class Basis(
    /**
     * The basis's X axis, and the column `0` of the matrix. On the identity basis, this vector points
     * right (`Vector3.RIGHT`).
     *
     * Generated from Godot docs: Basis.x
     */
    val x: Vector3,
    /**
     * The basis's Y axis, and the column `1` of the matrix. On the identity basis, this vector points
     * up (`Vector3.UP`).
     *
     * Generated from Godot docs: Basis.y
     */
    val y: Vector3,
    /**
     * The basis's Z axis, and the column `2` of the matrix. On the identity basis, this vector points
     * back (`Vector3.BACK`).
     *
     * Generated from Godot docs: Basis.z
     */
    val z: Vector3,
) {
    constructor(quaternion: Quaternion) : this(fromQuaternion(quaternion), Unit)

    private constructor(from: Basis, ignored: Unit) : this(from.x, from.y, from.z)

    /** Godot-style fuzzy compare: true if every axis is approximately equal. */
    fun isEqualApprox(other: Basis): Boolean =
        x.isEqualApprox(other.x) && y.isEqualApprox(other.y) && z.isEqualApprox(other.z)

    /** kanama convenience (Godot has no composite `is_zero_approx`): true if every axis is approximately zero. */
    fun isZeroApprox(): Boolean = x.isZeroApprox() && y.isZeroApprox() && z.isZeroApprox()

    operator fun times(vector: Vector3): Vector3 =
        x * vector.x + y * vector.y + z * vector.z

    /**
     * Returns the determinant (https://en.wikipedia.org/wiki/Determinant) of this basis's matrix. For
     * advanced math, this number can be used to determine a few attributes: - If the determinant is
     * exactly `0.0`, the basis is not invertible (see `inverse`). - If the determinant is a negative
     * number, the basis represents a negative scale. Note: If the basis's scale is the same for every
     * axis, its determinant is always that scale by the power of 3.
     *
     * Generated from Godot docs: Basis.determinant
     */
    fun determinant(): Double {
        Arena.ofConfined().use { arena ->
            val base = arena.allocate(GodotReal.SIZE_BYTES * 9, GodotReal.ALIGN_BYTES)
            val ret = arena.allocate(JAVA_DOUBLE)
            writeTo(base)
            BuiltinTypes.call(
                type = VariantType.BASIS,
                method = "determinant",
                hash = DETERMINANT_HASH,
                base = base,
                args = emptyList(),
                rReturn = ret,
            )
            return ret.get(JAVA_DOUBLE, 0)
        }
    }

    /**
     * Returns the inverse of this basis's matrix (https://en.wikipedia.org/wiki/Invertible_matrix).
     *
     * Generated from Godot docs: Basis.inverse
     */
    fun inverse(): Basis =
        callNoArgsRetBasis("inverse", INVERSE_HASH)

    /**
     * Returns the orthonormalized version of this basis. An orthonormal basis is both orthogonal (the
     * axes are perpendicular to each other) and normalized (the axes have a length of `1.0`), which
     * also means it can only represent a rotation. It is often useful to call this method to avoid
     * rounding errors on a rotating basis:
     *
     * Generated from Godot docs: Basis.orthonormalized
     */
    fun orthonormalized(): Basis =
        callNoArgsRetBasis("orthonormalized", ORTHONORMALIZED_HASH)

    fun lerp(to: Basis, weight: Double): Basis =
        Basis(
            x.lerp(to.x, weight),
            y.lerp(to.y, weight),
            z.lerp(to.z, weight),
        )

    /**
     * Returns this basis's rotation as a `Quaternion`. Note: Quaternions are much more suitable for 3D
     * math but are less intuitive. For user interfaces, consider using the `get_euler` method, which
     * returns Euler angles.
     *
     * Generated from Godot docs: Basis.get_rotation_quaternion
     */
    fun getRotationQuaternion(): Quaternion =
        callNoArgsRetQuaternion("get_rotation_quaternion", GET_ROTATION_QUATERNION_HASH)

    /**
     * Returns the length of each axis of this basis, as a `Vector3`. If the basis is not sheared, this
     * value is the scaling factor. It is not affected by rotation.
     *
     * Generated from Godot docs: Basis.get_scale
     */
    fun getScale(): Vector3 =
        callNoArgsRetVector3("get_scale", GET_SCALE_HASH)

    /**
     * Returns this basis with each axis's components scaled by the given `scale`'s components. The
     * basis matrix's rows are multiplied by `scale`'s components. This operation is a global scale
     * (relative to the parent).
     *
     * Generated from Godot docs: Basis.scaled
     */
    fun scaled(scale: Vector3): Basis {
        Arena.ofConfined().use { arena ->
            val base = arena.allocate(GodotReal.SIZE_BYTES * 9, GodotReal.ALIGN_BYTES)
            val scaleArg = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
            val ret = arena.allocate(GodotReal.SIZE_BYTES * 9, GodotReal.ALIGN_BYTES)
            writeTo(base)
            writeVector3(scaleArg, scale)
            BuiltinTypes.call(
                type = VariantType.BASIS,
                method = "scaled",
                hash = SCALED_HASH,
                base = base,
                args = listOf(scaleArg),
                rReturn = ret,
            )
            return readFrom(ret)
        }
    }

    fun withX(value: Vector3): Basis = Basis(value, y, z)
    fun withY(value: Vector3): Basis = Basis(x, value, z)
    fun withZ(value: Vector3): Basis = Basis(x, y, value)

    private fun callNoArgsRetVector3(method: String, hash: Long): Vector3 {
        Arena.ofConfined().use { arena ->
            val base = arena.allocate(GodotReal.SIZE_BYTES * 9, GodotReal.ALIGN_BYTES)
            val ret = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
            writeTo(base)
            BuiltinTypes.call(
                type = VariantType.BASIS,
                method = method,
                hash = hash,
                base = base,
                args = emptyList(),
                rReturn = ret,
            )
            return Vector3(
                GodotReal.readIndex(ret, 0),
                GodotReal.readIndex(ret, 1),
                GodotReal.readIndex(ret, 2),
            )
        }
    }

    private fun callNoArgsRetQuaternion(method: String, hash: Long): Quaternion {
        Arena.ofConfined().use { arena ->
            val base = arena.allocate(GodotReal.SIZE_BYTES * 9, GodotReal.ALIGN_BYTES)
            val ret = arena.allocate(GodotReal.SIZE_BYTES * 4, GodotReal.ALIGN_BYTES)
            writeTo(base)
            BuiltinTypes.call(
                type = VariantType.BASIS,
                method = method,
                hash = hash,
                base = base,
                args = emptyList(),
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

    private fun callNoArgsRetBasis(method: String, hash: Long): Basis {
        Arena.ofConfined().use { arena ->
            val base = arena.allocate(GodotReal.SIZE_BYTES * 9, GodotReal.ALIGN_BYTES)
            val ret = arena.allocate(GodotReal.SIZE_BYTES * 9, GodotReal.ALIGN_BYTES)
            writeTo(base)
            BuiltinTypes.call(
                type = VariantType.BASIS,
                method = method,
                hash = hash,
                base = base,
                args = emptyList(),
                rReturn = ret,
            )
            return readFrom(ret)
        }
    }

    /**
     * Returns this basis's rotation as a `Vector3` of Euler angles
     * (https://en.wikipedia.org/wiki/Euler_angles), in radians. For the returned value: - The
     * `Vector3.x` contains the angle around the `x` axis (pitch); - The `Vector3.y` contains the angle
     * around the `y` axis (yaw); - The `Vector3.z` contains the angle around the `z` axis (roll). The
     * order of each consecutive rotation can be changed with `order` (see `EulerOrder` constants). In
     * Godot, Euler angles always use intrinsic order. By default, the intrinsic YXZ convention is used
     * (`EULER_ORDER_YXZ`): since we are decomposing, local Z (roll) is calculated first, then local X
     * (pitch), and lastly local Y (yaw). When using the opposite method `from_euler` to compose a
     * rotation, this order is reversed. Note: For this method to return correctly, the basis needs to
     * be orthonormal (see `orthonormalized`). Note: Euler angles are much more intuitive but are not
     * suitable for 3D math. Because of this, consider using the `get_rotation_quaternion` method
     * instead, which returns a `Quaternion`. Note: In the Inspector dock, a basis's rotation is often
     * displayed in Euler angles (in degrees), as is the case with the `Node3D.rotation` property.
     *
     * Generated from Godot docs: Basis.get_euler
     */
    fun getEuler(order: Long = EULER_ORDER_YXZ): Vector3 {
        Arena.ofConfined().use { arena ->
            val base = arena.allocate(GodotReal.SIZE_BYTES * 9, GodotReal.ALIGN_BYTES)
            val orderArg = arena.allocate(JAVA_LONG)
            val ret = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
            writeTo(base)
            orderArg.set(JAVA_LONG, 0, order)
            BuiltinTypes.call(
                type = VariantType.BASIS,
                method = "get_euler",
                hash = GET_EULER_HASH,
                base = base,
                args = listOf(orderArg),
                rReturn = ret,
            )
            return Vector3(
                GodotReal.readIndex(ret, 0),
                GodotReal.readIndex(ret, 1),
                GodotReal.readIndex(ret, 2),
            )
        }
    }

    private fun writeTo(dest: MemorySegment) {
        GodotReal.writeIndex(dest, 0, x.x)
        GodotReal.writeIndex(dest, 1, y.x)
        GodotReal.writeIndex(dest, 2, z.x)
        GodotReal.writeIndex(dest, 3, x.y)
        GodotReal.writeIndex(dest, 4, y.y)
        GodotReal.writeIndex(dest, 5, z.y)
        GodotReal.writeIndex(dest, 6, x.z)
        GodotReal.writeIndex(dest, 7, y.z)
        GodotReal.writeIndex(dest, 8, z.z)
    }

    private fun writeVector3(dest: MemorySegment, vector: Vector3) {
        GodotReal.writeIndex(dest, 0, vector.x)
        GodotReal.writeIndex(dest, 1, vector.y)
        GodotReal.writeIndex(dest, 2, vector.z)
    }

    companion object {
        const val EULER_ORDER_XYZ = 0L
        const val EULER_ORDER_XZY = 1L
        const val EULER_ORDER_YXZ = 2L
        const val EULER_ORDER_YZX = 3L
        const val EULER_ORDER_ZXY = 4L
        const val EULER_ORDER_ZYX = 5L

        /**
         * The identity `Basis`. This is an orthonormal basis with no rotation, no shear, and a scale of
         * `Vector3.ONE`. This also means that: - The `x` points right (`Vector3.RIGHT`); - The `y` points
         * up (`Vector3.UP`); - The `z` points back (`Vector3.BACK`).
         *
         * Generated from Godot docs: Basis.IDENTITY
         */
        val IDENTITY = Basis(
            Vector3(1f, 0f, 0f),
            Vector3(0f, 1f, 0f),
            Vector3(0f, 0f, 1f),
        )

        /**
         * Constructs a new `Basis` that only represents rotation from the given `Vector3` of Euler angles
         * (https://en.wikipedia.org/wiki/Euler_angles), in radians. - The `Vector3.x` should contain the
         * angle around the `x` axis (pitch); - The `Vector3.y` should contain the angle around the `y`
         * axis (yaw); - The `Vector3.z` should contain the angle around the `z` axis (roll).
         *
         * Generated from Godot docs: Basis.from_euler
         */
        fun fromEuler(euler: Vector3, order: Long = EULER_ORDER_YXZ): Basis {
            Arena.ofConfined().use { arena ->
                val eulerArg = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
                val orderArg = arena.allocate(JAVA_LONG)
                val ret = arena.allocate(GodotReal.SIZE_BYTES * 9, GodotReal.ALIGN_BYTES)
                GodotReal.writeIndex(eulerArg, 0, euler.x)
                GodotReal.writeIndex(eulerArg, 1, euler.y)
                GodotReal.writeIndex(eulerArg, 2, euler.z)
                orderArg.set(JAVA_LONG, 0, order)
                BuiltinTypes.call(
                    type = VariantType.BASIS,
                    method = "from_euler",
                    hash = FROM_EULER_HASH,
                    base = MemorySegment.NULL,
                    args = listOf(eulerArg, orderArg),
                    rReturn = ret,
                )
                return readFrom(ret)
            }
        }

        /**
         * Creates a new `Basis` with a rotation such that the forward axis (-Z) points towards the
         * `target` position. By default, the -Z axis (camera forward) is treated as forward (implies +X is
         * right). If `use_model_front` is `true`, the +Z axis (asset front) is treated as forward (implies
         * +X is left) and points toward the `target` position. The up axis (+Y) points as close to the
         * `up` vector as possible while staying perpendicular to the forward axis. The returned basis is
         * orthonormalized (see `orthonormalized`). The `target` and the `up` cannot be `Vector3.ZERO`, and
         * shouldn't be colinear to avoid unintended rotation around local Z axis.
         *
         * Generated from Godot docs: Basis.looking_at
         */
        fun lookingAt(target: Vector3, up: Vector3 = Vector3.UP, useModelFront: Boolean = false): Basis {
            Arena.ofConfined().use { arena ->
                val targetArg = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
                val upArg = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
                val useModelFrontArg = arena.allocate(JAVA_BYTE)
                val ret = arena.allocate(GodotReal.SIZE_BYTES * 9, GodotReal.ALIGN_BYTES)
                GodotReal.writeIndex(targetArg, 0, target.x)
                GodotReal.writeIndex(targetArg, 1, target.y)
                GodotReal.writeIndex(targetArg, 2, target.z)
                GodotReal.writeIndex(upArg, 0, up.x)
                GodotReal.writeIndex(upArg, 1, up.y)
                GodotReal.writeIndex(upArg, 2, up.z)
                useModelFrontArg.set(JAVA_BYTE, 0, if (useModelFront) 1.toByte() else 0.toByte())
                BuiltinTypes.call(
                    type = VariantType.BASIS,
                    method = "looking_at",
                    hash = LOOKING_AT_HASH,
                    base = MemorySegment.NULL,
                    args = listOf(targetArg, upArg, useModelFrontArg),
                    rReturn = ret,
                )
                return readFrom(ret)
            }
        }

        private fun writeQuaternion(dest: MemorySegment, quaternion: Quaternion) {
            GodotReal.writeIndex(dest, 0, quaternion.x)
            GodotReal.writeIndex(dest, 1, quaternion.y)
            GodotReal.writeIndex(dest, 2, quaternion.z)
            GodotReal.writeIndex(dest, 3, quaternion.w)
        }

        private fun readFrom(src: MemorySegment): Basis =
            Basis(
                x = Vector3(
                    GodotReal.readIndex(src, 0),
                    GodotReal.readIndex(src, 3),
                    GodotReal.readIndex(src, 6),
                ),
                y = Vector3(
                    GodotReal.readIndex(src, 1),
                    GodotReal.readIndex(src, 4),
                    GodotReal.readIndex(src, 7),
                ),
                z = Vector3(
                    GodotReal.readIndex(src, 2),
                    GodotReal.readIndex(src, 5),
                    GodotReal.readIndex(src, 8),
                ),
            )

        private fun fromQuaternion(quaternion: Quaternion): Basis {
            Arena.ofConfined().use { arena ->
                val quaternionArg = arena.allocate(GodotReal.SIZE_BYTES * 4, GodotReal.ALIGN_BYTES)
                val ret = arena.allocate(GodotReal.SIZE_BYTES * 9, GodotReal.ALIGN_BYTES)
                writeQuaternion(quaternionArg, quaternion)
                BuiltinTypes.construct(
                    type = VariantType.BASIS,
                    dest = ret,
                    constructorIndex = 2,
                    args = listOf(quaternionArg),
                )
                return readFrom(ret)
            }
        }
    }
}

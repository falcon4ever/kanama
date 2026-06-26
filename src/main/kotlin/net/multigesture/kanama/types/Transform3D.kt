package net.multigesture.kanama.types

import net.multigesture.kanama.binding.runtime.BuiltinTypes
import net.multigesture.kanama.binding.runtime.VariantType
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.JAVA_BYTE
import java.lang.foreign.ValueLayout.JAVA_DOUBLE

private const val LOOKING_AT_HASH = 90889270L
private const val INTERPOLATE_WITH_HASH = 1786453358L
private const val ORTHONORMALIZED_HASH = 3816817146L
private const val SCALED_LOCAL_HASH = 1405596198L

/**
 * A 3×4 matrix representing a 3D transformation. Kanama value types are immutable snapshots;
 * assign a new value back to the Godot property after changing components.
 *
 * Generated from Godot docs: Transform3D
 */
data class Transform3D(
    /**
     * The `Basis` of this transform. It is composed by 3 axes (`Basis.x`, `Basis.y`, and `Basis.z`).
     * Together, these represent the transform's rotation, scale, and shear.
     *
     * Generated from Godot docs: Transform3D.basis
     */
    val basis: Basis,
    /**
     * The translation offset of this transform. In 3D space, this can be seen as the position.
     *
     * Generated from Godot docs: Transform3D.origin
     */
    val origin: Vector3,
) {
    /** Godot-style fuzzy compare: true if basis and origin are approximately equal. */
    fun isEqualApprox(other: Transform3D): Boolean =
        basis.isEqualApprox(other.basis) && origin.isEqualApprox(other.origin)

    /** kanama convenience (Godot has no composite `is_zero_approx`): true if basis and origin are approximately zero. */
    fun isZeroApprox(): Boolean = basis.isZeroApprox() && origin.isZeroApprox()

    operator fun times(vector: Vector3): Vector3 =
        basis * vector + origin

    /**
     * Returns the result of the linear interpolation between this transform and `xform` by the given
     * `weight`. The `weight` should be between `0.0` and `1.0` (inclusive). Values outside this range
     * are allowed and can be used to perform extrapolation instead.
     *
     * Generated from Godot docs: Transform3D.interpolate_with
     */
    fun interpolateWith(to: Transform3D, weight: Double): Transform3D {
        Arena.ofConfined().use { arena ->
            val base = arena.allocate(GodotReal.SIZE_BYTES * 12, GodotReal.ALIGN_BYTES)
            val toArg = arena.allocate(GodotReal.SIZE_BYTES * 12, GodotReal.ALIGN_BYTES)
            val weightArg = arena.allocate(JAVA_DOUBLE)
            val ret = arena.allocate(GodotReal.SIZE_BYTES * 12, GodotReal.ALIGN_BYTES)
            writeTo(base)
            to.writeTo(toArg)
            weightArg.set(JAVA_DOUBLE, 0, weight)
            BuiltinTypes.call(
                type = VariantType.TRANSFORM3D,
                method = "interpolate_with",
                hash = INTERPOLATE_WITH_HASH,
                base = base,
                args = listOf(toArg, weightArg),
                rReturn = ret,
            )
            return readFrom(ret)
        }
    }

    /**
     * Returns a copy of this transform with its `basis` orthonormalized. An orthonormal basis is both
     * orthogonal (the axes are perpendicular to each other) and normalized (the axes have a length of
     * `1.0`), which also means it can only represent a rotation. See also `Basis.orthonormalized`.
     *
     * Generated from Godot docs: Transform3D.orthonormalized
     */
    fun orthonormalized(): Transform3D {
        Arena.ofConfined().use { arena ->
            val base = arena.allocate(GodotReal.SIZE_BYTES * 12, GodotReal.ALIGN_BYTES)
            val ret = arena.allocate(GodotReal.SIZE_BYTES * 12, GodotReal.ALIGN_BYTES)
            writeTo(base)
            BuiltinTypes.call(
                type = VariantType.TRANSFORM3D,
                method = "orthonormalized",
                hash = ORTHONORMALIZED_HASH,
                base = base,
                args = emptyList(),
                rReturn = ret,
            )
            return readFrom(ret)
        }
    }

    /**
     * Returns a copy of this transform rotated so that the forward axis (-Z) points towards the
     * `target` position. The up axis (+Y) points as close to the `up` vector as possible while staying
     * perpendicular to the forward axis. The resulting transform is orthonormalized. The existing
     * rotation, scale, and skew information from the original transform is discarded. The `target` and
     * `up` vectors cannot be zero, cannot be parallel to each other, and are defined in global/parent
     * space. If `use_model_front` is `true`, the +Z axis (asset front) is treated as forward (implies
     * +X is left) and points toward the `target` position. By default, the -Z axis (camera forward) is
     * treated as forward (implies +X is right).
     *
     * Generated from Godot docs: Transform3D.looking_at
     */
    fun lookingAt(target: Vector3, up: Vector3 = Vector3.UP, useModelFront: Boolean = false): Transform3D {
        Arena.ofConfined().use { arena ->
            val base = arena.allocate(GodotReal.SIZE_BYTES * 12, GodotReal.ALIGN_BYTES)
            val targetArg = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
            val upArg = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
            val useModelFrontArg = arena.allocate(JAVA_BYTE)
            val ret = arena.allocate(GodotReal.SIZE_BYTES * 12, GodotReal.ALIGN_BYTES)
            writeTo(base)
            writeVector3(targetArg, target)
            writeVector3(upArg, up)
            useModelFrontArg.set(JAVA_BYTE, 0, if (useModelFront) 1.toByte() else 0.toByte())
            BuiltinTypes.call(
                type = VariantType.TRANSFORM3D,
                method = "looking_at",
                hash = LOOKING_AT_HASH,
                base = base,
                args = listOf(targetArg, upArg, useModelFrontArg),
                rReturn = ret,
            )
            return readFrom(ret)
        }
    }

    /**
     * Returns a copy of this transform scaled by the given `scale` factor. This method is an optimized
     * version of multiplying the given transform `X` with a corresponding scaling transform `S` from
     * the right, i.e., `X * S`. This can be seen as transforming with respect to the local frame.
     *
     * Generated from Godot docs: Transform3D.scaled_local
     */
    fun scaledLocal(scale: Vector3): Transform3D {
        Arena.ofConfined().use { arena ->
            val base = arena.allocate(GodotReal.SIZE_BYTES * 12, GodotReal.ALIGN_BYTES)
            val scaleArg = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)
            val ret = arena.allocate(GodotReal.SIZE_BYTES * 12, GodotReal.ALIGN_BYTES)
            writeTo(base)
            writeVector3(scaleArg, scale)
            BuiltinTypes.call(
                type = VariantType.TRANSFORM3D,
                method = "scaled_local",
                hash = SCALED_LOCAL_HASH,
                base = base,
                args = listOf(scaleArg),
                rReturn = ret,
            )
            return readFrom(ret)
        }
    }

    fun withBasis(value: Basis): Transform3D = copy(basis = value)

    fun withOrigin(value: Vector3): Transform3D = copy(origin = value)

    private fun writeTo(dest: MemorySegment) {
        GodotReal.writeIndex(dest, 0, basis.x.x)
        GodotReal.writeIndex(dest, 1, basis.y.x)
        GodotReal.writeIndex(dest, 2, basis.z.x)
        GodotReal.writeIndex(dest, 3, basis.x.y)
        GodotReal.writeIndex(dest, 4, basis.y.y)
        GodotReal.writeIndex(dest, 5, basis.z.y)
        GodotReal.writeIndex(dest, 6, basis.x.z)
        GodotReal.writeIndex(dest, 7, basis.y.z)
        GodotReal.writeIndex(dest, 8, basis.z.z)
        GodotReal.writeIndex(dest, 9, origin.x)
        GodotReal.writeIndex(dest, 10, origin.y)
        GodotReal.writeIndex(dest, 11, origin.z)
    }

    companion object {
        /**
         * The identity `Transform3D`. This is a transform with no translation, no rotation, and a scale of
         * `Vector3.ONE`. Its `basis` is equal to `Basis.IDENTITY`. This also means that: - Its `Basis.x`
         * points right (`Vector3.RIGHT`); - Its `Basis.y` points up (`Vector3.UP`); - Its `Basis.z` points
         * back (`Vector3.BACK`).
         *
         * Generated from Godot docs: Transform3D.IDENTITY
         */
        val IDENTITY = Transform3D(Basis.IDENTITY, Vector3.ZERO)

        private fun writeVector3(dest: MemorySegment, vector: Vector3) {
            GodotReal.writeIndex(dest, 0, vector.x)
            GodotReal.writeIndex(dest, 1, vector.y)
            GodotReal.writeIndex(dest, 2, vector.z)
        }

        private fun readFrom(src: MemorySegment): Transform3D =
            Transform3D(
                basis = Basis(
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
                ),
                origin = Vector3(
                    GodotReal.readIndex(src, 9),
                    GodotReal.readIndex(src, 10),
                    GodotReal.readIndex(src, 11),
                ),
            )
    }
}

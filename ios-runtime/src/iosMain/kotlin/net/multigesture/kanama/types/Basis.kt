package net.multigesture.kanama.types

import net.multigesture.kanama.binding.runtime.BuiltinCalls

/**
 * A 3×3 matrix for 3D rotation and scale, as three column axes (x, y, z). Kanama value
 * types are immutable snapshots; assign a new value back to the Godot property after
 * changing components.
 *
 * iOS marshalling note: at ptrcall a Basis is 9 `real_t` (float32 on single-precision
 * iOS) in column-major order — `[x.x, y.x, z.x, x.y, y.y, z.y, x.z, y.z, z.z]` — laid
 * out by the generated ObjectCalls helpers. Engine-computed value methods (e.g.
 * [inverse]) route through the BuiltinCalls value-type call path.
 */
data class Basis(
    val x: Vector3,
    val y: Vector3,
    val z: Vector3,
) {
    /** Builds the rotation basis for [quaternion] (matches Godot's `Basis(Quaternion)` constructor). */
    constructor(quaternion: Quaternion) : this(columnsFromQuaternion(quaternion))

    private constructor(columns: Triple<Vector3, Vector3, Vector3>) :
        this(columns.first, columns.second, columns.third)

    /** Godot-style fuzzy compare: true if every axis is approximately equal. */
    fun isEqualApprox(other: Basis): Boolean =
        x.isEqualApprox(other.x) && y.isEqualApprox(other.y) && z.isEqualApprox(other.z)

    /** True if every axis is approximately zero. */
    fun isZeroApprox(): Boolean = x.isZeroApprox() && y.isZeroApprox() && z.isZeroApprox()

    /** Returns a copy with the x/y/z axis replaced (matches desktop Basis.withX/withY/withZ). */
    fun withX(value: Vector3): Basis = Basis(value, y, z)
    fun withY(value: Vector3): Basis = Basis(x, value, z)
    fun withZ(value: Vector3): Basis = Basis(x, y, value)

    /** Transforms (basis-multiplies) [vector] by this basis (matches desktop Basis * Vector3). */
    operator fun times(vector: Vector3): Vector3 =
        x * vector.x + y * vector.y + z * vector.z

    /** Returns the inverse of this basis. Computed by Godot. */
    fun inverse(): Basis =
        fromGodotRealArray(BuiltinCalls.callNoArgsFloat32(inverseBind, toGodotRealArray()))

    /** Returns the transpose of this basis (rows and columns swapped). */
    fun transposed(): Basis =
        fromGodotRealArray(BuiltinCalls.callNoArgsFloat32(transposedBind, toGodotRealArray()))

    /** Returns this basis with its axes orthogonalized and normalized (Gram-Schmidt). */
    fun orthonormalized(): Basis =
        fromGodotRealArray(BuiltinCalls.callNoArgsFloat32(orthonormalizedBind, toGodotRealArray()))

    /**
     * Returns a copy of this basis scaled by [scale] (each row multiplied by the matching
     * [scale] component). IDENTITY scaled by (sx,sy,sz) is diag(sx,sy,sz).
     */
    fun scaled(scale: Vector3): Basis =
        fromGodotRealArray(BuiltinCalls.call(scaledBind, toGodotRealArray(), 9, listOf(
            BuiltinCalls.BArg.Floats(
                BuiltinCalls.PT_VECTOR3,
                vector3Array(scale),
            ),
        )))

    /**
     * Returns the determinant of this basis, computed by Godot (scalar `real_t` return
     * decoded to Double). A negative determinant means the basis flips orientation.
     */
    fun determinant(): Double =
        BuiltinCalls.callScalar(determinantBind, toGodotRealArray())

    /** Returns the scale component of this basis, computed by Godot (matches Basis.get_scale). */
    fun getScale(): Vector3 {
        val c = BuiltinCalls.call(getScaleBind, toGodotRealArray(), 3)
        return Vector3(GodotReal.fromC(c[0]), GodotReal.fromC(c[1]), GodotReal.fromC(c[2]))
    }

    /**
     * Returns the rotation part of this basis as a quaternion, computed by Godot
     * (orthonormalization + negative-scale handling match the engine; Basis.get_rotation_quaternion).
     */
    fun getRotationQuaternion(): Quaternion {
        val c = BuiltinCalls.call(getRotationQuaternionBind, toGodotRealArray(), 4)
        return Quaternion(GodotReal.fromC(c[0]), GodotReal.fromC(c[1]), GodotReal.fromC(c[2]), GodotReal.fromC(c[3]))
    }

    /**
     * Returns the Euler angles (radians) for this basis in Godot's default YXZ order. Pure-Kotlin
     * decomposition matching `Basis::get_euler(EULER_ORDER_YXZ)` exactly (the builtin needs an int
     * order arg the iOS value-call path doesn't carry; the math is convention-fixed here instead).
     * `rows[i][j]` in the engine maps to component `i` of column axis `j` (x=col0, y=col1, z=col2).
     */
    fun getEuler(): Vector3 {
        val m12 = z.y // rows[1][2]
        return when {
            m12 < 1.0 - CMP_EPSILON -> when {
                m12 > -(1.0 - CMP_EPSILON) -> Vector3(
                    kotlin.math.asin(-m12),
                    kotlin.math.atan2(z.x, z.z), // atan2(rows[0][2], rows[2][2])
                    kotlin.math.atan2(x.y, y.y), // atan2(rows[1][0], rows[1][1])
                )
                // m12 == -1
                else -> Vector3(kotlin.math.PI * 0.5, kotlin.math.atan2(y.x, x.x), 0.0)
            }
            // m12 == 1
            else -> Vector3(-kotlin.math.PI * 0.5, -kotlin.math.atan2(y.x, x.x), 0.0)
        }
    }

    // Column-major real_t values, matching the ObjectCalls Basis ptrcall layout.
    private fun toGodotRealArray(): GodotRealArray =
        GodotRealArray(9).also {
            it[0] = GodotReal.toC(x.x)
            it[1] = GodotReal.toC(y.x)
            it[2] = GodotReal.toC(z.x)
            it[3] = GodotReal.toC(x.y)
            it[4] = GodotReal.toC(y.y)
            it[5] = GodotReal.toC(z.y)
            it[6] = GodotReal.toC(x.z)
            it[7] = GodotReal.toC(y.z)
            it[8] = GodotReal.toC(z.z)
        }

    companion object {
        // Godot CMP_EPSILON (used by the Euler decomposition gimbal checks).
        private const val CMP_EPSILON = 0.00001

        val IDENTITY = Basis(
            Vector3(1.0, 0.0, 0.0),
            Vector3(0.0, 1.0, 0.0),
            Vector3(0.0, 0.0, 1.0),
        )

        /** Returns the rotation basis for [euler] (radians, YXZ), via Godot's Quaternion.from_euler. */
        fun fromEuler(euler: Vector3): Basis = Basis(Quaternion.fromEuler(euler))

        // Godot `Basis::set_quaternion`: builds the 3 column axes from a quaternion. rows[i][j] in
        // the engine is component i of column j here, so the engine rows are transposed into columns.
        private fun columnsFromQuaternion(q: Quaternion): Triple<Vector3, Vector3, Vector3> {
            val d = q.x * q.x + q.y * q.y + q.z * q.z + q.w * q.w
            val s = 2.0 / d
            val xs = q.x * s; val ys = q.y * s; val zs = q.z * s
            val wx = q.w * xs; val wy = q.w * ys; val wz = q.w * zs
            val xx = q.x * xs; val xy = q.x * ys; val xz = q.x * zs
            val yy = q.y * ys; val yz = q.y * zs; val zz = q.z * zs
            return Triple(
                Vector3(1.0 - (yy + zz), xy + wz, xz - wy),
                Vector3(xy - wz, 1.0 - (xx + zz), yz + wx),
                Vector3(xz + wy, yz - wx, 1.0 - (xx + yy)),
            )
        }

        // All no-arg->Self Basis builtin methods share this signature-shape hash; the name
        // selects the method (inverse / transposed / orthonormalized).
        private val inverseBind by lazy {
            BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_BASIS, "inverse", 594669093L)
        }
        private val transposedBind by lazy {
            BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_BASIS, "transposed", 594669093L)
        }
        private val orthonormalizedBind by lazy {
            BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_BASIS, "orthonormalized", 594669093L)
        }
        private val scaledBind by lazy {
            BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_BASIS, "scaled", 3934786792L)
        }
        private val determinantBind by lazy {
            BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_BASIS, "determinant", 466405837L)
        }
        private val getScaleBind by lazy {
            BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_BASIS, "get_scale", 1776574132L)
        }
        private val getRotationQuaternionBind by lazy {
            BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_BASIS, "get_rotation_quaternion", 4274879941L)
        }
        private val lookingAtBind by lazy {
            BuiltinCalls.getBuiltinMethod(BuiltinCalls.VT_BASIS, "looking_at", 3728732505L)
        }

        /**
         * Returns a rotation basis that points the -Z axis toward [target] with +Y toward [up]
         * (or the model-front axis when [useModelFront]). `Basis.looking_at` is a *static* builtin,
         * so the call passes an empty base (mirrors the desktop `base = NULL`).
         */
        fun lookingAt(target: Vector3, up: Vector3 = Vector3.UP, useModelFront: Boolean = false): Basis =
            fromGodotRealArray(BuiltinCalls.call(lookingAtBind, GodotRealArray(0), 9, listOf(
                BuiltinCalls.BArg.Floats(
                    BuiltinCalls.PT_VECTOR3,
                    vector3Array(target),
                ),
                BuiltinCalls.BArg.Floats(
                    BuiltinCalls.PT_VECTOR3,
                    vector3Array(up),
                ),
                BuiltinCalls.BArg.Bool(useModelFront),
            )))

        private fun vector3Array(v: Vector3): GodotRealArray =
            GodotRealArray(3).also {
                it[0] = GodotReal.toC(v.x)
                it[1] = GodotReal.toC(v.y)
                it[2] = GodotReal.toC(v.z)
            }

        private fun fromGodotRealArray(c: GodotRealArray): Basis =
            Basis(
                Vector3(GodotReal.fromC(c[0]), GodotReal.fromC(c[3]), GodotReal.fromC(c[6])),
                Vector3(GodotReal.fromC(c[1]), GodotReal.fromC(c[4]), GodotReal.fromC(c[7])),
                Vector3(GodotReal.fromC(c[2]), GodotReal.fromC(c[5]), GodotReal.fromC(c[8])),
            )
    }
}

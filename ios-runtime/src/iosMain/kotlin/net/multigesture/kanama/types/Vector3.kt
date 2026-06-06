package net.multigesture.kanama.types

import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

data class Vector3(
    val x: Double,
    val y: Double,
    val z: Double,
) {
    constructor(x: Number, y: Number, z: Number) : this(x.toDouble(), y.toDouble(), z.toDouble())

    operator fun plus(other: Vector3): Vector3 =
        Vector3(x + other.x, y + other.y, z + other.z)

    operator fun minus(other: Vector3): Vector3 =
        Vector3(x - other.x, y - other.y, z - other.z)

    operator fun times(scale: Number): Vector3 =
        Vector3(x * scale.toDouble(), y * scale.toDouble(), z * scale.toDouble())

    operator fun div(scale: Number): Vector3 =
        Vector3(x / scale.toDouble(), y / scale.toDouble(), z / scale.toDouble())

    operator fun unaryMinus(): Vector3 =
        Vector3(-x, -y, -z)

    fun withX(value: Number): Vector3 =
        copy(x = value.toDouble())

    fun withY(value: Number): Vector3 =
        copy(y = value.toDouble())

    fun withZ(value: Number): Vector3 =
        copy(z = value.toDouble())

    fun lengthSquared(): Double =
        x * x + y * y + z * z

    fun length(): Double =
        sqrt(lengthSquared())

    fun normalized(): Vector3 {
        val len = length()
        return if (len == 0.0) ZERO else this / len
    }

    fun lerp(to: Vector3, weight: Double): Vector3 =
        this + (to - this) * weight

    fun limitLength(maxLength: Double): Vector3 {
        val len = length()
        return if (len > maxLength && len > 0.0) normalized() * maxLength else this
    }

    fun rotated(axis: Vector3, angle: Double): Vector3 {
        if (axis.normalized() != UP) return this
        val c = cos(angle)
        val s = sin(angle)
        return Vector3(x * c - z * s, y, x * s + z * c)
    }

    companion object {
        val ZERO = Vector3(0.0, 0.0, 0.0)
        val ONE = Vector3(1.0, 1.0, 1.0)
        val UP = Vector3(0.0, 1.0, 0.0)
    }
}

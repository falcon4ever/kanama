package net.multigesture.kanama.types

import kotlin.math.atan2
import kotlin.math.sqrt

data class Vector2(
    val x: Double,
    val y: Double,
) {
    constructor(x: Number, y: Number) : this(x.toDouble(), y.toDouble())

    operator fun plus(other: Vector2): Vector2 =
        Vector2(x + other.x, y + other.y)

    operator fun minus(other: Vector2): Vector2 =
        Vector2(x - other.x, y - other.y)

    operator fun times(scale: Number): Vector2 =
        Vector2(x * scale.toDouble(), y * scale.toDouble())

    operator fun div(scale: Number): Vector2 =
        Vector2(x / scale.toDouble(), y / scale.toDouble())

    operator fun unaryMinus(): Vector2 =
        Vector2(-x, -y)

    fun lengthSquared(): Double =
        x * x + y * y

    fun length(): Double =
        sqrt(lengthSquared())

    fun angle(): Double =
        atan2(y, x)

    fun withX(value: Number): Vector2 = Vector2(value, y)

    fun withY(value: Number): Vector2 = Vector2(x, value)

    companion object {
        val ZERO = Vector2(0.0, 0.0)
        val ONE = Vector2(1.0, 1.0)
    }
}

package net.multigesture.kanama.types

import kotlin.math.sqrt

data class Vector2(val x: Double, val y: Double) {
  constructor(x: Number, y: Number) : this(x.toDouble(), y.toDouble())

  operator fun plus(other: Vector2): Vector2 = Vector2(x + other.x, y + other.y)

  operator fun minus(other: Vector2): Vector2 = Vector2(x - other.x, y - other.y)

  operator fun times(scalar: Number): Vector2 =
    Vector2(x * scalar.toDouble(), y * scalar.toDouble())

  operator fun times(scalar: Double): Vector2 = Vector2(x * scalar, y * scalar)

  operator fun div(scalar: Number): Vector2 = Vector2(x / scalar.toDouble(), y / scalar.toDouble())

  operator fun div(scalar: Double): Vector2 = Vector2(x / scalar, y / scalar)

  fun length(): Double = sqrt(x * x + y * y)

  fun withY(value: Number): Vector2 = Vector2(x, value.toDouble())

  companion object {
    val ZERO = Vector2(0.0, 0.0)
    val ONE = Vector2(1.0, 1.0)
  }
}

data class Rect2(val position: Vector2, val size: Vector2)

data class Color(val r: Float, val g: Float, val b: Float, val a: Float = 1.0f)

data class Vector2i(val x: Int, val y: Int) {
  companion object {
    val ZERO = Vector2i(0, 0)
  }
}

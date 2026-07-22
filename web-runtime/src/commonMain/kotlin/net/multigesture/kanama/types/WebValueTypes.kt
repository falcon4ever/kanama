package net.multigesture.kanama.types

data class Vector2(val x: Double, val y: Double) {
  operator fun plus(other: Vector2): Vector2 = Vector2(x + other.x, y + other.y)

  operator fun minus(other: Vector2): Vector2 = Vector2(x - other.x, y - other.y)

  operator fun times(scalar: Double): Vector2 = Vector2(x * scalar, y * scalar)
}

data class Rect2(val position: Vector2, val size: Vector2)

data class Color(val r: Float, val g: Float, val b: Float, val a: Float = 1.0f)

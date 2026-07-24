package net.multigesture.kanama.types

import kotlin.math.cos
import kotlin.math.sin
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

  fun withX(value: Number): Vector2 = Vector2(value.toDouble(), y)

  fun withY(value: Number): Vector2 = Vector2(x, value.toDouble())

  fun normalized(): Vector2 {
    val len = length()
    return if (len > 0.0) Vector2(x / len, y / len) else ZERO
  }

  fun clamp(min: Vector2, max: Vector2): Vector2 =
    Vector2(x.coerceIn(min.x, max.x), y.coerceIn(min.y, max.y))

  fun rotated(angle: Double): Vector2 {
    val c = cos(angle)
    val s = sin(angle)
    return Vector2(x * c - y * s, x * s + y * c)
  }

  companion object {
    val ZERO = Vector2(0.0, 0.0)
    val ONE = Vector2(1.0, 1.0)
  }
}

data class Vector3(val x: Double, val y: Double, val z: Double) {
  constructor(x: Number, y: Number, z: Number) : this(x.toDouble(), y.toDouble(), z.toDouble())

  operator fun plus(other: Vector3): Vector3 = Vector3(x + other.x, y + other.y, z + other.z)

  operator fun minus(other: Vector3): Vector3 = Vector3(x - other.x, y - other.y, z - other.z)

  operator fun times(scalar: Number): Vector3 =
    Vector3(x * scalar.toDouble(), y * scalar.toDouble(), z * scalar.toDouble())

  operator fun div(scalar: Number): Vector3 =
    Vector3(x / scalar.toDouble(), y / scalar.toDouble(), z / scalar.toDouble())

  fun length(): Double = sqrt(x * x + y * y + z * z)

  fun withX(value: Number): Vector3 = Vector3(value.toDouble(), y, z)

  fun withY(value: Number): Vector3 = Vector3(x, value.toDouble(), z)

  fun withZ(value: Number): Vector3 = Vector3(x, y, value.toDouble())

  fun normalized(): Vector3 {
    val len = length()
    return if (len > 0.0) Vector3(x / len, y / len, z / len) else ZERO
  }

  companion object {
    val ZERO = Vector3(0.0, 0.0, 0.0)
    val ONE = Vector3(1.0, 1.0, 1.0)
  }
}

data class Rect2(val position: Vector2, val size: Vector2)

data class Color(val r: Float, val g: Float, val b: Float, val a: Float = 1.0f)

data class Vector2i(val x: Int, val y: Int) {
  companion object {
    val ZERO = Vector2i(0, 0)
  }
}

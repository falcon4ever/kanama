package net.multigesture.kanama.types

data class Vector3i(val x: Int, val y: Int, val z: Int) {
  fun withX(value: Int): Vector3i = Vector3i(value, y, z)

  fun withY(value: Int): Vector3i = Vector3i(x, value, z)

  fun withZ(value: Int): Vector3i = Vector3i(x, y, value)

  companion object {
    val ZERO = Vector3i(0, 0, 0)
    val ONE = Vector3i(1, 1, 1)
  }
}

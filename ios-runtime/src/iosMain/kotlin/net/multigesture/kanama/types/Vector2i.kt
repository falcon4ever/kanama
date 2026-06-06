package net.multigesture.kanama.types

data class Vector2i(
    val x: Int,
    val y: Int,
) {
    fun withX(value: Int): Vector2i = Vector2i(value, y)

    fun withY(value: Int): Vector2i = Vector2i(x, value)

    companion object {
        val ZERO = Vector2i(0, 0)
        val ONE = Vector2i(1, 1)
    }
}

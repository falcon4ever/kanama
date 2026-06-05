package net.multigesture.kanama.types

data class Rect2(
    val position: Vector2,
    val size: Vector2,
) {
    val end: Vector2 get() = position + size

    companion object {
        val ZERO = Rect2(Vector2.ZERO, Vector2.ZERO)
    }
}

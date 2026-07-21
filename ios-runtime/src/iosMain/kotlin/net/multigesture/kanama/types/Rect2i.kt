package net.multigesture.kanama.types

/**
 * A 2D axis-aligned bounding box using integer coordinates — the iOS mirror of the desktop value
 * type (introduced with the Rect2i iOS return shape, task 26). Kanama value types are immutable
 * snapshots.
 */
data class Rect2i(val position: Vector2i, val size: Vector2i) {
  /** The ending point, equivalent to `position + size`. */
  val end: Vector2i
    get() = Vector2i(position.x + size.x, position.y + size.y)

  fun area(): Long = size.x.toLong() * size.y.toLong()

  companion object {
    val ZERO = Rect2i(Vector2i(0, 0), Vector2i(0, 0))
  }
}

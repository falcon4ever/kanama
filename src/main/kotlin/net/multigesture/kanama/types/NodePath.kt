package net.multigesture.kanama.types

/**
 * A pre-parsed scene tree path. Kanama value types are immutable snapshots; assign a new value back
 * to the Godot property after changing components.
 *
 * Generated from Godot docs: NodePath
 */
@JvmInline
value class NodePath(val path: String) {
  override fun toString(): String = path

  companion object {
    val EMPTY = NodePath("")
  }
}

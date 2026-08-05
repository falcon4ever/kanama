package net.multigesture.kanama.types

/**
 * A pre-parsed scene tree path. Kanama value types are immutable snapshots; assign a new value back
 * to the Godot property after changing components.
 *
 * Web mirror of the desktop `net.multigesture.kanama.types.NodePath`: the same shape at the same
 * fully-qualified name, so `@ScriptProperty` NodePath declarations compile unchanged for the
 * Kotlin/Wasm target. On the wire a NodePath is its plain [path] string.
 *
 * Generated from Godot docs: NodePath
 */
value class NodePath(val path: String) {
  override fun toString(): String = path

  companion object {
    val EMPTY = NodePath("")
  }
}

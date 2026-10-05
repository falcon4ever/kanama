package net.multigesture.kanama.types

import kotlin.jvm.JvmInline
import net.multigesture.kanama.binding.runtime.UtilityCalls
import net.multigesture.kanama.builtins.BoxedSig
import net.multigesture.kanama.builtins.BoxedType
import net.multigesture.kanama.builtins.NodePathMethods
import net.multigesture.kanama.builtins.nodePathAsPropertyPath
import net.multigesture.kanama.builtins.nodePathConcatenated
import net.multigesture.kanama.builtins.nodePathPart
import net.multigesture.kanama.builtins.nodePathSlice
import net.multigesture.kanama.builtins.parseNodePath

// Godot's NodePath methods (`getName(0)`, `getSubname(0)`, `isAbsolute()`, ...) are generated into
// the region below by scripts/generate_builtin_ops.py (task 134 D2): Godot's NodePath parse of
// [path], ported to Kotlin (`builtins/BoxedMethods.kt`), answers them; `hash()` runs in the engine.

/**
 * A pre-parsed scene tree path. Kanama value types are immutable snapshots; assign a new value back
 * to the Godot property after changing components.
 *
 * Generated from Godot docs: NodePath
 */
@JvmInline
value class NodePath(val path: String) {
  override fun toString(): String = path

  // ===== BEGIN GENERATED BUILTIN MEMBERS: NodePath (generate_builtin_ops.py) =====
  /**
   * Returns `true` if the node path is absolute. Unlike a relative path, an absolute path is
   * represented by a leading slash character (`/`) and always begins from the `SceneTree`. It can
   * be used to reliably access nodes from the root node (e.g. `"/root/Global"` if an autoload named
   * "Global" exists).
   *
   * Generated from Godot docs: NodePath.is_absolute
   */
  fun isAbsolute(): Boolean = parseNodePath(path)?.absolute ?: false

  /**
   * Returns the number of node names in the path. Property subnames are not included. For example,
   * `"../RigidBody2D/Sprite2D:texture"` contains 3 node names.
   *
   * Generated from Godot docs: NodePath.get_name_count
   */
  fun getNameCount(): Long = (parseNodePath(path)?.names?.size ?: 0).toLong()

  /**
   * Returns the node name indicated by `idx`, starting from 0. If `idx` is out of bounds, an error
   * is generated. See also `get_subname_count` and `get_name_count`.
   *
   * Generated from Godot docs: NodePath.get_name
   */
  fun getName(idx: Long): String = nodePathPart(path, idx, subnames = false)

  /**
   * Returns the number of property names ("subnames") in the path. Each subname in the node path is
   * listed after a colon character (`:`). For example,
   * `"Level/RigidBody2D/Sprite2D:texture:resource_name"` contains 2 subnames.
   *
   * Generated from Godot docs: NodePath.get_subname_count
   */
  fun getSubnameCount(): Long = (parseNodePath(path)?.subnames?.size ?: 0).toLong()

  /**
   * Returns the 32-bit hash value representing the node path's contents. Note: Node paths with
   * equal hash values are not guaranteed to be the same, as a result of hash collisions. Node paths
   * with different hash values are guaranteed to be different.
   *
   * Generated from Godot docs: NodePath.hash
   */
  fun hash(): Long =
    UtilityCalls.callMethod(
      NodePathMethods.hash,
      BoxedType.NODE_PATH,
      this,
      BoxedSig.NONE,
      arrayOf<Any?>(),
      BoxedType.INT,
    ) as Long

  /**
   * Returns the property name indicated by `idx`, starting from 0. If `idx` is out of bounds, an
   * error is generated. See also `get_subname_count`.
   *
   * Generated from Godot docs: NodePath.get_subname
   */
  fun getSubname(idx: Long): String = nodePathPart(path, idx, subnames = true)

  /**
   * Returns all node names concatenated with a slash character (`/`) as a single `StringName`.
   *
   * Generated from Godot docs: NodePath.get_concatenated_names
   */
  fun getConcatenatedNames(): String = nodePathConcatenated(path, subnames = false)

  /**
   * Returns all property subnames concatenated with a colon character (`:`) as a single
   * `StringName`.
   *
   * Generated from Godot docs: NodePath.get_concatenated_subnames
   */
  fun getConcatenatedSubnames(): String = nodePathConcatenated(path, subnames = true)

  /**
   * Returns the slice of the `NodePath`, from `begin` (inclusive) to `end` (exclusive), as a new
   * `NodePath`. The absolute value of `begin` and `end` will be clamped to the sum of
   * `get_name_count` and `get_subname_count`, so the default value for `end` makes it slice to the
   * end of the `NodePath` by default (i.e. `path.slice(1)` is a shorthand for `path.slice(1,
   * path.get_name_count() + path.get_subname_count())`). If either `begin` or `end` are negative,
   * they will be relative to the end of the `NodePath` (i.e. `path.slice(0, -2)` is a shorthand for
   * `path.slice(0, path.get_name_count() + path.get_subname_count() - 2)`).
   *
   * Generated from Godot docs: NodePath.slice
   */
  fun slice(begin: Long, end: Long = 2147483647L): NodePath =
    NodePath(nodePathSlice(path, begin, end))

  /**
   * Returns a copy of this node path with a colon character (`:`) prefixed, transforming it to a
   * pure property path with no node names (relative to the current node).
   *
   * Generated from Godot docs: NodePath.get_as_property_path
   */
  fun getAsPropertyPath(): NodePath = NodePath(nodePathAsPropertyPath(path))

  /**
   * Returns `true` if the node path has been constructed from an empty `String` (`""`).
   *
   * Generated from Godot docs: NodePath.is_empty
   */
  fun isEmpty(): Boolean = parseNodePath(path) == null

  // ===== END GENERATED BUILTIN MEMBERS: NodePath =====

  companion object {
    val EMPTY = NodePath("")
  }
}

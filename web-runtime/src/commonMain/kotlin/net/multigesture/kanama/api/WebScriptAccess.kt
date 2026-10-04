package net.multigesture.kanama.api

import net.multigesture.kanama.web.webScriptInstance

// The part of the task 133 script-authoring API the Web contract already carries: script checks and
// the tree accessors, with the native spelling so a shared script compiles for both. The node,
// script and preload delegates and the checked casts need a class-token table and `get_class` /
// `is_node_ready` in the Web call contract; they are a Web follow-up.

/** True when a Kotlin script of type [T] is attached to this object: GDScript's `body is Player`. */
inline fun <reified T : Any> GodotObject.isScript(): Boolean = webScriptInstance(handle.value) is T

/** The Kotlin script of type [T] attached to this object, or `null`: GDScript's `body as Player`. */
inline fun <reified T : Any> GodotObject.asScript(): T? = webScriptInstance(handle.value) as? T

/** The `SceneTree` this node is in; throws when the node is not inside the tree (as `getTree()`). */
val Node.tree: SceneTree
  get() = getTree()

/** The `Viewport` this node is in; throws when the node is not inside the tree. */
val Node.viewport: Viewport
  get() = getViewport() ?: throw IllegalStateException("Node is not inside the tree")

/** This node's parent; throws when it has none. */
val Node.parentNode: Node
  get() = getParent() ?: throw IllegalStateException("Node has no parent")

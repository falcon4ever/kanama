package net.multigesture.kanama.api

import kotlin.reflect.KClass
import net.multigesture.kanama.types.NodePath

// Task 142: the checked casts and typed node lookups of the shared ScriptAccess.kt (desktop, Android,
// iOS), with the same spelling, so a shared script compiles for the Web. A class's Godot name and
// constructor come from the generated table (WebGodotClasses.kt; Kotlin/Wasm has no reflection) and
// Godot is asked through `Object.is_class`, as on desktop.

/** [type]'s table entry; a class that is not a Web wrapper fails with its name. */
@PublishedApi
internal fun webClassToken(type: KClass<*>): WebGodotClassToken =
  webGodotClassToken(type)
    ?: throw IllegalArgumentException(
      "${type.simpleName} is not a Kanama wrapper of a Godot class on Web; checked casts take a " +
        "generated wrapper (Node, Timer, PackedScene, ...)"
    )

/** The Godot class of [value], for an error message (one generic call, on the error path only). */
@PublishedApi
internal fun webClassName(value: GodotObject): String = value.call("get_class") as? String ?: "Object"

/** How errors name an object, as on desktop: its node path inside the tree, else its class and name. */
@PublishedApi
internal fun webDescribeObject(value: GodotObject): String {
  if (!value.isClass("Node")) return webClassName(value)
  val node = value as? Node ?: Node(value.handle)
  if (node.isInsideTree()) {
    when (val path = node.call("get_path")) {
      is NodePath -> return path.path
      is String -> return path
    }
  }
  return "${webClassName(node)} \"${node.getName()}\""
}

/**
 * Desktop's `castOrNull` rule: Godot is asked (`Object.is_class`) except for `GodotObject`. A Node
 * that already is a `T` is returned as is; anything else is a new wrapper of the same object (a new
 * RefCounted wrapper keeps its own hold on the object, as every Web wrapper does).
 */
@PublishedApi
@Suppress("UNCHECKED_CAST")
internal fun <T : GodotObject> webCastOrNull(value: GodotObject, type: KClass<T>): T? {
  if (type == GodotObject::class) return value as T
  val token = webClassToken(type)
  if (!value.isClass(token.godotName)) return null
  if (value !is RefCounted && type.isInstance(value)) return value as T
  return token.wrap(value.handle) as T
}

@PublishedApi
internal fun <T : GodotObject> webCast(value: GodotObject, type: KClass<T>): T =
  webCastOrNull(value, type)
    ?: throw ClassCastException(
      "${webDescribeObject(value)} (${webClassName(value)}) is not a ${webClassToken(type).godotName}"
    )

@PublishedApi
internal fun <T : Node> webRequireNodeAs(owner: Node, path: String, type: KClass<T>): T {
  val node =
    owner.getNodeOrNull(path)
      ?: throw IllegalStateException("No node at \"$path\" under ${webDescribeObject(owner)}")
  return webCastOrNull(node, type)
    ?: throw IllegalStateException(
      "Node \"$path\" under ${webDescribeObject(owner)} is a ${webClassName(node)}, not a ${webClassToken(type).godotName}"
    )
}

/** This object as a `T` when Godot says it is one, else `null`: GDScript's `x as Camera3D`. */
inline fun <reified T : GodotObject> GodotObject.castOrNull(): T? = webCastOrNull(this, T::class)

/** [castOrNull] that throws a `ClassCastException` naming both classes instead of returning `null`. */
inline fun <reified T : GodotObject> GodotObject.cast(): T = webCast(this, T::class)

/** The node at [path] as a `T`, or `null` when the path is missing or the node is another class. */
inline fun <reified T : Node> Node.getNodeAs(path: String): T? =
  getNodeOrNull(path)?.let { webCastOrNull(it, T::class) }

/** [getNodeAs] for a [NodePath]. */
inline fun <reified T : Node> Node.getNodeAs(path: NodePath): T? = getNodeAs<T>(path.path)

/** The node at [path] as a `T`; throws naming the path when it is missing or another class. */
inline fun <reified T : Node> Node.requireAs(path: String): T = webRequireNodeAs(this, path, T::class)

/** [requireAs] for a [NodePath]. */
inline fun <reified T : Node> Node.requireAs(path: NodePath): T = webRequireNodeAs(this, path.path, T::class)

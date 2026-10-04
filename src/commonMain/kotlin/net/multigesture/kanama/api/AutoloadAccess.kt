package net.multigesture.kanama.api

import kotlin.reflect.KClass
import net.multigesture.kanama.types.NodePath

/**
 * The lookups behind the generated `Autoloads` object (task 133 C): `Autoloads.Settings` reads
 * `AutoloadAccess.script<Settings>("Settings")`. Game code uses `Autoloads`; these are public only
 * because the generated object lives in the project's script module.
 *
 * Resolution is GDScript's global autoload name: the node at `/root/<name>` of the running tree,
 * looked up on every read (keep it in a local in a hot loop). A missing node, a node of another
 * class, or one without the expected Kotlin script throws an `IllegalStateException` that names
 * the autoload.
 */
object AutoloadAccess {
  /** The autoload [name] as a `T` (its scene root or `extends` class), checked with `is_class`. */
  inline fun <reified T : Node> node(name: String): T = node(name, T::class)

  /** [node] for a class token. */
  fun <T : Node> node(name: String, type: KClass<T>): T {
    val node = lookup(name)
    return GodotClasses.castOrNull(node, type)
      ?: throw IllegalStateException(
        "Autoload '$name' at /root/$name is a ${node.getClassName()}, not a " +
          "${GodotClasses.token(type).godotName}; rebuild the scripts after changing the autoload " +
          "in Project Settings"
      )
  }

  /** The Kotlin script of type [T] on the autoload [name]. */
  inline fun <reified T : Any> script(name: String): T = script(name, T::class)

  /** [script] for a class token. */
  @Suppress("UNCHECKED_CAST")
  fun <T : Any> script(name: String, type: KClass<T>): T {
    val node = lookup(name)
    val instance = ScriptRuntime.scriptInstanceOf(node)
    if (instance != null && type.isInstance(instance)) return instance as T
    val found = instance?.let { "the script ${it::class.simpleName}" } ?: "no Kotlin script"
    throw IllegalStateException(
      "Autoload '$name' at /root/$name (${node.getClassName()}) has $found, not ${type.simpleName}"
    )
  }

  private fun lookup(name: String): Node {
    val root =
      try {
        SceneTree.root
      } catch (e: IllegalStateException) {
        throw IllegalStateException("Autoload '$name': no SceneTree is running (${e.message})", e)
      }
    return root.getNodeOrNull(NodePath(name))
      ?: throw IllegalStateException(
        "Autoload '$name' is not in the tree (no node at /root/$name): autoloads exist from " +
          "startup until quit; check the [autoload] entry in project.godot"
      )
  }
}

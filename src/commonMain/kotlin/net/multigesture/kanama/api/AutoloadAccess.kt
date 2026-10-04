package net.multigesture.kanama.api

import kotlin.concurrent.Volatile
import kotlin.reflect.KClass
import net.multigesture.kanama.types.NodePath

/**
 * The lookups behind the generated `Autoloads` object (task 133 C): `Autoloads.Settings` reads
 * `AutoloadAccess.script<Settings>("Settings")`. Game code uses `Autoloads`; these are public only
 * because the generated object lives in the project's script module.
 *
 * Resolution is GDScript's global autoload name: the node at `/root/<name>` of the running tree. A
 * missing node, a node of another class, or one without the expected Kotlin script throws an
 * `IllegalStateException` that names the autoload.
 *
 * Task 133 C2: the node is resolved once, on the main thread, and kept like GDScript keeps its
 * global; each later read only checks that the node is still alive (an instance-id check, safe from
 * any thread) and, for `script`, reads the node's current script object (so a hot-reloaded autoload
 * is seen). A freed node is resolved again on the next main-thread read. A worker thread can read
 * an autoload the main thread has resolved; one it has not resolved yet throws, because the scene
 * tree may only be queried from the main thread.
 */
object AutoloadAccess {
  /**
   * Resolved autoload nodes by name. Only the main thread writes it (copy on write, a handful of
   * entries); any thread reads it.
   */
  @Volatile private var resolved: Map<String, Node> = emptyMap()

  /** The autoload [name] as a `T` (its scene root or `extends` class), checked with `is_class`. */
  inline fun <reified T : Node> node(name: String): T = node(name, T::class)

  /** [node] for a class token. */
  @Suppress("UNCHECKED_CAST")
  fun <T : Node> node(name: String, type: KClass<T>): T {
    cached(name)?.let { if (type.isInstance(it)) return it as T }
    val node = lookup(name)
    return GodotClasses.castOrNull(node, type)?.also { remember(name, it) }
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
    val node = cached(name) ?: lookup(name).also { remember(name, it) }
    val instance = ScriptRuntime.scriptInstanceOf(node)
    if (instance != null && type.isInstance(instance)) return instance as T
    val found = instance?.let { "the script ${it::class.simpleName}" } ?: "no Kotlin script"
    throw IllegalStateException(
      "Autoload '$name' at /root/$name (${node.getClassName()}) has $found, not ${type.simpleName}"
    )
  }

  /** The resolved node of [name] while it is alive (any thread), else null. */
  private fun cached(name: String): Node? =
    resolved[name]?.takeIf { it.isAlive(it.handle.segment) }

  private fun remember(name: String, node: Node) {
    resolved = resolved + (name to node)
  }

  /** Resolves [name] in the running tree: main thread only, as every scene-tree query. */
  private fun lookup(name: String): Node {
    if (!Thread.isMainThread()) {
      throw IllegalStateException(
        "Autoload '$name' was read on a worker thread before the main thread resolved it (or " +
          "after its node was freed). The scene tree may only be queried from the main thread: " +
          "read Autoloads.$name once on the main thread first (in _ready, say), or hand the " +
          "value to the thread"
      )
    }
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

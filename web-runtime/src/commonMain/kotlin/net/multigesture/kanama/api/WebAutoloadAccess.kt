package net.multigesture.kanama.api

import net.multigesture.kanama.web.webScriptInstance

/**
 * The Web lookups behind the generated `Autoloads` object (task 133 C), the counterpart of the
 * native `AutoloadAccess`. The Web call contract has no `Engine.get_main_loop`, so the node at
 * `/root/<name>` is found from the script whose callback is running (Web script code always runs
 * inside one: the static `SceneTree.quit()` reaches the tree the same way); a typed node is checked
 * by its Godot class name (`is_class`) and wrapped by its constructor, as Web has no class-token
 * table yet.
 */
object AutoloadAccess {
  /** The autoload [name] checked as a [godotClass] and wrapped by [wrap]. */
  fun <T : Node> node(name: String, godotClass: String, wrap: (GodotHandle) -> T): T {
    val node = lookup(name)
    if (!node.isClass(godotClass)) {
      throw IllegalStateException(
        "Autoload '$name' at /root/$name is not a $godotClass; rebuild the scripts after changing " +
          "the autoload in Project Settings"
      )
    }
    return wrap(node.handle)
  }

  /** The Kotlin script of type [T] on the autoload [name]. */
  inline fun <reified T : Any> script(name: String): T {
    val node = lookupNode(name)
    val instance = webScriptInstance(node.handle.value)
    return instance as? T
      ?: throw IllegalStateException(
        "Autoload '$name' at /root/$name has " +
          (instance?.let { "the script ${it::class.simpleName}" } ?: "no Kotlin script") +
          ", not ${T::class.simpleName}"
      )
  }

  @PublishedApi internal fun lookupNode(name: String): Node = lookup(name)

  private fun lookup(name: String): Node {
    val owner = WebFrameScheduler.currentOwnerOrZero()
    if (owner <= 0) {
      throw IllegalStateException(
        "Autoload '$name' was read outside a script callback; on Web an autoload is reached " +
          "from the running script"
      )
    }
    return Node(GodotHandle(owner)).getNodeOrNull("/root/$name")
      ?: throw IllegalStateException(
        "Autoload '$name' is not in the tree (no node at /root/$name): autoloads exist from " +
          "startup until quit; check the [autoload] entry in project.godot"
      )
  }
}

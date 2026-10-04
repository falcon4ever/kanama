package net.multigesture.kanama.binding

import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.ProjectSettings
import net.multigesture.kanama.api.SceneTree
import net.multigesture.kanama.binding.runtime.ScriptErrors

/**
 * Task 133 C2 — a desktop hot reload re-creates the Kotlin script objects of the autoloads.
 *
 * Autoloads outlive the scene reload that refreshes every other script object, so after the new
 * templates are bound ([KanamaScript.rebindAllScripts]) each node of an autoload's subtree whose
 * Kotlin object came from the retired class loader gets a new script instance, built from the new
 * build: its script is detached and attached again (which frees the old instance), its exported
 * (stored) property values are set back, and `_ready` runs again (children first, as Godot readies
 * a tree). Every other Kotlin field starts from its initializer, as after a scene reload. Without
 * this, the autoload would keep the previous build's code and class loader until the game quits.
 */
internal object AutoloadReload {
  private const val NOTIFICATION_READY = 13
  private const val PROPERTY_USAGE_STORAGE = 2L

  /** Re-creates the autoload script instances whose class [retired] loaded; returns how many. */
  fun recreate(retired: ClassLoader?): Int {
    if (retired == null) return 0
    val root = runCatching { SceneTree.root }.getOrNull() ?: return 0
    val stale = mutableListOf<Node>()
    for (i in 0 until root.getChildCount()) {
      val child = root.getChild(i) ?: continue
      if (!ProjectSettings.hasSetting("autoload/${child.getName()}")) continue
      collect(child, retired, stale)
    }
    stale.forEach(::reattach)
    // `stale` is in tree pre-order, so reversed every node comes after its descendants.
    stale.asReversed().forEach { node ->
      if (node.isInsideTree()) {
        runCatching { node.notification(NOTIFICATION_READY) }
          .onFailure { ScriptErrors.report(it, "${node.getName()}._ready after hot reload") }
      }
    }
    return stale.size
  }

  private fun collect(node: Node, retired: ClassLoader, into: MutableList<Node>) {
    val kotlinObject = ScriptBridge.kotlinObjectForOwner(node.handle.segment)
    if (kotlinObject != null && kotlinObject.javaClass.classLoader === retired) into += node
    for (i in 0 until node.getChildCount()) {
      node.getChild(i)?.let { collect(it, retired, into) }
    }
  }

  @Suppress("UNCHECKED_CAST")
  private fun reattach(node: Node) {
    val script = node.getScript() as? GodotObject ?: return
    val stored =
      (script.call("get_script_property_list") as? List<Map<String, Any?>>)
        .orEmpty()
        .filter { ((it["usage"] as? Number)?.toLong() ?: 0L) and PROPERTY_USAGE_STORAGE != 0L }
        .mapNotNull { property -> (property["name"] as? String)?.let { it to node.get(it) } }
    // The node may hold the only reference to the script resource: keep it alive while detached.
    script.call("reference")
    try {
      node.call("set_script", null)
      node.call("set_script", script)
    } finally {
      script.call("unreference")
    }
    stored.forEach { (name, value) -> node.set(name, value) }
  }
}

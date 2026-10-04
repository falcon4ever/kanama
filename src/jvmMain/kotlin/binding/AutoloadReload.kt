package net.multigesture.kanama.binding

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.api.ClassDB
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.ProjectSettings
import net.multigesture.kanama.api.ResourceLoader
import net.multigesture.kanama.api.SceneTree
import net.multigesture.kanama.binding.runtime.ScriptErrors
import net.multigesture.kanama.binding.runtime.SignalCallables
import net.multigesture.kanama.binding.runtime.SignalCallbackRegistry

/**
 * Task 133 C2 — a desktop hot reload resets the Kotlin autoloads in place.
 *
 * Autoloads outlive the scene reload that refreshes every other script object, and they must stay
 * the same nodes: GDScript reaches an autoload through a global Godot sets once at startup, which
 * nothing can repoint. So each autoload whose subtree has a Kotlin object from the retired class
 * loader is reset, keeping the node:
 * 1. the lambda connections the old script objects made (their receiver is a reset node) are
 *    disconnected, so old closures stop running and stop pinning the retired loader; connections
 *    other objects made to the node stay;
 * 2. runtime-created nodes (a child whose `owner` is not the autoload or a node of its scene) are
 *    freed; the autoload's scene nodes stay;
 * 3. each reset node gets a fresh script instance of the new build (its script is detached and
 *    attached again);
 * 4. its stored properties are set as a fresh start would: the autoload scene's root values, then
 *    an old value where the old object had that property and the value's Variant type equals the
 *    new property's type (never an untyped copy: a property whose type changed keeps its default);
 * 5. `_ready` runs again, children before parents and siblings in tree order, as Godot readies a
 *    tree.
 */
internal object AutoloadReload {
  private const val NOTIFICATION_READY = 13
  private const val PROPERTY_USAGE_STORAGE = 2L
  private const val TYPE_NIL = 0L

  /** Resets the autoloads whose Kotlin code [retired] loaded; returns the reset node count. */
  fun recreate(retired: ClassLoader?): Int {
    if (retired == null) return 0
    val root = runCatching { SceneTree.root }.getOrNull() ?: return 0
    val autoloads =
      (0 until root.getChildCount())
        .mapNotNull { root.getChild(it) }
        .filter { ProjectSettings.hasSetting("autoload/${it.getName()}") }
    var reset = 0
    for (autoload in autoloads) {
      val scene = mutableListOf<Node>()
      val runtime = mutableListOf<Node>()
      collect(autoload, setOf(autoload.instanceId), scene, runtime)
      val stale = scene.filter { kotlinObjectOf(it)?.javaClass?.classLoader === retired }
      if (stale.isEmpty()) continue
      disconnectLambdas(stale)
      for (node in runtime) {
        node.getParent()?.removeChild(node)
        node.call("free")
      }
      val rootValues = sceneRootValues(autoload)
      for (node in stale) reattach(node, if (node === autoload) rootValues else emptyList())
      readyPostOrder(autoload, stale.map { it.instanceId }.toSet())
      reset += stale.size
    }
    return reset
  }

  private fun kotlinObjectOf(node: Node): Any? =
    ScriptBridge.kotlinObjectForOwner(node.handle.segment)

  /**
   * Pre-order walk of [node]'s subtree: a child whose owner is a scene node ([owners]) is a scene
   * node too (an instanced sub-scene's nodes are owned by its root, itself a scene node); any other
   * child was created at run time and is collected (not descended into) in [runtime].
   */
  private fun collect(
    node: Node,
    owners: Set<Long>,
    scene: MutableList<Node>,
    runtime: MutableList<Node>,
  ) {
    scene += node
    val sceneOwners = owners + node.instanceId
    for (i in 0 until node.getChildCount()) {
      val child = node.getChild(i) ?: continue
      val owner = child.getOwner()
      if (owner != null && owner.instanceId in owners) collect(child, sceneOwners, scene, runtime)
      else runtime += child
    }
  }

  /** Disconnects the lambda connections whose receiver is one of [stale] (made by old code). */
  private fun disconnectLambdas(stale: List<Node>) {
    val connections = SignalCallbackRegistry.connectionsTo(stale.map { it.instanceId }.toSet())
    for ((id, connection) in connections) {
      if (GD.isInstanceIdValid(connection.emitterInstanceId)) {
        SignalCallables.disconnect(
          MemorySegment.ofAddress(connection.emitterAddress),
          connection.signal,
          connection.receiverInstanceId,
          id,
        )
      }
      SignalCallbackRegistry.unregister(id)
    }
  }

  /** The root node's property values stored in the autoload's scene file (empty for a script). */
  private fun sceneRootValues(autoload: Node): List<Pair<String, Any?>> {
    val path = autoload.getSceneFilePath().takeIf { it.isNotEmpty() } ?: return emptyList()
    return ResourceLoader.loadPackedScene(path)?.use { scene ->
      scene.getState()?.use { state ->
        (0 until state.getNodePropertyCount(0)).map {
          state.getNodePropertyName(0, it) to state.getNodePropertyValue(0, it)
        }
      }
    } ?: emptyList()
  }

  /** The stored script properties of [node] (name to Variant type), the engine class's excluded. */
  @Suppress("UNCHECKED_CAST")
  private fun scriptProperties(node: Node, classProperties: Set<String>): Map<String, Long> =
    node
      .getPropertyList()
      .filter { ((it["usage"] as? Number)?.toLong() ?: 0L) and PROPERTY_USAGE_STORAGE != 0L }
      .mapNotNull { property ->
        val name = property["name"] as? String ?: return@mapNotNull null
        if (name == "script" || name in classProperties) return@mapNotNull null
        name to ((property["type"] as? Number)?.toLong() ?: TYPE_NIL)
      }
      .toMap()

  private fun reattach(node: Node, rootValues: List<Pair<String, Any?>>) {
    val script = node.getScript() as? GodotObject ?: return
    val classProperties =
      ClassDB.classGetPropertyList(node.getClassName()).mapNotNull { it["name"] as? String }.toSet()
    val old = scriptProperties(node, classProperties).mapValues { (name, _) -> node.get(name) }
    // The node may hold the only reference to the script resource: keep it alive while detached.
    script.call("reference")
    try {
      node.call("set_script", null)
      node.call("set_script", script)
    } finally {
      script.call("unreference")
    }
    val fresh = scriptProperties(node, classProperties)
    fun restore(name: String, value: Any?) {
      val type = fresh[name] ?: return
      // Only a value of the new property's own Variant type: no untyped copy across builds.
      if (type != TYPE_NIL && GD.typeOf(value) == type) node.set(name, value)
    }
    for ((name, value) in rootValues) restore(name, value)
    for ((name, value) in old) restore(name, value)
  }

  /** `_ready` of each reset node of [node]'s subtree: children first, siblings in tree order. */
  private fun readyPostOrder(node: Node, reset: Set<Long>) {
    for (i in 0 until node.getChildCount()) node.getChild(i)?.let { readyPostOrder(it, reset) }
    if (node.instanceId in reset && node.isInsideTree()) {
      runCatching { node.notification(NOTIFICATION_READY) }
        .onFailure { ScriptErrors.report(it, "${node.getName()}._ready after hot reload") }
    }
  }
}

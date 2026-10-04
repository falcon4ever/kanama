package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotError
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.MainThread
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.ResourceLoader
import net.multigesture.kanama.api.ResourceSaver
import net.multigesture.kanama.api.kotlinScriptInstance
import net.multigesture.kanama.api.newScriptInstance

// Review probe (task 132 re-review): the ResourceCache re-reference window. A cached resource whose
// KanamaScript object was collected but whose owner release has not been drained yet is loaded
// again (cache hit) in the same frame: does it keep the values it was loaded with?
@ScriptClass(attachTo = "Node")
class CacheRecreateProbe(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  private var firstId = 0L

  @OnReady
  fun ready() {
    val saved =
      newScriptInstance<OwnerLinkResource>().use { h ->
        h.instance.cash = 4242
        ResourceSaver.save(h.resource, PATH)
      }
    val firstRead = loadAndDrop()
    // Collect the dropped script object and let the cleaner thread queue the owner release.
    // The main-thread drain only runs next frame, so the window is open now.
    repeat(4) {
      System.gc()
      Thread.sleep(100)
    }
    val alive = GD.isInstanceIdValid(firstId)
    val again = ResourceLoader.load(PATH) // ResourceLoader.CacheMode.REUSE: a cache hit if alive
    val againId = again?.instanceId ?: 0L
    val againRead = again?.let { GodotObject(it.handle).get("cash") }
    val script = again?.kotlinScriptInstance<OwnerLinkResource>()
    System.err.println(
      "[kanama:kt] CacheRecreateProbe saved=${saved == GodotError.OK} first_read=$firstRead " +
        "alive_before_reload=$alive same_object=${againId == firstId} reload_read=$againRead " +
        "kotlin_cash=${script?.cash}"
    )
    again?.close()
    MainThread.postNextFrame { MainThread.postNextFrame { self.getTree()?.quit() } }
  }

  // Load with the cache, read through the engine, close the wrapper, keep nothing.
  private fun loadAndDrop(): Any? {
    val res = ResourceLoader.load(PATH) ?: return null
    firstId = res.instanceId
    val read = GodotObject(res.handle).get("cash")
    res.kotlinScriptInstance<OwnerLinkResource>() // touch the script object, then drop it
    res.close()
    return read
  }

  private companion object {
    const val PATH = "user://cache_recreate_probe.tres"
  }
}

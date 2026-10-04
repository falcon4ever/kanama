package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.MainThread
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.Resource
import net.multigesture.kanama.api.newScriptInstance

/**
 * Task 132 probe, run by `scripts/runtime_smoke.sh` as its own scene
 * (`property_retain_smoke.tscn`): the references a script-property setter takes are released with
 * the owner even when the GC has already collected the owner's Kotlin script object.
 *
 * A `KanamaScript` resource whose `items: List<RetainedItemResource>` is set through the engine
 * (`Object.set`, what loading a `.tres` does) holds a reference on each element. Re-setting the
 * property releases the old elements (`old_released`). Dropping the owner lets the GC collect its
 * Kotlin object (the owner link goes weak), the owner dies, and its elements must die too
 * (`items_dead`). Before the per-owner registry (883c936c) the elements' references were given back
 * only through the Kotlin object, which was gone: they leaked (City-Builder's "244 resources still
 * in use at exit").
 */
@ScriptClass(attachTo = "Node")
class PropertyRetainSmoke(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  private var ownerId = 0L
  private var firstIds = emptyList<Long>()
  private var secondIds = emptyList<Long>()
  private var held = 0
  private var frames = 0

  @OnReady
  fun ready() {
    setAndDrop()
    step()
  }

  // Its own function: no stack slot of ready() keeps the owner or the items reachable.
  private fun setAndDrop() {
    newScriptInstance<RetainingOwnerResource>().use { owner ->
      ownerId = owner.resource.instanceId
      firstIds = setItems(owner.resource, 3)
      secondIds = setItems(owner.resource, 2)
      held = owner.instance.items.size
    }
  }

  // [count] new items, set through the engine; this function's own references are closed after.
  private fun setItems(owner: Resource, count: Int): List<Long> {
    val items = (1..count).map { newScriptInstance<RetainedItemResource>() }
    items.forEachIndexed { index, item -> item.instance.tag = index.toLong() }
    owner.set("items", items.map { it.resource })
    val ids = items.map { it.resource.instanceId }
    items.forEach { it.close() }
    return ids
  }

  private fun alive(ids: List<Long>) = ids.count { GD.isInstanceIdValid(it) }

  private fun step() {
    MainThread.postNextFrame {
      System.gc()
      frames += 1
      val done = !GD.isInstanceIdValid(ownerId) && alive(firstIds) == 0 && alive(secondIds) == 0
      if (done || frames >= MAX_FRAMES) {
        System.err.println(
          "[kanama:kt] PropertyRetainSmoke held=$held old_released=${alive(firstIds) == 0} " +
            "owner_dead=${!GD.isInstanceIdValid(ownerId)} items_dead=${alive(secondIds) == 0} " +
            "frames=$frames"
        )
        self.getTree()?.quit()
      } else step()
    }
  }

  private companion object {
    const val MAX_FRAMES = 120
  }
}

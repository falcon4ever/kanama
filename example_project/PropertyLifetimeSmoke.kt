package net.multigesture.kanama.example

import java.lang.ref.WeakReference
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.MainThread
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.Node3D
import net.multigesture.kanama.api.PackedScene
import net.multigesture.kanama.api.Resource
import net.multigesture.kanama.api.StandardMaterial3D
import net.multigesture.kanama.api.asScript
import net.multigesture.kanama.api.newScriptInstance
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Task 132 review probe, run by `scripts/runtime_smoke.sh` as its own scene
 * (`property_lifetime_smoke.tscn`): the lifetime of what a script property holds.
 * - `swap`: `set_script(null)` on a live `KanamaScript` resource. Its detached script object must
 *   be collectable, so the elements of its `items` die (a9b495ac: the owner link stayed STRONG and
 *   the cleaner pinned the script object, `swap_items_dead=false`).
 * - `thread`: the setter runs on a worker thread, then the owner dies: the elements die too.
 * - `alias`: a Kotlin alias of a resource the engine set (`cached = holder.res`) stays valid after
 *   the engine sets the property again and the original is closed, as in GDScript (a9b495ac: the
 *   registry closed the alias's wrapper, `alias_valid=false`).
 * - `node_paths_kept`: Node-typed exports (`Node?`, `List<Node>`) take no reference: a
 *   `RefCounted.reference` ptrcall on a Node writes into its `scene_file_path` (the iOS shim did).
 * - `items`: the iOS self-test row `property-retain`, measured the same way. 20 plain resources
 *   (`StandardMaterial3D`) set into a `List<Material>` property, set again with 20 new ones, then
 *   the holder freed. The registry's own reference is exactly one per element (`reset_drop=1`: a
 *   new set drops each old element's count by one), and once nothing else holds them every element
 *   dies (`old_dead`, `freed_dead`). Here the old elements also have their Kotlin field wrappers,
 *   which the GC releases; on iOS the property keeps raw handles, so they die at once. 0467069e on
 *   the iPhone: the shim leaked its copy of the set Array, whose reference kept every element
 *   alive.
 */
@ScriptClass(attachTo = "Node")
class PropertyLifetimeSmoke(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  private var swapOwner: Resource? = null
  private var swapIds = emptyList<Long>()
  private var swapScript: WeakReference<Any>? = null
  private var firstItems = emptyList<Long>()
  private var secondItems = emptyList<Long>()
  private var threadOwnerId = 0L
  private var threadIds = emptyList<Long>()
  private var frames = 0

  @OnReady
  fun ready() {
    swapRow()
    threadRow()
    val alias = aliasRow()
    val nodePaths = nodeRow()
    System.err.println(
      "[kanama:kt] PropertyLifetimeSmoke alias_valid=$alias node_paths_kept=$nodePaths"
    )
    itemsRow()
    step()
  }

  private fun swapRow() {
    val owner = newScriptInstance<RetainingOwnerResource>()
    swapScript = WeakReference(owner.instance)
    val items = (1..3).map { newScriptInstance<RetainedItemResource>() }
    owner.resource.set("items", items.map { it.resource })
    swapIds = items.map { it.resource.instanceId }
    items.forEach { it.close() }
    owner.resource.setScript(null)
    swapOwner = owner.resource
  }

  private fun threadRow() {
    val owner = newScriptInstance<RetainingOwnerResource>()
    threadOwnerId = owner.resource.instanceId
    val items = (1..3).map { newScriptInstance<RetainedItemResource>() }
    val list = items.map { it.resource }
    val worker = Thread { owner.resource.set("items", list) }
    worker.start()
    worker.join()
    threadIds = items.map { it.resource.instanceId }
    items.forEach { it.close() }
    owner.close()
  }

  private fun aliasRow(): Boolean {
    val holderNode = self.getNode("Holder")!!
    val holder = holderNode.asScript<PropertyHolder>()!!
    val scene = PackedScene.create()
    holderNode.set("res", scene)
    val cached = holder.res!!
    holderNode.set("res", null)
    scene.close()
    val valid = GD.isInstanceIdValid(cached.instanceId) && cached.getClassName() == "PackedScene"
    cached.close()
    return valid
  }

  private fun nodeRow(): Boolean {
    val holderNode = self.getNode("Holder")!!
    val a = Node3D(GodotHandle(ObjectCalls.constructObject("Node3D")))
    val b = Node3D(GodotHandle(ObjectCalls.constructObject("Node3D")))
    a.setSceneFilePath("res://property_lifetime_a.tscn")
    b.setSceneFilePath("res://property_lifetime_b.tscn")
    holderNode.set("node", a)
    holderNode.set("nodes", listOf(a, b))
    holderNode.set("node", null)
    holderNode.set("nodes", emptyList<Node>())
    val kept =
      a.getSceneFilePath() == "res://property_lifetime_a.tscn" &&
        b.getSceneFilePath() == "res://property_lifetime_b.tscn"
    ObjectCalls.destroyObject(a.handle.segment)
    ObjectCalls.destroyObject(b.handle.segment)
    return kept
  }

  // 20 plain materials set into the holder's List<Material>, the row's own wrappers closed.
  private fun setItems(holderNode: Node): Pair<List<Long>, Resource> {
    val items = (1..20).map { StandardMaterial3D.create() }
    holderNode.set("items", items)
    val ids = items.map { it.instanceId }
    items.forEach { it.close() }
    // A view kept to read the first element's count (it takes no reference of its own).
    return ids to Resource.fromHandle(items[0].handle)!!
  }

  private fun itemsRow() {
    val holderNode = self.getNode("Holder")!!
    val (first, firstView) = setItems(holderNode)
    val before = firstView.getReferenceCount()
    val (second, _) = setItems(holderNode)
    val resetDrop = before - firstView.getReferenceCount()
    firstItems = first
    secondItems = second
    System.err.println(
      "[kanama:kt] PropertyLifetimeSmoke items reset_drop=$resetDrop " +
        "held=${second.count { GD.isInstanceIdValid(it) }}"
    )
    // The holder's free releases the second set (checked by the frame loop).
    holderNode.queueFree()
  }

  private fun alive(ids: List<Long>) = ids.count { GD.isInstanceIdValid(it) }

  private fun step() {
    MainThread.postNextFrame {
      System.gc()
      frames += 1
      val done =
        alive(swapIds) == 0 &&
          alive(threadIds) == 0 &&
          !GD.isInstanceIdValid(threadOwnerId) &&
          alive(firstItems) == 0 &&
          alive(secondItems) == 0
      if (done || frames >= MAX_FRAMES) {
        System.err.println(
          "[kanama:kt] PropertyLifetimeSmoke swap_items_dead=${alive(swapIds) == 0} " +
            "swap_owner_alive=${GD.isInstanceIdValid(swapOwner!!.instanceId)} " +
            "swap_script_collected=${swapScript?.get() == null} " +
            "thread_owner_dead=${!GD.isInstanceIdValid(threadOwnerId)} " +
            "thread_items_dead=${alive(threadIds) == 0} old_dead=${alive(firstItems) == 0} " +
            "freed_dead=${alive(secondItems) == 0} frames=$frames"
        )
        swapOwner?.close()
        swapOwner = null
        self.getTree()?.quit()
      } else step()
    }
  }

  private companion object {
    const val MAX_FRAMES = 120
  }
}

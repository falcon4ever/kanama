package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.FileAccess
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotError
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.MainThread
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.Resource
import net.multigesture.kanama.api.ResourceLoader
import net.multigesture.kanama.api.ResourceSaver
import net.multigesture.kanama.api.kotlinScriptInstance
import net.multigesture.kanama.api.newScriptInstance

/**
 * Task 132 probe, run by `scripts/runtime_smoke.sh` as its own scene (`script_owner_smoke.tscn`): a
 * script object keeps its RefCounted owner alive, as in GDScript.
 *
 * The City-Builder pattern (its `Builder.loadMap`): load a resource whose script extends
 * `KanamaScript`, keep ONLY the script object (`kotlinScriptInstance`), let the garbage collector
 * run for several frames, then use it -- read a property through the engine and save the resource
 * again. Before the fix (and with `KANAMA_SCRIPT_OWNER_LINKS=0`, the red run) the dropped load
 * wrapper's GC release freed the resource under the script object (`alive_after_gc=false`). Then
 * the script object is dropped too, and the resource must die (`dies_after_drop=true`): the script
 * object's reference is released, not leaked.
 *
 * `newScriptInstance<T>().instance` kept with its handle dropped is the same case (`created_*`).
 *
 * A plain script class (`SmokeResource`, not a `KanamaScript`) cannot anchor its instance, so its
 * owner keeps its pre-fallback lifetime: the dropped handle's GC release is parked
 * (`plain_alive_after_gc=true`) and runs once the script is detached
 * (`plain_dies_after_detach=true`).
 */
@ScriptClass(attachTo = "Node")
class ScriptOwnerSmoke(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  private var loaded: OwnerLinkResource? = null
  private var created: OwnerLinkResource? = null
  private var loadedId = 0L
  private var createdId = 0L
  private var plain: SmokeResource? = null
  private var plainId = 0L
  private var frames = 0

  @OnReady
  fun ready() {
    val saved =
      newScriptInstance<OwnerLinkResource>().use { handle ->
        handle.instance.cash = 4242
        ResourceSaver.save(handle.resource, PATH)
      }
    keepOnlyScriptObjects()
    System.err.println(
      "[kanama:kt] ScriptOwnerSmoke saved=${saved == GodotError.OK} loaded=${loaded != null} " +
        "created=${created != null}"
    )
    collectFrames(FRAMES) { useAfterGc() }
  }

  // Its own function: no stack slot of ready() keeps the dropped wrappers reachable.
  private fun keepOnlyScriptObjects() {
    loaded =
      ResourceLoader.load(PATH, cacheMode = ResourceLoader.CacheMode.IGNORE)
        ?.kotlinScriptInstance<OwnerLinkResource>()
    loadedId = loaded?.let { GodotObject(it.godotObject).instanceId } ?: 0L
    created = newScriptInstance<OwnerLinkResource>().instance
    created?.cash = 77
    createdId = created?.let { GodotObject(it.godotObject).instanceId } ?: 0L
    plain = newScriptInstance<SmokeResource>().instance
    plainId = plain?.let { GodotObject(it.godotObject).instanceId } ?: 0L
  }

  private fun useAfterGc() {
    val loadedAlive = GD.isInstanceIdValid(loadedId)
    val createdAlive = GD.isInstanceIdValid(createdId)
    var engineRead: Any? = null
    var resaved = false
    var createdRead: Any? = null
    // Only touch the engine through a live object: the red run must report, not crash.
    if (loadedAlive) {
      val map = loaded!!
      engineRead = GodotObject(map.godotObject).get("cash")
      Resource.fromObject(GodotObject(map.godotObject))?.use {
        resaved = ResourceSaver.save(it, RESAVE_PATH) == GodotError.OK
      }
      resaved = resaved && FileAccess.getFileAsString(RESAVE_PATH).contains("4242")
    }
    if (createdAlive) createdRead = GodotObject(created!!.godotObject).get("cash")
    val plainAlive = GD.isInstanceIdValid(plainId)
    // Detaching the script releases the pinned owner (it then dies with no other reference).
    if (plainAlive) GodotObject(plain!!.godotObject).setScript(null)
    System.err.println(
      "[kanama:kt] ScriptOwnerSmoke alive_after_gc=$loadedAlive engine_read=$engineRead " +
        "resaved=$resaved created_alive_after_gc=$createdAlive created_read=$createdRead " +
        "plain_alive_after_gc=$plainAlive"
    )
    loaded = null
    created = null
    plain = null
    collectFrames(FRAMES) {
      System.err.println(
        "[kanama:kt] ScriptOwnerSmoke dies_after_drop=${!GD.isInstanceIdValid(loadedId)} " +
          "created_dies_after_drop=${!GD.isInstanceIdValid(createdId)} " +
          "plain_dies_after_detach=${!GD.isInstanceIdValid(plainId)}"
      )
      self.getTree()?.quit()
    }
  }

  // [count] frames, each with a full GC, then [then].
  private fun collectFrames(count: Int, then: () -> Unit) {
    frames = 0
    fun step() {
      MainThread.postNextFrame {
        System.gc()
        frames += 1
        if (frames >= count) then() else step()
      }
    }
    step()
  }

  private companion object {
    const val PATH = "user://script_owner_smoke.tres"
    const val RESAVE_PATH = "user://script_owner_smoke_resaved.tres"
    const val FRAMES = 20
  }
}

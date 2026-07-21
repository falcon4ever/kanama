package net.multigesture.kanama.example

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.Tool
import net.multigesture.kanama.api.FileAccess
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.ManualGodotLifetimeApi
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.ResourceLoader
import net.multigesture.kanama.api.ResourceSaver
import net.multigesture.kanama.api.kotlinScriptInstance
import net.multigesture.kanama.api.newScriptInstance

// issue #38 — programmatic creation of a script-backed custom resource from Kotlin.
// @Tool so _ready fires in the editor as well as at runtime; the editor run exercises
// the force-real path (a non-@Tool resource script would otherwise be instantiated as
// an inert placeholder, and `newScriptInstance<SmokeResource>()` would fail to cast).
@ScriptClass(attachTo = "Node")
@Tool
class ResourceForgeSmoke(godotObject: MemorySegment) : KanamaScript<Node>(godotObject, ::Node) {

  @OnReady
  @OptIn(ManualGodotLifetimeApi::class)
  fun ready() {
    val path = "user://forged_smoke.tres"
    val owned =
      try {
        newScriptInstance<SmokeResource>()
      } catch (e: Throwable) {
        System.err.println("[kanama:kt] ResourceForgeSmoke create_failed=${e.message}")
        return
      }
    // `use { }` releases the owning reference on the way out — the supported release path.
    // No `Resource.fromHandle(...).close()` on a view the docs say is non-owning.
    owned.use { handle ->
      val res = handle.instance
      res.payload = "forged"
      res.customIntValue = 7
      val live = res.payload == "forged" && res.customIntValue == 7L

      // Owned like GDScript .new(): survives this save (our +1 outlives the transient Ref<>
      // the save wraps it in). `handle.resource` is the supported owning Resource.
      val saveError = ResourceSaver.save(handle.resource, path)

      // Text-level proof the script reference and payload serialized.
      val text = if (saveError == 0L) FileAccess.getFileAsString(path) else ""
      val scriptRef = text.contains("SmokeResource.kt")
      val payloadSaved = text.contains("forged")

      // Real reload round-trip: load the .tres back (cache-ignore) and resolve the live Kanama
      // instance — proving Godot can deserialize it into a working resource, not just that
      // text was written. `Resource` is now a `GodotObject` (task 51), so `kotlinScriptInstance`
      // resolves directly off the reloaded wrapper, which `use { }` then releases.
      var reloadPayload = ""
      var reloadInt = -1L
      if (saveError == 0L) {
        ResourceLoader.load(path, cacheMode = ResourceLoader.CACHE_MODE_IGNORE)?.use { reloaded ->
          reloaded.kotlinScriptInstance<SmokeResource>()?.let { r ->
            reloadPayload = r.payload
            reloadInt = r.customIntValue
          }
        }
      }
      val reloadOk = reloadPayload == "forged" && reloadInt == 7L

      System.err.println(
        "[kanama:kt] ResourceForgeSmoke live=$live save_error=$saveError " +
          "script_ref=$scriptRef payload_saved=$payloadSaved reload_ok=$reloadOk"
      )
    }
  }
}

package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.Tool
import net.multigesture.kanama.api.FileAccess
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.ManualGodotLifetimeApi
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.Resource
import net.multigesture.kanama.api.ResourceSaver
import net.multigesture.kanama.api.newScriptInstance
import java.lang.foreign.MemorySegment

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
        val created = try {
            newScriptInstance<SmokeResource>()
        } catch (e: Throwable) {
            System.err.println("[kanama:kt] ResourceForgeSmoke create_failed=${e.message}")
            return
        }
        created.payload = "forged"
        created.customIntValue = 7
        val live = created.payload == "forged" && created.customIntValue == 7L

        // Owned like GDScript .new(): the created resource survives this save, which
        // wraps it in a transient Ref<> internally.
        val saveError = ResourceSaver.save(Resource.fromHandle(created.godotObject), path)

        // Read the .tres back from disk to prove the script reference and the property
        // value serialized (leak-free: no reloaded engine object to manage).
        val text = if (saveError == 0L) FileAccess.getFileAsString(path) else ""
        val scriptRef = text.contains("SmokeResource.kt")
        val payloadSaved = text.contains("forged")

        System.err.println(
            "[kanama:kt] ResourceForgeSmoke live=$live save_error=$saveError " +
                "script_ref=$scriptRef payload_saved=$payloadSaved",
        )

        // Manual lifetime: release the owning reference so the smoke process exits clean.
        Resource.fromHandle(created.godotObject).close()
    }
}

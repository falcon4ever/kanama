package net.multigesture.kanama.example

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.annotations.RegisterFunction
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.Image
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.StandardMaterial3D
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Task 98 lifetime-safety probe, driven from `main.gd` (`run_lifetime_smoke`).
 * 1. `GD.isInstanceValid` answers through the instance id the wrapper captured at construction, so
 *    asking it about a wrapper whose object was freed is safe (before task 98 it built an OBJECT
 *    Variant from the raw pointer, reading the freed object's header).
 * 2. Every RefCounted-derived wrapper refuses a call through a closed handle with the same
 *    `IllegalStateException("RefCounted handle is closed")` the hand-shaped Tween/Mesh family
 *    raised before: receiver-side on a hand-shaped class (`Material.getRenderPriority`), on an
 *    inherited hand-shaped accessor (`BaseMaterial3D.albedoColor`), on a generated class
 *    (`Image.getWidth`), and argument-side (`requireOpenHandle()` in `setNextPass`).
 */
@ScriptClass(attachTo = "Node")
class LifetimeSmoke(godotObject: MemorySegment) : KanamaScript<Node>(godotObject, ::Node) {

  private fun describe(t: Throwable?): String =
    if (t == null) "none" else "${t::class.simpleName}:${t.message}"

  @RegisterFunction
  fun runLifetimeSmoke() {
    // --- isInstanceValid across free() ---
    val probe = Node.fromHandle(ObjectCalls.constructObject("Node"))!!
    val aliveValid = GD.isInstanceValid(probe)
    val idMatchesPtrcall = probe.instanceId == probe.getInstanceId() && probe.instanceId != 0L
    ObjectCalls.destroyObject(probe.handle) // Object.free() equivalent for an orphan node
    // Safe by construction: only the captured id is read, never the freed pointer.
    val afterFreeValid = GD.isInstanceValid(probe)
    val idAfterFreeValid = GD.isInstanceIdValid(probe.instanceId)

    // --- use-after-close on RefCounted wrappers ---
    val closedMaterial = StandardMaterial3D.create()
    closedMaterial.close()
    val closedReceiver = runCatching { closedMaterial.getRenderPriority() }.exceptionOrNull()
    val closedInherited = runCatching { closedMaterial.albedoColor }.exceptionOrNull()
    val closedArgument =
      StandardMaterial3D.create().use { live ->
        runCatching { live.setNextPass(closedMaterial) }.exceptionOrNull()
      }
    val closedImage = Image.create(2, 2, false, Image.FORMAT_RGBA8)!!
    closedImage.close()
    val closedGenerated = runCatching { closedImage.getWidth() }.exceptionOrNull()

    System.err.println(
      "[kanama:kt] LifetimeSmoke instance_valid_alive=$aliveValid " +
        "instance_valid_after_free=$afterFreeValid id_after_free_valid=$idAfterFreeValid " +
        "id_matches_ptrcall=$idMatchesPtrcall " +
        "closed_receiver=${describe(closedReceiver)} closed_inherited=${describe(closedInherited)} " +
        "closed_generated=${describe(closedGenerated)} closed_argument=${describe(closedArgument)}"
    )
  }
}

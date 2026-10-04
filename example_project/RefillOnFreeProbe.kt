package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.MainThread
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.ResourceLoader
import net.multigesture.kanama.api.ResourceSaver
import net.multigesture.kanama.api.newScriptInstance

// Review probe: a rebuilt (pending-refill) instance that is never used before its owner dies.
// Does the refill run during the owner's destruction (siFree -> si())?
@ScriptClass(attachTo = "Node")
class RefillOnFreeProbe(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  private var id = 0L
  private var afterRebuild = 0
  private var frames = 0

  @OnReady
  fun ready() {
    newScriptInstance<RefillProbeResource>().use { h ->
      h.instance.cash = 4242
      ResourceSaver.save(h.resource, PATH)
    }
    loadAndDrop()
    repeat(4) {
      System.gc()
      Thread.sleep(100)
    }
    val before = RefillProbeResource.constructions
    reloadAndDropUntouched()
    afterRebuild = RefillProbeResource.constructions
    System.err.println(
      "[kanama:kt] RefillOnFreeProbe constructions_before_reload=$before after_reload=$afterRebuild " +
        "alive=${GD.isInstanceIdValid(id)}"
    )
    step()
  }

  private fun loadAndDrop() {
    val res = ResourceLoader.load(PATH) ?: return
    id = res.instanceId
    res.close()
  }

  private fun reloadAndDropUntouched() {
    val again = ResourceLoader.load(PATH) ?: return
    System.err.println("[kanama:kt] RefillOnFreeProbe reload_same_object=${again.instanceId == id}")
    again.close()
  }

  private fun step() {
    MainThread.postNextFrame {
      System.gc()
      frames += 1
      if (!GD.isInstanceIdValid(id) || frames > 120) {
        System.err.println(
          "[kanama:kt] RefillOnFreeProbe dead=${!GD.isInstanceIdValid(id)} frames=$frames " +
            "constructions_at_end=${RefillProbeResource.constructions} after_reload=$afterRebuild"
        )
        self.getTree()?.quit()
      } else step()
    }
  }

  private companion object {
    const val PATH = "user://refill_on_free_probe.tres"
  }
}

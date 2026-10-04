package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.Signal
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.Sprite2D
import net.multigesture.kanama.generated.KanamaSceneAutoloadSignals

/**
 * Task 133 C2: a Kotlin scene autoload (`kanama_scene_autoload.tscn`, which stores `level = 7` and
 * has a `Fixed` child). `_ready` adds a runtime child and a lambda connection to its own signal.
 * `hot_reload_in_process_smoke.sh` rebuilds it with `BUILD = "B"` and `mood` changed from `Long` to
 * `String`; after the reload the node is the same (main.gd reads it by its GDScript global name),
 * `level` keeps its runtime value, `mood` (type changed) its new default, the runtime child exists
 * once, `Fixed` is still there, and only the new build's handler runs.
 */
@ScriptClass(attachTo = "Node")
class KanamaSceneAutoload(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  @Export var level: Long = 1
  @Export var mood: Long = 3 // hot-reload-type-change
  var handled: Long = 0
    private set

  @Signal fun pinged() = Unit

  @OnReady
  fun ready() {
    val runtime = Sprite2D.create()
    runtime.setName("Runtime")
    self.addChild(runtime)
    KanamaSceneAutoloadSignals.connectPinged(this, GodotObject(godotObject)) {
      handled++
      System.err.println("[kanama:kt] scene autoload handler build=$BUILD")
    }
  }

  fun ping() {
    KanamaSceneAutoloadSignals.pinged(this)
  }

  fun state(): String {
    val runtimeChildren = self.getChildren().count { it.getName() != "Fixed" }
    return "build=$BUILD level=$level mood=$mood handled=$handled runtime=$runtimeChildren " +
      "fixed=${self.getNodeOrNull("Fixed") != null}"
  }

  private companion object {
    const val BUILD = "A" // hot-reload-build
  }
}

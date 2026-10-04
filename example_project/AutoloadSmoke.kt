package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.AutoloadAccess
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.Node3D
import net.multigesture.kanama.generated.Autoloads

/**
 * Task 133 C: the generated `Autoloads` object. `KanamaKotlinAutoload` is typed to its Kotlin
 * script class, `KanamaSmokeAutoload` (a GDScript `extends Node`) to `Node`; a missing autoload and
 * a node of another class throw an `IllegalStateException` that names the autoload.
 */
@ScriptClass(attachTo = "Node")
class AutoloadSmoke(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  fun run(): String {
    val kotlin: KanamaKotlinAutoload = Autoloads.KanamaKotlinAutoload
    val gd: Node = Autoloads.KanamaSmokeAutoload
    val missing =
      try {
        AutoloadAccess.node<Node>("NoSuchAutoload")
        "no-error"
      } catch (e: IllegalStateException) {
        e.message?.contains("Autoload 'NoSuchAutoload' is not in the tree") == true
      }
    val wrongClass =
      try {
        AutoloadAccess.node<Node3D>("KanamaSmokeAutoload")
        "no-error"
      } catch (e: IllegalStateException) {
        e.message?.contains("is a Node, not a Node3D") == true
      }
    val wrongScript =
      try {
        AutoloadAccess.script<AutoloadSmoke>("KanamaKotlinAutoload")
        "no-error"
      } catch (e: IllegalStateException) {
        e.message?.contains("has the script KanamaKotlinAutoload, not AutoloadSmoke") == true
      }
    val greeting = kotlin.greeting()
    // Free the Kotlin autoload (at the end of this frame): an autoload outlives a desktop hot
    // reload (only the scene's script objects are re-created), and hot_reload_in_process_smoke.sh
    // checks that the previous build's class loader is collected.
    kotlin.self.queueFree()
    return "kotlin=$greeting gd=${gd.getName()}:${addCount(gd)} " +
      "missing=$missing wrong_class=$wrongClass wrong_script=$wrongScript"
  }

  // The GDScript autoload's own function: a mixed-language boundary, so a dynamic call.
  private fun addCount(gd: Node): Any? = gd.call("add_count", 2L, 3L)
}

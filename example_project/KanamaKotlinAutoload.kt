package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

/**
 * Task 133 C: a Kotlin script autoload (`project.godot` `[autoload]`). `AutoloadSmoke.kt` reaches
 * it as `Autoloads.KanamaKotlinAutoload`, typed to this class.
 */
@ScriptClass(attachTo = "Node")
class KanamaKotlinAutoload(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  var readyCalls: Long = 0
    private set

  @OnReady
  fun ready() {
    readyCalls++
  }

  fun greeting(): String = "autoload:$readyCalls"
}

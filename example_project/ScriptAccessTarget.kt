package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

/**
 * Task 133 probe target: the Kotlin script `ScriptAccessSmoke` reaches through `script<T>()`,
 * `isScript`/`asScript` and `instantiateScript`, and whose coroutines prove the free path.
 */
@ScriptClass(attachTo = "Node")
class ScriptAccessTarget(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  var frames = 0

  fun ping(): String = "pong"
}

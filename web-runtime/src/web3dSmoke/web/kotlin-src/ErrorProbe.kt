package web3d

import net.multigesture.kanama.annotations.OnProcess
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

/**
 * Harness-only (task 131 item 14): a script whose callbacks throw on purpose, one kind at a time:
 * a registered function, a signal handler, `_process` and a coroutine. Main's `error_probe` fires
 * them; each must be reported to Godot's error log once -- with the exception type, the message
 * and the frame -- and contained: the call returns its default, the other scripts and the frame
 * loop go on, and this script keeps working (see [errorAlive]).
 */
@ScriptClass(attachTo = "Node")
class ErrorProbe(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  private var processArmed = false
  private var alive = 0L

  fun errorThrow(value: Long): Long {
    explode("function")
    return 1L
  }

  fun errorSignal(value: Long): Long {
    self.signal("renamed").connect(self) { explode("signal") }
    self.emitSignal("renamed")
    return 1L
  }

  fun errorArmProcess(value: Long): Long {
    processArmed = true
    return 1L
  }

  @OnProcess
  fun tick(delta: Double) {
    if (processArmed) {
      processArmed = false
      explode("process")
    }
  }

  fun errorCoroutine(value: Long): Long {
    launch { explode("coroutine") }
    return 1L
  }

  /** Counts up: proof the script still answers after its callbacks threw. */
  fun errorAlive(value: Long): Long {
    alive += 1
    return alive
  }

  private fun explode(kind: String): Nothing =
    throw IllegalStateException("web3d deliberate script error ($kind)")
}

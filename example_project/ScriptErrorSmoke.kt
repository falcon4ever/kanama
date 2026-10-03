package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

/**
 * Task 131 item 1 (F4) probe, run by `scripts/runtime_smoke.sh` as its own scene
 * (`script_error_smoke.tscn`). Its `_ready` throws on purpose. The runtime contains the exception
 * (the process keeps running) and reports it through GDExtension `print_script_error_with_message`,
 * so Godot's own log -- and, under the editor, the Errors tab -- shows `SCRIPT ERROR:` with this
 * file and the line of the `throw` below. Before task 131 it reached only the process stderr, which
 * the editor's Play button does not capture.
 */
@ScriptClass(attachTo = "Node")
class ScriptErrorSmoke(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  @OnReady
  fun ready() {
    System.err.println("[kanama:kt] ScriptErrorSmoke ready: throwing")
    throw IllegalStateException("kanama smoke: deliberate _ready failure")
  }
}

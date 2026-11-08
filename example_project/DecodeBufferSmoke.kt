package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.binding.runtime.VariantDecodeProbe

/**
 * Every container decode releases each element exactly once ([VariantDecodeProbe]), run by
 * `scripts/runtime_smoke.sh` as its own scene (`decode_buffer_smoke.tscn`), first, before any other
 * scene. A decode loop that reused a destroyed Variant buffer released the previous element again
 * (the Godot 4.8 trial found it through `captureScriptBacktraces`; bound Callables show it on 4.7.2
 * too). On 4.8 the second release frees the element, and Godot may abort in whichever later run
 * decodes an Array of RefCounted elements, so the probe gets a process of its own: a crash here is
 * this probe's. The `start` line tells a crash inside the probe from one before it.
 */
@ScriptClass(attachTo = "Node")
class DecodeBufferSmoke(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  @OnReady
  fun ready() {
    System.err.println("[kanama:kt] DecodeBufferSmoke start")
    System.err.println("[kanama:kt] DecodeBufferSmoke ${VariantDecodeProbe.run(self)}")
  }
}

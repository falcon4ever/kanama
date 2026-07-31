package net.multigesture.kanama.spike

import net.multigesture.kanama.annotations.OnProcess
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

// Task 75 spike — stage D probe. Deliberately minimal: prove a Kanama script
// loads, its @OnReady fires, and a wrapper call reaches Godot on a desktop
// Kotlin/Native GDExtension.
@ScriptClass(attachTo = "Node")
class ProbeScript(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
    private var ticks: Long = 0

    @OnReady
    fun ready() {
        GD.print("[kanama][spike] ProbeScript READY on desktop Kotlin/Native")
        GD.print("[kanama][spike] self.name=" + self.getName())
    }

    @OnProcess
    fun process(delta: Double) {
        ticks += 1
        if (ticks == 3L) {
            GD.print("[kanama][spike] ProbeScript PROCESS tick 3 delta=" + delta)
        }
    }
}

package com.example.game

import net.multigesture.kanama.annotations.ClassName
import net.multigesture.kanama.annotations.OnProcess
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.PropertyHint
import net.multigesture.kanama.annotations.RegisterFunction
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.ScriptProperty
import net.multigesture.kanama.annotations.Tool
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Label
import net.multigesture.kanama.api.Node2D

@ScriptClass(attachTo = "Node2D")
@ClassName
@Tool
class HelloScript(godotObject: GodotHandle) :
    KanamaScript<Node2D>(godotObject, ::Node2D) {
    @ScriptProperty(hint = PropertyHint.RANGE, hintString = "0,4,0.1")
    var spinSpeed: Double = 0.6

    @ScriptProperty
    var greeting: String = "Hello from Kanama"

    private var elapsedSeconds = 0.0
    private var statusLabel: Label? = null

    @OnReady
    fun ready() {
        statusLabel = self.getNodeAsOrNull("Status", "Label", ::Label)
        updateStatus("ready")
        GD.print("[kanama:starter] $greeting")
    }

    @OnProcess
    fun process(delta: Double) {
        elapsedSeconds += delta
        self.rotation = elapsedSeconds * spinSpeed
        updateStatus("running for %.1fs".format(elapsedSeconds))
    }

    @RegisterFunction
    fun greet(name: String): String = "$greeting, $name"

    private fun updateStatus(state: String) {
        statusLabel?.text = "$greeting\nKotlin script is $state."
    }
}

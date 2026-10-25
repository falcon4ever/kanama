package com.example.game

// Task 142: one script, written the way the starter writes its own (explicit imports, no Web-only
// import), that local_ci compiles for desktop (installAddonJar) and for the Web (the starter's Web
// stage). It uses the script API the user pages teach, so a member on one platform that is an
// extension on the other fails one of the two builds. Compiled only; no scene attaches it.

import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Label
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.Node2D
import net.multigesture.kanama.api.PackedScene
import net.multigesture.kanama.api.castOrNull
import net.multigesture.kanama.api.getNodeAs
import net.multigesture.kanama.api.instantiateAs
import net.multigesture.kanama.api.instantiateScript
import net.multigesture.kanama.api.requireAs

@ScriptClass(attachTo = "Node2D")
class SharedScriptAccess(godotObject: GodotHandle) :
    KanamaScript<Node2D>(godotObject, ::Node2D) {
    private val status by node<Label>("Status")
    private val hello by script<HelloScript>(".")
    private val mainScene by preload<PackedScene>("res://main.tscn")

    @OnReady
    fun ready() {
        status.text = hello.greet("shared")
        val label: Label? = self.getNodeAs<Label>("Status")
        val node: Node? = self.requireAs<Label>("Status").castOrNull<Node>()
        val looked: Label? = self.getNodeAsOrNull("Status", "Label", ::Label)
        val root: Node2D = mainScene.instantiateAs<Node2D>()
        val script: HelloScript = mainScene.instantiateScript<HelloScript>()
        GD.print("${label != null} ${node != null} ${looked != null} ${root.getName()} ${script.greet("x")}")
    }
}

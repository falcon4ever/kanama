package net.multigesture.kanama.annotations

annotation class ScriptClass(val attachTo: String = "Node")

annotation class RegisterFunction(val name: String = "")

annotation class Method(val name: String = "")

annotation class OnReady

annotation class Ready

annotation class OnEnterTree

annotation class EnterTree

annotation class OnExitTree

annotation class ExitTree

annotation class OnProcess

annotation class Process

annotation class OnPhysicsProcess

annotation class PhysicsProcess

annotation class OnInput

annotation class Input

annotation class ScriptProperty

annotation class Export(
    val name: String = "",
    val hint: Int = 0,
    val hintString: String = "",
    val usage: Int = 6,
)

annotation class ExportGroup(
    val name: String,
    val prefix: String = "",
)

annotation class ExportSubgroup(
    val name: String,
    val prefix: String = "",
)

annotation class ExportCategory(val name: String)

annotation class Signal(val name: String = "")

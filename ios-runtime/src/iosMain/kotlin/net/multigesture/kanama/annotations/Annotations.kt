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

annotation class ScriptProperty

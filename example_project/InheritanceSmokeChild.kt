package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle

/**
 * Task 133 C: a script that extends another script class (GDScript `extends "base.gd"`). It
 * declares only what it adds or changes: `ready()` stays the `_ready` handler without
 * re-annotating, and the `describe()` override is what Godot calls.
 */
@ScriptClass(attachTo = "Node")
class InheritanceSmokeChild(godotObject: GodotHandle) : InheritanceSmokeBase(godotObject) {
  @Export var childOnly: String = "child"
  var childReady = false

  override fun ready() {
    super.ready()
    childReady = true
  }

  override fun describe(): String = "child:${baseOnly()}"

  fun readyState(): String = "calls=$readyCalls child=$childReady"
}

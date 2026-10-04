package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.ExportRange
import net.multigesture.kanama.annotations.GodotName
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.Signal
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

/**
 * Task 133 C: the superclass of `InheritanceSmokeChild`. Its exports, signal, `_ready` handler,
 * `@GodotName` function and public functions are the child script's members too, with no forwarding
 * override in the child.
 */
@ScriptClass(attachTo = "Node")
open class InheritanceSmokeBase(godotObject: GodotHandle) :
  KanamaScript<Node>(godotObject, ::Node) {
  @Export var baseSpeed: Double = 2.5
  @ExportRange(0.0, 10.0, 1.0) var baseLevel: Long = 3
  var readyCalls: Long = 0

  @Signal fun basePinged(value: Long) = Unit

  @OnReady
  open fun ready() {
    readyCalls++
  }

  open fun describe(): String = "base"

  fun baseOnly(): Long = 7

  @GodotName("_on_base_pressed") open fun onBasePressed(): String = "base-pressed"
}

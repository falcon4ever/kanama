package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

/**
 * Task 133 C2: a generic base. Its members are typed as members of the script class, so
 * `GenericInheritanceSmoke : GenericSmokeBase<Long>` exports `amount: T` as an int, registers
 * `echo(x: T)` with a `Long` parameter, and its `twice(x: Long)` override and the base's `open fun
 * twice(x: T)` are one Godot method (before, two methods named `twice`: a build error).
 */
abstract class GenericSmokeBase<T>(godotObject: GodotHandle, initial: T) :
  KanamaScript<Node>(godotObject, ::Node) {
  @Export var amount: T = initial

  open fun twice(x: T): T = x

  fun echo(x: T): T = x
}

@ScriptClass(attachTo = "Node")
class GenericInheritanceSmoke(godotObject: GodotHandle) : GenericSmokeBase<Long>(godotObject, 4L) {
  override fun twice(x: Long): Long = x * 2
}

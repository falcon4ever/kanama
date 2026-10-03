package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.Method
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.ScriptProperty
import net.multigesture.kanama.api.Engine
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.Node3D
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Task 131 items 2 (F2) and 6 (F23) probe, run by `scripts/runtime_smoke.sh` as its own scene
 * (`freed_object_smoke.tscn`).
 *
 * Item 6: two wrappers of one object are `==`, hash alike and collapse in a `Set`, whatever the
 * wrapper class; wrappers of two objects are not.
 *
 * Item 2, with GDScript's semantics (debug builds; the editor binary is one): HOLDING a wrapper of
 * a freed object is silent -- reading it back through an exported property ([target], twice) or
 * returning it from a script method ([freedTarget]) gives Godot nil, with no error -- while a CALL
 * through it throws `IllegalStateException`. Caught, that is an ordinary exception; uncaught inside
 * [callFreed] -- a script method Godot calls -- it is contained and reported as one Godot script
 * error at the line of that call, and [ready] carries on. Before task 131 every one of these read
 * freed memory: undefined behaviour, typically a native crash.
 *
 * Task 131 S5: the RefCounted elements of a returned typed Array (`captureScriptBacktraces`) are
 * owned by their wrappers: alive, usable and closed here. Before, the Array's destroy had freed
 * them.
 */
@ScriptClass(attachTo = "Node")
class FreedObjectSmoke(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  @ScriptProperty var target: Node? = null

  private var victim: Node? = null

  @OnReady
  fun ready() {
    val node = Node3D(GodotHandle(ObjectCalls.constructObject("Node3D")))
    val other = Node(GodotHandle(ObjectCalls.constructObject("Node")))
    val view = Node(node.handle) // a second wrapper, of another class, over the same object
    val equal = node == view && view == node && node.isSameInstance(view)
    val sameHash = node.hashCode() == view.hashCode()
    val setSize = setOf(node, view, other).size
    val notEqual = node != other

    target = view
    victim = node
    ObjectCalls.destroyObject(node.handle.segment) // Object.free()
    ObjectCalls.destroyObject(other.handle.segment)
    val valid = GD.isInstanceValid(node)
    val equalAfterFree = node == view
    val text = node.toString()
    // Holding is silent: reads and returns of the freed wrapper give Godot nil, no script error.
    val firstRead = self.get("target")
    val secondRead = self.get("target")
    val returned = self.call("freed_target")
    val caught =
      try {
        node.getName()
        "none"
      } catch (e: IllegalStateException) {
        e.message
      }
    // A call is the error: Godot calls call_freed as a script method; its uncaught error is
    // contained there (exactly one SCRIPT ERROR for this scene).
    val result = self.call("call_freed")
    System.err.println(
      "[kanama:kt] FreedObjectSmoke equal=$equal same_hash=$sameHash set_size=$setSize " +
        "not_equal=$notEqual valid_after_free=$valid equal_after_free=$equalAfterFree " +
        "to_string=$text property_reads=$firstRead,$secondRead method_return=$returned " +
        "survived=true result_null=${result == null}"
    )
    System.err.println("[kanama:kt] FreedObjectSmoke caught=$caught")

    val backtraces = Engine.captureScriptBacktraces()
    val backtracesValid = backtraces.map { GD.isInstanceValid(it) }
    val languages = backtraces.map { it.getLanguageName() }
    backtraces.forEach { it.close() }
    System.err.println(
      "[kanama:kt] FreedObjectSmoke backtraces valid=$backtracesValid languages=$languages"
    )
  }

  @Method(name = "freed_target") fun freedTarget(): GodotObject = victim!!

  @Method(name = "call_freed") fun callFreed(): String = victim!!.getName()
}

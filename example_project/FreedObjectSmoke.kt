package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.Method
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotHandle
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
 * Item 2: a call through a wrapper whose object was freed throws `IllegalStateException` (debug
 * builds; the editor binary is one) instead of dereferencing the dead pointer. Caught, it is an
 * ordinary exception; uncaught inside [callFreed] -- a script method Godot calls -- it is contained
 * and reported as a Godot script error at the line of that call, and [ready] carries on. Before
 * task 131 the same call was a use-after-free: undefined behaviour, typically a native crash.
 */
@ScriptClass(attachTo = "Node")
class FreedObjectSmoke(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  private var victim: Node? = null

  @OnReady
  fun ready() {
    val node = Node3D(GodotHandle(ObjectCalls.constructObject("Node3D")))
    val other = Node(GodotHandle(ObjectCalls.constructObject("Node")))
    val view = Node(node.handle) // a second wrapper, of another class, over the same object
    val equal = node == view && view == node
    val sameHash = node.hashCode() == view.hashCode()
    val setSize = setOf(node, view, other).size
    val notEqual = node != other

    ObjectCalls.destroyObject(node.handle.segment) // Object.free()
    ObjectCalls.destroyObject(other.handle.segment)
    val valid = GD.isInstanceValid(node)
    val equalAfterFree = node == view
    val text = node.toString()
    val caught =
      try {
        node.getName()
        "none"
      } catch (e: IllegalStateException) {
        e.message
      }
    victim = node
    // Godot calls call_freed as a script method; its uncaught error is contained there.
    val result = self.call("call_freed")
    System.err.println(
      "[kanama:kt] FreedObjectSmoke equal=$equal same_hash=$sameHash set_size=$setSize " +
        "not_equal=$notEqual valid_after_free=$valid equal_after_free=$equalAfterFree " +
        "to_string=$text survived=true result_null=${result == null}"
    )
    System.err.println("[kanama:kt] FreedObjectSmoke caught=$caught")
  }

  @Method(name = "call_freed") fun callFreed(): String = victim!!.getName()
}

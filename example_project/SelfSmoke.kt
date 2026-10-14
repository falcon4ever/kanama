package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.Node3D
import net.multigesture.kanama.api.RefCounted
import net.multigesture.kanama.api.RegEx

@ScriptClass(attachTo = "Node3D")
class SelfSmoke(godotObject: GodotHandle) : KanamaScript<Node3D>(godotObject, ::Node3D) {
  private val node = selfAs(::Node)

  @OnReady
  fun ready() {
    val sameObject = self.getInstanceId() == node.getInstanceId()
    System.err.println(
      "[kanama:kt] SelfSmoke self_class=${self.getClassName()} same_object=$sameObject"
    )
    callContainerElements()
  }

  // Task 122 item 3: `RegEx.search_all` through the Variant `call` returns a fresh Array of fresh
  // RegExMatch objects whose only reference is that Array. The elements must come back as owning
  // wrappers (alive after the return Variant is gone, one reference each) and be freed by the one
  // close() each. Before, they were borrowed views of objects already freed.
  private fun callContainerElements() {
    callContainerElements("call_container_elements") { regex -> regex.call("search_all", "a1b2c3") }
    // `callv` takes the same owned path as `call` (task 122 review): it used to keep the elements
    // borrowed, so the same factory through `callv` returned freed handles.
    callContainerElements("callv_container_elements") { regex ->
      regex.callv("search_all", listOf("a1b2c3"))
    }
  }

  private fun callContainerElements(label: String, invoke: (RegEx) -> Any?) {
    val regex = RegEx.createFromString("\\d") ?: error("RegEx did not compile")
    val matches = invoke(regex) as List<*>
    // A borrowed element is a freed object: only an owned one is safe to call.
    val owned = matches.isNotEmpty() && matches.all { it is RefCounted }
    val strings =
      if (owned) matches.map { (it as GodotObject).call("get_string", 0) } else emptyList()
    val refcounts =
      if (owned) matches.map { (it as RefCounted).getReferenceCount() } else emptyList()
    val ids = if (owned) matches.map { (it as GodotObject).getInstanceId() } else emptyList()
    if (owned) matches.forEach { (it as RefCounted).close() }
    val released = ids.isNotEmpty() && ids.none { GD.isInstanceIdValid(it) }
    regex.close()
    System.err.println(
      "[kanama:kt] SelfSmoke $label count=${matches.size} " +
        "strings=${strings.joinToString(",")} owned=$owned " +
        "refcounts=${refcounts.joinToString(",")} released=$released"
    )
  }
}

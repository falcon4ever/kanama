package net.multigesture.kanama.example

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.annotations.GlobalClass
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.ResourceLoader

// Task 56 fixture (outer). A non-@Tool resource created via newScriptInstance(). Its
// constructor runs inside the programmatic-create window, and there it reentrantly
// instantiates reentrant_inner.tscn — whose root carries the non-@Tool
// ReentrantPlaceholderProbe. That inner node is a *different* owner, so it must NOT inherit
// this resource's force-real override. Emits reentered=true so the smoke can confirm the
// reentrant instantiation actually happened (otherwise the absent inner marker would be a
// false pass).
@ScriptClass(attachTo = "Resource")
@GlobalClass
class ReentrantForgeProbe(val godotObject: MemorySegment) {
  init {
    val node = ResourceLoader.loadPackedScene("res://reentrant_inner.tscn")?.instantiate()
    System.err.println("[kanama:kt] ReentrantForgeProbe reentered=${node != null}")
    node?.queueFree()
  }
}

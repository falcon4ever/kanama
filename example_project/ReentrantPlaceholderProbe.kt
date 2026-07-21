package net.multigesture.kanama.example

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.annotations.ScriptClass

// Task 56 fixture (inner). A non-@Tool node script whose CONSTRUCTOR prints a marker.
// A placeholder instance never invokes the factory, so the constructor — and its marker —
// runs only when the instance is built for real. When this node is instantiated reentrantly
// *during* another script's newScriptInstance() create window, per-owner scoping must still
// hand it a placeholder (marker ABSENT). The pre-fix thread-wide flag forced it real (marker
// PRESENT); that regression is what tool_smoke's check_absent guards.
@ScriptClass(attachTo = "Node")
class ReentrantPlaceholderProbe(val godotObject: MemorySegment) {
  init {
    System.err.println("[kanama:kt] ReentrantPlaceholderProbe CONSTRUCTED")
  }
}

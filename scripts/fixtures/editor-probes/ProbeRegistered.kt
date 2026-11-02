package com.example.probe

import net.multigesture.kanama.annotations.RegisterClass
import net.multigesture.kanama.annotations.Tool
import net.multigesture.kanama.api.GodotHandle

// A class the editor registers once, at startup, from the first kanama-scripts.jar: the class loader that
// loaded it stays open on that jar for the whole editor session. scripts/check_editor_probes.gd calls lazyAnswer()
// only after several builds replaced the jar, so ProbeLazyHolder is read from the jar then (probe 6).
@RegisterClass(parentClassName = "Node")
@Tool
class ProbeRegistered(val godotObject: GodotHandle) {
  fun lazyAnswer(): Long = ProbeLazyHolder.answer()
}

object ProbeLazyHolder {
  fun answer(): Long = 7
}

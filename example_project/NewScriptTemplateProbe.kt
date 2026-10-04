// Task 131 item 5 (F15): exactly what the editor's New Script dialog writes for a script named
// NewScriptTemplateProbe that extends Node3D, saved beside this project's scripts (the
// `KanamaScriptTemplateTest` JVM test holds KanamaScriptTemplate.source to this file, below this
// comment). It is here so the example build compiles the template through the KSP processor.
package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node3D
import net.multigesture.kanama.types.*

@ScriptClass(attachTo = "Node3D")
class NewScriptTemplateProbe(godotObject: GodotHandle) :
  KanamaScript<Node3D>(godotObject, ::Node3D) {
  @OnReady
  fun ready() {
    // Called when the node enters the scene tree for the first time.
  }
}

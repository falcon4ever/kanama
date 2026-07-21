package net.multigesture.kanama.example

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.annotations.GlobalClass
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.ScriptProperty
import net.multigesture.kanama.api.AudioStream
import net.multigesture.kanama.api.Mesh
import net.multigesture.kanama.api.Shape3D

@ScriptClass(attachTo = "Resource")
@GlobalClass
class SmokeResource(val godotObject: MemorySegment) {
  @ScriptProperty var payload: String = "default"

  // task 33 (issue #36) — the reported custom-resource shape: value exports plus
  // resource slots (base-typed: the .tscn stores AudioStreamWAV/BoxMesh/BoxShape3D).
  @ScriptProperty var customIntValue: Long = 42

  @ScriptProperty var stream: AudioStream? = null

  @ScriptProperty var mesh: Mesh? = null

  @ScriptProperty var shape: Shape3D? = null
}

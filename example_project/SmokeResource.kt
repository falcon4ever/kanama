package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.GlobalClass
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.AudioStream
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.Mesh
import net.multigesture.kanama.api.Shape3D

@ScriptClass(attachTo = "Resource")
@GlobalClass
class SmokeResource(val godotObject: GodotHandle) {
  @Export var payload: String = "default"

  // task 33 (issue #36) — the reported custom-resource shape: value exports plus
  // resource slots (base-typed: the .tscn stores AudioStreamWAV/BoxMesh/BoxShape3D).
  @Export var customIntValue: Long = 42

  @Export var stream: AudioStream? = null

  @Export var mesh: Mesh? = null

  @Export var shape: Shape3D? = null
}

package com.example.probe

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.GlobalClass
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.Tool
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node2D

// The script scripts/check_editor_probes.gd edits in a running editor (scripts/editor_probe_smoke.sh). The
// probe replaces the marker line below with new members and saves the file, as a user does in their IDE.
@ScriptClass(attachTo = "Node2D")
@GlobalClass
@Tool
class ProbeScript(godotObject: GodotHandle) : KanamaScript<Node2D>(godotObject, ::Node2D) {
  @Export var speed: Double = 1.5

  fun buildMark(): Long = 1

  // probe: new members
}

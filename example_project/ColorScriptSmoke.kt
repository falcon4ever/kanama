package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.ExportColorNoAlpha
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.Signal
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.generated.ColorScriptSmokeSignals
import net.multigesture.kanama.types.Color

/**
 * Task 133 C2: `Color` as a script type. `color_script_smoke.tscn` stores `tint` for this script
 * and for its GDScript twin (`color_script_twin.gd`); `color_script_driver.gd` reads both through
 * the scene, Object.set/get, a function, a return and a signal, and prints one line per side, which
 * `scripts/runtime_smoke.sh` requires to be identical.
 */
@ScriptClass(attachTo = "Node")
class ColorScriptSmoke(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  @Export var tint: Color = Color(0.1, 0.2, 0.3, 0.4)
  @ExportColorNoAlpha var solid: Color = Color.RED

  /** What Kotlin received through the typed signal connection. */
  private var received: Color? = null

  @Signal fun tinted(color: Color) = Unit

  fun mixWith(other: Color): Color = tint.lerp(other, 0.25)

  fun current(): Color = tint

  fun describe(): String = "$tint|$solid"

  fun emitTint() {
    ColorScriptSmokeSignals.connectTinted(this, GodotObject(godotObject)) { received = it }
      .use { ColorScriptSmokeSignals.tinted(this, tint) }
  }

  fun received(): String = received.toString()
}

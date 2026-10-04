package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.ExportColorNoAlpha
import net.multigesture.kanama.annotations.ExportCustom
import net.multigesture.kanama.annotations.ExportDir
import net.multigesture.kanama.annotations.ExportEnum
import net.multigesture.kanama.annotations.ExportExpEasing
import net.multigesture.kanama.annotations.ExportFile
import net.multigesture.kanama.annotations.ExportFilePath
import net.multigesture.kanama.annotations.ExportFlags
import net.multigesture.kanama.annotations.ExportFlags2DNavigation
import net.multigesture.kanama.annotations.ExportFlags2DPhysics
import net.multigesture.kanama.annotations.ExportFlags2DRender
import net.multigesture.kanama.annotations.ExportFlags3DNavigation
import net.multigesture.kanama.annotations.ExportFlags3DPhysics
import net.multigesture.kanama.annotations.ExportFlags3DRender
import net.multigesture.kanama.annotations.ExportFlagsAvoidance
import net.multigesture.kanama.annotations.ExportGlobalDir
import net.multigesture.kanama.annotations.ExportGlobalFile
import net.multigesture.kanama.annotations.ExportMultiline
import net.multigesture.kanama.annotations.ExportNodePath
import net.multigesture.kanama.annotations.ExportPlaceholder
import net.multigesture.kanama.annotations.ExportRange
import net.multigesture.kanama.annotations.ExportStorage
import net.multigesture.kanama.annotations.PropertyHint
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Mathf
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.NodePath

/**
 * Task 133 C: one property per typed hint annotation. `export_hint_twin.gd` declares the same
 * properties with GDScript's `@export_*` annotations, and `main.gd` checks that Godot reports the
 * same type, hint and hint string for both (`get_property_list()`), and the same folded default for
 * `r_rad` (`Mathf.PI / 3.0`, GDScript `PI / 3.0`).
 */
@ScriptClass(attachTo = "Node")
class ExportHintSmoke(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  @ExportRange(0.0, 100.0, 1.0) var rInt: Long = 5
  @ExportRange(0.0, 1.0, 0.01) var rFloat: Double = 0.5
  @ExportRange(-10.0, 10.0) var rDefaultStep: Double = 0.0

  @ExportRange(0.0, 100.0, 0.5, orGreater = true, orLess = true, suffix = "m")
  var rExtra: Double = 1.0

  @ExportRange(0.0, 360.0, 0.1, radiansAsDegrees = true) var rRad: Double = Mathf.PI / 3.0
  @ExportRange(1e-5, 1000.0) var rTiny: Double = 1.0

  @ExportRange(0.0, 1.0, 0.01, exp = true, degrees = true, preferSlider = true, hideControl = true)
  var rFlags: Double = 0.0

  @ExportFile var fAny: String = ""
  @ExportFile("*.png", "*.jpg") var fImg: String = ""
  @ExportFilePath("*.txt") var fPath: String = ""
  @ExportDir var d: String = ""
  @ExportGlobalFile("*.cfg") var gf: String = ""
  @ExportGlobalDir var gdir: String = ""
  @ExportMultiline var ml: String = ""
  @ExportMultiline(monospace = true, noWrap = true) var ml2: String = ""
  @ExportPlaceholder("Name here") var ph: String = ""
  @ExportPlaceholder("a,b") var ph2: String = ""
  @ExportEnum("Warrior", "Magician:5", "Thief") var enInt: Long = 0
  @ExportEnum("Rebecca", "Mary") var enStr: String = ""
  @ExportFlags("Fire", "Water:4", "Earth") var fl: Long = 0
  @ExportFlags2DRender var l2r: Long = 0
  @ExportFlags2DPhysics var l2p: Long = 0
  @ExportFlags2DNavigation var l2n: Long = 0
  @ExportFlags3DRender var l3r: Long = 0
  @ExportFlags3DPhysics var l3p: Long = 0
  @ExportFlags3DNavigation var l3n: Long = 0
  @ExportFlagsAvoidance var lav: Long = 0
  @ExportExpEasing var ease: Double = 1.0
  @ExportExpEasing(attenuation = true, positiveOnly = true) var ease2: Double = 1.0
  @ExportNodePath("Button", "TouchScreenButton") var np: NodePath = NodePath("")
  @ExportNodePath var np2: NodePath = NodePath("")
  @ExportStorage var st: Long = 3
  @ExportCustom(PropertyHint.PASSWORD) var pw: String = ""
  @ExportFile("*.png") var fArr: List<String> = emptyList()
  @ExportMultiline var mlArr: List<String> = emptyList()
  @ExportEnum("A", "B") var enArr: List<String> = emptyList()
  @ExportColorNoAlpha var cna: Color = Color.RED
  @Export var col: Color = Color(0.2, 0.4, 0.6, 0.8)
}

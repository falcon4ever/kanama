package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNull
import kotlin.test.assertTrue
import net.multigesture.kanama.processor.ExportHints.Result
import net.multigesture.kanama.processor.ExportHints.Slot
import net.multigesture.kanama.processor.ExportHints.Use

/**
 * Task 133 C — the typed hint annotations build GDScript's hint and hint string.
 *
 * Every expected value below is what Godot 4.7.2's own `get_property_list()` reports for the
 * GDScript twin (`example_project/export_hint_twin.gd`, compared row by row against the Kotlin
 * script `ExportHintSmoke.kt` in `scripts/runtime_smoke.sh`). The same model feeds the desktop, iOS
 * and Web emitters; [WebScriptCodeEmitterTest] pins the Web proxy's `@export_custom` spelling.
 */
class ExportHintsTest {

  private fun hint(annotation: String, slot: Slot, vararg args: Pair<String, Any?>): Result =
    ExportHints.build("S.p", Use(annotation, mapOf(*args)), slot, slot.description) { name ->
      if (name in setOf("Button", "TouchScreenButton", "Node3D")) null else "not a Node class"
    }

  private fun assertHint(expectedHint: Int, expectedString: String, result: Result) {
    val hint = assertIs<Result.Hint>(result)
    assertEquals(expectedHint, hint.hint, "hint")
    assertEquals(expectedString, hint.hintString, "hint string")
  }

  @Test
  fun rangeMatchesGodotsFloatFormatting() {
    assertHint(
      1,
      "0.0,100.0,1.0",
      hint("ExportRange", Slot.INT, "min" to 0.0, "max" to 100.0, "step" to 1.0),
    )
    assertHint(
      1,
      "0.0,1.0,0.01",
      hint("ExportRange", Slot.FLOAT, "min" to 0.0, "max" to 1.0, "step" to 0.01),
    )
    // No step: GDScript leaves it out and the editor picks one.
    assertHint(
      1,
      "-10.0,10.0",
      hint("ExportRange", Slot.FLOAT, "min" to -10.0, "max" to 10.0, "step" to Double.NaN),
    )
    assertHint(1, "0.00001,1000.0", hint("ExportRange", Slot.FLOAT, "min" to 1e-5, "max" to 1000.0))
    assertHint(
      1,
      "0.25,1.75,0.125",
      hint("ExportRange", Slot.FLOAT, "min" to 0.25, "max" to 1.75, "step" to 0.125),
    )
  }

  @Test
  fun rangeExtraHintsInGdscriptOrder() {
    assertHint(
      1,
      "0.0,100.0,0.5,or_greater,or_less,suffix:m",
      hint(
        "ExportRange",
        Slot.FLOAT,
        "min" to 0.0,
        "max" to 100.0,
        "step" to 0.5,
        "suffix" to "m",
        "orLess" to true,
        "orGreater" to true,
      ),
    )
    assertHint(
      1,
      "0.0,360.0,0.1,radians_as_degrees",
      hint(
        "ExportRange",
        Slot.FLOAT,
        "min" to 0.0,
        "max" to 360.0,
        "step" to 0.1,
        "radiansAsDegrees" to true,
      ),
    )
    assertHint(
      1,
      "0.0,1.0,exp,degrees,prefer_slider,hide_control",
      hint(
        "ExportRange",
        Slot.FLOAT,
        "min" to 0.0,
        "max" to 1.0,
        "exp" to true,
        "degrees" to true,
        "preferSlider" to true,
        "hideControl" to true,
      ),
    )
  }

  @Test
  fun godotNumRealIsStringOfAFloatVariant() {
    assertEquals("0.0", ExportHints.godotNumReal(0.0))
    assertEquals("-10.0", ExportHints.godotNumReal(-10.0))
    assertEquals("0.01", ExportHints.godotNumReal(0.01))
    assertEquals("0.1", ExportHints.godotNumReal(0.1))
    assertEquals("0.00001", ExportHints.godotNumReal(1e-5))
    assertEquals("3.14159265358979", ExportHints.godotNumReal(Math.PI))
    assertEquals("123.456", ExportHints.godotNumReal(123.456))
    assertEquals("100000000000000000000.0", ExportHints.godotNumReal(1e20))
  }

  @Test
  fun fileDirAndTextHints() {
    assertHint(13, "", hint("ExportFile", Slot.STRING, "filters" to emptyList<String>()))
    assertHint(
      13,
      "*.png,*.jpg",
      hint("ExportFile", Slot.STRING, "filters" to listOf("*.png", "*.jpg")),
    )
    assertHint(44, "*.txt", hint("ExportFilePath", Slot.STRING, "filters" to listOf("*.txt")))
    assertHint(14, "", hint("ExportDir", Slot.STRING))
    assertHint(15, "*.cfg", hint("ExportGlobalFile", Slot.STRING, "filters" to arrayOf("*.cfg")))
    assertHint(16, "", hint("ExportGlobalDir", Slot.STRING))
    assertHint(18, "", hint("ExportMultiline", Slot.STRING))
    assertHint(18, "monospace", hint("ExportMultiline", Slot.STRING, "monospace" to true))
    assertHint(
      18,
      "monospace,no_wrap",
      hint("ExportMultiline", Slot.STRING, "monospace" to true, "noWrap" to true),
    )
    assertHint(
      20,
      "Name here",
      hint("ExportPlaceholder", Slot.STRING, "placeholder" to "Name here"),
    )
    // The placeholder is the one argument that may hold a comma (or be empty).
    assertHint(20, "a,b", hint("ExportPlaceholder", Slot.STRING, "placeholder" to "a,b"))
  }

  @Test
  fun enumFlagsAndLayers() {
    assertHint(
      2,
      "Warrior,Magician:5,Thief",
      hint("ExportEnum", Slot.INT, "names" to listOf("Warrior", "Magician:5", "Thief")),
    )
    assertHint(
      2,
      "Rebecca,Mary",
      hint("ExportEnum", Slot.STRING, "names" to listOf("Rebecca", "Mary")),
    )
    assertHint(
      6,
      "Fire,Water:4,Earth",
      hint("ExportFlags", Slot.INT, "names" to listOf("Fire", "Water:4", "Earth")),
    )
    val layers =
      mapOf(
        "ExportFlags2DRender" to 7,
        "ExportFlags2DPhysics" to 8,
        "ExportFlags2DNavigation" to 9,
        "ExportFlags3DRender" to 10,
        "ExportFlags3DPhysics" to 11,
        "ExportFlags3DNavigation" to 12,
        "ExportFlagsAvoidance" to 37,
      )
    for ((annotation, value) in layers) assertHint(value, "", hint(annotation, Slot.INT))
    assertEquals("export_flags_2d_physics", ExportHints.gdName("ExportFlags2DPhysics"))
    assertEquals("export_flags_avoidance", ExportHints.gdName("ExportFlagsAvoidance"))
  }

  @Test
  fun easingNodePathStorageAndCustom() {
    assertHint(4, "", hint("ExportExpEasing", Slot.FLOAT))
    assertHint(
      4,
      "attenuation,positive_only",
      hint("ExportExpEasing", Slot.FLOAT, "attenuation" to true, "positiveOnly" to true),
    )
    assertHint(
      26,
      "Button,TouchScreenButton",
      hint("ExportNodePath", Slot.NODE_PATH, "types" to listOf("Button", "TouchScreenButton")),
    )
    assertHint(26, "", hint("ExportNodePath", Slot.NODE_PATH, "types" to emptyList<String>()))
    assertIs<Result.Storage>(hint("ExportStorage", Slot.INT))
    val custom =
      assertIs<Result.Hint>(
        hint("ExportCustom", Slot.STRING, "hint" to 36, "hintString" to "", "usage" to 6)
      )
    assertEquals(36, custom.hint)
    assertEquals(6, custom.usage)
  }

  @Test
  fun stringListHintsMoveToTheElement() {
    assertHint(23, "4/13:*.png", hint("ExportFile", Slot.STRING_LIST, "filters" to listOf("*.png")))
    assertHint(23, "4/18:", hint("ExportMultiline", Slot.STRING_LIST))
    assertHint(23, "4/2:A,B", hint("ExportEnum", Slot.STRING_LIST, "names" to listOf("A", "B")))
  }

  @Test
  fun gdscriptsArgumentErrors() {
    fun error(result: Result): String = assertIs<Result.Error>(result).message
    assertTrue(
      error(hint("ExportFile", Slot.STRING, "filters" to listOf("*.png,*.jpg"))).contains("comma")
    )
    assertTrue(error(hint("ExportEnum", Slot.INT, "names" to listOf("A", ""))).contains("empty"))
    assertTrue(
      error(hint("ExportEnum", Slot.INT, "names" to emptyList<String>())).contains("at least one")
    )
    assertTrue(
      error(hint("ExportFlags", Slot.INT, "names" to listOf("Fire:x"))).contains("valid integer")
    )
    assertTrue(
      error(hint("ExportFlags", Slot.INT, "names" to listOf("Fire:0"))).contains("at least 1")
    )
    assertTrue(error(hint("ExportFlags", Slot.INT, "names" to listOf(":4"))).contains("flag name"))
    val many = (1..33).map { "F$it" }
    assertTrue(error(hint("ExportFlags", Slot.INT, "names" to many)).contains("argument 33"))
    assertNull(ExportHints.flagsError("x", (1..32).map { "F$it" } + "F33:4294967295"))
    assertTrue(
      error(hint("ExportNodePath", Slot.NODE_PATH, "types" to listOf("Resource")))
        .contains("not a Node class")
    )
    assertTrue(
      error(hint("ExportRange", Slot.FLOAT, "min" to 0.0, "max" to 1.0, "suffix" to "a,b"))
        .contains("comma")
    )
  }

  @Test
  fun wrongPropertyTypesAreErrors() {
    fun error(result: Result): String = assertIs<Result.Error>(result).message
    assertTrue(
      error(hint("ExportRange", Slot.STRING, "min" to 0.0, "max" to 1.0)).contains("Long or Int")
    )
    assertTrue(error(hint("ExportFile", Slot.INT)).contains("String"))
    assertTrue(error(hint("ExportFlags3DPhysics", Slot.STRING)).contains("Long"))
    assertTrue(error(hint("ExportNodePath", Slot.STRING)).contains("NodePath"))
    assertTrue(
      error(hint("ExportEnum", Slot.OTHER, "names" to listOf("A"))).contains("@export_enum")
    )
    // A missing min/max (an annotation whose arguments KSP did not expose) is loud, not a guess.
    assertTrue(error(hint("ExportRange", Slot.FLOAT)).contains("needs min and max"))
  }
}

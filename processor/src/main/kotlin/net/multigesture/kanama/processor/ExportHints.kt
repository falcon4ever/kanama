package net.multigesture.kanama.processor

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Task 133 C — the typed inspector-hint annotations (`@ExportRange`, `@ExportFile`,
 * `@ExportFlags2DPhysics`, ...), one per GDScript `@export_*` annotation of Godot 4.7.
 *
 * [build] turns one annotation and its argument values into the hint, hint string and usage the
 * property reports, exactly as GDScript's parser does (`GDScriptParser::export_annotations` in
 * `modules/gdscript/gdscript_parser.cpp`): the arguments are joined with `,` after Godot's own
 * `String(Variant)` conversion (a float prints as `num_real(x, true)`: `0.0`, `0.01`, `1e-05` ->
 * `0.00001`), an argument may not be empty or contain a comma (except the placeholder text), the
 * flag names follow `@export_flags`'s rules, and on a typed array (`List<String>`) the hint moves
 * to the element (`TYPE_STRING` with `"4/13:*.png"`). The example project's GDScript twin of
 * `ExportHintSmoke.kt` checks the result against Godot's own `get_property_list()`.
 *
 * Pure: the processor reads the KSP annotation into [Use] and reports [Result.Error] at the
 * property, so every rule here is unit-tested without a compilation.
 */
internal object ExportHints {

  const val PROPERTY_HINT_RANGE = 1
  const val PROPERTY_HINT_ENUM = 2
  const val PROPERTY_HINT_EXP_EASING = 4
  const val PROPERTY_HINT_FLAGS = 6
  const val PROPERTY_HINT_FILE = 13
  const val PROPERTY_HINT_DIR = 14
  const val PROPERTY_HINT_GLOBAL_FILE = 15
  const val PROPERTY_HINT_GLOBAL_DIR = 16
  const val PROPERTY_HINT_MULTILINE_TEXT = 18
  const val PROPERTY_HINT_PLACEHOLDER_TEXT = 20
  const val PROPERTY_HINT_COLOR_NO_ALPHA = 21
  const val PROPERTY_HINT_TYPE_STRING = 23
  const val PROPERTY_HINT_NODE_PATH_VALID_TYPES = 26
  const val PROPERTY_HINT_FILE_PATH = 44
  const val PROPERTY_USAGE_STORAGE = 2
  const val PROPERTY_USAGE_DEFAULT = 6

  private const val VARIANT_STRING = 4

  /** The layer-flag annotations: no arguments, a fixed hint. */
  val LAYER_HINTS: Map<String, Int> =
    linkedMapOf(
      "ExportFlags2DRender" to 7,
      "ExportFlags2DPhysics" to 8,
      "ExportFlags2DNavigation" to 9,
      "ExportFlags3DRender" to 10,
      "ExportFlags3DPhysics" to 11,
      "ExportFlags3DNavigation" to 12,
      "ExportFlagsAvoidance" to 37,
    )

  /** Every hint annotation (simple name). Each exports the property by itself. */
  val HINT_ANNOTATIONS: Set<String> =
    linkedSetOf(
      "ExportRange",
      "ExportEnum",
      "ExportFlags",
      "ExportFile",
      "ExportFilePath",
      "ExportDir",
      "ExportGlobalFile",
      "ExportGlobalDir",
      "ExportMultiline",
      "ExportPlaceholder",
      "ExportExpEasing",
      "ExportColorNoAlpha",
      "ExportNodePath",
      "ExportStorage",
      "ExportCustom",
    ) + LAYER_HINTS.keys

  /** The annotations that make a property exported: `@Export` and every hint annotation. */
  val EXPORT_ANNOTATIONS: Set<String> = setOf("Export") + HINT_ANNOTATIONS

  /** The Kotlin shape of the annotated property, as far as the hint rules care. */
  enum class Slot(val description: String) {
    INT("Long or Int"),
    FLOAT("Double or Float"),
    STRING("String"),
    NODE_PATH("NodePath"),
    COLOR("Color"),
    STRING_LIST("List<String>"),
    OTHER("other"),
  }

  /** One hint annotation on one property: its simple name and argument values by parameter. */
  data class Use(val annotation: String, val args: Map<String, Any?>)

  sealed interface Result {
    /** [usage] is null when the annotation leaves the usage to `@Export` / the default. */
    data class Hint(val hint: Int, val hintString: String, val usage: Int? = null) : Result

    /** `@ExportStorage`: the type's own hint, usage `PROPERTY_USAGE_STORAGE`. */
    data object Storage : Result

    data class Error(val message: String) : Result
  }

  /**
   * The hint, hint string and usage for [use] on a property of shape [slot] ([typeName] names it in
   * errors). [nodeClassError] validates an `@ExportNodePath` class name: null when it names a
   * `Node` class (engine or `@GlobalClass` script), else why not.
   */
  fun build(
    where: String,
    use: Use,
    slot: Slot,
    typeName: String,
    nodeClassError: (String) -> String? = { null },
  ): Result {
    val name = use.annotation
    val at = "$where: @$name"
    fun wrongType(vararg expected: String): Result.Error =
      Result.Error(
        "$at needs a property of type ${expected.joinToString(" or ")}, but '$typeName' was " +
          "given (GDScript @${gdName(name)} takes the same types)."
      )
    LAYER_HINTS[name]?.let { hint ->
      if (slot != Slot.INT && slot != Slot.FLOAT) return wrongType("Long", "Int")
      return Result.Hint(hint, "")
    }
    when (name) {
      "ExportStorage" -> return Result.Storage
      "ExportCustom" -> {
        val hint =
          (use.args["hint"] as? Number)?.toInt()
            ?: return Result.Error("$at needs the hint (a PropertyHint constant).")
        val hintString = use.args["hintString"] as? String ?: ""
        val usage = (use.args["usage"] as? Number)?.toInt() ?: PROPERTY_USAGE_DEFAULT
        return Result.Hint(hint, hintString, usage)
      }
    }
    val (hint, parts) =
      when (name) {
        "ExportRange" -> {
          if (slot != Slot.INT && slot != Slot.FLOAT) {
            return wrongType("Long", "Int", "Double", "Float")
          }
          PROPERTY_HINT_RANGE to (rangeParts(at, use.args) ?: return rangeError(at, use.args))
        }
        "ExportEnum" -> {
          if (slot != Slot.INT && slot != Slot.STRING && slot != Slot.STRING_LIST) {
            return wrongType("Long", "Int", "String", "List<String>")
          }
          val names = strings(use.args["names"])
          if (names.isEmpty()) return Result.Error("$at needs at least one name.")
          PROPERTY_HINT_ENUM to names
        }
        "ExportFlags" -> {
          if (slot != Slot.INT && slot != Slot.FLOAT) return wrongType("Long", "Int")
          val names = strings(use.args["names"])
          if (names.isEmpty()) return Result.Error("$at needs at least one flag name.")
          flagsError(at, names)?.let {
            return Result.Error(it)
          }
          PROPERTY_HINT_FLAGS to names
        }
        "ExportFile",
        "ExportFilePath",
        "ExportGlobalFile" -> {
          if (slot != Slot.STRING && slot != Slot.STRING_LIST) {
            return wrongType("String", "List<String>")
          }
          val hint =
            when (name) {
              "ExportFile" -> PROPERTY_HINT_FILE
              "ExportFilePath" -> PROPERTY_HINT_FILE_PATH
              else -> PROPERTY_HINT_GLOBAL_FILE
            }
          hint to strings(use.args["filters"])
        }
        "ExportDir",
        "ExportGlobalDir" -> {
          if (slot != Slot.STRING && slot != Slot.STRING_LIST) {
            return wrongType("String", "List<String>")
          }
          (if (name == "ExportDir") PROPERTY_HINT_DIR else PROPERTY_HINT_GLOBAL_DIR) to emptyList()
        }
        "ExportMultiline" -> {
          if (slot != Slot.STRING && slot != Slot.STRING_LIST) {
            return wrongType("String", "List<String>")
          }
          PROPERTY_HINT_MULTILINE_TEXT to
            listOfNotNull(
              "monospace".takeIf { use.args["monospace"] == true },
              "no_wrap".takeIf { use.args["noWrap"] == true },
            )
        }
        "ExportPlaceholder" -> {
          if (slot != Slot.STRING && slot != Slot.STRING_LIST) {
            return wrongType("String", "List<String>")
          }
          val text =
            use.args["placeholder"] as? String
              ?: return Result.Error("$at needs the placeholder text.")
          // The one annotation whose argument may be empty or hold a comma (GDScript too).
          return element(slot, PROPERTY_HINT_PLACEHOLDER_TEXT, text)
        }
        "ExportExpEasing" -> {
          if (slot != Slot.INT && slot != Slot.FLOAT) return wrongType("Double", "Float")
          PROPERTY_HINT_EXP_EASING to
            listOfNotNull(
              "attenuation".takeIf { use.args["attenuation"] == true },
              "positive_only".takeIf { use.args["positiveOnly"] == true },
            )
        }
        // Task 133 C2: a Color is a script type now. GDScript also takes Array[Color]; Kanama has
        // no
        // List<Color> export, so the element form is not offered.
        "ExportColorNoAlpha" -> {
          if (slot != Slot.COLOR) return wrongType("Color")
          PROPERTY_HINT_COLOR_NO_ALPHA to emptyList()
        }
        "ExportNodePath" -> {
          if (slot != Slot.NODE_PATH) return wrongType("NodePath")
          val types = strings(use.args["types"])
          types.forEachIndexed { i, type ->
            if (type.isNotEmpty() && !type.contains(',')) {
              nodeClassError(type)?.let {
                return Result.Error("$at: argument ${i + 1} (\"$type\"): $it")
              }
            }
          }
          PROPERTY_HINT_NODE_PATH_VALID_TYPES to types
        }
        else -> return Result.Error("$at is not a Kanama export annotation.")
      }
    parts.forEachIndexed { i, part ->
      if (part.isEmpty()) return Result.Error("$at: argument ${i + 1} is empty.")
      if (part.contains(',')) {
        return Result.Error(
          "$at: argument ${i + 1} (\"$part\") contains a comma; pass each value as its own argument."
        )
      }
    }
    return element(slot, hint, parts.joinToString(","))
  }

  /** The scalar hint, or its typed-array element form (`"4/<hint>:<hint string>"`). */
  private fun element(slot: Slot, hint: Int, hintString: String): Result.Hint =
    if (slot == Slot.STRING_LIST) {
      Result.Hint(PROPERTY_HINT_TYPE_STRING, "$VARIANT_STRING/$hint:$hintString")
    } else {
      Result.Hint(hint, hintString)
    }

  private fun rangeParts(at: String, args: Map<String, Any?>): List<String>? {
    val min = (args["min"] as? Number)?.toDouble() ?: return null
    val max = (args["max"] as? Number)?.toDouble() ?: return null
    val step = (args["step"] as? Number)?.toDouble()
    return buildList {
      add(godotNumReal(min))
      add(godotNumReal(max))
      if (step != null && !step.isNaN()) add(godotNumReal(step))
      if (args["orGreater"] == true) add("or_greater")
      if (args["orLess"] == true) add("or_less")
      if (args["exp"] == true) add("exp")
      if (args["radiansAsDegrees"] == true) add("radians_as_degrees")
      if (args["degrees"] == true) add("degrees")
      if (args["preferSlider"] == true) add("prefer_slider")
      if (args["hideControl"] == true) add("hide_control")
      (args["suffix"] as? String)?.takeIf { it.isNotEmpty() }?.let { add("suffix:$it") }
    }
  }

  private fun rangeError(at: String, args: Map<String, Any?>): Result.Error =
    Result.Error("$at needs min and max (numbers); got min=${args["min"]}, max=${args["max"]}.")

  /** `@export_flags`'s own checks on each `Name` / `Name:value` argument. */
  internal fun flagsError(at: String, names: List<String>): String? {
    val maxFlags = 32
    names.forEachIndexed { i, arg ->
      val parts = arg.split(":", limit = 2)
      if (parts[0].isEmpty()) return "$at: argument ${i + 1}: expected a flag name."
      if (parts.size == 2) {
        val raw = parts[1]
        if (raw.isEmpty()) return "$at: argument ${i + 1}: expected a flag value."
        val value =
          raw.toLongOrNull()
            ?: return "$at: argument ${i + 1}: the flag value must be a valid integer."
        if (value < 1 || value >= (1L shl maxFlags)) {
          return "$at: argument ${i + 1}: the flag value must be at least 1 and at most 2 ** $maxFlags - 1."
        }
      } else if (i >= maxFlags) {
        return "$at: argument ${i + 1}: from argument ${maxFlags + 1} on, the flag value must be " +
          "given explicitly (\"Name:value\")."
      }
    }
    return null
  }

  private fun strings(value: Any?): List<String> =
    when (value) {
      null -> emptyList()
      is String -> listOf(value)
      is Array<*> -> value.map { it.toString() }
      is Iterable<*> -> value.map { it.toString() }
      else -> listOf(value.toString())
    }

  /** `ExportFlags2DPhysics` -> `export_flags_2d_physics`, for messages. */
  fun gdName(annotation: String): String =
    annotation
      .replace("2D", "_2d_")
      .replace("3D", "_3d_")
      .replace(Regex("([a-z])([A-Z])"), "$1_$2")
      .replace("__", "_")
      .trim('_')
      .lowercase()

  /**
   * Godot's `String::num_real(p_num, true)` (what `String(Variant(float))` prints): an integral
   * value keeps one `.0`; otherwise `%.<14 - floor(log10(|x|)) for |x| > 10>lf` with the trailing
   * zeros removed. Rounded like C's printf, from the exact binary value (half-even).
   */
  fun godotNumReal(x: Double): String {
    if (x.isNaN()) return "nan"
    if (x.isInfinite()) return if (x < 0) "-inf" else "inf"
    if (x == x.toLong().toDouble() && kotlin.math.abs(x) < 9.2e18) return "${x.toLong()}.0"
    var decimals = 14
    val abs = kotlin.math.abs(x)
    if (abs > 10) decimals -= kotlin.math.floor(kotlin.math.log10(abs)).toInt()
    if (decimals < 0) decimals = 6 // Godot's "%lf" fallback for huge magnitudes.
    var text = BigDecimal(x).setScale(decimals, RoundingMode.HALF_EVEN).toPlainString()
    if (text.contains('.')) {
      text = text.trimEnd('0')
      if (text.endsWith('.')) text += "0"
    }
    return text
  }
}

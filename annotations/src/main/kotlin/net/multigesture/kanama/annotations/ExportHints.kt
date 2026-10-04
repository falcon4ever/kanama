package net.multigesture.kanama.annotations

// The typed inspector-hint annotations (task 133 C): one per GDScript `@export_*` annotation of
// Godot 4.7. Each exports the property by itself, as in GDScript (`@ExportRange(0.0, 1.0) var x` is
// `@export_range(0, 1) var x`), and the KSP processor builds the hint string exactly as GDScript's
// parser does, so `get_property_list()` reports the same hint and hint_string for a Kotlin script
// and its GDScript twin. One hint annotation per property; `@Export` may sit next to it to set the
// Godot `name` or the `usage`. The iOS copy lives in src/iosMain/.../annotations/Annotations.kt.

/**
 * GDScript `@export_range(min, max, step, extra_hints...)` on a `Long`, `Int`, `Double` or `Float`.
 *
 * Leave [step] out to let the editor pick it (1 for integers, the inspector's default float step
 * otherwise), as GDScript does when the argument is omitted. The flags are GDScript's extra hints,
 * written in this order: `or_greater`, `or_less`, `exp`, `radians_as_degrees`, `degrees`,
 * `prefer_slider`, `hide_control`, `suffix:<unit>`.
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportRange(
  val min: Double,
  val max: Double,
  val step: Double = Double.NaN,
  val orGreater: Boolean = false,
  val orLess: Boolean = false,
  val exp: Boolean = false,
  val radiansAsDegrees: Boolean = false,
  val degrees: Boolean = false,
  val preferSlider: Boolean = false,
  val hideControl: Boolean = false,
  val suffix: String = "",
)

/**
 * GDScript `@export_enum("Warrior", "Magician:5", ...)` on a `Long` / `Int` (the value of the
 * chosen entry is stored) or a `String` (its name is stored), or a `List<String>`.
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportEnum(vararg val names: String)

/** GDScript `@export_flags("Fire", "Water:4", ...)` on a `Long` / `Int`. */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportFlags(vararg val names: String)

/** GDScript `@export_flags_2d_render` on a `Long` / `Int`. */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportFlags2DRender

/** GDScript `@export_flags_2d_physics` on a `Long` / `Int`. */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportFlags2DPhysics

/** GDScript `@export_flags_2d_navigation` on a `Long` / `Int`. */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportFlags2DNavigation

/** GDScript `@export_flags_3d_render` on a `Long` / `Int`. */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportFlags3DRender

/** GDScript `@export_flags_3d_physics` on a `Long` / `Int`. */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportFlags3DPhysics

/** GDScript `@export_flags_3d_navigation` on a `Long` / `Int`. */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportFlags3DNavigation

/** GDScript `@export_flags_avoidance` on a `Long` / `Int`. */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportFlagsAvoidance

/**
 * GDScript `@export_file("*.png", ...)` on a `String` or `List<String>`: a project file, stored as
 * a `uid://` path when the file has one.
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportFile(vararg val filters: String)

/** GDScript `@export_file_path(...)`: [ExportFile] that stores the raw `res://` path. */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportFilePath(vararg val filters: String)

/** GDScript `@export_dir` on a `String` or `List<String>`. */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportDir

/** GDScript `@export_global_file(...)` on a `String` or `List<String>`: an absolute path. */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportGlobalFile(vararg val filters: String)

/** GDScript `@export_global_dir` on a `String` or `List<String>`. */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportGlobalDir

/**
 * GDScript `@export_multiline` on a `String` or `List<String>`; [monospace] and [noWrap] are its
 * `"monospace"` and `"no_wrap"` hints.
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportMultiline(val monospace: Boolean = false, val noWrap: Boolean = false)

/** GDScript `@export_color_no_alpha` on a `Color`: the inspector picker hides alpha. */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportColorNoAlpha

/** GDScript `@export_placeholder("...")` on a `String` or `List<String>`. */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportPlaceholder(val placeholder: String)

/**
 * GDScript `@export_exp_easing` on a `Double` / `Float`; [attenuation] and [positiveOnly] are its
 * `"attenuation"` and `"positive_only"` hints.
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportExpEasing(
  val attenuation: Boolean = false,
  val positiveOnly: Boolean = false,
)

/**
 * GDScript `@export_node_path("Button", ...)` on a `NodePath`: the node classes the picker accepts
 * (engine classes or `@GlobalClass` scripts that extend `Node`).
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportNodePath(vararg val types: String)

/**
 * GDScript `@export_storage`: saved with the scene or resource and copied by `duplicate()`, but not
 * shown in the inspector (`PROPERTY_USAGE_STORAGE`).
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportStorage

/**
 * GDScript `@export_custom(hint, hint_string, usage)`: any [PropertyHint] with a hand-written hint
 * string, for the hints no typed annotation covers (`PropertyHint.PASSWORD`, `INPUT_NAME`, ...).
 * The hint string and usage are passed to Godot unchanged.
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ExportCustom(
  val hint: Int,
  val hintString: String = "",
  val usage: Int = PropertyUsage.DEFAULT,
)

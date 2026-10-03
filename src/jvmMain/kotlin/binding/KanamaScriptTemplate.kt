package net.multigesture.kanama.binding

/**
 * The editor's New Script template (task 131 item 5, F15): the shape `docs/game-dev/scripts.md`
 * documents -- a `package`, a `KanamaScript<Base>` subclass and an `@OnReady` stub -- instead of
 * the old package-less `class X(val godotObject: GodotHandle) {}` that was always `@GlobalClass`.
 *
 * Godot's `ScriptLanguage._make_template(template, class_name, base_class_name)` is not told where
 * the script will be saved, so [source] writes [PACKAGE_PLACEHOLDER] as the package and
 * `KanamaResourceFormatSaver` fills it in from the save path ([packageFor]) when the editor saves
 * the new script, before the file is written and before the script editor shows it.
 *
 * `@GlobalClass` is emitted only when asked: when the template text Godot passes in contains
 * `@GlobalClass` (a project script template). Kanama reports no built-in templates, so a plain New
 * Script has none; add the annotation to name the class globally (Add Node dialog, typed exports).
 */
internal object KanamaScriptTemplate {
  /** The package of a template the editor has not saved yet. */
  const val PACKAGE_PLACEHOLDER = "_KANAMA_PACKAGE_"

  /** [packageFor]'s answer when neither a sibling script nor the directory names a package. */
  const val DEFAULT_PACKAGE = "game"

  /** The documented script root inside a Kanama project. */
  private const val SCRIPT_ROOT = "kotlin-src/"

  private const val MAX_LINE = 100

  /**
   * The New Script source for [className] attached to [baseClass] in [packageName]. [globalClass]
   * adds `@GlobalClass`. Godot's `Object` is Kanama's `GodotObject`; a base that is not an engine
   * class name (Godot passes a quoted script path for a custom type) falls back to `Node`.
   */
  fun source(
    className: String,
    baseClass: String,
    packageName: String = PACKAGE_PLACEHOLDER,
    globalClass: Boolean = false,
  ): String {
    val name = identifier(className.ifBlank { "NewScript" })
    val attachTo = baseClass.takeIf { IDENTIFIER.matches(it) } ?: "Node"
    val wrapper = if (attachTo == "Object") "GodotObject" else attachTo
    val header =
      "class $name(godotObject: GodotHandle) : KanamaScript<$wrapper>(godotObject, ::$wrapper) {"
    return buildString {
      appendLine("package $packageName")
      appendLine()
      if (globalClass) appendLine("import net.multigesture.kanama.annotations.GlobalClass")
      appendLine("import net.multigesture.kanama.annotations.OnReady")
      appendLine("import net.multigesture.kanama.annotations.ScriptClass")
      appendLine("import net.multigesture.kanama.api.GodotHandle")
      appendLine("import net.multigesture.kanama.api.KanamaScript")
      appendLine("import net.multigesture.kanama.api.$wrapper")
      appendLine()
      appendLine("@ScriptClass(attachTo = \"$attachTo\")")
      if (globalClass) appendLine("@GlobalClass")
      if (header.length <= MAX_LINE) {
        appendLine(header)
      } else {
        appendLine("class $name(godotObject: GodotHandle) :")
        appendLine("  KanamaScript<$wrapper>(godotObject, ::$wrapper) {")
      }
      appendLine("  @OnReady")
      appendLine("  fun ready() {")
      appendLine("    // Called when the node enters the scene tree for the first time.")
      appendLine("  }")
      appendLine("}")
    }
  }

  /** True when [template] (the text Godot passes to `_make_template`) asks for `@GlobalClass`. */
  fun wantsGlobalClass(template: String): Boolean = template.contains("@GlobalClass")

  /** True while [source] still carries the unsaved template's [PACKAGE_PLACEHOLDER]. */
  fun hasPackagePlaceholder(source: String): Boolean =
    source.lineSequence().firstOrNull()?.trim() == "package $PACKAGE_PLACEHOLDER"

  /** [source] with its [PACKAGE_PLACEHOLDER] package replaced by [packageName]. */
  fun withPackage(source: String, packageName: String): String =
    if (!hasPackagePlaceholder(source)) source
    else
      "package $packageName" +
        source.substring(source.indexOf('\n').let { if (it < 0) source.length else it })

  /**
   * The package for a new script saved at [resPath]: the package [siblingPackage] already used by
   * the scripts in that directory (Kanama projects keep a flat `kotlin-src/` with one package);
   * else the directory below `res://kotlin-src/` (or below `res://`) as a dotted package; else
   * [DEFAULT_PACKAGE].
   */
  fun packageFor(resPath: String, siblingPackage: String?): String {
    if (!siblingPackage.isNullOrBlank()) return siblingPackage
    val relative = resPath.removePrefix("res://").removePrefix(SCRIPT_ROOT)
    val segments =
      relative
        .substringBeforeLast('/', "")
        .split('/')
        .filter { it.isNotEmpty() }
        .map { identifier(it) }
    return if (segments.isEmpty()) DEFAULT_PACKAGE else segments.joinToString(".")
  }

  /** The `package` a Kotlin source declares, or null. */
  fun declaredPackage(source: String): String? =
    source.lineSequence().firstNotNullOfOrNull {
      PACKAGE_LINE.matchEntire(it.trim())?.groupValues?.get(1)
    }

  private val IDENTIFIER = Regex("[A-Za-z_][A-Za-z0-9_]*")
  private val PACKAGE_LINE = Regex("package\\s+([A-Za-z_][A-Za-z0-9_.]*)\\s*;?")

  // Kotlin's hard keywords: not valid as a bare name or package segment.
  private val HARD_KEYWORDS =
    setOf(
      "as",
      "break",
      "class",
      "continue",
      "do",
      "else",
      "false",
      "for",
      "fun",
      "if",
      "in",
      "interface",
      "is",
      "null",
      "object",
      "package",
      "return",
      "super",
      "this",
      "throw",
      "true",
      "try",
      "typealias",
      "typeof",
      "val",
      "var",
      "when",
      "while",
    )

  /** [raw] as a Kotlin identifier: invalid characters become `_`; a hard keyword gets backticks. */
  private fun identifier(raw: String): String {
    val cleaned = raw.map { if (it.isLetterOrDigit() || it == '_') it else '_' }.joinToString("")
    val name = if (cleaned.firstOrNull()?.isDigit() == true) "_$cleaned" else cleaned
    return if (name in HARD_KEYWORDS) "`$name`" else name
  }
}

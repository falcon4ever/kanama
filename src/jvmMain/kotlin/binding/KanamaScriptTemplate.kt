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
   * adds `@GlobalClass`. [nodeDerived] (the base is `Node` or inherits it) adds the `@OnReady`
   * stub; other bases (`Resource`, `RefCounted`, `Object`) never enter a scene tree. Godot's
   * `Object` is Kanama's `GodotObject`; a base that is not an engine class name (Godot passes a
   * quoted script path for a custom type) falls back to `Node`. A wrapper whose constructor is not
   * public ([FACTORY_BASES], e.g. `RefCounted`, whose wrapper ownership is explicit) is built
   * through its borrowed-view factory `fromHandle`.
   */
  fun source(
    className: String,
    baseClass: String,
    packageName: String = PACKAGE_PLACEHOLDER,
    globalClass: Boolean = false,
    nodeDerived: Boolean = true,
  ): String {
    val name = identifier(className.ifBlank { "NewScript" })
    val validBase = IDENTIFIER.matches(baseClass)
    val attachTo = if (validBase) baseClass else "Node"
    val stub = nodeDerived || !validBase
    val wrapper = if (attachTo == "Object") "GodotObject" else attachTo
    val construct = if (wrapper in FACTORY_BASES) "{ $wrapper.fromHandle(it)!! }" else "::$wrapper"
    val supertype = "KanamaScript<$wrapper>(godotObject, $construct)"
    val body = if (stub) " {" else ""
    val header = "class $name(godotObject: GodotHandle) : $supertype$body"
    return buildString {
      appendLine("package $packageName")
      appendLine()
      // Sorted, as ktfmt keeps them.
      listOfNotNull(
          "net.multigesture.kanama.annotations.GlobalClass".takeIf { globalClass },
          "net.multigesture.kanama.annotations.OnReady".takeIf { stub },
          "net.multigesture.kanama.annotations.ScriptClass",
          "net.multigesture.kanama.api.GodotHandle",
          "net.multigesture.kanama.api.KanamaScript",
          "net.multigesture.kanama.api.$wrapper",
        )
        .distinct()
        .sorted()
        .forEach { appendLine("import $it") }
      appendLine()
      appendLine("@ScriptClass(attachTo = \"$attachTo\")")
      if (globalClass) appendLine("@GlobalClass")
      if (header.length <= MAX_LINE) {
        appendLine(header)
      } else {
        appendLine("class $name(godotObject: GodotHandle) :")
        appendLine("  $supertype$body")
      }
      if (stub) {
        appendLine("  @OnReady")
        appendLine("  fun ready() {")
        appendLine("    // Called when the node enters the scene tree for the first time.")
        appendLine("  }")
        appendLine("}")
      }
    }
  }

  /**
   * Engine bases whose Kotlin wrapper constructor is internal: the script's `self` is built with
   * the wrapper's public `fromHandle` (a borrowed view, never closed) instead of `::Wrapper`.
   */
  val FACTORY_BASES: Set<String> = setOf("RefCounted", "ShaderMaterial", "NoiseTexture2D")

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
   * The package for a new script saved at [resPath]: the package most of the scripts already in
   * that directory declare ([siblingPackages]; a tie goes to the alphabetically first), since
   * Kanama projects keep a flat `kotlin-src/` with one package; else the directory below
   * `res://kotlin-src/` (or below `res://`) as a dotted package; else [DEFAULT_PACKAGE]. A package
   * with a `kotlin` or `java` segment is never chosen (those namespaces are reserved).
   */
  fun packageFor(resPath: String, siblingPackages: List<String>): String {
    val sibling =
      siblingPackages
        .filter { usable(it) }
        .groupingBy { it }
        .eachCount()
        .entries
        .sortedWith(compareByDescending<Map.Entry<String, Int>> { it.value }.thenBy { it.key })
        .firstOrNull()
        ?.key
    if (sibling != null) return sibling
    val relative = resPath.removePrefix("res://").removePrefix(SCRIPT_ROOT)
    val segments =
      relative
        .substringBeforeLast('/', "")
        .split('/')
        .filter { it.isNotEmpty() }
        .map { identifier(it) }
    val derived = segments.joinToString(".")
    return if (segments.isEmpty() || !usable(derived)) DEFAULT_PACKAGE else derived
  }

  private fun usable(packageName: String): Boolean =
    packageName.isNotBlank() && packageName.split('.').none { it == "kotlin" || it == "java" }

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

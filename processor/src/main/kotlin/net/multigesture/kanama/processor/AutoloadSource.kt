package net.multigesture.kanama.processor

/**
 * Task 133 C — the generated `Autoloads` object: one typed property per autoload of the project
 * (`project.godot` `[autoload]`), GDScript's global `Settings` / `Audio` names.
 *
 * **Where it is generated, and why KSP.** The processor already runs in every build that compiles
 * scripts — the desktop scripts jar (editor **Build Scripts**, `buildScripts`, hot reload), the
 * Android scripts AAR, the iOS static library and the Web module — so the object exists wherever a
 * script can reference it, with no extra task to wire per platform. And KSP is the one place that
 * knows the Kotlin script classes: an autoload whose path is a `.kt` file (or a scene whose root
 * carries one) is typed to that class, which a Gradle task would have to re-derive by parsing
 * Kotlin. The builds declare `project.godot` as an input of their KSP task, so editing the
 * `[autoload]` section re-runs it (non-incrementally: the file is not a Kotlin source).
 *
 * **Types.** A Kotlin script autoload (`res://kotlin-src/Settings.kt`, or a scene whose root has
 * that script) is its script class; a scene autoload is its root node's class; a GDScript autoload
 * is the engine class it `extends`; anything else is `Node`. A class Kanama has no wrapper for on
 * the target falls back to its nearest wrapped engine ancestor. Only enabled autoloads (`*` in
 * `project.godot`, the "Global Variable" checkbox) are generated: GDScript names only those.
 *
 * **Resolution** is GDScript's: the node at `/root/<Name>`, looked up on each read (an
 * `AutoloadAccess` call: two engine calls, and an `is_class` check for a typed node). A missing
 * node or a node of another class throws an `IllegalStateException` naming the autoload.
 *
 * Pure: parsing and emission are unit-tested; the processor supplies the files and the classes.
 */
internal object AutoloadSource {

  /** One `[autoload]` entry: `Name="*res://path"` (`*` = enabled as a global). */
  data class Entry(val name: String, val path: String, val global: Boolean)

  /** How an autoload is typed in the generated object. */
  sealed interface Kind {
    /** A Kotlin script class (fully qualified). */
    data class Script(val fqName: String) : Kind

    /** A node of the engine class [godotClass], wrapped by [wrapperFqName]. */
    data class Node(val godotClass: String, val wrapperFqName: String) : Kind
  }

  data class Resolved(val entry: Entry, val kind: Kind, val source: String)

  /** The `[autoload]` entries of a `project.godot`, in file order. */
  fun parseProjectGodot(text: String): List<Entry> {
    val entries = mutableListOf<Entry>()
    var inSection = false
    for (raw in text.lineSequence()) {
      val line = raw.trim()
      if (line.startsWith("[")) {
        inSection = line == "[autoload]"
        continue
      }
      if (!inSection || line.isEmpty() || line.startsWith(";") || line.startsWith("#")) continue
      val eq = line.indexOf('=')
      if (eq <= 0) continue
      val name = line.substring(0, eq).trim()
      var value = line.substring(eq + 1).trim()
      if (value.length >= 2 && value.startsWith("\"") && value.endsWith("\"")) {
        value = value.substring(1, value.length - 1)
      }
      val global = value.startsWith("*")
      entries += Entry(name, value.removePrefix("*"), global)
    }
    return entries
  }

  /** What a scene's root node declares: its class, an attached script, an inherited scene. */
  data class SceneRoot(val type: String?, val scriptPath: String?, val inherited: Boolean)

  private val HEADER = Regex("""^\[(\w+)(.*)]\s*$""")
  private val ATTRIBUTE = Regex("""(\w+)=("(?:\\.|[^"\\])*"|[^\s\]]+)""")
  private val EXT_RESOURCE_REF = Regex("""^script\s*=\s*ExtResource\(\s*"([^"]*)"\s*\)""")

  private fun attributes(text: String): Map<String, String> =
    ATTRIBUTE.findAll(text).associate { m ->
      m.groupValues[1] to m.groupValues[2].removeSurrounding("\"")
    }

  /** The root node of a text scene (`.tscn`), or null when there is none. */
  fun parseSceneRoot(text: String): SceneRoot? {
    val scripts = HashMap<String, String>()
    var inRoot = false
    var root: SceneRoot? = null
    for (raw in text.lineSequence()) {
      val line = raw.trim()
      val header = HEADER.matchEntire(line)
      if (header != null) {
        if (inRoot) return root
        val attrs = attributes(header.groupValues[2])
        when (header.groupValues[1]) {
          "ext_resource" ->
            if (attrs["type"] == "Script") {
              val id = attrs["id"]
              val path = attrs["path"]
              if (id != null && path != null) scripts[id] = path
            }
          "node" ->
            if ("parent" !in attrs) {
              inRoot = true
              root = SceneRoot(attrs["type"], null, "instance" in attrs)
            }
        }
        continue
      }
      if (inRoot) {
        EXT_RESOURCE_REF.find(line)?.let { m ->
          root = root?.copy(scriptPath = scripts[m.groupValues[1]])
        }
      }
    }
    return root
  }

  private val EXTENDS = Regex("""^extends\s+([A-Za-z_][A-Za-z0-9_]*)\s*(?:#.*)?$""")

  /** The engine class a GDScript file `extends` by name (null for a path or a missing line). */
  fun gdscriptExtends(text: String): String? {
    for (raw in text.lineSequence()) {
      val line = raw.trim()
      if (line.isEmpty() || line.startsWith("#") || line.startsWith("@")) continue
      if (line.startsWith("class_name")) continue
      return EXTENDS.matchEntire(line)?.groupValues?.get(1)
    }
    return null
  }

  private val KOTLIN_KEYWORDS =
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

  private fun identifier(name: String): String =
    if (name in KOTLIN_KEYWORDS || !name.matches(Regex("[A-Za-z_][A-Za-z0-9_]*"))) "`$name`"
    else name

  private fun quote(value: String): String =
    "\"" + value.replace("\\", "\\\\").replace("\"", "\\\"").replace("$", "\\$") + "\""

  /**
   * The `Autoloads` object source. [web] selects the Web runtime's `AutoloadAccess` spelling (no
   * class-token table there: a typed node is checked by its Godot class name and wrapped by its
   * constructor).
   */
  fun emit(autoloads: List<Resolved>, packageName: String, web: Boolean): String = buildString {
    appendLine("// Generated by KanamaProcessor from project.godot [autoload] — do not edit.")
    appendLine("package $packageName")
    appendLine()
    appendLine("import net.multigesture.kanama.api.AutoloadAccess")
    appendLine()
    appendLine("/**")
    appendLine(
      " * The project's autoloads, GDScript's global names (task 133 C): `Autoloads.Settings` is"
    )
    appendLine(" * the node at `/root/Settings`, typed to its Kotlin script, scene root class or")
    appendLine(
      " * `extends` class. Each read looks the node up; a missing node or one of another class"
    )
    appendLine(" * throws an `IllegalStateException` naming the autoload.")
    appendLine(" */")
    appendLine("object Autoloads {")
    for (autoload in autoloads) {
      val name = autoload.entry.name
      val id = identifier(name)
      appendLine("    /** `$name`: ${autoload.source}. */")
      when (val kind = autoload.kind) {
        is Kind.Script ->
          appendLine(
            "    val $id: ${kind.fqName} get() = AutoloadAccess.script<${kind.fqName}>(${quote(name)})"
          )
        is Kind.Node ->
          if (web) {
            appendLine(
              "    val $id: ${kind.wrapperFqName} get() = AutoloadAccess.node(${quote(name)}, " +
                "${quote(kind.godotClass)}) { ${kind.wrapperFqName}(it) }"
            )
          } else {
            appendLine(
              "    val $id: ${kind.wrapperFqName} get() = AutoloadAccess.node<${kind.wrapperFqName}>(${quote(name)})"
            )
          }
      }
    }
    appendLine("}")
  }
}

package net.multigesture.kanama.processor

/**
 * A Godot enum or bitfield as a script member uses it (task 128 B): INT on the wire, the generated
 * `@JvmInline value class` (`Node.ProcessMode`, `GodotError`) in Kotlin. Emitters wrap a raw `Long`
 * with `<kotlinFqName>(raw)` on the way in and read `.value` on the way out, so the ABI stays the
 * INT path every backend already marshals.
 */
internal data class GodotEnumRef(
  val kotlinFqName: String,
  val isBitfield: Boolean,
  /** Godot's qualified name (`Node.ProcessMode`, `Key`): the property's `class_name`. */
  val godotKey: String,
) {
  /**
   * The usage flag GDScript's own `@export var mode: Node.ProcessMode` adds:
   * `PROPERTY_USAGE_CLASS_IS_ENUM`, or for a bitfield Godot's native `_CLASS_IS_BITFIELD` (what a
   * bitfield method argument carries; GDScript's analyzer types it as a plain `int`, so assigning a
   * combination of flags does not warn as an int-as-enum).
   */
  val classUsageFlag: Int
    get() = if (isBitfield) PROPERTY_USAGE_CLASS_IS_BITFIELD else PROPERTY_USAGE_CLASS_IS_ENUM

  /** Wraps the raw `Long` expression [raw] into the value class. */
  fun wrap(raw: String): String = "$kotlinFqName($raw)"

  /** The value class's zero (`X(0L)`): Godot has no `NONE` in every enum, so none is invented. */
  val zeroLiteral: String
    get() = wrap("0L")

  companion object {
    const val PROPERTY_USAGE_CLASS_IS_BITFIELD = 1 shl 9
    const val PROPERTY_USAGE_CLASS_IS_ENUM = 1 shl 16
  }
}

/**
 * The processor's copy of the typed-enum model, loaded from the generated `godot-enums.tsv`
 * resource (written by `scripts/generate_api_wrapper.py --write-tree` from
 * `scripts/godot_enum_model.py`, held to it by the wrapper drift gate).
 *
 * A resource table rather than KSP symbol resolution: the value names are companion getters (`val
 * ALWAYS: ProcessMode get() = ProcessMode(3L)`) in a compiled API library, so KSP sees their names
 * but never their values, and a property hint or a constant-folded default needs both.
 */
internal object GodotEnumTable {

  /** One enum: its Godot key (`Node.ProcessMode`), Kotlin FQN, kind, and values in Godot order. */
  data class Entry(
    val godotKey: String,
    val kotlinFqName: String,
    val isBitfield: Boolean,
    /** (Kotlin value name, value) in Godot's declaration order; aliases kept. */
    val values: List<Pair<String, Long>>,
  ) {
    val ref: GodotEnumRef
      get() = GodotEnumRef(kotlinFqName, isBitfield, godotKey)

    /** `PROPERTY_HINT_FLAGS` for a bitfield, `PROPERTY_HINT_ENUM` otherwise. */
    val propertyHint: Int
      get() = if (isBitfield) PROPERTY_HINT_FLAGS else PROPERTY_HINT_ENUM

    /**
     * The inspector hint string, in the format Godot's own enum properties use: each value's name
     * through Godot's `String::capitalize` (`WHEN_PAUSED` -> `When Paused`, as in `Node`'s
     * `process_mode` hint "Inherit,Pausable,When Paused,Always,Disabled") with its value made
     * explicit (`Inherit:0,…`), because Godot's values are not always 0..n (or 1,2,4,… for flags).
     */
    val hintString: String
      get() = values.joinToString(",") { (name, value) -> "${godotCapitalize(name)}:$value" }

    fun valueOf(name: String): Long? = values.firstOrNull { it.first == name }?.second
  }

  private const val RESOURCE = "/net/multigesture/kanama/processor/godot-enums.tsv"
  const val PROPERTY_HINT_ENUM = 2
  const val PROPERTY_HINT_FLAGS = 6

  private val byKotlinFqName: Map<String, Entry>
  private val byGodotKey: Map<String, Entry>

  init {
    val kotlin = HashMap<String, Entry>()
    val godot = HashMap<String, Entry>()
    val stream =
      GodotEnumTable::class.java.getResourceAsStream(RESOURCE)
        ?: error("godot-enums.tsv resource not found on classpath ($RESOURCE)")
    stream.bufferedReader().useLines { lines ->
      for (line in lines) {
        if (line.isEmpty() || line.startsWith("#")) continue
        val f = line.split('\t')
        if (f[0] != "E" || f.size < 5) continue
        val values =
          if (f[4].isEmpty()) emptyList()
          else
            f[4].split(',').map { pair ->
              val eq = pair.indexOf('=')
              pair.substring(0, eq) to pair.substring(eq + 1).toLong()
            }
        val entry = Entry(f[1], f[2], f[3] == "bitfield", values)
        kotlin[entry.kotlinFqName] = entry
        godot[entry.godotKey] = entry
      }
    }
    byKotlinFqName = kotlin
    byGodotKey = godot
  }

  /** The enum whose value class is [kotlinFqName], or null for any other type. */
  fun forKotlin(kotlinFqName: String?): Entry? = kotlinFqName?.let { byKotlinFqName[it] }

  /** The Godot key of an `enum::X` / `bitfield::X` type string, or null for any other type. */
  fun godotKeyOfType(godotType: String): String? =
    when {
      godotType.startsWith("enum::") -> godotType.removePrefix("enum::")
      godotType.startsWith("bitfield::") -> godotType.removePrefix("bitfield::")
      else -> null
    }

  /** The enum an `enum::X` / `bitfield::X` type string names, or null. */
  fun forGodotType(godotType: String): Entry? = godotKeyOfType(godotType)?.let { byGodotKey[it] }

  /**
   * Constant-folds a `@ScriptProperty` initializer of a Godot enum type into the Kotlin literal the
   * registrar's default field takes (`net.multigesture.kanama.api.Node.ProcessMode(3L)`), or null
   * when the initializer is not one of the folded shapes:
   * - a value reference, qualified as far as the source spells it: `ALWAYS` is not accepted (it
   *   needs the classifier), `ProcessMode.ALWAYS`, `Node.ProcessMode.ALWAYS`, or the full FQN;
   * - the raw constructor `Node.ProcessMode(3L)` / `(3)`;
   * - for a bitfield, `or`-chains of those (`ProcessThreadMessages.MESSAGES or …MESSAGES_PHYSICS`).
   */
  fun defaultLiteral(initializer: String, entry: Entry): String? {
    val terms =
      if (entry.isBitfield) initializer.split(Regex("""\s+or\s+""")) else listOf(initializer)
    var folded = 0L
    for (term in terms) {
      folded = folded or (termValue(term.trim(), entry) ?: return null)
    }
    return entry.ref.wrap("${folded}L")
  }

  private val VALUE_REFERENCE =
    Regex("""((?:[A-Za-z_][A-Za-z0-9_]*\.)*[A-Za-z_][A-Za-z0-9_]*)\.([A-Za-z_][A-Za-z0-9_]*)""")
  private val RAW_CONSTRUCTOR =
    Regex("""((?:[A-Za-z_][A-Za-z0-9_]*\.)*[A-Za-z_][A-Za-z0-9_]*)\(\s*(-?\d+)[lL]?\s*\)""")

  private fun namesType(qualifier: String, entry: Entry): Boolean =
    entry.kotlinFqName == qualifier || entry.kotlinFqName.endsWith(".$qualifier")

  private fun termValue(term: String, entry: Entry): Long? {
    RAW_CONSTRUCTOR.matchEntire(term)?.let { match ->
      return if (namesType(match.groupValues[1], entry)) match.groupValues[2].toLong() else null
    }
    VALUE_REFERENCE.matchEntire(term)?.let { match ->
      return if (namesType(match.groupValues[1], entry)) entry.valueOf(match.groupValues[2])
      else null
    }
    return null
  }
}

/**
 * Godot's `String::capitalize` (core/string/ustring.cpp, `_separate_compound_words` then an upper
 * first letter per word), ported for the ASCII value names: `WHEN_PAUSED` -> `When Paused`, `KEY_0`
 * -> `Key 0`, `RGBA8` -> `Rgba 8`. The inspector shows Godot's own enum properties this way.
 */
internal fun godotCapitalize(name: String): String {
  if (name.isEmpty()) return name
  val separated = StringBuilder()
  var start = 0
  var prevUpper = name[0].isUpperCase()
  var prevLower = name[0].isLowerCase()
  var prevDigit = name[0].isDigit()
  for (i in 1 until name.length) {
    val currUpper = name[i].isUpperCase()
    val currLower = name[i].isLowerCase()
    val currDigit = name[i].isDigit()
    val nextLower = i + 1 < name.length && name[i + 1].isLowerCase()
    val condA = prevLower && currUpper // aA
    val condB = (prevUpper || prevDigit) && currUpper && nextLower // AAa, 2Aa
    val condC = prevDigit && currLower && nextLower // 2aa
    val condD = (prevUpper || prevLower) && currDigit // A2, a2
    if (condA || condB || condC || condD) {
      separated.append(name, start, i).append(' ')
      start = i
    }
    prevUpper = currUpper
    prevLower = currLower
    prevDigit = currDigit
  }
  separated.append(name, start, name.length)
  val words =
    separated
      .toString()
      .map { if (it.isWhitespace() || it == '_' || it == '-') ' ' else it }
      .joinToString("")
      .lowercase()
      .trim()
  return words
    .split(' ')
    .filter { it.isNotEmpty() }
    .joinToString(" ") { it.replaceFirstChar { c -> c.uppercaseChar() } }
}

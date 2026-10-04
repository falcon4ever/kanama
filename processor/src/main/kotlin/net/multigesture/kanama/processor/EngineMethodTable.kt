package net.multigesture.kanama.processor

/**
 * The engine's own (non-virtual) method names per class, loaded from the generated
 * `engine-methods.tsv` resource (`scripts/generate_engine_method_table.py`, derived from
 * `extension_api.json`).
 *
 * Task 133 B registers every public function of a script class under its snake_case name; one that
 * lands on a method of the attached class hierarchy (`fun queueFree()` is `queue_free`) would
 * shadow the engine's method for `call()`, `Callable`s and saved scene connections on that node.
 * [declaringClass] tells the processor which class owns such a name.
 */
internal object EngineMethodTable {

  private const val RESOURCE = "/net/multigesture/kanama/processor/engine-methods.tsv"

  private val inherits: Map<String, String>
  private val methodsByClass: Map<String, Set<String>>

  init {
    val inh = HashMap<String, String>()
    val methods = HashMap<String, Set<String>>()
    val stream =
      EngineMethodTable::class.java.getResourceAsStream(RESOURCE)
        ?: error("engine-methods.tsv resource not found on classpath ($RESOURCE)")
    stream.bufferedReader().useLines { lines ->
      for (line in lines) {
        if (line.isEmpty() || line.startsWith("#")) continue
        val f = line.split('\t')
        when (f[0]) {
          "I" -> if (f.size >= 3) inh[f[1]] = f[2]
          "M" -> if (f.size >= 3) methods[f[1]] = f[2].split(',').toHashSet()
        }
      }
    }
    inherits = inh
    methodsByClass = methods
  }

  /** True when [name] is an engine class (`Object` and every class that inherits). */
  fun isClass(name: String): Boolean = name == "Object" || name in inherits

  /** [name] and its engine ancestors, nearest first (`Button`, `BaseButton`, ..., `Object`). */
  fun lineage(name: String): List<String> {
    val chain = mutableListOf<String>()
    var cls: String? = name
    while (cls != null && cls !in chain) {
      chain += cls
      cls = inherits[cls]
    }
    return chain
  }

  /** True when the engine class [name] is [base] or inherits it. */
  fun inheritsFrom(name: String, base: String): Boolean = isClass(name) && base in lineage(name)

  /**
   * The class in [attachTo]'s inheritance chain (itself first) that declares the engine method
   * [godotName], or null when none does.
   */
  fun declaringClass(attachTo: String, godotName: String): String? {
    var cls: String? = attachTo
    val seen = HashSet<String>()
    while (cls != null && seen.add(cls)) {
      if (methodsByClass[cls]?.contains(godotName) == true) return cls
      cls = inherits[cls]
    }
    return null
  }
}

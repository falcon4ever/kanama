package net.multigesture.kanama.ios

import net.multigesture.kanama.types.Vector2i

/**
 * Built-in script for the debug self-test's Map @Export rows: a `Map<Int, String>` and a
 * `Map<Vector2i, Long>` property, set and read through the engine (Object.set / Object.get on the
 * owner, so the shim's property path and the bridge run as for a project script). The bridge below
 * is what IosScriptCodeEmitter generates for these two properties (IosMapExportEmitTest pins that).
 */
internal const val DICTIONARY_PROBE_SCRIPT_PATH = "res://kanama_ios_dictionary_probe.kt"

/** The probe's script object: the generated bridge reads and writes these two fields. */
internal class DictionaryProbeScript {
  var names: Map<Int, String> = emptyMap()
  var cells: Map<Vector2i, Long> = emptyMap()

  companion object {
    /** The instance the latest script attach created (the self-test reads its fields). */
    var last: DictionaryProbeScript? = null
  }
}

internal fun dictionaryProbeDescriptor(path: String): KanamaIosScriptDescriptor? =
  if (path != DICTIONARY_PROBE_SCRIPT_PATH) null
  else
    KanamaIosScriptDescriptor(
      path = path,
      baseType = "Node",
      methods = emptyList(),
      properties =
        listOf(
          KanamaIosScriptProperty("names", 27, 38, "2:;4:", 6),
          KanamaIosScriptProperty("cells", 27, 38, "6:;2:", 6),
        ),
      signals = emptyList(),
      rpcConfigs = emptyList(),
      factory = { _ ->
        DictionaryProbeBridge(DictionaryProbeScript().also { DictionaryProbeScript.last = it })
      },
    )

/** The bridge IosScriptCodeEmitter generates for the two properties (IosMapExportEmitTest). */
private class DictionaryProbeBridge(private val script: DictionaryProbeScript) :
  KanamaIosScriptBridge {
  override val scriptInstance: Any
    get() = script

  override fun getProperty(propertyIndex: Int): Any? =
    when (propertyIndex) {
      0 -> script.names.let { m -> net.multigesture.kanama.ios.KanamaIosTypedDictionary(m) }
      1 -> script.cells.let { m -> net.multigesture.kanama.ios.KanamaIosTypedDictionary(m) }
      else -> net.multigesture.kanama.ios.KanamaIosNoProperty
    }

  override fun setPropertyDictionary(propertyIndex: Int, entries: Map<Any?, Any?>): Boolean =
    when (propertyIndex) {
      0 -> {
        script.names =
          entries.entries
            .mapNotNull { entry ->
              val k = ((entry.key as? Number)?.toInt()) ?: return@mapNotNull null
              val v = (entry.value as? String) ?: return@mapNotNull null
              k to v
            }
            .toMap()
        true
      }
      1 -> {
        script.cells =
          entries.entries
            .mapNotNull { entry ->
              val k =
                (entry.key as? net.multigesture.kanama.types.Vector2i) ?: return@mapNotNull null
              val v = ((entry.value as? Number)?.toLong()) ?: return@mapNotNull null
              k to v
            }
            .toMap()
        true
      }
      else -> false
    }
}

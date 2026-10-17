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

/** Before a Map @Export had an iOS path, the generated bridge had no case for either property. */
private class DictionaryProbeBridge(private val script: DictionaryProbeScript) :
  KanamaIosScriptBridge {
  override val scriptInstance: Any
    get() = script
}

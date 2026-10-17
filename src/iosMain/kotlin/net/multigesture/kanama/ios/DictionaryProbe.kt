package net.multigesture.kanama.ios

import net.multigesture.kanama.types.Vector2i

/**
 * Built-in script for the debug self-test's Map @Export rows: a `Map<Int, String>` and a
 * `Map<Vector2i, Long>` property, set and read through the engine (Object.set / Object.get on the
 * owner, so the shim's property path and the bridge run as for a project script). The bridge below
 * is what IosScriptCodeEmitter generates for these two properties (IosMapExportEmitTest pins that).
 */
internal const val DICTIONARY_PROBE_SCRIPT_PATH = "res://kanama_ios_dictionary_probe.kt"

/** The enum key of the probe's `tiers` Map. */
internal enum class DictionaryProbeTier {
  LOW,
  HIGH,
}

/** The probe's script object: the generated bridge reads and writes these fields. */
internal class DictionaryProbeScript {
  var names: Map<Int, String> = emptyMap()
  var cells: Map<Vector2i, Long> = emptyMap()
  var tiers: Map<DictionaryProbeTier, Double?> = emptyMap()
  var textures: Map<String, net.multigesture.kanama.api.Texture2D> = emptyMap()

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
          KanamaIosScriptProperty("tiers", 27, 38, "2/2:LOW,HIGH;3:", 6),
          KanamaIosScriptProperty("textures", 27, 38, "4:;24/17:Texture2D", 6),
        ),
      signals = emptyList(),
      rpcConfigs = emptyList(),
      factory = { _ ->
        DictionaryProbeBridge(DictionaryProbeScript().also { DictionaryProbeScript.last = it })
      },
    )

/** The bridge IosScriptCodeEmitter generates for these properties (IosMapExportEmitTest). */
private class DictionaryProbeBridge(private val script: DictionaryProbeScript) :
  KanamaIosScriptBridge {
  override val scriptInstance: Any
    get() = script

  override fun getProperty(propertyIndex: Int): Any? =
    when (propertyIndex) {
      0 -> script.names.let { m -> net.multigesture.kanama.ios.KanamaIosTypedDictionary(m) }
      1 -> script.cells.let { m -> net.multigesture.kanama.ios.KanamaIosTypedDictionary(m) }
      2 ->
        script.tiers.let { m ->
          net.multigesture.kanama.ios.KanamaIosTypedDictionary(
            m.entries.associate { (k, v) -> k.ordinal.toLong() to v }
          )
        }
      3 -> script.textures.let { m -> net.multigesture.kanama.ios.KanamaIosTypedDictionary(m) }
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
      2 -> {
        script.tiers =
          entries.entries
            .mapNotNull { entry ->
              val k =
                ((entry.key as? Number)?.toInt()?.let {
                  net.multigesture.kanama.ios.DictionaryProbeTier.entries[
                      it.coerceIn(
                        0,
                        net.multigesture.kanama.ios.DictionaryProbeTier.entries.lastIndex,
                      )]
                }) ?: return@mapNotNull null
              k to (((entry.value as? Number)?.toDouble()))
            }
            .toMap()
        true
      }
      3 -> {
        script.textures =
          entries.entries
            .mapNotNull { entry ->
              val k = (entry.key as? String) ?: return@mapNotNull null
              val v =
                ((entry.value as? net.multigesture.kanama.api.GodotObject)?.let { o ->
                  net.multigesture.kanama.api.Texture2D.fromHandle(o.handle)
                }) ?: return@mapNotNull null
              k to v
            }
            .toMap()
        true
      }
      else -> false
    }

  override fun retainsDictionaryValues(propertyIndex: Int): Boolean = propertyIndex == 3
}

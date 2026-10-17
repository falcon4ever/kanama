package net.multigesture.kanama.processor

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Map @Export on iOS: a typed `Map<K, V>` with scalar, String, value-type or enum keys and values
 * is delivered through setPropertyDictionary and read back as a KanamaIosTypedDictionary, with the
 * desktop registrar's own key/value transforms (so both backends keep and drop the same entries); a
 * plain `Map<String, Any?>` rides the generic String-keyed path; object values are wrapped like
 * desktop wraps them and Resource values are retained like desktop retains them. The built-in
 * self-test probe (src/iosMain/.../ios/DictionaryProbe.kt) must carry the bridge this emitter
 * generates for its properties, so the device rows test generated code.
 */
class IosMapExportEmitTest {
  private fun mapProp(
    name: String,
    key: String,
    value: String,
    keyEnum: List<String> = emptyList(),
    valueEnumFq: String? = null,
    valueNullable: Boolean = false,
    wrapper: String? = null,
    script: String? = null,
    scriptIsResource: Boolean = false,
  ) =
    ScriptPropertyModel(
      kotlinName = name,
      godotName = name,
      type = TypeMapping.DICTIONARY,
      isMutable = true,
      hint = 38,
      mapKeyKotlinType = key,
      mapKeyEnumEntries = keyEnum,
      mapValueKotlinType =
        if (valueEnumFq != null || wrapper != null || script != null) null else value,
      mapValueEnumFqName = valueEnumFq,
      mapValueEnumEntries = if (valueEnumFq != null) listOf("A", "B") else emptyList(),
      mapValueWrapperFqName = wrapper,
      mapValueCustomScriptFqName = script,
      mapValueCustomScriptIsResource = scriptIsResource,
      mapValueNullable = valueNullable,
    )

  private fun emit(vararg props: ScriptPropertyModel): Pair<String, List<String>> {
    val errors = mutableListOf<String>()
    val model =
      ScriptModel(
        simpleName = "Maps",
        fqName = "net.multigesture.kanama.test.Maps",
        attachTo = "Node",
        isTool = false,
        isGlobalClass = false,
        properties = props.toList(),
        toolButtons = emptyList(),
        virtuals = emptyList(),
        methods = emptyList(),
        signals = emptyList(),
      )
    val source =
      IosScriptCodeEmitter(listOf(IosScriptInput(model, "res://Maps.kt")), error = { errors += it })
        .registrySource()
    return source to errors
  }

  // ktfmt breaks a `{ a; b }` branch over lines, drops the semicolons and adds trailing commas:
  // compare without all three.
  private fun squash(text: String) =
    text.filterNot { it.isWhitespace() || it == ';' }.replace(",)", ")").replace(",]", "]")

  @Test
  fun typedMapsAreDeliveredWithTheDesktopTransforms() {
    val names = mapProp("names", "Int", "String")
    val cells = mapProp("cells", "net.multigesture.kanama.types.Vector2i", "Long")
    val (source, errors) = emit(names, cells)
    assertEquals(emptyList(), errors)
    val setNames =
      "script.names = entries.entries.mapNotNull { entry -> val k = " +
        mapKeyReadTransformSafe(names, "entry.key") +
        " ?: return@mapNotNull null; val v = " +
        mapValueReadTransformSafe(names, "entry.value") +
        " ?: return@mapNotNull null; k to v }.toMap()"
    assertTrue(source.contains("0 -> { $setNames; true }"), source)
    assertTrue(source.contains("override fun setPropertyDictionary(propertyIndex: Int"), source)
    assertTrue(
      source.contains(
        "0 -> script.names.let { m -> net.multigesture.kanama.ios.KanamaIosTypedDictionary(m) }"
      ),
      source,
    )
    assertTrue(source.contains("(entry.key as? net.multigesture.kanama.types.Vector2i)"), source)
  }

  @Test
  fun enumKeysLeaveAsOrdinalsAndNullableValuesKeepTheirKey() {
    val (source, errors) =
      emit(
        mapProp("modes", "net.x.Mode", "Long", keyEnum = listOf("A", "B"), valueNullable = true),
        mapProp("tiers", "String", "", valueEnumFq = "net.x.Tier"),
      )
    assertEquals(emptyList(), errors)
    assertTrue(source.contains("m.entries.associate { (k, v) -> k.ordinal.toLong() to v }"), source)
    assertTrue(source.contains("k to (((entry.value as? Number)?.toLong()))"), source)
    assertTrue(source.contains("m.entries.associate { (k, v) -> k to v.ordinal.toLong() }"), source)
  }

  @Test
  fun aPlainStringKeyedMapRidesTheGenericPath() {
    val plain =
      ScriptPropertyModel(
        kotlinName = "meta",
        godotName = "meta",
        type = TypeMapping.DICTIONARY,
        isMutable = true,
      )
    val (source, errors) = emit(plain)
    assertEquals(emptyList(), errors)
    assertTrue(source.contains("0 -> script.meta\n"), source)
    assertTrue(
      source.contains("(entry.key as? String)?.let { k -> k to entry.value } }.toMap()"),
      source,
    )
  }

  @Test
  fun objectValuedMapsAreWrappedAndResourceValuesRetained() {
    val (source, errors) =
      emit(
        mapProp("textures", "String", "", wrapper = "net.multigesture.kanama.api.Texture2D"),
        mapProp("nodes", "String", "", wrapper = "net.multigesture.kanama.api.Node3D"),
        mapProp("items", "Int", "", script = "net.x.Item", scriptIsResource = true),
        mapProp("enemies", "Int", "", script = "net.x.Enemy"),
      )
    assertEquals(emptyList(), errors)
    assertTrue(
      source.contains(
        "(entry.value as? net.multigesture.kanama.api.GodotObject)?.let { o -> " +
          "net.multigesture.kanama.api.Texture2D.fromHandle(o.handle) }"
      ),
      source,
    )
    assertTrue(source.contains("?.let { o -> net.multigesture.kanama.api.Node3D(o.handle) }"))
    assertTrue(
      source.contains(
        "?.let { o -> net.multigesture.kanama.ios.iosScriptInstanceForOwner(" +
          "o.handle.segment.address()) as? net.x.Item }"
      ),
      source,
    )
    // A script value leaves as the script itself (the container encoder ships its owner).
    assertTrue(
      source.contains(
        "2 -> script.items.let { m -> net.multigesture.kanama.ios.KanamaIosTypedDictionary(m) }"
      ),
      source,
    )
    // Retained like desktop: resource wrappers and Resource scripts, not nodes or node scripts.
    assertTrue(
      source.contains(
        "override fun dictionaryRetainedValues(propertyIndex: Int): Collection<Any?>? = " +
          "when (propertyIndex) {\n        0 -> script.textures.values\n" +
          "        2 -> script.items.values\n        else -> null"
      ),
      source,
    )
  }

  @Test
  fun theSelfTestProbeCarriesTheGeneratedBridge() {
    val tier = "net.multigesture.kanama.ios.DictionaryProbeTier"
    val (source, _) =
      emit(
        mapProp("names", "Int", "String"),
        mapProp("cells", "net.multigesture.kanama.types.Vector2i", "Long"),
        mapProp("tiers", tier, "Double", keyEnum = listOf("LOW", "HIGH"), valueNullable = true),
        mapProp("textures", "String", "", wrapper = "net.multigesture.kanama.api.Texture2D"),
      )
    val generated =
      source.lines().filter { line ->
        val t = line.trim()
        (Regex("^[0-3] -> ").containsMatchIn(t) && "script." in t) ||
          t.startsWith("override fun dictionaryRetainedValues")
      }
    assertEquals(10, generated.size, source)
    val probe =
      squash(
        File(ROOT, "src/iosMain/kotlin/net/multigesture/kanama/ios/DictionaryProbe.kt").readText()
      )
    generated.forEach { line ->
      assertTrue(squash(line) in probe, "DictionaryProbe.kt lacks the generated `${line.trim()}`")
    }
  }

  private companion object {
    val ROOT: File =
      generateSequence(File("").absoluteFile) { it.parentFile }
        .first { File(it, "ios/bootstrap/kanama_ios_shim.c").exists() }
  }
}

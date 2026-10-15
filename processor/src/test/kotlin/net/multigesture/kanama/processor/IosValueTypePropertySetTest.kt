package net.multigesture.kanama.processor

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Task 138: on iOS the C shim decides which script properties follow GDScript's rule for a value of
 * another type (convert through `Variant::construct`, else refuse by name) from the Variant type
 * the property is registered with: Vector2 ... Color (5..20) and NodePath (22)
 * (`kanama_ios_is_value_type_property`). The runtime then assigns `value as T`, which is only safe
 * when the shim has made the Variant the property's own type. This pins the pairing off-device:
 * every property the emitter assigns through that cast is registered under one of those types, and
 * every property registered under one of them is assigned through it.
 */
class IosValueTypePropertySetTest {
  private val shimValueTypes = (5..20).toSet() + 22

  private fun model(types: List<TypeMapping>) =
    ScriptModel(
      simpleName = "Values",
      fqName = "net.multigesture.kanama.test.Values",
      attachTo = "Node",
      isTool = false,
      isGlobalClass = false,
      properties =
        types.map {
          ScriptPropertyModel(
            kotlinName = it.name.lowercase(),
            godotName = it.name.lowercase(),
            type = it,
            isMutable = true,
          )
        },
      toolButtons = emptyList(),
      virtuals = emptyList(),
      methods = emptyList(),
      signals = emptyList(),
    )

  @Test
  fun theValueCastPathIsExactlyTheShimsValueTypes() {
    // Object / container properties carry their own model fields; the plain TypeMappings are the
    // scalars, the vectors, the value types and the shapes that have no iOS export path.
    val types =
      TypeMapping.entries.filter {
        it != TypeMapping.OBJECT && it != TypeMapping.ARRAY && it != TypeMapping.DICTIONARY
      }
    val source =
      IosScriptCodeEmitter(
          listOf(IosScriptInput(model(types), "res://Values.kt")),
          error = {},
          warn = {},
        )
        .registrySource()
    val registered =
      Regex("""KanamaIosScriptProperty\("([a-z0-9_]+)", (\d+),""").findAll(source).associate {
        it.groupValues[1] to it.groupValues[2].toInt()
      }
    val castNames =
      Regex("""script\.([a-z0-9_]+) = value as """)
        .findAll(source)
        .map { it.groupValues[1] }
        .toSet()
    assertTrue(castNames.isNotEmpty(), source)
    castNames.forEach { name ->
      assertTrue(
        registered.getValue(name) in shimValueTypes,
        "$name is assigned `value as T` but registered as Variant type ${registered[name]}",
      )
    }
    registered
      .filterValues { it in shimValueTypes }
      .keys
      .forEach { name ->
        assertTrue(name in castNames, "$name is registered as a value type but has no value cast")
      }
    assertEquals(
      setOf(
        "vector2",
        "vector2i",
        "rect2",
        "rect2i",
        "vector3",
        "vector3i",
        "transform2d",
        "vector4",
        "vector4i",
        "plane",
        "quaternion",
        "aabb",
        "basis",
        "transform3d",
        "projection",
        "color",
        "node_path",
      ),
      castNames,
    )
  }

  @Test
  fun theShimConvertsExactlyThoseTypes() {
    val shim =
      generateSequence(File("").absoluteFile) { it.parentFile }
        .map { File(it, "ios/bootstrap/kanama_ios_shim.c") }
        .first { it.isFile }
        .readText()
    val predicate =
      shim
        .substringAfter("static int kanama_ios_is_value_type_property(int32_t variant_type) {")
        .substringBefore("}")
    assertTrue(
      predicate.contains(
        "variant_type >= KANAMA_IOS_VARIANT_TYPE_VECTOR2 && variant_type <= KANAMA_IOS_VARIANT_TYPE_COLOR"
      ) && predicate.contains("variant_type == KANAMA_IOS_VARIANT_TYPE_NODE_PATH"),
      predicate,
    )
    assertTrue(shim.contains("KANAMA_IOS_VARIANT_TYPE_VECTOR2 = 5,"))
    assertTrue(shim.contains("KANAMA_IOS_VARIANT_TYPE_COLOR = 20,"))
    assertTrue(shim.contains("KANAMA_IOS_VARIANT_TYPE_NODE_PATH = 22,"))
  }
}

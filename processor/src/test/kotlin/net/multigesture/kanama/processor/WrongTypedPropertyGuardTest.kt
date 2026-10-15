package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Task 138 items 26 and 27: a typed script property that receives a Variant of another type is
 * handled the way GDScript handles it: the generated decode runs inside
 * `BuiltinTypes.readAssignableVariant`, which hands it the offered Variant when the type matches
 * and otherwise the Variant `Variant::construct` converted it to (int and float, a String to a
 * number, a Basis to a Quaternion, ...), so the engine decides the coercions, not a table here.
 * What the engine cannot convert (a Nil, a Vector2 for a Vector3, a Dictionary for an int) is an
 * error naming the property, not a silent `0`, `ZERO` or empty value.
 */
class WrongTypedPropertyGuardTest {
  private fun prop(name: String, type: TypeMapping) =
    ScriptPropertyModel(kotlinName = name, godotName = name, type = type, isMutable = true)

  private val guarded =
    mapOf(
      "flag" to (TypeMapping.BOOL to "BOOL"),
      "count" to (TypeMapping.INT to "INT"),
      "ratio" to (TypeMapping.FLOAT to "FLOAT"),
      "label" to (TypeMapping.STRING to "STRING"),
      "path" to (TypeMapping.NODE_PATH to "NODE_PATH"),
      "pos2" to (TypeMapping.VECTOR2 to "VECTOR2"),
      "cell2" to (TypeMapping.VECTOR2I to "VECTOR2I"),
      "pos3" to (TypeMapping.VECTOR3 to "VECTOR3"),
      "cell3" to (TypeMapping.VECTOR3I to "VECTOR3I"),
      "tint" to (TypeMapping.COLOR to "COLOR"),
      "meta" to (TypeMapping.DICTIONARY to "DICTIONARY"),
      // The value types that decode through readVariantValue take the same conversions.
      "rot" to (TypeMapping.QUATERNION to "QUATERNION"),
      "area" to (TypeMapping.RECT2 to "RECT2"),
      "cell4" to (TypeMapping.VECTOR4I to "VECTOR4I"),
      "plane" to (TypeMapping.PLANE to "PLANE"),
      "xform" to (TypeMapping.TRANSFORM3D to "TRANSFORM3D"),
      "proj" to (TypeMapping.PROJECTION to "PROJECTION"),
    )

  private fun model(properties: List<ScriptPropertyModel>) =
    ScriptModel(
      simpleName = "GuardFixture",
      fqName = "test.GuardFixture",
      attachTo = "Node",
      isTool = false,
      isGlobalClass = false,
      properties = properties,
      toolButtons = emptyList(),
      virtuals = emptyList(),
      methods = emptyList(),
      signals = emptyList(),
    )

  private fun source(properties: List<ScriptPropertyModel>) =
    ScriptCodeEmitter(model(properties), "GuardFixtureScriptRegistrar").emit()

  /** The `dispatchSet` branch of the property [name]: from its label to the `true` it returns. */
  private fun setBranch(source: String, name: String): String {
    val set = source.substring(source.indexOf("dispatchSet = {"), source.indexOf("dispatchGet = {"))
    val start = set.indexOf("n_${name}_NameValue -> {")
    assertTrue(start >= 0, "set branch of $name")
    return set.substring(start, set.indexOf("true\n", start))
  }

  @Test
  fun everyScalarVectorValueAndContainerSetterDecodesTheAssignableVariant() {
    val src = source(guarded.map { (name, spec) -> prop(name, spec.first) })
    guarded.forEach { (name, spec) ->
      val branch = setBranch(src, name)
      val wrapper =
        "val v = BuiltinTypes.readAssignableVariant(value, VariantType.${spec.second}, " +
          "\"property '$name'\") { vVariant -> val vDecoded = "
      assertTrue(branch.contains(wrapper), "$name wrapper in: $branch")
      // The decode reads the Variant it is handed (perhaps a converted one), never `value` again:
      // a second conversion would report a bad colour name twice.
      val decode = branch.substringAfter(wrapper)
      assertTrue(decode.contains("vVariant"), "$name decode in: $branch")
      assertFalse(decode.contains("(value"), "$name decode reads value in: $branch")
      assertTrue(branch.contains("; vDecoded }"), "$name decode result in: $branch")
    }
  }

  @Test
  fun typedListsAndMapsAreArraysAndDictionaries() {
    val src =
      source(
        listOf(
          prop("names", TypeMapping.ARRAY).copy(arrayElementString = true),
          prop("meshes", TypeMapping.ARRAY)
            .copy(arrayElementWrapperFqName = "net.multigesture.kanama.api.Mesh"),
          prop("scores", TypeMapping.DICTIONARY)
            .copy(mapKeyKotlinType = "kotlin.String", mapValueKotlinType = "kotlin.Long"),
        )
      )
    assertTrue(setBranch(src, "names").contains("VariantType.ARRAY, \"property 'names'\")"))
    assertTrue(setBranch(src, "meshes").contains("VariantType.ARRAY, \"property 'meshes'\")"))
    assertTrue(setBranch(src, "scores").contains("VariantType.DICTIONARY, \"property 'scores'\")"))
  }

  @Test
  fun objectsKeepTheirOwnDecode() {
    val src =
      source(
        listOf(
          prop("target", TypeMapping.OBJECT)
            .copy(objectWrapperFqName = "net.multigesture.kanama.api.Node")
        )
      )
    assertFalse(src.contains("readAssignableVariant"), src)
  }

  @Test
  fun aValueTypeStillDecodesThroughReadVariantValue() {
    val branch = setBranch(source(listOf(prop("rot", TypeMapping.QUATERNION))), "rot")
    assertTrue(
      branch.contains(
        "BuiltinTypes.readVariantValue<net.multigesture.kanama.types.Quaternion>(vVariant, a, " +
          "\"property 'rot'\")"
      ),
      branch,
    )
  }

  @Test
  fun aMutableListKeepsItsConversionInsideTheDecode() {
    val src =
      source(
        listOf(
          prop("names", TypeMapping.ARRAY).copy(arrayElementString = true, isMutableList = true)
        )
      )
    val branch = setBranch(src, "names")
    assertTrue(branch.contains(".toMutableList(); vDecoded }"), branch)
    assertEquals(1, Regex("readAssignableVariant").findAll(branch).count())
  }
}

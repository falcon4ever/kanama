package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Task 131 item 9 (F25): a @Export the iOS backend cannot deliver used to be a warning and silently
 * kept its Kotlin default on iOS, so the scene and inspector value vanished on a Supported
 * platform. It is now a build error unless the project opts in with `-PkanamaIosAllowSkips=true`
 * (KSP option [ALLOW_IOS_SKIPS_OPTION]), which restores the warning.
 */
class IosExportSkipTest {
  private class Result(val warnings: List<String>, val errors: List<String>)

  private fun emit(allowSkips: Boolean, vararg props: ScriptPropertyModel): Result {
    val warnings = mutableListOf<String>()
    val errors = mutableListOf<String>()
    val model =
      ScriptModel(
        simpleName = "SkipFixture",
        fqName = "net.multigesture.kanama.test.SkipFixture",
        attachTo = "Node",
        isTool = false,
        isGlobalClass = false,
        properties = props.toList(),
        toolButtons = emptyList(),
        virtuals = emptyList(),
        methods = emptyList(),
        signals = emptyList(),
      )
    IosScriptCodeEmitter(
        listOf(IosScriptInput(model, "res://SkipFixture.kt")),
        warn = { warnings += it },
        error = { errors += it },
        allowSkips = allowSkips,
      )
      .registrySource()
    return Result(warnings, errors)
  }

  private fun prop(name: String, type: TypeMapping) =
    ScriptPropertyModel(kotlinName = name, godotName = name, type = type, isMutable = true)

  // A Map with engine-object values (delivered on iOS too).
  private fun objectMap(name: String) =
    prop(name, TypeMapping.DICTIONARY)
      .copy(
        mapKeyKotlinType = "String",
        mapValueWrapperFqName = "net.multigesture.kanama.api.Texture2D",
      )

  @Test
  fun aValueTypeWithoutAnIosPathIsABuildError() {
    // Task 133: every Variant value type but RID has an iOS @Export path now.
    val r = emit(false, prop("cell", TypeMapping.RID))

    assertEquals(1, r.errors.size, "${r.errors}")
    assertTrue(r.errors.single().contains("SkipFixture.cell (RID)"), r.errors.single())
    assertTrue(r.errors.single().contains("-P$ALLOW_IOS_SKIPS_OPTION=true"), r.errors.single())
    assertEquals(emptyList(), r.warnings)
  }

  @Test
  fun theOptInTurnsEachSkipBackIntoOneWarning() {
    val r = emit(true, prop("cell", TypeMapping.RID), prop("slot", TypeMapping.RID))

    assertEquals(emptyList(), r.errors)
    assertEquals(2, r.warnings.size, "${r.warnings}")
    assertTrue(r.warnings.all { it.contains("allowed by $ALLOW_IOS_SKIPS_OPTION") })
  }

  @Test
  fun deliveredPropertiesAreNotAffected() {
    val r =
      emit(
        false,
        prop("count", TypeMapping.INT),
        prop("motion", TypeMapping.VECTOR2),
        prop("label", TypeMapping.STRING),
        // Task 133 C2: Vector2i (task 131 item 15) and Color are delivered on iOS now.
        prop("cell", TypeMapping.VECTOR2I),
        prop("tint", TypeMapping.COLOR),
        // A Map<String, Any?> and a typed Map with scalar keys and values are delivered too.
        prop("meta", TypeMapping.DICTIONARY),
        objectMap("regions"),
        prop("names", TypeMapping.DICTIONARY)
          .copy(mapKeyKotlinType = "Int", mapValueKotlinType = "String"),
        // Task 133: the remaining value types, as raw Godot bytes.
        *IOS_RAW_VALUE_TYPES.map { prop(it.name.lowercase(), it) }.toTypedArray(),
      )

    assertEquals(emptyList(), r.errors)
    assertEquals(emptyList(), r.warnings)
  }
}

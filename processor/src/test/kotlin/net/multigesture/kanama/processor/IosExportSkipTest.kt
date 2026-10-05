package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Task 131 item 9 (F25): a @Export the iOS backend cannot deliver used to be a warning and silently
 * kept its Kotlin default on iOS, so the scene and inspector value vanished on a Supported
 * platform. It is now a build error unless the project opts in with
 * `-PkanamaIosAllowExportSkips=true` (KSP option [ALLOW_EXPORT_SKIPS_OPTION]), which restores the
 * warning.
 */
class IosExportSkipTest {
  private class Result(val warnings: List<String>, val errors: List<String>)

  private fun emit(allowExportSkips: Boolean, vararg props: ScriptPropertyModel): Result {
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
        allowExportSkips = allowExportSkips,
      )
      .registrySource()
    return Result(warnings, errors)
  }

  private fun prop(name: String, type: TypeMapping) =
    ScriptPropertyModel(kotlinName = name, godotName = name, type = type, isMutable = true)

  @Test
  fun aMapExportIsABuildErrorOnIos() {
    val r = emit(false, prop("regions", TypeMapping.DICTIONARY))

    assertEquals(1, r.errors.size, "one error per property, not one per guard: ${r.errors}")
    val error = r.errors.single()
    assertTrue(error.contains("SkipFixture.regions (Map)"), error)
    assertTrue(error.contains("-P$ALLOW_EXPORT_SKIPS_OPTION=true"), error)
    assertEquals(emptyList(), r.warnings)
  }

  @Test
  fun aValueTypeWithoutAnIosPathIsABuildError() {
    // Task 133: every Variant value type but RID has an iOS @Export path now.
    val r = emit(false, prop("cell", TypeMapping.RID))

    assertEquals(1, r.errors.size, "${r.errors}")
    assertTrue(r.errors.single().contains("SkipFixture.cell (RID)"), r.errors.single())
  }

  @Test
  fun theOptInTurnsEachSkipBackIntoOneWarning() {
    val r = emit(true, prop("regions", TypeMapping.DICTIONARY), prop("cell", TypeMapping.RID))

    assertEquals(emptyList(), r.errors)
    assertEquals(2, r.warnings.size, "${r.warnings}")
    assertTrue(r.warnings.all { it.contains("allowed by $ALLOW_EXPORT_SKIPS_OPTION") })
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
        // Task 133: the remaining value types, as raw Godot bytes.
        *IOS_RAW_VALUE_TYPES.map { prop(it.name.lowercase(), it) }.toTypedArray(),
      )

    assertEquals(emptyList(), r.errors)
    assertEquals(emptyList(), r.warnings)
  }
}

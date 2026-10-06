package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Task 131 N7: a script method iOS cannot dispatch -- a `@Function` (or any registered method,
 * virtual included) with an argument type the iOS call path does not pass, an `@OverrideVirtual`
 * whose return iOS does not marshal -- used to be only a warning, and the method silently never ran
 * on iOS while it worked on desktop. Each is now a build error unless the project opts in with
 * `-PkanamaIosAllowSkips=true` ([ALLOW_IOS_SKIPS_OPTION], the one opt-in for every iOS skip). Types
 * the iOS marshal path already handled elsewhere were added instead: RID arguments (typed signals
 * already decoded them), NodePath and the task-133 value types as returns (methods already returned
 * the value types; virtuals did not).
 */
class IosMethodSkipTest {
  private class Result(val source: String, val warnings: List<String>, val errors: List<String>)

  private fun emit(
    allowSkips: Boolean = false,
    methods: List<MethodModel> = emptyList(),
    virtuals: List<VirtualModel> = emptyList(),
  ): Result {
    val warnings = mutableListOf<String>()
    val errors = mutableListOf<String>()
    val model =
      ScriptModel(
        simpleName = "MethodSkipFixture",
        fqName = "net.multigesture.kanama.test.MethodSkipFixture",
        attachTo = "Node",
        isTool = false,
        isGlobalClass = false,
        properties = emptyList(),
        toolButtons = emptyList(),
        virtuals = virtuals,
        methods = methods,
        signals = emptyList(),
      )
    val source =
      IosScriptCodeEmitter(
          listOf(IosScriptInput(model, "res://MethodSkipFixture.kt")),
          warn = { warnings += it },
          error = { errors += it },
          allowSkips = allowSkips,
        )
        .registrySource()
    return Result(source, warnings, errors)
  }

  private fun method(name: String, returnType: TypeMapping?, vararg args: ArgModel) =
    MethodModel(
      kotlinName = name,
      godotName = name,
      returnType = returnType,
      args = args.toList(),
      kind = MethodKind.REGULAR,
    )

  private fun virtual(name: String, kotlinName: String, returnType: TypeMapping?) =
    VirtualModel(
      virtualName = name,
      callFunctionName = "call_$kotlinName",
      kotlinMethodName = kotlinName,
      returnType = returnType,
    )

  @Test
  fun aVoidMethodWithAnArgIosDoesNotPassIsABuildError() {
    val r = emit(methods = listOf(method("apply", null, ArgModel("table", TypeMapping.DICTIONARY))))

    assertEquals(1, r.errors.size, "${r.errors}")
    val error = r.errors.single()
    assertTrue(error.contains("MethodSkipFixture.apply (godot: apply)"), error)
    assertTrue(error.contains("table: Map<String, Any?>"), error)
    assertTrue(error.contains("a call to this method from Godot would not run"), error)
    assertTrue(error.contains("Vector2i"), "the error lists what is supported: $error")
    assertTrue(error.contains("-P$ALLOW_IOS_SKIPS_OPTION=true"), error)
    assertEquals(emptyList(), r.warnings)
    assertTrue("\"apply\" ->" !in r.source, "a skipped method gets no dispatch branch")
  }

  @Test
  fun aValueReturningMethodWithAnArgIosDoesNotPassIsABuildError() {
    val r =
      emit(
        methods =
          listOf(
            method("total", TypeMapping.INT, ArgModel("values", TypeMapping.PACKED_INT32_ARRAY))
          )
      )

    assertEquals(1, r.errors.size, "${r.errors}")
    assertTrue(r.errors.single().contains("MethodSkipFixture.total (godot: total)"), r.errors[0])
  }

  @Test
  fun theOptInTurnsEachMethodSkipBackIntoOneWarning() {
    val r =
      emit(
        allowSkips = true,
        methods =
          listOf(
            method("apply", null, ArgModel("table", TypeMapping.DICTIONARY)),
            method("total", TypeMapping.INT, ArgModel("values", TypeMapping.ARRAY)),
          ),
      )

    assertEquals(emptyList(), r.errors)
    assertEquals(2, r.warnings.size, "${r.warnings}")
    assertTrue(r.warnings.all { it.contains("allowed by $ALLOW_IOS_SKIPS_OPTION") })
  }

  @Test
  fun eachOverloadIsReportedAndEveryUnsupportedArgumentNamed() {
    // Two overloads of one Kotlin name are two script methods (two Godot names): both are errors.
    val r =
      emit(
        methods =
          listOf(
            method("apply", null, ArgModel("table", TypeMapping.DICTIONARY)),
            MethodModel(
              kotlinName = "apply",
              godotName = "apply_all",
              returnType = null,
              args =
                listOf(
                  ArgModel("tables", TypeMapping.ARRAY),
                  ArgModel("count", TypeMapping.INT),
                  ArgModel("bytes", TypeMapping.PACKED_BYTE_ARRAY),
                ),
              kind = MethodKind.REGULAR,
            ),
          )
      )

    assertEquals(2, r.errors.size, "${r.errors}")
    assertTrue(r.errors[0].contains("argument table: Map<String, Any?> has a type"), r.errors[0])
    assertTrue(r.errors[1].contains("(godot: apply_all): arguments tables:"), r.errors[1])
    assertTrue(r.errors[1].contains(", bytes: ") && "count:" !in r.errors[1], r.errors[1])
    assertTrue(r.errors[1].contains("have types iOS does not pass"), r.errors[1])
  }

  @Test
  fun anAnyArgumentIsDispatched() {
    // `_set(property, value: Any?)`, `_drop_data`: the decode is loud for a type iOS does not
    // marshal (decodeIosCallArg), so the parameter is served instead of being an error.
    val r =
      emit(
        methods =
          listOf(
            method(
              "store",
              TypeMapping.BOOL,
              ArgModel("property", TypeMapping.STRING),
              ArgModel("value", TypeMapping.VARIANT),
            )
          )
      )

    assertEquals(emptyList(), r.errors)
    assertTrue(
      r.source.contains("\"store\" -> script.store(args[0] as String, args[1] as Any?)"),
      r.source,
    )
  }

  @Test
  fun aRidArgumentIsDispatched() {
    val r = emit(methods = listOf(method("useRid", null, ArgModel("rid", TypeMapping.RID))))

    assertEquals(emptyList(), r.errors)
    assertTrue(
      r.source.contains(
        "\"useRid\" -> { script.useRid(args[0] as net.multigesture.kanama.types.RID); true }"
      ),
      r.source,
    )
  }

  @Test
  fun valueTypeAndNodePathReturningVirtualsAreDispatched() {
    // Before N7 a value-returning virtual outside the old return set was warned and dropped: the
    // engine never called the override on iOS. A method with the same return was dispatched.
    val r =
      emit(
        virtuals =
          listOf(
            virtual("_get_transform", "getTransform", TypeMapping.TRANSFORM3D),
            virtual("_get_target", "getTarget", TypeMapping.NODE_PATH),
          ),
        methods = listOf(method("targetPath", TypeMapping.NODE_PATH)),
      )

    assertEquals(emptyList(), r.errors)
    assertEquals(emptyList(), r.warnings)
    assertTrue(r.source.contains("\"_get_transform\" -> script.getTransform()"), r.source)
    assertTrue(r.source.contains("\"_get_target\" -> script.getTarget()"), r.source)
    assertTrue(r.source.contains("\"targetPath\" -> script.targetPath()"), r.source)
  }

  @Test
  fun everyScriptTypeReturnsOnIos() {
    // The return-skip error is the guard for a FUTURE TypeMapping: today every one is marshalled
    // back to the engine (encodeIosReturn). A new script type fails here until iOS returns it.
    assertEquals(emptyList(), TypeMapping.entries.filter { it !in IOS_RETURN_TYPES })
  }
}

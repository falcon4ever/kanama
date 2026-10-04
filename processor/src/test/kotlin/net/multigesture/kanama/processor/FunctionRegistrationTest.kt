package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * Task 133 B — one annotation set, automatic function registration, typed input handlers.
 *
 * [FunctionRegistration.decide] is the whole rule the processor applies to each declared function
 * of a `@ScriptClass` / `@RegisterClass` (the KSP side only gathers the facts), so these tests pin
 * the rule directly; the emitter tests at the end pin the typed `InputEvent` handler arg on the
 * desktop, iOS and Web emitters. The demos and the example project compile the generated code.
 */
class FunctionRegistrationTest {

  private fun facts(
    name: String,
    visibility: FunctionRegistration.Visibility = FunctionRegistration.Visibility.PUBLIC,
    annotations: Set<String> = emptySet(),
    godotName: String? = null,
    isSuspend: Boolean = false,
    extension: Boolean = false,
    generic: Boolean = false,
    overridesNonScript: Boolean = false,
    virtualName: String? = null,
    hasVararg: Boolean = false,
    engineMethodOwner: String? = null,
  ) =
    FunctionRegistration.Facts(
      owner = "Hud",
      kotlinName = name,
      visibility = visibility,
      annotations = annotations + listOfNotNull(godotName?.let { "GodotName" }),
      godotNameOverride = godotName,
      isSuspend = isSuspend,
      hasExtensionReceiver = extension,
      hasTypeParameters = generic,
      overridesNonScriptMember = overridesNonScript,
      engineVirtual = virtualName,
      hasVararg = hasVararg,
      engineMethodOwner = engineMethodOwner,
    )

  private fun decide(f: FunctionRegistration.Facts) = FunctionRegistration.decide(f)

  private fun errorOf(f: FunctionRegistration.Facts): String =
    assertIs<FunctionRegistration.Decision.Error>(decide(f)).message

  // ---------- automatic registration and visibility ----------

  @Test
  fun everyPublicFunctionIsRegisteredUnderItsSnakeCaseName() {
    assertEquals(
      FunctionRegistration.Decision.Register("show_message"),
      decide(facts("showMessage")),
    )
    assertEquals(FunctionRegistration.Decision.Register("damage"), decide(facts("damage")))
  }

  @Test
  fun privateProtectedAndInternalFunctionsStayKotlinOnly() {
    for (v in
      listOf(
        FunctionRegistration.Visibility.PRIVATE,
        FunctionRegistration.Visibility.PROTECTED,
        FunctionRegistration.Visibility.INTERNAL,
      )) {
      assertIs<FunctionRegistration.Decision.KotlinOnly>(decide(facts("helper", v)), "$v")
    }
  }

  @Test
  fun aKanamaAnnotationOnANonPublicFunctionIsAnError() {
    val message = errorOf(facts("ready", FunctionRegistration.Visibility.PRIVATE, setOf("OnReady")))
    assertContains(message, "Hud.ready is private")
    assertContains(message, "@OnReady")
    assertContains(message, "Make it public")
    assertContains(
      errorOf(
        facts("onPressed", FunctionRegistration.Visibility.INTERNAL, godotName = "_on_pressed")
      ),
      "@GodotName",
    )
  }

  @Test
  fun functionsGodotCannotCallStayKotlinOnly() {
    assertEquals(
      FunctionRegistration.Decision.KotlinOnly("suspend function"),
      decide(facts("load", isSuspend = true)),
    )
    assertEquals(
      FunctionRegistration.Decision.KotlinOnly("extension function"),
      decide(facts("pretty", extension = true)),
    )
    assertEquals(
      FunctionRegistration.Decision.KotlinOnly("generic function"),
      decide(facts("find", generic = true)),
    )
    assertEquals(
      FunctionRegistration.Decision.KotlinOnly("override of a non-script member"),
      decide(facts("toString", overridesNonScript = true)),
    )
    // ...and an explicit opt-in on one is refused rather than ignored.
    assertContains(
      errorOf(facts("load", isSuspend = true, godotName = "load")),
      "suspend function, which Godot cannot call",
    )
    assertContains(errorOf(facts("load", annotations = setOf("Rpc"), isSuspend = true)), "@Rpc")
  }

  // ---------- custom Godot-side names ----------

  @Test
  fun godotNameKeepsAnEditorConnectionName() {
    assertEquals(
      FunctionRegistration.Decision.Register("_on_StartButton_pressed"),
      decide(facts("onStartButtonPressed", godotName = "_on_StartButton_pressed")),
    )
    // An explicit name may be an engine virtual's (Match3's `_input_event`): the opt-in is
    // explicit.
    assertEquals(
      FunctionRegistration.Decision.Register("_input_event"),
      decide(facts("inputEvent", godotName = "_input_event")),
    )
  }

  @Test
  fun godotNameOnAWiredFunctionIsAnError() {
    assertContains(
      errorOf(facts("pinged", annotations = setOf("Signal"), godotName = "ping")),
      "takes its own `name = ...`",
    )
    assertContains(
      errorOf(facts("tick", annotations = setOf("OnProcess"), godotName = "tick")),
      "@OnProcess",
    )
  }

  @Test
  fun lifecycleAndOtherRoleAnnotationsAreNotRegisteredAgain() {
    for (role in FunctionRegistration.ROLE_ANNOTATIONS) {
      assertEquals(
        FunctionRegistration.Decision.Role,
        decide(facts("x", annotations = setOf(role))),
      )
    }
    assertContains(
      errorOf(facts("x", annotations = setOf("OnReady", "Rpc"))),
      "@Rpc goes on a registered function",
    )
  }

  // ---------- F19: an unannotated engine-virtual name ----------

  @Test
  fun anUnannotatedEngineVirtualNameIsAnError() {
    val process = errorOf(facts("_process", virtualName = "_process"))
    assertContains(process, "named like the engine virtual `_process`")
    assertContains(process, "@OnProcess")
    val draw = errorOf(facts("_draw", virtualName = "_draw"))
    assertContains(draw, "@OverrideVirtual")
    // Private does not hide it: Godot would not call it either way.
    assertIs<FunctionRegistration.Decision.Error>(
      decide(facts("_ready", FunctionRegistration.Visibility.PRIVATE, virtualName = "_ready"))
    )
    // Annotated, it is wired.
    assertEquals(
      FunctionRegistration.Decision.Role,
      decide(facts("_draw", annotations = setOf("OverrideVirtual"), virtualName = "_draw")),
    )
  }

  @Test
  fun aCamelCaseSpellingOfAnEngineVirtualIsTheSameError() {
    val message =
      errorOf(facts("_getConfigurationWarnings", virtualName = "_get_configuration_warnings"))
    assertContains(message, "named like the engine virtual `_get_configuration_warnings`")
    assertContains(message, "@OverrideVirtual")
  }

  // ---------- engine method names, vararg, empty names ----------

  @Test
  fun aPublicFunctionOnAnEngineMethodNameIsAnErrorUnlessConfirmed() {
    val message = errorOf(facts("queueFree", engineMethodOwner = "Node"))
    assertContains(message, "would register as `queue_free`")
    assertContains(message, "engine method Node.queue_free")
    assertContains(message, "@GodotName(\"queue_free\")")
    assertContains(message, "`internal`")
    // Confirmed explicitly, it registers under that name.
    assertEquals(
      FunctionRegistration.Decision.Register("queue_free"),
      decide(facts("queueFree", godotName = "queue_free", engineMethodOwner = "Node")),
    )
    // Kotlin-only functions never collide.
    assertIs<FunctionRegistration.Decision.KotlinOnly>(
      decide(
        facts("queueFree", FunctionRegistration.Visibility.PRIVATE, engineMethodOwner = "Node")
      )
    )
  }

  @Test
  fun theEngineMethodTableKnowsTheAttachedHierarchy() {
    assertEquals("Node", EngineMethodTable.declaringClass("CharacterBody3D", "queue_free"))
    assertEquals("Object", EngineMethodTable.declaringClass("Node2D", "get_class"))
    assertEquals(
      "CharacterBody3D",
      EngineMethodTable.declaringClass("CharacterBody3D", "move_and_slide"),
    )
    assertNull(EngineMethodTable.declaringClass("Node", "move_and_slide"))
    assertNull(EngineMethodTable.declaringClass("Node", "show_message"))
    // Virtuals are the virtual table's business, not this one's.
    assertNull(EngineMethodTable.declaringClass("Node", "_ready"))
  }

  @Test
  fun aVarargPublicFunctionIsAnError() {
    assertContains(errorOf(facts("spawnAll", hasVararg = true)), "vararg parameter")
    assertIs<FunctionRegistration.Decision.KotlinOnly>(
      decide(facts("spawnAll", FunctionRegistration.Visibility.INTERNAL, hasVararg = true))
    )
  }

  @Test
  fun anEmptyGodotNameIsAnError() {
    assertContains(errorOf(facts("onPressed", godotName = "")), "@GodotName(\"\") is empty")
  }

  // ---------- duplicates ----------

  @Test
  fun twoFunctionsOnOneGodotNameAreAnError() {
    val errors =
      FunctionRegistration.duplicateNameErrors(
        "Hud",
        listOf("damage" to "damage", "damage" to "damage", "heal" to "heal", "_ready" to "ready"),
      )
    assertEquals(1, errors.size)
    assertContains(errors.single(), "the 2 overloads of damage")
    val renamed =
      FunctionRegistration.duplicateNameErrors(
        "Hud",
        listOf("_on_pressed" to "onPressed", "_on_pressed" to "onPressedAgain"),
      )
    assertContains(renamed.single(), "onPressed and onPressedAgain")
    assertContains(renamed.single(), "@GodotName")
  }

  @Test
  fun aRegisterClassAccessorCollisionIsAnErrorNamingBoth() {
    val errors =
      FunctionRegistration.duplicateNameErrors(
        "HelloKanama",
        listOf(
          "get_counter" to "getCounter",
          "get_counter" to "the generated accessor of @Export var counter",
          "set_counter" to "the generated accessor of @Export var counter",
        ),
      )
    assertContains(
      errors.single(),
      "getCounter and the generated accessor of @Export var counter all register as the Godot " +
        "method 'get_counter'",
    )
  }

  @Test
  fun anUnsupportedTypeNamesTheKotlinOnlyFix() {
    val message =
      FunctionRegistration.unsupportedTypeMessage(
        "Hud.helper",
        "parameter 'cb'",
        "kotlin.Function0<Unit>",
      )
    assertContains(message, "'kotlin.Function0<Unit>'")
    assertContains(message, "make it `internal` or `private`")
    // Task 133 C2: the limit is Kanama's; Godot carries every Variant type.
    assertContains(message, "Kanama does not yet pass")
    assertContains(message, "Color")
  }

  // ---------- the removed annotations ----------

  private val removed =
    listOf(
      "RegisterFunction",
      "Method",
      "ScriptProperty",
      "RegisterProperty",
      "ClassName",
      "ToolButton",
      "Ready",
      "EnterTree",
      "ExitTree",
      "Process",
      "PhysicsProcess",
      "Input",
      "UnhandledInput",
      "ShortcutInput",
      "UnhandledKeyInput",
    )

  private val canonical =
    listOf(
      "RegisterClass",
      "ScriptClass",
      "Tool",
      "GlobalClass",
      "Export",
      "ExportCategory",
      "ExportGroup",
      "ExportSubgroup",
      "ExportToolButton",
      "Signal",
      "Rpc",
      "GodotName",
      "OverrideVirtual",
      "OnReady",
      "OnEnterTree",
      "OnExitTree",
      "OnProcess",
      "OnPhysicsProcess",
      "OnInput",
      "OnUnhandledInput",
      "OnShortcutInput",
      "OnUnhandledKeyInput",
    )

  @Test
  fun everyRemovedAnnotationIsATombstoneTheCompilerRefusesNamingItsReplacement() {
    // A tombstone, not an absence: with the class gone, `@Process` resolves to java.lang.Process
    // and `@ScriptProperty` is only "Unresolved reference". `DeprecationLevel.ERROR` makes every
    // use a compile error whose message names the replacement.
    for (name in removed) {
      val deprecated =
        assertNotNull(
          Class.forName("net.multigesture.kanama.annotations.$name")
            .getAnnotation(Deprecated::class.java),
          name,
        )
      assertEquals(DeprecationLevel.ERROR, deprecated.level, name)
      assertContains(deprecated.message, "removed in Kanama 0.5")
      assertContains(deprecated.message, expectedReplacement.getValue(name))
    }
    for (name in canonical) {
      assertNull(
        Class.forName("net.multigesture.kanama.annotations.$name")
          .getAnnotation(Deprecated::class.java),
        name,
      )
    }
  }

  private val expectedReplacement =
    mapOf(
      "RegisterFunction" to "@GodotName",
      "Method" to "@GodotName",
      "ScriptProperty" to "@Export",
      "RegisterProperty" to "@Export",
      "ClassName" to "@GlobalClass",
      "ToolButton" to "@ExportToolButton",
      "Ready" to "@OnReady",
      "EnterTree" to "@OnEnterTree",
      "ExitTree" to "@OnExitTree",
      "Process" to "@OnProcess",
      "PhysicsProcess" to "@OnPhysicsProcess",
      "Input" to "@OnInput",
      "UnhandledInput" to "@OnUnhandledInput",
      "ShortcutInput" to "@OnShortcutInput",
      "UnhandledKeyInput" to "@OnUnhandledKeyInput",
    )

  @Test
  fun everyRemovedAnnotationIsABuildErrorNamingItsReplacement() {
    assertEquals(removed.toSet(), FunctionRegistration.REMOVED_ANNOTATIONS.keys)
    val expected =
      mapOf(
        "RegisterFunction" to "@GodotName",
        "Method" to "@GodotName",
        "ScriptProperty" to "@Export",
        "RegisterProperty" to "@Export",
        "ClassName" to "@GlobalClass",
        "ToolButton" to "@ExportToolButton",
        "Ready" to "@OnReady",
        "Process" to "@OnProcess",
        "Input" to "@OnInput",
        "UnhandledKeyInput" to "@OnUnhandledKeyInput",
      )
    for ((name, replacement) in expected) {
      val message = assertNotNull(FunctionRegistration.removedAnnotationError("Hud.x", name))
      assertContains(message, "@$name was removed in Kanama 0.5")
      assertContains(message, replacement)
      assertContains(message, "migrate_script_annotations.py")
    }
    assertNull(FunctionRegistration.removedAnnotationError("Hud.x", "OnReady"))
    assertNull(FunctionRegistration.removedAnnotationError("Hud.x", "Export"))
  }

  // ---------- typed input handlers ----------

  private val inputEvent = FunctionRegistration.INPUT_EVENT_FQN

  @Test
  fun anInputHandlerTakesOneInputEvent() {
    for (annotation in FunctionRegistration.INPUT_LIFECYCLE) {
      assertNull(
        FunctionRegistration.inputHandlerError(
          "P.input",
          annotation,
          listOf("event"),
          listOf(inputEvent),
        )
      )
    }
    assertNull(
      FunctionRegistration.inputHandlerError(
        "P.input",
        "OnInput",
        listOf("e"),
        listOf("$inputEvent?"),
      )
    )
  }

  @Test
  fun aGodotObjectInputHandlerIsAnErrorNamingTheFix() {
    val message =
      assertNotNull(
        FunctionRegistration.inputHandlerError(
          "Player.input",
          "OnInput",
          listOf("inputEvent"),
          listOf(FunctionRegistration.GODOT_OBJECT_FQN),
        )
      )
    assertContains(message, "Player.input: an @OnInput handler takes the typed event")
    assertContains(message, "`(inputEvent: InputEvent)`")
    assertContains(message, "InputEvent(inputEvent.handle)")
    assertContains(message, "migrate_script_annotations.py")
  }

  @Test
  fun anyOtherInputHandlerShapeIsAnError() {
    assertContains(
      assertNotNull(
        FunctionRegistration.inputHandlerError(
          "P.input",
          "OnUnhandledInput",
          listOf("event"),
          listOf("net.multigesture.kanama.api.InputEventKey"),
        )
      ),
      "exactly one `InputEvent`",
    )
    assertNotNull(
      FunctionRegistration.inputHandlerError("P.input", "OnInput", emptyList(), emptyList())
    )
    assertNotNull(
      FunctionRegistration.inputHandlerError(
        "P.input",
        "OnInput",
        listOf("a", "b"),
        listOf(inputEvent, "kotlin.Long"),
      )
    )
  }

  private fun inputModel(simpleName: String = "InputFixture") =
    ScriptModel(
      simpleName = simpleName,
      fqName = "net.multigesture.kanama.test.$simpleName",
      attachTo = "Node",
      isTool = false,
      isGlobalClass = false,
      properties = emptyList(),
      toolButtons = emptyList(),
      virtuals =
        listOf(
          VirtualModel(
            "_input",
            "input",
            "input",
            args = listOf(ArgModel("event", TypeMapping.OBJECT, inputEvent)),
          ),
          VirtualModel(
            "_unhandled_input",
            "unhandledInput",
            "unhandledInput",
            args = listOf(ArgModel("event", TypeMapping.OBJECT, inputEvent)),
          ),
          VirtualModel(
            "_process",
            "process",
            "process",
            args = listOf(ArgModel("delta", TypeMapping.FLOAT)),
          ),
        ),
      methods =
        listOf(
          // A public `fun process()` registered next to the `_process` virtual (task 133 B).
          MethodModel(
            kotlinName = "process",
            godotName = "process",
            returnType = null,
            args = emptyList(),
            kind = MethodKind.REGULAR,
          )
        ),
      signals = emptyList(),
    )

  @Test
  fun desktopHandsTheHandlerAnInputEvent() {
    val source = ScriptCodeEmitter(inputModel(), "InputFixtureScriptRegistrar").emit()
    assertTrue(
      source.contains("$inputEvent(net.multigesture.kanama.api.GodotHandle("),
      "the desktop dispatch wraps the event as InputEvent",
    )
    assertTrue(
      !source.contains(
        "net.multigesture.kanama.api.GodotObject(net.multigesture.kanama.api.GodotHandle(arg"
      )
    )
    // The `_process` virtual and the registered `process` method get distinct interned names.
    assertContains(source, "private var n__process_NameValue: Long = 0L")
    assertContains(source, "private var n_process_NameValue: Long = 0L")
  }

  @Test
  fun iosHandsTheHandlerAnInputEvent() {
    val errors = mutableListOf<String>()
    val source =
      IosScriptCodeEmitter(
          listOf(IosScriptInput(inputModel(), "res://InputFixture.kt")),
          warn = {},
          error = { errors += it },
        )
        .registrySource()
    assertEquals(emptyList(), errors)
    assertContains(
      source,
      "\"_input\" -> { script.input($inputEvent(net.multigesture.kanama.api.GodotHandle(",
    )
    assertContains(
      source,
      "\"_unhandled_input\" -> { script.unhandledInput($inputEvent(net.multigesture.kanama.api.GodotHandle(",
    )
  }

  @Test
  fun webHandsTheHandlerAnInputEvent() {
    val model = inputModel().copy(methods = emptyList())
    val source =
      WebScriptCodeEmitter(listOf(WebScriptInput(model, "res://InputFixture.kt"))).registrySource()
    assertContains(
      source,
      "(script as InputFixture).input($inputEvent(GodotHandle.fromBackendToken(eventHandle.toLong())))",
    )
    assertContains(
      source,
      "(script as InputFixture).unhandledInput($inputEvent(GodotHandle.fromBackendToken(eventHandle.toLong())))",
    )
  }

  @Test
  fun methodHelpersRenameTheirReceiverWhenAParameterTakesItsName() {
    val model =
      inputModel()
        .copy(
          virtuals = emptyList(),
          methods =
            listOf(
              MethodModel(
                kotlinName = "aim",
                godotName = "aim",
                returnType = null,
                args =
                  listOf(
                    ArgModel("target", TypeMapping.OBJECT, FunctionRegistration.GODOT_OBJECT_FQN),
                    ArgModel("instance", TypeMapping.INT),
                  ),
                kind = MethodKind.REGULAR,
              )
            ),
        )
    val source = ScriptCodeEmitter(model, "InputFixtureScriptRegistrar").emit()
    assertContains(
      source,
      "fun aim(kanamaInstance: InputFixture, target: net.multigesture.kanama.api.GodotObject, instance: Long) {",
    )
    assertContains(
      source,
      "fun aim(kanamaTarget: net.multigesture.kanama.api.GodotObject, target: net.multigesture.kanama.api.GodotObject, instance: Long): Boolean {",
    )
  }
}

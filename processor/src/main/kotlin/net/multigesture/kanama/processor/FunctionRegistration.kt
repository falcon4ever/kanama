package net.multigesture.kanama.processor

/**
 * Task 133 B — which functions of a `@ScriptClass` / `@RegisterClass` reach Godot, and how.
 *
 * Kanama 0.5 keeps one GDScript-shaped annotation per concept and registers every public function
 * automatically, as GDScript exposes every `func`. The rules, in the order they apply:
 * 1. A **removed annotation** (`@RegisterFunction`, `@Ready`, `@ScriptProperty`, …) is a build
 *    error naming its replacement ([REMOVED_ANNOTATIONS]); Kotlin also fails to resolve it.
 * 2. A function with a **role annotation** ([ROLE_ANNOTATIONS]: a lifecycle `@On…`,
 *    `@OverrideVirtual`, `@Signal`, `@ExportToolButton`) is wired by that annotation and is not
 *    registered a second time.
 * 3. A function named like an **engine virtual** of the attached class (`fun _process(delta:
 *    Double)`) without a role annotation is a build error: Godot would never call it as the virtual
 *    (task 130 F19, Laurence's decision 3).
 * 4. Every other **public** function is registered under its snake_case name, or under
 *    `@GodotName("...")`. `private`, `protected` and `internal` functions stay Kotlin-only, and a
 *    Kanama annotation on one is a build error (it was silently ignored before).
 * 5. Functions Godot cannot call by construction stay Kotlin-only even when public: `suspend`
 *    functions, extension functions, generic functions, and an `override` of a member that does not
 *    come from a script class (`toString`, `equals`, an interface method). An explicit `@GodotName`
 *    or `@Rpc` on one is a build error.
 *
 * A registered function whose parameter or return type Godot cannot carry is a build error that
 * names the type and the fix (`internal`/`private`), raised where the method model is built.
 */
internal object FunctionRegistration {

  /** Lifecycle annotation -> the engine virtual it wires. */
  val LIFECYCLE_VIRTUALS: Map<String, String> =
    linkedMapOf(
      "OnReady" to "_ready",
      "OnEnterTree" to "_enter_tree",
      "OnExitTree" to "_exit_tree",
      "OnProcess" to "_process",
      "OnPhysicsProcess" to "_physics_process",
      "OnInput" to "_input",
      "OnUnhandledInput" to "_unhandled_input",
      "OnShortcutInput" to "_shortcut_input",
      "OnUnhandledKeyInput" to "_unhandled_key_input",
    )

  /** The lifecycle annotations whose handler takes the event (`fun input(event: InputEvent)`). */
  val INPUT_LIFECYCLE: Set<String> =
    setOf("OnInput", "OnUnhandledInput", "OnShortcutInput", "OnUnhandledKeyInput")

  /** Annotations that wire a function some other way than as a registered method. */
  val ROLE_ANNOTATIONS: Set<String> =
    LIFECYCLE_VIRTUALS.keys + setOf("OverrideVirtual", "Signal", "ExportToolButton")

  /** Every Kanama function annotation (a non-public function carrying one is a build error). */
  val FUNCTION_ANNOTATIONS: Set<String> = ROLE_ANNOTATIONS + setOf("Rpc", "GodotName")

  const val INPUT_EVENT_FQN = "net.multigesture.kanama.api.InputEvent"
  const val GODOT_OBJECT_FQN = "net.multigesture.kanama.api.GodotObject"

  /** Removed annotation (simple name) -> what to write instead. */
  val REMOVED_ANNOTATIONS: Map<String, String> =
    linkedMapOf(
      "RegisterFunction" to
        "public functions are registered automatically; delete it, or write " +
          "@GodotName(\"...\") when Godot must find the function under another name",
      "Method" to
        "public functions are registered automatically; delete it, or write " +
          "@GodotName(\"...\") when Godot must find the function under another name",
      "ScriptProperty" to "use @Export (same parameters)",
      "RegisterProperty" to "use @Export (same parameters)",
      "ClassName" to "use @GlobalClass",
      "ToolButton" to "use @ExportToolButton (same parameters)",
      "Ready" to "use @OnReady",
      "EnterTree" to "use @OnEnterTree",
      "ExitTree" to "use @OnExitTree",
      "Process" to "use @OnProcess",
      "PhysicsProcess" to "use @OnPhysicsProcess",
      "Input" to "use @OnInput",
      "UnhandledInput" to "use @OnUnhandledInput",
      "ShortcutInput" to "use @OnShortcutInput",
      "UnhandledKeyInput" to "use @OnUnhandledKeyInput",
    )

  /** The build error for a removed annotation, or null for any other name. */
  fun removedAnnotationError(where: String, simpleName: String): String? =
    REMOVED_ANNOTATIONS[simpleName]?.let {
      "$where: @$simpleName was removed in Kanama 0.5 — $it. " +
        "scripts/migrate_script_annotations.py rewrites a source tree."
    }

  enum class Visibility {
    PUBLIC,
    PROTECTED,
    INTERNAL,
    PRIVATE,
  }

  /** What the processor knows about one declared function, independent of KSP. */
  data class Facts(
    val owner: String,
    val kotlinName: String,
    val visibility: Visibility,
    val annotations: Set<String>,
    /** `@GodotName("...")` value, or null. */
    val godotNameOverride: String? = null,
    val isSuspend: Boolean = false,
    val hasExtensionReceiver: Boolean = false,
    val hasTypeParameters: Boolean = false,
    /** An `override` of a member declared outside any script / registered class. */
    val overridesNonScriptMember: Boolean = false,
    /** The function's own name is an engine virtual of the attached class (`_process`, `_draw`). */
    val nameIsEngineVirtual: Boolean = false,
  )

  sealed interface Decision {
    /** Registered as a method under [godotName]. */
    data class Register(val godotName: String) : Decision

    /** Wired by a role annotation (lifecycle, virtual, signal, tool button). */
    data object Role : Decision

    /** Not visible to Godot. */
    data class KotlinOnly(val reason: String) : Decision

    data class Error(val message: String) : Decision
  }

  fun decide(f: Facts): Decision {
    val where = "${f.owner}.${f.kotlinName}"
    val roles = f.annotations intersect ROLE_ANNOTATIONS
    val kanama = f.annotations intersect FUNCTION_ANNOTATIONS
    if (f.visibility != Visibility.PUBLIC && kanama.isNotEmpty()) {
      val word = f.visibility.name.lowercase()
      return Decision.Error(
        "$where is $word, but ${kanama.sorted().joinToString(", ") { "@$it" }} only works on a " +
          "public function (Godot calls it through the generated registrar). Make it public."
      )
    }
    if (roles.isNotEmpty()) {
      if ("GodotName" in f.annotations) {
        return Decision.Error(
          "$where: @GodotName names a registered function, but " +
            "${roles.sorted().joinToString(", ") { "@$it" }} wires this one to Godot already " +
            "(a signal or tool button takes its own `name = ...`)."
        )
      }
      if ("Rpc" in f.annotations) {
        return Decision.Error(
          "$where: @Rpc goes on a registered function (a plain public function), not on one " +
            "wired by ${roles.sorted().joinToString(", ") { "@$it" }}."
        )
      }
      return Decision.Role
    }
    if (f.nameIsEngineVirtual && f.godotNameOverride == null) {
      val annotation = LIFECYCLE_VIRTUALS.entries.firstOrNull { it.value == f.kotlinName }?.key
      val fix =
        if (annotation != null) {
          "annotate it @$annotation (any function name works: `@$annotation fun " +
            "${lifecycleExampleName(annotation)}`)"
        } else {
          "annotate it @OverrideVirtual to override the virtual"
        }
      return Decision.Error(
        "$where is named like the engine virtual `${f.kotlinName}`, which Godot would never call " +
          "on it: $fix, or rename it."
      )
    }
    if (f.visibility != Visibility.PUBLIC) {
      return Decision.KotlinOnly("${f.visibility.name.lowercase()} function")
    }
    val kotlinOnlyReason =
      when {
        f.isSuspend -> "suspend function"
        f.hasExtensionReceiver -> "extension function"
        f.hasTypeParameters -> "generic function"
        f.overridesNonScriptMember -> "override of a non-script member"
        else -> null
      }
    if (kotlinOnlyReason != null) {
      val explicit = f.annotations intersect setOf("GodotName", "Rpc")
      if (explicit.isNotEmpty()) {
        return Decision.Error(
          "$where is a $kotlinOnlyReason, which Godot cannot call, so " +
            "${explicit.sorted().joinToString(", ") { "@$it" }} cannot register it."
        )
      }
      return Decision.KotlinOnly(kotlinOnlyReason)
    }
    return Decision.Register(f.godotNameOverride ?: KanamaProcessor.camelToSnake(f.kotlinName))
  }

  private fun lifecycleExampleName(annotation: String): String =
    when (annotation) {
      "OnProcess" -> "process(delta: Double)"
      "OnPhysicsProcess" -> "physicsProcess(delta: Double)"
      "OnInput" -> "input(event: InputEvent)"
      "OnUnhandledInput" -> "unhandledInput(event: InputEvent)"
      "OnShortcutInput" -> "shortcutInput(event: InputEvent)"
      "OnUnhandledKeyInput" -> "unhandledKeyInput(event: InputEvent)"
      "OnEnterTree" -> "enterTree()"
      "OnExitTree" -> "exitTree()"
      else -> "ready()"
    }

  /**
   * The build error for an input handler's parameter list ([parameterTypes] are the resolved
   * qualified names; `?` marks a nullable one), or null when it is `(event: InputEvent)`.
   */
  fun inputHandlerError(
    where: String,
    annotation: String,
    parameterNames: List<String>,
    parameterTypes: List<String?>,
  ): String? {
    val single = parameterTypes.singleOrNull()?.removeSuffix("?")
    if (single == INPUT_EVENT_FQN) return null
    val name = parameterNames.singleOrNull() ?: "event"
    if (single == GODOT_OBJECT_FQN) {
      return "$where: an @$annotation handler takes the typed event since Kanama 0.5: declare " +
        "`($name: InputEvent)` (import net.multigesture.kanama.api.InputEvent), not GodotObject, " +
        "and drop any `InputEvent($name.handle)` rewrap; " +
        "scripts/migrate_script_annotations.py does both."
    }
    val declared = parameterTypes.joinToString(", ") { it?.substringAfterLast('.') ?: "?" }
    return "$where: an @$annotation handler takes exactly one `InputEvent` (Godot passes the " +
      "event as one), not ($declared). Cast inside: `InputEventKey.from(event)`."
  }

  /**
   * Godot-name collisions among a class's registered methods and wired virtuals: each message names
   * the Kotlin functions that share one Godot name.
   */
  fun duplicateNameErrors(owner: String, entries: List<Pair<String, String>>): List<String> =
    entries
      .groupBy({ it.first }, { it.second })
      .filter { (_, kotlinNames) -> kotlinNames.size > 1 }
      .map { (godotName, kotlinNames) ->
        val who =
          if (kotlinNames.distinct().size == 1)
            "the ${kotlinNames.size} overloads of ${kotlinNames[0]}"
          else kotlinNames.joinToString(" and ")
        "$owner: $who all register as the Godot " +
          "method '$godotName' (Godot has no overloads). Make all but one internal or private, " +
          "or give one @GodotName(\"...\")."
      }

  /** The message for a registered function whose type Godot cannot carry. */
  fun unsupportedTypeMessage(where: String, slot: String, typeName: String?): String =
    "$where: $slot has type '$typeName', which Godot cannot pass to or from a registered " +
      "function. Every public function of a script class is registered with Godot (Kanama 0.5); " +
      "make it `internal` or `private` to keep it Kotlin-only, or use a supported type."
}

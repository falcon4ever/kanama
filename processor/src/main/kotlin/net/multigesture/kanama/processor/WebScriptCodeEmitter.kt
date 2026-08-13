package net.multigesture.kanama.processor

/** One Web script model paired with its Godot resource path. */
internal data class WebScriptInput(val model: ScriptModel, val resourcePath: String)

// ---------- Task 80 slice 1: the generator declares its own degradations ----------

/**
 * How a declared script member actually reaches Kotlin on the Web backend.
 *
 * The Web emitter models a hand-maintained set of supported shapes; anything outside it degrades,
 * historically in silence. Every manifest entry carries one of these so the degradation is a number
 * in `KanamaWebProtocol.generated.json` and a build-time report line instead of a runtime surprise.
 */
internal enum class WebDispatchStatus(val json: String) {
  /** Dispatches with its declared payload intact. */
  TYPED("typed"),
  /** Emitted, but calling it throws at runtime (the `unsupportedGameplayMethod` stub). */
  UNSUPPORTED("unsupported"),
  /** Dispatches, but part of the declared payload never reaches Kotlin. */
  ARGUMENT_DROPPED("argument-dropped"),
  /** No crossing is emitted at all, so the member can never run on Web. */
  NOT_EMITTED("not-emitted"),
}

/** A member's Web dispatch verdict: the status plus a short machine-readable reason. */
internal data class WebDispatch(val status: WebDispatchStatus, val reason: String? = null) {
  val isTyped: Boolean
    get() = status == WebDispatchStatus.TYPED
}

/**
 * The proxy dispatch arm a registered `@ScriptFunction` takes, chosen by parameter shape.
 *
 * This enum IS the arm choice: [WebScriptCodeEmitter.methodArm] decides it once, the GDScript
 * emitter switches on it, and the protocol manifest reports it. Adding a bridge entry point means
 * adding an arm here and an emitter branch for it — the shape table cannot drift from what the
 * manifest claims, because there is only one table.
 */
internal enum class WebMethodArm(val dispatch: WebDispatch) {
  /** `_draw()` — crossed by the `_draw` virtual dispatcher, not by a per-method function. */
  DRAW_VIRTUAL(WebDispatch(WebDispatchStatus.TYPED)),
  /** A `_draw` overload the draw dispatcher does not accept: no crossing is emitted for it. */
  DRAW_SHAPE_MISMATCH(
    WebDispatch(
      WebDispatchStatus.NOT_EMITTED,
      "the _draw crossing dispatches only the zero-argument void form",
    )
  ),
  /** No arguments, no return: `callNoArgs`. */
  NO_ARGS(WebDispatch(WebDispatchStatus.TYPED)),
  /** `(INT) -> INT`: `callInt`. */
  INT_RET_INT(WebDispatch(WebDispatchStatus.TYPED)),
  /** `(VECTOR2I) -> void`: `callVector2i`. */
  VECTOR2I_VOID(WebDispatch(WebDispatchStatus.TYPED)),
  /** `(INT) -> void`: `callLongVoid`. */
  INT_VOID(WebDispatch(WebDispatchStatus.TYPED)),
  /** `(STRING) -> void`: `callString`. */
  STRING_VOID(WebDispatch(WebDispatchStatus.TYPED)),
  /** `(OBJECT) -> void`: `callObject`. */
  OBJECT_VOID(WebDispatch(WebDispatchStatus.TYPED)),
  /** `(OBJECT, OBJECT, INT) -> void`: `callObjectObjectLong`. */
  OBJECT_OBJECT_INT_VOID(WebDispatch(WebDispatchStatus.TYPED)),
  /**
   * Any all-numeric argument list flattened into the `callDoubles` slots (task 80 slice 2):
   * `(FLOAT)`, `(BOOL)`, `(VECTOR2)`, `(VECTOR3)`, `(VECTOR3, VECTOR3)`, `(VECTOR2, BOOL)`, `(INT,
   * FLOAT)`, … — one crossing for every shape whose arguments are scalar components.
   */
  NUMERIC_VOID(WebDispatch(WebDispatchStatus.TYPED)),
  /**
   * A zero-argument value-returning method: `callPacked` returns the value packed into one string
   * and the proxy parses it per the declared return type (task 80 slice 2).
   */
  PACKED_RETURN(WebDispatch(WebDispatchStatus.TYPED)),
  /**
   * A MIXED-channel argument list, packed into one string over the existing `callString` crossing
   * (task 80 slice 3): `(STRING, OBJECT)`, `(INT, OBJECT)`, `(STRING, INT)`, … — anything whose
   * arguments are text, whole numbers, booleans, or object handles. Object arguments ride as their
   * handle id, which is why this arm reaches shapes [NUMERIC_VOID] and [STRING_VOID] cannot.
   */
  PACKED_ARGS(WebDispatch(WebDispatchStatus.TYPED)),
  /** No arm matches: the proxy emits a stub that throws through `unsupportedGameplayMethod`. */
  NONE(WebDispatch(WebDispatchStatus.UNSUPPORTED));

  val isTyped: Boolean
    get() = dispatch.isTyped
}

/**
 * The proxy push arm an `@ScriptProperty` takes at hydration, keyed the same way the emitted
 * `_kanama_ensure_created` body is. [WebPropertyArm.NONE] is the historical `else -> Unit` silent
 * drop; task 64's `unsupportedWebPropertyErrors` already fails the build for it, so a NONE entry in
 * the manifest means that guard has a hole.
 */
internal enum class WebPropertyArm(val dispatch: WebDispatch) {
  STRING(WebDispatch(WebDispatchStatus.TYPED)),
  NODE_PATH(WebDispatch(WebDispatchStatus.TYPED)),
  INT(WebDispatch(WebDispatchStatus.TYPED)),
  FLOAT(WebDispatch(WebDispatchStatus.TYPED)),
  BOOL(WebDispatch(WebDispatchStatus.TYPED)),
  VECTOR2(WebDispatch(WebDispatchStatus.TYPED)),
  VECTOR3(WebDispatch(WebDispatchStatus.TYPED)),
  VECTOR2I(WebDispatch(WebDispatchStatus.TYPED)),
  OBJECT(WebDispatch(WebDispatchStatus.TYPED)),
  STRING_ARRAY(WebDispatch(WebDispatchStatus.TYPED)),
  OBJECT_ARRAY(WebDispatch(WebDispatchStatus.TYPED)),
  NONE(WebDispatch(WebDispatchStatus.NOT_EMITTED));

  val isTyped: Boolean
    get() = dispatch.isTyped
}

/** One non-`typed` manifest entry, as the build-time report prints it. */
internal data class WebDegradation(
  val scriptName: String,
  val memberName: String,
  /** `method`, `signal`, `property`, or `virtual` — the manifest section the entry sits in. */
  val kind: String,
  val dispatch: WebDispatch,
) {
  override fun toString(): String =
    "$scriptName.$memberName ($kind): ${dispatch.reason ?: dispatch.status.json}"
}

/** Static Kotlin/Wasm registry, protocol manifest, and Godot proxy emitter for Task 57. */
internal class WebScriptCodeEmitter(inputs: List<WebScriptInput>) {
  private val scripts = inputs.sortedWith(compareBy({ it.resourcePath }, { it.model.fqName }))

  companion object {
    /**
     * Runtime bridge contract version. 18 (task 82) replaces the per-script `kanamaWebFrame`
     * crossing with the ownerless `kanamaWebPumpFrameScheduler(delta)`, which the bridge drives
     * from the `_process` dispatch every proxy emits — so the coroutine frame scheduler advances
     * once per engine frame in every demo instead of only the four whose "Main" handle the bridge
     * happened to name.
     */
    const val PROTOCOL_VERSION = 18

    /**
     * Shape version of `KanamaWebProtocol.generated.json` itself — independent of
     * [PROTOCOL_VERSION], which versions the runtime bridge contract. 2 adds the per-entry
     * `dispatch` / `dispatchReason` fields (task 80); no bridge entry point changed, so the
     * protocol version deliberately did not move.
     */
    const val PROTOCOL_SCHEMA_VERSION = 2

    /**
     * Godot virtuals the emitted proxy actually crosses into Kotlin. Keep in sync with the
     * `append*Dispatcher()` functions below — a virtual outside this set is emitted into the script
     * model, compiles cleanly, and is then never dispatched.
     */
    val DISPATCHED_VIRTUALS =
      linkedSetOf(
        "_enter_tree",
        "_ready",
        "_process",
        "_physics_process",
        "_draw",
        "_exit_tree",
        "_input",
        "_unhandled_input",
      )

    /** True when KSP is running for the Web (Kotlin/Wasm) target. */
    fun isWebTarget(options: Map<String, String>): Boolean = options["kanamaRuntimeTarget"] == "web"

    // ---- Task 80: one arm table per member kind, read by the emitter AND the manifest ----

    /** The GDScript names of the three signal-delivery helpers every proxy emits. */
    internal const val SIGNAL_DISPATCH_ZERO = "_kanama_web_signal_dispatch0"
    internal const val SIGNAL_DISPATCH_ONE = "_kanama_web_signal_dispatch1"
    internal const val SIGNAL_DISPATCH_OBJECT = "_kanama_web_signal_dispatch_object"
    /** Packs one emitted scalar payload for [SIGNAL_DISPATCH_ONE] (task 80 slice 2). */
    internal const val SIGNAL_PACK_ARG = "_kanama_web_pack_signal_arg"

    /**
     * The dispatch arm the proxy emits for a registered `@ScriptFunction`. The emitter's method
     * loop switches on this and nothing else, so the manifest's `dispatch` value is the arm that
     * was actually taken rather than a second reading of the same shapes.
     */
    fun methodArm(method: MethodModel): WebMethodArm =
      when {
        method.godotName == "_draw" ->
          if (method.args.isEmpty() && method.returnType == null) WebMethodArm.DRAW_VIRTUAL
          else WebMethodArm.DRAW_SHAPE_MISMATCH
        method.args.isEmpty() && method.returnType == null -> WebMethodArm.NO_ARGS
        method.returnType == TypeMapping.INT &&
          method.args.size == 1 &&
          method.args.single().type == TypeMapping.INT -> WebMethodArm.INT_RET_INT
        method.returnType == null &&
          method.args.size == 1 &&
          method.args.single().type == TypeMapping.VECTOR2I -> WebMethodArm.VECTOR2I_VOID
        method.returnType == null &&
          method.args.size == 1 &&
          method.args.single().type == TypeMapping.INT -> WebMethodArm.INT_VOID
        method.returnType == null &&
          method.args.size == 1 &&
          method.args.single().type == TypeMapping.STRING -> WebMethodArm.STRING_VOID
        method.returnType == null &&
          method.args.size == 1 &&
          method.args.single().type == TypeMapping.OBJECT &&
          method.args.single().objectWrapperFqName != null -> WebMethodArm.OBJECT_VOID
        method.returnType == null &&
          method.args.size == 3 &&
          method.args[0].type == TypeMapping.OBJECT &&
          method.args[1].type == TypeMapping.OBJECT &&
          method.args[2].type == TypeMapping.INT -> WebMethodArm.OBJECT_OBJECT_INT_VOID
        method.returnType == null && numericArgSlots(method.args) != null ->
          WebMethodArm.NUMERIC_VOID
        method.args.isEmpty() && isPackedReturn(method.returnType) -> WebMethodArm.PACKED_RETURN
        method.returnType == null && isPackedArgList(method.args) -> WebMethodArm.PACKED_ARGS
        else -> WebMethodArm.NONE
      }

    /**
     * Numeric slots the `callDoubles` crossing carries.
     *
     * Six is exactly one `(VECTOR3, VECTOR3)` pair, the widest all-numeric registered-method shape
     * in the corpus. A wider one has no arm and says so in the census instead of degrading in
     * silence — which is the whole point of the arm table.
     */
    const val NUMERIC_ARG_SLOTS = 6

    /**
     * Scalar component count for a type the numeric crossing carries, or null for a type that has
     * to ride a different channel (strings, objects, arrays).
     */
    private fun numericComponents(type: TypeMapping): Int? =
      when (type) {
        TypeMapping.FLOAT,
        TypeMapping.INT,
        TypeMapping.BOOL -> 1
        TypeMapping.VECTOR2,
        TypeMapping.VECTOR2I -> 2
        TypeMapping.VECTOR3 -> 3
        else -> null
      }

    /**
     * Total numeric slots this argument list occupies, or null when it cannot ride the numeric
     * crossing (a non-numeric argument, no arguments at all, or more than [NUMERIC_ARG_SLOTS]).
     */
    fun numericArgSlots(args: List<ArgModel>): Int? {
      if (args.isEmpty()) return null
      var total = 0
      args.forEach { arg -> total += numericComponents(arg.type) ?: return null }
      return if (total <= NUMERIC_ARG_SLOTS) total else null
    }

    /**
     * Return types the packed-string return channel encodes. Same spelling as the property packer
     * (`getPackedProperty`), so the proxy parses a returned value exactly the way it already parses
     * a pulled property — one transport, one encoding, no second table.
     */
    fun isPackedReturn(type: TypeMapping?): Boolean =
      when (type) {
        TypeMapping.STRING,
        TypeMapping.NODE_PATH,
        TypeMapping.INT,
        TypeMapping.FLOAT,
        TypeMapping.BOOL,
        TypeMapping.VECTOR2,
        TypeMapping.VECTOR2I,
        TypeMapping.VECTOR3,
        TypeMapping.QUATERNION,
        TypeMapping.BASIS -> true
        else -> false
      }

    /**
     * The GDScript expression per numeric slot, in slot order. `float(...)` normalizes bools and
     * ints so the crossing sees one numeric type regardless of the declared parameter type.
     */
    fun numericArgSlotExpressions(args: List<ArgModel>): List<String> = buildList {
      args.forEach { arg ->
        when (arg.type) {
          TypeMapping.FLOAT -> add(arg.name)
          TypeMapping.INT,
          TypeMapping.BOOL -> add("float(${arg.name})")
          TypeMapping.VECTOR2,
          TypeMapping.VECTOR2I -> {
            add("float(${arg.name}.x)")
            add("float(${arg.name}.y)")
          }
          TypeMapping.VECTOR3 -> {
            add(arg.name + ".x")
            add(arg.name + ".y")
            add(arg.name + ".z")
          }
          else -> error("no numeric slot layout for ${arg.type.name}")
        }
      }
    }

    /**
     * The Kotlin expression rebuilding each declared argument from the numeric slot parameters
     * `a0`..`a5`. Mirror of [numericArgSlotExpressions] — the two walk the same argument list in
     * the same order, so a slot can never be read as a different component than it was written.
     */
    fun numericArgKotlinExpressions(args: List<ArgModel>): List<String> = buildList {
      var slot = 0
      fun next(): String = "a${slot++}"
      args.forEach { arg ->
        when (arg.type) {
          TypeMapping.FLOAT -> add(next())
          TypeMapping.INT -> add("${next()}.toLong()")
          TypeMapping.BOOL -> add("${next()} != 0.0")
          TypeMapping.VECTOR2 -> add("net.multigesture.kanama.types.Vector2(${next()}, ${next()})")
          TypeMapping.VECTOR2I -> add("Vector2i(${next()}.toInt(), ${next()}.toInt())")
          TypeMapping.VECTOR3 ->
            add("net.multigesture.kanama.types.Vector3(${next()}, ${next()}, ${next()})")
          else -> error("no numeric slot layout for ${arg.type.name}")
        }
      }
    }

    /**
     * The Kotlin expression packing a returned value of [type] into the transport string. Same
     * encoding as `getPackedProperty`; QUATERNION adds `w` and BASIS packs its three COLUMNS in
     * x/y/z order, which is exactly the argument order of GDScript's `Basis(x, y, z)`.
     */
    fun packedReturnExpression(access: String, type: TypeMapping): String =
      when (type) {
        TypeMapping.STRING -> access
        TypeMapping.NODE_PATH -> "$access.path"
        TypeMapping.INT,
        TypeMapping.FLOAT -> "$access.toString()"
        TypeMapping.BOOL -> "if ($access) \"1\" else \"0\""
        TypeMapping.VECTOR2,
        TypeMapping.VECTOR2I -> "$access.let { \"\${it.x},\${it.y}\" }"
        TypeMapping.VECTOR3 -> "$access.let { \"\${it.x},\${it.y},\${it.z}\" }"
        TypeMapping.QUATERNION -> "$access.let { \"\${it.x},\${it.y},\${it.z},\${it.w}\" }"
        TypeMapping.BASIS ->
          "$access.let { " +
            "\"\${it.x.x},\${it.x.y},\${it.x.z},\${it.y.x},\${it.y.y},\${it.y.z}," +
            "\${it.z.x},\${it.z.y},\${it.z.z}\" }"
        else -> error("no packed return encoding for ${type.name}")
      }

    // ---- Task 80 slice 3: the mixed-channel packed argument list ----

    /** The GDScript helper that %-escapes one text argument for the packed list. */
    internal const val PACKED_ARG_PACK_TEXT = "_kanama_web_pack_text"
    /** The GDScript helper that turns one object argument into a bridge handle id. */
    internal const val PACKED_ARG_PACK_OBJECT = "_kanama_web_pack_object"
    /** The proxy-local array of handles the call allocated, released right after the crossing. */
    private const val PACKED_ARG_TRANSIENT = "_kanama_packed_transient"
    /** The proxy-local packed parts being assembled. */
    private const val PACKED_ARG_PARTS = "_kanama_packed_args"
    /** The generated-Kotlin name for the decoded parts of the packed argument list. */
    private const val PACKED_ARG_KOTLIN_PARTS = "packedArgs"

    /**
     * Whether [arg] can ride the packed argument list.
     *
     * Deliberately NOT floats or float-backed vectors: the packed list is decimal TEXT produced by
     * GDScript's `str()`, which rounds a double to 14 significant digits, so a float carried here
     * would arrive slightly wrong — the silent-wrong-VALUE class this whole task exists to kill.
     * Whole numbers, booleans, text, and object handle ids all round-trip exactly. An all-numeric
     * shape has the exact [WebMethodArm.NUMERIC_VOID] crossing anyway; what is left over is a float
     * MIXED with text or an object, and that shape has no arm and fails the build rather than
     * losing precision in silence.
     */
    private fun isPackedArgType(arg: ArgModel): Boolean =
      when (arg.type) {
        TypeMapping.STRING,
        TypeMapping.NODE_PATH,
        TypeMapping.INT,
        TypeMapping.BOOL -> true
        TypeMapping.OBJECT -> arg.objectWrapperFqName != null
        else -> false
      }

    /** Whether this whole argument list rides the packed crossing. */
    fun isPackedArgList(args: List<ArgModel>): Boolean =
      args.isNotEmpty() && args.all(::isPackedArgType)

    /**
     * The GDScript expression for one packed part, in argument order. Objects resolve to a handle
     * id — a Kanama-scripted object's own script handle when it has one (so the Kotlin side can
     * reach its script instance), otherwise a transient handle appended to [PACKED_ARG_TRANSIENT]
     * and released after the crossing.
     */
    fun packedArgGdExpressions(args: List<ArgModel>): List<String> =
      args.map { arg ->
        when (arg.type) {
          TypeMapping.STRING -> "$PACKED_ARG_PACK_TEXT(${arg.name})"
          TypeMapping.NODE_PATH -> "$PACKED_ARG_PACK_TEXT(String(${arg.name}))"
          TypeMapping.INT -> "str(${arg.name})"
          TypeMapping.BOOL -> "(\"1\" if ${arg.name} else \"0\")"
          TypeMapping.OBJECT -> "str($PACKED_ARG_PACK_OBJECT(${arg.name}, $PACKED_ARG_TRANSIENT))"
          else -> error("no packed argument encoding for ${arg.type.name}")
        }
      }

    /**
     * The Kotlin expression rebuilding each declared argument from the packed parts. Mirror of
     * [packedArgGdExpressions] — the two walk the same argument list in the same order, so a part
     * can never be read as a different argument than it was written.
     */
    fun packedArgKotlinExpressions(args: List<ArgModel>): List<String> =
      args.mapIndexed { index, arg ->
        val part = "$PACKED_ARG_KOTLIN_PARTS[$index]"
        val text = "$part.replace(\"%1F\", \"\\u001F\").replace(\"%25\", \"%\")"
        when (arg.type) {
          TypeMapping.STRING -> text
          TypeMapping.NODE_PATH -> "net.multigesture.kanama.types.NodePath($text)"
          TypeMapping.INT -> "$part.toLong()"
          TypeMapping.BOOL -> "($part == \"1\")"
          TypeMapping.OBJECT -> {
            val wrapper =
              checkNotNull(arg.objectWrapperFqName) { "packed object argument needs a wrapper" }
            val handle = "$part.toInt().takeIf { it != 0 }?.let { WebObjectId(it) }"
            if (arg.nullable) "$handle?.let { $wrapper(it) }"
            else "$wrapper(checkNotNull($handle) { \"Argument ${arg.name} is not nullable\" })"
          }
          else -> error("no packed argument decoding for ${arg.type.name}")
        }
      }

    /** `(FLOAT, INT) -> void` — the shape spelling used in degradation reasons. */
    private fun shapeOf(args: List<ArgModel>, returnType: TypeMapping?): String =
      "(${args.joinToString(", ") { it.type.name }}) -> ${returnType?.name ?: "void"}"

    /** [WebMethodArm.dispatch] with the offending shape spelled into the reason. */
    fun methodDispatch(method: MethodModel): WebDispatch {
      val arm = methodArm(method)
      if (arm != WebMethodArm.NONE) return arm.dispatch
      return WebDispatch(
        WebDispatchStatus.UNSUPPORTED,
        "no arm for the registered-method shape " +
          "${shapeOf(method.args, method.returnType)}; the proxy emits a stub that throws",
      )
    }

    /**
     * Scalar signal payload types [SIGNAL_DISPATCH_ONE] packs and delivers to a Kotlin lambda (task
     * 80 slice 2). Same packing as the property/return channel: one string, parsed by the typed
     * `GodotSignal.connect*` overload the consumer chose.
     */
    fun isScalarSignalPayload(type: TypeMapping): Boolean =
      when (type) {
        TypeMapping.STRING,
        TypeMapping.INT,
        TypeMapping.FLOAT,
        TypeMapping.BOOL,
        TypeMapping.VECTOR2,
        TypeMapping.VECTOR2I,
        TypeMapping.VECTOR3 -> true
        else -> false
      }

    /**
     * How a declared `@ScriptSignal` payload reaches a Kotlin callback.
     *
     * A proxy emits three delivery helpers ([SIGNAL_DISPATCH_ZERO], [SIGNAL_DISPATCH_ONE],
     * [SIGNAL_DISPATCH_OBJECT]) and `GodotSignal.connect`/`connectObject` pick between them: zero
     * arguments, one scalar carried packed by [SIGNAL_DISPATCH_ONE], or one object handle. Two or
     * more emitted arguments, and single payloads outside [isScalarSignalPayload], still ride
     * [SIGNAL_DISPATCH_ONE] with the payload dropped.
     *
     * A payload the lambda path drops can still be delivered by connecting the signal to a
     * **named** registered method (`connect(target, "method")`), where it rides that method's own
     * arm — so this is `argument-dropped`, never `unsupported`.
     */
    fun signalDispatch(signal: SignalModel): WebDispatch {
      val args = signal.args
      if (args.isEmpty()) return WebDispatch(WebDispatchStatus.TYPED)
      if (args.size == 1 && args.single().type == TypeMapping.OBJECT) {
        return WebDispatch(WebDispatchStatus.TYPED)
      }
      if (args.size == 1 && isScalarSignalPayload(args.single().type)) {
        return WebDispatch(WebDispatchStatus.TYPED)
      }
      val limit =
        if (args.size == 1)
          "Kotlin lambda callbacks take 0 arguments, 1 packed scalar, or 1 object handle"
        else "Kotlin lambda callbacks accept at most 1 emitted argument"
      return WebDispatch(
        WebDispatchStatus.ARGUMENT_DROPPED,
        "signal payload dropped: $limit, so the declared " +
          "(${args.joinToString(", ") { it.type.name }}) payload reaches Kotlin only through a " +
          "named registered-method connect",
      )
    }

    /**
     * The push arm the proxy emits for an `@ScriptProperty` at hydration. Same contract as
     * [methodArm]: the emitter switches on this value, and the manifest reports it.
     */
    fun propertyArm(property: ScriptPropertyModel): WebPropertyArm =
      when (property.type) {
        TypeMapping.STRING -> WebPropertyArm.STRING
        TypeMapping.NODE_PATH -> WebPropertyArm.NODE_PATH
        TypeMapping.INT -> WebPropertyArm.INT
        TypeMapping.FLOAT -> WebPropertyArm.FLOAT
        TypeMapping.BOOL -> WebPropertyArm.BOOL
        TypeMapping.VECTOR2 -> WebPropertyArm.VECTOR2
        TypeMapping.VECTOR3 -> WebPropertyArm.VECTOR3
        TypeMapping.VECTOR2I -> WebPropertyArm.VECTOR2I
        TypeMapping.OBJECT -> WebPropertyArm.OBJECT
        TypeMapping.ARRAY ->
          if (property.arrayElementString) WebPropertyArm.STRING_ARRAY
          else WebPropertyArm.OBJECT_ARRAY
        else -> WebPropertyArm.NONE
      }

    /** [WebPropertyArm.dispatch] with the offending type spelled into the reason. */
    fun propertyDispatch(property: ScriptPropertyModel): WebDispatch {
      val arm = propertyArm(property)
      if (arm != WebPropertyArm.NONE) return arm.dispatch
      return WebDispatch(
        WebDispatchStatus.NOT_EMITTED,
        "no proxy push arm for property type ${property.type.name}; the exported value would " +
          "never be hydrated into Kotlin",
      )
    }

    /**
     * Whether the proxy crosses this virtual into Kotlin. [DISPATCHED_VIRTUALS] is the single arm
     * table; `undispatchedVirtualErrors` already fails a Web build for anything outside it, so a
     * non-typed virtual in the manifest means that guard has a hole.
     */
    fun virtualDispatch(virtual: VirtualModel): WebDispatch =
      if (virtual.virtualName in DISPATCHED_VIRTUALS) WebDispatch(WebDispatchStatus.TYPED)
      else
        WebDispatch(
          WebDispatchStatus.NOT_EMITTED,
          "${virtual.virtualName} is not dispatched by the Kanama Web backend, so the body " +
            "would never run",
        )

    /**
     * Errors for virtuals a Web build would silently drop (task 66).
     *
     * An undispatched virtual compiles for a Web target and is then never called, so the body just
     * disappears — in the tps-demo port that cost a long debugging detour for what should have been
     * a build failure. Fail the build instead, naming the script and the function. Empty on every
     * non-Web target, where the same virtuals are dispatched normally. (`_enter_tree` used to be
     * the canonical offender; task 66b admitted it to [DISPATCHED_VIRTUALS] for real.)
     */
    fun undispatchedVirtualErrors(model: ScriptModel, options: Map<String, String>): List<String> {
      if (!isWebTarget(options)) return emptyList()
      return model.virtuals
        .filter { it.virtualName !in DISPATCHED_VIRTUALS }
        .map { virtual ->
          val where = "${model.simpleName}.${virtual.kotlinMethodName}"
          val advice =
            "move the work into a dispatched virtual " +
              "(${DISPATCHED_VIRTUALS.joinToString(", ")})"
          "$where: ${virtual.virtualName} is not dispatched by the Kanama Web backend, so this " +
            "function would never run in a Web build; $advice."
        }
    }

    // Godot PropertyHint ids the Web emitter reasons about (mirrors annotations/PropertyHint).
    private const val PROPERTY_HINT_RANGE = 1
    private const val PROPERTY_HINT_RESOURCE_TYPE = 17
    private const val PROPERTY_HINT_TYPE_STRING = 23
    private const val PROPERTY_HINT_NODE_TYPE = 34

    private val RANGE_HINT_NUMBER = Regex("""[-+]?(?:\d+\.\d*|\.\d+|\d+)(?:[eE][-+]?\d+)?""")

    /** The normalized NodePath default literal shape produced by the processor. */
    private val NODE_PATH_DEFAULT =
      Regex("""net\.multigesture\.kanama\.types\.NodePath\((".*")\)""")

    /**
     * Parses a Godot RANGE hint string (`"min,max[,step][,option...]"`) into `@export_range`
     * argument spellings — leading numeric parts stay bare, trailing option flags (`or_greater`,
     * `suffix:m`, ...) are quoted — or null when the string has no `min,max` numeric prefix. Shared
     * by the emitter and [unsupportedWebPropertyErrors] so the accepted grammar cannot drift from
     * what actually reaches the proxy.
     */
    internal fun rangeExportArguments(hintString: String): List<String>? {
      val parts = hintString.split(',').map { it.trim() }
      if (parts.size < 2 || parts.any { it.isEmpty() }) return null
      val bounds = parts.takeWhile { RANGE_HINT_NUMBER.matches(it) }
      if (bounds.size < 2) return null
      return bounds + parts.drop(bounds.size).map { "\"$it\"" }
    }

    /**
     * Errors for every declared member a Web build would degrade (task 80 slice 3).
     *
     * The repo-wide rule, adopted from 66a (`undispatchedVirtualErrors`, kanama#114) and #148's
     * property guards and now generalized to registered functions and signals: **a generator may
     * not emit a stub that throws at runtime, or quietly drop a declared payload — it either
     * dispatches, or it fails the build.** Slice 1 made the population visible (the census), slice
     * 2 filled it, and this turns the report fatal, so the next hole in the hand-maintained shape
     * table announces itself as a compile error instead of as a bug someone finds by playing the
     * game.
     *
     * There is deliberately **no allowlist**: every shape the corpus declares has an arm, so an
     * exemption mechanism would only be a place for the next degradation to hide. Reached through
     * the same [methodDispatch] / [signalDispatch] / [propertyDispatch] / [virtualDispatch] tables
     * the manifest and the census read, so the build cannot fail for a reason the manifest denies —
     * or pass while the manifest declares a degradation. Empty on every non-Web target.
     */
    fun undispatchedMemberErrors(model: ScriptModel, options: Map<String, String>): List<String> {
      if (!isWebTarget(options)) return emptyList()
      val errors = mutableListOf<String>()
      model.methods.forEach { method ->
        val dispatch = methodDispatch(method)
        if (dispatch.isTyped) return@forEach
        errors +=
          "${model.simpleName}.${method.godotName} (registered function): " +
            "${dispatch.reason ?: dispatch.status.json}. Declare a shape the Web backend " +
            "dispatches — no arguments; any all-numeric argument list up to $NUMERIC_ARG_SLOTS " +
            "scalar slots; a single String or object argument; a mixed list of String/NodePath/" +
            "Long/Boolean/object arguments; or a zero-argument value return — or add the arm for " +
            "this shape to WebMethodArm and its emitter branch."
      }
      model.signals.forEach { signal ->
        val dispatch = signalDispatch(signal)
        if (dispatch.isTyped) return@forEach
        errors +=
          "${model.simpleName}.${signal.godotName} (signal): " +
            "${dispatch.reason ?: dispatch.status.json}. Declare a payload the Web backend " +
            "delivers — no arguments, one packed scalar (String/Long/Double/Boolean/Vector2/" +
            "Vector2i/Vector3), or one object — or add the wider delivery to the signal dispatch " +
            "helpers."
      }
      // Properties and virtuals already have their own guards; a non-typed entry here means one of
      // those has a hole, so say exactly that instead of repeating their advice.
      model.properties.forEach { property ->
        val dispatch = propertyDispatch(property)
        if (dispatch.isTyped) return@forEach
        errors +=
          "${model.simpleName}.${property.godotName} (property): " +
            "${dispatch.reason ?: dispatch.status.json} (unsupportedWebPropertyErrors has a hole " +
            "for this declaration)."
      }
      model.virtuals.forEach { virtual ->
        val dispatch = virtualDispatch(virtual)
        if (dispatch.isTyped) return@forEach
        errors +=
          "${model.simpleName}.${virtual.kotlinMethodName} (virtual): " +
            "${dispatch.reason ?: dispatch.status.json} (undispatchedVirtualErrors has a hole for " +
            "this declaration)."
      }
      return errors
    }

    /**
     * Errors for `@ScriptProperty`/`@Export` declarations a Web build would mishandle (task 64,
     * mirroring [undispatchedVirtualErrors]): a property type without the full Web arm set
     * (declaration, push, pull, registry accessors) used to emit non-compiling registry code or
     * silently drop values; an expression default used to hydrate the type default over the Kotlin
     * initializer; an unexpressible hint used to vanish from the proxy. Each is a build error
     * naming the script, the property, and the fix. Empty on every non-Web target.
     */
    fun unsupportedWebPropertyErrors(
      model: ScriptModel,
      options: Map<String, String>,
    ): List<String> {
      if (!isWebTarget(options)) return emptyList()
      val errors = mutableListOf<String>()
      for (property in model.properties) {
        val where = "${model.simpleName}.${property.kotlinName}"
        val supported =
          when {
            property.enumFqName != null || property.narrow != null -> false
            else ->
              when (property.type) {
                TypeMapping.STRING,
                TypeMapping.INT,
                TypeMapping.FLOAT,
                TypeMapping.BOOL,
                TypeMapping.VECTOR2,
                TypeMapping.VECTOR2I,
                TypeMapping.VECTOR3,
                TypeMapping.NODE_PATH -> true
                TypeMapping.OBJECT ->
                  property.objectWrapperFqName != null || property.customScriptFqName != null
                TypeMapping.ARRAY ->
                  property.arrayElementString ||
                    property.arrayElementWrapperFqName != null ||
                    property.arrayElementCustomScriptFqName != null
                else -> false
              }
          }
        if (!supported) {
          val declared =
            when {
              property.enumFqName != null -> property.enumFqName
              property.narrow != null -> "narrow ${property.narrow}"
              else -> property.type.toString()
            }
          errors +=
            "$where: @ScriptProperty type '$declared' has no full Kanama Web property arm set " +
              "(declaration/push/pull/registry); a Web build would emit broken or silently " +
              "dropped property code. Use a Web-supported property type " +
              "(String, Long, Double, Boolean, Vector2, Vector2i, Vector3, NodePath, a wrapped " +
              "object/script type, or a supported List) or keep the property off the Web target."
          continue
        }
        val defaultDrivesProxy =
          when (property.type) {
            TypeMapping.STRING,
            TypeMapping.INT,
            TypeMapping.FLOAT,
            TypeMapping.BOOL,
            TypeMapping.VECTOR2,
            TypeMapping.VECTOR2I,
            TypeMapping.VECTOR3,
            TypeMapping.NODE_PATH -> true
            else -> false
          }
        if (defaultDrivesProxy && property.defaultLiteral == null) {
          errors +=
            "$where: the property initializer is not a plain literal, so the Web proxy would " +
              "declare (and hydrate) the type default instead of the Kotlin value. Spell the " +
              "default as a literal (e.g. `1.0471975511965976` instead of `Mathf.PI / 3.0`)."
        }
        when (property.hint) {
          0 -> Unit
          PROPERTY_HINT_RANGE -> {
            val rangeType = property.type == TypeMapping.INT || property.type == TypeMapping.FLOAT
            if (!rangeType) {
              errors +=
                "$where: PropertyHint.RANGE is only expressible for int/float exports on the " +
                  "Web target (GDScript @export_range); this property is ${property.type}."
            } else if (rangeExportArguments(property.hintString) == null) {
              errors +=
                "$where: RANGE hintString '${property.hintString}' is not 'min,max[,step]" +
                  "[,option...]' with numeric bounds, so it cannot be emitted as " +
                  "@export_range; fix the hintString."
            }
          }
          // Structural hints are already carried by the typed GDScript declaration the proxy
          // emits (e.g. `@export var scene: PackedScene`, `@export var list: Array[Texture2D]`):
          // Godot re-derives the same resource/node/typed-array hint from the type, so nothing
          // is lost in the .gd.
          PROPERTY_HINT_RESOURCE_TYPE,
          PROPERTY_HINT_NODE_TYPE ->
            if (property.type != TypeMapping.OBJECT) {
              errors +=
                "$where: hint ${property.hint} is only valid on object/resource exports; " +
                  "it cannot be expressed in the Web proxy for ${property.type}."
            }
          PROPERTY_HINT_TYPE_STRING ->
            if (property.type != TypeMapping.ARRAY) {
              errors +=
                "$where: hint ${property.hint} is only valid on typed-array exports; " +
                  "it cannot be expressed in the Web proxy for ${property.type}."
            }
          else ->
            errors +=
              "$where: property hint ${property.hint} (hintString '${property.hintString}') " +
                "has no Kanama Web proxy emission; it would be silently dropped from the " +
                "generated .gd. Use a supported hint (RANGE, or the structural resource/node/" +
                "typed-array hints) or remove it for the Web target."
        }
      }
      return errors
    }
  }

  data class ProxySource(
    val sourceResourcePath: String,
    val proxyResourcePath: String,
    val fileName: String,
    val source: String,
  )

  private data class ProtocolMethod(
    val name: String,
    val args: List<ArgModel>,
    val returnType: TypeMapping?,
    val dispatch: WebDispatch,
  )

  fun proxySources(): List<ProxySource> =
    scripts.mapIndexed { index, input ->
      val fileName = input.model.simpleName
      ProxySource(
        sourceResourcePath = input.resourcePath,
        proxyResourcePath = "res://kanama-web/generated/$fileName.gd",
        fileName = fileName,
        source = proxySource(index + 1, input.model),
      )
    } +
      ProxySource(
        sourceResourcePath = "",
        proxyResourcePath = "res://kanama-web/generated/KanamaWebHandles.gd",
        fileName = "KanamaWebHandles",
        source =
          buildString {
            appendLine("# Generated by KanamaProcessor — do not edit.")
            appendLine("class_name KanamaWebHandles")
            appendLine("extends RefCounted")
            appendLine()
            appendLine("# One handle dictionary shared by every Kanama Web proxy: a handle")
            appendLine("# minted by any proxy resolves in all of them.")
            appendLine("static var handles: Dictionary = {}")
            appendLine()
            appendLine("# Proxy script path per Kanama script class (simple name), so a proxy can")
            appendLine("# instantiate a scripted resource for the runtime factory crossing.")
            appendLine("static var proxy_paths: Dictionary = {")
            scripts.forEach { input ->
              appendLine(
                "\t${quote(input.model.simpleName)}: ${quote("res://kanama-web/generated/${input.model.simpleName}.gd")},"
              )
            }
            appendLine("}")
          },
      )

  fun proxyManifest(): String = buildString {
    appendLine("# kanama-web-protocol=$PROTOCOL_VERSION")
    appendLine("# source-resource\tproxy-resource\tgenerated-file")
    proxySources()
      .filter { it.sourceResourcePath.isNotEmpty() }
      .forEach { proxy ->
        appendLine("${proxy.sourceResourcePath}\t${proxy.proxyResourcePath}\t${proxy.fileName}.gd")
      }
  }

  fun protocolManifest(): String = buildString {
    appendLine("{")
    appendLine("  \"schemaVersion\": $PROTOCOL_SCHEMA_VERSION,")
    appendLine("  \"protocolVersion\": $PROTOCOL_VERSION,")
    appendLine("  \"scripts\": [")
    scripts.forEachIndexed { scriptIndex, input ->
      val model = input.model
      appendLine("    {")
      appendLine("      \"id\": ${scriptIndex + 1},")
      appendLine("      \"resourcePath\": ${quote(input.resourcePath)},")
      appendLine("      \"className\": ${quote(model.fqName)},")
      appendLine("      \"attachTo\": ${quote(model.attachTo)},")
      appendLine("      \"properties\": [")
      model.properties.forEachIndexed { index, property ->
        append("        {\"id\": ${index + 1}, \"name\": ${quote(property.godotName)}, ")
        append("\"type\": ${quote(protocolPropertyType(property))}, ")
        append("\"nullable\": ${property.nullable}, \"hint\": ${property.hint}, ")
        append("\"hintString\": ${quote(property.hintString)}, \"usage\": ${property.usage}")
        appendDispatch(propertyDispatch(property))
        append("}")
        appendLine(if (index == model.properties.lastIndex) "" else ",")
      }
      appendLine("      ],")
      appendProtocolMethods(
        "virtuals",
        model.virtuals.map { virtual ->
          ProtocolMethod(
            virtual.virtualName,
            virtual.args,
            virtual.returnType,
            virtualDispatch(virtual),
          )
        },
      )
      appendLine(",")
      appendProtocolMethods(
        "methods",
        model.methods.map { method ->
          ProtocolMethod(method.godotName, method.args, method.returnType, methodDispatch(method))
        },
      )
      appendLine(",")
      appendLine("      \"signals\": [")
      model.signals.forEachIndexed { index, signal ->
        append("        {\"id\": ${index + 1}, \"name\": ${quote(signal.godotName)}, ")
        append("\"arguments\": ${protocolArgs(signal.args)}")
        appendDispatch(signalDispatch(signal))
        append("}")
        appendLine(if (index == model.signals.lastIndex) "" else ",")
      }
      appendLine("      ]")
      append("    }")
      appendLine(if (scriptIndex == scripts.lastIndex) "" else ",")
    }
    appendLine("  ]")
    appendLine("}")
  }

  /**
   * Every non-`typed` manifest entry, in manifest order (task 80).
   *
   * Same arm tables the emitter and [protocolManifest] use, so the build report can never claim a
   * different population than the manifest it is reporting on.
   */
  fun degradations(): List<WebDegradation> = buildList {
    scripts.forEach { input ->
      val model = input.model
      fun record(name: String, kind: String, dispatch: WebDispatch) {
        if (!dispatch.isTyped) add(WebDegradation(model.simpleName, name, kind, dispatch))
      }
      model.properties.forEach { record(it.godotName, "property", propertyDispatch(it)) }
      model.virtuals.forEach { record(it.virtualName, "virtual", virtualDispatch(it)) }
      model.methods.forEach { record(it.godotName, "method", methodDispatch(it)) }
      model.signals.forEach { record(it.godotName, "signal", signalDispatch(it)) }
    }
  }

  /** Total manifest entries across every script, i.e. the denominator of [degradations]. */
  fun memberCount(): Int =
    scripts.sumOf { (model, _) ->
      model.properties.size + model.virtuals.size + model.methods.size + model.signals.size
    }

  /**
   * The build-time degradation census (task 80 slice 1), as lines to log.
   *
   * Slice 1 made this visible and slice 2 emptied it; since slice 3 the same population is a BUILD
   * ERROR ([undispatchedMemberErrors]), so on a build that gets this far the detail lines are
   * always empty and the summary reads `0 of N`. It stays because the count is the census — the
   * number that turned "add a callDouble arm" into a measured parcel.
   */
  fun degradationReport(): List<String> = buildList {
    val degradations = degradations()
    val byKind = degradations.groupingBy { it.kind }.eachCount()
    val counts =
      listOf("method", "signal", "property", "virtual").joinToString(", ") {
        "$it ${byKind[it] ?: 0}"
      }
    add(
      "[kanama:web-dispatch] ${degradations.size} of ${memberCount()} declared member(s) across " +
        "${scripts.size} script(s) do not dispatch typed ($counts)"
    )
    degradations.forEach { add("[kanama:web-dispatch]   $it") }
    if (degradations.isNotEmpty()) {
      add(
        "[kanama:web-dispatch] these are declared in KanamaWebProtocol.generated.json as " +
          "dispatch != \"typed\" and FAIL the build (task 80 slice 3)"
      )
    }
  }

  fun constantsSource(): String = buildString {
    appendLine("package net.multigesture.kanama.generated")
    appendLine()
    appendLine("@Suppress(\"unused\")")
    appendLine(
      "private fun emitWebSignal(instance: Any, signalName: String, args: Array<out Any?>) {"
    )
    appendLine(
      "  val script = instance as? net.multigesture.kanama.api.KanamaScript<*> ?: error(\"Signal target is not a Kanama script\")"
    )
    appendLine(
      "  net.multigesture.kanama.api.GodotObject(script.godotObject).emitSignal(signalName, *args)"
    )
    appendLine("}")
    scripts.forEach { input ->
      val model = input.model
      if (model.signals.isNotEmpty()) {
        appendLine()
        appendLine("object ${model.simpleName}Signals {")
        model.signals.forEach { signal ->
          val params =
            signal.args.joinToString("") { arg ->
              ", ${constantIdentifier(arg.name)}: ${arg.kotlinType}"
            }
          val args = signal.args.joinToString(", ") { constantIdentifier(it.name) }
          appendLine(
            "  fun ${constantIdentifier(signal.godotName)}(instance: ${model.fqName}$params) {"
          )
          appendLine("    emitWebSignal(instance, ${quote(signal.godotName)}, arrayOf($args))")
          appendLine("  }")
        }
        appendLine("}")
      }
      if (
        model.methods.isNotEmpty() || model.properties.isNotEmpty() || model.signals.isNotEmpty()
      ) {
        appendLine()
        appendLine("object ${model.simpleName}Names {")
        if (model.methods.isNotEmpty()) {
          appendLine("  object Methods {")
          model.methods
            .distinctBy { constantIdentifier(it.kotlinName) }
            .forEach { method ->
              appendLine(
                "    const val ${constantIdentifier(method.kotlinName)}: String = ${quote(method.godotName)}"
              )
            }
          appendLine("  }")
        }
        if (model.properties.isNotEmpty()) {
          appendLine("  object Properties {")
          model.properties.forEach { property ->
            appendLine(
              "    const val ${constantIdentifier(property.kotlinName)}: String = ${quote(property.godotName)}"
            )
          }
          appendLine("  }")
        }
        if (model.signals.isNotEmpty()) {
          appendLine("  object Signals {")
          model.signals.forEach { signal ->
            appendLine(
              "    const val ${constantIdentifier(signal.godotName)}: String = ${quote(signal.godotName)}"
            )
          }
          appendLine("  }")
        }
        appendLine("}")
      }
    }
  }

  fun compatibilitySources(): Map<String, String> =
    scripts
      .map { it.model.fqName.substringBeforeLast('.', missingDelimiterValue = "") }
      .filter { it.isNotBlank() }
      .distinct()
      .sorted()
      .associateWith { packageName ->
        buildString {
          appendLine("package $packageName")
          appendLine()
          appendLine("@Suppress(\"unused\")")
          appendLine(
            "internal fun <T> MutableCollection<T>.removeIf(predicate: (T) -> Boolean): Boolean {"
          )
          appendLine("  val originalSize = size")
          appendLine("  removeAll(predicate)")
          appendLine("  return size != originalSize")
          appendLine("}")
          appendLine()
          appendLine("@Suppress(\"unused\")")
          appendLine("internal object System {")
          appendLine("  fun getenv(name: String): String? = null")
          appendLine("}")
        }
      }

  fun registrySource(): String = buildString {
    appendLine("// Generated by KanamaProcessor — do not edit.")
    appendLine("@file:OptIn(net.multigesture.kanama.backend.InternalKanamaBackendApi::class)")
    appendLine("package net.multigesture.kanama.web.generated")
    appendLine()
    appendLine("import net.multigesture.kanama.web.KanamaWebScript")
    appendLine("import net.multigesture.kanama.web.WebMemberDescriptor")
    appendLine("import net.multigesture.kanama.web.WebObjectId")
    appendLine("import net.multigesture.kanama.web.WebScriptDescriptor")
    appendLine("import net.multigesture.kanama.backend.GodotHandle")
    appendLine("import net.multigesture.kanama.types.Vector2i")
    scripts.forEach { appendLine("import ${it.model.fqName}") }
    appendLine()
    appendLine("object KanamaWebProjectRegistry {")
    appendLine("  const val PROTOCOL_VERSION: Int = $PROTOCOL_VERSION")
    appendLine()
    appendLine("  val scripts: List<WebScriptDescriptor> =")
    appendLine("    listOf(")
    scripts.forEachIndexed { index, input -> appendDescriptor(index + 1, input) }
    appendLine("    )")
    appendLine()
    appendLine("  fun create(scriptId: Int, objectId: Int): KanamaWebScript =")
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { index, input ->
      appendLine("      ${index + 1} -> ${input.model.simpleName}(WebObjectId(objectId))")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine()
    appendEnterTreeDispatcher()
    appendReadyDispatcher()
    appendProcessDispatcher()
    appendPhysicsProcessDispatcher()
    appendDrawDispatcher()
    appendExitTreeDispatcher()
    appendInputDispatcher()
    appendUnhandledInputDispatcher()
    appendNoArgsMethodDispatcher()
    appendStringPropertyGetter()
    appendStringPropertySetter()
    appendLongPropertySetter()
    appendDoublePropertySetter()
    appendVector2PropertySetter()
    appendVector2iPropertySetter()
    appendVector3PropertySetter()
    appendObjectPropertySetter()
    appendPackedPropertyGetter()
    appendObjectArrayPropertySetter()
    appendStringArrayPropertySetter()
    appendLongMethodDispatcher()
    appendLongVoidMethodDispatcher()
    appendStringMethodDispatcher()
    appendVector2iMethodDispatcher()
    appendObjectMethodDispatcher()
    appendObjectObjectLongMethodDispatcher()
    appendNumericMethodDispatcher()
    appendPackedReturnMethodDispatcher()
    appendLine("  private fun unknown(kind: String, id: Int): Nothing =")
    appendLine("    error(\"Unknown Kanama Web \$kind id=\$id\")")
    appendLine("}")
  }

  private fun StringBuilder.appendDescriptor(scriptId: Int, input: WebScriptInput) {
    val model = input.model
    appendLine("      WebScriptDescriptor(")
    appendLine("        id = $scriptId,")
    appendLine("        resourcePath = ${quote(input.resourcePath)},")
    appendLine("        className = ${quote(model.fqName)},")
    appendLine("        attachTo = ${quote(model.attachTo)},")
    appendMemberList("properties", model.properties.map { it.godotName })
    appendMemberList("virtuals", model.virtuals.map { it.virtualName })
    appendMemberList("methods", model.methods.map { it.godotName })
    appendMemberList("signals", model.signals.map { it.godotName })
    appendLine("      ),")
  }

  private fun StringBuilder.appendMemberList(label: String, names: List<String>) {
    val members =
      names.mapIndexed { index, name -> "WebMemberDescriptor(${index + 1}, ${quote(name)})" }
    appendLine("        $label = listOf(${members.joinToString(", ")}),")
  }

  private fun StringBuilder.appendReadyDispatcher() {
    appendLine("  fun ready(scriptId: Int, script: KanamaWebScript) {")
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { index, input ->
      val ready = input.model.virtuals.firstOrNull { it.virtualName == "_ready" }
      val body =
        if (ready == null) "Unit"
        else "(script as ${input.model.simpleName}).${ready.kotlinMethodName}()"
      appendLine("      ${index + 1} -> $body")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendProcessDispatcher() {
    appendLine("  fun process(scriptId: Int, script: KanamaWebScript, delta: Double) {")
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { index, input ->
      val process = input.model.virtuals.firstOrNull { it.virtualName == "_process" }
      val body =
        if (process == null) "Unit"
        else "(script as ${input.model.simpleName}).${process.kotlinMethodName}(delta)"
      appendLine("      ${index + 1} -> $body")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendPhysicsProcessDispatcher() {
    appendLine("  fun physicsProcess(scriptId: Int, script: KanamaWebScript, delta: Double) {")
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { index, input ->
      val physics = input.model.virtuals.firstOrNull { it.virtualName == "_physics_process" }
      val body =
        if (physics == null) "Unit"
        else "(script as ${input.model.simpleName}).${physics.kotlinMethodName}(delta)"
      appendLine("      ${index + 1} -> $body")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendDrawDispatcher() {
    appendLine("  fun draw(scriptId: Int, script: KanamaWebScript) {")
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { index, input ->
      val virtual = input.model.virtuals.firstOrNull { it.virtualName == "_draw" }
      // WebMethodArm.DRAW_VIRTUAL is exactly "_draw() -> void": one arm table decides which
      // _draw shapes this dispatcher accepts and which the manifest reports as not-emitted.
      val method = input.model.methods.firstOrNull { methodArm(it) == WebMethodArm.DRAW_VIRTUAL }
      val body =
        when {
          virtual != null -> "(script as ${input.model.simpleName}).${virtual.kotlinMethodName}()"
          method != null -> "(script as ${input.model.simpleName}).${method.kotlinName}()"
          else -> "Unit"
        }
      appendLine("      ${index + 1} -> $body")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendEnterTreeDispatcher() {
    appendLine("  fun enterTree(scriptId: Int, script: KanamaWebScript) {")
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { index, input ->
      val enterTree = input.model.virtuals.firstOrNull { it.virtualName == "_enter_tree" }
      val body =
        if (enterTree == null) "Unit"
        else "(script as ${input.model.simpleName}).${enterTree.kotlinMethodName}()"
      appendLine("      ${index + 1} -> $body")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendExitTreeDispatcher() {
    appendLine("  fun exitTree(scriptId: Int, script: KanamaWebScript) {")
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { index, input ->
      val exitTree = input.model.virtuals.firstOrNull { it.virtualName == "_exit_tree" }
      val body =
        if (exitTree == null) "Unit"
        else "(script as ${input.model.simpleName}).${exitTree.kotlinMethodName}()"
      appendLine("      ${index + 1} -> $body")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendInputDispatcher() {
    appendLine("  fun input(scriptId: Int, script: KanamaWebScript, eventHandle: Int) {")
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { index, input ->
      val virtual = input.model.virtuals.firstOrNull { it.virtualName == "_input" }
      val argument = virtual?.args?.singleOrNull()
      val wrapper = argument?.objectWrapperFqName
      val body =
        if (virtual == null || argument?.type != TypeMapping.OBJECT || wrapper == null) "Unit"
        else
          "(script as ${input.model.simpleName}).${virtual.kotlinMethodName}($wrapper(GodotHandle.fromBackendToken(eventHandle.toLong())))"
      appendLine("      ${index + 1} -> $body")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendUnhandledInputDispatcher() {
    appendLine("  fun unhandledInput(scriptId: Int, script: KanamaWebScript, eventHandle: Int) {")
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { index, input ->
      val virtual = input.model.virtuals.firstOrNull { it.virtualName == "_unhandled_input" }
      val argument = virtual?.args?.singleOrNull()
      val wrapper = argument?.objectWrapperFqName
      val body =
        if (virtual == null || argument?.type != TypeMapping.OBJECT || wrapper == null) "Unit"
        else
          "(script as ${input.model.simpleName}).${virtual.kotlinMethodName}($wrapper(GodotHandle.fromBackendToken(eventHandle.toLong())))"
      appendLine("      ${index + 1} -> $body")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendNoArgsMethodDispatcher() {
    appendLine("  fun callNoArgs(scriptId: Int, methodId: Int, script: KanamaWebScript) {")
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (methodId) {")
      input.model.methods.forEachIndexed { methodIndex, method ->
        if (method.args.isEmpty() && method.returnType == null) {
          appendLine(
            "        ${methodIndex + 1} -> (script as ${input.model.simpleName}).${method.kotlinName}()"
          )
        }
      }
      appendLine("        else -> unknown(\"method\", methodId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendStringPropertyGetter() {
    appendLine(
      "  fun getStringProperty(scriptId: Int, propertyId: Int, script: KanamaWebScript): String ="
    )
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (propertyId) {")
      input.model.properties.forEachIndexed { propertyIndex, property ->
        if (property.type == TypeMapping.STRING) {
          appendLine(
            "        ${propertyIndex + 1} -> (script as ${input.model.simpleName}).${property.kotlinName}"
          )
        }
        if (property.type == TypeMapping.NODE_PATH) {
          appendLine(
            "        ${propertyIndex + 1} -> (script as ${input.model.simpleName}).${property.kotlinName}.path"
          )
        }
      }
      appendLine("        else -> unknown(\"property\", propertyId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine()
  }

  private fun StringBuilder.appendStringPropertySetter() {
    appendLine(
      "  fun setStringProperty(scriptId: Int, propertyId: Int, script: KanamaWebScript, value: String) {"
    )
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (propertyId) {")
      input.model.properties.forEachIndexed { propertyIndex, property ->
        if (property.type == TypeMapping.STRING && property.isMutable) {
          appendLine(
            "        ${propertyIndex + 1} -> (script as ${input.model.simpleName}).${property.kotlinName} = value"
          )
        }
        if (property.type == TypeMapping.NODE_PATH && property.isMutable) {
          appendLine(
            "        ${propertyIndex + 1} -> (script as ${input.model.simpleName}).${property.kotlinName} = net.multigesture.kanama.types.NodePath(value)"
          )
        }
      }
      appendLine("        else -> unknown(\"property\", propertyId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendLongMethodDispatcher() {
    appendLine(
      "  fun callLong(scriptId: Int, methodId: Int, script: KanamaWebScript, value: Long): Long ="
    )
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (methodId) {")
      input.model.methods.forEachIndexed { methodIndex, method ->
        if (
          method.returnType == TypeMapping.INT &&
            method.args.size == 1 &&
            method.args.single().type == TypeMapping.INT
        ) {
          appendLine(
            "        ${methodIndex + 1} -> (script as ${input.model.simpleName}).${method.kotlinName}(value)"
          )
        }
      }
      appendLine("        else -> unknown(\"method\", methodId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine()
  }

  /**
   * The string crossing: one declared STRING argument ([WebMethodArm.STRING_VOID]), or a whole
   * MIXED argument list packed into that same string ([WebMethodArm.PACKED_ARGS], task 80 slice 3).
   * The arm table decides which — this dispatcher never re-reads the shapes — so the two encodings
   * can share one entry point without the protocol growing an extra crossing.
   */
  private fun StringBuilder.appendStringMethodDispatcher() {
    appendLine(
      "  fun callString(scriptId: Int, methodId: Int, script: KanamaWebScript, value: String) {"
    )
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (methodId) {")
      input.model.methods.forEachIndexed { methodIndex, method ->
        val target = "(script as ${input.model.simpleName}).${method.kotlinName}"
        when (methodArm(method)) {
          WebMethodArm.STRING_VOID -> appendLine("        ${methodIndex + 1} -> $target(value)")
          WebMethodArm.PACKED_ARGS -> {
            val arguments = packedArgKotlinExpressions(method.args).joinToString(", ")
            appendLine("        ${methodIndex + 1} -> {")
            appendLine("          val packedArgs = value.split('\\u001F')")
            appendLine("          $target($arguments)")
            appendLine("        }")
          }
          else -> Unit
        }
      }
      appendLine("        else -> unknown(\"method\", methodId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendLongVoidMethodDispatcher() {
    appendLine(
      "  fun callLongVoid(scriptId: Int, methodId: Int, script: KanamaWebScript, value: Long) {"
    )
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (methodId) {")
      input.model.methods.forEachIndexed { methodIndex, method ->
        if (
          method.returnType == null &&
            method.args.size == 1 &&
            method.args.single().type == TypeMapping.INT
        ) {
          appendLine(
            "        ${methodIndex + 1} -> (script as ${input.model.simpleName}).${method.kotlinName}(value)"
          )
        }
      }
      appendLine("        else -> unknown(\"method\", methodId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendVector2iMethodDispatcher() {
    appendLine(
      "  fun callVector2i(scriptId: Int, methodId: Int, script: KanamaWebScript, x: Int, y: Int) {"
    )
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (methodId) {")
      input.model.methods.forEachIndexed { methodIndex, method ->
        if (
          method.returnType == null &&
            method.args.size == 1 &&
            method.args.single().type == TypeMapping.VECTOR2I
        ) {
          appendLine(
            "        ${methodIndex + 1} -> (script as ${input.model.simpleName}).${method.kotlinName}(Vector2i(x, y))"
          )
        }
      }
      appendLine("        else -> unknown(\"method\", methodId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendObjectMethodDispatcher() {
    appendLine(
      "  fun callObject(scriptId: Int, methodId: Int, script: KanamaWebScript, objectHandle: Int) {"
    )
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (methodId) {")
      input.model.methods.forEachIndexed { methodIndex, method ->
        val arg = method.args.singleOrNull()
        val wrapper = arg?.objectWrapperFqName
        if (method.returnType == null && arg?.type == TypeMapping.OBJECT && wrapper != null) {
          appendLine(
            "        ${methodIndex + 1} -> (script as ${input.model.simpleName}).${method.kotlinName}($wrapper(WebObjectId(objectHandle)))"
          )
        }
      }
      appendLine("        else -> unknown(\"method\", methodId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendObjectObjectLongMethodDispatcher() {
    appendLine(
      "  fun callObjectObjectLong(scriptId: Int, methodId: Int, script: KanamaWebScript, firstHandle: Int, secondHandle: Int, value: Long) {"
    )
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (methodId) {")
      input.model.methods.forEachIndexed { methodIndex, method ->
        val first = method.args.getOrNull(0)
        val second = method.args.getOrNull(1)
        val third = method.args.getOrNull(2)
        val firstWrapper = first?.objectWrapperFqName
        val secondWrapper = second?.objectWrapperFqName
        if (
          method.returnType == null &&
            method.args.size == 3 &&
            first?.type == TypeMapping.OBJECT &&
            second?.type == TypeMapping.OBJECT &&
            third?.type == TypeMapping.INT &&
            firstWrapper != null &&
            secondWrapper != null
        ) {
          appendLine(
            "        ${methodIndex + 1} -> (script as ${input.model.simpleName}).${method.kotlinName}($firstWrapper(GodotHandle.fromBackendToken(firstHandle.toLong())), $secondWrapper(GodotHandle.fromBackendToken(secondHandle.toLong())), value)"
          )
        }
      }
      appendLine("        else -> unknown(\"method\", methodId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  /**
   * Task 80 slice 2: every [WebMethodArm.NUMERIC_VOID] method, reached through one six-slot
   * crossing. The arm table decides membership — this dispatcher never re-reads the shapes.
   */
  private fun StringBuilder.appendNumericMethodDispatcher() {
    val slots = (0 until NUMERIC_ARG_SLOTS).joinToString(", ") { "a$it: Double" }
    appendLine("  fun callDoubles(scriptId: Int, methodId: Int, script: KanamaWebScript, $slots) {")
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (methodId) {")
      input.model.methods.forEachIndexed { methodIndex, method ->
        if (methodArm(method) == WebMethodArm.NUMERIC_VOID) {
          val arguments = numericArgKotlinExpressions(method.args).joinToString(", ")
          appendLine(
            "        ${methodIndex + 1} -> (script as ${input.model.simpleName}).${method.kotlinName}($arguments)"
          )
        }
      }
      appendLine("        else -> unknown(\"method\", methodId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  /**
   * Task 80 slice 2: every [WebMethodArm.PACKED_RETURN] method. The returned value is packed with
   * the same encoding `getPackedProperty` uses and parsed by the proxy per its declared type.
   */
  private fun StringBuilder.appendPackedReturnMethodDispatcher() {
    appendLine("  fun callPacked(scriptId: Int, methodId: Int, script: KanamaWebScript): String =")
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (methodId) {")
      input.model.methods.forEachIndexed { methodIndex, method ->
        if (methodArm(method) == WebMethodArm.PACKED_RETURN) {
          val access = "(script as ${input.model.simpleName}).${method.kotlinName}()"
          val returnType = checkNotNull(method.returnType) { "PACKED_RETURN needs a return type" }
          appendLine("        ${methodIndex + 1} -> ${packedReturnExpression(access, returnType)}")
        }
      }
      appendLine("        else -> unknown(\"method\", methodId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine()
  }

  private fun StringBuilder.appendLongPropertySetter() {
    appendLine(
      "  fun setLongProperty(scriptId: Int, propertyId: Int, script: KanamaWebScript, value: Long) {"
    )
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (propertyId) {")
      input.model.properties.forEachIndexed { propertyIndex, property ->
        if (property.type == TypeMapping.INT && property.isMutable) {
          appendLine(
            "        ${propertyIndex + 1} -> (script as ${input.model.simpleName}).${property.kotlinName} = value"
          )
        }
        // The proxy pushes exported bools through the long channel (1/0).
        if (property.type == TypeMapping.BOOL && property.isMutable) {
          appendLine(
            "        ${propertyIndex + 1} -> (script as ${input.model.simpleName}).${property.kotlinName} = value != 0L"
          )
        }
      }
      appendLine("        else -> unknown(\"property\", propertyId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendDoublePropertySetter() {
    appendLine(
      "  fun setDoubleProperty(scriptId: Int, propertyId: Int, script: KanamaWebScript, value: Double) {"
    )
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (propertyId) {")
      input.model.properties.forEachIndexed { propertyIndex, property ->
        if (property.type == TypeMapping.FLOAT && property.isMutable) {
          appendLine(
            "        ${propertyIndex + 1} -> (script as ${input.model.simpleName}).${property.kotlinName} = value"
          )
        }
      }
      appendLine("        else -> unknown(\"property\", propertyId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendVector2PropertySetter() {
    appendLine(
      "  fun setVector2Property(scriptId: Int, propertyId: Int, script: KanamaWebScript, x: Double, y: Double) {"
    )
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (propertyId) {")
      input.model.properties.forEachIndexed { propertyIndex, property ->
        if (property.type == TypeMapping.VECTOR2 && property.isMutable) {
          appendLine(
            "        ${propertyIndex + 1} -> (script as ${input.model.simpleName}).${property.kotlinName} = net.multigesture.kanama.types.Vector2(x, y)"
          )
        }
      }
      appendLine("        else -> unknown(\"property\", propertyId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendVector2iPropertySetter() {
    appendLine(
      "  fun setVector2iProperty(scriptId: Int, propertyId: Int, script: KanamaWebScript, x: Int, y: Int) {"
    )
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (propertyId) {")
      input.model.properties.forEachIndexed { propertyIndex, property ->
        if (property.type == TypeMapping.VECTOR2I && property.isMutable) {
          appendLine(
            "        ${propertyIndex + 1} -> (script as ${input.model.simpleName}).${property.kotlinName} = Vector2i(x, y)"
          )
        }
      }
      appendLine("        else -> unknown(\"property\", propertyId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  /**
   * Current Kotlin property value packed as one string, per declared type (save-time pull). Object
   * values pack their backend handle, script arrays comma-join element handles, string arrays join
   * with the unit separator; the proxy parses per its declared type.
   */
  private fun StringBuilder.appendPackedPropertyGetter() {
    appendLine(
      "  fun getPackedProperty(scriptId: Int, propertyId: Int, script: KanamaWebScript): String ="
    )
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (propertyId) {")
      input.model.properties.forEachIndexed { propertyIndex, property ->
        val access = "(script as ${input.model.simpleName}).${property.kotlinName}"
        val expression =
          when {
            property.type == TypeMapping.STRING -> access
            property.type == TypeMapping.NODE_PATH -> "$access.path"
            property.type == TypeMapping.INT -> "$access.toString()"
            property.type == TypeMapping.FLOAT -> "$access.toString()"
            property.type == TypeMapping.BOOL -> "if ($access) \"1\" else \"0\""
            property.type == TypeMapping.VECTOR2 -> "$access.let { \"\${it.x},\${it.y}\" }"
            property.type == TypeMapping.VECTOR2I -> "$access.let { \"\${it.x},\${it.y}\" }"
            property.type == TypeMapping.VECTOR3 -> "$access.let { \"\${it.x},\${it.y},\${it.z}\" }"
            property.type == TypeMapping.OBJECT && property.customScriptFqName != null ->
              if (property.nullable) "($access?.objectId?.value ?: 0).toString()"
              else "$access.objectId.value.toString()"
            property.type == TypeMapping.OBJECT ->
              if (property.nullable) "($access?.handle?.value ?: 0).toString()"
              else "$access.handle.value.toString()"
            property.type == TypeMapping.ARRAY && property.arrayElementString ->
              "$access.joinToString(\"\\u001f\")"
            property.type == TypeMapping.ARRAY && property.arrayElementCustomScriptFqName != null ->
              "$access.joinToString(\",\") { it.objectId.value.toString() }"
            property.type == TypeMapping.ARRAY && property.arrayElementWrapperFqName != null ->
              "$access.joinToString(\",\") { it.handle.value.toString() }"
            else -> null
          }
        if (expression != null) {
          appendLine("        ${propertyIndex + 1} -> $expression")
        }
      }
      appendLine("        else -> unknown(\"property\", propertyId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine()
  }

  private fun StringBuilder.appendVector3PropertySetter() {
    appendLine(
      "  fun setVector3Property(scriptId: Int, propertyId: Int, script: KanamaWebScript, x: Double, y: Double, z: Double) {"
    )
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (propertyId) {")
      input.model.properties.forEachIndexed { propertyIndex, property ->
        if (property.type == TypeMapping.VECTOR3 && property.isMutable) {
          appendLine(
            "        ${propertyIndex + 1} -> (script as ${input.model.simpleName}).${property.kotlinName} = net.multigesture.kanama.types.Vector3(x, y, z)"
          )
        }
      }
      appendLine("        else -> unknown(\"property\", propertyId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendObjectPropertySetter() {
    appendLine(
      "  fun setObjectProperty(scriptId: Int, propertyId: Int, script: KanamaWebScript, value: Int) {"
    )
    appendLine("    val handle = value.takeIf { it != 0 }?.let { WebObjectId(it) }")
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (propertyId) {")
      input.model.properties.forEachIndexed { propertyIndex, property ->
        val wrapper = property.objectWrapperFqName
        val customScript = property.customScriptFqName
        if (property.type == TypeMapping.OBJECT && property.isMutable && wrapper != null) {
          val wrapped =
            if (property.nullable) "handle?.let { $wrapper(it) }"
            else
              "$wrapper(checkNotNull(handle) { \"Property ${property.godotName} is not nullable\" })"
          appendLine(
            "        ${propertyIndex + 1} -> (script as ${input.model.simpleName}).${property.kotlinName} = $wrapped"
          )
        } else if (
          property.type == TypeMapping.OBJECT && property.isMutable && customScript != null
        ) {
          // Custom-script node exports resolve to the hydrated Kotlin instance.
          val resolved =
            "handle?.let { net.multigesture.kanama.web.webScriptInstance(it.value) as? $customScript }"
          val wrapped =
            if (property.nullable) resolved
            else
              "checkNotNull($resolved) { \"Property ${property.godotName} is not a hydrated $customScript\" }"
          appendLine(
            "        ${propertyIndex + 1} -> (script as ${input.model.simpleName}).${property.kotlinName} = $wrapped"
          )
        }
      }
      appendLine("        else -> unknown(\"property\", propertyId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendStringArrayPropertySetter() {
    appendLine(
      "  fun setStringArrayProperty(scriptId: Int, propertyId: Int, script: KanamaWebScript, values: List<String>) {"
    )
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (propertyId) {")
      input.model.properties.forEachIndexed { propertyIndex, property ->
        if (
          property.type == TypeMapping.ARRAY && property.isMutable && property.arrayElementString
        ) {
          appendLine(
            "        ${propertyIndex + 1} -> (script as ${input.model.simpleName}).${property.kotlinName} = values"
          )
        }
      }
      appendLine("        else -> unknown(\"property\", propertyId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun StringBuilder.appendObjectArrayPropertySetter() {
    appendLine(
      "  fun setObjectArrayProperty(scriptId: Int, propertyId: Int, script: KanamaWebScript, values: IntArray) {"
    )
    appendLine("    when (scriptId) {")
    scripts.forEachIndexed { scriptIndex, input ->
      appendLine("      ${scriptIndex + 1} -> when (propertyId) {")
      input.model.properties.forEachIndexed { propertyIndex, property ->
        val elementScript = property.arrayElementCustomScriptFqName
        val wrapper = property.arrayElementWrapperFqName
        if (property.type == TypeMapping.ARRAY && property.isMutable && elementScript != null) {
          appendLine(
            "        ${propertyIndex + 1} -> (script as ${input.model.simpleName}).${property.kotlinName} = values.map { requireNotNull(net.multigesture.kanama.web.webScriptInstance(it) as? $elementScript) { \"Array element is not a hydrated $elementScript\" } }"
          )
        } else if (property.type == TypeMapping.ARRAY && property.isMutable && wrapper != null) {
          appendLine(
            "        ${propertyIndex + 1} -> (script as ${input.model.simpleName}).${property.kotlinName} = values.map { $wrapper(GodotHandle.fromBackendToken(it.toLong())) }"
          )
        }
      }
      appendLine("        else -> unknown(\"property\", propertyId)")
      appendLine("      }")
    }
    appendLine("      else -> unknown(\"script\", scriptId)")
    appendLine("    }")
    appendLine("  }")
    appendLine()
  }

  private fun proxySource(scriptId: Int, model: ScriptModel): String = buildString {
    val node2dAttachment = model.attachTo in setOf("Node2D", "Area2D", "Sprite2D", "GPUParticles2D")
    val node3dAttachment =
      model.attachTo in
        setOf(
          "Node3D",
          "Camera3D",
          "DirectionalLight3D",
          "OmniLight3D",
          "SpotLight3D",
          "MeshInstance3D",
          "CharacterBody3D",
          "StaticBody3D",
          "RigidBody3D",
          "Area3D",
        )
    val particlesAttachment = model.attachTo == "GPUParticles2D"
    appendLine("# Generated by KanamaProcessor — do not edit.")
    if (model.isGlobalClass) appendLine("class_name ${model.simpleName}")
    appendLine("extends ${model.attachTo}")
    appendLine()
    model.signals.forEach { signal ->
      val args = signal.args.joinToString(", ") { "${it.name}: ${gdType(it)}" }
      appendLine("signal ${signal.godotName}($args)")
    }
    if (model.signals.isNotEmpty()) appendLine()
    model.properties.forEach { property ->
      appendPropertyGroup(property)
      appendLine(
        "${exportAnnotation(property)} var ${property.godotName}: ${gdType(property)} = ${gdDefault(property)}"
      )
    }

    appendLine()
    appendLine("const _KANAMA_SCRIPT_ID: int = $scriptId")
    appendLine("const _KANAMA_PROTOCOL_VERSION: int = $PROTOCOL_VERSION")
    appendLine("var _kanama_bridge")
    appendLine("var _kanama_handle: int = 0")
    appendLine("var _kanama_apply_callback")
    appendLine("var _kanama_immediate_callback")
    appendLine("var _kanama_resource_callback")
    appendLine("var _kanama_signal_callback")
    appendLine("var _kanama_resource_release_callback")
    appendLine("var _kanama_construct_callback")
    appendLine("var _kanama_node_lookup_callback")
    appendLine("var _kanama_packed_scene_callback")
    appendLine("var _kanama_noargs_object_callback")
    appendLine("var _kanama_input_cursor_callback")
    appendLine("var _kanama_connect_callback")
    appendLine("var _kanama_object_query_callback")
    appendLine("var _kanama_noargs_vector2_callback")
    appendLine("var _kanama_signal_vector2i_callback")
    appendLine("var _kanama_tween_callback")
    appendLine("var _kanama_noargs_vector3_callback")
    appendLine("var _kanama_ready_dispatched: bool = false")
    appendLine("var _kanama_pulling: bool = false")
    appendLine("var _kanama_object_handles: Dictionary = KanamaWebHandles.handles")
    appendLine("var _kanama_tween_children: Dictionary = {}")
    appendLine("var _kanama_tween_targets: Dictionary = {}")
    appendLine()
    appendLine("func _kanama_ensure_created() -> int:")
    appendLine("\tif not OS.has_feature(\"web\"):")
    appendLine("\t\treturn 0")
    appendLine("\tif _kanama_handle != 0:")
    appendLine("\t\treturn _kanama_handle")
    appendLine("\t_kanama_bridge = JavaScriptBridge.get_interface(\"KanamaWebBridge\")")
    appendLine("\tif _kanama_bridge == null:")
    appendLine("\t\tpush_error(\"Kanama Web bridge was not initialized before Godot\")")
    appendLine("\t\treturn 0")
    appendLine("\tif int(_kanama_bridge.protocolVersion) != _KANAMA_PROTOCOL_VERSION:")
    appendLine(
      "\t\tpush_error(\"Kanama Web proxy protocol mismatch: expected %d, received %d\" % [_KANAMA_PROTOCOL_VERSION, int(_kanama_bridge.protocolVersion)])"
    )
    appendLine("\t\treturn 0")
    appendLine("\t_kanama_handle = int(_kanama_bridge.create(_KANAMA_SCRIPT_ID))")
    appendLine("\tif _kanama_handle == 0:")
    appendLine("\t\tpush_error(\"Kanama Web script construction failed\")")
    appendLine("\t\treturn 0")
    // Publish this script's own handle in the SHARED dictionary, completing the invariant the
    // node-lookup path below already states: a handle any proxy hands out must resolve in every
    // proxy. Without it a Kanama script handle resolved ONLY through `self if handle ==
    // _kanama_handle`, i.e. only inside its own proxy — so a foreign proxy asked to emit a signal
    // whose payload is another script (charactercontroller's kill plane emits
    // `kill_plane_touched` with the PLAYER's handle, through the Events autoload's proxy) had no
    // way to turn that handle back into an Object and pushed "Unknown Kanama Web signal argument
    // handle". `_exit_tree` already erased this entry; only the insert was missing.
    //
    // Nodes only: the erase lives in `_exit_tree`, which a Resource-attached script never gets,
    // and a RefCounted stored in a static Dictionary would be pinned alive forever.
    //
    // The `Object` local is load-bearing, not ceremony: GDScript's parser rejects `self is Node`
    // outright in a proxy that `extends Resource` ("Expression is of type Weapon so it can't be
    // of type Node") and the whole script fails to compile. Widening to the static type the check
    // is legal for keeps one spelling for every attachment.
    appendLine("\tvar _kanama_self_object: Object = self")
    appendLine("\tif _kanama_self_object is Node:")
    appendLine("\t\t_kanama_object_handles[_kanama_handle] = _kanama_self_object")
    appendLine(
      "\t_kanama_apply_callback = JavaScriptBridge.create_callback(_kanama_apply_commands)"
    )
    appendLine(
      "\t_kanama_immediate_callback = JavaScriptBridge.create_callback(_kanama_immediate_call)"
    )
    appendLine(
      "\t_kanama_resource_callback = JavaScriptBridge.create_callback(_kanama_resource_load)"
    )
    appendLine("\t_kanama_signal_callback = JavaScriptBridge.create_callback(_kanama_signal_emit)")
    appendLine(
      "\t_kanama_resource_release_callback = JavaScriptBridge.create_callback(_kanama_resource_release)"
    )
    appendLine(
      "\t_kanama_construct_callback = JavaScriptBridge.create_callback(_kanama_construct_object)"
    )
    appendLine(
      "\t_kanama_node_lookup_callback = JavaScriptBridge.create_callback(_kanama_node_lookup)"
    )
    appendLine(
      "\t_kanama_packed_scene_callback = JavaScriptBridge.create_callback(_kanama_packed_scene_instantiate)"
    )
    appendLine(
      "\t_kanama_noargs_object_callback = JavaScriptBridge.create_callback(_kanama_noargs_object)"
    )
    appendLine(
      "\t_kanama_input_cursor_callback = JavaScriptBridge.create_callback(_kanama_input_cursor)"
    )
    appendLine("\t_kanama_connect_callback = JavaScriptBridge.create_callback(_kanama_connect)")
    appendLine(
      "\t_kanama_object_query_callback = JavaScriptBridge.create_callback(_kanama_object_query)"
    )
    appendLine(
      "\t_kanama_noargs_vector2_callback = JavaScriptBridge.create_callback(_kanama_noargs_vector2)"
    )
    appendLine(
      "\t_kanama_signal_vector2i_callback = JavaScriptBridge.create_callback(_kanama_signal_emit_vector2i)"
    )
    appendLine("\t_kanama_tween_callback = JavaScriptBridge.create_callback(_kanama_tween_call)")
    appendLine(
      "\t_kanama_noargs_vector3_callback = JavaScriptBridge.create_callback(_kanama_noargs_vector3)"
    )
    appendLine("\t_kanama_bridge.installProxyCallbacks(")
    appendLine("\t\t_kanama_handle,")
    appendLine("\t\t_kanama_apply_callback,")
    appendLine("\t\t_kanama_immediate_callback,")
    appendLine("\t\t_kanama_resource_callback,")
    appendLine("\t\t_kanama_signal_callback,")
    appendLine("\t\t_kanama_resource_release_callback,")
    appendLine("\t\t_kanama_construct_callback,")
    appendLine("\t\t_kanama_node_lookup_callback,")
    appendLine("\t\t_kanama_packed_scene_callback,")
    appendLine("\t\t_kanama_noargs_object_callback,")
    appendLine("\t\t_kanama_input_cursor_callback,")
    appendLine("\t\t_kanama_connect_callback,")
    appendLine("\t\t_kanama_object_query_callback,")
    appendLine("\t\t_kanama_noargs_vector2_callback,")
    appendLine("\t\t_kanama_signal_vector2i_callback,")
    appendLine("\t\t_kanama_tween_callback,")
    appendLine("\t\t_kanama_noargs_vector3_callback)")
    if (node2dAttachment) {
      appendLine("\tvar target: Node2D = self")
      appendLine(
        "\t_kanama_bridge.refreshNode2DSnapshot(_kanama_handle, target.position.x, target.position.y, target.scale.x, target.scale.y, target.modulate.r, target.modulate.g, target.modulate.b, target.modulate.a, target.rotation)"
      )
    }
    if (node3dAttachment) {
      appendLine("\tvar target3d: Node3D = self")
      appendLine(
        "\t_kanama_bridge.refreshNode3DSnapshot(_kanama_handle, target3d.position.x, target3d.position.y, target3d.position.z, target3d.rotation.x, target3d.rotation.y, target3d.rotation.z, target3d.scale.x, target3d.scale.y, target3d.scale.z)"
      )
    }
    // The renderer name is global and any script may branch on it at ready (squash's Main
    // attaches to a plain Node), so every proxy seeds its per-script snapshot.
    appendLine(
      "\t_kanama_bridge.refreshRenderingMethodSnapshot(_kanama_handle, RenderingServer.get_current_rendering_method())"
    )
    if (particlesAttachment) {
      appendLine("\t_kanama_bridge.refreshParticlesSnapshot(_kanama_handle, emitting, lifetime)")
    }
    model.properties.forEachIndexed { index, property ->
      when (propertyArm(property)) {
        WebPropertyArm.STRING ->
          appendLine(
            "\t_kanama_bridge.setStringProperty(_kanama_handle, ${index + 1}, ${property.godotName})"
          )
        // A NodePath is its plain path string on the wire everywhere (protocol unchanged);
        // the Kotlin registry rewraps it into the web NodePath value class.
        WebPropertyArm.NODE_PATH ->
          appendLine(
            "\t_kanama_bridge.setStringProperty(_kanama_handle, ${index + 1}, String(${property.godotName}))"
          )
        WebPropertyArm.INT ->
          appendLine(
            "\t_kanama_bridge.setLongProperty(_kanama_handle, ${index + 1}, ${property.godotName})"
          )
        WebPropertyArm.FLOAT ->
          appendLine(
            "\t_kanama_bridge.setDoubleProperty(_kanama_handle, ${index + 1}, ${property.godotName})"
          )
        WebPropertyArm.BOOL ->
          appendLine(
            "\t_kanama_bridge.setLongProperty(_kanama_handle, ${index + 1}, 1 if ${property.godotName} else 0)"
          )
        WebPropertyArm.VECTOR2 ->
          appendLine(
            "\t_kanama_bridge.setVector2Property(_kanama_handle, ${index + 1}, ${property.godotName}.x, ${property.godotName}.y)"
          )
        WebPropertyArm.VECTOR3 ->
          appendLine(
            "\t_kanama_bridge.setVector3Property(_kanama_handle, ${index + 1}, ${property.godotName}.x, ${property.godotName}.y, ${property.godotName}.z)"
          )
        WebPropertyArm.VECTOR2I ->
          appendLine(
            "\t_kanama_bridge.setVector2iProperty(_kanama_handle, ${index + 1}, ${property.godotName}.x, ${property.godotName}.y)"
          )
        WebPropertyArm.OBJECT -> {
          appendLine("\tvar property_handle_${index + 1}: int = 0")
          appendLine("\tif ${property.godotName} != null:")
          appendLine(
            "\t\t# A Kanama-scripted value resolves to its script handle (hydrated + snapshotted);"
          )
          appendLine("\t\t# an engine value gets a fresh handle and, for Node3D, a transform seed.")
          appendLine("\t\tif ${property.godotName}.has_method(\"_kanama_ensure_created\"):")
          appendLine(
            "\t\t\tproperty_handle_${index + 1} = int(${property.godotName}.call(\"_kanama_ensure_created\"))"
          )
          appendLine("\tvar property_seed_node3d_${index + 1}: Node3D = null")
          appendLine("\tif property_handle_${index + 1} == 0 and ${property.godotName} != null:")
          appendLine(
            "\t\tproperty_handle_${index + 1} = int(_kanama_bridge.allocateBrowserHandle(\"Resource\", _kanama_handle))"
          )
          appendLine("\t\tvar property_object_${index + 1}: Object = ${property.godotName}")
          appendLine("\t\tif property_object_${index + 1} is Node3D:")
          appendLine(
            "\t\t\tproperty_seed_node3d_${index + 1} = property_object_${index + 1} as Node3D"
          )
          appendLine("\tif ${property.godotName} != null:")
          appendLine(
            "\t\t_kanama_object_handles[property_handle_${index + 1}] = ${property.godotName}"
          )
          appendLine(
            "\t_kanama_bridge.setObjectProperty(_kanama_handle, ${index + 1}, property_handle_${index + 1})"
          )
          appendLine("\tif property_seed_node3d_${index + 1} != null:")
          appendLine(
            "\t\t_kanama_bridge.refreshNode3DSnapshot(property_handle_${index + 1}, property_seed_node3d_${index + 1}.position.x, property_seed_node3d_${index + 1}.position.y, property_seed_node3d_${index + 1}.position.z, property_seed_node3d_${index + 1}.rotation.x, property_seed_node3d_${index + 1}.rotation.y, property_seed_node3d_${index + 1}.rotation.z, property_seed_node3d_${index + 1}.scale.x, property_seed_node3d_${index + 1}.scale.y, property_seed_node3d_${index + 1}.scale.z)"
          )
        }
        WebPropertyArm.STRING_ARRAY ->
          appendLine(
            "\t_kanama_bridge.setStringArrayProperty(_kanama_handle, ${index + 1}, \"\\u001f\".join(${property.godotName}))"
          )
        WebPropertyArm.OBJECT_ARRAY -> {
          appendLine("\tvar property_handles_${index + 1}: String = \"\"")
          appendLine("\tfor property_value in ${property.godotName}:")
          appendLine("\t\tvar property_value_handle := 0")
          appendLine(
            "\t\tif property_value != null and property_value.has_method(\"_kanama_ensure_created\"):"
          )
          appendLine(
            "\t\t\tproperty_value_handle = int(property_value.call(\"_kanama_ensure_created\"))"
          )
          appendLine("\t\tif property_value_handle == 0:")
          appendLine(
            "\t\t\tproperty_value_handle = int(_kanama_bridge.allocateBrowserHandle(\"Resource\", _kanama_handle))"
          )
          appendLine("\t\t_kanama_object_handles[property_value_handle] = property_value")
          appendLine(
            "\t\tproperty_handles_${index + 1} += (\",\" if not property_handles_${index + 1}.is_empty() else \"\") + str(property_value_handle)"
          )
          appendLine(
            "\t_kanama_bridge.setObjectArrayProperty(_kanama_handle, ${index + 1}, property_handles_${index + 1})"
          )
        }
        // Task 80: the historical silent drop. unsupportedWebPropertyErrors (task 64) already
        // fails a Web build here, and the manifest now records it as not-emitted either way.
        WebPropertyArm.NONE -> Unit
      }
    }
    appendLine("\treturn _kanama_handle")
    appendLine()
    appendPullProperties(model)
    if (model.virtuals.any { it.virtualName == "_enter_tree" }) {
      // _enter_tree fires before _ready, so the Kotlin instance may not exist yet:
      // _kanama_ensure_created constructs it AND pushes every @export property value
      // (scene-instantiated exports are applied during instantiate(), before add_child,
      // so they are visible to the Kotlin @OnEnterTree body). Unlike _ready this
      // dispatches on EVERY tree entry, matching Godot's own _enter_tree semantics.
      appendLine("func _enter_tree() -> void:")
      appendLine("\tif _kanama_ensure_created() == 0:")
      appendLine("\t\treturn")
      appendLine("\t_kanama_bridge.enterTree(_kanama_handle)")
      appendLine()
    }
    appendLine("func _ready() -> void:")
    appendLine("\tif _kanama_ensure_created() == 0 or _kanama_ready_dispatched:")
    appendLine("\t\treturn")
    appendLine("\t_kanama_ready_dispatched = true")
    if (node2dAttachment) {
      appendLine("\tvar target: Node2D = self")
      appendLine("\tvar viewport_rect := target.get_viewport_rect()")
      appendLine(
        "\t_kanama_bridge.refreshViewportRectSnapshot(_kanama_handle, viewport_rect.position.x, viewport_rect.position.y, viewport_rect.size.x, viewport_rect.size.y)"
      )
    }
    appendLine("\tif _kanama_bridge.shouldDeferReady(${quote(model.fqName)}):")
    appendLine("\t\t_kanama_bridge.recordDeferredReady(${quote(model.fqName)})")
    appendLine(
      "\t\t_kanama_bridge.recordReady(_kanama_handle, _KANAMA_SCRIPT_ID, ${quote(model.fqName)})"
    )
    appendLine("\t\treturn")
    appendLine("\t_kanama_bridge.ready(_kanama_handle)")
    val immediateMethod =
      model.methods.withIndex().firstOrNull { (_, method) ->
        methodArm(method) == WebMethodArm.INT_RET_INT
      }
    if (immediateMethod != null) {
      // Task 88: gated on the spike harness. This is an echo probe for the transport
      // benchmark, not gameplay -- unconditional, it invoked a USER method with a magic
      // argument at every scene entry.
      appendLine("\tif _kanama_bridge.shouldRunImmediateProbe():")
      appendLine(
        "\t\tvar immediate_result := int(_kanama_bridge.callInt(_kanama_handle, ${immediateMethod.index + 1}, 47))"
      )
      appendLine("\t\t_kanama_bridge.recordImmediateResult(immediate_result)")
    }
    appendLine(
      "\t_kanama_bridge.recordReady(_kanama_handle, _KANAMA_SCRIPT_ID, ${quote(model.fqName)})"
    )
    appendLine()
    appendLine("func _process(delta: float) -> void:")
    appendLine("\tif _kanama_handle != 0:")
    if (node2dAttachment) {
      appendLine("\t\tvar target: Node2D = self")
      appendLine("\t\tvar viewport_rect := target.get_viewport_rect()")
      appendLine(
        "\t\t_kanama_bridge.refreshViewportRectSnapshot(_kanama_handle, viewport_rect.position.x, viewport_rect.position.y, viewport_rect.size.x, viewport_rect.size.y)"
      )
    }
    if (node3dAttachment) {
      appendLine("\t\tvar target3d: Node3D = self")
      appendLine(
        "\t\t_kanama_bridge.refreshNode3DSnapshot(_kanama_handle, target3d.position.x, target3d.position.y, target3d.position.z, target3d.rotation.x, target3d.rotation.y, target3d.rotation.z, target3d.scale.x, target3d.scale.y, target3d.scale.z)"
      )
    }
    if (particlesAttachment) {
      appendLine("\t\t_kanama_bridge.refreshParticlesSnapshot(_kanama_handle, emitting, lifetime)")
    }
    if (model.attachTo == "CharacterBody3D") {
      // Seed + post-slide refresh for bodies driven from _process (the FPS player moves in
      // _process): the script reads self.velocity before its first write and after
      // move_and_slide the engine's velocity differs from the last written value.
      appendLine("\t\tvar process_body: CharacterBody3D = self")
      appendLine(
        "\t\t_kanama_bridge.refreshVelocitySnapshot(_kanama_handle, process_body.velocity.x, process_body.velocity.y, process_body.velocity.z)"
      )
    }
    appendLine("\t\t_kanama_bridge.frame(_kanama_handle, delta)")
    if (model.virtuals.any { it.virtualName == "_physics_process" }) {
      appendLine()
      appendLine("func _physics_process(delta: float) -> void:")
      appendLine("\tif _kanama_handle != 0:")
      if (node3dAttachment) {
        // Task 87: the transform snapshot must refresh per PHYSICS TICK, not only per
        // rendered frame. Physics outpaces rendering whenever rAF is slow or unpaced
        // (headless engines, low-FPS hosts), and a physics loop that WRITES a transform
        // derived from a READ of it (squash's lookAtFromPosition(self.position, ...))
        // teleported the body back to the frame-start position on every tick after the
        // first inside one rendered frame — net movement collapsed to one tick per
        // rendered frame (MEASURED 2026-08-11: 0.82 u per 600 ms hold on a free-running
        // headless Chrome at ~4.4 ticks/frame; CI Linux Chrome quantized to exactly one
        // tick, 0.233 u). move_and_slide displaces the body engine-side, so no Kotlin
        // write-through can keep this mirror coherent — only an engine read-back can.
        appendLine("\t\tvar physics_target3d: Node3D = self")
        appendLine(
          "\t\t_kanama_bridge.refreshNode3DSnapshot(_kanama_handle, physics_target3d.position.x, physics_target3d.position.y, physics_target3d.position.z, physics_target3d.rotation.x, physics_target3d.rotation.y, physics_target3d.rotation.z, physics_target3d.scale.x, physics_target3d.scale.y, physics_target3d.scale.z)"
        )
      }
      if (model.attachTo == "CharacterBody3D") {
        // Post-slide velocity refresh: the script reads self.velocity each tick, and after
        // move_and_slide the engine's velocity differs from the last written value.
        appendLine("\t\tvar body: CharacterBody3D = self")
        appendLine(
          "\t\t_kanama_bridge.refreshVelocitySnapshot(_kanama_handle, body.velocity.x, body.velocity.y, body.velocity.z)"
        )
      }
      appendLine("\t\t_kanama_bridge.physicsFrame(_kanama_handle, delta)")
    }
    if (model.virtuals.any { it.virtualName == "_input" }) {
      appendLine()
      appendLine("func _input(event: InputEvent) -> void:")
      appendLine("\tif _kanama_handle != 0:")
      appendLine(
        "\t\tvar event_handle := int(_kanama_bridge.allocateTransientObjectHandle(_kanama_handle))"
      )
      appendLine("\t\t_kanama_object_handles[event_handle] = event")
      appendLine("\t\t_kanama_bridge.input(_kanama_handle, event_handle)")
      appendLine("\t\t_kanama_object_handles.erase(event_handle)")
      appendLine("\t\t_kanama_bridge.releaseTransientObjectHandle(event_handle)")
    }
    if (model.virtuals.any { it.virtualName == "_unhandled_input" }) {
      appendLine()
      appendLine("func _unhandled_input(event: InputEvent) -> void:")
      appendLine("\tif _kanama_handle != 0:")
      appendLine(
        "\t\tvar event_handle := int(_kanama_bridge.allocateTransientObjectHandle(_kanama_handle))"
      )
      appendLine("\t\t_kanama_object_handles[event_handle] = event")
      appendLine("\t\t_kanama_bridge.unhandledInput(_kanama_handle, event_handle)")
      appendLine("\t\t_kanama_object_handles.erase(event_handle)")
      appendLine("\t\t_kanama_bridge.releaseTransientObjectHandle(event_handle)")
    }
    appendLine()
    appendLine("func _draw() -> void:")
    appendLine("\tif _kanama_handle != 0:")
    appendLine("\t\t_kanama_bridge.draw(_kanama_handle)")
    appendLine()
    appendLine("func _exit_tree() -> void:")
    appendLine("\tif _kanama_handle == 0:")
    appendLine("\t\t_kanama_clear_callbacks()")
    appendLine("\t\treturn")
    appendLine("\tvar freed_handle := _kanama_handle")
    appendLine("\t_kanama_bridge.free(_kanama_handle)")
    appendLine("\t_kanama_clear_callbacks()")
    appendLine("\t_kanama_handle = 0")
    appendLine("\t# The handle dictionary is shared across proxies: erase only this script's")
    appendLine("\t# entry (bridge-side ownership release retires the handles it minted).")
    appendLine("\t_kanama_object_handles.erase(freed_handle)")
    appendLine("\t_kanama_tween_children.clear()")
    appendLine("\t_kanama_tween_targets.clear()")
    appendLine("\t_kanama_bridge.recordFreed(freed_handle)")
    appendLine()
    appendLine("func _kanama_clear_callbacks() -> void:")
    appendLine("\tif _kanama_bridge == null:")
    appendLine("\t\treturn")
    appendLine("\tif _kanama_handle != 0:")
    appendLine("\t\t_kanama_bridge.clearProxyCallbacks(_kanama_handle)")
    appendLine("\t_kanama_apply_callback = null")
    appendLine("\t_kanama_immediate_callback = null")
    appendLine("\t_kanama_resource_callback = null")
    appendLine("\t_kanama_signal_callback = null")
    appendLine("\t_kanama_resource_release_callback = null")
    appendLine("\t_kanama_construct_callback = null")
    appendLine("\t_kanama_node_lookup_callback = null")
    appendLine("\t_kanama_packed_scene_callback = null")
    appendLine("\t_kanama_noargs_object_callback = null")
    appendLine("\t_kanama_input_cursor_callback = null")
    appendLine("\t_kanama_connect_callback = null")
    appendLine("\t_kanama_object_query_callback = null")
    appendLine("\t_kanama_noargs_vector2_callback = null")
    appendLine("\t_kanama_signal_vector2i_callback = null")
    appendLine("\t_kanama_tween_callback = null")
    appendLine("\t_kanama_noargs_vector3_callback = null")
    appendLine()
    appendLine("func _kanama_apply_commands(args: Array) -> int:")
    appendLine(
      "\tvar bytes: PackedByteArray = JavaScriptBridge.js_buffer_to_packed_byte_array(args[0])"
    )
    appendLine("\tvar command_count := int(args[1])")
    appendLine("\tvar applied := 0")
    appendLine("\tvar last_value := 0")
    appendLine("\tvar offset := 0")
    appendLine("\tfor command_index in range(command_count):")
    appendLine("\t\tvar opcode := bytes.decode_s32(offset)")
    appendLine("\t\tvar object_handle := bytes.decode_s32(offset + 4)")
    appendLine(
      "\t\tvar target_object: Object = self if object_handle == _kanama_handle else _kanama_object_handles.get(object_handle)"
    )
    // Task 88 (finding 4): a queue_free'd node stays ALIVE until the end of the frame, and
    // Godot (and the desktop backend) keep it callable for that window. The queue_free arm
    // used to erase the handle up front, so a later command in the SAME batch could not
    // resolve it, fell to this ladder's `else`, and took `push_error` + `break` -- dropping
    // every remaining command and then tripping `check(applied == expected)` Kotlin-side.
    // Identical Kotlin code works on desktop, so that divergence was a defect.
    //
    // The entry is now retired lazily instead: it survives while the node does, and is
    // erased the first time resolution sees a genuinely freed instance. That keeps the
    // shared dictionary from accumulating references to freed objects without needing a
    // free-time hook on every acquired node.
    appendLine("\t\tif target_object != null and not is_instance_valid(target_object):")
    appendLine("\t\t\t_kanama_object_handles.erase(object_handle)")
    appendLine("\t\t\ttarget_object = null")
    appendLine("\t\tif opcode == 1000 and object_handle == _kanama_handle:")
    appendLine("\t\t\tlast_value = bytes.decode_s32(offset + 8)")
    appendLine("\t\t\tset_meta(\"kanama_web_scalar\", last_value)")
    if (node2dAttachment) {
      appendLine("\t\t\tvar target: Node2D = self")
      appendLine("\t\t\ttarget.position = Vector2(float(last_value % 640), target.position.y)")
    }
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 1000 and target_object != null:")
    appendLine("\t\t\t# Frame marker for a script processed under another owner's proxy")
    appendLine("\t\t\t# (instantiated nodes flush through their creator): consume it.")
    appendLine("\t\t\tlast_value = bytes.decode_s32(offset + 8)")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 1001 and target_object != null:")
    appendLine("\t\t\t# Task 76: queued generic void call — callv by name. The packed-args")
    appendLine("\t\t\t# string is one-shot staged (consumed + evicted here), not interned.")
    appendLine(
      "\t\t\tvar generic_method := String(_kanama_bridge.resolveCommandStringName(bytes.decode_s32(offset + 8)))"
    )
    appendLine(
      "\t\t\tvar generic_packed := String(_kanama_bridge.takeStagedGenericArgs(bytes.decode_s32(offset + 12)))"
    )
    appendLine("\t\t\tvar generic_queued_args: Array = []")
    appendLine("\t\t\tif generic_packed != \"\":")
    appendLine("\t\t\t\tfor generic_queued_part in generic_packed.split(\"\\u001f\"):")
    appendLine(
      "\t\t\t\t\tgeneric_queued_args.append(_kanama_generic_decode_arg(generic_queued_part))"
    )
    appendLine("\t\t\ttarget_object.callv(StringName(generic_method), generic_queued_args)")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 3 and target_object is Node2D:")
    appendLine("\t\t\tvar target := target_object as Node2D")
    appendLine("\t\t\tvar position_x := bytes.decode_float(offset + 8)")
    appendLine("\t\t\tvar position_y := bytes.decode_float(offset + 12)")
    appendLine("\t\t\ttarget.position = Vector2(position_x, position_y)")
    appendLine("\t\t\tlast_value = int(position_x)")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 30 and target_object is Node2D:")
    appendLine("\t\t\tvar target := target_object as Node2D")
    appendLine(
      "\t\t\ttarget.scale = Vector2(bytes.decode_float(offset + 8), bytes.decode_float(offset + 12))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 53 and target_object is Node2D:")
    appendLine("\t\t\t(target_object as Node2D).rotation = bytes.decode_double(offset + 8)")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 54 and target_object is CanvasItem:")
    appendLine("\t\t\t(target_object as CanvasItem).visible = bytes.decode_s32(offset + 8) != 0")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 55 and target_object is AnimatedSprite2D:")
    appendLine(
      "\t\t\t(target_object as AnimatedSprite2D).flip_v = bytes.decode_s32(offset + 8) != 0"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 56 and target_object is AnimatedSprite2D:")
    appendLine(
      "\t\t\t(target_object as AnimatedSprite2D).flip_h = bytes.decode_s32(offset + 8) != 0"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 57 and target_object is AnimatedSprite2D:")
    appendLine("\t\t\tvar animation_id := bytes.decode_s32(offset + 8)")
    appendLine(
      "\t\t\tvar animation_name := String(_kanama_bridge.resolveCommandStringName(animation_id))"
    )
    appendLine("\t\t\t(target_object as AnimatedSprite2D).animation = StringName(animation_name)")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 58 and target_object is AnimatedSprite2D:")
    appendLine("\t\t\t(target_object as AnimatedSprite2D).play()")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 8")
    appendLine("\t\telif opcode == 59 and target_object is AnimatedSprite2D:")
    appendLine("\t\t\t(target_object as AnimatedSprite2D).stop()")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 8")
    appendLine("\t\telif opcode == 60 and target_object is RigidBody2D:")
    appendLine(
      "\t\t\t(target_object as RigidBody2D).linear_velocity = Vector2(bytes.decode_float(offset + 8), bytes.decode_float(offset + 12))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 61 and target_object is CollisionShape2D:")
    appendLine(
      "\t\t\t(target_object as CollisionShape2D).disabled = bytes.decode_s32(offset + 8) != 0"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 62 and target_object is Timer:")
    appendLine("\t\t\t(target_object as Timer).start(bytes.decode_double(offset + 8))")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 63 and target_object is Timer:")
    appendLine("\t\t\t(target_object as Timer).stop()")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 8")
    appendLine("\t\telif opcode == 64 and target_object is AudioStreamPlayer:")
    appendLine("\t\t\t(target_object as AudioStreamPlayer).stop()")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 8")
    appendLine("\t\telif opcode == 65 and target_object is PathFollow2D:")
    appendLine(
      "\t\t\t(target_object as PathFollow2D).progress_ratio = bytes.decode_double(offset + 8)"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 66 and target_object is Label:")
    appendLine("\t\t\tvar text_id := bytes.decode_s32(offset + 8)")
    appendLine(
      "\t\t\t(target_object as Label).text = String(_kanama_bridge.resolveCommandStringName(text_id))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 67 and target_object != null:")
    appendLine("\t\t\tvar property_id := bytes.decode_s32(offset + 8)")
    appendLine(
      "\t\t\tvar property_name := String(_kanama_bridge.resolveCommandStringName(property_id))"
    )
    appendLine(
      "\t\t\ttarget_object.set_deferred(StringName(property_name), bytes.decode_s32(offset + 12) != 0)"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 68 and target_object is SceneTree:")
    appendLine("\t\t\tvar group_id := bytes.decode_s32(offset + 8)")
    appendLine("\t\t\tvar method_id := bytes.decode_s32(offset + 12)")
    appendLine("\t\t\tvar group_name := String(_kanama_bridge.resolveCommandStringName(group_id))")
    appendLine(
      "\t\t\tvar method_name := String(_kanama_bridge.resolveCommandStringName(method_id))"
    )
    appendLine(
      "\t\t\t(target_object as SceneTree).call_group(StringName(group_name), StringName(method_name))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 32 and target_object is CanvasItem:")
    appendLine("\t\t\tvar target := target_object as CanvasItem")
    appendLine(
      "\t\t\ttarget.modulate = Color(bytes.decode_float(offset + 8), bytes.decode_float(offset + 12), bytes.decode_float(offset + 16), bytes.decode_float(offset + 20))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 24")
    appendLine("\t\telif opcode == 43 and target_object is GPUParticles2D:")
    appendLine(
      "\t\t\t(target_object as GPUParticles2D).emitting = bytes.decode_s32(offset + 8) != 0"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 144 and target_object is Node:")
    appendLine(
      "\t\t\t(target_object as Node).set_physics_process(bytes.decode_s32(offset + 8) != 0)"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 146 and target_object is AudioStreamPlayer3D:")
    appendLine("\t\t\t(target_object as AudioStreamPlayer3D).play(bytes.decode_double(offset + 8))")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 147 and target_object is AudioStreamPlayer3D:")
    appendLine("\t\t\t(target_object as AudioStreamPlayer3D).stop()")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 8")
    appendLine("\t\telif opcode == 152 and target_object is RigidBody3D:")
    appendLine("\t\t\t(target_object as RigidBody3D).freeze = bytes.decode_s32(offset + 8) != 0")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 153 and target_object is RigidBody3D:")
    appendLine("\t\t\t(target_object as RigidBody3D).sleeping = bytes.decode_s32(offset + 8) != 0")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 158 and target_object is AudioStreamPlayer3D:")
    appendLine(
      "\t\t\t(target_object as AudioStreamPlayer3D).pitch_scale = bytes.decode_double(offset + 8)"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 200 and target_object is AudioStreamPlayer3D:")
    appendLine(
      "\t\t\t(target_object as AudioStreamPlayer3D).volume_db = bytes.decode_double(offset + 8)"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 161 and target_object is RigidBody3D:")
    appendLine(
      "\t\t\t(target_object as RigidBody3D).gravity_scale = bytes.decode_double(offset + 8)"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 162 and target_object is RigidBody3D:")
    appendLine(
      "\t\t\t(target_object as RigidBody3D).lock_rotation = bytes.decode_s32(offset + 8) != 0"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 165 and target_object is PhysicsBody3D:")
    appendLine("\t\t\tvar exception_handle := bytes.decode_s32(offset + 8)")
    appendLine(
      "\t\t\tvar exception_node: Node = _kanama_object_handles.get(exception_handle) as Node"
    )
    appendLine("\t\t\tif exception_node != null:")
    appendLine(
      "\t\t\t\t(target_object as PhysicsBody3D).add_collision_exception_with(exception_node)"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 168 and target_object is SceneTree:")
    appendLine("\t\t\t(target_object as SceneTree).paused = bytes.decode_s32(offset + 8) != 0")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 169 and target_object is Node:")
    appendLine("\t\t\t(target_object as Node).set_process(bytes.decode_s32(offset + 8) != 0)")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 170 and target_object is SpringArm3D:")
    appendLine("\t\t\tvar excluded_handle := bytes.decode_s32(offset + 8)")
    appendLine(
      "\t\t\tvar excluded: CollisionObject3D = _kanama_object_handles.get(excluded_handle) as CollisionObject3D"
    )
    appendLine("\t\t\tif excluded != null:")
    appendLine("\t\t\t\t# The RID never crosses the boundary; it is derived applier-side.")
    appendLine("\t\t\t\t(target_object as SpringArm3D).add_excluded_object(excluded.get_rid())")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 171 and target_object is RayCast3D:")
    appendLine("\t\t\tvar ray_exception_handle := bytes.decode_s32(offset + 8)")
    appendLine(
      "\t\t\tvar ray_exception: CollisionObject3D = _kanama_object_handles.get(ray_exception_handle) as CollisionObject3D"
    )
    appendLine("\t\t\tif ray_exception != null:")
    appendLine("\t\t\t\t(target_object as RayCast3D).add_exception(ray_exception)")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 180 and target_object is AnimationMixer:")
    appendLine("\t\t\t(target_object as AnimationMixer).active = bytes.decode_s32(offset + 8) != 0")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 181 and target_object is AnimationPlayer:")
    appendLine("\t\t\t(target_object as AnimationPlayer).stop()")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 8")
    appendLine("\t\telif opcode == 182 and target_object is AnimationPlayer:")
    appendLine(
      "\t\t\t(target_object as AnimationPlayer).seek(bytes.decode_double(offset + 8), true)"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 183 and target_object is AnimationPlayer:")
    appendLine(
      "\t\t\t(target_object as AnimationPlayer).playback_default_blend_time = bytes.decode_double(offset + 8)"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 185 and target_object is Animation:")
    appendLine("\t\t\t(target_object as Animation).loop_mode = bytes.decode_s32(offset + 8)")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 189 and target_object is MeshInstance3D:")
    appendLine("\t\t\t# Material baked null: this family only clears surface overrides.")
    appendLine(
      "\t\t\t(target_object as MeshInstance3D).set_surface_override_material(bytes.decode_s32(offset + 8), null)"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 191 and target_object != null:")
    appendLine("\t\t\tvar dyn_method_id := bytes.decode_s32(offset + 8)")
    appendLine(
      "\t\t\tvar dyn_method := String(_kanama_bridge.resolveCommandStringName(dyn_method_id))"
    )
    appendLine(
      "\t\t\ttarget_object.call(StringName(dyn_method), Vector3(bytes.decode_float(offset + 12), bytes.decode_float(offset + 16), bytes.decode_float(offset + 20)), Vector3(bytes.decode_float(offset + 24), bytes.decode_float(offset + 28), bytes.decode_float(offset + 32)))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 36")
    appendLine("\t\telif opcode == 46 and target_object is AudioStreamPlayer:")
    appendLine("\t\t\tvar stream_handle := bytes.decode_s32(offset + 8)")
    appendLine(
      "\t\t\tvar stream: AudioStream = null if stream_handle == 0 else _kanama_object_handles.get(stream_handle) as AudioStream"
    )
    appendLine("\t\t\tif stream_handle != 0 and stream == null:")
    appendLine("\t\t\t\tpush_error(\"Unknown Kanama Web AudioStream handle: %d\" % stream_handle)")
    appendLine("\t\t\t\tbreak")
    appendLine("\t\t\t(target_object as AudioStreamPlayer).set_stream(stream)")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 47 and target_object is AudioStreamPlayer:")
    appendLine("\t\t\tvar bus_id := bytes.decode_s32(offset + 8)")
    appendLine("\t\t\tvar bus_name := String(_kanama_bridge.resolveCommandStringName(bus_id))")
    appendLine("\t\t\t(target_object as AudioStreamPlayer).set_bus(StringName(bus_name))")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 48 and target_object is AudioStreamPlayer:")
    appendLine(
      "\t\t\t(target_object as AudioStreamPlayer).set_volume_db(bytes.decode_double(offset + 8))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 49 and target_object is AudioStreamPlayer:")
    appendLine(
      "\t\t\t(target_object as AudioStreamPlayer).set_pitch_scale(bytes.decode_double(offset + 8))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 50 and target_object is AudioStreamPlayer:")
    appendLine("\t\t\t(target_object as AudioStreamPlayer).play(bytes.decode_double(offset + 8))")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 52 and target_object is SceneTree:")
    appendLine("\t\t\t(target_object as SceneTree).quit(bytes.decode_s32(offset + 8))")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 128 and target_object is AnimatedSprite3D:")
    appendLine(
      "\t\t\t(target_object as AnimatedSprite3D).play(StringName(String(_kanama_bridge.resolveCommandStringName(bytes.decode_s32(offset + 8)))))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 129 and target_object is AnimatedSprite3D:")
    appendLine("\t\t\t(target_object as AnimatedSprite3D).frame = bytes.decode_s32(offset + 8)")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 140 and target_object is VisualInstance3D:")
    appendLine("\t\t\t(target_object as VisualInstance3D).layers = bytes.decode_s32(offset + 8)")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 130 and target_object is TextureRect:")
    appendLine("\t\t\tvar crosshair_handle := bytes.decode_s32(offset + 8)")
    appendLine(
      "\t\t\t(target_object as TextureRect).texture = null if crosshair_handle == 0 else _kanama_object_handles.get(crosshair_handle) as Texture2D"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 202 and target_object is GridMap:")
    appendLine("\t\t\tvar library_handle := bytes.decode_s32(offset + 8)")
    appendLine(
      "\t\t\t(target_object as GridMap).mesh_library = null if library_handle == 0 else _kanama_object_handles.get(library_handle) as MeshLibrary"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 209 and target_object is MeshLibrary:")
    appendLine("\t\t\t(target_object as MeshLibrary).create_item(bytes.decode_s32(offset + 8))")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 132 and target_object != null:")
    appendLine(
      "\t\t\tvar damage_method := String(_kanama_bridge.resolveCommandStringName(bytes.decode_s32(offset + 8)))"
    )
    appendLine(
      "\t\t\ttarget_object.call(StringName(damage_method), bytes.decode_double(offset + 12))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 20")
    appendLine("\t\telif opcode == 118 and target_object is RayCast3D:")
    appendLine(
      "\t\t\t(target_object as RayCast3D).target_position = Vector3(bytes.decode_float(offset + 8), bytes.decode_float(offset + 12), bytes.decode_float(offset + 16))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 20")
    appendLine("\t\telif opcode == 115 and target_object is DirectionalLight3D:")
    appendLine(
      "\t\t\t(target_object as DirectionalLight3D).sky_mode = bytes.decode_s32(offset + 8)"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    if (node2dAttachment) {
      appendLine("\t\telif opcode == 5 and object_handle == _kanama_handle:")
      appendLine("\t\t\tvar canvas_target: CanvasItem = self")
      appendLine("\t\t\tcanvas_target.queue_redraw()")
      appendLine("\t\t\tapplied += 1")
      appendLine("\t\t\toffset += 8")
      appendLine("\t\telif opcode == 6 and object_handle == _kanama_handle:")
      appendLine("\t\t\tvar texture_handle := bytes.decode_s32(offset + 8)")
      appendLine("\t\t\tvar texture := _kanama_object_handles.get(texture_handle) as Texture2D")
      appendLine("\t\t\tif texture == null:")
      appendLine("\t\t\t\tpush_error(\"Unknown Kanama Web texture handle: %d\" % texture_handle)")
      appendLine("\t\t\t\tbreak")
      appendLine(
        "\t\t\tvar draw_position := Vector2(bytes.decode_float(offset + 12), bytes.decode_float(offset + 16))"
      )
      appendLine(
        "\t\t\tvar modulate := Color(bytes.decode_float(offset + 20), bytes.decode_float(offset + 24), bytes.decode_float(offset + 28), bytes.decode_float(offset + 32))"
      )
      appendLine("\t\t\tvar canvas_target: CanvasItem = self")
      appendLine("\t\t\tcanvas_target.draw_texture(texture, draw_position, modulate)")
      appendLine("\t\t\tapplied += 1")
      appendLine("\t\t\toffset += 36")
    }
    appendLine("\t\telif opcode == 13 and target_object is Node:")
    appendLine("\t\t\tvar child_handle := bytes.decode_s32(offset + 8)")
    appendLine("\t\t\tvar child := _kanama_object_handles.get(child_handle) as Node")
    appendLine("\t\t\tif child == null:")
    appendLine("\t\t\t\tpush_error(\"Unknown Kanama Web child handle: %d\" % child_handle)")
    appendLine("\t\t\t\tbreak")
    appendLine(
      "\t\t\t(target_object as Node).add_child(child, bytes.decode_s32(offset + 12) != 0, bytes.decode_s32(offset + 16))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 20")
    appendLine("\t\telif opcode == 14 and target_object is Node:")
    appendLine("\t\t\tvar child_handle := bytes.decode_s32(offset + 8)")
    appendLine("\t\t\tvar child := _kanama_object_handles.get(child_handle) as Node")
    appendLine("\t\t\tif child == null:")
    appendLine("\t\t\t\tpush_error(\"Unknown Kanama Web child handle: %d\" % child_handle)")
    appendLine("\t\t\t\tbreak")
    appendLine("\t\t\t(target_object as Node).remove_child(child)")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 15 and target_object is Node:")
    // Task 88 (finding 4): do NOT erase here -- the node is alive until the end of the
    // frame and must stay callable for that window (Godot + desktop semantics). The
    // lazy is_instance_valid sweep above retires the entry once the free actually lands.
    appendLine("\t\t\t(target_object as Node).queue_free()")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 8")
    appendLine("\t\telif opcode == 16 and target_object is Sprite2D:")
    appendLine("\t\t\tvar texture_handle := bytes.decode_s32(offset + 8)")
    appendLine(
      "\t\t\tvar texture: Texture2D = null if texture_handle == 0 else _kanama_object_handles.get(texture_handle) as Texture2D"
    )
    appendLine("\t\t\tif texture_handle != 0 and texture == null:")
    appendLine("\t\t\t\tpush_error(\"Unknown Kanama Web texture handle: %d\" % texture_handle)")
    appendLine("\t\t\t\tbreak")
    appendLine("\t\t\t(target_object as Sprite2D).set_texture(texture)")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 74 and target_object is Node3D:")
    appendLine(
      "\t\t\t(target_object as Node3D).position = Vector3(bytes.decode_float(offset + 8), bytes.decode_float(offset + 12), bytes.decode_float(offset + 16))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 20")
    appendLine("\t\telif opcode == 76 and target_object is Node3D:")
    appendLine(
      "\t\t\t(target_object as Node3D).rotation = Vector3(bytes.decode_float(offset + 8), bytes.decode_float(offset + 12), bytes.decode_float(offset + 16))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 20")
    appendLine("\t\telif opcode == 78 and target_object is Node3D:")
    appendLine(
      "\t\t\t(target_object as Node3D).scale = Vector3(bytes.decode_float(offset + 8), bytes.decode_float(offset + 12), bytes.decode_float(offset + 16))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 20")
    appendLine("\t\telif opcode == 80 and target_object is CanvasLayer:")
    appendLine("\t\t\t(target_object as CanvasLayer).visible = bytes.decode_s32(offset + 8) != 0")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 82 and target_object is Environment:")
    appendLine(
      "\t\t\t(target_object as Environment).background_energy_multiplier = bytes.decode_double(offset + 8)"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 84 and target_object is Light3D:")
    appendLine(
      "\t\t\t(target_object as Light3D).set_param(bytes.decode_s32(offset + 8), bytes.decode_double(offset + 12))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 20")
    appendLine("\t\telif opcode == 88 and target_object is CharacterBody3D:")
    appendLine(
      "\t\t\t(target_object as CharacterBody3D).velocity = Vector3(bytes.decode_float(offset + 8), bytes.decode_float(offset + 12), bytes.decode_float(offset + 16))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 20")
    appendLine("\t\telif opcode == 93 and target_object is GPUParticles3D:")
    appendLine(
      "\t\t\t(target_object as GPUParticles3D).emitting = bytes.decode_s32(offset + 8) != 0"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 94 and target_object is GPUParticles3D:")
    appendLine("\t\t\t(target_object as GPUParticles3D).restart(bytes.decode_s32(offset + 8) != 0)")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 95 and target_object is CollisionShape3D:")
    appendLine(
      "\t\t\t(target_object as CollisionShape3D).disabled = bytes.decode_s32(offset + 8) != 0"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 96 and target_object is AudioStreamPlayer:")
    appendLine(
      "\t\t\t(target_object as AudioStreamPlayer).stream_paused = bytes.decode_s32(offset + 8) != 0"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 97 and target_object is Node3D:")
    appendLine("\t\t\t(target_object as Node3D).visible = bytes.decode_s32(offset + 8) != 0")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 99 and target_object is AnimationPlayer:")
    appendLine(
      "\t\t\t(target_object as AnimationPlayer).speed_scale = bytes.decode_double(offset + 8)"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 101 and target_object is Node3D:")
    appendLine(
      "\t\t\t(target_object as Node3D).rotation_degrees = Vector3(bytes.decode_float(offset + 8), bytes.decode_float(offset + 12), bytes.decode_float(offset + 16))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 20")
    appendLine("\t\telif opcode == 105 and target_object != null:")
    appendLine(
      "\t\t\tvar call_method := String(_kanama_bridge.resolveCommandStringName(bytes.decode_s32(offset + 8)))"
    )
    appendLine(
      "\t\t\tvar call_arg := String(_kanama_bridge.resolveCommandStringName(bytes.decode_s32(offset + 12)))"
    )
    appendLine("\t\t\ttarget_object.call(StringName(call_method), call_arg)")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 103 and target_object is AnimationPlayer:")
    appendLine("\t\t\tvar anim_id := bytes.decode_s32(offset + 8)")
    appendLine("\t\t\tvar anim_name := String(_kanama_bridge.resolveCommandStringName(anim_id))")
    appendLine(
      "\t\t\t(target_object as AnimationPlayer).play(StringName(anim_name), bytes.decode_double(offset + 12))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 20")
    appendLine("\t\telif opcode == 150 and target_object is AnimationNodeStateMachinePlayback:")
    appendLine("\t\t\tvar travel_id := bytes.decode_s32(offset + 8)")
    appendLine(
      "\t\t\tvar travel_state := String(_kanama_bridge.resolveCommandStringName(travel_id))"
    )
    appendLine(
      "\t\t\t(target_object as AnimationNodeStateMachinePlayback).travel(StringName(travel_state))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 151 and target_object != null:")
    appendLine("\t\t\tvar indexed_id := bytes.decode_s32(offset + 8)")
    appendLine(
      "\t\t\tvar indexed_path := String(_kanama_bridge.resolveCommandStringName(indexed_id))"
    )
    appendLine(
      "\t\t\ttarget_object.set_indexed(NodePath(indexed_path), bytes.decode_double(offset + 12))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 20")
    appendLine("\t\telif opcode == 226 and target_object is CharacterBody3D:")
    appendLine(
      "\t\t\t(target_object as CharacterBody3D).up_direction = Vector3(bytes.decode_float(offset + 8), bytes.decode_float(offset + 12), bytes.decode_float(offset + 16))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 20")
    appendLine("\t\telif opcode == 228 and target_object is RigidBody3D:")
    appendLine(
      "\t\t\t(target_object as RigidBody3D).linear_velocity = Vector3(bytes.decode_float(offset + 8), bytes.decode_float(offset + 12), bytes.decode_float(offset + 16))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 20")
    appendLine("\t\telif opcode == 230 and target_object != null:")
    appendLine(
      "\t\t\tvar indexed_string_path := String(_kanama_bridge.resolveCommandStringName(bytes.decode_s32(offset + 8)))"
    )
    appendLine(
      "\t\t\tvar indexed_string_value := String(_kanama_bridge.resolveCommandStringName(bytes.decode_s32(offset + 12)))"
    )
    appendLine(
      "\t\t\ttarget_object.set_indexed(NodePath(indexed_string_path), indexed_string_value)"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 231 and target_object != null:")
    appendLine(
      "\t\t\tvar indexed_long_path := String(_kanama_bridge.resolveCommandStringName(bytes.decode_s32(offset + 8)))"
    )
    appendLine(
      "\t\t\ttarget_object.set_indexed(NodePath(indexed_long_path), bytes.decode_s32(offset + 12))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 232 and target_object != null:")
    appendLine(
      "\t\t\tvar indexed_vec2_path := String(_kanama_bridge.resolveCommandStringName(bytes.decode_s32(offset + 8)))"
    )
    appendLine(
      "\t\t\ttarget_object.set_indexed(NodePath(indexed_vec2_path), Vector2(bytes.decode_float(offset + 12), bytes.decode_float(offset + 16)))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 20")
    appendLine("\t\telif opcode == 236 and target_object is CPUParticles3D:")
    appendLine(
      "\t\t\t(target_object as CPUParticles3D).emitting = bytes.decode_s32(offset + 8) != 0"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 237 and target_object is CPUParticles3D:")
    appendLine("\t\t\t(target_object as CPUParticles3D).restart(bytes.decode_s32(offset + 8) != 0)")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 238 and target_object is CPUParticles3D:")
    appendLine(
      "\t\t\t(target_object as CPUParticles3D).emission_box_extents = Vector3(bytes.decode_float(offset + 8), bytes.decode_float(offset + 12), bytes.decode_float(offset + 16))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 20")
    appendLine("\t\telif opcode == 241 and target_object is Light3D:")
    appendLine(
      "\t\t\t(target_object as Light3D).shadow_enabled = bytes.decode_s32(offset + 8) != 0"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 242 and target_object is Environment:")
    appendLine(
      "\t\t\t(target_object as Environment).glow_enabled = bytes.decode_s32(offset + 8) != 0"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 247 and target_object is Material:")
    appendLine("\t\t\tvar next_pass_handle := bytes.decode_s32(offset + 8)")
    appendLine(
      "\t\t\t(target_object as Material).next_pass = null if next_pass_handle == 0 else _kanama_object_handles.get(next_pass_handle) as Material"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 248 and target_object is ShaderMaterial:")
    appendLine(
      "\t\t\tvar shader_param_name := String(_kanama_bridge.resolveCommandStringName(bytes.decode_s32(offset + 8)))"
    )
    appendLine(
      "\t\t\t(target_object as ShaderMaterial).set_shader_parameter(StringName(shader_param_name), bytes.decode_double(offset + 12))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 20")
    appendLine("\t\telif opcode == 251 and target_object is Control:")
    appendLine("\t\t\t(target_object as Control).grab_focus()")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 8")
    appendLine("\t\telif opcode == 254 and target_object is BaseButton:")
    appendLine(
      "\t\t\t(target_object as BaseButton).button_pressed = bytes.decode_s32(offset + 8) != 0"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 256 and target_object is BaseButton:")
    appendLine("\t\t\t(target_object as BaseButton).disabled = bytes.decode_s32(offset + 8) != 0")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 257 and target_object is BaseButton:")
    appendLine("\t\t\tvar button_group_handle := bytes.decode_s32(offset + 8)")
    appendLine(
      "\t\t\t(target_object as BaseButton).button_group = null if button_group_handle == 0 else _kanama_object_handles.get(button_group_handle) as ButtonGroup"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 258 and target_object is Button:")
    appendLine(
      "\t\t\t(target_object as Button).text = String(_kanama_bridge.resolveCommandStringName(bytes.decode_s32(offset + 8)))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 260 and target_object is LineEdit:")
    appendLine(
      "\t\t\t(target_object as LineEdit).text = String(_kanama_bridge.resolveCommandStringName(bytes.decode_s32(offset + 8)))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 261 and target_object is LineEdit:")
    appendLine("\t\t\t(target_object as LineEdit).editable = bytes.decode_s32(offset + 8) != 0")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 262 and target_object is Range:")
    appendLine("\t\t\t(target_object as Range).value = bytes.decode_double(offset + 8)")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 264 and target_object is ConfigFile:")
    appendLine(
      "\t\t\t(target_object as ConfigFile).load(String(_kanama_bridge.resolveCommandStringName(bytes.decode_s32(offset + 8))))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 265 and target_object is ConfigFile:")
    appendLine(
      "\t\t\t(target_object as ConfigFile).save(String(_kanama_bridge.resolveCommandStringName(bytes.decode_s32(offset + 8))))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 267 and target_object is ConfigFile:")
    appendLine(
      "\t\t\tvar config_parts := String(_kanama_bridge.resolveCommandStringName(bytes.decode_s32(offset + 8))).split(\"\\u001f\")"
    )
    appendLine("\t\t\tvar config_tagged: String = config_parts[2]")
    appendLine("\t\t\tvar config_value: Variant = null")
    appendLine("\t\t\tif config_tagged.begins_with(\"i:\"):")
    appendLine("\t\t\t\tconfig_value = int(config_tagged.substr(2))")
    appendLine("\t\t\telif config_tagged.begins_with(\"f:\"):")
    appendLine("\t\t\t\tconfig_value = float(config_tagged.substr(2))")
    appendLine("\t\t\telif config_tagged.begins_with(\"b:\"):")
    appendLine("\t\t\t\tconfig_value = config_tagged.substr(2) == \"true\"")
    appendLine("\t\t\telse:")
    appendLine("\t\t\t\tconfig_value = config_tagged.substr(2)")
    appendLine(
      "\t\t\t(target_object as ConfigFile).set_value(config_parts[0], config_parts[1], config_value)"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 273 and target_object is FastNoiseLite:")
    appendLine("\t\t\t(target_object as FastNoiseLite).seed = bytes.decode_s32(offset + 8)")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 274 and target_object is FastNoiseLite:")
    appendLine(
      "\t\t\t(target_object as FastNoiseLite).fractal_octaves = bytes.decode_s32(offset + 8)"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 275 and target_object is FastNoiseLite:")
    appendLine(
      "\t\t\t(target_object as FastNoiseLite).fractal_lacunarity = bytes.decode_double(offset + 8)"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 277 and target_object is Node:")
    appendLine(
      "\t\t\t(target_object as Node).name = StringName(String(_kanama_bridge.resolveCommandStringName(bytes.decode_s32(offset + 8))))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 279 and target_object is Node:")
    appendLine(
      "\t\t\tvar propagate_name := String(_kanama_bridge.resolveCommandStringName(bytes.decode_s32(offset + 8)))"
    )
    appendLine(
      "\t\t\t(target_object as Node).propagate_call(\"set\", [propagate_name, bytes.decode_s32(offset + 12) != 0])"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 280 and target_object != null:")
    appendLine(
      "\t\t\ttarget_object.call_deferred(StringName(String(_kanama_bridge.resolveCommandStringName(bytes.decode_s32(offset + 8)))))"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 281 and target_object != null:")
    appendLine(
      "\t\t\tvar deferred_method := String(_kanama_bridge.resolveCommandStringName(bytes.decode_s32(offset + 8)))"
    )
    appendLine("\t\t\tvar deferred_arg_handle := bytes.decode_s32(offset + 12)")
    appendLine(
      "\t\t\tvar deferred_arg: Object = null if deferred_arg_handle == 0 else _kanama_object_handles.get(deferred_arg_handle)"
    )
    appendLine("\t\t\ttarget_object.call_deferred(StringName(deferred_method), deferred_arg)")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 16")
    appendLine("\t\telif opcode == 283 and target_object is Camera3D:")
    appendLine("\t\t\t(target_object as Camera3D).make_current()")
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 8")
    appendLine("\t\telif opcode == 284 and target_object is LightmapGI:")
    appendLine("\t\t\tvar light_data_handle := bytes.decode_s32(offset + 8)")
    appendLine(
      "\t\t\t(target_object as LightmapGI).light_data = null if light_data_handle == 0 else _kanama_object_handles.get(light_data_handle) as LightmapGIData"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 285 and target_object is CollisionObject3D:")
    appendLine(
      "\t\t\t(target_object as CollisionObject3D).collision_layer = bytes.decode_s32(offset + 8)"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telif opcode == 286 and target_object is CollisionObject3D:")
    appendLine(
      "\t\t\t(target_object as CollisionObject3D).collision_mask = bytes.decode_s32(offset + 8)"
    )
    appendLine("\t\t\tapplied += 1")
    appendLine("\t\t\toffset += 12")
    appendLine("\t\telse:")
    appendLine(
      "\t\t\tpush_error(\"Invalid Kanama Web command opcode/object: %d/%d\" % [opcode, object_handle])"
    )
    appendLine("\t\t\tbreak")
    appendLine("\t_kanama_bridge.recordApplied(applied, last_value)")
    appendLine("\treturn applied")
    appendLine()
    appendLine("func _kanama_immediate_call(args: Array) -> int:")
    appendLine("\tvar object_handle := int(args[0])")
    appendLine("\tvar result := -1")
    appendLine("\t# Any tracked node can be counted, not just this proxy's own: a script may walk")
    appendLine("\t# the children of a node it looked up (the tps level counts spawn points).")
    appendLine(
      "\tvar count_target: Object = self if object_handle == _kanama_handle else _kanama_object_handles.get(object_handle)"
    )
    appendLine("\tif count_target is Node:")
    appendLine("\t\tresult = (count_target as Node).get_child_count(bool(args[1]))")
    appendLine("\t_kanama_bridge.recordImmediateChildCount(result)")
    appendLine("\treturn result")
    appendLine()
    appendLine("func _kanama_resource_load(args: Array) -> int:")
    appendLine("\tvar resource_handle := int(args[0])")
    appendLine("\tvar path := String(args[1])")
    appendLine("\tvar type_hint := String(args[2])")
    appendLine("\tvar cache_mode := int(args[3])")
    appendLine("\tvar resource := ResourceLoader.load(path, type_hint, cache_mode)")
    appendLine("\tif resource == null:")
    appendLine("\t\t_kanama_bridge.recordImmediateResourceHandle(0)")
    appendLine("\t\treturn 0")
    appendLine("\t# A Kanama-scripted resource resolves to its script handle so the Kotlin side")
    appendLine("\t# can reach the hydrated instance (node-lookup rule for loads).")
    appendLine("\tif resource.has_method(\"_kanama_ensure_created\"):")
    appendLine("\t\tvar loaded_script_handle := int(resource.call(\"_kanama_ensure_created\"))")
    appendLine("\t\tif loaded_script_handle != 0:")
    appendLine("\t\t\t_kanama_object_handles[loaded_script_handle] = resource")
    appendLine("\t\t\t_kanama_bridge.recordImmediateResourceHandle(loaded_script_handle)")
    appendLine("\t\t\treturn loaded_script_handle")
    appendLine("\t_kanama_object_handles[resource_handle] = resource")
    appendLine("\t_kanama_bridge.recordImmediateResourceHandle(resource_handle)")
    appendLine("\treturn resource_handle")
    appendLine()
    appendLine("func _kanama_signal_emit(args: Array) -> int:")
    appendLine("\tvar object_handle := int(args[0])")
    appendLine(
      "\tvar value: Object = self if object_handle == _kanama_handle else _kanama_object_handles.get(object_handle)"
    )
    appendLine("\tvar result := ERR_INVALID_PARAMETER")
    appendLine("\tif value != null:")
    appendLine("\t\tif args.size() == 2:")
    appendLine("\t\t\tresult = value.emit_signal(StringName(String(args[1])))")
    appendLine("\t\telse:")
    appendLine("\t\t\tresult = value.emit_signal(StringName(String(args[1])), int(args[2]))")
    appendLine("\t_kanama_bridge.recordImmediateSignalResult(result)")
    appendLine("\treturn result")
    appendLine()
    appendLine("func _kanama_resource_release(args: Array) -> int:")
    appendLine("\tvar resource_handle := int(args[0])")
    appendLine("\tvar released := int(_kanama_object_handles.erase(resource_handle))")
    appendLine("\t_kanama_bridge.recordImmediateResourceRelease(released)")
    appendLine("\treturn released")
    appendLine()
    appendLine("func _kanama_construct_object(args: Array) -> int:")
    appendLine("\tvar object_handle := int(args[0])")
    appendLine("\tvar requested_class := String(args[1])")
    appendLine("\tvar value: Object = ClassDB.instantiate(requested_class)")
    appendLine("\t# Nodes and Resources both construct here (MeshLibrary is a Resource); only a")
    appendLine("\t# failed instantiation refuses the proposed handle.")
    appendLine("\tif value == null:")
    appendLine("\t\t_kanama_bridge.recordImmediateConstructHandle(0)")
    appendLine("\t\treturn 0")
    appendLine("\t_kanama_object_handles[object_handle] = value")
    appendLine("\t_kanama_bridge.recordImmediateConstructHandle(object_handle)")
    appendLine("\treturn object_handle")
    appendLine()
    appendLine("func _kanama_node_lookup(args: Array) -> int:")
    appendLine("\tvar receiver_handle := int(args[0])")
    appendLine("\tvar result_handle := int(args[1])")
    appendLine(
      "\tvar receiver: Node = self if receiver_handle == _kanama_handle else _kanama_object_handles.get(receiver_handle) as Node"
    )
    appendLine(
      "\tvar value: Node = null if receiver == null else receiver.get_node_or_null(String(args[2]))"
    )
    appendLine("\tif value == null:")
    appendLine("\t\t_kanama_bridge.recordImmediateObjectHandle(0)")
    appendLine("\t\treturn 0")
    appendLine("\tvar script_handle := 0")
    appendLine("\tif value.has_method(\"_kanama_ensure_created\"):")
    appendLine("\t\tscript_handle = int(value.call(\"_kanama_ensure_created\"))")
    appendLine("\tif script_handle != 0:")
    appendLine("\t\t# Register locally too: any handle this proxy returns must be resolvable")
    appendLine("\t\t# in this proxy's dictionary (cross-script signal connects resolve here).")
    appendLine("\t\t_kanama_object_handles[script_handle] = value")
    appendLine("\t\t_kanama_bridge.recordImmediateObjectHandle(script_handle)")
    appendLine("\t\treturn script_handle")
    appendLine("\tfor existing_handle in _kanama_object_handles:")
    appendLine("\t\tif is_same(_kanama_object_handles[existing_handle], value):")
    appendLine("\t\t\t_kanama_bridge.recordImmediateObjectHandle(int(existing_handle))")
    appendLine("\t\t\treturn int(existing_handle)")
    appendLine("\t_kanama_object_handles[result_handle] = value")
    appendLine("\tif value is Node2D:")
    appendLine("\t\tvar node_2d := value as Node2D")
    appendLine(
      "\t\t_kanama_bridge.refreshNode2DSnapshot(result_handle, node_2d.position.x, node_2d.position.y, node_2d.scale.x, node_2d.scale.y, node_2d.modulate.r, node_2d.modulate.g, node_2d.modulate.b, node_2d.modulate.a, node_2d.rotation)"
    )
    appendLine("\tif value is Node3D:")
    appendLine("\t\tvar node_3d := value as Node3D")
    appendLine(
      "\t\t_kanama_bridge.refreshNode3DSnapshot(result_handle, node_3d.position.x, node_3d.position.y, node_3d.position.z, node_3d.rotation.x, node_3d.rotation.y, node_3d.rotation.z, node_3d.scale.x, node_3d.scale.y, node_3d.scale.z)"
    )
    appendLine("\tif value is Control:")
    appendLine("\t\t# Controls are CanvasItems but not Node2Ds: seed the mirrored canvas snapshot")
    appendLine("\t\t# so modulate reads (HUD fades) resolve without a prior write.")
    appendLine("\t\tvar control := value as Control")
    appendLine(
      "\t\t_kanama_bridge.refreshNode2DSnapshot(result_handle, control.position.x, control.position.y, control.scale.x, control.scale.y, control.modulate.r, control.modulate.g, control.modulate.b, control.modulate.a, control.rotation)"
    )
    appendLine("\tif value is RayCast3D:")
    appendLine("\t\tvar ray := value as RayCast3D")
    appendLine(
      "\t\t_kanama_bridge.refreshRayTargetSnapshot(result_handle, ray.target_position.x, ray.target_position.y, ray.target_position.z)"
    )
    appendLine("\t_kanama_bridge.recordImmediateObjectHandle(result_handle)")
    appendLine("\treturn result_handle")
    appendLine()
    appendLine("func _kanama_packed_scene_instantiate(args: Array) -> int:")
    appendLine("\tvar resource_handle := int(args[0])")
    appendLine("\tvar proposed_handle := int(args[1])")
    appendLine("\tvar scene := _kanama_object_handles.get(resource_handle) as PackedScene")
    appendLine("\tvar value: Node = null if scene == null else scene.instantiate(int(args[2]))")
    appendLine("\tif value == null:")
    appendLine("\t\t_kanama_bridge.recordImmediateObjectHandle(0)")
    appendLine("\t\treturn 0")
    appendLine("\tvar result_handle := proposed_handle")
    appendLine("\tif value.has_method(\"_kanama_ensure_created\"):")
    appendLine("\t\tresult_handle = int(value.call(\"_kanama_ensure_created\"))")
    appendLine("\tif result_handle == 0:")
    appendLine("\t\tvalue.queue_free()")
    appendLine("\t\t_kanama_bridge.recordImmediateObjectHandle(0)")
    appendLine("\t\treturn 0")
    appendLine("\t_kanama_object_handles[result_handle] = value")
    appendLine("\tif value is Node2D:")
    appendLine("\t\tvar node_2d := value as Node2D")
    appendLine(
      "\t\t_kanama_bridge.refreshNode2DSnapshot(result_handle, node_2d.position.x, node_2d.position.y, node_2d.scale.x, node_2d.scale.y, node_2d.modulate.r, node_2d.modulate.g, node_2d.modulate.b, node_2d.modulate.a, node_2d.rotation)"
    )
    appendLine("\tif value is Node3D:")
    appendLine("\t\tvar node_3d := value as Node3D")
    appendLine(
      "\t\t_kanama_bridge.refreshNode3DSnapshot(result_handle, node_3d.position.x, node_3d.position.y, node_3d.position.z, node_3d.rotation.x, node_3d.rotation.y, node_3d.rotation.z, node_3d.scale.x, node_3d.scale.y, node_3d.scale.z)"
    )
    appendLine("\t_kanama_bridge.recordImmediateObjectHandle(result_handle)")
    appendLine("\treturn result_handle")
    appendLine()
    appendLine("func _kanama_noargs_object(args: Array) -> int:")
    appendLine("\tvar opcode := int(args[0])")
    appendLine("\tvar receiver_handle := int(args[1])")
    appendLine("\tvar result_handle := int(args[2])")
    appendLine(
      "\tvar receiver: Object = self if receiver_handle == _kanama_handle else _kanama_object_handles.get(receiver_handle)"
    )
    appendLine("\tvar value: Object = null")
    appendLine("\tif opcode == 19 and receiver != null:")
    appendLine("\t\tvalue = receiver.get_viewport()")
    appendLine("\telif opcode == 36 and receiver != null:")
    appendLine("\t\tvalue = receiver.create_tween()")
    appendLine("\telif opcode == 51 and receiver != null:")
    appendLine("\t\tvalue = receiver.get_tree()")
    appendLine("\telif opcode == 71 and receiver is AnimatedSprite2D:")
    appendLine("\t\tvalue = (receiver as AnimatedSprite2D).sprite_frames")
    appendLine("\telif opcode == 73 and receiver != null:")
    appendLine("\t\tvalue = receiver.get_parent()")
    appendLine("\telif opcode == 81 and receiver is WorldEnvironment:")
    appendLine("\t\tvalue = (receiver as WorldEnvironment).environment")
    appendLine("\telif opcode == 194 and receiver is Viewport:")
    appendLine("\t\tvalue = (receiver as Viewport).get_camera_3d()")
    appendLine("\telif opcode == 212 and receiver is PackedScene:")
    appendLine("\t\tvalue = (receiver as PackedScene).get_state()")
    appendLine("\telif opcode == 225 and receiver is MeshInstance3D:")
    appendLine("\t\tvalue = (receiver as MeshInstance3D).mesh")
    appendLine("\telif opcode == 112 and receiver is KinematicCollision3D:")
    appendLine("\t\tvalue = (receiver as KinematicCollision3D).get_collider()")
    appendLine("\telif opcode == 114 and receiver is Node:")
    appendLine("\t\tvalue = (receiver as Node).duplicate()")
    appendLine("\telif opcode == 122 and receiver is RayCast3D:")
    appendLine("\t\tvalue = (receiver as RayCast3D).get_collider()")
    appendLine("\telif opcode == 246 and receiver is Material:")
    appendLine("\t\tvalue = (receiver as Material).next_pass")
    appendLine("\telif opcode == 250 and receiver is SceneTree:")
    appendLine("\t\tvalue = (receiver as SceneTree).root")
    appendLine("\tif value == null:")
    appendLine("\t\t_kanama_bridge.recordImmediateObjectHandle(0)")
    appendLine("\t\treturn 0")
    appendLine("\t# get_collider returns a live scene node: resolve to its existing script or")
    appendLine("\t# browser handle (node-lookup rule) instead of registering a duplicate.")
    appendLine("\tif opcode == 112 or opcode == 122:")
    appendLine("\t\tif value.has_method(\"_kanama_ensure_created\"):")
    appendLine("\t\t\tvar collider_script_handle := int(value.call(\"_kanama_ensure_created\"))")
    appendLine("\t\t\tif collider_script_handle != 0:")
    appendLine("\t\t\t\t_kanama_object_handles[collider_script_handle] = value")
    appendLine("\t\t\t\t_kanama_bridge.recordImmediateObjectHandle(collider_script_handle)")
    appendLine("\t\t\t\treturn collider_script_handle")
    appendLine("\t\tfor existing_handle in _kanama_object_handles:")
    appendLine("\t\t\tif is_same(_kanama_object_handles[existing_handle], value):")
    appendLine("\t\t\t\t_kanama_bridge.recordImmediateObjectHandle(int(existing_handle))")
    appendLine("\t\t\t\treturn int(existing_handle)")
    appendLine("\t_kanama_object_handles[result_handle] = value")
    appendLine("\tif value is Node3D:")
    appendLine("\t\tvar node_3d := value as Node3D")
    appendLine(
      "\t\t_kanama_bridge.refreshNode3DSnapshot(result_handle, node_3d.position.x, node_3d.position.y, node_3d.position.z, node_3d.rotation.x, node_3d.rotation.y, node_3d.rotation.z, node_3d.scale.x, node_3d.scale.y, node_3d.scale.z)"
    )
    appendLine("\tif value is SpriteFrames:")
    appendLine(
      "\t\t_kanama_bridge.loadAnimationNames(result_handle, \"\\n\".join((value as SpriteFrames).get_animation_names()))"
    )
    appendLine("\tif value is Tween:")
    appendLine("\t\t_kanama_tween_children[result_handle] = []")
    appendLine("\t\t_kanama_tween_targets[result_handle] = []")
    appendLine(
      "\t\t(value as Tween).finished.connect(_kanama_tween_finished.bind(result_handle), CONNECT_ONE_SHOT)"
    )
    appendLine("\tif value is Viewport:")
    appendLine("\t\tvar viewport_rect := (value as Viewport).get_visible_rect()")
    appendLine(
      "\t\t_kanama_bridge.refreshViewportRectSnapshot(result_handle, viewport_rect.position.x, viewport_rect.position.y, viewport_rect.size.x, viewport_rect.size.y)"
    )
    appendLine("\t_kanama_bridge.recordImmediateObjectHandle(result_handle)")
    appendLine("\treturn result_handle")
    appendLine()
    appendLine("func _kanama_tween_call(args: Array) -> int:")
    appendLine("\tvar opcode := int(args[0])")
    appendLine("\tvar receiver_handle := int(args[1])")
    appendLine("\tvar receiver: Object = _kanama_object_handles.get(receiver_handle)")
    appendLine("\tif opcode == 37 and receiver is Tween:")
    appendLine("\t\t(receiver as Tween).kill()")
    appendLine("\t\t_kanama_release_tween(receiver_handle)")
    appendLine("\t\t_kanama_bridge.recordImmediateLongResult(1)")
    appendLine("\t\treturn 1")
    appendLine("\tvar result_handle := 0")
    appendLine("\tif opcode == 38 and receiver is Tween:")
    appendLine("\t\t(receiver as Tween).set_parallel(bool(args[2]))")
    appendLine("\t\tresult_handle = receiver_handle")
    appendLine("\telif (opcode == 39 or opcode == 40 or opcode == 136) and receiver is Tween:")
    appendLine("\t\tvar proposed_handle := int(args[2])")
    appendLine("\t\tvar target_handle := int(args[3])")
    appendLine(
      "\t\tvar target: Object = self if target_handle == _kanama_handle else _kanama_object_handles.get(target_handle)"
    )
    appendLine("\t\tvar tweener: PropertyTweener = null")
    appendLine("\t\tif target != null and opcode == 39:")
    appendLine(
      "\t\t\ttweener = (receiver as Tween).tween_property(target, NodePath(String(args[4])), Vector2(float(args[5]), float(args[6])), float(args[7]))"
    )
    appendLine("\t\telif target != null and opcode == 40:")
    appendLine(
      "\t\t\ttweener = (receiver as Tween).tween_property(target, NodePath(String(args[4])), Color(float(args[5]), float(args[6]), float(args[7]), float(args[8])), float(args[9]))"
    )
    appendLine("\t\telif target != null and opcode == 136:")
    appendLine(
      "\t\t\ttweener = (receiver as Tween).tween_property(target, NodePath(String(args[4])), Vector3(float(args[5]), float(args[6]), float(args[7])), float(args[8]))"
    )
    appendLine("\t\tif tweener != null:")
    appendLine("\t\t\t_kanama_object_handles[proposed_handle] = tweener")
    appendLine("\t\t\tvar children: Array = _kanama_tween_children.get(receiver_handle, [])")
    appendLine("\t\t\tchildren.append(proposed_handle)")
    appendLine("\t\t\t_kanama_tween_children[receiver_handle] = children")
    appendLine("\t\t\tvar targets: Array = _kanama_tween_targets.get(receiver_handle, [])")
    appendLine("\t\t\tif not targets.has(target_handle):")
    appendLine("\t\t\t\ttargets.append(target_handle)")
    appendLine("\t\t\t_kanama_tween_targets[receiver_handle] = targets")
    appendLine("\t\t\tresult_handle = proposed_handle")
    appendLine("\telif opcode == 41 and receiver is PropertyTweener:")
    appendLine("\t\t(receiver as PropertyTweener).set_trans(int(args[2]))")
    appendLine("\t\tresult_handle = receiver_handle")
    appendLine("\telif opcode == 42 and receiver is PropertyTweener:")
    appendLine("\t\t(receiver as PropertyTweener).set_ease(int(args[2]))")
    appendLine("\t\tresult_handle = receiver_handle")
    appendLine("\telif opcode == 134 and receiver is Tween:")
    appendLine(
      "\t\tvar bind_target: Object = self if int(args[2]) == _kanama_handle else _kanama_object_handles.get(int(args[2]))"
    )
    appendLine("\t\tif bind_target is Node:")
    appendLine("\t\t\t(receiver as Tween).bind_node(bind_target as Node)")
    appendLine("\t\t\tresult_handle = receiver_handle")
    appendLine("\telif opcode == 135 and receiver is Tween:")
    appendLine("\t\t(receiver as Tween).set_ease(int(args[2]))")
    appendLine("\t\tresult_handle = receiver_handle")
    appendLine("\telif opcode == 137 and receiver is Tween:")
    appendLine("\t\tvar cb_proposed := int(args[2])")
    appendLine(
      "\t\tvar cb_target: Object = self if int(args[3]) == _kanama_handle else _kanama_object_handles.get(int(args[3]))"
    )
    appendLine("\t\tif cb_target != null:")
    appendLine(
      "\t\t\tvar callback_tweener := (receiver as Tween).tween_callback(Callable(cb_target, StringName(String(args[4]))))"
    )
    appendLine("\t\t\tif callback_tweener != null:")
    appendLine("\t\t\t\t_kanama_object_handles[cb_proposed] = callback_tweener")
    appendLine("\t\t\t\tvar cb_children: Array = _kanama_tween_children.get(receiver_handle, [])")
    appendLine("\t\t\t\tcb_children.append(cb_proposed)")
    appendLine("\t\t\t\t_kanama_tween_children[receiver_handle] = cb_children")
    appendLine("\t\t\t\tresult_handle = cb_proposed")
    appendLine("\telif opcode == 133:")
    appendLine(
      "\t\tvar child_parent: Object = self if receiver_handle == _kanama_handle else _kanama_object_handles.get(receiver_handle)"
    )
    appendLine("\t\tif child_parent is Node:")
    appendLine("\t\t\tvar proposed_child := int(args[2])")
    appendLine("\t\t\tvar child_node := (child_parent as Node).get_child(int(args[3]))")
    appendLine("\t\t\tif child_node != null:")
    appendLine("\t\t\t\tif child_node.has_method(\"_kanama_ensure_created\"):")
    appendLine("\t\t\t\t\tvar child_script := int(child_node.call(\"_kanama_ensure_created\"))")
    appendLine("\t\t\t\t\tif child_script != 0:")
    appendLine("\t\t\t\t\t\t_kanama_object_handles[child_script] = child_node")
    appendLine("\t\t\t\t\t\tresult_handle = child_script")
    appendLine("\t\t\t\tif result_handle == 0:")
    appendLine("\t\t\t\t\tfor child_existing in _kanama_object_handles:")
    appendLine("\t\t\t\t\t\tif is_same(_kanama_object_handles[child_existing], child_node):")
    appendLine("\t\t\t\t\t\t\tresult_handle = int(child_existing)")
    appendLine("\t\t\t\t\t\t\tbreak")
    appendLine("\t\t\t\tif result_handle == 0:")
    appendLine("\t\t\t\t\t_kanama_object_handles[proposed_child] = child_node")
    appendLine("\t\t\t\t\tresult_handle = proposed_child")
    appendLine("\t\t\t\t# Seed the mirrored transform snapshots like a node lookup does: the")
    appendLine("\t\t\t\t# caller may read the child's transform straight away (spawn points).")
    appendLine("\t\t\t\tif child_node is Node2D:")
    appendLine("\t\t\t\t\tvar child_2d := child_node as Node2D")
    appendLine(
      "\t\t\t\t\t_kanama_bridge.refreshNode2DSnapshot(result_handle, child_2d.position.x, child_2d.position.y, child_2d.scale.x, child_2d.scale.y, child_2d.modulate.r, child_2d.modulate.g, child_2d.modulate.b, child_2d.modulate.a, child_2d.rotation)"
    )
    appendLine("\t\t\t\tif child_node is Node3D:")
    appendLine("\t\t\t\t\tvar child_3d := child_node as Node3D")
    appendLine(
      "\t\t\t\t\t_kanama_bridge.refreshNode3DSnapshot(result_handle, child_3d.position.x, child_3d.position.y, child_3d.position.z, child_3d.rotation.x, child_3d.rotation.y, child_3d.rotation.z, child_3d.scale.x, child_3d.scale.y, child_3d.scale.z)"
    )
    appendLine("\telif opcode == 166:")
    appendLine(
      "\t\tvar sweep_body: Object = self if receiver_handle == _kanama_handle else _kanama_object_handles.get(receiver_handle)"
    )
    appendLine("\t\tif sweep_body is PhysicsBody3D:")
    appendLine("\t\t\tvar proposed_sweep_handle := int(args[2])")
    appendLine("\t\t\tvar motion_parts := String(args[3]).split_floats(\"\\u001f\")")
    appendLine(
      "\t\t\tvar sweep := (sweep_body as PhysicsBody3D).move_and_collide(Vector3(motion_parts[0], motion_parts[1], motion_parts[2]))"
    )
    appendLine("\t\t\tif sweep != null:")
    appendLine("\t\t\t\t_kanama_object_handles[proposed_sweep_handle] = sweep")
    appendLine("\t\t\t\tresult_handle = proposed_sweep_handle")
    appendLine("\t\t\tif sweep_body is Node3D:")
    appendLine("\t\t\t\tvar swept := sweep_body as Node3D")
    appendLine(
      "\t\t\t\t_kanama_bridge.refreshNode3DSnapshot(receiver_handle, swept.position.x, swept.position.y, swept.position.z, swept.rotation.x, swept.rotation.y, swept.rotation.z, swept.scale.x, swept.scale.y, swept.scale.z)"
    )
    appendLine("\telif opcode == 174:")
    appendLine(
      "\t\tvar shape_cast: Object = self if receiver_handle == _kanama_handle else _kanama_object_handles.get(receiver_handle)"
    )
    appendLine("\t\tif shape_cast is ShapeCast3D:")
    appendLine(
      "\t\t\tvar shape_hit: Object = (shape_cast as ShapeCast3D).get_collider(int(args[3]))"
    )
    appendLine("\t\t\tif shape_hit != null:")
    appendLine("\t\t\t\tif shape_hit.has_method(\"_kanama_ensure_created\"):")
    appendLine("\t\t\t\t\tvar shape_hit_script := int(shape_hit.call(\"_kanama_ensure_created\"))")
    appendLine("\t\t\t\t\tif shape_hit_script != 0:")
    appendLine("\t\t\t\t\t\t_kanama_object_handles[shape_hit_script] = shape_hit")
    appendLine("\t\t\t\t\t\tresult_handle = shape_hit_script")
    appendLine("\t\t\t\tif result_handle == 0:")
    appendLine("\t\t\t\t\tfor existing_hit_handle in _kanama_object_handles:")
    appendLine("\t\t\t\t\t\tif is_same(_kanama_object_handles[existing_hit_handle], shape_hit):")
    appendLine("\t\t\t\t\t\t\tresult_handle = int(existing_hit_handle)")
    appendLine("\t\t\t\t\t\t\tbreak")
    appendLine("\t\t\t\tif result_handle == 0:")
    appendLine("\t\t\t\t\t_kanama_object_handles[int(args[2])] = shape_hit")
    appendLine("\t\t\t\t\tresult_handle = int(args[2])")
    appendLine("\telif opcode == 192 and receiver is Tween:")
    appendLine("\t\tvar method_proposed := int(args[2])")
    appendLine(
      "\t\tvar method_target: Object = self if int(args[3]) == _kanama_handle else _kanama_object_handles.get(int(args[3]))"
    )
    appendLine("\t\tif method_target != null:")
    appendLine(
      "\t\t\tvar method_tweener := (receiver as Tween).tween_method(Callable(method_target, StringName(String(args[4]))), float(args[5]), float(args[6]), float(args[7]))"
    )
    appendLine("\t\t\tif method_tweener != null:")
    appendLine("\t\t\t\t_kanama_object_handles[method_proposed] = method_tweener")
    appendLine(
      "\t\t\t\tvar method_children: Array = _kanama_tween_children.get(receiver_handle, [])"
    )
    appendLine("\t\t\t\tmethod_children.append(method_proposed)")
    appendLine("\t\t\t\t_kanama_tween_children[receiver_handle] = method_children")
    appendLine("\t\t\t\tresult_handle = method_proposed")
    appendLine("\telif opcode == 111:")
    appendLine(
      "\t\tvar slide_body: Object = self if receiver_handle == _kanama_handle else _kanama_object_handles.get(receiver_handle)"
    )
    appendLine("\t\tif slide_body is CharacterBody3D:")
    appendLine("\t\t\tvar proposed_collision_handle := int(args[2])")
    appendLine(
      "\t\t\tvar collision := (slide_body as CharacterBody3D).get_slide_collision(int(args[3]))"
    )
    appendLine("\t\t\tif collision != null:")
    appendLine("\t\t\t\t_kanama_object_handles[proposed_collision_handle] = collision")
    appendLine("\t\t\t\tresult_handle = proposed_collision_handle")
    appendLine("\t_kanama_bridge.recordImmediateObjectHandle(result_handle)")
    appendLine("\treturn int(result_handle != 0)")
    appendLine()
    appendLine("func _kanama_tween_finished(tween_handle: int) -> void:")
    appendLine("\t_kanama_release_tween(tween_handle)")
    appendLine()
    appendLine("func _kanama_release_tween(tween_handle: int) -> void:")
    appendLine("\tif not _kanama_tween_children.has(tween_handle):")
    appendLine("\t\treturn")
    appendLine("\tvar targets: Array = _kanama_tween_targets.get(tween_handle, [])")
    appendLine("\tfor target_handle in targets:")
    appendLine(
      "\t\tvar target: Object = self if int(target_handle) == _kanama_handle else _kanama_object_handles.get(int(target_handle))"
    )
    appendLine("\t\tif target is Node2D:")
    appendLine("\t\t\tvar node_2d := target as Node2D")
    appendLine(
      "\t\t\t_kanama_bridge.refreshNode2DSnapshot(int(target_handle), node_2d.position.x, node_2d.position.y, node_2d.scale.x, node_2d.scale.y, node_2d.modulate.r, node_2d.modulate.g, node_2d.modulate.b, node_2d.modulate.a, node_2d.rotation)"
    )
    appendLine("\tvar children: Array = _kanama_tween_children.get(tween_handle, [])")
    appendLine("\tfor child_handle in children:")
    appendLine("\t\t_kanama_object_handles.erase(int(child_handle))")
    appendLine("\t_kanama_object_handles.erase(tween_handle)")
    appendLine("\t_kanama_tween_children.erase(tween_handle)")
    appendLine("\t_kanama_tween_targets.erase(tween_handle)")
    appendLine("\t_kanama_bridge.releaseTweenGraph(tween_handle)")
    appendLine()
    appendLine("func _kanama_input_cursor(args: Array) -> int:")
    appendLine("\tvar resource_handle := int(args[0])")
    appendLine(
      "\tvar texture: Texture2D = null if resource_handle == 0 else _kanama_object_handles.get(resource_handle) as Texture2D"
    )
    appendLine("\tif resource_handle != 0 and texture == null:")
    appendLine("\t\tpush_error(\"Unknown Kanama Web cursor texture handle: %d\" % resource_handle)")
    appendLine("\t\treturn 0")
    appendLine(
      "\tInput.set_custom_mouse_cursor(texture, int(args[1]), Vector2(float(args[2]), float(args[3])))"
    )
    appendLine("\treturn 1")
    appendLine()
    appendLine("func _kanama_connect(args: Array) -> int:")
    appendLine("\tvar source_handle := int(args[0])")
    appendLine("\tvar target_handle := int(args[2])")
    appendLine(
      "\tvar source: Object = self if source_handle == _kanama_handle else _kanama_object_handles.get(source_handle)"
    )
    appendLine(
      "\tvar target: Object = self if target_handle == _kanama_handle else _kanama_object_handles.get(target_handle)"
    )
    appendLine("\tvar result := ERR_INVALID_PARAMETER")
    appendLine("\tif source != null and target != null:")
    appendLine("\t\tvar callable := Callable(target, StringName(String(args[3])))")
    appendLine("\t\tif args.size() > 5:")
    appendLine("\t\t\tcallable = callable.bind(int(args[5]))")
    appendLine("\t\tif int(args[4]) == -1:")
    appendLine("\t\t\t# flags -1 selects disconnect for the same bound callable shape.")
    appendLine("\t\t\tsource.disconnect(StringName(String(args[1])), callable)")
    appendLine("\t\t\tresult = OK")
    appendLine("\t\telse:")
    appendLine("\t\t\tresult = source.connect(StringName(String(args[1])), callable, int(args[4]))")
    appendLine("\t_kanama_bridge.recordImmediateConnectResult(result)")
    appendLine("\treturn result")
    appendLine()
    appendLine("func $SIGNAL_DISPATCH_ZERO(callback_id: int) -> void:")
    appendLine("\t_kanama_bridge.dispatchSignal0(_kanama_handle, callback_id)")
    appendLine()
    appendLine("func $SIGNAL_DISPATCH_ONE(arg: Variant, callback_id: int) -> void:")
    appendLine(
      "\t# Task 80 slice 2: the emitted scalar crosses PACKED, so a typed GodotSignal.connect*"
    )
    appendLine(
      "\t# overload receives it. A zero-argument Kotlin lambda ignores the payload as before."
    )
    appendLine(
      "\t_kanama_bridge.dispatchSignal1(_kanama_handle, callback_id, $SIGNAL_PACK_ARG(arg))"
    )
    appendLine()
    appendLine("func $SIGNAL_PACK_ARG(arg: Variant) -> String:")
    appendLine("\tmatch typeof(arg):")
    appendLine("\t\tTYPE_NIL:")
    appendLine("\t\t\treturn \"\"")
    appendLine("\t\tTYPE_BOOL:")
    appendLine("\t\t\treturn \"1\" if arg else \"0\"")
    appendLine("\t\tTYPE_VECTOR2, TYPE_VECTOR2I:")
    appendLine("\t\t\treturn \"%s,%s\" % [arg.x, arg.y]")
    appendLine("\t\tTYPE_VECTOR3, TYPE_VECTOR3I:")
    appendLine("\t\t\treturn \"%s,%s,%s\" % [arg.x, arg.y, arg.z]")
    appendLine("\t\t_:")
    appendLine("\t\t\treturn str(arg)")
    appendLine()
    appendLine("func $PACKED_ARG_PACK_TEXT(value: String) -> String:")
    appendLine(
      "\t# Task 80 slice 3: %-escape a text argument so its payload cannot split the packed"
    )
    appendLine("\t# argument list. Same escaping as the generic-call transport: % first.")
    appendLine("\treturn value.replace(\"%\", \"%25\").replace(\"\\u001f\", \"%1F\")")
    appendLine()
    appendLine("func $PACKED_ARG_PACK_OBJECT(value: Object, transient_handles: Array[int]) -> int:")
    appendLine("\t# Task 80 slice 3: one object argument as a bridge handle id. A Kanama-scripted")
    appendLine("\t# object crosses as its SCRIPT handle (so Kotlin can resolve its instance);")
    appendLine("\t# anything else rides a transient handle the caller releases after the call.")
    appendLine("\tif value == null:")
    appendLine("\t\treturn 0")
    appendLine("\tif value.has_method(\"_kanama_ensure_created\"):")
    appendLine("\t\tvar script_handle := int(value.call(\"_kanama_ensure_created\"))")
    appendLine("\t\tif script_handle != 0:")
    appendLine("\t\t\treturn script_handle")
    appendLine(
      "\tvar packed_handle := int(_kanama_bridge.allocateTransientObjectHandle(_kanama_handle))"
    )
    appendLine("\t_kanama_object_handles[packed_handle] = value")
    appendLine("\ttransient_handles.append(packed_handle)")
    appendLine("\treturn packed_handle")
    appendLine()
    appendLine("func $SIGNAL_DISPATCH_OBJECT(arg: Variant, callback_id: int) -> void:")
    appendLine("\tvar script_arg_handle := 0")
    appendLine("\tif arg != null and arg.has_method(\"_kanama_ensure_created\"):")
    appendLine("\t\tscript_arg_handle = int(arg.call(\"_kanama_ensure_created\"))")
    appendLine("\tif script_arg_handle != 0:")
    appendLine(
      "\t\t_kanama_bridge.dispatchSignalObject(_kanama_handle, callback_id, script_arg_handle)"
    )
    appendLine("\telse:")
    appendLine(
      "\t\tvar arg_handle := int(_kanama_bridge.allocateTransientObjectHandle(_kanama_handle))"
    )
    appendLine("\t\t_kanama_object_handles[arg_handle] = arg")
    appendLine("\t\t_kanama_bridge.dispatchSignalObject(_kanama_handle, callback_id, arg_handle)")
    appendLine("\t\t_kanama_object_handles.erase(arg_handle)")
    appendLine("\t\t_kanama_bridge.releaseTransientObjectHandle(arg_handle)")
    appendLine()
    appendLine("func _kanama_object_query(args: Array) -> int:")
    appendLine("\tvar opcode := int(args[0])")
    appendLine("\tvar object_handle := int(args[1])")
    appendLine(
      "\tvar value: Object = self if object_handle == _kanama_handle else _kanama_object_handles.get(object_handle)"
    )
    appendLine("\tvar result := 0")
    appendLine("\tif value != null:")
    appendLine("\t\tif opcode == 23:")
    appendLine("\t\t\tresult = int(value.is_class(StringName(String(args[2]))))")
    appendLine("\t\telif opcode == 24 and value is InputEvent:")
    appendLine("\t\t\tresult = int((value as InputEvent).is_pressed())")
    appendLine("\t\telif opcode == 25 and value is InputEvent:")
    appendLine("\t\t\tresult = int((value as InputEvent).is_released())")
    appendLine("\t\telif opcode == 26 and value is InputEventMouseButton:")
    appendLine("\t\t\tresult = int((value as InputEventMouseButton).button_index)")
    appendLine("\t\telif opcode == 69:")
    appendLine("\t\t\tresult = int(Input.is_action_pressed(StringName(String(args[2]))))")
    appendLine("\t\telif opcode == 92:")
    appendLine("\t\t\tresult = int(Input.is_action_just_pressed(StringName(String(args[2]))))")
    appendLine("\t\telif opcode == 98 and value is Node:")
    appendLine("\t\t\tresult = int((value as Node).is_in_group(StringName(String(args[2]))))")
    appendLine("\t\telif opcode == 100 and value is SceneTree:")
    appendLine("\t\t\tresult = int((value as SceneTree).reload_current_scene())")
    appendLine("\t\telif opcode == 104:")
    appendLine("\t\t\tvar axis_parts := String(args[2]).split(\"\\u001f\")")
    appendLine(
      "\t\t\tresult = int(round(Input.get_axis(StringName(axis_parts[0]), StringName(axis_parts[1])) * 1000.0))"
    )
    appendLine("\t\telif opcode == 83:")
    appendLine("\t\t\tresult = int(OS.has_feature(String(args[2])))")
    appendLine("\t\telif opcode == 86:")
    appendLine("\t\t\tInput.action_press(StringName(String(args[2])))")
    appendLine("\t\t\tresult = 0")
    appendLine("\t\telif opcode == 87:")
    appendLine("\t\t\tInput.action_release(StringName(String(args[2])))")
    appendLine("\t\t\tresult = 0")
    appendLine("\t\telif opcode == 90 and value is CharacterBody3D:")
    appendLine("\t\t\tresult = int((value as CharacterBody3D).move_and_slide())")
    appendLine("\t\telif opcode == 91 and value is CharacterBody3D:")
    appendLine("\t\t\tresult = int((value as CharacterBody3D).is_on_floor())")
    appendLine("\t\telif opcode == 65 and value is PathFollow2D:")
    appendLine("\t\t\tvar follow := value as PathFollow2D")
    appendLine("\t\t\tfollow.progress_ratio = float(args[2])")
    appendLine(
      "\t\t\t_kanama_bridge.refreshNode2DSnapshot(object_handle, follow.position.x, follow.position.y, follow.scale.x, follow.scale.y, follow.modulate.r, follow.modulate.g, follow.modulate.b, follow.modulate.a, follow.rotation)"
    )
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 106 and value is CanvasItem:")
    appendLine("\t\t\tresult = int((value as CanvasItem).is_visible())")
    appendLine("\t\telif opcode == 107 and value is PathFollow3D:")
    appendLine("\t\t\tvar follow_3d := value as PathFollow3D")
    appendLine("\t\t\tfollow_3d.progress_ratio = float(args[2])")
    appendLine(
      "\t\t\t_kanama_bridge.refreshNode3DSnapshot(object_handle, follow_3d.position.x, follow_3d.position.y, follow_3d.position.z, follow_3d.rotation.x, follow_3d.rotation.y, follow_3d.rotation.z, follow_3d.scale.x, follow_3d.scale.y, follow_3d.scale.z)"
    )
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 108 and value is Node3D:")
    appendLine("\t\t\tvar look_node := value as Node3D")
    appendLine("\t\t\tvar look_parts := String(args[2]).split_floats(\"\\u001f\")")
    appendLine(
      "\t\t\tlook_node.look_at_from_position(Vector3(look_parts[0], look_parts[1], look_parts[2]), Vector3(look_parts[3], look_parts[4], look_parts[5]))"
    )
    appendLine(
      "\t\t\t_kanama_bridge.refreshNode3DSnapshot(object_handle, look_node.position.x, look_node.position.y, look_node.position.z, look_node.rotation.x, look_node.rotation.y, look_node.rotation.z, look_node.scale.x, look_node.scale.y, look_node.scale.z)"
    )
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 109 and value is Node3D:")
    appendLine("\t\t\tvar rotate_node := value as Node3D")
    appendLine("\t\t\trotate_node.rotate_y(float(args[2]))")
    appendLine(
      "\t\t\t_kanama_bridge.refreshNode3DSnapshot(object_handle, rotate_node.position.x, rotate_node.position.y, rotate_node.position.z, rotate_node.rotation.x, rotate_node.rotation.y, rotate_node.rotation.z, rotate_node.scale.x, rotate_node.scale.y, rotate_node.scale.z)"
    )
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 110 and value is CharacterBody3D:")
    appendLine("\t\t\tresult = (value as CharacterBody3D).get_slide_collision_count()")
    appendLine("\t\telif opcode == 116:")
    appendLine(
      "\t\t\tRenderingServer.directional_soft_shadow_filter_set_quality(int(String(args[2])))"
    )
    appendLine("\t\t\tresult = 0")
    appendLine("\t\telif opcode == 117 and value is InputEvent:")
    appendLine(
      "\t\t\tresult = int((value as InputEvent).is_action_pressed(StringName(String(args[2]))))"
    )
    appendLine("\t\telif opcode == 120 and value is RayCast3D:")
    appendLine("\t\t\t(value as RayCast3D).force_raycast_update()")
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 121 and value is RayCast3D:")
    appendLine("\t\t\tresult = int((value as RayCast3D).is_colliding())")
    appendLine("\t\telif opcode == 125 and value is CharacterBody3D:")
    appendLine("\t\t\tresult = int((value as CharacterBody3D).is_on_ceiling())")
    appendLine("\t\telif opcode == 126:")
    appendLine("\t\t\tInput.mouse_mode = int(String(args[2]))")
    appendLine("\t\t\tresult = 0")
    appendLine("\t\telif opcode == 131 and value != null:")
    appendLine("\t\t\tresult = int(value.has_method(StringName(String(args[2]))))")
    appendLine("\t\telif opcode == 139 and value is Timer:")
    appendLine("\t\t\tresult = int((value as Timer).is_stopped())")
    appendLine("\t\telif opcode == 142 and value is Node3D:")
    appendLine("\t\t\tvar global_pos_node := value as Node3D")
    appendLine("\t\t\tvar global_pos_parts := String(args[2]).split_floats(\"\\u001f\")")
    appendLine(
      "\t\t\tglobal_pos_node.global_position = Vector3(global_pos_parts[0], global_pos_parts[1], global_pos_parts[2])"
    )
    appendLine(
      "\t\t\t_kanama_bridge.refreshNode3DSnapshot(object_handle, global_pos_node.position.x, global_pos_node.position.y, global_pos_node.position.z, global_pos_node.rotation.x, global_pos_node.rotation.y, global_pos_node.rotation.z, global_pos_node.scale.x, global_pos_node.scale.y, global_pos_node.scale.z)"
    )
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 143 and value is Node3D:")
    appendLine("\t\t\tvar global_rot_node := value as Node3D")
    appendLine("\t\t\tvar global_rot_parts := String(args[2]).split_floats(\"\\u001f\")")
    appendLine(
      "\t\t\tglobal_rot_node.global_rotation = Vector3(global_rot_parts[0], global_rot_parts[1], global_rot_parts[2])"
    )
    appendLine(
      "\t\t\t_kanama_bridge.refreshNode3DSnapshot(object_handle, global_rot_node.position.x, global_rot_node.position.y, global_rot_node.position.z, global_rot_node.rotation.x, global_rot_node.rotation.y, global_rot_node.rotation.z, global_rot_node.scale.x, global_rot_node.scale.y, global_rot_node.scale.z)"
    )
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 145:")
    appendLine("\t\t\tresult = int(Input.mouse_mode)")
    appendLine("\t\telif opcode == 148 and value != null:")
    appendLine("\t\t\tvar emit_parts := String(args[2]).split(\"\\u001f\")")
    appendLine("\t\t\tvar emit_arg_handle := int(emit_parts[1])")
    appendLine(
      "\t\t\tvar emit_arg: Object = self if emit_arg_handle == _kanama_handle else _kanama_object_handles.get(emit_arg_handle)"
    )
    appendLine("\t\t\tif emit_arg == null:")
    appendLine(
      "\t\t\t\tpush_error(\"Unknown Kanama Web signal argument handle: %d\" % emit_arg_handle)"
    )
    appendLine("\t\t\t\tresult = ERR_INVALID_PARAMETER")
    appendLine("\t\t\telse:")
    appendLine("\t\t\t\tresult = value.emit_signal(StringName(emit_parts[0]), emit_arg)")
    appendLine("\t\telif opcode == 149 and value != null:")
    appendLine("\t\t\tvar get_parts := String(args[2]).split(\"\\u001f\")")
    appendLine("\t\t\tvar got_variant: Variant = value.get(get_parts[0])")
    appendLine("\t\t\tvar got: Object = got_variant if got_variant is Object else null")
    appendLine("\t\t\tif got == null:")
    appendLine("\t\t\t\tresult = 0")
    appendLine("\t\t\telse:")
    appendLine("\t\t\t\tresult = 0")
    appendLine("\t\t\t\tfor existing_handle in _kanama_object_handles:")
    appendLine("\t\t\t\t\tif is_same(_kanama_object_handles[existing_handle], got):")
    appendLine("\t\t\t\t\t\tresult = int(existing_handle)")
    appendLine("\t\t\t\t\t\tbreak")
    appendLine("\t\t\t\tif result == 0:")
    appendLine("\t\t\t\t\tresult = int(get_parts[1])")
    appendLine("\t\t\t\t\t_kanama_object_handles[result] = got")
    appendLine("\t\telif opcode == 184 and value is AnimationMixer:")
    appendLine("\t\t\tvar anim_get_parts := String(args[2]).split(\"\\u001f\")")
    appendLine(
      "\t\t\tvar named_animation: Animation = (value as AnimationMixer).get_animation(StringName(anim_get_parts[0]))"
    )
    appendLine("\t\t\tif named_animation == null:")
    appendLine("\t\t\t\tresult = 0")
    appendLine("\t\t\telse:")
    appendLine("\t\t\t\tresult = 0")
    appendLine("\t\t\t\tfor existing_anim_handle in _kanama_object_handles:")
    appendLine(
      "\t\t\t\t\tif is_same(_kanama_object_handles[existing_anim_handle], named_animation):"
    )
    appendLine("\t\t\t\t\t\tresult = int(existing_anim_handle)")
    appendLine("\t\t\t\t\t\tbreak")
    appendLine("\t\t\t\tif result == 0:")
    appendLine("\t\t\t\t\tresult = int(anim_get_parts[1])")
    appendLine("\t\t\t\t\t_kanama_object_handles[result] = named_animation")
    appendLine("\t\telif opcode == 154 and value is RigidBody3D:")
    appendLine("\t\t\tvar force_parts := String(args[2]).split_floats(\"\\u001f\")")
    appendLine(
      "\t\t\t(value as RigidBody3D).apply_force(Vector3(force_parts[0], force_parts[1], force_parts[2]), Vector3(force_parts[3], force_parts[4], force_parts[5]))"
    )
    appendLine("\t\t\tresult = 1")
    // Opcode 159 was in the contract (and the generated backend REQUIRES it in
    // invokeVector3Vector3Arg's opcode set) since 60f, but this applier arm was never
    // emitted: every Web RigidBody3D.apply_impulse threw "was not applied", and nothing
    // noticed until task 81 fix #3 first drove a bot's damage() on Web.
    appendLine("\t\telif opcode == 159 and value is RigidBody3D:")
    appendLine("\t\t\tvar impulse_parts := String(args[2]).split_floats(\"\\u001f\")")
    appendLine(
      "\t\t\t(value as RigidBody3D).apply_impulse(Vector3(impulse_parts[0], impulse_parts[1], impulse_parts[2]), Vector3(impulse_parts[3], impulse_parts[4], impulse_parts[5]))"
    )
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 155 and value is CollisionObject3D:")
    appendLine("\t\t\tvar mask_parts := String(args[2]).split(\"\\u001f\")")
    appendLine(
      "\t\t\t(value as CollisionObject3D).set_collision_mask_value(int(mask_parts[0]), mask_parts[1] == \"1\")"
    )
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 157 and value is AnimationPlayer:")
    appendLine("\t\t\tresult = int((value as AnimationPlayer).is_playing())")
    appendLine("\t\telif opcode == 160 and value is RigidBody3D:")
    appendLine("\t\t\tvar central_parts := String(args[2]).split_floats(\"\\u001f\")")
    appendLine(
      "\t\t\t(value as RigidBody3D).apply_central_impulse(Vector3(central_parts[0], central_parts[1], central_parts[2]))"
    )
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 163 and value is PhysicsBody3D:")
    appendLine("\t\t\tvar axis_lock_parts := String(args[2]).split(\"\\u001f\")")
    appendLine(
      "\t\t\t(value as PhysicsBody3D).set_axis_lock(int(axis_lock_parts[0]), axis_lock_parts[1] == \"1\")"
    )
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 164 and value is CollisionObject3D:")
    appendLine("\t\t\tvar layer_parts := String(args[2]).split(\"\\u001f\")")
    appendLine(
      "\t\t\t(value as CollisionObject3D).set_collision_layer_value(int(layer_parts[0]), layer_parts[1] == \"1\")"
    )
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 167 and value is Area3D:")
    appendLine("\t\t\tvar overlapping := (value as Area3D).get_overlapping_bodies()")
    appendLine("\t\t\tvar packed_handles := PackedStringArray()")
    appendLine("\t\t\tfor overlapping_body in overlapping:")
    appendLine("\t\t\t\tif overlapping_body.has_method(\"_kanama_ensure_created\"):")
    appendLine(
      "\t\t\t\t\tvar overlap_script := int(overlapping_body.call(\"_kanama_ensure_created\"))"
    )
    appendLine("\t\t\t\t\tif overlap_script != 0:")
    appendLine("\t\t\t\t\t\t_kanama_object_handles[overlap_script] = overlapping_body")
    appendLine("\t\t\t\t\t\tpacked_handles.append(str(overlap_script))")
    appendLine("\t\t\t_kanama_bridge.recordImmediateStringResult(\"\\u001f\".join(packed_handles))")
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 172 and value is ShapeCast3D:")
    appendLine("\t\t\tresult = (value as ShapeCast3D).get_collision_count()")
    appendLine("\t\telif opcode == 175 and value is ShapeCast3D:")
    appendLine("\t\t\tvar cast_target_parts := String(args[2]).split_floats(\"\\u001f\")")
    appendLine(
      "\t\t\t(value as ShapeCast3D).target_position = Vector3(cast_target_parts[0], cast_target_parts[1], cast_target_parts[2])"
    )
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 177 and value is NavigationAgent3D:")
    appendLine("\t\t\tvar nav_target_parts := String(args[2]).split_floats(\"\\u001f\")")
    appendLine(
      "\t\t\t(value as NavigationAgent3D).target_position = Vector3(nav_target_parts[0], nav_target_parts[1], nav_target_parts[2])"
    )
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 179 and value is NavigationAgent3D:")
    appendLine("\t\t\tresult = int((value as NavigationAgent3D).is_target_reached())")
    appendLine("\t\telif opcode == 186:")
    appendLine(
      "\t\t\tresult = int(round(Input.get_action_raw_strength(StringName(String(args[2]))) * 1000.0))"
    )
    appendLine("\t\telif opcode == 187:")
    appendLine(
      "\t\t\tresult = int(round(float(ProjectSettings.get_setting(String(args[2]), 0.0)) * 1000.0))"
    )
    appendLine("\t\telif opcode == 188 and value is AudioStreamPlayer3D:")
    appendLine("\t\t\tresult = int((value as AudioStreamPlayer3D).is_playing())")
    appendLine("\t\telif opcode == 287 and value is Node:")
    appendLine("\t\t\t# Optional child path ('' = the receiver itself): harness drivers reach an")
    appendLine(
      "\t\t\t# un-scripted child AudioStreamPlayer (platformer SoundFootsteps) through the"
    )
    appendLine("\t\t\t# owning script's handle. A missing or non-player target reports 0, which a")
    appendLine("\t\t\t# driver asserting 'playing' reads as false -- wrong paths cannot pass.")
    appendLine("\t\t\tvar audio_query_path := String(args[2])")
    appendLine(
      "\t\t\tvar audio_query_node: Node = (value as Node) if audio_query_path.is_empty() else (value as Node).get_node_or_null(audio_query_path)"
    )
    appendLine("\t\t\tif audio_query_node is AudioStreamPlayer:")
    appendLine("\t\t\t\tresult = int((audio_query_node as AudioStreamPlayer).is_playing())")
    appendLine("\t\telif opcode == 195 and value is Node3D:")
    appendLine("\t\t\tresult = int((value as Node3D).is_visible())")
    appendLine("\t\telif opcode == 196 and value != null:")
    appendLine("\t\t\tvar emit_string_parts := String(args[2]).split(\"\\u001f\")")
    appendLine(
      "\t\t\tresult = value.emit_signal(StringName(emit_string_parts[0]), emit_string_parts[1])"
    )
    appendLine("\t\telif opcode == 198 and value is RigidBody3D:")
    appendLine("\t\t\tvar angular_parts := String(args[2]).split_floats(\"\\u001f\")")
    appendLine(
      "\t\t\t(value as RigidBody3D).angular_velocity = Vector3(angular_parts[0], angular_parts[1], angular_parts[2])"
    )
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 199 and value is AudioStreamPlayer3D:")
    appendLine("\t\t\tresult = int(round((value as AudioStreamPlayer3D).volume_db * 1000.0))")
    appendLine("\t\telif opcode == 201 and value is AudioStreamPlayer3D:")
    appendLine("\t\t\tresult = int(round((value as AudioStreamPlayer3D).pitch_scale * 1000.0))")
    appendLine("\t\telif opcode == 190:")
    appendLine("\t\t\tresult = int(OS.shell_open(String(args[2])))")
    appendLine("\t\telif opcode == 0:")
    appendLine("\t\t\t# Reserved runtime crossing (not a contract opcode): instantiate a Kanama")
    appendLine("\t\t\t# scripted resource by class simple name and hydrate its Kotlin instance.")
    appendLine(
      "\t\t\tvar instantiate_path: String = KanamaWebHandles.proxy_paths.get(String(args[2]), \"\")"
    )
    appendLine("\t\t\tif instantiate_path != \"\":")
    appendLine("\t\t\t\tvar instantiate_script = load(instantiate_path)")
    appendLine(
      "\t\t\t\tvar created = instantiate_script.new() if instantiate_script != null else null"
    )
    appendLine("\t\t\t\tif created != null and created.has_method(\"_kanama_ensure_created\"):")
    appendLine("\t\t\t\t\tresult = int(created.call(\"_kanama_ensure_created\"))")
    appendLine("\t\t\t\t\tif result != 0:")
    appendLine("\t\t\t\t\t\t_kanama_object_handles[result] = created")
    appendLine("\t\telif opcode == 203 and value is GridMap:")
    appendLine("\t\t\tvar cell_parts := String(args[2]).split(\"\\u001f\")")
    appendLine(
      "\t\t\t(value as GridMap).set_cell_item(Vector3i(int(cell_parts[0]), int(cell_parts[1]), int(cell_parts[2])), int(cell_parts[3]), int(cell_parts[4]))"
    )
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 204 and value is GridMap:")
    appendLine("\t\t\tvar get_cell_parts := String(args[2]).split(\"\\u001f\")")
    appendLine(
      "\t\t\tresult = (value as GridMap).get_cell_item(Vector3i(int(get_cell_parts[0]), int(get_cell_parts[1]), int(get_cell_parts[2])))"
    )
    appendLine("\t\telif opcode == 205 and value is GridMap:")
    appendLine("\t\t\tvar orient_parts := String(args[2]).split(\"\\u001f\")")
    appendLine(
      "\t\t\tresult = (value as GridMap).get_cell_item_orientation(Vector3i(int(orient_parts[0]), int(orient_parts[1]), int(orient_parts[2])))"
    )
    appendLine("\t\telif opcode == 206 and value is GridMap:")
    appendLine("\t\t\tvar basis_parts := String(args[2]).split_floats(\"\\u001f\")")
    appendLine(
      "\t\t\tresult = (value as GridMap).get_orthogonal_index_from_basis(Basis(Vector3(basis_parts[0], basis_parts[1], basis_parts[2]), Vector3(basis_parts[3], basis_parts[4], basis_parts[5]), Vector3(basis_parts[6], basis_parts[7], basis_parts[8])))"
    )
    appendLine("\t\telif opcode == 207 and value is GridMap:")
    appendLine("\t\t\t(value as GridMap).clear()")
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 208 and value is GridMap:")
    appendLine("\t\t\tvar used_cells := (value as GridMap).get_used_cells()")
    appendLine("\t\t\tvar packed_cells := PackedStringArray()")
    appendLine("\t\t\tfor used_cell in used_cells:")
    appendLine(
      "\t\t\t\tpacked_cells.append(\"%d,%d,%d\" % [used_cell.x, used_cell.y, used_cell.z])"
    )
    appendLine("\t\t\t_kanama_bridge.recordImmediateStringResult(\"\\u001f\".join(packed_cells))")
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 210 and value is MeshLibrary:")
    appendLine("\t\t\tvar item_mesh_parts := String(args[2]).split(\"\\u001f\")")
    appendLine(
      "\t\t\tvar item_mesh: Mesh = _kanama_object_handles.get(int(item_mesh_parts[1])) as Mesh"
    )
    appendLine("\t\t\tif item_mesh != null:")
    appendLine("\t\t\t\t(value as MeshLibrary).set_item_mesh(int(item_mesh_parts[0]), item_mesh)")
    appendLine("\t\t\t\tresult = 1")
    appendLine("\t\telif opcode == 211 and value is MeshLibrary:")
    appendLine("\t\t\tvar item_transform_parts := String(args[2]).split_floats(\"\\u001f\")")
    appendLine(
      "\t\t\tvar item_basis := Basis(Vector3(item_transform_parts[1], item_transform_parts[2], item_transform_parts[3]), Vector3(item_transform_parts[4], item_transform_parts[5], item_transform_parts[6]), Vector3(item_transform_parts[7], item_transform_parts[8], item_transform_parts[9]))"
    )
    appendLine(
      "\t\t\t(value as MeshLibrary).set_item_mesh_transform(int(item_transform_parts[0]), Transform3D(item_basis, Vector3(item_transform_parts[10], item_transform_parts[11], item_transform_parts[12])))"
    )
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 213 and value is SceneState:")
    appendLine("\t\t\tresult = (value as SceneState).get_node_count()")
    appendLine("\t\telif opcode == 214 and value is SceneState:")
    appendLine(
      "\t\t\t_kanama_bridge.recordImmediateStringResult(String((value as SceneState).get_node_type(int(String(args[2])))))"
    )
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 215 and value is SceneState:")
    appendLine("\t\t\tresult = (value as SceneState).get_node_property_count(int(String(args[2])))")
    appendLine("\t\telif opcode == 216 and value is SceneState:")
    appendLine("\t\t\tvar prop_name_parts := String(args[2]).split(\"\\u001f\")")
    appendLine(
      "\t\t\t_kanama_bridge.recordImmediateStringResult(String((value as SceneState).get_node_property_name(int(prop_name_parts[0]), int(prop_name_parts[1]))))"
    )
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 217 and value is SceneState:")
    appendLine("\t\t\tvar prop_value_parts := String(args[2]).split(\"\\u001f\")")
    appendLine(
      "\t\t\tvar prop_variant: Variant = (value as SceneState).get_node_property_value(int(prop_value_parts[0]), int(prop_value_parts[1]))"
    )
    appendLine("\t\t\tvar prop_object: Object = prop_variant if prop_variant is Object else null")
    appendLine("\t\t\tif prop_object != null:")
    appendLine("\t\t\t\tresult = int(prop_value_parts[2])")
    appendLine("\t\t\t\t_kanama_object_handles[result] = prop_object")
    appendLine("\t\telif opcode == 221:")
    appendLine("\t\t\tresult = int(Input.is_action_just_released(StringName(String(args[2]))))")
    appendLine("\t\telif opcode == 222 and value is Resource:")
    appendLine("\t\t\tvar duplicate_parts := String(args[2]).split(\"\\u001f\")")
    appendLine(
      "\t\t\tvar duplicated: Resource = (value as Resource).duplicate(duplicate_parts[0] == \"1\")"
    )
    appendLine("\t\t\tif duplicated != null:")
    appendLine("\t\t\t\tresult = int(duplicate_parts[1])")
    appendLine("\t\t\t\t_kanama_object_handles[result] = duplicated")
    appendLine("\t\telif opcode == 223:")
    appendLine("\t\t\tvar save_parts := String(args[2]).split(\"\\u001f\")")
    appendLine("\t\t\tvar save_handle := int(save_parts[0])")
    appendLine(
      "\t\t\tvar save_target: Object = self if save_handle == _kanama_handle else _kanama_object_handles.get(save_handle)"
    )
    appendLine("\t\t\tif save_target == null or not (save_target is Resource):")
    appendLine("\t\t\t\tresult = ERR_INVALID_PARAMETER")
    appendLine("\t\t\telse:")
    appendLine("\t\t\t\t# Property flow is engine-to-Kotlin at hydration; pull the current Kotlin")
    appendLine("\t\t\t\t# values into the scripted resource graph before serializing.")
    appendLine("\t\t\t\tif save_target.has_method(\"_kanama_pull_properties\"):")
    appendLine("\t\t\t\t\tsave_target.call(\"_kanama_pull_properties\")")
    appendLine(
      "\t\t\t\tresult = ResourceSaver.save(save_target as Resource, save_parts[1], int(save_parts[2]))"
    )
    appendLine("\t\telif opcode == 224 and value is Node:")
    appendLine("\t\t\tvar find_parts := String(args[2]).split(\"\\u001f\")")
    appendLine(
      "\t\t\tvar found := (value as Node).find_children(find_parts[0], find_parts[1], find_parts[2] == \"1\", find_parts[3] == \"1\")"
    )
    appendLine("\t\t\tvar found_handles := PackedStringArray()")
    appendLine("\t\t\tfor found_node in found:")
    appendLine("\t\t\t\tvar found_handle := 0")
    appendLine("\t\t\t\tif found_node.has_method(\"_kanama_ensure_created\"):")
    appendLine("\t\t\t\t\tfound_handle = int(found_node.call(\"_kanama_ensure_created\"))")
    appendLine("\t\t\t\tif found_handle == 0:")
    appendLine("\t\t\t\t\tfor existing_found in _kanama_object_handles:")
    appendLine("\t\t\t\t\t\tif is_same(_kanama_object_handles[existing_found], found_node):")
    appendLine("\t\t\t\t\t\t\tfound_handle = int(existing_found)")
    appendLine("\t\t\t\t\t\t\tbreak")
    appendLine("\t\t\t\tif found_handle == 0:")
    appendLine(
      "\t\t\t\t\tfound_handle = int(_kanama_bridge.allocateFoundNodeHandle(_kanama_handle))"
    )
    appendLine("\t\t\t\t_kanama_object_handles[found_handle] = found_node")
    appendLine("\t\t\t\tfound_handles.append(str(found_handle))")
    appendLine("\t\t\t_kanama_bridge.recordImmediateStringResult(\"\\u001f\".join(found_handles))")
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 229 and value is Node3D:")
    appendLine("\t\t\tvar ray_parts := String(args[2]).split(\"\\u001f\")")
    appendLine(
      "\t\t\tvar ray_from := Vector3(float(ray_parts[0]), float(ray_parts[1]), float(ray_parts[2]))"
    )
    appendLine(
      "\t\t\tvar ray_to := Vector3(float(ray_parts[3]), float(ray_parts[4]), float(ray_parts[5]))"
    )
    appendLine("\t\t\tvar ray_exclude_handle := int(ray_parts[7])")
    appendLine(
      "\t\t\tvar ray_query := PhysicsRayQueryParameters3D.create(ray_from, ray_to, int(ray_parts[6]))"
    )
    appendLine("\t\t\tif ray_exclude_handle != 0:")
    appendLine(
      "\t\t\t\tvar ray_excluded: Object = self if ray_exclude_handle == _kanama_handle else _kanama_object_handles.get(ray_exclude_handle)"
    )
    appendLine("\t\t\t\tif ray_excluded is CollisionObject3D:")
    appendLine("\t\t\t\t\tray_query.exclude = [(ray_excluded as CollisionObject3D).get_rid()]")
    appendLine("\t\t\tvar ray_space := (value as Node3D).get_world_3d().direct_space_state")
    appendLine("\t\t\tvar ray_hit: Dictionary = ray_space.intersect_ray(ray_query)")
    appendLine("\t\t\tvar ray_result := PackedStringArray()")
    appendLine("\t\t\tif ray_hit.is_empty():")
    appendLine("\t\t\t\tray_result.append(\"0\")")
    appendLine("\t\t\t\tray_result.append_array([\"0\", \"0\", \"0\", \"0\"])")
    appendLine("\t\t\telse:")
    appendLine("\t\t\t\tvar ray_point: Vector3 = ray_hit[\"position\"]")
    appendLine(
      "\t\t\t\t# The collider only ever gets compared against an already-tracked handle, so an"
    )
    appendLine(
      "\t\t\t\t# untracked engine body (level geometry) reports 0 rather than minting a handle."
    )
    appendLine("\t\t\t\tvar ray_collider_handle := 0")
    appendLine("\t\t\t\tvar ray_collider: Object = ray_hit.get(\"collider\")")
    appendLine("\t\t\t\tif ray_collider != null:")
    appendLine("\t\t\t\t\tif ray_collider.has_method(\"_kanama_ensure_created\"):")
    appendLine(
      "\t\t\t\t\t\tray_collider_handle = int(ray_collider.call(\"_kanama_ensure_created\"))"
    )
    appendLine("\t\t\t\t\tif ray_collider_handle == 0:")
    appendLine("\t\t\t\t\t\tfor ray_existing in _kanama_object_handles:")
    appendLine("\t\t\t\t\t\t\tif is_same(_kanama_object_handles[ray_existing], ray_collider):")
    appendLine("\t\t\t\t\t\t\t\tray_collider_handle = int(ray_existing)")
    appendLine("\t\t\t\t\t\t\t\tbreak")
    appendLine("\t\t\t\tray_result.append(\"1\")")
    appendLine("\t\t\t\tray_result.append(str(ray_point.x))")
    appendLine("\t\t\t\tray_result.append(str(ray_point.y))")
    appendLine("\t\t\t\tray_result.append(str(ray_point.z))")
    appendLine("\t\t\t\tray_result.append(str(ray_collider_handle))")
    appendLine("\t\t\t_kanama_bridge.recordImmediateStringResult(\"\\u001f\".join(ray_result))")
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 233:")
    appendLine("\t\t\tvar property_variant: Variant = value.get(StringName(String(args[2])))")
    appendLine(
      "\t\t\tvar property_vector: Vector2 = property_variant if property_variant is Vector2 else Vector2.ZERO"
    )
    appendLine(
      "\t\t\t_kanama_bridge.recordImmediateStringResult(\"%s\\u001f%s\" % [property_vector.x, property_vector.y])"
    )
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 240 and value is CPUParticles3D:")
    appendLine("\t\t\tresult = int(round((value as CPUParticles3D).lifetime * 1000.0))")
    appendLine("\t\telif opcode == 249 and value is Timer:")
    appendLine("\t\t\tresult = int(round((value as Timer).time_left * 1000.0))")
    appendLine("\t\telif opcode == 263 and value is Range:")
    appendLine("\t\t\tresult = int(round((value as Range).value * 1000.0))")
    appendLine("\t\telif opcode == 243 and value is MeshInstance3D:")
    appendLine("\t\t\tvar override_parts := String(args[2]).split(\"\\u001f\")")
    appendLine(
      "\t\t\tvar override_material := (value as MeshInstance3D).get_surface_override_material(int(override_parts[0]))"
    )
    appendLine("\t\t\tif override_material != null:")
    appendLine("\t\t\t\tresult = int(override_parts[1])")
    appendLine("\t\t\t\t_kanama_object_handles[result] = override_material")
    appendLine("\t\telif opcode == 244 and value is Mesh:")
    appendLine("\t\t\tvar surface_parts := String(args[2]).split(\"\\u001f\")")
    appendLine(
      "\t\t\tvar surface_material := (value as Mesh).surface_get_material(int(surface_parts[0]))"
    )
    appendLine("\t\t\tif surface_material != null:")
    appendLine("\t\t\t\tresult = int(surface_parts[1])")
    appendLine("\t\t\t\t_kanama_object_handles[result] = surface_material")
    appendLine("\t\telif opcode == 245 and value is Mesh:")
    appendLine("\t\t\tvar set_surface_parts := String(args[2]).split(\"\\u001f\")")
    appendLine("\t\t\tvar set_surface_handle := int(set_surface_parts[1])")
    appendLine(
      "\t\t\tvar set_surface_material: Material = null if set_surface_handle == 0 else _kanama_object_handles.get(set_surface_handle) as Material"
    )
    appendLine(
      "\t\t\t(value as Mesh).surface_set_material(int(set_surface_parts[0]), set_surface_material)"
    )
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 255 and value is BaseButton:")
    appendLine("\t\t\tresult = int((value as BaseButton).button_pressed)")
    appendLine("\t\telif opcode == 259 and value is LineEdit:")
    appendLine("\t\t\t_kanama_bridge.recordImmediateStringResult(String((value as LineEdit).text))")
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 288 and value is Node:")
    appendLine(
      "\t\t\t# Optional child path ('' = the receiver itself): the squash ScoreLabel IS the"
    )
    appendLine("\t\t\t# scripted node, while the platformer 'Coins' Label hangs beneath the Hud")
    appendLine(
      "\t\t\t# script's Control. A missing or non-Label target publishes no string, so the"
    )
    appendLine("\t\t\t# bridge's immediate string query fails loud instead of returning something")
    appendLine("\t\t\t# that could pass as text.")
    appendLine("\t\t\tvar label_query_path := String(args[2])")
    appendLine(
      "\t\t\tvar label_query_node: Node = (value as Node) if label_query_path.is_empty() else (value as Node).get_node_or_null(label_query_path)"
    )
    appendLine("\t\t\tif label_query_node is Label:")
    appendLine(
      "\t\t\t\t_kanama_bridge.recordImmediateStringResult(String((label_query_node as Label).text))"
    )
    appendLine("\t\t\t\tresult = 1")
    appendLine("\t\telif opcode == 266 and value is ConfigFile:")
    appendLine("\t\t\tvar has_key_parts := String(args[2]).split(\"\\u001f\")")
    appendLine(
      "\t\t\tresult = int((value as ConfigFile).has_section_key(has_key_parts[0], has_key_parts[1]))"
    )
    appendLine("\t\telif opcode == 268 and value is ConfigFile:")
    appendLine("\t\t\tvar get_value_parts := String(args[2]).split(\"\\u001f\")")
    appendLine(
      "\t\t\tvar config_read: Variant = (value as ConfigFile).get_value(get_value_parts[0], get_value_parts[1])"
    )
    appendLine("\t\t\tvar config_read_tagged := \"n:\"")
    appendLine("\t\t\tif config_read is bool:")
    appendLine("\t\t\t\tconfig_read_tagged = \"b:true\" if config_read else \"b:false\"")
    appendLine("\t\t\telif config_read is int:")
    appendLine("\t\t\t\tconfig_read_tagged = \"i:%d\" % config_read")
    appendLine("\t\t\telif config_read is float:")
    appendLine("\t\t\t\tconfig_read_tagged = \"f:%s\" % config_read")
    appendLine("\t\t\telif config_read != null:")
    appendLine("\t\t\t\tconfig_read_tagged = \"s:%s\" % config_read")
    appendLine("\t\t\t_kanama_bridge.recordImmediateStringResult(config_read_tagged)")
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 269:")
    appendLine("\t\t\tEngine.max_fps = int(String(args[2]))")
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 270:")
    appendLine("\t\t\tresult = int(Engine.get_frames_per_second())")
    appendLine("\t\telif opcode == 271:")
    appendLine("\t\t\tresult = int(OS.get_static_memory_usage())")
    appendLine("\t\telif opcode == 272:")
    appendLine(
      "\t\t\tresult = int(round(Input.get_action_strength(StringName(String(args[2]))) * 1000.0))"
    )
    appendLine("\t\telif opcode == 276 and value is Noise:")
    appendLine("\t\t\tresult = int(round((value as Noise).get_noise_1d(float(args[2])) * 1000.0))")
    appendLine("\t\telif opcode == 278 and value is Node:")
    appendLine("\t\t\t_kanama_bridge.recordImmediateStringResult(String((value as Node).name))")
    appendLine("\t\t\tresult = 1")
    appendLine("\t\telif opcode == 282:")
    appendLine("\t\t\tresult = int(value.has_signal(StringName(String(args[2]))))")
    appendLine("\t\telif opcode == 1002:")
    appendLine("\t\t\t# EXPERIMENTAL (task 76 spike): immediate generic call — callv by name")
    appendLine("\t\t\t# with a variant-tagged result on the immediate string channel.")
    appendLine("\t\t\tvar generic_call_parts := String(args[2]).split(\"\\u001f\")")
    appendLine("\t\t\tvar generic_call_args: Array = []")
    appendLine("\t\t\tfor generic_part_index in range(1, generic_call_parts.size()):")
    appendLine(
      "\t\t\t\tgeneric_call_args.append(_kanama_generic_decode_arg(generic_call_parts[generic_part_index]))"
    )
    appendLine(
      "\t\t\tvar generic_call_result: Variant = value.callv(StringName(generic_call_parts[0]), generic_call_args)"
    )
    appendLine(
      "\t\t\t_kanama_bridge.recordImmediateStringResult(_kanama_generic_encode_result(generic_call_result))"
    )
    appendLine("\t\t\tresult = 1")
    appendLine("\t_kanama_bridge.recordImmediateLongResult(result)")
    appendLine("\treturn result")
    appendLine()
    appendLine("func _kanama_generic_decode_arg(generic_packed_arg: String) -> Variant:")
    appendLine("\t# EXPERIMENTAL (task 76 spike): one `typeTag:value` generic-call argument.")
    appendLine("\tvar generic_tag_split := generic_packed_arg.find(\":\")")
    appendLine("\tvar generic_arg_tag := generic_packed_arg.substr(0, generic_tag_split)")
    appendLine("\tvar generic_arg_payload := generic_packed_arg.substr(generic_tag_split + 1)")
    appendLine("\tif generic_arg_tag == \"n\":")
    appendLine("\t\treturn null")
    appendLine("\tif generic_arg_tag == \"b\":")
    appendLine("\t\treturn generic_arg_payload == \"true\"")
    appendLine("\tif generic_arg_tag == \"i\":")
    appendLine("\t\treturn int(generic_arg_payload)")
    appendLine("\tif generic_arg_tag == \"d\":")
    appendLine("\t\treturn float(generic_arg_payload)")
    appendLine("\tif generic_arg_tag == \"s\":")
    appendLine("\t\t# Unescape (reverse of the Kotlin encoder): %1F -> unit separator, %25 -> %.")
    appendLine(
      "\t\treturn generic_arg_payload.replace(\"%1F\", \"\\u001f\").replace(\"%25\", \"%\")"
    )
    appendLine("\tif generic_arg_tag == \"h\":")
    appendLine("\t\tvar generic_arg_handle := int(generic_arg_payload)")
    appendLine(
      "\t\treturn self if generic_arg_handle == _kanama_handle else _kanama_object_handles.get(generic_arg_handle)"
    )
    appendLine(
      "\tpush_error(\"Kanama Web generic call: unknown argument tag '%s'\" % generic_arg_tag)"
    )
    appendLine("\treturn null")
    appendLine()
    appendLine("func _kanama_generic_encode_result(generic_value: Variant) -> String:")
    appendLine("\t# Task 76: variant-tagged generic-call return. Object returns resolve to an")
    appendLine("\t# already-tracked handle first (script-backed via _kanama_ensure_created,")
    appendLine("\t# then the is_same scan); an untracked engine object is classified at")
    appendLine("\t# runtime (Node / Resource / plain Object) and a tracked handle of that")
    appendLine("\t# kind is MINTED, owned by the calling script per the task-61 close-what-")
    appendLine("\t# you-create contract (released via close() or at owner teardown).")
    appendLine("\tif generic_value == null:")
    appendLine("\t\treturn \"n\"")
    appendLine("\tif generic_value is bool:")
    appendLine("\t\treturn \"b\\u001ftrue\" if generic_value else \"b\\u001ffalse\"")
    appendLine("\tif generic_value is int:")
    appendLine("\t\treturn \"i\\u001f%d\" % generic_value")
    appendLine("\tif generic_value is float:")
    appendLine("\t\treturn \"f\\u001f%s\" % generic_value")
    appendLine("\tif generic_value is String or generic_value is StringName:")
    appendLine("\t\t# Escape so payload separators survive the packed transport: % first.")
    appendLine(
      "\t\treturn \"s\\u001f%s\" % String(generic_value).replace(\"%\", \"%25\").replace(\"\\u001f\", \"%1F\")"
    )
    appendLine("\tif generic_value is Vector2:")
    appendLine("\t\tvar generic_v2 := generic_value as Vector2")
    appendLine("\t\treturn \"v2\\u001f%s\\u001f%s\" % [generic_v2.x, generic_v2.y]")
    appendLine("\tif generic_value is Vector3:")
    appendLine("\t\tvar generic_v3 := generic_value as Vector3")
    appendLine(
      "\t\treturn \"v3\\u001f%s\\u001f%s\\u001f%s\" % [generic_v3.x, generic_v3.y, generic_v3.z]"
    )
    appendLine("\tif generic_value is Object:")
    appendLine("\t\tvar generic_object := generic_value as Object")
    appendLine("\t\tif generic_object.has_method(\"_kanama_ensure_created\"):")
    appendLine(
      "\t\t\tvar generic_script_handle := int(generic_object.call(\"_kanama_ensure_created\"))"
    )
    appendLine("\t\t\tif generic_script_handle != 0:")
    appendLine("\t\t\t\treturn \"o\\u001f%d\\u001fscript\" % generic_script_handle")
    appendLine("\t\tfor generic_existing in _kanama_object_handles:")
    appendLine("\t\t\tif is_same(_kanama_object_handles[generic_existing], generic_object):")
    appendLine("\t\t\t\treturn \"o\\u001f%d\\u001ftracked\" % int(generic_existing)")
    appendLine("\t\tvar generic_kind := \"object\"")
    appendLine("\t\tif generic_object is Node:")
    appendLine("\t\t\tgeneric_kind = \"node\"")
    appendLine("\t\telif generic_object is Resource:")
    appendLine("\t\t\tgeneric_kind = \"resource\"")
    appendLine(
      "\t\tvar generic_minted := int(_kanama_bridge.mintGenericHandle(_kanama_handle, generic_kind))"
    )
    appendLine("\t\t_kanama_object_handles[generic_minted] = generic_object")
    appendLine("\t\treturn \"o\\u001f%d\\u001f%s\" % [generic_minted, generic_kind]")
    appendLine("\treturn \"unsupported\\u001f%s\" % type_string(typeof(generic_value))")
    appendLine()
    appendLine("func _kanama_noargs_vector2(args: Array) -> int:")
    appendLine("\tvar opcode := int(args[0])")
    appendLine("\tvar object_handle := int(args[1])")
    appendLine(
      "\tvar value: Object = self if object_handle == _kanama_handle else _kanama_object_handles.get(object_handle)"
    )
    appendLine("\tvar result := Vector2.ZERO")
    appendLine("\tif opcode == 27 and value is CanvasItem:")
    appendLine("\t\tresult = (value as CanvasItem).get_local_mouse_position()")
    appendLine("\telif opcode == 127 and value is InputEventMouseMotion:")
    appendLine("\t\tresult = (value as InputEventMouseMotion).relative")
    appendLine("\telif opcode == 220 and value is Viewport:")
    appendLine("\t\tresult = (value as Viewport).get_mouse_position()")
    appendLine("\telif opcode == 252 and value is Control:")
    appendLine("\t\tresult = (value as Control).position")
    appendLine("\telif opcode == 253 and value is Control:")
    appendLine("\t\tresult = (value as Control).size")
    appendLine("\t_kanama_bridge.recordImmediateVector2(result.x, result.y)")
    appendLine("\treturn 1")
    appendLine()
    appendLine("func _kanama_noargs_vector3(args: Array) -> int:")
    appendLine("\tvar opcode := int(args[0])")
    appendLine("\tvar object_handle := int(args[1])")
    appendLine(
      "\tvar value: Object = self if object_handle == _kanama_handle else _kanama_object_handles.get(object_handle)"
    )
    appendLine("\tvar result := Vector3.ZERO")
    appendLine("\tif opcode == 113 and value is KinematicCollision3D:")
    appendLine("\t\tresult = (value as KinematicCollision3D).get_normal()")
    appendLine("\telif opcode == 123 and value is RayCast3D:")
    appendLine("\t\tresult = (value as RayCast3D).get_collision_point()")
    appendLine("\telif opcode == 124 and value is RayCast3D:")
    appendLine("\t\tresult = (value as RayCast3D).get_collision_normal()")
    appendLine("\telif opcode == 138 and value is Node3D:")
    appendLine("\t\tresult = (value as Node3D).global_position")
    appendLine("\telif opcode == 141 and value is Node3D:")
    appendLine("\t\tresult = (value as Node3D).global_rotation")
    appendLine("\telif opcode == 156 and value is CharacterBody3D:")
    appendLine("\t\tresult = (value as CharacterBody3D).get_wall_normal()")
    appendLine("\telif opcode == 173 and value is ShapeCast3D:")
    appendLine("\t\tresult = (value as ShapeCast3D).get_collision_point(int(args[2]))")
    appendLine("\telif opcode == 176 and value is ShapeCast3D:")
    appendLine("\t\tresult = (value as ShapeCast3D).target_position")
    appendLine("\telif opcode == 178 and value is NavigationAgent3D:")
    appendLine("\t\tresult = (value as NavigationAgent3D).get_next_path_position()")
    appendLine("\telif opcode == 218 and value is Camera3D:")
    appendLine(
      "\t\tresult = (value as Camera3D).project_ray_origin(Vector2(float(args[2]), float(args[3])))"
    )
    appendLine("\telif opcode == 219 and value is Camera3D:")
    appendLine(
      "\t\tresult = (value as Camera3D).project_ray_normal(Vector2(float(args[2]), float(args[3])))"
    )
    appendLine("\telif opcode == 197 and value is RigidBody3D:")
    appendLine("\t\tresult = (value as RigidBody3D).angular_velocity")
    appendLine("\telif opcode == 227 and value is PhysicsBody3D:")
    appendLine("\t\tresult = (value as PhysicsBody3D).get_gravity()")
    appendLine("\telif opcode == 234 and value is AnimationMixer:")
    appendLine("\t\tresult = (value as AnimationMixer).get_root_motion_position()")
    appendLine("\telif opcode == 235 and value is AnimationMixer:")
    appendLine(
      "\t\t# Web adaptation: the Quaternion crosses as euler angles and the wrapper recomposes it."
    )
    appendLine("\t\tresult = (value as AnimationMixer).get_root_motion_rotation().get_euler()")
    appendLine("\telif opcode == 239 and value is CPUParticles3D:")
    appendLine("\t\tresult = (value as CPUParticles3D).emission_box_extents")
    appendLine("\t_kanama_bridge.recordImmediateVector3(result.x, result.y, result.z)")
    appendLine("\treturn 1")
    appendLine()
    appendLine("func _kanama_signal_emit_vector2i(args: Array) -> int:")
    appendLine("\tvar object_handle := int(args[0])")
    appendLine(
      "\tvar value: Object = self if object_handle == _kanama_handle else _kanama_object_handles.get(object_handle)"
    )
    appendLine("\tvar result := ERR_INVALID_PARAMETER")
    appendLine("\tif value != null:")
    appendLine(
      "\t\tresult = value.emit_signal(StringName(String(args[1])), Vector2i(int(args[2]), int(args[3])))"
    )
    appendLine("\t_kanama_bridge.recordImmediateSignalResult(result)")
    appendLine("\treturn result")

    model.methods.forEachIndexed { index, method ->
      val arm = methodArm(method)
      // Both _draw arms skip the per-method function: the zero-arg void form is crossed by the
      // _draw virtual dispatcher, and any other _draw shape has no crossing at all.
      if (arm == WebMethodArm.DRAW_VIRTUAL || arm == WebMethodArm.DRAW_SHAPE_MISMATCH) {
        return@forEachIndexed
      }
      appendLine()
      val args = method.args.joinToString(", ") { "${it.name}: ${gdType(it)}" }
      val returnType = " -> ${method.returnType?.let(::gdType) ?: "void"}"
      appendLine("func ${method.godotName}($args)$returnType:")
      appendLine(
        "\tif _kanama_bridge.shouldDeferGameplayMethod(${quote(model.fqName)}, ${quote(method.godotName)}):"
      )
      appendLine(
        "\t\t_kanama_bridge.recordDeferredGameplayMethod(${quote(model.fqName)}, ${quote(method.godotName)})"
      )
      appendLine("\t\treturn${method.returnType?.let { " ${gdDefault(it)}" } ?: ""}")
      when (arm) {
        // Handled above: neither _draw arm reaches this point.
        WebMethodArm.DRAW_VIRTUAL,
        WebMethodArm.DRAW_SHAPE_MISMATCH -> Unit
        WebMethodArm.NO_ARGS ->
          appendLine("\t_kanama_bridge.callNoArgs(_kanama_handle, ${index + 1})")
        WebMethodArm.INT_RET_INT -> {
          val arg = method.args.single()
          appendLine(
            "\treturn int(_kanama_bridge.callInt(_kanama_handle, ${index + 1}, ${arg.name}))"
          )
        }
        WebMethodArm.VECTOR2I_VOID -> {
          val arg = method.args.single()
          appendLine(
            "\t_kanama_bridge.callVector2i(_kanama_handle, ${index + 1}, ${arg.name}.x, ${arg.name}.y)"
          )
        }
        WebMethodArm.INT_VOID -> {
          val arg = method.args.single()
          appendLine("\t_kanama_bridge.callLongVoid(_kanama_handle, ${index + 1}, ${arg.name})")
        }
        WebMethodArm.STRING_VOID -> {
          val arg = method.args.single()
          appendLine("\t_kanama_bridge.callString(_kanama_handle, ${index + 1}, ${arg.name})")
        }
        WebMethodArm.OBJECT_VOID -> {
          // Registered function taking a single Godot object (e.g. a body_entered signal
          // handler). A Kanama-scripted node is passed as its SCRIPT handle (mirrors node
          // lookup) so the Kotlin side can resolve kotlinScriptInstance on it; anything else
          // rides a transient handle registered for the call, then released.
          val arg = method.args.single()
          appendLine("\tvar script_arg_handle := 0")
          appendLine("\tif ${arg.name}.has_method(\"_kanama_ensure_created\"):")
          appendLine("\t\tscript_arg_handle = int(${arg.name}.call(\"_kanama_ensure_created\"))")
          appendLine("\tif script_arg_handle != 0:")
          appendLine(
            "\t\t_kanama_bridge.callObject(_kanama_handle, ${index + 1}, script_arg_handle)"
          )
          appendLine("\telse:")
          appendLine(
            "\t\tvar arg_handle := int(_kanama_bridge.allocateTransientObjectHandle(_kanama_handle))"
          )
          appendLine("\t\t_kanama_object_handles[arg_handle] = ${arg.name}")
          appendLine("\t\t_kanama_bridge.callObject(_kanama_handle, ${index + 1}, arg_handle)")
          appendLine("\t\t_kanama_object_handles.erase(arg_handle)")
          appendLine("\t\t_kanama_bridge.releaseTransientObjectHandle(arg_handle)")
        }
        WebMethodArm.OBJECT_OBJECT_INT_VOID -> {
          val first = method.args[0]
          val second = method.args[1]
          val value = method.args[2]
          appendLine(
            "\tvar first_handle := int(_kanama_bridge.allocateTransientObjectHandle(_kanama_handle))"
          )
          appendLine(
            "\tvar second_handle := int(_kanama_bridge.allocateTransientObjectHandle(_kanama_handle))"
          )
          appendLine("\t_kanama_object_handles[first_handle] = ${first.name}")
          appendLine("\t_kanama_object_handles[second_handle] = ${second.name}")
          appendLine(
            "\t_kanama_bridge.callObjectObjectLong(_kanama_handle, ${index + 1}, first_handle, second_handle, ${value.name})"
          )
          appendLine("\t_kanama_object_handles.erase(first_handle)")
          appendLine("\t_kanama_object_handles.erase(second_handle)")
          appendLine("\t_kanama_bridge.releaseTransientObjectHandle(first_handle)")
          appendLine("\t_kanama_bridge.releaseTransientObjectHandle(second_handle)")
        }
        WebMethodArm.NUMERIC_VOID -> {
          // Task 80 slice 2: every all-numeric argument list flattens into the same six-slot
          // crossing. Unused slots carry 0.0 so the bridge entry point keeps one fixed arity.
          val slots = numericArgSlotExpressions(method.args)
          val padded = slots + List(NUMERIC_ARG_SLOTS - slots.size) { "0.0" }
          appendLine(
            "\t_kanama_bridge.callDoubles(_kanama_handle, ${index + 1}, ${padded.joinToString(", ")})"
          )
        }
        WebMethodArm.PACKED_RETURN -> {
          // Task 80 slice 2: the return value crosses packed into one string with the same
          // encoding getPackedProperty uses, and is parsed here per the declared return type.
          val returnType = checkNotNull(method.returnType) { "PACKED_RETURN needs a return type" }
          appendLine(
            "\tvar _kanama_packed := String(_kanama_bridge.callPacked(_kanama_handle, ${index + 1}))"
          )
          appendPackedReturnParse(returnType)
        }
        WebMethodArm.PACKED_ARGS -> {
          // Task 80 slice 3: a MIXED argument list (text/int/bool/object handle) packed into one
          // string over the EXISTING string crossing -- object arguments ride as their handle id,
          // which is what lets one arm carry a shape the string and object arms cannot.
          appendLine("\tvar $PACKED_ARG_TRANSIENT: Array[int] = []")
          appendLine("\tvar $PACKED_ARG_PARTS: PackedStringArray = PackedStringArray()")
          packedArgGdExpressions(method.args).forEach {
            appendLine("\t$PACKED_ARG_PARTS.append($it)")
          }
          appendLine(
            "\t_kanama_bridge.callString(_kanama_handle, ${index + 1}, " +
              "\"\\u001f\".join($PACKED_ARG_PARTS))"
          )
          appendLine("\tfor _kanama_packed_handle in $PACKED_ARG_TRANSIENT:")
          appendLine("\t\t_kanama_object_handles.erase(_kanama_packed_handle)")
          appendLine("\t\t_kanama_bridge.releaseTransientObjectHandle(_kanama_packed_handle)")
        }
        // Task 80: this stub throws at runtime, and the manifest + build report now say so.
        WebMethodArm.NONE -> {
          appendLine(
            "\t_kanama_bridge.unsupportedGameplayMethod(_KANAMA_SCRIPT_ID, ${index + 1}, ${quote(method.godotName)})"
          )
          method.returnType?.let { appendLine("\treturn ${gdDefault(it)}") }
        }
      }
    }
  }

  /**
   * Parses the `callPacked` transport string into the declared return type and returns it. Mirror
   * of [packedReturnExpression] and of the property pull parser — one encoding, parsed the same way
   * on both channels.
   */
  private fun StringBuilder.appendPackedReturnParse(type: TypeMapping) {
    when (type) {
      TypeMapping.STRING -> appendLine("\treturn _kanama_packed")
      TypeMapping.NODE_PATH -> appendLine("\treturn NodePath(_kanama_packed)")
      TypeMapping.INT -> appendLine("\treturn int(_kanama_packed)")
      TypeMapping.FLOAT -> appendLine("\treturn float(_kanama_packed)")
      TypeMapping.BOOL -> appendLine("\treturn _kanama_packed == \"1\"")
      TypeMapping.VECTOR2 -> {
        appendLine("\tvar _kanama_parts := _kanama_packed.split_floats(\",\")")
        appendLine("\treturn Vector2(_kanama_parts[0], _kanama_parts[1])")
      }
      TypeMapping.VECTOR2I -> {
        appendLine("\tvar _kanama_parts := _kanama_packed.split(\",\")")
        appendLine("\treturn Vector2i(int(_kanama_parts[0]), int(_kanama_parts[1]))")
      }
      TypeMapping.VECTOR3 -> {
        appendLine("\tvar _kanama_parts := _kanama_packed.split_floats(\",\")")
        appendLine("\treturn Vector3(_kanama_parts[0], _kanama_parts[1], _kanama_parts[2])")
      }
      TypeMapping.QUATERNION -> {
        appendLine("\tvar _kanama_parts := _kanama_packed.split_floats(\",\")")
        appendLine(
          "\treturn Quaternion(_kanama_parts[0], _kanama_parts[1], _kanama_parts[2], _kanama_parts[3])"
        )
      }
      TypeMapping.BASIS -> {
        appendLine("\tvar _kanama_parts := _kanama_packed.split_floats(\",\")")
        appendLine("\t# Packed as the three basis COLUMNS, which is Basis(x_axis, y_axis, z_axis).")
        appendLine(
          "\treturn Basis(Vector3(_kanama_parts[0], _kanama_parts[1], _kanama_parts[2]), " +
            "Vector3(_kanama_parts[3], _kanama_parts[4], _kanama_parts[5]), " +
            "Vector3(_kanama_parts[6], _kanama_parts[7], _kanama_parts[8]))"
        )
      }
      else -> error("no packed return parse for ${type.name}")
    }
  }

  private fun StringBuilder.appendProtocolMethods(label: String, methods: List<ProtocolMethod>) {
    appendLine("      \"$label\": [")
    methods.forEachIndexed { index, method ->
      append("        {\"id\": ${index + 1}, \"name\": ${quote(method.name)}, ")
      append("\"arguments\": ${protocolArgs(method.args)}, \"returnType\": ")
      append(method.returnType?.let { quote(it.kotlinType) } ?: "null")
      appendDispatch(method.dispatch)
      append("}")
      appendLine(if (index == methods.lastIndex) "" else ",")
    }
    append("      ]")
  }

  /**
   * Task 80: every manifest entry declares how it actually reaches Kotlin. `dispatchReason` is
   * present only when the entry is not `typed`, so its absence is exactly "no degradation".
   */
  private fun StringBuilder.appendDispatch(dispatch: WebDispatch) {
    append(", \"dispatch\": ${quote(dispatch.status.json)}")
    dispatch.reason?.let { append(", \"dispatchReason\": ${quote(it)}") }
  }

  private fun protocolArgs(args: List<ArgModel>): String = buildString {
    append('[')
    args.forEachIndexed { index, arg ->
      if (index > 0) append(", ")
      append("{\"name\": ${quote(arg.name)}, \"type\": ${quote(protocolArgType(arg))}, ")
      append("\"nullable\": ${arg.nullable}, \"hasDefault\": ${arg.hasDefault}}")
    }
    append(']')
  }

  private fun protocolArgType(arg: ArgModel): String =
    arg.objectWrapperFqName ?: arg.type.kotlinType

  private fun protocolPropertyType(property: ScriptPropertyModel): String =
    when {
      property.type == TypeMapping.ARRAY && property.arrayElementWrapperFqName != null ->
        "List<${property.arrayElementWrapperFqName}>"
      property.type == TypeMapping.ARRAY && property.arrayElementCustomScriptFqName != null ->
        "List<${property.arrayElementCustomScriptFqName}>"
      property.type == TypeMapping.ARRAY && property.arrayElementString -> "List<String>"
      property.objectWrapperFqName != null -> property.objectWrapperFqName
      property.customScriptFqName != null -> property.customScriptFqName
      else -> property.type.kotlinType
    }

  /**
   * The export annotation for a property declaration. A RANGE hint becomes GDScript's dedicated
   * `@export_range(...)` form (that is how hint metadata reaches the proxy — task 64); structural
   * resource/node/typed-array hints are already carried by the typed declaration and keep the plain
   * `@export`. Any other nonzero hint was rejected loudly by [unsupportedWebPropertyErrors] before
   * emission, so nothing is ever silently dropped here.
   */
  private fun exportAnnotation(property: ScriptPropertyModel): String {
    if (property.hint != PROPERTY_HINT_RANGE) return "@export"
    val arguments =
      rangeExportArguments(property.hintString)
        ?: error(
          "unreachable: RANGE hintString '${property.hintString}' passed the Web property guard"
        )
    return "@export_range(${arguments.joinToString(", ")})"
  }

  private fun StringBuilder.appendPropertyGroup(property: ScriptPropertyModel) {
    fun appendGroup(annotation: String, group: ScriptPropertyGroupModel?) {
      if (group == null) return
      val prefix = if (group.prefix.isBlank()) "" else ", ${quote(group.prefix)}"
      appendLine("@$annotation(${quote(group.name)}$prefix)")
    }
    appendGroup("export_category", property.exportCategory)
    appendGroup("export_group", property.exportGroup)
    appendGroup("export_subgroup", property.exportSubgroup)
  }

  private fun gdType(arg: ArgModel): String =
    arg.objectWrapperFqName?.let(::gdObjectType) ?: gdType(arg.type)

  /**
   * Engine-side pull of the current Kotlin property values (save-time sync). Property flow is
   * normally engine-to-Kotlin at hydration; ResourceSaver.save must serialize what the Kotlin
   * instance holds NOW, so the save applier calls this before saving. Recurses into scripted
   * object/array elements; the reentry flag guards resource cycles.
   */
  private fun StringBuilder.appendPullProperties(model: ScriptModel) {
    appendLine("func _kanama_pull_properties() -> void:")
    appendLine("\tif _kanama_handle == 0 or _kanama_pulling:")
    appendLine("\t\treturn")
    appendLine("\t_kanama_pulling = true")
    model.properties.forEachIndexed { index, property ->
      val id = index + 1
      val name = property.godotName
      val packed = "_kanama_packed_$id"
      val supported =
        when (property.type) {
          TypeMapping.STRING,
          TypeMapping.INT,
          TypeMapping.FLOAT,
          TypeMapping.BOOL,
          TypeMapping.VECTOR2,
          TypeMapping.VECTOR2I,
          TypeMapping.VECTOR3,
          TypeMapping.NODE_PATH -> true
          TypeMapping.OBJECT ->
            property.customScriptFqName != null || property.objectWrapperFqName != null
          TypeMapping.ARRAY ->
            property.arrayElementString ||
              property.arrayElementCustomScriptFqName != null ||
              property.arrayElementWrapperFqName != null
          else -> false
        }
      if (!supported) {
        appendLine("\t# ${property.godotName}: unsupported pull type, left as-is")
        return@forEachIndexed
      }
      appendLine("\tvar $packed := String(_kanama_bridge.getPackedProperty(_kanama_handle, $id))")
      when {
        property.type == TypeMapping.STRING -> appendLine("\t$name = $packed")
        property.type == TypeMapping.NODE_PATH -> appendLine("\t$name = NodePath($packed)")
        property.type == TypeMapping.INT -> appendLine("\t$name = int($packed)")
        property.type == TypeMapping.FLOAT -> appendLine("\t$name = float($packed)")
        property.type == TypeMapping.BOOL -> appendLine("\t$name = $packed == \"1\"")
        property.type == TypeMapping.VECTOR2 -> {
          appendLine("\tvar _kanama_parts_$id := $packed.split_floats(\",\")")
          appendLine("\t$name = Vector2(_kanama_parts_$id[0], _kanama_parts_$id[1])")
        }
        property.type == TypeMapping.VECTOR2I -> {
          appendLine("\tvar _kanama_parts_$id := $packed.split(\",\")")
          appendLine("\t$name = Vector2i(int(_kanama_parts_$id[0]), int(_kanama_parts_$id[1]))")
        }
        property.type == TypeMapping.VECTOR3 -> {
          appendLine("\tvar _kanama_parts_$id := $packed.split_floats(\",\")")
          appendLine(
            "\t$name = Vector3(_kanama_parts_$id[0], _kanama_parts_$id[1], _kanama_parts_$id[2])"
          )
        }
        property.type == TypeMapping.OBJECT -> {
          appendLine("\tvar _kanama_pull_handle_$id := int($packed)")
          appendLine("\tvar _kanama_pull_value_$id: Object = null")
          appendLine("\tif _kanama_pull_handle_$id != 0:")
          appendLine(
            "\t\t_kanama_pull_value_$id = self if _kanama_pull_handle_$id == _kanama_handle else _kanama_object_handles.get(_kanama_pull_handle_$id)"
          )
          appendLine(
            "\tif _kanama_pull_value_$id != null and _kanama_pull_value_$id.has_method(\"_kanama_pull_properties\"):"
          )
          appendLine("\t\t_kanama_pull_value_$id.call(\"_kanama_pull_properties\")")
          appendLine("\t$name = _kanama_pull_value_$id as ${gdType(property)}")
        }
        property.type == TypeMapping.ARRAY && property.arrayElementString -> {
          appendLine("\tif $packed == \"\":")
          appendLine("\t\t$name.assign([])")
          appendLine("\telse:")
          appendLine("\t\t$name.assign($packed.split(\"\\u001f\"))")
        }
        property.type == TypeMapping.ARRAY -> {
          appendLine("\tvar _kanama_rebuilt_$id: Array = []")
          appendLine("\tfor _kanama_part in $packed.split(\",\"):")
          appendLine("\t\tif _kanama_part == \"\":")
          appendLine("\t\t\tcontinue")
          appendLine("\t\tvar _kanama_element = _kanama_object_handles.get(int(_kanama_part))")
          appendLine("\t\tif _kanama_element == null:")
          appendLine("\t\t\tcontinue")
          appendLine("\t\tif _kanama_element.has_method(\"_kanama_pull_properties\"):")
          appendLine("\t\t\t_kanama_element.call(\"_kanama_pull_properties\")")
          appendLine("\t\t_kanama_rebuilt_$id.append(_kanama_element)")
          appendLine("\t$name.assign(_kanama_rebuilt_$id)")
        }
        else -> error("unreachable pull arm for ${property.godotName}")
      }
    }
    appendLine("\t_kanama_pulling = false")
    appendLine()
  }

  private fun gdType(property: ScriptPropertyModel): String =
    when {
      property.type == TypeMapping.ARRAY && property.arrayElementWrapperFqName != null ->
        "Array[${property.arrayElementWrapperFqName.substringAfterLast('.')}]"
      property.type == TypeMapping.ARRAY && property.arrayElementCustomScriptFqName != null ->
        "Array[${property.arrayElementCustomScriptFqName.substringAfterLast('.')}]"
      property.type == TypeMapping.ARRAY && property.arrayElementString -> "Array[String]"
      property.type == TypeMapping.ARRAY -> "Array"
      property.objectWrapperFqName != null -> gdObjectType(property.objectWrapperFqName)
      property.customScriptFqName != null -> property.customScriptFqName.substringAfterLast('.')
      // Typed at the PROPERTY declaration only: method signatures keep Variant for these
      // (their unsupported-method fallback bodies `return null`, which a typed Vector3/NodePath
      // return would turn into a GDScript compile error).
      property.type == TypeMapping.VECTOR3 -> "Vector3"
      property.type == TypeMapping.NODE_PATH -> "NodePath"
      else -> gdType(property.type)
    }

  private fun gdObjectType(fqName: String): String =
    if (fqName == "net.multigesture.kanama.api.GodotObject") "Object"
    else fqName.substringAfterLast('.')

  private fun gdType(type: TypeMapping): String =
    when (type) {
      TypeMapping.STRING -> "String"
      TypeMapping.INT -> "int"
      TypeMapping.FLOAT -> "float"
      TypeMapping.BOOL -> "bool"
      TypeMapping.VECTOR2 -> "Vector2"
      TypeMapping.VECTOR2I -> "Vector2i"
      TypeMapping.OBJECT -> "Object"
      TypeMapping.ARRAY -> "Array"
      else -> "Variant"
    }

  private fun gdDefault(property: ScriptPropertyModel): String =
    when (property.type) {
      TypeMapping.STRING -> property.defaultLiteral ?: "\"\""
      TypeMapping.INT -> property.defaultLiteral?.removeSuffix("L") ?: "0"
      TypeMapping.FLOAT -> property.defaultLiteral?.removeSuffix("f")?.removeSuffix("F") ?: "0.0"
      TypeMapping.BOOL -> property.defaultLiteral ?: "false"
      TypeMapping.NODE_PATH -> "NodePath(${nodePathDefaultString(property.defaultLiteral)})"
      TypeMapping.VECTOR2 -> vectorGdDefault(property.defaultLiteral, "Vector2")
      TypeMapping.VECTOR2I -> vectorGdDefault(property.defaultLiteral, "Vector2i")
      TypeMapping.VECTOR3 -> vectorGdDefault(property.defaultLiteral, "Vector3")
      TypeMapping.ARRAY -> "[]"
      else -> "null"
    }

  /**
   * The quoted path from a normalized NodePath default literal
   * (`net.multigesture.kanama.types.NodePath("../View")` -> `"../View"`), or `""` when there is
   * none. The literal's quoted segment is a plain Kotlin string literal, which spells the same way
   * in GDScript.
   */
  private fun nodePathDefaultString(defaultLiteral: String?): String =
    defaultLiteral?.let { NODE_PATH_DEFAULT.matchEntire(it)?.groupValues?.get(1) } ?: "\"\""

  /**
   * The GDScript spelling of a normalized vector default literal: `<fq>.ZERO` -> `<Simple>.ZERO`,
   * `<fq>(args)` -> `<Simple>(args)` with Kotlin numeric suffixes stripped. A property with no
   * normalized literal falls back to `<Simple>.ZERO` (also the pre-task-64 spelling, which ignored
   * the literal entirely — honoring it keeps the pushed hydration value in sync with the Kotlin
   * initializer).
   */
  private fun vectorGdDefault(defaultLiteral: String?, simpleClass: String): String {
    if (defaultLiteral == null) return "$simpleClass.ZERO"
    if (defaultLiteral.endsWith(".ZERO")) return "$simpleClass.ZERO"
    val args = defaultLiteral.substringAfter('(').substringBeforeLast(')')
    val gdArgs =
      args.split(',').joinToString(", ") { it.trim().trimEnd('f', 'F', 'd', 'D', 'l', 'L') }
    return "$simpleClass($gdArgs)"
  }

  private fun gdDefault(type: TypeMapping): String =
    when (type) {
      TypeMapping.STRING -> "\"\""
      TypeMapping.INT -> "0"
      TypeMapping.FLOAT -> "0.0"
      TypeMapping.BOOL -> "false"
      TypeMapping.VECTOR2 -> "Vector2.ZERO"
      TypeMapping.VECTOR2I -> "Vector2i.ZERO"
      TypeMapping.ARRAY -> "[]"
      else -> "null"
    }

  private fun quote(value: String): String = buildString {
    append('"')
    value.forEach { char ->
      when (char) {
        '\\' -> append("\\\\")
        '"' -> append("\\\"")
        '\n' -> append("\\n")
        '\r' -> append("\\r")
        '\t' -> append("\\t")
        else -> append(char)
      }
    }
    append('"')
  }

  private fun constantIdentifier(name: String): String {
    val stripped = name.trim('_')
    if (stripped.isBlank()) return "value"
    val words = stripped.split('_', '-', ' ', '.', ':', '/').filter { it.isNotBlank() }
    val candidate =
      words.drop(1).fold(words.first()) { acc, part ->
        acc + part.replaceFirstChar { it.uppercaseChar() }
      }
    return candidate.replace(Regex("""[^A-Za-z0-9_]"""), "_").let {
      if (it.firstOrNull()?.isDigit() == true) "_$it" else it
    }
  }
}

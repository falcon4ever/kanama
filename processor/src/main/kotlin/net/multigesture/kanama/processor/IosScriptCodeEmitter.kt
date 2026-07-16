package net.multigesture.kanama.processor

import java.security.MessageDigest

// Kotlin/Native analogue of [ScriptCodeEmitter]: produces the iOS @ScriptClass registry
// Kotlin from the platform-neutral [ScriptModel], replacing the regex parser + Gradle
// codegen that lived in ios-runtime/build.gradle.kts (parseIosScript /
// generateIosRegistrySource / generateIosGeneratedConstantsSource /
// generateIosCompatibilitySources). Phase 3.2, script-model-unification-design.md.
//
// It is AGGREGATING (takes all scripts at once) because the regex path emits one combined
// `registerKanamaIosProjectScripts()` + per-script bridge file in package
// `net.multigesture.kanama.ios`, plus one constants file in `net.multigesture.kanama.generated`,
// plus per-package compatibility shims. Reproducing that exact shape lets the Phase-3.2
// parallel-run gate compare this output against the regex output for the demo scripts.
//
// The rich ScriptModel is mapped down to the regex path's thin fields here (see
// [toIosMethods]/[toIosProperties]) so the emitted source matches byte-for-byte.

/** One @ScriptClass to emit, paired with the `res://…` path Godot reports for its source. */
internal data class IosScriptInput(
    val model: ScriptModel,
    val resourcePath: String,
) {
    val packageName: String? get() = model.fqName.substringBeforeLast('.', "").ifEmpty { null }
    val className: String get() = model.simpleName
}

// ---------- thin per-script models (mirror the old build.gradle.kts data classes) ----------

private val lifecycleIosVirtuals = setOf(
    "_ready", "_enter_tree", "_exit_tree",
    "_process", "_physics_process",
    "_input", "_unhandled_input", "_shortcut_input", "_unhandled_key_input",
)

// Return types the iOS callVReturning encode + C kanama_ios_pt_return_to_variant can marshal.
// POD/value returns (Bool/Int/Float/Vector2/Vector2i) landed in Phase 5.3b; STRING is the first
// non-POD (variable-length, destroy-after-read) virtual return, task 13. Task 29 adds Vector3 and
// RID (POD/inline), the fixed-element Packed*Array families (desc + flat element buffer),
// Dictionary and generic Array (tagged-entry blobs). NOT mirrored on iOS (by design, see
// wrapper-maintenance.md): RECT2/AABB/TRANSFORM2D/TRANSFORM3D/PROJECTION — Transform3D/Projection
// exceed the 32-byte PT return scratch and none has a 4.7 virtual whose args are declarable, so
// the C boxing for those four waits for the iOS wrapper-family follow-up slice.
private val iosReturnTypes = setOf(
    TypeMapping.BOOL, TypeMapping.INT, TypeMapping.FLOAT,
    TypeMapping.VECTOR2, TypeMapping.VECTOR2I, TypeMapping.VECTOR3,
    TypeMapping.STRING,
    TypeMapping.PACKED_STRING_ARRAY,
    // Variant returns reuse the per-runtime-type encodeIosReturn dispatch (audited inner types;
    // an unaudited inner value serializes as nil — a valid Variant). task 13.
    TypeMapping.VARIANT,
    TypeMapping.RID,
    TypeMapping.PACKED_BYTE_ARRAY, TypeMapping.PACKED_INT32_ARRAY, TypeMapping.PACKED_INT64_ARRAY,
    TypeMapping.PACKED_FLOAT32_ARRAY, TypeMapping.PACKED_FLOAT64_ARRAY,
    TypeMapping.PACKED_VECTOR2_ARRAY, TypeMapping.PACKED_VECTOR3_ARRAY, TypeMapping.PACKED_COLOR_ARRAY,
    TypeMapping.DICTIONARY, TypeMapping.ARRAY,
)

internal data class IosMethod(
    val godotName: String,
    val kotlinName: String,
    // Typed parameters (Phase 3.3): the generated `callV` casts/wraps each decoded arg by its
    // declared type. Replaces the old enumerated IosScriptBridgeKind.
    val args: List<ArgModel>,
    // Phase 5.3b: return type for value-returning methods/virtuals. `null` => void (callV path).
    // A supported non-null type routes to `callVReturning`, which encodes the result PT-tagged for
    // the engine. Unsupported return types are skipped + warned (return dropped, as before 5.3b).
    val returnType: TypeMapping? = null,
) {
    val argumentCount: Int get() = args.size
}

internal data class IosProperty(
    val godotName: String,
    val kotlinName: String,
    val isObjectType: Boolean,
    val godotClassName: String,
    val isList: Boolean,
    val listElementClassName: String,
    val isNullable: Boolean,
    // FQ Kotlin type for a value-type property (NodePath/Vector2/Vector3) delivered via the
    // set-property value path (PT-tagged bytes -> setPropertyValue). Empty for non-value types.
    val valueTypeClassName: String = "",
    // Godot Variant::Type, so the iOS script instance can advertise this property to the engine
    // (get_property_list) — required for scene-stored @ScriptProperty values to be delivered.
    val godotVariantType: Int = 0,
    // PropertyInfo metadata advertised by the iOS ScriptInstance. Enum and enum-list exports
    // depend on these matching the desktop registrar so the inspector renders the same controls.
    val hint: Int = 0,
    val hintString: String = "",
    val usage: Int = 6,
    // FQ name of a user @ScriptClass type for an OBJECT property (e.g. a `node_paths`-exported
    // reference like `target: Vehicle?`). Delivered as the owner handle and resolved to the live
    // Kotlin script instance via iosScriptInstanceForOwner. Empty for non-custom-script properties.
    val customScriptFqName: String = "",
    // FQ name of a user @ScriptClass element type for a `List<UserScript>` property (e.g. FPS
    // `weapons: List<Weapon>`). Each delivered owner handle is resolved to its live Kotlin
    // script instance. Empty unless the array element is a user @ScriptClass type.
    val arrayElementCustomScriptFqName: String = "",
    // True for a `List<String>` property (Godot PackedStringArray). Delivered as a string list
    // via the set-property string-array path (the engine ships the scene-stored
    // PackedStringArray, the C dispatch extracts each element, the runtime hands a
    // List<String> to the generated bridge). See `setPropertyStringArray`.
    val listElementIsString: Boolean = false,
    // Expression that converts the raw 64-bit scalar slot named `value` into the declared
    // Kotlin field type. This is the iOS mirror of the JVM emitter's wide-slot conversion:
    // Float/Int narrow from Godot's 64-bit slots and enum ordinals clamp before lookup.
    val scalarSetExpression: String = "",
    // Expression that converts the declared Kotlin field back to an engine Variant value.
    // [KanamaIosRuntime] encodes the returned value through the same PT return path used by
    // value-returning methods, so Object.get reads live script-instance state.
    val scalarGetExpression: String = "",
    // FQ enum element type for List<Enum> properties. The C/runtime path delivers the Array's
    // integer cells separately from object arrays; the generated bridge maps ordinals to entries.
    val arrayElementEnumFqName: String = "",
)

internal data class IosSignal(
    val godotName: String,
    val kotlinName: String,
)

internal data class IosRpcConfig(
    val godotName: String,
    val mode: Int,
    val callLocal: Boolean,
    val transferMode: Int,
    val channel: Int,
)

internal data class IosScript(
    val resourcePath: String,
    val packageName: String?,
    val className: String,
    val baseType: String,
    val methods: List<IosMethod>,
    val properties: List<IosProperty>,
    val signals: List<IosSignal>,
    val rpcConfigs: List<IosRpcConfig>,
)

internal class IosScriptCodeEmitter(
    inputs: List<IosScriptInput>,
    private val warn: (String) -> Unit = {},
    // Fails the KSP round (build error). Used for defects that must not ship, e.g. a data-type
    // @ScriptProperty the engine can set but not read back — the exact get/set asymmetry that shipped
    // write-only value types in the iOS backend and broke multiplayer replication on device.
    private val error: (String) -> Unit = {},
) {
    // Sort by resourcePath to match the old task's `.sortedBy { it.resourcePath }`.
    private val scripts: List<IosScript> =
        inputs.sortedBy { it.resourcePath }.map { it.toIosScript() }

    /** The combined registry file: `registerKanamaIosProjectScripts()` + bridge classes. */
    fun registrySource(): String {
        val imports = scripts
            .mapNotNull { script -> script.packageName?.let { "$it.${script.className}" } }
            .distinct()
            .sorted()
        val builder = StringBuilder()
        builder.appendLine("package net.multigesture.kanama.ios")
        builder.appendLine()
        builder.appendLine("import java.lang.foreign.MemorySegment")
        imports.forEach { builder.appendLine("import $it") }
        builder.appendLine()
        // `actual` of the `expect` declared in the shared iosMain source set: KSP emits this
        // registry into the per-target leaf source sets (iosArm64Main / iosSimulatorArm64Main),
        // which the shared KanamaIosRuntime cannot reference directly, so the call site is an
        // expect/actual pair. (The pre-cutover regex path emitted into iosMain itself.)
        builder.appendLine("internal actual fun registerKanamaIosProjectScripts() {")
        builder.appendLine("    if (KanamaIosGeneratedProjectScripts.registered) return")
        builder.appendLine("    KanamaIosGeneratedProjectScripts.registered = true")
        scripts.forEach { script ->
            val bridgeName = "${script.className}IosBridge_${shortHash(script.resourcePath)}"
            builder.appendLine("    KanamaIosProjectRegistry.register(")
            builder.appendLine("        KanamaIosScriptDescriptor(")
            builder.appendLine("            path = ${kotlinString(script.resourcePath)},")
            builder.appendLine("            baseType = ${kotlinString(script.baseType)},")
            builder.appendLine("            methods = listOf(")
            script.methods.forEach { method ->
                builder.appendLine(
                    "                KanamaIosScriptMethod(${kotlinString(method.godotName)}, ${method.argumentCount}),",
                )
            }
            builder.appendLine("            ),")
            builder.appendLine("            properties = listOf(")
            script.properties.forEach { property ->
                builder.appendLine(
                    "                KanamaIosScriptProperty(${kotlinString(property.godotName)}, ${property.godotVariantType}, ${property.hint}, ${kotlinString(property.hintString)}, ${property.usage}),",
                )
            }
            builder.appendLine("            ),")
            builder.appendLine("            signals = listOf(")
            script.signals.forEach { signal ->
                builder.appendLine(
                    "                KanamaIosScriptSignal(${kotlinString(signal.godotName)}),",
                )
            }
            builder.appendLine("            ),")
            builder.appendLine("            rpcConfigs = listOf(")
            script.rpcConfigs.forEach { rpc ->
                builder.appendLine(
                    "                KanamaIosRpcConfig(${kotlinString(rpc.godotName)}, ${rpc.mode}, ${rpc.callLocal}, ${rpc.transferMode}, ${rpc.channel}),",
                )
            }
            builder.appendLine("            ),")
            builder.appendLine("            factory = { ownerObject ->")
            builder.appendLine(
                "                $bridgeName(${script.className}(MemorySegment.ofAddress(ownerObject)))",
            )
            builder.appendLine("            },")
            builder.appendLine("        ),")
            builder.appendLine("    )")
        }
        builder.appendLine("}")
        builder.appendLine()
        builder.appendLine("private object KanamaIosGeneratedProjectScripts {")
        builder.appendLine("    var registered: Boolean = false")
        builder.appendLine("}")
        scripts.forEach { script ->
            val bridgeName = "${script.className}IosBridge_${shortHash(script.resourcePath)}"
            builder.appendLine()
            builder.appendLine("private class $bridgeName(")
            builder.appendLine("    private val script: ${script.className},")
            builder.appendLine(") : KanamaIosScriptBridge {")
            builder.appendLine("    override val scriptInstance: Any get() = script")
            builder.appendLine()
            // Generic per-signature dispatch (Phase 3.3): one branch per method, each decoded arg
            // cast/wrapped to its declared type. A method with an unaudited arg type is skipped +
            // warned (no silent "wrong shape" drop).
            builder.appendLine("    override fun callV(methodName: String, args: List<Any?>): Boolean = when (methodName) {")
            script.methods.filter { it.returnType == null }.forEach { method ->
                val exprs = method.args.mapIndexed { i, a -> callArgExpr(i, a) }
                if (exprs.all { it != null }) {
                    val invocation = "script.${method.kotlinName}(${exprs.joinToString(", ")})"
                    builder.appendLine("        ${kotlinString(method.godotName)} -> { $invocation; true }")
                } else {
                    warn("[kanama-ios] ${script.className}.${method.kotlinName} (godot: ${method.godotName}) has an unaudited arg type — not dispatched on iOS")
                }
            }
            builder.appendLine("        else -> false")
            builder.appendLine("    }")
            val readableProperties = script.properties.filter {
                it.scalarGetExpression.isNotEmpty() || it.arrayElementEnumFqName.isNotEmpty() ||
                    it.listElementIsString
            }
            if (readableProperties.isNotEmpty()) {
                builder.appendLine()
                builder.appendLine("    override fun getProperty(propertyIndex: Int): Any? = when (propertyIndex) {")
                script.properties.forEachIndexed { index, property ->
                    when {
                        property.arrayElementEnumFqName.isNotEmpty() ->
                            builder.appendLine("        $index -> script.${property.kotlinName}.map { it.ordinal.toLong() }")
                        // List<String>: encodeIosReturn ships it as a PackedStringArray; without this
                        // branch a String-list @ScriptProperty is read back as nil (write-only).
                        property.listElementIsString ->
                            builder.appendLine("        $index -> script.${property.kotlinName}")
                        property.scalarGetExpression.isNotEmpty() ->
                            builder.appendLine("        $index -> ${property.scalarGetExpression}")
                    }
                }
                builder.appendLine("        else -> net.multigesture.kanama.ios.KanamaIosNoProperty")
                builder.appendLine("    }")
            }
            builder.appendLine()
            // Phase 5.3b: value-returning methods/virtuals dispatch here; the result is encoded
            // PT-tagged for the engine return slot by the @CName export.
            val valueMethods = script.methods.filter { it.returnType != null }
            if (valueMethods.isNotEmpty()) {
                builder.appendLine("    override fun callVReturning(methodName: String, args: List<Any?>): Any? = when (methodName) {")
                valueMethods.forEach { method ->
                    val exprs = method.args.mapIndexed { i, a -> callArgExpr(i, a) }
                    if (exprs.all { it != null }) {
                        builder.appendLine("        ${kotlinString(method.godotName)} -> script.${method.kotlinName}(${exprs.joinToString(", ")})")
                    } else {
                        warn("[kanama-ios] ${script.className}.${method.kotlinName} (godot: ${method.godotName}) has an unaudited arg type — not dispatched on iOS")
                    }
                }
                builder.appendLine("        else -> net.multigesture.kanama.ios.KanamaIosNoReturn")
                builder.appendLine("    }")
                builder.appendLine()
            }
            builder.appendLine("    override fun setProperty(propertyIndex: Int, value: Long): Boolean = when (propertyIndex) {")
            script.properties.forEachIndexed { index, property ->
                if (property.isObjectType && property.godotClassName.isNotEmpty()) {
                    if (property.isNullable) {
                        builder.appendLine("        $index -> { script.${property.kotlinName} = if (value != 0L) net.multigesture.kanama.api.${property.godotClassName}(java.lang.foreign.MemorySegment.ofAddress(value)) else null; true }")
                    } else {
                        builder.appendLine("        $index -> { script.${property.kotlinName} = net.multigesture.kanama.api.${property.godotClassName}(java.lang.foreign.MemorySegment.ofAddress(value)); true }")
                    }
                } else if (property.customScriptFqName.isNotEmpty()) {
                    // node_paths-exported reference to another @ScriptClass: the engine delivers the
                    // target node's owner handle; resolve it to the live Kotlin script instance.
                    builder.appendLine("        $index -> { script.${property.kotlinName} = if (value != 0L) net.multigesture.kanama.ios.iosScriptInstanceForOwner(value) as? ${property.customScriptFqName} else null; true }")
                } else if (property.scalarSetExpression.isNotEmpty()) {
                    builder.appendLine("        $index -> { script.${property.kotlinName} = ${property.scalarSetExpression}; true }")
                }
            }
            builder.appendLine("        else -> false")
            builder.appendLine("    }")
            val stringProperties = script.properties.filter { !it.isObjectType && !it.isList && it.godotClassName == "String" }
            if (stringProperties.isNotEmpty()) {
                builder.appendLine()
                builder.appendLine("    override fun setPropertyString(propertyIndex: Int, value: String): Boolean = when (propertyIndex) {")
                stringProperties.forEach { property ->
                    val index = script.properties.indexOf(property)
                    builder.appendLine("        $index -> { script.${property.kotlinName} = value; true }")
                }
                builder.appendLine("        else -> false")
                builder.appendLine("    }")
            }
            val listProperties = script.properties.filter {
                it.isList && (it.listElementClassName.isNotEmpty() || it.arrayElementCustomScriptFqName.isNotEmpty())
            }
            if (listProperties.isNotEmpty()) {
                builder.appendLine()
                builder.appendLine("    override fun setPropertyObjectArray(propertyIndex: Int, values: LongArray): Boolean = when (propertyIndex) {")
                script.properties.forEachIndexed { index, property ->
                    when {
                        // Array of a user @ScriptClass type (e.g. `weapons: List<Weapon>`): each
                        // delivered owner handle resolves to its live Kotlin script instance, mirroring
                        // the single-object node_paths case. Stray unresolved handles are dropped.
                        property.isList && property.arrayElementCustomScriptFqName.isNotEmpty() -> {
                            builder.appendLine("        $index -> { script.${property.kotlinName} = values.toList().mapNotNull { owner -> net.multigesture.kanama.ios.iosScriptInstanceForOwner(owner) as? ${property.arrayElementCustomScriptFqName} }; true }")
                        }
                        // Array of an engine wrapper type: wrap each handle directly.
                        property.isList && property.listElementClassName.isNotEmpty() -> {
                            builder.appendLine("        $index -> { script.${property.kotlinName} = values.map { owner: Long -> net.multigesture.kanama.api.${property.listElementClassName}(java.lang.foreign.MemorySegment.ofAddress(owner)) }; true }")
                        }
                    }
                }
                builder.appendLine("        else -> false")
                builder.appendLine("    }")
            }
            val enumListProperties = script.properties.filter { it.arrayElementEnumFqName.isNotEmpty() }
            if (enumListProperties.isNotEmpty()) {
                builder.appendLine()
                builder.appendLine("    override fun setPropertyIntArray(propertyIndex: Int, values: LongArray): Boolean = when (propertyIndex) {")
                script.properties.forEachIndexed { index, property ->
                    if (property.arrayElementEnumFqName.isNotEmpty()) {
                        val fq = property.arrayElementEnumFqName
                        builder.appendLine("        $index -> { script.${property.kotlinName} = values.map { i -> $fq.entries[i.toInt().coerceIn(0, $fq.entries.lastIndex)] }; true }")
                    }
                }
                builder.appendLine("        else -> false")
                builder.appendLine("    }")
            }
            val stringListProperties = script.properties.filter { it.isList && it.listElementIsString }
            if (stringListProperties.isNotEmpty()) {
                builder.appendLine()
                builder.appendLine("    override fun setPropertyStringArray(propertyIndex: Int, values: List<String>): Boolean = when (propertyIndex) {")
                script.properties.forEachIndexed { index, property ->
                    if (property.isList && property.listElementIsString) {
                        builder.appendLine("        $index -> { script.${property.kotlinName} = values; true }")
                    }
                }
                builder.appendLine("        else -> false")
                builder.appendLine("    }")
            }
            val valueProperties = script.properties.filter { it.valueTypeClassName.isNotEmpty() }
            if (valueProperties.isNotEmpty()) {
                builder.appendLine()
                builder.appendLine("    override fun setPropertyValue(propertyIndex: Int, value: Any): Boolean = when (propertyIndex) {")
                script.properties.forEachIndexed { index, property ->
                    if (property.valueTypeClassName.isNotEmpty()) {
                        builder.appendLine("        $index -> { script.${property.kotlinName} = value as ${property.valueTypeClassName}; true }")
                    }
                }
                builder.appendLine("        else -> false")
                builder.appendLine("    }")
            }
            // Guardrail: warn on any @ScriptProperty that gets NO iOS delivery case across the
            // set-property blocks above. Such a property silently keeps its Kotlin default (the
            // scene-stored value is dropped) — the class of bug that hid the third-person
            // BeetlebotSkin._force_loop (List<String>) and FPS List<Weapon> drops. The conditions
            // here mirror the five blocks exactly so the warning fires only for genuine gaps.
            script.properties.forEach { property ->
                if (!hasIosPropertyDeliveryCase(property)) {
                    warn(
                        "[kanama-ios] ${script.className}.${property.kotlinName} — no iOS " +
                            "@ScriptProperty delivery case; the scene-stored value will be " +
                            "silently dropped (kept its Kotlin default).",
                    )
                }
            }
            builder.appendLine("}")
        }
        return builder.toString()
    }

    /** True iff [property] gets a delivery case in one of the set-property* blocks. */
    private fun hasIosPropertyDeliveryCase(property: IosProperty): Boolean {
        if (property.isObjectType && property.godotClassName.isNotEmpty()) return true
        if (property.customScriptFqName.isNotEmpty()) return true
        if (!property.isObjectType && !property.isList && property.godotClassName == "String") return true
        if (property.scalarSetExpression.isNotEmpty()) return true
        if (property.isList && property.listElementIsString) return true
        if (property.isList && (property.listElementClassName.isNotEmpty() ||
                property.arrayElementCustomScriptFqName.isNotEmpty())
        ) {
            return true
        }
        if (property.arrayElementEnumFqName.isNotEmpty()) return true
        if (property.valueTypeClassName.isNotEmpty()) return true
        return false
    }

    /** `<Class>Signals` / `<Class>Methods` / `<Class>Names` helper objects. */
    fun constantsSource(): String {
        val builder = StringBuilder()
        builder.appendLine("package net.multigesture.kanama.generated")
        builder.appendLine()
        builder.appendLine("import net.multigesture.kanama.api.kotlinScriptInstance")
        builder.appendLine()
        builder.appendLine("@Suppress(\"unused\")")
        builder.appendLine("private fun emitIosSignal(instance: Any, signalName: String, args: Array<out Any?>) {")
        builder.appendLine("    val script = instance as? net.multigesture.kanama.api.KanamaScript<*> ?: return")
        builder.appendLine("    net.multigesture.kanama.api.GodotObject(script.godotObject).emitSignal(signalName, *args)")
        builder.appendLine("}")
        scripts.forEach { script ->
            if (script.signals.isNotEmpty()) {
                builder.appendLine()
                builder.appendLine("object ${script.className}Signals {")
                script.signals.forEach { signal ->
                    val functionName = constantIdentifier(signal.kotlinName)
                    val signalLiteral = kotlinString(signal.godotName)
                    val fqClassName = "${script.packageName}.${script.className}"
                    builder.appendLine("    fun $functionName(instance: $fqClassName, vararg args: Any?) {")
                    builder.appendLine("        emitIosSignal(instance, $signalLiteral, args)")
                    builder.appendLine("    }")
                }
                builder.appendLine("}")
            }
            val helperMethods = script.methods
                .filter { !it.godotName.startsWith("_") }
            if (helperMethods.isNotEmpty()) {
                val fqClassName = "${script.packageName}.${script.className}"
                builder.appendLine()
                builder.appendLine("object ${script.className}Methods {")
                helperMethods.forEach { method ->
                    val functionName = constantIdentifier(method.kotlinName)
                    // Synthetic positional params (a0, a1, …) typed by the declared arg type, so
                    // method helpers forward to the typed Kotlin method (parity with desktop's
                    // <Class>Methods.<method>(target, args…)). Zero-arg methods keep the prior shape.
                    val typedParams = method.args.mapIndexed { i, a -> ", a$i: ${a.kotlinType}" }.joinToString("")
                    val forwardArgs = method.args.indices.joinToString(", ") { "a$it" }
                    val passthroughArgs = method.args.indices.joinToString("") { ", a$it" }
                    builder.appendLine("    fun $functionName(instance: $fqClassName$typedParams) {")
                    builder.appendLine("        instance.${method.kotlinName}($forwardArgs)")
                    builder.appendLine("    }")
                    builder.appendLine()
                    builder.appendLine("    fun $functionName(target: net.multigesture.kanama.api.GodotObject$typedParams): Boolean {")
                    builder.appendLine("        val instance = target.kotlinScriptInstance<$fqClassName>() ?: return false")
                    builder.appendLine("        $functionName(instance$passthroughArgs)")
                    builder.appendLine("        return true")
                    builder.appendLine("    }")
                }
                builder.appendLine("}")
            }
            if (script.rpcConfigs.isNotEmpty()) {
                val fqClassName = "${script.packageName}.${script.className}"
                builder.appendLine()
                builder.appendLine("object ${script.className}Rpcs {")
                script.rpcConfigs.forEach { rpc ->
                    val method = script.methods.firstOrNull { it.godotName == rpc.godotName }
                    val suffix = constantIdentifier(rpc.godotName).removeSurrounding("`").replaceFirstChar { it.uppercase() }
                    val args = method?.args ?: emptyList()
                    val typedParams = args.joinToString("") { ", ${it.name}: ${it.kotlinType}" }
                    val forwardArgs = args.joinToString("") { ", ${it.name}" }
                    val godotLit = kotlinString(rpc.godotName)
                    builder.appendLine("    fun rpc$suffix(instance: $fqClassName$typedParams): Long =")
                    builder.appendLine("        net.multigesture.kanama.api.Node(instance.godotObject).rpc($godotLit$forwardArgs)")
                    builder.appendLine()
                    builder.appendLine("    fun rpcId$suffix(instance: $fqClassName, peerId: Long$typedParams): Long =")
                    builder.appendLine("        net.multigesture.kanama.api.Node(instance.godotObject).rpcId(peerId, $godotLit$forwardArgs)")
                    if (rpc.callLocal) {
                        builder.appendLine()
                        builder.appendLine("    fun callLocal$suffix(instance: $fqClassName$typedParams) {")
                        builder.appendLine("        net.multigesture.kanama.api.Node(instance.godotObject).callLocalRpc($godotLit$forwardArgs)")
                        builder.appendLine("    }")
                    }
                    builder.appendLine()
                }
                builder.appendLine("}")
            }
            val methods = script.methods
            val properties = script.properties
            val signals = script.signals
            if (methods.isNotEmpty() || properties.isNotEmpty() || signals.isNotEmpty()) {
                builder.appendLine()
                builder.appendLine("object ${script.className}Names {")
                if (methods.isNotEmpty()) {
                    builder.appendLine("    object Methods {")
                    // A single Kotlin function may back several Godot methods (e.g. one handler
                    // annotated @OnInput + @OnUnhandledInput → "_input" and "_unhandled_input").
                    // Those are distinct in `methods` (registration/dispatch need both) but collapse
                    // to one Kotlin-name-keyed const here, so dedupe to avoid conflicting declarations.
                    methods.distinctBy { constantIdentifier(it.kotlinName) }.forEach { method ->
                        builder.appendLine(
                            "        const val ${constantIdentifier(method.kotlinName)}: String = ${kotlinString(method.godotName)}",
                        )
                    }
                    builder.appendLine("    }")
                }
                if (properties.isNotEmpty()) {
                    builder.appendLine("    object Properties {")
                    properties.forEach { property ->
                        builder.appendLine(
                            "        const val ${constantIdentifier(property.kotlinName)}: String = ${kotlinString(property.godotName)}",
                        )
                    }
                    builder.appendLine("    }")
                }
                if (signals.isNotEmpty()) {
                    builder.appendLine("    object Signals {")
                    signals.forEach { signal ->
                        builder.appendLine(
                            "        const val ${constantIdentifier(signal.kotlinName)}: String = ${kotlinString(signal.godotName)}",
                        )
                    }
                    builder.appendLine("    }")
                }
                builder.appendLine("}")
            }
        }
        return builder.toString()
    }

    /** Per-package `removeIf` / `System` JVM-shim compatibility sources (relativePath -> source). */
    fun compatibilitySources(): Map<String, String> =
        scripts
            .mapNotNull { it.packageName }
            .distinct()
            .sorted()
            .associate { packageName ->
                val relativePath = "${packageName.replace('.', '/')}/KanamaIosCompatibility.generated.kt"
                val source = buildString {
                    appendLine("package $packageName")
                    appendLine()
                    appendLine("@Suppress(\"unused\")")
                    appendLine("internal fun <T> MutableCollection<T>.removeIf(predicate: (T) -> Boolean): Boolean {")
                    appendLine("    val originalSize = size")
                    appendLine("    removeAll(predicate)")
                    appendLine("    return size != originalSize")
                    appendLine("}")
                    appendLine()
                    appendLine("@Suppress(\"unused\")")
                    appendLine("internal object System {")
                    appendLine("    fun getenv(name: String): String? = null")
                    appendLine("    object PrintShim { fun println(message: Any?) { kotlin.io.println(message) } }")
                    appendLine("    val err: PrintShim = PrintShim")
                    appendLine("    val out: PrintShim = PrintShim")
                    appendLine("}")
                }
                relativePath to source
            }

    // ---------- rich ScriptModel -> thin iOS model ----------

    private fun IosScriptInput.toIosScript(): IosScript {
        val methods = (model.virtuals.mapNotNull { it.toIosMethod() } + model.methods.map { it.toIosMethod() })
            .distinctBy { it.godotName }
        val properties = model.properties.map { it.toIosProperty(className) }
            .distinctBy { it.godotName }
        val signals = model.signals.map { IosSignal(it.godotName, it.godotName) }
            .distinctBy { it.godotName }
        val rpcConfigs = model.methods.mapNotNull { method ->
            method.rpc?.let { rpc ->
                IosRpcConfig(
                    godotName = method.godotName,
                    mode = rpc.mode,
                    callLocal = rpc.callLocal,
                    transferMode = rpc.transferMode,
                    channel = rpc.channel,
                )
            }
        }.distinctBy { it.godotName }
        return IosScript(
            resourcePath = resourcePath,
            packageName = packageName,
            className = className,
            baseType = model.attachTo,
            methods = methods,
            properties = properties,
            signals = signals,
            rpcConfigs = rpcConfigs,
        )
    }

    // All Godot lifecycle/input virtuals become dispatchable iOS methods (Phase 3.4 — the old
    // IOS_UNWIRED_FUNCTION_ANNOTATIONS set is empty). Tree virtuals (_enter_tree/_exit_tree/_ready)
    // arrive via the script-instance notification callback; per-frame/input virtuals
    // (_process/_physics_process/_input/_unhandled_input/_shortcut_input/_unhandled_key_input)
    // arrive via the generic call callback once the C side enables the matching processing flag.
    // `_input_event` is just a regular multi-arg method via the generic path.
    //
    // Phase 5.3a: arbitrary VOID @OverrideVirtual virtuals (e.g. _draw, _gui_input, drag-and-drop
    // _drop_data) dispatch through the same generic call path as a regular method — the engine
    // calls them on the script instance via the call callback once they appear in the method list.
    // Value-returning virtuals (returnType != null) still need the iOS `callV` return-marshalling
    // primitive (Phase 5.3b) and are skipped + warned until then.
    private fun VirtualModel.toIosMethod(): IosMethod? = when {
        virtualName in lifecycleIosVirtuals ->
            IosMethod(virtualName, kotlinMethodName, args)
        returnType == null ->
            IosMethod(virtualName, kotlinMethodName, args)
        returnType in iosReturnTypes ->
            IosMethod(virtualName, kotlinMethodName, args, returnType)
        else -> {
            warn("[kanama-ios] $kotlinMethodName ($virtualName): @OverrideVirtual return type " +
                "$returnType not yet marshalled on iOS (supported: Bool/Int/Float/Vector2/" +
                "Vector2i/Vector3/String/RID/Packed*Array/Dictionary/Array/Variant) — silent no-op")
            null
        }
    }

    private fun MethodModel.toIosMethod(): IosMethod =
        IosMethod(
            godotName = godotName,
            kotlinName = kotlinName,
            args = args,
            returnType = returnType?.takeIf { it in iosReturnTypes },
        )

    /** TypeMapping arg types the C inbound marshalling + decode handle (besides OBJECT wrappers). */
    private val iosCallArgTypes = setOf(
        TypeMapping.INT, TypeMapping.FLOAT, TypeMapping.BOOL, TypeMapping.STRING,
        TypeMapping.NODE_PATH, TypeMapping.VECTOR2, TypeMapping.VECTOR2I, TypeMapping.VECTOR3,
    )

    /**
     * Kotlin expression for call arg [i] (`args[i]` is the decoded value), cast/wrapped to its
     * declared type. Null when the arg type isn't marshalled by the iOS inbound path (the method
     * is then skipped + warned, same boundary as before but keyed on audited type not call shape).
     */
    private fun callArgExpr(i: Int, a: ArgModel): String? {
        val cell = "args[$i]"
        a.objectWrapperFqName?.let { fq ->
            val w = "net.multigesture.kanama.api.${fq.substringAfterLast('.')}"
            return if (a.nullable) {
                "($cell as Long).let { if (it != 0L) $w(MemorySegment.ofAddress(it)) else null }"
            } else {
                "$w(MemorySegment.ofAddress($cell as Long))"
            }
        }
        return if (a.type in iosCallArgTypes) "$cell as ${a.type.kotlinType}" else null
    }

    private fun ScriptPropertyModel.toIosProperty(className: String): IosProperty {
        val isList = type == TypeMapping.ARRAY
        val isObject = objectWrapperFqName != null
        // A user @ScriptClass-typed OBJECT property (e.g. `target: Vehicle?` exported via node_paths):
        // delivered as the owner handle, resolved to the live Kotlin script instance at set time.
        val customScript = if (type == TypeMapping.OBJECT && customScriptFqName != null) customScriptFqName else ""
        val listElementClassName = arrayElementWrapperFqName?.substringAfterLast('.') ?: ""
        var valueTypeClassName = ""
        val godotClassName = when {
            isObject -> objectWrapperFqName.substringAfterLast('.')
            isList -> ""
            customScript.isNotEmpty() -> ""
            enumFqName != null -> "Long"
            narrow == NarrowScalar.FLOAT32 -> "Double"
            narrow == NarrowScalar.INT32 -> "Long"
            else -> when (type) {
                TypeMapping.INT -> "Long"
                TypeMapping.FLOAT -> "Double"
                TypeMapping.BOOL -> "Boolean"
                TypeMapping.STRING -> "String"
                // Value types delivered through the set-property value path: the C side ships
                // PT-tagged bytes, the runtime decodes them, and setPropertyValue assigns the
                // typed value. godotClassName stays empty (no setProperty Long case).
                TypeMapping.NODE_PATH, TypeMapping.VECTOR2, TypeMapping.VECTOR3 -> {
                    valueTypeClassName = type.kotlinType
                    ""
                }
                // Remaining value types (Vector2i/Vector3i/Quaternion/Basis/…) still lack a C
                // marshalling case: emit no setProperty case, keep the Kotlin default.
                else -> {
                    warn("[kanama-ios] $className.$kotlinName ($type) — no iOS @ScriptProperty path for this value type, will keep its Kotlin default")
                    ""
                }
            }
        }
        val scalarSetExpression = when {
            isObject || isList || customScript.isNotEmpty() -> ""
            enumFqName != null ->
                "$enumFqName.entries[value.toInt().coerceIn(0, $enumFqName.entries.lastIndex)]"
            narrow == NarrowScalar.FLOAT32 -> "Double.fromBits(value).toFloat()"
            narrow == NarrowScalar.INT32 -> "value.toInt()"
            type == TypeMapping.INT -> "value"
            type == TypeMapping.FLOAT -> "Double.fromBits(value)"
            type == TypeMapping.BOOL -> "value != 0L"
            else -> ""
        }
        val scalarGetExpression = when {
            isObject || isList || customScript.isNotEmpty() -> ""
            enumFqName != null -> "script.$kotlinName.ordinal.toLong()"
            scalarSetExpression.isNotEmpty() -> "script.$kotlinName"
            // Types that write through a dedicated set path (Vector2/Vector3/NodePath via
            // setPropertyValue, String via setPropertyString) have an empty scalarSetExpression — but
            // they are still *readable*: encodeIosReturn and the C pt_return_to_variant path handle
            // all of them. Without a getProperty branch the engine reads them back as nil, which
            // silently breaks MultiplayerSynchronizer replication of value-type @ScriptProperty (e.g.
            // a Vector2 `motion` or Vector3 `shoot_target`) on the authority peer — the value never
            // leaves the sending device. (String/NodePath have the identical defect; folded in here.)
            type == TypeMapping.VECTOR2 || type == TypeMapping.VECTOR3 ||
                type == TypeMapping.NODE_PATH || type == TypeMapping.STRING -> "script.$kotlinName"
            else -> ""
        }
        // Get/set parity guard: a *data* @ScriptProperty the engine can set but not read back is
        // write-only on iOS — the engine gets nil, silently breaking MultiplayerSynchronizer
        // replication and inspector reads (exactly how the Vector2 `motion` / Vector3 `shoot_target`
        // bug hid). Object and custom-script refs are intentionally excluded: replicating an object
        // handle across peers is meaningless and the inspector edits them via the node picker, not
        // get(). With Vector2/Vector3/String/NodePath and List<String> now readable this stays silent
        // in a healthy codebase and only trips if a new data type is added set-only (or one regresses).
        // This is a hard build ERROR, not a warning: a warning is exactly what let the original
        // write-only asymmetry ship unnoticed. A new settable data type must gain a getProperty path
        // (or be explicitly excluded here) before it can compile.
        val engineReadableData = scalarGetExpression.isNotEmpty() || (isList && arrayElementString)
        val engineSettableDataType = valueTypeClassName.isNotEmpty() ||
            (!isObject && !isList && godotClassName == "String") ||
            (isList && arrayElementString)
        if (engineSettableDataType && !engineReadableData) {
            error(
                "[kanama-ios] $className.$kotlinName ($type) — data @ScriptProperty is write-only on " +
                    "iOS: settable from scene data / Object.set, but the engine reads it back as nil " +
                    "(no getProperty path). MultiplayerSynchronizer replication and the inspector cannot " +
                    "get() this property. Add an encodeIosReturn case + a getProperty branch to fix.",
            )
        }
        val godotVariantType = when {
            isObject -> 24 // OBJECT
            isList && arrayElementString -> 34 // PACKED_STRING_ARRAY (List<String>)
            isList -> 28 // ARRAY
            else -> godotVariantTypeFor(type)
        }
        return IosProperty(
            godotName = godotName,
            kotlinName = kotlinName,
            isObjectType = isObject,
            godotClassName = godotClassName,
            isList = isList,
            listElementClassName = listElementClassName,
            isNullable = nullable,
            valueTypeClassName = valueTypeClassName,
            godotVariantType = godotVariantType,
            hint = hint,
            hintString = hintString,
            usage = usage,
            customScriptFqName = customScript,
            arrayElementCustomScriptFqName = if (isList) arrayElementCustomScriptFqName.orEmpty() else "",
            listElementIsString = isList && arrayElementString,
            scalarSetExpression = scalarSetExpression,
            scalarGetExpression = scalarGetExpression,
            arrayElementEnumFqName = if (isList) arrayElementEnumFqName.orEmpty() else "",
        )
    }

    /** Maps a [TypeMapping] to its Godot Variant::Type integer (engine enum). */
    private fun godotVariantTypeFor(type: TypeMapping): Int = when (type) {
        TypeMapping.BOOL -> 1
        TypeMapping.INT -> 2
        TypeMapping.FLOAT -> 3
        TypeMapping.STRING -> 4
        TypeMapping.VECTOR2 -> 5
        TypeMapping.VECTOR2I -> 6
        TypeMapping.VECTOR3 -> 9
        TypeMapping.VECTOR3I -> 10
        TypeMapping.QUATERNION -> 15
        TypeMapping.BASIS -> 17
        TypeMapping.NODE_PATH -> 22
        TypeMapping.OBJECT -> 24
        TypeMapping.ARRAY -> 28
        TypeMapping.PACKED_STRING_ARRAY -> 34
        TypeMapping.VARIANT -> 0 // NIL — a Variant/"any" property advertises no fixed type
        // task 29 return-only shapes (never @ScriptProperty types, but the when must be
        // exhaustive) — the engine Variant::Type values, matching VariantType.kt.
        TypeMapping.RECT2 -> 7
        TypeMapping.TRANSFORM2D -> 11
        TypeMapping.AABB -> 16
        TypeMapping.TRANSFORM3D -> 18
        TypeMapping.PROJECTION -> 19
        TypeMapping.RID -> 23
        TypeMapping.DICTIONARY -> 27
        TypeMapping.PACKED_BYTE_ARRAY -> 29
        TypeMapping.PACKED_INT32_ARRAY -> 30
        TypeMapping.PACKED_INT64_ARRAY -> 31
        TypeMapping.PACKED_FLOAT32_ARRAY -> 32
        TypeMapping.PACKED_FLOAT64_ARRAY -> 33
        TypeMapping.PACKED_VECTOR2_ARRAY -> 35
        TypeMapping.PACKED_VECTOR3_ARRAY -> 36
        TypeMapping.PACKED_COLOR_ARRAY -> 37
    }

    // ---------- string helpers (reproduce the build.gradle.kts versions exactly) ----------

    private fun shortHash(value: String): String =
        MessageDigest.getInstance("SHA-256")
            .digest(value.toByteArray(Charsets.UTF_8))
            .take(8)
            .joinToString("") { "%02x".format(it) }

    private fun kotlinString(value: String): String =
        buildString {
            append('"')
            value.forEach { ch ->
                when (ch) {
                    '\\' -> append("\\\\")
                    '"' -> append("\\\"")
                    '\n' -> append("\\n")
                    '\r' -> append("\\r")
                    '\t' -> append("\\t")
                    else -> append(ch)
                }
            }
            append('"')
        }

    private fun constantIdentifier(name: String): String {
        val stripped = name.trim('_')
        if (stripped.isBlank()) return "value"
        val words = stripped
            .split('_', '-', ' ')
            .filter { it.isNotBlank() }
        val candidate = words.drop(1).fold(words.first()) { acc, part ->
            acc + part.replaceFirstChar { it.uppercaseChar() }
        }
        return candidate.replace(Regex("""[^A-Za-z0-9_]"""), "_")
            .let { if (it.firstOrNull()?.isDigit() == true) "_$it" else it }
    }
}

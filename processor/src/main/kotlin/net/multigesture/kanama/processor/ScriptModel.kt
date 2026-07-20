package net.multigesture.kanama.processor

// The platform-neutral script model: the annotation-derived description of a @ScriptClass
// (and @RegisterClass) — its properties, methods, virtuals, signals — independent of how
// any one platform marshals or emits it. The KanamaProcessor builds these from the KSP
// symbol API; the JVM CodeEmitter/ScriptCodeEmitter consume them to generate registrars.
//
// Phase 3.1 (script-model-unification-design.md) turns this into the single source of
// truth for desktop/Android/iOS: it will be serialized (JSON) and consumed by the iOS
// build instead of the parseIosScript regex parser.
//
// NOTE (Phase 3.1 sub-step 1b, not yet done): ArgModel.readFromScratch/readPtrcallArg
// below still emit JVM (MemorySegment/ptrcall) codegen as strings — they are the one
// platform-specific bleed left in the model. They move to the JVM emitter side as the
// model is fully neutralized for serialization; kept here now so this extraction stays a
// byte-identical relocation.

internal data class ClassModel(
    val simpleName: String,
    val fqName: String,
    val parentClassName: String,
    val isTool: Boolean,
    val methods: List<MethodModel>,
    val properties: List<PropertyModel>,
    val virtuals: List<VirtualModel>,
    val signals: List<SignalModel>,
)

internal data class SignalModel(
    val godotName: String,
    val args: List<ArgModel>,
)

internal enum class MethodKind { REGULAR, PROPERTY_GETTER, PROPERTY_SETTER }

internal data class MethodModel(
    val kotlinName: String,
    val godotName: String,
    val returnType: TypeMapping?,
    val args: List<ArgModel>,
    val kind: MethodKind,
    val rpc: RpcModel? = null,
    /** For property accessors: the property's Kotlin name. */
    val propertyKotlinName: String? = null,
)

internal data class RpcModel(
    val mode: Int,
    val callLocal: Boolean,
    val transferMode: Int,
    val channel: Int,
)

internal data class ArgModel(
    val name: String,
    val type: TypeMapping,
    val objectWrapperFqName: String? = null,
    val nullable: Boolean = false,
    val hasDefault: Boolean = false,
) {
    /** Neutral: the Kotlin type the arg surfaces as (object wrapper FQN or mapped type). */
    val kotlinType: String
        get() = (objectWrapperFqName ?: type.kotlinType) + if (nullable) "?" else ""
}

// ArgModel's JVM-emit codegen (readFromScratch/readPtrcallArg/signalEmitValueExpr) lives
// with the JVM emitter in KanamaProcessor.kt as extension functions — not on the model —
// so the model stays a pure, serializable data holder (Phase 3.1).

internal data class PropertyModel(
    val kotlinName: String,
    val godotName: String,
    val type: TypeMapping,
    val isMutable: Boolean,
    val hint: Int = 0,
    val hintString: String = "",
    val usage: Int = 6,
)

internal data class VirtualModel(
    val virtualName: String,
    /** Name of the generated static upcall function (e.g. `callReady`). */
    val callFunctionName: String,
    /** Kotlin method to invoke on the user instance. */
    val kotlinMethodName: String,
    /** Ptrcall-convention args passed by Godot when invoking the virtual. */
    val args: List<ArgModel> = emptyList(),
    /**
     * Return type for value-returning virtuals (e.g. `_get_minimum_size` ->
     * Vector2). `null` for `void` virtuals (all the lifecycle ones). Set for
     * `@OverrideVirtual` declarations; drives return marshalling in
     * `dispatchCall` and the reported `return_type` in the method list.
     */
    val returnType: TypeMapping? = null,
)

// ---------- @ScriptClass models ----------

internal data class ScriptModel(
    val simpleName: String,
    val fqName: String,
    val attachTo: String,
    val isTool: Boolean,
    val isGlobalClass: Boolean,
    val properties: List<ScriptPropertyModel>,
    val toolButtons: List<ToolButtonModel>,
    val virtuals: List<VirtualModel>,
    val methods: List<MethodModel>,
    val signals: List<SignalModel>,
)

internal data class ToolButtonModel(
    val propertyName: String,
    val text: String,
    val icon: String,
    val method: MethodModel,
) {
    val hintString: String
        get() = if (icon.isEmpty()) text else "$text,$icon"
}

/**
 * Narrow Kotlin scalar surfaced for a 64-bit Godot Variant slot. Godot floats
 * and ints are always 64-bit on the wire; a `kotlin.Float`/`kotlin.Int`
 * property maps to Variant FLOAT/INT with widening on get and narrowing on
 * set. Both the JVM and iOS emitters generate the corresponding conversion.
 */
internal enum class NarrowScalar(
    val kotlinType: String,
    val zeroLiteral: String,
    /** Appended when the narrow Kotlin value flows out to a 64-bit slot. */
    val toWide: String,
    /** Appended when a 64-bit slot value is assigned into the Kotlin field. */
    val fromWide: String,
) {
    FLOAT32("Float", "0f", ".toDouble()", ".toFloat()"),
    INT32("Int", "0", ".toLong()", ".toInt()"),
}

internal data class ScriptPropertyModel(
    val kotlinName: String,
    val godotName: String,
    val type: TypeMapping,
    val isMutable: Boolean,
    val hint: Int = 0,
    val hintString: String = "",
    val defaultLiteral: String? = null,
    val exportCategory: ScriptPropertyGroupModel? = null,
    val exportGroup: ScriptPropertyGroupModel? = null,
    val exportSubgroup: ScriptPropertyGroupModel? = null,
    val usage: Int = 6,
    val objectWrapperFqName: String? = null,
    val arrayElementWrapperFqName: String? = null,
    val customScriptFqName: String? = null,
    val arrayElementCustomScriptFqName: String? = null,
    val customScriptIsResource: Boolean = false,
    val arrayElementCustomScriptIsResource: Boolean = false,
    val arrayElementString: Boolean = false,
    /**
     * Source-level nullability of the property type. The JVM emitter does not need it
     * (VariantConverters handle null), but the iOS emitter does: an object-typed
     * `@ScriptProperty` delivers `null` for a 0 handle only when the Kotlin field is
     * nullable. Phase 3.2 ([IosScriptCodeEmitter]).
     */
    val nullable: Boolean = false,
    /** Set for kotlin.Float / kotlin.Int properties (Variant slot stays 64-bit). */
    val narrow: NarrowScalar? = null,
    /**
     * Set for Kotlin `enum class` properties (task 32, issue #37). The Variant slot is
     * INT carrying the ordinal (C# export parity); registration adds PROPERTY_HINT_ENUM
     * with [enumEntries] as the hint string so the inspector renders a dropdown.
     */
    val enumFqName: String? = null,
    /** Ordered entry names of the enum class (ordinal order). Empty unless [enumFqName] is set. */
    val enumEntries: List<String> = emptyList(),
    /**
     * Set for `List<enum class>` properties (task 38, issue #40). The Variant slot is a
     * typed int Array (hint string `"2/2:<entries>"`) carrying ordinals per element — the
     * array form of the [enumFqName] scalar export (C# enum-array parity).
     */
    val arrayElementEnumFqName: String? = null,
    /** Ordered entry names of the element enum. Empty unless [arrayElementEnumFqName] is set. */
    val arrayElementEnumEntries: List<String> = emptyList(),
    /**
     * Typed `Map<K, V>` export (issue #40). The Variant slot is `DICTIONARY` registered with
     * `PROPERTY_HINT_DICTIONARY_TYPE` and a `"<keyEncoding>;<valueEncoding>"` hint string. These
     * [mapKey*]/[mapValue*] fields are the dictionary analogue of the [arrayElement*] fields and
     * are non-null only when the property is a typed Map. A plain `Map<String, Any?>` export stays
     * untyped (no map fields, hint 0) and rides the generic DICTIONARY read/write path.
     *
     * [mapKeyKotlinType] is the resolved Kotlin key type fq (`kotlin.String`, `kotlin.Long`,
     * `kotlin.Int`, or an enum class fq); its non-nullness is the "typed Map" discriminator.
     */
    val mapKeyKotlinType: String? = null,
    /** Ordered entry names when the key is an enum class; empty otherwise. */
    val mapKeyEnumEntries: List<String> = emptyList(),
    /** Set when the value is a scalar/String/value-type; the resolved Kotlin value type fq. */
    val mapValueKotlinType: String? = null,
    /** Set when the value is an engine object/resource wrapper (e.g. `Texture2D`). */
    val mapValueWrapperFqName: String? = null,
    /** Set when the value is a custom `@ScriptClass` script instance. */
    val mapValueCustomScriptFqName: String? = null,
    /** True when [mapValueCustomScriptFqName] attaches to `Resource` (ownership-managed read). */
    val mapValueCustomScriptIsResource: Boolean = false,
    /** Set when the value is an enum class; stored as ordinals (clamp-on-read like list enums). */
    val mapValueEnumFqName: String? = null,
    /** Ordered entry names when the value is an enum class; empty otherwise. */
    val mapValueEnumEntries: List<String> = emptyList(),
    /**
     * True when the map value type is declared nullable (`Map<K, V?>`). Mirrors Godot C#, whose
     * engine-backed `Godot.Collections.Dictionary` preserves a key whose value is nil. When true,
     * a nil / wrong-typed value decodes to `null` and the key is preserved; when false, Kotlin's
     * type system cannot hold a null there, so the entry is dropped (like the typed-array nil-drop).
     */
    val mapValueNullable: Boolean = false,
    /** True when the original property is `MutableMap` instead of immutable `Map`. */
    val isMutableMap: Boolean = false,
    /** True when the original property is `MutableList` instead of immutable `List`. */
    val isMutableList: Boolean = false,
)

internal data class ScriptPropertyGroupModel(
    val name: String,
    val prefix: String,
    val usage: Int,
)

internal data class ScriptPropertyTypeModel(
    val type: TypeMapping,
    val hint: Int = 0,
    val hintString: String = "",
    val objectWrapperFqName: String? = null,
    val arrayElementWrapperFqName: String? = null,
    val customScriptFqName: String? = null,
    val arrayElementCustomScriptFqName: String? = null,
    val customScriptIsResource: Boolean = false,
    val arrayElementCustomScriptIsResource: Boolean = false,
    val arrayElementString: Boolean = false,
    val narrow: NarrowScalar? = null,
    val enumFqName: String? = null,
    val enumEntries: List<String> = emptyList(),
    val arrayElementEnumFqName: String? = null,
    val arrayElementEnumEntries: List<String> = emptyList(),
    val mapKeyKotlinType: String? = null,
    val mapKeyEnumEntries: List<String> = emptyList(),
    val mapValueKotlinType: String? = null,
    val mapValueWrapperFqName: String? = null,
    val mapValueCustomScriptFqName: String? = null,
    val mapValueCustomScriptIsResource: Boolean = false,
    val mapValueEnumFqName: String? = null,
    val mapValueEnumEntries: List<String> = emptyList(),
    val mapValueNullable: Boolean = false,
    val isMutableMap: Boolean = false,
    val isMutableList: Boolean = false,
)

internal data class ScriptClassTypeInfo(
    val fqName: String,
    val simpleName: String,
    val attachTo: String,
    val isGlobalClass: Boolean,
) {
    val isExportableResource: Boolean
        get() = isGlobalClass && attachTo == "Resource"
    val isExportableGlobal: Boolean
        get() = isGlobalClass
    val propertyHint: Int
        get() = if (isExportableResource) 17 else 34
}

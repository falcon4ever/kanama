package net.multigesture.kanama.ios

// The seam between the iOS runtime and the code Kanama's processor generates for a project's
// scripts (IosScriptCodeEmitter): every runtime declaration that code names, besides the
// KanamaIosNoReturn / KanamaIosNoProperty / KanamaIosTypedDictionary sentinels in
// KanamaIosRuntime.kt. Public behind @InternalKanamaIosApi because a kit project compiles that code
// in its own module against the prebuilt runtime klib (task 135 item 5);
// api-snapshots/ios-seam.txt records them (check_public_signature_changes.py).

@InternalKanamaIosApi
data class KanamaIosScriptMethod(val name: String, val argumentCount: Int = 0)

@InternalKanamaIosApi
data class KanamaIosScriptProperty(
  val name: String,
  // Godot Variant::Type, so the script instance can advertise this @Export to the
  // engine (get_property_list) — required for scene-stored values to be applied. Default 0
  // (NIL) keeps older generated registrars compiling.
  val variantType: Int = 0,
  val hint: Int = 0,
  val hintString: String = "",
  val usage: Int = 6,
  // PropertyInfo.class_name when the generator knows it (task 128 B: a Godot-enum property names
  // its enum, `Node.ProcessMode`). Empty falls back to the object-property rule below.
  val className: String = "",
  // The @Export's default (task 138 item 8): its initializer literal, in the form getProperty
  // returns, when the generator could read one (desktop's registrar has the same literal);
  // [KanamaIosNoProperty] when not. Script._get_property_default_value answers it.
  val defaultValue: Any? = KanamaIosNoProperty,
)

@InternalKanamaIosApi data class KanamaIosScriptSignal(val name: String)

@InternalKanamaIosApi
data class KanamaIosRpcConfig(
  val methodName: String,
  val mode: Int,
  val callLocal: Boolean,
  val transferMode: Int,
  val channel: Int,
)

@InternalKanamaIosApi
data class KanamaIosScriptDescriptor(
  val path: String,
  val baseType: String,
  val methods: List<KanamaIosScriptMethod>,
  val properties: List<KanamaIosScriptProperty>,
  val signals: List<KanamaIosScriptSignal>,
  val rpcConfigs: List<KanamaIosRpcConfig> = emptyList(),
  val factory: (Long) -> KanamaIosScriptBridge?,
)

@InternalKanamaIosApi
interface KanamaIosScriptBridge {
  // Generic per-signature inbound dispatch (Phase 3.3): [args] are the already-decoded Kotlin
  // values (one per method param, in order); the generated branch casts/wraps each to its
  // declared type and invokes the script method.
  fun callV(methodName: String, args: List<Any?>): Boolean = false

  // Phase 5.3b: value-returning methods/virtuals. Returns the typed result (Bool/Long/Double/
  // Vector2/Vector2i) for the engine return slot, or [KanamaIosNoReturn] if this method has no
  // marshalled return (the void [callV] path then handles it).
  fun callVReturning(methodName: String, args: List<Any?>): Any? = KanamaIosNoReturn

  fun setProperty(propertyIndex: Int, value: Long): Boolean = false

  fun getProperty(propertyIndex: Int): Any? = KanamaIosNoProperty

  fun setPropertyString(propertyIndex: Int, value: String): Boolean = false

  fun setPropertyObjectArray(propertyIndex: Int, values: LongArray): Boolean = false

  // Integer Array @Export delivery. Kept separate from object arrays so malformed
  // scene data can never turn an integer cell into a bogus object handle. The generated
  // List<Enum> bridge converts these ordinals to enum entries with desktop-identical clamping.
  fun setPropertyIntArray(propertyIndex: Int, values: LongArray): Boolean = false

  // `List<String>` (Godot PackedStringArray) @Export delivery. The C side extracts the
  // packed array's elements to utf8 C strings and hands them here; the generated
  // setPropertyStringArray branch assigns the list to the field. See the C
  // PACKED_STRING_ARRAY set-property dispatch case + the emitter's string-list block.
  fun setPropertyStringArray(propertyIndex: Int, values: List<String>): Boolean = false

  // Value-type @Export delivery (NodePath/Vector2/Vector3/Color). [value] is the
  // already-decoded Kotlin value; the generated branch casts it to the field's type. See
  // the C set-property value path + setScriptInstancePropertyValue's tag decode.
  fun setPropertyValue(propertyIndex: Int, value: Any): Boolean = false

  // Map @Export delivery (a typed `Map<K, V>` or `Map<String, Any?>`): [entries] is the Dictionary
  // with its keys and values decoded as they are (desktop: readVariantDictionaryAny); the generated
  // branch converts them to the field's key and value types and drops what does not fit.
  fun setPropertyDictionary(propertyIndex: Int, entries: Map<Any?, Any?>): Boolean = false

  // For a Map property whose values are Resources: the values the field holds after the set (the
  // converted Map, so an entry dropped for its key or class is not kept), on which the runtime
  // takes one reference each ([KanamaIosRuntime.retainScriptInstancePropertyObjects]), as desktop's
  // retained Dictionary read does; null for any other property.
  fun dictionaryRetainedValues(propertyIndex: Int): Collection<Any?>? = null

  val scriptInstance: Any?
    get() = null
}

/**
 * The project's `@ScriptClass` scripts. The processor generates a
 * `@CName("kanama_ios_project_scripts_register")` function (IosScriptCodeEmitter) that registers
 * each one here; the C shim calls it once from `kanama_entry`, after the runtime started. It is
 * bound at link time, so the runtime never names generated code: the scripts compile in the
 * runtime's own module in a Kanama checkout and in a module of their own against the prebuilt
 * runtime klib in a kit project (`buildIosScripts`). A build with no scripts has no such function,
 * and the shim's weak reference to it is null.
 */
@InternalKanamaIosApi
object KanamaIosProjectRegistry {
  private val descriptors = linkedMapOf<String, KanamaIosScriptDescriptor>()

  fun register(descriptor: KanamaIosScriptDescriptor) {
    descriptors[descriptor.path] = descriptor
  }

  internal fun descriptor(path: String): KanamaIosScriptDescriptor? = descriptors[path]
}

/** The Kotlin script object attached to [ownerObject], or null (the generated bridges' lookup). */
@InternalKanamaIosApi
fun iosScriptInstanceForOwner(ownerObject: Long): Any? =
  KanamaIosRuntime.scriptInstanceForOwner(ownerObject)

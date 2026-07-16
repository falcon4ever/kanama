package net.multigesture.kanama.binding

import net.multigesture.kanama.binding.runtime.GodotStructs
import net.multigesture.kanama.binding.runtime.BuiltinTypes
import net.multigesture.kanama.binding.runtime.GodotStrings
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.VariantConverters
import net.multigesture.kanama.binding.runtime.VariantType
import net.multigesture.kanama.api.KanamaCoroutineOwner
import net.multigesture.kanama.ffi.GodotFFI
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_BYTE
import java.lang.foreign.ValueLayout.JAVA_DOUBLE
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.foreign.ValueLayout.JAVA_LONG
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.util.concurrent.ConcurrentHashMap

/**
 * Manages the shared [GDExtensionScriptInstanceInfo3] vtable and the
 * [script_instance_create3] call that turns a handle into an opaque
 * Godot ScriptInstance pointer.
 *
 * **One shared vtable** is created lazily and reused for every
 * [KanamaScriptInstance] created via [create]. The `p_instance_data`
 * pointer passed to the engine equals `MemorySegment.ofAddress(handle)`,
 * where `handle` is the [ObjectRegistry] key for the [KanamaScriptInstance].
 *
 * All 25 mandatory function-pointer slots are filled; the optional
 * `get_class_category_func` is left NULL (Godot uses a default).
 */
object ScriptBridge {
    private const val REFCOUNTED_REFERENCE_HASH = 2240911060L
    private const val REFCOUNTED_UNREFERENCE_HASH = 2240911060L
    private val kotlinObjectByOwnerAddress = ConcurrentHashMap<Long, Any>()
    private val scriptInstanceByOwnerAddress = ConcurrentHashMap<Long, KanamaScriptInstance>()
    private val nullScriptPropertyValue = Any()
    private val pendingKanamaScriptOwnerAddresses = ConcurrentHashMap.newKeySet<Long>()
    private val pendingScriptPropertyValuesByOwnerAddress =
        ConcurrentHashMap<Long, MutableMap<String, Any>>()

    // ---- GDExtension interface functions ----

    private val scriptInstanceCreate3 by lazy {
        GodotFFI.lookup(
            "script_instance_create3",
            FunctionDescriptor.of(ADDRESS, ADDRESS, ADDRESS),
        )
    }

    // ---- Shared info3 vtable ----

    /** The single permanently-allocated GDExtensionScriptInstanceInfo3 struct. */
    private val info3: MemorySegment by lazy { buildInfo3() }

    /**
     * Create a Godot ScriptInstance for the [KanamaScriptInstance] stored
     * under [handle] in [ObjectRegistry].
     *
     * Returns the opaque [GDExtensionScriptInstancePtr] that the engine
     * uses to route callbacks back to us.
     */
    fun create(handle: Long): MemorySegment =
        scriptInstanceCreate3.invoke(info3, MemorySegment.ofAddress(handle)) as MemorySegment

    fun kotlinObjectForOwner(ownerObject: MemorySegment): Any? =
        kotlinObjectByOwnerAddress[ownerObject.address()]

    fun trackKotlinObject(ownerObject: MemorySegment, kotlinObject: Any) {
        if (ownerObject.address() != 0L) {
            kotlinObjectByOwnerAddress[ownerObject.address()] = kotlinObject
        }
    }

    fun noteSetScript(ownerObject: MemorySegment, scriptObject: MemorySegment) {
        val ownerAddress = ownerObject.address()
        if (ownerAddress == 0L) return
        if (scriptObject.address() != 0L && KanamaScript.byObjectAddress(scriptObject.address()) != null) {
            pendingKanamaScriptOwnerAddresses.add(ownerAddress)
        } else {
            pendingKanamaScriptOwnerAddresses.remove(ownerAddress)
            pendingScriptPropertyValuesByOwnerAddress.remove(ownerAddress)
        }
    }

    fun applyOrRecordScriptPropertySet(ownerObject: MemorySegment, property: String, value: Any?) {
        if (!shouldRecordScriptPropertySet(property)) return
        val ownerAddress = ownerObject.address()
        if (ownerAddress == 0L) return
        if (scriptInstanceByOwnerAddress[ownerAddress] != null) {
            return
        }
        if (!ownerObjectHasKanamaScript(ownerObject)) {
            return
        }

        pendingScriptPropertyValuesByOwnerAddress
            .computeIfAbsent(ownerAddress) { ConcurrentHashMap() }[property] = value ?: nullScriptPropertyValue
    }

    private fun shouldRecordScriptPropertySet(property: String): Boolean =
        // Slash-separated paths are indexed engine properties, not generated script property names.
        '/' !in property

    fun trackScriptInstance(ownerObject: MemorySegment, scriptInstance: KanamaScriptInstance) {
        if (ownerObject.address() != 0L) {
            pendingKanamaScriptOwnerAddresses.remove(ownerObject.address())
            scriptInstanceByOwnerAddress[ownerObject.address()] = scriptInstance
        }
    }

    // ---- Vtable construction ----

    private fun buildInfo3(): MemorySegment {
        val struct = GodotFFI.arena.allocate(GodotStructs.scriptInstanceInfo3)
        val lookup = MethodHandles.lookup()

        fun stub(name: String, type: MethodType, desc: FunctionDescriptor): MemorySegment =
            GodotFFI.linker.upcallStub(
                lookup.findStatic(ScriptBridge::class.java, name, type),
                desc, GodotFFI.arena,
            )

        // Shared MethodType / FunctionDescriptor constants.
        val voidData         = MethodType.methodType(Void.TYPE, MemorySegment::class.java)
        val boolData         = MethodType.methodType(java.lang.Byte.TYPE, MemorySegment::class.java)
        val addrData         = MethodType.methodType(MemorySegment::class.java, MemorySegment::class.java)
        val boolDataAddr     = MethodType.methodType(java.lang.Byte.TYPE, MemorySegment::class.java, MemorySegment::class.java)
        val boolDataAddrAddr = MethodType.methodType(java.lang.Byte.TYPE, MemorySegment::class.java, MemorySegment::class.java, MemorySegment::class.java)
        val addrDataAddr     = MethodType.methodType(MemorySegment::class.java, MemorySegment::class.java, MemorySegment::class.java)
        val voidDataAddrInt  = MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java, java.lang.Integer.TYPE)
        val voidDataAddrAddr = MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java, MemorySegment::class.java)
        val voidDataIntByte  = MethodType.methodType(Void.TYPE, MemorySegment::class.java, java.lang.Integer.TYPE, java.lang.Byte.TYPE)
        val intDataAddrAddr  = MethodType.methodType(java.lang.Integer.TYPE, MemorySegment::class.java, MemorySegment::class.java, MemorySegment::class.java)
        val longDataAddrAddr = MethodType.methodType(java.lang.Long.TYPE, MemorySegment::class.java, MemorySegment::class.java, MemorySegment::class.java)
        val voidDataAddrAddrLongAddrAddr = MethodType.methodType(
            Void.TYPE,
            MemorySegment::class.java, MemorySegment::class.java, MemorySegment::class.java,
            java.lang.Long.TYPE,
            MemorySegment::class.java, MemorySegment::class.java,
        )

        val descVoidData         = FunctionDescriptor.ofVoid(ADDRESS)
        val descBoolData         = FunctionDescriptor.of(JAVA_BYTE, ADDRESS)
        val descAddrData         = FunctionDescriptor.of(ADDRESS, ADDRESS)
        val descBoolDataAddr     = FunctionDescriptor.of(JAVA_BYTE, ADDRESS, ADDRESS)
        val descBoolDataAddrAddr = FunctionDescriptor.of(JAVA_BYTE, ADDRESS, ADDRESS, ADDRESS)
        val descAddrDataAddr     = FunctionDescriptor.of(ADDRESS, ADDRESS, ADDRESS)
        val descVoidDataAddrInt  = FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, JAVA_INT)
        val descVoidDataAddrAddr = FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS)
        val descVoidDataIntByte  = FunctionDescriptor.ofVoid(ADDRESS, JAVA_INT, JAVA_BYTE)
        val descIntDataAddrAddr  = FunctionDescriptor.of(JAVA_INT, ADDRESS, ADDRESS, ADDRESS)
        val descLongDataAddrAddr = FunctionDescriptor.of(JAVA_LONG, ADDRESS, ADDRESS, ADDRESS)
        val descVoidCall = FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS, JAVA_LONG, ADDRESS, ADDRESS)

        fun off(field: String) = GodotStructs.offsetOf(GodotStructs.scriptInstanceInfo3, field)

        struct.set(ADDRESS, off("set_func"),
            stub("siSet", boolDataAddrAddr, descBoolDataAddrAddr))
        struct.set(ADDRESS, off("get_func"),
            stub("siGet", boolDataAddrAddr, descBoolDataAddrAddr))
        struct.set(ADDRESS, off("get_property_list_func"),
            stub("siGetPropertyList", addrDataAddr, descAddrDataAddr))
        struct.set(ADDRESS, off("free_property_list_func"),
            stub("siFreePropertyList", voidDataAddrInt, descVoidDataAddrInt))
        // get_class_category_func → NULL (optional)
        struct.set(ADDRESS, off("property_can_revert_func"),
            stub("siPropertyCanRevert", boolDataAddr, descBoolDataAddr))
        struct.set(ADDRESS, off("property_get_revert_func"),
            stub("siPropertyGetRevert", boolDataAddrAddr, descBoolDataAddrAddr))
        struct.set(ADDRESS, off("get_owner_func"),
            stub("siGetOwner", addrData, descAddrData))
        struct.set(ADDRESS, off("get_property_state_func"),
            stub("siGetPropertyState", voidDataAddrAddr, descVoidDataAddrAddr))
        struct.set(ADDRESS, off("get_method_list_func"),
            stub("siGetMethodList", addrDataAddr, descAddrDataAddr))
        struct.set(ADDRESS, off("free_method_list_func"),
            stub("siFreeMethodList", voidDataAddrInt, descVoidDataAddrInt))
        struct.set(ADDRESS, off("get_property_type_func"),
            stub("siGetPropertyType", intDataAddrAddr, descIntDataAddrAddr))
        struct.set(ADDRESS, off("validate_property_func"),
            stub("siValidateProperty", boolDataAddr, descBoolDataAddr))
        struct.set(ADDRESS, off("has_method_func"),
            stub("siHasMethod", boolDataAddr, descBoolDataAddr))
        struct.set(ADDRESS, off("get_method_argument_count_func"),
            stub("siGetMethodArgCount", longDataAddrAddr, descLongDataAddrAddr))
        struct.set(ADDRESS, off("call_func"),
            stub("siCall", voidDataAddrAddrLongAddrAddr, descVoidCall))
        struct.set(ADDRESS, off("notification_func"),
            stub("siNotification", voidDataIntByte, descVoidDataIntByte))
        struct.set(ADDRESS, off("to_string_func"),
            stub("siToString", voidDataAddrAddr, descVoidDataAddrAddr))
        struct.set(ADDRESS, off("refcount_incremented_func"),
            stub("siRefcountIncremented", voidData, descVoidData))
        // Contract (script_instance.h): "return true if it can die". Kanama's script
        // instance holds no reference on its owner, so the last unreference() must be
        // allowed to destroy it — returning false here made every scripted RefCounted
        // (custom Resources included) immortal: `die = die && script_ret` in
        // RefCounted::unreference() can never be true. (C# returns false only while
        // its managed GC handle owns deletion responsibility — Kanama has no such path.)
        struct.set(ADDRESS, off("refcount_decremented_func"),
            stub("siReturnTrue1", boolData, descBoolData))
        struct.set(ADDRESS, off("get_script_func"),
            stub("siGetScript", addrData, descAddrData))
        struct.set(ADDRESS, off("is_placeholder_func"),
            stub("siIsPlaceholder", boolData, descBoolData))
        struct.set(ADDRESS, off("set_fallback_func"),
            stub("siReturnFalse3", boolDataAddrAddr, descBoolDataAddrAddr))
        struct.set(ADDRESS, off("get_fallback_func"),
            stub("siReturnFalse3", boolDataAddrAddr, descBoolDataAddrAddr))
        struct.set(ADDRESS, off("get_language_func"),
            stub("siGetLanguage", addrData, descAddrData))
        struct.set(ADDRESS, off("free_func"),
            stub("siFree", voidData, descVoidData))

        return struct
    }

    // ---- Property-info layout constants (for siGetPropertyState / siGetPropertyType) ----

    private val propInfoSize = GodotStructs.propertyInfo.byteSize()
    private val propNameOff = GodotStructs.offsetOf(GodotStructs.propertyInfo, "name")
    private val propTypeOff = GodotStructs.offsetOf(GodotStructs.propertyInfo, "type")
    private val propUsageOff = GodotStructs.offsetOf(GodotStructs.propertyInfo, "usage")

    /** Descriptor for the addFunc callback passed to get_property_state_func. */
    private val addFuncDesc = FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS)

    /** Godot Variant is always 24 bytes on 64-bit (arm64 + x86_64). */
    private const val VARIANT_SIZE = 24L
    private const val PROPERTY_USAGE_GROUP = 64
    private const val PROPERTY_USAGE_CATEGORY = 128
    private const val PROPERTY_USAGE_SUBGROUP = 256
    private val physicsProcessNameValue by lazy { GodotStrings.stringNameStorage("_physics_process") }
    private val processNameValue by lazy { GodotStrings.stringNameStorage("_process") }
    private val processDeltaScratch = ThreadLocal.withInitial {
        Arena.ofAuto().allocate(JAVA_DOUBLE)
    }
    private val nodeSetPhysicsProcessBind by lazy {
        ObjectCalls.getMethodBind("Node", "set_physics_process", 2586408642L)
    }
    private val nodeSetProcessBind by lazy {
        ObjectCalls.getMethodBind("Node", "set_process", 2586408642L)
    }
    private val objectGetScriptBind by lazy {
        ObjectCalls.getMethodBind("Object", "get_script", 1214101251L)
    }

    private fun ownerObjectHasKanamaScript(ownerObject: MemorySegment): Boolean {
        if (pendingKanamaScriptOwnerAddresses.contains(ownerObject.address())) return true
        if (objectGetScriptBind.address() == 0L) return false
        val scriptObject = ObjectCalls.ptrcallNoArgsRetVariantObject(objectGetScriptBind, ownerObject)
        if (scriptObject.address() == 0L) return false
        return KanamaScript.byObjectAddress(scriptObject.address()) != null
    }

    // ---- Upcall implementations ----

    private fun si(data: MemorySegment): KanamaScriptInstance? =
        ObjectRegistry.get(data.address()) as? KanamaScriptInstance

    // --- Property set/get ---

    @JvmStatic
    fun siSet(data: MemorySegment, name: MemorySegment, value: MemorySegment): Byte {
        val nameLong = name.reinterpret(8).get(JAVA_LONG, 0)
        val si = si(data) ?: return 0
        if (si.dispatchSet(nameLong, value)) return 1
        val values = si.placeholderPropertyValues ?: return 0
        if (!si.hasProperty(nameLong)) return 0
        values.put(nameLong, BuiltinTypes.copyVariant(value))?.close()
        return 1
    }

    @JvmStatic
    fun siGet(data: MemorySegment, name: MemorySegment, ret: MemorySegment): Byte {
        val nameLong = name.reinterpret(8).get(JAVA_LONG, 0)
        val si = si(data) ?: return 0
        if (si.dispatchGet(nameLong, ret)) return 1
        val values = si.placeholderPropertyValues ?: return 0
        val storedValue = values[nameLong]
        if (storedValue != null) {
            storedValue.writeCopyTo(ret)
            return 1
        }
        return if (si.script?.writePropertyDefault?.invoke(nameLong, ret) == true) 1 else 0
    }

    // --- Property list ---

    @JvmStatic
    fun siGetPropertyList(data: MemorySegment, rCount: MemorySegment): MemorySegment {
        val si = si(data)
        rCount.reinterpret(4).set(JAVA_INT, 0, si?.propertyCount ?: 0)
        return si?.propertyListPtr ?: MemorySegment.NULL
    }

    @JvmStatic
    fun siFreePropertyList(data: MemorySegment, list: MemorySegment, count: Int) {
        // Static array — nothing to free.
    }

    @JvmStatic
    fun siFreeMethodList(data: MemorySegment, list: MemorySegment, count: Int) {
        // No method list allocated — nothing to free.
    }

    // --- Owner / script / language ---

    @JvmStatic
    fun siGetOwner(data: MemorySegment): MemorySegment =
        si(data)?.ownerObject ?: MemorySegment.NULL

    @JvmStatic
    fun siGetScript(data: MemorySegment): MemorySegment =
        si(data)?.script?.godotObject ?: MemorySegment.NULL

    @JvmStatic
    fun siGetLanguage(data: MemorySegment): MemorySegment =
        KanamaScriptLanguage.godotObject

    @JvmStatic
    fun siIsPlaceholder(data: MemorySegment): Byte =
        if (si(data)?.placeholderPropertyValues != null) 1 else 0

    // --- Method list (empty for now) ---

    @JvmStatic
    fun siGetMethodList(data: MemorySegment, rCount: MemorySegment): MemorySegment {
        rCount.reinterpret(4).set(JAVA_INT, 0, 0)
        return MemorySegment.NULL
    }

    // --- Has method ---

    @JvmStatic
    fun siHasMethod(data: MemorySegment, name: MemorySegment): Byte {
        val nameLong = name.reinterpret(8).get(JAVA_LONG, 0)
        return if (si(data)?.dispatchHasMethod?.invoke(nameLong) == true) 1 else 0
    }

    // --- Method call ---

    @JvmStatic
    fun siCall(
        data: MemorySegment,
        method: MemorySegment,
        args: MemorySegment,
        argCount: Long,
        rRet: MemorySegment,
        rError: MemorySegment,
    ) {
        val methodLong = method.reinterpret(8).get(JAVA_LONG, 0)
        val instance = si(data)
        val handled = try {
            instance?.dispatchDirectProcess(methodLong, args, argCount) == true ||
                instance?.dispatchCall?.invoke(methodLong, args, argCount, rRet, rError) == true
        } catch (t: Throwable) {
            System.err.println(
                "[kanama:kt] script method failed ${formatScriptCallContext(instance, data, methodLong, argCount)}: " +
                    "${t::class.qualifiedName}: ${t.message}",
            )
            t.printStackTrace(System.err)
            if (rError.address() != 0L) {
                // GDEXTENSION_CALL_ERROR_INVALID_METHOD = 1. GDExtension has no
                // script-exception error type, but returning a call error keeps
                // the exception from escaping the Panama upcall boundary.
                rError.reinterpret(12).set(JAVA_INT, 0, 1)
            }
            return
        }
        if (rError.address() != 0L) {
            if (handled) {
                // GDEXTENSION_CALL_OK = 0
                rError.reinterpret(12).set(JAVA_INT, 0, 0)
            } else {
                // GDEXTENSION_CALL_ERROR_INVALID_METHOD = 1
                rError.reinterpret(12).set(JAVA_INT, 0, 1)
            }
        }
    }

    private fun formatScriptCallContext(
        instance: KanamaScriptInstance?,
        data: MemorySegment,
        methodLong: Long,
        argCount: Long,
    ): String {
        val script = instance?.script
        val scriptName = script?.globalName?.takeIf { it.isNotEmpty() }
            ?: script?.kotlinClassName?.takeIf { it.isNotEmpty() }
            ?: instance?.kotlinObject?.javaClass?.name
            ?: "<unknown>"
        val ownerAddress = instance?.ownerObject?.address() ?: 0L
        return "script=$scriptName method=0x${methodLong.toString(16)} args=$argCount " +
            "instance=0x${data.address().toString(16)} owner=0x${ownerAddress.toString(16)}"
    }

    private fun KanamaScriptInstance.dispatchDirectProcess(
        methodLong: Long,
        args: MemorySegment,
        argCount: Long,
    ): Boolean {
        if (argCount < 1L) return false
        val dispatch = when (methodLong) {
            processNameValue -> dispatchProcess
            physicsProcessNameValue -> dispatchPhysicsProcess
            else -> null
        } ?: return false

        val argsArray = args.reinterpret(8L)
        val deltaScratch = processDeltaScratch.get()
        VariantConverters.variantToType(VariantType.FLOAT).invoke(
            deltaScratch,
            argsArray.get(ADDRESS, 0L),
        )
        dispatch(deltaScratch.get(JAVA_DOUBLE, 0L))
        return true
    }

    // --- Property type / validation ---

    @JvmStatic
    fun siGetPropertyType(data: MemorySegment, name: MemorySegment, rIsValid: MemorySegment): Int {
        val si = si(data)
        if (si != null && si.propertyCount > 0 && si.propertyListPtr != MemorySegment.NULL) {
            val nameLong = name.reinterpret(8L).get(JAVA_LONG, 0L)
            val list = si.propertyListPtr.reinterpret(propInfoSize * si.propertyCount)
            repeat(si.propertyCount) { i ->
                val entryNamePtr = list.get(ADDRESS, i.toLong() * propInfoSize + propNameOff)
                if (entryNamePtr.reinterpret(8L).get(JAVA_LONG, 0L) == nameLong) {
                    val type = list.get(JAVA_INT, i.toLong() * propInfoSize + propTypeOff)
                    if (rIsValid.address() != 0L) rIsValid.reinterpret(1).set(JAVA_BYTE, 0, 1)
                    return type
                }
            }
        }
        if (rIsValid.address() != 0L) rIsValid.reinterpret(1).set(JAVA_BYTE, 0, 0)
        return 0 // NIL — property not found
    }

    @JvmStatic
    fun siValidateProperty(data: MemorySegment, propInfo: MemorySegment): Byte = 1 // true

    // --- Property revert ---

    @JvmStatic
    fun siPropertyCanRevert(data: MemorySegment, name: MemorySegment): Byte {
        val si = si(data) ?: return 0
        val script = si.script ?: return 0
        val nameLong = name.reinterpret(8).get(JAVA_LONG, 0)
        return if (script.hasPropertyDefault(nameLong)) 1 else 0
    }

    @JvmStatic
    fun siPropertyGetRevert(data: MemorySegment, name: MemorySegment, ret: MemorySegment): Byte {
        val si = si(data) ?: return 0
        val script = si.script ?: return 0
        val nameLong = name.reinterpret(8).get(JAVA_LONG, 0)
        return if (script.writePropertyDefault(nameLong, ret)) 1 else 0
    }

    @JvmStatic
    fun siGetPropertyState(data: MemorySegment, addFunc: MemorySegment, userData: MemorySegment) {
        val si = si(data) ?: return
        if (si.propertyCount == 0 || si.propertyListPtr == MemorySegment.NULL) return
        if (addFunc.address() == 0L) return

        val callAdd = GodotFFI.linker.downcallHandle(addFunc, addFuncDesc)
        val list = si.propertyListPtr.reinterpret(propInfoSize * si.propertyCount)

        Arena.ofConfined().use { a ->
            repeat(si.propertyCount) { i ->
                val namePtr = list.get(ADDRESS, i.toLong() * propInfoSize + propNameOff)
                val nameLong = namePtr.reinterpret(8L).get(JAVA_LONG, 0L)
                val variantBuf = a.allocate(VARIANT_SIZE, 8L)
                if (si.dispatchGet(nameLong, variantBuf)) {
                    try {
                        callAdd.invoke(namePtr, variantBuf, userData)
                    } finally {
                        BuiltinTypes.destroyVariant(variantBuf)
                    }
                }
            }
        }
    }

    private fun KanamaScriptInstance.hasProperty(nameLong: Long): Boolean {
        if (propertyCount == 0 || propertyListPtr == MemorySegment.NULL) return false
        val list = propertyListPtr.reinterpret(propInfoSize * propertyCount)
        repeat(propertyCount) { i ->
            val usage = list.get(JAVA_INT, i.toLong() * propInfoSize + propUsageOff)
            if (usage and (PROPERTY_USAGE_GROUP or PROPERTY_USAGE_CATEGORY or PROPERTY_USAGE_SUBGROUP) != 0) {
                return@repeat
            }
            val namePtr = list.get(ADDRESS, i.toLong() * propInfoSize + propNameOff)
            if (namePtr.reinterpret(8L).get(JAVA_LONG, 0L) == nameLong) return true
        }
        return false
    }

    fun applyPendingScriptPropertyValues(ownerObject: MemorySegment, scriptInstance: KanamaScriptInstance) {
        val values = pendingScriptPropertyValuesByOwnerAddress.remove(ownerObject.address()) ?: return
        if (values.isEmpty()) return

        values.forEach { (property, value) ->
            applyScriptPropertyValue(
                scriptInstance = scriptInstance,
                property = property,
                value = if (value === nullScriptPropertyValue) null else value,
            )
        }
    }

    private fun applyScriptPropertyValue(scriptInstance: KanamaScriptInstance, property: String, value: Any?) {
        val nameLong = GodotStrings.stringNameStorage(property)

        Arena.ofConfined().use { arena ->
            val variant = arena.allocate(VARIANT_SIZE, 8L)
            BuiltinTypes.initVariantFromAny(variant, value, arena)
            try {
                val applied = scriptInstance.dispatchSet(nameLong, variant)
                if (System.getenv("KANAMA_TRACE_SCRIPT_PROPERTY_SET") == "1") {
                    System.err.println(
                        "[kanama:kt] script-property set ${scriptInstance.kotlinObject::class.qualifiedName}.$property " +
                            "applied=$applied value=${value?.let { it::class.qualifiedName } ?: "null"}"
                    )
                }
            } finally {
                BuiltinTypes.destroyVariant(variant)
            }
        }
    }

    // --- To string ---

    @JvmStatic
    fun siToString(data: MemorySegment, rIsValid: MemorySegment, rOut: MemorySegment) {
        if (rIsValid.address() != 0L) rIsValid.reinterpret(1).set(JAVA_BYTE, 0, 0)
    }

    // --- Refcounting ---

    @JvmStatic
    fun siRefcountIncremented(data: MemorySegment) {}

    // --- Notification ---

    @JvmStatic
    fun siNotification(_data: MemorySegment, _what: Int, _reversed: Byte) {
        // Processing is enabled once when the script instance is created. Re-enabling it from
        // ENTER_TREE or READY notifications would override a script's setProcess(false) or
        // setPhysicsProcess(false), including authority-gated multiplayer input scripts.
    }

    // --- Method arg count ---

    @JvmStatic
    fun siGetMethodArgCount(data: MemorySegment, name: MemorySegment, rIsValid: MemorySegment): Long {
        if (rIsValid.address() != 0L) rIsValid.reinterpret(1).set(JAVA_BYTE, 0, 0)
        return 0L
    }

    fun configureLifecycleProcessing(si: KanamaScriptInstance) {
        if (si.dispatchPhysicsProcess != null || si.dispatchHasMethod(physicsProcessNameValue)) {
            ObjectCalls.ptrcallWithBoolArg(nodeSetPhysicsProcessBind, si.ownerObject, true)
        }
        if (si.dispatchProcess != null || si.dispatchHasMethod(processNameValue)) {
            ObjectCalls.ptrcallWithBoolArg(nodeSetProcessBind, si.ownerObject, true)
        }
    }

    // --- Shared stubs for "return false" with different arities ---

    @JvmStatic fun siReturnFalse1(data: MemorySegment): Byte = 0

    @JvmStatic fun siReturnTrue1(data: MemorySegment): Byte = 1

    @JvmStatic fun siReturnFalse2(data: MemorySegment, a: MemorySegment): Byte = 0

    @JvmStatic fun siReturnFalse3(data: MemorySegment, a: MemorySegment, b: MemorySegment): Byte = 0

    // --- Free ---

    @JvmStatic
    fun siFree(data: MemorySegment) {
        val handle = data.address()
        val scriptInstance = si(data)
        (scriptInstance?.kotlinObject as? KanamaCoroutineOwner)?.let { owner ->
            runCatching { owner.kanamaScope.cancel() }
                .onFailure { error -> System.err.println("[kanama:kt] failed to cancel KanamaScope during siFree: ${error.message}") }
        }
        scriptInstance?.let { si ->
            si.placeholderPropertyValues?.values?.forEach { value ->
                runCatching { value.close() }
                    .onFailure { error -> System.err.println("[kanama:kt] failed to free placeholder property Variant during siFree: ${error.message}") }
            }
            si.placeholderPropertyValues?.clear()
            runCatching { si.cleanup() }
                .onFailure { error -> System.err.println("[kanama:kt] failed to clean Kanama script instance during siFree: ${error.message}") }
        }
        val scriptObject = scriptInstance?.script?.godotObject ?: MemorySegment.NULL
        if (scriptObject.address() != 0L) {
            val unreferenceBind = ObjectCalls.getMethodBind("RefCounted", "unreference", REFCOUNTED_UNREFERENCE_HASH)
            if (unreferenceBind.address() != 0L) {
                ObjectCalls.ptrcallNoArgsRetBool(unreferenceBind, scriptObject)
            }
        }
        if (scriptInstance != null) {
            scriptInstance.script?.untrackOwnerObject(scriptInstance.ownerObject.address())
            kotlinObjectByOwnerAddress.remove(scriptInstance.ownerObject.address())
            scriptInstanceByOwnerAddress.remove(scriptInstance.ownerObject.address())
            pendingKanamaScriptOwnerAddresses.remove(scriptInstance.ownerObject.address())
            pendingScriptPropertyValuesByOwnerAddress.remove(scriptInstance.ownerObject.address())
        }
        ObjectRegistry.unregister(handle)
        if (System.getenv("KANAMA_TRACE_INSTANCES") == "1") {
            System.err.println("[kanama:kt] ScriptBridge.siFree handle=$handle")
        }
    }

    fun retainScriptResource(scriptObject: MemorySegment) {
        if (scriptObject.address() == 0L) return
        val referenceBind = ObjectCalls.getMethodBind("RefCounted", "reference", REFCOUNTED_REFERENCE_HASH)
        if (referenceBind.address() != 0L) {
            ObjectCalls.ptrcallNoArgsRetBool(referenceBind, scriptObject)
        }
    }
}

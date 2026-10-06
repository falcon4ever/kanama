package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.FreedObjectChecks
import net.multigesture.kanama.binding.runtime.LiveFlag
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.ObjectRuntime
import net.multigesture.kanama.types.NodePath

/**
 * Non-owning wrapper around a Godot Object pointer.
 *
 * This wrapper does not free or retain the object. Use it for objects whose
 * lifetime is owned by Godot, such as the object passed into a script instance.
 *
 * The object must be alive when the wrapper is constructed: construction reads its
 * instance id once (see [instanceId]). After the object is freed, GDScript's rules apply in
 * debug builds (the editor and debug export templates; task 131 item 2):
 * - holding the wrapper is fine: [instanceId], [equals], [hashCode], [isSameInstance] and
 *   `GD.isInstanceValid` never touch the object, [toString] answers `<Freed Object>`, and
 *   handing the wrapper back to Godot as a value (a script property, a script method's return,
 *   a Variant argument, an Array element) passes nil;
 * - calling a method through it throws `IllegalStateException("Invalid access to previously
 *   freed instance ...")`, which Kanama contains and reports as a script error.
 * A release build does not check: there a freed wrapper is a dangling pointer, so test
 * `GD.isInstanceValid` before using an object that may have been freed.
 *
 * Two wrappers are [equals] when they view the same Godot object (the same instance id),
 * as GDScript's `==` compares objects; `List.contains`, `Set` and `Map` keys follow.
 *
 * Written once for every backend (task 117 P3′, D20): this file is compiled by the desktop/Android
 * and iOS targets alike. Every member is a ptrcall through `ObjectCalls` except the
 * platform-bound hooks — instance-id capture, the freed-object check, `emitSignal`, and the
 * script-property buffering in [set]/[call]/[setScript] — which go through the internal
 * `ObjectRuntime` seam
 * (`src/commonMain/.../binding/runtime/ObjectRuntime.expect.kt`). [signal] returns the platform's
 * own `GodotSignal`.
 */
open class GodotObject(val handle: GodotHandle) {
    // ===== BEGIN GENERATED ENUMS: GodotObject (scripts/generate_api_wrapper.py — do not edit) =====
    @JvmInline
    value class ConnectFlags(override val value: Long) : GodotEnumValue {
        infix fun or(other: ConnectFlags): ConnectFlags = ConnectFlags(value or other.value)

        infix fun and(other: ConnectFlags): ConnectFlags = ConnectFlags(value and other.value)

        infix fun xor(other: ConnectFlags): ConnectFlags = ConnectFlags(value xor other.value)

        fun inv(): ConnectFlags = ConnectFlags(value.inv())

        operator fun contains(other: ConnectFlags): Boolean = (value and other.value) == other.value

        companion object {
            val DEFERRED: ConnectFlags get() = ConnectFlags(1L)
            val PERSIST: ConnectFlags get() = ConnectFlags(2L)
            val ONE_SHOT: ConnectFlags get() = ConnectFlags(4L)
            val REFERENCE_COUNTED: ConnectFlags get() = ConnectFlags(8L)
            val APPEND_SOURCE_OBJECT: ConnectFlags get() = ConnectFlags(16L)
        }
    }
    // ===== END GENERATED ENUMS: GodotObject =====

    /**
     * The raw engine pointer behind [handle] — the runtime/ObjectCalls seam, read by every wrapper
     * call (receiver and typed object arguments). Internal: game code passes [handle] around and
     * never unwraps it. While `FreedObjectChecks.enabled` it first checks that the object is still
     * alive and throws `IllegalStateException` if it was freed (task 131 item 2): one read of the
     * object's [LiveFlag] when the instance-binding check is on (task 132 D7), else an
     * `object_get_instance_from_id` engine call.
     * Value encodings (Variant, property, return) use `FreedObjectChecks.valueSegment` instead,
     * which turns a freed object into nil without an error.
     */
    internal val segment: RawSegment
        get() {
            val raw = handle.segment
            if (FreedObjectChecks.enabled && !isAlive(raw)) {
                throw FreedObjectChecks.freedInstance(this::class.simpleName ?: "GodotObject", instanceId)
            }
            return raw
        }

    /**
     * Returns true when both wrappers refer to the same Godot object instance: the same
     * [instanceId], exactly what [equals] compares.
     */
    fun isSameInstance(other: GodotObject): Boolean = instanceId == other.instanceId

    init {
        require(handle.segment.address() != 0L) { "GodotObject handle must not be NULL" }
    }

    /**
     * The engine instance id, captured once at construction (`object_get_instance_id`, a
     * direct interface downcall). Unlike [getInstanceId] this never dereferences [segment]
     * again, so it stays valid to read after the object has been freed; `GD.isInstanceValid`
     * routes through it (task 98).
     *
     * The JVM getter is renamed because the ptrcall [getInstanceId] already owns the
     * `getInstanceId()J` signature; Kotlin callers read `instanceId` as usual.
     */
    @get:JvmName("capturedInstanceId")
    val instanceId: Long

    // The object's liveness flag (task 132 D7), shared by every wrapper of it; null when the
    // instance-binding check is off, and liveness is asked of the engine by instance id instead.
    private val liveFlag: LiveFlag? = ObjectRuntime.liveFlagOf(handle.segment)

    init {
        instanceId = liveFlag?.instanceId ?: ObjectRuntime.instanceIdOf(handle.segment)
    }

    /** Whether the object behind [raw] (this wrapper's handle) is still alive. Never dereferences it. */
    internal fun isAlive(raw: RawSegment): Boolean {
        val flag = liveFlag
        return if (flag != null) !flag.dead else ObjectRuntime.isLive(raw, instanceId)
    }

    /**
     * True when [other] is a wrapper of the same Godot object: the same [instanceId] (task 131
     * item 6), whatever the wrapper class (`Node` and `Node3D` views of one node are equal). Never
     * dereferences the object, so it stays safe after the object was freed. Final, so every
     * wrapper class keeps this one notion of identity.
     */
    final override fun equals(other: Any?): Boolean =
        this === other || (other is GodotObject && instanceId == other.instanceId)

    /** Hashes [instanceId], consistently with [equals]. */
    final override fun hashCode(): Int = instanceId.hashCode()

    /**
     * Argument-position handle check. A non-owning wrapper has nothing to refuse; [RefCounted]
     * overrides it with its closed-handle check (task 98). Internal: it hands out the raw engine
     * pointer, which is never part of a wrapper signature (task 104).
     */
    internal open fun requireOpenHandle(): RawSegment = segment

    fun getClassName(): String =
        ObjectCalls.ptrcallNoArgsRetString(Binds.getClassBind, segment)

    fun isClass(className: String): Boolean =
        ObjectCalls.ptrcallWithStringArgRetBool(Binds.isClassBind, segment, className)

    fun getInstanceId(): Long =
        ObjectCalls.ptrcallNoArgsRetLong(Binds.getInstanceIdBind, segment)

    fun isQueuedForDeletion(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(Binds.isQueuedForDeletionBind, segment)

    fun setIndexed(propertyPath: NodePath, value: Any?) {
        ObjectCalls.ptrcallWithNodePathAndVariantArg(Binds.setIndexedBind, segment, propertyPath, value)
    }

    fun setIndexed(propertyPath: String, value: Any?) {
        setIndexed(NodePath(propertyPath), value)
    }

    fun getIndexed(propertyPath: NodePath): Any? =
        ObjectCalls.ptrcallWithNodePathArgRetVariantScalar(Binds.getIndexedBind, segment, propertyPath)

    fun getIndexed(propertyPath: String): Any? =
        getIndexed(NodePath(propertyPath))

    fun getPropertyList(): List<Map<String, Any?>> =
        ObjectCalls.ptrcallNoArgsRetDictionaryList(Binds.getPropertyListBind, segment)

    fun getMethodList(): List<Map<String, Any?>> =
        ObjectCalls.ptrcallNoArgsRetDictionaryList(Binds.getMethodListBind, segment)

    fun propertyCanRevert(property: String): Boolean =
        ObjectCalls.ptrcallWithStringNameArgRetBool(Binds.propertyCanRevertBind, segment, property)

    fun propertyGetRevert(property: String): Any? =
        ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(Binds.propertyGetRevertBind, segment, property)

    fun notification(what: Int, reversed: Boolean = false) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.notificationBind, segment, what, reversed)
    }

    /** Variant-path read: the script comes back as a borrowed view, never `close()` it — see [call]. */
    fun getScript(): Any? =
        ObjectCalls.ptrcallNoArgsRetVariantScalar(Binds.getScriptBind, segment)

    fun setMeta(name: String, value: Any?) {
        ObjectCalls.ptrcallWithStringNameAndVariantArg(Binds.setMetaBind, segment, name, value)
    }

    /** Variant-path read: an object result is a borrowed view, never `close()` it — see [call]. */
    fun getMeta(name: String, defaultValue: Any? = null): Any? =
        ObjectCalls.ptrcallWithStringNameAndVariantArgRetVariantScalar(Binds.getMetaBind, segment, name, defaultValue)

    fun hasMeta(name: String): Boolean =
        ObjectCalls.ptrcallWithStringNameArgRetBool(Binds.hasMetaBind, segment, name)

    fun removeMeta(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.removeMetaBind, segment, name)
    }

    fun getMetaList(): List<String> =
        ObjectCalls.ptrcallNoArgsRetStringNameList(Binds.getMetaListBind, segment)

    fun addUserSignal(signal: String, arguments: List<Map<String, Any>> = emptyList()) {
        ObjectCalls.ptrcallWithStringAndArrayOfDictionariesArg(Binds.addUserSignalBind, segment, signal, arguments)
    }

    fun hasUserSignal(signal: String): Boolean =
        ObjectCalls.ptrcallWithStringNameArgRetBool(Binds.hasUserSignalBind, segment, signal)

    fun removeUserSignal(signal: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.removeUserSignalBind, segment, signal)
    }

    fun hasMethod(method: String): Boolean =
        ObjectCalls.ptrcallWithStringNameArgRetBool(Binds.hasMethodBind, segment, method)

    fun getMethodArgumentCount(method: String): Long =
        ObjectCalls.ptrcallWithStringNameArgRetInt(Binds.getMethodArgumentCountBind, segment, method).toLong()

    fun hasSignal(signal: String): Boolean =
        ObjectCalls.ptrcallWithStringNameArgRetBool(Binds.hasSignalBind, segment, signal)

    fun getSignalList(): List<Map<String, Any?>> =
        ObjectCalls.ptrcallNoArgsRetDictionaryList(Binds.getSignalListBind, segment)

    fun getSignalConnectionList(signal: String): List<Map<String, Any?>> =
        ObjectCalls.ptrcallWithStringNameArgRetDictionaryList(Binds.getSignalConnectionListBind, segment, signal)

    fun getIncomingConnections(): List<Map<String, Any?>> =
        ObjectCalls.ptrcallNoArgsRetDictionaryList(Binds.getIncomingConnectionsBind, segment)

    fun hasConnections(signal: String): Boolean =
        ObjectCalls.ptrcallWithStringNameArgRetBool(Binds.hasConnectionsBind, segment, signal)

    fun signal(name: String): GodotSignal =
        GodotSignal(this, name)

    /** Signal `script_changed()`; see [TypedSignal]. */
    val scriptChanged: Signal0
        @JvmName("scriptChangedTypedSignal")
        get() = Signal0(this, "script_changed")

    /** Signal `property_list_changed()`; see [TypedSignal]. */
    val propertyListChanged: Signal0
        @JvmName("propertyListChangedTypedSignal")
        get() = Signal0(this, "property_list_changed")

    /**
     * Connects [signal] to [method] on [target]; returns Godot's `Error`. [flags] combine
     * [GodotObject.ConnectFlags] values (`ConnectFlags.DEFERRED or ConnectFlags.ONE_SHOT`); Godot has
     * no named zero, so the default is `ConnectFlags(0L)`.
     */
    fun connect(
        signal: String,
        target: GodotObject,
        method: String,
        flags: GodotObject.ConnectFlags = GodotObject.ConnectFlags(0L),
    ): GodotError =
        GodotError(
            ObjectCalls.ptrcallWithStringNameCallableAndUInt32ArgsRetLong(
                Binds.connectBind,
                segment,
                signal,
                target.segment,
                method,
                flags.value,
            ),
        )

    internal fun connectBound(
        signal: String,
        target: GodotObject,
        method: String,
        boundArgs: List<Any?>,
        flags: GodotObject.ConnectFlags = GodotObject.ConnectFlags(0L),
    ): GodotError =
        GodotError(
            ObjectCalls.ptrcallWithStringNameBoundCallableAndUInt32ArgsRetLong(
                Binds.connectBind,
                segment,
                signal,
                target.segment,
                method,
                boundArgs,
                flags.value,
            ),
        )

    fun disconnect(signal: String, target: GodotObject, method: String) {
        ObjectCalls.ptrcallWithStringNameAndCallableArgs(Binds.disconnectBind, segment, signal, target.segment, method)
    }

    fun isConnected(signal: String, target: GodotObject, method: String): Boolean =
        ObjectCalls.ptrcallWithStringNameAndCallableArgsRetBool(Binds.isConnectedBind, segment, signal, target.segment, method)

    internal fun disconnectBound(signal: String, target: GodotObject, method: String, boundArgs: List<Any?>) {
        ObjectCalls.ptrcallWithStringNameAndBoundCallableArgs(
            Binds.disconnectBind,
            segment,
            signal,
            target.segment,
            method,
            boundArgs,
        )
    }

    fun emitSignal(signal: String, vararg args: Any?) {
        ObjectRuntime.emitSignal(segment, signal, args.toList())
    }

    fun setBlockSignals(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setBlockSignalsBind, segment, enable)
    }

    fun isBlockingSignals(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(Binds.isBlockingSignalsBind, segment)

    fun notifyPropertyListChanged() {
        ObjectCalls.ptrcallNoArgs(Binds.notifyPropertyListChangedBind, segment)
    }

    fun setMessageTranslation(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setMessageTranslationBind, segment, enable)
    }

    fun canTranslateMessages(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(Binds.canTranslateMessagesBind, segment)

    /**
     * Dynamic `Object.call`. Scalars come back as Kotlin values. An **object** result comes back
     * as a *borrowed* `GodotObject` view: the Variant-path decode
     * (`BuiltinTypes.variantToScalar`, `VariantType.OBJECT`) takes no reference for you, so
     *
     * - `close()` on it releases nothing (it took no reference). To keep the object, downcast it:
     *   `Resource.fromObject(...)`/`X.fromObject(...)` takes a reference of its own (task 132), which
     *   you may `close()` (or let the garbage collector release);
     * - if the call *minted* the object and the return Variant held its only reference
     *   (`call("duplicate")`, a static factory), the segment is already dead when you receive it;
     *   use the typed wrapper method instead (an owned `+1` you close), or `ClassDB.instantiate`,
     *   whose owned decode path retains before the Variant is destroyed.
     *
     * The same applies to every Variant-path read on this class: [callDeferred], [callv], [get],
     * [getMeta], [getScript]. Typed wrapper getters (`getMesh()`, `getAnimation(...)`) are the
     * other convention — an owned `+1` the caller closes. Both are spelled out in
     * `docs/game-dev/godot-api.md` "Resource Ownership" and, for the ABI, in
     * `docs/contributing/wrapper-maintenance.md` "RefCounted Return Ownership".
     */
    fun call(method: String, vararg args: Any?): Any? {
        val result = ObjectCalls.callWithVariantArgs(callBind, segment, listOf(method, *args))
        if (method == "set" && args.size == 2) {
            val property = args[0] as? String
            if (property != null) {
                ObjectRuntime.onPropertySet(segment, property, args[1])
            }
        }
        return result
    }

    /** Variant-path call; an object result is a borrowed view, never `close()` it — see [call]. */
    fun callDeferred(method: String, vararg args: Any?): Any? =
        ObjectCalls.callWithVariantArgs(Binds.callDeferredBind, segment, listOf(method, *args))

    /** Variant-path call; an object result is a borrowed view, never `close()` it — see [call]. */
    fun callv(method: String, arguments: List<Any?>): Any? =
        ObjectCalls.ptrcallWithStringNameArrayArgsRetVariantScalar(Binds.callvBind, segment, method, arguments)

    /**
     * Dynamic `Object.get`. A resource read this way (`get("mesh")`) is a *borrowed* view that
     * lives only while this object keeps the property — never `close()` it (see [call]). The
     * typed getter (`getMesh()`) is the owned `+1` you close.
     */
    fun get(property: String): Any? =
        ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(Binds.objectGetBind, segment, property)

    fun set(property: String, value: Any?): Long {
        ObjectCalls.ptrcallWithStringNameAndVariantArg(Binds.objectSetBind, segment, property, value)
        ObjectRuntime.onPropertySet(segment, property, value)
        return 0L
    }

    fun setDeferred(property: String, value: Any?) {
        ObjectCalls.ptrcallWithStringNameAndVariantArg(Binds.setDeferredBind, segment, property, value)
    }

    fun setScript(script: Resource?) {
        ObjectRuntime.onSetScript(segment, script?.segment ?: NULL_SEGMENT)
        ObjectCalls.ptrcallWithVariantArg(Binds.setScriptBind, segment, script)
    }

    fun tr(message: String, context: String = ""): String =
        ObjectCalls.ptrcallWithTwoStringNameArgsRetString(Binds.trBind, segment, message, context)

    fun trN(message: String, pluralMessage: String, n: Int, context: String = ""): String =
        ObjectCalls.ptrcallWithTwoStringNameIntStringNameArgsRetString(Binds.trNBind, segment, message, pluralMessage, n, context)

    fun getTranslationDomain(): String =
        ObjectCalls.ptrcallNoArgsRetStringName(Binds.getTranslationDomainBind, segment)

    fun setTranslationDomain(domain: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.setTranslationDomainBind, segment, domain)
    }

    fun cancelFree() {
        ObjectCalls.ptrcallNoArgs(Binds.cancelFreeBind, segment)
    }

    /**
     * Godot's `Object.to_string()`; `<Freed Object>` (GDScript's `str()` of a freed object) when
     * the freed-object check is on and the object was freed, so string templates never throw.
     */
    override fun toString(): String =
        if (FreedObjectChecks.enabled && !isAlive(handle.segment)) {
            "<Freed Object>"
        } else {
            ObjectCalls.ptrcallNoArgsRetString(Binds.toStringBind, segment)
        }

    object Signals {
        const val scriptChanged: String = "script_changed"
        const val propertyListChanged: String = "property_list_changed"
    }

    companion object {
        const val NOTIFICATION_POSTINITIALIZE = 0L
        const val NOTIFICATION_PREDELETE = 1L
        const val NOTIFICATION_EXTENSION_RELOADED = 2L

        /** A non-owning view of the object behind [handle], or null for a NULL handle. */
        fun fromHandle(handle: GodotHandle): GodotObject? = wrap(handle.segment)

        internal fun wrap(handle: RawSegment): GodotObject? =
            if (handle.address() == 0L) null else GodotObject(GodotHandle(handle))

        private const val NOARGS_STRING_HASH = 201670096L
        private const val NOARGS_BOOL_HASH = 36873697L
        private const val NOARGS_LONG_HASH = 3905245786L
        private const val STRING_BOOL_HASH = 3927539163L
        private const val TO_STRING_HASH = 2841200299L
        private const val STRING_NAME_BOOL_HASH = 2619796661L
        private const val STRING_NAME_VOID_HASH = 3304788590L
        private const val STRING_NAME_LONG_HASH = 2458036349L
        private const val BOOL_VOID_HASH = 2586408642L
        private const val NOARGS_VOID_HASH = 3218959716L
        private const val CALL_HASH = 3400424181L
        private const val SET_INDEXED_HASH = 3500910842L
        private const val GET_INDEXED_HASH = 4006125091L
        private const val OBJECT_SET_HASH = 3776071444L
        private const val OBJECT_GET_HASH = 2760726917L
        private const val DICTIONARY_LIST_HASH = 3995934104L
        private const val PROPERTY_GET_REVERT_HASH = 2760726917L
        private const val NOTIFICATION_HASH = 4023243586L
        private const val GET_SCRIPT_HASH = 1214101251L
        private const val SET_META_HASH = 3776071444L
        private const val GET_META_HASH = 3990617847L
        private const val GET_META_LIST_HASH = 3995934104L
        private const val ADD_USER_SIGNAL_HASH = 85656714L
        private const val GET_SIGNAL_CONNECTION_LIST_HASH = 3147814860L
        private const val IS_CONNECTED_HASH = 768136979L
        private const val CALLV_HASH = 1260104456L
        private const val TR_HASH = 1195764410L
        private const val TR_N_HASH = 162698058L
        private const val GET_TRANSLATION_DOMAIN_HASH = 2002593661L
        private const val SET_DEFERRED_HASH = 3776071444L
        private const val SET_SCRIPT_HASH = 1114965689L
        private const val CONNECT_HASH = 1518946055L
        private const val DISCONNECT_HASH = 1874754934L

        // Internal, not private: the iOS ObjectRuntime.emitSignal Variant path reuses this cached
        // Object.call bind instead of resolving its own (task 117 P3′ follow-up).
        internal val callBind by lazy {
            ObjectCalls.getMethodBind("Object", "call", CALL_HASH)
        }

    }

    // Every MethodBind of the class, bound together on the first call through any of them: the
    // holder's class initialisation is the laziness, and a bind is a static final after it
    // (task 131 item 18; the generated wrappers use the same holder).
    private object Binds {
        @JvmField val getClassBind = ObjectCalls.getMethodBind("Object", "get_class", NOARGS_STRING_HASH)
        @JvmField val isClassBind = ObjectCalls.getMethodBind("Object", "is_class", STRING_BOOL_HASH)
        @JvmField val getInstanceIdBind = ObjectCalls.getMethodBind("Object", "get_instance_id", NOARGS_LONG_HASH)
        @JvmField val isQueuedForDeletionBind = ObjectCalls.getMethodBind("Object", "is_queued_for_deletion", NOARGS_BOOL_HASH)
        @JvmField val setIndexedBind = ObjectCalls.getMethodBind("Object", "set_indexed", SET_INDEXED_HASH)
        @JvmField val getIndexedBind = ObjectCalls.getMethodBind("Object", "get_indexed", GET_INDEXED_HASH)
        @JvmField val objectSetBind = ObjectCalls.getMethodBind("Object", "set", OBJECT_SET_HASH)
        @JvmField val objectGetBind = ObjectCalls.getMethodBind("Object", "get", OBJECT_GET_HASH)
        @JvmField val getPropertyListBind = ObjectCalls.getMethodBind("Object", "get_property_list", DICTIONARY_LIST_HASH)
        @JvmField val getMethodListBind = ObjectCalls.getMethodBind("Object", "get_method_list", DICTIONARY_LIST_HASH)
        @JvmField val toStringBind = ObjectCalls.getMethodBind("Object", "to_string", TO_STRING_HASH)
        @JvmField val propertyCanRevertBind = ObjectCalls.getMethodBind("Object", "property_can_revert", STRING_NAME_BOOL_HASH)
        @JvmField val propertyGetRevertBind = ObjectCalls.getMethodBind("Object", "property_get_revert", PROPERTY_GET_REVERT_HASH)
        @JvmField val notificationBind = ObjectCalls.getMethodBind("Object", "notification", NOTIFICATION_HASH)
        @JvmField val getScriptBind = ObjectCalls.getMethodBind("Object", "get_script", GET_SCRIPT_HASH)
        @JvmField val setMetaBind = ObjectCalls.getMethodBind("Object", "set_meta", SET_META_HASH)
        @JvmField val getMetaBind = ObjectCalls.getMethodBind("Object", "get_meta", GET_META_HASH)
        @JvmField val hasMetaBind = ObjectCalls.getMethodBind("Object", "has_meta", STRING_NAME_BOOL_HASH)
        @JvmField val removeMetaBind = ObjectCalls.getMethodBind("Object", "remove_meta", STRING_NAME_VOID_HASH)
        @JvmField val getMetaListBind = ObjectCalls.getMethodBind("Object", "get_meta_list", GET_META_LIST_HASH)
        @JvmField val addUserSignalBind = ObjectCalls.getMethodBind("Object", "add_user_signal", ADD_USER_SIGNAL_HASH)
        @JvmField val hasUserSignalBind = ObjectCalls.getMethodBind("Object", "has_user_signal", STRING_NAME_BOOL_HASH)
        @JvmField val removeUserSignalBind = ObjectCalls.getMethodBind("Object", "remove_user_signal", STRING_NAME_VOID_HASH)
        @JvmField val hasMethodBind = ObjectCalls.getMethodBind("Object", "has_method", STRING_NAME_BOOL_HASH)
        @JvmField val getMethodArgumentCountBind = ObjectCalls.getMethodBind("Object", "get_method_argument_count", STRING_NAME_LONG_HASH)
        @JvmField val hasSignalBind = ObjectCalls.getMethodBind("Object", "has_signal", STRING_NAME_BOOL_HASH)
        @JvmField val getSignalListBind = ObjectCalls.getMethodBind("Object", "get_signal_list", DICTIONARY_LIST_HASH)
        @JvmField val getSignalConnectionListBind = ObjectCalls.getMethodBind("Object", "get_signal_connection_list", GET_SIGNAL_CONNECTION_LIST_HASH)
        @JvmField val getIncomingConnectionsBind = ObjectCalls.getMethodBind("Object", "get_incoming_connections", DICTIONARY_LIST_HASH)
        @JvmField val hasConnectionsBind = ObjectCalls.getMethodBind("Object", "has_connections", STRING_NAME_BOOL_HASH)
        @JvmField val connectBind = ObjectCalls.getMethodBind("Object", "connect", CONNECT_HASH)
        @JvmField val disconnectBind = ObjectCalls.getMethodBind("Object", "disconnect", DISCONNECT_HASH)
        @JvmField val isConnectedBind = ObjectCalls.getMethodBind("Object", "is_connected", IS_CONNECTED_HASH)
        @JvmField val setBlockSignalsBind = ObjectCalls.getMethodBind("Object", "set_block_signals", BOOL_VOID_HASH)
        @JvmField val isBlockingSignalsBind = ObjectCalls.getMethodBind("Object", "is_blocking_signals", NOARGS_BOOL_HASH)
        @JvmField val notifyPropertyListChangedBind = ObjectCalls.getMethodBind("Object", "notify_property_list_changed", NOARGS_VOID_HASH)
        @JvmField val setMessageTranslationBind = ObjectCalls.getMethodBind("Object", "set_message_translation", BOOL_VOID_HASH)
        @JvmField val canTranslateMessagesBind = ObjectCalls.getMethodBind("Object", "can_translate_messages", NOARGS_BOOL_HASH)
        @JvmField val callDeferredBind = ObjectCalls.getMethodBind("Object", "call_deferred", CALL_HASH)
        @JvmField val callvBind = ObjectCalls.getMethodBind("Object", "callv", CALLV_HASH)
        @JvmField val setDeferredBind = ObjectCalls.getMethodBind("Object", "set_deferred", SET_DEFERRED_HASH)
        @JvmField val setScriptBind = ObjectCalls.getMethodBind("Object", "set_script", SET_SCRIPT_HASH)
        @JvmField val trBind = ObjectCalls.getMethodBind("Object", "tr", TR_HASH)
        @JvmField val trNBind = ObjectCalls.getMethodBind("Object", "tr_n", TR_N_HASH)
        @JvmField val getTranslationDomainBind = ObjectCalls.getMethodBind("Object", "get_translation_domain", GET_TRANSLATION_DOMAIN_HASH)
        @JvmField val setTranslationDomainBind = ObjectCalls.getMethodBind("Object", "set_translation_domain", STRING_NAME_VOID_HASH)
        @JvmField val cancelFreeBind = ObjectCalls.getMethodBind("Object", "cancel_free", NOARGS_VOID_HASH)
    }
}

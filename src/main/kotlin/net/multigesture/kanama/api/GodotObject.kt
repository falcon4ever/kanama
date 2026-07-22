package net.multigesture.kanama.api

import net.multigesture.kanama.binding.ScriptBridge
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.Signals as RuntimeSignals
import net.multigesture.kanama.types.NodePath
import java.lang.foreign.MemorySegment

/**
 * Non-owning wrapper around a Godot Object pointer.
 *
 * This wrapper does not free or retain the object. Use it for objects whose
 * lifetime is owned by Godot, such as the object passed into a script instance.
 */
open class GodotObject(val handle: MemorySegment) {

    /** Returns true when both wrappers refer to the same Godot object instance. */
    fun isSameInstance(other: GodotObject): Boolean = handle.address() == other.handle.address()

    init {
        require(handle.address() != 0L) { "GodotObject handle must not be NULL" }
    }

    fun getClassName(): String =
        ObjectCalls.ptrcallNoArgsRetString(getClassBind, handle)

    fun isClass(className: String): Boolean =
        ObjectCalls.ptrcallWithStringArgRetBool(isClassBind, handle, className)

    fun getInstanceId(): Long =
        ObjectCalls.ptrcallNoArgsRetLong(getInstanceIdBind, handle)

    fun isQueuedForDeletion(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isQueuedForDeletionBind, handle)

    fun setIndexed(propertyPath: NodePath, value: Any?) {
        ObjectCalls.ptrcallWithNodePathAndVariantArg(setIndexedBind, handle, propertyPath, value)
    }

    fun setIndexed(propertyPath: String, value: Any?) {
        setIndexed(NodePath(propertyPath), value)
    }

    fun getIndexed(propertyPath: NodePath): Any? =
        ObjectCalls.ptrcallWithNodePathArgRetVariantScalar(getIndexedBind, handle, propertyPath)

    fun getIndexed(propertyPath: String): Any? =
        getIndexed(NodePath(propertyPath))

    fun getPropertyList(): List<Map<String, Any?>> =
        ObjectCalls.ptrcallNoArgsRetDictionaryList(getPropertyListBind, handle)

    fun getMethodList(): List<Map<String, Any?>> =
        ObjectCalls.ptrcallNoArgsRetDictionaryList(getMethodListBind, handle)

    fun propertyCanRevert(property: String): Boolean =
        ObjectCalls.ptrcallWithStringNameArgRetBool(propertyCanRevertBind, handle, property)

    fun propertyGetRevert(property: String): Any? =
        ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(propertyGetRevertBind, handle, property)

    fun notification(what: Int, reversed: Boolean = false) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(notificationBind, handle, what, reversed)
    }

    fun getScript(): Any? =
        ObjectCalls.ptrcallNoArgsRetVariantScalar(getScriptBind, handle)

    fun setMeta(name: String, value: Any?) {
        ObjectCalls.ptrcallWithStringNameAndVariantArg(setMetaBind, handle, name, value)
    }

    fun getMeta(name: String, defaultValue: Any? = null): Any? =
        ObjectCalls.ptrcallWithStringNameAndVariantArgRetVariantScalar(getMetaBind, handle, name, defaultValue)

    fun hasMeta(name: String): Boolean =
        ObjectCalls.ptrcallWithStringNameArgRetBool(hasMetaBind, handle, name)

    fun removeMeta(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeMetaBind, handle, name)
    }

    fun getMetaList(): List<String> =
        ObjectCalls.ptrcallNoArgsRetStringNameList(getMetaListBind, handle)

    fun addUserSignal(signal: String, arguments: List<Map<String, Any>> = emptyList()) {
        ObjectCalls.ptrcallWithStringAndArrayOfDictionariesArg(addUserSignalBind, handle, signal, arguments)
    }

    fun hasUserSignal(signal: String): Boolean =
        ObjectCalls.ptrcallWithStringNameArgRetBool(hasUserSignalBind, handle, signal)

    fun removeUserSignal(signal: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeUserSignalBind, handle, signal)
    }

    fun hasMethod(method: String): Boolean =
        ObjectCalls.ptrcallWithStringNameArgRetBool(hasMethodBind, handle, method)

    fun getMethodArgumentCount(method: String): Long =
        ObjectCalls.ptrcallWithStringNameArgRetInt(getMethodArgumentCountBind, handle, method).toLong()

    fun hasSignal(signal: String): Boolean =
        ObjectCalls.ptrcallWithStringNameArgRetBool(hasSignalBind, handle, signal)

    fun getSignalList(): List<Map<String, Any?>> =
        ObjectCalls.ptrcallNoArgsRetDictionaryList(getSignalListBind, handle)

    fun getSignalConnectionList(signal: String): List<Map<String, Any?>> =
        ObjectCalls.ptrcallWithStringNameArgRetDictionaryList(getSignalConnectionListBind, handle, signal)

    fun getIncomingConnections(): List<Map<String, Any?>> =
        ObjectCalls.ptrcallNoArgsRetDictionaryList(getIncomingConnectionsBind, handle)

    fun hasConnections(signal: String): Boolean =
        ObjectCalls.ptrcallWithStringNameArgRetBool(hasConnectionsBind, handle, signal)

    fun signal(name: String): GodotSignal =
        GodotSignal(this, name)

    fun connect(signal: String, target: GodotObject, method: String, flags: Long = CONNECT_DEFAULT): Long =
        ObjectCalls.ptrcallWithStringNameCallableAndUInt32ArgsRetLong(
            connectBind,
            handle,
            signal,
            target.handle,
            method,
            flags,
        )

    internal fun connectBound(
        signal: String,
        target: GodotObject,
        method: String,
        boundArgs: List<Any?>,
        flags: Long = CONNECT_DEFAULT,
    ): Long =
        ObjectCalls.ptrcallWithStringNameBoundCallableAndUInt32ArgsRetLong(
            connectBind,
            handle,
            signal,
            target.handle,
            method,
            boundArgs,
            flags,
        )

    fun disconnect(signal: String, target: GodotObject, method: String) {
        ObjectCalls.ptrcallWithStringNameAndCallableArgs(disconnectBind, handle, signal, target.handle, method)
    }

    fun isConnected(signal: String, target: GodotObject, method: String): Boolean =
        ObjectCalls.ptrcallWithStringNameAndCallableArgsRetBool(isConnectedBind, handle, signal, target.handle, method)

    internal fun disconnectBound(signal: String, target: GodotObject, method: String, boundArgs: List<Any?>) {
        ObjectCalls.ptrcallWithStringNameAndBoundCallableArgs(
            disconnectBind,
            handle,
            signal,
            target.handle,
            method,
            boundArgs,
        )
    }

    fun emitSignal(signal: String, vararg args: Any?) {
        RuntimeSignals.emitAny(handle, signal, args.toList())
    }

    fun setBlockSignals(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setBlockSignalsBind, handle, enable)
    }

    fun isBlockingSignals(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isBlockingSignalsBind, handle)

    fun notifyPropertyListChanged() {
        ObjectCalls.ptrcallNoArgs(notifyPropertyListChangedBind, handle)
    }

    fun setMessageTranslation(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMessageTranslationBind, handle, enable)
    }

    fun canTranslateMessages(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(canTranslateMessagesBind, handle)

    fun call(method: String, vararg args: Any?): Any? {
        val result = ObjectCalls.callWithVariantArgs(callBind, handle, listOf(method, *args))
        if (method == "set" && args.size == 2) {
            val property = args[0] as? String
            if (property != null) {
                ScriptBridge.applyOrRecordScriptPropertySet(handle, property, args[1])
            }
        }
        return result
    }

    fun callDeferred(method: String, vararg args: Any?): Any? =
        ObjectCalls.callWithVariantArgs(callDeferredBind, handle, listOf(method, *args))

    fun callv(method: String, arguments: List<Any?>): Any? =
        ObjectCalls.ptrcallWithStringNameArrayArgsRetVariantScalar(callvBind, handle, method, arguments)

    fun get(property: String): Any? =
        ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(objectGetBind, handle, property)

    fun set(property: String, value: Any?): Long {
        ObjectCalls.ptrcallWithStringNameAndVariantArg(objectSetBind, handle, property, value)
        ScriptBridge.applyOrRecordScriptPropertySet(handle, property, value)
        return 0L
    }

    fun setDeferred(property: String, value: Any?) {
        ObjectCalls.ptrcallWithStringNameAndVariantArg(setDeferredBind, handle, property, value)
    }

    fun setScript(script: Resource?) {
        ScriptBridge.noteSetScript(handle, script?.handle ?: MemorySegment.NULL)
        ObjectCalls.ptrcallWithVariantArg(setScriptBind, handle, script)
    }

    fun tr(message: String, context: String = ""): String =
        ObjectCalls.ptrcallWithTwoStringNameArgsRetString(trBind, handle, message, context)

    fun trN(message: String, pluralMessage: String, n: Int, context: String = ""): String =
        ObjectCalls.ptrcallWithTwoStringNameIntStringNameArgsRetString(trNBind, handle, message, pluralMessage, n, context)

    fun getTranslationDomain(): String =
        ObjectCalls.ptrcallNoArgsRetStringName(getTranslationDomainBind, handle)

    fun setTranslationDomain(domain: String) {
        ObjectCalls.ptrcallWithStringNameArg(setTranslationDomainBind, handle, domain)
    }

    fun cancelFree() {
        ObjectCalls.ptrcallNoArgs(cancelFreeBind, handle)
    }

    override fun toString(): String =
        ObjectCalls.ptrcallNoArgsRetString(toStringBind, handle)

    object Signals {
        const val scriptChanged: String = "script_changed"
        const val propertyListChanged: String = "property_list_changed"
    }

    companion object {
        const val NOTIFICATION_POSTINITIALIZE = 0L
        const val NOTIFICATION_PREDELETE = 1L
        const val NOTIFICATION_EXTENSION_RELOADED = 2L
        const val CONNECT_DEFAULT = 0L
        const val CONNECT_DEFERRED = 1L
        const val CONNECT_PERSIST = 2L
        const val CONNECT_ONE_SHOT = 4L
        const val CONNECT_REFERENCE_COUNTED = 8L
        const val CONNECT_APPEND_SOURCE_OBJECT = 16L

        fun wrap(handle: MemorySegment): GodotObject? =
            if (handle.address() == 0L) null else GodotObject(handle)

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

        private val getClassBind by lazy {
            ObjectCalls.getMethodBind("Object", "get_class", NOARGS_STRING_HASH)
        }

        private val isClassBind by lazy {
            ObjectCalls.getMethodBind("Object", "is_class", STRING_BOOL_HASH)
        }

        private val getInstanceIdBind by lazy {
            ObjectCalls.getMethodBind("Object", "get_instance_id", NOARGS_LONG_HASH)
        }

        private val isQueuedForDeletionBind by lazy {
            ObjectCalls.getMethodBind("Object", "is_queued_for_deletion", NOARGS_BOOL_HASH)
        }

        private val setIndexedBind by lazy {
            ObjectCalls.getMethodBind("Object", "set_indexed", SET_INDEXED_HASH)
        }

        private val getIndexedBind by lazy {
            ObjectCalls.getMethodBind("Object", "get_indexed", GET_INDEXED_HASH)
        }

        private val objectSetBind by lazy {
            ObjectCalls.getMethodBind("Object", "set", OBJECT_SET_HASH)
        }

        private val objectGetBind by lazy {
            ObjectCalls.getMethodBind("Object", "get", OBJECT_GET_HASH)
        }

        private val getPropertyListBind by lazy {
            ObjectCalls.getMethodBind("Object", "get_property_list", DICTIONARY_LIST_HASH)
        }

        private val getMethodListBind by lazy {
            ObjectCalls.getMethodBind("Object", "get_method_list", DICTIONARY_LIST_HASH)
        }

        private val toStringBind by lazy {
            ObjectCalls.getMethodBind("Object", "to_string", TO_STRING_HASH)
        }

        private val propertyCanRevertBind by lazy {
            ObjectCalls.getMethodBind("Object", "property_can_revert", STRING_NAME_BOOL_HASH)
        }

        private val propertyGetRevertBind by lazy {
            ObjectCalls.getMethodBind("Object", "property_get_revert", PROPERTY_GET_REVERT_HASH)
        }

        private val notificationBind by lazy {
            ObjectCalls.getMethodBind("Object", "notification", NOTIFICATION_HASH)
        }

        private val getScriptBind by lazy {
            ObjectCalls.getMethodBind("Object", "get_script", GET_SCRIPT_HASH)
        }

        private val setMetaBind by lazy {
            ObjectCalls.getMethodBind("Object", "set_meta", SET_META_HASH)
        }

        private val getMetaBind by lazy {
            ObjectCalls.getMethodBind("Object", "get_meta", GET_META_HASH)
        }

        private val hasMetaBind by lazy {
            ObjectCalls.getMethodBind("Object", "has_meta", STRING_NAME_BOOL_HASH)
        }

        private val removeMetaBind by lazy {
            ObjectCalls.getMethodBind("Object", "remove_meta", STRING_NAME_VOID_HASH)
        }

        private val getMetaListBind by lazy {
            ObjectCalls.getMethodBind("Object", "get_meta_list", GET_META_LIST_HASH)
        }

        private val addUserSignalBind by lazy {
            ObjectCalls.getMethodBind("Object", "add_user_signal", ADD_USER_SIGNAL_HASH)
        }

        private val hasUserSignalBind by lazy {
            ObjectCalls.getMethodBind("Object", "has_user_signal", STRING_NAME_BOOL_HASH)
        }

        private val removeUserSignalBind by lazy {
            ObjectCalls.getMethodBind("Object", "remove_user_signal", STRING_NAME_VOID_HASH)
        }

        private val hasMethodBind by lazy {
            ObjectCalls.getMethodBind("Object", "has_method", STRING_NAME_BOOL_HASH)
        }

        private val getMethodArgumentCountBind by lazy {
            ObjectCalls.getMethodBind("Object", "get_method_argument_count", STRING_NAME_LONG_HASH)
        }

        private val hasSignalBind by lazy {
            ObjectCalls.getMethodBind("Object", "has_signal", STRING_NAME_BOOL_HASH)
        }

        private val getSignalListBind by lazy {
            ObjectCalls.getMethodBind("Object", "get_signal_list", DICTIONARY_LIST_HASH)
        }

        private val getSignalConnectionListBind by lazy {
            ObjectCalls.getMethodBind("Object", "get_signal_connection_list", GET_SIGNAL_CONNECTION_LIST_HASH)
        }

        private val getIncomingConnectionsBind by lazy {
            ObjectCalls.getMethodBind("Object", "get_incoming_connections", DICTIONARY_LIST_HASH)
        }

        private val hasConnectionsBind by lazy {
            ObjectCalls.getMethodBind("Object", "has_connections", STRING_NAME_BOOL_HASH)
        }

        private val connectBind by lazy {
            ObjectCalls.getMethodBind("Object", "connect", CONNECT_HASH)
        }

        private val disconnectBind by lazy {
            ObjectCalls.getMethodBind("Object", "disconnect", DISCONNECT_HASH)
        }

        private val isConnectedBind by lazy {
            ObjectCalls.getMethodBind("Object", "is_connected", IS_CONNECTED_HASH)
        }

        private val setBlockSignalsBind by lazy {
            ObjectCalls.getMethodBind("Object", "set_block_signals", BOOL_VOID_HASH)
        }

        private val isBlockingSignalsBind by lazy {
            ObjectCalls.getMethodBind("Object", "is_blocking_signals", NOARGS_BOOL_HASH)
        }

        private val notifyPropertyListChangedBind by lazy {
            ObjectCalls.getMethodBind("Object", "notify_property_list_changed", NOARGS_VOID_HASH)
        }

        private val setMessageTranslationBind by lazy {
            ObjectCalls.getMethodBind("Object", "set_message_translation", BOOL_VOID_HASH)
        }

        private val canTranslateMessagesBind by lazy {
            ObjectCalls.getMethodBind("Object", "can_translate_messages", NOARGS_BOOL_HASH)
        }

        private val callBind by lazy {
            ObjectCalls.getMethodBind("Object", "call", CALL_HASH)
        }

        private val callDeferredBind by lazy {
            ObjectCalls.getMethodBind("Object", "call_deferred", CALL_HASH)
        }

        private val callvBind by lazy {
            ObjectCalls.getMethodBind("Object", "callv", CALLV_HASH)
        }

        private val setDeferredBind by lazy {
            ObjectCalls.getMethodBind("Object", "set_deferred", SET_DEFERRED_HASH)
        }

        private val setScriptBind by lazy {
            ObjectCalls.getMethodBind("Object", "set_script", SET_SCRIPT_HASH)
        }

        private val trBind by lazy {
            ObjectCalls.getMethodBind("Object", "tr", TR_HASH)
        }

        private val trNBind by lazy {
            ObjectCalls.getMethodBind("Object", "tr_n", TR_N_HASH)
        }

        private val getTranslationDomainBind by lazy {
            ObjectCalls.getMethodBind("Object", "get_translation_domain", GET_TRANSLATION_DOMAIN_HASH)
        }

        private val setTranslationDomainBind by lazy {
            ObjectCalls.getMethodBind("Object", "set_translation_domain", STRING_NAME_VOID_HASH)
        }

        private val cancelFreeBind by lazy {
            ObjectCalls.getMethodBind("Object", "cancel_free", NOARGS_VOID_HASH)
        }
    }
}

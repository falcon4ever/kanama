package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: GDScriptLanguageProtocol
 */
object GDScriptLanguageProtocol {
    private inline val singleton: RawSegment
        get() = Binds.singleton

    @JvmStatic
    fun getTextDocument(): GDScriptTextDocument? {
        return GDScriptTextDocument.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getTextDocumentBind, singleton))
    }

    @JvmStatic
    fun getWorkspace(): GDScriptWorkspace? {
        return GDScriptWorkspace.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getWorkspaceBind, singleton))
    }

    @JvmStatic
    fun isSmartResolveEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isSmartResolveEnabledBind, singleton)
    }

    @JvmStatic
    fun isInitialized(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isInitializedBind, singleton)
    }

    @JvmStatic
    fun initialize(params: Map<String, Any?>): Any? {
        return ObjectCalls.ptrcallWithDictionaryArgRetVariantScalar(Binds.initializeBind, singleton, params)
    }

    @JvmStatic
    fun initialized(params: Any?) {
        ObjectCalls.ptrcallWithVariantArg(Binds.initializedBind, singleton, params)
    }

    @JvmStatic
    fun onClientConnected(): GodotError {
        return GodotError(ObjectCalls.ptrcallNoArgsRetLong(Binds.onClientConnectedBind, singleton))
    }

    @JvmStatic
    fun onClientDisconnected(clientId: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.onClientDisconnectedBind, singleton, clientId)
    }

    @JvmStatic
    fun notifyClient(method: String, params: Any? = null, clientId: Int = -1) {
        ObjectCalls.ptrcallWithStringVariantAndIntArg(Binds.notifyClientBind, singleton, method, params, clientId)
    }

    @JvmStatic
    fun fromHandle(handle: GodotHandle): GDScriptLanguageProtocol? =
        wrap(handle.segment)

    internal fun wrap(handle: RawSegment): GDScriptLanguageProtocol? =
        if (handle.address() == 0L) null else this

    private object Binds {
        @JvmField
        val singleton = ObjectCalls.getSingleton("GDScriptLanguageProtocol")

        private const val GET_TEXT_DOCUMENT_HASH = 770545799L
        @JvmField
        val getTextDocumentBind =
            ObjectCalls.getMethodBind("GDScriptLanguageProtocol", "get_text_document", GET_TEXT_DOCUMENT_HASH)

        private const val GET_WORKSPACE_HASH = 969295246L
        @JvmField
        val getWorkspaceBind =
            ObjectCalls.getMethodBind("GDScriptLanguageProtocol", "get_workspace", GET_WORKSPACE_HASH)

        private const val IS_SMART_RESOLVE_ENABLED_HASH = 36873697L
        @JvmField
        val isSmartResolveEnabledBind =
            ObjectCalls.getMethodBind("GDScriptLanguageProtocol", "is_smart_resolve_enabled", IS_SMART_RESOLVE_ENABLED_HASH)

        private const val IS_INITIALIZED_HASH = 36873697L
        @JvmField
        val isInitializedBind =
            ObjectCalls.getMethodBind("GDScriptLanguageProtocol", "is_initialized", IS_INITIALIZED_HASH)

        private const val INITIALIZE_HASH = 3762224011L
        @JvmField
        val initializeBind =
            ObjectCalls.getMethodBind("GDScriptLanguageProtocol", "initialize", INITIALIZE_HASH)

        private const val INITIALIZED_HASH = 1114965689L
        @JvmField
        val initializedBind =
            ObjectCalls.getMethodBind("GDScriptLanguageProtocol", "initialized", INITIALIZED_HASH)

        private const val ON_CLIENT_CONNECTED_HASH = 166280745L
        @JvmField
        val onClientConnectedBind =
            ObjectCalls.getMethodBind("GDScriptLanguageProtocol", "on_client_connected", ON_CLIENT_CONNECTED_HASH)

        private const val ON_CLIENT_DISCONNECTED_HASH = 1286410249L
        @JvmField
        val onClientDisconnectedBind =
            ObjectCalls.getMethodBind("GDScriptLanguageProtocol", "on_client_disconnected", ON_CLIENT_DISCONNECTED_HASH)

        private const val NOTIFY_CLIENT_HASH = 2511212011L
        @JvmField
        val notifyClientBind =
            ObjectCalls.getMethodBind("GDScriptLanguageProtocol", "notify_client", NOTIFY_CLIENT_HASH)
    }
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: GDScriptLanguageProtocol
 */
object GDScriptLanguageProtocol {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("GDScriptLanguageProtocol")
    }

    @JvmStatic
    fun getTextDocument(): GDScriptTextDocument? {
        return GDScriptTextDocument.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextDocumentBind, singleton))
    }

    @JvmStatic
    fun getWorkspace(): GDScriptWorkspace? {
        return GDScriptWorkspace.wrap(ObjectCalls.ptrcallNoArgsRetObject(getWorkspaceBind, singleton))
    }

    @JvmStatic
    fun isSmartResolveEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSmartResolveEnabledBind, singleton)
    }

    @JvmStatic
    fun isInitialized(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isInitializedBind, singleton)
    }

    @JvmStatic
    fun onClientConnected(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(onClientConnectedBind, singleton)
    }

    @JvmStatic
    fun onClientDisconnected(clientId: Int) {
        ObjectCalls.ptrcallWithIntArg(onClientDisconnectedBind, singleton, clientId)
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): GDScriptLanguageProtocol? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): GDScriptLanguageProtocol? =
        if (handle.address() == 0L) null else this

    private const val GET_TEXT_DOCUMENT_HASH = 770545799L
    private val getTextDocumentBind by lazy {
        ObjectCalls.getMethodBind("GDScriptLanguageProtocol", "get_text_document", GET_TEXT_DOCUMENT_HASH)
    }

    private const val GET_WORKSPACE_HASH = 969295246L
    private val getWorkspaceBind by lazy {
        ObjectCalls.getMethodBind("GDScriptLanguageProtocol", "get_workspace", GET_WORKSPACE_HASH)
    }

    private const val IS_SMART_RESOLVE_ENABLED_HASH = 36873697L
    private val isSmartResolveEnabledBind by lazy {
        ObjectCalls.getMethodBind("GDScriptLanguageProtocol", "is_smart_resolve_enabled", IS_SMART_RESOLVE_ENABLED_HASH)
    }

    private const val IS_INITIALIZED_HASH = 36873697L
    private val isInitializedBind by lazy {
        ObjectCalls.getMethodBind("GDScriptLanguageProtocol", "is_initialized", IS_INITIALIZED_HASH)
    }

    private const val ON_CLIENT_CONNECTED_HASH = 166280745L
    private val onClientConnectedBind by lazy {
        ObjectCalls.getMethodBind("GDScriptLanguageProtocol", "on_client_connected", ON_CLIENT_CONNECTED_HASH)
    }

    private const val ON_CLIENT_DISCONNECTED_HASH = 1286410249L
    private val onClientDisconnectedBind by lazy {
        ObjectCalls.getMethodBind("GDScriptLanguageProtocol", "on_client_disconnected", ON_CLIENT_DISCONNECTED_HASH)
    }
}

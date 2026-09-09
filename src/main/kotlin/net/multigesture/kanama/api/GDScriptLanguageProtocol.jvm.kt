package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for GDScriptLanguageProtocol (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GDScriptLanguageProtocol waits on: ptrcallWithDictionaryArgRetVariantScalar,
//   ptrcallWithStringVariantAndIntArg, ptrcallWithVariantArg
// Index: docs/contributing/ios-shape-gap.md

fun GDScriptLanguageProtocol.initialize(params: Map<String, Any?>): Any? {
    return ObjectCalls.ptrcallWithDictionaryArgRetVariantScalar(initializeBind, gDScriptLanguageProtocolSingleton, params)
}

fun GDScriptLanguageProtocol.initialized(params: Any?) {
    ObjectCalls.ptrcallWithVariantArg(initializedBind, gDScriptLanguageProtocolSingleton, params)
}

fun GDScriptLanguageProtocol.notifyClient(method: String, params: Any? = null, clientId: Int = -1) {
    ObjectCalls.ptrcallWithStringVariantAndIntArg(notifyClientBind, gDScriptLanguageProtocolSingleton, method, params, clientId)
}

private val gDScriptLanguageProtocolSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("GDScriptLanguageProtocol")
}

private const val INITIALIZE_HASH = 3762224011L
private val initializeBind by lazy {
    ObjectCalls.getMethodBind("GDScriptLanguageProtocol", "initialize", INITIALIZE_HASH)
}

private const val INITIALIZED_HASH = 1114965689L
private val initializedBind by lazy {
    ObjectCalls.getMethodBind("GDScriptLanguageProtocol", "initialized", INITIALIZED_HASH)
}

private const val NOTIFY_CLIENT_HASH = 2511212011L
private val notifyClientBind by lazy {
    ObjectCalls.getMethodBind("GDScriptLanguageProtocol", "notify_client", NOTIFY_CLIENT_HASH)
}

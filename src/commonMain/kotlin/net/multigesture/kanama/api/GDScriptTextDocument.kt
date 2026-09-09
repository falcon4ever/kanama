package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: GDScriptTextDocument
 */
class GDScriptTextDocument(handle: MemorySegment) : RefCounted(handle) {
    fun showNativeSymbolInEditor(symbolId: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(showNativeSymbolInEditorBind, handle, symbolId)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): GDScriptTextDocument? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GDScriptTextDocument? =
            if (handle.address() == 0L) null else GDScriptTextDocument(handle)

        private const val SHOW_NATIVE_SYMBOL_IN_EDITOR_HASH = 83702148L
        private val showNativeSymbolInEditorBind by lazy {
            ObjectCalls.getMethodBind("GDScriptTextDocument", "show_native_symbol_in_editor", SHOW_NATIVE_SYMBOL_IN_EDITOR_HASH)
        }
    }
}

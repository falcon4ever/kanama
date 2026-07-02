package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: GDScriptTextDocument
 */
class GDScriptTextDocument(handle: MemorySegment) : RefCounted(handle) {
    fun showNativeSymbolInEditor(symbolId: String) {
        ObjectCalls.ptrcallWithStringArg(showNativeSymbolInEditorBind, handle, symbolId)
    }

    fun didOpen(params: Any?) {
        ObjectCalls.ptrcallWithVariantArg(didOpenBind, handle, params)
    }

    fun didClose(params: Any?) {
        ObjectCalls.ptrcallWithVariantArg(didCloseBind, handle, params)
    }

    fun didChange(params: Any?) {
        ObjectCalls.ptrcallWithVariantArg(didChangeBind, handle, params)
    }

    fun willSaveWaitUntil(params: Any?) {
        ObjectCalls.ptrcallWithVariantArg(willSaveWaitUntilBind, handle, params)
    }

    fun didSave(params: Any?) {
        ObjectCalls.ptrcallWithVariantArg(didSaveBind, handle, params)
    }

    fun nativeSymbol(params: Map<String, Any?>): Any? {
        return ObjectCalls.ptrcallWithDictionaryArgRetVariantScalar(nativeSymbolBind, handle, params)
    }

    fun documentSymbol(params: Map<String, Any?>): List<Any?> {
        return ObjectCalls.ptrcallWithDictionaryArgRetArray(documentSymbolBind, handle, params)
    }

    fun completion(params: Map<String, Any?>): List<Any?> {
        return ObjectCalls.ptrcallWithDictionaryArgRetArray(completionBind, handle, params)
    }

    fun prepareRename(params: Map<String, Any?>): Any? {
        return ObjectCalls.ptrcallWithDictionaryArgRetVariantScalar(prepareRenameBind, handle, params)
    }

    fun references(params: Map<String, Any?>): List<Any?> {
        return ObjectCalls.ptrcallWithDictionaryArgRetArray(referencesBind, handle, params)
    }

    fun foldingRange(params: Map<String, Any?>): List<Any?> {
        return ObjectCalls.ptrcallWithDictionaryArgRetArray(foldingRangeBind, handle, params)
    }

    fun codeLens(params: Map<String, Any?>): List<Any?> {
        return ObjectCalls.ptrcallWithDictionaryArgRetArray(codeLensBind, handle, params)
    }

    fun documentLink(params: Map<String, Any?>): List<Any?> {
        return ObjectCalls.ptrcallWithDictionaryArgRetArray(documentLinkBind, handle, params)
    }

    fun colorPresentation(params: Map<String, Any?>): List<Any?> {
        return ObjectCalls.ptrcallWithDictionaryArgRetArray(colorPresentationBind, handle, params)
    }

    fun hover(params: Map<String, Any?>): Any? {
        return ObjectCalls.ptrcallWithDictionaryArgRetVariantScalar(hoverBind, handle, params)
    }

    fun definition(params: Map<String, Any?>): List<Any?> {
        return ObjectCalls.ptrcallWithDictionaryArgRetArray(definitionBind, handle, params)
    }

    fun declaration(params: Map<String, Any?>): Any? {
        return ObjectCalls.ptrcallWithDictionaryArgRetVariantScalar(declarationBind, handle, params)
    }

    fun signatureHelp(params: Map<String, Any?>): Any? {
        return ObjectCalls.ptrcallWithDictionaryArgRetVariantScalar(signatureHelpBind, handle, params)
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

        private const val DIDOPEN_HASH = 1114965689L
        private val didOpenBind by lazy {
            ObjectCalls.getMethodBind("GDScriptTextDocument", "didOpen", DIDOPEN_HASH)
        }

        private const val DIDCLOSE_HASH = 1114965689L
        private val didCloseBind by lazy {
            ObjectCalls.getMethodBind("GDScriptTextDocument", "didClose", DIDCLOSE_HASH)
        }

        private const val DIDCHANGE_HASH = 1114965689L
        private val didChangeBind by lazy {
            ObjectCalls.getMethodBind("GDScriptTextDocument", "didChange", DIDCHANGE_HASH)
        }

        private const val WILLSAVEWAITUNTIL_HASH = 1114965689L
        private val willSaveWaitUntilBind by lazy {
            ObjectCalls.getMethodBind("GDScriptTextDocument", "willSaveWaitUntil", WILLSAVEWAITUNTIL_HASH)
        }

        private const val DIDSAVE_HASH = 1114965689L
        private val didSaveBind by lazy {
            ObjectCalls.getMethodBind("GDScriptTextDocument", "didSave", DIDSAVE_HASH)
        }

        private const val NATIVESYMBOL_HASH = 3762224011L
        private val nativeSymbolBind by lazy {
            ObjectCalls.getMethodBind("GDScriptTextDocument", "nativeSymbol", NATIVESYMBOL_HASH)
        }

        private const val DOCUMENTSYMBOL_HASH = 3877611628L
        private val documentSymbolBind by lazy {
            ObjectCalls.getMethodBind("GDScriptTextDocument", "documentSymbol", DOCUMENTSYMBOL_HASH)
        }

        private const val COMPLETION_HASH = 3877611628L
        private val completionBind by lazy {
            ObjectCalls.getMethodBind("GDScriptTextDocument", "completion", COMPLETION_HASH)
        }

        private const val PREPARERENAME_HASH = 3762224011L
        private val prepareRenameBind by lazy {
            ObjectCalls.getMethodBind("GDScriptTextDocument", "prepareRename", PREPARERENAME_HASH)
        }

        private const val REFERENCES_HASH = 3877611628L
        private val referencesBind by lazy {
            ObjectCalls.getMethodBind("GDScriptTextDocument", "references", REFERENCES_HASH)
        }

        private const val FOLDINGRANGE_HASH = 3877611628L
        private val foldingRangeBind by lazy {
            ObjectCalls.getMethodBind("GDScriptTextDocument", "foldingRange", FOLDINGRANGE_HASH)
        }

        private const val CODELENS_HASH = 3877611628L
        private val codeLensBind by lazy {
            ObjectCalls.getMethodBind("GDScriptTextDocument", "codeLens", CODELENS_HASH)
        }

        private const val DOCUMENTLINK_HASH = 3877611628L
        private val documentLinkBind by lazy {
            ObjectCalls.getMethodBind("GDScriptTextDocument", "documentLink", DOCUMENTLINK_HASH)
        }

        private const val COLORPRESENTATION_HASH = 3877611628L
        private val colorPresentationBind by lazy {
            ObjectCalls.getMethodBind("GDScriptTextDocument", "colorPresentation", COLORPRESENTATION_HASH)
        }

        private const val HOVER_HASH = 3762224011L
        private val hoverBind by lazy {
            ObjectCalls.getMethodBind("GDScriptTextDocument", "hover", HOVER_HASH)
        }

        private const val DEFINITION_HASH = 3877611628L
        private val definitionBind by lazy {
            ObjectCalls.getMethodBind("GDScriptTextDocument", "definition", DEFINITION_HASH)
        }

        private const val DECLARATION_HASH = 3762224011L
        private val declarationBind by lazy {
            ObjectCalls.getMethodBind("GDScriptTextDocument", "declaration", DECLARATION_HASH)
        }

        private const val SIGNATUREHELP_HASH = 3762224011L
        private val signatureHelpBind by lazy {
            ObjectCalls.getMethodBind("GDScriptTextDocument", "signatureHelp", SIGNATUREHELP_HASH)
        }
    }
}

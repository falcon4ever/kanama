package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: GDScriptTextDocument
 */
class GDScriptTextDocument(handle: GodotHandle) : RefCounted(handle) {
    fun showNativeSymbolInEditor(symbolId: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.showNativeSymbolInEditorBind, segment, symbolId)
    }

    fun didOpen(params: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithVariantArg(Binds.didOpenBind, segment, params)
    }

    fun didClose(params: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithVariantArg(Binds.didCloseBind, segment, params)
    }

    fun didChange(params: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithVariantArg(Binds.didChangeBind, segment, params)
    }

    fun willSaveWaitUntil(params: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithVariantArg(Binds.willSaveWaitUntilBind, segment, params)
    }

    fun didSave(params: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithVariantArg(Binds.didSaveBind, segment, params)
    }

    fun nativeSymbol(params: Map<String, Any?>): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithDictionaryArgRetVariantScalar(Binds.nativeSymbolBind, segment, params)
    }

    fun documentSymbol(params: Map<String, Any?>): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithDictionaryArgRetArray(Binds.documentSymbolBind, segment, params)
    }

    fun completion(params: Map<String, Any?>): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithDictionaryArgRetArray(Binds.completionBind, segment, params)
    }

    fun prepareRename(params: Map<String, Any?>): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithDictionaryArgRetVariantScalar(Binds.prepareRenameBind, segment, params)
    }

    fun references(params: Map<String, Any?>): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithDictionaryArgRetArray(Binds.referencesBind, segment, params)
    }

    fun foldingRange(params: Map<String, Any?>): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithDictionaryArgRetArray(Binds.foldingRangeBind, segment, params)
    }

    fun codeLens(params: Map<String, Any?>): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithDictionaryArgRetArray(Binds.codeLensBind, segment, params)
    }

    fun documentLink(params: Map<String, Any?>): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithDictionaryArgRetArray(Binds.documentLinkBind, segment, params)
    }

    fun colorPresentation(params: Map<String, Any?>): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithDictionaryArgRetArray(Binds.colorPresentationBind, segment, params)
    }

    fun hover(params: Map<String, Any?>): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithDictionaryArgRetVariantScalar(Binds.hoverBind, segment, params)
    }

    fun definition(params: Map<String, Any?>): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithDictionaryArgRetArray(Binds.definitionBind, segment, params)
    }

    fun declaration(params: Map<String, Any?>): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithDictionaryArgRetVariantScalar(Binds.declarationBind, segment, params)
    }

    fun signatureHelp(params: Map<String, Any?>): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithDictionaryArgRetVariantScalar(Binds.signatureHelpBind, segment, params)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GDScriptTextDocument? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GDScriptTextDocument? =
            if (handle.address() == 0L) null else RefCounted.owned(GDScriptTextDocument(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GDScriptTextDocument? =
            if (handle.address() == 0L) null else GDScriptTextDocument(GodotHandle(handle))
    }

    private object Binds {
        private const val SHOW_NATIVE_SYMBOL_IN_EDITOR_HASH = 83702148L
        @JvmField
        val showNativeSymbolInEditorBind =
            ObjectCalls.getMethodBind("GDScriptTextDocument", "show_native_symbol_in_editor", SHOW_NATIVE_SYMBOL_IN_EDITOR_HASH)

        private const val DIDOPEN_HASH = 1114965689L
        @JvmField
        val didOpenBind =
            ObjectCalls.getMethodBind("GDScriptTextDocument", "didOpen", DIDOPEN_HASH)

        private const val DIDCLOSE_HASH = 1114965689L
        @JvmField
        val didCloseBind =
            ObjectCalls.getMethodBind("GDScriptTextDocument", "didClose", DIDCLOSE_HASH)

        private const val DIDCHANGE_HASH = 1114965689L
        @JvmField
        val didChangeBind =
            ObjectCalls.getMethodBind("GDScriptTextDocument", "didChange", DIDCHANGE_HASH)

        private const val WILLSAVEWAITUNTIL_HASH = 1114965689L
        @JvmField
        val willSaveWaitUntilBind =
            ObjectCalls.getMethodBind("GDScriptTextDocument", "willSaveWaitUntil", WILLSAVEWAITUNTIL_HASH)

        private const val DIDSAVE_HASH = 1114965689L
        @JvmField
        val didSaveBind =
            ObjectCalls.getMethodBind("GDScriptTextDocument", "didSave", DIDSAVE_HASH)

        private const val NATIVESYMBOL_HASH = 3762224011L
        @JvmField
        val nativeSymbolBind =
            ObjectCalls.getMethodBind("GDScriptTextDocument", "nativeSymbol", NATIVESYMBOL_HASH)

        private const val DOCUMENTSYMBOL_HASH = 3877611628L
        @JvmField
        val documentSymbolBind =
            ObjectCalls.getMethodBind("GDScriptTextDocument", "documentSymbol", DOCUMENTSYMBOL_HASH)

        private const val COMPLETION_HASH = 3877611628L
        @JvmField
        val completionBind =
            ObjectCalls.getMethodBind("GDScriptTextDocument", "completion", COMPLETION_HASH)

        private const val PREPARERENAME_HASH = 3762224011L
        @JvmField
        val prepareRenameBind =
            ObjectCalls.getMethodBind("GDScriptTextDocument", "prepareRename", PREPARERENAME_HASH)

        private const val REFERENCES_HASH = 3877611628L
        @JvmField
        val referencesBind =
            ObjectCalls.getMethodBind("GDScriptTextDocument", "references", REFERENCES_HASH)

        private const val FOLDINGRANGE_HASH = 3877611628L
        @JvmField
        val foldingRangeBind =
            ObjectCalls.getMethodBind("GDScriptTextDocument", "foldingRange", FOLDINGRANGE_HASH)

        private const val CODELENS_HASH = 3877611628L
        @JvmField
        val codeLensBind =
            ObjectCalls.getMethodBind("GDScriptTextDocument", "codeLens", CODELENS_HASH)

        private const val DOCUMENTLINK_HASH = 3877611628L
        @JvmField
        val documentLinkBind =
            ObjectCalls.getMethodBind("GDScriptTextDocument", "documentLink", DOCUMENTLINK_HASH)

        private const val COLORPRESENTATION_HASH = 3877611628L
        @JvmField
        val colorPresentationBind =
            ObjectCalls.getMethodBind("GDScriptTextDocument", "colorPresentation", COLORPRESENTATION_HASH)

        private const val HOVER_HASH = 3762224011L
        @JvmField
        val hoverBind =
            ObjectCalls.getMethodBind("GDScriptTextDocument", "hover", HOVER_HASH)

        private const val DEFINITION_HASH = 3877611628L
        @JvmField
        val definitionBind =
            ObjectCalls.getMethodBind("GDScriptTextDocument", "definition", DEFINITION_HASH)

        private const val DECLARATION_HASH = 3762224011L
        @JvmField
        val declarationBind =
            ObjectCalls.getMethodBind("GDScriptTextDocument", "declaration", DECLARATION_HASH)

        private const val SIGNATUREHELP_HASH = 3762224011L
        @JvmField
        val signatureHelpBind =
            ObjectCalls.getMethodBind("GDScriptTextDocument", "signatureHelp", SIGNATUREHELP_HASH)
    }
}

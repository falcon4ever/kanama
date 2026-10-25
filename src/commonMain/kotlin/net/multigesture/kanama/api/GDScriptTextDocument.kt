package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Document related language server functionality.
 *
 * Generated from Godot docs: GDScriptTextDocument
 */
class GDScriptTextDocument(handle: GodotHandle) : RefCounted(handle) {
    fun showNativeSymbolInEditor(symbolId: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.showNativeSymbolInEditorBind, segment, symbolId)
        ObjectCalls.keepReachable(this)
    }

    fun didOpen(params: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithVariantArg(Binds.didOpenBind, segment, params)
        ObjectCalls.keepReachable(this)
    }

    fun didClose(params: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithVariantArg(Binds.didCloseBind, segment, params)
        ObjectCalls.keepReachable(this)
    }

    fun didChange(params: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithVariantArg(Binds.didChangeBind, segment, params)
        ObjectCalls.keepReachable(this)
    }

    fun willSaveWaitUntil(params: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithVariantArg(Binds.willSaveWaitUntilBind, segment, params)
        ObjectCalls.keepReachable(this)
    }

    fun didSave(params: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithVariantArg(Binds.didSaveBind, segment, params)
        ObjectCalls.keepReachable(this)
    }

    fun nativeSymbol(params: Map<String, Any?>): Any? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDictionaryArgRetVariantScalar(Binds.nativeSymbolBind, segment, params)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun documentSymbol(params: Map<String, Any?>): List<Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDictionaryArgRetArray(Binds.documentSymbolBind, segment, params)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun completion(params: Map<String, Any?>): List<Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDictionaryArgRetArray(Binds.completionBind, segment, params)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun prepareRename(params: Map<String, Any?>): Any? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDictionaryArgRetVariantScalar(Binds.prepareRenameBind, segment, params)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun references(params: Map<String, Any?>): List<Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDictionaryArgRetArray(Binds.referencesBind, segment, params)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun foldingRange(params: Map<String, Any?>): List<Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDictionaryArgRetArray(Binds.foldingRangeBind, segment, params)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun codeLens(params: Map<String, Any?>): List<Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDictionaryArgRetArray(Binds.codeLensBind, segment, params)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun documentLink(params: Map<String, Any?>): List<Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDictionaryArgRetArray(Binds.documentLinkBind, segment, params)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun colorPresentation(params: Map<String, Any?>): List<Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDictionaryArgRetArray(Binds.colorPresentationBind, segment, params)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun hover(params: Map<String, Any?>): Any? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDictionaryArgRetVariantScalar(Binds.hoverBind, segment, params)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun definition(params: Map<String, Any?>): List<Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDictionaryArgRetArray(Binds.definitionBind, segment, params)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun declaration(params: Map<String, Any?>): Any? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDictionaryArgRetVariantScalar(Binds.declarationBind, segment, params)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun signatureHelp(params: Map<String, Any?>): Any? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDictionaryArgRetVariantScalar(Binds.signatureHelpBind, segment, params)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GDScriptTextDocument? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GDScriptTextDocument? =
            if (handle.address() == 0L) null else RefCounted.owned(GDScriptTextDocument(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GDScriptTextDocument? =
            if (handle.address() == 0L) null else GDScriptTextDocument(GodotHandle(handle))

        /** A new GDScriptTextDocument, GDScript's `GDScriptTextDocument.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): GDScriptTextDocument =
            RefCounted.owned(GDScriptTextDocument(GodotHandle(requireConstructed(ObjectCalls.constructObject("GDScriptTextDocument"), "GDScriptTextDocument"))))
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

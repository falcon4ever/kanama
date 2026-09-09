package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for GDScriptTextDocument (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GDScriptTextDocument waits on: ptrcallWithDictionaryArgRetArray,
//   ptrcallWithDictionaryArgRetVariantScalar, ptrcallWithVariantArg
// Index: docs/contributing/ios-shape-gap.md

fun GDScriptTextDocument.didOpen(params: Any?) {
    checkOpen()
    ObjectCalls.ptrcallWithVariantArg(didOpenBind, handle, params)
}

fun GDScriptTextDocument.didClose(params: Any?) {
    checkOpen()
    ObjectCalls.ptrcallWithVariantArg(didCloseBind, handle, params)
}

fun GDScriptTextDocument.didChange(params: Any?) {
    checkOpen()
    ObjectCalls.ptrcallWithVariantArg(didChangeBind, handle, params)
}

fun GDScriptTextDocument.willSaveWaitUntil(params: Any?) {
    checkOpen()
    ObjectCalls.ptrcallWithVariantArg(willSaveWaitUntilBind, handle, params)
}

fun GDScriptTextDocument.didSave(params: Any?) {
    checkOpen()
    ObjectCalls.ptrcallWithVariantArg(didSaveBind, handle, params)
}

fun GDScriptTextDocument.nativeSymbol(params: Map<String, Any?>): Any? {
    checkOpen()
    return ObjectCalls.ptrcallWithDictionaryArgRetVariantScalar(nativeSymbolBind, handle, params)
}

fun GDScriptTextDocument.documentSymbol(params: Map<String, Any?>): List<Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithDictionaryArgRetArray(documentSymbolBind, handle, params)
}

fun GDScriptTextDocument.completion(params: Map<String, Any?>): List<Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithDictionaryArgRetArray(completionBind, handle, params)
}

fun GDScriptTextDocument.prepareRename(params: Map<String, Any?>): Any? {
    checkOpen()
    return ObjectCalls.ptrcallWithDictionaryArgRetVariantScalar(prepareRenameBind, handle, params)
}

fun GDScriptTextDocument.references(params: Map<String, Any?>): List<Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithDictionaryArgRetArray(referencesBind, handle, params)
}

fun GDScriptTextDocument.foldingRange(params: Map<String, Any?>): List<Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithDictionaryArgRetArray(foldingRangeBind, handle, params)
}

fun GDScriptTextDocument.codeLens(params: Map<String, Any?>): List<Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithDictionaryArgRetArray(codeLensBind, handle, params)
}

fun GDScriptTextDocument.documentLink(params: Map<String, Any?>): List<Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithDictionaryArgRetArray(documentLinkBind, handle, params)
}

fun GDScriptTextDocument.colorPresentation(params: Map<String, Any?>): List<Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithDictionaryArgRetArray(colorPresentationBind, handle, params)
}

fun GDScriptTextDocument.hover(params: Map<String, Any?>): Any? {
    checkOpen()
    return ObjectCalls.ptrcallWithDictionaryArgRetVariantScalar(hoverBind, handle, params)
}

fun GDScriptTextDocument.definition(params: Map<String, Any?>): List<Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithDictionaryArgRetArray(definitionBind, handle, params)
}

fun GDScriptTextDocument.declaration(params: Map<String, Any?>): Any? {
    checkOpen()
    return ObjectCalls.ptrcallWithDictionaryArgRetVariantScalar(declarationBind, handle, params)
}

fun GDScriptTextDocument.signatureHelp(params: Map<String, Any?>): Any? {
    checkOpen()
    return ObjectCalls.ptrcallWithDictionaryArgRetVariantScalar(signatureHelpBind, handle, params)
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

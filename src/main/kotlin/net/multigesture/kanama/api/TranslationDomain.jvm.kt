package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for TranslationDomain (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP TranslationDomain waits on: ptrcallWithStringAndBoolArgRetTypedObjectList,
//   ptrcallWithTwoStringNameArgsRetStringName,
//   ptrcallWithTwoStringNameIntStringNameArgsRetStringName
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns the `Translation` instances that match `locale` (see
 * `TranslationServer.compare_locales`). If `exact` is `true`, only instances whose locale exactly
 * equals `locale` will be returned.
 *
 * Generated from Godot docs: TranslationDomain.find_translations
 */
fun TranslationDomain.findTranslations(locale: String, exact: Boolean): List<Translation> {
    checkOpen()
    return ObjectCalls.ptrcallWithStringAndBoolArgRetTypedObjectList(findTranslationsBind, handle, locale, exact, Translation::fromHandle)
}

/**
 * Returns the current locale's translation for the given message and context.
 *
 * Generated from Godot docs: TranslationDomain.translate
 */
fun TranslationDomain.translate(message: String, context: String = ""): String {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoStringNameArgsRetStringName(translateBind, handle, message, context)
}

/**
 * Returns the current locale's translation for the given message, plural message and context. The
 * number `n` is the number or quantity of the plural object. It will be used to guide the
 * translation system to fetch the correct plural form for the selected language.
 *
 * Generated from Godot docs: TranslationDomain.translate_plural
 */
fun TranslationDomain.translatePlural(message: String, messagePlural: String, n: Int, context: String = ""): String {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoStringNameIntStringNameArgsRetStringName(translatePluralBind, handle, message, messagePlural, n, context)
}

private const val FIND_TRANSLATIONS_HASH = 2109650934L
private val findTranslationsBind by lazy {
    ObjectCalls.getMethodBind("TranslationDomain", "find_translations", FIND_TRANSLATIONS_HASH)
}

private const val TRANSLATE_HASH = 1829228469L
private val translateBind by lazy {
    ObjectCalls.getMethodBind("TranslationDomain", "translate", TRANSLATE_HASH)
}

private const val TRANSLATE_PLURAL_HASH = 229954002L
private val translatePluralBind by lazy {
    ObjectCalls.getMethodBind("TranslationDomain", "translate_plural", TRANSLATE_PLURAL_HASH)
}

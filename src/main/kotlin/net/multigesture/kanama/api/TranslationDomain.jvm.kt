package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for TranslationDomain (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP TranslationDomain waits on: ptrcallWithStringAndBoolArgRetTypedObjectList
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

private const val FIND_TRANSLATIONS_HASH = 2109650934L
private val findTranslationsBind by lazy {
    ObjectCalls.getMethodBind("TranslationDomain", "find_translations", FIND_TRANSLATIONS_HASH)
}

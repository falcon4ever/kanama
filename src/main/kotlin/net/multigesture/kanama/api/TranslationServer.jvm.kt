package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for TranslationServer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP TranslationServer waits on: ptrcallWithStringAndBoolArgRetTypedObjectList
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns the `Translation` instances in the main translation domain that match `locale` (see
 * `compare_locales`). If `exact` is `true`, only instances whose locale exactly equals `locale`
 * will be returned.
 *
 * Generated from Godot docs: TranslationServer.find_translations
 */
fun TranslationServer.findTranslations(locale: String, exact: Boolean): List<Translation> {
    return ObjectCalls.ptrcallWithStringAndBoolArgRetTypedObjectList(findTranslationsBind, translationServerSingleton, locale, exact, Translation::fromHandle)
}

private val translationServerSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("TranslationServer")
}

private const val FIND_TRANSLATIONS_HASH = 2109650934L
private val findTranslationsBind by lazy {
    ObjectCalls.getMethodBind("TranslationServer", "find_translations", FIND_TRANSLATIONS_HASH)
}

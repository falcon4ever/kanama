package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for TranslationServer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP TranslationServer waits on: ptrcallWithStringAndBoolArgRetString,
//   ptrcallWithStringAndBoolArgRetTypedObjectList, ptrcallWithTwoStringArgsRetString,
//   ptrcallWithTwoStringNameArgsRetStringName,
//   ptrcallWithTwoStringNameIntStringNameArgsRetStringName
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns a `locale` string standardized to match known locales (e.g. `en-US` would be matched to
 * `en_US`). If `add_defaults` is `true`, the locale may have a default script or country added.
 *
 * Generated from Godot docs: TranslationServer.standardize_locale
 */
fun TranslationServer.standardizeLocale(locale: String, addDefaults: Boolean = false): String {
    return ObjectCalls.ptrcallWithStringAndBoolArgRetString(standardizeLocaleBind, translationServerSingleton, locale, addDefaults)
}

/**
 * Returns the current locale's translation for the given message and context. Note: This method
 * always uses the main translation domain.
 *
 * Generated from Godot docs: TranslationServer.translate
 */
fun TranslationServer.translate(message: String, context: String = ""): String {
    return ObjectCalls.ptrcallWithTwoStringNameArgsRetStringName(translateBind, translationServerSingleton, message, context)
}

/**
 * Returns the current locale's translation for the given message, plural message and context. The
 * number `n` is the number or quantity of the plural object. It will be used to guide the
 * translation system to fetch the correct plural form for the selected language. Note: This method
 * always uses the main translation domain.
 *
 * Generated from Godot docs: TranslationServer.translate_plural
 */
fun TranslationServer.translatePlural(message: String, pluralMessage: String, n: Int, context: String = ""): String {
    return ObjectCalls.ptrcallWithTwoStringNameIntStringNameArgsRetStringName(translatePluralBind, translationServerSingleton, message, pluralMessage, n, context)
}

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

/**
 * Converts a number from Western Arabic (0..9) to the numeral system used in the given `locale`.
 *
 * Generated from Godot docs: TranslationServer.format_number
 */
fun TranslationServer.formatNumber(number: String, locale: String): String {
    return ObjectCalls.ptrcallWithTwoStringArgsRetString(formatNumberBind, translationServerSingleton, number, locale)
}

/**
 * Converts `number` from the numeral system used in the given `locale` to Western Arabic (0..9).
 *
 * Generated from Godot docs: TranslationServer.parse_number
 */
fun TranslationServer.parseNumber(number: String, locale: String): String {
    return ObjectCalls.ptrcallWithTwoStringArgsRetString(parseNumberBind, translationServerSingleton, number, locale)
}

private val translationServerSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("TranslationServer")
}

private const val STANDARDIZE_LOCALE_HASH = 4216441673L
private val standardizeLocaleBind by lazy {
    ObjectCalls.getMethodBind("TranslationServer", "standardize_locale", STANDARDIZE_LOCALE_HASH)
}

private const val TRANSLATE_HASH = 1829228469L
private val translateBind by lazy {
    ObjectCalls.getMethodBind("TranslationServer", "translate", TRANSLATE_HASH)
}

private const val TRANSLATE_PLURAL_HASH = 229954002L
private val translatePluralBind by lazy {
    ObjectCalls.getMethodBind("TranslationServer", "translate_plural", TRANSLATE_PLURAL_HASH)
}

private const val FIND_TRANSLATIONS_HASH = 2109650934L
private val findTranslationsBind by lazy {
    ObjectCalls.getMethodBind("TranslationServer", "find_translations", FIND_TRANSLATIONS_HASH)
}

private const val FORMAT_NUMBER_HASH = 315676799L
private val formatNumberBind by lazy {
    ObjectCalls.getMethodBind("TranslationServer", "format_number", FORMAT_NUMBER_HASH)
}

private const val PARSE_NUMBER_HASH = 315676799L
private val parseNumberBind by lazy {
    ObjectCalls.getMethodBind("TranslationServer", "parse_number", PARSE_NUMBER_HASH)
}

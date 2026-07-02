package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * The server responsible for language translations.
 *
 * Generated from Godot docs: TranslationServer
 */
object TranslationServer {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("TranslationServer")
    }

    var pseudolocalizationEnabled: Boolean
        @JvmName("pseudolocalizationEnabledProperty")
        get() = isPseudolocalizationEnabled()
        @JvmName("setPseudolocalizationEnabledProperty")
        set(value) = setPseudolocalizationEnabled(value)

    @JvmStatic
    fun setLocale(locale: String) {
        ObjectCalls.ptrcallWithStringArg(setLocaleBind, singleton, locale)
    }

    @JvmStatic
    fun getLocale(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLocaleBind, singleton)
    }

    @JvmStatic
    fun getToolLocale(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getToolLocaleBind, singleton)
    }

    @JvmStatic
    fun compareLocales(localeA: String, localeB: String): Int {
        return ObjectCalls.ptrcallWithTwoStringArgsRetInt(compareLocalesBind, singleton, localeA, localeB)
    }

    @JvmStatic
    fun standardizeLocale(locale: String, addDefaults: Boolean = false): String {
        return ObjectCalls.ptrcallWithStringAndBoolArgRetString(standardizeLocaleBind, singleton, locale, addDefaults)
    }

    @JvmStatic
    fun getAllLanguages(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getAllLanguagesBind, singleton)
    }

    @JvmStatic
    fun getLanguageName(language: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(getLanguageNameBind, singleton, language)
    }

    @JvmStatic
    fun getAllScripts(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getAllScriptsBind, singleton)
    }

    @JvmStatic
    fun getScriptName(script: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(getScriptNameBind, singleton, script)
    }

    @JvmStatic
    fun getAllCountries(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getAllCountriesBind, singleton)
    }

    @JvmStatic
    fun getCountryName(country: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(getCountryNameBind, singleton, country)
    }

    @JvmStatic
    fun getLocaleName(locale: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(getLocaleNameBind, singleton, locale)
    }

    @JvmStatic
    fun getPluralRules(locale: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(getPluralRulesBind, singleton, locale)
    }

    @JvmStatic
    /**
     * Returns the current locale's translation for the given message and context. Note: This method
     * always uses the main translation domain.
     *
     * Generated from Godot docs: TranslationServer.translate
     */
    fun translate(message: String, context: String = ""): String {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetStringName(translateBind, singleton, message, context)
    }

    @JvmStatic
    fun translatePlural(message: String, pluralMessage: String, n: Int, context: String = ""): String {
        return ObjectCalls.ptrcallWithTwoStringNameIntStringNameArgsRetStringName(translatePluralBind, singleton, message, pluralMessage, n, context)
    }

    @JvmStatic
    fun addTranslation(translation: Translation?) {
        ObjectCalls.ptrcallWithObjectArgs(addTranslationBind, singleton, listOf(translation?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    @JvmStatic
    fun removeTranslation(translation: Translation?) {
        ObjectCalls.ptrcallWithObjectArgs(removeTranslationBind, singleton, listOf(translation?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    @JvmStatic
    fun getTranslationObject(locale: String): Translation? {
        return Translation.wrap(ObjectCalls.ptrcallWithStringArgRetObject(getTranslationObjectBind, singleton, locale))
    }

    @JvmStatic
    fun getTranslations(): List<Translation> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getTranslationsBind, singleton, Translation::fromHandle)
    }

    @JvmStatic
    fun findTranslations(locale: String, exact: Boolean): List<Translation> {
        return ObjectCalls.ptrcallWithStringAndBoolArgRetTypedObjectList(findTranslationsBind, singleton, locale, exact, Translation::fromHandle)
    }

    @JvmStatic
    fun hasTranslationForLocale(locale: String, exact: Boolean): Boolean {
        return ObjectCalls.ptrcallWithStringAndBoolArgRetBool(hasTranslationForLocaleBind, singleton, locale, exact)
    }

    @JvmStatic
    fun hasTranslation(translation: Translation?): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(hasTranslationBind, singleton, translation?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    @JvmStatic
    fun hasDomain(domain: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasDomainBind, singleton, domain)
    }

    @JvmStatic
    fun getOrAddDomain(domain: String): TranslationDomain? {
        return TranslationDomain.wrap(ObjectCalls.ptrcallWithStringNameArgRetObject(getOrAddDomainBind, singleton, domain))
    }

    @JvmStatic
    fun removeDomain(domain: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeDomainBind, singleton, domain)
    }

    @JvmStatic
    /**
     * Removes all translations from the main translation domain.
     *
     * Generated from Godot docs: TranslationServer.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, singleton)
    }

    @JvmStatic
    fun getLoadedLocales(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getLoadedLocalesBind, singleton)
    }

    @JvmStatic
    fun formatNumber(number: String, locale: String): String {
        return ObjectCalls.ptrcallWithTwoStringArgsRetString(formatNumberBind, singleton, number, locale)
    }

    @JvmStatic
    fun getPercentSign(locale: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(getPercentSignBind, singleton, locale)
    }

    @JvmStatic
    fun parseNumber(number: String, locale: String): String {
        return ObjectCalls.ptrcallWithTwoStringArgsRetString(parseNumberBind, singleton, number, locale)
    }

    @JvmStatic
    fun isPseudolocalizationEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPseudolocalizationEnabledBind, singleton)
    }

    @JvmStatic
    fun setPseudolocalizationEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPseudolocalizationEnabledBind, singleton, enabled)
    }

    @JvmStatic
    fun reloadPseudolocalization() {
        ObjectCalls.ptrcallNoArgs(reloadPseudolocalizationBind, singleton)
    }

    @JvmStatic
    /**
     * Returns the pseudolocalized string based on the `message` passed in. Note: This method always
     * uses the main translation domain.
     *
     * Generated from Godot docs: TranslationServer.pseudolocalize
     */
    fun pseudolocalize(message: String): String {
        return ObjectCalls.ptrcallWithStringNameArgRetStringName(pseudolocalizeBind, singleton, message)
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): TranslationServer? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): TranslationServer? =
        if (handle.address() == 0L) null else this

    private const val SET_LOCALE_HASH = 83702148L
    private val setLocaleBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "set_locale", SET_LOCALE_HASH)
    }

    private const val GET_LOCALE_HASH = 201670096L
    private val getLocaleBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "get_locale", GET_LOCALE_HASH)
    }

    private const val GET_TOOL_LOCALE_HASH = 2841200299L
    private val getToolLocaleBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "get_tool_locale", GET_TOOL_LOCALE_HASH)
    }

    private const val COMPARE_LOCALES_HASH = 2878152881L
    private val compareLocalesBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "compare_locales", COMPARE_LOCALES_HASH)
    }

    private const val STANDARDIZE_LOCALE_HASH = 4216441673L
    private val standardizeLocaleBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "standardize_locale", STANDARDIZE_LOCALE_HASH)
    }

    private const val GET_ALL_LANGUAGES_HASH = 1139954409L
    private val getAllLanguagesBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "get_all_languages", GET_ALL_LANGUAGES_HASH)
    }

    private const val GET_LANGUAGE_NAME_HASH = 3135753539L
    private val getLanguageNameBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "get_language_name", GET_LANGUAGE_NAME_HASH)
    }

    private const val GET_ALL_SCRIPTS_HASH = 1139954409L
    private val getAllScriptsBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "get_all_scripts", GET_ALL_SCRIPTS_HASH)
    }

    private const val GET_SCRIPT_NAME_HASH = 3135753539L
    private val getScriptNameBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "get_script_name", GET_SCRIPT_NAME_HASH)
    }

    private const val GET_ALL_COUNTRIES_HASH = 1139954409L
    private val getAllCountriesBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "get_all_countries", GET_ALL_COUNTRIES_HASH)
    }

    private const val GET_COUNTRY_NAME_HASH = 3135753539L
    private val getCountryNameBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "get_country_name", GET_COUNTRY_NAME_HASH)
    }

    private const val GET_LOCALE_NAME_HASH = 3135753539L
    private val getLocaleNameBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "get_locale_name", GET_LOCALE_NAME_HASH)
    }

    private const val GET_PLURAL_RULES_HASH = 3135753539L
    private val getPluralRulesBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "get_plural_rules", GET_PLURAL_RULES_HASH)
    }

    private const val TRANSLATE_HASH = 1829228469L
    private val translateBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "translate", TRANSLATE_HASH)
    }

    private const val TRANSLATE_PLURAL_HASH = 229954002L
    private val translatePluralBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "translate_plural", TRANSLATE_PLURAL_HASH)
    }

    private const val ADD_TRANSLATION_HASH = 1466479800L
    private val addTranslationBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "add_translation", ADD_TRANSLATION_HASH)
    }

    private const val REMOVE_TRANSLATION_HASH = 1466479800L
    private val removeTranslationBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "remove_translation", REMOVE_TRANSLATION_HASH)
    }

    private const val GET_TRANSLATION_OBJECT_HASH = 2065240175L
    private val getTranslationObjectBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "get_translation_object", GET_TRANSLATION_OBJECT_HASH)
    }

    private const val GET_TRANSLATIONS_HASH = 3995934104L
    private val getTranslationsBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "get_translations", GET_TRANSLATIONS_HASH)
    }

    private const val FIND_TRANSLATIONS_HASH = 2109650934L
    private val findTranslationsBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "find_translations", FIND_TRANSLATIONS_HASH)
    }

    private const val HAS_TRANSLATION_FOR_LOCALE_HASH = 2034713381L
    private val hasTranslationForLocaleBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "has_translation_for_locale", HAS_TRANSLATION_FOR_LOCALE_HASH)
    }

    private const val HAS_TRANSLATION_HASH = 2696976312L
    private val hasTranslationBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "has_translation", HAS_TRANSLATION_HASH)
    }

    private const val HAS_DOMAIN_HASH = 2619796661L
    private val hasDomainBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "has_domain", HAS_DOMAIN_HASH)
    }

    private const val GET_OR_ADD_DOMAIN_HASH = 397200075L
    private val getOrAddDomainBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "get_or_add_domain", GET_OR_ADD_DOMAIN_HASH)
    }

    private const val REMOVE_DOMAIN_HASH = 3304788590L
    private val removeDomainBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "remove_domain", REMOVE_DOMAIN_HASH)
    }

    private const val CLEAR_HASH = 3218959716L
    private val clearBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "clear", CLEAR_HASH)
    }

    private const val GET_LOADED_LOCALES_HASH = 1139954409L
    private val getLoadedLocalesBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "get_loaded_locales", GET_LOADED_LOCALES_HASH)
    }

    private const val FORMAT_NUMBER_HASH = 315676799L
    private val formatNumberBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "format_number", FORMAT_NUMBER_HASH)
    }

    private const val GET_PERCENT_SIGN_HASH = 3135753539L
    private val getPercentSignBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "get_percent_sign", GET_PERCENT_SIGN_HASH)
    }

    private const val PARSE_NUMBER_HASH = 315676799L
    private val parseNumberBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "parse_number", PARSE_NUMBER_HASH)
    }

    private const val IS_PSEUDOLOCALIZATION_ENABLED_HASH = 36873697L
    private val isPseudolocalizationEnabledBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "is_pseudolocalization_enabled", IS_PSEUDOLOCALIZATION_ENABLED_HASH)
    }

    private const val SET_PSEUDOLOCALIZATION_ENABLED_HASH = 2586408642L
    private val setPseudolocalizationEnabledBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "set_pseudolocalization_enabled", SET_PSEUDOLOCALIZATION_ENABLED_HASH)
    }

    private const val RELOAD_PSEUDOLOCALIZATION_HASH = 3218959716L
    private val reloadPseudolocalizationBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "reload_pseudolocalization", RELOAD_PSEUDOLOCALIZATION_HASH)
    }

    private const val PSEUDOLOCALIZE_HASH = 1965194235L
    private val pseudolocalizeBind by lazy {
        ObjectCalls.getMethodBind("TranslationServer", "pseudolocalize", PSEUDOLOCALIZE_HASH)
    }
}

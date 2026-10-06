package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A self-contained collection of `Translation` resources.
 *
 * Generated from Godot docs: TranslationDomain
 */
class TranslationDomain(handle: GodotHandle) : RefCounted(handle) {
    var enabled: Boolean
        @JvmName("enabledProperty")
        get() = isEnabled()
        @JvmName("setEnabledProperty")
        set(value) = setEnabled(value)

    var pseudolocalizationEnabled: Boolean
        @JvmName("pseudolocalizationEnabledProperty")
        get() = isPseudolocalizationEnabled()
        @JvmName("setPseudolocalizationEnabledProperty")
        set(value) = setPseudolocalizationEnabled(value)

    var pseudolocalizationAccentsEnabled: Boolean
        @JvmName("pseudolocalizationAccentsEnabledProperty")
        get() = isPseudolocalizationAccentsEnabled()
        @JvmName("setPseudolocalizationAccentsEnabledProperty")
        set(value) = setPseudolocalizationAccentsEnabled(value)

    var pseudolocalizationDoubleVowelsEnabled: Boolean
        @JvmName("pseudolocalizationDoubleVowelsEnabledProperty")
        get() = isPseudolocalizationDoubleVowelsEnabled()
        @JvmName("setPseudolocalizationDoubleVowelsEnabledProperty")
        set(value) = setPseudolocalizationDoubleVowelsEnabled(value)

    var pseudolocalizationFakeBidiEnabled: Boolean
        @JvmName("pseudolocalizationFakeBidiEnabledProperty")
        get() = isPseudolocalizationFakeBidiEnabled()
        @JvmName("setPseudolocalizationFakeBidiEnabledProperty")
        set(value) = setPseudolocalizationFakeBidiEnabled(value)

    var pseudolocalizationOverrideEnabled: Boolean
        @JvmName("pseudolocalizationOverrideEnabledProperty")
        get() = isPseudolocalizationOverrideEnabled()
        @JvmName("setPseudolocalizationOverrideEnabledProperty")
        set(value) = setPseudolocalizationOverrideEnabled(value)

    var pseudolocalizationSkipPlaceholdersEnabled: Boolean
        @JvmName("pseudolocalizationSkipPlaceholdersEnabledProperty")
        get() = isPseudolocalizationSkipPlaceholdersEnabled()
        @JvmName("setPseudolocalizationSkipPlaceholdersEnabledProperty")
        set(value) = setPseudolocalizationSkipPlaceholdersEnabled(value)

    var pseudolocalizationExpansionRatio: Double
        @JvmName("pseudolocalizationExpansionRatioProperty")
        get() = getPseudolocalizationExpansionRatio()
        @JvmName("setPseudolocalizationExpansionRatioProperty")
        set(value) = setPseudolocalizationExpansionRatio(value)

    var pseudolocalizationPrefix: String
        @JvmName("pseudolocalizationPrefixProperty")
        get() = getPseudolocalizationPrefix()
        @JvmName("setPseudolocalizationPrefixProperty")
        set(value) = setPseudolocalizationPrefix(value)

    var pseudolocalizationSuffix: String
        @JvmName("pseudolocalizationSuffixProperty")
        get() = getPseudolocalizationSuffix()
        @JvmName("setPseudolocalizationSuffixProperty")
        set(value) = setPseudolocalizationSuffix(value)

    /**
     * Returns the `Translation` instance that best matches `locale`. Returns `null` if there are no
     * matches.
     *
     * Generated from Godot docs: TranslationDomain.get_translation_object
     */
    fun getTranslationObject(locale: String): Translation? {
        checkOpen()
        return Translation.wrapOwned(ObjectCalls.ptrcallWithStringArgRetObject(Binds.getTranslationObjectBind, segment, locale))
    }

    /**
     * Adds a translation.
     *
     * Generated from Godot docs: TranslationDomain.add_translation
     */
    fun addTranslation(translation: Translation?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.addTranslationBind, segment, listOf(translation?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Removes the given translation.
     *
     * Generated from Godot docs: TranslationDomain.remove_translation
     */
    fun removeTranslation(translation: Translation?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeTranslationBind, segment, listOf(translation?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Removes all translations.
     *
     * Generated from Godot docs: TranslationDomain.clear
     */
    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearBind, segment)
    }

    /**
     * Returns all available `Translation` instances as added by `add_translation`.
     *
     * Generated from Godot docs: TranslationDomain.get_translations
     */
    fun getTranslations(): List<Translation> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getTranslationsBind, segment, Translation::wrapBorrowed)
    }

    /**
     * Returns `true` if there are any `Translation` instances that match `locale` (see
     * `TranslationServer.compare_locales`). If `exact` is `true`, only instances whose locale exactly
     * equals `locale` are considered.
     *
     * Generated from Godot docs: TranslationDomain.has_translation_for_locale
     */
    fun hasTranslationForLocale(locale: String, exact: Boolean): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndBoolArgRetBool(Binds.hasTranslationForLocaleBind, segment, locale, exact)
    }

    /**
     * Returns `true` if this translation domain contains the given `translation`.
     *
     * Generated from Godot docs: TranslationDomain.has_translation
     */
    fun hasTranslation(translation: Translation?): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectArgRetBool(Binds.hasTranslationBind, segment, translation?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the `Translation` instances that match `locale` (see
     * `TranslationServer.compare_locales`). If `exact` is `true`, only instances whose locale exactly
     * equals `locale` will be returned.
     *
     * Generated from Godot docs: TranslationDomain.find_translations
     */
    fun findTranslations(locale: String, exact: Boolean): List<Translation> {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndBoolArgRetTypedObjectList(Binds.findTranslationsBind, segment, locale, exact, Translation::wrapBorrowed)
    }

    /**
     * Returns the current locale's translation for the given message and context.
     *
     * Generated from Godot docs: TranslationDomain.translate
     */
    fun translate(message: String, context: String = ""): String {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetStringName(Binds.translateBind, segment, message, context)
    }

    /**
     * Returns the current locale's translation for the given message, plural message and context. The
     * number `n` is the number or quantity of the plural object. It will be used to guide the
     * translation system to fetch the correct plural form for the selected language.
     *
     * Generated from Godot docs: TranslationDomain.translate_plural
     */
    fun translatePlural(message: String, messagePlural: String, n: Int, context: String = ""): String {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoStringNameIntStringNameArgsRetStringName(Binds.translatePluralBind, segment, message, messagePlural, n, context)
    }

    /**
     * Returns the locale override of the domain. Returns an empty string if locale override is
     * disabled.
     *
     * Generated from Godot docs: TranslationDomain.get_locale_override
     */
    fun getLocaleOverride(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getLocaleOverrideBind, segment)
    }

    /**
     * Sets the locale override of the domain. If `locale` is an empty string, locale override is
     * disabled. Otherwise, `locale` will be standardized to match known locales (e.g. `en-US` would be
     * matched to `en_US`). Note: Calling this method does not automatically update texts in the scene
     * tree. Please propagate the `MainLoop.NOTIFICATION_TRANSLATION_CHANGED` signal manually.
     *
     * Generated from Godot docs: TranslationDomain.set_locale_override
     */
    fun setLocaleOverride(locale: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setLocaleOverrideBind, segment, locale)
    }

    /**
     * If `true`, translation is enabled. Otherwise, `translate` and `translate_plural` will return the
     * input message unchanged regardless of the current locale.
     *
     * Generated from Godot docs: TranslationDomain.is_enabled
     */
    fun isEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEnabledBind, segment)
    }

    /**
     * If `true`, translation is enabled. Otherwise, `translate` and `translate_plural` will return the
     * input message unchanged regardless of the current locale.
     *
     * Generated from Godot docs: TranslationDomain.set_enabled
     */
    fun setEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnabledBind, segment, enabled)
    }

    /**
     * If `true`, enables pseudolocalization for the project. This can be used to spot untranslatable
     * strings or layout issues that may occur once the project is localized to languages that have
     * longer strings than the source language. Note: Updating this property does not automatically
     * update texts in the scene tree. Please propagate the `MainLoop.NOTIFICATION_TRANSLATION_CHANGED`
     * notification manually after you have finished modifying pseudolocalization related options.
     *
     * Generated from Godot docs: TranslationDomain.is_pseudolocalization_enabled
     */
    fun isPseudolocalizationEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isPseudolocalizationEnabledBind, segment)
    }

    /**
     * If `true`, enables pseudolocalization for the project. This can be used to spot untranslatable
     * strings or layout issues that may occur once the project is localized to languages that have
     * longer strings than the source language. Note: Updating this property does not automatically
     * update texts in the scene tree. Please propagate the `MainLoop.NOTIFICATION_TRANSLATION_CHANGED`
     * notification manually after you have finished modifying pseudolocalization related options.
     *
     * Generated from Godot docs: TranslationDomain.set_pseudolocalization_enabled
     */
    fun setPseudolocalizationEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setPseudolocalizationEnabledBind, segment, enabled)
    }

    /**
     * Replace all characters with their accented variants during pseudolocalization. Note: Updating
     * this property does not automatically update texts in the scene tree. Please propagate the
     * `MainLoop.NOTIFICATION_TRANSLATION_CHANGED` notification manually after you have finished
     * modifying pseudolocalization related options.
     *
     * Generated from Godot docs: TranslationDomain.is_pseudolocalization_accents_enabled
     */
    fun isPseudolocalizationAccentsEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isPseudolocalizationAccentsEnabledBind, segment)
    }

    /**
     * Replace all characters with their accented variants during pseudolocalization. Note: Updating
     * this property does not automatically update texts in the scene tree. Please propagate the
     * `MainLoop.NOTIFICATION_TRANSLATION_CHANGED` notification manually after you have finished
     * modifying pseudolocalization related options.
     *
     * Generated from Godot docs: TranslationDomain.set_pseudolocalization_accents_enabled
     */
    fun setPseudolocalizationAccentsEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setPseudolocalizationAccentsEnabledBind, segment, enabled)
    }

    /**
     * Double vowels in strings during pseudolocalization to simulate the lengthening of text due to
     * localization. Note: Updating this property does not automatically update texts in the scene
     * tree. Please propagate the `MainLoop.NOTIFICATION_TRANSLATION_CHANGED` notification manually
     * after you have finished modifying pseudolocalization related options.
     *
     * Generated from Godot docs: TranslationDomain.is_pseudolocalization_double_vowels_enabled
     */
    fun isPseudolocalizationDoubleVowelsEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isPseudolocalizationDoubleVowelsEnabledBind, segment)
    }

    /**
     * Double vowels in strings during pseudolocalization to simulate the lengthening of text due to
     * localization. Note: Updating this property does not automatically update texts in the scene
     * tree. Please propagate the `MainLoop.NOTIFICATION_TRANSLATION_CHANGED` notification manually
     * after you have finished modifying pseudolocalization related options.
     *
     * Generated from Godot docs: TranslationDomain.set_pseudolocalization_double_vowels_enabled
     */
    fun setPseudolocalizationDoubleVowelsEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setPseudolocalizationDoubleVowelsEnabledBind, segment, enabled)
    }

    /**
     * If `true`, emulate bidirectional (right-to-left) text when pseudolocalization is enabled. This
     * can be used to spot issues with RTL layout and UI mirroring that will crop up if the project is
     * localized to RTL languages such as Arabic or Hebrew. Note: Updating this property does not
     * automatically update texts in the scene tree. Please propagate the
     * `MainLoop.NOTIFICATION_TRANSLATION_CHANGED` notification manually after you have finished
     * modifying pseudolocalization related options.
     *
     * Generated from Godot docs: TranslationDomain.is_pseudolocalization_fake_bidi_enabled
     */
    fun isPseudolocalizationFakeBidiEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isPseudolocalizationFakeBidiEnabledBind, segment)
    }

    /**
     * If `true`, emulate bidirectional (right-to-left) text when pseudolocalization is enabled. This
     * can be used to spot issues with RTL layout and UI mirroring that will crop up if the project is
     * localized to RTL languages such as Arabic or Hebrew. Note: Updating this property does not
     * automatically update texts in the scene tree. Please propagate the
     * `MainLoop.NOTIFICATION_TRANSLATION_CHANGED` notification manually after you have finished
     * modifying pseudolocalization related options.
     *
     * Generated from Godot docs: TranslationDomain.set_pseudolocalization_fake_bidi_enabled
     */
    fun setPseudolocalizationFakeBidiEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setPseudolocalizationFakeBidiEnabledBind, segment, enabled)
    }

    /**
     * Replace all characters in the string with `*`. Useful for finding non-localizable strings. Note:
     * Updating this property does not automatically update texts in the scene tree. Please propagate
     * the `MainLoop.NOTIFICATION_TRANSLATION_CHANGED` notification manually after you have finished
     * modifying pseudolocalization related options.
     *
     * Generated from Godot docs: TranslationDomain.is_pseudolocalization_override_enabled
     */
    fun isPseudolocalizationOverrideEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isPseudolocalizationOverrideEnabledBind, segment)
    }

    /**
     * Replace all characters in the string with `*`. Useful for finding non-localizable strings. Note:
     * Updating this property does not automatically update texts in the scene tree. Please propagate
     * the `MainLoop.NOTIFICATION_TRANSLATION_CHANGED` notification manually after you have finished
     * modifying pseudolocalization related options.
     *
     * Generated from Godot docs: TranslationDomain.set_pseudolocalization_override_enabled
     */
    fun setPseudolocalizationOverrideEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setPseudolocalizationOverrideEnabledBind, segment, enabled)
    }

    /**
     * Skip placeholders for string formatting like `%s` or `%f` during pseudolocalization. Useful to
     * identify strings which need additional control characters to display correctly. Note: Updating
     * this property does not automatically update texts in the scene tree. Please propagate the
     * `MainLoop.NOTIFICATION_TRANSLATION_CHANGED` notification manually after you have finished
     * modifying pseudolocalization related options.
     *
     * Generated from Godot docs: TranslationDomain.is_pseudolocalization_skip_placeholders_enabled
     */
    fun isPseudolocalizationSkipPlaceholdersEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isPseudolocalizationSkipPlaceholdersEnabledBind, segment)
    }

    /**
     * Skip placeholders for string formatting like `%s` or `%f` during pseudolocalization. Useful to
     * identify strings which need additional control characters to display correctly. Note: Updating
     * this property does not automatically update texts in the scene tree. Please propagate the
     * `MainLoop.NOTIFICATION_TRANSLATION_CHANGED` notification manually after you have finished
     * modifying pseudolocalization related options.
     *
     * Generated from Godot docs: TranslationDomain.set_pseudolocalization_skip_placeholders_enabled
     */
    fun setPseudolocalizationSkipPlaceholdersEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setPseudolocalizationSkipPlaceholdersEnabledBind, segment, enabled)
    }

    /**
     * The expansion ratio to use during pseudolocalization. A value of `0.3` is sufficient for most
     * practical purposes, and will increase the length of each string by 30%. Note: Updating this
     * property does not automatically update texts in the scene tree. Please propagate the
     * `MainLoop.NOTIFICATION_TRANSLATION_CHANGED` notification manually after you have finished
     * modifying pseudolocalization related options.
     *
     * Generated from Godot docs: TranslationDomain.get_pseudolocalization_expansion_ratio
     */
    fun getPseudolocalizationExpansionRatio(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPseudolocalizationExpansionRatioBind, segment)
    }

    /**
     * The expansion ratio to use during pseudolocalization. A value of `0.3` is sufficient for most
     * practical purposes, and will increase the length of each string by 30%. Note: Updating this
     * property does not automatically update texts in the scene tree. Please propagate the
     * `MainLoop.NOTIFICATION_TRANSLATION_CHANGED` notification manually after you have finished
     * modifying pseudolocalization related options.
     *
     * Generated from Godot docs: TranslationDomain.set_pseudolocalization_expansion_ratio
     */
    fun setPseudolocalizationExpansionRatio(ratio: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPseudolocalizationExpansionRatioBind, segment, ratio)
    }

    /**
     * Prefix that will be prepended to the pseudolocalized string. Note: Updating this property does
     * not automatically update texts in the scene tree. Please propagate the
     * `MainLoop.NOTIFICATION_TRANSLATION_CHANGED` notification manually after you have finished
     * modifying pseudolocalization related options.
     *
     * Generated from Godot docs: TranslationDomain.get_pseudolocalization_prefix
     */
    fun getPseudolocalizationPrefix(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getPseudolocalizationPrefixBind, segment)
    }

    /**
     * Prefix that will be prepended to the pseudolocalized string. Note: Updating this property does
     * not automatically update texts in the scene tree. Please propagate the
     * `MainLoop.NOTIFICATION_TRANSLATION_CHANGED` notification manually after you have finished
     * modifying pseudolocalization related options.
     *
     * Generated from Godot docs: TranslationDomain.set_pseudolocalization_prefix
     */
    fun setPseudolocalizationPrefix(prefix: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setPseudolocalizationPrefixBind, segment, prefix)
    }

    /**
     * Suffix that will be appended to the pseudolocalized string. Note: Updating this property does
     * not automatically update texts in the scene tree. Please propagate the
     * `MainLoop.NOTIFICATION_TRANSLATION_CHANGED` notification manually after you have finished
     * modifying pseudolocalization related options.
     *
     * Generated from Godot docs: TranslationDomain.get_pseudolocalization_suffix
     */
    fun getPseudolocalizationSuffix(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getPseudolocalizationSuffixBind, segment)
    }

    /**
     * Suffix that will be appended to the pseudolocalized string. Note: Updating this property does
     * not automatically update texts in the scene tree. Please propagate the
     * `MainLoop.NOTIFICATION_TRANSLATION_CHANGED` notification manually after you have finished
     * modifying pseudolocalization related options.
     *
     * Generated from Godot docs: TranslationDomain.set_pseudolocalization_suffix
     */
    fun setPseudolocalizationSuffix(suffix: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setPseudolocalizationSuffixBind, segment, suffix)
    }

    /**
     * Returns the pseudolocalized string based on the `message` passed in.
     *
     * Generated from Godot docs: TranslationDomain.pseudolocalize
     */
    fun pseudolocalize(message: String): String {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetStringName(Binds.pseudolocalizeBind, segment, message)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TranslationDomain? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): TranslationDomain? =
            if (handle.address() == 0L) null else RefCounted.owned(TranslationDomain(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): TranslationDomain? =
            if (handle.address() == 0L) null else TranslationDomain(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_TRANSLATION_OBJECT_HASH = 606768082L
        @JvmField
        val getTranslationObjectBind =
            ObjectCalls.getMethodBind("TranslationDomain", "get_translation_object", GET_TRANSLATION_OBJECT_HASH)

        private const val ADD_TRANSLATION_HASH = 1466479800L
        @JvmField
        val addTranslationBind =
            ObjectCalls.getMethodBind("TranslationDomain", "add_translation", ADD_TRANSLATION_HASH)

        private const val REMOVE_TRANSLATION_HASH = 1466479800L
        @JvmField
        val removeTranslationBind =
            ObjectCalls.getMethodBind("TranslationDomain", "remove_translation", REMOVE_TRANSLATION_HASH)

        private const val CLEAR_HASH = 3218959716L
        @JvmField
        val clearBind =
            ObjectCalls.getMethodBind("TranslationDomain", "clear", CLEAR_HASH)

        private const val GET_TRANSLATIONS_HASH = 3995934104L
        @JvmField
        val getTranslationsBind =
            ObjectCalls.getMethodBind("TranslationDomain", "get_translations", GET_TRANSLATIONS_HASH)

        private const val HAS_TRANSLATION_FOR_LOCALE_HASH = 2034713381L
        @JvmField
        val hasTranslationForLocaleBind =
            ObjectCalls.getMethodBind("TranslationDomain", "has_translation_for_locale", HAS_TRANSLATION_FOR_LOCALE_HASH)

        private const val HAS_TRANSLATION_HASH = 2696976312L
        @JvmField
        val hasTranslationBind =
            ObjectCalls.getMethodBind("TranslationDomain", "has_translation", HAS_TRANSLATION_HASH)

        private const val FIND_TRANSLATIONS_HASH = 2109650934L
        @JvmField
        val findTranslationsBind =
            ObjectCalls.getMethodBind("TranslationDomain", "find_translations", FIND_TRANSLATIONS_HASH)

        private const val TRANSLATE_HASH = 1829228469L
        @JvmField
        val translateBind =
            ObjectCalls.getMethodBind("TranslationDomain", "translate", TRANSLATE_HASH)

        private const val TRANSLATE_PLURAL_HASH = 229954002L
        @JvmField
        val translatePluralBind =
            ObjectCalls.getMethodBind("TranslationDomain", "translate_plural", TRANSLATE_PLURAL_HASH)

        private const val GET_LOCALE_OVERRIDE_HASH = 201670096L
        @JvmField
        val getLocaleOverrideBind =
            ObjectCalls.getMethodBind("TranslationDomain", "get_locale_override", GET_LOCALE_OVERRIDE_HASH)

        private const val SET_LOCALE_OVERRIDE_HASH = 83702148L
        @JvmField
        val setLocaleOverrideBind =
            ObjectCalls.getMethodBind("TranslationDomain", "set_locale_override", SET_LOCALE_OVERRIDE_HASH)

        private const val IS_ENABLED_HASH = 36873697L
        @JvmField
        val isEnabledBind =
            ObjectCalls.getMethodBind("TranslationDomain", "is_enabled", IS_ENABLED_HASH)

        private const val SET_ENABLED_HASH = 2586408642L
        @JvmField
        val setEnabledBind =
            ObjectCalls.getMethodBind("TranslationDomain", "set_enabled", SET_ENABLED_HASH)

        private const val IS_PSEUDOLOCALIZATION_ENABLED_HASH = 36873697L
        @JvmField
        val isPseudolocalizationEnabledBind =
            ObjectCalls.getMethodBind("TranslationDomain", "is_pseudolocalization_enabled", IS_PSEUDOLOCALIZATION_ENABLED_HASH)

        private const val SET_PSEUDOLOCALIZATION_ENABLED_HASH = 2586408642L
        @JvmField
        val setPseudolocalizationEnabledBind =
            ObjectCalls.getMethodBind("TranslationDomain", "set_pseudolocalization_enabled", SET_PSEUDOLOCALIZATION_ENABLED_HASH)

        private const val IS_PSEUDOLOCALIZATION_ACCENTS_ENABLED_HASH = 36873697L
        @JvmField
        val isPseudolocalizationAccentsEnabledBind =
            ObjectCalls.getMethodBind("TranslationDomain", "is_pseudolocalization_accents_enabled", IS_PSEUDOLOCALIZATION_ACCENTS_ENABLED_HASH)

        private const val SET_PSEUDOLOCALIZATION_ACCENTS_ENABLED_HASH = 2586408642L
        @JvmField
        val setPseudolocalizationAccentsEnabledBind =
            ObjectCalls.getMethodBind("TranslationDomain", "set_pseudolocalization_accents_enabled", SET_PSEUDOLOCALIZATION_ACCENTS_ENABLED_HASH)

        private const val IS_PSEUDOLOCALIZATION_DOUBLE_VOWELS_ENABLED_HASH = 36873697L
        @JvmField
        val isPseudolocalizationDoubleVowelsEnabledBind =
            ObjectCalls.getMethodBind("TranslationDomain", "is_pseudolocalization_double_vowels_enabled", IS_PSEUDOLOCALIZATION_DOUBLE_VOWELS_ENABLED_HASH)

        private const val SET_PSEUDOLOCALIZATION_DOUBLE_VOWELS_ENABLED_HASH = 2586408642L
        @JvmField
        val setPseudolocalizationDoubleVowelsEnabledBind =
            ObjectCalls.getMethodBind("TranslationDomain", "set_pseudolocalization_double_vowels_enabled", SET_PSEUDOLOCALIZATION_DOUBLE_VOWELS_ENABLED_HASH)

        private const val IS_PSEUDOLOCALIZATION_FAKE_BIDI_ENABLED_HASH = 36873697L
        @JvmField
        val isPseudolocalizationFakeBidiEnabledBind =
            ObjectCalls.getMethodBind("TranslationDomain", "is_pseudolocalization_fake_bidi_enabled", IS_PSEUDOLOCALIZATION_FAKE_BIDI_ENABLED_HASH)

        private const val SET_PSEUDOLOCALIZATION_FAKE_BIDI_ENABLED_HASH = 2586408642L
        @JvmField
        val setPseudolocalizationFakeBidiEnabledBind =
            ObjectCalls.getMethodBind("TranslationDomain", "set_pseudolocalization_fake_bidi_enabled", SET_PSEUDOLOCALIZATION_FAKE_BIDI_ENABLED_HASH)

        private const val IS_PSEUDOLOCALIZATION_OVERRIDE_ENABLED_HASH = 36873697L
        @JvmField
        val isPseudolocalizationOverrideEnabledBind =
            ObjectCalls.getMethodBind("TranslationDomain", "is_pseudolocalization_override_enabled", IS_PSEUDOLOCALIZATION_OVERRIDE_ENABLED_HASH)

        private const val SET_PSEUDOLOCALIZATION_OVERRIDE_ENABLED_HASH = 2586408642L
        @JvmField
        val setPseudolocalizationOverrideEnabledBind =
            ObjectCalls.getMethodBind("TranslationDomain", "set_pseudolocalization_override_enabled", SET_PSEUDOLOCALIZATION_OVERRIDE_ENABLED_HASH)

        private const val IS_PSEUDOLOCALIZATION_SKIP_PLACEHOLDERS_ENABLED_HASH = 36873697L
        @JvmField
        val isPseudolocalizationSkipPlaceholdersEnabledBind =
            ObjectCalls.getMethodBind("TranslationDomain", "is_pseudolocalization_skip_placeholders_enabled", IS_PSEUDOLOCALIZATION_SKIP_PLACEHOLDERS_ENABLED_HASH)

        private const val SET_PSEUDOLOCALIZATION_SKIP_PLACEHOLDERS_ENABLED_HASH = 2586408642L
        @JvmField
        val setPseudolocalizationSkipPlaceholdersEnabledBind =
            ObjectCalls.getMethodBind("TranslationDomain", "set_pseudolocalization_skip_placeholders_enabled", SET_PSEUDOLOCALIZATION_SKIP_PLACEHOLDERS_ENABLED_HASH)

        private const val GET_PSEUDOLOCALIZATION_EXPANSION_RATIO_HASH = 1740695150L
        @JvmField
        val getPseudolocalizationExpansionRatioBind =
            ObjectCalls.getMethodBind("TranslationDomain", "get_pseudolocalization_expansion_ratio", GET_PSEUDOLOCALIZATION_EXPANSION_RATIO_HASH)

        private const val SET_PSEUDOLOCALIZATION_EXPANSION_RATIO_HASH = 373806689L
        @JvmField
        val setPseudolocalizationExpansionRatioBind =
            ObjectCalls.getMethodBind("TranslationDomain", "set_pseudolocalization_expansion_ratio", SET_PSEUDOLOCALIZATION_EXPANSION_RATIO_HASH)

        private const val GET_PSEUDOLOCALIZATION_PREFIX_HASH = 201670096L
        @JvmField
        val getPseudolocalizationPrefixBind =
            ObjectCalls.getMethodBind("TranslationDomain", "get_pseudolocalization_prefix", GET_PSEUDOLOCALIZATION_PREFIX_HASH)

        private const val SET_PSEUDOLOCALIZATION_PREFIX_HASH = 83702148L
        @JvmField
        val setPseudolocalizationPrefixBind =
            ObjectCalls.getMethodBind("TranslationDomain", "set_pseudolocalization_prefix", SET_PSEUDOLOCALIZATION_PREFIX_HASH)

        private const val GET_PSEUDOLOCALIZATION_SUFFIX_HASH = 201670096L
        @JvmField
        val getPseudolocalizationSuffixBind =
            ObjectCalls.getMethodBind("TranslationDomain", "get_pseudolocalization_suffix", GET_PSEUDOLOCALIZATION_SUFFIX_HASH)

        private const val SET_PSEUDOLOCALIZATION_SUFFIX_HASH = 83702148L
        @JvmField
        val setPseudolocalizationSuffixBind =
            ObjectCalls.getMethodBind("TranslationDomain", "set_pseudolocalization_suffix", SET_PSEUDOLOCALIZATION_SUFFIX_HASH)

        private const val PSEUDOLOCALIZE_HASH = 1965194235L
        @JvmField
        val pseudolocalizeBind =
            ObjectCalls.getMethodBind("TranslationDomain", "pseudolocalize", PSEUDOLOCALIZE_HASH)
    }
}

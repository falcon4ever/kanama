package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A self-contained collection of `Translation` resources.
 *
 * Generated from Godot docs: TranslationDomain
 */
class TranslationDomain(handle: MemorySegment) : RefCounted(handle) {
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
        return Translation.wrap(ObjectCalls.ptrcallWithStringArgRetObject(getTranslationObjectBind, handle, locale))
    }

    /**
     * Adds a translation.
     *
     * Generated from Godot docs: TranslationDomain.add_translation
     */
    fun addTranslation(translation: Translation?) {
        ObjectCalls.ptrcallWithObjectArgs(addTranslationBind, handle, listOf(translation?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Removes the given translation.
     *
     * Generated from Godot docs: TranslationDomain.remove_translation
     */
    fun removeTranslation(translation: Translation?) {
        ObjectCalls.ptrcallWithObjectArgs(removeTranslationBind, handle, listOf(translation?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Removes all translations.
     *
     * Generated from Godot docs: TranslationDomain.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    /**
     * Returns all available `Translation` instances as added by `add_translation`.
     *
     * Generated from Godot docs: TranslationDomain.get_translations
     */
    fun getTranslations(): List<Translation> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getTranslationsBind, handle, Translation::fromHandle)
    }

    /**
     * Returns `true` if there are any `Translation` instances that match `locale` (see
     * `TranslationServer.compare_locales`). If `exact` is `true`, only instances whose locale exactly
     * equals `locale` are considered.
     *
     * Generated from Godot docs: TranslationDomain.has_translation_for_locale
     */
    fun hasTranslationForLocale(locale: String, exact: Boolean): Boolean {
        return ObjectCalls.ptrcallWithStringAndBoolArgRetBool(hasTranslationForLocaleBind, handle, locale, exact)
    }

    /**
     * Returns `true` if this translation domain contains the given `translation`.
     *
     * Generated from Godot docs: TranslationDomain.has_translation
     */
    fun hasTranslation(translation: Translation?): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(hasTranslationBind, handle, translation?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the `Translation` instances that match `locale` (see
     * `TranslationServer.compare_locales`). If `exact` is `true`, only instances whose locale exactly
     * equals `locale` will be returned.
     *
     * Generated from Godot docs: TranslationDomain.find_translations
     */
    fun findTranslations(locale: String, exact: Boolean): List<Translation> {
        return ObjectCalls.ptrcallWithStringAndBoolArgRetTypedObjectList(findTranslationsBind, handle, locale, exact, Translation::fromHandle)
    }

    /**
     * Returns the current locale's translation for the given message and context.
     *
     * Generated from Godot docs: TranslationDomain.translate
     */
    fun translate(message: String, context: String = ""): String {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetStringName(translateBind, handle, message, context)
    }

    /**
     * Returns the current locale's translation for the given message, plural message and context. The
     * number `n` is the number or quantity of the plural object. It will be used to guide the
     * translation system to fetch the correct plural form for the selected language.
     *
     * Generated from Godot docs: TranslationDomain.translate_plural
     */
    fun translatePlural(message: String, messagePlural: String, n: Int, context: String = ""): String {
        return ObjectCalls.ptrcallWithTwoStringNameIntStringNameArgsRetStringName(translatePluralBind, handle, message, messagePlural, n, context)
    }

    /**
     * Returns the locale override of the domain. Returns an empty string if locale override is
     * disabled.
     *
     * Generated from Godot docs: TranslationDomain.get_locale_override
     */
    fun getLocaleOverride(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLocaleOverrideBind, handle)
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
        ObjectCalls.ptrcallWithStringArg(setLocaleOverrideBind, handle, locale)
    }

    /**
     * If `true`, translation is enabled. Otherwise, `translate` and `translate_plural` will return the
     * input message unchanged regardless of the current locale.
     *
     * Generated from Godot docs: TranslationDomain.is_enabled
     */
    fun isEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEnabledBind, handle)
    }

    /**
     * If `true`, translation is enabled. Otherwise, `translate` and `translate_plural` will return the
     * input message unchanged regardless of the current locale.
     *
     * Generated from Godot docs: TranslationDomain.set_enabled
     */
    fun setEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnabledBind, handle, enabled)
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
        return ObjectCalls.ptrcallNoArgsRetBool(isPseudolocalizationEnabledBind, handle)
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
        ObjectCalls.ptrcallWithBoolArg(setPseudolocalizationEnabledBind, handle, enabled)
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
        return ObjectCalls.ptrcallNoArgsRetBool(isPseudolocalizationAccentsEnabledBind, handle)
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
        ObjectCalls.ptrcallWithBoolArg(setPseudolocalizationAccentsEnabledBind, handle, enabled)
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
        return ObjectCalls.ptrcallNoArgsRetBool(isPseudolocalizationDoubleVowelsEnabledBind, handle)
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
        ObjectCalls.ptrcallWithBoolArg(setPseudolocalizationDoubleVowelsEnabledBind, handle, enabled)
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
        return ObjectCalls.ptrcallNoArgsRetBool(isPseudolocalizationFakeBidiEnabledBind, handle)
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
        ObjectCalls.ptrcallWithBoolArg(setPseudolocalizationFakeBidiEnabledBind, handle, enabled)
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
        return ObjectCalls.ptrcallNoArgsRetBool(isPseudolocalizationOverrideEnabledBind, handle)
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
        ObjectCalls.ptrcallWithBoolArg(setPseudolocalizationOverrideEnabledBind, handle, enabled)
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
        return ObjectCalls.ptrcallNoArgsRetBool(isPseudolocalizationSkipPlaceholdersEnabledBind, handle)
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
        ObjectCalls.ptrcallWithBoolArg(setPseudolocalizationSkipPlaceholdersEnabledBind, handle, enabled)
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
        return ObjectCalls.ptrcallNoArgsRetDouble(getPseudolocalizationExpansionRatioBind, handle)
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
        ObjectCalls.ptrcallWithDoubleArg(setPseudolocalizationExpansionRatioBind, handle, ratio)
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
        return ObjectCalls.ptrcallNoArgsRetString(getPseudolocalizationPrefixBind, handle)
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
        ObjectCalls.ptrcallWithStringArg(setPseudolocalizationPrefixBind, handle, prefix)
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
        return ObjectCalls.ptrcallNoArgsRetString(getPseudolocalizationSuffixBind, handle)
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
        ObjectCalls.ptrcallWithStringArg(setPseudolocalizationSuffixBind, handle, suffix)
    }

    /**
     * Returns the pseudolocalized string based on the `message` passed in.
     *
     * Generated from Godot docs: TranslationDomain.pseudolocalize
     */
    fun pseudolocalize(message: String): String {
        return ObjectCalls.ptrcallWithStringNameArgRetStringName(pseudolocalizeBind, handle, message)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): TranslationDomain? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TranslationDomain? =
            if (handle.address() == 0L) null else TranslationDomain(handle)

        private const val GET_TRANSLATION_OBJECT_HASH = 606768082L
        private val getTranslationObjectBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "get_translation_object", GET_TRANSLATION_OBJECT_HASH)
        }

        private const val ADD_TRANSLATION_HASH = 1466479800L
        private val addTranslationBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "add_translation", ADD_TRANSLATION_HASH)
        }

        private const val REMOVE_TRANSLATION_HASH = 1466479800L
        private val removeTranslationBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "remove_translation", REMOVE_TRANSLATION_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "clear", CLEAR_HASH)
        }

        private const val GET_TRANSLATIONS_HASH = 3995934104L
        private val getTranslationsBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "get_translations", GET_TRANSLATIONS_HASH)
        }

        private const val HAS_TRANSLATION_FOR_LOCALE_HASH = 2034713381L
        private val hasTranslationForLocaleBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "has_translation_for_locale", HAS_TRANSLATION_FOR_LOCALE_HASH)
        }

        private const val HAS_TRANSLATION_HASH = 2696976312L
        private val hasTranslationBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "has_translation", HAS_TRANSLATION_HASH)
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

        private const val GET_LOCALE_OVERRIDE_HASH = 201670096L
        private val getLocaleOverrideBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "get_locale_override", GET_LOCALE_OVERRIDE_HASH)
        }

        private const val SET_LOCALE_OVERRIDE_HASH = 83702148L
        private val setLocaleOverrideBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "set_locale_override", SET_LOCALE_OVERRIDE_HASH)
        }

        private const val IS_ENABLED_HASH = 36873697L
        private val isEnabledBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "is_enabled", IS_ENABLED_HASH)
        }

        private const val SET_ENABLED_HASH = 2586408642L
        private val setEnabledBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "set_enabled", SET_ENABLED_HASH)
        }

        private const val IS_PSEUDOLOCALIZATION_ENABLED_HASH = 36873697L
        private val isPseudolocalizationEnabledBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "is_pseudolocalization_enabled", IS_PSEUDOLOCALIZATION_ENABLED_HASH)
        }

        private const val SET_PSEUDOLOCALIZATION_ENABLED_HASH = 2586408642L
        private val setPseudolocalizationEnabledBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "set_pseudolocalization_enabled", SET_PSEUDOLOCALIZATION_ENABLED_HASH)
        }

        private const val IS_PSEUDOLOCALIZATION_ACCENTS_ENABLED_HASH = 36873697L
        private val isPseudolocalizationAccentsEnabledBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "is_pseudolocalization_accents_enabled", IS_PSEUDOLOCALIZATION_ACCENTS_ENABLED_HASH)
        }

        private const val SET_PSEUDOLOCALIZATION_ACCENTS_ENABLED_HASH = 2586408642L
        private val setPseudolocalizationAccentsEnabledBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "set_pseudolocalization_accents_enabled", SET_PSEUDOLOCALIZATION_ACCENTS_ENABLED_HASH)
        }

        private const val IS_PSEUDOLOCALIZATION_DOUBLE_VOWELS_ENABLED_HASH = 36873697L
        private val isPseudolocalizationDoubleVowelsEnabledBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "is_pseudolocalization_double_vowels_enabled", IS_PSEUDOLOCALIZATION_DOUBLE_VOWELS_ENABLED_HASH)
        }

        private const val SET_PSEUDOLOCALIZATION_DOUBLE_VOWELS_ENABLED_HASH = 2586408642L
        private val setPseudolocalizationDoubleVowelsEnabledBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "set_pseudolocalization_double_vowels_enabled", SET_PSEUDOLOCALIZATION_DOUBLE_VOWELS_ENABLED_HASH)
        }

        private const val IS_PSEUDOLOCALIZATION_FAKE_BIDI_ENABLED_HASH = 36873697L
        private val isPseudolocalizationFakeBidiEnabledBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "is_pseudolocalization_fake_bidi_enabled", IS_PSEUDOLOCALIZATION_FAKE_BIDI_ENABLED_HASH)
        }

        private const val SET_PSEUDOLOCALIZATION_FAKE_BIDI_ENABLED_HASH = 2586408642L
        private val setPseudolocalizationFakeBidiEnabledBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "set_pseudolocalization_fake_bidi_enabled", SET_PSEUDOLOCALIZATION_FAKE_BIDI_ENABLED_HASH)
        }

        private const val IS_PSEUDOLOCALIZATION_OVERRIDE_ENABLED_HASH = 36873697L
        private val isPseudolocalizationOverrideEnabledBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "is_pseudolocalization_override_enabled", IS_PSEUDOLOCALIZATION_OVERRIDE_ENABLED_HASH)
        }

        private const val SET_PSEUDOLOCALIZATION_OVERRIDE_ENABLED_HASH = 2586408642L
        private val setPseudolocalizationOverrideEnabledBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "set_pseudolocalization_override_enabled", SET_PSEUDOLOCALIZATION_OVERRIDE_ENABLED_HASH)
        }

        private const val IS_PSEUDOLOCALIZATION_SKIP_PLACEHOLDERS_ENABLED_HASH = 36873697L
        private val isPseudolocalizationSkipPlaceholdersEnabledBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "is_pseudolocalization_skip_placeholders_enabled", IS_PSEUDOLOCALIZATION_SKIP_PLACEHOLDERS_ENABLED_HASH)
        }

        private const val SET_PSEUDOLOCALIZATION_SKIP_PLACEHOLDERS_ENABLED_HASH = 2586408642L
        private val setPseudolocalizationSkipPlaceholdersEnabledBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "set_pseudolocalization_skip_placeholders_enabled", SET_PSEUDOLOCALIZATION_SKIP_PLACEHOLDERS_ENABLED_HASH)
        }

        private const val GET_PSEUDOLOCALIZATION_EXPANSION_RATIO_HASH = 1740695150L
        private val getPseudolocalizationExpansionRatioBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "get_pseudolocalization_expansion_ratio", GET_PSEUDOLOCALIZATION_EXPANSION_RATIO_HASH)
        }

        private const val SET_PSEUDOLOCALIZATION_EXPANSION_RATIO_HASH = 373806689L
        private val setPseudolocalizationExpansionRatioBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "set_pseudolocalization_expansion_ratio", SET_PSEUDOLOCALIZATION_EXPANSION_RATIO_HASH)
        }

        private const val GET_PSEUDOLOCALIZATION_PREFIX_HASH = 201670096L
        private val getPseudolocalizationPrefixBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "get_pseudolocalization_prefix", GET_PSEUDOLOCALIZATION_PREFIX_HASH)
        }

        private const val SET_PSEUDOLOCALIZATION_PREFIX_HASH = 83702148L
        private val setPseudolocalizationPrefixBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "set_pseudolocalization_prefix", SET_PSEUDOLOCALIZATION_PREFIX_HASH)
        }

        private const val GET_PSEUDOLOCALIZATION_SUFFIX_HASH = 201670096L
        private val getPseudolocalizationSuffixBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "get_pseudolocalization_suffix", GET_PSEUDOLOCALIZATION_SUFFIX_HASH)
        }

        private const val SET_PSEUDOLOCALIZATION_SUFFIX_HASH = 83702148L
        private val setPseudolocalizationSuffixBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "set_pseudolocalization_suffix", SET_PSEUDOLOCALIZATION_SUFFIX_HASH)
        }

        private const val PSEUDOLOCALIZE_HASH = 1965194235L
        private val pseudolocalizeBind by lazy {
            ObjectCalls.getMethodBind("TranslationDomain", "pseudolocalize", PSEUDOLOCALIZE_HASH)
        }
    }
}

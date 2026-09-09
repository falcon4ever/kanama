package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
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

    fun getTranslationObject(locale: String): Translation? {
        checkOpen()
        return Translation.wrap(ObjectCalls.ptrcallWithStringArgRetObject(getTranslationObjectBind, handle, locale))
    }

    fun addTranslation(translation: Translation?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(addTranslationBind, handle, listOf(translation?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun removeTranslation(translation: Translation?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(removeTranslationBind, handle, listOf(translation?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun getTranslations(): List<Translation> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getTranslationsBind, handle, Translation::fromHandle)
    }

    fun hasTranslationForLocale(locale: String, exact: Boolean): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndBoolArgRetBool(hasTranslationForLocaleBind, handle, locale, exact)
    }

    fun hasTranslation(translation: Translation?): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectArgRetBool(hasTranslationBind, handle, translation?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getLocaleOverride(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getLocaleOverrideBind, handle)
    }

    fun setLocaleOverride(locale: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setLocaleOverrideBind, handle, locale)
    }

    fun isEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isEnabledBind, handle)
    }

    fun setEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setEnabledBind, handle, enabled)
    }

    fun isPseudolocalizationEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isPseudolocalizationEnabledBind, handle)
    }

    fun setPseudolocalizationEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setPseudolocalizationEnabledBind, handle, enabled)
    }

    fun isPseudolocalizationAccentsEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isPseudolocalizationAccentsEnabledBind, handle)
    }

    fun setPseudolocalizationAccentsEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setPseudolocalizationAccentsEnabledBind, handle, enabled)
    }

    fun isPseudolocalizationDoubleVowelsEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isPseudolocalizationDoubleVowelsEnabledBind, handle)
    }

    fun setPseudolocalizationDoubleVowelsEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setPseudolocalizationDoubleVowelsEnabledBind, handle, enabled)
    }

    fun isPseudolocalizationFakeBidiEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isPseudolocalizationFakeBidiEnabledBind, handle)
    }

    fun setPseudolocalizationFakeBidiEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setPseudolocalizationFakeBidiEnabledBind, handle, enabled)
    }

    fun isPseudolocalizationOverrideEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isPseudolocalizationOverrideEnabledBind, handle)
    }

    fun setPseudolocalizationOverrideEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setPseudolocalizationOverrideEnabledBind, handle, enabled)
    }

    fun isPseudolocalizationSkipPlaceholdersEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isPseudolocalizationSkipPlaceholdersEnabledBind, handle)
    }

    fun setPseudolocalizationSkipPlaceholdersEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setPseudolocalizationSkipPlaceholdersEnabledBind, handle, enabled)
    }

    fun getPseudolocalizationExpansionRatio(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getPseudolocalizationExpansionRatioBind, handle)
    }

    fun setPseudolocalizationExpansionRatio(ratio: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setPseudolocalizationExpansionRatioBind, handle, ratio)
    }

    fun getPseudolocalizationPrefix(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getPseudolocalizationPrefixBind, handle)
    }

    fun setPseudolocalizationPrefix(prefix: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setPseudolocalizationPrefixBind, handle, prefix)
    }

    fun getPseudolocalizationSuffix(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getPseudolocalizationSuffixBind, handle)
    }

    fun setPseudolocalizationSuffix(suffix: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setPseudolocalizationSuffixBind, handle, suffix)
    }

    fun pseudolocalize(message: String): String {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetStringName(pseudolocalizeBind, handle, message)
    }

    companion object {
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

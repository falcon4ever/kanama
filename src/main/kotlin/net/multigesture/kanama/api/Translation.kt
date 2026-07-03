package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A language translation that maps a collection of strings to their individual translations.
 *
 * Generated from Godot docs: Translation
 */
open class Translation(handle: MemorySegment) : Resource(handle) {
    var locale: String
        @JvmName("localeProperty")
        get() = getLocale()
        @JvmName("setLocaleProperty")
        set(value) = setLocale(value)

    var pluralRulesOverride: String
        @JvmName("pluralRulesOverrideProperty")
        get() = getPluralRulesOverride()
        @JvmName("setPluralRulesOverrideProperty")
        set(value) = setPluralRulesOverride(value)

    /**
     * The locale of the translation.
     *
     * Generated from Godot docs: Translation.set_locale
     */
    fun setLocale(locale: String) {
        ObjectCalls.ptrcallWithStringArg(setLocaleBind, handle, locale)
    }

    /**
     * The locale of the translation.
     *
     * Generated from Godot docs: Translation.get_locale
     */
    fun getLocale(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLocaleBind, handle)
    }

    /**
     * Adds a message if nonexistent, followed by its translation. An additional context could be used
     * to specify the translation context or differentiate polysemic words.
     *
     * Generated from Godot docs: Translation.add_message
     */
    fun addMessage(srcMessage: String, xlatedMessage: String, context: String = "") {
        ObjectCalls.ptrcallWithThreeStringNameArgs(addMessageBind, handle, srcMessage, xlatedMessage, context)
    }

    /**
     * Adds a message involving plural translation if nonexistent, followed by its translation. An
     * additional context could be used to specify the translation context or differentiate polysemic
     * words.
     *
     * Generated from Godot docs: Translation.add_plural_message
     */
    fun addPluralMessage(srcMessage: String, xlatedMessages: List<String>, context: String = "") {
        ObjectCalls.ptrcallWithStringNamePackedStringListAndStringNameArgs(addPluralMessageBind, handle, srcMessage, xlatedMessages, context)
    }

    /**
     * Returns a message's translation.
     *
     * Generated from Godot docs: Translation.get_message
     */
    fun getMessage(srcMessage: String, context: String = ""): String {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetStringName(getMessageBind, handle, srcMessage, context)
    }

    /**
     * Returns a message's translation involving plurals. The number `n` is the number or quantity of
     * the plural object. It will be used to guide the translation system to fetch the correct plural
     * form for the selected language. Note: Plurals are only supported in gettext-based translations
     * (PO) ($DOCS_URL/tutorials/i18n/localization_using_gettext.html), not CSV.
     *
     * Generated from Godot docs: Translation.get_plural_message
     */
    fun getPluralMessage(srcMessage: String, srcPluralMessage: String, n: Int, context: String = ""): String {
        return ObjectCalls.ptrcallWithTwoStringNameIntStringNameArgsRetStringName(getPluralMessageBind, handle, srcMessage, srcPluralMessage, n, context)
    }

    /**
     * Erases a message.
     *
     * Generated from Godot docs: Translation.erase_message
     */
    fun eraseMessage(srcMessage: String, context: String = "") {
        ObjectCalls.ptrcallWithTwoStringNameArgs(eraseMessageBind, handle, srcMessage, context)
    }

    /**
     * Returns the keys of all messages, that is, the context and untranslated strings of each message.
     * Note: If a message does not use a context, the corresponding element is the untranslated string.
     * Otherwise, the corresponding element is the context and untranslated string separated by the EOT
     * character (`U+0004`). This is done for compatibility purposes.
     *
     * Generated from Godot docs: Translation.get_message_list
     */
    fun getMessageList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getMessageListBind, handle)
    }

    /**
     * Returns all the translated strings.
     *
     * Generated from Godot docs: Translation.get_translated_message_list
     */
    fun getTranslatedMessageList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getTranslatedMessageListBind, handle)
    }

    /**
     * Returns the number of existing messages.
     *
     * Generated from Godot docs: Translation.get_message_count
     */
    fun getMessageCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMessageCountBind, handle)
    }

    /**
     * The plural rules string to enforce. See GNU gettext
     * (https://www.gnu.org/software/gettext/manual/html_node/Plural-forms.html) for examples and more
     * info. If empty or invalid, default plural rules from `TranslationServer.get_plural_rules` are
     * used. The English plural rules are used as a fallback.
     *
     * Generated from Godot docs: Translation.set_plural_rules_override
     */
    fun setPluralRulesOverride(rules: String) {
        ObjectCalls.ptrcallWithStringArg(setPluralRulesOverrideBind, handle, rules)
    }

    /**
     * The plural rules string to enforce. See GNU gettext
     * (https://www.gnu.org/software/gettext/manual/html_node/Plural-forms.html) for examples and more
     * info. If empty or invalid, default plural rules from `TranslationServer.get_plural_rules` are
     * used. The English plural rules are used as a fallback.
     *
     * Generated from Godot docs: Translation.get_plural_rules_override
     */
    fun getPluralRulesOverride(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getPluralRulesOverrideBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Translation? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Translation? =
            if (handle.address() == 0L) null else Translation(handle)

        private const val SET_LOCALE_HASH = 83702148L
        private val setLocaleBind by lazy {
            ObjectCalls.getMethodBind("Translation", "set_locale", SET_LOCALE_HASH)
        }

        private const val GET_LOCALE_HASH = 201670096L
        private val getLocaleBind by lazy {
            ObjectCalls.getMethodBind("Translation", "get_locale", GET_LOCALE_HASH)
        }

        private const val ADD_MESSAGE_HASH = 3898530326L
        private val addMessageBind by lazy {
            ObjectCalls.getMethodBind("Translation", "add_message", ADD_MESSAGE_HASH)
        }

        private const val ADD_PLURAL_MESSAGE_HASH = 2356982266L
        private val addPluralMessageBind by lazy {
            ObjectCalls.getMethodBind("Translation", "add_plural_message", ADD_PLURAL_MESSAGE_HASH)
        }

        private const val GET_MESSAGE_HASH = 1829228469L
        private val getMessageBind by lazy {
            ObjectCalls.getMethodBind("Translation", "get_message", GET_MESSAGE_HASH)
        }

        private const val GET_PLURAL_MESSAGE_HASH = 229954002L
        private val getPluralMessageBind by lazy {
            ObjectCalls.getMethodBind("Translation", "get_plural_message", GET_PLURAL_MESSAGE_HASH)
        }

        private const val ERASE_MESSAGE_HASH = 3959009644L
        private val eraseMessageBind by lazy {
            ObjectCalls.getMethodBind("Translation", "erase_message", ERASE_MESSAGE_HASH)
        }

        private const val GET_MESSAGE_LIST_HASH = 1139954409L
        private val getMessageListBind by lazy {
            ObjectCalls.getMethodBind("Translation", "get_message_list", GET_MESSAGE_LIST_HASH)
        }

        private const val GET_TRANSLATED_MESSAGE_LIST_HASH = 1139954409L
        private val getTranslatedMessageListBind by lazy {
            ObjectCalls.getMethodBind("Translation", "get_translated_message_list", GET_TRANSLATED_MESSAGE_LIST_HASH)
        }

        private const val GET_MESSAGE_COUNT_HASH = 3905245786L
        private val getMessageCountBind by lazy {
            ObjectCalls.getMethodBind("Translation", "get_message_count", GET_MESSAGE_COUNT_HASH)
        }

        private const val SET_PLURAL_RULES_OVERRIDE_HASH = 83702148L
        private val setPluralRulesOverrideBind by lazy {
            ObjectCalls.getMethodBind("Translation", "set_plural_rules_override", SET_PLURAL_RULES_OVERRIDE_HASH)
        }

        private const val GET_PLURAL_RULES_OVERRIDE_HASH = 201670096L
        private val getPluralRulesOverrideBind by lazy {
            ObjectCalls.getMethodBind("Translation", "get_plural_rules_override", GET_PLURAL_RULES_OVERRIDE_HASH)
        }
    }
}

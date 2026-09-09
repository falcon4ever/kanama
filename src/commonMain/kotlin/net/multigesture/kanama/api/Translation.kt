package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

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
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setLocaleBind, handle, locale)
    }

    /**
     * The locale of the translation.
     *
     * Generated from Godot docs: Translation.get_locale
     */
    fun getLocale(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getLocaleBind, handle)
    }

    /**
     * Adds a message if nonexistent, followed by its translation. An additional context could be used
     * to specify the translation context or differentiate polysemic words.
     *
     * Generated from Godot docs: Translation.add_message
     */
    fun addMessage(srcMessage: String, xlatedMessage: String, context: String = "") {
        checkOpen()
        ObjectCalls.ptrcallWithThreeStringNameArgs(addMessageBind, handle, srcMessage, xlatedMessage, context)
    }

    /**
     * Erases a message.
     *
     * Generated from Godot docs: Translation.erase_message
     */
    fun eraseMessage(srcMessage: String, context: String = "") {
        checkOpen()
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getMessageListBind, handle)
    }

    /**
     * Returns all the translated strings.
     *
     * Generated from Godot docs: Translation.get_translated_message_list
     */
    fun getTranslatedMessageList(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getTranslatedMessageListBind, handle)
    }

    /**
     * Returns the number of existing messages.
     *
     * Generated from Godot docs: Translation.get_message_count
     */
    fun getMessageCount(): Int {
        checkOpen()
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
        checkOpen()
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
        checkOpen()
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

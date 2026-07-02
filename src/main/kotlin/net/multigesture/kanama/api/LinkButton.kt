package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A button that represents a link.
 *
 * Generated from Godot docs: LinkButton
 */
class LinkButton(handle: MemorySegment) : BaseButton(handle) {
    var text: String
        @JvmName("textProperty")
        get() = getText()
        @JvmName("setTextProperty")
        set(value) = setText(value)

    var underline: Long
        @JvmName("underlineProperty")
        get() = getUnderlineMode()
        @JvmName("setUnderlineProperty")
        set(value) = setUnderlineMode(value)

    var uri: String
        @JvmName("uriProperty")
        get() = getUri()
        @JvmName("setUriProperty")
        set(value) = setUri(value)

    var textOverrunBehavior: Long
        @JvmName("textOverrunBehaviorProperty")
        get() = getTextOverrunBehavior()
        @JvmName("setTextOverrunBehaviorProperty")
        set(value) = setTextOverrunBehavior(value)

    var ellipsisChar: String
        @JvmName("ellipsisCharProperty")
        get() = getEllipsisChar()
        @JvmName("setEllipsisCharProperty")
        set(value) = setEllipsisChar(value)

    var textDirection: Long
        @JvmName("textDirectionProperty")
        get() = getTextDirection()
        @JvmName("setTextDirectionProperty")
        set(value) = setTextDirection(value)

    var language: String
        @JvmName("languageProperty")
        get() = getLanguage()
        @JvmName("setLanguageProperty")
        set(value) = setLanguage(value)

    var structuredTextBidiOverride: Long
        @JvmName("structuredTextBidiOverrideProperty")
        get() = getStructuredTextBidiOverride()
        @JvmName("setStructuredTextBidiOverrideProperty")
        set(value) = setStructuredTextBidiOverride(value)

    var structuredTextBidiOverrideOptions: List<Any?>
        @JvmName("structuredTextBidiOverrideOptionsProperty")
        get() = getStructuredTextBidiOverrideOptions()
        @JvmName("setStructuredTextBidiOverrideOptionsProperty")
        set(value) = setStructuredTextBidiOverrideOptions(value)

    fun setText(text: String) {
        ObjectCalls.ptrcallWithStringArg(setTextBind, handle, text)
    }

    fun getText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTextBind, handle)
    }

    fun setTextOverrunBehavior(overrunBehavior: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextOverrunBehaviorBind, handle, overrunBehavior)
    }

    fun getTextOverrunBehavior(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextOverrunBehaviorBind, handle)
    }

    fun setEllipsisChar(char: String) {
        ObjectCalls.ptrcallWithStringArg(setEllipsisCharBind, handle, char)
    }

    fun getEllipsisChar(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getEllipsisCharBind, handle)
    }

    fun setTextDirection(direction: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextDirectionBind, handle, direction)
    }

    fun getTextDirection(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextDirectionBind, handle)
    }

    fun setLanguage(language: String) {
        ObjectCalls.ptrcallWithStringArg(setLanguageBind, handle, language)
    }

    fun getLanguage(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLanguageBind, handle)
    }

    fun setUri(uri: String) {
        ObjectCalls.ptrcallWithStringArg(setUriBind, handle, uri)
    }

    fun getUri(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getUriBind, handle)
    }

    fun setUnderlineMode(underlineMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setUnderlineModeBind, handle, underlineMode)
    }

    fun getUnderlineMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getUnderlineModeBind, handle)
    }

    fun setStructuredTextBidiOverride(parser: Long) {
        ObjectCalls.ptrcallWithLongArg(setStructuredTextBidiOverrideBind, handle, parser)
    }

    fun getStructuredTextBidiOverride(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStructuredTextBidiOverrideBind, handle)
    }

    fun setStructuredTextBidiOverrideOptions(args: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(setStructuredTextBidiOverrideOptionsBind, handle, args)
    }

    fun getStructuredTextBidiOverrideOptions(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getStructuredTextBidiOverrideOptionsBind, handle)
    }

    companion object {
        const val UNDERLINE_MODE_ALWAYS: Long = 0L
        const val UNDERLINE_MODE_ON_HOVER: Long = 1L
        const val UNDERLINE_MODE_NEVER: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): LinkButton? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): LinkButton? =
            if (handle.address() == 0L) null else LinkButton(handle)

        private const val SET_TEXT_HASH = 83702148L
        private val setTextBind by lazy {
            ObjectCalls.getMethodBind("LinkButton", "set_text", SET_TEXT_HASH)
        }

        private const val GET_TEXT_HASH = 201670096L
        private val getTextBind by lazy {
            ObjectCalls.getMethodBind("LinkButton", "get_text", GET_TEXT_HASH)
        }

        private const val SET_TEXT_OVERRUN_BEHAVIOR_HASH = 1008890932L
        private val setTextOverrunBehaviorBind by lazy {
            ObjectCalls.getMethodBind("LinkButton", "set_text_overrun_behavior", SET_TEXT_OVERRUN_BEHAVIOR_HASH)
        }

        private const val GET_TEXT_OVERRUN_BEHAVIOR_HASH = 3779142101L
        private val getTextOverrunBehaviorBind by lazy {
            ObjectCalls.getMethodBind("LinkButton", "get_text_overrun_behavior", GET_TEXT_OVERRUN_BEHAVIOR_HASH)
        }

        private const val SET_ELLIPSIS_CHAR_HASH = 83702148L
        private val setEllipsisCharBind by lazy {
            ObjectCalls.getMethodBind("LinkButton", "set_ellipsis_char", SET_ELLIPSIS_CHAR_HASH)
        }

        private const val GET_ELLIPSIS_CHAR_HASH = 201670096L
        private val getEllipsisCharBind by lazy {
            ObjectCalls.getMethodBind("LinkButton", "get_ellipsis_char", GET_ELLIPSIS_CHAR_HASH)
        }

        private const val SET_TEXT_DIRECTION_HASH = 119160795L
        private val setTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("LinkButton", "set_text_direction", SET_TEXT_DIRECTION_HASH)
        }

        private const val GET_TEXT_DIRECTION_HASH = 797257663L
        private val getTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("LinkButton", "get_text_direction", GET_TEXT_DIRECTION_HASH)
        }

        private const val SET_LANGUAGE_HASH = 83702148L
        private val setLanguageBind by lazy {
            ObjectCalls.getMethodBind("LinkButton", "set_language", SET_LANGUAGE_HASH)
        }

        private const val GET_LANGUAGE_HASH = 201670096L
        private val getLanguageBind by lazy {
            ObjectCalls.getMethodBind("LinkButton", "get_language", GET_LANGUAGE_HASH)
        }

        private const val SET_URI_HASH = 83702148L
        private val setUriBind by lazy {
            ObjectCalls.getMethodBind("LinkButton", "set_uri", SET_URI_HASH)
        }

        private const val GET_URI_HASH = 201670096L
        private val getUriBind by lazy {
            ObjectCalls.getMethodBind("LinkButton", "get_uri", GET_URI_HASH)
        }

        private const val SET_UNDERLINE_MODE_HASH = 4032947085L
        private val setUnderlineModeBind by lazy {
            ObjectCalls.getMethodBind("LinkButton", "set_underline_mode", SET_UNDERLINE_MODE_HASH)
        }

        private const val GET_UNDERLINE_MODE_HASH = 568343738L
        private val getUnderlineModeBind by lazy {
            ObjectCalls.getMethodBind("LinkButton", "get_underline_mode", GET_UNDERLINE_MODE_HASH)
        }

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 55961453L
        private val setStructuredTextBidiOverrideBind by lazy {
            ObjectCalls.getMethodBind("LinkButton", "set_structured_text_bidi_override", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)
        }

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 3385126229L
        private val getStructuredTextBidiOverrideBind by lazy {
            ObjectCalls.getMethodBind("LinkButton", "get_structured_text_bidi_override", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)
        }

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 381264803L
        private val setStructuredTextBidiOverrideOptionsBind by lazy {
            ObjectCalls.getMethodBind("LinkButton", "set_structured_text_bidi_override_options", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)
        }

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 3995934104L
        private val getStructuredTextBidiOverrideOptionsBind by lazy {
            ObjectCalls.getMethodBind("LinkButton", "get_structured_text_bidi_override_options", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)
        }
    }
}

package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A button that represents a link.
 *
 * Generated from Godot docs: LinkButton
 */
class LinkButton(handle: GodotHandle) : BaseButton(handle) {
    var text: String
        @JvmName("textProperty")
        get() = getText()
        @JvmName("setTextProperty")
        set(value) = setText(value)

    var underline: LinkButton.UnderlineMode
        @JvmName("underlineProperty")
        get() = getUnderlineMode()
        @JvmName("setUnderlineProperty")
        set(value) = setUnderlineMode(value)

    var uri: String
        @JvmName("uriProperty")
        get() = getUri()
        @JvmName("setUriProperty")
        set(value) = setUri(value)

    var textOverrunBehavior: TextServer.OverrunBehavior
        @JvmName("textOverrunBehaviorProperty")
        get() = getTextOverrunBehavior()
        @JvmName("setTextOverrunBehaviorProperty")
        set(value) = setTextOverrunBehavior(value)

    var ellipsisChar: String
        @JvmName("ellipsisCharProperty")
        get() = getEllipsisChar()
        @JvmName("setEllipsisCharProperty")
        set(value) = setEllipsisChar(value)

    var textDirection: Control.TextDirection
        @JvmName("textDirectionProperty")
        get() = getTextDirection()
        @JvmName("setTextDirectionProperty")
        set(value) = setTextDirection(value)

    var language: String
        @JvmName("languageProperty")
        get() = getLanguage()
        @JvmName("setLanguageProperty")
        set(value) = setLanguage(value)

    var structuredTextBidiOverride: TextServer.StructuredTextParser
        @JvmName("structuredTextBidiOverrideProperty")
        get() = getStructuredTextBidiOverride()
        @JvmName("setStructuredTextBidiOverrideProperty")
        set(value) = setStructuredTextBidiOverride(value)

    var structuredTextBidiOverrideOptions: List<Any?>
        @JvmName("structuredTextBidiOverrideOptionsProperty")
        get() = getStructuredTextBidiOverrideOptions()
        @JvmName("setStructuredTextBidiOverrideOptionsProperty")
        set(value) = setStructuredTextBidiOverrideOptions(value)

    /**
     * The button's text that will be displayed inside the button's area.
     *
     * Generated from Godot docs: LinkButton.set_text
     */
    fun setText(text: String) {
        ObjectCalls.ptrcallWithStringArg(setTextBind, segment, text)
    }

    /**
     * The button's text that will be displayed inside the button's area.
     *
     * Generated from Godot docs: LinkButton.get_text
     */
    fun getText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTextBind, segment)
    }

    /**
     * Sets the clipping behavior when the text exceeds the node's bounding rectangle.
     *
     * Generated from Godot docs: LinkButton.set_text_overrun_behavior
     */
    fun setTextOverrunBehavior(overrunBehavior: TextServer.OverrunBehavior) {
        ObjectCalls.ptrcallWithLongArg(setTextOverrunBehaviorBind, segment, overrunBehavior.value)
    }

    /**
     * Sets the clipping behavior when the text exceeds the node's bounding rectangle.
     *
     * Generated from Godot docs: LinkButton.get_text_overrun_behavior
     */
    fun getTextOverrunBehavior(): TextServer.OverrunBehavior {
        return TextServer.OverrunBehavior(ObjectCalls.ptrcallNoArgsRetLong(getTextOverrunBehaviorBind, segment))
    }

    /**
     * Ellipsis character used for text clipping.
     *
     * Generated from Godot docs: LinkButton.set_ellipsis_char
     */
    fun setEllipsisChar(char: String) {
        ObjectCalls.ptrcallWithStringArg(setEllipsisCharBind, segment, char)
    }

    /**
     * Ellipsis character used for text clipping.
     *
     * Generated from Godot docs: LinkButton.get_ellipsis_char
     */
    fun getEllipsisChar(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getEllipsisCharBind, segment)
    }

    /**
     * Base text writing direction.
     *
     * Generated from Godot docs: LinkButton.set_text_direction
     */
    fun setTextDirection(direction: Control.TextDirection) {
        ObjectCalls.ptrcallWithLongArg(setTextDirectionBind, segment, direction.value)
    }

    /**
     * Base text writing direction.
     *
     * Generated from Godot docs: LinkButton.get_text_direction
     */
    fun getTextDirection(): Control.TextDirection {
        return Control.TextDirection(ObjectCalls.ptrcallNoArgsRetLong(getTextDirectionBind, segment))
    }

    /**
     * Language code used for line-breaking and text shaping algorithms. If left empty, the current
     * locale is used instead.
     *
     * Generated from Godot docs: LinkButton.set_language
     */
    fun setLanguage(language: String) {
        ObjectCalls.ptrcallWithStringArg(setLanguageBind, segment, language)
    }

    /**
     * Language code used for line-breaking and text shaping algorithms. If left empty, the current
     * locale is used instead.
     *
     * Generated from Godot docs: LinkButton.get_language
     */
    fun getLanguage(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLanguageBind, segment)
    }

    /**
     * The URI (https://en.wikipedia.org/wiki/Uniform_Resource_Identifier) for this `LinkButton`. If
     * set to a valid URI, pressing the button opens the URI using the operating system's default
     * program for the protocol (via `OS.shell_open`). HTTP and HTTPS URLs open the default web
     * browser.
     *
     * Generated from Godot docs: LinkButton.set_uri
     */
    fun setUri(uri: String) {
        ObjectCalls.ptrcallWithStringArg(setUriBind, segment, uri)
    }

    /**
     * The URI (https://en.wikipedia.org/wiki/Uniform_Resource_Identifier) for this `LinkButton`. If
     * set to a valid URI, pressing the button opens the URI using the operating system's default
     * program for the protocol (via `OS.shell_open`). HTTP and HTTPS URLs open the default web
     * browser.
     *
     * Generated from Godot docs: LinkButton.get_uri
     */
    fun getUri(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getUriBind, segment)
    }

    /**
     * The underline mode to use for the text.
     *
     * Generated from Godot docs: LinkButton.set_underline_mode
     */
    fun setUnderlineMode(underlineMode: LinkButton.UnderlineMode) {
        ObjectCalls.ptrcallWithLongArg(setUnderlineModeBind, segment, underlineMode.value)
    }

    /**
     * The underline mode to use for the text.
     *
     * Generated from Godot docs: LinkButton.get_underline_mode
     */
    fun getUnderlineMode(): LinkButton.UnderlineMode {
        return LinkButton.UnderlineMode(ObjectCalls.ptrcallNoArgsRetLong(getUnderlineModeBind, segment))
    }

    /**
     * Set BiDi algorithm override for the structured text.
     *
     * Generated from Godot docs: LinkButton.set_structured_text_bidi_override
     */
    fun setStructuredTextBidiOverride(parser: TextServer.StructuredTextParser) {
        ObjectCalls.ptrcallWithLongArg(setStructuredTextBidiOverrideBind, segment, parser.value)
    }

    /**
     * Set BiDi algorithm override for the structured text.
     *
     * Generated from Godot docs: LinkButton.get_structured_text_bidi_override
     */
    fun getStructuredTextBidiOverride(): TextServer.StructuredTextParser {
        return TextServer.StructuredTextParser(ObjectCalls.ptrcallNoArgsRetLong(getStructuredTextBidiOverrideBind, segment))
    }

    /**
     * Set additional options for BiDi override.
     *
     * Generated from Godot docs: LinkButton.set_structured_text_bidi_override_options
     */
    fun setStructuredTextBidiOverrideOptions(args: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(setStructuredTextBidiOverrideOptionsBind, segment, args)
    }

    /**
     * Set additional options for BiDi override.
     *
     * Generated from Godot docs: LinkButton.get_structured_text_bidi_override_options
     */
    fun getStructuredTextBidiOverrideOptions(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getStructuredTextBidiOverrideOptionsBind, segment)
    }

    @JvmInline
    value class UnderlineMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The LinkButton will always show an underline at the bottom of its text.
             *
             * Generated from Godot docs: LinkButton.UNDERLINE_MODE_ALWAYS
             */
            val ALWAYS: UnderlineMode get() = UnderlineMode(0L)
            /**
             * The LinkButton will show an underline at the bottom of its text when the mouse cursor is over
             * it.
             *
             * Generated from Godot docs: LinkButton.UNDERLINE_MODE_ON_HOVER
             */
            val ON_HOVER: UnderlineMode get() = UnderlineMode(1L)
            /**
             * The LinkButton will never show an underline at the bottom of its text.
             *
             * Generated from Godot docs: LinkButton.UNDERLINE_MODE_NEVER
             */
            val NEVER: UnderlineMode get() = UnderlineMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): LinkButton? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): LinkButton? =
            if (handle.address() == 0L) null else LinkButton(GodotHandle(handle))

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

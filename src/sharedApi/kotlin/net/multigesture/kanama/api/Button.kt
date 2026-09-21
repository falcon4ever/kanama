package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A themed button that can contain text and an icon.
 *
 * Generated from Godot docs: Button
 */
open class Button(handle: GodotHandle) : BaseButton(handle) {
    var text: String
        @JvmName("textProperty")
        get() = getText()
        @JvmName("setTextProperty")
        set(value) = setText(value)

    var icon: Texture2D?
        @JvmName("iconProperty")
        get() = getButtonIcon()
        @JvmName("setIconProperty")
        set(value) = setButtonIcon(value)

    var flat: Boolean
        @JvmName("flatProperty")
        get() = isFlat()
        @JvmName("setFlatProperty")
        set(value) = setFlat(value)

    var alignment: Long
        @JvmName("alignmentProperty")
        get() = getTextAlignment()
        @JvmName("setAlignmentProperty")
        set(value) = setTextAlignment(value)

    var textOverrunBehavior: Long
        @JvmName("textOverrunBehaviorProperty")
        get() = getTextOverrunBehavior()
        @JvmName("setTextOverrunBehaviorProperty")
        set(value) = setTextOverrunBehavior(value)

    var autowrapMode: Long
        @JvmName("autowrapModeProperty")
        get() = getAutowrapMode()
        @JvmName("setAutowrapModeProperty")
        set(value) = setAutowrapMode(value)

    var autowrapTrimFlags: Long
        @JvmName("autowrapTrimFlagsProperty")
        get() = getAutowrapTrimFlags()
        @JvmName("setAutowrapTrimFlagsProperty")
        set(value) = setAutowrapTrimFlags(value)

    var clipText: Boolean
        @JvmName("clipTextProperty")
        get() = getClipText()
        @JvmName("setClipTextProperty")
        set(value) = setClipText(value)

    var iconAlignment: Long
        @JvmName("iconAlignmentProperty")
        get() = getIconAlignment()
        @JvmName("setIconAlignmentProperty")
        set(value) = setIconAlignment(value)

    var verticalIconAlignment: Long
        @JvmName("verticalIconAlignmentProperty")
        get() = getVerticalIconAlignment()
        @JvmName("setVerticalIconAlignmentProperty")
        set(value) = setVerticalIconAlignment(value)

    var expandIcon: Boolean
        @JvmName("expandIconProperty")
        get() = isExpandIcon()
        @JvmName("setExpandIconProperty")
        set(value) = setExpandIcon(value)

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

    /**
     * The button's text that will be displayed inside the button's area.
     *
     * Generated from Godot docs: Button.set_text
     */
    fun setText(text: String) {
        ObjectCalls.ptrcallWithStringArg(setTextBind, segment, text)
    }

    /**
     * The button's text that will be displayed inside the button's area.
     *
     * Generated from Godot docs: Button.get_text
     */
    fun getText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTextBind, segment)
    }

    /**
     * Sets the clipping behavior when the text exceeds the node's bounding rectangle.
     *
     * Generated from Godot docs: Button.set_text_overrun_behavior
     */
    fun setTextOverrunBehavior(overrunBehavior: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextOverrunBehaviorBind, segment, overrunBehavior)
    }

    /**
     * Sets the clipping behavior when the text exceeds the node's bounding rectangle.
     *
     * Generated from Godot docs: Button.get_text_overrun_behavior
     */
    fun getTextOverrunBehavior(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextOverrunBehaviorBind, segment)
    }

    /**
     * If set to something other than `TextServer.AUTOWRAP_OFF`, the text gets wrapped inside the
     * node's bounding rectangle.
     *
     * Generated from Godot docs: Button.set_autowrap_mode
     */
    fun setAutowrapMode(autowrapMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setAutowrapModeBind, segment, autowrapMode)
    }

    /**
     * If set to something other than `TextServer.AUTOWRAP_OFF`, the text gets wrapped inside the
     * node's bounding rectangle.
     *
     * Generated from Godot docs: Button.get_autowrap_mode
     */
    fun getAutowrapMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAutowrapModeBind, segment)
    }

    /**
     * Autowrap space trimming flags. See `TextServer.BREAK_TRIM_START_EDGE_SPACES` and
     * `TextServer.BREAK_TRIM_END_EDGE_SPACES` for more info.
     *
     * Generated from Godot docs: Button.set_autowrap_trim_flags
     */
    fun setAutowrapTrimFlags(autowrapTrimFlags: Long) {
        ObjectCalls.ptrcallWithLongArg(setAutowrapTrimFlagsBind, segment, autowrapTrimFlags)
    }

    /**
     * Autowrap space trimming flags. See `TextServer.BREAK_TRIM_START_EDGE_SPACES` and
     * `TextServer.BREAK_TRIM_END_EDGE_SPACES` for more info.
     *
     * Generated from Godot docs: Button.get_autowrap_trim_flags
     */
    fun getAutowrapTrimFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAutowrapTrimFlagsBind, segment)
    }

    /**
     * Base text writing direction.
     *
     * Generated from Godot docs: Button.set_text_direction
     */
    fun setTextDirection(direction: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextDirectionBind, segment, direction)
    }

    /**
     * Base text writing direction.
     *
     * Generated from Godot docs: Button.get_text_direction
     */
    fun getTextDirection(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextDirectionBind, segment)
    }

    /**
     * Language code used for line-breaking and text shaping algorithms. If left empty, the current
     * locale is used instead.
     *
     * Generated from Godot docs: Button.set_language
     */
    fun setLanguage(language: String) {
        ObjectCalls.ptrcallWithStringArg(setLanguageBind, segment, language)
    }

    /**
     * Language code used for line-breaking and text shaping algorithms. If left empty, the current
     * locale is used instead.
     *
     * Generated from Godot docs: Button.get_language
     */
    fun getLanguage(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLanguageBind, segment)
    }

    /**
     * Button's icon, if text is present the icon will be placed before the text. To edit margin and
     * spacing of the icon, use `h_separation` theme property and `content_margin_*` properties of the
     * used `StyleBox`es.
     *
     * Generated from Godot docs: Button.set_button_icon
     */
    fun setButtonIcon(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setButtonIconBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Button's icon, if text is present the icon will be placed before the text. To edit margin and
     * spacing of the icon, use `h_separation` theme property and `content_margin_*` properties of the
     * used `StyleBox`es.
     *
     * Generated from Godot docs: Button.get_button_icon
     */
    fun getButtonIcon(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getButtonIconBind, segment))
    }

    /**
     * Flat buttons don't display decoration.
     *
     * Generated from Godot docs: Button.set_flat
     */
    fun setFlat(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlatBind, segment, enabled)
    }

    /**
     * Flat buttons don't display decoration.
     *
     * Generated from Godot docs: Button.is_flat
     */
    fun isFlat(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFlatBind, segment)
    }

    /**
     * If `true`, text that is too large to fit the button is clipped horizontally. If `false`, the
     * button will always be wide enough to hold the text. The text is not vertically clipped, and the
     * button's height is not affected by this property.
     *
     * Generated from Godot docs: Button.set_clip_text
     */
    fun setClipText(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setClipTextBind, segment, enabled)
    }

    /**
     * If `true`, text that is too large to fit the button is clipped horizontally. If `false`, the
     * button will always be wide enough to hold the text. The text is not vertically clipped, and the
     * button's height is not affected by this property.
     *
     * Generated from Godot docs: Button.get_clip_text
     */
    fun getClipText(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getClipTextBind, segment)
    }

    /**
     * Text alignment policy for the button's text.
     *
     * Generated from Godot docs: Button.set_text_alignment
     */
    fun setTextAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextAlignmentBind, segment, alignment)
    }

    /**
     * Text alignment policy for the button's text.
     *
     * Generated from Godot docs: Button.get_text_alignment
     */
    fun getTextAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextAlignmentBind, segment)
    }

    /**
     * Specifies if the icon should be aligned horizontally to the left, right, or center of a button.
     * Uses the same `HorizontalAlignment` constants as the text alignment. If centered horizontally
     * and vertically, text will draw on top of the icon.
     *
     * Generated from Godot docs: Button.set_icon_alignment
     */
    fun setIconAlignment(iconAlignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setIconAlignmentBind, segment, iconAlignment)
    }

    /**
     * Specifies if the icon should be aligned horizontally to the left, right, or center of a button.
     * Uses the same `HorizontalAlignment` constants as the text alignment. If centered horizontally
     * and vertically, text will draw on top of the icon.
     *
     * Generated from Godot docs: Button.get_icon_alignment
     */
    fun getIconAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getIconAlignmentBind, segment)
    }

    /**
     * Specifies if the icon should be aligned vertically to the top, bottom, or center of a button.
     * Uses the same `VerticalAlignment` constants as the text alignment. If centered horizontally and
     * vertically, text will draw on top of the icon.
     *
     * Generated from Godot docs: Button.set_vertical_icon_alignment
     */
    fun setVerticalIconAlignment(verticalIconAlignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setVerticalIconAlignmentBind, segment, verticalIconAlignment)
    }

    /**
     * Specifies if the icon should be aligned vertically to the top, bottom, or center of a button.
     * Uses the same `VerticalAlignment` constants as the text alignment. If centered horizontally and
     * vertically, text will draw on top of the icon.
     *
     * Generated from Godot docs: Button.get_vertical_icon_alignment
     */
    fun getVerticalIconAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVerticalIconAlignmentBind, segment)
    }

    /**
     * When enabled, the button's icon will expand/shrink to fit the button's size while keeping its
     * aspect. See also `icon_max_width`.
     *
     * Generated from Godot docs: Button.set_expand_icon
     */
    fun setExpandIcon(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setExpandIconBind, segment, enabled)
    }

    /**
     * When enabled, the button's icon will expand/shrink to fit the button's size while keeping its
     * aspect. See also `icon_max_width`.
     *
     * Generated from Godot docs: Button.is_expand_icon
     */
    fun isExpandIcon(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isExpandIconBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Button? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Button? =
            if (handle.address() == 0L) null else Button(GodotHandle(handle))

        private const val SET_TEXT_HASH = 83702148L
        private val setTextBind by lazy {
            ObjectCalls.getMethodBind("Button", "set_text", SET_TEXT_HASH)
        }

        private const val GET_TEXT_HASH = 201670096L
        private val getTextBind by lazy {
            ObjectCalls.getMethodBind("Button", "get_text", GET_TEXT_HASH)
        }

        private const val SET_TEXT_OVERRUN_BEHAVIOR_HASH = 1008890932L
        private val setTextOverrunBehaviorBind by lazy {
            ObjectCalls.getMethodBind("Button", "set_text_overrun_behavior", SET_TEXT_OVERRUN_BEHAVIOR_HASH)
        }

        private const val GET_TEXT_OVERRUN_BEHAVIOR_HASH = 3779142101L
        private val getTextOverrunBehaviorBind by lazy {
            ObjectCalls.getMethodBind("Button", "get_text_overrun_behavior", GET_TEXT_OVERRUN_BEHAVIOR_HASH)
        }

        private const val SET_AUTOWRAP_MODE_HASH = 3289138044L
        private val setAutowrapModeBind by lazy {
            ObjectCalls.getMethodBind("Button", "set_autowrap_mode", SET_AUTOWRAP_MODE_HASH)
        }

        private const val GET_AUTOWRAP_MODE_HASH = 1549071663L
        private val getAutowrapModeBind by lazy {
            ObjectCalls.getMethodBind("Button", "get_autowrap_mode", GET_AUTOWRAP_MODE_HASH)
        }

        private const val SET_AUTOWRAP_TRIM_FLAGS_HASH = 2809697122L
        private val setAutowrapTrimFlagsBind by lazy {
            ObjectCalls.getMethodBind("Button", "set_autowrap_trim_flags", SET_AUTOWRAP_TRIM_FLAGS_HASH)
        }

        private const val GET_AUTOWRAP_TRIM_FLAGS_HASH = 2340632602L
        private val getAutowrapTrimFlagsBind by lazy {
            ObjectCalls.getMethodBind("Button", "get_autowrap_trim_flags", GET_AUTOWRAP_TRIM_FLAGS_HASH)
        }

        private const val SET_TEXT_DIRECTION_HASH = 119160795L
        private val setTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("Button", "set_text_direction", SET_TEXT_DIRECTION_HASH)
        }

        private const val GET_TEXT_DIRECTION_HASH = 797257663L
        private val getTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("Button", "get_text_direction", GET_TEXT_DIRECTION_HASH)
        }

        private const val SET_LANGUAGE_HASH = 83702148L
        private val setLanguageBind by lazy {
            ObjectCalls.getMethodBind("Button", "set_language", SET_LANGUAGE_HASH)
        }

        private const val GET_LANGUAGE_HASH = 201670096L
        private val getLanguageBind by lazy {
            ObjectCalls.getMethodBind("Button", "get_language", GET_LANGUAGE_HASH)
        }

        private const val SET_BUTTON_ICON_HASH = 4051416890L
        private val setButtonIconBind by lazy {
            ObjectCalls.getMethodBind("Button", "set_button_icon", SET_BUTTON_ICON_HASH)
        }

        private const val GET_BUTTON_ICON_HASH = 3635182373L
        private val getButtonIconBind by lazy {
            ObjectCalls.getMethodBind("Button", "get_button_icon", GET_BUTTON_ICON_HASH)
        }

        private const val SET_FLAT_HASH = 2586408642L
        private val setFlatBind by lazy {
            ObjectCalls.getMethodBind("Button", "set_flat", SET_FLAT_HASH)
        }

        private const val IS_FLAT_HASH = 36873697L
        private val isFlatBind by lazy {
            ObjectCalls.getMethodBind("Button", "is_flat", IS_FLAT_HASH)
        }

        private const val SET_CLIP_TEXT_HASH = 2586408642L
        private val setClipTextBind by lazy {
            ObjectCalls.getMethodBind("Button", "set_clip_text", SET_CLIP_TEXT_HASH)
        }

        private const val GET_CLIP_TEXT_HASH = 36873697L
        private val getClipTextBind by lazy {
            ObjectCalls.getMethodBind("Button", "get_clip_text", GET_CLIP_TEXT_HASH)
        }

        private const val SET_TEXT_ALIGNMENT_HASH = 2312603777L
        private val setTextAlignmentBind by lazy {
            ObjectCalls.getMethodBind("Button", "set_text_alignment", SET_TEXT_ALIGNMENT_HASH)
        }

        private const val GET_TEXT_ALIGNMENT_HASH = 341400642L
        private val getTextAlignmentBind by lazy {
            ObjectCalls.getMethodBind("Button", "get_text_alignment", GET_TEXT_ALIGNMENT_HASH)
        }

        private const val SET_ICON_ALIGNMENT_HASH = 2312603777L
        private val setIconAlignmentBind by lazy {
            ObjectCalls.getMethodBind("Button", "set_icon_alignment", SET_ICON_ALIGNMENT_HASH)
        }

        private const val GET_ICON_ALIGNMENT_HASH = 341400642L
        private val getIconAlignmentBind by lazy {
            ObjectCalls.getMethodBind("Button", "get_icon_alignment", GET_ICON_ALIGNMENT_HASH)
        }

        private const val SET_VERTICAL_ICON_ALIGNMENT_HASH = 1796458609L
        private val setVerticalIconAlignmentBind by lazy {
            ObjectCalls.getMethodBind("Button", "set_vertical_icon_alignment", SET_VERTICAL_ICON_ALIGNMENT_HASH)
        }

        private const val GET_VERTICAL_ICON_ALIGNMENT_HASH = 3274884059L
        private val getVerticalIconAlignmentBind by lazy {
            ObjectCalls.getMethodBind("Button", "get_vertical_icon_alignment", GET_VERTICAL_ICON_ALIGNMENT_HASH)
        }

        private const val SET_EXPAND_ICON_HASH = 2586408642L
        private val setExpandIconBind by lazy {
            ObjectCalls.getMethodBind("Button", "set_expand_icon", SET_EXPAND_ICON_HASH)
        }

        private const val IS_EXPAND_ICON_HASH = 36873697L
        private val isExpandIconBind by lazy {
            ObjectCalls.getMethodBind("Button", "is_expand_icon", IS_EXPAND_ICON_HASH)
        }
    }
}

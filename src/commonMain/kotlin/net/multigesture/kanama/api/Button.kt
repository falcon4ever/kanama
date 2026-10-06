package net.multigesture.kanama.api

import kotlin.jvm.JvmField
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

    var alignment: HorizontalAlignment
        @JvmName("alignmentProperty")
        get() = getTextAlignment()
        @JvmName("setAlignmentProperty")
        set(value) = setTextAlignment(value)

    var textOverrunBehavior: TextServer.OverrunBehavior
        @JvmName("textOverrunBehaviorProperty")
        get() = getTextOverrunBehavior()
        @JvmName("setTextOverrunBehaviorProperty")
        set(value) = setTextOverrunBehavior(value)

    var autowrapMode: TextServer.AutowrapMode
        @JvmName("autowrapModeProperty")
        get() = getAutowrapMode()
        @JvmName("setAutowrapModeProperty")
        set(value) = setAutowrapMode(value)

    var autowrapTrimFlags: TextServer.LineBreakFlag
        @JvmName("autowrapTrimFlagsProperty")
        get() = getAutowrapTrimFlags()
        @JvmName("setAutowrapTrimFlagsProperty")
        set(value) = setAutowrapTrimFlags(value)

    var clipText: Boolean
        @JvmName("clipTextProperty")
        get() = getClipText()
        @JvmName("setClipTextProperty")
        set(value) = setClipText(value)

    var iconAlignment: HorizontalAlignment
        @JvmName("iconAlignmentProperty")
        get() = getIconAlignment()
        @JvmName("setIconAlignmentProperty")
        set(value) = setIconAlignment(value)

    var verticalIconAlignment: VerticalAlignment
        @JvmName("verticalIconAlignmentProperty")
        get() = getVerticalIconAlignment()
        @JvmName("setVerticalIconAlignmentProperty")
        set(value) = setVerticalIconAlignment(value)

    var expandIcon: Boolean
        @JvmName("expandIconProperty")
        get() = isExpandIcon()
        @JvmName("setExpandIconProperty")
        set(value) = setExpandIcon(value)

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

    /**
     * The button's text that will be displayed inside the button's area.
     *
     * Generated from Godot docs: Button.set_text
     */
    fun setText(text: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setTextBind, segment, text)
    }

    /**
     * The button's text that will be displayed inside the button's area.
     *
     * Generated from Godot docs: Button.get_text
     */
    fun getText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getTextBind, segment)
    }

    /**
     * Sets the clipping behavior when the text exceeds the node's bounding rectangle.
     *
     * Generated from Godot docs: Button.set_text_overrun_behavior
     */
    fun setTextOverrunBehavior(overrunBehavior: TextServer.OverrunBehavior) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTextOverrunBehaviorBind, segment, overrunBehavior.value)
    }

    /**
     * Sets the clipping behavior when the text exceeds the node's bounding rectangle.
     *
     * Generated from Godot docs: Button.get_text_overrun_behavior
     */
    fun getTextOverrunBehavior(): TextServer.OverrunBehavior {
        return TextServer.OverrunBehavior(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTextOverrunBehaviorBind, segment))
    }

    /**
     * If set to something other than `TextServer.AutowrapMode.OFF`, the text gets wrapped inside the
     * node's bounding rectangle.
     *
     * Generated from Godot docs: Button.set_autowrap_mode
     */
    fun setAutowrapMode(autowrapMode: TextServer.AutowrapMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setAutowrapModeBind, segment, autowrapMode.value)
    }

    /**
     * If set to something other than `TextServer.AutowrapMode.OFF`, the text gets wrapped inside the
     * node's bounding rectangle.
     *
     * Generated from Godot docs: Button.get_autowrap_mode
     */
    fun getAutowrapMode(): TextServer.AutowrapMode {
        return TextServer.AutowrapMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAutowrapModeBind, segment))
    }

    /**
     * Autowrap space trimming flags. See `TextServer.LineBreakFlag.TRIM_START_EDGE_SPACES` and
     * `TextServer.LineBreakFlag.TRIM_END_EDGE_SPACES` for more info.
     *
     * Generated from Godot docs: Button.set_autowrap_trim_flags
     */
    fun setAutowrapTrimFlags(autowrapTrimFlags: TextServer.LineBreakFlag) {
        ObjectCalls.ptrcallWithLongArg(Binds.setAutowrapTrimFlagsBind, segment, autowrapTrimFlags.value)
    }

    /**
     * Autowrap space trimming flags. See `TextServer.LineBreakFlag.TRIM_START_EDGE_SPACES` and
     * `TextServer.LineBreakFlag.TRIM_END_EDGE_SPACES` for more info.
     *
     * Generated from Godot docs: Button.get_autowrap_trim_flags
     */
    fun getAutowrapTrimFlags(): TextServer.LineBreakFlag {
        return TextServer.LineBreakFlag(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAutowrapTrimFlagsBind, segment))
    }

    /**
     * Base text writing direction.
     *
     * Generated from Godot docs: Button.set_text_direction
     */
    fun setTextDirection(direction: Control.TextDirection) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTextDirectionBind, segment, direction.value)
    }

    /**
     * Base text writing direction.
     *
     * Generated from Godot docs: Button.get_text_direction
     */
    fun getTextDirection(): Control.TextDirection {
        return Control.TextDirection(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTextDirectionBind, segment))
    }

    /**
     * Language code used for line-breaking and text shaping algorithms. If left empty, the current
     * locale is used instead.
     *
     * Generated from Godot docs: Button.set_language
     */
    fun setLanguage(language: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setLanguageBind, segment, language)
    }

    /**
     * Language code used for line-breaking and text shaping algorithms. If left empty, the current
     * locale is used instead.
     *
     * Generated from Godot docs: Button.get_language
     */
    fun getLanguage(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getLanguageBind, segment)
    }

    /**
     * Button's icon, if text is present the icon will be placed before the text. To edit margin and
     * spacing of the icon, use `h_separation` theme property and `content_margin_*` properties of the
     * used `StyleBox`es.
     *
     * Generated from Godot docs: Button.set_button_icon
     */
    fun setButtonIcon(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setButtonIconBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Button's icon, if text is present the icon will be placed before the text. To edit margin and
     * spacing of the icon, use `h_separation` theme property and `content_margin_*` properties of the
     * used `StyleBox`es.
     *
     * Generated from Godot docs: Button.get_button_icon
     */
    fun getButtonIcon(): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getButtonIconBind, segment))
    }

    /**
     * Flat buttons don't display decoration.
     *
     * Generated from Godot docs: Button.set_flat
     */
    fun setFlat(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setFlatBind, segment, enabled)
    }

    /**
     * Flat buttons don't display decoration.
     *
     * Generated from Godot docs: Button.is_flat
     */
    fun isFlat(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isFlatBind, segment)
    }

    /**
     * If `true`, text that is too large to fit the button is clipped horizontally. If `false`, the
     * button will always be wide enough to hold the text. The text is not vertically clipped, and the
     * button's height is not affected by this property.
     *
     * Generated from Godot docs: Button.set_clip_text
     */
    fun setClipText(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setClipTextBind, segment, enabled)
    }

    /**
     * If `true`, text that is too large to fit the button is clipped horizontally. If `false`, the
     * button will always be wide enough to hold the text. The text is not vertically clipped, and the
     * button's height is not affected by this property.
     *
     * Generated from Godot docs: Button.get_clip_text
     */
    fun getClipText(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getClipTextBind, segment)
    }

    /**
     * Text alignment policy for the button's text.
     *
     * Generated from Godot docs: Button.set_text_alignment
     */
    fun setTextAlignment(alignment: HorizontalAlignment) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTextAlignmentBind, segment, alignment.value)
    }

    /**
     * Text alignment policy for the button's text.
     *
     * Generated from Godot docs: Button.get_text_alignment
     */
    fun getTextAlignment(): HorizontalAlignment {
        return HorizontalAlignment(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTextAlignmentBind, segment))
    }

    /**
     * Specifies if the icon should be aligned horizontally to the left, right, or center of a button.
     * Uses the same `HorizontalAlignment` constants as the text alignment. If centered horizontally
     * and vertically, text will draw on top of the icon.
     *
     * Generated from Godot docs: Button.set_icon_alignment
     */
    fun setIconAlignment(iconAlignment: HorizontalAlignment) {
        ObjectCalls.ptrcallWithLongArg(Binds.setIconAlignmentBind, segment, iconAlignment.value)
    }

    /**
     * Specifies if the icon should be aligned horizontally to the left, right, or center of a button.
     * Uses the same `HorizontalAlignment` constants as the text alignment. If centered horizontally
     * and vertically, text will draw on top of the icon.
     *
     * Generated from Godot docs: Button.get_icon_alignment
     */
    fun getIconAlignment(): HorizontalAlignment {
        return HorizontalAlignment(ObjectCalls.ptrcallNoArgsRetLong(Binds.getIconAlignmentBind, segment))
    }

    /**
     * Specifies if the icon should be aligned vertically to the top, bottom, or center of a button.
     * Uses the same `VerticalAlignment` constants as the text alignment. If centered horizontally and
     * vertically, text will draw on top of the icon.
     *
     * Generated from Godot docs: Button.set_vertical_icon_alignment
     */
    fun setVerticalIconAlignment(verticalIconAlignment: VerticalAlignment) {
        ObjectCalls.ptrcallWithLongArg(Binds.setVerticalIconAlignmentBind, segment, verticalIconAlignment.value)
    }

    /**
     * Specifies if the icon should be aligned vertically to the top, bottom, or center of a button.
     * Uses the same `VerticalAlignment` constants as the text alignment. If centered horizontally and
     * vertically, text will draw on top of the icon.
     *
     * Generated from Godot docs: Button.get_vertical_icon_alignment
     */
    fun getVerticalIconAlignment(): VerticalAlignment {
        return VerticalAlignment(ObjectCalls.ptrcallNoArgsRetLong(Binds.getVerticalIconAlignmentBind, segment))
    }

    /**
     * When enabled, the button's icon will expand/shrink to fit the button's size while keeping its
     * aspect. See also `icon_max_width`.
     *
     * Generated from Godot docs: Button.set_expand_icon
     */
    fun setExpandIcon(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setExpandIconBind, segment, enabled)
    }

    /**
     * When enabled, the button's icon will expand/shrink to fit the button's size while keeping its
     * aspect. See also `icon_max_width`.
     *
     * Generated from Godot docs: Button.is_expand_icon
     */
    fun isExpandIcon(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isExpandIconBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Button? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Button? =
            if (handle.address() == 0L) null else Button(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_TEXT_HASH = 83702148L
        @JvmField
        val setTextBind =
            ObjectCalls.getMethodBind("Button", "set_text", SET_TEXT_HASH)

        private const val GET_TEXT_HASH = 201670096L
        @JvmField
        val getTextBind =
            ObjectCalls.getMethodBind("Button", "get_text", GET_TEXT_HASH)

        private const val SET_TEXT_OVERRUN_BEHAVIOR_HASH = 1008890932L
        @JvmField
        val setTextOverrunBehaviorBind =
            ObjectCalls.getMethodBind("Button", "set_text_overrun_behavior", SET_TEXT_OVERRUN_BEHAVIOR_HASH)

        private const val GET_TEXT_OVERRUN_BEHAVIOR_HASH = 3779142101L
        @JvmField
        val getTextOverrunBehaviorBind =
            ObjectCalls.getMethodBind("Button", "get_text_overrun_behavior", GET_TEXT_OVERRUN_BEHAVIOR_HASH)

        private const val SET_AUTOWRAP_MODE_HASH = 3289138044L
        @JvmField
        val setAutowrapModeBind =
            ObjectCalls.getMethodBind("Button", "set_autowrap_mode", SET_AUTOWRAP_MODE_HASH)

        private const val GET_AUTOWRAP_MODE_HASH = 1549071663L
        @JvmField
        val getAutowrapModeBind =
            ObjectCalls.getMethodBind("Button", "get_autowrap_mode", GET_AUTOWRAP_MODE_HASH)

        private const val SET_AUTOWRAP_TRIM_FLAGS_HASH = 2809697122L
        @JvmField
        val setAutowrapTrimFlagsBind =
            ObjectCalls.getMethodBind("Button", "set_autowrap_trim_flags", SET_AUTOWRAP_TRIM_FLAGS_HASH)

        private const val GET_AUTOWRAP_TRIM_FLAGS_HASH = 2340632602L
        @JvmField
        val getAutowrapTrimFlagsBind =
            ObjectCalls.getMethodBind("Button", "get_autowrap_trim_flags", GET_AUTOWRAP_TRIM_FLAGS_HASH)

        private const val SET_TEXT_DIRECTION_HASH = 119160795L
        @JvmField
        val setTextDirectionBind =
            ObjectCalls.getMethodBind("Button", "set_text_direction", SET_TEXT_DIRECTION_HASH)

        private const val GET_TEXT_DIRECTION_HASH = 797257663L
        @JvmField
        val getTextDirectionBind =
            ObjectCalls.getMethodBind("Button", "get_text_direction", GET_TEXT_DIRECTION_HASH)

        private const val SET_LANGUAGE_HASH = 83702148L
        @JvmField
        val setLanguageBind =
            ObjectCalls.getMethodBind("Button", "set_language", SET_LANGUAGE_HASH)

        private const val GET_LANGUAGE_HASH = 201670096L
        @JvmField
        val getLanguageBind =
            ObjectCalls.getMethodBind("Button", "get_language", GET_LANGUAGE_HASH)

        private const val SET_BUTTON_ICON_HASH = 4051416890L
        @JvmField
        val setButtonIconBind =
            ObjectCalls.getMethodBind("Button", "set_button_icon", SET_BUTTON_ICON_HASH)

        private const val GET_BUTTON_ICON_HASH = 3635182373L
        @JvmField
        val getButtonIconBind =
            ObjectCalls.getMethodBind("Button", "get_button_icon", GET_BUTTON_ICON_HASH)

        private const val SET_FLAT_HASH = 2586408642L
        @JvmField
        val setFlatBind =
            ObjectCalls.getMethodBind("Button", "set_flat", SET_FLAT_HASH)

        private const val IS_FLAT_HASH = 36873697L
        @JvmField
        val isFlatBind =
            ObjectCalls.getMethodBind("Button", "is_flat", IS_FLAT_HASH)

        private const val SET_CLIP_TEXT_HASH = 2586408642L
        @JvmField
        val setClipTextBind =
            ObjectCalls.getMethodBind("Button", "set_clip_text", SET_CLIP_TEXT_HASH)

        private const val GET_CLIP_TEXT_HASH = 36873697L
        @JvmField
        val getClipTextBind =
            ObjectCalls.getMethodBind("Button", "get_clip_text", GET_CLIP_TEXT_HASH)

        private const val SET_TEXT_ALIGNMENT_HASH = 2312603777L
        @JvmField
        val setTextAlignmentBind =
            ObjectCalls.getMethodBind("Button", "set_text_alignment", SET_TEXT_ALIGNMENT_HASH)

        private const val GET_TEXT_ALIGNMENT_HASH = 341400642L
        @JvmField
        val getTextAlignmentBind =
            ObjectCalls.getMethodBind("Button", "get_text_alignment", GET_TEXT_ALIGNMENT_HASH)

        private const val SET_ICON_ALIGNMENT_HASH = 2312603777L
        @JvmField
        val setIconAlignmentBind =
            ObjectCalls.getMethodBind("Button", "set_icon_alignment", SET_ICON_ALIGNMENT_HASH)

        private const val GET_ICON_ALIGNMENT_HASH = 341400642L
        @JvmField
        val getIconAlignmentBind =
            ObjectCalls.getMethodBind("Button", "get_icon_alignment", GET_ICON_ALIGNMENT_HASH)

        private const val SET_VERTICAL_ICON_ALIGNMENT_HASH = 1796458609L
        @JvmField
        val setVerticalIconAlignmentBind =
            ObjectCalls.getMethodBind("Button", "set_vertical_icon_alignment", SET_VERTICAL_ICON_ALIGNMENT_HASH)

        private const val GET_VERTICAL_ICON_ALIGNMENT_HASH = 3274884059L
        @JvmField
        val getVerticalIconAlignmentBind =
            ObjectCalls.getMethodBind("Button", "get_vertical_icon_alignment", GET_VERTICAL_ICON_ALIGNMENT_HASH)

        private const val SET_EXPAND_ICON_HASH = 2586408642L
        @JvmField
        val setExpandIconBind =
            ObjectCalls.getMethodBind("Button", "set_expand_icon", SET_EXPAND_ICON_HASH)

        private const val IS_EXPAND_ICON_HASH = 36873697L
        @JvmField
        val isExpandIconBind =
            ObjectCalls.getMethodBind("Button", "is_expand_icon", IS_EXPAND_ICON_HASH)
    }
}

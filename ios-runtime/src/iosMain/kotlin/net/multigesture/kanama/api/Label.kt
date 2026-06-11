package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: Label
 */
class Label(handle: MemorySegment) : Control(handle) {
    var horizontalAlignment: Long
        @JvmName("horizontalAlignmentProperty")
        get() = getHorizontalAlignment()
        @JvmName("setHorizontalAlignmentProperty")
        set(value) = setHorizontalAlignment(value)

    var verticalAlignment: Long
        @JvmName("verticalAlignmentProperty")
        get() = getVerticalAlignment()
        @JvmName("setVerticalAlignmentProperty")
        set(value) = setVerticalAlignment(value)

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

    var justificationFlags: Long
        @JvmName("justificationFlagsProperty")
        get() = getJustificationFlags()
        @JvmName("setJustificationFlagsProperty")
        set(value) = setJustificationFlags(value)

    var clipText: Boolean
        @JvmName("clipTextProperty")
        get() = isClippingText()
        @JvmName("setClipTextProperty")
        set(value) = setClipText(value)

    var textOverrunBehavior: Long
        @JvmName("textOverrunBehaviorProperty")
        get() = getTextOverrunBehavior()
        @JvmName("setTextOverrunBehaviorProperty")
        set(value) = setTextOverrunBehavior(value)

    var uppercase: Boolean
        @JvmName("uppercaseProperty")
        get() = isUppercase()
        @JvmName("setUppercaseProperty")
        set(value) = setUppercase(value)

    var linesSkipped: Int
        @JvmName("linesSkippedProperty")
        get() = getLinesSkipped()
        @JvmName("setLinesSkippedProperty")
        set(value) = setLinesSkipped(value)

    var maxLinesVisible: Int
        @JvmName("maxLinesVisibleProperty")
        get() = getMaxLinesVisible()
        @JvmName("setMaxLinesVisibleProperty")
        set(value) = setMaxLinesVisible(value)

    var visibleCharacters: Int
        @JvmName("visibleCharactersProperty")
        get() = getVisibleCharacters()
        @JvmName("setVisibleCharactersProperty")
        set(value) = setVisibleCharacters(value)

    var visibleCharactersBehavior: Long
        @JvmName("visibleCharactersBehaviorProperty")
        get() = getVisibleCharactersBehavior()
        @JvmName("setVisibleCharactersBehaviorProperty")
        set(value) = setVisibleCharactersBehavior(value)

    var visibleRatio: Double
        @JvmName("visibleRatioProperty")
        get() = getVisibleRatio()
        @JvmName("setVisibleRatioProperty")
        set(value) = setVisibleRatio(value)

    var textDirection: Long
        @JvmName("textDirectionProperty")
        get() = getTextDirection()
        @JvmName("setTextDirectionProperty")
        set(value) = setTextDirection(value)

    var structuredTextBidiOverride: Long
        @JvmName("structuredTextBidiOverrideProperty")
        get() = getStructuredTextBidiOverride()
        @JvmName("setStructuredTextBidiOverrideProperty")
        set(value) = setStructuredTextBidiOverride(value)

    fun setHorizontalAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setHorizontalAlignmentBind, handle, alignment)
    }

    fun getHorizontalAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getHorizontalAlignmentBind, handle)
    }

    fun setVerticalAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setVerticalAlignmentBind, handle, alignment)
    }

    fun getVerticalAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVerticalAlignmentBind, handle)
    }

    fun setTextDirection(direction: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextDirectionBind, handle, direction)
    }

    fun getTextDirection(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextDirectionBind, handle)
    }

    fun setAutowrapMode(autowrapMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setAutowrapModeBind, handle, autowrapMode)
    }

    fun getAutowrapMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAutowrapModeBind, handle)
    }

    fun setAutowrapTrimFlags(autowrapTrimFlags: Long) {
        ObjectCalls.ptrcallWithLongArg(setAutowrapTrimFlagsBind, handle, autowrapTrimFlags)
    }

    fun getAutowrapTrimFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAutowrapTrimFlagsBind, handle)
    }

    fun setJustificationFlags(justificationFlags: Long) {
        ObjectCalls.ptrcallWithLongArg(setJustificationFlagsBind, handle, justificationFlags)
    }

    fun getJustificationFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getJustificationFlagsBind, handle)
    }

    fun setClipText(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setClipTextBind, handle, enable)
    }

    fun isClippingText(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isClippingTextBind, handle)
    }

    fun setTextOverrunBehavior(overrunBehavior: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextOverrunBehaviorBind, handle, overrunBehavior)
    }

    fun getTextOverrunBehavior(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextOverrunBehaviorBind, handle)
    }

    fun setUppercase(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUppercaseBind, handle, enable)
    }

    fun isUppercase(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUppercaseBind, handle)
    }

    fun getLineHeight(line: Int = -1): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getLineHeightBind, handle, line)
    }

    fun getLineCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLineCountBind, handle)
    }

    fun getVisibleLineCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getVisibleLineCountBind, handle)
    }

    fun getTotalCharacterCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTotalCharacterCountBind, handle)
    }

    fun setVisibleCharacters(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setVisibleCharactersBind, handle, amount)
    }

    fun getVisibleCharacters(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getVisibleCharactersBind, handle)
    }

    fun getVisibleCharactersBehavior(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVisibleCharactersBehaviorBind, handle)
    }

    fun setVisibleCharactersBehavior(behavior: Long) {
        ObjectCalls.ptrcallWithLongArg(setVisibleCharactersBehaviorBind, handle, behavior)
    }

    fun setVisibleRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVisibleRatioBind, handle, ratio)
    }

    fun getVisibleRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVisibleRatioBind, handle)
    }

    fun setLinesSkipped(linesSkipped: Int) {
        ObjectCalls.ptrcallWithIntArg(setLinesSkippedBind, handle, linesSkipped)
    }

    fun getLinesSkipped(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLinesSkippedBind, handle)
    }

    fun setMaxLinesVisible(linesVisible: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxLinesVisibleBind, handle, linesVisible)
    }

    fun getMaxLinesVisible(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxLinesVisibleBind, handle)
    }

    fun setStructuredTextBidiOverride(parser: Long) {
        ObjectCalls.ptrcallWithLongArg(setStructuredTextBidiOverrideBind, handle, parser)
    }

    fun getStructuredTextBidiOverride(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStructuredTextBidiOverrideBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): Label? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Label? =
            if (handle.address() == 0L) null else Label(handle)

        private const val SET_HORIZONTAL_ALIGNMENT_HASH = 2312603777L
        private val setHorizontalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_horizontal_alignment", SET_HORIZONTAL_ALIGNMENT_HASH)
        }

        private const val GET_HORIZONTAL_ALIGNMENT_HASH = 341400642L
        private val getHorizontalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_horizontal_alignment", GET_HORIZONTAL_ALIGNMENT_HASH)
        }

        private const val SET_VERTICAL_ALIGNMENT_HASH = 1796458609L
        private val setVerticalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_vertical_alignment", SET_VERTICAL_ALIGNMENT_HASH)
        }

        private const val GET_VERTICAL_ALIGNMENT_HASH = 3274884059L
        private val getVerticalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_vertical_alignment", GET_VERTICAL_ALIGNMENT_HASH)
        }

        private const val SET_TEXT_DIRECTION_HASH = 119160795L
        private val setTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_text_direction", SET_TEXT_DIRECTION_HASH)
        }

        private const val GET_TEXT_DIRECTION_HASH = 797257663L
        private val getTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_text_direction", GET_TEXT_DIRECTION_HASH)
        }

        private const val SET_AUTOWRAP_MODE_HASH = 3289138044L
        private val setAutowrapModeBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_autowrap_mode", SET_AUTOWRAP_MODE_HASH)
        }

        private const val GET_AUTOWRAP_MODE_HASH = 1549071663L
        private val getAutowrapModeBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_autowrap_mode", GET_AUTOWRAP_MODE_HASH)
        }

        private const val SET_AUTOWRAP_TRIM_FLAGS_HASH = 2809697122L
        private val setAutowrapTrimFlagsBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_autowrap_trim_flags", SET_AUTOWRAP_TRIM_FLAGS_HASH)
        }

        private const val GET_AUTOWRAP_TRIM_FLAGS_HASH = 2340632602L
        private val getAutowrapTrimFlagsBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_autowrap_trim_flags", GET_AUTOWRAP_TRIM_FLAGS_HASH)
        }

        private const val SET_JUSTIFICATION_FLAGS_HASH = 2877345813L
        private val setJustificationFlagsBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_justification_flags", SET_JUSTIFICATION_FLAGS_HASH)
        }

        private const val GET_JUSTIFICATION_FLAGS_HASH = 1583363614L
        private val getJustificationFlagsBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_justification_flags", GET_JUSTIFICATION_FLAGS_HASH)
        }

        private const val SET_CLIP_TEXT_HASH = 2586408642L
        private val setClipTextBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_clip_text", SET_CLIP_TEXT_HASH)
        }

        private const val IS_CLIPPING_TEXT_HASH = 36873697L
        private val isClippingTextBind by lazy {
            ObjectCalls.getMethodBind("Label", "is_clipping_text", IS_CLIPPING_TEXT_HASH)
        }

        private const val SET_TEXT_OVERRUN_BEHAVIOR_HASH = 1008890932L
        private val setTextOverrunBehaviorBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_text_overrun_behavior", SET_TEXT_OVERRUN_BEHAVIOR_HASH)
        }

        private const val GET_TEXT_OVERRUN_BEHAVIOR_HASH = 3779142101L
        private val getTextOverrunBehaviorBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_text_overrun_behavior", GET_TEXT_OVERRUN_BEHAVIOR_HASH)
        }

        private const val SET_UPPERCASE_HASH = 2586408642L
        private val setUppercaseBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_uppercase", SET_UPPERCASE_HASH)
        }

        private const val IS_UPPERCASE_HASH = 36873697L
        private val isUppercaseBind by lazy {
            ObjectCalls.getMethodBind("Label", "is_uppercase", IS_UPPERCASE_HASH)
        }

        private const val GET_LINE_HEIGHT_HASH = 181039630L
        private val getLineHeightBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_line_height", GET_LINE_HEIGHT_HASH)
        }

        private const val GET_LINE_COUNT_HASH = 3905245786L
        private val getLineCountBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_line_count", GET_LINE_COUNT_HASH)
        }

        private const val GET_VISIBLE_LINE_COUNT_HASH = 3905245786L
        private val getVisibleLineCountBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_visible_line_count", GET_VISIBLE_LINE_COUNT_HASH)
        }

        private const val GET_TOTAL_CHARACTER_COUNT_HASH = 3905245786L
        private val getTotalCharacterCountBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_total_character_count", GET_TOTAL_CHARACTER_COUNT_HASH)
        }

        private const val SET_VISIBLE_CHARACTERS_HASH = 1286410249L
        private val setVisibleCharactersBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_visible_characters", SET_VISIBLE_CHARACTERS_HASH)
        }

        private const val GET_VISIBLE_CHARACTERS_HASH = 3905245786L
        private val getVisibleCharactersBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_visible_characters", GET_VISIBLE_CHARACTERS_HASH)
        }

        private const val GET_VISIBLE_CHARACTERS_BEHAVIOR_HASH = 258789322L
        private val getVisibleCharactersBehaviorBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_visible_characters_behavior", GET_VISIBLE_CHARACTERS_BEHAVIOR_HASH)
        }

        private const val SET_VISIBLE_CHARACTERS_BEHAVIOR_HASH = 3383839701L
        private val setVisibleCharactersBehaviorBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_visible_characters_behavior", SET_VISIBLE_CHARACTERS_BEHAVIOR_HASH)
        }

        private const val SET_VISIBLE_RATIO_HASH = 373806689L
        private val setVisibleRatioBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_visible_ratio", SET_VISIBLE_RATIO_HASH)
        }

        private const val GET_VISIBLE_RATIO_HASH = 1740695150L
        private val getVisibleRatioBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_visible_ratio", GET_VISIBLE_RATIO_HASH)
        }

        private const val SET_LINES_SKIPPED_HASH = 1286410249L
        private val setLinesSkippedBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_lines_skipped", SET_LINES_SKIPPED_HASH)
        }

        private const val GET_LINES_SKIPPED_HASH = 3905245786L
        private val getLinesSkippedBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_lines_skipped", GET_LINES_SKIPPED_HASH)
        }

        private const val SET_MAX_LINES_VISIBLE_HASH = 1286410249L
        private val setMaxLinesVisibleBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_max_lines_visible", SET_MAX_LINES_VISIBLE_HASH)
        }

        private const val GET_MAX_LINES_VISIBLE_HASH = 3905245786L
        private val getMaxLinesVisibleBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_max_lines_visible", GET_MAX_LINES_VISIBLE_HASH)
        }

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 55961453L
        private val setStructuredTextBidiOverrideBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_structured_text_bidi_override", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)
        }

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 3385126229L
        private val getStructuredTextBidiOverrideBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_structured_text_bidi_override", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)
        }
    }

    // KANAMA-IOS-SUGAR: hand-added to a generated file; re-add after regeneration.
    // ── Kanama sugar (hand) - preserve on regenerate ──────────────────────────

    var text: String
        // KANAMA-IOS-STUB: get_text not read (String-return ptrcall not wired); set is real. Backlog.
        get() = ""
        set(value) { IosGodot.setObjectText(handle.address(), value) }

    fun setText(value: String) { text = value }
}

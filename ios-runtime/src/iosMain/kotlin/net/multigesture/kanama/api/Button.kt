package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: Button
 */
open class Button(handle: MemorySegment) : BaseButton(handle) {
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

    fun setButtonIcon(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setButtonIconBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getButtonIcon(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getButtonIconBind, handle))
    }

    fun setFlat(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlatBind, handle, enabled)
    }

    fun isFlat(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFlatBind, handle)
    }

    fun setClipText(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setClipTextBind, handle, enabled)
    }

    fun getClipText(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getClipTextBind, handle)
    }

    fun setTextAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextAlignmentBind, handle, alignment)
    }

    fun getTextAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextAlignmentBind, handle)
    }

    fun setIconAlignment(iconAlignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setIconAlignmentBind, handle, iconAlignment)
    }

    fun getIconAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getIconAlignmentBind, handle)
    }

    fun setVerticalIconAlignment(verticalIconAlignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setVerticalIconAlignmentBind, handle, verticalIconAlignment)
    }

    fun getVerticalIconAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVerticalIconAlignmentBind, handle)
    }

    fun setExpandIcon(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setExpandIconBind, handle, enabled)
    }

    fun isExpandIcon(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isExpandIconBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Button? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Button? =
            if (handle.address() == 0L) null else Button(handle)

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

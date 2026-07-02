package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A control used for visual representation of a percentage.
 *
 * Generated from Godot docs: ProgressBar
 */
class ProgressBar(handle: MemorySegment) : Range(handle) {
    var fillMode: Int
        @JvmName("fillModeProperty")
        get() = getFillMode()
        @JvmName("setFillModeProperty")
        set(value) = setFillMode(value)

    var showPercentage: Boolean
        @JvmName("showPercentageProperty")
        get() = isPercentageShown()
        @JvmName("setShowPercentageProperty")
        set(value) = setShowPercentage(value)

    var indeterminate: Boolean
        @JvmName("indeterminateProperty")
        get() = isIndeterminate()
        @JvmName("setIndeterminateProperty")
        set(value) = setIndeterminate(value)

    var editorPreviewIndeterminate: Boolean
        @JvmName("editorPreviewIndeterminateProperty")
        get() = isEditorPreviewIndeterminateEnabled()
        @JvmName("setEditorPreviewIndeterminateProperty")
        set(value) = setEditorPreviewIndeterminate(value)

    fun setFillMode(mode: Int) {
        ObjectCalls.ptrcallWithIntArg(setFillModeBind, handle, mode)
    }

    fun getFillMode(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFillModeBind, handle)
    }

    fun setShowPercentage(visible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShowPercentageBind, handle, visible)
    }

    fun isPercentageShown(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPercentageShownBind, handle)
    }

    fun setIndeterminate(indeterminate: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIndeterminateBind, handle, indeterminate)
    }

    fun isIndeterminate(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isIndeterminateBind, handle)
    }

    fun setEditorPreviewIndeterminate(previewIndeterminate: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEditorPreviewIndeterminateBind, handle, previewIndeterminate)
    }

    fun isEditorPreviewIndeterminateEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEditorPreviewIndeterminateEnabledBind, handle)
    }

    companion object {
        const val FILL_BEGIN_TO_END: Long = 0L
        const val FILL_END_TO_BEGIN: Long = 1L
        const val FILL_TOP_TO_BOTTOM: Long = 2L
        const val FILL_BOTTOM_TO_TOP: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): ProgressBar? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ProgressBar? =
            if (handle.address() == 0L) null else ProgressBar(handle)

        private const val SET_FILL_MODE_HASH = 1286410249L
        private val setFillModeBind by lazy {
            ObjectCalls.getMethodBind("ProgressBar", "set_fill_mode", SET_FILL_MODE_HASH)
        }

        private const val GET_FILL_MODE_HASH = 2455072627L
        private val getFillModeBind by lazy {
            ObjectCalls.getMethodBind("ProgressBar", "get_fill_mode", GET_FILL_MODE_HASH)
        }

        private const val SET_SHOW_PERCENTAGE_HASH = 2586408642L
        private val setShowPercentageBind by lazy {
            ObjectCalls.getMethodBind("ProgressBar", "set_show_percentage", SET_SHOW_PERCENTAGE_HASH)
        }

        private const val IS_PERCENTAGE_SHOWN_HASH = 36873697L
        private val isPercentageShownBind by lazy {
            ObjectCalls.getMethodBind("ProgressBar", "is_percentage_shown", IS_PERCENTAGE_SHOWN_HASH)
        }

        private const val SET_INDETERMINATE_HASH = 2586408642L
        private val setIndeterminateBind by lazy {
            ObjectCalls.getMethodBind("ProgressBar", "set_indeterminate", SET_INDETERMINATE_HASH)
        }

        private const val IS_INDETERMINATE_HASH = 36873697L
        private val isIndeterminateBind by lazy {
            ObjectCalls.getMethodBind("ProgressBar", "is_indeterminate", IS_INDETERMINATE_HASH)
        }

        private const val SET_EDITOR_PREVIEW_INDETERMINATE_HASH = 2586408642L
        private val setEditorPreviewIndeterminateBind by lazy {
            ObjectCalls.getMethodBind("ProgressBar", "set_editor_preview_indeterminate", SET_EDITOR_PREVIEW_INDETERMINATE_HASH)
        }

        private const val IS_EDITOR_PREVIEW_INDETERMINATE_ENABLED_HASH = 36873697L
        private val isEditorPreviewIndeterminateEnabledBind by lazy {
            ObjectCalls.getMethodBind("ProgressBar", "is_editor_preview_indeterminate_enabled", IS_EDITOR_PREVIEW_INDETERMINATE_ENABLED_HASH)
        }
    }
}

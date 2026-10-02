package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A control used for visual representation of a percentage.
 *
 * Generated from Godot docs: ProgressBar
 */
class ProgressBar(handle: GodotHandle) : Range(handle) {
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

    /**
     * The fill direction. See `FillMode` for possible values.
     *
     * Generated from Godot docs: ProgressBar.set_fill_mode
     */
    fun setFillMode(mode: Int) {
        ObjectCalls.ptrcallWithIntArg(setFillModeBind, segment, mode)
    }

    /**
     * The fill direction. See `FillMode` for possible values.
     *
     * Generated from Godot docs: ProgressBar.get_fill_mode
     */
    fun getFillMode(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFillModeBind, segment)
    }

    /**
     * If `true`, the fill percentage is displayed on the bar.
     *
     * Generated from Godot docs: ProgressBar.set_show_percentage
     */
    fun setShowPercentage(visible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShowPercentageBind, segment, visible)
    }

    /**
     * If `true`, the fill percentage is displayed on the bar.
     *
     * Generated from Godot docs: ProgressBar.is_percentage_shown
     */
    fun isPercentageShown(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPercentageShownBind, segment)
    }

    /**
     * When set to `true`, the progress bar indicates that something is happening with an animation,
     * but does not show the fill percentage or value.
     *
     * Generated from Godot docs: ProgressBar.set_indeterminate
     */
    fun setIndeterminate(indeterminate: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIndeterminateBind, segment, indeterminate)
    }

    /**
     * When set to `true`, the progress bar indicates that something is happening with an animation,
     * but does not show the fill percentage or value.
     *
     * Generated from Godot docs: ProgressBar.is_indeterminate
     */
    fun isIndeterminate(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isIndeterminateBind, segment)
    }

    /**
     * If `false`, the `indeterminate` animation will be paused in the editor.
     *
     * Generated from Godot docs: ProgressBar.set_editor_preview_indeterminate
     */
    fun setEditorPreviewIndeterminate(previewIndeterminate: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEditorPreviewIndeterminateBind, segment, previewIndeterminate)
    }

    /**
     * If `false`, the `indeterminate` animation will be paused in the editor.
     *
     * Generated from Godot docs: ProgressBar.is_editor_preview_indeterminate_enabled
     */
    fun isEditorPreviewIndeterminateEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEditorPreviewIndeterminateEnabledBind, segment)
    }

    /**
     * Godot's `ProgressBar.FillMode` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`ProgressBar.FillMode.<NAME>`).
     *
     * Generated from Godot docs: ProgressBar.FillMode
     */
    @JvmInline
    value class FillMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The progress bar fills from begin to end horizontally, according to the language direction. If
             * `Control.is_layout_rtl` returns `false`, it fills from left to right, and if it returns `true`,
             * it fills from right to left.
             *
             * Generated from Godot docs: ProgressBar.FILL_BEGIN_TO_END
             */
            val BEGIN_TO_END: FillMode get() = FillMode(0L)
            /**
             * The progress bar fills from end to begin horizontally, according to the language direction. If
             * `Control.is_layout_rtl` returns `false`, it fills from right to left, and if it returns `true`,
             * it fills from left to right.
             *
             * Generated from Godot docs: ProgressBar.FILL_END_TO_BEGIN
             */
            val END_TO_BEGIN: FillMode get() = FillMode(1L)
            /**
             * The progress fills from top to bottom.
             *
             * Generated from Godot docs: ProgressBar.FILL_TOP_TO_BOTTOM
             */
            val TOP_TO_BOTTOM: FillMode get() = FillMode(2L)
            /**
             * The progress fills from bottom to top.
             *
             * Generated from Godot docs: ProgressBar.FILL_BOTTOM_TO_TOP
             */
            val BOTTOM_TO_TOP: FillMode get() = FillMode(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ProgressBar? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ProgressBar? =
            if (handle.address() == 0L) null else ProgressBar(GodotHandle(handle))

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

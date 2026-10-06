package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A container that can be expanded/collapsed.
 *
 * Generated from Godot docs: FoldableContainer
 */
class FoldableContainer(handle: GodotHandle) : Container(handle) {
    var folded: Boolean
        @JvmName("foldedProperty")
        get() = isFolded()
        @JvmName("setFoldedProperty")
        set(value) = setFolded(value)

    var title: String
        @JvmName("titleProperty")
        get() = getTitle()
        @JvmName("setTitleProperty")
        set(value) = setTitle(value)

    var titleAlignment: HorizontalAlignment
        @JvmName("titleAlignmentProperty")
        get() = getTitleAlignment()
        @JvmName("setTitleAlignmentProperty")
        set(value) = setTitleAlignment(value)

    var titlePosition: FoldableContainer.TitlePosition
        @JvmName("titlePositionProperty")
        get() = getTitlePosition()
        @JvmName("setTitlePositionProperty")
        set(value) = setTitlePosition(value)

    var titleTextOverrunBehavior: TextServer.OverrunBehavior
        @JvmName("titleTextOverrunBehaviorProperty")
        get() = getTitleTextOverrunBehavior()
        @JvmName("setTitleTextOverrunBehaviorProperty")
        set(value) = setTitleTextOverrunBehavior(value)

    var foldableGroup: FoldableGroup?
        @JvmName("foldableGroupProperty")
        get() = getFoldableGroup()
        @JvmName("setFoldableGroupProperty")
        set(value) = setFoldableGroup(value)

    var titleTextDirection: Control.TextDirection
        @JvmName("titleTextDirectionProperty")
        get() = getTitleTextDirection()
        @JvmName("setTitleTextDirectionProperty")
        set(value) = setTitleTextDirection(value)

    var language: String
        @JvmName("languageProperty")
        get() = getLanguage()
        @JvmName("setLanguageProperty")
        set(value) = setLanguage(value)

    /**
     * Folds the container and emits `folding_changed`.
     *
     * Generated from Godot docs: FoldableContainer.fold
     */
    fun fold() {
        ObjectCalls.ptrcallNoArgs(Binds.foldBind, segment)
    }

    /**
     * Expands the container and emits `folding_changed`.
     *
     * Generated from Godot docs: FoldableContainer.expand
     */
    fun expand() {
        ObjectCalls.ptrcallNoArgs(Binds.expandBind, segment)
    }

    /**
     * If `true`, the container will become folded and will hide all its children.
     *
     * Generated from Godot docs: FoldableContainer.set_folded
     */
    fun setFolded(folded: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setFoldedBind, segment, folded)
    }

    /**
     * If `true`, the container will become folded and will hide all its children.
     *
     * Generated from Godot docs: FoldableContainer.is_folded
     */
    fun isFolded(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isFoldedBind, segment)
    }

    /**
     * The `FoldableGroup` associated with the container. When multiple `FoldableContainer` nodes share
     * the same group, only one of them is allowed to be unfolded.
     *
     * Generated from Godot docs: FoldableContainer.set_foldable_group
     */
    fun setFoldableGroup(buttonGroup: FoldableGroup?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setFoldableGroupBind, segment, listOf(buttonGroup?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `FoldableGroup` associated with the container. When multiple `FoldableContainer` nodes share
     * the same group, only one of them is allowed to be unfolded.
     *
     * Generated from Godot docs: FoldableContainer.get_foldable_group
     */
    fun getFoldableGroup(): FoldableGroup? {
        return FoldableGroup.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getFoldableGroupBind, segment))
    }

    /**
     * The container's title text.
     *
     * Generated from Godot docs: FoldableContainer.set_title
     */
    fun setTitle(text: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setTitleBind, segment, text)
    }

    /**
     * The container's title text.
     *
     * Generated from Godot docs: FoldableContainer.get_title
     */
    fun getTitle(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getTitleBind, segment)
    }

    /**
     * Title's horizontal text alignment.
     *
     * Generated from Godot docs: FoldableContainer.set_title_alignment
     */
    fun setTitleAlignment(alignment: HorizontalAlignment) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTitleAlignmentBind, segment, alignment.value)
    }

    /**
     * Title's horizontal text alignment.
     *
     * Generated from Godot docs: FoldableContainer.get_title_alignment
     */
    fun getTitleAlignment(): HorizontalAlignment {
        return HorizontalAlignment(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTitleAlignmentBind, segment))
    }

    /**
     * Language code used for text shaping algorithms. If left empty, the current locale is used
     * instead.
     *
     * Generated from Godot docs: FoldableContainer.set_language
     */
    fun setLanguage(language: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setLanguageBind, segment, language)
    }

    /**
     * Language code used for text shaping algorithms. If left empty, the current locale is used
     * instead.
     *
     * Generated from Godot docs: FoldableContainer.get_language
     */
    fun getLanguage(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getLanguageBind, segment)
    }

    /**
     * Title text writing direction.
     *
     * Generated from Godot docs: FoldableContainer.set_title_text_direction
     */
    fun setTitleTextDirection(textDirection: Control.TextDirection) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTitleTextDirectionBind, segment, textDirection.value)
    }

    /**
     * Title text writing direction.
     *
     * Generated from Godot docs: FoldableContainer.get_title_text_direction
     */
    fun getTitleTextDirection(): Control.TextDirection {
        return Control.TextDirection(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTitleTextDirectionBind, segment))
    }

    /**
     * Defines the behavior of the title when the text is longer than the available space.
     *
     * Generated from Godot docs: FoldableContainer.set_title_text_overrun_behavior
     */
    fun setTitleTextOverrunBehavior(overrunBehavior: TextServer.OverrunBehavior) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTitleTextOverrunBehaviorBind, segment, overrunBehavior.value)
    }

    /**
     * Defines the behavior of the title when the text is longer than the available space.
     *
     * Generated from Godot docs: FoldableContainer.get_title_text_overrun_behavior
     */
    fun getTitleTextOverrunBehavior(): TextServer.OverrunBehavior {
        return TextServer.OverrunBehavior(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTitleTextOverrunBehaviorBind, segment))
    }

    /**
     * Title's position.
     *
     * Generated from Godot docs: FoldableContainer.set_title_position
     */
    fun setTitlePosition(titlePosition: FoldableContainer.TitlePosition) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTitlePositionBind, segment, titlePosition.value)
    }

    /**
     * Title's position.
     *
     * Generated from Godot docs: FoldableContainer.get_title_position
     */
    fun getTitlePosition(): FoldableContainer.TitlePosition {
        return FoldableContainer.TitlePosition(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTitlePositionBind, segment))
    }

    /**
     * Adds a `Control` that will be placed next to the container's title, obscuring the clickable
     * area. Prime usage is adding `Button` nodes, but it can be any `Control`. The control will be
     * added as a child of this container and removed from previous parent if necessary. The controls
     * will be placed aligned to the right, with the first added control being the leftmost one.
     *
     * Generated from Godot docs: FoldableContainer.add_title_bar_control
     */
    fun addTitleBarControl(control: Control) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.addTitleBarControlBind, segment, listOf(control.segment))
    }

    /**
     * Removes a `Control` added with `add_title_bar_control`. The node is not freed automatically, you
     * need to use `Node.queue_free`.
     *
     * Generated from Godot docs: FoldableContainer.remove_title_bar_control
     */
    fun removeTitleBarControl(control: Control) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeTitleBarControlBind, segment, listOf(control.segment))
    }

    /** Signal `folding_changed(is_folded: bool)`; see [TypedSignal]. */
    val foldingChanged: Signal1<Boolean>
        @JvmName("foldingChangedTypedSignal")
        get() = Signal1(this, "folding_changed", SignalArgType.BOOLEAN)

    object Signals {
        const val foldingChanged: String = "folding_changed"
    }

    /**
     * Godot's `FoldableContainer.TitlePosition` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`FoldableContainer.TitlePosition.<NAME>`).
     *
     * Generated from Godot docs: FoldableContainer.TitlePosition
     */
    @JvmInline
    value class TitlePosition(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Makes the title appear at the top of the container.
             *
             * Generated from Godot docs: FoldableContainer.POSITION_TOP
             */
            val TOP: TitlePosition get() = TitlePosition(0L)
            /**
             * Makes the title appear at the bottom of the container. Also makes all StyleBoxes flipped
             * vertically.
             *
             * Generated from Godot docs: FoldableContainer.POSITION_BOTTOM
             */
            val BOTTOM: TitlePosition get() = TitlePosition(1L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): FoldableContainer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): FoldableContainer? =
            if (handle.address() == 0L) null else FoldableContainer(GodotHandle(handle))
    }

    private object Binds {
        private const val FOLD_HASH = 3218959716L
        @JvmField
        val foldBind =
            ObjectCalls.getMethodBind("FoldableContainer", "fold", FOLD_HASH)

        private const val EXPAND_HASH = 3218959716L
        @JvmField
        val expandBind =
            ObjectCalls.getMethodBind("FoldableContainer", "expand", EXPAND_HASH)

        private const val SET_FOLDED_HASH = 2586408642L
        @JvmField
        val setFoldedBind =
            ObjectCalls.getMethodBind("FoldableContainer", "set_folded", SET_FOLDED_HASH)

        private const val IS_FOLDED_HASH = 36873697L
        @JvmField
        val isFoldedBind =
            ObjectCalls.getMethodBind("FoldableContainer", "is_folded", IS_FOLDED_HASH)

        private const val SET_FOLDABLE_GROUP_HASH = 3001390597L
        @JvmField
        val setFoldableGroupBind =
            ObjectCalls.getMethodBind("FoldableContainer", "set_foldable_group", SET_FOLDABLE_GROUP_HASH)

        private const val GET_FOLDABLE_GROUP_HASH = 66499518L
        @JvmField
        val getFoldableGroupBind =
            ObjectCalls.getMethodBind("FoldableContainer", "get_foldable_group", GET_FOLDABLE_GROUP_HASH)

        private const val SET_TITLE_HASH = 83702148L
        @JvmField
        val setTitleBind =
            ObjectCalls.getMethodBind("FoldableContainer", "set_title", SET_TITLE_HASH)

        private const val GET_TITLE_HASH = 201670096L
        @JvmField
        val getTitleBind =
            ObjectCalls.getMethodBind("FoldableContainer", "get_title", GET_TITLE_HASH)

        private const val SET_TITLE_ALIGNMENT_HASH = 2312603777L
        @JvmField
        val setTitleAlignmentBind =
            ObjectCalls.getMethodBind("FoldableContainer", "set_title_alignment", SET_TITLE_ALIGNMENT_HASH)

        private const val GET_TITLE_ALIGNMENT_HASH = 341400642L
        @JvmField
        val getTitleAlignmentBind =
            ObjectCalls.getMethodBind("FoldableContainer", "get_title_alignment", GET_TITLE_ALIGNMENT_HASH)

        private const val SET_LANGUAGE_HASH = 83702148L
        @JvmField
        val setLanguageBind =
            ObjectCalls.getMethodBind("FoldableContainer", "set_language", SET_LANGUAGE_HASH)

        private const val GET_LANGUAGE_HASH = 201670096L
        @JvmField
        val getLanguageBind =
            ObjectCalls.getMethodBind("FoldableContainer", "get_language", GET_LANGUAGE_HASH)

        private const val SET_TITLE_TEXT_DIRECTION_HASH = 119160795L
        @JvmField
        val setTitleTextDirectionBind =
            ObjectCalls.getMethodBind("FoldableContainer", "set_title_text_direction", SET_TITLE_TEXT_DIRECTION_HASH)

        private const val GET_TITLE_TEXT_DIRECTION_HASH = 797257663L
        @JvmField
        val getTitleTextDirectionBind =
            ObjectCalls.getMethodBind("FoldableContainer", "get_title_text_direction", GET_TITLE_TEXT_DIRECTION_HASH)

        private const val SET_TITLE_TEXT_OVERRUN_BEHAVIOR_HASH = 1008890932L
        @JvmField
        val setTitleTextOverrunBehaviorBind =
            ObjectCalls.getMethodBind("FoldableContainer", "set_title_text_overrun_behavior", SET_TITLE_TEXT_OVERRUN_BEHAVIOR_HASH)

        private const val GET_TITLE_TEXT_OVERRUN_BEHAVIOR_HASH = 3779142101L
        @JvmField
        val getTitleTextOverrunBehaviorBind =
            ObjectCalls.getMethodBind("FoldableContainer", "get_title_text_overrun_behavior", GET_TITLE_TEXT_OVERRUN_BEHAVIOR_HASH)

        private const val SET_TITLE_POSITION_HASH = 2276829442L
        @JvmField
        val setTitlePositionBind =
            ObjectCalls.getMethodBind("FoldableContainer", "set_title_position", SET_TITLE_POSITION_HASH)

        private const val GET_TITLE_POSITION_HASH = 3028840207L
        @JvmField
        val getTitlePositionBind =
            ObjectCalls.getMethodBind("FoldableContainer", "get_title_position", GET_TITLE_POSITION_HASH)

        private const val ADD_TITLE_BAR_CONTROL_HASH = 1496901182L
        @JvmField
        val addTitleBarControlBind =
            ObjectCalls.getMethodBind("FoldableContainer", "add_title_bar_control", ADD_TITLE_BAR_CONTROL_HASH)

        private const val REMOVE_TITLE_BAR_CONTROL_HASH = 1496901182L
        @JvmField
        val removeTitleBarControlBind =
            ObjectCalls.getMethodBind("FoldableContainer", "remove_title_bar_control", REMOVE_TITLE_BAR_CONTROL_HASH)
    }
}

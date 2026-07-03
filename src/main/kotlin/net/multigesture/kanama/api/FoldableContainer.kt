package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A container that can be expanded/collapsed.
 *
 * Generated from Godot docs: FoldableContainer
 */
class FoldableContainer(handle: MemorySegment) : Container(handle) {
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

    var titleAlignment: Long
        @JvmName("titleAlignmentProperty")
        get() = getTitleAlignment()
        @JvmName("setTitleAlignmentProperty")
        set(value) = setTitleAlignment(value)

    var titlePosition: Long
        @JvmName("titlePositionProperty")
        get() = getTitlePosition()
        @JvmName("setTitlePositionProperty")
        set(value) = setTitlePosition(value)

    var titleTextOverrunBehavior: Long
        @JvmName("titleTextOverrunBehaviorProperty")
        get() = getTitleTextOverrunBehavior()
        @JvmName("setTitleTextOverrunBehaviorProperty")
        set(value) = setTitleTextOverrunBehavior(value)

    var foldableGroup: FoldableGroup?
        @JvmName("foldableGroupProperty")
        get() = getFoldableGroup()
        @JvmName("setFoldableGroupProperty")
        set(value) = setFoldableGroup(value)

    var titleTextDirection: Long
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
        ObjectCalls.ptrcallNoArgs(foldBind, handle)
    }

    /**
     * Expands the container and emits `folding_changed`.
     *
     * Generated from Godot docs: FoldableContainer.expand
     */
    fun expand() {
        ObjectCalls.ptrcallNoArgs(expandBind, handle)
    }

    /**
     * If `true`, the container will become folded and will hide all its children.
     *
     * Generated from Godot docs: FoldableContainer.set_folded
     */
    fun setFolded(folded: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFoldedBind, handle, folded)
    }

    /**
     * If `true`, the container will become folded and will hide all its children.
     *
     * Generated from Godot docs: FoldableContainer.is_folded
     */
    fun isFolded(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFoldedBind, handle)
    }

    /**
     * The `FoldableGroup` associated with the container. When multiple `FoldableContainer` nodes share
     * the same group, only one of them is allowed to be unfolded.
     *
     * Generated from Godot docs: FoldableContainer.set_foldable_group
     */
    fun setFoldableGroup(buttonGroup: FoldableGroup?) {
        ObjectCalls.ptrcallWithObjectArgs(setFoldableGroupBind, handle, listOf(buttonGroup?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The `FoldableGroup` associated with the container. When multiple `FoldableContainer` nodes share
     * the same group, only one of them is allowed to be unfolded.
     *
     * Generated from Godot docs: FoldableContainer.get_foldable_group
     */
    fun getFoldableGroup(): FoldableGroup? {
        return FoldableGroup.wrap(ObjectCalls.ptrcallNoArgsRetObject(getFoldableGroupBind, handle))
    }

    /**
     * The container's title text.
     *
     * Generated from Godot docs: FoldableContainer.set_title
     */
    fun setTitle(text: String) {
        ObjectCalls.ptrcallWithStringArg(setTitleBind, handle, text)
    }

    /**
     * The container's title text.
     *
     * Generated from Godot docs: FoldableContainer.get_title
     */
    fun getTitle(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTitleBind, handle)
    }

    /**
     * Title's horizontal text alignment.
     *
     * Generated from Godot docs: FoldableContainer.set_title_alignment
     */
    fun setTitleAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setTitleAlignmentBind, handle, alignment)
    }

    /**
     * Title's horizontal text alignment.
     *
     * Generated from Godot docs: FoldableContainer.get_title_alignment
     */
    fun getTitleAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTitleAlignmentBind, handle)
    }

    /**
     * Language code used for text shaping algorithms. If left empty, the current locale is used
     * instead.
     *
     * Generated from Godot docs: FoldableContainer.set_language
     */
    fun setLanguage(language: String) {
        ObjectCalls.ptrcallWithStringArg(setLanguageBind, handle, language)
    }

    /**
     * Language code used for text shaping algorithms. If left empty, the current locale is used
     * instead.
     *
     * Generated from Godot docs: FoldableContainer.get_language
     */
    fun getLanguage(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLanguageBind, handle)
    }

    /**
     * Title text writing direction.
     *
     * Generated from Godot docs: FoldableContainer.set_title_text_direction
     */
    fun setTitleTextDirection(textDirection: Long) {
        ObjectCalls.ptrcallWithLongArg(setTitleTextDirectionBind, handle, textDirection)
    }

    /**
     * Title text writing direction.
     *
     * Generated from Godot docs: FoldableContainer.get_title_text_direction
     */
    fun getTitleTextDirection(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTitleTextDirectionBind, handle)
    }

    /**
     * Defines the behavior of the title when the text is longer than the available space.
     *
     * Generated from Godot docs: FoldableContainer.set_title_text_overrun_behavior
     */
    fun setTitleTextOverrunBehavior(overrunBehavior: Long) {
        ObjectCalls.ptrcallWithLongArg(setTitleTextOverrunBehaviorBind, handle, overrunBehavior)
    }

    /**
     * Defines the behavior of the title when the text is longer than the available space.
     *
     * Generated from Godot docs: FoldableContainer.get_title_text_overrun_behavior
     */
    fun getTitleTextOverrunBehavior(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTitleTextOverrunBehaviorBind, handle)
    }

    /**
     * Title's position.
     *
     * Generated from Godot docs: FoldableContainer.set_title_position
     */
    fun setTitlePosition(titlePosition: Long) {
        ObjectCalls.ptrcallWithLongArg(setTitlePositionBind, handle, titlePosition)
    }

    /**
     * Title's position.
     *
     * Generated from Godot docs: FoldableContainer.get_title_position
     */
    fun getTitlePosition(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTitlePositionBind, handle)
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
        ObjectCalls.ptrcallWithObjectArgs(addTitleBarControlBind, handle, listOf(control.handle))
    }

    /**
     * Removes a `Control` added with `add_title_bar_control`. The node is not freed automatically, you
     * need to use `Node.queue_free`.
     *
     * Generated from Godot docs: FoldableContainer.remove_title_bar_control
     */
    fun removeTitleBarControl(control: Control) {
        ObjectCalls.ptrcallWithObjectArgs(removeTitleBarControlBind, handle, listOf(control.handle))
    }

    object Signals {
        const val foldingChanged: String = "folding_changed"
    }

    companion object {
        const val POSITION_TOP: Long = 0L
        const val POSITION_BOTTOM: Long = 1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): FoldableContainer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): FoldableContainer? =
            if (handle.address() == 0L) null else FoldableContainer(handle)

        private const val FOLD_HASH = 3218959716L
        private val foldBind by lazy {
            ObjectCalls.getMethodBind("FoldableContainer", "fold", FOLD_HASH)
        }

        private const val EXPAND_HASH = 3218959716L
        private val expandBind by lazy {
            ObjectCalls.getMethodBind("FoldableContainer", "expand", EXPAND_HASH)
        }

        private const val SET_FOLDED_HASH = 2586408642L
        private val setFoldedBind by lazy {
            ObjectCalls.getMethodBind("FoldableContainer", "set_folded", SET_FOLDED_HASH)
        }

        private const val IS_FOLDED_HASH = 36873697L
        private val isFoldedBind by lazy {
            ObjectCalls.getMethodBind("FoldableContainer", "is_folded", IS_FOLDED_HASH)
        }

        private const val SET_FOLDABLE_GROUP_HASH = 3001390597L
        private val setFoldableGroupBind by lazy {
            ObjectCalls.getMethodBind("FoldableContainer", "set_foldable_group", SET_FOLDABLE_GROUP_HASH)
        }

        private const val GET_FOLDABLE_GROUP_HASH = 66499518L
        private val getFoldableGroupBind by lazy {
            ObjectCalls.getMethodBind("FoldableContainer", "get_foldable_group", GET_FOLDABLE_GROUP_HASH)
        }

        private const val SET_TITLE_HASH = 83702148L
        private val setTitleBind by lazy {
            ObjectCalls.getMethodBind("FoldableContainer", "set_title", SET_TITLE_HASH)
        }

        private const val GET_TITLE_HASH = 201670096L
        private val getTitleBind by lazy {
            ObjectCalls.getMethodBind("FoldableContainer", "get_title", GET_TITLE_HASH)
        }

        private const val SET_TITLE_ALIGNMENT_HASH = 2312603777L
        private val setTitleAlignmentBind by lazy {
            ObjectCalls.getMethodBind("FoldableContainer", "set_title_alignment", SET_TITLE_ALIGNMENT_HASH)
        }

        private const val GET_TITLE_ALIGNMENT_HASH = 341400642L
        private val getTitleAlignmentBind by lazy {
            ObjectCalls.getMethodBind("FoldableContainer", "get_title_alignment", GET_TITLE_ALIGNMENT_HASH)
        }

        private const val SET_LANGUAGE_HASH = 83702148L
        private val setLanguageBind by lazy {
            ObjectCalls.getMethodBind("FoldableContainer", "set_language", SET_LANGUAGE_HASH)
        }

        private const val GET_LANGUAGE_HASH = 201670096L
        private val getLanguageBind by lazy {
            ObjectCalls.getMethodBind("FoldableContainer", "get_language", GET_LANGUAGE_HASH)
        }

        private const val SET_TITLE_TEXT_DIRECTION_HASH = 119160795L
        private val setTitleTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("FoldableContainer", "set_title_text_direction", SET_TITLE_TEXT_DIRECTION_HASH)
        }

        private const val GET_TITLE_TEXT_DIRECTION_HASH = 797257663L
        private val getTitleTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("FoldableContainer", "get_title_text_direction", GET_TITLE_TEXT_DIRECTION_HASH)
        }

        private const val SET_TITLE_TEXT_OVERRUN_BEHAVIOR_HASH = 1008890932L
        private val setTitleTextOverrunBehaviorBind by lazy {
            ObjectCalls.getMethodBind("FoldableContainer", "set_title_text_overrun_behavior", SET_TITLE_TEXT_OVERRUN_BEHAVIOR_HASH)
        }

        private const val GET_TITLE_TEXT_OVERRUN_BEHAVIOR_HASH = 3779142101L
        private val getTitleTextOverrunBehaviorBind by lazy {
            ObjectCalls.getMethodBind("FoldableContainer", "get_title_text_overrun_behavior", GET_TITLE_TEXT_OVERRUN_BEHAVIOR_HASH)
        }

        private const val SET_TITLE_POSITION_HASH = 2276829442L
        private val setTitlePositionBind by lazy {
            ObjectCalls.getMethodBind("FoldableContainer", "set_title_position", SET_TITLE_POSITION_HASH)
        }

        private const val GET_TITLE_POSITION_HASH = 3028840207L
        private val getTitlePositionBind by lazy {
            ObjectCalls.getMethodBind("FoldableContainer", "get_title_position", GET_TITLE_POSITION_HASH)
        }

        private const val ADD_TITLE_BAR_CONTROL_HASH = 1496901182L
        private val addTitleBarControlBind by lazy {
            ObjectCalls.getMethodBind("FoldableContainer", "add_title_bar_control", ADD_TITLE_BAR_CONTROL_HASH)
        }

        private const val REMOVE_TITLE_BAR_CONTROL_HASH = 1496901182L
        private val removeTitleBarControlBind by lazy {
            ObjectCalls.getMethodBind("FoldableContainer", "remove_title_bar_control", REMOVE_TITLE_BAR_CONTROL_HASH)
        }
    }
}

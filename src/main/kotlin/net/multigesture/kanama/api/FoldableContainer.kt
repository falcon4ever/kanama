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

    fun setFolded(folded: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFoldedBind, handle, folded)
    }

    fun isFolded(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFoldedBind, handle)
    }

    fun setFoldableGroup(buttonGroup: FoldableGroup?) {
        ObjectCalls.ptrcallWithObjectArgs(setFoldableGroupBind, handle, listOf(buttonGroup?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getFoldableGroup(): FoldableGroup? {
        return FoldableGroup.wrap(ObjectCalls.ptrcallNoArgsRetObject(getFoldableGroupBind, handle))
    }

    fun setTitle(text: String) {
        ObjectCalls.ptrcallWithStringArg(setTitleBind, handle, text)
    }

    fun getTitle(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTitleBind, handle)
    }

    fun setTitleAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setTitleAlignmentBind, handle, alignment)
    }

    fun getTitleAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTitleAlignmentBind, handle)
    }

    fun setLanguage(language: String) {
        ObjectCalls.ptrcallWithStringArg(setLanguageBind, handle, language)
    }

    fun getLanguage(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLanguageBind, handle)
    }

    fun setTitleTextDirection(textDirection: Long) {
        ObjectCalls.ptrcallWithLongArg(setTitleTextDirectionBind, handle, textDirection)
    }

    fun getTitleTextDirection(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTitleTextDirectionBind, handle)
    }

    fun setTitleTextOverrunBehavior(overrunBehavior: Long) {
        ObjectCalls.ptrcallWithLongArg(setTitleTextOverrunBehaviorBind, handle, overrunBehavior)
    }

    fun getTitleTextOverrunBehavior(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTitleTextOverrunBehaviorBind, handle)
    }

    fun setTitlePosition(titlePosition: Long) {
        ObjectCalls.ptrcallWithLongArg(setTitlePositionBind, handle, titlePosition)
    }

    fun getTitlePosition(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTitlePositionBind, handle)
    }

    fun addTitleBarControl(control: Control) {
        ObjectCalls.ptrcallWithObjectArgs(addTitleBarControlBind, handle, listOf(control.handle))
    }

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

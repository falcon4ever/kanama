package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * A group of foldable containers that doesn't allow more than one container to be expanded at a
 * time.
 *
 * Generated from Godot docs: FoldableGroup
 */
class FoldableGroup(handle: GodotHandle) : Resource(handle) {
    var allowFoldingAll: Boolean
        @JvmName("allowFoldingAllProperty")
        get() = isAllowFoldingAll()
        @JvmName("setAllowFoldingAllProperty")
        set(value) = setAllowFoldingAll(value)

    /**
     * Returns the current expanded container.
     *
     * Generated from Godot docs: FoldableGroup.get_expanded_container
     */
    fun getExpandedContainer(): FoldableContainer? {
        checkOpen()
        return FoldableContainer.wrap(ObjectCalls.ptrcallNoArgsRetObject(getExpandedContainerBind, segment))
    }

    /**
     * Returns an `Array` of `FoldableContainer`s that have this as their FoldableGroup (see
     * `FoldableContainer.foldable_group`). This is equivalent to `ButtonGroup` but for
     * FoldableContainers.
     *
     * Generated from Godot docs: FoldableGroup.get_containers
     */
    fun getContainers(): List<FoldableContainer> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getContainersBind, segment, FoldableContainer::wrap)
    }

    /**
     * If `true`, it is possible to fold all containers in this FoldableGroup.
     *
     * Generated from Godot docs: FoldableGroup.set_allow_folding_all
     */
    fun setAllowFoldingAll(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setAllowFoldingAllBind, segment, enabled)
    }

    /**
     * If `true`, it is possible to fold all containers in this FoldableGroup.
     *
     * Generated from Godot docs: FoldableGroup.is_allow_folding_all
     */
    fun isAllowFoldingAll(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isAllowFoldingAllBind, segment)
    }

    object Signals {
        const val expanded: String = "expanded"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): FoldableGroup? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): FoldableGroup? =
            if (handle.address() == 0L) null else FoldableGroup(GodotHandle(handle))

        private const val GET_EXPANDED_CONTAINER_HASH = 1427441056L
        private val getExpandedContainerBind by lazy {
            ObjectCalls.getMethodBind("FoldableGroup", "get_expanded_container", GET_EXPANDED_CONTAINER_HASH)
        }

        private const val GET_CONTAINERS_HASH = 3995934104L
        private val getContainersBind by lazy {
            ObjectCalls.getMethodBind("FoldableGroup", "get_containers", GET_CONTAINERS_HASH)
        }

        private const val SET_ALLOW_FOLDING_ALL_HASH = 2586408642L
        private val setAllowFoldingAllBind by lazy {
            ObjectCalls.getMethodBind("FoldableGroup", "set_allow_folding_all", SET_ALLOW_FOLDING_ALL_HASH)
        }

        private const val IS_ALLOW_FOLDING_ALL_HASH = 36873697L
        private val isAllowFoldingAllBind by lazy {
            ObjectCalls.getMethodBind("FoldableGroup", "is_allow_folding_all", IS_ALLOW_FOLDING_ALL_HASH)
        }
    }
}

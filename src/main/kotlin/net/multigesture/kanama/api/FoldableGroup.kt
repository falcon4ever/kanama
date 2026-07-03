package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A group of foldable containers that doesn't allow more than one container to be expanded at a
 * time.
 *
 * Generated from Godot docs: FoldableGroup
 */
class FoldableGroup(handle: MemorySegment) : Resource(handle) {
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
        return FoldableContainer.wrap(ObjectCalls.ptrcallNoArgsRetObject(getExpandedContainerBind, handle))
    }

    /**
     * Returns an `Array` of `FoldableContainer`s that have this as their FoldableGroup (see
     * `FoldableContainer.foldable_group`). This is equivalent to `ButtonGroup` but for
     * FoldableContainers.
     *
     * Generated from Godot docs: FoldableGroup.get_containers
     */
    fun getContainers(): List<FoldableContainer> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getContainersBind, handle, FoldableContainer::fromHandle)
    }

    /**
     * If `true`, it is possible to fold all containers in this FoldableGroup.
     *
     * Generated from Godot docs: FoldableGroup.set_allow_folding_all
     */
    fun setAllowFoldingAll(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAllowFoldingAllBind, handle, enabled)
    }

    /**
     * If `true`, it is possible to fold all containers in this FoldableGroup.
     *
     * Generated from Godot docs: FoldableGroup.is_allow_folding_all
     */
    fun isAllowFoldingAll(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAllowFoldingAllBind, handle)
    }

    object Signals {
        const val expanded: String = "expanded"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): FoldableGroup? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): FoldableGroup? =
            if (handle.address() == 0L) null else FoldableGroup(handle)

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

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Container for `Animation` resources.
 *
 * Generated from Godot docs: AnimationLibrary
 */
class AnimationLibrary(handle: MemorySegment) : Resource(handle) {
    /**
     * Adds the `animation` to the library, accessible by the key `name`.
     *
     * Generated from Godot docs: AnimationLibrary.add_animation
     */
    fun addAnimation(name: String, animation: Animation?): Long {
        return ObjectCalls.ptrcallWithStringNameAndObjectArgRetLong(addAnimationBind, handle, name, animation?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Removes the `Animation` with the key `name`.
     *
     * Generated from Godot docs: AnimationLibrary.remove_animation
     */
    fun removeAnimation(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeAnimationBind, handle, name)
    }

    /**
     * Changes the key of the `Animation` associated with the key `name` to `newname`.
     *
     * Generated from Godot docs: AnimationLibrary.rename_animation
     */
    fun renameAnimation(name: String, newname: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(renameAnimationBind, handle, name, newname)
    }

    /**
     * Returns `true` if the library stores an `Animation` with `name` as the key.
     *
     * Generated from Godot docs: AnimationLibrary.has_animation
     */
    fun hasAnimation(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasAnimationBind, handle, name)
    }

    /**
     * Returns the `Animation` with the key `name`. If the animation does not exist, `null` is returned
     * and an error is logged.
     *
     * Generated from Godot docs: AnimationLibrary.get_animation
     */
    fun getAnimation(name: String): Animation? {
        return Animation.wrap(ObjectCalls.ptrcallWithStringNameArgRetObject(getAnimationBind, handle, name))
    }

    /**
     * Returns the keys for the `Animation`s stored in the library.
     *
     * Generated from Godot docs: AnimationLibrary.get_animation_list
     */
    fun getAnimationList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetStringNameList(getAnimationListBind, handle)
    }

    /**
     * Returns the key count for the `Animation`s stored in the library.
     *
     * Generated from Godot docs: AnimationLibrary.get_animation_list_size
     */
    fun getAnimationListSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getAnimationListSizeBind, handle)
    }

    object Signals {
        const val animationAdded: String = "animation_added"
        const val animationRemoved: String = "animation_removed"
        const val animationRenamed: String = "animation_renamed"
        const val animationChanged: String = "animation_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AnimationLibrary? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AnimationLibrary? =
            if (handle.address() == 0L) null else AnimationLibrary(handle)

        private const val ADD_ANIMATION_HASH = 1811855551L
        private val addAnimationBind by lazy {
            ObjectCalls.getMethodBind("AnimationLibrary", "add_animation", ADD_ANIMATION_HASH)
        }

        private const val REMOVE_ANIMATION_HASH = 3304788590L
        private val removeAnimationBind by lazy {
            ObjectCalls.getMethodBind("AnimationLibrary", "remove_animation", REMOVE_ANIMATION_HASH)
        }

        private const val RENAME_ANIMATION_HASH = 3740211285L
        private val renameAnimationBind by lazy {
            ObjectCalls.getMethodBind("AnimationLibrary", "rename_animation", RENAME_ANIMATION_HASH)
        }

        private const val HAS_ANIMATION_HASH = 2619796661L
        private val hasAnimationBind by lazy {
            ObjectCalls.getMethodBind("AnimationLibrary", "has_animation", HAS_ANIMATION_HASH)
        }

        private const val GET_ANIMATION_HASH = 2933122410L
        private val getAnimationBind by lazy {
            ObjectCalls.getMethodBind("AnimationLibrary", "get_animation", GET_ANIMATION_HASH)
        }

        private const val GET_ANIMATION_LIST_HASH = 3995934104L
        private val getAnimationListBind by lazy {
            ObjectCalls.getMethodBind("AnimationLibrary", "get_animation_list", GET_ANIMATION_LIST_HASH)
        }

        private const val GET_ANIMATION_LIST_SIZE_HASH = 3905245786L
        private val getAnimationListSizeBind by lazy {
            ObjectCalls.getMethodBind("AnimationLibrary", "get_animation_list_size", GET_ANIMATION_LIST_SIZE_HASH)
        }
    }
}

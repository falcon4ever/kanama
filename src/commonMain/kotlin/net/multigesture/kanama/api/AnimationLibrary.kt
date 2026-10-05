package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Container for `Animation` resources.
 *
 * Generated from Godot docs: AnimationLibrary
 */
class AnimationLibrary(handle: GodotHandle) : Resource(handle) {
    /**
     * Adds the `animation` to the library, accessible by the key `name`.
     *
     * Generated from Godot docs: AnimationLibrary.add_animation
     */
    fun addAnimation(name: String, animation: Animation?): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringNameAndObjectArgRetLong(addAnimationBind, segment, name, animation?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Removes the `Animation` with the key `name`.
     *
     * Generated from Godot docs: AnimationLibrary.remove_animation
     */
    fun removeAnimation(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameArg(removeAnimationBind, segment, name)
    }

    /**
     * Changes the key of the `Animation` associated with the key `name` to `newname`.
     *
     * Generated from Godot docs: AnimationLibrary.rename_animation
     */
    fun renameAnimation(name: String, newname: String) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoStringNameArgs(renameAnimationBind, segment, name, newname)
    }

    /**
     * Returns `true` if the library stores an `Animation` with `name` as the key.
     *
     * Generated from Godot docs: AnimationLibrary.has_animation
     */
    fun hasAnimation(name: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasAnimationBind, segment, name)
    }

    /**
     * Returns the `Animation` with the key `name`. If the animation does not exist, `null` is returned
     * and an error is logged.
     *
     * Generated from Godot docs: AnimationLibrary.get_animation
     */
    fun getAnimation(name: String): Animation? {
        checkOpen()
        return Animation.wrapOwned(ObjectCalls.ptrcallWithStringNameArgRetObject(getAnimationBind, segment, name))
    }

    /**
     * Returns the keys for the `Animation`s stored in the library.
     *
     * Generated from Godot docs: AnimationLibrary.get_animation_list
     */
    fun getAnimationList(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetStringNameList(getAnimationListBind, segment)
    }

    /**
     * Returns the key count for the `Animation`s stored in the library.
     *
     * Generated from Godot docs: AnimationLibrary.get_animation_list_size
     */
    fun getAnimationListSize(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getAnimationListSizeBind, segment)
    }

    /** Signal `animation_added(anim_name: StringName)`; see [TypedSignal]. */
    val animationAdded: Signal1<String>
        @JvmName("animationAddedTypedSignal")
        get() = Signal1(this, "animation_added", SignalArgType.STRING)

    /** Signal `animation_removed(anim_name: StringName)`; see [TypedSignal]. */
    val animationRemoved: Signal1<String>
        @JvmName("animationRemovedTypedSignal")
        get() = Signal1(this, "animation_removed", SignalArgType.STRING)

    /** Signal `animation_renamed(old_name: StringName, new_name: StringName)`; see [TypedSignal]. */
    val animationRenamed: Signal2<String, String>
        @JvmName("animationRenamedTypedSignal")
        get() = Signal2(this, "animation_renamed", SignalArgType.STRING, SignalArgType.STRING)

    /** Signal `animation_changed(anim_name: StringName)`; see [TypedSignal]. */
    val animationChanged: Signal1<String>
        @JvmName("animationChangedTypedSignal")
        get() = Signal1(this, "animation_changed", SignalArgType.STRING)

    object Signals {
        const val animationAdded: String = "animation_added"
        const val animationRemoved: String = "animation_removed"
        const val animationRenamed: String = "animation_renamed"
        const val animationChanged: String = "animation_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationLibrary? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AnimationLibrary? =
            if (handle.address() == 0L) null else RefCounted.owned(AnimationLibrary(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AnimationLibrary? =
            if (handle.address() == 0L) null else AnimationLibrary(GodotHandle(handle))

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

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A node that may modify a Skeleton3D's bones.
 *
 * Generated from Godot docs: SkeletonModifier3D
 */
open class SkeletonModifier3D(handle: MemorySegment) : Node3D(handle) {
    var active: Boolean
        @JvmName("activeProperty")
        get() = isActive()
        @JvmName("setActiveProperty")
        set(value) = setActive(value)

    var influence: Double
        @JvmName("influenceProperty")
        get() = getInfluence()
        @JvmName("setInfluenceProperty")
        set(value) = setInfluence(value)

    /**
     * Returns the parent `Skeleton3D` node if it exists. Otherwise, returns `null`.
     *
     * Generated from Godot docs: SkeletonModifier3D.get_skeleton
     */
    fun getSkeleton(): Skeleton3D? {
        return Skeleton3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSkeletonBind, handle))
    }

    /**
     * If `true`, the `SkeletonModifier3D` will be processing.
     *
     * Generated from Godot docs: SkeletonModifier3D.set_active
     */
    fun setActive(active: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setActiveBind, handle, active)
    }

    /**
     * If `true`, the `SkeletonModifier3D` will be processing.
     *
     * Generated from Godot docs: SkeletonModifier3D.is_active
     */
    fun isActive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isActiveBind, handle)
    }

    /**
     * Sets the influence of the modification. Note: This value is used by `Skeleton3D` to blend, so
     * the `SkeletonModifier3D` should always apply only 100% of the result without interpolation.
     *
     * Generated from Godot docs: SkeletonModifier3D.set_influence
     */
    fun setInfluence(influence: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setInfluenceBind, handle, influence)
    }

    /**
     * Sets the influence of the modification. Note: This value is used by `Skeleton3D` to blend, so
     * the `SkeletonModifier3D` should always apply only 100% of the result without interpolation.
     *
     * Generated from Godot docs: SkeletonModifier3D.get_influence
     */
    fun getInfluence(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getInfluenceBind, handle)
    }

    object Signals {
        const val modificationProcessed: String = "modification_processed"
    }

    companion object {
        const val BONE_AXIS_PLUS_X: Long = 0L
        const val BONE_AXIS_MINUS_X: Long = 1L
        const val BONE_AXIS_PLUS_Y: Long = 2L
        const val BONE_AXIS_MINUS_Y: Long = 3L
        const val BONE_AXIS_PLUS_Z: Long = 4L
        const val BONE_AXIS_MINUS_Z: Long = 5L
        const val BONE_DIRECTION_PLUS_X: Long = 0L
        const val BONE_DIRECTION_MINUS_X: Long = 1L
        const val BONE_DIRECTION_PLUS_Y: Long = 2L
        const val BONE_DIRECTION_MINUS_Y: Long = 3L
        const val BONE_DIRECTION_PLUS_Z: Long = 4L
        const val BONE_DIRECTION_MINUS_Z: Long = 5L
        const val BONE_DIRECTION_FROM_PARENT: Long = 6L
        const val SECONDARY_DIRECTION_NONE: Long = 0L
        const val SECONDARY_DIRECTION_PLUS_X: Long = 1L
        const val SECONDARY_DIRECTION_MINUS_X: Long = 2L
        const val SECONDARY_DIRECTION_PLUS_Y: Long = 3L
        const val SECONDARY_DIRECTION_MINUS_Y: Long = 4L
        const val SECONDARY_DIRECTION_PLUS_Z: Long = 5L
        const val SECONDARY_DIRECTION_MINUS_Z: Long = 6L
        const val SECONDARY_DIRECTION_CUSTOM: Long = 7L
        const val ROTATION_AXIS_X: Long = 0L
        const val ROTATION_AXIS_Y: Long = 1L
        const val ROTATION_AXIS_Z: Long = 2L
        const val ROTATION_AXIS_ALL: Long = 3L
        const val ROTATION_AXIS_CUSTOM: Long = 4L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): SkeletonModifier3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SkeletonModifier3D? =
            if (handle.address() == 0L) null else SkeletonModifier3D(handle)

        private const val GET_SKELETON_HASH = 1488626673L
        private val getSkeletonBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModifier3D", "get_skeleton", GET_SKELETON_HASH)
        }

        private const val SET_ACTIVE_HASH = 2586408642L
        private val setActiveBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModifier3D", "set_active", SET_ACTIVE_HASH)
        }

        private const val IS_ACTIVE_HASH = 36873697L
        private val isActiveBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModifier3D", "is_active", IS_ACTIVE_HASH)
        }

        private const val SET_INFLUENCE_HASH = 373806689L
        private val setInfluenceBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModifier3D", "set_influence", SET_INFLUENCE_HASH)
        }

        private const val GET_INFLUENCE_HASH = 1740695150L
        private val getInfluenceBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModifier3D", "get_influence", GET_INFLUENCE_HASH)
        }
    }
}

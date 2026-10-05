package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A node that may modify a Skeleton3D's bones.
 *
 * Generated from Godot docs: SkeletonModifier3D
 */
open class SkeletonModifier3D(handle: GodotHandle) : Node3D(handle) {
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
        return Skeleton3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSkeletonBind, segment))
    }

    /**
     * If `true`, the `SkeletonModifier3D` will be processing.
     *
     * Generated from Godot docs: SkeletonModifier3D.set_active
     */
    fun setActive(active: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setActiveBind, segment, active)
    }

    /**
     * If `true`, the `SkeletonModifier3D` will be processing.
     *
     * Generated from Godot docs: SkeletonModifier3D.is_active
     */
    fun isActive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isActiveBind, segment)
    }

    /**
     * Sets the influence of the modification. Note: This value is used by `Skeleton3D` to blend, so
     * the `SkeletonModifier3D` should always apply only 100% of the result without interpolation.
     *
     * Generated from Godot docs: SkeletonModifier3D.set_influence
     */
    fun setInfluence(influence: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setInfluenceBind, segment, influence)
    }

    /**
     * Sets the influence of the modification. Note: This value is used by `Skeleton3D` to blend, so
     * the `SkeletonModifier3D` should always apply only 100% of the result without interpolation.
     *
     * Generated from Godot docs: SkeletonModifier3D.get_influence
     */
    fun getInfluence(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getInfluenceBind, segment)
    }

    /** Signal `modification_processed()`; see [TypedSignal]. */
    val modificationProcessed: Signal0
        @JvmName("modificationProcessedTypedSignal")
        get() = Signal0(this, "modification_processed")

    object Signals {
        const val modificationProcessed: String = "modification_processed"
    }

    /**
     * Godot's `SkeletonModifier3D.BoneAxis` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`SkeletonModifier3D.BoneAxis.<NAME>`).
     *
     * Generated from Godot docs: SkeletonModifier3D.BoneAxis
     */
    @JvmInline
    value class BoneAxis(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Enumerated value for the +X axis.
             *
             * Generated from Godot docs: SkeletonModifier3D.BONE_AXIS_PLUS_X
             */
            val PLUS_X: BoneAxis get() = BoneAxis(0L)
            /**
             * Enumerated value for the -X axis.
             *
             * Generated from Godot docs: SkeletonModifier3D.BONE_AXIS_MINUS_X
             */
            val MINUS_X: BoneAxis get() = BoneAxis(1L)
            /**
             * Enumerated value for the +Y axis.
             *
             * Generated from Godot docs: SkeletonModifier3D.BONE_AXIS_PLUS_Y
             */
            val PLUS_Y: BoneAxis get() = BoneAxis(2L)
            /**
             * Enumerated value for the -Y axis.
             *
             * Generated from Godot docs: SkeletonModifier3D.BONE_AXIS_MINUS_Y
             */
            val MINUS_Y: BoneAxis get() = BoneAxis(3L)
            /**
             * Enumerated value for the +Z axis.
             *
             * Generated from Godot docs: SkeletonModifier3D.BONE_AXIS_PLUS_Z
             */
            val PLUS_Z: BoneAxis get() = BoneAxis(4L)
            /**
             * Enumerated value for the -Z axis.
             *
             * Generated from Godot docs: SkeletonModifier3D.BONE_AXIS_MINUS_Z
             */
            val MINUS_Z: BoneAxis get() = BoneAxis(5L)
        }
    }

    /**
     * Godot's `SkeletonModifier3D.BoneDirection` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`SkeletonModifier3D.BoneDirection.<NAME>`).
     *
     * Generated from Godot docs: SkeletonModifier3D.BoneDirection
     */
    @JvmInline
    value class BoneDirection(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Enumerated value for the +X axis.
             *
             * Generated from Godot docs: SkeletonModifier3D.BONE_DIRECTION_PLUS_X
             */
            val PLUS_X: BoneDirection get() = BoneDirection(0L)
            /**
             * Enumerated value for the -X axis.
             *
             * Generated from Godot docs: SkeletonModifier3D.BONE_DIRECTION_MINUS_X
             */
            val MINUS_X: BoneDirection get() = BoneDirection(1L)
            /**
             * Enumerated value for the +Y axis.
             *
             * Generated from Godot docs: SkeletonModifier3D.BONE_DIRECTION_PLUS_Y
             */
            val PLUS_Y: BoneDirection get() = BoneDirection(2L)
            /**
             * Enumerated value for the -Y axis.
             *
             * Generated from Godot docs: SkeletonModifier3D.BONE_DIRECTION_MINUS_Y
             */
            val MINUS_Y: BoneDirection get() = BoneDirection(3L)
            /**
             * Enumerated value for the +Z axis.
             *
             * Generated from Godot docs: SkeletonModifier3D.BONE_DIRECTION_PLUS_Z
             */
            val PLUS_Z: BoneDirection get() = BoneDirection(4L)
            /**
             * Enumerated value for the -Z axis.
             *
             * Generated from Godot docs: SkeletonModifier3D.BONE_DIRECTION_MINUS_Z
             */
            val MINUS_Z: BoneDirection get() = BoneDirection(5L)
            /**
             * Enumerated value for the axis from a parent bone to the child bone.
             *
             * Generated from Godot docs: SkeletonModifier3D.BONE_DIRECTION_FROM_PARENT
             */
            val FROM_PARENT: BoneDirection get() = BoneDirection(6L)
        }
    }

    /**
     * Godot's `SkeletonModifier3D.SecondaryDirection` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`SkeletonModifier3D.SecondaryDirection.<NAME>`).
     *
     * Generated from Godot docs: SkeletonModifier3D.SecondaryDirection
     */
    @JvmInline
    value class SecondaryDirection(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Enumerated value for the case when the axis is undefined.
             *
             * Generated from Godot docs: SkeletonModifier3D.SECONDARY_DIRECTION_NONE
             */
            val NONE: SecondaryDirection get() = SecondaryDirection(0L)
            /**
             * Enumerated value for the +X axis.
             *
             * Generated from Godot docs: SkeletonModifier3D.SECONDARY_DIRECTION_PLUS_X
             */
            val PLUS_X: SecondaryDirection get() = SecondaryDirection(1L)
            /**
             * Enumerated value for the -X axis.
             *
             * Generated from Godot docs: SkeletonModifier3D.SECONDARY_DIRECTION_MINUS_X
             */
            val MINUS_X: SecondaryDirection get() = SecondaryDirection(2L)
            /**
             * Enumerated value for the +Y axis.
             *
             * Generated from Godot docs: SkeletonModifier3D.SECONDARY_DIRECTION_PLUS_Y
             */
            val PLUS_Y: SecondaryDirection get() = SecondaryDirection(3L)
            /**
             * Enumerated value for the -Y axis.
             *
             * Generated from Godot docs: SkeletonModifier3D.SECONDARY_DIRECTION_MINUS_Y
             */
            val MINUS_Y: SecondaryDirection get() = SecondaryDirection(4L)
            /**
             * Enumerated value for the +Z axis.
             *
             * Generated from Godot docs: SkeletonModifier3D.SECONDARY_DIRECTION_PLUS_Z
             */
            val PLUS_Z: SecondaryDirection get() = SecondaryDirection(5L)
            /**
             * Enumerated value for the -Z axis.
             *
             * Generated from Godot docs: SkeletonModifier3D.SECONDARY_DIRECTION_MINUS_Z
             */
            val MINUS_Z: SecondaryDirection get() = SecondaryDirection(6L)
            /**
             * Enumerated value for an optional axis.
             *
             * Generated from Godot docs: SkeletonModifier3D.SECONDARY_DIRECTION_CUSTOM
             */
            val CUSTOM: SecondaryDirection get() = SecondaryDirection(7L)
        }
    }

    /**
     * Godot's `SkeletonModifier3D.RotationAxis` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`SkeletonModifier3D.RotationAxis.<NAME>`).
     *
     * Generated from Godot docs: SkeletonModifier3D.RotationAxis
     */
    @JvmInline
    value class RotationAxis(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Enumerated value for the rotation of the X axis.
             *
             * Generated from Godot docs: SkeletonModifier3D.ROTATION_AXIS_X
             */
            val X: RotationAxis get() = RotationAxis(0L)
            /**
             * Enumerated value for the rotation of the Y axis.
             *
             * Generated from Godot docs: SkeletonModifier3D.ROTATION_AXIS_Y
             */
            val Y: RotationAxis get() = RotationAxis(1L)
            /**
             * Enumerated value for the rotation of the Z axis.
             *
             * Generated from Godot docs: SkeletonModifier3D.ROTATION_AXIS_Z
             */
            val Z: RotationAxis get() = RotationAxis(2L)
            /**
             * Enumerated value for the unconstrained rotation.
             *
             * Generated from Godot docs: SkeletonModifier3D.ROTATION_AXIS_ALL
             */
            val ALL: RotationAxis get() = RotationAxis(3L)
            /**
             * Enumerated value for an optional rotation axis.
             *
             * Generated from Godot docs: SkeletonModifier3D.ROTATION_AXIS_CUSTOM
             */
            val CUSTOM: RotationAxis get() = RotationAxis(4L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SkeletonModifier3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): SkeletonModifier3D? =
            if (handle.address() == 0L) null else SkeletonModifier3D(GodotHandle(handle))

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

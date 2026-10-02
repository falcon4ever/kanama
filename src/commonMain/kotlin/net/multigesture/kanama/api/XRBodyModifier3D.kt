package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A node for driving body meshes from `XRBodyTracker` data.
 *
 * Generated from Godot docs: XRBodyModifier3D
 */
class XRBodyModifier3D(handle: GodotHandle) : SkeletonModifier3D(handle) {
    var bodyTracker: String
        @JvmName("bodyTrackerProperty")
        get() = getBodyTracker()
        @JvmName("setBodyTrackerProperty")
        set(value) = setBodyTracker(value)

    var bodyUpdate: XRBodyModifier3D.BodyUpdate
        @JvmName("bodyUpdateProperty")
        get() = getBodyUpdate()
        @JvmName("setBodyUpdateProperty")
        set(value) = setBodyUpdate(value)

    var boneUpdate: XRBodyModifier3D.BoneUpdate
        @JvmName("boneUpdateProperty")
        get() = getBoneUpdate()
        @JvmName("setBoneUpdateProperty")
        set(value) = setBoneUpdate(value)

    /**
     * The name of the `XRBodyTracker` registered with `XRServer` to obtain the body tracking data
     * from.
     *
     * Generated from Godot docs: XRBodyModifier3D.set_body_tracker
     */
    fun setBodyTracker(trackerName: String) {
        ObjectCalls.ptrcallWithStringNameArg(setBodyTrackerBind, segment, trackerName)
    }

    /**
     * The name of the `XRBodyTracker` registered with `XRServer` to obtain the body tracking data
     * from.
     *
     * Generated from Godot docs: XRBodyModifier3D.get_body_tracker
     */
    fun getBodyTracker(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getBodyTrackerBind, segment)
    }

    /**
     * Specifies the body parts to update.
     *
     * Generated from Godot docs: XRBodyModifier3D.set_body_update
     */
    fun setBodyUpdate(bodyUpdate: XRBodyModifier3D.BodyUpdate) {
        ObjectCalls.ptrcallWithLongArg(setBodyUpdateBind, segment, bodyUpdate.value)
    }

    /**
     * Specifies the body parts to update.
     *
     * Generated from Godot docs: XRBodyModifier3D.get_body_update
     */
    fun getBodyUpdate(): XRBodyModifier3D.BodyUpdate {
        return XRBodyModifier3D.BodyUpdate(ObjectCalls.ptrcallNoArgsRetLong(getBodyUpdateBind, segment))
    }

    /**
     * Specifies the type of updates to perform on the bones.
     *
     * Generated from Godot docs: XRBodyModifier3D.set_bone_update
     */
    fun setBoneUpdate(boneUpdate: XRBodyModifier3D.BoneUpdate) {
        ObjectCalls.ptrcallWithLongArg(setBoneUpdateBind, segment, boneUpdate.value)
    }

    /**
     * Specifies the type of updates to perform on the bones.
     *
     * Generated from Godot docs: XRBodyModifier3D.get_bone_update
     */
    fun getBoneUpdate(): XRBodyModifier3D.BoneUpdate {
        return XRBodyModifier3D.BoneUpdate(ObjectCalls.ptrcallNoArgsRetLong(getBoneUpdateBind, segment))
    }

    @JvmInline
    value class BodyUpdate(val value: Long) {
        infix fun or(other: BodyUpdate): BodyUpdate = BodyUpdate(value or other.value)

        infix fun and(other: BodyUpdate): BodyUpdate = BodyUpdate(value and other.value)

        infix fun xor(other: BodyUpdate): BodyUpdate = BodyUpdate(value xor other.value)

        fun inv(): BodyUpdate = BodyUpdate(value.inv())

        operator fun contains(other: BodyUpdate): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * The skeleton's upper body joints are updated.
             *
             * Generated from Godot docs: XRBodyModifier3D.BODY_UPDATE_UPPER_BODY
             */
            val UPPER_BODY: BodyUpdate get() = BodyUpdate(1L)
            /**
             * The skeleton's lower body joints are updated.
             *
             * Generated from Godot docs: XRBodyModifier3D.BODY_UPDATE_LOWER_BODY
             */
            val LOWER_BODY: BodyUpdate get() = BodyUpdate(2L)
            /**
             * The skeleton's hand joints are updated.
             *
             * Generated from Godot docs: XRBodyModifier3D.BODY_UPDATE_HANDS
             */
            val HANDS: BodyUpdate get() = BodyUpdate(4L)
        }
    }

    @JvmInline
    value class BoneUpdate(val value: Long) {
        companion object {
            /**
             * The skeleton's bones are fully updated (both position and rotation) to match the tracked bones.
             *
             * Generated from Godot docs: XRBodyModifier3D.BONE_UPDATE_FULL
             */
            val FULL: BoneUpdate get() = BoneUpdate(0L)
            /**
             * The skeleton's bones are only rotated to align with the tracked bones, preserving bone length.
             *
             * Generated from Godot docs: XRBodyModifier3D.BONE_UPDATE_ROTATION_ONLY
             */
            val ROTATION_ONLY: BoneUpdate get() = BoneUpdate(1L)
            /**
             * Represents the size of the `BoneUpdate` enum.
             *
             * Generated from Godot docs: XRBodyModifier3D.BONE_UPDATE_MAX
             */
            val MAX: BoneUpdate get() = BoneUpdate(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): XRBodyModifier3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): XRBodyModifier3D? =
            if (handle.address() == 0L) null else XRBodyModifier3D(GodotHandle(handle))

        private const val SET_BODY_TRACKER_HASH = 3304788590L
        private val setBodyTrackerBind by lazy {
            ObjectCalls.getMethodBind("XRBodyModifier3D", "set_body_tracker", SET_BODY_TRACKER_HASH)
        }

        private const val GET_BODY_TRACKER_HASH = 2002593661L
        private val getBodyTrackerBind by lazy {
            ObjectCalls.getMethodBind("XRBodyModifier3D", "get_body_tracker", GET_BODY_TRACKER_HASH)
        }

        private const val SET_BODY_UPDATE_HASH = 2211199417L
        private val setBodyUpdateBind by lazy {
            ObjectCalls.getMethodBind("XRBodyModifier3D", "set_body_update", SET_BODY_UPDATE_HASH)
        }

        private const val GET_BODY_UPDATE_HASH = 2642335328L
        private val getBodyUpdateBind by lazy {
            ObjectCalls.getMethodBind("XRBodyModifier3D", "get_body_update", GET_BODY_UPDATE_HASH)
        }

        private const val SET_BONE_UPDATE_HASH = 3356796943L
        private val setBoneUpdateBind by lazy {
            ObjectCalls.getMethodBind("XRBodyModifier3D", "set_bone_update", SET_BONE_UPDATE_HASH)
        }

        private const val GET_BONE_UPDATE_HASH = 1309305964L
        private val getBoneUpdateBind by lazy {
            ObjectCalls.getMethodBind("XRBodyModifier3D", "get_bone_update", GET_BONE_UPDATE_HASH)
        }
    }
}

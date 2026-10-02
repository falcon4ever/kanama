package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A node for driving hand meshes from `XRHandTracker` data.
 *
 * Generated from Godot docs: XRHandModifier3D
 */
class XRHandModifier3D(handle: GodotHandle) : SkeletonModifier3D(handle) {
    var handTracker: String
        @JvmName("handTrackerProperty")
        get() = getHandTracker()
        @JvmName("setHandTrackerProperty")
        set(value) = setHandTracker(value)

    var boneUpdate: XRHandModifier3D.BoneUpdate
        @JvmName("boneUpdateProperty")
        get() = getBoneUpdate()
        @JvmName("setBoneUpdateProperty")
        set(value) = setBoneUpdate(value)

    /**
     * The name of the `XRHandTracker` registered with `XRServer` to obtain the hand tracking data
     * from.
     *
     * Generated from Godot docs: XRHandModifier3D.set_hand_tracker
     */
    fun setHandTracker(trackerName: String) {
        ObjectCalls.ptrcallWithStringNameArg(setHandTrackerBind, segment, trackerName)
    }

    /**
     * The name of the `XRHandTracker` registered with `XRServer` to obtain the hand tracking data
     * from.
     *
     * Generated from Godot docs: XRHandModifier3D.get_hand_tracker
     */
    fun getHandTracker(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getHandTrackerBind, segment)
    }

    /**
     * Specifies the type of updates to perform on the bones.
     *
     * Generated from Godot docs: XRHandModifier3D.set_bone_update
     */
    fun setBoneUpdate(boneUpdate: XRHandModifier3D.BoneUpdate) {
        ObjectCalls.ptrcallWithLongArg(setBoneUpdateBind, segment, boneUpdate.value)
    }

    /**
     * Specifies the type of updates to perform on the bones.
     *
     * Generated from Godot docs: XRHandModifier3D.get_bone_update
     */
    fun getBoneUpdate(): XRHandModifier3D.BoneUpdate {
        return XRHandModifier3D.BoneUpdate(ObjectCalls.ptrcallNoArgsRetLong(getBoneUpdateBind, segment))
    }

    /**
     * Godot's `XRHandModifier3D.BoneUpdate` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`XRHandModifier3D.BoneUpdate.<NAME>`).
     *
     * Generated from Godot docs: XRHandModifier3D.BoneUpdate
     */
    @JvmInline
    value class BoneUpdate(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The skeleton's bones are fully updated (both position and rotation) to match the tracked bones.
             *
             * Generated from Godot docs: XRHandModifier3D.BONE_UPDATE_FULL
             */
            val FULL: BoneUpdate get() = BoneUpdate(0L)
            /**
             * The skeleton's bones are only rotated to align with the tracked bones, preserving bone length.
             *
             * Generated from Godot docs: XRHandModifier3D.BONE_UPDATE_ROTATION_ONLY
             */
            val ROTATION_ONLY: BoneUpdate get() = BoneUpdate(1L)
            /**
             * Represents the size of the `BoneUpdate` enum.
             *
             * Generated from Godot docs: XRHandModifier3D.BONE_UPDATE_MAX
             */
            val MAX: BoneUpdate get() = BoneUpdate(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): XRHandModifier3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): XRHandModifier3D? =
            if (handle.address() == 0L) null else XRHandModifier3D(GodotHandle(handle))

        private const val SET_HAND_TRACKER_HASH = 3304788590L
        private val setHandTrackerBind by lazy {
            ObjectCalls.getMethodBind("XRHandModifier3D", "set_hand_tracker", SET_HAND_TRACKER_HASH)
        }

        private const val GET_HAND_TRACKER_HASH = 2002593661L
        private val getHandTrackerBind by lazy {
            ObjectCalls.getMethodBind("XRHandModifier3D", "get_hand_tracker", GET_HAND_TRACKER_HASH)
        }

        private const val SET_BONE_UPDATE_HASH = 3635701455L
        private val setBoneUpdateBind by lazy {
            ObjectCalls.getMethodBind("XRHandModifier3D", "set_bone_update", SET_BONE_UPDATE_HASH)
        }

        private const val GET_BONE_UPDATE_HASH = 2873665691L
        private val getBoneUpdateBind by lazy {
            ObjectCalls.getMethodBind("XRHandModifier3D", "get_bone_update", GET_BONE_UPDATE_HASH)
        }
    }
}

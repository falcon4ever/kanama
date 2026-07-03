package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A node for driving hand meshes from `XRHandTracker` data.
 *
 * Generated from Godot docs: XRHandModifier3D
 */
class XRHandModifier3D(handle: MemorySegment) : SkeletonModifier3D(handle) {
    var handTracker: String
        @JvmName("handTrackerProperty")
        get() = getHandTracker()
        @JvmName("setHandTrackerProperty")
        set(value) = setHandTracker(value)

    var boneUpdate: Long
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
        ObjectCalls.ptrcallWithStringNameArg(setHandTrackerBind, handle, trackerName)
    }

    /**
     * The name of the `XRHandTracker` registered with `XRServer` to obtain the hand tracking data
     * from.
     *
     * Generated from Godot docs: XRHandModifier3D.get_hand_tracker
     */
    fun getHandTracker(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getHandTrackerBind, handle)
    }

    /**
     * Specifies the type of updates to perform on the bones.
     *
     * Generated from Godot docs: XRHandModifier3D.set_bone_update
     */
    fun setBoneUpdate(boneUpdate: Long) {
        ObjectCalls.ptrcallWithLongArg(setBoneUpdateBind, handle, boneUpdate)
    }

    /**
     * Specifies the type of updates to perform on the bones.
     *
     * Generated from Godot docs: XRHandModifier3D.get_bone_update
     */
    fun getBoneUpdate(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBoneUpdateBind, handle)
    }

    companion object {
        const val BONE_UPDATE_FULL: Long = 0L
        const val BONE_UPDATE_ROTATION_ONLY: Long = 1L
        const val BONE_UPDATE_MAX: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): XRHandModifier3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): XRHandModifier3D? =
            if (handle.address() == 0L) null else XRHandModifier3D(handle)

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

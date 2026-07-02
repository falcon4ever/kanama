package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A node for driving body meshes from `XRBodyTracker` data.
 *
 * Generated from Godot docs: XRBodyModifier3D
 */
class XRBodyModifier3D(handle: MemorySegment) : SkeletonModifier3D(handle) {
    var bodyTracker: String
        @JvmName("bodyTrackerProperty")
        get() = getBodyTracker()
        @JvmName("setBodyTrackerProperty")
        set(value) = setBodyTracker(value)

    var bodyUpdate: Long
        @JvmName("bodyUpdateProperty")
        get() = getBodyUpdate()
        @JvmName("setBodyUpdateProperty")
        set(value) = setBodyUpdate(value)

    var boneUpdate: Long
        @JvmName("boneUpdateProperty")
        get() = getBoneUpdate()
        @JvmName("setBoneUpdateProperty")
        set(value) = setBoneUpdate(value)

    fun setBodyTracker(trackerName: String) {
        ObjectCalls.ptrcallWithStringNameArg(setBodyTrackerBind, handle, trackerName)
    }

    fun getBodyTracker(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getBodyTrackerBind, handle)
    }

    fun setBodyUpdate(bodyUpdate: Long) {
        ObjectCalls.ptrcallWithLongArg(setBodyUpdateBind, handle, bodyUpdate)
    }

    fun getBodyUpdate(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBodyUpdateBind, handle)
    }

    fun setBoneUpdate(boneUpdate: Long) {
        ObjectCalls.ptrcallWithLongArg(setBoneUpdateBind, handle, boneUpdate)
    }

    fun getBoneUpdate(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBoneUpdateBind, handle)
    }

    companion object {
        const val BODY_UPDATE_UPPER_BODY: Long = 1L
        const val BODY_UPDATE_LOWER_BODY: Long = 2L
        const val BODY_UPDATE_HANDS: Long = 4L
        const val BONE_UPDATE_FULL: Long = 0L
        const val BONE_UPDATE_ROTATION_ONLY: Long = 1L
        const val BONE_UPDATE_MAX: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): XRBodyModifier3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): XRBodyModifier3D? =
            if (handle.address() == 0L) null else XRBodyModifier3D(handle)

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

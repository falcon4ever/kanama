package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRRenderModelManager
 */
class OpenXRRenderModelManager(handle: MemorySegment) : Node3D(handle) {
    var tracker: Long
        @JvmName("trackerProperty")
        get() = getTracker()
        @JvmName("setTrackerProperty")
        set(value) = setTracker(value)

    var makeLocalToPose: String
        @JvmName("makeLocalToPoseProperty")
        get() = getMakeLocalToPose()
        @JvmName("setMakeLocalToPoseProperty")
        set(value) = setMakeLocalToPose(value)

    fun getTracker(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTrackerBind, handle)
    }

    fun setTracker(tracker: Long) {
        ObjectCalls.ptrcallWithLongArg(setTrackerBind, handle, tracker)
    }

    fun getMakeLocalToPose(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getMakeLocalToPoseBind, handle)
    }

    fun setMakeLocalToPose(makeLocalToPose: String) {
        ObjectCalls.ptrcallWithStringArg(setMakeLocalToPoseBind, handle, makeLocalToPose)
    }

    object Signals {
        const val renderModelAdded: String = "render_model_added"
        const val renderModelRemoved: String = "render_model_removed"
    }

    companion object {
        const val RENDER_MODEL_TRACKER_ANY: Long = 0L
        const val RENDER_MODEL_TRACKER_NONE_SET: Long = 1L
        const val RENDER_MODEL_TRACKER_LEFT_HAND: Long = 2L
        const val RENDER_MODEL_TRACKER_RIGHT_HAND: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRRenderModelManager? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRRenderModelManager? =
            if (handle.address() == 0L) null else OpenXRRenderModelManager(handle)

        private const val GET_TRACKER_HASH = 2456466356L
        private val getTrackerBind by lazy {
            ObjectCalls.getMethodBind("OpenXRRenderModelManager", "get_tracker", GET_TRACKER_HASH)
        }

        private const val SET_TRACKER_HASH = 2814627380L
        private val setTrackerBind by lazy {
            ObjectCalls.getMethodBind("OpenXRRenderModelManager", "set_tracker", SET_TRACKER_HASH)
        }

        private const val GET_MAKE_LOCAL_TO_POSE_HASH = 201670096L
        private val getMakeLocalToPoseBind by lazy {
            ObjectCalls.getMethodBind("OpenXRRenderModelManager", "get_make_local_to_pose", GET_MAKE_LOCAL_TO_POSE_HASH)
        }

        private const val SET_MAKE_LOCAL_TO_POSE_HASH = 83702148L
        private val setMakeLocalToPoseBind by lazy {
            ObjectCalls.getMethodBind("OpenXRRenderModelManager", "set_make_local_to_pose", SET_MAKE_LOCAL_TO_POSE_HASH)
        }
    }
}

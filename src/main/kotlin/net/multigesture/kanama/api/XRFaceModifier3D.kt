package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

/**
 * A node for driving standard face meshes from `XRFaceTracker` weights.
 *
 * Generated from Godot docs: XRFaceModifier3D
 */
class XRFaceModifier3D(handle: MemorySegment) : Node3D(handle) {
    var faceTracker: String
        @JvmName("faceTrackerProperty")
        get() = getFaceTracker()
        @JvmName("setFaceTrackerProperty")
        set(value) = setFaceTracker(value)

    var target: NodePath
        @JvmName("targetProperty")
        get() = getTarget()
        @JvmName("setTargetProperty")
        set(value) = setTarget(value)

    fun setFaceTracker(trackerName: String) {
        ObjectCalls.ptrcallWithStringNameArg(setFaceTrackerBind, handle, trackerName)
    }

    fun getFaceTracker(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getFaceTrackerBind, handle)
    }

    fun setTarget(target: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setTargetBind, handle, target)
    }

    fun getTarget(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getTargetBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): XRFaceModifier3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): XRFaceModifier3D? =
            if (handle.address() == 0L) null else XRFaceModifier3D(handle)

        private const val SET_FACE_TRACKER_HASH = 3304788590L
        private val setFaceTrackerBind by lazy {
            ObjectCalls.getMethodBind("XRFaceModifier3D", "set_face_tracker", SET_FACE_TRACKER_HASH)
        }

        private const val GET_FACE_TRACKER_HASH = 2002593661L
        private val getFaceTrackerBind by lazy {
            ObjectCalls.getMethodBind("XRFaceModifier3D", "get_face_tracker", GET_FACE_TRACKER_HASH)
        }

        private const val SET_TARGET_HASH = 1348162250L
        private val setTargetBind by lazy {
            ObjectCalls.getMethodBind("XRFaceModifier3D", "set_target", SET_TARGET_HASH)
        }

        private const val GET_TARGET_HASH = 4075236667L
        private val getTargetBind by lazy {
            ObjectCalls.getMethodBind("XRFaceModifier3D", "get_target", GET_TARGET_HASH)
        }
    }
}

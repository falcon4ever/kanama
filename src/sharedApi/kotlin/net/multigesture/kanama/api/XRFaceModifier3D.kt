package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath

/**
 * A node for driving standard face meshes from `XRFaceTracker` weights.
 *
 * Generated from Godot docs: XRFaceModifier3D
 */
class XRFaceModifier3D(handle: GodotHandle) : Node3D(handle) {
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

    /**
     * The `XRFaceTracker` path.
     *
     * Generated from Godot docs: XRFaceModifier3D.set_face_tracker
     */
    fun setFaceTracker(trackerName: String) {
        ObjectCalls.ptrcallWithStringNameArg(setFaceTrackerBind, segment, trackerName)
    }

    /**
     * The `XRFaceTracker` path.
     *
     * Generated from Godot docs: XRFaceModifier3D.get_face_tracker
     */
    fun getFaceTracker(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getFaceTrackerBind, segment)
    }

    /**
     * The `NodePath` of the face `MeshInstance3D`.
     *
     * Generated from Godot docs: XRFaceModifier3D.set_target
     */
    fun setTarget(target: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setTargetBind, segment, target)
    }

    /**
     * The `NodePath` of the face `MeshInstance3D`.
     *
     * Generated from Godot docs: XRFaceModifier3D.get_target
     */
    fun getTarget(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getTargetBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): XRFaceModifier3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): XRFaceModifier3D? =
            if (handle.address() == 0L) null else XRFaceModifier3D(GodotHandle(handle))

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

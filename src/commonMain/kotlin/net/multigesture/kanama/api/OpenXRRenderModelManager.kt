package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Helper node that will automatically manage displaying render models.
 *
 * Generated from Godot docs: OpenXRRenderModelManager
 */
class OpenXRRenderModelManager(handle: GodotHandle) : Node3D(handle) {
    var tracker: OpenXRRenderModelManager.RenderModelTracker
        @JvmName("trackerProperty")
        get() = getTracker()
        @JvmName("setTrackerProperty")
        set(value) = setTracker(value)

    var makeLocalToPose: String
        @JvmName("makeLocalToPoseProperty")
        get() = getMakeLocalToPose()
        @JvmName("setMakeLocalToPoseProperty")
        set(value) = setMakeLocalToPose(value)

    /**
     * Limits render models to the specified tracker. Include: 0 = All render models, 1 = Render models
     * not related to a tracker, 2 = Render models related to the left hand tracker, 3 = Render models
     * related to the right hand tracker.
     *
     * Generated from Godot docs: OpenXRRenderModelManager.get_tracker
     */
    fun getTracker(): OpenXRRenderModelManager.RenderModelTracker {
        return OpenXRRenderModelManager.RenderModelTracker(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTrackerBind, segment))
    }

    /**
     * Limits render models to the specified tracker. Include: 0 = All render models, 1 = Render models
     * not related to a tracker, 2 = Render models related to the left hand tracker, 3 = Render models
     * related to the right hand tracker.
     *
     * Generated from Godot docs: OpenXRRenderModelManager.set_tracker
     */
    fun setTracker(tracker: OpenXRRenderModelManager.RenderModelTracker) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTrackerBind, segment, tracker.value)
    }

    /**
     * Position render models local to this pose (this will adjust the position of the render models
     * container node).
     *
     * Generated from Godot docs: OpenXRRenderModelManager.get_make_local_to_pose
     */
    fun getMakeLocalToPose(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getMakeLocalToPoseBind, segment)
    }

    /**
     * Position render models local to this pose (this will adjust the position of the render models
     * container node).
     *
     * Generated from Godot docs: OpenXRRenderModelManager.set_make_local_to_pose
     */
    fun setMakeLocalToPose(makeLocalToPose: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setMakeLocalToPoseBind, segment, makeLocalToPose)
    }

    /** Signal `render_model_added(render_model: OpenXRRenderModel)`; see [TypedSignal]. */
    val renderModelAdded: Signal1<OpenXRRenderModel>
        @JvmName("renderModelAddedTypedSignal")
        get() = Signal1(this, "render_model_added", SignalArgType.objectOf("OpenXRRenderModel") { OpenXRRenderModel(it) })

    /** Signal `render_model_removed(render_model: OpenXRRenderModel)`; see [TypedSignal]. */
    val renderModelRemoved: Signal1<OpenXRRenderModel>
        @JvmName("renderModelRemovedTypedSignal")
        get() = Signal1(this, "render_model_removed", SignalArgType.objectOf("OpenXRRenderModel") { OpenXRRenderModel(it) })

    object Signals {
        const val renderModelAdded: String = "render_model_added"
        const val renderModelRemoved: String = "render_model_removed"
    }

    /**
     * Godot's `OpenXRRenderModelManager.RenderModelTracker` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`OpenXRRenderModelManager.RenderModelTracker.<NAME>`).
     *
     * Generated from Godot docs: OpenXRRenderModelManager.RenderModelTracker
     */
    @JvmInline
    value class RenderModelTracker(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * All active render models are shown regardless of what tracker they relate to.
             *
             * Generated from Godot docs: OpenXRRenderModelManager.RENDER_MODEL_TRACKER_ANY
             */
            val ANY: RenderModelTracker get() = RenderModelTracker(0L)
            /**
             * Only active render models are shown that are not related to any tracker we manage.
             *
             * Generated from Godot docs: OpenXRRenderModelManager.RENDER_MODEL_TRACKER_NONE_SET
             */
            val NONE_SET: RenderModelTracker get() = RenderModelTracker(1L)
            /**
             * Only active render models are shown that are related to the left hand tracker.
             *
             * Generated from Godot docs: OpenXRRenderModelManager.RENDER_MODEL_TRACKER_LEFT_HAND
             */
            val LEFT_HAND: RenderModelTracker get() = RenderModelTracker(2L)
            /**
             * Only active render models are shown that are related to the right hand tracker.
             *
             * Generated from Godot docs: OpenXRRenderModelManager.RENDER_MODEL_TRACKER_RIGHT_HAND
             */
            val RIGHT_HAND: RenderModelTracker get() = RenderModelTracker(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRRenderModelManager? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRRenderModelManager? =
            if (handle.address() == 0L) null else OpenXRRenderModelManager(GodotHandle(handle))

        /** A new OpenXRRenderModelManager, GDScript's `OpenXRRenderModelManager.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): OpenXRRenderModelManager =
            OpenXRRenderModelManager(GodotHandle(requireConstructed(ObjectCalls.constructObject("OpenXRRenderModelManager"), "OpenXRRenderModelManager")))
    }

    private object Binds {
        private const val GET_TRACKER_HASH = 2456466356L
        @JvmField
        val getTrackerBind =
            ObjectCalls.getMethodBind("OpenXRRenderModelManager", "get_tracker", GET_TRACKER_HASH)

        private const val SET_TRACKER_HASH = 2814627380L
        @JvmField
        val setTrackerBind =
            ObjectCalls.getMethodBind("OpenXRRenderModelManager", "set_tracker", SET_TRACKER_HASH)

        private const val GET_MAKE_LOCAL_TO_POSE_HASH = 201670096L
        @JvmField
        val getMakeLocalToPoseBind =
            ObjectCalls.getMethodBind("OpenXRRenderModelManager", "get_make_local_to_pose", GET_MAKE_LOCAL_TO_POSE_HASH)

        private const val SET_MAKE_LOCAL_TO_POSE_HASH = 83702148L
        @JvmField
        val setMakeLocalToPoseBind =
            ObjectCalls.getMethodBind("OpenXRRenderModelManager", "set_make_local_to_pose", SET_MAKE_LOCAL_TO_POSE_HASH)
    }
}

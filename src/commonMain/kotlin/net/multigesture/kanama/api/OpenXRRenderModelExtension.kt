package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform3D

/**
 * Generated from Godot docs: OpenXRRenderModelExtension
 */
class OpenXRRenderModelExtension(handle: GodotHandle) : OpenXRExtensionWrapper(handle) {
    fun isActive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isActiveBind, segment)
    }

    fun renderModelCreate(renderModelId: Long): RID {
        return ObjectCalls.ptrcallWithLongArgRetRID(Binds.renderModelCreateBind, segment, renderModelId)
    }

    fun renderModelDestroy(renderModel: RID) {
        ObjectCalls.ptrcallWithRIDArg(Binds.renderModelDestroyBind, segment, renderModel)
    }

    fun renderModelGetAll(): List<RID> {
        return ObjectCalls.ptrcallNoArgsRetRIDList(Binds.renderModelGetAllBind, segment)
    }

    fun renderModelNewSceneInstance(renderModel: RID): Node3D? {
        return Node3D.wrap(ObjectCalls.ptrcallWithRIDArgRetObject(Binds.renderModelNewSceneInstanceBind, segment, renderModel))
    }

    fun renderModelGetSubactionPaths(renderModel: RID): List<String> {
        return ObjectCalls.ptrcallWithRIDArgRetPackedStringList(Binds.renderModelGetSubactionPathsBind, segment, renderModel)
    }

    fun renderModelGetTopLevelPath(renderModel: RID): String {
        return ObjectCalls.ptrcallWithRIDArgRetString(Binds.renderModelGetTopLevelPathBind, segment, renderModel)
    }

    fun renderModelGetConfidence(renderModel: RID): XRPose.TrackingConfidence {
        return XRPose.TrackingConfidence(ObjectCalls.ptrcallWithRIDArgRetLong(Binds.renderModelGetConfidenceBind, segment, renderModel))
    }

    fun renderModelGetRootTransform(renderModel: RID): Transform3D {
        return ObjectCalls.ptrcallWithRIDArgRetTransform3D(Binds.renderModelGetRootTransformBind, segment, renderModel)
    }

    fun renderModelGetAnimatableNodeCount(renderModel: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(Binds.renderModelGetAnimatableNodeCountBind, segment, renderModel)
    }

    fun renderModelGetAnimatableNodeName(renderModel: RID, index: Long): String {
        return ObjectCalls.ptrcallWithRIDAndUInt32ArgRetString(Binds.renderModelGetAnimatableNodeNameBind, segment, renderModel, index)
    }

    fun renderModelIsAnimatableNodeVisible(renderModel: RID, index: Long): Boolean {
        return ObjectCalls.ptrcallWithRIDAndUInt32ArgRetBool(Binds.renderModelIsAnimatableNodeVisibleBind, segment, renderModel, index)
    }

    fun renderModelGetAnimatableNodeTransform(renderModel: RID, index: Long): Transform3D {
        return ObjectCalls.ptrcallWithRIDAndUInt32ArgRetTransform3D(Binds.renderModelGetAnimatableNodeTransformBind, segment, renderModel, index)
    }

    /** Signal `render_model_added(render_model: RID)`; see [TypedSignal]. */
    val renderModelAdded: Signal1<RID>
        @JvmName("renderModelAddedTypedSignal")
        get() = Signal1(this, "render_model_added", SignalArgType.valueOf<RID>("RID", RID::class))

    /** Signal `render_model_removed(render_model: RID)`; see [TypedSignal]. */
    val renderModelRemoved: Signal1<RID>
        @JvmName("renderModelRemovedTypedSignal")
        get() = Signal1(this, "render_model_removed", SignalArgType.valueOf<RID>("RID", RID::class))

    /** Signal `render_model_top_level_path_changed(render_model: RID)`; see [TypedSignal]. */
    val renderModelTopLevelPathChanged: Signal1<RID>
        @JvmName("renderModelTopLevelPathChangedTypedSignal")
        get() = Signal1(this, "render_model_top_level_path_changed", SignalArgType.valueOf<RID>("RID", RID::class))

    object Signals {
        const val renderModelAdded: String = "render_model_added"
        const val renderModelRemoved: String = "render_model_removed"
        const val renderModelTopLevelPathChanged: String = "render_model_top_level_path_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRRenderModelExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRRenderModelExtension? =
            if (handle.address() == 0L) null else OpenXRRenderModelExtension(GodotHandle(handle))
    }

    private object Binds {
        private const val IS_ACTIVE_HASH = 36873697L
        @JvmField
        val isActiveBind =
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "is_active", IS_ACTIVE_HASH)

        private const val RENDER_MODEL_CREATE_HASH = 937000113L
        @JvmField
        val renderModelCreateBind =
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_create", RENDER_MODEL_CREATE_HASH)

        private const val RENDER_MODEL_DESTROY_HASH = 2722037293L
        @JvmField
        val renderModelDestroyBind =
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_destroy", RENDER_MODEL_DESTROY_HASH)

        private const val RENDER_MODEL_GET_ALL_HASH = 2915620761L
        @JvmField
        val renderModelGetAllBind =
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_get_all", RENDER_MODEL_GET_ALL_HASH)

        private const val RENDER_MODEL_NEW_SCENE_INSTANCE_HASH = 788010739L
        @JvmField
        val renderModelNewSceneInstanceBind =
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_new_scene_instance", RENDER_MODEL_NEW_SCENE_INSTANCE_HASH)

        private const val RENDER_MODEL_GET_SUBACTION_PATHS_HASH = 2801473409L
        @JvmField
        val renderModelGetSubactionPathsBind =
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_get_subaction_paths", RENDER_MODEL_GET_SUBACTION_PATHS_HASH)

        private const val RENDER_MODEL_GET_TOP_LEVEL_PATH_HASH = 642473191L
        @JvmField
        val renderModelGetTopLevelPathBind =
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_get_top_level_path", RENDER_MODEL_GET_TOP_LEVEL_PATH_HASH)

        private const val RENDER_MODEL_GET_CONFIDENCE_HASH = 2350330949L
        @JvmField
        val renderModelGetConfidenceBind =
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_get_confidence", RENDER_MODEL_GET_CONFIDENCE_HASH)

        private const val RENDER_MODEL_GET_ROOT_TRANSFORM_HASH = 1128465797L
        @JvmField
        val renderModelGetRootTransformBind =
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_get_root_transform", RENDER_MODEL_GET_ROOT_TRANSFORM_HASH)

        private const val RENDER_MODEL_GET_ANIMATABLE_NODE_COUNT_HASH = 2198884583L
        @JvmField
        val renderModelGetAnimatableNodeCountBind =
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_get_animatable_node_count", RENDER_MODEL_GET_ANIMATABLE_NODE_COUNT_HASH)

        private const val RENDER_MODEL_GET_ANIMATABLE_NODE_NAME_HASH = 1464764419L
        @JvmField
        val renderModelGetAnimatableNodeNameBind =
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_get_animatable_node_name", RENDER_MODEL_GET_ANIMATABLE_NODE_NAME_HASH)

        private const val RENDER_MODEL_IS_ANIMATABLE_NODE_VISIBLE_HASH = 3120086654L
        @JvmField
        val renderModelIsAnimatableNodeVisibleBind =
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_is_animatable_node_visible", RENDER_MODEL_IS_ANIMATABLE_NODE_VISIBLE_HASH)

        private const val RENDER_MODEL_GET_ANIMATABLE_NODE_TRANSFORM_HASH = 1050775521L
        @JvmField
        val renderModelGetAnimatableNodeTransformBind =
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_get_animatable_node_transform", RENDER_MODEL_GET_ANIMATABLE_NODE_TRANSFORM_HASH)
    }
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform3D

/**
 * Generated from Godot docs: OpenXRRenderModelExtension
 */
class OpenXRRenderModelExtension(handle: MemorySegment) : OpenXRExtensionWrapper(handle) {
    fun isActive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isActiveBind, handle)
    }

    fun renderModelCreate(renderModelId: Long): RID {
        return ObjectCalls.ptrcallWithLongArgRetRID(renderModelCreateBind, handle, renderModelId)
    }

    fun renderModelDestroy(renderModel: RID) {
        ObjectCalls.ptrcallWithRIDArg(renderModelDestroyBind, handle, renderModel)
    }

    fun renderModelGetAll(): List<RID> {
        return ObjectCalls.ptrcallNoArgsRetRIDList(renderModelGetAllBind, handle)
    }

    fun renderModelNewSceneInstance(renderModel: RID): Node3D? {
        return Node3D.wrap(ObjectCalls.ptrcallWithRIDArgRetObject(renderModelNewSceneInstanceBind, handle, renderModel))
    }

    fun renderModelGetSubactionPaths(renderModel: RID): List<String> {
        return ObjectCalls.ptrcallWithRIDArgRetPackedStringList(renderModelGetSubactionPathsBind, handle, renderModel)
    }

    fun renderModelGetTopLevelPath(renderModel: RID): String {
        return ObjectCalls.ptrcallWithRIDArgRetString(renderModelGetTopLevelPathBind, handle, renderModel)
    }

    fun renderModelGetConfidence(renderModel: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(renderModelGetConfidenceBind, handle, renderModel)
    }

    fun renderModelGetRootTransform(renderModel: RID): Transform3D {
        return ObjectCalls.ptrcallWithRIDArgRetTransform3D(renderModelGetRootTransformBind, handle, renderModel)
    }

    fun renderModelGetAnimatableNodeCount(renderModel: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(renderModelGetAnimatableNodeCountBind, handle, renderModel)
    }

    fun renderModelGetAnimatableNodeName(renderModel: RID, index: Long): String {
        return ObjectCalls.ptrcallWithRIDAndUInt32ArgRetString(renderModelGetAnimatableNodeNameBind, handle, renderModel, index)
    }

    fun renderModelIsAnimatableNodeVisible(renderModel: RID, index: Long): Boolean {
        return ObjectCalls.ptrcallWithRIDAndUInt32ArgRetBool(renderModelIsAnimatableNodeVisibleBind, handle, renderModel, index)
    }

    fun renderModelGetAnimatableNodeTransform(renderModel: RID, index: Long): Transform3D {
        return ObjectCalls.ptrcallWithRIDAndUInt32ArgRetTransform3D(renderModelGetAnimatableNodeTransformBind, handle, renderModel, index)
    }

    object Signals {
        const val renderModelAdded: String = "render_model_added"
        const val renderModelRemoved: String = "render_model_removed"
        const val renderModelTopLevelPathChanged: String = "render_model_top_level_path_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRRenderModelExtension? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRRenderModelExtension? =
            if (handle.address() == 0L) null else OpenXRRenderModelExtension(handle)

        private const val IS_ACTIVE_HASH = 36873697L
        private val isActiveBind by lazy {
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "is_active", IS_ACTIVE_HASH)
        }

        private const val RENDER_MODEL_CREATE_HASH = 937000113L
        private val renderModelCreateBind by lazy {
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_create", RENDER_MODEL_CREATE_HASH)
        }

        private const val RENDER_MODEL_DESTROY_HASH = 2722037293L
        private val renderModelDestroyBind by lazy {
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_destroy", RENDER_MODEL_DESTROY_HASH)
        }

        private const val RENDER_MODEL_GET_ALL_HASH = 2915620761L
        private val renderModelGetAllBind by lazy {
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_get_all", RENDER_MODEL_GET_ALL_HASH)
        }

        private const val RENDER_MODEL_NEW_SCENE_INSTANCE_HASH = 788010739L
        private val renderModelNewSceneInstanceBind by lazy {
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_new_scene_instance", RENDER_MODEL_NEW_SCENE_INSTANCE_HASH)
        }

        private const val RENDER_MODEL_GET_SUBACTION_PATHS_HASH = 2801473409L
        private val renderModelGetSubactionPathsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_get_subaction_paths", RENDER_MODEL_GET_SUBACTION_PATHS_HASH)
        }

        private const val RENDER_MODEL_GET_TOP_LEVEL_PATH_HASH = 642473191L
        private val renderModelGetTopLevelPathBind by lazy {
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_get_top_level_path", RENDER_MODEL_GET_TOP_LEVEL_PATH_HASH)
        }

        private const val RENDER_MODEL_GET_CONFIDENCE_HASH = 2350330949L
        private val renderModelGetConfidenceBind by lazy {
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_get_confidence", RENDER_MODEL_GET_CONFIDENCE_HASH)
        }

        private const val RENDER_MODEL_GET_ROOT_TRANSFORM_HASH = 1128465797L
        private val renderModelGetRootTransformBind by lazy {
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_get_root_transform", RENDER_MODEL_GET_ROOT_TRANSFORM_HASH)
        }

        private const val RENDER_MODEL_GET_ANIMATABLE_NODE_COUNT_HASH = 2198884583L
        private val renderModelGetAnimatableNodeCountBind by lazy {
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_get_animatable_node_count", RENDER_MODEL_GET_ANIMATABLE_NODE_COUNT_HASH)
        }

        private const val RENDER_MODEL_GET_ANIMATABLE_NODE_NAME_HASH = 1464764419L
        private val renderModelGetAnimatableNodeNameBind by lazy {
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_get_animatable_node_name", RENDER_MODEL_GET_ANIMATABLE_NODE_NAME_HASH)
        }

        private const val RENDER_MODEL_IS_ANIMATABLE_NODE_VISIBLE_HASH = 3120086654L
        private val renderModelIsAnimatableNodeVisibleBind by lazy {
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_is_animatable_node_visible", RENDER_MODEL_IS_ANIMATABLE_NODE_VISIBLE_HASH)
        }

        private const val RENDER_MODEL_GET_ANIMATABLE_NODE_TRANSFORM_HASH = 1050775521L
        private val renderModelGetAnimatableNodeTransformBind by lazy {
            ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_get_animatable_node_transform", RENDER_MODEL_GET_ANIMATABLE_NODE_TRANSFORM_HASH)
        }
    }
}

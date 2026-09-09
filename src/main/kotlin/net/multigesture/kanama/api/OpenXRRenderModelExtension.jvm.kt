package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

// GENERATED desktop/Android companion for OpenXRRenderModelExtension (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP OpenXRRenderModelExtension waits on: ptrcallNoArgsRetRIDList,
//   ptrcallWithRIDAndUInt32ArgRetString, ptrcallWithRIDArgRetPackedStringList,
//   ptrcallWithRIDArgRetString
// Index: docs/contributing/ios-shape-gap.md

fun OpenXRRenderModelExtension.renderModelGetAll(): List<RID> {
    return ObjectCalls.ptrcallNoArgsRetRIDList(renderModelGetAllBind, handle)
}

fun OpenXRRenderModelExtension.renderModelGetSubactionPaths(renderModel: RID): List<String> {
    return ObjectCalls.ptrcallWithRIDArgRetPackedStringList(renderModelGetSubactionPathsBind, handle, renderModel)
}

fun OpenXRRenderModelExtension.renderModelGetTopLevelPath(renderModel: RID): String {
    return ObjectCalls.ptrcallWithRIDArgRetString(renderModelGetTopLevelPathBind, handle, renderModel)
}

fun OpenXRRenderModelExtension.renderModelGetAnimatableNodeName(renderModel: RID, index: Long): String {
    return ObjectCalls.ptrcallWithRIDAndUInt32ArgRetString(renderModelGetAnimatableNodeNameBind, handle, renderModel, index)
}

private const val RENDER_MODEL_GET_ALL_HASH = 2915620761L
private val renderModelGetAllBind by lazy {
    ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_get_all", RENDER_MODEL_GET_ALL_HASH)
}

private const val RENDER_MODEL_GET_SUBACTION_PATHS_HASH = 2801473409L
private val renderModelGetSubactionPathsBind by lazy {
    ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_get_subaction_paths", RENDER_MODEL_GET_SUBACTION_PATHS_HASH)
}

private const val RENDER_MODEL_GET_TOP_LEVEL_PATH_HASH = 642473191L
private val renderModelGetTopLevelPathBind by lazy {
    ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_get_top_level_path", RENDER_MODEL_GET_TOP_LEVEL_PATH_HASH)
}

private const val RENDER_MODEL_GET_ANIMATABLE_NODE_NAME_HASH = 1464764419L
private val renderModelGetAnimatableNodeNameBind by lazy {
    ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_get_animatable_node_name", RENDER_MODEL_GET_ANIMATABLE_NODE_NAME_HASH)
}

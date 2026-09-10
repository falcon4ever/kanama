package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

// GENERATED desktop/Android companion for OpenXRRenderModelExtension (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP OpenXRRenderModelExtension waits on: ptrcallNoArgsRetRIDList,
//   ptrcallWithRIDArgRetPackedStringList
// Index: docs/reference/generated/ios-shape-gap.md

fun OpenXRRenderModelExtension.renderModelGetAll(): List<RID> {
    return ObjectCalls.ptrcallNoArgsRetRIDList(renderModelGetAllBind, handle)
}

fun OpenXRRenderModelExtension.renderModelGetSubactionPaths(renderModel: RID): List<String> {
    return ObjectCalls.ptrcallWithRIDArgRetPackedStringList(renderModelGetSubactionPathsBind, handle, renderModel)
}

private const val RENDER_MODEL_GET_ALL_HASH = 2915620761L
private val renderModelGetAllBind by lazy {
    ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_get_all", RENDER_MODEL_GET_ALL_HASH)
}

private const val RENDER_MODEL_GET_SUBACTION_PATHS_HASH = 2801473409L
private val renderModelGetSubactionPathsBind by lazy {
    ObjectCalls.getMethodBind("OpenXRRenderModelExtension", "render_model_get_subaction_paths", RENDER_MODEL_GET_SUBACTION_PATHS_HASH)
}

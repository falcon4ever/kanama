package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

// GENERATED desktop/Android companion for FramebufferCacheRD (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP FramebufferCacheRD waits on: ptrcallWithRIDListObjectListUInt32ArgsRetRID
// Index: docs/contributing/ios-shape-gap.md

/**
 * Creates, or obtains a cached, framebuffer. `textures` lists textures accessed. `passes` defines
 * the subpasses and texture allocation, if left empty a single pass is created and textures are
 * allocated depending on their usage flags. `views` defines the number of views used when
 * rendering.
 *
 * Generated from Godot docs: FramebufferCacheRD.get_cache_multipass
 */
fun FramebufferCacheRD.Companion.getCacheMultipass(textures: List<RID>, passes: List<RDFramebufferPass>, views: Long): RID {
    return ObjectCalls.ptrcallWithRIDListObjectListUInt32ArgsRetRID(getCacheMultipassBind, MemorySegment.NULL, textures, passes, views)
}

private const val GET_CACHE_MULTIPASS_HASH = 3437881813L
private val getCacheMultipassBind by lazy {
    ObjectCalls.getMethodBind("FramebufferCacheRD", "get_cache_multipass", GET_CACHE_MULTIPASS_HASH)
}

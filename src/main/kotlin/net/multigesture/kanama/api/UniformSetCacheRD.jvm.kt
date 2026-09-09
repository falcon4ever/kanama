package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

// GENERATED desktop/Android companion for UniformSetCacheRD (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP UniformSetCacheRD waits on: ptrcallWithRIDUInt32ObjectListArgsRetRID
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Creates/returns a cached uniform set based on the provided uniforms for a given shader.
 *
 * Generated from Godot docs: UniformSetCacheRD.get_cache
 */
fun UniformSetCacheRD.Companion.getCache(shader: RID, set: Long, uniforms: List<RDUniform>): RID {
    return ObjectCalls.ptrcallWithRIDUInt32ObjectListArgsRetRID(getCacheBind, MemorySegment.NULL, shader, set, uniforms)
}

private const val GET_CACHE_HASH = 658571723L
private val getCacheBind by lazy {
    ObjectCalls.getMethodBind("UniformSetCacheRD", "get_cache", GET_CACHE_HASH)
}

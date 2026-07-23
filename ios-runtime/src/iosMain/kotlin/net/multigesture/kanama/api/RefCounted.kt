package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: RefCounted
 */
open class RefCounted(handle: MemorySegment) : GodotObject(handle) {
    fun unreference(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(unreferenceBind, handle)
    }

    fun getReferenceCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getReferenceCountBind, handle)
    }

    // ── Kanama iOS RefCounted ownership (generator custom-section; task 31 mirror) ─────
    // A wrapper returned from a RefCounted-typed ptrcall method owns the +1 reference the
    // engine hands through the return slot (meta:"required" included). close() releases it:
    // unreference() + destroy at zero. Wrappers minted from Variant-path returns or
    // fromHandle casts borrow — do not close those (see wrapper-maintenance.md
    // "RefCounted Return Ownership").
    private var wrapperReferenceReleased = false

    @ManualGodotLifetimeApi
    override fun close() {
        if (wrapperReferenceReleased) return
        wrapperReferenceReleased = true
        if (unreference()) {
            ObjectCalls.destroyObject(handle)
        }
    }

    companion object {
        fun fromHandle(handle: MemorySegment): RefCounted? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RefCounted? =
            if (handle.address() == 0L) null else RefCounted(handle)

        // Releases the +1 return-slot reference carried by `handle` without minting a
        // wrapper — the generated self-return-collapse pattern calls this before
        // returning `this` (task 31 mirror; matches desktop RefCounted.releaseHandle).
        internal fun releaseHandle(handle: MemorySegment) {
            if (handle.address() != 0L && ObjectCalls.ptrcallNoArgsRetBool(unreferenceBind, handle)) {
                ObjectCalls.destroyObject(handle)
            }
        }

        private const val UNREFERENCE_HASH = 2240911060L
        private val unreferenceBind by lazy {
            ObjectCalls.getMethodBind("RefCounted", "unreference", UNREFERENCE_HASH)
        }

        private const val GET_REFERENCE_COUNT_HASH = 3905245786L
        private val getReferenceCountBind by lazy {
            ObjectCalls.getMethodBind("RefCounted", "get_reference_count", GET_REFERENCE_COUNT_HASH)
        }
    }
}

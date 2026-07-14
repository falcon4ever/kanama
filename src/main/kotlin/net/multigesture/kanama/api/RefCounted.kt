package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import java.lang.foreign.MemorySegment

/**
 * Base class for reference-counted objects.
 *
 * Generated from Godot docs: RefCounted
 */
open class RefCounted internal constructor(
    handle: MemorySegment,
) : GodotObject(handle), AutoCloseable {

    private var closed = false
    private var wrapperReferenceReleased = false

    /**
     * Returns the current reference count.
     *
     * Generated from Godot docs: RefCounted.get_reference_count
     */
    fun getReferenceCount(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getReferenceCountBind, handle).toLong()
    }

    internal fun checkOpen() {
        check(!closed) { "RefCounted handle is closed" }
    }

    internal fun requireOpenHandle(): MemorySegment {
        checkOpen()
        return handle
    }

    /**
     * Takes a `+1` reference on the underlying object for a Kotlin wrapper that outlives the call
     * that produced it (e.g. a resource read out of a typed Array/Dictionary, or a script resource
     * retained by `ScriptBridge`). Balanced by [close]. Lifted here from the former standalone
     * `Resource` root so both `RefCounted` and `Resource` share one lifetime policy.
     */
    internal fun retainForKotlinWrapper(): MemorySegment {
        checkOpen()
        ObjectCalls.ptrcallNoArgsRetBool(referenceBind, handle)
        wrapperReferenceReleased = false
        return handle
    }

    /**
     * Releases this Kotlin wrapper's reference to the underlying Godot object.
     *
     * This is a low-level lifetime operation. Do not use it as general gameplay
     * cleanup for live Godot-owned values such as `Tween`, `Tweener`, `Material`,
     * `Mesh`, `PackedScene`, audio streams, or anything assigned to the scene tree
     * or scheduled to run later.
     */
    @ManualGodotLifetimeApi
    override fun close() {
        if (closed || wrapperReferenceReleased) return
        wrapperReferenceReleased = true
        val shouldDestroy = ObjectCalls.ptrcallNoArgsRetBool(unreferenceBind, handle)
        if (shouldDestroy) {
            closed = true
            ObjectCalls.destroyObject(handle)
        }
    }

    companion object {
        private const val NOARGS_LONG_HASH = 3905245786L
        private const val UNREFERENCE_HASH = 2240911060L
        // reference() shares unreference()'s hash: both are bool()-signatured no-arg RefCounted methods.
        private const val REFERENCE_HASH = 2240911060L

        private val getReferenceCountBind by lazy {
            ObjectCalls.getMethodBind("RefCounted", "get_reference_count", NOARGS_LONG_HASH)
        }

        private val unreferenceBind by lazy {
            ObjectCalls.getMethodBind("RefCounted", "unreference", UNREFERENCE_HASH)
        }

        private val referenceBind by lazy {
            ObjectCalls.getMethodBind("RefCounted", "reference", REFERENCE_HASH)
        }

        /**
         * Takes one owning reference on [handle] (RefCounted.reference), keeping the
         * object alive independently of any transient `Ref<>` the engine may create
         * (e.g. inside `ResourceSaver.save`). Mirrors the owned-return convention used
         * for freshly instantiated RefCounted values. Returns true when this was the
         * first reference (refcount went 0 -> 1).
         */
        internal fun retainHandle(handle: MemorySegment): Boolean {
            if (handle.address() == 0L) return false
            return ObjectCalls.ptrcallNoArgsRetBool(referenceBind, handle)
        }

        internal fun releaseHandle(handle: MemorySegment) {
            if (handle.address() != 0L) {
                if (ObjectCalls.ptrcallNoArgsRetBool(unreferenceBind, handle)) {
                    ObjectCalls.destroyObject(handle)
                }
            }
        }
    }
}

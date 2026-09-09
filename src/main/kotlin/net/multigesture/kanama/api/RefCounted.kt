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
     * Releases this Kotlin wrapper's reference to the underlying Godot object:
     * `unreference()`, and destroy only if that dropped the count to zero.
     *
     * Every `RefCounted`-typed return you receive — `create()`, `ResourceLoader.load…`,
     * and plain getters such as `getMesh()` — is a `+1` you own and must close (or `use { }`).
     * Do not close a wrapper you minted yourself over a handle you already had
     * (`fromHandle`/`fromObject`) or a live `Tween` (use `kill()`); see
     * `docs/game-dev/godot-api.md` "Resource Ownership".
     */
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

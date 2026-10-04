package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

// Written once for every backend (task 117 P3′, D20/D22): one lifetime policy, every body a
// ptrcall through `ObjectCalls`. The constructor is internal (D4): constructing a ref-counted wrapper
// from a raw handle without a retain is the ownership footgun `fromHandle` exists to prevent; the
// generated subclasses and the owned-decode paths inside the module keep working.
//
// Ownership (wrapper-maintenance.md "RefCounted Return Ownership"): a wrapper returned from a
// RefCounted-typed ptrcall method owns the +1 reference the engine hands through the return slot
// (meta:"required" included), and close() releases it — unreference() + destroy at zero. Wrappers
// minted from Variant-path returns or fromHandle casts borrow; do not close those.
//
// The KDoc blocks marked "Generated from Godot docs" are owned by sync_kdoc_from_godot_docs.py.
/**
 * Base class for reference-counted objects.
 *
 * Generated from Godot docs: RefCounted
 */
open class RefCounted internal constructor(
    handle: GodotHandle,
) : GodotObject(handle), AutoCloseable {

    private var closed = false
    private var wrapperReferenceReleased = false

    // `Int`, the generator's width mapping for Godot's int32 (task 117 D12/D22); desktop returned
    // `Long` until P3′.
    /**
     * Returns the current reference count.
     *
     * Generated from Godot docs: RefCounted.get_reference_count
     */
    fun getReferenceCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getReferenceCountBind, segment)
    }

    /**
     * Receiver-side use-after-close guard (task 98): every generated method on a RefCounted-derived
     * wrapper calls this first, so a call through a handle whose [close] destroyed the object is an
     * IllegalStateException, not a native fault.
     */
    internal fun checkOpen() {
        check(!closed) { "RefCounted handle is closed" }
    }

    /** Argument-position counterpart of [checkOpen]: the closed-handle check for an argument. */
    internal override fun requireOpenHandle(): RawSegment {
        checkOpen()
        return segment
    }

    /**
     * Takes a `+1` reference on the underlying object for a Kotlin wrapper that outlives the call
     * that produced it (e.g. a resource read out of a typed Array/Dictionary, or a script resource
     * retained by `ScriptBridge`). Balanced by [close]. Lifted here from the former standalone
     * `Resource` root so both `RefCounted` and `Resource` share one lifetime policy.
     */
    internal fun retainForKotlinWrapper(): RawSegment {
        checkOpen()
        ObjectCalls.ptrcallNoArgsRetBool(referenceBind, segment)
        wrapperReferenceReleased = false
        return segment
    }

    /**
     * Releases this Kotlin wrapper's reference to the underlying Godot object:
     * `unreference()`, and destroy only if that dropped the count to zero.
     *
     * Every `RefCounted`-typed return you receive — `create()`, `ResourceLoader.load…`,
     * plain getters such as `getMesh()`, and each element of a returned typed `Array`
     * (`getMaterials()`, `getProcessedTweens()`) — is a `+1` you own and must close (or
     * `use { }`). Do not close a wrapper you minted yourself over a handle you already had
     * (`fromHandle`/`fromObject`). `close()` never stops a `Tween` (the SceneTree holds its own
     * reference while it runs): `kill()` stops it, `close()` releases your wrapper once you no
     * longer call it; see `docs/game-dev/godot-api.md` "Resource Ownership".
     */
    override fun close() {
        if (closed || wrapperReferenceReleased) return
        wrapperReferenceReleased = true
        val shouldDestroy = ObjectCalls.ptrcallNoArgsRetBool(unreferenceBind, segment)
        if (shouldDestroy) {
            closed = true
            ObjectCalls.destroyObject(segment)
        }
    }

    companion object {
        /** A BORROWED view of the object behind [handle] (no retain): never `close()` it. */
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RefCounted? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): RefCounted? =
            if (handle.address() == 0L) null else RefCounted(GodotHandle(handle))

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
        internal fun retainHandle(handle: RawSegment): Boolean {
            if (handle.address() == 0L) return false
            return ObjectCalls.ptrcallNoArgsRetBool(referenceBind, handle)
        }

        /**
         * Releases the `+1` return-slot reference carried by [handle] without minting a wrapper —
         * the generated self-return-collapse pattern calls this before returning `this` (task 31).
         */
        internal fun releaseHandle(handle: RawSegment) {
            if (handle.address() != 0L) {
                if (ObjectCalls.ptrcallNoArgsRetBool(unreferenceBind, handle)) {
                    ObjectCalls.destroyObject(handle)
                }
            }
        }
    }
}

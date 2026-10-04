package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.OwnedReleases
import net.multigesture.kanama.binding.runtime.PendingRelease
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
// Ownership is a constructor fact (task 132 D1): `wrapOwned` (every generated class's companion,
// and [owned] here) builds a wrapper that owns a +1, `wrapBorrowed` / `fromHandle` / `from*` one
// that does not. Only an owned wrapper registers a fallback release (D2, [OwnedReleases]): if it
// becomes unreachable without close(), the GC's cleanup enqueues its raw handle and the main
// thread releases it on the next frame. close() stays the early, deterministic path and cancels
// that cleanup (D3), so a +1 is released exactly once.
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

    // True while this wrapper holds a +1 of its own (task 132 D1): set when it is built over a
    // returned or constructing +1 ([owned], `wrapOwned`) or takes one ([retainForKotlinWrapper],
    // [retained]); cleared when close() releases it. Tracked whether or not the GC fallback is on,
    // so close() on a borrowed view never releases a reference the view did not take.
    private var owned = false

    // The fallback release of that +1 (task 132 D2): null for a borrowed view, once close()
    // released it, while the fallback is off, or for a wrapper built off the main thread.
    // [releaseRegistration] is the platform's cleaner registration (JVM `Cleaner.Cleanable`,
    // Kotlin/Native `Cleaner`): it must live as long as this wrapper.
    private var pendingRelease: PendingRelease? = null
    private var releaseRegistration: Any? = null

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
     * retained by `ScriptBridge`): the wrapper owns it from then on, fallback release included
     * (task 132). Balanced by [close]. A no-op on a wrapper that already owns a reference.
     */
    internal fun retainForKotlinWrapper(): RawSegment {
        checkOpen()
        if (owned) return segment
        ObjectCalls.ptrcallNoArgsRetBool(referenceBind, segment)
        markOwned()
        return segment
    }

    /**
     * Marks this wrapper as the owner of the `+1` its handle carries (task 132 D1) and registers
     * the fallback release (D2) unless the fallback is off or this is not the engine main thread
     * (a wrapper built on a worker may still be in use there when the main thread would drain its
     * release). Idempotent.
     */
    internal fun markOwned() {
        owned = true
        if (pendingRelease != null) return
        val release = OwnedReleases.newRelease(this) ?: return
        pendingRelease = release
        releaseRegistration = OwnedReleases.register(this, release)
    }

    /** True while this wrapper holds a `+1` of its own (tests, diagnostics). */
    internal val isOwned: Boolean
        get() = owned

    // Test seam (OwnedReleases.firePendingForTest): the cleanup action, run now.
    internal fun firePendingForTest(): Boolean {
        val release = pendingRelease ?: return false
        release.fire()
        return true
    }

    /** True while this wrapper owns a `+1` whose fallback release is registered (tests). */
    internal val hasPendingRelease: Boolean
        get() = pendingRelease != null

    /**
     * close()'s cancel of the fallback (task 132 D3). False when the fallback won the race -- the
     * cleaner already queued this +1, so the drain releases it and close() must not. Internal so
     * the JVM unit tests can drive it without an engine.
     */
    internal fun cancelOwnedRelease(): Boolean {
        val release = pendingRelease ?: return true
        val won = release.tryDisarm()
        val registration = releaseRegistration
        pendingRelease = null
        releaseRegistration = null
        OwnedReleases.dropRegistration(registration, won)
        return won
    }

    /**
     * Releases this Kotlin wrapper's reference to the underlying Godot object:
     * `unreference()`, and destroy only if that dropped the count to zero.
     *
     * Every `RefCounted`-typed return you receive — `create()`, `ResourceLoader.load…`,
     * plain getters such as `getMesh()`, each element of a returned typed `Array`
     * (`getMaterials()`, `getProcessedTweens()`) and the `from*` downcasts — holds a `+1`
     * of its own. Close it (or `use { }`) to release it early; one you forget is released
     * after the garbage collector drops the wrapper. On a view built over a handle you
     * already had (`fromHandle`, a wrapper constructor) close() releases nothing: the view
     * took no reference (debug builds warn). `close()` never stops a `Tween` (the
     * SceneTree holds its own reference while it runs): `kill()` stops it, `close()`
     * releases your wrapper once you no longer call it; see `docs/game-dev/godot-api.md`
     * "Resource Ownership".
     */
    override fun close() {
        if (closed) return
        if (!owned) {
            OwnedReleases.warnBorrowedClose(this)
            return
        }
        owned = false
        // Lost the race with the fallback: its queued release is this +1.
        if (!cancelOwnedRelease()) return
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
            wrapBorrowed(handle.segment)

        /** The wrapper of a `+1` the caller hands over (a RefCounted-typed return): owned. */
        internal fun wrapOwned(handle: RawSegment): RefCounted? =
            if (handle.address() == 0L) null else owned(RefCounted(GodotHandle(handle)))

        /** A view of a handle someone else owns: no `+1`, no fallback release. */
        internal fun wrapBorrowed(handle: RawSegment): RefCounted? =
            if (handle.address() == 0L) null else RefCounted(GodotHandle(handle))

        /**
         * [wrapper], marked as the owner of the `+1` its handle carries (task 132 D1): every
         * `wrapOwned` and every RefCounted `create()` goes through here, so an owned wrapper
         * always has its fallback release registered.
         */
        internal fun <T : RefCounted> owned(wrapper: T): T {
            wrapper.markOwned()
            return wrapper
        }

        /**
         * [wrapper] after it took a `+1` of its own (`reference()`): what a `from*` downcast
         * returns, so a view kept in a field keeps the object alive like any other wrapper you can
         * reach (task 132).
         */
        internal fun <T : RefCounted> retained(wrapper: T): T {
            wrapper.retainForKotlinWrapper()
            return wrapper
        }

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
         * True when that was the last reference and the object was destroyed.
         */
        internal fun releaseHandle(handle: RawSegment): Boolean {
            if (handle.address() == 0L) return false
            if (!ObjectCalls.ptrcallNoArgsRetBool(unreferenceBind, handle)) return false
            ObjectCalls.destroyObject(handle)
            return true
        }
    }
}

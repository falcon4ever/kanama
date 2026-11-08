package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Stores the result of a shape motion cast query.
 *
 * Generated from Godot docs: PhysicsCastMotionResult2D
 */
class PhysicsCastMotionResult2D(handle: GodotHandle) : RefCounted(handle) {
    val safeFraction: Double
        @JvmName("safeFractionProperty")
        get() = getSafeFraction()

    val unsafeFraction: Double
        @JvmName("unsafeFractionProperty")
        get() = getUnsafeFraction()

    /**
     * The maximum fraction of the motion that can be made without a collision.
     *
     * Generated from Godot docs: PhysicsCastMotionResult2D.get_safe_fraction
     */
    fun getSafeFraction(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSafeFractionBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The minimum fraction of the distance that must be moved for a collision.
     *
     * Generated from Godot docs: PhysicsCastMotionResult2D.get_unsafe_fraction
     */
    fun getUnsafeFraction(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getUnsafeFractionBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PhysicsCastMotionResult2D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): PhysicsCastMotionResult2D? =
            if (handle.address() == 0L) null else RefCounted.owned(PhysicsCastMotionResult2D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): PhysicsCastMotionResult2D? =
            if (handle.address() == 0L) null else PhysicsCastMotionResult2D(GodotHandle(handle))

        /** A new PhysicsCastMotionResult2D, GDScript's `PhysicsCastMotionResult2D.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): PhysicsCastMotionResult2D =
            RefCounted.owned(PhysicsCastMotionResult2D(GodotHandle(requireConstructed(ObjectCalls.constructObject("PhysicsCastMotionResult2D"), "PhysicsCastMotionResult2D"))))
    }

    private object Binds {
        private const val GET_SAFE_FRACTION_HASH = 1740695150L
        @JvmField
        val getSafeFractionBind =
            ObjectCalls.getMethodBind("PhysicsCastMotionResult2D", "get_safe_fraction", GET_SAFE_FRACTION_HASH)

        private const val GET_UNSAFE_FRACTION_HASH = 1740695150L
        @JvmField
        val getUnsafeFractionBind =
            ObjectCalls.getMethodBind("PhysicsCastMotionResult2D", "get_unsafe_fraction", GET_UNSAFE_FRACTION_HASH)
    }
}

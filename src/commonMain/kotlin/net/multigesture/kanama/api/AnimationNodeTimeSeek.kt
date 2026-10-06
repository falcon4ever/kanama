package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A time-seeking animation node used in `AnimationTree`.
 *
 * Generated from Godot docs: AnimationNodeTimeSeek
 */
class AnimationNodeTimeSeek(handle: GodotHandle) : AnimationNode(handle) {
    var explicitElapse: Boolean
        @JvmName("explicitElapseProperty")
        get() = isExplicitElapse()
        @JvmName("setExplicitElapseProperty")
        set(value) = setExplicitElapse(value)

    /**
     * If `true`, some processes are executed to handle keys between seeks, such as calculating root
     * motion and finding the nearest discrete key.
     *
     * Generated from Godot docs: AnimationNodeTimeSeek.set_explicit_elapse
     */
    fun setExplicitElapse(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setExplicitElapseBind, segment, enable)
    }

    /**
     * If `true`, some processes are executed to handle keys between seeks, such as calculating root
     * motion and finding the nearest discrete key.
     *
     * Generated from Godot docs: AnimationNodeTimeSeek.is_explicit_elapse
     */
    fun isExplicitElapse(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isExplicitElapseBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationNodeTimeSeek? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AnimationNodeTimeSeek? =
            if (handle.address() == 0L) null else RefCounted.owned(AnimationNodeTimeSeek(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AnimationNodeTimeSeek? =
            if (handle.address() == 0L) null else AnimationNodeTimeSeek(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_EXPLICIT_ELAPSE_HASH = 2586408642L
        @JvmField
        val setExplicitElapseBind =
            ObjectCalls.getMethodBind("AnimationNodeTimeSeek", "set_explicit_elapse", SET_EXPLICIT_ELAPSE_HASH)

        private const val IS_EXPLICIT_ELAPSE_HASH = 36873697L
        @JvmField
        val isExplicitElapseBind =
            ObjectCalls.getMethodBind("AnimationNodeTimeSeek", "is_explicit_elapse", IS_EXPLICIT_ELAPSE_HASH)
    }
}

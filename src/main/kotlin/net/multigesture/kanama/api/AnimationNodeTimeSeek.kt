package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A time-seeking animation node used in `AnimationTree`.
 *
 * Generated from Godot docs: AnimationNodeTimeSeek
 */
class AnimationNodeTimeSeek(handle: MemorySegment) : AnimationNode(handle) {
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
        ObjectCalls.ptrcallWithBoolArg(setExplicitElapseBind, handle, enable)
    }

    /**
     * If `true`, some processes are executed to handle keys between seeks, such as calculating root
     * motion and finding the nearest discrete key.
     *
     * Generated from Godot docs: AnimationNodeTimeSeek.is_explicit_elapse
     */
    fun isExplicitElapse(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isExplicitElapseBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AnimationNodeTimeSeek? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AnimationNodeTimeSeek? =
            if (handle.address() == 0L) null else AnimationNodeTimeSeek(handle)

        private const val SET_EXPLICIT_ELAPSE_HASH = 2586408642L
        private val setExplicitElapseBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeTimeSeek", "set_explicit_elapse", SET_EXPLICIT_ELAPSE_HASH)
        }

        private const val IS_EXPLICIT_ELAPSE_HASH = 36873697L
        private val isExplicitElapseBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeTimeSeek", "is_explicit_elapse", IS_EXPLICIT_ELAPSE_HASH)
        }
    }
}

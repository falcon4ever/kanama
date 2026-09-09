package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: AnimationNodeSync
 */
open class AnimationNodeSync(handle: MemorySegment) : AnimationNode(handle) {
    var sync: Boolean
        @JvmName("syncProperty")
        get() = isUsingSync()
        @JvmName("setSyncProperty")
        set(value) = setUseSync(value)

    fun setUseSync(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setUseSyncBind, handle, enable)
    }

    fun isUsingSync(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingSyncBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): AnimationNodeSync? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AnimationNodeSync? =
            if (handle.address() == 0L) null else AnimationNodeSync(handle)

        private const val SET_USE_SYNC_HASH = 2586408642L
        private val setUseSyncBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeSync", "set_use_sync", SET_USE_SYNC_HASH)
        }

        private const val IS_USING_SYNC_HASH = 36873697L
        private val isUsingSyncBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeSync", "is_using_sync", IS_USING_SYNC_HASH)
        }
    }
}

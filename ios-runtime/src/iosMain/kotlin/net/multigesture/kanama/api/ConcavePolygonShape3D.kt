package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: ConcavePolygonShape3D
 */
class ConcavePolygonShape3D(handle: MemorySegment) : Shape3D(handle) {
    var backfaceCollision: Boolean
        @JvmName("backfaceCollisionProperty")
        get() = isBackfaceCollisionEnabled()
        @JvmName("setBackfaceCollisionProperty")
        set(value) = setBackfaceCollisionEnabled(value)

    fun setBackfaceCollisionEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setBackfaceCollisionEnabledBind, handle, enabled)
    }

    fun isBackfaceCollisionEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isBackfaceCollisionEnabledBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): ConcavePolygonShape3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ConcavePolygonShape3D? =
            if (handle.address() == 0L) null else ConcavePolygonShape3D(handle)

        private const val SET_BACKFACE_COLLISION_ENABLED_HASH = 2586408642L
        private val setBackfaceCollisionEnabledBind by lazy {
            ObjectCalls.getMethodBind("ConcavePolygonShape3D", "set_backface_collision_enabled", SET_BACKFACE_COLLISION_ENABLED_HASH)
        }

        private const val IS_BACKFACE_COLLISION_ENABLED_HASH = 36873697L
        private val isBackfaceCollisionEnabledBind by lazy {
            ObjectCalls.getMethodBind("ConcavePolygonShape3D", "is_backface_collision_enabled", IS_BACKFACE_COLLISION_ENABLED_HASH)
        }
    }
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.RID

/**
 * Provides virtual methods that can be overridden to create custom `PhysicsDirectSpaceState2D`
 * implementations.
 *
 * Generated from Godot docs: PhysicsDirectSpaceState2DExtension
 */
class PhysicsDirectSpaceState2DExtension(handle: GodotHandle) : PhysicsDirectSpaceState2D(handle) {
    fun isBodyExcludedFromQuery(body: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(isBodyExcludedFromQueryBind, segment, body)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PhysicsDirectSpaceState2DExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): PhysicsDirectSpaceState2DExtension? =
            if (handle.address() == 0L) null else PhysicsDirectSpaceState2DExtension(GodotHandle(handle))

        private const val IS_BODY_EXCLUDED_FROM_QUERY_HASH = 4155700596L
        private val isBodyExcludedFromQueryBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState2DExtension", "is_body_excluded_from_query", IS_BODY_EXCLUDED_FROM_QUERY_HASH)
        }
    }
}

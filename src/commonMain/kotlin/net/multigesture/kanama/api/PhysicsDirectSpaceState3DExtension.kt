package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.RID

/**
 * Provides virtual methods that can be overridden to create custom `PhysicsDirectSpaceState3D`
 * implementations.
 *
 * Generated from Godot docs: PhysicsDirectSpaceState3DExtension
 */
class PhysicsDirectSpaceState3DExtension(handle: MemorySegment) : PhysicsDirectSpaceState3D(handle) {
    fun isBodyExcludedFromQuery(body: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(isBodyExcludedFromQueryBind, handle, body)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PhysicsDirectSpaceState3DExtension? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicsDirectSpaceState3DExtension? =
            if (handle.address() == 0L) null else PhysicsDirectSpaceState3DExtension(handle)

        private const val IS_BODY_EXCLUDED_FROM_QUERY_HASH = 4155700596L
        private val isBodyExcludedFromQueryBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState3DExtension", "is_body_excluded_from_query", IS_BODY_EXCLUDED_FROM_QUERY_HASH)
        }
    }
}

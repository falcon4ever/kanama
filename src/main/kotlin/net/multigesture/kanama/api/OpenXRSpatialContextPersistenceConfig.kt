package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

/**
 * Generated from Godot docs: OpenXRSpatialContextPersistenceConfig
 */
class OpenXRSpatialContextPersistenceConfig(handle: MemorySegment) : OpenXRStructureBase(handle) {
    fun addPersistenceContext(persistenceContext: RID) {
        ObjectCalls.ptrcallWithRIDArg(addPersistenceContextBind, handle, persistenceContext)
    }

    fun removePersistenceContext(persistenceContext: RID) {
        ObjectCalls.ptrcallWithRIDArg(removePersistenceContextBind, handle, persistenceContext)
    }

    fun getPersistenceContexts(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getPersistenceContextsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRSpatialContextPersistenceConfig? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRSpatialContextPersistenceConfig? =
            if (handle.address() == 0L) null else OpenXRSpatialContextPersistenceConfig(handle)

        private const val ADD_PERSISTENCE_CONTEXT_HASH = 2722037293L
        private val addPersistenceContextBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialContextPersistenceConfig", "add_persistence_context", ADD_PERSISTENCE_CONTEXT_HASH)
        }

        private const val REMOVE_PERSISTENCE_CONTEXT_HASH = 2722037293L
        private val removePersistenceContextBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialContextPersistenceConfig", "remove_persistence_context", REMOVE_PERSISTENCE_CONTEXT_HASH)
        }

        private const val GET_PERSISTENCE_CONTEXTS_HASH = 3995934104L
        private val getPersistenceContextsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialContextPersistenceConfig", "get_persistence_contexts", GET_PERSISTENCE_CONTEXTS_HASH)
        }
    }
}

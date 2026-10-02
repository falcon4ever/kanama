package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID

/**
 * Generated from Godot docs: OpenXRSpatialContextPersistenceConfig
 */
class OpenXRSpatialContextPersistenceConfig(handle: GodotHandle) : OpenXRStructureBase(handle) {
    fun addPersistenceContext(persistenceContext: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(addPersistenceContextBind, segment, persistenceContext)
    }

    fun removePersistenceContext(persistenceContext: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(removePersistenceContextBind, segment, persistenceContext)
    }

    fun getPersistenceContexts(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(getPersistenceContextsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialContextPersistenceConfig? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRSpatialContextPersistenceConfig? =
            if (handle.address() == 0L) null else OpenXRSpatialContextPersistenceConfig(GodotHandle(handle))

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

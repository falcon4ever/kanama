package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID

/**
 * Configuration header for spatial persistence.
 *
 * Generated from Godot docs: OpenXRSpatialContextPersistenceConfig
 */
class OpenXRSpatialContextPersistenceConfig(handle: GodotHandle) : OpenXRStructureBase(handle) {
    /**
     * Adds a persistence context to this configuration. You must add at least one persistence context
     * to create a valid configuration. You can create a persistence context by calling
     * `OpenXRSpatialAnchorCapability.create_persistence_context`.
     *
     * Generated from Godot docs: OpenXRSpatialContextPersistenceConfig.add_persistence_context
     */
    fun addPersistenceContext(persistenceContext: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(Binds.addPersistenceContextBind, segment, persistenceContext)
    }

    /**
     * Removes a persistence context.
     *
     * Generated from Godot docs: OpenXRSpatialContextPersistenceConfig.remove_persistence_context
     */
    fun removePersistenceContext(persistenceContext: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(Binds.removePersistenceContextBind, segment, persistenceContext)
    }

    /**
     * Gets the persistence context(s) (as `RID`s) received by `add_persistence_context`.
     *
     * Generated from Godot docs: OpenXRSpatialContextPersistenceConfig.get_persistence_contexts
     */
    fun getPersistenceContexts(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getPersistenceContextsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialContextPersistenceConfig? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRSpatialContextPersistenceConfig? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRSpatialContextPersistenceConfig(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRSpatialContextPersistenceConfig? =
            if (handle.address() == 0L) null else OpenXRSpatialContextPersistenceConfig(GodotHandle(handle))
    }

    private object Binds {
        private const val ADD_PERSISTENCE_CONTEXT_HASH = 2722037293L
        @JvmField
        val addPersistenceContextBind =
            ObjectCalls.getMethodBind("OpenXRSpatialContextPersistenceConfig", "add_persistence_context", ADD_PERSISTENCE_CONTEXT_HASH)

        private const val REMOVE_PERSISTENCE_CONTEXT_HASH = 2722037293L
        @JvmField
        val removePersistenceContextBind =
            ObjectCalls.getMethodBind("OpenXRSpatialContextPersistenceConfig", "remove_persistence_context", REMOVE_PERSISTENCE_CONTEXT_HASH)

        private const val GET_PERSISTENCE_CONTEXTS_HASH = 3995934104L
        @JvmField
        val getPersistenceContextsBind =
            ObjectCalls.getMethodBind("OpenXRSpatialContextPersistenceConfig", "get_persistence_contexts", GET_PERSISTENCE_CONTEXTS_HASH)
    }
}

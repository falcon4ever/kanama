package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRSpatialComponentPersistenceList
 */
class OpenXRSpatialComponentPersistenceList(handle: GodotHandle) : OpenXRSpatialComponentData(handle) {
    fun getPersistentUuid(index: Long): String {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetString(getPersistentUuidBind, segment, index)
    }

    fun getPersistentState(index: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetLong(getPersistentStateBind, segment, index)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialComponentPersistenceList? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRSpatialComponentPersistenceList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentPersistenceList(GodotHandle(handle))

        private const val GET_PERSISTENT_UUID_HASH = 844755477L
        private val getPersistentUuidBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialComponentPersistenceList", "get_persistent_uuid", GET_PERSISTENT_UUID_HASH)
        }

        private const val GET_PERSISTENT_STATE_HASH = 923996154L
        private val getPersistentStateBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialComponentPersistenceList", "get_persistent_state", GET_PERSISTENT_STATE_HASH)
        }
    }
}

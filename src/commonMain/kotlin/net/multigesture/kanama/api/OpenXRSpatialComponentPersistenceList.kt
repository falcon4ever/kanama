package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Object for storing the query persistence result data.
 *
 * Generated from Godot docs: OpenXRSpatialComponentPersistenceList
 */
class OpenXRSpatialComponentPersistenceList(handle: GodotHandle) : OpenXRSpatialComponentData(handle) {
    /**
     * Returns the persistent uuid for the entity at this `index`.
     *
     * Generated from Godot docs: OpenXRSpatialComponentPersistenceList.get_persistent_uuid
     */
    fun getPersistentUuid(index: Long): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetString(Binds.getPersistentUuidBind, segment, index)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the persistent state (`XrSpatialPersistenceStateEXT`) for the entity at this `index`.
     *
     * Generated from Godot docs: OpenXRSpatialComponentPersistenceList.get_persistent_state
     */
    fun getPersistentState(index: Long): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetLong(Binds.getPersistentStateBind, segment, index)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialComponentPersistenceList? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRSpatialComponentPersistenceList? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRSpatialComponentPersistenceList(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRSpatialComponentPersistenceList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentPersistenceList(GodotHandle(handle))

        /** A new OpenXRSpatialComponentPersistenceList, GDScript's `OpenXRSpatialComponentPersistenceList.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): OpenXRSpatialComponentPersistenceList =
            RefCounted.owned(OpenXRSpatialComponentPersistenceList(GodotHandle(requireConstructed(ObjectCalls.constructObject("OpenXRSpatialComponentPersistenceList"), "OpenXRSpatialComponentPersistenceList"))))
    }

    private object Binds {
        private const val GET_PERSISTENT_UUID_HASH = 844755477L
        @JvmField
        val getPersistentUuidBind =
            ObjectCalls.getMethodBind("OpenXRSpatialComponentPersistenceList", "get_persistent_uuid", GET_PERSISTENT_UUID_HASH)

        private const val GET_PERSISTENT_STATE_HASH = 923996154L
        @JvmField
        val getPersistentStateBind =
            ObjectCalls.getMethodBind("OpenXRSpatialComponentPersistenceList", "get_persistent_state", GET_PERSISTENT_STATE_HASH)
    }
}

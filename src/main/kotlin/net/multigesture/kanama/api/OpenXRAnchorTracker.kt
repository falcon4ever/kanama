package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRAnchorTracker
 */
class OpenXRAnchorTracker(handle: MemorySegment) : OpenXRSpatialEntityTracker(handle) {
    var uuid: String
        @JvmName("uuidProperty")
        get() = getUuid()
        @JvmName("setUuidProperty")
        set(value) = setUuid(value)

    fun hasUuid(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasUuidBind, handle)
    }

    fun setUuid(uuid: String) {
        ObjectCalls.ptrcallWithStringArg(setUuidBind, handle, uuid)
    }

    fun getUuid(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getUuidBind, handle)
    }

    object Signals {
        const val uuidChanged: String = "uuid_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRAnchorTracker? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRAnchorTracker? =
            if (handle.address() == 0L) null else OpenXRAnchorTracker(handle)

        private const val HAS_UUID_HASH = 36873697L
        private val hasUuidBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAnchorTracker", "has_uuid", HAS_UUID_HASH)
        }

        private const val SET_UUID_HASH = 83702148L
        private val setUuidBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAnchorTracker", "set_uuid", SET_UUID_HASH)
        }

        private const val GET_UUID_HASH = 201670096L
        private val getUuidBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAnchorTracker", "get_uuid", GET_UUID_HASH)
        }
    }
}

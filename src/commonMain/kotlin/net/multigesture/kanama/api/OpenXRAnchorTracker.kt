package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRAnchorTracker
 */
class OpenXRAnchorTracker(handle: GodotHandle) : OpenXRSpatialEntityTracker(handle) {
    var uuid: String
        @JvmName("uuidProperty")
        get() = getUuid()
        @JvmName("setUuidProperty")
        set(value) = setUuid(value)

    fun hasUuid(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(hasUuidBind, segment)
    }

    fun setUuid(uuid: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setUuidBind, segment, uuid)
    }

    fun getUuid(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getUuidBind, segment)
    }

    object Signals {
        const val uuidChanged: String = "uuid_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRAnchorTracker? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRAnchorTracker? =
            if (handle.address() == 0L) null else OpenXRAnchorTracker(GodotHandle(handle))

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

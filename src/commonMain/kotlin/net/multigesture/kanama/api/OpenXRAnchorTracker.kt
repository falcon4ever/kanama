package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Positional tracker for our spatial entity anchor extension.
 *
 * Generated from Godot docs: OpenXRAnchorTracker
 */
class OpenXRAnchorTracker(handle: GodotHandle) : OpenXRSpatialEntityTracker(handle) {
    var uuid: String
        @JvmName("uuidProperty")
        get() = getUuid()
        @JvmName("setUuidProperty")
        set(value) = setUuid(value)

    /**
     * Returns `true` if a non-zero UUID is set.
     *
     * Generated from Godot docs: OpenXRAnchorTracker.has_uuid
     */
    fun hasUuid(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.hasUuidBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The UUID provided for persistent anchors.
     *
     * Generated from Godot docs: OpenXRAnchorTracker.set_uuid
     */
    fun setUuid(uuid: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setUuidBind, segment, uuid)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The UUID provided for persistent anchors.
     *
     * Generated from Godot docs: OpenXRAnchorTracker.get_uuid
     */
    fun getUuid(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getUuidBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /** Signal `uuid_changed()`; see [TypedSignal]. */
    val uuidChanged: Signal0
        @JvmName("uuidChangedTypedSignal")
        get() = Signal0(this, "uuid_changed")

    object Signals {
        const val uuidChanged: String = "uuid_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRAnchorTracker? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRAnchorTracker? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRAnchorTracker(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRAnchorTracker? =
            if (handle.address() == 0L) null else OpenXRAnchorTracker(GodotHandle(handle))
    }

    private object Binds {
        private const val HAS_UUID_HASH = 36873697L
        @JvmField
        val hasUuidBind =
            ObjectCalls.getMethodBind("OpenXRAnchorTracker", "has_uuid", HAS_UUID_HASH)

        private const val SET_UUID_HASH = 83702148L
        @JvmField
        val setUuidBind =
            ObjectCalls.getMethodBind("OpenXRAnchorTracker", "set_uuid", SET_UUID_HASH)

        private const val GET_UUID_HASH = 201670096L
        @JvmField
        val getUuidBind =
            ObjectCalls.getMethodBind("OpenXRAnchorTracker", "get_uuid", GET_UUID_HASH)
    }
}

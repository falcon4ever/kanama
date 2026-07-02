package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A tracked object.
 *
 * Generated from Godot docs: XRTracker
 */
open class XRTracker(handle: MemorySegment) : RefCounted(handle) {
    var type: Long
        @JvmName("typeProperty")
        get() = getTrackerType()
        @JvmName("setTypeProperty")
        set(value) = setTrackerType(value)

    var name: String
        @JvmName("nameProperty")
        get() = getTrackerName()
        @JvmName("setNameProperty")
        set(value) = setTrackerName(value)

    var description: String
        @JvmName("descriptionProperty")
        get() = getTrackerDesc()
        @JvmName("setDescriptionProperty")
        set(value) = setTrackerDesc(value)

    fun getTrackerType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTrackerTypeBind, handle)
    }

    fun setTrackerType(type: Long) {
        ObjectCalls.ptrcallWithLongArg(setTrackerTypeBind, handle, type)
    }

    fun getTrackerName(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getTrackerNameBind, handle)
    }

    fun setTrackerName(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(setTrackerNameBind, handle, name)
    }

    fun getTrackerDesc(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTrackerDescBind, handle)
    }

    fun setTrackerDesc(description: String) {
        ObjectCalls.ptrcallWithStringArg(setTrackerDescBind, handle, description)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): XRTracker? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): XRTracker? =
            if (handle.address() == 0L) null else XRTracker(handle)

        private const val GET_TRACKER_TYPE_HASH = 2784508102L
        private val getTrackerTypeBind by lazy {
            ObjectCalls.getMethodBind("XRTracker", "get_tracker_type", GET_TRACKER_TYPE_HASH)
        }

        private const val SET_TRACKER_TYPE_HASH = 3055763575L
        private val setTrackerTypeBind by lazy {
            ObjectCalls.getMethodBind("XRTracker", "set_tracker_type", SET_TRACKER_TYPE_HASH)
        }

        private const val GET_TRACKER_NAME_HASH = 2002593661L
        private val getTrackerNameBind by lazy {
            ObjectCalls.getMethodBind("XRTracker", "get_tracker_name", GET_TRACKER_NAME_HASH)
        }

        private const val SET_TRACKER_NAME_HASH = 3304788590L
        private val setTrackerNameBind by lazy {
            ObjectCalls.getMethodBind("XRTracker", "set_tracker_name", SET_TRACKER_NAME_HASH)
        }

        private const val GET_TRACKER_DESC_HASH = 201670096L
        private val getTrackerDescBind by lazy {
            ObjectCalls.getMethodBind("XRTracker", "get_tracker_desc", GET_TRACKER_DESC_HASH)
        }

        private const val SET_TRACKER_DESC_HASH = 83702148L
        private val setTrackerDescBind by lazy {
            ObjectCalls.getMethodBind("XRTracker", "set_tracker_desc", SET_TRACKER_DESC_HASH)
        }
    }
}

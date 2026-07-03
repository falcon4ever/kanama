package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * An internal editor class intended for keeping the data of unrecognized resources.
 *
 * Generated from Godot docs: MissingResource
 */
class MissingResource(handle: MemorySegment) : Resource(handle) {
    var originalClass: String
        @JvmName("originalClassProperty")
        get() = getOriginalClass()
        @JvmName("setOriginalClassProperty")
        set(value) = setOriginalClass(value)

    var recordingProperties: Boolean
        @JvmName("recordingPropertiesProperty")
        get() = isRecordingProperties()
        @JvmName("setRecordingPropertiesProperty")
        set(value) = setRecordingProperties(value)

    /**
     * The name of the class this resource was supposed to be (see `Object.get_class`).
     *
     * Generated from Godot docs: MissingResource.set_original_class
     */
    fun setOriginalClass(name: String) {
        ObjectCalls.ptrcallWithStringArg(setOriginalClassBind, handle, name)
    }

    /**
     * The name of the class this resource was supposed to be (see `Object.get_class`).
     *
     * Generated from Godot docs: MissingResource.get_original_class
     */
    fun getOriginalClass(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getOriginalClassBind, handle)
    }

    /**
     * If set to `true`, allows new properties to be added on top of the existing ones with
     * `Object.set`.
     *
     * Generated from Godot docs: MissingResource.set_recording_properties
     */
    fun setRecordingProperties(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRecordingPropertiesBind, handle, enable)
    }

    /**
     * If set to `true`, allows new properties to be added on top of the existing ones with
     * `Object.set`.
     *
     * Generated from Godot docs: MissingResource.is_recording_properties
     */
    fun isRecordingProperties(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRecordingPropertiesBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): MissingResource? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): MissingResource? =
            if (handle.address() == 0L) null else MissingResource(handle)

        private const val SET_ORIGINAL_CLASS_HASH = 83702148L
        private val setOriginalClassBind by lazy {
            ObjectCalls.getMethodBind("MissingResource", "set_original_class", SET_ORIGINAL_CLASS_HASH)
        }

        private const val GET_ORIGINAL_CLASS_HASH = 201670096L
        private val getOriginalClassBind by lazy {
            ObjectCalls.getMethodBind("MissingResource", "get_original_class", GET_ORIGINAL_CLASS_HASH)
        }

        private const val SET_RECORDING_PROPERTIES_HASH = 2586408642L
        private val setRecordingPropertiesBind by lazy {
            ObjectCalls.getMethodBind("MissingResource", "set_recording_properties", SET_RECORDING_PROPERTIES_HASH)
        }

        private const val IS_RECORDING_PROPERTIES_HASH = 36873697L
        private val isRecordingPropertiesBind by lazy {
            ObjectCalls.getMethodBind("MissingResource", "is_recording_properties", IS_RECORDING_PROPERTIES_HASH)
        }
    }
}

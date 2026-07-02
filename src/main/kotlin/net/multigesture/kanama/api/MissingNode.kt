package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * An internal editor class intended for keeping the data of unrecognized nodes.
 *
 * Generated from Godot docs: MissingNode
 */
class MissingNode(handle: MemorySegment) : Node(handle) {
    var originalClass: String
        @JvmName("originalClassProperty")
        get() = getOriginalClass()
        @JvmName("setOriginalClassProperty")
        set(value) = setOriginalClass(value)

    var originalScene: String
        @JvmName("originalSceneProperty")
        get() = getOriginalScene()
        @JvmName("setOriginalSceneProperty")
        set(value) = setOriginalScene(value)

    var recordingProperties: Boolean
        @JvmName("recordingPropertiesProperty")
        get() = isRecordingProperties()
        @JvmName("setRecordingPropertiesProperty")
        set(value) = setRecordingProperties(value)

    var recordingSignals: Boolean
        @JvmName("recordingSignalsProperty")
        get() = isRecordingSignals()
        @JvmName("setRecordingSignalsProperty")
        set(value) = setRecordingSignals(value)

    fun setOriginalClass(name: String) {
        ObjectCalls.ptrcallWithStringArg(setOriginalClassBind, handle, name)
    }

    fun getOriginalClass(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getOriginalClassBind, handle)
    }

    fun setOriginalScene(name: String) {
        ObjectCalls.ptrcallWithStringArg(setOriginalSceneBind, handle, name)
    }

    fun getOriginalScene(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getOriginalSceneBind, handle)
    }

    fun setRecordingProperties(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRecordingPropertiesBind, handle, enable)
    }

    fun isRecordingProperties(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRecordingPropertiesBind, handle)
    }

    fun setRecordingSignals(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRecordingSignalsBind, handle, enable)
    }

    fun isRecordingSignals(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRecordingSignalsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): MissingNode? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): MissingNode? =
            if (handle.address() == 0L) null else MissingNode(handle)

        private const val SET_ORIGINAL_CLASS_HASH = 83702148L
        private val setOriginalClassBind by lazy {
            ObjectCalls.getMethodBind("MissingNode", "set_original_class", SET_ORIGINAL_CLASS_HASH)
        }

        private const val GET_ORIGINAL_CLASS_HASH = 201670096L
        private val getOriginalClassBind by lazy {
            ObjectCalls.getMethodBind("MissingNode", "get_original_class", GET_ORIGINAL_CLASS_HASH)
        }

        private const val SET_ORIGINAL_SCENE_HASH = 83702148L
        private val setOriginalSceneBind by lazy {
            ObjectCalls.getMethodBind("MissingNode", "set_original_scene", SET_ORIGINAL_SCENE_HASH)
        }

        private const val GET_ORIGINAL_SCENE_HASH = 201670096L
        private val getOriginalSceneBind by lazy {
            ObjectCalls.getMethodBind("MissingNode", "get_original_scene", GET_ORIGINAL_SCENE_HASH)
        }

        private const val SET_RECORDING_PROPERTIES_HASH = 2586408642L
        private val setRecordingPropertiesBind by lazy {
            ObjectCalls.getMethodBind("MissingNode", "set_recording_properties", SET_RECORDING_PROPERTIES_HASH)
        }

        private const val IS_RECORDING_PROPERTIES_HASH = 36873697L
        private val isRecordingPropertiesBind by lazy {
            ObjectCalls.getMethodBind("MissingNode", "is_recording_properties", IS_RECORDING_PROPERTIES_HASH)
        }

        private const val SET_RECORDING_SIGNALS_HASH = 2586408642L
        private val setRecordingSignalsBind by lazy {
            ObjectCalls.getMethodBind("MissingNode", "set_recording_signals", SET_RECORDING_SIGNALS_HASH)
        }

        private const val IS_RECORDING_SIGNALS_HASH = 36873697L
        private val isRecordingSignalsBind by lazy {
            ObjectCalls.getMethodBind("MissingNode", "is_recording_signals", IS_RECORDING_SIGNALS_HASH)
        }
    }
}

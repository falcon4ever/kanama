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

    /**
     * The name of the class this node was supposed to be (see `Object.get_class`).
     *
     * Generated from Godot docs: MissingNode.set_original_class
     */
    fun setOriginalClass(name: String) {
        ObjectCalls.ptrcallWithStringArg(setOriginalClassBind, handle, name)
    }

    /**
     * The name of the class this node was supposed to be (see `Object.get_class`).
     *
     * Generated from Godot docs: MissingNode.get_original_class
     */
    fun getOriginalClass(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getOriginalClassBind, handle)
    }

    /**
     * Returns the path of the scene this node was instance of originally.
     *
     * Generated from Godot docs: MissingNode.set_original_scene
     */
    fun setOriginalScene(name: String) {
        ObjectCalls.ptrcallWithStringArg(setOriginalSceneBind, handle, name)
    }

    /**
     * Returns the path of the scene this node was instance of originally.
     *
     * Generated from Godot docs: MissingNode.get_original_scene
     */
    fun getOriginalScene(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getOriginalSceneBind, handle)
    }

    /**
     * If `true`, allows new properties to be set along with existing ones. If `false`, only existing
     * properties' values can be set, and new properties cannot be added.
     *
     * Generated from Godot docs: MissingNode.set_recording_properties
     */
    fun setRecordingProperties(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRecordingPropertiesBind, handle, enable)
    }

    /**
     * If `true`, allows new properties to be set along with existing ones. If `false`, only existing
     * properties' values can be set, and new properties cannot be added.
     *
     * Generated from Godot docs: MissingNode.is_recording_properties
     */
    fun isRecordingProperties(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRecordingPropertiesBind, handle)
    }

    /**
     * If `true`, allows new signals to be connected to along with existing ones. If `false`, only
     * existing signals can be connected to, and new signals cannot be added.
     *
     * Generated from Godot docs: MissingNode.set_recording_signals
     */
    fun setRecordingSignals(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRecordingSignalsBind, handle, enable)
    }

    /**
     * If `true`, allows new signals to be connected to along with existing ones. If `false`, only
     * existing signals can be connected to, and new signals cannot be added.
     *
     * Generated from Godot docs: MissingNode.is_recording_signals
     */
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

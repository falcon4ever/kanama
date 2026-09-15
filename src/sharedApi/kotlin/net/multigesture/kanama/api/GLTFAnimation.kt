package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: GLTFAnimation
 */
class GLTFAnimation(handle: GodotHandle) : Resource(handle) {
    var originalName: String
        @JvmName("originalNameProperty")
        get() = getOriginalName()
        @JvmName("setOriginalNameProperty")
        set(value) = setOriginalName(value)

    var loop: Boolean
        @JvmName("loopProperty")
        get() = getLoop()
        @JvmName("setLoopProperty")
        set(value) = setLoop(value)

    fun getOriginalName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getOriginalNameBind, segment)
    }

    fun setOriginalName(originalName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setOriginalNameBind, segment, originalName)
    }

    fun getLoop(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getLoopBind, segment)
    }

    fun setLoop(loop: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setLoopBind, segment, loop)
    }

    fun getAdditionalData(extensionName: String): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getAdditionalDataBind, segment, extensionName)
    }

    fun setAdditionalData(extensionName: String, additionalData: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameAndVariantArg(setAdditionalDataBind, segment, extensionName, additionalData)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFAnimation? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): GLTFAnimation? =
            if (handle.address() == 0L) null else GLTFAnimation(GodotHandle(handle))

        private const val GET_ORIGINAL_NAME_HASH = 2841200299L
        private val getOriginalNameBind by lazy {
            ObjectCalls.getMethodBind("GLTFAnimation", "get_original_name", GET_ORIGINAL_NAME_HASH)
        }

        private const val SET_ORIGINAL_NAME_HASH = 83702148L
        private val setOriginalNameBind by lazy {
            ObjectCalls.getMethodBind("GLTFAnimation", "set_original_name", SET_ORIGINAL_NAME_HASH)
        }

        private const val GET_LOOP_HASH = 36873697L
        private val getLoopBind by lazy {
            ObjectCalls.getMethodBind("GLTFAnimation", "get_loop", GET_LOOP_HASH)
        }

        private const val SET_LOOP_HASH = 2586408642L
        private val setLoopBind by lazy {
            ObjectCalls.getMethodBind("GLTFAnimation", "set_loop", SET_LOOP_HASH)
        }

        private const val GET_ADDITIONAL_DATA_HASH = 2138907829L
        private val getAdditionalDataBind by lazy {
            ObjectCalls.getMethodBind("GLTFAnimation", "get_additional_data", GET_ADDITIONAL_DATA_HASH)
        }

        private const val SET_ADDITIONAL_DATA_HASH = 3776071444L
        private val setAdditionalDataBind by lazy {
            ObjectCalls.getMethodBind("GLTFAnimation", "set_additional_data", SET_ADDITIONAL_DATA_HASH)
        }
    }
}

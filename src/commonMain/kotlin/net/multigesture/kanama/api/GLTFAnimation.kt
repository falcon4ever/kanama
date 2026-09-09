package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: GLTFAnimation
 */
class GLTFAnimation(handle: MemorySegment) : Resource(handle) {
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
        return ObjectCalls.ptrcallNoArgsRetString(getOriginalNameBind, handle)
    }

    fun setOriginalName(originalName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setOriginalNameBind, handle, originalName)
    }

    fun getLoop(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getLoopBind, handle)
    }

    fun setLoop(loop: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setLoopBind, handle, loop)
    }

    fun getAdditionalData(extensionName: String): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getAdditionalDataBind, handle, extensionName)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): GLTFAnimation? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GLTFAnimation? =
            if (handle.address() == 0L) null else GLTFAnimation(handle)

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
    }
}

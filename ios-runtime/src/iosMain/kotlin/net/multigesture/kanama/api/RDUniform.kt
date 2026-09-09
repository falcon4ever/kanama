package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.RID

/**
 * Generated from Godot docs: RDUniform
 */
class RDUniform(handle: MemorySegment) : RefCounted(handle) {
    var uniformType: Long
        @JvmName("uniformTypeProperty")
        get() = getUniformType()
        @JvmName("setUniformTypeProperty")
        set(value) = setUniformType(value)

    var binding: Int
        @JvmName("bindingProperty")
        get() = getBinding()
        @JvmName("setBindingProperty")
        set(value) = setBinding(value)

    fun setUniformType(pMember: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setUniformTypeBind, handle, pMember)
    }

    fun getUniformType(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getUniformTypeBind, handle)
    }

    fun setBinding(pMember: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setBindingBind, handle, pMember)
    }

    fun getBinding(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getBindingBind, handle)
    }

    fun addId(id: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(addIdBind, handle, id)
    }

    fun clearIds() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearIdsBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): RDUniform? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RDUniform? =
            if (handle.address() == 0L) null else RDUniform(handle)

        private const val SET_UNIFORM_TYPE_HASH = 1664894931L
        private val setUniformTypeBind by lazy {
            ObjectCalls.getMethodBind("RDUniform", "set_uniform_type", SET_UNIFORM_TYPE_HASH)
        }

        private const val GET_UNIFORM_TYPE_HASH = 475470040L
        private val getUniformTypeBind by lazy {
            ObjectCalls.getMethodBind("RDUniform", "get_uniform_type", GET_UNIFORM_TYPE_HASH)
        }

        private const val SET_BINDING_HASH = 1286410249L
        private val setBindingBind by lazy {
            ObjectCalls.getMethodBind("RDUniform", "set_binding", SET_BINDING_HASH)
        }

        private const val GET_BINDING_HASH = 3905245786L
        private val getBindingBind by lazy {
            ObjectCalls.getMethodBind("RDUniform", "get_binding", GET_BINDING_HASH)
        }

        private const val ADD_ID_HASH = 2722037293L
        private val addIdBind by lazy {
            ObjectCalls.getMethodBind("RDUniform", "add_id", ADD_ID_HASH)
        }

        private const val CLEAR_IDS_HASH = 3218959716L
        private val clearIdsBind by lazy {
            ObjectCalls.getMethodBind("RDUniform", "clear_ids", CLEAR_IDS_HASH)
        }
    }
}

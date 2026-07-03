package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

/**
 * Shader uniform (used by `RenderingDevice`).
 *
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

    /**
     * The uniform's data type.
     *
     * Generated from Godot docs: RDUniform.set_uniform_type
     */
    fun setUniformType(pMember: Long) {
        ObjectCalls.ptrcallWithLongArg(setUniformTypeBind, handle, pMember)
    }

    /**
     * The uniform's data type.
     *
     * Generated from Godot docs: RDUniform.get_uniform_type
     */
    fun getUniformType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getUniformTypeBind, handle)
    }

    /**
     * The uniform's binding.
     *
     * Generated from Godot docs: RDUniform.set_binding
     */
    fun setBinding(pMember: Int) {
        ObjectCalls.ptrcallWithIntArg(setBindingBind, handle, pMember)
    }

    /**
     * The uniform's binding.
     *
     * Generated from Godot docs: RDUniform.get_binding
     */
    fun getBinding(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBindingBind, handle)
    }

    /**
     * Binds the given id to the uniform. The data associated with the id is then used when the uniform
     * is passed to a shader.
     *
     * Generated from Godot docs: RDUniform.add_id
     */
    fun addId(id: RID) {
        ObjectCalls.ptrcallWithRIDArg(addIdBind, handle, id)
    }

    /**
     * Unbinds all ids currently bound to the uniform.
     *
     * Generated from Godot docs: RDUniform.clear_ids
     */
    fun clearIds() {
        ObjectCalls.ptrcallNoArgs(clearIdsBind, handle)
    }

    /**
     * Returns an array of all ids currently bound to the uniform.
     *
     * Generated from Godot docs: RDUniform.get_ids
     */
    fun getIds(): List<RID> {
        return ObjectCalls.ptrcallNoArgsRetRIDList(getIdsBind, handle)
    }

    companion object {
        @JvmStatic
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

        private const val GET_IDS_HASH = 3995934104L
        private val getIdsBind by lazy {
            ObjectCalls.getMethodBind("RDUniform", "get_ids", GET_IDS_HASH)
        }
    }
}

package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID

/**
 * Shader uniform (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDUniform
 */
class RDUniform(handle: GodotHandle) : RefCounted(handle) {
    var uniformType: RenderingDevice.UniformType
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
    fun setUniformType(pMember: RenderingDevice.UniformType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setUniformTypeBind, segment, pMember.value)
    }

    /**
     * The uniform's data type.
     *
     * Generated from Godot docs: RDUniform.get_uniform_type
     */
    fun getUniformType(): RenderingDevice.UniformType {
        checkOpen()
        return RenderingDevice.UniformType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getUniformTypeBind, segment))
    }

    /**
     * The uniform's binding.
     *
     * Generated from Godot docs: RDUniform.set_binding
     */
    fun setBinding(pMember: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setBindingBind, segment, pMember)
    }

    /**
     * The uniform's binding.
     *
     * Generated from Godot docs: RDUniform.get_binding
     */
    fun getBinding(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBindingBind, segment)
    }

    /**
     * Binds the given id to the uniform. The data associated with the id is then used when the uniform
     * is passed to a shader.
     *
     * Generated from Godot docs: RDUniform.add_id
     */
    fun addId(id: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(Binds.addIdBind, segment, id)
    }

    /**
     * Unbinds all ids currently bound to the uniform.
     *
     * Generated from Godot docs: RDUniform.clear_ids
     */
    fun clearIds() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearIdsBind, segment)
    }

    /**
     * Returns an array of all ids currently bound to the uniform.
     *
     * Generated from Godot docs: RDUniform.get_ids
     */
    fun getIds(): List<RID> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRIDList(Binds.getIdsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RDUniform? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): RDUniform? =
            if (handle.address() == 0L) null else RefCounted.owned(RDUniform(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): RDUniform? =
            if (handle.address() == 0L) null else RDUniform(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_UNIFORM_TYPE_HASH = 1664894931L
        @JvmField
        val setUniformTypeBind =
            ObjectCalls.getMethodBind("RDUniform", "set_uniform_type", SET_UNIFORM_TYPE_HASH)

        private const val GET_UNIFORM_TYPE_HASH = 475470040L
        @JvmField
        val getUniformTypeBind =
            ObjectCalls.getMethodBind("RDUniform", "get_uniform_type", GET_UNIFORM_TYPE_HASH)

        private const val SET_BINDING_HASH = 1286410249L
        @JvmField
        val setBindingBind =
            ObjectCalls.getMethodBind("RDUniform", "set_binding", SET_BINDING_HASH)

        private const val GET_BINDING_HASH = 3905245786L
        @JvmField
        val getBindingBind =
            ObjectCalls.getMethodBind("RDUniform", "get_binding", GET_BINDING_HASH)

        private const val ADD_ID_HASH = 2722037293L
        @JvmField
        val addIdBind =
            ObjectCalls.getMethodBind("RDUniform", "add_id", ADD_ID_HASH)

        private const val CLEAR_IDS_HASH = 3218959716L
        @JvmField
        val clearIdsBind =
            ObjectCalls.getMethodBind("RDUniform", "clear_ids", CLEAR_IDS_HASH)

        private const val GET_IDS_HASH = 3995934104L
        @JvmField
        val getIdsBind =
            ObjectCalls.getMethodBind("RDUniform", "get_ids", GET_IDS_HASH)
    }
}

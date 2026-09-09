package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: Material
 */
open class Material(handle: MemorySegment) : Resource(handle) {
    var renderPriority: Int
        @JvmName("renderPriorityProperty")
        get() = getRenderPriority()
        @JvmName("setRenderPriorityProperty")
        set(value) = setRenderPriority(value)

    var nextPass: Material?
        @JvmName("nextPassProperty")
        get() = getNextPass()
        @JvmName("setNextPassProperty")
        set(value) = setNextPass(value)

    fun setNextPass(nextPass: Material?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setNextPassBind, handle, listOf(nextPass?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getNextPass(): Material? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(getNextPassBind, handle)
        if (ret.address() == handle.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return Material.wrap(ret)
    }

    fun setRenderPriority(priority: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setRenderPriorityBind, handle, priority)
    }

    fun getRenderPriority(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getRenderPriorityBind, handle)
    }

    fun inspectNativeShaderCode() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(inspectNativeShaderCodeBind, handle)
    }

    fun createPlaceholder(): Resource? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(createPlaceholderBind, handle)
        if (ret.address() == handle.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return Resource.wrap(ret)
    }

    companion object {
        const val RENDER_PRIORITY_MAX: Long = 127L
        const val RENDER_PRIORITY_MIN: Long = -128L

        fun fromHandle(handle: MemorySegment): Material? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Material? =
            if (handle.address() == 0L) null else Material(handle)

        // Downcast a Resource to Material (null if not), mirroring the desktop helper.
        fun fromResource(value: Resource?): Material? =
            value?.takeIf { it.isClass("Material") }?.let { Material(it.handle) }

        private const val SET_NEXT_PASS_HASH = 2757459619L
        private val setNextPassBind by lazy {
            ObjectCalls.getMethodBind("Material", "set_next_pass", SET_NEXT_PASS_HASH)
        }

        private const val GET_NEXT_PASS_HASH = 5934680L
        private val getNextPassBind by lazy {
            ObjectCalls.getMethodBind("Material", "get_next_pass", GET_NEXT_PASS_HASH)
        }

        private const val SET_RENDER_PRIORITY_HASH = 1286410249L
        private val setRenderPriorityBind by lazy {
            ObjectCalls.getMethodBind("Material", "set_render_priority", SET_RENDER_PRIORITY_HASH)
        }

        private const val GET_RENDER_PRIORITY_HASH = 3905245786L
        private val getRenderPriorityBind by lazy {
            ObjectCalls.getMethodBind("Material", "get_render_priority", GET_RENDER_PRIORITY_HASH)
        }

        private const val INSPECT_NATIVE_SHADER_CODE_HASH = 3218959716L
        private val inspectNativeShaderCodeBind by lazy {
            ObjectCalls.getMethodBind("Material", "inspect_native_shader_code", INSPECT_NATIVE_SHADER_CODE_HASH)
        }

        private const val CREATE_PLACEHOLDER_HASH = 121922552L
        private val createPlaceholderBind by lazy {
            ObjectCalls.getMethodBind("Material", "create_placeholder", CREATE_PLACEHOLDER_HASH)
        }
    }
}

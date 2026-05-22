package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName

/**
 * Virtual base class for applying visual properties to an object, such as color and roughness.
 *
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

    /**
     * Sets the `Material` to be used for the next pass. This renders the object again using a
     * different material. Note: `next_pass` materials are not necessarily drawn immediately after the
     * source `Material`. Draw order is determined by material properties, `render_priority`, and
     * distance to camera. Note: This only applies to `StandardMaterial3D`s and `ShaderMaterial`s with
     * type "Spatial".
     *
     * Generated from Godot docs: Material.set_next_pass
     */
    fun setNextPass(nextPass: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(setNextPassBind, handle, listOf(nextPass?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Sets the `Material` to be used for the next pass. This renders the object again using a
     * different material. Note: `next_pass` materials are not necessarily drawn immediately after the
     * source `Material`. Draw order is determined by material properties, `render_priority`, and
     * distance to camera. Note: This only applies to `StandardMaterial3D`s and `ShaderMaterial`s with
     * type "Spatial".
     *
     * Generated from Godot docs: Material.get_next_pass
     */
    fun getNextPass(): Material? {
        return Material.wrap(ObjectCalls.ptrcallNoArgsRetObject(getNextPassBind, handle))
    }

    /**
     * Sets the render priority for objects in 3D scenes. Higher priority objects will be sorted in
     * front of lower priority objects. In other words, all objects with `render_priority` `1` will
     * render on top of all objects with `render_priority` `0`. Note: This only applies to
     * `StandardMaterial3D`s and `ShaderMaterial`s with type "Spatial". Note: This will not impact how
     * transparent objects are sorted relative to opaque objects or how dynamic meshes will be sorted
     * relative to other opaque meshes. This is because all transparent objects are drawn after all
     * opaque objects and all dynamic opaque meshes are drawn before other opaque meshes.
     *
     * Generated from Godot docs: Material.set_render_priority
     */
    fun setRenderPriority(priority: Int) {
        ObjectCalls.ptrcallWithIntArg(setRenderPriorityBind, handle, priority)
    }

    /**
     * Sets the render priority for objects in 3D scenes. Higher priority objects will be sorted in
     * front of lower priority objects. In other words, all objects with `render_priority` `1` will
     * render on top of all objects with `render_priority` `0`. Note: This only applies to
     * `StandardMaterial3D`s and `ShaderMaterial`s with type "Spatial". Note: This will not impact how
     * transparent objects are sorted relative to opaque objects or how dynamic meshes will be sorted
     * relative to other opaque meshes. This is because all transparent objects are drawn after all
     * opaque objects and all dynamic opaque meshes are drawn before other opaque meshes.
     *
     * Generated from Godot docs: Material.get_render_priority
     */
    fun getRenderPriority(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getRenderPriorityBind, handle)
    }

    /**
     * Only available when running in the editor. Opens a popup that visualizes the generated shader
     * code, including all variants and internal shader code. See also
     * `Shader.inspect_native_shader_code`.
     *
     * Generated from Godot docs: Material.inspect_native_shader_code
     */
    fun inspectNativeShaderCode() {
        ObjectCalls.ptrcallNoArgs(inspectNativeShaderCodeBind, handle)
    }

    /**
     * Creates a placeholder version of this resource (`PlaceholderMaterial`).
     *
     * Generated from Godot docs: Material.create_placeholder
     */
    fun createPlaceholder(): Resource? {
        return Resource.wrap(ObjectCalls.ptrcallNoArgsRetObject(createPlaceholderBind, handle))
    }

    companion object {
        const val RENDER_PRIORITY_MAX: Long = 127L
        const val RENDER_PRIORITY_MIN: Long = -128L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Material? =
            wrap(handle)

        @JvmStatic
        fun fromResource(value: Resource?): Material? =
            value?.takeIf { it.isClass("Material") }?.let { Material(it.handle) }

        internal fun wrap(handle: MemorySegment): Material? =
            if (handle.address() == 0L) null else Material(handle)

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

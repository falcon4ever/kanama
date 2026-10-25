package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector3

/**
 * A CSG Box shape.
 *
 * Generated from Godot docs: CSGBox3D
 */
class CSGBox3D(handle: GodotHandle) : CSGPrimitive3D(handle) {
    var size: Vector3
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    var material: Material?
        @JvmName("materialProperty")
        get() = getMaterial()
        @JvmName("setMaterialProperty")
        set(value) = setMaterial(value)

    /**
     * The box's width, height and depth.
     *
     * Generated from Godot docs: CSGBox3D.set_size
     */
    fun setSize(size: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(Binds.setSizeBind, segment, size)
    }

    /**
     * The box's width, height and depth.
     *
     * Generated from Godot docs: CSGBox3D.get_size
     */
    fun getSize(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getSizeBind, segment)
    }

    /**
     * The material used to render the box.
     *
     * Generated from Godot docs: CSGBox3D.set_material
     */
    fun setMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setMaterialBind, segment, listOf(material?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(material)
    }

    /**
     * The material used to render the box.
     *
     * Generated from Godot docs: CSGBox3D.get_material
     */
    fun getMaterial(): Material? {
        return Material.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getMaterialBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CSGBox3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CSGBox3D? =
            if (handle.address() == 0L) null else CSGBox3D(GodotHandle(handle))

        /** A new CSGBox3D, GDScript's `CSGBox3D.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): CSGBox3D =
            CSGBox3D(GodotHandle(ObjectCalls.constructObject("CSGBox3D")))
    }

    private object Binds {
        private const val SET_SIZE_HASH = 3460891852L
        @JvmField
        val setSizeBind =
            ObjectCalls.getMethodBind("CSGBox3D", "set_size", SET_SIZE_HASH)

        private const val GET_SIZE_HASH = 3360562783L
        @JvmField
        val getSizeBind =
            ObjectCalls.getMethodBind("CSGBox3D", "get_size", GET_SIZE_HASH)

        private const val SET_MATERIAL_HASH = 2757459619L
        @JvmField
        val setMaterialBind =
            ObjectCalls.getMethodBind("CSGBox3D", "set_material", SET_MATERIAL_HASH)

        private const val GET_MATERIAL_HASH = 5934680L
        @JvmField
        val getMaterialBind =
            ObjectCalls.getMethodBind("CSGBox3D", "get_material", GET_MATERIAL_HASH)
    }
}

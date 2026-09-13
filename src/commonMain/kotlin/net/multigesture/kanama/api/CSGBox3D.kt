package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector3

/**
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

    fun setSize(size: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setSizeBind, segment, size)
    }

    fun getSize(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getSizeBind, segment)
    }

    fun setMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(setMaterialBind, segment, listOf(material?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getMaterial(): Material? {
        return Material.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMaterialBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CSGBox3D? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): CSGBox3D? =
            if (handle.address() == 0L) null else CSGBox3D(GodotHandle(handle))

        private const val SET_SIZE_HASH = 3460891852L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("CSGBox3D", "set_size", SET_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 3360562783L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("CSGBox3D", "get_size", GET_SIZE_HASH)
        }

        private const val SET_MATERIAL_HASH = 2757459619L
        private val setMaterialBind by lazy {
            ObjectCalls.getMethodBind("CSGBox3D", "set_material", SET_MATERIAL_HASH)
        }

        private const val GET_MATERIAL_HASH = 5934680L
        private val getMaterialBind by lazy {
            ObjectCalls.getMethodBind("CSGBox3D", "get_material", GET_MATERIAL_HASH)
        }
    }
}

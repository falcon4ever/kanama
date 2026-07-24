package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3
import java.lang.foreign.MemorySegment

/**
 * A 3D box shape used for physics collision.
 *
 * Generated from Godot docs: BoxShape3D
 */
class BoxShape3D internal constructor(handle: MemorySegment) : Shape3D(handle) {

    /**
     * The box's width, height and depth.
     *
     * Generated from Godot docs: BoxShape3D.set_size
     */
    fun setSize(size: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(setSizeBind, handle, size)
    }

    /**
     * The box's width, height and depth.
     *
     * Generated from Godot docs: BoxShape3D.get_size
     */
    fun getSize(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(getSizeBind, handle)
    }

    companion object {
        private const val VECTOR3_VOID_HASH = 3460891852L
        private const val NOARGS_VECTOR3_HASH = 3360562783L

        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("BoxShape3D", "set_size", VECTOR3_VOID_HASH)
        }

        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("BoxShape3D", "get_size", NOARGS_VECTOR3_HASH)
        }

        @JvmStatic
        fun create(): BoxShape3D =
            BoxShape3D(ObjectCalls.constructObject("BoxShape3D"))

        @JvmStatic
        fun fromResource(value: Resource): BoxShape3D? =
            if (value.isClass("BoxShape3D")) BoxShape3D(value.handle) else null

    }
}

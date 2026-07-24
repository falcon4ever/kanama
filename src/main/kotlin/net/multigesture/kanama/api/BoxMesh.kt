package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3
import java.lang.foreign.MemorySegment

/**
 * Generate an axis-aligned box `PrimitiveMesh`.
 *
 * Generated from Godot docs: BoxMesh
 */
class BoxMesh internal constructor(handle: MemorySegment) : PrimitiveMesh(handle) {

    /**
     * The box's width, height and depth.
     *
     * Generated from Godot docs: BoxMesh.set_size
     */
    fun setSize(size: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(setSizeBind, handle, size)
    }

    /**
     * The box's width, height and depth.
     *
     * Generated from Godot docs: BoxMesh.get_size
     */
    fun getSize(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(getSizeBind, handle)
    }

    /**
     * Number of extra edge loops inserted along the X axis.
     *
     * Generated from Godot docs: BoxMesh.set_subdivide_width
     */
    fun setSubdivideWidth(subdivideWidth: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setSubdivideWidthBind, handle, subdivideWidth.toInt())
    }

    /**
     * Number of extra edge loops inserted along the X axis.
     *
     * Generated from Godot docs: BoxMesh.get_subdivide_width
     */
    fun getSubdivideWidth(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSubdivideWidthBind, handle).toLong()
    }

    /**
     * Number of extra edge loops inserted along the Y axis.
     *
     * Generated from Godot docs: BoxMesh.set_subdivide_height
     */
    fun setSubdivideHeight(subdivideHeight: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setSubdivideHeightBind, handle, subdivideHeight.toInt())
    }

    /**
     * Number of extra edge loops inserted along the Y axis.
     *
     * Generated from Godot docs: BoxMesh.get_subdivide_height
     */
    fun getSubdivideHeight(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSubdivideHeightBind, handle).toLong()
    }

    /**
     * Number of extra edge loops inserted along the Z axis.
     *
     * Generated from Godot docs: BoxMesh.set_subdivide_depth
     */
    fun setSubdivideDepth(subdivideDepth: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setSubdivideDepthBind, handle, subdivideDepth.toInt())
    }

    /**
     * Number of extra edge loops inserted along the Z axis.
     *
     * Generated from Godot docs: BoxMesh.get_subdivide_depth
     */
    fun getSubdivideDepth(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSubdivideDepthBind, handle).toLong()
    }

    companion object {
        private const val VECTOR3_VOID_HASH = 3460891852L
        private const val NOARGS_VECTOR3_HASH = 3360562783L
        private const val LONG_VOID_HASH = 1286410249L
        private const val NOARGS_LONG_HASH = 3905245786L

        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("BoxMesh", "set_size", VECTOR3_VOID_HASH)
        }

        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("BoxMesh", "get_size", NOARGS_VECTOR3_HASH)
        }

        private val setSubdivideWidthBind by lazy {
            ObjectCalls.getMethodBind("BoxMesh", "set_subdivide_width", LONG_VOID_HASH)
        }

        private val getSubdivideWidthBind by lazy {
            ObjectCalls.getMethodBind("BoxMesh", "get_subdivide_width", NOARGS_LONG_HASH)
        }

        private val setSubdivideHeightBind by lazy {
            ObjectCalls.getMethodBind("BoxMesh", "set_subdivide_height", LONG_VOID_HASH)
        }

        private val getSubdivideHeightBind by lazy {
            ObjectCalls.getMethodBind("BoxMesh", "get_subdivide_height", NOARGS_LONG_HASH)
        }

        private val setSubdivideDepthBind by lazy {
            ObjectCalls.getMethodBind("BoxMesh", "set_subdivide_depth", LONG_VOID_HASH)
        }

        private val getSubdivideDepthBind by lazy {
            ObjectCalls.getMethodBind("BoxMesh", "get_subdivide_depth", NOARGS_LONG_HASH)
        }

        @JvmStatic
        fun create(): BoxMesh =
            BoxMesh(ObjectCalls.constructObject("BoxMesh"))

    }
}

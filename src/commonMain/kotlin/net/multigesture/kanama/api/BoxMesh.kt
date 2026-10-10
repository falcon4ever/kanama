package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector3

/**
 * Generate an axis-aligned box `PrimitiveMesh`.
 *
 * Generated from Godot docs: BoxMesh
 */
class BoxMesh(handle: GodotHandle) : PrimitiveMesh(handle) {
    var size: Vector3
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    var subdivideWidth: Int
        @JvmName("subdivideWidthProperty")
        get() = getSubdivideWidth()
        @JvmName("setSubdivideWidthProperty")
        set(value) = setSubdivideWidth(value)

    var subdivideHeight: Int
        @JvmName("subdivideHeightProperty")
        get() = getSubdivideHeight()
        @JvmName("setSubdivideHeightProperty")
        set(value) = setSubdivideHeight(value)

    var subdivideDepth: Int
        @JvmName("subdivideDepthProperty")
        get() = getSubdivideDepth()
        @JvmName("setSubdivideDepthProperty")
        set(value) = setSubdivideDepth(value)

    /**
     * The box's width, height and depth.
     *
     * Generated from Godot docs: BoxMesh.set_size
     */
    fun setSize(size: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setSizeBind, segment, size)
    }

    /**
     * The box's width, height and depth.
     *
     * Generated from Godot docs: BoxMesh.get_size
     */
    fun getSize(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getSizeBind, segment)
    }

    /**
     * Number of extra edge loops inserted along the X axis.
     *
     * Generated from Godot docs: BoxMesh.set_subdivide_width
     */
    fun setSubdivideWidth(subdivideWidth: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSubdivideWidthBind, segment, subdivideWidth)
    }

    /**
     * Number of extra edge loops inserted along the X axis.
     *
     * Generated from Godot docs: BoxMesh.get_subdivide_width
     */
    fun getSubdivideWidth(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSubdivideWidthBind, segment)
    }

    /**
     * Number of extra edge loops inserted along the Y axis.
     *
     * Generated from Godot docs: BoxMesh.set_subdivide_height
     */
    fun setSubdivideHeight(subdivideHeight: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSubdivideHeightBind, segment, subdivideHeight)
    }

    /**
     * Number of extra edge loops inserted along the Y axis.
     *
     * Generated from Godot docs: BoxMesh.get_subdivide_height
     */
    fun getSubdivideHeight(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSubdivideHeightBind, segment)
    }

    /**
     * Number of extra edge loops inserted along the Z axis.
     *
     * Generated from Godot docs: BoxMesh.set_subdivide_depth
     */
    fun setSubdivideDepth(subdivideDepth: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSubdivideDepthBind, segment, subdivideDepth)
    }

    /**
     * Number of extra edge loops inserted along the Z axis.
     *
     * Generated from Godot docs: BoxMesh.get_subdivide_depth
     */
    fun getSubdivideDepth(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSubdivideDepthBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): BoxMesh? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): BoxMesh? =
            if (handle.address() == 0L) null else RefCounted.owned(BoxMesh(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): BoxMesh? =
            if (handle.address() == 0L) null else BoxMesh(GodotHandle(handle))

        // Instantiate a BoxMesh.
        @JvmStatic
        fun create(): BoxMesh =
            RefCounted.owned(BoxMesh(GodotHandle(ObjectCalls.constructObject("BoxMesh"))))
    }

    private object Binds {
        private const val SET_SIZE_HASH = 3460891852L
        @JvmField
        val setSizeBind =
            ObjectCalls.getMethodBind("BoxMesh", "set_size", SET_SIZE_HASH)

        private const val GET_SIZE_HASH = 3360562783L
        @JvmField
        val getSizeBind =
            ObjectCalls.getMethodBind("BoxMesh", "get_size", GET_SIZE_HASH)

        private const val SET_SUBDIVIDE_WIDTH_HASH = 1286410249L
        @JvmField
        val setSubdivideWidthBind =
            ObjectCalls.getMethodBind("BoxMesh", "set_subdivide_width", SET_SUBDIVIDE_WIDTH_HASH)

        private const val GET_SUBDIVIDE_WIDTH_HASH = 3905245786L
        @JvmField
        val getSubdivideWidthBind =
            ObjectCalls.getMethodBind("BoxMesh", "get_subdivide_width", GET_SUBDIVIDE_WIDTH_HASH)

        private const val SET_SUBDIVIDE_HEIGHT_HASH = 1286410249L
        @JvmField
        val setSubdivideHeightBind =
            ObjectCalls.getMethodBind("BoxMesh", "set_subdivide_height", SET_SUBDIVIDE_HEIGHT_HASH)

        private const val GET_SUBDIVIDE_HEIGHT_HASH = 3905245786L
        @JvmField
        val getSubdivideHeightBind =
            ObjectCalls.getMethodBind("BoxMesh", "get_subdivide_height", GET_SUBDIVIDE_HEIGHT_HASH)

        private const val SET_SUBDIVIDE_DEPTH_HASH = 1286410249L
        @JvmField
        val setSubdivideDepthBind =
            ObjectCalls.getMethodBind("BoxMesh", "set_subdivide_depth", SET_SUBDIVIDE_DEPTH_HASH)

        private const val GET_SUBDIVIDE_DEPTH_HASH = 3905245786L
        @JvmField
        val getSubdivideDepthBind =
            ObjectCalls.getMethodBind("BoxMesh", "get_subdivide_depth", GET_SUBDIVIDE_DEPTH_HASH)
    }
}

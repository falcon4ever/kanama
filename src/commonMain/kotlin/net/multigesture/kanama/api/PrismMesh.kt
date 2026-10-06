package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector3

/**
 * Class representing a prism-shaped `PrimitiveMesh`.
 *
 * Generated from Godot docs: PrismMesh
 */
class PrismMesh(handle: GodotHandle) : PrimitiveMesh(handle) {
    var leftToRight: Double
        @JvmName("leftToRightProperty")
        get() = getLeftToRight()
        @JvmName("setLeftToRightProperty")
        set(value) = setLeftToRight(value)

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
     * Displacement of the upper edge along the X axis. 0.0 positions edge straight above the
     * bottom-left edge.
     *
     * Generated from Godot docs: PrismMesh.set_left_to_right
     */
    fun setLeftToRight(leftToRight: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setLeftToRightBind, segment, leftToRight)
    }

    /**
     * Displacement of the upper edge along the X axis. 0.0 positions edge straight above the
     * bottom-left edge.
     *
     * Generated from Godot docs: PrismMesh.get_left_to_right
     */
    fun getLeftToRight(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLeftToRightBind, segment)
    }

    /**
     * Size of the prism.
     *
     * Generated from Godot docs: PrismMesh.set_size
     */
    fun setSize(size: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setSizeBind, segment, size)
    }

    /**
     * Size of the prism.
     *
     * Generated from Godot docs: PrismMesh.get_size
     */
    fun getSize(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getSizeBind, segment)
    }

    /**
     * Number of added edge loops along the X axis.
     *
     * Generated from Godot docs: PrismMesh.set_subdivide_width
     */
    fun setSubdivideWidth(segments: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSubdivideWidthBind, segment, segments)
    }

    /**
     * Number of added edge loops along the X axis.
     *
     * Generated from Godot docs: PrismMesh.get_subdivide_width
     */
    fun getSubdivideWidth(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSubdivideWidthBind, segment)
    }

    /**
     * Number of added edge loops along the Y axis.
     *
     * Generated from Godot docs: PrismMesh.set_subdivide_height
     */
    fun setSubdivideHeight(segments: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSubdivideHeightBind, segment, segments)
    }

    /**
     * Number of added edge loops along the Y axis.
     *
     * Generated from Godot docs: PrismMesh.get_subdivide_height
     */
    fun getSubdivideHeight(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSubdivideHeightBind, segment)
    }

    /**
     * Number of added edge loops along the Z axis.
     *
     * Generated from Godot docs: PrismMesh.set_subdivide_depth
     */
    fun setSubdivideDepth(segments: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSubdivideDepthBind, segment, segments)
    }

    /**
     * Number of added edge loops along the Z axis.
     *
     * Generated from Godot docs: PrismMesh.get_subdivide_depth
     */
    fun getSubdivideDepth(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSubdivideDepthBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PrismMesh? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): PrismMesh? =
            if (handle.address() == 0L) null else RefCounted.owned(PrismMesh(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): PrismMesh? =
            if (handle.address() == 0L) null else PrismMesh(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_LEFT_TO_RIGHT_HASH = 373806689L
        @JvmField
        val setLeftToRightBind =
            ObjectCalls.getMethodBind("PrismMesh", "set_left_to_right", SET_LEFT_TO_RIGHT_HASH)

        private const val GET_LEFT_TO_RIGHT_HASH = 1740695150L
        @JvmField
        val getLeftToRightBind =
            ObjectCalls.getMethodBind("PrismMesh", "get_left_to_right", GET_LEFT_TO_RIGHT_HASH)

        private const val SET_SIZE_HASH = 3460891852L
        @JvmField
        val setSizeBind =
            ObjectCalls.getMethodBind("PrismMesh", "set_size", SET_SIZE_HASH)

        private const val GET_SIZE_HASH = 3360562783L
        @JvmField
        val getSizeBind =
            ObjectCalls.getMethodBind("PrismMesh", "get_size", GET_SIZE_HASH)

        private const val SET_SUBDIVIDE_WIDTH_HASH = 1286410249L
        @JvmField
        val setSubdivideWidthBind =
            ObjectCalls.getMethodBind("PrismMesh", "set_subdivide_width", SET_SUBDIVIDE_WIDTH_HASH)

        private const val GET_SUBDIVIDE_WIDTH_HASH = 3905245786L
        @JvmField
        val getSubdivideWidthBind =
            ObjectCalls.getMethodBind("PrismMesh", "get_subdivide_width", GET_SUBDIVIDE_WIDTH_HASH)

        private const val SET_SUBDIVIDE_HEIGHT_HASH = 1286410249L
        @JvmField
        val setSubdivideHeightBind =
            ObjectCalls.getMethodBind("PrismMesh", "set_subdivide_height", SET_SUBDIVIDE_HEIGHT_HASH)

        private const val GET_SUBDIVIDE_HEIGHT_HASH = 3905245786L
        @JvmField
        val getSubdivideHeightBind =
            ObjectCalls.getMethodBind("PrismMesh", "get_subdivide_height", GET_SUBDIVIDE_HEIGHT_HASH)

        private const val SET_SUBDIVIDE_DEPTH_HASH = 1286410249L
        @JvmField
        val setSubdivideDepthBind =
            ObjectCalls.getMethodBind("PrismMesh", "set_subdivide_depth", SET_SUBDIVIDE_DEPTH_HASH)

        private const val GET_SUBDIVIDE_DEPTH_HASH = 3905245786L
        @JvmField
        val getSubdivideDepthBind =
            ObjectCalls.getMethodBind("PrismMesh", "get_subdivide_depth", GET_SUBDIVIDE_DEPTH_HASH)
    }
}

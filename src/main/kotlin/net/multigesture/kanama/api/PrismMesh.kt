package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

/**
 * Class representing a prism-shaped `PrimitiveMesh`.
 *
 * Generated from Godot docs: PrismMesh
 */
class PrismMesh(handle: MemorySegment) : PrimitiveMesh(handle) {
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
        ObjectCalls.ptrcallWithDoubleArg(setLeftToRightBind, handle, leftToRight)
    }

    /**
     * Displacement of the upper edge along the X axis. 0.0 positions edge straight above the
     * bottom-left edge.
     *
     * Generated from Godot docs: PrismMesh.get_left_to_right
     */
    fun getLeftToRight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLeftToRightBind, handle)
    }

    /**
     * Size of the prism.
     *
     * Generated from Godot docs: PrismMesh.set_size
     */
    fun setSize(size: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setSizeBind, handle, size)
    }

    /**
     * Size of the prism.
     *
     * Generated from Godot docs: PrismMesh.get_size
     */
    fun getSize(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getSizeBind, handle)
    }

    /**
     * Number of added edge loops along the X axis.
     *
     * Generated from Godot docs: PrismMesh.set_subdivide_width
     */
    fun setSubdivideWidth(segments: Int) {
        ObjectCalls.ptrcallWithIntArg(setSubdivideWidthBind, handle, segments)
    }

    /**
     * Number of added edge loops along the X axis.
     *
     * Generated from Godot docs: PrismMesh.get_subdivide_width
     */
    fun getSubdivideWidth(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSubdivideWidthBind, handle)
    }

    /**
     * Number of added edge loops along the Y axis.
     *
     * Generated from Godot docs: PrismMesh.set_subdivide_height
     */
    fun setSubdivideHeight(segments: Int) {
        ObjectCalls.ptrcallWithIntArg(setSubdivideHeightBind, handle, segments)
    }

    /**
     * Number of added edge loops along the Y axis.
     *
     * Generated from Godot docs: PrismMesh.get_subdivide_height
     */
    fun getSubdivideHeight(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSubdivideHeightBind, handle)
    }

    /**
     * Number of added edge loops along the Z axis.
     *
     * Generated from Godot docs: PrismMesh.set_subdivide_depth
     */
    fun setSubdivideDepth(segments: Int) {
        ObjectCalls.ptrcallWithIntArg(setSubdivideDepthBind, handle, segments)
    }

    /**
     * Number of added edge loops along the Z axis.
     *
     * Generated from Godot docs: PrismMesh.get_subdivide_depth
     */
    fun getSubdivideDepth(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSubdivideDepthBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PrismMesh? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PrismMesh? =
            if (handle.address() == 0L) null else PrismMesh(handle)

        private const val SET_LEFT_TO_RIGHT_HASH = 373806689L
        private val setLeftToRightBind by lazy {
            ObjectCalls.getMethodBind("PrismMesh", "set_left_to_right", SET_LEFT_TO_RIGHT_HASH)
        }

        private const val GET_LEFT_TO_RIGHT_HASH = 1740695150L
        private val getLeftToRightBind by lazy {
            ObjectCalls.getMethodBind("PrismMesh", "get_left_to_right", GET_LEFT_TO_RIGHT_HASH)
        }

        private const val SET_SIZE_HASH = 3460891852L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("PrismMesh", "set_size", SET_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 3360562783L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("PrismMesh", "get_size", GET_SIZE_HASH)
        }

        private const val SET_SUBDIVIDE_WIDTH_HASH = 1286410249L
        private val setSubdivideWidthBind by lazy {
            ObjectCalls.getMethodBind("PrismMesh", "set_subdivide_width", SET_SUBDIVIDE_WIDTH_HASH)
        }

        private const val GET_SUBDIVIDE_WIDTH_HASH = 3905245786L
        private val getSubdivideWidthBind by lazy {
            ObjectCalls.getMethodBind("PrismMesh", "get_subdivide_width", GET_SUBDIVIDE_WIDTH_HASH)
        }

        private const val SET_SUBDIVIDE_HEIGHT_HASH = 1286410249L
        private val setSubdivideHeightBind by lazy {
            ObjectCalls.getMethodBind("PrismMesh", "set_subdivide_height", SET_SUBDIVIDE_HEIGHT_HASH)
        }

        private const val GET_SUBDIVIDE_HEIGHT_HASH = 3905245786L
        private val getSubdivideHeightBind by lazy {
            ObjectCalls.getMethodBind("PrismMesh", "get_subdivide_height", GET_SUBDIVIDE_HEIGHT_HASH)
        }

        private const val SET_SUBDIVIDE_DEPTH_HASH = 1286410249L
        private val setSubdivideDepthBind by lazy {
            ObjectCalls.getMethodBind("PrismMesh", "set_subdivide_depth", SET_SUBDIVIDE_DEPTH_HASH)
        }

        private const val GET_SUBDIVIDE_DEPTH_HASH = 3905245786L
        private val getSubdivideDepthBind by lazy {
            ObjectCalls.getMethodBind("PrismMesh", "get_subdivide_depth", GET_SUBDIVIDE_DEPTH_HASH)
        }
    }
}

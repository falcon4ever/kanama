package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * Class representing a planar `PrimitiveMesh`.
 *
 * Generated from Godot docs: PlaneMesh
 */
open class PlaneMesh(handle: GodotHandle) : PrimitiveMesh(handle) {
    var size: Vector2
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    var subdivideWidth: Int
        @JvmName("subdivideWidthProperty")
        get() = getSubdivideWidth()
        @JvmName("setSubdivideWidthProperty")
        set(value) = setSubdivideWidth(value)

    var subdivideDepth: Int
        @JvmName("subdivideDepthProperty")
        get() = getSubdivideDepth()
        @JvmName("setSubdivideDepthProperty")
        set(value) = setSubdivideDepth(value)

    var centerOffset: Vector3
        @JvmName("centerOffsetProperty")
        get() = getCenterOffset()
        @JvmName("setCenterOffsetProperty")
        set(value) = setCenterOffset(value)

    var orientation: Long
        @JvmName("orientationProperty")
        get() = getOrientation()
        @JvmName("setOrientationProperty")
        set(value) = setOrientation(value)

    /**
     * Size of the generated plane.
     *
     * Generated from Godot docs: PlaneMesh.set_size
     */
    fun setSize(size: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(setSizeBind, segment, size)
    }

    /**
     * Size of the generated plane.
     *
     * Generated from Godot docs: PlaneMesh.get_size
     */
    fun getSize(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getSizeBind, segment)
    }

    /**
     * Number of subdivision along the X axis.
     *
     * Generated from Godot docs: PlaneMesh.set_subdivide_width
     */
    fun setSubdivideWidth(subdivide: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setSubdivideWidthBind, segment, subdivide)
    }

    /**
     * Number of subdivision along the X axis.
     *
     * Generated from Godot docs: PlaneMesh.get_subdivide_width
     */
    fun getSubdivideWidth(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSubdivideWidthBind, segment)
    }

    /**
     * Number of subdivision along the Z axis.
     *
     * Generated from Godot docs: PlaneMesh.set_subdivide_depth
     */
    fun setSubdivideDepth(subdivide: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setSubdivideDepthBind, segment, subdivide)
    }

    /**
     * Number of subdivision along the Z axis.
     *
     * Generated from Godot docs: PlaneMesh.get_subdivide_depth
     */
    fun getSubdivideDepth(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSubdivideDepthBind, segment)
    }

    /**
     * Offset of the generated plane. Useful for particles.
     *
     * Generated from Godot docs: PlaneMesh.set_center_offset
     */
    fun setCenterOffset(offset: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(setCenterOffsetBind, segment, offset)
    }

    /**
     * Offset of the generated plane. Useful for particles.
     *
     * Generated from Godot docs: PlaneMesh.get_center_offset
     */
    fun getCenterOffset(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(getCenterOffsetBind, segment)
    }

    /**
     * Direction that the `PlaneMesh` is facing.
     *
     * Generated from Godot docs: PlaneMesh.set_orientation
     */
    fun setOrientation(orientation: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setOrientationBind, segment, orientation)
    }

    /**
     * Direction that the `PlaneMesh` is facing.
     *
     * Generated from Godot docs: PlaneMesh.get_orientation
     */
    fun getOrientation(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getOrientationBind, segment)
    }

    companion object {
        const val FACE_X: Long = 0L
        const val FACE_Y: Long = 1L
        const val FACE_Z: Long = 2L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): PlaneMesh? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): PlaneMesh? =
            if (handle.address() == 0L) null else PlaneMesh(GodotHandle(handle))

        // Downcast a Resource to PlaneMesh (null if not).
        @JvmStatic
        fun fromResource(value: Resource): PlaneMesh? =
            if (value.isClass("PlaneMesh")) PlaneMesh(value.handle) else null

        private const val SET_SIZE_HASH = 743155724L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("PlaneMesh", "set_size", SET_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 3341600327L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("PlaneMesh", "get_size", GET_SIZE_HASH)
        }

        private const val SET_SUBDIVIDE_WIDTH_HASH = 1286410249L
        private val setSubdivideWidthBind by lazy {
            ObjectCalls.getMethodBind("PlaneMesh", "set_subdivide_width", SET_SUBDIVIDE_WIDTH_HASH)
        }

        private const val GET_SUBDIVIDE_WIDTH_HASH = 3905245786L
        private val getSubdivideWidthBind by lazy {
            ObjectCalls.getMethodBind("PlaneMesh", "get_subdivide_width", GET_SUBDIVIDE_WIDTH_HASH)
        }

        private const val SET_SUBDIVIDE_DEPTH_HASH = 1286410249L
        private val setSubdivideDepthBind by lazy {
            ObjectCalls.getMethodBind("PlaneMesh", "set_subdivide_depth", SET_SUBDIVIDE_DEPTH_HASH)
        }

        private const val GET_SUBDIVIDE_DEPTH_HASH = 3905245786L
        private val getSubdivideDepthBind by lazy {
            ObjectCalls.getMethodBind("PlaneMesh", "get_subdivide_depth", GET_SUBDIVIDE_DEPTH_HASH)
        }

        private const val SET_CENTER_OFFSET_HASH = 3460891852L
        private val setCenterOffsetBind by lazy {
            ObjectCalls.getMethodBind("PlaneMesh", "set_center_offset", SET_CENTER_OFFSET_HASH)
        }

        private const val GET_CENTER_OFFSET_HASH = 3360562783L
        private val getCenterOffsetBind by lazy {
            ObjectCalls.getMethodBind("PlaneMesh", "get_center_offset", GET_CENTER_OFFSET_HASH)
        }

        private const val SET_ORIENTATION_HASH = 2751399687L
        private val setOrientationBind by lazy {
            ObjectCalls.getMethodBind("PlaneMesh", "set_orientation", SET_ORIENTATION_HASH)
        }

        private const val GET_ORIENTATION_HASH = 3227599250L
        private val getOrientationBind by lazy {
            ObjectCalls.getMethodBind("PlaneMesh", "get_orientation", GET_ORIENTATION_HASH)
        }
    }
}

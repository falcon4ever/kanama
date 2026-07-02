package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * Class representing a planar `PrimitiveMesh`.
 *
 * Generated from Godot docs: PlaneMesh
 */
open class PlaneMesh(handle: MemorySegment) : PrimitiveMesh(handle) {
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

    fun setSize(size: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setSizeBind, handle, size)
    }

    fun getSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getSizeBind, handle)
    }

    fun setSubdivideWidth(subdivide: Int) {
        ObjectCalls.ptrcallWithIntArg(setSubdivideWidthBind, handle, subdivide)
    }

    fun getSubdivideWidth(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSubdivideWidthBind, handle)
    }

    fun setSubdivideDepth(subdivide: Int) {
        ObjectCalls.ptrcallWithIntArg(setSubdivideDepthBind, handle, subdivide)
    }

    fun getSubdivideDepth(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSubdivideDepthBind, handle)
    }

    fun setCenterOffset(offset: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setCenterOffsetBind, handle, offset)
    }

    fun getCenterOffset(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getCenterOffsetBind, handle)
    }

    fun setOrientation(orientation: Long) {
        ObjectCalls.ptrcallWithLongArg(setOrientationBind, handle, orientation)
    }

    fun getOrientation(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getOrientationBind, handle)
    }

    companion object {
        const val FACE_X: Long = 0L
        const val FACE_Y: Long = 1L
        const val FACE_Z: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): PlaneMesh? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PlaneMesh? =
            if (handle.address() == 0L) null else PlaneMesh(handle)

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

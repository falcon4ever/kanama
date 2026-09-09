package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: BoxMesh
 */
class BoxMesh(handle: MemorySegment) : PrimitiveMesh(handle) {
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

    fun setSize(size: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(setSizeBind, handle, size)
    }

    fun getSize(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(getSizeBind, handle)
    }

    fun setSubdivideWidth(subdivide: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setSubdivideWidthBind, handle, subdivide)
    }

    fun getSubdivideWidth(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSubdivideWidthBind, handle)
    }

    fun setSubdivideHeight(divisions: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setSubdivideHeightBind, handle, divisions)
    }

    fun getSubdivideHeight(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSubdivideHeightBind, handle)
    }

    fun setSubdivideDepth(divisions: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setSubdivideDepthBind, handle, divisions)
    }

    fun getSubdivideDepth(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSubdivideDepthBind, handle)
    }

    companion object {
        // KANAMA-IOS-SUGAR: [glue] desktop-parity constructor sugar (the desktop wrapper's
        // MemorySegment constructor is internal, so shared game code uses create()).
        fun create(): BoxMesh =
            BoxMesh(ObjectCalls.constructObject("BoxMesh"))

        fun fromHandle(handle: MemorySegment): BoxMesh? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): BoxMesh? =
            if (handle.address() == 0L) null else BoxMesh(handle)

        private const val SET_SIZE_HASH = 3460891852L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("BoxMesh", "set_size", SET_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 3360562783L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("BoxMesh", "get_size", GET_SIZE_HASH)
        }

        private const val SET_SUBDIVIDE_WIDTH_HASH = 1286410249L
        private val setSubdivideWidthBind by lazy {
            ObjectCalls.getMethodBind("BoxMesh", "set_subdivide_width", SET_SUBDIVIDE_WIDTH_HASH)
        }

        private const val GET_SUBDIVIDE_WIDTH_HASH = 3905245786L
        private val getSubdivideWidthBind by lazy {
            ObjectCalls.getMethodBind("BoxMesh", "get_subdivide_width", GET_SUBDIVIDE_WIDTH_HASH)
        }

        private const val SET_SUBDIVIDE_HEIGHT_HASH = 1286410249L
        private val setSubdivideHeightBind by lazy {
            ObjectCalls.getMethodBind("BoxMesh", "set_subdivide_height", SET_SUBDIVIDE_HEIGHT_HASH)
        }

        private const val GET_SUBDIVIDE_HEIGHT_HASH = 3905245786L
        private val getSubdivideHeightBind by lazy {
            ObjectCalls.getMethodBind("BoxMesh", "get_subdivide_height", GET_SUBDIVIDE_HEIGHT_HASH)
        }

        private const val SET_SUBDIVIDE_DEPTH_HASH = 1286410249L
        private val setSubdivideDepthBind by lazy {
            ObjectCalls.getMethodBind("BoxMesh", "set_subdivide_depth", SET_SUBDIVIDE_DEPTH_HASH)
        }

        private const val GET_SUBDIVIDE_DEPTH_HASH = 3905245786L
        private val getSubdivideDepthBind by lazy {
            ObjectCalls.getMethodBind("BoxMesh", "get_subdivide_depth", GET_SUBDIVIDE_DEPTH_HASH)
        }
    }
}

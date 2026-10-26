package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed
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

    var orientation: PlaneMesh.Orientation
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
        ObjectCalls.ptrcallWithVector2Arg(Binds.setSizeBind, segment, size)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Size of the generated plane.
     *
     * Generated from Godot docs: PlaneMesh.get_size
     */
    fun getSize(): Vector2 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector2(Binds.getSizeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Number of subdivision along the X axis.
     *
     * Generated from Godot docs: PlaneMesh.set_subdivide_width
     */
    fun setSubdivideWidth(subdivide: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSubdivideWidthBind, segment, subdivide)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Number of subdivision along the X axis.
     *
     * Generated from Godot docs: PlaneMesh.get_subdivide_width
     */
    fun getSubdivideWidth(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getSubdivideWidthBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Number of subdivision along the Z axis.
     *
     * Generated from Godot docs: PlaneMesh.set_subdivide_depth
     */
    fun setSubdivideDepth(subdivide: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSubdivideDepthBind, segment, subdivide)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Number of subdivision along the Z axis.
     *
     * Generated from Godot docs: PlaneMesh.get_subdivide_depth
     */
    fun getSubdivideDepth(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getSubdivideDepthBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Offset of the generated plane. Useful for particles.
     *
     * Generated from Godot docs: PlaneMesh.set_center_offset
     */
    fun setCenterOffset(offset: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setCenterOffsetBind, segment, offset)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Offset of the generated plane. Useful for particles.
     *
     * Generated from Godot docs: PlaneMesh.get_center_offset
     */
    fun getCenterOffset(): Vector3 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector3(Binds.getCenterOffsetBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Direction that the `PlaneMesh` is facing.
     *
     * Generated from Godot docs: PlaneMesh.set_orientation
     */
    fun setOrientation(orientation: PlaneMesh.Orientation) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setOrientationBind, segment, orientation.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Direction that the `PlaneMesh` is facing.
     *
     * Generated from Godot docs: PlaneMesh.get_orientation
     */
    fun getOrientation(): PlaneMesh.Orientation {
        checkOpen()
        val ret = PlaneMesh.Orientation(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOrientationBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `PlaneMesh.Orientation` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`PlaneMesh.Orientation.<NAME>`).
     *
     * Generated from Godot docs: PlaneMesh.Orientation
     */
    @JvmInline
    value class Orientation(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * `PlaneMesh` will face the positive X-axis.
             *
             * Generated from Godot docs: PlaneMesh.FACE_X
             */
            val X: Orientation get() = Orientation(0L)
            /**
             * `PlaneMesh` will face the positive Y-axis. This matches the behavior of the `PlaneMesh` in Godot
             * 3.x.
             *
             * Generated from Godot docs: PlaneMesh.FACE_Y
             */
            val Y: Orientation get() = Orientation(1L)
            /**
             * `PlaneMesh` will face the positive Z-axis. This matches the behavior of the QuadMesh in Godot
             * 3.x.
             *
             * Generated from Godot docs: PlaneMesh.FACE_Z
             */
            val Z: Orientation get() = Orientation(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PlaneMesh? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): PlaneMesh? =
            if (handle.address() == 0L) null else RefCounted.owned(PlaneMesh(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): PlaneMesh? =
            if (handle.address() == 0L) null else PlaneMesh(GodotHandle(handle))

        /** A new PlaneMesh, GDScript's `PlaneMesh.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): PlaneMesh =
            RefCounted.owned(PlaneMesh(GodotHandle(requireConstructed(ObjectCalls.constructObject("PlaneMesh"), "PlaneMesh"))))

        // Downcast a Resource to PlaneMesh (null if not).
        @JvmStatic
        fun fromResource(value: Resource): PlaneMesh? =
            if (value.isClass("PlaneMesh")) RefCounted.retained(PlaneMesh(value.handle), value) else null
    }

    private object Binds {
        private const val SET_SIZE_HASH = 743155724L
        @JvmField
        val setSizeBind =
            ObjectCalls.getMethodBind("PlaneMesh", "set_size", SET_SIZE_HASH)

        private const val GET_SIZE_HASH = 3341600327L
        @JvmField
        val getSizeBind =
            ObjectCalls.getMethodBind("PlaneMesh", "get_size", GET_SIZE_HASH)

        private const val SET_SUBDIVIDE_WIDTH_HASH = 1286410249L
        @JvmField
        val setSubdivideWidthBind =
            ObjectCalls.getMethodBind("PlaneMesh", "set_subdivide_width", SET_SUBDIVIDE_WIDTH_HASH)

        private const val GET_SUBDIVIDE_WIDTH_HASH = 3905245786L
        @JvmField
        val getSubdivideWidthBind =
            ObjectCalls.getMethodBind("PlaneMesh", "get_subdivide_width", GET_SUBDIVIDE_WIDTH_HASH)

        private const val SET_SUBDIVIDE_DEPTH_HASH = 1286410249L
        @JvmField
        val setSubdivideDepthBind =
            ObjectCalls.getMethodBind("PlaneMesh", "set_subdivide_depth", SET_SUBDIVIDE_DEPTH_HASH)

        private const val GET_SUBDIVIDE_DEPTH_HASH = 3905245786L
        @JvmField
        val getSubdivideDepthBind =
            ObjectCalls.getMethodBind("PlaneMesh", "get_subdivide_depth", GET_SUBDIVIDE_DEPTH_HASH)

        private const val SET_CENTER_OFFSET_HASH = 3460891852L
        @JvmField
        val setCenterOffsetBind =
            ObjectCalls.getMethodBind("PlaneMesh", "set_center_offset", SET_CENTER_OFFSET_HASH)

        private const val GET_CENTER_OFFSET_HASH = 3360562783L
        @JvmField
        val getCenterOffsetBind =
            ObjectCalls.getMethodBind("PlaneMesh", "get_center_offset", GET_CENTER_OFFSET_HASH)

        private const val SET_ORIENTATION_HASH = 2751399687L
        @JvmField
        val setOrientationBind =
            ObjectCalls.getMethodBind("PlaneMesh", "set_orientation", SET_ORIENTATION_HASH)

        private const val GET_ORIENTATION_HASH = 3227599250L
        @JvmField
        val getOrientationBind =
            ObjectCalls.getMethodBind("PlaneMesh", "get_orientation", GET_ORIENTATION_HASH)
    }
}

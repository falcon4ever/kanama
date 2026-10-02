package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector3

/**
 * Real-time global illumination (GI) probe.
 *
 * Generated from Godot docs: VoxelGI
 */
class VoxelGI(handle: GodotHandle) : VisualInstance3D(handle) {
    var subdiv: VoxelGI.Subdiv
        @JvmName("subdivProperty")
        get() = getSubdiv()
        @JvmName("setSubdivProperty")
        set(value) = setSubdiv(value)

    var size: Vector3
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    var cameraAttributes: CameraAttributes?
        @JvmName("cameraAttributesProperty")
        get() = getCameraAttributes()
        @JvmName("setCameraAttributesProperty")
        set(value) = setCameraAttributes(value)

    var data: VoxelGIData?
        @JvmName("dataProperty")
        get() = getProbeData()
        @JvmName("setDataProperty")
        set(value) = setProbeData(value)

    /**
     * The `VoxelGIData` resource that holds the data for this `VoxelGI`.
     *
     * Generated from Godot docs: VoxelGI.set_probe_data
     */
    fun setProbeData(data: VoxelGIData?) {
        ObjectCalls.ptrcallWithObjectArgs(setProbeDataBind, segment, listOf(data?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `VoxelGIData` resource that holds the data for this `VoxelGI`.
     *
     * Generated from Godot docs: VoxelGI.get_probe_data
     */
    fun getProbeData(): VoxelGIData? {
        return VoxelGIData.wrap(ObjectCalls.ptrcallNoArgsRetObject(getProbeDataBind, segment))
    }

    /**
     * Number of times to subdivide the grid that the `VoxelGI` operates on. A higher number results in
     * finer detail and thus higher visual quality, while lower numbers result in better performance.
     *
     * Generated from Godot docs: VoxelGI.set_subdiv
     */
    fun setSubdiv(subdiv: VoxelGI.Subdiv) {
        ObjectCalls.ptrcallWithLongArg(setSubdivBind, segment, subdiv.value)
    }

    /**
     * Number of times to subdivide the grid that the `VoxelGI` operates on. A higher number results in
     * finer detail and thus higher visual quality, while lower numbers result in better performance.
     *
     * Generated from Godot docs: VoxelGI.get_subdiv
     */
    fun getSubdiv(): VoxelGI.Subdiv {
        return VoxelGI.Subdiv(ObjectCalls.ptrcallNoArgsRetLong(getSubdivBind, segment))
    }

    /**
     * The size of the area covered by the `VoxelGI`. This must be `1.0` or greater on each axis. Note:
     * If you make the size larger without increasing the number of subdivisions with `subdiv`, the
     * size of each cell will increase and result in less detailed lighting.
     *
     * Generated from Godot docs: VoxelGI.set_size
     */
    fun setSize(size: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setSizeBind, segment, size)
    }

    /**
     * The size of the area covered by the `VoxelGI`. This must be `1.0` or greater on each axis. Note:
     * If you make the size larger without increasing the number of subdivisions with `subdiv`, the
     * size of each cell will increase and result in less detailed lighting.
     *
     * Generated from Godot docs: VoxelGI.get_size
     */
    fun getSize(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getSizeBind, segment)
    }

    /**
     * The `CameraAttributes` resource that specifies exposure levels to bake at. Auto-exposure and non
     * exposure properties will be ignored. Exposure settings should be used to reduce the dynamic
     * range present when baking. If exposure is too high, the `VoxelGI` will have banding artifacts or
     * may have over-exposure artifacts.
     *
     * Generated from Godot docs: VoxelGI.set_camera_attributes
     */
    fun setCameraAttributes(cameraAttributes: CameraAttributes?) {
        ObjectCalls.ptrcallWithObjectArgs(setCameraAttributesBind, segment, listOf(cameraAttributes?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `CameraAttributes` resource that specifies exposure levels to bake at. Auto-exposure and non
     * exposure properties will be ignored. Exposure settings should be used to reduce the dynamic
     * range present when baking. If exposure is too high, the `VoxelGI` will have banding artifacts or
     * may have over-exposure artifacts.
     *
     * Generated from Godot docs: VoxelGI.get_camera_attributes
     */
    fun getCameraAttributes(): CameraAttributes? {
        return CameraAttributes.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCameraAttributesBind, segment))
    }

    /**
     * Bakes the effect from all `GeometryInstance3D`s marked with `GeometryInstance3D.GIMode.STATIC`
     * and `Light3D`s marked with either `Light3D.BakeMode.STATIC` or `Light3D.BakeMode.DYNAMIC`. If
     * `create_visual_debug` is `true`, after baking the light, this will generate a `MultiMesh` that
     * has a cube representing each solid cell with each cube colored to the cell's albedo color. This
     * can be used to visualize the `VoxelGI`'s data and debug any issues that may be occurring. Note:
     * `bake` works from the editor and in exported projects. This makes it suitable for procedurally
     * generated or user-built levels. Baking a `VoxelGI` node generally takes from 5 to 20 seconds in
     * most scenes. Reducing `subdiv` can speed up baking. Note: `GeometryInstance3D`s and `Light3D`s
     * must be fully ready before `bake` is called. If you are procedurally creating those and some
     * meshes or lights are missing from your baked `VoxelGI`, use `call_deferred("bake")` instead of
     * calling `bake` directly.
     *
     * Generated from Godot docs: VoxelGI.bake
     */
    fun bake(fromNode: Node, createVisualDebug: Boolean = false) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(bakeBind, segment, fromNode.segment, createVisualDebug)
    }

    /**
     * Calls `bake` with `create_visual_debug` enabled.
     *
     * Generated from Godot docs: VoxelGI.debug_bake
     */
    fun debugBake() {
        ObjectCalls.ptrcallNoArgs(debugBakeBind, segment)
    }

    /**
     * Godot's `VoxelGI.Subdiv` enum as a typed value: `.value` is the raw number Godot uses, and the
     * companion holds the named values (`VoxelGI.Subdiv.<NAME>`).
     *
     * Generated from Godot docs: VoxelGI.Subdiv
     */
    @JvmInline
    value class Subdiv(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Use 64 subdivisions. This is the lowest quality setting, but the fastest. Use it if you can, but
             * especially use it on lower-end hardware.
             *
             * Generated from Godot docs: VoxelGI.SUBDIV_64
             */
            val SUBDIV_64: Subdiv get() = Subdiv(0L)
            /**
             * Use 128 subdivisions. This is the default quality setting.
             *
             * Generated from Godot docs: VoxelGI.SUBDIV_128
             */
            val SUBDIV_128: Subdiv get() = Subdiv(1L)
            /**
             * Use 256 subdivisions.
             *
             * Generated from Godot docs: VoxelGI.SUBDIV_256
             */
            val SUBDIV_256: Subdiv get() = Subdiv(2L)
            /**
             * Use 512 subdivisions. This is the highest quality setting, but the slowest. On lower-end
             * hardware, this could cause the GPU to stall.
             *
             * Generated from Godot docs: VoxelGI.SUBDIV_512
             */
            val SUBDIV_512: Subdiv get() = Subdiv(3L)
            /**
             * Represents the size of the `Subdiv` enum.
             *
             * Generated from Godot docs: VoxelGI.SUBDIV_MAX
             */
            val MAX: Subdiv get() = Subdiv(4L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VoxelGI? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VoxelGI? =
            if (handle.address() == 0L) null else VoxelGI(GodotHandle(handle))

        private const val SET_PROBE_DATA_HASH = 1637849675L
        private val setProbeDataBind by lazy {
            ObjectCalls.getMethodBind("VoxelGI", "set_probe_data", SET_PROBE_DATA_HASH)
        }

        private const val GET_PROBE_DATA_HASH = 1730645405L
        private val getProbeDataBind by lazy {
            ObjectCalls.getMethodBind("VoxelGI", "get_probe_data", GET_PROBE_DATA_HASH)
        }

        private const val SET_SUBDIV_HASH = 2240898472L
        private val setSubdivBind by lazy {
            ObjectCalls.getMethodBind("VoxelGI", "set_subdiv", SET_SUBDIV_HASH)
        }

        private const val GET_SUBDIV_HASH = 4261647950L
        private val getSubdivBind by lazy {
            ObjectCalls.getMethodBind("VoxelGI", "get_subdiv", GET_SUBDIV_HASH)
        }

        private const val SET_SIZE_HASH = 3460891852L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("VoxelGI", "set_size", SET_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 3360562783L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("VoxelGI", "get_size", GET_SIZE_HASH)
        }

        private const val SET_CAMERA_ATTRIBUTES_HASH = 2817810567L
        private val setCameraAttributesBind by lazy {
            ObjectCalls.getMethodBind("VoxelGI", "set_camera_attributes", SET_CAMERA_ATTRIBUTES_HASH)
        }

        private const val GET_CAMERA_ATTRIBUTES_HASH = 3921283215L
        private val getCameraAttributesBind by lazy {
            ObjectCalls.getMethodBind("VoxelGI", "get_camera_attributes", GET_CAMERA_ATTRIBUTES_HASH)
        }

        private const val BAKE_HASH = 2781551026L
        private val bakeBind by lazy {
            ObjectCalls.getMethodBind("VoxelGI", "bake", BAKE_HASH)
        }

        private const val DEBUG_BAKE_HASH = 3218959716L
        private val debugBakeBind by lazy {
            ObjectCalls.getMethodBind("VoxelGI", "debug_bake", DEBUG_BAKE_HASH)
        }
    }
}

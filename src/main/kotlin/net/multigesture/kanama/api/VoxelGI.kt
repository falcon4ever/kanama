package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

/**
 * Real-time global illumination (GI) probe.
 *
 * Generated from Godot docs: VoxelGI
 */
class VoxelGI(handle: MemorySegment) : VisualInstance3D(handle) {
    var subdiv: Long
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

    fun setProbeData(data: VoxelGIData?) {
        ObjectCalls.ptrcallWithObjectArgs(setProbeDataBind, handle, listOf(data?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getProbeData(): VoxelGIData? {
        return VoxelGIData.wrap(ObjectCalls.ptrcallNoArgsRetObject(getProbeDataBind, handle))
    }

    fun setSubdiv(subdiv: Long) {
        ObjectCalls.ptrcallWithLongArg(setSubdivBind, handle, subdiv)
    }

    fun getSubdiv(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSubdivBind, handle)
    }

    fun setSize(size: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setSizeBind, handle, size)
    }

    fun getSize(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getSizeBind, handle)
    }

    fun setCameraAttributes(cameraAttributes: CameraAttributes?) {
        ObjectCalls.ptrcallWithObjectArgs(setCameraAttributesBind, handle, listOf(cameraAttributes?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getCameraAttributes(): CameraAttributes? {
        return CameraAttributes.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCameraAttributesBind, handle))
    }

    /**
     * Bakes the effect from all `GeometryInstance3D`s marked with `GeometryInstance3D.GI_MODE_STATIC`
     * and `Light3D`s marked with either `Light3D.BAKE_STATIC` or `Light3D.BAKE_DYNAMIC`. If
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
        ObjectCalls.ptrcallWithObjectAndBoolArg(bakeBind, handle, fromNode.handle, createVisualDebug)
    }

    fun debugBake() {
        ObjectCalls.ptrcallNoArgs(debugBakeBind, handle)
    }

    companion object {
        const val SUBDIV_64: Long = 0L
        const val SUBDIV_128: Long = 1L
        const val SUBDIV_256: Long = 2L
        const val SUBDIV_512: Long = 3L
        const val SUBDIV_MAX: Long = 4L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VoxelGI? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VoxelGI? =
            if (handle.address() == 0L) null else VoxelGI(handle)

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

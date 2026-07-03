package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

/**
 * A region that contributes to the default volumetric fog from the world environment.
 *
 * Generated from Godot docs: FogVolume
 */
class FogVolume(handle: MemorySegment) : VisualInstance3D(handle) {
    var size: Vector3
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    var shape: Long
        @JvmName("shapeProperty")
        get() = getShape()
        @JvmName("setShapeProperty")
        set(value) = setShape(value)

    var material: Material?
        @JvmName("materialProperty")
        get() = getMaterial()
        @JvmName("setMaterialProperty")
        set(value) = setMaterial(value)

    /**
     * The size of the `FogVolume` when `shape` is `RenderingServer.FOG_VOLUME_SHAPE_ELLIPSOID`,
     * `RenderingServer.FOG_VOLUME_SHAPE_CONE`, `RenderingServer.FOG_VOLUME_SHAPE_CYLINDER` or
     * `RenderingServer.FOG_VOLUME_SHAPE_BOX`. Note: Thin fog volumes may appear to flicker when the
     * camera moves or rotates. This can be alleviated by increasing
     * `ProjectSettings.rendering/environment/volumetric_fog/volume_depth` (at a performance cost) or
     * by decreasing `Environment.volumetric_fog_length` (at no performance cost, but at the cost of
     * lower fog range). Alternatively, the `FogVolume` can be made thicker and use a lower density in
     * the `material`. Note: If `shape` is `RenderingServer.FOG_VOLUME_SHAPE_CONE` or
     * `RenderingServer.FOG_VOLUME_SHAPE_CYLINDER`, the cone/cylinder will be adjusted to fit within
     * the size. Non-uniform scaling of cone/cylinder shapes via the `size` property is not supported,
     * but you can scale the `FogVolume` node instead.
     *
     * Generated from Godot docs: FogVolume.set_size
     */
    fun setSize(size: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setSizeBind, handle, size)
    }

    /**
     * The size of the `FogVolume` when `shape` is `RenderingServer.FOG_VOLUME_SHAPE_ELLIPSOID`,
     * `RenderingServer.FOG_VOLUME_SHAPE_CONE`, `RenderingServer.FOG_VOLUME_SHAPE_CYLINDER` or
     * `RenderingServer.FOG_VOLUME_SHAPE_BOX`. Note: Thin fog volumes may appear to flicker when the
     * camera moves or rotates. This can be alleviated by increasing
     * `ProjectSettings.rendering/environment/volumetric_fog/volume_depth` (at a performance cost) or
     * by decreasing `Environment.volumetric_fog_length` (at no performance cost, but at the cost of
     * lower fog range). Alternatively, the `FogVolume` can be made thicker and use a lower density in
     * the `material`. Note: If `shape` is `RenderingServer.FOG_VOLUME_SHAPE_CONE` or
     * `RenderingServer.FOG_VOLUME_SHAPE_CYLINDER`, the cone/cylinder will be adjusted to fit within
     * the size. Non-uniform scaling of cone/cylinder shapes via the `size` property is not supported,
     * but you can scale the `FogVolume` node instead.
     *
     * Generated from Godot docs: FogVolume.get_size
     */
    fun getSize(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getSizeBind, handle)
    }

    /**
     * The shape of the `FogVolume`. This can be set to either
     * `RenderingServer.FOG_VOLUME_SHAPE_ELLIPSOID`, `RenderingServer.FOG_VOLUME_SHAPE_CONE`,
     * `RenderingServer.FOG_VOLUME_SHAPE_CYLINDER`, `RenderingServer.FOG_VOLUME_SHAPE_BOX` or
     * `RenderingServer.FOG_VOLUME_SHAPE_WORLD`.
     *
     * Generated from Godot docs: FogVolume.set_shape
     */
    fun setShape(shape: Long) {
        ObjectCalls.ptrcallWithLongArg(setShapeBind, handle, shape)
    }

    /**
     * The shape of the `FogVolume`. This can be set to either
     * `RenderingServer.FOG_VOLUME_SHAPE_ELLIPSOID`, `RenderingServer.FOG_VOLUME_SHAPE_CONE`,
     * `RenderingServer.FOG_VOLUME_SHAPE_CYLINDER`, `RenderingServer.FOG_VOLUME_SHAPE_BOX` or
     * `RenderingServer.FOG_VOLUME_SHAPE_WORLD`.
     *
     * Generated from Godot docs: FogVolume.get_shape
     */
    fun getShape(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getShapeBind, handle)
    }

    /**
     * The `Material` used by the `FogVolume`. Can be either a built-in `FogMaterial` or a custom
     * `ShaderMaterial`.
     *
     * Generated from Godot docs: FogVolume.set_material
     */
    fun setMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(setMaterialBind, handle, listOf(material?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The `Material` used by the `FogVolume`. Can be either a built-in `FogMaterial` or a custom
     * `ShaderMaterial`.
     *
     * Generated from Godot docs: FogVolume.get_material
     */
    fun getMaterial(): Material? {
        return Material.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMaterialBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): FogVolume? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): FogVolume? =
            if (handle.address() == 0L) null else FogVolume(handle)

        private const val SET_SIZE_HASH = 3460891852L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("FogVolume", "set_size", SET_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 3360562783L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("FogVolume", "get_size", GET_SIZE_HASH)
        }

        private const val SET_SHAPE_HASH = 1416323362L
        private val setShapeBind by lazy {
            ObjectCalls.getMethodBind("FogVolume", "set_shape", SET_SHAPE_HASH)
        }

        private const val GET_SHAPE_HASH = 3920334604L
        private val getShapeBind by lazy {
            ObjectCalls.getMethodBind("FogVolume", "get_shape", GET_SHAPE_HASH)
        }

        private const val SET_MATERIAL_HASH = 2757459619L
        private val setMaterialBind by lazy {
            ObjectCalls.getMethodBind("FogVolume", "set_material", SET_MATERIAL_HASH)
        }

        private const val GET_MATERIAL_HASH = 5934680L
        private val getMaterialBind by lazy {
            ObjectCalls.getMethodBind("FogVolume", "get_material", GET_MATERIAL_HASH)
        }
    }
}

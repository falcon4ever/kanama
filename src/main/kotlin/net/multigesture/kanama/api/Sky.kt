package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Defines a 3D environment's background by using a `Material`.
 *
 * Generated from Godot docs: Sky
 */
class Sky(handle: MemorySegment) : Resource(handle) {
    var skyMaterial: Material?
        @JvmName("skyMaterialProperty")
        get() = getMaterial()
        @JvmName("setSkyMaterialProperty")
        set(value) = setMaterial(value)

    var processMode: Long
        @JvmName("processModeProperty")
        get() = getProcessMode()
        @JvmName("setProcessModeProperty")
        set(value) = setProcessMode(value)

    var radianceSize: Long
        @JvmName("radianceSizeProperty")
        get() = getRadianceSize()
        @JvmName("setRadianceSizeProperty")
        set(value) = setRadianceSize(value)

    /**
     * The `Sky`'s radiance map size. The higher the radiance map size, the more detailed the lighting
     * from the `Sky` will be. Note: Some hardware will have trouble with higher radiance sizes,
     * especially `RADIANCE_SIZE_512` and above. Only use such high values on high-end hardware.
     *
     * Generated from Godot docs: Sky.set_radiance_size
     */
    fun setRadianceSize(size: Long) {
        ObjectCalls.ptrcallWithLongArg(setRadianceSizeBind, handle, size)
    }

    /**
     * The `Sky`'s radiance map size. The higher the radiance map size, the more detailed the lighting
     * from the `Sky` will be. Note: Some hardware will have trouble with higher radiance sizes,
     * especially `RADIANCE_SIZE_512` and above. Only use such high values on high-end hardware.
     *
     * Generated from Godot docs: Sky.get_radiance_size
     */
    fun getRadianceSize(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getRadianceSizeBind, handle)
    }

    /**
     * The method for generating the radiance map from the sky. The radiance map is a cubemap with
     * increasingly blurry versions of the sky corresponding to different levels of roughness. Radiance
     * maps can be expensive to calculate.
     *
     * Generated from Godot docs: Sky.set_process_mode
     */
    fun setProcessMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setProcessModeBind, handle, mode)
    }

    /**
     * The method for generating the radiance map from the sky. The radiance map is a cubemap with
     * increasingly blurry versions of the sky corresponding to different levels of roughness. Radiance
     * maps can be expensive to calculate.
     *
     * Generated from Godot docs: Sky.get_process_mode
     */
    fun getProcessMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getProcessModeBind, handle)
    }

    /**
     * `Material` used to draw the background. Can be `PanoramaSkyMaterial`, `ProceduralSkyMaterial`,
     * `PhysicalSkyMaterial`, or even a `ShaderMaterial` if you want to use your own custom shader.
     *
     * Generated from Godot docs: Sky.set_material
     */
    fun setMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(setMaterialBind, handle, listOf(material?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * `Material` used to draw the background. Can be `PanoramaSkyMaterial`, `ProceduralSkyMaterial`,
     * `PhysicalSkyMaterial`, or even a `ShaderMaterial` if you want to use your own custom shader.
     *
     * Generated from Godot docs: Sky.get_material
     */
    fun getMaterial(): Material? {
        return Material.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMaterialBind, handle))
    }

    companion object {
        const val RADIANCE_SIZE_32: Long = 0L
        const val RADIANCE_SIZE_64: Long = 1L
        const val RADIANCE_SIZE_128: Long = 2L
        const val RADIANCE_SIZE_256: Long = 3L
        const val RADIANCE_SIZE_512: Long = 4L
        const val RADIANCE_SIZE_1024: Long = 5L
        const val RADIANCE_SIZE_2048: Long = 6L
        const val RADIANCE_SIZE_MAX: Long = 7L
        const val PROCESS_MODE_AUTOMATIC: Long = 0L
        const val PROCESS_MODE_QUALITY: Long = 1L
        const val PROCESS_MODE_INCREMENTAL: Long = 2L
        const val PROCESS_MODE_REALTIME: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Sky? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Sky? =
            if (handle.address() == 0L) null else Sky(handle)

        private const val SET_RADIANCE_SIZE_HASH = 1512957179L
        private val setRadianceSizeBind by lazy {
            ObjectCalls.getMethodBind("Sky", "set_radiance_size", SET_RADIANCE_SIZE_HASH)
        }

        private const val GET_RADIANCE_SIZE_HASH = 2708733976L
        private val getRadianceSizeBind by lazy {
            ObjectCalls.getMethodBind("Sky", "get_radiance_size", GET_RADIANCE_SIZE_HASH)
        }

        private const val SET_PROCESS_MODE_HASH = 875986769L
        private val setProcessModeBind by lazy {
            ObjectCalls.getMethodBind("Sky", "set_process_mode", SET_PROCESS_MODE_HASH)
        }

        private const val GET_PROCESS_MODE_HASH = 731245043L
        private val getProcessModeBind by lazy {
            ObjectCalls.getMethodBind("Sky", "get_process_mode", GET_PROCESS_MODE_HASH)
        }

        private const val SET_MATERIAL_HASH = 2757459619L
        private val setMaterialBind by lazy {
            ObjectCalls.getMethodBind("Sky", "set_material", SET_MATERIAL_HASH)
        }

        private const val GET_MATERIAL_HASH = 5934680L
        private val getMaterialBind by lazy {
            ObjectCalls.getMethodBind("Sky", "get_material", GET_MATERIAL_HASH)
        }
    }
}

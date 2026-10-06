package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Defines a 3D environment's background by using a `Material`.
 *
 * Generated from Godot docs: Sky
 */
class Sky(handle: GodotHandle) : Resource(handle) {
    var skyMaterial: Material?
        @JvmName("skyMaterialProperty")
        get() = getMaterial()
        @JvmName("setSkyMaterialProperty")
        set(value) = setMaterial(value)

    var processMode: Sky.ProcessMode
        @JvmName("processModeProperty")
        get() = getProcessMode()
        @JvmName("setProcessModeProperty")
        set(value) = setProcessMode(value)

    var radianceSize: Sky.RadianceSize
        @JvmName("radianceSizeProperty")
        get() = getRadianceSize()
        @JvmName("setRadianceSizeProperty")
        set(value) = setRadianceSize(value)

    /**
     * The `Sky`'s radiance map size. The higher the radiance map size, the more detailed the lighting
     * from the `Sky` will be. Note: Some hardware will have trouble with higher radiance sizes,
     * especially `RadianceSize.SIZE_512` and above. Only use such high values on high-end hardware.
     *
     * Generated from Godot docs: Sky.set_radiance_size
     */
    fun setRadianceSize(size: Sky.RadianceSize) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setRadianceSizeBind, segment, size.value)
    }

    /**
     * The `Sky`'s radiance map size. The higher the radiance map size, the more detailed the lighting
     * from the `Sky` will be. Note: Some hardware will have trouble with higher radiance sizes,
     * especially `RadianceSize.SIZE_512` and above. Only use such high values on high-end hardware.
     *
     * Generated from Godot docs: Sky.get_radiance_size
     */
    fun getRadianceSize(): Sky.RadianceSize {
        checkOpen()
        return Sky.RadianceSize(ObjectCalls.ptrcallNoArgsRetLong(Binds.getRadianceSizeBind, segment))
    }

    /**
     * The method for generating the radiance map from the sky. The radiance map is a cubemap with
     * increasingly blurry versions of the sky corresponding to different levels of roughness. Radiance
     * maps can be expensive to calculate.
     *
     * Generated from Godot docs: Sky.set_process_mode
     */
    fun setProcessMode(mode: Sky.ProcessMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setProcessModeBind, segment, mode.value)
    }

    /**
     * The method for generating the radiance map from the sky. The radiance map is a cubemap with
     * increasingly blurry versions of the sky corresponding to different levels of roughness. Radiance
     * maps can be expensive to calculate.
     *
     * Generated from Godot docs: Sky.get_process_mode
     */
    fun getProcessMode(): Sky.ProcessMode {
        checkOpen()
        return Sky.ProcessMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getProcessModeBind, segment))
    }

    /**
     * `Material` used to draw the background. Can be `PanoramaSkyMaterial`, `ProceduralSkyMaterial`,
     * `PhysicalSkyMaterial`, or even a `ShaderMaterial` if you want to use your own custom shader.
     *
     * Generated from Godot docs: Sky.set_material
     */
    fun setMaterial(material: Material?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setMaterialBind, segment, listOf(material?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * `Material` used to draw the background. Can be `PanoramaSkyMaterial`, `ProceduralSkyMaterial`,
     * `PhysicalSkyMaterial`, or even a `ShaderMaterial` if you want to use your own custom shader.
     *
     * Generated from Godot docs: Sky.get_material
     */
    fun getMaterial(): Material? {
        checkOpen()
        return Material.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getMaterialBind, segment))
    }

    /**
     * Godot's `Sky.RadianceSize` enum as a typed value: `.value` is the raw number Godot uses, and the
     * companion holds the named values (`Sky.RadianceSize.<NAME>`).
     *
     * Generated from Godot docs: Sky.RadianceSize
     */
    @JvmInline
    value class RadianceSize(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Radiance texture size is 32×32 pixels.
             *
             * Generated from Godot docs: Sky.RADIANCE_SIZE_32
             */
            val SIZE_32: RadianceSize get() = RadianceSize(0L)
            /**
             * Radiance texture size is 64×64 pixels.
             *
             * Generated from Godot docs: Sky.RADIANCE_SIZE_64
             */
            val SIZE_64: RadianceSize get() = RadianceSize(1L)
            /**
             * Radiance texture size is 128×128 pixels.
             *
             * Generated from Godot docs: Sky.RADIANCE_SIZE_128
             */
            val SIZE_128: RadianceSize get() = RadianceSize(2L)
            /**
             * Radiance texture size is 256×256 pixels.
             *
             * Generated from Godot docs: Sky.RADIANCE_SIZE_256
             */
            val SIZE_256: RadianceSize get() = RadianceSize(3L)
            /**
             * Radiance texture size is 512×512 pixels.
             *
             * Generated from Godot docs: Sky.RADIANCE_SIZE_512
             */
            val SIZE_512: RadianceSize get() = RadianceSize(4L)
            /**
             * Radiance texture size is 1024×1024 pixels.
             *
             * Generated from Godot docs: Sky.RADIANCE_SIZE_1024
             */
            val SIZE_1024: RadianceSize get() = RadianceSize(5L)
            /**
             * Radiance texture size is 2048×2048 pixels.
             *
             * Generated from Godot docs: Sky.RADIANCE_SIZE_2048
             */
            val SIZE_2048: RadianceSize get() = RadianceSize(6L)
            /**
             * Represents the size of the `RadianceSize` enum.
             *
             * Generated from Godot docs: Sky.RADIANCE_SIZE_MAX
             */
            val MAX: RadianceSize get() = RadianceSize(7L)
        }
    }

    /**
     * Godot's `Sky.ProcessMode` enum as a typed value: `.value` is the raw number Godot uses, and the
     * companion holds the named values (`Sky.ProcessMode.<NAME>`).
     *
     * Generated from Godot docs: Sky.ProcessMode
     */
    @JvmInline
    value class ProcessMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Automatically selects the appropriate process mode based on your sky shader. If your shader uses
             * `TIME` or `POSITION`, this will use `ProcessMode.REALTIME`. If your shader uses any of the
             * `LIGHT_*` variables or any custom uniforms, this uses `ProcessMode.INCREMENTAL`. Otherwise, this
             * defaults to `ProcessMode.QUALITY`.
             *
             * Generated from Godot docs: Sky.PROCESS_MODE_AUTOMATIC
             */
            val AUTOMATIC: ProcessMode get() = ProcessMode(0L)
            /**
             * Uses high quality importance sampling to process the radiance map. In general, this results in
             * much higher quality than `ProcessMode.REALTIME` but takes much longer to generate. This should
             * not be used if you plan on changing the sky at runtime. If you are finding that the reflection
             * is not blurry enough and is showing sparkles or fireflies, try increasing
             * `ProjectSettings.rendering/reflections/sky_reflections/ggx_samples`.
             *
             * Generated from Godot docs: Sky.PROCESS_MODE_QUALITY
             */
            val QUALITY: ProcessMode get() = ProcessMode(1L)
            /**
             * Uses the same high quality importance sampling to process the radiance map as
             * `ProcessMode.QUALITY`, but updates over several frames. The number of frames is determined by
             * `ProjectSettings.rendering/reflections/sky_reflections/roughness_layers`. Use this when you need
             * highest quality radiance maps, but have a sky that updates slowly.
             *
             * Generated from Godot docs: Sky.PROCESS_MODE_INCREMENTAL
             */
            val INCREMENTAL: ProcessMode get() = ProcessMode(2L)
            /**
             * Uses the fast filtering algorithm to process the radiance map. In general this results in lower
             * quality, but substantially faster run times. If you need better quality, but still need to
             * update the sky every frame, consider turning on
             * `ProjectSettings.rendering/reflections/sky_reflections/fast_filter_high_quality`. Note: The fast
             * filtering algorithm is limited to 256×256 cubemaps, so `radiance_size` must be set to
             * `RadianceSize.SIZE_256`. Otherwise, a warning is printed and the overridden radiance size is
             * ignored.
             *
             * Generated from Godot docs: Sky.PROCESS_MODE_REALTIME
             */
            val REALTIME: ProcessMode get() = ProcessMode(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Sky? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Sky? =
            if (handle.address() == 0L) null else RefCounted.owned(Sky(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Sky? =
            if (handle.address() == 0L) null else Sky(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_RADIANCE_SIZE_HASH = 1512957179L
        @JvmField
        val setRadianceSizeBind =
            ObjectCalls.getMethodBind("Sky", "set_radiance_size", SET_RADIANCE_SIZE_HASH)

        private const val GET_RADIANCE_SIZE_HASH = 2708733976L
        @JvmField
        val getRadianceSizeBind =
            ObjectCalls.getMethodBind("Sky", "get_radiance_size", GET_RADIANCE_SIZE_HASH)

        private const val SET_PROCESS_MODE_HASH = 875986769L
        @JvmField
        val setProcessModeBind =
            ObjectCalls.getMethodBind("Sky", "set_process_mode", SET_PROCESS_MODE_HASH)

        private const val GET_PROCESS_MODE_HASH = 731245043L
        @JvmField
        val getProcessModeBind =
            ObjectCalls.getMethodBind("Sky", "get_process_mode", GET_PROCESS_MODE_HASH)

        private const val SET_MATERIAL_HASH = 2757459619L
        @JvmField
        val setMaterialBind =
            ObjectCalls.getMethodBind("Sky", "set_material", SET_MATERIAL_HASH)

        private const val GET_MATERIAL_HASH = 5934680L
        @JvmField
        val getMaterialBind =
            ObjectCalls.getMethodBind("Sky", "get_material", GET_MATERIAL_HASH)
    }
}

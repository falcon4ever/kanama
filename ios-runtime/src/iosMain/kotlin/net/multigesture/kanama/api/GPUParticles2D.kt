package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: GPUParticles2D
 */
class GPUParticles2D(handle: MemorySegment) : Node2D(handle) {
    var emitting: Boolean
        @JvmName("emittingProperty")
        get() = isEmitting()
        @JvmName("setEmittingProperty")
        set(value) = setEmitting(value)

    var amount: Int
        @JvmName("amountProperty")
        get() = getAmount()
        @JvmName("setAmountProperty")
        set(value) = setAmount(value)

    var amountRatio: Double
        @JvmName("amountRatioProperty")
        get() = getAmountRatio()
        @JvmName("setAmountRatioProperty")
        set(value) = setAmountRatio(value)

    var texture: Texture2D?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    var lifetime: Double
        @JvmName("lifetimeProperty")
        get() = getLifetime()
        @JvmName("setLifetimeProperty")
        set(value) = setLifetime(value)

    var interpToEnd: Double
        @JvmName("interpToEndProperty")
        get() = getInterpToEnd()
        @JvmName("setInterpToEndProperty")
        set(value) = setInterpToEnd(value)

    var oneShot: Boolean
        @JvmName("oneShotProperty")
        get() = getOneShot()
        @JvmName("setOneShotProperty")
        set(value) = setOneShot(value)

    var preprocess: Double
        @JvmName("preprocessProperty")
        get() = getPreProcessTime()
        @JvmName("setPreprocessProperty")
        set(value) = setPreProcessTime(value)

    var speedScale: Double
        @JvmName("speedScaleProperty")
        get() = getSpeedScale()
        @JvmName("setSpeedScaleProperty")
        set(value) = setSpeedScale(value)

    var explosiveness: Double
        @JvmName("explosivenessProperty")
        get() = getExplosivenessRatio()
        @JvmName("setExplosivenessProperty")
        set(value) = setExplosivenessRatio(value)

    var randomness: Double
        @JvmName("randomnessProperty")
        get() = getRandomnessRatio()
        @JvmName("setRandomnessProperty")
        set(value) = setRandomnessRatio(value)

    var useFixedSeed: Boolean
        @JvmName("useFixedSeedProperty")
        get() = getUseFixedSeed()
        @JvmName("setUseFixedSeedProperty")
        set(value) = setUseFixedSeed(value)

    var seed: Long
        @JvmName("seedProperty")
        get() = getSeed()
        @JvmName("setSeedProperty")
        set(value) = setSeed(value)

    var fixedFps: Int
        @JvmName("fixedFpsProperty")
        get() = getFixedFps()
        @JvmName("setFixedFpsProperty")
        set(value) = setFixedFps(value)

    var interpolate: Boolean
        @JvmName("interpolateProperty")
        get() = getInterpolate()
        @JvmName("setInterpolateProperty")
        set(value) = setInterpolate(value)

    var fractDelta: Boolean
        @JvmName("fractDeltaProperty")
        get() = getFractionalDelta()
        @JvmName("setFractDeltaProperty")
        set(value) = setFractionalDelta(value)

    var collisionBaseSize: Double
        @JvmName("collisionBaseSizeProperty")
        get() = getCollisionBaseSize()
        @JvmName("setCollisionBaseSizeProperty")
        set(value) = setCollisionBaseSize(value)

    var localCoords: Boolean
        @JvmName("localCoordsProperty")
        get() = getUseLocalCoordinates()
        @JvmName("setLocalCoordsProperty")
        set(value) = setUseLocalCoordinates(value)

    var drawOrder: Long
        @JvmName("drawOrderProperty")
        get() = getDrawOrder()
        @JvmName("setDrawOrderProperty")
        set(value) = setDrawOrder(value)

    var trailEnabled: Boolean
        @JvmName("trailEnabledProperty")
        get() = isTrailEnabled()
        @JvmName("setTrailEnabledProperty")
        set(value) = setTrailEnabled(value)

    var trailLifetime: Double
        @JvmName("trailLifetimeProperty")
        get() = getTrailLifetime()
        @JvmName("setTrailLifetimeProperty")
        set(value) = setTrailLifetime(value)

    var trailSections: Int
        @JvmName("trailSectionsProperty")
        get() = getTrailSections()
        @JvmName("setTrailSectionsProperty")
        set(value) = setTrailSections(value)

    var trailSectionSubdivisions: Int
        @JvmName("trailSectionSubdivisionsProperty")
        get() = getTrailSectionSubdivisions()
        @JvmName("setTrailSectionSubdivisionsProperty")
        set(value) = setTrailSectionSubdivisions(value)

    fun setEmitting(emitting: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEmittingBind, handle, emitting)
    }

    fun setAmount(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setAmountBind, handle, amount)
    }

    fun setLifetime(secs: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLifetimeBind, handle, secs)
    }

    fun setOneShot(secs: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setOneShotBind, handle, secs)
    }

    fun setPreProcessTime(secs: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPreProcessTimeBind, handle, secs)
    }

    fun setExplosivenessRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setExplosivenessRatioBind, handle, ratio)
    }

    fun setRandomnessRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRandomnessRatioBind, handle, ratio)
    }

    fun setUseLocalCoordinates(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseLocalCoordinatesBind, handle, enable)
    }

    fun setFixedFps(fps: Int) {
        ObjectCalls.ptrcallWithIntArg(setFixedFpsBind, handle, fps)
    }

    fun setFractionalDelta(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFractionalDeltaBind, handle, enable)
    }

    fun setInterpolate(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setInterpolateBind, handle, enable)
    }

    fun setSpeedScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSpeedScaleBind, handle, scale)
    }

    fun setCollisionBaseSize(size: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCollisionBaseSizeBind, handle, size)
    }

    fun setInterpToEnd(interp: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setInterpToEndBind, handle, interp)
    }

    fun requestParticlesProcess(processTime: Double, processTimeResidual: Double = 0.0) {
        ObjectCalls.ptrcallWithTwoDoubleArgs(requestParticlesProcessBind, handle, processTime, processTimeResidual)
    }

    fun isEmitting(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEmittingBind, handle)
    }

    fun getAmount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getAmountBind, handle)
    }

    fun getLifetime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLifetimeBind, handle)
    }

    fun getOneShot(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getOneShotBind, handle)
    }

    fun getPreProcessTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPreProcessTimeBind, handle)
    }

    fun getExplosivenessRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getExplosivenessRatioBind, handle)
    }

    fun getRandomnessRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRandomnessRatioBind, handle)
    }

    fun getUseLocalCoordinates(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseLocalCoordinatesBind, handle)
    }

    fun getFixedFps(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFixedFpsBind, handle)
    }

    fun getFractionalDelta(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getFractionalDeltaBind, handle)
    }

    fun getInterpolate(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getInterpolateBind, handle)
    }

    fun getSpeedScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSpeedScaleBind, handle)
    }

    fun getCollisionBaseSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCollisionBaseSizeBind, handle)
    }

    fun getInterpToEnd(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getInterpToEndBind, handle)
    }

    fun setDrawOrder(order: Long) {
        ObjectCalls.ptrcallWithLongArg(setDrawOrderBind, handle, order)
    }

    fun getDrawOrder(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDrawOrderBind, handle)
    }

    fun setTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, handle))
    }

    fun restart(keepSeed: Boolean = false) {
        ObjectCalls.ptrcallWithBoolArg(restartBind, handle, keepSeed)
    }

    fun setTrailEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setTrailEnabledBind, handle, enabled)
    }

    fun setTrailLifetime(secs: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTrailLifetimeBind, handle, secs)
    }

    fun isTrailEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isTrailEnabledBind, handle)
    }

    fun getTrailLifetime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTrailLifetimeBind, handle)
    }

    fun setTrailSections(sections: Int) {
        ObjectCalls.ptrcallWithIntArg(setTrailSectionsBind, handle, sections)
    }

    fun getTrailSections(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTrailSectionsBind, handle)
    }

    fun setTrailSectionSubdivisions(subdivisions: Int) {
        ObjectCalls.ptrcallWithIntArg(setTrailSectionSubdivisionsBind, handle, subdivisions)
    }

    fun getTrailSectionSubdivisions(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTrailSectionSubdivisionsBind, handle)
    }

    fun convertFromParticles(particles: Node) {
        ObjectCalls.ptrcallWithObjectArgs(convertFromParticlesBind, handle, listOf(particles.handle))
    }

    fun setAmountRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAmountRatioBind, handle, ratio)
    }

    fun getAmountRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAmountRatioBind, handle)
    }

    fun setUseFixedSeed(useFixedSeed: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseFixedSeedBind, handle, useFixedSeed)
    }

    fun getUseFixedSeed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseFixedSeedBind, handle)
    }

    fun setSeed(seed: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setSeedBind, handle, seed)
    }

    fun getSeed(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getSeedBind, handle)
    }

    object Signals {
        const val finished: String = "finished"
    }

    companion object {
        const val DRAW_ORDER_INDEX: Long = 0L
        const val DRAW_ORDER_LIFETIME: Long = 1L
        const val DRAW_ORDER_REVERSE_LIFETIME: Long = 2L
        const val EMIT_FLAG_POSITION: Long = 1L
        const val EMIT_FLAG_ROTATION_SCALE: Long = 2L
        const val EMIT_FLAG_VELOCITY: Long = 4L
        const val EMIT_FLAG_COLOR: Long = 8L
        const val EMIT_FLAG_CUSTOM: Long = 16L

        fun fromHandle(handle: MemorySegment): GPUParticles2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GPUParticles2D? =
            if (handle.address() == 0L) null else GPUParticles2D(handle)

        private const val SET_EMITTING_HASH = 2586408642L
        private val setEmittingBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "set_emitting", SET_EMITTING_HASH)
        }

        private const val SET_AMOUNT_HASH = 1286410249L
        private val setAmountBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "set_amount", SET_AMOUNT_HASH)
        }

        private const val SET_LIFETIME_HASH = 373806689L
        private val setLifetimeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "set_lifetime", SET_LIFETIME_HASH)
        }

        private const val SET_ONE_SHOT_HASH = 2586408642L
        private val setOneShotBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "set_one_shot", SET_ONE_SHOT_HASH)
        }

        private const val SET_PRE_PROCESS_TIME_HASH = 373806689L
        private val setPreProcessTimeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "set_pre_process_time", SET_PRE_PROCESS_TIME_HASH)
        }

        private const val SET_EXPLOSIVENESS_RATIO_HASH = 373806689L
        private val setExplosivenessRatioBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "set_explosiveness_ratio", SET_EXPLOSIVENESS_RATIO_HASH)
        }

        private const val SET_RANDOMNESS_RATIO_HASH = 373806689L
        private val setRandomnessRatioBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "set_randomness_ratio", SET_RANDOMNESS_RATIO_HASH)
        }

        private const val SET_USE_LOCAL_COORDINATES_HASH = 2586408642L
        private val setUseLocalCoordinatesBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "set_use_local_coordinates", SET_USE_LOCAL_COORDINATES_HASH)
        }

        private const val SET_FIXED_FPS_HASH = 1286410249L
        private val setFixedFpsBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "set_fixed_fps", SET_FIXED_FPS_HASH)
        }

        private const val SET_FRACTIONAL_DELTA_HASH = 2586408642L
        private val setFractionalDeltaBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "set_fractional_delta", SET_FRACTIONAL_DELTA_HASH)
        }

        private const val SET_INTERPOLATE_HASH = 2586408642L
        private val setInterpolateBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "set_interpolate", SET_INTERPOLATE_HASH)
        }

        private const val SET_SPEED_SCALE_HASH = 373806689L
        private val setSpeedScaleBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "set_speed_scale", SET_SPEED_SCALE_HASH)
        }

        private const val SET_COLLISION_BASE_SIZE_HASH = 373806689L
        private val setCollisionBaseSizeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "set_collision_base_size", SET_COLLISION_BASE_SIZE_HASH)
        }

        private const val SET_INTERP_TO_END_HASH = 373806689L
        private val setInterpToEndBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "set_interp_to_end", SET_INTERP_TO_END_HASH)
        }

        private const val REQUEST_PARTICLES_PROCESS_HASH = 2019720106L
        private val requestParticlesProcessBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "request_particles_process", REQUEST_PARTICLES_PROCESS_HASH)
        }

        private const val IS_EMITTING_HASH = 36873697L
        private val isEmittingBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "is_emitting", IS_EMITTING_HASH)
        }

        private const val GET_AMOUNT_HASH = 3905245786L
        private val getAmountBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "get_amount", GET_AMOUNT_HASH)
        }

        private const val GET_LIFETIME_HASH = 1740695150L
        private val getLifetimeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "get_lifetime", GET_LIFETIME_HASH)
        }

        private const val GET_ONE_SHOT_HASH = 36873697L
        private val getOneShotBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "get_one_shot", GET_ONE_SHOT_HASH)
        }

        private const val GET_PRE_PROCESS_TIME_HASH = 1740695150L
        private val getPreProcessTimeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "get_pre_process_time", GET_PRE_PROCESS_TIME_HASH)
        }

        private const val GET_EXPLOSIVENESS_RATIO_HASH = 1740695150L
        private val getExplosivenessRatioBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "get_explosiveness_ratio", GET_EXPLOSIVENESS_RATIO_HASH)
        }

        private const val GET_RANDOMNESS_RATIO_HASH = 1740695150L
        private val getRandomnessRatioBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "get_randomness_ratio", GET_RANDOMNESS_RATIO_HASH)
        }

        private const val GET_USE_LOCAL_COORDINATES_HASH = 36873697L
        private val getUseLocalCoordinatesBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "get_use_local_coordinates", GET_USE_LOCAL_COORDINATES_HASH)
        }

        private const val GET_FIXED_FPS_HASH = 3905245786L
        private val getFixedFpsBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "get_fixed_fps", GET_FIXED_FPS_HASH)
        }

        private const val GET_FRACTIONAL_DELTA_HASH = 36873697L
        private val getFractionalDeltaBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "get_fractional_delta", GET_FRACTIONAL_DELTA_HASH)
        }

        private const val GET_INTERPOLATE_HASH = 36873697L
        private val getInterpolateBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "get_interpolate", GET_INTERPOLATE_HASH)
        }

        private const val GET_SPEED_SCALE_HASH = 1740695150L
        private val getSpeedScaleBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "get_speed_scale", GET_SPEED_SCALE_HASH)
        }

        private const val GET_COLLISION_BASE_SIZE_HASH = 1740695150L
        private val getCollisionBaseSizeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "get_collision_base_size", GET_COLLISION_BASE_SIZE_HASH)
        }

        private const val GET_INTERP_TO_END_HASH = 1740695150L
        private val getInterpToEndBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "get_interp_to_end", GET_INTERP_TO_END_HASH)
        }

        private const val SET_DRAW_ORDER_HASH = 1939677959L
        private val setDrawOrderBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "set_draw_order", SET_DRAW_ORDER_HASH)
        }

        private const val GET_DRAW_ORDER_HASH = 941479095L
        private val getDrawOrderBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "get_draw_order", GET_DRAW_ORDER_HASH)
        }

        private const val SET_TEXTURE_HASH = 4051416890L
        private val setTextureBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "set_texture", SET_TEXTURE_HASH)
        }

        private const val GET_TEXTURE_HASH = 3635182373L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "get_texture", GET_TEXTURE_HASH)
        }

        private const val RESTART_HASH = 107499316L
        private val restartBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "restart", RESTART_HASH)
        }

        private const val SET_TRAIL_ENABLED_HASH = 2586408642L
        private val setTrailEnabledBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "set_trail_enabled", SET_TRAIL_ENABLED_HASH)
        }

        private const val SET_TRAIL_LIFETIME_HASH = 373806689L
        private val setTrailLifetimeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "set_trail_lifetime", SET_TRAIL_LIFETIME_HASH)
        }

        private const val IS_TRAIL_ENABLED_HASH = 36873697L
        private val isTrailEnabledBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "is_trail_enabled", IS_TRAIL_ENABLED_HASH)
        }

        private const val GET_TRAIL_LIFETIME_HASH = 1740695150L
        private val getTrailLifetimeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "get_trail_lifetime", GET_TRAIL_LIFETIME_HASH)
        }

        private const val SET_TRAIL_SECTIONS_HASH = 1286410249L
        private val setTrailSectionsBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "set_trail_sections", SET_TRAIL_SECTIONS_HASH)
        }

        private const val GET_TRAIL_SECTIONS_HASH = 3905245786L
        private val getTrailSectionsBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "get_trail_sections", GET_TRAIL_SECTIONS_HASH)
        }

        private const val SET_TRAIL_SECTION_SUBDIVISIONS_HASH = 1286410249L
        private val setTrailSectionSubdivisionsBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "set_trail_section_subdivisions", SET_TRAIL_SECTION_SUBDIVISIONS_HASH)
        }

        private const val GET_TRAIL_SECTION_SUBDIVISIONS_HASH = 3905245786L
        private val getTrailSectionSubdivisionsBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "get_trail_section_subdivisions", GET_TRAIL_SECTION_SUBDIVISIONS_HASH)
        }

        private const val CONVERT_FROM_PARTICLES_HASH = 1078189570L
        private val convertFromParticlesBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "convert_from_particles", CONVERT_FROM_PARTICLES_HASH)
        }

        private const val SET_AMOUNT_RATIO_HASH = 373806689L
        private val setAmountRatioBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "set_amount_ratio", SET_AMOUNT_RATIO_HASH)
        }

        private const val GET_AMOUNT_RATIO_HASH = 1740695150L
        private val getAmountRatioBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "get_amount_ratio", GET_AMOUNT_RATIO_HASH)
        }

        private const val SET_USE_FIXED_SEED_HASH = 2586408642L
        private val setUseFixedSeedBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "set_use_fixed_seed", SET_USE_FIXED_SEED_HASH)
        }

        private const val GET_USE_FIXED_SEED_HASH = 36873697L
        private val getUseFixedSeedBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "get_use_fixed_seed", GET_USE_FIXED_SEED_HASH)
        }

        private const val SET_SEED_HASH = 1286410249L
        private val setSeedBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "set_seed", SET_SEED_HASH)
        }

        private const val GET_SEED_HASH = 3905245786L
        private val getSeedBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles2D", "get_seed", GET_SEED_HASH)
        }
    }
}

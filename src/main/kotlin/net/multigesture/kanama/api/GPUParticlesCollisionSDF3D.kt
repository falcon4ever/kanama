package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

/**
 * A baked signed distance field 3D particle collision shape affecting `GPUParticles3D` nodes.
 *
 * Generated from Godot docs: GPUParticlesCollisionSDF3D
 */
class GPUParticlesCollisionSDF3D(handle: MemorySegment) : GPUParticlesCollision3D(handle) {
    var size: Vector3
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    var resolution: Long
        @JvmName("resolutionProperty")
        get() = getResolution()
        @JvmName("setResolutionProperty")
        set(value) = setResolution(value)

    var thickness: Double
        @JvmName("thicknessProperty")
        get() = getThickness()
        @JvmName("setThicknessProperty")
        set(value) = setThickness(value)

    var bakeMask: Long
        @JvmName("bakeMaskProperty")
        get() = getBakeMask()
        @JvmName("setBakeMaskProperty")
        set(value) = setBakeMask(value)

    var texture: Texture3D?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    fun setSize(size: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setSizeBind, handle, size)
    }

    fun getSize(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getSizeBind, handle)
    }

    fun setResolution(resolution: Long) {
        ObjectCalls.ptrcallWithLongArg(setResolutionBind, handle, resolution)
    }

    fun getResolution(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getResolutionBind, handle)
    }

    fun setTexture(texture: Texture3D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getTexture(): Texture3D? {
        return Texture3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, handle))
    }

    fun setThickness(thickness: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setThicknessBind, handle, thickness)
    }

    fun getThickness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getThicknessBind, handle)
    }

    fun setBakeMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setBakeMaskBind, handle, mask)
    }

    fun getBakeMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getBakeMaskBind, handle)
    }

    fun setBakeMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setBakeMaskValueBind, handle, layerNumber, value)
    }

    fun getBakeMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getBakeMaskValueBind, handle, layerNumber)
    }

    companion object {
        const val RESOLUTION_16: Long = 0L
        const val RESOLUTION_32: Long = 1L
        const val RESOLUTION_64: Long = 2L
        const val RESOLUTION_128: Long = 3L
        const val RESOLUTION_256: Long = 4L
        const val RESOLUTION_512: Long = 5L
        const val RESOLUTION_MAX: Long = 6L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): GPUParticlesCollisionSDF3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GPUParticlesCollisionSDF3D? =
            if (handle.address() == 0L) null else GPUParticlesCollisionSDF3D(handle)

        private const val SET_SIZE_HASH = 3460891852L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionSDF3D", "set_size", SET_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 3360562783L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionSDF3D", "get_size", GET_SIZE_HASH)
        }

        private const val SET_RESOLUTION_HASH = 1155629297L
        private val setResolutionBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionSDF3D", "set_resolution", SET_RESOLUTION_HASH)
        }

        private const val GET_RESOLUTION_HASH = 2919555867L
        private val getResolutionBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionSDF3D", "get_resolution", GET_RESOLUTION_HASH)
        }

        private const val SET_TEXTURE_HASH = 1188404210L
        private val setTextureBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionSDF3D", "set_texture", SET_TEXTURE_HASH)
        }

        private const val GET_TEXTURE_HASH = 373985333L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionSDF3D", "get_texture", GET_TEXTURE_HASH)
        }

        private const val SET_THICKNESS_HASH = 373806689L
        private val setThicknessBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionSDF3D", "set_thickness", SET_THICKNESS_HASH)
        }

        private const val GET_THICKNESS_HASH = 1740695150L
        private val getThicknessBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionSDF3D", "get_thickness", GET_THICKNESS_HASH)
        }

        private const val SET_BAKE_MASK_HASH = 1286410249L
        private val setBakeMaskBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionSDF3D", "set_bake_mask", SET_BAKE_MASK_HASH)
        }

        private const val GET_BAKE_MASK_HASH = 3905245786L
        private val getBakeMaskBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionSDF3D", "get_bake_mask", GET_BAKE_MASK_HASH)
        }

        private const val SET_BAKE_MASK_VALUE_HASH = 300928843L
        private val setBakeMaskValueBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionSDF3D", "set_bake_mask_value", SET_BAKE_MASK_VALUE_HASH)
        }

        private const val GET_BAKE_MASK_VALUE_HASH = 1116898809L
        private val getBakeMaskValueBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesCollisionSDF3D", "get_bake_mask_value", GET_BAKE_MASK_VALUE_HASH)
        }
    }
}

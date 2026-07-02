package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

/**
 * A box-shaped attractor with varying directions and strengths defined in it that influences
 * particles from `GPUParticles3D` nodes.
 *
 * Generated from Godot docs: GPUParticlesAttractorVectorField3D
 */
class GPUParticlesAttractorVectorField3D(handle: MemorySegment) : GPUParticlesAttractor3D(handle) {
    var size: Vector3
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

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

    fun setTexture(texture: Texture3D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getTexture(): Texture3D? {
        return Texture3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): GPUParticlesAttractorVectorField3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GPUParticlesAttractorVectorField3D? =
            if (handle.address() == 0L) null else GPUParticlesAttractorVectorField3D(handle)

        private const val SET_SIZE_HASH = 3460891852L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesAttractorVectorField3D", "set_size", SET_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 3360562783L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesAttractorVectorField3D", "get_size", GET_SIZE_HASH)
        }

        private const val SET_TEXTURE_HASH = 1188404210L
        private val setTextureBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesAttractorVectorField3D", "set_texture", SET_TEXTURE_HASH)
        }

        private const val GET_TEXTURE_HASH = 373985333L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("GPUParticlesAttractorVectorField3D", "get_texture", GET_TEXTURE_HASH)
        }
    }
}

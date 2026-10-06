package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector3

/**
 * A box-shaped attractor with varying directions and strengths defined in it that influences
 * particles from `GPUParticles3D` nodes.
 *
 * Generated from Godot docs: GPUParticlesAttractorVectorField3D
 */
class GPUParticlesAttractorVectorField3D(handle: GodotHandle) : GPUParticlesAttractor3D(handle) {
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

    /**
     * The size of the vector field box in 3D units.
     *
     * Generated from Godot docs: GPUParticlesAttractorVectorField3D.set_size
     */
    fun setSize(size: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(Binds.setSizeBind, segment, size)
    }

    /**
     * The size of the vector field box in 3D units.
     *
     * Generated from Godot docs: GPUParticlesAttractorVectorField3D.get_size
     */
    fun getSize(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getSizeBind, segment)
    }

    /**
     * The 3D texture to be used. Values are linearly interpolated between the texture's pixels. Note:
     * To get better performance, the 3D texture's resolution should reflect the `size` of the
     * attractor. Since particle attraction is usually low-frequency data, the texture can be kept at a
     * low resolution such as 64×64×64.
     *
     * Generated from Godot docs: GPUParticlesAttractorVectorField3D.set_texture
     */
    fun setTexture(texture: Texture3D?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The 3D texture to be used. Values are linearly interpolated between the texture's pixels. Note:
     * To get better performance, the 3D texture's resolution should reflect the `size` of the
     * attractor. Since particle attraction is usually low-frequency data, the texture can be kept at a
     * low resolution such as 64×64×64.
     *
     * Generated from Godot docs: GPUParticlesAttractorVectorField3D.get_texture
     */
    fun getTexture(): Texture3D? {
        return Texture3D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getTextureBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GPUParticlesAttractorVectorField3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): GPUParticlesAttractorVectorField3D? =
            if (handle.address() == 0L) null else GPUParticlesAttractorVectorField3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_SIZE_HASH = 3460891852L
        @JvmField
        val setSizeBind =
            ObjectCalls.getMethodBind("GPUParticlesAttractorVectorField3D", "set_size", SET_SIZE_HASH)

        private const val GET_SIZE_HASH = 3360562783L
        @JvmField
        val getSizeBind =
            ObjectCalls.getMethodBind("GPUParticlesAttractorVectorField3D", "get_size", GET_SIZE_HASH)

        private const val SET_TEXTURE_HASH = 1188404210L
        @JvmField
        val setTextureBind =
            ObjectCalls.getMethodBind("GPUParticlesAttractorVectorField3D", "set_texture", SET_TEXTURE_HASH)

        private const val GET_TEXTURE_HASH = 373985333L
        @JvmField
        val getTextureBind =
            ObjectCalls.getMethodBind("GPUParticlesAttractorVectorField3D", "get_texture", GET_TEXTURE_HASH)
    }
}

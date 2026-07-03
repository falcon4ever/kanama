package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Hit group (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDHitGroup
 */
class RDHitGroup(handle: MemorySegment) : RefCounted(handle) {
    var closestHitShader: RDPipelineShader?
        @JvmName("closestHitShaderProperty")
        get() = getClosestHitShader()
        @JvmName("setClosestHitShaderProperty")
        set(value) = setClosestHitShader(value)

    var anyHitShader: RDPipelineShader?
        @JvmName("anyHitShaderProperty")
        get() = getAnyHitShader()
        @JvmName("setAnyHitShaderProperty")
        set(value) = setAnyHitShader(value)

    var intersectionShader: RDPipelineShader?
        @JvmName("intersectionShaderProperty")
        get() = getIntersectionShader()
        @JvmName("setIntersectionShaderProperty")
        set(value) = setIntersectionShader(value)

    /**
     * Closest-hit shader for this hit group. Executed for the closest intersection. Can be `null`.
     *
     * Generated from Godot docs: RDHitGroup.set_closest_hit_shader
     */
    fun setClosestHitShader(pMember: RDPipelineShader?) {
        ObjectCalls.ptrcallWithObjectArgs(setClosestHitShaderBind, handle, listOf(pMember?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Closest-hit shader for this hit group. Executed for the closest intersection. Can be `null`.
     *
     * Generated from Godot docs: RDHitGroup.get_closest_hit_shader
     */
    fun getClosestHitShader(): RDPipelineShader? {
        return RDPipelineShader.wrap(ObjectCalls.ptrcallNoArgsRetObject(getClosestHitShaderBind, handle))
    }

    /**
     * Any-hit shader for this hit group. Executed for each potential intersection. Can be `null`.
     *
     * Generated from Godot docs: RDHitGroup.set_any_hit_shader
     */
    fun setAnyHitShader(pMember: RDPipelineShader?) {
        ObjectCalls.ptrcallWithObjectArgs(setAnyHitShaderBind, handle, listOf(pMember?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Any-hit shader for this hit group. Executed for each potential intersection. Can be `null`.
     *
     * Generated from Godot docs: RDHitGroup.get_any_hit_shader
     */
    fun getAnyHitShader(): RDPipelineShader? {
        return RDPipelineShader.wrap(ObjectCalls.ptrcallNoArgsRetObject(getAnyHitShaderBind, handle))
    }

    /**
     * Intersection shader for this hit group. Required for non-triangle geometry. Must be `null` when
     * using for triangle geometry.
     *
     * Generated from Godot docs: RDHitGroup.set_intersection_shader
     */
    fun setIntersectionShader(pMember: RDPipelineShader?) {
        ObjectCalls.ptrcallWithObjectArgs(setIntersectionShaderBind, handle, listOf(pMember?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Intersection shader for this hit group. Required for non-triangle geometry. Must be `null` when
     * using for triangle geometry.
     *
     * Generated from Godot docs: RDHitGroup.get_intersection_shader
     */
    fun getIntersectionShader(): RDPipelineShader? {
        return RDPipelineShader.wrap(ObjectCalls.ptrcallNoArgsRetObject(getIntersectionShaderBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RDHitGroup? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RDHitGroup? =
            if (handle.address() == 0L) null else RDHitGroup(handle)

        private const val SET_CLOSEST_HIT_SHADER_HASH = 2556777288L
        private val setClosestHitShaderBind by lazy {
            ObjectCalls.getMethodBind("RDHitGroup", "set_closest_hit_shader", SET_CLOSEST_HIT_SHADER_HASH)
        }

        private const val GET_CLOSEST_HIT_SHADER_HASH = 2937716847L
        private val getClosestHitShaderBind by lazy {
            ObjectCalls.getMethodBind("RDHitGroup", "get_closest_hit_shader", GET_CLOSEST_HIT_SHADER_HASH)
        }

        private const val SET_ANY_HIT_SHADER_HASH = 2556777288L
        private val setAnyHitShaderBind by lazy {
            ObjectCalls.getMethodBind("RDHitGroup", "set_any_hit_shader", SET_ANY_HIT_SHADER_HASH)
        }

        private const val GET_ANY_HIT_SHADER_HASH = 2937716847L
        private val getAnyHitShaderBind by lazy {
            ObjectCalls.getMethodBind("RDHitGroup", "get_any_hit_shader", GET_ANY_HIT_SHADER_HASH)
        }

        private const val SET_INTERSECTION_SHADER_HASH = 2556777288L
        private val setIntersectionShaderBind by lazy {
            ObjectCalls.getMethodBind("RDHitGroup", "set_intersection_shader", SET_INTERSECTION_SHADER_HASH)
        }

        private const val GET_INTERSECTION_SHADER_HASH = 2937716847L
        private val getIntersectionShaderBind by lazy {
            ObjectCalls.getMethodBind("RDHitGroup", "get_intersection_shader", GET_INTERSECTION_SHADER_HASH)
        }
    }
}

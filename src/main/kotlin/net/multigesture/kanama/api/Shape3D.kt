package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Abstract base class for 3D shapes used for physics collision.
 *
 * Generated from Godot docs: Shape3D
 */
open class Shape3D(handle: MemorySegment) : Resource(handle) {
    var customSolverBias: Double
        @JvmName("customSolverBiasProperty")
        get() = getCustomSolverBias()
        @JvmName("setCustomSolverBiasProperty")
        set(value) = setCustomSolverBias(value)

    var margin: Double
        @JvmName("marginProperty")
        get() = getMargin()
        @JvmName("setMarginProperty")
        set(value) = setMargin(value)

    fun setCustomSolverBias(bias: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCustomSolverBiasBind, handle, bias)
    }

    fun getCustomSolverBias(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCustomSolverBiasBind, handle)
    }

    fun setMargin(margin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMarginBind, handle, margin)
    }

    fun getMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMarginBind, handle)
    }

    fun getDebugMesh(): ArrayMesh? {
        return ArrayMesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(getDebugMeshBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Shape3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Shape3D? =
            if (handle.address() == 0L) null else Shape3D(handle)

        private const val SET_CUSTOM_SOLVER_BIAS_HASH = 373806689L
        private val setCustomSolverBiasBind by lazy {
            ObjectCalls.getMethodBind("Shape3D", "set_custom_solver_bias", SET_CUSTOM_SOLVER_BIAS_HASH)
        }

        private const val GET_CUSTOM_SOLVER_BIAS_HASH = 1740695150L
        private val getCustomSolverBiasBind by lazy {
            ObjectCalls.getMethodBind("Shape3D", "get_custom_solver_bias", GET_CUSTOM_SOLVER_BIAS_HASH)
        }

        private const val SET_MARGIN_HASH = 373806689L
        private val setMarginBind by lazy {
            ObjectCalls.getMethodBind("Shape3D", "set_margin", SET_MARGIN_HASH)
        }

        private const val GET_MARGIN_HASH = 1740695150L
        private val getMarginBind by lazy {
            ObjectCalls.getMethodBind("Shape3D", "get_margin", GET_MARGIN_HASH)
        }

        private const val GET_DEBUG_MESH_HASH = 1605880883L
        private val getDebugMeshBind by lazy {
            ObjectCalls.getMethodBind("Shape3D", "get_debug_mesh", GET_DEBUG_MESH_HASH)
        }
    }
}

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

    /**
     * The shape's custom solver bias. Defines how much bodies react to enforce contact separation when
     * this shape is involved. When set to `0`, the default value from
     * `ProjectSettings.physics/3d/solver/default_contact_bias` is used. Note: `custom_solver_bias` is
     * only effective when using GodotPhysics3D. It has no effect when using Jolt Physics.
     *
     * Generated from Godot docs: Shape3D.set_custom_solver_bias
     */
    fun setCustomSolverBias(bias: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCustomSolverBiasBind, handle, bias)
    }

    /**
     * The shape's custom solver bias. Defines how much bodies react to enforce contact separation when
     * this shape is involved. When set to `0`, the default value from
     * `ProjectSettings.physics/3d/solver/default_contact_bias` is used. Note: `custom_solver_bias` is
     * only effective when using GodotPhysics3D. It has no effect when using Jolt Physics.
     *
     * Generated from Godot docs: Shape3D.get_custom_solver_bias
     */
    fun getCustomSolverBias(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCustomSolverBiasBind, handle)
    }

    /**
     * The collision margin for the shape. This is not used in Godot Physics. Collision margins allow
     * collision detection to be more efficient by adding an extra shell around shapes. Collision
     * algorithms are more expensive when objects overlap by more than their margin, so a higher value
     * for margins is better for performance, at the cost of accuracy around edges as it makes them
     * less sharp.
     *
     * Generated from Godot docs: Shape3D.set_margin
     */
    fun setMargin(margin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMarginBind, handle, margin)
    }

    /**
     * The collision margin for the shape. This is not used in Godot Physics. Collision margins allow
     * collision detection to be more efficient by adding an extra shell around shapes. Collision
     * algorithms are more expensive when objects overlap by more than their margin, so a higher value
     * for margins is better for performance, at the cost of accuracy around edges as it makes them
     * less sharp.
     *
     * Generated from Godot docs: Shape3D.get_margin
     */
    fun getMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMarginBind, handle)
    }

    /**
     * Returns the `ArrayMesh` used to draw the debug collision for this `Shape3D`.
     *
     * Generated from Godot docs: Shape3D.get_debug_mesh
     */
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

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: Shape2D
 */
open class Shape2D(handle: MemorySegment) : Resource(handle) {
    var customSolverBias: Double
        @JvmName("customSolverBiasProperty")
        get() = getCustomSolverBias()
        @JvmName("setCustomSolverBiasProperty")
        set(value) = setCustomSolverBias(value)

    fun setCustomSolverBias(bias: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCustomSolverBiasBind, handle, bias)
    }

    fun getCustomSolverBias(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCustomSolverBiasBind, handle)
    }

    fun collide(localXform: Transform2D, withShape: Shape2D, shapeXform: Transform2D): Boolean {
        return ObjectCalls.ptrcallWithTransform2DObjectTransform2DArgsRetBool(collideBind, handle, localXform, withShape.requireOpenHandle(), shapeXform)
    }

    fun collideWithMotion(localXform: Transform2D, localMotion: Vector2, withShape: Shape2D, shapeXform: Transform2D, shapeMotion: Vector2): Boolean {
        return ObjectCalls.ptrcallWithTransform2DVector2ObjectTransform2DVector2ArgsRetBool(collideWithMotionBind, handle, localXform, localMotion, withShape.requireOpenHandle(), shapeXform, shapeMotion)
    }

    fun draw(canvasItem: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(drawBind, handle, canvasItem, color)
    }

    fun getRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getRectBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): Shape2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Shape2D? =
            if (handle.address() == 0L) null else Shape2D(handle)

        private const val SET_CUSTOM_SOLVER_BIAS_HASH = 373806689L
        private val setCustomSolverBiasBind by lazy {
            ObjectCalls.getMethodBind("Shape2D", "set_custom_solver_bias", SET_CUSTOM_SOLVER_BIAS_HASH)
        }

        private const val GET_CUSTOM_SOLVER_BIAS_HASH = 1740695150L
        private val getCustomSolverBiasBind by lazy {
            ObjectCalls.getMethodBind("Shape2D", "get_custom_solver_bias", GET_CUSTOM_SOLVER_BIAS_HASH)
        }

        private const val COLLIDE_HASH = 3709843132L
        private val collideBind by lazy {
            ObjectCalls.getMethodBind("Shape2D", "collide", COLLIDE_HASH)
        }

        private const val COLLIDE_WITH_MOTION_HASH = 2869556801L
        private val collideWithMotionBind by lazy {
            ObjectCalls.getMethodBind("Shape2D", "collide_with_motion", COLLIDE_WITH_MOTION_HASH)
        }

        private const val DRAW_HASH = 2948539648L
        private val drawBind by lazy {
            ObjectCalls.getMethodBind("Shape2D", "draw", DRAW_HASH)
        }

        private const val GET_RECT_HASH = 1639390495L
        private val getRectBind by lazy {
            ObjectCalls.getMethodBind("Shape2D", "get_rect", GET_RECT_HASH)
        }
    }
}

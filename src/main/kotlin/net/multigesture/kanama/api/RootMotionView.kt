package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.NodePath

/**
 * Editor-only helper for setting up root motion in `AnimationMixer`.
 *
 * Generated from Godot docs: RootMotionView
 */
class RootMotionView(handle: MemorySegment) : VisualInstance3D(handle) {
    var animationPath: NodePath
        @JvmName("animationPathProperty")
        get() = getAnimationPath()
        @JvmName("setAnimationPathProperty")
        set(value) = setAnimationPath(value)

    var color: Color
        @JvmName("colorProperty")
        get() = getColor()
        @JvmName("setColorProperty")
        set(value) = setColor(value)

    var cellSize: Double
        @JvmName("cellSizeProperty")
        get() = getCellSize()
        @JvmName("setCellSizeProperty")
        set(value) = setCellSize(value)

    var radius: Double
        @JvmName("radiusProperty")
        get() = getRadius()
        @JvmName("setRadiusProperty")
        set(value) = setRadius(value)

    var zeroY: Boolean
        @JvmName("zeroYProperty")
        get() = getZeroY()
        @JvmName("setZeroYProperty")
        set(value) = setZeroY(value)

    fun setAnimationPath(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setAnimationPathBind, handle, path)
    }

    fun getAnimationPath(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getAnimationPathBind, handle)
    }

    fun setColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setColorBind, handle, color)
    }

    fun getColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getColorBind, handle)
    }

    fun setCellSize(size: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCellSizeBind, handle, size)
    }

    fun getCellSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCellSizeBind, handle)
    }

    fun setRadius(size: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRadiusBind, handle, size)
    }

    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadiusBind, handle)
    }

    fun setZeroY(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setZeroYBind, handle, enable)
    }

    fun getZeroY(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getZeroYBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RootMotionView? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RootMotionView? =
            if (handle.address() == 0L) null else RootMotionView(handle)

        private const val SET_ANIMATION_PATH_HASH = 1348162250L
        private val setAnimationPathBind by lazy {
            ObjectCalls.getMethodBind("RootMotionView", "set_animation_path", SET_ANIMATION_PATH_HASH)
        }

        private const val GET_ANIMATION_PATH_HASH = 4075236667L
        private val getAnimationPathBind by lazy {
            ObjectCalls.getMethodBind("RootMotionView", "get_animation_path", GET_ANIMATION_PATH_HASH)
        }

        private const val SET_COLOR_HASH = 2920490490L
        private val setColorBind by lazy {
            ObjectCalls.getMethodBind("RootMotionView", "set_color", SET_COLOR_HASH)
        }

        private const val GET_COLOR_HASH = 3444240500L
        private val getColorBind by lazy {
            ObjectCalls.getMethodBind("RootMotionView", "get_color", GET_COLOR_HASH)
        }

        private const val SET_CELL_SIZE_HASH = 373806689L
        private val setCellSizeBind by lazy {
            ObjectCalls.getMethodBind("RootMotionView", "set_cell_size", SET_CELL_SIZE_HASH)
        }

        private const val GET_CELL_SIZE_HASH = 1740695150L
        private val getCellSizeBind by lazy {
            ObjectCalls.getMethodBind("RootMotionView", "get_cell_size", GET_CELL_SIZE_HASH)
        }

        private const val SET_RADIUS_HASH = 373806689L
        private val setRadiusBind by lazy {
            ObjectCalls.getMethodBind("RootMotionView", "set_radius", SET_RADIUS_HASH)
        }

        private const val GET_RADIUS_HASH = 1740695150L
        private val getRadiusBind by lazy {
            ObjectCalls.getMethodBind("RootMotionView", "get_radius", GET_RADIUS_HASH)
        }

        private const val SET_ZERO_Y_HASH = 2586408642L
        private val setZeroYBind by lazy {
            ObjectCalls.getMethodBind("RootMotionView", "set_zero_y", SET_ZERO_Y_HASH)
        }

        private const val GET_ZERO_Y_HASH = 36873697L
        private val getZeroYBind by lazy {
            ObjectCalls.getMethodBind("RootMotionView", "get_zero_y", GET_ZERO_Y_HASH)
        }
    }
}

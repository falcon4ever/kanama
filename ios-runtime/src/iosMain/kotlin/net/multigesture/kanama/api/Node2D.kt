package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: Node2D
 */
open class Node2D(handle: MemorySegment) : CanvasItem(handle) {
    var position: Vector2
        @JvmName("positionProperty")
        get() = getPosition()
        @JvmName("setPositionProperty")
        set(value) = setPosition(value)

    var rotation: Double
        @JvmName("rotationProperty")
        get() = getRotation()
        @JvmName("setRotationProperty")
        set(value) = setRotation(value)

    var rotationDegrees: Double
        @JvmName("rotationDegreesProperty")
        get() = getRotationDegrees()
        @JvmName("setRotationDegreesProperty")
        set(value) = setRotationDegrees(value)

    var scale: Vector2
        @JvmName("scaleProperty")
        get() = getScale()
        @JvmName("setScaleProperty")
        set(value) = setScale(value)

    var skew: Double
        @JvmName("skewProperty")
        get() = getSkew()
        @JvmName("setSkewProperty")
        set(value) = setSkew(value)

    var globalPosition: Vector2
        @JvmName("globalPositionProperty")
        get() = getGlobalPosition()
        @JvmName("setGlobalPositionProperty")
        set(value) = setGlobalPosition(value)

    var globalRotation: Double
        @JvmName("globalRotationProperty")
        get() = getGlobalRotation()
        @JvmName("setGlobalRotationProperty")
        set(value) = setGlobalRotation(value)

    var globalRotationDegrees: Double
        @JvmName("globalRotationDegreesProperty")
        get() = getGlobalRotationDegrees()
        @JvmName("setGlobalRotationDegreesProperty")
        set(value) = setGlobalRotationDegrees(value)

    var globalScale: Vector2
        @JvmName("globalScaleProperty")
        get() = getGlobalScale()
        @JvmName("setGlobalScaleProperty")
        set(value) = setGlobalScale(value)

    var globalSkew: Double
        @JvmName("globalSkewProperty")
        get() = getGlobalSkew()
        @JvmName("setGlobalSkewProperty")
        set(value) = setGlobalSkew(value)

    fun setPosition(position: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setPositionBind, handle, position)
    }

    fun setRotation(radians: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRotationBind, handle, radians)
    }

    fun setRotationDegrees(degrees: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRotationDegreesBind, handle, degrees)
    }

    fun setSkew(radians: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSkewBind, handle, radians)
    }

    fun setScale(scale: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setScaleBind, handle, scale)
    }

    fun getPosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getPositionBind, handle)
    }

    fun getRotation(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRotationBind, handle)
    }

    fun getRotationDegrees(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRotationDegreesBind, handle)
    }

    fun getSkew(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSkewBind, handle)
    }

    fun getScale(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getScaleBind, handle)
    }

    fun rotate(radians: Double) {
        ObjectCalls.ptrcallWithDoubleArg(rotateBind, handle, radians)
    }

    fun moveLocalX(delta: Double, scaled: Boolean = false) {
        ObjectCalls.ptrcallWithDoubleAndBoolArgs(moveLocalXBind, handle, delta, scaled)
    }

    fun moveLocalY(delta: Double, scaled: Boolean = false) {
        ObjectCalls.ptrcallWithDoubleAndBoolArgs(moveLocalYBind, handle, delta, scaled)
    }

    fun translate(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(translateBind, handle, offset)
    }

    fun globalTranslate(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(globalTranslateBind, handle, offset)
    }

    fun applyScale(ratio: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(applyScaleBind, handle, ratio)
    }

    fun setGlobalPosition(position: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setGlobalPositionBind, handle, position)
    }

    fun getGlobalPosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getGlobalPositionBind, handle)
    }

    fun setGlobalRotation(radians: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGlobalRotationBind, handle, radians)
    }

    fun setGlobalRotationDegrees(degrees: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGlobalRotationDegreesBind, handle, degrees)
    }

    fun getGlobalRotation(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGlobalRotationBind, handle)
    }

    fun getGlobalRotationDegrees(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGlobalRotationDegreesBind, handle)
    }

    fun setGlobalSkew(radians: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGlobalSkewBind, handle, radians)
    }

    fun getGlobalSkew(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGlobalSkewBind, handle)
    }

    fun setGlobalScale(scale: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setGlobalScaleBind, handle, scale)
    }

    fun getGlobalScale(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getGlobalScaleBind, handle)
    }

    fun lookAt(point: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(lookAtBind, handle, point)
    }

    fun getAngleTo(point: Vector2): Double {
        return ObjectCalls.ptrcallWithVector2ArgRetDouble(getAngleToBind, handle, point)
    }

    fun toLocal(globalPoint: Vector2): Vector2 {
        return ObjectCalls.ptrcallWithVector2ArgRetVector2(toLocalBind, handle, globalPoint)
    }

    fun toGlobal(localPoint: Vector2): Vector2 {
        return ObjectCalls.ptrcallWithVector2ArgRetVector2(toGlobalBind, handle, localPoint)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): Node2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Node2D? =
            if (handle.address() == 0L) null else Node2D(handle)

        private const val SET_POSITION_HASH = 743155724L
        private val setPositionBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "set_position", SET_POSITION_HASH)
        }

        private const val SET_ROTATION_HASH = 373806689L
        private val setRotationBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "set_rotation", SET_ROTATION_HASH)
        }

        private const val SET_ROTATION_DEGREES_HASH = 373806689L
        private val setRotationDegreesBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "set_rotation_degrees", SET_ROTATION_DEGREES_HASH)
        }

        private const val SET_SKEW_HASH = 373806689L
        private val setSkewBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "set_skew", SET_SKEW_HASH)
        }

        private const val SET_SCALE_HASH = 743155724L
        private val setScaleBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "set_scale", SET_SCALE_HASH)
        }

        private const val GET_POSITION_HASH = 3341600327L
        private val getPositionBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "get_position", GET_POSITION_HASH)
        }

        private const val GET_ROTATION_HASH = 1740695150L
        private val getRotationBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "get_rotation", GET_ROTATION_HASH)
        }

        private const val GET_ROTATION_DEGREES_HASH = 1740695150L
        private val getRotationDegreesBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "get_rotation_degrees", GET_ROTATION_DEGREES_HASH)
        }

        private const val GET_SKEW_HASH = 1740695150L
        private val getSkewBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "get_skew", GET_SKEW_HASH)
        }

        private const val GET_SCALE_HASH = 3341600327L
        private val getScaleBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "get_scale", GET_SCALE_HASH)
        }

        private const val ROTATE_HASH = 373806689L
        private val rotateBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "rotate", ROTATE_HASH)
        }

        private const val MOVE_LOCAL_X_HASH = 2087892650L
        private val moveLocalXBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "move_local_x", MOVE_LOCAL_X_HASH)
        }

        private const val MOVE_LOCAL_Y_HASH = 2087892650L
        private val moveLocalYBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "move_local_y", MOVE_LOCAL_Y_HASH)
        }

        private const val TRANSLATE_HASH = 743155724L
        private val translateBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "translate", TRANSLATE_HASH)
        }

        private const val GLOBAL_TRANSLATE_HASH = 743155724L
        private val globalTranslateBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "global_translate", GLOBAL_TRANSLATE_HASH)
        }

        private const val APPLY_SCALE_HASH = 743155724L
        private val applyScaleBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "apply_scale", APPLY_SCALE_HASH)
        }

        private const val SET_GLOBAL_POSITION_HASH = 743155724L
        private val setGlobalPositionBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "set_global_position", SET_GLOBAL_POSITION_HASH)
        }

        private const val GET_GLOBAL_POSITION_HASH = 3341600327L
        private val getGlobalPositionBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "get_global_position", GET_GLOBAL_POSITION_HASH)
        }

        private const val SET_GLOBAL_ROTATION_HASH = 373806689L
        private val setGlobalRotationBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "set_global_rotation", SET_GLOBAL_ROTATION_HASH)
        }

        private const val SET_GLOBAL_ROTATION_DEGREES_HASH = 373806689L
        private val setGlobalRotationDegreesBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "set_global_rotation_degrees", SET_GLOBAL_ROTATION_DEGREES_HASH)
        }

        private const val GET_GLOBAL_ROTATION_HASH = 1740695150L
        private val getGlobalRotationBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "get_global_rotation", GET_GLOBAL_ROTATION_HASH)
        }

        private const val GET_GLOBAL_ROTATION_DEGREES_HASH = 1740695150L
        private val getGlobalRotationDegreesBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "get_global_rotation_degrees", GET_GLOBAL_ROTATION_DEGREES_HASH)
        }

        private const val SET_GLOBAL_SKEW_HASH = 373806689L
        private val setGlobalSkewBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "set_global_skew", SET_GLOBAL_SKEW_HASH)
        }

        private const val GET_GLOBAL_SKEW_HASH = 1740695150L
        private val getGlobalSkewBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "get_global_skew", GET_GLOBAL_SKEW_HASH)
        }

        private const val SET_GLOBAL_SCALE_HASH = 743155724L
        private val setGlobalScaleBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "set_global_scale", SET_GLOBAL_SCALE_HASH)
        }

        private const val GET_GLOBAL_SCALE_HASH = 3341600327L
        private val getGlobalScaleBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "get_global_scale", GET_GLOBAL_SCALE_HASH)
        }

        private const val LOOK_AT_HASH = 743155724L
        private val lookAtBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "look_at", LOOK_AT_HASH)
        }

        private const val GET_ANGLE_TO_HASH = 2276447920L
        private val getAngleToBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "get_angle_to", GET_ANGLE_TO_HASH)
        }

        private const val TO_LOCAL_HASH = 2656412154L
        private val toLocalBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "to_local", TO_LOCAL_HASH)
        }

        private const val TO_GLOBAL_HASH = 2656412154L
        private val toGlobalBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "to_global", TO_GLOBAL_HASH)
        }
    }
}

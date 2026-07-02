package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: Curve2D
 */
class Curve2D(handle: MemorySegment) : Resource(handle) {
    var bakeInterval: Double
        @JvmName("bakeIntervalProperty")
        get() = getBakeInterval()
        @JvmName("setBakeIntervalProperty")
        set(value) = setBakeInterval(value)

    var pointCount: Int
        @JvmName("pointCountProperty")
        get() = getPointCount()
        @JvmName("setPointCountProperty")
        set(value) = setPointCount(value)

    fun getPointCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPointCountBind, handle)
    }

    fun setPointCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setPointCountBind, handle, count)
    }

    fun addPoint(position: Vector2, inValue: Vector2 = Vector2(0f, 0f), out: Vector2 = Vector2(0f, 0f), index: Int = -1) {
        ObjectCalls.ptrcallWithThreeVector2AndIntArg(addPointBind, handle, position, inValue, out, index)
    }

    fun setPointPosition(idx: Int, position: Vector2) {
        ObjectCalls.ptrcallWithIntAndVector2Arg(setPointPositionBind, handle, idx, position)
    }

    fun getPointPosition(idx: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(getPointPositionBind, handle, idx)
    }

    fun setPointIn(idx: Int, position: Vector2) {
        ObjectCalls.ptrcallWithIntAndVector2Arg(setPointInBind, handle, idx, position)
    }

    fun getPointIn(idx: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(getPointInBind, handle, idx)
    }

    fun setPointOut(idx: Int, position: Vector2) {
        ObjectCalls.ptrcallWithIntAndVector2Arg(setPointOutBind, handle, idx, position)
    }

    fun getPointOut(idx: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(getPointOutBind, handle, idx)
    }

    fun removePoint(idx: Int) {
        ObjectCalls.ptrcallWithIntArg(removePointBind, handle, idx)
    }

    fun clearPoints() {
        ObjectCalls.ptrcallNoArgs(clearPointsBind, handle)
    }

    fun sample(idx: Int, t: Double): Vector2 {
        return ObjectCalls.ptrcallWithIntAndDoubleArgRetVector2(sampleBind, handle, idx, t)
    }

    fun samplef(fofs: Double): Vector2 {
        return ObjectCalls.ptrcallWithDoubleArgRetVector2(samplefBind, handle, fofs)
    }

    fun setBakeInterval(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBakeIntervalBind, handle, distance)
    }

    fun getBakeInterval(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBakeIntervalBind, handle)
    }

    fun getBakedLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBakedLengthBind, handle)
    }

    fun sampleBaked(offset: Double = 0.0, cubic: Boolean = false): Vector2 {
        return ObjectCalls.ptrcallWithDoubleAndBoolArgRetVector2(sampleBakedBind, handle, offset, cubic)
    }

    fun sampleBakedWithRotation(offset: Double = 0.0, cubic: Boolean = false): Transform2D {
        return ObjectCalls.ptrcallWithDoubleAndBoolArgRetTransform2D(sampleBakedWithRotationBind, handle, offset, cubic)
    }

    fun getBakedPoints(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getBakedPointsBind, handle)
    }

    fun getClosestPoint(toPoint: Vector2): Vector2 {
        return ObjectCalls.ptrcallWithVector2ArgRetVector2(getClosestPointBind, handle, toPoint)
    }

    fun getClosestOffset(toPoint: Vector2): Double {
        return ObjectCalls.ptrcallWithVector2ArgRetDouble(getClosestOffsetBind, handle, toPoint)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): Curve2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Curve2D? =
            if (handle.address() == 0L) null else Curve2D(handle)

        private const val GET_POINT_COUNT_HASH = 3905245786L
        private val getPointCountBind by lazy {
            ObjectCalls.getMethodBind("Curve2D", "get_point_count", GET_POINT_COUNT_HASH)
        }

        private const val SET_POINT_COUNT_HASH = 1286410249L
        private val setPointCountBind by lazy {
            ObjectCalls.getMethodBind("Curve2D", "set_point_count", SET_POINT_COUNT_HASH)
        }

        private const val ADD_POINT_HASH = 4175465202L
        private val addPointBind by lazy {
            ObjectCalls.getMethodBind("Curve2D", "add_point", ADD_POINT_HASH)
        }

        private const val SET_POINT_POSITION_HASH = 163021252L
        private val setPointPositionBind by lazy {
            ObjectCalls.getMethodBind("Curve2D", "set_point_position", SET_POINT_POSITION_HASH)
        }

        private const val GET_POINT_POSITION_HASH = 2299179447L
        private val getPointPositionBind by lazy {
            ObjectCalls.getMethodBind("Curve2D", "get_point_position", GET_POINT_POSITION_HASH)
        }

        private const val SET_POINT_IN_HASH = 163021252L
        private val setPointInBind by lazy {
            ObjectCalls.getMethodBind("Curve2D", "set_point_in", SET_POINT_IN_HASH)
        }

        private const val GET_POINT_IN_HASH = 2299179447L
        private val getPointInBind by lazy {
            ObjectCalls.getMethodBind("Curve2D", "get_point_in", GET_POINT_IN_HASH)
        }

        private const val SET_POINT_OUT_HASH = 163021252L
        private val setPointOutBind by lazy {
            ObjectCalls.getMethodBind("Curve2D", "set_point_out", SET_POINT_OUT_HASH)
        }

        private const val GET_POINT_OUT_HASH = 2299179447L
        private val getPointOutBind by lazy {
            ObjectCalls.getMethodBind("Curve2D", "get_point_out", GET_POINT_OUT_HASH)
        }

        private const val REMOVE_POINT_HASH = 1286410249L
        private val removePointBind by lazy {
            ObjectCalls.getMethodBind("Curve2D", "remove_point", REMOVE_POINT_HASH)
        }

        private const val CLEAR_POINTS_HASH = 3218959716L
        private val clearPointsBind by lazy {
            ObjectCalls.getMethodBind("Curve2D", "clear_points", CLEAR_POINTS_HASH)
        }

        private const val SAMPLE_HASH = 26514310L
        private val sampleBind by lazy {
            ObjectCalls.getMethodBind("Curve2D", "sample", SAMPLE_HASH)
        }

        private const val SAMPLEF_HASH = 3588506812L
        private val samplefBind by lazy {
            ObjectCalls.getMethodBind("Curve2D", "samplef", SAMPLEF_HASH)
        }

        private const val SET_BAKE_INTERVAL_HASH = 373806689L
        private val setBakeIntervalBind by lazy {
            ObjectCalls.getMethodBind("Curve2D", "set_bake_interval", SET_BAKE_INTERVAL_HASH)
        }

        private const val GET_BAKE_INTERVAL_HASH = 1740695150L
        private val getBakeIntervalBind by lazy {
            ObjectCalls.getMethodBind("Curve2D", "get_bake_interval", GET_BAKE_INTERVAL_HASH)
        }

        private const val GET_BAKED_LENGTH_HASH = 1740695150L
        private val getBakedLengthBind by lazy {
            ObjectCalls.getMethodBind("Curve2D", "get_baked_length", GET_BAKED_LENGTH_HASH)
        }

        private const val SAMPLE_BAKED_HASH = 3464257706L
        private val sampleBakedBind by lazy {
            ObjectCalls.getMethodBind("Curve2D", "sample_baked", SAMPLE_BAKED_HASH)
        }

        private const val SAMPLE_BAKED_WITH_ROTATION_HASH = 3296056341L
        private val sampleBakedWithRotationBind by lazy {
            ObjectCalls.getMethodBind("Curve2D", "sample_baked_with_rotation", SAMPLE_BAKED_WITH_ROTATION_HASH)
        }

        private const val GET_BAKED_POINTS_HASH = 2961356807L
        private val getBakedPointsBind by lazy {
            ObjectCalls.getMethodBind("Curve2D", "get_baked_points", GET_BAKED_POINTS_HASH)
        }

        private const val GET_CLOSEST_POINT_HASH = 2656412154L
        private val getClosestPointBind by lazy {
            ObjectCalls.getMethodBind("Curve2D", "get_closest_point", GET_CLOSEST_POINT_HASH)
        }

        private const val GET_CLOSEST_OFFSET_HASH = 2276447920L
        private val getClosestOffsetBind by lazy {
            ObjectCalls.getMethodBind("Curve2D", "get_closest_offset", GET_CLOSEST_OFFSET_HASH)
        }
    }
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * A mathematical curve.
 *
 * Generated from Godot docs: Curve
 */
class Curve(handle: MemorySegment) : Resource(handle) {
    var minDomain: Double
        @JvmName("minDomainProperty")
        get() = getMinDomain()
        @JvmName("setMinDomainProperty")
        set(value) = setMinDomain(value)

    var maxDomain: Double
        @JvmName("maxDomainProperty")
        get() = getMaxDomain()
        @JvmName("setMaxDomainProperty")
        set(value) = setMaxDomain(value)

    var minValue: Double
        @JvmName("minValueProperty")
        get() = getMinValue()
        @JvmName("setMinValueProperty")
        set(value) = setMinValue(value)

    var maxValue: Double
        @JvmName("maxValueProperty")
        get() = getMaxValue()
        @JvmName("setMaxValueProperty")
        set(value) = setMaxValue(value)

    var bakeResolution: Int
        @JvmName("bakeResolutionProperty")
        get() = getBakeResolution()
        @JvmName("setBakeResolutionProperty")
        set(value) = setBakeResolution(value)

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

    fun addPoint(position: Vector2, leftTangent: Double = 0.0, rightTangent: Double = 0.0, leftMode: Long = 0L, rightMode: Long = 0L): Int {
        return ObjectCalls.ptrcallWithVector2TwoDoubleTwoLongArgsRetInt(addPointBind, handle, position, leftTangent, rightTangent, leftMode, rightMode)
    }

    fun removePoint(index: Int) {
        ObjectCalls.ptrcallWithIntArg(removePointBind, handle, index)
    }

    fun clearPoints() {
        ObjectCalls.ptrcallNoArgs(clearPointsBind, handle)
    }

    fun getPointPosition(index: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(getPointPositionBind, handle, index)
    }

    fun setPointValue(index: Int, y: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setPointValueBind, handle, index, y)
    }

    fun setPointOffset(index: Int, offset: Double): Int {
        return ObjectCalls.ptrcallWithIntAndDoubleArgRetInt(setPointOffsetBind, handle, index, offset)
    }

    /**
     * Returns the Y value for the point that would exist at the X position `offset` along the curve.
     *
     * Generated from Godot docs: Curve.sample
     */
    fun sample(offset: Double): Double {
        return ObjectCalls.ptrcallWithFloatArgRetFloat(sampleBind, handle, offset)
    }

    fun sampleBaked(offset: Double): Double {
        return ObjectCalls.ptrcallWithFloatArgRetFloat(sampleBakedBind, handle, offset)
    }

    fun getPointLeftTangent(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getPointLeftTangentBind, handle, index)
    }

    fun getPointRightTangent(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getPointRightTangentBind, handle, index)
    }

    fun getPointLeftMode(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getPointLeftModeBind, handle, index)
    }

    fun getPointRightMode(index: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getPointRightModeBind, handle, index)
    }

    fun setPointLeftTangent(index: Int, tangent: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setPointLeftTangentBind, handle, index, tangent)
    }

    fun setPointRightTangent(index: Int, tangent: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setPointRightTangentBind, handle, index, tangent)
    }

    fun setPointLeftMode(index: Int, mode: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setPointLeftModeBind, handle, index, mode)
    }

    fun setPointRightMode(index: Int, mode: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setPointRightModeBind, handle, index, mode)
    }

    fun getMinValue(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMinValueBind, handle)
    }

    fun setMinValue(min: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMinValueBind, handle, min)
    }

    fun getMaxValue(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMaxValueBind, handle)
    }

    fun setMaxValue(max: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMaxValueBind, handle, max)
    }

    fun getValueRange(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getValueRangeBind, handle)
    }

    fun getMinDomain(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMinDomainBind, handle)
    }

    fun setMinDomain(min: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMinDomainBind, handle, min)
    }

    fun getMaxDomain(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMaxDomainBind, handle)
    }

    fun setMaxDomain(max: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMaxDomainBind, handle, max)
    }

    fun getDomainRange(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDomainRangeBind, handle)
    }

    fun cleanDupes() {
        ObjectCalls.ptrcallNoArgs(cleanDupesBind, handle)
    }

    /**
     * Recomputes the baked cache of points for the curve.
     *
     * Generated from Godot docs: Curve.bake
     */
    fun bake() {
        ObjectCalls.ptrcallNoArgs(bakeBind, handle)
    }

    fun getBakeResolution(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBakeResolutionBind, handle)
    }

    fun setBakeResolution(resolution: Int) {
        ObjectCalls.ptrcallWithIntArg(setBakeResolutionBind, handle, resolution)
    }

    object Signals {
        const val rangeChanged: String = "range_changed"
        const val domainChanged: String = "domain_changed"
    }

    companion object {
        const val TANGENT_FREE: Long = 0L
        const val TANGENT_LINEAR: Long = 1L
        const val TANGENT_MODE_COUNT: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Curve? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Curve? =
            if (handle.address() == 0L) null else Curve(handle)

        private const val GET_POINT_COUNT_HASH = 3905245786L
        private val getPointCountBind by lazy {
            ObjectCalls.getMethodBind("Curve", "get_point_count", GET_POINT_COUNT_HASH)
        }

        private const val SET_POINT_COUNT_HASH = 1286410249L
        private val setPointCountBind by lazy {
            ObjectCalls.getMethodBind("Curve", "set_point_count", SET_POINT_COUNT_HASH)
        }

        private const val ADD_POINT_HASH = 434072736L
        private val addPointBind by lazy {
            ObjectCalls.getMethodBind("Curve", "add_point", ADD_POINT_HASH)
        }

        private const val REMOVE_POINT_HASH = 1286410249L
        private val removePointBind by lazy {
            ObjectCalls.getMethodBind("Curve", "remove_point", REMOVE_POINT_HASH)
        }

        private const val CLEAR_POINTS_HASH = 3218959716L
        private val clearPointsBind by lazy {
            ObjectCalls.getMethodBind("Curve", "clear_points", CLEAR_POINTS_HASH)
        }

        private const val GET_POINT_POSITION_HASH = 2299179447L
        private val getPointPositionBind by lazy {
            ObjectCalls.getMethodBind("Curve", "get_point_position", GET_POINT_POSITION_HASH)
        }

        private const val SET_POINT_VALUE_HASH = 1602489585L
        private val setPointValueBind by lazy {
            ObjectCalls.getMethodBind("Curve", "set_point_value", SET_POINT_VALUE_HASH)
        }

        private const val SET_POINT_OFFSET_HASH = 3780573764L
        private val setPointOffsetBind by lazy {
            ObjectCalls.getMethodBind("Curve", "set_point_offset", SET_POINT_OFFSET_HASH)
        }

        private const val SAMPLE_HASH = 3919130443L
        private val sampleBind by lazy {
            ObjectCalls.getMethodBind("Curve", "sample", SAMPLE_HASH)
        }

        private const val SAMPLE_BAKED_HASH = 3919130443L
        private val sampleBakedBind by lazy {
            ObjectCalls.getMethodBind("Curve", "sample_baked", SAMPLE_BAKED_HASH)
        }

        private const val GET_POINT_LEFT_TANGENT_HASH = 2339986948L
        private val getPointLeftTangentBind by lazy {
            ObjectCalls.getMethodBind("Curve", "get_point_left_tangent", GET_POINT_LEFT_TANGENT_HASH)
        }

        private const val GET_POINT_RIGHT_TANGENT_HASH = 2339986948L
        private val getPointRightTangentBind by lazy {
            ObjectCalls.getMethodBind("Curve", "get_point_right_tangent", GET_POINT_RIGHT_TANGENT_HASH)
        }

        private const val GET_POINT_LEFT_MODE_HASH = 426950354L
        private val getPointLeftModeBind by lazy {
            ObjectCalls.getMethodBind("Curve", "get_point_left_mode", GET_POINT_LEFT_MODE_HASH)
        }

        private const val GET_POINT_RIGHT_MODE_HASH = 426950354L
        private val getPointRightModeBind by lazy {
            ObjectCalls.getMethodBind("Curve", "get_point_right_mode", GET_POINT_RIGHT_MODE_HASH)
        }

        private const val SET_POINT_LEFT_TANGENT_HASH = 1602489585L
        private val setPointLeftTangentBind by lazy {
            ObjectCalls.getMethodBind("Curve", "set_point_left_tangent", SET_POINT_LEFT_TANGENT_HASH)
        }

        private const val SET_POINT_RIGHT_TANGENT_HASH = 1602489585L
        private val setPointRightTangentBind by lazy {
            ObjectCalls.getMethodBind("Curve", "set_point_right_tangent", SET_POINT_RIGHT_TANGENT_HASH)
        }

        private const val SET_POINT_LEFT_MODE_HASH = 1217242874L
        private val setPointLeftModeBind by lazy {
            ObjectCalls.getMethodBind("Curve", "set_point_left_mode", SET_POINT_LEFT_MODE_HASH)
        }

        private const val SET_POINT_RIGHT_MODE_HASH = 1217242874L
        private val setPointRightModeBind by lazy {
            ObjectCalls.getMethodBind("Curve", "set_point_right_mode", SET_POINT_RIGHT_MODE_HASH)
        }

        private const val GET_MIN_VALUE_HASH = 1740695150L
        private val getMinValueBind by lazy {
            ObjectCalls.getMethodBind("Curve", "get_min_value", GET_MIN_VALUE_HASH)
        }

        private const val SET_MIN_VALUE_HASH = 373806689L
        private val setMinValueBind by lazy {
            ObjectCalls.getMethodBind("Curve", "set_min_value", SET_MIN_VALUE_HASH)
        }

        private const val GET_MAX_VALUE_HASH = 1740695150L
        private val getMaxValueBind by lazy {
            ObjectCalls.getMethodBind("Curve", "get_max_value", GET_MAX_VALUE_HASH)
        }

        private const val SET_MAX_VALUE_HASH = 373806689L
        private val setMaxValueBind by lazy {
            ObjectCalls.getMethodBind("Curve", "set_max_value", SET_MAX_VALUE_HASH)
        }

        private const val GET_VALUE_RANGE_HASH = 1740695150L
        private val getValueRangeBind by lazy {
            ObjectCalls.getMethodBind("Curve", "get_value_range", GET_VALUE_RANGE_HASH)
        }

        private const val GET_MIN_DOMAIN_HASH = 1740695150L
        private val getMinDomainBind by lazy {
            ObjectCalls.getMethodBind("Curve", "get_min_domain", GET_MIN_DOMAIN_HASH)
        }

        private const val SET_MIN_DOMAIN_HASH = 373806689L
        private val setMinDomainBind by lazy {
            ObjectCalls.getMethodBind("Curve", "set_min_domain", SET_MIN_DOMAIN_HASH)
        }

        private const val GET_MAX_DOMAIN_HASH = 1740695150L
        private val getMaxDomainBind by lazy {
            ObjectCalls.getMethodBind("Curve", "get_max_domain", GET_MAX_DOMAIN_HASH)
        }

        private const val SET_MAX_DOMAIN_HASH = 373806689L
        private val setMaxDomainBind by lazy {
            ObjectCalls.getMethodBind("Curve", "set_max_domain", SET_MAX_DOMAIN_HASH)
        }

        private const val GET_DOMAIN_RANGE_HASH = 1740695150L
        private val getDomainRangeBind by lazy {
            ObjectCalls.getMethodBind("Curve", "get_domain_range", GET_DOMAIN_RANGE_HASH)
        }

        private const val CLEAN_DUPES_HASH = 3218959716L
        private val cleanDupesBind by lazy {
            ObjectCalls.getMethodBind("Curve", "clean_dupes", CLEAN_DUPES_HASH)
        }

        private const val BAKE_HASH = 3218959716L
        private val bakeBind by lazy {
            ObjectCalls.getMethodBind("Curve", "bake", BAKE_HASH)
        }

        private const val GET_BAKE_RESOLUTION_HASH = 3905245786L
        private val getBakeResolutionBind by lazy {
            ObjectCalls.getMethodBind("Curve", "get_bake_resolution", GET_BAKE_RESOLUTION_HASH)
        }

        private const val SET_BAKE_RESOLUTION_HASH = 1286410249L
        private val setBakeResolutionBind by lazy {
            ObjectCalls.getMethodBind("Curve", "set_bake_resolution", SET_BAKE_RESOLUTION_HASH)
        }
    }
}

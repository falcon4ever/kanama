package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * Generated from Godot docs: GLTFLight
 */
class GLTFLight(handle: MemorySegment) : Resource(handle) {
    var color: Color
        @JvmName("colorProperty")
        get() = getColor()
        @JvmName("setColorProperty")
        set(value) = setColor(value)

    var intensity: Double
        @JvmName("intensityProperty")
        get() = getIntensity()
        @JvmName("setIntensityProperty")
        set(value) = setIntensity(value)

    var lightType: String
        @JvmName("lightTypeProperty")
        get() = getLightType()
        @JvmName("setLightTypeProperty")
        set(value) = setLightType(value)

    var range: Double
        @JvmName("rangeProperty")
        get() = getRange()
        @JvmName("setRangeProperty")
        set(value) = setRange(value)

    var innerConeAngle: Double
        @JvmName("innerConeAngleProperty")
        get() = getInnerConeAngle()
        @JvmName("setInnerConeAngleProperty")
        set(value) = setInnerConeAngle(value)

    var outerConeAngle: Double
        @JvmName("outerConeAngleProperty")
        get() = getOuterConeAngle()
        @JvmName("setOuterConeAngleProperty")
        set(value) = setOuterConeAngle(value)

    fun toNode(): Light3D? {
        return Light3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(toNodeBind, handle))
    }

    fun toDictionary(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(toDictionaryBind, handle)
    }

    fun getColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getColorBind, handle)
    }

    fun setColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setColorBind, handle, color)
    }

    fun getIntensity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getIntensityBind, handle)
    }

    fun setIntensity(intensity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setIntensityBind, handle, intensity)
    }

    fun getLightType(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLightTypeBind, handle)
    }

    fun setLightType(lightType: String) {
        ObjectCalls.ptrcallWithStringArg(setLightTypeBind, handle, lightType)
    }

    fun getRange(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRangeBind, handle)
    }

    fun setRange(range: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRangeBind, handle, range)
    }

    fun getInnerConeAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getInnerConeAngleBind, handle)
    }

    fun setInnerConeAngle(innerConeAngle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setInnerConeAngleBind, handle, innerConeAngle)
    }

    fun getOuterConeAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getOuterConeAngleBind, handle)
    }

    fun setOuterConeAngle(outerConeAngle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setOuterConeAngleBind, handle, outerConeAngle)
    }

    fun getAdditionalData(extensionName: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getAdditionalDataBind, handle, extensionName)
    }

    fun setAdditionalData(extensionName: String, additionalData: Any?) {
        ObjectCalls.ptrcallWithStringNameAndVariantArg(setAdditionalDataBind, handle, extensionName, additionalData)
    }

    companion object {
        fun fromNode(lightNode: Light3D): GLTFLight? {
            return GLTFLight.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(fromNodeBind, MemorySegment.NULL, lightNode.handle))
        }

        fun fromDictionary(dictionary: Map<String, Any?>): GLTFLight? {
            return GLTFLight.wrap(ObjectCalls.ptrcallWithDictionaryArgRetObject(fromDictionaryBind, MemorySegment.NULL, dictionary))
        }

        @JvmStatic
        fun fromHandle(handle: MemorySegment): GLTFLight? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GLTFLight? =
            if (handle.address() == 0L) null else GLTFLight(handle)

        private const val FROM_NODE_HASH = 3907677874L
        private val fromNodeBind by lazy {
            ObjectCalls.getMethodBind("GLTFLight", "from_node", FROM_NODE_HASH)
        }

        private const val TO_NODE_HASH = 2040811672L
        private val toNodeBind by lazy {
            ObjectCalls.getMethodBind("GLTFLight", "to_node", TO_NODE_HASH)
        }

        private const val FROM_DICTIONARY_HASH = 4057087208L
        private val fromDictionaryBind by lazy {
            ObjectCalls.getMethodBind("GLTFLight", "from_dictionary", FROM_DICTIONARY_HASH)
        }

        private const val TO_DICTIONARY_HASH = 3102165223L
        private val toDictionaryBind by lazy {
            ObjectCalls.getMethodBind("GLTFLight", "to_dictionary", TO_DICTIONARY_HASH)
        }

        private const val GET_COLOR_HASH = 3200896285L
        private val getColorBind by lazy {
            ObjectCalls.getMethodBind("GLTFLight", "get_color", GET_COLOR_HASH)
        }

        private const val SET_COLOR_HASH = 2920490490L
        private val setColorBind by lazy {
            ObjectCalls.getMethodBind("GLTFLight", "set_color", SET_COLOR_HASH)
        }

        private const val GET_INTENSITY_HASH = 191475506L
        private val getIntensityBind by lazy {
            ObjectCalls.getMethodBind("GLTFLight", "get_intensity", GET_INTENSITY_HASH)
        }

        private const val SET_INTENSITY_HASH = 373806689L
        private val setIntensityBind by lazy {
            ObjectCalls.getMethodBind("GLTFLight", "set_intensity", SET_INTENSITY_HASH)
        }

        private const val GET_LIGHT_TYPE_HASH = 2841200299L
        private val getLightTypeBind by lazy {
            ObjectCalls.getMethodBind("GLTFLight", "get_light_type", GET_LIGHT_TYPE_HASH)
        }

        private const val SET_LIGHT_TYPE_HASH = 83702148L
        private val setLightTypeBind by lazy {
            ObjectCalls.getMethodBind("GLTFLight", "set_light_type", SET_LIGHT_TYPE_HASH)
        }

        private const val GET_RANGE_HASH = 191475506L
        private val getRangeBind by lazy {
            ObjectCalls.getMethodBind("GLTFLight", "get_range", GET_RANGE_HASH)
        }

        private const val SET_RANGE_HASH = 373806689L
        private val setRangeBind by lazy {
            ObjectCalls.getMethodBind("GLTFLight", "set_range", SET_RANGE_HASH)
        }

        private const val GET_INNER_CONE_ANGLE_HASH = 191475506L
        private val getInnerConeAngleBind by lazy {
            ObjectCalls.getMethodBind("GLTFLight", "get_inner_cone_angle", GET_INNER_CONE_ANGLE_HASH)
        }

        private const val SET_INNER_CONE_ANGLE_HASH = 373806689L
        private val setInnerConeAngleBind by lazy {
            ObjectCalls.getMethodBind("GLTFLight", "set_inner_cone_angle", SET_INNER_CONE_ANGLE_HASH)
        }

        private const val GET_OUTER_CONE_ANGLE_HASH = 191475506L
        private val getOuterConeAngleBind by lazy {
            ObjectCalls.getMethodBind("GLTFLight", "get_outer_cone_angle", GET_OUTER_CONE_ANGLE_HASH)
        }

        private const val SET_OUTER_CONE_ANGLE_HASH = 373806689L
        private val setOuterConeAngleBind by lazy {
            ObjectCalls.getMethodBind("GLTFLight", "set_outer_cone_angle", SET_OUTER_CONE_ANGLE_HASH)
        }

        private const val GET_ADDITIONAL_DATA_HASH = 2138907829L
        private val getAdditionalDataBind by lazy {
            ObjectCalls.getMethodBind("GLTFLight", "get_additional_data", GET_ADDITIONAL_DATA_HASH)
        }

        private const val SET_ADDITIONAL_DATA_HASH = 3776071444L
        private val setAdditionalDataBind by lazy {
            ObjectCalls.getMethodBind("GLTFLight", "set_additional_data", SET_ADDITIONAL_DATA_HASH)
        }
    }
}

package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color

/**
 * Generated from Godot docs: GLTFLight
 */
class GLTFLight(handle: GodotHandle) : Resource(handle) {
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
        checkOpen()
        return Light3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.toNodeBind, segment))
    }

    fun toDictionary(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(Binds.toDictionaryBind, segment)
    }

    fun getColor(): Color {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getColorBind, segment)
    }

    fun setColor(color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(Binds.setColorBind, segment, color)
    }

    fun getIntensity(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getIntensityBind, segment)
    }

    fun setIntensity(intensity: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setIntensityBind, segment, intensity)
    }

    fun getLightType(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getLightTypeBind, segment)
    }

    fun setLightType(lightType: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setLightTypeBind, segment, lightType)
    }

    fun getRange(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRangeBind, segment)
    }

    fun setRange(range: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRangeBind, segment, range)
    }

    fun getInnerConeAngle(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getInnerConeAngleBind, segment)
    }

    fun setInnerConeAngle(innerConeAngle: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setInnerConeAngleBind, segment, innerConeAngle)
    }

    fun getOuterConeAngle(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getOuterConeAngleBind, segment)
    }

    fun setOuterConeAngle(outerConeAngle: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setOuterConeAngleBind, segment, outerConeAngle)
    }

    fun getAdditionalData(extensionName: String): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(Binds.getAdditionalDataBind, segment, extensionName)
    }

    fun setAdditionalData(extensionName: String, additionalData: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameAndVariantArg(Binds.setAdditionalDataBind, segment, extensionName, additionalData)
    }

    companion object {
        fun fromNode(lightNode: Light3D): GLTFLight? {
            return GLTFLight.wrapOwned(ObjectCalls.ptrcallWithObjectArgRetObject(Binds.fromNodeBind, NULL_SEGMENT, lightNode.segment))
        }

        fun fromDictionary(dictionary: Map<String, Any?>): GLTFLight? {
            return GLTFLight.wrapOwned(ObjectCalls.ptrcallWithDictionaryArgRetObject(Binds.fromDictionaryBind, NULL_SEGMENT, dictionary))
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFLight? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GLTFLight? =
            if (handle.address() == 0L) null else RefCounted.owned(GLTFLight(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GLTFLight? =
            if (handle.address() == 0L) null else GLTFLight(GodotHandle(handle))
    }

    private object Binds {
        private const val FROM_NODE_HASH = 3907677874L
        @JvmField
        val fromNodeBind =
            ObjectCalls.getMethodBind("GLTFLight", "from_node", FROM_NODE_HASH)

        private const val TO_NODE_HASH = 2040811672L
        @JvmField
        val toNodeBind =
            ObjectCalls.getMethodBind("GLTFLight", "to_node", TO_NODE_HASH)

        private const val FROM_DICTIONARY_HASH = 4057087208L
        @JvmField
        val fromDictionaryBind =
            ObjectCalls.getMethodBind("GLTFLight", "from_dictionary", FROM_DICTIONARY_HASH)

        private const val TO_DICTIONARY_HASH = 3102165223L
        @JvmField
        val toDictionaryBind =
            ObjectCalls.getMethodBind("GLTFLight", "to_dictionary", TO_DICTIONARY_HASH)

        private const val GET_COLOR_HASH = 3200896285L
        @JvmField
        val getColorBind =
            ObjectCalls.getMethodBind("GLTFLight", "get_color", GET_COLOR_HASH)

        private const val SET_COLOR_HASH = 2920490490L
        @JvmField
        val setColorBind =
            ObjectCalls.getMethodBind("GLTFLight", "set_color", SET_COLOR_HASH)

        private const val GET_INTENSITY_HASH = 191475506L
        @JvmField
        val getIntensityBind =
            ObjectCalls.getMethodBind("GLTFLight", "get_intensity", GET_INTENSITY_HASH)

        private const val SET_INTENSITY_HASH = 373806689L
        @JvmField
        val setIntensityBind =
            ObjectCalls.getMethodBind("GLTFLight", "set_intensity", SET_INTENSITY_HASH)

        private const val GET_LIGHT_TYPE_HASH = 2841200299L
        @JvmField
        val getLightTypeBind =
            ObjectCalls.getMethodBind("GLTFLight", "get_light_type", GET_LIGHT_TYPE_HASH)

        private const val SET_LIGHT_TYPE_HASH = 83702148L
        @JvmField
        val setLightTypeBind =
            ObjectCalls.getMethodBind("GLTFLight", "set_light_type", SET_LIGHT_TYPE_HASH)

        private const val GET_RANGE_HASH = 191475506L
        @JvmField
        val getRangeBind =
            ObjectCalls.getMethodBind("GLTFLight", "get_range", GET_RANGE_HASH)

        private const val SET_RANGE_HASH = 373806689L
        @JvmField
        val setRangeBind =
            ObjectCalls.getMethodBind("GLTFLight", "set_range", SET_RANGE_HASH)

        private const val GET_INNER_CONE_ANGLE_HASH = 191475506L
        @JvmField
        val getInnerConeAngleBind =
            ObjectCalls.getMethodBind("GLTFLight", "get_inner_cone_angle", GET_INNER_CONE_ANGLE_HASH)

        private const val SET_INNER_CONE_ANGLE_HASH = 373806689L
        @JvmField
        val setInnerConeAngleBind =
            ObjectCalls.getMethodBind("GLTFLight", "set_inner_cone_angle", SET_INNER_CONE_ANGLE_HASH)

        private const val GET_OUTER_CONE_ANGLE_HASH = 191475506L
        @JvmField
        val getOuterConeAngleBind =
            ObjectCalls.getMethodBind("GLTFLight", "get_outer_cone_angle", GET_OUTER_CONE_ANGLE_HASH)

        private const val SET_OUTER_CONE_ANGLE_HASH = 373806689L
        @JvmField
        val setOuterConeAngleBind =
            ObjectCalls.getMethodBind("GLTFLight", "set_outer_cone_angle", SET_OUTER_CONE_ANGLE_HASH)

        private const val GET_ADDITIONAL_DATA_HASH = 2138907829L
        @JvmField
        val getAdditionalDataBind =
            ObjectCalls.getMethodBind("GLTFLight", "get_additional_data", GET_ADDITIONAL_DATA_HASH)

        private const val SET_ADDITIONAL_DATA_HASH = 3776071444L
        @JvmField
        val setAdditionalDataBind =
            ObjectCalls.getMethodBind("GLTFLight", "set_additional_data", SET_ADDITIONAL_DATA_HASH)
    }
}

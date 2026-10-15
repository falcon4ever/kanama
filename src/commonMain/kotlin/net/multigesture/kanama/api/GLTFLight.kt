package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color

/**
 * Represents a glTF light.
 *
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

    /**
     * Converts this GLTFLight instance into a Godot `Light3D` node.
     *
     * Generated from Godot docs: GLTFLight.to_node
     */
    fun toNode(): Light3D? {
        checkOpen()
        val ret = Light3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.toNodeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Serializes this GLTFLight instance into a `Dictionary`.
     *
     * Generated from Godot docs: GLTFLight.to_dictionary
     */
    fun toDictionary(): Map<String, Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDictionary(Binds.toDictionaryBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The `Color` of the light in linear space. Defaults to white. A black color causes the light to
     * have no effect. This value is linear to match glTF, but will be converted to nonlinear sRGB when
     * creating a Godot `Light3D` node upon import, or converted to linear when exporting a Godot
     * `Light3D` to glTF.
     *
     * Generated from Godot docs: GLTFLight.get_color
     */
    fun getColor(): Color {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetColor(Binds.getColorBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The `Color` of the light in linear space. Defaults to white. A black color causes the light to
     * have no effect. This value is linear to match glTF, but will be converted to nonlinear sRGB when
     * creating a Godot `Light3D` node upon import, or converted to linear when exporting a Godot
     * `Light3D` to glTF.
     *
     * Generated from Godot docs: GLTFLight.set_color
     */
    fun setColor(color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(Binds.setColorBind, segment, color)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The intensity of the light. This is expressed in candelas (lumens per steradian) for point and
     * spot lights, and lux (lumens per m²) for directional lights. When creating a Godot light, this
     * value is converted to a unitless multiplier.
     *
     * Generated from Godot docs: GLTFLight.get_intensity
     */
    fun getIntensity(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getIntensityBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The intensity of the light. This is expressed in candelas (lumens per steradian) for point and
     * spot lights, and lux (lumens per m²) for directional lights. When creating a Godot light, this
     * value is converted to a unitless multiplier.
     *
     * Generated from Godot docs: GLTFLight.set_intensity
     */
    fun setIntensity(intensity: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setIntensityBind, segment, intensity)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The type of the light. The values accepted by Godot are "point", "spot", and "directional",
     * which correspond to Godot's `OmniLight3D`, `SpotLight3D`, and `DirectionalLight3D` respectively.
     *
     * Generated from Godot docs: GLTFLight.get_light_type
     */
    fun getLightType(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getLightTypeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The type of the light. The values accepted by Godot are "point", "spot", and "directional",
     * which correspond to Godot's `OmniLight3D`, `SpotLight3D`, and `DirectionalLight3D` respectively.
     *
     * Generated from Godot docs: GLTFLight.set_light_type
     */
    fun setLightType(lightType: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setLightTypeBind, segment, lightType)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The range of the light, beyond which the light has no effect. glTF lights with no range defined
     * behave like physical lights (which have infinite range). When creating a Godot light, the range
     * is clamped to `4096.0`.
     *
     * Generated from Godot docs: GLTFLight.get_range
     */
    fun getRange(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRangeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The range of the light, beyond which the light has no effect. glTF lights with no range defined
     * behave like physical lights (which have infinite range). When creating a Godot light, the range
     * is clamped to `4096.0`.
     *
     * Generated from Godot docs: GLTFLight.set_range
     */
    fun setRange(range: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRangeBind, segment, range)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The inner angle of the cone in a spotlight. Must be less than or equal to the outer cone angle.
     * Within this angle, the light is at full brightness. Between the inner and outer cone angles,
     * there is a transition from full brightness to zero brightness. When creating a Godot
     * `SpotLight3D`, the ratio between the inner and outer cone angles is used to calculate the
     * attenuation of the light.
     *
     * Generated from Godot docs: GLTFLight.get_inner_cone_angle
     */
    fun getInnerConeAngle(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getInnerConeAngleBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The inner angle of the cone in a spotlight. Must be less than or equal to the outer cone angle.
     * Within this angle, the light is at full brightness. Between the inner and outer cone angles,
     * there is a transition from full brightness to zero brightness. When creating a Godot
     * `SpotLight3D`, the ratio between the inner and outer cone angles is used to calculate the
     * attenuation of the light.
     *
     * Generated from Godot docs: GLTFLight.set_inner_cone_angle
     */
    fun setInnerConeAngle(innerConeAngle: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setInnerConeAngleBind, segment, innerConeAngle)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The outer angle of the cone in a spotlight. Must be greater than or equal to the inner angle. At
     * this angle, the light drops off to zero brightness. Between the inner and outer cone angles,
     * there is a transition from full brightness to zero brightness. If this angle is a half turn,
     * then the spotlight emits in all directions. When creating a Godot `SpotLight3D`, the outer cone
     * angle is used as the angle of the spotlight.
     *
     * Generated from Godot docs: GLTFLight.get_outer_cone_angle
     */
    fun getOuterConeAngle(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getOuterConeAngleBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The outer angle of the cone in a spotlight. Must be greater than or equal to the inner angle. At
     * this angle, the light drops off to zero brightness. Between the inner and outer cone angles,
     * there is a transition from full brightness to zero brightness. If this angle is a half turn,
     * then the spotlight emits in all directions. When creating a Godot `SpotLight3D`, the outer cone
     * angle is used as the angle of the spotlight.
     *
     * Generated from Godot docs: GLTFLight.set_outer_cone_angle
     */
    fun setOuterConeAngle(outerConeAngle: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setOuterConeAngleBind, segment, outerConeAngle)
        ObjectCalls.keepReachable(this)
    }

    fun getAdditionalData(extensionName: String): Any? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(Binds.getAdditionalDataBind, segment, extensionName)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun setAdditionalData(extensionName: String, additionalData: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameAndVariantArg(Binds.setAdditionalDataBind, segment, extensionName, additionalData)
        ObjectCalls.keepReachable(this)
    }

    companion object {
        /**
         * Create a new GLTFLight instance from the given Godot `Light3D` node.
         *
         * Generated from Godot docs: GLTFLight.from_node
         */
        fun fromNode(lightNode: Light3D): GLTFLight? {
            val ret = GLTFLight.wrapOwned(ObjectCalls.ptrcallWithObjectArgRetObject(Binds.fromNodeBind, NULL_SEGMENT, lightNode.segment))
            ObjectCalls.keepReachable(lightNode)
            return ret
        }

        /**
         * Creates a new GLTFLight instance by parsing the given `Dictionary`.
         *
         * Generated from Godot docs: GLTFLight.from_dictionary
         */
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

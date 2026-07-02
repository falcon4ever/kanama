package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A tracked face.
 *
 * Generated from Godot docs: XRFaceTracker
 */
class XRFaceTracker(handle: MemorySegment) : XRTracker(handle) {
    var blendShapes: List<Float>
        @JvmName("blendShapesProperty")
        get() = getBlendShapes()
        @JvmName("setBlendShapesProperty")
        set(value) = setBlendShapes(value)

    fun getBlendShape(blendShape: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getBlendShapeBind, handle, blendShape)
    }

    fun setBlendShape(blendShape: Long, weight: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setBlendShapeBind, handle, blendShape, weight)
    }

    fun getBlendShapes(): List<Float> {
        return ObjectCalls.ptrcallNoArgsRetPackedFloat32List(getBlendShapesBind, handle)
    }

    fun setBlendShapes(weights: List<Float>) {
        ObjectCalls.ptrcallWithPackedFloat32ListArg(setBlendShapesBind, handle, weights)
    }

    companion object {
        const val FT_EYE_LOOK_OUT_RIGHT: Long = 0L
        const val FT_EYE_LOOK_IN_RIGHT: Long = 1L
        const val FT_EYE_LOOK_UP_RIGHT: Long = 2L
        const val FT_EYE_LOOK_DOWN_RIGHT: Long = 3L
        const val FT_EYE_LOOK_OUT_LEFT: Long = 4L
        const val FT_EYE_LOOK_IN_LEFT: Long = 5L
        const val FT_EYE_LOOK_UP_LEFT: Long = 6L
        const val FT_EYE_LOOK_DOWN_LEFT: Long = 7L
        const val FT_EYE_CLOSED_RIGHT: Long = 8L
        const val FT_EYE_CLOSED_LEFT: Long = 9L
        const val FT_EYE_SQUINT_RIGHT: Long = 10L
        const val FT_EYE_SQUINT_LEFT: Long = 11L
        const val FT_EYE_WIDE_RIGHT: Long = 12L
        const val FT_EYE_WIDE_LEFT: Long = 13L
        const val FT_EYE_DILATION_RIGHT: Long = 14L
        const val FT_EYE_DILATION_LEFT: Long = 15L
        const val FT_EYE_CONSTRICT_RIGHT: Long = 16L
        const val FT_EYE_CONSTRICT_LEFT: Long = 17L
        const val FT_BROW_PINCH_RIGHT: Long = 18L
        const val FT_BROW_PINCH_LEFT: Long = 19L
        const val FT_BROW_LOWERER_RIGHT: Long = 20L
        const val FT_BROW_LOWERER_LEFT: Long = 21L
        const val FT_BROW_INNER_UP_RIGHT: Long = 22L
        const val FT_BROW_INNER_UP_LEFT: Long = 23L
        const val FT_BROW_OUTER_UP_RIGHT: Long = 24L
        const val FT_BROW_OUTER_UP_LEFT: Long = 25L
        const val FT_NOSE_SNEER_RIGHT: Long = 26L
        const val FT_NOSE_SNEER_LEFT: Long = 27L
        const val FT_NASAL_DILATION_RIGHT: Long = 28L
        const val FT_NASAL_DILATION_LEFT: Long = 29L
        const val FT_NASAL_CONSTRICT_RIGHT: Long = 30L
        const val FT_NASAL_CONSTRICT_LEFT: Long = 31L
        const val FT_CHEEK_SQUINT_RIGHT: Long = 32L
        const val FT_CHEEK_SQUINT_LEFT: Long = 33L
        const val FT_CHEEK_PUFF_RIGHT: Long = 34L
        const val FT_CHEEK_PUFF_LEFT: Long = 35L
        const val FT_CHEEK_SUCK_RIGHT: Long = 36L
        const val FT_CHEEK_SUCK_LEFT: Long = 37L
        const val FT_JAW_OPEN: Long = 38L
        const val FT_MOUTH_CLOSED: Long = 39L
        const val FT_JAW_RIGHT: Long = 40L
        const val FT_JAW_LEFT: Long = 41L
        const val FT_JAW_FORWARD: Long = 42L
        const val FT_JAW_BACKWARD: Long = 43L
        const val FT_JAW_CLENCH: Long = 44L
        const val FT_JAW_MANDIBLE_RAISE: Long = 45L
        const val FT_LIP_SUCK_UPPER_RIGHT: Long = 46L
        const val FT_LIP_SUCK_UPPER_LEFT: Long = 47L
        const val FT_LIP_SUCK_LOWER_RIGHT: Long = 48L
        const val FT_LIP_SUCK_LOWER_LEFT: Long = 49L
        const val FT_LIP_SUCK_CORNER_RIGHT: Long = 50L
        const val FT_LIP_SUCK_CORNER_LEFT: Long = 51L
        const val FT_LIP_FUNNEL_UPPER_RIGHT: Long = 52L
        const val FT_LIP_FUNNEL_UPPER_LEFT: Long = 53L
        const val FT_LIP_FUNNEL_LOWER_RIGHT: Long = 54L
        const val FT_LIP_FUNNEL_LOWER_LEFT: Long = 55L
        const val FT_LIP_PUCKER_UPPER_RIGHT: Long = 56L
        const val FT_LIP_PUCKER_UPPER_LEFT: Long = 57L
        const val FT_LIP_PUCKER_LOWER_RIGHT: Long = 58L
        const val FT_LIP_PUCKER_LOWER_LEFT: Long = 59L
        const val FT_MOUTH_UPPER_UP_RIGHT: Long = 60L
        const val FT_MOUTH_UPPER_UP_LEFT: Long = 61L
        const val FT_MOUTH_LOWER_DOWN_RIGHT: Long = 62L
        const val FT_MOUTH_LOWER_DOWN_LEFT: Long = 63L
        const val FT_MOUTH_UPPER_DEEPEN_RIGHT: Long = 64L
        const val FT_MOUTH_UPPER_DEEPEN_LEFT: Long = 65L
        const val FT_MOUTH_UPPER_RIGHT: Long = 66L
        const val FT_MOUTH_UPPER_LEFT: Long = 67L
        const val FT_MOUTH_LOWER_RIGHT: Long = 68L
        const val FT_MOUTH_LOWER_LEFT: Long = 69L
        const val FT_MOUTH_CORNER_PULL_RIGHT: Long = 70L
        const val FT_MOUTH_CORNER_PULL_LEFT: Long = 71L
        const val FT_MOUTH_CORNER_SLANT_RIGHT: Long = 72L
        const val FT_MOUTH_CORNER_SLANT_LEFT: Long = 73L
        const val FT_MOUTH_FROWN_RIGHT: Long = 74L
        const val FT_MOUTH_FROWN_LEFT: Long = 75L
        const val FT_MOUTH_STRETCH_RIGHT: Long = 76L
        const val FT_MOUTH_STRETCH_LEFT: Long = 77L
        const val FT_MOUTH_DIMPLE_RIGHT: Long = 78L
        const val FT_MOUTH_DIMPLE_LEFT: Long = 79L
        const val FT_MOUTH_RAISER_UPPER: Long = 80L
        const val FT_MOUTH_RAISER_LOWER: Long = 81L
        const val FT_MOUTH_PRESS_RIGHT: Long = 82L
        const val FT_MOUTH_PRESS_LEFT: Long = 83L
        const val FT_MOUTH_TIGHTENER_RIGHT: Long = 84L
        const val FT_MOUTH_TIGHTENER_LEFT: Long = 85L
        const val FT_TONGUE_OUT: Long = 86L
        const val FT_TONGUE_UP: Long = 87L
        const val FT_TONGUE_DOWN: Long = 88L
        const val FT_TONGUE_RIGHT: Long = 89L
        const val FT_TONGUE_LEFT: Long = 90L
        const val FT_TONGUE_ROLL: Long = 91L
        const val FT_TONGUE_BLEND_DOWN: Long = 92L
        const val FT_TONGUE_CURL_UP: Long = 93L
        const val FT_TONGUE_SQUISH: Long = 94L
        const val FT_TONGUE_FLAT: Long = 95L
        const val FT_TONGUE_TWIST_RIGHT: Long = 96L
        const val FT_TONGUE_TWIST_LEFT: Long = 97L
        const val FT_SOFT_PALATE_CLOSE: Long = 98L
        const val FT_THROAT_SWALLOW: Long = 99L
        const val FT_NECK_FLEX_RIGHT: Long = 100L
        const val FT_NECK_FLEX_LEFT: Long = 101L
        const val FT_EYE_CLOSED: Long = 102L
        const val FT_EYE_WIDE: Long = 103L
        const val FT_EYE_SQUINT: Long = 104L
        const val FT_EYE_DILATION: Long = 105L
        const val FT_EYE_CONSTRICT: Long = 106L
        const val FT_BROW_DOWN_RIGHT: Long = 107L
        const val FT_BROW_DOWN_LEFT: Long = 108L
        const val FT_BROW_DOWN: Long = 109L
        const val FT_BROW_UP_RIGHT: Long = 110L
        const val FT_BROW_UP_LEFT: Long = 111L
        const val FT_BROW_UP: Long = 112L
        const val FT_NOSE_SNEER: Long = 113L
        const val FT_NASAL_DILATION: Long = 114L
        const val FT_NASAL_CONSTRICT: Long = 115L
        const val FT_CHEEK_PUFF: Long = 116L
        const val FT_CHEEK_SUCK: Long = 117L
        const val FT_CHEEK_SQUINT: Long = 118L
        const val FT_LIP_SUCK_UPPER: Long = 119L
        const val FT_LIP_SUCK_LOWER: Long = 120L
        const val FT_LIP_SUCK: Long = 121L
        const val FT_LIP_FUNNEL_UPPER: Long = 122L
        const val FT_LIP_FUNNEL_LOWER: Long = 123L
        const val FT_LIP_FUNNEL: Long = 124L
        const val FT_LIP_PUCKER_UPPER: Long = 125L
        const val FT_LIP_PUCKER_LOWER: Long = 126L
        const val FT_LIP_PUCKER: Long = 127L
        const val FT_MOUTH_UPPER_UP: Long = 128L
        const val FT_MOUTH_LOWER_DOWN: Long = 129L
        const val FT_MOUTH_OPEN: Long = 130L
        const val FT_MOUTH_RIGHT: Long = 131L
        const val FT_MOUTH_LEFT: Long = 132L
        const val FT_MOUTH_SMILE_RIGHT: Long = 133L
        const val FT_MOUTH_SMILE_LEFT: Long = 134L
        const val FT_MOUTH_SMILE: Long = 135L
        const val FT_MOUTH_SAD_RIGHT: Long = 136L
        const val FT_MOUTH_SAD_LEFT: Long = 137L
        const val FT_MOUTH_SAD: Long = 138L
        const val FT_MOUTH_STRETCH: Long = 139L
        const val FT_MOUTH_DIMPLE: Long = 140L
        const val FT_MOUTH_TIGHTENER: Long = 141L
        const val FT_MOUTH_PRESS: Long = 142L
        const val FT_MAX: Long = 143L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): XRFaceTracker? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): XRFaceTracker? =
            if (handle.address() == 0L) null else XRFaceTracker(handle)

        private const val GET_BLEND_SHAPE_HASH = 330010046L
        private val getBlendShapeBind by lazy {
            ObjectCalls.getMethodBind("XRFaceTracker", "get_blend_shape", GET_BLEND_SHAPE_HASH)
        }

        private const val SET_BLEND_SHAPE_HASH = 2352588791L
        private val setBlendShapeBind by lazy {
            ObjectCalls.getMethodBind("XRFaceTracker", "set_blend_shape", SET_BLEND_SHAPE_HASH)
        }

        private const val GET_BLEND_SHAPES_HASH = 675695659L
        private val getBlendShapesBind by lazy {
            ObjectCalls.getMethodBind("XRFaceTracker", "get_blend_shapes", GET_BLEND_SHAPES_HASH)
        }

        private const val SET_BLEND_SHAPES_HASH = 2899603908L
        private val setBlendShapesBind by lazy {
            ObjectCalls.getMethodBind("XRFaceTracker", "set_blend_shapes", SET_BLEND_SHAPES_HASH)
        }
    }
}

package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A tracked face.
 *
 * Generated from Godot docs: XRFaceTracker
 */
class XRFaceTracker(handle: GodotHandle) : XRTracker(handle) {
    var blendShapes: List<Float>
        @JvmName("blendShapesProperty")
        get() = getBlendShapes()
        @JvmName("setBlendShapesProperty")
        set(value) = setBlendShapes(value)

    /**
     * Returns the requested face blend shape weight.
     *
     * Generated from Godot docs: XRFaceTracker.get_blend_shape
     */
    fun getBlendShape(blendShape: XRFaceTracker.BlendShapeEntry): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetDouble(Binds.getBlendShapeBind, segment, blendShape.value)
    }

    /**
     * Sets a face blend shape weight.
     *
     * Generated from Godot docs: XRFaceTracker.set_blend_shape
     */
    fun setBlendShape(blendShape: XRFaceTracker.BlendShapeEntry, weight: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndDoubleArg(Binds.setBlendShapeBind, segment, blendShape.value, weight)
    }

    /**
     * The array of face blend shape weights with indices corresponding to the `BlendShapeEntry` enum.
     *
     * Generated from Godot docs: XRFaceTracker.get_blend_shapes
     */
    fun getBlendShapes(): List<Float> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedFloat32List(Binds.getBlendShapesBind, segment)
    }

    /**
     * The array of face blend shape weights with indices corresponding to the `BlendShapeEntry` enum.
     *
     * Generated from Godot docs: XRFaceTracker.set_blend_shapes
     */
    fun setBlendShapes(weights: List<Float>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedFloat32ListArg(Binds.setBlendShapesBind, segment, weights)
    }

    /**
     * Godot's `XRFaceTracker.BlendShapeEntry` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`XRFaceTracker.BlendShapeEntry.<NAME>`).
     *
     * Generated from Godot docs: XRFaceTracker.BlendShapeEntry
     */
    @JvmInline
    value class BlendShapeEntry(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Right eye looks outwards.
             *
             * Generated from Godot docs: XRFaceTracker.FT_EYE_LOOK_OUT_RIGHT
             */
            val EYE_LOOK_OUT_RIGHT: BlendShapeEntry get() = BlendShapeEntry(0L)
            /**
             * Right eye looks inwards.
             *
             * Generated from Godot docs: XRFaceTracker.FT_EYE_LOOK_IN_RIGHT
             */
            val EYE_LOOK_IN_RIGHT: BlendShapeEntry get() = BlendShapeEntry(1L)
            /**
             * Right eye looks upwards.
             *
             * Generated from Godot docs: XRFaceTracker.FT_EYE_LOOK_UP_RIGHT
             */
            val EYE_LOOK_UP_RIGHT: BlendShapeEntry get() = BlendShapeEntry(2L)
            /**
             * Right eye looks downwards.
             *
             * Generated from Godot docs: XRFaceTracker.FT_EYE_LOOK_DOWN_RIGHT
             */
            val EYE_LOOK_DOWN_RIGHT: BlendShapeEntry get() = BlendShapeEntry(3L)
            /**
             * Left eye looks outwards.
             *
             * Generated from Godot docs: XRFaceTracker.FT_EYE_LOOK_OUT_LEFT
             */
            val EYE_LOOK_OUT_LEFT: BlendShapeEntry get() = BlendShapeEntry(4L)
            /**
             * Left eye looks inwards.
             *
             * Generated from Godot docs: XRFaceTracker.FT_EYE_LOOK_IN_LEFT
             */
            val EYE_LOOK_IN_LEFT: BlendShapeEntry get() = BlendShapeEntry(5L)
            /**
             * Left eye looks upwards.
             *
             * Generated from Godot docs: XRFaceTracker.FT_EYE_LOOK_UP_LEFT
             */
            val EYE_LOOK_UP_LEFT: BlendShapeEntry get() = BlendShapeEntry(6L)
            /**
             * Left eye looks downwards.
             *
             * Generated from Godot docs: XRFaceTracker.FT_EYE_LOOK_DOWN_LEFT
             */
            val EYE_LOOK_DOWN_LEFT: BlendShapeEntry get() = BlendShapeEntry(7L)
            /**
             * Closes the right eyelid.
             *
             * Generated from Godot docs: XRFaceTracker.FT_EYE_CLOSED_RIGHT
             */
            val EYE_CLOSED_RIGHT: BlendShapeEntry get() = BlendShapeEntry(8L)
            /**
             * Closes the left eyelid.
             *
             * Generated from Godot docs: XRFaceTracker.FT_EYE_CLOSED_LEFT
             */
            val EYE_CLOSED_LEFT: BlendShapeEntry get() = BlendShapeEntry(9L)
            /**
             * Squeezes the right eye socket muscles.
             *
             * Generated from Godot docs: XRFaceTracker.FT_EYE_SQUINT_RIGHT
             */
            val EYE_SQUINT_RIGHT: BlendShapeEntry get() = BlendShapeEntry(10L)
            /**
             * Squeezes the left eye socket muscles.
             *
             * Generated from Godot docs: XRFaceTracker.FT_EYE_SQUINT_LEFT
             */
            val EYE_SQUINT_LEFT: BlendShapeEntry get() = BlendShapeEntry(11L)
            /**
             * Right eyelid widens beyond relaxed.
             *
             * Generated from Godot docs: XRFaceTracker.FT_EYE_WIDE_RIGHT
             */
            val EYE_WIDE_RIGHT: BlendShapeEntry get() = BlendShapeEntry(12L)
            /**
             * Left eyelid widens beyond relaxed.
             *
             * Generated from Godot docs: XRFaceTracker.FT_EYE_WIDE_LEFT
             */
            val EYE_WIDE_LEFT: BlendShapeEntry get() = BlendShapeEntry(13L)
            /**
             * Dilates the right eye pupil.
             *
             * Generated from Godot docs: XRFaceTracker.FT_EYE_DILATION_RIGHT
             */
            val EYE_DILATION_RIGHT: BlendShapeEntry get() = BlendShapeEntry(14L)
            /**
             * Dilates the left eye pupil.
             *
             * Generated from Godot docs: XRFaceTracker.FT_EYE_DILATION_LEFT
             */
            val EYE_DILATION_LEFT: BlendShapeEntry get() = BlendShapeEntry(15L)
            /**
             * Constricts the right eye pupil.
             *
             * Generated from Godot docs: XRFaceTracker.FT_EYE_CONSTRICT_RIGHT
             */
            val EYE_CONSTRICT_RIGHT: BlendShapeEntry get() = BlendShapeEntry(16L)
            /**
             * Constricts the left eye pupil.
             *
             * Generated from Godot docs: XRFaceTracker.FT_EYE_CONSTRICT_LEFT
             */
            val EYE_CONSTRICT_LEFT: BlendShapeEntry get() = BlendShapeEntry(17L)
            /**
             * Right eyebrow pinches in.
             *
             * Generated from Godot docs: XRFaceTracker.FT_BROW_PINCH_RIGHT
             */
            val BROW_PINCH_RIGHT: BlendShapeEntry get() = BlendShapeEntry(18L)
            /**
             * Left eyebrow pinches in.
             *
             * Generated from Godot docs: XRFaceTracker.FT_BROW_PINCH_LEFT
             */
            val BROW_PINCH_LEFT: BlendShapeEntry get() = BlendShapeEntry(19L)
            /**
             * Outer right eyebrow pulls down.
             *
             * Generated from Godot docs: XRFaceTracker.FT_BROW_LOWERER_RIGHT
             */
            val BROW_LOWERER_RIGHT: BlendShapeEntry get() = BlendShapeEntry(20L)
            /**
             * Outer left eyebrow pulls down.
             *
             * Generated from Godot docs: XRFaceTracker.FT_BROW_LOWERER_LEFT
             */
            val BROW_LOWERER_LEFT: BlendShapeEntry get() = BlendShapeEntry(21L)
            /**
             * Inner right eyebrow pulls up.
             *
             * Generated from Godot docs: XRFaceTracker.FT_BROW_INNER_UP_RIGHT
             */
            val BROW_INNER_UP_RIGHT: BlendShapeEntry get() = BlendShapeEntry(22L)
            /**
             * Inner left eyebrow pulls up.
             *
             * Generated from Godot docs: XRFaceTracker.FT_BROW_INNER_UP_LEFT
             */
            val BROW_INNER_UP_LEFT: BlendShapeEntry get() = BlendShapeEntry(23L)
            /**
             * Outer right eyebrow pulls up.
             *
             * Generated from Godot docs: XRFaceTracker.FT_BROW_OUTER_UP_RIGHT
             */
            val BROW_OUTER_UP_RIGHT: BlendShapeEntry get() = BlendShapeEntry(24L)
            /**
             * Outer left eyebrow pulls up.
             *
             * Generated from Godot docs: XRFaceTracker.FT_BROW_OUTER_UP_LEFT
             */
            val BROW_OUTER_UP_LEFT: BlendShapeEntry get() = BlendShapeEntry(25L)
            /**
             * Right side face sneers.
             *
             * Generated from Godot docs: XRFaceTracker.FT_NOSE_SNEER_RIGHT
             */
            val NOSE_SNEER_RIGHT: BlendShapeEntry get() = BlendShapeEntry(26L)
            /**
             * Left side face sneers.
             *
             * Generated from Godot docs: XRFaceTracker.FT_NOSE_SNEER_LEFT
             */
            val NOSE_SNEER_LEFT: BlendShapeEntry get() = BlendShapeEntry(27L)
            /**
             * Right side nose canal dilates.
             *
             * Generated from Godot docs: XRFaceTracker.FT_NASAL_DILATION_RIGHT
             */
            val NASAL_DILATION_RIGHT: BlendShapeEntry get() = BlendShapeEntry(28L)
            /**
             * Left side nose canal dilates.
             *
             * Generated from Godot docs: XRFaceTracker.FT_NASAL_DILATION_LEFT
             */
            val NASAL_DILATION_LEFT: BlendShapeEntry get() = BlendShapeEntry(29L)
            /**
             * Right side nose canal constricts.
             *
             * Generated from Godot docs: XRFaceTracker.FT_NASAL_CONSTRICT_RIGHT
             */
            val NASAL_CONSTRICT_RIGHT: BlendShapeEntry get() = BlendShapeEntry(30L)
            /**
             * Left side nose canal constricts.
             *
             * Generated from Godot docs: XRFaceTracker.FT_NASAL_CONSTRICT_LEFT
             */
            val NASAL_CONSTRICT_LEFT: BlendShapeEntry get() = BlendShapeEntry(31L)
            /**
             * Raises the right side cheek.
             *
             * Generated from Godot docs: XRFaceTracker.FT_CHEEK_SQUINT_RIGHT
             */
            val CHEEK_SQUINT_RIGHT: BlendShapeEntry get() = BlendShapeEntry(32L)
            /**
             * Raises the left side cheek.
             *
             * Generated from Godot docs: XRFaceTracker.FT_CHEEK_SQUINT_LEFT
             */
            val CHEEK_SQUINT_LEFT: BlendShapeEntry get() = BlendShapeEntry(33L)
            /**
             * Puffs the right side cheek.
             *
             * Generated from Godot docs: XRFaceTracker.FT_CHEEK_PUFF_RIGHT
             */
            val CHEEK_PUFF_RIGHT: BlendShapeEntry get() = BlendShapeEntry(34L)
            /**
             * Puffs the left side cheek.
             *
             * Generated from Godot docs: XRFaceTracker.FT_CHEEK_PUFF_LEFT
             */
            val CHEEK_PUFF_LEFT: BlendShapeEntry get() = BlendShapeEntry(35L)
            /**
             * Sucks in the right side cheek.
             *
             * Generated from Godot docs: XRFaceTracker.FT_CHEEK_SUCK_RIGHT
             */
            val CHEEK_SUCK_RIGHT: BlendShapeEntry get() = BlendShapeEntry(36L)
            /**
             * Sucks in the left side cheek.
             *
             * Generated from Godot docs: XRFaceTracker.FT_CHEEK_SUCK_LEFT
             */
            val CHEEK_SUCK_LEFT: BlendShapeEntry get() = BlendShapeEntry(37L)
            /**
             * Opens jawbone.
             *
             * Generated from Godot docs: XRFaceTracker.FT_JAW_OPEN
             */
            val JAW_OPEN: BlendShapeEntry get() = BlendShapeEntry(38L)
            /**
             * Closes the mouth.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_CLOSED
             */
            val MOUTH_CLOSED: BlendShapeEntry get() = BlendShapeEntry(39L)
            /**
             * Pushes jawbone right.
             *
             * Generated from Godot docs: XRFaceTracker.FT_JAW_RIGHT
             */
            val JAW_RIGHT: BlendShapeEntry get() = BlendShapeEntry(40L)
            /**
             * Pushes jawbone left.
             *
             * Generated from Godot docs: XRFaceTracker.FT_JAW_LEFT
             */
            val JAW_LEFT: BlendShapeEntry get() = BlendShapeEntry(41L)
            /**
             * Pushes jawbone forward.
             *
             * Generated from Godot docs: XRFaceTracker.FT_JAW_FORWARD
             */
            val JAW_FORWARD: BlendShapeEntry get() = BlendShapeEntry(42L)
            /**
             * Pushes jawbone backward.
             *
             * Generated from Godot docs: XRFaceTracker.FT_JAW_BACKWARD
             */
            val JAW_BACKWARD: BlendShapeEntry get() = BlendShapeEntry(43L)
            /**
             * Flexes jaw muscles.
             *
             * Generated from Godot docs: XRFaceTracker.FT_JAW_CLENCH
             */
            val JAW_CLENCH: BlendShapeEntry get() = BlendShapeEntry(44L)
            /**
             * Raises the jawbone.
             *
             * Generated from Godot docs: XRFaceTracker.FT_JAW_MANDIBLE_RAISE
             */
            val JAW_MANDIBLE_RAISE: BlendShapeEntry get() = BlendShapeEntry(45L)
            /**
             * Upper right lip part tucks in the mouth.
             *
             * Generated from Godot docs: XRFaceTracker.FT_LIP_SUCK_UPPER_RIGHT
             */
            val LIP_SUCK_UPPER_RIGHT: BlendShapeEntry get() = BlendShapeEntry(46L)
            /**
             * Upper left lip part tucks in the mouth.
             *
             * Generated from Godot docs: XRFaceTracker.FT_LIP_SUCK_UPPER_LEFT
             */
            val LIP_SUCK_UPPER_LEFT: BlendShapeEntry get() = BlendShapeEntry(47L)
            /**
             * Lower right lip part tucks in the mouth.
             *
             * Generated from Godot docs: XRFaceTracker.FT_LIP_SUCK_LOWER_RIGHT
             */
            val LIP_SUCK_LOWER_RIGHT: BlendShapeEntry get() = BlendShapeEntry(48L)
            /**
             * Lower left lip part tucks in the mouth.
             *
             * Generated from Godot docs: XRFaceTracker.FT_LIP_SUCK_LOWER_LEFT
             */
            val LIP_SUCK_LOWER_LEFT: BlendShapeEntry get() = BlendShapeEntry(49L)
            /**
             * Right lip corner folds into the mouth.
             *
             * Generated from Godot docs: XRFaceTracker.FT_LIP_SUCK_CORNER_RIGHT
             */
            val LIP_SUCK_CORNER_RIGHT: BlendShapeEntry get() = BlendShapeEntry(50L)
            /**
             * Left lip corner folds into the mouth.
             *
             * Generated from Godot docs: XRFaceTracker.FT_LIP_SUCK_CORNER_LEFT
             */
            val LIP_SUCK_CORNER_LEFT: BlendShapeEntry get() = BlendShapeEntry(51L)
            /**
             * Upper right lip part pushes into a funnel.
             *
             * Generated from Godot docs: XRFaceTracker.FT_LIP_FUNNEL_UPPER_RIGHT
             */
            val LIP_FUNNEL_UPPER_RIGHT: BlendShapeEntry get() = BlendShapeEntry(52L)
            /**
             * Upper left lip part pushes into a funnel.
             *
             * Generated from Godot docs: XRFaceTracker.FT_LIP_FUNNEL_UPPER_LEFT
             */
            val LIP_FUNNEL_UPPER_LEFT: BlendShapeEntry get() = BlendShapeEntry(53L)
            /**
             * Lower right lip part pushes into a funnel.
             *
             * Generated from Godot docs: XRFaceTracker.FT_LIP_FUNNEL_LOWER_RIGHT
             */
            val LIP_FUNNEL_LOWER_RIGHT: BlendShapeEntry get() = BlendShapeEntry(54L)
            /**
             * Lower left lip part pushes into a funnel.
             *
             * Generated from Godot docs: XRFaceTracker.FT_LIP_FUNNEL_LOWER_LEFT
             */
            val LIP_FUNNEL_LOWER_LEFT: BlendShapeEntry get() = BlendShapeEntry(55L)
            /**
             * Upper right lip part pushes outwards.
             *
             * Generated from Godot docs: XRFaceTracker.FT_LIP_PUCKER_UPPER_RIGHT
             */
            val LIP_PUCKER_UPPER_RIGHT: BlendShapeEntry get() = BlendShapeEntry(56L)
            /**
             * Upper left lip part pushes outwards.
             *
             * Generated from Godot docs: XRFaceTracker.FT_LIP_PUCKER_UPPER_LEFT
             */
            val LIP_PUCKER_UPPER_LEFT: BlendShapeEntry get() = BlendShapeEntry(57L)
            /**
             * Lower right lip part pushes outwards.
             *
             * Generated from Godot docs: XRFaceTracker.FT_LIP_PUCKER_LOWER_RIGHT
             */
            val LIP_PUCKER_LOWER_RIGHT: BlendShapeEntry get() = BlendShapeEntry(58L)
            /**
             * Lower left lip part pushes outwards.
             *
             * Generated from Godot docs: XRFaceTracker.FT_LIP_PUCKER_LOWER_LEFT
             */
            val LIP_PUCKER_LOWER_LEFT: BlendShapeEntry get() = BlendShapeEntry(59L)
            /**
             * Upper right part of the lip pulls up.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_UPPER_UP_RIGHT
             */
            val MOUTH_UPPER_UP_RIGHT: BlendShapeEntry get() = BlendShapeEntry(60L)
            /**
             * Upper left part of the lip pulls up.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_UPPER_UP_LEFT
             */
            val MOUTH_UPPER_UP_LEFT: BlendShapeEntry get() = BlendShapeEntry(61L)
            /**
             * Lower right part of the lip pulls up.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_LOWER_DOWN_RIGHT
             */
            val MOUTH_LOWER_DOWN_RIGHT: BlendShapeEntry get() = BlendShapeEntry(62L)
            /**
             * Lower left part of the lip pulls up.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_LOWER_DOWN_LEFT
             */
            val MOUTH_LOWER_DOWN_LEFT: BlendShapeEntry get() = BlendShapeEntry(63L)
            /**
             * Upper right lip part pushes in the cheek.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_UPPER_DEEPEN_RIGHT
             */
            val MOUTH_UPPER_DEEPEN_RIGHT: BlendShapeEntry get() = BlendShapeEntry(64L)
            /**
             * Upper left lip part pushes in the cheek.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_UPPER_DEEPEN_LEFT
             */
            val MOUTH_UPPER_DEEPEN_LEFT: BlendShapeEntry get() = BlendShapeEntry(65L)
            /**
             * Moves upper lip right.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_UPPER_RIGHT
             */
            val MOUTH_UPPER_RIGHT: BlendShapeEntry get() = BlendShapeEntry(66L)
            /**
             * Moves upper lip left.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_UPPER_LEFT
             */
            val MOUTH_UPPER_LEFT: BlendShapeEntry get() = BlendShapeEntry(67L)
            /**
             * Moves lower lip right.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_LOWER_RIGHT
             */
            val MOUTH_LOWER_RIGHT: BlendShapeEntry get() = BlendShapeEntry(68L)
            /**
             * Moves lower lip left.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_LOWER_LEFT
             */
            val MOUTH_LOWER_LEFT: BlendShapeEntry get() = BlendShapeEntry(69L)
            /**
             * Right lip corner pulls diagonally up and out.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_CORNER_PULL_RIGHT
             */
            val MOUTH_CORNER_PULL_RIGHT: BlendShapeEntry get() = BlendShapeEntry(70L)
            /**
             * Left lip corner pulls diagonally up and out.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_CORNER_PULL_LEFT
             */
            val MOUTH_CORNER_PULL_LEFT: BlendShapeEntry get() = BlendShapeEntry(71L)
            /**
             * Right corner lip slants up.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_CORNER_SLANT_RIGHT
             */
            val MOUTH_CORNER_SLANT_RIGHT: BlendShapeEntry get() = BlendShapeEntry(72L)
            /**
             * Left corner lip slants up.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_CORNER_SLANT_LEFT
             */
            val MOUTH_CORNER_SLANT_LEFT: BlendShapeEntry get() = BlendShapeEntry(73L)
            /**
             * Right corner lip pulls down.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_FROWN_RIGHT
             */
            val MOUTH_FROWN_RIGHT: BlendShapeEntry get() = BlendShapeEntry(74L)
            /**
             * Left corner lip pulls down.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_FROWN_LEFT
             */
            val MOUTH_FROWN_LEFT: BlendShapeEntry get() = BlendShapeEntry(75L)
            /**
             * Mouth corner lip pulls out and down.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_STRETCH_RIGHT
             */
            val MOUTH_STRETCH_RIGHT: BlendShapeEntry get() = BlendShapeEntry(76L)
            /**
             * Mouth corner lip pulls out and down.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_STRETCH_LEFT
             */
            val MOUTH_STRETCH_LEFT: BlendShapeEntry get() = BlendShapeEntry(77L)
            /**
             * Right lip corner is pushed backwards.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_DIMPLE_RIGHT
             */
            val MOUTH_DIMPLE_RIGHT: BlendShapeEntry get() = BlendShapeEntry(78L)
            /**
             * Left lip corner is pushed backwards.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_DIMPLE_LEFT
             */
            val MOUTH_DIMPLE_LEFT: BlendShapeEntry get() = BlendShapeEntry(79L)
            /**
             * Raises and slightly pushes out the upper mouth.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_RAISER_UPPER
             */
            val MOUTH_RAISER_UPPER: BlendShapeEntry get() = BlendShapeEntry(80L)
            /**
             * Raises and slightly pushes out the lower mouth.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_RAISER_LOWER
             */
            val MOUTH_RAISER_LOWER: BlendShapeEntry get() = BlendShapeEntry(81L)
            /**
             * Right side lips press and flatten together vertically.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_PRESS_RIGHT
             */
            val MOUTH_PRESS_RIGHT: BlendShapeEntry get() = BlendShapeEntry(82L)
            /**
             * Left side lips press and flatten together vertically.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_PRESS_LEFT
             */
            val MOUTH_PRESS_LEFT: BlendShapeEntry get() = BlendShapeEntry(83L)
            /**
             * Right side lips squeeze together horizontally.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_TIGHTENER_RIGHT
             */
            val MOUTH_TIGHTENER_RIGHT: BlendShapeEntry get() = BlendShapeEntry(84L)
            /**
             * Left side lips squeeze together horizontally.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_TIGHTENER_LEFT
             */
            val MOUTH_TIGHTENER_LEFT: BlendShapeEntry get() = BlendShapeEntry(85L)
            /**
             * Tongue visibly sticks out of the mouth.
             *
             * Generated from Godot docs: XRFaceTracker.FT_TONGUE_OUT
             */
            val TONGUE_OUT: BlendShapeEntry get() = BlendShapeEntry(86L)
            /**
             * Tongue points upwards.
             *
             * Generated from Godot docs: XRFaceTracker.FT_TONGUE_UP
             */
            val TONGUE_UP: BlendShapeEntry get() = BlendShapeEntry(87L)
            /**
             * Tongue points downwards.
             *
             * Generated from Godot docs: XRFaceTracker.FT_TONGUE_DOWN
             */
            val TONGUE_DOWN: BlendShapeEntry get() = BlendShapeEntry(88L)
            /**
             * Tongue points right.
             *
             * Generated from Godot docs: XRFaceTracker.FT_TONGUE_RIGHT
             */
            val TONGUE_RIGHT: BlendShapeEntry get() = BlendShapeEntry(89L)
            /**
             * Tongue points left.
             *
             * Generated from Godot docs: XRFaceTracker.FT_TONGUE_LEFT
             */
            val TONGUE_LEFT: BlendShapeEntry get() = BlendShapeEntry(90L)
            /**
             * Sides of the tongue funnel, creating a roll.
             *
             * Generated from Godot docs: XRFaceTracker.FT_TONGUE_ROLL
             */
            val TONGUE_ROLL: BlendShapeEntry get() = BlendShapeEntry(91L)
            /**
             * Tongue arches up then down inside the mouth.
             *
             * Generated from Godot docs: XRFaceTracker.FT_TONGUE_BLEND_DOWN
             */
            val TONGUE_BLEND_DOWN: BlendShapeEntry get() = BlendShapeEntry(92L)
            /**
             * Tongue arches down then up inside the mouth.
             *
             * Generated from Godot docs: XRFaceTracker.FT_TONGUE_CURL_UP
             */
            val TONGUE_CURL_UP: BlendShapeEntry get() = BlendShapeEntry(93L)
            /**
             * Tongue squishes together and thickens.
             *
             * Generated from Godot docs: XRFaceTracker.FT_TONGUE_SQUISH
             */
            val TONGUE_SQUISH: BlendShapeEntry get() = BlendShapeEntry(94L)
            /**
             * Tongue flattens and thins out.
             *
             * Generated from Godot docs: XRFaceTracker.FT_TONGUE_FLAT
             */
            val TONGUE_FLAT: BlendShapeEntry get() = BlendShapeEntry(95L)
            /**
             * Tongue tip rotates clockwise, with the rest following gradually.
             *
             * Generated from Godot docs: XRFaceTracker.FT_TONGUE_TWIST_RIGHT
             */
            val TONGUE_TWIST_RIGHT: BlendShapeEntry get() = BlendShapeEntry(96L)
            /**
             * Tongue tip rotates counter-clockwise, with the rest following gradually.
             *
             * Generated from Godot docs: XRFaceTracker.FT_TONGUE_TWIST_LEFT
             */
            val TONGUE_TWIST_LEFT: BlendShapeEntry get() = BlendShapeEntry(97L)
            /**
             * Inner mouth throat closes.
             *
             * Generated from Godot docs: XRFaceTracker.FT_SOFT_PALATE_CLOSE
             */
            val SOFT_PALATE_CLOSE: BlendShapeEntry get() = BlendShapeEntry(98L)
            /**
             * The Adam's apple visibly swallows.
             *
             * Generated from Godot docs: XRFaceTracker.FT_THROAT_SWALLOW
             */
            val THROAT_SWALLOW: BlendShapeEntry get() = BlendShapeEntry(99L)
            /**
             * Right side neck visibly flexes.
             *
             * Generated from Godot docs: XRFaceTracker.FT_NECK_FLEX_RIGHT
             */
            val NECK_FLEX_RIGHT: BlendShapeEntry get() = BlendShapeEntry(100L)
            /**
             * Left side neck visibly flexes.
             *
             * Generated from Godot docs: XRFaceTracker.FT_NECK_FLEX_LEFT
             */
            val NECK_FLEX_LEFT: BlendShapeEntry get() = BlendShapeEntry(101L)
            /**
             * Closes both eye lids.
             *
             * Generated from Godot docs: XRFaceTracker.FT_EYE_CLOSED
             */
            val EYE_CLOSED: BlendShapeEntry get() = BlendShapeEntry(102L)
            /**
             * Widens both eye lids.
             *
             * Generated from Godot docs: XRFaceTracker.FT_EYE_WIDE
             */
            val EYE_WIDE: BlendShapeEntry get() = BlendShapeEntry(103L)
            /**
             * Squints both eye lids.
             *
             * Generated from Godot docs: XRFaceTracker.FT_EYE_SQUINT
             */
            val EYE_SQUINT: BlendShapeEntry get() = BlendShapeEntry(104L)
            /**
             * Dilates both pupils.
             *
             * Generated from Godot docs: XRFaceTracker.FT_EYE_DILATION
             */
            val EYE_DILATION: BlendShapeEntry get() = BlendShapeEntry(105L)
            /**
             * Constricts both pupils.
             *
             * Generated from Godot docs: XRFaceTracker.FT_EYE_CONSTRICT
             */
            val EYE_CONSTRICT: BlendShapeEntry get() = BlendShapeEntry(106L)
            /**
             * Pulls the right eyebrow down and in.
             *
             * Generated from Godot docs: XRFaceTracker.FT_BROW_DOWN_RIGHT
             */
            val BROW_DOWN_RIGHT: BlendShapeEntry get() = BlendShapeEntry(107L)
            /**
             * Pulls the left eyebrow down and in.
             *
             * Generated from Godot docs: XRFaceTracker.FT_BROW_DOWN_LEFT
             */
            val BROW_DOWN_LEFT: BlendShapeEntry get() = BlendShapeEntry(108L)
            /**
             * Pulls both eyebrows down and in.
             *
             * Generated from Godot docs: XRFaceTracker.FT_BROW_DOWN
             */
            val BROW_DOWN: BlendShapeEntry get() = BlendShapeEntry(109L)
            /**
             * Right brow appears worried.
             *
             * Generated from Godot docs: XRFaceTracker.FT_BROW_UP_RIGHT
             */
            val BROW_UP_RIGHT: BlendShapeEntry get() = BlendShapeEntry(110L)
            /**
             * Left brow appears worried.
             *
             * Generated from Godot docs: XRFaceTracker.FT_BROW_UP_LEFT
             */
            val BROW_UP_LEFT: BlendShapeEntry get() = BlendShapeEntry(111L)
            /**
             * Both brows appear worried.
             *
             * Generated from Godot docs: XRFaceTracker.FT_BROW_UP
             */
            val BROW_UP: BlendShapeEntry get() = BlendShapeEntry(112L)
            /**
             * Entire face sneers.
             *
             * Generated from Godot docs: XRFaceTracker.FT_NOSE_SNEER
             */
            val NOSE_SNEER: BlendShapeEntry get() = BlendShapeEntry(113L)
            /**
             * Both nose canals dilate.
             *
             * Generated from Godot docs: XRFaceTracker.FT_NASAL_DILATION
             */
            val NASAL_DILATION: BlendShapeEntry get() = BlendShapeEntry(114L)
            /**
             * Both nose canals constrict.
             *
             * Generated from Godot docs: XRFaceTracker.FT_NASAL_CONSTRICT
             */
            val NASAL_CONSTRICT: BlendShapeEntry get() = BlendShapeEntry(115L)
            /**
             * Puffs both cheeks.
             *
             * Generated from Godot docs: XRFaceTracker.FT_CHEEK_PUFF
             */
            val CHEEK_PUFF: BlendShapeEntry get() = BlendShapeEntry(116L)
            /**
             * Sucks in both cheeks.
             *
             * Generated from Godot docs: XRFaceTracker.FT_CHEEK_SUCK
             */
            val CHEEK_SUCK: BlendShapeEntry get() = BlendShapeEntry(117L)
            /**
             * Raises both cheeks.
             *
             * Generated from Godot docs: XRFaceTracker.FT_CHEEK_SQUINT
             */
            val CHEEK_SQUINT: BlendShapeEntry get() = BlendShapeEntry(118L)
            /**
             * Tucks in the upper lips.
             *
             * Generated from Godot docs: XRFaceTracker.FT_LIP_SUCK_UPPER
             */
            val LIP_SUCK_UPPER: BlendShapeEntry get() = BlendShapeEntry(119L)
            /**
             * Tucks in the lower lips.
             *
             * Generated from Godot docs: XRFaceTracker.FT_LIP_SUCK_LOWER
             */
            val LIP_SUCK_LOWER: BlendShapeEntry get() = BlendShapeEntry(120L)
            /**
             * Tucks in both lips.
             *
             * Generated from Godot docs: XRFaceTracker.FT_LIP_SUCK
             */
            val LIP_SUCK: BlendShapeEntry get() = BlendShapeEntry(121L)
            /**
             * Funnels in the upper lips.
             *
             * Generated from Godot docs: XRFaceTracker.FT_LIP_FUNNEL_UPPER
             */
            val LIP_FUNNEL_UPPER: BlendShapeEntry get() = BlendShapeEntry(122L)
            /**
             * Funnels in the lower lips.
             *
             * Generated from Godot docs: XRFaceTracker.FT_LIP_FUNNEL_LOWER
             */
            val LIP_FUNNEL_LOWER: BlendShapeEntry get() = BlendShapeEntry(123L)
            /**
             * Funnels in both lips.
             *
             * Generated from Godot docs: XRFaceTracker.FT_LIP_FUNNEL
             */
            val LIP_FUNNEL: BlendShapeEntry get() = BlendShapeEntry(124L)
            /**
             * Upper lip part pushes outwards.
             *
             * Generated from Godot docs: XRFaceTracker.FT_LIP_PUCKER_UPPER
             */
            val LIP_PUCKER_UPPER: BlendShapeEntry get() = BlendShapeEntry(125L)
            /**
             * Lower lip part pushes outwards.
             *
             * Generated from Godot docs: XRFaceTracker.FT_LIP_PUCKER_LOWER
             */
            val LIP_PUCKER_LOWER: BlendShapeEntry get() = BlendShapeEntry(126L)
            /**
             * Lips push outwards.
             *
             * Generated from Godot docs: XRFaceTracker.FT_LIP_PUCKER
             */
            val LIP_PUCKER: BlendShapeEntry get() = BlendShapeEntry(127L)
            /**
             * Raises the upper lips.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_UPPER_UP
             */
            val MOUTH_UPPER_UP: BlendShapeEntry get() = BlendShapeEntry(128L)
            /**
             * Lowers the lower lips.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_LOWER_DOWN
             */
            val MOUTH_LOWER_DOWN: BlendShapeEntry get() = BlendShapeEntry(129L)
            /**
             * Mouth opens, revealing teeth.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_OPEN
             */
            val MOUTH_OPEN: BlendShapeEntry get() = BlendShapeEntry(130L)
            /**
             * Moves mouth right.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_RIGHT
             */
            val MOUTH_RIGHT: BlendShapeEntry get() = BlendShapeEntry(131L)
            /**
             * Moves mouth left.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_LEFT
             */
            val MOUTH_LEFT: BlendShapeEntry get() = BlendShapeEntry(132L)
            /**
             * Right side of the mouth smiles.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_SMILE_RIGHT
             */
            val MOUTH_SMILE_RIGHT: BlendShapeEntry get() = BlendShapeEntry(133L)
            /**
             * Left side of the mouth smiles.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_SMILE_LEFT
             */
            val MOUTH_SMILE_LEFT: BlendShapeEntry get() = BlendShapeEntry(134L)
            /**
             * Mouth expresses a smile.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_SMILE
             */
            val MOUTH_SMILE: BlendShapeEntry get() = BlendShapeEntry(135L)
            /**
             * Right side of the mouth expresses sadness.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_SAD_RIGHT
             */
            val MOUTH_SAD_RIGHT: BlendShapeEntry get() = BlendShapeEntry(136L)
            /**
             * Left side of the mouth expresses sadness.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_SAD_LEFT
             */
            val MOUTH_SAD_LEFT: BlendShapeEntry get() = BlendShapeEntry(137L)
            /**
             * Mouth expresses sadness.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_SAD
             */
            val MOUTH_SAD: BlendShapeEntry get() = BlendShapeEntry(138L)
            /**
             * Mouth stretches.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_STRETCH
             */
            val MOUTH_STRETCH: BlendShapeEntry get() = BlendShapeEntry(139L)
            /**
             * Lip corners dimple.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_DIMPLE
             */
            val MOUTH_DIMPLE: BlendShapeEntry get() = BlendShapeEntry(140L)
            /**
             * Mouth tightens.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_TIGHTENER
             */
            val MOUTH_TIGHTENER: BlendShapeEntry get() = BlendShapeEntry(141L)
            /**
             * Mouth presses together.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MOUTH_PRESS
             */
            val MOUTH_PRESS: BlendShapeEntry get() = BlendShapeEntry(142L)
            /**
             * Represents the size of the `BlendShapeEntry` enum.
             *
             * Generated from Godot docs: XRFaceTracker.FT_MAX
             */
            val MAX: BlendShapeEntry get() = BlendShapeEntry(143L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): XRFaceTracker? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): XRFaceTracker? =
            if (handle.address() == 0L) null else RefCounted.owned(XRFaceTracker(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): XRFaceTracker? =
            if (handle.address() == 0L) null else XRFaceTracker(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_BLEND_SHAPE_HASH = 330010046L
        @JvmField
        val getBlendShapeBind =
            ObjectCalls.getMethodBind("XRFaceTracker", "get_blend_shape", GET_BLEND_SHAPE_HASH)

        private const val SET_BLEND_SHAPE_HASH = 2352588791L
        @JvmField
        val setBlendShapeBind =
            ObjectCalls.getMethodBind("XRFaceTracker", "set_blend_shape", SET_BLEND_SHAPE_HASH)

        private const val GET_BLEND_SHAPES_HASH = 675695659L
        @JvmField
        val getBlendShapesBind =
            ObjectCalls.getMethodBind("XRFaceTracker", "get_blend_shapes", GET_BLEND_SHAPES_HASH)

        private const val SET_BLEND_SHAPES_HASH = 2899603908L
        @JvmField
        val setBlendShapesBind =
            ObjectCalls.getMethodBind("XRFaceTracker", "set_blend_shapes", SET_BLEND_SHAPES_HASH)
    }
}

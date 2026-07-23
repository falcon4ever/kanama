package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * Holds a particle configuration for `GPUParticles2D` or `GPUParticles3D` nodes.
 *
 * Generated from Godot docs: ParticleProcessMaterial
 */
class ParticleProcessMaterial(handle: MemorySegment) : Material(handle) {
    var lifetimeRandomness: Double
        @JvmName("lifetimeRandomnessProperty")
        get() = getLifetimeRandomness()
        @JvmName("setLifetimeRandomnessProperty")
        set(value) = setLifetimeRandomness(value)

    var particleFlagAlignY: Boolean
        @JvmName("particleFlagAlignYProperty")
        get() = getParticleFlag(0L)
        @JvmName("setParticleFlagAlignYProperty")
        set(value) = setParticleFlag(0L, value)

    var particleFlagRotateY: Boolean
        @JvmName("particleFlagRotateYProperty")
        get() = getParticleFlag(1L)
        @JvmName("setParticleFlagRotateYProperty")
        set(value) = setParticleFlag(1L, value)

    var particleFlagDisableZ: Boolean
        @JvmName("particleFlagDisableZProperty")
        get() = getParticleFlag(2L)
        @JvmName("setParticleFlagDisableZProperty")
        set(value) = setParticleFlag(2L, value)

    var particleFlagDampingAsFriction: Boolean
        @JvmName("particleFlagDampingAsFrictionProperty")
        get() = getParticleFlag(3L)
        @JvmName("setParticleFlagDampingAsFrictionProperty")
        set(value) = setParticleFlag(3L, value)

    var particleFlagInheritEmitterScale: Boolean
        @JvmName("particleFlagInheritEmitterScaleProperty")
        get() = getParticleFlag(4L)
        @JvmName("setParticleFlagInheritEmitterScaleProperty")
        set(value) = setParticleFlag(4L, value)

    var emissionShapeOffset: Vector3
        @JvmName("emissionShapeOffsetProperty")
        get() = getEmissionShapeOffset()
        @JvmName("setEmissionShapeOffsetProperty")
        set(value) = setEmissionShapeOffset(value)

    var emissionShapeScale: Vector3
        @JvmName("emissionShapeScaleProperty")
        get() = getEmissionShapeScale()
        @JvmName("setEmissionShapeScaleProperty")
        set(value) = setEmissionShapeScale(value)

    var emissionShape: Long
        @JvmName("emissionShapeProperty")
        get() = getEmissionShape()
        @JvmName("setEmissionShapeProperty")
        set(value) = setEmissionShape(value)

    var emissionSphereRadius: Double
        @JvmName("emissionSphereRadiusProperty")
        get() = getEmissionSphereRadius()
        @JvmName("setEmissionSphereRadiusProperty")
        set(value) = setEmissionSphereRadius(value)

    var emissionBoxExtents: Vector3
        @JvmName("emissionBoxExtentsProperty")
        get() = getEmissionBoxExtents()
        @JvmName("setEmissionBoxExtentsProperty")
        set(value) = setEmissionBoxExtents(value)

    var emissionPointTexture: Texture2D?
        @JvmName("emissionPointTextureProperty")
        get() = getEmissionPointTexture()
        @JvmName("setEmissionPointTextureProperty")
        set(value) = setEmissionPointTexture(value)

    var emissionNormalTexture: Texture2D?
        @JvmName("emissionNormalTextureProperty")
        get() = getEmissionNormalTexture()
        @JvmName("setEmissionNormalTextureProperty")
        set(value) = setEmissionNormalTexture(value)

    var emissionColorTexture: Texture2D?
        @JvmName("emissionColorTextureProperty")
        get() = getEmissionColorTexture()
        @JvmName("setEmissionColorTextureProperty")
        set(value) = setEmissionColorTexture(value)

    var emissionPointCount: Int
        @JvmName("emissionPointCountProperty")
        get() = getEmissionPointCount()
        @JvmName("setEmissionPointCountProperty")
        set(value) = setEmissionPointCount(value)

    var emissionRingAxis: Vector3
        @JvmName("emissionRingAxisProperty")
        get() = getEmissionRingAxis()
        @JvmName("setEmissionRingAxisProperty")
        set(value) = setEmissionRingAxis(value)

    var emissionRingHeight: Double
        @JvmName("emissionRingHeightProperty")
        get() = getEmissionRingHeight()
        @JvmName("setEmissionRingHeightProperty")
        set(value) = setEmissionRingHeight(value)

    var emissionRingRadius: Double
        @JvmName("emissionRingRadiusProperty")
        get() = getEmissionRingRadius()
        @JvmName("setEmissionRingRadiusProperty")
        set(value) = setEmissionRingRadius(value)

    var emissionRingInnerRadius: Double
        @JvmName("emissionRingInnerRadiusProperty")
        get() = getEmissionRingInnerRadius()
        @JvmName("setEmissionRingInnerRadiusProperty")
        set(value) = setEmissionRingInnerRadius(value)

    var emissionRingConeAngle: Double
        @JvmName("emissionRingConeAngleProperty")
        get() = getEmissionRingConeAngle()
        @JvmName("setEmissionRingConeAngleProperty")
        set(value) = setEmissionRingConeAngle(value)

    var angle: Vector2
        @JvmName("angleProperty")
        get() = getParam(7L)
        @JvmName("setAngleProperty")
        set(value) = setParam(7L, value)

    var angleMin: Double
        @JvmName("angleMinProperty")
        get() = getParamMin(7L)
        @JvmName("setAngleMinProperty")
        set(value) = setParamMin(7L, value)

    var angleMax: Double
        @JvmName("angleMaxProperty")
        get() = getParamMax(7L)
        @JvmName("setAngleMaxProperty")
        set(value) = setParamMax(7L, value)

    var angleCurve: Texture2D?
        @JvmName("angleCurveProperty")
        get() = getParamTexture(7L)
        @JvmName("setAngleCurveProperty")
        set(value) = setParamTexture(7L, value)

    var useRotation3d: Boolean
        @JvmName("useRotation3dProperty")
        get() = isUsingRotation3d()
        @JvmName("setUseRotation3dProperty")
        set(value) = setUseRotation3d(value)

    var rotation3dMin: Vector3
        @JvmName("rotation3dMinProperty")
        get() = getRotation3dMin()
        @JvmName("setRotation3dMinProperty")
        set(value) = setRotation3dMin(value)

    var rotation3dMax: Vector3
        @JvmName("rotation3dMaxProperty")
        get() = getRotation3dMax()
        @JvmName("setRotation3dMaxProperty")
        set(value) = setRotation3dMax(value)

    var inheritVelocityRatio: Double
        @JvmName("inheritVelocityRatioProperty")
        get() = getInheritVelocityRatio()
        @JvmName("setInheritVelocityRatioProperty")
        set(value) = setInheritVelocityRatio(value)

    var velocityPivot: Vector3
        @JvmName("velocityPivotProperty")
        get() = getVelocityPivot()
        @JvmName("setVelocityPivotProperty")
        set(value) = setVelocityPivot(value)

    var direction: Vector3
        @JvmName("directionProperty")
        get() = getDirection()
        @JvmName("setDirectionProperty")
        set(value) = setDirection(value)

    var spread: Double
        @JvmName("spreadProperty")
        get() = getSpread()
        @JvmName("setSpreadProperty")
        set(value) = setSpread(value)

    var flatness: Double
        @JvmName("flatnessProperty")
        get() = getFlatness()
        @JvmName("setFlatnessProperty")
        set(value) = setFlatness(value)

    var initialVelocity: Vector2
        @JvmName("initialVelocityProperty")
        get() = getParam(0L)
        @JvmName("setInitialVelocityProperty")
        set(value) = setParam(0L, value)

    var initialVelocityMin: Double
        @JvmName("initialVelocityMinProperty")
        get() = getParamMin(0L)
        @JvmName("setInitialVelocityMinProperty")
        set(value) = setParamMin(0L, value)

    var initialVelocityMax: Double
        @JvmName("initialVelocityMaxProperty")
        get() = getParamMax(0L)
        @JvmName("setInitialVelocityMaxProperty")
        set(value) = setParamMax(0L, value)

    var angularVelocity: Vector2
        @JvmName("angularVelocityProperty")
        get() = getParam(1L)
        @JvmName("setAngularVelocityProperty")
        set(value) = setParam(1L, value)

    var angularVelocityMin: Double
        @JvmName("angularVelocityMinProperty")
        get() = getParamMin(1L)
        @JvmName("setAngularVelocityMinProperty")
        set(value) = setParamMin(1L, value)

    var angularVelocityMax: Double
        @JvmName("angularVelocityMaxProperty")
        get() = getParamMax(1L)
        @JvmName("setAngularVelocityMaxProperty")
        set(value) = setParamMax(1L, value)

    var angularVelocityCurve: Texture2D?
        @JvmName("angularVelocityCurveProperty")
        get() = getParamTexture(1L)
        @JvmName("setAngularVelocityCurveProperty")
        set(value) = setParamTexture(1L, value)

    var directionalVelocity: Vector2
        @JvmName("directionalVelocityProperty")
        get() = getParam(16L)
        @JvmName("setDirectionalVelocityProperty")
        set(value) = setParam(16L, value)

    var directionalVelocityMin: Double
        @JvmName("directionalVelocityMinProperty")
        get() = getParamMin(16L)
        @JvmName("setDirectionalVelocityMinProperty")
        set(value) = setParamMin(16L, value)

    var directionalVelocityMax: Double
        @JvmName("directionalVelocityMaxProperty")
        get() = getParamMax(16L)
        @JvmName("setDirectionalVelocityMaxProperty")
        set(value) = setParamMax(16L, value)

    var directionalVelocityCurve: Texture2D?
        @JvmName("directionalVelocityCurveProperty")
        get() = getParamTexture(16L)
        @JvmName("setDirectionalVelocityCurveProperty")
        set(value) = setParamTexture(16L, value)

    var orbitVelocity: Vector2
        @JvmName("orbitVelocityProperty")
        get() = getParam(2L)
        @JvmName("setOrbitVelocityProperty")
        set(value) = setParam(2L, value)

    var orbitVelocityMin: Double
        @JvmName("orbitVelocityMinProperty")
        get() = getParamMin(2L)
        @JvmName("setOrbitVelocityMinProperty")
        set(value) = setParamMin(2L, value)

    var orbitVelocityMax: Double
        @JvmName("orbitVelocityMaxProperty")
        get() = getParamMax(2L)
        @JvmName("setOrbitVelocityMaxProperty")
        set(value) = setParamMax(2L, value)

    var orbitVelocityCurve: Texture2D?
        @JvmName("orbitVelocityCurveProperty")
        get() = getParamTexture(2L)
        @JvmName("setOrbitVelocityCurveProperty")
        set(value) = setParamTexture(2L, value)

    var radialVelocity: Vector2
        @JvmName("radialVelocityProperty")
        get() = getParam(15L)
        @JvmName("setRadialVelocityProperty")
        set(value) = setParam(15L, value)

    var radialVelocityMin: Double
        @JvmName("radialVelocityMinProperty")
        get() = getParamMin(15L)
        @JvmName("setRadialVelocityMinProperty")
        set(value) = setParamMin(15L, value)

    var radialVelocityMax: Double
        @JvmName("radialVelocityMaxProperty")
        get() = getParamMax(15L)
        @JvmName("setRadialVelocityMaxProperty")
        set(value) = setParamMax(15L, value)

    var radialVelocityCurve: Texture2D?
        @JvmName("radialVelocityCurveProperty")
        get() = getParamTexture(15L)
        @JvmName("setRadialVelocityCurveProperty")
        set(value) = setParamTexture(15L, value)

    var velocityLimitCurve: Texture2D?
        @JvmName("velocityLimitCurveProperty")
        get() = getVelocityLimitCurve()
        @JvmName("setVelocityLimitCurveProperty")
        set(value) = setVelocityLimitCurve(value)

    var useRotationVelocity3d: Boolean
        @JvmName("useRotationVelocity3dProperty")
        get() = isUsingRotationVelocity3d()
        @JvmName("setUseRotationVelocity3dProperty")
        set(value) = setUsingRotationVelocity3d(value)

    var rotationVelocity3dMin: Vector3
        @JvmName("rotationVelocity3dMinProperty")
        get() = getRotationVelocity3dMin()
        @JvmName("setRotationVelocity3dMinProperty")
        set(value) = setRotationVelocity3dMin(value)

    var rotationVelocity3dMax: Vector3
        @JvmName("rotationVelocity3dMaxProperty")
        get() = getRotationVelocity3dMax()
        @JvmName("setRotationVelocity3dMaxProperty")
        set(value) = setRotationVelocity3dMax(value)

    var rotationVelocity3dCurve: Texture2D?
        @JvmName("rotationVelocity3dCurveProperty")
        get() = getRotationVelocity3dCurve()
        @JvmName("setRotationVelocity3dCurveProperty")
        set(value) = setRotationVelocity3dCurve(value)

    var gravity: Vector3
        @JvmName("gravityProperty")
        get() = getGravity()
        @JvmName("setGravityProperty")
        set(value) = setGravity(value)

    var linearAccel: Vector2
        @JvmName("linearAccelProperty")
        get() = getParam(3L)
        @JvmName("setLinearAccelProperty")
        set(value) = setParam(3L, value)

    var linearAccelMin: Double
        @JvmName("linearAccelMinProperty")
        get() = getParamMin(3L)
        @JvmName("setLinearAccelMinProperty")
        set(value) = setParamMin(3L, value)

    var linearAccelMax: Double
        @JvmName("linearAccelMaxProperty")
        get() = getParamMax(3L)
        @JvmName("setLinearAccelMaxProperty")
        set(value) = setParamMax(3L, value)

    var linearAccelCurve: Texture2D?
        @JvmName("linearAccelCurveProperty")
        get() = getParamTexture(3L)
        @JvmName("setLinearAccelCurveProperty")
        set(value) = setParamTexture(3L, value)

    var radialAccel: Vector2
        @JvmName("radialAccelProperty")
        get() = getParam(4L)
        @JvmName("setRadialAccelProperty")
        set(value) = setParam(4L, value)

    var radialAccelMin: Double
        @JvmName("radialAccelMinProperty")
        get() = getParamMin(4L)
        @JvmName("setRadialAccelMinProperty")
        set(value) = setParamMin(4L, value)

    var radialAccelMax: Double
        @JvmName("radialAccelMaxProperty")
        get() = getParamMax(4L)
        @JvmName("setRadialAccelMaxProperty")
        set(value) = setParamMax(4L, value)

    var radialAccelCurve: Texture2D?
        @JvmName("radialAccelCurveProperty")
        get() = getParamTexture(4L)
        @JvmName("setRadialAccelCurveProperty")
        set(value) = setParamTexture(4L, value)

    var tangentialAccel: Vector2
        @JvmName("tangentialAccelProperty")
        get() = getParam(5L)
        @JvmName("setTangentialAccelProperty")
        set(value) = setParam(5L, value)

    var tangentialAccelMin: Double
        @JvmName("tangentialAccelMinProperty")
        get() = getParamMin(5L)
        @JvmName("setTangentialAccelMinProperty")
        set(value) = setParamMin(5L, value)

    var tangentialAccelMax: Double
        @JvmName("tangentialAccelMaxProperty")
        get() = getParamMax(5L)
        @JvmName("setTangentialAccelMaxProperty")
        set(value) = setParamMax(5L, value)

    var tangentialAccelCurve: Texture2D?
        @JvmName("tangentialAccelCurveProperty")
        get() = getParamTexture(5L)
        @JvmName("setTangentialAccelCurveProperty")
        set(value) = setParamTexture(5L, value)

    var damping: Vector2
        @JvmName("dampingProperty")
        get() = getParam(6L)
        @JvmName("setDampingProperty")
        set(value) = setParam(6L, value)

    var dampingMin: Double
        @JvmName("dampingMinProperty")
        get() = getParamMin(6L)
        @JvmName("setDampingMinProperty")
        set(value) = setParamMin(6L, value)

    var dampingMax: Double
        @JvmName("dampingMaxProperty")
        get() = getParamMax(6L)
        @JvmName("setDampingMaxProperty")
        set(value) = setParamMax(6L, value)

    var dampingCurve: Texture2D?
        @JvmName("dampingCurveProperty")
        get() = getParamTexture(6L)
        @JvmName("setDampingCurveProperty")
        set(value) = setParamTexture(6L, value)

    var attractorInteractionEnabled: Boolean
        @JvmName("attractorInteractionEnabledProperty")
        get() = isAttractorInteractionEnabled()
        @JvmName("setAttractorInteractionEnabledProperty")
        set(value) = setAttractorInteractionEnabled(value)

    var useScale3d: Boolean
        @JvmName("useScale3dProperty")
        get() = isUsingScale3d()
        @JvmName("setUseScale3dProperty")
        set(value) = setUseScale3d(value)

    var scale3dMin: Vector3
        @JvmName("scale3dMinProperty")
        get() = getScale3dMin()
        @JvmName("setScale3dMinProperty")
        set(value) = setScale3dMin(value)

    var scale3dMax: Vector3
        @JvmName("scale3dMaxProperty")
        get() = getScale3dMax()
        @JvmName("setScale3dMaxProperty")
        set(value) = setScale3dMax(value)

    var scale: Vector2
        @JvmName("scaleProperty")
        get() = getParam(8L)
        @JvmName("setScaleProperty")
        set(value) = setParam(8L, value)

    var scaleMin: Double
        @JvmName("scaleMinProperty")
        get() = getParamMin(8L)
        @JvmName("setScaleMinProperty")
        set(value) = setParamMin(8L, value)

    var scaleMax: Double
        @JvmName("scaleMaxProperty")
        get() = getParamMax(8L)
        @JvmName("setScaleMaxProperty")
        set(value) = setParamMax(8L, value)

    var scaleCurve: Texture2D?
        @JvmName("scaleCurveProperty")
        get() = getParamTexture(8L)
        @JvmName("setScaleCurveProperty")
        set(value) = setParamTexture(8L, value)

    var scaleOverVelocity: Vector2
        @JvmName("scaleOverVelocityProperty")
        get() = getParam(17L)
        @JvmName("setScaleOverVelocityProperty")
        set(value) = setParam(17L, value)

    var scaleOverVelocityMin: Double
        @JvmName("scaleOverVelocityMinProperty")
        get() = getParamMin(17L)
        @JvmName("setScaleOverVelocityMinProperty")
        set(value) = setParamMin(17L, value)

    var scaleOverVelocityMax: Double
        @JvmName("scaleOverVelocityMaxProperty")
        get() = getParamMax(17L)
        @JvmName("setScaleOverVelocityMaxProperty")
        set(value) = setParamMax(17L, value)

    var scaleOverVelocityCurve: Texture2D?
        @JvmName("scaleOverVelocityCurveProperty")
        get() = getParamTexture(17L)
        @JvmName("setScaleOverVelocityCurveProperty")
        set(value) = setParamTexture(17L, value)

    var color: Color
        @JvmName("colorProperty")
        get() = getColor()
        @JvmName("setColorProperty")
        set(value) = setColor(value)

    var colorRamp: Texture2D?
        @JvmName("colorRampProperty")
        get() = getColorRamp()
        @JvmName("setColorRampProperty")
        set(value) = setColorRamp(value)

    var colorInitialRamp: Texture2D?
        @JvmName("colorInitialRampProperty")
        get() = getColorInitialRamp()
        @JvmName("setColorInitialRampProperty")
        set(value) = setColorInitialRamp(value)

    var alphaCurve: Texture2D?
        @JvmName("alphaCurveProperty")
        get() = getAlphaCurve()
        @JvmName("setAlphaCurveProperty")
        set(value) = setAlphaCurve(value)

    var emissionCurve: Texture2D?
        @JvmName("emissionCurveProperty")
        get() = getEmissionCurve()
        @JvmName("setEmissionCurveProperty")
        set(value) = setEmissionCurve(value)

    var hueVariation: Vector2
        @JvmName("hueVariationProperty")
        get() = getParam(9L)
        @JvmName("setHueVariationProperty")
        set(value) = setParam(9L, value)

    var hueVariationMin: Double
        @JvmName("hueVariationMinProperty")
        get() = getParamMin(9L)
        @JvmName("setHueVariationMinProperty")
        set(value) = setParamMin(9L, value)

    var hueVariationMax: Double
        @JvmName("hueVariationMaxProperty")
        get() = getParamMax(9L)
        @JvmName("setHueVariationMaxProperty")
        set(value) = setParamMax(9L, value)

    var hueVariationCurve: Texture2D?
        @JvmName("hueVariationCurveProperty")
        get() = getParamTexture(9L)
        @JvmName("setHueVariationCurveProperty")
        set(value) = setParamTexture(9L, value)

    var animSpeed: Vector2
        @JvmName("animSpeedProperty")
        get() = getParam(10L)
        @JvmName("setAnimSpeedProperty")
        set(value) = setParam(10L, value)

    var animSpeedMin: Double
        @JvmName("animSpeedMinProperty")
        get() = getParamMin(10L)
        @JvmName("setAnimSpeedMinProperty")
        set(value) = setParamMin(10L, value)

    var animSpeedMax: Double
        @JvmName("animSpeedMaxProperty")
        get() = getParamMax(10L)
        @JvmName("setAnimSpeedMaxProperty")
        set(value) = setParamMax(10L, value)

    var animSpeedCurve: Texture2D?
        @JvmName("animSpeedCurveProperty")
        get() = getParamTexture(10L)
        @JvmName("setAnimSpeedCurveProperty")
        set(value) = setParamTexture(10L, value)

    var animOffset: Vector2
        @JvmName("animOffsetProperty")
        get() = getParam(11L)
        @JvmName("setAnimOffsetProperty")
        set(value) = setParam(11L, value)

    var animOffsetMin: Double
        @JvmName("animOffsetMinProperty")
        get() = getParamMin(11L)
        @JvmName("setAnimOffsetMinProperty")
        set(value) = setParamMin(11L, value)

    var animOffsetMax: Double
        @JvmName("animOffsetMaxProperty")
        get() = getParamMax(11L)
        @JvmName("setAnimOffsetMaxProperty")
        set(value) = setParamMax(11L, value)

    var animOffsetCurve: Texture2D?
        @JvmName("animOffsetCurveProperty")
        get() = getParamTexture(11L)
        @JvmName("setAnimOffsetCurveProperty")
        set(value) = setParamTexture(11L, value)

    var turbulenceEnabled: Boolean
        @JvmName("turbulenceEnabledProperty")
        get() = getTurbulenceEnabled()
        @JvmName("setTurbulenceEnabledProperty")
        set(value) = setTurbulenceEnabled(value)

    var turbulenceNoiseStrength: Double
        @JvmName("turbulenceNoiseStrengthProperty")
        get() = getTurbulenceNoiseStrength()
        @JvmName("setTurbulenceNoiseStrengthProperty")
        set(value) = setTurbulenceNoiseStrength(value)

    var turbulenceNoiseScale: Double
        @JvmName("turbulenceNoiseScaleProperty")
        get() = getTurbulenceNoiseScale()
        @JvmName("setTurbulenceNoiseScaleProperty")
        set(value) = setTurbulenceNoiseScale(value)

    var turbulenceNoiseSpeed: Vector3
        @JvmName("turbulenceNoiseSpeedProperty")
        get() = getTurbulenceNoiseSpeed()
        @JvmName("setTurbulenceNoiseSpeedProperty")
        set(value) = setTurbulenceNoiseSpeed(value)

    var turbulenceNoiseSpeedRandom: Double
        @JvmName("turbulenceNoiseSpeedRandomProperty")
        get() = getTurbulenceNoiseSpeedRandom()
        @JvmName("setTurbulenceNoiseSpeedRandomProperty")
        set(value) = setTurbulenceNoiseSpeedRandom(value)

    var turbulenceInfluence: Vector2
        @JvmName("turbulenceInfluenceProperty")
        get() = getParam(13L)
        @JvmName("setTurbulenceInfluenceProperty")
        set(value) = setParam(13L, value)

    var turbulenceInfluenceMin: Double
        @JvmName("turbulenceInfluenceMinProperty")
        get() = getParamMin(13L)
        @JvmName("setTurbulenceInfluenceMinProperty")
        set(value) = setParamMin(13L, value)

    var turbulenceInfluenceMax: Double
        @JvmName("turbulenceInfluenceMaxProperty")
        get() = getParamMax(13L)
        @JvmName("setTurbulenceInfluenceMaxProperty")
        set(value) = setParamMax(13L, value)

    var turbulenceInitialDisplacement: Vector2
        @JvmName("turbulenceInitialDisplacementProperty")
        get() = getParam(14L)
        @JvmName("setTurbulenceInitialDisplacementProperty")
        set(value) = setParam(14L, value)

    var turbulenceInitialDisplacementMin: Double
        @JvmName("turbulenceInitialDisplacementMinProperty")
        get() = getParamMin(14L)
        @JvmName("setTurbulenceInitialDisplacementMinProperty")
        set(value) = setParamMin(14L, value)

    var turbulenceInitialDisplacementMax: Double
        @JvmName("turbulenceInitialDisplacementMaxProperty")
        get() = getParamMax(14L)
        @JvmName("setTurbulenceInitialDisplacementMaxProperty")
        set(value) = setParamMax(14L, value)

    var turbulenceInfluenceOverLife: Texture2D?
        @JvmName("turbulenceInfluenceOverLifeProperty")
        get() = getParamTexture(12L)
        @JvmName("setTurbulenceInfluenceOverLifeProperty")
        set(value) = setParamTexture(12L, value)

    var collisionMode: Long
        @JvmName("collisionModeProperty")
        get() = getCollisionMode()
        @JvmName("setCollisionModeProperty")
        set(value) = setCollisionMode(value)

    var collisionFriction: Double
        @JvmName("collisionFrictionProperty")
        get() = getCollisionFriction()
        @JvmName("setCollisionFrictionProperty")
        set(value) = setCollisionFriction(value)

    var collisionBounce: Double
        @JvmName("collisionBounceProperty")
        get() = getCollisionBounce()
        @JvmName("setCollisionBounceProperty")
        set(value) = setCollisionBounce(value)

    var collisionUseScale: Boolean
        @JvmName("collisionUseScaleProperty")
        get() = isCollisionUsingScale()
        @JvmName("setCollisionUseScaleProperty")
        set(value) = setCollisionUseScale(value)

    var subEmitterMode: Long
        @JvmName("subEmitterModeProperty")
        get() = getSubEmitterMode()
        @JvmName("setSubEmitterModeProperty")
        set(value) = setSubEmitterMode(value)

    var subEmitterFrequency: Double
        @JvmName("subEmitterFrequencyProperty")
        get() = getSubEmitterFrequency()
        @JvmName("setSubEmitterFrequencyProperty")
        set(value) = setSubEmitterFrequency(value)

    var subEmitterAmountAtEnd: Int
        @JvmName("subEmitterAmountAtEndProperty")
        get() = getSubEmitterAmountAtEnd()
        @JvmName("setSubEmitterAmountAtEndProperty")
        set(value) = setSubEmitterAmountAtEnd(value)

    var subEmitterAmountAtCollision: Int
        @JvmName("subEmitterAmountAtCollisionProperty")
        get() = getSubEmitterAmountAtCollision()
        @JvmName("setSubEmitterAmountAtCollisionProperty")
        set(value) = setSubEmitterAmountAtCollision(value)

    var subEmitterAmountAtStart: Int
        @JvmName("subEmitterAmountAtStartProperty")
        get() = getSubEmitterAmountAtStart()
        @JvmName("setSubEmitterAmountAtStartProperty")
        set(value) = setSubEmitterAmountAtStart(value)

    var subEmitterKeepVelocity: Boolean
        @JvmName("subEmitterKeepVelocityProperty")
        get() = getSubEmitterKeepVelocity()
        @JvmName("setSubEmitterKeepVelocityProperty")
        set(value) = setSubEmitterKeepVelocity(value)

    /**
     * Unit vector specifying the particles' emission direction.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_direction
     */
    fun setDirection(degrees: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setDirectionBind, handle, degrees)
    }

    /**
     * Unit vector specifying the particles' emission direction.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_direction
     */
    fun getDirection(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getDirectionBind, handle)
    }

    /**
     * Percentage of the velocity of the respective `GPUParticles2D` or `GPUParticles3D` inherited by
     * each particle when spawning.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_inherit_velocity_ratio
     */
    fun setInheritVelocityRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setInheritVelocityRatioBind, handle, ratio)
    }

    /**
     * Percentage of the velocity of the respective `GPUParticles2D` or `GPUParticles3D` inherited by
     * each particle when spawning.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_inherit_velocity_ratio
     */
    fun getInheritVelocityRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getInheritVelocityRatioBind, handle)
    }

    /**
     * Each particle's initial direction range from `+spread` to `-spread` degrees.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_spread
     */
    fun setSpread(degrees: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSpreadBind, handle, degrees)
    }

    /**
     * Each particle's initial direction range from `+spread` to `-spread` degrees.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_spread
     */
    fun getSpread(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSpreadBind, handle)
    }

    /**
     * Amount of `spread` along the Y axis.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_flatness
     */
    fun setFlatness(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFlatnessBind, handle, amount)
    }

    /**
     * Amount of `spread` along the Y axis.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_flatness
     */
    fun getFlatness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFlatnessBind, handle)
    }

    /**
     * Sets the minimum and maximum values of the given `param`. The `x` component of the argument
     * vector corresponds to minimum and the `y` component corresponds to maximum.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_param
     */
    fun setParam(param: Long, value: Vector2) {
        ObjectCalls.ptrcallWithLongAndVector2Arg(setParamBind, handle, param, value)
    }

    /**
     * Returns the minimum and maximum values of the given `param` as a vector. The `x` component of
     * the returned vector corresponds to minimum and the `y` component corresponds to maximum.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_param
     */
    fun getParam(param: Long): Vector2 {
        return ObjectCalls.ptrcallWithLongArgRetVector2(getParamBind, handle, param)
    }

    /**
     * Minimum displacement of each particle's spawn position by the turbulence. The actual amount of
     * displacement will be a factor of the underlying turbulence multiplied by a random value between
     * `turbulence_initial_displacement_min` and `turbulence_initial_displacement_max`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_param_min
     */
    fun setParamMin(param: Long, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamMinBind, handle, param, value)
    }

    /**
     * Minimum displacement of each particle's spawn position by the turbulence. The actual amount of
     * displacement will be a factor of the underlying turbulence multiplied by a random value between
     * `turbulence_initial_displacement_min` and `turbulence_initial_displacement_max`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_param_min
     */
    fun getParamMin(param: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamMinBind, handle, param)
    }

    /**
     * Maximum displacement of each particle's spawn position by the turbulence. The actual amount of
     * displacement will be a factor of the underlying turbulence multiplied by a random value between
     * `turbulence_initial_displacement_min` and `turbulence_initial_displacement_max`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_param_max
     */
    fun setParamMax(param: Long, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamMaxBind, handle, param, value)
    }

    /**
     * Maximum displacement of each particle's spawn position by the turbulence. The actual amount of
     * displacement will be a factor of the underlying turbulence multiplied by a random value between
     * `turbulence_initial_displacement_min` and `turbulence_initial_displacement_max`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_param_max
     */
    fun getParamMax(param: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamMaxBind, handle, param)
    }

    /**
     * Each particle's amount of turbulence will be influenced along this `CurveTexture` over its life
     * time.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_param_texture
     */
    fun setParamTexture(param: Long, texture: Texture2D?) {
        ObjectCalls.ptrcallWithLongAndObjectArg(setParamTextureBind, handle, param, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Each particle's amount of turbulence will be influenced along this `CurveTexture` over its life
     * time.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_param_texture
     */
    fun getParamTexture(param: Long): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithLongArgRetObject(getParamTextureBind, handle, param))
    }

    /**
     * Each particle's initial color. If the `GPUParticles2D`'s `texture` is defined, it will be
     * multiplied by this color. Note: `color` multiplies the particle mesh's vertex colors. To have a
     * visible effect on a `BaseMaterial3D`, `BaseMaterial3D.vertex_color_use_as_albedo` must be
     * `true`. For a `ShaderMaterial`, `ALBEDO *= COLOR.rgb;` must be inserted in the shader's
     * `fragment()` function. Otherwise, `color` will have no visible effect.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_color
     */
    fun setColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setColorBind, handle, color)
    }

    /**
     * Each particle's initial color. If the `GPUParticles2D`'s `texture` is defined, it will be
     * multiplied by this color. Note: `color` multiplies the particle mesh's vertex colors. To have a
     * visible effect on a `BaseMaterial3D`, `BaseMaterial3D.vertex_color_use_as_albedo` must be
     * `true`. For a `ShaderMaterial`, `ALBEDO *= COLOR.rgb;` must be inserted in the shader's
     * `fragment()` function. Otherwise, `color` will have no visible effect.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_color
     */
    fun getColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getColorBind, handle)
    }

    /**
     * Enable the usage of `scale_3d_min` and `scale_3d_max`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_use_scale_3d
     */
    fun setUseScale3d(usingScale3d: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseScale3dBind, handle, usingScale3d)
    }

    /**
     * Enable the usage of `scale_3d_min` and `scale_3d_max`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.is_using_scale_3d
     */
    fun isUsingScale3d(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingScale3dBind, handle)
    }

    /**
     * The minimum value of the random scale vector for each particle. Works only if `use_scale_3d` is
     * enabled.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_scale_3d_min
     */
    fun setScale3dMin(scale3dMin: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setScale3dMinBind, handle, scale3dMin)
    }

    /**
     * The minimum value of the random scale vector for each particle. Works only if `use_scale_3d` is
     * enabled.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_scale_3d_min
     */
    fun getScale3dMin(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getScale3dMinBind, handle)
    }

    /**
     * The maximum value of the random scale vector for each particle. Works only if `use_scale_3d` is
     * enabled.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_scale_3d_max
     */
    fun setScale3dMax(scale3dMax: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setScale3dMaxBind, handle, scale3dMax)
    }

    /**
     * The maximum value of the random scale vector for each particle. Works only if `use_scale_3d` is
     * enabled.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_scale_3d_max
     */
    fun getScale3dMax(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getScale3dMaxBind, handle)
    }

    /**
     * Enable the usage of `rotation_3d_min` and `rotation_3d_max`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_use_rotation_3d
     */
    fun setUseRotation3d(usingRotation3d: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseRotation3dBind, handle, usingRotation3d)
    }

    /**
     * Enable the usage of `rotation_3d_min` and `rotation_3d_max`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.is_using_rotation_3d
     */
    fun isUsingRotation3d(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingRotation3dBind, handle)
    }

    /**
     * The minimum 3D orientation, in degrees. Works only in 3D and if `use_rotation_3d` is enabled.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_rotation_3d_min
     */
    fun setRotation3dMin(rotation3dMin: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setRotation3dMinBind, handle, rotation3dMin)
    }

    /**
     * The minimum 3D orientation, in degrees. Works only in 3D and if `use_rotation_3d` is enabled.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_rotation_3d_min
     */
    fun getRotation3dMin(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getRotation3dMinBind, handle)
    }

    /**
     * The maximum 3D orientation, in degrees. Works only in 3D and if `use_rotation_3d` is enabled.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_rotation_3d_max
     */
    fun setRotation3dMax(rotation3dMax: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setRotation3dMaxBind, handle, rotation3dMax)
    }

    /**
     * The maximum 3D orientation, in degrees. Works only in 3D and if `use_rotation_3d` is enabled.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_rotation_3d_max
     */
    fun getRotation3dMax(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getRotation3dMaxBind, handle)
    }

    /**
     * Each particle's color will vary along this `GradientTexture1D` over its lifetime (multiplied
     * with `color`). Note: `color_ramp` multiplies the particle mesh's vertex colors. To have a
     * visible effect on a `BaseMaterial3D`, `BaseMaterial3D.vertex_color_use_as_albedo` must be
     * `true`. For a `ShaderMaterial`, `ALBEDO *= COLOR.rgb;` must be inserted in the shader's
     * `fragment()` function. Otherwise, `color_ramp` will have no visible effect.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_color_ramp
     */
    fun setColorRamp(ramp: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setColorRampBind, handle, listOf(ramp?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Each particle's color will vary along this `GradientTexture1D` over its lifetime (multiplied
     * with `color`). Note: `color_ramp` multiplies the particle mesh's vertex colors. To have a
     * visible effect on a `BaseMaterial3D`, `BaseMaterial3D.vertex_color_use_as_albedo` must be
     * `true`. For a `ShaderMaterial`, `ALBEDO *= COLOR.rgb;` must be inserted in the shader's
     * `fragment()` function. Otherwise, `color_ramp` will have no visible effect.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_color_ramp
     */
    fun getColorRamp(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getColorRampBind, handle))
    }

    /**
     * The alpha value of each particle's color will be multiplied by this `CurveTexture` over its
     * lifetime. Note: `alpha_curve` multiplies the particle mesh's vertex colors. To have a visible
     * effect on a `BaseMaterial3D`, `BaseMaterial3D.vertex_color_use_as_albedo` must be `true`. For a
     * `ShaderMaterial`, `ALPHA *= COLOR.a;` must be inserted in the shader's `fragment()` function.
     * Otherwise, `alpha_curve` will have no visible effect.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_alpha_curve
     */
    fun setAlphaCurve(curve: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setAlphaCurveBind, handle, listOf(curve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The alpha value of each particle's color will be multiplied by this `CurveTexture` over its
     * lifetime. Note: `alpha_curve` multiplies the particle mesh's vertex colors. To have a visible
     * effect on a `BaseMaterial3D`, `BaseMaterial3D.vertex_color_use_as_albedo` must be `true`. For a
     * `ShaderMaterial`, `ALPHA *= COLOR.a;` must be inserted in the shader's `fragment()` function.
     * Otherwise, `alpha_curve` will have no visible effect.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_alpha_curve
     */
    fun getAlphaCurve(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getAlphaCurveBind, handle))
    }

    /**
     * Each particle's color will be multiplied by this `CurveTexture` over its lifetime. Note:
     * `emission_curve` multiplies the particle mesh's vertex colors. To have a visible effect on a
     * `BaseMaterial3D`, `BaseMaterial3D.vertex_color_use_as_albedo` must be `true`. For a
     * `ShaderMaterial`, `ALBEDO *= COLOR.rgb;` must be inserted in the shader's `fragment()` function.
     * Otherwise, `emission_curve` will have no visible effect.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_curve
     */
    fun setEmissionCurve(curve: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setEmissionCurveBind, handle, listOf(curve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Each particle's color will be multiplied by this `CurveTexture` over its lifetime. Note:
     * `emission_curve` multiplies the particle mesh's vertex colors. To have a visible effect on a
     * `BaseMaterial3D`, `BaseMaterial3D.vertex_color_use_as_albedo` must be `true`. For a
     * `ShaderMaterial`, `ALBEDO *= COLOR.rgb;` must be inserted in the shader's `fragment()` function.
     * Otherwise, `emission_curve` will have no visible effect.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_curve
     */
    fun getEmissionCurve(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEmissionCurveBind, handle))
    }

    /**
     * Each particle's initial color will vary along this `GradientTexture1D` (multiplied with
     * `color`). Note: `color_initial_ramp` multiplies the particle mesh's vertex colors. To have a
     * visible effect on a `BaseMaterial3D`, `BaseMaterial3D.vertex_color_use_as_albedo` must be
     * `true`. For a `ShaderMaterial`, `ALBEDO *= COLOR.rgb;` must be inserted in the shader's
     * `fragment()` function. Otherwise, `color_initial_ramp` will have no visible effect.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_color_initial_ramp
     */
    fun setColorInitialRamp(ramp: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setColorInitialRampBind, handle, listOf(ramp?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Each particle's initial color will vary along this `GradientTexture1D` (multiplied with
     * `color`). Note: `color_initial_ramp` multiplies the particle mesh's vertex colors. To have a
     * visible effect on a `BaseMaterial3D`, `BaseMaterial3D.vertex_color_use_as_albedo` must be
     * `true`. For a `ShaderMaterial`, `ALBEDO *= COLOR.rgb;` must be inserted in the shader's
     * `fragment()` function. Otherwise, `color_initial_ramp` will have no visible effect.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_color_initial_ramp
     */
    fun getColorInitialRamp(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getColorInitialRampBind, handle))
    }

    /**
     * A `CurveTexture` that defines the maximum velocity of a particle during its lifetime.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_velocity_limit_curve
     */
    fun setVelocityLimitCurve(curve: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setVelocityLimitCurveBind, handle, listOf(curve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * A `CurveTexture` that defines the maximum velocity of a particle during its lifetime.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_velocity_limit_curve
     */
    fun getVelocityLimitCurve(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getVelocityLimitCurveBind, handle))
    }

    /**
     * If `true`, particles rotate around Y axis by `angle_min`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_particle_flag
     */
    fun setParticleFlag(particleFlag: Long, enable: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setParticleFlagBind, handle, particleFlag, enable)
    }

    /**
     * If `true`, particles rotate around Y axis by `angle_min`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_particle_flag
     */
    fun getParticleFlag(particleFlag: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(getParticleFlagBind, handle, particleFlag)
    }

    /**
     * A pivot point used to calculate radial and orbital velocity of particles.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_velocity_pivot
     */
    fun setVelocityPivot(pivot: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setVelocityPivotBind, handle, pivot)
    }

    /**
     * A pivot point used to calculate radial and orbital velocity of particles.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_velocity_pivot
     */
    fun getVelocityPivot(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getVelocityPivotBind, handle)
    }

    /**
     * Particles will be emitted inside this region.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_shape
     */
    fun setEmissionShape(shape: Long) {
        ObjectCalls.ptrcallWithLongArg(setEmissionShapeBind, handle, shape)
    }

    /**
     * Particles will be emitted inside this region.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_shape
     */
    fun getEmissionShape(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getEmissionShapeBind, handle)
    }

    /**
     * The sphere's radius if `emission_shape` is set to `EMISSION_SHAPE_SPHERE`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_sphere_radius
     */
    fun setEmissionSphereRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionSphereRadiusBind, handle, radius)
    }

    /**
     * The sphere's radius if `emission_shape` is set to `EMISSION_SHAPE_SPHERE`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_sphere_radius
     */
    fun getEmissionSphereRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionSphereRadiusBind, handle)
    }

    /**
     * The box's extents if `emission_shape` is set to `EMISSION_SHAPE_BOX`. Note:
     * `emission_box_extents` starts from the center point and applies the X, Y, and Z values in both
     * directions. The size is twice the area of the extents.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_box_extents
     */
    fun setEmissionBoxExtents(extents: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setEmissionBoxExtentsBind, handle, extents)
    }

    /**
     * The box's extents if `emission_shape` is set to `EMISSION_SHAPE_BOX`. Note:
     * `emission_box_extents` starts from the center point and applies the X, Y, and Z values in both
     * directions. The size is twice the area of the extents.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_box_extents
     */
    fun getEmissionBoxExtents(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getEmissionBoxExtentsBind, handle)
    }

    /**
     * Particles will be emitted at positions determined by sampling this texture at a random position.
     * Used with `EMISSION_SHAPE_POINTS` and `EMISSION_SHAPE_DIRECTED_POINTS`. Can be created
     * automatically from mesh or node by selecting "Create Emission Points from Mesh/Node" under the
     * "Particles" tool in the toolbar.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_point_texture
     */
    fun setEmissionPointTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setEmissionPointTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Particles will be emitted at positions determined by sampling this texture at a random position.
     * Used with `EMISSION_SHAPE_POINTS` and `EMISSION_SHAPE_DIRECTED_POINTS`. Can be created
     * automatically from mesh or node by selecting "Create Emission Points from Mesh/Node" under the
     * "Particles" tool in the toolbar.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_point_texture
     */
    fun getEmissionPointTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEmissionPointTextureBind, handle))
    }

    /**
     * Particle velocity and rotation will be set by sampling this texture at the same point as the
     * `emission_point_texture`. Used only in `EMISSION_SHAPE_DIRECTED_POINTS`. Can be created
     * automatically from mesh or node by selecting "Create Emission Points from Mesh/Node" under the
     * "Particles" tool in the toolbar.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_normal_texture
     */
    fun setEmissionNormalTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setEmissionNormalTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Particle velocity and rotation will be set by sampling this texture at the same point as the
     * `emission_point_texture`. Used only in `EMISSION_SHAPE_DIRECTED_POINTS`. Can be created
     * automatically from mesh or node by selecting "Create Emission Points from Mesh/Node" under the
     * "Particles" tool in the toolbar.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_normal_texture
     */
    fun getEmissionNormalTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEmissionNormalTextureBind, handle))
    }

    /**
     * Particle color will be modulated by color determined by sampling this texture at the same point
     * as the `emission_point_texture`. Note: `emission_color_texture` multiplies the particle mesh's
     * vertex colors. To have a visible effect on a `BaseMaterial3D`,
     * `BaseMaterial3D.vertex_color_use_as_albedo` must be `true`. For a `ShaderMaterial`, `ALBEDO *=
     * COLOR.rgb;` must be inserted in the shader's `fragment()` function. Otherwise,
     * `emission_color_texture` will have no visible effect.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_color_texture
     */
    fun setEmissionColorTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setEmissionColorTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Particle color will be modulated by color determined by sampling this texture at the same point
     * as the `emission_point_texture`. Note: `emission_color_texture` multiplies the particle mesh's
     * vertex colors. To have a visible effect on a `BaseMaterial3D`,
     * `BaseMaterial3D.vertex_color_use_as_albedo` must be `true`. For a `ShaderMaterial`, `ALBEDO *=
     * COLOR.rgb;` must be inserted in the shader's `fragment()` function. Otherwise,
     * `emission_color_texture` will have no visible effect.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_color_texture
     */
    fun getEmissionColorTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEmissionColorTextureBind, handle))
    }

    /**
     * The number of emission points if `emission_shape` is set to `EMISSION_SHAPE_POINTS` or
     * `EMISSION_SHAPE_DIRECTED_POINTS`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_point_count
     */
    fun setEmissionPointCount(pointCount: Int) {
        ObjectCalls.ptrcallWithIntArg(setEmissionPointCountBind, handle, pointCount)
    }

    /**
     * The number of emission points if `emission_shape` is set to `EMISSION_SHAPE_POINTS` or
     * `EMISSION_SHAPE_DIRECTED_POINTS`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_point_count
     */
    fun getEmissionPointCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getEmissionPointCountBind, handle)
    }

    /**
     * The axis of the ring when using the emitter `EMISSION_SHAPE_RING`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_ring_axis
     */
    fun setEmissionRingAxis(axis: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setEmissionRingAxisBind, handle, axis)
    }

    /**
     * The axis of the ring when using the emitter `EMISSION_SHAPE_RING`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_ring_axis
     */
    fun getEmissionRingAxis(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getEmissionRingAxisBind, handle)
    }

    /**
     * The height of the ring when using the emitter `EMISSION_SHAPE_RING`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_ring_height
     */
    fun setEmissionRingHeight(height: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionRingHeightBind, handle, height)
    }

    /**
     * The height of the ring when using the emitter `EMISSION_SHAPE_RING`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_ring_height
     */
    fun getEmissionRingHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionRingHeightBind, handle)
    }

    /**
     * The radius of the ring when using the emitter `EMISSION_SHAPE_RING`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_ring_radius
     */
    fun setEmissionRingRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionRingRadiusBind, handle, radius)
    }

    /**
     * The radius of the ring when using the emitter `EMISSION_SHAPE_RING`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_ring_radius
     */
    fun getEmissionRingRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionRingRadiusBind, handle)
    }

    /**
     * The inner radius of the ring when using the emitter `EMISSION_SHAPE_RING`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_ring_inner_radius
     */
    fun setEmissionRingInnerRadius(innerRadius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionRingInnerRadiusBind, handle, innerRadius)
    }

    /**
     * The inner radius of the ring when using the emitter `EMISSION_SHAPE_RING`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_ring_inner_radius
     */
    fun getEmissionRingInnerRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionRingInnerRadiusBind, handle)
    }

    /**
     * The angle of the cone when using the emitter `EMISSION_SHAPE_RING`. The default angle of 90
     * degrees results in a ring, while an angle of 0 degrees results in a cone. Intermediate values
     * will result in a ring where one end is larger than the other. Note: Depending on
     * `emission_ring_height`, the angle may be clamped if the ring's end is reached to form a perfect
     * cone.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_ring_cone_angle
     */
    fun setEmissionRingConeAngle(coneAngle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionRingConeAngleBind, handle, coneAngle)
    }

    /**
     * The angle of the cone when using the emitter `EMISSION_SHAPE_RING`. The default angle of 90
     * degrees results in a ring, while an angle of 0 degrees results in a cone. Intermediate values
     * will result in a ring where one end is larger than the other. Note: Depending on
     * `emission_ring_height`, the angle may be clamped if the ring's end is reached to form a perfect
     * cone.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_ring_cone_angle
     */
    fun getEmissionRingConeAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionRingConeAngleBind, handle)
    }

    /**
     * The offset for the `emission_shape`, in local space.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_shape_offset
     */
    fun setEmissionShapeOffset(emissionShapeOffset: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setEmissionShapeOffsetBind, handle, emissionShapeOffset)
    }

    /**
     * The offset for the `emission_shape`, in local space.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_shape_offset
     */
    fun getEmissionShapeOffset(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getEmissionShapeOffsetBind, handle)
    }

    /**
     * The scale of the `emission_shape`, in local space.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_shape_scale
     */
    fun setEmissionShapeScale(emissionShapeScale: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setEmissionShapeScaleBind, handle, emissionShapeScale)
    }

    /**
     * The scale of the `emission_shape`, in local space.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_shape_scale
     */
    fun getEmissionShapeScale(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getEmissionShapeScaleBind, handle)
    }

    /**
     * If `true`, enables turbulence for the particle system. Turbulence can be used to vary particle
     * movement according to its position (based on a 3D noise pattern). In 3D,
     * `GPUParticlesAttractorVectorField3D` with `NoiseTexture3D` can be used as an alternative to
     * turbulence that works in world space and with multiple particle systems reacting in the same
     * way. Note: Enabling turbulence has a high performance cost on the GPU. Only enable turbulence on
     * a few particle systems at once at most, and consider disabling it when targeting mobile/web
     * platforms.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_turbulence_enabled
     */
    fun getTurbulenceEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getTurbulenceEnabledBind, handle)
    }

    /**
     * If `true`, enables turbulence for the particle system. Turbulence can be used to vary particle
     * movement according to its position (based on a 3D noise pattern). In 3D,
     * `GPUParticlesAttractorVectorField3D` with `NoiseTexture3D` can be used as an alternative to
     * turbulence that works in world space and with multiple particle systems reacting in the same
     * way. Note: Enabling turbulence has a high performance cost on the GPU. Only enable turbulence on
     * a few particle systems at once at most, and consider disabling it when targeting mobile/web
     * platforms.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_turbulence_enabled
     */
    fun setTurbulenceEnabled(turbulenceEnabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setTurbulenceEnabledBind, handle, turbulenceEnabled)
    }

    /**
     * The turbulence noise strength. Increasing this will result in a stronger, more contrasting, flow
     * pattern.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_turbulence_noise_strength
     */
    fun getTurbulenceNoiseStrength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTurbulenceNoiseStrengthBind, handle)
    }

    /**
     * The turbulence noise strength. Increasing this will result in a stronger, more contrasting, flow
     * pattern.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_turbulence_noise_strength
     */
    fun setTurbulenceNoiseStrength(turbulenceNoiseStrength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTurbulenceNoiseStrengthBind, handle, turbulenceNoiseStrength)
    }

    /**
     * This value controls the overall scale/frequency of the turbulence noise pattern. A small scale
     * will result in smaller features with more detail while a high scale will result in smoother
     * noise with larger features.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_turbulence_noise_scale
     */
    fun getTurbulenceNoiseScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTurbulenceNoiseScaleBind, handle)
    }

    /**
     * This value controls the overall scale/frequency of the turbulence noise pattern. A small scale
     * will result in smaller features with more detail while a high scale will result in smoother
     * noise with larger features.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_turbulence_noise_scale
     */
    fun setTurbulenceNoiseScale(turbulenceNoiseScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTurbulenceNoiseScaleBind, handle, turbulenceNoiseScale)
    }

    /**
     * The in-place rate of change of the turbulence field. This defines how quickly the noise pattern
     * varies over time. A value of 0.0 will result in a fixed pattern.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_turbulence_noise_speed_random
     */
    fun getTurbulenceNoiseSpeedRandom(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTurbulenceNoiseSpeedRandomBind, handle)
    }

    /**
     * The in-place rate of change of the turbulence field. This defines how quickly the noise pattern
     * varies over time. A value of 0.0 will result in a fixed pattern.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_turbulence_noise_speed_random
     */
    fun setTurbulenceNoiseSpeedRandom(turbulenceNoiseSpeedRandom: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTurbulenceNoiseSpeedRandomBind, handle, turbulenceNoiseSpeedRandom)
    }

    /**
     * A scrolling velocity for the turbulence field. This sets a directional trend for the pattern to
     * move in over time. The default value of `Vector3(0, 0, 0)` turns off the scrolling.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_turbulence_noise_speed
     */
    fun getTurbulenceNoiseSpeed(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getTurbulenceNoiseSpeedBind, handle)
    }

    /**
     * A scrolling velocity for the turbulence field. This sets a directional trend for the pattern to
     * move in over time. The default value of `Vector3(0, 0, 0)` turns off the scrolling.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_turbulence_noise_speed
     */
    fun setTurbulenceNoiseSpeed(turbulenceNoiseSpeed: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setTurbulenceNoiseSpeedBind, handle, turbulenceNoiseSpeed)
    }

    /**
     * Gravity applied to every particle.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_gravity
     */
    fun getGravity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getGravityBind, handle)
    }

    /**
     * Gravity applied to every particle.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_gravity
     */
    fun setGravity(accelVec: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setGravityBind, handle, accelVec)
    }

    /**
     * Particle lifetime randomness ratio. The equation for the lifetime of a particle is `lifetime *
     * (1.0 - randf() * lifetime_randomness)`. For example, a `lifetime_randomness` of `0.4` scales the
     * lifetime between `0.6` to `1.0` of its original value.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_lifetime_randomness
     */
    fun setLifetimeRandomness(randomness: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLifetimeRandomnessBind, handle, randomness)
    }

    /**
     * Particle lifetime randomness ratio. The equation for the lifetime of a particle is `lifetime *
     * (1.0 - randf() * lifetime_randomness)`. For example, a `lifetime_randomness` of `0.4` scales the
     * lifetime between `0.6` to `1.0` of its original value.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_lifetime_randomness
     */
    fun getLifetimeRandomness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLifetimeRandomnessBind, handle)
    }

    /**
     * The particle subemitter mode (see `GPUParticles2D.sub_emitter` and
     * `GPUParticles3D.sub_emitter`).
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_sub_emitter_mode
     */
    fun getSubEmitterMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSubEmitterModeBind, handle)
    }

    /**
     * The particle subemitter mode (see `GPUParticles2D.sub_emitter` and
     * `GPUParticles3D.sub_emitter`).
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_sub_emitter_mode
     */
    fun setSubEmitterMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setSubEmitterModeBind, handle, mode)
    }

    /**
     * The frequency at which particles should be emitted from the subemitter node. One particle will
     * be spawned every `sub_emitter_frequency` seconds. Note: This value shouldn't exceed
     * `GPUParticles2D.amount` or `GPUParticles3D.amount` defined on the subemitter node (not the main
     * node), relative to the subemitter's particle lifetime. If the number of particles is exceeded,
     * no new particles will spawn from the subemitter until enough particles have expired.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_sub_emitter_frequency
     */
    fun getSubEmitterFrequency(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSubEmitterFrequencyBind, handle)
    }

    /**
     * The frequency at which particles should be emitted from the subemitter node. One particle will
     * be spawned every `sub_emitter_frequency` seconds. Note: This value shouldn't exceed
     * `GPUParticles2D.amount` or `GPUParticles3D.amount` defined on the subemitter node (not the main
     * node), relative to the subemitter's particle lifetime. If the number of particles is exceeded,
     * no new particles will spawn from the subemitter until enough particles have expired.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_sub_emitter_frequency
     */
    fun setSubEmitterFrequency(hz: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSubEmitterFrequencyBind, handle, hz)
    }

    /**
     * The amount of particles to spawn from the subemitter node when the particle expires. Note: This
     * value shouldn't exceed `GPUParticles2D.amount` or `GPUParticles3D.amount` defined on the
     * subemitter node (not the main node), relative to the subemitter's particle lifetime. If the
     * number of particles is exceeded, no new particles will spawn from the subemitter until enough
     * particles have expired.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_sub_emitter_amount_at_end
     */
    fun getSubEmitterAmountAtEnd(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSubEmitterAmountAtEndBind, handle)
    }

    /**
     * The amount of particles to spawn from the subemitter node when the particle expires. Note: This
     * value shouldn't exceed `GPUParticles2D.amount` or `GPUParticles3D.amount` defined on the
     * subemitter node (not the main node), relative to the subemitter's particle lifetime. If the
     * number of particles is exceeded, no new particles will spawn from the subemitter until enough
     * particles have expired.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_sub_emitter_amount_at_end
     */
    fun setSubEmitterAmountAtEnd(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setSubEmitterAmountAtEndBind, handle, amount)
    }

    /**
     * The amount of particles to spawn from the subemitter node when a collision occurs. When combined
     * with `COLLISION_HIDE_ON_CONTACT` on the main particles material, this can be used to achieve
     * effects such as raindrops hitting the ground. Note: This value shouldn't exceed
     * `GPUParticles2D.amount` or `GPUParticles3D.amount` defined on the subemitter node (not the main
     * node), relative to the subemitter's particle lifetime. If the number of particles is exceeded,
     * no new particles will spawn from the subemitter until enough particles have expired.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_sub_emitter_amount_at_collision
     */
    fun getSubEmitterAmountAtCollision(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSubEmitterAmountAtCollisionBind, handle)
    }

    /**
     * The amount of particles to spawn from the subemitter node when a collision occurs. When combined
     * with `COLLISION_HIDE_ON_CONTACT` on the main particles material, this can be used to achieve
     * effects such as raindrops hitting the ground. Note: This value shouldn't exceed
     * `GPUParticles2D.amount` or `GPUParticles3D.amount` defined on the subemitter node (not the main
     * node), relative to the subemitter's particle lifetime. If the number of particles is exceeded,
     * no new particles will spawn from the subemitter until enough particles have expired.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_sub_emitter_amount_at_collision
     */
    fun setSubEmitterAmountAtCollision(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setSubEmitterAmountAtCollisionBind, handle, amount)
    }

    /**
     * The amount of particles to spawn from the subemitter node when the particle spawns. Note: This
     * value shouldn't exceed `GPUParticles2D.amount` or `GPUParticles3D.amount` defined on the
     * subemitter node (not the main node), relative to the subemitter's particle lifetime. If the
     * number of particles is exceeded, no new particles will spawn from the subemitter until enough
     * particles have expired.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_sub_emitter_amount_at_start
     */
    fun getSubEmitterAmountAtStart(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSubEmitterAmountAtStartBind, handle)
    }

    /**
     * The amount of particles to spawn from the subemitter node when the particle spawns. Note: This
     * value shouldn't exceed `GPUParticles2D.amount` or `GPUParticles3D.amount` defined on the
     * subemitter node (not the main node), relative to the subemitter's particle lifetime. If the
     * number of particles is exceeded, no new particles will spawn from the subemitter until enough
     * particles have expired.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_sub_emitter_amount_at_start
     */
    fun setSubEmitterAmountAtStart(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setSubEmitterAmountAtStartBind, handle, amount)
    }

    /**
     * If `true`, the subemitter inherits the parent particle's velocity when it spawns.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_sub_emitter_keep_velocity
     */
    fun getSubEmitterKeepVelocity(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getSubEmitterKeepVelocityBind, handle)
    }

    /**
     * If `true`, the subemitter inherits the parent particle's velocity when it spawns.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_sub_emitter_keep_velocity
     */
    fun setSubEmitterKeepVelocity(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSubEmitterKeepVelocityBind, handle, enable)
    }

    /**
     * If `true`, interaction with particle attractors is enabled. In 3D, attraction only occurs within
     * the area defined by the `GPUParticles3D` node's `GPUParticles3D.visibility_aabb`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_attractor_interaction_enabled
     */
    fun setAttractorInteractionEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAttractorInteractionEnabledBind, handle, enabled)
    }

    /**
     * If `true`, interaction with particle attractors is enabled. In 3D, attraction only occurs within
     * the area defined by the `GPUParticles3D` node's `GPUParticles3D.visibility_aabb`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.is_attractor_interaction_enabled
     */
    fun isAttractorInteractionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAttractorInteractionEnabledBind, handle)
    }

    /**
     * The particles' collision mode. Note: 3D Particles can only collide with
     * `GPUParticlesCollision3D` nodes, not `PhysicsBody3D` nodes. To make particles collide with
     * various objects, you can add `GPUParticlesCollision3D` nodes as children of `PhysicsBody3D`
     * nodes. In 3D, collisions only occur within the area defined by the `GPUParticles3D` node's
     * `GPUParticles3D.visibility_aabb`. Note: 2D Particles can only collide with `LightOccluder2D`
     * nodes, not `PhysicsBody2D` nodes.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_collision_mode
     */
    fun setCollisionMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setCollisionModeBind, handle, mode)
    }

    /**
     * The particles' collision mode. Note: 3D Particles can only collide with
     * `GPUParticlesCollision3D` nodes, not `PhysicsBody3D` nodes. To make particles collide with
     * various objects, you can add `GPUParticlesCollision3D` nodes as children of `PhysicsBody3D`
     * nodes. In 3D, collisions only occur within the area defined by the `GPUParticles3D` node's
     * `GPUParticles3D.visibility_aabb`. Note: 2D Particles can only collide with `LightOccluder2D`
     * nodes, not `PhysicsBody2D` nodes.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_collision_mode
     */
    fun getCollisionMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCollisionModeBind, handle)
    }

    /**
     * If `true`, `GPUParticles3D.collision_base_size` is multiplied by the particle's effective scale
     * (see `scale_min`, `scale_max`, `scale_curve`, and `scale_over_velocity_curve`).
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_collision_use_scale
     */
    fun setCollisionUseScale(radius: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCollisionUseScaleBind, handle, radius)
    }

    /**
     * If `true`, `GPUParticles3D.collision_base_size` is multiplied by the particle's effective scale
     * (see `scale_min`, `scale_max`, `scale_curve`, and `scale_over_velocity_curve`).
     *
     * Generated from Godot docs: ParticleProcessMaterial.is_collision_using_scale
     */
    fun isCollisionUsingScale(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollisionUsingScaleBind, handle)
    }

    /**
     * The particles' friction. Values range from `0` (frictionless) to `1` (maximum friction). Only
     * effective if `collision_mode` is `COLLISION_RIGID`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_collision_friction
     */
    fun setCollisionFriction(friction: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCollisionFrictionBind, handle, friction)
    }

    /**
     * The particles' friction. Values range from `0` (frictionless) to `1` (maximum friction). Only
     * effective if `collision_mode` is `COLLISION_RIGID`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_collision_friction
     */
    fun getCollisionFriction(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCollisionFrictionBind, handle)
    }

    /**
     * The particles' bounciness. Values range from `0` (no bounce) to `1` (full bounciness). Only
     * effective if `collision_mode` is `COLLISION_RIGID`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_collision_bounce
     */
    fun setCollisionBounce(bounce: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCollisionBounceBind, handle, bounce)
    }

    /**
     * The particles' bounciness. Values range from `0` (no bounce) to `1` (full bounciness). Only
     * effective if `collision_mode` is `COLLISION_RIGID`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_collision_bounce
     */
    fun getCollisionBounce(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCollisionBounceBind, handle)
    }

    /**
     * Enable 3D rotation velocity.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_using_rotation_velocity_3d
     */
    fun setUsingRotationVelocity3d(useRotationVelocity3d: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUsingRotationVelocity3dBind, handle, useRotationVelocity3d)
    }

    /**
     * Enable 3D rotation velocity.
     *
     * Generated from Godot docs: ParticleProcessMaterial.is_using_rotation_velocity_3d
     */
    fun isUsingRotationVelocity3d(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingRotationVelocity3dBind, handle)
    }

    /**
     * Maximum 3D rotation velocity on the particle's local axis. Enable `use_rotation_velocity_3d` to
     * use this.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_rotation_velocity_3d_max
     */
    fun setRotationVelocity3dMax(rotationVelocity3dMax: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setRotationVelocity3dMaxBind, handle, rotationVelocity3dMax)
    }

    /**
     * Maximum 3D rotation velocity on the particle's local axis. Enable `use_rotation_velocity_3d` to
     * use this.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_rotation_velocity_3d_max
     */
    fun getRotationVelocity3dMax(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getRotationVelocity3dMaxBind, handle)
    }

    /**
     * Minimum 3D rotation velocity on the particle's local axis. Enable `use_rotation_velocity_3d` to
     * use this.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_rotation_velocity_3d_min
     */
    fun setRotationVelocity3dMin(rotationVelocity3dMin: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setRotationVelocity3dMinBind, handle, rotationVelocity3dMin)
    }

    /**
     * Minimum 3D rotation velocity on the particle's local axis. Enable `use_rotation_velocity_3d` to
     * use this.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_rotation_velocity_3d_min
     */
    fun getRotationVelocity3dMin(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getRotationVelocity3dMinBind, handle)
    }

    /**
     * Rotation velocity curve over lifetime, per-axis. Enable `use_rotation_velocity_3d` to use this.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_rotation_velocity_3d_curve
     */
    fun setRotationVelocity3dCurve(rotationVelocity3dCurve: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setRotationVelocity3dCurveBind, handle, listOf(rotationVelocity3dCurve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Rotation velocity curve over lifetime, per-axis. Enable `use_rotation_velocity_3d` to use this.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_rotation_velocity_3d_curve
     */
    fun getRotationVelocity3dCurve(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getRotationVelocity3dCurveBind, handle))
    }

    object Signals {
        const val emissionShapeChanged: String = "emission_shape_changed"
    }

    companion object {
        const val PARAM_INITIAL_LINEAR_VELOCITY: Long = 0L
        const val PARAM_ANGULAR_VELOCITY: Long = 1L
        const val PARAM_ORBIT_VELOCITY: Long = 2L
        const val PARAM_LINEAR_ACCEL: Long = 3L
        const val PARAM_RADIAL_ACCEL: Long = 4L
        const val PARAM_TANGENTIAL_ACCEL: Long = 5L
        const val PARAM_DAMPING: Long = 6L
        const val PARAM_ANGLE: Long = 7L
        const val PARAM_SCALE: Long = 8L
        const val PARAM_HUE_VARIATION: Long = 9L
        const val PARAM_ANIM_SPEED: Long = 10L
        const val PARAM_ANIM_OFFSET: Long = 11L
        const val PARAM_RADIAL_VELOCITY: Long = 15L
        const val PARAM_DIRECTIONAL_VELOCITY: Long = 16L
        const val PARAM_SCALE_OVER_VELOCITY: Long = 17L
        const val PARAM_MAX: Long = 18L
        const val PARAM_TURB_VEL_INFLUENCE: Long = 13L
        const val PARAM_TURB_INIT_DISPLACEMENT: Long = 14L
        const val PARAM_TURB_INFLUENCE_OVER_LIFE: Long = 12L
        const val PARTICLE_FLAG_ALIGN_Y_TO_VELOCITY: Long = 0L
        const val PARTICLE_FLAG_ROTATE_Y: Long = 1L
        const val PARTICLE_FLAG_DISABLE_Z: Long = 2L
        const val PARTICLE_FLAG_DAMPING_AS_FRICTION: Long = 3L
        const val PARTICLE_FLAG_INHERIT_EMITTER_SCALE: Long = 4L
        const val PARTICLE_FLAG_MAX: Long = 5L
        const val EMISSION_SHAPE_POINT: Long = 0L
        const val EMISSION_SHAPE_SPHERE: Long = 1L
        const val EMISSION_SHAPE_SPHERE_SURFACE: Long = 2L
        const val EMISSION_SHAPE_BOX: Long = 3L
        const val EMISSION_SHAPE_POINTS: Long = 4L
        const val EMISSION_SHAPE_DIRECTED_POINTS: Long = 5L
        const val EMISSION_SHAPE_RING: Long = 6L
        const val EMISSION_SHAPE_MAX: Long = 7L
        const val SUB_EMITTER_DISABLED: Long = 0L
        const val SUB_EMITTER_CONSTANT: Long = 1L
        const val SUB_EMITTER_AT_END: Long = 2L
        const val SUB_EMITTER_AT_COLLISION: Long = 3L
        const val SUB_EMITTER_AT_START: Long = 4L
        const val SUB_EMITTER_MAX: Long = 5L
        const val COLLISION_DISABLED: Long = 0L
        const val COLLISION_RIGID: Long = 1L
        const val COLLISION_HIDE_ON_CONTACT: Long = 2L
        const val COLLISION_MAX: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): ParticleProcessMaterial? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ParticleProcessMaterial? =
            if (handle.address() == 0L) null else ParticleProcessMaterial(handle)

        @JvmStatic
        fun fromResource(value: Resource): ParticleProcessMaterial? =
            if (value.isClass("ParticleProcessMaterial")) ParticleProcessMaterial(value.handle) else null

        private const val SET_DIRECTION_HASH = 3460891852L
        private val setDirectionBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_direction", SET_DIRECTION_HASH)
        }

        private const val GET_DIRECTION_HASH = 3360562783L
        private val getDirectionBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_direction", GET_DIRECTION_HASH)
        }

        private const val SET_INHERIT_VELOCITY_RATIO_HASH = 373806689L
        private val setInheritVelocityRatioBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_inherit_velocity_ratio", SET_INHERIT_VELOCITY_RATIO_HASH)
        }

        private const val GET_INHERIT_VELOCITY_RATIO_HASH = 191475506L
        private val getInheritVelocityRatioBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_inherit_velocity_ratio", GET_INHERIT_VELOCITY_RATIO_HASH)
        }

        private const val SET_SPREAD_HASH = 373806689L
        private val setSpreadBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_spread", SET_SPREAD_HASH)
        }

        private const val GET_SPREAD_HASH = 1740695150L
        private val getSpreadBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_spread", GET_SPREAD_HASH)
        }

        private const val SET_FLATNESS_HASH = 373806689L
        private val setFlatnessBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_flatness", SET_FLATNESS_HASH)
        }

        private const val GET_FLATNESS_HASH = 1740695150L
        private val getFlatnessBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_flatness", GET_FLATNESS_HASH)
        }

        private const val SET_PARAM_HASH = 676779352L
        private val setParamBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_param", SET_PARAM_HASH)
        }

        private const val GET_PARAM_HASH = 2623708480L
        private val getParamBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_param", GET_PARAM_HASH)
        }

        private const val SET_PARAM_MIN_HASH = 2295964248L
        private val setParamMinBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_param_min", SET_PARAM_MIN_HASH)
        }

        private const val GET_PARAM_MIN_HASH = 3903786503L
        private val getParamMinBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_param_min", GET_PARAM_MIN_HASH)
        }

        private const val SET_PARAM_MAX_HASH = 2295964248L
        private val setParamMaxBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_param_max", SET_PARAM_MAX_HASH)
        }

        private const val GET_PARAM_MAX_HASH = 3903786503L
        private val getParamMaxBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_param_max", GET_PARAM_MAX_HASH)
        }

        private const val SET_PARAM_TEXTURE_HASH = 526976089L
        private val setParamTextureBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_param_texture", SET_PARAM_TEXTURE_HASH)
        }

        private const val GET_PARAM_TEXTURE_HASH = 3489372978L
        private val getParamTextureBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_param_texture", GET_PARAM_TEXTURE_HASH)
        }

        private const val SET_COLOR_HASH = 2920490490L
        private val setColorBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_color", SET_COLOR_HASH)
        }

        private const val GET_COLOR_HASH = 3444240500L
        private val getColorBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_color", GET_COLOR_HASH)
        }

        private const val SET_USE_SCALE_3D_HASH = 2586408642L
        private val setUseScale3dBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_use_scale_3d", SET_USE_SCALE_3D_HASH)
        }

        private const val IS_USING_SCALE_3D_HASH = 36873697L
        private val isUsingScale3dBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "is_using_scale_3d", IS_USING_SCALE_3D_HASH)
        }

        private const val SET_SCALE_3D_MIN_HASH = 3460891852L
        private val setScale3dMinBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_scale_3d_min", SET_SCALE_3D_MIN_HASH)
        }

        private const val GET_SCALE_3D_MIN_HASH = 3360562783L
        private val getScale3dMinBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_scale_3d_min", GET_SCALE_3D_MIN_HASH)
        }

        private const val SET_SCALE_3D_MAX_HASH = 3460891852L
        private val setScale3dMaxBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_scale_3d_max", SET_SCALE_3D_MAX_HASH)
        }

        private const val GET_SCALE_3D_MAX_HASH = 3360562783L
        private val getScale3dMaxBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_scale_3d_max", GET_SCALE_3D_MAX_HASH)
        }

        private const val SET_USE_ROTATION_3D_HASH = 2586408642L
        private val setUseRotation3dBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_use_rotation_3d", SET_USE_ROTATION_3D_HASH)
        }

        private const val IS_USING_ROTATION_3D_HASH = 36873697L
        private val isUsingRotation3dBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "is_using_rotation_3d", IS_USING_ROTATION_3D_HASH)
        }

        private const val SET_ROTATION_3D_MIN_HASH = 3460891852L
        private val setRotation3dMinBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_rotation_3d_min", SET_ROTATION_3D_MIN_HASH)
        }

        private const val GET_ROTATION_3D_MIN_HASH = 3360562783L
        private val getRotation3dMinBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_rotation_3d_min", GET_ROTATION_3D_MIN_HASH)
        }

        private const val SET_ROTATION_3D_MAX_HASH = 3460891852L
        private val setRotation3dMaxBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_rotation_3d_max", SET_ROTATION_3D_MAX_HASH)
        }

        private const val GET_ROTATION_3D_MAX_HASH = 3360562783L
        private val getRotation3dMaxBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_rotation_3d_max", GET_ROTATION_3D_MAX_HASH)
        }

        private const val SET_COLOR_RAMP_HASH = 4051416890L
        private val setColorRampBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_color_ramp", SET_COLOR_RAMP_HASH)
        }

        private const val GET_COLOR_RAMP_HASH = 3635182373L
        private val getColorRampBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_color_ramp", GET_COLOR_RAMP_HASH)
        }

        private const val SET_ALPHA_CURVE_HASH = 4051416890L
        private val setAlphaCurveBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_alpha_curve", SET_ALPHA_CURVE_HASH)
        }

        private const val GET_ALPHA_CURVE_HASH = 3635182373L
        private val getAlphaCurveBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_alpha_curve", GET_ALPHA_CURVE_HASH)
        }

        private const val SET_EMISSION_CURVE_HASH = 4051416890L
        private val setEmissionCurveBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_curve", SET_EMISSION_CURVE_HASH)
        }

        private const val GET_EMISSION_CURVE_HASH = 3635182373L
        private val getEmissionCurveBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_curve", GET_EMISSION_CURVE_HASH)
        }

        private const val SET_COLOR_INITIAL_RAMP_HASH = 4051416890L
        private val setColorInitialRampBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_color_initial_ramp", SET_COLOR_INITIAL_RAMP_HASH)
        }

        private const val GET_COLOR_INITIAL_RAMP_HASH = 3635182373L
        private val getColorInitialRampBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_color_initial_ramp", GET_COLOR_INITIAL_RAMP_HASH)
        }

        private const val SET_VELOCITY_LIMIT_CURVE_HASH = 4051416890L
        private val setVelocityLimitCurveBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_velocity_limit_curve", SET_VELOCITY_LIMIT_CURVE_HASH)
        }

        private const val GET_VELOCITY_LIMIT_CURVE_HASH = 3635182373L
        private val getVelocityLimitCurveBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_velocity_limit_curve", GET_VELOCITY_LIMIT_CURVE_HASH)
        }

        private const val SET_PARTICLE_FLAG_HASH = 1711815571L
        private val setParticleFlagBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_particle_flag", SET_PARTICLE_FLAG_HASH)
        }

        private const val GET_PARTICLE_FLAG_HASH = 3895316907L
        private val getParticleFlagBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_particle_flag", GET_PARTICLE_FLAG_HASH)
        }

        private const val SET_VELOCITY_PIVOT_HASH = 3460891852L
        private val setVelocityPivotBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_velocity_pivot", SET_VELOCITY_PIVOT_HASH)
        }

        private const val GET_VELOCITY_PIVOT_HASH = 3783033775L
        private val getVelocityPivotBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_velocity_pivot", GET_VELOCITY_PIVOT_HASH)
        }

        private const val SET_EMISSION_SHAPE_HASH = 461501442L
        private val setEmissionShapeBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_shape", SET_EMISSION_SHAPE_HASH)
        }

        private const val GET_EMISSION_SHAPE_HASH = 3719733018L
        private val getEmissionShapeBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_shape", GET_EMISSION_SHAPE_HASH)
        }

        private const val SET_EMISSION_SPHERE_RADIUS_HASH = 373806689L
        private val setEmissionSphereRadiusBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_sphere_radius", SET_EMISSION_SPHERE_RADIUS_HASH)
        }

        private const val GET_EMISSION_SPHERE_RADIUS_HASH = 1740695150L
        private val getEmissionSphereRadiusBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_sphere_radius", GET_EMISSION_SPHERE_RADIUS_HASH)
        }

        private const val SET_EMISSION_BOX_EXTENTS_HASH = 3460891852L
        private val setEmissionBoxExtentsBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_box_extents", SET_EMISSION_BOX_EXTENTS_HASH)
        }

        private const val GET_EMISSION_BOX_EXTENTS_HASH = 3360562783L
        private val getEmissionBoxExtentsBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_box_extents", GET_EMISSION_BOX_EXTENTS_HASH)
        }

        private const val SET_EMISSION_POINT_TEXTURE_HASH = 4051416890L
        private val setEmissionPointTextureBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_point_texture", SET_EMISSION_POINT_TEXTURE_HASH)
        }

        private const val GET_EMISSION_POINT_TEXTURE_HASH = 3635182373L
        private val getEmissionPointTextureBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_point_texture", GET_EMISSION_POINT_TEXTURE_HASH)
        }

        private const val SET_EMISSION_NORMAL_TEXTURE_HASH = 4051416890L
        private val setEmissionNormalTextureBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_normal_texture", SET_EMISSION_NORMAL_TEXTURE_HASH)
        }

        private const val GET_EMISSION_NORMAL_TEXTURE_HASH = 3635182373L
        private val getEmissionNormalTextureBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_normal_texture", GET_EMISSION_NORMAL_TEXTURE_HASH)
        }

        private const val SET_EMISSION_COLOR_TEXTURE_HASH = 4051416890L
        private val setEmissionColorTextureBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_color_texture", SET_EMISSION_COLOR_TEXTURE_HASH)
        }

        private const val GET_EMISSION_COLOR_TEXTURE_HASH = 3635182373L
        private val getEmissionColorTextureBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_color_texture", GET_EMISSION_COLOR_TEXTURE_HASH)
        }

        private const val SET_EMISSION_POINT_COUNT_HASH = 1286410249L
        private val setEmissionPointCountBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_point_count", SET_EMISSION_POINT_COUNT_HASH)
        }

        private const val GET_EMISSION_POINT_COUNT_HASH = 3905245786L
        private val getEmissionPointCountBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_point_count", GET_EMISSION_POINT_COUNT_HASH)
        }

        private const val SET_EMISSION_RING_AXIS_HASH = 3460891852L
        private val setEmissionRingAxisBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_ring_axis", SET_EMISSION_RING_AXIS_HASH)
        }

        private const val GET_EMISSION_RING_AXIS_HASH = 3360562783L
        private val getEmissionRingAxisBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_ring_axis", GET_EMISSION_RING_AXIS_HASH)
        }

        private const val SET_EMISSION_RING_HEIGHT_HASH = 373806689L
        private val setEmissionRingHeightBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_ring_height", SET_EMISSION_RING_HEIGHT_HASH)
        }

        private const val GET_EMISSION_RING_HEIGHT_HASH = 1740695150L
        private val getEmissionRingHeightBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_ring_height", GET_EMISSION_RING_HEIGHT_HASH)
        }

        private const val SET_EMISSION_RING_RADIUS_HASH = 373806689L
        private val setEmissionRingRadiusBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_ring_radius", SET_EMISSION_RING_RADIUS_HASH)
        }

        private const val GET_EMISSION_RING_RADIUS_HASH = 1740695150L
        private val getEmissionRingRadiusBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_ring_radius", GET_EMISSION_RING_RADIUS_HASH)
        }

        private const val SET_EMISSION_RING_INNER_RADIUS_HASH = 373806689L
        private val setEmissionRingInnerRadiusBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_ring_inner_radius", SET_EMISSION_RING_INNER_RADIUS_HASH)
        }

        private const val GET_EMISSION_RING_INNER_RADIUS_HASH = 1740695150L
        private val getEmissionRingInnerRadiusBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_ring_inner_radius", GET_EMISSION_RING_INNER_RADIUS_HASH)
        }

        private const val SET_EMISSION_RING_CONE_ANGLE_HASH = 373806689L
        private val setEmissionRingConeAngleBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_ring_cone_angle", SET_EMISSION_RING_CONE_ANGLE_HASH)
        }

        private const val GET_EMISSION_RING_CONE_ANGLE_HASH = 1740695150L
        private val getEmissionRingConeAngleBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_ring_cone_angle", GET_EMISSION_RING_CONE_ANGLE_HASH)
        }

        private const val SET_EMISSION_SHAPE_OFFSET_HASH = 3460891852L
        private val setEmissionShapeOffsetBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_shape_offset", SET_EMISSION_SHAPE_OFFSET_HASH)
        }

        private const val GET_EMISSION_SHAPE_OFFSET_HASH = 3360562783L
        private val getEmissionShapeOffsetBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_shape_offset", GET_EMISSION_SHAPE_OFFSET_HASH)
        }

        private const val SET_EMISSION_SHAPE_SCALE_HASH = 3460891852L
        private val setEmissionShapeScaleBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_shape_scale", SET_EMISSION_SHAPE_SCALE_HASH)
        }

        private const val GET_EMISSION_SHAPE_SCALE_HASH = 3360562783L
        private val getEmissionShapeScaleBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_shape_scale", GET_EMISSION_SHAPE_SCALE_HASH)
        }

        private const val GET_TURBULENCE_ENABLED_HASH = 36873697L
        private val getTurbulenceEnabledBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_turbulence_enabled", GET_TURBULENCE_ENABLED_HASH)
        }

        private const val SET_TURBULENCE_ENABLED_HASH = 2586408642L
        private val setTurbulenceEnabledBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_turbulence_enabled", SET_TURBULENCE_ENABLED_HASH)
        }

        private const val GET_TURBULENCE_NOISE_STRENGTH_HASH = 1740695150L
        private val getTurbulenceNoiseStrengthBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_turbulence_noise_strength", GET_TURBULENCE_NOISE_STRENGTH_HASH)
        }

        private const val SET_TURBULENCE_NOISE_STRENGTH_HASH = 373806689L
        private val setTurbulenceNoiseStrengthBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_turbulence_noise_strength", SET_TURBULENCE_NOISE_STRENGTH_HASH)
        }

        private const val GET_TURBULENCE_NOISE_SCALE_HASH = 1740695150L
        private val getTurbulenceNoiseScaleBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_turbulence_noise_scale", GET_TURBULENCE_NOISE_SCALE_HASH)
        }

        private const val SET_TURBULENCE_NOISE_SCALE_HASH = 373806689L
        private val setTurbulenceNoiseScaleBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_turbulence_noise_scale", SET_TURBULENCE_NOISE_SCALE_HASH)
        }

        private const val GET_TURBULENCE_NOISE_SPEED_RANDOM_HASH = 1740695150L
        private val getTurbulenceNoiseSpeedRandomBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_turbulence_noise_speed_random", GET_TURBULENCE_NOISE_SPEED_RANDOM_HASH)
        }

        private const val SET_TURBULENCE_NOISE_SPEED_RANDOM_HASH = 373806689L
        private val setTurbulenceNoiseSpeedRandomBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_turbulence_noise_speed_random", SET_TURBULENCE_NOISE_SPEED_RANDOM_HASH)
        }

        private const val GET_TURBULENCE_NOISE_SPEED_HASH = 3360562783L
        private val getTurbulenceNoiseSpeedBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_turbulence_noise_speed", GET_TURBULENCE_NOISE_SPEED_HASH)
        }

        private const val SET_TURBULENCE_NOISE_SPEED_HASH = 3460891852L
        private val setTurbulenceNoiseSpeedBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_turbulence_noise_speed", SET_TURBULENCE_NOISE_SPEED_HASH)
        }

        private const val GET_GRAVITY_HASH = 3360562783L
        private val getGravityBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_gravity", GET_GRAVITY_HASH)
        }

        private const val SET_GRAVITY_HASH = 3460891852L
        private val setGravityBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_gravity", SET_GRAVITY_HASH)
        }

        private const val SET_LIFETIME_RANDOMNESS_HASH = 373806689L
        private val setLifetimeRandomnessBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_lifetime_randomness", SET_LIFETIME_RANDOMNESS_HASH)
        }

        private const val GET_LIFETIME_RANDOMNESS_HASH = 1740695150L
        private val getLifetimeRandomnessBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_lifetime_randomness", GET_LIFETIME_RANDOMNESS_HASH)
        }

        private const val GET_SUB_EMITTER_MODE_HASH = 2399052877L
        private val getSubEmitterModeBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_sub_emitter_mode", GET_SUB_EMITTER_MODE_HASH)
        }

        private const val SET_SUB_EMITTER_MODE_HASH = 2161806672L
        private val setSubEmitterModeBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_sub_emitter_mode", SET_SUB_EMITTER_MODE_HASH)
        }

        private const val GET_SUB_EMITTER_FREQUENCY_HASH = 1740695150L
        private val getSubEmitterFrequencyBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_sub_emitter_frequency", GET_SUB_EMITTER_FREQUENCY_HASH)
        }

        private const val SET_SUB_EMITTER_FREQUENCY_HASH = 373806689L
        private val setSubEmitterFrequencyBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_sub_emitter_frequency", SET_SUB_EMITTER_FREQUENCY_HASH)
        }

        private const val GET_SUB_EMITTER_AMOUNT_AT_END_HASH = 3905245786L
        private val getSubEmitterAmountAtEndBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_sub_emitter_amount_at_end", GET_SUB_EMITTER_AMOUNT_AT_END_HASH)
        }

        private const val SET_SUB_EMITTER_AMOUNT_AT_END_HASH = 1286410249L
        private val setSubEmitterAmountAtEndBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_sub_emitter_amount_at_end", SET_SUB_EMITTER_AMOUNT_AT_END_HASH)
        }

        private const val GET_SUB_EMITTER_AMOUNT_AT_COLLISION_HASH = 3905245786L
        private val getSubEmitterAmountAtCollisionBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_sub_emitter_amount_at_collision", GET_SUB_EMITTER_AMOUNT_AT_COLLISION_HASH)
        }

        private const val SET_SUB_EMITTER_AMOUNT_AT_COLLISION_HASH = 1286410249L
        private val setSubEmitterAmountAtCollisionBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_sub_emitter_amount_at_collision", SET_SUB_EMITTER_AMOUNT_AT_COLLISION_HASH)
        }

        private const val GET_SUB_EMITTER_AMOUNT_AT_START_HASH = 3905245786L
        private val getSubEmitterAmountAtStartBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_sub_emitter_amount_at_start", GET_SUB_EMITTER_AMOUNT_AT_START_HASH)
        }

        private const val SET_SUB_EMITTER_AMOUNT_AT_START_HASH = 1286410249L
        private val setSubEmitterAmountAtStartBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_sub_emitter_amount_at_start", SET_SUB_EMITTER_AMOUNT_AT_START_HASH)
        }

        private const val GET_SUB_EMITTER_KEEP_VELOCITY_HASH = 36873697L
        private val getSubEmitterKeepVelocityBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_sub_emitter_keep_velocity", GET_SUB_EMITTER_KEEP_VELOCITY_HASH)
        }

        private const val SET_SUB_EMITTER_KEEP_VELOCITY_HASH = 2586408642L
        private val setSubEmitterKeepVelocityBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_sub_emitter_keep_velocity", SET_SUB_EMITTER_KEEP_VELOCITY_HASH)
        }

        private const val SET_ATTRACTOR_INTERACTION_ENABLED_HASH = 2586408642L
        private val setAttractorInteractionEnabledBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_attractor_interaction_enabled", SET_ATTRACTOR_INTERACTION_ENABLED_HASH)
        }

        private const val IS_ATTRACTOR_INTERACTION_ENABLED_HASH = 36873697L
        private val isAttractorInteractionEnabledBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "is_attractor_interaction_enabled", IS_ATTRACTOR_INTERACTION_ENABLED_HASH)
        }

        private const val SET_COLLISION_MODE_HASH = 653804659L
        private val setCollisionModeBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_collision_mode", SET_COLLISION_MODE_HASH)
        }

        private const val GET_COLLISION_MODE_HASH = 139371864L
        private val getCollisionModeBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_collision_mode", GET_COLLISION_MODE_HASH)
        }

        private const val SET_COLLISION_USE_SCALE_HASH = 2586408642L
        private val setCollisionUseScaleBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_collision_use_scale", SET_COLLISION_USE_SCALE_HASH)
        }

        private const val IS_COLLISION_USING_SCALE_HASH = 36873697L
        private val isCollisionUsingScaleBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "is_collision_using_scale", IS_COLLISION_USING_SCALE_HASH)
        }

        private const val SET_COLLISION_FRICTION_HASH = 373806689L
        private val setCollisionFrictionBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_collision_friction", SET_COLLISION_FRICTION_HASH)
        }

        private const val GET_COLLISION_FRICTION_HASH = 1740695150L
        private val getCollisionFrictionBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_collision_friction", GET_COLLISION_FRICTION_HASH)
        }

        private const val SET_COLLISION_BOUNCE_HASH = 373806689L
        private val setCollisionBounceBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_collision_bounce", SET_COLLISION_BOUNCE_HASH)
        }

        private const val GET_COLLISION_BOUNCE_HASH = 1740695150L
        private val getCollisionBounceBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_collision_bounce", GET_COLLISION_BOUNCE_HASH)
        }

        private const val SET_USING_ROTATION_VELOCITY_3D_HASH = 2586408642L
        private val setUsingRotationVelocity3dBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_using_rotation_velocity_3d", SET_USING_ROTATION_VELOCITY_3D_HASH)
        }

        private const val IS_USING_ROTATION_VELOCITY_3D_HASH = 36873697L
        private val isUsingRotationVelocity3dBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "is_using_rotation_velocity_3d", IS_USING_ROTATION_VELOCITY_3D_HASH)
        }

        private const val SET_ROTATION_VELOCITY_3D_MAX_HASH = 3460891852L
        private val setRotationVelocity3dMaxBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_rotation_velocity_3d_max", SET_ROTATION_VELOCITY_3D_MAX_HASH)
        }

        private const val GET_ROTATION_VELOCITY_3D_MAX_HASH = 3360562783L
        private val getRotationVelocity3dMaxBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_rotation_velocity_3d_max", GET_ROTATION_VELOCITY_3D_MAX_HASH)
        }

        private const val SET_ROTATION_VELOCITY_3D_MIN_HASH = 3460891852L
        private val setRotationVelocity3dMinBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_rotation_velocity_3d_min", SET_ROTATION_VELOCITY_3D_MIN_HASH)
        }

        private const val GET_ROTATION_VELOCITY_3D_MIN_HASH = 3360562783L
        private val getRotationVelocity3dMinBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_rotation_velocity_3d_min", GET_ROTATION_VELOCITY_3D_MIN_HASH)
        }

        private const val SET_ROTATION_VELOCITY_3D_CURVE_HASH = 4051416890L
        private val setRotationVelocity3dCurveBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_rotation_velocity_3d_curve", SET_ROTATION_VELOCITY_3D_CURVE_HASH)
        }

        private const val GET_ROTATION_VELOCITY_3D_CURVE_HASH = 3635182373L
        private val getRotationVelocity3dCurveBind by lazy {
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_rotation_velocity_3d_curve", GET_ROTATION_VELOCITY_3D_CURVE_HASH)
        }
    }
}

package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * Holds a particle configuration for `GPUParticles2D` or `GPUParticles3D` nodes.
 *
 * Generated from Godot docs: ParticleProcessMaterial
 */
class ParticleProcessMaterial(handle: GodotHandle) : Material(handle) {
    var lifetimeRandomness: Double
        @JvmName("lifetimeRandomnessProperty")
        get() = getLifetimeRandomness()
        @JvmName("setLifetimeRandomnessProperty")
        set(value) = setLifetimeRandomness(value)

    var particleFlagAlignY: Boolean
        @JvmName("particleFlagAlignYProperty")
        get() = getParticleFlag(ParticleProcessMaterial.ParticleFlags.ALIGN_Y_TO_VELOCITY)
        @JvmName("setParticleFlagAlignYProperty")
        set(value) = setParticleFlag(ParticleProcessMaterial.ParticleFlags.ALIGN_Y_TO_VELOCITY, value)

    var particleFlagRotateY: Boolean
        @JvmName("particleFlagRotateYProperty")
        get() = getParticleFlag(ParticleProcessMaterial.ParticleFlags.ROTATE_Y)
        @JvmName("setParticleFlagRotateYProperty")
        set(value) = setParticleFlag(ParticleProcessMaterial.ParticleFlags.ROTATE_Y, value)

    var particleFlagDisableZ: Boolean
        @JvmName("particleFlagDisableZProperty")
        get() = getParticleFlag(ParticleProcessMaterial.ParticleFlags.DISABLE_Z)
        @JvmName("setParticleFlagDisableZProperty")
        set(value) = setParticleFlag(ParticleProcessMaterial.ParticleFlags.DISABLE_Z, value)

    var particleFlagDampingAsFriction: Boolean
        @JvmName("particleFlagDampingAsFrictionProperty")
        get() = getParticleFlag(ParticleProcessMaterial.ParticleFlags.DAMPING_AS_FRICTION)
        @JvmName("setParticleFlagDampingAsFrictionProperty")
        set(value) = setParticleFlag(ParticleProcessMaterial.ParticleFlags.DAMPING_AS_FRICTION, value)

    var particleFlagInheritEmitterScale: Boolean
        @JvmName("particleFlagInheritEmitterScaleProperty")
        get() = getParticleFlag(ParticleProcessMaterial.ParticleFlags.INHERIT_EMITTER_SCALE)
        @JvmName("setParticleFlagInheritEmitterScaleProperty")
        set(value) = setParticleFlag(ParticleProcessMaterial.ParticleFlags.INHERIT_EMITTER_SCALE, value)

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

    var emissionShape: ParticleProcessMaterial.EmissionShape
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
        get() = getParam(ParticleProcessMaterial.Parameter.ANGLE)
        @JvmName("setAngleProperty")
        set(value) = setParam(ParticleProcessMaterial.Parameter.ANGLE, value)

    var angleMin: Double
        @JvmName("angleMinProperty")
        get() = getParamMin(ParticleProcessMaterial.Parameter.ANGLE)
        @JvmName("setAngleMinProperty")
        set(value) = setParamMin(ParticleProcessMaterial.Parameter.ANGLE, value)

    var angleMax: Double
        @JvmName("angleMaxProperty")
        get() = getParamMax(ParticleProcessMaterial.Parameter.ANGLE)
        @JvmName("setAngleMaxProperty")
        set(value) = setParamMax(ParticleProcessMaterial.Parameter.ANGLE, value)

    var angleCurve: Texture2D?
        @JvmName("angleCurveProperty")
        get() = getParamTexture(ParticleProcessMaterial.Parameter.ANGLE)
        @JvmName("setAngleCurveProperty")
        set(value) = setParamTexture(ParticleProcessMaterial.Parameter.ANGLE, value)

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
        get() = getParam(ParticleProcessMaterial.Parameter.INITIAL_LINEAR_VELOCITY)
        @JvmName("setInitialVelocityProperty")
        set(value) = setParam(ParticleProcessMaterial.Parameter.INITIAL_LINEAR_VELOCITY, value)

    var initialVelocityMin: Double
        @JvmName("initialVelocityMinProperty")
        get() = getParamMin(ParticleProcessMaterial.Parameter.INITIAL_LINEAR_VELOCITY)
        @JvmName("setInitialVelocityMinProperty")
        set(value) = setParamMin(ParticleProcessMaterial.Parameter.INITIAL_LINEAR_VELOCITY, value)

    var initialVelocityMax: Double
        @JvmName("initialVelocityMaxProperty")
        get() = getParamMax(ParticleProcessMaterial.Parameter.INITIAL_LINEAR_VELOCITY)
        @JvmName("setInitialVelocityMaxProperty")
        set(value) = setParamMax(ParticleProcessMaterial.Parameter.INITIAL_LINEAR_VELOCITY, value)

    var angularVelocity: Vector2
        @JvmName("angularVelocityProperty")
        get() = getParam(ParticleProcessMaterial.Parameter.ANGULAR_VELOCITY)
        @JvmName("setAngularVelocityProperty")
        set(value) = setParam(ParticleProcessMaterial.Parameter.ANGULAR_VELOCITY, value)

    var angularVelocityMin: Double
        @JvmName("angularVelocityMinProperty")
        get() = getParamMin(ParticleProcessMaterial.Parameter.ANGULAR_VELOCITY)
        @JvmName("setAngularVelocityMinProperty")
        set(value) = setParamMin(ParticleProcessMaterial.Parameter.ANGULAR_VELOCITY, value)

    var angularVelocityMax: Double
        @JvmName("angularVelocityMaxProperty")
        get() = getParamMax(ParticleProcessMaterial.Parameter.ANGULAR_VELOCITY)
        @JvmName("setAngularVelocityMaxProperty")
        set(value) = setParamMax(ParticleProcessMaterial.Parameter.ANGULAR_VELOCITY, value)

    var angularVelocityCurve: Texture2D?
        @JvmName("angularVelocityCurveProperty")
        get() = getParamTexture(ParticleProcessMaterial.Parameter.ANGULAR_VELOCITY)
        @JvmName("setAngularVelocityCurveProperty")
        set(value) = setParamTexture(ParticleProcessMaterial.Parameter.ANGULAR_VELOCITY, value)

    var directionalVelocity: Vector2
        @JvmName("directionalVelocityProperty")
        get() = getParam(ParticleProcessMaterial.Parameter.DIRECTIONAL_VELOCITY)
        @JvmName("setDirectionalVelocityProperty")
        set(value) = setParam(ParticleProcessMaterial.Parameter.DIRECTIONAL_VELOCITY, value)

    var directionalVelocityMin: Double
        @JvmName("directionalVelocityMinProperty")
        get() = getParamMin(ParticleProcessMaterial.Parameter.DIRECTIONAL_VELOCITY)
        @JvmName("setDirectionalVelocityMinProperty")
        set(value) = setParamMin(ParticleProcessMaterial.Parameter.DIRECTIONAL_VELOCITY, value)

    var directionalVelocityMax: Double
        @JvmName("directionalVelocityMaxProperty")
        get() = getParamMax(ParticleProcessMaterial.Parameter.DIRECTIONAL_VELOCITY)
        @JvmName("setDirectionalVelocityMaxProperty")
        set(value) = setParamMax(ParticleProcessMaterial.Parameter.DIRECTIONAL_VELOCITY, value)

    var directionalVelocityCurve: Texture2D?
        @JvmName("directionalVelocityCurveProperty")
        get() = getParamTexture(ParticleProcessMaterial.Parameter.DIRECTIONAL_VELOCITY)
        @JvmName("setDirectionalVelocityCurveProperty")
        set(value) = setParamTexture(ParticleProcessMaterial.Parameter.DIRECTIONAL_VELOCITY, value)

    var orbitVelocity: Vector2
        @JvmName("orbitVelocityProperty")
        get() = getParam(ParticleProcessMaterial.Parameter.ORBIT_VELOCITY)
        @JvmName("setOrbitVelocityProperty")
        set(value) = setParam(ParticleProcessMaterial.Parameter.ORBIT_VELOCITY, value)

    var orbitVelocityMin: Double
        @JvmName("orbitVelocityMinProperty")
        get() = getParamMin(ParticleProcessMaterial.Parameter.ORBIT_VELOCITY)
        @JvmName("setOrbitVelocityMinProperty")
        set(value) = setParamMin(ParticleProcessMaterial.Parameter.ORBIT_VELOCITY, value)

    var orbitVelocityMax: Double
        @JvmName("orbitVelocityMaxProperty")
        get() = getParamMax(ParticleProcessMaterial.Parameter.ORBIT_VELOCITY)
        @JvmName("setOrbitVelocityMaxProperty")
        set(value) = setParamMax(ParticleProcessMaterial.Parameter.ORBIT_VELOCITY, value)

    var orbitVelocityCurve: Texture2D?
        @JvmName("orbitVelocityCurveProperty")
        get() = getParamTexture(ParticleProcessMaterial.Parameter.ORBIT_VELOCITY)
        @JvmName("setOrbitVelocityCurveProperty")
        set(value) = setParamTexture(ParticleProcessMaterial.Parameter.ORBIT_VELOCITY, value)

    var radialVelocity: Vector2
        @JvmName("radialVelocityProperty")
        get() = getParam(ParticleProcessMaterial.Parameter.RADIAL_VELOCITY)
        @JvmName("setRadialVelocityProperty")
        set(value) = setParam(ParticleProcessMaterial.Parameter.RADIAL_VELOCITY, value)

    var radialVelocityMin: Double
        @JvmName("radialVelocityMinProperty")
        get() = getParamMin(ParticleProcessMaterial.Parameter.RADIAL_VELOCITY)
        @JvmName("setRadialVelocityMinProperty")
        set(value) = setParamMin(ParticleProcessMaterial.Parameter.RADIAL_VELOCITY, value)

    var radialVelocityMax: Double
        @JvmName("radialVelocityMaxProperty")
        get() = getParamMax(ParticleProcessMaterial.Parameter.RADIAL_VELOCITY)
        @JvmName("setRadialVelocityMaxProperty")
        set(value) = setParamMax(ParticleProcessMaterial.Parameter.RADIAL_VELOCITY, value)

    var radialVelocityCurve: Texture2D?
        @JvmName("radialVelocityCurveProperty")
        get() = getParamTexture(ParticleProcessMaterial.Parameter.RADIAL_VELOCITY)
        @JvmName("setRadialVelocityCurveProperty")
        set(value) = setParamTexture(ParticleProcessMaterial.Parameter.RADIAL_VELOCITY, value)

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
        get() = getParam(ParticleProcessMaterial.Parameter.LINEAR_ACCEL)
        @JvmName("setLinearAccelProperty")
        set(value) = setParam(ParticleProcessMaterial.Parameter.LINEAR_ACCEL, value)

    var linearAccelMin: Double
        @JvmName("linearAccelMinProperty")
        get() = getParamMin(ParticleProcessMaterial.Parameter.LINEAR_ACCEL)
        @JvmName("setLinearAccelMinProperty")
        set(value) = setParamMin(ParticleProcessMaterial.Parameter.LINEAR_ACCEL, value)

    var linearAccelMax: Double
        @JvmName("linearAccelMaxProperty")
        get() = getParamMax(ParticleProcessMaterial.Parameter.LINEAR_ACCEL)
        @JvmName("setLinearAccelMaxProperty")
        set(value) = setParamMax(ParticleProcessMaterial.Parameter.LINEAR_ACCEL, value)

    var linearAccelCurve: Texture2D?
        @JvmName("linearAccelCurveProperty")
        get() = getParamTexture(ParticleProcessMaterial.Parameter.LINEAR_ACCEL)
        @JvmName("setLinearAccelCurveProperty")
        set(value) = setParamTexture(ParticleProcessMaterial.Parameter.LINEAR_ACCEL, value)

    var radialAccel: Vector2
        @JvmName("radialAccelProperty")
        get() = getParam(ParticleProcessMaterial.Parameter.RADIAL_ACCEL)
        @JvmName("setRadialAccelProperty")
        set(value) = setParam(ParticleProcessMaterial.Parameter.RADIAL_ACCEL, value)

    var radialAccelMin: Double
        @JvmName("radialAccelMinProperty")
        get() = getParamMin(ParticleProcessMaterial.Parameter.RADIAL_ACCEL)
        @JvmName("setRadialAccelMinProperty")
        set(value) = setParamMin(ParticleProcessMaterial.Parameter.RADIAL_ACCEL, value)

    var radialAccelMax: Double
        @JvmName("radialAccelMaxProperty")
        get() = getParamMax(ParticleProcessMaterial.Parameter.RADIAL_ACCEL)
        @JvmName("setRadialAccelMaxProperty")
        set(value) = setParamMax(ParticleProcessMaterial.Parameter.RADIAL_ACCEL, value)

    var radialAccelCurve: Texture2D?
        @JvmName("radialAccelCurveProperty")
        get() = getParamTexture(ParticleProcessMaterial.Parameter.RADIAL_ACCEL)
        @JvmName("setRadialAccelCurveProperty")
        set(value) = setParamTexture(ParticleProcessMaterial.Parameter.RADIAL_ACCEL, value)

    var tangentialAccel: Vector2
        @JvmName("tangentialAccelProperty")
        get() = getParam(ParticleProcessMaterial.Parameter.TANGENTIAL_ACCEL)
        @JvmName("setTangentialAccelProperty")
        set(value) = setParam(ParticleProcessMaterial.Parameter.TANGENTIAL_ACCEL, value)

    var tangentialAccelMin: Double
        @JvmName("tangentialAccelMinProperty")
        get() = getParamMin(ParticleProcessMaterial.Parameter.TANGENTIAL_ACCEL)
        @JvmName("setTangentialAccelMinProperty")
        set(value) = setParamMin(ParticleProcessMaterial.Parameter.TANGENTIAL_ACCEL, value)

    var tangentialAccelMax: Double
        @JvmName("tangentialAccelMaxProperty")
        get() = getParamMax(ParticleProcessMaterial.Parameter.TANGENTIAL_ACCEL)
        @JvmName("setTangentialAccelMaxProperty")
        set(value) = setParamMax(ParticleProcessMaterial.Parameter.TANGENTIAL_ACCEL, value)

    var tangentialAccelCurve: Texture2D?
        @JvmName("tangentialAccelCurveProperty")
        get() = getParamTexture(ParticleProcessMaterial.Parameter.TANGENTIAL_ACCEL)
        @JvmName("setTangentialAccelCurveProperty")
        set(value) = setParamTexture(ParticleProcessMaterial.Parameter.TANGENTIAL_ACCEL, value)

    var damping: Vector2
        @JvmName("dampingProperty")
        get() = getParam(ParticleProcessMaterial.Parameter.DAMPING)
        @JvmName("setDampingProperty")
        set(value) = setParam(ParticleProcessMaterial.Parameter.DAMPING, value)

    var dampingMin: Double
        @JvmName("dampingMinProperty")
        get() = getParamMin(ParticleProcessMaterial.Parameter.DAMPING)
        @JvmName("setDampingMinProperty")
        set(value) = setParamMin(ParticleProcessMaterial.Parameter.DAMPING, value)

    var dampingMax: Double
        @JvmName("dampingMaxProperty")
        get() = getParamMax(ParticleProcessMaterial.Parameter.DAMPING)
        @JvmName("setDampingMaxProperty")
        set(value) = setParamMax(ParticleProcessMaterial.Parameter.DAMPING, value)

    var dampingCurve: Texture2D?
        @JvmName("dampingCurveProperty")
        get() = getParamTexture(ParticleProcessMaterial.Parameter.DAMPING)
        @JvmName("setDampingCurveProperty")
        set(value) = setParamTexture(ParticleProcessMaterial.Parameter.DAMPING, value)

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
        get() = getParam(ParticleProcessMaterial.Parameter.SCALE)
        @JvmName("setScaleProperty")
        set(value) = setParam(ParticleProcessMaterial.Parameter.SCALE, value)

    var scaleMin: Double
        @JvmName("scaleMinProperty")
        get() = getParamMin(ParticleProcessMaterial.Parameter.SCALE)
        @JvmName("setScaleMinProperty")
        set(value) = setParamMin(ParticleProcessMaterial.Parameter.SCALE, value)

    var scaleMax: Double
        @JvmName("scaleMaxProperty")
        get() = getParamMax(ParticleProcessMaterial.Parameter.SCALE)
        @JvmName("setScaleMaxProperty")
        set(value) = setParamMax(ParticleProcessMaterial.Parameter.SCALE, value)

    var scaleCurve: Texture2D?
        @JvmName("scaleCurveProperty")
        get() = getParamTexture(ParticleProcessMaterial.Parameter.SCALE)
        @JvmName("setScaleCurveProperty")
        set(value) = setParamTexture(ParticleProcessMaterial.Parameter.SCALE, value)

    var scaleOverVelocity: Vector2
        @JvmName("scaleOverVelocityProperty")
        get() = getParam(ParticleProcessMaterial.Parameter.SCALE_OVER_VELOCITY)
        @JvmName("setScaleOverVelocityProperty")
        set(value) = setParam(ParticleProcessMaterial.Parameter.SCALE_OVER_VELOCITY, value)

    var scaleOverVelocityMin: Double
        @JvmName("scaleOverVelocityMinProperty")
        get() = getParamMin(ParticleProcessMaterial.Parameter.SCALE_OVER_VELOCITY)
        @JvmName("setScaleOverVelocityMinProperty")
        set(value) = setParamMin(ParticleProcessMaterial.Parameter.SCALE_OVER_VELOCITY, value)

    var scaleOverVelocityMax: Double
        @JvmName("scaleOverVelocityMaxProperty")
        get() = getParamMax(ParticleProcessMaterial.Parameter.SCALE_OVER_VELOCITY)
        @JvmName("setScaleOverVelocityMaxProperty")
        set(value) = setParamMax(ParticleProcessMaterial.Parameter.SCALE_OVER_VELOCITY, value)

    var scaleOverVelocityCurve: Texture2D?
        @JvmName("scaleOverVelocityCurveProperty")
        get() = getParamTexture(ParticleProcessMaterial.Parameter.SCALE_OVER_VELOCITY)
        @JvmName("setScaleOverVelocityCurveProperty")
        set(value) = setParamTexture(ParticleProcessMaterial.Parameter.SCALE_OVER_VELOCITY, value)

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
        get() = getParam(ParticleProcessMaterial.Parameter.HUE_VARIATION)
        @JvmName("setHueVariationProperty")
        set(value) = setParam(ParticleProcessMaterial.Parameter.HUE_VARIATION, value)

    var hueVariationMin: Double
        @JvmName("hueVariationMinProperty")
        get() = getParamMin(ParticleProcessMaterial.Parameter.HUE_VARIATION)
        @JvmName("setHueVariationMinProperty")
        set(value) = setParamMin(ParticleProcessMaterial.Parameter.HUE_VARIATION, value)

    var hueVariationMax: Double
        @JvmName("hueVariationMaxProperty")
        get() = getParamMax(ParticleProcessMaterial.Parameter.HUE_VARIATION)
        @JvmName("setHueVariationMaxProperty")
        set(value) = setParamMax(ParticleProcessMaterial.Parameter.HUE_VARIATION, value)

    var hueVariationCurve: Texture2D?
        @JvmName("hueVariationCurveProperty")
        get() = getParamTexture(ParticleProcessMaterial.Parameter.HUE_VARIATION)
        @JvmName("setHueVariationCurveProperty")
        set(value) = setParamTexture(ParticleProcessMaterial.Parameter.HUE_VARIATION, value)

    var animSpeed: Vector2
        @JvmName("animSpeedProperty")
        get() = getParam(ParticleProcessMaterial.Parameter.ANIM_SPEED)
        @JvmName("setAnimSpeedProperty")
        set(value) = setParam(ParticleProcessMaterial.Parameter.ANIM_SPEED, value)

    var animSpeedMin: Double
        @JvmName("animSpeedMinProperty")
        get() = getParamMin(ParticleProcessMaterial.Parameter.ANIM_SPEED)
        @JvmName("setAnimSpeedMinProperty")
        set(value) = setParamMin(ParticleProcessMaterial.Parameter.ANIM_SPEED, value)

    var animSpeedMax: Double
        @JvmName("animSpeedMaxProperty")
        get() = getParamMax(ParticleProcessMaterial.Parameter.ANIM_SPEED)
        @JvmName("setAnimSpeedMaxProperty")
        set(value) = setParamMax(ParticleProcessMaterial.Parameter.ANIM_SPEED, value)

    var animSpeedCurve: Texture2D?
        @JvmName("animSpeedCurveProperty")
        get() = getParamTexture(ParticleProcessMaterial.Parameter.ANIM_SPEED)
        @JvmName("setAnimSpeedCurveProperty")
        set(value) = setParamTexture(ParticleProcessMaterial.Parameter.ANIM_SPEED, value)

    var animOffset: Vector2
        @JvmName("animOffsetProperty")
        get() = getParam(ParticleProcessMaterial.Parameter.ANIM_OFFSET)
        @JvmName("setAnimOffsetProperty")
        set(value) = setParam(ParticleProcessMaterial.Parameter.ANIM_OFFSET, value)

    var animOffsetMin: Double
        @JvmName("animOffsetMinProperty")
        get() = getParamMin(ParticleProcessMaterial.Parameter.ANIM_OFFSET)
        @JvmName("setAnimOffsetMinProperty")
        set(value) = setParamMin(ParticleProcessMaterial.Parameter.ANIM_OFFSET, value)

    var animOffsetMax: Double
        @JvmName("animOffsetMaxProperty")
        get() = getParamMax(ParticleProcessMaterial.Parameter.ANIM_OFFSET)
        @JvmName("setAnimOffsetMaxProperty")
        set(value) = setParamMax(ParticleProcessMaterial.Parameter.ANIM_OFFSET, value)

    var animOffsetCurve: Texture2D?
        @JvmName("animOffsetCurveProperty")
        get() = getParamTexture(ParticleProcessMaterial.Parameter.ANIM_OFFSET)
        @JvmName("setAnimOffsetCurveProperty")
        set(value) = setParamTexture(ParticleProcessMaterial.Parameter.ANIM_OFFSET, value)

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
        get() = getParam(ParticleProcessMaterial.Parameter.TURB_VEL_INFLUENCE)
        @JvmName("setTurbulenceInfluenceProperty")
        set(value) = setParam(ParticleProcessMaterial.Parameter.TURB_VEL_INFLUENCE, value)

    var turbulenceInfluenceMin: Double
        @JvmName("turbulenceInfluenceMinProperty")
        get() = getParamMin(ParticleProcessMaterial.Parameter.TURB_VEL_INFLUENCE)
        @JvmName("setTurbulenceInfluenceMinProperty")
        set(value) = setParamMin(ParticleProcessMaterial.Parameter.TURB_VEL_INFLUENCE, value)

    var turbulenceInfluenceMax: Double
        @JvmName("turbulenceInfluenceMaxProperty")
        get() = getParamMax(ParticleProcessMaterial.Parameter.TURB_VEL_INFLUENCE)
        @JvmName("setTurbulenceInfluenceMaxProperty")
        set(value) = setParamMax(ParticleProcessMaterial.Parameter.TURB_VEL_INFLUENCE, value)

    var turbulenceInitialDisplacement: Vector2
        @JvmName("turbulenceInitialDisplacementProperty")
        get() = getParam(ParticleProcessMaterial.Parameter.TURB_INIT_DISPLACEMENT)
        @JvmName("setTurbulenceInitialDisplacementProperty")
        set(value) = setParam(ParticleProcessMaterial.Parameter.TURB_INIT_DISPLACEMENT, value)

    var turbulenceInitialDisplacementMin: Double
        @JvmName("turbulenceInitialDisplacementMinProperty")
        get() = getParamMin(ParticleProcessMaterial.Parameter.TURB_INIT_DISPLACEMENT)
        @JvmName("setTurbulenceInitialDisplacementMinProperty")
        set(value) = setParamMin(ParticleProcessMaterial.Parameter.TURB_INIT_DISPLACEMENT, value)

    var turbulenceInitialDisplacementMax: Double
        @JvmName("turbulenceInitialDisplacementMaxProperty")
        get() = getParamMax(ParticleProcessMaterial.Parameter.TURB_INIT_DISPLACEMENT)
        @JvmName("setTurbulenceInitialDisplacementMaxProperty")
        set(value) = setParamMax(ParticleProcessMaterial.Parameter.TURB_INIT_DISPLACEMENT, value)

    var turbulenceInfluenceOverLife: Texture2D?
        @JvmName("turbulenceInfluenceOverLifeProperty")
        get() = getParamTexture(ParticleProcessMaterial.Parameter.TURB_INFLUENCE_OVER_LIFE)
        @JvmName("setTurbulenceInfluenceOverLifeProperty")
        set(value) = setParamTexture(ParticleProcessMaterial.Parameter.TURB_INFLUENCE_OVER_LIFE, value)

    var collisionMode: ParticleProcessMaterial.CollisionMode
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

    var subEmitterMode: ParticleProcessMaterial.SubEmitterMode
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
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setDirectionBind, segment, degrees)
    }

    /**
     * Unit vector specifying the particles' emission direction.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_direction
     */
    fun getDirection(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getDirectionBind, segment)
    }

    /**
     * Percentage of the velocity of the respective `GPUParticles2D` or `GPUParticles3D` inherited by
     * each particle when spawning.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_inherit_velocity_ratio
     */
    fun setInheritVelocityRatio(ratio: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setInheritVelocityRatioBind, segment, ratio)
    }

    /**
     * Percentage of the velocity of the respective `GPUParticles2D` or `GPUParticles3D` inherited by
     * each particle when spawning.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_inherit_velocity_ratio
     */
    fun getInheritVelocityRatio(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getInheritVelocityRatioBind, segment)
    }

    /**
     * Each particle's initial direction range from `+spread` to `-spread` degrees.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_spread
     */
    fun setSpread(degrees: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSpreadBind, segment, degrees)
    }

    /**
     * Each particle's initial direction range from `+spread` to `-spread` degrees.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_spread
     */
    fun getSpread(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSpreadBind, segment)
    }

    /**
     * Amount of `spread` along the Y axis.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_flatness
     */
    fun setFlatness(amount: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setFlatnessBind, segment, amount)
    }

    /**
     * Amount of `spread` along the Y axis.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_flatness
     */
    fun getFlatness(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFlatnessBind, segment)
    }

    /**
     * Sets the minimum and maximum values of the given `param`. The `x` component of the argument
     * vector corresponds to minimum and the `y` component corresponds to maximum.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_param
     */
    fun setParam(param: ParticleProcessMaterial.Parameter, value: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndVector2Arg(Binds.setParamBind, segment, param.value, value)
    }

    /**
     * Returns the minimum and maximum values of the given `param` as a vector. The `x` component of
     * the returned vector corresponds to minimum and the `y` component corresponds to maximum.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_param
     */
    fun getParam(param: ParticleProcessMaterial.Parameter): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetVector2(Binds.getParamBind, segment, param.value)
    }

    /**
     * Minimum displacement of each particle's spawn position by the turbulence. The actual amount of
     * displacement will be a factor of the underlying turbulence multiplied by a random value between
     * `turbulence_initial_displacement_min` and `turbulence_initial_displacement_max`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_param_min
     */
    fun setParamMin(param: ParticleProcessMaterial.Parameter, value: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndDoubleArg(Binds.setParamMinBind, segment, param.value, value)
    }

    /**
     * Minimum displacement of each particle's spawn position by the turbulence. The actual amount of
     * displacement will be a factor of the underlying turbulence multiplied by a random value between
     * `turbulence_initial_displacement_min` and `turbulence_initial_displacement_max`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_param_min
     */
    fun getParamMin(param: ParticleProcessMaterial.Parameter): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetDouble(Binds.getParamMinBind, segment, param.value)
    }

    /**
     * Maximum displacement of each particle's spawn position by the turbulence. The actual amount of
     * displacement will be a factor of the underlying turbulence multiplied by a random value between
     * `turbulence_initial_displacement_min` and `turbulence_initial_displacement_max`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_param_max
     */
    fun setParamMax(param: ParticleProcessMaterial.Parameter, value: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndDoubleArg(Binds.setParamMaxBind, segment, param.value, value)
    }

    /**
     * Maximum displacement of each particle's spawn position by the turbulence. The actual amount of
     * displacement will be a factor of the underlying turbulence multiplied by a random value between
     * `turbulence_initial_displacement_min` and `turbulence_initial_displacement_max`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_param_max
     */
    fun getParamMax(param: ParticleProcessMaterial.Parameter): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetDouble(Binds.getParamMaxBind, segment, param.value)
    }

    /**
     * Each particle's amount of turbulence will be influenced along this `CurveTexture` over its life
     * time.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_param_texture
     */
    fun setParamTexture(param: ParticleProcessMaterial.Parameter, texture: Texture2D?) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndObjectArg(Binds.setParamTextureBind, segment, param.value, texture?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Each particle's amount of turbulence will be influenced along this `CurveTexture` over its life
     * time.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_param_texture
     */
    fun getParamTexture(param: ParticleProcessMaterial.Parameter): Texture2D? {
        checkOpen()
        return Texture2D.wrapOwned(ObjectCalls.ptrcallWithLongArgRetObject(Binds.getParamTextureBind, segment, param.value))
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
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(Binds.setColorBind, segment, color)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getColorBind, segment)
    }

    /**
     * Enable the usage of `scale_3d_min` and `scale_3d_max`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_use_scale_3d
     */
    fun setUseScale3d(usingScale3d: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseScale3dBind, segment, usingScale3d)
    }

    /**
     * Enable the usage of `scale_3d_min` and `scale_3d_max`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.is_using_scale_3d
     */
    fun isUsingScale3d(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isUsingScale3dBind, segment)
    }

    /**
     * The minimum value of the random scale vector for each particle. Works only if `use_scale_3d` is
     * enabled.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_scale_3d_min
     */
    fun setScale3dMin(scale3dMin: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setScale3dMinBind, segment, scale3dMin)
    }

    /**
     * The minimum value of the random scale vector for each particle. Works only if `use_scale_3d` is
     * enabled.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_scale_3d_min
     */
    fun getScale3dMin(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getScale3dMinBind, segment)
    }

    /**
     * The maximum value of the random scale vector for each particle. Works only if `use_scale_3d` is
     * enabled.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_scale_3d_max
     */
    fun setScale3dMax(scale3dMax: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setScale3dMaxBind, segment, scale3dMax)
    }

    /**
     * The maximum value of the random scale vector for each particle. Works only if `use_scale_3d` is
     * enabled.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_scale_3d_max
     */
    fun getScale3dMax(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getScale3dMaxBind, segment)
    }

    /**
     * Enable the usage of `rotation_3d_min` and `rotation_3d_max`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_use_rotation_3d
     */
    fun setUseRotation3d(usingRotation3d: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseRotation3dBind, segment, usingRotation3d)
    }

    /**
     * Enable the usage of `rotation_3d_min` and `rotation_3d_max`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.is_using_rotation_3d
     */
    fun isUsingRotation3d(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isUsingRotation3dBind, segment)
    }

    /**
     * The minimum 3D orientation, in degrees. Works only in 3D and if `use_rotation_3d` is enabled.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_rotation_3d_min
     */
    fun setRotation3dMin(rotation3dMin: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setRotation3dMinBind, segment, rotation3dMin)
    }

    /**
     * The minimum 3D orientation, in degrees. Works only in 3D and if `use_rotation_3d` is enabled.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_rotation_3d_min
     */
    fun getRotation3dMin(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getRotation3dMinBind, segment)
    }

    /**
     * The maximum 3D orientation, in degrees. Works only in 3D and if `use_rotation_3d` is enabled.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_rotation_3d_max
     */
    fun setRotation3dMax(rotation3dMax: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setRotation3dMaxBind, segment, rotation3dMax)
    }

    /**
     * The maximum 3D orientation, in degrees. Works only in 3D and if `use_rotation_3d` is enabled.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_rotation_3d_max
     */
    fun getRotation3dMax(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getRotation3dMaxBind, segment)
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
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setColorRampBind, segment, listOf(ramp?.requireOpenHandle() ?: NULL_SEGMENT))
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
        checkOpen()
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getColorRampBind, segment))
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
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setAlphaCurveBind, segment, listOf(curve?.requireOpenHandle() ?: NULL_SEGMENT))
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
        checkOpen()
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getAlphaCurveBind, segment))
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
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setEmissionCurveBind, segment, listOf(curve?.requireOpenHandle() ?: NULL_SEGMENT))
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
        checkOpen()
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getEmissionCurveBind, segment))
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
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setColorInitialRampBind, segment, listOf(ramp?.requireOpenHandle() ?: NULL_SEGMENT))
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
        checkOpen()
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getColorInitialRampBind, segment))
    }

    /**
     * A `CurveTexture` that defines the maximum velocity of a particle during its lifetime.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_velocity_limit_curve
     */
    fun setVelocityLimitCurve(curve: Texture2D?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setVelocityLimitCurveBind, segment, listOf(curve?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * A `CurveTexture` that defines the maximum velocity of a particle during its lifetime.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_velocity_limit_curve
     */
    fun getVelocityLimitCurve(): Texture2D? {
        checkOpen()
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getVelocityLimitCurveBind, segment))
    }

    /**
     * If `true`, particles rotate around Y axis by `angle_min`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_particle_flag
     */
    fun setParticleFlag(particleFlag: ParticleProcessMaterial.ParticleFlags, enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndBoolArgs(Binds.setParticleFlagBind, segment, particleFlag.value, enable)
    }

    /**
     * If `true`, particles rotate around Y axis by `angle_min`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_particle_flag
     */
    fun getParticleFlag(particleFlag: ParticleProcessMaterial.ParticleFlags): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetBool(Binds.getParticleFlagBind, segment, particleFlag.value)
    }

    /**
     * A pivot point used to calculate radial and orbital velocity of particles.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_velocity_pivot
     */
    fun setVelocityPivot(pivot: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setVelocityPivotBind, segment, pivot)
    }

    /**
     * A pivot point used to calculate radial and orbital velocity of particles.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_velocity_pivot
     */
    fun getVelocityPivot(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getVelocityPivotBind, segment)
    }

    /**
     * Particles will be emitted inside this region.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_shape
     */
    fun setEmissionShape(shape: ParticleProcessMaterial.EmissionShape) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setEmissionShapeBind, segment, shape.value)
    }

    /**
     * Particles will be emitted inside this region.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_shape
     */
    fun getEmissionShape(): ParticleProcessMaterial.EmissionShape {
        checkOpen()
        return ParticleProcessMaterial.EmissionShape(ObjectCalls.ptrcallNoArgsRetLong(Binds.getEmissionShapeBind, segment))
    }

    /**
     * The sphere's radius if `emission_shape` is set to `EmissionShape.SPHERE`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_sphere_radius
     */
    fun setEmissionSphereRadius(radius: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setEmissionSphereRadiusBind, segment, radius)
    }

    /**
     * The sphere's radius if `emission_shape` is set to `EmissionShape.SPHERE`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_sphere_radius
     */
    fun getEmissionSphereRadius(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getEmissionSphereRadiusBind, segment)
    }

    /**
     * The box's extents if `emission_shape` is set to `EmissionShape.BOX`. Note:
     * `emission_box_extents` starts from the center point and applies the X, Y, and Z values in both
     * directions. The size is twice the area of the extents.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_box_extents
     */
    fun setEmissionBoxExtents(extents: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setEmissionBoxExtentsBind, segment, extents)
    }

    /**
     * The box's extents if `emission_shape` is set to `EmissionShape.BOX`. Note:
     * `emission_box_extents` starts from the center point and applies the X, Y, and Z values in both
     * directions. The size is twice the area of the extents.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_box_extents
     */
    fun getEmissionBoxExtents(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getEmissionBoxExtentsBind, segment)
    }

    /**
     * Particles will be emitted at positions determined by sampling this texture at a random position.
     * Used with `EmissionShape.POINTS` and `EmissionShape.DIRECTED_POINTS`. Can be created
     * automatically from mesh or node by selecting "Create Emission Points from Mesh/Node" under the
     * "Particles" tool in the toolbar.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_point_texture
     */
    fun setEmissionPointTexture(texture: Texture2D?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setEmissionPointTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Particles will be emitted at positions determined by sampling this texture at a random position.
     * Used with `EmissionShape.POINTS` and `EmissionShape.DIRECTED_POINTS`. Can be created
     * automatically from mesh or node by selecting "Create Emission Points from Mesh/Node" under the
     * "Particles" tool in the toolbar.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_point_texture
     */
    fun getEmissionPointTexture(): Texture2D? {
        checkOpen()
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getEmissionPointTextureBind, segment))
    }

    /**
     * Particle velocity and rotation will be set by sampling this texture at the same point as the
     * `emission_point_texture`. Used only in `EmissionShape.DIRECTED_POINTS`. Can be created
     * automatically from mesh or node by selecting "Create Emission Points from Mesh/Node" under the
     * "Particles" tool in the toolbar.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_normal_texture
     */
    fun setEmissionNormalTexture(texture: Texture2D?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setEmissionNormalTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Particle velocity and rotation will be set by sampling this texture at the same point as the
     * `emission_point_texture`. Used only in `EmissionShape.DIRECTED_POINTS`. Can be created
     * automatically from mesh or node by selecting "Create Emission Points from Mesh/Node" under the
     * "Particles" tool in the toolbar.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_normal_texture
     */
    fun getEmissionNormalTexture(): Texture2D? {
        checkOpen()
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getEmissionNormalTextureBind, segment))
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
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setEmissionColorTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
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
        checkOpen()
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getEmissionColorTextureBind, segment))
    }

    /**
     * The number of emission points if `emission_shape` is set to `EmissionShape.POINTS` or
     * `EmissionShape.DIRECTED_POINTS`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_point_count
     */
    fun setEmissionPointCount(pointCount: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setEmissionPointCountBind, segment, pointCount)
    }

    /**
     * The number of emission points if `emission_shape` is set to `EmissionShape.POINTS` or
     * `EmissionShape.DIRECTED_POINTS`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_point_count
     */
    fun getEmissionPointCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getEmissionPointCountBind, segment)
    }

    /**
     * The axis of the ring when using the emitter `EmissionShape.RING`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_ring_axis
     */
    fun setEmissionRingAxis(axis: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setEmissionRingAxisBind, segment, axis)
    }

    /**
     * The axis of the ring when using the emitter `EmissionShape.RING`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_ring_axis
     */
    fun getEmissionRingAxis(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getEmissionRingAxisBind, segment)
    }

    /**
     * The height of the ring when using the emitter `EmissionShape.RING`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_ring_height
     */
    fun setEmissionRingHeight(height: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setEmissionRingHeightBind, segment, height)
    }

    /**
     * The height of the ring when using the emitter `EmissionShape.RING`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_ring_height
     */
    fun getEmissionRingHeight(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getEmissionRingHeightBind, segment)
    }

    /**
     * The radius of the ring when using the emitter `EmissionShape.RING`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_ring_radius
     */
    fun setEmissionRingRadius(radius: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setEmissionRingRadiusBind, segment, radius)
    }

    /**
     * The radius of the ring when using the emitter `EmissionShape.RING`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_ring_radius
     */
    fun getEmissionRingRadius(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getEmissionRingRadiusBind, segment)
    }

    /**
     * The inner radius of the ring when using the emitter `EmissionShape.RING`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_ring_inner_radius
     */
    fun setEmissionRingInnerRadius(innerRadius: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setEmissionRingInnerRadiusBind, segment, innerRadius)
    }

    /**
     * The inner radius of the ring when using the emitter `EmissionShape.RING`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_ring_inner_radius
     */
    fun getEmissionRingInnerRadius(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getEmissionRingInnerRadiusBind, segment)
    }

    /**
     * The angle of the cone when using the emitter `EmissionShape.RING`. The default angle of 90
     * degrees results in a ring, while an angle of 0 degrees results in a cone. Intermediate values
     * will result in a ring where one end is larger than the other. Note: Depending on
     * `emission_ring_height`, the angle may be clamped if the ring's end is reached to form a perfect
     * cone.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_ring_cone_angle
     */
    fun setEmissionRingConeAngle(coneAngle: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setEmissionRingConeAngleBind, segment, coneAngle)
    }

    /**
     * The angle of the cone when using the emitter `EmissionShape.RING`. The default angle of 90
     * degrees results in a ring, while an angle of 0 degrees results in a cone. Intermediate values
     * will result in a ring where one end is larger than the other. Note: Depending on
     * `emission_ring_height`, the angle may be clamped if the ring's end is reached to form a perfect
     * cone.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_ring_cone_angle
     */
    fun getEmissionRingConeAngle(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getEmissionRingConeAngleBind, segment)
    }

    /**
     * The offset for the `emission_shape`, in local space.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_shape_offset
     */
    fun setEmissionShapeOffset(emissionShapeOffset: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setEmissionShapeOffsetBind, segment, emissionShapeOffset)
    }

    /**
     * The offset for the `emission_shape`, in local space.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_shape_offset
     */
    fun getEmissionShapeOffset(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getEmissionShapeOffsetBind, segment)
    }

    /**
     * The scale of the `emission_shape`, in local space.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_emission_shape_scale
     */
    fun setEmissionShapeScale(emissionShapeScale: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setEmissionShapeScaleBind, segment, emissionShapeScale)
    }

    /**
     * The scale of the `emission_shape`, in local space.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_emission_shape_scale
     */
    fun getEmissionShapeScale(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getEmissionShapeScaleBind, segment)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getTurbulenceEnabledBind, segment)
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
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setTurbulenceEnabledBind, segment, turbulenceEnabled)
    }

    /**
     * The turbulence noise strength. Increasing this will result in a stronger, more contrasting, flow
     * pattern.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_turbulence_noise_strength
     */
    fun getTurbulenceNoiseStrength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getTurbulenceNoiseStrengthBind, segment)
    }

    /**
     * The turbulence noise strength. Increasing this will result in a stronger, more contrasting, flow
     * pattern.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_turbulence_noise_strength
     */
    fun setTurbulenceNoiseStrength(turbulenceNoiseStrength: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setTurbulenceNoiseStrengthBind, segment, turbulenceNoiseStrength)
    }

    /**
     * This value controls the overall scale/frequency of the turbulence noise pattern. A small scale
     * will result in smaller features with more detail while a high scale will result in smoother
     * noise with larger features.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_turbulence_noise_scale
     */
    fun getTurbulenceNoiseScale(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getTurbulenceNoiseScaleBind, segment)
    }

    /**
     * This value controls the overall scale/frequency of the turbulence noise pattern. A small scale
     * will result in smaller features with more detail while a high scale will result in smoother
     * noise with larger features.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_turbulence_noise_scale
     */
    fun setTurbulenceNoiseScale(turbulenceNoiseScale: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setTurbulenceNoiseScaleBind, segment, turbulenceNoiseScale)
    }

    /**
     * The in-place rate of change of the turbulence field. This defines how quickly the noise pattern
     * varies over time. A value of 0.0 will result in a fixed pattern.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_turbulence_noise_speed_random
     */
    fun getTurbulenceNoiseSpeedRandom(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getTurbulenceNoiseSpeedRandomBind, segment)
    }

    /**
     * The in-place rate of change of the turbulence field. This defines how quickly the noise pattern
     * varies over time. A value of 0.0 will result in a fixed pattern.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_turbulence_noise_speed_random
     */
    fun setTurbulenceNoiseSpeedRandom(turbulenceNoiseSpeedRandom: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setTurbulenceNoiseSpeedRandomBind, segment, turbulenceNoiseSpeedRandom)
    }

    /**
     * A scrolling velocity for the turbulence field. This sets a directional trend for the pattern to
     * move in over time. The default value of `Vector3(0, 0, 0)` turns off the scrolling.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_turbulence_noise_speed
     */
    fun getTurbulenceNoiseSpeed(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getTurbulenceNoiseSpeedBind, segment)
    }

    /**
     * A scrolling velocity for the turbulence field. This sets a directional trend for the pattern to
     * move in over time. The default value of `Vector3(0, 0, 0)` turns off the scrolling.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_turbulence_noise_speed
     */
    fun setTurbulenceNoiseSpeed(turbulenceNoiseSpeed: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setTurbulenceNoiseSpeedBind, segment, turbulenceNoiseSpeed)
    }

    /**
     * Gravity applied to every particle.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_gravity
     */
    fun getGravity(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getGravityBind, segment)
    }

    /**
     * Gravity applied to every particle.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_gravity
     */
    fun setGravity(accelVec: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setGravityBind, segment, accelVec)
    }

    /**
     * Particle lifetime randomness ratio. The equation for the lifetime of a particle is `lifetime *
     * (1.0 - randf() * lifetime_randomness)`. For example, a `lifetime_randomness` of `0.4` scales the
     * lifetime between `0.6` to `1.0` of its original value.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_lifetime_randomness
     */
    fun setLifetimeRandomness(randomness: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setLifetimeRandomnessBind, segment, randomness)
    }

    /**
     * Particle lifetime randomness ratio. The equation for the lifetime of a particle is `lifetime *
     * (1.0 - randf() * lifetime_randomness)`. For example, a `lifetime_randomness` of `0.4` scales the
     * lifetime between `0.6` to `1.0` of its original value.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_lifetime_randomness
     */
    fun getLifetimeRandomness(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLifetimeRandomnessBind, segment)
    }

    /**
     * The particle subemitter mode (see `GPUParticles2D.sub_emitter` and
     * `GPUParticles3D.sub_emitter`).
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_sub_emitter_mode
     */
    fun getSubEmitterMode(): ParticleProcessMaterial.SubEmitterMode {
        checkOpen()
        return ParticleProcessMaterial.SubEmitterMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getSubEmitterModeBind, segment))
    }

    /**
     * The particle subemitter mode (see `GPUParticles2D.sub_emitter` and
     * `GPUParticles3D.sub_emitter`).
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_sub_emitter_mode
     */
    fun setSubEmitterMode(mode: ParticleProcessMaterial.SubEmitterMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setSubEmitterModeBind, segment, mode.value)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSubEmitterFrequencyBind, segment)
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
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSubEmitterFrequencyBind, segment, hz)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSubEmitterAmountAtEndBind, segment)
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
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSubEmitterAmountAtEndBind, segment, amount)
    }

    /**
     * The amount of particles to spawn from the subemitter node when a collision occurs. When combined
     * with `CollisionMode.HIDE_ON_CONTACT` on the main particles material, this can be used to achieve
     * effects such as raindrops hitting the ground. Note: This value shouldn't exceed
     * `GPUParticles2D.amount` or `GPUParticles3D.amount` defined on the subemitter node (not the main
     * node), relative to the subemitter's particle lifetime. If the number of particles is exceeded,
     * no new particles will spawn from the subemitter until enough particles have expired.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_sub_emitter_amount_at_collision
     */
    fun getSubEmitterAmountAtCollision(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSubEmitterAmountAtCollisionBind, segment)
    }

    /**
     * The amount of particles to spawn from the subemitter node when a collision occurs. When combined
     * with `CollisionMode.HIDE_ON_CONTACT` on the main particles material, this can be used to achieve
     * effects such as raindrops hitting the ground. Note: This value shouldn't exceed
     * `GPUParticles2D.amount` or `GPUParticles3D.amount` defined on the subemitter node (not the main
     * node), relative to the subemitter's particle lifetime. If the number of particles is exceeded,
     * no new particles will spawn from the subemitter until enough particles have expired.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_sub_emitter_amount_at_collision
     */
    fun setSubEmitterAmountAtCollision(amount: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSubEmitterAmountAtCollisionBind, segment, amount)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSubEmitterAmountAtStartBind, segment)
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
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSubEmitterAmountAtStartBind, segment, amount)
    }

    /**
     * If `true`, the subemitter inherits the parent particle's velocity when it spawns.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_sub_emitter_keep_velocity
     */
    fun getSubEmitterKeepVelocity(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getSubEmitterKeepVelocityBind, segment)
    }

    /**
     * If `true`, the subemitter inherits the parent particle's velocity when it spawns.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_sub_emitter_keep_velocity
     */
    fun setSubEmitterKeepVelocity(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setSubEmitterKeepVelocityBind, segment, enable)
    }

    /**
     * If `true`, interaction with particle attractors is enabled. In 3D, attraction only occurs within
     * the area defined by the `GPUParticles3D` node's `GPUParticles3D.visibility_aabb`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_attractor_interaction_enabled
     */
    fun setAttractorInteractionEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setAttractorInteractionEnabledBind, segment, enabled)
    }

    /**
     * If `true`, interaction with particle attractors is enabled. In 3D, attraction only occurs within
     * the area defined by the `GPUParticles3D` node's `GPUParticles3D.visibility_aabb`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.is_attractor_interaction_enabled
     */
    fun isAttractorInteractionEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAttractorInteractionEnabledBind, segment)
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
    fun setCollisionMode(mode: ParticleProcessMaterial.CollisionMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setCollisionModeBind, segment, mode.value)
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
    fun getCollisionMode(): ParticleProcessMaterial.CollisionMode {
        checkOpen()
        return ParticleProcessMaterial.CollisionMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getCollisionModeBind, segment))
    }

    /**
     * If `true`, `GPUParticles3D.collision_base_size` is multiplied by the particle's effective scale
     * (see `scale_min`, `scale_max`, `scale_curve`, and `scale_over_velocity_curve`).
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_collision_use_scale
     */
    fun setCollisionUseScale(radius: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setCollisionUseScaleBind, segment, radius)
    }

    /**
     * If `true`, `GPUParticles3D.collision_base_size` is multiplied by the particle's effective scale
     * (see `scale_min`, `scale_max`, `scale_curve`, and `scale_over_velocity_curve`).
     *
     * Generated from Godot docs: ParticleProcessMaterial.is_collision_using_scale
     */
    fun isCollisionUsingScale(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCollisionUsingScaleBind, segment)
    }

    /**
     * The particles' friction. Values range from `0` (frictionless) to `1` (maximum friction). Only
     * effective if `collision_mode` is `CollisionMode.RIGID`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_collision_friction
     */
    fun setCollisionFriction(friction: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setCollisionFrictionBind, segment, friction)
    }

    /**
     * The particles' friction. Values range from `0` (frictionless) to `1` (maximum friction). Only
     * effective if `collision_mode` is `CollisionMode.RIGID`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_collision_friction
     */
    fun getCollisionFriction(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCollisionFrictionBind, segment)
    }

    /**
     * The particles' bounciness. Values range from `0` (no bounce) to `1` (full bounciness). Only
     * effective if `collision_mode` is `CollisionMode.RIGID`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_collision_bounce
     */
    fun setCollisionBounce(bounce: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setCollisionBounceBind, segment, bounce)
    }

    /**
     * The particles' bounciness. Values range from `0` (no bounce) to `1` (full bounciness). Only
     * effective if `collision_mode` is `CollisionMode.RIGID`.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_collision_bounce
     */
    fun getCollisionBounce(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCollisionBounceBind, segment)
    }

    /**
     * Enable 3D rotation velocity.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_using_rotation_velocity_3d
     */
    fun setUsingRotationVelocity3d(useRotationVelocity3d: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setUsingRotationVelocity3dBind, segment, useRotationVelocity3d)
    }

    /**
     * Enable 3D rotation velocity.
     *
     * Generated from Godot docs: ParticleProcessMaterial.is_using_rotation_velocity_3d
     */
    fun isUsingRotationVelocity3d(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isUsingRotationVelocity3dBind, segment)
    }

    /**
     * Maximum 3D rotation velocity on the particle's local axis. Enable `use_rotation_velocity_3d` to
     * use this.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_rotation_velocity_3d_max
     */
    fun setRotationVelocity3dMax(rotationVelocity3dMax: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setRotationVelocity3dMaxBind, segment, rotationVelocity3dMax)
    }

    /**
     * Maximum 3D rotation velocity on the particle's local axis. Enable `use_rotation_velocity_3d` to
     * use this.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_rotation_velocity_3d_max
     */
    fun getRotationVelocity3dMax(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getRotationVelocity3dMaxBind, segment)
    }

    /**
     * Minimum 3D rotation velocity on the particle's local axis. Enable `use_rotation_velocity_3d` to
     * use this.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_rotation_velocity_3d_min
     */
    fun setRotationVelocity3dMin(rotationVelocity3dMin: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setRotationVelocity3dMinBind, segment, rotationVelocity3dMin)
    }

    /**
     * Minimum 3D rotation velocity on the particle's local axis. Enable `use_rotation_velocity_3d` to
     * use this.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_rotation_velocity_3d_min
     */
    fun getRotationVelocity3dMin(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getRotationVelocity3dMinBind, segment)
    }

    /**
     * Rotation velocity curve over lifetime, per-axis. Enable `use_rotation_velocity_3d` to use this.
     *
     * Generated from Godot docs: ParticleProcessMaterial.set_rotation_velocity_3d_curve
     */
    fun setRotationVelocity3dCurve(rotationVelocity3dCurve: Texture2D?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setRotationVelocity3dCurveBind, segment, listOf(rotationVelocity3dCurve?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Rotation velocity curve over lifetime, per-axis. Enable `use_rotation_velocity_3d` to use this.
     *
     * Generated from Godot docs: ParticleProcessMaterial.get_rotation_velocity_3d_curve
     */
    fun getRotationVelocity3dCurve(): Texture2D? {
        checkOpen()
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getRotationVelocity3dCurveBind, segment))
    }

    /** Signal `emission_shape_changed()`; see [TypedSignal]. */
    val emissionShapeChanged: Signal0
        @JvmName("emissionShapeChangedTypedSignal")
        get() = Signal0(this, "emission_shape_changed")

    object Signals {
        const val emissionShapeChanged: String = "emission_shape_changed"
    }

    /**
     * Godot's `ParticleProcessMaterial.Parameter` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`ParticleProcessMaterial.Parameter.<NAME>`).
     *
     * Generated from Godot docs: ParticleProcessMaterial.Parameter
     */
    @JvmInline
    value class Parameter(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_texture` to set initial velocity
             * properties.
             *
             * Generated from Godot docs: ParticleProcessMaterial.PARAM_INITIAL_LINEAR_VELOCITY
             */
            val INITIAL_LINEAR_VELOCITY: Parameter get() = Parameter(0L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_texture` to set angular velocity
             * properties.
             *
             * Generated from Godot docs: ParticleProcessMaterial.PARAM_ANGULAR_VELOCITY
             */
            val ANGULAR_VELOCITY: Parameter get() = Parameter(1L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_texture` to set orbital velocity
             * properties.
             *
             * Generated from Godot docs: ParticleProcessMaterial.PARAM_ORBIT_VELOCITY
             */
            val ORBIT_VELOCITY: Parameter get() = Parameter(2L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_texture` to set linear acceleration
             * properties.
             *
             * Generated from Godot docs: ParticleProcessMaterial.PARAM_LINEAR_ACCEL
             */
            val LINEAR_ACCEL: Parameter get() = Parameter(3L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_texture` to set radial acceleration
             * properties.
             *
             * Generated from Godot docs: ParticleProcessMaterial.PARAM_RADIAL_ACCEL
             */
            val RADIAL_ACCEL: Parameter get() = Parameter(4L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_texture` to set tangential
             * acceleration properties.
             *
             * Generated from Godot docs: ParticleProcessMaterial.PARAM_TANGENTIAL_ACCEL
             */
            val TANGENTIAL_ACCEL: Parameter get() = Parameter(5L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_texture` to set damping properties.
             *
             * Generated from Godot docs: ParticleProcessMaterial.PARAM_DAMPING
             */
            val DAMPING: Parameter get() = Parameter(6L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_texture` to set angle properties.
             *
             * Generated from Godot docs: ParticleProcessMaterial.PARAM_ANGLE
             */
            val ANGLE: Parameter get() = Parameter(7L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_texture` to set scale properties.
             *
             * Generated from Godot docs: ParticleProcessMaterial.PARAM_SCALE
             */
            val SCALE: Parameter get() = Parameter(8L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_texture` to set hue variation
             * properties.
             *
             * Generated from Godot docs: ParticleProcessMaterial.PARAM_HUE_VARIATION
             */
            val HUE_VARIATION: Parameter get() = Parameter(9L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_texture` to set animation speed
             * properties.
             *
             * Generated from Godot docs: ParticleProcessMaterial.PARAM_ANIM_SPEED
             */
            val ANIM_SPEED: Parameter get() = Parameter(10L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_texture` to set animation offset
             * properties.
             *
             * Generated from Godot docs: ParticleProcessMaterial.PARAM_ANIM_OFFSET
             */
            val ANIM_OFFSET: Parameter get() = Parameter(11L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_texture` to set radial velocity
             * properties.
             *
             * Generated from Godot docs: ParticleProcessMaterial.PARAM_RADIAL_VELOCITY
             */
            val RADIAL_VELOCITY: Parameter get() = Parameter(15L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_texture` to set directional velocity
             * properties.
             *
             * Generated from Godot docs: ParticleProcessMaterial.PARAM_DIRECTIONAL_VELOCITY
             */
            val DIRECTIONAL_VELOCITY: Parameter get() = Parameter(16L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_texture` to set scale over velocity
             * properties.
             *
             * Generated from Godot docs: ParticleProcessMaterial.PARAM_SCALE_OVER_VELOCITY
             */
            val SCALE_OVER_VELOCITY: Parameter get() = Parameter(17L)
            /**
             * Represents the size of the `Parameter` enum.
             *
             * Generated from Godot docs: ParticleProcessMaterial.PARAM_MAX
             */
            val MAX: Parameter get() = Parameter(18L)
            /**
             * Use with `set_param_min` and `set_param_max` to set the turbulence minimum und maximum influence
             * on each particles velocity.
             *
             * Generated from Godot docs: ParticleProcessMaterial.PARAM_TURB_VEL_INFLUENCE
             */
            val TURB_VEL_INFLUENCE: Parameter get() = Parameter(13L)
            /**
             * Use with `set_param_min` and `set_param_max` to set the turbulence minimum and maximum
             * displacement of the particles spawn position.
             *
             * Generated from Godot docs: ParticleProcessMaterial.PARAM_TURB_INIT_DISPLACEMENT
             */
            val TURB_INIT_DISPLACEMENT: Parameter get() = Parameter(14L)
            /**
             * Use with `set_param_texture` to set the turbulence influence over the particles life time.
             *
             * Generated from Godot docs: ParticleProcessMaterial.PARAM_TURB_INFLUENCE_OVER_LIFE
             */
            val TURB_INFLUENCE_OVER_LIFE: Parameter get() = Parameter(12L)
        }
    }

    /**
     * Godot's `ParticleProcessMaterial.ParticleFlags` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`ParticleProcessMaterial.ParticleFlags.<NAME>`).
     *
     * Generated from Godot docs: ParticleProcessMaterial.ParticleFlags
     */
    @JvmInline
    value class ParticleFlags(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Use with `set_particle_flag` to set `particle_flag_align_y`.
             *
             * Generated from Godot docs: ParticleProcessMaterial.PARTICLE_FLAG_ALIGN_Y_TO_VELOCITY
             */
            val ALIGN_Y_TO_VELOCITY: ParticleFlags get() = ParticleFlags(0L)
            /**
             * Use with `set_particle_flag` to set `particle_flag_rotate_y`.
             *
             * Generated from Godot docs: ParticleProcessMaterial.PARTICLE_FLAG_ROTATE_Y
             */
            val ROTATE_Y: ParticleFlags get() = ParticleFlags(1L)
            /**
             * Use with `set_particle_flag` to set `particle_flag_disable_z`.
             *
             * Generated from Godot docs: ParticleProcessMaterial.PARTICLE_FLAG_DISABLE_Z
             */
            val DISABLE_Z: ParticleFlags get() = ParticleFlags(2L)
            val DAMPING_AS_FRICTION: ParticleFlags get() = ParticleFlags(3L)
            val INHERIT_EMITTER_SCALE: ParticleFlags get() = ParticleFlags(4L)
            /**
             * Represents the size of the `ParticleFlags` enum.
             *
             * Generated from Godot docs: ParticleProcessMaterial.PARTICLE_FLAG_MAX
             */
            val MAX: ParticleFlags get() = ParticleFlags(5L)
        }
    }

    /**
     * Godot's `ParticleProcessMaterial.EmissionShape` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`ParticleProcessMaterial.EmissionShape.<NAME>`).
     *
     * Generated from Godot docs: ParticleProcessMaterial.EmissionShape
     */
    @JvmInline
    value class EmissionShape(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * All particles will be emitted from a single point.
             *
             * Generated from Godot docs: ParticleProcessMaterial.EMISSION_SHAPE_POINT
             */
            val POINT: EmissionShape get() = EmissionShape(0L)
            /**
             * Particles will be emitted in the volume of a sphere.
             *
             * Generated from Godot docs: ParticleProcessMaterial.EMISSION_SHAPE_SPHERE
             */
            val SPHERE: EmissionShape get() = EmissionShape(1L)
            /**
             * Particles will be emitted on the surface of a sphere.
             *
             * Generated from Godot docs: ParticleProcessMaterial.EMISSION_SHAPE_SPHERE_SURFACE
             */
            val SPHERE_SURFACE: EmissionShape get() = EmissionShape(2L)
            /**
             * Particles will be emitted in the volume of a box.
             *
             * Generated from Godot docs: ParticleProcessMaterial.EMISSION_SHAPE_BOX
             */
            val BOX: EmissionShape get() = EmissionShape(3L)
            /**
             * Particles will be emitted at a position determined by sampling a random point on the
             * `emission_point_texture`. Particle color will be modulated by `emission_color_texture`.
             *
             * Generated from Godot docs: ParticleProcessMaterial.EMISSION_SHAPE_POINTS
             */
            val POINTS: EmissionShape get() = EmissionShape(4L)
            /**
             * Particles will be emitted at a position determined by sampling a random point on the
             * `emission_point_texture`. Particle velocity and rotation will be set based on
             * `emission_normal_texture`. Particle color will be modulated by `emission_color_texture`.
             *
             * Generated from Godot docs: ParticleProcessMaterial.EMISSION_SHAPE_DIRECTED_POINTS
             */
            val DIRECTED_POINTS: EmissionShape get() = EmissionShape(5L)
            /**
             * Particles will be emitted in a ring or cylinder.
             *
             * Generated from Godot docs: ParticleProcessMaterial.EMISSION_SHAPE_RING
             */
            val RING: EmissionShape get() = EmissionShape(6L)
            /**
             * Represents the size of the `EmissionShape` enum.
             *
             * Generated from Godot docs: ParticleProcessMaterial.EMISSION_SHAPE_MAX
             */
            val MAX: EmissionShape get() = EmissionShape(7L)
        }
    }

    /**
     * Godot's `ParticleProcessMaterial.SubEmitterMode` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`ParticleProcessMaterial.SubEmitterMode.<NAME>`).
     *
     * Generated from Godot docs: ParticleProcessMaterial.SubEmitterMode
     */
    @JvmInline
    value class SubEmitterMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The subemitter is disabled.
             *
             * Generated from Godot docs: ParticleProcessMaterial.SUB_EMITTER_DISABLED
             */
            val DISABLED: SubEmitterMode get() = SubEmitterMode(0L)
            /**
             * The submitter is emitted on the constant interval defined by `sub_emitter_frequency`.
             *
             * Generated from Godot docs: ParticleProcessMaterial.SUB_EMITTER_CONSTANT
             */
            val CONSTANT: SubEmitterMode get() = SubEmitterMode(1L)
            /**
             * The subemitter is emitted at the end of the particle's lifetime.
             *
             * Generated from Godot docs: ParticleProcessMaterial.SUB_EMITTER_AT_END
             */
            val AT_END: SubEmitterMode get() = SubEmitterMode(2L)
            /**
             * The subemitter is emitted when the particle collides.
             *
             * Generated from Godot docs: ParticleProcessMaterial.SUB_EMITTER_AT_COLLISION
             */
            val AT_COLLISION: SubEmitterMode get() = SubEmitterMode(3L)
            /**
             * The subemitter is emitted when the particle spawns.
             *
             * Generated from Godot docs: ParticleProcessMaterial.SUB_EMITTER_AT_START
             */
            val AT_START: SubEmitterMode get() = SubEmitterMode(4L)
            /**
             * Represents the size of the `SubEmitterMode` enum.
             *
             * Generated from Godot docs: ParticleProcessMaterial.SUB_EMITTER_MAX
             */
            val MAX: SubEmitterMode get() = SubEmitterMode(5L)
        }
    }

    /**
     * Godot's `ParticleProcessMaterial.CollisionMode` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`ParticleProcessMaterial.CollisionMode.<NAME>`).
     *
     * Generated from Godot docs: ParticleProcessMaterial.CollisionMode
     */
    @JvmInline
    value class CollisionMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * No collision for particles. Particles will go through `GPUParticlesCollision3D` nodes.
             *
             * Generated from Godot docs: ParticleProcessMaterial.COLLISION_DISABLED
             */
            val DISABLED: CollisionMode get() = CollisionMode(0L)
            /**
             * `RigidBody3D`-style collision for particles using `GPUParticlesCollision3D` nodes.
             *
             * Generated from Godot docs: ParticleProcessMaterial.COLLISION_RIGID
             */
            val RIGID: CollisionMode get() = CollisionMode(1L)
            /**
             * Hide particles instantly when colliding with a `GPUParticlesCollision3D` node. This can be
             * combined with a subemitter that uses the `CollisionMode.RIGID` collision mode to "replace" the
             * parent particle with the subemitter on impact.
             *
             * Generated from Godot docs: ParticleProcessMaterial.COLLISION_HIDE_ON_CONTACT
             */
            val HIDE_ON_CONTACT: CollisionMode get() = CollisionMode(2L)
            /**
             * Represents the size of the `CollisionMode` enum.
             *
             * Generated from Godot docs: ParticleProcessMaterial.COLLISION_MAX
             */
            val MAX: CollisionMode get() = CollisionMode(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ParticleProcessMaterial? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ParticleProcessMaterial? =
            if (handle.address() == 0L) null else RefCounted.owned(ParticleProcessMaterial(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ParticleProcessMaterial? =
            if (handle.address() == 0L) null else ParticleProcessMaterial(GodotHandle(handle))

        // Downcast a Resource to ParticleProcessMaterial (null if not).
        @JvmStatic
        fun fromResource(value: Resource): ParticleProcessMaterial? =
            if (value.isClass("ParticleProcessMaterial")) RefCounted.retained(ParticleProcessMaterial(value.handle)) else null
    }

    private object Binds {
        private const val SET_DIRECTION_HASH = 3460891852L
        @JvmField
        val setDirectionBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_direction", SET_DIRECTION_HASH)

        private const val GET_DIRECTION_HASH = 3360562783L
        @JvmField
        val getDirectionBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_direction", GET_DIRECTION_HASH)

        private const val SET_INHERIT_VELOCITY_RATIO_HASH = 373806689L
        @JvmField
        val setInheritVelocityRatioBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_inherit_velocity_ratio", SET_INHERIT_VELOCITY_RATIO_HASH)

        private const val GET_INHERIT_VELOCITY_RATIO_HASH = 191475506L
        @JvmField
        val getInheritVelocityRatioBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_inherit_velocity_ratio", GET_INHERIT_VELOCITY_RATIO_HASH)

        private const val SET_SPREAD_HASH = 373806689L
        @JvmField
        val setSpreadBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_spread", SET_SPREAD_HASH)

        private const val GET_SPREAD_HASH = 1740695150L
        @JvmField
        val getSpreadBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_spread", GET_SPREAD_HASH)

        private const val SET_FLATNESS_HASH = 373806689L
        @JvmField
        val setFlatnessBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_flatness", SET_FLATNESS_HASH)

        private const val GET_FLATNESS_HASH = 1740695150L
        @JvmField
        val getFlatnessBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_flatness", GET_FLATNESS_HASH)

        private const val SET_PARAM_HASH = 676779352L
        @JvmField
        val setParamBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_param", SET_PARAM_HASH)

        private const val GET_PARAM_HASH = 2623708480L
        @JvmField
        val getParamBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_param", GET_PARAM_HASH)

        private const val SET_PARAM_MIN_HASH = 2295964248L
        @JvmField
        val setParamMinBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_param_min", SET_PARAM_MIN_HASH)

        private const val GET_PARAM_MIN_HASH = 3903786503L
        @JvmField
        val getParamMinBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_param_min", GET_PARAM_MIN_HASH)

        private const val SET_PARAM_MAX_HASH = 2295964248L
        @JvmField
        val setParamMaxBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_param_max", SET_PARAM_MAX_HASH)

        private const val GET_PARAM_MAX_HASH = 3903786503L
        @JvmField
        val getParamMaxBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_param_max", GET_PARAM_MAX_HASH)

        private const val SET_PARAM_TEXTURE_HASH = 526976089L
        @JvmField
        val setParamTextureBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_param_texture", SET_PARAM_TEXTURE_HASH)

        private const val GET_PARAM_TEXTURE_HASH = 3489372978L
        @JvmField
        val getParamTextureBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_param_texture", GET_PARAM_TEXTURE_HASH)

        private const val SET_COLOR_HASH = 2920490490L
        @JvmField
        val setColorBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_color", SET_COLOR_HASH)

        private const val GET_COLOR_HASH = 3444240500L
        @JvmField
        val getColorBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_color", GET_COLOR_HASH)

        private const val SET_USE_SCALE_3D_HASH = 2586408642L
        @JvmField
        val setUseScale3dBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_use_scale_3d", SET_USE_SCALE_3D_HASH)

        private const val IS_USING_SCALE_3D_HASH = 36873697L
        @JvmField
        val isUsingScale3dBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "is_using_scale_3d", IS_USING_SCALE_3D_HASH)

        private const val SET_SCALE_3D_MIN_HASH = 3460891852L
        @JvmField
        val setScale3dMinBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_scale_3d_min", SET_SCALE_3D_MIN_HASH)

        private const val GET_SCALE_3D_MIN_HASH = 3360562783L
        @JvmField
        val getScale3dMinBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_scale_3d_min", GET_SCALE_3D_MIN_HASH)

        private const val SET_SCALE_3D_MAX_HASH = 3460891852L
        @JvmField
        val setScale3dMaxBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_scale_3d_max", SET_SCALE_3D_MAX_HASH)

        private const val GET_SCALE_3D_MAX_HASH = 3360562783L
        @JvmField
        val getScale3dMaxBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_scale_3d_max", GET_SCALE_3D_MAX_HASH)

        private const val SET_USE_ROTATION_3D_HASH = 2586408642L
        @JvmField
        val setUseRotation3dBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_use_rotation_3d", SET_USE_ROTATION_3D_HASH)

        private const val IS_USING_ROTATION_3D_HASH = 36873697L
        @JvmField
        val isUsingRotation3dBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "is_using_rotation_3d", IS_USING_ROTATION_3D_HASH)

        private const val SET_ROTATION_3D_MIN_HASH = 3460891852L
        @JvmField
        val setRotation3dMinBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_rotation_3d_min", SET_ROTATION_3D_MIN_HASH)

        private const val GET_ROTATION_3D_MIN_HASH = 3360562783L
        @JvmField
        val getRotation3dMinBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_rotation_3d_min", GET_ROTATION_3D_MIN_HASH)

        private const val SET_ROTATION_3D_MAX_HASH = 3460891852L
        @JvmField
        val setRotation3dMaxBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_rotation_3d_max", SET_ROTATION_3D_MAX_HASH)

        private const val GET_ROTATION_3D_MAX_HASH = 3360562783L
        @JvmField
        val getRotation3dMaxBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_rotation_3d_max", GET_ROTATION_3D_MAX_HASH)

        private const val SET_COLOR_RAMP_HASH = 4051416890L
        @JvmField
        val setColorRampBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_color_ramp", SET_COLOR_RAMP_HASH)

        private const val GET_COLOR_RAMP_HASH = 3635182373L
        @JvmField
        val getColorRampBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_color_ramp", GET_COLOR_RAMP_HASH)

        private const val SET_ALPHA_CURVE_HASH = 4051416890L
        @JvmField
        val setAlphaCurveBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_alpha_curve", SET_ALPHA_CURVE_HASH)

        private const val GET_ALPHA_CURVE_HASH = 3635182373L
        @JvmField
        val getAlphaCurveBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_alpha_curve", GET_ALPHA_CURVE_HASH)

        private const val SET_EMISSION_CURVE_HASH = 4051416890L
        @JvmField
        val setEmissionCurveBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_curve", SET_EMISSION_CURVE_HASH)

        private const val GET_EMISSION_CURVE_HASH = 3635182373L
        @JvmField
        val getEmissionCurveBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_curve", GET_EMISSION_CURVE_HASH)

        private const val SET_COLOR_INITIAL_RAMP_HASH = 4051416890L
        @JvmField
        val setColorInitialRampBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_color_initial_ramp", SET_COLOR_INITIAL_RAMP_HASH)

        private const val GET_COLOR_INITIAL_RAMP_HASH = 3635182373L
        @JvmField
        val getColorInitialRampBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_color_initial_ramp", GET_COLOR_INITIAL_RAMP_HASH)

        private const val SET_VELOCITY_LIMIT_CURVE_HASH = 4051416890L
        @JvmField
        val setVelocityLimitCurveBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_velocity_limit_curve", SET_VELOCITY_LIMIT_CURVE_HASH)

        private const val GET_VELOCITY_LIMIT_CURVE_HASH = 3635182373L
        @JvmField
        val getVelocityLimitCurveBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_velocity_limit_curve", GET_VELOCITY_LIMIT_CURVE_HASH)

        private const val SET_PARTICLE_FLAG_HASH = 1711815571L
        @JvmField
        val setParticleFlagBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_particle_flag", SET_PARTICLE_FLAG_HASH)

        private const val GET_PARTICLE_FLAG_HASH = 3895316907L
        @JvmField
        val getParticleFlagBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_particle_flag", GET_PARTICLE_FLAG_HASH)

        private const val SET_VELOCITY_PIVOT_HASH = 3460891852L
        @JvmField
        val setVelocityPivotBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_velocity_pivot", SET_VELOCITY_PIVOT_HASH)

        private const val GET_VELOCITY_PIVOT_HASH = 3783033775L
        @JvmField
        val getVelocityPivotBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_velocity_pivot", GET_VELOCITY_PIVOT_HASH)

        private const val SET_EMISSION_SHAPE_HASH = 461501442L
        @JvmField
        val setEmissionShapeBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_shape", SET_EMISSION_SHAPE_HASH)

        private const val GET_EMISSION_SHAPE_HASH = 3719733018L
        @JvmField
        val getEmissionShapeBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_shape", GET_EMISSION_SHAPE_HASH)

        private const val SET_EMISSION_SPHERE_RADIUS_HASH = 373806689L
        @JvmField
        val setEmissionSphereRadiusBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_sphere_radius", SET_EMISSION_SPHERE_RADIUS_HASH)

        private const val GET_EMISSION_SPHERE_RADIUS_HASH = 1740695150L
        @JvmField
        val getEmissionSphereRadiusBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_sphere_radius", GET_EMISSION_SPHERE_RADIUS_HASH)

        private const val SET_EMISSION_BOX_EXTENTS_HASH = 3460891852L
        @JvmField
        val setEmissionBoxExtentsBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_box_extents", SET_EMISSION_BOX_EXTENTS_HASH)

        private const val GET_EMISSION_BOX_EXTENTS_HASH = 3360562783L
        @JvmField
        val getEmissionBoxExtentsBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_box_extents", GET_EMISSION_BOX_EXTENTS_HASH)

        private const val SET_EMISSION_POINT_TEXTURE_HASH = 4051416890L
        @JvmField
        val setEmissionPointTextureBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_point_texture", SET_EMISSION_POINT_TEXTURE_HASH)

        private const val GET_EMISSION_POINT_TEXTURE_HASH = 3635182373L
        @JvmField
        val getEmissionPointTextureBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_point_texture", GET_EMISSION_POINT_TEXTURE_HASH)

        private const val SET_EMISSION_NORMAL_TEXTURE_HASH = 4051416890L
        @JvmField
        val setEmissionNormalTextureBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_normal_texture", SET_EMISSION_NORMAL_TEXTURE_HASH)

        private const val GET_EMISSION_NORMAL_TEXTURE_HASH = 3635182373L
        @JvmField
        val getEmissionNormalTextureBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_normal_texture", GET_EMISSION_NORMAL_TEXTURE_HASH)

        private const val SET_EMISSION_COLOR_TEXTURE_HASH = 4051416890L
        @JvmField
        val setEmissionColorTextureBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_color_texture", SET_EMISSION_COLOR_TEXTURE_HASH)

        private const val GET_EMISSION_COLOR_TEXTURE_HASH = 3635182373L
        @JvmField
        val getEmissionColorTextureBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_color_texture", GET_EMISSION_COLOR_TEXTURE_HASH)

        private const val SET_EMISSION_POINT_COUNT_HASH = 1286410249L
        @JvmField
        val setEmissionPointCountBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_point_count", SET_EMISSION_POINT_COUNT_HASH)

        private const val GET_EMISSION_POINT_COUNT_HASH = 3905245786L
        @JvmField
        val getEmissionPointCountBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_point_count", GET_EMISSION_POINT_COUNT_HASH)

        private const val SET_EMISSION_RING_AXIS_HASH = 3460891852L
        @JvmField
        val setEmissionRingAxisBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_ring_axis", SET_EMISSION_RING_AXIS_HASH)

        private const val GET_EMISSION_RING_AXIS_HASH = 3360562783L
        @JvmField
        val getEmissionRingAxisBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_ring_axis", GET_EMISSION_RING_AXIS_HASH)

        private const val SET_EMISSION_RING_HEIGHT_HASH = 373806689L
        @JvmField
        val setEmissionRingHeightBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_ring_height", SET_EMISSION_RING_HEIGHT_HASH)

        private const val GET_EMISSION_RING_HEIGHT_HASH = 1740695150L
        @JvmField
        val getEmissionRingHeightBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_ring_height", GET_EMISSION_RING_HEIGHT_HASH)

        private const val SET_EMISSION_RING_RADIUS_HASH = 373806689L
        @JvmField
        val setEmissionRingRadiusBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_ring_radius", SET_EMISSION_RING_RADIUS_HASH)

        private const val GET_EMISSION_RING_RADIUS_HASH = 1740695150L
        @JvmField
        val getEmissionRingRadiusBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_ring_radius", GET_EMISSION_RING_RADIUS_HASH)

        private const val SET_EMISSION_RING_INNER_RADIUS_HASH = 373806689L
        @JvmField
        val setEmissionRingInnerRadiusBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_ring_inner_radius", SET_EMISSION_RING_INNER_RADIUS_HASH)

        private const val GET_EMISSION_RING_INNER_RADIUS_HASH = 1740695150L
        @JvmField
        val getEmissionRingInnerRadiusBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_ring_inner_radius", GET_EMISSION_RING_INNER_RADIUS_HASH)

        private const val SET_EMISSION_RING_CONE_ANGLE_HASH = 373806689L
        @JvmField
        val setEmissionRingConeAngleBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_ring_cone_angle", SET_EMISSION_RING_CONE_ANGLE_HASH)

        private const val GET_EMISSION_RING_CONE_ANGLE_HASH = 1740695150L
        @JvmField
        val getEmissionRingConeAngleBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_ring_cone_angle", GET_EMISSION_RING_CONE_ANGLE_HASH)

        private const val SET_EMISSION_SHAPE_OFFSET_HASH = 3460891852L
        @JvmField
        val setEmissionShapeOffsetBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_shape_offset", SET_EMISSION_SHAPE_OFFSET_HASH)

        private const val GET_EMISSION_SHAPE_OFFSET_HASH = 3360562783L
        @JvmField
        val getEmissionShapeOffsetBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_shape_offset", GET_EMISSION_SHAPE_OFFSET_HASH)

        private const val SET_EMISSION_SHAPE_SCALE_HASH = 3460891852L
        @JvmField
        val setEmissionShapeScaleBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_emission_shape_scale", SET_EMISSION_SHAPE_SCALE_HASH)

        private const val GET_EMISSION_SHAPE_SCALE_HASH = 3360562783L
        @JvmField
        val getEmissionShapeScaleBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_emission_shape_scale", GET_EMISSION_SHAPE_SCALE_HASH)

        private const val GET_TURBULENCE_ENABLED_HASH = 36873697L
        @JvmField
        val getTurbulenceEnabledBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_turbulence_enabled", GET_TURBULENCE_ENABLED_HASH)

        private const val SET_TURBULENCE_ENABLED_HASH = 2586408642L
        @JvmField
        val setTurbulenceEnabledBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_turbulence_enabled", SET_TURBULENCE_ENABLED_HASH)

        private const val GET_TURBULENCE_NOISE_STRENGTH_HASH = 1740695150L
        @JvmField
        val getTurbulenceNoiseStrengthBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_turbulence_noise_strength", GET_TURBULENCE_NOISE_STRENGTH_HASH)

        private const val SET_TURBULENCE_NOISE_STRENGTH_HASH = 373806689L
        @JvmField
        val setTurbulenceNoiseStrengthBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_turbulence_noise_strength", SET_TURBULENCE_NOISE_STRENGTH_HASH)

        private const val GET_TURBULENCE_NOISE_SCALE_HASH = 1740695150L
        @JvmField
        val getTurbulenceNoiseScaleBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_turbulence_noise_scale", GET_TURBULENCE_NOISE_SCALE_HASH)

        private const val SET_TURBULENCE_NOISE_SCALE_HASH = 373806689L
        @JvmField
        val setTurbulenceNoiseScaleBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_turbulence_noise_scale", SET_TURBULENCE_NOISE_SCALE_HASH)

        private const val GET_TURBULENCE_NOISE_SPEED_RANDOM_HASH = 1740695150L
        @JvmField
        val getTurbulenceNoiseSpeedRandomBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_turbulence_noise_speed_random", GET_TURBULENCE_NOISE_SPEED_RANDOM_HASH)

        private const val SET_TURBULENCE_NOISE_SPEED_RANDOM_HASH = 373806689L
        @JvmField
        val setTurbulenceNoiseSpeedRandomBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_turbulence_noise_speed_random", SET_TURBULENCE_NOISE_SPEED_RANDOM_HASH)

        private const val GET_TURBULENCE_NOISE_SPEED_HASH = 3360562783L
        @JvmField
        val getTurbulenceNoiseSpeedBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_turbulence_noise_speed", GET_TURBULENCE_NOISE_SPEED_HASH)

        private const val SET_TURBULENCE_NOISE_SPEED_HASH = 3460891852L
        @JvmField
        val setTurbulenceNoiseSpeedBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_turbulence_noise_speed", SET_TURBULENCE_NOISE_SPEED_HASH)

        private const val GET_GRAVITY_HASH = 3360562783L
        @JvmField
        val getGravityBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_gravity", GET_GRAVITY_HASH)

        private const val SET_GRAVITY_HASH = 3460891852L
        @JvmField
        val setGravityBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_gravity", SET_GRAVITY_HASH)

        private const val SET_LIFETIME_RANDOMNESS_HASH = 373806689L
        @JvmField
        val setLifetimeRandomnessBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_lifetime_randomness", SET_LIFETIME_RANDOMNESS_HASH)

        private const val GET_LIFETIME_RANDOMNESS_HASH = 1740695150L
        @JvmField
        val getLifetimeRandomnessBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_lifetime_randomness", GET_LIFETIME_RANDOMNESS_HASH)

        private const val GET_SUB_EMITTER_MODE_HASH = 2399052877L
        @JvmField
        val getSubEmitterModeBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_sub_emitter_mode", GET_SUB_EMITTER_MODE_HASH)

        private const val SET_SUB_EMITTER_MODE_HASH = 2161806672L
        @JvmField
        val setSubEmitterModeBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_sub_emitter_mode", SET_SUB_EMITTER_MODE_HASH)

        private const val GET_SUB_EMITTER_FREQUENCY_HASH = 1740695150L
        @JvmField
        val getSubEmitterFrequencyBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_sub_emitter_frequency", GET_SUB_EMITTER_FREQUENCY_HASH)

        private const val SET_SUB_EMITTER_FREQUENCY_HASH = 373806689L
        @JvmField
        val setSubEmitterFrequencyBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_sub_emitter_frequency", SET_SUB_EMITTER_FREQUENCY_HASH)

        private const val GET_SUB_EMITTER_AMOUNT_AT_END_HASH = 3905245786L
        @JvmField
        val getSubEmitterAmountAtEndBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_sub_emitter_amount_at_end", GET_SUB_EMITTER_AMOUNT_AT_END_HASH)

        private const val SET_SUB_EMITTER_AMOUNT_AT_END_HASH = 1286410249L
        @JvmField
        val setSubEmitterAmountAtEndBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_sub_emitter_amount_at_end", SET_SUB_EMITTER_AMOUNT_AT_END_HASH)

        private const val GET_SUB_EMITTER_AMOUNT_AT_COLLISION_HASH = 3905245786L
        @JvmField
        val getSubEmitterAmountAtCollisionBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_sub_emitter_amount_at_collision", GET_SUB_EMITTER_AMOUNT_AT_COLLISION_HASH)

        private const val SET_SUB_EMITTER_AMOUNT_AT_COLLISION_HASH = 1286410249L
        @JvmField
        val setSubEmitterAmountAtCollisionBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_sub_emitter_amount_at_collision", SET_SUB_EMITTER_AMOUNT_AT_COLLISION_HASH)

        private const val GET_SUB_EMITTER_AMOUNT_AT_START_HASH = 3905245786L
        @JvmField
        val getSubEmitterAmountAtStartBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_sub_emitter_amount_at_start", GET_SUB_EMITTER_AMOUNT_AT_START_HASH)

        private const val SET_SUB_EMITTER_AMOUNT_AT_START_HASH = 1286410249L
        @JvmField
        val setSubEmitterAmountAtStartBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_sub_emitter_amount_at_start", SET_SUB_EMITTER_AMOUNT_AT_START_HASH)

        private const val GET_SUB_EMITTER_KEEP_VELOCITY_HASH = 36873697L
        @JvmField
        val getSubEmitterKeepVelocityBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_sub_emitter_keep_velocity", GET_SUB_EMITTER_KEEP_VELOCITY_HASH)

        private const val SET_SUB_EMITTER_KEEP_VELOCITY_HASH = 2586408642L
        @JvmField
        val setSubEmitterKeepVelocityBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_sub_emitter_keep_velocity", SET_SUB_EMITTER_KEEP_VELOCITY_HASH)

        private const val SET_ATTRACTOR_INTERACTION_ENABLED_HASH = 2586408642L
        @JvmField
        val setAttractorInteractionEnabledBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_attractor_interaction_enabled", SET_ATTRACTOR_INTERACTION_ENABLED_HASH)

        private const val IS_ATTRACTOR_INTERACTION_ENABLED_HASH = 36873697L
        @JvmField
        val isAttractorInteractionEnabledBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "is_attractor_interaction_enabled", IS_ATTRACTOR_INTERACTION_ENABLED_HASH)

        private const val SET_COLLISION_MODE_HASH = 653804659L
        @JvmField
        val setCollisionModeBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_collision_mode", SET_COLLISION_MODE_HASH)

        private const val GET_COLLISION_MODE_HASH = 139371864L
        @JvmField
        val getCollisionModeBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_collision_mode", GET_COLLISION_MODE_HASH)

        private const val SET_COLLISION_USE_SCALE_HASH = 2586408642L
        @JvmField
        val setCollisionUseScaleBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_collision_use_scale", SET_COLLISION_USE_SCALE_HASH)

        private const val IS_COLLISION_USING_SCALE_HASH = 36873697L
        @JvmField
        val isCollisionUsingScaleBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "is_collision_using_scale", IS_COLLISION_USING_SCALE_HASH)

        private const val SET_COLLISION_FRICTION_HASH = 373806689L
        @JvmField
        val setCollisionFrictionBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_collision_friction", SET_COLLISION_FRICTION_HASH)

        private const val GET_COLLISION_FRICTION_HASH = 1740695150L
        @JvmField
        val getCollisionFrictionBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_collision_friction", GET_COLLISION_FRICTION_HASH)

        private const val SET_COLLISION_BOUNCE_HASH = 373806689L
        @JvmField
        val setCollisionBounceBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_collision_bounce", SET_COLLISION_BOUNCE_HASH)

        private const val GET_COLLISION_BOUNCE_HASH = 1740695150L
        @JvmField
        val getCollisionBounceBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_collision_bounce", GET_COLLISION_BOUNCE_HASH)

        private const val SET_USING_ROTATION_VELOCITY_3D_HASH = 2586408642L
        @JvmField
        val setUsingRotationVelocity3dBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_using_rotation_velocity_3d", SET_USING_ROTATION_VELOCITY_3D_HASH)

        private const val IS_USING_ROTATION_VELOCITY_3D_HASH = 36873697L
        @JvmField
        val isUsingRotationVelocity3dBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "is_using_rotation_velocity_3d", IS_USING_ROTATION_VELOCITY_3D_HASH)

        private const val SET_ROTATION_VELOCITY_3D_MAX_HASH = 3460891852L
        @JvmField
        val setRotationVelocity3dMaxBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_rotation_velocity_3d_max", SET_ROTATION_VELOCITY_3D_MAX_HASH)

        private const val GET_ROTATION_VELOCITY_3D_MAX_HASH = 3360562783L
        @JvmField
        val getRotationVelocity3dMaxBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_rotation_velocity_3d_max", GET_ROTATION_VELOCITY_3D_MAX_HASH)

        private const val SET_ROTATION_VELOCITY_3D_MIN_HASH = 3460891852L
        @JvmField
        val setRotationVelocity3dMinBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_rotation_velocity_3d_min", SET_ROTATION_VELOCITY_3D_MIN_HASH)

        private const val GET_ROTATION_VELOCITY_3D_MIN_HASH = 3360562783L
        @JvmField
        val getRotationVelocity3dMinBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_rotation_velocity_3d_min", GET_ROTATION_VELOCITY_3D_MIN_HASH)

        private const val SET_ROTATION_VELOCITY_3D_CURVE_HASH = 4051416890L
        @JvmField
        val setRotationVelocity3dCurveBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "set_rotation_velocity_3d_curve", SET_ROTATION_VELOCITY_3D_CURVE_HASH)

        private const val GET_ROTATION_VELOCITY_3D_CURVE_HASH = 3635182373L
        @JvmField
        val getRotationVelocity3dCurveBind =
            ObjectCalls.getMethodBind("ParticleProcessMaterial", "get_rotation_velocity_3d_curve", GET_ROTATION_VELOCITY_3D_CURVE_HASH)
    }
}

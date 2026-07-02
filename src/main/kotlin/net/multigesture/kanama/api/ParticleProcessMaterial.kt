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

    fun setDirection(degrees: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setDirectionBind, handle, degrees)
    }

    fun getDirection(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getDirectionBind, handle)
    }

    fun setInheritVelocityRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setInheritVelocityRatioBind, handle, ratio)
    }

    fun getInheritVelocityRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getInheritVelocityRatioBind, handle)
    }

    fun setSpread(degrees: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSpreadBind, handle, degrees)
    }

    fun getSpread(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSpreadBind, handle)
    }

    fun setFlatness(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFlatnessBind, handle, amount)
    }

    fun getFlatness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFlatnessBind, handle)
    }

    fun setParam(param: Long, value: Vector2) {
        ObjectCalls.ptrcallWithLongAndVector2Arg(setParamBind, handle, param, value)
    }

    fun getParam(param: Long): Vector2 {
        return ObjectCalls.ptrcallWithLongArgRetVector2(getParamBind, handle, param)
    }

    fun setParamMin(param: Long, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamMinBind, handle, param, value)
    }

    fun getParamMin(param: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamMinBind, handle, param)
    }

    fun setParamMax(param: Long, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamMaxBind, handle, param, value)
    }

    fun getParamMax(param: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamMaxBind, handle, param)
    }

    fun setParamTexture(param: Long, texture: Texture2D?) {
        ObjectCalls.ptrcallWithLongAndObjectArg(setParamTextureBind, handle, param, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getParamTexture(param: Long): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithLongArgRetObject(getParamTextureBind, handle, param))
    }

    fun setColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setColorBind, handle, color)
    }

    fun getColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getColorBind, handle)
    }

    fun setUseScale3d(usingScale3d: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseScale3dBind, handle, usingScale3d)
    }

    fun isUsingScale3d(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingScale3dBind, handle)
    }

    fun setScale3dMin(scale3dMin: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setScale3dMinBind, handle, scale3dMin)
    }

    fun getScale3dMin(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getScale3dMinBind, handle)
    }

    fun setScale3dMax(scale3dMax: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setScale3dMaxBind, handle, scale3dMax)
    }

    fun getScale3dMax(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getScale3dMaxBind, handle)
    }

    fun setUseRotation3d(usingRotation3d: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseRotation3dBind, handle, usingRotation3d)
    }

    fun isUsingRotation3d(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingRotation3dBind, handle)
    }

    fun setRotation3dMin(rotation3dMin: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setRotation3dMinBind, handle, rotation3dMin)
    }

    fun getRotation3dMin(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getRotation3dMinBind, handle)
    }

    fun setRotation3dMax(rotation3dMax: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setRotation3dMaxBind, handle, rotation3dMax)
    }

    fun getRotation3dMax(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getRotation3dMaxBind, handle)
    }

    fun setColorRamp(ramp: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setColorRampBind, handle, listOf(ramp?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getColorRamp(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getColorRampBind, handle))
    }

    fun setAlphaCurve(curve: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setAlphaCurveBind, handle, listOf(curve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getAlphaCurve(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getAlphaCurveBind, handle))
    }

    fun setEmissionCurve(curve: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setEmissionCurveBind, handle, listOf(curve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getEmissionCurve(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEmissionCurveBind, handle))
    }

    fun setColorInitialRamp(ramp: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setColorInitialRampBind, handle, listOf(ramp?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getColorInitialRamp(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getColorInitialRampBind, handle))
    }

    fun setVelocityLimitCurve(curve: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setVelocityLimitCurveBind, handle, listOf(curve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getVelocityLimitCurve(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getVelocityLimitCurveBind, handle))
    }

    fun setParticleFlag(particleFlag: Long, enable: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setParticleFlagBind, handle, particleFlag, enable)
    }

    fun getParticleFlag(particleFlag: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(getParticleFlagBind, handle, particleFlag)
    }

    fun setVelocityPivot(pivot: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setVelocityPivotBind, handle, pivot)
    }

    fun getVelocityPivot(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getVelocityPivotBind, handle)
    }

    fun setEmissionShape(shape: Long) {
        ObjectCalls.ptrcallWithLongArg(setEmissionShapeBind, handle, shape)
    }

    fun getEmissionShape(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getEmissionShapeBind, handle)
    }

    fun setEmissionSphereRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionSphereRadiusBind, handle, radius)
    }

    fun getEmissionSphereRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionSphereRadiusBind, handle)
    }

    fun setEmissionBoxExtents(extents: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setEmissionBoxExtentsBind, handle, extents)
    }

    fun getEmissionBoxExtents(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getEmissionBoxExtentsBind, handle)
    }

    fun setEmissionPointTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setEmissionPointTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getEmissionPointTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEmissionPointTextureBind, handle))
    }

    fun setEmissionNormalTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setEmissionNormalTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getEmissionNormalTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEmissionNormalTextureBind, handle))
    }

    fun setEmissionColorTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setEmissionColorTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getEmissionColorTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEmissionColorTextureBind, handle))
    }

    fun setEmissionPointCount(pointCount: Int) {
        ObjectCalls.ptrcallWithIntArg(setEmissionPointCountBind, handle, pointCount)
    }

    fun getEmissionPointCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getEmissionPointCountBind, handle)
    }

    fun setEmissionRingAxis(axis: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setEmissionRingAxisBind, handle, axis)
    }

    fun getEmissionRingAxis(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getEmissionRingAxisBind, handle)
    }

    fun setEmissionRingHeight(height: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionRingHeightBind, handle, height)
    }

    fun getEmissionRingHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionRingHeightBind, handle)
    }

    fun setEmissionRingRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionRingRadiusBind, handle, radius)
    }

    fun getEmissionRingRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionRingRadiusBind, handle)
    }

    fun setEmissionRingInnerRadius(innerRadius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionRingInnerRadiusBind, handle, innerRadius)
    }

    fun getEmissionRingInnerRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionRingInnerRadiusBind, handle)
    }

    fun setEmissionRingConeAngle(coneAngle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionRingConeAngleBind, handle, coneAngle)
    }

    fun getEmissionRingConeAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionRingConeAngleBind, handle)
    }

    fun setEmissionShapeOffset(emissionShapeOffset: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setEmissionShapeOffsetBind, handle, emissionShapeOffset)
    }

    fun getEmissionShapeOffset(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getEmissionShapeOffsetBind, handle)
    }

    fun setEmissionShapeScale(emissionShapeScale: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setEmissionShapeScaleBind, handle, emissionShapeScale)
    }

    fun getEmissionShapeScale(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getEmissionShapeScaleBind, handle)
    }

    fun getTurbulenceEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getTurbulenceEnabledBind, handle)
    }

    fun setTurbulenceEnabled(turbulenceEnabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setTurbulenceEnabledBind, handle, turbulenceEnabled)
    }

    fun getTurbulenceNoiseStrength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTurbulenceNoiseStrengthBind, handle)
    }

    fun setTurbulenceNoiseStrength(turbulenceNoiseStrength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTurbulenceNoiseStrengthBind, handle, turbulenceNoiseStrength)
    }

    fun getTurbulenceNoiseScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTurbulenceNoiseScaleBind, handle)
    }

    fun setTurbulenceNoiseScale(turbulenceNoiseScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTurbulenceNoiseScaleBind, handle, turbulenceNoiseScale)
    }

    fun getTurbulenceNoiseSpeedRandom(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTurbulenceNoiseSpeedRandomBind, handle)
    }

    fun setTurbulenceNoiseSpeedRandom(turbulenceNoiseSpeedRandom: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTurbulenceNoiseSpeedRandomBind, handle, turbulenceNoiseSpeedRandom)
    }

    fun getTurbulenceNoiseSpeed(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getTurbulenceNoiseSpeedBind, handle)
    }

    fun setTurbulenceNoiseSpeed(turbulenceNoiseSpeed: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setTurbulenceNoiseSpeedBind, handle, turbulenceNoiseSpeed)
    }

    fun getGravity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getGravityBind, handle)
    }

    fun setGravity(accelVec: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setGravityBind, handle, accelVec)
    }

    fun setLifetimeRandomness(randomness: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLifetimeRandomnessBind, handle, randomness)
    }

    fun getLifetimeRandomness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLifetimeRandomnessBind, handle)
    }

    fun getSubEmitterMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSubEmitterModeBind, handle)
    }

    fun setSubEmitterMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setSubEmitterModeBind, handle, mode)
    }

    fun getSubEmitterFrequency(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSubEmitterFrequencyBind, handle)
    }

    fun setSubEmitterFrequency(hz: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSubEmitterFrequencyBind, handle, hz)
    }

    fun getSubEmitterAmountAtEnd(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSubEmitterAmountAtEndBind, handle)
    }

    fun setSubEmitterAmountAtEnd(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setSubEmitterAmountAtEndBind, handle, amount)
    }

    fun getSubEmitterAmountAtCollision(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSubEmitterAmountAtCollisionBind, handle)
    }

    fun setSubEmitterAmountAtCollision(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setSubEmitterAmountAtCollisionBind, handle, amount)
    }

    fun getSubEmitterAmountAtStart(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSubEmitterAmountAtStartBind, handle)
    }

    fun setSubEmitterAmountAtStart(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setSubEmitterAmountAtStartBind, handle, amount)
    }

    fun getSubEmitterKeepVelocity(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getSubEmitterKeepVelocityBind, handle)
    }

    fun setSubEmitterKeepVelocity(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSubEmitterKeepVelocityBind, handle, enable)
    }

    fun setAttractorInteractionEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAttractorInteractionEnabledBind, handle, enabled)
    }

    fun isAttractorInteractionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAttractorInteractionEnabledBind, handle)
    }

    fun setCollisionMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setCollisionModeBind, handle, mode)
    }

    fun getCollisionMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCollisionModeBind, handle)
    }

    fun setCollisionUseScale(radius: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCollisionUseScaleBind, handle, radius)
    }

    fun isCollisionUsingScale(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollisionUsingScaleBind, handle)
    }

    fun setCollisionFriction(friction: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCollisionFrictionBind, handle, friction)
    }

    fun getCollisionFriction(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCollisionFrictionBind, handle)
    }

    fun setCollisionBounce(bounce: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCollisionBounceBind, handle, bounce)
    }

    fun getCollisionBounce(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCollisionBounceBind, handle)
    }

    fun setUsingRotationVelocity3d(useRotationVelocity3d: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUsingRotationVelocity3dBind, handle, useRotationVelocity3d)
    }

    fun isUsingRotationVelocity3d(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingRotationVelocity3dBind, handle)
    }

    fun setRotationVelocity3dMax(rotationVelocity3dMax: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setRotationVelocity3dMaxBind, handle, rotationVelocity3dMax)
    }

    fun getRotationVelocity3dMax(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getRotationVelocity3dMaxBind, handle)
    }

    fun setRotationVelocity3dMin(rotationVelocity3dMin: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setRotationVelocity3dMinBind, handle, rotationVelocity3dMin)
    }

    fun getRotationVelocity3dMin(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getRotationVelocity3dMinBind, handle)
    }

    fun setRotationVelocity3dCurve(rotationVelocity3dCurve: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setRotationVelocity3dCurveBind, handle, listOf(rotationVelocity3dCurve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

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

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * This object contains all data related to a pose on a tracked object.
 *
 * Generated from Godot docs: XRPose
 */
class XRPose(handle: MemorySegment) : RefCounted(handle) {
    var hasTrackingData: Boolean
        @JvmName("hasTrackingDataProperty")
        get() = getHasTrackingData()
        @JvmName("setHasTrackingDataProperty")
        set(value) = setHasTrackingData(value)

    var name: String
        @JvmName("nameProperty")
        get() = getName()
        @JvmName("setNameProperty")
        set(value) = setName(value)

    var transform: Transform3D
        @JvmName("transformProperty")
        get() = getTransform()
        @JvmName("setTransformProperty")
        set(value) = setTransform(value)

    var linearVelocity: Vector3
        @JvmName("linearVelocityProperty")
        get() = getLinearVelocity()
        @JvmName("setLinearVelocityProperty")
        set(value) = setLinearVelocity(value)

    var angularVelocity: Vector3
        @JvmName("angularVelocityProperty")
        get() = getAngularVelocity()
        @JvmName("setAngularVelocityProperty")
        set(value) = setAngularVelocity(value)

    var trackingConfidence: Long
        @JvmName("trackingConfidenceProperty")
        get() = getTrackingConfidence()
        @JvmName("setTrackingConfidenceProperty")
        set(value) = setTrackingConfidence(value)

    fun setHasTrackingData(hasTrackingData: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHasTrackingDataBind, handle, hasTrackingData)
    }

    fun getHasTrackingData(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getHasTrackingDataBind, handle)
    }

    fun setName(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(setNameBind, handle, name)
    }

    fun getName(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getNameBind, handle)
    }

    fun setTransform(transform: Transform3D) {
        ObjectCalls.ptrcallWithTransform3DArg(setTransformBind, handle, transform)
    }

    fun getTransform(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getTransformBind, handle)
    }

    fun getAdjustedTransform(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getAdjustedTransformBind, handle)
    }

    fun setLinearVelocity(velocity: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setLinearVelocityBind, handle, velocity)
    }

    fun getLinearVelocity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getLinearVelocityBind, handle)
    }

    fun setAngularVelocity(velocity: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setAngularVelocityBind, handle, velocity)
    }

    fun getAngularVelocity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getAngularVelocityBind, handle)
    }

    fun setTrackingConfidence(trackingConfidence: Long) {
        ObjectCalls.ptrcallWithLongArg(setTrackingConfidenceBind, handle, trackingConfidence)
    }

    fun getTrackingConfidence(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTrackingConfidenceBind, handle)
    }

    companion object {
        const val XR_TRACKING_CONFIDENCE_NONE: Long = 0L
        const val XR_TRACKING_CONFIDENCE_LOW: Long = 1L
        const val XR_TRACKING_CONFIDENCE_HIGH: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): XRPose? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): XRPose? =
            if (handle.address() == 0L) null else XRPose(handle)

        private const val SET_HAS_TRACKING_DATA_HASH = 2586408642L
        private val setHasTrackingDataBind by lazy {
            ObjectCalls.getMethodBind("XRPose", "set_has_tracking_data", SET_HAS_TRACKING_DATA_HASH)
        }

        private const val GET_HAS_TRACKING_DATA_HASH = 36873697L
        private val getHasTrackingDataBind by lazy {
            ObjectCalls.getMethodBind("XRPose", "get_has_tracking_data", GET_HAS_TRACKING_DATA_HASH)
        }

        private const val SET_NAME_HASH = 3304788590L
        private val setNameBind by lazy {
            ObjectCalls.getMethodBind("XRPose", "set_name", SET_NAME_HASH)
        }

        private const val GET_NAME_HASH = 2002593661L
        private val getNameBind by lazy {
            ObjectCalls.getMethodBind("XRPose", "get_name", GET_NAME_HASH)
        }

        private const val SET_TRANSFORM_HASH = 2952846383L
        private val setTransformBind by lazy {
            ObjectCalls.getMethodBind("XRPose", "set_transform", SET_TRANSFORM_HASH)
        }

        private const val GET_TRANSFORM_HASH = 3229777777L
        private val getTransformBind by lazy {
            ObjectCalls.getMethodBind("XRPose", "get_transform", GET_TRANSFORM_HASH)
        }

        private const val GET_ADJUSTED_TRANSFORM_HASH = 3229777777L
        private val getAdjustedTransformBind by lazy {
            ObjectCalls.getMethodBind("XRPose", "get_adjusted_transform", GET_ADJUSTED_TRANSFORM_HASH)
        }

        private const val SET_LINEAR_VELOCITY_HASH = 3460891852L
        private val setLinearVelocityBind by lazy {
            ObjectCalls.getMethodBind("XRPose", "set_linear_velocity", SET_LINEAR_VELOCITY_HASH)
        }

        private const val GET_LINEAR_VELOCITY_HASH = 3360562783L
        private val getLinearVelocityBind by lazy {
            ObjectCalls.getMethodBind("XRPose", "get_linear_velocity", GET_LINEAR_VELOCITY_HASH)
        }

        private const val SET_ANGULAR_VELOCITY_HASH = 3460891852L
        private val setAngularVelocityBind by lazy {
            ObjectCalls.getMethodBind("XRPose", "set_angular_velocity", SET_ANGULAR_VELOCITY_HASH)
        }

        private const val GET_ANGULAR_VELOCITY_HASH = 3360562783L
        private val getAngularVelocityBind by lazy {
            ObjectCalls.getMethodBind("XRPose", "get_angular_velocity", GET_ANGULAR_VELOCITY_HASH)
        }

        private const val SET_TRACKING_CONFIDENCE_HASH = 4171656666L
        private val setTrackingConfidenceBind by lazy {
            ObjectCalls.getMethodBind("XRPose", "set_tracking_confidence", SET_TRACKING_CONFIDENCE_HASH)
        }

        private const val GET_TRACKING_CONFIDENCE_HASH = 2064923680L
        private val getTrackingConfidenceBind by lazy {
            ObjectCalls.getMethodBind("XRPose", "get_tracking_confidence", GET_TRACKING_CONFIDENCE_HASH)
        }
    }
}

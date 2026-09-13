package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: GLTFPhysicsBody
 */
class GLTFPhysicsBody(handle: GodotHandle) : Resource(handle) {
    var bodyType: String
        @JvmName("bodyTypeProperty")
        get() = getBodyType()
        @JvmName("setBodyTypeProperty")
        set(value) = setBodyType(value)

    var mass: Double
        @JvmName("massProperty")
        get() = getMass()
        @JvmName("setMassProperty")
        set(value) = setMass(value)

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

    var centerOfMass: Vector3
        @JvmName("centerOfMassProperty")
        get() = getCenterOfMass()
        @JvmName("setCenterOfMassProperty")
        set(value) = setCenterOfMass(value)

    var inertiaDiagonal: Vector3
        @JvmName("inertiaDiagonalProperty")
        get() = getInertiaDiagonal()
        @JvmName("setInertiaDiagonalProperty")
        set(value) = setInertiaDiagonal(value)

    var inertiaOrientation: Quaternion
        @JvmName("inertiaOrientationProperty")
        get() = getInertiaOrientation()
        @JvmName("setInertiaOrientationProperty")
        set(value) = setInertiaOrientation(value)

    var inertiaTensor: Basis
        @JvmName("inertiaTensorProperty")
        get() = getInertiaTensor()
        @JvmName("setInertiaTensorProperty")
        set(value) = setInertiaTensor(value)

    fun toNode(): CollisionObject3D? {
        checkOpen()
        return CollisionObject3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(toNodeBind, segment))
    }

    fun toDictionary(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(toDictionaryBind, segment)
    }

    fun getBodyType(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getBodyTypeBind, segment)
    }

    fun setBodyType(bodyType: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setBodyTypeBind, segment, bodyType)
    }

    fun getMass(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getMassBind, segment)
    }

    fun setMass(mass: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setMassBind, segment, mass)
    }

    fun getLinearVelocity(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(getLinearVelocityBind, segment)
    }

    fun setLinearVelocity(linearVelocity: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(setLinearVelocityBind, segment, linearVelocity)
    }

    fun getAngularVelocity(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(getAngularVelocityBind, segment)
    }

    fun setAngularVelocity(angularVelocity: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(setAngularVelocityBind, segment, angularVelocity)
    }

    fun getCenterOfMass(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(getCenterOfMassBind, segment)
    }

    fun setCenterOfMass(centerOfMass: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(setCenterOfMassBind, segment, centerOfMass)
    }

    fun getInertiaDiagonal(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(getInertiaDiagonalBind, segment)
    }

    fun setInertiaDiagonal(inertiaDiagonal: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(setInertiaDiagonalBind, segment, inertiaDiagonal)
    }

    fun getInertiaOrientation(): Quaternion {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetQuaternion(getInertiaOrientationBind, segment)
    }

    fun setInertiaOrientation(inertiaOrientation: Quaternion) {
        checkOpen()
        ObjectCalls.ptrcallWithQuaternionArg(setInertiaOrientationBind, segment, inertiaOrientation)
    }

    fun getInertiaTensor(): Basis {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBasis(getInertiaTensorBind, segment)
    }

    fun setInertiaTensor(inertiaTensor: Basis) {
        checkOpen()
        ObjectCalls.ptrcallWithBasisArg(setInertiaTensorBind, segment, inertiaTensor)
    }

    companion object {
        fun fromNode(bodyNode: CollisionObject3D): GLTFPhysicsBody? {
            return GLTFPhysicsBody.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(fromNodeBind, MemorySegment.NULL, bodyNode.segment))
        }

        fun fromDictionary(dictionary: Map<String, Any?>): GLTFPhysicsBody? {
            return GLTFPhysicsBody.wrap(ObjectCalls.ptrcallWithDictionaryArgRetObject(fromDictionaryBind, MemorySegment.NULL, dictionary))
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFPhysicsBody? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): GLTFPhysicsBody? =
            if (handle.address() == 0L) null else GLTFPhysicsBody(GodotHandle(handle))

        private const val FROM_NODE_HASH = 420544174L
        private val fromNodeBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "from_node", FROM_NODE_HASH)
        }

        private const val TO_NODE_HASH = 3224013656L
        private val toNodeBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "to_node", TO_NODE_HASH)
        }

        private const val FROM_DICTIONARY_HASH = 1177544336L
        private val fromDictionaryBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "from_dictionary", FROM_DICTIONARY_HASH)
        }

        private const val TO_DICTIONARY_HASH = 3102165223L
        private val toDictionaryBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "to_dictionary", TO_DICTIONARY_HASH)
        }

        private const val GET_BODY_TYPE_HASH = 201670096L
        private val getBodyTypeBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "get_body_type", GET_BODY_TYPE_HASH)
        }

        private const val SET_BODY_TYPE_HASH = 83702148L
        private val setBodyTypeBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "set_body_type", SET_BODY_TYPE_HASH)
        }

        private const val GET_MASS_HASH = 1740695150L
        private val getMassBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "get_mass", GET_MASS_HASH)
        }

        private const val SET_MASS_HASH = 373806689L
        private val setMassBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "set_mass", SET_MASS_HASH)
        }

        private const val GET_LINEAR_VELOCITY_HASH = 3360562783L
        private val getLinearVelocityBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "get_linear_velocity", GET_LINEAR_VELOCITY_HASH)
        }

        private const val SET_LINEAR_VELOCITY_HASH = 3460891852L
        private val setLinearVelocityBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "set_linear_velocity", SET_LINEAR_VELOCITY_HASH)
        }

        private const val GET_ANGULAR_VELOCITY_HASH = 3360562783L
        private val getAngularVelocityBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "get_angular_velocity", GET_ANGULAR_VELOCITY_HASH)
        }

        private const val SET_ANGULAR_VELOCITY_HASH = 3460891852L
        private val setAngularVelocityBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "set_angular_velocity", SET_ANGULAR_VELOCITY_HASH)
        }

        private const val GET_CENTER_OF_MASS_HASH = 3360562783L
        private val getCenterOfMassBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "get_center_of_mass", GET_CENTER_OF_MASS_HASH)
        }

        private const val SET_CENTER_OF_MASS_HASH = 3460891852L
        private val setCenterOfMassBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "set_center_of_mass", SET_CENTER_OF_MASS_HASH)
        }

        private const val GET_INERTIA_DIAGONAL_HASH = 3360562783L
        private val getInertiaDiagonalBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "get_inertia_diagonal", GET_INERTIA_DIAGONAL_HASH)
        }

        private const val SET_INERTIA_DIAGONAL_HASH = 3460891852L
        private val setInertiaDiagonalBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "set_inertia_diagonal", SET_INERTIA_DIAGONAL_HASH)
        }

        private const val GET_INERTIA_ORIENTATION_HASH = 1222331677L
        private val getInertiaOrientationBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "get_inertia_orientation", GET_INERTIA_ORIENTATION_HASH)
        }

        private const val SET_INERTIA_ORIENTATION_HASH = 1727505552L
        private val setInertiaOrientationBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "set_inertia_orientation", SET_INERTIA_ORIENTATION_HASH)
        }

        private const val GET_INERTIA_TENSOR_HASH = 2716978435L
        private val getInertiaTensorBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "get_inertia_tensor", GET_INERTIA_TENSOR_HASH)
        }

        private const val SET_INERTIA_TENSOR_HASH = 1055510324L
        private val setInertiaTensorBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "set_inertia_tensor", SET_INERTIA_TENSOR_HASH)
        }
    }
}

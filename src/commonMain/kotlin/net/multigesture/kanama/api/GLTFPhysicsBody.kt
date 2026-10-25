package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.Vector3

/**
 * Represents a glTF physics body.
 *
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

    /**
     * Converts this GLTFPhysicsBody instance into a Godot `CollisionObject3D` node.
     *
     * Generated from Godot docs: GLTFPhysicsBody.to_node
     */
    fun toNode(): CollisionObject3D? {
        checkOpen()
        val ret = CollisionObject3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.toNodeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Serializes this GLTFPhysicsBody instance into a `Dictionary`. It will be in the format expected
     * by the `OMI_physics_body` glTF extension.
     *
     * Generated from Godot docs: GLTFPhysicsBody.to_dictionary
     */
    fun toDictionary(): Map<String, Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDictionary(Binds.toDictionaryBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The type of the body. When importing, this controls what type of `CollisionObject3D` node Godot
     * should generate. Valid values are `"static"`, `"animatable"`, `"character"`, `"rigid"`,
     * `"vehicle"`, and `"trigger"`. When exporting, this will be squashed down to one of `"static"`,
     * `"kinematic"`, or `"dynamic"` motion types, or the `"trigger"` property.
     *
     * Generated from Godot docs: GLTFPhysicsBody.get_body_type
     */
    fun getBodyType(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getBodyTypeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The type of the body. When importing, this controls what type of `CollisionObject3D` node Godot
     * should generate. Valid values are `"static"`, `"animatable"`, `"character"`, `"rigid"`,
     * `"vehicle"`, and `"trigger"`. When exporting, this will be squashed down to one of `"static"`,
     * `"kinematic"`, or `"dynamic"` motion types, or the `"trigger"` property.
     *
     * Generated from Godot docs: GLTFPhysicsBody.set_body_type
     */
    fun setBodyType(bodyType: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setBodyTypeBind, segment, bodyType)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The mass of the physics body, in kilograms. This is only used when the body type is "rigid" or
     * "vehicle".
     *
     * Generated from Godot docs: GLTFPhysicsBody.get_mass
     */
    fun getMass(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMassBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The mass of the physics body, in kilograms. This is only used when the body type is "rigid" or
     * "vehicle".
     *
     * Generated from Godot docs: GLTFPhysicsBody.set_mass
     */
    fun setMass(mass: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMassBind, segment, mass)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The linear velocity of the physics body, in meters per second. This is only used when the body
     * type is "rigid" or "vehicle".
     *
     * Generated from Godot docs: GLTFPhysicsBody.get_linear_velocity
     */
    fun getLinearVelocity(): Vector3 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector3(Binds.getLinearVelocityBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The linear velocity of the physics body, in meters per second. This is only used when the body
     * type is "rigid" or "vehicle".
     *
     * Generated from Godot docs: GLTFPhysicsBody.set_linear_velocity
     */
    fun setLinearVelocity(linearVelocity: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setLinearVelocityBind, segment, linearVelocity)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The angular velocity of the physics body, in radians per second. This is only used when the body
     * type is "rigid" or "vehicle".
     *
     * Generated from Godot docs: GLTFPhysicsBody.get_angular_velocity
     */
    fun getAngularVelocity(): Vector3 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector3(Binds.getAngularVelocityBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The angular velocity of the physics body, in radians per second. This is only used when the body
     * type is "rigid" or "vehicle".
     *
     * Generated from Godot docs: GLTFPhysicsBody.set_angular_velocity
     */
    fun setAngularVelocity(angularVelocity: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setAngularVelocityBind, segment, angularVelocity)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The center of mass of the body, in meters. This is in local space relative to the body. By
     * default, the center of the mass is the body's origin.
     *
     * Generated from Godot docs: GLTFPhysicsBody.get_center_of_mass
     */
    fun getCenterOfMass(): Vector3 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector3(Binds.getCenterOfMassBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The center of mass of the body, in meters. This is in local space relative to the body. By
     * default, the center of the mass is the body's origin.
     *
     * Generated from Godot docs: GLTFPhysicsBody.set_center_of_mass
     */
    fun setCenterOfMass(centerOfMass: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setCenterOfMassBind, segment, centerOfMass)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The inertia strength of the physics body, in kilogram meter squared (kg⋅m²). This represents the
     * inertia around the principle axes, the diagonal of the inertia tensor matrix. This is only used
     * when the body type is "rigid" or "vehicle". When converted to a Godot `RigidBody3D` node, if
     * this value is zero, then the inertia will be calculated automatically.
     *
     * Generated from Godot docs: GLTFPhysicsBody.get_inertia_diagonal
     */
    fun getInertiaDiagonal(): Vector3 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector3(Binds.getInertiaDiagonalBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The inertia strength of the physics body, in kilogram meter squared (kg⋅m²). This represents the
     * inertia around the principle axes, the diagonal of the inertia tensor matrix. This is only used
     * when the body type is "rigid" or "vehicle". When converted to a Godot `RigidBody3D` node, if
     * this value is zero, then the inertia will be calculated automatically.
     *
     * Generated from Godot docs: GLTFPhysicsBody.set_inertia_diagonal
     */
    fun setInertiaDiagonal(inertiaDiagonal: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setInertiaDiagonalBind, segment, inertiaDiagonal)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The inertia orientation of the physics body. This defines the rotation of the inertia's
     * principle axes relative to the object's local axes. This is only used when the body type is
     * "rigid" or "vehicle" and `inertia_diagonal` is set to a non-zero value.
     *
     * Generated from Godot docs: GLTFPhysicsBody.get_inertia_orientation
     */
    fun getInertiaOrientation(): Quaternion {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetQuaternion(Binds.getInertiaOrientationBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The inertia orientation of the physics body. This defines the rotation of the inertia's
     * principle axes relative to the object's local axes. This is only used when the body type is
     * "rigid" or "vehicle" and `inertia_diagonal` is set to a non-zero value.
     *
     * Generated from Godot docs: GLTFPhysicsBody.set_inertia_orientation
     */
    fun setInertiaOrientation(inertiaOrientation: Quaternion) {
        checkOpen()
        ObjectCalls.ptrcallWithQuaternionArg(Binds.setInertiaOrientationBind, segment, inertiaOrientation)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The inertia tensor of the physics body, in kilogram meter squared (kg⋅m²). This is only used
     * when the body type is "rigid" or "vehicle". When converted to a Godot `RigidBody3D` node, if
     * this value is zero, then the inertia will be calculated automatically.
     *
     * Generated from Godot docs: GLTFPhysicsBody.get_inertia_tensor
     */
    fun getInertiaTensor(): Basis {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBasis(Binds.getInertiaTensorBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The inertia tensor of the physics body, in kilogram meter squared (kg⋅m²). This is only used
     * when the body type is "rigid" or "vehicle". When converted to a Godot `RigidBody3D` node, if
     * this value is zero, then the inertia will be calculated automatically.
     *
     * Generated from Godot docs: GLTFPhysicsBody.set_inertia_tensor
     */
    fun setInertiaTensor(inertiaTensor: Basis) {
        checkOpen()
        ObjectCalls.ptrcallWithBasisArg(Binds.setInertiaTensorBind, segment, inertiaTensor)
        ObjectCalls.keepReachable(this)
    }

    companion object {
        /**
         * Creates a new GLTFPhysicsBody instance from the given Godot `CollisionObject3D` node.
         *
         * Generated from Godot docs: GLTFPhysicsBody.from_node
         */
        fun fromNode(bodyNode: CollisionObject3D): GLTFPhysicsBody? {
            val ret = GLTFPhysicsBody.wrapOwned(ObjectCalls.ptrcallWithObjectArgRetObject(Binds.fromNodeBind, NULL_SEGMENT, bodyNode.segment))
            ObjectCalls.keepReachable(bodyNode)
            return ret
        }

        /**
         * Creates a new GLTFPhysicsBody instance by parsing the given `Dictionary` in the
         * `OMI_physics_body` glTF extension format.
         *
         * Generated from Godot docs: GLTFPhysicsBody.from_dictionary
         */
        fun fromDictionary(dictionary: Map<String, Any?>): GLTFPhysicsBody? {
            return GLTFPhysicsBody.wrapOwned(ObjectCalls.ptrcallWithDictionaryArgRetObject(Binds.fromDictionaryBind, NULL_SEGMENT, dictionary))
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFPhysicsBody? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GLTFPhysicsBody? =
            if (handle.address() == 0L) null else RefCounted.owned(GLTFPhysicsBody(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GLTFPhysicsBody? =
            if (handle.address() == 0L) null else GLTFPhysicsBody(GodotHandle(handle))

        /** A new GLTFPhysicsBody, GDScript's `GLTFPhysicsBody.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): GLTFPhysicsBody =
            RefCounted.owned(GLTFPhysicsBody(GodotHandle(ObjectCalls.constructObject("GLTFPhysicsBody"))))
    }

    private object Binds {
        private const val FROM_NODE_HASH = 420544174L
        @JvmField
        val fromNodeBind =
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "from_node", FROM_NODE_HASH)

        private const val TO_NODE_HASH = 3224013656L
        @JvmField
        val toNodeBind =
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "to_node", TO_NODE_HASH)

        private const val FROM_DICTIONARY_HASH = 1177544336L
        @JvmField
        val fromDictionaryBind =
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "from_dictionary", FROM_DICTIONARY_HASH)

        private const val TO_DICTIONARY_HASH = 3102165223L
        @JvmField
        val toDictionaryBind =
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "to_dictionary", TO_DICTIONARY_HASH)

        private const val GET_BODY_TYPE_HASH = 201670096L
        @JvmField
        val getBodyTypeBind =
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "get_body_type", GET_BODY_TYPE_HASH)

        private const val SET_BODY_TYPE_HASH = 83702148L
        @JvmField
        val setBodyTypeBind =
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "set_body_type", SET_BODY_TYPE_HASH)

        private const val GET_MASS_HASH = 1740695150L
        @JvmField
        val getMassBind =
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "get_mass", GET_MASS_HASH)

        private const val SET_MASS_HASH = 373806689L
        @JvmField
        val setMassBind =
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "set_mass", SET_MASS_HASH)

        private const val GET_LINEAR_VELOCITY_HASH = 3360562783L
        @JvmField
        val getLinearVelocityBind =
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "get_linear_velocity", GET_LINEAR_VELOCITY_HASH)

        private const val SET_LINEAR_VELOCITY_HASH = 3460891852L
        @JvmField
        val setLinearVelocityBind =
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "set_linear_velocity", SET_LINEAR_VELOCITY_HASH)

        private const val GET_ANGULAR_VELOCITY_HASH = 3360562783L
        @JvmField
        val getAngularVelocityBind =
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "get_angular_velocity", GET_ANGULAR_VELOCITY_HASH)

        private const val SET_ANGULAR_VELOCITY_HASH = 3460891852L
        @JvmField
        val setAngularVelocityBind =
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "set_angular_velocity", SET_ANGULAR_VELOCITY_HASH)

        private const val GET_CENTER_OF_MASS_HASH = 3360562783L
        @JvmField
        val getCenterOfMassBind =
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "get_center_of_mass", GET_CENTER_OF_MASS_HASH)

        private const val SET_CENTER_OF_MASS_HASH = 3460891852L
        @JvmField
        val setCenterOfMassBind =
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "set_center_of_mass", SET_CENTER_OF_MASS_HASH)

        private const val GET_INERTIA_DIAGONAL_HASH = 3360562783L
        @JvmField
        val getInertiaDiagonalBind =
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "get_inertia_diagonal", GET_INERTIA_DIAGONAL_HASH)

        private const val SET_INERTIA_DIAGONAL_HASH = 3460891852L
        @JvmField
        val setInertiaDiagonalBind =
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "set_inertia_diagonal", SET_INERTIA_DIAGONAL_HASH)

        private const val GET_INERTIA_ORIENTATION_HASH = 1222331677L
        @JvmField
        val getInertiaOrientationBind =
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "get_inertia_orientation", GET_INERTIA_ORIENTATION_HASH)

        private const val SET_INERTIA_ORIENTATION_HASH = 1727505552L
        @JvmField
        val setInertiaOrientationBind =
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "set_inertia_orientation", SET_INERTIA_ORIENTATION_HASH)

        private const val GET_INERTIA_TENSOR_HASH = 2716978435L
        @JvmField
        val getInertiaTensorBind =
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "get_inertia_tensor", GET_INERTIA_TENSOR_HASH)

        private const val SET_INERTIA_TENSOR_HASH = 1055510324L
        @JvmField
        val setInertiaTensorBind =
            ObjectCalls.getMethodBind("GLTFPhysicsBody", "set_inertia_tensor", SET_INERTIA_TENSOR_HASH)
    }
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

/**
 * Provides virtual methods that can be overridden to create custom `PhysicsServer2D`
 * implementations.
 *
 * Generated from Godot docs: PhysicsServer2DExtension
 */
class PhysicsServer2DExtension(handle: MemorySegment) : GodotObject(handle) {
    /**
     * Returns `true` if the body with the given `RID` is being excluded from `_body_test_motion`. See
     * also `Object.get_instance_id`.
     *
     * Generated from Godot docs: PhysicsServer2DExtension.body_test_motion_is_excluding_body
     */
    fun bodyTestMotionIsExcludingBody(body: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(bodyTestMotionIsExcludingBodyBind, handle, body)
    }

    /**
     * Returns `true` if the object with the given instance ID is being excluded from
     * `_body_test_motion`. See also `Object.get_instance_id`.
     *
     * Generated from Godot docs: PhysicsServer2DExtension.body_test_motion_is_excluding_object
     */
    fun bodyTestMotionIsExcludingObject(objectValue: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(bodyTestMotionIsExcludingObjectBind, handle, objectValue)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PhysicsServer2DExtension? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicsServer2DExtension? =
            if (handle.address() == 0L) null else PhysicsServer2DExtension(handle)

        private const val BODY_TEST_MOTION_IS_EXCLUDING_BODY_HASH = 4155700596L
        private val bodyTestMotionIsExcludingBodyBind by lazy {
            ObjectCalls.getMethodBind("PhysicsServer2DExtension", "body_test_motion_is_excluding_body", BODY_TEST_MOTION_IS_EXCLUDING_BODY_HASH)
        }

        private const val BODY_TEST_MOTION_IS_EXCLUDING_OBJECT_HASH = 1116898809L
        private val bodyTestMotionIsExcludingObjectBind by lazy {
            ObjectCalls.getMethodBind("PhysicsServer2DExtension", "body_test_motion_is_excluding_object", BODY_TEST_MOTION_IS_EXCLUDING_OBJECT_HASH)
        }
    }
}

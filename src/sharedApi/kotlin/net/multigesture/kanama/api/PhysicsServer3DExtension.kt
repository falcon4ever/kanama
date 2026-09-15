package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID

/**
 * Provides virtual methods that can be overridden to create custom `PhysicsServer3D`
 * implementations.
 *
 * Generated from Godot docs: PhysicsServer3DExtension
 */
class PhysicsServer3DExtension(handle: GodotHandle) : GodotObject(handle) {
    fun bodyTestMotionIsExcludingBody(body: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(bodyTestMotionIsExcludingBodyBind, segment, body)
    }

    fun bodyTestMotionIsExcludingObject(objectValue: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(bodyTestMotionIsExcludingObjectBind, segment, objectValue)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PhysicsServer3DExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): PhysicsServer3DExtension? =
            if (handle.address() == 0L) null else PhysicsServer3DExtension(GodotHandle(handle))

        private const val BODY_TEST_MOTION_IS_EXCLUDING_BODY_HASH = 4155700596L
        private val bodyTestMotionIsExcludingBodyBind by lazy {
            ObjectCalls.getMethodBind("PhysicsServer3DExtension", "body_test_motion_is_excluding_body", BODY_TEST_MOTION_IS_EXCLUDING_BODY_HASH)
        }

        private const val BODY_TEST_MOTION_IS_EXCLUDING_OBJECT_HASH = 1116898809L
        private val bodyTestMotionIsExcludingObjectBind by lazy {
            ObjectCalls.getMethodBind("PhysicsServer3DExtension", "body_test_motion_is_excluding_object", BODY_TEST_MOTION_IS_EXCLUDING_OBJECT_HASH)
        }
    }
}

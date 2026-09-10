package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Provides direct access to a physics space in the `PhysicsServer2D`.
 *
 * Generated from Godot docs: PhysicsDirectSpaceState2D
 */
open class PhysicsDirectSpaceState2D(handle: MemorySegment) : GodotObject(handle) {
    /**
     * Checks how far a `Shape2D` can move without colliding. All the parameters for the query,
     * including the shape and the motion, are supplied through a `PhysicsShapeQueryParameters2D`
     * object. Returns an array with the safe and unsafe proportions (between 0 and 1) of the motion.
     * The safe proportion is the maximum fraction of the motion that can be made without a collision.
     * The unsafe proportion is the minimum fraction of the distance that must be moved for a
     * collision. If no collision is detected a result of `[1.0, 1.0]` will be returned. Note: Any
     * `Shape2D`s that the shape is already colliding with e.g. inside of, will be ignored. Use
     * `collide_shape` to determine the `Shape2D`s that the shape is already colliding with.
     *
     * Generated from Godot docs: PhysicsDirectSpaceState2D.cast_motion
     */
    fun castMotion(parameters: PhysicsShapeQueryParameters2D): List<Float> {
        return ObjectCalls.ptrcallWithObjectArgRetPackedFloat32List(castMotionBind, handle, parameters.requireOpenHandle())
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PhysicsDirectSpaceState2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicsDirectSpaceState2D? =
            if (handle.address() == 0L) null else PhysicsDirectSpaceState2D(handle)

        private const val CAST_MOTION_HASH = 711275086L
        private val castMotionBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectSpaceState2D", "cast_motion", CAST_MOTION_HASH)
        }
    }
}

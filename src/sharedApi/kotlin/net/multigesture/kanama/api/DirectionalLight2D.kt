package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Directional 2D light from a distance.
 *
 * Generated from Godot docs: DirectionalLight2D
 */
class DirectionalLight2D(handle: GodotHandle) : Light2D(handle) {
    var maxDistance: Double
        @JvmName("maxDistanceProperty")
        get() = getMaxDistance()
        @JvmName("setMaxDistanceProperty")
        set(value) = setMaxDistance(value)

    /**
     * The maximum distance from the camera center objects can be before their shadows are culled (in
     * pixels). Decreasing this value can prevent objects located outside the camera from casting
     * shadows (while also improving performance). `Camera2D.zoom` is not taken into account by
     * `max_distance`, which means that at higher zoom values, shadows will appear to fade out sooner
     * when zooming onto a given point.
     *
     * Generated from Godot docs: DirectionalLight2D.set_max_distance
     */
    fun setMaxDistance(pixels: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMaxDistanceBind, segment, pixels)
    }

    /**
     * The maximum distance from the camera center objects can be before their shadows are culled (in
     * pixels). Decreasing this value can prevent objects located outside the camera from casting
     * shadows (while also improving performance). `Camera2D.zoom` is not taken into account by
     * `max_distance`, which means that at higher zoom values, shadows will appear to fade out sooner
     * when zooming onto a given point.
     *
     * Generated from Godot docs: DirectionalLight2D.get_max_distance
     */
    fun getMaxDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMaxDistanceBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): DirectionalLight2D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): DirectionalLight2D? =
            if (handle.address() == 0L) null else DirectionalLight2D(GodotHandle(handle))

        private const val SET_MAX_DISTANCE_HASH = 373806689L
        private val setMaxDistanceBind by lazy {
            ObjectCalls.getMethodBind("DirectionalLight2D", "set_max_distance", SET_MAX_DISTANCE_HASH)
        }

        private const val GET_MAX_DISTANCE_HASH = 1740695150L
        private val getMaxDistanceBind by lazy {
            ObjectCalls.getMethodBind("DirectionalLight2D", "get_max_distance", GET_MAX_DISTANCE_HASH)
        }
    }
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

/**
 * A resource that holds all components of a 3D world, such as a visual scenario and a physics
 * space.
 *
 * Generated from Godot docs: World3D
 */
class World3D(handle: MemorySegment) : Resource(handle) {
    var environment: Environment?
        @JvmName("environmentProperty")
        get() = getEnvironment()
        @JvmName("setEnvironmentProperty")
        set(value) = setEnvironment(value)

    var fallbackEnvironment: Environment?
        @JvmName("fallbackEnvironmentProperty")
        get() = getFallbackEnvironment()
        @JvmName("setFallbackEnvironmentProperty")
        set(value) = setFallbackEnvironment(value)

    var cameraAttributes: CameraAttributes?
        @JvmName("cameraAttributesProperty")
        get() = getCameraAttributes()
        @JvmName("setCameraAttributesProperty")
        set(value) = setCameraAttributes(value)

    val space: RID
        @JvmName("spaceProperty")
        get() = getSpace()

    val navigationMap: RID
        @JvmName("navigationMapProperty")
        get() = getNavigationMap()

    val scenario: RID
        @JvmName("scenarioProperty")
        get() = getScenario()

    val directSpaceState: PhysicsDirectSpaceState3D?
        @JvmName("directSpaceStateProperty")
        get() = getDirectSpaceState()

    fun getSpace(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getSpaceBind, handle)
    }

    fun getNavigationMap(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getNavigationMapBind, handle)
    }

    fun getScenario(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getScenarioBind, handle)
    }

    fun setEnvironment(env: Environment?) {
        ObjectCalls.ptrcallWithObjectArgs(setEnvironmentBind, handle, listOf(env?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getEnvironment(): Environment? {
        return Environment.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEnvironmentBind, handle))
    }

    fun setFallbackEnvironment(env: Environment?) {
        ObjectCalls.ptrcallWithObjectArgs(setFallbackEnvironmentBind, handle, listOf(env?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getFallbackEnvironment(): Environment? {
        return Environment.wrap(ObjectCalls.ptrcallNoArgsRetObject(getFallbackEnvironmentBind, handle))
    }

    fun setCameraAttributes(attributes: CameraAttributes?) {
        ObjectCalls.ptrcallWithObjectArgs(setCameraAttributesBind, handle, listOf(attributes?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getCameraAttributes(): CameraAttributes? {
        return CameraAttributes.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCameraAttributesBind, handle))
    }

    fun getDirectSpaceState(): PhysicsDirectSpaceState3D? {
        return PhysicsDirectSpaceState3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getDirectSpaceStateBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): World3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): World3D? =
            if (handle.address() == 0L) null else World3D(handle)

        private const val GET_SPACE_HASH = 2944877500L
        private val getSpaceBind by lazy {
            ObjectCalls.getMethodBind("World3D", "get_space", GET_SPACE_HASH)
        }

        private const val GET_NAVIGATION_MAP_HASH = 2944877500L
        private val getNavigationMapBind by lazy {
            ObjectCalls.getMethodBind("World3D", "get_navigation_map", GET_NAVIGATION_MAP_HASH)
        }

        private const val GET_SCENARIO_HASH = 2944877500L
        private val getScenarioBind by lazy {
            ObjectCalls.getMethodBind("World3D", "get_scenario", GET_SCENARIO_HASH)
        }

        private const val SET_ENVIRONMENT_HASH = 4143518816L
        private val setEnvironmentBind by lazy {
            ObjectCalls.getMethodBind("World3D", "set_environment", SET_ENVIRONMENT_HASH)
        }

        private const val GET_ENVIRONMENT_HASH = 3082064660L
        private val getEnvironmentBind by lazy {
            ObjectCalls.getMethodBind("World3D", "get_environment", GET_ENVIRONMENT_HASH)
        }

        private const val SET_FALLBACK_ENVIRONMENT_HASH = 4143518816L
        private val setFallbackEnvironmentBind by lazy {
            ObjectCalls.getMethodBind("World3D", "set_fallback_environment", SET_FALLBACK_ENVIRONMENT_HASH)
        }

        private const val GET_FALLBACK_ENVIRONMENT_HASH = 3082064660L
        private val getFallbackEnvironmentBind by lazy {
            ObjectCalls.getMethodBind("World3D", "get_fallback_environment", GET_FALLBACK_ENVIRONMENT_HASH)
        }

        private const val SET_CAMERA_ATTRIBUTES_HASH = 2817810567L
        private val setCameraAttributesBind by lazy {
            ObjectCalls.getMethodBind("World3D", "set_camera_attributes", SET_CAMERA_ATTRIBUTES_HASH)
        }

        private const val GET_CAMERA_ATTRIBUTES_HASH = 3921283215L
        private val getCameraAttributesBind by lazy {
            ObjectCalls.getMethodBind("World3D", "get_camera_attributes", GET_CAMERA_ATTRIBUTES_HASH)
        }

        private const val GET_DIRECT_SPACE_STATE_HASH = 2069328350L
        private val getDirectSpaceStateBind by lazy {
            ObjectCalls.getMethodBind("World3D", "get_direct_space_state", GET_DIRECT_SPACE_STATE_HASH)
        }
    }
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Default environment properties for the entire scene (post-processing effects, lighting and
 * background settings).
 *
 * Generated from Godot docs: WorldEnvironment
 */
class WorldEnvironment(handle: MemorySegment) : Node(handle) {
    var environment: Environment?
        @JvmName("environmentProperty")
        get() = getEnvironment()
        @JvmName("setEnvironmentProperty")
        set(value) = setEnvironment(value)

    var cameraAttributes: CameraAttributes?
        @JvmName("cameraAttributesProperty")
        get() = getCameraAttributes()
        @JvmName("setCameraAttributesProperty")
        set(value) = setCameraAttributes(value)

    var compositor: Compositor?
        @JvmName("compositorProperty")
        get() = getCompositor()
        @JvmName("setCompositorProperty")
        set(value) = setCompositor(value)

    fun setEnvironment(env: Environment?) {
        ObjectCalls.ptrcallWithObjectArgs(setEnvironmentBind, handle, listOf(env?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getEnvironment(): Environment? {
        return Environment.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEnvironmentBind, handle))
    }

    fun setCameraAttributes(cameraAttributes: CameraAttributes?) {
        ObjectCalls.ptrcallWithObjectArgs(setCameraAttributesBind, handle, listOf(cameraAttributes?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getCameraAttributes(): CameraAttributes? {
        return CameraAttributes.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCameraAttributesBind, handle))
    }

    fun setCompositor(compositor: Compositor?) {
        ObjectCalls.ptrcallWithObjectArgs(setCompositorBind, handle, listOf(compositor?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getCompositor(): Compositor? {
        return Compositor.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCompositorBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): WorldEnvironment? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): WorldEnvironment? =
            if (handle.address() == 0L) null else WorldEnvironment(handle)

        private const val SET_ENVIRONMENT_HASH = 4143518816L
        private val setEnvironmentBind by lazy {
            ObjectCalls.getMethodBind("WorldEnvironment", "set_environment", SET_ENVIRONMENT_HASH)
        }

        private const val GET_ENVIRONMENT_HASH = 3082064660L
        private val getEnvironmentBind by lazy {
            ObjectCalls.getMethodBind("WorldEnvironment", "get_environment", GET_ENVIRONMENT_HASH)
        }

        private const val SET_CAMERA_ATTRIBUTES_HASH = 2817810567L
        private val setCameraAttributesBind by lazy {
            ObjectCalls.getMethodBind("WorldEnvironment", "set_camera_attributes", SET_CAMERA_ATTRIBUTES_HASH)
        }

        private const val GET_CAMERA_ATTRIBUTES_HASH = 3921283215L
        private val getCameraAttributesBind by lazy {
            ObjectCalls.getMethodBind("WorldEnvironment", "get_camera_attributes", GET_CAMERA_ATTRIBUTES_HASH)
        }

        private const val SET_COMPOSITOR_HASH = 1586754307L
        private val setCompositorBind by lazy {
            ObjectCalls.getMethodBind("WorldEnvironment", "set_compositor", SET_COMPOSITOR_HASH)
        }

        private const val GET_COMPOSITOR_HASH = 3647707413L
        private val getCompositorBind by lazy {
            ObjectCalls.getMethodBind("WorldEnvironment", "get_compositor", GET_COMPOSITOR_HASH)
        }
    }
}

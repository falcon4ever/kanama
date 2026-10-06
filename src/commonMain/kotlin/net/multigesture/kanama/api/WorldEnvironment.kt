package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Default environment properties for the entire scene (post-processing effects, lighting and
 * background settings).
 *
 * Generated from Godot docs: WorldEnvironment
 */
class WorldEnvironment(handle: GodotHandle) : Node(handle) {
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

    /**
     * The `Environment` resource used by this `WorldEnvironment`, defining the default properties.
     *
     * Generated from Godot docs: WorldEnvironment.set_environment
     */
    fun setEnvironment(env: Environment?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setEnvironmentBind, segment, listOf(env?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `Environment` resource used by this `WorldEnvironment`, defining the default properties.
     *
     * Generated from Godot docs: WorldEnvironment.get_environment
     */
    fun getEnvironment(): Environment? {
        return Environment.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getEnvironmentBind, segment))
    }

    /**
     * The default `CameraAttributes` resource to use if none set on the `Camera3D`.
     *
     * Generated from Godot docs: WorldEnvironment.set_camera_attributes
     */
    fun setCameraAttributes(cameraAttributes: CameraAttributes?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setCameraAttributesBind, segment, listOf(cameraAttributes?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The default `CameraAttributes` resource to use if none set on the `Camera3D`.
     *
     * Generated from Godot docs: WorldEnvironment.get_camera_attributes
     */
    fun getCameraAttributes(): CameraAttributes? {
        return CameraAttributes.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getCameraAttributesBind, segment))
    }

    /**
     * The default `Compositor` resource to use if none set on the `Camera3D`.
     *
     * Generated from Godot docs: WorldEnvironment.set_compositor
     */
    fun setCompositor(compositor: Compositor?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setCompositorBind, segment, listOf(compositor?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The default `Compositor` resource to use if none set on the `Camera3D`.
     *
     * Generated from Godot docs: WorldEnvironment.get_compositor
     */
    fun getCompositor(): Compositor? {
        return Compositor.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getCompositorBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): WorldEnvironment? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): WorldEnvironment? =
            if (handle.address() == 0L) null else WorldEnvironment(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_ENVIRONMENT_HASH = 4143518816L
        @JvmField
        val setEnvironmentBind =
            ObjectCalls.getMethodBind("WorldEnvironment", "set_environment", SET_ENVIRONMENT_HASH)

        private const val GET_ENVIRONMENT_HASH = 3082064660L
        @JvmField
        val getEnvironmentBind =
            ObjectCalls.getMethodBind("WorldEnvironment", "get_environment", GET_ENVIRONMENT_HASH)

        private const val SET_CAMERA_ATTRIBUTES_HASH = 2817810567L
        @JvmField
        val setCameraAttributesBind =
            ObjectCalls.getMethodBind("WorldEnvironment", "set_camera_attributes", SET_CAMERA_ATTRIBUTES_HASH)

        private const val GET_CAMERA_ATTRIBUTES_HASH = 3921283215L
        @JvmField
        val getCameraAttributesBind =
            ObjectCalls.getMethodBind("WorldEnvironment", "get_camera_attributes", GET_CAMERA_ATTRIBUTES_HASH)

        private const val SET_COMPOSITOR_HASH = 1586754307L
        @JvmField
        val setCompositorBind =
            ObjectCalls.getMethodBind("WorldEnvironment", "set_compositor", SET_COMPOSITOR_HASH)

        private const val GET_COMPOSITOR_HASH = 3647707413L
        @JvmField
        val getCompositorBind =
            ObjectCalls.getMethodBind("WorldEnvironment", "get_compositor", GET_COMPOSITOR_HASH)
    }
}

package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath

/**
 * A box-shaped region of 3D space that, when visible on screen, enables a target node.
 *
 * Generated from Godot docs: VisibleOnScreenEnabler3D
 */
class VisibleOnScreenEnabler3D(handle: GodotHandle) : VisibleOnScreenNotifier3D(handle) {
    var enableMode: VisibleOnScreenEnabler3D.EnableMode
        @JvmName("enableModeProperty")
        get() = getEnableMode()
        @JvmName("setEnableModeProperty")
        set(value) = setEnableMode(value)

    var enableNodePath: NodePath
        @JvmName("enableNodePathProperty")
        get() = getEnableNodePath()
        @JvmName("setEnableNodePathProperty")
        set(value) = setEnableNodePath(value)

    /**
     * Determines how the target node is enabled. Corresponds to `Node.ProcessMode`. When the node is
     * disabled, it always uses `Node.PROCESS_MODE_DISABLED`.
     *
     * Generated from Godot docs: VisibleOnScreenEnabler3D.set_enable_mode
     */
    fun setEnableMode(mode: VisibleOnScreenEnabler3D.EnableMode) {
        ObjectCalls.ptrcallWithLongArg(setEnableModeBind, segment, mode.value)
    }

    /**
     * Determines how the target node is enabled. Corresponds to `Node.ProcessMode`. When the node is
     * disabled, it always uses `Node.PROCESS_MODE_DISABLED`.
     *
     * Generated from Godot docs: VisibleOnScreenEnabler3D.get_enable_mode
     */
    fun getEnableMode(): VisibleOnScreenEnabler3D.EnableMode {
        return VisibleOnScreenEnabler3D.EnableMode(ObjectCalls.ptrcallNoArgsRetLong(getEnableModeBind, segment))
    }

    /**
     * The path to the target node, relative to the `VisibleOnScreenEnabler3D`. The target node is
     * cached; it's only assigned when setting this property (if the `VisibleOnScreenEnabler3D` is
     * inside the scene tree) and every time the `VisibleOnScreenEnabler3D` enters the scene tree. If
     * the path is empty, no node will be affected. If the path is invalid, an error is also generated.
     *
     * Generated from Godot docs: VisibleOnScreenEnabler3D.set_enable_node_path
     */
    fun setEnableNodePath(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setEnableNodePathBind, segment, path)
    }

    /**
     * The path to the target node, relative to the `VisibleOnScreenEnabler3D`. The target node is
     * cached; it's only assigned when setting this property (if the `VisibleOnScreenEnabler3D` is
     * inside the scene tree) and every time the `VisibleOnScreenEnabler3D` enters the scene tree. If
     * the path is empty, no node will be affected. If the path is invalid, an error is also generated.
     *
     * Generated from Godot docs: VisibleOnScreenEnabler3D.get_enable_node_path
     */
    fun getEnableNodePath(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getEnableNodePathBind, segment)
    }

    @JvmInline
    value class EnableMode(val value: Long) {
        companion object {
            /**
             * Corresponds to `Node.PROCESS_MODE_INHERIT`.
             *
             * Generated from Godot docs: VisibleOnScreenEnabler3D.ENABLE_MODE_INHERIT
             */
            val INHERIT: EnableMode get() = EnableMode(0L)
            /**
             * Corresponds to `Node.PROCESS_MODE_ALWAYS`.
             *
             * Generated from Godot docs: VisibleOnScreenEnabler3D.ENABLE_MODE_ALWAYS
             */
            val ALWAYS: EnableMode get() = EnableMode(1L)
            /**
             * Corresponds to `Node.PROCESS_MODE_WHEN_PAUSED`.
             *
             * Generated from Godot docs: VisibleOnScreenEnabler3D.ENABLE_MODE_WHEN_PAUSED
             */
            val WHEN_PAUSED: EnableMode get() = EnableMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisibleOnScreenEnabler3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisibleOnScreenEnabler3D? =
            if (handle.address() == 0L) null else VisibleOnScreenEnabler3D(GodotHandle(handle))

        private const val SET_ENABLE_MODE_HASH = 320303646L
        private val setEnableModeBind by lazy {
            ObjectCalls.getMethodBind("VisibleOnScreenEnabler3D", "set_enable_mode", SET_ENABLE_MODE_HASH)
        }

        private const val GET_ENABLE_MODE_HASH = 3352990031L
        private val getEnableModeBind by lazy {
            ObjectCalls.getMethodBind("VisibleOnScreenEnabler3D", "get_enable_mode", GET_ENABLE_MODE_HASH)
        }

        private const val SET_ENABLE_NODE_PATH_HASH = 1348162250L
        private val setEnableNodePathBind by lazy {
            ObjectCalls.getMethodBind("VisibleOnScreenEnabler3D", "set_enable_node_path", SET_ENABLE_NODE_PATH_HASH)
        }

        private const val GET_ENABLE_NODE_PATH_HASH = 277076166L
        private val getEnableNodePathBind by lazy {
            ObjectCalls.getMethodBind("VisibleOnScreenEnabler3D", "get_enable_node_path", GET_ENABLE_NODE_PATH_HASH)
        }
    }
}

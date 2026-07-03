package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

/**
 * A rectangular region of 2D space that, when visible on screen, enables a target node.
 *
 * Generated from Godot docs: VisibleOnScreenEnabler2D
 */
class VisibleOnScreenEnabler2D(handle: MemorySegment) : VisibleOnScreenNotifier2D(handle) {
    var enableMode: Long
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
     * Generated from Godot docs: VisibleOnScreenEnabler2D.set_enable_mode
     */
    fun setEnableMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setEnableModeBind, handle, mode)
    }

    /**
     * Determines how the target node is enabled. Corresponds to `Node.ProcessMode`. When the node is
     * disabled, it always uses `Node.PROCESS_MODE_DISABLED`.
     *
     * Generated from Godot docs: VisibleOnScreenEnabler2D.get_enable_mode
     */
    fun getEnableMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getEnableModeBind, handle)
    }

    /**
     * The path to the target node, relative to the `VisibleOnScreenEnabler2D`. The target node is
     * cached; it's only assigned when setting this property (if the `VisibleOnScreenEnabler2D` is
     * inside the scene tree) and every time the `VisibleOnScreenEnabler2D` enters the scene tree. If
     * the path is empty, no node will be affected. If the path is invalid, an error is also generated.
     *
     * Generated from Godot docs: VisibleOnScreenEnabler2D.set_enable_node_path
     */
    fun setEnableNodePath(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setEnableNodePathBind, handle, path)
    }

    /**
     * The path to the target node, relative to the `VisibleOnScreenEnabler2D`. The target node is
     * cached; it's only assigned when setting this property (if the `VisibleOnScreenEnabler2D` is
     * inside the scene tree) and every time the `VisibleOnScreenEnabler2D` enters the scene tree. If
     * the path is empty, no node will be affected. If the path is invalid, an error is also generated.
     *
     * Generated from Godot docs: VisibleOnScreenEnabler2D.get_enable_node_path
     */
    fun getEnableNodePath(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getEnableNodePathBind, handle)
    }

    companion object {
        const val ENABLE_MODE_INHERIT: Long = 0L
        const val ENABLE_MODE_ALWAYS: Long = 1L
        const val ENABLE_MODE_WHEN_PAUSED: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisibleOnScreenEnabler2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisibleOnScreenEnabler2D? =
            if (handle.address() == 0L) null else VisibleOnScreenEnabler2D(handle)

        private const val SET_ENABLE_MODE_HASH = 2961788752L
        private val setEnableModeBind by lazy {
            ObjectCalls.getMethodBind("VisibleOnScreenEnabler2D", "set_enable_mode", SET_ENABLE_MODE_HASH)
        }

        private const val GET_ENABLE_MODE_HASH = 2650445576L
        private val getEnableModeBind by lazy {
            ObjectCalls.getMethodBind("VisibleOnScreenEnabler2D", "get_enable_mode", GET_ENABLE_MODE_HASH)
        }

        private const val SET_ENABLE_NODE_PATH_HASH = 1348162250L
        private val setEnableNodePathBind by lazy {
            ObjectCalls.getMethodBind("VisibleOnScreenEnabler2D", "set_enable_node_path", SET_ENABLE_NODE_PATH_HASH)
        }

        private const val GET_ENABLE_NODE_PATH_HASH = 277076166L
        private val getEnableNodePathBind by lazy {
            ObjectCalls.getMethodBind("VisibleOnScreenEnabler2D", "get_enable_node_path", GET_ENABLE_NODE_PATH_HASH)
        }
    }
}

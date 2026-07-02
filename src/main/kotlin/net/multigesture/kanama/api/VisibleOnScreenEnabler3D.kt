package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

/**
 * A box-shaped region of 3D space that, when visible on screen, enables a target node.
 *
 * Generated from Godot docs: VisibleOnScreenEnabler3D
 */
class VisibleOnScreenEnabler3D(handle: MemorySegment) : VisibleOnScreenNotifier3D(handle) {
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

    fun setEnableMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setEnableModeBind, handle, mode)
    }

    fun getEnableMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getEnableModeBind, handle)
    }

    fun setEnableNodePath(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setEnableNodePathBind, handle, path)
    }

    fun getEnableNodePath(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getEnableNodePathBind, handle)
    }

    companion object {
        const val ENABLE_MODE_INHERIT: Long = 0L
        const val ENABLE_MODE_ALWAYS: Long = 1L
        const val ENABLE_MODE_WHEN_PAUSED: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisibleOnScreenEnabler3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisibleOnScreenEnabler3D? =
            if (handle.address() == 0L) null else VisibleOnScreenEnabler3D(handle)

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

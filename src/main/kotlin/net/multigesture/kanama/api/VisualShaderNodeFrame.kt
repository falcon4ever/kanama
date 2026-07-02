package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * Generated from Godot docs: VisualShaderNodeFrame
 */
open class VisualShaderNodeFrame(handle: MemorySegment) : VisualShaderNodeResizableBase(handle) {
    var title: String
        @JvmName("titleProperty")
        get() = getTitle()
        @JvmName("setTitleProperty")
        set(value) = setTitle(value)

    var tintColorEnabled: Boolean
        @JvmName("tintColorEnabledProperty")
        get() = isTintColorEnabled()
        @JvmName("setTintColorEnabledProperty")
        set(value) = setTintColorEnabled(value)

    var tintColor: Color
        @JvmName("tintColorProperty")
        get() = getTintColor()
        @JvmName("setTintColorProperty")
        set(value) = setTintColor(value)

    var autoshrink: Boolean
        @JvmName("autoshrinkProperty")
        get() = isAutoshrinkEnabled()
        @JvmName("setAutoshrinkProperty")
        set(value) = setAutoshrinkEnabled(value)

    var attachedNodes: List<Int>
        @JvmName("attachedNodesProperty")
        get() = getAttachedNodes()
        @JvmName("setAttachedNodesProperty")
        set(value) = setAttachedNodes(value)

    fun setTitle(title: String) {
        ObjectCalls.ptrcallWithStringArg(setTitleBind, handle, title)
    }

    fun getTitle(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTitleBind, handle)
    }

    fun setTintColorEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setTintColorEnabledBind, handle, enable)
    }

    fun isTintColorEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isTintColorEnabledBind, handle)
    }

    fun setTintColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setTintColorBind, handle, color)
    }

    fun getTintColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getTintColorBind, handle)
    }

    fun setAutoshrinkEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutoshrinkEnabledBind, handle, enable)
    }

    fun isAutoshrinkEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAutoshrinkEnabledBind, handle)
    }

    fun addAttachedNode(node: Int) {
        ObjectCalls.ptrcallWithIntArg(addAttachedNodeBind, handle, node)
    }

    fun removeAttachedNode(node: Int) {
        ObjectCalls.ptrcallWithIntArg(removeAttachedNodeBind, handle, node)
    }

    fun setAttachedNodes(attachedNodes: List<Int>) {
        ObjectCalls.ptrcallWithPackedInt32ListArg(setAttachedNodesBind, handle, attachedNodes)
    }

    fun getAttachedNodes(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getAttachedNodesBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeFrame? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeFrame? =
            if (handle.address() == 0L) null else VisualShaderNodeFrame(handle)

        private const val SET_TITLE_HASH = 83702148L
        private val setTitleBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFrame", "set_title", SET_TITLE_HASH)
        }

        private const val GET_TITLE_HASH = 201670096L
        private val getTitleBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFrame", "get_title", GET_TITLE_HASH)
        }

        private const val SET_TINT_COLOR_ENABLED_HASH = 2586408642L
        private val setTintColorEnabledBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFrame", "set_tint_color_enabled", SET_TINT_COLOR_ENABLED_HASH)
        }

        private const val IS_TINT_COLOR_ENABLED_HASH = 36873697L
        private val isTintColorEnabledBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFrame", "is_tint_color_enabled", IS_TINT_COLOR_ENABLED_HASH)
        }

        private const val SET_TINT_COLOR_HASH = 2920490490L
        private val setTintColorBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFrame", "set_tint_color", SET_TINT_COLOR_HASH)
        }

        private const val GET_TINT_COLOR_HASH = 3444240500L
        private val getTintColorBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFrame", "get_tint_color", GET_TINT_COLOR_HASH)
        }

        private const val SET_AUTOSHRINK_ENABLED_HASH = 2586408642L
        private val setAutoshrinkEnabledBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFrame", "set_autoshrink_enabled", SET_AUTOSHRINK_ENABLED_HASH)
        }

        private const val IS_AUTOSHRINK_ENABLED_HASH = 36873697L
        private val isAutoshrinkEnabledBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFrame", "is_autoshrink_enabled", IS_AUTOSHRINK_ENABLED_HASH)
        }

        private const val ADD_ATTACHED_NODE_HASH = 1286410249L
        private val addAttachedNodeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFrame", "add_attached_node", ADD_ATTACHED_NODE_HASH)
        }

        private const val REMOVE_ATTACHED_NODE_HASH = 1286410249L
        private val removeAttachedNodeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFrame", "remove_attached_node", REMOVE_ATTACHED_NODE_HASH)
        }

        private const val SET_ATTACHED_NODES_HASH = 3614634198L
        private val setAttachedNodesBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFrame", "set_attached_nodes", SET_ATTACHED_NODES_HASH)
        }

        private const val GET_ATTACHED_NODES_HASH = 1930428628L
        private val getAttachedNodesBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFrame", "get_attached_nodes", GET_ATTACHED_NODES_HASH)
        }
    }
}

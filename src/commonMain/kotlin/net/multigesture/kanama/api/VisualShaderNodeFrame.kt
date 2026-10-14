package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color

/**
 * A frame other visual shader nodes can be attached to for better organization.
 *
 * Generated from Godot docs: VisualShaderNodeFrame
 */
open class VisualShaderNodeFrame(handle: GodotHandle) : VisualShaderNodeResizableBase(handle) {
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

    /**
     * The title of the node.
     *
     * Generated from Godot docs: VisualShaderNodeFrame.set_title
     */
    fun setTitle(title: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setTitleBind, segment, title)
    }

    /**
     * The title of the node.
     *
     * Generated from Godot docs: VisualShaderNodeFrame.get_title
     */
    fun getTitle(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getTitleBind, segment)
    }

    /**
     * If `true`, the frame will be tinted with the color specified in `tint_color`.
     *
     * Generated from Godot docs: VisualShaderNodeFrame.set_tint_color_enabled
     */
    fun setTintColorEnabled(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setTintColorEnabledBind, segment, enable)
    }

    /**
     * If `true`, the frame will be tinted with the color specified in `tint_color`.
     *
     * Generated from Godot docs: VisualShaderNodeFrame.is_tint_color_enabled
     */
    fun isTintColorEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isTintColorEnabledBind, segment)
    }

    /**
     * The color of the frame when `tint_color_enabled` is `true`.
     *
     * Generated from Godot docs: VisualShaderNodeFrame.set_tint_color
     */
    fun setTintColor(color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(Binds.setTintColorBind, segment, color)
    }

    /**
     * The color of the frame when `tint_color_enabled` is `true`.
     *
     * Generated from Godot docs: VisualShaderNodeFrame.get_tint_color
     */
    fun getTintColor(): Color {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getTintColorBind, segment)
    }

    /**
     * If `true`, the frame will automatically resize to enclose all attached nodes.
     *
     * Generated from Godot docs: VisualShaderNodeFrame.set_autoshrink_enabled
     */
    fun setAutoshrinkEnabled(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setAutoshrinkEnabledBind, segment, enable)
    }

    /**
     * If `true`, the frame will automatically resize to enclose all attached nodes.
     *
     * Generated from Godot docs: VisualShaderNodeFrame.is_autoshrink_enabled
     */
    fun isAutoshrinkEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAutoshrinkEnabledBind, segment)
    }

    /**
     * Adds a node to the list of nodes attached to the frame. Should not be called directly, use the
     * `VisualShader.attach_node_to_frame` method instead.
     *
     * Generated from Godot docs: VisualShaderNodeFrame.add_attached_node
     */
    fun addAttachedNode(node: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.addAttachedNodeBind, segment, node)
    }

    /**
     * Removes a node from the list of nodes attached to the frame. Should not be called directly, use
     * the `VisualShader.detach_node_from_frame` method instead.
     *
     * Generated from Godot docs: VisualShaderNodeFrame.remove_attached_node
     */
    fun removeAttachedNode(node: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removeAttachedNodeBind, segment, node)
    }

    /**
     * The list of nodes attached to the frame.
     *
     * Generated from Godot docs: VisualShaderNodeFrame.set_attached_nodes
     */
    fun setAttachedNodes(attachedNodes: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedInt32ListArg(Binds.setAttachedNodesBind, segment, attachedNodes)
    }

    /**
     * The list of nodes attached to the frame.
     *
     * Generated from Godot docs: VisualShaderNodeFrame.get_attached_nodes
     */
    fun getAttachedNodes(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(Binds.getAttachedNodesBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeFrame? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeFrame? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeFrame(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeFrame? =
            if (handle.address() == 0L) null else VisualShaderNodeFrame(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_TITLE_HASH = 83702148L
        @JvmField
        val setTitleBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFrame", "set_title", SET_TITLE_HASH)

        private const val GET_TITLE_HASH = 201670096L
        @JvmField
        val getTitleBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFrame", "get_title", GET_TITLE_HASH)

        private const val SET_TINT_COLOR_ENABLED_HASH = 2586408642L
        @JvmField
        val setTintColorEnabledBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFrame", "set_tint_color_enabled", SET_TINT_COLOR_ENABLED_HASH)

        private const val IS_TINT_COLOR_ENABLED_HASH = 36873697L
        @JvmField
        val isTintColorEnabledBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFrame", "is_tint_color_enabled", IS_TINT_COLOR_ENABLED_HASH)

        private const val SET_TINT_COLOR_HASH = 2920490490L
        @JvmField
        val setTintColorBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFrame", "set_tint_color", SET_TINT_COLOR_HASH)

        private const val GET_TINT_COLOR_HASH = 3444240500L
        @JvmField
        val getTintColorBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFrame", "get_tint_color", GET_TINT_COLOR_HASH)

        private const val SET_AUTOSHRINK_ENABLED_HASH = 2586408642L
        @JvmField
        val setAutoshrinkEnabledBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFrame", "set_autoshrink_enabled", SET_AUTOSHRINK_ENABLED_HASH)

        private const val IS_AUTOSHRINK_ENABLED_HASH = 36873697L
        @JvmField
        val isAutoshrinkEnabledBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFrame", "is_autoshrink_enabled", IS_AUTOSHRINK_ENABLED_HASH)

        private const val ADD_ATTACHED_NODE_HASH = 1286410249L
        @JvmField
        val addAttachedNodeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFrame", "add_attached_node", ADD_ATTACHED_NODE_HASH)

        private const val REMOVE_ATTACHED_NODE_HASH = 1286410249L
        @JvmField
        val removeAttachedNodeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFrame", "remove_attached_node", REMOVE_ATTACHED_NODE_HASH)

        private const val SET_ATTACHED_NODES_HASH = 3614634198L
        @JvmField
        val setAttachedNodesBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFrame", "set_attached_nodes", SET_ATTACHED_NODES_HASH)

        private const val GET_ATTACHED_NODES_HASH = 1930428628L
        @JvmField
        val getAttachedNodesBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFrame", "get_attached_nodes", GET_ATTACHED_NODES_HASH)
    }
}

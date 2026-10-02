package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath

/**
 * A node used for advanced animation transitions in an `AnimationPlayer`.
 *
 * Generated from Godot docs: AnimationTree
 */
class AnimationTree(handle: GodotHandle) : AnimationMixer(handle) {
    var treeRoot: AnimationRootNode?
        @JvmName("treeRootProperty")
        get() = getTreeRoot()
        @JvmName("setTreeRootProperty")
        set(value) = setTreeRoot(value)

    var advanceExpressionBaseNode: NodePath
        @JvmName("advanceExpressionBaseNodeProperty")
        get() = getAdvanceExpressionBaseNode()
        @JvmName("setAdvanceExpressionBaseNodeProperty")
        set(value) = setAdvanceExpressionBaseNode(value)

    var animPlayer: NodePath
        @JvmName("animPlayerProperty")
        get() = getAnimationPlayer()
        @JvmName("setAnimPlayerProperty")
        set(value) = setAnimationPlayer(value)

    /**
     * The root animation node of this `AnimationTree`. See `AnimationRootNode`.
     *
     * Generated from Godot docs: AnimationTree.set_tree_root
     */
    fun setTreeRoot(animationNode: AnimationRootNode?) {
        ObjectCalls.ptrcallWithObjectArgs(setTreeRootBind, segment, listOf(animationNode?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The root animation node of this `AnimationTree`. See `AnimationRootNode`.
     *
     * Generated from Godot docs: AnimationTree.get_tree_root
     */
    fun getTreeRoot(): AnimationRootNode? {
        return AnimationRootNode.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTreeRootBind, segment))
    }

    /**
     * The path to the `Node` used to evaluate the `AnimationNode` `Expression` if one is not
     * explicitly specified internally.
     *
     * Generated from Godot docs: AnimationTree.set_advance_expression_base_node
     */
    fun setAdvanceExpressionBaseNode(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setAdvanceExpressionBaseNodeBind, segment, path)
    }

    /**
     * The path to the `Node` used to evaluate the `AnimationNode` `Expression` if one is not
     * explicitly specified internally.
     *
     * Generated from Godot docs: AnimationTree.get_advance_expression_base_node
     */
    fun getAdvanceExpressionBaseNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getAdvanceExpressionBaseNodeBind, segment)
    }

    /**
     * The path to the `AnimationPlayer` used for animating.
     *
     * Generated from Godot docs: AnimationTree.set_animation_player
     */
    fun setAnimationPlayer(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setAnimationPlayerBind, segment, path)
    }

    /**
     * The path to the `AnimationPlayer` used for animating.
     *
     * Generated from Godot docs: AnimationTree.get_animation_player
     */
    fun getAnimationPlayer(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getAnimationPlayerBind, segment)
    }

    /**
     * Sets the process notification in which to update animations.
     *
     * Generated from Godot docs: AnimationTree.set_process_callback
     */
    fun setProcessCallback(mode: AnimationTree.AnimationProcessCallback) {
        ObjectCalls.ptrcallWithLongArg(setProcessCallbackBind, segment, mode.value)
    }

    /**
     * Returns the process notification in which to update animations.
     *
     * Generated from Godot docs: AnimationTree.get_process_callback
     */
    fun getProcessCallback(): AnimationTree.AnimationProcessCallback {
        return AnimationTree.AnimationProcessCallback(ObjectCalls.ptrcallNoArgsRetLong(getProcessCallbackBind, segment))
    }

    object Signals {
        const val animationPlayerChanged: String = "animation_player_changed"
    }

    @JvmInline
    value class AnimationProcessCallback(override val value: Long) : GodotEnumValue {
        companion object {
            val PHYSICS: AnimationProcessCallback get() = AnimationProcessCallback(0L)
            val IDLE: AnimationProcessCallback get() = AnimationProcessCallback(1L)
            val MANUAL: AnimationProcessCallback get() = AnimationProcessCallback(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationTree? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AnimationTree? =
            if (handle.address() == 0L) null else AnimationTree(GodotHandle(handle))

        private const val SET_TREE_ROOT_HASH = 2581683800L
        private val setTreeRootBind by lazy {
            ObjectCalls.getMethodBind("AnimationTree", "set_tree_root", SET_TREE_ROOT_HASH)
        }

        private const val GET_TREE_ROOT_HASH = 4110384712L
        private val getTreeRootBind by lazy {
            ObjectCalls.getMethodBind("AnimationTree", "get_tree_root", GET_TREE_ROOT_HASH)
        }

        private const val SET_ADVANCE_EXPRESSION_BASE_NODE_HASH = 1348162250L
        private val setAdvanceExpressionBaseNodeBind by lazy {
            ObjectCalls.getMethodBind("AnimationTree", "set_advance_expression_base_node", SET_ADVANCE_EXPRESSION_BASE_NODE_HASH)
        }

        private const val GET_ADVANCE_EXPRESSION_BASE_NODE_HASH = 4075236667L
        private val getAdvanceExpressionBaseNodeBind by lazy {
            ObjectCalls.getMethodBind("AnimationTree", "get_advance_expression_base_node", GET_ADVANCE_EXPRESSION_BASE_NODE_HASH)
        }

        private const val SET_ANIMATION_PLAYER_HASH = 1348162250L
        private val setAnimationPlayerBind by lazy {
            ObjectCalls.getMethodBind("AnimationTree", "set_animation_player", SET_ANIMATION_PLAYER_HASH)
        }

        private const val GET_ANIMATION_PLAYER_HASH = 4075236667L
        private val getAnimationPlayerBind by lazy {
            ObjectCalls.getMethodBind("AnimationTree", "get_animation_player", GET_ANIMATION_PLAYER_HASH)
        }

        private const val SET_PROCESS_CALLBACK_HASH = 1723352826L
        private val setProcessCallbackBind by lazy {
            ObjectCalls.getMethodBind("AnimationTree", "set_process_callback", SET_PROCESS_CALLBACK_HASH)
        }

        private const val GET_PROCESS_CALLBACK_HASH = 891317132L
        private val getProcessCallbackBind by lazy {
            ObjectCalls.getMethodBind("AnimationTree", "get_process_callback", GET_PROCESS_CALLBACK_HASH)
        }
    }
}

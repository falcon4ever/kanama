package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

// GENERATED desktop/Android companion for Control (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Control waits on: ptrcallWithNodePathListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * The paths to the nodes which are controlled by this node.
 *
 * Generated from Godot docs: Control.set_accessibility_controls_nodes
 */
fun Control.setAccessibilityControlsNodes(nodePath: List<NodePath>) {
    ObjectCalls.ptrcallWithNodePathListArg(setAccessibilityControlsNodesBind, handle, nodePath)
}

/**
 * The paths to the nodes which are describing this node.
 *
 * Generated from Godot docs: Control.set_accessibility_described_by_nodes
 */
fun Control.setAccessibilityDescribedByNodes(nodePath: List<NodePath>) {
    ObjectCalls.ptrcallWithNodePathListArg(setAccessibilityDescribedByNodesBind, handle, nodePath)
}

/**
 * The paths to the nodes which label this node.
 *
 * Generated from Godot docs: Control.set_accessibility_labeled_by_nodes
 */
fun Control.setAccessibilityLabeledByNodes(nodePath: List<NodePath>) {
    ObjectCalls.ptrcallWithNodePathListArg(setAccessibilityLabeledByNodesBind, handle, nodePath)
}

/**
 * The paths to the nodes which this node flows into.
 *
 * Generated from Godot docs: Control.set_accessibility_flow_to_nodes
 */
fun Control.setAccessibilityFlowToNodes(nodePath: List<NodePath>) {
    ObjectCalls.ptrcallWithNodePathListArg(setAccessibilityFlowToNodesBind, handle, nodePath)
}

private const val SET_ACCESSIBILITY_CONTROLS_NODES_HASH = 381264803L
private val setAccessibilityControlsNodesBind by lazy {
    ObjectCalls.getMethodBind("Control", "set_accessibility_controls_nodes", SET_ACCESSIBILITY_CONTROLS_NODES_HASH)
}

private const val SET_ACCESSIBILITY_DESCRIBED_BY_NODES_HASH = 381264803L
private val setAccessibilityDescribedByNodesBind by lazy {
    ObjectCalls.getMethodBind("Control", "set_accessibility_described_by_nodes", SET_ACCESSIBILITY_DESCRIBED_BY_NODES_HASH)
}

private const val SET_ACCESSIBILITY_LABELED_BY_NODES_HASH = 381264803L
private val setAccessibilityLabeledByNodesBind by lazy {
    ObjectCalls.getMethodBind("Control", "set_accessibility_labeled_by_nodes", SET_ACCESSIBILITY_LABELED_BY_NODES_HASH)
}

private const val SET_ACCESSIBILITY_FLOW_TO_NODES_HASH = 381264803L
private val setAccessibilityFlowToNodesBind by lazy {
    ObjectCalls.getMethodBind("Control", "set_accessibility_flow_to_nodes", SET_ACCESSIBILITY_FLOW_TO_NODES_HASH)
}

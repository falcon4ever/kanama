package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.RID

/**
 * Base class for all scene objects.
 *
 * Generated from Godot docs: Node
 */
open class Node(handle: GodotHandle) : GodotObject(handle) {
    var name: String
        @JvmName("nameProperty")
        get() = getName()
        @JvmName("setNameProperty")
        set(value) = setName(value)

    var uniqueNameInOwner: Boolean
        @JvmName("uniqueNameInOwnerProperty")
        get() = isUniqueNameInOwner()
        @JvmName("setUniqueNameInOwnerProperty")
        set(value) = setUniqueNameInOwner(value)

    var sceneFilePath: String
        @JvmName("sceneFilePathProperty")
        get() = getSceneFilePath()
        @JvmName("setSceneFilePathProperty")
        set(value) = setSceneFilePath(value)

    var owner: Node?
        @JvmName("ownerProperty")
        get() = getOwner()
        @JvmName("setOwnerProperty")
        set(value) = setOwner(value)

    val multiplayer: MultiplayerAPI?
        @JvmName("multiplayerProperty")
        get() = getMultiplayer()

    var processMode: Long
        @JvmName("processModeProperty")
        get() = getProcessMode()
        @JvmName("setProcessModeProperty")
        set(value) = setProcessMode(value)

    var processPriority: Int
        @JvmName("processPriorityProperty")
        get() = getProcessPriority()
        @JvmName("setProcessPriorityProperty")
        set(value) = setProcessPriority(value)

    var processPhysicsPriority: Int
        @JvmName("processPhysicsPriorityProperty")
        get() = getPhysicsProcessPriority()
        @JvmName("setProcessPhysicsPriorityProperty")
        set(value) = setPhysicsProcessPriority(value)

    var processThreadGroup: Long
        @JvmName("processThreadGroupProperty")
        get() = getProcessThreadGroup()
        @JvmName("setProcessThreadGroupProperty")
        set(value) = setProcessThreadGroup(value)

    var processThreadGroupOrder: Int
        @JvmName("processThreadGroupOrderProperty")
        get() = getProcessThreadGroupOrder()
        @JvmName("setProcessThreadGroupOrderProperty")
        set(value) = setProcessThreadGroupOrder(value)

    var processThreadMessages: Long
        @JvmName("processThreadMessagesProperty")
        get() = getProcessThreadMessages()
        @JvmName("setProcessThreadMessagesProperty")
        set(value) = setProcessThreadMessages(value)

    var physicsInterpolationMode: Long
        @JvmName("physicsInterpolationModeProperty")
        get() = getPhysicsInterpolationMode()
        @JvmName("setPhysicsInterpolationModeProperty")
        set(value) = setPhysicsInterpolationMode(value)

    var autoTranslateMode: Long
        @JvmName("autoTranslateModeProperty")
        get() = getAutoTranslateMode()
        @JvmName("setAutoTranslateModeProperty")
        set(value) = setAutoTranslateMode(value)

    var editorDescription: String
        @JvmName("editorDescriptionProperty")
        get() = getEditorDescription()
        @JvmName("setEditorDescriptionProperty")
        set(value) = setEditorDescription(value)

    /**
     * Adds a `sibling` node to this node's parent, and moves the added sibling right below this node.
     * If `force_readable_name` is `true`, improves the readability of the added `sibling`. If not
     * named, the `sibling` is renamed to its type, and if it shares `name` with a sibling, a number is
     * suffixed more appropriately. This operation is very slow. As such, it is recommended leaving
     * this to `false`, which assigns a dummy name featuring `@` in both situations. Use `add_child`
     * instead of this method if you don't need the child node to be added below a specific node in the
     * list of children. Note: If this node is internal, the added sibling will be internal too (see
     * `add_child`'s `internal` parameter).
     *
     * Generated from Godot docs: Node.add_sibling
     */
    fun addSibling(sibling: Node, forceReadableName: Boolean = false) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(addSiblingBind, segment, sibling.segment, forceReadableName)
    }

    /**
     * The name of the node. This name must be unique among the siblings (other child nodes from the
     * same parent). When set to an existing sibling's name, the node is automatically renamed. Note:
     * When changing the name, the following characters will be replaced with an underscore: (`.` `:`
     * `@` `/` `"` `%`). In particular, the `@` character is reserved for auto-generated names. See
     * also `String.validate_node_name`.
     *
     * Generated from Godot docs: Node.set_name
     */
    fun setName(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(setNameBind, segment, name)
    }

    /**
     * The name of the node. This name must be unique among the siblings (other child nodes from the
     * same parent). When set to an existing sibling's name, the node is automatically renamed. Note:
     * When changing the name, the following characters will be replaced with an underscore: (`.` `:`
     * `@` `/` `"` `%`). In particular, the `@` character is reserved for auto-generated names. See
     * also `String.validate_node_name`.
     *
     * Generated from Godot docs: Node.get_name
     */
    fun getName(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getNameBind, segment)
    }

    /**
     * Adds a child `node`. Nodes can have any number of children, but every child must have a unique
     * name. Child nodes are automatically deleted when the parent node is deleted, so an entire scene
     * can be removed by deleting its topmost node. If `force_readable_name` is `true`, improves the
     * readability of the added `node`. If not named, the `node` is renamed to its type, and if it
     * shares `name` with a sibling, a number is suffixed more appropriately. This operation is very
     * slow. As such, it is recommended leaving this to `false`, which assigns a dummy name featuring
     * `@` in both situations. If `internal` is different than `INTERNAL_MODE_DISABLED`, the child will
     * be added as internal node. These nodes are ignored by methods like `get_children`, unless their
     * parameter `include_internal` is `true`. It also prevents these nodes being duplicated with their
     * parent. The intended usage is to hide the internal nodes from the user, so the user won't
     * accidentally delete or modify them. Used by some GUI nodes, e.g. `ColorPicker`. Note: If `node`
     * already has a parent, this method will fail. Use `remove_child` first to remove `node` from its
     * current parent. For example:
     *
     * Generated from Godot docs: Node.add_child
     */
    fun addChild(node: Node, forceReadableName: Boolean = false, internalValue: Long = 0L) {
        ObjectCalls.ptrcallWithObjectBoolLongArgs(addChildBind, segment, node.segment, forceReadableName, internalValue)
    }

    /**
     * Removes a child `node`. The `node`, along with its children, are not deleted. To delete a node,
     * see `queue_free`. Note: When this node is inside the tree, this method sets the `owner` of the
     * removed `node` (or its descendants) to `null`, if their `owner` is no longer an ancestor (see
     * `is_ancestor_of`).
     *
     * Generated from Godot docs: Node.remove_child
     */
    fun removeChild(node: Node) {
        ObjectCalls.ptrcallWithObjectArgs(removeChildBind, segment, listOf(node.segment))
    }

    /**
     * Changes the parent of this `Node` to the `new_parent`. The node needs to already have a parent.
     * The node's `owner` is preserved if its owner is still reachable from the new location (i.e., the
     * node is still a descendant of the new parent after the operation). If `keep_global_transform` is
     * `true`, the node's global transform will be preserved if supported. `Node2D`, `Node3D` and
     * `Control` support this argument (but `Control` keeps only position). Warning: If
     * `ProjectSettings.physics/common/physics_interpolation` is enabled and reparenting causes a large
     * change in global transform, the object may appear to move from its old position to its new one
     * over the next physics tick. To avoid this, call `reset_physics_interpolation` after reparenting.
     *
     * Generated from Godot docs: Node.reparent
     */
    fun reparent(newParent: Node, keepGlobalTransform: Boolean = true) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(reparentBind, segment, newParent.segment, keepGlobalTransform)
    }

    /**
     * Returns the number of children of this node. If `include_internal` is `false`, internal children
     * are not counted (see `add_child`'s `internal` parameter).
     *
     * Generated from Godot docs: Node.get_child_count
     */
    fun getChildCount(includeInternal: Boolean = false): Int {
        return ObjectCalls.ptrcallWithBoolArgRetInt(getChildCountBind, segment, includeInternal)
    }

    /**
     * Returns all children of this node inside an `Array`. If `include_internal` is `false`, excludes
     * internal children from the returned array (see `add_child`'s `internal` parameter).
     *
     * Generated from Godot docs: Node.get_children
     */
    fun getChildren(includeInternal: Boolean = false): List<Node> {
        return ObjectCalls.ptrcallWithBoolArgRetTypedObjectList(getChildrenBind, segment, includeInternal, Node::wrap)
    }

    /**
     * Fetches a child node by its index. Each child node has an index relative to its siblings (see
     * `get_index`). The first child is at index 0. Negative values can also be used to start from the
     * end of the list. This method can be used in combination with `get_child_count` to iterate over
     * this node's children. If no child exists at the given index, this method returns `null` and an
     * error is generated. If `include_internal` is `false`, internal children are ignored (see
     * `add_child`'s `internal` parameter).
     *
     * Generated from Godot docs: Node.get_child
     */
    fun getChild(idx: Int, includeInternal: Boolean = false): Node? {
        return Node.wrap(ObjectCalls.ptrcallWithIntAndBoolArgsRetObject(getChildBind, segment, idx, includeInternal))
    }

    /**
     * Returns `true` if the `path` points to a valid node. See also `get_node`.
     *
     * Generated from Godot docs: Node.has_node
     */
    fun hasNode(path: NodePath): Boolean {
        return ObjectCalls.ptrcallWithNodePathArgRetBool(hasNodeBind, segment, path)
    }

    /**
     * Fetches a node. The `NodePath` can either be a relative path (from this node), or an absolute
     * path (from the `SceneTree.root`) to a node. If `path` does not point to a valid node, generates
     * an error and returns `null`. Attempts to access methods on the return value will result in an
     * "Attempt to call <method> on a null instance." error. Note: Fetching by absolute path only works
     * when the node is inside the scene tree (see `is_inside_tree`).
     *
     * Generated from Godot docs: Node.get_node
     */
    fun getNode(path: NodePath): Node? {
        return Node.wrap(ObjectCalls.ptrcallWithNodePathArgRetObject(getNodeBind, segment, path))
    }

    /**
     * Fetches a node by `NodePath`. Similar to `get_node`, but does not generate an error if `path`
     * does not point to a valid node.
     *
     * Generated from Godot docs: Node.get_node_or_null
     */
    fun getNodeOrNull(path: NodePath): Node? {
        return Node.wrap(ObjectCalls.ptrcallWithNodePathArgRetObject(getNodeOrNullBind, segment, path))
    }

    /**
     * Returns this node's parent node, or `null` if the node doesn't have a parent.
     *
     * Generated from Godot docs: Node.get_parent
     */
    fun getParent(): Node? {
        return Node.wrap(ObjectCalls.ptrcallNoArgsRetObject(getParentBind, segment))
    }

    /**
     * Finds the first descendant of this node whose `name` matches `pattern`, returning `null` if no
     * match is found. The matching is done against node names, not their paths, through
     * `String.match`. As such, it is case-sensitive, `"*"` matches zero or more characters, and `"?"`
     * matches any single character. If `recursive` is `false`, only this node's direct children are
     * checked. Nodes are checked in tree order, so this node's first direct child is checked first,
     * then its own direct children, etc., before moving to the second direct child, and so on.
     * Internal children are also included in the search (see `internal` parameter in `add_child`). If
     * `owned` is `true`, only descendants with a valid `owner` node are checked. Note: This method can
     * be very slow. Consider storing a reference to the found node in a variable. Alternatively, use
     * `get_node` with unique names (see `unique_name_in_owner`). Note: To find all descendant nodes
     * matching a pattern or a class type, see `find_children`.
     *
     * Generated from Godot docs: Node.find_child
     */
    fun findChild(pattern: String, recursive: Boolean = true, owned: Boolean = true): Node? {
        return Node.wrap(ObjectCalls.ptrcallWithStringAndTwoBoolArgsRetObject(findChildBind, segment, pattern, recursive, owned))
    }

    /**
     * Finds all descendants of this node whose names match `pattern`, returning an empty `Array` if no
     * match is found. The matching is done against node names, not their paths, through
     * `String.match`. As such, it is case-sensitive, `"*"` matches zero or more characters, and `"?"`
     * matches any single character. If `type` is not empty, only descendants inheriting from `type`
     * are included (see `Object.is_class`). If `recursive` is `false`, only this node's direct
     * children are checked. Nodes are checked in tree order, so this node's first direct child is
     * checked first, then its own direct children, etc., before moving to the second direct child, and
     * so on. Internal children are also included in the search (see `internal` parameter in
     * `add_child`). If `owned` is `true`, only descendants with a valid `owner` node are checked.
     * Note: This method can be very slow. Consider storing references to the found nodes in a
     * variable. Note: To find a single descendant node matching a pattern, see `find_child`.
     *
     * Generated from Godot docs: Node.find_children
     */
    fun findChildren(pattern: String, type: String = "", recursive: Boolean = true, owned: Boolean = true): List<Node> {
        return ObjectCalls.ptrcallWithTwoStringAndTwoBoolArgsRetTypedObjectList(findChildrenBind, segment, pattern, type, recursive, owned, Node::wrap)
    }

    /**
     * Finds the first ancestor of this node whose `name` matches `pattern`, returning `null` if no
     * match is found. The matching is done through `String.match`. As such, it is case-sensitive,
     * `"*"` matches zero or more characters, and `"?"` matches any single character. See also
     * `find_child` and `find_children`. Note: As this method walks upwards in the scene tree, it can
     * be slow in large, deeply nested nodes. Consider storing a reference to the found node in a
     * variable. Alternatively, use `get_node` with unique names (see `unique_name_in_owner`).
     *
     * Generated from Godot docs: Node.find_parent
     */
    fun findParent(pattern: String): Node? {
        return Node.wrap(ObjectCalls.ptrcallWithStringArgRetObject(findParentBind, segment, pattern))
    }

    /**
     * Returns `true` if `path` points to a valid node and its subnames point to a valid `Resource`,
     * e.g. `Area2D/CollisionShape2D:shape`. Properties that are not `Resource` types (such as nodes or
     * other `Variant` types) are not considered. See also `get_node_and_resource`.
     *
     * Generated from Godot docs: Node.has_node_and_resource
     */
    fun hasNodeAndResource(path: NodePath): Boolean {
        return ObjectCalls.ptrcallWithNodePathArgRetBool(hasNodeAndResourceBind, segment, path)
    }

    /**
     * Fetches a node and its most nested resource as specified by the `NodePath`'s subname. Returns an
     * `Array` of size `3` where: - Element `0` is the `Node`, or `null` if not found; - Element `1` is
     * the subname's last nested `Resource`, or `null` if not found; - Element `2` is the remaining
     * `NodePath`, referring to an existing, non-`Resource` property (see `Object.get_indexed`).
     *
     * Generated from Godot docs: Node.get_node_and_resource
     */
    fun getNodeAndResource(path: NodePath): List<Any?> {
        return ObjectCalls.ptrcallWithNodePathArgRetArray(getNodeAndResourceBind, segment, path)
    }

    /**
     * Returns `true` if this node is currently inside a `SceneTree`. See also `get_tree`.
     *
     * Generated from Godot docs: Node.is_inside_tree
     */
    fun isInsideTree(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isInsideTreeBind, segment)
    }

    /**
     * Returns `true` if the node is part of the scene currently opened in the editor.
     *
     * Generated from Godot docs: Node.is_part_of_edited_scene
     */
    fun isPartOfEditedScene(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPartOfEditedSceneBind, segment)
    }

    /**
     * Returns `true` if the given `node` is a direct or indirect child of this node.
     *
     * Generated from Godot docs: Node.is_ancestor_of
     */
    fun isAncestorOf(node: Node): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(isAncestorOfBind, segment, node.segment)
    }

    /**
     * Returns `true` if the given `node` occurs later in the scene hierarchy than this node. A node
     * occurring later is usually processed last.
     *
     * Generated from Godot docs: Node.is_greater_than
     */
    fun isGreaterThan(node: Node): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(isGreaterThanBind, segment, node.segment)
    }

    /**
     * Returns the node's absolute path, relative to the `SceneTree.root`. If the node is not inside
     * the scene tree, this method fails and returns an empty `NodePath`.
     *
     * Generated from Godot docs: Node.get_path
     */
    fun getPath(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getPathBind, segment)
    }

    /**
     * Returns the relative `NodePath` from this node to the specified `node`. Both nodes must be in
     * the same `SceneTree` or scene hierarchy, otherwise this method fails and returns an empty
     * `NodePath`. If `use_unique_path` is `true`, returns the shortest path accounting for this node's
     * unique name (see `unique_name_in_owner`). Note: If you get a relative path which starts from a
     * unique node, the path may be longer than a normal relative path, due to the addition of the
     * unique node's name.
     *
     * Generated from Godot docs: Node.get_path_to
     */
    fun getPathTo(node: Node, useUniquePath: Boolean = false): NodePath {
        return ObjectCalls.ptrcallWithObjectAndBoolArgRetNodePath(getPathToBind, segment, node.segment, useUniquePath)
    }

    /**
     * Adds the node to the `group`. Groups can be helpful to organize a subset of nodes, for example
     * `"enemies"` or `"collectables"`. See notes in the description, and the group methods in
     * `SceneTree`. If `persistent` is `true`, the group will be stored when saved inside a
     * `PackedScene`. All groups created and displayed in the Groups dock are persistent. Note: To
     * improve performance, the order of group names is not guaranteed and may vary between project
     * runs. Therefore, do not rely on the group order. Note: `SceneTree`'s group methods will not work
     * on this node if not inside the tree (see `is_inside_tree`).
     *
     * Generated from Godot docs: Node.add_to_group
     */
    fun addToGroup(group: String, persistent: Boolean = false) {
        ObjectCalls.ptrcallWithStringNameAndBoolArg(addToGroupBind, segment, group, persistent)
    }

    /**
     * Removes the node from the given `group`. Does nothing if the node is not in the `group`. See
     * also notes in the description, and the `SceneTree`'s group methods.
     *
     * Generated from Godot docs: Node.remove_from_group
     */
    fun removeFromGroup(group: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeFromGroupBind, segment, group)
    }

    /**
     * Returns `true` if this node has been added to the given `group`. See `add_to_group` and
     * `remove_from_group`. See also notes in the description, and the `SceneTree`'s group methods.
     *
     * Generated from Godot docs: Node.is_in_group
     */
    fun isInGroup(group: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(isInGroupBind, segment, group)
    }

    /**
     * Moves `child_node` to the given index. A node's index is the order among its siblings. If
     * `to_index` is negative, the index is counted from the end of the list. See also `get_child` and
     * `get_index`. Note: The processing order of several engine callbacks (`_ready`, `_process`, etc.)
     * and notifications sent through `propagate_notification` is affected by tree order. `CanvasItem`
     * nodes are also rendered in tree order. See also `process_priority`.
     *
     * Generated from Godot docs: Node.move_child
     */
    fun moveChild(childNode: Node, toIndex: Int) {
        ObjectCalls.ptrcallWithObjectAndIntArg(moveChildBind, segment, childNode.segment, toIndex)
    }

    /**
     * Returns an `Array` of group names that the node has been added to. Note: To improve performance,
     * the order of group names is not guaranteed and may vary between project runs. Therefore, do not
     * rely on the group order. Note: This method may also return some group names starting with an
     * underscore (`_`). These are internally used by the engine. To avoid conflicts, do not use custom
     * groups starting with underscores. To exclude internal groups, see the following code snippet:
     *
     * Generated from Godot docs: Node.get_groups
     */
    fun getGroups(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetStringNameList(getGroupsBind, segment)
    }

    /**
     * The owner of this node. The owner must be an ancestor of this node. When packing the owner node
     * in a `PackedScene`, all the nodes it owns are also saved with it. See also
     * `unique_name_in_owner`. Note: In the editor, nodes not owned by the scene root are usually not
     * displayed in the Scene dock, and will not be saved. To prevent this, remember to set the owner
     * after calling `add_child`. Note: The owner needs to be the current scene root. See Instancing
     * scenes ($DOCS_URL/tutorials/plugins/running_code_in_the_editor.html#instancing-scenes) in the
     * documentation for more information.
     *
     * Generated from Godot docs: Node.set_owner
     */
    fun setOwner(owner: Node?) {
        ObjectCalls.ptrcallWithObjectArgs(setOwnerBind, segment, listOf(owner?.segment ?: NULL_SEGMENT))
    }

    /**
     * The owner of this node. The owner must be an ancestor of this node. When packing the owner node
     * in a `PackedScene`, all the nodes it owns are also saved with it. See also
     * `unique_name_in_owner`. Note: In the editor, nodes not owned by the scene root are usually not
     * displayed in the Scene dock, and will not be saved. To prevent this, remember to set the owner
     * after calling `add_child`. Note: The owner needs to be the current scene root. See Instancing
     * scenes ($DOCS_URL/tutorials/plugins/running_code_in_the_editor.html#instancing-scenes) in the
     * documentation for more information.
     *
     * Generated from Godot docs: Node.get_owner
     */
    fun getOwner(): Node? {
        return Node.wrap(ObjectCalls.ptrcallNoArgsRetObject(getOwnerBind, segment))
    }

    /**
     * Returns this node's order among its siblings. The first node's index is `0`. See also
     * `get_child`. If `include_internal` is `false`, returns the index ignoring internal children. The
     * first, non-internal child will have an index of `0` (see `add_child`'s `internal` parameter).
     *
     * Generated from Godot docs: Node.get_index
     */
    fun getIndex(includeInternal: Boolean = false): Int {
        return ObjectCalls.ptrcallWithBoolArgRetInt(getIndexBind, segment, includeInternal)
    }

    /**
     * Prints the node and its children to the console, recursively. The node does not have to be
     * inside the tree. This method outputs `NodePath`s relative to this node, and is good for
     * copy/pasting into `get_node`. See also `print_tree_pretty`. May print, for example:
     *
     * Generated from Godot docs: Node.print_tree
     */
    fun printTree() {
        ObjectCalls.ptrcallNoArgs(printTreeBind, segment)
    }

    /**
     * Prints the node and its children to the console, recursively. The node does not have to be
     * inside the tree. Similar to `print_tree`, but the graphical representation looks like what is
     * displayed in the editor's Scene dock. It is useful for inspecting larger trees. May print, for
     * example:
     *
     * Generated from Godot docs: Node.print_tree_pretty
     */
    fun printTreePretty() {
        ObjectCalls.ptrcallNoArgs(printTreePrettyBind, segment)
    }

    /**
     * Returns the tree as a `String`. Used mainly for debugging purposes. This version displays the
     * path relative to the current node, and is good for copy/pasting into the `get_node` function. It
     * also can be used in game UI/UX. May print, for example:
     *
     * Generated from Godot docs: Node.get_tree_string
     */
    fun getTreeString(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTreeStringBind, segment)
    }

    /**
     * Similar to `get_tree_string`, this returns the tree as a `String`. This version displays a more
     * graphical representation similar to what is displayed in the Scene Dock. It is useful for
     * inspecting larger trees. May print, for example:
     *
     * Generated from Godot docs: Node.get_tree_string_pretty
     */
    fun getTreeStringPretty(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTreeStringPrettyBind, segment)
    }

    /**
     * The original scene's file path, if the node has been instantiated from a `PackedScene` file.
     * Only scene root nodes contains this.
     *
     * Generated from Godot docs: Node.set_scene_file_path
     */
    fun setSceneFilePath(sceneFilePath: String) {
        ObjectCalls.ptrcallWithStringArg(setSceneFilePathBind, segment, sceneFilePath)
    }

    /**
     * The original scene's file path, if the node has been instantiated from a `PackedScene` file.
     * Only scene root nodes contains this.
     *
     * Generated from Godot docs: Node.get_scene_file_path
     */
    fun getSceneFilePath(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getSceneFilePathBind, segment)
    }

    /**
     * Calls `Object.notification` with `what` on this node and all of its children, recursively.
     *
     * Generated from Godot docs: Node.propagate_notification
     */
    fun propagateNotification(what: Int) {
        ObjectCalls.ptrcallWithIntArg(propagateNotificationBind, segment, what)
    }

    /**
     * Calls the given `method` name, passing `args` as arguments, on this node and all of its
     * children, recursively. If `parent_first` is `true`, the method is called on this node first,
     * then on all of its children. If `false`, the children's methods are called first.
     *
     * Generated from Godot docs: Node.propagate_call
     */
    fun propagateCall(method: String, args: List<Any?> = emptyList(), parentFirst: Boolean = false) {
        ObjectCalls.ptrcallWithStringNameArrayBoolArgs(propagateCallBind, segment, method, args, parentFirst)
    }

    /**
     * If set to `true`, enables physics (fixed framerate) processing. When a node is being processed,
     * it will receive a `NOTIFICATION_PHYSICS_PROCESS` at a fixed (usually 60 FPS, see
     * `Engine.physics_ticks_per_second` to change) interval (and the `_physics_process` callback will
     * be called if it exists). Note: If `_physics_process` is overridden, this will be automatically
     * enabled before `_ready` is called.
     *
     * Generated from Godot docs: Node.set_physics_process
     */
    fun setPhysicsProcess(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPhysicsProcessBind, segment, enable)
    }

    /**
     * Returns the time elapsed (in seconds) since the last physics callback. This value is identical
     * to `_physics_process`'s `delta` parameter, and is often consistent at run-time, unless
     * `Engine.physics_ticks_per_second` is changed. See also `NOTIFICATION_PHYSICS_PROCESS`. Note: The
     * returned value will be larger than expected if running at a framerate lower than
     * `Engine.physics_ticks_per_second` / `Engine.max_physics_steps_per_frame` FPS. This is done to
     * avoid "spiral of death" scenarios where performance would plummet due to an ever-increasing
     * number of physics steps per frame. This behavior affects both `_process` and `_physics_process`.
     * As a result, avoid using `delta` for time measurements in real-world seconds. Use the `Time`
     * singleton's methods for this purpose instead, such as `Time.get_ticks_usec`.
     *
     * Generated from Godot docs: Node.get_physics_process_delta_time
     */
    fun getPhysicsProcessDeltaTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPhysicsProcessDeltaTimeBind, segment)
    }

    /**
     * Returns `true` if physics processing is enabled (see `set_physics_process`).
     *
     * Generated from Godot docs: Node.is_physics_processing
     */
    fun isPhysicsProcessing(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPhysicsProcessingBind, segment)
    }

    /**
     * Returns the time elapsed (in seconds) since the last process callback. This value is identical
     * to `_process`'s `delta` parameter, and may vary from frame to frame. See also
     * `NOTIFICATION_PROCESS`. Note: The returned value will be larger than expected if running at a
     * framerate lower than `Engine.physics_ticks_per_second` / `Engine.max_physics_steps_per_frame`
     * FPS. This is done to avoid "spiral of death" scenarios where performance would plummet due to an
     * ever-increasing number of physics steps per frame. This behavior affects both `_process` and
     * `_physics_process`. As a result, avoid using `delta` for time measurements in real-world
     * seconds. Use the `Time` singleton's methods for this purpose instead, such as
     * `Time.get_ticks_usec`.
     *
     * Generated from Godot docs: Node.get_process_delta_time
     */
    fun getProcessDeltaTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getProcessDeltaTimeBind, segment)
    }

    /**
     * If set to `true`, enables processing. When a node is being processed, it will receive a
     * `NOTIFICATION_PROCESS` on every drawn frame (and the `_process` callback will be called if it
     * exists). Note: If `_process` is overridden, this will be automatically enabled before `_ready`
     * is called. Note: This method only affects the `_process` callback, i.e. it has no effect on
     * other callbacks like `_physics_process`. If you want to disable all processing for the node, set
     * `process_mode` to `PROCESS_MODE_DISABLED`.
     *
     * Generated from Godot docs: Node.set_process
     */
    fun setProcess(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setProcessBind, segment, enable)
    }

    /**
     * The node's execution order of the process callbacks (`_process`, `NOTIFICATION_PROCESS`, and
     * `NOTIFICATION_INTERNAL_PROCESS`). Nodes whose priority value is lower call their process
     * callbacks first, regardless of tree order.
     *
     * Generated from Godot docs: Node.set_process_priority
     */
    fun setProcessPriority(priority: Int) {
        ObjectCalls.ptrcallWithIntArg(setProcessPriorityBind, segment, priority)
    }

    /**
     * The node's execution order of the process callbacks (`_process`, `NOTIFICATION_PROCESS`, and
     * `NOTIFICATION_INTERNAL_PROCESS`). Nodes whose priority value is lower call their process
     * callbacks first, regardless of tree order.
     *
     * Generated from Godot docs: Node.get_process_priority
     */
    fun getProcessPriority(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getProcessPriorityBind, segment)
    }

    /**
     * Similar to `process_priority` but for `NOTIFICATION_PHYSICS_PROCESS`, `_physics_process`, or
     * `NOTIFICATION_INTERNAL_PHYSICS_PROCESS`.
     *
     * Generated from Godot docs: Node.set_physics_process_priority
     */
    fun setPhysicsProcessPriority(priority: Int) {
        ObjectCalls.ptrcallWithIntArg(setPhysicsProcessPriorityBind, segment, priority)
    }

    /**
     * Similar to `process_priority` but for `NOTIFICATION_PHYSICS_PROCESS`, `_physics_process`, or
     * `NOTIFICATION_INTERNAL_PHYSICS_PROCESS`.
     *
     * Generated from Godot docs: Node.get_physics_process_priority
     */
    fun getPhysicsProcessPriority(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPhysicsProcessPriorityBind, segment)
    }

    /**
     * Returns `true` if processing is enabled (see `set_process`).
     *
     * Generated from Godot docs: Node.is_processing
     */
    fun isProcessing(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isProcessingBind, segment)
    }

    /**
     * If set to `true`, enables input processing. Note: If `_input` is overridden, this will be
     * automatically enabled before `_ready` is called. Input processing is also already enabled for
     * GUI controls, such as `Button` and `TextEdit`.
     *
     * Generated from Godot docs: Node.set_process_input
     */
    fun setProcessInput(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setProcessInputBind, segment, enable)
    }

    /**
     * Returns `true` if the node is processing input (see `set_process_input`).
     *
     * Generated from Godot docs: Node.is_processing_input
     */
    fun isProcessingInput(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isProcessingInputBind, segment)
    }

    /**
     * If set to `true`, enables shortcut processing for this node. Note: If `_shortcut_input` is
     * overridden, this will be automatically enabled before `_ready` is called.
     *
     * Generated from Godot docs: Node.set_process_shortcut_input
     */
    fun setProcessShortcutInput(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setProcessShortcutInputBind, segment, enable)
    }

    /**
     * Returns `true` if the node is processing shortcuts (see `set_process_shortcut_input`).
     *
     * Generated from Godot docs: Node.is_processing_shortcut_input
     */
    fun isProcessingShortcutInput(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isProcessingShortcutInputBind, segment)
    }

    /**
     * If set to `true`, enables unhandled input processing. It enables the node to receive all input
     * that was not previously handled (usually by a `Control`). Note: If `_unhandled_input` is
     * overridden, this will be automatically enabled before `_ready` is called. Unhandled input
     * processing is also already enabled for GUI controls, such as `Button` and `TextEdit`.
     *
     * Generated from Godot docs: Node.set_process_unhandled_input
     */
    fun setProcessUnhandledInput(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setProcessUnhandledInputBind, segment, enable)
    }

    /**
     * Returns `true` if the node is processing unhandled input (see `set_process_unhandled_input`).
     *
     * Generated from Godot docs: Node.is_processing_unhandled_input
     */
    fun isProcessingUnhandledInput(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isProcessingUnhandledInputBind, segment)
    }

    /**
     * If set to `true`, enables unhandled key input processing. Note: If `_unhandled_key_input` is
     * overridden, this will be automatically enabled before `_ready` is called.
     *
     * Generated from Godot docs: Node.set_process_unhandled_key_input
     */
    fun setProcessUnhandledKeyInput(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setProcessUnhandledKeyInputBind, segment, enable)
    }

    /**
     * Returns `true` if the node is processing unhandled key input (see
     * `set_process_unhandled_key_input`).
     *
     * Generated from Godot docs: Node.is_processing_unhandled_key_input
     */
    fun isProcessingUnhandledKeyInput(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isProcessingUnhandledKeyInputBind, segment)
    }

    /**
     * The node's processing behavior. To check if the node can process in its current mode, use
     * `can_process`.
     *
     * Generated from Godot docs: Node.set_process_mode
     */
    fun setProcessMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setProcessModeBind, segment, mode)
    }

    /**
     * The node's processing behavior. To check if the node can process in its current mode, use
     * `can_process`.
     *
     * Generated from Godot docs: Node.get_process_mode
     */
    fun getProcessMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getProcessModeBind, segment)
    }

    /**
     * Returns `true` if the node can receive processing notifications and input callbacks
     * (`NOTIFICATION_PROCESS`, `_input`, etc.) from the `SceneTree` and `Viewport`. The returned value
     * depends on `process_mode`: - If set to `PROCESS_MODE_PAUSABLE`, returns `true` when the game is
     * processing, i.e. `SceneTree.paused` is `false`; - If set to `PROCESS_MODE_WHEN_PAUSED`, returns
     * `true` when the game is paused, i.e. `SceneTree.paused` is `true`; - If set to
     * `PROCESS_MODE_ALWAYS`, always returns `true`; - If set to `PROCESS_MODE_DISABLED`, always
     * returns `false`; - If set to `PROCESS_MODE_INHERIT`, use the parent node's `process_mode` to
     * determine the result. If the node is not inside the tree, returns `false` no matter the value of
     * `process_mode`.
     *
     * Generated from Godot docs: Node.can_process
     */
    fun canProcess(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(canProcessBind, segment)
    }

    /**
     * Set the process thread group for this node (basically, whether it receives
     * `NOTIFICATION_PROCESS`, `NOTIFICATION_PHYSICS_PROCESS`, `_process` or `_physics_process` (and
     * the internal versions) on the main thread or in a sub-thread. By default, the thread group is
     * `PROCESS_THREAD_GROUP_INHERIT`, which means that this node belongs to the same thread group as
     * the parent node. The thread groups means that nodes in a specific thread group will process
     * together, separate to other thread groups (depending on `process_thread_group_order`). If the
     * value is set is `PROCESS_THREAD_GROUP_SUB_THREAD`, this thread group will occur on a sub thread
     * (not the main thread), otherwise if set to `PROCESS_THREAD_GROUP_MAIN_THREAD` it will process on
     * the main thread. If there is not a parent or grandparent node set to something other than
     * inherit, the node will belong to the default thread group. This default group will process on
     * the main thread and its group order is 0. During processing in a sub-thread, accessing most
     * functions in nodes outside the thread group is forbidden (and it will result in an error in
     * debug mode). Use `Object.call_deferred`, `call_thread_safe`, `call_deferred_thread_group` and
     * the likes in order to communicate from the thread groups to the main thread (or to other thread
     * groups). To better understand process thread groups, the idea is that any node set to any other
     * value than `PROCESS_THREAD_GROUP_INHERIT` will include any child (and grandchild) nodes set to
     * inherit into its process thread group. This means that the processing of all the nodes in the
     * group will happen together, at the same time as the node including them.
     *
     * Generated from Godot docs: Node.set_process_thread_group
     */
    fun setProcessThreadGroup(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setProcessThreadGroupBind, segment, mode)
    }

    /**
     * Set the process thread group for this node (basically, whether it receives
     * `NOTIFICATION_PROCESS`, `NOTIFICATION_PHYSICS_PROCESS`, `_process` or `_physics_process` (and
     * the internal versions) on the main thread or in a sub-thread. By default, the thread group is
     * `PROCESS_THREAD_GROUP_INHERIT`, which means that this node belongs to the same thread group as
     * the parent node. The thread groups means that nodes in a specific thread group will process
     * together, separate to other thread groups (depending on `process_thread_group_order`). If the
     * value is set is `PROCESS_THREAD_GROUP_SUB_THREAD`, this thread group will occur on a sub thread
     * (not the main thread), otherwise if set to `PROCESS_THREAD_GROUP_MAIN_THREAD` it will process on
     * the main thread. If there is not a parent or grandparent node set to something other than
     * inherit, the node will belong to the default thread group. This default group will process on
     * the main thread and its group order is 0. During processing in a sub-thread, accessing most
     * functions in nodes outside the thread group is forbidden (and it will result in an error in
     * debug mode). Use `Object.call_deferred`, `call_thread_safe`, `call_deferred_thread_group` and
     * the likes in order to communicate from the thread groups to the main thread (or to other thread
     * groups). To better understand process thread groups, the idea is that any node set to any other
     * value than `PROCESS_THREAD_GROUP_INHERIT` will include any child (and grandchild) nodes set to
     * inherit into its process thread group. This means that the processing of all the nodes in the
     * group will happen together, at the same time as the node including them.
     *
     * Generated from Godot docs: Node.get_process_thread_group
     */
    fun getProcessThreadGroup(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getProcessThreadGroupBind, segment)
    }

    /**
     * Set whether the current thread group will process messages (calls to
     * `call_deferred_thread_group` on threads), and whether it wants to receive them during regular
     * process or physics process callbacks.
     *
     * Generated from Godot docs: Node.set_process_thread_messages
     */
    fun setProcessThreadMessages(flags: Long) {
        ObjectCalls.ptrcallWithLongArg(setProcessThreadMessagesBind, segment, flags)
    }

    /**
     * Set whether the current thread group will process messages (calls to
     * `call_deferred_thread_group` on threads), and whether it wants to receive them during regular
     * process or physics process callbacks.
     *
     * Generated from Godot docs: Node.get_process_thread_messages
     */
    fun getProcessThreadMessages(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getProcessThreadMessagesBind, segment)
    }

    /**
     * Change the process thread group order. Groups with a lesser order will process before groups
     * with a greater order. This is useful when a large amount of nodes process in sub thread and,
     * afterwards, another group wants to collect their result in the main thread, as an example.
     *
     * Generated from Godot docs: Node.set_process_thread_group_order
     */
    fun setProcessThreadGroupOrder(order: Int) {
        ObjectCalls.ptrcallWithIntArg(setProcessThreadGroupOrderBind, segment, order)
    }

    /**
     * Change the process thread group order. Groups with a lesser order will process before groups
     * with a greater order. This is useful when a large amount of nodes process in sub thread and,
     * afterwards, another group wants to collect their result in the main thread, as an example.
     *
     * Generated from Godot docs: Node.get_process_thread_group_order
     */
    fun getProcessThreadGroupOrder(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getProcessThreadGroupOrderBind, segment)
    }

    /**
     * Queues an accessibility information update for this node.
     *
     * Generated from Godot docs: Node.queue_accessibility_update
     */
    fun queueAccessibilityUpdate() {
        ObjectCalls.ptrcallNoArgs(queueAccessibilityUpdateBind, segment)
    }

    /**
     * Returns main accessibility element RID. Note: This method should be called only during
     * accessibility information updates (`NOTIFICATION_ACCESSIBILITY_UPDATE`).
     *
     * Generated from Godot docs: Node.get_accessibility_element
     */
    fun getAccessibilityElement(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getAccessibilityElementBind, segment)
    }

    /**
     * If set to `true`, the node appears folded in the Scene dock. As a result, all of its children
     * are hidden. This method is intended to be used in editor plugins and tools, but it also works in
     * release builds. See also `is_displayed_folded`.
     *
     * Generated from Godot docs: Node.set_display_folded
     */
    fun setDisplayFolded(fold: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDisplayFoldedBind, segment, fold)
    }

    /**
     * Returns `true` if the node is folded (collapsed) in the Scene dock. This method is intended to
     * be used in editor plugins and tools. See also `set_display_folded`.
     *
     * Generated from Godot docs: Node.is_displayed_folded
     */
    fun isDisplayedFolded(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDisplayedFoldedBind, segment)
    }

    /**
     * If set to `true`, enables internal processing for this node. Internal processing happens in
     * isolation from the normal `_process` calls and is used by some nodes internally to guarantee
     * proper functioning even if the node is paused or processing is disabled for scripting
     * (`set_process`). Warning: Built-in nodes rely on internal processing for their internal logic.
     * Disabling it is unsafe and may lead to unexpected behavior. Use this method if you know what you
     * are doing.
     *
     * Generated from Godot docs: Node.set_process_internal
     */
    fun setProcessInternal(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setProcessInternalBind, segment, enable)
    }

    /**
     * Returns `true` if internal processing is enabled (see `set_process_internal`).
     *
     * Generated from Godot docs: Node.is_processing_internal
     */
    fun isProcessingInternal(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isProcessingInternalBind, segment)
    }

    /**
     * If set to `true`, enables internal physics for this node. Internal physics processing happens in
     * isolation from the normal `_physics_process` calls and is used by some nodes internally to
     * guarantee proper functioning even if the node is paused or physics processing is disabled for
     * scripting (`set_physics_process`). Warning: Built-in nodes rely on internal processing for their
     * internal logic. Disabling it is unsafe and may lead to unexpected behavior. Use this method if
     * you know what you are doing.
     *
     * Generated from Godot docs: Node.set_physics_process_internal
     */
    fun setPhysicsProcessInternal(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPhysicsProcessInternalBind, segment, enable)
    }

    /**
     * Returns `true` if internal physics processing is enabled (see `set_physics_process_internal`).
     *
     * Generated from Godot docs: Node.is_physics_processing_internal
     */
    fun isPhysicsProcessingInternal(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPhysicsProcessingInternalBind, segment)
    }

    /**
     * The physics interpolation mode to use for this node. Only effective if
     * `ProjectSettings.physics/common/physics_interpolation` or `SceneTree.physics_interpolation` is
     * `true`. By default, nodes inherit the physics interpolation mode from their parent. This
     * property can enable or disable physics interpolation individually for each node, regardless of
     * their parents' physics interpolation mode. Note: Some node types like `VehicleWheel3D` have
     * physics interpolation disabled by default, as they rely on their own custom solution. Note: When
     * teleporting a node to a distant position, it's recommended to temporarily disable interpolation
     * with `Node.reset_physics_interpolation` after moving the node. This avoids creating a visual
     * streak between the old and new positions.
     *
     * Generated from Godot docs: Node.set_physics_interpolation_mode
     */
    fun setPhysicsInterpolationMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setPhysicsInterpolationModeBind, segment, mode)
    }

    /**
     * The physics interpolation mode to use for this node. Only effective if
     * `ProjectSettings.physics/common/physics_interpolation` or `SceneTree.physics_interpolation` is
     * `true`. By default, nodes inherit the physics interpolation mode from their parent. This
     * property can enable or disable physics interpolation individually for each node, regardless of
     * their parents' physics interpolation mode. Note: Some node types like `VehicleWheel3D` have
     * physics interpolation disabled by default, as they rely on their own custom solution. Note: When
     * teleporting a node to a distant position, it's recommended to temporarily disable interpolation
     * with `Node.reset_physics_interpolation` after moving the node. This avoids creating a visual
     * streak between the old and new positions.
     *
     * Generated from Godot docs: Node.get_physics_interpolation_mode
     */
    fun getPhysicsInterpolationMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPhysicsInterpolationModeBind, segment)
    }

    /**
     * Returns `true` if physics interpolation is enabled for this node (see
     * `physics_interpolation_mode`). Note: Interpolation will only be active if both the flag is set
     * and physics interpolation is enabled within the `SceneTree`. This can be tested using
     * `is_physics_interpolated_and_enabled`.
     *
     * Generated from Godot docs: Node.is_physics_interpolated
     */
    fun isPhysicsInterpolated(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPhysicsInterpolatedBind, segment)
    }

    /**
     * Returns `true` if physics interpolation is enabled (see `physics_interpolation_mode`) and
     * enabled in the `SceneTree`. This is a convenience version of `is_physics_interpolated` that also
     * checks whether physics interpolation is enabled globally. See `SceneTree.physics_interpolation`
     * and `ProjectSettings.physics/common/physics_interpolation`.
     *
     * Generated from Godot docs: Node.is_physics_interpolated_and_enabled
     */
    fun isPhysicsInterpolatedAndEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPhysicsInterpolatedAndEnabledBind, segment)
    }

    /**
     * When physics interpolation is active, moving a node to a radically different transform (such as
     * placement within a level) can result in a visible glitch as the object is rendered moving from
     * the old to new position over the physics tick. That glitch can be prevented by calling this
     * method, which temporarily disables interpolation until the physics tick is complete. The
     * notification `NOTIFICATION_RESET_PHYSICS_INTERPOLATION` will be received by the node and all
     * children recursively. Note: This function should be called after moving the node, rather than
     * before.
     *
     * Generated from Godot docs: Node.reset_physics_interpolation
     */
    fun resetPhysicsInterpolation() {
        ObjectCalls.ptrcallNoArgs(resetPhysicsInterpolationBind, segment)
    }

    /**
     * Defines if any text should automatically change to its translated version depending on the
     * current locale (for nodes such as `Label`, `RichTextLabel`, `Window`, etc.). Also decides if the
     * node's strings should be parsed for translation template generation. Note: For the root node,
     * auto translate mode can also be set via
     * `ProjectSettings.internationalization/rendering/root_node_auto_translate`.
     *
     * Generated from Godot docs: Node.set_auto_translate_mode
     */
    fun setAutoTranslateMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setAutoTranslateModeBind, segment, mode)
    }

    /**
     * Defines if any text should automatically change to its translated version depending on the
     * current locale (for nodes such as `Label`, `RichTextLabel`, `Window`, etc.). Also decides if the
     * node's strings should be parsed for translation template generation. Note: For the root node,
     * auto translate mode can also be set via
     * `ProjectSettings.internationalization/rendering/root_node_auto_translate`.
     *
     * Generated from Godot docs: Node.get_auto_translate_mode
     */
    fun getAutoTranslateMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAutoTranslateModeBind, segment)
    }

    /**
     * Returns `true` if this node can automatically translate messages depending on the current
     * locale. See `auto_translate_mode`, `atr`, and `atr_n`.
     *
     * Generated from Godot docs: Node.can_auto_translate
     */
    fun canAutoTranslate(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(canAutoTranslateBind, segment)
    }

    /**
     * Makes this node inherit the translation domain from its parent node. If this node has no parent,
     * the main translation domain will be used. This is the default behavior for all nodes. Calling
     * `Object.set_translation_domain` disables this behavior.
     *
     * Generated from Godot docs: Node.set_translation_domain_inherited
     */
    fun setTranslationDomainInherited() {
        ObjectCalls.ptrcallNoArgs(setTranslationDomainInheritedBind, segment)
    }

    /**
     * Returns the `Window` that contains this node. If the node is in the main window, this is
     * equivalent to getting the root node (`get_tree().get_root()`).
     *
     * Generated from Godot docs: Node.get_window
     */
    fun getWindow(): Window? {
        return Window.wrap(ObjectCalls.ptrcallNoArgsRetObject(getWindowBind, segment))
    }

    /**
     * Returns the `Window` that contains this node, or the last exclusive child in a chain of windows
     * starting with the one that contains this node.
     *
     * Generated from Godot docs: Node.get_last_exclusive_window
     */
    fun getLastExclusiveWindow(): Window? {
        return Window.wrap(ObjectCalls.ptrcallNoArgsRetObject(getLastExclusiveWindowBind, segment))
    }

    /**
     * Returns the `SceneTree` that contains this node. If this node is not inside the tree, generates
     * an error and returns `null`. See also `is_inside_tree`.
     *
     * Generated from Godot docs: Node.get_tree
     */
    fun getTree(): SceneTree? {
        return SceneTree.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTreeBind, segment))
    }

    /**
     * Duplicates the node, returning a new node with all of its properties, signals, groups, and
     * children copied from the original, recursively. The behavior can be tweaked through the `flags`
     * (see `DuplicateFlags`). Internal nodes are not duplicated. Note: For nodes with a `Script`
     * attached, if `Object._init` has been defined with required parameters, the duplicated node will
     * not have a `Script`. Note: By default, this method will duplicate only properties marked for
     * serialization (i.e. using `@GlobalScope.PROPERTY_USAGE_STORAGE`, or in GDScript,
     * `@GDScript.@export`). If you want to duplicate all properties, use `DUPLICATE_INTERNAL_STATE`.
     *
     * Generated from Godot docs: Node.duplicate
     */
    fun duplicate(flags: Int = 15): Node? {
        return Node.wrap(ObjectCalls.ptrcallWithIntArgRetObject(duplicateBind, segment, flags))
    }

    /**
     * Replaces this node by the given `node`. All children of this node are moved to `node`. If
     * `keep_groups` is `true`, the `node` is added to the same groups that the replaced node is in
     * (see `add_to_group`). Warning: The replaced node is removed from the tree, but it is not
     * deleted. To prevent memory leaks, store a reference to the node in a variable, or use
     * `Object.free`.
     *
     * Generated from Godot docs: Node.replace_by
     */
    fun replaceBy(node: Node, keepGroups: Boolean = false) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(replaceByBind, segment, node.segment, keepGroups)
    }

    /**
     * If set to `true`, the node becomes an `InstancePlaceholder` when packed and instantiated from a
     * `PackedScene`. See also `get_scene_instance_load_placeholder`.
     *
     * Generated from Godot docs: Node.set_scene_instance_load_placeholder
     */
    fun setSceneInstanceLoadPlaceholder(loadPlaceholder: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSceneInstanceLoadPlaceholderBind, segment, loadPlaceholder)
    }

    /**
     * Returns `true` if this node is an instance load placeholder. See `InstancePlaceholder` and
     * `set_scene_instance_load_placeholder`.
     *
     * Generated from Godot docs: Node.get_scene_instance_load_placeholder
     */
    fun getSceneInstanceLoadPlaceholder(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getSceneInstanceLoadPlaceholderBind, segment)
    }

    /**
     * Set to `true` to allow all nodes owned by `node` to be available, and editable, in the Scene
     * dock, even if their `owner` is not the scene root. This method is intended to be used in editor
     * plugins and tools, but it also works in release builds. See also `is_editable_instance`.
     *
     * Generated from Godot docs: Node.set_editable_instance
     */
    fun setEditableInstance(node: Node, isEditable: Boolean) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(setEditableInstanceBind, segment, node.segment, isEditable)
    }

    /**
     * Returns `true` if `node` has editable children enabled relative to this node. This method is
     * intended to be used in editor plugins and tools. See also `set_editable_instance`.
     *
     * Generated from Godot docs: Node.is_editable_instance
     */
    fun isEditableInstance(node: Node): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(isEditableInstanceBind, segment, node.segment)
    }

    /**
     * Returns the node's closest `Viewport` ancestor, if the node is inside the tree. Otherwise,
     * returns `null`.
     *
     * Generated from Godot docs: Node.get_viewport
     */
    fun getViewport(): Viewport? {
        return Viewport.wrap(ObjectCalls.ptrcallNoArgsRetObject(getViewportBind, segment))
    }

    /**
     * Queues this node to be deleted at the end of the current frame. When deleted, all of its
     * children are deleted as well, and all references to the node and its children become invalid.
     * Unlike with `Object.free`, the node is not deleted instantly, and it can still be accessed
     * before deletion. It is also safe to call `queue_free` multiple times. Use
     * `Object.is_queued_for_deletion` to check if the node will be deleted at the end of the frame.
     * Note: The node will only be freed after all other deferred calls are finished. Using this method
     * is not always the same as calling `Object.free` through `Object.call_deferred`.
     *
     * Generated from Godot docs: Node.queue_free
     */
    fun queueFree() {
        ObjectCalls.ptrcallNoArgs(queueFreeBind, segment)
    }

    /**
     * Requests `_ready` to be called again the next time the node enters the tree. Does not
     * immediately call `_ready`. Note: This method only affects the current node. If the node's
     * children also need to request ready, this method needs to be called for each one of them. When
     * the node and its children enter the tree again, the order of `_ready` callbacks will be the same
     * as normal.
     *
     * Generated from Godot docs: Node.request_ready
     */
    fun requestReady() {
        ObjectCalls.ptrcallNoArgs(requestReadyBind, segment)
    }

    /**
     * Returns `true` if the node is ready, i.e. it's inside scene tree and all its children are
     * initialized. `request_ready` resets it back to `false`.
     *
     * Generated from Godot docs: Node.is_node_ready
     */
    fun isNodeReady(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isNodeReadyBind, segment)
    }

    /**
     * Sets the node's multiplayer authority to the peer with the given peer `id`. The multiplayer
     * authority is the peer that has authority over the node on the network. Defaults to peer ID 1
     * (the server). Useful in conjunction with `rpc_config` and the `MultiplayerAPI`. If `recursive`
     * is `true`, the given peer is recursively set as the authority for all children of this node.
     * Warning: This does not automatically replicate the new authority to other peers. It is the
     * developer's responsibility to do so. You may replicate the new authority's information using
     * `MultiplayerSpawner.spawn_function`, an RPC, or a `MultiplayerSynchronizer`. Furthermore, the
     * parent's authority does not propagate to newly added children.
     *
     * Generated from Godot docs: Node.set_multiplayer_authority
     */
    fun setMultiplayerAuthority(id: Int, recursive: Boolean = true) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setMultiplayerAuthorityBind, segment, id, recursive)
    }

    /**
     * Returns the peer ID of the multiplayer authority for this node. See `set_multiplayer_authority`.
     *
     * Generated from Godot docs: Node.get_multiplayer_authority
     */
    fun getMultiplayerAuthority(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMultiplayerAuthorityBind, segment)
    }

    /**
     * Returns `true` if the local system is the multiplayer authority of this node.
     *
     * Generated from Godot docs: Node.is_multiplayer_authority
     */
    fun isMultiplayerAuthority(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMultiplayerAuthorityBind, segment)
    }

    /**
     * The `MultiplayerAPI` instance associated with this node. See `SceneTree.get_multiplayer`. Note:
     * Renaming the node, or moving it in the tree, will not move the `MultiplayerAPI` to the new path,
     * you will have to update this manually.
     *
     * Generated from Godot docs: Node.get_multiplayer
     */
    fun getMultiplayer(): MultiplayerAPI? {
        return MultiplayerAPI.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMultiplayerBind, segment))
    }

    /**
     * Changes the RPC configuration for the given `method`. `config` should either be `null` to
     * disable the feature (as by default), or a `Dictionary` containing the following entries: -
     * `rpc_mode`: see `MultiplayerAPI.RPCMode`; - `transfer_mode`: see `MultiplayerPeer.TransferMode`;
     * - `call_local`: if `true`, the method will also be called locally; - `channel`: an `int`
     * representing the channel to send the RPC on. Note: In GDScript, this method corresponds to the
     * `@GDScript.@rpc` annotation, with various parameters passed (`@rpc(any)`, `@rpc(authority)`...).
     * See also the high-level multiplayer ($DOCS_URL/tutorials/networking/high_level_multiplayer.html)
     * tutorial.
     *
     * Generated from Godot docs: Node.rpc_config
     */
    fun rpcConfig(method: String, config: Any?) {
        ObjectCalls.ptrcallWithStringNameAndVariantArg(rpcConfigBind, segment, method, config)
    }

    /**
     * Returns a `Dictionary` mapping method names to their RPC configuration defined for this node
     * using `rpc_config`. Note: This method only returns the RPC configuration assigned via
     * `rpc_config`. See `Script.get_rpc_config` to retrieve the RPCs defined by the `Script`.
     *
     * Generated from Godot docs: Node.get_node_rpc_config
     */
    fun getNodeRpcConfig(): Any? {
        return ObjectCalls.ptrcallNoArgsRetVariantScalar(getNodeRpcConfigBind, segment)
    }

    /**
     * An optional description to the node. It will be displayed as a tooltip when hovering over the
     * node in the editor's Scene dock.
     *
     * Generated from Godot docs: Node.set_editor_description
     */
    fun setEditorDescription(editorDescription: String) {
        ObjectCalls.ptrcallWithStringArg(setEditorDescriptionBind, segment, editorDescription)
    }

    /**
     * An optional description to the node. It will be displayed as a tooltip when hovering over the
     * node in the editor's Scene dock.
     *
     * Generated from Godot docs: Node.get_editor_description
     */
    fun getEditorDescription(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getEditorDescriptionBind, segment)
    }

    /**
     * If `true`, the node can be accessed from any node sharing the same `owner` or from the `owner`
     * itself, with special `%Name` syntax in `get_node`. Note: If another node with the same `owner`
     * shares the same `name` as this node, the other node will no longer be accessible as unique.
     *
     * Generated from Godot docs: Node.set_unique_name_in_owner
     */
    fun setUniqueNameInOwner(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUniqueNameInOwnerBind, segment, enable)
    }

    /**
     * If `true`, the node can be accessed from any node sharing the same `owner` or from the `owner`
     * itself, with special `%Name` syntax in `get_node`. Note: If another node with the same `owner`
     * shares the same `name` as this node, the other node will no longer be accessible as unique.
     *
     * Generated from Godot docs: Node.is_unique_name_in_owner
     */
    fun isUniqueNameInOwner(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUniqueNameInOwnerBind, segment)
    }

    /**
     * Translates a `message`, using the translation catalogs configured in the Project Settings.
     * Further `context` can be specified to help with the translation. Note that most `Control` nodes
     * automatically translate their strings, so this method is mostly useful for formatted strings or
     * custom drawn text. This method works the same as `Object.tr`, with the addition of respecting
     * the `auto_translate_mode` state. If `Object.can_translate_messages` is `false`, or no
     * translation is available, this method returns the `message` without changes. See
     * `Object.set_message_translation`. For detailed examples, see Internationalizing games
     * ($DOCS_URL/tutorials/i18n/internationalizing_games.html).
     *
     * Generated from Godot docs: Node.atr
     */
    fun atr(message: String, context: String = ""): String {
        return ObjectCalls.ptrcallWithStringAndStringNameArgRetString(atrBind, segment, message, context)
    }

    /**
     * Translates a `message` or `plural_message`, using the translation catalogs configured in the
     * Project Settings. Further `context` can be specified to help with the translation. This method
     * works the same as `Object.tr_n`, with the addition of respecting the `auto_translate_mode`
     * state. If `Object.can_translate_messages` is `false`, or no translation is available, this
     * method returns `message` or `plural_message`, without changes. See
     * `Object.set_message_translation`. The `n` is the number, or amount, of the message's subject. It
     * is used by the translation system to fetch the correct plural form for the current language. For
     * detailed examples, see Localization using gettext
     * ($DOCS_URL/tutorials/i18n/localization_using_gettext.html). Note: Negative and `float` numbers
     * may not properly apply to some countable subjects. It's recommended to handle these cases with
     * `atr`.
     *
     * Generated from Godot docs: Node.atr_n
     */
    fun atrN(message: String, pluralMessage: String, n: Int, context: String = ""): String {
        return ObjectCalls.ptrcallWithStringStringNameIntStringNameArgsRetString(atrNBind, segment, message, pluralMessage, n, context)
    }

    /**
     * Sends a remote procedure call request for the given `method` to peers on the network (and
     * locally), sending additional arguments to the method called by the RPC. The call request will
     * only be received by nodes with the same `NodePath`, including the exact same `name`. Behavior
     * depends on the RPC configuration for the given `method` (see `rpc_config` and `@GDScript.@rpc`).
     * By default, methods are not exposed to RPCs. May return `OK` if the call is successful,
     * `ERR_INVALID_PARAMETER` if the arguments passed in the `method` do not match, `ERR_UNCONFIGURED`
     * if the node's `multiplayer` cannot be fetched (such as when the node is not inside the tree),
     * `ERR_CONNECTION_ERROR` if `multiplayer`'s connection is not available. Note: You can only safely
     * use RPCs on clients after you received the `MultiplayerAPI.connected_to_server` signal from the
     * `MultiplayerAPI`. You also need to keep track of the connection state, either by the
     * `MultiplayerAPI` signals like `MultiplayerAPI.server_disconnected` or by checking
     * (`get_multiplayer().peer.get_connection_status() == CONNECTION_CONNECTED`).
     *
     * Generated from Godot docs: Node.rpc
     */
    fun rpc(method: String, vararg extraArgs: Any?): Long {
        return (ObjectCalls.callWithVariantArgs(rpcBind, segment, listOf(method, *extraArgs)) as Number).toLong()
    }

    /**
     * Sends a `rpc` to a specific peer identified by `peer_id` (see
     * `MultiplayerPeer.set_target_peer`). May return `OK` if the call is successful,
     * `ERR_INVALID_PARAMETER` if the arguments passed in the `method` do not match, `ERR_UNCONFIGURED`
     * if the node's `multiplayer` cannot be fetched (such as when the node is not inside the tree),
     * `ERR_CONNECTION_ERROR` if `multiplayer`'s connection is not available.
     *
     * Generated from Godot docs: Node.rpc_id
     */
    fun rpcId(peerId: Long, method: String, vararg extraArgs: Any?): Long {
        return (ObjectCalls.callWithVariantArgs(rpcIdBind, segment, listOf(peerId, method, *extraArgs)) as Number).toLong()
    }

    /**
     * Refreshes the warnings displayed for this node in the Scene dock. Use
     * `_get_configuration_warnings` to customize the warning messages to display.
     *
     * Generated from Godot docs: Node.update_configuration_warnings
     */
    fun updateConfigurationWarnings() {
        ObjectCalls.ptrcallNoArgs(updateConfigurationWarningsBind, segment)
    }

    /**
     * This function is similar to `Object.call_deferred` except that the call will take place when the
     * node thread group is processed. If the node thread group processes in sub-threads, then the call
     * will be done on that thread, right before `NOTIFICATION_PROCESS` or
     * `NOTIFICATION_PHYSICS_PROCESS`, the `_process` or `_physics_process` or their internal versions
     * are called.
     *
     * Generated from Godot docs: Node.call_deferred_thread_group
     */
    fun callDeferredThreadGroup(method: String, vararg extraArgs: Any?): Any? {
        return ObjectCalls.callWithVariantArgs(callDeferredThreadGroupBind, segment, listOf(method, *extraArgs))
    }

    /**
     * Similar to `call_deferred_thread_group`, but for setting properties.
     *
     * Generated from Godot docs: Node.set_deferred_thread_group
     */
    fun setDeferredThreadGroup(property: String, value: Any?) {
        ObjectCalls.ptrcallWithStringNameAndVariantArg(setDeferredThreadGroupBind, segment, property, value)
    }

    /**
     * Similar to `call_deferred_thread_group`, but for notifications.
     *
     * Generated from Godot docs: Node.notify_deferred_thread_group
     */
    fun notifyDeferredThreadGroup(what: Int) {
        ObjectCalls.ptrcallWithIntArg(notifyDeferredThreadGroupBind, segment, what)
    }

    /**
     * This function ensures that the calling of this function will succeed, no matter whether it's
     * being done from a thread or not. If called from a thread that is not allowed to call the
     * function, the call will become deferred. Otherwise, the call will go through directly.
     *
     * Generated from Godot docs: Node.call_thread_safe
     */
    fun callThreadSafe(method: String, vararg extraArgs: Any?): Any? {
        return ObjectCalls.callWithVariantArgs(callThreadSafeBind, segment, listOf(method, *extraArgs))
    }

    /**
     * Similar to `call_thread_safe`, but for setting properties.
     *
     * Generated from Godot docs: Node.set_thread_safe
     */
    fun setThreadSafe(property: String, value: Any?) {
        ObjectCalls.ptrcallWithStringNameAndVariantArg(setThreadSafeBind, segment, property, value)
    }

    /**
     * Similar to `call_thread_safe`, but for notifications.
     *
     * Generated from Godot docs: Node.notify_thread_safe
     */
    fun notifyThreadSafe(what: Int) {
        ObjectCalls.ptrcallWithIntArg(notifyThreadSafeBind, segment, what)
    }

    // ── Kanama Node ergonomics (generator custom-section, not from Godot docs) ────────────────
    // These lived twice until task 117 P1'(b2): on the hand-written desktop `Node`, and (a subset,
    // with `IosGodot.*` bodies) in IOS_MEMBER_SECTIONS['Node']. `Node` is one generated class now,
    // so they live here once, keeping the DESKTOP names, overloads and signatures (D1/D11) — every
    // `requireNodeAs` / `getNodeAsOrNull` / `requireAs` / `getAsOrNull` / `callLocalRpc` call site
    // in the demos and the examples keeps compiling, on both platforms. Every body uses only seams
    // both platforms resolve: the generated NodePath accessors, `isClass`, `call`, `handle`.

    // String-path overloads of the generated `NodePath` accessors, so a script can pass a plain
    // path literal (`self.getNodeOrNull("Hud/Label")`). Plain comments, not KDoc: these five names
    // are Godot methods, so sync_kdoc_from_godot_docs.py owns their doc block.
    /**
     * Fetches a node by `NodePath`. Similar to `get_node`, but does not generate an error if `path`
     * does not point to a valid node.
     *
     * Generated from Godot docs: Node.get_node_or_null
     */
    fun getNodeOrNull(path: String): Node? = getNodeOrNull(NodePath(path))

    /**
     * Fetches a node. The `NodePath` can either be a relative path (from this node), or an absolute
     * path (from the `SceneTree.root`) to a node. If `path` does not point to a valid node, generates
     * an error and returns `null`. Attempts to access methods on the return value will result in an
     * "Attempt to call <method> on a null instance." error. Note: Fetching by absolute path only works
     * when the node is inside the scene tree (see `is_inside_tree`).
     *
     * Generated from Godot docs: Node.get_node
     */
    fun getNode(path: String): Node? = getNode(NodePath(path))

    /**
     * Returns `true` if the `path` points to a valid node. See also `get_node`.
     *
     * Generated from Godot docs: Node.has_node
     */
    fun hasNode(path: String): Boolean = hasNode(NodePath(path))

    /**
     * Returns `true` if `path` points to a valid node and its subnames point to a valid `Resource`,
     * e.g. `Area2D/CollisionShape2D:shape`. Properties that are not `Resource` types (such as nodes or
     * other `Variant` types) are not considered. See also `get_node_and_resource`.
     *
     * Generated from Godot docs: Node.has_node_and_resource
     */
    fun hasNodeAndResource(path: String): Boolean = hasNodeAndResource(NodePath(path))

    /**
     * Fetches a node and its most nested resource as specified by the `NodePath`'s subname. Returns an
     * `Array` of size `3` where: - Element `0` is the `Node`, or `null` if not found; - Element `1` is
     * the subname's last nested `Resource`, or `null` if not found; - Element `2` is the remaining
     * `NodePath`, referring to an existing, non-`Resource` property (see `Object.get_indexed`).
     *
     * Generated from Godot docs: Node.get_node_and_resource
     */
    fun getNodeAndResource(path: String): List<Any?> = getNodeAndResource(NodePath(path))

    /**
     * Returns the node at `path` as `wrapper` when Godot reports that it is an
     * instance of `expectedClass`, or `null` when the path is missing or the
     * class check fails.
     *
     * This is the stricter typed form of GDScript's `get_node(path) as Type`.
     * It is useful when the child must be a specific built-in Godot class.
     * For instanced scene roots whose script type may not satisfy
     * `Object.is_class(...)`, prefer [getAsOrNull].
     */
    fun <T : Node> getNodeAsOrNull(
        path: String,
        expectedClass: String,
        wrapper: (GodotHandle) -> T,
    ): T? {
        val node = getNodeOrNull(path) ?: return null
        return if (node.isClass(expectedClass)) wrapper(node.handle) else null
    }

    fun <T : Node> getNodeAsOrNull(
        path: NodePath,
        expectedClass: String,
        wrapper: (GodotHandle) -> T,
    ): T? = getNodeAsOrNull(path.path, expectedClass, wrapper)

    /**
     * Returns the node at `path` wrapped as `T`, or `null` when the path is
     * missing.
     *
     * This is Kanama's lightweight typed equivalent of GDScript's
     * `get_node(path)` for cases where the scene structure already guarantees
     * the node type:
     *
     * ```
     * val mobSpawnLocation = self.getAsOrNull("SpawnPath/SpawnLocation", ::PathFollow3D)
     * ```
     *
     * Unlike [getNodeAsOrNull], this helper does not call `Object.is_class`.
     * That makes it work for child scene roots and Kanama script instances
     * where the expected Kotlin wrapper is known from the scene, while still
     * returning a non-owning wrapper around the same Godot node.
     */
    fun <T : Node> getAsOrNull(
        path: String,
        wrapper: (GodotHandle) -> T,
    ): T? = getNodeOrNull(path)?.let { wrapper(it.handle) }

    fun <T : Node> getAsOrNull(
        path: NodePath,
        wrapper: (GodotHandle) -> T,
    ): T? = getAsOrNull(path.path, wrapper)

    /**
     * Returns the node at `path` wrapped as `T`, or throws a descriptive error
     * if the node is missing.
     *
     * This is the common Kanama replacement for GDScript's required
     * `get_node(path)` calls:
     *
     * ```
     * var mob_spawn_location = get_node(^"SpawnPath/MobSpawnLocation")
     * ```
     *
     * becomes:
     *
     * ```
     * val mobSpawnLocation = self.requireAs("SpawnPath/MobSpawnLocation", ::PathFollow2D)
     * ```
     *
     * The returned wrapper is non-owning; Godot still owns the node. Use this
     * when the scene requires the child to exist. Use [getAsOrNull] when a
     * missing child is valid.
     */
    fun <T : Node> requireAs(
        path: String,
        wrapper: (GodotHandle) -> T,
    ): T = getAsOrNull(path, wrapper)
        ?: error("Required node '$path' was not found under ${describeForErrors()}")

    fun <T : Node> requireAs(
        path: NodePath,
        wrapper: (GodotHandle) -> T,
    ): T = requireAs(path.path, wrapper)

    /**
     * Strict required form of [getNodeAsOrNull]. Throws if `path` is missing or
     * the resolved node is not reported by Godot as `expectedClass`.
     */
    fun <T : Node> requireNodeAs(
        path: String,
        expectedClass: String,
        wrapper: (GodotHandle) -> T,
    ): T = getNodeAsOrNull(path, expectedClass, wrapper)
        ?: error("Required node '$path' was not found under ${describeForErrors()} or is not a $expectedClass")

    fun <T : Node> requireNodeAs(
        path: NodePath,
        expectedClass: String,
        wrapper: (GodotHandle) -> T,
    ): T = requireNodeAs(path.path, expectedClass, wrapper)

    /**
     * Sends an RPC and falls back to a local method call if Godot reports that the RPC could not be sent.
     *
     * This is useful for `@Rpc(callLocal = true)` gameplay events that should also work while the node
     * uses an offline or not-yet-connected multiplayer peer.
     *
     * The generated `<Class>Rpcs.callLocal*` helpers and the KSP processor emit calls to this member
     * (KanamaProcessor.kt / IosScriptCodeEmitter.kt), so its name and signature are load-bearing.
     */
    fun callLocalRpc(method: String, vararg extraArgs: Any?) {
        if (rpc(method, *extraArgs) != 0L) {
            call(method, *extraArgs)
        }
    }

    // Receiver description for the requireAs/requireNodeAs failure messages. The desktop hand file
    // used GodotObject.getClassName(), which is desktop-only (P3' owns the roots), so the class name
    // comes through the Variant `call` seam both platforms implement — an error path, called once
    // before a throw.
    private fun describeForErrors(): String = "${call("get_class")}#${getInstanceId()}"

    object Signals {
        const val ready: String = "ready"
        const val renamed: String = "renamed"
        const val treeEntered: String = "tree_entered"
        const val treeExiting: String = "tree_exiting"
        const val treeExited: String = "tree_exited"
        const val childEnteredTree: String = "child_entered_tree"
        const val childExitingTree: String = "child_exiting_tree"
        const val childOrderChanged: String = "child_order_changed"
        const val replacingBy: String = "replacing_by"
        const val editorDescriptionChanged: String = "editor_description_changed"
        const val editorStateChanged: String = "editor_state_changed"
    }

    companion object {
        /**
         * Prints all orphan nodes (nodes outside the `SceneTree`). Useful for debugging. Note: This method
         * only works in debug builds. It does nothing in a project exported in release mode.
         *
         * Generated from Godot docs: Node.print_orphan_nodes
         */
        fun printOrphanNodes() {
            ObjectCalls.ptrcallNoArgs(printOrphanNodesBind, NULL_SEGMENT)
        }

        /**
         * Returns object IDs of all orphan nodes (nodes outside the `SceneTree`). Used for debugging.
         * Note: `get_orphan_node_ids` only works in debug builds. When called in a project exported in
         * release mode, `get_orphan_node_ids` will return an empty array.
         *
         * Generated from Godot docs: Node.get_orphan_node_ids
         */
        fun getOrphanNodeIds(): List<Long> {
            return ObjectCalls.ptrcallNoArgsRetLongList(getOrphanNodeIdsBind, NULL_SEGMENT)
        }

        const val NOTIFICATION_ENTER_TREE: Long = 10L
        const val NOTIFICATION_EXIT_TREE: Long = 11L
        const val NOTIFICATION_MOVED_IN_PARENT: Long = 12L
        const val NOTIFICATION_READY: Long = 13L
        const val NOTIFICATION_PAUSED: Long = 14L
        const val NOTIFICATION_UNPAUSED: Long = 15L
        const val NOTIFICATION_PHYSICS_PROCESS: Long = 16L
        const val NOTIFICATION_PROCESS: Long = 17L
        const val NOTIFICATION_PARENTED: Long = 18L
        const val NOTIFICATION_UNPARENTED: Long = 19L
        const val NOTIFICATION_SCENE_INSTANTIATED: Long = 20L
        const val NOTIFICATION_DRAG_BEGIN: Long = 21L
        const val NOTIFICATION_DRAG_END: Long = 22L
        const val NOTIFICATION_PATH_RENAMED: Long = 23L
        const val NOTIFICATION_CHILD_ORDER_CHANGED: Long = 24L
        const val NOTIFICATION_INTERNAL_PROCESS: Long = 25L
        const val NOTIFICATION_INTERNAL_PHYSICS_PROCESS: Long = 26L
        const val NOTIFICATION_POST_ENTER_TREE: Long = 27L
        const val NOTIFICATION_DISABLED: Long = 28L
        const val NOTIFICATION_ENABLED: Long = 29L
        const val NOTIFICATION_RESET_PHYSICS_INTERPOLATION: Long = 2001L
        const val NOTIFICATION_EDITOR_PRE_SAVE: Long = 9001L
        const val NOTIFICATION_EDITOR_POST_SAVE: Long = 9002L
        const val NOTIFICATION_WM_MOUSE_ENTER: Long = 1002L
        const val NOTIFICATION_WM_MOUSE_EXIT: Long = 1003L
        const val NOTIFICATION_WM_WINDOW_FOCUS_IN: Long = 1004L
        const val NOTIFICATION_WM_WINDOW_FOCUS_OUT: Long = 1005L
        const val NOTIFICATION_WM_CLOSE_REQUEST: Long = 1006L
        const val NOTIFICATION_WM_GO_BACK_REQUEST: Long = 1007L
        const val NOTIFICATION_WM_SIZE_CHANGED: Long = 1008L
        const val NOTIFICATION_WM_DPI_CHANGE: Long = 1009L
        const val NOTIFICATION_VP_MOUSE_ENTER: Long = 1010L
        const val NOTIFICATION_VP_MOUSE_EXIT: Long = 1011L
        const val NOTIFICATION_WM_POSITION_CHANGED: Long = 1012L
        const val NOTIFICATION_WM_OUTPUT_MAX_LINEAR_VALUE_CHANGED: Long = 1013L
        const val NOTIFICATION_OS_MEMORY_WARNING: Long = 2009L
        const val NOTIFICATION_TRANSLATION_CHANGED: Long = 2010L
        const val NOTIFICATION_WM_ABOUT: Long = 2011L
        const val NOTIFICATION_CRASH: Long = 2012L
        const val NOTIFICATION_OS_IME_UPDATE: Long = 2013L
        const val NOTIFICATION_APPLICATION_RESUMED: Long = 2014L
        const val NOTIFICATION_APPLICATION_PAUSED: Long = 2015L
        const val NOTIFICATION_APPLICATION_FOCUS_IN: Long = 2016L
        const val NOTIFICATION_APPLICATION_FOCUS_OUT: Long = 2017L
        const val NOTIFICATION_TEXT_SERVER_CHANGED: Long = 2018L
        const val NOTIFICATION_APPLICATION_PIP_MODE_ENTERED: Long = 2019L
        const val NOTIFICATION_APPLICATION_PIP_MODE_EXITED: Long = 2020L
        const val NOTIFICATION_ACCESSIBILITY_UPDATE: Long = 3000L
        const val NOTIFICATION_ACCESSIBILITY_INVALIDATE: Long = 3001L
        const val PROCESS_MODE_INHERIT: Long = 0L
        const val PROCESS_MODE_PAUSABLE: Long = 1L
        const val PROCESS_MODE_WHEN_PAUSED: Long = 2L
        const val PROCESS_MODE_ALWAYS: Long = 3L
        const val PROCESS_MODE_DISABLED: Long = 4L
        const val PROCESS_THREAD_GROUP_INHERIT: Long = 0L
        const val PROCESS_THREAD_GROUP_MAIN_THREAD: Long = 1L
        const val PROCESS_THREAD_GROUP_SUB_THREAD: Long = 2L
        const val FLAG_PROCESS_THREAD_MESSAGES: Long = 1L
        const val FLAG_PROCESS_THREAD_MESSAGES_PHYSICS: Long = 2L
        const val FLAG_PROCESS_THREAD_MESSAGES_ALL: Long = 3L
        const val PHYSICS_INTERPOLATION_MODE_INHERIT: Long = 0L
        const val PHYSICS_INTERPOLATION_MODE_ON: Long = 1L
        const val PHYSICS_INTERPOLATION_MODE_OFF: Long = 2L
        const val DUPLICATE_SIGNALS: Long = 1L
        const val DUPLICATE_GROUPS: Long = 2L
        const val DUPLICATE_SCRIPTS: Long = 4L
        const val DUPLICATE_USE_INSTANTIATION: Long = 8L
        const val DUPLICATE_INTERNAL_STATE: Long = 16L
        const val DUPLICATE_DEFAULT: Long = 15L
        const val INTERNAL_MODE_DISABLED: Long = 0L
        const val INTERNAL_MODE_FRONT: Long = 1L
        const val INTERNAL_MODE_BACK: Long = 2L
        const val AUTO_TRANSLATE_MODE_INHERIT: Long = 0L
        const val AUTO_TRANSLATE_MODE_ALWAYS: Long = 1L
        const val AUTO_TRANSLATE_MODE_DISABLED: Long = 2L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): Node? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Node? =
            if (handle.address() == 0L) null else Node(GodotHandle(handle))

        private const val PRINT_ORPHAN_NODES_HASH = 3218959716L
        private val printOrphanNodesBind by lazy {
            ObjectCalls.getMethodBind("Node", "print_orphan_nodes", PRINT_ORPHAN_NODES_HASH)
        }

        private const val GET_ORPHAN_NODE_IDS_HASH = 2915620761L
        private val getOrphanNodeIdsBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_orphan_node_ids", GET_ORPHAN_NODE_IDS_HASH)
        }

        private const val ADD_SIBLING_HASH = 2570952461L
        private val addSiblingBind by lazy {
            ObjectCalls.getMethodBind("Node", "add_sibling", ADD_SIBLING_HASH)
        }

        private const val SET_NAME_HASH = 3304788590L
        private val setNameBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_name", SET_NAME_HASH)
        }

        private const val GET_NAME_HASH = 2002593661L
        private val getNameBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_name", GET_NAME_HASH)
        }

        private const val ADD_CHILD_HASH = 3863233950L
        private val addChildBind by lazy {
            ObjectCalls.getMethodBind("Node", "add_child", ADD_CHILD_HASH)
        }

        private const val REMOVE_CHILD_HASH = 1078189570L
        private val removeChildBind by lazy {
            ObjectCalls.getMethodBind("Node", "remove_child", REMOVE_CHILD_HASH)
        }

        private const val REPARENT_HASH = 3685795103L
        private val reparentBind by lazy {
            ObjectCalls.getMethodBind("Node", "reparent", REPARENT_HASH)
        }

        private const val GET_CHILD_COUNT_HASH = 894402480L
        private val getChildCountBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_child_count", GET_CHILD_COUNT_HASH)
        }

        private const val GET_CHILDREN_HASH = 873284517L
        private val getChildrenBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_children", GET_CHILDREN_HASH)
        }

        private const val GET_CHILD_HASH = 541253412L
        private val getChildBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_child", GET_CHILD_HASH)
        }

        private const val HAS_NODE_HASH = 861721659L
        private val hasNodeBind by lazy {
            ObjectCalls.getMethodBind("Node", "has_node", HAS_NODE_HASH)
        }

        private const val GET_NODE_HASH = 2734337346L
        private val getNodeBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_node", GET_NODE_HASH)
        }

        private const val GET_NODE_OR_NULL_HASH = 2734337346L
        private val getNodeOrNullBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_node_or_null", GET_NODE_OR_NULL_HASH)
        }

        private const val GET_PARENT_HASH = 3160264692L
        private val getParentBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_parent", GET_PARENT_HASH)
        }

        private const val FIND_CHILD_HASH = 2008217037L
        private val findChildBind by lazy {
            ObjectCalls.getMethodBind("Node", "find_child", FIND_CHILD_HASH)
        }

        private const val FIND_CHILDREN_HASH = 2560337219L
        private val findChildrenBind by lazy {
            ObjectCalls.getMethodBind("Node", "find_children", FIND_CHILDREN_HASH)
        }

        private const val FIND_PARENT_HASH = 1140089439L
        private val findParentBind by lazy {
            ObjectCalls.getMethodBind("Node", "find_parent", FIND_PARENT_HASH)
        }

        private const val HAS_NODE_AND_RESOURCE_HASH = 861721659L
        private val hasNodeAndResourceBind by lazy {
            ObjectCalls.getMethodBind("Node", "has_node_and_resource", HAS_NODE_AND_RESOURCE_HASH)
        }

        private const val GET_NODE_AND_RESOURCE_HASH = 502563882L
        private val getNodeAndResourceBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_node_and_resource", GET_NODE_AND_RESOURCE_HASH)
        }

        private const val IS_INSIDE_TREE_HASH = 36873697L
        private val isInsideTreeBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_inside_tree", IS_INSIDE_TREE_HASH)
        }

        private const val IS_PART_OF_EDITED_SCENE_HASH = 36873697L
        private val isPartOfEditedSceneBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_part_of_edited_scene", IS_PART_OF_EDITED_SCENE_HASH)
        }

        private const val IS_ANCESTOR_OF_HASH = 3093956946L
        private val isAncestorOfBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_ancestor_of", IS_ANCESTOR_OF_HASH)
        }

        private const val IS_GREATER_THAN_HASH = 3093956946L
        private val isGreaterThanBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_greater_than", IS_GREATER_THAN_HASH)
        }

        private const val GET_PATH_HASH = 4075236667L
        private val getPathBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_path", GET_PATH_HASH)
        }

        private const val GET_PATH_TO_HASH = 498846349L
        private val getPathToBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_path_to", GET_PATH_TO_HASH)
        }

        private const val ADD_TO_GROUP_HASH = 3683006648L
        private val addToGroupBind by lazy {
            ObjectCalls.getMethodBind("Node", "add_to_group", ADD_TO_GROUP_HASH)
        }

        private const val REMOVE_FROM_GROUP_HASH = 3304788590L
        private val removeFromGroupBind by lazy {
            ObjectCalls.getMethodBind("Node", "remove_from_group", REMOVE_FROM_GROUP_HASH)
        }

        private const val IS_IN_GROUP_HASH = 2619796661L
        private val isInGroupBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_in_group", IS_IN_GROUP_HASH)
        }

        private const val MOVE_CHILD_HASH = 3315886247L
        private val moveChildBind by lazy {
            ObjectCalls.getMethodBind("Node", "move_child", MOVE_CHILD_HASH)
        }

        private const val GET_GROUPS_HASH = 3995934104L
        private val getGroupsBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_groups", GET_GROUPS_HASH)
        }

        private const val SET_OWNER_HASH = 1078189570L
        private val setOwnerBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_owner", SET_OWNER_HASH)
        }

        private const val GET_OWNER_HASH = 3160264692L
        private val getOwnerBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_owner", GET_OWNER_HASH)
        }

        private const val GET_INDEX_HASH = 894402480L
        private val getIndexBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_index", GET_INDEX_HASH)
        }

        private const val PRINT_TREE_HASH = 3218959716L
        private val printTreeBind by lazy {
            ObjectCalls.getMethodBind("Node", "print_tree", PRINT_TREE_HASH)
        }

        private const val PRINT_TREE_PRETTY_HASH = 3218959716L
        private val printTreePrettyBind by lazy {
            ObjectCalls.getMethodBind("Node", "print_tree_pretty", PRINT_TREE_PRETTY_HASH)
        }

        private const val GET_TREE_STRING_HASH = 2841200299L
        private val getTreeStringBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_tree_string", GET_TREE_STRING_HASH)
        }

        private const val GET_TREE_STRING_PRETTY_HASH = 2841200299L
        private val getTreeStringPrettyBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_tree_string_pretty", GET_TREE_STRING_PRETTY_HASH)
        }

        private const val SET_SCENE_FILE_PATH_HASH = 83702148L
        private val setSceneFilePathBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_scene_file_path", SET_SCENE_FILE_PATH_HASH)
        }

        private const val GET_SCENE_FILE_PATH_HASH = 201670096L
        private val getSceneFilePathBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_scene_file_path", GET_SCENE_FILE_PATH_HASH)
        }

        private const val PROPAGATE_NOTIFICATION_HASH = 1286410249L
        private val propagateNotificationBind by lazy {
            ObjectCalls.getMethodBind("Node", "propagate_notification", PROPAGATE_NOTIFICATION_HASH)
        }

        private const val PROPAGATE_CALL_HASH = 1871007965L
        private val propagateCallBind by lazy {
            ObjectCalls.getMethodBind("Node", "propagate_call", PROPAGATE_CALL_HASH)
        }

        private const val SET_PHYSICS_PROCESS_HASH = 2586408642L
        private val setPhysicsProcessBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_physics_process", SET_PHYSICS_PROCESS_HASH)
        }

        private const val GET_PHYSICS_PROCESS_DELTA_TIME_HASH = 1740695150L
        private val getPhysicsProcessDeltaTimeBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_physics_process_delta_time", GET_PHYSICS_PROCESS_DELTA_TIME_HASH)
        }

        private const val IS_PHYSICS_PROCESSING_HASH = 36873697L
        private val isPhysicsProcessingBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_physics_processing", IS_PHYSICS_PROCESSING_HASH)
        }

        private const val GET_PROCESS_DELTA_TIME_HASH = 1740695150L
        private val getProcessDeltaTimeBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_process_delta_time", GET_PROCESS_DELTA_TIME_HASH)
        }

        private const val SET_PROCESS_HASH = 2586408642L
        private val setProcessBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_process", SET_PROCESS_HASH)
        }

        private const val SET_PROCESS_PRIORITY_HASH = 1286410249L
        private val setProcessPriorityBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_process_priority", SET_PROCESS_PRIORITY_HASH)
        }

        private const val GET_PROCESS_PRIORITY_HASH = 3905245786L
        private val getProcessPriorityBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_process_priority", GET_PROCESS_PRIORITY_HASH)
        }

        private const val SET_PHYSICS_PROCESS_PRIORITY_HASH = 1286410249L
        private val setPhysicsProcessPriorityBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_physics_process_priority", SET_PHYSICS_PROCESS_PRIORITY_HASH)
        }

        private const val GET_PHYSICS_PROCESS_PRIORITY_HASH = 3905245786L
        private val getPhysicsProcessPriorityBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_physics_process_priority", GET_PHYSICS_PROCESS_PRIORITY_HASH)
        }

        private const val IS_PROCESSING_HASH = 36873697L
        private val isProcessingBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_processing", IS_PROCESSING_HASH)
        }

        private const val SET_PROCESS_INPUT_HASH = 2586408642L
        private val setProcessInputBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_process_input", SET_PROCESS_INPUT_HASH)
        }

        private const val IS_PROCESSING_INPUT_HASH = 36873697L
        private val isProcessingInputBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_processing_input", IS_PROCESSING_INPUT_HASH)
        }

        private const val SET_PROCESS_SHORTCUT_INPUT_HASH = 2586408642L
        private val setProcessShortcutInputBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_process_shortcut_input", SET_PROCESS_SHORTCUT_INPUT_HASH)
        }

        private const val IS_PROCESSING_SHORTCUT_INPUT_HASH = 36873697L
        private val isProcessingShortcutInputBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_processing_shortcut_input", IS_PROCESSING_SHORTCUT_INPUT_HASH)
        }

        private const val SET_PROCESS_UNHANDLED_INPUT_HASH = 2586408642L
        private val setProcessUnhandledInputBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_process_unhandled_input", SET_PROCESS_UNHANDLED_INPUT_HASH)
        }

        private const val IS_PROCESSING_UNHANDLED_INPUT_HASH = 36873697L
        private val isProcessingUnhandledInputBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_processing_unhandled_input", IS_PROCESSING_UNHANDLED_INPUT_HASH)
        }

        private const val SET_PROCESS_UNHANDLED_KEY_INPUT_HASH = 2586408642L
        private val setProcessUnhandledKeyInputBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_process_unhandled_key_input", SET_PROCESS_UNHANDLED_KEY_INPUT_HASH)
        }

        private const val IS_PROCESSING_UNHANDLED_KEY_INPUT_HASH = 36873697L
        private val isProcessingUnhandledKeyInputBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_processing_unhandled_key_input", IS_PROCESSING_UNHANDLED_KEY_INPUT_HASH)
        }

        private const val SET_PROCESS_MODE_HASH = 1841290486L
        private val setProcessModeBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_process_mode", SET_PROCESS_MODE_HASH)
        }

        private const val GET_PROCESS_MODE_HASH = 739966102L
        private val getProcessModeBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_process_mode", GET_PROCESS_MODE_HASH)
        }

        private const val CAN_PROCESS_HASH = 36873697L
        private val canProcessBind by lazy {
            ObjectCalls.getMethodBind("Node", "can_process", CAN_PROCESS_HASH)
        }

        private const val SET_PROCESS_THREAD_GROUP_HASH = 2275442745L
        private val setProcessThreadGroupBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_process_thread_group", SET_PROCESS_THREAD_GROUP_HASH)
        }

        private const val GET_PROCESS_THREAD_GROUP_HASH = 1866404740L
        private val getProcessThreadGroupBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_process_thread_group", GET_PROCESS_THREAD_GROUP_HASH)
        }

        private const val SET_PROCESS_THREAD_MESSAGES_HASH = 1357280998L
        private val setProcessThreadMessagesBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_process_thread_messages", SET_PROCESS_THREAD_MESSAGES_HASH)
        }

        private const val GET_PROCESS_THREAD_MESSAGES_HASH = 4228993612L
        private val getProcessThreadMessagesBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_process_thread_messages", GET_PROCESS_THREAD_MESSAGES_HASH)
        }

        private const val SET_PROCESS_THREAD_GROUP_ORDER_HASH = 1286410249L
        private val setProcessThreadGroupOrderBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_process_thread_group_order", SET_PROCESS_THREAD_GROUP_ORDER_HASH)
        }

        private const val GET_PROCESS_THREAD_GROUP_ORDER_HASH = 3905245786L
        private val getProcessThreadGroupOrderBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_process_thread_group_order", GET_PROCESS_THREAD_GROUP_ORDER_HASH)
        }

        private const val QUEUE_ACCESSIBILITY_UPDATE_HASH = 3218959716L
        private val queueAccessibilityUpdateBind by lazy {
            ObjectCalls.getMethodBind("Node", "queue_accessibility_update", QUEUE_ACCESSIBILITY_UPDATE_HASH)
        }

        private const val GET_ACCESSIBILITY_ELEMENT_HASH = 2944877500L
        private val getAccessibilityElementBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_accessibility_element", GET_ACCESSIBILITY_ELEMENT_HASH)
        }

        private const val SET_DISPLAY_FOLDED_HASH = 2586408642L
        private val setDisplayFoldedBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_display_folded", SET_DISPLAY_FOLDED_HASH)
        }

        private const val IS_DISPLAYED_FOLDED_HASH = 36873697L
        private val isDisplayedFoldedBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_displayed_folded", IS_DISPLAYED_FOLDED_HASH)
        }

        private const val SET_PROCESS_INTERNAL_HASH = 2586408642L
        private val setProcessInternalBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_process_internal", SET_PROCESS_INTERNAL_HASH)
        }

        private const val IS_PROCESSING_INTERNAL_HASH = 36873697L
        private val isProcessingInternalBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_processing_internal", IS_PROCESSING_INTERNAL_HASH)
        }

        private const val SET_PHYSICS_PROCESS_INTERNAL_HASH = 2586408642L
        private val setPhysicsProcessInternalBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_physics_process_internal", SET_PHYSICS_PROCESS_INTERNAL_HASH)
        }

        private const val IS_PHYSICS_PROCESSING_INTERNAL_HASH = 36873697L
        private val isPhysicsProcessingInternalBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_physics_processing_internal", IS_PHYSICS_PROCESSING_INTERNAL_HASH)
        }

        private const val SET_PHYSICS_INTERPOLATION_MODE_HASH = 3202404928L
        private val setPhysicsInterpolationModeBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_physics_interpolation_mode", SET_PHYSICS_INTERPOLATION_MODE_HASH)
        }

        private const val GET_PHYSICS_INTERPOLATION_MODE_HASH = 2920385216L
        private val getPhysicsInterpolationModeBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_physics_interpolation_mode", GET_PHYSICS_INTERPOLATION_MODE_HASH)
        }

        private const val IS_PHYSICS_INTERPOLATED_HASH = 36873697L
        private val isPhysicsInterpolatedBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_physics_interpolated", IS_PHYSICS_INTERPOLATED_HASH)
        }

        private const val IS_PHYSICS_INTERPOLATED_AND_ENABLED_HASH = 36873697L
        private val isPhysicsInterpolatedAndEnabledBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_physics_interpolated_and_enabled", IS_PHYSICS_INTERPOLATED_AND_ENABLED_HASH)
        }

        private const val RESET_PHYSICS_INTERPOLATION_HASH = 3218959716L
        private val resetPhysicsInterpolationBind by lazy {
            ObjectCalls.getMethodBind("Node", "reset_physics_interpolation", RESET_PHYSICS_INTERPOLATION_HASH)
        }

        private const val SET_AUTO_TRANSLATE_MODE_HASH = 776149714L
        private val setAutoTranslateModeBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_auto_translate_mode", SET_AUTO_TRANSLATE_MODE_HASH)
        }

        private const val GET_AUTO_TRANSLATE_MODE_HASH = 2498906432L
        private val getAutoTranslateModeBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_auto_translate_mode", GET_AUTO_TRANSLATE_MODE_HASH)
        }

        private const val CAN_AUTO_TRANSLATE_HASH = 36873697L
        private val canAutoTranslateBind by lazy {
            ObjectCalls.getMethodBind("Node", "can_auto_translate", CAN_AUTO_TRANSLATE_HASH)
        }

        private const val SET_TRANSLATION_DOMAIN_INHERITED_HASH = 3218959716L
        private val setTranslationDomainInheritedBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_translation_domain_inherited", SET_TRANSLATION_DOMAIN_INHERITED_HASH)
        }

        private const val GET_WINDOW_HASH = 1757182445L
        private val getWindowBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_window", GET_WINDOW_HASH)
        }

        private const val GET_LAST_EXCLUSIVE_WINDOW_HASH = 1757182445L
        private val getLastExclusiveWindowBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_last_exclusive_window", GET_LAST_EXCLUSIVE_WINDOW_HASH)
        }

        private const val GET_TREE_HASH = 2958820483L
        private val getTreeBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_tree", GET_TREE_HASH)
        }

        private const val DUPLICATE_HASH = 3511555459L
        private val duplicateBind by lazy {
            ObjectCalls.getMethodBind("Node", "duplicate", DUPLICATE_HASH)
        }

        private const val REPLACE_BY_HASH = 2570952461L
        private val replaceByBind by lazy {
            ObjectCalls.getMethodBind("Node", "replace_by", REPLACE_BY_HASH)
        }

        private const val SET_SCENE_INSTANCE_LOAD_PLACEHOLDER_HASH = 2586408642L
        private val setSceneInstanceLoadPlaceholderBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_scene_instance_load_placeholder", SET_SCENE_INSTANCE_LOAD_PLACEHOLDER_HASH)
        }

        private const val GET_SCENE_INSTANCE_LOAD_PLACEHOLDER_HASH = 36873697L
        private val getSceneInstanceLoadPlaceholderBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_scene_instance_load_placeholder", GET_SCENE_INSTANCE_LOAD_PLACEHOLDER_HASH)
        }

        private const val SET_EDITABLE_INSTANCE_HASH = 2731852923L
        private val setEditableInstanceBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_editable_instance", SET_EDITABLE_INSTANCE_HASH)
        }

        private const val IS_EDITABLE_INSTANCE_HASH = 3093956946L
        private val isEditableInstanceBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_editable_instance", IS_EDITABLE_INSTANCE_HASH)
        }

        private const val GET_VIEWPORT_HASH = 3596683776L
        private val getViewportBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_viewport", GET_VIEWPORT_HASH)
        }

        private const val QUEUE_FREE_HASH = 3218959716L
        private val queueFreeBind by lazy {
            ObjectCalls.getMethodBind("Node", "queue_free", QUEUE_FREE_HASH)
        }

        private const val REQUEST_READY_HASH = 3218959716L
        private val requestReadyBind by lazy {
            ObjectCalls.getMethodBind("Node", "request_ready", REQUEST_READY_HASH)
        }

        private const val IS_NODE_READY_HASH = 36873697L
        private val isNodeReadyBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_node_ready", IS_NODE_READY_HASH)
        }

        private const val SET_MULTIPLAYER_AUTHORITY_HASH = 972357352L
        private val setMultiplayerAuthorityBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_multiplayer_authority", SET_MULTIPLAYER_AUTHORITY_HASH)
        }

        private const val GET_MULTIPLAYER_AUTHORITY_HASH = 3905245786L
        private val getMultiplayerAuthorityBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_multiplayer_authority", GET_MULTIPLAYER_AUTHORITY_HASH)
        }

        private const val IS_MULTIPLAYER_AUTHORITY_HASH = 36873697L
        private val isMultiplayerAuthorityBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_multiplayer_authority", IS_MULTIPLAYER_AUTHORITY_HASH)
        }

        private const val GET_MULTIPLAYER_HASH = 406750475L
        private val getMultiplayerBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_multiplayer", GET_MULTIPLAYER_HASH)
        }

        private const val RPC_CONFIG_HASH = 3776071444L
        private val rpcConfigBind by lazy {
            ObjectCalls.getMethodBind("Node", "rpc_config", RPC_CONFIG_HASH)
        }

        private const val GET_NODE_RPC_CONFIG_HASH = 1214101251L
        private val getNodeRpcConfigBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_node_rpc_config", GET_NODE_RPC_CONFIG_HASH)
        }

        private const val SET_EDITOR_DESCRIPTION_HASH = 83702148L
        private val setEditorDescriptionBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_editor_description", SET_EDITOR_DESCRIPTION_HASH)
        }

        private const val GET_EDITOR_DESCRIPTION_HASH = 201670096L
        private val getEditorDescriptionBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_editor_description", GET_EDITOR_DESCRIPTION_HASH)
        }

        private const val SET_UNIQUE_NAME_IN_OWNER_HASH = 2586408642L
        private val setUniqueNameInOwnerBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_unique_name_in_owner", SET_UNIQUE_NAME_IN_OWNER_HASH)
        }

        private const val IS_UNIQUE_NAME_IN_OWNER_HASH = 36873697L
        private val isUniqueNameInOwnerBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_unique_name_in_owner", IS_UNIQUE_NAME_IN_OWNER_HASH)
        }

        private const val ATR_HASH = 3344478075L
        private val atrBind by lazy {
            ObjectCalls.getMethodBind("Node", "atr", ATR_HASH)
        }

        private const val ATR_N_HASH = 259354841L
        private val atrNBind by lazy {
            ObjectCalls.getMethodBind("Node", "atr_n", ATR_N_HASH)
        }

        private const val RPC_HASH = 4047867050L
        private val rpcBind by lazy {
            ObjectCalls.getMethodBind("Node", "rpc", RPC_HASH)
        }

        private const val RPC_ID_HASH = 361499283L
        private val rpcIdBind by lazy {
            ObjectCalls.getMethodBind("Node", "rpc_id", RPC_ID_HASH)
        }

        private const val UPDATE_CONFIGURATION_WARNINGS_HASH = 3218959716L
        private val updateConfigurationWarningsBind by lazy {
            ObjectCalls.getMethodBind("Node", "update_configuration_warnings", UPDATE_CONFIGURATION_WARNINGS_HASH)
        }

        private const val CALL_DEFERRED_THREAD_GROUP_HASH = 3400424181L
        private val callDeferredThreadGroupBind by lazy {
            ObjectCalls.getMethodBind("Node", "call_deferred_thread_group", CALL_DEFERRED_THREAD_GROUP_HASH)
        }

        private const val SET_DEFERRED_THREAD_GROUP_HASH = 3776071444L
        private val setDeferredThreadGroupBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_deferred_thread_group", SET_DEFERRED_THREAD_GROUP_HASH)
        }

        private const val NOTIFY_DEFERRED_THREAD_GROUP_HASH = 1286410249L
        private val notifyDeferredThreadGroupBind by lazy {
            ObjectCalls.getMethodBind("Node", "notify_deferred_thread_group", NOTIFY_DEFERRED_THREAD_GROUP_HASH)
        }

        private const val CALL_THREAD_SAFE_HASH = 3400424181L
        private val callThreadSafeBind by lazy {
            ObjectCalls.getMethodBind("Node", "call_thread_safe", CALL_THREAD_SAFE_HASH)
        }

        private const val SET_THREAD_SAFE_HASH = 3776071444L
        private val setThreadSafeBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_thread_safe", SET_THREAD_SAFE_HASH)
        }

        private const val NOTIFY_THREAD_SAFE_HASH = 1286410249L
        private val notifyThreadSafeBind by lazy {
            ObjectCalls.getMethodBind("Node", "notify_thread_safe", NOTIFY_THREAD_SAFE_HASH)
        }
    }
}

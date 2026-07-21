package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.RID
import java.lang.foreign.MemorySegment

/**
 * Base class for all scene objects.
 *
 * Generated from Godot docs: Node
 */
open class Node(handle: MemorySegment) : GodotObject(handle) {

    /**
     * Returns `true` if this node is currently inside a `SceneTree`. See also `get_tree`.
     *
     * Generated from Godot docs: Node.is_inside_tree
     */
    fun isInsideTree(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isInsideTreeBind, handle)

    /**
     * Returns `true` if the node is part of the scene currently opened in the editor.
     *
     * Generated from Godot docs: Node.is_part_of_edited_scene
     */
    fun isPartOfEditedScene(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isPartOfEditedSceneBind, handle)

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
        ObjectCalls.ptrcallWithStringNameArg(setNameBind, handle, name)
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
    fun getName(): String =
        ObjectCalls.ptrcallNoArgsRetStringName(getNameBind, handle)

    /**
     * Returns the number of children of this node. If `include_internal` is `false`, internal children
     * are not counted (see `add_child`'s `internal` parameter).
     *
     * Generated from Godot docs: Node.get_child_count
     */
    fun getChildCount(includeInternal: Boolean = false): Long =
        ObjectCalls.ptrcallWithBoolArgRetInt(getChildCountBind, handle, includeInternal).toLong()

    /**
     * Returns all children of this node inside an `Array`. If `include_internal` is `false`, excludes
     * internal children from the returned array (see `add_child`'s `internal` parameter).
     *
     * Generated from Godot docs: Node.get_children
     */
    fun getChildren(includeInternal: Boolean = false): List<Node> =
        ObjectCalls.ptrcallWithBoolArgRetTypedNodeList(getChildrenBind, handle, includeInternal)

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
    fun getChild(idx: Int, includeInternal: Boolean = false): Node? =
        ObjectCalls.ptrcallWithIntAndBoolArgsRetObject(getChildBind, handle, idx, includeInternal).toNodeOrNull()

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
    fun getChild(idx: Long, includeInternal: Boolean = false): Node? =
        getChild(idx.toInt(), includeInternal)

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
    fun findChild(pattern: String, recursive: Boolean = true, owned: Boolean = true): Node? =
        ObjectCalls.ptrcallWithStringAndTwoBoolArgsRetObject(
            findChildBind,
            handle,
            pattern,
            recursive,
            owned,
        ).toNodeOrNull()

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
    fun findChildren(
        pattern: String,
        type: String = "",
        recursive: Boolean = true,
        owned: Boolean = true,
    ): List<Node> =
        ObjectCalls.ptrcallWithTwoStringAndTwoBoolArgsRetTypedNodeList(
            findChildrenBind,
            handle,
            pattern,
            type,
            recursive,
            owned,
        )

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
    fun findParent(pattern: String): Node? =
        ObjectCalls.ptrcallWithStringArgRetObject(findParentBind, handle, pattern).toNodeOrNull()

    /**
     * Returns `true` if the `path` points to a valid node. See also `get_node`.
     *
     * Generated from Godot docs: Node.has_node
     */
    fun hasNode(path: String): Boolean =
        ObjectCalls.ptrcallWithNodePathArgRetBool(hasNodeBind, handle, path)

    /**
     * Returns `true` if the `path` points to a valid node. See also `get_node`.
     *
     * Generated from Godot docs: Node.has_node
     */
    fun hasNode(path: NodePath): Boolean =
        hasNode(path.path)

    /**
     * Fetches a node. The `NodePath` can either be a relative path (from this node), or an absolute
     * path (from the `SceneTree.root`) to a node. If `path` does not point to a valid node, generates
     * an error and returns `null`. Attempts to access methods on the return value will result in an
     * "Attempt to call <method> on a null instance." error. Note: Fetching by absolute path only works
     * when the node is inside the scene tree (see `is_inside_tree`).
     *
     * Generated from Godot docs: Node.get_node
     */
    fun getNode(path: String): Node? =
        ObjectCalls.ptrcallWithNodePathArgRetObject(getNodeBind, handle, path).toNodeOrNull()

    /**
     * Fetches a node. The `NodePath` can either be a relative path (from this node), or an absolute
     * path (from the `SceneTree.root`) to a node. If `path` does not point to a valid node, generates
     * an error and returns `null`. Attempts to access methods on the return value will result in an
     * "Attempt to call <method> on a null instance." error. Note: Fetching by absolute path only works
     * when the node is inside the scene tree (see `is_inside_tree`).
     *
     * Generated from Godot docs: Node.get_node
     */
    fun getNode(path: NodePath): Node? =
        getNode(path.path)

    /**
     * Fetches a node by `NodePath`. Similar to `get_node`, but does not generate an error if `path`
     * does not point to a valid node.
     *
     * Generated from Godot docs: Node.get_node_or_null
     */
    fun getNodeOrNull(path: String): Node? =
        ObjectCalls.ptrcallWithNodePathArgRetObject(getNodeOrNullBind, handle, path).toNodeOrNull()

    /**
     * Fetches a node by `NodePath`. Similar to `get_node`, but does not generate an error if `path`
     * does not point to a valid node.
     *
     * Generated from Godot docs: Node.get_node_or_null
     */
    fun getNodeOrNull(path: NodePath): Node? =
        getNodeOrNull(path.path)

    /**
     * Returns `true` if `path` points to a valid node and its subnames point to a valid `Resource`,
     * e.g. `Area2D/CollisionShape2D:shape`. Properties that are not `Resource` types (such as nodes or
     * other `Variant` types) are not considered. See also `get_node_and_resource`.
     *
     * Generated from Godot docs: Node.has_node_and_resource
     */
    fun hasNodeAndResource(path: String): Boolean =
        ObjectCalls.ptrcallWithNodePathArgRetBool(hasNodeAndResourceBind, handle, path)

    /**
     * Returns `true` if `path` points to a valid node and its subnames point to a valid `Resource`,
     * e.g. `Area2D/CollisionShape2D:shape`. Properties that are not `Resource` types (such as nodes or
     * other `Variant` types) are not considered. See also `get_node_and_resource`.
     *
     * Generated from Godot docs: Node.has_node_and_resource
     */
    fun hasNodeAndResource(path: NodePath): Boolean =
        hasNodeAndResource(path.path)

    /**
     * Fetches a node and its most nested resource as specified by the `NodePath`'s subname. Returns an
     * `Array` of size `3` where: - Element `0` is the `Node`, or `null` if not found; - Element `1` is
     * the subname's last nested `Resource`, or `null` if not found; - Element `2` is the remaining
     * `NodePath`, referring to an existing, non-`Resource` property (see `Object.get_indexed`).
     *
     * Generated from Godot docs: Node.get_node_and_resource
     */
    fun getNodeAndResource(path: String): List<Any?> =
        getNodeAndResource(NodePath(path))

    /**
     * Fetches a node and its most nested resource as specified by the `NodePath`'s subname. Returns an
     * `Array` of size `3` where: - Element `0` is the `Node`, or `null` if not found; - Element `1` is
     * the subname's last nested `Resource`, or `null` if not found; - Element `2` is the remaining
     * `NodePath`, referring to an existing, non-`Resource` property (see `Object.get_indexed`).
     *
     * Generated from Godot docs: Node.get_node_and_resource
     */
    fun getNodeAndResource(path: NodePath): List<Any?> =
        ObjectCalls.ptrcallWithNodePathArgRetArray(getNodeAndResourceBind, handle, path)

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
        wrapper: (MemorySegment) -> T,
    ): T? {
        val node = getNodeOrNull(path) ?: return null
        return if (node.isClass(expectedClass)) wrapper(node.handle) else null
    }

    fun <T : Node> getNodeAsOrNull(
        path: NodePath,
        expectedClass: String,
        wrapper: (MemorySegment) -> T,
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
        wrapper: (MemorySegment) -> T,
    ): T? = getNodeOrNull(path)?.let { wrapper(it.handle) }

    fun <T : Node> getAsOrNull(
        path: NodePath,
        wrapper: (MemorySegment) -> T,
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
        wrapper: (MemorySegment) -> T,
    ): T = getAsOrNull(path, wrapper)
        ?: error("Required node '$path' was not found under ${describeForErrors()}")

    fun <T : Node> requireAs(
        path: NodePath,
        wrapper: (MemorySegment) -> T,
    ): T = requireAs(path.path, wrapper)

    /**
     * Strict required form of [getNodeAsOrNull]. Throws if `path` is missing or
     * the resolved node is not reported by Godot as `expectedClass`.
     */
    fun <T : Node> requireNodeAs(
        path: String,
        expectedClass: String,
        wrapper: (MemorySegment) -> T,
    ): T = getNodeAsOrNull(path, expectedClass, wrapper)
        ?: error("Required node '$path' was not found under ${describeForErrors()} or is not a $expectedClass")

    fun <T : Node> requireNodeAs(
        path: NodePath,
        expectedClass: String,
        wrapper: (MemorySegment) -> T,
    ): T = requireNodeAs(path.path, expectedClass, wrapper)

    /**
     * Returns this node's parent node, or `null` if the node doesn't have a parent.
     *
     * Generated from Godot docs: Node.get_parent
     */
    fun getParent(): Node? =
        ObjectCalls.ptrcallNoArgsRetObject(getParentBind, handle).toNodeOrNull()

    /**
     * Returns `true` if the given `node` is a direct or indirect child of this node.
     *
     * Generated from Godot docs: Node.is_ancestor_of
     */
    fun isAncestorOf(node: Node): Boolean =
        ObjectCalls.ptrcallWithObjectArgRetBool(isAncestorOfBind, handle, node.handle)

    /**
     * Returns `true` if the given `node` occurs later in the scene hierarchy than this node. A node
     * occurring later is usually processed last.
     *
     * Generated from Godot docs: Node.is_greater_than
     */
    fun isGreaterThan(node: Node): Boolean =
        ObjectCalls.ptrcallWithObjectArgRetBool(isGreaterThanBind, handle, node.handle)

    /**
     * Returns the node's absolute path, relative to the `SceneTree.root`. If the node is not inside
     * the scene tree, this method fails and returns an empty `NodePath`.
     *
     * Generated from Godot docs: Node.get_path
     */
    fun getPath(): NodePath =
        ObjectCalls.ptrcallNoArgsRetNodePath(getPathBind, handle)

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
    fun getPathTo(node: Node, useUniquePath: Boolean = false): NodePath =
        ObjectCalls.ptrcallWithObjectAndBoolArgRetNodePath(getPathToBind, handle, node.handle, useUniquePath)

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
        // Godot's Node::set_owner cleans up the previous owner and returns when p_owner is null, so
        // null clears the owner (the engine itself calls child->set_owner(nullptr) while replacing
        // nodes). Marshal null as MemorySegment.NULL. Task 52a / issue #60; audited in
        // NULLABLE_OBJECT_PARAM_OVERRIDES in scripts/generate_api_wrapper.py.
        ObjectCalls.ptrcallWithObjectArgs(setOwnerBind, handle, listOf(owner?.handle ?: MemorySegment.NULL))
    }

    /**
     * The owner of this node, or `null` to clear it. Honest hand-shaped mirror of the generated
     * `var owner: Node?` property the reparented iOS wrapper now emits (task 52a).
     */
    var owner: Node?
        @JvmName("ownerProperty")
        get() = getOwner()
        @JvmName("setOwnerProperty")
        set(value) = setOwner(value)

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
    fun getOwner(): Node? =
        ObjectCalls.ptrcallNoArgsRetObject(getOwnerBind, handle).toNodeOrNull()

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
    fun addChild(
        node: Node,
        forceReadableName: Boolean = false,
        internalMode: Long = INTERNAL_MODE_DISABLED,
    ) {
        ObjectCalls.ptrcallWithObjectBoolLongArgs(addChildBind, handle, node.handle, forceReadableName, internalMode)
    }

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
        ObjectCalls.ptrcallWithObjectAndBoolArg(addSiblingBind, handle, sibling.handle, forceReadableName)
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
    fun duplicate(flags: Long = DUPLICATE_DEFAULT): Node? =
        ObjectCalls.ptrcallWithIntArgRetObject(duplicateBind, handle, flags.toInt()).toNodeOrNull()

    /**
     * Removes a child `node`. The `node`, along with its children, are not deleted. To delete a node,
     * see `queue_free`. Note: When this node is inside the tree, this method sets the `owner` of the
     * removed `node` (or its descendants) to `null`, if their `owner` is no longer an ancestor (see
     * `is_ancestor_of`).
     *
     * Generated from Godot docs: Node.remove_child
     */
    fun removeChild(node: Node) {
        ObjectCalls.ptrcallWithObjectArgs(removeChildBind, handle, listOf(node.handle))
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
        ObjectCalls.ptrcallWithObjectAndBoolArg(reparentBind, handle, newParent.handle, keepGlobalTransform)
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
        ObjectCalls.ptrcallWithObjectAndIntArg(moveChildBind, handle, childNode.handle, toIndex)
    }

    /**
     * Returns this node's order among its siblings. The first node's index is `0`. See also
     * `get_child`. If `include_internal` is `false`, returns the index ignoring internal children. The
     * first, non-internal child will have an index of `0` (see `add_child`'s `internal` parameter).
     *
     * Generated from Godot docs: Node.get_index
     */
    fun getIndex(includeInternal: Boolean = false): Long =
        ObjectCalls.ptrcallWithBoolArgRetInt(getIndexBind, handle, includeInternal).toLong()

    /**
     * The original scene's file path, if the node has been instantiated from a `PackedScene` file.
     * Only scene root nodes contains this.
     *
     * Generated from Godot docs: Node.get_scene_file_path
     */
    fun getSceneFilePath(): String =
        ObjectCalls.ptrcallNoArgsRetString(getSceneFilePathBind, handle)

    /**
     * The original scene's file path, if the node has been instantiated from a `PackedScene` file.
     * Only scene root nodes contains this.
     *
     * Generated from Godot docs: Node.set_scene_file_path
     */
    fun setSceneFilePath(sceneFilePath: String) {
        ObjectCalls.ptrcallWithStringArg(setSceneFilePathBind, handle, sceneFilePath)
    }

    /**
     * Returns the tree as a `String`. Used mainly for debugging purposes. This version displays the
     * path relative to the current node, and is good for copy/pasting into the `get_node` function. It
     * also can be used in game UI/UX. May print, for example:
     *
     * Generated from Godot docs: Node.get_tree_string
     */
    fun getTreeString(): String =
        ObjectCalls.ptrcallNoArgsRetString(getTreeStringBind, handle)

    /**
     * Similar to `get_tree_string`, this returns the tree as a `String`. This version displays a more
     * graphical representation similar to what is displayed in the Scene Dock. It is useful for
     * inspecting larger trees. May print, for example:
     *
     * Generated from Godot docs: Node.get_tree_string_pretty
     */
    fun getTreeStringPretty(): String =
        ObjectCalls.ptrcallNoArgsRetString(getTreeStringPrettyBind, handle)

    /**
     * Prints the node and its children to the console, recursively. The node does not have to be
     * inside the tree. This method outputs `NodePath`s relative to this node, and is good for
     * copy/pasting into `get_node`. See also `print_tree_pretty`. May print, for example:
     *
     * Generated from Godot docs: Node.print_tree
     */
    fun printTree() {
        ObjectCalls.ptrcallNoArgs(printTreeBind, handle)
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
        ObjectCalls.ptrcallNoArgs(printTreePrettyBind, handle)
    }

    /**
     * Calls `Object.notification` with `what` on this node and all of its children, recursively.
     *
     * Generated from Godot docs: Node.propagate_notification
     */
    fun propagateNotification(what: Int) {
        ObjectCalls.ptrcallWithIntArg(propagateNotificationBind, handle, what)
    }

    /**
     * Calls the given `method` name, passing `args` as arguments, on this node and all of its
     * children, recursively. If `parent_first` is `true`, the method is called on this node first,
     * then on all of its children. If `false`, the children's methods are called first.
     *
     * Generated from Godot docs: Node.propagate_call
     */
    fun propagateCall(method: String, args: List<Any?> = emptyList(), parentFirst: Boolean = false) {
        ObjectCalls.ptrcallWithStringNameArrayBoolArgs(propagateCallBind, handle, method, args, parentFirst)
    }

    /**
     * Returns the `SceneTree` that contains this node. If this node is not inside the tree, generates
     * an error and returns `null`. See also `is_inside_tree`.
     *
     * Generated from Godot docs: Node.get_tree
     */
    fun getTree(): SceneTree {
        check(ObjectCalls.ptrcallNoArgsRetObject(getTreeBind, handle).address() != 0L) {
            "Node is not inside a SceneTree"
        }
        return SceneTree
    }

    /**
     * Creates a new `Tween` and binds it to this node. This is the equivalent of doing:
     *
     * Generated from Godot docs: Node.create_tween
     */
    fun createTween(): Tween? =
        Tween.wrap(ObjectCalls.ptrcallNoArgsRetObject(createTweenBind, handle))

    /**
     * Returns the node's closest `Viewport` ancestor, if the node is inside the tree. Otherwise,
     * returns `null`.
     *
     * Generated from Godot docs: Node.get_viewport
     */
    fun getViewport(): Viewport? =
        ObjectCalls.ptrcallNoArgsRetObject(getViewportBind, handle).let {
            if (it.address() == 0L) null else Viewport(it)
        }

    /**
     * Returns the `Window` that contains this node. If the node is in the main window, this is
     * equivalent to getting the root node (`get_tree().get_root()`).
     *
     * Generated from Godot docs: Node.get_window
     */
    fun getWindow(): Window? =
        Window.wrap(ObjectCalls.ptrcallNoArgsRetObject(getWindowBind, handle))

    /**
     * Returns the `Window` that contains this node, or the last exclusive child in a chain of windows
     * starting with the one that contains this node.
     *
     * Generated from Godot docs: Node.get_last_exclusive_window
     */
    fun getLastExclusiveWindow(): Window? =
        Window.wrap(ObjectCalls.ptrcallNoArgsRetObject(getLastExclusiveWindowBind, handle))

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
    fun canProcess(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(canProcessBind, handle)

    /**
     * Returns `true` if processing is enabled (see `set_process`).
     *
     * Generated from Godot docs: Node.is_processing
     */
    fun isProcessing(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isProcessingBind, handle)

    /**
     * Returns `true` if physics processing is enabled (see `set_physics_process`).
     *
     * Generated from Godot docs: Node.is_physics_processing
     */
    fun isPhysicsProcessing(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isPhysicsProcessingBind, handle)

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
    fun getProcessDeltaTime(): Double =
        ObjectCalls.ptrcallNoArgsRetDouble(getProcessDeltaTimeBind, handle)

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
    fun getPhysicsProcessDeltaTime(): Double =
        ObjectCalls.ptrcallNoArgsRetDouble(getPhysicsProcessDeltaTimeBind, handle)

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
        ObjectCalls.ptrcallWithStringNameAndBoolArg(addToGroupBind, handle, group, persistent)
    }

    /**
     * Removes the node from the given `group`. Does nothing if the node is not in the `group`. See
     * also notes in the description, and the `SceneTree`'s group methods.
     *
     * Generated from Godot docs: Node.remove_from_group
     */
    fun removeFromGroup(group: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeFromGroupBind, handle, group)
    }

    /**
     * Returns `true` if this node has been added to the given `group`. See `add_to_group` and
     * `remove_from_group`. See also notes in the description, and the `SceneTree`'s group methods.
     *
     * Generated from Godot docs: Node.is_in_group
     */
    fun isInGroup(group: String): Boolean =
        ObjectCalls.ptrcallWithStringNameArgRetBool(isInGroupBind, handle, group)

    /**
     * Returns an `Array` of group names that the node has been added to. Note: To improve performance,
     * the order of group names is not guaranteed and may vary between project runs. Therefore, do not
     * rely on the group order. Note: This method may also return some group names starting with an
     * underscore (`_`). These are internally used by the engine. To avoid conflicts, do not use custom
     * groups starting with underscores. To exclude internal groups, see the following code snippet:
     *
     * Generated from Godot docs: Node.get_groups
     */
    fun getGroups(): List<String> =
        ObjectCalls.ptrcallNoArgsRetStringNameList(getGroupsBind, handle)

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
        ObjectCalls.ptrcallWithBoolArg(setProcessBind, handle, enable)
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
        ObjectCalls.ptrcallWithBoolArg(setPhysicsProcessBind, handle, enable)
    }

    /**
     * If set to `true`, enables input processing. Note: If `_input` is overridden, this will be
     * automatically enabled before `_ready` is called. Input processing is also already enabled for
     * GUI controls, such as `Button` and `TextEdit`.
     *
     * Generated from Godot docs: Node.set_process_input
     */
    fun setProcessInput(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setProcessInputBind, handle, enable)
    }

    /**
     * Returns `true` if the node is processing input (see `set_process_input`).
     *
     * Generated from Godot docs: Node.is_processing_input
     */
    fun isProcessingInput(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isProcessingInputBind, handle)

    /**
     * If set to `true`, enables shortcut processing for this node. Note: If `_shortcut_input` is
     * overridden, this will be automatically enabled before `_ready` is called.
     *
     * Generated from Godot docs: Node.set_process_shortcut_input
     */
    fun setProcessShortcutInput(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setProcessShortcutInputBind, handle, enable)
    }

    /**
     * Returns `true` if the node is processing shortcuts (see `set_process_shortcut_input`).
     *
     * Generated from Godot docs: Node.is_processing_shortcut_input
     */
    fun isProcessingShortcutInput(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isProcessingShortcutInputBind, handle)

    /**
     * If set to `true`, enables unhandled input processing. It enables the node to receive all input
     * that was not previously handled (usually by a `Control`). Note: If `_unhandled_input` is
     * overridden, this will be automatically enabled before `_ready` is called. Unhandled input
     * processing is also already enabled for GUI controls, such as `Button` and `TextEdit`.
     *
     * Generated from Godot docs: Node.set_process_unhandled_input
     */
    fun setProcessUnhandledInput(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setProcessUnhandledInputBind, handle, enable)
    }

    /**
     * Returns `true` if the node is processing unhandled input (see `set_process_unhandled_input`).
     *
     * Generated from Godot docs: Node.is_processing_unhandled_input
     */
    fun isProcessingUnhandledInput(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isProcessingUnhandledInputBind, handle)

    /**
     * If set to `true`, enables unhandled key input processing. Note: If `_unhandled_key_input` is
     * overridden, this will be automatically enabled before `_ready` is called.
     *
     * Generated from Godot docs: Node.set_process_unhandled_key_input
     */
    fun setProcessUnhandledKeyInput(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setProcessUnhandledKeyInputBind, handle, enable)
    }

    /**
     * Returns `true` if the node is processing unhandled key input (see
     * `set_process_unhandled_key_input`).
     *
     * Generated from Godot docs: Node.is_processing_unhandled_key_input
     */
    fun isProcessingUnhandledKeyInput(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isProcessingUnhandledKeyInputBind, handle)

    /**
     * Returns `true` if the node is ready, i.e. it's inside scene tree and all its children are
     * initialized. `request_ready` resets it back to `false`.
     *
     * Generated from Godot docs: Node.is_node_ready
     */
    fun isNodeReady(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isNodeReadyBind, handle)

    /**
     * Returns `true` if the local system is the multiplayer authority of this node.
     *
     * Generated from Godot docs: Node.is_multiplayer_authority
     */
    fun isMultiplayerAuthority(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isMultiplayerAuthorityBind, handle)

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
        ObjectCalls.ptrcallWithIntAndBoolArgs(setMultiplayerAuthorityBind, handle, id, recursive)
    }

    /**
     * Returns the peer ID of the multiplayer authority for this node. See `set_multiplayer_authority`.
     *
     * Generated from Godot docs: Node.get_multiplayer_authority
     */
    fun getMultiplayerAuthority(): Long =
        ObjectCalls.ptrcallNoArgsRetInt(getMultiplayerAuthorityBind, handle).toLong()

    /**
     * The `MultiplayerAPI` instance associated with this node. See `SceneTree.get_multiplayer`. Note:
     * Renaming the node, or moving it in the tree, will not move the `MultiplayerAPI` to the new path,
     * you will have to update this manually.
     *
     * Generated from Godot docs: Node.get_multiplayer
     */
    fun getMultiplayer(): MultiplayerAPI? =
        MultiplayerAPI.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMultiplayerBind, handle))

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
        ObjectCalls.ptrcallWithStringNameAndVariantArg(rpcConfigBind, handle, method, config)
    }

    /**
     * Returns a `Dictionary` mapping method names to their RPC configuration defined for this node
     * using `rpc_config`. Note: This method only returns the RPC configuration assigned via
     * `rpc_config`. See `Script.get_rpc_config` to retrieve the RPCs defined by the `Script`.
     *
     * Generated from Godot docs: Node.get_node_rpc_config
     */
    fun getNodeRpcConfig(): Any? =
        ObjectCalls.ptrcallNoArgsRetVariantScalar(getNodeRpcConfigBind, handle)

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
    fun rpc(method: String, vararg extraArgs: Any?): Long =
        (ObjectCalls.callWithVariantArgs(rpcBind, handle, listOf(method, *extraArgs)) as Number).toLong()

    /**
     * Sends an RPC and falls back to a local method call if Godot reports that the RPC could not be sent.
     *
     * This is useful for `@Rpc(callLocal = true)` gameplay events that should also work while the node
     * uses an offline or not-yet-connected multiplayer peer.
     */
    fun callLocalRpc(method: String, vararg extraArgs: Any?) {
        if (rpc(method, *extraArgs) != 0L) {
            call(method, *extraArgs)
        }
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
    fun rpcId(peerId: Long, method: String, vararg extraArgs: Any?): Long =
        (ObjectCalls.callWithVariantArgs(rpcIdBind, handle, listOf(peerId, method, *extraArgs)) as Number).toLong()

    /**
     * The node's execution order of the process callbacks (`_process`, `NOTIFICATION_PROCESS`, and
     * `NOTIFICATION_INTERNAL_PROCESS`). Nodes whose priority value is lower call their process
     * callbacks first, regardless of tree order.
     *
     * Generated from Godot docs: Node.set_process_priority
     */
    fun setProcessPriority(priority: Long) {
        ObjectCalls.ptrcallWithIntArg(setProcessPriorityBind, handle, priority.toInt())
    }

    /**
     * The node's execution order of the process callbacks (`_process`, `NOTIFICATION_PROCESS`, and
     * `NOTIFICATION_INTERNAL_PROCESS`). Nodes whose priority value is lower call their process
     * callbacks first, regardless of tree order.
     *
     * Generated from Godot docs: Node.get_process_priority
     */
    fun getProcessPriority(): Long =
        ObjectCalls.ptrcallNoArgsRetInt(getProcessPriorityBind, handle).toLong()

    /**
     * Similar to `process_priority` but for `NOTIFICATION_PHYSICS_PROCESS`, `_physics_process`, or
     * `NOTIFICATION_INTERNAL_PHYSICS_PROCESS`.
     *
     * Generated from Godot docs: Node.set_physics_process_priority
     */
    fun setPhysicsProcessPriority(priority: Long) {
        ObjectCalls.ptrcallWithIntArg(setPhysicsProcessPriorityBind, handle, priority.toInt())
    }

    /**
     * Similar to `process_priority` but for `NOTIFICATION_PHYSICS_PROCESS`, `_physics_process`, or
     * `NOTIFICATION_INTERNAL_PHYSICS_PROCESS`.
     *
     * Generated from Godot docs: Node.get_physics_process_priority
     */
    fun getPhysicsProcessPriority(): Long =
        ObjectCalls.ptrcallNoArgsRetInt(getPhysicsProcessPriorityBind, handle).toLong()

    /**
     * If set to `true`, the node appears folded in the Scene dock. As a result, all of its children
     * are hidden. This method is intended to be used in editor plugins and tools, but it also works in
     * release builds. See also `is_displayed_folded`.
     *
     * Generated from Godot docs: Node.set_display_folded
     */
    fun setDisplayFolded(fold: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDisplayFoldedBind, handle, fold)
    }

    /**
     * Returns `true` if the node is folded (collapsed) in the Scene dock. This method is intended to
     * be used in editor plugins and tools. See also `set_display_folded`.
     *
     * Generated from Godot docs: Node.is_displayed_folded
     */
    fun isDisplayedFolded(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isDisplayedFoldedBind, handle)

    /**
     * If `true`, the node can be accessed from any node sharing the same `owner` or from the `owner`
     * itself, with special `%Name` syntax in `get_node`. Note: If another node with the same `owner`
     * shares the same `name` as this node, the other node will no longer be accessible as unique.
     *
     * Generated from Godot docs: Node.set_unique_name_in_owner
     */
    fun setUniqueNameInOwner(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUniqueNameInOwnerBind, handle, enable)
    }

    /**
     * If `true`, the node can be accessed from any node sharing the same `owner` or from the `owner`
     * itself, with special `%Name` syntax in `get_node`. Note: If another node with the same `owner`
     * shares the same `name` as this node, the other node will no longer be accessible as unique.
     *
     * Generated from Godot docs: Node.is_unique_name_in_owner
     */
    fun isUniqueNameInOwner(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isUniqueNameInOwnerBind, handle)

    /**
     * An optional description to the node. It will be displayed as a tooltip when hovering over the
     * node in the editor's Scene dock.
     *
     * Generated from Godot docs: Node.set_editor_description
     */
    fun setEditorDescription(description: String) {
        ObjectCalls.ptrcallWithStringArg(setEditorDescriptionBind, handle, description)
    }

    /**
     * An optional description to the node. It will be displayed as a tooltip when hovering over the
     * node in the editor's Scene dock.
     *
     * Generated from Godot docs: Node.get_editor_description
     */
    fun getEditorDescription(): String =
        ObjectCalls.ptrcallNoArgsRetString(getEditorDescriptionBind, handle)

    /**
     * The node's processing behavior. To check if the node can process in its current mode, use
     * `can_process`.
     *
     * Generated from Godot docs: Node.set_process_mode
     */
    fun setProcessMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setProcessModeBind, handle, mode)
    }

    /**
     * The node's processing behavior. To check if the node can process in its current mode, use
     * `can_process`.
     *
     * Generated from Godot docs: Node.get_process_mode
     */
    fun getProcessMode(): Long =
        ObjectCalls.ptrcallNoArgsRetLong(getProcessModeBind, handle)

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
        ObjectCalls.ptrcallWithLongArg(setProcessThreadGroupBind, handle, mode)
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
    fun getProcessThreadGroup(): Long =
        ObjectCalls.ptrcallNoArgsRetLong(getProcessThreadGroupBind, handle)

    /**
     * Set whether the current thread group will process messages (calls to
     * `call_deferred_thread_group` on threads), and whether it wants to receive them during regular
     * process or physics process callbacks.
     *
     * Generated from Godot docs: Node.set_process_thread_messages
     */
    fun setProcessThreadMessages(flags: Long) {
        ObjectCalls.ptrcallWithLongArg(setProcessThreadMessagesBind, handle, flags)
    }

    /**
     * Set whether the current thread group will process messages (calls to
     * `call_deferred_thread_group` on threads), and whether it wants to receive them during regular
     * process or physics process callbacks.
     *
     * Generated from Godot docs: Node.get_process_thread_messages
     */
    fun getProcessThreadMessages(): Long =
        ObjectCalls.ptrcallNoArgsRetLong(getProcessThreadMessagesBind, handle)

    /**
     * Change the process thread group order. Groups with a lesser order will process before groups
     * with a greater order. This is useful when a large amount of nodes process in sub thread and,
     * afterwards, another group wants to collect their result in the main thread, as an example.
     *
     * Generated from Godot docs: Node.set_process_thread_group_order
     */
    fun setProcessThreadGroupOrder(order: Long) {
        ObjectCalls.ptrcallWithIntArg(setProcessThreadGroupOrderBind, handle, order.toInt())
    }

    /**
     * Change the process thread group order. Groups with a lesser order will process before groups
     * with a greater order. This is useful when a large amount of nodes process in sub thread and,
     * afterwards, another group wants to collect their result in the main thread, as an example.
     *
     * Generated from Godot docs: Node.get_process_thread_group_order
     */
    fun getProcessThreadGroupOrder(): Long =
        ObjectCalls.ptrcallNoArgsRetInt(getProcessThreadGroupOrderBind, handle).toLong()

    /**
     * Queues an accessibility information update for this node.
     *
     * Generated from Godot docs: Node.queue_accessibility_update
     */
    fun queueAccessibilityUpdate() {
        ObjectCalls.ptrcallNoArgs(queueAccessibilityUpdateBind, handle)
    }

    /**
     * Returns main accessibility element RID. Note: This method should be called only during
     * accessibility information updates (`NOTIFICATION_ACCESSIBILITY_UPDATE`).
     *
     * Generated from Godot docs: Node.get_accessibility_element
     */
    fun getAccessibilityElement(): RID =
        ObjectCalls.ptrcallNoArgsRetRID(getAccessibilityElementBind, handle)

    /**
     * This function is similar to `Object.call_deferred` except that the call will take place when the
     * node thread group is processed. If the node thread group processes in sub-threads, then the call
     * will be done on that thread, right before `NOTIFICATION_PROCESS` or
     * `NOTIFICATION_PHYSICS_PROCESS`, the `_process` or `_physics_process` or their internal versions
     * are called.
     *
     * Generated from Godot docs: Node.call_deferred_thread_group
     */
    fun callDeferredThreadGroup(method: String, vararg extraArgs: Any?): Any? =
        ObjectCalls.callWithVariantArgs(callDeferredThreadGroupBind, handle, listOf(method, *extraArgs))

    /**
     * Similar to `call_deferred_thread_group`, but for setting properties.
     *
     * Generated from Godot docs: Node.set_deferred_thread_group
     */
    fun setDeferredThreadGroup(property: String, value: Any?) {
        ObjectCalls.ptrcallWithStringNameAndVariantArg(setDeferredThreadGroupBind, handle, property, value)
    }

    /**
     * Similar to `call_deferred_thread_group`, but for notifications.
     *
     * Generated from Godot docs: Node.notify_deferred_thread_group
     */
    fun notifyDeferredThreadGroup(what: Int) {
        ObjectCalls.ptrcallWithIntArg(notifyDeferredThreadGroupBind, handle, what)
    }

    /**
     * This function ensures that the calling of this function will succeed, no matter whether it's
     * being done from a thread or not. If called from a thread that is not allowed to call the
     * function, the call will become deferred. Otherwise, the call will go through directly.
     *
     * Generated from Godot docs: Node.call_thread_safe
     */
    fun callThreadSafe(method: String, vararg extraArgs: Any?): Any? =
        ObjectCalls.callWithVariantArgs(callThreadSafeBind, handle, listOf(method, *extraArgs))

    /**
     * Similar to `call_thread_safe`, but for setting properties.
     *
     * Generated from Godot docs: Node.set_thread_safe
     */
    fun setThreadSafe(property: String, value: Any?) {
        ObjectCalls.ptrcallWithStringNameAndVariantArg(setThreadSafeBind, handle, property, value)
    }

    /**
     * Similar to `call_thread_safe`, but for notifications.
     *
     * Generated from Godot docs: Node.notify_thread_safe
     */
    fun notifyThreadSafe(what: Int) {
        ObjectCalls.ptrcallWithIntArg(notifyThreadSafeBind, handle, what)
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
        ObjectCalls.ptrcallWithBoolArg(setProcessInternalBind, handle, enable)
    }

    /**
     * Returns `true` if internal processing is enabled (see `set_process_internal`).
     *
     * Generated from Godot docs: Node.is_processing_internal
     */
    fun isProcessingInternal(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isProcessingInternalBind, handle)

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
        ObjectCalls.ptrcallWithBoolArg(setPhysicsProcessInternalBind, handle, enable)
    }

    /**
     * Returns `true` if internal physics processing is enabled (see `set_physics_process_internal`).
     *
     * Generated from Godot docs: Node.is_physics_processing_internal
     */
    fun isPhysicsProcessingInternal(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isPhysicsProcessingInternalBind, handle)

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
        ObjectCalls.ptrcallWithLongArg(setPhysicsInterpolationModeBind, handle, mode)
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
    fun getPhysicsInterpolationMode(): Long =
        ObjectCalls.ptrcallNoArgsRetLong(getPhysicsInterpolationModeBind, handle)

    /**
     * Returns `true` if physics interpolation is enabled for this node (see
     * `physics_interpolation_mode`). Note: Interpolation will only be active if both the flag is set
     * and physics interpolation is enabled within the `SceneTree`. This can be tested using
     * `is_physics_interpolated_and_enabled`.
     *
     * Generated from Godot docs: Node.is_physics_interpolated
     */
    fun isPhysicsInterpolated(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isPhysicsInterpolatedBind, handle)

    /**
     * Returns `true` if physics interpolation is enabled (see `physics_interpolation_mode`) and
     * enabled in the `SceneTree`. This is a convenience version of `is_physics_interpolated` that also
     * checks whether physics interpolation is enabled globally. See `SceneTree.physics_interpolation`
     * and `ProjectSettings.physics/common/physics_interpolation`.
     *
     * Generated from Godot docs: Node.is_physics_interpolated_and_enabled
     */
    fun isPhysicsInterpolatedAndEnabled(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isPhysicsInterpolatedAndEnabledBind, handle)

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
        ObjectCalls.ptrcallNoArgs(resetPhysicsInterpolationBind, handle)
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
        ObjectCalls.ptrcallNoArgs(queueFreeBind, handle)
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
        ObjectCalls.ptrcallWithLongArg(setAutoTranslateModeBind, handle, mode)
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
    fun getAutoTranslateMode(): Long =
        ObjectCalls.ptrcallNoArgsRetLong(getAutoTranslateModeBind, handle)

    /**
     * Returns `true` if this node can automatically translate messages depending on the current
     * locale. See `auto_translate_mode`, `atr`, and `atr_n`.
     *
     * Generated from Godot docs: Node.can_auto_translate
     */
    fun canAutoTranslate(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(canAutoTranslateBind, handle)

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
    fun atr(message: String, context: String = ""): String =
        ObjectCalls.ptrcallWithStringAndStringNameArgRetString(atrBind, handle, message, context)

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
    fun atrN(message: String, pluralMessage: String, n: Int, context: String = ""): String =
        ObjectCalls.ptrcallWithStringStringNameIntStringNameArgsRetString(
            atrNBind,
            handle,
            message,
            pluralMessage,
            n,
            context,
        )

    /**
     * Makes this node inherit the translation domain from its parent node. If this node has no parent,
     * the main translation domain will be used. This is the default behavior for all nodes. Calling
     * `Object.set_translation_domain` disables this behavior.
     *
     * Generated from Godot docs: Node.set_translation_domain_inherited
     */
    fun setTranslationDomainInherited() {
        ObjectCalls.ptrcallNoArgs(setTranslationDomainInheritedBind, handle)
    }

    /**
     * If set to `true`, the node becomes an `InstancePlaceholder` when packed and instantiated from a
     * `PackedScene`. See also `get_scene_instance_load_placeholder`.
     *
     * Generated from Godot docs: Node.set_scene_instance_load_placeholder
     */
    fun setSceneInstanceLoadPlaceholder(loadPlaceholder: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSceneInstanceLoadPlaceholderBind, handle, loadPlaceholder)
    }

    /**
     * Returns `true` if this node is an instance load placeholder. See `InstancePlaceholder` and
     * `set_scene_instance_load_placeholder`.
     *
     * Generated from Godot docs: Node.get_scene_instance_load_placeholder
     */
    fun getSceneInstanceLoadPlaceholder(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(getSceneInstanceLoadPlaceholderBind, handle)

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
        ObjectCalls.ptrcallWithObjectAndBoolArg(replaceByBind, handle, node.handle, keepGroups)
    }

    /**
     * Set to `true` to allow all nodes owned by `node` to be available, and editable, in the Scene
     * dock, even if their `owner` is not the scene root. This method is intended to be used in editor
     * plugins and tools, but it also works in release builds. See also `is_editable_instance`.
     *
     * Generated from Godot docs: Node.set_editable_instance
     */
    fun setEditableInstance(node: Node, isEditable: Boolean) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(setEditableInstanceBind, handle, node.handle, isEditable)
    }

    /**
     * Returns `true` if `node` has editable children enabled relative to this node. This method is
     * intended to be used in editor plugins and tools. See also `set_editable_instance`.
     *
     * Generated from Godot docs: Node.is_editable_instance
     */
    fun isEditableInstance(node: Node): Boolean =
        ObjectCalls.ptrcallWithObjectArgRetBool(isEditableInstanceBind, handle, node.handle)

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
        ObjectCalls.ptrcallNoArgs(requestReadyBind, handle)
    }

    /**
     * Refreshes the warnings displayed for this node in the Scene dock. Use
     * `_get_configuration_warnings` to customize the warning messages to display.
     *
     * Generated from Godot docs: Node.update_configuration_warnings
     */
    fun updateConfigurationWarnings() {
        ObjectCalls.ptrcallNoArgs(updateConfigurationWarningsBind, handle)
    }

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
            ObjectCalls.ptrcallNoArgs(printOrphanNodesBind, MemorySegment.NULL)
        }

        /**
         * Returns object IDs of all orphan nodes (nodes outside the `SceneTree`). Used for debugging.
         * Note: `get_orphan_node_ids` only works in debug builds. When called in a project exported in
         * release mode, `get_orphan_node_ids` will return an empty array.
         *
         * Generated from Godot docs: Node.get_orphan_node_ids
         */
        fun getOrphanNodeIds(): List<Long> =
            ObjectCalls.ptrcallNoArgsRetLongList(getOrphanNodeIdsBind, MemorySegment.NULL)

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Node? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Node? =
            if (handle.address() == 0L) null else Node(handle)

        const val NOTIFICATION_ENTER_TREE = 10L
        const val NOTIFICATION_EXIT_TREE = 11L
        const val NOTIFICATION_MOVED_IN_PARENT = 12L
        const val NOTIFICATION_READY = 13L
        const val NOTIFICATION_PAUSED = 14L
        const val NOTIFICATION_UNPAUSED = 15L
        const val NOTIFICATION_PHYSICS_PROCESS = 16L
        const val NOTIFICATION_PROCESS = 17L
        const val NOTIFICATION_PARENTED = 18L
        const val NOTIFICATION_UNPARENTED = 19L
        const val NOTIFICATION_SCENE_INSTANTIATED = 20L
        const val NOTIFICATION_DRAG_BEGIN = 21L
        const val NOTIFICATION_DRAG_END = 22L
        const val NOTIFICATION_PATH_RENAMED = 23L
        const val NOTIFICATION_CHILD_ORDER_CHANGED = 24L
        const val NOTIFICATION_INTERNAL_PROCESS = 25L
        const val NOTIFICATION_INTERNAL_PHYSICS_PROCESS = 26L
        const val NOTIFICATION_POST_ENTER_TREE = 27L
        const val NOTIFICATION_DISABLED = 28L
        const val NOTIFICATION_ENABLED = 29L
        const val NOTIFICATION_RESET_PHYSICS_INTERPOLATION = 2001L
        const val NOTIFICATION_EDITOR_PRE_SAVE = 9001L
        const val NOTIFICATION_EDITOR_POST_SAVE = 9002L
        const val NOTIFICATION_WM_MOUSE_ENTER = 1002L
        const val NOTIFICATION_WM_MOUSE_EXIT = 1003L
        const val NOTIFICATION_WM_WINDOW_FOCUS_IN = 1004L
        const val NOTIFICATION_WM_WINDOW_FOCUS_OUT = 1005L
        const val NOTIFICATION_WM_CLOSE_REQUEST = 1006L
        const val NOTIFICATION_WM_GO_BACK_REQUEST = 1007L
        const val NOTIFICATION_WM_SIZE_CHANGED = 1008L
        const val NOTIFICATION_WM_DPI_CHANGE = 1009L
        const val NOTIFICATION_VP_MOUSE_ENTER = 1010L
        const val NOTIFICATION_VP_MOUSE_EXIT = 1011L
        const val NOTIFICATION_WM_POSITION_CHANGED = 1012L
        const val NOTIFICATION_OS_MEMORY_WARNING = 2009L
        const val NOTIFICATION_TRANSLATION_CHANGED = 2010L
        const val NOTIFICATION_WM_ABOUT = 2011L
        const val NOTIFICATION_CRASH = 2012L
        const val NOTIFICATION_OS_IME_UPDATE = 2013L
        const val NOTIFICATION_APPLICATION_RESUMED = 2014L
        const val NOTIFICATION_APPLICATION_PAUSED = 2015L
        const val NOTIFICATION_APPLICATION_FOCUS_IN = 2016L
        const val NOTIFICATION_APPLICATION_FOCUS_OUT = 2017L
        const val NOTIFICATION_TEXT_SERVER_CHANGED = 2018L
        const val NOTIFICATION_ACCESSIBILITY_UPDATE = 3000L
        const val NOTIFICATION_ACCESSIBILITY_INVALIDATE = 3001L

        const val PROCESS_MODE_INHERIT = 0L
        const val PROCESS_MODE_PAUSABLE = 1L
        const val PROCESS_MODE_WHEN_PAUSED = 2L
        const val PROCESS_MODE_ALWAYS = 3L
        const val PROCESS_MODE_DISABLED = 4L

        const val PROCESS_THREAD_GROUP_INHERIT = 0L
        const val PROCESS_THREAD_GROUP_MAIN_THREAD = 1L
        const val PROCESS_THREAD_GROUP_SUB_THREAD = 2L

        const val FLAG_PROCESS_THREAD_MESSAGES = 1L
        const val FLAG_PROCESS_THREAD_MESSAGES_PHYSICS = 2L
        const val FLAG_PROCESS_THREAD_MESSAGES_ALL = 3L

        const val PHYSICS_INTERPOLATION_MODE_INHERIT = 0L
        const val PHYSICS_INTERPOLATION_MODE_ON = 1L
        const val PHYSICS_INTERPOLATION_MODE_OFF = 2L

        const val AUTO_TRANSLATE_MODE_INHERIT = 0L
        const val AUTO_TRANSLATE_MODE_ALWAYS = 1L
        const val AUTO_TRANSLATE_MODE_DISABLED = 2L

        const val INTERNAL_MODE_DISABLED = 0L
        const val INTERNAL_MODE_FRONT = 1L
        const val INTERNAL_MODE_BACK = 2L

        const val DUPLICATE_SIGNALS = 1L
        const val DUPLICATE_GROUPS = 2L
        const val DUPLICATE_SCRIPTS = 4L
        const val DUPLICATE_USE_INSTANTIATION = 8L
        const val DUPLICATE_INTERNAL_STATE = 16L
        const val DUPLICATE_DEFAULT = 15L

        private const val NOARGS_BOOL_HASH = 36873697L
        private const val NOARGS_STRING_HASH = 2841200299L
        private const val NOARGS_SCENE_FILE_PATH_HASH = 201670096L
        private const val GET_TREE_HASH = 2958820483L
        private const val CREATE_TWEEN_HASH = 3426978995L
        private const val GET_VIEWPORT_HASH = 3596683776L
        private const val NOARGS_DOUBLE_HASH = 1740695150L
        private const val NOARGS_LONG_HASH = 3905245786L
        private const val BOOL_LONG_HASH = 894402480L
        private const val BOOL_VOID_HASH = 2586408642L
        private const val LONG_VOID_HASH = 1286410249L
        private const val STRING_VOID_HASH = 83702148L
        private const val SET_PROCESS_MODE_HASH = 1841290486L
        private const val GET_PROCESS_MODE_HASH = 739966102L
        private const val SET_PROCESS_THREAD_GROUP_HASH = 2275442745L
        private const val GET_PROCESS_THREAD_GROUP_HASH = 1866404740L
        private const val SET_PROCESS_THREAD_MESSAGES_HASH = 1357280998L
        private const val GET_PROCESS_THREAD_MESSAGES_HASH = 4228993612L
        private const val SET_PHYSICS_INTERPOLATION_MODE_HASH = 3202404928L
        private const val GET_PHYSICS_INTERPOLATION_MODE_HASH = 2920385216L
        private const val SET_AUTO_TRANSLATE_MODE_HASH = 776149714L
        private const val GET_AUTO_TRANSLATE_MODE_HASH = 2498906432L
        private const val STRING_NAME_BOOL_VOID_HASH = 3683006648L
        private const val STRING_NAME_VOID_HASH = 3304788590L
        private const val STRING_NAME_BOOL_HASH = 2619796661L
        private const val NODE_PATH_BOOL_HASH = 861721659L
        private const val NODE_PATH_OBJECT_HASH = 2734337346L
        private const val NOARGS_NODE_HASH = 3160264692L
        private const val ADD_CHILD_HASH = 3863233950L
        private const val NODE_VOID_HASH = 1078189570L
        private const val DUPLICATE_HASH = 3511555459L
        private const val PRINT_ORPHAN_NODES_HASH = 3218959716L
        private const val GET_ORPHAN_NODE_IDS_HASH = 2915620761L
        private const val ADD_SIBLING_HASH = 2570952461L
        private const val SET_NAME_HASH = 3304788590L
        private const val GET_NAME_HASH = 2002593661L
        private const val REPARENT_HASH = 3685795103L
        private const val GET_CHILD_HASH = 541253412L
        private const val FIND_CHILD_HASH = 2008217037L
        private const val FIND_PARENT_HASH = 1140089439L
        private const val GET_NODE_AND_RESOURCE_HASH = 502563882L
        private const val IS_OBJECT_OBJECT_BOOL_HASH = 3093956946L
        private const val GET_PATH_HASH = 4075236667L
        private const val GET_PATH_TO_HASH = 498846349L
        private const val MOVE_CHILD_HASH = 3315886247L
        private const val GET_GROUPS_HASH = 3995934104L
        private const val PRINT_TREE_HASH = 3218959716L
        private const val GET_TREE_STRING_PRETTY_HASH = 2841200299L
        private const val PROPAGATE_CALL_HASH = 1871007965L
        private const val GET_ACCESSIBILITY_ELEMENT_HASH = 2944877500L
        private const val GET_WINDOW_HASH = 1757182445L
        private const val REPLACE_BY_HASH = 2570952461L
        private const val SET_EDITABLE_INSTANCE_HASH = 2731852923L
        private const val SET_MULTIPLAYER_AUTHORITY_HASH = 972357352L
        private const val GET_MULTIPLAYER_HASH = 406750475L
        private const val RPC_CONFIG_HASH = 3776071444L
        private const val GET_NODE_RPC_CONFIG_HASH = 1214101251L
        private const val ATR_HASH = 3344478075L
        private const val ATR_N_HASH = 259354841L
        private const val RPC_HASH = 4047867050L
        private const val RPC_ID_HASH = 361499283L
        private const val CALL_THREAD_GROUP_HASH = 3400424181L

        private fun MemorySegment.toNodeOrNull(): Node? =
            if (address() == 0L) null else Node(this)

        private val printOrphanNodesBind by lazy {
            ObjectCalls.getMethodBind("Node", "print_orphan_nodes", PRINT_ORPHAN_NODES_HASH)
        }

        private val getOrphanNodeIdsBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_orphan_node_ids", GET_ORPHAN_NODE_IDS_HASH)
        }

        private val isInsideTreeBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_inside_tree", NOARGS_BOOL_HASH)
        }

        private val isPartOfEditedSceneBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_part_of_edited_scene", NOARGS_BOOL_HASH)
        }

        private val getChildCountBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_child_count", BOOL_LONG_HASH)
        }

        private val getChildrenBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_children", 873284517L)
        }

        private val getChildBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_child", GET_CHILD_HASH)
        }

        private val findChildBind by lazy {
            ObjectCalls.getMethodBind("Node", "find_child", FIND_CHILD_HASH)
        }

        private val findChildrenBind by lazy {
            ObjectCalls.getMethodBind("Node", "find_children", 2560337219L)
        }

        private val findParentBind by lazy {
            ObjectCalls.getMethodBind("Node", "find_parent", FIND_PARENT_HASH)
        }

        private val hasNodeBind by lazy {
            ObjectCalls.getMethodBind("Node", "has_node", NODE_PATH_BOOL_HASH)
        }

        private val getNodeBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_node", NODE_PATH_OBJECT_HASH)
        }

        private val getNodeOrNullBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_node_or_null", NODE_PATH_OBJECT_HASH)
        }

        private val hasNodeAndResourceBind by lazy {
            ObjectCalls.getMethodBind("Node", "has_node_and_resource", NODE_PATH_BOOL_HASH)
        }

        private val getNodeAndResourceBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_node_and_resource", GET_NODE_AND_RESOURCE_HASH)
        }

        private val getParentBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_parent", NOARGS_NODE_HASH)
        }

        private val isAncestorOfBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_ancestor_of", IS_OBJECT_OBJECT_BOOL_HASH)
        }

        private val isGreaterThanBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_greater_than", IS_OBJECT_OBJECT_BOOL_HASH)
        }

        private val getPathBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_path", GET_PATH_HASH)
        }

        private val getPathToBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_path_to", GET_PATH_TO_HASH)
        }

        private val setOwnerBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_owner", NODE_VOID_HASH)
        }

        private val getOwnerBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_owner", NOARGS_NODE_HASH)
        }

        private val setNameBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_name", SET_NAME_HASH)
        }

        private val getNameBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_name", GET_NAME_HASH)
        }

        private val addChildBind by lazy {
            ObjectCalls.getMethodBind("Node", "add_child", ADD_CHILD_HASH)
        }

        private val addSiblingBind by lazy {
            ObjectCalls.getMethodBind("Node", "add_sibling", ADD_SIBLING_HASH)
        }

        private val duplicateBind by lazy {
            ObjectCalls.getMethodBind("Node", "duplicate", DUPLICATE_HASH)
        }

        private val removeChildBind by lazy {
            ObjectCalls.getMethodBind("Node", "remove_child", NODE_VOID_HASH)
        }

        private val reparentBind by lazy {
            ObjectCalls.getMethodBind("Node", "reparent", REPARENT_HASH)
        }

        private val moveChildBind by lazy {
            ObjectCalls.getMethodBind("Node", "move_child", MOVE_CHILD_HASH)
        }

        private val getIndexBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_index", BOOL_LONG_HASH)
        }

        private val setSceneFilePathBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_scene_file_path", STRING_VOID_HASH)
        }

        private val getSceneFilePathBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_scene_file_path", NOARGS_SCENE_FILE_PATH_HASH)
        }

        private val getTreeStringBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_tree_string", NOARGS_STRING_HASH)
        }

        private val getTreeStringPrettyBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_tree_string_pretty", GET_TREE_STRING_PRETTY_HASH)
        }

        private val printTreeBind by lazy {
            ObjectCalls.getMethodBind("Node", "print_tree", PRINT_TREE_HASH)
        }

        private val printTreePrettyBind by lazy {
            ObjectCalls.getMethodBind("Node", "print_tree_pretty", PRINT_TREE_HASH)
        }

        private val propagateNotificationBind by lazy {
            ObjectCalls.getMethodBind("Node", "propagate_notification", LONG_VOID_HASH)
        }

        private val propagateCallBind by lazy {
            ObjectCalls.getMethodBind("Node", "propagate_call", PROPAGATE_CALL_HASH)
        }

        private val getTreeBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_tree", GET_TREE_HASH)
        }

        private val createTweenBind by lazy {
            ObjectCalls.getMethodBind("Node", "create_tween", CREATE_TWEEN_HASH)
        }

        private val getViewportBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_viewport", GET_VIEWPORT_HASH)
        }

        private val getWindowBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_window", GET_WINDOW_HASH)
        }

        private val getLastExclusiveWindowBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_last_exclusive_window", GET_WINDOW_HASH)
        }

        private val canProcessBind by lazy {
            ObjectCalls.getMethodBind("Node", "can_process", NOARGS_BOOL_HASH)
        }

        private val isProcessingBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_processing", NOARGS_BOOL_HASH)
        }

        private val isPhysicsProcessingBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_physics_processing", NOARGS_BOOL_HASH)
        }

        private val getProcessDeltaTimeBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_process_delta_time", NOARGS_DOUBLE_HASH)
        }

        private val getPhysicsProcessDeltaTimeBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_physics_process_delta_time", NOARGS_DOUBLE_HASH)
        }

        private val addToGroupBind by lazy {
            ObjectCalls.getMethodBind("Node", "add_to_group", STRING_NAME_BOOL_VOID_HASH)
        }

        private val removeFromGroupBind by lazy {
            ObjectCalls.getMethodBind("Node", "remove_from_group", STRING_NAME_VOID_HASH)
        }

        private val isInGroupBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_in_group", STRING_NAME_BOOL_HASH)
        }

        private val getGroupsBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_groups", GET_GROUPS_HASH)
        }

        private val setProcessBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_process", BOOL_VOID_HASH)
        }

        private val setPhysicsProcessBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_physics_process", BOOL_VOID_HASH)
        }

        private val setProcessInputBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_process_input", BOOL_VOID_HASH)
        }

        private val isProcessingInputBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_processing_input", NOARGS_BOOL_HASH)
        }

        private val setProcessShortcutInputBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_process_shortcut_input", BOOL_VOID_HASH)
        }

        private val isProcessingShortcutInputBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_processing_shortcut_input", NOARGS_BOOL_HASH)
        }

        private val setProcessUnhandledInputBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_process_unhandled_input", BOOL_VOID_HASH)
        }

        private val isProcessingUnhandledInputBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_processing_unhandled_input", NOARGS_BOOL_HASH)
        }

        private val setProcessUnhandledKeyInputBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_process_unhandled_key_input", BOOL_VOID_HASH)
        }

        private val isProcessingUnhandledKeyInputBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_processing_unhandled_key_input", NOARGS_BOOL_HASH)
        }

        private val isNodeReadyBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_node_ready", NOARGS_BOOL_HASH)
        }

        private val isMultiplayerAuthorityBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_multiplayer_authority", NOARGS_BOOL_HASH)
        }

        private val getMultiplayerAuthorityBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_multiplayer_authority", NOARGS_LONG_HASH)
        }

        private val setMultiplayerAuthorityBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_multiplayer_authority", SET_MULTIPLAYER_AUTHORITY_HASH)
        }

        private val getMultiplayerBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_multiplayer", GET_MULTIPLAYER_HASH)
        }

        private val rpcConfigBind by lazy {
            ObjectCalls.getMethodBind("Node", "rpc_config", RPC_CONFIG_HASH)
        }

        private val getNodeRpcConfigBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_node_rpc_config", GET_NODE_RPC_CONFIG_HASH)
        }

        private val rpcBind by lazy {
            ObjectCalls.getMethodBind("Node", "rpc", RPC_HASH)
        }

        private val rpcIdBind by lazy {
            ObjectCalls.getMethodBind("Node", "rpc_id", RPC_ID_HASH)
        }

        private val setProcessPriorityBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_process_priority", LONG_VOID_HASH)
        }

        private val getProcessPriorityBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_process_priority", NOARGS_LONG_HASH)
        }

        private val setPhysicsProcessPriorityBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_physics_process_priority", LONG_VOID_HASH)
        }

        private val getPhysicsProcessPriorityBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_physics_process_priority", NOARGS_LONG_HASH)
        }

        private val setDisplayFoldedBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_display_folded", BOOL_VOID_HASH)
        }

        private val isDisplayedFoldedBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_displayed_folded", NOARGS_BOOL_HASH)
        }

        private val setUniqueNameInOwnerBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_unique_name_in_owner", BOOL_VOID_HASH)
        }

        private val isUniqueNameInOwnerBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_unique_name_in_owner", NOARGS_BOOL_HASH)
        }

        private val setEditorDescriptionBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_editor_description", STRING_VOID_HASH)
        }

        private val getEditorDescriptionBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_editor_description", NOARGS_SCENE_FILE_PATH_HASH)
        }

        private val setProcessModeBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_process_mode", SET_PROCESS_MODE_HASH)
        }

        private val getProcessModeBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_process_mode", GET_PROCESS_MODE_HASH)
        }

        private val setProcessThreadGroupBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_process_thread_group", SET_PROCESS_THREAD_GROUP_HASH)
        }

        private val getProcessThreadGroupBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_process_thread_group", GET_PROCESS_THREAD_GROUP_HASH)
        }

        private val setProcessThreadMessagesBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_process_thread_messages", SET_PROCESS_THREAD_MESSAGES_HASH)
        }

        private val getProcessThreadMessagesBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_process_thread_messages", GET_PROCESS_THREAD_MESSAGES_HASH)
        }

        private val setProcessThreadGroupOrderBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_process_thread_group_order", LONG_VOID_HASH)
        }

        private val getProcessThreadGroupOrderBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_process_thread_group_order", NOARGS_LONG_HASH)
        }

        private val queueAccessibilityUpdateBind by lazy {
            ObjectCalls.getMethodBind("Node", "queue_accessibility_update", PRINT_TREE_HASH)
        }

        private val getAccessibilityElementBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_accessibility_element", GET_ACCESSIBILITY_ELEMENT_HASH)
        }

        private val callDeferredThreadGroupBind by lazy {
            ObjectCalls.getMethodBind("Node", "call_deferred_thread_group", CALL_THREAD_GROUP_HASH)
        }

        private val setDeferredThreadGroupBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_deferred_thread_group", RPC_CONFIG_HASH)
        }

        private val notifyDeferredThreadGroupBind by lazy {
            ObjectCalls.getMethodBind("Node", "notify_deferred_thread_group", LONG_VOID_HASH)
        }

        private val callThreadSafeBind by lazy {
            ObjectCalls.getMethodBind("Node", "call_thread_safe", CALL_THREAD_GROUP_HASH)
        }

        private val setThreadSafeBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_thread_safe", RPC_CONFIG_HASH)
        }

        private val notifyThreadSafeBind by lazy {
            ObjectCalls.getMethodBind("Node", "notify_thread_safe", LONG_VOID_HASH)
        }

        private val setProcessInternalBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_process_internal", BOOL_VOID_HASH)
        }

        private val isProcessingInternalBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_processing_internal", NOARGS_BOOL_HASH)
        }

        private val setPhysicsProcessInternalBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_physics_process_internal", BOOL_VOID_HASH)
        }

        private val isPhysicsProcessingInternalBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_physics_processing_internal", NOARGS_BOOL_HASH)
        }

        private val setPhysicsInterpolationModeBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_physics_interpolation_mode", SET_PHYSICS_INTERPOLATION_MODE_HASH)
        }

        private val getPhysicsInterpolationModeBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_physics_interpolation_mode", GET_PHYSICS_INTERPOLATION_MODE_HASH)
        }

        private val isPhysicsInterpolatedBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_physics_interpolated", NOARGS_BOOL_HASH)
        }

        private val isPhysicsInterpolatedAndEnabledBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_physics_interpolated_and_enabled", NOARGS_BOOL_HASH)
        }

        private val resetPhysicsInterpolationBind by lazy {
            ObjectCalls.getMethodBind("Node", "reset_physics_interpolation", 3218959716L)
        }

        private val queueFreeBind by lazy {
            ObjectCalls.getMethodBind("Node", "queue_free", 3218959716L)
        }

        private val setAutoTranslateModeBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_auto_translate_mode", SET_AUTO_TRANSLATE_MODE_HASH)
        }

        private val getAutoTranslateModeBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_auto_translate_mode", GET_AUTO_TRANSLATE_MODE_HASH)
        }

        private val canAutoTranslateBind by lazy {
            ObjectCalls.getMethodBind("Node", "can_auto_translate", NOARGS_BOOL_HASH)
        }

        private val atrBind by lazy {
            ObjectCalls.getMethodBind("Node", "atr", ATR_HASH)
        }

        private val atrNBind by lazy {
            ObjectCalls.getMethodBind("Node", "atr_n", ATR_N_HASH)
        }

        private val setTranslationDomainInheritedBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_translation_domain_inherited", 3218959716L)
        }

        private val setSceneInstanceLoadPlaceholderBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_scene_instance_load_placeholder", BOOL_VOID_HASH)
        }

        private val getSceneInstanceLoadPlaceholderBind by lazy {
            ObjectCalls.getMethodBind("Node", "get_scene_instance_load_placeholder", NOARGS_BOOL_HASH)
        }

        private val replaceByBind by lazy {
            ObjectCalls.getMethodBind("Node", "replace_by", REPLACE_BY_HASH)
        }

        private val setEditableInstanceBind by lazy {
            ObjectCalls.getMethodBind("Node", "set_editable_instance", SET_EDITABLE_INSTANCE_HASH)
        }

        private val isEditableInstanceBind by lazy {
            ObjectCalls.getMethodBind("Node", "is_editable_instance", IS_OBJECT_OBJECT_BOOL_HASH)
        }

        private val requestReadyBind by lazy {
            ObjectCalls.getMethodBind("Node", "request_ready", PRINT_TREE_HASH)
        }

        private val updateConfigurationWarningsBind by lazy {
            ObjectCalls.getMethodBind("Node", "update_configuration_warnings", PRINT_TREE_HASH)
        }
    }

    private fun describeForErrors(): String =
        "${getClassName()}#${getInstanceId()}"
}

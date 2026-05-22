package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import java.lang.foreign.MemorySegment

/**
 * Manages the game loop via a hierarchy of nodes.
 *
 * Generated from Godot docs: SceneTree
 */
object SceneTree {
    val root: Window
        get() = Window(getRoot())

    private const val BOOL_NOARGS_HASH = 36873697L
    private const val SET_BOOL_HASH = 2586408642L
    private const val GET_INT_HASH = 3905245786L
    private const val QUIT_HASH = 1995695955L
    private const val CHANGE_SCENE_TO_FILE_HASH = 166001499L
    private const val RELOAD_CURRENT_SCENE_HASH = 166280745L
    private const val UNLOAD_CURRENT_SCENE_HASH = 3218959716L
    private const val SET_MULTIPLAYER_HASH = 2385607013L
    private const val GET_MULTIPLAYER_HASH = 3453401404L
    private const val HAS_GROUP_HASH = 2619796661L
    private const val NODE_COUNT_IN_GROUP_HASH = 2458036349L
    private const val GET_ROOT_HASH = 1757182445L
    private const val GET_CURRENT_SCENE_HASH = 3160264692L
    private const val SET_CURRENT_SCENE_HASH = 1078189570L
    private const val GET_NODES_IN_GROUP_HASH = 689397652L
    private const val GET_FIRST_NODE_IN_GROUP_HASH = 4071044623L
    private const val QUEUE_DELETE_HASH = 3975164845L
    private const val CHANGE_SCENE_TO_PACKED_HASH = 107349098L
    private const val CHANGE_SCENE_TO_NODE_HASH = 2584678054L
    private const val GET_EDITED_SCENE_ROOT_HASH = 3160264692L
    private const val SET_EDITED_SCENE_ROOT_HASH = 1078189570L
    private const val CALL_GROUP_HASH = 1257962832L
    private const val NOTIFY_GROUP_HASH = 2415702435L
    private const val CALL_GROUP_FLAGS_HASH = 1527739229L
    private const val NOTIFY_GROUP_FLAGS_HASH = 1245489420L
    private const val SET_GROUP_FLAGS_HASH = 3497599527L
    private const val SET_GROUP_HASH = 1279312029L
    private const val CREATE_TWEEN_HASH = 3426978995L
    private const val GET_PROCESSED_TWEENS_HASH = 2915620761L
    private const val CREATE_TIMER_HASH = 2709170273L
    private const val GET_MAIN_LOOP_HASH = 1016888095L

    private val engineSingleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("Engine")
    }

    private val getMainLoopBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_main_loop", GET_MAIN_LOOP_HASH)
    }

    private fun sceneTreeObject(): MemorySegment =
        ObjectCalls.ptrcallNoArgsRetObject(getMainLoopBind, engineSingleton)

    private val isPausedBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "is_paused", BOOL_NOARGS_HASH)
    }

    private val setPauseBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "set_pause", SET_BOOL_HASH)
    }

    private val getNodeCountBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "get_node_count", GET_INT_HASH)
    }

    private val getFrameBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "get_frame", GET_INT_HASH)
    }

    private val quitBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "quit", QUIT_HASH)
    }

    private val changeSceneToFileBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "change_scene_to_file", CHANGE_SCENE_TO_FILE_HASH)
    }

    private val reloadCurrentSceneBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "reload_current_scene", RELOAD_CURRENT_SCENE_HASH)
    }

    private val unloadCurrentSceneBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "unload_current_scene", UNLOAD_CURRENT_SCENE_HASH)
    }

    private val setMultiplayerBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "set_multiplayer", SET_MULTIPLAYER_HASH)
    }

    private val getMultiplayerBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "get_multiplayer", GET_MULTIPLAYER_HASH)
    }

    private val isMultiplayerPollEnabledBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "is_multiplayer_poll_enabled", BOOL_NOARGS_HASH)
    }

    private val setMultiplayerPollEnabledBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "set_multiplayer_poll_enabled", SET_BOOL_HASH)
    }

    private val hasGroupBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "has_group", HAS_GROUP_HASH)
    }

    private val getNodeCountInGroupBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "get_node_count_in_group", NODE_COUNT_IN_GROUP_HASH)
    }

    private val getRootBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "get_root", GET_ROOT_HASH)
    }

    private val getCurrentSceneBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "get_current_scene", GET_CURRENT_SCENE_HASH)
    }

    private val setCurrentSceneBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "set_current_scene", SET_CURRENT_SCENE_HASH)
    }

    private val getFirstNodeInGroupBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "get_first_node_in_group", GET_FIRST_NODE_IN_GROUP_HASH)
    }

    private val getNodesInGroupBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "get_nodes_in_group", GET_NODES_IN_GROUP_HASH)
    }

    private val queueDeleteBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "queue_delete", QUEUE_DELETE_HASH)
    }

    private val changeSceneToPackedBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "change_scene_to_packed", CHANGE_SCENE_TO_PACKED_HASH)
    }

    private val changeSceneToNodeBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "change_scene_to_node", CHANGE_SCENE_TO_NODE_HASH)
    }

    private val getEditedSceneRootBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "get_edited_scene_root", GET_EDITED_SCENE_ROOT_HASH)
    }

    private val setEditedSceneRootBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "set_edited_scene_root", SET_EDITED_SCENE_ROOT_HASH)
    }

    private val callGroupBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "call_group", CALL_GROUP_HASH)
    }

    private val notifyGroupBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "notify_group", NOTIFY_GROUP_HASH)
    }

    private val callGroupFlagsBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "call_group_flags", CALL_GROUP_FLAGS_HASH)
    }

    private val notifyGroupFlagsBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "notify_group_flags", NOTIFY_GROUP_FLAGS_HASH)
    }

    private val setGroupFlagsBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "set_group_flags", SET_GROUP_FLAGS_HASH)
    }

    private val setGroupBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "set_group", SET_GROUP_HASH)
    }

    private val createTweenBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "create_tween", CREATE_TWEEN_HASH)
    }

    private val getProcessedTweensBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "get_processed_tweens", GET_PROCESSED_TWEENS_HASH)
    }

    private val createTimerBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "create_timer", CREATE_TIMER_HASH)
    }

    private val isAccessibilityEnabledBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "is_accessibility_enabled", BOOL_NOARGS_HASH)
    }

    private val isAccessibilitySupportedBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "is_accessibility_supported", BOOL_NOARGS_HASH)
    }

    private val isAutoAcceptQuitBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "is_auto_accept_quit", BOOL_NOARGS_HASH)
    }

    private val setAutoAcceptQuitBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "set_auto_accept_quit", SET_BOOL_HASH)
    }

    private val isQuitOnGoBackBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "is_quit_on_go_back", BOOL_NOARGS_HASH)
    }

    private val setQuitOnGoBackBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "set_quit_on_go_back", SET_BOOL_HASH)
    }

    private val setDebugCollisionsHintBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "set_debug_collisions_hint", SET_BOOL_HASH)
    }

    private val isDebuggingCollisionsHintBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "is_debugging_collisions_hint", BOOL_NOARGS_HASH)
    }

    private val setDebugPathsHintBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "set_debug_paths_hint", SET_BOOL_HASH)
    }

    private val isDebuggingPathsHintBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "is_debugging_paths_hint", BOOL_NOARGS_HASH)
    }

    private val setDebugNavigationHintBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "set_debug_navigation_hint", SET_BOOL_HASH)
    }

    private val isDebuggingNavigationHintBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "is_debugging_navigation_hint", BOOL_NOARGS_HASH)
    }

    private val setPhysicsInterpolationEnabledBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "set_physics_interpolation_enabled", SET_BOOL_HASH)
    }

    private val isPhysicsInterpolationEnabledBind by lazy {
        ObjectCalls.getMethodBind("SceneTree", "is_physics_interpolation_enabled", BOOL_NOARGS_HASH)
    }

    /**
     * If `true`, the scene tree is considered paused. This causes the following behavior: - 2D and 3D
     * physics will be stopped, as well as collision detection and related signals. - Depending on each
     * node's `Node.process_mode`, their `Node._process`, `Node._physics_process` and `Node._input`
     * callback methods may not called anymore.
     *
     * Generated from Godot docs: SceneTree.is_paused
     */
    @JvmStatic
    fun isPaused(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isPausedBind, sceneTreeObject())

    @JvmStatic
    fun setPaused(value: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPauseBind, sceneTreeObject(), value)
    }

    /**
     * Returns the number of nodes inside this tree.
     *
     * Generated from Godot docs: SceneTree.get_node_count
     */
    @JvmStatic
    fun getNodeCount(): Long =
        ObjectCalls.ptrcallNoArgsRetInt(getNodeCountBind, sceneTreeObject()).toLong()

    /**
     * Returns how many physics process steps have been processed, since the application started. This
     * is not a measurement of elapsed time. See also `physics_frame`. For the number of frames
     * rendered, see `Engine.get_process_frames`.
     *
     * Generated from Godot docs: SceneTree.get_frame
     */
    @JvmStatic
    fun getFrame(): Long =
        ObjectCalls.ptrcallNoArgsRetLong(getFrameBind, sceneTreeObject())

    /**
     * Quits the application at the end of the current iteration, with the given `exit_code`. By
     * convention, an exit code of `0` indicates success, whereas any other exit code indicates an
     * error. For portability reasons, it should be between `0` and `125` (inclusive). Note: On iOS
     * this method doesn't work. Instead, as recommended by the iOS Human Interface Guidelines
     * (https://developer.apple.com/library/archive/qa/qa1561/_index.html), the user is expected to
     * close apps via the Home button.
     *
     * Generated from Godot docs: SceneTree.quit
     */
    @JvmStatic
    fun quit(exitCode: Long = 0) {
        ObjectCalls.ptrcallWithIntArg(quitBind, sceneTreeObject(), exitCode.toInt())
    }

    /**
     * Changes the running scene to the one at the given `path`, after loading it into a `PackedScene`
     * and creating a new instance. Returns `OK` on success, `ERR_CANT_OPEN` if the `path` cannot be
     * loaded into a `PackedScene`, or `ERR_CANT_CREATE` if that scene cannot be instantiated. Note:
     * See `change_scene_to_node` for details on the order of operations.
     *
     * Generated from Godot docs: SceneTree.change_scene_to_file
     */
    @JvmStatic
    fun changeSceneToFile(path: String): Long =
        ObjectCalls.ptrcallWithStringArgRetLong(changeSceneToFileBind, sceneTreeObject(), path)

    /**
     * Reloads the currently active scene, replacing `current_scene` with a new instance of its
     * original `PackedScene`. Returns `OK` on success, `ERR_UNCONFIGURED` if no `current_scene` is
     * defined, `ERR_CANT_OPEN` if `current_scene` cannot be loaded into a `PackedScene`, or
     * `ERR_CANT_CREATE` if the scene cannot be instantiated.
     *
     * Generated from Godot docs: SceneTree.reload_current_scene
     */
    @JvmStatic
    fun reloadCurrentScene(): Long =
        ObjectCalls.ptrcallNoArgsRetLong(reloadCurrentSceneBind, sceneTreeObject())

    /**
     * If a current scene is loaded, calling this method will unload it.
     *
     * Generated from Godot docs: SceneTree.unload_current_scene
     */
    @JvmStatic
    fun unloadCurrentScene() {
        ObjectCalls.ptrcallNoArgs(unloadCurrentSceneBind, sceneTreeObject())
    }

    /**
     * Sets a custom `MultiplayerAPI` with the given `root_path` (controlling also the relative
     * subpaths), or override the default one if `root_path` is empty. Note: No `MultiplayerAPI` must
     * be configured for the subpath containing `root_path`, nested custom multiplayers are not
     * allowed. I.e. if one is configured for `"/root/Foo"` setting one for `"/root/Foo/Bar"` will
     * cause an error. Note: `set_multiplayer` should be called before the child nodes are ready at the
     * given `root_path`. If multiplayer nodes like `MultiplayerSpawner` or `MultiplayerSynchronizer`
     * are added to the tree before the custom multiplayer API is set, they will not work.
     *
     * Generated from Godot docs: SceneTree.set_multiplayer
     */
    @JvmStatic
    fun setMultiplayer(multiplayerApiObject: MemorySegment, rootPath: String = "") {
        ObjectCalls.ptrcallWithObjectAndNodePathArg(
            setMultiplayerBind,
            sceneTreeObject(),
            multiplayerApiObject,
            rootPath,
        )
    }

    /**
     * Searches for the `MultiplayerAPI` configured for the given path, if one does not exist it
     * searches the parent paths until one is found. If the path is empty, or none is found, the
     * default one is returned. See `set_multiplayer`.
     *
     * Generated from Godot docs: SceneTree.get_multiplayer
     */
    @JvmStatic
    fun getMultiplayer(forPath: String = ""): MemorySegment =
        ObjectCalls.ptrcallWithNodePathArgRetObject(getMultiplayerBind, sceneTreeObject(), forPath)

    /**
     * If `true` (default value), enables automatic polling of the `MultiplayerAPI` for this SceneTree
     * during `process_frame`. If `false`, you need to manually call `MultiplayerAPI.poll` to process
     * network packets and deliver RPCs. This allows running RPCs in a different loop (e.g. physics,
     * thread, specific time step) and for manual `Mutex` protection when accessing the
     * `MultiplayerAPI` from threads.
     *
     * Generated from Godot docs: SceneTree.is_multiplayer_poll_enabled
     */
    @JvmStatic
    fun isMultiplayerPollEnabled(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isMultiplayerPollEnabledBind, sceneTreeObject())

    /**
     * If `true` (default value), enables automatic polling of the `MultiplayerAPI` for this SceneTree
     * during `process_frame`. If `false`, you need to manually call `MultiplayerAPI.poll` to process
     * network packets and deliver RPCs. This allows running RPCs in a different loop (e.g. physics,
     * thread, specific time step) and for manual `Mutex` protection when accessing the
     * `MultiplayerAPI` from threads.
     *
     * Generated from Godot docs: SceneTree.set_multiplayer_poll_enabled
     */
    @JvmStatic
    fun setMultiplayerPollEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMultiplayerPollEnabledBind, sceneTreeObject(), enabled)
    }

    /**
     * Returns `true` if a node added to the given group `name` exists in the tree.
     *
     * Generated from Godot docs: SceneTree.has_group
     */
    @JvmStatic
    fun hasGroup(name: String): Boolean =
        ObjectCalls.ptrcallWithStringNameArgRetBool(hasGroupBind, sceneTreeObject(), name)

    /**
     * Returns the number of nodes assigned to the given group.
     *
     * Generated from Godot docs: SceneTree.get_node_count_in_group
     */
    @JvmStatic
    fun getNodeCountInGroup(name: String): Long =
        ObjectCalls.ptrcallWithStringNameArgRetInt(getNodeCountInGroupBind, sceneTreeObject(), name).toLong()

    /**
     * The tree's root `Window`. This is top-most `Node` of the scene tree, and is always present. An
     * absolute `NodePath` always starts from this node. Children of the root node may include the
     * loaded `current_scene`, as well as any AutoLoad
     * ($DOCS_URL/tutorials/scripting/singletons_autoload.html) configured in the Project Settings.
     * Warning: Do not delete this node. This will result in unstable behavior, followed by a crash.
     *
     * Generated from Godot docs: SceneTree.get_root
     */
    @JvmStatic
    fun getRoot(): MemorySegment =
        ObjectCalls.ptrcallNoArgsRetObject(getRootBind, sceneTreeObject())

    /**
     * The root node of the currently loaded main scene, usually as a direct child of `root`. See also
     * `change_scene_to_file`, `change_scene_to_packed`, and `reload_current_scene`. Warning: Setting
     * this property directly may not work as expected, as it does not add or remove any nodes from
     * this tree.
     *
     * Generated from Godot docs: SceneTree.get_current_scene
     */
    @JvmStatic
    fun getCurrentScene(): MemorySegment =
        ObjectCalls.ptrcallNoArgsRetObject(getCurrentSceneBind, sceneTreeObject())

    /**
     * The root node of the currently loaded main scene, usually as a direct child of `root`. See also
     * `change_scene_to_file`, `change_scene_to_packed`, and `reload_current_scene`. Warning: Setting
     * this property directly may not work as expected, as it does not add or remove any nodes from
     * this tree.
     *
     * Generated from Godot docs: SceneTree.set_current_scene
     */
    @JvmStatic
    fun setCurrentScene(nodeObject: MemorySegment) {
        ObjectCalls.ptrcallWithObjectArgs(setCurrentSceneBind, sceneTreeObject(), listOf(nodeObject))
    }

    /**
     * Returns the first `Node` found inside the tree, that has been added to the given `group`, in
     * scene hierarchy order. Returns `null` if no match is found. See also `get_nodes_in_group`.
     *
     * Generated from Godot docs: SceneTree.get_first_node_in_group
     */
    @JvmStatic
    fun getFirstNodeInGroup(name: String): MemorySegment =
        ObjectCalls.ptrcallWithStringNameArgRetObject(getFirstNodeInGroupBind, sceneTreeObject(), name)

    /**
     * Returns an `Array` containing all nodes inside this tree, that have been added to the given
     * `group`, in scene hierarchy order.
     *
     * Generated from Godot docs: SceneTree.get_nodes_in_group
     */
    @JvmStatic
    fun getNodesInGroup(name: String): List<Node> =
        ObjectCalls.ptrcallWithStringNameArgRetObjectList(getNodesInGroupBind, sceneTreeObject(), name)
            .map { Node(it.handle) }

    /**
     * Queues the given `obj` to be deleted, calling its `Object.free` at the end of the current frame.
     * This method is similar to `Node.queue_free`.
     *
     * Generated from Godot docs: SceneTree.queue_delete
     */
    @JvmStatic
    fun queueDelete(nodeObject: MemorySegment) {
        ObjectCalls.ptrcallWithObjectArgs(queueDeleteBind, sceneTreeObject(), listOf(nodeObject))
    }

    /**
     * Changes the running scene to a new instance of the given `PackedScene` (which must be valid).
     * Returns `OK` on success, `ERR_CANT_CREATE` if the scene cannot be instantiated, or
     * `ERR_INVALID_PARAMETER` if the scene is invalid. Note: See `change_scene_to_node` for details on
     * the order of operations.
     *
     * Generated from Godot docs: SceneTree.change_scene_to_packed
     */
    @JvmStatic
    fun changeSceneToPacked(packedSceneObject: MemorySegment): Long =
        ObjectCalls.ptrcallWithObjectArgsRetLong(changeSceneToPackedBind, sceneTreeObject(), listOf(packedSceneObject))

    /**
     * Changes the running scene to the provided `Node`. Useful when you want to set up the new scene
     * before changing. Returns `OK` on success, `ERR_INVALID_PARAMETER` if the `node` is `null`, or
     * `ERR_UNCONFIGURED` if the `node` is already inside the scene tree. Note: Operations happen in
     * the following order when `change_scene_to_node` is called: 1. The current scene node is
     * immediately removed from the tree. From that point, `Node.get_tree` called on the current
     * (outgoing) scene will return `null`. `current_scene` will be `null` too, because the new scene
     * is not available yet. 2. At the end of the frame, the formerly current scene, already removed
     * from the tree, will be deleted (freed from memory) and then the new scene node will be added to
     * the tree. `Node.get_tree` and `current_scene` will be back to working as usual. This ensures
     * that both scenes aren't running at the same time, while still freeing the previous scene in a
     * safe way similar to `Node.queue_free`. If you want to reliably access the new scene, await the
     * `scene_changed` signal. Warning: After using this method, the `SceneTree` will take ownership of
     * the node and will free it automatically when changing scene again. Any references you had to
     * that node will become invalid.
     *
     * Generated from Godot docs: SceneTree.change_scene_to_node
     */
    @JvmStatic
    fun changeSceneToNode(nodeObject: MemorySegment): Long =
        ObjectCalls.ptrcallWithObjectArgsRetLong(changeSceneToNodeBind, sceneTreeObject(), listOf(nodeObject))

    /**
     * The root of the scene currently being edited in the editor. This is usually a direct child of
     * `root`. Note: This property does nothing in release builds.
     *
     * Generated from Godot docs: SceneTree.get_edited_scene_root
     */
    @JvmStatic
    fun getEditedSceneRoot(): MemorySegment =
        ObjectCalls.ptrcallNoArgsRetObject(getEditedSceneRootBind, sceneTreeObject())

    /**
     * The root of the scene currently being edited in the editor. This is usually a direct child of
     * `root`. Note: This property does nothing in release builds.
     *
     * Generated from Godot docs: SceneTree.set_edited_scene_root
     */
    @JvmStatic
    fun setEditedSceneRoot(nodeObject: MemorySegment) {
        ObjectCalls.ptrcallWithObjectArgs(setEditedSceneRootBind, sceneTreeObject(), listOf(nodeObject))
    }

    /**
     * Calls `method` on each node inside this tree added to the given `group`. You can pass arguments
     * to `method` by specifying them at the end of this method call. Nodes that cannot call `method`
     * (either because the method doesn't exist or the arguments do not match) are ignored. See also
     * `set_group` and `notify_group`. Note: This method acts immediately on all selected nodes at
     * once, which may cause stuttering in some performance-intensive situations. Note: In C#, `method`
     * must be in snake_case when referring to built-in Godot methods. Prefer using the names exposed
     * in the `MethodName` class to avoid allocating a new `StringName` on each call.
     *
     * Generated from Godot docs: SceneTree.call_group
     */
    @JvmStatic
    fun callGroup(groupName: String, methodName: String, vararg args: Any?) {
        GodotObject(sceneTreeObject()).call("call_group", groupName, methodName, *args)
    }

    /**
     * Calls `Object.notification` with the given `notification` to all nodes inside this tree added to
     * the `group`. See also Godot notifications
     * ($DOCS_URL/tutorials/best_practices/godot_notifications.html) and `call_group` and `set_group`.
     * Note: This method acts immediately on all selected nodes at once, which may cause stuttering in
     * some performance-intensive situations.
     *
     * Generated from Godot docs: SceneTree.notify_group
     */
    @JvmStatic
    fun notifyGroup(groupName: String, notification: Long) {
        ObjectCalls.ptrcallWithStringNameAndIntArg(notifyGroupBind, sceneTreeObject(), groupName, notification.toInt())
    }

    /**
     * Calls the given `method` on each node inside this tree added to the given `group`. Use `flags`
     * to customize this method's behavior (see `GroupCallFlags`). Additional arguments for `method`
     * can be passed at the end of this method. Nodes that cannot call `method` (either because the
     * method doesn't exist or the arguments do not match) are ignored.
     *
     * Generated from Godot docs: SceneTree.call_group_flags
     */
    @JvmStatic
    fun callGroupFlags(flags: Long, groupName: String, methodName: String, vararg args: Any?) {
        GodotObject(sceneTreeObject()).call("call_group_flags", flags, groupName, methodName, *args)
    }

    /**
     * Calls `Object.notification` with the given `notification` to all nodes inside this tree added to
     * the `group`. Use `call_flags` to customize this method's behavior (see `GroupCallFlags`).
     *
     * Generated from Godot docs: SceneTree.notify_group_flags
     */
    @JvmStatic
    fun notifyGroupFlags(flags: Long, groupName: String, notification: Long) {
        ObjectCalls.ptrcallWithUInt32StringNameAndIntArgs(
            notifyGroupFlagsBind,
            sceneTreeObject(),
            flags,
            groupName,
            notification.toInt(),
        )
    }

    /**
     * Sets the given `property` to `value` on all nodes inside this tree added to the given `group`.
     * Nodes that do not have the `property` are ignored. Use `call_flags` to customize this method's
     * behavior (see `GroupCallFlags`). Note: In C#, `property` must be in snake_case when referring to
     * built-in Godot properties. Prefer using the names exposed in the `PropertyName` class to avoid
     * allocating a new `StringName` on each call.
     *
     * Generated from Godot docs: SceneTree.set_group_flags
     */
    @JvmStatic
    fun setGroupFlags(flags: Long, groupName: String, property: String, value: Any?) {
        ObjectCalls.ptrcallWithUInt32StringNameStringVariantArgs(
            setGroupFlagsBind,
            sceneTreeObject(),
            flags,
            groupName,
            property,
            value,
        )
    }

    /**
     * Sets the given `property` to `value` on all nodes inside this tree added to the given `group`.
     * Nodes that do not have the `property` are ignored. See also `call_group` and `notify_group`.
     * Note: This method acts immediately on all selected nodes at once, which may cause stuttering in
     * some performance-intensive situations. Note: In C#, `property` must be in snake_case when
     * referring to built-in Godot properties. Prefer using the names exposed in the `PropertyName`
     * class to avoid allocating a new `StringName` on each call.
     *
     * Generated from Godot docs: SceneTree.set_group
     */
    @JvmStatic
    fun setGroup(groupName: String, property: String, value: Any?) {
        ObjectCalls.ptrcallWithStringNameStringAndVariantArg(setGroupBind, sceneTreeObject(), groupName, property, value)
    }

    @JvmStatic
    fun createTweenHandle(): MemorySegment =
        ObjectCalls.ptrcallNoArgsRetObject(createTweenBind, sceneTreeObject())

    /**
     * Creates and returns a new `Tween` processed in this tree. The Tween will start automatically on
     * the next process frame or physics frame (depending on its `Tween.TweenProcessMode`). Note: A
     * `Tween` created using this method is not bound to any `Node`. It may keep working until there is
     * nothing left to animate. If you want the `Tween` to be automatically killed when the `Node` is
     * freed, use `Node.create_tween` or `Tween.bind_node`.
     *
     * Generated from Godot docs: SceneTree.create_tween
     */
    @JvmStatic
    fun createTween(): Tween? =
        Tween.wrap(createTweenHandle())

    /**
     * Returns an `Array` of currently existing `Tween`s in the tree, including paused tweens.
     *
     * Generated from Godot docs: SceneTree.get_processed_tweens
     */
    @JvmStatic
    fun getProcessedTweens(): List<Tween> =
        ObjectCalls.ptrcallNoArgsRetTypedObjectList(getProcessedTweensBind, sceneTreeObject(), Tween::wrap)

    @JvmStatic
    fun createTimerHandle(
        timeSec: Double,
        processAlways: Boolean = true,
        processInPhysics: Boolean = false,
        ignoreTimeScale: Boolean = false,
    ): MemorySegment = ObjectCalls.ptrcallWithDoubleAndThreeBoolArgsRetObject(
        createTimerBind,
        sceneTreeObject(),
        timeSec,
        processAlways,
        processInPhysics,
        ignoreTimeScale,
    )

    /**
     * Returns a new `SceneTreeTimer`. After `time_sec` in seconds have passed, the timer will emit
     * `SceneTreeTimer.timeout` and will be automatically freed. If `process_always` is `false`, the
     * timer will be paused when setting `SceneTree.paused` to `true`. If `process_in_physics` is
     * `true`, the timer will update at the end of the physics frame, instead of the process frame. If
     * `ignore_time_scale` is `true`, the timer will ignore `Engine.time_scale` and update with the
     * real, elapsed time. This method is commonly used to create a one-shot delay timer, as in the
     * following example:
     *
     * Generated from Godot docs: SceneTree.create_timer
     */
    @JvmStatic
    fun createTimer(
        timeSec: Double,
        processAlways: Boolean = true,
        processInPhysics: Boolean = false,
        ignoreTimeScale: Boolean = false,
    ): SceneTreeTimer? = SceneTreeTimer.wrap(
        createTimerHandle(timeSec, processAlways, processInPhysics, ignoreTimeScale),
    )

    @JvmStatic
    suspend fun delaySeconds(
        timeSec: Double,
        processAlways: Boolean = true,
        processInPhysics: Boolean = false,
        ignoreTimeScale: Boolean = false,
    ) {
        if (timeSec <= 0.0) return

        var elapsedSeconds = 0.0
        var lastUsec = Time.getTicksUsec()

        while (elapsedSeconds < timeSec) {
            MainThread.awaitNextFrame()

            val nowUsec = Time.getTicksUsec()
            var frameSeconds = (nowUsec - lastUsec).coerceAtLeast(0L) / 1_000_000.0
            lastUsec = nowUsec

            if (!processAlways && isPaused()) {
                frameSeconds = 0.0
            } else if (!ignoreTimeScale) {
                frameSeconds *= Engine.getTimeScale().coerceAtLeast(0.0)
            }

            elapsedSeconds += frameSeconds
        }
    }

    /**
     * Returns `true` if accessibility features are enabled, and accessibility information updates are
     * actively processed.
     *
     * Generated from Godot docs: SceneTree.is_accessibility_enabled
     */
    @JvmStatic
    fun isAccessibilityEnabled(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isAccessibilityEnabledBind, sceneTreeObject())

    /**
     * Returns `true` if accessibility features are supported by the OS and enabled in project
     * settings.
     *
     * Generated from Godot docs: SceneTree.is_accessibility_supported
     */
    @JvmStatic
    fun isAccessibilitySupported(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isAccessibilitySupportedBind, sceneTreeObject())

    /**
     * If `true`, the application automatically accepts quitting requests. For mobile platforms, see
     * `quit_on_go_back`.
     *
     * Generated from Godot docs: SceneTree.is_auto_accept_quit
     */
    @JvmStatic
    fun isAutoAcceptQuit(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isAutoAcceptQuitBind, sceneTreeObject())

    /**
     * If `true`, the application automatically accepts quitting requests. For mobile platforms, see
     * `quit_on_go_back`.
     *
     * Generated from Godot docs: SceneTree.set_auto_accept_quit
     */
    @JvmStatic
    fun setAutoAcceptQuit(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutoAcceptQuitBind, sceneTreeObject(), enabled)
    }

    /**
     * If `true`, the application quits automatically when navigating back (e.g. using the system
     * "Back" button on Android). To handle 'Go Back' button when this option is disabled, use
     * `DisplayServer.WINDOW_EVENT_GO_BACK_REQUEST`.
     *
     * Generated from Godot docs: SceneTree.is_quit_on_go_back
     */
    @JvmStatic
    fun isQuitOnGoBack(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isQuitOnGoBackBind, sceneTreeObject())

    /**
     * If `true`, the application quits automatically when navigating back (e.g. using the system
     * "Back" button on Android). To handle 'Go Back' button when this option is disabled, use
     * `DisplayServer.WINDOW_EVENT_GO_BACK_REQUEST`.
     *
     * Generated from Godot docs: SceneTree.set_quit_on_go_back
     */
    @JvmStatic
    fun setQuitOnGoBack(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setQuitOnGoBackBind, sceneTreeObject(), enabled)
    }

    /**
     * If `true`, collision shapes will be visible when running the game from the editor for debugging
     * purposes. Note: This property is not designed to be changed at run-time. Changing the value of
     * `debug_collisions_hint` while the project is running will not have the desired effect.
     *
     * Generated from Godot docs: SceneTree.set_debug_collisions_hint
     */
    @JvmStatic
    fun setDebugCollisionsHint(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDebugCollisionsHintBind, sceneTreeObject(), enabled)
    }

    /**
     * If `true`, collision shapes will be visible when running the game from the editor for debugging
     * purposes. Note: This property is not designed to be changed at run-time. Changing the value of
     * `debug_collisions_hint` while the project is running will not have the desired effect.
     *
     * Generated from Godot docs: SceneTree.is_debugging_collisions_hint
     */
    @JvmStatic
    fun isDebuggingCollisionsHint(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isDebuggingCollisionsHintBind, sceneTreeObject())

    /**
     * If `true`, curves from `Path2D` and `Path3D` nodes will be visible when running the game from
     * the editor for debugging purposes. Note: This property is not designed to be changed at
     * run-time. Changing the value of `debug_paths_hint` while the project is running will not have
     * the desired effect.
     *
     * Generated from Godot docs: SceneTree.set_debug_paths_hint
     */
    @JvmStatic
    fun setDebugPathsHint(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDebugPathsHintBind, sceneTreeObject(), enabled)
    }

    /**
     * If `true`, curves from `Path2D` and `Path3D` nodes will be visible when running the game from
     * the editor for debugging purposes. Note: This property is not designed to be changed at
     * run-time. Changing the value of `debug_paths_hint` while the project is running will not have
     * the desired effect.
     *
     * Generated from Godot docs: SceneTree.is_debugging_paths_hint
     */
    @JvmStatic
    fun isDebuggingPathsHint(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isDebuggingPathsHintBind, sceneTreeObject())

    /**
     * If `true`, navigation polygons will be visible when running the game from the editor for
     * debugging purposes. Note: This property is not designed to be changed at run-time. Changing the
     * value of `debug_navigation_hint` while the project is running will not have the desired effect.
     *
     * Generated from Godot docs: SceneTree.set_debug_navigation_hint
     */
    @JvmStatic
    fun setDebugNavigationHint(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDebugNavigationHintBind, sceneTreeObject(), enabled)
    }

    /**
     * If `true`, navigation polygons will be visible when running the game from the editor for
     * debugging purposes. Note: This property is not designed to be changed at run-time. Changing the
     * value of `debug_navigation_hint` while the project is running will not have the desired effect.
     *
     * Generated from Godot docs: SceneTree.is_debugging_navigation_hint
     */
    @JvmStatic
    fun isDebuggingNavigationHint(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isDebuggingNavigationHintBind, sceneTreeObject())

    /**
     * If `true`, the renderer will interpolate the transforms of objects (both physics and
     * non-physics) between the last two transforms, so that smooth motion is seen even when physics
     * ticks do not coincide with rendered frames. The default value of this property is controlled by
     * `ProjectSettings.physics/common/physics_interpolation`. Note: Although this is a global setting,
     * finer control of individual branches of the `SceneTree` is possible using
     * `Node.physics_interpolation_mode`.
     *
     * Generated from Godot docs: SceneTree.set_physics_interpolation_enabled
     */
    @JvmStatic
    fun setPhysicsInterpolationEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPhysicsInterpolationEnabledBind, sceneTreeObject(), enabled)
    }

    /**
     * If `true`, the renderer will interpolate the transforms of objects (both physics and
     * non-physics) between the last two transforms, so that smooth motion is seen even when physics
     * ticks do not coincide with rendered frames. The default value of this property is controlled by
     * `ProjectSettings.physics/common/physics_interpolation`. Note: Although this is a global setting,
     * finer control of individual branches of the `SceneTree` is possible using
     * `Node.physics_interpolation_mode`.
     *
     * Generated from Godot docs: SceneTree.is_physics_interpolation_enabled
     */
    @JvmStatic
    fun isPhysicsInterpolationEnabled(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isPhysicsInterpolationEnabledBind, sceneTreeObject())

}

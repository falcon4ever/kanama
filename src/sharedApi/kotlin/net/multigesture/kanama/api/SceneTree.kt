package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath

/**
 * Manages the game loop via a hierarchy of nodes.
 *
 * Generated from Godot docs: SceneTree
 */
class SceneTree(handle: GodotHandle) : MainLoop(handle) {
    var autoAcceptQuit: Boolean
        @JvmName("autoAcceptQuitProperty")
        get() = isAutoAcceptQuit()
        @JvmName("setAutoAcceptQuitProperty")
        set(value) = setAutoAcceptQuit(value)

    var quitOnGoBack: Boolean
        @JvmName("quitOnGoBackProperty")
        get() = isQuitOnGoBack()
        @JvmName("setQuitOnGoBackProperty")
        set(value) = setQuitOnGoBack(value)

    var debugCollisionsHint: Boolean
        @JvmName("debugCollisionsHintProperty")
        get() = isDebuggingCollisionsHint()
        @JvmName("setDebugCollisionsHintProperty")
        set(value) = setDebugCollisionsHint(value)

    var debugPathsHint: Boolean
        @JvmName("debugPathsHintProperty")
        get() = isDebuggingPathsHint()
        @JvmName("setDebugPathsHintProperty")
        set(value) = setDebugPathsHint(value)

    var debugNavigationHint: Boolean
        @JvmName("debugNavigationHintProperty")
        get() = isDebuggingNavigationHint()
        @JvmName("setDebugNavigationHintProperty")
        set(value) = setDebugNavigationHint(value)

    var paused: Boolean
        @JvmName("pausedProperty")
        get() = isPaused()
        @JvmName("setPausedProperty")
        set(value) = setPause(value)

    val editedSceneRoot: Node?
        @JvmName("editedSceneRootProperty")
        get() = getEditedSceneRoot()

    val currentScene: Node?
        @JvmName("currentSceneProperty")
        get() = getCurrentScene()

    val root: Window?
        @JvmName("rootProperty")
        get() = getRoot()

    var multiplayerPoll: Boolean
        @JvmName("multiplayerPollProperty")
        get() = isMultiplayerPollEnabled()
        @JvmName("setMultiplayerPollProperty")
        set(value) = setMultiplayerPollEnabled(value)

    var physicsInterpolation: Boolean
        @JvmName("physicsInterpolationProperty")
        get() = isPhysicsInterpolationEnabled()
        @JvmName("setPhysicsInterpolationProperty")
        set(value) = setPhysicsInterpolationEnabled(value)

    /**
     * The tree's root `Window`. This is top-most `Node` of the scene tree, and is always present. An
     * absolute `NodePath` always starts from this node. Children of the root node may include the
     * loaded `current_scene`, as well as any AutoLoad
     * ($DOCS_URL/tutorials/scripting/singletons_autoload.html) configured in the Project Settings.
     * Warning: Do not delete this node. This will result in unstable behavior, followed by a crash.
     *
     * Generated from Godot docs: SceneTree.get_root
     */
    fun getRoot(): Window? {
        return Window.wrap(ObjectCalls.ptrcallNoArgsRetObject(getRootBind, segment))
    }

    /**
     * Returns `true` if a node added to the given group `name` exists in the tree.
     *
     * Generated from Godot docs: SceneTree.has_group
     */
    fun hasGroup(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasGroupBind, segment, name)
    }

    /**
     * Returns `true` if accessibility features are enabled, and accessibility information updates are
     * actively processed.
     *
     * Generated from Godot docs: SceneTree.is_accessibility_enabled
     */
    fun isAccessibilityEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAccessibilityEnabledBind, segment)
    }

    /**
     * Returns `true` if accessibility features are supported by the OS and enabled in project
     * settings.
     *
     * Generated from Godot docs: SceneTree.is_accessibility_supported
     */
    fun isAccessibilitySupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAccessibilitySupportedBind, segment)
    }

    /**
     * If `true`, the application automatically accepts quitting requests. For mobile platforms, see
     * `quit_on_go_back`.
     *
     * Generated from Godot docs: SceneTree.is_auto_accept_quit
     */
    fun isAutoAcceptQuit(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAutoAcceptQuitBind, segment)
    }

    /**
     * If `true`, the application automatically accepts quitting requests. For mobile platforms, see
     * `quit_on_go_back`.
     *
     * Generated from Godot docs: SceneTree.set_auto_accept_quit
     */
    fun setAutoAcceptQuit(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutoAcceptQuitBind, segment, enabled)
    }

    /**
     * If `true`, the application quits automatically when navigating back (e.g. using the system
     * "Back" button on Android). To handle 'Go Back' button when this option is disabled, use
     * `DisplayServer.WINDOW_EVENT_GO_BACK_REQUEST`.
     *
     * Generated from Godot docs: SceneTree.is_quit_on_go_back
     */
    fun isQuitOnGoBack(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isQuitOnGoBackBind, segment)
    }

    /**
     * If `true`, the application quits automatically when navigating back (e.g. using the system
     * "Back" button on Android). To handle 'Go Back' button when this option is disabled, use
     * `DisplayServer.WINDOW_EVENT_GO_BACK_REQUEST`.
     *
     * Generated from Godot docs: SceneTree.set_quit_on_go_back
     */
    fun setQuitOnGoBack(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setQuitOnGoBackBind, segment, enabled)
    }

    /**
     * If `true`, collision shapes will be visible when running the game from the editor for debugging
     * purposes. Note: This property is not designed to be changed at run-time. Changing the value of
     * `debug_collisions_hint` while the project is running will not have the desired effect.
     *
     * Generated from Godot docs: SceneTree.set_debug_collisions_hint
     */
    fun setDebugCollisionsHint(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDebugCollisionsHintBind, segment, enable)
    }

    /**
     * If `true`, collision shapes will be visible when running the game from the editor for debugging
     * purposes. Note: This property is not designed to be changed at run-time. Changing the value of
     * `debug_collisions_hint` while the project is running will not have the desired effect.
     *
     * Generated from Godot docs: SceneTree.is_debugging_collisions_hint
     */
    fun isDebuggingCollisionsHint(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDebuggingCollisionsHintBind, segment)
    }

    /**
     * If `true`, curves from `Path2D` and `Path3D` nodes will be visible when running the game from
     * the editor for debugging purposes. Note: This property is not designed to be changed at
     * run-time. Changing the value of `debug_paths_hint` while the project is running will not have
     * the desired effect.
     *
     * Generated from Godot docs: SceneTree.set_debug_paths_hint
     */
    fun setDebugPathsHint(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDebugPathsHintBind, segment, enable)
    }

    /**
     * If `true`, curves from `Path2D` and `Path3D` nodes will be visible when running the game from
     * the editor for debugging purposes. Note: This property is not designed to be changed at
     * run-time. Changing the value of `debug_paths_hint` while the project is running will not have
     * the desired effect.
     *
     * Generated from Godot docs: SceneTree.is_debugging_paths_hint
     */
    fun isDebuggingPathsHint(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDebuggingPathsHintBind, segment)
    }

    /**
     * If `true`, navigation polygons will be visible when running the game from the editor for
     * debugging purposes. Note: This property is not designed to be changed at run-time. Changing the
     * value of `debug_navigation_hint` while the project is running will not have the desired effect.
     *
     * Generated from Godot docs: SceneTree.set_debug_navigation_hint
     */
    fun setDebugNavigationHint(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDebugNavigationHintBind, segment, enable)
    }

    /**
     * If `true`, navigation polygons will be visible when running the game from the editor for
     * debugging purposes. Note: This property is not designed to be changed at run-time. Changing the
     * value of `debug_navigation_hint` while the project is running will not have the desired effect.
     *
     * Generated from Godot docs: SceneTree.is_debugging_navigation_hint
     */
    fun isDebuggingNavigationHint(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDebuggingNavigationHintBind, segment)
    }

    /**
     * The root of the scene currently being edited in the editor. This is usually a direct child of
     * `root`. Note: This property does nothing in release builds.
     *
     * Generated from Godot docs: SceneTree.set_edited_scene_root
     */
    fun setEditedSceneRoot(scene: Node) {
        ObjectCalls.ptrcallWithObjectArgs(setEditedSceneRootBind, segment, listOf(scene.segment))
    }

    /**
     * The root of the scene currently being edited in the editor. This is usually a direct child of
     * `root`. Note: This property does nothing in release builds.
     *
     * Generated from Godot docs: SceneTree.get_edited_scene_root
     */
    fun getEditedSceneRoot(): Node? {
        return Node.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEditedSceneRootBind, segment))
    }

    /**
     * If `true`, the scene tree is considered paused. This causes the following behavior: - 2D and 3D
     * physics will be stopped, as well as collision detection and related signals. - Depending on each
     * node's `Node.process_mode`, their `Node._process`, `Node._physics_process` and `Node._input`
     * callback methods may not called anymore.
     *
     * Generated from Godot docs: SceneTree.set_pause
     */
    fun setPause(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPauseBind, segment, enable)
    }

    /**
     * If `true`, the scene tree is considered paused. This causes the following behavior: - 2D and 3D
     * physics will be stopped, as well as collision detection and related signals. - Depending on each
     * node's `Node.process_mode`, their `Node._process`, `Node._physics_process` and `Node._input`
     * callback methods may not called anymore.
     *
     * Generated from Godot docs: SceneTree.is_paused
     */
    fun isPaused(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPausedBind, segment)
    }

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
    fun createTimer(timeSec: Double, processAlways: Boolean = true, processInPhysics: Boolean = false, ignoreTimeScale: Boolean = false): SceneTreeTimer? {
        return SceneTreeTimer.wrap(ObjectCalls.ptrcallWithDoubleAndThreeBoolArgsRetObject(createTimerBind, segment, timeSec, processAlways, processInPhysics, ignoreTimeScale))
    }

    /**
     * Returns the number of nodes inside this tree.
     *
     * Generated from Godot docs: SceneTree.get_node_count
     */
    fun getNodeCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getNodeCountBind, segment)
    }

    /**
     * Returns how many physics process steps have been processed, since the application started. This
     * is not a measurement of elapsed time. See also `physics_frame`. For the number of frames
     * rendered, see `Engine.get_process_frames`.
     *
     * Generated from Godot docs: SceneTree.get_frame
     */
    fun getFrame(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFrameBind, segment)
    }

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
    fun quit(exitCode: Int = 0) {
        ObjectCalls.ptrcallWithIntArg(quitBind, segment, exitCode)
    }

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
    fun setPhysicsInterpolationEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPhysicsInterpolationEnabledBind, segment, enabled)
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
    fun isPhysicsInterpolationEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPhysicsInterpolationEnabledBind, segment)
    }

    /**
     * Queues the given `obj` to be deleted, calling its `Object.free` at the end of the current frame.
     * This method is similar to `Node.queue_free`.
     *
     * Generated from Godot docs: SceneTree.queue_delete
     */
    fun queueDelete(obj: GodotObject) {
        ObjectCalls.ptrcallWithObjectArgs(queueDeleteBind, segment, listOf(obj.segment))
    }

    /**
     * Calls the given `method` on each node inside this tree added to the given `group`. Use `flags`
     * to customize this method's behavior (see `GroupCallFlags`). Additional arguments for `method`
     * can be passed at the end of this method. Nodes that cannot call `method` (either because the
     * method doesn't exist or the arguments do not match) are ignored.
     *
     * Generated from Godot docs: SceneTree.call_group_flags
     */
    fun callGroupFlags(flags: Long, group: String, method: String, vararg extraArgs: Any?) {
        ObjectCalls.callWithVariantArgs(callGroupFlagsBind, segment, listOf(flags, group, method, *extraArgs))
    }

    /**
     * Calls `Object.notification` with the given `notification` to all nodes inside this tree added to
     * the `group`. Use `call_flags` to customize this method's behavior (see `GroupCallFlags`).
     *
     * Generated from Godot docs: SceneTree.notify_group_flags
     */
    fun notifyGroupFlags(callFlags: Long, group: String, notification: Int) {
        ObjectCalls.ptrcallWithUInt32StringNameAndIntArgs(notifyGroupFlagsBind, segment, callFlags, group, notification)
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
    fun setGroupFlags(callFlags: Long, group: String, property: String, value: Any?) {
        ObjectCalls.ptrcallWithUInt32StringNameStringVariantArgs(setGroupFlagsBind, segment, callFlags, group, property, value)
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
    fun callGroup(group: String, method: String, vararg extraArgs: Any?) {
        ObjectCalls.callWithVariantArgs(callGroupBind, segment, listOf(group, method, *extraArgs))
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
    fun notifyGroup(group: String, notification: Int) {
        ObjectCalls.ptrcallWithStringNameAndIntArg(notifyGroupBind, segment, group, notification)
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
    fun setGroup(group: String, property: String, value: Any?) {
        ObjectCalls.ptrcallWithStringNameStringAndVariantArg(setGroupBind, segment, group, property, value)
    }

    /**
     * Returns an `Array` containing all nodes inside this tree, that have been added to the given
     * `group`, in scene hierarchy order.
     *
     * Generated from Godot docs: SceneTree.get_nodes_in_group
     */
    fun getNodesInGroup(group: String): List<Node> {
        return ObjectCalls.ptrcallWithStringNameArgRetTypedObjectList(getNodesInGroupBind, segment, group, Node::wrap)
    }

    /**
     * Returns the first `Node` found inside the tree, that has been added to the given `group`, in
     * scene hierarchy order. Returns `null` if no match is found. See also `get_nodes_in_group`.
     *
     * Generated from Godot docs: SceneTree.get_first_node_in_group
     */
    fun getFirstNodeInGroup(group: String): Node? {
        return Node.wrap(ObjectCalls.ptrcallWithStringNameArgRetObject(getFirstNodeInGroupBind, segment, group))
    }

    /**
     * Returns the number of nodes assigned to the given group.
     *
     * Generated from Godot docs: SceneTree.get_node_count_in_group
     */
    fun getNodeCountInGroup(group: String): Int {
        return ObjectCalls.ptrcallWithStringNameArgRetInt(getNodeCountInGroupBind, segment, group)
    }

    /**
     * The root node of the currently loaded main scene, usually as a direct child of `root`. See also
     * `change_scene_to_file`, `change_scene_to_packed`, and `reload_current_scene`. Warning: Setting
     * this property directly may not work as expected, as it does not add or remove any nodes from
     * this tree.
     *
     * Generated from Godot docs: SceneTree.set_current_scene
     */
    fun setCurrentScene(childNode: Node) {
        ObjectCalls.ptrcallWithObjectArgs(setCurrentSceneBind, segment, listOf(childNode.segment))
    }

    /**
     * The root node of the currently loaded main scene, usually as a direct child of `root`. See also
     * `change_scene_to_file`, `change_scene_to_packed`, and `reload_current_scene`. Warning: Setting
     * this property directly may not work as expected, as it does not add or remove any nodes from
     * this tree.
     *
     * Generated from Godot docs: SceneTree.get_current_scene
     */
    fun getCurrentScene(): Node? {
        return Node.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCurrentSceneBind, segment))
    }

    /**
     * Changes the running scene to the one at the given `path`, after loading it into a `PackedScene`
     * and creating a new instance. Returns `OK` on success, `ERR_CANT_OPEN` if the `path` cannot be
     * loaded into a `PackedScene`, or `ERR_CANT_CREATE` if that scene cannot be instantiated. Note:
     * See `change_scene_to_node` for details on the order of operations.
     *
     * Generated from Godot docs: SceneTree.change_scene_to_file
     */
    fun changeSceneToFile(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(changeSceneToFileBind, segment, path)
    }

    /**
     * Changes the running scene to a new instance of the given `PackedScene` (which must be valid).
     * Returns `OK` on success, `ERR_CANT_CREATE` if the scene cannot be instantiated, or
     * `ERR_INVALID_PARAMETER` if the scene is invalid. Note: See `change_scene_to_node` for details on
     * the order of operations.
     *
     * Generated from Godot docs: SceneTree.change_scene_to_packed
     */
    fun changeSceneToPacked(packedScene: PackedScene): Long {
        return ObjectCalls.ptrcallWithObjectArgRetLong(changeSceneToPackedBind, segment, packedScene.requireOpenHandle())
    }

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
    fun changeSceneToNode(node: Node): Long {
        return ObjectCalls.ptrcallWithObjectArgRetLong(changeSceneToNodeBind, segment, node.segment)
    }

    /**
     * Reloads the currently active scene, replacing `current_scene` with a new instance of its
     * original `PackedScene`. Returns `OK` on success, `ERR_UNCONFIGURED` if no `current_scene` is
     * defined, `ERR_CANT_OPEN` if `current_scene` cannot be loaded into a `PackedScene`, or
     * `ERR_CANT_CREATE` if the scene cannot be instantiated.
     *
     * Generated from Godot docs: SceneTree.reload_current_scene
     */
    fun reloadCurrentScene(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(reloadCurrentSceneBind, segment)
    }

    /**
     * If a current scene is loaded, calling this method will unload it.
     *
     * Generated from Godot docs: SceneTree.unload_current_scene
     */
    fun unloadCurrentScene() {
        ObjectCalls.ptrcallNoArgs(unloadCurrentSceneBind, segment)
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
    fun setMultiplayer(multiplayer: MultiplayerAPI?, rootPath: NodePath) {
        ObjectCalls.ptrcallWithObjectAndNodePathArg(setMultiplayerBind, segment, multiplayer?.requireOpenHandle() ?: NULL_SEGMENT, rootPath)
    }

    /**
     * Searches for the `MultiplayerAPI` configured for the given path, if one does not exist it
     * searches the parent paths until one is found. If the path is empty, or none is found, the
     * default one is returned. See `set_multiplayer`.
     *
     * Generated from Godot docs: SceneTree.get_multiplayer
     */
    fun getMultiplayer(forPath: NodePath): MultiplayerAPI? {
        return MultiplayerAPI.wrap(ObjectCalls.ptrcallWithNodePathArgRetObject(getMultiplayerBind, segment, forPath))
    }

    /**
     * If `true` (default value), enables automatic polling of the `MultiplayerAPI` for this SceneTree
     * during `process_frame`. If `false`, you need to manually call `MultiplayerAPI.poll` to process
     * network packets and deliver RPCs. This allows running RPCs in a different loop (e.g. physics,
     * thread, specific time step) and for manual `Mutex` protection when accessing the
     * `MultiplayerAPI` from threads.
     *
     * Generated from Godot docs: SceneTree.set_multiplayer_poll_enabled
     */
    fun setMultiplayerPollEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMultiplayerPollEnabledBind, segment, enabled)
    }

    /**
     * If `true` (default value), enables automatic polling of the `MultiplayerAPI` for this SceneTree
     * during `process_frame`. If `false`, you need to manually call `MultiplayerAPI.poll` to process
     * network packets and deliver RPCs. This allows running RPCs in a different loop (e.g. physics,
     * thread, specific time step) and for manual `Mutex` protection when accessing the
     * `MultiplayerAPI` from threads.
     *
     * Generated from Godot docs: SceneTree.is_multiplayer_poll_enabled
     */
    fun isMultiplayerPollEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMultiplayerPollEnabledBind, segment)
    }

    // ── Kanama SceneTree ergonomics (generator custom-section, not from Godot docs) ───────────
    // setPaused is the desktop/Android spelling of Godot's set_pause. The retired desktop
    // `object SceneTree` and the retired iOS hand class both exposed it, and demo scripts call
    // getTree().setPaused(...), so it stays a member on both platforms (task 117 P1'(b1)).
    fun setPaused(value: Boolean) {
        setPause(value)
    }

    // Frame-driven delay: wait [timeSec] seconds of TREE time by pumping the engine frame loop
    // (MainThread.awaitNextFrame, which both backends resume once per frame) instead of sleeping on
    // the wall clock, so the wait survives a variable frame rate and honours `paused` and
    // Engine.time_scale. Body carried over verbatim from the desktop `object SceneTree`
    // (task 117 P1'(b1)); iOS previously used a wall-clock coroutine delay.
    // `processInPhysics` is accepted for signature parity with createTimer and is not read.
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
                frameSeconds *= engineTimeScale().coerceAtLeast(0.0)
            }

            elapsedSeconds += frameSeconds
        }
    }

    object Signals {
        const val treeChanged: String = "tree_changed"
        const val sceneChanged: String = "scene_changed"
        const val treeProcessModeChanged: String = "tree_process_mode_changed"
        const val nodeAdded: String = "node_added"
        const val nodeRemoved: String = "node_removed"
        const val nodeRenamed: String = "node_renamed"
        const val nodeConfigurationWarningChanged: String = "node_configuration_warning_changed"
        const val processFrame: String = "process_frame"
        const val physicsFrame: String = "physics_frame"
    }

    companion object {
        const val GROUP_CALL_DEFAULT: Long = 0L
        const val GROUP_CALL_REVERSE: Long = 1L
        const val GROUP_CALL_DEFERRED: Long = 2L
        const val GROUP_CALL_UNIQUE: Long = 4L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): SceneTree? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): SceneTree? =
            if (handle.address() == 0L) null else SceneTree(GodotHandle(handle))

        // ── The live SceneTree, and the desktop/Android static entry points ──────────────────
        // Until task 117 P1'(b1) desktop modelled SceneTree as an `object` whose every method
        // resolved the running tree through Engine.get_main_loop(), and the iOS hand-written class
        // carried the same static forms on its companion. SceneTree is one generated class now, so
        // those entry points live here: same names, delegating to active(), typed like the instance
        // member each one calls (see the CHANGELOG for the signatures whose types changed).
        // They carry NO @JvmStatic: a @JvmStatic companion member compiles to a static method on
        // SceneTree itself, which would clash with the instance method of the same JVM signature.
        // A Kotlin caller writes SceneTree.quit() either way; only active() and the two legacy
        // *Handle helpers, which have no instance twin, can stay @JvmStatic.
        private const val GET_MAIN_LOOP_HASH = 1016888095L
        private const val GET_TIME_SCALE_HASH = 191475506L
        private const val CREATE_TWEEN_HASH = 3426978995L

        private val engineSingleton: RawSegment by lazy {
            ObjectCalls.getSingleton("Engine")
        }

        private val getMainLoopBind by lazy {
            ObjectCalls.getMethodBind("Engine", "get_main_loop", GET_MAIN_LOOP_HASH)
        }

        private val getTimeScaleBind by lazy {
            ObjectCalls.getMethodBind("Engine", "get_time_scale", GET_TIME_SCALE_HASH)
        }

        private val createTweenHandleBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "create_tween", CREATE_TWEEN_HASH)
        }

        // Engine.get_time_scale through the singleton above: the desktop `Engine` wrapper has
        // getTimeScale(), the iOS one does not, and delaySeconds needs it on both.
        internal fun engineTimeScale(): Double =
            ObjectCalls.ptrcallNoArgsRetDouble(getTimeScaleBind, engineSingleton)

        /**
         * The SceneTree the engine is running, resolved through `Engine.get_main_loop()`.
         *
         * Throws if there is no main loop, or if the main loop is some other `MainLoop`.
         */
        @JvmStatic
        fun active(): SceneTree {
            val tree = checkNotNull(wrap(ObjectCalls.ptrcallNoArgsRetObject(getMainLoopBind, engineSingleton))) {
                "SceneTree.active(): Engine.get_main_loop() returned null - no main loop is running"
            }
            check(tree.isClass("SceneTree")) {
                "SceneTree.active(): the running main loop is not a SceneTree"
            }
            return tree
        }

        /** The root `Window` of the running tree. Non-null: a running tree always has one. */
        // @JvmName as the generated properties use: without it the getter would be getRoot(), which
        // collides with the getRoot() twin two lines down.
        val root: Window
            @JvmName("rootProperty")
            get() = checkNotNull(active().getRoot()) { "SceneTree.root: the running tree has no root Window" }

        /**
         * If `true`, the scene tree is considered paused. This causes the following behavior: - 2D and 3D
         * physics will be stopped, as well as collision detection and related signals. - Depending on each
         * node's `Node.process_mode`, their `Node._process`, `Node._physics_process` and `Node._input`
         * callback methods may not called anymore.
         *
         * Generated from Godot docs: SceneTree.is_paused
         */
        fun isPaused(): Boolean = active().isPaused()

        fun setPaused(value: Boolean) = active().setPaused(value)

        /**
         * Returns the number of nodes inside this tree.
         *
         * Generated from Godot docs: SceneTree.get_node_count
         */
        fun getNodeCount(): Int = active().getNodeCount()

        /**
         * Returns how many physics process steps have been processed, since the application started. This
         * is not a measurement of elapsed time. See also `physics_frame`. For the number of frames
         * rendered, see `Engine.get_process_frames`.
         *
         * Generated from Godot docs: SceneTree.get_frame
         */
        fun getFrame(): Long = active().getFrame()

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
        fun quit(exitCode: Int = 0) = active().quit(exitCode)

        /**
         * Changes the running scene to the one at the given `path`, after loading it into a `PackedScene`
         * and creating a new instance. Returns `OK` on success, `ERR_CANT_OPEN` if the `path` cannot be
         * loaded into a `PackedScene`, or `ERR_CANT_CREATE` if that scene cannot be instantiated. Note:
         * See `change_scene_to_node` for details on the order of operations.
         *
         * Generated from Godot docs: SceneTree.change_scene_to_file
         */
        fun changeSceneToFile(path: String): Long = active().changeSceneToFile(path)

        /**
         * Reloads the currently active scene, replacing `current_scene` with a new instance of its
         * original `PackedScene`. Returns `OK` on success, `ERR_UNCONFIGURED` if no `current_scene` is
         * defined, `ERR_CANT_OPEN` if `current_scene` cannot be loaded into a `PackedScene`, or
         * `ERR_CANT_CREATE` if the scene cannot be instantiated.
         *
         * Generated from Godot docs: SceneTree.reload_current_scene
         */
        fun reloadCurrentScene(): Long = active().reloadCurrentScene()

        /**
         * If a current scene is loaded, calling this method will unload it.
         *
         * Generated from Godot docs: SceneTree.unload_current_scene
         */
        fun unloadCurrentScene() = active().unloadCurrentScene()

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
        fun setMultiplayer(multiplayer: MultiplayerAPI?, rootPath: NodePath = NodePath("")) =
            active().setMultiplayer(multiplayer, rootPath)

        /**
         * Searches for the `MultiplayerAPI` configured for the given path, if one does not exist it
         * searches the parent paths until one is found. If the path is empty, or none is found, the
         * default one is returned. See `set_multiplayer`.
         *
         * Generated from Godot docs: SceneTree.get_multiplayer
         */
        fun getMultiplayer(forPath: NodePath = NodePath("")): MultiplayerAPI? = active().getMultiplayer(forPath)

        /**
         * If `true` (default value), enables automatic polling of the `MultiplayerAPI` for this SceneTree
         * during `process_frame`. If `false`, you need to manually call `MultiplayerAPI.poll` to process
         * network packets and deliver RPCs. This allows running RPCs in a different loop (e.g. physics,
         * thread, specific time step) and for manual `Mutex` protection when accessing the
         * `MultiplayerAPI` from threads.
         *
         * Generated from Godot docs: SceneTree.is_multiplayer_poll_enabled
         */
        fun isMultiplayerPollEnabled(): Boolean = active().isMultiplayerPollEnabled()

        /**
         * If `true` (default value), enables automatic polling of the `MultiplayerAPI` for this SceneTree
         * during `process_frame`. If `false`, you need to manually call `MultiplayerAPI.poll` to process
         * network packets and deliver RPCs. This allows running RPCs in a different loop (e.g. physics,
         * thread, specific time step) and for manual `Mutex` protection when accessing the
         * `MultiplayerAPI` from threads.
         *
         * Generated from Godot docs: SceneTree.set_multiplayer_poll_enabled
         */
        fun setMultiplayerPollEnabled(enabled: Boolean) = active().setMultiplayerPollEnabled(enabled)

        /**
         * Returns `true` if a node added to the given group `name` exists in the tree.
         *
         * Generated from Godot docs: SceneTree.has_group
         */
        fun hasGroup(name: String): Boolean = active().hasGroup(name)

        /**
         * Returns the number of nodes assigned to the given group.
         *
         * Generated from Godot docs: SceneTree.get_node_count_in_group
         */
        fun getNodeCountInGroup(name: String): Int = active().getNodeCountInGroup(name)

        /**
         * The tree's root `Window`. This is top-most `Node` of the scene tree, and is always present. An
         * absolute `NodePath` always starts from this node. Children of the root node may include the
         * loaded `current_scene`, as well as any AutoLoad
         * ($DOCS_URL/tutorials/scripting/singletons_autoload.html) configured in the Project Settings.
         * Warning: Do not delete this node. This will result in unstable behavior, followed by a crash.
         *
         * Generated from Godot docs: SceneTree.get_root
         */
        fun getRoot(): Window? = active().getRoot()

        /**
         * The root node of the currently loaded main scene, usually as a direct child of `root`. See also
         * `change_scene_to_file`, `change_scene_to_packed`, and `reload_current_scene`. Warning: Setting
         * this property directly may not work as expected, as it does not add or remove any nodes from
         * this tree.
         *
         * Generated from Godot docs: SceneTree.get_current_scene
         */
        fun getCurrentScene(): Node? = active().getCurrentScene()

        /**
         * The root node of the currently loaded main scene, usually as a direct child of `root`. See also
         * `change_scene_to_file`, `change_scene_to_packed`, and `reload_current_scene`. Warning: Setting
         * this property directly may not work as expected, as it does not add or remove any nodes from
         * this tree.
         *
         * Generated from Godot docs: SceneTree.set_current_scene
         */
        fun setCurrentScene(childNode: Node) = active().setCurrentScene(childNode)

        /**
         * Returns the first `Node` found inside the tree, that has been added to the given `group`, in
         * scene hierarchy order. Returns `null` if no match is found. See also `get_nodes_in_group`.
         *
         * Generated from Godot docs: SceneTree.get_first_node_in_group
         */
        fun getFirstNodeInGroup(name: String): Node? = active().getFirstNodeInGroup(name)

        /**
         * Returns an `Array` containing all nodes inside this tree, that have been added to the given
         * `group`, in scene hierarchy order.
         *
         * Generated from Godot docs: SceneTree.get_nodes_in_group
         */
        fun getNodesInGroup(name: String): List<Node> = active().getNodesInGroup(name)

        /**
         * Queues the given `obj` to be deleted, calling its `Object.free` at the end of the current frame.
         * This method is similar to `Node.queue_free`.
         *
         * Generated from Godot docs: SceneTree.queue_delete
         */
        fun queueDelete(obj: GodotObject) = active().queueDelete(obj)

        /**
         * Changes the running scene to a new instance of the given `PackedScene` (which must be valid).
         * Returns `OK` on success, `ERR_CANT_CREATE` if the scene cannot be instantiated, or
         * `ERR_INVALID_PARAMETER` if the scene is invalid. Note: See `change_scene_to_node` for details on
         * the order of operations.
         *
         * Generated from Godot docs: SceneTree.change_scene_to_packed
         */
        fun changeSceneToPacked(packedScene: PackedScene): Long = active().changeSceneToPacked(packedScene)

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
        fun changeSceneToNode(node: Node): Long = active().changeSceneToNode(node)

        /**
         * The root of the scene currently being edited in the editor. This is usually a direct child of
         * `root`. Note: This property does nothing in release builds.
         *
         * Generated from Godot docs: SceneTree.get_edited_scene_root
         */
        fun getEditedSceneRoot(): Node? = active().getEditedSceneRoot()

        /**
         * The root of the scene currently being edited in the editor. This is usually a direct child of
         * `root`. Note: This property does nothing in release builds.
         *
         * Generated from Godot docs: SceneTree.set_edited_scene_root
         */
        fun setEditedSceneRoot(scene: Node) = active().setEditedSceneRoot(scene)

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
        fun callGroup(groupName: String, methodName: String, vararg args: Any?) =
            active().callGroup(groupName, methodName, *args)

        /**
         * Calls `Object.notification` with the given `notification` to all nodes inside this tree added to
         * the `group`. See also Godot notifications
         * ($DOCS_URL/tutorials/best_practices/godot_notifications.html) and `call_group` and `set_group`.
         * Note: This method acts immediately on all selected nodes at once, which may cause stuttering in
         * some performance-intensive situations.
         *
         * Generated from Godot docs: SceneTree.notify_group
         */
        fun notifyGroup(groupName: String, notification: Int) = active().notifyGroup(groupName, notification)

        /**
         * Calls the given `method` on each node inside this tree added to the given `group`. Use `flags`
         * to customize this method's behavior (see `GroupCallFlags`). Additional arguments for `method`
         * can be passed at the end of this method. Nodes that cannot call `method` (either because the
         * method doesn't exist or the arguments do not match) are ignored.
         *
         * Generated from Godot docs: SceneTree.call_group_flags
         */
        fun callGroupFlags(flags: Long, groupName: String, methodName: String, vararg args: Any?) =
            active().callGroupFlags(flags, groupName, methodName, *args)

        /**
         * Calls `Object.notification` with the given `notification` to all nodes inside this tree added to
         * the `group`. Use `call_flags` to customize this method's behavior (see `GroupCallFlags`).
         *
         * Generated from Godot docs: SceneTree.notify_group_flags
         */
        fun notifyGroupFlags(flags: Long, groupName: String, notification: Int) =
            active().notifyGroupFlags(flags, groupName, notification)

        /**
         * Sets the given `property` to `value` on all nodes inside this tree added to the given `group`.
         * Nodes that do not have the `property` are ignored. Use `call_flags` to customize this method's
         * behavior (see `GroupCallFlags`). Note: In C#, `property` must be in snake_case when referring to
         * built-in Godot properties. Prefer using the names exposed in the `PropertyName` class to avoid
         * allocating a new `StringName` on each call.
         *
         * Generated from Godot docs: SceneTree.set_group_flags
         */
        fun setGroupFlags(flags: Long, groupName: String, property: String, value: Any?) =
            active().setGroupFlags(flags, groupName, property, value)

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
        fun setGroup(groupName: String, property: String, value: Any?) = active().setGroup(groupName, property, value)

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
        fun createTimer(
            timeSec: Double,
            processAlways: Boolean = true,
            processInPhysics: Boolean = false,
            ignoreTimeScale: Boolean = false,
        ): SceneTreeTimer? = active().createTimer(timeSec, processAlways, processInPhysics, ignoreTimeScale)

        // legacy handle-returning form: the retired desktop `object SceneTree` exposed the raw
        // GodotHandle next to the wrapper-returning call. Kept so callers keep compiling; prefer
        // createTimer(...) / SceneTree.createTween().
        @JvmStatic
        fun createTimerHandle(
            timeSec: Double,
            processAlways: Boolean = true,
            processInPhysics: Boolean = false,
            ignoreTimeScale: Boolean = false,
        ): GodotHandle = GodotHandle(
            ObjectCalls.ptrcallWithDoubleAndThreeBoolArgsRetObject(
                createTimerBind,
                active().segment,
                timeSec,
                processAlways,
                processInPhysics,
                ignoreTimeScale,
            ),
        )

        // legacy handle-returning form (see createTimerHandle). SceneTree.create_tween, not
        // Node.create_tween: the tree is a MainLoop, not a Node.
        @JvmStatic
        fun createTweenHandle(): GodotHandle =
            GodotHandle(ObjectCalls.ptrcallNoArgsRetObject(createTweenHandleBind, active().segment))

        suspend fun delaySeconds(
            timeSec: Double,
            processAlways: Boolean = true,
            processInPhysics: Boolean = false,
            ignoreTimeScale: Boolean = false,
        ) = active().delaySeconds(timeSec, processAlways, processInPhysics, ignoreTimeScale)

        /**
         * Returns `true` if accessibility features are enabled, and accessibility information updates are
         * actively processed.
         *
         * Generated from Godot docs: SceneTree.is_accessibility_enabled
         */
        fun isAccessibilityEnabled(): Boolean = active().isAccessibilityEnabled()

        /**
         * Returns `true` if accessibility features are supported by the OS and enabled in project
         * settings.
         *
         * Generated from Godot docs: SceneTree.is_accessibility_supported
         */
        fun isAccessibilitySupported(): Boolean = active().isAccessibilitySupported()

        /**
         * If `true`, the application automatically accepts quitting requests. For mobile platforms, see
         * `quit_on_go_back`.
         *
         * Generated from Godot docs: SceneTree.is_auto_accept_quit
         */
        fun isAutoAcceptQuit(): Boolean = active().isAutoAcceptQuit()

        /**
         * If `true`, the application automatically accepts quitting requests. For mobile platforms, see
         * `quit_on_go_back`.
         *
         * Generated from Godot docs: SceneTree.set_auto_accept_quit
         */
        fun setAutoAcceptQuit(enabled: Boolean) = active().setAutoAcceptQuit(enabled)

        /**
         * If `true`, the application quits automatically when navigating back (e.g. using the system
         * "Back" button on Android). To handle 'Go Back' button when this option is disabled, use
         * `DisplayServer.WINDOW_EVENT_GO_BACK_REQUEST`.
         *
         * Generated from Godot docs: SceneTree.is_quit_on_go_back
         */
        fun isQuitOnGoBack(): Boolean = active().isQuitOnGoBack()

        /**
         * If `true`, the application quits automatically when navigating back (e.g. using the system
         * "Back" button on Android). To handle 'Go Back' button when this option is disabled, use
         * `DisplayServer.WINDOW_EVENT_GO_BACK_REQUEST`.
         *
         * Generated from Godot docs: SceneTree.set_quit_on_go_back
         */
        fun setQuitOnGoBack(enabled: Boolean) = active().setQuitOnGoBack(enabled)

        /**
         * If `true`, collision shapes will be visible when running the game from the editor for debugging
         * purposes. Note: This property is not designed to be changed at run-time. Changing the value of
         * `debug_collisions_hint` while the project is running will not have the desired effect.
         *
         * Generated from Godot docs: SceneTree.set_debug_collisions_hint
         */
        fun setDebugCollisionsHint(enabled: Boolean) = active().setDebugCollisionsHint(enabled)

        /**
         * If `true`, collision shapes will be visible when running the game from the editor for debugging
         * purposes. Note: This property is not designed to be changed at run-time. Changing the value of
         * `debug_collisions_hint` while the project is running will not have the desired effect.
         *
         * Generated from Godot docs: SceneTree.is_debugging_collisions_hint
         */
        fun isDebuggingCollisionsHint(): Boolean = active().isDebuggingCollisionsHint()

        /**
         * If `true`, curves from `Path2D` and `Path3D` nodes will be visible when running the game from
         * the editor for debugging purposes. Note: This property is not designed to be changed at
         * run-time. Changing the value of `debug_paths_hint` while the project is running will not have
         * the desired effect.
         *
         * Generated from Godot docs: SceneTree.set_debug_paths_hint
         */
        fun setDebugPathsHint(enabled: Boolean) = active().setDebugPathsHint(enabled)

        /**
         * If `true`, curves from `Path2D` and `Path3D` nodes will be visible when running the game from
         * the editor for debugging purposes. Note: This property is not designed to be changed at
         * run-time. Changing the value of `debug_paths_hint` while the project is running will not have
         * the desired effect.
         *
         * Generated from Godot docs: SceneTree.is_debugging_paths_hint
         */
        fun isDebuggingPathsHint(): Boolean = active().isDebuggingPathsHint()

        /**
         * If `true`, navigation polygons will be visible when running the game from the editor for
         * debugging purposes. Note: This property is not designed to be changed at run-time. Changing the
         * value of `debug_navigation_hint` while the project is running will not have the desired effect.
         *
         * Generated from Godot docs: SceneTree.set_debug_navigation_hint
         */
        fun setDebugNavigationHint(enabled: Boolean) = active().setDebugNavigationHint(enabled)

        /**
         * If `true`, navigation polygons will be visible when running the game from the editor for
         * debugging purposes. Note: This property is not designed to be changed at run-time. Changing the
         * value of `debug_navigation_hint` while the project is running will not have the desired effect.
         *
         * Generated from Godot docs: SceneTree.is_debugging_navigation_hint
         */
        fun isDebuggingNavigationHint(): Boolean = active().isDebuggingNavigationHint()

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
        fun setPhysicsInterpolationEnabled(enabled: Boolean) = active().setPhysicsInterpolationEnabled(enabled)

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
        fun isPhysicsInterpolationEnabled(): Boolean = active().isPhysicsInterpolationEnabled()

        private const val GET_ROOT_HASH = 1757182445L
        private val getRootBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "get_root", GET_ROOT_HASH)
        }

        private const val HAS_GROUP_HASH = 2619796661L
        private val hasGroupBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "has_group", HAS_GROUP_HASH)
        }

        private const val IS_ACCESSIBILITY_ENABLED_HASH = 36873697L
        private val isAccessibilityEnabledBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "is_accessibility_enabled", IS_ACCESSIBILITY_ENABLED_HASH)
        }

        private const val IS_ACCESSIBILITY_SUPPORTED_HASH = 36873697L
        private val isAccessibilitySupportedBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "is_accessibility_supported", IS_ACCESSIBILITY_SUPPORTED_HASH)
        }

        private const val IS_AUTO_ACCEPT_QUIT_HASH = 36873697L
        private val isAutoAcceptQuitBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "is_auto_accept_quit", IS_AUTO_ACCEPT_QUIT_HASH)
        }

        private const val SET_AUTO_ACCEPT_QUIT_HASH = 2586408642L
        private val setAutoAcceptQuitBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "set_auto_accept_quit", SET_AUTO_ACCEPT_QUIT_HASH)
        }

        private const val IS_QUIT_ON_GO_BACK_HASH = 36873697L
        private val isQuitOnGoBackBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "is_quit_on_go_back", IS_QUIT_ON_GO_BACK_HASH)
        }

        private const val SET_QUIT_ON_GO_BACK_HASH = 2586408642L
        private val setQuitOnGoBackBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "set_quit_on_go_back", SET_QUIT_ON_GO_BACK_HASH)
        }

        private const val SET_DEBUG_COLLISIONS_HINT_HASH = 2586408642L
        private val setDebugCollisionsHintBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "set_debug_collisions_hint", SET_DEBUG_COLLISIONS_HINT_HASH)
        }

        private const val IS_DEBUGGING_COLLISIONS_HINT_HASH = 36873697L
        private val isDebuggingCollisionsHintBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "is_debugging_collisions_hint", IS_DEBUGGING_COLLISIONS_HINT_HASH)
        }

        private const val SET_DEBUG_PATHS_HINT_HASH = 2586408642L
        private val setDebugPathsHintBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "set_debug_paths_hint", SET_DEBUG_PATHS_HINT_HASH)
        }

        private const val IS_DEBUGGING_PATHS_HINT_HASH = 36873697L
        private val isDebuggingPathsHintBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "is_debugging_paths_hint", IS_DEBUGGING_PATHS_HINT_HASH)
        }

        private const val SET_DEBUG_NAVIGATION_HINT_HASH = 2586408642L
        private val setDebugNavigationHintBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "set_debug_navigation_hint", SET_DEBUG_NAVIGATION_HINT_HASH)
        }

        private const val IS_DEBUGGING_NAVIGATION_HINT_HASH = 36873697L
        private val isDebuggingNavigationHintBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "is_debugging_navigation_hint", IS_DEBUGGING_NAVIGATION_HINT_HASH)
        }

        private const val SET_EDITED_SCENE_ROOT_HASH = 1078189570L
        private val setEditedSceneRootBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "set_edited_scene_root", SET_EDITED_SCENE_ROOT_HASH)
        }

        private const val GET_EDITED_SCENE_ROOT_HASH = 3160264692L
        private val getEditedSceneRootBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "get_edited_scene_root", GET_EDITED_SCENE_ROOT_HASH)
        }

        private const val SET_PAUSE_HASH = 2586408642L
        private val setPauseBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "set_pause", SET_PAUSE_HASH)
        }

        private const val IS_PAUSED_HASH = 36873697L
        private val isPausedBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "is_paused", IS_PAUSED_HASH)
        }

        private const val CREATE_TIMER_HASH = 2709170273L
        private val createTimerBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "create_timer", CREATE_TIMER_HASH)
        }

        private const val GET_NODE_COUNT_HASH = 3905245786L
        private val getNodeCountBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "get_node_count", GET_NODE_COUNT_HASH)
        }

        private const val GET_FRAME_HASH = 3905245786L
        private val getFrameBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "get_frame", GET_FRAME_HASH)
        }

        private const val QUIT_HASH = 1995695955L
        private val quitBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "quit", QUIT_HASH)
        }

        private const val SET_PHYSICS_INTERPOLATION_ENABLED_HASH = 2586408642L
        private val setPhysicsInterpolationEnabledBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "set_physics_interpolation_enabled", SET_PHYSICS_INTERPOLATION_ENABLED_HASH)
        }

        private const val IS_PHYSICS_INTERPOLATION_ENABLED_HASH = 36873697L
        private val isPhysicsInterpolationEnabledBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "is_physics_interpolation_enabled", IS_PHYSICS_INTERPOLATION_ENABLED_HASH)
        }

        private const val QUEUE_DELETE_HASH = 3975164845L
        private val queueDeleteBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "queue_delete", QUEUE_DELETE_HASH)
        }

        private const val CALL_GROUP_FLAGS_HASH = 1527739229L
        private val callGroupFlagsBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "call_group_flags", CALL_GROUP_FLAGS_HASH)
        }

        private const val NOTIFY_GROUP_FLAGS_HASH = 1245489420L
        private val notifyGroupFlagsBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "notify_group_flags", NOTIFY_GROUP_FLAGS_HASH)
        }

        private const val SET_GROUP_FLAGS_HASH = 3497599527L
        private val setGroupFlagsBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "set_group_flags", SET_GROUP_FLAGS_HASH)
        }

        private const val CALL_GROUP_HASH = 1257962832L
        private val callGroupBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "call_group", CALL_GROUP_HASH)
        }

        private const val NOTIFY_GROUP_HASH = 2415702435L
        private val notifyGroupBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "notify_group", NOTIFY_GROUP_HASH)
        }

        private const val SET_GROUP_HASH = 1279312029L
        private val setGroupBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "set_group", SET_GROUP_HASH)
        }

        private const val GET_NODES_IN_GROUP_HASH = 689397652L
        private val getNodesInGroupBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "get_nodes_in_group", GET_NODES_IN_GROUP_HASH)
        }

        private const val GET_FIRST_NODE_IN_GROUP_HASH = 4071044623L
        private val getFirstNodeInGroupBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "get_first_node_in_group", GET_FIRST_NODE_IN_GROUP_HASH)
        }

        private const val GET_NODE_COUNT_IN_GROUP_HASH = 2458036349L
        private val getNodeCountInGroupBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "get_node_count_in_group", GET_NODE_COUNT_IN_GROUP_HASH)
        }

        private const val SET_CURRENT_SCENE_HASH = 1078189570L
        private val setCurrentSceneBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "set_current_scene", SET_CURRENT_SCENE_HASH)
        }

        private const val GET_CURRENT_SCENE_HASH = 3160264692L
        private val getCurrentSceneBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "get_current_scene", GET_CURRENT_SCENE_HASH)
        }

        private const val CHANGE_SCENE_TO_FILE_HASH = 166001499L
        private val changeSceneToFileBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "change_scene_to_file", CHANGE_SCENE_TO_FILE_HASH)
        }

        private const val CHANGE_SCENE_TO_PACKED_HASH = 107349098L
        private val changeSceneToPackedBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "change_scene_to_packed", CHANGE_SCENE_TO_PACKED_HASH)
        }

        private const val CHANGE_SCENE_TO_NODE_HASH = 2584678054L
        private val changeSceneToNodeBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "change_scene_to_node", CHANGE_SCENE_TO_NODE_HASH)
        }

        private const val RELOAD_CURRENT_SCENE_HASH = 166280745L
        private val reloadCurrentSceneBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "reload_current_scene", RELOAD_CURRENT_SCENE_HASH)
        }

        private const val UNLOAD_CURRENT_SCENE_HASH = 3218959716L
        private val unloadCurrentSceneBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "unload_current_scene", UNLOAD_CURRENT_SCENE_HASH)
        }

        private const val SET_MULTIPLAYER_HASH = 2385607013L
        private val setMultiplayerBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "set_multiplayer", SET_MULTIPLAYER_HASH)
        }

        private const val GET_MULTIPLAYER_HASH = 3453401404L
        private val getMultiplayerBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "get_multiplayer", GET_MULTIPLAYER_HASH)
        }

        private const val SET_MULTIPLAYER_POLL_ENABLED_HASH = 2586408642L
        private val setMultiplayerPollEnabledBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "set_multiplayer_poll_enabled", SET_MULTIPLAYER_POLL_ENABLED_HASH)
        }

        private const val IS_MULTIPLAYER_POLL_ENABLED_HASH = 36873697L
        private val isMultiplayerPollEnabledBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "is_multiplayer_poll_enabled", IS_MULTIPLAYER_POLL_ENABLED_HASH)
        }
    }
}

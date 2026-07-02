package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.RID

/**
 * Generated from Godot docs: Node
 */
open class Node(handle: MemorySegment) : GodotObject(handle) {
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

    val owner: Node?
        @JvmName("ownerProperty")
        get() = getOwner()

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

    fun addSibling(sibling: Node, forceReadableName: Boolean = false) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(addSiblingBind, handle, sibling.handle, forceReadableName)
    }

    fun setName(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(setNameBind, handle, name)
    }

    fun getName(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getNameBind, handle)
    }

    fun addChild(node: Node, forceReadableName: Boolean = false, internalValue: Long = 0L) {
        ObjectCalls.ptrcallWithObjectBoolLongArgs(addChildBind, handle, node.handle, forceReadableName, internalValue)
    }

    fun removeChild(node: Node) {
        ObjectCalls.ptrcallWithObjectArgs(removeChildBind, handle, listOf(node.handle))
    }

    fun reparent(newParent: Node, keepGlobalTransform: Boolean = true) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(reparentBind, handle, newParent.handle, keepGlobalTransform)
    }

    fun getChildCount(includeInternal: Boolean = false): Int {
        return ObjectCalls.ptrcallWithBoolArgRetInt(getChildCountBind, handle, includeInternal)
    }

    fun getChildren(includeInternal: Boolean = false): List<Node> {
        return ObjectCalls.ptrcallWithBoolArgRetTypedObjectList(getChildrenBind, handle, includeInternal, Node::fromHandle)
    }

    fun getChild(idx: Int, includeInternal: Boolean = false): Node? {
        return Node.wrap(ObjectCalls.ptrcallWithIntAndBoolArgsRetObject(getChildBind, handle, idx, includeInternal))
    }

    fun hasNode(path: NodePath): Boolean {
        return ObjectCalls.ptrcallWithNodePathArgRetBool(hasNodeBind, handle, path)
    }

    fun getNode(path: NodePath): Node? {
        return Node.wrap(ObjectCalls.ptrcallWithNodePathArgRetObject(getNodeBind, handle, path))
    }

    fun getNodeOrNull(path: NodePath): Node? {
        return Node.wrap(ObjectCalls.ptrcallWithNodePathArgRetObject(getNodeOrNullBind, handle, path))
    }

    fun getParent(): Node? {
        return Node.wrap(ObjectCalls.ptrcallNoArgsRetObject(getParentBind, handle))
    }

    fun findChild(pattern: String, recursive: Boolean = true, owned: Boolean = true): Node? {
        return Node.wrap(ObjectCalls.ptrcallWithStringAndTwoBoolArgsRetObject(findChildBind, handle, pattern, recursive, owned))
    }

    fun findChildren(pattern: String, type: String = "", recursive: Boolean = true, owned: Boolean = true): List<Node> {
        return ObjectCalls.ptrcallWithTwoStringAndTwoBoolArgsRetTypedObjectList(findChildrenBind, handle, pattern, type, recursive, owned, Node::fromHandle)
    }

    fun findParent(pattern: String): Node? {
        return Node.wrap(ObjectCalls.ptrcallWithStringArgRetObject(findParentBind, handle, pattern))
    }

    fun hasNodeAndResource(path: NodePath): Boolean {
        return ObjectCalls.ptrcallWithNodePathArgRetBool(hasNodeAndResourceBind, handle, path)
    }

    fun getNodeAndResource(path: NodePath): List<Any?> {
        return ObjectCalls.ptrcallWithNodePathArgRetArray(getNodeAndResourceBind, handle, path)
    }

    fun isInsideTree(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isInsideTreeBind, handle)
    }

    fun isPartOfEditedScene(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPartOfEditedSceneBind, handle)
    }

    fun isAncestorOf(node: Node): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(isAncestorOfBind, handle, node.handle)
    }

    fun isGreaterThan(node: Node): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(isGreaterThanBind, handle, node.handle)
    }

    fun getPath(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getPathBind, handle)
    }

    fun getPathTo(node: Node, useUniquePath: Boolean = false): NodePath {
        return ObjectCalls.ptrcallWithObjectAndBoolArgRetNodePath(getPathToBind, handle, node.handle, useUniquePath)
    }

    fun addToGroup(group: String, persistent: Boolean = false) {
        ObjectCalls.ptrcallWithStringNameAndBoolArg(addToGroupBind, handle, group, persistent)
    }

    fun removeFromGroup(group: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeFromGroupBind, handle, group)
    }

    fun isInGroup(group: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(isInGroupBind, handle, group)
    }

    fun moveChild(childNode: Node, toIndex: Int) {
        ObjectCalls.ptrcallWithObjectAndIntArg(moveChildBind, handle, childNode.handle, toIndex)
    }

    fun getGroups(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetStringNameList(getGroupsBind, handle)
    }

    fun setOwner(owner: Node) {
        ObjectCalls.ptrcallWithObjectArgs(setOwnerBind, handle, listOf(owner.handle))
    }

    fun getOwner(): Node? {
        return Node.wrap(ObjectCalls.ptrcallNoArgsRetObject(getOwnerBind, handle))
    }

    fun getIndex(includeInternal: Boolean = false): Int {
        return ObjectCalls.ptrcallWithBoolArgRetInt(getIndexBind, handle, includeInternal)
    }

    fun printTree() {
        ObjectCalls.ptrcallNoArgs(printTreeBind, handle)
    }

    fun printTreePretty() {
        ObjectCalls.ptrcallNoArgs(printTreePrettyBind, handle)
    }

    fun getTreeString(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTreeStringBind, handle)
    }

    fun getTreeStringPretty(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTreeStringPrettyBind, handle)
    }

    fun setSceneFilePath(sceneFilePath: String) {
        ObjectCalls.ptrcallWithStringArg(setSceneFilePathBind, handle, sceneFilePath)
    }

    fun getSceneFilePath(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getSceneFilePathBind, handle)
    }

    fun propagateNotification(what: Int) {
        ObjectCalls.ptrcallWithIntArg(propagateNotificationBind, handle, what)
    }

    fun setPhysicsProcess(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPhysicsProcessBind, handle, enable)
    }

    fun getPhysicsProcessDeltaTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPhysicsProcessDeltaTimeBind, handle)
    }

    fun isPhysicsProcessing(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPhysicsProcessingBind, handle)
    }

    fun getProcessDeltaTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getProcessDeltaTimeBind, handle)
    }

    fun setProcess(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setProcessBind, handle, enable)
    }

    fun setProcessPriority(priority: Int) {
        ObjectCalls.ptrcallWithIntArg(setProcessPriorityBind, handle, priority)
    }

    fun getProcessPriority(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getProcessPriorityBind, handle)
    }

    fun setPhysicsProcessPriority(priority: Int) {
        ObjectCalls.ptrcallWithIntArg(setPhysicsProcessPriorityBind, handle, priority)
    }

    fun getPhysicsProcessPriority(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPhysicsProcessPriorityBind, handle)
    }

    fun isProcessing(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isProcessingBind, handle)
    }

    fun setProcessInput(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setProcessInputBind, handle, enable)
    }

    fun isProcessingInput(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isProcessingInputBind, handle)
    }

    fun setProcessShortcutInput(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setProcessShortcutInputBind, handle, enable)
    }

    fun isProcessingShortcutInput(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isProcessingShortcutInputBind, handle)
    }

    fun setProcessUnhandledInput(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setProcessUnhandledInputBind, handle, enable)
    }

    fun isProcessingUnhandledInput(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isProcessingUnhandledInputBind, handle)
    }

    fun setProcessUnhandledKeyInput(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setProcessUnhandledKeyInputBind, handle, enable)
    }

    fun isProcessingUnhandledKeyInput(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isProcessingUnhandledKeyInputBind, handle)
    }

    fun setProcessMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setProcessModeBind, handle, mode)
    }

    fun getProcessMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getProcessModeBind, handle)
    }

    fun canProcess(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(canProcessBind, handle)
    }

    fun setProcessThreadGroup(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setProcessThreadGroupBind, handle, mode)
    }

    fun getProcessThreadGroup(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getProcessThreadGroupBind, handle)
    }

    fun setProcessThreadMessages(flags: Long) {
        ObjectCalls.ptrcallWithLongArg(setProcessThreadMessagesBind, handle, flags)
    }

    fun getProcessThreadMessages(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getProcessThreadMessagesBind, handle)
    }

    fun setProcessThreadGroupOrder(order: Int) {
        ObjectCalls.ptrcallWithIntArg(setProcessThreadGroupOrderBind, handle, order)
    }

    fun getProcessThreadGroupOrder(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getProcessThreadGroupOrderBind, handle)
    }

    fun queueAccessibilityUpdate() {
        ObjectCalls.ptrcallNoArgs(queueAccessibilityUpdateBind, handle)
    }

    fun getAccessibilityElement(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getAccessibilityElementBind, handle)
    }

    fun setDisplayFolded(fold: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDisplayFoldedBind, handle, fold)
    }

    fun isDisplayedFolded(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDisplayedFoldedBind, handle)
    }

    fun setProcessInternal(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setProcessInternalBind, handle, enable)
    }

    fun isProcessingInternal(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isProcessingInternalBind, handle)
    }

    fun setPhysicsProcessInternal(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPhysicsProcessInternalBind, handle, enable)
    }

    fun isPhysicsProcessingInternal(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPhysicsProcessingInternalBind, handle)
    }

    fun setPhysicsInterpolationMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setPhysicsInterpolationModeBind, handle, mode)
    }

    fun getPhysicsInterpolationMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPhysicsInterpolationModeBind, handle)
    }

    fun isPhysicsInterpolated(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPhysicsInterpolatedBind, handle)
    }

    fun isPhysicsInterpolatedAndEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPhysicsInterpolatedAndEnabledBind, handle)
    }

    fun resetPhysicsInterpolation() {
        ObjectCalls.ptrcallNoArgs(resetPhysicsInterpolationBind, handle)
    }

    fun setAutoTranslateMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setAutoTranslateModeBind, handle, mode)
    }

    fun getAutoTranslateMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAutoTranslateModeBind, handle)
    }

    fun canAutoTranslate(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(canAutoTranslateBind, handle)
    }

    fun setTranslationDomainInherited() {
        ObjectCalls.ptrcallNoArgs(setTranslationDomainInheritedBind, handle)
    }

    fun getWindow(): Window? {
        return Window.wrap(ObjectCalls.ptrcallNoArgsRetObject(getWindowBind, handle))
    }

    fun getLastExclusiveWindow(): Window? {
        return Window.wrap(ObjectCalls.ptrcallNoArgsRetObject(getLastExclusiveWindowBind, handle))
    }

    fun duplicate(flags: Int = 15): Node? {
        return Node.wrap(ObjectCalls.ptrcallWithIntArgRetObject(duplicateBind, handle, flags))
    }

    fun replaceBy(node: Node, keepGroups: Boolean = false) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(replaceByBind, handle, node.handle, keepGroups)
    }

    fun setSceneInstanceLoadPlaceholder(loadPlaceholder: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSceneInstanceLoadPlaceholderBind, handle, loadPlaceholder)
    }

    fun getSceneInstanceLoadPlaceholder(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getSceneInstanceLoadPlaceholderBind, handle)
    }

    fun setEditableInstance(node: Node, isEditable: Boolean) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(setEditableInstanceBind, handle, node.handle, isEditable)
    }

    fun isEditableInstance(node: Node): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(isEditableInstanceBind, handle, node.handle)
    }

    fun getViewport(): Viewport? {
        return Viewport.wrap(ObjectCalls.ptrcallNoArgsRetObject(getViewportBind, handle))
    }

    fun queueFree() {
        ObjectCalls.ptrcallNoArgs(queueFreeBind, handle)
    }

    fun requestReady() {
        ObjectCalls.ptrcallNoArgs(requestReadyBind, handle)
    }

    fun isNodeReady(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isNodeReadyBind, handle)
    }

    fun setMultiplayerAuthority(id: Int, recursive: Boolean = true) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setMultiplayerAuthorityBind, handle, id, recursive)
    }

    fun getMultiplayerAuthority(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMultiplayerAuthorityBind, handle)
    }

    fun isMultiplayerAuthority(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMultiplayerAuthorityBind, handle)
    }

    fun getMultiplayer(): MultiplayerAPI? {
        return MultiplayerAPI.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMultiplayerBind, handle))
    }

    fun getNodeRpcConfig(): Any? {
        return ObjectCalls.ptrcallNoArgsRetVariantScalar(getNodeRpcConfigBind, handle)
    }

    fun setEditorDescription(editorDescription: String) {
        ObjectCalls.ptrcallWithStringArg(setEditorDescriptionBind, handle, editorDescription)
    }

    fun getEditorDescription(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getEditorDescriptionBind, handle)
    }

    fun setUniqueNameInOwner(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUniqueNameInOwnerBind, handle, enable)
    }

    fun isUniqueNameInOwner(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUniqueNameInOwnerBind, handle)
    }

    fun atr(message: String, context: String = ""): String {
        return ObjectCalls.ptrcallWithStringAndStringNameArgRetString(atrBind, handle, message, context)
    }

    fun atrN(message: String, pluralMessage: String, n: Int, context: String = ""): String {
        return ObjectCalls.ptrcallWithStringStringNameIntStringNameArgsRetString(atrNBind, handle, message, pluralMessage, n, context)
    }

    fun rpc(method: String, vararg extraArgs: Any?): Long {
        return (ObjectCalls.callWithVariantArgs(rpcBind, handle, listOf(method, *extraArgs)) as Number).toLong()
    }

    fun rpcId(peerId: Long, method: String, vararg extraArgs: Any?): Long {
        return (ObjectCalls.callWithVariantArgs(rpcIdBind, handle, listOf(peerId, method, *extraArgs)) as Number).toLong()
    }

    fun updateConfigurationWarnings() {
        ObjectCalls.ptrcallNoArgs(updateConfigurationWarningsBind, handle)
    }

    fun callDeferredThreadGroup(method: String, vararg extraArgs: Any?): Any? {
        return ObjectCalls.callWithVariantArgs(callDeferredThreadGroupBind, handle, listOf(method, *extraArgs))
    }

    fun notifyDeferredThreadGroup(what: Int) {
        ObjectCalls.ptrcallWithIntArg(notifyDeferredThreadGroupBind, handle, what)
    }

    fun callThreadSafe(method: String, vararg extraArgs: Any?): Any? {
        return ObjectCalls.callWithVariantArgs(callThreadSafeBind, handle, listOf(method, *extraArgs))
    }

    fun notifyThreadSafe(what: Int) {
        ObjectCalls.ptrcallWithIntArg(notifyThreadSafeBind, handle, what)
    }

    // ── Kanama iOS sugar (generator custom-section, not from Godot docs) ───────
    // getViewport() is generated above (Rect2 kind widening made Viewport an emitted
    // return type), so it is intentionally not duplicated here.

    fun getTree(): SceneTree =
        SceneTree(MemorySegment.ofAddress(IosGodot.nodeGetTree(handle.address())))

    fun getNodeOrNull(path: String): Node? =
        IosGodot.nodeGetNodeOrNull(handle.address(), path).takeIf { it != 0L }?.let {
            Node(MemorySegment.ofAddress(it))
        }

    fun <T : Node> getAsOrNull(path: String, ctor: (MemorySegment) -> T): T? =
        getNodeOrNull(path)?.let { ctor(it.handle) }

    fun <T : Node> getAsOrNull(path: NodePath, ctor: (MemorySegment) -> T): T? =
        getAsOrNull(path.path, ctor)

    fun <T : Node> requireAs(path: String, ctor: (MemorySegment) -> T): T =
        getAsOrNull(path, ctor) ?: error("Required node '$path' was not found")

    fun <T : Node> requireAs(path: NodePath, ctor: (MemorySegment) -> T): T =
        requireAs(path.path, ctor)

    fun <T : Node> getNodeAsOrNull(path: String, className: String, ctor: (MemorySegment) -> T): T? =
        getNodeOrNull(path)?.takeIf { it.isClass(className) }?.let { ctor(it.handle) }

    // `open` so the hand-written SceneTree subclass (IosGodotApi.kt) can override createTween() with
    // the correct SceneTree.create_tween bind — the FPS F2 fix. Generated here so a regen preserves
    // the openness instead of silently dropping it (which would re-break the SIGSEGV path).
    open fun createTween(): Tween? =
        IosGodot.nodeCreateTween(handle.address()).takeIf { it != 0L }?.let {
            Tween(MemorySegment.ofAddress(it))
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
        fun printOrphanNodes() {
            ObjectCalls.ptrcallNoArgs(printOrphanNodesBind, MemorySegment.NULL)
        }

        fun getOrphanNodeIds(): List<Long> {
            return ObjectCalls.ptrcallNoArgsRetLongList(getOrphanNodeIdsBind, MemorySegment.NULL)
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

        fun fromHandle(handle: MemorySegment): Node? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Node? =
            if (handle.address() == 0L) null else Node(handle)

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

        private const val NOTIFY_DEFERRED_THREAD_GROUP_HASH = 1286410249L
        private val notifyDeferredThreadGroupBind by lazy {
            ObjectCalls.getMethodBind("Node", "notify_deferred_thread_group", NOTIFY_DEFERRED_THREAD_GROUP_HASH)
        }

        private const val CALL_THREAD_SAFE_HASH = 3400424181L
        private val callThreadSafeBind by lazy {
            ObjectCalls.getMethodBind("Node", "call_thread_safe", CALL_THREAD_SAFE_HASH)
        }

        private const val NOTIFY_THREAD_SAFE_HASH = 1286410249L
        private val notifyThreadSafeBind by lazy {
            ObjectCalls.getMethodBind("Node", "notify_thread_safe", NOTIFY_THREAD_SAFE_HASH)
        }
    }
}

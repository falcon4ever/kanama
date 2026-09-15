package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3
import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName

/**
 * Base object in 3D space, inherited by all 3D nodes.
 *
 * Generated from Godot docs: Node3D
 */
open class Node3D(handle: GodotHandle) : Node(handle) {
    var transform: Transform3D
        @JvmName("transformProperty")
        get() = getTransform()
        @JvmName("setTransformProperty")
        set(value) = setTransform(value)

    var globalTransform: Transform3D
        @JvmName("globalTransformProperty")
        get() = getGlobalTransform()
        @JvmName("setGlobalTransformProperty")
        set(value) = setGlobalTransform(value)

    var position: Vector3
        @JvmName("positionProperty")
        get() = getPosition()
        @JvmName("setPositionProperty")
        set(value) = setPosition(value)

    var rotation: Vector3
        @JvmName("rotationProperty")
        get() = getRotation()
        @JvmName("setRotationProperty")
        set(value) = setRotation(value)

    var rotationDegrees: Vector3
        @JvmName("rotationDegreesProperty")
        get() = getRotationDegrees()
        @JvmName("setRotationDegreesProperty")
        set(value) = setRotationDegrees(value)

    var quaternion: Quaternion
        @JvmName("quaternionProperty")
        get() = getQuaternion()
        @JvmName("setQuaternionProperty")
        set(value) = setQuaternion(value)

    var basis: Basis
        @JvmName("basisProperty")
        get() = getBasis()
        @JvmName("setBasisProperty")
        set(value) = setBasis(value)

    var scale: Vector3
        @JvmName("scaleProperty")
        get() = getScale()
        @JvmName("setScaleProperty")
        set(value) = setScale(value)

    var rotationEditMode: Long
        @JvmName("rotationEditModeProperty")
        get() = getRotationEditMode()
        @JvmName("setRotationEditModeProperty")
        set(value) = setRotationEditMode(value)

    var rotationOrder: Long
        @JvmName("rotationOrderProperty")
        get() = getRotationOrder()
        @JvmName("setRotationOrderProperty")
        set(value) = setRotationOrder(value)

    var topLevel: Boolean
        @JvmName("topLevelProperty")
        get() = isSetAsTopLevel()
        @JvmName("setTopLevelProperty")
        set(value) = setAsTopLevel(value)

    var globalPosition: Vector3
        @JvmName("globalPositionProperty")
        get() = getGlobalPosition()
        @JvmName("setGlobalPositionProperty")
        set(value) = setGlobalPosition(value)

    var globalBasis: Basis
        @JvmName("globalBasisProperty")
        get() = getGlobalBasis()
        @JvmName("setGlobalBasisProperty")
        set(value) = setGlobalBasis(value)

    var globalRotation: Vector3
        @JvmName("globalRotationProperty")
        get() = getGlobalRotation()
        @JvmName("setGlobalRotationProperty")
        set(value) = setGlobalRotation(value)

    var globalRotationDegrees: Vector3
        @JvmName("globalRotationDegreesProperty")
        get() = getGlobalRotationDegrees()
        @JvmName("setGlobalRotationDegreesProperty")
        set(value) = setGlobalRotationDegrees(value)

    var visible: Boolean
        @JvmName("visibleProperty")
        get() = isVisible()
        @JvmName("setVisibleProperty")
        set(value) = setVisible(value)

    var visibilityParent: NodePath
        @JvmName("visibilityParentProperty")
        get() = getVisibilityParent()
        @JvmName("setVisibilityParentProperty")
        set(value) = setVisibilityParent(value)

    /**
     * The local transformation of this node, in parent space (relative to the parent node). Contains
     * and represents this node's `position`, `rotation`, and `scale`.
     *
     * Generated from Godot docs: Node3D.set_transform
     */
    fun setTransform(local: Transform3D) {
        ObjectCalls.ptrcallWithTransform3DArg(setTransformBind, segment, local)
    }

    /**
     * The local transformation of this node, in parent space (relative to the parent node). Contains
     * and represents this node's `position`, `rotation`, and `scale`.
     *
     * Generated from Godot docs: Node3D.get_transform
     */
    fun getTransform(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getTransformBind, segment)
    }

    /**
     * Position (translation) of this node in parent space (relative to the parent node). This is
     * equivalent to the `transform`'s `Transform3D.origin`.
     *
     * Generated from Godot docs: Node3D.set_position
     */
    fun setPosition(position: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setPositionBind, segment, position)
    }

    /**
     * Position (translation) of this node in parent space (relative to the parent node). This is
     * equivalent to the `transform`'s `Transform3D.origin`.
     *
     * Generated from Godot docs: Node3D.get_position
     */
    fun getPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getPositionBind, segment)
    }

    /**
     * Rotation of this node as Euler angles (https://en.wikipedia.org/wiki/Euler_angles), in radians
     * and in parent space (relative to the parent node). This value is obtained from `basis`'s
     * rotation. - The `Vector3.x` is the angle around the local X axis (pitch); - The `Vector3.y` is
     * the angle around the local Y axis (yaw); - The `Vector3.z` is the angle around the local Z axis
     * (roll). The order of each consecutive rotation can be changed with `rotation_order` (see
     * `EulerOrder` constants). In Godot, Euler angles always use intrinsic order. By default, the
     * intrinsic YXZ convention is used (`EULER_ORDER_YXZ`). Note: This property is edited in degrees
     * in the inspector. If you want to use degrees in a script, use `rotation_degrees`.
     *
     * Generated from Godot docs: Node3D.set_rotation
     */
    fun setRotation(eulerRadians: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setRotationBind, segment, eulerRadians)
    }

    /**
     * Rotation of this node as Euler angles (https://en.wikipedia.org/wiki/Euler_angles), in radians
     * and in parent space (relative to the parent node). This value is obtained from `basis`'s
     * rotation. - The `Vector3.x` is the angle around the local X axis (pitch); - The `Vector3.y` is
     * the angle around the local Y axis (yaw); - The `Vector3.z` is the angle around the local Z axis
     * (roll). The order of each consecutive rotation can be changed with `rotation_order` (see
     * `EulerOrder` constants). In Godot, Euler angles always use intrinsic order. By default, the
     * intrinsic YXZ convention is used (`EULER_ORDER_YXZ`). Note: This property is edited in degrees
     * in the inspector. If you want to use degrees in a script, use `rotation_degrees`.
     *
     * Generated from Godot docs: Node3D.get_rotation
     */
    fun getRotation(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getRotationBind, segment)
    }

    /**
     * The `rotation` of this node, in degrees instead of radians. Note: This is not the property
     * available in the Inspector dock.
     *
     * Generated from Godot docs: Node3D.set_rotation_degrees
     */
    fun setRotationDegrees(eulerDegrees: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setRotationDegreesBind, segment, eulerDegrees)
    }

    /**
     * The `rotation` of this node, in degrees instead of radians. Note: This is not the property
     * available in the Inspector dock.
     *
     * Generated from Godot docs: Node3D.get_rotation_degrees
     */
    fun getRotationDegrees(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getRotationDegreesBind, segment)
    }

    /**
     * The axis rotation order of the `rotation` property. In Godot, Euler angles always use intrinsic
     * order, meaning that the final orientation is calculated by rotating around the local axes in
     * this order.
     *
     * Generated from Godot docs: Node3D.set_rotation_order
     */
    fun setRotationOrder(order: Long) {
        ObjectCalls.ptrcallWithLongArg(setRotationOrderBind, segment, order)
    }

    /**
     * The axis rotation order of the `rotation` property. In Godot, Euler angles always use intrinsic
     * order, meaning that the final orientation is calculated by rotating around the local axes in
     * this order.
     *
     * Generated from Godot docs: Node3D.get_rotation_order
     */
    fun getRotationOrder(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getRotationOrderBind, segment)
    }

    /**
     * How this node's rotation and scale are displayed in the Inspector dock.
     *
     * Generated from Godot docs: Node3D.set_rotation_edit_mode
     */
    fun setRotationEditMode(editMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setRotationEditModeBind, segment, editMode)
    }

    /**
     * How this node's rotation and scale are displayed in the Inspector dock.
     *
     * Generated from Godot docs: Node3D.get_rotation_edit_mode
     */
    fun getRotationEditMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getRotationEditModeBind, segment)
    }

    /**
     * Scale of this node in local space (relative to this node). This value is obtained from `basis`'s
     * scale. Note: The behavior of some 3D node types is not affected by this property. These include
     * `Light3D`, `Camera3D`, `AudioStreamPlayer3D`, and more. Warning: The scale's components must
     * either be all positive or all negative, and not exactly `0.0`. Otherwise, it won't be possible
     * to obtain the scale from the `basis`. This may cause the intended scale to be lost when reloaded
     * from disk, and potentially other unstable behavior.
     *
     * Generated from Godot docs: Node3D.set_scale
     */
    fun setScale(scale: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setScaleBind, segment, scale)
    }

    /**
     * Scale of this node in local space (relative to this node). This value is obtained from `basis`'s
     * scale. Note: The behavior of some 3D node types is not affected by this property. These include
     * `Light3D`, `Camera3D`, `AudioStreamPlayer3D`, and more. Warning: The scale's components must
     * either be all positive or all negative, and not exactly `0.0`. Otherwise, it won't be possible
     * to obtain the scale from the `basis`. This may cause the intended scale to be lost when reloaded
     * from disk, and potentially other unstable behavior.
     *
     * Generated from Godot docs: Node3D.get_scale
     */
    fun getScale(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getScaleBind, segment)
    }

    /**
     * Rotation of this node represented as a `Quaternion` in parent space (relative to the parent
     * node). This value is obtained from `basis`'s rotation. Note: Quaternions are much more suitable
     * for 3D math but are less intuitive. Setting this property can be useful for interpolation (see
     * `Quaternion.slerp`).
     *
     * Generated from Godot docs: Node3D.set_quaternion
     */
    fun setQuaternion(quaternion: Quaternion) {
        ObjectCalls.ptrcallWithQuaternionArg(setQuaternionBind, segment, quaternion)
    }

    /**
     * Rotation of this node represented as a `Quaternion` in parent space (relative to the parent
     * node). This value is obtained from `basis`'s rotation. Note: Quaternions are much more suitable
     * for 3D math but are less intuitive. Setting this property can be useful for interpolation (see
     * `Quaternion.slerp`).
     *
     * Generated from Godot docs: Node3D.get_quaternion
     */
    fun getQuaternion(): Quaternion {
        return ObjectCalls.ptrcallNoArgsRetQuaternion(getQuaternionBind, segment)
    }

    /**
     * Basis of the `transform` property. Represents the rotation, scale, and shear of this node in
     * parent space (relative to the parent node).
     *
     * Generated from Godot docs: Node3D.set_basis
     */
    fun setBasis(basis: Basis) {
        ObjectCalls.ptrcallWithBasisArg(setBasisBind, segment, basis)
    }

    /**
     * Basis of the `transform` property. Represents the rotation, scale, and shear of this node in
     * parent space (relative to the parent node).
     *
     * Generated from Godot docs: Node3D.get_basis
     */
    fun getBasis(): Basis {
        return ObjectCalls.ptrcallNoArgsRetBasis(getBasisBind, segment)
    }

    /**
     * The transformation of this node, in global space (relative to the world). Contains and
     * represents this node's `global_position`, `global_rotation`, and global scale. Note: If the node
     * is not inside the tree, getting this property fails and returns `Transform3D.IDENTITY`.
     *
     * Generated from Godot docs: Node3D.set_global_transform
     */
    fun setGlobalTransform(global: Transform3D) {
        ObjectCalls.ptrcallWithTransform3DArg(setGlobalTransformBind, segment, global)
    }

    /**
     * The transformation of this node, in global space (relative to the world). Contains and
     * represents this node's `global_position`, `global_rotation`, and global scale. Note: If the node
     * is not inside the tree, getting this property fails and returns `Transform3D.IDENTITY`.
     *
     * Generated from Godot docs: Node3D.get_global_transform
     */
    fun getGlobalTransform(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getGlobalTransformBind, segment)
    }

    /**
     * When using physics interpolation, there will be circumstances in which you want to know the
     * interpolated (displayed) transform of a node rather than the standard transform (which may only
     * be accurate to the most recent physics tick). This is particularly important for frame-based
     * operations that take place in `Node._process`, rather than `Node._physics_process`. Examples
     * include `Camera3D`s focusing on a node, or finding where to fire lasers from on a frame rather
     * than physics tick. Note: This function creates an interpolation pump on the `Node3D` the first
     * time it is called, which can respond to physics interpolation resets. If you get problems with
     * "streaking" when initially following a `Node3D`, be sure to call
     * `get_global_transform_interpolated` at least once before resetting the `Node3D` physics
     * interpolation.
     *
     * Generated from Godot docs: Node3D.get_global_transform_interpolated
     */
    fun getGlobalTransformInterpolated(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getGlobalTransformInterpolatedBind, segment)
    }

    /**
     * Global position (translation) of this node in global space (relative to the world). This is
     * equivalent to the `global_transform`'s `Transform3D.origin`. Note: If the node is not inside the
     * tree, getting this property fails and returns `Vector3.ZERO`.
     *
     * Generated from Godot docs: Node3D.set_global_position
     */
    fun setGlobalPosition(position: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setGlobalPositionBind, segment, position)
    }

    /**
     * Global position (translation) of this node in global space (relative to the world). This is
     * equivalent to the `global_transform`'s `Transform3D.origin`. Note: If the node is not inside the
     * tree, getting this property fails and returns `Vector3.ZERO`.
     *
     * Generated from Godot docs: Node3D.get_global_position
     */
    fun getGlobalPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getGlobalPositionBind, segment)
    }

    /**
     * Basis of the `global_transform` property. Represents the rotation, scale, and shear of this node
     * in global space (relative to the world). Note: If the node is not inside the tree, getting this
     * property fails and returns `Basis.IDENTITY`.
     *
     * Generated from Godot docs: Node3D.set_global_basis
     */
    fun setGlobalBasis(basis: Basis) {
        ObjectCalls.ptrcallWithBasisArg(setGlobalBasisBind, segment, basis)
    }

    /**
     * Basis of the `global_transform` property. Represents the rotation, scale, and shear of this node
     * in global space (relative to the world). Note: If the node is not inside the tree, getting this
     * property fails and returns `Basis.IDENTITY`.
     *
     * Generated from Godot docs: Node3D.get_global_basis
     */
    fun getGlobalBasis(): Basis {
        return ObjectCalls.ptrcallNoArgsRetBasis(getGlobalBasisBind, segment)
    }

    /**
     * Global rotation of this node as Euler angles (https://en.wikipedia.org/wiki/Euler_angles), in
     * radians and in global space (relative to the world). This value is obtained from
     * `global_basis`'s rotation. - The `Vector3.x` is the angle around the global X axis (pitch); -
     * The `Vector3.y` is the angle around the global Y axis (yaw); - The `Vector3.z` is the angle
     * around the global Z axis (roll). Note: Unlike `rotation`, this property always follows the YXZ
     * convention (`EULER_ORDER_YXZ`). Note: If the node is not inside the tree, getting this property
     * fails and returns `Vector3.ZERO`.
     *
     * Generated from Godot docs: Node3D.set_global_rotation
     */
    fun setGlobalRotation(eulerRadians: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setGlobalRotationBind, segment, eulerRadians)
    }

    /**
     * Global rotation of this node as Euler angles (https://en.wikipedia.org/wiki/Euler_angles), in
     * radians and in global space (relative to the world). This value is obtained from
     * `global_basis`'s rotation. - The `Vector3.x` is the angle around the global X axis (pitch); -
     * The `Vector3.y` is the angle around the global Y axis (yaw); - The `Vector3.z` is the angle
     * around the global Z axis (roll). Note: Unlike `rotation`, this property always follows the YXZ
     * convention (`EULER_ORDER_YXZ`). Note: If the node is not inside the tree, getting this property
     * fails and returns `Vector3.ZERO`.
     *
     * Generated from Godot docs: Node3D.get_global_rotation
     */
    fun getGlobalRotation(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getGlobalRotationBind, segment)
    }

    /**
     * The `global_rotation` of this node, in degrees instead of radians. Note: If the node is not
     * inside the tree, getting this property fails and returns `Vector3.ZERO`.
     *
     * Generated from Godot docs: Node3D.set_global_rotation_degrees
     */
    fun setGlobalRotationDegrees(eulerDegrees: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setGlobalRotationDegreesBind, segment, eulerDegrees)
    }

    /**
     * The `global_rotation` of this node, in degrees instead of radians. Note: If the node is not
     * inside the tree, getting this property fails and returns `Vector3.ZERO`.
     *
     * Generated from Godot docs: Node3D.get_global_rotation_degrees
     */
    fun getGlobalRotationDegrees(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getGlobalRotationDegreesBind, segment)
    }

    /**
     * Returns the parent `Node3D` that directly affects this node's `global_transform`. Returns `null`
     * if no parent exists, the parent is not a `Node3D`, or `top_level` is `true`. Note: This method
     * is not always equivalent to `Node.get_parent`, which does not take `top_level` into account.
     *
     * Generated from Godot docs: Node3D.get_parent_node_3d
     */
    fun getParentNode3d(): Node3D? {
        return Node3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getParentNode3dBind, segment))
    }

    /**
     * If `true`, the node will not receive `NOTIFICATION_TRANSFORM_CHANGED` or
     * `NOTIFICATION_LOCAL_TRANSFORM_CHANGED`. It may useful to call this method when handling these
     * notifications to prevent infinite recursion.
     *
     * Generated from Godot docs: Node3D.set_ignore_transform_notification
     */
    fun setIgnoreTransformNotification(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIgnoreTransformNotificationBind, segment, enabled)
    }

    /**
     * If `true`, the node does not inherit its transformations from its parent. As such, node
     * transformations will only be in global space, which also means that `global_transform` and
     * `transform` will be identical.
     *
     * Generated from Godot docs: Node3D.set_as_top_level
     */
    fun setAsTopLevel(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAsTopLevelBind, segment, enable)
    }

    /**
     * If `true`, the node does not inherit its transformations from its parent. As such, node
     * transformations will only be in global space, which also means that `global_transform` and
     * `transform` will be identical.
     *
     * Generated from Godot docs: Node3D.is_set_as_top_level
     */
    fun isSetAsTopLevel(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSetAsTopLevelBind, segment)
    }

    /**
     * If `true`, this node's `global_transform` is automatically orthonormalized. This results in this
     * node not appearing distorted, as if its global scale were set to `Vector3.ONE` (or its negative
     * counterpart). See also `is_scale_disabled` and `orthonormalize`. Note: `transform` is not
     * affected by this setting.
     *
     * Generated from Godot docs: Node3D.set_disable_scale
     */
    fun setDisableScale(disable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDisableScaleBind, segment, disable)
    }

    /**
     * Returns `true` if this node's `global_transform` is automatically orthonormalized. This results
     * in this node not appearing distorted, as if its global scale were set to `Vector3.ONE` (or its
     * negative counterpart). See also `set_disable_scale` and `orthonormalize`. Note: `transform` is
     * not affected by this setting.
     *
     * Generated from Godot docs: Node3D.is_scale_disabled
     */
    fun isScaleDisabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isScaleDisabledBind, segment)
    }

    /**
     * Returns the `World3D` this node is registered to. Usually, this is the same as the world used by
     * this node's viewport (see `Node.get_viewport` and `Viewport.find_world_3d`).
     *
     * Generated from Godot docs: Node3D.get_world_3d
     */
    fun getWorld3d(): World3D? {
        return World3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getWorld3dBind, segment))
    }

    /**
     * Forces the node's `global_transform` to update, by sending `NOTIFICATION_TRANSFORM_CHANGED`.
     * Fails if the node is not inside the tree. Note: For performance reasons, transform changes are
     * usually accumulated and applied once at the end of the frame. The update propagates through
     * `Node3D` children, as well. Therefore, use this method only when you need an up-to-date
     * transform (such as during physics operations).
     *
     * Generated from Godot docs: Node3D.force_update_transform
     */
    fun forceUpdateTransform() {
        ObjectCalls.ptrcallNoArgs(forceUpdateTransformBind, segment)
    }

    /**
     * Path to the visibility range parent for this node and its descendants. The visibility parent
     * must be a `GeometryInstance3D`. Any visual instance will only be visible if the visibility
     * parent (and all of its visibility ancestors) is hidden by being closer to the camera than its
     * own `GeometryInstance3D.visibility_range_begin`. Nodes hidden via the `Node3D.visible` property
     * are essentially removed from the visibility dependency tree, so dependent instances will not
     * take the hidden node or its descendants into account.
     *
     * Generated from Godot docs: Node3D.set_visibility_parent
     */
    fun setVisibilityParent(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setVisibilityParentBind, segment, path)
    }

    /**
     * Path to the visibility range parent for this node and its descendants. The visibility parent
     * must be a `GeometryInstance3D`. Any visual instance will only be visible if the visibility
     * parent (and all of its visibility ancestors) is hidden by being closer to the camera than its
     * own `GeometryInstance3D.visibility_range_begin`. Nodes hidden via the `Node3D.visible` property
     * are essentially removed from the visibility dependency tree, so dependent instances will not
     * take the hidden node or its descendants into account.
     *
     * Generated from Godot docs: Node3D.get_visibility_parent
     */
    fun getVisibilityParent(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getVisibilityParentBind, segment)
    }

    /**
     * Updates all the `EditorNode3DGizmo` objects attached to this node. Only works in the editor.
     *
     * Generated from Godot docs: Node3D.update_gizmos
     */
    fun updateGizmos() {
        ObjectCalls.ptrcallNoArgs(updateGizmosBind, segment)
    }

    /**
     * Attaches the given `gizmo` to this node. Only works in the editor. Note: `gizmo` should be an
     * `EditorNode3DGizmo`. The argument type is `Node3DGizmo` to avoid depending on editor classes in
     * `Node3D`.
     *
     * Generated from Godot docs: Node3D.add_gizmo
     */
    fun addGizmo(gizmo: Node3DGizmo?) {
        ObjectCalls.ptrcallWithObjectArgs(addGizmoBind, segment, listOf(gizmo?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Returns all the `EditorNode3DGizmo` objects attached to this node. Only works in the editor.
     *
     * Generated from Godot docs: Node3D.get_gizmos
     */
    fun getGizmos(): List<Node3DGizmo> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getGizmosBind, segment, Node3DGizmo::wrap)
    }

    /**
     * Clears all `EditorNode3DGizmo` objects attached to this node. Only works in the editor.
     *
     * Generated from Godot docs: Node3D.clear_gizmos
     */
    fun clearGizmos() {
        ObjectCalls.ptrcallNoArgs(clearGizmosBind, segment)
    }

    /**
     * Selects the `gizmo`'s subgizmo with the given `id` and sets its transform. Only works in the
     * editor. Note: The gizmo object would typically be an instance of `EditorNode3DGizmo`, but the
     * argument type is kept generic to avoid creating a dependency on editor classes in `Node3D`.
     *
     * Generated from Godot docs: Node3D.set_subgizmo_selection
     */
    fun setSubgizmoSelection(gizmo: Node3DGizmo?, id: Int, transform: Transform3D) {
        ObjectCalls.ptrcallWithObjectIntTransform3DArgs(setSubgizmoSelectionBind, segment, gizmo?.requireOpenHandle() ?: MemorySegment.NULL, id, transform)
    }

    /**
     * Deselects all subgizmos for this node. Useful to call when the selected subgizmo may no longer
     * exist after a property change. Only works in the editor.
     *
     * Generated from Godot docs: Node3D.clear_subgizmo_selection
     */
    fun clearSubgizmoSelection() {
        ObjectCalls.ptrcallNoArgs(clearSubgizmoSelectionBind, segment)
    }

    /**
     * If `true`, this node can be visible. The node is only rendered when all of its ancestors are
     * visible, as well. That means `is_visible_in_tree` must return `true`.
     *
     * Generated from Godot docs: Node3D.set_visible
     */
    fun setVisible(visible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVisibleBind, segment, visible)
    }

    /**
     * If `true`, this node can be visible. The node is only rendered when all of its ancestors are
     * visible, as well. That means `is_visible_in_tree` must return `true`.
     *
     * Generated from Godot docs: Node3D.is_visible
     */
    fun isVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVisibleBind, segment)
    }

    /**
     * Returns `true` if this node is inside the scene tree and the `visible` property is `true` for
     * this node and all of its `Node3D` ancestors in sequence. An ancestor of any other type (such as
     * `Node` or `Node2D`) breaks the sequence. See also `Node.get_parent`. Note: This method cannot
     * take `VisualInstance3D.layers` into account, so even if this method returns `true`, the node may
     * not be rendered.
     *
     * Generated from Godot docs: Node3D.is_visible_in_tree
     */
    fun isVisibleInTree(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVisibleInTreeBind, segment)
    }

    /**
     * Allows this node to be rendered. Equivalent to setting `visible` to `true`. This is the opposite
     * of `hide`.
     *
     * Generated from Godot docs: Node3D.show
     */
    fun show() {
        ObjectCalls.ptrcallNoArgs(showBind, segment)
    }

    /**
     * Prevents this node from being rendered. Equivalent to setting `visible` to `false`. This is the
     * opposite of `show`.
     *
     * Generated from Godot docs: Node3D.hide
     */
    fun hide() {
        ObjectCalls.ptrcallNoArgs(hideBind, segment)
    }

    /**
     * If `true`, the node will receive `NOTIFICATION_LOCAL_TRANSFORM_CHANGED` whenever `transform`
     * changes. Note: Some 3D nodes such as `CSGShape3D` or `CollisionShape3D` automatically enable
     * this to function correctly.
     *
     * Generated from Godot docs: Node3D.set_notify_local_transform
     */
    fun setNotifyLocalTransform(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setNotifyLocalTransformBind, segment, enable)
    }

    /**
     * Returns `true` if the node receives `NOTIFICATION_LOCAL_TRANSFORM_CHANGED` whenever `transform`
     * changes. This is enabled with `set_notify_local_transform`.
     *
     * Generated from Godot docs: Node3D.is_local_transform_notification_enabled
     */
    fun isLocalTransformNotificationEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isLocalTransformNotificationEnabledBind, segment)
    }

    /**
     * If `true`, the node will receive `NOTIFICATION_TRANSFORM_CHANGED` whenever `global_transform`
     * changes. Note: Most 3D nodes such as `VisualInstance3D` or `CollisionObject3D` automatically
     * enable this to function correctly. Note: In the editor, nodes will propagate this notification
     * to their children if a gizmo is attached (see `add_gizmo`).
     *
     * Generated from Godot docs: Node3D.set_notify_transform
     */
    fun setNotifyTransform(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setNotifyTransformBind, segment, enable)
    }

    /**
     * Returns `true` if the node receives `NOTIFICATION_TRANSFORM_CHANGED` whenever `global_transform`
     * changes. This is enabled with `set_notify_transform`.
     *
     * Generated from Godot docs: Node3D.is_transform_notification_enabled
     */
    fun isTransformNotificationEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isTransformNotificationEnabledBind, segment)
    }

    /**
     * Rotates this node's `basis` around the `axis` by the given `angle`, in radians. This operation
     * is calculated in parent space (relative to the parent) and preserves the `position`.
     *
     * Generated from Godot docs: Node3D.rotate
     */
    fun rotate(axis: Vector3, angle: Double) {
        ObjectCalls.ptrcallWithVector3AndDoubleArg(rotateBind, segment, axis, angle)
    }

    /**
     * Rotates this node's `global_basis` around the global `axis` by the given `angle`, in radians.
     * This operation is calculated in global space (relative to the world) and preserves the
     * `global_position`.
     *
     * Generated from Godot docs: Node3D.global_rotate
     */
    fun globalRotate(axis: Vector3, angle: Double) {
        ObjectCalls.ptrcallWithVector3AndDoubleArg(globalRotateBind, segment, axis, angle)
    }

    /**
     * Scales this node's `global_basis` by the given `scale` factor. This operation is calculated in
     * global space (relative to the world) and preserves the `global_position`. Note: This method is
     * not to be confused with the `scale` property.
     *
     * Generated from Godot docs: Node3D.global_scale
     */
    fun globalScale(scale: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(globalScaleBind, segment, scale)
    }

    /**
     * Adds the given translation `offset` to the node's `global_position` in global space (relative to
     * the world).
     *
     * Generated from Godot docs: Node3D.global_translate
     */
    fun globalTranslate(offset: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(globalTranslateBind, segment, offset)
    }

    /**
     * Rotates this node's `basis` around the `axis` by the given `angle`, in radians. This operation
     * is calculated in local space (relative to this node) and preserves the `position`.
     *
     * Generated from Godot docs: Node3D.rotate_object_local
     */
    fun rotateObjectLocal(axis: Vector3, angle: Double) {
        ObjectCalls.ptrcallWithVector3AndDoubleArg(rotateObjectLocalBind, segment, axis, angle)
    }

    /**
     * Scales this node's `basis` by the given `scale` factor. This operation is calculated in local
     * space (relative to this node) and preserves the `position`.
     *
     * Generated from Godot docs: Node3D.scale_object_local
     */
    fun scaleObjectLocal(scale: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(scaleObjectLocalBind, segment, scale)
    }

    /**
     * Adds the given translation `offset` to the node's position, in local space (relative to this
     * node).
     *
     * Generated from Godot docs: Node3D.translate_object_local
     */
    fun translateObjectLocal(offset: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(translateObjectLocalBind, segment, offset)
    }

    /**
     * Rotates this node's `basis` around the X axis by the given `angle`, in radians. This operation
     * is calculated in parent space (relative to the parent) and preserves the `position`.
     *
     * Generated from Godot docs: Node3D.rotate_x
     */
    fun rotateX(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(rotateXBind, segment, angle)
    }

    /**
     * Rotates this node's `basis` around the Y axis by the given `angle`, in radians. This operation
     * is calculated in parent space (relative to the parent) and preserves the `position`.
     *
     * Generated from Godot docs: Node3D.rotate_y
     */
    fun rotateY(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(rotateYBind, segment, angle)
    }

    /**
     * Rotates this node's `basis` around the Z axis by the given `angle`, in radians. This operation
     * is calculated in parent space (relative to the parent) and preserves the `position`.
     *
     * Generated from Godot docs: Node3D.rotate_z
     */
    fun rotateZ(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(rotateZBind, segment, angle)
    }

    /**
     * Adds the given translation `offset` to the node's position, in local space (relative to this
     * node). Note: Prefer using `translate_object_local`, instead, as this method may be changed in a
     * future release. Note: Despite the naming convention, this operation is not calculated in parent
     * space for compatibility reasons. To translate in parent space, add `offset` to the `position`
     * (`node_3d.position += offset`).
     *
     * Generated from Godot docs: Node3D.translate
     */
    fun translate(offset: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(translateBind, segment, offset)
    }

    /**
     * Orthonormalizes this node's `basis`. This method sets this node's `scale` to `Vector3.ONE` (or
     * its negative counterpart), but preserves the `position` and `rotation`. See also
     * `Transform3D.orthonormalized`.
     *
     * Generated from Godot docs: Node3D.orthonormalize
     */
    fun orthonormalize() {
        ObjectCalls.ptrcallNoArgs(orthonormalizeBind, segment)
    }

    /**
     * Sets this node's `transform` to `Transform3D.IDENTITY`, which resets all transformations in
     * parent space (`position`, `rotation`, and `scale`).
     *
     * Generated from Godot docs: Node3D.set_identity
     */
    fun setIdentity() {
        ObjectCalls.ptrcallNoArgs(setIdentityBind, segment)
    }

    /**
     * Rotates the node so that the local forward axis (-Z, `Vector3.FORWARD`) points toward the
     * `target` position. This operation is calculated in global space (relative to the world). The
     * local up axis (+Y) points as close to the `up` vector as possible while staying perpendicular to
     * the local forward axis. The resulting transform is orthogonal, and the scale is preserved.
     * Non-uniform scaling may not work correctly. The `target` position cannot be the same as the
     * node's position, the `up` vector cannot be `Vector3.ZERO`. Furthermore, the direction from the
     * node's position to the `target` position cannot be parallel to the `up` vector, to avoid an
     * unintended rotation around the local Z axis. If `use_model_front` is `true`, the +Z axis (asset
     * front) is treated as forward (implies +X is left) and points toward the `target` position. By
     * default, the -Z axis (camera forward) is treated as forward (implies +X is right). Note: This
     * method fails if the node is not in the scene tree. If necessary, use `look_at_from_position`
     * instead.
     *
     * Generated from Godot docs: Node3D.look_at
     */
    fun lookAt(target: Vector3, up: Vector3 = Vector3.UP, useModelFront: Boolean = false) {
        ObjectCalls.ptrcallWithTwoVector3AndBoolArgs(lookAtBind, segment, target, up, useModelFront)
    }

    /**
     * Moves the node to the specified `position`, then rotates the node to point toward the `target`
     * position, similar to `look_at`. This operation is calculated in global space (relative to the
     * world).
     *
     * Generated from Godot docs: Node3D.look_at_from_position
     */
    fun lookAtFromPosition(
        position: Vector3,
        target: Vector3,
        up: Vector3 = Vector3.UP,
        useModelFront: Boolean = false,
    ) {
        ObjectCalls.ptrcallWithThreeVector3AndBoolArgs(lookAtFromPositionBind, segment, position, target, up, useModelFront)
    }

    /**
     * Returns the `global_point` converted from global space to this node's local space. This is the
     * opposite of `to_global`.
     *
     * Generated from Godot docs: Node3D.to_local
     */
    fun toLocal(globalPoint: Vector3): Vector3 {
        return ObjectCalls.ptrcallWithVector3ArgRetVector3(toLocalBind, segment, globalPoint)
    }

    /**
     * Returns the `local_point` converted from this node's local space to global space. This is the
     * opposite of `to_local`.
     *
     * Generated from Godot docs: Node3D.to_global
     */
    fun toGlobal(localPoint: Vector3): Vector3 {
        return ObjectCalls.ptrcallWithVector3ArgRetVector3(toGlobalBind, segment, localPoint)
    }

    object Signals {
        const val visibilityChanged: String = "visibility_changed"
    }

    companion object {
        const val NOTIFICATION_TRANSFORM_CHANGED: Long = 2000L
        const val NOTIFICATION_ENTER_WORLD: Long = 41L
        const val NOTIFICATION_EXIT_WORLD: Long = 42L
        const val NOTIFICATION_VISIBILITY_CHANGED: Long = 43L
        const val NOTIFICATION_LOCAL_TRANSFORM_CHANGED: Long = 44L
        const val ROTATION_EDIT_MODE_EULER: Long = 0L
        const val ROTATION_EDIT_MODE_QUATERNION: Long = 1L
        const val ROTATION_EDIT_MODE_BASIS: Long = 2L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): Node3D? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): Node3D? =
            if (handle.address() == 0L) null else Node3D(GodotHandle(handle))

        private const val SET_TRANSFORM_HASH = 2952846383L
        private val setTransformBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "set_transform", SET_TRANSFORM_HASH)
        }

        private const val GET_TRANSFORM_HASH = 3229777777L
        private val getTransformBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "get_transform", GET_TRANSFORM_HASH)
        }

        private const val SET_POSITION_HASH = 3460891852L
        private val setPositionBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "set_position", SET_POSITION_HASH)
        }

        private const val GET_POSITION_HASH = 3360562783L
        private val getPositionBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "get_position", GET_POSITION_HASH)
        }

        private const val SET_ROTATION_HASH = 3460891852L
        private val setRotationBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "set_rotation", SET_ROTATION_HASH)
        }

        private const val GET_ROTATION_HASH = 3360562783L
        private val getRotationBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "get_rotation", GET_ROTATION_HASH)
        }

        private const val SET_ROTATION_DEGREES_HASH = 3460891852L
        private val setRotationDegreesBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "set_rotation_degrees", SET_ROTATION_DEGREES_HASH)
        }

        private const val GET_ROTATION_DEGREES_HASH = 3360562783L
        private val getRotationDegreesBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "get_rotation_degrees", GET_ROTATION_DEGREES_HASH)
        }

        private const val SET_ROTATION_ORDER_HASH = 1820889989L
        private val setRotationOrderBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "set_rotation_order", SET_ROTATION_ORDER_HASH)
        }

        private const val GET_ROTATION_ORDER_HASH = 916939469L
        private val getRotationOrderBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "get_rotation_order", GET_ROTATION_ORDER_HASH)
        }

        private const val SET_ROTATION_EDIT_MODE_HASH = 141483330L
        private val setRotationEditModeBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "set_rotation_edit_mode", SET_ROTATION_EDIT_MODE_HASH)
        }

        private const val GET_ROTATION_EDIT_MODE_HASH = 1572188370L
        private val getRotationEditModeBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "get_rotation_edit_mode", GET_ROTATION_EDIT_MODE_HASH)
        }

        private const val SET_SCALE_HASH = 3460891852L
        private val setScaleBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "set_scale", SET_SCALE_HASH)
        }

        private const val GET_SCALE_HASH = 3360562783L
        private val getScaleBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "get_scale", GET_SCALE_HASH)
        }

        private const val SET_QUATERNION_HASH = 1727505552L
        private val setQuaternionBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "set_quaternion", SET_QUATERNION_HASH)
        }

        private const val GET_QUATERNION_HASH = 1222331677L
        private val getQuaternionBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "get_quaternion", GET_QUATERNION_HASH)
        }

        private const val SET_BASIS_HASH = 1055510324L
        private val setBasisBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "set_basis", SET_BASIS_HASH)
        }

        private const val GET_BASIS_HASH = 2716978435L
        private val getBasisBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "get_basis", GET_BASIS_HASH)
        }

        private const val SET_GLOBAL_TRANSFORM_HASH = 2952846383L
        private val setGlobalTransformBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "set_global_transform", SET_GLOBAL_TRANSFORM_HASH)
        }

        private const val GET_GLOBAL_TRANSFORM_HASH = 3229777777L
        private val getGlobalTransformBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "get_global_transform", GET_GLOBAL_TRANSFORM_HASH)
        }

        private const val GET_GLOBAL_TRANSFORM_INTERPOLATED_HASH = 4183770049L
        private val getGlobalTransformInterpolatedBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "get_global_transform_interpolated", GET_GLOBAL_TRANSFORM_INTERPOLATED_HASH)
        }

        private const val SET_GLOBAL_POSITION_HASH = 3460891852L
        private val setGlobalPositionBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "set_global_position", SET_GLOBAL_POSITION_HASH)
        }

        private const val GET_GLOBAL_POSITION_HASH = 3360562783L
        private val getGlobalPositionBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "get_global_position", GET_GLOBAL_POSITION_HASH)
        }

        private const val SET_GLOBAL_BASIS_HASH = 1055510324L
        private val setGlobalBasisBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "set_global_basis", SET_GLOBAL_BASIS_HASH)
        }

        private const val GET_GLOBAL_BASIS_HASH = 2716978435L
        private val getGlobalBasisBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "get_global_basis", GET_GLOBAL_BASIS_HASH)
        }

        private const val SET_GLOBAL_ROTATION_HASH = 3460891852L
        private val setGlobalRotationBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "set_global_rotation", SET_GLOBAL_ROTATION_HASH)
        }

        private const val GET_GLOBAL_ROTATION_HASH = 3360562783L
        private val getGlobalRotationBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "get_global_rotation", GET_GLOBAL_ROTATION_HASH)
        }

        private const val SET_GLOBAL_ROTATION_DEGREES_HASH = 3460891852L
        private val setGlobalRotationDegreesBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "set_global_rotation_degrees", SET_GLOBAL_ROTATION_DEGREES_HASH)
        }

        private const val GET_GLOBAL_ROTATION_DEGREES_HASH = 3360562783L
        private val getGlobalRotationDegreesBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "get_global_rotation_degrees", GET_GLOBAL_ROTATION_DEGREES_HASH)
        }

        private const val GET_PARENT_NODE_3D_HASH = 151077316L
        private val getParentNode3dBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "get_parent_node_3d", GET_PARENT_NODE_3D_HASH)
        }

        private const val SET_IGNORE_TRANSFORM_NOTIFICATION_HASH = 2586408642L
        private val setIgnoreTransformNotificationBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "set_ignore_transform_notification", SET_IGNORE_TRANSFORM_NOTIFICATION_HASH)
        }

        private const val SET_AS_TOP_LEVEL_HASH = 2586408642L
        private val setAsTopLevelBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "set_as_top_level", SET_AS_TOP_LEVEL_HASH)
        }

        private const val IS_SET_AS_TOP_LEVEL_HASH = 36873697L
        private val isSetAsTopLevelBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "is_set_as_top_level", IS_SET_AS_TOP_LEVEL_HASH)
        }

        private const val SET_DISABLE_SCALE_HASH = 2586408642L
        private val setDisableScaleBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "set_disable_scale", SET_DISABLE_SCALE_HASH)
        }

        private const val IS_SCALE_DISABLED_HASH = 36873697L
        private val isScaleDisabledBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "is_scale_disabled", IS_SCALE_DISABLED_HASH)
        }

        private const val GET_WORLD_3D_HASH = 317588385L
        private val getWorld3dBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "get_world_3d", GET_WORLD_3D_HASH)
        }

        private const val FORCE_UPDATE_TRANSFORM_HASH = 3218959716L
        private val forceUpdateTransformBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "force_update_transform", FORCE_UPDATE_TRANSFORM_HASH)
        }

        private const val SET_VISIBILITY_PARENT_HASH = 1348162250L
        private val setVisibilityParentBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "set_visibility_parent", SET_VISIBILITY_PARENT_HASH)
        }

        private const val GET_VISIBILITY_PARENT_HASH = 4075236667L
        private val getVisibilityParentBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "get_visibility_parent", GET_VISIBILITY_PARENT_HASH)
        }

        private const val UPDATE_GIZMOS_HASH = 3218959716L
        private val updateGizmosBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "update_gizmos", UPDATE_GIZMOS_HASH)
        }

        private const val ADD_GIZMO_HASH = 1544533845L
        private val addGizmoBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "add_gizmo", ADD_GIZMO_HASH)
        }

        private const val GET_GIZMOS_HASH = 3995934104L
        private val getGizmosBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "get_gizmos", GET_GIZMOS_HASH)
        }

        private const val CLEAR_GIZMOS_HASH = 3218959716L
        private val clearGizmosBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "clear_gizmos", CLEAR_GIZMOS_HASH)
        }

        private const val SET_SUBGIZMO_SELECTION_HASH = 3317607635L
        private val setSubgizmoSelectionBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "set_subgizmo_selection", SET_SUBGIZMO_SELECTION_HASH)
        }

        private const val CLEAR_SUBGIZMO_SELECTION_HASH = 3218959716L
        private val clearSubgizmoSelectionBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "clear_subgizmo_selection", CLEAR_SUBGIZMO_SELECTION_HASH)
        }

        private const val SET_VISIBLE_HASH = 2586408642L
        private val setVisibleBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "set_visible", SET_VISIBLE_HASH)
        }

        private const val IS_VISIBLE_HASH = 36873697L
        private val isVisibleBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "is_visible", IS_VISIBLE_HASH)
        }

        private const val IS_VISIBLE_IN_TREE_HASH = 36873697L
        private val isVisibleInTreeBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "is_visible_in_tree", IS_VISIBLE_IN_TREE_HASH)
        }

        private const val SHOW_HASH = 3218959716L
        private val showBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "show", SHOW_HASH)
        }

        private const val HIDE_HASH = 3218959716L
        private val hideBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "hide", HIDE_HASH)
        }

        private const val SET_NOTIFY_LOCAL_TRANSFORM_HASH = 2586408642L
        private val setNotifyLocalTransformBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "set_notify_local_transform", SET_NOTIFY_LOCAL_TRANSFORM_HASH)
        }

        private const val IS_LOCAL_TRANSFORM_NOTIFICATION_ENABLED_HASH = 36873697L
        private val isLocalTransformNotificationEnabledBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "is_local_transform_notification_enabled", IS_LOCAL_TRANSFORM_NOTIFICATION_ENABLED_HASH)
        }

        private const val SET_NOTIFY_TRANSFORM_HASH = 2586408642L
        private val setNotifyTransformBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "set_notify_transform", SET_NOTIFY_TRANSFORM_HASH)
        }

        private const val IS_TRANSFORM_NOTIFICATION_ENABLED_HASH = 36873697L
        private val isTransformNotificationEnabledBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "is_transform_notification_enabled", IS_TRANSFORM_NOTIFICATION_ENABLED_HASH)
        }

        private const val ROTATE_HASH = 3436291937L
        private val rotateBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "rotate", ROTATE_HASH)
        }

        private const val GLOBAL_ROTATE_HASH = 3436291937L
        private val globalRotateBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "global_rotate", GLOBAL_ROTATE_HASH)
        }

        private const val GLOBAL_SCALE_HASH = 3460891852L
        private val globalScaleBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "global_scale", GLOBAL_SCALE_HASH)
        }

        private const val GLOBAL_TRANSLATE_HASH = 3460891852L
        private val globalTranslateBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "global_translate", GLOBAL_TRANSLATE_HASH)
        }

        private const val ROTATE_OBJECT_LOCAL_HASH = 3436291937L
        private val rotateObjectLocalBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "rotate_object_local", ROTATE_OBJECT_LOCAL_HASH)
        }

        private const val SCALE_OBJECT_LOCAL_HASH = 3460891852L
        private val scaleObjectLocalBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "scale_object_local", SCALE_OBJECT_LOCAL_HASH)
        }

        private const val TRANSLATE_OBJECT_LOCAL_HASH = 3460891852L
        private val translateObjectLocalBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "translate_object_local", TRANSLATE_OBJECT_LOCAL_HASH)
        }

        private const val ROTATE_X_HASH = 373806689L
        private val rotateXBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "rotate_x", ROTATE_X_HASH)
        }

        private const val ROTATE_Y_HASH = 373806689L
        private val rotateYBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "rotate_y", ROTATE_Y_HASH)
        }

        private const val ROTATE_Z_HASH = 373806689L
        private val rotateZBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "rotate_z", ROTATE_Z_HASH)
        }

        private const val TRANSLATE_HASH = 3460891852L
        private val translateBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "translate", TRANSLATE_HASH)
        }

        private const val ORTHONORMALIZE_HASH = 3218959716L
        private val orthonormalizeBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "orthonormalize", ORTHONORMALIZE_HASH)
        }

        private const val SET_IDENTITY_HASH = 3218959716L
        private val setIdentityBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "set_identity", SET_IDENTITY_HASH)
        }

        private const val LOOK_AT_HASH = 2882425029L
        private val lookAtBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "look_at", LOOK_AT_HASH)
        }

        private const val LOOK_AT_FROM_POSITION_HASH = 2086826090L
        private val lookAtFromPositionBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "look_at_from_position", LOOK_AT_FROM_POSITION_HASH)
        }

        private const val TO_LOCAL_HASH = 192990374L
        private val toLocalBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "to_local", TO_LOCAL_HASH)
        }

        private const val TO_GLOBAL_HASH = 192990374L
        private val toGlobalBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "to_global", TO_GLOBAL_HASH)
        }
    }
}

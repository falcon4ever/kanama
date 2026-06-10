package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: Node3D
 */
open class Node3D(handle: MemorySegment) : Node(handle) {
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

    fun setPosition(position: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setPositionBind, handle, position)
    }

    fun getPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getPositionBind, handle)
    }

    fun setRotation(eulerRadians: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setRotationBind, handle, eulerRadians)
    }

    fun getRotation(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getRotationBind, handle)
    }

    fun setRotationDegrees(eulerDegrees: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setRotationDegreesBind, handle, eulerDegrees)
    }

    fun getRotationDegrees(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getRotationDegreesBind, handle)
    }

    fun setRotationOrder(order: Long) {
        ObjectCalls.ptrcallWithLongArg(setRotationOrderBind, handle, order)
    }

    fun getRotationOrder(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getRotationOrderBind, handle)
    }

    fun setRotationEditMode(editMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setRotationEditModeBind, handle, editMode)
    }

    fun getRotationEditMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getRotationEditModeBind, handle)
    }

    fun setScale(scale: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setScaleBind, handle, scale)
    }

    fun getScale(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getScaleBind, handle)
    }

    fun setGlobalPosition(position: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setGlobalPositionBind, handle, position)
    }

    fun getGlobalPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getGlobalPositionBind, handle)
    }

    fun setGlobalRotation(eulerRadians: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setGlobalRotationBind, handle, eulerRadians)
    }

    fun getGlobalRotation(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getGlobalRotationBind, handle)
    }

    fun setGlobalRotationDegrees(eulerDegrees: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setGlobalRotationDegreesBind, handle, eulerDegrees)
    }

    fun getGlobalRotationDegrees(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getGlobalRotationDegreesBind, handle)
    }

    fun getParentNode3d(): Node3D? {
        return Node3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getParentNode3dBind, handle))
    }

    fun setIgnoreTransformNotification(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIgnoreTransformNotificationBind, handle, enabled)
    }

    fun setAsTopLevel(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAsTopLevelBind, handle, enable)
    }

    fun isSetAsTopLevel(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSetAsTopLevelBind, handle)
    }

    fun setDisableScale(disable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDisableScaleBind, handle, disable)
    }

    fun isScaleDisabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isScaleDisabledBind, handle)
    }

    fun forceUpdateTransform() {
        ObjectCalls.ptrcallNoArgs(forceUpdateTransformBind, handle)
    }

    fun updateGizmos() {
        ObjectCalls.ptrcallNoArgs(updateGizmosBind, handle)
    }

    fun clearGizmos() {
        ObjectCalls.ptrcallNoArgs(clearGizmosBind, handle)
    }

    fun clearSubgizmoSelection() {
        ObjectCalls.ptrcallNoArgs(clearSubgizmoSelectionBind, handle)
    }

    fun setVisible(visible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVisibleBind, handle, visible)
    }

    fun isVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVisibleBind, handle)
    }

    fun isVisibleInTree(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVisibleInTreeBind, handle)
    }

    fun show() {
        ObjectCalls.ptrcallNoArgs(showBind, handle)
    }

    fun hide() {
        ObjectCalls.ptrcallNoArgs(hideBind, handle)
    }

    fun setNotifyLocalTransform(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setNotifyLocalTransformBind, handle, enable)
    }

    fun isLocalTransformNotificationEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isLocalTransformNotificationEnabledBind, handle)
    }

    fun setNotifyTransform(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setNotifyTransformBind, handle, enable)
    }

    fun isTransformNotificationEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isTransformNotificationEnabledBind, handle)
    }

    fun rotate(axis: Vector3, angle: Double) {
        ObjectCalls.ptrcallWithVector3AndDoubleArg(rotateBind, handle, axis, angle)
    }

    fun globalRotate(axis: Vector3, angle: Double) {
        ObjectCalls.ptrcallWithVector3AndDoubleArg(globalRotateBind, handle, axis, angle)
    }

    fun globalScale(scale: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(globalScaleBind, handle, scale)
    }

    fun globalTranslate(offset: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(globalTranslateBind, handle, offset)
    }

    fun rotateObjectLocal(axis: Vector3, angle: Double) {
        ObjectCalls.ptrcallWithVector3AndDoubleArg(rotateObjectLocalBind, handle, axis, angle)
    }

    fun scaleObjectLocal(scale: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(scaleObjectLocalBind, handle, scale)
    }

    fun translateObjectLocal(offset: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(translateObjectLocalBind, handle, offset)
    }

    fun rotateX(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(rotateXBind, handle, angle)
    }

    fun rotateY(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(rotateYBind, handle, angle)
    }

    fun rotateZ(angle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(rotateZBind, handle, angle)
    }

    fun translate(offset: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(translateBind, handle, offset)
    }

    fun orthonormalize() {
        ObjectCalls.ptrcallNoArgs(orthonormalizeBind, handle)
    }

    fun setIdentity() {
        ObjectCalls.ptrcallNoArgs(setIdentityBind, handle)
    }

    fun lookAt(target: Vector3, up: Vector3, useModelFront: Boolean = false) {
        ObjectCalls.ptrcallWithTwoVector3AndBoolArgs(lookAtBind, handle, target, up, useModelFront)
    }

    fun lookAtFromPosition(position: Vector3, target: Vector3, up: Vector3, useModelFront: Boolean = false) {
        ObjectCalls.ptrcallWithThreeVector3AndBoolArgs(lookAtFromPositionBind, handle, position, target, up, useModelFront)
    }

    fun toLocal(globalPoint: Vector3): Vector3 {
        return ObjectCalls.ptrcallWithVector3ArgRetVector3(toLocalBind, handle, globalPoint)
    }

    fun toGlobal(localPoint: Vector3): Vector3 {
        return ObjectCalls.ptrcallWithVector3ArgRetVector3(toGlobalBind, handle, localPoint)
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

        fun fromHandle(handle: MemorySegment): Node3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Node3D? =
            if (handle.address() == 0L) null else Node3D(handle)

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

        private const val SET_GLOBAL_POSITION_HASH = 3460891852L
        private val setGlobalPositionBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "set_global_position", SET_GLOBAL_POSITION_HASH)
        }

        private const val GET_GLOBAL_POSITION_HASH = 3360562783L
        private val getGlobalPositionBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "get_global_position", GET_GLOBAL_POSITION_HASH)
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

        private const val FORCE_UPDATE_TRANSFORM_HASH = 3218959716L
        private val forceUpdateTransformBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "force_update_transform", FORCE_UPDATE_TRANSFORM_HASH)
        }

        private const val UPDATE_GIZMOS_HASH = 3218959716L
        private val updateGizmosBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "update_gizmos", UPDATE_GIZMOS_HASH)
        }

        private const val CLEAR_GIZMOS_HASH = 3218959716L
        private val clearGizmosBind by lazy {
            ObjectCalls.getMethodBind("Node3D", "clear_gizmos", CLEAR_GIZMOS_HASH)
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

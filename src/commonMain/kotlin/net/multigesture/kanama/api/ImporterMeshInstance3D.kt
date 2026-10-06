package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath

/**
 * Generated from Godot docs: ImporterMeshInstance3D
 */
class ImporterMeshInstance3D(handle: GodotHandle) : Node3D(handle) {
    var mesh: ImporterMesh?
        @JvmName("meshProperty")
        get() = getMesh()
        @JvmName("setMeshProperty")
        set(value) = setMesh(value)

    var skin: Skin?
        @JvmName("skinProperty")
        get() = getSkin()
        @JvmName("setSkinProperty")
        set(value) = setSkin(value)

    var skeletonPath: NodePath
        @JvmName("skeletonPathProperty")
        get() = getSkeletonPath()
        @JvmName("setSkeletonPathProperty")
        set(value) = setSkeletonPath(value)

    var layerMask: Long
        @JvmName("layerMaskProperty")
        get() = getLayerMask()
        @JvmName("setLayerMaskProperty")
        set(value) = setLayerMask(value)

    var castShadow: GeometryInstance3D.ShadowCastingSetting
        @JvmName("castShadowProperty")
        get() = getCastShadowsSetting()
        @JvmName("setCastShadowProperty")
        set(value) = setCastShadowsSetting(value)

    var visibilityRangeBegin: Double
        @JvmName("visibilityRangeBeginProperty")
        get() = getVisibilityRangeBegin()
        @JvmName("setVisibilityRangeBeginProperty")
        set(value) = setVisibilityRangeBegin(value)

    var visibilityRangeBeginMargin: Double
        @JvmName("visibilityRangeBeginMarginProperty")
        get() = getVisibilityRangeBeginMargin()
        @JvmName("setVisibilityRangeBeginMarginProperty")
        set(value) = setVisibilityRangeBeginMargin(value)

    var visibilityRangeEnd: Double
        @JvmName("visibilityRangeEndProperty")
        get() = getVisibilityRangeEnd()
        @JvmName("setVisibilityRangeEndProperty")
        set(value) = setVisibilityRangeEnd(value)

    var visibilityRangeEndMargin: Double
        @JvmName("visibilityRangeEndMarginProperty")
        get() = getVisibilityRangeEndMargin()
        @JvmName("setVisibilityRangeEndMarginProperty")
        set(value) = setVisibilityRangeEndMargin(value)

    var visibilityRangeFadeMode: GeometryInstance3D.VisibilityRangeFadeMode
        @JvmName("visibilityRangeFadeModeProperty")
        get() = getVisibilityRangeFadeMode()
        @JvmName("setVisibilityRangeFadeModeProperty")
        set(value) = setVisibilityRangeFadeMode(value)

    fun setMesh(mesh: ImporterMesh?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setMeshBind, segment, listOf(mesh?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getMesh(): ImporterMesh? {
        return ImporterMesh.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getMeshBind, segment))
    }

    fun setSkin(skin: Skin?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setSkinBind, segment, listOf(skin?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getSkin(): Skin? {
        return Skin.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getSkinBind, segment))
    }

    fun setSkeletonPath(skeletonPath: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(Binds.setSkeletonPathBind, segment, skeletonPath)
    }

    fun getSkeletonPath(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getSkeletonPathBind, segment)
    }

    fun setLayerMask(layerMask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setLayerMaskBind, segment, layerMask)
    }

    fun getLayerMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getLayerMaskBind, segment)
    }

    fun setCastShadowsSetting(shadowCastingSetting: GeometryInstance3D.ShadowCastingSetting) {
        ObjectCalls.ptrcallWithLongArg(Binds.setCastShadowsSettingBind, segment, shadowCastingSetting.value)
    }

    fun getCastShadowsSetting(): GeometryInstance3D.ShadowCastingSetting {
        return GeometryInstance3D.ShadowCastingSetting(ObjectCalls.ptrcallNoArgsRetLong(Binds.getCastShadowsSettingBind, segment))
    }

    fun setVisibilityRangeEndMargin(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setVisibilityRangeEndMarginBind, segment, distance)
    }

    fun getVisibilityRangeEndMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getVisibilityRangeEndMarginBind, segment)
    }

    fun setVisibilityRangeEnd(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setVisibilityRangeEndBind, segment, distance)
    }

    fun getVisibilityRangeEnd(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getVisibilityRangeEndBind, segment)
    }

    fun setVisibilityRangeBeginMargin(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setVisibilityRangeBeginMarginBind, segment, distance)
    }

    fun getVisibilityRangeBeginMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getVisibilityRangeBeginMarginBind, segment)
    }

    fun setVisibilityRangeBegin(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setVisibilityRangeBeginBind, segment, distance)
    }

    fun getVisibilityRangeBegin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getVisibilityRangeBeginBind, segment)
    }

    fun setVisibilityRangeFadeMode(mode: GeometryInstance3D.VisibilityRangeFadeMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setVisibilityRangeFadeModeBind, segment, mode.value)
    }

    fun getVisibilityRangeFadeMode(): GeometryInstance3D.VisibilityRangeFadeMode {
        return GeometryInstance3D.VisibilityRangeFadeMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getVisibilityRangeFadeModeBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ImporterMeshInstance3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ImporterMeshInstance3D? =
            if (handle.address() == 0L) null else ImporterMeshInstance3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_MESH_HASH = 2255166972L
        @JvmField
        val setMeshBind =
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "set_mesh", SET_MESH_HASH)

        private const val GET_MESH_HASH = 3161779525L
        @JvmField
        val getMeshBind =
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "get_mesh", GET_MESH_HASH)

        private const val SET_SKIN_HASH = 3971435618L
        @JvmField
        val setSkinBind =
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "set_skin", SET_SKIN_HASH)

        private const val GET_SKIN_HASH = 2074563878L
        @JvmField
        val getSkinBind =
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "get_skin", GET_SKIN_HASH)

        private const val SET_SKELETON_PATH_HASH = 1348162250L
        @JvmField
        val setSkeletonPathBind =
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "set_skeleton_path", SET_SKELETON_PATH_HASH)

        private const val GET_SKELETON_PATH_HASH = 4075236667L
        @JvmField
        val getSkeletonPathBind =
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "get_skeleton_path", GET_SKELETON_PATH_HASH)

        private const val SET_LAYER_MASK_HASH = 1286410249L
        @JvmField
        val setLayerMaskBind =
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "set_layer_mask", SET_LAYER_MASK_HASH)

        private const val GET_LAYER_MASK_HASH = 3905245786L
        @JvmField
        val getLayerMaskBind =
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "get_layer_mask", GET_LAYER_MASK_HASH)

        private const val SET_CAST_SHADOWS_SETTING_HASH = 856677339L
        @JvmField
        val setCastShadowsSettingBind =
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "set_cast_shadows_setting", SET_CAST_SHADOWS_SETTING_HASH)

        private const val GET_CAST_SHADOWS_SETTING_HASH = 3383019359L
        @JvmField
        val getCastShadowsSettingBind =
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "get_cast_shadows_setting", GET_CAST_SHADOWS_SETTING_HASH)

        private const val SET_VISIBILITY_RANGE_END_MARGIN_HASH = 373806689L
        @JvmField
        val setVisibilityRangeEndMarginBind =
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "set_visibility_range_end_margin", SET_VISIBILITY_RANGE_END_MARGIN_HASH)

        private const val GET_VISIBILITY_RANGE_END_MARGIN_HASH = 1740695150L
        @JvmField
        val getVisibilityRangeEndMarginBind =
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "get_visibility_range_end_margin", GET_VISIBILITY_RANGE_END_MARGIN_HASH)

        private const val SET_VISIBILITY_RANGE_END_HASH = 373806689L
        @JvmField
        val setVisibilityRangeEndBind =
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "set_visibility_range_end", SET_VISIBILITY_RANGE_END_HASH)

        private const val GET_VISIBILITY_RANGE_END_HASH = 1740695150L
        @JvmField
        val getVisibilityRangeEndBind =
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "get_visibility_range_end", GET_VISIBILITY_RANGE_END_HASH)

        private const val SET_VISIBILITY_RANGE_BEGIN_MARGIN_HASH = 373806689L
        @JvmField
        val setVisibilityRangeBeginMarginBind =
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "set_visibility_range_begin_margin", SET_VISIBILITY_RANGE_BEGIN_MARGIN_HASH)

        private const val GET_VISIBILITY_RANGE_BEGIN_MARGIN_HASH = 1740695150L
        @JvmField
        val getVisibilityRangeBeginMarginBind =
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "get_visibility_range_begin_margin", GET_VISIBILITY_RANGE_BEGIN_MARGIN_HASH)

        private const val SET_VISIBILITY_RANGE_BEGIN_HASH = 373806689L
        @JvmField
        val setVisibilityRangeBeginBind =
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "set_visibility_range_begin", SET_VISIBILITY_RANGE_BEGIN_HASH)

        private const val GET_VISIBILITY_RANGE_BEGIN_HASH = 1740695150L
        @JvmField
        val getVisibilityRangeBeginBind =
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "get_visibility_range_begin", GET_VISIBILITY_RANGE_BEGIN_HASH)

        private const val SET_VISIBILITY_RANGE_FADE_MODE_HASH = 1440117808L
        @JvmField
        val setVisibilityRangeFadeModeBind =
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "set_visibility_range_fade_mode", SET_VISIBILITY_RANGE_FADE_MODE_HASH)

        private const val GET_VISIBILITY_RANGE_FADE_MODE_HASH = 2067221882L
        @JvmField
        val getVisibilityRangeFadeModeBind =
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "get_visibility_range_fade_mode", GET_VISIBILITY_RANGE_FADE_MODE_HASH)
    }
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

/**
 * Generated from Godot docs: ImporterMeshInstance3D
 */
class ImporterMeshInstance3D(handle: MemorySegment) : Node3D(handle) {
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

    var castShadow: Long
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

    var visibilityRangeFadeMode: Long
        @JvmName("visibilityRangeFadeModeProperty")
        get() = getVisibilityRangeFadeMode()
        @JvmName("setVisibilityRangeFadeModeProperty")
        set(value) = setVisibilityRangeFadeMode(value)

    fun setMesh(mesh: ImporterMesh?) {
        ObjectCalls.ptrcallWithObjectArgs(setMeshBind, handle, listOf(mesh?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getMesh(): ImporterMesh? {
        return ImporterMesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMeshBind, handle))
    }

    fun setSkin(skin: Skin?) {
        ObjectCalls.ptrcallWithObjectArgs(setSkinBind, handle, listOf(skin?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getSkin(): Skin? {
        return Skin.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSkinBind, handle))
    }

    fun setSkeletonPath(skeletonPath: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setSkeletonPathBind, handle, skeletonPath)
    }

    fun getSkeletonPath(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getSkeletonPathBind, handle)
    }

    fun setLayerMask(layerMask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setLayerMaskBind, handle, layerMask)
    }

    fun getLayerMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getLayerMaskBind, handle)
    }

    fun setCastShadowsSetting(shadowCastingSetting: Long) {
        ObjectCalls.ptrcallWithLongArg(setCastShadowsSettingBind, handle, shadowCastingSetting)
    }

    fun getCastShadowsSetting(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCastShadowsSettingBind, handle)
    }

    fun setVisibilityRangeEndMargin(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVisibilityRangeEndMarginBind, handle, distance)
    }

    fun getVisibilityRangeEndMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVisibilityRangeEndMarginBind, handle)
    }

    fun setVisibilityRangeEnd(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVisibilityRangeEndBind, handle, distance)
    }

    fun getVisibilityRangeEnd(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVisibilityRangeEndBind, handle)
    }

    fun setVisibilityRangeBeginMargin(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVisibilityRangeBeginMarginBind, handle, distance)
    }

    fun getVisibilityRangeBeginMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVisibilityRangeBeginMarginBind, handle)
    }

    fun setVisibilityRangeBegin(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVisibilityRangeBeginBind, handle, distance)
    }

    fun getVisibilityRangeBegin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVisibilityRangeBeginBind, handle)
    }

    fun setVisibilityRangeFadeMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setVisibilityRangeFadeModeBind, handle, mode)
    }

    fun getVisibilityRangeFadeMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVisibilityRangeFadeModeBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ImporterMeshInstance3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ImporterMeshInstance3D? =
            if (handle.address() == 0L) null else ImporterMeshInstance3D(handle)

        private const val SET_MESH_HASH = 2255166972L
        private val setMeshBind by lazy {
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "set_mesh", SET_MESH_HASH)
        }

        private const val GET_MESH_HASH = 3161779525L
        private val getMeshBind by lazy {
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "get_mesh", GET_MESH_HASH)
        }

        private const val SET_SKIN_HASH = 3971435618L
        private val setSkinBind by lazy {
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "set_skin", SET_SKIN_HASH)
        }

        private const val GET_SKIN_HASH = 2074563878L
        private val getSkinBind by lazy {
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "get_skin", GET_SKIN_HASH)
        }

        private const val SET_SKELETON_PATH_HASH = 1348162250L
        private val setSkeletonPathBind by lazy {
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "set_skeleton_path", SET_SKELETON_PATH_HASH)
        }

        private const val GET_SKELETON_PATH_HASH = 4075236667L
        private val getSkeletonPathBind by lazy {
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "get_skeleton_path", GET_SKELETON_PATH_HASH)
        }

        private const val SET_LAYER_MASK_HASH = 1286410249L
        private val setLayerMaskBind by lazy {
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "set_layer_mask", SET_LAYER_MASK_HASH)
        }

        private const val GET_LAYER_MASK_HASH = 3905245786L
        private val getLayerMaskBind by lazy {
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "get_layer_mask", GET_LAYER_MASK_HASH)
        }

        private const val SET_CAST_SHADOWS_SETTING_HASH = 856677339L
        private val setCastShadowsSettingBind by lazy {
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "set_cast_shadows_setting", SET_CAST_SHADOWS_SETTING_HASH)
        }

        private const val GET_CAST_SHADOWS_SETTING_HASH = 3383019359L
        private val getCastShadowsSettingBind by lazy {
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "get_cast_shadows_setting", GET_CAST_SHADOWS_SETTING_HASH)
        }

        private const val SET_VISIBILITY_RANGE_END_MARGIN_HASH = 373806689L
        private val setVisibilityRangeEndMarginBind by lazy {
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "set_visibility_range_end_margin", SET_VISIBILITY_RANGE_END_MARGIN_HASH)
        }

        private const val GET_VISIBILITY_RANGE_END_MARGIN_HASH = 1740695150L
        private val getVisibilityRangeEndMarginBind by lazy {
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "get_visibility_range_end_margin", GET_VISIBILITY_RANGE_END_MARGIN_HASH)
        }

        private const val SET_VISIBILITY_RANGE_END_HASH = 373806689L
        private val setVisibilityRangeEndBind by lazy {
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "set_visibility_range_end", SET_VISIBILITY_RANGE_END_HASH)
        }

        private const val GET_VISIBILITY_RANGE_END_HASH = 1740695150L
        private val getVisibilityRangeEndBind by lazy {
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "get_visibility_range_end", GET_VISIBILITY_RANGE_END_HASH)
        }

        private const val SET_VISIBILITY_RANGE_BEGIN_MARGIN_HASH = 373806689L
        private val setVisibilityRangeBeginMarginBind by lazy {
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "set_visibility_range_begin_margin", SET_VISIBILITY_RANGE_BEGIN_MARGIN_HASH)
        }

        private const val GET_VISIBILITY_RANGE_BEGIN_MARGIN_HASH = 1740695150L
        private val getVisibilityRangeBeginMarginBind by lazy {
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "get_visibility_range_begin_margin", GET_VISIBILITY_RANGE_BEGIN_MARGIN_HASH)
        }

        private const val SET_VISIBILITY_RANGE_BEGIN_HASH = 373806689L
        private val setVisibilityRangeBeginBind by lazy {
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "set_visibility_range_begin", SET_VISIBILITY_RANGE_BEGIN_HASH)
        }

        private const val GET_VISIBILITY_RANGE_BEGIN_HASH = 1740695150L
        private val getVisibilityRangeBeginBind by lazy {
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "get_visibility_range_begin", GET_VISIBILITY_RANGE_BEGIN_HASH)
        }

        private const val SET_VISIBILITY_RANGE_FADE_MODE_HASH = 1440117808L
        private val setVisibilityRangeFadeModeBind by lazy {
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "set_visibility_range_fade_mode", SET_VISIBILITY_RANGE_FADE_MODE_HASH)
        }

        private const val GET_VISIBILITY_RANGE_FADE_MODE_HASH = 2067221882L
        private val getVisibilityRangeFadeModeBind by lazy {
            ObjectCalls.getMethodBind("ImporterMeshInstance3D", "get_visibility_range_fade_mode", GET_VISIBILITY_RANGE_FADE_MODE_HASH)
        }
    }
}

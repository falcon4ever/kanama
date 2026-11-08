package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed
import net.multigesture.kanama.types.Vector3

/**
 * Inputs a scale and converts it to the position of a child bone.
 *
 * Generated from Godot docs: BoneSpaceAdjuster3D
 */
class BoneSpaceAdjuster3D(handle: GodotHandle) : SkeletonModifier3D(handle) {
    var useBoneSkinScaleForAllBones: Boolean
        @JvmName("useBoneSkinScaleForAllBonesProperty")
        get() = areAllBonesUsingBoneSkinScale()
        @JvmName("setUseBoneSkinScaleForAllBonesProperty")
        set(value) = setUseBoneSkinScaleForAllBones(value)

    var settingSize: Int
        @JvmName("settingSizeProperty")
        get() = getSettingSize()
        @JvmName("setSettingSizeProperty")
        set(value) = setSettingSize(value)

    /**
     * If `true`, this modifier processes all bones for which `Skeleton3D.get_bone_skin_scale` is not
     * `Vector3(1, 1, 1)`. If `false`, this modifier allows you to configure whether to process bone
     * skin scaling individually in the settings.
     *
     * Generated from Godot docs: BoneSpaceAdjuster3D.set_use_bone_skin_scale_for_all_bones
     */
    fun setUseBoneSkinScaleForAllBones(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseBoneSkinScaleForAllBonesBind, segment, enabled)
    }

    /**
     * If `true`, this modifier processes all bones for which `Skeleton3D.get_bone_skin_scale` is not
     * `Vector3(1, 1, 1)`. If `false`, this modifier allows you to configure whether to process bone
     * skin scaling individually in the settings.
     *
     * Generated from Godot docs: BoneSpaceAdjuster3D.are_all_bones_using_bone_skin_scale
     */
    fun areAllBonesUsingBoneSkinScale(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.areAllBonesUsingBoneSkinScaleBind, segment)
    }

    /**
     * Sets the scaled bone name of the setting at `index`.
     *
     * Generated from Godot docs: BoneSpaceAdjuster3D.set_bone_name
     */
    fun setBoneName(index: Int, boneName: String) {
        ObjectCalls.ptrcallWithIntAndStringNameArg(Binds.setBoneNameBind, segment, index, boneName)
    }

    /**
     * Returns the scaled bone name of the setting at `index`.
     *
     * Generated from Godot docs: BoneSpaceAdjuster3D.get_bone_name
     */
    fun getBoneName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetStringName(Binds.getBoneNameBind, segment, index)
    }

    /**
     * Sets the scaled bone of the setting at `index`.
     *
     * Generated from Godot docs: BoneSpaceAdjuster3D.set_bone
     */
    fun setBone(index: Int, bone: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setBoneBind, segment, index, bone)
    }

    /**
     * Returns the scaled bone of the setting at `index`.
     *
     * Generated from Godot docs: BoneSpaceAdjuster3D.get_bone
     */
    fun getBone(index: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getBoneBind, segment, index)
    }

    /**
     * Sets whether to use the `skin_scale` property of a `Skeleton3D`'s bone of the setting at
     * `index`.
     *
     * Generated from Godot docs: BoneSpaceAdjuster3D.set_use_bone_skin_scale
     */
    fun setUseBoneSkinScale(index: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setUseBoneSkinScaleBind, segment, index, enabled)
    }

    /**
     * Returns whether to use the `skin_scale` property of a `Skeleton3D`'s bone of the setting at
     * `index`.
     *
     * Generated from Godot docs: BoneSpaceAdjuster3D.is_using_bone_skin_scale
     */
    fun isUsingBoneSkinScale(index: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isUsingBoneSkinScaleBind, segment, index)
    }

    /**
     * When `is_using_bone_skin_scale` returns `false`, sets the scale of the setting at `index`. Note:
     * The `scale` value is recommended to be non-zero.
     *
     * Generated from Godot docs: BoneSpaceAdjuster3D.set_bone_scale
     */
    fun setBoneScale(index: Int, scale: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(Binds.setBoneScaleBind, segment, index, scale)
    }

    /**
     * Returns the scale of the setting at `index`.
     *
     * Generated from Godot docs: BoneSpaceAdjuster3D.get_bone_scale
     */
    fun getBoneScale(index: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(Binds.getBoneScaleBind, segment, index)
    }

    /**
     * The number of settings in the modifier.
     *
     * Generated from Godot docs: BoneSpaceAdjuster3D.set_setting_size
     */
    fun setSettingSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setSettingSizeBind, segment, size)
    }

    /**
     * The number of settings in the modifier.
     *
     * Generated from Godot docs: BoneSpaceAdjuster3D.get_setting_size
     */
    fun getSettingSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSettingSizeBind, segment)
    }

    /**
     * Clears all settings.
     *
     * Generated from Godot docs: BoneSpaceAdjuster3D.clear_setting
     */
    fun clearSetting() {
        ObjectCalls.ptrcallNoArgs(Binds.clearSettingBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): BoneSpaceAdjuster3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): BoneSpaceAdjuster3D? =
            if (handle.address() == 0L) null else BoneSpaceAdjuster3D(GodotHandle(handle))

        /** A new BoneSpaceAdjuster3D, GDScript's `BoneSpaceAdjuster3D.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): BoneSpaceAdjuster3D =
            BoneSpaceAdjuster3D(GodotHandle(requireConstructed(ObjectCalls.constructObject("BoneSpaceAdjuster3D"), "BoneSpaceAdjuster3D")))
    }

    private object Binds {
        private const val SET_USE_BONE_SKIN_SCALE_FOR_ALL_BONES_HASH = 2586408642L
        @JvmField
        val setUseBoneSkinScaleForAllBonesBind =
            ObjectCalls.getMethodBind("BoneSpaceAdjuster3D", "set_use_bone_skin_scale_for_all_bones", SET_USE_BONE_SKIN_SCALE_FOR_ALL_BONES_HASH)

        private const val ARE_ALL_BONES_USING_BONE_SKIN_SCALE_HASH = 36873697L
        @JvmField
        val areAllBonesUsingBoneSkinScaleBind =
            ObjectCalls.getMethodBind("BoneSpaceAdjuster3D", "are_all_bones_using_bone_skin_scale", ARE_ALL_BONES_USING_BONE_SKIN_SCALE_HASH)

        private const val SET_BONE_NAME_HASH = 3780747571L
        @JvmField
        val setBoneNameBind =
            ObjectCalls.getMethodBind("BoneSpaceAdjuster3D", "set_bone_name", SET_BONE_NAME_HASH)

        private const val GET_BONE_NAME_HASH = 659327637L
        @JvmField
        val getBoneNameBind =
            ObjectCalls.getMethodBind("BoneSpaceAdjuster3D", "get_bone_name", GET_BONE_NAME_HASH)

        private const val SET_BONE_HASH = 3937882851L
        @JvmField
        val setBoneBind =
            ObjectCalls.getMethodBind("BoneSpaceAdjuster3D", "set_bone", SET_BONE_HASH)

        private const val GET_BONE_HASH = 923996154L
        @JvmField
        val getBoneBind =
            ObjectCalls.getMethodBind("BoneSpaceAdjuster3D", "get_bone", GET_BONE_HASH)

        private const val SET_USE_BONE_SKIN_SCALE_HASH = 300928843L
        @JvmField
        val setUseBoneSkinScaleBind =
            ObjectCalls.getMethodBind("BoneSpaceAdjuster3D", "set_use_bone_skin_scale", SET_USE_BONE_SKIN_SCALE_HASH)

        private const val IS_USING_BONE_SKIN_SCALE_HASH = 1116898809L
        @JvmField
        val isUsingBoneSkinScaleBind =
            ObjectCalls.getMethodBind("BoneSpaceAdjuster3D", "is_using_bone_skin_scale", IS_USING_BONE_SKIN_SCALE_HASH)

        private const val SET_BONE_SCALE_HASH = 1530502735L
        @JvmField
        val setBoneScaleBind =
            ObjectCalls.getMethodBind("BoneSpaceAdjuster3D", "set_bone_scale", SET_BONE_SCALE_HASH)

        private const val GET_BONE_SCALE_HASH = 711720468L
        @JvmField
        val getBoneScaleBind =
            ObjectCalls.getMethodBind("BoneSpaceAdjuster3D", "get_bone_scale", GET_BONE_SCALE_HASH)

        private const val SET_SETTING_SIZE_HASH = 1286410249L
        @JvmField
        val setSettingSizeBind =
            ObjectCalls.getMethodBind("BoneSpaceAdjuster3D", "set_setting_size", SET_SETTING_SIZE_HASH)

        private const val GET_SETTING_SIZE_HASH = 2455072627L
        @JvmField
        val getSettingSizeBind =
            ObjectCalls.getMethodBind("BoneSpaceAdjuster3D", "get_setting_size", GET_SETTING_SIZE_HASH)

        private const val CLEAR_SETTING_HASH = 3218959716L
        @JvmField
        val clearSettingBind =
            ObjectCalls.getMethodBind("BoneSpaceAdjuster3D", "clear_setting", CLEAR_SETTING_HASH)
    }
}

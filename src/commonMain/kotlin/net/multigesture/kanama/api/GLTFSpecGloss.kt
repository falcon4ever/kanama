package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color

/**
 * Generated from Godot docs: GLTFSpecGloss
 */
class GLTFSpecGloss(handle: GodotHandle) : Resource(handle) {
    var diffuseImg: Image?
        @JvmName("diffuseImgProperty")
        get() = getDiffuseImg()
        @JvmName("setDiffuseImgProperty")
        set(value) = setDiffuseImg(value)

    var diffuseFactor: Color
        @JvmName("diffuseFactorProperty")
        get() = getDiffuseFactor()
        @JvmName("setDiffuseFactorProperty")
        set(value) = setDiffuseFactor(value)

    var glossFactor: Double
        @JvmName("glossFactorProperty")
        get() = getGlossFactor()
        @JvmName("setGlossFactorProperty")
        set(value) = setGlossFactor(value)

    var specularFactor: Color
        @JvmName("specularFactorProperty")
        get() = getSpecularFactor()
        @JvmName("setSpecularFactorProperty")
        set(value) = setSpecularFactor(value)

    var specGlossImg: Image?
        @JvmName("specGlossImgProperty")
        get() = getSpecGlossImg()
        @JvmName("setSpecGlossImgProperty")
        set(value) = setSpecGlossImg(value)

    fun getDiffuseImg(): Image? {
        checkOpen()
        return Image.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getDiffuseImgBind, segment))
    }

    fun setDiffuseImg(diffuseImg: Image?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setDiffuseImgBind, segment, listOf(diffuseImg?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getDiffuseFactor(): Color {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getDiffuseFactorBind, segment)
    }

    fun setDiffuseFactor(diffuseFactor: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(Binds.setDiffuseFactorBind, segment, diffuseFactor)
    }

    fun getGlossFactor(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getGlossFactorBind, segment)
    }

    fun setGlossFactor(glossFactor: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setGlossFactorBind, segment, glossFactor)
    }

    fun getSpecularFactor(): Color {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getSpecularFactorBind, segment)
    }

    fun setSpecularFactor(specularFactor: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(Binds.setSpecularFactorBind, segment, specularFactor)
    }

    fun getSpecGlossImg(): Image? {
        checkOpen()
        return Image.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getSpecGlossImgBind, segment))
    }

    fun setSpecGlossImg(specGlossImg: Image?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setSpecGlossImgBind, segment, listOf(specGlossImg?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFSpecGloss? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GLTFSpecGloss? =
            if (handle.address() == 0L) null else RefCounted.owned(GLTFSpecGloss(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GLTFSpecGloss? =
            if (handle.address() == 0L) null else GLTFSpecGloss(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_DIFFUSE_IMG_HASH = 564927088L
        @JvmField
        val getDiffuseImgBind =
            ObjectCalls.getMethodBind("GLTFSpecGloss", "get_diffuse_img", GET_DIFFUSE_IMG_HASH)

        private const val SET_DIFFUSE_IMG_HASH = 532598488L
        @JvmField
        val setDiffuseImgBind =
            ObjectCalls.getMethodBind("GLTFSpecGloss", "set_diffuse_img", SET_DIFFUSE_IMG_HASH)

        private const val GET_DIFFUSE_FACTOR_HASH = 3200896285L
        @JvmField
        val getDiffuseFactorBind =
            ObjectCalls.getMethodBind("GLTFSpecGloss", "get_diffuse_factor", GET_DIFFUSE_FACTOR_HASH)

        private const val SET_DIFFUSE_FACTOR_HASH = 2920490490L
        @JvmField
        val setDiffuseFactorBind =
            ObjectCalls.getMethodBind("GLTFSpecGloss", "set_diffuse_factor", SET_DIFFUSE_FACTOR_HASH)

        private const val GET_GLOSS_FACTOR_HASH = 191475506L
        @JvmField
        val getGlossFactorBind =
            ObjectCalls.getMethodBind("GLTFSpecGloss", "get_gloss_factor", GET_GLOSS_FACTOR_HASH)

        private const val SET_GLOSS_FACTOR_HASH = 373806689L
        @JvmField
        val setGlossFactorBind =
            ObjectCalls.getMethodBind("GLTFSpecGloss", "set_gloss_factor", SET_GLOSS_FACTOR_HASH)

        private const val GET_SPECULAR_FACTOR_HASH = 3200896285L
        @JvmField
        val getSpecularFactorBind =
            ObjectCalls.getMethodBind("GLTFSpecGloss", "get_specular_factor", GET_SPECULAR_FACTOR_HASH)

        private const val SET_SPECULAR_FACTOR_HASH = 2920490490L
        @JvmField
        val setSpecularFactorBind =
            ObjectCalls.getMethodBind("GLTFSpecGloss", "set_specular_factor", SET_SPECULAR_FACTOR_HASH)

        private const val GET_SPEC_GLOSS_IMG_HASH = 564927088L
        @JvmField
        val getSpecGlossImgBind =
            ObjectCalls.getMethodBind("GLTFSpecGloss", "get_spec_gloss_img", GET_SPEC_GLOSS_IMG_HASH)

        private const val SET_SPEC_GLOSS_IMG_HASH = 532598488L
        @JvmField
        val setSpecGlossImgBind =
            ObjectCalls.getMethodBind("GLTFSpecGloss", "set_spec_gloss_img", SET_SPEC_GLOSS_IMG_HASH)
    }
}

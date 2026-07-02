package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * Generated from Godot docs: GLTFSpecGloss
 */
class GLTFSpecGloss(handle: MemorySegment) : Resource(handle) {
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
        return Image.wrap(ObjectCalls.ptrcallNoArgsRetObject(getDiffuseImgBind, handle))
    }

    fun setDiffuseImg(diffuseImg: Image?) {
        ObjectCalls.ptrcallWithObjectArgs(setDiffuseImgBind, handle, listOf(diffuseImg?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getDiffuseFactor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getDiffuseFactorBind, handle)
    }

    fun setDiffuseFactor(diffuseFactor: Color) {
        ObjectCalls.ptrcallWithColorArg(setDiffuseFactorBind, handle, diffuseFactor)
    }

    fun getGlossFactor(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGlossFactorBind, handle)
    }

    fun setGlossFactor(glossFactor: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGlossFactorBind, handle, glossFactor)
    }

    fun getSpecularFactor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getSpecularFactorBind, handle)
    }

    fun setSpecularFactor(specularFactor: Color) {
        ObjectCalls.ptrcallWithColorArg(setSpecularFactorBind, handle, specularFactor)
    }

    fun getSpecGlossImg(): Image? {
        return Image.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSpecGlossImgBind, handle))
    }

    fun setSpecGlossImg(specGlossImg: Image?) {
        ObjectCalls.ptrcallWithObjectArgs(setSpecGlossImgBind, handle, listOf(specGlossImg?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): GLTFSpecGloss? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GLTFSpecGloss? =
            if (handle.address() == 0L) null else GLTFSpecGloss(handle)

        private const val GET_DIFFUSE_IMG_HASH = 564927088L
        private val getDiffuseImgBind by lazy {
            ObjectCalls.getMethodBind("GLTFSpecGloss", "get_diffuse_img", GET_DIFFUSE_IMG_HASH)
        }

        private const val SET_DIFFUSE_IMG_HASH = 532598488L
        private val setDiffuseImgBind by lazy {
            ObjectCalls.getMethodBind("GLTFSpecGloss", "set_diffuse_img", SET_DIFFUSE_IMG_HASH)
        }

        private const val GET_DIFFUSE_FACTOR_HASH = 3200896285L
        private val getDiffuseFactorBind by lazy {
            ObjectCalls.getMethodBind("GLTFSpecGloss", "get_diffuse_factor", GET_DIFFUSE_FACTOR_HASH)
        }

        private const val SET_DIFFUSE_FACTOR_HASH = 2920490490L
        private val setDiffuseFactorBind by lazy {
            ObjectCalls.getMethodBind("GLTFSpecGloss", "set_diffuse_factor", SET_DIFFUSE_FACTOR_HASH)
        }

        private const val GET_GLOSS_FACTOR_HASH = 191475506L
        private val getGlossFactorBind by lazy {
            ObjectCalls.getMethodBind("GLTFSpecGloss", "get_gloss_factor", GET_GLOSS_FACTOR_HASH)
        }

        private const val SET_GLOSS_FACTOR_HASH = 373806689L
        private val setGlossFactorBind by lazy {
            ObjectCalls.getMethodBind("GLTFSpecGloss", "set_gloss_factor", SET_GLOSS_FACTOR_HASH)
        }

        private const val GET_SPECULAR_FACTOR_HASH = 3200896285L
        private val getSpecularFactorBind by lazy {
            ObjectCalls.getMethodBind("GLTFSpecGloss", "get_specular_factor", GET_SPECULAR_FACTOR_HASH)
        }

        private const val SET_SPECULAR_FACTOR_HASH = 2920490490L
        private val setSpecularFactorBind by lazy {
            ObjectCalls.getMethodBind("GLTFSpecGloss", "set_specular_factor", SET_SPECULAR_FACTOR_HASH)
        }

        private const val GET_SPEC_GLOSS_IMG_HASH = 564927088L
        private val getSpecGlossImgBind by lazy {
            ObjectCalls.getMethodBind("GLTFSpecGloss", "get_spec_gloss_img", GET_SPEC_GLOSS_IMG_HASH)
        }

        private const val SET_SPEC_GLOSS_IMG_HASH = 532598488L
        private val setSpecGlossImgBind by lazy {
            ObjectCalls.getMethodBind("GLTFSpecGloss", "set_spec_gloss_img", SET_SPEC_GLOSS_IMG_HASH)
        }
    }
}

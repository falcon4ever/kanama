package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: GLTFTextureSampler
 */
class GLTFTextureSampler(handle: MemorySegment) : Resource(handle) {
    var magFilter: Int
        @JvmName("magFilterProperty")
        get() = getMagFilter()
        @JvmName("setMagFilterProperty")
        set(value) = setMagFilter(value)

    var minFilter: Int
        @JvmName("minFilterProperty")
        get() = getMinFilter()
        @JvmName("setMinFilterProperty")
        set(value) = setMinFilter(value)

    var wrapS: Int
        @JvmName("wrapSProperty")
        get() = getWrapS()
        @JvmName("setWrapSProperty")
        set(value) = setWrapS(value)

    var wrapT: Int
        @JvmName("wrapTProperty")
        get() = getWrapT()
        @JvmName("setWrapTProperty")
        set(value) = setWrapT(value)

    fun getMagFilter(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMagFilterBind, handle)
    }

    fun setMagFilter(filterMode: Int) {
        ObjectCalls.ptrcallWithIntArg(setMagFilterBind, handle, filterMode)
    }

    fun getMinFilter(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMinFilterBind, handle)
    }

    fun setMinFilter(filterMode: Int) {
        ObjectCalls.ptrcallWithIntArg(setMinFilterBind, handle, filterMode)
    }

    fun getWrapS(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getWrapSBind, handle)
    }

    fun setWrapS(wrapMode: Int) {
        ObjectCalls.ptrcallWithIntArg(setWrapSBind, handle, wrapMode)
    }

    fun getWrapT(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getWrapTBind, handle)
    }

    fun setWrapT(wrapMode: Int) {
        ObjectCalls.ptrcallWithIntArg(setWrapTBind, handle, wrapMode)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): GLTFTextureSampler? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GLTFTextureSampler? =
            if (handle.address() == 0L) null else GLTFTextureSampler(handle)

        private const val GET_MAG_FILTER_HASH = 3905245786L
        private val getMagFilterBind by lazy {
            ObjectCalls.getMethodBind("GLTFTextureSampler", "get_mag_filter", GET_MAG_FILTER_HASH)
        }

        private const val SET_MAG_FILTER_HASH = 1286410249L
        private val setMagFilterBind by lazy {
            ObjectCalls.getMethodBind("GLTFTextureSampler", "set_mag_filter", SET_MAG_FILTER_HASH)
        }

        private const val GET_MIN_FILTER_HASH = 3905245786L
        private val getMinFilterBind by lazy {
            ObjectCalls.getMethodBind("GLTFTextureSampler", "get_min_filter", GET_MIN_FILTER_HASH)
        }

        private const val SET_MIN_FILTER_HASH = 1286410249L
        private val setMinFilterBind by lazy {
            ObjectCalls.getMethodBind("GLTFTextureSampler", "set_min_filter", SET_MIN_FILTER_HASH)
        }

        private const val GET_WRAP_S_HASH = 3905245786L
        private val getWrapSBind by lazy {
            ObjectCalls.getMethodBind("GLTFTextureSampler", "get_wrap_s", GET_WRAP_S_HASH)
        }

        private const val SET_WRAP_S_HASH = 1286410249L
        private val setWrapSBind by lazy {
            ObjectCalls.getMethodBind("GLTFTextureSampler", "set_wrap_s", SET_WRAP_S_HASH)
        }

        private const val GET_WRAP_T_HASH = 3905245786L
        private val getWrapTBind by lazy {
            ObjectCalls.getMethodBind("GLTFTextureSampler", "get_wrap_t", GET_WRAP_T_HASH)
        }

        private const val SET_WRAP_T_HASH = 1286410249L
        private val setWrapTBind by lazy {
            ObjectCalls.getMethodBind("GLTFTextureSampler", "set_wrap_t", SET_WRAP_T_HASH)
        }
    }
}

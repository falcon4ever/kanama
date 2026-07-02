package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Rect2

/**
 * A texture that crops out part of another Texture2D.
 *
 * Generated from Godot docs: AtlasTexture
 */
class AtlasTexture(handle: MemorySegment) : Texture2D(handle) {
    var atlas: Texture2D?
        @JvmName("atlasProperty")
        get() = getAtlas()
        @JvmName("setAtlasProperty")
        set(value) = setAtlas(value)

    var region: Rect2
        @JvmName("regionProperty")
        get() = getRegion()
        @JvmName("setRegionProperty")
        set(value) = setRegion(value)

    var margin: Rect2
        @JvmName("marginProperty")
        get() = getMargin()
        @JvmName("setMarginProperty")
        set(value) = setMargin(value)

    var filterClip: Boolean
        @JvmName("filterClipProperty")
        get() = hasFilterClip()
        @JvmName("setFilterClipProperty")
        set(value) = setFilterClip(value)

    fun setAtlas(atlas: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setAtlasBind, handle, listOf(atlas?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getAtlas(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getAtlasBind, handle))
    }

    fun setRegion(region: Rect2) {
        ObjectCalls.ptrcallWithRect2Arg(setRegionBind, handle, region)
    }

    fun getRegion(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getRegionBind, handle)
    }

    fun setMargin(margin: Rect2) {
        ObjectCalls.ptrcallWithRect2Arg(setMarginBind, handle, margin)
    }

    fun getMargin(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getMarginBind, handle)
    }

    fun setFilterClip(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFilterClipBind, handle, enable)
    }

    fun hasFilterClip(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasFilterClipBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AtlasTexture? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AtlasTexture? =
            if (handle.address() == 0L) null else AtlasTexture(handle)

        private const val SET_ATLAS_HASH = 4051416890L
        private val setAtlasBind by lazy {
            ObjectCalls.getMethodBind("AtlasTexture", "set_atlas", SET_ATLAS_HASH)
        }

        private const val GET_ATLAS_HASH = 3635182373L
        private val getAtlasBind by lazy {
            ObjectCalls.getMethodBind("AtlasTexture", "get_atlas", GET_ATLAS_HASH)
        }

        private const val SET_REGION_HASH = 2046264180L
        private val setRegionBind by lazy {
            ObjectCalls.getMethodBind("AtlasTexture", "set_region", SET_REGION_HASH)
        }

        private const val GET_REGION_HASH = 1639390495L
        private val getRegionBind by lazy {
            ObjectCalls.getMethodBind("AtlasTexture", "get_region", GET_REGION_HASH)
        }

        private const val SET_MARGIN_HASH = 2046264180L
        private val setMarginBind by lazy {
            ObjectCalls.getMethodBind("AtlasTexture", "set_margin", SET_MARGIN_HASH)
        }

        private const val GET_MARGIN_HASH = 1639390495L
        private val getMarginBind by lazy {
            ObjectCalls.getMethodBind("AtlasTexture", "get_margin", GET_MARGIN_HASH)
        }

        private const val SET_FILTER_CLIP_HASH = 2586408642L
        private val setFilterClipBind by lazy {
            ObjectCalls.getMethodBind("AtlasTexture", "set_filter_clip", SET_FILTER_CLIP_HASH)
        }

        private const val HAS_FILTER_CLIP_HASH = 36873697L
        private val hasFilterClipBind by lazy {
            ObjectCalls.getMethodBind("AtlasTexture", "has_filter_clip", HAS_FILTER_CLIP_HASH)
        }
    }
}

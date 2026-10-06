package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Rect2

/**
 * A texture that crops out part of another Texture2D.
 *
 * Generated from Godot docs: AtlasTexture
 */
class AtlasTexture(handle: GodotHandle) : Texture2D(handle) {
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

    /**
     * The texture that contains the atlas. Can be any type inheriting from `Texture2D`, including
     * another `AtlasTexture`.
     *
     * Generated from Godot docs: AtlasTexture.set_atlas
     */
    fun setAtlas(atlas: Texture2D?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setAtlasBind, segment, listOf(atlas?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The texture that contains the atlas. Can be any type inheriting from `Texture2D`, including
     * another `AtlasTexture`.
     *
     * Generated from Godot docs: AtlasTexture.get_atlas
     */
    fun getAtlas(): Texture2D? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(Binds.getAtlasBind, segment)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return Texture2D.wrapOwned(ret)
    }

    /**
     * The region used to draw the `atlas`. If either dimension of the region's size is `0`, the value
     * from `atlas` size will be used for that axis instead. Note: The image size is always an integer,
     * so the actual region size is rounded down.
     *
     * Generated from Godot docs: AtlasTexture.set_region
     */
    fun setRegion(region: Rect2) {
        checkOpen()
        ObjectCalls.ptrcallWithRect2Arg(Binds.setRegionBind, segment, region)
    }

    /**
     * The region used to draw the `atlas`. If either dimension of the region's size is `0`, the value
     * from `atlas` size will be used for that axis instead. Note: The image size is always an integer,
     * so the actual region size is rounded down.
     *
     * Generated from Godot docs: AtlasTexture.get_region
     */
    fun getRegion(): Rect2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRect2(Binds.getRegionBind, segment)
    }

    /**
     * The margin around the `region`. Useful for small adjustments. If the `Rect2.size` of this
     * property ("w" and "h" in the editor) is set, the drawn texture is resized to fit within the
     * margin.
     *
     * Generated from Godot docs: AtlasTexture.set_margin
     */
    fun setMargin(margin: Rect2) {
        checkOpen()
        ObjectCalls.ptrcallWithRect2Arg(Binds.setMarginBind, segment, margin)
    }

    /**
     * The margin around the `region`. Useful for small adjustments. If the `Rect2.size` of this
     * property ("w" and "h" in the editor) is set, the drawn texture is resized to fit within the
     * margin.
     *
     * Generated from Godot docs: AtlasTexture.get_margin
     */
    fun getMargin(): Rect2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRect2(Binds.getMarginBind, segment)
    }

    /**
     * If `true`, the area outside of the `region` is clipped to avoid bleeding of the surrounding
     * texture pixels.
     *
     * Generated from Godot docs: AtlasTexture.set_filter_clip
     */
    fun setFilterClip(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setFilterClipBind, segment, enable)
    }

    /**
     * If `true`, the area outside of the `region` is clipped to avoid bleeding of the surrounding
     * texture pixels.
     *
     * Generated from Godot docs: AtlasTexture.has_filter_clip
     */
    fun hasFilterClip(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasFilterClipBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AtlasTexture? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AtlasTexture? =
            if (handle.address() == 0L) null else RefCounted.owned(AtlasTexture(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AtlasTexture? =
            if (handle.address() == 0L) null else AtlasTexture(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_ATLAS_HASH = 4051416890L
        @JvmField
        val setAtlasBind =
            ObjectCalls.getMethodBind("AtlasTexture", "set_atlas", SET_ATLAS_HASH)

        private const val GET_ATLAS_HASH = 3635182373L
        @JvmField
        val getAtlasBind =
            ObjectCalls.getMethodBind("AtlasTexture", "get_atlas", GET_ATLAS_HASH)

        private const val SET_REGION_HASH = 2046264180L
        @JvmField
        val setRegionBind =
            ObjectCalls.getMethodBind("AtlasTexture", "set_region", SET_REGION_HASH)

        private const val GET_REGION_HASH = 1639390495L
        @JvmField
        val getRegionBind =
            ObjectCalls.getMethodBind("AtlasTexture", "get_region", GET_REGION_HASH)

        private const val SET_MARGIN_HASH = 2046264180L
        @JvmField
        val setMarginBind =
            ObjectCalls.getMethodBind("AtlasTexture", "set_margin", SET_MARGIN_HASH)

        private const val GET_MARGIN_HASH = 1639390495L
        @JvmField
        val getMarginBind =
            ObjectCalls.getMethodBind("AtlasTexture", "get_margin", GET_MARGIN_HASH)

        private const val SET_FILTER_CLIP_HASH = 2586408642L
        @JvmField
        val setFilterClipBind =
            ObjectCalls.getMethodBind("AtlasTexture", "set_filter_clip", SET_FILTER_CLIP_HASH)

        private const val HAS_FILTER_CLIP_HASH = 36873697L
        @JvmField
        val hasFilterClipBind =
            ObjectCalls.getMethodBind("AtlasTexture", "has_filter_clip", HAS_FILTER_CLIP_HASH)
    }
}

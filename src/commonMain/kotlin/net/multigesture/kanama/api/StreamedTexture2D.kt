package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A texture that supports dynamic mipmap streaming for optimized VRAM usage.
 *
 * Generated from Godot docs: StreamedTexture2D
 */
class StreamedTexture2D(handle: GodotHandle) : Texture2D(handle) {
    val loadPath: String
        @JvmName("loadPathProperty")
        get() = getLoadPath()

    var maxLodOverride: Int
        @JvmName("maxLodOverrideProperty")
        get() = getMaxLodOverride()
        @JvmName("setMaxLodOverrideProperty")
        set(value) = setMaxLodOverride(value)

    var minLodOverride: Int
        @JvmName("minLodOverrideProperty")
        get() = getMinLodOverride()
        @JvmName("setMinLodOverrideProperty")
        set(value) = setMinLodOverride(value)

    /**
     * The `StreamedTexture2D`'s file path to a `.stex` file.
     *
     * Generated from Godot docs: StreamedTexture2D.load
     */
    fun load(path: String): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.loadBind, segment, path))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The `StreamedTexture2D`'s file path to a `.stex` file.
     *
     * Generated from Godot docs: StreamedTexture2D.get_load_path
     */
    fun getLoadPath(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getLoadPathBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Overrides the maximum LOD (mipmap level) that can be loaded for this specific texture. Lower
     * values allow higher resolution. A value of `0` uses the global setting from
     * `ProjectSettings.rendering/textures/streaming/max_lod`. Values `1` to `14` correspond to LOD
     * levels `0` to `13`.
     *
     * Generated from Godot docs: StreamedTexture2D.set_max_lod_override
     */
    fun setMaxLodOverride(maxLod: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMaxLodOverrideBind, segment, maxLod)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Overrides the maximum LOD (mipmap level) that can be loaded for this specific texture. Lower
     * values allow higher resolution. A value of `0` uses the global setting from
     * `ProjectSettings.rendering/textures/streaming/max_lod`. Values `1` to `14` correspond to LOD
     * levels `0` to `13`.
     *
     * Generated from Godot docs: StreamedTexture2D.get_max_lod_override
     */
    fun getMaxLodOverride(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxLodOverrideBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Overrides the minimum LOD (mipmap level) this texture starts at when loaded. Higher values
     * result in lower initial resolution. A value of `0` uses the global setting from
     * `ProjectSettings.rendering/textures/streaming/min_lod`. Values `1` to `14` correspond to LOD
     * levels `0` to `13`.
     *
     * Generated from Godot docs: StreamedTexture2D.set_min_lod_override
     */
    fun setMinLodOverride(minLod: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMinLodOverrideBind, segment, minLod)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Overrides the minimum LOD (mipmap level) this texture starts at when loaded. Higher values
     * result in lower initial resolution. A value of `0` uses the global setting from
     * `ProjectSettings.rendering/textures/streaming/min_lod`. Values `1` to `14` correspond to LOD
     * levels `0` to `13`.
     *
     * Generated from Godot docs: StreamedTexture2D.get_min_lod_override
     */
    fun getMinLodOverride(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getMinLodOverrideBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): StreamedTexture2D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): StreamedTexture2D? =
            if (handle.address() == 0L) null else RefCounted.owned(StreamedTexture2D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): StreamedTexture2D? =
            if (handle.address() == 0L) null else StreamedTexture2D(GodotHandle(handle))

        /** A new StreamedTexture2D, GDScript's `StreamedTexture2D.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): StreamedTexture2D =
            RefCounted.owned(StreamedTexture2D(GodotHandle(requireConstructed(ObjectCalls.constructObject("StreamedTexture2D"), "StreamedTexture2D"))))
    }

    private object Binds {
        private const val LOAD_HASH = 166001499L
        @JvmField
        val loadBind =
            ObjectCalls.getMethodBind("StreamedTexture2D", "load", LOAD_HASH)

        private const val GET_LOAD_PATH_HASH = 201670096L
        @JvmField
        val getLoadPathBind =
            ObjectCalls.getMethodBind("StreamedTexture2D", "get_load_path", GET_LOAD_PATH_HASH)

        private const val SET_MAX_LOD_OVERRIDE_HASH = 1286410249L
        @JvmField
        val setMaxLodOverrideBind =
            ObjectCalls.getMethodBind("StreamedTexture2D", "set_max_lod_override", SET_MAX_LOD_OVERRIDE_HASH)

        private const val GET_MAX_LOD_OVERRIDE_HASH = 3905245786L
        @JvmField
        val getMaxLodOverrideBind =
            ObjectCalls.getMethodBind("StreamedTexture2D", "get_max_lod_override", GET_MAX_LOD_OVERRIDE_HASH)

        private const val SET_MIN_LOD_OVERRIDE_HASH = 1286410249L
        @JvmField
        val setMinLodOverrideBind =
            ObjectCalls.getMethodBind("StreamedTexture2D", "set_min_lod_override", SET_MIN_LOD_OVERRIDE_HASH)

        private const val GET_MIN_LOD_OVERRIDE_HASH = 3905245786L
        @JvmField
        val getMinLodOverrideBind =
            ObjectCalls.getMethodBind("StreamedTexture2D", "get_min_lod_override", GET_MIN_LOD_OVERRIDE_HASH)
    }
}

package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Manages dynamic texture mipmap streaming to optimize VRAM usage.
 *
 * Generated from Godot docs: TextureStreaming
 */
object TextureStreaming {
    private inline val singleton: RawSegment
        get() = Binds.singleton

    var minLodOverride: Int
        @JvmName("minLodOverrideProperty")
        get() = getMinLodOverride()
        @JvmName("setMinLodOverrideProperty")
        set(value) = setMinLodOverride(value)

    var maxLodOverride: Int
        @JvmName("maxLodOverrideProperty")
        get() = getMaxLodOverride()
        @JvmName("setMaxLodOverrideProperty")
        set(value) = setMaxLodOverride(value)

    var memoryBudgetMbOverride: Long
        @JvmName("memoryBudgetMbOverrideProperty")
        get() = getMemoryBudgetMbOverride()
        @JvmName("setMemoryBudgetMbOverrideProperty")
        set(value) = setMemoryBudgetMbOverride(value)

    /**
     * Overrides the minimum LOD (mipmap level) that textures start at when loaded. Higher values
     * result in lower initial resolution. Valid values are `0` (full resolution) to `13` (lowest
     * resolution). Values outside that range clear the override and use the default from
     * `ProjectSettings.rendering/textures/streaming/min_lod`; when no override is active this property
     * returns `-1`.
     *
     * Generated from Godot docs: TextureStreaming.get_min_lod_override
     */
    @JvmStatic
    fun getMinLodOverride(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMinLodOverrideBind, singleton)
    }

    /**
     * Overrides the minimum LOD (mipmap level) that textures start at when loaded. Higher values
     * result in lower initial resolution. Valid values are `0` (full resolution) to `13` (lowest
     * resolution). Values outside that range clear the override and use the default from
     * `ProjectSettings.rendering/textures/streaming/min_lod`; when no override is active this property
     * returns `-1`.
     *
     * Generated from Godot docs: TextureStreaming.set_min_lod_override
     */
    @JvmStatic
    fun setMinLodOverride(minLod: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setMinLodOverrideBind, singleton, minLod)
    }

    /**
     * Overrides the maximum LOD (mipmap level) that can be loaded for streamed textures at runtime.
     * Lower values allow higher resolution textures. Valid values are `0` (full resolution) to `13`
     * (lowest resolution). Values outside that range clear the override and use the default from
     * `ProjectSettings.rendering/textures/streaming/max_lod`; when no override is active this property
     * returns `-1`.
     *
     * Generated from Godot docs: TextureStreaming.get_max_lod_override
     */
    @JvmStatic
    fun getMaxLodOverride(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxLodOverrideBind, singleton)
    }

    /**
     * Overrides the maximum LOD (mipmap level) that can be loaded for streamed textures at runtime.
     * Lower values allow higher resolution textures. Valid values are `0` (full resolution) to `13`
     * (lowest resolution). Values outside that range clear the override and use the default from
     * `ProjectSettings.rendering/textures/streaming/max_lod`; when no override is active this property
     * returns `-1`.
     *
     * Generated from Godot docs: TextureStreaming.set_max_lod_override
     */
    @JvmStatic
    fun setMaxLodOverride(maxLod: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setMaxLodOverrideBind, singleton, maxLod)
    }

    /**
     * Overrides the memory budget for streamed textures at runtime (in megabytes). When the budget is
     * exceeded, the system reduces texture resolution to stay within the limit. A value of
     * `UINT32_MAX` (`4294967295`, the maximum unsigned 32-bit integer) disables the override and uses
     * the default from `ProjectSettings.rendering/textures/streaming/memory_budget_mb`.
     *
     * Generated from Godot docs: TextureStreaming.get_memory_budget_mb_override
     */
    @JvmStatic
    fun getMemoryBudgetMbOverride(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getMemoryBudgetMbOverrideBind, singleton)
    }

    /**
     * Overrides the memory budget for streamed textures at runtime (in megabytes). When the budget is
     * exceeded, the system reduces texture resolution to stay within the limit. A value of
     * `UINT32_MAX` (`4294967295`, the maximum unsigned 32-bit integer) disables the override and uses
     * the default from `ProjectSettings.rendering/textures/streaming/memory_budget_mb`.
     *
     * Generated from Godot docs: TextureStreaming.set_memory_budget_mb_override
     */
    @JvmStatic
    fun setMemoryBudgetMbOverride(mb: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setMemoryBudgetMbOverrideBind, singleton, mb)
    }

    /**
     * Returns the current VRAM usage of streamed textures in bytes. This represents the total memory
     * consumed by all streamed textures at their currently loaded mipmap levels.
     *
     * Generated from Godot docs: TextureStreaming.get_memory_budget_bytes_used
     */
    @JvmStatic
    fun getMemoryBudgetBytesUsed(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getMemoryBudgetBytesUsedBind, singleton)
    }

    /**
     * Forces all currently queued texture streaming operations to complete immediately, bypassing the
     * normal gradual transition and I/O throttling. This immediately loads or unloads mipmap levels to
     * reach the target resolution for all managed textures, then emits the `flush_completed` signal.
     * Useful during loading screens or when teleporting the player to ensure all textures are at their
     * optimal resolution without a visible transition.
     *
     * Generated from Godot docs: TextureStreaming.flush_texture_streaming
     */
    @JvmStatic
    fun flushTextureStreaming() {
        ObjectCalls.ptrcallNoArgs(Binds.flushTextureStreamingBind, singleton)
    }

    /** Signal `flush_completed()`; see [TypedSignal]. */
    val flushCompleted: Signal0
        @JvmName("flushCompletedTypedSignal")
        get() = Signal0(GodotObject(GodotHandle(singleton)), "flush_completed")

    object Signals {
        const val flushCompleted: String = "flush_completed"
    }

    @JvmStatic
    fun fromHandle(handle: GodotHandle): TextureStreaming? =
        wrap(handle.segment)

    internal fun wrap(handle: RawSegment): TextureStreaming? =
        if (handle.address() == 0L) null else this

    private object Binds {
        @JvmField
        val singleton = ObjectCalls.getSingleton("TextureStreaming")

        private const val GET_MIN_LOD_OVERRIDE_HASH = 3905245786L
        @JvmField
        val getMinLodOverrideBind =
            ObjectCalls.getMethodBind("TextureStreaming", "get_min_lod_override", GET_MIN_LOD_OVERRIDE_HASH)

        private const val SET_MIN_LOD_OVERRIDE_HASH = 1286410249L
        @JvmField
        val setMinLodOverrideBind =
            ObjectCalls.getMethodBind("TextureStreaming", "set_min_lod_override", SET_MIN_LOD_OVERRIDE_HASH)

        private const val GET_MAX_LOD_OVERRIDE_HASH = 3905245786L
        @JvmField
        val getMaxLodOverrideBind =
            ObjectCalls.getMethodBind("TextureStreaming", "get_max_lod_override", GET_MAX_LOD_OVERRIDE_HASH)

        private const val SET_MAX_LOD_OVERRIDE_HASH = 1286410249L
        @JvmField
        val setMaxLodOverrideBind =
            ObjectCalls.getMethodBind("TextureStreaming", "set_max_lod_override", SET_MAX_LOD_OVERRIDE_HASH)

        private const val GET_MEMORY_BUDGET_MB_OVERRIDE_HASH = 3905245786L
        @JvmField
        val getMemoryBudgetMbOverrideBind =
            ObjectCalls.getMethodBind("TextureStreaming", "get_memory_budget_mb_override", GET_MEMORY_BUDGET_MB_OVERRIDE_HASH)

        private const val SET_MEMORY_BUDGET_MB_OVERRIDE_HASH = 1286410249L
        @JvmField
        val setMemoryBudgetMbOverrideBind =
            ObjectCalls.getMethodBind("TextureStreaming", "set_memory_budget_mb_override", SET_MEMORY_BUDGET_MB_OVERRIDE_HASH)

        private const val GET_MEMORY_BUDGET_BYTES_USED_HASH = 2455072627L
        @JvmField
        val getMemoryBudgetBytesUsedBind =
            ObjectCalls.getMethodBind("TextureStreaming", "get_memory_budget_bytes_used", GET_MEMORY_BUDGET_BYTES_USED_HASH)

        private const val FLUSH_TEXTURE_STREAMING_HASH = 3218959716L
        @JvmField
        val flushTextureStreamingBind =
            ObjectCalls.getMethodBind("TextureStreaming", "flush_texture_streaming", FLUSH_TEXTURE_STREAMING_HASH)
    }
}

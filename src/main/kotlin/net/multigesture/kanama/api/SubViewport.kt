package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2i

/**
 * An interface to a game world that doesn't create a window or draw to the screen directly.
 *
 * Generated from Godot docs: SubViewport
 */
class SubViewport(handle: MemorySegment) : Viewport(handle) {
    var size: Vector2i
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    var size2dOverride: Vector2i
        @JvmName("size2dOverrideProperty")
        get() = getSize2dOverride()
        @JvmName("setSize2dOverrideProperty")
        set(value) = setSize2dOverride(value)

    var size2dOverrideStretch: Boolean
        @JvmName("size2dOverrideStretchProperty")
        get() = isSize2dOverrideStretchEnabled()
        @JvmName("setSize2dOverrideStretchProperty")
        set(value) = setSize2dOverrideStretch(value)

    var viewCount: Int
        @JvmName("viewCountProperty")
        get() = getViewCount()
        @JvmName("setViewCountProperty")
        set(value) = setViewCount(value)

    var renderTargetClearMode: Long
        @JvmName("renderTargetClearModeProperty")
        get() = getClearMode()
        @JvmName("setRenderTargetClearModeProperty")
        set(value) = setClearMode(value)

    var renderTargetUpdateMode: Long
        @JvmName("renderTargetUpdateModeProperty")
        get() = getUpdateMode()
        @JvmName("setRenderTargetUpdateModeProperty")
        set(value) = setUpdateMode(value)

    /**
     * The width and height of the sub-viewport. Must be set to a value greater than or equal to 2
     * pixels on both dimensions. Otherwise, nothing will be displayed. Note: If the parent node is a
     * `SubViewportContainer` and its `SubViewportContainer.stretch` is `true`, the viewport size
     * cannot be changed manually.
     *
     * Generated from Godot docs: SubViewport.set_size
     */
    fun setSize(size: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setSizeBind, handle, size)
    }

    /**
     * The width and height of the sub-viewport. Must be set to a value greater than or equal to 2
     * pixels on both dimensions. Otherwise, nothing will be displayed. Note: If the parent node is a
     * `SubViewportContainer` and its `SubViewportContainer.stretch` is `true`, the viewport size
     * cannot be changed manually.
     *
     * Generated from Godot docs: SubViewport.get_size
     */
    fun getSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getSizeBind, handle)
    }

    /**
     * The 2D size override of the sub-viewport. If either the width or height is `0`, the override is
     * disabled.
     *
     * Generated from Godot docs: SubViewport.set_size_2d_override
     */
    fun setSize2dOverride(size: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setSize2dOverrideBind, handle, size)
    }

    /**
     * The 2D size override of the sub-viewport. If either the width or height is `0`, the override is
     * disabled.
     *
     * Generated from Godot docs: SubViewport.get_size_2d_override
     */
    fun getSize2dOverride(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getSize2dOverrideBind, handle)
    }

    /**
     * If `true`, the 2D size override affects stretch as well.
     *
     * Generated from Godot docs: SubViewport.set_size_2d_override_stretch
     */
    fun setSize2dOverrideStretch(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSize2dOverrideStretchBind, handle, enable)
    }

    /**
     * If `true`, the 2D size override affects stretch as well.
     *
     * Generated from Godot docs: SubViewport.is_size_2d_override_stretch_enabled
     */
    fun isSize2dOverrideStretchEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSize2dOverrideStretchEnabledBind, handle)
    }

    /**
     * The number of view layers we are rendering to. Set this to `2` to enable stereo rendering.
     *
     * Generated from Godot docs: SubViewport.set_view_count
     */
    fun setViewCount(viewCount: Int) {
        ObjectCalls.ptrcallWithIntArg(setViewCountBind, handle, viewCount)
    }

    /**
     * The number of view layers we are rendering to. Set this to `2` to enable stereo rendering.
     *
     * Generated from Godot docs: SubViewport.get_view_count
     */
    fun getViewCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getViewCountBind, handle)
    }

    /**
     * The update mode when the sub-viewport is used as a render target.
     *
     * Generated from Godot docs: SubViewport.set_update_mode
     */
    fun setUpdateMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setUpdateModeBind, handle, mode)
    }

    /**
     * The update mode when the sub-viewport is used as a render target.
     *
     * Generated from Godot docs: SubViewport.get_update_mode
     */
    fun getUpdateMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getUpdateModeBind, handle)
    }

    /**
     * The clear mode when the sub-viewport is used as a render target. Note: This property is intended
     * for 2D usage.
     *
     * Generated from Godot docs: SubViewport.set_clear_mode
     */
    fun setClearMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setClearModeBind, handle, mode)
    }

    /**
     * The clear mode when the sub-viewport is used as a render target. Note: This property is intended
     * for 2D usage.
     *
     * Generated from Godot docs: SubViewport.get_clear_mode
     */
    fun getClearMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getClearModeBind, handle)
    }

    companion object {
        const val CLEAR_MODE_ALWAYS: Long = 0L
        const val CLEAR_MODE_NEVER: Long = 1L
        const val CLEAR_MODE_ONCE: Long = 2L
        const val UPDATE_DISABLED: Long = 0L
        const val UPDATE_ONCE: Long = 1L
        const val UPDATE_WHEN_VISIBLE: Long = 2L
        const val UPDATE_WHEN_PARENT_VISIBLE: Long = 3L
        const val UPDATE_ALWAYS: Long = 4L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): SubViewport? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SubViewport? =
            if (handle.address() == 0L) null else SubViewport(handle)

        private const val SET_SIZE_HASH = 1130785943L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("SubViewport", "set_size", SET_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 3690982128L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("SubViewport", "get_size", GET_SIZE_HASH)
        }

        private const val SET_SIZE_2D_OVERRIDE_HASH = 1130785943L
        private val setSize2dOverrideBind by lazy {
            ObjectCalls.getMethodBind("SubViewport", "set_size_2d_override", SET_SIZE_2D_OVERRIDE_HASH)
        }

        private const val GET_SIZE_2D_OVERRIDE_HASH = 3690982128L
        private val getSize2dOverrideBind by lazy {
            ObjectCalls.getMethodBind("SubViewport", "get_size_2d_override", GET_SIZE_2D_OVERRIDE_HASH)
        }

        private const val SET_SIZE_2D_OVERRIDE_STRETCH_HASH = 2586408642L
        private val setSize2dOverrideStretchBind by lazy {
            ObjectCalls.getMethodBind("SubViewport", "set_size_2d_override_stretch", SET_SIZE_2D_OVERRIDE_STRETCH_HASH)
        }

        private const val IS_SIZE_2D_OVERRIDE_STRETCH_ENABLED_HASH = 36873697L
        private val isSize2dOverrideStretchEnabledBind by lazy {
            ObjectCalls.getMethodBind("SubViewport", "is_size_2d_override_stretch_enabled", IS_SIZE_2D_OVERRIDE_STRETCH_ENABLED_HASH)
        }

        private const val SET_VIEW_COUNT_HASH = 1286410249L
        private val setViewCountBind by lazy {
            ObjectCalls.getMethodBind("SubViewport", "set_view_count", SET_VIEW_COUNT_HASH)
        }

        private const val GET_VIEW_COUNT_HASH = 3905245786L
        private val getViewCountBind by lazy {
            ObjectCalls.getMethodBind("SubViewport", "get_view_count", GET_VIEW_COUNT_HASH)
        }

        private const val SET_UPDATE_MODE_HASH = 1295690030L
        private val setUpdateModeBind by lazy {
            ObjectCalls.getMethodBind("SubViewport", "set_update_mode", SET_UPDATE_MODE_HASH)
        }

        private const val GET_UPDATE_MODE_HASH = 2980171553L
        private val getUpdateModeBind by lazy {
            ObjectCalls.getMethodBind("SubViewport", "get_update_mode", GET_UPDATE_MODE_HASH)
        }

        private const val SET_CLEAR_MODE_HASH = 2834454712L
        private val setClearModeBind by lazy {
            ObjectCalls.getMethodBind("SubViewport", "set_clear_mode", SET_CLEAR_MODE_HASH)
        }

        private const val GET_CLEAR_MODE_HASH = 331324495L
        private val getClearModeBind by lazy {
            ObjectCalls.getMethodBind("SubViewport", "get_clear_mode", GET_CLEAR_MODE_HASH)
        }
    }
}

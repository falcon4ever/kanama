package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A singleton that provides access to static information about `Theme` resources used by the
 * engine and by your project.
 *
 * Generated from Godot docs: ThemeDB
 */
object ThemeDB {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("ThemeDB")
    }

    var fallbackBaseScale: Double
        @JvmName("fallbackBaseScaleProperty")
        get() = getFallbackBaseScale()
        @JvmName("setFallbackBaseScaleProperty")
        set(value) = setFallbackBaseScale(value)

    var fallbackFont: Font?
        @JvmName("fallbackFontProperty")
        get() = getFallbackFont()
        @JvmName("setFallbackFontProperty")
        set(value) = setFallbackFont(value)

    var fallbackFontSize: Int
        @JvmName("fallbackFontSizeProperty")
        get() = getFallbackFontSize()
        @JvmName("setFallbackFontSizeProperty")
        set(value) = setFallbackFontSize(value)

    var fallbackIcon: Texture2D?
        @JvmName("fallbackIconProperty")
        get() = getFallbackIcon()
        @JvmName("setFallbackIconProperty")
        set(value) = setFallbackIcon(value)

    var fallbackStylebox: StyleBox?
        @JvmName("fallbackStyleboxProperty")
        get() = getFallbackStylebox()
        @JvmName("setFallbackStyleboxProperty")
        set(value) = setFallbackStylebox(value)

    /**
     * Returns a reference to the default engine `Theme`. This theme resource is responsible for the
     * out-of-the-box look of `Control` nodes and cannot be overridden.
     *
     * Generated from Godot docs: ThemeDB.get_default_theme
     */
    @JvmStatic
    fun getDefaultTheme(): Theme? {
        return Theme.wrap(ObjectCalls.ptrcallNoArgsRetObject(getDefaultThemeBind, singleton))
    }

    /**
     * Returns a reference to the custom project `Theme`. This theme resources allows to override the
     * default engine theme for every control node in the project. To set the project theme, see
     * `ProjectSettings.gui/theme/custom`.
     *
     * Generated from Godot docs: ThemeDB.get_project_theme
     */
    @JvmStatic
    fun getProjectTheme(): Theme? {
        return Theme.wrap(ObjectCalls.ptrcallNoArgsRetObject(getProjectThemeBind, singleton))
    }

    /**
     * The fallback base scale factor of every `Control` node and `Theme` resource. Used when no other
     * value is available to the control. See also `Theme.default_base_scale`.
     *
     * Generated from Godot docs: ThemeDB.set_fallback_base_scale
     */
    @JvmStatic
    fun setFallbackBaseScale(baseScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFallbackBaseScaleBind, singleton, baseScale)
    }

    /**
     * The fallback base scale factor of every `Control` node and `Theme` resource. Used when no other
     * value is available to the control. See also `Theme.default_base_scale`.
     *
     * Generated from Godot docs: ThemeDB.get_fallback_base_scale
     */
    @JvmStatic
    fun getFallbackBaseScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFallbackBaseScaleBind, singleton)
    }

    /**
     * The fallback font of every `Control` node and `Theme` resource. Used when no other value is
     * available to the control. See also `Theme.default_font`.
     *
     * Generated from Godot docs: ThemeDB.set_fallback_font
     */
    @JvmStatic
    fun setFallbackFont(font: Font?) {
        ObjectCalls.ptrcallWithObjectArgs(setFallbackFontBind, singleton, listOf(font?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The fallback font of every `Control` node and `Theme` resource. Used when no other value is
     * available to the control. See also `Theme.default_font`.
     *
     * Generated from Godot docs: ThemeDB.get_fallback_font
     */
    @JvmStatic
    fun getFallbackFont(): Font? {
        return Font.wrap(ObjectCalls.ptrcallNoArgsRetObject(getFallbackFontBind, singleton))
    }

    /**
     * The fallback font size of every `Control` node and `Theme` resource. Used when no other value is
     * available to the control. See also `Theme.default_font_size`.
     *
     * Generated from Godot docs: ThemeDB.set_fallback_font_size
     */
    @JvmStatic
    fun setFallbackFontSize(fontSize: Int) {
        ObjectCalls.ptrcallWithIntArg(setFallbackFontSizeBind, singleton, fontSize)
    }

    /**
     * The fallback font size of every `Control` node and `Theme` resource. Used when no other value is
     * available to the control. See also `Theme.default_font_size`.
     *
     * Generated from Godot docs: ThemeDB.get_fallback_font_size
     */
    @JvmStatic
    fun getFallbackFontSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFallbackFontSizeBind, singleton)
    }

    /**
     * The fallback icon of every `Control` node and `Theme` resource. Used when no other value is
     * available to the control.
     *
     * Generated from Godot docs: ThemeDB.set_fallback_icon
     */
    @JvmStatic
    fun setFallbackIcon(icon: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setFallbackIconBind, singleton, listOf(icon?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The fallback icon of every `Control` node and `Theme` resource. Used when no other value is
     * available to the control.
     *
     * Generated from Godot docs: ThemeDB.get_fallback_icon
     */
    @JvmStatic
    fun getFallbackIcon(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getFallbackIconBind, singleton))
    }

    /**
     * The fallback stylebox of every `Control` node and `Theme` resource. Used when no other value is
     * available to the control.
     *
     * Generated from Godot docs: ThemeDB.set_fallback_stylebox
     */
    @JvmStatic
    fun setFallbackStylebox(stylebox: StyleBox?) {
        ObjectCalls.ptrcallWithObjectArgs(setFallbackStyleboxBind, singleton, listOf(stylebox?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The fallback stylebox of every `Control` node and `Theme` resource. Used when no other value is
     * available to the control.
     *
     * Generated from Godot docs: ThemeDB.get_fallback_stylebox
     */
    @JvmStatic
    fun getFallbackStylebox(): StyleBox? {
        return StyleBox.wrap(ObjectCalls.ptrcallNoArgsRetObject(getFallbackStyleboxBind, singleton))
    }

    object Signals {
        const val fallbackChanged: String = "fallback_changed"
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): ThemeDB? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): ThemeDB? =
        if (handle.address() == 0L) null else this

    private const val GET_DEFAULT_THEME_HASH = 754276358L
    private val getDefaultThemeBind by lazy {
        ObjectCalls.getMethodBind("ThemeDB", "get_default_theme", GET_DEFAULT_THEME_HASH)
    }

    private const val GET_PROJECT_THEME_HASH = 754276358L
    private val getProjectThemeBind by lazy {
        ObjectCalls.getMethodBind("ThemeDB", "get_project_theme", GET_PROJECT_THEME_HASH)
    }

    private const val SET_FALLBACK_BASE_SCALE_HASH = 373806689L
    private val setFallbackBaseScaleBind by lazy {
        ObjectCalls.getMethodBind("ThemeDB", "set_fallback_base_scale", SET_FALLBACK_BASE_SCALE_HASH)
    }

    private const val GET_FALLBACK_BASE_SCALE_HASH = 191475506L
    private val getFallbackBaseScaleBind by lazy {
        ObjectCalls.getMethodBind("ThemeDB", "get_fallback_base_scale", GET_FALLBACK_BASE_SCALE_HASH)
    }

    private const val SET_FALLBACK_FONT_HASH = 1262170328L
    private val setFallbackFontBind by lazy {
        ObjectCalls.getMethodBind("ThemeDB", "set_fallback_font", SET_FALLBACK_FONT_HASH)
    }

    private const val GET_FALLBACK_FONT_HASH = 3656929885L
    private val getFallbackFontBind by lazy {
        ObjectCalls.getMethodBind("ThemeDB", "get_fallback_font", GET_FALLBACK_FONT_HASH)
    }

    private const val SET_FALLBACK_FONT_SIZE_HASH = 1286410249L
    private val setFallbackFontSizeBind by lazy {
        ObjectCalls.getMethodBind("ThemeDB", "set_fallback_font_size", SET_FALLBACK_FONT_SIZE_HASH)
    }

    private const val GET_FALLBACK_FONT_SIZE_HASH = 2455072627L
    private val getFallbackFontSizeBind by lazy {
        ObjectCalls.getMethodBind("ThemeDB", "get_fallback_font_size", GET_FALLBACK_FONT_SIZE_HASH)
    }

    private const val SET_FALLBACK_ICON_HASH = 4051416890L
    private val setFallbackIconBind by lazy {
        ObjectCalls.getMethodBind("ThemeDB", "set_fallback_icon", SET_FALLBACK_ICON_HASH)
    }

    private const val GET_FALLBACK_ICON_HASH = 255860311L
    private val getFallbackIconBind by lazy {
        ObjectCalls.getMethodBind("ThemeDB", "get_fallback_icon", GET_FALLBACK_ICON_HASH)
    }

    private const val SET_FALLBACK_STYLEBOX_HASH = 2797200388L
    private val setFallbackStyleboxBind by lazy {
        ObjectCalls.getMethodBind("ThemeDB", "set_fallback_stylebox", SET_FALLBACK_STYLEBOX_HASH)
    }

    private const val GET_FALLBACK_STYLEBOX_HASH = 496040854L
    private val getFallbackStyleboxBind by lazy {
        ObjectCalls.getMethodBind("ThemeDB", "get_fallback_stylebox", GET_FALLBACK_STYLEBOX_HASH)
    }
}

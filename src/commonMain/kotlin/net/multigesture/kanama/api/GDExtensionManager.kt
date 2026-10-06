package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Provides access to GDExtension functionality.
 *
 * Generated from Godot docs: GDExtensionManager
 */
object GDExtensionManager {
    private inline val singleton: RawSegment
        get() = Binds.singleton

    /**
     * Loads an extension by absolute file path. The `path` needs to point to a valid `GDExtension`.
     * Returns `LoadStatus.OK` if successful.
     *
     * Generated from Godot docs: GDExtensionManager.load_extension
     */
    @JvmStatic
    fun loadExtension(path: String): GDExtensionManager.LoadStatus {
        return GDExtensionManager.LoadStatus(ObjectCalls.ptrcallWithStringArgRetLong(Binds.loadExtensionBind, singleton, path))
    }

    /**
     * Reloads the extension at the given file path. The `path` needs to point to a valid
     * `GDExtension`, otherwise this method may return either `LoadStatus.NOT_LOADED` or
     * `LoadStatus.FAILED`. Note: You can only reload extensions in the editor. In release builds, this
     * method always fails and returns `LoadStatus.FAILED`.
     *
     * Generated from Godot docs: GDExtensionManager.reload_extension
     */
    @JvmStatic
    fun reloadExtension(path: String): GDExtensionManager.LoadStatus {
        return GDExtensionManager.LoadStatus(ObjectCalls.ptrcallWithStringArgRetLong(Binds.reloadExtensionBind, singleton, path))
    }

    /**
     * Unloads an extension by file path. The `path` needs to point to an already loaded `GDExtension`,
     * otherwise this method returns `LoadStatus.NOT_LOADED`.
     *
     * Generated from Godot docs: GDExtensionManager.unload_extension
     */
    @JvmStatic
    fun unloadExtension(path: String): GDExtensionManager.LoadStatus {
        return GDExtensionManager.LoadStatus(ObjectCalls.ptrcallWithStringArgRetLong(Binds.unloadExtensionBind, singleton, path))
    }

    /**
     * Returns `true` if the extension at the given file `path` has already been loaded successfully.
     * See also `get_loaded_extensions`.
     *
     * Generated from Godot docs: GDExtensionManager.is_extension_loaded
     */
    @JvmStatic
    fun isExtensionLoaded(path: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(Binds.isExtensionLoadedBind, singleton, path)
    }

    /**
     * Returns the file paths of all currently loaded extensions.
     *
     * Generated from Godot docs: GDExtensionManager.get_loaded_extensions
     */
    @JvmStatic
    fun getLoadedExtensions(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getLoadedExtensionsBind, singleton)
    }

    /**
     * Returns the `GDExtension` at the given file `path`, or `null` if it has not been loaded or does
     * not exist.
     *
     * Generated from Godot docs: GDExtensionManager.get_extension
     */
    @JvmStatic
    fun getExtension(path: String): GDExtension? {
        return GDExtension.wrapOwned(ObjectCalls.ptrcallWithStringArgRetObject(Binds.getExtensionBind, singleton, path))
    }

    /** Signal `extensions_reloaded()`; see [TypedSignal]. */
    val extensionsReloaded: Signal0
        @JvmName("extensionsReloadedTypedSignal")
        get() = Signal0(GodotObject(GodotHandle(singleton)), "extensions_reloaded")

    /** Signal `extension_loaded(extension: GDExtension)`; see [TypedSignal]. */
    val extensionLoaded: Signal1<GDExtension>
        @JvmName("extensionLoadedTypedSignal")
        get() = Signal1(GodotObject(GodotHandle(singleton)), "extension_loaded", SignalArgType.objectOf("GDExtension") { GDExtension(it) })

    /** Signal `extension_unloading(extension: GDExtension)`; see [TypedSignal]. */
    val extensionUnloading: Signal1<GDExtension>
        @JvmName("extensionUnloadingTypedSignal")
        get() = Signal1(GodotObject(GodotHandle(singleton)), "extension_unloading", SignalArgType.objectOf("GDExtension") { GDExtension(it) })

    object Signals {
        const val extensionsReloaded: String = "extensions_reloaded"
        const val extensionLoaded: String = "extension_loaded"
        const val extensionUnloading: String = "extension_unloading"
    }

    /**
     * Godot's `GDExtensionManager.LoadStatus` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`GDExtensionManager.LoadStatus.<NAME>`).
     *
     * Generated from Godot docs: GDExtensionManager.LoadStatus
     */
    @JvmInline
    value class LoadStatus(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The extension has loaded successfully.
             *
             * Generated from Godot docs: GDExtensionManager.LOAD_STATUS_OK
             */
            val OK: LoadStatus get() = LoadStatus(0L)
            /**
             * The extension has failed to load, possibly because it does not exist or has missing
             * dependencies.
             *
             * Generated from Godot docs: GDExtensionManager.LOAD_STATUS_FAILED
             */
            val FAILED: LoadStatus get() = LoadStatus(1L)
            /**
             * The extension has already been loaded.
             *
             * Generated from Godot docs: GDExtensionManager.LOAD_STATUS_ALREADY_LOADED
             */
            val ALREADY_LOADED: LoadStatus get() = LoadStatus(2L)
            /**
             * The extension has not been loaded.
             *
             * Generated from Godot docs: GDExtensionManager.LOAD_STATUS_NOT_LOADED
             */
            val NOT_LOADED: LoadStatus get() = LoadStatus(3L)
            /**
             * The extension requires the application to restart to fully load.
             *
             * Generated from Godot docs: GDExtensionManager.LOAD_STATUS_NEEDS_RESTART
             */
            val NEEDS_RESTART: LoadStatus get() = LoadStatus(4L)
        }
    }

    @JvmStatic
    fun fromHandle(handle: GodotHandle): GDExtensionManager? =
        wrap(handle.segment)

    internal fun wrap(handle: RawSegment): GDExtensionManager? =
        if (handle.address() == 0L) null else this

    private object Binds {
        @JvmField
        val singleton = ObjectCalls.getSingleton("GDExtensionManager")

        private const val LOAD_EXTENSION_HASH = 4024158731L
        @JvmField
        val loadExtensionBind =
            ObjectCalls.getMethodBind("GDExtensionManager", "load_extension", LOAD_EXTENSION_HASH)

        private const val RELOAD_EXTENSION_HASH = 4024158731L
        @JvmField
        val reloadExtensionBind =
            ObjectCalls.getMethodBind("GDExtensionManager", "reload_extension", RELOAD_EXTENSION_HASH)

        private const val UNLOAD_EXTENSION_HASH = 4024158731L
        @JvmField
        val unloadExtensionBind =
            ObjectCalls.getMethodBind("GDExtensionManager", "unload_extension", UNLOAD_EXTENSION_HASH)

        private const val IS_EXTENSION_LOADED_HASH = 3927539163L
        @JvmField
        val isExtensionLoadedBind =
            ObjectCalls.getMethodBind("GDExtensionManager", "is_extension_loaded", IS_EXTENSION_LOADED_HASH)

        private const val GET_LOADED_EXTENSIONS_HASH = 1139954409L
        @JvmField
        val getLoadedExtensionsBind =
            ObjectCalls.getMethodBind("GDExtensionManager", "get_loaded_extensions", GET_LOADED_EXTENSIONS_HASH)

        private const val GET_EXTENSION_HASH = 49743343L
        @JvmField
        val getExtensionBind =
            ObjectCalls.getMethodBind("GDExtensionManager", "get_extension", GET_EXTENSION_HASH)
    }
}

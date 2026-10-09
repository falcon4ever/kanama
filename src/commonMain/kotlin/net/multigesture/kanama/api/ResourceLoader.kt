package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A singleton for loading resource files.
 *
 * Generated from Godot docs: ResourceLoader
 */
object ResourceLoader {
    private inline val singleton: RawSegment
        get() = Binds.singleton

    /**
     * Loads the resource using threads. If `use_sub_threads` is `true`, multiple threads will be used
     * to load the resource, which makes loading faster, but may affect the main thread (and thus cause
     * game slowdowns). The `cache_mode` parameter defines whether and how the cache should be used or
     * updated when loading the resource.
     *
     * Generated from Godot docs: ResourceLoader.load_threaded_request
     */
    @JvmStatic
    fun loadThreadedRequest(path: String, typeHint: String = "", useSubThreads: Boolean = false, cacheMode: ResourceLoader.CacheMode = ResourceLoader.CacheMode.REUSE): GodotError {
        return GodotError(ObjectCalls.ptrcallWithTwoStringBoolLongArgsRetLong(Binds.loadThreadedRequestBind, singleton, path, typeHint, useSubThreads, cacheMode.value))
    }

    /**
     * Returns the status of a threaded loading operation started with `load_threaded_request` for the
     * resource at `path`. An array variable can optionally be passed via `progress`, and will return a
     * one-element array containing the ratio of completion of the threaded loading (between `0.0` and
     * `1.0`). Note: The recommended way of using this method is to call it during different frames
     * (e.g., in `Node._process`, instead of a loop).
     *
     * Generated from Godot docs: ResourceLoader.load_threaded_get_status
     */
    @JvmStatic
    fun loadThreadedGetStatus(path: String, progress: List<Any?> = emptyList()): ResourceLoader.ThreadLoadStatus {
        return ResourceLoader.ThreadLoadStatus(ObjectCalls.ptrcallWithStringAndArrayArgRetLong(Binds.loadThreadedGetStatusBind, singleton, path, progress))
    }

    /**
     * Returns the resource loaded by `load_threaded_request`. If this is called before the loading
     * thread is done (i.e. `load_threaded_get_status` is not `ThreadLoadStatus.LOADED`), the calling
     * thread will be blocked until the resource has finished loading. However, it's recommended to use
     * `load_threaded_get_status` to known when the load has actually completed.
     *
     * Generated from Godot docs: ResourceLoader.load_threaded_get
     */
    @JvmStatic
    fun loadThreadedGet(path: String): Resource? {
        return Resource.wrapOwned(ObjectCalls.ptrcallWithStringArgRetObject(Binds.loadThreadedGetBind, singleton, path))
    }

    /**
     * Loads a resource at the given `path`, caching the result for further access. The registered
     * `ResourceFormatLoader`s are queried sequentially to find the first one which can handle the
     * file's extension, and then attempt loading. If loading fails, the remaining
     * ResourceFormatLoaders are also attempted. An optional `type_hint` can be used to further specify
     * the `Resource` type that should be handled by the `ResourceFormatLoader`. Anything that inherits
     * from `Resource` can be used as a type hint, for example `Image`. The `cache_mode` property
     * defines whether and how the cache should be used or updated when loading the resource. Returns
     * an empty resource if no `ResourceFormatLoader` could handle the file, and prints an error if no
     * file is found at the specified path. GDScript has a simplified `@GDScript.load` built-in method
     * which can be used in most situations, leaving the use of `ResourceLoader` for more advanced
     * scenarios. Note: If `ProjectSettings.editor/export/convert_text_resources_to_binary` is `true`,
     * `@GDScript.load` will not be able to read converted files in an exported project. If you rely on
     * run-time loading of files present within the PCK, set
     * `ProjectSettings.editor/export/convert_text_resources_to_binary` to `false`. Note: Relative
     * paths will be prefixed with `"res://"` before loading, to avoid unexpected results make sure
     * your paths are absolute.
     *
     * Generated from Godot docs: ResourceLoader.load
     */
    @JvmStatic
    fun load(path: String, typeHint: String = "", cacheMode: ResourceLoader.CacheMode = ResourceLoader.CacheMode.REUSE): Resource? {
        return Resource.wrapOwned(ObjectCalls.ptrcallWithTwoStringAndLongArgsRetObject(Binds.loadBind, singleton, path, typeHint, cacheMode.value))
    }

    /**
     * Returns the list of recognized extensions for a resource type.
     *
     * Generated from Godot docs: ResourceLoader.get_recognized_extensions_for_type
     */
    @JvmStatic
    fun getRecognizedExtensionsForType(type: String): List<String> {
        return ObjectCalls.ptrcallWithStringArgRetPackedStringList(Binds.getRecognizedExtensionsForTypeBind, singleton, type)
    }

    /**
     * Registers a new `ResourceFormatLoader`. The ResourceLoader will use the ResourceFormatLoader as
     * described in `load`. This method is performed implicitly for ResourceFormatLoaders written in
     * GDScript (see `ResourceFormatLoader` for more information).
     *
     * Generated from Godot docs: ResourceLoader.add_resource_format_loader
     */
    @JvmStatic
    fun addResourceFormatLoader(formatLoader: ResourceFormatLoader, atFront: Boolean = false) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(Binds.addResourceFormatLoaderBind, singleton, formatLoader.requireOpenHandle(), atFront)
    }

    /**
     * Unregisters the given `ResourceFormatLoader`.
     *
     * Generated from Godot docs: ResourceLoader.remove_resource_format_loader
     */
    @JvmStatic
    fun removeResourceFormatLoader(formatLoader: ResourceFormatLoader) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeResourceFormatLoaderBind, singleton, listOf(formatLoader.requireOpenHandle()))
    }

    /**
     * Changes the behavior on missing sub-resources. The default behavior is to abort loading.
     *
     * Generated from Godot docs: ResourceLoader.set_abort_on_missing_resources
     */
    @JvmStatic
    fun setAbortOnMissingResources(abort: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAbortOnMissingResourcesBind, singleton, abort)
    }

    /**
     * Returns the dependencies for the resource at the given `path`. Each dependency is a string that
     * can be divided into sections by `::`. There can be either one section or three sections, with
     * the second section always being empty. When there is one section, it contains the file path.
     * When there are three sections, the first section contains the UID and the third section contains
     * the fallback path.
     *
     * Generated from Godot docs: ResourceLoader.get_dependencies
     */
    @JvmStatic
    fun getDependencies(path: String): List<String> {
        return ObjectCalls.ptrcallWithStringArgRetPackedStringList(Binds.getDependenciesBind, singleton, path)
    }

    /**
     * Returns whether a cached resource is available for the given `path`. Once a resource has been
     * loaded by the engine, it is cached in memory for faster access, and future calls to the `load`
     * method will use the cached version. The cached resource can be overridden by using
     * `Resource.take_over_path` on a new resource for that same path.
     *
     * Generated from Godot docs: ResourceLoader.has_cached
     */
    @JvmStatic
    fun hasCached(path: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(Binds.hasCachedBind, singleton, path)
    }

    /**
     * Returns the cached resource reference for the given `path`. Note: If the resource is not cached,
     * the returned `Resource` will be invalid.
     *
     * Generated from Godot docs: ResourceLoader.get_cached_ref
     */
    @JvmStatic
    fun getCachedRef(path: String): Resource? {
        return Resource.wrapOwned(ObjectCalls.ptrcallWithStringArgRetObject(Binds.getCachedRefBind, singleton, path))
    }

    /**
     * Returns whether a recognized resource exists for the given `path`. An optional `type_hint` can
     * be used to further specify the `Resource` type that should be handled by the
     * `ResourceFormatLoader`. Anything that inherits from `Resource` can be used as a type hint, for
     * example `Image`. Note: If you use `Resource.take_over_path`, this method will return `true` for
     * the taken path even if the resource wasn't saved (i.e. exists only in resource cache).
     *
     * Generated from Godot docs: ResourceLoader.exists
     */
    @JvmStatic
    fun exists(path: String, typeHint: String = ""): Boolean {
        return ObjectCalls.ptrcallWithTwoStringArgsRetBool(Binds.existsBind, singleton, path, typeHint)
    }

    /**
     * Returns the ID associated with a given resource path, or `-1` when no such ID exists.
     *
     * Generated from Godot docs: ResourceLoader.get_resource_uid
     */
    @JvmStatic
    fun getResourceUid(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(Binds.getResourceUidBind, singleton, path)
    }

    /**
     * Lists a directory, returning all resources and subdirectories contained within. The resource
     * files have the original file names as visible in the editor before exporting. The directories
     * have `"/"` appended.
     *
     * Generated from Godot docs: ResourceLoader.list_directory
     */
    @JvmStatic
    fun listDirectory(directoryPath: String): List<String> {
        return ObjectCalls.ptrcallWithStringArgRetPackedStringList(Binds.listDirectoryBind, singleton, directoryPath)
    }

    /**
     * [loadThreadedGetStatusWithProgress]'s result: the status and, when Godot reports it, the
     * completion ratio. (Named `ThreadLoadStatus` before task 128 A, when that name became Godot's
     * enum `ResourceLoader.ThreadLoadStatus`.)
     */
    data class ThreadLoadProgress(val status: ResourceLoader.ThreadLoadStatus, val progress: Double?)

    /** [loadThreadedGetStatus] with the progress (0..1) Godot reports for [path]. */
    @JvmStatic
    fun loadThreadedGetStatusWithProgress(path: String): ThreadLoadProgress {
        val (status, progress) = ObjectCalls.ptrcallLoadStatusWithProgress(Binds.loadThreadedGetStatusBind, singleton, path)
        return if (status < 0L) {
            ThreadLoadProgress(ResourceLoader.ThreadLoadStatus.INVALID_RESOURCE, null)
        } else {
            ThreadLoadProgress(ResourceLoader.ThreadLoadStatus(status), progress)
        }
    }

    // The typed loaders (TYPED_LOADERS) wrap what Godot returns as the type they name; a resource of
    // another class is released and reported, like GDScript's typed-assignment error.
    private fun <T : RefCounted> typedResource(loaded: T?, expected: String, path: String): T? {
        if (loaded == null || loaded.isClass(expected)) return loaded
        val actual = loaded.getClassName()
        loaded.close()
        GD.pushError("ResourceLoader: '$path' is a $actual, not a $expected")
        return null
    }

    /** [load] with the `PackedScene` type hint (null and a Godot error when the resource is another class). */
    @JvmStatic
    fun loadPackedScene(path: String, cacheMode: ResourceLoader.CacheMode = ResourceLoader.CacheMode.REUSE): PackedScene? =
        typedResource(
            PackedScene.wrapOwned(
                ObjectCalls.ptrcallWithTwoStringAndLongArgsRetObject(Binds.loadBind, singleton, path, "PackedScene", cacheMode.value),
            ),
            "PackedScene",
            path,
        )

    /** [loadThreadedGet] as a `PackedScene` (null and a Godot error when the resource is another class). */
    @JvmStatic
    fun loadThreadedGetPackedScene(path: String): PackedScene? =
        typedResource(
            PackedScene.wrapOwned(ObjectCalls.ptrcallWithStringArgRetObject(Binds.loadThreadedGetBind, singleton, path)),
            "PackedScene",
            path,
        )

    /** [load] with the `Texture2D` type hint (null and a Godot error when the resource is another class). */
    @JvmStatic
    fun loadTexture2D(path: String, cacheMode: ResourceLoader.CacheMode = ResourceLoader.CacheMode.REUSE): Texture2D? =
        typedResource(
            Texture2D.wrapOwned(
                ObjectCalls.ptrcallWithTwoStringAndLongArgsRetObject(Binds.loadBind, singleton, path, "Texture2D", cacheMode.value),
            ),
            "Texture2D",
            path,
        )

    /** [load] with the `AudioStream` type hint (null and a Godot error when the resource is another class). */
    @JvmStatic
    fun loadAudioStream(path: String, cacheMode: ResourceLoader.CacheMode = ResourceLoader.CacheMode.REUSE): AudioStream? =
        typedResource(
            AudioStream.wrapOwned(
                ObjectCalls.ptrcallWithTwoStringAndLongArgsRetObject(Binds.loadBind, singleton, path, "AudioStream", cacheMode.value),
            ),
            "AudioStream",
            path,
        )

    /** [load] with the `LightmapGIData` type hint (null and a Godot error when the resource is another class). */
    @JvmStatic
    fun loadLightmapGIData(path: String, cacheMode: ResourceLoader.CacheMode = ResourceLoader.CacheMode.REUSE): LightmapGIData? =
        typedResource(
            LightmapGIData.wrapOwned(
                ObjectCalls.ptrcallWithTwoStringAndLongArgsRetObject(Binds.loadBind, singleton, path, "LightmapGIData", cacheMode.value),
            ),
            "LightmapGIData",
            path,
        )

    /**
     * Godot's `ResourceLoader.ThreadLoadStatus` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`ResourceLoader.ThreadLoadStatus.<NAME>`).
     *
     * Generated from Godot docs: ResourceLoader.ThreadLoadStatus
     */
    @JvmInline
    value class ThreadLoadStatus(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The resource is invalid, or has not been loaded with `load_threaded_request`.
             *
             * Generated from Godot docs: ResourceLoader.THREAD_LOAD_INVALID_RESOURCE
             */
            val INVALID_RESOURCE: ThreadLoadStatus get() = ThreadLoadStatus(0L)
            /**
             * The resource is still being loaded.
             *
             * Generated from Godot docs: ResourceLoader.THREAD_LOAD_IN_PROGRESS
             */
            val IN_PROGRESS: ThreadLoadStatus get() = ThreadLoadStatus(1L)
            /**
             * Some error occurred during loading and it failed.
             *
             * Generated from Godot docs: ResourceLoader.THREAD_LOAD_FAILED
             */
            val FAILED: ThreadLoadStatus get() = ThreadLoadStatus(2L)
            /**
             * The resource was loaded successfully and can be accessed via `load_threaded_get`.
             *
             * Generated from Godot docs: ResourceLoader.THREAD_LOAD_LOADED
             */
            val LOADED: ThreadLoadStatus get() = ThreadLoadStatus(3L)
        }
    }

    /**
     * Godot's `ResourceLoader.CacheMode` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`ResourceLoader.CacheMode.<NAME>`).
     *
     * Generated from Godot docs: ResourceLoader.CacheMode
     */
    @JvmInline
    value class CacheMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Neither the main resource (the one requested to be loaded) nor any of its subresources are
             * retrieved from cache nor stored into it. Dependencies (external resources) are loaded with
             * `CacheMode.REUSE`.
             *
             * Generated from Godot docs: ResourceLoader.CACHE_MODE_IGNORE
             */
            val IGNORE: CacheMode get() = CacheMode(0L)
            /**
             * The main resource (the one requested to be loaded), its subresources, and its dependencies
             * (external resources) are retrieved from cache if present, instead of loaded. Those not cached
             * are loaded and then stored into the cache. The same rules are propagated recursively down the
             * tree of dependencies (external resources).
             *
             * Generated from Godot docs: ResourceLoader.CACHE_MODE_REUSE
             */
            val REUSE: CacheMode get() = CacheMode(1L)
            /**
             * Like `CacheMode.REUSE`, but the cache is checked for the main resource (the one requested to be
             * loaded) as well as for each of its subresources. Those already in the cache, as long as the
             * loaded and cached types match, have their data refreshed from storage into the already existing
             * instances. Otherwise, they are recreated as completely new objects.
             *
             * Generated from Godot docs: ResourceLoader.CACHE_MODE_REPLACE
             */
            val REPLACE: CacheMode get() = CacheMode(2L)
            /**
             * Like `CacheMode.IGNORE`, but propagated recursively down the tree of dependencies (external
             * resources).
             *
             * Generated from Godot docs: ResourceLoader.CACHE_MODE_IGNORE_DEEP
             */
            val IGNORE_DEEP: CacheMode get() = CacheMode(3L)
            /**
             * Like `CacheMode.REPLACE`, but propagated recursively down the tree of dependencies (external
             * resources).
             *
             * Generated from Godot docs: ResourceLoader.CACHE_MODE_REPLACE_DEEP
             */
            val REPLACE_DEEP: CacheMode get() = CacheMode(4L)
        }
    }

    @JvmStatic
    fun fromHandle(handle: GodotHandle): ResourceLoader? =
        wrap(handle.segment)

    internal fun wrap(handle: RawSegment): ResourceLoader? =
        if (handle.address() == 0L) null else this

    private object Binds {
        @JvmField
        val singleton = ObjectCalls.getSingleton("ResourceLoader")

        private const val LOAD_THREADED_REQUEST_HASH = 3614384323L
        @JvmField
        val loadThreadedRequestBind =
            ObjectCalls.getMethodBind("ResourceLoader", "load_threaded_request", LOAD_THREADED_REQUEST_HASH)

        private const val LOAD_THREADED_GET_STATUS_HASH = 4137685479L
        @JvmField
        val loadThreadedGetStatusBind =
            ObjectCalls.getMethodBind("ResourceLoader", "load_threaded_get_status", LOAD_THREADED_GET_STATUS_HASH)

        private const val LOAD_THREADED_GET_HASH = 1748875256L
        @JvmField
        val loadThreadedGetBind =
            ObjectCalls.getMethodBind("ResourceLoader", "load_threaded_get", LOAD_THREADED_GET_HASH)

        private const val LOAD_HASH = 3358495409L
        @JvmField
        val loadBind =
            ObjectCalls.getMethodBind("ResourceLoader", "load", LOAD_HASH)

        private const val GET_RECOGNIZED_EXTENSIONS_FOR_TYPE_HASH = 3538744774L
        @JvmField
        val getRecognizedExtensionsForTypeBind =
            ObjectCalls.getMethodBind("ResourceLoader", "get_recognized_extensions_for_type", GET_RECOGNIZED_EXTENSIONS_FOR_TYPE_HASH)

        private const val ADD_RESOURCE_FORMAT_LOADER_HASH = 2896595483L
        @JvmField
        val addResourceFormatLoaderBind =
            ObjectCalls.getMethodBind("ResourceLoader", "add_resource_format_loader", ADD_RESOURCE_FORMAT_LOADER_HASH)

        private const val REMOVE_RESOURCE_FORMAT_LOADER_HASH = 405397102L
        @JvmField
        val removeResourceFormatLoaderBind =
            ObjectCalls.getMethodBind("ResourceLoader", "remove_resource_format_loader", REMOVE_RESOURCE_FORMAT_LOADER_HASH)

        private const val SET_ABORT_ON_MISSING_RESOURCES_HASH = 2586408642L
        @JvmField
        val setAbortOnMissingResourcesBind =
            ObjectCalls.getMethodBind("ResourceLoader", "set_abort_on_missing_resources", SET_ABORT_ON_MISSING_RESOURCES_HASH)

        private const val GET_DEPENDENCIES_HASH = 3538744774L
        @JvmField
        val getDependenciesBind =
            ObjectCalls.getMethodBind("ResourceLoader", "get_dependencies", GET_DEPENDENCIES_HASH)

        private const val HAS_CACHED_HASH = 2323990056L
        @JvmField
        val hasCachedBind =
            ObjectCalls.getMethodBind("ResourceLoader", "has_cached", HAS_CACHED_HASH)

        private const val GET_CACHED_REF_HASH = 1748875256L
        @JvmField
        val getCachedRefBind =
            ObjectCalls.getMethodBind("ResourceLoader", "get_cached_ref", GET_CACHED_REF_HASH)

        private const val EXISTS_HASH = 4185558881L
        @JvmField
        val existsBind =
            ObjectCalls.getMethodBind("ResourceLoader", "exists", EXISTS_HASH)

        private const val GET_RESOURCE_UID_HASH = 1597066294L
        @JvmField
        val getResourceUidBind =
            ObjectCalls.getMethodBind("ResourceLoader", "get_resource_uid", GET_RESOURCE_UID_HASH)

        private const val LIST_DIRECTORY_HASH = 3538744774L
        @JvmField
        val listDirectoryBind =
            ObjectCalls.getMethodBind("ResourceLoader", "list_directory", LIST_DIRECTORY_HASH)
    }
}

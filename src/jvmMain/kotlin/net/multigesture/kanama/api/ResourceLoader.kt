package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import java.lang.foreign.MemorySegment

/**
 * A singleton for loading resource files.
 *
 * Generated from Godot docs: ResourceLoader
 */
object ResourceLoader {
    data class ThreadLoadStatus(val status: Long, val progress: Double?)

    private const val STRING_PACKED_STRING_ARRAY_HASH = 3538744774L
    private const val STRING_BOOL_HASH = 2323990056L
    private const val STRING_STRING_BOOL_HASH = 4185558881L
    private const val STRING_LONG_HASH = 1597066294L
    private const val LOAD_THREADED_REQUEST_HASH = 3614384323L
    private const val LOAD_THREADED_GET_STATUS_HASH = 4137685479L
    private const val LOAD_HASH = 3358495409L
    private const val GET_CACHED_REF_HASH = 1748875256L
    private const val ADD_RESOURCE_FORMAT_LOADER_HASH = 2896595483L
    private const val REMOVE_RESOURCE_FORMAT_LOADER_HASH = 405397102L
    private const val SET_ABORT_ON_MISSING_RESOURCES_HASH = 2586408642L

    const val THREAD_LOAD_INVALID_RESOURCE = 0L
    const val THREAD_LOAD_IN_PROGRESS = 1L
    const val THREAD_LOAD_FAILED = 2L
    const val THREAD_LOAD_LOADED = 3L

    const val CACHE_MODE_IGNORE = 0L
    const val CACHE_MODE_REUSE = 1L
    const val CACHE_MODE_REPLACE = 2L
    const val CACHE_MODE_IGNORE_DEEP = 3L
    const val CACHE_MODE_REPLACE_DEEP = 4L

    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("ResourceLoader")
    }

    private val getRecognizedExtensionsForTypeBind by lazy {
        ObjectCalls.getMethodBind(
            "ResourceLoader",
            "get_recognized_extensions_for_type",
            STRING_PACKED_STRING_ARRAY_HASH,
        )
    }

    private val getDependenciesBind by lazy {
        ObjectCalls.getMethodBind("ResourceLoader", "get_dependencies", STRING_PACKED_STRING_ARRAY_HASH)
    }

    private val hasCachedBind by lazy {
        ObjectCalls.getMethodBind("ResourceLoader", "has_cached", STRING_BOOL_HASH)
    }

    private val existsBind by lazy {
        ObjectCalls.getMethodBind("ResourceLoader", "exists", STRING_STRING_BOOL_HASH)
    }

    private val getResourceUidBind by lazy {
        ObjectCalls.getMethodBind("ResourceLoader", "get_resource_uid", STRING_LONG_HASH)
    }

    private val listDirectoryBind by lazy {
        ObjectCalls.getMethodBind("ResourceLoader", "list_directory", STRING_PACKED_STRING_ARRAY_HASH)
    }

    private val loadThreadedRequestBind by lazy {
        ObjectCalls.getMethodBind("ResourceLoader", "load_threaded_request", LOAD_THREADED_REQUEST_HASH)
    }

    private val loadThreadedGetStatusBind by lazy {
        ObjectCalls.getMethodBind("ResourceLoader", "load_threaded_get_status", LOAD_THREADED_GET_STATUS_HASH)
    }

    private val loadThreadedGetBind by lazy {
        ObjectCalls.getMethodBind("ResourceLoader", "load_threaded_get", GET_CACHED_REF_HASH)
    }

    private val loadBind by lazy {
        ObjectCalls.getMethodBind("ResourceLoader", "load", LOAD_HASH)
    }

    private val getCachedRefBind by lazy {
        ObjectCalls.getMethodBind("ResourceLoader", "get_cached_ref", GET_CACHED_REF_HASH)
    }

    private val addResourceFormatLoaderBind by lazy {
        ObjectCalls.getMethodBind("ResourceLoader", "add_resource_format_loader", ADD_RESOURCE_FORMAT_LOADER_HASH)
    }

    private val removeResourceFormatLoaderBind by lazy {
        ObjectCalls.getMethodBind("ResourceLoader", "remove_resource_format_loader", REMOVE_RESOURCE_FORMAT_LOADER_HASH)
    }

    private val setAbortOnMissingResourcesBind by lazy {
        ObjectCalls.getMethodBind("ResourceLoader", "set_abort_on_missing_resources", SET_ABORT_ON_MISSING_RESOURCES_HASH)
    }

    /**
     * Returns the list of recognized extensions for a resource type.
     *
     * Generated from Godot docs: ResourceLoader.get_recognized_extensions_for_type
     */
    @JvmStatic
    fun getRecognizedExtensionsForType(type: String): List<String> =
        ObjectCalls.ptrcallWithStringArgRetPackedStringList(getRecognizedExtensionsForTypeBind, singleton, type)

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
    fun getDependencies(path: String): List<String> =
        ObjectCalls.ptrcallWithStringArgRetPackedStringList(getDependenciesBind, singleton, path)

    /**
     * Returns whether a cached resource is available for the given `path`. Once a resource has been
     * loaded by the engine, it is cached in memory for faster access, and future calls to the `load`
     * method will use the cached version. The cached resource can be overridden by using
     * `Resource.take_over_path` on a new resource for that same path.
     *
     * Generated from Godot docs: ResourceLoader.has_cached
     */
    @JvmStatic
    fun hasCached(path: String): Boolean =
        ObjectCalls.ptrcallWithStringArgRetBool(hasCachedBind, singleton, path)

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
    fun exists(path: String, typeHint: String = ""): Boolean =
        ObjectCalls.ptrcallWithTwoStringArgsRetBool(existsBind, singleton, path, typeHint)

    /**
     * Returns the ID associated with a given resource path, or `-1` when no such ID exists.
     *
     * Generated from Godot docs: ResourceLoader.get_resource_uid
     */
    @JvmStatic
    fun getResourceUid(path: String): Long =
        ObjectCalls.ptrcallWithStringArgRetLong(getResourceUidBind, singleton, path)

    /**
     * Lists a directory, returning all resources and subdirectories contained within. The resource
     * files have the original file names as visible in the editor before exporting. The directories
     * have `"/"` appended.
     *
     * Generated from Godot docs: ResourceLoader.list_directory
     */
    @JvmStatic
    fun listDirectory(directoryPath: String): List<String> =
        ObjectCalls.ptrcallWithStringArgRetPackedStringList(listDirectoryBind, singleton, directoryPath)

    /**
     * Loads the resource using threads. If `use_sub_threads` is `true`, multiple threads will be used
     * to load the resource, which makes loading faster, but may affect the main thread (and thus cause
     * game slowdowns). The `cache_mode` parameter defines whether and how the cache should be used or
     * updated when loading the resource.
     *
     * Generated from Godot docs: ResourceLoader.load_threaded_request
     */
    @JvmStatic
    fun loadThreadedRequest(
        path: String,
        typeHint: String = "",
        useSubThreads: Boolean = false,
        cacheMode: Long = CACHE_MODE_REUSE,
    ): Long =
        ObjectCalls.ptrcallWithTwoStringBoolLongArgsRetLong(
            loadThreadedRequestBind,
            singleton,
            path,
            typeHint,
            useSubThreads,
            cacheMode,
        )

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
    fun loadThreadedGetStatus(path: String, progress: List<Any?> = emptyList()): Long =
        ObjectCalls.ptrcallWithStringAndArrayArgRetLong(loadThreadedGetStatusBind, singleton, path, progress)

    @JvmStatic
    fun loadThreadedGetStatusWithProgress(path: String): ThreadLoadStatus {
        val (status, progress) = ObjectCalls.ptrcallWithStringAndArrayArgRetLongAndArray(
            loadThreadedGetStatusBind,
            singleton,
            path,
            listOf(0.0),
        )
        return ThreadLoadStatus(status, (progress.firstOrNull() as? Number)?.toDouble())
    }

    /**
     * Returns the resource loaded by `load_threaded_request`. If this is called before the loading
     * thread is done (i.e. `load_threaded_get_status` is not `THREAD_LOAD_LOADED`), the calling thread
     * will be blocked until the resource has finished loading. However, it's recommended to use
     * `load_threaded_get_status` to known when the load has actually completed.
     *
     * Generated from Godot docs: ResourceLoader.load_threaded_get
     */
    @JvmStatic
    fun loadThreadedGet(path: String): Resource? =
        Resource.wrap(ObjectCalls.ptrcallWithStringArgRetObject(loadThreadedGetBind, singleton, path))

    @JvmStatic
    fun loadThreadedGetPackedScene(path: String): PackedScene? =
        PackedScene.wrap(ObjectCalls.ptrcallWithStringArgRetObject(loadThreadedGetBind, singleton, path))

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
    fun load(path: String, typeHint: String = "", cacheMode: Long = CACHE_MODE_REUSE): Resource? =
        Resource.wrap(
            ObjectCalls.ptrcallWithTwoStringAndLongArgsRetObject(loadBind, singleton, path, typeHint, cacheMode),
        )

    @JvmStatic
    fun loadPackedScene(path: String, cacheMode: Long = CACHE_MODE_REUSE): PackedScene? =
        PackedScene.wrap(
            ObjectCalls.ptrcallWithTwoStringAndLongArgsRetObject(loadBind, singleton, path, "PackedScene", cacheMode),
        )

    @JvmStatic
    fun loadTexture2D(path: String, cacheMode: Long = CACHE_MODE_REUSE): Texture2D? =
        Texture2D.wrap(
            ObjectCalls.ptrcallWithTwoStringAndLongArgsRetObject(loadBind, singleton, path, "Texture2D", cacheMode),
        )

    @JvmStatic
    fun loadAudioStream(path: String, cacheMode: Long = CACHE_MODE_REUSE): AudioStream? =
        AudioStream.wrap(
            ObjectCalls.ptrcallWithTwoStringAndLongArgsRetObject(loadBind, singleton, path, "AudioStream", cacheMode),
        )

    @JvmStatic
    fun loadLightmapGIData(path: String, cacheMode: Long = CACHE_MODE_REUSE): LightmapGIData? =
        LightmapGIData.wrap(
            ObjectCalls.ptrcallWithTwoStringAndLongArgsRetObject(loadBind, singleton, path, "LightmapGIData", cacheMode),
        )

    /**
     * Returns the cached resource reference for the given `path`. Note: If the resource is not cached,
     * the returned `Resource` will be invalid.
     *
     * Generated from Godot docs: ResourceLoader.get_cached_ref
     */
    @JvmStatic
    fun getCachedRef(path: String): Resource? =
        Resource.wrap(ObjectCalls.ptrcallWithStringArgRetObject(getCachedRefBind, singleton, path))

    /**
     * Registers a new `ResourceFormatLoader`. The ResourceLoader will use the ResourceFormatLoader as
     * described in `load`. This method is performed implicitly for ResourceFormatLoaders written in
     * GDScript (see `ResourceFormatLoader` for more information).
     *
     * Generated from Godot docs: ResourceLoader.add_resource_format_loader
     */
    @JvmStatic
    fun addResourceFormatLoader(formatLoader: ResourceFormatLoader?, atFront: Boolean = false) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(
            addResourceFormatLoaderBind,
            singleton,
            formatLoader?.requireOpenHandle() ?: MemorySegment.NULL,
            atFront,
        )
    }

    /**
     * Unregisters the given `ResourceFormatLoader`.
     *
     * Generated from Godot docs: ResourceLoader.remove_resource_format_loader
     */
    @JvmStatic
    fun removeResourceFormatLoader(formatLoader: ResourceFormatLoader?) {
        ObjectCalls.ptrcallWithObjectArgs(
            removeResourceFormatLoaderBind,
            singleton,
            listOf(formatLoader?.requireOpenHandle() ?: MemorySegment.NULL),
        )
    }

    /**
     * Changes the behavior on missing sub-resources. The default behavior is to abort loading.
     *
     * Generated from Godot docs: ResourceLoader.set_abort_on_missing_resources
     */
    @JvmStatic
    fun setAbortOnMissingResources(abort: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAbortOnMissingResourcesBind, singleton, abort)
    }

}

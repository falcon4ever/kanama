package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import java.lang.foreign.MemorySegment

/**
 * Base class for serializable objects.
 *
 * Generated from Godot docs: Resource
 */
open class Resource internal constructor(
    handle: MemorySegment,
) : RefCounted(handle) {

    /**
     * The unique path to this resource. If it has been saved to disk, the value will be its filepath.
     * If the resource is exclusively contained within a scene, the value will be the `PackedScene`'s
     * filepath, followed by a unique identifier. Note: Setting this property manually may fail if a
     * resource with the same path has already been previously loaded. If necessary, use
     * `take_over_path`.
     *
     * Generated from Godot docs: Resource.set_path
     */
    fun setPath(path: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setPathBind, handle, path)
    }

    /**
     * Sets the `resource_path` to `path`, potentially overriding an existing cache entry for this
     * path. Further attempts to load an overridden resource by path will instead return this resource.
     *
     * Generated from Godot docs: Resource.take_over_path
     */
    fun takeOverPath(path: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(takeOverPathBind, handle, path)
    }

    /**
     * The unique path to this resource. If it has been saved to disk, the value will be its filepath.
     * If the resource is exclusively contained within a scene, the value will be the `PackedScene`'s
     * filepath, followed by a unique identifier. Note: Setting this property manually may fail if a
     * resource with the same path has already been previously loaded. If necessary, use
     * `take_over_path`.
     *
     * Generated from Godot docs: Resource.get_path
     */
    fun getPath(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getPathBind, handle)
    }

    /**
     * Sets the resource's path to `path` without involving the resource cache. Useful for handling
     * `ResourceFormatLoader.CacheMode` values when implementing a custom resource format by extending
     * `ResourceFormatLoader` and `ResourceFormatSaver`.
     *
     * Generated from Godot docs: Resource.set_path_cache
     */
    fun setPathCache(path: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setPathCacheBind, handle, path)
    }

    /**
     * An optional name for this resource. When defined, its value is displayed to represent the
     * resource in the Inspector dock. For built-in scripts, the name is displayed as part of the tab
     * name in the script editor. Note: Some resource formats do not support resource names. You can
     * still set the name in the editor or via code, but it will be lost when the resource is reloaded.
     * For example, only built-in scripts can have a resource name, while scripts stored in separate
     * files cannot.
     *
     * Generated from Godot docs: Resource.set_name
     */
    fun setName(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setNameBind, handle, name)
    }

    /**
     * An optional name for this resource. When defined, its value is displayed to represent the
     * resource in the Inspector dock. For built-in scripts, the name is displayed as part of the tab
     * name in the script editor. Note: Some resource formats do not support resource names. You can
     * still set the name in the editor or via code, but it will be lost when the resource is reloaded.
     * For example, only built-in scripts can have a resource name, while scripts stored in separate
     * files cannot.
     *
     * Generated from Godot docs: Resource.get_name
     */
    fun getName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getNameBind, handle)
    }

    /**
     * Returns the `RID` of this resource (or an empty RID). Many resources (such as `Texture2D`,
     * `Mesh`, and so on) are high-level abstractions of resources stored in a specialized server
     * (`DisplayServer`, `RenderingServer`, etc.), so this function will return the original `RID`.
     *
     * Generated from Godot docs: Resource.get_rid
     */
    fun getRid(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(getRidBind, handle)
    }

    /**
     * If `true`, the resource is duplicated for each instance of all scenes using it. At run-time, the
     * resource can be modified in one scene without affecting other instances (see
     * `PackedScene.instantiate`). Note: Changing this property at run-time has no effect on already
     * created duplicate resources.
     *
     * Generated from Godot docs: Resource.set_local_to_scene
     */
    fun setLocalToScene(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setLocalToSceneBind, handle, enable)
    }

    /**
     * A unique identifier relative to this resource's scene. If left empty, the ID is automatically
     * generated when this resource is saved inside a `PackedScene`. If the resource is not inside a
     * scene, this property is empty by default. Note: When the `PackedScene` is saved, if multiple
     * resources in the same scene use the same ID, only the earliest resource in the scene hierarchy
     * keeps the original ID. The other resources are assigned new IDs from `generate_scene_unique_id`.
     * Note: Setting this property does not emit the `changed` signal. Warning: When setting, the ID
     * must only consist of letters, numbers, and underscores. Otherwise, it will fail and default to a
     * randomly generated ID.
     *
     * Generated from Godot docs: Resource.get_scene_unique_id
     */
    fun getSceneUniqueId(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getSceneUniqueIdBind, handle)
    }

    /**
     * A unique identifier relative to this resource's scene. If left empty, the ID is automatically
     * generated when this resource is saved inside a `PackedScene`. If the resource is not inside a
     * scene, this property is empty by default. Note: When the `PackedScene` is saved, if multiple
     * resources in the same scene use the same ID, only the earliest resource in the scene hierarchy
     * keeps the original ID. The other resources are assigned new IDs from `generate_scene_unique_id`.
     * Note: Setting this property does not emit the `changed` signal. Warning: When setting, the ID
     * must only consist of letters, numbers, and underscores. Otherwise, it will fail and default to a
     * randomly generated ID.
     *
     * Generated from Godot docs: Resource.set_scene_unique_id
     */
    fun setSceneUniqueId(id: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setSceneUniqueIdBind, handle, id)
    }

    /**
     * In the internal cache for scene-unique IDs, sets the ID of this resource to `id` for the scene
     * at `path`. If `id` is empty, the cache entry for `path` is cleared. Useful to keep scene-unique
     * IDs the same when implementing a VCS-friendly custom resource format by extending
     * `ResourceFormatLoader` and `ResourceFormatSaver`. Note: This method is only implemented when
     * running in an editor context.
     *
     * Generated from Godot docs: Resource.set_id_for_path
     */
    fun setIdForPath(path: String, id: String) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoStringArgs(setIdForPathBind, handle, path, id)
    }

    /**
     * From the internal cache for scene-unique IDs, returns the ID of this resource for the scene at
     * `path`. If there is no entry, an empty string is returned. Useful to keep scene-unique IDs the
     * same when implementing a VCS-friendly custom resource format by extending `ResourceFormatLoader`
     * and `ResourceFormatSaver`. Note: This method is only implemented when running in an editor
     * context. At runtime, it returns an empty string.
     *
     * Generated from Godot docs: Resource.get_id_for_path
     */
    fun getIdForPath(path: String): String {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetString(getIdForPathBind, handle, path)
    }

    /**
     * Returns `true` if the resource is saved on disk as a part of another resource's file.
     *
     * Generated from Godot docs: Resource.is_built_in
     */
    fun isBuiltIn(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isBuiltInBind, handle)
    }

    /**
     * If `true`, the resource is duplicated for each instance of all scenes using it. At run-time, the
     * resource can be modified in one scene without affecting other instances (see
     * `PackedScene.instantiate`). Note: Changing this property at run-time has no effect on already
     * created duplicate resources.
     *
     * Generated from Godot docs: Resource.is_local_to_scene
     */
    fun isLocalToScene(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isLocalToSceneBind, handle)
    }

    /**
     * If `resource_local_to_scene` is set to `true` and the resource has been loaded from a
     * `PackedScene` instantiation, returns the root `Node` of the scene where this resource is used.
     * Otherwise, returns `null`.
     *
     * Generated from Godot docs: Resource.get_local_scene
     */
    fun getLocalScene(): Node? {
        checkOpen()
        return Node.wrap(ObjectCalls.ptrcallNoArgsRetObject(getLocalSceneBind, handle))
    }

    /**
     * Calls `_setup_local_to_scene`. If `resource_local_to_scene` is set to `true`, this method is
     * automatically called from `PackedScene.instantiate` by the newly duplicated resource within the
     * scene instance.
     *
     * Generated from Godot docs: Resource.setup_local_to_scene
     */
    fun setupLocalToScene() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(setupLocalToSceneBind, handle)
    }

    /**
     * Makes the resource clear its non-exported properties. See also `_reset_state`. Useful when
     * implementing a custom resource format by extending `ResourceFormatLoader` and
     * `ResourceFormatSaver`.
     *
     * Generated from Godot docs: Resource.reset_state
     */
    fun resetState() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(resetStateBind, handle)
    }

    /**
     * Emits the `changed` signal. This method is called automatically for some built-in resources.
     * Note: For custom resources, it's recommended to call this method whenever a meaningful change
     * occurs, such as a modified property. This ensures that custom `Object`s depending on the
     * resource are properly updated.
     *
     * Generated from Godot docs: Resource.emit_changed
     */
    fun emitChanged() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(emitChangedBind, handle)
    }

    /**
     * Duplicates this resource, returning a new resource with its `export`ed or
     * `PROPERTY_USAGE_STORAGE` properties copied from the original. If `deep` is `false`, a shallow
     * copy is returned: nested `Array`, `Dictionary`, and `Resource` properties are not duplicated and
     * are shared with the original resource. If `deep` is `true`, a deep copy is returned: all nested
     * arrays, dictionaries, and packed arrays are also duplicated (recursively). Any `Resource` found
     * inside will only be duplicated if it's local, like `DEEP_DUPLICATE_INTERNAL` used with
     * `duplicate_deep`. The following exceptions apply: - Subresource properties with the
     * `PROPERTY_USAGE_ALWAYS_DUPLICATE` flag are always duplicated (recursively or not, depending on
     * `deep`). - Subresource properties with the `PROPERTY_USAGE_NEVER_DUPLICATE` flag are never
     * duplicated. Note: For custom resources, this method will fail if `Object._init` has been defined
     * with required parameters. Note: When duplicating with `deep` set to `true`, each resource found,
     * including the one on which this method is called, will be only duplicated once and referenced as
     * many times as needed in the duplicate. For instance, if you are duplicating resource A that
     * happens to have resource B referenced twice, you'll get a new resource A' referencing a new
     * resource B' twice.
     *
     * Generated from Godot docs: Resource.duplicate
     */
    fun duplicate(subresources: Boolean = false): Resource? {
        checkOpen()
        return wrap(ObjectCalls.ptrcallWithBoolArgRetObject(duplicateBind, handle, subresources))
    }

    /**
     * Duplicates this resource, deeply, like `duplicate` when passing `true`, with extra control over
     * how subresources are handled.
     *
     * Generated from Godot docs: Resource.duplicate_deep
     */
    fun duplicateDeep(mode: Long = DEEP_DUPLICATE_INTERNAL): Resource? {
        checkOpen()
        return wrap(ObjectCalls.ptrcallWithLongArgRetObject(duplicateDeepBind, handle, mode))
    }

    /**
     * Returns a non-owning Object wrapper for this resource.
     *
     * Retained for source compatibility: `Resource` now inherits `GodotObject`'s surface directly
     * (`setMeta`/`connect`/`callDeferred`/…), so this is largely a convenience alias for callers
     * that already hold one (e.g. `docs/contributing/demo-porting-rules.md`).
     */
    fun asObject(): GodotObject {
        checkOpen()
        return GodotObject(handle)
    }

    object Signals {
        const val changed: String = "changed"
        const val setupLocalToSceneRequested: String = "setup_local_to_scene_requested"
    }

    companion object {
        const val DEEP_DUPLICATE_NONE = 0L
        const val DEEP_DUPLICATE_INTERNAL = 1L
        const val DEEP_DUPLICATE_ALL = 2L

        /**
         * Generates a unique identifier for a resource to be contained inside a `PackedScene`, based on
         * the current date, time, and a random value. The returned string is only composed of letters (`a`
         * to `y`) and numbers (`0` to `8`). See also `resource_scene_unique_id`.
         *
         * Generated from Godot docs: Resource.generate_scene_unique_id
         */
        @JvmStatic
        fun generateSceneUniqueId(): String =
            ObjectCalls.ptrcallNoArgsRetString(generateSceneUniqueIdBind, MemorySegment.NULL)

        private const val GET_PATH_HASH = 201670096L
        private const val GET_STRING_HASH = 201670096L
        private const val SET_STRING_HASH = 83702148L
        private const val GENERATE_SCENE_UNIQUE_ID_HASH = 2841200299L
        private const val STRING_STRING_HASH = 3135753539L
        private const val TWO_STRING_HASH = 3186203200L
        private const val NOARGS_BOOL_HASH = 36873697L
        private const val NOARGS_RID_HASH = 2944877500L
        private const val GET_LOCAL_SCENE_HASH = 3160264692L
        private const val SET_BOOL_HASH = 2586408642L
        private const val NOARGS_VOID_HASH = 3218959716L
        private const val DUPLICATE_HASH = 482882304L
        private const val DUPLICATE_DEEP_HASH = 905779109L

        private val getPathBind by lazy {
            ObjectCalls.getMethodBind("Resource", "get_path", GET_PATH_HASH)
        }

        private val setPathBind by lazy {
            ObjectCalls.getMethodBind("Resource", "set_path", SET_STRING_HASH)
        }

        private val takeOverPathBind by lazy {
            ObjectCalls.getMethodBind("Resource", "take_over_path", SET_STRING_HASH)
        }

        private val setPathCacheBind by lazy {
            ObjectCalls.getMethodBind("Resource", "set_path_cache", SET_STRING_HASH)
        }

        private val generateSceneUniqueIdBind by lazy {
            ObjectCalls.getMethodBind("Resource", "generate_scene_unique_id", GENERATE_SCENE_UNIQUE_ID_HASH)
        }

        private val setNameBind by lazy {
            ObjectCalls.getMethodBind("Resource", "set_name", SET_STRING_HASH)
        }

        private val getNameBind by lazy {
            ObjectCalls.getMethodBind("Resource", "get_name", GET_STRING_HASH)
        }

        private val getRidBind by lazy {
            ObjectCalls.getMethodBind("Resource", "get_rid", NOARGS_RID_HASH)
        }

        private val setLocalToSceneBind by lazy {
            ObjectCalls.getMethodBind("Resource", "set_local_to_scene", SET_BOOL_HASH)
        }

        private val getSceneUniqueIdBind by lazy {
            ObjectCalls.getMethodBind("Resource", "get_scene_unique_id", GET_STRING_HASH)
        }

        private val setSceneUniqueIdBind by lazy {
            ObjectCalls.getMethodBind("Resource", "set_scene_unique_id", SET_STRING_HASH)
        }

        private val getIdForPathBind by lazy {
            ObjectCalls.getMethodBind("Resource", "get_id_for_path", STRING_STRING_HASH)
        }

        private val setIdForPathBind by lazy {
            ObjectCalls.getMethodBind("Resource", "set_id_for_path", TWO_STRING_HASH)
        }

        private val isBuiltInBind by lazy {
            ObjectCalls.getMethodBind("Resource", "is_built_in", NOARGS_BOOL_HASH)
        }

        private val isLocalToSceneBind by lazy {
            ObjectCalls.getMethodBind("Resource", "is_local_to_scene", NOARGS_BOOL_HASH)
        }

        private val getLocalSceneBind by lazy {
            ObjectCalls.getMethodBind("Resource", "get_local_scene", GET_LOCAL_SCENE_HASH)
        }

        private val setupLocalToSceneBind by lazy {
            ObjectCalls.getMethodBind("Resource", "setup_local_to_scene", NOARGS_VOID_HASH)
        }

        private val resetStateBind by lazy {
            ObjectCalls.getMethodBind("Resource", "reset_state", NOARGS_VOID_HASH)
        }

        private val emitChangedBind by lazy {
            ObjectCalls.getMethodBind("Resource", "emit_changed", NOARGS_VOID_HASH)
        }

        private val duplicateBind by lazy {
            ObjectCalls.getMethodBind("Resource", "duplicate", DUPLICATE_HASH)
        }

        private val duplicateDeepBind by lazy {
            ObjectCalls.getMethodBind("Resource", "duplicate_deep", DUPLICATE_DEEP_HASH)
        }

        internal fun wrap(handle: MemorySegment): Resource? =
            if (handle.address() == 0L) null else Resource(handle)

        /**
         * Wraps an existing Godot `Resource` handle without taking ownership.
         *
         * This is primarily useful for custom resource scripts that extend
         * `KanamaScript<Resource>` and need a public self factory.
         */
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Resource =
            Resource(handle)

        @JvmStatic
        fun fromObject(value: GodotObject): Resource? =
            if (value.isClass("Resource")) Resource(value.handle) else null

        @JvmStatic
        fun create(): Resource =
            Resource(ObjectCalls.constructObject("Resource"))
    }
}

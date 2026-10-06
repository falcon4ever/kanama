package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID

/**
 * Base class for serializable objects.
 *
 * Generated from Godot docs: Resource
 */
open class Resource(handle: GodotHandle) : RefCounted(handle) {
    var resourceLocalToScene: Boolean
        @JvmName("resourceLocalToSceneProperty")
        get() = isLocalToScene()
        @JvmName("setResourceLocalToSceneProperty")
        set(value) = setLocalToScene(value)

    var resourcePath: String
        @JvmName("resourcePathProperty")
        get() = getPath()
        @JvmName("setResourcePathProperty")
        set(value) = setPath(value)

    var resourceName: String
        @JvmName("resourceNameProperty")
        get() = getName()
        @JvmName("setResourceNameProperty")
        set(value) = setName(value)

    var resourceSceneUniqueId: String
        @JvmName("resourceSceneUniqueIdProperty")
        get() = getSceneUniqueId()
        @JvmName("setResourceSceneUniqueIdProperty")
        set(value) = setSceneUniqueId(value)

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
        ObjectCalls.ptrcallWithStringArg(Binds.setPathBind, segment, path)
    }

    /**
     * Sets the `resource_path` to `path`, potentially overriding an existing cache entry for this
     * path. Further attempts to load an overridden resource by path will instead return this resource.
     *
     * Generated from Godot docs: Resource.take_over_path
     */
    fun takeOverPath(path: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.takeOverPathBind, segment, path)
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
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getPathBind, segment)
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
        ObjectCalls.ptrcallWithStringArg(Binds.setPathCacheBind, segment, path)
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
        ObjectCalls.ptrcallWithStringArg(Binds.setNameBind, segment, name)
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
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getNameBind, segment)
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
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getRidBind, segment)
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
        ObjectCalls.ptrcallWithBoolArg(Binds.setLocalToSceneBind, segment, enable)
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
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isLocalToSceneBind, segment)
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
        return Node.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getLocalSceneBind, segment))
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
        ObjectCalls.ptrcallNoArgs(Binds.setupLocalToSceneBind, segment)
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
        ObjectCalls.ptrcallNoArgs(Binds.resetStateBind, segment)
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
        ObjectCalls.ptrcallWithTwoStringArgs(Binds.setIdForPathBind, segment, path, id)
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
        return ObjectCalls.ptrcallWithStringArgRetString(Binds.getIdForPathBind, segment, path)
    }

    /**
     * Returns `true` if the resource is saved on disk as a part of another resource's file.
     *
     * Generated from Godot docs: Resource.is_built_in
     */
    fun isBuiltIn(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isBuiltInBind, segment)
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
        ObjectCalls.ptrcallWithStringArg(Binds.setSceneUniqueIdBind, segment, id)
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
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getSceneUniqueIdBind, segment)
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
        ObjectCalls.ptrcallNoArgs(Binds.emitChangedBind, segment)
    }

    /**
     * Duplicates this resource, returning a new resource with its `export`ed or
     * `PropertyUsageFlags.STORAGE` properties copied from the original. If `deep` is `false`, a
     * shallow copy is returned: nested `Array`, `Dictionary`, and `Resource` properties are not
     * duplicated and are shared with the original resource. If `deep` is `true`, a deep copy is
     * returned: all nested arrays, dictionaries, and packed arrays are also duplicated (recursively).
     * Any `Resource` found inside will only be duplicated if it's local, like
     * `DeepDuplicateMode.INTERNAL` used with `duplicate_deep`. The following exceptions apply: -
     * Subresource properties with the `PropertyUsageFlags.ALWAYS_DUPLICATE` flag are always duplicated
     * (recursively or not, depending on `deep`). - Subresource properties with the
     * `PropertyUsageFlags.NEVER_DUPLICATE` flag are never duplicated. Note: For custom resources, this
     * method will fail if `Object._init` has been defined with required parameters. Note: When
     * duplicating with `deep` set to `true`, each resource found, including the one on which this
     * method is called, will be only duplicated once and referenced as many times as needed in the
     * duplicate. For instance, if you are duplicating resource A that happens to have resource B
     * referenced twice, you'll get a new resource A' referencing a new resource B' twice.
     *
     * Generated from Godot docs: Resource.duplicate
     */
    fun duplicate(deep: Boolean = false): Resource? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithBoolArgRetObject(Binds.duplicateBind, segment, deep)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return Resource.wrapOwned(ret)
    }

    /**
     * Duplicates this resource, deeply, like `duplicate` when passing `true`, with extra control over
     * how subresources are handled.
     *
     * Generated from Godot docs: Resource.duplicate_deep
     */
    fun duplicateDeep(deepSubresourcesMode: Resource.DeepDuplicateMode = Resource.DeepDuplicateMode.INTERNAL): Resource? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetObject(Binds.duplicateDeepBind, segment, deepSubresourcesMode.value)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return Resource.wrapOwned(ret)
    }

    /**
     * Copies the data from `resource` into this resource. Both resources must share the same class.
     *
     * Generated from Godot docs: Resource.copy_from_resource
     */
    fun copyFromResource(resource: Resource?): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithObjectArgRetLong(Binds.copyFromResourceBind, segment, resource?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /** Signal `changed()`; see [TypedSignal]. */
    val changed: Signal0
        @JvmName("changedTypedSignal")
        get() = Signal0(this, "changed")

    /** Signal `setup_local_to_scene_requested()`; see [TypedSignal]. */
    val setupLocalToSceneRequested: Signal0
        @JvmName("setupLocalToSceneRequestedTypedSignal")
        get() = Signal0(this, "setup_local_to_scene_requested")

    object Signals {
        const val changed: String = "changed"
        const val setupLocalToSceneRequested: String = "setup_local_to_scene_requested"
    }

    /**
     * Godot's `Resource.DeepDuplicateMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`Resource.DeepDuplicateMode.<NAME>`).
     *
     * Generated from Godot docs: Resource.DeepDuplicateMode
     */
    @JvmInline
    value class DeepDuplicateMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * No subresources at all are duplicated. This is useful even in a deep duplication to have all the
             * arrays and dictionaries duplicated but still pointing to the original resources.
             *
             * Generated from Godot docs: Resource.DEEP_DUPLICATE_NONE
             */
            val NONE: DeepDuplicateMode get() = DeepDuplicateMode(0L)
            /**
             * Only subresources without a path or with a scene-local path will be duplicated.
             *
             * Generated from Godot docs: Resource.DEEP_DUPLICATE_INTERNAL
             */
            val INTERNAL: DeepDuplicateMode get() = DeepDuplicateMode(1L)
            /**
             * Every subresource found will be duplicated, even if it has a non-local path. In other words,
             * even potentially big resources stored separately will be duplicated.
             *
             * Generated from Godot docs: Resource.DEEP_DUPLICATE_ALL
             */
            val ALL: DeepDuplicateMode get() = DeepDuplicateMode(2L)
        }
    }

    companion object {
        /**
         * Generates a unique identifier for a resource to be contained inside a `PackedScene`, based on
         * the current date, time, and a random value. The returned string is only composed of letters (`a`
         * to `y`) and numbers (`0` to `8`). See also `resource_scene_unique_id`.
         *
         * Generated from Godot docs: Resource.generate_scene_unique_id
         */
        fun generateSceneUniqueId(): String {
            return ObjectCalls.ptrcallNoArgsRetString(Binds.generateSceneUniqueIdBind, NULL_SEGMENT)
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): Resource =
            Resource(handle)

        internal fun wrapOwned(handle: RawSegment): Resource? =
            if (handle.address() == 0L) null else RefCounted.owned(Resource(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Resource? =
            if (handle.address() == 0L) null else Resource(GodotHandle(handle))

        // Instantiate a Resource.
        @JvmStatic
        fun create(): Resource =
            RefCounted.owned(Resource(GodotHandle(ObjectCalls.constructObject("Resource"))))

        // Downcast a GodotObject to Resource (null if not).
        @JvmStatic
        fun fromObject(value: GodotObject): Resource? =
            if (value.isClass("Resource")) RefCounted.retained(Resource(value.handle)) else null
    }

    private object Binds {
        private const val SET_PATH_HASH = 83702148L
        @JvmField
        val setPathBind =
            ObjectCalls.getMethodBind("Resource", "set_path", SET_PATH_HASH)

        private const val TAKE_OVER_PATH_HASH = 83702148L
        @JvmField
        val takeOverPathBind =
            ObjectCalls.getMethodBind("Resource", "take_over_path", TAKE_OVER_PATH_HASH)

        private const val GET_PATH_HASH = 201670096L
        @JvmField
        val getPathBind =
            ObjectCalls.getMethodBind("Resource", "get_path", GET_PATH_HASH)

        private const val SET_PATH_CACHE_HASH = 83702148L
        @JvmField
        val setPathCacheBind =
            ObjectCalls.getMethodBind("Resource", "set_path_cache", SET_PATH_CACHE_HASH)

        private const val SET_NAME_HASH = 83702148L
        @JvmField
        val setNameBind =
            ObjectCalls.getMethodBind("Resource", "set_name", SET_NAME_HASH)

        private const val GET_NAME_HASH = 201670096L
        @JvmField
        val getNameBind =
            ObjectCalls.getMethodBind("Resource", "get_name", GET_NAME_HASH)

        private const val GET_RID_HASH = 2944877500L
        @JvmField
        val getRidBind =
            ObjectCalls.getMethodBind("Resource", "get_rid", GET_RID_HASH)

        private const val SET_LOCAL_TO_SCENE_HASH = 2586408642L
        @JvmField
        val setLocalToSceneBind =
            ObjectCalls.getMethodBind("Resource", "set_local_to_scene", SET_LOCAL_TO_SCENE_HASH)

        private const val IS_LOCAL_TO_SCENE_HASH = 36873697L
        @JvmField
        val isLocalToSceneBind =
            ObjectCalls.getMethodBind("Resource", "is_local_to_scene", IS_LOCAL_TO_SCENE_HASH)

        private const val GET_LOCAL_SCENE_HASH = 3160264692L
        @JvmField
        val getLocalSceneBind =
            ObjectCalls.getMethodBind("Resource", "get_local_scene", GET_LOCAL_SCENE_HASH)

        private const val SETUP_LOCAL_TO_SCENE_HASH = 3218959716L
        @JvmField
        val setupLocalToSceneBind =
            ObjectCalls.getMethodBind("Resource", "setup_local_to_scene", SETUP_LOCAL_TO_SCENE_HASH)

        private const val RESET_STATE_HASH = 3218959716L
        @JvmField
        val resetStateBind =
            ObjectCalls.getMethodBind("Resource", "reset_state", RESET_STATE_HASH)

        private const val SET_ID_FOR_PATH_HASH = 3186203200L
        @JvmField
        val setIdForPathBind =
            ObjectCalls.getMethodBind("Resource", "set_id_for_path", SET_ID_FOR_PATH_HASH)

        private const val GET_ID_FOR_PATH_HASH = 3135753539L
        @JvmField
        val getIdForPathBind =
            ObjectCalls.getMethodBind("Resource", "get_id_for_path", GET_ID_FOR_PATH_HASH)

        private const val IS_BUILT_IN_HASH = 36873697L
        @JvmField
        val isBuiltInBind =
            ObjectCalls.getMethodBind("Resource", "is_built_in", IS_BUILT_IN_HASH)

        private const val GENERATE_SCENE_UNIQUE_ID_HASH = 2841200299L
        @JvmField
        val generateSceneUniqueIdBind =
            ObjectCalls.getMethodBind("Resource", "generate_scene_unique_id", GENERATE_SCENE_UNIQUE_ID_HASH)

        private const val SET_SCENE_UNIQUE_ID_HASH = 83702148L
        @JvmField
        val setSceneUniqueIdBind =
            ObjectCalls.getMethodBind("Resource", "set_scene_unique_id", SET_SCENE_UNIQUE_ID_HASH)

        private const val GET_SCENE_UNIQUE_ID_HASH = 201670096L
        @JvmField
        val getSceneUniqueIdBind =
            ObjectCalls.getMethodBind("Resource", "get_scene_unique_id", GET_SCENE_UNIQUE_ID_HASH)

        private const val EMIT_CHANGED_HASH = 3218959716L
        @JvmField
        val emitChangedBind =
            ObjectCalls.getMethodBind("Resource", "emit_changed", EMIT_CHANGED_HASH)

        private const val DUPLICATE_HASH = 482882304L
        @JvmField
        val duplicateBind =
            ObjectCalls.getMethodBind("Resource", "duplicate", DUPLICATE_HASH)

        private const val DUPLICATE_DEEP_HASH = 905779109L
        @JvmField
        val duplicateDeepBind =
            ObjectCalls.getMethodBind("Resource", "duplicate_deep", DUPLICATE_DEEP_HASH)

        private const val COPY_FROM_RESOURCE_HASH = 3338311164L
        @JvmField
        val copyFromResourceBind =
            ObjectCalls.getMethodBind("Resource", "copy_from_resource", COPY_FROM_RESOURCE_HASH)
    }
}

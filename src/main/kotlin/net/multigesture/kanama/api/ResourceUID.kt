package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A singleton that manages the unique identifiers of all resources within a project.
 *
 * Generated from Godot docs: ResourceUID
 */
object ResourceUID {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("ResourceUID")
    }

    const val INVALID_ID: Long = -1L

    /**
     * Converts the given UID to a `uid://` string value.
     *
     * Generated from Godot docs: ResourceUID.id_to_text
     */
    @JvmStatic
    fun idToText(id: Long): String {
        return ObjectCalls.ptrcallWithLongArgRetString(idToTextBind, singleton, id)
    }

    /**
     * Extracts the UID value from the given `uid://` string.
     *
     * Generated from Godot docs: ResourceUID.text_to_id
     */
    @JvmStatic
    fun textToId(textId: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(textToIdBind, singleton, textId)
    }

    /**
     * Generates a random resource UID which is guaranteed to be unique within the list of currently
     * loaded UIDs. In order for this UID to be registered, you must call `add_id` or `set_id`.
     *
     * Generated from Godot docs: ResourceUID.create_id
     */
    @JvmStatic
    fun createId(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(createIdBind, singleton)
    }

    /**
     * Like `create_id`, but the UID is seeded with the provided `path` and project name. UIDs
     * generated for that path will be always the same within the current project.
     *
     * Generated from Godot docs: ResourceUID.create_id_for_path
     */
    @JvmStatic
    fun createIdForPath(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(createIdForPathBind, singleton, path)
    }

    /**
     * Returns whether the given UID value is known to the cache.
     *
     * Generated from Godot docs: ResourceUID.has_id
     */
    @JvmStatic
    fun hasId(id: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(hasIdBind, singleton, id)
    }

    /**
     * Adds a new UID value which is mapped to the given resource path. Fails with an error if the UID
     * already exists, so be sure to check `has_id` beforehand, or use `set_id` instead.
     *
     * Generated from Godot docs: ResourceUID.add_id
     */
    @JvmStatic
    fun addId(id: Long, path: String) {
        ObjectCalls.ptrcallWithLongAndStringArg(addIdBind, singleton, id, path)
    }

    /**
     * Updates the resource path of an existing UID. Fails with an error if the UID does not exist, so
     * be sure to check `has_id` beforehand, or use `add_id` instead.
     *
     * Generated from Godot docs: ResourceUID.set_id
     */
    @JvmStatic
    fun setId(id: Long, path: String) {
        ObjectCalls.ptrcallWithLongAndStringArg(setIdBind, singleton, id, path)
    }

    /**
     * Returns the path that the given UID value refers to. Fails with an error if the UID does not
     * exist, so be sure to check `has_id` beforehand.
     *
     * Generated from Godot docs: ResourceUID.get_id_path
     */
    @JvmStatic
    fun getIdPath(id: Long): String {
        return ObjectCalls.ptrcallWithLongArgRetString(getIdPathBind, singleton, id)
    }

    /**
     * Removes a loaded UID value from the cache. Fails with an error if the UID does not exist, so be
     * sure to check `has_id` beforehand.
     *
     * Generated from Godot docs: ResourceUID.remove_id
     */
    @JvmStatic
    fun removeId(id: Long) {
        ObjectCalls.ptrcallWithLongArg(removeIdBind, singleton, id)
    }

    /**
     * Converts the provided `uid` to a path. Prints an error if the UID is invalid.
     *
     * Generated from Godot docs: ResourceUID.uid_to_path
     */
    @JvmStatic
    fun uidToPath(uid: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(uidToPathBind, singleton, uid)
    }

    /**
     * Converts the provided resource `path` to a UID. Returns the unchanged path if it has no
     * associated UID.
     *
     * Generated from Godot docs: ResourceUID.path_to_uid
     */
    @JvmStatic
    fun pathToUid(path: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(pathToUidBind, singleton, path)
    }

    /**
     * Returns a path, converting `path_or_uid` if necessary. Fails and returns an empty string if an
     * invalid UID is provided.
     *
     * Generated from Godot docs: ResourceUID.ensure_path
     */
    @JvmStatic
    fun ensurePath(pathOrUid: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(ensurePathBind, singleton, pathOrUid)
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): ResourceUID? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): ResourceUID? =
        if (handle.address() == 0L) null else this

    private const val ID_TO_TEXT_HASH = 844755477L
    private val idToTextBind by lazy {
        ObjectCalls.getMethodBind("ResourceUID", "id_to_text", ID_TO_TEXT_HASH)
    }

    private const val TEXT_TO_ID_HASH = 1321353865L
    private val textToIdBind by lazy {
        ObjectCalls.getMethodBind("ResourceUID", "text_to_id", TEXT_TO_ID_HASH)
    }

    private const val CREATE_ID_HASH = 2455072627L
    private val createIdBind by lazy {
        ObjectCalls.getMethodBind("ResourceUID", "create_id", CREATE_ID_HASH)
    }

    private const val CREATE_ID_FOR_PATH_HASH = 1597066294L
    private val createIdForPathBind by lazy {
        ObjectCalls.getMethodBind("ResourceUID", "create_id_for_path", CREATE_ID_FOR_PATH_HASH)
    }

    private const val HAS_ID_HASH = 1116898809L
    private val hasIdBind by lazy {
        ObjectCalls.getMethodBind("ResourceUID", "has_id", HAS_ID_HASH)
    }

    private const val ADD_ID_HASH = 501894301L
    private val addIdBind by lazy {
        ObjectCalls.getMethodBind("ResourceUID", "add_id", ADD_ID_HASH)
    }

    private const val SET_ID_HASH = 501894301L
    private val setIdBind by lazy {
        ObjectCalls.getMethodBind("ResourceUID", "set_id", SET_ID_HASH)
    }

    private const val GET_ID_PATH_HASH = 844755477L
    private val getIdPathBind by lazy {
        ObjectCalls.getMethodBind("ResourceUID", "get_id_path", GET_ID_PATH_HASH)
    }

    private const val REMOVE_ID_HASH = 1286410249L
    private val removeIdBind by lazy {
        ObjectCalls.getMethodBind("ResourceUID", "remove_id", REMOVE_ID_HASH)
    }

    private const val UID_TO_PATH_HASH = 1703090593L
    private val uidToPathBind by lazy {
        ObjectCalls.getMethodBind("ResourceUID", "uid_to_path", UID_TO_PATH_HASH)
    }

    private const val PATH_TO_UID_HASH = 1703090593L
    private val pathToUidBind by lazy {
        ObjectCalls.getMethodBind("ResourceUID", "path_to_uid", PATH_TO_UID_HASH)
    }

    private const val ENSURE_PATH_HASH = 1703090593L
    private val ensurePathBind by lazy {
        ObjectCalls.getMethodBind("ResourceUID", "ensure_path", ENSURE_PATH_HASH)
    }
}

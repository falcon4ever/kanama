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

    @JvmStatic
    fun idToText(id: Long): String {
        return ObjectCalls.ptrcallWithLongArgRetString(idToTextBind, singleton, id)
    }

    @JvmStatic
    fun textToId(textId: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(textToIdBind, singleton, textId)
    }

    @JvmStatic
    fun createId(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(createIdBind, singleton)
    }

    @JvmStatic
    fun createIdForPath(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(createIdForPathBind, singleton, path)
    }

    @JvmStatic
    fun hasId(id: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(hasIdBind, singleton, id)
    }

    @JvmStatic
    fun addId(id: Long, path: String) {
        ObjectCalls.ptrcallWithLongAndStringArg(addIdBind, singleton, id, path)
    }

    @JvmStatic
    fun setId(id: Long, path: String) {
        ObjectCalls.ptrcallWithLongAndStringArg(setIdBind, singleton, id, path)
    }

    @JvmStatic
    fun getIdPath(id: Long): String {
        return ObjectCalls.ptrcallWithLongArgRetString(getIdPathBind, singleton, id)
    }

    @JvmStatic
    fun removeId(id: Long) {
        ObjectCalls.ptrcallWithLongArg(removeIdBind, singleton, id)
    }

    @JvmStatic
    fun uidToPath(uid: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(uidToPathBind, singleton, uid)
    }

    @JvmStatic
    fun pathToUid(path: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(pathToUidBind, singleton, path)
    }

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

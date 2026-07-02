package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A singleton for saving `Resource`s to the filesystem.
 *
 * Generated from Godot docs: ResourceSaver
 */
object ResourceSaver {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("ResourceSaver")
    }

    const val FLAG_NONE: Long = 0L
    const val FLAG_RELATIVE_PATHS: Long = 1L
    const val FLAG_BUNDLE_RESOURCES: Long = 2L
    const val FLAG_CHANGE_PATH: Long = 4L
    const val FLAG_OMIT_EDITOR_PROPERTIES: Long = 8L
    const val FLAG_SAVE_BIG_ENDIAN: Long = 16L
    const val FLAG_COMPRESS: Long = 32L
    const val FLAG_REPLACE_SUBRESOURCE_PATHS: Long = 64L

    @JvmStatic
    /**
     * Saves a resource to disk to the given path, using a `ResourceFormatSaver` that recognizes the
     * resource object. If `path` is empty, `ResourceSaver` will try to use `Resource.resource_path`.
     * The `flags` bitmask can be specified to customize the save behavior. Returns `OK` on success.
     * Note: When the project is running, any generated UID associated with the resource will not be
     * saved as the required code is only executed in editor mode.
     *
     * Generated from Godot docs: ResourceSaver.save
     */
    fun save(resource: Resource?, path: String = "", flags: Long = 0L): Long {
        return ObjectCalls.ptrcallWithObjectStringLongArgsRetLong(saveBind, singleton, resource?.requireOpenHandle() ?: MemorySegment.NULL, path, flags)
    }

    @JvmStatic
    fun setUid(resource: String, uid: Long): Long {
        return ObjectCalls.ptrcallWithStringAndLongArgRetLong(setUidBind, singleton, resource, uid)
    }

    @JvmStatic
    fun getRecognizedExtensions(type: Resource?): List<String> {
        return ObjectCalls.ptrcallWithObjectArgRetPackedStringList(getRecognizedExtensionsBind, singleton, type?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    @JvmStatic
    fun addResourceFormatSaver(formatSaver: ResourceFormatSaver?, atFront: Boolean = false) {
        ObjectCalls.ptrcallWithObjectAndBoolArg(addResourceFormatSaverBind, singleton, formatSaver?.requireOpenHandle() ?: MemorySegment.NULL, atFront)
    }

    @JvmStatic
    fun removeResourceFormatSaver(formatSaver: ResourceFormatSaver?) {
        ObjectCalls.ptrcallWithObjectArgs(removeResourceFormatSaverBind, singleton, listOf(formatSaver?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    @JvmStatic
    fun getResourceIdForPath(path: String, generate: Boolean = false): Long {
        return ObjectCalls.ptrcallWithStringAndBoolArgRetLong(getResourceIdForPathBind, singleton, path, generate)
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): ResourceSaver? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): ResourceSaver? =
        if (handle.address() == 0L) null else this

    private const val SAVE_HASH = 2983274697L
    private val saveBind by lazy {
        ObjectCalls.getMethodBind("ResourceSaver", "save", SAVE_HASH)
    }

    private const val SET_UID_HASH = 993915709L
    private val setUidBind by lazy {
        ObjectCalls.getMethodBind("ResourceSaver", "set_uid", SET_UID_HASH)
    }

    private const val GET_RECOGNIZED_EXTENSIONS_HASH = 4223597960L
    private val getRecognizedExtensionsBind by lazy {
        ObjectCalls.getMethodBind("ResourceSaver", "get_recognized_extensions", GET_RECOGNIZED_EXTENSIONS_HASH)
    }

    private const val ADD_RESOURCE_FORMAT_SAVER_HASH = 362894272L
    private val addResourceFormatSaverBind by lazy {
        ObjectCalls.getMethodBind("ResourceSaver", "add_resource_format_saver", ADD_RESOURCE_FORMAT_SAVER_HASH)
    }

    private const val REMOVE_RESOURCE_FORMAT_SAVER_HASH = 3373026878L
    private val removeResourceFormatSaverBind by lazy {
        ObjectCalls.getMethodBind("ResourceSaver", "remove_resource_format_saver", REMOVE_RESOURCE_FORMAT_SAVER_HASH)
    }

    private const val GET_RESOURCE_ID_FOR_PATH_HASH = 150756522L
    private val getResourceIdForPathBind by lazy {
        ObjectCalls.getMethodBind("ResourceSaver", "get_resource_id_for_path", GET_RESOURCE_ID_FOR_PATH_HASH)
    }
}

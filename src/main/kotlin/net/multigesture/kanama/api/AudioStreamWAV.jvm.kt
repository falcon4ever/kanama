package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for AudioStreamWAV (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP AudioStreamWAV waits on: ptrcallNoArgsRetByteArray, ptrcallNoArgsRetDictionary,
//   ptrcallWithByteArrayAndDictionaryArgRetObject, ptrcallWithByteArrayArg,
//   ptrcallWithDictionaryArg, ptrcallWithStringAndDictionaryArgRetObject
// Index: docs/contributing/ios-shape-gap.md

/**
 * Creates a new `AudioStreamWAV` instance from the given buffer. The buffer must contain WAV data.
 * The keys and values of `options` match the properties of `ResourceImporterWAV`. The usage of
 * `options` is identical to `AudioStreamWAV.load_from_file`.
 *
 * Generated from Godot docs: AudioStreamWAV.load_from_buffer
 */
fun AudioStreamWAV.Companion.loadFromBuffer(streamData: ByteArray, options: Map<String, Any?> = emptyMap()): AudioStreamWAV? {
    return AudioStreamWAV.wrap(ObjectCalls.ptrcallWithByteArrayAndDictionaryArgRetObject(loadFromBufferBind, MemorySegment.NULL, streamData, options))
}

/**
 * Creates a new `AudioStreamWAV` instance from the given file path. The file must be in WAV
 * format. The keys and values of `options` match the properties of `ResourceImporterWAV`.
 *
 * Generated from Godot docs: AudioStreamWAV.load_from_file
 */
fun AudioStreamWAV.Companion.loadFromFile(path: String, options: Map<String, Any?> = emptyMap()): AudioStreamWAV? {
    return AudioStreamWAV.wrap(ObjectCalls.ptrcallWithStringAndDictionaryArgRetObject(loadFromFileBind, MemorySegment.NULL, path, options))
}

/**
 * Contains the audio data in bytes. Note: If `format` is set to `FORMAT_8_BITS`, this property
 * expects signed 8-bit PCM data. To convert from unsigned 8-bit PCM, subtract 128 from each byte.
 * Note: If `format` is set to `FORMAT_QOA`, this property expects data from a full QOA file.
 *
 * Generated from Godot docs: AudioStreamWAV.set_data
 */
fun AudioStreamWAV.setData(data: ByteArray) {
    checkOpen()
    ObjectCalls.ptrcallWithByteArrayArg(setDataBind, handle, data)
}

/**
 * Contains the audio data in bytes. Note: If `format` is set to `FORMAT_8_BITS`, this property
 * expects signed 8-bit PCM data. To convert from unsigned 8-bit PCM, subtract 128 from each byte.
 * Note: If `format` is set to `FORMAT_QOA`, this property expects data from a full QOA file.
 *
 * Generated from Godot docs: AudioStreamWAV.get_data
 */
fun AudioStreamWAV.getData(): ByteArray {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetByteArray(getDataBind, handle)
}

/**
 * Contains user-defined tags if found in the WAV data. Commonly used tags include `title`,
 * `artist`, `album`, `tracknumber`, and `date` (`date` does not have a standard date format).
 * Note: No tag is guaranteed to be present in every file, so make sure to account for the keys not
 * always existing. Note: Only WAV files using a `LIST` chunk with an identifier of `INFO` to
 * encode the tags are currently supported.
 *
 * Generated from Godot docs: AudioStreamWAV.set_tags
 */
fun AudioStreamWAV.setTags(tags: Map<String, Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithDictionaryArg(setTagsBind, handle, tags)
}

/**
 * Contains user-defined tags if found in the WAV data. Commonly used tags include `title`,
 * `artist`, `album`, `tracknumber`, and `date` (`date` does not have a standard date format).
 * Note: No tag is guaranteed to be present in every file, so make sure to account for the keys not
 * always existing. Note: Only WAV files using a `LIST` chunk with an identifier of `INFO` to
 * encode the tags are currently supported.
 *
 * Generated from Godot docs: AudioStreamWAV.get_tags
 */
fun AudioStreamWAV.getTags(): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetDictionary(getTagsBind, handle)
}

var AudioStreamWAV.data: ByteArray
    @JvmName("dataProperty")
    get() = getData()
    @JvmName("setDataProperty")
    set(value) = setData(value)

var AudioStreamWAV.tags: Map<String, Any?>
    @JvmName("tagsProperty")
    get() = getTags()
    @JvmName("setTagsProperty")
    set(value) = setTags(value)

private const val LOAD_FROM_BUFFER_HASH = 4266838938L
private val loadFromBufferBind by lazy {
    ObjectCalls.getMethodBind("AudioStreamWAV", "load_from_buffer", LOAD_FROM_BUFFER_HASH)
}

private const val LOAD_FROM_FILE_HASH = 4015802384L
private val loadFromFileBind by lazy {
    ObjectCalls.getMethodBind("AudioStreamWAV", "load_from_file", LOAD_FROM_FILE_HASH)
}

private const val SET_DATA_HASH = 2971499966L
private val setDataBind by lazy {
    ObjectCalls.getMethodBind("AudioStreamWAV", "set_data", SET_DATA_HASH)
}

private const val GET_DATA_HASH = 2362200018L
private val getDataBind by lazy {
    ObjectCalls.getMethodBind("AudioStreamWAV", "get_data", GET_DATA_HASH)
}

private const val SET_TAGS_HASH = 4155329257L
private val setTagsBind by lazy {
    ObjectCalls.getMethodBind("AudioStreamWAV", "set_tags", SET_TAGS_HASH)
}

private const val GET_TAGS_HASH = 3102165223L
private val getTagsBind by lazy {
    ObjectCalls.getMethodBind("AudioStreamWAV", "get_tags", GET_TAGS_HASH)
}

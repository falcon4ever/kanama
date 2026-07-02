package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Provides a low-level interface for creating parsers for XML files.
 *
 * Generated from Godot docs: XMLParser
 */
class XMLParser(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Parses the next node in the file. This method returns an error code.
     *
     * Generated from Godot docs: XMLParser.read
     */
    fun read(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(readBind, handle)
    }

    fun getNodeType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getNodeTypeBind, handle)
    }

    fun getNodeName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getNodeNameBind, handle)
    }

    fun getNodeData(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getNodeDataBind, handle)
    }

    fun getNodeOffset(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getNodeOffsetBind, handle)
    }

    fun getAttributeCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getAttributeCountBind, handle)
    }

    fun getAttributeName(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getAttributeNameBind, handle, idx)
    }

    fun getAttributeValue(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getAttributeValueBind, handle, idx)
    }

    fun hasAttribute(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasAttributeBind, handle, name)
    }

    fun getNamedAttributeValue(name: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(getNamedAttributeValueBind, handle, name)
    }

    fun getNamedAttributeValueSafe(name: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(getNamedAttributeValueSafeBind, handle, name)
    }

    fun isEmpty(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEmptyBind, handle)
    }

    fun getCurrentLine(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCurrentLineBind, handle)
    }

    fun skipSection() {
        ObjectCalls.ptrcallNoArgs(skipSectionBind, handle)
    }

    /**
     * Moves the buffer cursor to a certain offset (since the beginning) and reads the next node there.
     * This method returns an error code.
     *
     * Generated from Godot docs: XMLParser.seek
     */
    fun seek(position: Long): Long {
        return ObjectCalls.ptrcallWithLongArgRetLong(seekBind, handle, position)
    }

    /**
     * Opens an XML `file` for parsing. This method returns an error code.
     *
     * Generated from Godot docs: XMLParser.open
     */
    fun open(file: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(openBind, handle, file)
    }

    fun openBuffer(buffer: ByteArray): Long {
        return ObjectCalls.ptrcallWithByteArrayArgRetLong(openBufferBind, handle, buffer)
    }

    companion object {
        const val NODE_NONE: Long = 0L
        const val NODE_ELEMENT: Long = 1L
        const val NODE_ELEMENT_END: Long = 2L
        const val NODE_TEXT: Long = 3L
        const val NODE_COMMENT: Long = 4L
        const val NODE_CDATA: Long = 5L
        const val NODE_UNKNOWN: Long = 6L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): XMLParser? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): XMLParser? =
            if (handle.address() == 0L) null else XMLParser(handle)

        private const val READ_HASH = 166280745L
        private val readBind by lazy {
            ObjectCalls.getMethodBind("XMLParser", "read", READ_HASH)
        }

        private const val GET_NODE_TYPE_HASH = 2984359541L
        private val getNodeTypeBind by lazy {
            ObjectCalls.getMethodBind("XMLParser", "get_node_type", GET_NODE_TYPE_HASH)
        }

        private const val GET_NODE_NAME_HASH = 201670096L
        private val getNodeNameBind by lazy {
            ObjectCalls.getMethodBind("XMLParser", "get_node_name", GET_NODE_NAME_HASH)
        }

        private const val GET_NODE_DATA_HASH = 201670096L
        private val getNodeDataBind by lazy {
            ObjectCalls.getMethodBind("XMLParser", "get_node_data", GET_NODE_DATA_HASH)
        }

        private const val GET_NODE_OFFSET_HASH = 3905245786L
        private val getNodeOffsetBind by lazy {
            ObjectCalls.getMethodBind("XMLParser", "get_node_offset", GET_NODE_OFFSET_HASH)
        }

        private const val GET_ATTRIBUTE_COUNT_HASH = 3905245786L
        private val getAttributeCountBind by lazy {
            ObjectCalls.getMethodBind("XMLParser", "get_attribute_count", GET_ATTRIBUTE_COUNT_HASH)
        }

        private const val GET_ATTRIBUTE_NAME_HASH = 844755477L
        private val getAttributeNameBind by lazy {
            ObjectCalls.getMethodBind("XMLParser", "get_attribute_name", GET_ATTRIBUTE_NAME_HASH)
        }

        private const val GET_ATTRIBUTE_VALUE_HASH = 844755477L
        private val getAttributeValueBind by lazy {
            ObjectCalls.getMethodBind("XMLParser", "get_attribute_value", GET_ATTRIBUTE_VALUE_HASH)
        }

        private const val HAS_ATTRIBUTE_HASH = 3927539163L
        private val hasAttributeBind by lazy {
            ObjectCalls.getMethodBind("XMLParser", "has_attribute", HAS_ATTRIBUTE_HASH)
        }

        private const val GET_NAMED_ATTRIBUTE_VALUE_HASH = 3135753539L
        private val getNamedAttributeValueBind by lazy {
            ObjectCalls.getMethodBind("XMLParser", "get_named_attribute_value", GET_NAMED_ATTRIBUTE_VALUE_HASH)
        }

        private const val GET_NAMED_ATTRIBUTE_VALUE_SAFE_HASH = 3135753539L
        private val getNamedAttributeValueSafeBind by lazy {
            ObjectCalls.getMethodBind("XMLParser", "get_named_attribute_value_safe", GET_NAMED_ATTRIBUTE_VALUE_SAFE_HASH)
        }

        private const val IS_EMPTY_HASH = 36873697L
        private val isEmptyBind by lazy {
            ObjectCalls.getMethodBind("XMLParser", "is_empty", IS_EMPTY_HASH)
        }

        private const val GET_CURRENT_LINE_HASH = 3905245786L
        private val getCurrentLineBind by lazy {
            ObjectCalls.getMethodBind("XMLParser", "get_current_line", GET_CURRENT_LINE_HASH)
        }

        private const val SKIP_SECTION_HASH = 3218959716L
        private val skipSectionBind by lazy {
            ObjectCalls.getMethodBind("XMLParser", "skip_section", SKIP_SECTION_HASH)
        }

        private const val SEEK_HASH = 844576869L
        private val seekBind by lazy {
            ObjectCalls.getMethodBind("XMLParser", "seek", SEEK_HASH)
        }

        private const val OPEN_HASH = 166001499L
        private val openBind by lazy {
            ObjectCalls.getMethodBind("XMLParser", "open", OPEN_HASH)
        }

        private const val OPEN_BUFFER_HASH = 680677267L
        private val openBufferBind by lazy {
            ObjectCalls.getMethodBind("XMLParser", "open_buffer", OPEN_BUFFER_HASH)
        }
    }
}

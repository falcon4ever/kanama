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

    /**
     * Returns the type of the current node. Compare with `NodeType` constants.
     *
     * Generated from Godot docs: XMLParser.get_node_type
     */
    fun getNodeType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getNodeTypeBind, handle)
    }

    /**
     * Returns the name of a node. This method will raise an error if the currently parsed node is a
     * text node. Note: The content of a `NODE_CDATA` node and the comment string of a `NODE_COMMENT`
     * node are also considered names.
     *
     * Generated from Godot docs: XMLParser.get_node_name
     */
    fun getNodeName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getNodeNameBind, handle)
    }

    /**
     * Returns the contents of a text node. This method will raise an error if the current parsed node
     * is of any other type.
     *
     * Generated from Godot docs: XMLParser.get_node_data
     */
    fun getNodeData(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getNodeDataBind, handle)
    }

    /**
     * Returns the byte offset of the currently parsed node since the beginning of the file or buffer.
     * This is usually equivalent to the number of characters before the read position.
     *
     * Generated from Godot docs: XMLParser.get_node_offset
     */
    fun getNodeOffset(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getNodeOffsetBind, handle)
    }

    /**
     * Returns the number of attributes in the currently parsed element. Note: If this method is used
     * while the currently parsed node is not `NODE_ELEMENT` or `NODE_ELEMENT_END`, this count will not
     * be updated and will still reflect the last element.
     *
     * Generated from Godot docs: XMLParser.get_attribute_count
     */
    fun getAttributeCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getAttributeCountBind, handle)
    }

    /**
     * Returns the name of an attribute of the currently parsed element, specified by the `idx` index.
     *
     * Generated from Godot docs: XMLParser.get_attribute_name
     */
    fun getAttributeName(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getAttributeNameBind, handle, idx)
    }

    /**
     * Returns the value of an attribute of the currently parsed element, specified by the `idx` index.
     *
     * Generated from Godot docs: XMLParser.get_attribute_value
     */
    fun getAttributeValue(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getAttributeValueBind, handle, idx)
    }

    /**
     * Returns `true` if the currently parsed element has an attribute with the `name`.
     *
     * Generated from Godot docs: XMLParser.has_attribute
     */
    fun hasAttribute(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasAttributeBind, handle, name)
    }

    /**
     * Returns the value of an attribute of the currently parsed element, specified by its `name`. This
     * method will raise an error if the element has no such attribute.
     *
     * Generated from Godot docs: XMLParser.get_named_attribute_value
     */
    fun getNamedAttributeValue(name: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(getNamedAttributeValueBind, handle, name)
    }

    /**
     * Returns the value of an attribute of the currently parsed element, specified by its `name`. This
     * method will return an empty string if the element has no such attribute.
     *
     * Generated from Godot docs: XMLParser.get_named_attribute_value_safe
     */
    fun getNamedAttributeValueSafe(name: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(getNamedAttributeValueSafeBind, handle, name)
    }

    /**
     * Returns `true` if the currently parsed element is empty, e.g. `<element />`.
     *
     * Generated from Godot docs: XMLParser.is_empty
     */
    fun isEmpty(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEmptyBind, handle)
    }

    /**
     * Returns the current line in the parsed file, counting from 0.
     *
     * Generated from Godot docs: XMLParser.get_current_line
     */
    fun getCurrentLine(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCurrentLineBind, handle)
    }

    /**
     * Skips the current section. If the currently parsed node contains more inner nodes, they will be
     * ignored and the cursor will go to the closing of the current element.
     *
     * Generated from Godot docs: XMLParser.skip_section
     */
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

    /**
     * Opens an XML raw `buffer` for parsing. This method returns an error code.
     *
     * Generated from Godot docs: XMLParser.open_buffer
     */
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

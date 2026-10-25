package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Provides a low-level interface for creating parsers for XML files.
 *
 * Generated from Godot docs: XMLParser
 */
class XMLParser(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Parses the next node in the file. This method returns an error code.
     *
     * Generated from Godot docs: XMLParser.read
     */
    fun read(): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallNoArgsRetLong(Binds.readBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the type of the current node. Compare with `NodeType` constants.
     *
     * Generated from Godot docs: XMLParser.get_node_type
     */
    fun getNodeType(): XMLParser.NodeType {
        checkOpen()
        val ret = XMLParser.NodeType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getNodeTypeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the name of a node. This method will raise an error if the currently parsed node is a
     * text node. Note: The content of a `NodeType.CDATA` node and the comment string of a
     * `NodeType.COMMENT` node are also considered names.
     *
     * Generated from Godot docs: XMLParser.get_node_name
     */
    fun getNodeName(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getNodeNameBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the contents of a text node. This method will raise an error if the current parsed node
     * is of any other type.
     *
     * Generated from Godot docs: XMLParser.get_node_data
     */
    fun getNodeData(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getNodeDataBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the byte offset of the currently parsed node since the beginning of the file or buffer.
     * This is usually equivalent to the number of characters before the read position.
     *
     * Generated from Godot docs: XMLParser.get_node_offset
     */
    fun getNodeOffset(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.getNodeOffsetBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the number of attributes in the currently parsed element. Note: If this method is used
     * while the currently parsed node is not `NodeType.ELEMENT` or `NodeType.ELEMENT_END`, this count
     * will not be updated and will still reflect the last element.
     *
     * Generated from Godot docs: XMLParser.get_attribute_count
     */
    fun getAttributeCount(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getAttributeCountBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the name of an attribute of the currently parsed element, specified by the `idx` index.
     *
     * Generated from Godot docs: XMLParser.get_attribute_name
     */
    fun getAttributeName(idx: Int): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetString(Binds.getAttributeNameBind, segment, idx)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the value of an attribute of the currently parsed element, specified by the `idx` index.
     *
     * Generated from Godot docs: XMLParser.get_attribute_value
     */
    fun getAttributeValue(idx: Int): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetString(Binds.getAttributeValueBind, segment, idx)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns `true` if the currently parsed element has an attribute with the `name`.
     *
     * Generated from Godot docs: XMLParser.has_attribute
     */
    fun hasAttribute(name: String): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringArgRetBool(Binds.hasAttributeBind, segment, name)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the value of an attribute of the currently parsed element, specified by its `name`. This
     * method will raise an error if the element has no such attribute.
     *
     * Generated from Godot docs: XMLParser.get_named_attribute_value
     */
    fun getNamedAttributeValue(name: String): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringArgRetString(Binds.getNamedAttributeValueBind, segment, name)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the value of an attribute of the currently parsed element, specified by its `name`. This
     * method will return an empty string if the element has no such attribute.
     *
     * Generated from Godot docs: XMLParser.get_named_attribute_value_safe
     */
    fun getNamedAttributeValueSafe(name: String): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringArgRetString(Binds.getNamedAttributeValueSafeBind, segment, name)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns `true` if the currently parsed element is empty, e.g. `<element />`.
     *
     * Generated from Godot docs: XMLParser.is_empty
     */
    fun isEmpty(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isEmptyBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the current line in the parsed file, counting from 0.
     *
     * Generated from Godot docs: XMLParser.get_current_line
     */
    fun getCurrentLine(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getCurrentLineBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Skips the current section. If the currently parsed node contains more inner nodes, they will be
     * ignored and the cursor will go to the closing of the current element.
     *
     * Generated from Godot docs: XMLParser.skip_section
     */
    fun skipSection() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.skipSectionBind, segment)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Moves the buffer cursor to a certain offset (since the beginning) and reads the next node there.
     * This method returns an error code.
     *
     * Generated from Godot docs: XMLParser.seek
     */
    fun seek(position: Long): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithLongArgRetLong(Binds.seekBind, segment, position))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Opens an XML `file` for parsing. This method returns an error code.
     *
     * Generated from Godot docs: XMLParser.open
     */
    fun open(file: String): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.openBind, segment, file))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Opens an XML raw `buffer` for parsing. This method returns an error code.
     *
     * Generated from Godot docs: XMLParser.open_buffer
     */
    fun openBuffer(buffer: ByteArray): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithByteArrayArgRetLong(Binds.openBufferBind, segment, buffer))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `XMLParser.NodeType` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`XMLParser.NodeType.<NAME>`).
     *
     * Generated from Godot docs: XMLParser.NodeType
     */
    @JvmInline
    value class NodeType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * There's no node (no file or buffer opened).
             *
             * Generated from Godot docs: XMLParser.NODE_NONE
             */
            val NONE: NodeType get() = NodeType(0L)
            /**
             * An element node type, also known as a tag, e.g. `<title>`.
             *
             * Generated from Godot docs: XMLParser.NODE_ELEMENT
             */
            val ELEMENT: NodeType get() = NodeType(1L)
            /**
             * An end of element node type, e.g. `</title>`.
             *
             * Generated from Godot docs: XMLParser.NODE_ELEMENT_END
             */
            val ELEMENT_END: NodeType get() = NodeType(2L)
            /**
             * A text node type, i.e. text that is not inside an element. This includes whitespace.
             *
             * Generated from Godot docs: XMLParser.NODE_TEXT
             */
            val TEXT: NodeType get() = NodeType(3L)
            /**
             * A comment node type, e.g. `<!--A comment-->`.
             *
             * Generated from Godot docs: XMLParser.NODE_COMMENT
             */
            val COMMENT: NodeType get() = NodeType(4L)
            /**
             * A node type for CDATA (Character Data) sections, e.g. `<![CDATA[CDATA section]]>`.
             *
             * Generated from Godot docs: XMLParser.NODE_CDATA
             */
            val CDATA: NodeType get() = NodeType(5L)
            /**
             * An unknown node type.
             *
             * Generated from Godot docs: XMLParser.NODE_UNKNOWN
             */
            val UNKNOWN: NodeType get() = NodeType(6L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): XMLParser? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): XMLParser? =
            if (handle.address() == 0L) null else RefCounted.owned(XMLParser(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): XMLParser? =
            if (handle.address() == 0L) null else XMLParser(GodotHandle(handle))

        /** A new XMLParser, GDScript's `XMLParser.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): XMLParser =
            RefCounted.owned(XMLParser(GodotHandle(ObjectCalls.constructObject("XMLParser"))))
    }

    private object Binds {
        private const val READ_HASH = 166280745L
        @JvmField
        val readBind =
            ObjectCalls.getMethodBind("XMLParser", "read", READ_HASH)

        private const val GET_NODE_TYPE_HASH = 2984359541L
        @JvmField
        val getNodeTypeBind =
            ObjectCalls.getMethodBind("XMLParser", "get_node_type", GET_NODE_TYPE_HASH)

        private const val GET_NODE_NAME_HASH = 201670096L
        @JvmField
        val getNodeNameBind =
            ObjectCalls.getMethodBind("XMLParser", "get_node_name", GET_NODE_NAME_HASH)

        private const val GET_NODE_DATA_HASH = 201670096L
        @JvmField
        val getNodeDataBind =
            ObjectCalls.getMethodBind("XMLParser", "get_node_data", GET_NODE_DATA_HASH)

        private const val GET_NODE_OFFSET_HASH = 3905245786L
        @JvmField
        val getNodeOffsetBind =
            ObjectCalls.getMethodBind("XMLParser", "get_node_offset", GET_NODE_OFFSET_HASH)

        private const val GET_ATTRIBUTE_COUNT_HASH = 3905245786L
        @JvmField
        val getAttributeCountBind =
            ObjectCalls.getMethodBind("XMLParser", "get_attribute_count", GET_ATTRIBUTE_COUNT_HASH)

        private const val GET_ATTRIBUTE_NAME_HASH = 844755477L
        @JvmField
        val getAttributeNameBind =
            ObjectCalls.getMethodBind("XMLParser", "get_attribute_name", GET_ATTRIBUTE_NAME_HASH)

        private const val GET_ATTRIBUTE_VALUE_HASH = 844755477L
        @JvmField
        val getAttributeValueBind =
            ObjectCalls.getMethodBind("XMLParser", "get_attribute_value", GET_ATTRIBUTE_VALUE_HASH)

        private const val HAS_ATTRIBUTE_HASH = 3927539163L
        @JvmField
        val hasAttributeBind =
            ObjectCalls.getMethodBind("XMLParser", "has_attribute", HAS_ATTRIBUTE_HASH)

        private const val GET_NAMED_ATTRIBUTE_VALUE_HASH = 3135753539L
        @JvmField
        val getNamedAttributeValueBind =
            ObjectCalls.getMethodBind("XMLParser", "get_named_attribute_value", GET_NAMED_ATTRIBUTE_VALUE_HASH)

        private const val GET_NAMED_ATTRIBUTE_VALUE_SAFE_HASH = 3135753539L
        @JvmField
        val getNamedAttributeValueSafeBind =
            ObjectCalls.getMethodBind("XMLParser", "get_named_attribute_value_safe", GET_NAMED_ATTRIBUTE_VALUE_SAFE_HASH)

        private const val IS_EMPTY_HASH = 36873697L
        @JvmField
        val isEmptyBind =
            ObjectCalls.getMethodBind("XMLParser", "is_empty", IS_EMPTY_HASH)

        private const val GET_CURRENT_LINE_HASH = 3905245786L
        @JvmField
        val getCurrentLineBind =
            ObjectCalls.getMethodBind("XMLParser", "get_current_line", GET_CURRENT_LINE_HASH)

        private const val SKIP_SECTION_HASH = 3218959716L
        @JvmField
        val skipSectionBind =
            ObjectCalls.getMethodBind("XMLParser", "skip_section", SKIP_SECTION_HASH)

        private const val SEEK_HASH = 844576869L
        @JvmField
        val seekBind =
            ObjectCalls.getMethodBind("XMLParser", "seek", SEEK_HASH)

        private const val OPEN_HASH = 166001499L
        @JvmField
        val openBind =
            ObjectCalls.getMethodBind("XMLParser", "open", OPEN_HASH)

        private const val OPEN_BUFFER_HASH = 680677267L
        @JvmField
        val openBufferBind =
            ObjectCalls.getMethodBind("XMLParser", "open_buffer", OPEN_BUFFER_HASH)
    }
}

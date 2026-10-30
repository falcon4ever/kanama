package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed
import net.multigesture.kanama.types.NodePath

/**
 * Class for importing and exporting glTF files in and out of Godot.
 *
 * Generated from Godot docs: GLTFDocument
 */
open class GLTFDocument(handle: GodotHandle) : Resource(handle) {
    var imageFormat: String
        @JvmName("imageFormatProperty")
        get() = getImageFormat()
        @JvmName("setImageFormatProperty")
        set(value) = setImageFormat(value)

    var lossyQuality: Double
        @JvmName("lossyQualityProperty")
        get() = getLossyQuality()
        @JvmName("setLossyQualityProperty")
        set(value) = setLossyQuality(value)

    var fallbackImageFormat: String
        @JvmName("fallbackImageFormatProperty")
        get() = getFallbackImageFormat()
        @JvmName("setFallbackImageFormatProperty")
        set(value) = setFallbackImageFormat(value)

    var fallbackImageQuality: Double
        @JvmName("fallbackImageQualityProperty")
        get() = getFallbackImageQuality()
        @JvmName("setFallbackImageQualityProperty")
        set(value) = setFallbackImageQuality(value)

    var rootNodeMode: GLTFDocument.RootNodeMode
        @JvmName("rootNodeModeProperty")
        get() = getRootNodeMode()
        @JvmName("setRootNodeModeProperty")
        set(value) = setRootNodeMode(value)

    var textureMapMode: GLTFDocument.TextureMapMode
        @JvmName("textureMapModeProperty")
        get() = getTextureMapMode()
        @JvmName("setTextureMapModeProperty")
        set(value) = setTextureMapMode(value)

    var visibilityMode: GLTFDocument.VisibilityMode
        @JvmName("visibilityModeProperty")
        get() = getVisibilityMode()
        @JvmName("setVisibilityModeProperty")
        set(value) = setVisibilityMode(value)

    /**
     * The user-friendly name of the export image format. This is used when exporting the glTF file,
     * including writing to a file and writing to a byte array. By default, Godot allows the following
     * options: "None", "PNG", "JPEG", "Lossless WebP", and "Lossy WebP". Support for more image
     * formats can be added in `GLTFDocumentExtension` classes. A single extension class can provide
     * multiple options for the specific format to use, or even an option that uses multiple formats at
     * once.
     *
     * Generated from Godot docs: GLTFDocument.set_image_format
     */
    fun setImageFormat(imageFormat: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setImageFormatBind, segment, imageFormat)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The user-friendly name of the export image format. This is used when exporting the glTF file,
     * including writing to a file and writing to a byte array. By default, Godot allows the following
     * options: "None", "PNG", "JPEG", "Lossless WebP", and "Lossy WebP". Support for more image
     * formats can be added in `GLTFDocumentExtension` classes. A single extension class can provide
     * multiple options for the specific format to use, or even an option that uses multiple formats at
     * once.
     *
     * Generated from Godot docs: GLTFDocument.get_image_format
     */
    fun getImageFormat(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getImageFormatBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If `image_format` is a lossy image format, this determines the lossy quality of the image. On a
     * range of `0.0` to `1.0`, where `0.0` is the lowest quality and `1.0` is the highest quality. A
     * lossy quality of `1.0` is not the same as lossless.
     *
     * Generated from Godot docs: GLTFDocument.set_lossy_quality
     */
    fun setLossyQuality(lossyQuality: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setLossyQualityBind, segment, lossyQuality)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If `image_format` is a lossy image format, this determines the lossy quality of the image. On a
     * range of `0.0` to `1.0`, where `0.0` is the lowest quality and `1.0` is the highest quality. A
     * lossy quality of `1.0` is not the same as lossless.
     *
     * Generated from Godot docs: GLTFDocument.get_lossy_quality
     */
    fun getLossyQuality(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLossyQualityBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The user-friendly name of the fallback image format. This is used when exporting the glTF file,
     * including writing to a file and writing to a byte array. This property may only be one of
     * "None", "PNG", or "JPEG", and is only used when the `image_format` is not one of "None", "PNG",
     * or "JPEG". If having multiple extension image formats is desired, that can be done using a
     * `GLTFDocumentExtension` class - this property only covers the use case of providing a base glTF
     * fallback image when using a custom image format.
     *
     * Generated from Godot docs: GLTFDocument.set_fallback_image_format
     */
    fun setFallbackImageFormat(fallbackImageFormat: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setFallbackImageFormatBind, segment, fallbackImageFormat)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The user-friendly name of the fallback image format. This is used when exporting the glTF file,
     * including writing to a file and writing to a byte array. This property may only be one of
     * "None", "PNG", or "JPEG", and is only used when the `image_format` is not one of "None", "PNG",
     * or "JPEG". If having multiple extension image formats is desired, that can be done using a
     * `GLTFDocumentExtension` class - this property only covers the use case of providing a base glTF
     * fallback image when using a custom image format.
     *
     * Generated from Godot docs: GLTFDocument.get_fallback_image_format
     */
    fun getFallbackImageFormat(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getFallbackImageFormatBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The quality of the fallback image, if any. For PNG files, this downscales the image on both
     * dimensions by this factor. For JPEG files, this is the lossy quality of the image. A low value
     * is recommended, since including multiple high quality images in a glTF file defeats the file
     * size gains of using a more efficient image format.
     *
     * Generated from Godot docs: GLTFDocument.set_fallback_image_quality
     */
    fun setFallbackImageQuality(fallbackImageQuality: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setFallbackImageQualityBind, segment, fallbackImageQuality)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The quality of the fallback image, if any. For PNG files, this downscales the image on both
     * dimensions by this factor. For JPEG files, this is the lossy quality of the image. A low value
     * is recommended, since including multiple high quality images in a glTF file defeats the file
     * size gains of using a more efficient image format.
     *
     * Generated from Godot docs: GLTFDocument.get_fallback_image_quality
     */
    fun getFallbackImageQuality(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFallbackImageQualityBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * How to process the root node during export. The default and recommended value is
     * `RootNodeMode.SINGLE_ROOT`. Note: Regardless of how the glTF file is exported, when importing,
     * the root node type and name can be overridden in the scene import settings tab.
     *
     * Generated from Godot docs: GLTFDocument.set_root_node_mode
     */
    fun setRootNodeMode(rootNodeMode: GLTFDocument.RootNodeMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setRootNodeModeBind, segment, rootNodeMode.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * How to process the root node during export. The default and recommended value is
     * `RootNodeMode.SINGLE_ROOT`. Note: Regardless of how the glTF file is exported, when importing,
     * the root node type and name can be overridden in the scene import settings tab.
     *
     * Generated from Godot docs: GLTFDocument.get_root_node_mode
     */
    fun getRootNodeMode(): GLTFDocument.RootNodeMode {
        checkOpen()
        val ret = GLTFDocument.RootNodeMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getRootNodeModeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * How to handle texture maps during import. The default and recommended value is
     * `TextureMapMode.REMAP_TO_STANDARD_MATERIAL`, which automatically remaps from glTF's flexible
     * texture map system to the more specific texture map slots in Godot's `StandardMaterial3D` class.
     * Alternatively, `TextureMapMode.DO_NOT_REMAP` can be used to preserve the original texture maps
     * from the glTF file, which may be desirable if using the glTF file with custom shaders, but may
     * not display correctly with Godot's built-in materials.
     *
     * Generated from Godot docs: GLTFDocument.set_texture_map_mode
     */
    fun setTextureMapMode(textureMapMode: GLTFDocument.TextureMapMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setTextureMapModeBind, segment, textureMapMode.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * How to handle texture maps during import. The default and recommended value is
     * `TextureMapMode.REMAP_TO_STANDARD_MATERIAL`, which automatically remaps from glTF's flexible
     * texture map system to the more specific texture map slots in Godot's `StandardMaterial3D` class.
     * Alternatively, `TextureMapMode.DO_NOT_REMAP` can be used to preserve the original texture maps
     * from the glTF file, which may be desirable if using the glTF file with custom shaders, but may
     * not display correctly with Godot's built-in materials.
     *
     * Generated from Godot docs: GLTFDocument.get_texture_map_mode
     */
    fun getTextureMapMode(): GLTFDocument.TextureMapMode {
        checkOpen()
        val ret = GLTFDocument.TextureMapMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTextureMapModeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * How to deal with node visibility during export. This setting does nothing if all nodes are
     * visible. The default and recommended value is `VisibilityMode.INCLUDE_REQUIRED`, which uses the
     * `KHR_node_visibility` extension.
     *
     * Generated from Godot docs: GLTFDocument.set_visibility_mode
     */
    fun setVisibilityMode(visibilityMode: GLTFDocument.VisibilityMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setVisibilityModeBind, segment, visibilityMode.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * How to deal with node visibility during export. This setting does nothing if all nodes are
     * visible. The default and recommended value is `VisibilityMode.INCLUDE_REQUIRED`, which uses the
     * `KHR_node_visibility` extension.
     *
     * Generated from Godot docs: GLTFDocument.get_visibility_mode
     */
    fun getVisibilityMode(): GLTFDocument.VisibilityMode {
        checkOpen()
        val ret = GLTFDocument.VisibilityMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getVisibilityModeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Takes a path to a glTF file and imports the data at that file path to the given `GLTFState`
     * object through the `state` parameter. Note: The `base_path` tells `append_from_file` where to
     * find dependencies and can be empty.
     *
     * Generated from Godot docs: GLTFDocument.append_from_file
     */
    fun appendFromFile(path: String, state: GLTFState?, flags: Long = 0L, basePath: String = ""): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithStringObjectUInt32StringArgsRetLong(Binds.appendFromFileBind, segment, path, state?.requireOpenHandle() ?: NULL_SEGMENT, flags, basePath))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(state)
        return ret
    }

    /**
     * Takes a `PackedByteArray` defining a glTF and imports the data to the given `GLTFState` object
     * through the `state` parameter. Note: The `base_path` tells `append_from_buffer` where to find
     * dependencies and can be empty.
     *
     * Generated from Godot docs: GLTFDocument.append_from_buffer
     */
    fun appendFromBuffer(bytes: ByteArray, basePath: String, state: GLTFState?, flags: Long = 0L): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithPackedByteArrayStringObjectUInt32ArgsRetLong(Binds.appendFromBufferBind, segment, bytes, basePath, state?.requireOpenHandle() ?: NULL_SEGMENT, flags))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(state)
        return ret
    }

    /**
     * Takes a Godot Engine scene node and exports it and its descendants to the given `GLTFState`
     * object through the `state` parameter.
     *
     * Generated from Godot docs: GLTFDocument.append_from_scene
     */
    fun appendFromScene(node: Node, state: GLTFState?, flags: Long = 0L): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithTwoObjectUInt32ArgsRetLong(Binds.appendFromSceneBind, segment, node.segment, state?.requireOpenHandle() ?: NULL_SEGMENT, flags))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(node)
        ObjectCalls.keepReachable(state)
        return ret
    }

    /**
     * Takes a `GLTFState` object through the `state` parameter and returns a Godot Engine scene node.
     * The `bake_fps` parameter overrides the bake_fps in `state`.
     *
     * Generated from Godot docs: GLTFDocument.generate_scene
     */
    fun generateScene(state: GLTFState?, bakeFps: Double = 30.0, trimming: Boolean = false, removeImmutableTracks: Boolean = true): Node? {
        checkOpen()
        val ret = Node.wrap(ObjectCalls.ptrcallWithObjectDoubleTwoBoolArgsRetObject(Binds.generateSceneBind, segment, state?.requireOpenHandle() ?: NULL_SEGMENT, bakeFps, trimming, removeImmutableTracks))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(state)
        return ret
    }

    /**
     * Takes a `GLTFState` object through the `state` parameter and returns a glTF `PackedByteArray`.
     *
     * Generated from Godot docs: GLTFDocument.generate_buffer
     */
    fun generateBuffer(state: GLTFState?): ByteArray {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithObjectArgRetByteArray(Binds.generateBufferBind, segment, state?.requireOpenHandle() ?: NULL_SEGMENT)
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(state)
        return ret
    }

    /**
     * Takes a `GLTFState` object through the `state` parameter and writes a glTF file to the
     * filesystem. Note: The extension of the glTF file determines if it is a .glb binary file or a
     * .gltf text file.
     *
     * Generated from Godot docs: GLTFDocument.write_to_filesystem
     */
    fun writeToFilesystem(state: GLTFState?, path: String): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithObjectAndStringArgRetLong(Binds.writeToFilesystemBind, segment, state?.requireOpenHandle() ?: NULL_SEGMENT, path))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(state)
        return ret
    }

    /**
     * Godot's `GLTFDocument.RootNodeMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`GLTFDocument.RootNodeMode.<NAME>`).
     *
     * Generated from Godot docs: GLTFDocument.RootNodeMode
     */
    @JvmInline
    value class RootNodeMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Treat the Godot scene's root node as the root node of the glTF file, and mark it as the single
             * root node via the `GODOT_single_root` glTF extension. This will be parsed the same as
             * `RootNodeMode.KEEP_ROOT` if the implementation does not support `GODOT_single_root`.
             *
             * Generated from Godot docs: GLTFDocument.ROOT_NODE_MODE_SINGLE_ROOT
             */
            val SINGLE_ROOT: RootNodeMode get() = RootNodeMode(0L)
            /**
             * Treat the Godot scene's root node as the root node of the glTF file, but do not mark it as
             * anything special. An extra root node will be generated when importing into Godot. This uses only
             * vanilla glTF features. This is equivalent to the behavior in Godot 4.1 and earlier.
             *
             * Generated from Godot docs: GLTFDocument.ROOT_NODE_MODE_KEEP_ROOT
             */
            val KEEP_ROOT: RootNodeMode get() = RootNodeMode(1L)
            /**
             * Treat the Godot scene's root node as the name of the glTF scene, and add all of its children as
             * root nodes of the glTF file. This uses only vanilla glTF features. This avoids an extra root
             * node, but only the name of the Godot scene's root node will be preserved, as it will not be
             * saved as a node.
             *
             * Generated from Godot docs: GLTFDocument.ROOT_NODE_MODE_MULTI_ROOT
             */
            val MULTI_ROOT: RootNodeMode get() = RootNodeMode(2L)
        }
    }

    /**
     * Godot's `GLTFDocument.TextureMapMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`GLTFDocument.TextureMapMode.<NAME>`).
     *
     * Generated from Godot docs: GLTFDocument.TextureMapMode
     */
    @JvmInline
    value class TextureMapMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Import the texture maps in the glTF file as they are, without trying to fit them into specific
             * texture slots suitable for Godot's built-in materials. This may be desirable if using the glTF
             * file with custom shaders, but may not display correctly with Godot's built-in materials. This is
             * equivalent to the behavior in Godot 4.6 and earlier.
             *
             * Generated from Godot docs: GLTFDocument.TEXTURE_MAP_MODE_DO_NOT_REMAP
             */
            val DO_NOT_REMAP: TextureMapMode get() = TextureMapMode(0L)
            /**
             * Import the texture maps in the glTF file remapped to the most suitable texture slots based on
             * Godot's `StandardMaterial3D` class. This is the default behavior.
             *
             * Generated from Godot docs: GLTFDocument.TEXTURE_MAP_MODE_REMAP_TO_STANDARD_MATERIAL
             */
            val REMAP_TO_STANDARD_MATERIAL: TextureMapMode get() = TextureMapMode(1L)
        }
    }

    /**
     * Godot's `GLTFDocument.VisibilityMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`GLTFDocument.VisibilityMode.<NAME>`).
     *
     * Generated from Godot docs: GLTFDocument.VisibilityMode
     */
    @JvmInline
    value class VisibilityMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * If the scene contains any non-visible nodes, include them, mark them as non-visible with
             * `KHR_node_visibility`, and require that importers respect their non-visibility. Downside: If the
             * importer does not support `KHR_node_visibility`, the file cannot be imported.
             *
             * Generated from Godot docs: GLTFDocument.VISIBILITY_MODE_INCLUDE_REQUIRED
             */
            val INCLUDE_REQUIRED: VisibilityMode get() = VisibilityMode(0L)
            /**
             * If the scene contains any non-visible nodes, include them, mark them as non-visible with
             * `KHR_node_visibility`, and do not impose any requirements on importers. Downside: If the
             * importer does not support `KHR_node_visibility`, invisible objects will be visible.
             *
             * Generated from Godot docs: GLTFDocument.VISIBILITY_MODE_INCLUDE_OPTIONAL
             */
            val INCLUDE_OPTIONAL: VisibilityMode get() = VisibilityMode(1L)
            /**
             * If the scene contains any non-visible nodes, do not include them in the export. This is the same
             * as the behavior in Godot 4.4 and earlier. Downside: Invisible nodes will not exist in the
             * exported file.
             *
             * Generated from Godot docs: GLTFDocument.VISIBILITY_MODE_EXCLUDE
             */
            val EXCLUDE: VisibilityMode get() = VisibilityMode(2L)
        }
    }

    /**
     * Godot's `GLTFDocument.ImportFlags` bitfield as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`GLTFDocument.ImportFlags.<NAME>`).
     *
     * Generated from Godot docs: GLTFDocument.ImportFlags
     */
    @JvmInline
    value class ImportFlags(override val value: Long) : GodotEnumValue {
        infix fun or(other: ImportFlags): ImportFlags = ImportFlags(value or other.value)

        infix fun and(other: ImportFlags): ImportFlags = ImportFlags(value and other.value)

        infix fun xor(other: ImportFlags): ImportFlags = ImportFlags(value xor other.value)

        fun inv(): ImportFlags = ImportFlags(value.inv())

        operator fun contains(other: ImportFlags): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * If `true`, generate vertex tangents using Mikktspace (http://www.mikktspace.com/) if the input
             * meshes don't have tangent data. When possible, it's recommended to let the 3D modeling software
             * generate tangents on export instead of relying on this option. Tangents are required for correct
             * display of normal and height maps, along with any material/shader features that require
             * tangents. If you don't need material features that require tangents, disabling this can reduce
             * output file size and speed up importing if the source 3D file doesn't contain tangents.
             *
             * Generated from Godot docs: GLTFDocument.IMPORT_FLAG_GENERATE_TANGENT_ARRAYS
             */
            val GENERATE_TANGENT_ARRAYS: ImportFlags get() = ImportFlags(8L)
            /**
             * If checked, use named `Skin`s for animation. The `MeshInstance3D` node contains 3 properties of
             * relevance here: a skeleton `NodePath` pointing to the `Skeleton3D` node (usually `..`), a mesh,
             * and a skin: - The `Skeleton3D` node contains a list of bones with names, their pose and rest, a
             * name, and a parent bone. - The mesh is all of the raw vertex data needed to display a mesh. In
             * terms of the mesh, it knows how vertices are weight-painted and uses some internal numbering
             * often imported from 3D modeling software. - The skin contains the information necessary to bind
             * this mesh onto this Skeleton3D. For each of the internal bone IDs chosen by the 3D modeling
             * software, it contains two things. Firstly, a matrix known as the Bind Pose Matrix, Inverse Bind
             * Matrix, or IBM for short. Secondly, the `Skin` contains each bone's name (if this flag is
             * enabled), or the bone's index within the `Skeleton3D` list (if this flag is disabled). Together,
             * this information is enough to tell Godot how to use the bone poses in the `Skeleton3D` node to
             * render the mesh from each `MeshInstance3D`. Note that each `MeshInstance3D` may share binds, as
             * is common in models exported from Blender, or each `MeshInstance3D` may use a separate `Skin`
             * object, as is common in models exported from other tools such as Maya.
             *
             * Generated from Godot docs: GLTFDocument.IMPORT_FLAG_USE_NAMED_SKIN_BINDS
             */
            val USE_NAMED_SKIN_BINDS: ImportFlags get() = ImportFlags(16L)
            /**
             * Ignore meshes and materials on import. When importing a scene as an `AnimationLibrary`, this
             * flag is always enabled.
             *
             * Generated from Godot docs: GLTFDocument.IMPORT_FLAG_DISCARD_MESHES_AND_MATERIALS
             */
            val DISCARD_MESHES_AND_MATERIALS: ImportFlags get() = ImportFlags(32L)
            /**
             * If `true`, mesh compression will not be used. Consider enabling if you notice blocky artifacts
             * in your mesh normals or UVs, or if you have meshes that are larger than a few thousand meters in
             * each direction.
             *
             * Generated from Godot docs: GLTFDocument.IMPORT_FLAG_FORCE_DISABLE_MESH_COMPRESSION
             */
            val FORCE_DISABLE_MESH_COMPRESSION: ImportFlags get() = ImportFlags(64L)
        }
    }

    companion object {
        /**
         * Determines a mapping between the given glTF Object Model `json_pointer` and the corresponding
         * Godot node path(s) in the generated Godot scene. The details of this mapping are returned in a
         * `GLTFObjectModelProperty` object. Additional mappings can be supplied via the
         * `GLTFDocumentExtension._export_object_model_property` callback method.
         *
         * Generated from Godot docs: GLTFDocument.import_object_model_property
         */
        fun importObjectModelProperty(state: GLTFState?, jsonPointer: String): GLTFObjectModelProperty? {
            val ret = GLTFObjectModelProperty.wrapOwned(ObjectCalls.ptrcallWithObjectStringArgRetObject(Binds.importObjectModelPropertyBind, NULL_SEGMENT, state?.requireOpenHandle() ?: NULL_SEGMENT, jsonPointer))
            ObjectCalls.keepReachable(state)
            return ret
        }

        /**
         * Determines a mapping between the given Godot `node_path` and the corresponding glTF Object Model
         * JSON pointer(s) in the generated glTF file. The details of this mapping are returned in a
         * `GLTFObjectModelProperty` object. Additional mappings can be supplied via the
         * `GLTFDocumentExtension._import_object_model_property` callback method.
         *
         * Generated from Godot docs: GLTFDocument.export_object_model_property
         */
        fun exportObjectModelProperty(state: GLTFState?, nodePath: NodePath, godotNode: Node, gltfNodeIndex: Int): GLTFObjectModelProperty? {
            val ret = GLTFObjectModelProperty.wrapOwned(ObjectCalls.ptrcallWithObjectNodePathObjectIntArgsRetObject(Binds.exportObjectModelPropertyBind, NULL_SEGMENT, state?.requireOpenHandle() ?: NULL_SEGMENT, nodePath, godotNode.segment, gltfNodeIndex))
            ObjectCalls.keepReachable(state)
            ObjectCalls.keepReachable(godotNode)
            return ret
        }

        /**
         * Registers the given `GLTFDocumentExtension` instance with GLTFDocument. If `first_priority` is
         * `true`, this extension will be run first. Otherwise, it will be run last. Note: Like
         * GLTFDocument itself, all GLTFDocumentExtension classes must be stateless in order to function
         * properly. If you need to store data, use the `set_additional_data` and `get_additional_data`
         * methods in `GLTFState` or `GLTFNode`.
         *
         * Generated from Godot docs: GLTFDocument.register_gltf_document_extension
         */
        fun registerGltfDocumentExtension(extension: GLTFDocumentExtension?, firstPriority: Boolean = false) {
            ObjectCalls.ptrcallWithObjectAndBoolArg(Binds.registerGltfDocumentExtensionBind, NULL_SEGMENT, extension?.requireOpenHandle() ?: NULL_SEGMENT, firstPriority)
            ObjectCalls.keepReachable(extension)
        }

        /**
         * Unregisters the given `GLTFDocumentExtension` instance.
         *
         * Generated from Godot docs: GLTFDocument.unregister_gltf_document_extension
         */
        fun unregisterGltfDocumentExtension(extension: GLTFDocumentExtension?) {
            ObjectCalls.ptrcallWithObjectArgs(Binds.unregisterGltfDocumentExtensionBind, NULL_SEGMENT, listOf(extension?.requireOpenHandle() ?: NULL_SEGMENT))
            ObjectCalls.keepReachable(extension)
        }

        /**
         * Returns a list of all support glTF extensions, including extensions supported directly by the
         * engine, and extensions supported by user plugins registering `GLTFDocumentExtension` classes.
         * Note: If this method is run before a GLTFDocumentExtension is registered, its extensions won't
         * be included in the list. Be sure to only run this method after all extensions are registered. If
         * you run this when the engine starts, consider waiting a frame before calling this method to
         * ensure all extensions are registered.
         *
         * Generated from Godot docs: GLTFDocument.get_supported_gltf_extensions
         */
        fun getSupportedGltfExtensions(): List<String> {
            return ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getSupportedGltfExtensionsBind, NULL_SEGMENT)
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFDocument? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GLTFDocument? =
            if (handle.address() == 0L) null else RefCounted.owned(GLTFDocument(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GLTFDocument? =
            if (handle.address() == 0L) null else GLTFDocument(GodotHandle(handle))

        /** A new GLTFDocument, GDScript's `GLTFDocument.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): GLTFDocument =
            RefCounted.owned(GLTFDocument(GodotHandle(requireConstructed(ObjectCalls.constructObject("GLTFDocument"), "GLTFDocument"))))
    }

    private object Binds {
        private const val SET_IMAGE_FORMAT_HASH = 83702148L
        @JvmField
        val setImageFormatBind =
            ObjectCalls.getMethodBind("GLTFDocument", "set_image_format", SET_IMAGE_FORMAT_HASH)

        private const val GET_IMAGE_FORMAT_HASH = 201670096L
        @JvmField
        val getImageFormatBind =
            ObjectCalls.getMethodBind("GLTFDocument", "get_image_format", GET_IMAGE_FORMAT_HASH)

        private const val SET_LOSSY_QUALITY_HASH = 373806689L
        @JvmField
        val setLossyQualityBind =
            ObjectCalls.getMethodBind("GLTFDocument", "set_lossy_quality", SET_LOSSY_QUALITY_HASH)

        private const val GET_LOSSY_QUALITY_HASH = 1740695150L
        @JvmField
        val getLossyQualityBind =
            ObjectCalls.getMethodBind("GLTFDocument", "get_lossy_quality", GET_LOSSY_QUALITY_HASH)

        private const val SET_FALLBACK_IMAGE_FORMAT_HASH = 83702148L
        @JvmField
        val setFallbackImageFormatBind =
            ObjectCalls.getMethodBind("GLTFDocument", "set_fallback_image_format", SET_FALLBACK_IMAGE_FORMAT_HASH)

        private const val GET_FALLBACK_IMAGE_FORMAT_HASH = 201670096L
        @JvmField
        val getFallbackImageFormatBind =
            ObjectCalls.getMethodBind("GLTFDocument", "get_fallback_image_format", GET_FALLBACK_IMAGE_FORMAT_HASH)

        private const val SET_FALLBACK_IMAGE_QUALITY_HASH = 373806689L
        @JvmField
        val setFallbackImageQualityBind =
            ObjectCalls.getMethodBind("GLTFDocument", "set_fallback_image_quality", SET_FALLBACK_IMAGE_QUALITY_HASH)

        private const val GET_FALLBACK_IMAGE_QUALITY_HASH = 1740695150L
        @JvmField
        val getFallbackImageQualityBind =
            ObjectCalls.getMethodBind("GLTFDocument", "get_fallback_image_quality", GET_FALLBACK_IMAGE_QUALITY_HASH)

        private const val SET_ROOT_NODE_MODE_HASH = 463633402L
        @JvmField
        val setRootNodeModeBind =
            ObjectCalls.getMethodBind("GLTFDocument", "set_root_node_mode", SET_ROOT_NODE_MODE_HASH)

        private const val GET_ROOT_NODE_MODE_HASH = 948057992L
        @JvmField
        val getRootNodeModeBind =
            ObjectCalls.getMethodBind("GLTFDocument", "get_root_node_mode", GET_ROOT_NODE_MODE_HASH)

        private const val SET_TEXTURE_MAP_MODE_HASH = 3144426102L
        @JvmField
        val setTextureMapModeBind =
            ObjectCalls.getMethodBind("GLTFDocument", "set_texture_map_mode", SET_TEXTURE_MAP_MODE_HASH)

        private const val GET_TEXTURE_MAP_MODE_HASH = 2113256994L
        @JvmField
        val getTextureMapModeBind =
            ObjectCalls.getMethodBind("GLTFDocument", "get_texture_map_mode", GET_TEXTURE_MAP_MODE_HASH)

        private const val SET_VISIBILITY_MODE_HASH = 2803579218L
        @JvmField
        val setVisibilityModeBind =
            ObjectCalls.getMethodBind("GLTFDocument", "set_visibility_mode", SET_VISIBILITY_MODE_HASH)

        private const val GET_VISIBILITY_MODE_HASH = 3885445962L
        @JvmField
        val getVisibilityModeBind =
            ObjectCalls.getMethodBind("GLTFDocument", "get_visibility_mode", GET_VISIBILITY_MODE_HASH)

        private const val APPEND_FROM_FILE_HASH = 866380864L
        @JvmField
        val appendFromFileBind =
            ObjectCalls.getMethodBind("GLTFDocument", "append_from_file", APPEND_FROM_FILE_HASH)

        private const val APPEND_FROM_BUFFER_HASH = 1616081266L
        @JvmField
        val appendFromBufferBind =
            ObjectCalls.getMethodBind("GLTFDocument", "append_from_buffer", APPEND_FROM_BUFFER_HASH)

        private const val APPEND_FROM_SCENE_HASH = 1622574258L
        @JvmField
        val appendFromSceneBind =
            ObjectCalls.getMethodBind("GLTFDocument", "append_from_scene", APPEND_FROM_SCENE_HASH)

        private const val GENERATE_SCENE_HASH = 596118388L
        @JvmField
        val generateSceneBind =
            ObjectCalls.getMethodBind("GLTFDocument", "generate_scene", GENERATE_SCENE_HASH)

        private const val GENERATE_BUFFER_HASH = 741783455L
        @JvmField
        val generateBufferBind =
            ObjectCalls.getMethodBind("GLTFDocument", "generate_buffer", GENERATE_BUFFER_HASH)

        private const val WRITE_TO_FILESYSTEM_HASH = 1784551478L
        @JvmField
        val writeToFilesystemBind =
            ObjectCalls.getMethodBind("GLTFDocument", "write_to_filesystem", WRITE_TO_FILESYSTEM_HASH)

        private const val IMPORT_OBJECT_MODEL_PROPERTY_HASH = 1206708632L
        @JvmField
        val importObjectModelPropertyBind =
            ObjectCalls.getMethodBind("GLTFDocument", "import_object_model_property", IMPORT_OBJECT_MODEL_PROPERTY_HASH)

        private const val EXPORT_OBJECT_MODEL_PROPERTY_HASH = 314209806L
        @JvmField
        val exportObjectModelPropertyBind =
            ObjectCalls.getMethodBind("GLTFDocument", "export_object_model_property", EXPORT_OBJECT_MODEL_PROPERTY_HASH)

        private const val REGISTER_GLTF_DOCUMENT_EXTENSION_HASH = 3752678331L
        @JvmField
        val registerGltfDocumentExtensionBind =
            ObjectCalls.getMethodBind("GLTFDocument", "register_gltf_document_extension", REGISTER_GLTF_DOCUMENT_EXTENSION_HASH)

        private const val UNREGISTER_GLTF_DOCUMENT_EXTENSION_HASH = 2684415758L
        @JvmField
        val unregisterGltfDocumentExtensionBind =
            ObjectCalls.getMethodBind("GLTFDocument", "unregister_gltf_document_extension", UNREGISTER_GLTF_DOCUMENT_EXTENSION_HASH)

        private const val GET_SUPPORTED_GLTF_EXTENSIONS_HASH = 2981934095L
        @JvmField
        val getSupportedGltfExtensionsBind =
            ObjectCalls.getMethodBind("GLTFDocument", "get_supported_gltf_extensions", GET_SUPPORTED_GLTF_EXTENSIONS_HASH)
    }
}

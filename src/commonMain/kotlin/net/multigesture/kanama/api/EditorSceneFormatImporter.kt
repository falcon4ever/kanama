package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Imports scenes from third-parties' 3D files.
 *
 * Generated from Godot docs: EditorSceneFormatImporter
 */
open class EditorSceneFormatImporter(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Add a specific import option (name and default value only). This function can only be called
     * from `_get_import_options`.
     *
     * Generated from Godot docs: EditorSceneFormatImporter.add_import_option
     */
    fun addImportOption(name: String, value: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithStringAndVariantArg(addImportOptionBind, segment, name, value)
    }

    /**
     * Add a specific import option. This function can only be called from `_get_import_options`.
     *
     * Generated from Godot docs: EditorSceneFormatImporter.add_import_option_advanced
     */
    fun addImportOptionAdvanced(type: VariantType, name: String, defaultValue: Any?, hint: GodotPropertyHint = GodotPropertyHint.NONE, hintString: String = "", usageFlags: Int = 6) {
        checkOpen()
        ObjectCalls.ptrcallWithLongStringVariantLongStringIntArgs(addImportOptionAdvancedBind, segment, type.value, name, defaultValue, hint.value, hintString, usageFlags)
    }

    @JvmInline
    value class ImportFlags(val value: Long) {
        infix fun or(other: ImportFlags): ImportFlags = ImportFlags(value or other.value)

        infix fun and(other: ImportFlags): ImportFlags = ImportFlags(value and other.value)

        infix fun xor(other: ImportFlags): ImportFlags = ImportFlags(value xor other.value)

        fun inv(): ImportFlags = ImportFlags(value.inv())

        operator fun contains(other: ImportFlags): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * Unused flag (this has no effect when enabled).
             *
             * Generated from Godot docs: EditorSceneFormatImporter.IMPORT_SCENE
             */
            val SCENE: ImportFlags get() = ImportFlags(1L)
            /**
             * Import animations from the 3D scene. When importing a scene as an `AnimationLibrary`, this flag
             * is always enabled.
             *
             * Generated from Godot docs: EditorSceneFormatImporter.IMPORT_ANIMATION
             */
            val ANIMATION: ImportFlags get() = ImportFlags(2L)
            /**
             * Unused flag (this has no effect when enabled).
             *
             * Generated from Godot docs: EditorSceneFormatImporter.IMPORT_FAIL_ON_MISSING_DEPENDENCIES
             */
            val FAIL_ON_MISSING_DEPENDENCIES: ImportFlags get() = ImportFlags(4L)
            /**
             * If `true`, generate vertex tangents using Mikktspace (http://www.mikktspace.com/) if the input
             * meshes don't have tangent data. When possible, it's recommended to let the 3D modeling software
             * generate tangents on export instead of relying on this option. Tangents are required for correct
             * display of normal and height maps, along with any material/shader features that require
             * tangents. If you don't need material features that require tangents, disabling this can reduce
             * output file size and speed up importing if the source 3D file doesn't contain tangents.
             *
             * Generated from Godot docs: EditorSceneFormatImporter.IMPORT_GENERATE_TANGENT_ARRAYS
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
             * Generated from Godot docs: EditorSceneFormatImporter.IMPORT_USE_NAMED_SKIN_BINDS
             */
            val USE_NAMED_SKIN_BINDS: ImportFlags get() = ImportFlags(16L)
            /**
             * Ignore meshes and materials on import. When importing a scene as an `AnimationLibrary`, this
             * flag is always enabled.
             *
             * Generated from Godot docs: EditorSceneFormatImporter.IMPORT_DISCARD_MESHES_AND_MATERIALS
             */
            val DISCARD_MESHES_AND_MATERIALS: ImportFlags get() = ImportFlags(32L)
            /**
             * If `true`, mesh compression will not be used. Consider enabling if you notice blocky artifacts
             * in your mesh normals or UVs, or if you have meshes that are larger than a few thousand meters in
             * each direction.
             *
             * Generated from Godot docs: EditorSceneFormatImporter.IMPORT_FORCE_DISABLE_MESH_COMPRESSION
             */
            val FORCE_DISABLE_MESH_COMPRESSION: ImportFlags get() = ImportFlags(64L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorSceneFormatImporter? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): EditorSceneFormatImporter? =
            if (handle.address() == 0L) null else EditorSceneFormatImporter(GodotHandle(handle))

        private const val ADD_IMPORT_OPTION_HASH = 402577236L
        private val addImportOptionBind by lazy {
            ObjectCalls.getMethodBind("EditorSceneFormatImporter", "add_import_option", ADD_IMPORT_OPTION_HASH)
        }

        private const val ADD_IMPORT_OPTION_ADVANCED_HASH = 3674075649L
        private val addImportOptionAdvancedBind by lazy {
            ObjectCalls.getMethodBind("EditorSceneFormatImporter", "add_import_option_advanced", ADD_IMPORT_OPTION_ADVANCED_HASH)
        }
    }
}

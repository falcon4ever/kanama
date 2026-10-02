package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A native library for GDExtension.
 *
 * Generated from Godot docs: GDExtension
 */
class GDExtension(handle: GodotHandle) : Resource(handle) {
    /**
     * Returns `true` if this extension's library has been opened.
     *
     * Generated from Godot docs: GDExtension.is_library_open
     */
    fun isLibraryOpen(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isLibraryOpenBind, segment)
    }

    /**
     * Returns the lowest level required for this extension to be properly initialized (see the
     * `InitializationLevel` enum).
     *
     * Generated from Godot docs: GDExtension.get_minimum_library_initialization_level
     */
    fun getMinimumLibraryInitializationLevel(): GDExtension.InitializationLevel {
        checkOpen()
        return GDExtension.InitializationLevel(ObjectCalls.ptrcallNoArgsRetLong(getMinimumLibraryInitializationLevelBind, segment))
    }

    @JvmInline
    value class InitializationLevel(val value: Long) {
        companion object {
            /**
             * The library is initialized at the same time as the core features of the engine.
             *
             * Generated from Godot docs: GDExtension.INITIALIZATION_LEVEL_CORE
             */
            val CORE: InitializationLevel get() = InitializationLevel(0L)
            /**
             * The library is initialized at the same time as the engine's servers (such as `RenderingServer`
             * or `PhysicsServer3D`).
             *
             * Generated from Godot docs: GDExtension.INITIALIZATION_LEVEL_SERVERS
             */
            val SERVERS: InitializationLevel get() = InitializationLevel(1L)
            /**
             * The library is initialized at the same time as the engine's scene-related classes.
             *
             * Generated from Godot docs: GDExtension.INITIALIZATION_LEVEL_SCENE
             */
            val SCENE: InitializationLevel get() = InitializationLevel(2L)
            /**
             * The library is initialized at the same time as the engine's editor classes. Only happens when
             * loading the GDExtension in the editor.
             *
             * Generated from Godot docs: GDExtension.INITIALIZATION_LEVEL_EDITOR
             */
            val EDITOR: InitializationLevel get() = InitializationLevel(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GDExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): GDExtension? =
            if (handle.address() == 0L) null else GDExtension(GodotHandle(handle))

        private const val IS_LIBRARY_OPEN_HASH = 36873697L
        private val isLibraryOpenBind by lazy {
            ObjectCalls.getMethodBind("GDExtension", "is_library_open", IS_LIBRARY_OPEN_HASH)
        }

        private const val GET_MINIMUM_LIBRARY_INITIALIZATION_LEVEL_HASH = 964858755L
        private val getMinimumLibraryInitializationLevelBind by lazy {
            ObjectCalls.getMethodBind("GDExtension", "get_minimum_library_initialization_level", GET_MINIMUM_LIBRARY_INITIALIZATION_LEVEL_HASH)
        }
    }
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * An optimized translation.
 *
 * Generated from Godot docs: OptimizedTranslation
 */
class OptimizedTranslation(handle: MemorySegment) : Translation(handle) {
    /**
     * Generates and sets an optimized translation from the given `Translation` resource. Returns
     * `true` if successful. Note: Messages in `from` should not use context or plural forms. Note:
     * This method is intended to be used in the editor. It does nothing when called from an exported
     * project.
     *
     * Generated from Godot docs: OptimizedTranslation.generate
     */
    fun generate(from: Translation?): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(generateBind, handle, from?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OptimizedTranslation? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OptimizedTranslation? =
            if (handle.address() == 0L) null else OptimizedTranslation(handle)

        private const val GENERATE_HASH = 2141509306L
        private val generateBind by lazy {
            ObjectCalls.getMethodBind("OptimizedTranslation", "generate", GENERATE_HASH)
        }
    }
}

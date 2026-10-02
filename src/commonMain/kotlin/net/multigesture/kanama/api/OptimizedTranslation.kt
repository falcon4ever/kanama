package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * An optimized translation.
 *
 * Generated from Godot docs: OptimizedTranslation
 */
class OptimizedTranslation(handle: GodotHandle) : Translation(handle) {
    /**
     * Generates and sets an optimized translation from the given `Translation` resource. Returns
     * `true` if successful. Note: Messages in `from` should not use context or plural forms. Note:
     * This method is intended to be used in the editor. It does nothing when called from an exported
     * project.
     *
     * Generated from Godot docs: OptimizedTranslation.generate
     */
    fun generate(from: Translation?): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectArgRetBool(generateBind, segment, from?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OptimizedTranslation? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OptimizedTranslation? =
            if (handle.address() == 0L) null else OptimizedTranslation(GodotHandle(handle))

        private const val GENERATE_HASH = 2141509306L
        private val generateBind by lazy {
            ObjectCalls.getMethodBind("OptimizedTranslation", "generate", GENERATE_HASH)
        }
    }
}

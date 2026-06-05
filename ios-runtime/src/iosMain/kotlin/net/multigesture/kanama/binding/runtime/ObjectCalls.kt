package net.multigesture.kanama.binding.runtime

import java.lang.foreign.MemorySegment
import kotlinx.cinterop.ExperimentalForeignApi
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_construct_object

object ObjectCalls {
    @OptIn(ExperimentalForeignApi::class)
    fun constructObject(className: String): MemorySegment =
        MemorySegment.ofAddress(kanama_ios_godot_construct_object(className))
}

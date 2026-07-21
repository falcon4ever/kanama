package net.multigesture.kanama.example

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.annotations.OverrideVirtual
import net.multigesture.kanama.annotations.ScriptClass

/**
 * Task 29 probe: exercises the PackedByteArray (`ByteArray`) and PackedInt32Array (`IntArray`)
 * virtual-return families against TextServerExtension's virtual table. Width-sensitive values:
 * full-range bytes and Int.MAX_VALUE catch element-width regressions.
 */
@ScriptClass(attachTo = "TextServerExtension")
class TextServerVirtualReturnProbe(val godotObject: MemorySegment) {

  // PackedByteArray return (ByteArray — 1-byte elements, full signed range).
  @OverrideVirtual fun _get_support_data(): ByteArray = byteArrayOf(0x7F, 0x00, -0x80)

  // PackedInt32Array return (IntArray — 4-byte elements; Int.MAX_VALUE would
  // corrupt under a wrong-width write).
  @OverrideVirtual
  fun _string_get_word_breaks(string: String, language: String, charsPerLine: Long): IntArray =
    intArrayOf(0, string.length, Int.MAX_VALUE)
}

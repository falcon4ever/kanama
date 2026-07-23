@file:OptIn(ExperimentalWasmJsInterop::class)

package net.multigesture.kanama.web

import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsAny
import kotlin.js.JsName

@JsName("Int32Array")
private external class WebInt32Array(length: Int) : JsAny {
  operator fun get(index: Int): Int

  operator fun set(index: Int, value: Int)
}

/** Reused packed command storage. Records are variable-width and never grow the backing array. */
internal class WebCommandBuffer(capacity: Int) {
  private val commandCapacity = capacity
  private val words = WebInt32Array(capacity * MAX_WORDS_PER_COMMAND)
  private var commandCount = 0
  private var wordCount = 0

  fun clear() {
    commandCount = 0
    wordCount = 0
  }

  fun appendScalarMutation(objectHandle: Int, value: Int) {
    val offset = reserve(WORDS_SCALAR_OR_VECTOR)
    words[offset] = OPCODE_SCALAR_MUTATION
    words[offset + 1] = objectHandle
    words[offset + 2] = value
    words[offset + 3] = 0
  }

  fun appendVector2Mutation(opcode: Int, objectHandle: Int, x: Float, y: Float) {
    val offset = reserve(WORDS_SCALAR_OR_VECTOR)
    words[offset] = opcode
    words[offset + 1] = objectHandle
    words[offset + 2] = x.toBits()
    words[offset + 3] = y.toBits()
  }

  fun appendBoolMutation(opcode: Int, objectHandle: Int, value: Boolean) {
    val offset = reserve(WORDS_SCALAR_OR_VECTOR)
    words[offset] = opcode
    words[offset + 1] = objectHandle
    words[offset + 2] = if (value) 1 else 0
    words[offset + 3] = 0
  }

  fun appendColorMutation(opcode: Int, objectHandle: Int, r: Float, g: Float, b: Float, a: Float) {
    val offset = reserve(WORDS_COLOR)
    words[offset] = opcode
    words[offset + 1] = objectHandle
    words[offset + 2] = r.toBits()
    words[offset + 3] = g.toBits()
    words[offset + 4] = b.toBits()
    words[offset + 5] = a.toBits()
  }

  fun appendNoArgsMutation(opcode: Int, objectHandle: Int) {
    val offset = reserve(WORDS_NOARGS)
    words[offset] = opcode
    words[offset + 1] = objectHandle
  }

  fun appendObjectArg(opcode: Int, objectHandle: Int, valueHandle: Int) {
    val offset = reserve(WORDS_OBJECT_ARG)
    words[offset] = opcode
    words[offset + 1] = objectHandle
    words[offset + 2] = valueHandle
  }

  fun appendStringNameMutation(opcode: Int, objectHandle: Int, value: String) {
    val offset = reserve(WORDS_OBJECT_ARG)
    words[offset] = opcode
    words[offset + 1] = objectHandle
    words[offset + 2] = internWebCommandStringName(value)
  }

  fun appendLongMutation(opcode: Int, objectHandle: Int, value: Long) {
    require(value in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())
    val offset = reserve(WORDS_OBJECT_ARG)
    words[offset] = opcode
    words[offset + 1] = objectHandle
    words[offset + 2] = value.toInt()
  }

  fun appendDoubleMutation(opcode: Int, objectHandle: Int, value: Double) {
    val bits = value.toBits()
    val offset = reserve(WORDS_SCALAR_OR_VECTOR)
    words[offset] = opcode
    words[offset + 1] = objectHandle
    words[offset + 2] = bits.toInt()
    words[offset + 3] = (bits ushr 32).toInt()
  }

  fun appendObjectBoolLongArgs(
    opcode: Int,
    objectHandle: Int,
    valueHandle: Int,
    boolValue: Boolean,
    longValue: Long,
  ) {
    require(longValue in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())
    val offset = reserve(WORDS_OBJECT_BOOL_LONG_ARGS)
    words[offset] = opcode
    words[offset + 1] = objectHandle
    words[offset + 2] = valueHandle
    words[offset + 3] = if (boolValue) 1 else 0
    words[offset + 4] = longValue.toInt()
  }

  fun appendDrawTexture(
    opcode: Int,
    objectHandle: Int,
    textureHandle: Int,
    x: Float,
    y: Float,
    r: Float,
    g: Float,
    b: Float,
    a: Float,
  ) {
    val offset = reserve(WORDS_DRAW_TEXTURE)
    words[offset] = opcode
    words[offset + 1] = objectHandle
    words[offset + 2] = textureHandle
    words[offset + 3] = x.toBits()
    words[offset + 4] = y.toBits()
    words[offset + 5] = r.toBits()
    words[offset + 6] = g.toBits()
    words[offset + 7] = b.toBits()
    words[offset + 8] = a.toBits()
  }

  fun flush(): Int {
    if (commandCount == 0) return 0
    val expected = commandCount
    val expectedWords = wordCount
    // Mark the batch consumed before crossing into Godot. Applying add_child can synchronously run
    // a generated child's _ready callback; that nested callback must see an empty queue rather than
    // recursively replaying the parent batch.
    clear()
    val applied = flushWebCommands(words, expectedWords, expected)
    check(applied == expected) { "Kanama Web command batch applied $applied of $expected commands" }
    return applied
  }

  private fun reserve(wordsNeeded: Int): Int {
    check(commandCount < commandCapacity) { "Kanama Web command buffer capacity exceeded" }
    check(wordCount + wordsNeeded <= commandCapacity * MAX_WORDS_PER_COMMAND) {
      "Kanama Web command word capacity exceeded"
    }
    val offset = wordCount
    wordCount += wordsNeeded
    commandCount += 1
    return offset
  }

  companion object {
    const val OPCODE_SCALAR_MUTATION = 100
    const val WORDS_NOARGS = 2
    const val WORDS_OBJECT_ARG = 3
    const val WORDS_SCALAR_OR_VECTOR = 4
    const val WORDS_OBJECT_BOOL_LONG_ARGS = 5
    const val WORDS_COLOR = 6
    const val WORDS_DRAW_TEXTURE = 9
    const val MAX_WORDS_PER_COMMAND = WORDS_DRAW_TEXTURE
    // The benchmark contributes 10,000 data mutations plus one phase-control mutation (redraw).
    const val BENCHMARK_COMMAND_CAPACITY = 10_001
    const val DRAW_COMMAND_CAPACITY = 100_000
  }
}

private fun flushWebCommands(words: WebInt32Array, wordCount: Int, commandCount: Int): Int =
  js("globalThis.KanamaWebBridge?.flushCommands(words, wordCount, commandCount) ?? commandCount")

private fun internWebCommandStringName(value: String): Int =
  js("globalThis.KanamaWebBridge.internCommandStringName(value)")

internal fun webNowMillis(): Double = js("performance.now()")

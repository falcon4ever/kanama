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

/** Reused fixed-width command storage: `[opcode, object-handle, value-0, value-1]`. */
internal class WebCommandBuffer(capacity: Int) {
  private val capacity = capacity
  private val words = WebInt32Array(capacity * WORDS_PER_COMMAND)
  private var size = 0

  fun clear() {
    size = 0
  }

  fun appendScalarMutation(objectHandle: Int, value: Int) {
    check(size < wordsCapacity()) { "Kanama Web command buffer capacity exceeded" }
    val offset = size * WORDS_PER_COMMAND
    words[offset] = OPCODE_SCALAR_MUTATION
    words[offset + 1] = objectHandle
    words[offset + 2] = value
    words[offset + 3] = 0
    size += 1
  }

  fun appendPositionMutation(objectHandle: Int, x: Float, y: Float) {
    check(size < wordsCapacity()) { "Kanama Web command buffer capacity exceeded" }
    val offset = size * WORDS_PER_COMMAND
    words[offset] = OPCODE_POSITION_MUTATION
    words[offset + 1] = objectHandle
    words[offset + 2] = x.toBits()
    words[offset + 3] = y.toBits()
    size += 1
  }

  fun appendNoArgsMutation(opcode: Int, objectHandle: Int) {
    check(size < wordsCapacity()) { "Kanama Web command buffer capacity exceeded" }
    val offset = size * WORDS_PER_COMMAND
    words[offset] = opcode
    words[offset + 1] = objectHandle
    words[offset + 2] = 0
    words[offset + 3] = 0
    size += 1
  }

  fun flush(): Int {
    if (size == 0) return 0
    val commandCount = size
    val applied = flushWebCommands(words, commandCount)
    size = 0
    return applied
  }

  private fun wordsCapacity(): Int = capacity

  companion object {
    const val OPCODE_SCALAR_MUTATION = 100
    const val OPCODE_POSITION_MUTATION = 3
    const val WORDS_PER_COMMAND = 4
    // The benchmark contributes 10,000 data mutations plus one phase-control mutation (redraw).
    const val BENCHMARK_COMMAND_CAPACITY = 10_001
  }
}

private fun flushWebCommands(words: WebInt32Array, count: Int): Int =
  js("globalThis.KanamaWebBridge?.flushCommands(words, count) ?? count")

internal fun webNowMillis(): Double = js("performance.now()")

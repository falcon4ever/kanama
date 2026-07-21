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

/** Reused fixed-width command storage: `[opcode, object-handle, scalar-value]`. */
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
    size += 1
  }

  fun flush(): Int {
    if (size == 0) return 0
    return flushWebCommands(words, size)
  }

  private fun wordsCapacity(): Int = capacity

  companion object {
    const val OPCODE_SCALAR_MUTATION = 1
    const val WORDS_PER_COMMAND = 3
    const val BENCHMARK_COMMAND_CAPACITY = 10_000
  }
}

private fun flushWebCommands(words: WebInt32Array, count: Int): Int =
  js("globalThis.KanamaWebBridge?.flushCommands(words, count) ?? count")

internal fun webNowMillis(): Double = js("performance.now()")

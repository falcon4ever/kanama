@file:OptIn(ExperimentalWasmJsInterop::class)

package net.multigesture.kanama.api

import kotlin.js.ExperimentalWasmJsInterop

internal actual fun releaseWebResource(resourceHandle: Int) {
  val released = releaseWebResourceInterop(resourceHandle)
  check(released == 1) { "Unknown or already released Kanama Web resource handle=$resourceHandle" }
}

private fun releaseWebResourceInterop(resourceHandle: Int): Int =
  js("globalThis.KanamaWebBridge.releaseResource(resourceHandle)")

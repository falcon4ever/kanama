@file:OptIn(ExperimentalWasmJsInterop::class)

package net.multigesture.kanama.binding.runtime

import kotlin.js.ExperimentalWasmJsInterop
import net.multigesture.kanama.api.WebFrameScheduler

/**
 * One builtin call over the bridge (task 134 D1). It is stateless, so any proxy can answer it: the
 * running script's when there is one, else the bridge picks a live proxy. Nothing is flushed first
 * -- a builtin method reads no engine state.
 */
internal actual fun webBuiltinTransport(packed: String): String =
  immediateWebBuiltinCall(WebFrameScheduler.currentOwnerOrZero(), packed)

private fun immediateWebBuiltinCall(ownerHandle: Int, packed: String): String =
  js("globalThis.KanamaWebBridge.immediateBuiltinCall(ownerHandle, packed)")

@file:OptIn(ExperimentalWasmJsInterop::class)

package net.multigesture.kanama.api

import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsAny
import kotlin.js.toJsReference
import net.multigesture.kanama.web.containsWebBrowserHandle
import net.multigesture.kanama.web.flushWebCommandsBeforeRelease
import net.multigesture.kanama.web.isRefCountedBrowserHandle

/** Bit 30: a browser handle (a script handle never has it, and is not the bridge's to hold). */
private const val BROWSER_HANDLE_BIT = 0x40000000

internal actual fun webWrapperCreated(wrapper: GodotObject, token: Int) {
  if (!isRefCountedWebHandle(token)) return
  registerWrapperInterop(wrapper.toJsReference(), token)
}

internal actual fun releaseWebWrapper(wrapper: GodotObject, token: Int) {
  if ((token and BROWSER_HANDLE_BIT) == 0) return
  if (isRefCountedBrowserHandle(token)) flushWebCommandsBeforeRelease()
  closeWrapperInterop(wrapper.toJsReference(), token)
}

internal actual fun dropWebWrapperHold(wrapper: GodotObject, token: Int) {
  if (!isRefCountedWebHandle(token)) return
  dropWrapperInterop(wrapper.toJsReference(), token)
}

internal actual fun isRefCountedWebHandle(token: Int): Boolean =
  (token and BROWSER_HANDLE_BIT) != 0 && isRefCountedBrowserHandle(token)

private fun registerWrapperInterop(ref: JsAny, handle: Int): Int =
  js("globalThis.KanamaWebBridge.registerWrapper(ref, handle)")

private fun dropWrapperInterop(ref: JsAny, handle: Int): Int =
  js("globalThis.KanamaWebBridge.dropWrapper(ref, handle)")

private fun closeWrapperInterop(ref: JsAny, handle: Int): Int =
  js("globalThis.KanamaWebBridge.closeWrapper(ref, handle)")

internal actual fun isWebBrowserHandleLive(handle: Int): Boolean = containsWebBrowserHandle(handle)

internal actual fun instantiateWebScript(className: String): Int =
  instantiateWebScriptInterop(className)

private fun instantiateWebScriptInterop(className: String): Int =
  js("globalThis.KanamaWebBridge.instantiateScript(className)")

internal actual fun releaseWebScriptResource(handle: Int) {
  flushWebCommandsBeforeRelease()
  val released = releaseWebScriptResourceInterop(handle)
  check(released == 1) { "Unknown or already released Kanama Web script resource handle=$handle" }
}

private fun releaseWebScriptResourceInterop(handle: Int): Int =
  js("globalThis.KanamaWebBridge.releaseScriptResource(handle)")

internal actual fun webGenericImmediateStringCall(target: GodotObject, method: String): String =
  net.multigesture.kanama.web.WebExperimentalGenericCall.callImmediate(target, method).asString()

internal actual fun webGenericCall(target: GodotObject, method: String, args: List<Any?>): Any? =
  net.multigesture.kanama.web.WebExperimentalGenericCall.callImmediate(target, method, args).asAny()

internal actual fun webGenericQueue(target: GodotObject, method: String, args: List<Any?>) {
  net.multigesture.kanama.web.WebExperimentalGenericCall.queueVoidCall(target, method, args)
}

internal actual fun webEmitSignalGeneric(target: GodotObject, signal: String, args: Array<out Any?>) {
  net.multigesture.kanama.web.WebExperimentalGenericCall.callImmediate(
    target,
    "emit_signal",
    listOf<Any?>(signal) + args,
  )
}

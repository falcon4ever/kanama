@file:OptIn(ExperimentalWasmJsInterop::class)

package net.multigesture.kanama.api

import kotlin.js.ExperimentalWasmJsInterop
import net.multigesture.kanama.web.WebBrowserHandleKind
import net.multigesture.kanama.web.unregisterWebBrowserHandle

internal actual fun releaseWebResource(resourceHandle: Int) {
  val released = releaseWebResourceInterop(resourceHandle)
  check(released == 1) { "Unknown or already released Kanama Web resource handle=$resourceHandle" }
  unregisterWebBrowserHandle(resourceHandle, WebBrowserHandleKind.RESOURCE)
}

private fun releaseWebResourceInterop(resourceHandle: Int): Int =
  js("globalThis.KanamaWebBridge.releaseResource(resourceHandle)")

internal actual fun isWebBrowserHandleLive(handle: Int): Boolean =
  net.multigesture.kanama.web.containsWebBrowserHandle(handle)

internal actual fun releaseWebCollision(collisionHandle: Int) {
  val released = releaseWebCollisionInterop(collisionHandle)
  check(released == 1) { "Unknown or already released Kanama Web collision handle=$collisionHandle" }
  unregisterWebBrowserHandle(collisionHandle, WebBrowserHandleKind.OBJECT)
}

private fun releaseWebCollisionInterop(collisionHandle: Int): Int =
  js("globalThis.KanamaWebBridge.releaseCollision(collisionHandle)")

@file:OptIn(ExperimentalWasmJsInterop::class)

package net.multigesture.kanama.web

import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsException

/**
 * Hand-written Web transport primitives (Task 60a, "Option A").
 *
 * These `js(...)` externs are the codec/transport boundary between the Kanama Wasm module and the
 * `KanamaWebBridge` JavaScript seam. They are hand-written because the JS-interop bodies are not
 * derivable from the platform-neutral model; the generated dispatch in
 * `WebCommonGodotBackend.generated.kt` calls them. Admitting a new call family is a regenerated
 * dispatch diff plus, where a new crossing shape appears, a transport extern added here.
 */
/**
 * Task 131 item 14: hands a rendered script-error report to the bridge, which reaches `push_error`.
 */
internal fun jsReportScriptError(text: String): Unit =
  js("globalThis.KanamaWebBridge.reportScriptError(text)")

internal fun immediateWebChildCount(objectId: Int, includeInternal: Boolean): Int = webFreedAware {
  jsImmediateWebChildCount(objectId, includeInternal)
}

private fun jsImmediateWebChildCount(objectId: Int, includeInternal: Boolean): Int =
  js("globalThis.KanamaWebBridge.immediateChildCount(objectId, includeInternal)")

internal fun immediateWebResourceLoad(path: String, typeHint: String, cacheMode: Int): Int =
  webFreedAware {
    jsImmediateWebResourceLoad(path, typeHint, cacheMode)
  }

private fun jsImmediateWebResourceLoad(path: String, typeHint: String, cacheMode: Int): Int =
  js("globalThis.KanamaWebBridge.immediateResourceLoad(path, typeHint, cacheMode)")

internal fun immediateWebEmitSignal(objectId: Int, name: String, value: Int): Int = webFreedAware {
  jsImmediateWebEmitSignal(objectId, name, value)
}

private fun jsImmediateWebEmitSignal(objectId: Int, name: String, value: Int): Int =
  js("globalThis.KanamaWebBridge.immediateEmitSignal(objectId, name, value)")

internal fun immediateWebEmitSignalNoArgs(objectId: Int, name: String): Int = webFreedAware {
  jsImmediateWebEmitSignalNoArgs(objectId, name)
}

private fun jsImmediateWebEmitSignalNoArgs(objectId: Int, name: String): Int =
  js("globalThis.KanamaWebBridge.immediateEmitSignalNoArgs(objectId, name)")

internal fun immediateWebConstructObject(className: String): Int = webFreedAware {
  jsImmediateWebConstructObject(className)
}

private fun jsImmediateWebConstructObject(className: String): Int =
  js("globalThis.KanamaWebBridge.immediateConstructObject(className)")

internal fun immediateWebNodeLookup(objectId: Int, path: String): Int = webFreedAware {
  jsImmediateWebNodeLookup(objectId, path)
}

private fun jsImmediateWebNodeLookup(objectId: Int, path: String): Int =
  js("globalThis.KanamaWebBridge.immediateNodeLookup(objectId, path)")

internal fun immediateWebPropertyObjectQuery(opcode: Int, objectId: Int, name: String): Int =
  webFreedAware {
    jsImmediateWebPropertyObjectQuery(opcode, objectId, name)
  }

private fun jsImmediateWebPropertyObjectQuery(opcode: Int, objectId: Int, name: String): Int =
  js("globalThis.KanamaWebBridge.immediatePropertyObjectQuery(opcode, objectId, name)")

internal fun immediateWebMoveAndCollide(opcode: Int, objectId: Int, packed: String): Int =
  webFreedAware {
    jsImmediateWebMoveAndCollide(opcode, objectId, packed)
  }

private fun jsImmediateWebMoveAndCollide(opcode: Int, objectId: Int, packed: String): Int =
  js("globalThis.KanamaWebBridge.immediateMoveAndCollide(opcode, objectId, packed)")

internal fun immediateWebStringQuery(opcode: Int, objectId: Int, value: String): String =
  webFreedAware {
    jsImmediateWebStringQuery(opcode, objectId, value)
  }

private fun jsImmediateWebStringQuery(opcode: Int, objectId: Int, value: String): String =
  js("globalThis.KanamaWebBridge.immediateStringQuery(opcode, objectId, value)")

internal fun immediateWebIndexedObjectLookup(opcode: Int, objectId: Int, index: Int): Int =
  webFreedAware {
    jsImmediateWebIndexedObjectLookup(opcode, objectId, index)
  }

private fun jsImmediateWebIndexedObjectLookup(opcode: Int, objectId: Int, index: Int): Int =
  js("globalThis.KanamaWebBridge.immediateIndexedObjectLookup(opcode, objectId, index)")

internal fun immediateWebIndexedVector3X(opcode: Int, objectId: Int, index: Int): Double =
  webFreedAware {
    jsImmediateWebIndexedVector3X(opcode, objectId, index)
  }

private fun jsImmediateWebIndexedVector3X(opcode: Int, objectId: Int, index: Int): Double =
  js("globalThis.KanamaWebBridge.immediateIndexedVector3X(opcode, objectId, index)")

internal fun immediateWebDisconnectBound(
  objectId: Int,
  signal: String,
  targetId: Int,
  method: String,
  boundValue: Int,
): Int = webFreedAware {
  jsImmediateWebDisconnectBound(objectId, signal, targetId, method, boundValue)
}

private fun jsImmediateWebDisconnectBound(
  objectId: Int,
  signal: String,
  targetId: Int,
  method: String,
  boundValue: Int,
): Int =
  js(
    "globalThis.KanamaWebBridge.immediateDisconnectBound(objectId, signal, targetId, method, boundValue)"
  )

internal fun immediateWebTweenMethod(
  opcode: Int,
  tweenId: Int,
  targetId: Int,
  method: String,
  fromValue: Double,
  toValue: Double,
  duration: Double,
): Int = webFreedAware {
  jsImmediateWebTweenMethod(opcode, tweenId, targetId, method, fromValue, toValue, duration)
}

private fun jsImmediateWebTweenMethod(
  opcode: Int,
  tweenId: Int,
  targetId: Int,
  method: String,
  fromValue: Double,
  toValue: Double,
  duration: Double,
): Int =
  js(
    "globalThis.KanamaWebBridge.immediateTweenMethod(opcode, tweenId, targetId, method, fromValue, toValue, duration)"
  )

internal fun immediateWebPackedSceneInstantiate(resourceId: Int, editState: Int): Int =
  webFreedAware {
    jsImmediateWebPackedSceneInstantiate(resourceId, editState)
  }

private fun jsImmediateWebPackedSceneInstantiate(resourceId: Int, editState: Int): Int =
  js("globalThis.KanamaWebBridge.immediatePackedSceneInstantiate(resourceId, editState)")

internal fun immediateWebNoArgsObject(opcode: Int, objectId: Int): Int = webFreedAware {
  jsImmediateWebNoArgsObject(opcode, objectId)
}

private fun jsImmediateWebNoArgsObject(opcode: Int, objectId: Int): Int =
  js("globalThis.KanamaWebBridge.immediateNoArgsObject(opcode, objectId)")

internal fun immediateWebTweenNoArgs(opcode: Int, objectId: Int): Int = webFreedAware {
  jsImmediateWebTweenNoArgs(opcode, objectId)
}

private fun jsImmediateWebTweenNoArgs(opcode: Int, objectId: Int): Int =
  js("globalThis.KanamaWebBridge.immediateTweenNoArgs(opcode, objectId)")

internal fun immediateWebTweenBoolRetObject(opcode: Int, objectId: Int, value: Boolean): Int =
  webFreedAware {
    jsImmediateWebTweenBoolRetObject(opcode, objectId, value)
  }

private fun jsImmediateWebTweenBoolRetObject(opcode: Int, objectId: Int, value: Boolean): Int =
  js("globalThis.KanamaWebBridge.immediateTweenBoolRetObject(opcode, objectId, value)")

internal fun immediateWebTweenLongRetObject(opcode: Int, objectId: Int, value: Int): Int =
  webFreedAware {
    jsImmediateWebTweenLongRetObject(opcode, objectId, value)
  }

private fun jsImmediateWebTweenLongRetObject(opcode: Int, objectId: Int, value: Int): Int =
  js("globalThis.KanamaWebBridge.immediateTweenLongRetObject(opcode, objectId, value)")

internal fun immediateWebTweenPropertyVector2(
  opcode: Int,
  tweenId: Int,
  targetId: Int,
  property: String,
  x: Double,
  y: Double,
  duration: Double,
): Int = webFreedAware {
  jsImmediateWebTweenPropertyVector2(opcode, tweenId, targetId, property, x, y, duration)
}

private fun jsImmediateWebTweenPropertyVector2(
  opcode: Int,
  tweenId: Int,
  targetId: Int,
  property: String,
  x: Double,
  y: Double,
  duration: Double,
): Int =
  js(
    "globalThis.KanamaWebBridge.immediateTweenPropertyVector2(opcode, tweenId, targetId, property, x, y, duration)"
  )

internal fun immediateWebTweenPropertyColor(
  opcode: Int,
  tweenId: Int,
  targetId: Int,
  property: String,
  r: Double,
  g: Double,
  b: Double,
  a: Double,
  duration: Double,
): Int = webFreedAware {
  jsImmediateWebTweenPropertyColor(opcode, tweenId, targetId, property, r, g, b, a, duration)
}

private fun jsImmediateWebTweenPropertyColor(
  opcode: Int,
  tweenId: Int,
  targetId: Int,
  property: String,
  r: Double,
  g: Double,
  b: Double,
  a: Double,
  duration: Double,
): Int =
  js(
    "globalThis.KanamaWebBridge.immediateTweenPropertyColor(opcode, tweenId, targetId, property, r, g, b, a, duration)"
  )

internal fun immediateWebSetCustomMouseCursor(
  ownerId: Int,
  resourceId: Int,
  shape: Int,
  hotspotX: Double,
  hotspotY: Double,
): Int = webFreedAware {
  jsImmediateWebSetCustomMouseCursor(ownerId, resourceId, shape, hotspotX, hotspotY)
}

private fun jsImmediateWebSetCustomMouseCursor(
  ownerId: Int,
  resourceId: Int,
  shape: Int,
  hotspotX: Double,
  hotspotY: Double,
): Int =
  js(
    "globalThis.KanamaWebBridge.immediateSetCustomMouseCursor(ownerId, resourceId, shape, hotspotX, hotspotY)"
  )

internal fun immediateWebConnect(
  objectId: Int,
  signal: String,
  targetId: Int,
  method: String,
  flags: Int,
): Int = webFreedAware { jsImmediateWebConnect(objectId, signal, targetId, method, flags) }

private fun jsImmediateWebConnect(
  objectId: Int,
  signal: String,
  targetId: Int,
  method: String,
  flags: Int,
): Int =
  js("globalThis.KanamaWebBridge.immediateConnect(objectId, signal, targetId, method, flags)")

internal fun immediateWebConnectBound(
  objectId: Int,
  signal: String,
  targetId: Int,
  method: String,
  boundValue: Int,
  flags: Int,
): Int = webFreedAware {
  jsImmediateWebConnectBound(objectId, signal, targetId, method, boundValue, flags)
}

private fun jsImmediateWebConnectBound(
  objectId: Int,
  signal: String,
  targetId: Int,
  method: String,
  boundValue: Int,
  flags: Int,
): Int =
  js(
    "globalThis.KanamaWebBridge.immediateConnectBound(objectId, signal, targetId, method, boundValue, flags)"
  )

internal fun immediateWebObjectQuery(opcode: Int, objectId: Int, value: String): Int =
  webFreedAware {
    jsImmediateWebObjectQuery(opcode, objectId, value)
  }

private fun jsImmediateWebObjectQuery(opcode: Int, objectId: Int, value: String): Int =
  js("globalThis.KanamaWebBridge.immediateObjectQuery(opcode, objectId, value)")

internal fun immediateWebSetProgressRatio(objectId: Int, ratio: Double): Int = webFreedAware {
  jsImmediateWebSetProgressRatio(objectId, ratio)
}

private fun jsImmediateWebSetProgressRatio(objectId: Int, ratio: Double): Int =
  js("globalThis.KanamaWebBridge.immediateSetProgressRatio(objectId, ratio)")

internal fun immediateWebSetProgressRatio3D(objectId: Int, ratio: Double): Int = webFreedAware {
  jsImmediateWebSetProgressRatio3D(objectId, ratio)
}

private fun jsImmediateWebSetProgressRatio3D(objectId: Int, ratio: Double): Int =
  js("globalThis.KanamaWebBridge.immediateDoubleQuery(107, objectId, ratio)")

/**
 * Generic Double-argument, Double-returning query channel (Noise.get_noise_1d, Curve.sample; x1000
 * integer result). Distinct from the confirm-only `immediateDoubleQuery` shape (107, 109): the
 * published integer IS the sample, so 0 is a valid answer rather than a missing confirmation.
 */
internal fun immediateWebDoubleQuery(opcode: Int, objectId: Int, value: Double): Int =
  webFreedAware {
    jsImmediateWebDoubleQuery(opcode, objectId, value)
  }

private fun jsImmediateWebDoubleQuery(opcode: Int, objectId: Int, value: Double): Int =
  js("globalThis.KanamaWebBridge.immediateDoubleRetDouble(opcode, objectId, value)")

internal fun immediateWebRotateY(objectId: Int, angle: Double): Int = webFreedAware {
  jsImmediateWebRotateY(objectId, angle)
}

private fun jsImmediateWebRotateY(objectId: Int, angle: Double): Int =
  js("globalThis.KanamaWebBridge.immediateDoubleQuery(109, objectId, angle)")

internal fun immediateWebSlideCollision(objectId: Int, index: Int): Int = webFreedAware {
  jsImmediateWebSlideCollision(objectId, index)
}

private fun jsImmediateWebSlideCollision(objectId: Int, index: Int): Int =
  js("globalThis.KanamaWebBridge.immediateSlideCollision(objectId, index)")

internal fun immediateWebNodeChild(objectId: Int, index: Int): Int = webFreedAware {
  jsImmediateWebNodeChild(objectId, index)
}

private fun jsImmediateWebNodeChild(objectId: Int, index: Int): Int =
  js("globalThis.KanamaWebBridge.immediateNodeChild(objectId, index)")

internal fun immediateWebTweenObjectRetObject(opcode: Int, objectId: Int, valueId: Int): Int =
  webFreedAware {
    jsImmediateWebTweenObjectRetObject(opcode, objectId, valueId)
  }

private fun jsImmediateWebTweenObjectRetObject(opcode: Int, objectId: Int, valueId: Int): Int =
  js("globalThis.KanamaWebBridge.immediateTweenObjectRetObject(opcode, objectId, valueId)")

internal fun immediateWebTweenPropertyVector3(
  opcode: Int,
  tweenId: Int,
  targetId: Int,
  property: String,
  x: Double,
  y: Double,
  z: Double,
  duration: Double,
): Int = webFreedAware {
  jsImmediateWebTweenPropertyVector3(opcode, tweenId, targetId, property, x, y, z, duration)
}

private fun jsImmediateWebTweenPropertyVector3(
  opcode: Int,
  tweenId: Int,
  targetId: Int,
  property: String,
  x: Double,
  y: Double,
  z: Double,
  duration: Double,
): Int =
  js(
    "globalThis.KanamaWebBridge.immediateTweenPropertyVector3(opcode, tweenId, targetId, property, x, y, z, duration)"
  )

internal fun immediateWebColorRetHandle(
  opcode: Int,
  objectId: Int,
  r: Double,
  g: Double,
  b: Double,
  alpha: Double,
): Int = webFreedAware { jsImmediateWebColorRetHandle(opcode, objectId, r, g, b, alpha) }

private fun jsImmediateWebColorRetHandle(
  opcode: Int,
  objectId: Int,
  r: Double,
  g: Double,
  b: Double,
  alpha: Double,
): Int = js("globalThis.KanamaWebBridge.immediateColorRetHandle(opcode, objectId, r, g, b, alpha)")

internal fun immediateWebTweenPropertyDouble(
  opcode: Int,
  tweenId: Int,
  targetId: Int,
  property: String,
  value: Double,
  duration: Double,
): Int = webFreedAware {
  jsImmediateWebTweenPropertyDouble(opcode, tweenId, targetId, property, value, duration)
}

private fun jsImmediateWebTweenPropertyDouble(
  opcode: Int,
  tweenId: Int,
  targetId: Int,
  property: String,
  value: Double,
  duration: Double,
): Int =
  js(
    "globalThis.KanamaWebBridge.immediateTweenPropertyDouble(opcode, tweenId, targetId, property, value, duration)"
  )

internal fun immediateWebTweenCallback(
  opcode: Int,
  tweenId: Int,
  targetId: Int,
  method: String,
): Int = webFreedAware { jsImmediateWebTweenCallback(opcode, tweenId, targetId, method) }

private fun jsImmediateWebTweenCallback(
  opcode: Int,
  tweenId: Int,
  targetId: Int,
  method: String,
): Int = js("globalThis.KanamaWebBridge.immediateTweenCallback(opcode, tweenId, targetId, method)")

internal fun immediateWebVector2ArgVector3X(
  opcode: Int,
  objectId: Int,
  x: Double,
  y: Double,
): Double = webFreedAware { jsImmediateWebVector2ArgVector3X(opcode, objectId, x, y) }

private fun jsImmediateWebVector2ArgVector3X(
  opcode: Int,
  objectId: Int,
  x: Double,
  y: Double,
): Double = js("globalThis.KanamaWebBridge.immediateVector2ArgVector3X(opcode, objectId, x, y)")

internal fun immediateWebNoArgsVector3X(opcode: Int, objectId: Int): Double = webFreedAware {
  jsImmediateWebNoArgsVector3X(opcode, objectId)
}

private fun jsImmediateWebNoArgsVector3X(opcode: Int, objectId: Int): Double =
  js("globalThis.KanamaWebBridge.immediateNoArgsVector3X(opcode, objectId)")

internal fun immediateWebNoArgsVector3Y(): Double = webFreedAware { jsImmediateWebNoArgsVector3Y() }

private fun jsImmediateWebNoArgsVector3Y(): Double =
  js("globalThis.KanamaWebBridge.immediateNoArgsVector3Y()")

internal fun immediateWebNoArgsVector3Z(): Double = webFreedAware { jsImmediateWebNoArgsVector3Z() }

private fun jsImmediateWebNoArgsVector3Z(): Double =
  js("globalThis.KanamaWebBridge.immediateNoArgsVector3Z()")

internal fun immediateWebNoArgsVector2X(opcode: Int, objectId: Int): Double = webFreedAware {
  jsImmediateWebNoArgsVector2X(opcode, objectId)
}

private fun jsImmediateWebNoArgsVector2X(opcode: Int, objectId: Int): Double =
  js("globalThis.KanamaWebBridge.immediateNoArgsVector2X(opcode, objectId)")

internal fun immediateWebNoArgsVector2Y(): Double = webFreedAware { jsImmediateWebNoArgsVector2Y() }

private fun jsImmediateWebNoArgsVector2Y(): Double =
  js("globalThis.KanamaWebBridge.immediateNoArgsVector2Y()")

internal fun immediateWebEmitSignalVector2i(objectId: Int, name: String, x: Int, y: Int): Int =
  webFreedAware {
    jsImmediateWebEmitSignalVector2i(objectId, name, x, y)
  }

private fun jsImmediateWebEmitSignalVector2i(objectId: Int, name: String, x: Int, y: Int): Int =
  js("globalThis.KanamaWebBridge.immediateEmitSignalVector2i(objectId, name, x, y)")

/**
 * Task 138 item 3: an immediate crossing whose object the engine freed under a script (a child
 * freed with its parent, a timer, a body) used to fail in the bridge with "callback did not publish
 * a result". The bridge now says the instance was freed ([FREED_INSTANCE_MESSAGE]); this turns that
 * into the `IllegalStateException` desktop throws for a freed instance, so a script can catch it
 * and it reads the same on every platform.
 */
private inline fun <T> webFreedAware(block: () -> T): T =
  try {
    block()
  } catch (error: JsException) {
    val message = error.message.orEmpty()
    val at = message.indexOf(FREED_INSTANCE_MESSAGE)
    if (at < 0) throw error
    throw IllegalStateException(message.substring(at), error)
  }

private const val FREED_INSTANCE_MESSAGE = "Invalid access to previously freed instance"

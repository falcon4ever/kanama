@file:OptIn(ExperimentalWasmJsInterop::class)

package net.multigesture.kanama.web

import kotlin.js.ExperimentalWasmJsInterop

/**
 * Hand-written Web transport primitives (Task 60a, "Option A").
 *
 * These `js(...)` externs are the codec/transport boundary between the Kanama Wasm module and the
 * `KanamaWebBridge` JavaScript seam. They are hand-written because the JS-interop bodies are not
 * derivable from the platform-neutral model; the generated dispatch in
 * `WebCommonGodotBackend.generated.kt` calls them. Admitting a new call family is a regenerated
 * dispatch diff plus, where a new crossing shape appears, a transport extern added here.
 */
internal fun immediateWebChildCount(objectId: Int, includeInternal: Boolean): Int =
  js("globalThis.KanamaWebBridge.immediateChildCount(objectId, includeInternal)")

internal fun immediateWebResourceLoad(path: String, typeHint: String, cacheMode: Int): Int =
  js("globalThis.KanamaWebBridge.immediateResourceLoad(path, typeHint, cacheMode)")

internal fun immediateWebEmitSignal(objectId: Int, name: String, value: Int): Int =
  js("globalThis.KanamaWebBridge.immediateEmitSignal(objectId, name, value)")

internal fun immediateWebEmitSignalNoArgs(objectId: Int, name: String): Int =
  js("globalThis.KanamaWebBridge.immediateEmitSignalNoArgs(objectId, name)")

internal fun immediateWebConstructObject(className: String): Int =
  js("globalThis.KanamaWebBridge.immediateConstructObject(className)")

internal fun immediateWebNodeLookup(objectId: Int, path: String): Int =
  js("globalThis.KanamaWebBridge.immediateNodeLookup(objectId, path)")

internal fun immediateWebPropertyObjectQuery(opcode: Int, objectId: Int, name: String): Int =
  js("globalThis.KanamaWebBridge.immediatePropertyObjectQuery(opcode, objectId, name)")

internal fun immediateWebMoveAndCollide(opcode: Int, objectId: Int, packed: String): Int =
  js("globalThis.KanamaWebBridge.immediateMoveAndCollide(opcode, objectId, packed)")

internal fun immediateWebStringQuery(opcode: Int, objectId: Int, value: String): String =
  js("globalThis.KanamaWebBridge.immediateStringQuery(opcode, objectId, value)")

internal fun immediateWebIndexedObjectLookup(opcode: Int, objectId: Int, index: Int): Int =
  js("globalThis.KanamaWebBridge.immediateIndexedObjectLookup(opcode, objectId, index)")

internal fun immediateWebIndexedVector3X(opcode: Int, objectId: Int, index: Int): Double =
  js("globalThis.KanamaWebBridge.immediateIndexedVector3X(opcode, objectId, index)")

internal fun immediateWebDisconnectBound(
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
): Int =
  js(
    "globalThis.KanamaWebBridge.immediateTweenMethod(opcode, tweenId, targetId, method, fromValue, toValue, duration)"
  )

internal fun immediateWebPackedSceneInstantiate(resourceId: Int, editState: Int): Int =
  js("globalThis.KanamaWebBridge.immediatePackedSceneInstantiate(resourceId, editState)")

internal fun immediateWebNoArgsObject(opcode: Int, objectId: Int): Int =
  js("globalThis.KanamaWebBridge.immediateNoArgsObject(opcode, objectId)")

internal fun immediateWebTweenNoArgs(opcode: Int, objectId: Int): Int =
  js("globalThis.KanamaWebBridge.immediateTweenNoArgs(opcode, objectId)")

internal fun immediateWebTweenBoolRetObject(opcode: Int, objectId: Int, value: Boolean): Int =
  js("globalThis.KanamaWebBridge.immediateTweenBoolRetObject(opcode, objectId, value)")

internal fun immediateWebTweenLongRetObject(opcode: Int, objectId: Int, value: Int): Int =
  js("globalThis.KanamaWebBridge.immediateTweenLongRetObject(opcode, objectId, value)")

internal fun immediateWebTweenPropertyVector2(
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
): Int =
  js("globalThis.KanamaWebBridge.immediateConnect(objectId, signal, targetId, method, flags)")

internal fun immediateWebConnectBound(
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
  js("globalThis.KanamaWebBridge.immediateObjectQuery(opcode, objectId, value)")

internal fun immediateWebSetProgressRatio(objectId: Int, ratio: Double): Int =
  js("globalThis.KanamaWebBridge.immediateSetProgressRatio(objectId, ratio)")

internal fun immediateWebSetProgressRatio3D(objectId: Int, ratio: Double): Int =
  js("globalThis.KanamaWebBridge.immediateDoubleQuery(107, objectId, ratio)")

/** Generic Double-argument query channel (Noise.get_noise_1d; x1000 integer result). */
internal fun immediateWebDoubleQuery(opcode: Int, objectId: Int, value: Double): Int =
  js("globalThis.KanamaWebBridge.immediateDoubleQuery(opcode, objectId, value)")

internal fun immediateWebRotateY(objectId: Int, angle: Double): Int =
  js("globalThis.KanamaWebBridge.immediateDoubleQuery(109, objectId, angle)")

internal fun immediateWebSlideCollision(objectId: Int, index: Int): Int =
  js("globalThis.KanamaWebBridge.immediateSlideCollision(objectId, index)")

internal fun immediateWebNodeChild(objectId: Int, index: Int): Int =
  js("globalThis.KanamaWebBridge.immediateNodeChild(objectId, index)")

internal fun immediateWebTweenObjectRetObject(opcode: Int, objectId: Int, valueId: Int): Int =
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
): Int =
  js(
    "globalThis.KanamaWebBridge.immediateTweenPropertyVector3(opcode, tweenId, targetId, property, x, y, z, duration)"
  )

internal fun immediateWebTweenCallback(
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
): Double = js("globalThis.KanamaWebBridge.immediateVector2ArgVector3X(opcode, objectId, x, y)")

internal fun immediateWebNoArgsVector3X(opcode: Int, objectId: Int): Double =
  js("globalThis.KanamaWebBridge.immediateNoArgsVector3X(opcode, objectId)")

internal fun immediateWebNoArgsVector3Y(): Double =
  js("globalThis.KanamaWebBridge.immediateNoArgsVector3Y()")

internal fun immediateWebNoArgsVector3Z(): Double =
  js("globalThis.KanamaWebBridge.immediateNoArgsVector3Z()")

internal fun immediateWebNoArgsVector2X(opcode: Int, objectId: Int): Double =
  js("globalThis.KanamaWebBridge.immediateNoArgsVector2X(opcode, objectId)")

internal fun immediateWebNoArgsVector2Y(): Double =
  js("globalThis.KanamaWebBridge.immediateNoArgsVector2Y()")

internal fun immediateWebEmitSignalVector2i(objectId: Int, name: String, x: Int, y: Int): Int =
  js("globalThis.KanamaWebBridge.immediateEmitSignalVector2i(objectId, name, x, y)")

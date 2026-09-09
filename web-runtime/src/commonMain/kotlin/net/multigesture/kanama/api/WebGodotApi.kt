package net.multigesture.kanama.api

import net.multigesture.kanama.web.KanamaWebScript

/**
 * Hand-written base of the Web API. The Godot classes themselves are generated from the Web call
 * contract into `generated/` (`scripts/generate_web_wrappers.py`); this file keeps only what is not
 * a Godot class: the script base, the coverage markers, and the platform seams the generated
 * lifetimes call.
 */

/**
 * Deprecated, no longer applied to any Web API (task 97); kept so an existing
 * `@OptIn(ManualGodotLifetimeApi::class)` in ported code still compiles. `close()` is the
 * documented contract — see `docs/game-dev/godot-api.md` "Resource Ownership".
 */
@Deprecated(
  "No longer required; close() is the documented contract (docs/game-dev/godot-api.md#resource-ownership)"
)
@RequiresOptIn(
  level = RequiresOptIn.Level.WARNING,
  message = "This API exposes manual Godot resource lifetime management.",
)
@Retention(AnnotationRetention.BINARY)
annotation class ManualGodotLifetimeApi

abstract class KanamaScript<T : GodotObject>(
  val godotObject: GodotHandle,
  wrapper: (GodotHandle) -> T,
) : KanamaWebScript(godotObject) {
  protected val self: T = wrapper(godotObject)

  inline fun <R> selfAs(ctor: (GodotHandle) -> R): R = ctor(godotObject)
}

/**
 * Coverage markers. Each gameplay operation that has not entered the typed Web call manifest fails
 * explicitly and is harvested into the generated Web coverage backlog
 * (`scripts/generate_web_gameplay_coverage.py`); none of them silently returns a placeholder.
 */
@PublishedApi
internal fun unsupportedWebGameplayCall(signature: String): Nothing =
  error("Kanama Web gameplay call is not implemented: $signature (Task 57e backlog)")

@PublishedApi
internal fun unsupportedWebGameplayFamily(signature: String): Nothing =
  error("Kanama Web gameplay call family is not implemented: $signature")

/**
 * Marks a call satisfied by the GENERIC fallback tier (task 76: `callv` behind opcodes 1001/1002)
 * rather than a typed opcode family. Coverage counts it as covered, but the generated report lists
 * it in its own generic (slow-path) bucket so the typed fast-path set can grow from real usage.
 * Place one marker per Class.method at the wrapper (or fixture) call site that routes through
 * [net.multigesture.kanama.web.WebExperimentalGenericCall].
 */
@PublishedApi
internal fun genericWebGameplayFallback(signature: String) {
  require('.' in signature) { "Generic fallback marker must name Class.method: $signature" }
}

// Platform seams the generated lifetimes and liveness helpers call; the wasmJs actuals live next to
// the bridge interop.

internal expect fun releaseWebResource(resourceHandle: Int)

internal expect fun instantiateWebScript(className: String): Int

internal expect fun releaseWebScriptResource(handle: Int)

internal expect fun releaseWebConstructedObject(handle: Int)

/** Release a tracked OBJECT-kind handle (a duplicated resource the script owns). */
internal expect fun releaseWebTrackedObject(handle: Int)

internal expect fun releaseWebCollision(collisionHandle: Int)

internal expect fun isWebBrowserHandleLive(handle: Int): Boolean

/**
 * Immediate generic call (task-76 `callv` tier) expecting a String result. The wasmJs actual
 * routes through `WebExperimentalGenericCall.callImmediate` — commonMain wrappers cannot see the
 * wasmJs source set directly, and this keeps the generic transport's single extern as the only
 * crossing. Wrapper call sites carry a [genericWebGameplayFallback] marker so the coverage
 * report's slow-path bucket stays honest.
 */
internal expect fun webGenericImmediateStringCall(target: GodotObject, method: String): String

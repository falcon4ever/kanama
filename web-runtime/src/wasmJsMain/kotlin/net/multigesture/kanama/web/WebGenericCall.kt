@file:OptIn(ExperimentalJsExport::class, ExperimentalWasmJsInterop::class)

package net.multigesture.kanama.web

import kotlin.js.ExperimentalJsExport
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsExport
import net.multigesture.kanama.api.GodotEnumValue
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.releaseWebConstructedObject
import net.multigesture.kanama.api.releaseWebResource
import net.multigesture.kanama.api.releaseWebTrackedObject

/**
 * Task 76: "call any Godot method by name" fallback for the Web backend.
 *
 * The typed opcode families stay the fast path; this is the generic reflective tier (`callv` behind
 * one opcode pair) so an unadmitted method no longer needs a per-family hand-wired opcode. It is
 * deliberately NOT part of the shared cross-platform API surface — desktop/Android/iOS already have
 * their generic primitive (ptrcall). A production wrapper that routes a call through this tier must
 * carry a `genericWebGameplayFallback("Class.method")` marker so the coverage report accounts for
 * it in the generic (slow-path) bucket.
 *
 * Argument encoding: each argument crosses as `typeTag:value`, arguments joined with the unit
 * separator (the established packed-string transport). Supported tags: `n` (null), `b` (bool), `i`
 * (int, 64-bit; a typed Godot enum / bitfield -- a `GodotEnumValue` -- crosses as its number), `d`
 * (double), `s` (string; `%` and separator payload bytes are percent-escaped), `h` (an
 * already-tracked object handle, resolved through `_kanama_object_handles` in the GDScript arm) and
 * `v` (task 134 D1: a value type or NodePath as `<Variant.Type>:<payload>`, see `WebPackedValues`).
 *
 * Return encoding (immediate calls): `typeTag<US>payload...`. Object returns resolve to an
 * already-tracked handle first (`_kanama_ensure_created` for script-backed objects, `is_same` scan
 * otherwise); an untracked engine object is classified at runtime (Node / Resource / plain Object)
 * and a tracked handle of that kind is MINTED, owned by the receiver's proxy per the task-61 "close
 * what you create" contract — release it with [WebGenericCallResult.close] or let the owner
 * script's teardown drain it.
 */
object WebExperimentalGenericCall {
  internal const val UNIT_SEPARATOR = '\u001f' // matches the packed-string transport

  /**
   * Queues a generic void call (fire-and-forget mutation) on the command buffer; it applies with
   * the next flush, in order with the queued typed mutations around it.
   */
  fun queueVoidCall(target: GodotObject, method: String, args: List<Any?> = emptyList()) {
    commands.appendGenericVoidCall(target.handle.value, method, encodeArgs(args))
  }

  /**
   * Executes a generic call immediately and returns its variant-tagged result. Flushes the queued
   * command buffer first, like every typed immediate family, so queued mutations are visible to the
   * call.
   */
  fun callImmediate(
    target: GodotObject,
    method: String,
    args: List<Any?> = emptyList(),
  ): WebGenericCallResult {
    commands.flush()
    val packed = buildString {
      append(method)
      for (arg in args) {
        append(UNIT_SEPARATOR)
        append(encodeArg(arg))
      }
    }
    val raw = webFreedAware {
      immediateWebStringQuery(
        WebCommandBuffer.OPCODE_GENERIC_IMMEDIATE_CALL,
        target.handle.value,
        packed,
      )
    }
    return WebGenericCallResult.parse(raw)
  }

  /** Wall-clock milliseconds for harness measurements (performance.now under the hood). */
  fun nowMillis(): Double = webNowMillis()

  /** Publishes the smoke fixture's probe report for the browser driver to read. */
  fun publishProbeReport(report: String) {
    webGenericCallProbeReport = report
  }

  internal fun escapeStringPayload(value: String): String =
    value.replace("%", "%25").replace(UNIT_SEPARATOR.toString(), "%1F")

  internal fun unescapeStringPayload(value: String): String =
    value.replace("%1F", UNIT_SEPARATOR.toString()).replace("%25", "%")

  private fun encodeArgs(args: List<Any?>): String =
    args.joinToString(UNIT_SEPARATOR.toString()) { encodeArg(it) }

  private fun encodeArg(arg: Any?): String =
    when (arg) {
      null -> "n:"
      is Boolean -> if (arg) "b:true" else "b:false"
      is Int -> "i:$arg"
      // The text carries a full 64-bit int (GDScript's `int()` reads it exactly); task 134 D1
      // lifted the old int32 limit, which only the typed int arms have.
      is Long -> "i:$arg"
      // Decimals as the proxy's `_kanama_web_float` reads them (NaN and the infinities too).
      is Double -> "d:${WebPackedFloats.encode(arg)}"
      is Float -> "d:${WebPackedFloats.encode(arg.toDouble())}"
      is String -> "s:${escapeStringPayload(arg)}"
      is GodotObject -> "h:${arg.handle.value}"
      // Task 128: a typed Godot enum / bitfield crosses as the INT it stands for.
      is GodotEnumValue -> encodeArg(arg.value)
      // Task 134 D1: a value type or NodePath as a Variant (`v:<Variant.Type>:<payload>`).
      else -> "v:${WebPackedValues.encodeVariant(arg)}"
    }
}

/** Variant-tagged result of an immediate generic call. See [WebExperimentalGenericCall]. */
class WebGenericCallResult internal constructor(val tag: String, val payload: List<String>) {
  val isNull: Boolean
    get() = tag == "n"

  fun asBoolean(): Boolean {
    check(tag == "b") { "Generic call returned tag '$tag', not a bool" }
    return payload[0] == "true"
  }

  fun asLong(): Long {
    check(tag == "i") { "Generic call returned tag '$tag', not an int" }
    return payload[0].toLong()
  }

  fun asDouble(): Double {
    check(tag == "f") { "Generic call returned tag '$tag', not a float" }
    return WebPackedFloats.decode(payload[0])
  }

  fun asString(): String {
    check(tag == "s") { "Generic call returned tag '$tag', not a string" }
    return WebExperimentalGenericCall.unescapeStringPayload(payload[0])
  }

  /**
   * A value-type result (task 134 D1): `Vector2`/`Vector3` (their older `v2`/`v3` tags) or any
   * other value type (`v`, a `<Variant.Type>:<payload>` Variant).
   */
  fun asValue(): Any =
    when (tag) {
      "v2" ->
        net.multigesture.kanama.types.Vector2(
          WebPackedFloats.decode(payload[0]),
          WebPackedFloats.decode(payload[1]),
        )
      "v3" ->
        net.multigesture.kanama.types.Vector3(
          WebPackedFloats.decode(payload[0]),
          WebPackedFloats.decode(payload[1]),
          WebPackedFloats.decode(payload[2]),
        )
      "v" ->
        checkNotNull(WebPackedValues.decodeVariant(payload[0])) {
          "Generic call returned a null value"
        }
      else -> error("Generic call returned tag '$tag', not a value type")
    }

  /** The tracked handle an object return resolved (or minted) to. */
  fun asObjectHandle(): Int {
    check(tag == "o") { "Generic call returned tag '$tag', not an object" }
    return payload[0].toInt()
  }

  /**
   * How the object return resolved: `script` (a Kanama-scripted object's own handle), `tracked` (an
   * existing handle found by the `is_same` scan), or the minted kinds `node` / `resource` /
   * `object`. Null for non-object results.
   */
  val objectKind: String?
    get() = if (tag == "o" && payload.size > 1) payload[1] else null

  /** True when this call MINTED the returned handle (so this caller owns it). */
  val isMintedHandle: Boolean
    get() = objectKind == "node" || objectKind == "resource" || objectKind == "object"

  /**
   * Releases a MINTED handle through its kind-specific release path (task-61 "close what you
   * create"): the proxy erases its dictionary reference and the browser slot retires. Handles that
   * resolved to `script`/`tracked` are not owned by this caller and refuse to close.
   */
  fun close() {
    val handle = asObjectHandle()
    when (objectKind) {
      "node" -> releaseWebConstructedObject(handle)
      "resource" -> releaseWebResource(handle)
      "object" -> releaseWebTrackedObject(handle)
      else ->
        error(
          "Generic call handle kind '$objectKind' is not owned by this caller (only minted handles close)"
        )
    }
  }

  companion object {
    internal fun parse(raw: String): WebGenericCallResult {
      val parts = raw.split(WebExperimentalGenericCall.UNIT_SEPARATOR)
      return WebGenericCallResult(parts[0], parts.drop(1))
    }
  }
}

internal var webGenericCallProbeReport: String = ""

@JsExport fun kanamaWebGenericCallProbeReport(): String = webGenericCallProbeReport

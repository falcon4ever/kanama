@file:OptIn(ExperimentalJsExport::class, ExperimentalWasmJsInterop::class)

package net.multigesture.kanama.web

import kotlin.js.ExperimentalJsExport
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsExport
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
 * (int), `d` (double), `s` (string; `%` and separator payload bytes are percent-escaped), and `h`
 * (an already-tracked object handle, resolved through `_kanama_object_handles` in the GDScript
 * arm).
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
    val raw =
      immediateWebStringQuery(
        WebCommandBuffer.OPCODE_GENERIC_IMMEDIATE_CALL,
        target.handle.value,
        packed,
      )
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
      is Long -> {
        require(arg in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong()) {
          "Kanama Web generic call int argument must fit Godot's int32 ABI"
        }
        "i:$arg"
      }
      is Double -> "d:$arg"
      is Float -> "d:${arg.toDouble()}"
      is String -> "s:${escapeStringPayload(arg)}"
      is GodotObject -> "h:${arg.handle.value}"
      else -> error("Kanama Web generic call cannot encode argument type ${arg::class.simpleName}")
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
    return payload[0].toDouble()
  }

  fun asString(): String {
    check(tag == "s") { "Generic call returned tag '$tag', not a string" }
    return WebExperimentalGenericCall.unescapeStringPayload(payload[0])
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

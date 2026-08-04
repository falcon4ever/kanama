@file:OptIn(ExperimentalJsExport::class, ExperimentalWasmJsInterop::class)

package net.multigesture.kanama.web

import kotlin.js.ExperimentalJsExport
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsExport
import net.multigesture.kanama.api.GodotObject

/**
 * EXPERIMENTAL — Task 76 spike: "call any Godot method by name" fallback for the Web backend.
 *
 * The typed opcode families stay the fast path; this is the feasibility probe for a generic
 * reflective tier (`callv` behind one opcode pair) so an unadmitted method no longer needs a
 * per-family hand-wired opcode. It is deliberately NOT part of the shared cross-platform API
 * surface — desktop/Android/iOS already have their generic primitive (ptrcall) — and it must not be
 * used by production gameplay code until the design questions recorded in the task-76 PR
 * (handle-minting policy, protocol bump, coverage-gate slow-path bucket) are settled.
 *
 * Argument encoding: each argument crosses as `typeTag:value`, arguments joined with the unit
 * separator (the established packed-string transport). Supported tags: `n` (null), `b` (bool), `i`
 * (int), `d` (double), `s` (string, must not contain the separator), and `h` (an already-tracked
 * object handle, resolved through `_kanama_object_handles` in the GDScript arm).
 *
 * Return encoding (immediate calls): `typeTag<US>payload...`. Object returns follow the
 * CONSERVATIVE policy copied from the space-state ray-query arm: resolve to an already-tracked
 * handle (`_kanama_ensure_created` for script-backed objects, `is_same` scan otherwise); an
 * untracked engine object reports tag `object-untracked` with handle 0. The spike never mints new
 * handles.
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

  /** Wall-clock milliseconds for spike measurements (performance.now under the hood). */
  fun nowMillis(): Double = webNowMillis()

  /** Publishes the smoke fixture's probe report for the browser driver to read. */
  fun publishProbeReport(report: String) {
    webGenericCallProbeReport = report
  }

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
      is String -> {
        require(UNIT_SEPARATOR !in arg) {
          "Kanama Web generic call string arguments must not contain the unit separator"
        }
        "s:$arg"
      }
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
    return payload[0]
  }

  /**
   * The already-tracked handle an object return resolved to, or 0 when the engine object is
   * untracked (tag `object-untracked` — the spike's conservative no-minting policy).
   */
  fun asObjectHandle(): Int {
    check(tag == "o" || tag == "object-untracked") {
      "Generic call returned tag '$tag', not an object"
    }
    return payload[0].toInt()
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

package net.multigesture.kanama.binding.runtime

import net.multigesture.kanama.web.WebPackedFloats
import net.multigesture.kanama.web.WebPackedValues

/**
 * The bridge crossing of a builtin call (task 134 D1); the wasmJs actual is
 * `KanamaWebBridge.immediateBuiltinCall`, answered by the proxy's `_kanama_web_builtin_call`.
 */
internal expect fun webBuiltinTransport(packed: String): String

private const val US = '\u001F'

/**
 * Runs [method] in the engine: `<variant type>␟<method>␟<static 0/1>␟<base>␟<arg 1>…`, each value a
 * `<Variant.Type>:<payload>` ([WebPackedValues.encodeVariant]'s format, components in Godot's
 * memory layout as the frame holds them), and reads the returned Variant (same format) into the
 * frame's return slot. A proxy-side failure (`E:<message>`) throws instead of returning zeros.
 */
internal fun webRemoteBuiltinCall(
  frame: BuiltinFrame,
  method: BuiltinMethod,
  argc: Int,
  static: Boolean,
) {
  val types = method.argumentTypes
  require(argc <= types.size) { "builtin ${method.name}: $argc arguments, ${types.size} declared" }
  val packed = buildString {
    append(method.variantType)
      .append(US)
      .append(method.name)
      .append(US)
      .append(if (static) '1' else '0')
    append(US)
    if (!static) append(encodeSlot(frame, 0, method.variantType))
    for (i in 1..argc) append(US).append(encodeSlot(frame, i, types[i - 1]))
  }
  val result = webBuiltinTransport(packed)
  if (result.startsWith("E:")) {
    error(
      "Godot builtin ${method.name} (Variant type ${method.variantType}) failed on Web: ${result.substring(2)}"
    )
  }
  val type = WebPackedValues.variantType(result)
  val payload = WebPackedValues.variantPayload(result)
  frame.retType = type
  when (type) {
    WebPackedValues.TYPE_NIL -> Unit
    WebPackedValues.TYPE_BOOL -> frame.ret[0] = if (payload == "1") 1.0 else 0.0
    WebPackedValues.TYPE_INT -> frame.retLongValue = payload.toLong()
    WebPackedValues.TYPE_FLOAT -> frame.ret[0] = WebPackedFloats.decode(payload)
    else -> {
      check(WebPackedValues.isValueType(type)) {
        "Godot builtin ${method.name} returned Variant type $type, which the Web frame cannot hold"
      }
      WebPackedValues.decodeComponents(payload).copyInto(frame.ret)
    }
  }
}

private fun encodeSlot(frame: BuiltinFrame, slot: Int, type: Int): String {
  val c = frame.slots[slot]
  val payload =
    when (type) {
      WebPackedValues.TYPE_BOOL -> if (c[0] != 0.0) "1" else "0"
      WebPackedValues.TYPE_INT -> frame.longs[slot].toString()
      WebPackedValues.TYPE_FLOAT -> WebPackedFloats.encode(c[0])
      WebPackedValues.TYPE_STRING,
      WebPackedValues.TYPE_STRING_NAME,
      WebPackedValues.TYPE_NODE_PATH ->
        WebPackedValues.escapeText(
          checkNotNull(frame.strings[slot]) { "builtin argument $slot: no String" }
        )
      else -> {
        check(WebPackedValues.isValueType(type)) {
          "builtin argument $slot: Variant type $type has no Web encoding"
        }
        WebPackedValues.encodeComponents(type, c, WebPackedValues.componentCount(type))
      }
    }
  return "$type:$payload"
}

package net.multigesture.kanama.binding.runtime

import net.multigesture.kanama.web.WebPackedFloats
import net.multigesture.kanama.web.WebPackedValues

/**
 * The bridge crossing of a builtin call (task 134 D1); the wasmJs actual is
 * `KanamaWebBridge.immediateBuiltinCall`, answered by the proxy's `_kanama_web_builtin_call`.
 */
internal expect fun webBuiltinTransport(packed: String): String

/** A test seam: when set, builtin calls go here instead of the bridge (Node has no bridge). */
internal var webBuiltinTransportForTests: ((String) -> String)? = null

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
  val result = webBuiltinTransportForTests?.invoke(packed) ?: webBuiltinTransport(packed)
  if (result.startsWith("E:")) {
    error(
      "Godot builtin ${method.name} (Variant type ${method.variantType}) failed on Web: ${result.substring(2)}"
    )
  }
  val type = WebPackedValues.variantType(result)
  val payload = WebPackedValues.variantPayload(result)
  // Task 134 D1 review S2: `callv` answers a wrong arity or argument type with an error print and
  // null, and a Variant of an unexpected type would be read as zeros: both fail loud here.
  val expected = method.returnType
  if (expected != WebBuiltinSignatures.VARIANT_RETURN && type != expected) {
    error(
      "Godot builtin ${method.name} (Variant type ${method.variantType}) returned Variant type $type " +
        "on Web, expected $expected (a wrong argument count or type makes the engine answer nil)"
    )
  }
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
      WebPackedValues.decodeComponents(type, payload).copyInto(frame.ret)
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

/**
 * The Web half of the boxed builtin-method call (task 134 D2; native: `UtilityCalls.callMethod` in
 * `src/commonMain/.../binding/runtime/UtilityCalls.expect.kt`), which the shared String, NodePath
 * and PackedByteArray methods (`net.multigesture.kanama.builtins`, `types/NodePath.kt`) call. Web
 * is a separate project, so this is a plain object with the same shape. One crossing carries the
 * base and the arguments as encoded Variants ([WebPackedValues.encodeVariant]: scalars, String,
 * NodePath, value types, ByteArray) and returns one; a type the Web channel does not carry (a Map
 * or List argument, an Array or Dictionary return) fails loud.
 */
internal object UtilityCalls {
  fun callMethod(
    method: BuiltinMethod,
    baseType: Int,
    base: Any?,
    argTypes: IntArray,
    args: Array<out Any?>,
    retType: Int,
  ): Any? {
    val packed = buildString {
      append(method.variantType)
        .append(US)
        .append(method.name)
        .append(US)
        .append(if (base == null) '1' else '0')
      append(US)
      if (base != null) append(WebPackedValues.encodeVariant(base))
      for (arg in args) append(US).append(WebPackedValues.encodeVariant(arg))
    }
    val result = webBuiltinTransportForTests?.invoke(packed) ?: webBuiltinTransport(packed)
    if (result.startsWith("E:")) {
      error(
        "Godot builtin ${method.name} (Variant type ${method.variantType}) failed on Web: ${result.substring(2)}"
      )
    }
    if (retType < 0) return null
    val type = WebPackedValues.variantType(result)
    check(retType == 0 || type == retType) {
      "Godot builtin ${method.name} (Variant type ${method.variantType}) returned Variant type $type " +
        "on Web, expected $retType (a wrong argument count or type makes the engine answer nil)"
    }
    return WebPackedValues.decodeVariant(result)
  }
}

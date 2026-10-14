package net.multigesture.kanama.binding.runtime

import net.multigesture.kanama.api.WebLocalUtilities
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
  storeFrameResult(
    frame,
    result,
    "builtin ${method.name} (Variant type ${method.variantType})",
    method.returnType,
  )
}

/**
 * Task 129 E: one utility function (`GD.sin`, `GD.randf`, ...) with float / int / bool arguments
 * from the frame. It rides the builtin opcode with static flag `2` and no base:
 * `0<US><name><US>2<US><US><args...>`; the proxy runs it by name through an `Expression`.
 */
internal fun webRemoteUtilityCall(frame: BuiltinFrame, fn: UtilityFunction, argc: Int) {
  val packed = buildString {
    append("0").append(US).append(fn.name).append(US).append(UTILITY_FLAG).append(US)
    for (i in 1..argc) append(US).append(encodeSlot(frame, i, frame.kinds[i]))
  }
  val result = webBuiltinTransportForTests?.invoke(packed) ?: webBuiltinTransport(packed)
  if (result.startsWith("E:")) {
    error("Godot utility function ${fn.name} failed on Web: ${result.substring(2)}")
  }
  storeFrameResult(
    frame,
    result,
    "utility function ${fn.name}",
    WebBuiltinSignatures.VARIANT_RETURN,
  )
}

/** The static flag that marks a utility-function call on the builtin opcode (task 129 E). */
private const val UTILITY_FLAG = '2'

/**
 * Reads the returned Variant into the frame's return slot. [what] names the call in errors
 * ("builtin length (Variant type 4)", "utility function sin").
 */
private fun storeFrameResult(frame: BuiltinFrame, result: String, what: String, expected: Int) {
  val type = WebPackedValues.variantType(result)
  val payload = WebPackedValues.variantPayload(result)
  // Task 134 D1 review S2: `callv` answers a wrong arity or argument type with an error print and
  // null, and a Variant of an unexpected type would be read as zeros: both fail loud here.
  if (expected != WebBuiltinSignatures.VARIANT_RETURN && type != expected) {
    error(
      "Godot $what returned Variant type $type on Web, expected $expected (a wrong argument count " +
        "or type makes the engine answer nil)"
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
        "Godot $what returned Variant type $type, which the Web frame cannot hold"
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
 * The Web half of the boxed builtin-method and utility-function calls (task 134 D2, task 129 E;
 * native: `src/commonMain/.../binding/runtime/UtilityCalls.expect.kt`), which the shared String,
 * NodePath and PackedByteArray methods (`net.multigesture.kanama.builtins`, `types/NodePath.kt`)
 * and the shared generated `GD` call. Web is a separate project, so this is a plain object with the
 * same shape. One crossing carries the base and the arguments as encoded Variants
 * ([WebPackedValues.encodeVariant]: scalars, String, NodePath, value types, ByteArray, objects,
 * Callables, and Lists / Maps of those since task 138 item 2) and returns one (Arrays and
 * Dictionaries included).
 */
internal object UtilityCalls {
  /**
   * Calls the utility function [fn]; [argTypes] is the native seam's, the values carry their own.
   */
  @Suppress("UNUSED_PARAMETER")
  fun call(fn: UtilityFunction, argTypes: IntArray, args: Array<out Any?>, retType: Int): Any? {
    if (fn.localId >= 0) {
      val local = WebLocalUtilities.callBoxed(fn.localId, args)
      if (local !== WebLocalUtilities.NOT_LOCAL) return local
    }
    val packed = buildString {
      append("0").append(US).append(fn.name).append(US).append(UTILITY_FLAG).append(US)
      for (arg in args) append(US).append(WebPackedValues.encodeVariant(arg))
    }
    return decodeReturn(
      transport(packed, "utility function ${fn.name}"),
      "utility function ${fn.name}",
      retType,
    )
  }

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
    val what = "builtin ${method.name} (Variant type ${method.variantType})"
    return decodeReturn(transport(packed, what), what, retType)
  }

  private fun transport(packed: String, what: String): String {
    val result = webBuiltinTransportForTests?.invoke(packed) ?: webBuiltinTransport(packed)
    if (result.startsWith("E:")) error("Godot $what failed on Web: ${result.substring(2)}")
    return result
  }

  private fun decodeReturn(result: String, what: String, retType: Int): Any? {
    if (retType < 0) return null
    val type = WebPackedValues.variantType(result)
    check(
      retType == 0 || type == retType || (retType == WebPackedValues.TYPE_OBJECT && type == 0)
    ) {
      "Godot $what returned Variant type $type on Web, expected $retType (a wrong argument count " +
        "or type makes the engine answer nil)"
    }
    return WebPackedValues.decodeVariant(result)
  }
}

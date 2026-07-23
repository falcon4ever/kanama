package net.multigesture.kanama.binding.runtime

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.RefCounted
import net.multigesture.kanama.backend.GodotBackendCalls
import net.multigesture.kanama.backend.GodotBackendSpi
import net.multigesture.kanama.backend.GodotCallDescriptor
import net.multigesture.kanama.backend.GodotCallSite
import net.multigesture.kanama.backend.GodotColor
import net.multigesture.kanama.backend.GodotHandle
import net.multigesture.kanama.backend.GodotRect2
import net.multigesture.kanama.backend.GodotVector2
import net.multigesture.kanama.backend.GodotVector2i
import net.multigesture.kanama.backend.InternalKanamaBackendApi
import net.multigesture.kanama.binding.runtime.Signals as RuntimeSignals
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

/** JVM/Panama implementation of the first neutral typed call-shape slice. */
@OptIn(InternalKanamaBackendApi::class)
internal object CommonGodotBackend : GodotBackendSpi {
  private val resourceLoaderSingleton by lazy { ObjectCalls.getSingleton("ResourceLoader") }
  private val inputSingleton by lazy { ObjectCalls.getSingleton("Input") }

  override fun requireLive(handle: GodotHandle) {
    require(handle.backendToken() != 0L) { "Godot object handle must not be NULL" }
  }

  override fun resolve(descriptor: GodotCallDescriptor): GodotCallSite =
    if (descriptor.className == "@GlobalScope") {
      GodotCallSite.fromBackendToken(descriptor.opcode.toLong())
    } else {
      GodotCallSite.fromBackendToken(
        ObjectCalls.getMethodBind(descriptor.className, descriptor.methodName, descriptor.hash)
          .address()
      )
    }

  override fun invokeBoolRetInt(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: Boolean,
  ): Int = ObjectCalls.ptrcallWithBoolArgRetInt(segment(callSite), segment(receiver), value)

  override fun invokeBoolRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: Boolean,
  ): GodotHandle? =
    collapseRetainedFluentResult(
      receiver,
      ObjectCalls.ptrcallWithBoolArgRetObject(segment(callSite), segment(receiver), value),
    )

  override fun invokeBoolArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: Boolean,
  ) {
    ObjectCalls.ptrcallWithBoolArg(segment(callSite), segment(receiver), value)
  }

  override fun invokeDoubleArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: Double,
  ) {
    ObjectCalls.ptrcallWithDoubleArg(segment(callSite), segment(receiver), value)
  }

  override fun invokeNoArgsRetVector2(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): GodotVector2 =
    ObjectCalls.ptrcallNoArgsRetVector2(segment(callSite), segment(receiver)).let {
      GodotVector2(it.x, it.y)
    }

  override fun invokeVector2Arg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: GodotVector2,
  ) {
    ObjectCalls.ptrcallWithVector2Arg(
      segment(callSite),
      segment(receiver),
      Vector2(value.x, value.y),
    )
  }

  override fun invokeNoArgsRetRect2(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): GodotRect2 =
    ObjectCalls.ptrcallNoArgsRetRect2(segment(callSite), segment(receiver)).let {
      GodotRect2(GodotVector2(it.position.x, it.position.y), GodotVector2(it.size.x, it.size.y))
    }

  override fun invokeNoArgsVoid(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ) {
    ObjectCalls.ptrcallNoArgs(segment(callSite), segment(receiver))
  }

  override fun invokeTexture2DVector2ColorArgs(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    texture: GodotHandle,
    position: GodotVector2,
    modulate: GodotColor,
  ) {
    ObjectCalls.ptrcallWithObjectVector2AndColorArgs(
      segment(callSite),
      segment(receiver),
      segment(texture),
      Vector2(position.x, position.y),
      Color(modulate.r, modulate.g, modulate.b, modulate.a),
    )
  }

  override fun invokeStringStringLongRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    first: String,
    second: String,
    value: Long,
  ): GodotHandle? =
    ObjectCalls.ptrcallWithTwoStringAndLongArgsRetObject(
        segment(callSite),
        resourceLoaderSingleton,
        first,
        second,
        value,
      )
      .takeIf { it.address() != 0L }
      ?.let { GodotHandle.fromBackendToken(it.address()) }

  override fun invokeUtilityNoArgsVoid(descriptor: GodotCallDescriptor, callSite: GodotCallSite) {
    GD.randomize()
  }

  override fun invokeUtilityNoArgsRetLong(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
  ): Long = GD.randi()

  override fun invokeUtilityNoArgsRetDouble(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
  ): Double = GD.randf()

  override fun invokeStringNameIntRetInt(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    name: String,
    value: Int,
  ): Int {
    RuntimeSignals.emitAny(segment(receiver), name, listOf(value))
    return 0
  }

  override fun invokeStringNameRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    value: String,
  ): GodotHandle? =
    ObjectCalls.constructObject(value)
      .takeIf { it.address() != 0L }
      ?.let { GodotHandle.fromBackendToken(it.address()) }

  override fun invokeObjectBoolLongArgs(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    objectValue: GodotHandle,
    boolValue: Boolean,
    longValue: Long,
  ) {
    ObjectCalls.ptrcallWithObjectBoolLongArgs(
      segment(callSite),
      segment(receiver),
      segment(objectValue),
      boolValue,
      longValue,
    )
  }

  override fun invokeObjectArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: GodotHandle?,
  ) {
    ObjectCalls.ptrcallWithObjectArgs(
      segment(callSite),
      segment(receiver),
      listOf(value?.let(::segment) ?: MemorySegment.NULL),
    )
  }

  override fun invokeNodePathRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    path: String,
  ): GodotHandle? =
    ObjectCalls.ptrcallWithNodePathArgRetObject(segment(callSite), segment(receiver), path)
      .takeIf { it.address() != 0L }
      ?.let { GodotHandle.fromBackendToken(it.address()) }

  override fun invokeLongRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: Long,
  ): GodotHandle? =
    collapseRetainedFluentResult(
      receiver,
      ObjectCalls.ptrcallWithLongArgRetObject(segment(callSite), segment(receiver), value),
    )

  override fun invokeLongArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: Long,
  ) {
    require(descriptor.opcode == 52)
    require(value in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())
    ObjectCalls.ptrcallWithIntArg(segment(callSite), segment(receiver), value.toInt())
  }

  override fun invokeNoArgsRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): GodotHandle? =
    ObjectCalls.ptrcallNoArgsRetObject(segment(callSite), segment(receiver))
      .takeIf { it.address() != 0L }
      ?.let { GodotHandle.fromBackendToken(it.address()) }

  override fun invokeObjectLongVector2Args(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    objectValue: GodotHandle?,
    longValue: Long,
    vectorValue: GodotVector2,
  ) {
    ObjectCalls.ptrcallWithObjectLongAndVector2Arg(
      segment(callSite),
      inputSingleton,
      objectValue?.let(::segment) ?: MemorySegment.NULL,
      longValue,
      Vector2(vectorValue.x, vectorValue.y),
    )
  }

  override fun invokeStringNameCallableLongRetLong(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    signal: String,
    target: GodotHandle,
    method: String,
    flags: Long,
  ): Long =
    ObjectCalls.ptrcallWithStringNameCallableAndUInt32ArgsRetLong(
      segment(callSite),
      segment(receiver),
      signal,
      segment(target),
      method,
      flags,
    )

  override fun invokeStringNameBoundCallableLongRetLong(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    signal: String,
    target: GodotHandle,
    method: String,
    boundValue: Long,
    flags: Long,
  ): Long =
    ObjectCalls.ptrcallWithStringNameBoundCallableAndUInt32ArgsRetLong(
      segment(callSite),
      segment(receiver),
      signal,
      segment(target),
      method,
      listOf(boundValue),
      flags,
    )

  override fun invokeStringNameRetInt(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: String,
  ): Int {
    RuntimeSignals.emitAny(segment(receiver), value, emptyList())
    return 0
  }

  override fun invokeStringNameRetBool(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: String,
  ): Boolean =
    ObjectCalls.ptrcallWithStringNameArgRetBool(segment(callSite), segment(receiver), value)

  override fun invokeNoArgsRetBool(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): Boolean = ObjectCalls.ptrcallNoArgsRetBool(segment(callSite), segment(receiver))

  override fun invokeNoArgsRetDouble(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): Double = ObjectCalls.ptrcallNoArgsRetDouble(segment(callSite), segment(receiver))

  override fun invokeNoArgsRetLong(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): Long = ObjectCalls.ptrcallNoArgsRetLong(segment(callSite), segment(receiver))

  override fun invokeStringNameArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: String,
  ) {
    ObjectCalls.ptrcallWithStringNameArg(segment(callSite), segment(receiver), value)
  }

  override fun invokeStringNameVector2iRetInt(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    name: String,
    value: GodotVector2i,
  ): Int {
    RuntimeSignals.emitAny(segment(receiver), name, listOf(Vector2i(value.x, value.y)))
    return 0
  }

  override fun invokeNoArgsRetColor(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): GodotColor =
    ObjectCalls.ptrcallNoArgsRetColor(segment(callSite), segment(receiver)).let {
      GodotColor(it.r, it.g, it.b, it.a)
    }

  override fun invokeColorArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: GodotColor,
  ) {
    ObjectCalls.ptrcallWithColorArg(
      segment(callSite),
      segment(receiver),
      Color(value.r, value.g, value.b, value.a),
    )
  }

  override fun invokeObjectNodePathVector2DoubleRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    target: GodotHandle,
    property: String,
    finalValue: GodotVector2,
    duration: Double,
  ): GodotHandle? =
    ObjectCalls.ptrcallWithObjectNodePathVariantDoubleArgsRetObject(
        segment(callSite),
        segment(receiver),
        segment(target),
        property,
        Vector2(finalValue.x, finalValue.y),
        duration,
      )
      .takeIf { it.address() != 0L }
      ?.let { GodotHandle.fromBackendToken(it.address()) }

  override fun invokeObjectNodePathColorDoubleRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    target: GodotHandle,
    property: String,
    finalValue: GodotColor,
    duration: Double,
  ): GodotHandle? =
    ObjectCalls.ptrcallWithObjectNodePathVariantDoubleArgsRetObject(
        segment(callSite),
        segment(receiver),
        segment(target),
        property,
        Color(finalValue.r, finalValue.g, finalValue.b, finalValue.a),
        duration,
      )
      .takeIf { it.address() != 0L }
      ?.let { GodotHandle.fromBackendToken(it.address()) }

  private fun segment(handle: GodotHandle): MemorySegment =
    MemorySegment.ofAddress(handle.backendToken())

  private fun segment(callSite: GodotCallSite): MemorySegment =
    MemorySegment.ofAddress(callSite.backendToken())

  private fun collapseRetainedFluentResult(
    receiver: GodotHandle,
    returned: MemorySegment,
  ): GodotHandle? {
    if (returned.address() == 0L) return null
    if (returned.address() == receiver.backendToken()) {
      RefCounted.releaseHandle(returned)
      return receiver
    }
    return GodotHandle.fromBackendToken(returned.address())
  }
}

@OptIn(InternalKanamaBackendApi::class)
internal fun installCommonGodotBackend() {
  GodotBackendCalls.install(CommonGodotBackend)
}

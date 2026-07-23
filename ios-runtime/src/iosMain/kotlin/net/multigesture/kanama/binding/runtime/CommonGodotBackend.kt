package net.multigesture.kanama.binding.runtime

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.IosGodot
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
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Vector2

/** Kotlin/Native/C-shim implementation of the first neutral typed call-shape slice. */
@OptIn(InternalKanamaBackendApi::class)
internal object CommonGodotBackend : GodotBackendSpi {
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
      IosGodot.tweenSetParallel(receiver.backendToken(), if (value) 1 else 0),
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
    if (descriptor.opcode == 37) {
      IosGodot.tweenKill(receiver.backendToken())
    } else {
      ObjectCalls.ptrcallNoArgs(segment(callSite), segment(receiver))
    }
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
  ): GodotHandle? {
    require(value == 1L) { "iOS typed ResourceLoader slice currently supports CACHE_MODE_REUSE" }
    return IosGodot.resourceLoaderLoad(first, second)
      .takeIf { it != 0L }
      ?.let { GodotHandle.fromBackendToken(it) }
  }

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
  ): Int = IosGodot.objectEmitSignalInt(receiver.backendToken(), name, value.toLong())

  override fun invokeStringNameRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    value: String,
  ): GodotHandle? =
    IosGodot.constructObject(value).takeIf { it != 0L }?.let(GodotHandle::fromBackendToken)

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
    ObjectCalls.ptrcallWithNodePathArgRetObject(
        segment(callSite),
        segment(receiver),
        NodePath(path),
      )
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
      when (descriptor.opcode) {
        41 -> IosGodot.tweenerSetTrans(receiver.backendToken(), value)
        42 -> IosGodot.tweenerSetEase(receiver.backendToken(), value)
        else ->
          ObjectCalls.ptrcallWithLongArgRetObject(segment(callSite), segment(receiver), value)
            .address()
      },
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
    (if (descriptor.opcode == 36) {
        IosGodot.nodeCreateTween(receiver.backendToken())
      } else {
        ObjectCalls.ptrcallNoArgsRetObject(segment(callSite), segment(receiver)).address()
      })
      .takeIf { it != 0L }
      ?.let(GodotHandle::fromBackendToken)

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
    IosGodot.objectConnect(receiver.backendToken(), signal, target.backendToken(), method, flags)

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
    ObjectCalls.connectBound(
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
    GodotObject(receiver.backendToken()).emitSignal(value)
    return 0
  }

  override fun invokeStringNameRetBool(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: String,
  ): Boolean = IosGodot.objectIsClass(receiver.backendToken(), value)

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
  ): Int =
    IosGodot.objectEmitSignalVector2i(
      receiver.backendToken(),
      name,
      value.x.toLong(),
      value.y.toLong(),
    )

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
    IosGodot.tweenTweenPropertyVector2(
        receiver.backendToken(),
        target.backendToken(),
        property,
        finalValue.x.toDouble(),
        finalValue.y.toDouble(),
        duration,
      )
      .takeIf { it != 0L }
      ?.let(GodotHandle::fromBackendToken)

  override fun invokeObjectNodePathColorDoubleRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    target: GodotHandle,
    property: String,
    finalValue: GodotColor,
    duration: Double,
  ): GodotHandle? =
    IosGodot.tweenTweenPropertyColor(
        receiver.backendToken(),
        target.backendToken(),
        property,
        finalValue.r.toDouble(),
        finalValue.g.toDouble(),
        finalValue.b.toDouble(),
        finalValue.a.toDouble(),
        duration,
      )
      .takeIf { it != 0L }
      ?.let(GodotHandle::fromBackendToken)

  private fun segment(handle: GodotHandle): MemorySegment =
    MemorySegment.ofAddress(handle.backendToken())

  private fun segment(callSite: GodotCallSite): MemorySegment =
    MemorySegment.ofAddress(callSite.backendToken())

  private fun collapseRetainedFluentResult(receiver: GodotHandle, returned: Long): GodotHandle? {
    if (returned == 0L) return null
    if (returned == receiver.backendToken()) {
      RefCounted.releaseHandle(MemorySegment.ofAddress(returned))
      return receiver
    }
    return GodotHandle.fromBackendToken(returned)
  }
}

@OptIn(InternalKanamaBackendApi::class)
internal fun installCommonGodotBackend() {
  GodotBackendCalls.install(CommonGodotBackend)
}

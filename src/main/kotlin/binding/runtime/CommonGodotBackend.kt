package net.multigesture.kanama.binding.runtime

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.backend.GodotBackendCalls
import net.multigesture.kanama.backend.GodotBackendSpi
import net.multigesture.kanama.backend.GodotCallDescriptor
import net.multigesture.kanama.backend.GodotCallSite
import net.multigesture.kanama.backend.GodotColor
import net.multigesture.kanama.backend.GodotHandle
import net.multigesture.kanama.backend.GodotRect2
import net.multigesture.kanama.backend.GodotVector2
import net.multigesture.kanama.backend.InternalKanamaBackendApi
import net.multigesture.kanama.binding.runtime.Signals as RuntimeSignals
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2

/** JVM/Panama implementation of the first neutral typed call-shape slice. */
@OptIn(InternalKanamaBackendApi::class)
internal object CommonGodotBackend : GodotBackendSpi {
  private val resourceLoaderSingleton by lazy { ObjectCalls.getSingleton("ResourceLoader") }

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

  private fun segment(handle: GodotHandle): MemorySegment =
    MemorySegment.ofAddress(handle.backendToken())

  private fun segment(callSite: GodotCallSite): MemorySegment =
    MemorySegment.ofAddress(callSite.backendToken())
}

@OptIn(InternalKanamaBackendApi::class)
internal fun installCommonGodotBackend() {
  GodotBackendCalls.install(CommonGodotBackend)
}

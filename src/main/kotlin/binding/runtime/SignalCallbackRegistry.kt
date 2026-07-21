package net.multigesture.kanama.binding.runtime

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

object SignalCallbackRegistry {
  private val nextId = AtomicLong(1)
  private val callbacks = ConcurrentHashMap<Long, (List<Any?>) -> Unit>()

  fun register(callback: (List<Any?>) -> Unit): Long {
    val id = nextId.getAndIncrement()
    callbacks[id] = callback
    return id
  }

  fun unregister(id: Long) {
    callbacks.remove(id)
  }

  fun invoke(id: Long, args: List<Any?>) {
    callbacks[id]?.invoke(args)
  }
}

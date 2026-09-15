package net.multigesture.kanama.binding

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.atomic.AtomicReferenceArray

/**
 * Maps GDExtension class-instance pointers to real Kotlin objects.
 *
 * When we register a class with Godot, every engine-owned object carries an opaque `void*` instance
 * token that we chose. Godot hands that token back on every callback (method call, virtual
 * dispatch, set/get, notification, free). The registry is how we get from that token to the actual
 * Kotlin instance.
 *
 * We deliberately do *not* use [System.identityHashCode] or the Kotlin object's JVM address — both
 * can collide and neither survives GC moves. Instead every registered object gets a fresh monotonic
 * [Long] handle from [nextHandle]. The handle is also the `void*` we hand to `object_set_instance`.
 *
 * Starting at 1 (not 0) so the handle is never confused with NULL.
 */
object ObjectRegistry {

  private const val INITIAL_DENSE_CAPACITY = 4096
  private const val MAX_DENSE_HANDLE = 1 shl 20

  private val nextHandle = AtomicLong(1)
  private val byHandle = ConcurrentHashMap<Long, Any>()
  @Volatile private var denseByHandle = AtomicReferenceArray<Any?>(INITIAL_DENSE_CAPACITY)

  fun register(instance: Any): Long {
    val handle = nextHandle.getAndIncrement()
    byHandle[handle] = instance
    setDense(handle, instance)
    return handle
  }

  fun get(handle: Long): Any? {
    val index = handle.toInt()
    val dense = denseByHandle
    if (handle > 0L && handle == index.toLong() && index < dense.length()) {
      dense.get(index)?.let {
        return it
      }
    }
    return byHandle[handle]
  }

  fun unregister(handle: Long): Any? {
    clearDense(handle)
    return byHandle.remove(handle)
  }

  fun size(): Int = byHandle.size

  private fun setDense(handle: Long, instance: Any) {
    val index = handle.toInt()
    if (handle <= 0L || handle != index.toLong() || index > MAX_DENSE_HANDLE) return
    ensureDenseCapacity(index)
    denseByHandle.set(index, instance)
  }

  private fun clearDense(handle: Long) {
    val index = handle.toInt()
    val dense = denseByHandle
    if (handle > 0L && handle == index.toLong() && index < dense.length()) {
      dense.set(index, null)
    }
  }

  private fun ensureDenseCapacity(index: Int) {
    if (index < denseByHandle.length()) return
    synchronized(this) {
      val current = denseByHandle
      if (index < current.length()) return
      var newLength = current.length()
      while (newLength <= index && newLength <= MAX_DENSE_HANDLE / 2) {
        newLength *= 2
      }
      if (newLength <= index) newLength = MAX_DENSE_HANDLE + 1
      val expanded = AtomicReferenceArray<Any?>(newLength)
      for (i in 0 until current.length()) {
        expanded.set(i, current.get(i))
      }
      denseByHandle = expanded
    }
  }
}

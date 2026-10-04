package net.multigesture.kanama.binding

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.api.RefCounted
import net.multigesture.kanama.binding.runtime.ScriptPropertyRetains

/**
 * Desktop's way into [ScriptPropertyRetains] (task 132): a generated setter runs its decode inside
 * [capture], and the retaining readers in `BuiltinTypes` report each reference they take for the
 * property through [recordHandle] (a `+1` they took on a raw handle) or [retainForProperty] (a `+1`
 * of the registry's own beside a wrapper's). When the assignment succeeds the references are
 * registered under (owner, property), releasing the ones the property held before; when the read or
 * the assignment throws they are released and the previous entry stays.
 *
 * Captures nest per thread: a read that resolves a script object may run another owner's setter (a
 * rebuilt instance's refill).
 */
internal object ScriptPropertyCapture {
  private class Capture {
    val handles = ArrayList<MemorySegment>(4)
  }

  private val captures = ThreadLocal.withInitial { ArrayDeque<Capture>() }

  fun <T> capture(owner: MemorySegment, property: String, read: () -> T, assign: (T) -> Unit) {
    val stack = captures.get()
    val capture = Capture()
    stack.addLast(capture)
    val value =
      try {
        read()
      } catch (t: Throwable) {
        stack.removeLast()
        ScriptPropertyRetains.release(capture.handles)
        throw t
      }
    stack.removeLast()
    try {
      assign(value)
    } catch (t: Throwable) {
      ScriptPropertyRetains.release(capture.handles)
      throw t
    }
    ScriptPropertyRetains.register(owner.address(), property, capture.handles)
  }

  /** A retaining reader took a `+1` on [handle] for the property being set. */
  fun recordHandle(handle: MemorySegment) {
    captures.get().lastOrNull()?.handles?.add(handle)
  }

  /**
   * The registry's own `+1` on a value whose wrapper keeps its own (an engine resource read into a
   * property): the wrapper stays the Kotlin code's, so an alias of it survives a later set. Nothing
   * is taken outside a setter's capture.
   */
  fun retainForProperty(handle: MemorySegment) {
    val capture = captures.get().lastOrNull() ?: return
    if (handle.address() != 0L && RefCounted.retainHandle(handle)) capture.handles.add(handle)
  }
}

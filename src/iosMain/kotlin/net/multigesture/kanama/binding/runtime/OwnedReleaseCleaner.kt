package net.multigesture.kanama.binding.runtime

import kotlin.concurrent.atomics.AtomicInt
import kotlin.concurrent.atomics.AtomicReference
import kotlin.concurrent.atomics.ExperimentalAtomicApi
import kotlin.experimental.ExperimentalNativeApi
import kotlin.native.ref.Cleaner
import kotlin.native.ref.createCleaner
import kotlin.native.runtime.GC
import kotlin.native.runtime.NativeRuntimeApi
import platform.Foundation.NSThread
import platform.posix.usleep

/**
 * iOS: the platform half of [OwnedReleases] (task 132 D2). The `expect` declaration and what each
 * member promises are in
 * `src/commonMain/kotlin/net/multigesture/kanama/binding/runtime/OwnedReleaseCleaner.expect.kt`.
 *
 * `kotlin.native.ref.createCleaner` runs the cleanup action once the returned `Cleaner` -- kept in
 * the wrapper -- is collected, on the runtime's cleaner thread; the action's resource is the
 * [PendingRelease], never the wrapper. Kotlin/Native cannot cancel a cleaner, so `close()` only
 * disarms the release and the action later runs as a no-op. The queue is a Treiber stack over an
 * atomic reference: lock-free pushes from the cleaner thread, one swap-and-walk on the main thread.
 */
@OptIn(ExperimentalAtomicApi::class, ExperimentalNativeApi::class)
internal actual object OwnedReleaseCleaner {
  private class Node(val release: PendingRelease, val next: Node?)

  /** Pushed by the cleaner thread. */
  private val pushed = AtomicReference<Node?>(null)

  /** Main thread only: the batch taken from [pushed], oldest first. */
  private var batch: ArrayDeque<PendingRelease> = ArrayDeque()

  actual val available: Boolean
    get() = true

  actual val unavailableReason: String
    get() = ""

  actual val mechanism: String
    get() = "kotlin.native.ref.createCleaner"

  actual fun register(wrapper: Any, release: PendingRelease): Any? =
    createCleaner(release) { it.fire() }

  actual fun cancel(registration: Any?) {
    // A Kotlin/Native Cleaner cannot be cancelled: the release is disarmed, so it fires as a no-op.
  }

  actual fun enqueue(release: PendingRelease) {
    while (true) {
      val head = pushed.load()
      if (pushed.compareAndSet(head, Node(release, head))) return
    }
  }

  actual fun poll(): PendingRelease? {
    if (batch.isEmpty()) {
      var node = pushed.exchange(null) ?: return null
      // The stack is newest first; release in creation order.
      val taken = ArrayList<PendingRelease>()
      while (true) {
        taken += node.release
        node = node.next ?: break
      }
      for (index in taken.indices.reversed()) batch.addLast(taken[index])
    }
    return batch.removeFirstOrNull()
  }

  @OptIn(NativeRuntimeApi::class)
  actual fun collectGarbage(timeoutMillis: Long) {
    // A sentinel cleaner made unreachable just before each collection: once its action has run,
    // the cleaner thread has processed that collection's cleaners.
    repeat(2) {
      val done = AtomicInt(0)
      dropSentinel(done)
      GC.collect()
      var waited = 0L
      while (done.load() == 0 && waited < timeoutMillis / 2) {
        usleep(1_000u)
        waited += 1
      }
    }
  }

  // Its own function, so no stack slot of the caller keeps the sentinel reachable.
  private fun dropSentinel(done: AtomicInt) {
    sentinelSink = createCleaner(done) { it.store(1) }
    sentinelSink = null
  }

  private var sentinelSink: Cleaner? = null

  actual fun creationSite(): String? {
    val report = IosScriptErrors.reportFor(Throwable(), "")
    return if (report.file.isEmpty()) null else "${report.file}:${report.line}"
  }

  actual fun log(message: String) {
    IosGodotOutput.print(message)
  }

  actual fun warn(message: String) {
    IosGodotOutput.warn(message)
  }

  // Kotlin/Native scripts run on the app's main thread, which is Godot's main thread on iOS.
  actual fun noteMainThread() {}

  actual fun isMainThread(): Boolean = NSThread.isMainThread

  actual fun ownerHasPlainScript(address: Long): Boolean =
    net.multigesture.kanama.ios.KanamaIosRuntime.ownerHasPlainScript(address)
}

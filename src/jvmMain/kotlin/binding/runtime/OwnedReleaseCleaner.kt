package net.multigesture.kanama.binding.runtime

import java.lang.ref.Cleaner
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import net.multigesture.kanama.api.GD

/**
 * Desktop/Android: the platform half of [OwnedReleases] (task 132 D2). The `expect` declaration and
 * what each member promises are in
 * `src/commonMain/kotlin/net/multigesture/kanama/binding/runtime/OwnedReleaseCleaner.expect.kt`.
 *
 * One shared `java.lang.ref.Cleaner` (one daemon thread, `kanama-owned-releases`) runs the cleanup
 * actions; the queue they feed is a `ConcurrentLinkedQueue` (lock-free). `java.lang.ref.Cleaner`
 * exists on every desktop JDK and on Android from API 33 (Android 13, Kanama's Android minimum).
 * [register] runs on any thread (the cleaner and the queue are thread-agnostic).
 */
internal actual object OwnedReleaseCleaner {
  private val cleaner: Cleaner =
    Cleaner.create { runnable ->
      Thread(runnable, "kanama-owned-releases").apply { isDaemon = true }
    }

  private val pending = ConcurrentLinkedQueue<PendingRelease>()

  actual val mechanism: String
    get() = "java.lang.ref.Cleaner"

  actual fun register(wrapper: Any, release: PendingRelease): Any? =
    cleaner.register(wrapper, FireRelease(release))

  actual fun cancel(registration: Any?) {
    // Runs the (disarmed, so no-op) action now and drops the phantom reference, so a closed
    // wrapper costs the cleaner nothing more.
    (registration as? Cleaner.Cleanable)?.clean()
  }

  actual fun enqueue(release: PendingRelease) {
    pending.add(release)
  }

  actual fun poll(): PendingRelease? = pending.poll()

  actual fun collectGarbage(timeoutMillis: Long) {
    // Two rounds: a sentinel registered just before each System.gc() is unreachable at once, so
    // when its action has run the cleaner thread has processed that collection's references.
    repeat(2) {
      val done = CountDownLatch(1)
      cleaner.register(Any(), done::countDown)
      // Runtime.gc(), not System.gc(): ART ignores System.gc() unless a finalization ran since
      // the last one; Runtime.gc() always collects (on HotSpot the two are the same call).
      Runtime.getRuntime().gc()
      done.await(timeoutMillis / 2, TimeUnit.MILLISECONDS)
    }
  }

  actual fun creationSite(): String? {
    val report = ScriptErrors.reportFor(Throwable(), "")
    return if (report.file.isEmpty()) null else "${report.file}:${report.line}"
  }

  actual fun log(message: String) {
    runCatching { GD.print(message) }.onFailure { System.err.println("[kanama:kt] $message") }
  }

  actual fun warn(message: String) {
    runCatching { GD.pushWarning(message) }
      .onFailure { System.err.println("[kanama:kt] WARNING: $message") }
  }

  actual fun ownerHasPlainScript(address: Long): Boolean =
    net.multigesture.kanama.binding.ScriptOwnerLinks.ownerHasPlainScript(address)

  /** The cleanup action: holds the release, never the wrapper. */
  private class FireRelease(private val release: PendingRelease) : Runnable {
    override fun run() {
      release.fire()
    }
  }
}

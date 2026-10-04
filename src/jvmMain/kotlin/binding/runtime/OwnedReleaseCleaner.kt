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
 * exists on every desktop JDK and on Android from API 33 (Android 13, the floor of Kanama's Android
 * release builds); on older Android debug installs [available] is false and `close()` stays the
 * only release. The `Cleaner` type is touched only inside [JdkCleaner], which loads once the class
 * is known to exist.
 */
internal actual object OwnedReleaseCleaner {
  private val cleaner: JdkCleaner? =
    try {
      Class.forName("java.lang.ref.Cleaner")
      JdkCleaner()
    } catch (_: ClassNotFoundException) {
      null
    } catch (_: LinkageError) {
      null
    }

  private val pending = ConcurrentLinkedQueue<PendingRelease>()

  actual val available: Boolean
    get() = cleaner != null

  actual val unavailableReason: String
    get() = if (cleaner != null) "" else "java.lang.ref.Cleaner needs Android 13 (API 33)"

  actual val mechanism: String
    get() = "java.lang.ref.Cleaner"

  actual fun register(wrapper: Any, release: PendingRelease): Any? =
    cleaner?.register(wrapper, release)

  actual fun cancel(registration: Any?) {
    // Runs the (disarmed, so no-op) action now and drops the phantom reference, so a closed
    // wrapper costs the cleaner nothing more.
    if (registration != null) cleaner?.cancel(registration)
  }

  actual fun enqueue(release: PendingRelease) {
    pending.add(release)
  }

  actual fun poll(): PendingRelease? = pending.poll()

  actual fun collectGarbage(timeoutMillis: Long) {
    val cleaner = cleaner ?: return
    // Two rounds: a sentinel registered just before each System.gc() is unreachable at once, so
    // when its action has run the cleaner thread has processed that collection's references.
    repeat(2) {
      val done = CountDownLatch(1)
      cleaner.registerSentinel(done)
      System.gc()
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

  /** The `java.lang.ref.Cleaner` itself, isolated so a runtime without the class never loads it. */
  private class JdkCleaner {
    private val cleaner: Cleaner =
      Cleaner.create { runnable ->
        Thread(runnable, "kanama-owned-releases").apply { isDaemon = true }
      }

    fun register(wrapper: Any, release: PendingRelease): Cleaner.Cleanable =
      cleaner.register(wrapper, FireRelease(release))

    fun cancel(registration: Any) {
      (registration as Cleaner.Cleanable).clean()
    }

    fun registerSentinel(done: CountDownLatch) {
      cleaner.register(Any(), done::countDown)
    }
  }

  /** The cleanup action: holds the release, never the wrapper. */
  private class FireRelease(private val release: PendingRelease) : Runnable {
    override fun run() {
      release.fire()
    }
  }
}

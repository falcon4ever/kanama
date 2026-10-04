package net.multigesture.kanama.binding.runtime

/**
 * The platform half of [OwnedReleases] (task 132 D2): register a cleanup action that runs once an
 * owned wrapper is unreachable, and the lock-free queue that action feeds and the main thread
 * drains.
 * - desktop/Android: one shared `java.lang.ref.Cleaner` (one daemon thread) and a
 *   `ConcurrentLinkedQueue`; Android before API 33 has no `java.lang.ref.Cleaner`, so the fallback
 *   is off there ([available] false) and `close()` stays the only release;
 * - iOS: `kotlin.native.ref.createCleaner` and a Treiber stack over an atomic reference.
 *
 * Files named `*.expect.kt` hold `expect` declarations only (the Android remap skips them), and no
 * member has a default argument.
 */
internal expect object OwnedReleaseCleaner {
  /** False when this runtime has no cleaner mechanism; [unavailableReason] says why. */
  val available: Boolean

  /** Why [available] is false (`""` when it is true). */
  val unavailableReason: String

  /** The mechanism's name for the status line (`java.lang.ref.Cleaner`, ...). */
  val mechanism: String

  /**
   * Registers [release]'s [PendingRelease.fire] to run once [wrapper] is unreachable. The result is
   * the registration: the wrapper keeps it (Kotlin/Native's `Cleaner` fires when IT is collected)
   * and hands it back to [cancel].
   */
  fun register(wrapper: Any, release: PendingRelease): Any?

  /** Drops [registration] now (`close()`); the release it guards is already disarmed. */
  fun cancel(registration: Any?)

  /** Called by [PendingRelease.fire] on the cleaner's thread: lock-free, never calls Godot. */
  fun enqueue(release: PendingRelease)

  /** Main thread: the next enqueued release, or null when the queue is empty. */
  fun poll(): PendingRelease?

  /**
   * D4: runs a full GC and waits (at most [timeoutMillis]) for the cleanup actions it triggered to
   * have enqueued their releases.
   */
  fun collectGarbage(timeoutMillis: Long)

  /** The creation site of the wrapper being registered (`Player.kt:42`), for the D5 log. */
  fun creationSite(): String?

  /** Prints [message] to Godot's output (the D5 log). */
  fun log(message: String)
}

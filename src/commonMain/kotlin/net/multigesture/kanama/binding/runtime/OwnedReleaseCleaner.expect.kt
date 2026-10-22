package net.multigesture.kanama.binding.runtime

/**
 * The platform half of [OwnedReleases] (task 132 D2): register a cleanup action that runs once an
 * owned wrapper is unreachable, and the lock-free queue that action feeds and the main thread
 * drains.
 * - desktop/Android: one shared `java.lang.ref.Cleaner` (one daemon thread) and a
 *   `ConcurrentLinkedQueue` (Android has both from API 33, Kanama's Android minimum);
 * - iOS: `kotlin.native.ref.createCleaner` and a Treiber stack over an atomic reference.
 *
 * [register] runs on whichever thread builds the owned wrapper (task 138 item 6); [poll] only on
 * the engine main thread. A worker's wrapper is safe to register only because
 * `ObjectCalls.keepReachable` keeps it reachable through each engine call it makes.
 *
 * Files named `*.expect.kt` hold `expect` declarations only (the Android remap skips them), and no
 * member has a default argument.
 */
internal expect object OwnedReleaseCleaner {
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

  /** Reports [message] as a Godot warning (`push_warning`). */
  fun warn(message: String)

  /**
   * Whether the object at [address] carries a script instance whose Kotlin object is a plain class
   * (not a `KanamaScript`): its owned-wrapper releases are parked (see [OwnedReleases.drain]).
   */
  fun ownerHasPlainScript(address: Long): Boolean
}

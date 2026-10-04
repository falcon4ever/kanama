package net.multigesture.kanama.binding.runtime

import kotlin.concurrent.atomics.AtomicInt
import kotlin.concurrent.atomics.AtomicLong
import kotlin.concurrent.atomics.ExperimentalAtomicApi
import kotlin.concurrent.atomics.decrementAndFetch
import kotlin.concurrent.atomics.incrementAndFetch
import kotlin.jvm.JvmField
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.TimeSource
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.OS
import net.multigesture.kanama.api.RefCounted

/**
 * The fallback release of an owned `RefCounted` wrapper (task 132): a forgotten `close()` is a late
 * release, not a leak.
 * - D1: only a wrapper that owns a `+1` ([RefCounted.markOwned], reached through `wrapOwned`,
 *   `create()`, the `from*` downcasts and `retainForKotlinWrapper`) registers one. A borrowed view
 *   (`fromHandle`, a wrapper constructor, Variant decoding) registers nothing: releasing it would
 *   drop a reference nobody took, and its close() is a no-op. A wrapper built off the engine main
 *   thread registers nothing either (it stays owned; close() releases it): the worker may still be
 *   inside a call through it when the main thread would drain its release.
 * - D2: the registration is a platform cleanup ([OwnedReleaseCleaner]: `java.lang.ref.Cleaner` on
 *   desktop/Android, `kotlin.native.ref.createCleaner` on iOS) whose action holds a
 *   [PendingRelease] -- the raw handle, never the wrapper. When the wrapper becomes unreachable the
 *   action runs on the cleaner's thread and only enqueues the release on a lock-free queue; the
 *   main thread [drain]s that queue once per frame (`unreference()`, destroy at zero), so Godot is
 *   never called off the main thread.
 * - D3: `close()` cancels the cleanup ([cancel]) before it releases, so a `+1` is released once.
 * - D4: [shutdown] runs a GC, waits for the cleaner, and drains, before Godot's leak report.
 * - D5: with the project setting [LOG_SETTING] on, each release made by the GC is logged once per
 *   creation site: `released by GC: <class> (created at <file>:<line>)`. The site is a stack walk
 *   taken when each owned wrapper is built, a few microseconds each, so the setting is for finding
 *   the sites, not for shipping.
 * - Script instances (task 132 blocker 1): a Kotlin script object on a RefCounted owner holds a
 *   `+1` on its owner, released through this same queue (a [PendingRelease] with a [ReleaseHook])
 *   once the script object is unreachable; see `ScriptOwnerLinks` on each backend.
 *
 * Main-thread state ([drain], [configure], [shutdown], the logged-site set) is touched only by the
 * engine main thread; only [PendingRelease.fire] runs elsewhere.
 */
@OptIn(ExperimentalAtomicApi::class)
internal object OwnedReleases {
  /** Whether owned wrappers register a fallback release. Decided by [configure]. */
  @JvmField var enabled: Boolean = true

  /** Whether each GC release is logged once per creation site (D5). Decided by [configure]. */
  @JvmField var logGcReleases: Boolean = false

  /** Whether the engine is a debug build: close() on a borrowed view warns there. */
  @JvmField var debugBuild: Boolean = false

  /** Releases [drain] has made since start: the shutdown log line and the tests read it. */
  var releasedByGc: Long = 0L
    private set

  /** The project setting that turns the D5 log on (off by default). */
  const val LOG_SETTING: String = "kanama/debug/log_gc_releases"

  /** Measurement knob: `0`/`off` turns the fallback off, as `KANAMA_FREED_OBJECT_CHECKS` does. */
  const val ENVIRONMENT_VARIABLE: String = "KANAMA_GC_RELEASES"

  private val loggedSites = HashSet<String>()

  /** Test seam: the release [drain] makes, in JVM unit tests that have no engine. */
  internal var releaseOverride: ((RawSegment) -> Unit)? = null

  /** Test seam: where the D5 lines go instead of Godot's output. */
  internal var logOverride: ((String) -> Unit)? = null

  private fun log(message: String) {
    val sink = logOverride
    if (sink != null) sink(message) else OwnedReleaseCleaner.log(message)
  }

  private fun warn(message: String) {
    val sink = logOverride
    if (sink != null) sink("WARNING: $message") else OwnedReleaseCleaner.warn(message)
  }

  /**
   * Decides [enabled] and [logGcReleases] once the engine singletons exist (the SCENE
   * initialization level) and returns the line the backend logs. [environment] is the value of
   * [ENVIRONMENT_VARIABLE] (empty when unset), [logSetting] the value of [LOG_SETTING]. Never
   * throws.
   */
  fun configure(environment: String, logSetting: Boolean): String {
    logGcReleases = logSetting
    val forced =
      when (environment.trim().lowercase()) {
        "0",
        "false",
        "off" -> false
        "1",
        "true",
        "on" -> true
        else -> null
      }
    val log = if (logGcReleases) ", logging GC releases" else ""
    if (!OwnedReleaseCleaner.available) {
      enabled = false
      return "off (${OwnedReleaseCleaner.unavailableReason}); close() is the only release"
    }
    enabled = forced ?: true
    return when {
      forced == false -> "off ($ENVIRONMENT_VARIABLE=${environment.trim()})"
      forced == true -> "on ($ENVIRONMENT_VARIABLE=${environment.trim()}$log)"
      else -> "on (${OwnedReleaseCleaner.mechanism}$log)"
    }
  }

  /**
   * [configure] from the running engine: [ENVIRONMENT_VARIABLE] through `OS.get_environment` (the
   * game's environment on every backend, as `FreedObjectChecks` reads its knob) and [LOG_SETTING]
   * through `ProjectSettings.get_setting`. Never throws.
   */
  fun configureFromEngine(): String {
    OwnedReleaseCleaner.noteMainThread()
    debugBuild = runCatching { OS.isDebugBuild() }.getOrDefault(false)
    val environment = runCatching { OS.getEnvironment(ENVIRONMENT_VARIABLE) }.getOrDefault("")
    val logSetting = runCatching { boolSetting(LOG_SETTING) }.getOrDefault(false)
    return configure(environment, logSetting)
  }

  private fun boolSetting(name: String): Boolean {
    val settings = GodotObject(GodotHandle(ObjectCalls.getSingleton("ProjectSettings")))
    return settings.call("has_setting", name) == true && settings.call("get_setting", name) == true
  }

  /**
   * Live fallback registrations of owned wrappers (registered, not yet closed or drained). Above
   * [REGISTRATION_CAP] new owned wrappers stop registering (they stay owned; close() releases them)
   * and a one-time warning names the log setting that finds the sites.
   */
  private val liveRegistrations = AtomicLong(0L)

  /** Test seam and measurement: live registrations right now. */
  internal val liveRegistrationCount: Long
    get() = liveRegistrations.load()

  /** See [liveRegistrations]; a test may lower it. */
  internal var registrationCap: Long = REGISTRATION_CAP

  private const val REGISTRATION_CAP = 100_000L
  private var warnedCap = false

  /**
   * The release record for [wrapper], or null when the fallback is off, this is not the engine main
   * thread (one thread compare; see the class comment) or [registrationCap] is reached.
   */
  fun newRelease(wrapper: RefCounted): PendingRelease? {
    if (!enabled || !OwnedReleaseCleaner.isMainThread()) return null
    if (liveRegistrations.load() >= registrationCap) {
      if (!warnedCap) {
        warnedCap = true
        warn(
          "More than $registrationCap owned RefCounted wrappers are waiting for the garbage " +
            "collector to release them; new ones are no longer registered and stay alive until " +
            "you close() them. Turn on $LOG_SETTING to find the sites that drop them, and close " +
            "them (use { })."
        )
      }
      return null
    }
    liveRegistrations.incrementAndFetch()
    val origin = if (logGcReleases) describeOrigin(wrapper) else null
    return PendingRelease(wrapper.handle.segment, wrapper.instanceId, origin, null)
  }

  /** Registers [release] to fire once [wrapper] is unreachable; the result lives in [wrapper]. */
  fun register(wrapper: RefCounted, release: PendingRelease): Any? =
    OwnedReleaseCleaner.register(wrapper, release)

  /** Test seam: runs [wrapper]'s cleanup action now, as the cleaner thread would. */
  internal fun firePendingForTest(wrapper: RefCounted): Boolean = wrapper.firePendingForTest()

  /**
   * After a disarm: the platform drops [registration] now (its action runs as a no-op). [closed] is
   * true when close() won the race, so this registration ends here (not in the drain).
   */
  fun dropRegistration(registration: Any?, closed: Boolean) {
    if (closed) liveRegistrations.decrementAndFetch()
    OwnedReleaseCleaner.cancel(registration)
  }

  private val warnedBorrowedCloses = HashSet<String>()

  /**
   * close() on a wrapper that holds no reference of its own (a borrowed view): nothing to release.
   * Debug builds warn once per wrapper class, because the caller probably meant a different
   * wrapper; before task 132 this released a reference the view never took.
   */
  fun warnBorrowedClose(wrapper: RefCounted) {
    if (!debugBuild) return
    val name = wrapper::class.simpleName ?: "RefCounted"
    if (!warnedBorrowedCloses.add(name)) return
    warn(
      "close() on a borrowed $name view (fromHandle or a wrapper constructor) releases nothing: " +
        "the view took no reference. Close the wrapper you got from the API instead."
    )
  }

  /** Time the drain may spend on owned-wrapper releases per frame; the rest carries over. */
  internal var frameBudget: Duration = 1.milliseconds

  // Owned-wrapper releases taken off the queue but not yet run (the budget ran out); main thread.
  private val backlog = ArrayDeque<PendingRelease>()

  /** Releases waiting for a later frame's budget (tests, measurements). */
  internal val backlogSize: Int
    get() = backlog.size

  /**
   * Main thread, once per frame: releases the handles whose owned wrappers (and script objects) the
   * GC collected. Script-object owner releases (a [ReleaseHook] on the release) run at once, ahead
   * of everything else, so a resource whose script object was collected dies this frame. Owned
   * wrapper releases run within [frameBudget] (about 1 ms), oldest first, and the rest carries over
   * to the next frame, so a burst of millions of dropped wrappers does not stall one frame. Returns
   * how many it released.
   */
  fun drain(): Int = drain(frameBudget)

  private fun drain(budget: Duration): Int {
    var released = 0
    while (true) {
      val release = OwnedReleaseCleaner.poll() ?: break
      when {
        release.hook === UnparkHook -> unpark(release.handle.address())
        release.hook != null -> released += runRelease(release)
        else -> backlog.addLast(release)
      }
    }
    val start = TimeSource.Monotonic.markNow()
    var sinceCheck = 0
    while (backlog.isNotEmpty()) {
      // The clock is read every 64 releases: a release is ~0.1-1 us, the clock ~20 ns.
      if (sinceCheck++ == 64) {
        sinceCheck = 0
        if (start.elapsedNow() >= budget) break
      }
      val release = backlog.removeFirst()
      // An owned wrapper of an object whose script object is a plain class: keep the object (the
      // script object cannot anchor it, see ScriptOwnerLinks), park the release until the script
      // is detached or the game shuts down.
      val owner = release.handle.address()
      if (OwnedReleaseCleaner.ownerHasPlainScript(owner)) {
        // Parked, not live: a parked release no longer counts toward the registration cap.
        liveRegistrations.decrementAndFetch()
        parked.getOrPut(owner) { ArrayList(1) } += release
        continue
      }
      liveRegistrations.decrementAndFetch()
      released += runRelease(release)
    }
    releasedByGc += released
    return released
  }

  // One release on the main thread. An object already destroyed (only possible after a program
  // error, such as closing a borrowed view that dropped the count to zero) is skipped, never
  // unreferenced again.
  private fun runRelease(release: PendingRelease): Int {
    var released = 0
    try {
      if (release.hook?.beforeRelease() != false && isLiveOrUnknown(release)) {
        val override = releaseOverride
        if (override != null) override(release.handle) else RefCounted.releaseHandle(release.handle)
        released = 1
      }
    } catch (t: Throwable) {
      log("[kanama] GC release failed: ${t::class.simpleName}: ${t.message}")
    }
    val origin = release.origin
    if (origin != null && loggedSites.add(origin)) {
      log("released by GC: $origin")
    }
    return released
  }

  /**
   * D4, at the deinitialization levels (before Godot's leak report): collect the wrappers that are
   * unreachable now, wait for their cleanup actions, and release them all, with no frame budget and
   * the parked releases included. Repeated while a round releases something (at most
   * [SHUTDOWN_ROUNDS]): a release can free an owner whose script instance then gives back what its
   * properties held, and those objects' script objects only become collectable in the next round
   * (City-Builder: the map, then its 121 structures). Returns how many it released.
   */
  fun shutdown(): Int {
    if (!enabled) return 0
    var released = 0
    for (round in 1..SHUTDOWN_ROUNDS) {
      OwnedReleaseCleaner.collectGarbage(SHUTDOWN_WAIT_MILLIS)
      var count = drain(Duration.INFINITE)
      for (owner in parked.keys.toList()) count += unpark(owner)
      released += count
      if (count == 0) break
    }
    return released
  }

  // Releases parked per plain script owner (see [drain]); main thread only. Re-checked only when
  // that owner's script is detached ([unparkLater]) or at shutdown, never by a per-frame scan.
  private val parked = HashMap<Long, MutableList<PendingRelease>>()

  /**
   * The plain script instance on [owner] was freed (its script detached or replaced): its parked
   * releases go back to the queue, so the next drain releases them. Any thread.
   */
  fun unparkLater(owner: RawSegment, instanceId: Long) {
    OwnedReleaseCleaner.enqueue(PendingRelease(owner, instanceId, null, UnparkHook))
  }

  private fun unpark(owner: Long): Int {
    val releases = parked.remove(owner) ?: return 0
    var released = 0
    for (release in releases) released += runRelease(release)
    releasedByGc += released
    return released
  }

  /** The marker [unparkLater] queues: never released itself. */
  private object UnparkHook : ReleaseHook {
    override fun beforeRelease(): Boolean = false
  }

  private const val SHUTDOWN_WAIT_MILLIS = 500L
  private const val SHUTDOWN_ROUNDS = 8

  // The instance-id lookup the freed-object check uses (task 131); when the backend could not
  // resolve it, the release goes ahead as it did before that check existed.
  private fun isLiveOrUnknown(release: PendingRelease): Boolean =
    runCatching { ObjectRuntime.isLive(release.handle, release.instanceId) }.getOrDefault(true)

  /** `<class> (created at <file>:<line>)`: the first game frame of the creation stack. */
  private fun describeOrigin(wrapper: RefCounted): String {
    val className = wrapper::class.simpleName ?: "RefCounted"
    val site = runCatching { OwnedReleaseCleaner.creationSite() }.getOrNull()
    return if (site.isNullOrEmpty()) className else "$className (created at $site)"
  }
}

/**
 * One owned `+1` waiting for its wrapper to become unreachable (task 132 D2). Holds the raw handle
 * and the instance id, never the wrapper: a reference to the wrapper would keep it reachable and
 * the cleanup would never run.
 */
@OptIn(ExperimentalAtomicApi::class)
internal class PendingRelease(
  @JvmField val handle: RawSegment,
  @JvmField val instanceId: Long,
  /** `<class> (created at <site>)` when the D5 log is on, else null. */
  @JvmField val origin: String?,
  /** Asked on the main thread right before the release; false skips it (script owner links). */
  @JvmField val hook: ReleaseHook?,
) {
  private val armed = AtomicInt(1)

  /**
   * The cleanup action, on the platform's cleaner thread: enqueue for the main thread unless
   * `close()` disarmed it first. Never calls Godot.
   */
  fun fire() {
    if (tryDisarm()) OwnedReleaseCleaner.enqueue(this)
  }

  /**
   * Atomically disarms: true for exactly one caller, the cleanup action ([fire]) or `close()`. The
   * loser must not release: the winner does.
   */
  fun tryDisarm(): Boolean = armed.compareAndSet(1, 0)
}

/** See [PendingRelease.hook]. */
internal interface ReleaseHook {
  /** Main thread, right before the drain releases: false when the release no longer applies. */
  fun beforeRelease(): Boolean
}

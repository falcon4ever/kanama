package net.multigesture.kanama.binding.runtime

import java.util.concurrent.ConcurrentHashMap

/**
 * Opt-in thread-affinity diagnostic (task 98).
 *
 * Kanama has no thread-affinity checks: the engine calls whichever callback on whichever thread it
 * likes, and the runtime assumes -- but never verified -- that script callbacks arrive on the
 * thread that ran `initialize`. `ResourceLoader.load_threaded_request` on a `.kt` script is the
 * documented way that assumption breaks (`_load` runs on a worker), and a user thread poking a
 * wrapper is the undocumented one. With `KANAMA_THREAD_DIAGNOSTICS=1` the two entry points that
 * matter ([ScriptBridge.siCall] and the `.kt` loader's `_load`) log **once per site** when they run
 * on a different thread than `initialize` did. It is a diagnostic, not an assertion: nothing is
 * refused.
 *
 * Off by default, and then the cost is one static boolean read per call.
 */
object ThreadDiagnostics {

  private val enabled = System.getenv("KANAMA_THREAD_DIAGNOSTICS") == "1"

  @Volatile private var initializeThreadId: Long = -1L
  @Volatile private var initializeThreadName: String = "?"

  private val reportedSites = ConcurrentHashMap.newKeySet<String>()

  /** Called from `KanamaBinding.initializeCallback` on every level; the first call wins. */
  fun noteInitializeThread() {
    if (!enabled || initializeThreadId != -1L) return
    val t = Thread.currentThread()
    initializeThreadId = t.threadId()
    initializeThreadName = t.name
    System.err.println(
      "[kanama:thread] diagnostics on; initialize ran on thread id=$initializeThreadId name=$initializeThreadName"
    )
  }

  /**
   * Called at the top of an engine callback. Logs once per [site] when the current thread is not
   * the one `initialize` ran on. Cheap when disabled: one static boolean read.
   */
  fun noteCallback(site: String) {
    if (!enabled) return
    val t = Thread.currentThread()
    if (initializeThreadId == -1L || t.threadId() == initializeThreadId) return
    if (!reportedSites.add(site)) return
    System.err.println(
      "[kanama:thread] $site ran on thread id=${t.threadId()} name=${t.name}, not the initialize " +
        "thread (id=$initializeThreadId name=$initializeThreadName). Kanama performs no " +
        "thread-affinity checks; see docs/game-dev/scripts.md \"Threads\". Logged once per site."
    )
  }
}

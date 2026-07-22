package net.multigesture.kanama.api

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.Runnable

/**
 * Deterministic single-thread scheduler behind Web coroutine delays and main-thread posts.
 *
 * Godot advances this scheduler once per engine frame. Work scheduled while a pump is running is
 * always deferred to a later pump, which prevents recursive same-frame continuation storms.
 */
internal class WebFrameSchedulerState {
  private data class Task(
    val id: Long,
    val sequence: Long,
    val ownerHandle: Int,
    val earliestFrame: Long,
    val dueSeconds: Double,
    val job: Job?,
    val action: () -> Unit,
  )

  private val pending = mutableListOf<Task>()
  private val ownerByJob = mutableMapOf<Job, Int>()
  private var nextTaskId = 1L
  private var nextSequence = 1L
  private var frame = 0L
  private var elapsedSeconds = 0.0
  private var currentOwnerHandle = 0

  val pendingCount: Int
    get() {
      var count = 0
      for (task in pending) {
        if (task.job == null || task.job.isActive) count += 1
      }
      return count
    }

  val registeredJobCount: Int
    get() {
      var count = 0
      for (job in ownerByJob.keys) {
        if (job.isActive) count += 1
      }
      return count
    }

  fun <T> withOwner(ownerHandle: Int, block: () -> T): T {
    require(ownerHandle > 0) { "Kanama Web frame work requires a positive owner handle" }
    val previous = currentOwnerHandle
    currentOwnerHandle = ownerHandle
    try {
      return block()
    } finally {
      currentOwnerHandle = previous
    }
  }

  fun requireCurrentOwner(): Int =
    currentOwnerHandle.takeIf { it > 0 }
      ?: error("Kanama Web frame work was scheduled outside a script callback")

  fun scheduleNextFrame(ownerHandle: Int, job: Job?, action: () -> Unit): Long =
    schedule(ownerHandle, frame + 1L, elapsedSeconds, job, action)

  fun scheduleDelay(
    ownerHandle: Int,
    delaySeconds: Double,
    job: Job?,
    action: () -> Unit,
  ): Long {
    require(delaySeconds.isFinite() && delaySeconds >= 0.0) {
      "Kanama Web delay must be finite and non-negative"
    }
    return schedule(ownerHandle, frame + 1L, elapsedSeconds + delaySeconds, job, action)
  }

  fun cancelTask(taskId: Long) {
    pending.removeAll { it.id == taskId }
  }

  fun cancelJob(job: Job) {
    pending.removeAll { it.job == job }
    ownerByJob.remove(job)
  }

  fun cancelOwner(ownerHandle: Int) {
    val jobs = ownerByJob.filterValues { it == ownerHandle }.keys.toList()
    jobs.forEach(ownerByJob::remove)
    withOwner(ownerHandle) {
      jobs.forEach {
        it.cancel(CancellationException("Kanama Web script handle=$ownerHandle was freed"))
      }

      // A coroutine canceled before its first dispatched frame still needs that inactive runnable
      // to complete its Job. Drain only cancellation bookkeeping while the owner is live; ordinary
      // posts and active user work are discarded below.
      while (true) {
        var cleanup: Task? = null
        for (task in pending) {
          if (
            task.ownerHandle == ownerHandle &&
              task.job?.isActive == false &&
              (cleanup == null || task.sequence < cleanup.sequence)
          ) {
            cleanup = task
          }
        }
        val selected = cleanup ?: break
        pending.remove(selected)
        selected.action()
      }
    }
    pending.removeAll { it.ownerHandle == ownerHandle }
  }

  fun pump(
    deltaSeconds: Double,
    isOwnerLive: (Int) -> Boolean,
    runForOwner: (Int, () -> Unit) -> Unit,
  ): Int {
    require(deltaSeconds.isFinite() && deltaSeconds >= 0.0) {
      "Kanama Web frame delta must be finite and non-negative"
    }
    frame += 1L
    elapsedSeconds += deltaSeconds

    val ready =
      pending
        .filter { it.earliestFrame <= frame && it.dueSeconds <= elapsedSeconds }
        .sortedBy(Task::sequence)
    if (ready.isEmpty()) return 0
    val readyIds = ready.mapTo(mutableSetOf(), Task::id)
    pending.removeAll { it.id in readyIds }

    var executed = 0
    ready.forEach { task ->
      check(isOwnerLive(task.ownerHandle)) {
        "Stale Kanama Web frame owner handle=${task.ownerHandle}"
      }
      val countAsExecuted = task.job?.isActive != false
      runForOwner(task.ownerHandle) {
        withOwner(task.ownerHandle) {
          task.action()
          if (countAsExecuted) executed += 1
        }
      }
    }
    return executed
  }

  private fun schedule(
    ownerHandle: Int,
    earliestFrame: Long,
    dueSeconds: Double,
    job: Job?,
    action: () -> Unit,
  ): Long {
    require(ownerHandle > 0) { "Kanama Web frame work requires a positive owner handle" }
    if (job != null) registerJob(job, ownerHandle)
    check(nextTaskId > 0L && nextSequence > 0L) { "Kanama Web frame scheduler exhausted" }
    val taskId = nextTaskId++
    pending +=
      Task(taskId, nextSequence++, ownerHandle, earliestFrame, dueSeconds, job, action)
    return taskId
  }

  private fun registerJob(job: Job, ownerHandle: Int) {
    val existingOwner = ownerByJob[job]
    check(existingOwner == null || existingOwner == ownerHandle) {
      "Kanama Web coroutine job moved from owner=$existingOwner to owner=$ownerHandle"
    }
    if (existingOwner != null) return
    ownerByJob[job] = ownerHandle
    job.invokeOnCompletion { cancelJob(job) }
  }
}

internal object WebFrameScheduler {
  private val state = WebFrameSchedulerState()

  val pendingCount: Int
    get() = state.pendingCount

  val registeredJobCount: Int
    get() = state.registeredJobCount

  fun <T> withOwner(ownerHandle: Int, block: () -> T): T = state.withOwner(ownerHandle, block)

  fun requireCurrentOwner(): Int = state.requireCurrentOwner()

  fun dispatch(job: Job?, block: Runnable) {
    val ownerHandle = requireCurrentOwner()
    if (job?.isActive == false) {
      // Coroutine cancellation dispatches a final bookkeeping continuation. Running that cleanup
      // immediately keeps cancellation deterministic and prevents inactive work from surviving
      // owner teardown; canceled user code is not resumed.
      block.run()
    } else {
      state.scheduleNextFrame(ownerHandle, job, block::run)
    }
  }

  fun post(block: () -> Unit) {
    state.scheduleNextFrame(requireCurrentOwner(), null, block)
  }

  fun scheduleDelay(delaySeconds: Double, job: Job?, action: () -> Unit): Long =
    state.scheduleDelay(requireCurrentOwner(), delaySeconds, job, action)

  fun cancelTask(taskId: Long) = state.cancelTask(taskId)

  fun cancelOwner(ownerHandle: Int) = state.cancelOwner(ownerHandle)

  fun pump(
    deltaSeconds: Double,
    isOwnerLive: (Int) -> Boolean,
    runForOwner: (Int, () -> Unit) -> Unit,
  ): Int = state.pump(deltaSeconds, isOwnerLive, runForOwner)
}

/** Deterministic state-machine probe; it never touches the live global scheduler. */
internal fun webFrameSchedulerStateProbe(): Int {
  val scheduler = WebFrameSchedulerState()
  val liveOwners = mutableSetOf(7, 8)
  val events = mutableListOf<String>()
  var result = 0

  scheduler.withOwner(7) {
    scheduler.scheduleNextFrame(7, null) {
      events += "post"
      scheduler.scheduleNextFrame(7, null) { events += "nested" }
    }
    scheduler.scheduleDelay(7, 0.2, null) { events += "delay-a" }
    scheduler.scheduleDelay(7, 0.2, null) { events += "delay-b" }
  }
  if (events.isEmpty() && scheduler.pendingCount == 3) result = result or 1

  scheduler.pump(0.1, liveOwners::contains) { _, action -> action() }
  if (events == listOf("post") && scheduler.pendingCount == 3) result = result or 2

  scheduler.pump(0.1, liveOwners::contains) { _, action -> action() }
  if (events == listOf("post", "delay-a", "delay-b", "nested")) result = result or 4

  scheduler.scheduleNextFrame(8, null) { events += "cancelled" }
  scheduler.cancelOwner(8)
  scheduler.pump(0.1, liveOwners::contains) { _, action -> action() }
  if ("cancelled" !in events) result = result or 8

  scheduler.scheduleDelay(7, 10.0, null) { events += "teardown" }
  scheduler.cancelOwner(7)
  if (scheduler.pendingCount == 0) result = result or 16

  scheduler.scheduleNextFrame(9, null) { events += "stale" }
  val staleRejected =
    runCatching {
        scheduler.pump(0.1, liveOwners::contains) { _, action -> action() }
      }
      .exceptionOrNull()
      ?.message
      ?.contains("Stale Kanama Web frame owner handle=9") == true
  if (staleRejected && "stale" !in events) result = result or 32

  return result
}

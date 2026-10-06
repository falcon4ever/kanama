package net.multigesture.kanama.web

import net.multigesture.kanama.api.WebFrameScheduler

internal data class WebScriptRecord(val scriptId: Int, val script: KanamaWebScript)

/**
 * Small generation-tagged registry for the Phase 0 bridge.
 *
 * A script handle packs a 14-bit generation and a 16-bit slot. Bit 30 remains clear so browser-
 * owned resource handles can occupy a disjoint positive-int namespace. Reusing a freed slot
 * advances its generation, so a delayed browser callback cannot accidentally target the replacement
 * object.
 */
internal class WebInstanceRegistry(
  private val createScript: (scriptId: Int, objectHandle: Int) -> KanamaWebScript
) {
  private data class Slot(
    var generation: Int = 0,
    var record: WebScriptRecord? = null,
    var pendingScriptId: Int? = null,
  )

  private val slots = mutableListOf(Slot()) // slot zero is never a valid handle
  private val freeSlots = mutableListOf<Int>()

  /**
   * Step one of creating a script: take a slot and mint its handle, but construct nothing yet.
   *
   * Construction is a second step ([construct]) because the proxy must install its callbacks under
   * the handle BEFORE the Kotlin constructor runs. A script's property initializers may call the
   * engine (`RenderingServer.getCurrentRenderingDriverName()`, `ConfigFile.create()`), as
   * GDScript's `var x = ...` initializers do, and those calls are applied through the proxy's
   * callbacks. The slot is not live ([isLive], [require]) until [construct] returns.
   */
  fun reserve(scriptId: Int): Int {
    val slotIndex = if (freeSlots.isEmpty()) allocateSlot() else freeSlots.removeLast()
    val slot = slots[slotIndex]
    slot.generation = nextGeneration(slot.generation)
    slot.pendingScriptId = scriptId
    return encode(slotIndex, slot.generation)
  }

  /**
   * Step two: construct the reserved script inside its own owner scope. A script's property
   * initializers run here, and `KanamaScript` captures the owner for its coroutine scope: the scope
   * must bind to the script being built, not to whichever callback happened to trigger the
   * construction. [around] wraps only the constructor (the caller marks the handle as the running
   * script, so engine singleton calls from an initializer have an owner). A constructor that throws
   * releases the slot before the exception propagates. Constructing a handle that was never
   * reserved, or that is already constructed, throws without touching the slot.
   */
  fun construct(handle: Int, around: (block: () -> KanamaWebScript) -> KanamaWebScript): Int {
    val slot = liveSlot(handle)
    val scriptId =
      slot?.pendingScriptId
        ?: error(
          if (slot?.record != null) "Kanama Web handle=$handle is already constructed"
          else "Kanama Web handle=$handle was not reserved"
        )
    try {
      val script = around { WebFrameScheduler.withOwner(handle) { createScript(scriptId, handle) } }
      slot.record = WebScriptRecord(scriptId, script)
      slot.pendingScriptId = null
    } catch (error: Throwable) {
      free(handle)
      throw error
    }
    return handle
  }

  /** The script id a reserved, not yet constructed handle will become, or null. */
  fun pendingScriptId(handle: Int): Int? = liveSlot(handle)?.pendingScriptId

  fun require(handle: Int): WebScriptRecord {
    val slot = liveSlot(handle)
    slot?.record?.let {
      return it
    }
    error(
      if (slot?.pendingScriptId != null) {
        "Kanama Web script handle=$handle is still constructing: its constructor cannot call back " +
          "into its own script instance"
      } else {
        "Stale Kanama Web object handle=$handle"
      }
    )
  }

  /** True once the script is constructed and until it is freed. */
  fun isLive(handle: Int): Boolean = liveSlot(handle)?.record != null

  /**
   * True from [reserve] until [free]: the script's engine node exists and its proxy is wired, so
   * the engine-side handle (`self`) answers calls, snapshots and lookups even while the Kotlin
   * constructor is still running its property initializers.
   */
  fun isLiveOrConstructing(handle: Int): Boolean =
    liveSlot(handle)?.let { it.record != null || it.pendingScriptId != null } == true

  /** Releases a constructed or reserved slot; false when it was already free (no double-push). */
  fun free(handle: Int): Boolean {
    val slotIndex = slotIndex(handle)
    val slot = liveSlot(handle) ?: return false
    if (slot.record == null && slot.pendingScriptId == null) return false
    slot.record?.script?.close()
    slot.record = null
    slot.pendingScriptId = null
    freeSlots += slotIndex
    return true
  }

  private fun allocateSlot(): Int {
    check(slots.size <= SLOT_MASK) { "Kanama Web object registry exhausted" }
    slots += Slot()
    return slots.lastIndex
  }

  private fun liveSlot(handle: Int): Slot? {
    val slotIndex = slotIndex(handle)
    if (slotIndex == 0 || slotIndex >= slots.size) return null
    val slot = slots[slotIndex]
    return slot.takeIf { it.generation == generation(handle) }
  }

  private fun nextGeneration(current: Int): Int = if (current >= GENERATION_MASK) 1 else current + 1

  private fun encode(slotIndex: Int, generation: Int): Int = (generation shl SLOT_BITS) or slotIndex

  private fun slotIndex(handle: Int): Int = handle and SLOT_MASK

  private fun generation(handle: Int): Int = (handle ushr SLOT_BITS) and GENERATION_MASK

  private companion object {
    const val SLOT_BITS = 16
    const val SLOT_MASK = 0xffff
    const val GENERATION_MASK = 0x3fff
  }
}

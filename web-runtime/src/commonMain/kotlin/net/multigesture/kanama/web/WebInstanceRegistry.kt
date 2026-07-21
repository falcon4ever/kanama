package net.multigesture.kanama.web

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
  private data class Slot(var generation: Int = 0, var record: WebScriptRecord? = null)

  private val slots = mutableListOf(Slot()) // slot zero is never a valid handle
  private val freeSlots = mutableListOf<Int>()

  fun create(scriptId: Int): Int {
    val slotIndex = if (freeSlots.isEmpty()) allocateSlot() else freeSlots.removeLast()
    val slot = slots[slotIndex]
    slot.generation = nextGeneration(slot.generation)
    val handle = encode(slotIndex, slot.generation)
    slot.record = WebScriptRecord(scriptId, createScript(scriptId, handle))
    return handle
  }

  fun require(handle: Int): WebScriptRecord {
    val slot = liveSlot(handle)
    return slot?.record ?: error("Stale Kanama Web object handle=$handle")
  }

  fun isLive(handle: Int): Boolean = liveSlot(handle)?.record != null

  fun free(handle: Int): Boolean {
    val slotIndex = slotIndex(handle)
    val slot = liveSlot(handle) ?: return false
    slot.record?.script?.close()
    slot.record = null
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

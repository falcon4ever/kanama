@file:OptIn(InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import net.multigesture.kanama.backend.ClassDBBackendContractProbe
import net.multigesture.kanama.backend.GodotObjectBackendContractProbe
import net.multigesture.kanama.backend.InputEventKeyBackendContractProbe
import net.multigesture.kanama.backend.InputEventWithModifiersBackendContractProbe
import net.multigesture.kanama.backend.InputMapBackendContractProbe
import net.multigesture.kanama.backend.InternalKanamaBackendApi
import net.multigesture.kanama.web.WebObjectId

/**
 * Web API surface for runtime input-map registration and key events (task 64 tier 3).
 *
 * Third-person's shared Player.kt registers its own bindings at startup (`hasAction` ->
 * `addAction` -> `actionAddEvent` on a constructed [InputEventKey], then `close()`), and its
 * FullScreenHandler.kt matches F11 / alt+Enter on delivered key events. Every InputMap call is
 * immediate, so the sequence composes in order and the temporary event may be closed right after
 * it is attached -- the InputMap holds its own reference by then (the create/close contract).
 */
object InputMap {
  fun hasAction(action: String): Boolean = InputMapBackendContractProbe.hasAction(action)

  /**
   * Web carries the action name only and the engine applies Godot's default deadzone (0.2). Any
   * other deadzone fails loud here rather than silently registering with 0.2.
   */
  fun addAction(action: String, deadzone: Double = 0.2) {
    require(deadzone == 0.2) {
      "Web InputMap.add_action supports only Godot's default deadzone 0.2 (got $deadzone)"
    }
    InputMapBackendContractProbe.addAction(action)
  }

  /** Attach [event] to [action]; the call fails loud if the engine did not attach it. */
  fun actionAddEvent(action: String, event: InputEvent) {
    InputMapBackendContractProbe.actionAddEvent(action, event.backendHandle)
  }

  fun eraseAction(action: String) {
    InputMapBackendContractProbe.eraseAction(action)
  }
}

/** Modifier-carrying input event (key and mouse); Web exposes the alt read the corpus uses. */
open class InputEventWithModifiers(godotObject: GodotHandle) : InputEvent(godotObject) {
  fun isAltPressed(): Boolean =
    InputEventWithModifiersBackendContractProbe(backendHandle).isAltPressed()
}

/**
 * Keyboard event. Delivered events are wrapped with [from]; runtime bindings are built with
 * [create], configured, attached through [InputMap.actionAddEvent], and released with [close].
 * The keycode setters are queued and the getters read the engine value back, so a read after a
 * write sees what the engine holds, not a Kotlin-side echo.
 */
class InputEventKey(godotObject: GodotHandle) : InputEventWithModifiers(godotObject) {
  var keycode: Long
    get() = getKeycode()
    set(value) = setKeycode(value)

  var physicalKeycode: Long
    get() = getPhysicalKeycode()
    set(value) = setPhysicalKeycode(value)

  fun setKeycode(keycode: Long) {
    InputEventKeyBackendContractProbe(backendHandle).setKeycode(keycode)
  }

  fun getKeycode(): Long = InputEventKeyBackendContractProbe(backendHandle).getKeycode()

  fun setPhysicalKeycode(physicalKeycode: Long) {
    InputEventKeyBackendContractProbe(backendHandle).setPhysicalKeycode(physicalKeycode)
  }

  fun getPhysicalKeycode(): Long =
    InputEventKeyBackendContractProbe(backendHandle).getPhysicalKeycode()

  /**
   * Release the handle [create] handed out. A ClassDB-constructed object is NODE-kind in the Web
   * bookkeeping (the ButtonGroup / ConfigFile precedent), so this is the constructed-object
   * release, not the resource release. Only call it on a created event, never on a delivered one.
   */
  fun close() {
    releaseWebConstructedObject(handle.value)
  }

  companion object {
    fun create(): InputEventKey {
      val handle =
        checkNotNull(ClassDBBackendContractProbe.instantiate("InputEventKey")) {
          "Godot could not instantiate InputEventKey"
        }
      return InputEventKey(WebObjectId(handle.backendToken().toInt()))
    }

    /** Engine-side class check, the desktop `from` contract: null when [value] is not a key event. */
    fun from(value: GodotObject): InputEventKey? =
      value
        .takeIf { GodotObjectBackendContractProbe(it.backendHandle).isClass("InputEventKey") }
        ?.let { InputEventKey(it.handle) }

    const val KEY_ESCAPE = 4194305L
    const val KEY_TAB = 4194306L
    const val KEY_ENTER = 4194309L
    const val KEY_F10 = 4194341L
    const val KEY_F11 = 4194342L
    const val KEY_SPACE = 32L
    const val KEY_A = 65L
    const val KEY_D = 68L
    const val KEY_E = 69L
    const val KEY_F = 70L
    const val KEY_Q = 81L
    const val KEY_R = 82L
    const val KEY_S = 83L
    const val KEY_W = 87L
  }
}

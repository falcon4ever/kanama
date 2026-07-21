package net.multigesture.kanama.example

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.annotations.OnInput
import net.multigesture.kanama.annotations.OnProcess
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.OnShortcutInput
import net.multigesture.kanama.annotations.OnUnhandledInput
import net.multigesture.kanama.annotations.OnUnhandledKeyInput
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotObject

/**
 * Non-tool sister script that locks in the editor-gating behaviour: without `@Tool`, neither
 * `_ready` nor `_process` should fire in editor mode (the editor uses a placeholder instance). In
 * game mode they fire normally.
 *
 * Also exercises the four input lifecycle annotations so the KSP processor wiring is covered. The
 * bodies are no-ops — without an actual InputEvent firing in headless mode they don't fire either,
 * but registration is verified by the KSP "virtuals=N" warn line.
 */
@ScriptClass(attachTo = "Node")
class NonToolScript(val godotObject: MemorySegment) {
  private var processLogged: Boolean = false

  @OnReady
  fun ready() {
    System.err.println("[kanama:kt] NonToolScript._ready fired")
  }

  @OnProcess
  fun process(delta: Double) {
    if (!processLogged) {
      processLogged = true
      System.err.println("[kanama:kt] NonToolScript._process fired")
    }
  }

  @OnInput fun input(event: GodotObject) = Unit

  @OnUnhandledInput fun unhandledInput(event: GodotObject) = Unit

  @OnShortcutInput fun shortcutInput(event: GodotObject) = Unit

  @OnUnhandledKeyInput fun unhandledKeyInput(event: GodotObject) = Unit
}

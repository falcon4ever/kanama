package net.multigesture.kanama.binding.runtime

import kotlinx.cinterop.ExperimentalForeignApi
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_print

/**
 * Godot's own output on iOS (task 132): the `print` / `push_warning` utility functions through the
 * C shim, so the runtime's diagnostics land in the same log as on desktop (`GD.print`). Falls back
 * to stdout when the engine API did not resolve (the shim records a fault).
 */
@OptIn(ExperimentalForeignApi::class)
internal object IosGodotOutput {
  fun print(message: String) {
    if (kanama_ios_godot_print(message, 0) == 0) println(message)
  }

  fun warn(message: String) {
    if (kanama_ios_godot_print(message, 1) == 0) println("WARNING: $message")
  }
}

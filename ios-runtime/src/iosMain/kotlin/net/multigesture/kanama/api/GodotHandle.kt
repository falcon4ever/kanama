package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment

/** Platform-neutral script-constructor handle; iOS stores the native Godot pointer opaquely. */
typealias GodotHandle = MemorySegment

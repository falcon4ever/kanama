package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment

/**
 * Opaque Godot object identity passed to an attachable Kotlin script constructor.
 *
 * Game code should preserve and pass this value to [KanamaScript]; it must not inspect, retain, or
 * free the underlying engine object. The JVM representation remains an FFM [MemorySegment] for
 * binary/source compatibility, while AOT backends provide their own representation under the same
 * Kotlin-facing name.
 */
typealias GodotHandle = MemorySegment

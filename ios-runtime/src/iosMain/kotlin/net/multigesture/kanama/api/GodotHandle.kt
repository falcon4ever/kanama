package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmInline

/**
 * Opaque Godot object identity: the value every Kanama wrapper and every attachable script
 * constructor takes. Source-identical with the desktop/Android and Web declarations of the same
 * fully-qualified name, so a script signature ports unchanged.
 *
 * The [segment] accessor and the public constructor are the **backend seam** — the iOS runtime and
 * the KSP-generated glue use them to cross between a raw engine pointer and a wrapper. Game code
 * never constructs or unwraps a handle.
 */
@JvmInline
value class GodotHandle(val segment: MemorySegment)

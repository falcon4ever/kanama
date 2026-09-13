package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment

/**
 * Opaque Godot object identity: the value every Kanama wrapper and every attachable script
 * constructor takes.
 *
 * Game code receives a handle, passes it on (`class Player(godotObject: GodotHandle) :
 * KanamaScript<CharacterBody3D>(godotObject, ::CharacterBody3D)`), and re-wraps another wrapper's
 * identity with it (`CharacterBody3D(body.handle)`). It must not inspect, retain, or free the
 * underlying engine object.
 *
 * Every backend declares its own `GodotHandle` under this same fully-qualified name, so a script
 * signature is source-identical on desktop, Android, iOS and Web. The JVM/Android and iOS
 * representations are a zero-cost wrapper around the backend's native pointer type; Web wraps its
 * generation-tagged registry identity instead.
 *
 * The [segment] accessor and the public constructor are the **backend seam** — they exist so the
 * runtime and the KSP-generated glue can cross between a raw engine pointer and a wrapper. Game
 * code never constructs or unwraps a handle.
 */
@JvmInline
value class GodotHandle(val segment: MemorySegment)

package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Opaque Godot object identity: the value every Kanama wrapper and every attachable script
 * constructor takes.
 *
 * Game code receives a handle, passes it on (`class Player(godotObject: GodotHandle) :
 * KanamaScript<CharacterBody3D>(godotObject, ::CharacterBody3D)`), and re-wraps another wrapper's
 * identity with it (`CharacterBody3D(body.handle)`). It must not inspect, retain, or free the
 * underlying engine object.
 *
 * This declaration is shared by every native backend (task 104 step 3): the pointer it carries is
 * [RawSegment], which each platform aliases to its own raw engine pointer type, so a script
 * signature is source-identical on desktop, Android and iOS. Web declares its own `GodotHandle`
 * under this same fully-qualified name, wrapping a generation-tagged registry identity instead.
 *
 * The [segment] accessor and the public constructor are the **backend seam** — they exist so the
 * runtime and the KSP-generated glue can cross between a raw engine pointer and a wrapper. Game
 * code never constructs or unwraps a handle.
 */
@JvmInline
value class GodotHandle(val segment: RawSegment)

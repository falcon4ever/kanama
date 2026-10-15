package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.RegisterClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.Resource

/**
 * Task 138 item 6: a `@RegisterClass` method returning a `RefCounted` hands Godot a reference of
 * its own (`register_class_object_return_driver.gd`). Godot's return slot is a `Ref<T>` (or the
 * Variant a typed call returns) that Godot unreferences later; before the fix the ptrcall upcall
 * wrote the raw pointer, so Godot dropped a reference it never took: [keptResource] destroyed the
 * resource this object still holds, and every [makeResource] result died once the collector
 * released the wrapper's own `+1` (on any thread since the fallback registers there too).
 */
@RegisterClass(parentClassName = "Node")
class RegisterClassObjectReturnProbe(val godotObject: GodotHandle) {
  private var kept: Resource? = null

  /** A fresh owned Resource whose wrapper is dropped on return. */
  fun makeResource(): GodotObject = Resource.create()

  /** The Resource this object keeps, returned while it still holds it. */
  fun keptResource(): GodotObject = kept ?: Resource.create().also { kept = it }

  /** The kept Resource's reference count (this object's reference alone: 1). */
  fun keptReferenceCount(): Long = kept?.getReferenceCount()?.toLong() ?: 0L
}

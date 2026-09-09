package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.binding.runtime.*

// GENERATED iOS companion for ShapeCast3D (scripts/generate_api_wrapper.py --write-tree, from
// IOS_EXTENSION_SECTIONS). DO NOT EDIT BY HAND. iOS-only sugar over the shared wrapper: it
// uses C-shim helpers desktop/Android do not have, so it cannot live in the shared file.

// Long-index overload (desktop ShapeCast3D exposes both Int and Long), so loops over the now-Long
// getCollisionCount() (`for (i in 0 until getCollisionCount())`) pass a Long index straight through.
fun ShapeCast3D.getCollisionPoint(index: Long): Vector3 = getCollisionPoint(index.toInt())

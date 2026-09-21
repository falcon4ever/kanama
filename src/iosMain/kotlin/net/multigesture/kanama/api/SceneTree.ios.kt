package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

// GENERATED iOS companion for SceneTree (scripts/generate_api_wrapper.py --write-tree, from
// IOS_EXTENSION_SECTIONS). DO NOT EDIT BY HAND. iOS-only sugar over the shared wrapper: it
// uses C-shim helpers desktop/Android do not have, so it cannot live in the shared file.

// SceneTree.create_tween through the TREE's own bind. Carried over from the retired hand-written
// iOS `class SceneTree : Node`, whose `override fun createTween()` existed because Node.create_tween
// on a tree handle SIGSEGVs (the task-103 "F2 fix"); SceneTree is a MainLoop now, so nothing is
// inherited and this is plain sugar. Mirrors the generated desktop SceneTree.createTween extension
// in SceneTree.jvm.kt — iOS hosts no Tween.wrap, so it cannot be a shared member.
fun SceneTree.createTween(): Tween? =
    ObjectCalls.ptrcallNoArgsRetObject(sceneTreeCreateTweenBind, segment)
        .takeIf { it.address() != 0L }
        ?.let { Tween(GodotHandle(it)) }

private val sceneTreeCreateTweenBind by lazy {
    ObjectCalls.getMethodBind("SceneTree", "create_tween", 3426978995L)
}

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

// GENERATED iOS companion for Node (scripts/generate_api_wrapper.py --write-tree, from
// IOS_EXTENSION_SECTIONS). DO NOT EDIT BY HAND. iOS-only sugar over the shared wrapper: it
// uses C-shim helpers desktop/Android do not have, so it cannot live in the shared file.

// Node.create_tween — the shared tree cannot host it (iOS has no Tween.wrap, the same gap the
// generated desktop `Node.createTween` extension in Node.jvm.kt documents), and the retired
// hand-written iOS `Node` carried it as a member, so iOS keeps it as an extension: every
// `self.createTween()` / `node.createTween()` call site resolves on both platforms
// (task 117 P1'(b2); mirrors the SceneTree entry above).
fun Node.createTween(): Tween? =
    ObjectCalls.ptrcallNoArgsRetObject(nodeCreateTweenBind, segment)
        .takeIf { it.address() != 0L }
        ?.let { Tween(GodotHandle(it)) }

private val nodeCreateTweenBind by lazy {
    ObjectCalls.getMethodBind("Node", "create_tween", 3426978995L)
}

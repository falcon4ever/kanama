package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// KANAMA-IOS-HANDWRITTEN: [glue] Engine singleton. Not retired to the generated wrapper because
// get_main_loop() returns a MainLoop, which has no iOS wrapper class, so the generator drops the
// method (object return of a non-wrapper type). SceneTree.active() depends on getMainLoop() for the
// static SceneTree.quit()/unloadCurrentScene() forms, so this stays bespoke until MainLoop is a
// wrapper (or the generator emits raw-handle object returns — task 11). is_editor_hint is generatable.
object Engine {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("Engine")
    }

    fun isEditorHint(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isEditorHintBind, singleton)

    /** Engine.get_main_loop() — the active MainLoop (the SceneTree) as a raw object handle. */
    fun getMainLoop(): MemorySegment =
        ObjectCalls.ptrcallNoArgsRetObject(getMainLoopBind, singleton)

    private val isEditorHintBind by lazy {
        ObjectCalls.getMethodBind("Engine", "is_editor_hint", 36873697L)
    }
    private val getMainLoopBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_main_loop", 1016888095L)
    }
}

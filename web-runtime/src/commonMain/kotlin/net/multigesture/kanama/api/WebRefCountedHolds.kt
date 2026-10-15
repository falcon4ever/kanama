package net.multigesture.kanama.api

import net.multigesture.kanama.web.WebObjectId

// The lifetime root of a RefCounted on Web (protocol 37, closes #332): a browser handle that names
// a RefCounted lives while a Kotlin wrapper of it is reachable, or until that wrapper's close(), as
// desktop's owned wrappers do. The wasmJs actuals talk to the bridge's FinalizationRegistry.

/**
 * Every wrapper of a browser handle announces itself (the generated [GodotObject] root's init). A
 * wrapper of a RefCounted handle registers with the bridge, which keeps the handle while the
 * wrapper is reachable or until its [RefCounted.close] (protocol 37, desktop's rule); a Node or
 * plain-Object wrapper costs one set lookup and no crossing.
 */
internal expect fun webWrapperCreated(wrapper: GodotObject, token: Int)

/** [RefCounted.close]: [wrapper]'s hold on [token] ends; the last hold's end releases it at once. */
internal expect fun releaseWebWrapper(wrapper: GodotObject, token: Int)

/** [wrapper]'s hold ends without a release: a temporary another wrapper replaced ([webRetyped]). */
internal expect fun dropWebWrapperHold(wrapper: GodotObject, token: Int)

/**
 * [typed], built over the handle of the temporary wrapper [temp] a conversion made (a load's
 * `Resource`, a generic call's `GodotObject`), takes its place: [temp]'s hold ends, so `close()` on
 * [typed] releases the handle at once when nothing else holds it.
 */
internal fun <T : GodotObject> webRetyped(temp: GodotObject, typed: T): T {
  dropWebWrapperHold(temp, temp.handle.value)
  return typed
}

/** True for a browser handle that names a RefCounted, held by the Kotlin wrappers of it. */
internal expect fun isRefCountedWebHandle(token: Int): Boolean

/** This wrapper's token for a call that crosses: a closed RefCounted wrapper fails loud here. */
@OptIn(net.multigesture.kanama.backend.InternalKanamaBackendApi::class)
internal fun GodotObject.openToken(): Int = requireOpenHandle().backendToken().toInt()

/**
 * The wrapper a decoded object handle gets (a signal argument, a Variant or generic-call result):
 * a [RefCounted] for a RefCounted handle, so `as? RefCounted`, `as? AutoCloseable` and `use { }`
 * work on it as on desktop; a [GodotObject] otherwise.
 */
internal fun webWrap(handle: WebObjectId): GodotObject =
  if (isRefCountedWebHandle(handle.value)) RefCounted(handle) else GodotObject(handle)

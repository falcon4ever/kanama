package net.multigesture.kanama.api

/**
 * iOS mirror of the desktop/Android [OwnedScriptResource]. Present so shared game code that
 * references the type compiles for the iOS (Kotlin/Native) backend; construction of the resource
 * only happens on desktop/Android (see [newScriptInstance]).
 */
class OwnedScriptResource<out T> @PublishedApi internal constructor(
    val instance: T,
    val resource: Resource,
) : AutoCloseable {
    override fun close() {
        resource.close()
    }
}

/**
 * Creating a script-backed resource from Kotlin is **deferred on iOS**.
 *
 * This declaration exists so shared game code calling `newScriptInstance<T>()` compiles for the
 * iOS backend (its sibling `kotlinScriptInstance<T>()` is already mirrored); it throws at runtime.
 * The construction path (constructObject + setScript + owned retain) lives in the desktop/Android
 * `KanamaScript` runtime and has not been ported to the iOS C shim yet. Guard the call by platform,
 * or build the resource on desktop/Android. Mirrors the enum / dictionary-property iOS deferrals.
 */
inline fun <reified T : Any> newScriptInstance(): OwnedScriptResource<T> =
    throw NotImplementedError(
        "newScriptInstance() is not supported on iOS yet — it works on desktop and Android. " +
            "Guard the call by platform, or construct the resource on those backends.",
    )

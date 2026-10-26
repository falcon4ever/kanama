package net.multigesture.kanama.ios

/**
 * The runtime declarations the code Kanama's processor generates for a project's iOS scripts calls:
 * the script descriptors, the bridge interface, the registry and the owner lookup.
 *
 * They are public only because that generated code is compiled in another module than the runtime
 * when a project builds for iOS from the Kanama addon (`buildIosScripts`): the processor and the
 * runtime klib ship together and change together. Game code never names them; the generated files
 * opt in with `@file:OptIn(InternalKanamaIosApi::class)`.
 */
@RequiresOptIn(
  level = RequiresOptIn.Level.ERROR,
  message = "This API is reserved for the code Kanama generates for a project's iOS scripts.",
)
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class InternalKanamaIosApi

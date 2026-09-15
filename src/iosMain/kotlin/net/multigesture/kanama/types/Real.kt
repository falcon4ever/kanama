@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)

package net.multigesture.kanama.types

import kotlinx.cinterop.FloatVar

/**
 * The cinterop half of Godot's `real_t` on iOS: the C variable type a `real_t` buffer is read and
 * written through (`allocArray<GodotRealVar>`, `reinterpret<GodotRealVar>`).
 *
 * `real_t`, `GodotRealArray` and the pure `GodotReal` conversions are generated into commonMain
 * (`generateKanamaReal` in the root `build.gradle.kts`) and shared by every target; iOS supports
 * single precision only, which the `compileKotlinIos*` guard enforces. The desktop/Android
 * counterpart of this file is the generated `GodotRealSegment` (Panama).
 */
typealias GodotRealVar = FloatVar

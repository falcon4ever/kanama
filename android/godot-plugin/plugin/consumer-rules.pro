# Kanama consumer ProGuard/R8 rules.
#
# These rules are inherited by any game APK that depends on the Kanama
# plugin AAR and enables R8 minification. They keep the entry points and
# reflective surfaces the Kanama runtime needs while leaving game script
# classes free to be obfuscated by the game's own configuration.
#
# STATUS: R8 minification is validated with Kanama's PanamaPort fork
# (`com.github.falcon4ever.PanamaPort:Core:0.1.3-kanama-r8.2`). Upstream PanamaPort
# v0.1.3 still has the sealed-switch R8 issue documented below, so these rules
# keep only the Kanama/Godot reflective surface and still avoid broad
# `com.v7878.**` keeps.

# Godot Android native code and plugin startup resolve parts of the Java
# template by JNI/reflection while bootstrapping the engine. Keep the Godot
# Java surface when a game APK enables R8.
-keep class org.godotengine.** { *; }
-keep class com.godot.game.** { *; }

# Godot discovers Android plugins through manifest metadata string values.
-keep class net.multigesture.kanama.android.** { *; }

# Bootstrap entry: bootstrap.c resolves init(JJJ)V by name via JNI, and
# KanamaBinding resolves its lifecycle callbacks by string via
# MethodHandles.lookup().findStatic(...). Keep this one class's member names.
-keep class net.multigesture.kanama.KanamaBinding {
    *;
}

# Panama upcall/downcall targets are resolved via MethodHandles.lookup()
# with string names; R8 cannot see those references.
-keep class net.multigesture.kanama.binding.** { *; }
-keep class net.multigesture.kanama.binding.runtime.** { *; }
-keep class net.multigesture.kanama.ffi.** { *; }

# KSP-generated registrars and registry (invoked at INITIALIZATION_SCENE).
-keep class net.multigesture.kanama.generated.** { *; }
-keep class **.KanamaRegistry { *; }
-keep class **.KanamaScriptRegistry { *; }

# Kanama annotations drive script resolution at runtime.
-keep @interface net.multigesture.kanama.annotation.** { *; }
-keepattributes RuntimeVisibleAnnotations, RuntimeInvisibleAnnotations

# PanamaPort (com.v7878.**) KNOWN LIMITATION IN UPSTREAM v0.1.3: PanamaPort's
# Android linker generates native downcall/upcall stubs with Java
# pattern-matching `switch`es over sealed type families -- the storage
# descriptors (_LLVMStorageDescriptor.LLVMStorage) and the MemoryLayout hierarchy
# (_AndroidLinkerImpl.sdToLLVMType / layoutToLLVMType / layoutName). Godot 4.7's
# R8 (AGP 8.6.1) mis-handles those switches when it optimizes the sealed types,
# so they fall through to `default -> Utils.shouldNotReachHere()`, crashing
# nativeLinker().downcallHandle() at startup (the "flickering splash + No loader
# found for res://kotlin-src/*.kt" symptom; deobfuscated 2026-06-26).
#
# This is NOT fixable from keep rules: keeping the sealed types (in any form,
# including -keep,allowoptimization) blocks the optimization that PanamaPort's
# own @CheckDiscard rules (from R8Annotations) REQUIRE -- R8 must scalarize the
# _ScopedMemoryAccess$SessionLock allocations in the _VarHandleSegmentAs*
# accessors those layouts flow through -- so the build fails the discard check.
# Keep them and the build breaks; don't and the switch breaks at runtime. The
# fix must come from PanamaPort source (or upstream) -- not consumer keep rules.
# Kanama's fork rewrites the affected switch sites to explicit `instanceof`
# branches and was validated by scripts/android_export_minified.sh on Pixel 7.
#
# PanamaPort references desktop-only / hidden symbols on code paths it does not
# take on Android; keep R8 from turning those into app warnings.
-dontwarn com.v7878.**

# newScriptInstance<T>() resolves the @ScriptClass template by T::class.qualifiedName, which must
# match the KSP-registered key (the original name, baked into the kept generated registrar). R8
# obfuscation renames game @ScriptClass classes, so the runtime name diverges and newScriptInstance
# fails in minified release with "is not a registered @ScriptClass" (confirmed on Pixel 7, 2026-07-20).
# Keep @ScriptClass class names AND their Kotlin metadata so qualifiedName stays stable; members and
# non-@ScriptClass game classes remain freely obfuscatable.
-keepnames @net.multigesture.kanama.annotations.ScriptClass class *
# Preserve Kotlin metadata on kept @ScriptClass classes so T::class.qualifiedName resolves to the
# original name at runtime (the value KSP registered as the template key).
-keepkotlinmetadata

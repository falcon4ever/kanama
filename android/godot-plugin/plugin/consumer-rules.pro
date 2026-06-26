# Kanama consumer ProGuard/R8 rules.
#
# These rules are inherited by any game APK that depends on the Kanama
# plugin AAR and enables R8 minification. They keep the entry points and
# reflective surfaces the Kanama runtime needs while leaving game script
# classes free to be obfuscated by the game's own configuration.
#
# STATUS: R8 minification is BLOCKED upstream (root-caused 2026-06-26). The
# minified GDExtension loads and runs fine up to KanamaBinding.init; it then dies
# in PanamaPort's FFI bootstrap (see the com.v7878.** note below). This is a
# PanamaPort-vs-R8 incompatibility that consumer keep rules cannot resolve, so
# these rules only cover the Kanama/Godot surface and silence PanamaPort warnings.
# Ship Android release builds WITHOUT minify until upstream resolves it.

# Godot Android native code and plugin startup resolve parts of the Java
# template by JNI/reflection while bootstrapping the engine. Keep the Godot
# Java surface when a game APK enables R8.
-keep class org.godotengine.** { *; }
-keep class com.godot.game.** { *; }

# Godot discovers Android plugins through manifest metadata string values.
-keep class net.multigesture.kanama.android.** { *; }

# Bootstrap entry: bootstrap.c resolves this class and its static
# init(JJJ)V method by name via JNI (FindClass / GetStaticMethodID).
-keep class net.multigesture.kanama.KanamaBinding {
    public static void init(long, long, long);
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

# PanamaPort (com.v7878.**) KNOWN LIMITATION: R8 minification is not currently
# supported. PanamaPort's Android linker generates native downcall/upcall stubs
# with Java pattern-matching `switch`es over sealed type families -- the storage
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
# fix must come from upstream (a PanamaPort release that keeps R8 from optimizing
# its sealed switches, or a newer R8) -- PanamaPort Core tops out at v0.1.3 on
# Maven Central today. Until then, ship Android without R8/minify (debug-signed
# release or minifyEnabled=false). scripts/android_export_minified.sh reproduces
# the failure for an upstream report.
#
# PanamaPort references desktop-only / hidden symbols on code paths it does not
# take on Android; keep R8 from turning those into app warnings.
-dontwarn com.v7878.**

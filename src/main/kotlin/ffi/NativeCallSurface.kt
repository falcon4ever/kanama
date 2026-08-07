package net.multigesture.kanama.ffi

import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.foreign.ValueLayout.JAVA_LONG

/**
 * Every native downcall adapter shape the desktop/Android backend can ever link.
 *
 * ## Why this exists
 *
 * Kanama reaches Godot through Panama downcall handles. Creating one for a shape the process has
 * not seen before makes the JVM **generate native code**: `AbstractLinker.downcallHandle` misses
 * its cache, `DowncallLinker` builds a `NativeEntryPoint` (a downcall stub blob in the code cache)
 * and `BindingSpecializer` spins a hidden class for the argument marshalling.
 *
 * Doing that while execution is already inside a Godot→JVM upcall puts native code generation
 * inside a thread-local executable-memory transition — on macOS/AArch64 in particular. That is a
 * bad shape regardless of whether it has ever misbehaved, and it is entirely avoidable: [prewarm]
 * links every shape during JNI bootstrap, before Kanama installs its lifecycle upcalls, so an
 * upcall only ever *executes* adapters that already exist.
 *
 * ## Why prewarming shapes is enough
 *
 * The linker caches on the descriptor, not on the symbol: `downcallHandle(symbol, descriptor)` is
 * `downcallHandle(descriptor).bindTo(symbol)`. Binding a symbol to an already-linked shape is pure
 * `MethodHandle` work and generates nothing. That is what makes this tractable — the backend binds
 * thousands of engine function pointers (one per builtin method, per variant constructor, per
 * utility function) but they collapse onto the handful of shapes listed below. It is also why
 * [prewarm] links each shape *unbound*: prewarming must not depend on which engine entry points
 * happen to exist in a given Godot build.
 *
 * ## Adding a call family
 *
 * If a new backend call needs a descriptor that is not in this list, add it here with the family
 * that needs it. `scripts/check_native_call_surface.py` fails the build otherwise, so introducing a
 * lazily-linked adapter is a visible choice rather than an invisible default.
 *
 * ## What this does not cover
 *
 * Upcall stubs. The linker caches the per-shape *arrangement*, but `UpcallLinker.makeUpcallStub` is
 * still called for every individual stub, so each one allocates a fresh native blob. Kanama's stubs
 * for `@RegisterClass` / `@ScriptClass` members can only be built once those classes are known,
 * which is inside the `initialize` upcall (and, for hot reload, later still) — the engine offers no
 * earlier registration point. "No upcall stub creation inside an upcall" is therefore not
 * achievable, and is deliberately out of scope here; the trace reports upcall shapes so the
 * residual stays visible.
 */
object NativeCallSurface {

  /**
   * Shape list, in prewarm order: return-by-value shapes first (grouped by arity), then void shapes
   * (grouped by arity). The order is arbitrary but fixed, so the adapter trace is stable run to
   * run.
   *
   * Labels name the call family, not a single symbol — most shapes are shared.
   */
  private val shapes: List<Pair<String, FunctionDescriptor>> =
    listOf(
      // get_proc_address, global_get_singleton, classdb_construct_object3
      "interface_lookup" to FunctionDescriptor.of(ADDRESS, ADDRESS),
      // script_instance_create3
      "script_instance_create" to FunctionDescriptor.of(ADDRESS, ADDRESS, ADDRESS),
      // variant_get_ptr_utility_function, packed_*_array_operator_index[_const]
      "utility_lookup" to FunctionDescriptor.of(ADDRESS, ADDRESS, JAVA_LONG),
      // classdb_get_method_bind
      "method_bind_lookup" to FunctionDescriptor.of(ADDRESS, ADDRESS, ADDRESS, JAVA_LONG),
      // variant_get_ptr_destructor, variant_get_ptr_keyed_setter,
      // get_variant_{from,to}_type_constructor
      "variant_type_lookup" to FunctionDescriptor.of(ADDRESS, JAVA_INT),
      // variant_get_ptr_constructor
      "variant_constructor_lookup" to FunctionDescriptor.of(ADDRESS, JAVA_INT, JAVA_INT),
      // variant_get_ptr_builtin_method
      "builtin_method_lookup" to FunctionDescriptor.of(ADDRESS, JAVA_INT, ADDRESS, JAVA_LONG),
      // variant_get_type
      "variant_get_type" to FunctionDescriptor.of(JAVA_INT, ADDRESS),
      // string_to_utf8_chars
      "string_to_utf8" to FunctionDescriptor.of(JAVA_LONG, ADDRESS, ADDRESS, JAVA_LONG),
      // get_godot_version2, object_destroy, variant_destroy, variant_new_nil, and every
      // builtin/String/StringName ptr destructor
      "one_pointer_void" to FunctionDescriptor.ofVoid(ADDRESS),
      // variant_new_copy, string[_name]_new_with_utf8_chars, classdb_unregister_extension_class,
      // every variant from/to-type constructor and every builtin ptr constructor
      "two_pointer_void" to FunctionDescriptor.ofVoid(ADDRESS, ADDRESS),
      // object_set_instance, classdb_register_extension_class_method, builtin keyed setters,
      // and the engine-supplied add-callback in get_property_state_func
      "three_pointer_void" to FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS),
      // object_method_bind_ptrcall, classdb_register_extension_class6
      "ptrcall" to FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS, ADDRESS),
      // classdb_register_extension_class_property
      "register_property" to FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS, ADDRESS, ADDRESS),
      // classdb_register_extension_class_signal
      "register_signal" to FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS, ADDRESS, JAVA_LONG),
      // object_method_bind_call -- the generic varcall path behind GodotObject.call and
      // Object::emit_signal
      "varcall" to
        FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS, JAVA_LONG, ADDRESS, ADDRESS),
      // builtin ptr methods (Array/Dictionary/StringName/... member calls)
      "builtin_ptr_call" to FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS, JAVA_INT),
      // utility-function ptr calls (GD.*)
      "utility_ptr_call" to FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, JAVA_INT),
    )

  /**
   * Link every adapter in [shapes].
   *
   * Called from `KanamaBinding.init` after `GodotFFI.bootstrap` and **before**
   * `installInitCallbacks`, which is the moment Godot gains the ability to re-enter the JVM.
   */
  fun prewarm() {
    shapes.forEach { (label, descriptor) -> GodotFFI.prelinkDowncall(descriptor, label) }
  }
}

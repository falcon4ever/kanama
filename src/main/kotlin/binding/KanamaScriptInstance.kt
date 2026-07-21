package net.multigesture.kanama.binding

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.BuiltinTypes

/**
 * Runtime data for one Godot node that has a Kanama script attached.
 *
 * One [KanamaScriptInstance] exists per node-with-script. It is stored in
 * [net.multigesture.kanama.binding.ObjectRegistry] under a long handle, which becomes the opaque
 * `p_instance_data` pointer passed to every [ScriptBridge] callback by the engine.
 *
 * The dispatch lambdas are supplied by the KSP-generated registrar for the specific
 * [@ScriptClass][net.multigesture.kanama.annotations.ScriptClass] class. Defaults for all lambdas
 * are safe no-ops / "not handled" stubs.
 *
 * @property kotlinObject The actual [@ScriptClass] Kotlin instance.
 * @property ownerObject The Godot node this script is attached to.
 * @property script The [KanamaScript] resource; set by [KanamaScript.Registrar.callInstanceCreate].
 * @property propertyListPtr Pre-built static [GDExtensionPropertyInfo] array, or NULL.
 * @property propertyCount Number of entries in [propertyListPtr].
 * @property placeholderPropertyValues Editor-only Variant storage for non-@Tool placeholder
 *   instances. Godot still needs to set/get exported values in the inspector, but user Kotlin code
 *   must not run in editor mode.
 * @property dispatchCall Called from [ScriptBridge]'s `call_func` with an interned StringName long,
 *   Variant args, arg count, return Variant pointer, and CallError pointer. Return true if handled,
 *   false if the method name was not recognised.
 * @property dispatchSet Called from `set_func`. [name] is the interned StringName long, [value] is
 *   a GDExtensionConstVariantPtr. Return true if the property was set.
 * @property dispatchGet Called from `get_func`. [name] is the interned StringName long, [ret] is a
 *   GDExtensionVariantPtr to write into. Return true if the property was found.
 * @property dispatchHasMethod Called from `has_method_func`. Return true if the class exposes a
 *   method with the given interned name.
 * @property cleanup Called when Godot frees the script instance. Generated registrars use this to
 *   release closeable Kotlin wrappers retained for script properties.
 */
class KanamaScriptInstance(
  val kotlinObject: Any,
  val ownerObject: MemorySegment,
  var script: KanamaScript? = null,
  val propertyListPtr: MemorySegment = MemorySegment.NULL,
  val propertyCount: Int = 0,
  val placeholderPropertyValues: MutableMap<Long, BuiltinTypes.StoredVariant>? = null,
  val dispatchCall:
    (
      name: Long, args: MemorySegment, argCount: Long, ret: MemorySegment, error: MemorySegment,
    ) -> Boolean =
    { _, _, _, _, _ ->
      false
    },
  val dispatchSet: (name: Long, value: MemorySegment) -> Boolean = { _, _ -> false },
  val dispatchGet: (name: Long, ret: MemorySegment) -> Boolean = { _, _ -> false },
  val dispatchHasMethod: (name: Long) -> Boolean = { false },
  val dispatchProcess: ((delta: Double) -> Unit)? = null,
  val dispatchPhysicsProcess: ((delta: Double) -> Unit)? = null,
  val cleanup: () -> Unit = {},
)

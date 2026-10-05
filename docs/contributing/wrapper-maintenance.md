# Wrapper Maintenance

This page is for Kanama maintainers working on generated wrappers, KDoc, or API
refreshes. Game projects do not need these commands.

## Generator Role

The wrapper generator turns Godot's `extension_api.json` into conservative
Kotlin wrapper drafts. Its job is to scale API coverage without hand-writing
thousands of MethodBind calls, while refusing shapes where Kanama has not
defined ABI, ownership, or marshalling policy yet.

Generated drafts are not automatically promoted. A class only becomes public
source after review, smoke coverage where practical, and reproducibility checks
against the generator output. This keeps broad wrapper growth auditable instead
of relying on one-off manual wrappers.

Refreshing generated Panama bindings from a new Godot API/header set requires
`jextract` 25+. Normal game projects do not run `jextract`; it is only part of
Kanama's API/header refresh workflow.

What the generated API promises script authors (names, types, nullability, enums, defaults,
platform parity) is stated rule by rule in
[Generated API Conventions](../reference/wrapper-conventions.md). A generator change that alters
a public signature is a source break: `scripts/check_public_signature_changes.py` fails until a
`- **Source break:**` line in `CHANGELOG.md` names the owner of each changed declaration and the
snapshots in `api-snapshots/` are regenerated with `--write` (see
[Source breaks](../reference/wrapper-conventions.md#source-breaks)). Additions and source-compatible
changes (a default added) only need `--write`; every public declaration must state its type.

## Wrapper Guardrails

Kanama's conservative wrapper generator is intentionally fail-loud. CI checks
the generated method shapes against the runtime FFM helpers, including
int32/int64 width, float/double layout, packed-array storage, generic
Array/Dictionary/Variant policy, and Callable blocking.

Generated public APIs must keep concrete Godot object types concrete. For
example, a Godot `Node3D` return should render as `Node3D?`, not
`GodotObject`, `Object`, or a raw pointer. The one handle type a public
signature may name is `GodotHandle` (the opaque wrapper/script handle, task 104). Exact Godot `Object` APIs remain
dynamic because the engine itself does not promise a more specific type.
Ownership-sensitive namespace-style types use explicit policy before default
generation. `Callable` stays blocked unless a helper has a bounded ownership
shape; `DirAccess` and `FileAccess` use dedicated handle aliases
where factory methods return nullable object handles. (`SceneTree` had one,
`SceneTreeHandle`, until task 117 P1'(b1) generated `SceneTree` itself into the
shared tree as `SceneTree : MainLoop`; `Node.getTree(): SceneTree?` names it directly now.)

**The raw engine pointer in the shared sources is `RawSegment`, never a
`java.lang.foreign` type** (task 104 step 3). It is declared ONCE, in the root
module's KMP common fragment, as
`expect sealed interface RawSegment { fun address(): Long }` in
`src/commonMain/kotlin/net/multigesture/kanama/binding/runtime/RawSegment.expect.kt`,
with the null pointer beside it as a top-level `expect val NULL_SEGMENT` (a Java
`static final` field cannot actualize an `expect` companion member). Each platform
actualizes the pair with a typealias — desktop/Android
`src/jvmMain/kotlin/binding/runtime/RawSegment.kt` to the FFM `MemorySegment` (the
PanamaPort remap rewrites it to `com.v7878.foreign.MemorySegment` and strips the
`actual` modifier), iOS `src/iosMain/.../binding/runtime/RawSegment.kt` to its
Kotlin/Native shim — so
`RawSegment` *is* the platform pointer type and the two spellings interoperate
freely. The generator emits it in the only three shared-tree shapes that name the
pointer at all: `internal fun wrap(handle: RawSegment)`, `NULL_SEGMENT` for a static
receiver or a null object argument, and `private val singleton: RawSegment by lazy`.

`NULL_SEGMENT` in the *receiver* position is the tree's static-method marker
(`_null_segment()` for an `is_static` method), and the two backends read it
differently. Desktop/Android pass the null pointer straight to
`object_method_bind_ptrcall`, which is exactly what Godot expects for a static bind.
iOS cannot: its C instance entry point `kanama_ios_godot_ptrcall` keeps a deliberate
null-instance guard, so the iOS `ObjectCalls` routes a zero instance to the separate
`kanama_ios_godot_ptrcall_static` entry point through the hand-written
`ptrcallDispatch` helper, which every hand and generated ptrcall member in that file
calls. Never "fix" a static call site by inventing a receiver, and never drop the
dispatcher: without it every shared-tree static is a silent no-op on device.

The generator's marker is not the only way a zero instance arises: **a failed singleton
lookup yields one too, and it now reaches Godot on iOS exactly as it does on desktop** —
as a null `this`, not as a no-op the C guard swallowed — so a lookup that can fail must be
checked before its result is used as a receiver. Both `ObjectCalls.getSingleton`
implementations therefore log loudly when the lookup returns null
(`[kanama][ios][kn] ERROR: getSingleton("<name>") returned null …` and the `[kanama:kt]`
equivalent on desktop; Godot's own error print for this does not reach the iOS device console
capture). They still return the null pointer unchanged — the return shape is public behaviour.

That guard is not unique to `kanama_ios_godot_ptrcall`. Over twenty
`kanama_ios_godot_*` entry points — the packed-array, array-blob, UTF-8,
Variant-scalar and object-call shapes an `ObjectCalls` helper reaches for a
non-generic return — carry the same `instance == 0` early return, and the shared tree
reaches statics through their helpers too. **The rule: every guarded entry point an
`ObjectCalls` helper calls gets the `30c949a1` split** — the body in an unguarded
`static <symbol>_dispatch(...)`, the existing symbol keeping its guard and calling it,
and a `<symbol>_static(...)` sibling (declared in `ios/include/kanama_ios.h`, the
cinterop header) calling it with a null instance — **and is named exactly once in
`ObjectCalls.kt`, inside a private `<entry>Dispatch` function that picks the `_static`
sibling when the instance is zero.** Never remove an existing guard and never change an
existing signature to make a static work. `scripts/check_ios_static_dispatch.py` (a
`local_ci.sh` stage) enforces this: it derives the guarded set from the shim itself —
by SIGNATURE (`kanama_ios_*` taking both an `int64_t method_bind` and an instance it
early-returns on), not by name prefix, which is what finally brought
`kanama_ios_classdb_instantiate_owned` into scope — and fails on any raw call, so the
next static the generator renders through a new shape is covered without anyone
remembering this paragraph.

The gate also reads the **shim's own `_dispatch` bodies**, because the Kotlin rule is
only half the property. A `_dispatch` body is the unguarded half of a split, so it is
by definition reachable with a zero instance; if it then calls a guarded entry point
instead of that entry's `_dispatch` body, the early return bites one frame below the
Kotlin dispatcher and the `_static` sibling above it is a dead end — the result cell
is never written and the call no-ops exactly as before. Two bodies shipped that way
(`..._ret_raycast_dict_dispatch` and `..._ret_object_handles_dispatch`, both calling
`kanama_ios_godot_ptrcall` instead of `kanama_ios_godot_ptrcall_dispatch`). Inside a
`_dispatch` body, call the callee's `_dispatch` body.

The per-platform generated files keep the JDK/shim spelling, and a genuine
`const void*` argument still renders as `MemorySegment`: the three desktop-only
helpers `GDExtensionManager.loadExtensionFromFunction(initFunc)`,
`OpenXRAPIExtension.transformFromPose(pose)` and
`OpenXRAPIExtension.setCustomPlaySpace(space)` take a raw native pointer, not an
object handle, and are the only public signatures left that name a
`java.lang.foreign` type. `check_wrapper_generator.py` fails if any file under
`src/commonMain` (which holds the wrapper tree) names one at all (comments excluded: the
`expect` files' KDoc names the JVM type when it explains the typealias).

Generated wrappers are held to a **single-tree drift gate**. There is one generated
wrapper tree, `src/commonMain/kotlin/net/multigesture/kanama/api`, part of the KMP
common fragment of the one multiplatform module since task 117 P4′: the JVM and both
iOS targets compile it as common code, and the Android plugin's remapped copy of
`commonMain` carries it. `compileCommonMainKotlinMetadata` compiles it on its own, so a
tree file that names a platform-only declaration fails the build — the API is
platform-neutral by construction. The few platform classes the tree names are `expect`
declarations beside it (`GodotSignal.expect.kt` with `SignalConnection`,
`MainThread.expect.kt`) with one `actual` per platform, and an `expect` declaration
carries **no default argument** (overloads instead): the Android copy skips
`*.expect.kt` and strips `actual`, so a default declared only on the `expect` would not
exist there. `scripts/check_expect_no_defaults.py` (a local_ci stage) fails on one. The shared
file of a class carries the members both native backends can call: the method set is
the iOS-audited helper-shape set (`IOS_AUDIT_ONLY`), the surface is desktop's
(`@JvmStatic`, factory helpers). Members desktop can call but iOS cannot yet (no
audited `ObjectCalls` helper for the ptrcall shape, or a wrapper type iOS does not
host) are generated as extensions into a per-class desktop companion
`src/jvmMain/kotlin/.../api/<Class>.jvm.kt`; the companion header names the helpers it
waits on, and the generated [iOS Shape Gap](../reference/generated/ios-shape-gap.md)
page lists the whole gap. When a helper lands on iOS the next regen moves the member
back into the shared file. Because those members are extensions, a script that calls
one needs the member imported by name (`import net.multigesture.kanama.api.<member>`), not only
the class import. iOS-only sugar on a shared class (`IOS_EXTENSION_SECTIONS`) is
generated the same way into `src/iosMain/.../api/<Class>.ios.kt`.

Hand-written members a generated wrapper must carry live in the **custom-section
tables** of `scripts/generate_api_wrapper.py`, keyed by class: `SHARED_MEMBER_SECTIONS`
/ `SHARED_COMPANION_MEMBER_SECTIONS` for shared classes (their text may only use what
both platforms resolve), `DESKTOP_*`/`IOS_*_MEMBER_SECTIONS` and
`*_COMPANION_MEMBER_SECTIONS` for the per-platform generated ones, and the
`*_EXTENSION_SECTIONS` above for platform sugar on a shared class.
`check_section_tables` refuses a key in the wrong table. The **factory helpers** are
not sections: a `create()` or a `from*` downcast is a row in `FACTORY_HELPERS` instead
(task 119 item 33), because every one of them was the same two shapes pasted by hand.
Add a row, not Kotlin:

```python
"SphereMesh": FactorySpec(False, (Downcast("fromResource", "Resource", False),)),
"FastNoiseLite": FactorySpec(True, (Downcast("fromResource", "Resource", False),)),
```

`create=True` emits `fun create(): C` over `constructObject` in the spelling the
render target compiles; each `Downcast(name, param_type, nullable)` emits
`fun <name>(value: <param_type>): C?` — the `isClass` form for a non-null parameter,
the `?.takeIf { … }?.let { … }` form for a nullable one (a fourth field renames the
parameter, which only `SceneMultiplayer.fromApi(api:)` needs). `render_factory_helpers`
owns the body and the comment; the block is appended to the companion object after the
class's custom section (so iOS `InputEventKey` keeps its Key constants above its
factories) and before the MethodBinds, and rows of iOS-only generated classes carry no
`@JvmStatic` (those files compile for iOS alone). `check_factory_helpers` fails the run
if the key is not a class the generator renders, or if a section still pastes the same
helper (any visibility or annotation).

The classes that are not shared are listed once, platform-tagged, in
`PER_PLATFORM_WRAPPERS` (`scripts/generate_api_wrapper.py`): for each, what desktop
does (`generated` into `src/jvmMain/kotlin/.../api`, or `hand`) and what iOS does
(`generated` into `src/iosMain/.../api`, `hand`, `collision` for a class hand-written
inside `IosGodotApi.kt` or a bespoke file, or `unsupported`). `DESKTOP_HANDSHAPED`,
`IOS_HANDSHAPED`, `IOS_HANDWRITTEN_COLLISION_CLASSES` and `IOS_UNSUPPORTED_CLASSES` are
views of that table. A class is per-platform only when the platforms genuinely host it
differently, and a `hand` cell is the only thing the gate exempts; a class joins that
table only when the generator genuinely cannot reproduce it on both platforms. Non-API
files (`GD`, `DirAccessHandle`, …) are auto-excluded, and so are the hand-written roots
described next.

**Retiring a per-platform class: a table row, not a hand class (task 129).** Every
`PER_PLATFORM_WRAPPERS` entry is a class written twice, and the two copies drift (iOS `Tween` had 7
of Godot's 28 methods until task 129 A). To generate one once:

1. Delete its entry and both hand copies (the desktop file, the iOS file or its block in
   `IosGodotApi.kt`), and create `src/commonMain/kotlin/.../api/<Class>.kt` holding only the
   `package` line: the tree universe is the set of committed wrapper files, so the placeholder is
   what puts the class in it.
2. Run `generate_api_wrapper.py --write-tree` twice (the first pass writes the class; the second sees
   its declaration, so the members that return the class itself, such as fluent setters, render
   too), then `sync_kdoc_from_godot_docs.py --write` and `./gradlew ktfmtFormat`.
3. Carry the hand copies' sugar over as data, keeping the desktop names and signatures:
   - `create()` / `from*` downcasts: a `FACTORY_HELPERS` row;
   - parameter names Godot spells differently: `PARAMETER_NAME_OVERRIDES`;
   - overloads and helpers both platforms can compile: `SHARED_MEMBER_SECTIONS[<Class>]` (member
     text) or `SHARED_COMPANION_MEMBER_SECTIONS` (companion text); `Tween`'s String-path
     `tweenProperty` and target-plus-method `tweenCallback` / `tweenMethod` are the example;
   - sugar only one platform can compile: `DESKTOP_EXTENSION_SECTIONS` / `IOS_EXTENSION_SECTIONS`.
   A member the iOS helper set cannot call yet lands in the class's generated `<Class>.jvm.kt`
   companion by itself (listed in `ios-shape-gap.md`); do not hand-write it.
4. If a desktop helper the shared tree now calls is missing `actual`, add the modifier (the expect is
   generated from the helpers the tree references). Delete what the old copies called and nothing
   else does: `IosGodot` functions, C shim entry points and their `kanama_ios.h` declarations.
5. Run `check_public_signature_changes.py`; announce any break in the CHANGELOG, fix the demos in a
   paired change, then `--write`. Drop the files from `scripts/hand_code_budget.json`
   (`check_hand_code_budget.py --write`).

**The hand-code budget.** `scripts/check_hand_code_budget.py` (a `local_ci.sh` stage) lists every
hand-written Kotlin file of the API (under an `api/` directory, or declaring the
`net.multigesture.kanama.api` package anywhere in `src/` or `web-runtime/src/`) in
`scripts/hand_code_budget.json` with its category: `seam` (the platform call mechanism),
`runtime-core` (what `extension_api.json` does not describe), `sugar` (GDScript-syntax sugar written
once) or `transitional` (should be generated; carries the task-129 parcel that retires it). Every
file has a line ratchet, and so does the hand Kotlin inside the generator tables (each `*_SECTIONS`
key here and each Web `CLASS_POLICY` string). The gate fails on an unlisted hand file, a listed file
that is gone or generated, and any growth. Only the GENERATED ENUMS regions a generator really
splices into a file are left out of its count; a GENERATED marker pair in any other API file fails
the gate. A legitimate seam, runtime-core or sugar change raises its ratchet with
`check_hand_code_budget.py --write --reason "<why>"`, which records the reason in the JSON; a
transitional file never grows, because an API addition is generated. "Generated" is the set of
`regenerate_tree()` write targets, not a file header.

**The roots are written once (task 117 P3′).** `GodotObject`, `RefCounted` and
`GodotCallable` are hand-written files in the shared tree
(`src/commonMain/kotlin/.../api/{GodotObject,RefCounted,GodotCallable}.kt`), compiled by every
platform like the generated classes they are the base of; neither platform has a copy.
`RefCounted` shares its name with a Godot class, so `SHARED_HAND_ROOTS` in the generator keeps it
out of the tree universe (and `--ios-emit-class RefCounted` refuses it); `GodotObject` (Godot's
`Object`) and `GodotCallable` carry no Godot class name and were never generated. Every member of
the three is a ptrcall through `ObjectCalls`, except four platform-bound hooks that go through the
internal **`ObjectRuntime` seam** — `internal expect object ObjectRuntime` in
`src/commonMain/.../binding/runtime/ObjectRuntime.expect.kt`, the compiler-checked contract in the
same style as `ObjectCalls` and the builtin-call `BuiltinFrame`:

| hook | called from | desktop/Android actual | iOS actual |
|---|---|---|---|
| `instanceIdOf(segment)` | `GodotObject.instanceId` (captured at construction) | `ObjectCalls.objectGetInstanceId` | `IosGodot.objectGetInstanceId` |
| `emitSignal(segment, signal, args)` | `GodotObject.emitSignal` | `binding.runtime.Signals.emitAny` | shim fast paths for one `Int`/`Long`/`Vector2i`, else Variant `emit_signal` |
| `onPropertySet(segment, property, value)` | `GodotObject.set`, `call("set", …)` | `ScriptBridge.applyOrRecordScriptPropertySet` | documented no-op |
| `onSetScript(segment, script)` | `GodotObject.setScript` (before the ptrcall) | `ScriptBridge.noteSetScript` | documented no-op |

Keep the seam that small: a hook belongs there only when a root's body cannot be a ptrcall. The
root `Object` helper shapes the roots call are part of the generated `expect object ObjectCalls`
like any other referenced helper; on iOS the generator emits them from the root `Object` methods
(`collect_root_object_shapes`) where the renderer can, and the rest are hand-written above the
`GENERATED MEMBERS` marker (listed in `IOS_HANDWRITTEN_HELPERS`). `GodotSignal` and
`SignalConnection` are the two genuinely per-platform classes left (desktop
`SignalCallbackRegistry` + bound Callable, iOS `IosCallableRegistry` + the shim's custom
Callable): since task 117 P4′ they are `expect class`es in
`src/commonMain/.../api/GodotSignal.expect.kt` with one `actual` per platform, and `MainThread`
is an `expect object` the same way — the compiler holds both platforms to every member of the
expect, and `scripts/check_actual_public_surface.py` (a local_ci stage) fails on an `actual` that
declares a public member the expect does not, which the compiler allows; so the old
`scripts/check_wrapper_parity.py` and its allowlist are retired. Their former default
arguments are overloads (`connect(target, method)` + `connect(target, method, flags)`,
`connect(target, argumentCount, callback)` + `connect(target, argumentCount, flags, callback)`,
`connectObject(target, callback)` + `connectObject(target, flags, callback)`, `await(target)` +
`await(target, argumentCount)`). The drift gate fails on a stale per-platform copy of a root (a
`<Root>.kt` in a platform api directory, or a `class <Root>` declared in any platform api file);
a non-`actual` platform copy of an `expect` class is a compile error.

Typed engine signals (task 134 D4) are generated beside each class's `Signals` constants by
`render_signal_accessors` in `scripts/generate_api_wrapper.py`: one property per signal over the
hand-written `Signal0` … `Signal5` in `src/commonMain/.../api/TypedSignals.kt`, typed by
`signal_arg_type`, named by `signal_accessor_names` (a `Signal` suffix where a member of the class,
an ancestor or a descendant already has the name). Each platform decodes the arguments through a
`SignalArgReader` (`JvmSignalArgReader`, `IosSignalArgReader`). `check_typed_signals` in
`scripts/check_wrapper_generator.py` fails when an engine signal has no accessor or one with the
wrong arity; `GodotObject`, `Tween` and `AudioStreamPlayer` carry theirs by hand. Web generates
`Signal0`/`Signal1` for its policy signals in `scripts/generate_web_wrappers.py`.

`check_single_tree` in `scripts/check_wrapper_generator.py` regenerates the whole tree
in-process (a few seconds) and fails if any generated file — a shared class, a
per-platform generated class, a companion, the `GENERATED MEMBERS` region of the iOS
`ObjectCalls.kt` (compared by member set, since ktfmt reformats it), or the gap index —
differs from a fresh regen
(behavior-comparable; `sync_kdoc_from_godot_docs.py` owns the prose), if a committed
companion is no longer produced (its gap closed), or if a shared class also has a copy
in a platform directory. A hand edit to a generated file, an un-adopted generator
improvement, or a platform-local shape change all fail here: there is one tree, so the
platforms cannot drift from each other. Re-adopt with
`python3 scripts/generate_api_wrapper.py --write-tree` followed by
`sync_kdoc_from_godot_docs.py --godot-docs … --write` (the regen output carries no
KDoc); `--emit-class <Class> --allow-overwrite` does the same for one class, writing it
into its home (the shared file plus companions, or its per-platform directory).
Android has no separate committed tree — `prepareAndroidKanamaSources` copies
`src/commonMain` (minus `*.expect.kt`, so the tree comes with it) and the desktop sources
through the PanamaPort remap, so the desktop side
of the gate covers it transitively. Adopted classes with skipped methods are only
accepted when every skip is a Godot virtual callback that belongs to the
override-registration design rather than the public ptrcall wrapper surface. How the
two trees were merged, and what the KMP `expect/actual` form would still take, is
recorded in [Shared Wrapper Tree: Design Check](shared-wrapper-tree-design-check.md).

## Typed Godot Enums and Required Returns

Since task 128 every Godot enum and bitfield (764 class enums, 22 global, 7 on builtin value types:
793 value classes on Godot 4.7.2) is a `@JvmInline value class X(val value: Long)`, and every
generated parameter, return and property Godot types `enum::X` / `bitfield::X` uses it. The model
lives in `scripts/godot_enum_model.py`; the generator, the KDoc sync, the migration script and the
gate all import it.

- **ABI unchanged.** The generator keeps the logical kind `"enum"` / `"bitfield"` for call-shape
  selection (`SCALAR_KOTLIN_TYPES` still maps both to `Long`: that is the helper's ABI type, not the
  surface), so the same `ptrcall*Long*` helpers carry them and no `ObjectCalls` helper was added.
  Only the Kotlin surface changes: `kotlin_type` returns `enum_type_ref(type)`, arguments cross as
  `name.value`, returns are wrapped `X(raw)`, vararg fixed arguments go into the Variant list as
  `.value`, and defaults render as the named constant whose value equals Godot's default
  (`X(<n>L)` when none does). The JVM erases a non-null value class to `long`, so there is no boxing.
- **Placement.** A class enum nests in its owner's generated file (`Node.ProcessMode`); globals go to
  the generated `src/commonMain/.../api/GlobalEnums.kt`, renamed only on a collision
  (`GLOBAL_ENUM_RENAMES`: `Error` -> `GodotError`, `PropertyHint` -> `GodotPropertyHint`,
  `Variant.Type` / `Variant.Operator` -> `VariantType` / `VariantOperator`). Generated code names a
  class enum owner-qualified and a global by its plain name, package-qualified only where a class
  enum of the same simple name could shadow it (`Orientation`).
- **Hand-written owners** (the `hand`/`collision` cells of `PER_PLATFORM_WRAPPERS`, the shared
  roots, and the builtin value types under `src/commonMain/.../types`) carry a marked region
  `// ===== BEGIN GENERATED ENUMS: <Class> ... =====` / `// ===== END GENERATED ENUMS: <Class> =====`
  inside the class body. `--write-tree` rewrites only that region and refuses missing, duplicate or
  misplaced markers; the drift gate checks it like any generated file (whitespace-insensitively in the
  ktfmt-formatted value types). Each target gets its own spelling: Kotlin/Native-only sources carry no
  `@JvmInline` (an `@OptionalExpectation` only common and JVM code may name).
- **Enum-only expects.** A per-platform owner whose enum a shared signature names
  (`PropertyTweener.setTrans(trans: Tween.TransitionType)`, `ZIPPacker.startFile(permissions:
  FileAccess.UnixPermissionFlags)`) cannot be seen from common code, so the generator finds such
  owners in the rendered shared tree (`find_expect_enum_owners`; today `Tween` and `FileAccess`) and
  emits `src/commonMain/.../api/<Class>.expect.kt` declaring only the nested enums; the platform
  regions become their `actual`s (one `actual` per line, so the Android copy strips them).
  `scripts/check_actual_public_surface.py` scopes the rest of those hand-written classes
  (`HAND_SURFACE_SCOPED`) and prints the cross-platform gap task 129 closes.
- **Value names** come from ONE function, `godot_enum_model.enum_value_name(prefix, godot_name)`:
  Godot's C# rule (`bindings_generator.cpp` `_determine_enum_prefix` /
  `_apply_prefix_to_enum_constants`) ported exactly, SCREAMING_CASE kept, except C#'s hard-coded
  `ERR_` prefix for `Error`: Kanama keeps `GodotError`'s full names (`GodotError.ERR_FILE_NOT_FOUND`).
  The prefix is frozen per
  enum in `scripts/enum_prefix_lock.json` (generated, do not edit): the generator uses an existing
  entry as-is, appends one only for a new enum, and a value that does not carry its enum's frozen
  prefix keeps Godot's full name, so siblings never change name across Godot versions. The
  file's "do not edit" header is the only guard against a hand edit of an EXISTING entry, by design:
  the generator trusts the lock, and `check_typed_enums.py` then holds every emitted name to it.
- **Raw values** are always constructible (`X(3L)`) and readable (`.value`); `toString()` stays the
  value-class default. Every value class implements the generated marker `GodotEnumValue`
  (`GlobalEnums.kt`): the `Any?` -> Variant encoders (desktop `BuiltinTypes.initVariantFromAny`, the
  iOS `packVariantDesc` / `encodeVariantArgs` / container `taggedValue` / script-return
  `encodeIosReturn`) map a boxed one to INT, so `set("process_mode", Node.ProcessMode.ALWAYS)` works;
  dynamic paths (`call`/`get`, Variant returns) still RETURN `Long`.
- **The KSP processor's copy** (task 128 B) is
  `processor/src/main/resources/net/multigesture/kanama/processor/godot-enums.tsv`: one row per enum
  (Godot key, Kotlin FQN, enum/bitfield, `NAME=value` pairs through the same naming function and
  lock), written by `--write-tree` beside the lock and compared by the drift gate. The processor
  needs the values, not just the names (property hint strings, constant-folded `@Export`
  defaults, the `@OverrideVirtual` typed-signature check), and a compiled API library gives KSP the
  companion getters' names only.
- **Companion values are getters** (`val ALWAYS: ProcessMode get() = ProcessMode(3L)`): no backing
  field and no companion static initialiser, the smallest JVM shape that keeps them typed (measured in
  task 128: 1.70 MB of class files for the 786 enums vs 1.98 MB with backing fields).
- **Required object returns.** A Godot object return marked `meta: "required"` renders non-null and
  goes through `binding.runtime.requireGodotReturn`, which throws
  `IllegalStateException("Godot returned null from required <Class>.<method>")`; every other object
  return stays nullable.
- **Gates.** `scripts/check_typed_enums.py` (a local_ci stage) reads the committed sources: every
  enum slot of every method tied to its Godot method through its `getMethodBind` uses its value class,
  required returns are non-null (others nullable), no generated top-level name equals a Kotlin
  default import or a public Kanama type, the lock covers every enum and every emitted value name is
  the naming function under it. `scripts/migrate_enum_constants.py --table
  docs/reference/generated/enum-migration.md --check` keeps the migration table current.

## RefCounted Return Ownership

> This section is the **ABI** truth, for contributors. The rule a *game
> developer* needs — and the owned / borrowed / engine-owned / node categories it
> produces — lives in
> [Godot API → Resource Ownership](../game-dev/godot-api.md#resource-ownership).
> Keep the two in step: this page explains **why** the `+1` exists, that page
> explains **what to do about it**.

Engine methods whose return type derives from `RefCounted` hand the ptrcall
caller a **+1 reference through the return slot**. This holds for every such
method: Godot's `PtrToArg<Ref<T>>::encode` copy-assigns into the slot and
`PtrToArg<RequiredResult<T>>::encode` nets the same +1 — `meta: "required"`
signals never-null-on-success, **not** a different ownership convention
(runtime-measured on Godot 4.7-stable; see the `Tweener ownership` probe in
`scripts/runtime_smoke.sh`). Non-RefCounted object returns (nodes, servers)
are raw pointers with no reference transfer.

The wrapper convention on desktop/Android:

- A generated wrapper returned from a RefCounted-typed method **owns that
  reference**; `close()` releases it (`unreference()` + destroy at zero).
  This matches the script-property retention path
  (`ScriptBridge.retainScriptResource`), which takes its own reference.
- **Ownership is a constructor fact** (task 132 D1). A RefCounted wrapper class
  has no `wrap`: its companion carries `wrapOwned` (the wrapper owns a `+1`)
  and `wrapBorrowed` (a view), and `RefCounted.owned(wrapper)` marks a wrapper
  built some other way (`create()`, a hand-written loader). The generator picks
  one per site (`wrap_owned_helper` / `wrap_borrowed_helper`): RefCounted
  returns, the self-return collapse and `create()` are owned; `fromHandle`,
  the `from*` downcasts and typed-Array element callbacks (`X::wrapBorrowed`,
  because `readArrayObjectsOwned` / iOS `ownedListElement` retain the element
  and mark it owned) are borrowed. Hand-written and per-platform files follow
  the same rule, and `refcounted_ownership_problems` in
  `scripts/audit_generator_shape_policy.py` checks every site.
- **Only an owned wrapper registers the GC fallback** (task 132 D2-D5,
  `binding/runtime/OwnedReleases.kt`): a cleanup holding the raw handle and
  instance id (never the wrapper) through `OwnedReleaseCleaner` -- one shared
  `java.lang.ref.Cleaner` on desktop/Android (absent before Android API 33:
  the fallback is off there), `kotlin.native.ref.createCleaner` on iOS. The
  cleanup only enqueues on a lock-free queue; the main thread drains it once
  per frame (`ScriptLanguage._frame` on desktop/Android,
  `KanamaIosRuntime.frame()` on iOS) and at SCENE deinitialization after a
  forced collection, before Godot's leak report. `close()` cancels the cleanup
  first, so a `+1` is released once; a release whose object is already gone
  (instance id no longer resolves) is skipped. `retainForKotlinWrapper()` makes
  a borrowed wrapper owned, fallback included. `close()` disarms atomically
  (`PendingRelease.tryDisarm`): if the cleanup won the race, close() leaves the
  release to the drain. A wrapper built off the engine main thread is owned but
  registers no fallback. `close()` on a wrapper that holds no reference of its
  own (a borrowed view) releases nothing and warns in debug builds; the `from*`
  downcasts take their own `+1` (`RefCounted.retained`).
- **A script object keeps its RefCounted owner alive** (task 132 blocker 1,
  `ScriptOwnerLinks.kt` on desktop/Android, `IosScriptInstance` in
  `KanamaIosRuntime.kt` on iOS): C#'s `CSharpInstance` model. The instance of a
  `KanamaScript` object holds a `+1` on its owner; the runtime's link to the
  instance (`ObjectRegistry`, `ScriptBridge`'s owner maps) is strong while the
  owner's count is above 1 and weak at 1, switched in `refcount_incremented` /
  `refcount_decremented` (which now returns false at count 1). The script object
  anchors the instance (`KanamaScript.kanamaInstanceAnchor`) and carries the
  cleanup that queues the owner's release once it is unreachable. A plain script
  class has no anchor and takes no `+1`; instead `OwnedReleases.drain` parks the
  GC releases of its owner's owned wrappers until the script is detached (or
  shutdown), the lifetime it had before the fallback. A collected instance whose owner the engine
  references again before the release ran is rebuilt from the script factory,
  as C#'s `_internal_new_managed` does, and refilled from an uncached load of
  the owner's file -- both on the instance's first use (`materialize`), never in
  `refcount_incremented` (Godot holds the ResourceCache lock and the
  `ResourceLoader` mutex there) nor on the free path (`siFree` reads the link
  without building; owner/script/placeholder metadata never builds). The drain runs owner-link
  releases first and at once, and owned-wrapper releases within a ~1 ms frame
  budget (the rest carries over); above 100,000 live registrations new owned
  wrappers stop registering. Plain-class parking is keyed by owner and
  re-checked only when that owner's script is detached (`UnparkHook`).
- **Self-returning fluent methods collapse**: when the returned address equals
  the receiver's handle, the generated method releases the duplicate reference
  and returns `this` instead of minting a second owning wrapper (chained calls
  such as `tweenAwait(...).setTimeout(...)` stay reference-neutral). The
  generator emits this pattern whenever the receiver class conforms to the
  method's return class (`Tween.setParallel(...)`, generated since task 129 A, is
  one). The `Tweener` fluent setters are `meta: "required"` in
  Godot, so since task 128 they return the **non-null** self type
  (`setTrans(...): PropertyTweener`) and a null engine return throws through
  `requireGodotReturn` (see "Required object returns" below); other generated
  object returns stay nullable. The whole `Tweener` family is generated since
  task 117 P2'.
- A method returning a **typed `Array` of RefCounted** hands back an Array that the
  ptrcall helper destroys right after decoding it, which drops the Array's own
  reference to each element — for an element nobody else holds
  (`Engine.captureScriptBacktraces()`), the object dies there. So the decode is
  the owning one: desktop `BuiltinTypes.readArrayObjectsOwned` retains each
  RefCounted element (`retainForKotlinWrapper`) before the destroy, and the iOS
  shim's `kanama_ios_godot_ptrcall_ret_object_handles` does the same in C; the
  element wrapper owns that `+1`. RefCounted-ness of an untyped element is read
  from bit 63 of its instance id (`ObjectID::is_ref_counted`), so the test costs
  no engine call. A decode that fails midway releases what it retained.
  `scripts/audit_generator_shape_policy.py` fails on any bare `readArrayObjects`
  in `ObjectCalls` (task 131).
- Wrappers minted from **Variant-path** returns or `fromHandle` casts borrow;
  a release there underflows. Self-collapse must therefore
  sit on a ptrcall object-return helper, never on `callWithVariantArgs`
  (`PropertyTweener.from` regressed exactly this way once; its generated form
  goes through `ptrcallWithVariantArgRetObject` for the same reason). That helper
  is **not** a `METHOD_CALL_SHAPE_OVERRIDES` entry — that table holds only the two
  `ClassDB` rows. It comes from a return-type-keyed special case in
  `_candidate_for_impl` (`scripts/generate_api_wrapper.py`): an `Object` return over
  a single `Variant` argument picks the ptrcall helper instead of the Variant path
  when the declared `return_type` is one of `Node` / `PropertyTweener`. Adding a
  class to that set is how a new self-collapsing `(Variant) -> Object` method gets
  the ptrcall shape.

The iOS island mirrors the same convention (task 30): the C-shim exposes
`object_destroy` (`kanama_ios_godot_object_destroy`) and `ObjectCalls.destroyObject`
wraps it. Since task 117 P3′ there is one `RefCounted` for every platform — the hand-written
shared root — carrying `close()` (unreference + destroy at zero) and the internal
`releaseHandle` primitive, so the collapse pattern above is emitted identically in the
shared tree and the iOS-only generated files. `GodotObject` is not `AutoCloseable`;
`RefCounted` declares it and owns `close()` (node/server returns are raw pointers with no
reference transfer). The shared `RefCounted`'s ownership members and the collapse emission are
locked by `check_ios_policies`, and the on-device self-test matrix carries a
`refcounted-ret-owns-plus1` refcount probe (duplicate() → refcount 1 → close()).

### Freshly created resources own their reference — *close what you create* (tasks 61/62)

`X.create()` factories return an **owning** wrapper. All backends construct via
`classdb_construct_object3` (Godot 4.7), which returns a RefCounted subtype
**already owned** (refcount 1) — Godot's own contract: *"the caller … is
responsible for decrementing it again when the object is no longer needed."* No
hand-rolled `init_ref` claim: the wrapper owns its `+1` straight out of
construction, balanced by `close()` (or `use { }`).

This is what makes handing a created resource to the engine safe. When you pass
it to a `Ref<>`-taking sink — a surface-override material, a node property,
`ResourceSaver.save` — the engine takes **its own** reference on top of the
wrapper's. A later `close()`/`use { }` then drops only the wrapper's `+1`, so the
resource survives for the engine (issue #91 — previously, on the deprecated
`construct_object2` path, `create()` was non-owning and `close()` dropped the
engine's only reference, so the material/mesh vanished from the saved scene).
Two `Ref<Material>` sinks (surface override + material override) plus a
`create_refcount=1` assertion are guarded by the `MaterialHandoffSmoke` row in
`scripts/runtime_smoke.sh`; iOS guards the same on-device in its
`ObjectCalls` self-test.

What a game developer does about this `+1` — close what you create, close what
a getter hands back, `use { }` where possible — is the one rule in
[Godot API → Resource Ownership](../game-dev/godot-api.md#resource-ownership);
this page only records why the `+1` exists on every path.

> **Issue #81 is subsumed.** Before the `construct_object3` migration (task 62),
> `create()` was non-owning, so `ResourceSaver.save`'s transient `Ref` could drop
> a fresh resource's only reference to zero and free it mid-call (issue #81, a JVM
> SIGSEGV), which a protective-reference guard in the save ptrcall helper worked
> around. With `construct_object3` the wrapper's own `+1` outlives the transient
> `Ref`, so the guard was **removed** — `save` needs no special-casing. The
> `issue81 packed_scene_save` row (`ref_after_save=1`, `alive_after_save=true`)
> proves the fresh resource survives save with no guard.

Two **dynamic** paths share this ownership problem: `ClassDB.instantiate` and
`ClassDB.class_call_static` both return a Variant that holds the fresh
instance's *only* reference, so the borrowed Variant-scalar decode would hand
back a handle that dies with the Variant (use-after-free for RefCounted classes
— GitHub PR #42; `instantiate` is also the canonical construction path for
third-party GDExtension classes, and a static factory such as
`RegEx.create_from_string` or `Image.create` has the same sole-reference
return). Both route through the same mechanism — a `METHOD_CALL_SHAPE_OVERRIDES`
entry in `generate_api_wrapper.py` that names the owned dispatch helper the
generated wrapper calls — and differ only in that helper:

- `ClassDB.instantiate` is a generated ptrcall →
  `ptrcallWithStringNameArgRetVariantScalarOwned`.
- `ClassDB.class_call_static` is a varargs `Object.call`, so its override names
  the vararg helper `callWithVariantArgsOwned`, which delegates to
  `callWithVariantArgs(owned = true)` (`render_vararg_method` reads the override
  to pick the helper, defaulting to `callWithVariantArgs`).

In both cases RefCounted results are retained **before** the return Variant is
destroyed and come back as the owning `RefCounted` wrapper (`close()` releases).
On iOS the retain must happen inside the C shim, before it destroys the return
Variant: `instantiate` uses the dedicated entry
`kanama_ios_classdb_instantiate_owned`, while `class_call_static` uses the
shared `kanama_ios_godot_object_call` with its `out_is_refcounted` out-param
(non-NULL turns on the retain). Non-RefCounted and non-object results stay
borrowed. Do **not** blanket-retain in `variantToScalar` itself — every other
dynamic object read (property gets, ordinary `call()` returns) is a borrow, and
a retain there leaks one reference per read. Other fresh-sole-reference dynamic
calls (`obj.call("duplicate")`) remain unfixed by design for now; add a
`METHOD_CALL_SHAPE_OVERRIDES` entry naming an owned helper if one bites.
Validated by the `ClassDB instantiate ownership` and `ClassDB class_call_static
ownership` probes in `scripts/runtime_smoke.sh`.

Engine-wide `MethodName`, `PropertyName`, and `SignalName` constants are
generated from `extension_api.json` separately from the class wrapper drafts:

```sh
python3 scripts/generate_name_constants.py
```

`scripts/check_wrapper_generator.py` runs the same generator in `--check` mode,
so a Godot API refresh fails loudly if the committed name constants are stale.

## iOS Generator Policy

iOS-mode rendering (`IOS_AUDIT_ONLY`: it decides the shared tree's method set and renders
the iOS-only generated files) has extra policy so a regen stays honest instead of silently
dropping or clashing. These are locked by `check_ios_policies`
in `scripts/check_wrapper_generator.py`:

- **Bare-`Object` returns.** `get_collider()`-style methods that return the root Godot
  `Object` wrap to `GodotObject?`. `wrapper_has_wrap` finds `GodotObject.wrap()` in the shared
  `GodotObject.kt` on every platform (until task 117 P3′ the iOS `GodotObject` lived inside
  `IosGodotApi.kt` and needed a special case). Without it the iOS regen drops every
  bare-`Object`-return method (a silent coverage loss), even though the
  `ptrcallNoArgsRetObject` helper is fully wired (Node returns use it).

- **Subclass-override openness** *(no live probe since task 117 P1'(b2); kept as the rule for the
  next real case)*. Where a hand-written iOS subclass overrides a generated
  method, the base method must be generated `open` — otherwise a regen drops the keyword and
  the override stops compiling. The standing example was `Node.createTween()`, opened for the
  hand-written iOS `SceneTree`, which overrode it with the correct `SceneTree.create_tween` bind
  (the FPS F2 fix). Both halves are gone now: `SceneTree` is a generated `MainLoop` since task 117
  P1'(b1), and `Node` is generated into the shared tree since P1'(b2), where `createTween` is a
  plain generated member since task 129 A generated `Tween` once. Add a real case to the class's `IOS_MEMBER_SECTIONS`
  entry (or `IOS_EXTENSION_SECTIONS` for a shared class), not by hand-editing the generated file.

- **A custom section that REPLACES a generated member.** A section normally adds members the
  generator cannot emit; when it declares the same Kotlin name AND parameter list as a generated
  one (a different return type, say), the two collide as conflicting overloads. Record the pair in
  `IOS_SECTION_REPLACED_METHODS` with its reason — the generated form is then skipped on the iOS
  render target and the reason lands in the skip report. The table is **empty today**: its one
  entry was `("Node", "get_tree")`, whose section declared `getTree(): SceneTree` non-null, and
  `Node` is generated once into the shared tree since task 117 P1'(b2) — every caller sees the
  generated `SceneTree?`.

- **Explicit class collisions.** Real Godot classes that are deliberately hand-written on iOS
  (inside `IosGodotApi.kt` or a bespoke single-class file) are the `collision` cells of
  `PER_PLATFORM_WRAPPERS` (the `IOS_HANDWRITTEN_COLLISION_CLASSES` view), each with a reason. `--ios-emit-class <that class>` logs a
  `collision:` line and skips it, instead of writing a `<Class>.kt` that duplicate-declares
  the class and breaks the compile. When a class graduates to a real generated wrapper
  (as `Time`/`InputMap`/`PhysicsServer3D` did), delete its entry so generation is allowed.
  `FileAccess` lives here: iOS hosts it as a hand-written static facade plus its own
  `FileAccessHandle` in `src/iosMain/.../api/FileAccess.kt`.

- **Explicit uncompilable classes.** The `unsupported` cells of `PER_PLATFORM_WRAPPERS` (the
  `IOS_UNSUPPORTED_CLASSES` view) list the classes whose
  generated draft cannot compile on iOS, each with its reason: `DirAccess` (its draft
  references the hand-authored `DirAccessHandle` desktop policy class iOS does not
  carry) is the only one left — `MethodTweener` sat here until task 117 P2' retired the
  hand-written iOS `Tweener` glue its fluent methods clashed with.
  `--ios-emit-class <that class>` logs an
  `unsupported:` line and skips it. Together with the collision registry these are the only
  by-design exceptions to iOS class-set parity with desktop (task 30); retire an entry by
  porting the desktop policy surface it depends on.

- **RefCounted ownership.** The shared hand-written `RefCounted`'s `close()`/`releaseHandle`/
  `checkOpen()`/`requireOpenHandle()` members, the iOS renderer's refusal to emit a second
  `RefCounted`, and the fluent self-return collapse emission (see "RefCounted Return
  Ownership" above) are locked by `check_ios_policies` so a refactor cannot silently
  reintroduce the per-call RefCounted return leak. (Until task 117 P3′ the iOS `RefCounted` was
  generated with these as custom sections.)

Two cross-platform load-bearing wrapper shapes are reproduced by explicit policy (so a regen
does not drop them), locked by `check_ios_policies`:

- **Composite default-value overrides.** `KOTLIN_DEFAULT_EXPRESSION_OVERRIDES` injects a final
  Kotlin default expression by exact `(class, method, arg)`. `Node3D.look_at` and
  `Node3D.look_at_from_position` (both `up = Vector3.UP`) are the current entries — demos
  call the short `lookAt(target)` form and rely on it. Kept
  surgical (per exact arg) so no other method silently gains a default.
- **Non-null factory.** `NON_NULL_FROM_HANDLE_CLASSES` (currently `{Resource}`) emits
  `fromHandle(handle): Resource` (non-null) so a `@ScriptClass(attachTo = "Resource")` script's
  `(GodotHandle) -> Resource` selfFactory type-checks. The nullable `wrap` helper stays on the
  raw pointer (`RawSegment` in the shared tree) — it is the `(MemorySegment) -> T?` callback
  `ObjectCalls` takes, and `RawSegment` is that same type on each platform; it is `internal`, so it
  never appears in a public signature.

The broad pre-existing regen drift (a fresh regen once changed the majority of the committed
desktop generated files — accumulated generator improvements that were never re-adopted) has been
**closed** by the task 21 convergence pass: all in-API wrappers were regenerated and re-adopted
from the honest generator, and the full drift-gate above now keeps committed == fresh regen on
every platform, so that class of drift cannot silently return.

## Coverage Triage

For wrapper work, use the coverage reports as the baseline instead of
waiting for another demo to expose a gap:

1. Refresh `docs/reference/generated/api-coverage.md` and
   `docs/reference/generated/wrapper-generator-report.md`.
2. Start from the generator skip categories and missing helper shapes.
3. Rank gaps by common Godot workflows: scene tree, resources, physics queries,
   animation, UI, input, materials, signals, RPC, and export-facing APIs.
4. Prefer removing raw escape hatches (`Object.call`, `Object.get`,
   `Object.set`, string method names, manual handle casts) from normal user
   workflows over broad class-count increases.
5. Use public demos as regression coverage after choosing a wrapper slice, not
   as the only source of wrapper priorities.

The current coverage pages already separate promoted source coverage from
generator capability. Treat low-coverage areas such as variant/dictionary
helper shapes, typed arrays, and multiplayer/editor methods as API design work:
add helper policy and audits before promoting wider wrappers.

### Long-tail triage (task 22)

**Desktop-only by design.** A member whose argument types cannot exist on iOS at all (a raw
`const void*`, a C function pointer) stays in its desktop companion file and is recorded in
`IOS_DESKTOP_ONLY_BY_DESIGN` in `scripts/generate_api_wrapper.py` with the reason. The gap page
(`docs/reference/generated/ios-shape-gap.md`) lists those members in their own "Desktop-only by
design" table and does not count them as waiting on a helper, so "0 desktop-only members waiting"
is the steady state after task 100. Unlike `BY_DESIGN_METHOD_SKIPS`, this does not remove the
member from desktop.

Once the class surface is broadly promoted, the remaining skips are a long tail
of **data-type / shape** gaps, not missing classes. A full triage of the
non-virtual skips and the skipped properties found:

- **Non-virtual method skips (65 as of task 28's start):** the
  large majority are *intentional*, not gaps —
  - **~49 root `Object` methods** (`call`/`get`/`set`/`connect`/`emit_signal`/…)
    are deliberately routed through the hand-shaped `GodotObject` policy, not
    regenerated per subclass. These are the escape hatches; keep them on
    `GodotObject`.
  - **2 `RefCounted` lifetime methods** (`reference`/`init_ref`) are
    ownership-sensitive and hand-shaped.
  - **One clean low-ABI slice landed** — a new call shape that recombines
    *already-audited* primitives and whose signature contains a type not in
    `IOS_ARG_KINDS` / `IOS_RET_KOTLIN`, so it generates on desktop+Android and
    is **cleanly iOS-skipped** (no C-shim helper, no self-test row, no device
    gate — the same guardrail Dictionary uses):
    - **`(Rect2i, Object, Color, int32, Object)`→`void`**
      (`DrawableTexture2D.blit_rect`) via `ptrcallWithRect2iObjectColorIntObjectArgs`
      — reuses the Rect2i/Object/Color/int cells. `Rect2i` is not an iOS arg kind.
  - **`Dictionary`→`Dictionary` (`GDScriptTextDocument.resolve`/`.rename`) is a
    by-design skip**, not a gap. Task 22 briefly landed it via
    `ptrcallWithDictionaryArgRetDictionary`, but the shape was **pulled from the
    generator for stability** (the helper body is still in `ObjectCalls.kt`; nothing
    emits a call to it): a Dictionary-in/Dictionary-out passthrough through the generic
    `Map<String, Any?>` helper silently drops non-String keys, so
    `audit_generator_shape_policy` keeps the `('Dictionary',) -> 'Dictionary'`
    shape out of `CALL_SHAPES` (it must stay hand-audited if ever needed). The
    two methods are editor-LSP plumbing with no game-runtime workflow.
  - **The formerly deferred ~12 landed in task 28** as audited desktop/Android
    families (all iOS-cleanly-skipped — none of the new kinds are iOS
    arg/return kinds; task 30 brought iOS to full class-set parity, and these
    per-method shape mirrors remain documented skip-report deferrals):
    - **Typed-array argument family**: RenderingDevice `blas_create` /
      `tlas_build` / `raytracing_pipeline_create`, `ImporterMesh.merge_importer_meshes`
      (`TypedObjectArray` + `TypedTransform3DArray`), `DrawableTexture2D.blit_rect_multi`,
      `RenderingServer.texture_drawable_blit_rect` (`TypedRIDArray` + `Rect2i`),
      and OpenXR `do_entity_update` — new `ObjectCalls` helpers recombining the
      audited `initArrayOfObjects` / `initArrayOfRids` / `initArray` primitives,
      registered in the shape-policy audit's dynamic helper sets.
    - **`Signal` scalar arg** (`Tween.tween_await`): admitted per-method like
      Callable (never in the generic `CALL_SHAPES` table). `BuiltinTypes.initSignal`
      builds Signal(Object, StringName) for the call and destroys it after; the
      engine keeps its own ObjectID-based copy, so no Kotlin state is retained.
      Runtime-validated in `runtime_smoke` (await resolves on real emission).
    - **Hand-shaped-class landings**:
      `OpenXRSpatialAnchorCapability.create_new_anchor`/`do_entity_update` are
      hand-hosted on their `DESKTOP_HANDSHAPED` wrapper in the exact generated
      form (draft-verified) — the committed copies had drifted to pre-4.7
      signatures on compatibility hashes and now target the current 4.7 binds.
      `EditorExportPlatform.export_project` and `Font.find_variation` were on
      that list until task 117 P1'(a): both classes left `PER_PLATFORM_WRAPPERS`,
      so the generator now emits those methods itself into
      `src/commonMain/.../api/EditorExportPlatform.kt` and `.../Font.kt` on the same
      current 4.7 binds.

    After task 28, the generator report's non-virtual skips are **only** the
    documented by-design entries above (root-Object policy, RefCounted lifetime,
    the `GDScriptTextDocument` Dictionary revert) — each carries its rationale
    verbatim in the report via `BY_DESIGN_METHOD_SKIPS` / the policy messages.
- **~505 skipped properties are NOT a marshalling gap.** Triaged:
  - **~415 are indexed / parameterized accessors** whose getter takes an
    argument (e.g. `get_list_stream(i)`, `get_param(param)`). Godot lists them as
    `foo/0`, `foo/1`, … pseudo-properties; they are not Kotlin `val`/`var`s by
    design, and the underlying getter/setter **methods are already generated and
    callable**. Not a gap.
  - **~90 have no simple own getter method** — the getter is inherited (the
    property surfaces on the parent wrapper and the getter method is inherited),
    an engine virtual `_get_*` (task 13 territory), or an indexed inherited
    getter. None are unlocked by a new marshalling shape.

  Net: **no skipped property is a "quick win behind a shape."** Property coverage
  advances only when its *getter method* becomes generatable (a new no-arg return
  shape) or via ergonomic hand-shaping, never by widening property emission.

### Virtual-return coverage (task 29)

`@OverrideVirtual` return marshalling covers **every Variant-expressible return
family** in the 4.7 virtual signature table on desktop/Android. Tasks 13 + 29
landed, per family (Kotlin return type in parentheses): the audited scalar/POD
set, `String`, `PackedStringArray` (`List<String>`), all remaining fixed-element
`Packed*Array`s (`ByteArray`/`IntArray`/`LongArray`/`FloatArray`/`DoubleArray`/
`List<Vector2>`/`List<Vector3>`/`List<Color>`), `Dictionary`
(`Map<String, Any?>`, String keys — the audited container policy), generic and
typed Arrays (`List<Any?>` — also the shape for the engine's `typedarray::*`
returns), `RID`, `Rect2`, `AABB`, `Transform2D`, `Transform3D`, `Projection`,
and `Variant` (`Any?`). Two families additionally ride existing ones by
Variant conversion: `StringName` returns are declared as `String` (the engine
casts the returned Variant at the call site, exactly as it does for GDScript),
and enum/bitfield parameters and returns are the typed value class (task 128 B:
INT on the wire, `X(raw)` in and `.value` out; a `Long` there fails the build,
and the two `meta: "required"` `_get_space_state` returns must be non-null).

**By-design excluded returns (6 virtuals):** `void*` (3) and `const Glyph*`
(3, TextServerExtension glyph buffers). Raw-pointer returns are not
Variant-expressible — GDScript cannot override these virtuals either — so they
stay outside the `@OverrideVirtual` surface permanently.

**iOS mirror bounds:** iOS value-returns cover Bool/Int/Float/Vector2/Vector2i/
Vector3/String/RID, all fixed-element `Packed*Array` families (a
`KanamaIosPackedArgDesc` + flat element buffer through the PT return scratch),
`PackedStringArray`, `Dictionary` and generic `Array` (tagged-entry blobs), and
Variant returns over that audited inner-type set (an unaudited inner value
serializes as nil — a valid Variant). The `Rect2`/`AABB`/`Transform2D`/
`Transform3D`/`Projection` returns are **desktop/Android-only for now, by
design**: Transform3D/Projection exceed the 32-byte PT return scratch (they
need the pointer-to-scratch route), the C shim has no `variant_from` boxing for
the four yet, and no 4.7 virtual returning them has arguments that are
declarable in Kotlin today — the iOS emitter warn-skips them fail-loud (the
same guardrail the task-28 desktop families use), and the mirror belongs to the
iOS wrapper-family follow-up slice.

Width-sensitive coverage: the desktop families are runtime-validated in
`runtime_smoke.sh` (the `vret` rows — GDScript `typeof()` + content checks
through the script-instance dispatch, including `Int.MAX_VALUE`, `1.0e308`, and
full-range bytes); the iOS families have Kotlin encode round-trips plus C
build/box round-trips in the on-device self-test matrix (the
`virtual-packed-*-ret` / `virtual-dictionary-ret` / `virtual-array-ret` rows).

**iOS `@Export` conversion parity:** narrow scalars
(`kotlin.Float`/`kotlin.Int` widened to the 64-bit FLOAT/INT slots), Kotlin
`enum class` exports (INT slot carrying the ordinal), and enum-list exports
(typed int Array of ordinals) use the same conversions on iOS as on
desktop/Android. The iOS emitter narrows numeric slots, clamps scalar enum
ordinals, and maps/clamps enum-list elements. Integer arrays have a dedicated
C/runtime delivery entrypoint rather than sharing object-array cells, so stale
or malformed integers cannot be misread as object handles. Scene-loaded values
and later engine-originated `Object.set` updates both take this path. The iOS
descriptor now preserves each property's hint, hint string, and usage, while
the ScriptInstance getter maps the Kotlin values back to widened scalars or
ordinal arrays for engine reads.

## KDoc Maintenance

Public Godot-backed wrappers and builtin value types carry generated KDoc
imported from Godot's `doc/classes/*.xml` files. The generated blocks include a
`Generated from Godot docs:` marker so they can be refreshed safely instead of
hand-edited.

**Docs version pin:** wrapper KDoc is synced from the **Godot 4.7.2-stable**
`doc/classes` (commit `ed1daf0bf0`), matching the shipped runtime baseline. Do
not sync from a `-rc` / `latest` tree — a mismatched docs tree reintroduces
version skew. Bump this pin when the Godot runtime baseline moves, and re-run the
full refresh (part of the [Godot Upgrade Runbook](godot-upgrade.md)).

To check whether KDoc is current against a local Godot source tree:

```sh
python3 scripts/sync_kdoc_from_godot_docs.py \
  --godot-docs /path/to/godot-4.7.2-stable/doc/classes --check
```

To refresh the whole tree (the 4.7-stable baseline refresh, task 23):

```sh
python3 scripts/sync_kdoc_from_godot_docs.py \
  --godot-docs /path/to/godot-4.7.2-stable/doc/classes --write
```

The script uses `$GODOT_DOCS` when set, otherwise it defaults to
`godot/doc/classes` relative to the current working directory. Use
`--godot-docs /path/to/godot/doc/classes` when syncing against a different
Godot checkout. Use `--classes Node,Node3D,Tween` for a focused refresh while
working on one wrapper slice, or `--scope types --classes Vector3` when
working on builtin values. By default the api scope covers the shared tree, the
desktop per-platform files and their `<Class>.jvm.kt` companions; `--api-dir`
narrows it to one directory. Keep `--types-dir` (`.../kanama/types`) separate —
pointing it at an api dir mislabels node classes as value types.

The refresh is **gate-neutral**: `check_full_drift_gate` strips
`Generated from Godot docs:` blocks before comparing (`comparable_source`), so a
KDoc refresh never affects drift-gate correctness and only touches comments. The
sync replaces an existing generated block in place (multi-line **or** the older
single-line `/** … */` form), so re-running is idempotent.

## ObjectCalls: one object per platform, one generated region

`net.multigesture.kanama.binding.runtime.ObjectCalls` is the only seam between the shared
wrapper tree and the engine, and it exists once per platform under that one
fully-qualified name: `src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt` over Panama/FFM for
desktop (Android gets it through the source remap), and
`src/iosMain/.../binding/runtime/ObjectCalls.kt` over the C shim for iOS. Since task 104
step 3 both are `actual object ObjectCalls`, actualizing the GENERATED
`expect object ObjectCalls` in
`src/commonMain/kotlin/net/multigesture/kanama/binding/runtime/ObjectCalls.expect.kt` — all
1,450 helpers the tree calls. Until task 117 P4′ seven were documented exceptions (six named a
wrapper class the common fragment could not see, `callWithVariantArgs` carried an `owned`
default); `scripts/check_objectcalls_parity.py` now fails on any exception. The compiler, not a
script, is the parity contract. A helper whose desktop signature carries a default argument stops
`--write-tree`: split it into overloads, as `callWithVariantArgs` (borrowed) /
`callWithVariantArgsOwned` were.

**One file per platform.** The iOS file is hand-written except for a marked region at the
end of the `object ObjectCalls` body:

```kotlin
  // ===== BEGIN GENERATED MEMBERS (scripts/generate_api_wrapper.py — do not edit) =====
  ...  1,384 generated helper members + the PT_* ptrcall tag table
  // ===== END GENERATED MEMBERS =====
```

`generate_api_wrapper.py --write-tree` rewrites only what is between the markers, and
refuses to run when the pair is missing, duplicated or inverted. Everything above the BEGIN
marker is hand-written: the override set (`IOS_HANDWRITTEN_HELPERS`), the read-back and
descriptor machinery the generated bodies call (`ptrcallRetUtf8`, `pack<Kind>Desc`, …) and
the device self-test. The PT_* tags are declared once, inside the region: the generator
emits the whole table (not just the tags a run happens to use) because both halves read it.

Before task 104 step 3 parcel B the generated helpers were extension functions
`fun ObjectCalls.x(...)` in a separate `ObjectCallsGenerated.kt`. They are members now
because the rest of step 3 makes this object an `expect object`, and common code can only
see MEMBERS of one — a platform-only extension is invisible from `src/commonMain`.
Extensions are still fine for a helper nothing in the shared tree references.

**Parameter names are desktop's.** An `expect` member is actualized only by a member with
the same parameter names, and `foo(bar = 1)` does not compile against an `actual` that
spells the parameter differently. The generator reads the desktop file's signature for each
helper name and applies it positionally
(`desktop_objectcalls_signatures` / `_desktop_parameter_rename`); it refuses — keeping the
generated names and printing the helper in the run summary — when desktop has no such
helper, when the arities disagree, or when a generated parameter is not one of the
`a<N>` / `a<N>Object` / `a<N>Method` / `fromHandle` shapes it knows how to map, so a
surprise can never silently relabel an argument.

**The gate.** `scripts/check_objectcalls_parity.py` (a local_ci stage) parses both objects
and requires every helper the shared tree calls to be a member on both platforms with equal
arity and equal parameter names in order. It compares names, never types: the raw engine
pointer is `MemorySegment` on desktop/Android and the Kotlin/Native shim on iOS by design
(`RawSegment`), and `expect`/`actual` resolves that through the typealias. It reports
missing-on-desktop, missing-on-iOS, extension-not-member, param-count and name-mismatch,
and `--list` prints the contract. Overloads compare as sets of parameter-name tuples, and
iOS may declare more than desktop needs — an `actual object` may carry extra members, which
is how the iOS-only `ptrcallNoArgsRetByteArray(methodBind, instance, sizeHint)` coexists
with the two-argument shape desktop declares.

The drift gate (`check_single_tree`) compares the region's MEMBER SET rather than its bytes,
because the file is ktfmt-formatted outside the region and so the region gets reformatted
after every regen; `--write-tree` skips rewriting the file for the same reason when the
member set has not changed. `scripts/fixtures/wrapper_generator/ios/ObjectCalls.generated-members.kt.txt`
locks the region's rendering for one class (Node3D) byte-for-byte.

## Value-Type Audits

The 19 builtin value types are ONE hand-written set under
`src/commonMain/kotlin/net/multigesture/kanama/types` since task 104 step 2 — the
the module's JVM and iOS targets and the Android copy task all compile those
files, and neither platform keeps a copy. Only `Real.kt` is per platform
(generated at build time on desktop, hand-written on iOS, written by the plugin
build script on Android) — as `GodotHandle` was until task 104 step 3 moved it into
the shared tree over `RawSegment`.

A shared body reaches the engine only through the builtin-call facade
`net.multigesture.kanama.binding.runtime.BuiltinFrame` / `BuiltinMethod`, declared once as
`internal expect class`es in `src/commonMain/.../binding/runtime/BuiltinFrame.expect.kt` and
implemented over Panama/FFM in `src/jvmMain/kotlin/binding/runtime/BuiltinFrame.kt` (Android gets
it through the source remap) and over the C shim in `src/iosMain/.../binding/runtime/BuiltinFrame.kt`,
so adding a member to one half without the other is a compile error at the edit. A call allocates
nothing: the generated member writes its base and arguments into the thread's frame slots and
calls through one constant downcall (task 134 B). The `VT_*`/`PT_*` wire numbers are common
`const val`s in `BuiltinTags.kt` — values, which an `expect` declaration cannot carry.

Which methods are engine-computed is a policy, not a preference: a method with a
transcendental (sin/cos/atan2/exp/pow) or a long body — Euler angles,
orthonormalization, shortest-arc slerp — calls the builtin; exact arithmetic
(operators, `dot`, `lerp`, `moveToward`, rect/AABB tests, `is_normalized`'s epsilon
comparison) is plain Kotlin with Godot's operand order, so it costs no round trip
and is never slower than GDScript. Value-type helpers that mirror
Godot builtin methods should call the matching builtin unless the local
implementation is deliberately proven equivalent. Use the audit script when
editing `types/*.kt`:

```sh
python3 scripts/audit_value_type_wrappers.py --api extension_api.json
```

The audit is report-only by default, and `--strict` is wired into local CI. It
also checks the builtin float ABI in its new shape: Godot's ptr-ABI passes a
Variant `float` argument as an 8-byte double whatever the engine's `real_t`
precision, so such an argument must be written with `putDouble`, never
as a `putReal` component. Reviewed local scalar
formulas are allowlisted in the script; any newly added Godot-named value helper
that does not call the builtin should be treated as suspicious until it is either
bound to Godot's builtin or deliberately added to the reviewed list with focused
parity evidence.

### Generated operators and methods (task 134 B)

Every builtin operator and method of the value types is generated by
`scripts/generate_builtin_ops.py` from `extension_api.json` into two marked regions per class
(`GENERATED BUILTIN MEMBERS` in the class body, `GENERATED BUILTIN STATICS` in the companion);
the rest of each file stays hand-written, and a hand-written member with the Godot name wins (the
generator skips it). Operators and the methods in the generator's `PURE_METHODS` are Kotlin:
component-wise ones come from templates, composite ones call the hand-ported formulas in
`types/BuiltinFormulas.kt` (Godot's operand order, `real_t` width). Every other method calls the
engine through the thread's `BuiltinFrame` (base and arguments written by Godot type through the
generated `types/BuiltinMarshalling.kt`: `real_t` components, `int32` for `Vector2i`-style types,
float32 for `Color`, a `String` built for the call; one `BuiltinMethod` constant per method). `types/BuiltinScalarOperators.kt` holds `2.0 * v` and
`points * transform`. After editing the generator or a value type:

```sh
python3 scripts/generate_builtin_ops.py --write
./gradlew ktfmtFormat
python3 scripts/sync_kdoc_from_godot_docs.py --godot-docs "$GODOT_DOCS" --scope types --write
python3 scripts/check_builtin_coverage.py --report
```

The generator also writes the runtime smoke's probe pair (`example_project/BuiltinParitySmoke.kt`
and `builtin_parity_ref.gd`): every Kotlin-computed member over 256 fixed-seed random inputs and
every engine-backed one over 8, hashed and compared with GDScript; a changed formula that is not
Godot's to the bit fails the smoke and names the member. The Web value types get the same
component-wise members (`web-runtime/.../types/WebValueTypes.kt`), checked against Godot's hashes
recorded in `scripts/fixtures/builtin_parity_expected.json` by `WebBuiltinParityTest`; after a
change to the probe, record them from a runtime smoke log with
`python3 scripts/generate_builtin_ops.py --record-parity /tmp/kanama_runtime_smoke.log`, then
`--write` (the runtime smoke re-checks the recording with `--verify-recorded`).
`scripts/check_builtin_coverage.py` fails when an API operator or method has neither a Kotlin
member nor a recorded reason.

For scalar Godot `float` method arguments, the ptrcall helper layout audit is
the ABI guard (it absorbed the narrower `audit_scalar_float_abi.py`, retired in
task 99 — see the [Gates Index](../reference/generated/gates.md)):

```sh
python3 scripts/audit_ptrcall_helper_layouts.py
```

Godot's scalar `float` ptrcall slot is 64-bit (`JAVA_DOUBLE`) even when
single-precision `real_t` value components are float32 in engine buffers (and in the
value types' storage; `GodotRealSegment.readRaw`/`writeRaw` move them); every helper with a
scalar float slot must use `JAVA_DOUBLE`, and only `Color` component storage may
use `JAVA_FLOAT`.

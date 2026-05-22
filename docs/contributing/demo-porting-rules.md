# Demo Porting Rules

This guide is for contributors porting Godot demos or writing Kanama gameplay
code. It records rules learned from the Platformer, Match-3, FPS, and Racing
ports.

## Start Every Session

Read `CONTRIBUTING.md` before changing code and confirm the current task from
the issue, pull request, or maintainer handoff. When you finish a slice, run the
relevant validation and commit both Kanama and demo-repo changes if both repos
changed.

Check the original GDScript and scene files before inventing a Kotlin shape.
If a port exposes missing framework support, prefer fixing Kanama over adding
a port-specific workaround that changes the original semantics.

## High-Risk Changes

Use extra review time for final audit design, FFM marshalling core changes,
Array/Dictionary/Variant policy, ownership/refcount semantics, or review of
large risky changes before release. These areas are where small mistakes can
corrupt data at the Godot/Kotlin boundary.

Extra review can help, but keep the scope narrow: read-only ABI sweeps,
ownership-policy review, Array/Dictionary/Variant policy comparison, or
reviewing a specific FFM change before commit. Broad ABI fixes should have a
precise owner and a focused integration review.

## Script Classes

Kanama scripts are normal Kotlin objects attached to Godot objects:

```kotlin
package fps

@ScriptClass(attachTo = "CharacterBody3D")
class Player(godotObject: MemorySegment) :
    KanamaScript<CharacterBody3D>(godotObject, ::CharacterBody3D) {

    @OnReady
    fun ready() {
        val camera = self.requireAs("Camera3D", ::Camera3D)
    }
}
```

Use a named package in demo scripts. The Kotlin `this` value is the JVM script
object, not the Godot node. Prefer `KanamaScript<Self>` for new scripts so
`self` is typed to the `attachTo` class. Use `selfAs(::NodeType)` for another
compatible wrapper on the same Godot object. Treat
`selfAs(godotObject, ::NodeType)` as legacy compatibility syntax; new ports
should use `KanamaScript<T>`.

If a script directly extends `KanamaScript<...>`, keep the generic type aligned
with `@ScriptClass(attachTo = "...")`. KSP emits a warning for mismatches. Treat
that warning as a likely porting mistake unless the script intentionally wants a
looser `self` type.

`@ScriptClass(attachTo = "...")` names the Godot base class the script is
attached to. `@GlobalClass` makes a script usable as a typed value in Godot's
inspector, including custom resource scripts such as `Weapon` and node scripts
such as `Vehicle`.

Inherited Kotlin methods are not registered automatically for subclassed
scripts yet. If a subclass must expose a callback or lifecycle method to Godot,
add an annotated forwarding method on that subclass.

## Exports And Scene Data

Keep exported properties aligned with the original GDScript type:

```kotlin
@ScriptProperty
var crosshair: TextureRect? = null

@ScriptProperty
var target: Vehicle? = null
```

Keep readable GDScript-style defaults when Kanama supports them. For example,
prefer `Math.toRadians(-60.0)` over a long radian literal when porting
`deg_to_rad(-60.0)`; KSP preserves that as a static inspector default.

Godot may serialize exported object references as `NodePath(...)` in `.tscn`
files, but that does not mean the Kotlin property should be a `NodePath`. If
the GDScript declared `@export var crosshair: TextureRect`, keep the Kotlin
property as `TextureRect?`. Use `NodePath` only when the original script
exposed a path value intentionally.

For GDScript `PackedStringArray` exports used as string lists, use
`List<String>` in Kotlin. Kanama exports that as `Array[String]` and accepts
scene-authored `PackedStringArray` values during load.

When the original GDScript configures an instantiated scene before
`add_child(...)`, keep that flow. `Object.set(...)` values are applied to the
Kanama script instance once Godot creates it:

```kotlin
val bullet = bulletScene?.instantiate()
bullet?.call("set", "velocity", direction * speed)
bullet?.call("set", "shooter", self)
parent.addChild(bullet)
```

For custom resources, use same-project `@GlobalClass` resource scripts and
typed collections:

```kotlin
@ScriptClass(attachTo = "Resource")
@GlobalClass
class Weapon(val godotObject: MemorySegment) {
    @ScriptProperty
    var crosshair: Texture2D? = null
}

@ScriptProperty
var weapons: List<Weapon> = emptyList()
```

Kanama resolves live Kotlin script resource instances when reading exported
resource references, so inspector-authored fields are preserved.

For runtime-created custom resources, do not translate GDScript
`DataMap.new()` into `ResourceLoader.load("res://...kt").call("new")`. Generic
Kanama script resources do not expose the GDScript-only `new` API. Create a
plain `Resource`, attach the loaded script with `setScript`, and resolve the
typed Kotlin instance:

```kotlin
val script = ResourceLoader.load("res://kotlin-src/DataMap.kt") ?: error("missing script")
val owner = Resource.create()
owner.asObject().setScript(script)
val map = owner.asObject().kotlinScriptInstance<DataMap>() ?: error("missing DataMap")
```

Treat Kanama `.kt` scripts as trusted executable project code. This is the
same security model as GDScript, C#, native GDExtensions, and editor plugins;
do not run untrusted Godot projects with their addons/extensions enabled.

Use `@ExportGroup("...")` and `@ExportSubgroup("...")` on the first exported
property in a group when matching grouped GDScript inspector layout.

Use annotation constants for inspector metadata instead of private magic
numbers in gameplay scripts:

```kotlin
@Export(hint = PropertyHint.RANGE, hintString = "0,100,1")
var health = 100
```

## Mobile Runtime Warmup

Android can expose first-use hitches that are hard to see on desktop. When a
mobile control path can trigger a scene, audio stream, particle effect, or
generated mesh for the first time, load or cache that work during startup or a
noninteractive pause screen instead of doing it on the first button press.

Prefer a small project-owned warmup helper for demo ports. Cache
`PackedScene`s that are instantiated during combat, keep frequently played
`AudioStream`s warm, and avoid synchronous `load(...)` calls in gameplay
callbacks. For generated visuals such as aiming arcs or trajectory ribbons,
reuse or throttle mesh generation rather than committing a new mesh every frame.

Keep desktop warmup lighter than mobile warmup unless the demo has a measured
desktop hitch. Loading and caching `PackedScene` or `AudioStream` resources is
safe on a pause screen, but adding hidden scene instances to a paused tree and
immediately `queueFree()`-ing them can defer renderer/physics cleanup until the
player resumes. On desktop Forward+/Metal this can show up as a crash or
shader/resource teardown noise on the first unpause. Gate heavy instance pools
or instantiate-and-free warmup behind mobile feature checks, and keep desktop
startup to resource caches unless a targeted smoke test proves the heavier path
is needed.

## Resource And Tween Ownership

Treat live Godot `Resource` and `RefCounted` values as ownership-sensitive.
Do not close a value just because a Kotlin wrapper is in scope if Godot is
expected to keep using the underlying object.

For tweens, this means never closing a `Tween` or `Tweener` immediately after
scheduling animation work:

```kotlin
// Correct: Godot owns and runs the active tween.
val tween = self.createTween() ?: return
tween.tweenProperty(icon, "modulate", Color.WHITE, 0.2)

// Wrong: this can release the live tween before the renderer/UI consumes it.
tween.tweenProperty(icon, "modulate", Color.WHITE, 0.2)?.close()
tween.close()
```

If the script tracks a tween to cancel it later, use `kill()` and drop the
Kotlin reference. Closing active tweens early can show up as native renderer
crashes on desktop Metal/MoltenVK rather than as a clean Kotlin exception.

The same rule applies when assigning generated resources into scene nodes.
For example, after `meshInstance.setMesh(mesh)`, do not immediately
`mesh.close()` unless the wrapper/API contract says the setter retained a
separate reference. Prefer keeping the resource live for as long as the scene
node can render it. Calls to `Resource.close()` and `RefCounted.close()` are
annotated with `@ManualGodotLifetimeApi`; demo gameplay should not opt in
unless the value is clearly caller-owned and the original GDScript had an
equivalent explicit release.

## Signals And Callbacks

Scene-connected methods need to be callable from Godot:

```kotlin
@RegisterFunction("_on_body_entered")
fun onBodyEntered(body: GodotObject) {
    collect()
}
```

The string is the method name stored in the scene connection. Methods named
with a leading underscore are just ordinary scene callback names unless they
are Godot lifecycle methods.

Use lifecycle annotations for Godot lifecycle hooks:

```kotlin
@OnReady
fun ready() {
    ...
}

@OnPhysicsProcess
fun physicsProcess(delta: Double) {
    ...
}
```

Declare custom signals with `@Signal` and emit them through the generated
helper:

```kotlin
@Signal
fun healthUpdated(value: Long) = Unit

PlayerSignals.healthUpdated(this, health)
```

Animation call-track methods and custom signals are separate concepts. If
GDScript declares a signal and an animation track calls a method that emits it,
map both pieces explicitly:

```gdscript
signal stepped

func _step() -> void:
    stepped.emit()
```

```kotlin
@RegisterFunction("_step")
fun step() {
    CharacterSkinSignals.stepped(this)
}

@Signal
fun stepped() = Unit
```

Signals are instance-scoped. A receiver knows which object emitted the signal
through the scene connection or explicit `connect` call, not only by the
signal name.

Use generated signal and method names when connecting or emitting Kanama script
signals without the generated helper:

```kotlin
events.signal(EventsNames.Signals.flagReached).connect(self, argumentCount = 0) {
    onFlagReached()
}

events.signal(EventsNames.Signals.killPlaneTouched).emit(body)
```

Use built-in signal constants for engine signals:

```kotlin
area.signal(Area3D.Signals.bodyEntered).connectObject(self) { body ->
    ...
}
```

Avoid raw string signal and method names in demo ports when generated constants
exist. Raw strings are acceptable only for dynamic GDScript/engine calls that
Kanama cannot type yet, and the parity audit should record why.

Do not use `GodotObject.call("get", ...)`, `GodotObject.call("set", ...)`, or
string method calls in demo gameplay code when the target is a known Godot
class or known Kanama script. Add or use a typed Kanama wrapper first, then
update the demo to call the typed method/property. Accept dynamic calls only at
true dynamic boundaries, such as untyped Godot callback bodies, intentionally
duck-typed damage receivers, mixed GDScript/Kanama edges, or smoke-test probes.

For `AnimationTree` parameter paths, use `AnimationMixer.getParameter`,
`setParameter`, and `getStateMachinePlayback`. Keep the parameter path strings
as named constants close to the original GDScript, but do not leak raw
`Object.get`/`Object.set` calls into demo scripts.

For known Kanama scripts attached to nodes, resolve the script instance with
`kotlinScriptInstance<T>()` and call the Kotlin method directly. Do not call
script methods through `GodotObject.call("method_name")` unless the original is
intentionally duck typed.

For GDScript `await get_tree().process_frame`, use
`MainThread.awaitNextFrame()` inside a `KanamaScope` coroutine. That helper is
the Kanama equivalent of yielding until Godot's next process frame while
continuing on the main thread.

Do not schedule engine-wide follow-up work in a coroutine owned by the scene
being destroyed. In particular, after `SceneTree.unloadCurrentScene()`, do not
launch `kanamaScope.launch { ... SceneTree.quit() ... }` from a script attached
to that scene. The scene unload can free the script and cancel the coroutine
before the quit runs. Use `MainThread.postAfterFrames(...)` for this kind of
process-level handoff.

## Porting Style

Keep the Kotlin port close to the original GDScript. Preserve original comments
whenever they are still applicable, especially comments that explain gameplay
rules, timing, scene relationships, resource ownership, exported inspector
values, or non-obvious Godot behavior. These ports are examples for new Kanama
users, so comments from the source demo are part of the teaching surface. Avoid
copying only comments that became false after the port or comments that merely
describe syntax already obvious in Kotlin.

Before calling a demo port done, do a side-by-side parity audit against the
original GDScript files. Check lifecycle callbacks, signal wiring, emitted
signal names, comments, queue/pool ownership, tween ownership, resource
ownership, input/action names, animation names, exported defaults, type checks,
dynamic `call`/`signal` usage, and `self` access.

Do not hide live scene instances behind companion-object singleton facades
unless the original project used a true singleton/autoload with matching
semantics. Prefer the original node/autoload lookup flow, then call the typed
Kanama script instance from that node.

Do not embed environment-gated smoke-test branches in normal gameplay scripts.
Put smoke behavior in dedicated `Smoke.kt` or `SmokeQuit.kt` scripts/nodes so
the gameplay port stays close to the source demo.

When the original GDScript calls Godot math, random, or value-type helpers,
prefer the Kanama/Godot wrapper (`GD`, `Mathf`, `Vector3.moveToward`,
`Vector3.signedAngleTo`, etc.) over hand-written Kotlin math inside the demo.
If the wrapper is missing, add it to Kanama first instead of adding a local
helper in the port.

Put demo gameplay scripts under `kotlin-src/`. Once a scene is rewired to
Kotlin, remove stale gameplay `.gd` files and their `.uid` files so Godot does
not resolve old script UIDs back to the original implementation. Keep the
Kanama tools plugin scripts.

Do not hard-code scene paths, resource paths, or inspector values as a
workaround for missing wrappers or marshalling support unless the user asks
for a temporary workaround. Real demo ports should drive real framework fixes.

Do not make required autoloads or scene nodes optional just to avoid a crash.
If the original GDScript directly uses an autoload such as `Events`, the
Kotlin port should fail loudly when it is missing:

```kotlin
fun Node.eventsNode(): Node =
    getTreeRootNode().getAsOrNull("Events", ::Node)
        ?: error("Events autoload is missing")
```

Silent fallback should not be the default in Kanama examples. Kotlin's value
here is compile-time and early-runtime protection, so required scene
relationships should use typed wrappers, generated names, `requireAs(...)`, or
explicit `error(...)` paths that expose misconfigured scenes immediately.
Optional lookup belongs only where the original GDScript behavior was optional.

Preserve original type checks. If GDScript says `body is Player`, do not
broaden it to `hasMethod("damage")`, `isInGroup("damageables")`, or a generic
node check unless the original code used that broader rule. When Kanama cannot
express a script class check directly yet, centralize the closest equivalent in
a small helper and document why it matches the original scene's intent.

## Build And Smoke Checklist

For a Kanama demo checked out beside the Kanama repo, build scripts with:

```bash
../kanama/gradlew -p ../kanama-demos/<DemoName> buildScripts
```

The Godot editor can also run the same flow through the Kanama tools plugin's
`Build Scripts` button.

Validate with the active Godot preview baseline and any compatibility build
needed by the task:

```bash
/path/to/godot-4.7-beta-3/bin/godot.macos.editor.arm64
/path/to/godot-4.7-stable/bin/godot.macos.editor.arm64
```

Headless smoke tests should load the real scene and exercise a small behavior
slice, such as signal wiring, exported resource hydration, typed object
exports, or physics entry points. Manual playtesting is still required for
feel-sensitive demos such as FPS and Racing.

Known shutdown diagnostics such as RID leaks, particle shader cleanup logs, or
lingering embedded game audio are tracked separately. Record them, but do not
treat them as proof that a gameplay port failed unless they leave a process
alive or break repeatable validation.

Before chasing shutdown leaks, separate owned resources from borrowed scene
state. Good cleanup candidates are project-owned warmup caches, explicitly
created `Tween`/`Tweener` wrappers after configuration, pooled nodes that will
not be reused, and active `AudioStreamPlayer`s that should stop before
`unloadCurrentScene()`. Do not close resources returned from scene-owned nodes
just because the wrapper is closeable.

Resource-returning getters create closeable temporary wrappers. Do not write
checks such as `crosshair.texture != null` and discard the wrapper. Store it,
check it, and close it:

```kotlin
val texture = crosshair.texture
check(texture != null)
texture?.close()
```

Be careful with resources borrowed from scene-owned nodes. For example,
`AnimationPlayer.getAnimation()` returns an animation owned by the player/scene.
Use the returned wrapper immediately, but do not `use { ... }` or `close()` it
just to silence a shutdown warning:

```kotlin
animationPlayer.getAnimation("walk")?.setLoopMode(Animation.LOOP_LINEAR)
```

If a shutdown warning names a borrowed scene resource, prefer fixing the scene
lifetime, stopping active playback, or documenting the residual warning over
releasing a resource you do not own.

# Kotlin Style

Conventions for Kanama scripts and library contributions. Consistent style
makes it easier for contributors to read and extend the codebase.

## Packages

Every script file must declare a named package. Use a reversed domain or short
project name:

```kotlin
package com.mygame.scripts
```

Default-package scripts (no `package` declaration) cannot be referenced by
other scripts and break generated registrars for global classes.

## Imports

Import by name; do not use wildcard imports (`net.multigesture.kanama.api.*`). This matters
more since the generated wrappers became one shared tree (task 103): the members only
desktop and Android can call are extension functions in per-class companion files, so
`import net.multigesture.kanama.api.GridMap` brings in the class but not `getUsedCells()`;
add `import net.multigesture.kanama.api.getUsedCells` beside it. The call site does not
change, and when the member gains an iOS helper (task 100) it moves back into the class
and the same import keeps working. The demos and templates follow this rule; an IDE's
auto-import does it for you on completion.

## Script Class Structure

Declare members in this order within a script class:

1. Exported / inspector properties (`@Export`)
2. Private cached secondary wrappers (`selfAs(...)`)
3. Other private state
4. Lifecycle functions (`@OnReady`, `@OnProcess`, etc.) — roughly in execution order
5. Public functions (registered with Godot)
6. Private helper functions

```kotlin
@ScriptClass(attachTo = "CharacterBody3D")
@GlobalClass
class Player(godotObject: GodotHandle) :
    KanamaScript<CharacterBody3D>(godotObject, ::CharacterBody3D) {

    // 1. Exports
    @ExportGroup("Movement")
    @Export var speed: Double = 5.0
    @Export var jumpVelocity: Double = 4.5

    // 2. Cached wrappers
    private val node3d = selfAs(::Node3D)

    // 3. Other state
    private var isJumping = false

    // 4. Lifecycle
    @OnReady
    fun ready() { ... }

    @OnPhysicsProcess
    fun physicsProcess(delta: Double) { ... }

    // 5. Registered functions
    fun takeDamage(amount: Long) { ... }

    // 6. Helpers
    private fun applyGravity(delta: Double) { ... }
}
```

## Class-Level Annotation Order

Apply class-level annotations top-down in this order:

1. `@ScriptClass` or `@RegisterClass`
2. `@GlobalClass` (if present)
3. `@Tool` (if present)

```kotlin
@ScriptClass(attachTo = "Node")
@GlobalClass
@Tool
class MyEditorTool(val godotObject: GodotHandle)
```

## Naming

Follow standard Kotlin conventions:

| Thing | Convention | Example |
|---|---|---|
| Class | `PascalCase` | `PlayerController` |
| Function | `camelCase` | `physicsProcess` |
| Property | `camelCase` | `jumpVelocity` |
| Constant | `UPPER_SNAKE_CASE` | `MAX_SPEED` |
| Package | `lowercase.dotted` | `com.mygame.scripts` |

Lifecycle functions drop the leading underscore — use `fun ready()` not
`fun _ready()`. The annotation (`@OnReady`) is what matters, not the name.

## Constructor Parameter

Name the Godot handle `godotObject` consistently:

```kotlin
// Correct
class Player(val godotObject: GodotHandle)
class Player(godotObject: GodotHandle) : KanamaScript<CharacterBody3D>(godotObject, ::CharacterBody3D)
```

Avoid alternative names (`obj`, `handle`, `segment`). Documentation and tooling
refer to `godotObject` by name.

## `self` And `selfAs` Caching

Use `KanamaScript<T>` and `self` for the primary attached node/resource type.
Cache additional `selfAs` views in properties, not inside per-frame functions:

```kotlin
// Correct — allocated once at script instantiation
private val node3d = selfAs(::Node3D)

// Avoid — allocates a wrapper every frame
@OnProcess
fun process(delta: Double) {
    val node3d = selfAs(::Node3D)
    node3d.translate(...)
}
```

`selfAs` is a cheap wrapper allocation, but constructing it on every frame is
unnecessary and shows up as allocator pressure under profiling.

## Exports and Properties

Use `@Export` for exported properties, on `@ScriptClass` scripts and
`@RegisterClass` types alike.

Place `@ExportGroup("Name")` on the first exported property in that group, not
as a standalone line:

```kotlin
// Correct
@ExportGroup("Combat")
@Export var damage: Double = 10.0
@Export var range: Double = 3.0

// Avoid — standalone group declaration
@ExportGroup("Combat")
```

## Null Safety and Tool Scripts

Exported node/resource references are nullable. Avoid force-unwrap (`!!`) in
lifecycle functions that may run in editor context:

```kotlin
// Safe for @Tool scripts
@OnProcess
fun process(delta: Double) {
    label?.text = score.toString()
}
```

`@Tool` scripts run in the editor where exported properties may not be
assigned yet. Unconditional `!!` will crash the editor.

## Float / Double

- Decimal literals: use `Double` — `Vector3(0.0, 1.0, 0.0)`, `Color(1.0, 0.5, 0.0)`; no `f`
  suffixes and no `.toFloat()` / `.toDouble()` between components, `delta` and scalar arguments
  (all `Double`)
- Compare decimals with `isEqualApprox`, as in GDScript: components are stored as float32, so
  `Vector2(0.1, 0.2).x == 0.1` is `false` (while `v == Vector2(0.1, 0.2)` is `true`)
- Integer components need no `.0` (`Vector3(0, 1, 0)`), but a call that mixes them with decimals
  does (`Vector3(speed, 0.0, 0.0)`)
- Use `withX()` / `withY()` / `withZ()` to change one component without constructing a full new vector

```kotlin
// Preferred
body.velocity = body.velocity.withY(body.velocity.y + gravity * delta)
```

## Signals

Prefer generated signal and method constants over free-form strings when wiring
signals from code. Declare custom signals with `@Signal` and emit them through
the generated `*Signals` helper. See
[Signals and Callbacks](signals.md) for examples and scene-connection rules.

## Registered Functions and Signal Callbacks

Every public function is registered with Godot — scene signal callbacks,
callable targets, methods called via `GodotObject.call(...)` — so keep helpers
Godot never calls `private` (or `internal` when another script calls them). The
GDScript-side name is `snake_case(functionName)`; use `@GodotName` to match a
specific scene-saved signal connection:

```kotlin
@GodotName("_on_body_entered")
fun onBodyEntered(body: Node) { ... }
```

## Android-Compatible Callbacks

For Android-targeted projects, avoid nullable Kotlin function calls written as
`callback?.invoke()`. Kanama's current Android export path remaps desktop FFM
sources for PanamaPort compatibility, and that broad remap can confuse nullable
Kotlin callback invocation with low-level `MethodHandle.invoke(...)`.

Prefer explicit state or a direct lambda call inside `let`:

```kotlin
// Prefer this for simple optional callbacks.
onFinished?.let { it() }

// Prefer explicit state when the follow-up is part of gameplay flow.
private var hideAfterTween = false

private fun finishTween() {
    if (hideAfterTween) {
        hideAfterTween = false
        panel.hide()
    }
}
```

Android demo/source audits fail early on `?.invoke(...)` so the problem is
caught before export.

## Coroutines

Every `KanamaScript` has its own coroutine scope. Start a coroutine with
`launch { }` and suspend in it with `wait(seconds)`, `nextFrame()` or a signal
`await`, the way a GDScript function uses `await`. Prefer them over manual delay
loops.

The scope belongs to the script instance: Kanama cancels it when the script's
Godot object is freed. Work launched there should be work that is allowed to
stop then: gameplay loops, delayed effects, scene-local warmup, polling, and
node-owned animation or cleanup. Do not use it for process-level work that must
survive freeing the node that started it.

Use `launch` when the delayed work touches this script, `self`, child nodes, or
resources owned by the current scene:

```kotlin
@ScriptClass(attachTo = "Node")
class Door(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
    fun openBriefly() {
        launch {
            self.show()
            wait(0.4)
            self.hide()
        }
    }
}
```

That coroutine stops if the door node is freed. This is the normal, safe
behavior for scene-owned work. Leaving the tree does not stop it, as in GDScript;
call `cancelCoroutines()` (for example in `@OnExitTree`) when it should.

Store the returned `Job` when a later event should cancel one piece of work:

```kotlin
private var warmupJob: Job? = null

fun startWarmup() {
    warmupJob = launch {
        nextFrame()
        warmUpSceneResources(self)
    }
}

fun cancelWarmup() {
    warmupJob?.cancel()
    warmupJob = null
}
```

`scriptScope` is the scope itself, for the `kotlinx.coroutines` builders that
`launch` does not cover (`scriptScope.launch(start = CoroutineStart.UNDISPATCHED)`,
`scriptScope.async { }`).

Use `MainThread.post` or `MainThread.postAfterFrames` when the work is a
process-level handoff and must outlive the node that requested it. For example,
after unloading the current scene, do not put the quit call in the scene's
script scope:

```kotlin
SceneTree.unloadCurrentScene()
MainThread.postAfterFrames(2) {
    SceneTree.quit()
}
```

The rule of thumb is ownership: if the delayed code uses scene-local state,
`launch` it from the script; if it only touches global engine state such as
`SceneTree.quit()`, use `MainThread`.

## Godot-Owned Resources

This is the **exception** to the ownership rule in
[Godot API → Resource Ownership](godot-api.md#resource-ownership), not a
contradiction of it. Closing is safe when another owner still holds a reference;
the cases below are the ones where you may be the **last** owner, so releasing
your reference destroys a live object.

Do not call `close()` on a Godot object that the scene tree still needs. In
particular, `createTween()` returns a live Godot `Tween`; after scheduling
work, let Godot own the running tween. The `Tweener` that `tweenProperty`,
`tweenMethod` and `tweenCallback` hand back is a different object: it is an
owned `RefCounted` return, so close it once you have configured it, as the
[Resource Ownership](godot-api.md#resource-ownership) rule says.

```kotlin
val tween = self.createTween() ?: return
tween.tweenProperty(icon, "modulate", Color.WHITE, 0.2).close()
```

If you keep a `Tween` in a field so you can cancel it later, kill it through
Godot and drop the Kotlin reference:

```kotlin
private var fadeTween: Tween? = null

fun fadeOut() {
    fadeTween?.kill()
    fadeTween = null
    val tween = self.createTween() ?: return
    fadeTween = tween
    tween.tweenProperty(self, "modulate", Color.TRANSPARENT, 0.2).close()
    tween.signal(Tween.Signals.finished).connect(self, argumentCount = 0, flags = GodotObject.ConnectFlags.ONE_SHOT) {
        if (fadeTween === tween) {
            fadeTween = null
        }
    }
}
```

Everything else — generated meshes, materials, audio streams, and every other
`Resource`/`RefCounted` value you create or read back, plain getters included —
follows the one rule in
[Godot API → Resource Ownership](godot-api.md#resource-ownership): the wrapper
is yours, so `close()` it (or `use { }`) to release it as soon as you are done.
A wrapper you forget is released later, after the garbage collector drops it,
so closing is about *when*, not about leaking; prefer `use { }` for big
resources and anything made in a loop. Handing it to a node first does not
change that: after `meshInstance.setMesh(mesh)` the node holds its own reference
and `mesh.close()` releases only yours (tasks 61/62).

`RefCounted.close()` carries no opt-in annotation. `@ManualGodotLifetimeApi`
is deprecated and no longer applied anywhere; an `@OptIn` for it in a script
compiles with a deprecation warning and can simply be deleted.

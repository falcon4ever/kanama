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

## Script Class Structure

Declare members in this order within a script class:

1. Exported / inspector properties (`@ScriptProperty`, `@Export`)
2. Private cached secondary wrappers (`selfAs(...)`)
3. Other private state
4. Lifecycle functions (`@OnReady`, `@OnProcess`, etc.) — roughly in execution order
5. `@RegisterFunction` functions exposed to Godot
6. Private helper functions

```kotlin
@ScriptClass(attachTo = "CharacterBody3D")
@GlobalClass
class Player(godotObject: MemorySegment) :
    KanamaScript<CharacterBody3D>(godotObject, ::CharacterBody3D) {

    // 1. Exports
    @ExportGroup("Movement")
    @ScriptProperty var speed: Double = 5.0
    @ScriptProperty var jumpVelocity: Double = 4.5

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
    @RegisterFunction
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
class MyEditorTool(val godotObject: MemorySegment)
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
class Player(val godotObject: MemorySegment)
class Player(godotObject: MemorySegment) : KanamaScript<CharacterBody3D>(godotObject, ::CharacterBody3D)
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

Use `@ScriptProperty` for `@ScriptClass` scripts and `@RegisterProperty` for
`@RegisterClass` types. `@Export` is an alias that works in both and is fine
for ported codebases.

Place `@ExportGroup("Name")` on the first exported property in that group, not
as a standalone line:

```kotlin
// Correct
@ExportGroup("Combat")
@ScriptProperty var damage: Double = 10.0
@ScriptProperty var range: Double = 3.0

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

- Vector component literals: use `Float` — `Vector3(0f, 1f, 0f)`
- `delta` and scalar method arguments: `Double`, no conversion needed
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

Use `@RegisterFunction` for methods Godot needs to call — scene signal
callbacks, callable targets, and methods called via `GodotObject.call(...)`.
The GDScript-side name defaults to `snake_case(functionName)`; use an explicit
string argument to match a specific scene-saved signal connection:

```kotlin
@RegisterFunction("_on_body_entered")
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

Use `KanamaCoroutineOwner` for scripts that need coroutines. Prefer
`MainThread.awaitNextFrame()` and `SceneTree.delaySeconds(...)` over manual
delay loops.

Treat `kanamaScope` as owned by the script instance. Work launched in that
scope should be work that is allowed to stop when the script's Godot object is
freed: gameplay loops, delayed effects, scene-local warmup, polling, and
node-owned animation or cleanup. Do not use a script-owned coroutine for
process-level work that must survive freeing the node that started it.

Use `kanamaScope` when the delayed work touches this script, `self`, child
nodes, or resources owned by the current scene:

```kotlin
@ScriptClass(attachTo = "Node")
class Door(godotObject: MemorySegment) :
    KanamaScript<Node>(godotObject, ::Node),
    KanamaCoroutineOwner {

    override val kanamaScope = KanamaScope()

    fun openBriefly() {
        kanamaScope.launch {
            self.show()
            SceneTree.delaySeconds(0.4)
            self.hide()
        }
    }
}
```

That coroutine should stop if the door node is freed. This is the normal,
safe behavior for scene-owned work.

Store the returned `Job` when a later event should cancel scene-owned work:

```kotlin
private var warmupJob: Job? = null

fun startWarmup() {
    warmupJob = kanamaScope.launch {
        MainThread.awaitNextFrame()
        warmUpSceneResources(self)
    }
}

fun cancelWarmup() {
    warmupJob?.cancel()
    warmupJob = null
}
```

Use `MainThread.post` or `MainThread.postAfterFrames` when the work is a
process-level handoff and must outlive the node that requested it. For example,
after unloading the current scene, do not put the quit call in the scene's
`kanamaScope`:

```kotlin
SceneTree.unloadCurrentScene()
MainThread.postAfterFrames(2) {
    SceneTree.quit()
}
```

The rule of thumb is ownership: if the delayed code uses scene-local state,
keep it in `kanamaScope`; if it only touches global engine state such as
`SceneTree.quit()`, use `MainThread`.

## Godot-Owned Resources

This is the **exception** to the ownership rule in
[Godot API → Resource Ownership](godot-api.md#resource-ownership), not a
contradiction of it. Closing is safe when another owner still holds a reference;
the cases below are the ones where you may be the **last** owner, so releasing
your reference destroys a live object.

Do not call `close()` on a Godot object that the scene tree still needs. In
particular, `createTween()` returns a live Godot `Tween`; after scheduling
work with `tweenProperty`, `tweenMethod`, or `tweenCallback`, let Godot own the
running tween.

```kotlin
val tween = self.createTween() ?: return
tween.tweenProperty(icon, "modulate", Color.WHITE, 0.2)
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
    tween.tweenProperty(self, "modulate", Color.TRANSPARENT, 0.2)
    tween.signal(Tween.Signals.finished).connect(self, argumentCount = 0, flags = GodotObject.CONNECT_ONE_SHOT) {
        if (fadeTween === tween) {
            fadeTween = null
        }
    }
}
```

The same ownership rule applies to generated meshes, materials, audio streams,
and other `Resource`/`RefCounted` values. If a node property stores the object,
for example `meshInstance.setMesh(mesh)`, do not immediately close that same
resource unless you have verified the setter retained its own reference.

Kanama marks manual Godot lifetime APIs with `@ManualGodotLifetimeApi`. If a
game script triggers that warning, treat it as a design review: either replace
the call with a Godot lifecycle API, or add an explicit `@OptIn` only when the
value is documented as caller-owned.

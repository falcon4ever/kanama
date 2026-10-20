# Kotlin Style

These are the conventions Kanama's templates and demos follow. They keep
scripts easy to read for anyone who knows Godot, and a few of them avoid real
bugs. The snippets sit inside a script like the one in
[Writing Scripts](scripts.md#a-complete-script), which lists the imports.

## Packages

```kotlin
package com.mygame.scripts
```

Every script file declares a package, a reversed domain or a short project
name. A script without one cannot be used from other scripts, and a
`@GlobalClass` in it does not register.

## Imports

```kotlin
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.Label
import net.multigesture.kanama.api.Node2D
import net.multigesture.kanama.types.*
```

The templates import annotations and Godot classes by name, which your IDE
does as you complete a name, and the value types with
`net.multigesture.kanama.types.*`. That wildcard also brings the operators
written as extensions, such as `2.0 * v`. A wildcard
`net.multigesture.kanama.api.*`, as in [Writing Scripts](scripts.md#a-complete-script),
works as well.

## Script Class Structure

```kotlin
@ScriptClass(attachTo = "CharacterBody3D")
@GlobalClass
class Player(godotObject: GodotHandle) :
    KanamaScript<CharacterBody3D>(godotObject, ::CharacterBody3D) {

    // 1. Exports
    @ExportGroup("Movement")
    @Export var speed: Double = 5.0
    @Export var jumpVelocity: Double = 4.5

    // 2. Cached views and node lookups
    private val node3d = selfAs(::Node3D)
    private val camera by node<Camera3D>("Camera")

    // 3. Other state
    private var isJumping = false

    // 4. Lifecycle callbacks, roughly in the order Godot calls them
    @OnReady
    fun ready() { /* ... */ }

    @OnPhysicsProcess
    fun physicsProcess(delta: Double) { /* ... */ }

    // 5. Public functions, which Godot can call
    fun takeDamage(amount: Long) { /* ... */ }

    // 6. Private functions
    private fun applyGravity(delta: Double) { /* ... */ }
}
```

Declare a script's members in this order. Class annotations go top-down:
`@ScriptClass`, then `@GlobalClass`, then `@Tool`.

## Naming

| Thing | Convention | Example |
|---|---|---|
| Class | `PascalCase` | `PlayerController` |
| Function | `camelCase` | `physicsProcess` |
| Property | `camelCase` | `jumpVelocity` |
| Constant | `UPPER_SNAKE_CASE` | `MAX_SPEED` |
| Package | `lowercase.dotted` | `com.mygame.scripts` |

Lifecycle functions drop GDScript's leading underscore: `fun ready()`, not
`fun _ready()`. The annotation is what Godot looks for, not the name.

Name the constructor parameter `godotObject`, as the templates and the
**Attach Script** dialog do:

```kotlin
class Player(godotObject: GodotHandle) :
    KanamaScript<CharacterBody3D>(godotObject, ::CharacterBody3D)
```

## `self` and `selfAs` Caching

```kotlin
private val node3d = selfAs(::Node3D)      // made once, with the script

@OnProcess
fun process(delta: Double) {
    node3d.translate(Vector3(0.0, 0.0, delta))
}
```

`self` is the node as the class in `KanamaScript<T>`. Keep a second view from
`selfAs` in a property, not in a per-frame function: each call makes a new
wrapper, which is cheap but adds up every frame.

## Exports and Properties

```kotlin
@ExportGroup("Combat")
@Export var damage: Double = 10.0
@Export var range: Double = 3.0
```

Put `@ExportGroup` on the first property of its group, not on a line of its
own.

## Null Safety and Tool Scripts

```kotlin
@Export var label: Label? = null
private var score = 0L

@OnProcess
fun process(delta: Double) {
    label?.text = score.toString()
}
```

An exported node or resource is nullable, and in a `@Tool` script it can still
be `null` while the editor runs your code. Use `?.` instead of `!!` in
lifecycle functions: a `!!` on an unassigned export crashes the editor.

## Decimals

```kotlin
@Export var gravity: Double = 980.0

@OnPhysicsProcess
fun physicsProcess(delta: Double) {
    self.velocity = self.velocity.withY(self.velocity.y + gravity * delta)
}
```

- Write decimals as `Double`: `Vector3(0.0, 1.0, 0.0)`, `Color(1.0, 0.5, 0.0)`,
  with no `f` suffix and no `.toFloat()` between components, `delta` and other
  arguments.
- Integer components need no `.0`, as in GDScript: `Vector3(0, 1, 0)`.
- Change one component with `withX()`, `withY()` or `withZ()`.
- Compare decimals with `isEqualApprox`, as in GDScript. Components are stored
  as 32-bit floats, so `Vector2(0.1, 0.2).x == 0.1` is `false`.

## Signals and Registered Functions

```kotlin
@GodotName("_on_body_entered")
fun onBodyEntered(body: Node3D) { /* ... */ }

private fun recalculate() { /* ... */ }
```

Every public function is visible to Godot, so make the functions Godot never
calls `private`, or `internal` when another script calls them. Use
`@GodotName` only to match a name a scene already saved. Prefer the generated
typed signal handles and `Names` constants to strings. See
[Signals and Await](signals.md).

## Calling Function Values

```kotlin
private var onFinished: (() -> Unit)? = null

fun finish() {
    onFinished?.let { it() }
}
```

Call a function value directly, `callback()` or `callback?.let { it() }`,
never with `.invoke()`. A `.invoke()` call does not compile for Android; see
[Limitations](../reference/limitations.md#android).

## Coroutines

```kotlin
@ScriptClass(attachTo = "Node2D")
class Door(godotObject: GodotHandle) : KanamaScript<Node2D>(godotObject, ::Node2D) {
    fun openBriefly() {
        launch {
            self.show()
            wait(0.4)
            self.hide()
        }
    }
}
```

Every script has its own coroutine scope. Start a coroutine with `launch { }`
and suspend in it with `wait(seconds)`, `nextFrame()` or a signal's `await()`,
the way a GDScript function uses `await`. Kanama cancels the scope when the
script's node is freed, so a coroutine never touches a freed node. Leaving the
tree does not stop it, as in GDScript; call `cancelCoroutines()`, in
`@OnExitTree` for example, when it should.

Use `launch` for work that belongs to the scene: gameplay loops, delayed
effects, warmup, polling, animation and cleanup. Keep the returned `Job` when a
later event should cancel one piece of work:

```kotlin
import kotlinx.coroutines.Job

private var warmupJob: Job? = null

fun startWarmup() {
    warmupJob = launch {
        nextFrame()
        warmUp()
    }
}

fun cancelWarmup() {
    warmupJob?.cancel()
    warmupJob = null
}

private fun warmUp() { /* load what the next scene needs */ }
```

`scriptScope` is the scope itself, for other `kotlinx.coroutines` builders such
as `scriptScope.async { }`.

Work that must outlive the node goes to `MainThread` instead. After unloading
the current scene, for example, the scene's scope is about to be cancelled:

```kotlin
fun quitToDesktop() {
    val tree = self.tree
    tree.unloadCurrentScene()
    MainThread.postAfterFrames(2) {
        tree.quit()
    }
}
```

The rule is ownership: if the delayed code uses the scene, `launch` it from the
script; if it only touches global engine state, post it with
`MainThread.post { }` or `MainThread.postAfterFrames(n) { }`.

## Godot-Owned Resources

`close()` releases your reference and never destroys an object someone else
still holds ([Resource Ownership](godot-api.md#resource-ownership)). A running
`Tween` is the exception: the scene tree runs it, so stop it through Godot
with `kill()` instead of closing it.

```kotlin
private var fadeTween: Tween? = null

fun fadeOut() {
    fadeTween?.kill()
    val tween = self.createTween()
    fadeTween = tween
    tween.tweenProperty(self, "modulate", Color.TRANSPARENT, 0.2).close()
    tween.finished.connect(GodotObject.ConnectFlags.ONE_SHOT) {
        if (fadeTween == tween) fadeTween = null
    }
}
```

The `Tweener` that `tweenProperty`, `tweenMethod` and `tweenCallback` return is
a reference of your own, so close it once you have configured it, as above.
Every other resource you create or read back follows the ownership rule:
`close()` it, or use `use { }`, when you are done. After
`meshInstance.setMesh(mesh)` the node holds its own reference, and
`mesh.close()` releases only yours. A wrapper you forget is released later,
after the garbage collector drops it, so prefer `use { }` for big resources and
anything you make in a loop.

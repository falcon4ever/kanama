# Upgrading from 0.4 to 0.5

Kanama 0.5 is a large step for a pre-1.0 preview. The generated Godot API now
follows [one set of rules](../reference/wrapper-conventions.md) on every
platform, so some names, types and nullability changed, and a number of things
that behaved differently on Web, iOS and desktop now behave the same. Before
1.0 such source breaks are allowed when they serve the API's long-term shape,
but each one is announced in the [changelog](https://github.com/falcon4ever/kanama/blob/main/CHANGELOG.md)
and, wherever a script can do it, fixed for you.

This page lists every announced source break and the behaviour changes that do
not stop the build but change results, with the code you had in 0.4 and the code
you write now.

## Who needs this

- **You have Kotlin scripts written against 0.4** (desktop, Android, iOS or
  Web): read it and follow the order below.
- **You start a new project from a 0.5 release kit, store addon or starter
  template:** you do not need it. The starters already use the 0.5 spellings.
- **You maintain your own Kanama checkout or scripts that call Kanama's Gradle
  tasks:** read [Tooling, Gradle and the editor](#tooling-gradle-and-the-editor).

## The order to do things in

1. **Commit your project.** The migration scripts rewrite files in place.
2. **Bump the version.**
    - *Source checkout:* update the checkout to the 0.5 release (`git pull`, or
      check out its tag), then rerun the
      `installAddonJar` command from [Use a Source Checkout](source-checkout.md)
      against your project.
    - *Store addon:* unzip the 0.5 `kanama-store-addon` zip at the root of your
      Godot project (it replaces `addons/kanama` and `addons/kanama_tools`).
      Your root `build.gradle.kts` and `settings.gradle.kts` are yours; compare
      them with `addons/kanama/templates/release-kit`. Two things changed: the
      JDK toolchain resolver in `settings.gradle.kts` (see
      [Tooling](#tooling-gradle-and-the-editor)), and the `kotlin("jvm")` and
      `com.google.devtools.ksp` plugin versions in `build.gradle.kts`, now
      2.4.21 and 2.3.12 (the 0.4 versions, 2.3.21 and 2.3.9, still build).
    - *Release kit:* a kit is for new projects. Copy your `kotlin-src` into a
      project made from the 0.5 kit, or apply the store-addon steps above.
    - Use the **Godot 4.7.2** editor and export templates for the build
      (see [Version Support → Requirements](../reference/version-support.md#requirements)).
3. **Run the migration scripts on your Kotlin sources, in this order.** They are
   Python 3 files in the `scripts/` folder of a Kanama checkout (the packaged
   kits do not carry them) and take your source folder as the argument:

    ```sh
    python3 scripts/migrate_script_annotations.py path/to/kotlin-src
    python3 scripts/migrate_export_hints.py path/to/kotlin-src
    python3 scripts/migrate_enum_constants.py path/to/kotlin-src
    ```

    The annotation script must run first: it turns `@ScriptProperty` into
    `@Export`, which the hint script then splits into `@ExportRange` and
    friends. Each script prints what it changed and what needs a human, and
    running one again on migrated sources changes nothing. Preview with
    `--check` (the first two) or `--dry-run` (the enum script) before
    letting them write. See [Script annotations and export
    hints](#script-annotations-and-export-hints) and [Enums and
    bitfields](#enums-and-bitfields).
4. **Build and fix what the compiler still reports.** Use the table below to
   find the section for each message.
5. **Re-export.** A Web game must be rebuilt and re-exported: the bridge is at
   protocol 39 <!-- kanama-claim: protocol --> and a page built with an older
   protocol refuses to run, so never mix builds. Rebuild iOS and Android scripts
   too: the generated registrars changed.
6. **Read [Silent changes to check](#silent-changes-to-check).** They compile
   and still change results.

| The compiler (or a script) says | Section |
| --- | --- |
| `Unresolved reference` on `Node.PROCESS_MODE_ALWAYS`, `Tween.TRANS_SINE`, `InputEventKey.KEY_W`, `GodotObject.CONNECT_DEFAULT` | [Enums and bitfields](#enums-and-bitfields) |
| `Type mismatch: inferred type is Long, but Int/Double/GodotError was expected` | [Enums](#enums-and-bitfields), [Integer widths](#node-scenetree-viewport-and-resource), [Signals](#signals-callables-and-await) |
| `Float` expected where you pass `Double`, or the reverse, on a vector or color | [Decimal values are Double](#decimal-values-are-double) |
| Deprecated annotation with a message naming a replacement | [Script annotations](#script-annotations-and-export-hints) |
| `FileAccessHandle` / `DirAccessHandle`, `fileExistsAt`, `writeString` | [FileAccess and DirAccess](#fileaccess-and-diraccess) |
| `MemorySegment` in a script constructor | [The script constructor handle](#the-script-constructor-handle) |
| `Only safe (?.) or non-null asserted (!!.) calls are allowed` after `getTree()`; `Unnecessary safe call` in a tween chain | [Node, SceneTree, Viewport and Resource](#node-scenetree-viewport-and-resource), [Tween](#tween-and-tweeners) |
| Web: `set("position:x", ...)` stopped moving the node, a `Web*` import no longer resolves | [Web](#web) |

## What changes at a glance

- **A Web script and a desktop script now behave the same.** Web compiles the
  same `GD`, `Mathf`, value types, `GodotCallable` and typed signals, follows the
  same ownership and freed-object rules, reports script exceptions to Godot's
  log, and uses Godot's random generator.
- **The Godot API is generated once.** About twenty classes that were hand
  written per platform (`Node`, `SceneTree`, `Tween`, `FileAccess`,
  `DirAccess`, `Engine`, ...) are generated like the rest, so a member exists on
  every native platform or on none. iOS gained a lot (the full `Tween`,
  `FileAccess`, `DirAccess`, `Engine`, `GD`).
- **Every Godot enum and bitfield is a typed value class**
  (`Node.ProcessMode.ALWAYS`, `Key.W`), and `connect` returns a `GodotError`.
- **Every decimal value is `Double`**, including vector and color components.
- **One annotation set, like GDScript's.** Public functions are registered
  automatically, `@Export` has typed hints (`@ExportRange`), and input handlers
  take an `InputEvent`.
- **`close()` is optional.** A `RefCounted` you forget is released after the
  garbage collector drops it, on every thread and platform. `close()` and
  `use { }` release early.
- **New for scripts** (nothing to migrate): typed engine and `@Signal` signals
  with `await()`, `node<T>()` / `script<T>()` / `preload<T>()` delegates, checked
  casts, generated `Autoloads`, script inheritance, all Godot value types as
  script types, Godot's `String`, `NodePath` and `PackedByteArray` methods.

The rest of this page is organised by area.

## Kotlin API surface

### The script constructor handle

`GodotHandle` was a typealias for `java.lang.foreign.MemorySegment` on desktop
and Android. It is a `@JvmInline value class` now, declared once for every native
backend, so no public signature names an FFM type and one script source compiles
everywhere.

A script written as the templates always showed compiles unchanged:

```kotlin
class Player(godotObject: GodotHandle) : KanamaScript<CharacterBody3D>(godotObject, ::CharacterBody3D)
```

What breaks is code that spelled the old type or reached through the handle:

```kotlin
// 0.4
import java.lang.foreign.MemorySegment
class Player(godotObject: MemorySegment) : KanamaScript<CharacterBody3D>(godotObject, ::CharacterBody3D)
val raw = body.handle.address()

// 0.5
import net.multigesture.kanama.api.GodotHandle
class Player(godotObject: GodotHandle) : KanamaScript<CharacterBody3D>(godotObject, ::CharacterBody3D)
val same = body.isSameInstance(other)   // no raw pointer by design
```

Re-wrapping through another wrapper's handle (`CharacterBody3D(body.handle)`,
`selfAs(::Node3D)`) is unchanged. Three desktop-only helpers keep a
`MemorySegment` parameter on purpose, because they take a native pointer:
`GDExtensionManager.loadExtensionFromFunction`,
`OpenXRAPIExtension.transformFromPose` and `OpenXRAPIExtension.setCustomPlaySpace`.
A script left on the old type fails with a message that spells out this
migration. No script fixes it.

### Decimal values are `Double`

Vector, transform and color components are `Double` in every signature on every
platform, like GDScript's 64-bit `float`. In 0.4 they were `real_t` (`Float` in
the single-precision builds Kanama ships) and `Color` was `Float`. The value
types keep storing Godot's width internally (so `node.position = v;
node.position == v` is `true`), but you no longer see `Float`.

```kotlin
// 0.4
val v = Vector3(0f, 1f, 0f)
val c = Color(1f, 1f, 1f, alpha.toFloat())
val x: Double = v.x.toDouble()
val dim = c.copy(a = alpha.toFloat())

// 0.5
val v = Vector3(0.0, 1.0, 0.0)
val c = Color(1.0, 1.0, 1.0, alpha)
val x: Double = v.x
val dim = c.copy(a = alpha)
```

- Integer literals construct without boxing: `Vector3(0, 1, 0)`,
  `Vector3(speed, 0, 0)`, `Color(1, 1, 1, 0.72)`, `v * 2`, `v.withX(0)`. `Vector4`,
  `Quaternion` and `Plane(x, y, z, d)` take all-`Int` or all-`Double` arguments.
- The classes are no longer `data` classes. `copy`, `componentN`, `equals` and
  `hashCode` remain.
- The typealias `real_t` and `GodotReal.fromNumber` / `fromDouble` / `fromFloat`
  are removed; `GodotRealSegment.readIndex` / `writeIndex` take and return
  `Double`. Game code does not use them.
- Your own `Float` fields that hold components or channels become `Double`.
- Compare decimals you wrote with the value you read back through
  `isEqualApprox`, as in GDScript: `Vector2(0.1, 0.2).x == 0.1` is `false` in a
  float32 build.

Parameter names and one return type follow Godot's for the members that moved
into the generated value-type set (`Vector3.reflect(line)` is new); only calls that name the argument notice:

| Member | 0.4 | 0.5 |
| --- | --- | --- |
| `Vector2.limitLength`, `Vector3.limitLength` | `limitLength(maxLength)` | `limitLength(length = 1.0)` |
| `Vector3.bounce` | `bounce(normal)` | `bounce(n)` |
| `Transform3D.interpolateWith` | `interpolateWith(to, weight)` | `interpolateWith(xform, weight)` |
| `Vector3.maxAxisIndex()` (iOS; desktop gains it) | `Int` | `Long`: compare with `Vector3.Axis.X.value` or a `Long` literal |

Script: none; fix by hand (the compiler lists every site).

### FileAccess and DirAccess

`FileAccess` and `DirAccess` are `RefCounted` classes now, as in GDScript, on
every platform (iOS gained all of both). `FileAccessHandle` and
`DirAccessHandle` are gone, as are the 38 `FileAccess` and 23 `DirAccess`
helpers that took a path on every call. Open the file, use it, close it:

```kotlin
// 0.4
val h: FileAccessHandle? = FileAccess.open("user://save.txt", FileAccess.WRITE)
FileAccess.writeString("user://save.txt", text)
val first = FileAccess.getLineFor("user://save.txt")
val word = FileAccess.get32At("user://data.bin", 16L)
val exists = DirAccess.fileExistsAt("user://", "save.txt")
val all = DirAccess.getFilesAt("user://", includeHidden = true)

// 0.5
FileAccess.open("user://save.txt", FileAccess.ModeFlags.WRITE)?.use { it.storeString(text) }
val first = FileAccess.open("user://save.txt", FileAccess.ModeFlags.READ)?.use { it.getLine() }
val word = FileAccess.open("user://data.bin", FileAccess.ModeFlags.READ)?.use {
    it.seek(16L)
    it.get32()
}
val exists = DirAccess.open("user://")?.use { it.fileExists("save.txt") }
val all = DirAccess.open("user://")?.use { dir ->
    dir.includeHidden = true
    dir.getFiles()
}
```

- `FileAccess.open` returns an owned `FileAccess?` (`null`, with the reason in
  `FileAccess.getOpenError()`); so do `openCompressed`, `openEncrypted`,
  `openEncryptedWithPass`, `createTemp`, and `DirAccess.open` / `createTemp`.
  Write `FileAccess` / `DirAccess` where you named the handle type, and
  `FileAccess.fromHandle(h)` for `FileAccessHandle.fromHandle(h)`. There is no
  deprecated alias.
- The forms that took a `position` become `it.seek(position)` before the read;
  `getPositionFromEnd(path, p)` becomes `it.seekEnd(p)` then `it.getPosition()`.
  Big-endian is `it.bigEndian = true` first; a resize opens `READ_WRITE` and calls
  `it.resize(n)`.
- Whole-file reads keep Godot's statics: `FileAccess.getFileAsString(path)` and
  `getFileAsBytes(path)`. Godot's directory statics stay too: `getFilesAt(path)`,
  `getDirectoriesAt(path)`, `makeDirAbsolute`, `makeDirRecursiveAbsolute`,
  `removeAbsolute`, `copyAbsolute`, `renameAbsolute`, `dirExistsAbsolute`,
  `getDriveCount`, `getDriveName`, `getOpenError`. A walk with "is directory"
  uses `listDirBegin()`, `getNext()`, `currentIsDir()`, `listDirEnd()` as in
  GDScript.
- `FileAccess.close()` closes the file and releases your reference (also on a
  borrowed view). Godot's own `close()` is `closeFile()`, which closes the file
  and keeps your reference. `DirAccess.close()` only releases the reference.
- **Always `use { }` a file you open.** Unlike GDScript, a file you forget stays
  open, with its writes unflushed, until the garbage collector releases it (see
  [Ownership](#ownership-and-close)).
- Signatures follow Godot: `DirAccess.getDriveCount()` returns `Int`,
  `getDriveName(idx: Int)`; `FileAccess.get8()` / `get16()` return `Int` and
  `store8` / `store16` take `Int`; the `FileAccess` statics name their path
  `file` (`getModifiedTime`, `getSize`, `getUnixPermissions`, ...);
  `storeString(string)`. `FileAccess` is a class now, not an object:
  `is FileAccess` tests a file instance.

Script: none.

### Node, SceneTree, Viewport and Resource

These classes were hand written on desktop and are generated now. The generated
shape won, which changes integer widths, a few names and nullability. iOS sees
almost none of this (it already had the generated shapes).

**`Node`.** Godot's `int32` is `Int`:

```kotlin
// 0.4
val n: Long = node.getChildCount()
val last = node.getChild(n - 1)
node.setProcessPriority(5L)
val copy = node.duplicate(Node.DUPLICATE_SIGNALS or Node.DUPLICATE_GROUPS)
self.getTree().quit()

// 0.5
val n: Int = node.getChildCount()
val last = node.getChild(n - 1)               // getChild(idx: Int)
node.setProcessPriority(5)
val copy = node.duplicate(
    Node.DuplicateFlags.SIGNALS.value.toInt() or Node.DuplicateFlags.GROUPS.value.toInt()
)
self.getTree()?.quit()                         // or SceneTree.quit()
```

- `getChildCount`, `getIndex`, `getMultiplayerAuthority`, the process and
  physics-process priorities and the thread group order are `Int`. A
  `Long`-typed counter fed from them needs `.toLong()`, and `?: -1L` fallbacks
  become `?: -1`.
- `duplicate(flags: Int = ...)` takes an `Int`. The flag constants are typed
  values now, and `DuplicateFlags` is an enum in Godot's API, not a bitfield, so
  combine their `.value`s as above (the enum script rewrites the constants but
  leaves this call to you), or pass the default with `duplicate()`.
- `Node.getTree()` is `SceneTree?`. Use `self.tree` in a script (non-null, it
  throws if the node is outside the tree), `getTree()?.`, or the `SceneTree`
  companion entry points (`SceneTree.quit()`), which resolve the running tree.
- `createTween()` stays a member of `Node` and `SceneTree` and now returns a
  non-null `Tween` (it was `Tween?`); see [Tween](#tween-and-tweeners).
- Parameter names follow the generator: `addChild(node, forceReadableName,
  internalValue)` (was `internalMode`) and `setEditorDescription(editorDescription)`.
  Thirteen properties arrive (`name`, `processMode`, `sceneFilePath`, ...).

**`SceneTree`.** The old desktop `object SceneTree`, the placeholder
`SceneTreeHandle` and iOS's own class are one generated `SceneTree : MainLoop`.
Every old entry point survives on the companion, typed like the instance member
it calls, so `SceneTree.quit()`, `SceneTree.delaySeconds(...)`,
`SceneTree.unloadCurrentScene()` and friends read as before. What changed:

| 0.4 | 0.5 |
| --- | --- |
| `getNodeCount(): Long`, `getNodeCountInGroup(name): Long` | `Int` |
| `quit(exitCode: Long = 0)` | `quit(exitCode: Int = 0)` |
| `notifyGroup(group, notification: Long)` | `notification: Int` |
| `getRoot(): GodotHandle` | `getRoot(): Window` (non-null) |
| `getCurrentScene(): GodotHandle`, `getFirstNodeInGroup(name): GodotHandle` | `Node?` |
| `setCurrentScene(nodeObject: GodotHandle)` | `setCurrentScene(childNode: Node)` |
| `queueDelete(nodeObject: GodotHandle)` | `queueDelete(obj: GodotObject)` |
| `changeSceneToPacked(h: GodotHandle): Long` | `changeSceneToPacked(packedScene: PackedScene): GodotError` |
| `changeSceneToNode(h: GodotHandle): Long` | `changeSceneToNode(node: Node): GodotError` |
| `changeSceneToFile(path): Long`, `reloadCurrentScene(): Long` | `GodotError` |
| `getMultiplayer(forPath: String = ""): GodotHandle` | `getMultiplayer(forPath: NodePath): MultiplayerAPI` on the instance; the companion keeps the default: `getMultiplayer(forPath: NodePath = NodePath(""))` |

A Java caller of the companion entry points writes `SceneTree.Companion.quit()`.
`SceneTree.delaySeconds` waits in engine frames on every platform now, so it
honors pause and time scale (see [iOS](#ios)).

**`Viewport` and `Resource`.**

- `Viewport.pushInput` / `pushUnhandledInput` take a non-null `InputEvent`.
  `getCamera2D()` / `getCamera3D()` stay.
- `Resource.asObject()` is gone: `Resource` is a `GodotObject`, call `setMeta`,
  `connect`, `callDeferred` on it directly.
- `Resource.duplicate(deep: Boolean = false)` (was `subresources`) and
  `duplicateDeep(deepSubresourcesMode: Resource.DeepDuplicateMode = ...)`.
- `Resource.generateSceneUniqueId()` loses `@JvmStatic`: a Java caller writes
  `Resource.Companion.generateSceneUniqueId()`.
- The primary constructors of generated classes are public (`Resource(handle)`
  is a borrowed view: never `close()` a wrapper you made over a handle).

**`RefCounted` and `GodotObject`.**

- `RefCounted.getReferenceCount()` returns `Int` (was `Long` on desktop): write
  `?: 0`, not `?: 0L`. The constructor is `internal` everywhere; use
  `RefCounted.fromHandle(handle)` for a borrowed view.
- `GodotObject.set(property, value)` returns `Unit` instead of an always-zero
  `Long`: drop `val r = node.set("x", 1)`.
- `GodotObject.fromHandle` and `RefCounted.fromHandle` are public on every
  platform.

**`TabBar`, `AnimationPlayer`, `Button`, `LineEdit`, `Range`, `Slider`.** Twelve
desktop properties take the generator's names (the Godot property name), and the
accessor pairs underneath are untouched, so a script that called `getMin()` /
`setMin()` needs no edit:

| Class | 0.4 | 0.5 |
| --- | --- | --- |
| `Button` | `buttonIcon` | `icon` |
| `Button` | `textAlignment` | `alignment` |
| `LineEdit` | `horizontalAlignment` | `alignment` |
| `LineEdit` | `placeholder` | `placeholderText` |
| `LineEdit` | `expandToTextLengthEnabled` | `expandToTextLength` |
| `LineEdit` | `caretBlinkEnabled` | `caretBlink` |
| `LineEdit` | `caretMidGraphemeEnabled` | `caretMidGrapheme` |
| `Range` | `min` / `max` | `minValue` / `maxValue` |
| `Range` | `useRoundedValues` | `rounded` |
| `Range` | `expRatio` | `expEdit` |
| `Slider` | `ticks` | `tickCount` |

Also: `Range.valueChanged(newValue)` (it bound a private virtual, not the
signal) is gone, and `Range.Signals.valueChanged` is unchanged;
`Range.setPage`'s parameter is `pagesize`; `LineEdit.getMenu()` returns
`PopupMenu?`; `TabBar.addTab(title: String = "", icon: Texture2D?)` has no
default for `icon`, so `tabBar.addTab("Alpha")` becomes `tabBar.addTab("Alpha", null)`;
`AnimationPlayer.Signals.animationFinished` lives on its base class, write
`AnimationMixer.Signals.animationFinished` (same string).

Script: none for any of this; the compiler finds every site.

### Tween and tweeners

```kotlin
// 0.4
val t: Tween? = self.createTween()
t?.setLoops(3L)
if ((t?.getLoopsLeft() ?: 0L) > 0L) { }
t?.tweenProperty(self, "position", target, 0.5)
    ?.setTrans(Tween.TRANS_BACK)
    ?.setEase(Tween.EASE_OUT)

// 0.5
val t = self.createTween()                    // Tween, never null
t.setLoops(3)                                  // loops: Int
if (t.getLoopsLeft() > 0) { }                  // getLoopsLeft(): Int
t.tweenProperty(self, "position", target, 0.5)
    .setTrans(Tween.TransitionType.BACK)
    .setEase(Tween.EaseType.OUT)               // non-null chain
```

- `createTween()` and `getProcessedTweens()` are members of `Node` and
  `SceneTree` on every platform. If your code imports
  `net.multigesture.kanama.api.createTween`, `...getProcessedTweens` or (iOS)
  `...setExclude` (only builds of `main` between 0.4 and 0.5 needed them),
  delete the import: the calls resolve to the members, including
  `PhysicsRayQueryParameters3D.setExclude(List<RID>)`.
- `Tween.setLoops(loops: Int = 0)` and `getLoopsLeft(): Int` use `Int`;
  `tweenSubtween(subtween: Tween)` takes a non-null `Tween`.
- The `Tweener` family is generated once. The fluent setters and the builder
  methods return non-null types (Godot marks them required; a null from the
  engine throws), so `?.` in a chain compiles with a warning: change it to `.`.
- iOS `Tween.tweenProperty(target, property, finalValue, duration)` takes any
  Variant value (a `Double` rotation, a `Vector3`, a typed enum); it used to
  tween everything but a `Vector2` or `Color` to `Vector2(0, 0)`.
  `Tweener.setTrans` / `setEase` live on `PropertyTweener` and `MethodTweener`
  (where Godot declares them); `tweenMethod` returns `MethodTweener`.

Script: `migrate_enum_constants.py` rewrites the `Tween.TRANS_*` / `EASE_*`
constants; the rest is by hand.

### Engine, BoxMesh, AudioStreamPlayer, ResourceLoader and other classes generated once

Twenty classes that were written per platform are generated once now
(`ConfigFile`, `ENetMultiplayerPeer`, `InputEventKey`, `InputEventMouseMotion`,
`LightmapGI`, `MeshDataTool`, `NoiseTexture2D`, `SceneMultiplayer`,
`ShaderMaterial`, `SurfaceTool`, `ImageTexture`, `ParticleProcessMaterial`,
`ProceduralSkyMaterial`, `ProjectSettings`, `BoxMesh`, `BoxShape3D`,
`AudioStreamPlayer`, `ResourceLoader`, `Engine`, ...). The desktop names,
overloads and factories are kept. What breaks:

```kotlin
// 0.4
Engine.registerSingleton("Settings", obj.handle)
val sub: Long = boxMesh.subdivideWidth
player.maxPolyphony = n.toLong()
ResourceLoader.addResourceFormatLoader(loaderOrNull)

// 0.5
Engine.registerSingleton("Settings", obj)     // the object, not its handle
val sub: Int = boxMesh.subdivideWidth          // BoxMesh.subdivide*, AudioStreamPlayer.maxPolyphony: Int
player.maxPolyphony = n.toInt()
ResourceLoader.addResourceFormatLoader(loader) // non-null ResourceFormatLoader
```

- `Engine.registerSingleton` still rejects a `RefCounted` instance, now with an
  `IllegalArgumentException` on every platform (desktop threw
  `IllegalStateException`).
- `ResourceLoader.ThreadLoadStatus`, the data class, is
  `ResourceLoader.ThreadLoadProgress(status, progress)`: `ThreadLoadStatus` is
  Godot's enum name now. `loadThreadedRequest` returns `GodotError`.
- **iOS compile errors:** `Engine.getMainLoop()` returns `MainLoop?` (it returned
  a `GodotHandle`; use `Engine.getMainLoop()?.handle`); `fromResource(value)` of
  `ShaderMaterial`, `ParticleProcessMaterial` and `ProceduralSkyMaterial` takes a
  non-null `Resource` (`res?.let { ShaderMaterial.fromResource(it) }`); the
  iOS-only `Long` overload of `ShapeCast3D.getCollisionPoint` is gone (pass an
  `Int`: `getCollisionPoint(Int)`, and `getCollisionCount()` returns `Int`);
  `PhysicsRayQueryParameters3D.create(from, to)` returns
  `PhysicsRayQueryParameters3D?` there too (add `?: return` or `!!`).
- **iOS, calls compile unchanged:** `AudioStreamPlayer.play(fromPosition: Double
  = 0.0)` and `setStreamFromPath(path)` (it gains desktop's `cacheMode`);
  `NoiseTexture2D.setGenerateMipmaps(generate)`;
  `ProjectSettings.getSettingDouble(name, defaultValue = 0.0)`;
  `SurfaceTool.commit(existing: ArrayMesh? = null, flags: Long = 0)` is one
  function with defaults, so only a function reference `SurfaceTool::commit`
  changes.
- Renamed parameters matter only to a call that names its arguments:
  `AudioStreamPlayer.setBus(bus)` / `setPitchScale(pitchScale)` /
  `setVolumeDb(volumeDb)` / `setStreamPaused(paused)` on iOS.

Script: none.

### `GD` and `Mathf`

`GD` is generated once from Godot's utility functions (all 114, the same on every
platform, including Web), and `Mathf` is common code.

```kotlin
// 0.4
GD.typeString(type.toInt())
GD.errorString(err.toInt())
GD.randfn()                      // had defaults
val a = Mathf.inverseLerp(from = 0.0, to = 10.0, value = x)   // named argument, iOS

// 0.5
GD.typeString(type)              // Long
GD.errorString(err)              // Long
GD.randfn(0.0, 1.0)              // Godot's randfn has no defaults
val a = Mathf.inverseLerp(from = 0.0, to = 10.0, weight = x)
```

- Parameters are named as Godot names them (`GD.sin(angleRad)`,
  `GD.degToRad(deg)`, `GD.seed(base)`, `GD.typeOf(variable)`, `GD.hash(variable)`),
  which matters only to a call that names its arguments.
- `GD.typeString` and `GD.errorString` take a `Long` (a literal still compiles).
- `GD.isInstanceValid` takes `GodotObject?` on every platform (desktop 0.4.0
  took `Any?`); for an instance id use `GD.isInstanceIdValid(id)`. iOS:
  `GD.degToRad` / `radToDeg` name their parameter `deg` / `rad`.
- Web, in builds of `main` between 0.4 and 0.5 (0.4.0's preview had no `GD.print`
  and a `Mathf` with only `abs` and `log`): `GD.print(message)` is
  `GD.print(vararg values)`; `Mathf.pow(x, y)`, `Mathf.PI` / `Mathf.TAU` are
  `const`; see [Web](#web).

Script: none.

### `InputEventMouseButton`

One shared class on every platform. On iOS it now extends `InputEventMouse` (it
extended `InputEvent`), so every member compiles everywhere.
`InputEventMouseButton.from(event)` returns `null` for an `InputEventScreenTouch`
on iOS; before, it wrapped the touch as a mouse button. A touch reaches
mouse-button code through Godot's
`input_devices/pointing/emulate_mouse_from_touch` setting (on by default), as on
every other platform. Code that wants the touch itself reads
`event.castOrNull<InputEventScreenTouch>()`; `InputEventScreenTouch.create()` and
`InputEventScreenTouch.from(event)` are new.

Script: none.

### Coroutines: `KanamaCoroutineOwner`

`KanamaCoroutineOwner` is removed and `KanamaScope` is internal, on every
platform: a script's coroutines live on its `KanamaScript` scope, which freeing
the script cancels.

```kotlin
// 0.4
class Hud(godotObject: GodotHandle) :
    KanamaScript<CanvasLayer>(godotObject, ::CanvasLayer), KanamaCoroutineOwner {
    override val kanamaScope = KanamaScope()
    fun flash() { kanamaScope.launch { SceneTree.delaySeconds(1.0); self.hide() } }
}

// 0.5
class Hud(godotObject: GodotHandle) : KanamaScript<CanvasLayer>(godotObject, ::CanvasLayer) {
    fun flash() { launch { wait(1.0); self.hide() } }
}
```

`kanamaScope.launch(...)` with arguments is `scriptScope.launch(...)`, and
`kanamaScope.cancel()` is `cancelCoroutines()` (the scope stays usable). The
generated registrar no longer cancels the scope on `_exit_tree`; leaving the tree
does not stop a coroutine (as in GDScript), freeing does. `KanamaScript` itself
moved into common code with unchanged members: a script does not notice.

Script: none; the change is mechanical (`kanamaScope.launch` becomes `launch`).

### Changes that need no edit

Announced because the signature snapshots record them; your code compiles as is.

- Web classes `Texture2D`, `AudioStream`, `PackedScene`, `InputEventKey`,
  `InputEventMouseButton`, `Mesh`, `Material`, `ButtonGroup`, `ConfigFile`,
  `FastNoiseLite`, `MeshLibrary`, `KinematicCollision3D` and `ScriptResource` no
  longer declare `close()` and `AutoCloseable` themselves; both come from
  `RefCounted`.
- The typed signal classes `TypedSignal`, `Signal0` ... `Signal5` were reshaped
  only against an unreleased first version (`TypedSignal.owner` is `emitter`;
  the nullability of a few signal arguments follows a scan of Godot's source). 0.4 had none of these declarations.
- `kanamaIosRuntimeReleaseCallable` and `kanamaIosRuntimeDispatchCallable` are
  entry points of the iOS C shim, not for scripts.
- On Web `KanamaScript.self` is public, as on the other platforms, so shared code
  can reach an autoload's node (`Autoloads.Events.self`).
- The per-platform `kotlinScriptInstance<T>()` extension on nodes is now the common
  `GodotObject.kotlinScriptInstance<T>()`; calls compile unchanged.
- `GodotSignal`'s default arguments became overloads (`connect(target, method)`
  plus `connect(target, method, flags)`, and so on): positional, trailing-lambda
  and named-argument calls compile unchanged. A script compiled against an old
  `kanama.jar` calls the removed `$default` methods: recompile the scripts (the
  normal build does).

## Enums and bitfields

Every Godot enum and bitfield is a `@JvmInline value class` wrapping its `Long`:
764 class enums, 22 global enums and the value-type enums, 793 in all. A class
enum nests in its class (`Node.ProcessMode`, `Tween.TransitionType`,
`GodotObject.ConnectFlags`, `Vector3.Axis`); a global enum is top-level in
`net.multigesture.kanama.api` under Godot's name. Every parameter, return and
property Godot types as an enum or bitfield uses the class. There are no
deprecated aliases: the old `const val` values are gone.

```kotlin
// 0.4
self.setProcessMode(Node.PROCESS_MODE_ALWAYS)
tween.setTrans(Tween.TRANS_SINE)
if (event is InputEventKey && event.keycode == InputEventKey.KEY_W) { }
button.mouseFilter = Control.MOUSE_FILTER_IGNORE
val err = node.connect("tree_exiting", target, "on_exit", GodotObject.CONNECT_DEFAULT)
if (err != 0L) { }
ResourceLoader.loadThreadedRequest(path, "", false, ResourceLoader.CACHE_MODE_REUSE)

// 0.5
self.setProcessMode(Node.ProcessMode.ALWAYS)
tween.setTrans(Tween.TransitionType.SINE)
if (event is InputEventKey && event.keycode == Key.W) { }
button.mouseFilter = Control.MouseFilter.IGNORE
val err = node.connect("tree_exiting", target, "on_exit", GodotObject.ConnectFlags(0L))
if (err != GodotError.OK) { }
ResourceLoader.loadThreadedRequest(path, "", false, ResourceLoader.CacheMode.REUSE)
```

- **Four global enums carry a Kanama name** because Godot's name collides with
  Kotlin or with Kanama: `Error` is `GodotError`, `PropertyHint` is
  `GodotPropertyHint` (Kanama's annotation constants `annotations.PropertyHint`
  stay as they are), `Variant.Type` is `VariantType`, `Variant.Operator` is
  `VariantOperator`. 0.4 had no typed global enums, so you meet these names in
  the return type of `connect` and in values such as `GodotError.OK`.
- **Value names drop the enum's common prefix**, by the rule Godot's C# bindings
  use, keeping SCREAMING_CASE: `PROCESS_MODE_ALWAYS` is `ALWAYS`, `TRANS_SINE` is
  `SINE`, `KEY_W` is `W`. A name that would start with a digit keeps a word
  (`Key.KEY_0`); enums with no common prefix keep Godot's names
  (`GodotError.ERR_FILE_NOT_FOUND`). The prefix of each enum is frozen, so a later
  Godot adding a value never renames its siblings. The full old to new table is
  [Enum Constant Migration](../reference/generated/enum-migration.md).
- **The hand-written subsets move to the real enums:** `InputEventKey.KEY_*` is
  `Key.*`, `InputEventMouseButton.MOUSE_BUTTON_*` is `MouseButton.*`,
  `PhysicsBody3D.BODY_AXIS_*` is `PhysicsServer3D.BodyAxis.*`, and Kanama's own
  `GodotObject.CONNECT_DEFAULT` is `GodotObject.ConnectFlags(0L)` (Godot names no
  zero flag). Non-enum class constants (`Node.NOTIFICATION_READY`) stay `const val`.
- **Raw values stay reachable:** `Node.ProcessMode(3L)` builds any value, `.value`
  reads the `Long`. Bitfields add `or`, `and`, `xor`, `inv()` and `in`
  (`contains`); the empty set is `X(0L)`.
- **Dynamic calls take the typed values.** Every enum class implements
  `GodotEnumValue`, and `call`, `set`, `callDeferred`, `emitSignal`,
  `ConfigFile.setValue` and Array/Dictionary elements pass it as the INT it stands
  for. What comes back from a dynamic path (`get`, `call`, Variant returns) is
  still a `Long`.
- `GodotObject.connect`, `GodotSignal.connect` and the lambda overloads take
  `flags: GodotObject.ConnectFlags` and return `GodotError`;
  `SignalConnection.error` is a `GodotError` (compare with `GodotError.OK`, not
  `0L`). The KSP signal helpers `connect<Signal>(instance, target, flags = ...)`
  and `rpc<Method>` / `rpcId<Method>` follow.
- `ResourceLoader.loadThreadedGetStatus` returns `ResourceLoader.ThreadLoadStatus`,
  the enum, and the `cacheMode` parameters take `ResourceLoader.CacheMode`.
- **The 38 object returns Godot marks required are non-null** (`Node.createTween()`,
  `SceneTree.createTween()` / `createTimer()` / `getRoot()` / `getMultiplayer()`,
  `CanvasItem.makeInputLocal`, `InputEvent.xformedBy`, every `Tween` builder and
  fluent setter, ...). A chain uses `.` instead of `?.`. A null from the engine is
  an engine bug and throws `IllegalStateException("Godot returned null from
  required <Class>.<method>")`.
- Web has the same types and names. Removed on Web: the hand subsets, the enum
  `const val`s (`Window.MODE_*`, `DisplayServer.VSYNC_*`, ...) and the Web-only
  top-level `BodyAxis` (use `PhysicsServer3D.BodyAxis`). `SceneTree.getRoot()` on
  Web returns `Window` (it was a raw `GodotHandle`).
- A Godot enum class is also an `@Export` type and a `@Signal` / `@Rpc` argument
  type; see [Exporting Enums](../game-dev/properties-resources.md#exporting-enums).
  An engine virtual you override with `@OverrideVirtual` takes the value class
  where Godot types it as an enum: an override still declaring `Long` fails the
  build naming the type to use.

**Script:** `migrate_enum_constants.py path/to/kotlin-src` rewrites `Owner.OLD` to
`Owner.Enum.NEW` (and the hand subsets), adds the imports a file needs, and lists
what a human must fix. It cannot fix a raw number passed where an enum is now
required (`setProcessMode(3L)` becomes `setProcessMode(Node.ProcessMode.ALWAYS)`),
a `== 0L` error check, or a `List<Long>` settings table compared with the stored
numbers; those show up as compile errors.

## Script annotations and export hints

### One annotation per concept

The aliases are removed with no deprecation period. Each leftover is kept as a
tombstone annotation that fails to compile with a message naming its
replacement, and the KSP processor reports it at its declaration.

| Removed | Use |
| --- | --- |
| `@RegisterFunction`, `@Method` | nothing: public functions are registered automatically; `@GodotName("...")` for another Godot name |
| `@ScriptProperty`, `@RegisterProperty` | `@Export` (`name`, `usage`) |
| `@ClassName` | `@GlobalClass` |
| `@ToolButton` | `@ExportToolButton` |
| `@Ready`, `@EnterTree`, `@ExitTree` | `@OnReady`, `@OnEnterTree`, `@OnExitTree` |
| `@Process`, `@PhysicsProcess` | `@OnProcess`, `@OnPhysicsProcess` |
| `@Input`, `@UnhandledInput`, `@ShortcutInput`, `@UnhandledKeyInput` | `@OnInput`, `@OnUnhandledInput`, `@OnShortcutInput`, `@OnUnhandledKeyInput` |

```kotlin
// 0.4
@ScriptClass(attachTo = "Node2D")
@ClassName
class Player(godotObject: GodotHandle) : KanamaScript<Node2D>(godotObject, ::Node2D) {
    @Ready fun ready() { }
    @Process fun process(delta: Double) { }
    @RegisterFunction(name = "_on_start_pressed") fun onStartPressed() { }
    @Input fun input(event: GodotObject) { val e = InputEvent(event.handle) }
}

// 0.5
@ScriptClass(attachTo = "Node2D")
@GlobalClass
class Player(godotObject: GodotHandle) : KanamaScript<Node2D>(godotObject, ::Node2D) {
    @OnReady fun ready() { }
    @OnProcess fun process(delta: Double) { }
    @GodotName("_on_start_pressed") fun onStartPressed() { }
    @OnInput fun input(event: InputEvent) { val e = event }
}
```

### Public functions are registered automatically

Every public function of a script class is registered with Godot under its
snake_case name (`fun showMessage()` is `show_message`), as GDScript exposes
every `func`; `@RegisterClass` types follow the same rule. `private`,
`protected` and `internal` functions stay Kotlin-only, and so do `suspend`,
extension and generic functions and an `override` of a non-script member such as
`toString()`. Keep a name a `.tscn` connection saved with `@GodotName`. New
build errors, each naming the fix:

- a public function whose parameter or return type Godot cannot carry: make it
  `internal` or `private`;
- a public function whose snake_case name is an engine method of the attached
  class (`fun queueFree()` would shadow `queue_free`): rename it, make it
  Kotlin-only, or confirm with `@GodotName("queue_free")`;
- a `vararg` public function, an empty `@GodotName("")`, two functions on one
  Godot name (on a `@RegisterClass`, including the `get_x` / `set_x` accessors of
  `@Export var x`);
- a Kanama annotation on a non-public function (it used to be ignored silently);
- a function named like an engine virtual without an annotation
  (`fun _process(delta: Double)` never ran; the error names `@OnProcess` or
  `@OverrideVirtual`);
- an input handler still taking `GodotObject`.

### Input handlers take `InputEvent`

`@OnInput`, `@OnUnhandledInput`, `@OnShortcutInput` and `@OnUnhandledKeyInput`
functions are `fun input(event: InputEvent)` on every platform, so the
`InputEvent(event.handle)` rewrap goes.

### Typed export hints

`@Export` loses its `hint` and `hintString` parameters (`@Export(name, usage)`
remains). Each GDScript `@export_*` annotation has a typed twin that exports the
property by itself and builds the hint string the way GDScript does:

| 0.4 | 0.5 |
| --- | --- |
| `@ScriptProperty(hint = PropertyHint.RANGE, hintString = "0,100,1")` | `@ExportRange(0.0, 100.0, 1.0)` |
| `@ScriptProperty(hint = PropertyHint.RANGE, hintString = "0,1,0.01,or_greater")` | `@ExportRange(0.0, 1.0, 0.01, orGreater = true)` |
| `@ScriptProperty(hint = PropertyHint.ENUM, hintString = "A,B")` | `@ExportEnum("A", "B")` |
| `@ScriptProperty(hint = PropertyHint.FILE, hintString = "*.png")` | `@ExportFile("*.png")` |
| `@ScriptProperty(hint = PropertyHint.MULTILINE_TEXT)` | `@ExportMultiline` |
| `@ScriptProperty(name = "x", hint = <other>, hintString = "s")` | `@Export(name = "x") @ExportCustom(<other>, "s")` |

A range hint string now reads like GDScript's (`"0.0,100.0,1.0"` where the old
raw string was `"0,100,1"`); the inspector behaves the same. `@Export` next to a
typed hint sets only `name` and `usage`. The full set also has `@ExportFlags`,
`@ExportDir`, `@ExportGlobalFile`, `@ExportGlobalDir`, `@ExportPlaceholder`,
`@ExportExpEasing`, `@ExportNodePath`, `@ExportStorage`, `@ExportColorNoAlpha`
and the 2D/3D render, physics and navigation flag variants; see
[Export Hints](../game-dev/properties-resources.md#export-hints).

### Inheritance and generated autoloads (behaviour change)

A script class that extends another class of the same build now has all of the
base's members: exports, signals, lifecycle handlers, `@OverrideVirtual`s and
registered functions, with no forwarding overrides. The most derived declaration
is the one Godot calls; two functions of the chain on one Godot name are build
errors. Before, a subclass registered only its own declarations, and an
unannotated `override fun ready()` registered a public method `ready`. The
processor also generates `net.multigesture.kanama.generated.Autoloads` from the
`[autoload]` section of `project.godot` (see
[Autoloads](../game-dev/scripts.md#autoloads)).

**Script:** `python3 scripts/migrate_script_annotations.py <kotlin-src>` renames the
removed annotations and their imports, deletes `@RegisterFunction` / `@Method`
(a custom name becomes `@GodotName`), retypes input handlers and drops their
rewraps, then lists what needs a human. With `--report-registered` it also lists
each public function that was not registered before and now is (mark it
`internal` or `private` to keep it Kotlin-only).
`python3 scripts/migrate_export_hints.py <kotlin-src>` rewrites the hint
parameters to the typed annotations (a hint string it cannot parse is kept as
`@ExportCustom`). Run the annotation script first.

## Ownership and `close()`

`close()` means "release my reference", and 0.5 makes the rule the same
everywhere. See [Resource Ownership](../game-dev/godot-api.md#resource-ownership).

- **A forgotten `close()` is a late release, not a leak.** An owned `RefCounted`
  wrapper you never close is released on the main thread at the next frame after
  the garbage collector drops it, for wrappers made on any thread, on desktop,
  Android 13+ and iOS, and on Web through the browser's collector. `close()` and
  `use { }` still release at once. Android 8-12 debug installs have no
  `java.lang.ref.Cleaner`, so there `close()` stays the only release. Close big
  resources (meshes, textures, audio) and anything you make in a loop yourself:
  native memory is invisible to the collector. `kanama/debug/log_gc_releases` logs
  each collector release with the creation site.
- **`@ManualGodotLifetimeApi` is deprecated and no longer applied.** `close()`
  carried a `RequiresOptIn` warning in 0.4. Delete `@OptIn(ManualGodotLifetimeApi::class)`;
  it still compiles with a deprecation warning, and nothing replaces it.
- **Getters are owned.** Every `RefCounted`-typed return, plain getters and the
  `Tweener` a `tweenProperty(...)` returns included, is a reference you may
  `close()`; so is each `RefCounted` element of a returned typed `Array`, and the
  `from*` downcasts and checked casts to a `RefCounted` class
  (`res.cast<Texture2D>()`) take a reference of their own. A wrapper you mint
  yourself over a handle (`fromHandle`, a wrapper constructor) is a borrowed view:
  `close()` on it releases nothing (debug builds warn); before, it released a
  reference the view never took.
- **A script object keeps its resource alive**, as in GDScript: holding the Kotlin
  object of a resource script keeps the resource alive after the wrapper it came
  from is closed or collected.
- **A script property's references belong to its resource or node**, not to the
  Kotlin object.
- **Elements of an `Object.call` container result are owned.** `node.call(...)`
  (and `callv`, `callDeferred`, `GodotCallable.call`) returning an `Array` or
  `Dictionary` of `RefCounted` objects hands back owning wrappers; close them or
  let the collector release them. Elements of a `call()` made off the main thread
  get no collector fallback: close them yourself.
- **Wrappers made off the main thread** register the same fallback; see
  [Threads](../game-dev/scripts.md#threads).
- **Open files.** A file closes when its last reference goes, so `use { }` every
  `FileAccess` (see [FileAccess and DirAccess](#fileaccess-and-diraccess)).

Script: none. `use { }` and `close()` are unchanged; you only gain permission to
forget one.

## Signals, callables and await

Typed engine signals are an addition, but the supporting changes touch existing
code:

- `GodotObject.connect(signal, target, method, flags)` takes
  `GodotObject.ConnectFlags` and returns `GodotError`, and `SignalConnection.error`
  is a `GodotError` (see [Enums](#enums-and-bitfields)).
- Every engine signal is a typed property: `area.bodyEntered.connect { body -> ... }`
  inside a `KanamaScript` binds the lambda to the script's object, as a GDScript
  lambda is bound to its script; `connect(flags) { }` takes
  `GodotObject.ConnectFlags.ONE_SHOT` / `DEFERRED`; `await()` suspends inside
  `launch { }`. Declared `@Signal`s get typed handles too. See
  [Signals and Callbacks](../game-dev/signals.md).
- The untyped `connect(target, argumentCount) { args -> }` no longer rejects more
  than three arguments.
- A `StringName` read through the generic decode (`call`, `get`, an untyped signal
  lambda's `List<Any?>`) is its text, not `null`.
- `GodotCallable` (call, callv, callDeferred, isValid, getObject, getArgumentCount,
  rpc, rpcId) and `GodotSignal.isConnected` / `getConnections` / `hasConnections`
  exist on every platform, Web included.
- **Hot reload (desktop):** a lambda connection belongs to the build that made it.
  Once that build's last script object is freed, every lambda connection it made
  is disconnected on the next frame, with one warning, even when its target
  outlived the script (the scene tree, a GDScript autoload). Make lambda
  connections in `_ready`, so the new build's objects connect again with the new
  code. By-name connections are Godot's and survive a reload. See
  [Hot reload](../game-dev/signals.md#hot-reload).

Script: none; `migrate_enum_constants.py` rewrites the `CONNECT_*` constants, and
the `== 0L` result checks are by hand.

## Web

Web is the area that changed most toward the other platforms. Rebuild and
re-export every Web game (bridge protocol 39 <!-- kanama-claim: protocol -->). See the [Web export guide](../exporting/web.md).

**A 0.4.0 Web project used the preview API.** 0.4.0 shipped Web as a two-demo
preview (a small set of hand-written wrappers): it had no `GodotObject.set`,
`call` or `callDeferred`, no `Basis`, no `GD.print`, and a `Mathf` with only `abs`
and `log`. Most of what follows describes the change from builds of `main`
between 0.4 and 0.5, where Web had grown those members. If your Web code is
still the 0.4.0 preview, follow the compiler and the
[enum script](#enums-and-bitfields): for example `GodotObject.CONNECT_ONE_SHOT`
becomes `GodotObject.ConnectFlags.ONE_SHOT`, `GodotSignal.connect` returns a
`GodotError` (it returned `Long`), and `InputEventMouseButton.getButtonIndex()`
returns a `MouseButton` (it returned `Long`).

```kotlin
// builds of main between 0.4 and 0.5 (Web)
import net.multigesture.kanama.api.call               // top-level extension
node.set("position:x", 5.0)
node.call("play", "walk")                             // queued, returned Unit
val col = basis.getColumn(0)
val b = Basis.fromAxisAngle(Vector3.UP, angle)
GD.print("hello")
val d = Mathf.pow(value = 2.0, exponent = 3.0)

// 0.5 (Web, as on desktop)
node.setIndexed("position:x", 5.0)                    // set(...) is Object.set: a path changes nothing
node.call("play", "walk")                             // immediate, returns the result (Any?); no import
val col = basis.x
val b = Basis.IDENTITY.rotated(Vector3.UP, angle)
GD.print("hello")                                     // GD.print(vararg values)
val d = Mathf.pow(x = 2.0, y = 3.0)
```

The notes below are written against those `main` builds.

- **`GodotObject.set`** is `Object.set` as on desktop. A property path such as
  `"position:x"` names no property and changes nothing; `setIndexed(path, value)`
  is the path form. `set(property: String, value: Any?)` replaces the typed
  overloads for `Double`, `Long`, `String`, `Vector2` and `GodotEnumValue`, so a
  call that names the first argument writes `property =`. A name without a `:`
  writes the same property as before.
- **`call` / `callDeferred`.** The typed overloads (`call(method, String)`,
  `call(method, Vector3, Vector3)`, `callDeferred(method, GodotObject)`, ...) and
  the top-level `GodotObject.call` / `callDeferred` extensions are removed. The
  Variant `call(method, vararg args): Any?` and `callDeferred(method, vararg
  args): Any?` take those calls and run immediately, as on desktop.
- **`GD`, `Mathf`, `GodotCallable`** are the shared classes. `GD` has all 114
  utility functions, `Mathf.isEqualApprox` uses Godot's tolerance instead of
  `1e-6`, and the Web-only spellings sources import by name
  (`net.multigesture.kanama.api.radToDeg`, `randf`, `randi`, `randomize`,
  `pushError`, `Basis`) are kept.
- **Value types** are the shared classes (0.4.0's preview had only `Vector2`,
  `Vector2i`, `Rect2` and a `Float` `Color`): `Basis.getColumn(i)` and
  `Basis.fromAxisAngle` are gone, and parameter names follow desktop
  (`Basis(x, y, z)`, `withX(value)`, `lookingAt(target, up, useModelFront)`,
  `interpolateWith(xform, weight)`, `withBasis(value)`, `bounce(n)`, `times(scale)`).
  `Vector4`, `Vector4i`, `AABB`, `Transform2D` and `Projection` now exist on Web.
- **Wrappers follow Godot's argument names and `int32` as `Int`**, as on desktop.
  A call that passed a `Long` where Godot has an `int32` needs `.toInt()`. The
  import-compat extensions keep `import net.multigesture.kanama.api.setProcess`
  style imports resolving.
- **`Window`** is a generated `Viewport` subclass, `Node.getWindow()` returns the
  root window (null outside the tree), and `SceneTree.getRoot()` returns `Window`.
- **Ownership and lifetime** now follow desktop (see [Silent changes](#silent-changes-to-check)):
  a second `close()` does nothing, `close()` ends only that wrapper's reference,
  and `ResourceLoader.loadThreadedGet` removes the finished load.
- `KanamaScript.self` is public. Layer and collision masks take all 32 bits.
- **Ray queries and RIDs** take desktop's API. A `RID` is the same value class as
  on desktop (`body.getRid()`), the query's `exclude` is a `List<RID>`,
  `getWorld3d()` and `directSpaceState` are nullable, and `intersectRay` answers
  desktop's `Map` (empty on a miss) instead of a `RayHit?`:

  ```kotlin
  // builds of main between 0.4 and 0.5 (Web)
  val query = PhysicsRayQueryParameters3D.create(from, to, 0xffffffffL, listOf(self))!!
  val hit = self.getWorld3d().directSpaceState.intersectRay(query)
  if (hit != null) target = hit.position
  springArm.addExcludedObject(body)

  // 0.5 (Web, as on desktop)
  val query = PhysicsRayQueryParameters3D.create(from, to, 0xffffffffL, listOf(self.getRid()))!!
  val hit = self.getWorld3d()?.directSpaceState?.intersectRay(query) ?: emptyMap()
  if (hit.isNotEmpty()) target = hit["position"] as Vector3
  springArm.addExcludedObject(body.getRid())
  ```

  A ray is still one engine crossing on Web. `RayCast3D.addExceptionRid`,
  `getColliderRid` and the query flags (`collideWithAreas`, `hitFromInside`, ...)
  are new there.
- `Texture2D.close()` and friends come from `RefCounted` (nothing to migrate).

Script: none; the compiler lists the sites. The enum script's rewrites apply to
Web sources too.

## iOS

- **Exports require iOS 15.0**, Godot 4.7.2's default `application/min_ios_version`
  (Xcode 27 refuses a lower deployment target). Set it in your export preset.
- **A `Map` with non-String keys no longer converts to a generic Godot `Dictionary` on iOS.** A
  script method returning a `Map<Int, V>` (or a `List` holding one), or a `Map` argument with such
  keys to `call` / `callv` / `set`, used to reach Godot with the keys turned into Strings (`"1"`); it
  now fails with `Unsupported Dictionary key type: <class>`, as on desktop. Return a
  `Map<String, V>`, or declare the property as a typed `@Export` `Map<K, V>`, which keeps its keys.
- **A script method iOS cannot dispatch now fails the iOS build** instead of
  warning and silently never running: a registered function or virtual with a
  parameter type the iOS call path does not pass (`Map`, `List`,
  `Packed*Array`), an `@OverrideVirtual` whose return type iOS does not
  marshal, or an exported property iOS cannot deliver (a `RID`; `Map` exports are delivered). The error names the method, the parameter and the supported types;
  change the type, or build with `-PkanamaIosAllowSkips=true` to accept the skip
  (a warning again). `RID` and `Any?` parameters, and `NodePath` and every value
  type as an `@OverrideVirtual` return, are dispatched now.
- **Removed, replaced or retyped on iOS (compile errors):**
  `GodotObject(handle: Long)` is gone, use `GodotObject.fromHandle(handle)` or a
  `GodotHandle`; the `emitSignal(name, Int | Long | Vector2i): Int` overloads are
  gone, `emitSignal(name, vararg args)` returns `Unit`; `connect` / `disconnect`
  name the parameter `signal` (was `signalName`); `connectBound` / `disconnectBound`
  are internal; `RefCounted`'s constructor is internal; `GodotObject` is no longer
  `AutoCloseable` (0.4.0 iOS had a no-op `close()`), so `close()` or `use { }` on a
  wrapper that is not a `RefCounted` no longer compiles (like desktop: `RefCounted`
  still owns `close()`); building a `GodotObject` over a NULL handle now throws
  instead of yielding a wrapper with `instanceId == 0`; see also the iOS items
  under [Kotlin API surface](#kotlin-api-surface).
- **`toString()` is an engine call on iOS** (`Object.to_string`, `<Node2D#123>`),
  as on desktop. Calling it on a wrapper whose object was freed (a string
  template, a log line) dereferences a dangling pointer: check `instanceId` /
  `GD.isInstanceValid` first.
- **`GD.print` and the other print functions write to Godot's logger**, not
  stdout. On a device that is the system log; read it in Xcode's console or
  Console.app. `GD.seed(n)` reproduces GDScript's sequence on the phone.
- **Script errors name the Kotlin file and line** in debug builds
  (`at: Player.ready (res://kotlin-src/Player.kt:42)`) when the app keeps its symbol
  table: build Godot's exported Xcode project. Godot's `.ipa` export and one-click
  deploy strip the app, so there a report names the callback with line 0.
- **`System.getenv` reads the process environment** (it returned `null`).
- iOS gained the full `Tween`, `FileAccess`, `DirAccess`, `Engine`,
  `ProjectSettings`, `ResourceLoader`, `AudioStreamPlayer` and `GD`; see [Kotlin API
  surface](#kotlin-api-surface). `SceneTree.delaySeconds` waits in engine frames
  (pause and time scale apply). `Mathf.clamp` no longer throws when `min > max`.
- The runtime self-tests no longer run or ship in users' builds: they compile in
  only with `-PkanamaIosSelfTest=true`.

Script: none.

## Android

- The Android runtime uses PanamaPort `0.1.5-kanama-r8.1` (it was `0.1.3-kanama-r8.4`),
  rebased for the Android 17 September update (apps built on the old fork aborted
  at startup on that update). The coordinate is the default of `-PkanamaPanamaPortCore`;
  nothing to change unless you pinned the old one. Use Godot 4.7.2 export
  templates.
- The class-token tables behind `castOrNull`, `requireAs` and `node<T>()` keep
  every wrapper class's constructor reachable, so **R8 can no longer drop unused
  wrapper classes**: expect a larger minified release build.
- Release builds still require Android 13+; debug installs are validated to
  Android 9, where `close()` stays the only release (see [Ownership](#ownership-and-close)).

Script: none.

## Tooling, Gradle and the editor

- **JDK.** The build pins `jvmToolchain(25)`. The release-kit `settings.gradle.kts`
  now applies the foojay toolchain resolver, so a machine with only another JDK
  downloads JDK 25 once; add the same `plugins { id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0" }`
  block to your own `settings.gradle.kts` if you copied one. The editor picks the
  JDK itself (the `kanama/build/jdk_path` editor setting, then `JAVA_HOME`, then
  the usual install locations), and a `JAVA_HOME` older than 25 is skipped. See
  [JDK Selection](editor-workflow.md#jdk-selection).
- **`addons/kanama/kanama-project.gradle.kts`** gains the KSP inputs that make
  `Autoloads` regenerate when `project.godot` changes. It ships inside the addon,
  so the new addon brings it; a copy you edited by hand needs the same block.
- **Export guard.** The Kanama Tools plugin checks, when an export begins, that
  every `.kt` script the exported scenes attach is bound. When one is not, it prints
  `Kanama: Export stopped:` with the script, the reason and the fix, and a
  command-line export ends the editor process with a non-zero exit status. Opt out
  with `kanama/tools/export_guard_enabled`.
- **JVM options for desktop games:** `kanama-jvm-options.txt` next to
  `kanama.jar`, the `kanama/jvm/options` project setting, and `KANAMA_JVM_OPTIONS`.
  See [Desktop and Packaging → JVM Options](../exporting/desktop.md#jvm-options).
- **Source checkout and contributors.** Kanama is one Kotlin Multiplatform module;
  `:ios-runtime` no longer exists. Task names: `compileKotlin` is
  `compileKotlinJvm`, `test` is `jvmTest`, `jar` is `jvmJar`, `kspKotlin` is
  `kspKotlinJvm`, and every `:ios-runtime:X` is `X` (`linkDebugStaticIosArm64`,
  ...). `installAddonJar`, `syncExampleAddonJar`, `publishKanamaToMavenLocal`, the
  `package*` tasks, the xcframework tasks and `ktfmtCheck` / `ktfmtFormat` keep
  their names. Sources moved:
  `src/main/kotlin` to `src/jvmMain/kotlin`, `src/test` to `src/jvmTest`, and
  `ios-runtime/src/iosMain/kotlin` to `src/iosMain/kotlin`. The Godot API wrapper
  tree is common code in `src/commonMain/kotlin/net/multigesture/kanama/api`.
- **Documentation moved** (no redirects): `contributing/android-internals.md` is
  `contributing/backends/android.md`, `web-internals.md` is
  `contributing/backends/web.md`, the iOS backend page is
  `contributing/backends/ios.md`, and the generated reports (`api-coverage`,
  `wrapper-generator-report`, `gates`, `ios-backend-handwritten`) are under
  `reference/generated/`.

Script: none.

## Silent changes to check

These compile and still change results. Check each against your project.

**Properties and values**

- **A property set to a value of the wrong type is refused.** A script property of
  type `Boolean`, `Long`, `Double`, `String`, `NodePath`, a vector or value type,
  `Color`, `List<T>` or `Map<K, V>` that received a Variant of another type
  (`null`, a `Vector2` for a `Vector3`, ...) used to become `false`, `0`, `""`, the
  zero vector or an empty collection. Now Godot's conversion rules apply (an `int`
  for a `float`, a `Vector2i` for a `Vector2`, ... are converted) and anything else
  fails with `Expected Vector3 for property 'velocity' but the Variant holds
  Vector2`, naming the property, and the property keeps its value. A registered
  function's argument of a value type (`Quaternion`, `Basis`, `Rect2`,
  `Transform3D`, ...) that received a Variant of another type fails the call with
  the same message instead of becoming zero or identity. On iOS the same rule
  covers only the value-type and `NodePath` properties, where it replaces a bare
  `ClassCastException` (or a dropped `null`); `Boolean`, `Long`, `Double`,
  `String`, `List` and `Map` properties are unchanged on iOS.
- **`GD.clamp(value, min, max)` with `min > max`** matches GDScript: `value` is set
  to `min` if it is less, then to `max` if it is greater, so `GD.clamp(0.0, 3.0, 1.0)`
  is `1.0`. 0.4.0 had no `GD.clamp` (only `clampf` and `clampi`, and `Mathf.clamp`
on top of them), and those gave `3.0`; builds of `main` between 0.4 and 0.5 had a
`GD.clamp` that did too. `clampf` and `clampi` keep Godot's typed result.
- **Value types print and compare like Godot.** `toString()` is GDScript's `str(v)`
  (`(0.1, 0.2)`, not `Vector2(x=0.1, y=0.2)`); `==` compares the stored
  components; basic arithmetic gives Godot's results bit for bit.
- **`Quaternion.inverse()`** on desktop and Android is the engine's (the
  conjugate), where the old local body divided by the squared length and returned
  `IDENTITY` for a zero quaternion. Unit quaternions agree.
- **Range hint strings** read `"0.0,100.0,1.0"`, as GDScript's do; the inspector
  behaves the same.
- **`StringName` values** in generic decodes arrive as text, not `null` (desktop,
  Android, iOS). On iOS, script properties now report their initializer as the
  default value, so a scene packed at runtime stores only what differs.
- **Typed resource loaders check the class.** `ResourceLoader.loadTexture2D`,
  `loadPackedScene`, `loadAudioStream`, `loadLightmapGIData` and
  `loadThreadedGetPackedScene` return `null` and call `pushError` (`'path' is a
  Texture2D, not a PackedScene`) when the resource is of another class; they used
  to wrap it as the requested type. On iOS `loadThreadedGet` now really calls
  Godot's `load_threaded_get`: it consumes the load, and returns `null` with an
  error when nothing was requested for the path.
  `loadThreadedGetStatusWithProgress` fails loudly when Godot returns no progress
  value instead of reporting 0 (desktop throws `IllegalStateException`, iOS
  reports `INVALID_RESOURCE`).

**Random numbers and math (Web)**

- `GD.randf`, `randi`, `randiRange`, `randfRange`, `randfn`, `seed`, `randomize` and
  `randFromSeed` use Godot's global generator on every platform, so
  `GD.seed(n)` reproduces GDScript's sequence everywhere. Web's own generator is
  gone. Each draw is one bridge call.
- `Mathf.inverseLerp(a, a, x)` answers Godot's NaN or infinity (Web answered 0),
  and `Mathf.roundToInt` rounds halves away from zero (Web rounded to even); both
  compare builds of `main` between 0.4 and 0.5, since 0.4.0's Web `Mathf` had
  only `abs` and `log`. `GD.randiRange` / `randfRange` no longer throw for
  `from > to` (they did in 0.4.0).

**Ownership and lifetime**

- **Two wrappers of one object are equal** on desktop, Android and iOS.
  `equals` and `hashCode` (now `final`) compare the instance id, whatever the
  wrapper class (`Node` vs `Node3D`), so `node == other` is `true`, and
  `List.contains`, `Set` and `Map` keys treat them as one. 0.4.0's desktop
  `GodotObject` had no `equals`, so two wrappers were two keys. Check any
  `Set<Node>`, `Map<Node, ...>` or `indexOf` you relied on being identity-based.
- **A call through a freed wrapper throws** on desktop, Android and iOS, in debug
  builds (the editor and debug exports): `IllegalStateException: Invalid access to
  previously freed instance (Node3D, instance id ...)`, contained and reported
  like any script error. Before, the call read freed memory (wrong values, or a
  native crash). Comparing such a wrapper, `GD.isInstanceValid` and `toString()`
  stay silent, and a release export does not check, so keep
  `GD.isInstanceValid(node)` before using an object that may be gone. The Web
  rule is the next bullet.
- **Web:** a node or plain object you hold is valid until the engine frees it,
  whichever script got it, and a node you `queueFree()` stays usable to the end of
  the frame. A handler argument can be kept (`lastHit = body`) and `body == player`
  is `true` for the same node. Using an object the engine freed throws
  `IllegalStateException: Invalid access to previously freed instance` for every
  call that returns a value (before, a bridge message), and a call that returns
  nothing is skipped and reported once as the same script error. A second `close()`
  does nothing (it threw "Unknown or already released"); `close()` ends only that
  wrapper's reference; `ResourceLoader.loadThreadedGet` removes the finished load
  (a later `loadThreadedGetStatus` answers `INVALID_RESOURCE`, it stayed `LOADED`).
  At most 4096 `RefCounted` arguments that only Kotlin holds stay alive on Web;
  copy the values you need instead of keeping thousands of events.
- **Web script exceptions** are reported once through Godot's error log
  (`SCRIPT ERROR: <type>: <message>`, `at: <Script>.<callback>`) and contained: the
  call returns its default and the game goes on. Before, an exception ended the
  page with a failure banner. Only an exception thrown by your code is contained.
- **Desktop and Android:** a `@RegisterClass` method returning a `RefCounted`
  hands Godot a reference of its own; before, GDScript dropping the result could
  destroy a resource the Kotlin object still held.
- **iOS:** setting a `Node` into a script property no longer corrupts it, and a
  no-arg getter returning a `String` or an array no longer runs the Godot method
  twice (`FileAccess.getLine()` skipped every other line).
- **`DirAccess` and `FileAccess` writes** stay unflushed until the file is closed
  (see [FileAccess and DirAccess](#fileaccess-and-diraccess)).

**Scripts and signals**

- **A Kotlin exception is a Godot script error** on desktop, Android and iOS
  (Web: see "Web script exceptions" above). It is reported as `SCRIPT ERROR: <type>:
  <message>` with `at: Player.ready (res://kotlin-src/.../Player.kt:42)`, as
  GDScript reports a runtime error, and it shows in the editor's Errors tab.
  The failed call returns `null` with `CALL_OK` (it was `INVALID_METHOD`, so Godot
  also added a misleading "method not found"): a caller that tested for that
  error now gets a normal `null` result. On iOS the exception is contained; it
  used to cross the native boundary and end the app.
- **Public functions are registered** and **subclasses inherit the base's
  members** (see [Script annotations](#script-annotations-and-export-hints)); a
  public helper that was Kotlin-only is now callable from GDScript, and the build
  fails when Godot cannot carry its types.
- **Lambda connections do not survive a hot reload** when their script's build
  is retired (see [Signals](#signals-callables-and-await)).
- **Web:** a `@Signal` or engine signal delivers every argument, not one, and an
  `await()` is cancelled when the emitter is freed first (it never resumed).

**Build and export**

- **iOS script builds fail** for a method the platform cannot dispatch, and **an
  export fails** when a `.kt` script does not bind (the export guard). A command-line
  export ends non-zero and writes no artifact; an export from the Export dialog is
  only marked Failed and its files stay written as Godot wrote them.
- **A failing headless export of a project without `.godot/` exits 1, not 134**
  (an unknown preset, an unwritable folder, an export plugin that stops the
  export), as a GDScript project does. A CI step that expected 134 needs 1. A
  project that does not enable the Kanama Tools plugin can still crash this way.
- **R8-minified Android builds are larger** (see [Android](#android)).
- **An export built with an older Web protocol refuses to run**: re-export.

## Godot 4.8

Godot 4.8: this guide will gain a section when Kanama moves to Godot 4.8.

## Checklist: every announced source break

The 0.5 changelog announces each source break with a `- **Source break:**` line
under `## Unreleased`. The table maps each of those lines, in changelog order, to
the section of this page that covers it. Entries before the marker gate existed carry
no marker lines; their breaks are covered in the sections named in the second
table.

| # | Changelog entry | Owner named by the marker | Guide section |
| --- | --- | --- | --- |
| 1 | Web ray queries and RIDs use desktop's API | Web `RayHit`, `PhysicsDirectSpaceState3D`, `PhysicsRayQueryParameters3D`, `World3D`, `SpringArm3D`, `top-level` | [Web](#web) |
| 2 | Web `set("position:x", ...)` does what it does on desktop: nothing | `GodotObject.set` (Web) | [Web](#web) |
| 3 | Web: a RefCounted lives while Kotlin holds it, and every RefCounted has `close()` | Web `Texture2D`, `AudioStream`, `PackedScene`, ... `close()` | [Changes that need no edit](#changes-that-need-no-edit) |
| 4 | iOS: a lambda connection's closure lives as long as any Callable of it | `top-level` `kanamaIosRuntimeReleaseCallable` | [Changes that need no edit](#changes-that-need-no-edit) |
| 5 | `GodotObject.set` returns `Unit` | `GodotObject.set` | [Node, SceneTree, Viewport and Resource](#node-scenetree-viewport-and-resource) |
| 6 | Web compiles the shared `GD`, `Mathf` and `GodotCallable` | `GD`, `Mathf` (Web) | [Web](#web) |
| 7 | Web compiles the shared `GD`, `Mathf` and `GodotCallable` | `GodotObject` and top-level `call` / `callDeferred` (Web) | [Web](#web) |
| 8 | `FileAccess` and `DirAccess` are RefCounted classes | `FileAccessHandle`, `DirAccessHandle` | [FileAccess and DirAccess](#fileaccess-and-diraccess) |
| 9 | `FileAccess` and `DirAccess` are RefCounted classes | the 38 `FileAccess` path helpers | [FileAccess and DirAccess](#fileaccess-and-diraccess) |
| 10 | `FileAccess` and `DirAccess` are RefCounted classes | the 23 `DirAccess` per-call helpers | [FileAccess and DirAccess](#fileaccess-and-diraccess) |
| 11 | `FileAccess` and `DirAccess` are RefCounted classes | generated `FileAccess` / `DirAccess` signatures | [FileAccess and DirAccess](#fileaccess-and-diraccess) |
| 12 | `FileAccess` and `DirAccess` are RefCounted classes | iOS `ShapeCast3D.getCollisionPoint`, `PhysicsRayQueryParameters3D.create` | [Engine, BoxMesh, ... generated once](#engine-boxmesh-audiostreamplayer-resourceloader-and-other-classes-generated-once) |
| 13 | `FileAccess` and `DirAccess` are RefCounted classes | `top-level` `kotlinScriptInstance<T>()` | [Changes that need no edit](#changes-that-need-no-edit) (a call on a node compiles unchanged) |
| 14 | 20 more per-platform classes generated once | `Engine` | [Engine, BoxMesh, ... generated once](#engine-boxmesh-audiostreamplayer-resourceloader-and-other-classes-generated-once) |
| 15 | 20 more per-platform classes generated once | `BoxMesh`, `AudioStreamPlayer` | [Engine, BoxMesh, ... generated once](#engine-boxmesh-audiostreamplayer-resourceloader-and-other-classes-generated-once) |
| 16 | 20 more per-platform classes generated once | `ResourceLoader` | [Engine, BoxMesh, ... generated once](#engine-boxmesh-audiostreamplayer-resourceloader-and-other-classes-generated-once) |
| 17 | 20 more per-platform classes generated once | `ShaderMaterial`, `ParticleProcessMaterial`, `ProceduralSkyMaterial` (iOS) | [Engine, BoxMesh, ... generated once](#engine-boxmesh-audiostreamplayer-resourceloader-and-other-classes-generated-once) |
| 18 | 20 more per-platform classes generated once | `NoiseTexture2D`, `ProjectSettings`, `SurfaceTool` (iOS) | [Engine, BoxMesh, ... generated once](#engine-boxmesh-audiostreamplayer-resourceloader-and-other-classes-generated-once) |
| 19 | iOS: no silent method skips; script errors name the Kotlin line | iOS builds | [iOS](#ios) |
| 20 | `GD` generated once, `Mathf` written once | `GD` | [`GD` and `Mathf`](#gd-and-mathf) |
| 21 | `GD` generated once, `Mathf` written once | `Mathf` (iOS) | [`GD` and `Mathf`](#gd-and-mathf) |
| 22 | `Tween` and `InputEventMouseButton` generated once | `InputEventMouseButton` | [`InputEventMouseButton`](#inputeventmousebutton) |
| 23 | `Tween` and `InputEventMouseButton` generated once | `top-level` `createTween`, `getProcessedTweens`, `setExclude` | [Tween and tweeners](#tween-and-tweeners) |
| 24 | `Tween` and `InputEventMouseButton` generated once | `Tween` | [Tween and tweeners](#tween-and-tweeners) |
| 25 | Web parity for signals and value types | `Basis`, `Transform3D`, `Vector2`, `Vector3` (Web) | [Web](#web) |
| 26 | Typed engine signals | `TypedSignal`, `Signal0` ... `Signal5`, `Area2D`, ... | [Changes that need no edit](#changes-that-need-no-edit) |
| 27 | StringName values decoded as nil | `top-level` `kanamaIosRuntimeDispatchCallable` | [Changes that need no edit](#changes-that-need-no-edit) |
| 28 | Typed export hints, generated autoloads, script inheritance | `Export` | [Script annotations and export hints](#script-annotations-and-export-hints) |
| 29 | Typed export hints, generated autoloads, script inheritance | `KanamaScript.self` (Web) | [Changes that need no edit](#changes-that-need-no-edit) |
| 30 | Value-type methods follow Godot exactly | `Vector3.maxAxisIndex()` | [Decimal values are `Double`](#decimal-values-are-double) |
| 31 | Value-type methods follow Godot exactly | `Vector2`, `Vector3`, `Transform3D` parameter names | [Decimal values are `Double`](#decimal-values-are-double) |
| 32 | Removed: `KanamaCoroutineOwner` | `KanamaCoroutineOwner`, `KanamaScope` | [Coroutines](#coroutines-kanamacoroutineowner) |
| 33 | Removed: `KanamaCoroutineOwner` | `KanamaScript` moved to common code | [Coroutines](#coroutines-kanamacoroutineowner) |
| 34 | Every decimal value is `Double` | `Vector2`, `Vector3`, `Vector4`, `Quaternion`, `Plane`, `Color` | [Decimal values are `Double`](#decimal-values-are-double) |
| 35 | One annotation set, public functions registered automatically, typed input handlers | script annotations and lifecycle input handlers | [Script annotations and export hints](#script-annotations-and-export-hints) |

Breaking entries that predate the marker gate (no `Source break` line, covered
here as well):

| Changelog entry | Guide section |
| --- | --- |
| Typed Godot enums and required returns (BREAKING) | [Enums and bitfields](#enums-and-bitfields) |
| Typed export hints, one annotation set (BREAKING) | [Script annotations and export hints](#script-annotations-and-export-hints) |
| `GodotHandle` replaces `MemorySegment` in every public signature | [The script constructor handle](#the-script-constructor-handle) |
| Desktop `GD.isInstanceValid(Any?)` takes `GodotObject?` | [`GD` and `Mathf`](#gd-and-mathf) |
| iOS `GodotObject` is no longer `AutoCloseable` | [iOS](#ios) |
| The roots written once (desktop and iOS source breaks) | [Node, SceneTree, Viewport and Resource](#node-scenetree-viewport-and-resource), [iOS](#ios) |
| `Node`, `SceneTree` generated once; `SceneTreeHandle` retired | [Node, SceneTree, Viewport and Resource](#node-scenetree-viewport-and-resource) |
| `Viewport`, `Resource`, `Button`, `LineEdit`, `Range`, `Slider`, `TabBar`, `AnimationPlayer` generated once | [Node, SceneTree, Viewport and Resource](#node-scenetree-viewport-and-resource) |
| The `Tweener` family is generated once | [Tween and tweeners](#tween-and-tweeners) |
| The API tree is common code (defaults became overloads) | [Changes that need no edit](#changes-that-need-no-edit) |
| Web wrappers generated from the call contract | [Web](#web) |
| One resource-ownership rule; a forgotten `close()` is a late release | [Ownership and `close()`](#ownership-and-close) |
| iOS exports require iOS 15.0; Godot 4.7.2 baseline | [iOS](#ios), [The order to do things in](#the-order-to-do-things-in) |
| Kanama is one Kotlin Multiplatform module; docs pages moved | [Tooling, Gradle and the editor](#tooling-gradle-and-the-editor) |

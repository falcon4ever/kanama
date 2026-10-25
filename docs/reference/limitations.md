# Limitations

This page lists what does not work, per platform, with the reason in one
line and what to do instead. The other pages describe the normal case and link
here.

## Every Platform

`@RegisterClass` in the rows below registers a Kotlin class as a new Godot
class, as a C++ GDExtension does, instead of attaching it to a node as a
script. Most games use `@ScriptClass` only, as in
[Writing Scripts](../game-dev/scripts.md).

| What does not work | Why | What to do instead |
|---|---|---|
| A public script function cannot take or return a `List`, `Map`, `ByteArray` or `Any?`. | Godot cannot hand those types to a Kotlin script function; the build stops with an error naming the parameter. | Pass the values as separate arguments, or make the function `internal` if only Kotlin calls it. |
| A `@RegisterClass` type's methods need every argument from Godot; Kotlin default arguments are not applied. | Godot registers those methods with no default arguments. | Pass every argument from GDScript. For a signal connection, attach the missing ones to the callable with GDScript's `Callable.bind`, which adds fixed extra arguments to every call. |
| A `@RegisterClass` type cannot have a public `fun getX()` beside `@Export var x`. | The export already defines `get_x` and `set_x` for Godot. | Use the property, or give the function another name. |
| `Engine.registerSingleton` refuses a `RefCounted` object. | Godot's singleton table does not hold a reference, so the object could be freed under it. | Register an object that extends `Object` or `Node`. |
| `get()`, `call()` and `getMeta()` return `null` when the value is a `Callable` or a `Signal`. | Kanama does not convert those two types when it reads a value by name. | Use the typed signal properties, or a `GodotCallable(target, "method")` you build yourself. |
| `GodotCallable` has no `bind` or `unbind`, so you cannot attach fixed extra arguments to it. | It carries a target and a method name only. | Capture the extra values in a Kotlin lambda connection instead. |
| A `@RegisterClass` type's `@Export` can only be a `Long`, `Double`, `Boolean`, `String`, `NodePath`, value type or `GodotObject`; an enum, `Int`, `List`, `Map` or other class fails the build. | `@RegisterClass` properties are registered through a smaller set of types than script properties. | Put those properties on a `@ScriptClass` script. |

## Desktop

| What does not work | Why | What to do instead |
|---|---|---|
| A change to a `@RegisterClass` type takes effect only after you restart the editor. | Godot reads extension classes once, at startup, so hot reload covers `@ScriptClass` scripts only. | Restart the editor after changing one, and keep code you change often in `@ScriptClass` scripts. |
| A macOS export downloaded from the internet does not open on a player's Mac. | Kanama signs the `.app` for your own machine only, and Gatekeeper wants a Developer ID signature and Apple's notarization. | Sign and notarize the game yourself, as [Signing for Distribution](../exporting/desktop.md#signing-for-distribution) shows. |
| A macOS export does not run on an Intel Mac, and there is no Windows on Arm export. | The bundled Java runtime is built for `macos-arm64`, `windows-x64`, `linux-x64` and `linux-arm64` only. | Export macOS games for `arm64`, and ship Windows games for x64. |
| A library you add to `build.gradle.kts` compiles, but the game fails with `ClassNotFoundException` or `NoClassDefFoundError` when it uses it. | The editor and a desktop export load Kanama, the Kotlin standard library, kotlinx.coroutines and `kanama-scripts.jar`, which holds only your own compiled scripts. | Copy a pure-Kotlin library's source into `kotlin-src/`, or do without the library. |
| A Godot build compiled with `precision=double` does not work with Kanama. | Parts of Kanama's value passing assume Godot's default single-precision sizes. | Use the official Godot 4.7.2 builds, which are single precision. |

## Android

| What does not work | Why | What to do instead |
|---|---|---|
| A script error in a minified release build names the callback, not the file and line. | R8, Android's release shrinker, removes the file and line information. | Reproduce the error in a debug build to get the line. |
| Exporting needs a checkout of the Kanama repository; a project made from the release kit or the store addon cannot export to Android on its own. | The Android build compiles your scripts from the checkout. The prebuilt Android addon in a release carries the runtime only. | Install Kanama from a [source checkout](../getting-started/source-checkout.md) and run the Android install commands from it, as [Export for Android](../exporting/android.md) shows. |
| Hot reload and the IntelliJ debugger do not work on a phone. | Kanama starts the reload watcher and the debugger port on desktop only. | Iterate and debug on desktop, then export. |
| A Kotlin standard library or coroutines function newer than Kotlin 2.1 or kotlinx.coroutines 1.6.4 fails the Android build. | An APK runs on the Kotlin libraries of Godot's Android template, so scripts compile against those versions for Android. | Use an older equivalent. The build error names the call. |
| Calling a function value with `.invoke()`, such as `callback?.invoke()`, does not compile for Android, and the Android build stops at it. | Android's Java runtime cannot run some Java calls named `invoke` directly, so the Android build rewrites them. It cannot tell your function value's `.invoke()` from those, and stops rather than rewrite it wrongly. | Call it directly: `callback()`, or `callback?.let { it() }`. See [Kotlin Style](../game-dev/style-guide.md#on-other-platforms). |

## iOS

| What does not work | Why | What to do instead |
|---|---|---|
| Exporting needs a checkout of the Kanama repository; a project made from the release kit or the store addon cannot export to iOS on its own. | The iOS build compiles your scripts to native code from the checkout. The prebuilt iOS addon in a release carries the runtime only. | Install Kanama from a [source checkout](../getting-started/source-checkout.md) and run `installIosAddon` from it, as [Export for iOS](../exporting/ios.md) shows. |
| Hot reload and the IntelliJ debugger do not work on an iPhone or iPad. | iOS runs your scripts as native code, with no Java runtime to reload or attach to. | Iterate and debug on desktop, then export. |
| A Godot call that cannot reach the engine returns an empty value and logs a `FAULT` line, instead of throwing. | On iOS, Kanama's native layer reports a failed call in the log and goes on, so one failure does not stop the game. | Treat every `FAULT` line as a bug; [FAULT Lines in the Log](../exporting/ios.md#fault-lines-in-the-log) says what to do. |
| An `Any?` parameter of an `@OverrideVirtual` function, an engine method you override, fails the call with a script error naming the type when Godot passes it an `Array`, `Dictionary` or packed array. | iOS does not convert those Godot values to Kotlin when they arrive as `Any?`. | Keep container values in exported `List` or `Map` properties, which iOS carries. |
| A script error shows line 0 in an `.ipa` export, a one-click deploy, or a release build. | Those builds remove the debug information Kanama reads to find the Kotlin line. | Use **Export Project Only** and run the Xcode project, which keeps them, while you debug. See [iOS](../exporting/ios.md). |
| A `Callable` or `Signal` argument of a signal or script function fails with an error naming the type. | iOS does not pass those types; desktop passes `null`. | Connect with a typed signal property, or pass the target object and method name instead. |
| An `Array` or `Dictionary` nested more than 8 levels deep arrives as `null` at the deepest level. | iOS converts nested containers to at most 8 levels. | Flatten deep data, or store it in a `Resource`. |
| An export, script function or `@OverrideVirtual` function with a type iOS cannot pass, such as an `RID` export or a `Map` parameter, fails the iOS build. | The iOS build checks every declaration ahead of time instead of dropping it on the device. | Change the type the error names. `-PkanamaIosAllowSkips=true` builds anyway and leaves those members out on iOS. |
| A signal with an `Array`, `Dictionary`, packed-array or `Rect2` argument reports a script error when it fires, and your lambda is not called. | The iOS signal path does not carry those argument types. | Avoid those signals in code that runs on iOS. Each one's documentation in your IDE says so. |
| `newScriptInstance<T>()` throws. | iOS cannot create a script resource from Kotlin. | Create the resource in the editor, or on desktop, save it as a `.tres` file and load it. |
| Setting a `Boolean`, `Long`, `Double`, `String`, `List` or `Map` property to a value of another type, from GDScript or `set()`, does not follow the conversion rule in [Exports and Resources](../game-dev/properties-resources.md#values-of-the-wrong-type). | iOS checks the type only for value types, `NodePath` and `Color`. | Set the property with a value of its own type. |

## Web

Web is experimental. [Export for the Web](../exporting/web.md) shows how to
export your project.

| What does not work | Why | What to do instead |
|---|---|---|
| Exporting needs a checkout of the Kanama repository; a project made from the release kit or the store addon cannot export to the Web on its own. | The Web build compiles your scripts from the checkout, and no release carries a Web addon. | Install Kanama from a [source checkout](../getting-started/source-checkout.md) and run `exportWeb` from it, as [Export for the Web](../exporting/web.md) shows. |
| Your Web export preset is not used: its export filters, HTML page options and custom HTML shell do not apply. | The Web export uses Kanama's own preset and page, which start your scripts before the game. | Set the game's name, icon, window size and main scene in Project Settings, which the export uses. Keep files you do not want to ship out of the project folder. |
| Threads and `SharedArrayBuffer` are not available. | The export uses Godot's single-thread Web template. | Keep the work on the main thread, and split long work over frames with `launch` and `nextFrame()`. |
| Forward+ and Mobile rendering are missing, and with them volumetric fog, SDFGI, VoxelGI, SSAO, SSIL and SSR. A shader can hitch once the first time it compiles. | The Web export uses the Compatibility renderer. | Switch your project to the Compatibility renderer while you build the Web version, so you see what the browser will show. |
| Multiplayer does not connect. | Browsers have no ENet, and Godot's WebSocket and WebRTC peers do not reach your Kotlin scripts on the Web. The multiplayer API answers as an offline single player. | Keep Web builds single-player; shared scripts that read the multiplayer API still run. |
| There is no Web editor and no hot reload. | Scripts are compiled ahead of time into the export. | Build and export again after every change. |
| Only `_enter_tree`, `_ready`, `_process`, `_physics_process`, `_draw`, `_exit_tree`, `_input` and `_unhandled_input` run. Any other virtual fails the Web build. | On the Web, Godot passes only those eight callbacks to your script. | Move the work into one of the eight. |
| A public function that Godot calls fails the Web build when its parameters are not one of these: none; up to six numbers (a `Vector2` counts as two, a `Vector3` as three); one `String` or object; or a mix of `String`, `NodePath`, numbers, `Boolean`, objects and value types. | On the Web, Godot can pass only those argument lists to your script. | Change the parameters, or make the function `internal` if Godot never calls it. The build error names the function. |
| `wait()` takes no `processAlways` or `ignoreTimeScale`. | On the Web, `wait` counts game frames with the defaults only. | Keep the defaults on Web. |
| A node's position and rotation are read once, at the start of the frame's `_process` or `_physics_process`. | Asking the engine on every read would be slow in the browser. | Drive movement from `velocity` and `moveAndSlide()` instead of re-reading a value you just wrote. |
| A script error names the script and callback, not the Kotlin file and line. | The Web export leaves out the debug information that maps the code back to Kotlin lines. | Reproduce on desktop to get the line. |
| When a script function throws, a GDScript caller expecting a `Long`, `Double`, `Boolean`, `String` or `Vector2` receives that type's default (`0`, `""`, ...) instead of `null`. | On the Web, the function's GDScript side has that return type, and it cannot return `null`. | Check for the error in the log, not for `null`. |
| A setter or other call that returns nothing, made on a freed object, is reported at the end of the frame, not thrown at the call. | Kanama sends such calls to the engine together at the end of the frame, so the engine reports the freed object only then. | Ask `GD.isInstanceValid(node)` before using an object that may be gone. |
| If your code keeps more than 4096 `RefCounted` arguments, such as every input event in a list, the oldest are released, and using one throws "Invalid access to previously freed instance". | The limit bounds memory on browsers that collect rarely, such as Firefox. | Copy the values you need (`lastKey = event.keycode`) instead of keeping the events. |
| The Kotlin object of a custom resource's script (`resource.asScript<T>()`) does not keep the resource alive. | On the Web, that Kotlin object holds no reference to its resource. | Keep your reference to the resource while you use its Kotlin object. |
| `SceneTree.createTween()`, `getProcessedTweens()`, `tweenAwait` and `Engine.registerSingleton` do not exist on Web. | Web has a subset of the Godot API, and these are not in it. | Use `node.createTween()` for tweens. |
| `getObjectId()` and `instanceId` fail on a freed object instead of answering its old id. | Web asks the engine for the id. | Read the id while the object is alive. |
| An exported value type whose default the build cannot work out, such as `Transform3D.IDENTITY.translated(Vector3.UP)`, fails the Web build. | The Web build must know each export's default when it builds, not when the game runs. | Use a named constant or a constructor of literals, and set the rest in `@OnReady`. |
| An exported `List` of an enum, Kotlin or Godot, fails the Web build. | The Web build cannot export a list of enums to the inspector. | Export a single enum, or a `List<Long>`. |
| An exported Godot enum shows its dropdown in the inspector, but code that reads `get_property_list()` sees a plain `int` property. | On the Web, an exported property cannot carry the name of its enum class. | Nothing for the inspector. In code, compare the value with the enum's constants. |
| The two `NavigationAgent3D` signals with a `Dictionary` argument have no typed property. | Web has no typed form for that argument. | Handle them in a small GDScript, or check the agent's state each physics frame. |
| An autoload read outside a script callback fails. | Web looks the autoload up through the running script. | Read `Autoloads.<Name>` in a lifecycle callback, a signal handler or a coroutine. |
| In some runs, enemies that leave the screen are never freed, because `VisibleOnScreenNotifier2D.screen_exited` does not fire. <!-- KANAMA-BLOCKED(since:2026-07-28, issue:370): the open screen_exited defect in this row --> | An open defect, [issue #370](https://github.com/falcon4ever/kanama/issues/370); its cause is not known. | If enemies pile up in a Web export, check the issue before you look for a bug in your project. |

How Kanama runs your scripts in the browser, and why these limits exist, is in
[Web Internals](../contributing/backends/web.md).

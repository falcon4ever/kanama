# Limitations

This page lists what does not work yet, per platform, with the reason in one
line and what to do instead. The other pages describe the normal case and link
here.

## Every Platform

| What does not work | Why | What to do instead |
|---|---|---|
| A public script function cannot take or return a `List`, `Map`, `ByteArray` or `Any?`. | Kanama does not pass those types to a script function yet; the build stops with an error naming the parameter. | Pass the values as separate arguments, or make the function `internal` if only Kotlin calls it. |
| A `@RegisterClass` type's methods need every argument from Godot. | Godot registers them with no default arguments. | Pass every argument from GDScript and from signal connections, or add an overload. |
| `Engine.registerSingleton` refuses a `RefCounted` object. | Godot's singleton table does not hold a reference, so the object could be freed under it. | Register an object that extends `Object` or `Node`. |
| A `Callable` or `Signal` value read through `get`, `call` or `getMeta` is `null`. | Kanama does not decode those two types on the dynamic path. | Use the typed signal properties, or a `GodotCallable(target, "method")` you build yourself. |
| `GodotCallable` has no `bind` or `unbind`. | It carries a target and a method name only. | Capture the extra values in a Kotlin lambda connection instead. |

## Android

| What does not work | Why | What to do instead |
|---|---|---|
| On Android 8 to 12, the garbage collector never releases a resource you forget to close. | Those versions lack the Java API Kanama uses to notice a dropped wrapper; this affects debug installs only, because release builds need Android 13. | `close()` or `use { }` what you load, as the [ownership rules](../game-dev/godot-api.md#resource-ownership) recommend. |
| A script error in a minified release build names the callback, not the file and line. | R8 removes the source information. | Reproduce the error in a debug build to get the line. |

## iOS

| What does not work | Why | What to do instead |
|---|---|---|
| A script error shows line 0 in an `.ipa` export, a one-click deploy, or a release build. | Those builds strip the symbols the line lookup needs. | Use **Export Project Only** and run the Xcode project, which keeps them, while you debug. See [iOS](../exporting/ios.md). |
| A `Callable` or `Signal` argument of a signal or script function fails with an error naming the type. | iOS does not pass those types; desktop passes `null`. | Connect with a typed signal property, or pass the target object and method name instead. |
| An `Array` or `Dictionary` nested more than 8 levels deep arrives as `null` at the deepest level. | The iOS container encoder stops at 8 levels. | Flatten deep data, or store it in a `Resource`. |
| A script file without a `package` line is untested on iOS. | The iOS build derives its generated names and imports from the package. | Give every script a package; the editor's **New Script** dialog does. |
| An export, script function or engine virtual iOS cannot carry fails the iOS build. | The iOS build checks every declaration ahead of time instead of dropping it on the device. | Change the type the error names. `-PkanamaIosAllowSkips=true` builds anyway and leaves those members out on iOS. |

## Web

Web is experimental. Its platform-wide limits (single thread, Compatibility
renderer, no multiplayer, no hot reload, source-checkout export only) are in
[Web: Known Limitations](../exporting/web.md#known-limitations). The rows below
are about scripts.

| What does not work | Why | What to do instead |
|---|---|---|
| Only `_enter_tree`, `_ready`, `_process`, `_physics_process`, `_draw`, `_exit_tree`, `_input` and `_unhandled_input` run. Any other virtual fails the Web build. | The Web bridge forwards those eight callbacks. | Move the work into one of the eight. |
| A public function whose parameter list the Web bridge does not carry fails the Web build. It carries no arguments, up to six numbers, one `String` or object, or a mix of `String`, `NodePath`, numbers, `Boolean`, objects and value types. | Each parameter list needs its own crossing into the browser. | Change the parameters, or make the function `internal` if Godot never calls it. |
| The `node`, `script` and `preload` properties, `castOrNull`, `cast`, `requireAs<T>`, `getNodeAs<T>` and the `instantiate` functions are missing. | The Web API has not caught up yet. `launch`, `wait`, `nextFrame`, `isScript`, `asScript` and `self.tree` work. | Use `getNodeOrNull` and the wrapper constructors, or keep a Web copy of the script in `web/kotlin-src/`. |
| `wait()` takes no `processAlways` or `ignoreTimeScale`. | Web uses its frame scheduler's delay. | Keep the defaults on Web. |
| A node's position and rotation are a snapshot from the start of the frame's `_process` or `_physics_process`. | Each live read would cross into the browser. | Drive movement from `velocity` and `moveAndSlide()` instead of re-reading a value you just wrote. |
| A script error names the script and callback, not the Kotlin file and line. | The Web export ships no names or source maps. | Reproduce on desktop to get the line. |
| When a script function throws, a GDScript caller expecting a `Long`, `Double`, `Boolean`, `String` or `Vector2` receives that type's default (`0`, `""`, ...) instead of `null`. | The Web script declares those returns with their type. | Check for the error in the log, not for `null`. |
| A setter or other call that returns nothing, made on a freed object, is reported at the end of the frame, not thrown at the call. | Such calls are batched. | Ask `GD.isInstanceValid(node)` before using an object that may be gone. |
| At most 4096 `RefCounted` arguments that only your code holds stay alive; past that the oldest are released. | It bounds memory on browsers that collect rarely, such as Firefox. | Copy the values you need (`lastKey = event.keycode`) instead of keeping thousands of events. |
| A custom resource's script object does not keep the resource alive. | The link between the two is not built on Web yet. | Keep your reference to the resource while you use its script object. |
| `SceneTree.createTween()`, `getProcessedTweens()`, `tweenAwait` and `Engine.registerSingleton` are missing. | Not built on Web yet. | Use `node.createTween()`. |
| `getObjectId()` and `instanceId` fail on a freed object instead of answering its old id. | Web asks the engine for the id. | Read the id while the object is alive. |
| An autoload read outside a script callback fails. | Web looks the autoload up through the running script. | Read `Autoloads.<Name>` in a lifecycle callback, a signal handler or a coroutine. |
| A Godot-side call that leaves out Kotlin default arguments is untested. | It is covered on desktop, Android and iOS only. | Pass every argument from GDScript and signal connections. |

How the Web bridge works, and why these limits exist, is in
[Web Internals](../contributing/backends/web.md).

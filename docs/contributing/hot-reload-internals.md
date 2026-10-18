# Hot Reload Internals

This document describes the current `@ScriptClass` reload path.

## Scope

Kanama supports reload for attachable script classes:

- `@ScriptClass`: reloadable. These classes register script templates in
  `KanamaScript` and are loaded from `kanama-scripts.jar`.
- `@RegisterClass`: not reloadable. These classes are registered permanently
  in Godot's ClassDB and require an editor restart after changes.

## Runtime Flow

1. `./gradlew syncExampleAddonJar` builds the checked-in example project, or
   `./gradlew installAddonJar -PkanamaProjectDir=/path/to/project -PkanamaProjectScriptsDir=/path/to/project`
   builds an external project:
   - `kanama.jar`: the Kanama runtime and any runtime-bundled registrars.
   - `kanama-scripts.jar`: project `.kt` scripts plus generated project class
     and script registrars.
2. Godot loads `kanama.jar` through `bootstrap.c`.
3. `KanamaBinding.initializeCallback` registers the runtime:
   - `KanamaScript`
   - `KanamaScriptLanguage`
   - `.kt` resource loader/saver
   - optional permanent `KanamaRegistry` entries from the runtime jar
4. `KanamaHotReload.initialize()` loads `kanama-scripts.jar` through a
   child-first classloader.
5. On first load, the generated `KanamaClassRegistry` registers project
   `@RegisterClass` classes with ClassDB. These classes are permanent for the
   Godot process.
6. The generated `KanamaScriptRegistry` registers only reloadable
   `@ScriptClass` templates.
7. `KanamaScriptLanguage.callFrame()` periodically calls
   `KanamaHotReload.frameTick()`, which checks the script jar mtime and reloads
   changed templates.

## Connections Across A Reload

A lambda signal connection is owned by the build (the script jar's class loader
generation) that made it. Godot keys a connection's custom Callable to its
receiver and emitter only, so without a build key a lambda whose receiver
outlives its maker (the `SceneTree`, a GDScript autoload, a kept node) keeps
running the old build's code and pins the retired loader. The pieces:

1. **Build tag.** `GodotSignal.connectArgs` takes the user's callback as
   `origin` (the typed `Signal0`…`Signal5` connections and the `KanamaScript`
   member forms pass the callback; `await` passes null: its connection is closed
   when the script scope is cancelled at the free). `SignalCallbackRegistry`
   records `Entry.build`, the defining class loader of `origin` when it is a
   `ChildFirstClassLoader`, else null (runtime code). A script lambda or
   `::method` reference compiled with `invokedynamic` is a hidden class of its
   host, so it reports the script jar's loader. iOS has no hot reload: its
   `connectArgs` ignores `origin`.
2. **Live count.** `ScriptBridge` counts live script objects per build at one
   tracking site, `noteScriptObject`: from `trackKotlinObject` (a new instance)
   and `ScriptOwnerLinks.materialize` (an owner link's rebuilt instance, which
   moves the owner to the current build). `siFree` uncounts. When a retired
   (closed) build's count reaches 0, `KanamaHotReload.requestSweep()` sets a
   flag; the free itself may run inside an emission, so nothing is disconnected
   there.
3. **Sweep.** `KanamaHotReload.sweepRetiredConnections()`, on the main thread,
   runs right after `AutoloadReload.recreate` in `reloadScripts`, at the next
   `frameTick` after the flag is set, and before each retired-loader check (a
   backstop). For each retired build with no live script object it takes
   `SignalCallbackRegistry.connectionsOf(build)`, unregisters each entry (its
   release hook does not run) and then disconnects it through
   `SignalCallables.disconnect` while the emitter exists, and prints one
   `push_warning` per sweep:
   `hot-reload: dropped N lambda connection(s) of build B whose script objects are gone (Emitter.signal xN, ...); the new build's _ready connects again`.
   The emitter is named by its node name (an autoload reads as its global) or
   its class. A build that still has live objects keeps its connections.
4. **Logs.** `retired-loader-check collected=C alive=A alive_ids=… swept=N`
   reports the connections the sweeps dropped since the previous check; with
   nothing left to pin it, the retired loader is collected (`alive=0`). The
   line is printed when something changed (a collection, a sweep, a different
   set of live loaders), not every second. The check forces garbage
   collections only while some retired build has no live script object left:
   a build whose objects are still alive (a running game whose scene was not
   reloaded) cannot be collected, and forcing collections for it stalled the
   main thread once a second.

`AutoloadReload` still disconnects the lambdas bound to a reset autoload node
at once (receiver-keyed); the build sweep covers every other target. An
emission in the same frame as the build's last free still reaches the old
lambda: the sweep runs on the next frame tick.

## Editor Workflow

The optional `res://addons/kanama_tools` plugin provides `Build Scripts`,
debounced build-on-save, scene reload after sync, and JDWP settings. See
[The Editor Loop](../getting-started/editor-workflow.md) for the daily
workflow.

After a sync, existing `KanamaScript` resources are rebound to the newest
template. Scene reload is still recommended for reliable live-node replacement.

For breakpoint debugging, enable `kanama/debug/jdwp_enabled`. Kanama tools
registers `kanama/debug/jdwp_port` with the conventional runtime/game default
`5005`. Restart the game process after changing either setting. Kanama reads
these settings before starting the embedded JVM and enables JDWP at
`localhost:PORT` for runtime/game processes (skipped, with a log line, when the
port is already taken on macOS and Linux); attach IntelliJ with a **Remote JVM Debug**
configuration pointed at `localhost:PORT`. Editor processes skip the runtime
port so pressing Play does not create a port conflict.

## Verification

Use:

```sh
./scripts/hot_reload_smoke.sh /absolute/path/to/godot_binary
./scripts/runtime_smoke.sh /absolute/path/to/godot_binary
```

`runtime_smoke.sh` is the main headless runtime smoke. It verifies that Godot
loads Kanama, registers the script language and resource loader, and runs the
example project far enough to observe expected markers.

`hot_reload_smoke.sh` edits `example_project/HelloScript.kt`, rebuilds the
script jar, and verifies the changed marker appears in a new headless editor
run.

`hot_reload_in_process_smoke.sh` keeps one headless Godot process alive, edits
and rebuilds `kanama-scripts.jar`, waits for the runtime hot-reload loop to
rebind scripts, reloads the current scene, and verifies the updated script
marker without restarting Godot. It also checks retired script classloaders via
weak references and asserts that the old loader is collected after the old
script instance is freed. A lambda `HelloScript` connects to the GDScript
autoload (a target that survives the reload) must run once per build, never
for build A after the reload, with the `hot-reload: dropped 1 lambda
connection(s)` warning.

## Current Behavior

- `@RegisterClass` hot reload is not supported because Godot ClassDB extension
  registrations are permanent for the process. Project `@RegisterClass`
  changes require an editor/game restart.
- Live in-place replacement of already-instanced nodes is partial; scene
  reload remains the reliable editor workflow.
- The child-first package list is fixed in `KanamaHotReload`.
- A lambda connection dies with the build that made it: once the old build's
  last script object is gone, its lambda connections are disconnected with one
  warning, whatever their target. Make connections in `_ready`; by-name
  connections are Godot's and survive, as in GDScript.

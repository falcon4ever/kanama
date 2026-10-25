# Open in IntelliJ IDEA

Godot runs your game; IntelliJ IDEA is where you write the Kotlin. This page
gets you from a fresh starter to completion on the whole Godot API and a
debugger stopped on a line of your script while the game runs.

You need a project with its own Gradle build: a starter made with
`createStarterProject` ([source checkout](source-checkout.md)), a
[release kit](release-kit.md), or an existing project set up with the
[store addon](store-addon.md). They all have a `build.gradle.kts` beside
`project.godot`.

## Open the project

1. In IntelliJ IDEA, choose **File > Open** and pick the project folder (the
   one with `project.godot` and `build.gradle.kts`). Click **Trust Project**
   when asked.
2. IntelliJ sees the Gradle build and imports it. The first import also
   indexes the Kanama API, which takes a little while.
3. Open `kotlin-src/HelloScript.kt`. Inside `process`, type `self.` and you get
   the `Node2D` members. Cmd-click (Ctrl-click on Linux and Windows) `Node2D`
   to open its source: every Godot class comes with its Godot documentation, so
   **View > Quick Documentation** works on any engine call.

The API IntelliJ indexes is the one your game runs on: the build reads Kanama
from the project's own `addons/kanama/maven` folder, sources included. The code
Kanama generates for your scripts (the registrars in
`build/generated/ksp/main/kotlin`) is a source folder too, so it resolves after
the first build.

IntelliJ runs Gradle on its bundled runtime unless you choose another one, and
that works: the build compiles with JDK 25 whichever runtime starts Gradle. If
the import fails with a message about a Java toolchain, see
[Common problems](#common-problems).

## Build your scripts

Edit a script and save. Then either:

- press **Build Scripts** in Godot, or
- run the `buildScripts` task from IntelliJ's Gradle tool window
  (**kanama > buildScripts**), or `./gradlew buildScripts` in a terminal.

Either way, `addons/kanama/kanama-scripts.jar` is rebuilt and Godot picks it
up. While the Godot editor is open, you can skip that step: build on save is
on, so every save in IntelliJ starts a build by itself, and the status light
in Godot's toolbar turns green when it is done. The build takes a few seconds.
[Edit, Build, Run](editor-workflow.md#reloading-after-a-build) covers what
happens after it.

## Debug the running game

Here is the whole setup, once per project:

1. In Godot, open **Project > Project Settings**, switch on **Advanced
   Settings**, and under **Kanama > Debug** turn on **Jdwp Enabled**. Leave
   **Jdwp Port** at `5005`. In `project.godot` that is:

    ```ini
    [kanama]
    debug/jdwp_enabled=true
    debug/jdwp_port=5005
    ```

2. In IntelliJ, choose **Run > Edit Configurations**, add a **Remote JVM
   Debug** configuration, and set **Host** to `localhost` and **Port** to
   `5005`. Keep **Debugger mode** at **Attach to remote JVM**.

Then, each time:

1. Press **Play** in Godot. The output panel shows
   `[kanama] JDWP debug agent enabled at localhost:5005`.
2. In IntelliJ, start the Remote JVM Debug configuration with **Debug**.
3. Click in the gutter next to a line in `process` to set a breakpoint. The game
   stops there on the next frame; step, inspect `delta` and your fields, then
   resume.

The setting is read when the game starts, so restart the game after changing
it. Only the game opens the port: the editor skips it, so pressing Play does not
fight the editor for it.

To run the game from a terminal with the debugger, set the port for that
launch instead:

```sh
KANAMA_JDWP_PORT=5005 godot --path /path/to/project
```

### Stop before your first line runs

The game does not wait for the debugger, so `_ready` of the first scene has
usually run by the time you attach. To make the game wait, turn
**Jdwp Enabled** off and start it with the JVM's own debug option:

```sh
KANAMA_JVM_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=localhost:5005" \
  godot --path /path/to/project
```

The game waits at startup until you attach from IntelliJ; then it runs, and a
breakpoint in `ready` stops.

### Debug tool scripts in the editor

`@Tool` scripts run inside the editor. Start the editor from a terminal with
its own port and attach to that one:

```sh
KANAMA_JDWP_PORT=5006 godot --path /path/to/project --editor
```

A game you start with Play from that editor inherits `KANAMA_JDWP_PORT=5006`,
finds the editor holding the port, and runs without the debugger; start the
game from another terminal with its own port to debug both.

### Debug from another machine

The debug port listens on `localhost` only, because anyone who can reach a
debug port can run code in your game. To debug a game running on another
machine, give an explicit address, on a network you trust:

```sh
KANAMA_JDWP_PORT='*:5005' godot --path /path/to/project
```

## Common problems

| Symptom | Fix |
| --- | --- |
| `net.multigesture.kanama` imports are red; nothing completes. | The Gradle import did not finish or found no Kanama API. Check that `addons/kanama/maven` exists (in a source-checkout starter, `installAddonJar` writes it), then open the Gradle tool window and click **Reload All Gradle Projects**. |
| The project has no `build.gradle.kts`. | It was prepared with `installStarterTemplate`, which adds no Gradle build. Set it up with the [store addon](store-addon.md) to get one, or start from `createStarterProject`. |
| Completion shows an old API after you updated Kanama. | Rerun `installAddonJar` from the checkout ([Building and Installing Kanama](source-checkout.md#building-and-installing-kanama)), then **Reload All Gradle Projects**. |
| The import fails with "Cannot find a Java installation matching languageVersion=25". | No JDK 25 is installed and Gradle could not download one (it does that once, with network access). Install a JDK 25, or set **Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JVM** to one. |
| "Connection refused" when you attach. | The game is not listening. Look for `JDWP debug agent enabled at` in the Godot output: without it, debugging is off or the game was started before you turned it on. Check that the port in IntelliJ matches. |
| The output says `JDWP port 5005 is already in use (another game instance?); starting without the debugger`. | Another game instance has the port, for example the first of several started with **Debug > Customize Run Instances**: only one instance gets the debugger. Attach to that one. To debug another instance too, start it from a terminal with its own `KANAMA_JDWP_PORT` (run instances take launch arguments, not environment variables). |
| A breakpoint never hits. | The line already ran before you attached (see [Stop before your first line runs](#stop-before-your-first-line-runs)), or the node is still running the code from before your last build: after a hot reload, existing nodes keep their old code until the scene reloads. Restart the scene or the game. |

## How these steps were checked

Checked on macOS (Apple M1 Max) on 2026-10-18 with Godot 4.7.2 and a starter
made with `createStarterProject` and `installAddonJar`. IntelliJ IDEA 2026.1.2
cannot be driven by a script, so the steps were checked by what IntelliJ asks
Gradle for: `./gradlew buildScripts` succeeds, also with IntelliJ's bundled
runtime as the Gradle JVM, and every Kanama dependency resolves a sources jar.
The debugger steps were checked with the JDK's `jdb` attached to the headless
game: it stopped in `HelloScript.process` with the project setting and with
`KANAMA_JDWP_PORT`, and in `HelloScript.ready` with `suspend=y`; the editor
skipped the port, and a second game instance started without it.

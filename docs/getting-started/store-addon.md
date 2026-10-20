# Add Kanama to an Existing Project

The store addon adds Kanama to a Godot project you already have. The zip only
writes under `addons/`. A setup script then adds a Gradle build and a starter
Kotlin script, and keeps any file that is already there.

## Requirements

You need Godot 4.7.2 and a JDK 25 or newer, as listed in
[Get Started](index.md#requirements). The addon carries the Kanama runtime for
every desktop platform and the Gradle wrapper. The first build downloads
Gradle and the Kotlin compiler, so it needs a network connection.

!!! note "Until Kanama 0.5.0 is released"
    The addons published so far are for Kanama 0.4, whose scripts look
    different from the ones in these docs. Until the 0.5.0 addon is out, use
    [a source checkout](source-checkout.md#adding-kanama-to-an-existing-project).

## Installation

1. Download `kanama-store-addon-v<version>.zip` from the
   [releases page](https://github.com/falcon4ever/kanama/releases) and unzip
   it at the root of your Godot project, next to `project.godot`. It adds:
    - `addons/kanama`: the runtime Godot loads, with the Kanama libraries your
      build compiles against in `addons/kanama/maven`;
    - `addons/kanama_tools`: the Kanama Tools editor plugin;
    - `addons/kanama/templates/release-kit`: the Gradle files the setup script
      copies.
2. On macOS, clear the download quarantine once, or Godot refuses to load the
   addon:

    ```sh
    xattr -dr com.apple.quarantine /path/to/project
    ```

3. From the project root, run the setup script:

    ```sh
    cd /path/to/project
    sh addons/kanama/setup-kanama-project.sh
    ```

    On Windows, run `.\addons\kanama\setup-kanama-project.ps1`. The script
    copies `build.gradle.kts`, `settings.gradle.kts`, `gradlew` and the
    `gradle/` folder, adds `kotlin-src/HelloScript.kt`, and registers Kanama in
    `.godot/extension_list.cfg`. It skips every file that already exists and
    prints `keeping existing` for it.

4. Build the Kotlin scripts:

    ```sh
    ./gradlew buildScripts
    ```

    On Windows, run `.\gradlew.bat buildScripts`. The build compiles every
    `.kt` file in `kotlin-src/` into `addons/kanama/kanama-scripts.jar`.

5. Open the project in Godot, or reopen it if it was open. In
   **Project > Project Settings > Plugins**, enable **Kanama Tools**. It adds
   **Build Scripts** and **Open Kotlin** to the editor toolbar.
6. Select a `Node2D` in a scene. In the inspector, load
   `res://kotlin-src/HelloScript.kt` into its **Script** property, the same way
   you attach a `.gd` file. Run the scene: the node rotates, and the Output
   panel shows `[kanama:starter] Hello from Kanama`.

From here the loop is the same as in any Kanama project: edit in IntelliJ IDEA,
press **Build Scripts**, press **Play**. [Open in IntelliJ IDEA](intellij.md)
and [Edit, Build, Run](editor-workflow.md) cover it.

## Projects That Already Have a Gradle Build

If your project already has a `build.gradle.kts`, the setup script keeps it.
Add the Kanama parts to it yourself. The kit's own build is short:

```kotlin
plugins {
    kotlin("jvm") version "2.4.21"
    id("com.google.devtools.ksp") version "2.3.12"
}

kotlin {
    jvmToolchain(25)
    sourceSets.named("main") {
        kotlin.srcDir("kotlin-src")
        kotlin.srcDir(layout.buildDirectory.dir("generated/ksp/main/kotlin"))
    }
}

apply(from = "addons/kanama/kanama-project.gradle.kts")
```

The last line does the rest: `addons/kanama/kanama-project.gradle.kts` adds
the `addons/kanama/maven` repository, the Kanama dependencies and the
`buildScripts` task. Copy `settings.gradle.kts` from
`addons/kanama/templates/release-kit` too if your build has none; it lets
Gradle download a JDK 25 when only another JDK is installed.

To replace the root Gradle files with the template instead, run the script
with `--force` (`-Force` on Windows). It overwrites `build.gradle.kts`, `settings.gradle.kts`, the
Gradle wrapper and `kotlin-src/HelloScript.kt`, so only use it when nothing in
those files is yours.

## Troubleshooting

| Problem | Fix |
| --- | --- |
| Godot cannot find `libjvm`. | Install a JDK 25 or newer. Kanama looks in the usual install folders; if yours is elsewhere, set `JAVA_HOME`, or the editor setting **Kanama > Build > JDK Path**, to the JDK folder and restart Godot. See [Which JDK Kanama Uses](editor-workflow.md#which-jdk-kanama-uses). |
| macOS reports `"libkanama_bootstrap.dylib" Not Opened`. | Gatekeeper quarantined the zip. Run `xattr -dr com.apple.quarantine /path/to/project` and open the project again. |
| Gradle cannot resolve the Kanama libraries. | Check that `addons/kanama/maven` exists and that `build.gradle.kts` ends with `apply(from = "addons/kanama/kanama-project.gradle.kts")`. |
| Godot does not recognize `.kt` scripts. | Check that `.godot/extension_list.cfg` contains `res://addons/kanama/kanama.gdextension`, then reopen the project. |
| The game still runs your old Kotlin code. | Press **Build Scripts** or run `./gradlew buildScripts`, then run the scene again. |

# Troubleshooting

Start from what you see. Each row names the likely cause and the fix, and
links the page that explains more. If something cannot work on a platform at
all, it is on [Limitations](../reference/limitations.md) instead.

## Opening a Project

| What you see | Cause | Fix |
| --- | --- | --- |
| Godot cannot find `libjvm`, or the Kanama Tools plugin warns about Java. | Kanama found no JDK 25 or newer. | Install a JDK 25 or newer. If it is not in a usual install folder, set **Editor Settings > Kanama > Build > JDK Path** (`kanama/build/jdk_path`) or `JAVA_HOME`, then restart Godot. See [JDK Selection](editor-workflow.md#jdk-selection). |
| **Build Scripts** says it found no JDK 25 or newer, but `./gradlew buildScripts` works in a terminal. | Godot started from a launcher, a desktop shortcut or a file manager does not see the `JAVA_HOME` from your shell profile. | Set JDK Path. See [JDK Selection](editor-workflow.md#jdk-selection). |
| Godot installed as a Flatpak or Snap finds no JDK. | The sandbox cannot see your JDK folder. | Give the sandbox access to the JDK, or use the unsandboxed Godot build. See [Edit, Build, Run](editor-workflow.md#on-other-platforms). |
| The plugin says **"Restart the editor to use JDK ..."**. | Kanama reads the JDK setting once, when Godot loads it. | Restart the editor. |
| macOS says `"libkanama_bootstrap.dylib" Not Opened`. | Gatekeeper, the macOS check on downloaded files, quarantined the zip. | Clear the quarantine as [Install from a Release Kit](release-kit.md#on-other-platforms) shows, then open the project again. |
| Godot does not recognize `.kt` files, or reports a missing `res://kotlin-src/HelloScript.kt`. | The Kanama addon is not registered with the project. | Check that `.godot/extension_list.cfg` contains `res://addons/kanama/kanama.gdextension`, then reopen the project. See [Add Kanama to an Existing Project](store-addon.md#troubleshooting). |

## Building Scripts

| What you see | Cause | Fix |
| --- | --- | --- |
| The game still runs your old Kotlin code. | The build did not run, or the nodes already in the scene keep their old code until the scene reloads. | Press **Build Scripts** or run `./gradlew buildScripts`, then play the scene again. See [Reloading after a Build](editor-workflow.md#reloading-after-a-build). |
| Gradle cannot resolve `net.multigesture.kanama` or the Kanama libraries. | The build reads them from `addons/kanama/maven`, and that folder is missing. | Unzip the kit again, reinstall the store addon, or run `installAddonJar` from your checkout. See [Building Scripts](editor-workflow.md#building-scripts). |
| Gradle says "Cannot find a Java installation matching languageVersion=25". | No JDK 25 is installed, and Gradle could not download one. | Install a JDK 25. In IntelliJ, see [Open in IntelliJ IDEA](intellij.md#common-problems). |
| CMake stops and names a JDK, or no CMake or C compiler is found. | A source checkout builds Kanama's native library, which needs the JDK 25 headers and a C toolchain. | See [Install from a Source Checkout](source-checkout.md#troubleshooting). |
| The build stops with an error that names a parameter of one of your public functions. | That type cannot be passed between Godot and a script function. | Change the type, or make the function `internal`. See [Limitations](../reference/limitations.md#every-platform). |
| `PlayerMethods`, `PlayerSignals` or `Autoloads` are red in the IDE. | The build generates them from your scripts. | Build, or sync Gradle. See [Common Pitfalls](../game-dev/scripts.md#common-pitfalls). |
| `net.multigesture.kanama` imports are red in IntelliJ, and nothing completes. | The Gradle import did not finish. | See [Open in IntelliJ IDEA](intellij.md#common-problems). |

## Running Scripts

| What you see | Cause | Fix |
| --- | --- | --- |
| A node acts as if it had no script, and the output says `[kanama:kt] WARNING: no Kotlin class bound for res://...`. | `kanama-scripts.jar` does not contain that script yet. | Press **Build Scripts**. See [Building Scripts](editor-workflow.md#building-scripts). |
| `SCRIPT ERROR:` with a Java exception and a Kotlin file and line. | An exception escaped your code. The game goes on with the next frame. | Open the file and line from the Debugger's Errors tab. See [Printing and Errors](../game-dev/scripts.md#printing-and-errors). |
| `Invalid access to previously freed instance`. | Your code used a node or object after Godot freed it. | Ask `GD.isInstanceValid(node)` first. See [Printing and Errors](../game-dev/scripts.md#printing-and-errors). |
| `println` output is missing from the Output panel. | `println` writes to the process's own output, which only the terminal that started Godot shows. | Use `GD.print`. See [Printing and Errors](../game-dev/scripts.md#printing-and-errors). |
| A change to a `@RegisterClass` type has no effect. | Godot reads those classes once, at startup. | Restart the editor. See [Reloading after a Build](editor-workflow.md#reloading-after-a-build). |
| The IntelliJ debugger cannot attach, or a breakpoint never hits. | The game is not listening for the debugger, or the line ran before you attached. | See [Open in IntelliJ IDEA](intellij.md#common-problems). |

## Exporting

| What you see | Cause | Fix |
| --- | --- | --- |
| The export stops with `Kanama: Export stopped:`. | A scene uses a Kotlin script that does not load in the editor, usually because the scripts are not built. | Build your scripts as the shipping page for your platform shows, then export again. See [Export Guard](editor-workflow.md#export-guard). |
| An exported desktop game cannot find Java, or uses the JDK installed on your machine. | The export has no `runtime/` folder: the assembly step did not run on it. | Run `export_game_assemble.sh` on the export. See [Export for Desktop](../exporting/desktop.md#troubleshooting). |
| macOS kills the exported game at launch. | A file changed after the game was signed. | Run the assembly step again, and sign for distribution only after it. See [Export for Desktop](../exporting/desktop.md#troubleshooting). |
| An exported desktop game runs old Kotlin code. | The assembly step copies the scripts as they were when it ran. | Build your scripts, then run the assembly step again. |
| A macOS export does not open on a player's Mac. | Apple wants a Developer ID signature and notarization for a downloaded app. | See [Signing for Distribution](../exporting/desktop.md#signing-for-distribution). |

## Android

| What you see | Cause | Fix |
| --- | --- | --- |
| `installAndroidPluginAar` stops at a line such as `callback?.invoke()`. | The Android build cannot compile `.invoke()` on a Kotlin function value. | Write `callback()` or `callback?.let { it() }`. See [Limitations](../reference/limitations.md#android). |
| The export logs `No loader found for resource: res://….kt`, or scenes lose their property values. | The desktop addon in the project is missing or out of date. | Run the two install commands, delete `.godot/exported`, and export again. See [Export for Android](../exporting/android.md#troubleshooting). |
| The app crashes at startup before any script runs. | An outdated install, an old graphics driver, or a locked screen. | See [Export for Android](../exporting/android.md#troubleshooting), which tells the three apart. |
| Gradle cannot resolve `com.github.falcon4ever.PanamaPort`. | The export downloads that library from JitPack, a package host, and needs network access. | Connect, then run the two install commands again. See [Export for Android](../exporting/android.md#troubleshooting). |

## iOS

| What you see | Cause | Fix |
| --- | --- | --- |
| Godot cannot export for iOS, or Xcode cannot sign the app. | Missing export templates, signing team or provisioning profile. | See [Export for iOS](../exporting/ios.md#troubleshooting). |
| Scenes load without their script property values, and the export log shows `no Kotlin class bound`. | The iOS build did not include your scripts. | Run `installIosAddon` with `-PkanamaIosProjectScriptsDir`, delete `.godot/exported`, and export again. See [Export for iOS](../exporting/ios.md#troubleshooting). |
| The log shows a line with `FAULT`. | A call from your script did not reach Godot. | See [FAULT Lines in the Log](../exporting/ios.md#fault-lines-in-the-log). |

## Web

| What you see | Cause | Fix |
| --- | --- | --- |
| The page is blank when you open `index.html` from disk. | A browser does not load the game's files into a page opened from disk. | Serve the folder over HTTP. See [Running the Export](../exporting/web.md#running-the-export). |
| The page loads on a phone or another computer, but the game never starts. | Browsers treat a plain-HTTP network address as insecure, and Godot's Web build does not start there. | See [Testing on a Phone or Tablet](../exporting/web.md#testing-on-a-phone-or-tablet). |
| The Web build stops on one of your scripts. | The script uses something the Web build does not support. | See [Web-Compatible Scripts](../exporting/web.md#web-compatible-scripts). |
| `exportWeb` or `packageWebExport` stops with a message. | The message names the cause. | Find the message in [Export for the Web](../exporting/web.md#troubleshooting). |

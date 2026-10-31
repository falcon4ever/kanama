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
| The plugin says **"Restart the editor to use JDK ..."**. | Kanama reads the JDK setting once, when Godot loads it. | Restart the editor. See [JDK Selection](editor-workflow.md#jdk-selection). |
| macOS says `"libkanama_bootstrap.dylib" Not Opened`. | Gatekeeper, the macOS check on downloaded files, quarantined the zip. | Run `xattr -dr com.apple.quarantine /path/to/your-project`, then open the project again. See [Install from a Release Kit](release-kit.md#on-other-platforms). |
| Godot does not recognize `.kt` files, **Attach Script** offers no Kotlin language, or Godot reports a missing `res://kotlin-src/HelloScript.kt`. | The Kanama addon is not registered with the project. | Check that `.godot/extension_list.cfg` contains `res://addons/kanama/kanama.gdextension`, then reopen the project. See [Add Kanama to an Existing Project](store-addon.md#troubleshooting). |
| On Windows, `./gradlew` is not recognized. | Windows shells run the Gradle wrapper's batch file. | Run `.\gradlew.bat` instead. See [Edit, Build, Run](editor-workflow.md#on-other-platforms). |

## Building Scripts

| What you see | Cause | Fix |
| --- | --- | --- |
| The game still runs your old Kotlin code. | The build did not run, or the nodes already in the scene keep their old code until the scene reloads. | Press **Build Scripts** or run `./gradlew buildScripts`, then play the scene again. See [Reloading after a Build](editor-workflow.md#reloading-after-a-build). |
| Gradle cannot resolve `net.multigesture.kanama` or the Kanama libraries. | The build reads them from `addons/kanama/maven`, and that folder is missing. | Unzip the kit again, reinstall the store addon, or run `installAddonJar` from your checkout. See [Building Scripts](editor-workflow.md#building-scripts). |
| Gradle says "Cannot find a Java installation matching languageVersion=25". | No JDK 25 is installed, and Gradle could not download one. | Install a JDK 25. In IntelliJ, also set the Gradle JVM to it; see [Open in IntelliJ IDEA](intellij.md#common-problems). |
| CMake stops and names a JDK whose `jni.h` is too old, or cannot find `include/jni.h`. | A source checkout builds Kanama's native library, which needs the headers of a full JDK 25. | Install a full JDK 25, not a JRE, point `JAVA_HOME` at it, and run the command again. See [Install from a Source Checkout](source-checkout.md#troubleshooting). |
| CMake or a C compiler is not found. | A source checkout builds Kanama's native library with them. | Install CMake 3.22 or newer and your platform's C compiler, then open a new terminal. See [Install from a Source Checkout](source-checkout.md#requirements). |
| The build stops at a parameter or return type of one of your public functions. | A limit on what Godot can pass to a script function; see [Limitations](../reference/limitations.md#every-platform). | Change the type the error names, or make the function `internal`. |
| `PlayerMethods`, `PlayerSignals` or `Autoloads` are red in the IDE. | The build generates them from your scripts. | Build, or sync Gradle. See [Common Pitfalls](../game-dev/scripts.md#common-pitfalls). |
| Gradle stops with `Kanama addon not found` or `Kanama addon is incomplete`. | Part of `addons/kanama` is missing, or it holds another version than `addons/kanama/kanama.version` names. | Unzip the kit's `addons/` folder or the store addon into the project again. See [Install from a Release Kit](release-kit.md#troubleshooting). |
| Godot says `your scripts were built for Kanama` one version `but the addon is` another. | You unzipped a newer addon after your last build. | Press **Sync Addon** in that message, or **Build Scripts**. See [Install from a Release Kit](release-kit.md#troubleshooting). |
| `net.multigesture.kanama` imports are red in IntelliJ, and nothing completes. | The Gradle import did not finish, or found no Kanama libraries. | Check that `addons/kanama/maven` exists, then click **Reload All Gradle Projects** in the Gradle tool window. See [Open in IntelliJ IDEA](intellij.md#common-problems). |

## Running Scripts

| What you see | Cause | Fix |
| --- | --- | --- |
| A node acts as if it had no script, and the output says `[kanama:kt] WARNING: no Kotlin class bound for res://...`. | `kanama-scripts.jar` has no class for that file: the scripts are not built, or the file is not named after its class. Kanama finds the class by the file's name. | Name the file after the class (`Player.kt` for `class Player`), then press **Build Scripts**. See [Adding a Script to a Node](../game-dev/scripts.md#adding-a-script-to-a-node). |
| `ClassNotFoundException` or `NoClassDefFoundError` names a class from a library you added to `build.gradle.kts`. | A desktop limit; see [Limitations](../reference/limitations.md#desktop). | Remove the dependency, or copy the library's Kotlin source into `kotlin-src/`. |
| `SCRIPT ERROR:` with a Java exception and a Kotlin file and line. | An exception escaped your code. The game goes on with the next frame. | Open the file and line from the Debugger's Errors tab. See [Printing and Errors](../game-dev/scripts.md#printing-and-errors). |
| `Invalid access to previously freed instance`. | Your code used a node or object after Godot freed it. | Ask `GD.isInstanceValid(node)` first. See [Printing and Errors](../game-dev/scripts.md#printing-and-errors). |
| `println` output is missing from the Output panel. | `println` writes to the process's own output, which only the terminal that started Godot shows. | Use `GD.print`. See [Printing and Errors](../game-dev/scripts.md#printing-and-errors). |
| A change to a `@RegisterClass` type has no effect. | Godot reads those classes once, at startup. | Restart the editor. See [Reloading after a Build](editor-workflow.md#reloading-after-a-build). |
| IntelliJ says "Connection refused" when you attach the debugger. | The game is not listening: debugging is off, or the game started before you turned it on. | Look for `JDWP debug agent enabled at` in the Godot output, and check that IntelliJ uses the same port. See [Open in IntelliJ IDEA](intellij.md#common-problems). |
| A breakpoint never hits. | The line ran before you attached, or the node still runs the code from before your last build. | Restart the scene or the game, then attach again. See [Open in IntelliJ IDEA](intellij.md#common-problems). |

## Exporting

The desktop rows mention the **assembly step**: after Godot exports the game,
`scripts/export_game_assemble.sh`, run from your Kanama checkout, adds the
Java runtime and your compiled scripts to the export, as
[Exporting a Game](../exporting/desktop.md#exporting-a-game) shows.

| What you see | Cause | Fix |
| --- | --- | --- |
| The export stops with `Kanama: Export stopped:`. | A scene uses a Kotlin script that does not load in the editor, usually because the scripts are not built. | Build your scripts as the page for your platform shows, then export again: [Desktop](../exporting/desktop.md), [Android](../exporting/android.md), [iOS](../exporting/ios.md), [Web](../exporting/web.md). See [Export Guard](editor-workflow.md#export-guard). |
| An exported desktop game cannot find Java, or uses the JDK installed on your machine. | The export has no `runtime/` folder: the assembly step did not run on it. | Run the assembly step on the folder that holds the executable, or on the `.app`. See [Export for Desktop](../exporting/desktop.md#troubleshooting). |
| macOS kills the exported game at launch. | A file changed after the game was signed. | Run the assembly step again, which signs again, and sign for distribution only after it. See [Export for Desktop](../exporting/desktop.md#troubleshooting). |
| An exported desktop game runs old Kotlin code. | The assembly step copies your scripts as they were when it ran. | Build your scripts, then run the assembly step again. See [Export for Desktop](../exporting/desktop.md#troubleshooting). |
| An exported macOS game says `"libkanama_bootstrap.dylib" Not Opened`. | Gatekeeper quarantined a downloaded copy of the game. | On your own Mac, run `xattr -dr com.apple.quarantine /path/to/MyGame.app`. For players, sign and notarize the game; see [Signing for Distribution](../exporting/desktop.md#signing-for-distribution). |

## Android

| What you see | Cause | Fix |
| --- | --- | --- |
| `adb install` fails with `INSTALL_FAILED_OLDER_SDK`, or a store does not offer the game on a phone. | The phone runs Android 12 or older; Kanama games need Android 13 or newer. | Use a phone with Android 13 or newer. See [Requirements](../exporting/android.md#requirements). |
| `adb install` fails with `INSTALL_FAILED_UPDATE_INCOMPATIBLE`. | The phone has a copy of the game signed with another key. | Uninstall that copy (`adb uninstall com.example.mygame`), then install again. See [Running on a Device](../exporting/android.md#running-on-a-device). |
| The export stops with `Kanama: Export stopped: Android 13 or newer is required`, or Gradle says `minSdkVersion ... cannot be smaller than version 33`. | The Android preset's **Min SDK** is below 33. | Set **Min SDK** to `33` under **Gradle Build** in the preset. See [Export for Android](../exporting/android.md#troubleshooting). |
| `buildAndroidScripts` (or `installAndroidPluginAar` from a checkout) stops at a line such as `callback?.invoke()`. | An Android limit; see [Limitations](../reference/limitations.md#android). | Call the function directly: `callback()`. |
| `buildAndroidScripts` stops with `Android SDK not found`. | Neither `ANDROID_HOME` nor Godot's Android SDK Path names an SDK. | Set `ANDROID_HOME`, or **Editor Settings > Export > Android > Android SDK Path** in Godot. See [Export for Android](../exporting/android.md#building-your-scripts-for-android). |
| Gradle stops with `Task 'buildAndroidScripts' not found`. | The project's Gradle files are older than its addon. | Merge the template's `pluginManagement { }` and `plugins { }` blocks into your Gradle files. See [Add Kanama to an Existing Project](store-addon.md#projects-that-already-have-a-gradle-build). |
| The export stops with `ETC2/ASTC texture compression is required for Android export`. | The project does not import textures for Android yet. | Run `./gradlew buildAndroidScripts`, which turns the import on, then choose **Project > Reload Current Project** in Godot, or press **Fix Import** in the export dialog. See [Export for Android](../exporting/android.md#troubleshooting). |
| The export logs `No loader found for resource: res://….kt`, or scenes lose their property values. | The desktop addon in the project is missing or out of date. | Run `./gradlew buildAndroidScripts` again (from a checkout, the two install commands), delete `.godot/exported`, and export again. See [Export for Android](../exporting/android.md#troubleshooting). |
| The app crashes at startup, in PanamaPort, the library Kanama uses to call Godot on Android. | `android/plugins/KanamaAndroid.gdap` names an old PanamaPort build. | Run `./gradlew buildAndroidScripts` again, then export again. See [Export for Android](../exporting/android.md#troubleshooting). |
| An older phone crashes inside its graphics driver before any script runs. | Some old OpenGL drivers crash on Godot 4.7's shaders. | Use the **Mobile** renderer, which uses Vulkan. See [Export for Android](../exporting/android.md#troubleshooting). |
| The app fails to create its render surface at launch. | The phone's screen is locked. | Unlock the phone and keep the screen on. |
| Gradle cannot resolve `com.github.falcon4ever.PanamaPort`. | The export downloads that library from JitPack, a package host, and needs network access. | Connect, then run `./gradlew buildAndroidScripts` again. See [Export for Android](../exporting/android.md#troubleshooting). |

## iOS

| What you see | Cause | Fix |
| --- | --- | --- |
| Godot cannot export for iOS. | The iOS export templates are not installed. | Install the iOS export templates for Godot 4.7.2. See [Export for iOS](../exporting/ios.md#requirements). |
| Xcode reports no signing team, or cannot create a provisioning profile. | The preset has no team, or Xcode is not signed in to your Apple Developer account. | Set the team in the preset or under **Signing & Capabilities**, and sign in to your account in Xcode. See [Export for iOS](../exporting/ios.md#troubleshooting). |
| `buildIosScripts` stops with `Xcode not found`. | The Mac has only the Command Line Tools, or `xcode-select` points at them. | Install Xcode and run `sudo xcode-select -s /Applications/Xcode.app`. See [Export for iOS](../exporting/ios.md#requirements). |
| `buildIosScripts` stops with `addons/kanama/maven has no Kanama iOS runtime`. | The addon came from a Linux or Windows kit, or the unzip was incomplete. | Unzip the macOS kit or the store addon of the same Kanama version into the project again. |
| The iOS export fails with `Export Icons: Invalid icon`. | The project has no app icon. | Set **Project Settings > Application > Config > Icon**. |
| Scenes load without their script property values, and the export log shows `no Kotlin class bound`. | The iOS build did not include your scripts. | Run `buildIosScripts` (`installIosAddon` from a checkout), delete `.godot/exported`, and export again. See [Export for iOS](../exporting/ios.md#troubleshooting). |
| The log shows a line with `FAULT`. | A call from your script did not reach Godot. `bind-lookup-failed` or `api-unresolved` almost always means the iOS libraries were built for another Godot version. | Run `buildIosScripts` again and export again. For any other reason, report the first `FAULT` line with the log. See [FAULT Lines in the Log](../exporting/ios.md#fault-lines-in-the-log). |

## Web

| What you see | Cause | Fix |
| --- | --- | --- |
| The page is blank when you open `index.html` from disk. | A browser does not load the game's files into a page opened from disk. | Serve the folder over HTTP. See [Running the Export](../exporting/web.md#running-the-export). |
| The page loads on a phone or another computer, but the game never starts. | Godot's Web export only starts from a secure address, and a plain-HTTP network address is not one. | Serve with `--lan --https` and accept the certificate warning once on the device. See [Testing on a Phone or Tablet](../exporting/web.md#testing-on-a-phone-or-tablet). |
| The Web build stops at one of your scripts. | The script uses a callback, function signature or API the Web build does not have; the error names it. | Change what the error names. See [Web-Compatible Scripts](../exporting/web.md#web-compatible-scripts). |
| `exportWeb` stops with `The Kanama addon has no Web runtime`. | The addon is incomplete, or older than the Web export. | Unzip the kit or the store addon of the same Kanama version into the project again. See [Export for the Web](../exporting/web.md#troubleshooting). |
| `exportWeb` fails with `Java heap space` or `OutOfMemoryError`. | Compiling a project to WebAssembly needs more memory than the Web build gives the compiler; a project larger than the Kanama demos can need more. | Run `./gradlew exportWeb -PkanamaWebHeap=6g`. From a source checkout, raise `-Xmx` in `kotlin.daemon.jvmargs` of the checkout's `gradle.properties`, or add `--no-daemon -Pkotlin.compiler.execution.strategy=in-process` to the command. See [Export for the Web](../exporting/web.md#troubleshooting). |
| The page says `Kanama Web protocol mismatch` or `Kanama Web runtime not started`. | The page's files do not come from one `exportWeb`. | Run `exportWeb` again, serve the new export, and reload without the cache. See [Export for the Web](../exporting/web.md#troubleshooting). |
| From a source checkout, `exportWeb` says `Web project not found` or `has no project.godot`. | The path you passed is not your project folder. | Pass the folder that holds `project.godot`, as an absolute path. See [Export for the Web](../exporting/web.md#from-a-source-checkout). |
| `packageWebExport` says `No Web export to package`, or `stale or interrupted export`. | There is no complete export to package. | Run `exportWeb` again for the same project, then package. See [Export for the Web](../exporting/web.md#troubleshooting). |

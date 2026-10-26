# Export for Android

Kanama runs inside a normal Godot Android export as an Android plugin. Your
scripts run on the phone's own Java runtime. You need no Godot fork and no
Android or native code of your own.

## Requirements

- A Kanama project that runs in the editor, made from a
  [release kit](../getting-started/release-kit.md) or set up with the
  [store addon](../getting-started/store-addon.md). The addon carries the
  Kanama Android runtime, so you need nothing else from Kanama.
- Godot's Android export templates for 4.7.2, installed from **Editor > Manage
  Export Templates**.
- A JDK 21 for Godot's Android build, set in **Editor Settings > Export >
  Android > Java SDK Path**. Kanama and your scripts still build with your
  JDK 25.
- The Android SDK, set in **Editor Settings > Export > Android > Android SDK
  Path**, or with `ANDROID_HOME`. Install these packages with Android Studio's
  SDK Manager or `sdkmanager`:

    | SDK package | Version |
    | --- | --- |
    | SDK Platform | 36 |
    | Build-Tools | 36.1.0 |
    | NDK | 29.0.14206865 |

- A phone or emulator with Android 13 or newer, USB debugging on, and `adb`
  from the SDK's `platform-tools`.

## Building Your Scripts for Android

Run this in your project:

```sh
cd /path/to/your-project
./gradlew buildAndroidScripts
```

It builds your scripts for desktop first, as **Build Scripts** does, because
the editor loads them during the export. Then it compiles the scripts in
`kotlin-src/` for Android and writes three files to `android/plugins/`:
`KanamaAndroid.gdap`, which tells Godot about the plugin, and two AAR files
(Android library archives), one with the Kanama runtime and one with your
scripts. It also adds the Android lines to `addons/kanama/kanama.gdextension`,
and turns on the ETC2/ASTC texture import in `project.godot`, which Godot's
Android export needs. If the project is open in Godot when that happens,
choose **Project > Reload Current Project**: until you do, the editor keeps
its old settings and would write them back over the change.

The Android SDK is the one Godot uses: `ANDROID_HOME`, or **Editor Settings >
Export > Android > Android SDK Path**. The first run downloads Android's
Gradle plugin and the PanamaPort library Kanama calls Godot through, so it
needs a network connection.

Run it again after every change to your scripts, before you export.

## Setting Up the Export Preset

1. In Godot, choose **Project > Install Android Build Template**.
2. In **Project > Export**, add an **Android** preset.
3. Under **Gradle Build**, turn on **Use Gradle Build** and set **Min SDK** to
   `33`. Kanama needs Android 13 or newer, and the export stops with a message
   if Min SDK is lower or empty.
4. Under **Architectures**, turn on `arm64-v8a`. Add `x86_64` for an emulator
   on an Intel or AMD machine.
5. Set **Package > Unique Name**, for example `com.example.mygame`.
6. Under **Plugins**, turn on **KanamaAndroid**. It appears there once
   `buildAndroidScripts` has run.

## Exporting an APK

When the export begins, the Kanama Tools editor plugin checks that every
Kotlin script the exported scenes use loads in the editor, and stops the
export if one does not; see [Export Guard](../getting-started/editor-workflow.md#export-guard).

Delete Godot's cache of converted scenes, then export from **Project > Export**
or from the command line:

```sh
rm -rf /path/to/your-project/.godot/exported
godot --headless --path /path/to/your-project \
  --export-debug Android /path/to/build/mygame.apk
```

`Android` is the preset's name. The cache can hold scenes converted while an
older `kanama-scripts.jar` was installed, which would lose their script
property values.

To check that the exported scenes kept their script property values, run
the checker the addon carries:

```sh
godot --headless --path /path/to/your-project \
  --script res://addons/kanama/checks/check_exported_scene_properties.gd \
  -- --require-converted
```

It names each node and property the export dropped. A project that sets
`editor/export/convert_text_resources_to_binary=false` exports its scenes as
text, and the check passes with a note saying so.

## Running on a Device

Connect the phone, then install and start the APK:

```sh
adb install -r /path/to/build/mygame.apk
adb shell monkey -p com.example.mygame 1
adb logcat -s godot
```

Your `GD.print` output and script errors appear in the `godot` log. Godot's
one-click deploy, the Android button in the editor's toolbar, does the same
in one step.

## Release Builds

Export with `--export-release` and a release keystore set in the preset, as
for any Godot Android game.

## Performance on Devices

A phone shows a short hitch the first time the game loads a scene, decodes an
audio stream, starts particles or builds a mesh. Load and cache those during
startup, and avoid rebuilding generated meshes every frame.

## From a Source Checkout

A project you build from a checkout of the Kanama repository, as
[Install from a Source Checkout](../getting-started/source-checkout.md) sets
up, gets its Android plugin from the checkout instead. Run these from the
checkout, in this order:

```sh
cd /path/to/kanama
./gradlew installAndroidPluginAar -PkanamaAndroidProjectDir=/path/to/your-project
./gradlew installAddonJar \
  -PkanamaProjectDir=/path/to/your-project \
  -PkanamaProjectScriptsDir=/path/to/your-project/kotlin-src
```

The first builds the Kanama Android runtime from the checkout and writes the
same three files to `android/plugins/`. The second refreshes the desktop
addon and keeps the Android lines the first adds to
`addons/kanama/kanama.gdextension`. Run both again after every change to your
scripts. Building the runtime also needs the SDK's **CMake** package,
version 3.22.1.

## Troubleshooting

| Problem | Fix |
| --- | --- |
| `buildAndroidScripts` fails and names a line that calls `.invoke(` on a Kotlin function value, such as `callback?.invoke()`. | The Android build cannot compile that call. Write `callback()` or `callback?.let { it() }` instead. |
| `buildAndroidScripts` stops with `Android SDK not found`. | Set `ANDROID_HOME` to your Android SDK folder, or set **Editor Settings > Export > Android > Android SDK Path** in Godot. |
| `buildAndroidScripts` stops with `addons/kanama/maven has no Kanama Android runtime`. | The addon is incomplete. Unzip the kit or the store addon into the project again. A project built from a source checkout uses the commands in [From a Source Checkout](#from-a-source-checkout) instead. |
| Gradle stops with `Task 'buildAndroidScripts' not found`. | The project's Gradle files are older than its addon. Merge the template's `pluginManagement { }` block into your `settings.gradle.kts` and its `plugins { }` block into your `build.gradle.kts`, as [Add Kanama to an Existing Project](../getting-started/store-addon.md#projects-that-already-have-a-gradle-build) shows. |
| The export stops with `ETC2/ASTC texture compression is required for Android export`. | Run `./gradlew buildAndroidScripts`, which turns the import on, then choose **Project > Reload Current Project** in Godot and export again. Or press **Fix Import** in the export dialog. |
| The export logs `No loader found for resource: res://….kt`, or scenes lose their property values. | The desktop addon is missing or out of date. Run `./gradlew buildAndroidScripts` again, delete `.godot/exported`, and export again. |
| The export stops with `Kanama: Export stopped: Android 13 or newer is required`. | Set **Min SDK** to `33` under **Gradle Build** in the Android preset. |
| The Gradle build fails with `uses-sdk:minSdkVersion 26 cannot be smaller than version 33`. | The same fix: set **Min SDK** to `33` in the Android preset. |
| The app crashes at startup before any script runs, in the PanamaPort library Kanama uses to call Godot on Android. | `android/plugins/KanamaAndroid.gdap` names an old PanamaPort build. Run `./gradlew buildAndroidScripts` again and export again. |
| The Gradle build cannot resolve `com.github.falcon4ever.PanamaPort`. | The export downloads it from JitPack, so the machine needs network access. If it has access, run `./gradlew buildAndroidScripts` again to refresh `KanamaAndroid.gdap`. |
| An older phone crashes inside its graphics driver before any script runs. | Some old OpenGL drivers crash on Godot 4.7's shaders. Use the **Mobile** renderer (`rendering/renderer/rendering_method.mobile="mobile"`), which uses Vulkan. Godot falls back to the Compatibility renderer on phones without Vulkan. |
| The app fails to create its render surface at launch. | Unlock the phone and keep the screen on. A locked screen stops the app from drawing. |
| The 3D sky or background looks darker on the emulator than on desktop. | The emulator's OpenGL path renders differently. Check the look on a phone. |

## On Other Platforms

On **Windows**, run `.\gradlew.bat` wherever this page says `./gradlew`.

What does not work on Android is listed in
[Limitations](../reference/limitations.md#android).

# Export for Android

Kanama runs inside a normal Godot Android export as an Android plugin. Your
scripts run on the phone's own Java runtime. You need no Godot fork and no
Android or native code of your own.

## Requirements

- Your project runs in the editor, built from a checkout of the Kanama
  repository. The Android build compiles your scripts from that checkout; see
  [Install from a Source Checkout](../getting-started/source-checkout.md).
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
    | CMake | 3.22.1 |

- A phone with USB debugging on, or an emulator, and `adb` from the SDK's
  `platform-tools`.

## Installing Kanama into the Project

Run these from the Kanama checkout. The first command builds the Android
plugin and compiles the scripts in your project's `kotlin-src/` for Android.
The second refreshes the desktop addon, which the editor needs during the
export:

```sh
cd /path/to/kanama
./gradlew installAndroidPluginAar -PkanamaAndroidDemoDir=/path/to/your-project
./gradlew installAddonJar \
  -PkanamaProjectDir=/path/to/your-project \
  -PkanamaProjectScriptsDir=/path/to/your-project/kotlin-src
```

Run them in this order: the second keeps the Android entries the first adds
to `addons/kanama/kanama.gdextension`. The first writes three files to
`android/plugins/` in your project: `KanamaAndroid.gdap`, which tells Godot
about the plugin, and two AAR files (Android library archives), one with the
Kanama runtime and one with your scripts.

Run both commands again after every change to your scripts, before you export.

## Setting Up the Export Preset

1. In Godot, choose **Project > Install Android Build Template**.
2. In **Project > Export**, add an **Android** preset.
3. Under **Gradle Build**, turn on **Use Gradle Build** and set **Min SDK** to
   `26` or higher.
4. Under **Architectures**, turn on `arm64-v8a`. Add `x86_64` for an emulator
   on an Intel or AMD machine.
5. Set **Package > Unique Name**, for example `com.example.mygame`.
6. Under **Plugins**, turn on **KanamaAndroid**. It appears there once the
   install commands above have run.

## Exporting an APK

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
Kanama's checker in the project:

```sh
godot --headless --path /path/to/your-project \
  --script /path/to/kanama/scripts/check_exported_scene_properties.gd \
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
for any Godot Android game. Release builds run on Android 13 and newer; to
ship to older phones, set the preset's Min SDK to `33`, or ship a debug build.

## Export Guard

When the export begins, the Kanama Tools plugin checks every Kotlin script that
the exported scenes and resources use. If one does not load in the editor, it
stops the export with one error that starts with `Kanama: Export stopped:` and
names the scripts. The usual cause is an out-of-date `kanama-scripts.jar`: run
the two install commands again.

- A command-line export ends before it writes any file, with a non-zero exit
  status.
- An export from the Export dialog shows the error and is marked failed. The
  APK it writes is the one Godot would have written without the check, so do
  not ship it.
- A one-click deploy installs nothing.

Only the scenes and resources the preset exports are checked. A `.kt` file
that no scene uses is not. The project setting
`kanama/tools/export_guard_enabled` turns the check off.

## Performance on Devices

A phone shows a short hitch the first time the game loads a scene, decodes an
audio stream, starts particles or builds a mesh. Load and cache those during
startup, and avoid rebuilding generated meshes every frame.

## Troubleshooting

| Problem | Fix |
| --- | --- |
| `installAndroidPluginAar` fails with `Android demo source audit failed before remap`. | A listed line calls a Kotlin function value with `.invoke(`, such as `callback?.invoke()`. Write `callback?.let { it() }` or `callback()` instead. |
| The export logs `No loader found for resource: res://….kt`, or scenes lose their property values. | The desktop addon is missing or out of date. Run the two install commands, delete `.godot/exported`, and export again. |
| A release APK crashes at startup on Android 12 or older, while the debug APK runs. | Release builds need Android 13. Raise the preset's Min SDK to `33`, or ship a debug build to older phones. |
| The app crashes at startup before any script runs, on phones older than Android 16 or on Android 17. | The Android build uses an older PanamaPort, the library that lets Kanama call Godot on Android. Check the version under [PanamaPort](#panamaport). |
| A minified release APK crashes with `AssertionError: Should not reach here`, then logs `No loader found for resource`. | The build uses the upstream PanamaPort, which code shrinking breaks. Use the version under [PanamaPort](#panamaport). |
| The Gradle build cannot resolve `com.github.falcon4ever.PanamaPort`. | The export downloads it from JitPack, so the machine needs network access. Run `installAndroidPluginAar` again if `KanamaAndroid.gdap` predates this version of Kanama. |
| An older phone crashes inside its graphics driver before any script runs. | Some old OpenGL drivers crash on Godot 4.7's shaders. Use the **Mobile** renderer (`rendering/renderer/rendering_method.mobile="mobile"`), which uses Vulkan. Godot falls back to the Compatibility renderer on phones without Vulkan. |
| The app fails to create its render surface at launch. | Unlock the phone and keep the screen on. A locked screen stops the app from drawing. |
| The 3D sky or background looks darker on the emulator than on desktop. | The emulator's OpenGL path renders differently. Check the look on a phone. |

## PanamaPort

Kanama's Android runtime calls Godot through PanamaPort, a library that brings
Java's foreign-function API to Android. Kanama uses its own build of it:

```text
com.github.falcon4ever.PanamaPort:Core:0.1.5-kanama-r8.1
```

The install command adds it, and Godot's export downloads it from
[JitPack](https://jitpack.io). You only need this name to check a build log.
`-PkanamaPanamaPortCore=<coordinate>` on `installAndroidPluginAar` selects a
different build.

## On Other Platforms

On **Windows**, run `.\gradlew.bat` wherever this page says `./gradlew`.

What does not work on Android is listed in
[Limitations](../reference/limitations.md#android).

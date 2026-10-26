# Export for iOS

On iOS, Kanama compiles your scripts to native code. The game carries no Java
runtime, and your scripts call the same Godot API as on desktop. Godot writes
an Xcode project, and you build and run it on an iPhone or iPad from Xcode.

## Requirements

- A Mac with Xcode 26.5 or newer, the full app and not only the Command Line
  Tools. If `xcode-select -p` prints a Command Line Tools path, run
  `sudo xcode-select -s /Applications/Xcode.app`, or put
  `DEVELOPER_DIR=/Applications/Xcode.app/Contents/Developer` in front of the
  commands on this page.
- A Kanama project that runs in the editor, made from the macOS
  [release kit](../getting-started/release-kit.md) or set up with the
  [store addon](../getting-started/store-addon.md). The addon carries the
  Kanama iOS runtime, so you need nothing else from Kanama.
- 16 GB of memory or more. Linking the release library uses up to about
  11 GB.
- Godot's iOS export templates for 4.7.2, installed from **Editor > Manage
  Export Templates**.
- An Apple Developer account, signed in under **Xcode > Settings > Accounts**.
- An iPhone or iPad with iOS 15 or newer, Developer Mode on, connected and
  trusted.

## Building Your Scripts for iOS

Run this in your project:

```sh
cd /path/to/your-project
./gradlew buildIosScripts
```

It builds your scripts for desktop first, as **Build Scripts** does, because
the editor loads them during the export. Then it compiles the scripts in
`kotlin-src/` to native code for iOS and links them with the Kanama runtime
into two libraries, one debug and one release. It writes them to
`addons/kanama/bin/ios/` as `.xcframework` bundles, Apple's format for a
prebuilt library, and adds their entries to `addons/kanama/kanama.gdextension`,
keeping the desktop and Android entries already there. It also turns on the
ETC2/ASTC texture import in `project.godot`, which Godot's iOS export needs. If
the project is open in Godot when that happens, choose **Project > Reload
Current Project**: until you do, the editor keeps its old settings and would
write them back over the change.

The first run on a Mac downloads the Kotlin/Native compiler and its iOS
tools, about 1 GB, into `~/.konan`, so it needs a network connection. Expect
the first run to take around ten minutes and later runs a few: most of that
is the release library, whose link uses up to about 11 GB of memory.

It uses the Xcode that `DEVELOPER_DIR` names, or else the one
`xcode-select -p` prints. If Xcode is not in `/Applications` and neither names it, add
`-PkanamaXcodeDeveloperDir=/path/to/Xcode.app/Contents/Developer`.

Run it again after every change to your scripts, before you export. When the
export begins, the Kanama Tools editor plugin checks that every Kotlin script
the exported scenes use loads in the editor; see
[Export Guard](../getting-started/editor-workflow.md#export-guard).

The two libraries are large; [Requirements and Platform
Status](../reference/version-support.md#ios-library-size) gives their size.
Keep `addons/kanama/bin/ios/` out of version control, or use Git LFS.

### Unsupported Types on iOS

The iOS build checks every export and script function ahead of time. If iOS
cannot carry a type, such as an `RID` export, the build stops with an error
that names the member and its type. Change that type. To build anyway and
leave those members out on iOS, add `-PkanamaIosAllowSkips=true` to the
`buildIosScripts` command; each skipped member then prints a warning. The
types iOS does not carry are in [Limitations](../reference/limitations.md#ios).

## Setting Up the Export Preset

In **Project > Export**, add an **iOS** preset and set:

- **Application > Bundle Identifier**: an id your Apple team owns, such as
  `com.example.mygame`.
- **Application > App Store Team ID**: your team, as shown in your Apple
  Developer account.
- **Application > Export Project Only**: on. Godot then writes an Xcode
  project for you to build, instead of an `.ipa`. Script errors name the file
  and line only in a debug build made this way.
- **Architectures > arm64**: on.

Godot's iOS export also needs an app icon: set **Project Settings >
Application > Config > Icon**. A project made from the release kit has one.

Save the preset before you use the command line below.

## Exporting the Xcode Project

Delete Godot's cache of converted scenes, then export from **Project > Export**
or from the command line:

```sh
rm -rf /path/to/your-project/.godot/exported
godot --headless --path /path/to/your-project \
  --export-debug iOS /path/to/export/MyGame.ipa
```

`iOS` is the preset's name. With **Export Project Only** on, Godot writes
`MyGame.xcodeproj` and its files into `/path/to/export/`. The cache can hold
scenes converted while an older `kanama-scripts.jar` was installed, which
would lose their script property values.

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

Open `MyGame.xcodeproj` in Xcode, select your iPhone as the run destination,
check the team under **Signing & Capabilities**, and press **Run**.

From the command line, build with `xcodebuild`, then install and start the
app with `devicectl`. Put your own device id, team id and bundle identifier in
place of the placeholders; `xcrun devicectl list devices` lists the device
ids:

```sh
xcodebuild \
  -project /path/to/export/MyGame.xcodeproj \
  -scheme MyGame \
  -configuration Debug \
  -sdk iphoneos \
  -destination "id=<your-device-id>" \
  -derivedDataPath /path/to/derived-data \
  -allowProvisioningUpdates \
  CODE_SIGN_STYLE=Automatic \
  DEVELOPMENT_TEAM=<your-team-id> \
  build

xcrun devicectl device install app \
  --device <your-device-id> \
  /path/to/derived-data/Build/Products/Debug-iphoneos/MyGame.app

xcrun devicectl device process launch \
  --device <your-device-id> \
  --terminate-existing \
  com.example.mygame
```

`-allowProvisioningUpdates` lets Xcode create the provisioning profile.

`GD.print` output and script errors go to the device's system log. Read them
in Xcode's console or in Console.app. Kotlin's `println` writes to the app's
standard output, which Xcode's console also shows.

For a release build, export with `--export-release` and build with
`-configuration Release`. Distribution signing and App Store upload work as
for any Godot game; see Godot's
[Exporting for iOS](https://docs.godotengine.org/en/stable/tutorials/export/exporting_for_ios.html).

## Audio and the Silent Switch

Kanama sets the app's audio session to the **Ambient** category at startup.
Game audio plays in Ring mode, is muted by the Silent switch, and mixes with
audio from other apps. A game that needs background or media playback sets
its own audio session category in native iOS code after startup.

## From a Source Checkout

A project you build from a checkout of the Kanama repository, as
[Install from a Source Checkout](../getting-started/source-checkout.md) sets
up, gets its iOS libraries from the checkout instead. Run this from the
checkout:

```sh
cd /path/to/kanama
./gradlew installIosAddon \
  -PkanamaIosProjectDir=/path/to/your-project \
  -PkanamaIosProjectScriptsDir=/path/to/your-project/kotlin-src
```

It compiles the scripts in `kotlin-src/` twice, to native code for iOS and
into the desktop `kanama-scripts.jar` the editor needs during the export, and
installs the same two libraries and descriptor entries as `buildIosScripts`.
Run it again after every change to your scripts. It takes
`-PkanamaXcodeDeveloperDir` and `-PkanamaIosAllowSkips=true` as above, and
the scene checker is in the checkout's `scripts/` folder.

## Troubleshooting

| Problem | Fix |
| --- | --- |
| `buildIosScripts` stops with `Xcode not found`. | Install Xcode, then run `sudo xcode-select -s /Applications/Xcode.app`, or set `DEVELOPER_DIR` to its `Contents/Developer` folder. The Command Line Tools alone cannot build for iOS. |
| `buildIosScripts` stops with `addons/kanama/maven has no Kanama iOS runtime`. | The project's addon came from a Linux or Windows kit, or the unzip was incomplete. Unzip the macOS kit or the store addon of the same Kanama version into the project again. |
| `buildIosScripts` fails with `Java heap space` or the Mac runs out of memory. | Linking the release library needs about 11 GB. Close other large apps and run it again. |
| Godot cannot export for iOS. | Install the iOS export templates for Godot 4.7.2. |
| The export fails with `Cannot export project with preset "iOS" due to configuration errors`. | Godot's iOS export needs the ETC2/ASTC texture import. Run `./gradlew buildIosScripts`, which turns it on, then choose **Project > Reload Current Project** in Godot and export again. |
| The export fails with `Export Icons: Invalid icon`. | Set an app icon in **Project Settings > Application > Config > Icon**. |
| Xcode reports no signing team. | Set the team in the preset, or under **Signing & Capabilities** in Xcode. |
| Xcode cannot create a provisioning profile. | Sign in to your Apple Developer account in Xcode and allow provisioning updates, or create the profile in the Apple Developer portal. |
| The iPhone is not a valid run destination. | Connect and trust it, turn on Developer Mode, and check that `xcrun devicectl list devices` lists it. |
| Scenes load without their script property values, such as a `PackedScene` export that is `null` in `_ready`. | The export log shows `[kanama:kt] WARNING: no Kotlin class bound for res://...`. Run `buildIosScripts` again, delete `.godot/exported`, and export again. |
| The export stops with `Kanama: Export stopped:`. | A scene uses a Kotlin script that the editor cannot load. Run `buildIosScripts` and export again. See [Export Guard](../getting-started/editor-workflow.md#export-guard). |
| Godot warns about a missing iOS GDExtension library. | Run `buildIosScripts` again, and check that `addons/kanama/kanama.gdextension` has `ios.debug.arm64` and `ios.release.arm64` entries. |
| The log shows a `FAULT` line. | See [FAULT Lines in the Log](#fault-lines-in-the-log). |

In a project built from a checkout, run `installIosAddon` wherever this table
says `buildIosScripts`.

### FAULT Lines in the Log

A line like this means a call from your script did not reach Godot and
returned an empty value instead:

```text
[kanama][ios][c] FAULT kanama_ios_godot_get_method_bind: bind-lookup-failed Node3D.set_visible hash=1
```

It is never normal in a shipped game. Read the first `FAULT` line in the log;
later ones often follow from it. A `bind-lookup-failed` or `api-unresolved`
line almost always means the iOS libraries were built for another Godot
version: run `buildIosScripts` again and export again. For any other reason,
report the line with the log. What each reason means is in
[iOS Backend Architecture](../contributing/backends/ios.md#fault-reasons).

## On Other Platforms

The libraries Kanama builds are for devices only. A project built from a
source checkout can also run in the iOS simulator, but the simulator says
nothing about frame rate or feel. Use a real device for that.

What does not work on iOS is listed in
[Limitations](../reference/limitations.md#ios).

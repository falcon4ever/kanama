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
- Your project runs in the editor, built from a checkout of the Kanama
  repository. The iOS build compiles your scripts from that checkout; see
  [Install from a Source Checkout](../getting-started/source-checkout.md).
- Godot's iOS export templates for 4.7.2, installed from **Editor > Manage
  Export Templates**.
- An Apple Developer account, signed in under **Xcode > Settings > Accounts**.
- An iPhone or iPad with iOS 15 or newer, Developer Mode on, connected and
  trusted.

## Installing Kanama into the Project

Run this from the Kanama checkout:

```sh
cd /path/to/kanama
./gradlew installIosAddon \
  -PkanamaIosProjectDir=/path/to/your-project \
  -PkanamaIosProjectScriptsDir=/path/to/your-project/kotlin-src
```

It compiles the scripts in `kotlin-src/` twice: to native code for iOS, and
into the desktop `kanama-scripts.jar` that the editor needs during the export.
It writes the iOS libraries to `addons/kanama/bin/ios/` as two `.xcframework`
bundles, Apple's format for a prebuilt library, one debug and one release. It
adds their entries to `addons/kanama/kanama.gdextension` and keeps the desktop
and Android entries already there.

Run it again after every change to your scripts, before you export. When the
export begins, the Kanama Tools editor plugin checks that every Kotlin script
the exported scenes use loads in the editor; see
[Export Guard](../getting-started/editor-workflow.md#export-guard).

If Xcode is not in `/Applications`, add
`-PkanamaXcodeDeveloperDir=/path/to/Xcode.app/Contents/Developer`.

### Unsupported Types on iOS

The iOS build checks every export and script function ahead of time. If iOS
cannot carry a type, such as an `RID` export, the build stops with an error
that names the member and its type. Change that type. To build anyway and
leave those members out on iOS, add `-PkanamaIosAllowSkips=true` to the
`installIosAddon` command; each skipped member then prints a warning. The
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

## Troubleshooting

| Problem | Fix |
| --- | --- |
| Godot cannot export for iOS. | Install the iOS export templates for Godot 4.7.2. |
| Xcode reports no signing team. | Set the team in the preset, or under **Signing & Capabilities** in Xcode. |
| Xcode cannot create a provisioning profile. | Sign in to your Apple Developer account in Xcode and allow provisioning updates, or create the profile in the Apple Developer portal. |
| The iPhone is not a valid run destination. | Connect and trust it, turn on Developer Mode, and check that `xcrun devicectl list devices` lists it. |
| Scenes load without their script property values, such as a `PackedScene` export that is `null` in `_ready`. | The export log shows `[kanama:kt] WARNING: no Kotlin class bound for res://...`. Run `installIosAddon` with `-PkanamaIosProjectScriptsDir`, delete `.godot/exported`, and export again. |
| The export stops with `Kanama: Export stopped:`. | A scene uses a Kotlin script that the editor cannot load. Run `installIosAddon` with `-PkanamaIosProjectScriptsDir` and export again. See [Export Guard](../getting-started/editor-workflow.md#export-guard). |
| Godot warns about a missing iOS GDExtension library. | Run `installIosAddon` again, and check that `addons/kanama/kanama.gdextension` has `ios.debug.arm64` and `ios.release.arm64` entries. |
| The log shows a `FAULT` line. | See [FAULT Lines in the Log](#fault-lines-in-the-log). |

### FAULT Lines in the Log

A line like this means a call from your script did not reach Godot and
returned an empty value instead:

```text
[kanama][ios][c] FAULT kanama_ios_godot_get_method_bind: bind-lookup-failed Node3D.set_visible hash=1
```

It is never normal in a shipped game. Read the first `FAULT` line in the log;
later ones often follow from it. A `bind-lookup-failed` or `api-unresolved`
line almost always means the iOS libraries were built for another Godot
version: run `installIosAddon` again and export again. For any other reason,
report the line with the log. What each reason means is in
[iOS Backend Architecture](../contributing/backends/ios.md#fault-reasons).

## On Other Platforms

The iOS simulator can build and start a Kanama game, but it says nothing
about frame rate or feel. Use a real device for that.

A Kanama release also carries a prebuilt iOS addon,
`kanama-mobile-addon-ios-v<version>.zip`, with the iOS runtime only. It cannot
compile your scripts, so a game still uses `installIosAddon` from a checkout.
The zip holds both device `arm64` xcframeworks, debug and release. They are large:
roughly 168 MB debug / 110 MB release before zip. <!-- kanama-claim: ios-static-size -->

What does not work on iOS is listed in
[Limitations](../reference/limitations.md#ios).

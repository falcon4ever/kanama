# Requirements and Platform Status

This page lists what you need to install for each way of working with Kanama,
and how far each platform is supported. The current release is Kanama
`0.4.0`.

## Requirements

| Workflow | What you need |
| --- | --- |
| A desktop project, from a release kit, the store addon or a source checkout | **Godot 4.7.2 stable**, the editor and its export templates, from the [Godot 4.7.2 archive](https://godotengine.org/download/archive/4.7.2-stable/). A **JDK 25** or newer; Temurin 25 is a good choice. A computer running macOS on Apple silicon, Windows x64, Linux x64 or Linux arm64. |
| A source checkout | Everything in the first row, plus **CMake 3.22** or newer and a C compiler, because the checkout builds Kanama's native library. Release kits and the store addon ship that library prebuilt. You do not need Godot's source code. |
| Exporting for Android | Godot 4.7.2's Android export templates, a **JDK 21** for Godot's Android build, and the Android SDK packages listed in [Export for Android](../exporting/android.md#requirements). Kanama and your scripts still build with your JDK 25. |
| Exporting for iOS | Godot 4.7.2's iOS export templates, a Mac with the full Xcode app and 16 GB of memory, and an Apple Developer account. See [Export for iOS](../exporting/ios.md#requirements). |
| Exporting for the Web | Godot 4.7.2 and its `web_nothreads_release` export template, and a network connection for the first export. See [Export for the Web](../exporting/web.md#requirements). |

## Platform Status

| Platform | Status | What it means for you |
| --- | --- | --- |
| macOS on Apple silicon | Supported | Edit, build and export. A game you distribute needs your own signature and Apple's notarization; see [Signing for Distribution](../exporting/desktop.md#signing-for-distribution). |
| Windows x64 | Supported | Edit, build and export. Players need no Java and no Visual C++ redistributable. |
| Linux x64 | Supported | Edit, build and export. |
| Linux arm64 | Supported | Edit, build and export. |
| Android | Supported | Export from a release kit, the store addon or a source checkout. Requires Android 13 (API 33) or newer; set **Min SDK** to `33` in the Android export preset. |
| iOS | Supported | Export on a Mac, from the macOS release kit, the store addon or a source checkout. Requires iOS 15 or newer on the iPhone or iPad. |
| Web | Experimental | Export from a release kit, the store addon or a source checkout, to desktop Chrome 130 and Firefox 141 or newer. Safari and phones are checked by hand. |

**Supported** means you can build and ship your own game on that platform.
**Experimental** means the platform runs the Kanama demos, but nothing is
promised for your own game. The runs behind each status are on
[Platform Evidence](../contributing/platform-evidence.md).

A desktop export carries its own Java runtime, and you can build it for any
of the four desktop platforms from any of them. What does not work on
each platform, and what to do instead, is on [Limitations](limitations.md).

## Godot Version

Kanama is built against Godot 4.7.2 stable. Its Godot classes are generated
from that version's API, so use 4.7.2 for the editor, the export templates and
the games you ship. Kanama moves to a new Godot version only when that version
has a stable release.

## Kotlin Version

The current source build uses Kotlin `2.4.21`, KSP `2.3.12`, and
kotlinx.coroutines `1.11.0`. Your scripts compile with the same versions.

An Android APK runs on the Kotlin libraries of Godot's Android build template
(4.7.2: kotlin-stdlib 2.1.21, kotlinx.coroutines 1.6.4), so the Android runtime
and scripts compile at Kotlin API level 2.1 and against kotlinx.coroutines 1.6.4:
a newer API fails the build, not the device.

## iOS Library Size

The iOS build adds Kanama to your game as two prebuilt libraries,
~168.0 MB debug / ~109.6 MB release static `.a`, measured on a clean link with
Kotlin 2.4.21. A project built from a release kit links a larger debug library,
about 195 MB. Both libraries sit in your project's `addons/kanama/bin/ios/`
folder, so keep them out of version control or use Git LFS.

## Next Steps

- [Limitations](limitations.md) lists what each platform cannot do.
- [Upgrading from 0.4](../getting-started/upgrading-0.4-to-0.5.md) covers moving
  a project to the next release.
- [Platform Evidence](../contributing/platform-evidence.md) records the device
  and browser runs behind each status.

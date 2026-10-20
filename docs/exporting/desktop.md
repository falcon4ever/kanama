# Export a Desktop Game

An exported Kanama game carries its own trimmed Java runtime, so players
install nothing. You export from Godot as usual, then one script adds the
runtime and your compiled scripts to the export. The same steps work for
Windows, macOS and Linux, from any of the three.

## Requirements

- The project runs in the editor: Godot 4.7.2 and a JDK 25 or newer, as in
  [Get Started](../getting-started/index.md#requirements).
- Godot's export templates for 4.7.2, installed from **Editor > Manage Export
  Templates**.
- A checkout of the Kanama repository, which builds the runtime and holds the
  assembly script. If you started from a release kit or the store addon, clone
  it once as in [Cloning Kanama](../getting-started/source-checkout.md#cloning-kanama);
  you do not need to change your project.
- An export preset for your target in **Project > Export**.

## Exporting a Game

1. Build your scripts. Press **Build Scripts** in the editor, or run:

    ```sh
    cd /path/to/your-project
    ./gradlew buildScripts
    ```

2. Build the Java runtime in the Kanama checkout:

    ```sh
    cd /path/to/kanama
    ./gradlew jlinkGameRuntime
    ```

    The runtime lands in `build/game-runtime/runtime`. It is built for the
    machine you run it on; to export for another platform, see
    [Exporting for Another Platform](#exporting-for-another-platform).

3. Export the game from Godot with **Project > Export**, or from the command
   line:

    ```sh
    godot --headless --path /path/to/your-project \
      --export-release "Windows Desktop" /path/to/export/MyGame.exe
    ```

    Use your preset's name in place of `"Windows Desktop"`.

4. Add the runtime and your scripts to the export, from the Kanama checkout:

    ```sh
    cd /path/to/kanama
    scripts/export_game_assemble.sh \
      --kanama-jar /path/to/your-project/addons/kanama/kanama.jar \
      --scripts-jar /path/to/your-project/addons/kanama/kanama-scripts.jar \
      /path/to/export
    ```

    The last argument is the folder that holds the exported executable, or,
    for macOS, the exported `.app` bundle.

The export folder now holds everything the game needs:

| File | What it is |
| --- | --- |
| The executable and `.pck` | Godot's export, as usual. |
| `kanama_bootstrap.dll`, `libkanama_bootstrap.so` or `libkanama_bootstrap.dylib` | The Kanama native library. Godot's export copies it. |
| `kanama.jar` | The Kanama runtime. |
| `kanama-scripts.jar` | Your compiled scripts. |
| `runtime/` | The Java runtime the game starts. |
| `kanama-jvm-options.txt` | Your [JVM options](#jvm-options), when the project sets any. |

On macOS these sit inside the `.app`; see [Signing for macOS](#macos-bundle-layout-entitlements-and-signing).

Ship the whole folder, or the `.app`, as one zip.

## Running the Exported Game

Run the exported executable from a terminal once. Kanama prints which Java
runtime it loaded:

```text
[kanama] using libjvm: /path/to/export/runtime/lib/server/libjvm.so
```

The path is inside the export folder when the bundled runtime is in place. On
Windows it ends in `runtime\bin\server\jvm.dll`, and on macOS in
`Contents/Resources/runtime/lib/server/libjvm.dylib`. If it names a JDK
installed on your machine instead, the export has no `runtime/` folder: run
step 4 again.

## Exporting for Another Platform

You can build the runtime for any desktop target from any desktop host.
`jlinkGameRuntimeCross` builds it for the target you name, and the assembly
script takes it with `--runtime`:

```sh
cd /path/to/kanama
./gradlew jlinkGameRuntimeCross -PkanamaRuntimeTarget=windows-x64
scripts/export_game_assemble.sh \
  --runtime build/game-runtime/windows-x64/runtime \
  --kanama-jar /path/to/your-project/addons/kanama/kanama.jar \
  --scripts-jar /path/to/your-project/addons/kanama/kanama-scripts.jar \
  /path/to/windows-export
```

The targets are `windows-x64`, `linux-x64`, `linux-arm64` and `macos-arm64`.
The first build for a target downloads that platform's Java modules (about
80 MB). They are kept in `~/.gradle/kanama-target-jmods`, so later builds skip
the download. Set `-PkanamaTargetJmodsCacheDir` or `KANAMA_TARGET_JMODS_CACHE`
to keep them somewhere else.

The assembly script refuses a runtime built for a different platform than the
export, because that game would not start on a player's machine.

## Adding Java Modules

The runtime holds the Java modules every Kanama game needs and nothing more.
If your scripts use another part of the JDK, such as `java.net.http`, add its
module when you build the runtime:

```sh
./gradlew jlinkGameRuntime -PkanamaRuntimeAdditionalModules=java.net.http
```

Separate several modules with commas. `jlinkGameRuntimeCross` takes the same
property.

## Download Size

The runtime adds about 31 MB to a Windows or macOS game, and about 40 MB to a
Linux one. `kanama.jar` adds about 12 MB more. Godot's own executable is
usually the largest file in the download.

## JVM Options

Kanama starts Java with its default heap and garbage collector settings. To
pass your own options, such as a heap size or a GC log, set them in one of
three places. A later one wins:

1. `kanama-jvm-options.txt` next to `kanama.jar`. This is how an exported game
   carries them. The assembly script writes it from the project setting below,
   or from `--jvm-options "..."`. Lines that start with `#` are comments.
2. The project setting `kanama/jvm/options`, in **Project > Project Settings >
   Kanama** with **Advanced Settings** on, or as `jvm/options="..."` under
   `[kanama]` in `project.godot`. It applies when you run the game from the
   editor. Restart the game after you change it.
3. The `KANAMA_JVM_OPTIONS` environment variable, for one run:

    ```sh
    KANAMA_JVM_OPTIONS="-Xmx512m -Xlog:gc" godot --path /path/to/your-project
    ```

Separate options with spaces. Quote a part that contains spaces with `"..."`
or `'...'`, as in `-Dgame.title="My Game"`. A word that does not start with `-`
is reported and skipped, and options after the 64th are dropped.

`JAVA_TOOL_OPTIONS` also works, with one exception: Java ignores
`-XX:MaxNewSize` and `-XX:NewSize` there. Put those in one of the three places
above; `-Xmn` works in all of them.

If frames are slow for a few seconds after your game creates many objects at
once, such as on a level load, add `-XX:MaxNewSize=128m`. It costs a little
frame time the rest of the time, so add it only when you see that slowdown.

## macOS Bundle Layout, Entitlements and Signing

A macOS export is an `.app` bundle. The assembly script puts Kanama's files in
`Contents/Resources/`, beside the `.pck`:

```text
MyGame.app/Contents
├── Frameworks/libkanama_bootstrap.dylib   # where Godot's export puts it
├── MacOS/MyGame                           # the exported Godot executable
├── Resources
│   ├── MyGame.pck
│   ├── kanama.jar
│   ├── kanama-scripts.jar
│   └── runtime/                           # the bundled Java runtime
│       ├── bin/{java,keytool}
│       └── lib/server/libjvm.dylib
└── _CodeSignature/CodeResources
```

`Resources/` is the one place where `codesign` accepts the jars and the
runtime. Adding files after Godot's export breaks the signature Godot wrote,
so the script signs the bundle again with an ad-hoc signature:

```sh
codesign --force --sign - --preserve-metadata=entitlements,flags,identifier MyGame.app
```

### Entitlements

The macOS export preset needs **Import ETC2 ASTC** turned on in the project's
VRAM compression settings, for any universal or arm64 export. It also needs
three hardened-runtime entitlements in the preset's codesign section, because
Java writes and runs its own machine code:

| Preset option | Entitlement in the signed app | Why Java needs it |
| --- | --- | --- |
| `allow_jit_code_execution` | `com.apple.security.cs.allow-jit` | Without it Java crashes before it starts. |
| `allow_unsigned_executable_memory` | `com.apple.security.cs.allow-unsigned-executable-memory` | Java runs code it generated itself. |
| `disable_library_validation` | `com.apple.security.cs.disable-library-validation` | The runtime's libraries are signed by the JDK vendor, not by your team. |

Check a build with `codesign -dv --entitlements - MyGame.app`. It should list
those three entitlements, and `codesign --verify --deep --strict MyGame.app`
should pass.

### Signing for Distribution

The ad-hoc signature is fine for your own machine. A game downloaded from the
internet meets Gatekeeper, which needs a Developer ID signature and Apple's
notarization. Kanama does not sign or notarize for you. To do it yourself:

1. Export, then assemble. The assembly step replaces any signature the export
   had, so sign last.
2. Sign every executable file inside `Contents/Resources/runtime/` first:
   every `.dylib`, plus `bin/java`, `bin/keytool` and `lib/jspawnhelper`.
   Signing the `.app` does not sign them.

    ```sh
    codesign --force --timestamp --options runtime \
      --sign "Developer ID Application: Your Name (TEAMID)" <file>
    ```

3. Sign the `.app` itself last, the same way. Changing any file inside it
   afterwards breaks the signature again.
4. Zip the `.app`, submit it with `xcrun notarytool submit`, then run
   `xcrun stapler staple MyGame.app`.

Once the runtime carries your own signature, you can drop
`disable_library_validation`.

## Troubleshooting

| Problem | Fix |
| --- | --- |
| The exported game cannot find Java, or uses a JDK installed on your machine. | The export has no `runtime/` folder. Run the assembly step on the folder that holds the executable, or on the `.app`. |
| macOS kills the game at launch. | A file changed after the last signature. Run the assembly step again, which re-signs, and sign for distribution only after it. |
| macOS reports `"libkanama_bootstrap.dylib" Not Opened`. | Gatekeeper quarantined a downloaded copy. On your own machine, run `xattr -dr com.apple.quarantine /path/to/MyGame.app`. For players, sign and notarize the game. |
| The assembly script refuses the runtime. | The runtime was built for a different platform than the export. Build it with `jlinkGameRuntimeCross` for the export's target. |
| `jlinkGameRuntimeCross` fails on a version mismatch. | The downloaded Java modules must have the same major version as the JDK running Gradle. Build with a JDK 25. |
| The export stops with `Kanama: Export stopped:`. | A scene uses a Kotlin script that does not load in the editor. Press **Build Scripts** and export again. See [Settings](../getting-started/editor-workflow.md#settings). |
| The exported game runs old Kotlin code. | Build your scripts, then run the assembly step again. It copies `kanama-scripts.jar` at the time you run it. |

## On Other Platforms

On **Windows**, run `.\gradlew.bat` wherever this page says `./gradlew`, and
run `export_game_assemble.sh` from Git Bash. A Windows game needs no Visual C++
redistributable on the player's machine.

What does not work on desktop is listed in
[Limitations](../reference/limitations.md).

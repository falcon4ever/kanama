# Use a Source Checkout

Use this path when you want the current Kanama source tree to install into a
Godot project. It is also the right path for testing unreleased Kanama changes.

## 1. Clone Kanama

```sh
git clone https://github.com/falcon4ever/kanama
cd kanama
```

Keep the checkout near your Godot projects so the editor plugin can find
`gradlew`, or set `kanama/tools/repo_dir` in Godot project settings.

## 2. Create or Prepare a Godot Project

For a new starter project:

```sh
./gradlew createStarterProject \
  -PkanamaStarterProjectDir=/absolute/path/to/kanama-starter
```

For an existing project, copy only the safe starter files:

```sh
./gradlew installStarterTemplate \
  -PkanamaStarterProjectDir=/absolute/path/to/godot_project
```

`installStarterTemplate` copies `HelloScript.kt` and
`addons/kanama_tools` without replacing `project.godot` or your main scene.

## 3. Build and Sync Kanama

```sh
./gradlew installAddonJar \
  -PkanamaProjectDir=/absolute/path/to/kanama-starter \
  -PkanamaProjectScriptsDir=/absolute/path/to/kanama-starter
```

This builds `kanama.jar`, builds the host native bootstrap with CMake, compiles
project `.kt` files into `kanama-scripts.jar`, copies addon files into
`<kanamaProjectDir>/addons/kanama`, and registers the extension in
`<kanamaProjectDir>/.godot/extension_list.cfg`.

If your Kotlin scripts live outside the project root, point
`kanamaProjectScriptsDir` at that folder. For multiple roots, use
`-PkanamaProjectScriptsDirs=` with path-separated or comma-separated paths.

## 4. Open and Iterate

1. Open the project in Godot.
2. Confirm **Kanama Tools** is enabled in **Project > Project Settings > Plugins**.
3. Press **Play**.
4. Edit a Kotlin script.
5. Press **Build Scripts** in the Godot toolbar.
6. Press **Play** again.

See [The Editor Loop](editor-workflow.md) for hot reload and debugger setup.

## Source Checkout Notes

Building Kanama from source does not require a Godot source checkout. The
desktop native bootstrap uses the GDExtension headers tracked in this
repository, JDK 25 headers, CMake, and the platform C toolchain.

The build pins its toolchain to JDK 25 and downloads one once (Gradle toolchain
auto-provisioning) when only another JDK is installed; any JDK 25+ can run
Gradle itself. The native bootstrap is compiled against that JDK 25, passed to
CMake as `-DKANAMA_JAVA_HOME`; without Gradle, CMake uses `JAVA_HOME`, then on
macOS `/usr/libexec/java_home -v 25`, then the system default JDK. On every OS
the configure step stops with a message naming the JDK it found when that JDK's
`jni.h` has no `JNI_VERSION_21`, so a bare `JNI_VERSION_21 undeclared` from
`bootstrap.c` no longer appears: fix the named JDK or `JAVA_HOME`.
Linux and Windows desktop launchers do not pass your shell's `JAVA_HOME` to
Godot; Build Scripts and the runtime pick a JDK 25+ themselves, see
[Which JDK Kanama Uses](editor-workflow.md#which-jdk-kanama-uses).

`syncExampleAddonJar` refreshes the checked-in example project and remains the
local smoke-test path:

```sh
./gradlew syncExampleAddonJar
```

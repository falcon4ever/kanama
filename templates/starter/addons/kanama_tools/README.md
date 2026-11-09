# Kanama Tools

Kanama lets you write Godot scripts in Kotlin. It needs a JDK 25 or newer and
builds your scripts with an editor button. Like C# needs the .NET SDK, it is
not enable-and-go. Install a JDK 25 first (Temurin 25 is a good choice).

Kanama Tools is the editor plugin that does the building. The Kanama runtime
itself is in `addons/kanama`, and a running game does not need this plugin.
The editor side runs on macOS (Apple silicon), Windows x64, and Linux x64 and
arm64.

## From zip to a running Kotlin script

1. Unzip the addon at the root of your Godot project, next to `project.godot`,
   and open the project (Godot 4.8; each release lists its exact Godot
   version). If it was already open, choose **Project > Reload Current
   Project**. On macOS, if Godot says `libkanama_bootstrap.dylib` Not Opened,
   clear the download quarantine as `addons/kanama/README.md` shows.
2. Enable **Kanama Tools** in **Project > Project Settings > Plugins**. A row
   with a status light and a **Build Scripts** button appears in the toolbar.
3. Choose **Project > Tools > Kanama Tools: Set Up Kanama Project**. It adds a
   Gradle build and a starter script, `kotlin-src/HelloScript.kt`, and keeps
   every file you already have.
4. Press **Build Scripts**. The first build downloads Gradle and the Kotlin
   compiler, so it needs a network connection and takes a minute.
5. Load `res://kotlin-src/HelloScript.kt` into the **Script** property of a
   `Node2D`, like a `.gd` file, and run the scene. The node rotates and the
   Output panel prints `[kanama:starter] Hello from Kanama`.

The status light shows where your scripts stand. Green: built and current.
Yellow: building, or a `.kt` file changed since the last build. Red: the build
failed; click the light to read why. Grey: no Kotlin scripts yet. After step 4,
saving a `.kt` file builds it for you.

If Godot warns that it cannot find a JDK 25, set **Editor > Editor Settings >
Kanama > Build > JDK Path** to the JDK folder and restart the editor.

## More

- Setup steps and fixes: https://falcon4ever.github.io/kanama/getting-started/store-addon/
- Edit, build, run: https://falcon4ever.github.io/kanama/getting-started/editor-workflow/
- Documentation: https://falcon4ever.github.io/kanama/
- Source and issues: https://github.com/falcon4ever/kanama

Games export for desktop, Android and iOS from this addon, and for the Web
(experimental). Android, iOS and Web need extra tools; the requirements list
them: https://falcon4ever.github.io/kanama/reference/version-support/#requirements

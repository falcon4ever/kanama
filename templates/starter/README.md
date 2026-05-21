# Kanama Starter Template

This folder is copied into a Godot project by:

`./gradlew installStarterTemplate -PkanamaStarterProjectDir=/absolute/path/to/your_godot_project`

After copy:

1. Open the target project in Godot.
2. Attach `res://HelloScript.kt` to a `Node2D`.
3. Build/update Kanama jars with:
   `./gradlew installAddonJar -PkanamaProjectDir=/absolute/path/to/your_godot_project -PkanamaProjectScriptsDir=/absolute/path/to/your_godot_project`
4. Optional: enable the `Kanama Tools` plugin from `Project -> Project Settings -> Plugins`
   for the `Build Scripts` toolbar button, `Open Kotlin` shortcut, and optional build-on-save.

Use `HelloScript.kt` as the baseline for new script classes. It uses
`@ClassName` and `@Tool` so the class is editor-facing and runs in editor mode
when attached to a scene node.

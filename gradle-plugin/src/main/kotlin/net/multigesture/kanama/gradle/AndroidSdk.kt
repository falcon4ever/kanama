package net.multigesture.kanama.gradle

import java.io.File

/**
 * Finds the Android SDK the way a Godot user has already set it up: `ANDROID_HOME` (or
 * `ANDROID_SDK_ROOT`), then the **Android SDK Path** in Godot's own editor settings, then the folder
 * Android Studio installs to. A user who exports Android from Godot today needs no new setting.
 */
internal object AndroidSdk {
  data class Found(val dir: File, val source: String)

  const val NOT_FOUND =
    "Android SDK not found. Set ANDROID_HOME to your Android SDK, or set Editor Settings > " +
      "Export > Android > Android SDK Path in Godot."

  fun find(
    env: Map<String, String> = System.getenv(),
    osName: String = System.getProperty("os.name"),
    home: File = File(System.getProperty("user.home")),
    godotVersion: String = KanamaVersions.GODOT,
  ): Found? {
    for (name in listOf("ANDROID_HOME", "ANDROID_SDK_ROOT")) {
      val value = env[name]?.trim().orEmpty()
      if (value.isNotEmpty() && File(value).isDirectory) return Found(File(value), name)
    }
    val settings = editorSettingsFile(env, osName, home, godotVersion)
    if (settings != null && settings.isFile) {
      editorSettingsSdkPath(settings.readText())?.let { path ->
        val dir = File(path)
        if (dir.isDirectory) return Found(dir, "Godot's Android SDK Path (${settings.path})")
      }
    }
    val default =
      when {
        osName.startsWith("Mac") -> home.resolve("Library/Android/sdk")
        osName.startsWith("Windows") ->
          env["LOCALAPPDATA"]?.let { File(it).resolve("Android/Sdk") }
            ?: home.resolve("AppData/Local/Android/Sdk")
        else -> home.resolve("Android/Sdk")
      }
    return if (default.isDirectory) Found(default, "the default Android Studio location") else null
  }

  /** `editor_settings-<major.minor>.tres` in Godot's per-user config folder. */
  fun editorSettingsFile(
    env: Map<String, String>,
    osName: String,
    home: File,
    godotVersion: String,
  ): File? {
    val majorMinor = godotVersion.split('.').take(2).joinToString(".")
    val configDir =
      when {
        osName.startsWith("Mac") -> home.resolve("Library/Application Support/Godot")
        osName.startsWith("Windows") -> env["APPDATA"]?.let { File(it).resolve("Godot") } ?: return null
        else ->
          (env["XDG_CONFIG_HOME"]?.takeIf { it.isNotBlank() }?.let(::File) ?: home.resolve(".config"))
            .resolve("godot")
      }
    return configDir.resolve("editor_settings-$majorMinor.tres")
  }

  private val SDK_PATH = Regex("""^export/android/android_sdk_path\s*=\s*"(.*)"\s*$""", RegexOption.MULTILINE)

  fun editorSettingsSdkPath(text: String): String? =
    SDK_PATH.find(text)?.groupValues?.get(1)?.replace("\\\\", "\\")?.takeIf { it.isNotBlank() }
}

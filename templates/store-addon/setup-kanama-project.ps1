# Usage: .\addons\kanama\setup-kanama-project.ps1 [-Force] [-ProjectDir DIR]
# Run from a Godot project root after unzipping the Kanama store addon, or name the project with
# -ProjectDir (the Kanama Tools menu item Set Up Kanama Project does). Copies the packaged Gradle
# template when root Gradle files are missing, adds the starter Kotlin script when kotlin-src has no
# .kt file, adds build/.gdignore so Godot skips Gradle's build folder, and registers the Kanama
# GDExtension. Prints one "wrote", "created" or "registered" line for each thing it adds; the same
# logic as setup-kanama-project.sh. -Force replaces existing template files.
param(
    [switch]$Force,
    [string]$ProjectDir = ""
)

$ErrorActionPreference = "Stop"
if ([string]::IsNullOrEmpty($ProjectDir)) {
    $ProjectDir = (Get-Location).Path
} else {
    $ProjectDir = (Resolve-Path -LiteralPath $ProjectDir).Path
}
$ProjectDir = $ProjectDir.TrimEnd('\', '/')
$TemplateDir = Join-Path $ProjectDir "addons/kanama/templates/release-kit"
$ExtensionPath = "res://addons/kanama/kanama.gdextension"

if (-not (Test-Path -LiteralPath $TemplateDir -PathType Container)) {
    Write-Error "[kanama] missing template directory: $TemplateDir. Run this script from the Godot project root where the addon was unzipped, or pass -ProjectDir."
}

# A settings.gradle.kts of the project's own must resolve the Kanama Gradle plugin, which the template's
# build.gradle.kts applies: its pluginManagement block reads addons/kanama/kanama.version. A file without a
# pluginManagement block gets the template's after its leading @file: and import lines (once: the version
# file's path marks it). A file with a block of its own (`pluginManagement {`, the brace on the same line or
# the next), or a Groovy settings.gradle, is not edited: the setup stops before it changes anything and says
# what to merge. It never writes a settings.gradle.kts beside a settings.gradle. The same rules as the .sh.
$Settings = Join-Path $ProjectDir "settings.gradle.kts"
$MergeSettings = $false
if (Test-Path -LiteralPath (Join-Path $ProjectDir "settings.gradle")) {
    [Console]::Error.WriteLine("[kanama] the project has a Groovy settings.gradle: merge the pluginManagement block of addons/kanama/templates/release-kit/settings.gradle.kts into it (Add Kanama to an Existing Project, Projects That Already Have a Gradle Build), then run the setup again. Nothing was changed.")
    exit 1
}
if ((Test-Path -LiteralPath $Settings) -and -not $Force) {
    $SettingsBytes = [System.IO.File]::ReadAllBytes($Settings)
    $SettingsBom = $SettingsBytes.Length -ge 3 -and $SettingsBytes[0] -eq 0xEF -and $SettingsBytes[1] -eq 0xBB -and $SettingsBytes[2] -eq 0xBF
    $SettingsText = [System.IO.File]::ReadAllText($Settings)
    if ($SettingsText.Contains("addons/kanama/kanama.version")) {
    } elseif ($SettingsText -match '(?m)^[ \t]*pluginManagement[ \t]*(\{|\r?$)') {
        [Console]::Error.WriteLine("[kanama] settings.gradle.kts has a pluginManagement block of its own: merge the one in addons/kanama/templates/release-kit/settings.gradle.kts into it (Add Kanama to an Existing Project, Projects That Already Have a Gradle Build), then run the setup again. Nothing was changed.")
        exit 1
    } else {
        $MergeSettings = $true
    }
}

function Copy-KanamaFile {
    param(
        [string]$Source,
        [string]$Destination
    )
    if ((Test-Path -LiteralPath $Destination) -and -not $Force) {
        Write-Host "[kanama] keeping existing $($Destination.Substring($ProjectDir.Length + 1).Replace('\', '/'))"
        return
    }
    New-Item -ItemType Directory -Force -Path (Split-Path -Parent $Destination) | Out-Null
    Copy-Item -LiteralPath $Source -Destination $Destination -Force
    Write-Host "[kanama] wrote $($Destination.Substring($ProjectDir.Length + 1).Replace('\', '/'))"
}

function Copy-KanamaDirectory {
    param(
        [string]$Source,
        [string]$Destination
    )
    if ((Test-Path -LiteralPath $Destination) -and -not $Force) {
        Write-Host "[kanama] keeping existing $($Destination.Substring($ProjectDir.Length + 1).Replace('\', '/'))"
        return
    }
    if (Test-Path -LiteralPath $Destination) {
        Remove-Item -LiteralPath $Destination -Recurse -Force
    }
    New-Item -ItemType Directory -Force -Path (Split-Path -Parent $Destination) | Out-Null
    Copy-Item -LiteralPath $Source -Destination $Destination -Recurse -Force
    Write-Host "[kanama] wrote $($Destination.Substring($ProjectDir.Length + 1).Replace('\', '/'))"
}

Copy-KanamaFile (Join-Path $TemplateDir "build.gradle.kts") (Join-Path $ProjectDir "build.gradle.kts")
if ($MergeSettings) {
    # Gradle wants pluginManagement before every other statement; only @file: annotations and imports may
    # precede it. The block goes after the file's leading ones (comments and blank lines among them stay),
    # and the rest of the user's lines follow, in the file's own line endings and byte order mark.
    $Block = New-Object System.Collections.Generic.List[string]
    $Keep = $false
    foreach ($Line in [System.IO.File]::ReadAllLines((Join-Path $TemplateDir "settings.gradle.kts"))) {
        if ($Line -match '^pluginManagement \{') { $Keep = $true }
        if ($Keep) { $Block.Add($Line) }
        if ($Keep -and $Line -match '^\}') { break }
    }
    $NewLine = if ($SettingsText.Contains("`r`n")) { "`r`n" } else { "`n" }
    $UserLines = $SettingsText -split "\r?\n"
    $After = 0
    for ($i = 0; $i -lt $UserLines.Length; $i++) {
        $Text = $UserLines[$i].TrimStart()
        if ($Text -match '^(import[ \t]|@file:)') { $After = $i + 1; continue }
        if ($Text -eq "" -or $Text.StartsWith("//")) { continue }
        break
    }
    $Out = New-Object System.Collections.Generic.List[string]
    for ($i = 0; $i -lt $After; $i++) { $Out.Add($UserLines[$i]) }
    if ($After -gt 0) { $Out.Add("") }
    $Out.AddRange($Block)
    $Out.Add("")
    for ($i = $After; $i -lt $UserLines.Length; $i++) { $Out.Add($UserLines[$i]) }
    [System.IO.File]::WriteAllText($Settings, ($Out -join $NewLine), (New-Object System.Text.UTF8Encoding($SettingsBom)))
    Write-Host "[kanama] merged settings.gradle.kts (added the Kanama pluginManagement block)"
} else {
    Copy-KanamaFile (Join-Path $TemplateDir "settings.gradle.kts") $Settings
}
Copy-KanamaFile (Join-Path $TemplateDir "gradlew") (Join-Path $ProjectDir "gradlew")
Copy-KanamaFile (Join-Path $TemplateDir "gradlew.bat") (Join-Path $ProjectDir "gradlew.bat")
Copy-KanamaDirectory (Join-Path $TemplateDir "gradle") (Join-Path $ProjectDir "gradle")

$KotlinSrc = Join-Path $ProjectDir "kotlin-src"
if (-not (Test-Path -LiteralPath $KotlinSrc -PathType Container)) {
    New-Item -ItemType Directory -Force -Path $KotlinSrc | Out-Null
    Write-Host "[kanama] created kotlin-src/"
}
# The starter script only goes into a kotlin-src without Kotlin scripts of its own.
$ExistingScripts = @(Get-ChildItem -LiteralPath $KotlinSrc -Recurse -File -Filter "*.kt")
if ($Force -or $ExistingScripts.Count -eq 0) {
    Copy-KanamaFile (Join-Path $TemplateDir "kotlin-src/HelloScript.kt") (Join-Path $KotlinSrc "HelloScript.kt")
} else {
    Write-Host "[kanama] keeping existing kotlin-src/ (it has Kotlin scripts)"
}

# Gradle's build folder holds generated .kt files: Godot must not import or export them.
$BuildIgnore = Join-Path $ProjectDir "build/.gdignore"
if (Test-Path -LiteralPath $BuildIgnore) {
    Write-Host "[kanama] keeping existing build/.gdignore"
} else {
    New-Item -ItemType Directory -Force -Path (Join-Path $ProjectDir "build") | Out-Null
    New-Item -ItemType File -Force -Path $BuildIgnore | Out-Null
    Write-Host "[kanama] wrote build/.gdignore"
}

$GodotDir = Join-Path $ProjectDir ".godot"
New-Item -ItemType Directory -Force -Path $GodotDir | Out-Null
$ExtensionList = Join-Path $GodotDir "extension_list.cfg"
$Existing = if (Test-Path -LiteralPath $ExtensionList) { Get-Content -LiteralPath $ExtensionList } else { @() }
if ($Existing -contains $ExtensionPath) {
    Write-Host "[kanama] extension already registered"
} else {
    Add-Content -LiteralPath $ExtensionList -Value $ExtensionPath
    Write-Host "[kanama] registered $ExtensionPath"
}

Write-Host "[kanama] next: .\gradlew.bat buildScripts"

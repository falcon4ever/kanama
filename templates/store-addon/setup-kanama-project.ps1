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

function Copy-KanamaFile {
    param(
        [string]$Source,
        [string]$Destination
    )
    if ((Test-Path -LiteralPath $Destination) -and -not $Force) {
        Write-Host "[kanama] keeping existing $($Destination.Substring($ProjectDir.Length + 1))"
        return
    }
    New-Item -ItemType Directory -Force -Path (Split-Path -Parent $Destination) | Out-Null
    Copy-Item -LiteralPath $Source -Destination $Destination -Force
    Write-Host "[kanama] wrote $($Destination.Substring($ProjectDir.Length + 1))"
}

function Copy-KanamaDirectory {
    param(
        [string]$Source,
        [string]$Destination
    )
    if ((Test-Path -LiteralPath $Destination) -and -not $Force) {
        Write-Host "[kanama] keeping existing $($Destination.Substring($ProjectDir.Length + 1))"
        return
    }
    if (Test-Path -LiteralPath $Destination) {
        Remove-Item -LiteralPath $Destination -Recurse -Force
    }
    New-Item -ItemType Directory -Force -Path (Split-Path -Parent $Destination) | Out-Null
    Copy-Item -LiteralPath $Source -Destination $Destination -Recurse -Force
    Write-Host "[kanama] wrote $($Destination.Substring($ProjectDir.Length + 1))"
}

Copy-KanamaFile (Join-Path $TemplateDir "build.gradle.kts") (Join-Path $ProjectDir "build.gradle.kts")
Copy-KanamaFile (Join-Path $TemplateDir "settings.gradle.kts") (Join-Path $ProjectDir "settings.gradle.kts")
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

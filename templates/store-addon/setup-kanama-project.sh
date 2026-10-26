#!/usr/bin/env sh
set -eu

force=0
project_dir=$(pwd)
while [ "$#" -gt 0 ]; do
    case "$1" in
        --force)
            force=1
            ;;
        --project-dir)
            if [ "$#" -lt 2 ]; then
                echo "[kanama] --project-dir needs a directory" >&2
                exit 2
            fi
            project_dir=$2
            shift
            ;;
        -h|--help)
            cat <<'EOF'
usage: addons/kanama/setup-kanama-project.sh [--force] [--project-dir DIR]

Run from a Godot project root after unzipping the Kanama store addon, or name
the project with --project-dir (the Kanama Tools menu item Set Up Kanama
Project does). The script copies the packaged Gradle template when root Gradle
files are missing, adds the starter Kotlin script when kotlin-src has no .kt
file, adds build/.gdignore so Godot skips Gradle's build folder, and registers
the Kanama GDExtension. It prints one "wrote", "created" or "registered" line
for each thing it adds.

Use --force to replace existing template files.
EOF
            exit 0
            ;;
        *)
            echo "[kanama] unknown argument: $1 (see --help)" >&2
            exit 2
            ;;
    esac
    shift
done

template_dir="$project_dir/addons/kanama/templates/release-kit"
extension_path="res://addons/kanama/kanama.gdextension"

if [ ! -d "$template_dir" ]; then
    echo "[kanama] missing template directory: $template_dir" >&2
    echo "[kanama] run this script from the root of the Godot project where the addon was unzipped, or pass --project-dir." >&2
    exit 1
fi

# A settings.gradle.kts of the project's own must resolve the Kanama Gradle plugin, which the template's
# build.gradle.kts applies: its pluginManagement block reads addons/kanama/kanama.version. A file without a
# pluginManagement block gets the template's after its leading @file: and import lines (once: the version
# file's path marks it). A file with a block of its own (`pluginManagement {`, the brace on the same line or
# the next), or a Groovy settings.gradle, is not edited: the setup stops before it changes anything and says
# what to merge. It never writes a settings.gradle.kts beside a settings.gradle.
settings="$project_dir/settings.gradle.kts"
merge_settings=0
if [ -f "$project_dir/settings.gradle" ]; then
    echo "[kanama] the project has a Groovy settings.gradle: merge the pluginManagement block of addons/kanama/templates/release-kit/settings.gradle.kts into it (Add Kanama to an Existing Project, Projects That Already Have a Gradle Build), then run the setup again. Nothing was changed." >&2
    exit 1
fi
if [ -f "$settings" ] && [ "$force" -ne 1 ]; then
    if grep -Fq 'addons/kanama/kanama.version' "$settings"; then
        :
    elif grep -Eq '^[[:space:]]*pluginManagement[[:space:]]*([{]|$)' "$settings"; then
        echo "[kanama] settings.gradle.kts has a pluginManagement block of its own: merge the one in addons/kanama/templates/release-kit/settings.gradle.kts into it (Add Kanama to an Existing Project, Projects That Already Have a Gradle Build), then run the setup again. Nothing was changed." >&2
        exit 1
    else
        merge_settings=1
    fi
fi

copy_file() {
    src="$1"
    dst="$2"
    if [ -e "$dst" ] && [ "$force" -ne 1 ]; then
        echo "[kanama] keeping existing ${dst#"$project_dir"/}"
        return
    fi
    mkdir -p "$(dirname "$dst")"
    cp "$src" "$dst"
    echo "[kanama] wrote ${dst#"$project_dir"/}"
}

copy_dir() {
    src="$1"
    dst="$2"
    if [ -e "$dst" ] && [ "$force" -ne 1 ]; then
        echo "[kanama] keeping existing ${dst#"$project_dir"/}"
        return
    fi
    if [ -e "$dst" ]; then
        rm -rf "$dst"
    fi
    mkdir -p "$(dirname "$dst")"
    cp -R "$src" "$dst"
    echo "[kanama] wrote ${dst#"$project_dir"/}"
}

copy_file "$template_dir/build.gradle.kts" "$project_dir/build.gradle.kts"
if [ "$merge_settings" -eq 1 ]; then
    # Gradle wants pluginManagement before every other statement; only @file: annotations and imports may
    # precede it. The block goes after the file's leading ones (comments and blank lines among them stay),
    # and the rest of the user's lines follow.
    block="$settings.kanama-block"
    merged="$settings.kanama-merge"
    awk '/^pluginManagement \{/ { keep = 1 } keep { print } keep && /^\}/ { exit }' "$template_dir/settings.gradle.kts" >"$block"
    awk -v block_file="$block" '
        BEGIN { while ((getline line < block_file) > 0) block = block line "\n" }
        { lines[NR] = $0 }
        END {
            after = 0
            for (i = 1; i <= NR; i++) {
                text = lines[i]
                sub(/^[ \t]+/, "", text)
                sub(/\r$/, "", text)
                if (text ~ /^(import[ \t]|@file:)/) { after = i; continue }
                if (text == "" || text ~ /^\/\//) continue
                break
            }
            for (i = 1; i <= after; i++) print lines[i]
            if (after > 0) print ""
            printf "%s", block
            print ""
            for (i = after + 1; i <= NR; i++) print lines[i]
        }' "$settings" >"$merged"
    rm -f "$block"
    mv "$merged" "$settings"
    echo "[kanama] merged settings.gradle.kts (added the Kanama pluginManagement block)"
else
    copy_file "$template_dir/settings.gradle.kts" "$settings"
fi
copy_file "$template_dir/gradlew" "$project_dir/gradlew"
copy_file "$template_dir/gradlew.bat" "$project_dir/gradlew.bat"
copy_dir "$template_dir/gradle" "$project_dir/gradle"
if [ ! -d "$project_dir/kotlin-src" ]; then
    mkdir -p "$project_dir/kotlin-src"
    echo "[kanama] created kotlin-src/"
fi
# The starter script only goes into a kotlin-src without Kotlin scripts of its own.
if [ "$force" -eq 1 ] || [ -z "$(find "$project_dir/kotlin-src" -name '*.kt' -print | head -n 1)" ]; then
    copy_file "$template_dir/kotlin-src/HelloScript.kt" "$project_dir/kotlin-src/HelloScript.kt"
else
    echo "[kanama] keeping existing kotlin-src/ (it has Kotlin scripts)"
fi

# Gradle's build folder holds generated .kt files: Godot must not import or export them.
if [ -e "$project_dir/build/.gdignore" ]; then
    echo "[kanama] keeping existing build/.gdignore"
else
    mkdir -p "$project_dir/build"
    : >"$project_dir/build/.gdignore"
    echo "[kanama] wrote build/.gdignore"
fi

if [ -f "$project_dir/gradlew" ]; then
    chmod +x "$project_dir/gradlew"
fi

mkdir -p "$project_dir/.godot"
extension_list="$project_dir/.godot/extension_list.cfg"
if [ -f "$extension_list" ] && grep -Fxq "$extension_path" "$extension_list"; then
    echo "[kanama] extension already registered"
else
    printf '%s\n' "$extension_path" >>"$extension_list"
    echo "[kanama] registered $extension_path"
fi

echo "[kanama] next: ./gradlew buildScripts"

#!/usr/bin/env bash
# One-time setup: point this clone's git hooks at the version-controlled .githooks dir.
set -euo pipefail
ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
git -C "$ROOT_DIR" config core.hooksPath .githooks
echo "Installed: core.hooksPath -> .githooks"
echo "  pre-push now runs ./gradlew ktfmtCheck (blocks unformatted Kotlin, the CI gate)."

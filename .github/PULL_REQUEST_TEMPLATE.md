<!-- Thanks for contributing to Kanama! Fill in the sections and delete these comments. -->

## What & why

<!-- What does this change, and why? Link the issue it addresses. -->
Closes #

## Checklist

- [ ] Ran `scripts/local_ci.sh <godot>` to green — the full gate, not just the in-editor smoke (see `AGENTS.md` → Validation).
- [ ] Up to date with the latest `main` (branch protection also enforces this at merge time).
- [ ] If this touches ABI / memory-ownership code (`src/main/kotlin/binding/`, `processor/`, retain/release, marshalling), I've called it out below so it gets a careful review.
- [ ] Updated docs / CHANGELOG if behavior changed, and bumped any paired constants I touched (see `AGENTS.md` → synchronized invariants).

## Testing

<!-- How did you verify this? Which platforms did you build/run — desktop / Android / iOS? -->

## AI assistance

<!-- Good practice (and required for a future Asset Store listing): disclose if AI tools were used to write this change. -->

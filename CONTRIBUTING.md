# Contributing to Kanama

This guide is for anyone changing Kanama: runtime contributors, demo porters,
and documentation contributors. It holds the rules; the detail is in the
**Develop Kanama** half of the docs, starting at
[Start Here](docs/contributing/index.md).

## Repo Layout

```text
kanama/
├── src/                    # Runtime, binding layer, API wrappers
├── annotations/            # KSP annotation definitions
├── processor/              # KSP processor for script registrars
├── project-scripts/        # Gradle module for user Kotlin scripts
├── bootstrap/              # Native GDExtension bootstrap (C, desktop + Android)
├── gdextension/            # Pinned gdextension_interface.h input
├── android/                # Godot Android plugin (AAR)
├── ios/                    # iOS C shim + headers
├── src/iosMain/            # Kotlin/Native iOS runtime + generated iOS wrappers
├── web-runtime/            # Kotlin/Wasm Web backend, generated wrappers (api/generated/), proxies, JS bridge; library/ is the runtime klib
├── kanama-common-api/      # Web call contract (opcodes, shapes, descriptors) from extension_api.json
├── example_project/        # Godot smoke-test project
├── templates/              # starter, consumer-gradle, release-kit, store-addon
├── scripts/                # Local CI, smoke, audit, and generator scripts
└── docs/                   # MkDocs documentation
```

The companion demo repository is
[falcon4ever/kanama-demos](https://github.com/falcon4ever/kanama-demos). It is
a separate checkout and contains external Godot projects ported to Kanama.

## Before You Start

1. Read [Architecture](docs/contributing/architecture.md) before touching FFI,
   binding, or script lifetime code.
2. Check the open issue, pull request, or maintainer-provided task context for
   the current priority.
3. Establish a local baseline with:

   ```sh
   ./scripts/local_ci.sh /path/to/godot_binary
   ```

## Workflow

- Keep changes focused and reviewable.
- Prefer existing patterns and helper APIs over new abstractions.
- Do not rewrite generated or hand-authored wrapper policy casually.
- Add or update tests, smoke scripts, or audits when a change affects runtime
  behavior, ABI shape, generated wrappers, KSP marshalling, or public API.
- Run the narrowest useful validation while iterating, then run the broader
  check before committing.
- Keep release-facing decisions in documentation, issue threads, pull requests,
  or the changelog instead of ad hoc progress notes.

Typical validation:

```sh
./gradlew build
python3 scripts/validate_godot_api.py --api extension_api.json
./scripts/local_ci.sh /path/to/godot-4.7.2-stable
```

Gate scripts follow two rules (`scripts/README-gates.md`): a check that cannot run prints
`SKIP: <id>: <reason>` and fails under CI unless the id is listed in `KANAMA_ALLOW_SKIP`, and no
failure is swallowed (`|| true`, `2>/dev/null`) without a `# justified:` comment. A new gate ships with
its red run: add a `case(...)` to `scripts/gate_red_runs.py` and a row to that README.
[Gates and Claims](docs/contributing/gates.md) explains the local gate, the red runs and the
checks that read the docs.

Before release-facing changes, validate from isolated clones instead of your
development worktree:

```sh
./scripts/fresh_clone_smoke.sh /path/to/godot-4.7.2-stable
```

For a consumer project:

```sh
./gradlew installAddonJar \
  -PkanamaProjectDir=/path/to/a/godot/project \
  -PkanamaProjectScriptsDir=/path/to/a/godot/project
```

## Formatting

Hand-written Kotlin is formatted with [ktfmt](https://github.com/facebook/ktfmt)
(googleStyle, 2-space indent), configured in the root `build.gradle.kts`. Format
your changes before pushing:

```sh
./gradlew ktfmtFormat   # rewrite files in place
./gradlew ktfmtCheck    # verify formatting without writing (what CI runs)
```

`local_ci.sh` runs `ktfmtCheck` as a gate stage, so an unformatted change fails
CI. To catch that locally in seconds instead of a CI round-trip, install the
pre-push hook once per clone:

```sh
scripts/install-git-hooks.sh   # points core.hooksPath at .githooks
```

The `.githooks/pre-push` hook runs `ktfmtCheck` before every push and refuses
unformatted Kotlin (it never rewrites files; run `./gradlew ktfmtFormat` yourself,
or `git push --no-verify` to bypass in an emergency).

Two things are deliberately **not** formatted:

- Generated Godot API wrappers under `**/net/multigesture/kanama/api/**` — they
  are byte-compared against `scripts/generate_api_wrapper.py` by the drift gate,
  so formatting them would break `check_wrapper_generator.py`.
- `*.gradle.kts` build scripts — the ktfmt Gradle-DSL parser mishandles
  the root `build.gradle.kts` iOS targets.

The knobs live in the `ktfmt { ... }` block in `build.gradle.kts`.

## Wrapper Work

- Every Godot class is generated from `extension_api.json`; public wrappers must stay
  regeneratable. The few hand-written roots and the per-platform table (`PER_PLATFORM_WRAPPERS`
  in `scripts/generate_api_wrapper.py`) are the exception, and the drift gate
  (`check_wrapper_generator.py`) fails on any hand edit of generated output. Keep that table and
  the generator policy audits current when you change this boundary: the full drift gate fails
  otherwise.
- Before adding wrappers, check the coverage report (`python3 scripts/api_wrapper_coverage.py`)
  and the candidate report (`python3 scripts/api_wrapper_candidates.py`).
- Only add methods whose types have a known `ObjectCalls` marshal path. A method that needs a
  new ABI shape gets the exact helper and its audit coverage first.
- Validate method binds after wrapper changes
  (`python3 scripts/validate_godot_api.py --api extension_api.json`), run the ABI, shape and
  signature audits that cover the change, and add a smoke or compile fixture for a
  user-visible API addition.
- Virtual methods (`"is_virtual": true`) are override points, never generated as callable
  wrappers; scripts override them with `@OverrideVirtual`.
- Hand-written code under `api/` is a budget, not a default: `scripts/check_hand_code_budget.py`
  fails on an unlisted hand-written API file and on growth past its line ratchet. A legitimate
  seam, runtime-core or sugar change raises the ratchet with
  `python3 scripts/check_hand_code_budget.py --write --reason "<why>"`. Sugar for a generated
  class goes into the generator's tables, not into a hand class.

The steps, the boundary between generated and hand-written code, and the virtual-return
coverage are in [Wrapper Maintenance](docs/contributing/wrapper-maintenance.md#working-on-wrappers).

## Porting and Demo Integration

Ported demos are integration pressure for real Kanama consumer code. When a
demo breaks after wrapper generation, decide whether the fix belongs in Kanama
or in stale demo code:

- Restore a Kanama convenience when it matches Godot defaults or common API
  usage.
- Fix the demo when generated wrappers are now more precise than an old
  permissive hand wrapper.
- Add guardrails when a mistake could recur in generated output.

The demos repository exposes:

```sh
./gradlew buildAllScripts
./gradlew demoParityAudit
```

## Platform Backends

Android, iOS and Web each have an internals page under `docs/contributing/backends/`
([Android](docs/contributing/backends/android.md), [iOS](docs/contributing/backends/ios.md),
[Web](docs/contributing/backends/web.md)); the support status of each platform is on
[Requirements and Platform Status](docs/reference/version-support.md). Android 13 (API 33) is the
minimum for every Android build, and the mobile runtimes ship inside the kits and the store addon (the iOS
one in the macOS kit and the store addon only). Never widen a
support claim past what the matching device or browser runs validated
([Release Process](docs/contributing/release.md#support-levels)).

## Documented Limitations

A comment that says "blocked on X" is true when written and can be a lie by the
next release: the change that removes X has no reason to grep for the comment.
Such a claim then keeps steering decisions, because it reads as authoritative.
This has happened here — one "missing iOS wrapper types" line outlived its cause
by six weeks and nearly cost a working demo.

**When you land a change that removes a limitation, grep for the comments citing
it** (`rg -i "<the thing you just added>"`), and delete the ones that are now
false. This is the counterpart to the pinned-constant rule in `AGENTS.md`.

When the limitation is machine-checkable, do not rely on the grep habit — write
the claim so `scripts/audit_stale_blockers.py` can re-check it every run:

```
KANAMA-BLOCKED(since:2026-07-13, symbol:OpenXRInterface@web): the Web backend carries no OpenXRInterface
```

The audit is inverted on purpose: it **fails when the blocker no longer holds**,
so the build goes red at the moment the comment becomes false, not whenever
someone next happens to read it. Fixing a failure means deleting the marker and
the claim it guards.

- `since:YYYY-MM-DD` is required. An undated claim rots invisibly.
- Every other token asserts an **absence** — the gap the claim depends on:
  - `symbol:<Name>@<tree>` — no Kotlin declaration or `<Name>.kt` in that tree
    (`desktop`, `ios`, `web`, `processor`, `example`, `repo`).
  - `file:<repo-relative-path-or-glob>` — nothing matches.
  - `webcall:<Class>.<godot_method_name>` — the Web backend contract dispatches
    no such call. Validated against `extension_api.json`, so a typo cannot pass.
  - `task:<id>` — the `kanama-tasks` spec is still open (not in `archive/`).
    That repo is separate, so these tokens are **skipped with a note** when it is
    absent, including in PR CI. Run the audit locally, or pass `--require-tasks`,
    to check them.
- An unresolvable token — unknown kind, unknown tree, a `file:` glob whose
  anchor directory has moved, a Godot method that does not exist — is a loud
  failure, never a silent pass. A marker that can never fire is worse than no
  marker at all.
- Only convert claims that are actually machine-checkable. Do not invent a
  marker that guesses at a symbol name a future fix *might* use: it will fail
  open and go stale exactly like the prose it replaced.

Run it directly with `python3 scripts/audit_stale_blockers.py --list`; it is a
`local_ci.sh` stage. [Gates and Claims](docs/contributing/gates.md#checked-lines-in-the-docs)
covers the markers in the docs and the `kanama-claim` checks beside them.

## Design Rules

- Kotlin public APIs use lower-camel names. Do not add PascalCase aliases.
- `Node` and `GodotObject` wrappers are non-owning.
- `Resource` and `RefCounted` wrappers must follow the established closeable
  ownership policy.
- Do not expose broad `Callable`, `Dictionary`, or generic container APIs
  without explicit policy and tests.
- Do not ignore ABI metadata such as `uint32`, `int32`, enum slots, or typed
  arrays.
- Public API changes need focused validation.
- Prefer automated guardrails over one-off manual knowledge.

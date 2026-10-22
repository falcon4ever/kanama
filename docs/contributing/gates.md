# Gates and Claims

Kanama's checks are scripts under `scripts/`. Most run as stages of
`scripts/local_ci.sh` and in CI; the device and browser runs are described in
[Device and Browser Runs](device-runs.md). The generated
[Gates Index](../reference/generated/gates.md) lists every stage and job, what
it proves and where it runs. This page covers running the local gate, the
checks behind the API conventions, and the source-break gate.

## Local Validation

Run the local CI shortcut with one or more Godot binaries:

```sh
scripts/local_ci.sh /absolute/path/to/godot-4.7.2-stable
```

With several binaries, the Godot stages run once per binary:

```sh
scripts/local_ci.sh \
  /absolute/path/to/godot-4.7.2-stable \
  /absolute/path/to/godot-4.7.2-stable
```

Before tagging a release, run the isolated source-and-demo gate from a clean
temporary clone:

```sh
scripts/fresh_clone_smoke.sh /absolute/path/to/godot-4.7.2-stable
```

For package validation, build the packaged artifacts and smoke them without a
sibling Kanama checkout:

```sh
./gradlew packageDistributions
scripts/package_install_smoke.sh \
  --desktop-kit \
  build/distributions/kanama-desktop-kit-v<version>-<platform>.zip \
  /absolute/path/to/godot-4.7.2-stable
scripts/package_install_smoke.sh \
  --store-addon \
  build/distributions/kanama-store-addon-v<version>.zip \
  /absolute/path/to/godot-4.7.2-stable
```

`local_ci.sh` runs about forty stages (read the script for the list); the
headline ones are:

- API constant validation against `extension_api.json`,
- Gradle jar sync,
- native bootstrap configure/build when CMake is available,
- strict docs build when `mkdocs` is available,
- runtime smoke through `scripts/runtime_smoke.sh`,
- `@Tool` editor execution smoke, and
- hot-reload smoke.

`runtime_smoke.sh` checks the core Kanama runtime path: Godot loads the
GDExtension, starts the JVM, registers the script language and resource
loader, loads Kotlin scripts, and runs the example project far enough to verify
the expected markers.

The Godot pin is `kanamaGodotVersion` in `gradle.properties`;
`scripts/check_godot_version_pin.py` fails `local_ci.sh` when a workflow or
build file drifts from it, and the [Godot Upgrade Runbook](godot-upgrade.md) is
the only process that moves it. The JDK floor is the Gradle toolchain
(`jvmToolchain(25)` in `build.gradle.kts`). Gradle build cache is enabled for
the main Kanama build and the Android plugin build.

Install the docs dependencies with:

```sh
pip install -r docs/requirements.txt
```

### Linux Validation

Use a matching Linux Godot binary from the Godot 4.7.2 stable archive and set
`JAVA_HOME` to JDK 25+:

```sh
cd /path/to/kanama-demos
JAVA_HOME=/path/to/jdk-25 \
XDG_DATA_HOME=/tmp/kanama-godot-state-linux \
XDG_CONFIG_HOME=/tmp/kanama-godot-config-linux \
KANAMA_DESKTOP_SMOKE_LOG_DIR=/tmp/kanama-desktop-smokes-linux \
scripts/desktop_smoke_all.sh /path/to/Godot_v4.7.2-stable_linux.arm64
```

`desktop_smoke_all.sh` defaults both XDG variables to those paths on Linux; set
them explicitly only to relocate the isolated state.

Use the Godot binary for the architecture under test, such as
`Godot_v4.7.2-stable_linux.arm64` or `Godot_v4.7.2-stable_linux.x86_64`. The
demo smoke script uses Godot's OpenGL Compatibility renderer on desktop.
Before refreshing demo addons, rebuild the native bootstrap from a clean
checkout and preflight `libkanama_bootstrap.so` with `file`, `ldd`, and
`readelf`.

### Windows Validation

Launch Godot from an environment with `JAVA_HOME` set to JDK 25+ so Kanama can
load `%JAVA_HOME%\bin\server\jvm.dll`, and use the 4.7 stable **console**
binary so smoke markers reach the terminal.

The native bootstrap needs CMake and MSVC, which are not on the default
PowerShell or Git Bash `PATH`: wrap every Gradle command that builds demos in
`VsDevCmd.bat` (a plain `gradlew.bat buildAllScripts` fails on the nested
native bootstrap otherwise):

```bat
cmd /c "call ""C:\Program Files\Microsoft Visual Studio\2022\Community\Common7\Tools\VsDevCmd.bat"" -arch=x64 -host_arch=x64 && .\gradlew.bat buildAllScripts -PkanamaRoot=C:\path\to\kanama"
```

Point the demo builds at the Godot console binary either with the
`KANAMA_GODOT` environment variable or with `-Pkanama.godot.executable=...`
(the root aggregate tasks forward the property to nested demo builds):

```bat
cmd /c "call ""...\VsDevCmd.bat"" -arch=x64 -host_arch=x64 && .\gradlew.bat importAllGodot -PkanamaRoot=C:\path\to\kanama -Pkanama.godot.executable=C:\path\to\Godot_v4.7.2-stable_win64_console.exe"
```

Run the demo smoke matrix through Git Bash:

```bat
cmd /c "set KANAMA_DESKTOP_SMOKE_LOG_DIR=%TEMP%\kanama-desktop-smokes&& ""C:\Program Files\Git\bin\bash.exe"" scripts/desktop_smoke_all.sh C:\path\to\Godot_v4.7.2-stable_win64_console.exe"
```

The Gradle audit tasks resolve Python as `py`/`python` on Windows (the
`python3` name usually hits the inert Microsoft Store shim). Keep
`core.autocrlf` disabled (the demo repos normalize to LF via
`.gitattributes`); CRLF-rewritten `.tres`/`.tscn` files fail Godot resource
parsing.

## Checked Lines in the Docs

Some doc lines are read by scripts: the `kanama-claim` comments, the
`KANAMA-BLOCKED` comments, the iOS size and Kotlin version sentences in
`reference/version-support.md`, and the `./gradlew` commands on the Get Started
pages. [Writing These Docs](docs-style.md#lines-the-checks-read) lists them and
the checks to run before a docs change.

## Godot Upgrade Risks

Treat these as high-risk when moving to a new Godot version:

- GDExtension struct layouts.
- Script language and script instance virtual method surfaces.
- MethodBind and utility-function hashes.
- Builtin method hashes and constructor indexes.
- `VariantType` enum ids.
- Builtin type sizes such as Variant and GDExtensionCallError.
- Generated header function names such as `classdb_register_extension_class6`.
- `.gdextension` compatibility metadata.
- Engine singleton lifetime policy. Godot 4.7 preview builds warn when
  `Engine.register_singleton` receives a `RefCounted`; Kanama rejects this in
  `Engine.registerSingleton` and audits built-in singletons so singleton
  handles remain engine-owned `Object` instances.

The runbook's gate list is in
[Godot Upgrade Runbook](godot-upgrade.md#gates-never-finish-a-bump-with-any-of-these-red).

## API Convention Checks

Each rule on [API Conventions](../reference/wrapper-conventions.md) is held by
a check. "The drift gate" is `scripts/check_wrapper_generator.py`: the
committed tree is exactly what the generator writes, so every generated file,
including the enum prefix lock and the class-token tables, is held to the
generator.

| Rule | Kept by |
|---|---|
| 1. Names | The drift gate and the signature snapshot ([Source Breaks](#source-breaks)). |
| 2. Properties | `scripts/check_property_coverage.py` (no Godot property on a generated class is dropped silently) and the drift gate. |
| 3. Integers | `scripts/audit_wrapper_signatures.py` and `scripts/audit_wrapper_abi_policy.py --strict` (each call's width matches Godot's), and the drift gate. |
| 4. Decimals | The value types' signatures in `api-snapshots/types.txt` / `web-types.txt`; the `RealStorageWidthTest` unit test (storage, equality, `toString` against Godot's strings, `real_t` arithmetic); the `value_type_storage_smoke` rows of `scripts/runtime_smoke.sh`, which compare an engine round trip, `str(v)`, a few operations and a 256-input randomized parity row (every bit-identical operation, hashed per operation) with GDScript's in the same run; the builtin parity row, which compares every Kotlin-computed and engine-computed value-type member with GDScript over fixed-seed random inputs and over ±0, NaN, ±INF and `.5` ties; and the Web `WebValueTypeStorageTest` (Node). |
| 5. Enums and Bitfields | `scripts/check_typed_enums.py` (native: no enum slot is a raw `Long`, names follow the lock, no top-level name shadows a Kotlin default import or a public Kanama type) and `scripts/check_web_typed_enums.py` (Web). `scripts/runtime_smoke.sh` compares each `@Export*` hint and hint string with its GDScript twin row by row. |
| 6. Nullability | `scripts/check_typed_enums.py` (part b: a required return is non-null and goes through `requireGodotReturn`, every other object return is nullable), `scripts/check_web_typed_enums.py` (the same on Web) and `scripts/audit_generator_object_policy.py` (the parameter decision and the override table). |
| 7. Ownership | The generator functions named in the rule; `refcounted_ownership_problems` in `scripts/audit_generator_shape_policy.py`, which checks every wrapper source for the owned or borrowed choice (returns and `create()` owned, `fromHandle` and typed-Array element callbacks borrowed) and that every returned object Array decodes through `readArrayObjectsOwned`; and `scripts/runtime_smoke.sh`, which asserts reference counts and liveness around the documented cases, and that 10,000 dropped owned `Resource`s are released after a collection (with the fallback off, they stay). |
| 8. Statics and Singletons | The drift gate. |
| 9. Defaults and Overloads | `scripts/check_expect_no_defaults.py` (expect declarations), `api_wrapper_generator_report.py --check` (the "Godot Defaults Not Expressed" list) and the drift gate. |
| 10. Collections | `scripts/audit_wrapper_signatures.py`, `scripts/audit_variant_marshalling_policy.py`, `scripts/type_coverage_audit.py`, `scripts/check_builtin_coverage.py` (reads the standard library with `javap` and fails on a generated `String` method name that collides; `--report` lists every Godot method with its Kotlin member or the reason it has none) and the drift gate. |
| 11. Handles and Factories | `check_factory_helpers`, which every regeneration runs (the drift gate's included): a table key must be a class the generator renders, and no factory may be pasted by hand outside the table. `ClassTokenTableTest` checks that the shared class-token table lists every shared class. |
| 13. Platform Parity | `scripts/check_actual_public_surface.py` keeps an `actual` from adding public members its `expect` lacks; `check_public_signature_changes.py` lists each Web `GodotObject` member that differs from native, with its reason (`WEB_GODOT_OBJECT_DRIFT`), and fails on any it does not list. |

## Source Breaks

A source break is any change that can stop a script from compiling: a removed
or renamed member, class or parameter, a changed type or nullability, a removed
default argument, a member that is no longer `open`. Additions are not breaks,
and neither are the source-compatible changes the check recognises: a parameter
that gains a default, a declaration that becomes `open`, a class that gains a
supertype.

Before 1.0 a break is allowed when it serves the long-term shape of the API,
but never silently. Every break gets an entry in `CHANGELOG.md` under
`## Unreleased` that says what changed and how to migrate, with a line that
starts with the prefix below. The line may be indented, as a nested bullet, and
it **names the owner of every declaration it breaks**: the class or object
(`Node`, or `Node.removeChild`), or `top-level` for a top-level declaration; an
extension may name its receiver class instead. Names match as whole words, so
`Node` does not announce `Node3D`, and a qualified name announces that member
only: `Node.removeChild` does not announce `Node.setProcessMode`.

```text
- **Source break:** `Node.removeChild` takes a nullable `Node?` ...
```

Keep a `## Unreleased` section, even an empty one: with none, no break counts
as announced. The user-facing summary of this policy is the stability
paragraph on [API Conventions](../reference/wrapper-conventions.md).

`scripts/check_public_signature_changes.py` (a `local_ci.sh` stage) enforces
the policy: it compares the public surface with checked-in snapshots in the
repository's `api-snapshots/` directory, one line per declaration, one file per
surface (every source directory is read recursively):

| Snapshot | Sources | What |
|---|---|---|
| `common.txt` | `src/commonMain/.../api` | the shared native tree: generated classes, `GlobalEnums.kt`, the hand roots `GodotObject`, `RefCounted`, `GodotCallable`, `GodotHandle`, the `expect` declarations |
| `types.txt` | `src/commonMain/.../types` | the builtin value types (`Vector3`, `Color`, `Basis`, ...) |
| `jvm.txt`, `ios.txt` | `src/jvmMain/.../api`, `src/iosMain/.../api` | what each native platform declares on its own: the per-platform classes, the `<Class>.jvm.kt` / `<Class>.ios.kt` companions, the `actual`s, and on desktop the name constants (`GD` and `Mathf` are common) |
| `web.txt` | `web-runtime/.../api` | the Web wrappers, generated and hand-written |
| `web-types.txt` | `web-runtime/.../types` | the Web value types |
| `annotations.txt` | `annotations/.../annotations` | the script annotations and their parameters (`@ScriptClass`, `@Export`, `@Rpc`, ...) |

A line records the owner, kind, name, receiver, type parameters and `where`
clauses, parameter names and types, whether each parameter has a default (not
the default's value), the return or property type, supertypes and source
modifiers (`open`, `final`, `override`, ...); annotations, constant values and
bodies are not part of it. Modifiers count whether they stand on the
declaration's own line or on the lines just above it. `private` and `internal`
declarations, and those marked `@Deprecated(level = DeprecationLevel.HIDDEN)`,
are skipped, so hiding a declaration reads as removing it. The snapshots are
read from the Kotlin sources, not from a build, in a few seconds.

**Every public declaration states its type.** A declaration whose type is not
written out fails the gate with its file and line (`fun seed(value: Long) = ...`
must be `fun seed(value: Long): Unit = ...`), except where the type is certain
from the source: `= Unit`, a literal, a companion constant built with its own
class's constructor (`val ZERO = Vector3(...)`), or a Web import-compatibility
extension whose whole body calls the same-named member, whose type the gate
takes over. Today no declaration is left without a visible type.

The gate compares lines, so it also stops changes that still compile, such as a
return that becomes non-null or a parameter that becomes nullable: those are
announced the same way.

| What changed | Gate result | What to do |
|---|---|---|
| nothing | PASS | — |
| signatures added, or a source-compatible change | FAIL: snapshots out of date | `python3 scripts/check_public_signature_changes.py --write`, commit the snapshots |
| a signature removed or changed, its owner not named by a `Source break` line | FAIL naming each change (`was` / `now`) and each unannounced owner | fix the generator, or announce the break in the CHANGELOG |
| a signature removed or changed, its owner named | FAIL: snapshots out of date | `--write`, commit the snapshots with the change |
| a public declaration without a visible type | FAIL naming file and line | write the type |

`--write` refuses to record a removal or change whose owner no `Source break`
line names. The snapshot diff then shows every changed signature in the pull
request that changes it.

# Shared Wrapper Tree: Design Check

Kanama ships two committed generated wrapper trees for the native backends:
desktop/Android under `src/main/kotlin/net/multigesture/kanama/api` and iOS under
`ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api`. The task-21 design
("commonMain unification") proposed one shared tree with an `expect/actual
ObjectCalls` seam; the drift gate landed, the physical move did not. Task 103
re-opened the move. This page records what the code said when the move was
checked (2026-09), so the next attempt starts from facts instead of the design's
assumptions. Nothing here changes behaviour.

## What the two trees are today

| | Desktop/Android tree | iOS tree |
|---|---|---|
| Files | 1,053 (988 generated, 47 hand-shaped, 18 non-API) | 1,029 (1,013 generated, 8 hand-shaped, 3 collision-class files; 9 more collision classes live in `IosGodotApi.kt`) |
| Lines | 302,193 | 181,056, plus 26,677 in the generated `ObjectCallsGenerated.kt` |
| Generated on both platforms | 979 classes | |
| Identical after stripping KDoc and the iOS wildcard import | **0 of 979** | |

Of the 979 shared generated classes, 691 differ only because the iOS emitter
omits `@JvmStatic`. The other 288 differ in **method set**: 1,178 methods across
279 classes are emitted on desktop and skipped on iOS because their ptrcall
helper shape is not audited there (510 distinct `ObjectCalls` helpers, 893 call
sites). That subset is real, deliberate (`IOS_AUDIT_ONLY`), and gated; it is not
drift. But one tree means one method set, so it is the first thing a merge has
to answer.

## Blockers the design named, and what the code says

| Blocker | Verdict |
|---|---|
| Android consuming `expect`/`actual` through the source remap | **Not a blocker.** `prepareAndroidKanamaSources` already copies `kanama-common-api`'s `commonMain` minus the `expect` file and strips `actual ` from the JVM actual. A new root is one more `from(...)` block; the audit's forbidden fragments (`expect class`, `actual class`) need `object` variants added. |
| iOS KDoc stripping | **Not a blocker.** KDoc is owned by `sync_kdoc_from_godot_docs.py` and stripped by the drift gate's `comparable_source`; emitting it once is a flag, not a design problem. |
| iOS collision classes and the two hand-shaped lists | **Not a blocker, but larger than "~55".** The union of `DESKTOP_HANDSHAPED` (47), `IOS_HANDSHAPED` (8), `IOS_HANDWRITTEN_COLLISION_CLASSES` (12) and `IOS_UNSUPPORTED_CLASSES` (2) is 56 classes that need a per-platform file. 34 desktop hand-shaped classes are *generated* on iOS and 5 iOS hand-shaped ones are generated on desktop, so "per-platform variant" often means "generated on one side, hand-written on the other", not two hand files. |
| `Node.createTween()` openness | **Not only openness.** iOS `SceneTree` is a `Node` subclass that overrides `createTween()`; desktop `SceneTree` is an `object` facade with `@JvmStatic createTween()`. `Node` is hand-shaped on desktop and generated (with an iOS custom section) on iOS, so it is a per-platform file either way; making it `open` on both is a one-word alignment once the trees merge. |
| iOS-only `GodotObject : AutoCloseable` and public `unreference()` | **Independent of the tree move.** Both live in per-platform base files (`GodotObject.kt` / `IosGodotApi.kt`), so aligning them is a small standalone change (task 97 R10c). |
| Two-tree assumptions in tooling | **Real but mechanical.** `check_wrapper_generator.py` (`API_DIR`/`IOS_API_DIR`, two exemption sets), `upgrade_godot.sh` step 5, `check_ios_no_silent_stubs.py`, `api_wrapper_coverage.py`; `sync_kdoc_from_godot_docs.py` only knows the desktop tree. |

## Blockers the design did not name

1. **The dependency closure is the whole runtime.** Starting from the generated
   wrappers alone, transitive references reach 1,087 of the 1,096 files under
   `src/main/kotlin` (99 %): `GodotObject` → `ScriptBridge`, `ObjectCalls` →
   `GodotFFI`, hand-shaped wrappers → `KanamaScript`; on iOS 1,050 of 1,056
   (`ObjectCalls` → `KanamaIosRuntime`). `ObjectCalls` and the wrappers also
   reference each other (the desktop facade imports `Node`, `Area2D`, …). A KMP
   module that holds the shared tree *and* its `actual ObjectCalls` is therefore
   not the design's "small reversible module": it is the root runtime and
   `:ios-runtime` folded into one multiplatform build, which the design
   explicitly deferred.
2. **`java.lang.foreign.MemorySegment` is the public handle type.** Every wrapper
   constructor takes it (1,041 files; 628 `MemorySegment.NULL` uses in generated
   bodies). iOS compiles because a shim class in the same package exists in its
   own source set. In a KMP `commonMain` that JDK package cannot be declared or
   `expect`ed; the shared tree would have to switch every public signature to a
   Kanama-owned handle type, a source-visible API change the design did not cost.
3. **Value types are hand-written and diverged per platform.** The 18 shared
   `types/*.kt` names differ in about 2,400 lines (desktop uses Panama `Arena`
   and `ValueLayout` plus method-bind calls; iOS uses `BuiltinCalls`);
   `Vector4i` exists only on desktop; iOS carries a hand-written `Real.kt` where
   desktop generates one at build time. The design listed value types as
   "shared in commonMain" as if they were generated. Under KMP they need their
   own unification first; under a shared source directory they stay per platform.

## Two mechanisms, and what each costs

**(a) KMP `commonMain` + `expect/actual ObjectCalls`, per the design.** Requires:
root and `:ios-runtime` become one KMP module (blocker 1); a Kanama handle type
replaces `MemorySegment` in public signatures (blocker 2); value types are
unified or `expect`ed (blocker 3); the desktop 40k-line hand-written
`ObjectCalls` gains `actual` on ~1,400 members with reconciled parameter names
(`getSingleton(name)` vs `getSingleton(className)` today); the iOS generated
extension helpers become members of the `actual object`. Gains a compiler-checked
contract declaration.

**(b) One shared source directory compiled by each platform module.** The
generator emits one file per class into a shared root; the root JVM module and
`:ios-runtime` add that root as a `srcDir`; Android's copy task adds it as one
more `from(...)`. Each platform compiles the shared files against its own
`ObjectCalls`, `types`, and `MemorySegment`, exactly as Android already does
with the desktop tree. No KMP change, no public type change, per-platform value
types untouched. The "contract" is enforced by each platform's compile (a shared
wrapper calling a helper iOS lacks fails `compileKotlinIosArm64`) rather than by
an `expect` declaration. Loses the declaration; keeps everything else the design
wanted (one tree, no copies, single-tree drift gate, KDoc emitted once).

Either way the 1,178 desktop-only methods need a home before any file is shared.

## What has to be decided before the move starts

1. **Mechanism:** (a) or (b). Recommendation: (b). It is the only shape that
   fits without a runtime-wide build rewrite, and it is the shape Android has
   used successfully for every release.
2. **The 1,178 desktop-only methods (279 classes).** Options: audit the 510
   missing iOS helper shapes first (C shim, Kotlin/Native helper, self-test rows,
   device gate; the honest prerequisite, and the reason task 21's exit criteria
   were never met); or emit them into a per-platform companion file
   (`<Class>.jvm.kt`, extension members over the shared class) so the shared tree
   holds the audited intersection and the desktop-only surface is explicit,
   drift-gated, and shrinks as shapes are audited; or emit fail-loud
   `UnsupportedOperationException` bodies on iOS for un-audited shapes (rejected
   by the design's no-stub rule and `check_ios_no_silent_stubs.py`); or drop
   them from desktop (rejected: public API regression). Recommendation: the
   companion-file split now, the shape audit as its own funded task.
3. **Only under (a):** the public handle type, the value-type unification, and
   the root-to-KMP conversion each need their own decision.

Alignments that need no decision and can land with the move: emit `@JvmStatic`
on iOS too (`kotlin.jvm.JvmStatic` is declared `@OptionalExpectation` in the
common stdlib, like the `@JvmName` the iOS tree already carries; confirm with
`compileKotlinIosArm64` when adopting); one hand-shaped table with platform tags
in `check_wrapper_generator.py`; `Node.createTween()` `open` on both platforms;
iOS `GodotObject` aligned with desktop (not `AutoCloseable`; `RefCounted` is)
and its `unreference()` hidden.

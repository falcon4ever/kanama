# Internal docs — index

What each file in `docs/internals/` is, and whether it's current. Private
handoff notes — device UDIDs, local-path commands, raw logs — are intentionally
kept **outside** this public repo per AGENTS.md. Tactical task specs also live
outside this repo; this directory is the public strategic log.

## Active — current work, keep in sync with code
| File | What it is |
|---|---|
| [ios-backend-roadmap.md](./active/ios-backend-roadmap.md) | **Start here for iOS strategy.** Stable-iOS gates, mobile parity with Android, and the post-parity generator/runtime roadmap. |
| [ios-demo-port-tracker.md](./active/ios-demo-port-tracker.md) | Public-safe status of each iOS demo port, validation pointers, generator gotchas, and demo-relevant fixed bugs. |
| [wrapper-coverage-tracker.md](./active/wrapper-coverage-tracker.md) | Condensed current state for wrapper coverage and remaining generator work. |
| [release-support-decision.md](./release-support-decision.md) | Support-tier recommendation (desktop supported; iOS+Android experimental, device-validated) + required release-gate matrix. Awaiting human sign-off before the wording lands in public docs. |

## Reference — evergreen, read to understand the system
| File | What it is |
|---|---|
| [ios-backend-architecture.md](./reference/ios-backend-architecture.md) | How Kanama runs Kotlin scripts on iOS (C-shim ptrcall, the shared wrapper generator, script model). |
| [ios-backend-handwritten.md](./reference/ios-backend-handwritten.md) | **Generated report** (do not edit; run `scripts/ios_handwritten_report.py`) of hand-written/stub iOS classes. |
| [architecture-review-2026-06.md](./reference/architecture-review-2026-06.md) | Point-in-time architecture/performance review (June 2026). |
| [commonmain-unification-design.md](./reference/commonmain-unification-design.md) | Design record for moving generated wrappers to shared `commonMain` with `expect/actual ObjectCalls`. |
| [wrapper-coverage-roadmap.md](./reference/wrapper-coverage-roadmap.md) | Historical full-wrapper-coverage plan; use the iOS roadmap for current strategy and the tracker for live state. |

## Historical — design records for completed phases (kept for context)
| File | What it is |
|---|---|
| [callable-args-design.md](./historical/callable-args-design.md) | Design record, roadmap task 1.4 (Callable args in generated wrappers). |
| [script-model-unification-design.md](./historical/script-model-unification-design.md) | Design record, roadmap task 3.1 (unified serialized script model). |
| [virtual-method-coverage-design.md](./historical/virtual-method-coverage-design.md) | Design record, roadmap task 5.1 (override any engine virtual). |

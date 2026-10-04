# Gate scripts: no silent fallbacks, a red run for every gate (task 118)

A gate that swallows a failure is worse than no gate: it produces green evidence for a red state. This file is the
record of the task-118 audit of `scripts/`, `.github/workflows/` and the companion demos' `scripts/`, and the one place
that lists how each gate is made to fail. It is written by hand around two generated parts: the audit table (made by
grepping the tree) and the red-run table (made by `scripts/gate_red_runs.py --markdown`). When you add a gate, add its
row to the red-run table (a `case(...)` in `scripts/gate_red_runs.py`) in the same change; when you add a swallowed
failure, give it a `# justified:` comment or make it fail loudly.

Contents: [rules](#rules) · [skips](#skips-print-skip-and-fail-in-ci) · [audit](#1-audit-of-swallowed-failures) ·
[red runs](#2-red-run-per-gate) · [artifact identity](#3-a-gate-proves-the-artifact-it-ran) ·
[web3d flake](#4-the-web3d-flake-it-was-not-a-teardown-race) · [how to re-run](#re-running)

## Rules

1. **No silent skip.** A check that does not run prints `SKIP: <id>: <reason>` (never a bare "skipping X"). Under CI
   (`CI=true`, which GitHub Actions sets) a skip is a failure unless the id is listed in `KANAMA_ALLOW_SKIP`
   (comma-separated). Shell: `source scripts/gate_skip.sh; gate_skip <id> "<reason>" || exit 1`. Python:
   `from gate_skip import skip; skip("<id>", "<reason>")`. An explicit opt-out flag (`--skip-docs`, `--skip-bootstrap`,
   `--skip-web`, `KANAMA_IOS_SKIP_PROBES=1`, `KANAMA_IOS_CRASH_REPORTS=0`) prints a `SKIP:` line too.
2. **No swallowed failure.** Every `|| true`, `2>/dev/null`, `>/dev/null 2>&1`, `set +e`, `continue-on-error`, bare
   `continue` and `except ...: pass` is either gone (the failure is fatal, with the captured output) or carries a
   `# justified: <why the verdict cannot change>` comment. Two idioms are justified as a class and carry no per-site
   comment: `command -v X >/dev/null 2>&1` / `kill -0 PID 2>/dev/null` / `if cmd >/dev/null 2>&1; then` (the exit status
   is the test; only the output is dropped), and a bare `>/dev/null` on a command whose stderr and exit status are kept
   (errexit still fails the script).
3. **A file this gate reads must exist, and the set it scans must not be empty.** A path that moved is a failure, not
   a pass (two gates had been vacuous for months on exactly this: see the audit).
4. **A gate proves the artifact it ran is the one it built** (see section 3).
5. **Every gate has a recorded red run** (section 2), exercised once on the branch.


## Skips print `SKIP:` and fail in CI

Every skip in the gates goes through `gate_skip` (shell) / `skip` (Python). The ids, and who may opt out in CI:

| id | skipped when | CI |
|---|---|---|
| `kdoc-staleness` | `local_ci.sh` finds no Godot `doc/classes` checkout (`GODOT_DOCS`) | listed in `ci.yml` `KANAMA_ALLOW_SKIP` (CI has no Godot source checkout) |
| `task-index` | `audit_claims.sh` finds no `kanama-tasks` checkout (it is local-only) | listed in `ci.yml` |
| `stale-blocker-task-tokens` | `audit_stale_blockers.py` finds no `kanama-tasks` checkout for its `task:<id>` tokens | listed in `ci.yml` |
| `linux-readelf` | `local_ci.sh` on Linux without `readelf` | not listed: fails (ubuntu runners have it) |
| `bootstrap-cmake`, `mkdocs`, `web-node` | `local_ci.sh` without `cmake` / `mkdocs` / `node` | not listed: fail (CI uses `--skip-docs` for the docs job, which is an explicit flag) |
| `jdk-lookup-parity-windows` | `check_jdk_lookup_parity.sh` on a Windows shell (symlink fixture) | not listed: no CI lane runs it there |
| `android-apk-package-id` | `android_smoke.sh` / `android_export_minified.sh` with neither `aapt2` nor `apkanalyzer` under the SDK | device scripts do not run in CI |

`--skip-docs`, `--skip-bootstrap`, `--skip-web`, `KANAMA_IOS_SKIP_PROBES=1` and `KANAMA_IOS_CRASH_REPORTS=0` are explicit
opt-outs and print a `SKIP:` line. Two skips that used to be warnings are now failures with a named opt-out:
`ios_device_run.sh` without `check_exported_scene_properties.gd` in `KANAMA_ROOT` (`KANAMA_IOS_ALLOW_MISSING_SCENE_CHECK=1`)
and the crash-report copy (`KANAMA_IOS_CRASH_REPORTS=0`).

## 1. Audit of swallowed failures

Scope: `scripts/*.sh`, `scripts/**/*.sh`, `scripts/*.py`, `scripts/web/*.py`,
`.github/workflows/*.yml` run steps, and the demos repo's `scripts/*`. Patterns: `|| true`, `|| :`, `2>/dev/null`,
`>/dev/null`, `set +e`, `continue-on-error`, bare `continue` after a failed command, `|| continue`/`|| exit 0`; in
Python every `except` handler (an `except` that raises, records an error or prints a FAIL is *loud* and needs no
comment), `check=False`, `sys.exit(0)`, `errors="ignore"`. A bare `return 0` / `sys.exit(0)` that ends `main()` after all
checks passed is not an error path and is not listed; the early-`return 0` sites were each read (the report-only and
`--write` modes, listed in the "other fixes" table where they mattered). **Not audited:** the 68 `catch` blocks of
`scripts/web/drivers/**/*.mjs` (page polls that end in a deadline error, and browser cleanup): outside the globs of this
task; the drivers' own failures surface as the result envelope's assertions.

**Counts.** Before (origin/main): **214** sites in both repos. After: **192** in this repo and **25** in the demos
repo (217 in all, after 9 sites were fixed and the new code of this task added its own, each justified). Every one has a verdict.
This repo: **155** justified by a `# justified:` comment or a named idiom, **35** Python handlers that are loud (they raise or
record the error), **2** deferred to in-flight task 132 (`runtime_smoke.sh` and `audit_generator_shape_policy.py`, which
this task may not edit; the list is `DEFERRED` in `audit_swallowed_failures.py`: delete it when 132 lands). Demos: **25** justified,
**0** loud. With the skip branches and vacuous gates in 1b, this task fixed **31** defects.

### 1a. Sites in this repo (generated; `scripts/audit_swallowed_failures.py` checks it)

The reason column is the `# justified:` comment in the code (trailing, on a line of the same statement, or in the
comment block directly above it), or a named idiom. `scripts/audit_swallowed_failures.py` (a `local_ci.sh` stage)
fails on a site with neither, and fails when this table is stale (line numbers are ignored in that comparison):
regenerate it with `python3 scripts/audit_swallowed_failures.py --write`.

<!-- audit-table:begin (generated by scripts/audit_swallowed_failures.py --write) -->

| file:line | construct | verdict |
|---|---|---|
| `scripts/android_apk_id.sh:17` | `tool="$(ls -d "$sdk"/build-tools/*/aapt2 2>/dev/null \| sort -V \| tail -n 1)"` | justified: a missing build-tools dir just leaves $tool empty and falls through to apkanalyzer, then to the SKIP below. |
| `scripts/android_apk_id.sh:23` | `tool="$(ls -d "$sdk"/cmdline-tools/*/bin/apkanalyzer 2>/dev/null \| sort -V \| tail -n 1)"` | justified: a missing cmdline-tools dir just leaves $tool empty, which returns 3 (the gate_skip path). |
| `scripts/android_apk_id.sh:56` | `pid="$("$adb" shell pidof -s "$package" 2>/dev/null \| tr -d '\r' \|\| true)"` | justified: a failing or missing pidof prints nothing, and the `ps -A` fallback below is the second opinion. |
| `scripts/android_apk_id.sh:59` | `pid="$("$adb" shell ps -A 2>/dev/null \| tr -d '\r' \| awk -v p="$package" '$NF == p { print $2; exit }' \|\| true` | justified: an adb error leaves $pid empty; the caller retries until its deadline and then fails. |
| `scripts/android_export_minified.sh:132` | `"$ADB_BIN" shell am force-stop "$PACKAGE_NAME" >/dev/null 2>&1 \|\| true` | justified: cleanup after the verdict; the app may already have exited. |
| `scripts/android_export_minified.sh:298` | `"$ADB_BIN" start-server >/dev/null` | justified: stdout only; stderr and the exit status are kept, so errexit still fails the script |
| `scripts/android_export_minified.sh:309` | `"$ADB_BIN" uninstall "$PACKAGE_NAME" >/dev/null 2>&1 \|\| true` | justified: the package may simply not be installed; the plain install on the next line is the check. |
| `scripts/android_export_minified.sh:310` | `"$ADB_BIN" install "$APK_PATH" >/dev/null` | justified: stdout only; stderr and the exit status are kept, so errexit still fails the script |
| `scripts/android_export_minified.sh:314` | `"$ADB_BIN" shell am force-stop "$PACKAGE_NAME" >/dev/null 2>&1 \|\| true` | justified: the app may not be running yet; the launch below and the pid check after it decide. |
| `scripts/android_export_minified.sh:315` | `"$ADB_BIN" shell monkey -p "$PACKAGE_NAME" -c android.intent.category.LAUNCHER 1 >/dev/null` | justified: stdout only; stderr and the exit status are kept, so errexit still fails the script |
| `scripts/android_smoke.sh:126` | `"$ADB_BIN" kill-server >/dev/null 2>&1 \|\| true` | justified: the daemon restart is best effort; the retried command on the next loop turn is the check (after 3 attempts adb_retry returns 1). |
| `scripts/android_smoke.sh:129` | `"$ADB_BIN" start-server >/dev/null 2>&1 \|\| true` | justified: best-effort daemon restart inside adb_retry; the retried command on the next loop turn is the check. |
| `scripts/android_smoke.sh:138` | `"$ADB_BIN" shell am force-stop "$PACKAGE_NAME" >/dev/null 2>&1 \|\| true` | justified: cleanup after the verdict; the app may already have exited. |
| `scripts/android_smoke.sh:289` | `grep -E 'No loader found for resource: res://.*\.kt\|_load path=' "$EXPORT_LOG" \| head -5 >&2 \|\| true` | justified: diagnostics only; the next line exits 1 whatever this prints. |
| `scripts/android_smoke.sh:304` | `adb_retry start-server >/dev/null` | justified: stdout only; stderr and the exit status are kept, so errexit still fails the script |
| `scripts/android_smoke.sh:313` | `"$ADB_BIN" uninstall "$PACKAGE_NAME" >/dev/null 2>&1 \|\| true` | justified: the package may simply not be installed; the retried install on the next line is the check. |
| `scripts/android_smoke.sh:314` | `adb_retry install "$APK_PATH" >/dev/null` | justified: stdout only; stderr and the exit status are kept, so errexit still fails the script |
| `scripts/android_smoke.sh:318` | `adb_retry shell am force-stop "$PACKAGE_NAME" >/dev/null 2>&1 \|\| true` | justified: the app may not be running yet; the launch below and the pid check after it decide. |
| `scripts/android_smoke.sh:324` | `adb_retry shell input keyevent KEYCODE_WAKEUP >/dev/null 2>&1 \|\| true` | justified: best effort; a screen that stayed locked fails the launch/renderer/screenshot checks below. |
| `scripts/android_smoke.sh:326` | `adb_retry shell wm dismiss-keyguard >/dev/null 2>&1 \|\| true` | justified: best effort; a screen that stayed locked fails the launch/renderer/screenshot checks below. |
| `scripts/android_smoke.sh:327` | `adb_retry shell monkey -p "$PACKAGE_NAME" -c android.intent.category.LAUNCHER 1 >/dev/null` | justified: stdout only; stderr and the exit status are kept, so errexit still fails the script |
| `scripts/android_smoke.sh:383` | `adb_retry shell screencap -p /sdcard/kanama_android_smoke.png >/dev/null` | justified: stdout only; stderr and the exit status are kept, so errexit still fails the script |
| `scripts/android_smoke.sh:384` | `adb_retry pull /sdcard/kanama_android_smoke.png "$SCREENSHOT" >/dev/null` | justified: stdout only; stderr and the exit status are kept, so errexit still fails the script |
| `scripts/api_wrapper_generator_report.py:122` | `except ValueError:` | justified: only the path shown in the report text; no verdict reads it |
| `scripts/audit_claims.sh:33` | `common_dir="$(git -C "$ROOT_DIR" rev-parse --git-common-dir 2>/dev/null \|\| true)"` | justified: no git (a tarball) just means the sibling-of-this-checkout default below; a wrong guess surfaces as the task-index SKIP. |
| `scripts/audit_stale_blockers.py:139` | `except (subprocess.CalledProcessError, OSError):` | justified: git is only one way to find the tasks checkout; an absent one is the SKIP path below |
| `scripts/audit_stale_blockers.py:171` | `except UnicodeDecodeError:` | justified: not text (a binary the suffix list does not know), so it carries no marker |
| `scripts/audit_stale_blockers.py:173` | `except OSError as error:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/audit_stale_blockers.py:249` | `except UnicodeDecodeError:` | justified: not text, so it declares no Kotlin symbol |
| `scripts/audit_stale_blockers.py:489` | `except ValueError:` | justified: --list display of the marker age; the audit verdict is already decided |
| `scripts/audit_wrapper_abi_policy.py:84` | `except ValueError as error:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/audit_wrapper_signatures.py:309` | `except ValueError as e:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/check_actual_public_surface.py:123` | `except ValueError:` | justified: only the path printed in a finding; no verdict reads it |
| `scripts/check_actual_public_surface.py:460` | `except ParseError as error:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/check_actual_public_surface.py:481` | `except ParseError as error:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/check_bootstrap_jdk_resolution.sh:23` | `if ! command -v cmake >/dev/null 2>&1; then` | justified: probe; the exit status is the test, only its output is dropped |
| `scripts/check_bootstrap_jdk_resolution.sh:31` | `REAL_JDK="$(/usr/libexec/java_home -v 25 2>/dev/null \|\| true)"` | justified: no JDK 25 leaves REAL_JDK empty, which the "need a real JDK 25+" check just below turns into exit 2. |
| `scripts/check_expect_no_defaults.py:155` | `except (Unparsed, ValueError):` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/check_exported_scene_properties_selftest.sh:42` | `printf '%s\n' "$green" \| grep -E '^\[check_exported_scenes\]' \| sed 's/^/ /' \|\| true` | justified: echoing the evidence lines; the assertions on "$green" follow. |
| `scripts/check_exported_scene_properties_selftest.sh:64` | `printf '%s\n' "$red" \| grep -E '^\[check_exported_scenes\] FAIL' \| sed 's/^/ /' \|\| true` | justified: echoing the evidence lines; the assertion on "$red" follows. |
| `scripts/check_gate_evidence.py:63` | `except ValueError:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/check_gate_evidence.py:76` | `except json.JSONDecodeError as error:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/check_ios_shim_faults.py:276` | `except Exception as exc:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/check_ios_static_dispatch.py:180` | `except ValueError:` | justified: only the path printed in a finding; no verdict reads it |
| `scripts/check_ios_static_dispatch.py:359` | `except (ParseError, OSError) as exc:` | justified: reported as a `parse-error` finding, which fails the gate (never silenced) |
| `scripts/check_jdk_lookup_parity.sh:42` | `REAL_JDK="$(/usr/libexec/java_home -v 25 2>/dev/null \|\| true)"` | justified: no JDK 25 leaves REAL_JDK empty, which the "need a real JDK 25+" check just below turns into exit 2. |
| `scripts/check_jdk_lookup_parity.sh:49` | `"$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >/dev/null` | justified: stdout only; stderr and the exit status are kept, so errexit still fails the script |
| `scripts/check_jdk_lookup_parity.sh:83` | `ln -s "$REAL_JDK/conf" "$dir/conf" 2>/dev/null \|\| true` | justified: conf/ and legal/ are optional JDK parts; a JDK without them is still a valid fixture, and the scenario's libjvm lookup (the thing under test) decides. |
| `scripts/check_jdk_lookup_parity.sh:85` | `ln -s "$REAL_JDK/legal" "$dir/legal" 2>/dev/null \|\| true` | justified: legal/ is an optional JDK part, like conf/ above; the scenario's libjvm lookup decides. |
| `scripts/check_jdk_lookup_parity.sh:100` | `( "$@" >"$log" 2>&1 ) 2>/dev/null \|\| true` | justified: a scenario whose JVM is not found makes Godot abort on purpose; the verdict is the libjvm line read from "$log" afterwards, never this exit status. |
| `scripts/check_jdk_lookup_parity.sh:171` | `grep -E "^\[kanama\]" "$boot_log" \| tail -n 12 \| sed 's/^/ \| /' >&2 \|\| true` | justified: diagnostics on a scenario already counted as a failure above. |
| `scripts/check_native_call_surface.py:157` | `except ValueError:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/check_pt_tag_tables.py:237` | `except ValueError as exc:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/check_pt_tag_tables.py:293` | `except SystemExit as exc:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/check_pt_tag_tables.py:337` | `except ValueError as exc:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/check_pt_tag_tables.py:365` | `except ParseError as exc:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/check_pt_tag_tables.py:367` | `except OSError as exc:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/check_pt_tag_tables.py:375` | `except ValueError:` | justified: only the path printed in a finding; no verdict reads it |
| `scripts/check_public_signature_changes.py:938` | `except ParseError as error:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/check_public_signature_changes.py:1140` | `except ParseError as error:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/check_shell_lint.sh:27` | `if ! command -v shellcheck >/dev/null 2>&1; then` | justified: probe; the exit status is the test, only its output is dropped |
| `scripts/export_game_assemble.sh:199` | `if command -v codesign >/dev/null 2>&1; then` | justified: probe; the exit status is the test, only its output is dropped |
| `scripts/export_game_smoke.sh:133` | `rm -rf "$work_dir" 2>/dev/null \|\| true` | justified: scratch-dir cleanup after the verdict |
| `scripts/export_game_smoke.sh:150` | `"$ROOT_DIR/gradlew" --no-daemon -p "$ROOT_DIR" buildNativeBootstrap syncExampleAddonJar >/dev/null` | justified: stdout only; stderr and the exit status are kept, so errexit still fails the script |
| `scripts/export_game_smoke.sh:153` | `buildNativeBootstrap syncExampleAddonJar jlinkGameRuntime >/dev/null` | justified: stdout only; stderr and the exit status are kept, so errexit still fails the script |
| `scripts/export_game_smoke.sh:298` | `system_root="$(cygpath -u "${SYSTEMROOT:-C:\\Windows}" 2>/dev/null \|\| printf '/c/Windows')"` | justified: the fallback is the standard Windows root; a wrong PATH makes the game fail to launch, which is the verdict. |
| `scripts/export_game_smoke.sh:350` | `export_dir_native="$(cygpath -m "$export_dir" 2>/dev/null \|\| printf '%s' "$export_dir")"` | justified: without cygpath the path is already native; a wrong one fails the launch below. |
| `scripts/gate_red_runs.py:157` | `except BaseException:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/gate_red_runs.py:177` | `except OSError:` | justified: a directory a gate filled meanwhile is the gate's own output; it is not ours to delete |
| `scripts/gate_red_runs.py:196` | `except subprocess.TimeoutExpired:` | justified: reported as exit 124 with the partial output; the case then fails |
| `scripts/gate_red_runs.py:199` | `except ProcessLookupError:` | justified: the group already exited between the timeout and the kill |
| `scripts/gate_red_runs.py:241` | `subprocess.run(["git", "worktree", "remove", "--force", str(tree)], cwd=SRC, capture_output=True, check=False)` | justified: best effort; the rmtree and prune below finish the job |
| `scripts/gate_red_runs.py:243` | `subprocess.run(["git", "worktree", "prune"], cwd=SRC, capture_output=True, check=False)` | justified: housekeeping of a registration that is already gone |
| `scripts/generate_api_wrapper.py:1805` | `except ValueError:` | justified: not a literal of this kind, so no Kotlin default is emitted; the diff-gated generated tree shows any change |
| `scripts/generate_api_wrapper.py:1810` | `except ValueError:` | justified: not a literal of this kind, so no Kotlin default is emitted; the diff-gated generated tree shows any change |
| `scripts/generate_api_wrapper.py:1815` | `except ValueError:` | justified: not a literal of this kind, so no Kotlin default is emitted; the diff-gated generated tree shows any change |
| `scripts/generate_api_wrapper.py:1850` | `except ValueError:` | justified: not a literal of this kind, so no Kotlin default is emitted; the diff-gated generated tree shows any change |
| `scripts/generate_gates_index.py:376` | `except ValueError as error:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/generate_gates_index.py:420` | `check=False,` | justified: a failed probe reads as "not shallow", and the full-history path then derives the dates itself |
| `scripts/generate_gates_index.py:438` | `check=False,` | justified: a failed `git log` leaves the date as a dash, which --check reports as a stale page |
| `scripts/hot_reload_in_process_smoke.sh:18` | `if command -v cygpath >/dev/null 2>&1; then` | justified: probe; the exit status is the test, only its output is dropped |
| `scripts/hot_reload_in_process_smoke.sh:35` | `if [[ -n "$GODOT_PID" ]] && kill -0 "$GODOT_PID" 2>/dev/null; then` | justified: liveness probe (`kill -0`); the exit status is the test |
| `scripts/hot_reload_in_process_smoke.sh:37` | `kill "$GODOT_PID" 2>/dev/null \|\| true` | justified: cleanup of our own child after the verdict; it may already be gone. |
| `scripts/hot_reload_in_process_smoke.sh:39` | `wait "$GODOT_PID" 2>/dev/null \|\| true` | justified: cleanup of our own child after the verdict; it may already be gone. |
| `scripts/hot_reload_in_process_smoke.sh:44` | `if ! "$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >/dev/null; then` | justified: output dropped, the command's exit status is the condition of the `if` |
| `scripts/hot_reload_in_process_smoke.sh:67` | `tail -n 160 "$LOG_FILE" >&2 2>/dev/null \|\| true` | justified: diagnostics only; the next lines print the reason and exit 1 either way. |
| `scripts/hot_reload_in_process_smoke.sh:83` | `if [[ -n "$GODOT_PID" ]] && ! kill -0 "$GODOT_PID" 2>/dev/null; then` | justified: liveness probe (`kill -0`); the exit status is the test |
| `scripts/hot_reload_in_process_smoke.sh:108` | `"$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >/dev/null` | justified: stdout only; stderr and the exit status are kept, so errexit still fails the script |
| `scripts/hot_reload_in_process_smoke.sh:121` | `"$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >/dev/null` | justified: stdout only; stderr and the exit status are kept, so errexit still fails the script |
| `scripts/hot_reload_smoke.sh:19` | `if command -v cygpath >/dev/null 2>&1; then` | justified: probe; the exit status is the test, only its output is dropped |
| `scripts/hot_reload_smoke.sh:39` | `if ! "$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >/dev/null; then` | justified: output dropped, the command's exit status is the condition of the `if` |
| `scripts/hot_reload_smoke.sh:110` | `"$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >/dev/null` | justified: stdout only; stderr and the exit status are kept, so errexit still fails the script |
| `scripts/hot_reload_smoke.sh:116` | `"$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >/dev/null` | justified: stdout only; stderr and the exit status are kept, so errexit still fails the script |
| `scripts/ios_device_gate.sh:305` | `"$bundle_id" >/dev/null 2>&1 \|\| true` | justified: the bundle is usually not installed; the next install and launch are the checks |
| `scripts/ios_device_gate.sh:393` | `git -C "$ROOT_DIR" worktree remove --force "$root" >/dev/null 2>&1 \|\| rm -rf "$root"` | justified: a throwaway worktree; if git cannot remove it the directory is deleted instead. |
| `scripts/ios_device_gate.sh:397` | `git -C "$ROOT_DIR" worktree prune >/dev/null 2>&1 \|\| true` | justified: housekeeping of throwaway worktrees; a stale registration is cleared again before the next build phase. |
| `scripts/ios_device_gate.sh:432` | `run_step "fresh-starter-project" "$OUTPUT_DIR/fresh-starter.log" "${fresh_args[@]}" \|\| true` | justified: run_step records the FAIL itself; keep going so the demo matrix still runs and the final aggregation below decides the overall exit status. |
| `scripts/ios_device_gate.sh:445` | `continue` | justified: this demo was already recorded as a SKIP row (resumed after the --start-at demo). |
| `scripts/ios_device_gate.sh:465` | `"$OUTPUT_DIR/${demo_apps[$i]}" \|\| true` | justified: run_step already recorded the FAIL row |
| `scripts/ios_device_gate.sh:483` | `git -C "$ROOT_DIR" worktree prune >/dev/null 2>&1 \|\| true` | justified: housekeeping; a stale registration would make the `worktree add` below fail loudly. |
| `scripts/ios_device_gate.sh:488` | `git -C "$ROOT_DIR" worktree remove --force "$root" >/dev/null 2>&1 \|\| rm -rf "$root"` | justified: a throwaway worktree; if git cannot remove it the directory is deleted instead. |
| `scripts/ios_device_gate.sh:506` | `continue` | justified: this demo was already recorded as a SKIP row (resumed after the --start-at demo). |
| `scripts/ios_device_gate.sh:515` | `prepare_demo_copy "$DEMOS_ROOT/${demo_dirs[$i]}" "${demo_apps[$i]}" >/dev/null` | justified: stdout only; stderr and the exit status are kept, so errexit still fails the script |
| `scripts/ios_device_gate.sh:553` | `for pid in "${pids[@]}"; do wait "$pid" \|\| true; done` | justified: a build job's subshell status is not the result; success is the .build.ok marker it writes, and phase 2 records FAIL for every demo that has no .build.ok (a job that died writes neither marker). |
| `scripts/ios_device_gate.sh:565` | `continue` | justified: the demo was just recorded as a FAIL row with its build log. |
| `scripts/ios_device_gate.sh:571` | `continue` | justified: the demo was just recorded as a FAIL row (no build result at all). |
| `scripts/ios_device_gate.sh:583` | `"$OUTPUT_DIR/$app" \|\| true` | justified: run_step already recorded the FAIL row |
| `scripts/ios_template_preflight.sh:92` | `if ! command -v unzip >/dev/null 2>&1; then` | justified: probe; the exit status is the test, only its output is dropped |
| `scripts/ios_template_preflight.sh:107` | `rm -rf "$work_dir" 2>/dev/null \|\| true` | justified: scratch-dir cleanup after the verdict |
| `scripts/ios_visual_smoke.sh:356` | `DEVELOPER_DIR="$xcode_developer_dir" xcrun devicectl list devices >&2 \|\| true` | justified: diagnostics before the exit 2 on the next line. |
| `scripts/ios_visual_smoke.sh:2610` | `if rg -q 'OBJECTCALLS SELFTEST:' "$stderr_log" "$stdout_log" 2>/dev/null; then` | justified: a poll loop; the logs may not exist yet. The loader/self-test assertions after the loop decide. |
| `scripts/ios_visual_smoke.sh:2620` | `kill "$launch_pid" >/dev/null 2>&1 \|\| true` | justified: stopping our own console stream after the capture window; it may already have exited. |
| `scripts/ios_visual_smoke.sh:2622` | `wait "$launch_pid" >/dev/null 2>&1 \|\| true` | justified: stopping our own console stream after the capture window; it may already have exited. |
| `scripts/ios_visual_smoke.sh:2670` | `kill "$launch_pid" >/dev/null 2>&1 \|\| true` | justified: stopping our own console stream after the capture window; it may already have exited. |
| `scripts/ios_visual_smoke.sh:2672` | `wait "$launch_pid" >/dev/null 2>&1 \|\| true` | justified: stopping our own console stream after the capture window; it may already have exited. |
| `scripts/ios_visual_smoke.sh:2708` | `rg 'SELFTEST FAIL:\|SELFTEST( MATRIX)?: [0-9]+ passed, [0-9]+ failed' "$stderr_log" "$stdout_log" >&2 \|\| true` | justified: printing the evidence; the exit 1 on the next line is the verdict. |
| `scripts/ios_visual_smoke.sh:2755` | `rg 'SELFTEST FAIL:\|SELFTEST( MATRIX)?: [0-9]+ passed, [0-9]+ failed' "$stderr_log" "$stdout_log" >&2 \|\| true` | justified: printing the evidence; the exit 1 on the next line is the verdict. |
| `scripts/ios_visual_smoke.sh:2839` | `rg -h 'project script method call.*method=add_bunny' "$stderr_log" "$stdout_log" \|\| true` | justified: no match exits 1 and the count is 0; the `-lt 25` check right after is the verdict. |
| `scripts/ios_visual_smoke.sh:2977` | `DEVELOPER_DIR="$xcode_developer_dir" xcrun simctl terminate "$device_udid" "$bundle_id" >/dev/null 2>&1 \|\| tru` | justified: closing the app after the verdict; it may already have exited. |
| `scripts/ios_visual_smoke.sh:2979` | `DEVELOPER_DIR="$xcode_developer_dir" xcrun simctl launch "$device_udid" "$bundle_id" >/dev/null` | justified: stdout only; stderr and the exit status are kept, so errexit still fails the script |
| `scripts/local_ci.sh:48` | `write_timings_json FAIL \|\| true` | justified: this runs inside the failure banner; the run is already red and the exit code is the original one. |
| `scripts/local_ci.sh:207` | `if command -v cygpath >/dev/null 2>&1; then` | justified: probe; the exit status is the test, only its output is dropped |
| `scripts/local_ci.sh:659` | `ldd "$linux_native" >&2 \|\| true` | justified: diagnostics on a path that already exits 1 just below |
| `scripts/local_ci.sh:662` | `if command -v readelf >/dev/null 2>&1; then` | justified: probe; the exit status is the test, only its output is dropped |
| `scripts/local_ci.sh:664` | `readelf -d "$linux_native" >/dev/null` | justified: stdout only; stderr and the exit status are kept, so errexit still fails the script |
| `scripts/local_ci.sh:702` | `if command -v cmake >/dev/null 2>&1; then` | justified: probe; the exit status is the test, only its output is dropped |
| `scripts/local_ci.sh:718` | `if command -v mkdocs >/dev/null 2>&1; then` | justified: probe; the exit status is the test, only its output is dropped |
| `scripts/local_ci.sh:731` | `if command -v node >/dev/null 2>&1; then` | justified: probe; the exit status is the test, only its output is dropped |
| `scripts/package_install_smoke.sh:114` | `rm -rf "$work_dir" 2>/dev/null \|\| true` | justified: scratch-dir cleanup after the verdict |
| `scripts/package_install_smoke.sh:130` | `if command -v xattr >/dev/null 2>&1; then` | justified: probe; the exit status is the test, only its output is dropped |
| `scripts/package_install_smoke.sh:132` | `xattr -dr com.apple.quarantine "$project_dir" 2>/dev/null \|\| true` | justified: clearing quarantine is best effort; a quarantined binary that cannot load fails the Godot launch below. |
| `scripts/package_install_smoke.sh:395` | `if command -v cygpath >/dev/null 2>&1; then` | justified: probe; the exit status is the test, only its output is dropped |
| `scripts/record_gate_evidence.py:40` | `except (OSError, subprocess.CalledProcessError) as error:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/runtime_smoke.sh:20` | `if command -v cygpath >/dev/null 2>&1; then` | justified: probe; the exit status is the test, only its output is dropped |
| `scripts/runtime_smoke.sh:27` | `"$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >/dev/null` | justified: stdout only; stderr and the exit status are kept, so errexit still fails the script |
| `scripts/runtime_smoke.sh:41` | `cat "$GLOBAL_CLASS_CACHE" 2>/dev/null \|\| true` | deferred: in-flight task 132 |
| `scripts/runtime_smoke.sh:312` | `freed_errors="$(grep -c '^SCRIPT ERROR: .*previously freed instance' "$LOG_FILE" \|\| true)"` | deferred: in-flight task 132 |
| `scripts/scene_connection_lint.py:103` | `except OSError:` | justified: an unreadable script registers no methods, so every connection to it is reported missing |
| `scripts/tool_smoke.sh:18` | `if command -v cygpath >/dev/null 2>&1; then` | justified: probe; the exit status is the test, only its output is dropped |
| `scripts/tool_smoke.sh:25` | `"$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >/dev/null` | justified: stdout only; stderr and the exit status are kept, so errexit still fails the script |
| `scripts/tool_smoke.sh:115` | `if command -v cygpath >/dev/null 2>&1; then` | justified: probe; the exit status is the test, only its output is dropped |
| `scripts/upgrade_godot.sh:96` | `BIN_VERSION="$("$GODOT_BIN" --version 2>/dev/null \| grep -E '^[0-9]' \| tail -n 1)"` | justified: a binary that prints no version leaves BIN_VERSION empty, which fails the pin comparison just below. |
| `scripts/upgrade_godot.sh:129` | `DOCS_VERSION_PY="$(cd "$GODOT_DOCS_DIR/../.." 2>/dev/null && pwd)/version.py"` | justified: an unreachable docs dir yields a version.py path that does not exist, which fails loudly just below. |
| `scripts/upgrade_godot.sh:168` | `unexpected="$(printf '%s\n' "$STATUS_BEFORE" \| grep -v '^??' \| grep -vE "$allowed_dirty" \| grep -v '^$' \|\| tru` | justified: grep -v exits 1 when it filters every line out, which is the clean case (no unexpected change). |
| `scripts/upgrade_godot.sh:326` | `diff <(printf '%s\n' "$STATUS_BEFORE") <(printf '%s\n' "$STATUS_AFTER") >&2 \|\| true` | justified: diff exits 1 on a difference, which is the very thing being printed; the next line exits 1. |
| `scripts/validate_godot_api.py:230` | `except ValueError as e:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/validate_godot_api.py:252` | `except ValueError as e:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/validate_godot_api.py:278` | `except ValueError as e:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/web/check_browser_floor.py:110` | `except FloorError as error:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/web/check_browser_floor.py:113` | `except (OSError, json.JSONDecodeError) as error:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/web/check_budgets.py:203` | `except (OSError, json.JSONDecodeError) as error:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/web/check_budgets.py:228` | `except BudgetError as error:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/web/coverage_report.py:48` | `except json.JSONDecodeError as error:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/web/demos.sh:142` | `[[ "$1" == "spike" ]] \|\| kanama_web_demo_project_dir "$1" >/dev/null 2>&1` | justified: a probe; the registry lookup's exit status is the answer (unknown demo), its stdout/stderr are noise. |
| `scripts/web/result_schema.py:235` | `except (OSError, json.JSONDecodeError) as error:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/web/result_schema.py:446` | `except (OSError, json.JSONDecodeError) as error:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/web/result_schema.py:452` | `except SchemaError as error:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/web/result_schema.py:471` | `except (OSError, json.JSONDecodeError) as error:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/web/scaffold_selftest.sh:64` | `note "stderr: $(tail -n3 "$case_dir/stderr.log" 2>/dev/null \|\| true)"` | justified: diagnostics for a case already counted as failed by bad() above. |
| `scripts/web/scaffold_selftest.sh:69` | `if pgrep -f "serve_export.py $export_dir" >/dev/null 2>&1; then` | justified: output dropped, the command's exit status is the condition of the `if` |
| `scripts/web/scaffold_selftest.sh:72` | `if pgrep -f "fake_driver.py.*$result" >/dev/null 2>&1; then` | justified: output dropped, the command's exit status is the condition of the `if` |
| `scripts/web/scaffold_selftest.sh:103` | `&& python3 "$ROOT_DIR/scripts/web/result_schema.py" "$WORK/success/result.json" >/dev/null; then` | justified: output dropped, the command's exit status is the condition of the `if` |
| `scripts/web/scaffold_selftest.sh:112` | `>/dev/null 2>&1 \|\| usage_status=$?` | justified: the status is captured and asserted on the next lines |
| `scripts/web/serve_export.py:83` | `except OSError:` | justified: no route means no LAN address to print; the server itself still binds and serves |
| `scripts/web/serve_export.py:113` | `except FileNotFoundError:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/web/serve_export.py:120` | `except subprocess.CalledProcessError as error:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/web/serve_export.py:206` | `except KeyboardInterrupt:` | justified: Ctrl-C is how an operator stops the dev server; exit 0 is right |
| `scripts/web/visibility_probe.py:147` | `except subprocess.TimeoutExpired:` | justified: the browser ignored terminate(); escalating to kill() IS the handling |
| `scripts/web/visibility_probe.py:164` | `except (OSError, ValueError) as error:` | justified: the handler reports (raises, records an error or prints a FAIL), so the failure reaches the verdict |
| `scripts/web/visibility_probe.py:182` | `except OSError:` | justified: the browser-output tail is diagnostics only; this branch returns 2 (INCONCLUSIVE) regardless |
| `scripts/web_ci_matrix.sh:274` | `continue` | justified: the demo was just recorded as a failed cell (`record_unrun_demo`) and FAILED=1 is set. |
| `scripts/web_ci_matrix.sh:283` | `continue` | justified: the demo was just recorded as a failed cell (`record_unrun_demo`) and FAILED=1 is set. |
| `scripts/web_export_smoke.sh:123` | `if [[ -n "$DRIVER_PID" ]] && kill -0 "$DRIVER_PID" 2>/dev/null; then` | justified: liveness probe (`kill -0`); the exit status is the test |
| `scripts/web_export_smoke.sh:124` | `kill -TERM -- "-$DRIVER_PID" 2>/dev/null \|\| kill -TERM "$DRIVER_PID" 2>/dev/null \|\| true` | justified: cleanup after the verdict; the process may already be gone |
| `scripts/web_export_smoke.sh:126` | `kill -0 "$DRIVER_PID" 2>/dev/null \|\| break` | justified: liveness probe (`kill -0`); the exit status is the test |
| `scripts/web_export_smoke.sh:129` | `kill -KILL -- "-$DRIVER_PID" 2>/dev/null \|\| kill -KILL "$DRIVER_PID" 2>/dev/null \|\| true` | justified: cleanup after the verdict; the process may already be gone |
| `scripts/web_export_smoke.sh:131` | `if [[ -n "$SERVER_PID" ]] && kill -0 "$SERVER_PID" 2>/dev/null; then` | justified: liveness probe (`kill -0`); the exit status is the test |
| `scripts/web_export_smoke.sh:132` | `kill -TERM "$SERVER_PID" 2>/dev/null \|\| true` | justified: cleanup after the verdict; the process may already be gone |
| `scripts/web_export_smoke.sh:133` | `wait "$SERVER_PID" 2>/dev/null \|\| true` | justified: cleanup after the verdict; the process may already be gone |
| `scripts/web_export_smoke.sh:157` | `if ! kill -0 "$SERVER_PID" 2>/dev/null; then` | justified: liveness probe (`kill -0`); the exit status is the test |
| `scripts/web_export_smoke.sh:188` | `if ! command -v setsid >/dev/null 2>&1; then` | justified: probe; the exit status is the test, only its output is dropped |
| `scripts/web_export_smoke.sh:209` | `if ! kill -0 "$DRIVER_PID" 2>/dev/null; then` | justified: liveness probe (`kill -0`); the exit status is the test |
| `scripts/web_export_smoke.sh:215` | `kill -TERM -- "-$DRIVER_PID" 2>/dev/null \|\| kill -TERM "$DRIVER_PID" 2>/dev/null \|\| true` | justified: killing a driver that timed out; the `fail` just below is the verdict |
| `scripts/web_export_smoke.sh:217` | `kill -KILL -- "-$DRIVER_PID" 2>/dev/null \|\| kill -KILL "$DRIVER_PID" 2>/dev/null \|\| true` | justified: killing a driver that timed out; the `fail` just below is the verdict |
| `scripts/web_fresh_checkout_smoke.sh:97` | `demo_project_dir "$demo" >/dev/null \|\| die "unknown --demo: $demo (expected one of: ${ALL_DEMOS[*]} \| all)"` | justified: not a swallow; `\|\| die` fails the script with a message |
| `scripts/web_fresh_checkout_smoke.sh:216` | `git -C "$DEMOS_DIR" status --short >&2 \|\| true` | justified: diagnostics; FAILED=1 above already decided |
| `scripts/web_hand_metric.py:99` | `except OSError:` | justified: a size metric for a report, not a gate; an unreadable file counts as 0 lines |
| `.github/workflows/package.yml:313` | `chmod +x "$templates/${{ matrix.export_template }}" \|\| true` | justified: a template that is not executable is not an error (Windows); the smoke below fails if it cannot run. |
| `.github/workflows/package.yml:330` | `chmod +x "$godot_bin" \|\| true` | justified: if the editor binary is not executable the smoke's own executable check on it fails the step. |
| `.github/workflows/package.yml:420` | `gh release view "$GITHUB_REF_NAME" >/dev/null 2>&1 \|\| \` | justified: `gh release view` is the existence probe; failing it means "create the release" on the next line. |
| `.github/workflows/web.yml:228` | `continue-on-error: true` | justified: an experiment, not a gate (see the comment above the job); its failure must never block a merge. |
| `.github/workflows/web.yml:281` | `"$godot_bin" --headless --path scripts/fixtures/godot-visibility-probe --import \|\| true` | justified: the import only warms .godot/; the export on the next line fails loudly if it did not take. |
| `.github/workflows/web.yml:296` | `set +e` | justified: `set +e` only reads the probe's exit status into $status, which the case below prints; the job is an experiment. |

<!-- audit-table:end -->

### 1a'. Sites in the demos repo (generated with `--root ../kanama-demos --label demos:`; not checked by CI)

| file:line | construct | verdict |
|---|---|---|
| `demos:scripts/desktop_smoke_all.sh:19` | `if command -v cygpath >/dev/null 2>&1; then` | justified: probe; the exit status is the test, only its output is dropped |
| `demos:scripts/desktop_smoke_all.sh:63` | `grep -En "$error_pattern" "$log_file" >&2 \|\| true` | justified: diagnostics; the exit 1 on the next line is the verdict. |
| `demos:scripts/desktop_smoke_all.sh:94` | `leaks="$(grep -F 'Leaked instance: ' "$log_file" \|\| true)"` | justified: grep exits 1 when there are no leaked instances, which is the clean case. |
| `demos:scripts/desktop_smoke_all.sh:147` | `if command -v cygpath >/dev/null 2>&1; then` | justified: probe; the exit status is the test, only its output is dropped |
| `demos:scripts/desktop_smoke_all.sh:164` | `if command -v timeout >/dev/null 2>&1; then` | justified: probe; the exit status is the test, only its output is dropped |
| `demos:scripts/desktop_smoke_all.sh:181` | `if command -v timeout >/dev/null 2>&1; then` | justified: probe; the exit status is the test, only its output is dropped |
| `demos:scripts/ios_device_run.sh:131` | `fail_lines="$(grep -E 'SELFTEST FAIL:' "$log" 2>/dev/null \|\| true)"` | justified: grep exits 1 when nothing matches, which is the clean case; the checks below read the captured text. |
| `demos:scripts/ios_device_run.sh:138` | `bad_summaries="$(grep -E 'SELFTEST( MATRIX)?: [0-9]+ passed, [1-9][0-9]* failed' "$log" 2>/dev/null \|\| true)"` | justified: grep exits 1 when no summary has a failed count, which is the clean case. |
| `demos:scripts/ios_device_run.sh:145` | `any="$(grep -c -E "$SELFTEST_ANY_RE" "$log" 2>/dev/null \|\| true)"` | justified: grep -c prints 0 and exits 1 when nothing matches, which is the answer, not an error. |
| `demos:scripts/ios_device_run.sh:148` | `if ! grep -q -E 'PTRCALL SELFTEST MATRIX: [0-9]+ passed, 0 failed' "$log" 2>/dev/null; then` | justified: the grep status is the test |
| `demos:scripts/ios_device_run.sh:152` | `if ! grep -q -E 'OBJECTCALLS SELFTEST: [0-9]+ passed, 0 failed' "$log" 2>/dev/null; then` | justified: the grep status is the test |
| `demos:scripts/ios_device_run.sh:167` | `first="$(awk -v m="$FAULT_LINE_MARKER" 'index($0, m) { print; exit }' "$log" 2>/dev/null \|\| true)"` | justified: awk exits non-zero only when the log is unreadable, and the callers have just written or checked it. |
| `demos:scripts/ios_device_run.sh:177` | `ran="$(grep -c -F "$SELFTEST_RAN_MARKER" "$log" 2>/dev/null \|\| true)"` | justified: grep -c prints 0 and exits 1 when nothing matches, which is the answer, not an error. |
| `demos:scripts/ios_device_run.sh:179` | `summaries="$(grep -c -E 'OBJECTCALLS SELFTEST.*faults=' "$log" 2>/dev/null \|\| true)"` | justified: grep -c prints 0 and exits 1 when nothing matches, which is the answer, not an error. |
| `demos:scripts/ios_device_run.sh:199` | `done < <(grep -E 'OBJECTCALLS SELFTEST.*faults=' "$log" 2>/dev/null \|\| true)` | justified: no summary line is the empty loop; the "ran but printed no summary" case was already failed above. |
| `demos:scripts/ios_device_run.sh:262` | `--timeout 30 --json-output "$list_file" >/dev/null 2>&1 && [[ -s "$list_file" ]]; then` | justified: cleanup after the verdict; falls back to the table listing below |
| `demos:scripts/ios_device_run.sh:266` | `--timeout 30 2>/dev/null \|\| true` | justified: cleanup after the verdict; an empty listing means nothing to terminate |
| `demos:scripts/ios_device_run.sh:270` | `pids="$(printf '%s\n' "$listing" \| parse_app_pids "$APP_NAME" \|\| true)"` | justified: cleanup after the verdict; an empty result means nothing to terminate |
| `demos:scripts/ios_device_run.sh:277` | `--pid "$pid" --timeout 30 >/dev/null 2>&1; then` | justified: cleanup after the verdict; failure prints a WARNING in the else branch |
| `demos:scripts/ios_device_run.sh:286` | `pids="$(printf '%s\n' "$listing" \| parse_app_pids "$APP_NAME" \|\| true)"` | justified: cleanup after the verdict; an empty result means nothing to terminate |
| `demos:scripts/ios_device_run.sh:289` | `--pid "$pid" --kill --timeout 30 >/dev/null 2>&1; then` | justified: cleanup after the verdict; failure prints a WARNING in the else branch |
| `demos:scripts/ios_device_run.sh:529` | `grep -B1 'ResourceFormatLoader\._load bound kotlinClass= ' "$EXPORT_LOG" \| grep '_load path=' >&2 \|\| true` | justified: diagnostics; the exit 1 on the next lines is the verdict. |
| `demos:scripts/ios_device_run.sh:704` | `if grep -q -E "$CONSOLE_FAIL_PATTERN" "$CONSOLE_LOG" 2>/dev/null; then` | justified: output dropped, the command's exit status is the condition of the `if` |
| `demos:scripts/ios_device_run.sh:707` | `if ! kill -0 "$console_pid" 2>/dev/null; then` | justified: liveness probe (`kill -0`); the exit status is the test |
| `demos:scripts/ios_device_run.sh:710` | `if [[ -z "$launched_at" ]] && grep -q '\[kanama\]\[ios\]' "$CONSOLE_LOG" 2>/dev/null; then` | justified: poll of the log being written; the verdict is read from the window copy afterwards |
| `demos:scripts/ios_device_run.sh:720` | `if [[ "$smoke_active" -eq 1 ]] && grep -q -E "$SMOKE_COMPLETE_PATTERN" "$CONSOLE_LOG" 2>/dev/null; then` | justified: only decides whether to wait 3 s more before the verdict is taken from the window copy |
| `demos:scripts/ios_device_run.sh:731` | `kill "$console_pid" >/dev/null 2>&1 \|\| true` | justified: stopping our own console stream after the window; the verdict was copied to $VERDICT_LOG above. |
| `demos:scripts/ios_device_run.sh:733` | `wait "$console_pid" >/dev/null 2>&1 \|\| true` | justified: reaping our own console stream; its exit status says nothing about the app. |
| `demos:scripts/ios_device_run.sh:737` | `terminate_demo_app \|\| true` | justified: terminate_demo_app is documented never to fail the run (it only closes the app after the verdict). |
| `demos:scripts/ios_device_run.sh:777` | `cp "$OUTPUT_DIR/crashes.after/$ips" "$OUTPUT_DIR/crashes/" 2>/dev/null \|\| true` | justified: keeping a copy of the report for humans; the step exits 1 below because of the NEW report either way. |

### 1a''. The sites this task fixed (before-state locations on origin/main; the code no longer has them)

| file:line | construct | verdict |
|---|---|---|
| `scripts/android_export_minified.sh:167` | `"$GODOT_BIN" --headless --path "$DEMO_DIR" --install-android-build-template --quit >/dev/null 2>&1 \|\| true` | fixed: the `--install-android-build-template` output is kept in `<apk>.install-template.log` and shown when the command exits non-zero. Its status is still not the verdict (in 4.7.2 it installs nothing on its own, task 116): the `build.gradle` check right after is, and installs the template by hand |
| `scripts/android_export_minified.sh:284` | `if ! "$ADB_BIN" install -r "$APK_PATH" >/dev/null 2>&1; then` | fixed: the first `adb install -r` output is kept in `<apk>.install.log` and printed before the stale-package uninstall and retry (it was thrown away) |
| `scripts/android_smoke.sh:192` | `--quit >/dev/null 2>&1 \|\| true` | fixed: same as `android_export_minified.sh:167` |
| `scripts/android_smoke.sh:286` | `if ! "$ADB_BIN" install -r "$APK_PATH" >/dev/null 2>&1; then` | fixed: same as `android_export_minified.sh:284` |
| `scripts/audit_stale_blockers.py:169` | `except (UnicodeDecodeError, OSError):` | fixed: an `OSError` on a tracked file is now an error entry (the audit did not look at that file); only `UnicodeDecodeError` (not text) is skipped, with a `# justified:` comment |
| `scripts/audit_stale_blockers.py:243` | `except (UnicodeDecodeError, OSError):` | fixed: an `OSError` now propagates; only `UnicodeDecodeError` is skipped |
| `scripts/hot_reload_in_process_smoke.sh:39` | `"$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >/dev/null \|\| true` | fixed: a failed re-sync of the example addon jar (which leaves the smoke's mutated scripts jar behind for the NEXT gate) now prints a WARNING naming the fix instead of nothing |
| `scripts/hot_reload_smoke.sh:36` | `"$ROOT_DIR/gradlew" -p "$ROOT_DIR" syncExampleAddonJar >/dev/null \|\| true` | fixed: same as `hot_reload_in_process_smoke.sh:39` |
| `demos:scripts/ios_device_run.sh:517` | `--domain-type systemCrashLogs --source . --destination "$dest" >/dev/null 2>&1; then` | fixed: a failed crash-log copy now fails the step with devicectl's own output (it was a WARNING and the run went on with its crash-report check switched off); `KANAMA_IOS_CRASH_REPORTS=0` opts out and prints `SKIP:` |

### 1b. Other fixes the audit found (no grep hit: a skip branch, a vacuous path, a missing-file pass)

| # | where | what was silent | fix |
|---|---|---|---|
| 1 | `scripts/check_gdextension_modernization.py` | The desktop leg scanned `src/main`, which stopped existing with the KMP move (task 104 step 3): "bound: desktop/Android=0". The deprecated-function and convergence checks were vacuous for desktop. | Scans `src/jvmMain` (36 functions bound, green) and fails when a backend binds nothing. |
| 2 | `scripts/validate_godot_api.py` | `validate_kotlin_hashes` read only `src/jvmMain`; the shared wrapper tree under `src/commonMain` (nearly every MethodBind hash) was never checked after task 117 P4'. A wrong hash there passed. | Scans jvmMain, commonMain and iosMain (one named exception: the iOS fault probe's deliberately wrong hash); fails on an empty file set. Green on the real tree. |
| 3 | `scripts/audit_builtin_storage_sizes.py` | `if not IOS_SHIM.exists(): return []` made the iOS shim audit a no-op if the file moved. | Returns an error naming the missing file. |
| 4 | `scripts/check_native_call_surface.py` | A downcall whose argument list `balanced()` could not read was skipped (`except ValueError: continue`). | Recorded as an unresolvable site, which the gate reports. |
| 5 | `scripts/check_unapplied_annotations.py` | A missing explicit scan root printed `SKIP missing root` and the scan went on. | Exit 2. |
| 6 | `scripts/audit_stale_blockers.py` | `task:<id>` tokens were skipped with a note when `kanama-tasks` was absent. | `SKIP: stale-blocker-task-tokens`, fatal in CI unless allowed. |
| 7 | `scripts/audit_claims.sh` | `require_script` turned a deleted/renamed check script into a `SKIPPED` line and exit 0. | A missing script is a FAIL. |
| 8 | `scripts/audit_claims.sh` | The `task-index` skip was green everywhere. | `gate_skip task-index` (fatal in CI unless allowed). |
| 9 | `scripts/local_ci.sh` | The KDoc staleness stage echoed `skip:` and passed on CI; `readelf`, `cmake`, `mkdocs`, `node` missing were `echo ...; skipping` and green. | `gate_skip` for each (see Skips). |
| 10 | `scripts/local_ci.sh` | The web driver `node --check` loop was `[[ -e "$driver" ]] && node --check ...`: a glob matching nothing passed. | Fails when a driver glob matches nothing and prints the count checked. |
| 11 | `scripts/check_jdk_lookup_parity.sh` | Printed `skipped on Windows` and exited 0. | `gate_skip jdk-lookup-parity-windows`. |
| 12 | `scripts/android_smoke.sh`, `android_export_minified.sh` | `pidof ... \|\| true` then `logcat -d` of the whole device when the app was not running: a crashed app's startup lines passed the positive checks (a native crash prints no `FATAL EXCEPTION`). | An empty pid after the launch wait fails the run with the logcat tail. **Device run pending.** |
| 13 | `scripts/ios_device_gate.sh` | "no `FAIL` row" was the pass condition: a step that never ran left no row and the matrix printed PASS (and ledgered it). | The row count must equal the planned step count. |
| 14 | demos `scripts/ios_device_run.sh` | A `KANAMA_ROOT` without `check_exported_scene_properties.gd` printed a WARNING and built and launched an app whose scenes were never compared. | Fails unless `KANAMA_IOS_ALLOW_MISSING_SCENE_CHECK=1` (prints `SKIP:`). |
| 15 | demos `scripts/ios_device_run.sh` | The device crash-log copy failing disabled the crash-report check with a WARNING. | Fails with devicectl's output unless `KANAMA_IOS_CRASH_REPORTS=0` (prints `SKIP:`). |
| 16 | demos `scripts/desktop_smoke_all.sh` | `assert_no_hard_log_errors` and `assert_no_per_tick_leaks` returned (check skipped) when the log file was missing. | A missing log fails the run. |
| 17 | demos `scripts/demo_parity_audit.py` | An empty file set printed `PASS checked 0`. | Exit 2. |
| 18 | demos `scripts/ios_smoke_all.sh` | `KANAMA_IOS_SKIP_PROBES=1` skipped the probe validation silently. | Prints `SKIP:` per demo. |
| 19 | `scripts/ios_visual_smoke.sh`, `android_smoke.sh`, `android_export_minified.sh` | The artifact launched was never compared with the artifact built (section 3). | Bundle id / application id verified before install. |
| 20 | `scripts/generate_gates_index.py`, `docs/reference/generated/gates.md` | The two skip descriptions said "CI prints a skip line". | Say what is now true; the page is regenerated. |
| 21 | `scripts/web/drivers/chrome_cdp.mjs` | No way to make a Chrome run slower. | `KANAMA_WEB_CPU_THROTTLE=<rate>` (CDP `Emulation.setCPUThrottlingRate`); an unusable value fails the run. |
| 22 | `scripts/web/drivers/demos/web3d.mjs` and the web3d fixture | The "teardown race" flake: see section 4 (it was a fixture defect, not a teardown race). | `parity_restore` probe + `levelUprightAfterParity` check + `KANAMA_WEB3D_EXTRA_PLAY_MS`. |

## 2. Red run per gate

Method. `python3 scripts/gate_red_runs.py` holds each listed gate to green, red, green: the gate passes on the tree as
it is, a reversible mutation (an edit, a created, deleted or renamed file) makes it exit non-zero **and** print the
expected text, and it passes again after the revert. Every case runs in a **throwaway `git worktree`** of HEAD carrying
the dev checkout's uncommitted changes, so a killed run (SIGTERM, a timeout, a crash) cannot leave a mutation in the
checkout you work in; a timeout kills the case's whole process group; `--strict` (implied under `CI`) makes a SKIPPED
case (a missing `GODOT_DOCS`, ...) a failure unless named by `--allow-skip`. The rows below were produced by that command
on the committed branch (`--markdown`): the fast set (57 cases) took about 6 minutes; the Godot and Gradle smokes run
with `--slow` (`KANAMA_GODOT_BIN` set). Gates that drive a browser, a device or a long export are in the second table.

**CI.** `ci.yml` job `gate-red-runs` runs the fast set with `--strict --allow-skip sync_kdoc` on every push to main and on
every pull request that touches `scripts/` or `.github/workflows/` (gated by the `changes` job).
`gate-red-runs-nightly.yml` runs `--slow` nightly. It is not part of `local_ci.sh` (it takes minutes).

### 2a. `scripts/gate_red_runs.py` (exercised on this branch)

| gate | how to make it red | red output | green after revert |
|---|---|---|---|
| `check_gdextension_modernization.py` | a desktop source binds a deprecated GDExtension function (this leg checked nothing before task 118: it scanned the deleted src/main) | `[deprecated] desktop/Android (JVM) binds deprecated 'classdb_construct_object2' (src/jvmMain/kotlin/net/multigesture/kanama/ZzRedRun.kt) — migrate to the newest variant / [divergence] family 'classdb_` | green |
| `validate_godot_api.py` | a MethodBind hash in the shared wrapper tree is off by one (this tree was not scanned before task 118) | `- src/commonMain/kotlin/net/multigesture/kanama/api/Node.kt: Node.get_parent hash 3160264693 not in {3160264692} / [validate_godot_api] hashes checked: jvmMain=664, commonMain=14737, iosMain=621` | green |
| `validate_godot_api.py (tree moved)` | the shared wrapper tree is not where the validator looks (it must fail, not pass on what is left) | `- src/commonMain/kotlin: no Kotlin sources found; this tree carries MethodBind hashes and was not checked` | green |
| `audit_builtin_storage_sizes.py` | the iOS shim sizes a Packed*Array slot at 8 bytes | `[builtin_storage_size_audit] FAIL / - ios/bootstrap/kanama_ios_shim.c: KANAMA_IOS_PACKED_ARRAY_OPAQUE_SIZE is 8, expected 16 (float_64 builtin_class_sizes — all Packed*Array are 16 on 64-bit)` | green |
| `check_actual_public_surface.py` | an `actual object` gains a public member its `expect` lacks | `src/jvmMain/kotlin/net/multigesture/kanama/api/MainThread.kt:17: desktop 'actual object MainThread' declares public 'fun redRunExtraMember/0', which the expect does not / [actual_public_surface] exclu` | green |
| `check_android_remap_sources.py` | a runtime source uses a fragment the Android remap cannot compile | `[android-remap-sources] FAIL src/jvmMain/kotlin/net/multigesture/kanama/ZzRedRun.kt:2: forbidden after the Android remap: 'Files.readString' (call a function value as f(args) / f?.let { it(args) }, ne` | green |
| `check_doc_claims.py` | a marked doc line states the wrong Web protocol | `check_doc_claims: FAIL — 1 stale or malformed claim(s): / docs/exporting/web.md:5: claims protocol 21, but WebScriptCodeEmitter declares 29` | green |
| `check_expect_no_defaults.py` | an `expect fun` declares a default argument | `[expect_defaults] FAIL 1 default argument(s) on an 'expect' declaration. The Android lane skips *.expect.kt, so the default would not exist there; declare an overload per omitted argument instead (tas` | green |
| `check_gate_evidence.py` | the evidence ledger carries a wrong schema version | `[check_gate_evidence] FAIL ledger must be an object with schemaVersion 1: evidence/gates.json` | green |
| `check_godot_version_pin.py` | gradle.properties pins a different Godot than CI | `[check_godot_version_pin] FAIL — Godot version pins drifted: / - .github/workflows/package.yml: GODOT_VERSION=4.7.2-stable but expected 4.7.1-stable (from kanamaGodotVersion=4.7.1.stable)` | green |
| `check_ios_no_silent_stubs.py` | a shared wrapper function whose whole body is a bare default, without a marker | `[check_ios_no_silent_stubs] FAIL — un-annotated silent stub(s): / src/commonMain/kotlin/net/multigesture/kanama/api/ZzRedRun.kt:4: fun redRun(): Boolean = false` | green |
| `check_ios_shim_faults.py` | a guarded early return in the iOS shim stops reporting its fault | `[check-ios-shim-faults] FAIL silent-return kanama_ios_shim.c:1758 kanama_ios_godot_get_method_bind: if (!kanama_ios_resolve_godot_api()) returns without calling kanama_ios_fault( / [check-ios-shim-fau` | green |
| `check_ios_static_dispatch.py` | a static call is routed through a guarded iOS entry point | `[ios_static_dispatch] FAIL dispatcher-no-static src/iosMain/kotlin/net/multigesture/kanama/binding/runtime/ObjectCalls.kt:271 ptrcallDispatch() calls kanama_ios_godot_ptrcall but never kanama_ios_godo` | green |
| `check_jdk_locations_parity.py` | one JDK location row differs between bootstrap.c and the plugins | `[check_jdk_locations_parity] FAIL templates/starter/addons/kanama_tools/plugin.gd differs from bootstrap/bootstrap.c / [check_jdk_locations_parity]   row 1: ('linux', '/usr/lib65/jvm', '', '') != ('li` | green |
| `check_native_call_surface.py` | a new native downcall shape that NativeCallSurface does not prewarm | `[native-call-surface] FAIL / - downcall shape of(JAVA_BYTE,JAVA_BYTE,JAVA_BYTE) is linked but not prewarmed by src/jvmMain/kotlin/ffi/NativeCallSurface.kt:` | green |
| `check_objectcalls_parity.py` | the expect ObjectCalls renames a helper the shared tree calls | `[objectcalls_parity] FAIL the generated expect object does not match the referenced set (1 platform-only exception(s); the list must be empty since task 117 P4') / missing-from-expect  ptrcallWithBool` | green |
| `check_property_coverage.py` | a generated wrapper property disappears | `[property_coverage] FAIL 1 generated property(ies) are silently dropped (no wrapper member, not private, not allow-listed): / CanvasItem.visible (getter=is_visible)` | green |
| `check_protocol_pins.py` | the bridge pins protocol 28 while the emitter says 29 | `[protocol_pins] FAIL the protocol version disagrees across its pins: / 28  bridge constant  (web-runtime/src/webSpikeGodot/assets/kanama-web-bridge.js)` | green |
| `check_pt_tag_tables.py` | one copy of the iOS ptrcall tag table is renumbered | `[pt_tags] FAIL value-mismatch VOID: C enum=0 (ios/bootstrap/kanama_ios_shim.c), generator=99 (scripts/generate_api_wrapper.py), ObjectCalls=0 (src/iosMain/kotlin/net/multigesture/kanama/binding/runtim` | green |
| `check_public_signature_changes.py` | a public signature changes without a CHANGELOG `Source break` line | `[public_signatures] FAIL unannounced source break (2.4s): 1 public signature(s) removed or changed and not announced: no '- **Source break:**' line in CHANGELOG.md '## Unreleased' names Node (marker l` | green |
| `check_typed_enums.py` | an enum parameter goes back to a raw Long | `[typed_enums] FAIL 1 problem(s): / src/commonMain/kotlin/net/multigesture/kanama/api/Node.kt:796: setProcessMode(mode: Long) -- Godot Node.set_process_mode types 'mode' enum::Node.ProcessMode; expecte` | green |
| `check_unapplied_annotations.py` | a lifecycle annotation is imported and never applied | `[unapplied_annotations] src/jvmMain/kotlin/net/multigesture/kanama/ZzRedRun.kt: imports @OnReady but never applies it -- the annotated behaviour is silently disabled / [unapplied_annotations] FAIL 1 f` | green |
| `check_web_callback_flush.py` | a Web callback boundary stops flushing the command buffer | `check_web_callback_flush: FAIL — 1 boundary/boundaries run user Kotlin without flushing the command buffer: / web-runtime/src/wasmJsMain/kotlin/net/multigesture/kanama/web/Main.kt:168  kanamaWebEnterT` | green |
| `check_web_typed_enums.py` | a Web wrapper enum parameter goes back to a raw Long | `[web_typed_enums] FAIL 1 problem(s): / web-runtime/src/commonMain/kotlin/net/multigesture/kanama/api/generated/Node.kt:138: setProcessMode(mode: Long) -- Godot Node.set_process_mode types 'mode' enum:` | green |
| `check_wrapper_generator.py` | a generated wrapper is hand-edited (one stray space) | `[wrapper_generator] FAIL single-tree drift-gate: 1 committed generated files differ from a fresh regen / src/commonMain/kotlin/net/multigesture/kanama/api/Node.kt` | green |
| `audit_api_wrapper_inheritance.py` | a wrapper extends an unrelated handle type | `[api_wrapper_inheritance_audit] FAIL / - src/commonMain/kotlin/net/multigesture/kanama/api/CanvasItem.kt: CanvasItem extends RefCounted, but Godot inherits from Node` | green |
| `audit_claims.sh` | one of the aggregated check scripts is deleted (it used to be a SKIPPED line and exit 0) | `── web protocol pin agreement -- FAIL: the check script is missing: scripts/check_protocol_pins.py / ── unapplied lifecycle annotations (kanama only)` | green |
| `audit_generator_object_policy.py` | the shell generator stops excluding `Object` | `- generate_api_shell_wrappers.UNSAFE_DEFAULT_EXCLUDES is missing: Object` | green |
| `audit_generator_shape_policy.py` | a Dictionary-argument helper stops using the explicit initializer (script is in flight under task 132; red run only) | `- src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:635: ptrcallWithObjectAndDictionaryArg for ('Object', 'Dictionary') -> void: Dictionary argument is not initialized through BuiltinTypes.initDiction` | green |
| `audit_godot_object_script_paths.py` | GodotObject.set stops calling the Object.set bind | `- GodotObject.set must call the Object.set MethodBind` | green |
| `audit_ptrcall_helper_layouts.py` | a uint32 helper reads a 64-bit slot | `- src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt:3601: ptrcallNoArgsRetUInt32 uses JAVA_LONG but helper shape has no int64/enum/bitfield/RID slot` | green |
| `audit_replicated_script_properties.py` | a scene replicates a property its Kotlin script does not export | `example_project/zz_redrun.tscn:.: zz_redrun.kt does not expose replicated property 'health'` | green |
| `audit_runtime_node_lookups.py` | a per-frame callback resolves a node path | `[runtime_node_lookup] FAIL / example_project/zz_redrun.kt:5: function tick contains runtime required node lookup: self.requireAs("Child", ::Node)` | green |
| `audit_singleton_refcounted_policy.py` | the Engine wrapper loses registerSingleton | `[singleton_refcounted] Engine.registerSingleton wrapper not found` | green |
| `audit_stale_blockers.py` | a stale-blocker marker whose blocker no longer holds | `[stale_blockers] FAIL / - CONTRIBUTING.md:2: BLOCKER LIFTED -- CONTRIBUTING.md now matches CONTRIBUTING.md. Claim: red run` | green |
| `audit_value_type_wrappers.py` | a Godot `float` argument is marshalled as a real_t component array | `- src/commonMain/kotlin/net/multigesture/kanama/types/Quaternion.kt:159: Quaternion.slerp passes 1 Godot float arg(s) (weight) but only 0 BArg.Real; a scalar float is an 8-byte double at ptrcall, not ` | green |
| `audit_vararg_ptrcalls.py` | a vararg Godot method is wrapped through ptrcall | `src/commonMain/kotlin/net/multigesture/kanama/api/ZzRedRun.kt:4: Object.call is vararg and must use dynamic Object.call, not ptrcall` | green |
| `audit_variant_marshalling_policy.py` | the Variant marshaller coerces an unknown value instead of failing | `[variant_marshalling_policy_audit] FAIL / - initVariantFromAny must throw for unsupported values` | green |
| `audit_wrapper_abi_policy.py` | a wrapper selects a bool return helper for an object return | `- src/commonMain/kotlin/net/multigesture/kanama/api/Node.kt:263: Node.get_parent: return helper slot is bool, exact policy expects object for Node[]` | green |
| `audit_wrapper_signatures.py` | a wrapper's return helper disagrees with extension_api.json | `[wrapper_signature_audit] FAIL / - src/commonMain/kotlin/net/multigesture/kanama/api/Node.kt:263: Node.get_parent return uses bool, API expects object: Node get_parent()` | green |
| `type_coverage_audit.py` | a value type loses its Variant write branch | `[type_coverage_audit] 1 type(s) appear in ObjectCalls but lack Variant marshal coverage (potential symmetric-gap bug).` | green |
| `api_wrapper_coverage.py --check` | the committed coverage page is hand-edited | `[api_wrapper_coverage] FAIL stale markdown: docs/reference/generated/api-coverage.md / [api_wrapper_coverage] run: python3 scripts/api_wrapper_coverage.py --markdown docs/reference/generated/api-cover` | green |
| `api_wrapper_generator_report.py --check` | the committed generator report is hand-edited | `[wrapper_generator_report] FAIL stale markdown: docs/reference/generated/wrapper-generator-report.md` | green |
| `migrate_enum_constants.py --check` | the committed enum migration table is hand-edited | `[migrate_enum_constants] FAIL docs/reference/generated/enum-migration.md is stale; re-run with --table docs/reference/generated/enum-migration.md` | green |
| `generate_gates_index.py --check` | the committed gates index is hand-edited | `[generate_gates_index] FAIL stale gates index: docs/reference/generated/gates.md / [generate_gates_index] run: python3 scripts/generate_gates_index.py --markdown docs/reference/generated/gates.md` | green |
| `generate_virtual_signature_table.py --check` | the committed virtual-signature table is hand-edited | `[virtual_signature_table] STALE — re-run scripts/generate_virtual_signature_table.py` | green |
| `generate_engine_method_table.py --check` | the committed engine-method table is hand-edited | `[engine_method_table] STALE — re-run scripts/generate_engine_method_table.py` | green |
| `generate_web_backend.py --check` | the generated Web backend dispatch is hand-edited (a real token: comments and wrapping are normalised away) | `[web_backend] FAIL generated Web backend dispatch drift: web-runtime/src/wasmJsMain/kotlin/net/multigesture/kanama/web/WebCommonGodotBackend.generated.kt` | green |
| `generate_web_wrappers.py --check` | a generated Web wrapper is hand-edited | `[web_wrappers] FAIL drift in Node.kt / [web_wrappers] run scripts/generate_web_wrappers.py to regenerate` | green |
| `generate_api_shell_wrappers.py --fail-if-candidates` | a wrapper file is gone while the skip report names it (the shell generator then has a candidate to write) | `[generate_api_shell_wrappers] candidates=1` | green |
| `sync_kdoc_from_godot_docs.py --check` | a wrapper KDoc block differs from the Godot class docs | `[sync_kdoc] classes=1081 classes_with_docs=795 documented_items=13692 missing_or_unmatched_docs=690 changed_files=1 / [sync_kdoc] changed src/commonMain/kotlin/net/multigesture/kanama/api/Node.kt` | green |
| `audit_swallowed_failures.py` | a gate script swallows a failure (`|| true`) without saying why | `[audit_swallowed_failures] FAIL 1 swallowed-failure site(s) with no '# justified: <why>' and no known idiom: / scripts/install-git-hooks.sh:9: false \|\| true` | green |
| `audit_swallowed_failures.py (stale table)` | the generated audit table no longer matches the `# justified:` comments | `[audit_swallowed_failures] FAIL the audit table in scripts/README-gates.md is stale (a site or its '# justified:' text changed); run 'python3 scripts/audit_swallowed_failures.py --write'` | green |
| `check_shell_lint.sh` | a new gate script carries a shellcheck warning (unquoted variable, unchecked cd) | `In scripts/zz_redrun.sh line 2: / cd /tmp` | green |
| `check_no_local_paths.py --self-test` | the scan stops finding leaks (the gate is broken; its self-test must say so) | `check_no_local_paths --self-test FAIL: expected the text and the binary leak, got []` | green |
| `web/scaffold_selftest.sh` | web_export_smoke.sh stops running the budget gate (the self-test's own `over-budget` case must catch that) | `FAIL: over-budget expected exit 1, got 0 / stderr: coverage_report: FAIL — $TMPDIR/kanama-web-scaffold.jkB5pO/over-budget/result.json carries no exercisedMembe` | green |
| `web_export_smoke.sh (browser-floor)` | web_export_smoke.sh stops running the browser-floor gate (the `below-floor` case must catch that) | `FAIL: below-floor expected exit 1, got 0 / stderr: coverage_report: FAIL — $TMPDIR/kanama-web-scaffold.8JbYG8/below-floor/result.json carries no exercisedMembe` | green |
| `tool_smoke.sh` | the example script no longer logs its scene-delivered properties: the failure comes from the editor run's log, not the plugin-copy pre-check | `[tool_smoke] missing pattern: HelloScript\(file\)\._ready health=99 speed=5\.1 label=from_tscn / [tool_smoke] log tail:` | green |
| `tool_smoke.sh (plugin copies)` | the example project's editor plugin drifts from the starter template's copy (the cheap pre-check) | `[tool_smoke] plugin copies differ: templates/starter/addons/kanama_tools/plugin.gd vs example_project/addons/kanama_tools/plugin.gd / [tool_smoke] log tail:` | green |
| `runtime_smoke.sh` | the example script no longer logs its scene-delivered properties (script unchanged: in-flight task 132; red run only) | `[runtime_smoke] missing pattern: HelloScript\(file\)\._ready health=99 speed=5\.1 label=from_tscn difficulty=HARD / [runtime_smoke] log tail:` | green |
| `hot_reload_smoke.sh` | the reloadable script line the smoke rewrites is gone, so no marker can reach the log | `[hot_reload_smoke] missing marker: A / [kanama:kt] FileAccess numeric_fixture write8=true read8=127 write16=true be16=18-52 write32=true read32=16909060 write64=true read64=72623859790382856 write_dou` | green |
| `check_exported_scene_properties_selftest.sh` | the scene check stops saying `lost script property` (its own red run is inside the self-test) | `[scene_check_selftest] FAIL: the missing override property was not reported` | green |
| `check_bootstrap_jdk_resolution.sh` | the bootstrap's stale-JDK diagnostic changes text (its fail cases then match nothing) | `[check_bootstrap_jdk_resolution] FAIL env_stale_header -- output does not match: predates JDK 21 \(no JNI_VERSION_21\)` | green |
| `check_gradle_templates_configure.sh` | the release-kit settings file puts `plugins {}` before `pluginManagement {}` | `[check_gradle_templates_configure] FAIL -- the release-kit Gradle template does not configure:` | green |
| `hot_reload_in_process_smoke.sh` | the reloadable script line the smoke rewrites is gone, so no marker can reach the log | `[hot_reload_in_process_smoke] FAIL -- timeout waiting for pattern: HelloScript\(file\)\._ready\[A\] / [hot_reload_in_process_smoke] full log: /tmp/kanama_hot_reload_in_process.log` | green |
| `check_jdk_lookup_parity.sh` | the editor plugin's JDK location table drifts from bootstrap.c's (a `~/.jdks` row renamed) | `[check_jdk_lookup_parity] FAIL table_home_dot_jdks: expected $TMPDIR/kanama_jdk_lookup.qT9Bq2/s12/home/.jdks/jdk-25-fake/lib/server/libjvm.dylib / [check_jdk_l` | green |

### 2b. Self-tests, browser/device gates, and gates exercised another way

| gate | how to make it red | red output (1-3 lines) | green after revert |
|---|---|---|---|
| `web/scaffold_selftest.sh` (covers `web_export_smoke.sh`, `result_schema.py`, `check_budgets.py`, `check_browser_floor.py`, `tree_checksum.py`) | built in: ten fake-driver scenarios, each must exit as listed: `failed-assertion`, `malformed-result`, `schema-violation`, `wrong-checksum`, `driver-crash`, `driver-timeout`, `tree-mutation`, `below-floor`, `over-budget` exit 1; `success` 0; a usage error 2. The self-test itself is broken on purpose by two rows of 2a | `PASS: over-budget (exit 1)` ... `== scaffold self-test: 12 passed, 0 failed ==` | green |
| `web_export_smoke.sh` on a real browser (web3d, Chrome 154) | make `Main.parityRestore` a no-op (section 4) | exit 1, `39/40`, `levelUprightAfterParity` false | 10/10 PASS, 30/30 under load |
| `web/check_no_local_paths.py` | `--self-test` plants a path in a text file and in a binary file | `PASS` (the scan is also broken on purpose in 2a: `--self-test FAIL: expected the text and the binary leak`) | green |
| `web/coverage_report.py` | a report, not a gate: its own unusable input exits 2. `--result` a JSON without `exercisedMembers` | `coverage_report: FAIL ... carries no exercisedMembers census`, exit 2 | exit 0 on a real result |
| `web_package_smoke.sh` | hand it a zip with no `index.html` at its root | `unzip of ... has no index.html at the zip root`, exit 2 | green in `web.yml` / `package.yml` |
| `package_install_smoke.sh` | hand it a zip that is no kit (`--ios-addon bad.zip`) | `could not detect package kind for: bad.zip`, exit 1 | green in `package.yml` (needs the release zips; not built here) |
| `ios_template_preflight.sh` | hand it an `ios.zip` without the simulator slice | `caution: filename not matched: libgodot.ios.debug.xcframework/ios-arm64_x86_64-simulator/libgodot.a`, exit 11 | **red on this machine's real 4.7.2 `ios.zip` too** (`Install a corrected Godot iOS export template`): the official template carries no arm64 simulator slice; unchanged by this task |
| `gate_skip.sh` / `gate_skip.py` | `CI=true` and a skip not in `KANAMA_ALLOW_SKIP` | `FAIL: android-apk-package-id was skipped in CI. Fix the runner, or list ... in KANAMA_ALLOW_SKIP` (shell); `FAIL: stale-blocker-task-tokens was skipped in CI` (Python), exit 1 | allowed id: exit 0 |
| `android_apk_id.sh` (used by `android_smoke.sh`, `android_export_minified.sh`) | ask for a package the APK is not (a real APK, `net.multigesture.kanama.dodge`, asked as `...match3`) | `the exported APK's application id is '...dodge' but this run was asked to install and launch '...match3'`, exit 1 | `APK application id verified: net.multigesture.kanama.dodge` |
| `ios_visual_smoke.sh` `assert_built_bundle_id` | a `.app` whose `Info.plist` names another bundle id (fake `.app`) | `the built app's bundle id is '...thirdperson' but this run launches '...iosvisualsmoke'`, exit 1 | `built app bundle id verified` |
| demos `ios_device_run.sh --check-console-faults` / `--self-test-console-checks` (the task-124 fault check and the runtime self-test verdict) | `--self-test-console-checks` feeds fixture console logs and needs 11 verdicts: clean passes; `OBJECTCALLS SELFTEST FAIL:` + `306 passed, 2 failed` (the logged device run, which the old runner passed with exit 0: verified against the pre-fix script), a failed count in the ObjectCalls or the PTRCALL summary, a clean early summary with a failed final one, a missing summary, a FAULT line, `faults=8 expected=7` all fail; no self-test text at all passes unless `KANAMA_IOS_REQUIRE_SELFTEST=1` | `console: a runtime self-test reported a FAILURE`, `a runtime self-test summary has failures`, `no 'OBJECTCALLS SELFTEST: N passed, 0 failed' summary line; the self-test did not finish`, `the iOS bridge reported a FAULT`; each ends `FAIL` | `console checks self-test: PASS`; `--check-console-faults` on a clean log: `PASS`; `--self-test-app-pids`: `PASS` |
| demos `desktop_smoke_all.sh` | `GODOT` is a fake that exits 0 without writing `--log-file` (exercised) | `expected log is missing, so the hard-error check cannot run (import): ...import.log`, exit 1 | a fake that writes its log: `[desktop_smoke_all] PASS` (9 demos) |
| demos `demo_parity_audit.py` | a `kotlin-src/ZzRedRun.kt` with `n.call("do_thing")` (exercised, removed); or a root without any `kotlin-src` (exit 2, was `PASS checked 0`) | `Bunnymark/kotlin-src/ZzRedRun.kt:3: raw call("do_thing") should use typed/generated API or be allowlisted` | `PASS checked 126 Kotlin demo script(s)` |
| `android_smoke.sh`, `android_export_minified.sh` | **device run pending.** Export an APK whose preset `package/unique_name` differs from the package argument; or a build whose app dies after init (`kill -9` the pid during `KANAMA_ANDROID_LAUNCH_WAIT`) | `the exported APK's application id is ...` (before any install); `is not running ...s after launch (crashed or never started)` | green on the four devices of the version-support table |
| `ios_visual_smoke.sh`, `ios_device_gate.sh` | **device run pending.** `--physical-device` with a preset that writes a different bundle id; or a console with `OBJECTCALLS SELFTEST: N passed, 1 failed` / without the summary line | `the built app's bundle id is ...`; `runtime self-test reported failures` / `a runtime self-test summary line is missing ...`; a result matrix with a missing row: `expected N result rows ... a step did not run` | green on iPhone 12 / 15 Pro |
| demos `ios_device_run.sh` (launch path), `android_smoke_all.sh`, `ios_smoke_all.sh` | **device run pending.** Pass a bundle id that is not the exported one | `installed bundle id '...' (from the export preset) != requested '...'; the launch would start a different app` | green on the device matrix |
| `web_ci_matrix.sh` | `--skip-export --demo charactercontroller` (no export on disk) | `charactercontroller: no export at web-runtime/build/web-export/charactercontroller`, a `**FAIL**` row in the table, `[web_ci_matrix] FAIL`, exit 1 | `--skip-export --demo web3d --engine chrome`: `PASS`, protocol 29 |
| `fresh_clone_smoke.sh`, `web_fresh_checkout_smoke.sh`, `export_game_smoke.sh` | **not exercised** (a clone + `local_ci.sh`, a demos export, a jlink runtime): their red runs are the gates they wrap (2a); for `export_game_smoke.sh` it is its `libjvm` path assertion (a runtime that boots from a JDK elsewhere on the machine must fail it) | n/a | `package.yml` |
| `upgrade_godot.sh` | **not exercised** (release tool, runs every gate): its preflight exits 1 on a binary whose version is not the pin, a non-stable pin, an unverifiable docs tree and a dirty tree | `FAIL: binary reports ... but kanamaGodotVersion=...` | n/a |
| `local_ci.sh` | umbrella: any stage red; the ERR trap prints `[local_ci] FAILED (exit N)`, the stage and the command | n/a (not run by this task) | `ci.yml` |
| `audit_embedded_processes.sh`, `refresh_godot_api.sh`, `jextract.sh`, `install-git-hooks.sh`, `web/serve_export.py`, `web/browser_version.py`, `web/differential_diff.py`, `web/visibility_probe.py`, `generate_*` writers | not gates (maintainer aids, a dev server, experiments, generators whose `--check` mode is in 2a) | n/a | n/a |

### 2c. Runtime self-test verdicts and smoke completion: the silent-pass sweep (task 118 follow-up)

A device run showed the demos' `ios_device_run.sh` exiting 0 over `OBJECTCALLS SELFTEST FAIL: ...` / `2 failed`. How the
runner knows an app prints self-tests: it does not assume it. The self-test is *expected* when the console shows any
self-test text (the `PTRCALL SELFTEST MATRIX` marker, a `SELFTEST FAIL:` line or a summary), and then both summaries must
read `0 failed`; `KANAMA_IOS_REQUIRE_SELFTEST=1` requires it even when the console shows none. The default stays
marker-based so a runtime that predates the self-tests is not failed for lacking them.

| runner | verdict source | gap found | state |
|---|---|---|---|
| demos `ios_device_run.sh` | console window | FAIL lines and failed counts ignored; only `faults=` compared | **fixed** (above) |
| `ios_visual_smoke.sh` | launch logs | FAIL line / failed count / both summaries were checked only under `--kanama-user-script-probe`; other probe modes (the demos' probes) ignored them | **fixed**: FAIL line and failed count now fail every Kanama launch; the "both summaries present" rule stays in the starter probe |
| `ios_device_gate.sh`, demos `ios_smoke_all.sh` | their children above | none of their own | covered by the two fixes |
| demos `desktop_smoke_all.sh` | console log | the six `KANAMA_DEMO_SMOKE_QUIT=1` demos' completion line `[kanama:smoke] SmokeQuit complete` was never required (only a `timeout` would catch a run that did not finish, and `timeout` is optional there) | **fixed**: required; exercised with a fake Godot with and without the line |
| demos `desktop_smoke_all.sh` (FPS, Racing, City-Builder) | console log | their smokes quit from `ready()` and print no completion line; a failing `check()` is a Kotlin exception that keeps the game running until the timeout; the hard-error patterns do not include `SCRIPT ERROR` | **listed, not fixed**: needs a completion line in those three `Smoke.kt` files, a demos change outside this task |
| `android_smoke.sh`, `android_export_minified.sh`, demos `android_smoke_all.sh` | logcat | no runtime self-test exists on Android; the demos' SmokeQuit does not run there (no `KANAMA_DEMO_SMOKE_QUIT`); crash markers and the positive startup checks are the verdict | none to fix; listed |
| `runtime_smoke.sh`, `tool_smoke.sh`, `hot_reload*_smoke.sh` | Godot log | positive `check` patterns plus `check_absent`; the example project has no self-test summary to ignore | none |
| Web drivers (`scripts/web/drivers/**`) | result envelope | assertion counts are in the envelope, which `result_schema.py` validates and `web_export_smoke.sh` turns into the exit status (scaffold cases `failed-assertion`, `malformed-result`, `schema-violation`) | none |

## 3. A gate proves the artifact it ran is the one it built

Rule from the iOS launch-mismatch entry (the runner installed one bundle id and launched another, so an old build's
console was reported as the new build's result). Every device and browser runner, checked:

| runner | what ties the run to the build | state |
|---|---|---|
| demos `ios_device_run.sh` | reads `bundleID:` from the `devicectl device install app` output and refuses to launch on a mismatch or when none is reported | already in the demos runner before this task (added by the task-115 diagnosis); unchanged. Red run: needs a device ("device run pending"): export a demo whose preset id differs from the id passed on the command line |
| `ios_visual_smoke.sh` (starter + `ios_device_gate.sh` fresh path, simulator and device) | **new:** `assert_built_bundle_id` reads `CFBundleIdentifier` from the built `.app` and refuses to install a different id than `launch` uses | fixed. Exercised without a device on a fake `.app`: wrong id -> `refusing to install a different app than the one it would launch`, exit 1; right id -> `built app bundle id verified`. Device run pending |
| `ios_device_gate.sh` demo matrix | exports every demo copy under the one gate bundle id (`prepare_demo_copy` rewrites the preset) and hands that id to `ios_device_run.sh`, which verifies it (above) | ok; plus the new result-row-count check |
| `android_smoke.sh`, `android_export_minified.sh` | **new:** `android_apk_id.sh` reads the APK's application id (`aapt2 dump packagename`, else `apkanalyzer manifest application-id`) and refuses to install on a mismatch with the package it will launch; and the logcat judged is the live pid's, never the whole device's | fixed. Exercised on a real APK: the matching package passes, a wrong one prints the refusal and exits 1, no tools -> `SKIP: android-apk-package-id` (fatal under CI unless allowed). Device run pending |
| `web_export_smoke.sh` + the Chrome/Firefox/Safari drivers | serves exactly `--export-dir`, checksums the served tree before and after, the driver echoes `--source-checksum` into the result, and `result_schema.py --manifest` compares the page's protocol with the export's manifest | ok (build-id is not compared: the page does not report the export's `buildId`; protocol + tree checksum is what ties them today). Red runs: the scaffold self-test's `wrong-checksum`, `tree-mutation` and protocol cases |
| `web_package_smoke.sh` | unzips the artifact it was handed and serves THAT | ok |
| `package_install_smoke.sh` | unzips the zip it was handed and builds from the unzipped project | ok |
| `export_game_smoke.sh` | asserts the bootstrap's logged `libjvm` path is the bundled runtime | ok |
| `tool_smoke.sh`, `runtime_smoke.sh`, `hot_reload*_smoke.sh` | each starts with `syncExampleAddonJar`, so the jar Godot loads is the one just built | ok (no build stamp) |
| demos `android_smoke_all.sh`, `ios_smoke_all.sh` | pass the package / bundle id per demo to the runners above, which verify it | ok |

## 4. The web3d flake: it was not a teardown race

The spec's first "Known flaky gate steps" entry (kanama#258, 2026-09-15: 38 of 39 assertions, `noCallbackFaults` false,
`Player._physics_process` throwing `DownRay should hit the floor from a grounded player` 61 times in 0.3 s, called a
teardown-ordering race) is a defect in the fixture and in the driver's parity probes. No error is ignored in the runner
and nothing about teardown changed.

**Reproduction (before the fix).** Not reproducible on an idle machine (0 failures in 8 runs: one plain, six with 20 s of extra play, one with a yaw trace). With 14
busy-loop processes on the machine: 1 failure in 9 valid runs; with `KANAMA_WEB_CPU_THROTTLE=10`: 1 in 10; 1 in 4 more on an
instrumented build; throttle 4 and 25: 0 in 10 each (CPU throttling alone did not reproduce it). Every failure
had the CI signature: 38/39, only `noCallbackFaults` false, the same boundary error.

**Cause.** The observed state, from an instrumented build that put it into the error text: `insideTree=true queued=false parentQueued=false
onFloor=true floor=true pos=(0.13, -0.30, 6.33)`. At the moment of the first failure the level was **not** being torn
down, the Player was **not** queued for deletion, and the floor existed; the Player was standing at **z = 6.33** on a
12 x 12 floor (half-size 6) with x near 0. `Main.parityProbe` / `parityModelFrontLook` aim the **root**
(`lookAtFromPosition`), which turns the whole level a quarter turn about Y, and nothing turned it back (the driver read
the baseline export at `1.5708` rad at teardown, from 8.3 s on). The mechanism that follows is inferred from that state and from the yaw reading, and is what the fix removes: `Player` sets its pacing velocity in world axes but paces
on its local `x`, so with the level turned it walks along the level's z axis, off the floor, respawns, and walks off
again. When a touchdown lands while its centre is past the floor's edge it still overlaps the floor (`isOnFloor()` true)
but the ray from its centre misses: `check(downRay.isColliding())` throws, `moveAndSlide` is never reached, the state
cannot change, and every physics tick throws until teardown frees the Player. That is consistent with the 61 errors in 0.3 s. Whether a
touchdown lands in the edge window is phase luck: a few percent of runs, more on a loaded machine.

**Fix (in the fixture and its driver, not in the runner).**
`web-runtime/src/web3dSmoke/web/kotlin-src/Main.kt`: `parityRestore()` (declared last, so it is method 34 and
renumbers nothing; the driver resolves it by name) turns the root back and checks the yaw.
`scripts/web/drivers/demos/web3d.mjs`: calls it right after reading the second yaw, and adds the check
`levelUprightAfterParity` (40 assertions now), which reads the yaw itself and does not rely on the Kotlin check.
`KANAMA_WEB3D_EXTRA_PLAY_MS=<ms>` keeps the level running before teardown (a longer window for a probabilistic
defect); `KANAMA_WEB_CPU_THROTTLE=<rate>` (Chrome driver) is the matching slow-CI knob.

**Proof.**

| run | result |
|---|---|
| red: `parityRestore` made a no-op (Kotlin check removed), driver unchanged, 1 run | exit 1, 39/40, `levelUprightAfterParity` false (deterministic; the old flake needed luck) |
| fix, 10 local headless Chrome 154 runs, no load (final export) | 10 pass / 0 fail |
| fix, 30 runs under load (14 busy loops, machine load average 27-76) | 30 pass / 0 fail |
| before the fix, same load class | 1/9, 1/10, 1/4 failed |

## Re-running

```sh
python3 scripts/gate_red_runs.py                          # every fast case (about 7 minutes)
python3 scripts/gate_red_runs.py --only audit_            # a subset
KANAMA_GODOT_BIN=/path/to/godot python3 scripts/gate_red_runs.py --slow   # + the Godot / Gradle smokes
python3 scripts/gate_red_runs.py --markdown /tmp/red.md   # the table rows
python3 scripts/web/check_no_local_paths.py --self-test
scripts/web/scaffold_selftest.sh
```

The harness mutates files in place and always reverts them; run it on a checkout nothing else is writing to.

# shellcheck shell=bash
#
# demos.sh -- the single Web demo registry, sourced by every Web gate.
#
# The demo key -> project directory -> Gradle property mapping used to live in
# each gate script; two copies is one drift away from a gate that silently skips
# a demo. Everything that needs to know about the corpus sources this file:
# scripts/web_fresh_checkout_smoke.sh (60g) and scripts/web_ci_matrix.sh (60h).
#
# Adding a demo means editing THIS file plus the driver wiring listed in
# docs/exporting/web.md.

# The full corpus, in port order. `web3d` is an in-repo fixture; every other demo
# lives in the kanama-demos checkout.
KANAMA_WEB_ALL_DEMOS=(
  match3 bunnymark dodge web3d platformer squash fps
  charactercontroller thirdperson racing citybuilder tpsdemo
)

# The per-PR subset (Task 60h). Three demos chosen for coverage, not speed:
# match3 exercises pointer-drag input and the 2D snapshot path, web3d the 3D
# renderer from an in-repo fixture (it needs no demos checkout at all), and
# dodge the full node/signal/scheduler lifecycle to a zero handle count.
# The full corpus runs on push to main and on the nightly schedule.
KANAMA_WEB_PR_DEMOS=(match3 web3d dodge)

# The soak gate (Task 60h) is a DRIVER, not a thirteenth demo: it drives the
# DODGE export for a long window and asserts no slow leak. It is deliberately
# absent from both lists above -- it is opt-in (`--demo soak`), because it costs
# ten minutes, and it must never silently pad a "corpus is green" claim.

# The gated transport benchmark (task 84 follow-up). Like soak it is a harness
# cell rather than a thirteenth game demo, but unlike soak the CI matrix DOES
# fold it into the full and ci sets: task 84 made the benchmark opt-in, and
# opt-in with no gate is exactly the shape that lets a path rot unnoticed
# (task 81). It exports the IN-REPO staged Phase 0 project through its own
# dedicated Gradle task (`:web-runtime:exportWebSpike`), so it needs no demos
# checkout. It stays out of the per-PR subset, which is chosen for speed.
KANAMA_WEB_GATED_BENCHMARKS=(spike)

# demo key -> the export/build key it runs against. Everything but soak is itself.
kanama_web_demo_export_key() {
  case "$1" in
    soak) echo "dodge" ;;
    *) echo "$1" ;;
  esac
}

# demo key -> the Gradle task that builds its export. Everything but the spike
# goes through the shared `exportWeb` task with a `-PkanamaWebDemo` key; the
# spike's staged benchmark project has had its own task since Phase 0.
kanama_web_demo_export_task() {
  case "$1" in
    spike) echo ":web-runtime:exportWebSpike" ;;
    *) echo ":web-runtime:exportWeb" ;;
  esac
}

# demo key -> export directory, relative to the kanama repo root.
kanama_web_demo_export_dir() {
  case "$1" in
    spike) echo "web-runtime/build/web-spike/export" ;;
    *) echo "web-runtime/build/web-export/$1" ;;
  esac
}

# demo key -> kanama-demos project directory ("" for the in-repo fixture).
kanama_web_demo_project_dir() {
  case "$1" in
    match3) echo "Starter-Kit-Match3" ;;
    soak) echo "godot-demo-2d-dodge-the-creeps" ;;
    bunnymark) echo "Bunnymark" ;;
    dodge) echo "godot-demo-2d-dodge-the-creeps" ;;
    web3d) echo "" ;;
    platformer) echo "Starter-Kit-3D-Platformer" ;;
    squash) echo "godot-demo-3d-squash-the-creeps" ;;
    fps) echo "Starter-Kit-FPS" ;;
    charactercontroller) echo "godot-4-3d-character-controller-tutorial" ;;
    thirdperson) echo "godot-4-3d-third-person-controller" ;;
    racing) echo "Starter-Kit-Racing" ;;
    citybuilder) echo "Starter-Kit-City-Builder" ;;
    tpsdemo) echo "tps-demo-kanama" ;;
    *) return 1 ;;
  esac
}

# demo key -> the -PkanamaWeb<Key>ProjectDir gradle property the build reads.
kanama_web_demo_project_property() {
  case "$1" in
    match3) echo "kanamaWebMatch3ProjectDir" ;;
    soak) echo "kanamaWebDodgeProjectDir" ;;
    bunnymark) echo "kanamaWebBunnymarkProjectDir" ;;
    dodge) echo "kanamaWebDodgeProjectDir" ;;
    web3d) echo "" ;;
    platformer) echo "kanamaWebPlatformerProjectDir" ;;
    squash) echo "kanamaWebSquashProjectDir" ;;
    fps) echo "kanamaWebFpsProjectDir" ;;
    charactercontroller) echo "kanamaWebCharactercontrollerProjectDir" ;;
    thirdperson) echo "kanamaWebThirdpersonProjectDir" ;;
    racing) echo "kanamaWebRacingProjectDir" ;;
    citybuilder) echo "kanamaWebCitybuilderProjectDir" ;;
    tpsdemo) echo "kanamaWebTpsdemoProjectDir" ;;
    *) return 1 ;;
  esac
}

# Extra `exportWeb` arguments a demo needs, one per line (empty for most).
# Bunnymark's validated Web configuration is the 256-sprite V1Sprites variant.
kanama_web_demo_export_args() {
  case "$1" in
    bunnymark) echo "-PkanamaWebBunnymarkVariant=BunnymarkV1Sprites" ;;
    *) : ;;
  esac
}

# Per-demo driver budget in seconds. These are wall-clock ceilings, not
# expectations: the heavy 3D demos hold input for fixed windows and warm up
# shaders on a software GL, so they legitimately take minutes. A CI host is
# slower than a workstation -- scale with --timeout-scale rather than editing
# these, so the local and CI numbers stay comparable.
kanama_web_demo_timeout() {
  case "$1" in
    match3|bunnymark|dodge|web3d|spike) echo 300 ;;
    platformer|squash|fps) echo 480 ;;
    charactercontroller|thirdperson|racing|citybuilder|tpsdemo) echo 600 ;;
    # The soak budget is derived from its own duration, never guessed: the driver
    # runs for KANAMA_WEB_SOAK_SECONDS and then still has to tear down.
    soak) echo $(( ${KANAMA_WEB_SOAK_SECONDS:-600} + 240 )) ;;
    *) echo 300 ;;
  esac
}

kanama_web_demo_is_known() {
  # The spike is deliberately NOT in kanama_web_demo_project_dir: the
  # fresh-checkout gate validates its --demo arguments against that mapping and
  # cannot export the spike (it has no exportWeb key), so leaving it unknown
  # there keeps that gate's rejection loud instead of failing mid-export.
  [[ "$1" == "spike" ]] || kanama_web_demo_project_dir "$1" >/dev/null 2>&1
}

# Demos CI structurally cannot run -> the reason. This is NOT quarantine: nothing
# here is a defect and nothing here gets fixed. Safari is the existing example of
# the same idea at the engine level (no headless mode, so it is a local gate), and
# tpsdemo is the demo-level one: its Kotlin/Wasm compile is killed by the OOM
# killer on a 16 GB GitHub-hosted runner, which is a property of the runner.
#
# These still run LOCALLY, where they pass, and the matrix records them as
# explicitly skipped-with-a-reason rather than quietly leaving them out -- a demo
# that silently disappears from a corpus is how a green run stops meaning
# anything.
kanama_web_demo_local_only_reason() {
  case "$1" in
    tpsdemo)
      echo "the Kotlin/Wasm compile exceeds a GitHub-hosted runner (OOM in compileProductionExecutableKotlinWasmJs); local gate only"
      ;;
    *) : ;;
  esac
}

# Quarantined cells: "demo:engine" -> the reason, which must name a task.
#
# A quarantined cell still EXPORTS, still RUNS, and still reports its result --
# it simply does not fail the build. Deleting a demo from the matrix instead
# would be the exact trap this gate exists to close: the corpus would look green
# because nobody was looking. A quarantined cell that PASSES is reported just as
# loudly, because a stale quarantine is worse than none.
#
# Lifting one is a one-line deletion here -- and scripts/audit_stale_blockers.py makes the
# deletion mandatory: the KANAMA-BLOCKED marker below fails the build the moment its task is
# archived, so a quarantine cannot outlive the defect it cites.
kanama_web_quarantine_reason() {
  case "$1" in
    # KANAMA-BLOCKED(since:2026-07-28, task:71): dodge/squash mobs never free on a Linux host
    dodge:firefox|squash:chrome|squash:firefox)
      echo "task 71 — spawned mobs never free on a Linux host; both demos pass on macOS"
      ;;
    *) : ;;
  esac
}

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

# demo key -> kanama-demos project directory ("" for the in-repo fixture).
kanama_web_demo_project_dir() {
  case "$1" in
    match3) echo "Starter-Kit-Match3" ;;
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
    match3|bunnymark|dodge|web3d) echo 300 ;;
    platformer|squash|fps) echo 480 ;;
    charactercontroller|thirdperson|racing|citybuilder|tpsdemo) echo 600 ;;
    *) echo 300 ;;
  esac
}

kanama_web_demo_is_known() {
  kanama_web_demo_project_dir "$1" >/dev/null 2>&1
}

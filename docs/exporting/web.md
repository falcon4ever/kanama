# Export for the Web

!!! note "Web is experimental"
    You can export your own project to the Web. Kanama checks exports in
    desktop Chrome and Firefox; Safari and phones are checked by hand. What
    does not work on the Web is listed in
    [Limitations](../reference/limitations.md#web).

Kanama compiles your Kotlin scripts to WebAssembly, the code format browsers
run natively, and Godot's Web export runs them in the page next to your game.
[Web Internals](../contributing/backends/web.md) explains how the two talk.

## Requirements

- A Kanama project that runs in the editor, made from a
  [release kit](../getting-started/release-kit.md) or set up with the
  [store addon](../getting-started/store-addon.md). The addon carries the
  Kanama Web runtime, so you need nothing else from Kanama. A project built
  from a checkout of the Kanama repository exports from there; see
  [From a Source Checkout](#from-a-source-checkout).
- Godot 4.7.2 and its export templates, installed from **Editor > Manage
  Export Templates**. The Web export uses `web_nothreads_release.zip` from the
  templates folder:

    | OS | Export templates folder |
    | --- | --- |
    | macOS | `~/Library/Application Support/Godot/export_templates/4.7.2.stable/` |
    | Linux | `~/.local/share/godot/export_templates/4.7.2.stable/` |
    | Windows | `%APPDATA%\Godot\export_templates\4.7.2.stable\` |

- A network connection for the first export. It downloads Node.js, Yarn and
  Binaryen, the tools the Web build runs, about 70 MB, into Gradle's folder in
  your home folder (`~/.gradle`).
- 8 GB of memory or more. Compiling your scripts with the Kanama Web runtime
  can use up to 3 GB.
- Chrome 130 or Firefox 141, or newer, to run the export.

## Exporting Your Project

Run this in your project:

```sh
cd /path/to/your-project
./gradlew exportWeb
```

It builds your scripts for desktop first, as **Build Scripts** does, because
the editor loads them during the export. Then it compiles the scripts in
`kotlin-src/` to WebAssembly with the Kanama Web runtime from `addons/kanama/maven`. It exports a copy of the project
with Godot, with each Kotlin script replaced by a small GDScript file that
calls into WebAssembly, and puts the two together. The export lands in
`build/web-export/`. Your project folder is not changed: the copy Godot
exports is under `build/kanama/web/`.

The first export takes a minute or two, most of it the downloads. Later ones
take about 30 seconds for the starter project.

The game's name, icon, window size and main scene come from your Project
Settings, as in any export. The settings of your own Web export preset do not
apply; [Limitations](../reference/limitations.md#web) says what to do instead.

The export finds Godot and its Web template where Godot installs them: the
templates folder above, and `/Applications/Godot.app` on macOS. If yours are
somewhere else, name them:

```sh
cd /path/to/your-project
./gradlew exportWeb \
  -PkanamaGodotExecutable=/path/to/godot \
  -PkanamaWebTemplateRelease=/path/to/web_nothreads_release.zip
```

When the build stops on one of your scripts, the error names the script and
the function. See [Web-Compatible Scripts](#web-compatible-scripts).

## Running the Export

Serve the export over HTTP. Opening `index.html` from disk does not load the
game. Any static server works; the one in the addon sends the right type for
`.wasm` files and listens on this machine only:

```sh
cd /path/to/your-project
python3 addons/kanama/web/serve_export.py build/web-export
```

It prints `PORT=<n>`. Open `http://127.0.0.1:<n>/` in your browser. The
server runs until you press **Ctrl+C**.

## Testing on a Phone or Tablet

To open the export from another device on your network, serve it over HTTPS.
Godot's Web export only starts from a secure address, and `127.0.0.1` is the
only plain-HTTP address that counts as one:

```sh
cd /path/to/your-project
python3 addons/kanama/web/serve_export.py --lan --https build/web-export
```

It prints `LAN=https://<this-machine>:<n>/`. `--https` makes a self-signed
certificate with the `openssl` command the first time and reuses it for 30
days. Open the address on the device and accept the certificate warning once.
On iPhone and iPad Safari, tap **Show Details**, then **visit this website**.

## Publishing

Zip the contents of `build/web-export/`, so that `index.html` is at the root
of the zip:

```sh
cd /path/to/your-project/build/web-export
zip -r ../my-game-web.zip .
```

To publish on **itch.io**,
[install butler](https://itch.io/docs/butler/installing.html), log in once
with `butler login`, and upload the zip:

```sh
butler push /path/to/your-project/build/my-game-web.zip <user>/<game>:html5
```

On the game's edit page, set the upload to **This file will be played in the
browser**. Leave **SharedArrayBuffer support** off: the export uses a single
thread and needs no special headers. itch.io takes at most 500 MB and 1000
files; `build/web-export/kanama-web/export-report.json` lists every file and
the total size.

To publish on **any other static host**, such as GitHub Pages, copy the
contents of `build/web-export/` into a folder the host serves over HTTPS. The
host must send `.wasm` files with the `application/wasm` content type; GitHub
Pages does.

## Web-Compatible Scripts

The Web build compiles the same `kotlin-src/` folder as desktop, and most
scripts need no change: `by node<Timer>("Timer")`, `requireAs<T>()`,
`castOrNull<T>()`, `preload` and `instantiateAs<T>()` work the same way. When
the build stops on a script, the error names the script and the function.
[Limitations](../reference/limitations.md#web) lists what the Web build cannot
compile, with what to do instead.

For a script that must differ on the Web, a file with the same name in
`web/kotlin-src/` replaces the shared file in the Web build only. A
desktop-only script is left out by listing its path, relative to
`kotlin-src/`, in `web/kotlin-src-excludes.txt`, one per line. The demos use
both.

## From a Source Checkout

A project you build from a checkout of the Kanama repository, as
[Install from a Source Checkout](../getting-started/source-checkout.md) sets
up, exports from the checkout instead. Pass the folder that holds your
project's `project.godot`:

```sh
cd /path/to/kanama
./gradlew :web-runtime:exportWeb -PkanamaWebProjectDir=/path/to/your-project
```

It runs the same steps as `exportWeb` above and lands in
`web-runtime/build/web-export/project/` inside the checkout. It takes
`-PkanamaGodotExecutable` and `-PkanamaWebTemplateRelease` as above. Serve it
with `python3 scripts/web/serve_export.py web-runtime/build/web-export/project`.

`packageWebExport` zips an export you have already built and tried, and checks
it first:

```sh
cd /path/to/kanama
./gradlew :web-runtime:packageWebExport -PkanamaWebProjectDir=/path/to/your-project
```

The zip lands in `web-runtime/build/distributions/`. It is named after your
project folder and the version of your Kanama checkout, such as
`kanama-web-my-game-v0.5.0.zip`, with `index.html` at its root. The task
builds nothing. It fails when the export is missing, older than the current
build or made from another project, when the export contains a path from your
machine, or when it exceeds itch.io's limits of 500 MB and 1000 files.

### Trying the Web Build with a Demo


The [Kanama demos](https://github.com/falcon4ever/kanama-demos) export the
same way. `-PkanamaWebDemo` picks the demo, and the matching
`-PkanamaWeb<Demo>ProjectDir` points at its folder in the demos checkout:

```sh
cd /path/to/kanama
./gradlew :web-runtime:exportWeb -PkanamaWebDemo=match3 \
  -PkanamaWebMatch3ProjectDir=/path/to/kanama-demos/Starter-Kit-Match3
```

| `-PkanamaWebDemo` | Demo folder | `-PkanamaWebDemo` | Demo folder |
|---|---|---|---|
| `match3` | `Starter-Kit-Match3` | `thirdperson` | `godot-4-3d-third-person-controller` |
| `bunnymark` | `Bunnymark` | `charactercontroller` | `godot-4-3d-character-controller-tutorial` |
| `dodge` | `godot-demo-2d-dodge-the-creeps` | `racing` | `Starter-Kit-Racing` |
| `platformer` | `Starter-Kit-3D-Platformer` | `citybuilder` | `Starter-Kit-City-Builder` |
| `squash` | `godot-demo-3d-squash-the-creeps` | `tpsdemo` | `tps-demo-kanama` |
| `fps` | `Starter-Kit-FPS` | `web3d` | none; it is built into Kanama |

The property name capitalizes the key: `dodge` takes
`-PkanamaWebDodgeProjectDir`. Bunnymark also takes
`-PkanamaWebBunnymarkVariant=BunnymarkSprites`, the variant that runs on the
Web. A demo's export lands in `web-runtime/build/web-export/<demo>/`.
`tpsdemo` is larger than itch.io's 500 MB limit, so `packageWebExport` refuses
it; serve it from your own host.

## Troubleshooting

| Problem | Fix |
| --- | --- |
| `exportWeb` stops with `The Kanama addon has no Web runtime`. | The addon is incomplete, or older than the Web export. Unzip the kit or the store addon of the same Kanama version into the project again. A project built from a source checkout uses the command in [From a Source Checkout](#from-a-source-checkout) instead. |
| `exportWeb` says the bridge `speaks Kanama Web protocol` one number but the processor generated another. | `addons/kanama` mixes two Kanama versions. Unzip the kit or the store addon into the project again. |
| `exportWeb` says `No kotlin-src/ folder`. | The Web export compiles your Kotlin scripts from `kotlin-src/`. Put them there. |
| `exportWeb` says the project `has no main scene`, or that the main scene does not exist or matches no scene file. | Set **Project > Project Settings > Application > Run > Main Scene** to a scene of the project, and save it. |
| The build stops on one of your scripts. | See [Web-Compatible Scripts](#web-compatible-scripts). |
| `exportWeb` says `Godot was not found` or `Godot not found at`. | Add `-PkanamaGodotExecutable=/path/to/godot`. |
| `exportWeb` says the Web export template is not installed or not found. | Install the 4.7.2 export templates from **Editor > Manage Export Templates**, or add `-PkanamaWebTemplateRelease=/path/to/web_nothreads_release.zip`. |
| The first `exportWeb` fails while it downloads Node.js, Yarn or an npm package. | It needs a network connection, also through a proxy Gradle can use. Run it again once the network is back. |
| `exportWeb` fails with `Java heap space` or `OutOfMemoryError`. | The project needs more than 3 GB heap the Web build gives the compiler. Run `./gradlew exportWeb -PkanamaWebHeap=6g`. |
| The page says `Kanama Web protocol mismatch`. | The page's files come from two exports. Run `exportWeb` again and serve the new `build/web-export/`, and clear the browser's cache. |
| The page says `Kanama Web runtime not started`. | The WebAssembly module was not built by `exportWeb`. Run `exportWeb` again. |
| The page loads but the game never starts, on a phone or another computer. | See [Testing on a Phone or Tablet](#testing-on-a-phone-or-tablet). |
| The page is blank when opened from disk. | Serve the folder over HTTP as in [Running the Export](#running-the-export). |

From a source checkout:

| Problem | Fix |
| --- | --- |
| `exportWeb` says `Pass -PkanamaWebProjectDir=...`. | Add `-PkanamaWebProjectDir` with your project folder. Check its spelling: Gradle ignores a property it does not know. |
| `exportWeb` says `Web project not found` or `has no project.godot`. | Pass the folder that holds your `project.godot`, as an absolute path. |
| `exportWeb` says the project `has no kotlin-src/`. | The Web export compiles your Kotlin scripts from `kotlin-src/`. Point it at a Kanama project. |
| `packageWebExport` says the export is stale. | Run `exportWeb` again for the same project, then package. |
| `exportWeb` fails with `Unsupported -PkanamaWebDemo=...`. | Use one of the demo keys in the table, or `-PkanamaWebProjectDir` for your own project. |

## On Other Platforms

On **Windows**, run `.\gradlew.bat` wherever this page says `./gradlew`, and
`python` wherever it says `python3`. Put a command on one line, or end each
line with a backtick (`` ` ``) instead of a backslash, which is the PowerShell
line continuation. On **Linux** and **Windows**, the export finds Godot when it
is on your `PATH` as `godot`; otherwise pass `-PkanamaGodotExecutable`. To zip
an export on Windows, select everything inside `build\web-export` in Explorer
and choose **Send to > Compressed (zipped) folder**.

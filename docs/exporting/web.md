# Export for the Web

!!! note "Web is experimental"
    This release exports only the Kanama demo projects to the Web. It cannot
    export your own game. Use this page to try the Web build with a demo, and
    to keep your scripts ready for the Web. What does not work is listed in
    [Limitations](../reference/limitations.md#web).

The Web backend compiles Kanama project scripts to **Kotlin/Wasm** and runs
them against a Godot Web export through a generated per-call proxy and a
versioned JavaScript bridge (protocol 39). <!-- kanama-claim: protocol -->
In plain terms: your scripts become a WebAssembly module that runs next to
Godot's own in the browser, with no Java. [Web Internals](../contributing/backends/web.md)
explains how the two talk.

## Requirements

- A checkout of the Kanama repository, set up as in
  [Install from a Source Checkout](../getting-started/source-checkout.md).
- A checkout of the [Kanama demos](https://github.com/falcon4ever/kanama-demos).
- Godot 4.7.2 and its export templates, installed from **Editor > Manage
  Export Templates**. The Web export uses `web_nothreads_release.zip` from the
  templates folder:

    | OS | Export templates folder |
    | --- | --- |
    | macOS | `~/Library/Application Support/Godot/export_templates/4.7.2.stable/` |
    | Linux | `~/.local/share/godot/export_templates/4.7.2.stable/` |
    | Windows | `%APPDATA%\Godot\export_templates\4.7.2.stable\` |

- A desktop browser recent enough to run the WebAssembly features Kanama's
  scripts use: Chrome 130, Firefox 141 or Safari 26.5, or newer.

## Trying the Web Build with a Demo

### Exporting a Demo

Run this from the Kanama checkout. `-PkanamaWebDemo` picks the demo, and the
matching `-PkanamaWeb<Demo>ProjectDir` points at its folder in the demos
checkout:

```sh
cd /path/to/kanama
./gradlew --no-daemon -Pkotlin.compiler.execution.strategy=in-process \
  :web-runtime:exportWeb \
  -PkanamaWebDemo=match3 \
  -PkanamaGodotExecutable=/path/to/godot \
  -PkanamaWebTemplateRelease=/path/to/web_nothreads_release.zip \
  -PkanamaWebMatch3ProjectDir=/path/to/kanama-demos/Starter-Kit-Match3
```

Keep `--no-daemon -Pkotlin.compiler.execution.strategy=in-process`: the
Kotlin compiler can run out of memory on this build without them. The export
works on a copy of the demo and leaves the demo folder unchanged. It lands in
`web-runtime/build/web-export/<demo>/`.

| `-PkanamaWebDemo` | Demo folder | `-PkanamaWebDemo` | Demo folder |
|---|---|---|---|
| `match3` | `Starter-Kit-Match3` | `thirdperson` | `godot-4-3d-third-person-controller` |
| `bunnymark` | `Bunnymark` | `charactercontroller` | `godot-4-3d-character-controller-tutorial` |
| `dodge` | `godot-demo-2d-dodge-the-creeps` | `racing` | `Starter-Kit-Racing` |
| `platformer` | `Starter-Kit-3D-Platformer` | `citybuilder` | `Starter-Kit-City-Builder` |
| `squash` | `godot-demo-3d-squash-the-creeps` | `tpsdemo` | `tps-demo-kanama` |
| `fps` | `Starter-Kit-FPS` | `web3d` | none; it is built into Kanama |

The property name capitalizes the key: `dodge` takes
`-PkanamaWebDodgeProjectDir`, `charactercontroller` takes
`-PkanamaWebCharactercontrollerProjectDir`. Bunnymark also takes
`-PkanamaWebBunnymarkVariant=BunnymarkV1Sprites`, the variant that runs on the
Web.

### Running the Export

Serve the export over HTTP. Opening `index.html` from disk does not load the
WebAssembly modules. Any static server works; Kanama's sends the right
`application/wasm` type and listens on this machine only:

```sh
python3 scripts/web/serve_export.py web-runtime/build/web-export/match3
```

It prints `PORT=<n>`. Open `http://127.0.0.1:<n>/` in your browser.

### Testing on a Phone or Tablet

To open the export from another device on your network, serve it over HTTPS.
Godot's Web export only starts from a secure address, and `127.0.0.1` is the
only plain-HTTP address that counts as one:

```sh
python3 scripts/web/serve_export.py --lan --https web-runtime/build/web-export/match3
```

It prints `LAN=https://<this-machine>:<n>/`. Open that address on the device
and accept the warning about the self-signed certificate once. On iPhone and
iPad Safari, tap **Show Details**, then **visit this website**.

### Publishing the Demo

`packageWebExport` zips an export you have already built and tried:

```sh
cd /path/to/kanama
./gradlew --no-daemon -Pkotlin.compiler.execution.strategy=in-process \
  :web-runtime:packageWebExport -PkanamaWebDemo=match3
```

The zip lands in `web-runtime/build/distributions/kanama-web-<demo>-v<version>.zip`,
with `index.html` at its root. The task builds nothing. It fails when the
export is missing or older than the current build, when the export contains a
path from your machine, or when it exceeds itch.io's limits of 500 MB and 1000
files.

To publish on **itch.io**, upload the zip with
[butler](https://itch.io/docs/butler/):

```sh
butler push web-runtime/build/distributions/kanama-web-match3-v<version>.zip \
  <user>/<game>:html5
```

On the game's edit page, set the upload to **This file will be played in the
browser**. Leave **SharedArrayBuffer support** off: the export uses a single
thread and needs no special headers.

To publish on **any other static host**, unzip the zip into a folder the host
serves over HTTPS, with the `application/wasm` content type for `.wasm` files.

## Web-Compatible Scripts

The Web build compiles the same `kotlin-src/` folder as desktop, and most
scripts need no change. If you plan to ship on the Web later, avoid what the
Web build stops on, with an error that names the script:

- uses Java classes such as `java.io.File`, which do not exist in the browser;
- compares raw handles with `handle.address()`; use `isSameInstance()`;
- calls a Godot method the Web build does not have;
- gives an exported property a default that is not a literal or a constant
  expression. `Mathf.PI / 3.0` works; another property's value does not;
- exports a property type the Web build does not carry.

For a script that must differ on the Web, a file with the same name in
`web/kotlin-src/` replaces the shared file in the Web build only. A
desktop-only script is left out by listing its path, relative to
`kotlin-src/`, in `web/kotlin-src-excludes.txt`, one per line. The demos use
both.

## Troubleshooting

| Problem | Fix |
| --- | --- |
| The page loads but the game never starts, on a phone or another computer. | Serve with `--lan --https`. A plain-HTTP network address is not secure, so Godot does not start. |
| The page is blank when opened from disk. | Serve the folder over HTTP as in [Running the Export](#running-the-export). |
| The Gradle build runs out of memory. | Add `--no-daemon -Pkotlin.compiler.execution.strategy=in-process`. |
| `exportWeb` fails with `Unsupported -PkanamaWebDemo=...`. | Use one of the demo keys in the table. |
| `packageWebExport` says the export is stale. | Run `exportWeb` again, then package. |
| `packageWebExport` rejects `tpsdemo`. | Its assets exceed itch.io's 500 MB limit. Serve it from your own host instead. |
| Enemies pile up and never leave the screen. | See the open defect under [Limitations](../reference/limitations.md#web). |

## On Other Platforms

On **Windows**, run `.\gradlew.bat` wherever this page says `./gradlew`.

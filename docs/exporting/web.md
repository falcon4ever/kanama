# Export for the Web

!!! note "Web is experimental"
    The Web export builds the Kanama demo projects from a checkout of the
    Kanama repository. It cannot export your own project. Use it to see
    how a Kanama game runs in a browser, and to keep your scripts ready for
    the Web; see [Known Limitations](#known-limitations).

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
- Godot 4.7.2, and its **`web_nothreads_release.zip`** export template. Install
  the export templates from **Editor > Manage Export Templates**; the file is
  in Godot's `export_templates/4.7.2.stable/` folder.
- A recent Chrome, Firefox or Safari. The browser needs WebAssembly garbage
  collection and exception handling; the minimum versions are on
  [Requirements and Platform Status](../reference/version-support.md).

## Exporting a Demo

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

## Running the Export

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

## Publishing

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

### itch.io

Upload the zip with [butler](https://itch.io/docs/butler/):

```sh
butler push web-runtime/build/distributions/kanama-web-match3-v<version>.zip \
  <user>/<game>:html5
```

On the game's edit page, set the upload to **This file will be played in the
browser**. Leave **SharedArrayBuffer support** off: the export uses a single
thread and needs no special headers.

### Any Static HTTPS Host

Unzip the zip into any folder a static host serves. The host needs two things:

- HTTPS, for the same reason as on a phone.
- The `application/wasm` content type for `.wasm` files.

No special headers and no server code are needed.

## Writing Scripts That Also Run on the Web

The Web build compiles the same `kotlin-src/` folder as desktop. Most scripts
need no change. A script fails the Web build, with an error that names it,
when it:

- uses Java classes such as `java.io.File`, which do not exist in the browser;
- compares raw handles with `handle.address()`; use `isSameInstance()`;
- calls a Godot method the Web build does not have;
- gives an exported property a default that is not a literal or a constant
  expression. `Mathf.PI / 3.0` works; another property's value does not;
- exports a property type the Web build does not carry.

For a script that must differ on the Web, put a file with the same name in
`web/kotlin-src/`. It replaces the shared file in the Web build only. To leave
a desktop-only script out of the Web build, list its path relative to
`kotlin-src/` in `web/kotlin-src-excludes.txt`, one per line.

A property initializer may call Godot, as `var x = ...` can in GDScript. On
the Web the node is usually already in the scene tree when it runs.

## Troubleshooting

| Problem | Fix |
| --- | --- |
| The page loads but the game never starts, on a phone or another computer. | Serve with `--lan --https`. A plain-HTTP network address is not secure, so Godot does not start. |
| The page is blank when opened from disk. | Serve the folder over HTTP as in [Running the Export](#running-the-export). |
| The Gradle build runs out of memory. | Add `--no-daemon -Pkotlin.compiler.execution.strategy=in-process`. |
| `exportWeb` fails with `Unsupported -PkanamaWebDemo=...`. | Use one of the demo keys in the table. |
| `packageWebExport` says the export is stale. | Run `exportWeb` again, then package. |
| `packageWebExport` rejects `tpsdemo`. | Its assets exceed itch.io's 500 MB limit. Serve it from your own host instead. |
| Enemies pile up and never leave the screen. | See the open defect under [Known Limitations](#known-limitations). |

## Known Limitations

- **Your own project cannot be exported.** The export runs on the demo
  projects listed above, from a Kanama checkout. No packaged addon exports to
  the Web.
- **Single thread.** The export uses Godot's `nothreads` template: no threads,
  no SharedArrayBuffer, no special headers. In exchange, any static HTTPS host
  can serve the game, itch.io included.
- **Compatibility renderer only.** Forward+ and Mobile are not available on
  the Web, so features that need them, such as volumetric fog and global
  illumination, are missing. A shader can hitch once the first time it
  compiles.
- **No multiplayer.** Browsers have no ENet, and Godot's WebSocket and WebRTC
  peers are not connected to Kanama on the Web. The multiplayer API answers as
  an offline single player, as desktop does before a game sets a peer, so
  shared scripts that read it still run.
- **No editor and no hot reload.** Every script change needs a new build and
  export.
- **Safari on iPhone and iPad** runs the export, but Kanama does not test it
  automatically or name a minimum version.
- Script-level limits, such as which callbacks run and what errors report,
  are in [Limitations](../reference/limitations.md#web).
<!-- KANAMA-BLOCKED(since:2026-07-28, issue:370): the unexplained-defect entry below -->
- **One defect is open ([issue #370](https://github.com/falcon4ever/kanama/issues/370)).**
  On one Linux host, enemies spawned in two demos leave the screen, but
  `VisibleOnScreenNotifier2D.screen_exited` never fires, so they are never
  freed. No cause in Kanama has been found, and none is ruled out. If
  enemies pile up in a Web export, check the issue before you look for a bug
  in your project.

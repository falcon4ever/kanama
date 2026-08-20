#!/usr/bin/env python3
"""Fail when the Web protocol version is not the SAME number everywhere it is written.

The protocol pins the generated GDScript proxies to the JS bridge, and the two compare it
at startup. It is written in several places by hand, and they must agree:

    processor/.../WebScriptCodeEmitter.kt   const val PROTOCOL_VERSION      <- the source
    web-runtime/.../kanama-web-bridge.js    KANAMA_WEB_PROTOCOL_VERSION
    processor/src/test/.../WebScriptCodeEmitterTest.kt   three assertions

**Why this gate exists.** Bumping the emitter but not the bridge does not fail loudly: the
bridge rejects the proxy at load, the page never finishes booting, and the smoke reports
"Kotlin/Wasm <demo> scene did not become ready" -- which reads like a hang, a slow host or
a broken export. It cost two debugging rounds (protocol 20 and 21) before the cause was
obvious, and BOTH times the bump itself was correct.

The test pins fail loudly and the two marked doc claims are covered by
check_doc_claims.py, so this exists for the bridge constant above all.

Not a substitute for a single source of truth -- the honest fix is for the bridge to read
the number rather than restate it. Until then, this makes disagreement loud at build time
instead of silent at page load.
"""

from __future__ import annotations

import pathlib
import re
import sys

ROOT = pathlib.Path(__file__).resolve().parent.parent

EMITTER = ROOT / "processor/src/main/kotlin/net/multigesture/kanama/processor/WebScriptCodeEmitter.kt"
BRIDGE = ROOT / "web-runtime/src/webSpikeGodot/assets/kanama-web-bridge.js"
TEST = ROOT / "processor/src/test/kotlin/net/multigesture/kanama/processor/WebScriptCodeEmitterTest.kt"

SITES = [
    (EMITTER, re.compile(r"const val PROTOCOL_VERSION = (\d+)"), "emitter constant (the source)"),
    (BRIDGE, re.compile(r"const KANAMA_WEB_PROTOCOL_VERSION = (\d+);"), "bridge constant"),
    (TEST, re.compile(r"PROTOCOL_VERSION: Int = (\d+)"), "test: generated constant"),
    (TEST, re.compile(r"kanama-web-protocol=(\d+)"), "test: proxy manifest header"),
    (TEST, re.compile(r'protocolVersion\\": (\d+)'), "test: manifest body"),
]


def main() -> int:
    found: list[tuple[str, int, str]] = []
    for path, pattern, label in SITES:
        if not path.exists():
            print(f"[protocol_pins] FAIL missing file for {label}: {path}")
            return 2
        matches = pattern.findall(path.read_text(encoding="utf-8"))
        if not matches:
            # A pin that stops matching is worse than a mismatched one: it silently drops
            # out of the check and nothing says so.
            print(f"[protocol_pins] FAIL no match for {label} in {path.relative_to(ROOT)} "
                  f"-- the pattern has drifted, so this site is no longer checked")
            return 2
        for value in matches:
            found.append((label, int(value), str(path.relative_to(ROOT))))

    versions = {value for _label, value, _path in found}
    if len(versions) != 1:
        print("[protocol_pins] FAIL the protocol version disagrees across its pins:")
        for label, value, path in sorted(found, key=lambda item: item[1]):
            print(f"    {value:>4}  {label}  ({path})")
        print("  Bumping the emitter without the bridge does NOT fail loudly -- the page")
        print("  never finishes booting and the smoke reports 'scene did not become ready'.")
        return 1

    version = versions.pop()
    print(f"[protocol_pins] PASS protocol {version} agrees across {len(found)} pins")
    return 0


if __name__ == "__main__":
    sys.exit(main())

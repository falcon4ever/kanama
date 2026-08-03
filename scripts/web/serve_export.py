#!/usr/bin/env python3
"""Serve an already-built Web export over an ephemeral localhost HTTP port.

The Web export-smoke shell owns this server's lifecycle. It binds 127.0.0.1 on
an OS-chosen port (port 0), prints the resolved port as ``PORT=<n>`` on stdout so
the caller can construct the artifact URL without racing on a fixed port, then
serves the export directory read-only until it is terminated.

The server never mutates the served tree. COOP/COEP headers are intentionally
NOT sent: the preview backend is the single-thread Compatibility renderer, which
does not require cross-origin isolation.

Usage:
    python3 serve_export.py [--lan] [--https] <export-dir>

``--https`` serves over TLS with a cached self-signed certificate. It exists
for phone testing: Godot's Web export requires a secure context, ``127.0.0.1``
is one but a LAN address over plain HTTP is not, so ``--lan`` without
``--https`` cannot start the engine on a device (task 70).
"""

from __future__ import annotations

import http.server
import os
import socket
import ssl
import subprocess
import sys
import threading
import time
from functools import partial


class _QuietHandler(http.server.SimpleHTTPRequestHandler):
    """SimpleHTTPRequestHandler with the correct Wasm MIME type and quiet logs."""

    extensions_map = {
        **http.server.SimpleHTTPRequestHandler.extensions_map,
        ".wasm": "application/wasm",
        ".js": "text/javascript",
        ".mjs": "text/javascript",
    }

    def log_message(self, *args: object) -> None:  # noqa: D401 - silence per-request noise
        # The shell captures failures via the driver/result; per-request access
        # logging would only add noise to the preserved diagnostics.
        return

    # In --lan mode, report the first request from each remote device with its
    # user-agent. A hand-validation pass has to record WHAT it ran on, and asking
    # a human to transcribe an iOS build number is how evidence goes wrong; the
    # device announces it on every request anyway.
    seen_remotes: set = set()
    announce_remotes: bool = False

    def handle_one_request(self) -> None:
        super().handle_one_request()
        if not self.announce_remotes:
            return
        remote = self.client_address[0]
        if remote in ("127.0.0.1", "::1") or remote in self.seen_remotes:
            return
        self.seen_remotes.add(remote)
        agent = self.headers.get("User-Agent", "(no user-agent)")
        print(f"DEVICE={remote} {agent}", flush=True)

    def end_headers(self) -> None:
        # No-store keeps re-runs from serving a stale export out of a browser
        # cache; cache-busting query strings are a belt-and-braces second layer.
        self.send_header("Cache-Control", "no-store")
        super().end_headers()


def _lan_address() -> str | None:
    """Best-effort LAN address of this machine, for the --lan banner."""
    try:
        with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as probe:
            # No packet is sent; connect() on UDP just picks the outbound route,
            # which is the interface a phone on the same network would reach.
            probe.connect(("192.0.2.1", 9))  # TEST-NET-1, deliberately unroutable
            return probe.getsockname()[0]
    except OSError:
        return None


def _ensure_self_signed_cert(address: str) -> tuple[str, str]:
    """Return (cert, key) paths for ``address``, generating them if missing.

    Certificates are cached per address under ~/.cache/kanama-web-serve so a
    device that accepted the certificate once does not see a fresh warning on
    every run. Self-signed is the point: Godot's Web export requires a secure
    context (task 70 — over plain LAN HTTP it never starts), and a tap-through
    warning on the device is the cheap way to get one on a LAN.
    """
    cache_dir = os.path.join(os.path.expanduser("~"), ".cache", "kanama-web-serve")
    os.makedirs(cache_dir, exist_ok=True)
    cert = os.path.join(cache_dir, f"cert-{address}.pem")
    key = os.path.join(cache_dir, f"key-{address}.pem")
    if os.path.isfile(cert) and os.path.isfile(key):
        return cert, key
    san = f"subjectAltName=IP:{address},IP:127.0.0.1,DNS:localhost"
    try:
        subprocess.run(
            [
                "openssl", "req", "-x509", "-newkey", "rsa:2048",
                "-keyout", key, "-out", cert, "-days", "30", "-nodes",
                "-subj", f"/CN={address}", "-addext", san,
            ],
            check=True,
            capture_output=True,
        )
    except FileNotFoundError:
        print(
            "serve_export: --https needs the openssl CLI to generate a"
            " self-signed certificate and it was not found on PATH",
            file=sys.stderr,
        )
        raise SystemExit(2) from None
    except subprocess.CalledProcessError as error:
        stderr = error.stderr.decode(errors="replace") if error.stderr else ""
        print(f"serve_export: certificate generation failed:\n{stderr}", file=sys.stderr)
        raise SystemExit(2) from None
    return cert, key


def main(argv: list[str]) -> int:
    lan = "--lan" in argv
    https = "--https" in argv
    args = [arg for arg in argv if arg not in ("--lan", "--https")]
    if len(args) != 1:
        print("usage: serve_export.py [--lan] [--https] <export-dir>", file=sys.stderr)
        return 2

    export_dir = args[0]
    handler = partial(_QuietHandler, directory=export_dir)
    _QuietHandler.announce_remotes = lan

    # Loopback by default: the automated gates drive a browser on this machine,
    # and an export server should not be reachable from the network unless it was
    # asked for. --lan binds every interface so a phone or tablet on the same
    # network can load the export -- the only way to hand-validate iOS/iPadOS
    # WebKit, which safaridriver cannot reach (task 70).
    host = "0.0.0.0" if lan else "127.0.0.1"

    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as probe:
        probe.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        probe.bind((host, 0))
        port = probe.getsockname()[1]

    server = http.server.ThreadingHTTPServer((host, port), handler)

    scheme = "http"
    if https:
        # A phone can only run the export over a secure context (Godot refuses,
        # or dies in audio-worklet init, without one — task 70), and a LAN IP is
        # never one over plain HTTP. 127.0.0.1 is always secure, so --https is
        # only *required* together with --lan; it works alone for parity.
        cert_address = (_lan_address() or "127.0.0.1") if lan else "127.0.0.1"
        cert, key = _ensure_self_signed_cert(cert_address)
        context = ssl.SSLContext(ssl.PROTOCOL_TLS_SERVER)
        context.load_cert_chain(cert, key)
        server.socket = context.wrap_socket(server.socket, server_side=True)
        scheme = "https"

    # The smoke shell owns this server and stops it in its cleanup trap — but if
    # the shell is SIGKILLed or its terminal goes away, nothing reaches us and
    # this process holds its port forever. Watch for being reparented away from
    # the shell that launched us and shut down.
    def _exit_when_orphaned(launch_parent: int) -> None:
        while os.getppid() == launch_parent:
            time.sleep(1)
        server.shutdown()

    threading.Thread(
        target=_exit_when_orphaned, args=(os.getppid(),), daemon=True
    ).start()
    # Announce the resolved port on a single line the shell greps for, then flush
    # so the caller never blocks waiting on a buffered pipe.
    print(f"PORT={port}", flush=True)
    if lan:
        address = _lan_address()
        where = (
            f"{scheme}://{address}:{port}/"
            if address
            else f"{scheme}://<this-machine>:{port}/"
        )
        print(f"LAN={where}", flush=True)
        print(f"serve_export: open {where} on the device", flush=True)
        if https:
            print(
                "serve_export: the certificate is self-signed -- accept the"
                " device's one-time warning (iOS Safari: Show Details -> visit"
                " this website)",
                flush=True,
            )
        else:
            print(
                "serve_export: NOTE plain HTTP is not a secure context on a"
                " LAN address; Godot Web exports will not start on the device."
                " Pass --https for phone testing (task 70).",
                flush=True,
            )
    try:
        server.serve_forever()
    except KeyboardInterrupt:
        pass
    finally:
        server.server_close()
    return 0


if __name__ == "__main__":
    raise SystemExit(main(sys.argv[1:]))

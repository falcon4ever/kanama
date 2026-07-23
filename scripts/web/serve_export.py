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
    python3 serve_export.py <export-dir>
"""

from __future__ import annotations

import http.server
import socket
import sys
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

    def end_headers(self) -> None:
        # No-store keeps re-runs from serving a stale export out of a browser
        # cache; cache-busting query strings are a belt-and-braces second layer.
        self.send_header("Cache-Control", "no-store")
        super().end_headers()


def main(argv: list[str]) -> int:
    if len(argv) != 1:
        print("usage: serve_export.py <export-dir>", file=sys.stderr)
        return 2

    export_dir = argv[0]
    handler = partial(_QuietHandler, directory=export_dir)

    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as probe:
        probe.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        probe.bind(("127.0.0.1", 0))
        port = probe.getsockname()[1]

    server = http.server.ThreadingHTTPServer(("127.0.0.1", port), handler)
    # Announce the resolved port on a single line the shell greps for, then flush
    # so the caller never blocks waiting on a buffered pipe.
    print(f"PORT={port}", flush=True)
    try:
        server.serve_forever()
    except KeyboardInterrupt:
        pass
    finally:
        server.server_close()
    return 0


if __name__ == "__main__":
    raise SystemExit(main(sys.argv[1:]))

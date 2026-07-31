#!/usr/bin/env python3
"""Task 71 control: does VisibleOnScreenNotifier2D fire in a Godot Web export?

Kanama is not involved. This serves a **plain GDScript** export (no addon, no
bridge, no generated proxies), launches a browser at it, and records the beacons
the scene fetches back. If the signal fails here too, the behaviour belongs to
Godot-on-that-host and nothing in this repository can fix it; if it fires here
while dodge's mobs never free, the difference is ours and task 71 reopens on our
side.

Deliberately protocol-free: no CDP, no WebDriver. The page reports by fetching
``/probe/<message>``, so the only thing needed is a browser that can open a URL.
That keeps the control independent of the very harness whose behaviour is in
question.

Usage:
    python3 visibility_probe.py --export-dir <dir> --browser <path> [--engine chrome]
                               [--timeout 60] [--profile-dir <dir>]

Exit codes: 0 the signal fired, 1 it did not, 2 the probe could not run.
"""

from __future__ import annotations

import argparse
import http.server
import os
import shutil
import socket
import subprocess
import sys
import tempfile
import threading
import time

BEACONS: list[tuple[float, str]] = []
_STARTED = time.monotonic()


class _ProbeHandler(http.server.SimpleHTTPRequestHandler):
    def log_message(self, *args) -> None:  # noqa: D401 - quiet by design
        return

    def do_GET(self) -> None:  # noqa: N802 - stdlib naming
        if self.path.startswith("/probe/"):
            BEACONS.append((time.monotonic() - _STARTED, self.path[len("/probe/"):]))
            self.send_response(204)
            self.end_headers()
            return
        super().do_GET()

    def end_headers(self) -> None:
        self.send_header("Cache-Control", "no-store")
        super().end_headers()


def main(argv: list[str]) -> int:
    parser = argparse.ArgumentParser(description="Godot visibility-notifier control probe.")
    parser.add_argument("--export-dir", required=True)
    parser.add_argument("--browser", required=True, help="browser binary to launch")
    parser.add_argument("--engine", default="chrome", choices=["chrome", "firefox"])
    parser.add_argument("--timeout", type=int, default=60)
    args = parser.parse_args(argv)

    if not os.path.isfile(os.path.join(args.export_dir, "index.html")):
        print(f"visibility_probe: no export at {args.export_dir}", file=sys.stderr)
        return 2
    if not os.path.exists(args.browser):
        print(f"visibility_probe: no browser at {args.browser}", file=sys.stderr)
        return 2

    handler = lambda *a, **kw: _ProbeHandler(*a, directory=args.export_dir, **kw)  # noqa: E731
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as probe:
        probe.bind(("127.0.0.1", 0))
        port = probe.getsockname()[1]
    server = http.server.ThreadingHTTPServer(("127.0.0.1", port), handler)
    threading.Thread(target=server.serve_forever, daemon=True).start()
    url = f"http://127.0.0.1:{port}/"
    print(f"visibility_probe: serving {args.export_dir} at {url}")

    profile = tempfile.mkdtemp(prefix="kanama-probe-")
    if args.engine == "chrome":
        # Same software-GL opt-ins the Chrome smoke driver uses, so the control
        # runs on the same renderer path as the thing it is controlling for.
        cmd = [
            args.browser, "--headless=new", "--enable-unsafe-swiftshader",
            "--use-angle=swiftshader", "--no-first-run", "--no-default-browser-check",
            "--use-mock-keychain", "--password-store=basic",
            "--force-device-scale-factor=1", "--window-size=1280,900",
            f"--user-data-dir={profile}", url,
        ]
    else:
        # The full pref set the Firefox smoke driver uses. An earlier version set
        # only two of these and the page never booted on a CI runner -- zero
        # beacons, which is INCONCLUSIVE, not a negative result.
        with open(os.path.join(profile, "user.js"), "w", encoding="utf-8") as handle:
            handle.write('user_pref("webgl.force-enabled", true);\n')
            handle.write('user_pref("webgl.disabled", false);\n')
            handle.write('user_pref("gfx.webrender.software", true);\n')
            handle.write('user_pref("dom.webgl.software.render", true);\n')
        cmd = [args.browser, "--headless", "--no-remote", "--profile", profile, url]

    # Keep the browser's own output: when nothing is reported, its stderr is the
    # only thing that says whether the browser or the page was at fault.
    browser_log = tempfile.NamedTemporaryFile(  # noqa: SIM115 - lives for the run
        prefix="kanama-probe-browser-", suffix=".log", delete=False, mode="w+"
    )
    browser = subprocess.Popen(cmd, stdout=browser_log, stderr=subprocess.STDOUT)
    deadline = time.monotonic() + args.timeout
    verdict = None
    try:
        while time.monotonic() < deadline:
            for _, message in BEACONS:
                if message.startswith("exited"):
                    verdict = ("FIRED", message)
                    break
                if message.startswith("timeout"):
                    verdict = ("NEVER FIRED", message)
                    break
            if verdict:
                break
            time.sleep(0.25)
    finally:
        browser.terminate()
        try:
            browser.wait(timeout=10)
        except subprocess.TimeoutExpired:
            browser.kill()
        server.shutdown()
        shutil.rmtree(profile, ignore_errors=True)

    print(f"visibility_probe: {len(BEACONS)} beacon(s) on {args.engine}")
    for at, message in BEACONS:
        print(f"  {at:6.1f}s  {message}")

    if verdict is None:
        print("visibility_probe: INCONCLUSIVE -- the page reported nothing, so the "
              "export never ran. This says NOTHING about the notifier; the browser "
              "or the page is at fault.", file=sys.stderr)
        browser_log.flush()
        try:
            with open(browser_log.name, encoding="utf-8", errors="replace") as handle:
                tail = handle.read()[-2000:]
            if tail.strip():
                print("visibility_probe: browser output tail:", file=sys.stderr)
                print(tail, file=sys.stderr)
        except OSError:
            pass
        print(f"visibility_probe: browser exit code {browser.returncode}", file=sys.stderr)
        return 2
    label, message = verdict
    print(f"visibility_probe: screen_exited {label} ({message})")
    return 0 if label == "FIRED" else 1


if __name__ == "__main__":
    raise SystemExit(main(sys.argv[1:]))

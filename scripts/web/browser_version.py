#!/usr/bin/env python3
"""Parse a browser version out of the user-agent a Web smoke driver recorded.

The drivers write ``navigator.userAgent`` verbatim into ``browser.version`` of
the result envelope -- the whole string, deliberately, because that is the raw
evidence. Reading a *number* out of it is a separate concern, and one that has
exactly one correct implementation per engine; it lives here so the matrix
summary (Task 60h) and the version-floor gate agree by construction.

Parsing is strict: an unrecognizable user-agent returns ``None`` rather than a
guess, and a caller that needs a version treats ``None`` as a failure.

Usage:
    python3 browser_version.py <engine> <user-agent>
"""

from __future__ import annotations

import re
import sys

# Ordered per engine: the first pattern that matches wins. Headless Chrome
# reports "HeadlessChrome/150.0.0.0", which must be tried before the generic
# "Chrome/" (headed Chrome UAs contain only the latter).
_PATTERNS: dict[str, tuple[re.Pattern[str], ...]] = {
    "chrome": (
        re.compile(r"HeadlessChrome/(\d+(?:\.\d+)*)"),
        re.compile(r"Chrome/(\d+(?:\.\d+)*)"),
        re.compile(r"Chromium/(\d+(?:\.\d+)*)"),
    ),
    "firefox": (re.compile(r"Firefox/(\d+(?:\.\d+)*)"),),
    # Safari reports the marketing version in "Version/26.5" and the engine
    # build in the trailing "Safari/605.1.15"; the marketing version is what
    # release notes and support tables talk about.
    "safari": (re.compile(r"Version/(\d+(?:\.\d+)*)\s+.*\bSafari/"),),
}


def parse_version(engine: str, user_agent: str | None) -> str | None:
    """Return the dotted browser version, or None if it cannot be read."""
    if not user_agent:
        return None
    for pattern in _PATTERNS.get(engine, ()):
        match = pattern.search(user_agent)
        if match:
            return match.group(1)
    return None


def major(version: str | None) -> int | None:
    """Return the leading integer component of a dotted version."""
    if not version:
        return None
    head = version.split(".", 1)[0]
    return int(head) if head.isdigit() else None


def version_tuple(version: str) -> tuple[int, ...]:
    """Comparable form of a dotted version ('26.5' -> (26, 5))."""
    parts: list[int] = []
    for part in version.split("."):
        if not part.isdigit():
            break
        parts.append(int(part))
    return tuple(parts)


def main(argv: list[str]) -> int:
    if len(argv) != 2:
        print(f"usage: {sys.argv[0]} <engine> <user-agent>", file=sys.stderr)
        return 2
    engine, user_agent = argv
    version = parse_version(engine, user_agent)
    if version is None:
        print(f"browser_version: could not read a {engine} version from: {user_agent}", file=sys.stderr)
        return 1
    print(version)
    return 0


if __name__ == "__main__":
    raise SystemExit(main(sys.argv[1:]))

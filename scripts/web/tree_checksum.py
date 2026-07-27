#!/usr/bin/env python3
"""Deterministic checksum over a directory tree.

Used as immutability evidence by the Web gates: `web_export_smoke.sh` proves the
served export was not mutated by serving/driving it, and
`web_fresh_checkout_smoke.sh` proves exporting never writes into the demo source
tree. Both must agree byte-for-byte, so the algorithm lives in one file.

The digest covers sorted (relative-path, contents) pairs, so it is stable across
filesystems and platforms (macOS `shasum` vs Linux `sha256sum` never enter into
it). Symlinks are hashed as their target string rather than followed, so a
dangling or re-pointed link is a difference.

Usage:
    python3 tree_checksum.py <dir> [--exclude NAME]...

`--exclude` drops a directory *name* anywhere in the tree (e.g. `.git`), which
is what callers need for source trees carrying VCS or engine caches.
"""

from __future__ import annotations

import hashlib
import os
import sys


def tree_checksum(root: str, exclude: frozenset[str] = frozenset()) -> str:
    digest = hashlib.sha256()
    for dirpath, dirnames, filenames in os.walk(root):
        dirnames[:] = sorted(name for name in dirnames if name not in exclude)
        for name in sorted(filenames):
            if name in exclude:
                continue
            path = os.path.join(dirpath, name)
            rel = os.path.relpath(path, root)
            digest.update(rel.encode("utf-8"))
            digest.update(b"\0")
            if os.path.islink(path):
                digest.update(b"symlink:")
                digest.update(os.readlink(path).encode("utf-8"))
            else:
                with open(path, "rb") as handle:
                    for chunk in iter(lambda: handle.read(65536), b""):
                        digest.update(chunk)
            digest.update(b"\0")
    return digest.hexdigest()


def main(argv: list[str]) -> int:
    if not argv:
        print("usage: tree_checksum.py <dir> [--exclude NAME]...", file=sys.stderr)
        return 2
    root = argv[0]
    exclude: set[str] = set()
    rest = argv[1:]
    while rest:
        if rest[0] != "--exclude" or len(rest) < 2:
            print(f"unexpected argument: {rest[0]}", file=sys.stderr)
            return 2
        exclude.add(rest[1])
        rest = rest[2:]
    if not os.path.isdir(root):
        print(f"not a directory: {root}", file=sys.stderr)
        return 2
    print(tree_checksum(root, frozenset(exclude)))
    return 0


if __name__ == "__main__":
    raise SystemExit(main(sys.argv[1:]))

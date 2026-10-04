#!/usr/bin/env python3
"""Validate saved Godot scene connections against Kanama script methods.

This catches a common GDScript-to-Kanama porting error:

    [connection ... to="HUD" method="_on_coin_collected"]

while the Kotlin script exposes only `on_coin_collected`: every public function of a
script is registered under its snake_case name (`fun onCoinCollected()` is
`on_coin_collected`), so a connection the editor saved as `_on_coin_collected` needs
`@GodotName("_on_coin_collected")` on the function (Kanama 0.5, task 133).
"""

from __future__ import annotations

import argparse
import re
import sys
from dataclasses import dataclass
from pathlib import Path


EXT_RESOURCE_RE = re.compile(r'^\[ext_resource\b(?P<body>.*)\]$')
NODE_RE = re.compile(r'^\[node\b(?P<body>.*)\]$')
CONNECTION_RE = re.compile(r'^\[connection\b(?P<body>.*)\]$')
EXT_RESOURCE_REF_RE = re.compile(r'^ExtResource\("(?P<id>[^"]+)"\)$')
GODOT_NAME_RE = re.compile(r'@GodotName\(\s*(?:name\s*=\s*)?"(?P<name>[^"]+)"\s*\)')
ANNOTATION_RE = re.compile(r"@(?P<name>[A-Za-z_][A-Za-z0-9_.]*)(?:\([^)]*\))?")
FUN_RE = re.compile(
    r"^(?P<prefix>[^/\"]*?)\bfun\s+(?:<[^>]*>\s*)?(?P<name>[A-Za-z_][A-Za-z0-9_]*)\s*\("
)
NOT_REGISTERED_MODIFIERS = {"private", "internal", "protected", "suspend"}
# Functions wired to Godot some other way than as a registered method by their own name.
ROLE_ANNOTATIONS = {
    "OnReady",
    "OnEnterTree",
    "OnExitTree",
    "OnProcess",
    "OnPhysicsProcess",
    "OnInput",
    "OnUnhandledInput",
    "OnShortcutInput",
    "OnUnhandledKeyInput",
    "OverrideVirtual",
    "Signal",
    "ExportToolButton",
}


@dataclass(frozen=True)
class Connection:
    scene: Path
    line: int
    signal: str
    source: str
    target: str
    method: str


@dataclass
class NodeInfo:
    path: str
    script: str | None = None


def parse_attrs(body: str) -> dict[str, str]:
    attrs: dict[str, str] = {}
    for match in re.finditer(r'([A-Za-z0-9_./-]+)=("[^"]*"|\S+)', body):
        key = match.group(1)
        value = match.group(2)
        if value.startswith('"') and value.endswith('"'):
            value = value[1:-1]
        attrs[key] = value
    return attrs


def normalize_node_path(parent: str | None, name: str | None) -> str:
    if not name:
        return "."
    if not parent:
        return "."
    if parent == ".":
        return name
    return f"{parent}/{name}"


def camel_to_snake(name: str) -> str:
    """The processor's rule (KanamaProcessor.camelToSnake)."""
    out = []
    for index, char in enumerate(name):
        if index > 0 and char.isupper():
            out.append("_")
        out.append(char.lower())
    return "".join(out)


def script_methods(script_path: Path) -> set[str]:
    """Godot names of the functions the script registers: every public function (task 133 B),
    under `@GodotName("...")` when it has one, else its snake_case name. A source heuristic,
    good enough for a lint: it reads annotations on the declaration line and the lines above."""
    try:
        lines = script_path.read_text(encoding="utf-8").splitlines()
    except OSError:  # justified: an unreadable script registers no methods, so every connection to it is reported missing
        return set()

    methods: set[str] = set()
    pending_annotations: list[str] = []
    pending_godot_name: str | None = None
    for line in lines:
        stripped = line.strip()
        fun = FUN_RE.search(line)
        if fun is None:
            if stripped.startswith("@"):
                pending_annotations += [m.group("name").rsplit(".", 1)[-1] for m in ANNOTATION_RE.finditer(stripped)]
                named = GODOT_NAME_RE.search(stripped)
                if named:
                    pending_godot_name = named.group("name")
            elif stripped and not stripped.startswith("//"):
                pending_annotations = []
                pending_godot_name = None
            continue
        prefix = fun.group("prefix")
        annotations = pending_annotations + [
            m.group("name").rsplit(".", 1)[-1] for m in ANNOTATION_RE.finditer(prefix)
        ]
        named = GODOT_NAME_RE.search(prefix)
        godot_name = named.group("name") if named else pending_godot_name
        words = set(ANNOTATION_RE.sub(" ", prefix).split())
        pending_annotations = []
        pending_godot_name = None
        if words & NOT_REGISTERED_MODIFIERS or set(annotations) & ROLE_ANNOTATIONS:
            continue
        methods.add(godot_name or camel_to_snake(fun.group("name")))
    return methods


def resolve_script(project_dir: Path, resource_path: str) -> Path | None:
    if resource_path.startswith("res://"):
        return project_dir / resource_path.removeprefix("res://")
    return None


def parse_scene(project_dir: Path, scene: Path) -> tuple[dict[str, NodeInfo], list[Connection]]:
    resources: dict[str, str] = {}
    nodes: dict[str, NodeInfo] = {}
    connections: list[Connection] = []
    current_node: NodeInfo | None = None

    for line_number, raw in enumerate(scene.read_text(encoding="utf-8").splitlines(), start=1):
        line = raw.strip()
        resource = EXT_RESOURCE_RE.match(line)
        if resource:
            attrs = parse_attrs(resource.group("body"))
            if attrs.get("type") == "Script" and "id" in attrs and "path" in attrs:
                resources[attrs["id"]] = attrs["path"]
            current_node = None
            continue

        node = NODE_RE.match(line)
        if node:
            attrs = parse_attrs(node.group("body"))
            path = normalize_node_path(attrs.get("parent"), attrs.get("name"))
            current_node = NodeInfo(path=path)
            nodes[path] = current_node
            continue

        connection = CONNECTION_RE.match(line)
        if connection:
            attrs = parse_attrs(connection.group("body"))
            if {"signal", "from", "to", "method"} <= attrs.keys():
                connections.append(
                    Connection(
                        scene=scene,
                        line=line_number,
                        signal=attrs["signal"],
                        source=attrs["from"],
                        target=attrs["to"],
                        method=attrs["method"],
                    )
                )
            current_node = None
            continue

        if current_node and line.startswith("script = "):
            value = line.removeprefix("script = ").strip()
            ref = EXT_RESOURCE_REF_RE.match(value)
            if ref:
                current_node.script = resources.get(ref.group("id"))

    return nodes, connections


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("project_dir", type=Path, help="Godot project directory")
    args = parser.parse_args()

    project_dir = args.project_dir.resolve()
    if not project_dir.is_dir():
        print(f"[scene_connection_lint] not a directory: {project_dir}", file=sys.stderr)
        return 2

    method_cache: dict[Path, set[str]] = {}
    failures: list[str] = []
    checked = 0
    skipped = 0

    for scene in sorted(project_dir.rglob("*.tscn")):
        nodes, connections = parse_scene(project_dir, scene)
        for connection in connections:
            target = nodes.get(connection.target)
            if not target or not target.script:
                skipped += 1
                continue

            script = resolve_script(project_dir, target.script)
            if script is None or script.suffix != ".kt":
                skipped += 1
                continue

            methods = method_cache.setdefault(script, script_methods(script))
            checked += 1
            if connection.method not in methods:
                failures.append(
                    f"{connection.scene.relative_to(project_dir)}:{connection.line}: "
                    f"connection '{connection.signal}' targets {connection.target}.{connection.method}, "
                    f"but {script.relative_to(project_dir)} exposes {sorted(methods) or 'no public functions'}"
                )

    if failures:
        print("[scene_connection_lint] FAIL", file=sys.stderr)
        for failure in failures:
            print(f"  {failure}", file=sys.stderr)
        return 1

    print(f"[scene_connection_lint] PASS checked={checked} skipped={skipped}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())

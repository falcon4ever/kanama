#!/usr/bin/env python3
"""Audit SceneReplicationConfig root properties against Kotlin script properties.

This catches a common porting drift: a scene keeps synchronizing `.:property`
from the original GDScript, but the Kotlin port leaves that field private or
unannotated, so multiplayer replication silently loses part of the behavior.
"""

from __future__ import annotations

import argparse
from dataclasses import dataclass
from pathlib import Path
import re
import sys


BUILTIN_REPLICATED_PROPERTIES = {
    "angular_velocity",
    "global_transform",
    "linear_velocity",
    "position",
    "rotation",
    "rotation_degrees",
    "transform",
    "visible",
}

EXT_RESOURCE_RE = re.compile(r'^\[ext_resource type="Script" path="res://([^"]+)" id="([^"]+)"\]')
SUB_RESOURCE_RE = re.compile(r'^\[sub_resource type="SceneReplicationConfig" id="([^"]+)"\]')
NODE_RE = re.compile(r'^\[node name="([^"]+)"(?: [^\]]*parent="([^"]+)")?')
SCRIPT_RE = re.compile(r'^script = ExtResource\("([^"]+)"\)')
REPLICATION_RE = re.compile(r'^replication_config = SubResource\("([^"]+)"\)')
ROOT_REPLICATION_PATH_RE = re.compile(r'NodePath\("\.:([^"]+)"\)')
SCRIPT_PROPERTY_RE = re.compile(r'^\s*@ScriptProperty(?:\(\s*name\s*=\s*"([^"]+)"\s*\))?')
VAR_RE = re.compile(r"\bvar\s+([A-Za-z_][A-Za-z0-9_]*)\b")


@dataclass
class Node:
    path: str
    script_id: str | None = None
    replication_config_id: str | None = None


@dataclass
class Finding:
    scene: Path
    node_path: str
    script_path: Path
    property_name: str


def parse_script_properties(path: Path) -> set[str]:
    properties: set[str] = set()
    pending_name: str | None = None
    pending_property = False

    for line in path.read_text().splitlines():
        match = SCRIPT_PROPERTY_RE.match(line)
        if match:
            pending_property = True
            pending_name = match.group(1)
            continue

        if pending_property:
            var_match = VAR_RE.search(line)
            if var_match:
                properties.add(pending_name or var_match.group(1))
                pending_property = False
                pending_name = None
            elif line.strip() and not line.strip().startswith("@"):
                pending_property = False
                pending_name = None

    return properties


def scene_node_path(name: str, parent: str | None) -> str:
    if parent is None:
        return "."
    if parent == ".":
        return name
    return f"{parent}/{name}"


def parse_scene(path: Path) -> tuple[dict[str, str], dict[str, set[str]], dict[str, Node]]:
    script_resources: dict[str, str] = {}
    replication_configs: dict[str, set[str]] = {}
    nodes: dict[str, Node] = {}
    current_config: str | None = None
    current_node: Node | None = None

    for line in path.read_text().splitlines():
        if match := EXT_RESOURCE_RE.match(line):
            script_resources[match.group(2)] = match.group(1)
            current_config = None
            current_node = None
            continue

        if match := SUB_RESOURCE_RE.match(line):
            current_config = match.group(1)
            replication_configs[current_config] = set()
            current_node = None
            continue

        if match := NODE_RE.match(line):
            current_node = Node(scene_node_path(match.group(1), match.group(2)))
            nodes[current_node.path] = current_node
            current_config = None
            continue

        if current_config is not None:
            if match := ROOT_REPLICATION_PATH_RE.search(line):
                replication_configs[current_config].add(match.group(1))
            continue

        if current_node is not None:
            if match := SCRIPT_RE.match(line):
                current_node.script_id = match.group(1)
            elif match := REPLICATION_RE.match(line):
                current_node.replication_config_id = match.group(1)

    return script_resources, replication_configs, nodes


def audit_scene(project_root: Path, scene: Path, property_cache: dict[Path, set[str]]) -> list[Finding]:
    script_resources, replication_configs, nodes = parse_scene(scene)
    findings: list[Finding] = []

    for node in nodes.values():
        if node.replication_config_id is None:
            continue

        replicated = replication_configs.get(node.replication_config_id, set())
        target = nodes.get(node.path.rsplit("/", 1)[0] if "/" in node.path else ".")
        if target is None or target.script_id is None:
            continue

        script_rel = script_resources.get(target.script_id)
        if script_rel is None or not script_rel.endswith(".kt"):
            continue

        script_path = project_root / script_rel
        script_properties = property_cache.setdefault(script_path, parse_script_properties(script_path))
        for property_name in sorted(replicated):
            if property_name in BUILTIN_REPLICATED_PROPERTIES:
                continue
            if property_name not in script_properties:
                findings.append(Finding(scene, target.path, script_path, property_name))

    return findings


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("project_root", type=Path)
    args = parser.parse_args()

    project_root = args.project_root.resolve()
    property_cache: dict[Path, set[str]] = {}
    scenes = sorted(project_root.rglob("*.tscn"))
    findings = [
        finding
        for scene in scenes
        for finding in audit_scene(project_root, scene, property_cache)
    ]

    if findings:
        print("[replicated_script_properties] FAIL")
        for finding in findings:
            print(
                f"{finding.scene}:{finding.node_path}: "
                f"{finding.script_path.relative_to(project_root)} does not expose "
                f"replicated property {finding.property_name!r}"
            )
        return 1

    print(
        f"[replicated_script_properties] PASS "
        f"scenes={len(scenes)} scripts={len(property_cache)}"
    )
    return 0


if __name__ == "__main__":
    sys.exit(main())

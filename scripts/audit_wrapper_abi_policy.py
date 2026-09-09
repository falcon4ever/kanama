#!/usr/bin/env python3
"""Report wrapper MethodBind calls against the exact ABI policy model.

This is the stricter successor path for `audit_wrapper_signatures.py`. The
first version is intentionally report-only by default so we can surface current
unsigned-width and generic-policy gaps before making the check fatal in CI.
Use `--strict` once the reported mismatches have explicit helper or facade
policy.
"""

from __future__ import annotations

import argparse
import sys
from collections import Counter
from dataclasses import dataclass
from pathlib import Path

from audit_wrapper_signatures import (
    API_DIR,
    API_PATH,
    BIND_RE,
    CALL_RE,
    ROOT,
    TYPED_OBJECT_ARRAY_KINDS,
    constants_for,
    helper_shape,
    parse_int_token,
)
from wrapper_model import ApiMethod, load_api_classes, load_api_method_index, object_type_names, value_policy, wrapper_source_files


ACTUAL_STORAGE_ALIASES = {
    "float": "float64_scalar",
    "object": "object",
    "int64": "int64",
    "int32": "int32",
}


@dataclass(frozen=True)
class AbiFinding:
    path: Path
    line: int
    class_name: str
    method_name: str
    kind: str
    message: str

    def format(self) -> str:
        rel = self.path.relative_to(ROOT)
        return f"{rel}:{self.line}: {self.class_name}.{self.method_name}: {self.message}"


def canonical_actual(kind: str) -> str:
    return ACTUAL_STORAGE_ALIASES.get(kind, kind)


def storage_matches(expected: str, actual: str) -> bool:
    actual = canonical_actual(actual)
    if expected == actual:
        return True
    if expected.startswith("object::") and actual == "object":
        return True
    if expected in {"typed_object_array"} or expected.startswith("typed_object_array::"):
        return actual in {"array", *TYPED_OBJECT_ARRAY_KINDS}
    return False


def scan_file(
    path: Path,
    api_methods: dict[tuple[str, str, int], ApiMethod],
    object_types: set[str],
) -> list[AbiFinding]:
    content = path.read_text(encoding="utf-8")
    constants = constants_for(content)
    bindings: dict[str, tuple[str, str, int]] = {}
    findings: list[AbiFinding] = []

    for match in BIND_RE.finditer(content):
        variable, class_name, method_name, token = match.groups()
        try:
            method_hash = parse_int_token(token, constants, path)
        except ValueError as error:
            line = content.count("\n", 0, match.start()) + 1
            findings.append(AbiFinding(path, line, class_name, method_name, "unresolved_hash", str(error)))
            continue
        bindings[variable] = (class_name, method_name, method_hash)

    for match in CALL_RE.finditer(content):
        helper, variable = match.groups()
        if variable not in bindings:
            continue
        shape = helper_shape(helper)
        if shape is None:
            continue
        class_name, method_name, method_hash = bindings[variable]
        api_method = api_methods.get((class_name, method_name, method_hash))
        if api_method is None:
            continue
        line = content.count("\n", 0, match.start()) + 1

        expected_args = tuple(
            value_policy(type_name, meta, object_types).storage_kind
            for type_name, meta in zip(api_method.argument_types, api_method.argument_metas, strict=True)
        )
        expected_return = value_policy(api_method.return_type, api_method.return_meta, object_types).storage_kind

        if len(shape.arg_kinds) != len(expected_args):
            findings.append(
                AbiFinding(
                    path,
                    line,
                    class_name,
                    method_name,
                    "arity",
                    f"{helper} has {len(shape.arg_kinds)} arg slot(s), API has {len(expected_args)}",
                ),
            )
            continue

        for index, (expected, actual) in enumerate(zip(expected_args, shape.arg_kinds, strict=True), start=1):
            if storage_matches(expected, actual):
                continue
            policy = value_policy(api_method.argument_types[index - 1], api_method.argument_metas[index - 1], object_types)
            findings.append(
                AbiFinding(
                    path,
                    line,
                    class_name,
                    method_name,
                    f"arg:{policy.exact_kind}",
                    f"arg {index} helper slot is {actual}, exact policy expects {expected} "
                    f"for {policy.api_type}[{policy.meta}]",
                ),
            )

        if not storage_matches(expected_return, shape.return_kind):
            policy = value_policy(api_method.return_type, api_method.return_meta, object_types)
            findings.append(
                AbiFinding(
                    path,
                    line,
                    class_name,
                    method_name,
                    f"return:{policy.exact_kind}",
                    f"return helper slot is {shape.return_kind}, exact policy expects {expected_return} "
                    f"for {policy.api_type}[{policy.meta}]",
                ),
            )

    return findings


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("--api", type=Path, default=API_PATH)
    parser.add_argument("--api-dir", type=Path, default=API_DIR)
    parser.add_argument("--strict", action="store_true")
    parser.add_argument("--max-findings", type=int, default=40)
    args = parser.parse_args()

    classes = load_api_classes(args.api)
    api_methods = load_api_method_index(args.api)
    object_types = object_type_names(classes)

    findings: list[AbiFinding] = []
    for path in wrapper_source_files(args.api_dir, companions=True):
        findings.extend(scan_file(path, api_methods, object_types))

    if not findings:
        print("[wrapper_abi_policy_audit] PASS")
        return 0

    counts = Counter(finding.kind for finding in findings)
    print("[wrapper_abi_policy_audit] REPORT")
    for kind, count in sorted(counts.items(), key=lambda item: (-item[1], item[0])):
        print(f"  {kind}: {count}")
    print(f"  total: {len(findings)}")

    for finding in findings[: args.max_findings]:
        print(f"  - {finding.format()}")
    if len(findings) > args.max_findings:
        print(f"  ... {len(findings) - args.max_findings} more finding(s)")

    if args.strict:
        print("[wrapper_abi_policy_audit] FAIL strict mode", file=sys.stderr)
        return 1
    return 0


if __name__ == "__main__":
    raise SystemExit(main())

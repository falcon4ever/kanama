#!/usr/bin/env python3
"""Every `ObjectCalls` helper the shared wrapper tree calls is classified by how a generated API exercise can observe it.

The generated wrapper tree reaches the engine through ~1,500 distinct `ObjectCalls` helpers, one per
call shape, and each backend implements each shape once. A defect in a shape is shared by every
call site of that helper, so an exercise needs one row per referenced helper -- and before any row
runs, the inventory of which helpers can be checked, how, and which cannot (with the reason) has to
exist and stay true as the tree and the Godot API move. This script is that inventory:

  * the set it classifies is the generator's own (`referenced_objectcalls_helpers`, the set the
    `expect object ObjectCalls` is derived from);
  * each generated call site is mapped back to its Godot method through the generator's naming
    (`method_function_name`), its receiver read from the call (`segment`, `NULL_SEGMENT`,
    `singleton`), and classified against `extension_api.json`;
  * the judgement calls (exclusions, stateless singletons, runtime helpers, static arguments, the
    scratch directory) are hand-kept in `scripts/api_exercise_policy.py`.

Cases, ranked per helper over all its call sites (the best site wins; within a case a non-weak row
beats a weak one; when C wins, the best non-C row is recorded too):
  C static      an `is_static` method on a non-excluded class with a return value (weak when it takes
                no argument and returns a type whose zero a healthy host can return)
  A pair        the setter or getter of a non-indexed property, on an instantiable class (or its
                nearest instantiable non-excluded descendant, or the policy's RECEIVER_SOURCES factory)
  B const       a const, non-void instance method on such a class
  D singleton   a const non-void method of a non-excluded singleton, or any non-void method of a
                stateless singleton
  F reach       everything else that can be called (void, non-const, void statics, METHOD_OVERRIDES)
  E excluded    every site is on an excluded, editor, or abstract-without-receiver class
  N runtime     no generated MethodBind call: plumbing every row uses (listed in the policy file)

Every `ObjectCalls.<helper>(` call of a MethodBind helper in the shared sources must be a site the
scanner read; one it misses fails with its file:line instead of dropping out of the inventory.

Usage:
    python3 scripts/generate_api_exercise.py --report   # write docs/reference/generated/api-exercise.md
    python3 scripts/generate_api_exercise.py --check    # drift, stale policy, unclassified helper -> exit 1
"""

from __future__ import annotations

import argparse
import collections
import json
import re
import sys
from dataclasses import dataclass, field
from pathlib import Path

import api_exercise_policy as policy_module
from generate_api_wrapper import (
    IOS_HANDWRITTEN_HELPERS,
    IOS_OBJECTCALLS,
    IOS_PT_TAG_VALUES,
    OBJECTCALLS_REFERENCE_RE,
    SHARED_HAND_ROOT_FILES,
    SHARED_API_DIR,
    SHARED_TREES,
    ios_generated_region,
    method_function_name,
    objectcalls_helpers_in,
    strip_comments,
    tree_universe,
)
from wrapper_model import ROOT, WRAPPER_CLASS_ALIASES, ApiClass, ApiMethod, load_api_classes, load_api_singletons

TAG = "[api_exercise]"
API_PATH = ROOT / "extension_api.json"
REPORT_PATH = ROOT / "docs/reference/generated/api-exercise.md"
FIXTURE_DIR = ROOT / "scripts/fixtures/api_exercise"

ORDER = "CABDFE"
VALUE_CASES = "CABD"
ABSTRACT = "abstract, and no instantiable descendant"

# One generated call: `ObjectCalls.<helper>(Binds.<fn>Bind, <receiver>, ...)`. The receiver is the
# instance handle, the static marker, the singleton handle, or (in a shared extension section) an
# expression ending in `.segment`.
SITE_RE = re.compile(
    r"\bObjectCalls\.(\w+)\(\s*(?:Binds\.)?(\w+)Bind\s*,\s*(segment|NULL_SEGMENT|singleton|\w+\(\)\.segment|\w+)\s*[,)]"
)
# A String argument that names a file or directory (the scratch-directory rule covers these rows).
PATH_ARG_RE = re.compile(r"(?:^|_)(?:path|file|dir|directory|pack|filename)(?:_|$)")
# Return types whose zero value (false, 0, an empty container) a healthy host can legitimately return.
ZERO_PRONE_RETURNS = re.compile(r"^(?:bool|int|float|enum::.*|bitfield::.*|Array|typedarray::.*|Dictionary|Packed\w+Array)$")


@dataclass(frozen=True)
class Site:
    cls: str
    method: str  # the Godot method name
    kind: str  # instance | static | singleton
    hand: bool = False  # called from a hand-written shared root (GodotObject, RefCounted)


@dataclass
class Verdict:
    case: str
    reason: str
    target: str = ""  # where a row gets its receiver, when it is not a fresh instance of the declaring class
    weak: str = ""  # why the row's value may not tell a working call from a no-op ("" = it can)


@dataclass
class HelperRow:
    helper: str
    case: str
    site: Site | None
    verdict: Verdict | None
    sites: int
    tags: str = ""
    secondary: tuple[Site, Verdict] | None = None  # the best non-C row, when the best row is C

    def strong(self) -> bool:
        """True when some row of this helper asserts a value a silent no-op cannot produce."""
        if self.verdict is None:
            return False
        if self.case in VALUE_CASES and not self.verdict.weak:
            return True
        return self.secondary is not None and self.secondary[1].case in VALUE_CASES and not self.secondary[1].weak


@dataclass
class Inventory:
    rows: dict[str, HelperRow]
    sites: list[tuple[str, Site]]
    receivers: collections.Counter
    failures: list[str] = field(default_factory=list)
    exclusion_hits: dict[str, set[str]] = field(default_factory=dict)  # entry -> API classes it matches
    exclusion_helpers: collections.Counter = field(default_factory=collections.Counter)  # entry -> E helpers
    non_bind_files: dict[str, set[str]] = field(default_factory=dict)
    rules: "Rules | None" = None
    static_args_expected: dict[tuple[str, str], tuple[str | None, ...]] = field(default_factory=dict)


# ---- the classification rules (pure: the fixture drives them on a toy API) -------------------------------


class Rules:
    def __init__(self, api: dict[str, ApiClass], raw: dict[str, dict], singletons: set[str], policy) -> None:
        self.api = api
        self.raw = raw
        self.singletons = singletons
        self.policy = policy
        self.children: dict[str, list[str]] = collections.defaultdict(list)
        for name, cls in sorted(api.items()):
            if cls.inherits:
                self.children[cls.inherits].append(name)
        self.pairs: dict[tuple[str, str], str] = {}
        for name, cls in api.items():
            for prop in cls.properties:
                if prop.get("index") is not None or not prop.get("setter") or not prop.get("getter"):
                    continue
                self.pairs[(name, str(prop["setter"]))] = f"setter of `{prop['name']}`"
                self.pairs[(name, str(prop["getter"]))] = f"getter of `{prop['name']}`"

    def chain(self, name: str) -> list[str]:
        out = []
        while name and name in self.api:
            out.append(name)
            name = self.api[name].inherits
        return out

    def exclusion(self, name: str) -> tuple[str, str] | None:
        """(entry, reason) of the first exclusion that covers [name] or an ancestor, else None."""
        api_type = self.raw.get(name, {}).get("api_type", "core")
        if api_type != "core":
            return (f"api_type={api_type}", f"`api_type` is `{api_type}` in extension_api.json")
        for n in self.chain(name):
            if n in self.policy.EXCLUDE_EXACT:
                return (n, self.policy.EXCLUDE_EXACT[n])
            for sub, reason in self.policy.EXCLUDE_SUBSTRING.items():
                if sub in n:
                    return (f"*{sub}*", reason)
            for prefix, reason in self.policy.EXCLUDE_PREFIX.items():
                if n.startswith(prefix):
                    return (f"{prefix}*", reason)
        return None

    def instantiable(self, name: str) -> bool:
        return bool(self.raw.get(name, {}).get("is_instantiable"))

    def receiver(self, name: str) -> tuple[str | None, str]:
        """(where a row gets an instance of [name], why there is none). [name] itself when a row can
        construct it; the policy's factory (RECEIVER_SOURCES); else the nearest (BFS) instantiable,
        non-excluded, non-singleton descendant. With none, the reason names the exclusion that
        removed the descendants, if one did."""
        if self.instantiable(name) and name not in self.singletons:
            return name, ""
        source = self.policy.RECEIVER_SOURCES.get(name)
        if source is not None:
            return f"{source[0]}.{source[1]}()", ""
        removed_by: list[str] = []
        queue = list(self.children.get(name, []))
        while queue:
            n = queue.pop(0)
            if self.instantiable(n) and n not in self.singletons:
                excluded = self.exclusion(n)
                if excluded is None:
                    return n, ""
                if excluded[0] not in removed_by:
                    removed_by.append(excluded[0])
            queue.extend(self.children.get(n, []))
        if removed_by:
            return None, f"{removed_by[0]}: abstract; every instantiable descendant is excluded by it"
        return None, ABSTRACT

    def method(self, cls: str, name: str) -> ApiMethod:
        return self.api[cls].methods[name][0]

    def site(self, site: Site) -> Verdict:
        verdict = self._site(site)
        override = self.policy.METHOD_OVERRIDES.get((site.cls, site.method))
        if override is None or verdict.case == "E":
            return verdict
        return Verdict(override[0], f"{override[1]} (METHOD_OVERRIDES)", verdict.target)

    def _site(self, site: Site) -> Verdict:
        method = self.method(site.cls, site.method)
        void = method.return_type == "void"
        excluded = self.exclusion(site.cls)
        if excluded is not None:
            return Verdict("E", f"{excluded[0]}: {excluded[1]}")
        if site.kind == "static":
            if void:
                return Verdict("F", "void static: nothing to compare")
            weak = ""
            if not method.argument_types and ZERO_PRONE_RETURNS.match(method.return_type):
                weak = "argument-free static whose result can be its type's zero on a healthy host"
            if method.return_type == "enum::Error" and method.argument_types:
                return Verdict("C", "static returning Error: its arguments must make it fail", weak=weak)
            return Verdict("C", "static with a return value", weak=weak)
        if site.kind == "singleton" or site.cls in self.singletons:
            if not void and site.cls in self.policy.STATELESS_SINGLETONS:
                return Verdict("D", "non-void method of a stateless singleton")
            if not void and method.is_const:
                return Verdict("D", "const non-void singleton method")
            return Verdict("F", "void singleton method" if void else "non-const singleton method")
        target, why_none = self.receiver(site.cls)
        if target is None:
            return Verdict("E", why_none)
        via = "" if target == site.cls else target
        pair = self.pairs.get((site.cls, site.method))
        if pair is not None:
            return Verdict("A", pair, via)
        if not void and method.is_const:
            return Verdict("B", "const non-void method", via)
        return Verdict("F", "void method" if void else "non-const method", via)


def _ranked(rules: Rules, sites) -> list[tuple[Site, Verdict]]:
    scored = [(s, rules.site(s)) for s in set(sites)]
    scored.sort(key=lambda sv: (ORDER.index(sv[1].case), bool(sv[1].weak), sv[0].cls, sv[0].method, sv[0].kind))
    return scored


def best(rules: Rules, sites: list[Site]) -> tuple[Site, Verdict]:
    return _ranked(rules, sites)[0]


def secondary(rules: Rules, sites: list[Site]) -> tuple[Site, Verdict] | None:
    """The best non-C row of a helper whose best row is C (a static may not tell a no-op apart)."""
    others = [sv for sv in _ranked(rules, sites) if sv[1].case != "C"]
    return others[0] if others else None


# ---- reading the tree --------------------------------------------------------------------------------------


def load_raw(api_path: Path) -> dict[str, dict]:
    data = json.loads(api_path.read_text(encoding="utf-8"))
    return {c["name"]: c for c in data.get("classes", [])}


# A hand root's own MethodBind declarations: `val <fn>Bind = ObjectCalls.getMethodBind("<Class>", "<method>", ...)`.
HAND_BIND_RE = re.compile(
    r"\bval\s+(\w+)Bind\b[^=\n]*(?:=|by\s+lazy\s*\{)\s*ObjectCalls\.getMethodBind\(\s*\"(\w+)\"\s*,\s*\"(\w+)\""
)


class SourceCache:
    """Each shared source read and comment-stripped once (the strip is most of the run time)."""

    def __init__(self) -> None:
        self.text: dict[Path, str] = {}
        for root in SHARED_TREES:
            for path in sorted(root.rglob("*.kt")):
                self.text[path] = strip_comments(path.read_text(encoding="utf-8"))

    def referenced(self) -> set[str]:
        """The generator's `referenced_objectcalls_helpers` over the text this cache already stripped."""
        return objectcalls_helpers_in(self.text.values())


def tree_sites(api: dict[str, ApiClass], singletons: set[str], failures: list[str], cache: SourceCache, starts: set):
    """Every MethodBind call of the shared tree as (helper, Site): the generated classes (their binds
    named by the generator's `method_function_name`) and the hand roots (their binds read from the
    root's own `getMethodBind` declarations)."""
    shared, _, _ = tree_universe(api)
    files = [(name, SHARED_API_DIR / f"{name}.kt", False) for name in shared]
    files += [(WRAPPER_CLASS_ALIASES.get(stem, stem), SHARED_API_DIR / f"{stem}.kt", True) for stem in SHARED_HAND_ROOT_FILES]
    sites: list[tuple[str, Site]] = []
    receivers: collections.Counter = collections.Counter()
    for name, path, hand in files:
        rel = path.relative_to(ROOT)
        if not path.is_file():
            failures.append(f"{rel}: the generator lists {name} in the shared tree but the file is missing")
            continue
        source = cache.text.get(path)
        if source is None:
            failures.append(f"{rel}: not under the generator's SHARED_TREES")
            continue
        if hand:
            binds = {}
            for bind, owner, method in HAND_BIND_RE.findall(source):
                if owner not in api or method not in api[owner].methods:
                    failures.append(f"{rel}: `{bind}Bind` binds {owner}.{method}, a method the API lacks")
                    continue
                binds[bind] = (owner, method)
        else:
            binds = {method_function_name(name, m): (name, m) for m in api[name].methods}
        for match in SITE_RE.finditer(source):
            helper, bind, receiver = match.groups()
            starts.add((path, match.start()))
            resolved = binds.get(bind)
            if resolved is None:
                how = "its getMethodBind declarations" if hand else "method_function_name"
                failures.append(
                    f"{rel}: `{bind}Bind` (helper {helper}) maps to no {name} method through {how}; "
                    "the inventory cannot say which Godot method the call is"
                )
                continue
            owner, method = resolved
            api_method = api[owner].methods[method][0]
            # The generator passes NULL_SEGMENT for a static and the singleton handle for every method
            # of a singleton, statics included (ResourceUID.uid_to_path).
            if receiver == "NULL_SEGMENT":
                kind = "static"
            elif receiver == "singleton" or owner in singletons:
                kind = "singleton"
            else:
                kind = "instance"
            if (kind == "static") != (api_method.is_static and kind != "singleton"):
                failures.append(
                    f"{rel}: {owner}.{method} is called with receiver `{receiver}` but "
                    f"is_static={api_method.is_static} in extension_api.json"
                )
                continue
            receivers["hand root" if hand else kind] += 1
            sites.append((helper, Site(owner, method, kind, hand)))
    return sites, receivers


def references_by_file(cache: SourceCache) -> dict[str, set[str]]:
    found: dict[str, set[str]] = collections.defaultdict(set)
    for path, text in cache.text.items():
        for helper in OBJECTCALLS_REFERENCE_RE.findall(text):
            found[helper].add(path.stem.removesuffix(".expect"))
    return found


def unread_calls(cache: SourceCache, starts: set, skip: set[str]) -> list[str]:
    """Every `ObjectCalls.<helper>(` call of a MethodBind helper that the site scanner did not read,
    as `file:line` failures: a call SITE_RE misses would otherwise drop out of the inventory."""
    out = []
    for path, text in cache.text.items():
        for match in OBJECTCALLS_REFERENCE_RE.finditer(text):
            if match.group(1) in skip or (path, match.start()) in starts:
                continue
            line = text.count("\n", 0, match.start()) + 1
            out.append(
                f"{path.relative_to(ROOT)}:{line}: `ObjectCalls.{match.group(1)}(` is a call the site scanner did not "
                "read (no `<fn>Bind, <receiver>` it knows, or a file outside the shared tree's classes and hand "
                "roots); the inventory would lose it. Teach SITE_RE the spelling, or list a runtime helper in NON_BIND_HELPERS"
            )
    return out


def ios_tags() -> dict[str, set[str]]:
    """PT_* tags each generated iOS helper body uses (the region the generator owns)."""
    region = ios_generated_region(IOS_OBJECTCALLS.read_text(encoding="utf-8"))
    tags: dict[str, set[str]] = {}
    for member in re.split(r"\n  (?:actual )?fun ", region)[1:]:
        head = re.match(r"(?:<[^>]*>\s*)?(\w+)\s*\(", member)
        if head is None:
            continue
        tags.setdefault(head.group(1), set()).update(
            t for t in re.findall(r"\bPT_[A-Z0-9_]+\b", member) if t in IOS_PT_TAG_VALUES
        )
    return tags


# ---- the inventory ----------------------------------------------------------------------------------------


def build(api_path: Path = API_PATH, policy=policy_module) -> Inventory:
    api = load_api_classes(api_path)
    raw = load_raw(api_path)
    singletons = load_api_singletons(api_path)
    rules = Rules(api, raw, singletons, policy)
    failures: list[str] = []
    cache = SourceCache()
    starts: set = set()
    sites, receivers = tree_sites(api, singletons, failures, cache, starts)
    referenced = cache.referenced()
    by_helper: dict[str, list[Site]] = collections.defaultdict(list)
    for helper, site in sites:
        by_helper[helper].append(site)
    refs = references_by_file(cache)
    failures += unread_calls(cache, starts, set(policy.NON_BIND_HELPERS))
    tags = ios_tags()

    rows: dict[str, HelperRow] = {}
    for helper in sorted(referenced):
        helper_sites = by_helper.get(helper, [])
        if helper_sites:
            site, verdict = best(rules, helper_sites)
            row = HelperRow(helper, verdict.case, site, verdict, len(helper_sites))
            if verdict.case == "C":
                row.secondary = secondary(rules, helper_sites)
            if helper in policy.NON_BIND_HELPERS:
                failures.append(
                    f"NON_BIND_HELPERS lists {helper}, but generated methods call it with a MethodBind "
                    f"({len(helper_sites)} site(s)); delete the entry"
                )
        elif helper in policy.NON_BIND_HELPERS:
            row = HelperRow(helper, "N", None, Verdict("N", policy.NON_BIND_HELPERS[helper]), 0)
        else:
            files = ", ".join(sorted(refs.get(helper, set()))) or "?"
            failures.append(
                f"unclassified referenced helper {helper}: the shared sources call it ({files}) but no generated "
                "method calls it with a MethodBind and scripts/api_exercise_policy.py NON_BIND_HELPERS does not "
                "list it; add it there with the reason it needs no row of its own"
            )
            row = HelperRow(helper, "?", None, None, 0)
        if helper in IOS_HANDWRITTEN_HELPERS or helper not in tags:
            row.tags = "hand"
        else:
            row.tags = " ".join(sorted(tags[helper])) or "(no tag)"
        rows[helper] = row
    for helper in sorted(set(policy.NON_BIND_HELPERS) - referenced):
        failures.append(f"NON_BIND_HELPERS lists {helper}, which the shared sources no longer call; delete the entry")

    inv = Inventory(rows=rows, sites=sites, receivers=receivers, failures=failures, rules=rules)
    inv.non_bind_files = {h: refs.get(h, set()) for h, r in rows.items() if r.case == "N"}
    check_policy(inv, rules, api, singletons, policy)
    return inv


def check_policy(inv: Inventory, rules: Rules, api: dict[str, ApiClass], singletons: set[str], policy) -> None:
    names = sorted(api)
    for entry in policy.EXCLUDE_EXACT:
        if entry not in api:
            inv.failures.append(f"EXCLUDE_EXACT names {entry}, a class the API lacks; delete or rename the entry")
        inv.exclusion_hits[entry] = {entry} if entry in api else set()
    for sub in policy.EXCLUDE_SUBSTRING:
        hits = {n for n in names if sub in n}
        inv.exclusion_hits[f"*{sub}*"] = hits
        if not hits:
            inv.failures.append(f"EXCLUDE_SUBSTRING {sub!r} matches no class of the API; delete the entry")
    for prefix in policy.EXCLUDE_PREFIX:
        hits = {n for n in names if n.startswith(prefix)}
        inv.exclusion_hits[f"{prefix}*"] = hits
        if not hits:
            inv.failures.append(f"EXCLUDE_PREFIX {prefix!r} matches no class of the API; delete the entry")
    for name in policy.STATELESS_SINGLETONS:
        if name not in singletons:
            inv.failures.append(f"STATELESS_SINGLETONS names {name}, which is not a singleton of the API")
    for name, (owner, method, _reason) in policy.RECEIVER_SOURCES.items():
        if name not in api:
            inv.failures.append(f"RECEIVER_SOURCES names {name}, a class the API lacks")
        elif owner not in singletons or method not in api.get(owner, ApiClass(owner, "", {}, (), (), (), ())).methods:
            inv.failures.append(f"RECEIVER_SOURCES[{name}] names {owner}.{method}, which is not a singleton method of the API")
        elif name not in rules.chain(rules.method(owner, method).return_type):
            inv.failures.append(
                f"RECEIVER_SOURCES[{name}]: {owner}.{method} returns {rules.method(owner, method).return_type}, not a {name}"
            )
        elif rules.instantiable(name):
            inv.failures.append(f"RECEIVER_SOURCES names {name}, which is instantiable: a row constructs it; delete the entry")
    called = {(site.cls, site.method) for _, site in inv.sites}
    for (cls, method), (case, _reason) in policy.METHOD_OVERRIDES.items():
        if cls not in api or method not in api[cls].methods:
            inv.failures.append(f"METHOD_OVERRIDES names {cls}.{method}, a method the API lacks; delete the entry")
        elif case not in ("F", "E"):
            inv.failures.append(f"METHOD_OVERRIDES[{cls}.{method}] sets case {case!r}; only F and E are overrides")
        elif (cls, method) not in called:
            inv.failures.append(f"METHOD_OVERRIDES names {cls}.{method}, which the shared tree never calls; delete the entry")
        else:
            # The override must change what the class-level rules decide for at least one call site:
            # an entry whose rule-given case is already its own case (or E) no longer applies.
            ruled = {rules._site(site).case for _, site in inv.sites if (site.cls, site.method) == (cls, method)}
            if not ruled - {case, "E"}:
                inv.failures.append(
                    f"METHOD_OVERRIDES[{cls}.{method}] sets case {case}, which the class rules already give it "
                    f"({'/'.join(sorted(ruled))}): the entry no longer applies; delete it"
                )
    for row in inv.rows.values():
        if row.case == "E" and row.verdict is not None:
            reason = row.verdict.reason
            key = "METHOD_OVERRIDES" if reason.endswith("(METHOD_OVERRIDES)") else reason.split(":", 1)[0]
            inv.exclusion_helpers[key] += 1

    # STATIC_ARGS: exactly one entry per value-asserting static row that takes arguments.
    expected: dict[tuple[str, str], tuple[str | None, ...]] = {}
    for row in inv.rows.values():
        if row.case != "C" or row.site is None:
            continue
        method = rules.method(row.site.cls, row.site.method)
        if method.argument_types:
            expected[(row.site.cls, row.site.method)] = method.argument_defaults
    inv.static_args_expected = expected
    for key, value in policy.STATIC_ARGS.items():
        cls, name = key
        if cls not in api or name not in api[cls].methods:
            inv.failures.append(f"STATIC_ARGS names {cls}.{name}, a method the API lacks; delete the entry")
        elif key not in expected:
            inv.failures.append(
                f"STATIC_ARGS names {cls}.{name}, which is not a value-asserting static row that takes "
                "arguments (not static, excluded, void, argument-less, or another site ranks higher); delete the entry"
            )
        elif len(value) != len(expected[key]):
            inv.failures.append(
                f"STATIC_ARGS[{cls}.{name}] has {len(value)} argument(s); the method takes {len(expected[key])}"
            )
        else:
            method = rules.method(cls, name)
            for index, (given, default) in enumerate(zip(value, expected[key])):
                if default is not None and given != default:
                    inv.failures.append(
                        f"STATIC_ARGS[{cls}.{name}] argument {index + 1} (`{method.argument_names[index]}`) is {given!r}, "
                        f"but extension_api.json's default is {default!r} (did an API refresh change it?)"
                    )
    for key in sorted(set(expected) - set(policy.STATIC_ARGS)):
        inv.failures.append(
            f"static row {key[0]}.{key[1]} has no STATIC_ARGS entry; add `{key!r}: {expected[key]!r},` "
            "(the extension_api.json defaults, None where a value must be chosen)"
        )


# ---- the report -------------------------------------------------------------------------------------------


def md(text: str) -> str:
    return text.replace("|", "\\|")


def site_text(site: Site, verdict: Verdict) -> str:
    text = f"`{site.cls}.{site.method}`" + (" (hand-written root)" if site.hand else "")
    if verdict.target:
        text += f" on `{verdict.target}`"
    text += f" ({md(verdict.reason)})"
    if verdict.weak:
        text += f" — **weak**: {md(verdict.weak)}"
    return text


def path_rows(inv: Inventory) -> list[tuple[Site, Verdict, list[str]]]:
    """The rows (best and secondary) whose method takes a String path argument."""
    out = []
    seen = set()
    for row in inv.rows.values():
        for pair in ((row.site, row.verdict), row.secondary):
            if pair is None or pair[0] is None or pair[1].case == "E":
                continue
            method = inv.rules.method(pair[0].cls, pair[0].method)
            names = [
                n for n, t in zip(method.argument_names, method.argument_types) if t == "String" and PATH_ARG_RE.search(n)
            ]
            if names and (pair[0].cls, pair[0].method) not in seen:
                seen.add((pair[0].cls, pair[0].method))
                out.append((pair[0], pair[1], names))
    return sorted(out, key=lambda item: (item[0].cls, item[0].method))


def render(inv: Inventory, policy=policy_module) -> str:
    rows = inv.rows
    counts = collections.Counter(r.case for r in rows.values())
    bind_shaped = sum(1 for r in rows.values() if r.case not in ("N", "?"))
    strong = sum(1 for r in rows.values() if r.strong())
    in_value_cases = sum(counts[c] for c in VALUE_CASES)
    weak_only = sum(1 for r in rows.values() if r.case in VALUE_CASES and not r.strong())
    rescued = sum(1 for r in rows.values() if r.case == "C" and r.verdict.weak and r.strong())
    via = sum(1 for r in rows.values() if r.verdict is not None and r.verdict.target)
    rc = inv.receivers
    generated_sites = rc["instance"] + rc["singleton"] + rc["static"]
    single = sum(1 for r in rows.values() if r.case not in ("N", "?") and r.sites == 1)
    lines = [
        "# API Exercise Inventory",
        "",
        "<!-- GENERATED by scripts/generate_api_exercise.py --report. DO NOT EDIT BY HAND; the local_ci stage",
        '     "API exercise inventory drift" (`--check`) fails when this page is stale. -->',
        "",
        "Every generated wrapper member reaches the engine through one `ObjectCalls.<helper>(bind, receiver, args…)`",
        "call, and each backend implements each helper once, so a defect in a helper is shared by every call site",
        "of it. This page classifies every helper the shared wrapper tree calls by how a generated exercise row can",
        "observe it, from `extension_api.json`, the generator's tables and the committed tree; the judgement calls",
        "(exclusions, stateless singletons, receivers, method-level cases, runtime helpers, static arguments, the",
        "scratch directory) are hand-kept in `scripts/api_exercise_policy.py`. It is an inventory: no row runs yet.",
        "",
        f"**Referenced helpers:** {len(rows)} ({bind_shaped} called by generated methods with a MethodBind, "
        f"{counts['N']} runtime helpers). **MethodBind call sites:** {generated_sites} generated "
        f"({rc['instance']} instance, {rc['singleton']} singleton, {rc['static']} static) and {rc['hand root']} in "
        f"the hand-written roots (`GodotObject`, `RefCounted`); {single} helpers have exactly one call site. "
        f"**Helpers with a row that asserts a value a no-op cannot produce:** {strong} "
        f"(of the {in_value_cases} whose best case is C/A/B/D: {weak_only} have only a weak row, and {rescued} weak "
        f"C rows are backed by a non-weak A/B/D row of the same helper); reach-only {counts['F']}; excluded {counts['E']}.",
        "",
        "## Cases",
        "",
        "Each helper gets the best case any of its call sites qualifies for, in the order of the table (within a case,",
        "a non-weak row before a weak one). When the best case is C, the helper's best other row is listed too: a",
        "static row is what guards the static dispatch path, but an argument-free static can return its type's zero",
        "on a healthy host (**weak**), and then the second row is the one that can tell a no-op apart.",
        "",
        "| case | a site qualifies when | a row would assert | helpers |",
        "|---|---|---|---|",
        f"| C static | `is_static` with a return value, class not excluded | the wrapper's result equals `ClassDB.class_call_static` with the same arguments, and is not the type's zero (an `Error` static: a known non-OK `Error`) | {counts['C']} |",
        f"| A property pair | setter or getter of a non-indexed property of an instantiable class (or its receiver below) | a value set through the wrapper reads back through the getter and `Object.get` | {counts['A']} |",
        f"| B const oracle | const non-void instance method on such a class | the wrapper's result equals `Object.callv` on the same object | {counts['B']} |",
        f"| D singleton | const non-void method of a non-excluded singleton, or any non-void method of a stateless singleton | the wrapper's result equals `Object.callv` on the singleton | {counts['D']} |",
        f"| F reach only | void or non-const methods, void statics, `METHOD_OVERRIDES` (clock reads, a new object per call) | the call returns without an exception or a backend fault | {counts['F']} |",
        f"| E excluded | every site is on an excluded or editor class, on an abstract class without a receiver, or a method `METHOD_OVERRIDES` excludes | nothing; listed below with the reason | {counts['E']} |",
        f"| N runtime helper | no generated method calls it with a MethodBind | nothing of its own: every row uses it | {counts['N']} |",
        "",
        f"{via} of the rows get their receiver elsewhere than a fresh instance of the declaring class (a descendant,",
        "or the factory `RECEIVER_SOURCES` names, because the declaring class is abstract).",
        "",
    ]
    lines += render_class_tops(rows)
    lines += render_tag_table(rows)
    lines += render_exclusions(inv, policy)
    lines += [
        "## Receivers and method-level cases",
        "",
        "`RECEIVER_SOURCES` (an abstract class's rows use the engine's own instance):",
        "",
    ]
    for name, (owner, method, reason) in policy.RECEIVER_SOURCES.items():
        lines.append(f"- `{name}` from `{owner}.{method}()`: {md(reason)}.")
    lines += [
        "",
        "`METHOD_OVERRIDES` (methods the class rules would give a value row):",
        "",
        "| method | case | reason |",
        "|---|---|---|",
    ]
    for (cls, method), (case, reason) in policy.METHOD_OVERRIDES.items():
        lines.append(f"| `{cls}.{method}` | {case} | {md(reason)} |")
    lines += [
        "",
        "## Runtime helpers",
        "",
        "Referenced by the shared sources, but never with a generated MethodBind (`NON_BIND_HELPERS`).",
        "",
        "| helper | used by | why it has no row of its own |",
        "|---|---|---|",
    ]
    for helper, row in sorted(rows.items()):
        if row.case == "N":
            users = sorted(inv.non_bind_files.get(helper, set()))
            files = ", ".join(f"`{f}`" for f in users[:4]) + (f", … ({len(users)} files)" if len(users) > 4 else "")
            lines.append(f"| `{helper}` | {files} | {md(row.verdict.reason)} |")
    lines += ["", "## Static rows and the scratch directory", ""]
    open_entries = sum(1 for v in policy.STATIC_ARGS.values() if any(a is None for a in v))
    argless = counts["C"] - len(inv.static_args_expected)
    weak_c = sum(1 for r in rows.values() if r.case == "C" and r.verdict.weak)
    lines += [
        f"{counts['C']} static rows; {argless} take no argument ({weak_c} of them weak). The other "
        f"{len(inv.static_args_expected)} take their arguments from `STATIC_ARGS` ({open_entries} of them still have "
        "an argument to choose, shown as `?`). A static returning `Error` must be given arguments that make it fail "
        "with a known non-OK `Error`: OK is 0, which a no-op returns too.",
        "",
        md(policy.SCRATCH_POLICY),
        "",
        "| static | arguments | note |",
        "|---|---|---|",
    ]
    for row in sorted((r for r in rows.values() if r.case == "C"), key=lambda r: (r.site.cls, r.site.method)):
        key = (row.site.cls, row.site.method)
        args = policy.STATIC_ARGS.get(key)
        text = "(none)" if args is None else ", ".join("?" if a is None else f"`{md(a)}`" for a in args)
        note = []
        if inv.rules.method(*key).return_type == "enum::Error" and args is not None:
            note.append("returns `Error`: arguments must make it fail")
        if row.verdict.weak:
            backed = row.secondary is not None and row.secondary[1].case in "ABD" and not row.secondary[1].weak
            note.append("weak; " + (f"backed by {row.secondary[1].case} `{row.secondary[0].cls}.{row.secondary[0].method}`" if backed else "no non-weak row"))
        lines.append(f"| `{row.site.cls}.{row.site.method}` | {text} | {'; '.join(note) or '—'} |")
    paths = [p for p in path_rows(inv) if p[1].case != "C"]
    lines += [
        "",
        f"Rows of other cases whose method takes a path ({len(paths)}): each takes one under the scratch directory.",
        "",
        "| row | case | path arguments |",
        "|---|---|---|",
    ]
    for site, verdict, names in paths:
        if verdict.case == "C":
            continue
        lines.append(f"| `{site.cls}.{site.method}` | {verdict.case} | {', '.join(f'`{n}`' for n in names)} |")
    lines += [
        "",
        "## Every helper",
        "",
        "`iOS tags`: the ptrcall type tags the generated iOS body packs (`(no tag)`: it hands its cells to a read-back "
        "helper without naming one), or `hand` for a helper hand-written on iOS.",
        "",
        "| helper | case | row | sites | iOS tags |",
        "|---|---|---|---|---|",
    ]
    for helper, row in sorted(rows.items()):
        where = "—" if row.site is None else site_text(row.site, row.verdict)
        if row.secondary is not None:
            where += f"; then {row.secondary[1].case} " + site_text(*row.secondary)
        lines.append(f"| `{helper}` | {row.case} | {where} | {row.sites} | {row.tags} |")
    return "\n".join(lines) + "\n"


def render_class_tops(rows: dict[str, HelperRow]) -> list[str]:
    def top(case: str, limit: int = 12) -> str:
        c = collections.Counter(r.site.cls for r in rows.values() if r.case == case and r.site is not None)
        return ", ".join(f"`{name}` {n}" for name, n in sorted(c.items(), key=lambda kv: (-kv[1], kv[0]))[:limit])

    descendants = collections.Counter(
        r.site.cls for r in rows.values() if r.case == "B" and r.verdict.target
    )
    return [
        "## Where the rows are",
        "",
        f"- **C static** by class: {top('C', 50)}.",
        f"- **D singleton** by class: {top('D')}.",
        f"- **B const** through a descendant, by declaring class: "
        + ", ".join(f"`{n}` {k}" for n, k in sorted(descendants.items(), key=lambda kv: (-kv[1], kv[0]))[:12])
        + ".",
        f"- **F reach only**, largest classes: {top('F')}.",
        "",
    ]


def render_tag_table(rows: dict[str, HelperRow]) -> list[str]:
    table: dict[str, list] = {}
    for row in rows.values():
        if row.tags == "hand" or row.case in ("N", "?"):
            continue
        for tag in row.tags.split() if row.tags.startswith("PT_") else ():
            entry = table.setdefault(tag, [0, 0, collections.Counter()])
            entry[0] += 1
            entry[1] += row.sites
            entry[2][row.case] += 1
    hand = sum(1 for r in rows.values() if r.tags == "hand" and r.case not in ("N", "?"))
    lines = [
        "## By iOS ptrcall tag",
        "",
        f"The generated iOS helpers by the type tags their bodies pack (a helper counts once per tag it uses); the "
        f"{hand} bind-shaped helpers hand-written on iOS are not in this table.",
        "",
        "| tag | helpers | sites | C | A | B | D | F | E |",
        "|---|---|---|---|---|---|---|---|---|",
    ]
    for tag, (n, s, letters) in sorted(table.items(), key=lambda kv: (-kv[1][0], kv[0])):
        cells = " | ".join(str(letters[c]) if letters[c] else "–" for c in "CABDFE")
        lines.append(f"| `{tag}` | {n} | {s} | {cells} |")
    no_value = sorted(t for t, (_, _, letters) in table.items() if not any(letters[c] for c in VALUE_CASES))
    lines += [
        "",
        "Tags with no value-asserting row among generated helpers: "
        + (", ".join(f"`{t}`" for t in no_value) if no_value else "none")
        + ".",
        "",
    ]
    return lines


def render_exclusions(inv: Inventory, policy) -> list[str]:
    lines = [
        "## Exclusions",
        "",
        "Matched against the class and each of its ancestors, so an entry excludes its subtree. `helpers` counts",
        "the helpers whose best case is E because of the entry, including the abstract classes whose every",
        "instantiable descendant it excludes.",
        "",
        "| entry | kind | reason | API classes matched | helpers |",
        "|---|---|---|---|---|",
    ]
    derived = sorted(k for k in inv.exclusion_helpers if k.startswith("api_type="))
    for key in derived:
        lines.append(f"| `{key}` | derived | not a core class (editor) | — | {inv.exclusion_helpers[key]} |")
    for kind, table, fmt in (
        ("exact", policy.EXCLUDE_EXACT, "{}"),
        ("substring", policy.EXCLUDE_SUBSTRING, "*{}*"),
        ("prefix", policy.EXCLUDE_PREFIX, "{}*"),
    ):
        for entry, reason in table.items():
            key = fmt.format(entry)
            hits = len(inv.exclusion_hits.get(key, set()))
            lines.append(f"| `{key}` | {kind} | {md(reason)} | {hits} | {inv.exclusion_helpers.get(key, 0)} |")
    lines += [
        f"| — | derived | {ABSTRACT} (and no `RECEIVER_SOURCES` entry) | — | {inv.exclusion_helpers.get(ABSTRACT, 0)} |",
        f"| `METHOD_OVERRIDES` | method | see below | — | {inv.exclusion_helpers.get('METHOD_OVERRIDES', 0)} |",
        "",
        "Stateless singletons (`STATELESS_SINGLETONS`, every non-void method is a D row): "
        + ", ".join(f"`{n}` ({md(r)})" for n, r in policy.STATELESS_SINGLETONS.items())
        + ".",
        "",
    ]
    return lines


# ---- the fixture -------------------------------------------------------------------------------------------


def fixture_lines(fixture_dir: Path = FIXTURE_DIR) -> list[str]:
    """The rules on a toy API: one line per site, then one per helper's winning case."""
    spec = json.loads((fixture_dir / "rules.json").read_text(encoding="utf-8"))
    api_path = fixture_dir / "extension_api.json"
    api = load_api_classes(api_path)
    rules = Rules(api, load_raw(api_path), load_api_singletons(api_path), _Policy(spec["policy"]))
    out = []
    by_helper: dict[str, list[Site]] = collections.defaultdict(list)
    for helper, cls, method, kind in spec["sites"]:
        site = Site(cls, method, kind)
        by_helper[helper].append(site)
        verdict = rules.site(site)
        via = f" on {verdict.target}" if verdict.target else ""
        weak = f" [weak: {verdict.weak}]" if verdict.weak else ""
        out.append(f"site {cls}.{method} [{kind}] -> {verdict.case}{via}: {verdict.reason}{weak}")
    for helper, sites in sorted(by_helper.items()):
        site, verdict = best(rules, sites)
        then = ""
        if verdict.case == "C":
            other = secondary(rules, sites)
            then = f", then {other[1].case} ({other[0].cls}.{other[0].method})" if other else ""
        out.append(f"helper {helper} -> {verdict.case} ({site.cls}.{site.method}){then}")
    return out


class _Policy:
    def __init__(self, spec: dict) -> None:
        self.EXCLUDE_EXACT = spec.get("exclude_exact", {})
        self.EXCLUDE_SUBSTRING = spec.get("exclude_substring", {})
        self.EXCLUDE_PREFIX = spec.get("exclude_prefix", {})
        self.STATELESS_SINGLETONS = spec.get("stateless_singletons", {})
        self.RECEIVER_SOURCES = {k: tuple(v) for k, v in spec.get("receiver_sources", {}).items()}
        self.METHOD_OVERRIDES = {tuple(k.split(".")): tuple(v) for k, v in spec.get("method_overrides", {}).items()}


# ---- main --------------------------------------------------------------------------------------------------


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    mode = parser.add_mutually_exclusive_group(required=True)
    mode.add_argument("--report", action="store_true", help=f"write {REPORT_PATH.relative_to(ROOT)}")
    mode.add_argument("--check", action="store_true", help="fail on drift, a stale policy entry or an unclassified helper")
    mode.add_argument("--write-fixture", action="store_true", help="rewrite the fixture's expected output")
    args = parser.parse_args()

    expected_path = FIXTURE_DIR / "rules.expected.txt"
    if args.write_fixture:
        expected_path.write_text("\n".join(fixture_lines()) + "\n", encoding="utf-8")
        print(f"{TAG} wrote {expected_path.relative_to(ROOT)}")
        return 0

    inv = build()
    failures = list(inv.failures)
    if not inv.sites:
        failures.append("no generated call site was found in the shared tree: the scanner reads nothing")
    text = render(inv)
    counts = collections.Counter(r.case for r in inv.rows.values())
    summary = " ".join(f"{c}={counts[c]}" for c in "CABDFEN")
    if args.report:
        if failures:
            for failure in failures:
                print(f"{TAG} FAIL {failure}", file=sys.stderr)
            return 1
        REPORT_PATH.write_text(text, encoding="utf-8")
        print(f"{TAG} wrote {REPORT_PATH.relative_to(ROOT)}: {len(inv.rows)} helpers {summary}")
        return 0

    fixture = "\n".join(fixture_lines()) + "\n"
    if not expected_path.is_file() or expected_path.read_text(encoding="utf-8") != fixture:
        failures.append(
            f"{expected_path.relative_to(ROOT)} does not match the rules run on the fixture API "
            "(a classification rule changed); review the diff of --write-fixture"
        )
    if not REPORT_PATH.is_file() or REPORT_PATH.read_text(encoding="utf-8") != text:
        failures.append(
            f"{REPORT_PATH.relative_to(ROOT)} is stale: regenerate with "
            "`python3 scripts/generate_api_exercise.py --report` and review the diff"
        )
    if failures:
        for failure in failures:
            print(f"{TAG} FAIL {failure}", file=sys.stderr)
        return 1
    print(f"{TAG} PASS {len(inv.rows)} referenced helpers classified ({summary}); report and fixture current")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())

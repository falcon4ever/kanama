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
    enum_spec,
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
    row: str = ""  # the row's shape when the case alone does not say it: singleton-pair, keyed-pair, factory


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
        self.pair_props: dict[tuple[str, str], dict] = {}  # (class, setter or getter) -> the property
        pair_overrides = getattr(policy, "PAIR_OVERRIDES", {})
        for name, cls in api.items():
            for prop in cls.properties:
                if prop.get("index") is not None or not prop.get("setter") or not prop.get("getter"):
                    continue
                if (name, prop["name"]) in pair_overrides and pair_overrides[(name, prop["name"])][0] is None:
                    continue
                self.pairs[(name, str(prop["setter"]))] = f"setter of `{prop['name']}`"
                self.pairs[(name, str(prop["getter"]))] = f"getter of `{prop['name']}`"
                self.pair_props[(name, str(prop["setter"]))] = prop
                self.pair_props[(name, str(prop["getter"]))] = prop

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

    def factory(self, cls: str, method: ApiMethod) -> bool:
        """An argument-free (or all-default) `*_create` / `create_*` method returning a RID, on a
        RID_FACTORIES server: its row creates, checks and frees."""
        return (
            cls in getattr(self.policy, "RID_FACTORIES", {})
            and method.return_type == "RID"
            and (method.name.endswith("_create") or method.name.startswith("create_"))
            and all(d is not None for d in method.argument_defaults)
        )

    def keyed_pair(self, cls: str, name: str) -> str | None:
        """'setter/getter of keyed `key`' when [name] is either half of a SINGLETON_KEYED_PAIRS entry."""
        for (owner, setter), (getter, key, _reason) in getattr(self.policy, "SINGLETON_KEYED_PAIRS", {}).items():
            if owner == cls and name in (setter, getter):
                return f"{'setter' if name == setter else 'getter'} of the keyed value {key}"
        return None

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
            result = getattr(self.policy, "STATIC_RESULTS", {}).get((site.cls, site.method))
            if result is not None and result[0] == "weak":
                weak = f"{result[1]} (STATIC_RESULTS)"
            if method.return_type == "enum::Error" and method.argument_types:
                return Verdict("C", "static returning Error: its arguments must make it fail", weak=weak)
            return Verdict("C", "static with a return value", weak=weak)
        if site.kind == "singleton" or site.cls in self.singletons:
            pair = self.pairs.get((site.cls, site.method))
            if pair is not None and site.cls in getattr(self.policy, "SINGLETON_PAIRS", {}):
                return Verdict("A", f"{pair}, set and restored on the singleton (SINGLETON_PAIRS)", row="singleton-pair")
            keyed = self.keyed_pair(site.cls, site.method)
            if keyed is not None:
                return Verdict("A", f"{keyed}, set and restored (SINGLETON_KEYED_PAIRS)", row="keyed-pair")
            if self.factory(site.cls, method):
                return Verdict("D", "RID factory: a valid RID, one read of it, freed (RID_FACTORIES)", row="factory")
            if not void and site.cls in self.policy.STATELESS_SINGLETONS:
                return Verdict("D", "non-void method of a stateless singleton")
            if not void and method.is_const:
                return Verdict("D", "const non-void singleton method")
            return Verdict("F", "void singleton method" if void else "non-const singleton method")
        target, why_none = self.receiver(site.cls)
        if target is None:
            return Verdict("E", why_none)
        via = "" if target == site.cls else target
        if self.factory(site.cls, method):
            return Verdict("B", "RID factory: a valid RID, one read of it, freed (RID_FACTORIES)", via, row="factory")
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
                if given is None:
                    inv.failures.append(
                        f"STATIC_ARGS[{cls}.{name}] argument {index + 1} (`{method.argument_names[index]}`) has no value; choose one"
                    )
                elif isinstance(given, policy.Arg):
                    if not given.reason:
                        inv.failures.append(f"STATIC_ARGS[{cls}.{name}] argument {index + 1}: an Arg needs its reason")
                    elif default is not None and str(given) == default:
                        inv.failures.append(
                            f"STATIC_ARGS[{cls}.{name}] argument {index + 1} is marked Arg (a deliberate non-default) but "
                            f"equals extension_api.json's default {default!r}; make it a plain string"
                        )
                elif default is not None and given != default:
                    inv.failures.append(
                        f"STATIC_ARGS[{cls}.{name}] argument {index + 1} (`{method.argument_names[index]}`) is {given!r}, "
                        f"but extension_api.json's default is {default!r} (did an API refresh change it? A deliberate "
                        "other value is an `Arg(value, reason)`)"
                    )
    for (cls, prop), (value, reason) in getattr(policy, "PAIR_OVERRIDES", {}).items():
        props = {str(x["name"]): x for x in api[cls].properties} if cls in api else {}
        if prop not in props or not props[prop].get("setter") or not props[prop].get("getter"):
            inv.failures.append(f"PAIR_OVERRIDES names {cls}.{prop}, which is not a set/get property of the API; delete the entry")
        elif not reason:
            inv.failures.append(f"PAIR_OVERRIDES[{cls}.{prop}] needs its reason")
        elif value is not None:
            try:
                parse_literal(value)
            except LiteralError as error:
                inv.failures.append(f"PAIR_OVERRIDES[{cls}.{prop}]: {error}")
    object_statics = {
        (r.site.cls, r.site.method): rules.method(r.site.cls, r.site.method).return_type
        for r in inv.rows.values()
        if r.case == "C" and r.site is not None and rules.method(r.site.cls, r.site.method).return_type in api
    }
    checks = getattr(policy, "STATIC_OBJECT_CHECKS", {})
    for key in sorted(set(object_statics) - set(checks)):
        inv.failures.append(
            f"static row {key[0]}.{key[1]} returns a {object_statics[key]} but STATIC_OBJECT_CHECKS names nothing to read "
            "back from it; add the one to three properties or `method()`s its arguments show in"
        )
    for key, (names, reason) in checks.items():
        returned = object_statics.get(key)
        if returned is None:
            inv.failures.append(f"STATIC_OBJECT_CHECKS names {key[0]}.{key[1]}, which is not a static row returning an object; delete the entry")
            continue
        if not names and not reason:
            inv.failures.append(f"STATIC_OBJECT_CHECKS[{key[0]}.{key[1]}] reads nothing and gives no reason")
        if len(names) > 3:
            inv.failures.append(f"STATIC_OBJECT_CHECKS[{key[0]}.{key[1]}] reads {len(names)} values; name one to three")
        for name in names:
            chain = rules.chain(returned)
            if name.endswith("()"):
                found = any(
                    name[:-2] in api[c].methods
                    and not api[c].methods[name[:-2]][0].argument_types
                    and api[c].methods[name[:-2]][0].return_type != "void"
                    for c in chain
                )
            else:
                found = any(name in {str(x["name"]) for x in api[c].properties} for c in chain)
            if not found:
                inv.failures.append(
                    f"STATIC_OBJECT_CHECKS[{key[0]}.{key[1]}] reads `{name}`, which is not a property or argument-free "
                    f"non-void method of {returned}"
                )
    for key, (kind, reason) in getattr(policy, "STATIC_RESULTS", {}).items():
        if key not in {(site.cls, site.method) for _, site in inv.sites if site.kind == "static"}:
            inv.failures.append(f"STATIC_RESULTS names {key[0]}.{key[1]}, which the shared tree never calls as a static; delete the entry")
        elif kind not in ("weak", "fresh") or not reason:
            inv.failures.append(f"STATIC_RESULTS[{key[0]}.{key[1]}] must be ('weak' | 'fresh', reason)")
    for key, (platforms, reason) in getattr(policy, "STATIC_ZERO_ON", {}).items():
        if key not in {(site.cls, site.method) for _, site in inv.sites if site.kind == "static"}:
            inv.failures.append(f"STATIC_ZERO_ON names {key[0]}.{key[1]}, which the shared tree never calls as a static; delete the entry")
        elif not platforms or set(platforms) - {"ios", "android", "desktop"} or not reason:
            inv.failures.append(f"STATIC_ZERO_ON[{key[0]}.{key[1]}] must be ((platforms among ios, android, desktop), reason)")
        elif key in getattr(policy, "STATIC_RESULTS", {}):
            inv.failures.append(f"STATIC_ZERO_ON[{key[0]}.{key[1]}]: the row is in STATIC_RESULTS too; keep one entry")
    check_singleton_policy(inv, rules, api, singletons, policy)
    for key in sorted(set(expected) - set(policy.STATIC_ARGS)):
        inv.failures.append(
            f"static row {key[0]}.{key[1]} has no STATIC_ARGS entry; add `{key!r}: {expected[key]!r},` "
            "(the extension_api.json defaults, None where a value must be chosen)"
        )


def check_singleton_policy(inv: Inventory, rules: Rules, api: dict[str, ApiClass], singletons: set[str], policy) -> None:
    """SINGLETON_PAIRS, SINGLETON_KEYED_PAIRS, RID_FACTORIES and RID_ARG_SOURCES name
    what the API has and what a row uses."""
    fail = inv.failures.append
    for name in getattr(policy, "SINGLETON_PAIRS", {}):
        if name not in singletons:
            fail(f"SINGLETON_PAIRS names {name}, which is not a singleton of the API")
        elif not any(cls == name for cls, _ in rules.pairs):
            fail(f"SINGLETON_PAIRS names {name}, which has no set/get property: delete the entry")
    for (owner, setter), (getter, key, reason) in getattr(policy, "SINGLETON_KEYED_PAIRS", {}).items():
        if owner not in singletons:
            fail(f"SINGLETON_KEYED_PAIRS names {owner}, which is not a singleton of the API")
            continue
        methods = api[owner].methods
        if setter not in methods or getter not in methods:
            fail(f"SINGLETON_KEYED_PAIRS[{owner}.{setter}] names {setter}/{getter}, which {owner} lacks")
        elif len(methods[setter][0].argument_types) != 2 or not methods[getter][0].argument_types:
            fail(f"SINGLETON_KEYED_PAIRS[{owner}.{setter}]: {setter} must take (key, value) and {getter} (key, ...)")
        if not reason:
            fail(f"SINGLETON_KEYED_PAIRS[{owner}.{setter}] needs its reason")
        try:
            parse_literal(key)
        except LiteralError as error:
            fail(f"SINGLETON_KEYED_PAIRS[{owner}.{setter}]: {error}")
    factories = getattr(policy, "RID_FACTORIES", {})
    for server, (free, reason) in factories.items():
        if server not in api:
            fail(f"RID_FACTORIES names {server}, a class the API lacks")
        elif free not in api[server].methods or api[server].methods[free][0].argument_types != ("RID",):
            fail(f"RID_FACTORIES[{server}] frees with {free}, which is not a {server} method taking one RID")
        elif not reason:
            fail(f"RID_FACTORIES[{server}] needs its reason")
    # Every RID argument of a B/D row the rows give a source: the entries must be the ones they use.
    used: set[tuple[str, str]] = set()
    for _, site in inv.sites:
        verdict = rules.site(site)
        if verdict.case not in "BD" or verdict.row:
            continue
        method = rules.method(site.cls, site.method)
        for arg, kind, default in zip(method.argument_names, method.argument_types, method.argument_defaults):
            if kind == "RID" and default is None:
                used.add((site.cls, arg))
    for (cls, arg), source in getattr(policy, "RID_ARG_SOURCES", {}).items():
        server, _, factory = source.partition(".")
        if (cls, arg) not in used:
            fail(f"RID_ARG_SOURCES names {cls} argument `{arg}`, which no B or D row of {cls} takes: delete the entry")
        elif server not in factories:
            fail(f"RID_ARG_SOURCES[{cls}.{arg}] makes its RID with {source}, but {server} is not in RID_FACTORIES (nothing frees it)")
        elif server not in api or factory not in api[server].methods or not rules.factory(server, rules.method(server, factory)):
            fail(f"RID_ARG_SOURCES[{cls}.{arg}] names {source}, which is not an argument-free RID factory of the API")
    value_rows = {}
    for _, site in inv.sites:
        verdict = rules.site(site)
        if verdict.case in "BD" and not verdict.row:
            value_rows[(site.cls, site.method)] = verdict
    for key, literals in getattr(policy, "ROW_ARGS", {}).items():
        if key not in value_rows:
            fail(f"ROW_ARGS names {key[0]}.{key[1]}, which is not a B or D row: delete the entry")
            continue
        required = sum(1 for d in rules.method(*key).argument_defaults if d is None)
        if len(literals) != required:
            fail(f"ROW_ARGS[{key[0]}.{key[1]}] gives {len(literals)} value(s); the method takes {required} required argument(s)")
        for literal in literals:
            try:
                parse_literal(str(literal))
            except LiteralError as error:
                fail(f"ROW_ARGS[{key[0]}.{key[1]}]: {error}")
    for key, sets in getattr(policy, "ROW_RECEIVER_SETS", {}).items():
        verdict = value_rows.get(key)
        if verdict is None or verdict.case != "B":
            fail(f"ROW_RECEIVER_SETS names {key[0]}.{key[1]}, which is not a B row: delete the entry")
            continue
        receiver = verdict.target or key[0]
        props = {str(p["name"]) for c in rules.chain(receiver) for p in api[c].properties}
        for prop, literal in sets:
            if prop not in props:
                fail(f"ROW_RECEIVER_SETS[{key[0]}.{key[1]}] sets `{prop}`, which is not a property of {receiver}")
            try:
                parse_literal(literal)
            except LiteralError as error:
                fail(f"ROW_RECEIVER_SETS[{key[0]}.{key[1]}]: {error}")
    for (cls, prop), reason in getattr(policy, "PAIR_GROW", {}).items():
        types = {str(p["name"]): p.get("type") for p in api[cls].properties} if cls in api else {}
        if cls not in getattr(policy, "SINGLETON_PAIRS", {}) or types.get(prop) != "int" or not reason:
            fail(f"PAIR_GROW names {cls}.{prop}, which is not an int property of a SINGLETON_PAIRS singleton (with a reason)")



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
        "scratch directory, the singleton rows) are hand-kept in `scripts/api_exercise_policy.py`. The C, A, B and D",
        "rows run: `ApiExercise.generated.kt` (the same script writes it) on the desktop smoke and in the iOS self-test",
        "build; the F (reach-only) rows are listed here and run nowhere yet.",
        "",
        f"**Referenced helpers:** {len(rows)} ({bind_shaped} called by generated methods with a MethodBind, "
        f"{counts['N']} runtime helpers). **MethodBind call sites:** {generated_sites} generated "
        f"({rc['instance']} instance, {rc['singleton']} singleton, {rc['static']} static) and {rc['hand root']} in "
        f"the hand-written roots (`GodotObject`, `RefCounted`); {single} helpers have exactly one call site. "
        f"**Helpers with a row that asserts a value a no-op cannot produce:** {strong} "
        f"(of the {in_value_cases} whose best case is C/A/B/D: {weak_only} have only a weak row, and {rescued} weak "
        f"C rows are backed by a non-weak A/B/D row of the same helper); reach-only {counts['F']}; excluded {counts['E']}. "
        "A B or D row is weak at run time when its result is its type's zero (a fresh object's empty state): the "
        "runs count those apart (`weak` in the summary line), so this count is an upper bound.",
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
        f"| A property pair | setter or getter of a non-indexed property of an instantiable class (or its receiver below), or of a `SINGLETON_PAIRS` singleton, or a `SINGLETON_KEYED_PAIRS` method | a value set through the wrapper reads back through the getter and `Object.get` (on a singleton, the value read first is set back in a `finally`) | {counts['A']} |",
        f"| B const oracle | const non-void instance method on such a class (or an argument-free RID factory of a `RID_FACTORIES` class) | the wrapper's result equals `Object.call` with the same arguments on the same object; a result equal to its type's zero is **weak** (counted apart, not failed) | {counts['B']} |",
        f"| D singleton | const non-void method of a non-excluded singleton, any non-void method of a stateless singleton, or an argument-free RID factory of a `RID_FACTORIES` server | the wrapper's result equals `Object.call` on the singleton (weak as B); a factory returns a valid RID, one read of it agrees on both paths, and the RID is freed | {counts['D']} |",
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
    argless = counts["C"] - len(inv.static_args_expected)
    weak_c = sum(1 for r in rows.values() if r.case == "C" and r.verdict.weak)
    lines += [
        f"{counts['C']} static rows; {argless} take no argument. {weak_c} are weak (they check that the wrapper and "
        "`ClassDB.class_call_static` agree and are counted apart). The other "
        f"{len(inv.static_args_expected)} take their arguments from `STATIC_ARGS` (GDScript literals; "
        "`scratch(\"name\")` is a path under the scratch directory, `fixture(\"name\")` bytes or text the runner "
        "builds). A static returning `Error` is given arguments that make it fail with a known non-OK `Error`: OK is "
        "0, which a no-op returns too.",
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
        for index, arg in enumerate(args or ()):
            if isinstance(arg, policy.Arg):
                note.append(f"argument {index + 1} is not the API default: {md(arg.reason)}")
        object_check = getattr(policy, "STATIC_OBJECT_CHECKS", {}).get(key)
        if object_check is not None:
            if object_check[0]:
                note.append("reads back " + ", ".join(f"`{n}`" for n in object_check[0]) + " from both results")
            else:
                note.append(f"reads nothing back: {md(object_check[1])}")
        result = getattr(policy, "STATIC_RESULTS", {}).get(key)
        if result is not None and result[0] == "fresh":
            note.append(f"fresh result: {md(result[1])} (both paths non-zero, not compared)")
        zero_hosts = getattr(policy, "STATIC_ZERO_ON", {}).get(key)
        if zero_hosts is not None:
            note.append(f"a zero counts weak on {', '.join(zero_hosts[0])} (STATIC_ZERO_ON)")
        if inv.rules.method(*key).return_type == "enum::Error" and args is not None:
            note.append("returns `Error`: arguments must make it fail")
        if row.verdict.weak:
            backed = row.secondary is not None and row.secondary[1].case in "ABD" and not row.secondary[1].weak
            note.append("weak; " + (f"backed by {row.secondary[1].case} `{row.secondary[0].cls}.{row.secondary[0].method}`" if backed else "no non-weak row"))
        lines.append(f"| `{row.site.cls}.{row.site.method}` | {text} | {'; '.join(note) or '—'} |")
    lines += [
        "",
        "`STATIC_RESULTS` (statics whose result cannot be held to equal-and-not-zero):",
        "",
    ]
    for (cls, method), (kind, reason) in getattr(policy, "STATIC_RESULTS", {}).items():
        lines.append(f"- `{cls}.{method}`: {kind}, {md(reason)}.")
    lines += [
        "",
        "`STATIC_ZERO_ON` (statics whose result may be its type's zero on a platform: there a zero from both paths",
        "counts weak, a non-zero result passes as everywhere else):",
        "",
    ]
    for (cls, method), (platforms, reason) in getattr(policy, "STATIC_ZERO_ON", {}).items():
        lines.append(f"- `{cls}.{method}` on {', '.join(platforms)}: {md(reason)}.")
    lines += [
        "",
        "## Known findings",
        "",
        "Rows that fail on a platform for a gap tracked elsewhere (`KNOWN_FINDINGS`): the row still runs there,",
        "and a failure in its Variant-path half whose message contains the entry's text prints `KNOWN:` instead of",
        "`FAIL:`; any other failure fails, and once the row passes the run fails until the entry is removed.",
        "",
        "| row | platforms | reason |",
        "|---|---|---|",
    ]
    for (case, label), (platforms, failure, reason, _tracking) in getattr(policy, "KNOWN_FINDINGS", {}).items():
        lines.append(f"| {case} `{label}` | {', '.join(platforms)} | {md(reason)}; excuses only `{md(failure)}` (tracked) |")
    if not getattr(policy, "KNOWN_FINDINGS", {}):
        lines.append("| — | — | none |")
    lines += [
        "",
        "## Property-pair overrides",
        "",
        "An A row sets a recognisable non-default value chosen from the property's type. `PAIR_OVERRIDES` gives a",
        "property the value it can keep, or says it is no pair (a set value does not read back as itself on a fresh",
        "object), which hands its setter and getter back to their method's own case.",
        "",
        "| property | value | reason |",
        "|---|---|---|",
    ]
    for (cls, prop), (value, reason) in getattr(policy, "PAIR_OVERRIDES", {}).items():
        shown = "not a pair" if value is None else f"`{md(value)}`"
        lines.append(f"| `{cls}.{prop}` | {shown} | {md(reason)} |")
    lines += render_singleton_policy(inv, policy)
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


def render_singleton_policy(inv: Inventory, policy) -> list[str]:
    factories = sorted({(s.cls, s.method) for _, s in inv.sites if inv.rules.site(s).row == "factory"})
    lines = [
        "",
        "## Singleton state and RID factories",
        "",
        "Singleton property pairs that set process-wide state and restore it (`SINGLETON_PAIRS`): "
        + ", ".join(f"`{n}` ({md(r)})" for n, r in getattr(policy, "SINGLETON_PAIRS", {}).items())
        + ". Keyed pairs (`SINGLETON_KEYED_PAIRS`): "
        + ", ".join(
            f"`{o}.{s}`/`{g}` on `{md(k)}` ({md(r)})" for (o, s), (g, k, r) in getattr(policy, "SINGLETON_KEYED_PAIRS", {}).items()
        )
        + ".",
        "",
        f"RID factories (`RID_FACTORIES`; {len(factories)} rows, one per factory method the tree calls): each creates, "
        "requires a valid RID, reads one const `<prefix>_get_*`/`<prefix>_is_*` of it through the wrapper and the "
        "Variant path where the server has one, and frees it in a `finally`. The same factories make the RID "
        "arguments of the servers' B and D rows (an argument `x` takes the class's own `x_create`, or the "
        "`RID_ARG_SOURCES` entry); any other RID argument is the invalid `RID()`.",
        "",
        "| server | frees with | reason |",
        "|---|---|---|",
    ]
    for server, (free, reason) in getattr(policy, "RID_FACTORIES", {}).items():
        lines.append(f"| `{server}` | `{free}` | {md(reason)} |")
    lines += ["", "| RID argument | made by |", "|---|---|"]
    for (cls, arg), source in getattr(policy, "RID_ARG_SOURCES", {}).items():
        lines.append(f"| `{cls}` `{arg}` | `{source}` |")
    lines += [
        "",
        "B and D rows whose per-type arguments would leave them weak take chosen ones (`ROW_ARGS`, one per "
        "required argument) or a receiver set up through `Object.set` first (`ROW_RECEIVER_SETS`):",
        "",
        "| row | arguments | receiver set |",
        "|---|---|---|",
    ]
    sets = getattr(policy, "ROW_RECEIVER_SETS", {})
    for key in sorted(set(getattr(policy, "ROW_ARGS", {})) | set(sets)):
        args = ", ".join(f"`{md(str(a))}`" for a in getattr(policy, "ROW_ARGS", {}).get(key, ())) or "—"
        recv = ", ".join(f"`{p} = {md(v)}`" for p, v in sets.get(key, ())) or "—"
        lines.append(f"| `{key[0]}.{key[1]}` | {args} | {recv} |")
    lines += [
        "",
        "`PAIR_GROW` (singleton properties a row only ever raises by one): "
        + ", ".join(f"`{c}.{p}` ({md(r)})" for (c, p), r in getattr(policy, "PAIR_GROW", {}).items())
        + ".",
        "",
        "A factory row also calls the factory through `Object.call`: a `RID()` from both paths is the host's "
        "(a renderer without decals, fog volumes or VoxelGI; the headless dummy renderer) and counts weak, "
        "printed with that reason; a `RID()` from the wrapper alone fails.",
    ]
    return lines


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
        self.SINGLETON_PAIRS = spec.get("singleton_pairs", {})
        self.SINGLETON_KEYED_PAIRS = {tuple(k.split(".")): tuple(v) for k, v in spec.get("singleton_keyed_pairs", {}).items()}
        self.RID_FACTORIES = {k: tuple(v) for k, v in spec.get("rid_factories", {}).items()}


# ---- the rows: ApiExercise.generated.kt ----------------------------------------------------------------
#
# P1 emits the C (static) and A (property-pair) rows; the B/D/F rows come later. Each row is one private
# function of straight-line code (no lambda per row) reporting into the hand-written ApiExerciseRunner.
# The Kotlin signatures are read from the committed shared tree (the generator wrote them), so a row
# calls exactly what a user calls.

ROWS_PATH = ROOT / "src/commonMain/kotlin/net/multigesture/kanama/exercise/ApiExercise.generated.kt"
ROW_CASES = "CABD"
# The runner calls one function per case, in this order (ApiExercise.run).
ROW_RUNS = (("C", "static"), ("A", "pairs"), ("B", "const"), ("D", "singletons"))


@dataclass(frozen=True)
class KotlinFun:
    name: str
    params: tuple[tuple[str, str], ...]  # (name, Kotlin type), defaults dropped
    ret: str  # "" for Unit
    defaults: tuple[bool, ...] = ()  # per parameter: True when the Kotlin declaration has a default


@dataclass
class ExerciseRow:
    case: str  # C or A
    helpers: list[str]
    site: Site
    verdict: Verdict
    label: str
    body: list[str]


def _split_top(text: str, sep: str = ",") -> list[str]:
    out, depth, start = [], 0, 0
    for i, ch in enumerate(text):
        if ch in "(<[{":
            depth += 1
        elif ch in ")>]}":
            depth -= 1
        elif ch == sep and depth == 0:
            out.append(text[start:i])
            start = i + 1
    tail = text[start:]
    if tail.strip():
        out.append(tail)
    return [part.strip() for part in out]


class KotlinTree:
    """The generated shared wrappers' function signatures, read from the files the generator wrote."""

    def __init__(self, rules: Rules) -> None:
        self.rules = rules
        self.cache: dict[str, str] = {}

    def text(self, cls: str) -> str | None:
        name = {v: k for k, v in WRAPPER_CLASS_ALIASES.items()}.get(cls, cls)
        if name not in self.cache:
            path = SHARED_API_DIR / f"{name}.kt"
            self.cache[name] = path.read_text(encoding="utf-8") if path.is_file() else ""
        return self.cache[name] or None

    def function(self, cls: str, method: str, static: bool) -> KotlinFun | None:
        """[method] of [cls] (or the nearest ancestor that declares it), with Godot's argument count."""
        api_method = None
        for owner in self.rules.chain(cls):
            if method in self.rules.api[owner].methods:
                api_method = self.rules.api[owner].methods[method][0]
                break
        if api_method is None:
            return None
        fn = method_function_name(owner, method)
        text = self.text(owner)
        if text is None:
            return None
        indent = 8 if static else 4
        pattern = re.compile(r"\n" + " " * indent + r"(?:@\w+\s+)*fun (" + re.escape(fn) + r")\(")
        for match in pattern.finditer(text):
            start = match.end()
            depth, i = 1, start
            while depth:
                depth += {"(": 1, ")": -1}.get(text[i], 0)
                i += 1
            raw = text[start : i - 1]
            params = []
            defaults = []
            for part in _split_top(" ".join(raw.split())):
                name, _, rest = part.partition(":")
                ktype = _split_top(rest, "=")[0] if "=" in rest else rest.strip()
                params.append((name.strip(), ktype.strip()))
                defaults.append("=" in rest)
            if len(params) != len(api_method.argument_types):
                continue
            tail = text[i : i + 300]
            ret = ""
            ret_match = re.match(r"\s*:\s*([^{=\n]+?)\s*(?:\{|=|\n)", tail)
            if ret_match:
                ret = ret_match.group(1).strip()
            return KotlinFun(fn, tuple(params), ret, tuple(defaults))
        return None


# ---- Godot literals (STATIC_ARGS, PAIR_OVERRIDES) -> Kotlin -----------------------------------------------

_TOKEN_RE = re.compile(r'\s*(?:("(?:[^"\\]|\\.)*")|(-?\d+(?:\.\d+)?(?:e-?\d+)?)|([A-Za-z_][\w]*(?:\[\w+\])?(?:\.[A-Za-z_]\w*)*)|(.))')


def _tokens(text: str) -> list[str]:
    out = []
    for match in _TOKEN_RE.finditer(text):
        token = next(g for g in match.groups() if g is not None) if any(match.groups()) else None
        if token is not None and token.strip():
            out.append(token)
    return out


class LiteralError(ValueError):
    pass


def parse_literal(text: str):
    """A GDScript literal as a small tree: ("str", s) ("num", text) ("bool", b) ("null",) ("list", [..])
    ("dict", [(k, v)..]) ("call", name, [..]) ("const", "Class.NAME") ("new", "Class")."""
    tokens = _tokens(text)
    pos = 0

    def peek():
        return tokens[pos] if pos < len(tokens) else None

    def take(expected=None):
        nonlocal pos
        token = peek()
        if token is None or (expected is not None and token != expected):
            raise LiteralError(f"{text!r}: expected {expected or 'a value'} at token {pos} ({token!r})")
        pos += 1
        return token

    def items(close):
        out = []
        while peek() != close:
            out.append(value())
            if peek() == ",":
                take(",")
        take(close)
        return out

    def value():
        token = take()
        if token.startswith('"'):
            return ("str", json.loads(token))
        if re.fullmatch(r"-?\d+(?:\.\d+)?(?:e-?\d+)?", token):
            return ("num", token)
        if token in ("true", "false"):
            return ("bool", token == "true")
        if token == "null":
            return ("null",)
        if token == "[":
            return ("list", items("]"))
        if token == "{":
            pairs = []
            while peek() != "}":
                key = value()
                take(":")
                pairs.append((key, value()))
                if peek() == ",":
                    take(",")
            take("}")
            return ("dict", pairs)
        if re.fullmatch(r"[A-Za-z_][\w\[\]]*(?:\.\w+)*", token):
            if token.endswith(".new") and peek() == "(":
                take("(")
                take(")")
                return ("new", token.removesuffix(".new"))
            if peek() == "(":
                take("(")
                return ("call", token, items(")"))
            if "." in token:
                return ("const", token)
        raise LiteralError(f"{text!r}: cannot read {token!r}")

    tree = value()
    if pos != len(tokens):
        raise LiteralError(f"{text!r}: trailing input after the value")
    return tree


def kotlin_string(value: str) -> str:
    return '"' + value.replace("\\", "\\\\").replace('"', '\\"').replace("$", "\\$").replace("\n", "\\n") + '"'


VALUE_TYPES_3 = {"Vector3", "Vector3i"}
# Required list arguments of B and D rows, by Godot type: a shape the geometry rows can compute something
# from (a square, a tetrahedron's corners, the six planes of a box) rather than an empty list.
# The required arguments of B and D rows by Kotlin type, one value per occurrence of the type in a call
# (three or more, all different), so no two cells of one type carry the same value.
ARG_SEQUENCES = {
    "Int": ("7", "3", "5", "2"),
    "Long": ("7L", "3L", "5L", "2L"),
    "Double": ("0.75", "0.25", "0.5", "0.125"),
    "Float": ("0.75f", "0.25f", "0.5f", "0.125f"),
    "String": ('"kanama125"', '"kanama7"', '"kanama5"'),
    "NodePath": ('NodePath("kanama/125")', 'NodePath("kanama/7")', 'NodePath("kanama/5")'),
    "Vector2": ("Vector2(0.25, 0.5)", "Vector2(0.75, 1.0)", "Vector2(1.5, 0.125)", "Vector2(2.0, 0.375)"),
    "Vector2i": ("Vector2i(7, 3)", "Vector2i(3, 7)", "Vector2i(5, 2)", "Vector2i(2, 5)"),
    "Vector3": (
        "Vector3(0.25, 0.5, 0.75)",
        "Vector3(1.0, 0.5, 0.25)",
        "Vector3(0.5, 1.5, 0.125)",
        "Vector3(2.0, 0.375, 1.25)",
        "Vector3(0.625, 2.5, 0.875)",
    ),
    "Vector3i": ("Vector3i(7, 3, 5)", "Vector3i(3, 7, 5)", "Vector3i(5, 2, 7)", "Vector3i(2, 5, 3)"),
    "Color": ("Color(0.25, 0.5, 0.75, 1.0)", "Color(1.0, 0.75, 0.5, 0.25)", "Color(0.5, 0.25, 1.0, 0.75)"),
    "Rect2": (
        "Rect2(Vector2(1.0, 2.0), Vector2(3.0, 4.0))",
        "Rect2(Vector2(4.0, 3.0), Vector2(2.0, 1.0))",
        "Rect2(Vector2(0.5, 1.5), Vector2(2.5, 0.25))",
    ),
    "Rect2i": (
        "Rect2i(Vector2i(1, 2), Vector2i(3, 4))",
        "Rect2i(Vector2i(4, 3), Vector2i(2, 1))",
        "Rect2i(Vector2i(5, 7), Vector2i(1, 6))",
    ),
}
ARG_LISTS = {
    "PackedVector2Array": "listOf(Vector2(0.0, 0.0), Vector2(4.0, 0.0), Vector2(4.0, 4.0), Vector2(0.0, 4.0))",
    "PackedVector3Array": "listOf(Vector3(0.0, 0.0, 0.0), Vector3(1.0, 0.0, 0.0), Vector3(0.0, 1.0, 0.0), Vector3(0.0, 0.0, 1.0))",
    "typedarray::Plane": (
        "listOf(Plane(1.0, 0.0, 0.0, 1.0), Plane(-1.0, 0.0, 0.0, 1.0), Plane(0.0, 1.0, 0.0, 1.0), "
        "Plane(0.0, -1.0, 0.0, 1.0), Plane(0.0, 0.0, 1.0, 1.0), Plane(0.0, 0.0, -1.0, 1.0))"
    ),
}
REAL_TYPES = {"Vector2", "Vector3", "Vector4", "Rect2", "Transform2D", "Transform3D", "Basis", "Quaternion", "Plane", "AABB", "Color"}
ENUM_KOTLIN_RE = re.compile(r"^[A-Z]\w*(?:\.[A-Z]\w*)?$")


class RowContext:
    """The Kotlin statements one row needs before its calls: fresh objects, and the enum lookups."""

    def __init__(self, gen: "RowGenerator") -> None:
        self.gen = gen
        self.lines: list[str] = []
        self.count = 0

    def fresh(self, cls: str) -> str:
        receiver, why = self.gen.rules.receiver(cls)
        if receiver is None or "(" in receiver:
            raise LiteralError(f"no fresh instance of {cls}: {why or receiver}")
        var = f"f{self.count}"
        self.count += 1
        self.lines.append(f"val {var} = {self.gen.construct(receiver)}")
        return var


class RowGenerator:
    def __init__(self, inv: Inventory, policy) -> None:
        self.inv = inv
        self.rules = inv.rules
        self.policy = policy
        self.tree = KotlinTree(self.rules)
        self.failures: list[str] = []
        self.godot_constants = self._constants()

    def _constants(self) -> dict[str, int]:
        """`Class.NAME` -> value for every class enum value and integer constant."""
        out: dict[str, int] = {}
        for name, raw in self.rules.raw.items():
            for enum in raw.get("enums", []) or []:
                for value in enum.get("values", []):
                    out[f"{name}.{value['name']}"] = int(value["value"])
            for constant in raw.get("constants", []) or []:
                out[f"{name}.{constant['name']}"] = int(constant["value"])
        return out

    def kotlin_class(self, cls: str) -> str:
        return {v: k for k, v in WRAPPER_CLASS_ALIASES.items()}.get(cls, cls)

    def is_refcounted(self, cls: str) -> bool:
        return "RefCounted" in self.rules.chain(cls)

    def construct(self, cls: str) -> str:
        kotlin = self.kotlin_class(cls)
        how = "ref" if self.is_refcounted(cls) else "obj"
        return f'r.{how}({kotlin}(r.construct("{cls}")))'

    # -- a literal in a parameter of a known Kotlin type --

    def literal(self, tree, ktype: str, ctx: RowContext) -> str:
        ktype = ktype.strip()
        nullable = ktype.endswith("?")
        base = ktype.removesuffix("?")
        kind = tree[0]
        if kind == "null":
            if not nullable and base != "Any":
                raise LiteralError(f"null for the non-null {ktype}")
            return "null"
        if kind == "bool":
            return "true" if tree[1] else "false"
        if kind == "str":
            if base == "NodePath":
                return f"NodePath({kotlin_string(tree[1])})"
            return kotlin_string(tree[1])
        if kind == "num":
            return self.number(tree[1], base)
        if kind == "const":
            if tree[1] not in self.godot_constants:
                raise LiteralError(f"{tree[1]} is not a constant of the API")
            return self.number(str(self.godot_constants[tree[1]]), base, comment=tree[1].split(".")[-1])
        if kind == "list":
            element = self.list_element(base)
            if not tree[1]:
                return "emptyList()"
            return f"listOf<{element}>(" + ", ".join(self.literal(item, element, ctx) for item in tree[1]) + ")"
        if kind == "dict":
            if not tree[1]:
                return "emptyMap()"
            return "mapOf<String, Any?>(" + ", ".join(
                f"{self.literal(k, 'String', ctx)} to {self.literal(v, 'Any?', ctx)}" for k, v in tree[1]
            ) + ")"
        if kind == "new":
            return ctx.fresh(tree[1])
        if kind == "call":
            return self.call(tree[1], tree[2], base, ctx)
        raise LiteralError(f"cannot write {tree!r} as {ktype}")

    def number(self, text: str, base: str, comment: str = "") -> str:
        note = f" /* {comment} */" if comment else ""
        is_float = "." in text or "e" in text
        if base in ("Double", "Any"):
            return (text if is_float else f"{text}.0" if base == "Double" else f"{text}L") + note
        if base == "Float":
            return f"{text}f" + note
        if is_float:
            raise LiteralError(f"{text} for the integer type {base}")
        if base == "Int":
            return text + note
        if base == "Long":
            return f"{text}L" + note
        if ENUM_KOTLIN_RE.match(base) or base.startswith(API_PACKAGE_PREFIX):
            return f"{base}({text}L){note}"
        raise LiteralError(f"{text} for {base}")

    def list_element(self, base: str) -> str:
        match = re.fullmatch(r"List<(.+)>", base)
        if match:
            return match.group(1)
        if base == "Any":
            return "Any?"
        raise LiteralError(f"a list for {base}")

    def call(self, name: str, args: list, base: str, ctx: RowContext) -> str:
        if name == "scratch":
            return f"r.scratch({kotlin_string(args[0][1])})"
        if name == "fixture":
            if base == "ByteArray":
                return f"r.fixtureBytes({kotlin_string(args[0][1])})"
            if base in ("String", "Any"):
                return f"r.fixtureText({kotlin_string(args[0][1])})"
            raise LiteralError(f"fixture({args[0][1]!r}) for {base}")
        if name == "PackedByteArray":
            if not args:
                return "ByteArray(0)"
            return "byteArrayOf(" + ", ".join(self.number(a[1], "Int") for a in args) + ")"
        if re.fullmatch(r"Packed\w+Array", name) and not args:
            return "emptyList()"
        if re.fullmatch(r"Array\[\w+\]", name):
            if args and args[0] == ("list", []):
                return "emptyList()"
            return self.literal(args[0], base, ctx)
        if name == "Transform3D" and not args:
            return "Transform3D.IDENTITY"
        if name == "Transform3D" and len(args) == 4:
            axes = [self.literal(a, "Vector3", ctx) for a in args]
            return f"Transform3D(Basis({axes[0]}, {axes[1]}, {axes[2]}), {axes[3]})"
        if name == "Basis" and len(args) == 3:
            axes = [self.literal(a, "Vector3", ctx) for a in args]
            return f"Basis({axes[0]}, {axes[1]}, {axes[2]})"
        if name in ("Rect2i", "Rect2") and len(args) == 4:
            vec, kind = ("Vector2i", "Int") if name == "Rect2i" else ("Vector2", "Double")
            nums = [self.number(a[1], kind) for a in args]
            return f"{name}({vec}({nums[0]}, {nums[1]}), {vec}({nums[2]}, {nums[3]}))"
        if name == "Transform2D" and not args:
            return "Transform2D.IDENTITY"
        if name in ("Vector2", "Vector3", "Vector4", "Color", "Quaternion", "Plane"):
            kind = "Double"
            return f"{name}(" + ", ".join(self.number(a[1], kind) for a in args) + ")"
        if name in ("Vector2i", "Vector3i", "Vector4i"):
            return f"{name}(" + ", ".join(self.number(a[1], "Int") for a in args) + ")"
        if name == "NodePath":
            return f"NodePath({kotlin_string(args[0][1])})"
        raise LiteralError(f"cannot write {name}(...) as {base}")

    # -- the value an A row sets: two candidates, the row picks the one the object does not hold --

    def pair_candidates(self, ktype: str, godot_type: str, ctx: RowContext) -> tuple[str, str]:
        base = ktype.removesuffix("?")
        simple = {
            "Boolean": ("true", "false"),
            "Int": ("7", "3"),
            "Long": ("7L", "3L"),
            "Double": ("0.75", "0.25"),
            "Float": ("0.75f", "0.25f"),
            "String": ('"kanama125"', '"kanama7"'),
            "NodePath": ('NodePath("kanama/125")', 'NodePath("kanama/7")'),
            "RID": ("RID(7L)", "RID(3L)"),
            "Vector2": ("Vector2(0.25, 0.5)", "Vector2(0.75, 1.0)"),
            "Vector2i": ("Vector2i(7, 3)", "Vector2i(3, 7)"),
            "Vector3": ("Vector3(0.25, 0.5, 0.75)", "Vector3(1.0, 0.5, 0.25)"),
            "Vector3i": ("Vector3i(7, 3, 5)", "Vector3i(3, 7, 5)"),
            "Vector4": ("Vector4(0.25, 0.5, 0.75, 1.0)", "Vector4(1.0, 0.75, 0.5, 0.25)"),
            "Color": ("Color(0.25, 0.5, 0.75, 1.0)", "Color(1.0, 0.75, 0.5, 0.25)"),
            "Rect2": ("Rect2(Vector2(1.0, 2.0), Vector2(3.0, 4.0))", "Rect2(Vector2(4.0, 3.0), Vector2(2.0, 1.0))"),
            "Rect2i": ("Rect2i(Vector2i(1, 2), Vector2i(3, 4))", "Rect2i(Vector2i(4, 3), Vector2i(2, 1))"),
            "Transform2D": (
                "Transform2D(Vector2(1.0, 0.0), Vector2(0.0, 1.0), Vector2(7.0, 3.0))",
                "Transform2D(Vector2(1.0, 0.0), Vector2(0.0, 1.0), Vector2(3.0, 7.0))",
            ),
            "Transform3D": (
                "Transform3D(Basis.IDENTITY, Vector3(1.0, 2.0, 3.0))",
                "Transform3D(Basis.IDENTITY, Vector3(3.0, 2.0, 1.0))",
            ),
            "Basis": (
                "Basis(Vector3(2.0, 0.0, 0.0), Vector3(0.0, 3.0, 0.0), Vector3(0.0, 0.0, 4.0))",
                "Basis(Vector3(4.0, 0.0, 0.0), Vector3(0.0, 3.0, 0.0), Vector3(0.0, 0.0, 2.0))",
            ),
            "Quaternion": ("Quaternion(0.5, 0.5, 0.5, 0.5)", "Quaternion(0.0, 0.0, 0.0, 1.0)"),
            "Plane": ("Plane(0.0, 1.0, 0.0, 0.5)", "Plane(1.0, 0.0, 0.0, 0.25)"),
            "AABB": ("AABB(Vector3(1.0, 2.0, 3.0), Vector3(4.0, 5.0, 6.0))", "AABB(Vector3(6.0, 5.0, 4.0), Vector3(3.0, 2.0, 1.0))"),
            "ByteArray": ("byteArrayOf(1, 2, 3)", "byteArrayOf(3, 2, 1)"),
            "Any": ('"kanama125"', "7L"),
            "Map<String, Any?>": ('mapOf<String, Any?>("kanama" to 125L)', 'mapOf<String, Any?>("kanama" to 7L)'),
        }
        if base in simple:
            return simple[base]
        if godot_type.startswith(("enum::", "bitfield::")):
            spec = enum_spec(godot_type)
            values = [v for v in spec.values if not v.name.endswith("_MAX")]
            distinct = []
            for value in values:
                if value.value not in [d.value for d in distinct]:
                    distinct.append(value)
            if len(distinct) < 2:
                raise LiteralError(f"{godot_type} has fewer than two values")
            kotlin = base
            return tuple(f"{kotlin}({v.value}L) /* {v.name} */" for v in distinct[:2])  # type: ignore[return-value]
        match = re.fullmatch(r"List<(.+)>", base)
        if match:
            element = match.group(1)
            inner = self._element_godot_type(godot_type)
            a, b = self.pair_candidates(element, inner, ctx)
            return (f"listOf<{element}>({a})", f"listOf<{element}>({b})")
        if base in self.rules.api or base in WRAPPER_CLASS_ALIASES:
            cls = WRAPPER_CLASS_ALIASES.get(base, base)
            return (ctx.fresh(cls), ctx.fresh(cls))
        raise LiteralError(f"no pair value for the Kotlin type {ktype} (Godot {godot_type})")

    def _element_godot_type(self, godot_type: str) -> str:
        if godot_type.startswith("typedarray::"):
            return godot_type.removeprefix("typedarray::")
        packed = {
            "PackedStringArray": "String",
            "PackedInt32Array": "int",
            "PackedInt64Array": "int",
            "PackedFloat32Array": "float",
            "PackedFloat64Array": "float",
            "PackedVector2Array": "Vector2",
            "PackedVector3Array": "Vector3",
            "PackedColorArray": "Color",
            "PackedByteArray": "int",
        }
        return packed.get(godot_type, "Variant")

    # -- rows --

    def rows(self) -> list[ExerciseRow]:
        out: list[ExerciseRow] = []
        pairs: dict[tuple[str, str, str], ExerciseRow] = {}
        by_helper: dict[str, list[Site]] = collections.defaultdict(list)
        for helper, site in self.inv.sites:
            by_helper[helper].append(site)
        for helper, row in sorted(self.inv.rows.items()):
            chosen = []
            if row.case == "C":
                chosen.append((row.site, row.verdict))
                if row.secondary is not None and row.secondary[1].case in "ABD":
                    chosen.append(row.secondary)
            elif row.case in "ABD":
                chosen.append((row.site, row.verdict))
            for site, verdict in chosen:
                if verdict.row == "factory":
                    continue  # every factory method gets its own row below
                try:
                    if verdict.case == "C":
                        out.append(self.static_row(helper, site, verdict))
                    elif verdict.case == "A":
                        prop_key = verdict.row or "instance"
                        if verdict.row == "keyed-pair":
                            setter = self.keyed_setter(site.cls, site.method)
                            key = ("keyed", site.cls, setter)
                        else:
                            prop = self.rules.pair_props[(site.cls, site.method)]
                            key = (verdict.target or site.cls, site.cls, str(prop["name"]))
                        if key in pairs:
                            pairs[key].helpers.append(helper)
                            continue
                        if verdict.row == "keyed-pair":
                            made = self.keyed_pair_row(helper, site, verdict, key[2])
                        else:
                            made = self.pair_row(helper, site, verdict, prop, verdict.target or site.cls, prop_key)
                        pairs[key] = made
                        out.append(made)
                    else:
                        out.append(self.oracle_row_for(helper, site, verdict, by_helper[helper]))
                except LiteralError as error:
                    self.failures.append(f"row {helper} ({site.cls}.{site.method}): {error}")
        # Every SINGLETON_PAIRS property gets its row, also where its helpers' best row is elsewhere (the
        # setter's helper is usually shared with an instance setter that sorts first): each is process-wide
        # state of its own, labelled with its helpers.
        for helper, site in sorted(self.inv.sites, key=lambda hs: (hs[1].cls, hs[1].method, hs[0])):
            verdict = self.rules.site(site)
            if verdict.row != "singleton-pair":
                continue
            prop = self.rules.pair_props[(site.cls, site.method)]
            key = (site.cls, site.cls, str(prop["name"]))
            if key in pairs:
                if helper not in pairs[key].helpers:
                    pairs[key].helpers.append(helper)
                continue
            try:
                made = self.pair_row(helper, site, verdict, prop, site.cls, "singleton-pair")
            except LiteralError as error:
                self.failures.append(f"singleton pair row {site.cls}.{prop['name']}: {error}")
                continue
            pairs[key] = made
            out.append(made)
        # One factory row per factory method the tree calls (several share a helper: each is its own
        # create path through the server), labelled with its helper.
        factories: dict[tuple[str, str], list[str]] = collections.defaultdict(list)
        verdicts: dict[tuple[str, str], tuple[Site, Verdict]] = {}
        for helper, site in self.inv.sites:
            verdict = self.rules.site(site)
            if verdict.row == "factory" and helper not in factories[(site.cls, site.method)]:
                factories[(site.cls, site.method)].append(helper)
                verdicts[(site.cls, site.method)] = (site, verdict)
        for key in sorted(factories):
            site, verdict = verdicts[key]
            try:
                out.append(self.factory_row(sorted(factories[key]), site, verdict))
            except LiteralError as error:
                self.failures.append(f"factory row {site.cls}.{site.method}: {error}")
        return out

    def keyed_setter(self, cls: str, method: str) -> str:
        for (owner, setter), (getter, _key, _reason) in self.policy.SINGLETON_KEYED_PAIRS.items():
            if owner == cls and method in (setter, getter):
                return setter
        raise LiteralError(f"{cls}.{method} is no SINGLETON_KEYED_PAIRS half")

    # -- receivers and arguments of the B and D rows --

    def receiver_lines(self, site: Site, verdict: Verdict) -> tuple[list[str], str, str]:
        """(statements, Kotlin receiver of the wrapper call, receiver of the Variant-path call)."""
        if verdict.case == "D" or site.cls in self.rules.singletons:
            return [f'val s = r.singleton("{site.cls}")'], self.kotlin_class(site.cls), "s"
        target = verdict.target or site.cls
        if "(" in target:
            owner, method = target.removesuffix("()").split(".")
            fn = method_function_name(owner, method)
            return [f"val o = r.track(checkNotNull({self.kotlin_class(owner)}.{fn}()))"], "o", "o"
        return [f"val o = {self.construct(target)}"], "o", "o"

    def server_call(self, server: str, method: str, receiver_server: str, wrapper: str) -> str:
        """The Kotlin call of [server].[method]: on the row's own receiver when it is that server (a
        TextServer instance), else on the singleton object."""
        fn = method_function_name(server, method)
        if server == receiver_server and wrapper == "o":
            return f"o.{fn}"
        if server not in self.rules.singletons:
            raise LiteralError(f"{server}.{method}: not a singleton and not the row's receiver")
        return f"{self.kotlin_class(server)}.{fn}"

    def rid_source(self, cls: str, arg: str) -> str | None:
        """'Server.factory' for a RID argument [arg] of a [cls] row: RID_ARG_SOURCES, else the class's own
        argument-free `<arg>_create`, else None (the row passes an invalid RID)."""
        named = self.policy.RID_ARG_SOURCES.get((cls, arg))
        if named is not None:
            return named
        own = self.rules.api[cls].methods.get(f"{arg}_create") if cls in self.rules.api else None
        if own and self.rules.factory(cls, own[0]):
            return f"{cls}.{arg}_create"
        return None

    def arg_value(self, godot_type: str, ktype: str, name: str, site: Site, ctx: RowContext, occurrence: int = 0) -> str:
        """A recognisable argument for a required parameter (design 2: per type a small non-default
        literal; a path under the scratch directory; a fresh instance for an object). [occurrence]
        counts the earlier required arguments of the same type in the call: each occurrence gets its
        own value (ARG_SEQUENCES, three or more per type), so a helper that swaps any two cells of one
        type passes different values and the oracle sees it."""
        base = ktype.removesuffix("?")
        if godot_type == "String" and PATH_ARG_RE.search(name):
            return f'r.scratch("{site.cls}.{site.method}.{name}")'
        if godot_type.startswith(("enum::", "bitfield::")):
            spec = enum_spec(godot_type)
            values = [v for v in spec.values if not v.name.endswith("_MAX")]
            distinct = []
            for value in [v for v in values if v.value != 0] + [v for v in values if v.value == 0]:
                if value.value not in [d.value for d in distinct]:
                    distinct.append(value)
            if not distinct:
                raise LiteralError(f"{godot_type} has no value")
            chosen = distinct[occurrence % len(distinct)]
            return f"{base}({chosen.value}L) /* {chosen.name} */"
        if godot_type == "bool":
            return "true" if occurrence % 2 == 0 else "false"
        if godot_type == "RID":
            return "RID(0L)"
        rich = ARG_LISTS.get(godot_type)
        if rich is not None:
            return rich
        if godot_type in ("Variant",):
            return '"kanama125"'
        if base in self.rules.api or base in WRAPPER_CLASS_ALIASES:
            cls = WRAPPER_CLASS_ALIASES.get(base, base)
            receiver, _ = self.rules.receiver(cls)
            if receiver is None or "(" in receiver:
                if ktype.endswith("?"):
                    return "null"
                raise LiteralError(f"no instance of {cls} for the non-null argument `{name}`")
            return ctx.fresh(cls)
        sequence = ARG_SEQUENCES.get(base)
        if sequence is not None:
            return sequence[occurrence % len(sequence)]
        candidates = self.pair_candidates(ktype, godot_type, ctx)
        return candidates[occurrence % len(candidates)]

    def default_value(self, text: str, ktype: str, ctx: RowContext) -> str:
        """The extension_api.json default of a parameter the Kotlin declaration gives no default."""
        text = text.strip()
        if text.startswith(("&", "^")):
            text = text[1:]
        if text == "RID()":
            return "RID(0L)"
        match = re.fullmatch(r"Transform2D\((.*)\)", text)
        if match:
            nums = [n.strip() for n in match.group(1).split(",")]
            vecs = [f"Vector2({self.number(nums[i], 'Double')}, {self.number(nums[i + 1], 'Double')})" for i in (0, 2, 4)]
            return f"Transform2D({', '.join(vecs)})"
        return self.literal(parse_literal(text), ktype, ctx)

    def call_args(self, fun: KotlinFun, method: ApiMethod, site: Site, ctx: RowContext, lines: list[str],
                  frees: list[str], receiver_server: str, wrapper: str) -> list[str]:
        """The argument names of a B/D row's call: every required parameter, and the defaulted ones up to
        the last whose Kotlin declaration has no default (the rest take their defaults on both paths)."""
        needed = 0
        for index, default in enumerate(method.argument_defaults):
            kotlin_default = fun.defaults[index] if index < len(fun.defaults) else False
            if default is None or not kotlin_default:
                needed = index + 1
        names = []
        occurrences: collections.Counter = collections.Counter()
        chosen = list(getattr(self.policy, "ROW_ARGS", {}).get((site.cls, site.method), ()))
        for index in range(needed):
            pname, ptype = fun.params[index]
            gtype = method.argument_types[index]
            aname = method.argument_names[index]
            default = method.argument_defaults[index]
            if default is not None:
                expr = self.default_value(default, ptype, ctx)
            elif chosen:
                expr = self.literal(parse_literal(str(chosen.pop(0))), ptype, ctx)
            elif gtype == "RID" and (source := self.rid_source(site.cls, aname)) is not None:
                # Made inside the row's guarded block, freed in its finally once it was made.
                server, factory = source.split(".")
                free = self.policy.RID_FACTORIES[server][0]
                lines.append(f"var x{index} = RID(0L)")
                frees.append(
                    f"x{index} = {self.server_call(server, factory, receiver_server, wrapper)}()\0"
                    f"if (x{index}.value != 0L) {self.server_call(server, free, receiver_server, wrapper)}(x{index})"
                )
                names.append(f"x{index}")
                continue
            else:
                base = ptype.removesuffix("?")
                expr = self.arg_value(gtype, ptype, aname, site, ctx, occurrences[base])
                occurrences[base] += 1
            lines.append(f"val a{index}: {ptype} = {expr}")
            names.append(f"a{index}")
        return names

    def oracle_row_for(self, helper: str, site: Site, verdict: Verdict, sites: list[Site]) -> ExerciseRow:
        """The helper's B or D row, from its best site; when that site's arguments cannot be written,
        the next site of the same case."""
        try:
            return self.oracle_row(helper, site, verdict)
        except LiteralError as first:
            for other, other_verdict in _ranked(self.rules, sites):
                if other == site or other_verdict.case != verdict.case or other_verdict.row:
                    continue
                try:
                    return self.oracle_row(helper, other, other_verdict)
                except LiteralError:
                    # justified: the next site of the same case is tried; with none left, the first error is raised
                    continue
            raise first

    def oracle_row(self, helper: str, site: Site, verdict: Verdict) -> ExerciseRow:
        fun = self.tree.function(site.cls, site.method, static=False)
        if fun is None:
            raise LiteralError(f"no Kotlin function {method_function_name(site.cls, site.method)} with Godot's arguments")
        method = self.rules.method(site.cls, site.method)
        ctx = RowContext(self)
        lines, wrapper, variant = self.receiver_lines(site, verdict)
        for prop, literal in getattr(self.policy, "ROW_RECEIVER_SETS", {}).get((site.cls, site.method), ()):
            lines.append(f'{variant}.set("{prop}", {self.literal(parse_literal(literal), "Any?", ctx)}) // ROW_RECEIVER_SETS')
        receiver_server = site.cls if wrapper == "o" else ""
        frees: list[str] = []
        args: list[str] = []
        names = self.call_args(fun, method, site, ctx, args, frees, receiver_server, wrapper)
        lines += ctx.lines + args
        oracle_args = "".join(f", {n}" for n in names)
        body = [
            f"val got = r.track({wrapper}.{fun.name}({', '.join(names)}))",
            "r.oracle()",
            f'val oracle = r.track({variant}.call("{site.method}"{oracle_args}))',
            "r.sameAsCall(got, oracle)",
        ]
        lines += self.guarded(body, frees)
        target = verdict.target
        label = f"{site.cls}.{site.method}" + (f" on {target}" if target else "")
        return ExerciseRow(verdict.case, [helper], site, verdict, label, lines)

    @staticmethod
    def guarded(body: list[str], frees: list[str]) -> list[str]:
        """[body] in a try whose finally runs [frees] in reverse. A free spelled `make\\0free` makes
        its resource first thing inside the try (so a failing later make still frees the earlier ones)."""
        if not frees:
            return body
        makes = [f.split("\0")[0] for f in frees if "\0" in f]
        cleanup = [f.split("\0")[-1] for f in frees]
        return (
            ["try {"]
            + [f"  {line}" for line in makes + body]
            + ["} finally {"]
            + [f"  {f}" for f in reversed(cleanup)]
            + ["}"]
        )

    def factory_row(self, helpers: list[str], site: Site, verdict: Verdict) -> ExerciseRow:
        fun = self.tree.function(site.cls, site.method, static=False)
        if fun is None:
            raise LiteralError(f"no Kotlin function {method_function_name(site.cls, site.method)}")
        lines, wrapper, variant = self.receiver_lines(site, verdict)
        receiver_server = site.cls
        free = self.policy.RID_FACTORIES[site.cls][0]
        free_call = self.server_call(site.cls, free, receiver_server, wrapper)
        # The factory through the wrapper and through the Variant path: a RID(0) from both is the host
        # (a renderer without decals, the headless dummy renderer), a RID(0) from the wrapper alone is ours.
        lines += ["var rid = RID(0L)", "var viaCall = RID(0L)"]
        body = [
            f"rid = {self.server_call(site.cls, site.method, receiver_server, wrapper)}()",
            f'viaCall = (r.track({variant}.call("{site.method}")) as? RID) ?: RID(0L)',
            "r.created(rid, viaCall)",
            "r.oracle()",
        ]
        read = self.factory_read(site.cls, site.method)
        if read is not None:
            body += [
                "if (rid.value != 0L) {",
                f"  val got = r.track({self.server_call(site.cls, read[0], receiver_server, wrapper)}(rid))",
                f'  val oracle = r.track({variant}.call("{read[0]}", rid))',
                f'  r.readBack(got, oracle, "{read[0]}")',
                "}",
            ]
        lines += [
            "try {",
            *[f"  {line}" for line in body],
            "} finally {",
            f"  if (viaCall.value != 0L) {free_call}(viaCall)",
            f"  if (rid.value != 0L) {free_call}(rid)",
            "}",
        ]
        target = verdict.target
        label = f"{site.cls}.{site.method}" + (f" on {target}" if target else "")
        return ExerciseRow(verdict.case, helpers, site, verdict, label, lines)

    def factory_read(self, cls: str, factory: str) -> tuple[str] | None:
        """The first const `<prefix>_get_*` / `<prefix>_is_*` of [cls] (in API order) that takes the
        created RID and nothing else required, and has a Kotlin function: the row's one read."""
        prefix = factory.removesuffix("_create") if factory.endswith("_create") else factory.removeprefix("create_")
        for name, methods in self.rules.api[cls].methods.items():
            m = methods[0]
            if not (name.startswith(f"{prefix}_get_") or name.startswith(f"{prefix}_is_")):
                continue
            if not m.is_const or m.return_type == "void" or not m.argument_types or m.argument_types[0] != "RID":
                continue
            if any(d is None for d in m.argument_defaults[1:]):
                continue
            fun = self.tree.function(cls, name, static=False)
            if fun is None or not all(fun.defaults[1:]):
                continue
            return (name,)
        return None

    def singleton_pair_lines(self, cls: str, setter: KotlinFun, getter: KotlinFun, key_expr: str, read_name: str,
                             a: str, b: str, chosen: str | None, ptype: str, restore: tuple[str, str],
                             grow: bool = False) -> list[str]:
        """A set/read-back row on process-wide singleton state. The original value is read and put back
        through the Variant path ([restore]: the read and the write expression, `{}` the value), never
        through the setter and getter under test, so a broken wrapper cannot leave the state wrong."""
        kotlin = self.kotlin_class(cls)
        key = f"{key_expr}, " if key_expr else ""
        get = f"{kotlin}.{getter.name}({key_expr})"
        lines = [
            f'val s = r.singleton("{cls}")',
            f"val original = r.track({restore[0]})",
            f"val before = r.track({get})",
            "try {",
        ]
        if chosen is not None:
            lines.append(f"  val v: {ptype} = {chosen} // PAIR_OVERRIDES")
        elif grow:
            lines.append(f"  val v = before + 1 // PAIR_GROW")
        else:
            lines.append(f"  val v = r.pick(before, {a}, {b})")
        lines.append(f"  {kotlin}.{setter.name}({key}v)")
        lines.append(f"  r.pair(v, before, r.track({get}), r.track(s.get({read_name})))")
        lines += [
            "} finally {",
            f"  {restore[1].format('original')}",
            f"  r.restored(original, r.track({restore[0]}))",
            "}",
        ]
        return lines

    def keyed_pair_row(self, helper: str, site: Site, verdict: Verdict, setter_name: str) -> ExerciseRow:
        getter_name, key_literal, _reason = self.policy.SINGLETON_KEYED_PAIRS[(site.cls, setter_name)]
        setter = self.tree.function(site.cls, setter_name, static=False)
        getter = self.tree.function(site.cls, getter_name, static=False)
        if setter is None or getter is None:
            raise LiteralError(f"no Kotlin {setter_name}/{getter_name} on {site.cls}")
        ctx = RowContext(self)
        key = self.literal(parse_literal(key_literal), setter.params[0][1], ctx)
        ptype = setter.params[1][1]
        a, b = self.pair_candidates(ptype, "Variant", ctx)
        lines = ctx.lines + [f"val key: {setter.params[0][1]} = {key}"]
        lines += self.singleton_pair_lines(
            site.cls, setter, getter, "key", "key", a, b, None, ptype,
            (f's.call("{getter_name}", key)', f's.call("{setter_name}", key, {{}})'),
        )
        return ExerciseRow("A", [helper], site, verdict, f"{site.cls}.{setter_name}/{getter_name}", lines)

    def static_row(self, helper: str, site: Site, verdict: Verdict) -> ExerciseRow:
        fun = self.tree.function(site.cls, site.method, static=True)
        if fun is None:
            raise LiteralError(f"no static Kotlin function {method_function_name(site.cls, site.method)} in {site.cls}.kt")
        method = self.rules.method(site.cls, site.method)
        ctx = RowContext(self)
        literals = self.policy.STATIC_ARGS.get((site.cls, site.method), ())
        args = []
        for (pname, ptype), literal in zip(fun.params, literals):
            args.append(self.literal(parse_literal(str(literal)), ptype, ctx))
        names = []
        lines = list(ctx.lines)
        for index, (expr, (_, ptype)) in enumerate(zip(args, fun.params)):
            lines.append(f"val a{index}: {ptype} = {expr}")
            names.append(f"a{index}")
        kotlin_cls = self.kotlin_class(site.cls)
        call_args = ", ".join(names)
        oracle_args = "".join(f", {n}" for n in names)
        lines.append(f"val got = r.track({kotlin_cls}.{fun.name}({call_args}))")
        result = getattr(self.policy, "STATIC_RESULTS", {}).get((site.cls, site.method))
        zero_hosts = getattr(self.policy, "STATIC_ZERO_ON", {}).get((site.cls, site.method))
        zero_on = f', zeroOn = "{",".join(zero_hosts[0])}"' if zero_hosts else ""
        ret = method.return_type
        # The wrapper's own result first: a known finding (KNOWN_FINDINGS) can excuse only the oracle half.
        if ret == "enum::Error" and not verdict.weak:
            lines.append("r.wrapperError(got.value)")
        elif ret in self.rules.api:
            lines.append(f'r.wrapperObject(got, "{ret}")')
        elif ret == "enum::Error":
            pass
        else:
            weak = verdict.weak or (result is not None and result[0] == "weak")
            lines.append(f"r.wrapperValue(got, weak = {'true' if weak else 'false'}{zero_on})")
        lines.append("r.oracle()")
        lines.append(f'val oracle = r.track(ClassDB.classCallStatic("{site.cls}", "{site.method}"{oracle_args}))')
        if ret == "enum::Error" and not verdict.weak:
            lines.append("r.staticError(got.value, oracle)")
        elif ret in self.rules.api:
            names, _ = getattr(self.policy, "STATIC_OBJECT_CHECKS", {}).get((site.cls, site.method), ((), ""))
            reads = "".join(f', "{n}"' for n in names)
            lines.append(f'r.staticObject(got, oracle, "{ret}"{reads})')
        elif result is not None and result[0] == "fresh":
            lines.append("r.staticFresh(got, oracle)")
        elif ret == "enum::Error":
            lines.append(f"r.staticValue(got.value, oracle, weak = true)")
        else:
            lines.append(f"r.staticValue(got, oracle, weak = {'true' if verdict.weak else 'false'}{zero_on})")
        return ExerciseRow("C", [helper], site, verdict, f"{site.cls}.{site.method}", lines)

    def pair_row(self, helper: str, site: Site, verdict: Verdict, prop: dict, receiver: str, kind: str = "instance") -> ExerciseRow:
        setter = self.tree.function(site.cls, str(prop["setter"]), static=False)
        getter = self.tree.function(site.cls, str(prop["getter"]), static=False)
        if setter is None or getter is None:
            raise LiteralError(f"no Kotlin setter/getter for {site.cls}.{prop['name']}")
        ptype = setter.params[0][1]
        setter_method = None
        for owner in self.rules.chain(site.cls):
            if str(prop["setter"]) in self.rules.api[owner].methods:
                setter_method = self.rules.api[owner].methods[str(prop["setter"])][0]
                break
        godot_type = setter_method.argument_types[0] if setter_method else ""
        ctx = RowContext(self)
        override = getattr(self.policy, "PAIR_OVERRIDES", {}).get((site.cls, str(prop["name"])))
        chosen = None
        if override is not None and override[0] is not None:
            chosen = self.literal(parse_literal(override[0]), ptype, ctx)
        else:
            a, b = self.pair_candidates(ptype, godot_type, ctx)
        if kind == "singleton-pair":
            name = f'"{prop["name"]}"'
            grow = (site.cls, str(prop["name"])) in getattr(self.policy, "PAIR_GROW", {})
            lines = ctx.lines + self.singleton_pair_lines(
                site.cls, setter, getter, "", name, *(("", "") if chosen is not None else (a, b)), chosen, ptype,
                (f"s.get({name})", f"s.set({name}, {{}})"), grow,
            )
            return ExerciseRow("A", [helper], site, verdict, f"{site.cls}.{prop['name']}", lines)
        lines = [f"val o = {self.construct(receiver)}"]
        lines += ctx.lines
        lines.append(f"val before = r.track(o.{getter.name}())")
        if chosen is not None:
            lines.append(f"val v: {ptype} = {chosen} // PAIR_OVERRIDES")
        else:
            lines.append(f"val v = r.pick(before, {a}, {b})")
        lines.append(f"o.{setter.name}(v)")
        lines.append(
            f'r.pair(v, before, r.track(o.{getter.name}()), r.track(o.get("{prop["name"]}")))'
        )
        label = f"{site.cls}.{prop['name']}" + (f" on {receiver}" if receiver != site.cls else "")
        return ExerciseRow("A", [helper], site, verdict, label, lines)


API_PACKAGE_PREFIX = "net.multigesture.kanama.api."


def render_rows(inv: Inventory, policy=policy_module) -> tuple[str, list[str], list[ExerciseRow]]:
    gen = RowGenerator(inv, policy)
    rows = gen.rows()
    counts = collections.Counter(r.case for r in inv.rows.values())
    out = [
        "// GENERATED by scripts/generate_api_exercise.py --report. DO NOT EDIT BY HAND: --check fails on any",
        "// difference. The API exercise rows (docs/reference/generated/api-exercise.md): compiled only into a",
        "// -PkanamaApiExercise=true (desktop/Android) or -PkanamaIosSelfTest=true (iOS) build, run by",
        "// ApiExerciseRunner. One function per row, straight-line code.",
        "@file:Suppress(\"UNUSED_VARIABLE\", \"RedundantSuppression\")",
        "",
        "package net.multigesture.kanama.exercise",
        "",
        "import net.multigesture.kanama.api.*",
        "import net.multigesture.kanama.types.*",
        "",
        "internal object ApiExerciseRows {",
        f"  /** Referenced helpers the inventory excludes (case E): reported, never run. */",
        f"  const val EXCLUDED: Int = {counts['E']}",
        "",
        f"  /** Rows in this file: {sum(1 for r in rows if r.case == 'C')} static (C), {sum(1 for r in rows if r.case == 'A')} pair (A), "
        f"{sum(1 for r in rows if r.case == 'B')} const (B), {sum(1 for r in rows if r.case == 'D')} singleton (D). */",
        f"  const val ROWS: Int = {len(rows)}",
        "",
    ]
    for case, title in ROW_RUNS:
        out.append(f"  fun run{title[0].upper()}{title[1:]}(r: ApiExerciseRunner) {{")
        for index, row in enumerate(r for r in rows if r.case == case):
            out.append(f"    {case.lower()}{index:03d}(r)")
        out.append("  }")
        out.append("")
    known = getattr(policy, "KNOWN_FINDINGS", {})
    labels = {(r.case, r.label) for r in rows}
    for key, (platforms, failure, reason, tracking) in known.items():
        if key not in labels:
            gen.failures.append(f"KNOWN_FINDINGS names {key[0]} {key[1]}, which is not a generated row; delete or fix the entry")
        if not platforms or set(platforms) - {"ios", "android", "desktop"}:
            gen.failures.append(f"KNOWN_FINDINGS[{key[0]} {key[1]}] names platforms {platforms!r}; use ios, android, desktop")
        if not failure or not reason or not tracking:
            gen.failures.append(
                f"KNOWN_FINDINGS[{key[0]} {key[1]}] needs the failure text it excuses, its reason and where it is tracked"
            )
    for case in ROW_CASES:
        for index, row in enumerate(r for r in rows if r.case == case):
            out.append(f"  // {row.verdict.reason}" + (f"; weak: {row.verdict.weak}" if row.verdict.weak else ""))
            out.append(f"  private fun {case.lower()}{index:03d}(r: ApiExerciseRunner) {{")
            finding = known.get((case, row.label))
            if finding is None:
                out.append(f'    r.begin(\'{case}\', "{" ".join(row.helpers)}", "{row.label}")')
            else:
                out.append(f'    r.begin(\'{case}\', "{" ".join(row.helpers)}", "{row.label}",')
                out.append(
                    f'      known = "{",".join(finding[0])}", knownWhy = {kotlin_string(finding[2])}, '
                    f"knownFailure = {kotlin_string(finding[1])})"
                )
            out.append("    try {")
            out += [f"      {line}" for line in row.body]
            out.append("    } catch (t: Throwable) {")
            out.append("      r.threw(t)")
            out.append("    }")
            out.append("    r.end()")
            out.append("  }")
            out.append("")
    out[-1:] = ["}"]
    return "\n".join(out) + "\n", gen.failures, rows


EXERCISE_PACKAGE_PATH = "net/multigesture/kanama/exercise/"


def exercise_entries(archive) -> list[str]:
    """The exercise classes in a jar, or in the jars and AARs an archive carries (an AAR's `classes.jar` and
    `libs/*.jar`, a mobile add-on zip's AAR)."""
    import io
    import zipfile

    # Files only: an incremental build can leave the package's (empty) directory entry behind.
    found = [n for n in archive.namelist() if n.startswith(EXERCISE_PACKAGE_PATH) and not n.endswith("/")]
    for name in archive.namelist():
        if name.endswith((".jar", ".aar")):
            with zipfile.ZipFile(io.BytesIO(archive.read(name))) as inner:
                found += [f"{name}!/{n}" for n in exercise_entries(inner)]
    return found


def check_jars(jars: list[Path]) -> int:
    """The rows are a Kanama test hook (-PkanamaApiExercise=true): no shipped or installed jar or AAR has one."""
    import zipfile

    failures = []
    for jar in jars:
        if not jar.is_file():
            failures.append(f"{jar}: no such jar")
            continue
        with zipfile.ZipFile(jar) as archive:
            found = exercise_entries(archive)
        if found:
            failures.append(
                f"{jar} carries {len(found)} API exercise class(es) ({found[0]}, ...): it was built with "
                "-PkanamaApiExercise=true, which is for the smokes only"
            )
    for failure in failures:
        print(f"{TAG} FAIL {failure}", file=sys.stderr)
    if failures:
        return 1
    print(f"{TAG} PASS no API exercise class in {', '.join(str(j) for j in jars)}")
    return 0


def row_summary(rows: list[ExerciseRow]) -> str:
    c = [r for r in rows if r.case == "C"]
    a = [r for r in rows if r.case == "A"]
    b = [r for r in rows if r.case == "B"]
    d = [r for r in rows if r.case == "D"]
    factories = sum(1 for r in rows if r.verdict.row == "factory")
    return (
        f"{len(c)} static rows ({sum(1 for r in c if r.verdict.weak)} weak), {len(a)} pair rows "
        f"covering {sum(len(r.helpers) for r in a)} helper rows, {len(b)} const rows, {len(d)} singleton rows "
        f"({factories} of the B/D rows are RID factories)"
    )


# ---- main --------------------------------------------------------------------------------------------------


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    mode = parser.add_mutually_exclusive_group(required=True)
    mode.add_argument("--report", action="store_true", help=f"write {REPORT_PATH.relative_to(ROOT)}")
    mode.add_argument("--check", action="store_true", help="fail on drift, a stale policy entry or an unclassified helper")
    mode.add_argument("--write-fixture", action="store_true", help="rewrite the fixture's expected output")
    mode.add_argument(
        "--check-jar",
        nargs="+",
        metavar="JAR",
        help="fail when a jar or AAR (a published or installed kanama.jar, a runtime AAR) carries an exercise class",
    )
    args = parser.parse_args()

    if args.check_jar:
        return check_jars([Path(j) for j in args.check_jar])

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
        rows_text, row_failures, rows = render_rows(inv)
        if row_failures:
            for failure in row_failures:
                print(f"{TAG} FAIL {failure}", file=sys.stderr)
            return 1
        REPORT_PATH.write_text(text, encoding="utf-8")
        ROWS_PATH.parent.mkdir(parents=True, exist_ok=True)
        ROWS_PATH.write_text(rows_text, encoding="utf-8")
        print(f"{TAG} wrote {REPORT_PATH.relative_to(ROOT)}: {len(inv.rows)} helpers {summary}")
        print(f"{TAG} wrote {ROWS_PATH.relative_to(ROOT)}: {row_summary(rows)}")
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
    rows_text, row_failures, rows = render_rows(inv)
    failures += row_failures
    if not ROWS_PATH.is_file() or ROWS_PATH.read_text(encoding="utf-8") != rows_text:
        failures.append(
            f"{ROWS_PATH.relative_to(ROOT)} is stale or hand-edited: regenerate with "
            "`python3 scripts/generate_api_exercise.py --report` and review the diff"
        )
    if failures:
        for failure in failures:
            print(f"{TAG} FAIL {failure}", file=sys.stderr)
        return 1
    print(
        f"{TAG} PASS {len(inv.rows)} referenced helpers classified ({summary}); report, fixture and rows current "
        f"({row_summary(rows)})"
    )
    return 0


if __name__ == "__main__":
    raise SystemExit(main())

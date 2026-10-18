# Writing These Docs

The docs have two halves. **Use Kanama** is for people making games: it gets
them from nothing installed to a Kotlin script running in a scene, then to a
shipped game. **Develop Kanama** is for people changing Kanama itself: the
architecture, the generator, the backends, the checks and the release. This
page is the style guide for both, and the user half follows it strictly.

## Who Reads the User Pages

A Godot developer who knows GDScript or C#, has IntelliJ IDEA or is willing to
install it, and wants to see Kotlin run in a scene within ten minutes. They
skim. They copy the code before they read the prose around it.

## Rules

1. **Second person, present tense.** "You press Build Scripts", not "Build
   Scripts is pressed" or "Kanama supports pressing Build Scripts".
2. **Headings are proper section titles in title case**, like a printed
   manual. Use the conventional ones where they fit: "Requirements",
   "Installation", "Next Steps", "Troubleshooting". Otherwise name the task as
   a noun phrase or a gerund ("Changing a Vector", "Calling a GDScript
   Autoload"), not the internal feature name ("Value Type Mutation"). Never a
   spoken fragment such as "What you need", "Where next" or "What just ran".
3. **Example before rule.** Show the five-line snippet, then state the one rule
   it demonstrates. A rule without code is not finished.
4. **Short sentences, one idea each.** No parenthesis inside a parenthesis. A
   sentence that needs a dash and a parenthesis is two sentences.
5. **No internal ids on user pages.** No task numbers, PR or issue numbers,
   check or script names, protocol numbers, and none of the words "parcel",
   "shape", "slice", "promoted", "helper", "marker" or "red run". A user page
   names a script only when the user runs it, such as
   `migrate_enum_constants.py` or `export_game_assemble.sh`.
6. **Caveats last, and linked.** A section describes the normal case. Platform
   differences go in one "On Other Platforms" paragraph at the end of the page.
7. **Limitations in one place.** One page lists what does not work, per
   platform, with a one-line reason and the workaround. Other pages link to it
   instead of restating it.
8. **No history on user pages.** "Before 0.5" belongs in the upgrade guide and
   the changelog. Write what is true now.
9. **No evidence on user pages.** A status is a word (Supported,
   Experimental) and a link. Dates, device models and measurements live in the
   Develop Kanama half.
10. **Numbers only when the reader acts on them:** a JDK version, a port, a
    size on disk. Not benchmark results.

## Before and After

| Before | After |
| --- | --- |
| "Kanama's current public onboarding path is a source checkout. Packaged desktop kits and store add-ons are buildable release artifacts, but they are download flows only after matching GitHub zip artifacts are published." | "You need Godot 4.7.2, a JDK 25, and one of two things: a Kanama desktop kit zip, or a checkout of this repository." |
| "Kanama now promotes generated wrappers in broad slices rather than maintaining a hand-written class list in this page." | "Every Godot 4.7 class has a Kotlin class of the same name. `CharacterBody3D.move_and_slide()` is `moveAndSlide()`. If a method is missing, the coverage page says why." |
| "Every Godot enum and bitfield is a typed Kotlin value class (since 0.5, task 128)." | "`PROCESS_MODE_ALWAYS` is `Node.ProcessMode.ALWAYS`, `KEY_ESCAPE` is `Key.ESCAPE`. A Godot enum is a type, so the compiler stops you from passing a `Long` where Godot wants a mode." |
| "The decided end state is unpack-and-play: exported desktop games ship a bundled, jlink-trimmed JVM runtime that the native bootstrap finds app-relative." | "An exported game carries its own trimmed Java runtime. Players install nothing." |
| "Kanama should feel familiar to Godot users coming from GDScript or C#, while still being honest about the places where Kotlin/JVM and GDExtension have different constraints." | "If you know Godot's API, you know Kanama's. This page shows how a Godot name becomes a Kotlin name, and the five places Kotlin behaves differently." |

## Where a Fact Goes

When you rewrite a page, move facts; do not lose them. A paragraph that leaves
a user page goes to the Develop Kanama page that owns the topic: evidence and
measurements to the platform pages, check mechanics to the contributor guide,
backend internals to the backend pages. If a fact is dropped because it is no
longer true, say so in the commit message.

## Lines the Checks Read

Some lines in the docs are read by scripts, so a rewrite keeps them:

- Lines with an HTML `kanama-claim` comment (`check_doc_claims.py`). The
  comment stays on the same line as the claim; the line may move to another
  page under `docs/`.
- `KANAMA-BLOCKED` comments (`audit_stale_blockers.py`). They travel with the
  paragraph they guard, unchanged. In prose, write the word in backticks with
  no parenthesis after it.
- The iOS size sentence and the Kotlin, KSP and coroutines version sentences in
  `reference/version-support.md` (`check_doc_claims.py`,
  `check_kotlin_pins.py`).
- `./gradlew` commands in the shell blocks of `README.md` and the Get Started
  pages (`check_gradle_templates_configure.sh`): every task must exist where
  the page runs it. A `cd kanama` line moves the block to the checkout; any
  other `cd` moves it to the game project.
- No local paths: write `/path/to/...`, never a home directory.

Before you push a docs change, run:

```sh
mkdocs build --strict
python3 scripts/check_doc_claims.py
python3 scripts/audit_stale_blockers.py
scripts/check_gradle_templates_configure.sh
```

`gate_red_runs.py` mutates exact sentences in some pages; when you edit one of
those pages, run its cases too (`python3 scripts/gate_red_runs.py --only <gate>`).

## Previewing the Site

```sh
pip install -r docs/requirements.txt
mkdocs serve
```

`mkdocs serve` rebuilds the site as you save and serves it on
`http://127.0.0.1:8000`.

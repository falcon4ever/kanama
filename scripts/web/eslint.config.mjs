// ESLint flat config for the Web export-smoke drivers (scripts/web/drivers).
//
// Motivation: task 86 — safari_webdriver.mjs collected the performance section
// (`const performance = await collectPerformance(evaluate);`) and never passed
// it into buildEnvelope. The variable was simply unused, from 2026-07-29 until
// a hand-run Safari gate finally hit the fallout on 2026-08-11. `no-unused-vars`
// flags that class of bug at introduction.
//
// Policy: correctness rules only (eslint/js recommended + no-unused-vars +
// no-undef). No formatting or style rules — the drivers are hand-written smoke
// tooling, not a styled application codebase.
import js from "@eslint/js";
import globals from "globals";

export default [
  {
    // The install itself, plus anything a local run may drop here.
    ignores: ["node_modules/"],
  },
  js.configs.recommended,
  {
    files: ["**/*.mjs"],
    languageOptions: {
      ecmaVersion: 2024,
      sourceType: "module",
      // The drivers are plain Node ESM (process, setTimeout, fetch, URL, ...).
      // Code they inject into the page travels as strings, so no browser
      // globals are needed here.
      globals: { ...globals.node },
    },
    rules: {
      // An argument that must exist for arity but is intentionally unused is
      // spelled with a leading underscore; everything else is an error.
      "no-unused-vars": [
        "error",
        { argsIgnorePattern: "^_", caughtErrorsIgnorePattern: "^_" },
      ],
      "no-undef": "error",
    },
  },
];

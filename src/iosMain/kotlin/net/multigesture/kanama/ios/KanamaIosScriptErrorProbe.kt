package net.multigesture.kanama.ios

/**
 * The debug self-test's deliberate script error (task 131 item 13), in a file of its own: a debug
 * device build maps this file's frames to their Kotlin line the way it maps the game's own scripts
 * (`generateIosDeviceDebugSourceLines` in build.gradle.kts), so the self-test can assert that a
 * report carries a file and a line on the phone.
 */
internal fun throwSelfTestScriptError(): Nothing =
  throw IllegalStateException("kanama self-test: deliberate script error")

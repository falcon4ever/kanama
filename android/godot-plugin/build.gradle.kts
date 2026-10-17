plugins {
    id("com.android.library") version "9.2.1" apply false
    // Pins the Kotlin that compiles the Android runtime to the root build's version; without it
    // AGP's built-in Kotlin (2.2.10 in AGP 9.2.1) compiles it.
    id("org.jetbrains.kotlin.android") version "2.4.21" apply false
}

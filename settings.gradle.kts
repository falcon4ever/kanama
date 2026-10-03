// The build pins `jvmToolchain(25)` in every module. With only another JDK installed (a JDK 26-only
// Linux box, kanama#277) Gradle would stop with "Cannot find a Java installation ... languageVersion=25".
// The foojay resolver is Gradle's standard toolchain auto-provisioning: a missing JDK 25 is downloaded
// once into ~/.gradle/jdks and reused. The pin stays 25; any JDK 25+ can run Gradle itself.
plugins { id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0" }

rootProject.name = "kanama"

include(":annotations")
include(":kanama-common-api")
include(":processor")
include(":project-scripts")
include(":web-runtime")

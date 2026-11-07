// Fetches what a Gradle build resolves, without compiling it: `./gradlew --init-script <this file> resolveBuildDependencies`,
// for the root build and for android/godot-plugin, which the kit builds on its own. gate-red-runs-nightly.yml starts with
// it, where a retry can ask a repository again: Gradle remembers a 404 for a day, so one bad minute of Maven Central would
// otherwise fail every later Gradle case of the shard. Only module artifacts: a dependency on a project has variants a
// consumer must choose from (the Android plugin's), and nothing to download. The iOS and Native configurations are left
// out: they do not resolve on every host, and no Linux shard builds them.
import org.gradle.api.artifacts.component.ModuleComponentIdentifier

val hostBound = Regex("ios|swift|cinterop|native", RegexOption.IGNORE_CASE)

allprojects {
    tasks.register("resolveBuildDependencies") {
        doLast {
            configurations
                .filter { it.isCanBeResolved && !hostBound.containsMatchIn(it.name) }
                .forEach { configuration ->
                    configuration.incoming.artifactView { componentFilter { it is ModuleComponentIdentifier } }.files.files
                }
        }
    }
}

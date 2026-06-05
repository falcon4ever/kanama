plugins {
    kotlin("multiplatform")
}

kotlin {
    iosArm64()
    iosSimulatorArm64()

    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>().configureEach {
        compilations.getByName("main") {
            cinterops {
                val kanama_ios by creating {
                    defFile(project.file("src/nativeInterop/cinterop/kanama_ios.def"))
                    includeDirs(rootProject.file("ios/include"))
                }
            }
        }

        binaries {
            staticLib {
                baseName = "kanama_ios_runtime"
            }
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib"))
            }
        }
    }
}

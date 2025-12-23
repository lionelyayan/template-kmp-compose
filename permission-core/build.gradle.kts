import org.jetbrains.kotlin.konan.target.HostManager

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.androidLint)

    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {

    androidLibrary {
        namespace = "com.permission.core"
        compileSdk = 36
        minSdk = 24
    }

    val xcfName = "permission-coreKit"

    if (HostManager.hostIsMac) {
        iosX64 {
            binaries.framework {
                baseName = xcfName
            }
        }
        iosArm64 {
            binaries.framework {
                baseName = xcfName
            }
        }
        iosSimulatorArm64 {
            binaries.framework {
                baseName = xcfName
            }
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.androidx.activity.ktx)
                implementation(libs.androidx.core.ktx)
            }
        }

        iosMain {
            dependencies {
                // iOS specific (jika perlu)
            }
        }
    }
}

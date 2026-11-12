plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "io.github.sangcomz.commercenavigation.core.navigation"
    compileSdk = 37
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures { compose = true }
}

dependencies {
    api(libs.navigation3.runtime)
    api(platform(libs.compose.bom))
    api(libs.compose.ui)
    testImplementation(libs.junit)
}

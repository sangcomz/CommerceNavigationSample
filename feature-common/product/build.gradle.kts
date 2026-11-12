plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "io.github.sangcomz.commercenavigation.feature.common.product"
    compileSdk = 37
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    api(project(":core:navigation"))
    testImplementation(libs.junit)
}

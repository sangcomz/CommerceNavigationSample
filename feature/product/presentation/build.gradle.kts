plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "io.github.sangcomz.commercenavigation.feature.product.presentation"
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
    implementation(project(":core:model"))
    implementation(project(":feature:product:domain"))
    implementation(project(":core:navigation"))
    implementation(project(":core:ui"))
    implementation(project(":feature-common:product"))
    implementation(project(":feature-common:cart"))
    implementation(libs.lifecycle.compose)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.hilt.viewmodel)
    implementation(libs.serialization)
    implementation(libs.coroutines)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material3)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    testImplementation(libs.junit)
}

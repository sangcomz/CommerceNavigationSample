plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "io.github.sangcomz.commercenavigation"
    compileSdk = 37
    defaultConfig {
        minSdk = 24
        applicationId = "io.github.sangcomz.commercenavigation"
        targetSdk = 36
        versionCode = 1
        versionName = "0.0.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures { compose = true }
}

dependencies {
    implementation(project(":feature:product:presentation"))
    implementation(project(":feature:product:data"))
    implementation(project(":feature:cart:presentation"))
    implementation(project(":feature:cart:data"))
    implementation(project(":feature:order:presentation"))
    implementation(project(":feature:order:data"))
    implementation(project(":core:navigation"))
    implementation(project(":core:ui"))
    implementation(project(":feature-common:product"))
    implementation(libs.activity.compose)
    implementation(libs.navigation3.ui)
    implementation(libs.lifecycle.navigation3)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material3)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.test)
    androidTestImplementation(libs.test.runner)
    androidTestImplementation(libs.test.junit)
    debugImplementation(libs.compose.test.manifest)
}

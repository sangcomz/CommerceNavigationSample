plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
}

val checkModuleDependencies by tasks.registering {
    group = "verification"
    doLast {
        subprojects.forEach { source ->
            val parts = source.path.split(":").filter(String::isNotBlank)
            source.configurations.filter { it.name in setOf("api", "implementation", "compileOnly", "runtimeOnly") }
                .flatMap { it.dependencies.withType<ProjectDependency>() }
                .forEach { dependency ->
                    val target = dependency.path.split(":").filter(String::isNotBlank)
                    val allowed = when (parts.firstOrNull()) {
                        "feature-common" -> target.firstOrNull() == "core"
                        "feature" -> when (target.firstOrNull()) {
                            "core", "feature-common" -> true
                            "feature" -> parts[1] == target[1] && target[2] == "domain" && parts[2] in setOf("data", "presentation")
                            else -> false
                        }
                        "core" -> target.firstOrNull() == "core"
                        else -> true
                    }
                    check(allowed) { "Forbidden dependency: ${source.path} -> ${dependency.path}" }
                }
        }
    }
}

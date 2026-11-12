pluginManagement {
    repositories { google(); mavenCentral(); gradlePluginPortal() }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories { google(); mavenCentral() }
}
rootProject.name = "CommerceNavigationSample"
include(":app")
include(":core:model")
include(":core:navigation")
include(":core:ui")
include(":feature-common:product")
include(":feature-common:cart")
include(":feature-common:order")
include(":feature:product:domain")
include(":feature:product:data")
include(":feature:product:presentation")
include(":feature:cart:domain")
include(":feature:cart:data")
include(":feature:cart:presentation")
include(":feature:order:domain")
include(":feature:order:data")
include(":feature:order:presentation")

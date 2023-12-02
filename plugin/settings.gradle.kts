rootProject.name = "code-counter-plugin"

include("app", "plugin")

pluginManagement{
    repositories{
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement{
    repositories{
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
    }
}
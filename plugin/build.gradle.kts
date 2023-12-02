plugins{
    `kotlin-dsl`
    `maven-publish`
    `java-gradle-plugin`
}

gradlePlugin{
    plugins{
        create("codeCounterPlugin"){
            id = "code.counter.plugin"
            displayName = "Simple Kotlin codecounter plugin"
            implementationClass = "code.counter.gradle.application.CodeCounterApplicationPlugin"
            version="1.0.0"
        }
    }
}

dependencies{
    api("gradle.plugin.com.google.cloud.tools:jib-gradle-plugin:3.1.2")

}
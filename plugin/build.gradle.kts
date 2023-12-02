plugins{
    `kotlin-dsl`
    `maven-publish`
    `java-gradle-plugin`
}

gradlePlugin{
    plugins{
        create("codeCounterPlugin"){
            id = "code.counter.plugin"
            implementationClass = "code.counter.gradle.application.CodeCounterApplicationPlugin"
        }
    }
}

dependencies{
    api("gradle.plugin.com.google.cloud.tools:jib-gradle-plugin:3.1.2")
}
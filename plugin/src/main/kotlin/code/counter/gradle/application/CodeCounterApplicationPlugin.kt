package com.example.plugin.code.counter.gradle.application

import com.google.cloud.tools.jib.gradle.JibExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaApplication
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.resolver.buildSrcSourceRootsFilePath
import kotlin.reflect.*

class CodeCounterApplicationPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.apply{
            plugin("org.gradle.application")
            plugin("com.google.cloud.tools.jib")
            //подключаем зависимости для плагина

        }

        //при применении плагина гредл создаст экстеншин с именем code-counter
        //по этому имени получим обьект класса ApplicationExtension

        target.extensions.create("code_counter",
            ApplicationExtension::class.java,
            target
            //передаем vararg параметры
        )

    }
}

abstract class ApplicationExtension(private val target: Project){
    private inline fun <reified T> extension() = target.extensions.getByType(T::class.java)

    val javaPlugin = extension<JavaPluginExtension>()
    val javaApplication = extension<JavaApplication>()


    init{
        println("Hello gradle!")
    }
}

//  ./gradlew p:pTML
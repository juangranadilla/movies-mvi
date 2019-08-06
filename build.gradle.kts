buildscript {
    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath(BuildPlugins.gradle)
        classpath(BuildPlugins.kotlin_gradle_plugin)
        classpath(BuildPlugins.gradle_versions_plugin)
        classpath(BuildPlugins.navigation_safeargs_plugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean",Delete::class) {
    delete(rootProject.buildDir)
}

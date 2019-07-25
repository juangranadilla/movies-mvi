buildscript {
    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath(BuildPlugins.gradle)
        classpath(BuildPlugins.kotlin_gradle_plugin)
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

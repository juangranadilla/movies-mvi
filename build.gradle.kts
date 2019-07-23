buildscript {
    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath(Config.Libs.gradle)
        classpath(Config.Libs.kotlin_gradle_plugin)
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

buildscript {
    extra["buildConfig"] = mapOf(
        "compileSdk" to AndroidConfig.COMPILE_SDK_VERSION,
        "minSdk" to AndroidConfig.MIN_SDK_VERSION,
        "targetSdk" to AndroidConfig.TARGET_SDK_VERSION,
        "buildTools" to AndroidConfig.BUILD_TOOLS_VERSION
    )
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath(buildcfg.GradlePlugins.androidGradlePlugin)
        classpath(buildcfg.Libs.Kotlin.kotlinGradlePlugin)
        classpath(buildcfg.GradlePlugins.gradleMavenPublishPlugin)
        classpath(buildcfg.GradlePlugins.gradleVersionsPlugin)
    }
}

subprojects {
    version = project.property("VERSION_NAME")!!
    group = project.property("GROUP")!!

    repositories {
        google()
        mavenCentral()
    }

    // https://kotlinlang.org/docs/gradle.html#compiler-options
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = buildcfg.Versions.JAVA_VERSION_STR
            // Sets up Kotlin’s Java interoperability to strictly follow JSR-305 annotations for increased null safety.
            freeCompilerArgs = listOf("-Xjsr305=strict")
            // Treat all Kotlin warnings as errors
            allWarningsAsErrors = false
        }
    }

    plugins.withType<com.vanniktech.maven.publish.MavenPublishPlugin> {
        configure<com.vanniktech.maven.publish.MavenPublishPluginExtension> {
            sonatypeHost = com.vanniktech.maven.publish.SonatypeHost.S01
        }
    }
}

apply(plugin = buildcfg.GradlePlugins.gradleVersionsPluginName)
tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
    checkForGradleUpdate = false
    outputFormatter = "plain,html"
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"
    // report only stable versions:
    fun isStable(version: String): Boolean {
        val stableKeyword = version.toUpperCase() in setOf("RELEASE", "FINAL", "GA")
        val regex = "^[0-9,.v-]+(-r)?$".toRegex()
        return stableKeyword || regex.matches(version)
    }
    rejectVersionIf { isStable(this.currentVersion) && !isStable(this.candidate.version) }
    // In other words, if the lib uses stable release, we'll get update only on stable versions.
    // If the lib uses any non-stable (alpha/beta/RC), we'll get update about the newer non-stable.
}

import org.gradle.kotlin.dsl.`kotlin-dsl`

// The kotlin-dsl plugin requires a repository to be declared
repositories {
    mavenCentral()
}

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

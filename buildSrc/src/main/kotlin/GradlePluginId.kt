object GradlePluginId {
    const val ANDROID_APPLICATION = "com.android.application"
    const val ANDROID_LIBRARY = "com.android.library"
    const val KOTLIN_ANDROID = "org.jetbrains.kotlin.android"
    const val KOTLIN_JVM = "org.jetbrains.kotlin.jvm"
    const val KOTLIN_KAPT = "org.jetbrains.kotlin.kapt"
    const val KOTLIN_PARCELIZE = "kotlin-parcelize"
    const val ANDROIDX_NAVIGATION_SAFEARGS = "androidx.navigation.safeargs"
    const val ANDROIDX_NAVIGATION_SAFEARGS_KOTLIN = "androidx.navigation.safeargs.kotlin"

    // https://developer.android.com/topic/libraries/view-binding/migration
    @Deprecated("using Kotlin synthetics for view binding is no longer supported")
    const val KOTLIN_ANDROID_EXTENSIONS = "kotlin-android-extensions"

    const val MAVEN_PUBLISH = "com.vanniktech.maven.publish"
}

// ktlint-disable max-line-length

@file:Suppress("MemberVisibilityCanBePrivate")

package buildcfg

import org.gradle.api.JavaVersion

object Versions {
    const val JAVA_VERSION_STR = "11"
    val JAVA_VERSION = JavaVersion.VERSION_11
}

object GradlePlugins {
    // https://developer.android.com/studio/releases/gradle-plugin
    // https://maven.google.com/web/index.html#com.android.tools.build:gradle
    const val agpVersion = "7.1.2"
    const val androidGradlePlugin = "com.android.tools.build:gradle:$agpVersion"
    // https://github.com/vanniktech/gradle-maven-publish-plugin/blob/master/CHANGELOG.md
    const val gradleMavenPublishPlugin = "com.vanniktech:gradle-maven-publish-plugin:0.19.0"
    // https://github.com/ben-manes/gradle-versions-plugin/releases
    const val gradleVersionsPlugin = "com.github.ben-manes:gradle-versions-plugin:0.42.0"
    const val gradleVersionsPluginName = "com.github.ben-manes.versions"
    // https://maven.google.com/web/index.html#com.google.gms:google-services
    const val googleServices = "com.google.gms:google-services:4.3.10"
}

object Libs {
    // https://github.com/JakeWharton/timber/releases
    const val timber = "com.jakewharton.timber:timber:5.0.1"

    object Google {
        object Android {
            // https://maven.google.com/web/index.html#com.google.android.material
            object Material {
                const val material = "com.google.android.material:material:1.5.0"
                const val materialObsolete = "com.google.android.material:material:1.0.0"
                // When using AppCompat theme w/ Compose (https://developer.android.com/jetpack/compose/interop/adding#reuse-view):
                const val composeThemeAdapter = "com.google.android.material:compose-theme-adapter:${AndroidX.Compose.version}"
            }
            const val playCore = "com.google.android.play:core:1.6.5"
        }
        // https://mvnrepository.com/artifact/com.google.accompanist
        // (strangely this com.google.accompanist is not available on maven.google.com)
        object Accompanist {
            private const val version = "0.20.3"
            // When using a MDC theme w/ Compose (https://developer.android.com/jetpack/compose/interop/adding#reuse-view):
            const val accompanistAppcompatTheme = "com.google.accompanist:accompanist-appcompat-theme:$version"
        }
        const val zxingCore = "com.google.zxing:core:3.4.1" // 3.3.2 is the last working with zxing-android-embedded:3.6.0
        const val truth = "com.google.truth:truth:1.0.1"
    }

    object Kotlin {
        // https://kotlinlang.org/docs/releases.html#release-details
        // https://github.com/JetBrains/kotlin/releases
        const val version = "1.6.10" // latest: 1.6.21, but better to wait to compose get support for 1.6.21
        const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        const val kotlinStdlib7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
        const val kotlinStdlib8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:$version"
        const val kotlinTestJunit = "org.jetbrains.kotlin:kotlin-test-junit:$version"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/
    // https://search.maven.org/search?q=g:org.jetbrains.kotlinx%20AND%20a:kotlinx-coroutines*
    object Coroutines {
        private const val version = "1.6.0"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val coroutinesRx2 = "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:$version"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val coroutinesPlayServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$version"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        const val coroutinesDebug = "org.jetbrains.kotlinx:kotlinx-coroutines-debug:$version"
    }

    object Dagger {
        // https://github.com/google/dagger/releases
        // https://repo1.maven.org/maven2/com/google/dagger/
        private const val version = "2.41"
        // WARNING:
        // There is an issue introduced in 2.40.4, that caused build failure.
        // The issue happen  to Jetpack Compose and Dagger.
        // More about the issue: https://github.com/google/dagger/issues/3090
        // An example build scan: https://scans.gradle.com/s/cndq2gclvgm4m/failure
        // Workaround: use 'api' for 'androidx.compose.runtime:runtime' from modules, or add it to the app module.
        const val dagger = "com.google.dagger:dagger:$version"
        const val daggerAndroid = "com.google.dagger:dagger-android:$version"
        const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:$version"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:$version"
        const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:$version"
        const val hiltAndroid = "com.google.dagger:hilt-android:$version"
        const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:$version"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:$version"
        const val hiltCore = "com.google.dagger:hilt-core:$version"
    }

    object AssistedInject {
        private const val version = "0.8.1"
        const val assistedInjectAnnotationDagger2 = "com.squareup.inject:assisted-inject-annotations-dagger2:$version"
        const val assistedInjectProcessorDagger2 = "com.squareup.inject:assisted-inject-processor-dagger2:$version"
        const val assistedInjectAnnotations = "com.squareup.inject:assisted-inject-annotations:$version"
        const val assistedInjectProcessor = "com.squareup.inject:assisted-inject-processor:$version"
        const val inflationInject = "com.squareup.inject:inflation-inject:$version"
        const val inflationInjectProcessor = "com.squareup.inject:inflation-inject-processor:$version"
    }

    object JavaxAnnotation {
        const val jsr250Api = "javax.annotation:jsr250-api:1.0"
        const val javaxAnnotationApi = "javax.annotation:javax.annotation-api:1.3.2"
    }

    // Sources: https://android.googlesource.com/platform/frameworks/support/+/HEAD/
    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.4.1"                      // https://maven.google.com/web/index.html#androidx.appcompat:appcompat
        // https://developer.android.com/jetpack/androidx/releases/recyclerview
        const val recyclerview = "androidx.recyclerview:recyclerview:1.2.1"             // https://maven.google.com/web/index.html#androidx.recyclerview:recyclerview
        const val cardview = "androidx.cardview:cardview:1.0.0"
        // https://developer.android.com/jetpack/androidx/releases/constraintlayout
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.1.3" // https://maven.google.com/web/index.html#androidx.constraintlayout:constraintlayout
        const val swypeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0" // latest: 1.2.0-alpha01
        const val legacySupportV4 = "androidx.legacy:legacy-support-v4:1.0.0"
        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val viewpager2 = "androidx.viewpager2:viewpager2:1.0.0" // latest: 1.1.0-alpha01
        const val startup = "androidx.startup:startup-runtime:1.1.0"
        // optional - Test helpers for LiveData
        const val archCoreTesting = "androidx.arch.core:core-testing:2.1.0"

        // https://developer.android.com/jetpack/androidx/releases/activity
        // https://maven.google.com/web/index.html#androidx.activity
        object Activity {
            private const val version = "1.4.0"
            const val activity = "androidx.activity:activity:$version"
            const val activityKtx = "androidx.activity:activity-ktx:$version"
            const val activityCompose = "androidx.activity:activity-compose:$version"
        }

        // https://maven.google.com/web/index.html#androidx.annotation
        object Annotation {
            const val annotation = "androidx.annotation:annotation:1.3.0"
        }

        // https://maven.google.com/web/index.html#android.arch.core
        object ArchCore {
            // https://mvnrepository.com/artifact/androidx.arch.core/core-testing
            const val coreTesting = "androidx.arch.core:core-testing:2.1.0"
        }

        object Compose {
            // Versions: https://developer.android.google.cn/jetpack/androidx/releases/compose?hl=en#versions
            // Sources: https://android.googlesource.com/platform/frameworks/support/+/refs/heads/androidx-compose-release/
            const val version = "1.1.1"

            // https://developer.android.com/jetpack/androidx/releases/compose-animation
            // https://maven.google.com/web/index.html#androidx.compose.animation
            object Animation {
                const val animation = "androidx.compose.animation:animation:$version"
            }
            // https://developer.android.com/jetpack/androidx/releases/compose-compiler
            // https://maven.google.com/web/index.html#androidx.compose.compiler
            object Compiler {
                // 1.0.5      - requires Kotlin 1.5.31
                // 1.1.0-rc01 - requires Kotlin 1.6.0
                // 1.1.0-rc02 - requires Kotlin 1.6.10
                const val version = Compose.version
                const val compiler = "androidx.compose.compiler:compiler:$version"
                // NOTE: Compose Compiler's version can be set explicitly
                //       with android.composeOptions.kotlinCompilerExtensionVersion.
            }
            // https://developer.android.com/jetpack/androidx/releases/compose-foundation
            // https://maven.google.com/web/index.html#androidx.compose.foundation
            object Foundation {
                const val foundation = "androidx.compose.foundation:foundation:$version"
            }
            // https://developer.android.com/jetpack/androidx/releases/compose-material
            // https://maven.google.com/web/index.html#androidx.compose.material
            object Material {
                const val material = "androidx.compose.material:material:$version"
                const val materialIconsCore = "androidx.compose.material:material-icons-core:$version"
                const val materialIconsExtended = "androidx.compose.material:material-icons-extended:$version"
            }
            // https://developer.android.com/jetpack/androidx/releases/compose-runtime
            // https://maven.google.com/web/index.html#androidx.compose.runtime
            object Runtime {
                const val runtime = "androidx.compose.runtime:runtime:$version"
                const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"
                const val runtimeRxjava2 = "androidx.compose.runtime:runtime-rxjava2:$version"
            }
            // https://developer.android.com/jetpack/androidx/releases/compose-ui
            // https://maven.google.com/web/index.html#androidx.compose.ui
            object Ui {
                const val ui = "androidx.compose.ui:ui:$version"
                // Tooling support (Previews, etc.)
                const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
                const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
                const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4:$version"
            }
        }

        // https://maven.google.com/web/index.html#androidx.databinding
        object DataBinding {
            // https://mvnrepository.com/artifact/androidx.databinding/databinding-compiler
            private const val version = GradlePlugins.agpVersion
            const val databindingCompiler = "androidx.databinding:databinding-compiler:$version"
            const val databindingRuntime = "androidx.databinding:databinding-runtime:$version"
        }

        // https://developer.android.com/jetpack/androidx/releases/fragment
        // https://maven.google.com/web/index.html#androidx.fragment
        object Fragment {
            private const val version = "1.4.1"
            const val fragment = "androidx.fragment:fragment:$version"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:$version"
        }

        // https://developer.android.com/jetpack/androidx/releases/hilt
        // https://maven.google.com/web/index.html#androidx.hilt
        object Hilt {
            private const val version = "1.0.0"
            const val hiltCommon = "androidx.hilt:hilt-common:$version"
            const val hiltCompiler = "androidx.hilt:hilt-compiler:$version"
            const val hiltLifecycleViewmodel = "hilt-lifecycle-viewmodel:$version"
            const val hiltNavigation = "hilt-navigation:$version"
            const val hiltNavigationCompose = "hilt-navigation-compose:$version"
            const val hiltNavigationFragment = "hilt-navigation-fragment:$version"
            const val hiltWork = "androidx.hilt:hilt-work:$version"
        }

        // https://developer.android.com/jetpack/androidx/releases/lifecycle
        // https://maven.google.com/web/index.html#androidx.lifecycle
        // https://mvnrepository.com/artifact/androidx.lifecycle
        // Sources: https://android.googlesource.com/platform/frameworks/support/+/refs/heads/androidx-main/lifecycle/
        object Lifecycle {
            private const val version = "2.4.1"
            // ViewModel and LiveData
            const val lifecycleCommon = "androidx.lifecycle:lifecycle-common:$version"
            const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val lifecycleProcess = "androidx.lifecycle:lifecycle-process:$version"
            // alternatively - just ViewModel
            const val lifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel:$version"
            const val lifecycleViewmodelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
            // alternatively - just ViewModel (for Kotlin)
            const val lifecycleViewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val lifecycleService = "androidx.lifecycle:lifecycle-service:$version"
            // alternatively - just LiveData
            const val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata:$version"
            // alternatively - Lifecycles only (no ViewModel or LiveData). Some UI
            //     AndroidX libraries use this lightweight import for Lifecycle
            const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:$version"
            const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            // Annotations compiler (when to use: https://stackoverflow.com/a/58286301/3023806)
            const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:$version"
            // alternately - if using Java8, use the following instead of lifecycle-compiler
            const val lifecycleCommonJava8 = "androidx.lifecycle:lifecycle-common-java8:$version"
            // optional - ReactiveStreams support for LiveData
            const val lifecycleReactivestreams = "androidx.lifecycle:lifecycle-reactivestreams:$version"
            // optional - ReactiveStreams support for LiveData (for Kotlin)
            const val lifecycleReactivestreamsKtx = "androidx.lifecycle:lifecycle-reactivestreams-ktx:$version"
        }

        // https://developer.android.com/jetpack/androidx/releases/navigation
        // https://maven.google.com/web/index.html#androidx.navigation
        object Navigation {
            private const val version = "2.4.1"
            const val navigationFragment = "androidx.navigation:navigation-fragment:$version"
            const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
            const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$version"
            const val navigationSafeArgsGradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }

        // https://maven.google.com/web/index.html#androidx.room
        // https://developer.android.com/jetpack/androidx/releases/room
        object Room {
            private const val roomVersion = "2.4.2"
            const val roomKtx = "androidx.room:room-ktx:$roomVersion"
            const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
            const val roomTestImpl = "androidx.room:room-testing:$roomVersion"
        }

        // https://maven.google.com/web/index.html#androidx.security
        object Security {
            const val securityCrypto = "androidx.security:security-crypto:1.1.0-alpha03"                          // https://maven.google.com/web/index.html#androidx.security:security-crypto
            const val securityCryptoKtx = "androidx.security:security-crypto:1.1.0-alpha03"                       // https://maven.google.com/web/index.html#androidx.security:security-crypto-ktx
            const val securityIdentityCredential = "androidx.security:security-identity-credential:1.0.0-alpha03" // https://maven.google.com/web/index.html#androidx.security:security-identity-credential
        }

        object Test {
            private const val version = "1.4.0"
            // Core library
            const val core = "androidx.test:core:$version"
            // AndroidJUnitRunner and JUnit Rules
            const val runner = "androidx.test:runner:$version"
            const val rules = "androidx.test:rules:$version"
            // Assertions
            object Ext {
                const val junitVersion = "1.1.3"
                const val junit = "androidx.test.ext:junit:$junitVersion"
                const val junitKtx = "androidx.test.ext:junit-ktx:$junitVersion"
                const val truth = "androidx.test.ext:truth:$version"
            }
            // Espresso dependencies
            object Espresso {
                private const val version = "3.4.0"
                const val espressoCore = "androidx.test.espresso:espresso-core:$version"
                const val espressoContrib = "androidx.test.espresso:espresso-contrib:$version"
                const val espressoIntents = "androidx.test.espresso:espresso-intents:$version"
                const val espressoAccessibility = "androidx.test.espresso:espresso-accessibility:$version"
                const val espressoWeb = "androidx.test.espresso:espresso-web:$version"
                const val espressoConcurrent = "androidx.test.espresso:idling-concurrent:$version"
                // The following Espresso dependency can be either "implementation"
                // or "androidTestImplementation", depending on whether you want the
                // dependency to appear on your APK's compile classpath or the test APK
                // classpath.
                const val espressoIdlingResource = "androidx.test.espresso:espresso-idling-resource:$version"
            }
            // UIAutomator
            const val uiautomator = "androidx.test.uiautomator:uiautomator:2.2.0"
            // Orchestrator
            const val orchestrator = "androidx.test:orchestrator:1.2.0" // latest: 1.3.0-alpha05
        }
    }

    // https://github.com/evant/binding-collection-adapter/tags
    object BindingCollectionAdapter {
        const val bindingCollectionAdapter = "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter:4.0.0"
        const val bindingCollectionAdapterRecyclerView = "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter-recyclerview:4.0.0"
        const val bindingCollectionAdapterViewPager2 = "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter-viewpager2:4.0.0"
    }

    object AirBnb {
        const val mavericksVersion = "2.5.0" // https://github.com/airbnb/mavericks/releases
        const val mavericks = "com.airbnb.android:mavericks:$mavericksVersion"
        const val epoxyVersion = "5.0.0-beta05" // https://github.com/airbnb/epoxy/releases
        const val epoxy = "com.airbnb.android:epoxy:$epoxyVersion"
        const val epoxyDatabinding = "com.airbnb.android:epoxy-databinding:$epoxyVersion"
        const val epoxyProcessor = "com.airbnb.android:epoxy-processor:$epoxyVersion"
    }

    object Splitties {
        // https://github.com/LouisCAD/Splitties/releases/tag/v3.0.0
        // https://repo1.maven.org/maven2/com/louiscad/splitties/
        const val version = "3.0.0"
        const val splittiesCoroutines = "com.louiscad.splitties:splitties-coroutines:$version"
    }

    // networking

    object Retrofit {
        // https://search.maven.org/search?q=g:com.squareup.retrofit2
        private const val version = "2.9.0"
        private const val versionConverters = "2.8.1"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val retrofitConverters = "com.squareup.retrofit2:retrofit-converters:$versionConverters"
        const val adapterRxjava2 = "com.squareup.retrofit2:adapter-rxjava2:$version"
        const val converterGson = "com.squareup.retrofit2:converter-gson:$version"
        const val converterMoshi = "com.squareup.retrofit2:converter-moshi:$version"
        const val converterScalars = "com.squareup.retrofit2:converter-scalars:$version"
    }

    object OkHttp {
        // https://search.maven.org/search?q=g:com.squareup.okhttp3
        // https://github.com/square/okhttp/blob/master/CHANGELOG.md
        private const val version = "4.9.3"
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
        const val mockwebserver = "com.squareup.okhttp3:mockwebserver:$version"
    }

    object Moshi {
        // https://search.maven.org/search?q=g:com.squareup.moshi
        private const val version = "1.13.0"
        const val moshi = "com.squareup.moshi:moshi:$version"
        const val moshiAdapters = "com.squareup.moshi:moshi-adapters:$version"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$version"
        const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
    }

    const val gson = "com.google.code.gson:gson:2.8.8"

    const val threeTenABP = "com.jakewharton.threetenabp:threetenabp:1.3.1"
    // https://search.maven.org/search?q=g:org.threeten%20AND%20a:threetenbp&core=gav
    const val threeTen = "org.threeten:threetenbp:1.5.2"

    object Guava {
        // https://search.maven.org/search?q=g:com.google.guava
        // https://github.com/google/guava/releases
        const val version = "31.1"
        const val guavaJre =     "com.google.guava:guava:$version-jre"
        const val guavaAndroid = "com.google.guava:guava:$version-android"
        const val guavaTestlibJre =     "com.google.guava:guava-testlib:$version-jre"
        const val guavaTestlibAndroid = "com.google.guava:guava-testlib:$version-android"
    }

    // https://mvnrepository.com/artifact/io.reactivex.rxjava2
    object RxJava {
        const val rxjava = "io.reactivex.rxjava2:rxjava:2.2.21"
        const val rxandroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
        const val rxkotlin = "io.reactivex.rxjava2:rxkotlin:2.4.0"
    }

    // SAAS

    object GooglePlayServices {
        const val playServicesBase = "com.google.android.gms:play-services-base:17.3.0"
        const val playServicesAuthApiPhone = "com.google.android.gms:play-services-auth-api-phone:17.4.0"
        const val playServicesAdsIdentifier = "com.google.android.gms:play-services-ads-identifier:17.0.0"
        const val playServicesWallet = "com.google.android.gms:play-services-wallet:18.0.0"
        const val playServicesWalletObsolete = "com.google.android.gms:play-services-wallet:16.0.0"
        const val playServicesMaps = "com.google.android.gms:play-services-maps:17.0.0"
        const val playServicesLocation = "com.google.android.gms:play-services-location:17.0.0"
    }

    object Firebase {
        const val firebaseAnalytics = "com.google.firebase:firebase-analytics:20.1.0"        // https://maven.google.com/web/index.html#com.google.firebase:firebase-analytics
        const val firebaseAnalyticsKtx = "com.google.firebase:firebase-analytics-ktx:20.1.0" // https://maven.google.com/web/index.html#com.google.firebase:firebase-analytics-ktx
        const val firebaseAuth = "com.google.firebase:firebase-auth:21.0.1"                  // https://maven.google.com/web/index.html#com.google.firebase:firebase-auth
        const val firebaseBom = "com.google.firebase:firebase-bom:29.1.0"                    // https://maven.google.com/web/index.html#com.google.firebase:firebase-bom
        const val firebaseConfig = "com.google.firebase:firebase-config:21.0.1"              // https://maven.google.com/web/index.html#com.google.firebase:firebase-config
        const val firebaseConfigKtx = "com.google.firebase:firebase-config-ktx:21.0.1"       // https://maven.google.com/web/index.html#com.google.firebase:firebase-config-ktx
        const val firebaseCore = "com.google.firebase:firebase-core:20.1.0"                  // https://maven.google.com/web/index.html#com.google.firebase:firebase-core
        const val firebaseFirestore = "com.google.firebase:firebase-firestore:24.0.1"        // https://maven.google.com/web/index.html#com.google.firebase:firebase-firestore
        const val firebaseMessaging = "com.google.firebase:firebase-messaging:23.0.0"        // https://maven.google.com/web/index.html#com.google.firebase:firebase-messaging
        const val firebaseMessagingKtx = "com.google.firebase:firebase-messaging-ktx:23.0.0" // https://maven.google.com/web/index.html#com.google.firebase:firebase-messaging-ktx

        object Crashlytics {
            const val crashlytics = "com.google.firebase:firebase-crashlytics:17.2.1"
            const val crashlyticsNdk = "com.google.firebase:firebase-crashlytics-ndk:17.2.1"
            const val gradlePlugin = "com.google.firebase:firebase-crashlytics-gradle:2.3.0"
        }
    }

    // https://search.maven.org/artifact/junit/junit
    const val junit4 = "junit:junit:4.13.2"
    const val mockito = "org.mockito:mockito-core:3.3.0"
    const val mockitoAndroid = "org.mockito:mockito-android:3.3.2"
    const val mockk = "io.mockk:mockk:1.12.0"
    const val hamcrest = "org.hamcrest:hamcrest-integration:1.3"
    const val assertJ = "org.assertj:assertj-core:3.21.0"

    object Wire {
        // https://search.maven.org/search?q=g:com.twobuffers.wire
        // https://s01.oss.sonatype.org/content/groups/public/com/twobuffers/wire/ (RELEASES)
        // https://s01.oss.sonatype.org/content/groups/staging/com/twobuffers/wire/ (SNAPSHOTS)
        private const val version = "0.4.8"
        const val wireCoroutines = "com.twobuffers.wire:wire-coroutines:$version"
        const val wireCoroutinesAndroid = "com.twobuffers.wire:wire-coroutines-android:$version"
        const val wireDiAnnotationsCommon = "com.twobuffers.wire:wire-di-annotations-common:$version"
        const val wireDiAnnotations = "com.twobuffers.wire:wire-di-annotations:$version"
        const val wireInitializer = "com.twobuffers.wire:wire-initializer:$version"
        const val wireFirebaseConfig = "com.twobuffers.wire:wire-firebase-config:$version"
        const val wireUtils = "com.twobuffers.wire:wire-utils:$version"
        const val wireUtilsAndroid = "com.twobuffers.wire:wire-utils-android:$version"
        const val wireThreeTenABP = "com.twobuffers.wire:wire-threetenabp:$version"
        const val wireMoshi = "com.twobuffers.wire:wire-moshi:$version"
        const val wireRetrofit = "com.twobuffers.wire:wire-retrofit:$version"
        const val wireRetrofitAndroid = "com.twobuffers.wire:wire-retrofit-android:$version"
        const val wireRxjava = "com.twobuffers.wire:wire-rxjava:$version"
        const val wireRxjavaAndroid = "com.twobuffers.wire:wire-rxjava-android:$version"
    }

    object Suricate {
        // https://search.maven.org/search?q=g:com.twobuffers.suricate
        // https://s01.oss.sonatype.org/content/groups/public/com/twobuffers/suricate/ (RELEASES)
        // https://s01.oss.sonatype.org/content/groups/staging/com/twobuffers/suricate/ (SNAPSHOTS)
        private const val version = "0.0.1"
        const val suricate = "com.twobuffers.suricate:suricate:$version"
        // alternatively:
        // https://jitpack.io/#twobuffers/suricate
        const val suricate_master = "com.github.twobuffers:suricate:master-SNAPSHOT"
        const val suricate_c17dd2457b = "com.github.twobuffers:suricate:c17dd2457b"
        const val suricate_0_0_1 = "com.github.twobuffers:suricate:0.0.1"
    }
}

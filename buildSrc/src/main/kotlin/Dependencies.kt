object ProjectModules {
    private const val LIBRARIES = ":libraries"
    const val DOMAIN = "$LIBRARIES:domain"
    const val DATA = "$LIBRARIES:data"
    const val DATA_API = "$LIBRARIES:data_api"
    const val COMMONS = ":commons"
}

object Features {
    const val MARKET_CHART = ":features:marketChart"
}

object Versions {
    const val KOTLIN = "1.3.72"
    const val RETROFIT = "2.9.0"
    const val RETROFIT_CONVERTER_GSON = "2.9.0"
    const val KOTLINX_COROUTINES = "1.3.9"

    const val JUNIT = "4.13.1"
    const val MOCKITO = "2.2.0"
    const val KOTLIN_COROUTINES_TEST = "1.3.9"
    const val ANDROID_TEST_ESPRESSO = "3.3.0"
    const val ANDROID_JUNIT = "1.1.2"
    const val FRAGMENT_TEST = "1.2.4"

    const val ANDROIDX_CORE = "1.3.2"
    const val ANDROIDX_APPCOMPAT = "1.2.0"
    const val ANDROIDX_LIFECYCLE_VIEWMODEL = "2.1.0-beta01"
    const val ANDROIDX_CORE_TEST = "2.1.0"
    const val CONSTRAINT_LAYOUT = "2.0.4"

    const val DAGGER_ANDROID = "2.24"
    const val DAGGER_COMPILER = "2.24"
    const val DAGGER_PROCESSOR = "2.24"

    const val ANDROID_CHART = "v3.1.0"
    const val ANDROID_MATERIAL = "1.2.1"
}

object Dependencies {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}"
    const val KOTLINX_COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLINX_COROUTINES}"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_CONVERTER_GSON =
        "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT_CONVERTER_GSON}"

    const val ANDROIDX_CORE = "androidx.core:core-ktx:${Versions.ANDROIDX_CORE}"
    const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:${Versions.ANDROIDX_APPCOMPAT}"
    const val ANDROIDX_LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.ANDROIDX_LIFECYCLE_VIEWMODEL}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:2.3.2"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:2.3.2"

    const val DAGGER_ANDROID = "com.google.dagger:dagger-android:${Versions.DAGGER_ANDROID}"
    const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${Versions.DAGGER_COMPILER}"
    const val DAGGER_PROCESSOR = "com.google.dagger:dagger-android-processor:${Versions.DAGGER_PROCESSOR}"

    const val ANDROID_CHART = "com.github.PhilJay:MPAndroidChart:${Versions.ANDROID_CHART}"
    const val ANDROID_MATERIAL = "com.google.android.material:material:${Versions.ANDROID_MATERIAL}"
}

object TestDependencies {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val MOCKITO = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.MOCKITO}"
    const val KOTLIN_COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.KOTLIN_COROUTINES_TEST}"
    const val ANDROIDX_CORE_TEST = "androidx.arch.core:core-testing:${Versions.ANDROIDX_CORE_TEST}"

    const val ANDROID_TEST_ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ANDROID_TEST_ESPRESSO}"
    const val ANDROID_JUNIT = "androidx.test.ext:junit:${Versions.ANDROID_JUNIT}"
    const val RULES = "androidx.test:rules:1.2.0"
    const val FRAGMENT_TEST = "androidx.fragment:fragment-testing:${Versions.FRAGMENT_TEST}"
}
object Versions{
    const val kotlin_stdlib_version = "1.5.10"
    const val core_ktx_version = "1.5.0"
    const val appcompat_version = "1.3.0"
    const val material_version = "1.3.0"
    const val constraintlayout_version = "2.0.4"
    const val junit_version = "4.+"
    const val junit_test_version = "1.1.2"
    const val espresso_core_version = "3.3.0"
    const val gradle_version = "4.2.1"
    const val gson_version = "2.8.7"

    //Facebook
    const val facebook_android_sdk_version = "[4,5)"
}

object Deps{
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle_version}"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_stdlib_version}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_stdlib_version}"
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx_version}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat_version}"
    const val material = "com.google.android.material:material:${Versions.material_version}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout_version}"
    const val junit = "junit:junit:${Versions.junit_version}"
    const val junit_test = "androidx.test.ext:junit:${Versions.junit_test_version}"
    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core_version}"
    const val facebook_android_sdk = "com.facebook.android:facebook-android-sdk:${Versions.facebook_android_sdk_version}"
    const val gson = "com.google.code.gson:gson:${Versions.gson_version}"
}
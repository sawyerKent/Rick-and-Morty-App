plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}
android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21 //25
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(Deps.hiltAndroid)
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("junit:junit:4.12")
    kapt(Deps.hiltCompiler)

    // Retrofit
    implementation(Deps.retrofit)
    implementation(Deps.gsonConverter)

    // Room components
    implementation(Deps.roomRuntime)
    kapt(Deps.roomCompiler)
    implementation(Deps.roomKtx)

    // Paging
    implementation("androidx.paging:paging-runtime:3.1.1")

    // Coroutines
    implementation(Deps.coroutineViewModel)
    implementation(Deps.coroutineLifecycle)
}
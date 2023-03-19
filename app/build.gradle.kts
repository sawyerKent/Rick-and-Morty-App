plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp") version "1.6.10-1.0.4"
}
android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.skent.rickandmortyv2"
        minSdk = 21 //25
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        // Enables Jetpack Compose for this module
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.0"
    }
    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }
    ksp {
        arg("compose-destinations.generateNavGraphs", "true")
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.paging:paging-common-ktx:3.1.1")
    implementation("androidx.paging:paging-compose:1.0.0-alpha15")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    // Implement lib_data module inside the app module
    implementation(project(":lib_data"))

    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltCompiler)

    // Coroutines
    implementation(Deps.coroutineViewModel)
    implementation(Deps.coroutineLifecycle)

    // Compose
    implementation("androidx.compose.ui:ui:${Versions.compose}")
    implementation("androidx.compose.material3:material3:1.0.0-alpha01")
    implementation("androidx.compose.material:material:1.3.0-alpha01")
    implementation("androidx.activity:activity-compose:${Versions.compose}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Versions.compose}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.0")

    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Versions.compose}")
    debugImplementation("androidx.compose.ui:ui-tooling:${Versions.compose}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${Versions.compose}")
    implementation("androidx.compose.material:material-icons-extended:${Versions.compose}")
    implementation("io.github.raamcosta.compose-destinations:animations-core:1.5.13-beta")
    ksp("io.github.raamcosta.compose-destinations:ksp:1.5.13-beta")

    implementation("androidx.navigation:navigation-fragment-ktx:2.5.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0-beta01")


    implementation("com.google.accompanist:accompanist-drawablepainter:0.16.0")

    implementation("io.coil-kt:coil:2.1.0")
    implementation("io.coil-kt:coil-compose:2.1.0")
}
import Versions.coroutines
import Versions.hilt
import Versions.retro
import Versions.gson
import Versions.room

object Versions {
    const val hilt = "2.42"
    const val kotlin = "1.6.10" //1.5.31
    const val gradle = "7.2.1"
    const val retro = "2.9.0"
    const val gson = "2.9.0"
    const val room = "2.4.0-alpha03"
    const val coroutines = "2.5.0"
    const val compose = "1.1.1" //1.1.1
}
object Deps {
    val hiltAndroid = "com.google.dagger:hilt-android:$hilt"
    val hiltCompiler = "com.google.dagger:hilt-compiler:$hilt"
    val retrofit = "com.squareup.retrofit2:retrofit:$retro"
    val gsonConverter = "com.squareup.retrofit2:converter-gson:$gson"
    val roomRuntime = "androidx.room:room-runtime:$room"
    val roomCompiler = "androidx.room:room-compiler:$room"
    val roomKtx = "androidx.room:room-ktx:$room"
    val coroutineViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$coroutines"
    val coroutineLifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:$coroutines"
    //val gson = "com.google.code.gson:gson:$retro"
}
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "mbk.io.smarthouse"
    compileSdk = 33

    defaultConfig {
        applicationId = "mbk.io.smarthouse"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Retrofit && Converter
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //OkHttpClien
    val okHttpClienVersion = "4.12.0"
    implementation("com.squareup.okhttp3:okhttp:$okHttpClienVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okHttpClienVersion")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("androidx.appcompat:appcompat-resources:1.7.0-alpha03")

    //Room
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")

    /*PullRefresh*/
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")

    //coil
    implementation("io.coil-kt:coil:2.5.0")


    //hilt
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-compiler:2.50")

    //ViewModel && LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    /*ViewModels*/
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    /*ViewPager2*/
    implementation("androidx.viewpager2:viewpager2:1.0.0")
}
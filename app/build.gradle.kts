plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.umg_moto_xpress_android"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.umg_moto_xpress_android"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String","BASE_URL","\"https://localhost:9090\"")
        }

        release {
            buildConfigField("String","BASE_URL","\"https://localhost:9090\"")
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
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.legacy.support.v4)
    implementation(libs.annotation)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //Secure Preferences
    implementation("com.scottyab:secure-preferences-lib:0.1.7")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.1.0")
    implementation("com.google.code.gson:gson:2.6.2")
    implementation("com.squareup.retrofit2:converter-gson:2.1.0")

    implementation("com.google.android.material:material:1.5.0")
    implementation("me.relex:circleindicator:2.1.6")
    implementation("org.imaginativeworld.whynotimagecarousel:whynotimagecarousel:2.1.0")

    implementation("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.1")

    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
}
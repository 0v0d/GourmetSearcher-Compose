plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.secrets.gradle.plugin)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.feature_restaurant"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

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
    buildFeatures {
        compose = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.java.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.java.get())
    }
    kotlinOptions {
        jvmTarget = libs.versions.kotlin.jvm.get()
    }
}

dependencies {
    implementation(projects.core)
    implementation(projects.sharedUi)

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.androidx.runtime)
    implementation(libs.androidx.hilt.navigation.compose)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.lifecycle.runtime.compose.android)
    ksp(libs.dagger.hilt.android.compiler)

    implementation(libs.paging.runtime)
    implementation(libs.paging.compose)

    debugImplementation(libs.androidx.ui.tooling)

    // MockitoJUnitRunner
    testImplementation(libs.mockito)
    testImplementation(libs.junit)
    testImplementation(libs.dagger.hilt.android.testing)
    kspTest(libs.dagger.hilt.android.compiler)
    testImplementation(libs.androidx.runner)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.kotlinx.coroutines.test)
}
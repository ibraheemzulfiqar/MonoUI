plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "2.1.0"
}

android {
    namespace = "com.mono.ui"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mono.ui"
        minSdk = 21
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(project(":mono-ui"))


    val nav_version = "2.8.5"

    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}


allprojects {
    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
        compilerOptions {
            freeCompilerArgs.addAll(
                listOf(
                    "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
                    "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
                    "-opt-in=androidx.compose.runtime.ExperimentalComposeApi",
                    "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
                    "-opt-in=com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi",
                    "-opt-in=com.google.accompanist.pager.ExperimentalPagerApi",
                    "-opt-in=com.google.accompanist.permissions.ExperimentalPermissionsApi",
                    "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                    "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
                    "-opt-in=androidx.compose.foundation.layout.ExperimentalLayoutApi",
                )
            )
        }
    }
}


plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.gerenciadodetarefaskotlin52_2026"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.example.gerenciadodetarefaskotlin52_2026"
        minSdk = 24
        targetSdk = 37
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

}

dependencies {
    // AppCompat — necessário para AppCompatActivity e temas Material
    implementation(libs.androidx.appcompat)
    // Material Design — componentes visuais
    implementation(libs.material)
    // Core KTX — extensões Kotlin para APIs Android
    implementation(libs.androidx.core.ktx)
    // Activity KTX
    implementation(libs.androidx.activity)
    // ConstraintLayout (opcional, caso queira usar no futuro)
    implementation(libs.androidx.constraintlayout)

    // Testes
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
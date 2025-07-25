plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    //Firbase
    id("com.google.gms.google-services")

    //Dagger hilt

    id("com.google.dagger.hilt.android")


    //kps:

    id("com.google.devtools.ksp") //version "1.9.23-1.0.19"


}

android {
    namespace = "com.solidtype.atenas_apk_2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.solidtype.atenas_apk_2"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.firebase:firebase-auth-ktx:22.3.1")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.7")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.activity:activity:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.7.7")
    implementation("androidx.navigation:navigation-ui:2.7.7")
//TEstin area
    testImplementation("io.mockk:mockk:1.12.2")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")

    testImplementation("junit:junit:4.13.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))

    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.compose.material:material-icons-extended:1.6.4")
    implementation("androidx.compose.material:material-icons-core:1.6.4")

    testImplementation("com.google.firebase:firebase-auth:21.0.3")


    //firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.4"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-firestore-ktx:24.10.3")
    implementation("com.google.firebase:firebase-auth-ktx:22.3.1")

    //Serializacion para json
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    //Corrutinas
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")

    implementation("androidx.navigation:navigation-compose:2.7.7")

    //LoginViewModel
    implementation("androidx.compose.runtime:runtime-livedata:1.6.4")

    //Dagger hilt
    implementation("com.google.dagger:hilt-android:2.51")
    ksp("com.google.dagger:hilt-android-compiler:2.51")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    //KSP:
    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.22-1.0.17") // Dependencia de KSP
    implementation("com.google.devtools.ksp:symbol-processing:1.9.22-1.0.17") // Dependencia de KSP

    //Room Database
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")

    ksp("androidx.room:room-compiler:$room_version")

    implementation("androidx.room:room-ktx:$room_version")
    //Excel maneger
    implementation("org.apache.poi:poi:5.2.5") // Para trabajar con formatos de archivo de Excel (XLS)
    implementation("org.apache.poi:poi-ooxml:5.2.5") // Para trabajar con formatos de archivo de Excel (XLSX)
    implementation("com.google.android.gms:play-services-base:17.6.0")
    implementation("com.google.firebase:firebase-messaging:22.0.0")

    //Lotti y animaciones :
    implementation("com.airbnb.android:lottie-compose:6.0.1")

    //Printer

    // implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    //testImplementation ("org.mockito.kotlin:mockito-kotlin:3.2.0")

    //Sincronizacion el worker
    implementation("androidx.work:work-runtime-ktx:2.7.1")

    implementation("com.github.kenglxn.QRGen:android:3.0.1") //QR

    //TEsteando Impresora :
    //implementation("com.github.DantSu:ESCPOS-ThermalPrinter-Android:3.3.0")
    //implementation("com.github.mazenrashed:Printooth:1.3.1")

}

ksp {
    // Configuración específica de KSP
    arg("output", "$buildDir/generated/ksp")// Argumentos opcionales para KSP
    // Puedes agregar más configurations según sea necesario
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17" // La versión de JVM que estás utilizando
        //freeCompilerArgs += listOf("-Xlint:deprecation")
        //freeCompilerArgs += listOf("-opt-in=kotlin.RequiresOptIn") // Otras opciones de compilación
    }
}
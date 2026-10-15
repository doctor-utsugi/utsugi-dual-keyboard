plugins {
    id("com.android.application")
}

android {
    namespace = "io.github.doctorutsugi.dualkeyboard"
    compileSdk = 36

    defaultConfig {
        applicationId = "io.github.doctorutsugi.dualkeyboard"
        minSdk = 33
        targetSdk = 36
        versionCode = 26
        versionName = "0.23.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    testImplementation("junit:junit:4.13.2")
}

tasks.withType<org.gradle.api.tasks.compile.JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

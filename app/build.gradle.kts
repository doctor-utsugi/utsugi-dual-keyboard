plugins {
    id("com.android.application")
}

// Release key: UTSUGI_KEYSTORE / UTSUGI_KEYSTORE_PASSWORD, else ~/.android/utsugi-dual-keyboard-release.{jks,pass}.
val releaseKeystore = file(System.getenv("UTSUGI_KEYSTORE")
    ?: "${System.getProperty("user.home")}/.android/utsugi-dual-keyboard-release.jks")
val releaseKeyPassword = System.getenv("UTSUGI_KEYSTORE_PASSWORD")
    ?: releaseKeystore.resolveSibling(releaseKeystore.nameWithoutExtension + ".pass")
        .takeIf { it.exists() }?.readText()?.trim()

android {
    namespace = "io.github.doctorutsugi.dualkeyboard"
    compileSdk = 36

    defaultConfig {
        applicationId = "io.github.doctorutsugi.dualkeyboard"
        minSdk = 33
        targetSdk = 36
        versionCode = 27
        versionName = "0.24.0"
    }

    signingConfigs {
        if (releaseKeystore.exists() && releaseKeyPassword != null) {
            create("release") {
                storeFile = releaseKeystore
                storePassword = releaseKeyPassword
                keyAlias = "utsugi"
                keyPassword = releaseKeyPassword
            }
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.findByName("release")
        }
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

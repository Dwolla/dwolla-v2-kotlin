group = "com.dwolla"
version = "0.1.0-SNAPSHOT"

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    id("org.jetbrains.kotlin.jvm").version("1.3.21")
    id("java-library")
    id("maven-publish")
    id("org.jlleitschuh.gradle.ktlint").version("8.0.0")
}

repositories {
    jcenter()

    maven(url = "https://jitpack.io") {
        name = "jitpack"
    }

    maven(url = "https://kotlin.bintray.com/kotlinx") {
        name = "kotlinx"
    }
}

dependencies {
    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Fuel HTTP client
    implementation(group = "com.github.kittinunf.fuel", name = "fuel", version = "-SNAPSHOT")
    implementation(group = "com.github.kittinunf.fuel", name = "fuel-coroutines", version = "-SNAPSHOT")

    // GSON
    implementation(group = "com.google.code.gson", name = "gson", version = "2.8.5")
    implementation("net.dongliu:gson-java8-datatype:1.1.0")

    // Testing
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("io.mockk:mockk:1.9.3")
}

publishing {
    publications {
        create<MavenPublication>("dwolla-v2-kotlin") {
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "dwolla-v2-kotlin"
            url = uri("file://$buildDir/repo")
        }
    }
}

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.20"
    application
}

group = "me.edi"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://jitpack.io")
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("org.reflections", "reflections", "0.9.12")
    implementation("com.beust","klaxon","5.0.1")
    implementation("com.github.jkcclemens","khttp","-SNAPSHOT")
    testImplementation(kotlin("test-junit"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "util.Runner"
}
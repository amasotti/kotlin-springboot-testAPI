import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.0-SNAPSHOT"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.graalvm.buildtools.native") version "0.9.20"
    kotlin("jvm") version "1.8.20"
    kotlin("plugin.spring") version "1.8.20"
    jacoco
}

group = "learning.antonio"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("io.mockk:mockk:1.13.4")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

// Tests settings

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport")
}

tasks.test {
    finalizedBy("jacocoTestReport")
}


tasks.jacocoTestReport {
    reports {
        html.required.set(true)
        xml.required.set(true)
        csv.required.set(false)
    }
}


val testCoverage by tasks.registering {
    group = "verification"
    description = "Runs the unit tests with coverage"

    dependsOn(":test",
        ":jacocoTestReport"
        )

    tasks["jacocoTestReport"].mustRunAfter(tasks["test"])
}
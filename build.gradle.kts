plugins {
    java
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.actuator)
    implementation(libs.spring.boot.starter.batch)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.spring.boot.security.web)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.flyway.core)
    implementation(libs.flyway.database.postgresql)
    implementation(libs.spring.jdbc)
    implementation(libs.spring.boot.configuration.processor)
    implementation(libs.spring.boot.starter.data.jpa)
    runtimeOnly(libs.postgresql)
    runtimeOnly(libs.r2dbc.postgresql)
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.reactor.test)
    testImplementation(libs.spring.batch.test)
    testImplementation(libs.spring.security.test)
    runtimeOnly(libs.h2)
    runtimeOnly(libs.r2dbc.h2)
    testRuntimeOnly(libs.junit.platform.launcher)
    implementation(libs.springdoc.openapi.ui)
    implementation(libs.hikari.cp)
//    implementation(libs.spring.boot.docker.compose)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

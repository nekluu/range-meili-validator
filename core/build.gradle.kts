import org.gradle.api.internal.provider.ValueSupplier.ValueProducer.task
import org.gradle.kotlin.dsl.task

plugins {
    `java-library`
}

group = "com.range"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:5.3.2")
    implementation("com.google.guava:guava:32.1.2-jre")
    testImplementation("org.junit.jupiter:junit-jupiter:6.0.2")
    // Source: https://mvnrepository.com/artifact/org.slf4j/slf4j-api
    compileOnly("org.slf4j:slf4j-api:2.0.17")
}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
    withSourcesJar()
    withJavadocJar()
}


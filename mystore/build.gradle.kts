plugins {
    java
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "be.cbtw"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(22)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.flywaydb:flyway-core:9.22.3")
    implementation("org.postgresql:postgresql")
    implementation("org.springframework.boot:spring-boot-starter-web:3.3.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("junit:junit:4.13.1")
    testImplementation("com.h2database:h2:2.2.220")

}

tasks.withType<Test> {
    useJUnitPlatform()
}

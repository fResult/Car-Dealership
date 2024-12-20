import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
  java
  application
  id("org.springframework.boot") version "3.4.0"
  id("io.spring.dependency-management") version "1.1.6"
  id("org.hibernate.orm") version "6.6.3.Final"
//  id("org.graalvm.buildtools.native") version "0.10.3"
}

group = "dev.fResult"
version = "0.0.1"
val boostrapClassName = "$group.carDealership.CarDealershipApplication"

application {
  mainClass.set(boostrapClassName) // for `./gradlew run`
}

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(23)
  }
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-web")
  developmentOnly("org.springframework.boot:spring-boot-devtools")
//  implementation("com.fasterxml.jackson.core:jackson-databind:2.18.1")

  runtimeOnly("com.h2database:h2")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

hibernate {
  enhancement {
    enableAssociationManagement = true
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
  // jvmArgs = listOf("-Dnet.bytebuddy.experimental=true")
}

tasks.withType<BootRun> {
  doFirst {
    jvmArgs = listOf("-Dnet.bytebuddy.experimental=true")
  }
}

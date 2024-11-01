plugins {
  java
  id("org.springframework.boot") version "3.3.5"
  id("io.spring.dependency-management") version "1.1.6"
  id("org.hibernate.orm") version "6.5.3.Final"
  id("org.graalvm.buildtools.native") version "0.10.3"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  developmentOnly("org.springframework.boot:spring-boot-devtools")
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
//  jvmArgs = listOf("-Dnet.bytebuddy.experimental=true")
}
//tasks.named("bootRun") {
//    doFirst {
//        (this as JavaExec).jvmArgs = listOf("-Dnet.bytebuddy.experimental=true")
//    }
//}

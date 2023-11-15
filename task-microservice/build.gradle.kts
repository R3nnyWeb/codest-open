plugins {
    java
    id("org.springframework.boot") version "3.1.1"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "ru.tinkoff.summer"
version = "1.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}


repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2022.0.3"

dependencies {
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.liquibase:liquibase-core:4.23.0")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.1.1")
    implementation(project(":task-shared-domain"))
    // https://mvnrepository.com/artifact/net.logstash.logback/logstash-logback-encoder
    implementation("net.logstash.logback:logstash-logback-encoder:7.4")

    // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    testImplementation("ch.qos.logback:logback-classic:1.4.8")
// https://mvnrepository.com/artifact/ch.qos.logback/logback-core
    implementation("ch.qos.logback:logback-core:1.4.8")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("org.mockito:mockito-core:5.4.0")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}


tasks.withType<Test> {
    useJUnitPlatform()
}
tasks.compileJava {
    options.encoding = "UTF-8"
}
tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes["Main-Class"] = "ru.tinkoff.summer.taskmicroservice.TaskMicroserviceApplication"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })

}
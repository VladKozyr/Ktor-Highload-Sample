val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val exposeVersion: String by project
val postgresVersion: String by project
val hikariVersion: String by project
val jedisVersion: String by project
val rabbitmqVersion: String by project

plugins {
    application
    kotlin("jvm") version "1.6.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.0"
}

group = "com.kozyr.vlad"
version = "0.0.1"
application {
    mainClass.set("com.kozyr.vlad.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-serialization:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")

    implementation("org.jetbrains.exposed:exposed-core:$exposeVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposeVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposeVersion")

    implementation("org.postgresql:postgresql:$postgresVersion")
    implementation("com.zaxxer:HikariCP:$hikariVersion")
    implementation("redis.clients:jedis:$jedisVersion")
    implementation("com.rabbitmq:amqp-client:$rabbitmqVersion")
}
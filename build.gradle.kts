

group = "me.user"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.5.31"
    id("io.qameta.allure") version "2.9.4"
}


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
    implementation("io.qameta.allure:allure-junit5:2.16.0")
    implementation("org.assertj:assertj-core:3.21.0")
}

allure {
    adapter {
        version.set("2.15.0")
        autoconfigure.set(true)
        aspectjWeaver.set(true)
        aspectjVersion.set("1.9.7")
        frameworks {
            junit5
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

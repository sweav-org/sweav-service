plugins {
    kotlin("plugin.jpa") version "1.9.25"
}

dependencies {
    implementation(project(":common"))

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // querydsl
    kapt("io.github.openfeign.querydsl:querydsl-apt:6.10.1:jpa")
    api("io.github.openfeign.querydsl:querydsl-jpa:6.10.1")

    // mysql
    runtimeOnly("com.mysql:mysql-connector-j")
}

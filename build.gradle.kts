plugins {
    java
    jacoco
    application
    checkstyle
    alias(libs.plugins.task.tree)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.gson)
    implementation(libs.guava)
    implementation(libs.logback.classic)
    implementation(libs.slf4j.api)
    testImplementation(libs.bundles.mockito.all)
    testImplementation(libs.junit.jupiter)
    testImplementation(platform(libs.junit.bom))
    testRuntimeOnly(libs.junit.launcher)
}

application {
    mainClass.set("io.huangsam.trial.JavaTrial")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        html.required.set(true)
        xml.required.set(true)
    }
    // https://www.baeldung.com/jacoco-report-exclude
    classDirectories.setFrom(files(classDirectories.files.map {
        fileTree(it) {
            exclude("io/huangsam/**/JavaTrial.class")
        }
    }))
}

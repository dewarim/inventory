grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.dependency.resolver = "maven"
grails.project.war.file = "target/${appName}.war"
grails.project.target.level = 1.8
grails.project.source.level = 1.8
grails.reload.enabled = true

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        grailsCentral()
        mavenLocal()
        mavenCentral()
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        // runtime 'mysql:mysql-connector-java:5.1.20'
        runtime 'postgresql:postgresql:9.1-901.jdbc4'
    }

    plugins {
        runtime ":hibernate:3.6.10.18"
        build ":tomcat:8.0.15"

        runtime ":jquery:1.11.1"
        compile ":asset-pipeline:1.9.9"
        compile(':spring-security-core:2.0-RC4')

    }
}

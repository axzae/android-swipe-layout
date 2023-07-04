@file:Suppress("UnstableApiUsage")

val publicationName = "mavenJava"
apply(plugin = "org.gradle.maven-publish")
apply(plugin = "org.gradle.signing")

group = property("GROUP").toString()
version = property("VERSION").toString()

configure<PublishingExtension> {
    publications {
        create<MavenPublication>(publicationName) {
            artifactId = property("ARTIFACT_ID").toString()
            artifact("$buildDir/outputs/aar/${project.name}-release.aar")

            pom {
                name.set(property("DISPLAY_NAME").toString())
                description.set(property("DESCRIPTION").toString())
                url.set(property("WEBSITE").toString())

                organization {
                    name.set(property("ORGANIZATION_NAME").toString())
                    url.set(property("ORGANIZATION_URL").toString())
                }

                licenses {
                    license {
                        name.set(property("LICENSE_NAME").toString())
                        url.set(property("LICENSE_URL").toString())
                    }
                }

                scm {
                    val (vcsHost, vcsUser, vcsRepo) = property("VCS_URL").toString()
                        .substringAfter("https://")
                        .split("/")
                    url.set(property("VCS_URL").toString())
                    connection.set("scm:git:git://$vcsHost/$vcsUser/$vcsRepo.git")
                    developerConnection.set("scm:git:ssh://git@$vcsHost:$vcsUser/$vcsRepo.git")
                }

                developers {
                    developer {
                        name.set(property("DEVELOPER_NAME").toString())
                    }
                }
            }
        }
    }

    repositories {
        maven {
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = System.getenv("SONATYPE_USERNAME")
                password = System.getenv("SONATYPE_SECRET")
            }
        }
    }
}

configure<SigningExtension> {
    val signingKey = System.getenv("GPG_SIGNING_KEY")
    val signingPassword = System.getenv("GPG_SIGNING_PASSWORD")
    useInMemoryPgpKeys(signingKey.chunked(64).joinToString("\n"), signingPassword)

    val pubExt = checkNotNull(extensions.findByType(PublishingExtension::class.java))
    val publication = pubExt.publications[publicationName]
    sign(publication)
}

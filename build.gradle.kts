plugins {
    `java-plugin`
}

val buildNumber: String? = System.getenv("BUILD_NUMBER")

rootProject.version = if (buildNumber != null) "3.4-$buildNumber" else "3.4"

subprojects.filter { it.name != "api" }.forEach {
    it.project.version = rootProject.version
}
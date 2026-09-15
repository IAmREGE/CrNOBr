plugins {
	id("net.fabricmc.fabric-loom-remap") version "1.17-SNAPSHOT"
}

version = project.property("mod_version") as String
group = project.property("maven_group") as String

base {
	archivesName = project.property("archives_base_name") as String
}

dependencies {
	minecraft("com.mojang:minecraft:${project.property("mc_version")}")
	mappings("net.fabricmc:yarn:${project.property("yarn_mappings")}:v2")
	modImplementation("net.fabricmc:fabric-loader:${project.property("loader_version")}")
}

tasks.withType<JavaCompile>().configureEach {
	options.release = 8
}

java {
	withSourcesJar()
	sourceCompatibility = JavaVersion.VERSION_1_8
	targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.jar {
	from(rootProject.file("LICENSE")) {
		into("META-INF")
	}
}

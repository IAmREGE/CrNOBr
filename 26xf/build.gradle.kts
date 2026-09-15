plugins {
	id("net.fabricmc.fabric-loom") version "1.17-SNAPSHOT"
	id("xyz.wagyourtail.jvmdowngrader") version "1.3.6"
}

version = project.property("mod_version") as String
group = project.property("maven_group") as String

base {
	archivesName = project.property("archives_base_name") as String
}

jvmdg {
	downgradeTo = JavaVersion.VERSION_1_8
}

loom {
	splitEnvironmentSourceSets()
	mods {
		create("crnobr") {
			sourceSet(sourceSets.main.get())
			sourceSet(sourceSets.named("client").get())
		}
	}
}

dependencies {
	minecraft("com.mojang:minecraft:${project.property("mc_version")}")
	implementation("net.fabricmc:fabric-loader:${project.property("loader_version")}")
	compileOnly(files("fakeneo.jar"))
}

tasks.withType<JavaCompile>().configureEach {
	options.release = 25
}

java {
	withSourcesJar()
	sourceCompatibility = JavaVersion.VERSION_25
	targetCompatibility = JavaVersion.VERSION_25
}

tasks.jar {
	from(rootProject.file("LICENSE")) {
		into("META-INF")
	}
}

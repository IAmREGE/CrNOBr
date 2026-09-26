plugins {
	java
}

version = project.property("mod_version") as String
group = project.property("maven_group") as String

base {
	archivesName = project.property("archives_base_name") as String
}

repositories {
	maven("https://repository.hanbings.io/proxy") {
		name = "Fabric"
	}
	mavenCentral()
}

dependencies {
	compileOnly("net.fabricmc:sponge-mixin:0.17.4+mixin.0.8.7")
	compileOnly(files("fake.jar"))
}

tasks.withType<JavaCompile>().configureEach {
	options.encoding = "UTF-8"
	options.release = 8
}

java {
	withSourcesJar()
	sourceCompatibility = JavaVersion.VERSION_1_8
	targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.jar {
	manifest {
		attributes("MixinConfigs" to "crnobr.mixins.json")
	}
	from(rootProject.file("LICENSE")) {
		into("META-INF")
	}
}

tasks.named<Jar>("sourcesJar") {
	from(rootProject.file("LICENSE")) {
		into("META-INF")
	}
}

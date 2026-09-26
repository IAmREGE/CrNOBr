plugins {
	java
}

version = project.property("mod_version") as String
group = project.property("maven_group") as String

base {
	archivesName = project.property("archives_base_name") as String
}

repositories {
	maven("https://jitpack.io") {
		name = "JitPack"
	}
	mavenCentral()
}

dependencies {
	compileOnly("com.github.LegacyModdingMC.UniMixins:unimixins-all-1.7.10:0.1.19")
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
		attributes(
			"TweakClass" to "org.spongepowered.asm.launch.MixinTweaker",
			"MixinConfigs" to "crnobr.mixins.json",
			"ForceLoadAsMod" to "true",
			"FMLCorePluginContainsFMLMod" to "true"
		)
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
